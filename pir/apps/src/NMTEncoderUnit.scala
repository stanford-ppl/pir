import pir._
import pir.node._
import arch._
import prism.enums._

object NMTEncoderUnit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x45139 = DRAM().name("x45139").ctrl(top).srcCtx("NMTDSE.scala:413:24:xDRAM") // x45139 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x45147 = DRAM().name("x45147").ctrl(top).srcCtx("NMTDSE.scala:416:32:hidStateBufFw") // x45147 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x45148 = DRAM().name("x45148").ctrl(top).srcCtx("NMTDSE.scala:417:32:hidStateBufBw") // x45148 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x45149 = DRAM().name("x45149").ctrl(top).srcCtx("CellsPar.scala:80:24") // x45149 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45150 = DRAM().name("x45150").ctrl(top).srcCtx("CellsPar.scala:81:24") // x45150 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x45151 = DRAM().name("x45151").ctrl(top).srcCtx("CellsPar.scala:82:24") // x45151 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45177 = DRAM().name("x45177").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x45177 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45178 = DRAM().name("x45178").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x45178 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45179 = DRAM().name("x45179").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x45179 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45180 = DRAM().name("x45180").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x45180 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45181 = DRAM().name("x45181").ctrl(top).srcCtx("CellsPar.scala:94:25") // x45181 = DRAMNew(ArrayBuffer(Const(1024), Const(2048)),Const(0))
    val x45225 = DRAM().name("x45225").ctrl(top).srcCtx("CellsPar.scala:80:24") // x45225 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45226 = DRAM().name("x45226").ctrl(top).srcCtx("CellsPar.scala:81:24") // x45226 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x45227 = DRAM().name("x45227").ctrl(top).srcCtx("CellsPar.scala:82:24") // x45227 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45253 = DRAM().name("x45253").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x45253 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45254 = DRAM().name("x45254").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x45254 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45255 = DRAM().name("x45255").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x45255 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45256 = DRAM().name("x45256").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x45256 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45257 = DRAM().name("x45257").ctrl(top).srcCtx("CellsPar.scala:94:25") // x45257 = DRAMNew(ArrayBuffer(Const(1024), Const(2048)),Const(0))
    val x45301 = DRAM().name("x45301").ctrl(top).srcCtx("CellsPar.scala:80:24") // x45301 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45302 = DRAM().name("x45302").ctrl(top).srcCtx("CellsPar.scala:81:24") // x45302 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x45303 = DRAM().name("x45303").ctrl(top).srcCtx("CellsPar.scala:82:24") // x45303 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45329 = DRAM().name("x45329").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x45329 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x45330 = DRAM().name("x45330").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x45330 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x45331 = DRAM().name("x45331").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x45331 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x45332 = DRAM().name("x45332").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x45332 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x45333 = DRAM().name("x45333").ctrl(top).srcCtx("CellsPar.scala:94:25") // x45333 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x45377 = DRAM().name("x45377").ctrl(top).srcCtx("CellsPar.scala:80:24") // x45377 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45378 = DRAM().name("x45378").ctrl(top).srcCtx("CellsPar.scala:81:24") // x45378 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x45379 = DRAM().name("x45379").ctrl(top).srcCtx("CellsPar.scala:82:24") // x45379 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45405 = DRAM().name("x45405").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x45405 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45406 = DRAM().name("x45406").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x45406 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45407 = DRAM().name("x45407").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x45407 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45408 = DRAM().name("x45408").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x45408 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45409 = DRAM().name("x45409").ctrl(top).srcCtx("CellsPar.scala:94:25") // x45409 = DRAMNew(ArrayBuffer(Const(1024), Const(2048)),Const(0))
    val x45453 = DRAM().name("x45453").ctrl(top).srcCtx("CellsPar.scala:80:24") // x45453 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45454 = DRAM().name("x45454").ctrl(top).srcCtx("CellsPar.scala:81:24") // x45454 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x45455 = DRAM().name("x45455").ctrl(top).srcCtx("CellsPar.scala:82:24:output_hidden") // x45455 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x45481 = DRAM().name("x45481").ctrl(top).srcCtx("CellsPar.scala:90:21:kernel") // x45481 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45482 = DRAM().name("x45482").ctrl(top).srcCtx("CellsPar.scala:91:21:kernel") // x45482 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45483 = DRAM().name("x45483").ctrl(top).srcCtx("CellsPar.scala:92:21:kernel") // x45483 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45484 = DRAM().name("x45484").ctrl(top).srcCtx("CellsPar.scala:93:21:kernel") // x45484 = DRAMNew(ArrayBuffer(Const(1024), Const(512)),Const(0))
    val x45485 = DRAM().name("x45485").ctrl(top).srcCtx("CellsPar.scala:94:25") // x45485 = DRAMNew(ArrayBuffer(Const(1024), Const(2048)),Const(0))
    val x48394 = UnitController(style=SeqPipe, level=OuterControl).name("x48394").ctrl(top).srcCtx("NMTDSE.scala:425:11") // Hwblock(Block(Const(())),false)
    val x45529_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45529_d0_b0").ctrl(x48394).srcCtx("NMTDSE.scala:426:57") // x45529 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45529_d0_b0) = false
    bufferDepthOf(x45529_d0_b0) = 1
    val x45529_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45529_d1_b0").ctrl(x48394).srcCtx("NMTDSE.scala:426:57") // x45529 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45529_d1_b0) = false
    bufferDepthOf(x45529_d1_b0) = 1
    val x45529_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45529_d2_b0").ctrl(x48394).srcCtx("NMTDSE.scala:426:57") // x45529 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45529_d2_b0) = false
    bufferDepthOf(x45529_d2_b0) = 1
    val x45529_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45529_d3_b0").ctrl(x48394).srcCtx("NMTDSE.scala:426:57") // x45529 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45529_d3_b0) = false
    bufferDepthOf(x45529_d3_b0) = 1
    val x45530_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d0_b0) = false
    bufferDepthOf(x45530_d0_b0) = 1
    val x45530_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d1_b0) = false
    bufferDepthOf(x45530_d1_b0) = 1
    val x45530_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d2_b0) = false
    bufferDepthOf(x45530_d2_b0) = 1
    val x45530_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d3_b0) = false
    bufferDepthOf(x45530_d3_b0) = 1
    val x45530_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d4_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d4_b0) = false
    bufferDepthOf(x45530_d4_b0) = 1
    val x45530_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d5_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d5_b0) = false
    bufferDepthOf(x45530_d5_b0) = 1
    val x45530_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d6_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d6_b0) = false
    bufferDepthOf(x45530_d6_b0) = 1
    val x45530_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d7_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d7_b0) = false
    bufferDepthOf(x45530_d7_b0) = 1
    val x45530_d8_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45530_d8_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45530 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45530_d8_b0) = false
    bufferDepthOf(x45530_d8_b0) = 1
    val x45531_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45531_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:140:20") // x45531 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45531_d0_b0) = true
    bufferDepthOf(x45531_d0_b0) = 1
    val x45532_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45532_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45532 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45532_d0_b0) = false
    bufferDepthOf(x45532_d0_b0) = 1
    val x45532_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45532_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45532 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45532_d1_b0) = true
    bufferDepthOf(x45532_d1_b0) = 1
    val x45533_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45533_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45533 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45533_d0_b0) = false
    bufferDepthOf(x45533_d0_b0) = 1
    val x45533_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45533_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45533 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45533_d1_b0) = true
    bufferDepthOf(x45533_d1_b0) = 1
    val x45534_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45534_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45534 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45534_d0_b0) = false
    bufferDepthOf(x45534_d0_b0) = 1
    val x45534_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45534_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45534 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45534_d1_b0) = true
    bufferDepthOf(x45534_d1_b0) = 1
    val x45535_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45535_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45535 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45535_d0_b0) = false
    bufferDepthOf(x45535_d0_b0) = 1
    val x45535_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45535_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45535 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45535_d1_b0) = true
    bufferDepthOf(x45535_d1_b0) = 1
    val x45536_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45536_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45536 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45536_d0_b0) = false
    bufferDepthOf(x45536_d0_b0) = 1
    val x45536_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45536_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45536 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45536_d1_b0) = false
    bufferDepthOf(x45536_d1_b0) = 1
    val x45536_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45536_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45536 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45536_d2_b0) = false
    bufferDepthOf(x45536_d2_b0) = 1
    val x45536_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45536_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45536 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45536_d3_b0) = false
    bufferDepthOf(x45536_d3_b0) = 1
    val x45537_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45537_d0_b0").ctrl(x48394).srcCtx("NMTDSE.scala:427:57") // x45537 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45537_d0_b0) = false
    bufferDepthOf(x45537_d0_b0) = 1
    val x45537_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45537_d1_b0").ctrl(x48394).srcCtx("NMTDSE.scala:427:57") // x45537 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45537_d1_b0) = false
    bufferDepthOf(x45537_d1_b0) = 1
    val x45537_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45537_d2_b0").ctrl(x48394).srcCtx("NMTDSE.scala:427:57") // x45537 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45537_d2_b0) = false
    bufferDepthOf(x45537_d2_b0) = 1
    val x45537_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45537_d3_b0").ctrl(x48394).srcCtx("NMTDSE.scala:427:57") // x45537 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45537_d3_b0) = false
    bufferDepthOf(x45537_d3_b0) = 1
    val x45538_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d0_b0) = false
    bufferDepthOf(x45538_d0_b0) = 1
    val x45538_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d1_b0) = false
    bufferDepthOf(x45538_d1_b0) = 1
    val x45538_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d2_b0) = false
    bufferDepthOf(x45538_d2_b0) = 1
    val x45538_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d3_b0) = false
    bufferDepthOf(x45538_d3_b0) = 1
    val x45538_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d4_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d4_b0) = false
    bufferDepthOf(x45538_d4_b0) = 1
    val x45538_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d5_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d5_b0) = false
    bufferDepthOf(x45538_d5_b0) = 1
    val x45538_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d6_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d6_b0) = false
    bufferDepthOf(x45538_d6_b0) = 1
    val x45538_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d7_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d7_b0) = false
    bufferDepthOf(x45538_d7_b0) = 1
    val x45538_d8_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45538_d8_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45538 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45538_d8_b0) = false
    bufferDepthOf(x45538_d8_b0) = 1
    val x45539_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45539_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:140:20") // x45539 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45539_d0_b0) = true
    bufferDepthOf(x45539_d0_b0) = 1
    val x45540_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45540_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45540 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45540_d0_b0) = false
    bufferDepthOf(x45540_d0_b0) = 1
    val x45540_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45540_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45540 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45540_d1_b0) = true
    bufferDepthOf(x45540_d1_b0) = 1
    val x45541_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45541_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45541 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45541_d0_b0) = false
    bufferDepthOf(x45541_d0_b0) = 1
    val x45541_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45541_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45541 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45541_d1_b0) = true
    bufferDepthOf(x45541_d1_b0) = 1
    val x45542_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45542_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45542 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45542_d0_b0) = false
    bufferDepthOf(x45542_d0_b0) = 1
    val x45542_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45542_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45542 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45542_d1_b0) = true
    bufferDepthOf(x45542_d1_b0) = 1
    val x45543_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45543_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45543 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45543_d0_b0) = false
    bufferDepthOf(x45543_d0_b0) = 1
    val x45543_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45543_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45543 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45543_d1_b0) = true
    bufferDepthOf(x45543_d1_b0) = 1
    val x45544_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45544_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45544 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45544_d0_b0) = false
    bufferDepthOf(x45544_d0_b0) = 1
    val x45544_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45544_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45544 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45544_d1_b0) = false
    bufferDepthOf(x45544_d1_b0) = 1
    val x45544_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45544_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45544 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45544_d2_b0) = false
    bufferDepthOf(x45544_d2_b0) = 1
    val x45544_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45544_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45544 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45544_d3_b0) = false
    bufferDepthOf(x45544_d3_b0) = 1
    val x45545_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d0_b0) = false
    bufferDepthOf(x45545_d0_b0) = 1
    val x45545_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d1_b0) = false
    bufferDepthOf(x45545_d1_b0) = 1
    val x45545_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d2_b0) = false
    bufferDepthOf(x45545_d2_b0) = 1
    val x45545_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d3_b0) = false
    bufferDepthOf(x45545_d3_b0) = 1
    val x45545_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d4_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d4_b0) = false
    bufferDepthOf(x45545_d4_b0) = 1
    val x45545_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d5_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d5_b0) = false
    bufferDepthOf(x45545_d5_b0) = 1
    val x45545_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d6_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d6_b0) = false
    bufferDepthOf(x45545_d6_b0) = 1
    val x45545_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d7_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d7_b0) = false
    bufferDepthOf(x45545_d7_b0) = 1
    val x45545_d8_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45545_d8_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45545 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45545_d8_b0) = false
    bufferDepthOf(x45545_d8_b0) = 1
    val x45546_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45546_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:140:20") // x45546 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45546_d0_b0) = true
    bufferDepthOf(x45546_d0_b0) = 1
    val x45547_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45547_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45547 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45547_d0_b0) = false
    bufferDepthOf(x45547_d0_b0) = 1
    val x45547_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45547_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45547 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45547_d1_b0) = true
    bufferDepthOf(x45547_d1_b0) = 1
    val x45548_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45548_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45548 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45548_d0_b0) = false
    bufferDepthOf(x45548_d0_b0) = 1
    val x45548_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45548_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45548 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45548_d1_b0) = true
    bufferDepthOf(x45548_d1_b0) = 1
    val x45549_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45549_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45549 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45549_d0_b0) = false
    bufferDepthOf(x45549_d0_b0) = 1
    val x45549_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45549_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45549 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45549_d1_b0) = true
    bufferDepthOf(x45549_d1_b0) = 1
    val x45550_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45550_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45550 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45550_d0_b0) = false
    bufferDepthOf(x45550_d0_b0) = 1
    val x45550_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45550_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45550 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45550_d1_b0) = true
    bufferDepthOf(x45550_d1_b0) = 1
    val x45551_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45551_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45551 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45551_d0_b0) = false
    bufferDepthOf(x45551_d0_b0) = 1
    val x45551_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45551_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45551 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45551_d1_b0) = false
    bufferDepthOf(x45551_d1_b0) = 1
    val x45551_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45551_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45551 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45551_d2_b0) = false
    bufferDepthOf(x45551_d2_b0) = 1
    val x45551_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45551_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45551 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45551_d3_b0) = false
    bufferDepthOf(x45551_d3_b0) = 1
    val x45552_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d0_b0) = false
    bufferDepthOf(x45552_d0_b0) = 1
    val x45552_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d1_b0) = false
    bufferDepthOf(x45552_d1_b0) = 1
    val x45552_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d2_b0) = false
    bufferDepthOf(x45552_d2_b0) = 1
    val x45552_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d3_b0) = false
    bufferDepthOf(x45552_d3_b0) = 1
    val x45552_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d4_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d4_b0) = false
    bufferDepthOf(x45552_d4_b0) = 1
    val x45552_d5_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d5_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d5_b0) = false
    bufferDepthOf(x45552_d5_b0) = 1
    val x45552_d6_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d6_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d6_b0) = false
    bufferDepthOf(x45552_d6_b0) = 1
    val x45552_d7_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d7_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d7_b0) = false
    bufferDepthOf(x45552_d7_b0) = 1
    val x45552_d8_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45552_d8_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45552 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45552_d8_b0) = false
    bufferDepthOf(x45552_d8_b0) = 1
    val x45553_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45553_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:140:20") // x45553 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45553_d0_b0) = true
    bufferDepthOf(x45553_d0_b0) = 1
    val x45554_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45554_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45554 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45554_d0_b0) = false
    bufferDepthOf(x45554_d0_b0) = 1
    val x45554_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45554_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45554 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45554_d1_b0) = true
    bufferDepthOf(x45554_d1_b0) = 1
    val x45555_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45555_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45555 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45555_d0_b0) = false
    bufferDepthOf(x45555_d0_b0) = 1
    val x45555_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45555_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45555 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45555_d1_b0) = true
    bufferDepthOf(x45555_d1_b0) = 1
    val x45556_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45556_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45556 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45556_d0_b0) = false
    bufferDepthOf(x45556_d0_b0) = 1
    val x45556_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45556_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45556 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45556_d1_b0) = true
    bufferDepthOf(x45556_d1_b0) = 1
    val x45557_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45557_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45557 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45557_d0_b0) = false
    bufferDepthOf(x45557_d0_b0) = 1
    val x45557_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45557_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45557 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45557_d1_b0) = true
    bufferDepthOf(x45557_d1_b0) = 1
    val x45558_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45558_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45558 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45558_d0_b0) = false
    bufferDepthOf(x45558_d0_b0) = 1
    val x45558_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45558_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45558 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45558_d1_b0) = false
    bufferDepthOf(x45558_d1_b0) = 1
    val x45558_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45558_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45558 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45558_d2_b0) = false
    bufferDepthOf(x45558_d2_b0) = 1
    val x45558_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45558_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45558 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45558_d3_b0) = false
    bufferDepthOf(x45558_d3_b0) = 1
    val x45559_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45559_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45559_d0_b0) = false
    bufferDepthOf(x45559_d0_b0) = 1
    val x45559_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45559_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45559_d1_b0) = false
    bufferDepthOf(x45559_d1_b0) = 1
    val x45559_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45559_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45559_d2_b0) = false
    bufferDepthOf(x45559_d2_b0) = 1
    val x45559_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45559_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45559_d3_b0) = false
    bufferDepthOf(x45559_d3_b0) = 1
    val x45559_d4_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45559_d4_b0").ctrl(x48394).srcCtx("CellsPar.scala:139:20") // x45559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45559_d4_b0) = false
    bufferDepthOf(x45559_d4_b0) = 1
    val x45560_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45560_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:140:20") // x45560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45560_d0_b0) = false
    bufferDepthOf(x45560_d0_b0) = 1
    val x45560_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45560_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:140:20") // x45560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45560_d1_b0) = true
    bufferDepthOf(x45560_d1_b0) = 1
    val x45561_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45561_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45561 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45561_d0_b0) = false
    bufferDepthOf(x45561_d0_b0) = 1
    val x45561_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45561_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:45:targetSRAM") // x45561 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45561_d1_b0) = true
    bufferDepthOf(x45561_d1_b0) = 1
    val x45562_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45562_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45562 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45562_d0_b0) = false
    bufferDepthOf(x45562_d0_b0) = 1
    val x45562_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45562_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:141:79:targetSRAM") // x45562 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45562_d1_b0) = true
    bufferDepthOf(x45562_d1_b0) = 1
    val x45563_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45563_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45563 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45563_d0_b0) = false
    bufferDepthOf(x45563_d0_b0) = 1
    val x45563_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45563_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:44:targetSRAM") // x45563 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45563_d1_b0) = true
    bufferDepthOf(x45563_d1_b0) = 1
    val x45564_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45564_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45564 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45564_d0_b0) = false
    bufferDepthOf(x45564_d0_b0) = 1
    val x45564_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x45564_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:142:78:targetSRAM") // x45564 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x45564_d1_b0) = true
    bufferDepthOf(x45564_d1_b0) = 1
    val x45565_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45565_d0_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45565_d0_b0) = false
    bufferDepthOf(x45565_d0_b0) = 1
    val x45565_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45565_d1_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45565_d1_b0) = false
    bufferDepthOf(x45565_d1_b0) = 1
    val x45565_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45565_d2_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45565_d2_b0) = false
    bufferDepthOf(x45565_d2_b0) = 1
    val x45565_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x45565_d3_b0").ctrl(x48394).srcCtx("CellsPar.scala:143:23:bias") // x45565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x45565_d3_b0) = false
    bufferDepthOf(x45565_d3_b0) = 1
    val x45566 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45566").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45567 = CounterChain(List(x45566)).name("x45567").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45566))
    val x45589 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45567).name("x45589").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x45567,Block(Const(())),List(List(b28799)),List(List(b28800)))
    val b28799 = CounterIter(x45566, Some(0)).name("b28799").ctrl(x45589) // b28799
    val b28800 = Const(true).name("b28800").ctrl(x45589) // b28800
    val b48404 = StreamOut(field="offset").name("b48404").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // x45568 = StreamOutNew(BurstCmdBus)
    isAccum(b48404) = false
    bufferDepthOf(b48404) = 1
    val b48405 = StreamOut(field="size").name("b48405").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // x45568 = StreamOutNew(BurstCmdBus)
    isAccum(b48405) = false
    bufferDepthOf(b48405) = 1
    val x45569 = StreamIn(field="data").name("x45569").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // x45569 = StreamInNew(BurstDataBus())
    isAccum(x45569) = false
    bufferDepthOf(x45569) = 1
    val x45580 = UnitController(style=SeqPipe, level=InnerControl).name("x45580").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b28800),Block(x45579))
    val x45570 = b28799 // FixConvert(b28799,TRUE,_32,_0) (Same Type. No op)
    val x45571 = OpDef(op=FixSla, inputs=List(x45570, Const(11))).name("x45571").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // FixLsh(x45570,Const(11))
    val x45572 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x45573 = OpDef(op=FixAdd, inputs=List(x45571, x45572)).name("x45573").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // FixAdd(x45571,x45572)
    val x45574 = OpDef(op=FixSla, inputs=List(x45573, Const(2))).name("x45574").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // FixLsh(x45573,Const(2))
    val x45575 = x45574 // FixConvert(x45574,TRUE,_64,_0)
    val x45576 = DramAddress(x45150).name("x45576").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x45150)
    val x45578_x45577 = OpDef(op=FixAdd, inputs=List(x45575, x45576)).name("x45578_x45577").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // FixAdd(x45575,x45576)
    // x45578 = SimpleStruct(ArrayBuffer((offset,x45577), (size,Const(8192)), (isLoad,Const(true))))
    val x45579_b48406_b48404 = WriteMem(b48404, x45578_x45577).name("x45579_b48406_b48404").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45568,x45578,b28800)
    val x45579_b48407_b48405 = WriteMem(b48405, Const(8192)).name("x45579_b48407_b48405").ctrl(x45580).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45568,x45578,b28800)
    val x45581 = FringeDenseLoad(dram=List(x45150), cmdStream=List(b48404, b48405), dataStream=List(x45569)).name("x45581").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x45150,x45568,x45569)
    val x45582 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x45582").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x45583 = CounterChain(List(x45582)).name("x45583").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45582))
    val x45588 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45583).name("x45588").ctrl(x45589).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b28800),x45583,Block(Const(())),List(List(b28817)),List(List(b28818)))
    val b28817 = CounterIter(x45582, None).name("b28817").ctrl(x45588) // b28817
    val b28818 = Const(true).name("b28818").ctrl(x45588) // b28818
    val x45584 = OpDef(op=BitAnd, inputs=List(b28818, b28800)).name("x45584").ctrl(x45588).srcCtx("UnrollingBase.scala:28:66") // And(b28818,b28800)
    val x45585_x45585 = ReadMem(x45569).name("x45585_x45585").ctrl(x45588).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x45569,List(x45584))
    val x45586_x45586 = x45585_x45585 // x45586 = VectorApply(x45585,0)
    val x45587 = StoreBanks(List(x45536_d0_b0, x45536_d1_b0, x45536_d2_b0, x45536_d3_b0), List(b28799, b28817), x45586_x45586).name("x45587").ctrl(x45588).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x45536,List(List(b28799, b28817)),List(x45586),List(x45584))
    val x45590 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x45590").ctrl(x48394).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x45591 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45591").ctrl(x48394).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45592 = CounterChain(List(x45591,x45590)).name("x45592").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x45591, x45590))
    val x45597 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45592).name("x45597").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x45592,Block(Const(())),List(List(b28828), List(b28829)),List(List(b28830), List(b28831)))
    val b28828 = CounterIter(x45591, Some(0)).name("b28828").ctrl(x45597) // b28828
    val b28830 = Const(true).name("b28830").ctrl(x45597) // b28830
    val b28829 = CounterIter(x45590, Some(0)).name("b28829").ctrl(x45597) // b28829
    val b28831 = Const(true).name("b28831").ctrl(x45597) // b28831
    val x45596 = UnitController(style=SeqPipe, level=InnerControl).name("x45596").ctrl(x45597).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b28830, b28831),Block(Const(())))
    val x45593 = OpDef(op=BitAnd, inputs=List(b28830, b28831)).name("x45593").ctrl(x45596).srcCtx("UnrollingBase.scala:28:66") // And(b28830,b28831)
    val x45594 = StoreBanks(List(x45531_d0_b0), List(b28828, b28829), Const(0.0)).name("x45594").ctrl(x45596).srcCtx("CellsPar.scala:164:18") // SRAMStore(x45531,ArrayBuffer(Const(1), Const(512)),List(b28828, b28829),Const(0),Const(0),x45593)
    val x45595 = StoreBanks(List(x45530_d0_b0, x45530_d5_b0, x45530_d1_b0, x45530_d6_b0, x45530_d2_b0, x45530_d7_b0, x45530_d3_b0, x45530_d8_b0, x45530_d4_b0), List(b28828, b28829), Const(0.0)).name("x45595").ctrl(x45596).srcCtx("CellsPar.scala:165:18") // SRAMStore(x45530,ArrayBuffer(Const(1), Const(512)),List(b28828, b28829),Const(0),Const(0),x45593)
    val x45598 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45598").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45599 = CounterChain(List(x45598)).name("x45599").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45598))
    val x45621 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45599).name("x45621").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x45599,Block(Const(())),List(List(b28840)),List(List(b28841)))
    val b28840 = CounterIter(x45598, Some(0)).name("b28840").ctrl(x45621) // b28840
    val b28841 = Const(true).name("b28841").ctrl(x45621) // b28841
    val b48408 = StreamOut(field="offset").name("b48408").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // x45600 = StreamOutNew(BurstCmdBus)
    isAccum(b48408) = false
    bufferDepthOf(b48408) = 1
    val b48409 = StreamOut(field="size").name("b48409").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // x45600 = StreamOutNew(BurstCmdBus)
    isAccum(b48409) = false
    bufferDepthOf(b48409) = 1
    val x45601 = StreamIn(field="data").name("x45601").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // x45601 = StreamInNew(BurstDataBus())
    isAccum(x45601) = false
    bufferDepthOf(x45601) = 1
    val x45612 = UnitController(style=SeqPipe, level=InnerControl).name("x45612").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b28841),Block(x45611))
    val x45602 = b28840 // FixConvert(b28840,TRUE,_32,_0) (Same Type. No op)
    val x45603 = OpDef(op=FixSla, inputs=List(x45602, Const(11))).name("x45603").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // FixLsh(x45602,Const(11))
    val x45604 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x45605 = OpDef(op=FixAdd, inputs=List(x45603, x45604)).name("x45605").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // FixAdd(x45603,x45604)
    val x45606 = OpDef(op=FixSla, inputs=List(x45605, Const(2))).name("x45606").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // FixLsh(x45605,Const(2))
    val x45607 = x45606 // FixConvert(x45606,TRUE,_64,_0)
    val x45608 = DramAddress(x45226).name("x45608").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x45226)
    val x45610_x45609 = OpDef(op=FixAdd, inputs=List(x45607, x45608)).name("x45610_x45609").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // FixAdd(x45607,x45608)
    // x45610 = SimpleStruct(ArrayBuffer((offset,x45609), (size,Const(8192)), (isLoad,Const(true))))
    val x45611_b48410_b48408 = WriteMem(b48408, x45610_x45609).name("x45611_b48410_b48408").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45600,x45610,b28841)
    val x45611_b48411_b48409 = WriteMem(b48409, Const(8192)).name("x45611_b48411_b48409").ctrl(x45612).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45600,x45610,b28841)
    val x45613 = FringeDenseLoad(dram=List(x45226), cmdStream=List(b48408, b48409), dataStream=List(x45601)).name("x45613").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x45226,x45600,x45601)
    val x45614 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x45614").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x45615 = CounterChain(List(x45614)).name("x45615").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45614))
    val x45620 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45615).name("x45620").ctrl(x45621).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b28841),x45615,Block(Const(())),List(List(b28858)),List(List(b28859)))
    val b28858 = CounterIter(x45614, None).name("b28858").ctrl(x45620) // b28858
    val b28859 = Const(true).name("b28859").ctrl(x45620) // b28859
    val x45616 = OpDef(op=BitAnd, inputs=List(b28859, b28841)).name("x45616").ctrl(x45620).srcCtx("UnrollingBase.scala:28:66") // And(b28859,b28841)
    val x45617_x45617 = ReadMem(x45601).name("x45617_x45617").ctrl(x45620).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x45601,List(x45616))
    val x45618_x45618 = x45617_x45617 // x45618 = VectorApply(x45617,0)
    val x45619 = StoreBanks(List(x45544_d0_b0, x45544_d1_b0, x45544_d2_b0, x45544_d3_b0), List(b28840, b28858), x45618_x45618).name("x45619").ctrl(x45620).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x45544,List(List(b28840, b28858)),List(x45618),List(x45616))
    val x45622 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x45622").ctrl(x48394).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x45623 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45623").ctrl(x48394).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45624 = CounterChain(List(x45623,x45622)).name("x45624").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x45623, x45622))
    val x45629 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45624).name("x45629").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x45624,Block(Const(())),List(List(b28869), List(b28870)),List(List(b28871), List(b28872)))
    val b28869 = CounterIter(x45623, Some(0)).name("b28869").ctrl(x45629) // b28869
    val b28871 = Const(true).name("b28871").ctrl(x45629) // b28871
    val b28870 = CounterIter(x45622, Some(0)).name("b28870").ctrl(x45629) // b28870
    val b28872 = Const(true).name("b28872").ctrl(x45629) // b28872
    val x45628 = UnitController(style=SeqPipe, level=InnerControl).name("x45628").ctrl(x45629).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b28871, b28872),Block(Const(())))
    val x45625 = OpDef(op=BitAnd, inputs=List(b28871, b28872)).name("x45625").ctrl(x45628).srcCtx("UnrollingBase.scala:28:66") // And(b28871,b28872)
    val x45626 = StoreBanks(List(x45539_d0_b0), List(b28869, b28870), Const(0.0)).name("x45626").ctrl(x45628).srcCtx("CellsPar.scala:164:18") // SRAMStore(x45539,ArrayBuffer(Const(1), Const(512)),List(b28869, b28870),Const(0),Const(0),x45625)
    val x45627 = StoreBanks(List(x45538_d0_b0, x45538_d5_b0, x45538_d1_b0, x45538_d6_b0, x45538_d2_b0, x45538_d7_b0, x45538_d3_b0, x45538_d8_b0, x45538_d4_b0), List(b28869, b28870), Const(0.0)).name("x45627").ctrl(x45628).srcCtx("CellsPar.scala:165:18") // SRAMStore(x45538,ArrayBuffer(Const(1), Const(512)),List(b28869, b28870),Const(0),Const(0),x45625)
    val x45630 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45630").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45631 = CounterChain(List(x45630)).name("x45631").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45630))
    val x45653 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45631).name("x45653").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x45631,Block(Const(())),List(List(b28881)),List(List(b28882)))
    val b28881 = CounterIter(x45630, Some(0)).name("b28881").ctrl(x45653) // b28881
    val b28882 = Const(true).name("b28882").ctrl(x45653) // b28882
    val b48412 = StreamOut(field="offset").name("b48412").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // x45632 = StreamOutNew(BurstCmdBus)
    isAccum(b48412) = false
    bufferDepthOf(b48412) = 1
    val b48413 = StreamOut(field="size").name("b48413").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // x45632 = StreamOutNew(BurstCmdBus)
    isAccum(b48413) = false
    bufferDepthOf(b48413) = 1
    val x45633 = StreamIn(field="data").name("x45633").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // x45633 = StreamInNew(BurstDataBus())
    isAccum(x45633) = false
    bufferDepthOf(x45633) = 1
    val x45644 = UnitController(style=SeqPipe, level=InnerControl).name("x45644").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b28882),Block(x45643))
    val x45634 = b28881 // FixConvert(b28881,TRUE,_32,_0) (Same Type. No op)
    val x45635 = OpDef(op=FixSla, inputs=List(x45634, Const(11))).name("x45635").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // FixLsh(x45634,Const(11))
    val x45636 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x45637 = OpDef(op=FixAdd, inputs=List(x45635, x45636)).name("x45637").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // FixAdd(x45635,x45636)
    val x45638 = OpDef(op=FixSla, inputs=List(x45637, Const(2))).name("x45638").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // FixLsh(x45637,Const(2))
    val x45639 = x45638 // FixConvert(x45638,TRUE,_64,_0)
    val x45640 = DramAddress(x45302).name("x45640").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x45302)
    val x45642_x45641 = OpDef(op=FixAdd, inputs=List(x45639, x45640)).name("x45642_x45641").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // FixAdd(x45639,x45640)
    // x45642 = SimpleStruct(ArrayBuffer((offset,x45641), (size,Const(8192)), (isLoad,Const(true))))
    val x45643_b48414_b48412 = WriteMem(b48412, x45642_x45641).name("x45643_b48414_b48412").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45632,x45642,b28882)
    val x45643_b48415_b48413 = WriteMem(b48413, Const(8192)).name("x45643_b48415_b48413").ctrl(x45644).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45632,x45642,b28882)
    val x45645 = FringeDenseLoad(dram=List(x45302), cmdStream=List(b48412, b48413), dataStream=List(x45633)).name("x45645").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x45302,x45632,x45633)
    val x45646 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x45646").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x45647 = CounterChain(List(x45646)).name("x45647").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45646))
    val x45652 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45647).name("x45652").ctrl(x45653).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b28882),x45647,Block(Const(())),List(List(b28899)),List(List(b28900)))
    val b28899 = CounterIter(x45646, None).name("b28899").ctrl(x45652) // b28899
    val b28900 = Const(true).name("b28900").ctrl(x45652) // b28900
    val x45648 = OpDef(op=BitAnd, inputs=List(b28900, b28882)).name("x45648").ctrl(x45652).srcCtx("UnrollingBase.scala:28:66") // And(b28900,b28882)
    val x45649_x45649 = ReadMem(x45633).name("x45649_x45649").ctrl(x45652).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x45633,List(x45648))
    val x45650_x45650 = x45649_x45649 // x45650 = VectorApply(x45649,0)
    val x45651 = StoreBanks(List(x45551_d0_b0, x45551_d1_b0, x45551_d2_b0, x45551_d3_b0), List(b28881, b28899), x45650_x45650).name("x45651").ctrl(x45652).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x45551,List(List(b28881, b28899)),List(x45650),List(x45648))
    val x45654 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x45654").ctrl(x48394).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x45655 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45655").ctrl(x48394).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45656 = CounterChain(List(x45655,x45654)).name("x45656").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x45655, x45654))
    val x45661 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45656).name("x45661").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x45656,Block(Const(())),List(List(b28910), List(b28911)),List(List(b28912), List(b28913)))
    val b28910 = CounterIter(x45655, Some(0)).name("b28910").ctrl(x45661) // b28910
    val b28912 = Const(true).name("b28912").ctrl(x45661) // b28912
    val b28911 = CounterIter(x45654, Some(0)).name("b28911").ctrl(x45661) // b28911
    val b28913 = Const(true).name("b28913").ctrl(x45661) // b28913
    val x45660 = UnitController(style=SeqPipe, level=InnerControl).name("x45660").ctrl(x45661).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b28912, b28913),Block(Const(())))
    val x45657 = OpDef(op=BitAnd, inputs=List(b28912, b28913)).name("x45657").ctrl(x45660).srcCtx("UnrollingBase.scala:28:66") // And(b28912,b28913)
    val x45658 = StoreBanks(List(x45546_d0_b0), List(b28910, b28911), Const(0.0)).name("x45658").ctrl(x45660).srcCtx("CellsPar.scala:164:18") // SRAMStore(x45546,ArrayBuffer(Const(1), Const(512)),List(b28910, b28911),Const(0),Const(0),x45657)
    val x45659 = StoreBanks(List(x45545_d0_b0, x45545_d5_b0, x45545_d1_b0, x45545_d6_b0, x45545_d2_b0, x45545_d7_b0, x45545_d3_b0, x45545_d8_b0, x45545_d4_b0), List(b28910, b28911), Const(0.0)).name("x45659").ctrl(x45660).srcCtx("CellsPar.scala:165:18") // SRAMStore(x45545,ArrayBuffer(Const(1), Const(512)),List(b28910, b28911),Const(0),Const(0),x45657)
    val x45662 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45662").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45663 = CounterChain(List(x45662)).name("x45663").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45662))
    val x45685 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45663).name("x45685").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x45663,Block(Const(())),List(List(b28922)),List(List(b28923)))
    val b28922 = CounterIter(x45662, Some(0)).name("b28922").ctrl(x45685) // b28922
    val b28923 = Const(true).name("b28923").ctrl(x45685) // b28923
    val b48416 = StreamOut(field="offset").name("b48416").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // x45664 = StreamOutNew(BurstCmdBus)
    isAccum(b48416) = false
    bufferDepthOf(b48416) = 1
    val b48417 = StreamOut(field="size").name("b48417").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // x45664 = StreamOutNew(BurstCmdBus)
    isAccum(b48417) = false
    bufferDepthOf(b48417) = 1
    val x45665 = StreamIn(field="data").name("x45665").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // x45665 = StreamInNew(BurstDataBus())
    isAccum(x45665) = false
    bufferDepthOf(x45665) = 1
    val x45676 = UnitController(style=SeqPipe, level=InnerControl).name("x45676").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b28923),Block(x45675))
    val x45666 = b28922 // FixConvert(b28922,TRUE,_32,_0) (Same Type. No op)
    val x45667 = OpDef(op=FixSla, inputs=List(x45666, Const(11))).name("x45667").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // FixLsh(x45666,Const(11))
    val x45668 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x45669 = OpDef(op=FixAdd, inputs=List(x45667, x45668)).name("x45669").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // FixAdd(x45667,x45668)
    val x45670 = OpDef(op=FixSla, inputs=List(x45669, Const(2))).name("x45670").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // FixLsh(x45669,Const(2))
    val x45671 = x45670 // FixConvert(x45670,TRUE,_64,_0)
    val x45672 = DramAddress(x45378).name("x45672").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x45378)
    val x45674_x45673 = OpDef(op=FixAdd, inputs=List(x45671, x45672)).name("x45674_x45673").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // FixAdd(x45671,x45672)
    // x45674 = SimpleStruct(ArrayBuffer((offset,x45673), (size,Const(8192)), (isLoad,Const(true))))
    val x45675_b48418_b48416 = WriteMem(b48416, x45674_x45673).name("x45675_b48418_b48416").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45664,x45674,b28923)
    val x45675_b48419_b48417 = WriteMem(b48417, Const(8192)).name("x45675_b48419_b48417").ctrl(x45676).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45664,x45674,b28923)
    val x45677 = FringeDenseLoad(dram=List(x45378), cmdStream=List(b48416, b48417), dataStream=List(x45665)).name("x45677").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x45378,x45664,x45665)
    val x45678 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x45678").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x45679 = CounterChain(List(x45678)).name("x45679").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45678))
    val x45684 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45679).name("x45684").ctrl(x45685).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b28923),x45679,Block(Const(())),List(List(b28940)),List(List(b28941)))
    val b28940 = CounterIter(x45678, None).name("b28940").ctrl(x45684) // b28940
    val b28941 = Const(true).name("b28941").ctrl(x45684) // b28941
    val x45680 = OpDef(op=BitAnd, inputs=List(b28941, b28923)).name("x45680").ctrl(x45684).srcCtx("UnrollingBase.scala:28:66") // And(b28941,b28923)
    val x45681_x45681 = ReadMem(x45665).name("x45681_x45681").ctrl(x45684).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x45665,List(x45680))
    val x45682_x45682 = x45681_x45681 // x45682 = VectorApply(x45681,0)
    val x45683 = StoreBanks(List(x45558_d0_b0, x45558_d1_b0, x45558_d2_b0, x45558_d3_b0), List(b28922, b28940), x45682_x45682).name("x45683").ctrl(x45684).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x45558,List(List(b28922, b28940)),List(x45682),List(x45680))
    val x45686 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x45686").ctrl(x48394).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x45687 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45687").ctrl(x48394).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45688 = CounterChain(List(x45687,x45686)).name("x45688").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x45687, x45686))
    val x45693 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45688).name("x45693").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x45688,Block(Const(())),List(List(b28951), List(b28952)),List(List(b28953), List(b28954)))
    val b28951 = CounterIter(x45687, Some(0)).name("b28951").ctrl(x45693) // b28951
    val b28953 = Const(true).name("b28953").ctrl(x45693) // b28953
    val b28952 = CounterIter(x45686, Some(0)).name("b28952").ctrl(x45693) // b28952
    val b28954 = Const(true).name("b28954").ctrl(x45693) // b28954
    val x45692 = UnitController(style=SeqPipe, level=InnerControl).name("x45692").ctrl(x45693).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b28953, b28954),Block(Const(())))
    val x45689 = OpDef(op=BitAnd, inputs=List(b28953, b28954)).name("x45689").ctrl(x45692).srcCtx("UnrollingBase.scala:28:66") // And(b28953,b28954)
    val x45690 = StoreBanks(List(x45553_d0_b0), List(b28951, b28952), Const(0.0)).name("x45690").ctrl(x45692).srcCtx("CellsPar.scala:164:18") // SRAMStore(x45553,ArrayBuffer(Const(1), Const(512)),List(b28951, b28952),Const(0),Const(0),x45689)
    val x45691 = StoreBanks(List(x45552_d0_b0, x45552_d5_b0, x45552_d1_b0, x45552_d6_b0, x45552_d2_b0, x45552_d7_b0, x45552_d3_b0, x45552_d8_b0, x45552_d4_b0), List(b28951, b28952), Const(0.0)).name("x45691").ctrl(x45692).srcCtx("CellsPar.scala:165:18") // SRAMStore(x45552,ArrayBuffer(Const(1), Const(512)),List(b28951, b28952),Const(0),Const(0),x45689)
    val x45694 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45694").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45695 = CounterChain(List(x45694)).name("x45695").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45694))
    val x45717 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45695).name("x45717").ctrl(x48394).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(Const(true)),x45695,Block(Const(())),List(List(b28963)),List(List(b28964)))
    val b28963 = CounterIter(x45694, Some(0)).name("b28963").ctrl(x45717) // b28963
    val b28964 = Const(true).name("b28964").ctrl(x45717) // b28964
    val b48420 = StreamOut(field="offset").name("b48420").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // x45696 = StreamOutNew(BurstCmdBus)
    isAccum(b48420) = false
    bufferDepthOf(b48420) = 1
    val b48421 = StreamOut(field="size").name("b48421").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // x45696 = StreamOutNew(BurstCmdBus)
    isAccum(b48421) = false
    bufferDepthOf(b48421) = 1
    val x45697 = StreamIn(field="data").name("x45697").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // x45697 = StreamInNew(BurstDataBus())
    isAccum(x45697) = false
    bufferDepthOf(x45697) = 1
    val x45708 = UnitController(style=SeqPipe, level=InnerControl).name("x45708").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // UnitPipe(List(b28964),Block(x45707))
    val x45698 = b28963 // FixConvert(b28963,TRUE,_32,_0) (Same Type. No op)
    val x45699 = OpDef(op=FixSla, inputs=List(x45698, Const(11))).name("x45699").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // FixLsh(x45698,Const(11))
    val x45700 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x45701 = OpDef(op=FixAdd, inputs=List(x45699, x45700)).name("x45701").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // FixAdd(x45699,x45700)
    val x45702 = OpDef(op=FixSla, inputs=List(x45701, Const(2))).name("x45702").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // FixLsh(x45701,Const(2))
    val x45703 = x45702 // FixConvert(x45702,TRUE,_64,_0)
    val x45704 = DramAddress(x45454).name("x45704").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // GetDRAMAddress(x45454)
    val x45706_x45705 = OpDef(op=FixAdd, inputs=List(x45703, x45704)).name("x45706_x45705").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // FixAdd(x45703,x45704)
    // x45706 = SimpleStruct(ArrayBuffer((offset,x45705), (size,Const(8192)), (isLoad,Const(true))))
    val x45707_b48422_b48420 = WriteMem(b48420, x45706_x45705).name("x45707_b48422_b48420").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45696,x45706,b28964)
    val x45707_b48423_b48421 = WriteMem(b48421, Const(8192)).name("x45707_b48423_b48421").ctrl(x45708).srcCtx("CellsPar.scala:161:12") // StreamWrite(x45696,x45706,b28964)
    val x45709 = FringeDenseLoad(dram=List(x45454), cmdStream=List(b48420, b48421), dataStream=List(x45697)).name("x45709").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // FringeDenseLoad(x45454,x45696,x45697)
    val x45710 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x45710").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x45711 = CounterChain(List(x45710)).name("x45711").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // CounterChainNew(List(x45710))
    val x45716 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45711).name("x45716").ctrl(x45717).srcCtx("CellsPar.scala:161:12") // UnrolledForeach(List(b28964),x45711,Block(Const(())),List(List(b28981)),List(List(b28982)))
    val b28981 = CounterIter(x45710, None).name("b28981").ctrl(x45716) // b28981
    val b28982 = Const(true).name("b28982").ctrl(x45716) // b28982
    val x45712 = OpDef(op=BitAnd, inputs=List(b28982, b28964)).name("x45712").ctrl(x45716).srcCtx("UnrollingBase.scala:28:66") // And(b28982,b28964)
    val x45713_x45713 = ReadMem(x45697).name("x45713_x45713").ctrl(x45716).srcCtx("CellsPar.scala:161:12") // ParStreamRead(x45697,List(x45712))
    val x45714_x45714 = x45713_x45713 // x45714 = VectorApply(x45713,0)
    val x45715 = StoreBanks(List(x45565_d0_b0, x45565_d1_b0, x45565_d2_b0, x45565_d3_b0), List(b28963, b28981), x45714_x45714).name("x45715").ctrl(x45716).srcCtx("CellsPar.scala:161:12") // ParSRAMStore(x45565,List(List(b28963, b28981)),List(x45714),List(x45712))
    val x45718 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x45718").ctrl(x48394).srcCtx("CellsPar.scala:162:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x45719 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45719").ctrl(x48394).srcCtx("CellsPar.scala:162:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45720 = CounterChain(List(x45719,x45718)).name("x45720").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // CounterChainNew(List(x45719, x45718))
    val x45725 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45720).name("x45725").ctrl(x48394).srcCtx("CellsPar.scala:162:63") // UnrolledForeach(List(Const(true)),x45720,Block(Const(())),List(List(b28992), List(b28993)),List(List(b28994), List(b28995)))
    val b28992 = CounterIter(x45719, Some(0)).name("b28992").ctrl(x45725) // b28992
    val b28994 = Const(true).name("b28994").ctrl(x45725) // b28994
    val b28993 = CounterIter(x45718, Some(0)).name("b28993").ctrl(x45725) // b28993
    val b28995 = Const(true).name("b28995").ctrl(x45725) // b28995
    val x45724 = UnitController(style=SeqPipe, level=InnerControl).name("x45724").ctrl(x45725).srcCtx("CellsPar.scala:163:18") // UnitPipe(List(b28994, b28995),Block(Const(())))
    val x45721 = OpDef(op=BitAnd, inputs=List(b28994, b28995)).name("x45721").ctrl(x45724).srcCtx("UnrollingBase.scala:28:66") // And(b28994,b28995)
    val x45722 = StoreBanks(List(x45560_d0_b0, x45560_d1_b0), List(b28992, b28993), Const(0.0)).name("x45722").ctrl(x45724).srcCtx("CellsPar.scala:164:18") // SRAMStore(x45560,ArrayBuffer(Const(1), Const(512)),List(b28992, b28993),Const(0),Const(0),x45721)
    val x45723 = StoreBanks(List(x45559_d0_b0, x45559_d1_b0, x45559_d2_b0, x45559_d3_b0, x45559_d4_b0), List(b28992, b28993), Const(0.0)).name("x45723").ctrl(x45724).srcCtx("CellsPar.scala:165:18") // SRAMStore(x45559,ArrayBuffer(Const(1), Const(512)),List(b28992, b28993),Const(0),Const(0),x45721)
    val x45726 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x45726").ctrl(x48394).srcCtx("NMTDSE.scala:441:34") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x45727 = CounterChain(List(x45726)).name("x45727").ctrl(x48394).srcCtx("NMTDSE.scala:441:40") // CounterChainNew(List(x45726))
    val x46824 = LoopController(style=SeqPipe, level=OuterControl, cchain=x45727).name("x46824").ctrl(x48394).srcCtx("NMTDSE.scala:441:40") // UnrolledForeach(List(Const(true)),x45727,Block(Const(())),List(List(b29005)),List(List(b29006)))
    val b29005 = CounterIter(x45726, Some(0)).name("b29005").ctrl(x46824) // b29005
    val b29006 = Const(true).name("b29006").ctrl(x46824) // b29006
    val x46270 = UnitController(style=SeqPipe, level=OuterControl).name("x46270").ctrl(x46824).srcCtx("NMTDSE.scala:443:16") // UnitPipe(List(b29006),Block(Const(())))
    val x45729 = UnitController(style=SeqPipe, level=InnerControl).name("x45729").ctrl(x46270).srcCtx("NMTDSE.scala:443:16") // UnitPipe(List(b29006),Block(Const(())))
    val x45728 = OpDef(op=FixAdd, inputs=List(b29005, Const(1))).name("x45728").ctrl(x45729).srcCtx("NMTDSE.scala:444:32") // FixAdd(b29005,Const(1))
    val x45730 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45730").ctrl(x46270).srcCtx("NMTDSE.scala:444:22") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45731 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45731").ctrl(x46270).srcCtx("NMTDSE.scala:444:22") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45732 = CounterChain(List(x45730,x45731)).name("x45732").ctrl(x46270).srcCtx("NMTDSE.scala:444:22") // CounterChainNew(List(x45730, x45731))
    val x45762 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45732).name("x45762").ctrl(x46270).srcCtx("NMTDSE.scala:444:22") // UnrolledForeach(List(b29006),x45732,Block(Const(())),List(List(b29012), List(b29013)),List(List(b29014), List(b29015)))
    val b29012 = CounterIter(x45730, Some(0)).name("b29012").ctrl(x45762) // b29012
    val b29014 = Const(true).name("b29014").ctrl(x45762) // b29014
    val b29013 = CounterIter(x45731, Some(0)).name("b29013").ctrl(x45762) // b29013
    val b29015 = Const(true).name("b29015").ctrl(x45762) // b29015
    val b48424 = StreamOut(field="offset").name("b48424").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // x45733 = StreamOutNew(BurstCmdBus)
    isAccum(b48424) = false
    bufferDepthOf(b48424) = 1
    val b48425 = StreamOut(field="size").name("b48425").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // x45733 = StreamOutNew(BurstCmdBus)
    isAccum(b48425) = false
    bufferDepthOf(b48425) = 1
    val x45734 = StreamIn(field="data").name("x45734").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // x45734 = StreamInNew(BurstDataBus())
    isAccum(x45734) = false
    bufferDepthOf(x45734) = 1
    val x45751 = UnitController(style=SeqPipe, level=InnerControl).name("x45751").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // UnitPipe(List(b29014, b29015, b29006),Block(x45750))
    val x45735 = OpDef(op=FixAdd, inputs=List(b29005, b29013)).name("x45735").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixAdd(b29005,b29013)
    val x45736 = b29012 // FixConvert(b29012,TRUE,_32,_0) (Same Type. No op)
    val x45737 = OpDef(op=FixSla, inputs=List(x45736, Const(11))).name("x45737").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixLsh(x45736,Const(11))
    val x45738 = x45735 // FixConvert(x45735,TRUE,_32,_0) (Same Type. No op)
    val x45739 = OpDef(op=FixSla, inputs=List(x45738, Const(9))).name("x45739").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixLsh(x45738,Const(9))
    val x45740 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x45741 = OpDef(op=FixAdd, inputs=List(x45737, x45739)).name("x45741").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixAdd(x45737,x45739)
    val x45742 = OpDef(op=FixAdd, inputs=List(x45741, x45740)).name("x45742").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixAdd(x45741,x45740)
    val x45743 = OpDef(op=FixSla, inputs=List(x45742, Const(2))).name("x45743").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixLsh(x45742,Const(2))
    val x45744 = x45743 // FixConvert(x45743,TRUE,_64,_0)
    val x45745 = DramAddress(x45139).name("x45745").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // GetDRAMAddress(x45139)
    val x45747_x45746 = OpDef(op=FixAdd, inputs=List(x45744, x45745)).name("x45747_x45746").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // FixAdd(x45744,x45745)
    // x45747 = SimpleStruct(ArrayBuffer((offset,x45746), (size,Const(2048)), (isLoad,Const(true))))
    val x45748 = OpDef(op=BitAnd, inputs=List(b29014, b29015)).name("x45748").ctrl(x45751).srcCtx("UnrollingBase.scala:28:66") // And(b29014,b29015)
    val x45749 = OpDef(op=BitAnd, inputs=List(x45748, b29006)).name("x45749").ctrl(x45751).srcCtx("UnrollingBase.scala:28:66") // And(x45748,b29006)
    val x45750_b48426_b48424 = WriteMem(b48424, x45747_x45746).name("x45750_b48426_b48424").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // StreamWrite(x45733,x45747,x45749)
    val x45750_b48427_b48425 = WriteMem(b48425, Const(2048)).name("x45750_b48427_b48425").ctrl(x45751).srcCtx("NMTDSE.scala:444:22") // StreamWrite(x45733,x45747,x45749)
    val x45752 = FringeDenseLoad(dram=List(x45139), cmdStream=List(b48424, b48425), dataStream=List(x45734)).name("x45752").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // FringeDenseLoad(x45139,x45733,x45734)
    val x45753 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x45753").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x45754 = CounterChain(List(x45753)).name("x45754").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // CounterChainNew(List(x45753))
    val x45761 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45754).name("x45761").ctrl(x45762).srcCtx("NMTDSE.scala:444:22") // UnrolledForeach(List(b29014, b29015, b29006),x45754,Block(Const(())),List(List(b29038)),List(List(b29039)))
    val b29038 = CounterIter(x45753, None).name("b29038").ctrl(x45761) // b29038
    val b29039 = Const(true).name("b29039").ctrl(x45761) // b29039
    val x45755 = OpDef(op=BitAnd, inputs=List(b29039, b29014)).name("x45755").ctrl(x45761).srcCtx("UnrollingBase.scala:28:66") // And(b29039,b29014)
    val x45756 = OpDef(op=BitAnd, inputs=List(b29015, b29006)).name("x45756").ctrl(x45761).srcCtx("UnrollingBase.scala:28:66") // And(b29015,b29006)
    val x45757 = OpDef(op=BitAnd, inputs=List(x45755, x45756)).name("x45757").ctrl(x45761).srcCtx("UnrollingBase.scala:28:66") // And(x45755,x45756)
    val x45758_x45758 = ReadMem(x45734).name("x45758_x45758").ctrl(x45761).srcCtx("NMTDSE.scala:444:22") // ParStreamRead(x45734,List(x45757))
    val x45759_x45759 = x45758_x45758 // x45759 = VectorApply(x45758,0)
    val x45760 = StoreBanks(List(x45529_d0_b0, x45529_d1_b0, x45529_d2_b0, x45529_d3_b0), List(b29012, b29038), x45759_x45759).name("x45760").ctrl(x45761).srcCtx("NMTDSE.scala:444:22") // ParSRAMStore(x45529,List(List(b29012, b29038)),List(x45759),List(x45757))
    val x45763 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x45763").ctrl(x46270).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x45764 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x45764").ctrl(x46270).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x45765 = CounterChain(List(x45764,x45763)).name("x45765").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x45764, x45763))
    val x45870 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45765).name("x45870").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x45765,Block(Const(())),List(List(b29051), List(b29052)),List(List(b29053), List(b29054)))
    val b29051 = CounterIter(x45764, Some(0)).name("b29051").ctrl(x45870) // b29051
    val b29053 = Const(true).name("b29053").ctrl(x45870) // b29053
    val b29052 = CounterIter(x45763, Some(0)).name("b29052").ctrl(x45870) // b29052
    val b29054 = Const(true).name("b29054").ctrl(x45870) // b29054
    val x45766_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x45766_d0_b0").ctrl(x45870).srcCtx("CellsPar.scala:191:33:tileKernel") // x45766 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x45766_d0_b0) = false
    bufferDepthOf(x45766_d0_b0) = 2
    val x45769 = UnitController(style=SeqPipe, level=InnerControl).name("x45769").ctrl(x45870).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29053, b29054, b29006),Block(Const(())))
    val x45767 = OpDef(op=FixAdd, inputs=List(b29051, Const(16))).name("x45767").ctrl(x45769).srcCtx("CellsPar.scala:192:36") // FixAdd(b29051,Const(16))
    val x45768 = OpDef(op=FixAdd, inputs=List(b29052, Const(16))).name("x45768").ctrl(x45769).srcCtx("CellsPar.scala:192:45") // FixAdd(b29052,Const(16))
    val x45770 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45770").ctrl(x45870).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45771 = CounterChain(List(x45770)).name("x45771").ctrl(x45870).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x45770))
    val x45800 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45771).name("x45800").ctrl(x45870).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29053, b29054, b29006),x45771,Block(Const(())),List(List(b29061)),List(List(b29062)))
    val b29061 = CounterIter(x45770, Some(0)).name("b29061").ctrl(x45800) // b29061
    val b29062 = Const(true).name("b29062").ctrl(x45800) // b29062
    val b48428 = StreamOut(field="offset").name("b48428").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // x45772 = StreamOutNew(BurstCmdBus)
    isAccum(b48428) = false
    bufferDepthOf(b48428) = 1
    val b48429 = StreamOut(field="size").name("b48429").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // x45772 = StreamOutNew(BurstCmdBus)
    isAccum(b48429) = false
    bufferDepthOf(b48429) = 1
    val x45773 = StreamIn(field="data").name("x45773").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // x45773 = StreamInNew(BurstDataBus())
    isAccum(x45773) = false
    bufferDepthOf(x45773) = 1
    val x45788 = UnitController(style=SeqPipe, level=InnerControl).name("x45788").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29062, b29053, b29054, b29006),Block(x45787))
    val x45774 = OpDef(op=FixAdd, inputs=List(b29051, b29061)).name("x45774").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // FixAdd(b29051,b29061)
    val x45775 = x45774 // FixConvert(x45774,TRUE,_32,_0) (Same Type. No op)
    val x45776 = OpDef(op=FixSla, inputs=List(x45775, Const(9))).name("x45776").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // FixLsh(x45775,Const(9))
    val x45777 = b29052 // FixConvert(b29052,TRUE,_32,_0) (Same Type. No op)
    val x45778 = OpDef(op=FixAdd, inputs=List(x45776, x45777)).name("x45778").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // FixAdd(x45776,x45777)
    val x45779 = OpDef(op=FixSla, inputs=List(x45778, Const(2))).name("x45779").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // FixLsh(x45778,Const(2))
    val x45780 = x45779 // FixConvert(x45779,TRUE,_64,_0)
    val x45781 = DramAddress(x45177).name("x45781").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45177)
    val x45783_x45782 = OpDef(op=FixAdd, inputs=List(x45780, x45781)).name("x45783_x45782").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // FixAdd(x45780,x45781)
    // x45783 = SimpleStruct(ArrayBuffer((offset,x45782), (size,Const(64)), (isLoad,Const(true))))
    val x45784 = OpDef(op=BitAnd, inputs=List(b29062, b29053)).name("x45784").ctrl(x45788).srcCtx("UnrollingBase.scala:28:66") // And(b29062,b29053)
    val x45785 = OpDef(op=BitAnd, inputs=List(b29054, b29006)).name("x45785").ctrl(x45788).srcCtx("UnrollingBase.scala:28:66") // And(b29054,b29006)
    val x45786 = OpDef(op=BitAnd, inputs=List(x45784, x45785)).name("x45786").ctrl(x45788).srcCtx("UnrollingBase.scala:28:66") // And(x45784,x45785)
    val x45787_b48430_b48428 = WriteMem(b48428, x45783_x45782).name("x45787_b48430_b48428").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // StreamWrite(x45772,x45783,x45786)
    val x45787_b48431_b48429 = WriteMem(b48429, Const(64)).name("x45787_b48431_b48429").ctrl(x45788).srcCtx("CellsPar.scala:192:20") // StreamWrite(x45772,x45783,x45786)
    val x45789 = FringeDenseLoad(dram=List(x45177), cmdStream=List(b48428, b48429), dataStream=List(x45773)).name("x45789").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45177,x45772,x45773)
    val x45790 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45790").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45791 = CounterChain(List(x45790)).name("x45791").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x45790))
    val x45799 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45791).name("x45799").ctrl(x45800).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29062, b29053, b29054, b29006),x45791,Block(Const(())),List(List(b29083)),List(List(b29084)))
    val b29083 = CounterIter(x45790, None).name("b29083").ctrl(x45799) // b29083
    val b29084 = Const(true).name("b29084").ctrl(x45799) // b29084
    val x45792 = OpDef(op=BitAnd, inputs=List(b29084, b29062)).name("x45792").ctrl(x45799).srcCtx("UnrollingBase.scala:28:66") // And(b29084,b29062)
    val x45793 = OpDef(op=BitAnd, inputs=List(b29053, b29054)).name("x45793").ctrl(x45799).srcCtx("UnrollingBase.scala:28:66") // And(b29053,b29054)
    val x45794 = OpDef(op=BitAnd, inputs=List(x45792, x45793)).name("x45794").ctrl(x45799).srcCtx("UnrollingBase.scala:28:66") // And(x45792,x45793)
    val x45795 = OpDef(op=BitAnd, inputs=List(x45794, b29006)).name("x45795").ctrl(x45799).srcCtx("UnrollingBase.scala:28:66") // And(x45794,b29006)
    val x45796_x45796 = ReadMem(x45773).name("x45796_x45796").ctrl(x45799).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x45773,List(x45795))
    val x45797_x45797 = x45796_x45796 // x45797 = VectorApply(x45796,0)
    val x45798 = StoreBanks(List(x45766_d0_b0), List(b29061, b29083), x45797_x45797).name("x45798").ctrl(x45799).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x45766,List(List(b29061, b29083)),List(x45797),List(x45795))
    val x45801 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45801").ctrl(x45870).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45802 = CounterChain(List(x45801)).name("x45802").ctrl(x45870).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x45801))
    val x45869 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45802).name("x45869").ctrl(x45870).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29053, b29054, b29006),x45802,Block(Const(())),List(List(b29096)),List(List(b29097)))
    val b29096 = CounterIter(x45801, Some(0)).name("b29096").ctrl(x45869) // b29096
    val b29097 = Const(true).name("b29097").ctrl(x45869) // b29097
    val x45803 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45803").ctrl(x45869).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45804 = CounterChain(List(x45803)).name("x45804").ctrl(x45869).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x45803))
    val x45868 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45804).name("x45868").ctrl(x45869).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29097, b29053, b29054, b29006),x45804,Block(Const(())),List(List(b29100)),List(List(b29101)))
    val b29100 = CounterIter(x45803, Some(0)).name("b29100").ctrl(x45868) // b29100
    val b29101 = Const(true).name("b29101").ctrl(x45868) // b29101
    val x45805_d0 = Reg(init=Some(0.0)).name("x45805_d0").ctrl(x45868).srcCtx("CellsPar.scala:195:34:prod") // x45805 = RegNew(Const(0))
    isAccum(x45805_d0) = false
    bufferDepthOf(x45805_d0) = 2
    val x45805_d1 = Reg(init=Some(0.0)).name("x45805_d1").ctrl(x45868).srcCtx("CellsPar.scala:195:34:prod") // x45805 = RegNew(Const(0))
    isAccum(x45805_d1) = true
    bufferDepthOf(x45805_d1) = 1
    val x45806 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45806").ctrl(x45868).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45807 = CounterChain(List(x45806)).name("x45807").ctrl(x45868).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x45806))
    val x45833 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45807).name("x45833").ctrl(x45868).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29101, b29097, b29053, b29054, b29006),x45807,x45805,Block((x45805) => Const(())),List(List(b29105)),List(List(b29106)))
    val b29105 = CounterIter(x45806, None).name("b29105").ctrl(x45833) // b29105
    val b29106 = Const(true).name("b29106").ctrl(x45833) // b29106
    val x45808 = OpDef(op=FixAdd, inputs=List(b29051, b29105)).name("x45808").ctrl(x45833).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29051,b29105)
    val x45809 = OpDef(op=BitAnd, inputs=List(b29106, b29101)).name("x45809").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(b29106,b29101)
    val x45810 = OpDef(op=BitAnd, inputs=List(b29097, b29053)).name("x45810").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(b29097,b29053)
    val x45811 = OpDef(op=BitAnd, inputs=List(b29054, b29006)).name("x45811").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(b29054,b29006)
    val x45812 = OpDef(op=BitAnd, inputs=List(x45809, x45810)).name("x45812").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(x45809,x45810)
    val x45813 = OpDef(op=BitAnd, inputs=List(x45812, x45811)).name("x45813").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(x45812,x45811)
    val x45814 = LoadBanks(List(x45766_d0_b0), List(b29105, b29100)).name("x45814").ctrl(x45833).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x45766,List(List(b29105, b29100)),List(x45813))
    val x45815 = x45814 // x45815 = VectorApply(x45814,0)
    val x45816 = LoadBanks(List(x45529_d3_b0), List(b29096, x45808)).name("x45816").ctrl(x45833).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45529,List(List(b29096, x45808)),List(x45813))
    val x45817 = x45816 // x45817 = VectorApply(x45816,0)
    val x45818 = OpDef(op=FixMul, inputs=List(x45817, x45815)).name("x45818").ctrl(x45833).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x45817,x45815)
    val x45819 = OpDef(op=FixSub, inputs=List(x45808, Const(512))).name("x45819").ctrl(x45833).srcCtx("CellsPar.scala:205:51") // FixSub(x45808,Const(512))
    val x45820 = LoadBanks(List(x45530_d8_b0), List(b29096, x45819)).name("x45820").ctrl(x45833).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45530,List(List(b29096, x45819)),List(x45813))
    val x45821 = x45820 // x45821 = VectorApply(x45820,0)
    val x45822 = OpDef(op=FixMul, inputs=List(x45821, x45815)).name("x45822").ctrl(x45833).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x45821,x45815)
    val x45823 = OpDef(op=FixLt, inputs=List(x45808, Const(512))).name("x45823").ctrl(x45833).srcCtx("CellsPar.scala:206:38") // FixLt(x45808,Const(512))
    val x45824 = OpDef(op=MuxOp, inputs=List(x45823, x45818, x45822)).name("x45824").ctrl(x45833).srcCtx("CellsPar.scala:206:18") // Mux(x45823,x45818,x45822)
    val x45825 = ReadMem(x45805_d1).name("x45825").ctrl(x45833).srcCtx("CellsPar.scala:207:15") // RegRead(x45805)
    val x45826 = OpDef(op=FixEql, inputs=List(b29105, Const(0))).name("x45826").ctrl(x45833).srcCtx("CellsPar.scala:207:15") // FixEql(b29105,Const(0))
    val x45827 = ReduceAccumOp(op=FixAdd, input=x45824, accum=x45825).name("x45827").ctrl(x45833).srcCtx("CellsPar.scala:207:17") // FixAdd(x45824,x45825)
    val x45828 = OpDef(op=BitAnd, inputs=List(b29101, b29097)).name("x45828").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(b29101,b29097)
    val x45829 = OpDef(op=BitAnd, inputs=List(b29053, b29054)).name("x45829").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(b29053,b29054)
    val x45830 = OpDef(op=BitAnd, inputs=List(x45828, x45829)).name("x45830").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(x45828,x45829)
    val x45831 = OpDef(op=BitAnd, inputs=List(x45830, b29006)).name("x45831").ctrl(x45833).srcCtx("UnrollingBase.scala:28:66") // And(x45830,b29006)
    val x45832_x45805_d0 = WriteMem(x45805_d0, x45827).name("x45832_x45805_d0").ctrl(x45833).srcCtx("CellsPar.scala:207:15") // RegWrite(x45805,x45827,x45831)
    val x45832_x45805_d1 = WriteMem(x45805_d1, x45827).name("x45832_x45805_d1").ctrl(x45833).srcCtx("CellsPar.scala:207:15") // RegWrite(x45805,x45827,x45831)
    val x45867 = UnitController(style=SeqPipe, level=InnerControl).name("x45867").ctrl(x45868).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29101, b29097, b29053, b29054, b29006),Block(Const(())))
    val x45834 = OpDef(op=FixAdd, inputs=List(b29052, b29100)).name("x45834").ctrl(x45867).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29052,b29100)
    val x45835 = ReadMem(x45805_d0).name("x45835").ctrl(x45867).srcCtx("CellsPar.scala:210:28") // RegRead(x45805)
    val x45836 = OpDef(op=FixEql, inputs=List(b29051, Const(0))).name("x45836").ctrl(x45867).srcCtx("CellsPar.scala:210:42") // FixEql(b29051,Const(0))
    val x45837 = OpDef(op=BitAnd, inputs=List(b29101, b29097)).name("x45837").ctrl(x45867).srcCtx("UnrollingBase.scala:28:66") // And(b29101,b29097)
    val x45838 = OpDef(op=BitAnd, inputs=List(b29053, b29054)).name("x45838").ctrl(x45867).srcCtx("UnrollingBase.scala:28:66") // And(b29053,b29054)
    val x45839 = OpDef(op=BitAnd, inputs=List(x45837, x45838)).name("x45839").ctrl(x45867).srcCtx("UnrollingBase.scala:28:66") // And(x45837,x45838)
    val x45840 = OpDef(op=BitAnd, inputs=List(x45839, b29006)).name("x45840").ctrl(x45867).srcCtx("UnrollingBase.scala:28:66") // And(x45839,b29006)
    val x45841 = LoadBanks(List(x45536_d3_b0), List(Const(0), x45834)).name("x45841").ctrl(x45867).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45536,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x45834),Const(0),x45840)
    val x45842 = LoadBanks(List(x45532_d1_b0), List(b29096, x45834)).name("x45842").ctrl(x45867).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45532,ArrayBuffer(Const(1), Const(512)),List(b29096, x45834),Const(0),x45840)
    val x45843 = OpDef(op=MuxOp, inputs=List(x45836, x45841, x45842)).name("x45843").ctrl(x45867).srcCtx("CellsPar.scala:210:39") // Mux(x45836,x45841,x45842)
    val x45844 = OpDef(op=FixAdd, inputs=List(x45835, x45843)).name("x45844").ctrl(x45867).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x45835,x45843)
    val x45845 = OpDef(op=FixLeq, inputs=List(Const(1008), b29051)).name("x45845").ctrl(x45867).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29051)
    def split1 = {
    // x45846 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x45846_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x45846_int1").ctrl(x45867).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45846_int2 = OpDef(op=FixSla, inputs=List(x45846_int1, Const(24))).name("x45846_int2").ctrl(x45867).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45846_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x45846_frac1").ctrl(x45867).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45846_frac2 = OpDef(op=FixSla, inputs=List(x45846_frac1, Const(24))).name("x45846_frac2").ctrl(x45867).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45846 = OpDef(op=BitOr, inputs=List(x45846_int2, x45846_frac2)).name("x45846").ctrl(x45867).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x45847 = OpDef(op=FixAdd, inputs=List(x45844, x45846)).name("x45847").ctrl(x45867).srcCtx("CellsPar.scala:218:87") // FixAdd(x45844,x45846)
    val x45848 = OpDef(op=FixSra, inputs=List(x45847, Const(1))).name("x45848").ctrl(x45867).srcCtx("CellsPar.scala:29:22") // FixRsh(x45847,Const(1))
    val x45849 = x45848 // FixConvert(x45848,TRUE,_8,_24) (Same Type. No op)
    val x45850 = OpDef(op=FixAbs, inputs=List(x45849)).name("x45850").ctrl(x45867).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x45849)
    val x45851 = OpDef(op=FixLt, inputs=List(Const(2.5), x45850)).name("x45851").ctrl(x45867).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x45850)
    val x45852 = OpDef(op=FixLt, inputs=List(Const(0.5), x45850)).name("x45852").ctrl(x45867).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x45850)
    val x45853 = OpDef(op=FixLeq, inputs=List(x45850, Const(2.5))).name("x45853").ctrl(x45867).srcCtx("CellsPar.scala:54:52") // FixLeq(x45850,Const(2.5))
    val x45854 = OpDef(op=BitAnd, inputs=List(x45852, x45853)).name("x45854").ctrl(x45867).srcCtx("CellsPar.scala:54:43:cond2") // And(x45852,x45853)
    val x45855 = OpDef(op=FixSra, inputs=List(x45850, Const(2))).name("x45855").ctrl(x45867).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x45850,Const(2))
    val x45856 = OpDef(op=FixAdd, inputs=List(x45855, Const(0.375))).name("x45856").ctrl(x45867).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x45855,Const(0.375))
    val x45857 = OpDef(op=MuxOp, inputs=List(x45854, x45856, x45850)).name("x45857").ctrl(x45867).srcCtx("CellsPar.scala:58:20:body2") // Mux(x45854,x45856,x45850)
    val x45858 = OpDef(op=MuxOp, inputs=List(x45851, Const(1.0), x45857)).name("x45858").ctrl(x45867).srcCtx("CellsPar.scala:60:20:absre") // Mux(x45851,Const(1),x45857)
    val x45859 = OpDef(op=FixNeg, inputs=List(x45858)).name("x45859").ctrl(x45867).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x45858)
    val x45860 = OpDef(op=FixLt, inputs=List(x45849, Const(0.0))).name("x45860").ctrl(x45867).srcCtx("CellsPar.scala:63:22") // FixLt(x45849,Const(0))
    val x45861 = OpDef(op=MuxOp, inputs=List(x45860, x45859, x45858)).name("x45861").ctrl(x45867).srcCtx("CellsPar.scala:63:17:re") // Mux(x45860,x45859,x45858)
    val x45862 = x45861 // FixConvert(x45861,TRUE,_8,_24) (Same Type. No op)
    val x45863 = OpDef(op=FixAdd, inputs=List(x45862, Const(1.0))).name("x45863").ctrl(x45867).srcCtx("CellsPar.scala:29:33") // FixAdd(x45862,Const(1))
    val x45864 = OpDef(op=FixSra, inputs=List(x45863, Const(1))).name("x45864").ctrl(x45867).srcCtx("CellsPar.scala:29:44") // FixRsh(x45863,Const(1))
    val x45865 = OpDef(op=MuxOp, inputs=List(x45845, x45864, x45844)).name("x45865").ctrl(x45867).srcCtx("CellsPar.scala:218:43") // Mux(x45845,x45864,x45844)
    val x45866 = StoreBanks(List(x45532_d0_b0, x45532_d1_b0), List(b29096, x45834), x45865).name("x45866").ctrl(x45867).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45532,ArrayBuffer(Const(1), Const(512)),List(b29096, x45834),Const(0),x45865,x45840)
    val x45871 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x45871").ctrl(x46270).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x45872 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x45872").ctrl(x46270).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x45873 = CounterChain(List(x45872,x45871)).name("x45873").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x45872, x45871))
    val x45976 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45873).name("x45976").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x45873,Block(Const(())),List(List(b29173), List(b29174)),List(List(b29175), List(b29176)))
    val b29173 = CounterIter(x45872, Some(0)).name("b29173").ctrl(x45976) // b29173
    val b29175 = Const(true).name("b29175").ctrl(x45976) // b29175
    val b29174 = CounterIter(x45871, Some(0)).name("b29174").ctrl(x45976) // b29174
    val b29176 = Const(true).name("b29176").ctrl(x45976) // b29176
    val x45874_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x45874_d0_b0").ctrl(x45976).srcCtx("CellsPar.scala:191:33:tileKernel") // x45874 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x45874_d0_b0) = false
    bufferDepthOf(x45874_d0_b0) = 2
    val x45877 = UnitController(style=SeqPipe, level=InnerControl).name("x45877").ctrl(x45976).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29175, b29176, b29006),Block(Const(())))
    val x45875 = OpDef(op=FixAdd, inputs=List(b29173, Const(16))).name("x45875").ctrl(x45877).srcCtx("CellsPar.scala:192:36") // FixAdd(b29173,Const(16))
    val x45876 = OpDef(op=FixAdd, inputs=List(b29174, Const(16))).name("x45876").ctrl(x45877).srcCtx("CellsPar.scala:192:45") // FixAdd(b29174,Const(16))
    val x45878 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45878").ctrl(x45976).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45879 = CounterChain(List(x45878)).name("x45879").ctrl(x45976).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x45878))
    val x45908 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45879).name("x45908").ctrl(x45976).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29175, b29176, b29006),x45879,Block(Const(())),List(List(b29183)),List(List(b29184)))
    val b29183 = CounterIter(x45878, Some(0)).name("b29183").ctrl(x45908) // b29183
    val b29184 = Const(true).name("b29184").ctrl(x45908) // b29184
    val b48432 = StreamOut(field="offset").name("b48432").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // x45880 = StreamOutNew(BurstCmdBus)
    isAccum(b48432) = false
    bufferDepthOf(b48432) = 1
    val b48433 = StreamOut(field="size").name("b48433").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // x45880 = StreamOutNew(BurstCmdBus)
    isAccum(b48433) = false
    bufferDepthOf(b48433) = 1
    val x45881 = StreamIn(field="data").name("x45881").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // x45881 = StreamInNew(BurstDataBus())
    isAccum(x45881) = false
    bufferDepthOf(x45881) = 1
    val x45896 = UnitController(style=SeqPipe, level=InnerControl).name("x45896").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29184, b29175, b29176, b29006),Block(x45895))
    val x45882 = OpDef(op=FixAdd, inputs=List(b29173, b29183)).name("x45882").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // FixAdd(b29173,b29183)
    val x45883 = x45882 // FixConvert(x45882,TRUE,_32,_0) (Same Type. No op)
    val x45884 = OpDef(op=FixSla, inputs=List(x45883, Const(9))).name("x45884").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // FixLsh(x45883,Const(9))
    val x45885 = b29174 // FixConvert(b29174,TRUE,_32,_0) (Same Type. No op)
    val x45886 = OpDef(op=FixAdd, inputs=List(x45884, x45885)).name("x45886").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // FixAdd(x45884,x45885)
    val x45887 = OpDef(op=FixSla, inputs=List(x45886, Const(2))).name("x45887").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // FixLsh(x45886,Const(2))
    val x45888 = x45887 // FixConvert(x45887,TRUE,_64,_0)
    val x45889 = DramAddress(x45178).name("x45889").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45178)
    val x45891_x45890 = OpDef(op=FixAdd, inputs=List(x45888, x45889)).name("x45891_x45890").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // FixAdd(x45888,x45889)
    // x45891 = SimpleStruct(ArrayBuffer((offset,x45890), (size,Const(64)), (isLoad,Const(true))))
    val x45892 = OpDef(op=BitAnd, inputs=List(b29184, b29175)).name("x45892").ctrl(x45896).srcCtx("UnrollingBase.scala:28:66") // And(b29184,b29175)
    val x45893 = OpDef(op=BitAnd, inputs=List(b29176, b29006)).name("x45893").ctrl(x45896).srcCtx("UnrollingBase.scala:28:66") // And(b29176,b29006)
    val x45894 = OpDef(op=BitAnd, inputs=List(x45892, x45893)).name("x45894").ctrl(x45896).srcCtx("UnrollingBase.scala:28:66") // And(x45892,x45893)
    val x45895_b48434_b48432 = WriteMem(b48432, x45891_x45890).name("x45895_b48434_b48432").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // StreamWrite(x45880,x45891,x45894)
    val x45895_b48435_b48433 = WriteMem(b48433, Const(64)).name("x45895_b48435_b48433").ctrl(x45896).srcCtx("CellsPar.scala:192:20") // StreamWrite(x45880,x45891,x45894)
    val x45897 = FringeDenseLoad(dram=List(x45178), cmdStream=List(b48432, b48433), dataStream=List(x45881)).name("x45897").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45178,x45880,x45881)
    val x45898 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45898").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45899 = CounterChain(List(x45898)).name("x45899").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x45898))
    val x45907 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45899).name("x45907").ctrl(x45908).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29184, b29175, b29176, b29006),x45899,Block(Const(())),List(List(b29205)),List(List(b29206)))
    val b29205 = CounterIter(x45898, None).name("b29205").ctrl(x45907) // b29205
    val b29206 = Const(true).name("b29206").ctrl(x45907) // b29206
    val x45900 = OpDef(op=BitAnd, inputs=List(b29206, b29184)).name("x45900").ctrl(x45907).srcCtx("UnrollingBase.scala:28:66") // And(b29206,b29184)
    val x45901 = OpDef(op=BitAnd, inputs=List(b29175, b29176)).name("x45901").ctrl(x45907).srcCtx("UnrollingBase.scala:28:66") // And(b29175,b29176)
    val x45902 = OpDef(op=BitAnd, inputs=List(x45900, x45901)).name("x45902").ctrl(x45907).srcCtx("UnrollingBase.scala:28:66") // And(x45900,x45901)
    val x45903 = OpDef(op=BitAnd, inputs=List(x45902, b29006)).name("x45903").ctrl(x45907).srcCtx("UnrollingBase.scala:28:66") // And(x45902,b29006)
    val x45904_x45904 = ReadMem(x45881).name("x45904_x45904").ctrl(x45907).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x45881,List(x45903))
    val x45905_x45905 = x45904_x45904 // x45905 = VectorApply(x45904,0)
    val x45906 = StoreBanks(List(x45874_d0_b0), List(b29183, b29205), x45905_x45905).name("x45906").ctrl(x45907).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x45874,List(List(b29183, b29205)),List(x45905),List(x45903))
    val x45909 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x45909").ctrl(x45976).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x45910 = CounterChain(List(x45909)).name("x45910").ctrl(x45976).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x45909))
    val x45975 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45910).name("x45975").ctrl(x45976).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29175, b29176, b29006),x45910,Block(Const(())),List(List(b29218)),List(List(b29219)))
    val b29218 = CounterIter(x45909, Some(0)).name("b29218").ctrl(x45975) // b29218
    val b29219 = Const(true).name("b29219").ctrl(x45975) // b29219
    val x45911 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45911").ctrl(x45975).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45912 = CounterChain(List(x45911)).name("x45912").ctrl(x45975).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x45911))
    val x45974 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45912).name("x45974").ctrl(x45975).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29219, b29175, b29176, b29006),x45912,Block(Const(())),List(List(b29222)),List(List(b29223)))
    val b29222 = CounterIter(x45911, Some(0)).name("b29222").ctrl(x45974) // b29222
    val b29223 = Const(true).name("b29223").ctrl(x45974) // b29223
    val x45913_d0 = Reg(init=Some(0.0)).name("x45913_d0").ctrl(x45974).srcCtx("CellsPar.scala:195:34:prod") // x45913 = RegNew(Const(0))
    isAccum(x45913_d0) = false
    bufferDepthOf(x45913_d0) = 2
    val x45913_d1 = Reg(init=Some(0.0)).name("x45913_d1").ctrl(x45974).srcCtx("CellsPar.scala:195:34:prod") // x45913 = RegNew(Const(0))
    isAccum(x45913_d1) = true
    bufferDepthOf(x45913_d1) = 1
    val x45914 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45914").ctrl(x45974).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45915 = CounterChain(List(x45914)).name("x45915").ctrl(x45974).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x45914))
    val x45941 = LoopController(style=InnerPipe, level=InnerControl, cchain=x45915).name("x45941").ctrl(x45974).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29223, b29219, b29175, b29176, b29006),x45915,x45913,Block((x45913) => Const(())),List(List(b29227)),List(List(b29228)))
    val b29227 = CounterIter(x45914, None).name("b29227").ctrl(x45941) // b29227
    val b29228 = Const(true).name("b29228").ctrl(x45941) // b29228
    val x45916 = OpDef(op=FixAdd, inputs=List(b29173, b29227)).name("x45916").ctrl(x45941).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29173,b29227)
    val x45917 = OpDef(op=BitAnd, inputs=List(b29228, b29223)).name("x45917").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(b29228,b29223)
    val x45918 = OpDef(op=BitAnd, inputs=List(b29219, b29175)).name("x45918").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(b29219,b29175)
    val x45919 = OpDef(op=BitAnd, inputs=List(b29176, b29006)).name("x45919").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(b29176,b29006)
    val x45920 = OpDef(op=BitAnd, inputs=List(x45917, x45918)).name("x45920").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(x45917,x45918)
    val x45921 = OpDef(op=BitAnd, inputs=List(x45920, x45919)).name("x45921").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(x45920,x45919)
    val x45922 = LoadBanks(List(x45874_d0_b0), List(b29227, b29222)).name("x45922").ctrl(x45941).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x45874,List(List(b29227, b29222)),List(x45921))
    val x45923 = x45922 // x45923 = VectorApply(x45922,0)
    val x45924 = LoadBanks(List(x45529_d2_b0), List(b29218, x45916)).name("x45924").ctrl(x45941).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45529,List(List(b29218, x45916)),List(x45921))
    val x45925 = x45924 // x45925 = VectorApply(x45924,0)
    val x45926 = OpDef(op=FixMul, inputs=List(x45925, x45923)).name("x45926").ctrl(x45941).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x45925,x45923)
    val x45927 = OpDef(op=FixSub, inputs=List(x45916, Const(512))).name("x45927").ctrl(x45941).srcCtx("CellsPar.scala:205:51") // FixSub(x45916,Const(512))
    val x45928 = LoadBanks(List(x45530_d7_b0), List(b29218, x45927)).name("x45928").ctrl(x45941).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45530,List(List(b29218, x45927)),List(x45921))
    val x45929 = x45928 // x45929 = VectorApply(x45928,0)
    val x45930 = OpDef(op=FixMul, inputs=List(x45929, x45923)).name("x45930").ctrl(x45941).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x45929,x45923)
    val x45931 = OpDef(op=FixLt, inputs=List(x45916, Const(512))).name("x45931").ctrl(x45941).srcCtx("CellsPar.scala:206:38") // FixLt(x45916,Const(512))
    val x45932 = OpDef(op=MuxOp, inputs=List(x45931, x45926, x45930)).name("x45932").ctrl(x45941).srcCtx("CellsPar.scala:206:18") // Mux(x45931,x45926,x45930)
    val x45933 = ReadMem(x45913_d1).name("x45933").ctrl(x45941).srcCtx("CellsPar.scala:207:15") // RegRead(x45913)
    val x45934 = OpDef(op=FixEql, inputs=List(b29227, Const(0))).name("x45934").ctrl(x45941).srcCtx("CellsPar.scala:207:15") // FixEql(b29227,Const(0))
    val x45935 = ReduceAccumOp(op=FixAdd, input=x45932, accum=x45933).name("x45935").ctrl(x45941).srcCtx("CellsPar.scala:207:17") // FixAdd(x45932,x45933)
    val x45936 = OpDef(op=BitAnd, inputs=List(b29223, b29219)).name("x45936").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(b29223,b29219)
    val x45937 = OpDef(op=BitAnd, inputs=List(b29175, b29176)).name("x45937").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(b29175,b29176)
    val x45938 = OpDef(op=BitAnd, inputs=List(x45936, x45937)).name("x45938").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(x45936,x45937)
    val x45939 = OpDef(op=BitAnd, inputs=List(x45938, b29006)).name("x45939").ctrl(x45941).srcCtx("UnrollingBase.scala:28:66") // And(x45938,b29006)
    val x45940_x45913_d0 = WriteMem(x45913_d0, x45935).name("x45940_x45913_d0").ctrl(x45941).srcCtx("CellsPar.scala:207:15") // RegWrite(x45913,x45935,x45939)
    val x45940_x45913_d1 = WriteMem(x45913_d1, x45935).name("x45940_x45913_d1").ctrl(x45941).srcCtx("CellsPar.scala:207:15") // RegWrite(x45913,x45935,x45939)
    val x45973 = UnitController(style=SeqPipe, level=InnerControl).name("x45973").ctrl(x45974).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29223, b29219, b29175, b29176, b29006),Block(Const(())))
    val x45942 = OpDef(op=FixAdd, inputs=List(b29174, b29222)).name("x45942").ctrl(x45973).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29174,b29222)
    val x45943 = ReadMem(x45913_d0).name("x45943").ctrl(x45973).srcCtx("CellsPar.scala:210:28") // RegRead(x45913)
    val x45944 = OpDef(op=FixEql, inputs=List(b29173, Const(0))).name("x45944").ctrl(x45973).srcCtx("CellsPar.scala:210:42") // FixEql(b29173,Const(0))
    val x45945 = OpDef(op=FixAdd, inputs=List(x45942, Const(512))).name("x45945").ctrl(x45973).srcCtx("CellsPar.scala:210:66") // FixAdd(x45942,Const(512))
    val x45946 = OpDef(op=BitAnd, inputs=List(b29223, b29219)).name("x45946").ctrl(x45973).srcCtx("UnrollingBase.scala:28:66") // And(b29223,b29219)
    val x45947 = OpDef(op=BitAnd, inputs=List(b29175, b29176)).name("x45947").ctrl(x45973).srcCtx("UnrollingBase.scala:28:66") // And(b29175,b29176)
    val x45948 = OpDef(op=BitAnd, inputs=List(x45946, x45947)).name("x45948").ctrl(x45973).srcCtx("UnrollingBase.scala:28:66") // And(x45946,x45947)
    val x45949 = OpDef(op=BitAnd, inputs=List(x45948, b29006)).name("x45949").ctrl(x45973).srcCtx("UnrollingBase.scala:28:66") // And(x45948,b29006)
    val x45950 = LoadBanks(List(x45536_d2_b0), List(Const(0), x45945)).name("x45950").ctrl(x45973).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45536,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x45945),Const(0),x45949)
    val x45951 = LoadBanks(List(x45533_d1_b0), List(b29218, x45942)).name("x45951").ctrl(x45973).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45533,ArrayBuffer(Const(1), Const(512)),List(b29218, x45942),Const(0),x45949)
    val x45952 = OpDef(op=MuxOp, inputs=List(x45944, x45950, x45951)).name("x45952").ctrl(x45973).srcCtx("CellsPar.scala:210:39") // Mux(x45944,x45950,x45951)
    val x45953 = OpDef(op=FixAdd, inputs=List(x45943, x45952)).name("x45953").ctrl(x45973).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x45943,x45952)
    val x45954 = OpDef(op=FixLeq, inputs=List(Const(1008), b29173)).name("x45954").ctrl(x45973).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29173)
    // x45955 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x45955_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x45955_int1").ctrl(x45973).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45955_int2 = OpDef(op=FixSla, inputs=List(x45955_int1, Const(24))).name("x45955_int2").ctrl(x45973).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45955_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x45955_frac1").ctrl(x45973).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45955_frac2 = OpDef(op=FixSla, inputs=List(x45955_frac1, Const(24))).name("x45955_frac2").ctrl(x45973).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x45955 = OpDef(op=BitOr, inputs=List(x45955_int2, x45955_frac2)).name("x45955").ctrl(x45973).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x45956 = OpDef(op=FixAdd, inputs=List(x45953, x45955)).name("x45956").ctrl(x45973).srcCtx("CellsPar.scala:218:87") // FixAdd(x45953,x45955)
    val x45957 = x45956 // FixConvert(x45956,TRUE,_8,_24) (Same Type. No op)
    val x45958 = OpDef(op=FixAbs, inputs=List(x45957)).name("x45958").ctrl(x45973).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x45957)
    val x45959 = OpDef(op=FixLt, inputs=List(Const(2.5), x45958)).name("x45959").ctrl(x45973).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x45958)
    val x45960 = OpDef(op=FixLt, inputs=List(Const(0.5), x45958)).name("x45960").ctrl(x45973).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x45958)
    val x45961 = OpDef(op=FixLeq, inputs=List(x45958, Const(2.5))).name("x45961").ctrl(x45973).srcCtx("CellsPar.scala:54:52") // FixLeq(x45958,Const(2.5))
    val x45962 = OpDef(op=BitAnd, inputs=List(x45960, x45961)).name("x45962").ctrl(x45973).srcCtx("CellsPar.scala:54:43:cond2") // And(x45960,x45961)
    val x45963 = OpDef(op=FixSra, inputs=List(x45958, Const(2))).name("x45963").ctrl(x45973).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x45958,Const(2))
    val x45964 = OpDef(op=FixAdd, inputs=List(x45963, Const(0.375))).name("x45964").ctrl(x45973).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x45963,Const(0.375))
    val x45965 = OpDef(op=MuxOp, inputs=List(x45962, x45964, x45958)).name("x45965").ctrl(x45973).srcCtx("CellsPar.scala:58:20:body2") // Mux(x45962,x45964,x45958)
    val x45966 = OpDef(op=MuxOp, inputs=List(x45959, Const(1.0), x45965)).name("x45966").ctrl(x45973).srcCtx("CellsPar.scala:60:20:absre") // Mux(x45959,Const(1),x45965)
    val x45967 = OpDef(op=FixNeg, inputs=List(x45966)).name("x45967").ctrl(x45973).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x45966)
    val x45968 = OpDef(op=FixLt, inputs=List(x45957, Const(0.0))).name("x45968").ctrl(x45973).srcCtx("CellsPar.scala:63:22") // FixLt(x45957,Const(0))
    val x45969 = OpDef(op=MuxOp, inputs=List(x45968, x45967, x45966)).name("x45969").ctrl(x45973).srcCtx("CellsPar.scala:63:17:re") // Mux(x45968,x45967,x45966)
    val x45970 = x45969 // FixConvert(x45969,TRUE,_8,_24) (Same Type. No op)
    val x45971 = OpDef(op=MuxOp, inputs=List(x45954, x45970, x45953)).name("x45971").ctrl(x45973).srcCtx("CellsPar.scala:218:43") // Mux(x45954,x45970,x45953)
    val x45972 = StoreBanks(List(x45533_d0_b0, x45533_d1_b0), List(b29218, x45942), x45971).name("x45972").ctrl(x45973).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45533,ArrayBuffer(Const(1), Const(512)),List(b29218, x45942),Const(0),x45971,x45949)
    val x45977 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x45977").ctrl(x46270).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x45978 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x45978").ctrl(x46270).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x45979 = CounterChain(List(x45978,x45977)).name("x45979").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x45978, x45977))
    val x46085 = LoopController(style=MetaPipe, level=OuterControl, cchain=x45979).name("x46085").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x45979,Block(Const(())),List(List(b29293), List(b29294)),List(List(b29295), List(b29296)))
    val b29293 = CounterIter(x45978, Some(0)).name("b29293").ctrl(x46085) // b29293
    val b29295 = Const(true).name("b29295").ctrl(x46085) // b29295
    val b29294 = CounterIter(x45977, Some(0)).name("b29294").ctrl(x46085) // b29294
    val b29296 = Const(true).name("b29296").ctrl(x46085) // b29296
    val x45980_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x45980_d0_b0").ctrl(x46085).srcCtx("CellsPar.scala:191:33:tileKernel") // x45980 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x45980_d0_b0) = false
    bufferDepthOf(x45980_d0_b0) = 2
    val x45983 = UnitController(style=SeqPipe, level=InnerControl).name("x45983").ctrl(x46085).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29295, b29296, b29006),Block(Const(())))
    val x45981 = OpDef(op=FixAdd, inputs=List(b29293, Const(16))).name("x45981").ctrl(x45983).srcCtx("CellsPar.scala:192:36") // FixAdd(b29293,Const(16))
    val x45982 = OpDef(op=FixAdd, inputs=List(b29294, Const(16))).name("x45982").ctrl(x45983).srcCtx("CellsPar.scala:192:45") // FixAdd(b29294,Const(16))
    val x45984 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x45984").ctrl(x46085).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x45985 = CounterChain(List(x45984)).name("x45985").ctrl(x46085).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x45984))
    val x46014 = LoopController(style=StreamPipe, level=OuterControl, cchain=x45985).name("x46014").ctrl(x46085).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29295, b29296, b29006),x45985,Block(Const(())),List(List(b29303)),List(List(b29304)))
    val b29303 = CounterIter(x45984, Some(0)).name("b29303").ctrl(x46014) // b29303
    val b29304 = Const(true).name("b29304").ctrl(x46014) // b29304
    val b48436 = StreamOut(field="offset").name("b48436").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // x45986 = StreamOutNew(BurstCmdBus)
    isAccum(b48436) = false
    bufferDepthOf(b48436) = 1
    val b48437 = StreamOut(field="size").name("b48437").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // x45986 = StreamOutNew(BurstCmdBus)
    isAccum(b48437) = false
    bufferDepthOf(b48437) = 1
    val x45987 = StreamIn(field="data").name("x45987").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // x45987 = StreamInNew(BurstDataBus())
    isAccum(x45987) = false
    bufferDepthOf(x45987) = 1
    val x46002 = UnitController(style=SeqPipe, level=InnerControl).name("x46002").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29304, b29295, b29296, b29006),Block(x46001))
    val x45988 = OpDef(op=FixAdd, inputs=List(b29293, b29303)).name("x45988").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // FixAdd(b29293,b29303)
    val x45989 = x45988 // FixConvert(x45988,TRUE,_32,_0) (Same Type. No op)
    val x45990 = OpDef(op=FixSla, inputs=List(x45989, Const(9))).name("x45990").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // FixLsh(x45989,Const(9))
    val x45991 = b29294 // FixConvert(b29294,TRUE,_32,_0) (Same Type. No op)
    val x45992 = OpDef(op=FixAdd, inputs=List(x45990, x45991)).name("x45992").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // FixAdd(x45990,x45991)
    val x45993 = OpDef(op=FixSla, inputs=List(x45992, Const(2))).name("x45993").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // FixLsh(x45992,Const(2))
    val x45994 = x45993 // FixConvert(x45993,TRUE,_64,_0)
    val x45995 = DramAddress(x45179).name("x45995").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45179)
    val x45997_x45996 = OpDef(op=FixAdd, inputs=List(x45994, x45995)).name("x45997_x45996").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // FixAdd(x45994,x45995)
    // x45997 = SimpleStruct(ArrayBuffer((offset,x45996), (size,Const(64)), (isLoad,Const(true))))
    val x45998 = OpDef(op=BitAnd, inputs=List(b29304, b29295)).name("x45998").ctrl(x46002).srcCtx("UnrollingBase.scala:28:66") // And(b29304,b29295)
    val x45999 = OpDef(op=BitAnd, inputs=List(b29296, b29006)).name("x45999").ctrl(x46002).srcCtx("UnrollingBase.scala:28:66") // And(b29296,b29006)
    val x46000 = OpDef(op=BitAnd, inputs=List(x45998, x45999)).name("x46000").ctrl(x46002).srcCtx("UnrollingBase.scala:28:66") // And(x45998,x45999)
    val x46001_b48438_b48436 = WriteMem(b48436, x45997_x45996).name("x46001_b48438_b48436").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // StreamWrite(x45986,x45997,x46000)
    val x46001_b48439_b48437 = WriteMem(b48437, Const(64)).name("x46001_b48439_b48437").ctrl(x46002).srcCtx("CellsPar.scala:192:20") // StreamWrite(x45986,x45997,x46000)
    val x46003 = FringeDenseLoad(dram=List(x45179), cmdStream=List(b48436, b48437), dataStream=List(x45987)).name("x46003").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45179,x45986,x45987)
    val x46004 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46004").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46005 = CounterChain(List(x46004)).name("x46005").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46004))
    val x46013 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46005).name("x46013").ctrl(x46014).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29304, b29295, b29296, b29006),x46005,Block(Const(())),List(List(b29325)),List(List(b29326)))
    val b29325 = CounterIter(x46004, None).name("b29325").ctrl(x46013) // b29325
    val b29326 = Const(true).name("b29326").ctrl(x46013) // b29326
    val x46006 = OpDef(op=BitAnd, inputs=List(b29326, b29304)).name("x46006").ctrl(x46013).srcCtx("UnrollingBase.scala:28:66") // And(b29326,b29304)
    val x46007 = OpDef(op=BitAnd, inputs=List(b29295, b29296)).name("x46007").ctrl(x46013).srcCtx("UnrollingBase.scala:28:66") // And(b29295,b29296)
    val x46008 = OpDef(op=BitAnd, inputs=List(x46006, x46007)).name("x46008").ctrl(x46013).srcCtx("UnrollingBase.scala:28:66") // And(x46006,x46007)
    val x46009 = OpDef(op=BitAnd, inputs=List(x46008, b29006)).name("x46009").ctrl(x46013).srcCtx("UnrollingBase.scala:28:66") // And(x46008,b29006)
    val x46010_x46010 = ReadMem(x45987).name("x46010_x46010").ctrl(x46013).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x45987,List(x46009))
    val x46011_x46011 = x46010_x46010 // x46011 = VectorApply(x46010,0)
    val x46012 = StoreBanks(List(x45980_d0_b0), List(b29303, b29325), x46011_x46011).name("x46012").ctrl(x46013).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x45980,List(List(b29303, b29325)),List(x46011),List(x46009))
    val x46015 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46015").ctrl(x46085).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46016 = CounterChain(List(x46015)).name("x46016").ctrl(x46085).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x46015))
    val x46084 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46016).name("x46084").ctrl(x46085).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29295, b29296, b29006),x46016,Block(Const(())),List(List(b29338)),List(List(b29339)))
    val b29338 = CounterIter(x46015, Some(0)).name("b29338").ctrl(x46084) // b29338
    val b29339 = Const(true).name("b29339").ctrl(x46084) // b29339
    val x46017 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46017").ctrl(x46084).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46018 = CounterChain(List(x46017)).name("x46018").ctrl(x46084).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x46017))
    val x46083 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46018).name("x46083").ctrl(x46084).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29339, b29295, b29296, b29006),x46018,Block(Const(())),List(List(b29342)),List(List(b29343)))
    val b29342 = CounterIter(x46017, Some(0)).name("b29342").ctrl(x46083) // b29342
    val b29343 = Const(true).name("b29343").ctrl(x46083) // b29343
    val x46019_d0 = Reg(init=Some(0.0)).name("x46019_d0").ctrl(x46083).srcCtx("CellsPar.scala:195:34:prod") // x46019 = RegNew(Const(0))
    isAccum(x46019_d0) = false
    bufferDepthOf(x46019_d0) = 2
    val x46019_d1 = Reg(init=Some(0.0)).name("x46019_d1").ctrl(x46083).srcCtx("CellsPar.scala:195:34:prod") // x46019 = RegNew(Const(0))
    isAccum(x46019_d1) = true
    bufferDepthOf(x46019_d1) = 1
    val x46020 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46020").ctrl(x46083).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46021 = CounterChain(List(x46020)).name("x46021").ctrl(x46083).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x46020))
    val x46047 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46021).name("x46047").ctrl(x46083).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29343, b29339, b29295, b29296, b29006),x46021,x46019,Block((x46019) => Const(())),List(List(b29347)),List(List(b29348)))
    val b29347 = CounterIter(x46020, None).name("b29347").ctrl(x46047) // b29347
    val b29348 = Const(true).name("b29348").ctrl(x46047) // b29348
    val x46022 = OpDef(op=FixAdd, inputs=List(b29293, b29347)).name("x46022").ctrl(x46047).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29293,b29347)
    val x46023 = OpDef(op=BitAnd, inputs=List(b29348, b29343)).name("x46023").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(b29348,b29343)
    val x46024 = OpDef(op=BitAnd, inputs=List(b29339, b29295)).name("x46024").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(b29339,b29295)
    val x46025 = OpDef(op=BitAnd, inputs=List(b29296, b29006)).name("x46025").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(b29296,b29006)
    val x46026 = OpDef(op=BitAnd, inputs=List(x46023, x46024)).name("x46026").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(x46023,x46024)
    val x46027 = OpDef(op=BitAnd, inputs=List(x46026, x46025)).name("x46027").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(x46026,x46025)
    val x46028 = LoadBanks(List(x45980_d0_b0), List(b29347, b29342)).name("x46028").ctrl(x46047).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x45980,List(List(b29347, b29342)),List(x46027))
    val x46029 = x46028 // x46029 = VectorApply(x46028,0)
    val x46030 = LoadBanks(List(x45529_d1_b0), List(b29338, x46022)).name("x46030").ctrl(x46047).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45529,List(List(b29338, x46022)),List(x46027))
    val x46031 = x46030 // x46031 = VectorApply(x46030,0)
    val x46032 = OpDef(op=FixMul, inputs=List(x46031, x46029)).name("x46032").ctrl(x46047).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x46031,x46029)
    val x46033 = OpDef(op=FixSub, inputs=List(x46022, Const(512))).name("x46033").ctrl(x46047).srcCtx("CellsPar.scala:205:51") // FixSub(x46022,Const(512))
    val x46034 = LoadBanks(List(x45530_d6_b0), List(b29338, x46033)).name("x46034").ctrl(x46047).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45530,List(List(b29338, x46033)),List(x46027))
    val x46035 = x46034 // x46035 = VectorApply(x46034,0)
    val x46036 = OpDef(op=FixMul, inputs=List(x46035, x46029)).name("x46036").ctrl(x46047).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x46035,x46029)
    val x46037 = OpDef(op=FixLt, inputs=List(x46022, Const(512))).name("x46037").ctrl(x46047).srcCtx("CellsPar.scala:206:38") // FixLt(x46022,Const(512))
    val x46038 = OpDef(op=MuxOp, inputs=List(x46037, x46032, x46036)).name("x46038").ctrl(x46047).srcCtx("CellsPar.scala:206:18") // Mux(x46037,x46032,x46036)
    val x46039 = ReadMem(x46019_d1).name("x46039").ctrl(x46047).srcCtx("CellsPar.scala:207:15") // RegRead(x46019)
    val x46040 = OpDef(op=FixEql, inputs=List(b29347, Const(0))).name("x46040").ctrl(x46047).srcCtx("CellsPar.scala:207:15") // FixEql(b29347,Const(0))
    val x46041 = ReduceAccumOp(op=FixAdd, input=x46038, accum=x46039).name("x46041").ctrl(x46047).srcCtx("CellsPar.scala:207:17") // FixAdd(x46038,x46039)
    val x46042 = OpDef(op=BitAnd, inputs=List(b29343, b29339)).name("x46042").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(b29343,b29339)
    val x46043 = OpDef(op=BitAnd, inputs=List(b29295, b29296)).name("x46043").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(b29295,b29296)
    val x46044 = OpDef(op=BitAnd, inputs=List(x46042, x46043)).name("x46044").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(x46042,x46043)
    val x46045 = OpDef(op=BitAnd, inputs=List(x46044, b29006)).name("x46045").ctrl(x46047).srcCtx("UnrollingBase.scala:28:66") // And(x46044,b29006)
    val x46046_x46019_d0 = WriteMem(x46019_d0, x46041).name("x46046_x46019_d0").ctrl(x46047).srcCtx("CellsPar.scala:207:15") // RegWrite(x46019,x46041,x46045)
    val x46046_x46019_d1 = WriteMem(x46019_d1, x46041).name("x46046_x46019_d1").ctrl(x46047).srcCtx("CellsPar.scala:207:15") // RegWrite(x46019,x46041,x46045)
    val x46082 = UnitController(style=SeqPipe, level=InnerControl).name("x46082").ctrl(x46083).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29343, b29339, b29295, b29296, b29006),Block(Const(())))
    val x46048 = OpDef(op=FixAdd, inputs=List(b29294, b29342)).name("x46048").ctrl(x46082).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29294,b29342)
    val x46049 = ReadMem(x46019_d0).name("x46049").ctrl(x46082).srcCtx("CellsPar.scala:210:28") // RegRead(x46019)
    val x46050 = OpDef(op=FixEql, inputs=List(b29293, Const(0))).name("x46050").ctrl(x46082).srcCtx("CellsPar.scala:210:42") // FixEql(b29293,Const(0))
    val x46051 = OpDef(op=FixAdd, inputs=List(x46048, Const(1024))).name("x46051").ctrl(x46082).srcCtx("CellsPar.scala:210:66") // FixAdd(x46048,Const(1024))
    val x46052 = OpDef(op=BitAnd, inputs=List(b29343, b29339)).name("x46052").ctrl(x46082).srcCtx("UnrollingBase.scala:28:66") // And(b29343,b29339)
    val x46053 = OpDef(op=BitAnd, inputs=List(b29295, b29296)).name("x46053").ctrl(x46082).srcCtx("UnrollingBase.scala:28:66") // And(b29295,b29296)
    val x46054 = OpDef(op=BitAnd, inputs=List(x46052, x46053)).name("x46054").ctrl(x46082).srcCtx("UnrollingBase.scala:28:66") // And(x46052,x46053)
    val x46055 = OpDef(op=BitAnd, inputs=List(x46054, b29006)).name("x46055").ctrl(x46082).srcCtx("UnrollingBase.scala:28:66") // And(x46054,b29006)
    val x46056 = LoadBanks(List(x45536_d1_b0), List(Const(0), x46051)).name("x46056").ctrl(x46082).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45536,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46051),Const(0),x46055)
    val x46057 = LoadBanks(List(x45534_d1_b0), List(b29338, x46048)).name("x46057").ctrl(x46082).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45534,ArrayBuffer(Const(1), Const(512)),List(b29338, x46048),Const(0),x46055)
    val x46058 = OpDef(op=MuxOp, inputs=List(x46050, x46056, x46057)).name("x46058").ctrl(x46082).srcCtx("CellsPar.scala:210:39") // Mux(x46050,x46056,x46057)
    val x46059 = OpDef(op=FixAdd, inputs=List(x46049, x46058)).name("x46059").ctrl(x46082).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x46049,x46058)
    val x46060 = OpDef(op=FixLeq, inputs=List(Const(1008), b29293)).name("x46060").ctrl(x46082).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29293)
    // x46061 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46061_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x46061_int1").ctrl(x46082).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46061_int2 = OpDef(op=FixSla, inputs=List(x46061_int1, Const(24))).name("x46061_int2").ctrl(x46082).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46061_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x46061_frac1").ctrl(x46082).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46061_frac2 = OpDef(op=FixSla, inputs=List(x46061_frac1, Const(24))).name("x46061_frac2").ctrl(x46082).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46061 = OpDef(op=BitOr, inputs=List(x46061_int2, x46061_frac2)).name("x46061").ctrl(x46082).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x46062 = OpDef(op=FixAdd, inputs=List(x46059, x46061)).name("x46062").ctrl(x46082).srcCtx("CellsPar.scala:218:87") // FixAdd(x46059,x46061)
    val x46063 = OpDef(op=FixSra, inputs=List(x46062, Const(1))).name("x46063").ctrl(x46082).srcCtx("CellsPar.scala:29:22") // FixRsh(x46062,Const(1))
    val x46064 = x46063 // FixConvert(x46063,TRUE,_8,_24) (Same Type. No op)
    val x46065 = OpDef(op=FixAbs, inputs=List(x46064)).name("x46065").ctrl(x46082).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46064)
    val x46066 = OpDef(op=FixLt, inputs=List(Const(2.5), x46065)).name("x46066").ctrl(x46082).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46065)
    val x46067 = OpDef(op=FixLt, inputs=List(Const(0.5), x46065)).name("x46067").ctrl(x46082).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46065)
    val x46068 = OpDef(op=FixLeq, inputs=List(x46065, Const(2.5))).name("x46068").ctrl(x46082).srcCtx("CellsPar.scala:54:52") // FixLeq(x46065,Const(2.5))
    val x46069 = OpDef(op=BitAnd, inputs=List(x46067, x46068)).name("x46069").ctrl(x46082).srcCtx("CellsPar.scala:54:43:cond2") // And(x46067,x46068)
    val x46070 = OpDef(op=FixSra, inputs=List(x46065, Const(2))).name("x46070").ctrl(x46082).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46065,Const(2))
    val x46071 = OpDef(op=FixAdd, inputs=List(x46070, Const(0.375))).name("x46071").ctrl(x46082).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46070,Const(0.375))
    val x46072 = OpDef(op=MuxOp, inputs=List(x46069, x46071, x46065)).name("x46072").ctrl(x46082).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46069,x46071,x46065)
    val x46073 = OpDef(op=MuxOp, inputs=List(x46066, Const(1.0), x46072)).name("x46073").ctrl(x46082).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46066,Const(1),x46072)
    val x46074 = OpDef(op=FixNeg, inputs=List(x46073)).name("x46074").ctrl(x46082).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46073)
    val x46075 = OpDef(op=FixLt, inputs=List(x46064, Const(0.0))).name("x46075").ctrl(x46082).srcCtx("CellsPar.scala:63:22") // FixLt(x46064,Const(0))
    val x46076 = OpDef(op=MuxOp, inputs=List(x46075, x46074, x46073)).name("x46076").ctrl(x46082).srcCtx("CellsPar.scala:63:17:re") // Mux(x46075,x46074,x46073)
    val x46077 = x46076 // FixConvert(x46076,TRUE,_8,_24) (Same Type. No op)
    val x46078 = OpDef(op=FixAdd, inputs=List(x46077, Const(1.0))).name("x46078").ctrl(x46082).srcCtx("CellsPar.scala:29:33") // FixAdd(x46077,Const(1))
    val x46079 = OpDef(op=FixSra, inputs=List(x46078, Const(1))).name("x46079").ctrl(x46082).srcCtx("CellsPar.scala:29:44") // FixRsh(x46078,Const(1))
    val x46080 = OpDef(op=MuxOp, inputs=List(x46060, x46079, x46059)).name("x46080").ctrl(x46082).srcCtx("CellsPar.scala:218:43") // Mux(x46060,x46079,x46059)
    val x46081 = StoreBanks(List(x45534_d0_b0, x45534_d1_b0), List(b29338, x46048), x46080).name("x46081").ctrl(x46082).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45534,ArrayBuffer(Const(1), Const(512)),List(b29338, x46048),Const(0),x46080,x46055)
    val x46086 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x46086").ctrl(x46270).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x46087 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x46087").ctrl(x46270).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x46088 = CounterChain(List(x46087,x46086)).name("x46088").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x46087, x46086))
    val x46194 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46088).name("x46194").ctrl(x46270).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x46088,Block(Const(())),List(List(b29416), List(b29417)),List(List(b29418), List(b29419)))
    val b29416 = CounterIter(x46087, Some(0)).name("b29416").ctrl(x46194) // b29416
    val b29418 = Const(true).name("b29418").ctrl(x46194) // b29418
    val b29417 = CounterIter(x46086, Some(0)).name("b29417").ctrl(x46194) // b29417
    val b29419 = Const(true).name("b29419").ctrl(x46194) // b29419
    val x46089_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x46089_d0_b0").ctrl(x46194).srcCtx("CellsPar.scala:191:33:tileKernel") // x46089 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x46089_d0_b0) = false
    bufferDepthOf(x46089_d0_b0) = 2
    val x46092 = UnitController(style=SeqPipe, level=InnerControl).name("x46092").ctrl(x46194).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29418, b29419, b29006),Block(Const(())))
    val x46090 = OpDef(op=FixAdd, inputs=List(b29416, Const(16))).name("x46090").ctrl(x46092).srcCtx("CellsPar.scala:192:36") // FixAdd(b29416,Const(16))
    val x46091 = OpDef(op=FixAdd, inputs=List(b29417, Const(16))).name("x46091").ctrl(x46092).srcCtx("CellsPar.scala:192:45") // FixAdd(b29417,Const(16))
    val x46093 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46093").ctrl(x46194).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46094 = CounterChain(List(x46093)).name("x46094").ctrl(x46194).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46093))
    val x46123 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46094).name("x46123").ctrl(x46194).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29418, b29419, b29006),x46094,Block(Const(())),List(List(b29426)),List(List(b29427)))
    val b29426 = CounterIter(x46093, Some(0)).name("b29426").ctrl(x46123) // b29426
    val b29427 = Const(true).name("b29427").ctrl(x46123) // b29427
    val b48440 = StreamOut(field="offset").name("b48440").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // x46095 = StreamOutNew(BurstCmdBus)
    isAccum(b48440) = false
    bufferDepthOf(b48440) = 1
    val b48441 = StreamOut(field="size").name("b48441").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // x46095 = StreamOutNew(BurstCmdBus)
    isAccum(b48441) = false
    bufferDepthOf(b48441) = 1
    val x46096 = StreamIn(field="data").name("x46096").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // x46096 = StreamInNew(BurstDataBus())
    isAccum(x46096) = false
    bufferDepthOf(x46096) = 1
    val x46111 = UnitController(style=SeqPipe, level=InnerControl).name("x46111").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29427, b29418, b29419, b29006),Block(x46110))
    val x46097 = OpDef(op=FixAdd, inputs=List(b29416, b29426)).name("x46097").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // FixAdd(b29416,b29426)
    val x46098 = x46097 // FixConvert(x46097,TRUE,_32,_0) (Same Type. No op)
    val x46099 = OpDef(op=FixSla, inputs=List(x46098, Const(9))).name("x46099").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // FixLsh(x46098,Const(9))
    val x46100 = b29417 // FixConvert(b29417,TRUE,_32,_0) (Same Type. No op)
    val x46101 = OpDef(op=FixAdd, inputs=List(x46099, x46100)).name("x46101").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // FixAdd(x46099,x46100)
    val x46102 = OpDef(op=FixSla, inputs=List(x46101, Const(2))).name("x46102").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // FixLsh(x46101,Const(2))
    val x46103 = x46102 // FixConvert(x46102,TRUE,_64,_0)
    val x46104 = DramAddress(x45180).name("x46104").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45180)
    val x46106_x46105 = OpDef(op=FixAdd, inputs=List(x46103, x46104)).name("x46106_x46105").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // FixAdd(x46103,x46104)
    // x46106 = SimpleStruct(ArrayBuffer((offset,x46105), (size,Const(64)), (isLoad,Const(true))))
    val x46107 = OpDef(op=BitAnd, inputs=List(b29427, b29418)).name("x46107").ctrl(x46111).srcCtx("UnrollingBase.scala:28:66") // And(b29427,b29418)
    val x46108 = OpDef(op=BitAnd, inputs=List(b29419, b29006)).name("x46108").ctrl(x46111).srcCtx("UnrollingBase.scala:28:66") // And(b29419,b29006)
    val x46109 = OpDef(op=BitAnd, inputs=List(x46107, x46108)).name("x46109").ctrl(x46111).srcCtx("UnrollingBase.scala:28:66") // And(x46107,x46108)
    val x46110_b48442_b48440 = WriteMem(b48440, x46106_x46105).name("x46110_b48442_b48440").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46095,x46106,x46109)
    val x46110_b48443_b48441 = WriteMem(b48441, Const(64)).name("x46110_b48443_b48441").ctrl(x46111).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46095,x46106,x46109)
    val x46112 = FringeDenseLoad(dram=List(x45180), cmdStream=List(b48440, b48441), dataStream=List(x46096)).name("x46112").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45180,x46095,x46096)
    val x46113 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46113").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46114 = CounterChain(List(x46113)).name("x46114").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46113))
    val x46122 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46114).name("x46122").ctrl(x46123).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29427, b29418, b29419, b29006),x46114,Block(Const(())),List(List(b29448)),List(List(b29449)))
    val b29448 = CounterIter(x46113, None).name("b29448").ctrl(x46122) // b29448
    val b29449 = Const(true).name("b29449").ctrl(x46122) // b29449
    val x46115 = OpDef(op=BitAnd, inputs=List(b29449, b29427)).name("x46115").ctrl(x46122).srcCtx("UnrollingBase.scala:28:66") // And(b29449,b29427)
    val x46116 = OpDef(op=BitAnd, inputs=List(b29418, b29419)).name("x46116").ctrl(x46122).srcCtx("UnrollingBase.scala:28:66") // And(b29418,b29419)
    val x46117 = OpDef(op=BitAnd, inputs=List(x46115, x46116)).name("x46117").ctrl(x46122).srcCtx("UnrollingBase.scala:28:66") // And(x46115,x46116)
    val x46118 = OpDef(op=BitAnd, inputs=List(x46117, b29006)).name("x46118").ctrl(x46122).srcCtx("UnrollingBase.scala:28:66") // And(x46117,b29006)
    val x46119_x46119 = ReadMem(x46096).name("x46119_x46119").ctrl(x46122).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x46096,List(x46118))
    val x46120_x46120 = x46119_x46119 // x46120 = VectorApply(x46119,0)
    val x46121 = StoreBanks(List(x46089_d0_b0), List(b29426, b29448), x46120_x46120).name("x46121").ctrl(x46122).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x46089,List(List(b29426, b29448)),List(x46120),List(x46118))
    val x46124 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46124").ctrl(x46194).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46125 = CounterChain(List(x46124)).name("x46125").ctrl(x46194).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x46124))
    val x46193 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46125).name("x46193").ctrl(x46194).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29418, b29419, b29006),x46125,Block(Const(())),List(List(b29461)),List(List(b29462)))
    val b29461 = CounterIter(x46124, Some(0)).name("b29461").ctrl(x46193) // b29461
    val b29462 = Const(true).name("b29462").ctrl(x46193) // b29462
    val x46126 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46126").ctrl(x46193).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46127 = CounterChain(List(x46126)).name("x46127").ctrl(x46193).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x46126))
    val x46192 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46127).name("x46192").ctrl(x46193).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29462, b29418, b29419, b29006),x46127,Block(Const(())),List(List(b29465)),List(List(b29466)))
    val b29465 = CounterIter(x46126, Some(0)).name("b29465").ctrl(x46192) // b29465
    val b29466 = Const(true).name("b29466").ctrl(x46192) // b29466
    val x46128_d0 = Reg(init=Some(0.0)).name("x46128_d0").ctrl(x46192).srcCtx("CellsPar.scala:195:34:prod") // x46128 = RegNew(Const(0))
    isAccum(x46128_d0) = false
    bufferDepthOf(x46128_d0) = 2
    val x46128_d1 = Reg(init=Some(0.0)).name("x46128_d1").ctrl(x46192).srcCtx("CellsPar.scala:195:34:prod") // x46128 = RegNew(Const(0))
    isAccum(x46128_d1) = true
    bufferDepthOf(x46128_d1) = 1
    val x46129 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46129").ctrl(x46192).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46130 = CounterChain(List(x46129)).name("x46130").ctrl(x46192).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x46129))
    val x46156 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46130).name("x46156").ctrl(x46192).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29466, b29462, b29418, b29419, b29006),x46130,x46128,Block((x46128) => Const(())),List(List(b29470)),List(List(b29471)))
    val b29470 = CounterIter(x46129, None).name("b29470").ctrl(x46156) // b29470
    val b29471 = Const(true).name("b29471").ctrl(x46156) // b29471
    val x46131 = OpDef(op=FixAdd, inputs=List(b29416, b29470)).name("x46131").ctrl(x46156).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29416,b29470)
    val x46132 = OpDef(op=BitAnd, inputs=List(b29471, b29466)).name("x46132").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(b29471,b29466)
    val x46133 = OpDef(op=BitAnd, inputs=List(b29462, b29418)).name("x46133").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(b29462,b29418)
    val x46134 = OpDef(op=BitAnd, inputs=List(b29419, b29006)).name("x46134").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(b29419,b29006)
    val x46135 = OpDef(op=BitAnd, inputs=List(x46132, x46133)).name("x46135").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(x46132,x46133)
    val x46136 = OpDef(op=BitAnd, inputs=List(x46135, x46134)).name("x46136").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(x46135,x46134)
    val x46137 = LoadBanks(List(x46089_d0_b0), List(b29470, b29465)).name("x46137").ctrl(x46156).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x46089,List(List(b29470, b29465)),List(x46136))
    val x46138 = x46137 // x46138 = VectorApply(x46137,0)
    val x46139 = LoadBanks(List(x45529_d0_b0), List(b29461, x46131)).name("x46139").ctrl(x46156).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45529,List(List(b29461, x46131)),List(x46136))
    val x46140 = x46139 // x46140 = VectorApply(x46139,0)
    val x46141 = OpDef(op=FixMul, inputs=List(x46140, x46138)).name("x46141").ctrl(x46156).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x46140,x46138)
    val x46142 = OpDef(op=FixSub, inputs=List(x46131, Const(512))).name("x46142").ctrl(x46156).srcCtx("CellsPar.scala:205:51") // FixSub(x46131,Const(512))
    val x46143 = LoadBanks(List(x45530_d5_b0), List(b29461, x46142)).name("x46143").ctrl(x46156).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45530,List(List(b29461, x46142)),List(x46136))
    val x46144 = x46143 // x46144 = VectorApply(x46143,0)
    val x46145 = OpDef(op=FixMul, inputs=List(x46144, x46138)).name("x46145").ctrl(x46156).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x46144,x46138)
    val x46146 = OpDef(op=FixLt, inputs=List(x46131, Const(512))).name("x46146").ctrl(x46156).srcCtx("CellsPar.scala:206:38") // FixLt(x46131,Const(512))
    val x46147 = OpDef(op=MuxOp, inputs=List(x46146, x46141, x46145)).name("x46147").ctrl(x46156).srcCtx("CellsPar.scala:206:18") // Mux(x46146,x46141,x46145)
    val x46148 = ReadMem(x46128_d1).name("x46148").ctrl(x46156).srcCtx("CellsPar.scala:207:15") // RegRead(x46128)
    val x46149 = OpDef(op=FixEql, inputs=List(b29470, Const(0))).name("x46149").ctrl(x46156).srcCtx("CellsPar.scala:207:15") // FixEql(b29470,Const(0))
    val x46150 = ReduceAccumOp(op=FixAdd, input=x46147, accum=x46148).name("x46150").ctrl(x46156).srcCtx("CellsPar.scala:207:17") // FixAdd(x46147,x46148)
    val x46151 = OpDef(op=BitAnd, inputs=List(b29466, b29462)).name("x46151").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(b29466,b29462)
    val x46152 = OpDef(op=BitAnd, inputs=List(b29418, b29419)).name("x46152").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(b29418,b29419)
    val x46153 = OpDef(op=BitAnd, inputs=List(x46151, x46152)).name("x46153").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(x46151,x46152)
    val x46154 = OpDef(op=BitAnd, inputs=List(x46153, b29006)).name("x46154").ctrl(x46156).srcCtx("UnrollingBase.scala:28:66") // And(x46153,b29006)
    val x46155_x46128_d0 = WriteMem(x46128_d0, x46150).name("x46155_x46128_d0").ctrl(x46156).srcCtx("CellsPar.scala:207:15") // RegWrite(x46128,x46150,x46154)
    val x46155_x46128_d1 = WriteMem(x46128_d1, x46150).name("x46155_x46128_d1").ctrl(x46156).srcCtx("CellsPar.scala:207:15") // RegWrite(x46128,x46150,x46154)
    val x46191 = UnitController(style=SeqPipe, level=InnerControl).name("x46191").ctrl(x46192).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29466, b29462, b29418, b29419, b29006),Block(Const(())))
    val x46157 = OpDef(op=FixAdd, inputs=List(b29417, b29465)).name("x46157").ctrl(x46191).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29417,b29465)
    val x46158 = ReadMem(x46128_d0).name("x46158").ctrl(x46191).srcCtx("CellsPar.scala:210:28") // RegRead(x46128)
    val x46159 = OpDef(op=FixEql, inputs=List(b29416, Const(0))).name("x46159").ctrl(x46191).srcCtx("CellsPar.scala:210:42") // FixEql(b29416,Const(0))
    val x46160 = OpDef(op=FixAdd, inputs=List(x46157, Const(1536))).name("x46160").ctrl(x46191).srcCtx("CellsPar.scala:210:66") // FixAdd(x46157,Const(1536))
    val x46161 = OpDef(op=BitAnd, inputs=List(b29466, b29462)).name("x46161").ctrl(x46191).srcCtx("UnrollingBase.scala:28:66") // And(b29466,b29462)
    val x46162 = OpDef(op=BitAnd, inputs=List(b29418, b29419)).name("x46162").ctrl(x46191).srcCtx("UnrollingBase.scala:28:66") // And(b29418,b29419)
    val x46163 = OpDef(op=BitAnd, inputs=List(x46161, x46162)).name("x46163").ctrl(x46191).srcCtx("UnrollingBase.scala:28:66") // And(x46161,x46162)
    val x46164 = OpDef(op=BitAnd, inputs=List(x46163, b29006)).name("x46164").ctrl(x46191).srcCtx("UnrollingBase.scala:28:66") // And(x46163,b29006)
    val x46165 = LoadBanks(List(x45536_d0_b0), List(Const(0), x46160)).name("x46165").ctrl(x46191).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45536,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46160),Const(0),x46164)
    val x46166 = LoadBanks(List(x45535_d1_b0), List(b29461, x46157)).name("x46166").ctrl(x46191).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45535,ArrayBuffer(Const(1), Const(512)),List(b29461, x46157),Const(0),x46164)
    val x46167 = OpDef(op=MuxOp, inputs=List(x46159, x46165, x46166)).name("x46167").ctrl(x46191).srcCtx("CellsPar.scala:210:39") // Mux(x46159,x46165,x46166)
    val x46168 = OpDef(op=FixAdd, inputs=List(x46158, x46167)).name("x46168").ctrl(x46191).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x46158,x46167)
    val x46169 = OpDef(op=FixLeq, inputs=List(Const(1008), b29416)).name("x46169").ctrl(x46191).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29416)
    // x46170 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46170_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x46170_int1").ctrl(x46191).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46170_int2 = OpDef(op=FixSla, inputs=List(x46170_int1, Const(24))).name("x46170_int2").ctrl(x46191).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46170_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x46170_frac1").ctrl(x46191).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46170_frac2 = OpDef(op=FixSla, inputs=List(x46170_frac1, Const(24))).name("x46170_frac2").ctrl(x46191).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46170 = OpDef(op=BitOr, inputs=List(x46170_int2, x46170_frac2)).name("x46170").ctrl(x46191).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x46171 = OpDef(op=FixAdd, inputs=List(x46168, x46170)).name("x46171").ctrl(x46191).srcCtx("CellsPar.scala:218:87") // FixAdd(x46168,x46170)
    val x46172 = OpDef(op=FixSra, inputs=List(x46171, Const(1))).name("x46172").ctrl(x46191).srcCtx("CellsPar.scala:29:22") // FixRsh(x46171,Const(1))
    val x46173 = x46172 // FixConvert(x46172,TRUE,_8,_24) (Same Type. No op)
    val x46174 = OpDef(op=FixAbs, inputs=List(x46173)).name("x46174").ctrl(x46191).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46173)
    val x46175 = OpDef(op=FixLt, inputs=List(Const(2.5), x46174)).name("x46175").ctrl(x46191).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46174)
    val x46176 = OpDef(op=FixLt, inputs=List(Const(0.5), x46174)).name("x46176").ctrl(x46191).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46174)
    val x46177 = OpDef(op=FixLeq, inputs=List(x46174, Const(2.5))).name("x46177").ctrl(x46191).srcCtx("CellsPar.scala:54:52") // FixLeq(x46174,Const(2.5))
    val x46178 = OpDef(op=BitAnd, inputs=List(x46176, x46177)).name("x46178").ctrl(x46191).srcCtx("CellsPar.scala:54:43:cond2") // And(x46176,x46177)
    val x46179 = OpDef(op=FixSra, inputs=List(x46174, Const(2))).name("x46179").ctrl(x46191).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46174,Const(2))
    val x46180 = OpDef(op=FixAdd, inputs=List(x46179, Const(0.375))).name("x46180").ctrl(x46191).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46179,Const(0.375))
    val x46181 = OpDef(op=MuxOp, inputs=List(x46178, x46180, x46174)).name("x46181").ctrl(x46191).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46178,x46180,x46174)
    val x46182 = OpDef(op=MuxOp, inputs=List(x46175, Const(1.0), x46181)).name("x46182").ctrl(x46191).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46175,Const(1),x46181)
    val x46183 = OpDef(op=FixNeg, inputs=List(x46182)).name("x46183").ctrl(x46191).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46182)
    val x46184 = OpDef(op=FixLt, inputs=List(x46173, Const(0.0))).name("x46184").ctrl(x46191).srcCtx("CellsPar.scala:63:22") // FixLt(x46173,Const(0))
    val x46185 = OpDef(op=MuxOp, inputs=List(x46184, x46183, x46182)).name("x46185").ctrl(x46191).srcCtx("CellsPar.scala:63:17:re") // Mux(x46184,x46183,x46182)
    val x46186 = x46185 // FixConvert(x46185,TRUE,_8,_24) (Same Type. No op)
    val x46187 = OpDef(op=FixAdd, inputs=List(x46186, Const(1.0))).name("x46187").ctrl(x46191).srcCtx("CellsPar.scala:29:33") // FixAdd(x46186,Const(1))
    val x46188 = OpDef(op=FixSra, inputs=List(x46187, Const(1))).name("x46188").ctrl(x46191).srcCtx("CellsPar.scala:29:44") // FixRsh(x46187,Const(1))
    val x46189 = OpDef(op=MuxOp, inputs=List(x46169, x46188, x46168)).name("x46189").ctrl(x46191).srcCtx("CellsPar.scala:218:43") // Mux(x46169,x46188,x46168)
    val x46190 = StoreBanks(List(x45535_d0_b0, x45535_d1_b0), List(b29461, x46157), x46189).name("x46190").ctrl(x46191).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45535,ArrayBuffer(Const(1), Const(512)),List(b29461, x46157),Const(0),x46189,x46164)
    val x46195 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46195").ctrl(x46270).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46196 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46196").ctrl(x46270).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46197 = CounterChain(List(x46196,x46195)).name("x46197").ctrl(x46270).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x46196, x46195))
    val x46230 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46197).name("x46230").ctrl(x46270).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b29006),x46197,Block(Const(())),List(List(b29540), List(b29541)),List(List(b29542), List(b29543)))
    val b29540 = CounterIter(x46196, Some(0)).name("b29540").ctrl(x46230) // b29540
    val b29542 = Const(true).name("b29542").ctrl(x46230) // b29542
    val b29541 = CounterIter(x46195, None).name("b29541").ctrl(x46230) // b29541
    val b29543 = Const(true).name("b29543").ctrl(x46230) // b29543
    val x46198 = OpDef(op=BitAnd, inputs=List(b29542, b29543)).name("x46198").ctrl(x46230).srcCtx("UnrollingBase.scala:28:66") // And(b29542,b29543)
    val x46199 = OpDef(op=BitAnd, inputs=List(x46198, b29006)).name("x46199").ctrl(x46230).srcCtx("UnrollingBase.scala:28:66") // And(x46198,b29006)
    val x46200 = LoadBanks(List(x45531_d0_b0), List(b29540, b29541)).name("x46200").ctrl(x46230).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x45531,List(List(b29540, b29541)),List(x46199))
    val x46201 = x46200 // x46201 = VectorApply(x46200,0)
    val x46202 = LoadBanks(List(x45534_d0_b0), List(b29540, b29541)).name("x46202").ctrl(x46230).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x45534,List(List(b29540, b29541)),List(x46199))
    val x46203 = x46202 // x46203 = VectorApply(x46202,0)
    val x46204 = OpDef(op=FixMul, inputs=List(x46201, x46203)).name("x46204").ctrl(x46230).srcCtx("CellsPar.scala:244:28") // FixMul(x46201,x46203)
    val x46205 = LoadBanks(List(x45532_d0_b0), List(b29540, b29541)).name("x46205").ctrl(x46230).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x45532,List(List(b29540, b29541)),List(x46199))
    val x46206 = x46205 // x46206 = VectorApply(x46205,0)
    val x46207 = LoadBanks(List(x45533_d0_b0), List(b29540, b29541)).name("x46207").ctrl(x46230).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x45533,List(List(b29540, b29541)),List(x46199))
    val x46208 = x46207 // x46208 = VectorApply(x46207,0)
    val x46209 = OpDef(op=FixMul, inputs=List(x46206, x46208)).name("x46209").ctrl(x46230).srcCtx("CellsPar.scala:244:52") // FixMul(x46206,x46208)
    val x46210 = OpDef(op=FixAdd, inputs=List(x46204, x46209)).name("x46210").ctrl(x46230).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x46204,x46209)
    val x46211 = x46210 // FixConvert(x46210,TRUE,_8,_24) (Same Type. No op)
    val x46212 = OpDef(op=FixAbs, inputs=List(x46211)).name("x46212").ctrl(x46230).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46211)
    val x46213 = OpDef(op=FixLt, inputs=List(Const(2.5), x46212)).name("x46213").ctrl(x46230).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46212)
    val x46214 = OpDef(op=FixLt, inputs=List(Const(0.5), x46212)).name("x46214").ctrl(x46230).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46212)
    val x46215 = OpDef(op=FixLeq, inputs=List(x46212, Const(2.5))).name("x46215").ctrl(x46230).srcCtx("CellsPar.scala:54:52") // FixLeq(x46212,Const(2.5))
    val x46216 = OpDef(op=BitAnd, inputs=List(x46214, x46215)).name("x46216").ctrl(x46230).srcCtx("CellsPar.scala:54:43:cond2") // And(x46214,x46215)
    val x46217 = OpDef(op=FixSra, inputs=List(x46212, Const(2))).name("x46217").ctrl(x46230).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46212,Const(2))
    val x46218 = OpDef(op=FixAdd, inputs=List(x46217, Const(0.375))).name("x46218").ctrl(x46230).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46217,Const(0.375))
    val x46219 = OpDef(op=MuxOp, inputs=List(x46216, x46218, x46212)).name("x46219").ctrl(x46230).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46216,x46218,x46212)
    val x46220 = OpDef(op=MuxOp, inputs=List(x46213, Const(1.0), x46219)).name("x46220").ctrl(x46230).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46213,Const(1),x46219)
    val x46221 = OpDef(op=FixNeg, inputs=List(x46220)).name("x46221").ctrl(x46230).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46220)
    val x46222 = OpDef(op=FixLt, inputs=List(x46211, Const(0.0))).name("x46222").ctrl(x46230).srcCtx("CellsPar.scala:63:22") // FixLt(x46211,Const(0))
    val x46223 = OpDef(op=MuxOp, inputs=List(x46222, x46221, x46220)).name("x46223").ctrl(x46230).srcCtx("CellsPar.scala:63:17:re") // Mux(x46222,x46221,x46220)
    val x46224 = x46223 // FixConvert(x46223,TRUE,_8,_24) (Same Type. No op)
    val x46225 = LoadBanks(List(x45535_d0_b0), List(b29540, b29541)).name("x46225").ctrl(x46230).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x45535,List(List(b29540, b29541)),List(x46199))
    val x46226 = x46225 // x46226 = VectorApply(x46225,0)
    val x46227 = OpDef(op=FixMul, inputs=List(x46224, x46226)).name("x46227").ctrl(x46230).srcCtx("CellsPar.scala:245:39") // FixMul(x46224,x46226)
    val x46228 = StoreBanks(List(x45530_d0_b0, x45530_d5_b0, x45530_d1_b0, x45530_d6_b0, x45530_d2_b0, x45530_d7_b0, x45530_d3_b0, x45530_d8_b0, x45530_d4_b0), List(b29540, b29541), x46227).name("x46228").ctrl(x46230).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x45530,List(List(b29540, b29541)),List(x46227),List(x46199))
    val x46229 = StoreBanks(List(x45531_d0_b0), List(b29540, b29541), x46210).name("x46229").ctrl(x46230).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x45531,List(List(b29540, b29541)),List(x46210),List(x46199))
    val x46231 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46231").ctrl(x46270).srcCtx("NMTDSE.scala:446:69") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46232 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46232").ctrl(x46270).srcCtx("NMTDSE.scala:446:69") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46233 = CounterChain(List(x46231,x46232)).name("x46233").ctrl(x46270).srcCtx("NMTDSE.scala:446:69") // CounterChainNew(List(x46231, x46232))
    val x46269 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46233).name("x46269").ctrl(x46270).srcCtx("NMTDSE.scala:446:69") // UnrolledForeach(List(b29006),x46233,Block(Const(())),List(List(b29580), List(b29581)),List(List(b29582), List(b29583)))
    val b29580 = CounterIter(x46231, Some(0)).name("b29580").ctrl(x46269) // b29580
    val b29582 = Const(true).name("b29582").ctrl(x46269) // b29582
    val b29581 = CounterIter(x46232, Some(0)).name("b29581").ctrl(x46269) // b29581
    val b29583 = Const(true).name("b29583").ctrl(x46269) // b29583
    val b48444 = StreamOut(field="offset").name("b48444").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // x46234 = StreamOutNew(BurstCmdBus)
    isAccum(b48444) = false
    bufferDepthOf(b48444) = 1
    val b48445 = StreamOut(field="size").name("b48445").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // x46234 = StreamOutNew(BurstCmdBus)
    isAccum(b48445) = false
    bufferDepthOf(b48445) = 1
    val x46235 = StreamOut(field="data").name("x46235").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // x46235 = StreamOutNew(BurstFullDataBus())
    isAccum(x46235) = false
    bufferDepthOf(x46235) = 1
    val x46236 = StreamIn(field="ack").name("x46236").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // x46236 = StreamInNew(BurstAckBus)
    isAccum(x46236) = false
    bufferDepthOf(x46236) = 1
    val x46253 = UnitController(style=SeqPipe, level=InnerControl).name("x46253").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // UnitPipe(List(b29582, b29583, b29006),Block(x46252))
    val x46237 = OpDef(op=FixAdd, inputs=List(b29005, b29581)).name("x46237").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixAdd(b29005,b29581)
    val x46238 = b29580 // FixConvert(b29580,TRUE,_32,_0) (Same Type. No op)
    val x46239 = OpDef(op=FixSla, inputs=List(x46238, Const(11))).name("x46239").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixLsh(x46238,Const(11))
    val x46240 = x46237 // FixConvert(x46237,TRUE,_32,_0) (Same Type. No op)
    val x46241 = OpDef(op=FixSla, inputs=List(x46240, Const(9))).name("x46241").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixLsh(x46240,Const(9))
    val x46242 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x46243 = OpDef(op=FixAdd, inputs=List(x46239, x46241)).name("x46243").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixAdd(x46239,x46241)
    val x46244 = OpDef(op=FixAdd, inputs=List(x46243, x46242)).name("x46244").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixAdd(x46243,x46242)
    val x46245 = OpDef(op=FixSla, inputs=List(x46244, Const(2))).name("x46245").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixLsh(x46244,Const(2))
    val x46246 = x46245 // FixConvert(x46245,TRUE,_64,_0)
    val x46247 = DramAddress(x45147).name("x46247").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // GetDRAMAddress(x45147)
    val x46249_x46248 = OpDef(op=FixAdd, inputs=List(x46246, x46247)).name("x46249_x46248").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // FixAdd(x46246,x46247)
    // x46249 = SimpleStruct(ArrayBuffer((offset,x46248), (size,Const(2048)), (isLoad,Const(false))))
    val x46250 = OpDef(op=BitAnd, inputs=List(b29582, b29583)).name("x46250").ctrl(x46253).srcCtx("UnrollingBase.scala:28:66") // And(b29582,b29583)
    val x46251 = OpDef(op=BitAnd, inputs=List(x46250, b29006)).name("x46251").ctrl(x46253).srcCtx("UnrollingBase.scala:28:66") // And(x46250,b29006)
    val x46252_b48446_b48444 = WriteMem(b48444, x46249_x46248).name("x46252_b48446_b48444").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // StreamWrite(x46234,x46249,x46251)
    val x46252_b48447_b48445 = WriteMem(b48445, Const(2048)).name("x46252_b48447_b48445").ctrl(x46253).srcCtx("NMTDSE.scala:446:69") // StreamWrite(x46234,x46249,x46251)
    val x46254 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46254").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46255 = CounterChain(List(x46254)).name("x46255").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // CounterChainNew(List(x46254))
    val x46263 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46255).name("x46263").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // UnrolledForeach(List(b29582, b29583, b29006),x46255,Block(Const(())),List(List(b29606)),List(List(b29607)))
    val b29606 = CounterIter(x46254, None).name("b29606").ctrl(x46263) // b29606
    val b29607 = Const(true).name("b29607").ctrl(x46263) // b29607
    val x46256 = OpDef(op=BitAnd, inputs=List(b29607, b29582)).name("x46256").ctrl(x46263).srcCtx("UnrollingBase.scala:28:66") // And(b29607,b29582)
    val x46257 = OpDef(op=BitAnd, inputs=List(b29583, b29006)).name("x46257").ctrl(x46263).srcCtx("UnrollingBase.scala:28:66") // And(b29583,b29006)
    val x46258 = OpDef(op=BitAnd, inputs=List(x46256, x46257)).name("x46258").ctrl(x46263).srcCtx("UnrollingBase.scala:28:66") // And(x46256,x46257)
    val x46259 = LoadBanks(List(x45530_d4_b0), List(b29580, b29606)).name("x46259").ctrl(x46263).srcCtx("NMTDSE.scala:446:69") // ParSRAMLoad(x45530,List(List(b29580, b29606)),List(x46258))
    val x46261_x46260 = x46259 // x46260 = VectorApply(x46259,0)
    // x46261 = SimpleStruct(ArrayBuffer((_1,x46260), (_2,Const(true))))
    val x46262_x46262_x46235 = WriteMem(x46235, x46261_x46260).name("x46262_x46262_x46235").ctrl(x46263).srcCtx("NMTDSE.scala:446:69") // ParStreamWrite(x46235,List(x46261),List(x46258))
    val x46264 = FringeDenseStore(dram=List(x45147), cmdStream=List(b48444, b48445), dataStream=List(x46235), ackStream=List(x46236)).name("x46264").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // FringeDenseStore(x45147,x46234,x46235,x46236)
    val x46268 = UnitController(style=SeqPipe, level=InnerControl).name("x46268").ctrl(x46269).srcCtx("NMTDSE.scala:446:69") // UnitPipe(List(b29582, b29583, b29006),Block(Const(())))
    val x46265 = OpDef(op=BitAnd, inputs=List(b29582, b29583)).name("x46265").ctrl(x46268).srcCtx("UnrollingBase.scala:28:66") // And(b29582,b29583)
    val x46266 = OpDef(op=BitAnd, inputs=List(x46265, b29006)).name("x46266").ctrl(x46268).srcCtx("UnrollingBase.scala:28:66") // And(x46265,b29006)
    val x46267_x46267 = ReadMem(x46236).name("x46267_x46267").ctrl(x46268).srcCtx("NMTDSE.scala:446:69") // StreamRead(x46236,x46266)
    val x46823 = UnitController(style=SeqPipe, level=OuterControl).name("x46823").ctrl(x46824).srcCtx("NMTDSE.scala:449:16") // UnitPipe(List(b29006),Block(Const(())))
    val x46271_d0 = Reg(init=Some(0)).name("x46271_d0").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // x46271 = RegNew(Const(0))
    isAccum(x46271_d0) = false
    bufferDepthOf(x46271_d0) = 1
    val x46271_d1 = Reg(init=Some(0)).name("x46271_d1").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // x46271 = RegNew(Const(0))
    isAccum(x46271_d1) = false
    bufferDepthOf(x46271_d1) = 1
    val x46272_d0 = Reg(init=Some(0)).name("x46272_d0").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // x46272 = RegNew(Const(0))
    isAccum(x46272_d0) = false
    bufferDepthOf(x46272_d0) = 1
    val x46272_d1 = Reg(init=Some(0)).name("x46272_d1").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // x46272 = RegNew(Const(0))
    isAccum(x46272_d1) = false
    bufferDepthOf(x46272_d1) = 1
    val x46278 = UnitController(style=SeqPipe, level=InnerControl).name("x46278").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // UnitPipe(List(b29006),Block(Const(())))
    val x46273 = OpDef(op=FixSub, inputs=List(Const(4), b29005)).name("x46273").ctrl(x46278).srcCtx("NMTDSE.scala:450:56") // FixSub(Const(4),b29005)
    val x46274 = OpDef(op=FixSub, inputs=List(x46273, Const(1))).name("x46274").ctrl(x46278).srcCtx("NMTDSE.scala:450:67") // FixSub(x46273,Const(1))
    val x46275 = OpDef(op=FixSub, inputs=List(x46273, x46274)).name("x46275").ctrl(x46278).srcCtx("NMTDSE.scala:450:22") // FixSub(x46273,x46274)
    val x46276_x46271_d0 = WriteMem(x46271_d0, x46274).name("x46276_x46271_d0").ctrl(x46278).srcCtx("NMTDSE.scala:449:16") // RegWrite(x46271,x46274,b29006)
    val x46276_x46271_d1 = WriteMem(x46271_d1, x46274).name("x46276_x46271_d1").ctrl(x46278).srcCtx("NMTDSE.scala:449:16") // RegWrite(x46271,x46274,b29006)
    val x46277_x46272_d0 = WriteMem(x46272_d0, x46275).name("x46277_x46272_d0").ctrl(x46278).srcCtx("NMTDSE.scala:449:16") // RegWrite(x46272,x46275,b29006)
    val x46277_x46272_d1 = WriteMem(x46272_d1, x46275).name("x46277_x46272_d1").ctrl(x46278).srcCtx("NMTDSE.scala:449:16") // RegWrite(x46272,x46275,b29006)
    val x46279 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46279").ctrl(x46823).srcCtx("NMTDSE.scala:450:22") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46280 = ReadMem(x46272_d1).name("x46280").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // RegRead(x46272)
    val x46281 = Counter(min=Const(0), max=x46280, step=Const(1), par=1).name("x46281").ctrl(x46823).srcCtx("NMTDSE.scala:450:22") // CounterNew(Const(0),x46280,Const(1),Const(1))
    val x46282 = CounterChain(List(x46279,x46281)).name("x46282").ctrl(x46823).srcCtx("NMTDSE.scala:450:22") // CounterChainNew(List(x46279, x46281))
    val x46313 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46282).name("x46313").ctrl(x46823).srcCtx("NMTDSE.scala:450:22") // UnrolledForeach(List(b29006),x46282,Block(Const(())),List(List(b29635), List(b29636)),List(List(b29637), List(b29638)))
    val b29635 = CounterIter(x46279, Some(0)).name("b29635").ctrl(x46313) // b29635
    val b29637 = Const(true).name("b29637").ctrl(x46313) // b29637
    val b29636 = CounterIter(x46281, Some(0)).name("b29636").ctrl(x46313) // b29636
    val b29638 = Const(true).name("b29638").ctrl(x46313) // b29638
    val b48448 = StreamOut(field="offset").name("b48448").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // x46283 = StreamOutNew(BurstCmdBus)
    isAccum(b48448) = false
    bufferDepthOf(b48448) = 1
    val b48449 = StreamOut(field="size").name("b48449").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // x46283 = StreamOutNew(BurstCmdBus)
    isAccum(b48449) = false
    bufferDepthOf(b48449) = 1
    val x46284 = StreamIn(field="data").name("x46284").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // x46284 = StreamInNew(BurstDataBus())
    isAccum(x46284) = false
    bufferDepthOf(x46284) = 1
    val x46302 = UnitController(style=SeqPipe, level=InnerControl).name("x46302").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // UnitPipe(List(b29637, b29638, b29006),Block(x46301))
    val x46285 = ReadMem(x46271_d1).name("x46285").ctrl(x46302).srcCtx("NMTDSE.scala:449:16") // RegRead(x46271)
    val x46286 = OpDef(op=FixAdd, inputs=List(x46285, b29636)).name("x46286").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixAdd(x46285,b29636)
    val x46287 = b29635 // FixConvert(b29635,TRUE,_32,_0) (Same Type. No op)
    val x46288 = OpDef(op=FixSla, inputs=List(x46287, Const(11))).name("x46288").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixLsh(x46287,Const(11))
    val x46289 = x46286 // FixConvert(x46286,TRUE,_32,_0) (Same Type. No op)
    val x46290 = OpDef(op=FixSla, inputs=List(x46289, Const(9))).name("x46290").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixLsh(x46289,Const(9))
    val x46291 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x46292 = OpDef(op=FixAdd, inputs=List(x46288, x46290)).name("x46292").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixAdd(x46288,x46290)
    val x46293 = OpDef(op=FixAdd, inputs=List(x46292, x46291)).name("x46293").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixAdd(x46292,x46291)
    val x46294 = OpDef(op=FixSla, inputs=List(x46293, Const(2))).name("x46294").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixLsh(x46293,Const(2))
    val x46295 = x46294 // FixConvert(x46294,TRUE,_64,_0)
    val x46296 = DramAddress(x45139).name("x46296").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // GetDRAMAddress(x45139)
    val x46298_x46297 = OpDef(op=FixAdd, inputs=List(x46295, x46296)).name("x46298_x46297").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // FixAdd(x46295,x46296)
    // x46298 = SimpleStruct(ArrayBuffer((offset,x46297), (size,Const(2048)), (isLoad,Const(true))))
    val x46299 = OpDef(op=BitAnd, inputs=List(b29637, b29638)).name("x46299").ctrl(x46302).srcCtx("UnrollingBase.scala:28:66") // And(b29637,b29638)
    val x46300 = OpDef(op=BitAnd, inputs=List(x46299, b29006)).name("x46300").ctrl(x46302).srcCtx("UnrollingBase.scala:28:66") // And(x46299,b29006)
    val x46301_b48450_b48448 = WriteMem(b48448, x46298_x46297).name("x46301_b48450_b48448").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // StreamWrite(x46283,x46298,x46300)
    val x46301_b48451_b48449 = WriteMem(b48449, Const(2048)).name("x46301_b48451_b48449").ctrl(x46302).srcCtx("NMTDSE.scala:450:22") // StreamWrite(x46283,x46298,x46300)
    val x46303 = FringeDenseLoad(dram=List(x45139), cmdStream=List(b48448, b48449), dataStream=List(x46284)).name("x46303").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // FringeDenseLoad(x45139,x46283,x46284)
    val x46304 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46304").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46305 = CounterChain(List(x46304)).name("x46305").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // CounterChainNew(List(x46304))
    val x46312 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46305).name("x46312").ctrl(x46313).srcCtx("NMTDSE.scala:450:22") // UnrolledForeach(List(b29637, b29638, b29006),x46305,Block(Const(())),List(List(b29662)),List(List(b29663)))
    val b29662 = CounterIter(x46304, None).name("b29662").ctrl(x46312) // b29662
    val b29663 = Const(true).name("b29663").ctrl(x46312) // b29663
    val x46306 = OpDef(op=BitAnd, inputs=List(b29663, b29637)).name("x46306").ctrl(x46312).srcCtx("UnrollingBase.scala:28:66") // And(b29663,b29637)
    val x46307 = OpDef(op=BitAnd, inputs=List(b29638, b29006)).name("x46307").ctrl(x46312).srcCtx("UnrollingBase.scala:28:66") // And(b29638,b29006)
    val x46308 = OpDef(op=BitAnd, inputs=List(x46306, x46307)).name("x46308").ctrl(x46312).srcCtx("UnrollingBase.scala:28:66") // And(x46306,x46307)
    val x46309_x46309 = ReadMem(x46284).name("x46309_x46309").ctrl(x46312).srcCtx("NMTDSE.scala:450:22") // ParStreamRead(x46284,List(x46308))
    val x46310_x46310 = x46309_x46309 // x46310 = VectorApply(x46309,0)
    val x46311 = StoreBanks(List(x45537_d0_b0, x45537_d1_b0, x45537_d2_b0, x45537_d3_b0), List(b29635, b29662), x46310_x46310).name("x46311").ctrl(x46312).srcCtx("NMTDSE.scala:450:22") // ParSRAMStore(x45537,List(List(b29635, b29662)),List(x46310),List(x46308))
    val x46314 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x46314").ctrl(x46823).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x46315 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x46315").ctrl(x46823).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x46316 = CounterChain(List(x46315,x46314)).name("x46316").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x46315, x46314))
    val x46421 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46316).name("x46421").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x46316,Block(Const(())),List(List(b29675), List(b29676)),List(List(b29677), List(b29678)))
    val b29675 = CounterIter(x46315, Some(0)).name("b29675").ctrl(x46421) // b29675
    val b29677 = Const(true).name("b29677").ctrl(x46421) // b29677
    val b29676 = CounterIter(x46314, Some(0)).name("b29676").ctrl(x46421) // b29676
    val b29678 = Const(true).name("b29678").ctrl(x46421) // b29678
    val x46317_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x46317_d0_b0").ctrl(x46421).srcCtx("CellsPar.scala:191:33:tileKernel") // x46317 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x46317_d0_b0) = false
    bufferDepthOf(x46317_d0_b0) = 2
    val x46320 = UnitController(style=SeqPipe, level=InnerControl).name("x46320").ctrl(x46421).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29677, b29678, b29006),Block(Const(())))
    val x46318 = OpDef(op=FixAdd, inputs=List(b29675, Const(16))).name("x46318").ctrl(x46320).srcCtx("CellsPar.scala:192:36") // FixAdd(b29675,Const(16))
    val x46319 = OpDef(op=FixAdd, inputs=List(b29676, Const(16))).name("x46319").ctrl(x46320).srcCtx("CellsPar.scala:192:45") // FixAdd(b29676,Const(16))
    val x46321 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46321").ctrl(x46421).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46322 = CounterChain(List(x46321)).name("x46322").ctrl(x46421).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46321))
    val x46351 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46322).name("x46351").ctrl(x46421).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29677, b29678, b29006),x46322,Block(Const(())),List(List(b29685)),List(List(b29686)))
    val b29685 = CounterIter(x46321, Some(0)).name("b29685").ctrl(x46351) // b29685
    val b29686 = Const(true).name("b29686").ctrl(x46351) // b29686
    val b48452 = StreamOut(field="offset").name("b48452").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // x46323 = StreamOutNew(BurstCmdBus)
    isAccum(b48452) = false
    bufferDepthOf(b48452) = 1
    val b48453 = StreamOut(field="size").name("b48453").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // x46323 = StreamOutNew(BurstCmdBus)
    isAccum(b48453) = false
    bufferDepthOf(b48453) = 1
    val x46324 = StreamIn(field="data").name("x46324").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // x46324 = StreamInNew(BurstDataBus())
    isAccum(x46324) = false
    bufferDepthOf(x46324) = 1
    val x46339 = UnitController(style=SeqPipe, level=InnerControl).name("x46339").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29686, b29677, b29678, b29006),Block(x46338))
    val x46325 = OpDef(op=FixAdd, inputs=List(b29675, b29685)).name("x46325").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // FixAdd(b29675,b29685)
    val x46326 = x46325 // FixConvert(x46325,TRUE,_32,_0) (Same Type. No op)
    val x46327 = OpDef(op=FixSla, inputs=List(x46326, Const(9))).name("x46327").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // FixLsh(x46326,Const(9))
    val x46328 = b29676 // FixConvert(b29676,TRUE,_32,_0) (Same Type. No op)
    val x46329 = OpDef(op=FixAdd, inputs=List(x46327, x46328)).name("x46329").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // FixAdd(x46327,x46328)
    val x46330 = OpDef(op=FixSla, inputs=List(x46329, Const(2))).name("x46330").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // FixLsh(x46329,Const(2))
    val x46331 = x46330 // FixConvert(x46330,TRUE,_64,_0)
    val x46332 = DramAddress(x45253).name("x46332").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45253)
    val x46334_x46333 = OpDef(op=FixAdd, inputs=List(x46331, x46332)).name("x46334_x46333").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // FixAdd(x46331,x46332)
    // x46334 = SimpleStruct(ArrayBuffer((offset,x46333), (size,Const(64)), (isLoad,Const(true))))
    val x46335 = OpDef(op=BitAnd, inputs=List(b29686, b29677)).name("x46335").ctrl(x46339).srcCtx("UnrollingBase.scala:28:66") // And(b29686,b29677)
    val x46336 = OpDef(op=BitAnd, inputs=List(b29678, b29006)).name("x46336").ctrl(x46339).srcCtx("UnrollingBase.scala:28:66") // And(b29678,b29006)
    val x46337 = OpDef(op=BitAnd, inputs=List(x46335, x46336)).name("x46337").ctrl(x46339).srcCtx("UnrollingBase.scala:28:66") // And(x46335,x46336)
    val x46338_b48454_b48452 = WriteMem(b48452, x46334_x46333).name("x46338_b48454_b48452").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46323,x46334,x46337)
    val x46338_b48455_b48453 = WriteMem(b48453, Const(64)).name("x46338_b48455_b48453").ctrl(x46339).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46323,x46334,x46337)
    val x46340 = FringeDenseLoad(dram=List(x45253), cmdStream=List(b48452, b48453), dataStream=List(x46324)).name("x46340").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45253,x46323,x46324)
    val x46341 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46341").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46342 = CounterChain(List(x46341)).name("x46342").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46341))
    val x46350 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46342).name("x46350").ctrl(x46351).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29686, b29677, b29678, b29006),x46342,Block(Const(())),List(List(b29707)),List(List(b29708)))
    val b29707 = CounterIter(x46341, None).name("b29707").ctrl(x46350) // b29707
    val b29708 = Const(true).name("b29708").ctrl(x46350) // b29708
    val x46343 = OpDef(op=BitAnd, inputs=List(b29708, b29686)).name("x46343").ctrl(x46350).srcCtx("UnrollingBase.scala:28:66") // And(b29708,b29686)
    val x46344 = OpDef(op=BitAnd, inputs=List(b29677, b29678)).name("x46344").ctrl(x46350).srcCtx("UnrollingBase.scala:28:66") // And(b29677,b29678)
    val x46345 = OpDef(op=BitAnd, inputs=List(x46343, x46344)).name("x46345").ctrl(x46350).srcCtx("UnrollingBase.scala:28:66") // And(x46343,x46344)
    val x46346 = OpDef(op=BitAnd, inputs=List(x46345, b29006)).name("x46346").ctrl(x46350).srcCtx("UnrollingBase.scala:28:66") // And(x46345,b29006)
    val x46347_x46347 = ReadMem(x46324).name("x46347_x46347").ctrl(x46350).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x46324,List(x46346))
    val x46348_x46348 = x46347_x46347 // x46348 = VectorApply(x46347,0)
    val x46349 = StoreBanks(List(x46317_d0_b0), List(b29685, b29707), x46348_x46348).name("x46349").ctrl(x46350).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x46317,List(List(b29685, b29707)),List(x46348),List(x46346))
    val x46352 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46352").ctrl(x46421).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46353 = CounterChain(List(x46352)).name("x46353").ctrl(x46421).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x46352))
    val x46420 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46353).name("x46420").ctrl(x46421).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29677, b29678, b29006),x46353,Block(Const(())),List(List(b29720)),List(List(b29721)))
    val b29720 = CounterIter(x46352, Some(0)).name("b29720").ctrl(x46420) // b29720
    val b29721 = Const(true).name("b29721").ctrl(x46420) // b29721
    val x46354 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46354").ctrl(x46420).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46355 = CounterChain(List(x46354)).name("x46355").ctrl(x46420).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x46354))
    val x46419 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46355).name("x46419").ctrl(x46420).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29721, b29677, b29678, b29006),x46355,Block(Const(())),List(List(b29724)),List(List(b29725)))
    val b29724 = CounterIter(x46354, Some(0)).name("b29724").ctrl(x46419) // b29724
    val b29725 = Const(true).name("b29725").ctrl(x46419) // b29725
    val x46356_d0 = Reg(init=Some(0.0)).name("x46356_d0").ctrl(x46419).srcCtx("CellsPar.scala:195:34:prod") // x46356 = RegNew(Const(0))
    isAccum(x46356_d0) = false
    bufferDepthOf(x46356_d0) = 2
    val x46356_d1 = Reg(init=Some(0.0)).name("x46356_d1").ctrl(x46419).srcCtx("CellsPar.scala:195:34:prod") // x46356 = RegNew(Const(0))
    isAccum(x46356_d1) = true
    bufferDepthOf(x46356_d1) = 1
    val x46357 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46357").ctrl(x46419).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46358 = CounterChain(List(x46357)).name("x46358").ctrl(x46419).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x46357))
    val x46384 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46358).name("x46384").ctrl(x46419).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29725, b29721, b29677, b29678, b29006),x46358,x46356,Block((x46356) => Const(())),List(List(b29729)),List(List(b29730)))
    val b29729 = CounterIter(x46357, None).name("b29729").ctrl(x46384) // b29729
    val b29730 = Const(true).name("b29730").ctrl(x46384) // b29730
    val x46359 = OpDef(op=FixAdd, inputs=List(b29675, b29729)).name("x46359").ctrl(x46384).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29675,b29729)
    val x46360 = OpDef(op=BitAnd, inputs=List(b29730, b29725)).name("x46360").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(b29730,b29725)
    val x46361 = OpDef(op=BitAnd, inputs=List(b29721, b29677)).name("x46361").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(b29721,b29677)
    val x46362 = OpDef(op=BitAnd, inputs=List(b29678, b29006)).name("x46362").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(b29678,b29006)
    val x46363 = OpDef(op=BitAnd, inputs=List(x46360, x46361)).name("x46363").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(x46360,x46361)
    val x46364 = OpDef(op=BitAnd, inputs=List(x46363, x46362)).name("x46364").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(x46363,x46362)
    val x46365 = LoadBanks(List(x46317_d0_b0), List(b29729, b29724)).name("x46365").ctrl(x46384).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x46317,List(List(b29729, b29724)),List(x46364))
    val x46366 = x46365 // x46366 = VectorApply(x46365,0)
    val x46367 = LoadBanks(List(x45537_d3_b0), List(b29720, x46359)).name("x46367").ctrl(x46384).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45537,List(List(b29720, x46359)),List(x46364))
    val x46368 = x46367 // x46368 = VectorApply(x46367,0)
    val x46369 = OpDef(op=FixMul, inputs=List(x46368, x46366)).name("x46369").ctrl(x46384).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x46368,x46366)
    val x46370 = OpDef(op=FixSub, inputs=List(x46359, Const(512))).name("x46370").ctrl(x46384).srcCtx("CellsPar.scala:205:51") // FixSub(x46359,Const(512))
    val x46371 = LoadBanks(List(x45538_d8_b0), List(b29720, x46370)).name("x46371").ctrl(x46384).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45538,List(List(b29720, x46370)),List(x46364))
    val x46372 = x46371 // x46372 = VectorApply(x46371,0)
    val x46373 = OpDef(op=FixMul, inputs=List(x46372, x46366)).name("x46373").ctrl(x46384).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x46372,x46366)
    val x46374 = OpDef(op=FixLt, inputs=List(x46359, Const(512))).name("x46374").ctrl(x46384).srcCtx("CellsPar.scala:206:38") // FixLt(x46359,Const(512))
    val x46375 = OpDef(op=MuxOp, inputs=List(x46374, x46369, x46373)).name("x46375").ctrl(x46384).srcCtx("CellsPar.scala:206:18") // Mux(x46374,x46369,x46373)
    val x46376 = ReadMem(x46356_d1).name("x46376").ctrl(x46384).srcCtx("CellsPar.scala:207:15") // RegRead(x46356)
    val x46377 = OpDef(op=FixEql, inputs=List(b29729, Const(0))).name("x46377").ctrl(x46384).srcCtx("CellsPar.scala:207:15") // FixEql(b29729,Const(0))
    val x46378 = ReduceAccumOp(op=FixAdd, input=x46375, accum=x46376).name("x46378").ctrl(x46384).srcCtx("CellsPar.scala:207:17") // FixAdd(x46375,x46376)
    val x46379 = OpDef(op=BitAnd, inputs=List(b29725, b29721)).name("x46379").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(b29725,b29721)
    val x46380 = OpDef(op=BitAnd, inputs=List(b29677, b29678)).name("x46380").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(b29677,b29678)
    val x46381 = OpDef(op=BitAnd, inputs=List(x46379, x46380)).name("x46381").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(x46379,x46380)
    val x46382 = OpDef(op=BitAnd, inputs=List(x46381, b29006)).name("x46382").ctrl(x46384).srcCtx("UnrollingBase.scala:28:66") // And(x46381,b29006)
    val x46383_x46356_d0 = WriteMem(x46356_d0, x46378).name("x46383_x46356_d0").ctrl(x46384).srcCtx("CellsPar.scala:207:15") // RegWrite(x46356,x46378,x46382)
    val x46383_x46356_d1 = WriteMem(x46356_d1, x46378).name("x46383_x46356_d1").ctrl(x46384).srcCtx("CellsPar.scala:207:15") // RegWrite(x46356,x46378,x46382)
    val x46418 = UnitController(style=SeqPipe, level=InnerControl).name("x46418").ctrl(x46419).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29725, b29721, b29677, b29678, b29006),Block(Const(())))
    val x46385 = OpDef(op=FixAdd, inputs=List(b29676, b29724)).name("x46385").ctrl(x46418).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29676,b29724)
    val x46386 = ReadMem(x46356_d0).name("x46386").ctrl(x46418).srcCtx("CellsPar.scala:210:28") // RegRead(x46356)
    val x46387 = OpDef(op=FixEql, inputs=List(b29675, Const(0))).name("x46387").ctrl(x46418).srcCtx("CellsPar.scala:210:42") // FixEql(b29675,Const(0))
    val x46388 = OpDef(op=BitAnd, inputs=List(b29725, b29721)).name("x46388").ctrl(x46418).srcCtx("UnrollingBase.scala:28:66") // And(b29725,b29721)
    val x46389 = OpDef(op=BitAnd, inputs=List(b29677, b29678)).name("x46389").ctrl(x46418).srcCtx("UnrollingBase.scala:28:66") // And(b29677,b29678)
    val x46390 = OpDef(op=BitAnd, inputs=List(x46388, x46389)).name("x46390").ctrl(x46418).srcCtx("UnrollingBase.scala:28:66") // And(x46388,x46389)
    val x46391 = OpDef(op=BitAnd, inputs=List(x46390, b29006)).name("x46391").ctrl(x46418).srcCtx("UnrollingBase.scala:28:66") // And(x46390,b29006)
    val x46392 = LoadBanks(List(x45544_d3_b0), List(Const(0), x46385)).name("x46392").ctrl(x46418).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45544,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46385),Const(0),x46391)
    val x46393 = LoadBanks(List(x45540_d1_b0), List(b29720, x46385)).name("x46393").ctrl(x46418).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45540,ArrayBuffer(Const(1), Const(512)),List(b29720, x46385),Const(0),x46391)
    val x46394 = OpDef(op=MuxOp, inputs=List(x46387, x46392, x46393)).name("x46394").ctrl(x46418).srcCtx("CellsPar.scala:210:39") // Mux(x46387,x46392,x46393)
    val x46395 = OpDef(op=FixAdd, inputs=List(x46386, x46394)).name("x46395").ctrl(x46418).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x46386,x46394)
    val x46396 = OpDef(op=FixLeq, inputs=List(Const(1008), b29675)).name("x46396").ctrl(x46418).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29675)
    // x46397 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46397_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x46397_int1").ctrl(x46418).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46397_int2 = OpDef(op=FixSla, inputs=List(x46397_int1, Const(24))).name("x46397_int2").ctrl(x46418).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46397_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x46397_frac1").ctrl(x46418).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46397_frac2 = OpDef(op=FixSla, inputs=List(x46397_frac1, Const(24))).name("x46397_frac2").ctrl(x46418).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46397 = OpDef(op=BitOr, inputs=List(x46397_int2, x46397_frac2)).name("x46397").ctrl(x46418).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x46398 = OpDef(op=FixAdd, inputs=List(x46395, x46397)).name("x46398").ctrl(x46418).srcCtx("CellsPar.scala:218:87") // FixAdd(x46395,x46397)
    val x46399 = OpDef(op=FixSra, inputs=List(x46398, Const(1))).name("x46399").ctrl(x46418).srcCtx("CellsPar.scala:29:22") // FixRsh(x46398,Const(1))
    val x46400 = x46399 // FixConvert(x46399,TRUE,_8,_24) (Same Type. No op)
    val x46401 = OpDef(op=FixAbs, inputs=List(x46400)).name("x46401").ctrl(x46418).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46400)
    val x46402 = OpDef(op=FixLt, inputs=List(Const(2.5), x46401)).name("x46402").ctrl(x46418).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46401)
    val x46403 = OpDef(op=FixLt, inputs=List(Const(0.5), x46401)).name("x46403").ctrl(x46418).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46401)
    val x46404 = OpDef(op=FixLeq, inputs=List(x46401, Const(2.5))).name("x46404").ctrl(x46418).srcCtx("CellsPar.scala:54:52") // FixLeq(x46401,Const(2.5))
    val x46405 = OpDef(op=BitAnd, inputs=List(x46403, x46404)).name("x46405").ctrl(x46418).srcCtx("CellsPar.scala:54:43:cond2") // And(x46403,x46404)
    val x46406 = OpDef(op=FixSra, inputs=List(x46401, Const(2))).name("x46406").ctrl(x46418).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46401,Const(2))
    val x46407 = OpDef(op=FixAdd, inputs=List(x46406, Const(0.375))).name("x46407").ctrl(x46418).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46406,Const(0.375))
    val x46408 = OpDef(op=MuxOp, inputs=List(x46405, x46407, x46401)).name("x46408").ctrl(x46418).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46405,x46407,x46401)
    val x46409 = OpDef(op=MuxOp, inputs=List(x46402, Const(1.0), x46408)).name("x46409").ctrl(x46418).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46402,Const(1),x46408)
    val x46410 = OpDef(op=FixNeg, inputs=List(x46409)).name("x46410").ctrl(x46418).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46409)
    val x46411 = OpDef(op=FixLt, inputs=List(x46400, Const(0.0))).name("x46411").ctrl(x46418).srcCtx("CellsPar.scala:63:22") // FixLt(x46400,Const(0))
    val x46412 = OpDef(op=MuxOp, inputs=List(x46411, x46410, x46409)).name("x46412").ctrl(x46418).srcCtx("CellsPar.scala:63:17:re") // Mux(x46411,x46410,x46409)
    val x46413 = x46412 // FixConvert(x46412,TRUE,_8,_24) (Same Type. No op)
    val x46414 = OpDef(op=FixAdd, inputs=List(x46413, Const(1.0))).name("x46414").ctrl(x46418).srcCtx("CellsPar.scala:29:33") // FixAdd(x46413,Const(1))
    val x46415 = OpDef(op=FixSra, inputs=List(x46414, Const(1))).name("x46415").ctrl(x46418).srcCtx("CellsPar.scala:29:44") // FixRsh(x46414,Const(1))
    val x46416 = OpDef(op=MuxOp, inputs=List(x46396, x46415, x46395)).name("x46416").ctrl(x46418).srcCtx("CellsPar.scala:218:43") // Mux(x46396,x46415,x46395)
    val x46417 = StoreBanks(List(x45540_d0_b0, x45540_d1_b0), List(b29720, x46385), x46416).name("x46417").ctrl(x46418).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45540,ArrayBuffer(Const(1), Const(512)),List(b29720, x46385),Const(0),x46416,x46391)
    val x46422 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x46422").ctrl(x46823).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x46423 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x46423").ctrl(x46823).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x46424 = CounterChain(List(x46423,x46422)).name("x46424").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x46423, x46422))
    val x46527 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46424).name("x46527").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x46424,Block(Const(())),List(List(b29797), List(b29798)),List(List(b29799), List(b29800)))
    val b29797 = CounterIter(x46423, Some(0)).name("b29797").ctrl(x46527) // b29797
    val b29799 = Const(true).name("b29799").ctrl(x46527) // b29799
    val b29798 = CounterIter(x46422, Some(0)).name("b29798").ctrl(x46527) // b29798
    val b29800 = Const(true).name("b29800").ctrl(x46527) // b29800
    val x46425_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x46425_d0_b0").ctrl(x46527).srcCtx("CellsPar.scala:191:33:tileKernel") // x46425 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x46425_d0_b0) = false
    bufferDepthOf(x46425_d0_b0) = 2
    val x46428 = UnitController(style=SeqPipe, level=InnerControl).name("x46428").ctrl(x46527).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29799, b29800, b29006),Block(Const(())))
    val x46426 = OpDef(op=FixAdd, inputs=List(b29797, Const(16))).name("x46426").ctrl(x46428).srcCtx("CellsPar.scala:192:36") // FixAdd(b29797,Const(16))
    val x46427 = OpDef(op=FixAdd, inputs=List(b29798, Const(16))).name("x46427").ctrl(x46428).srcCtx("CellsPar.scala:192:45") // FixAdd(b29798,Const(16))
    val x46429 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46429").ctrl(x46527).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46430 = CounterChain(List(x46429)).name("x46430").ctrl(x46527).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46429))
    val x46459 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46430).name("x46459").ctrl(x46527).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29799, b29800, b29006),x46430,Block(Const(())),List(List(b29807)),List(List(b29808)))
    val b29807 = CounterIter(x46429, Some(0)).name("b29807").ctrl(x46459) // b29807
    val b29808 = Const(true).name("b29808").ctrl(x46459) // b29808
    val b48456 = StreamOut(field="offset").name("b48456").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // x46431 = StreamOutNew(BurstCmdBus)
    isAccum(b48456) = false
    bufferDepthOf(b48456) = 1
    val b48457 = StreamOut(field="size").name("b48457").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // x46431 = StreamOutNew(BurstCmdBus)
    isAccum(b48457) = false
    bufferDepthOf(b48457) = 1
    val x46432 = StreamIn(field="data").name("x46432").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // x46432 = StreamInNew(BurstDataBus())
    isAccum(x46432) = false
    bufferDepthOf(x46432) = 1
    val x46447 = UnitController(style=SeqPipe, level=InnerControl).name("x46447").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29808, b29799, b29800, b29006),Block(x46446))
    val x46433 = OpDef(op=FixAdd, inputs=List(b29797, b29807)).name("x46433").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // FixAdd(b29797,b29807)
    val x46434 = x46433 // FixConvert(x46433,TRUE,_32,_0) (Same Type. No op)
    val x46435 = OpDef(op=FixSla, inputs=List(x46434, Const(9))).name("x46435").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // FixLsh(x46434,Const(9))
    val x46436 = b29798 // FixConvert(b29798,TRUE,_32,_0) (Same Type. No op)
    def split2 = {
    val x46437 = OpDef(op=FixAdd, inputs=List(x46435, x46436)).name("x46437").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // FixAdd(x46435,x46436)
    val x46438 = OpDef(op=FixSla, inputs=List(x46437, Const(2))).name("x46438").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // FixLsh(x46437,Const(2))
    val x46439 = x46438 // FixConvert(x46438,TRUE,_64,_0)
    val x46440 = DramAddress(x45254).name("x46440").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45254)
    val x46442_x46441 = OpDef(op=FixAdd, inputs=List(x46439, x46440)).name("x46442_x46441").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // FixAdd(x46439,x46440)
    // x46442 = SimpleStruct(ArrayBuffer((offset,x46441), (size,Const(64)), (isLoad,Const(true))))
    val x46443 = OpDef(op=BitAnd, inputs=List(b29808, b29799)).name("x46443").ctrl(x46447).srcCtx("UnrollingBase.scala:28:66") // And(b29808,b29799)
    val x46444 = OpDef(op=BitAnd, inputs=List(b29800, b29006)).name("x46444").ctrl(x46447).srcCtx("UnrollingBase.scala:28:66") // And(b29800,b29006)
    val x46445 = OpDef(op=BitAnd, inputs=List(x46443, x46444)).name("x46445").ctrl(x46447).srcCtx("UnrollingBase.scala:28:66") // And(x46443,x46444)
    val x46446_b48458_b48456 = WriteMem(b48456, x46442_x46441).name("x46446_b48458_b48456").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46431,x46442,x46445)
    val x46446_b48459_b48457 = WriteMem(b48457, Const(64)).name("x46446_b48459_b48457").ctrl(x46447).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46431,x46442,x46445)
    val x46448 = FringeDenseLoad(dram=List(x45254), cmdStream=List(b48456, b48457), dataStream=List(x46432)).name("x46448").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45254,x46431,x46432)
    val x46449 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46449").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46450 = CounterChain(List(x46449)).name("x46450").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46449))
    val x46458 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46450).name("x46458").ctrl(x46459).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29808, b29799, b29800, b29006),x46450,Block(Const(())),List(List(b29829)),List(List(b29830)))
    val b29829 = CounterIter(x46449, None).name("b29829").ctrl(x46458) // b29829
    val b29830 = Const(true).name("b29830").ctrl(x46458) // b29830
    val x46451 = OpDef(op=BitAnd, inputs=List(b29830, b29808)).name("x46451").ctrl(x46458).srcCtx("UnrollingBase.scala:28:66") // And(b29830,b29808)
    val x46452 = OpDef(op=BitAnd, inputs=List(b29799, b29800)).name("x46452").ctrl(x46458).srcCtx("UnrollingBase.scala:28:66") // And(b29799,b29800)
    val x46453 = OpDef(op=BitAnd, inputs=List(x46451, x46452)).name("x46453").ctrl(x46458).srcCtx("UnrollingBase.scala:28:66") // And(x46451,x46452)
    val x46454 = OpDef(op=BitAnd, inputs=List(x46453, b29006)).name("x46454").ctrl(x46458).srcCtx("UnrollingBase.scala:28:66") // And(x46453,b29006)
    val x46455_x46455 = ReadMem(x46432).name("x46455_x46455").ctrl(x46458).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x46432,List(x46454))
    val x46456_x46456 = x46455_x46455 // x46456 = VectorApply(x46455,0)
    val x46457 = StoreBanks(List(x46425_d0_b0), List(b29807, b29829), x46456_x46456).name("x46457").ctrl(x46458).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x46425,List(List(b29807, b29829)),List(x46456),List(x46454))
    val x46460 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46460").ctrl(x46527).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46461 = CounterChain(List(x46460)).name("x46461").ctrl(x46527).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x46460))
    val x46526 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46461).name("x46526").ctrl(x46527).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29799, b29800, b29006),x46461,Block(Const(())),List(List(b29842)),List(List(b29843)))
    val b29842 = CounterIter(x46460, Some(0)).name("b29842").ctrl(x46526) // b29842
    val b29843 = Const(true).name("b29843").ctrl(x46526) // b29843
    val x46462 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46462").ctrl(x46526).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46463 = CounterChain(List(x46462)).name("x46463").ctrl(x46526).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x46462))
    val x46525 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46463).name("x46525").ctrl(x46526).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29843, b29799, b29800, b29006),x46463,Block(Const(())),List(List(b29846)),List(List(b29847)))
    val b29846 = CounterIter(x46462, Some(0)).name("b29846").ctrl(x46525) // b29846
    val b29847 = Const(true).name("b29847").ctrl(x46525) // b29847
    val x46464_d0 = Reg(init=Some(0.0)).name("x46464_d0").ctrl(x46525).srcCtx("CellsPar.scala:195:34:prod") // x46464 = RegNew(Const(0))
    isAccum(x46464_d0) = false
    bufferDepthOf(x46464_d0) = 2
    val x46464_d1 = Reg(init=Some(0.0)).name("x46464_d1").ctrl(x46525).srcCtx("CellsPar.scala:195:34:prod") // x46464 = RegNew(Const(0))
    isAccum(x46464_d1) = true
    bufferDepthOf(x46464_d1) = 1
    val x46465 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46465").ctrl(x46525).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46466 = CounterChain(List(x46465)).name("x46466").ctrl(x46525).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x46465))
    val x46492 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46466).name("x46492").ctrl(x46525).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29847, b29843, b29799, b29800, b29006),x46466,x46464,Block((x46464) => Const(())),List(List(b29851)),List(List(b29852)))
    val b29851 = CounterIter(x46465, None).name("b29851").ctrl(x46492) // b29851
    val b29852 = Const(true).name("b29852").ctrl(x46492) // b29852
    val x46467 = OpDef(op=FixAdd, inputs=List(b29797, b29851)).name("x46467").ctrl(x46492).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29797,b29851)
    val x46468 = OpDef(op=BitAnd, inputs=List(b29852, b29847)).name("x46468").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(b29852,b29847)
    val x46469 = OpDef(op=BitAnd, inputs=List(b29843, b29799)).name("x46469").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(b29843,b29799)
    val x46470 = OpDef(op=BitAnd, inputs=List(b29800, b29006)).name("x46470").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(b29800,b29006)
    val x46471 = OpDef(op=BitAnd, inputs=List(x46468, x46469)).name("x46471").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(x46468,x46469)
    val x46472 = OpDef(op=BitAnd, inputs=List(x46471, x46470)).name("x46472").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(x46471,x46470)
    val x46473 = LoadBanks(List(x46425_d0_b0), List(b29851, b29846)).name("x46473").ctrl(x46492).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x46425,List(List(b29851, b29846)),List(x46472))
    val x46474 = x46473 // x46474 = VectorApply(x46473,0)
    val x46475 = LoadBanks(List(x45537_d2_b0), List(b29842, x46467)).name("x46475").ctrl(x46492).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45537,List(List(b29842, x46467)),List(x46472))
    val x46476 = x46475 // x46476 = VectorApply(x46475,0)
    val x46477 = OpDef(op=FixMul, inputs=List(x46476, x46474)).name("x46477").ctrl(x46492).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x46476,x46474)
    val x46478 = OpDef(op=FixSub, inputs=List(x46467, Const(512))).name("x46478").ctrl(x46492).srcCtx("CellsPar.scala:205:51") // FixSub(x46467,Const(512))
    val x46479 = LoadBanks(List(x45538_d7_b0), List(b29842, x46478)).name("x46479").ctrl(x46492).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45538,List(List(b29842, x46478)),List(x46472))
    val x46480 = x46479 // x46480 = VectorApply(x46479,0)
    val x46481 = OpDef(op=FixMul, inputs=List(x46480, x46474)).name("x46481").ctrl(x46492).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x46480,x46474)
    val x46482 = OpDef(op=FixLt, inputs=List(x46467, Const(512))).name("x46482").ctrl(x46492).srcCtx("CellsPar.scala:206:38") // FixLt(x46467,Const(512))
    val x46483 = OpDef(op=MuxOp, inputs=List(x46482, x46477, x46481)).name("x46483").ctrl(x46492).srcCtx("CellsPar.scala:206:18") // Mux(x46482,x46477,x46481)
    val x46484 = ReadMem(x46464_d1).name("x46484").ctrl(x46492).srcCtx("CellsPar.scala:207:15") // RegRead(x46464)
    val x46485 = OpDef(op=FixEql, inputs=List(b29851, Const(0))).name("x46485").ctrl(x46492).srcCtx("CellsPar.scala:207:15") // FixEql(b29851,Const(0))
    val x46486 = ReduceAccumOp(op=FixAdd, input=x46483, accum=x46484).name("x46486").ctrl(x46492).srcCtx("CellsPar.scala:207:17") // FixAdd(x46483,x46484)
    val x46487 = OpDef(op=BitAnd, inputs=List(b29847, b29843)).name("x46487").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(b29847,b29843)
    val x46488 = OpDef(op=BitAnd, inputs=List(b29799, b29800)).name("x46488").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(b29799,b29800)
    val x46489 = OpDef(op=BitAnd, inputs=List(x46487, x46488)).name("x46489").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(x46487,x46488)
    val x46490 = OpDef(op=BitAnd, inputs=List(x46489, b29006)).name("x46490").ctrl(x46492).srcCtx("UnrollingBase.scala:28:66") // And(x46489,b29006)
    val x46491_x46464_d0 = WriteMem(x46464_d0, x46486).name("x46491_x46464_d0").ctrl(x46492).srcCtx("CellsPar.scala:207:15") // RegWrite(x46464,x46486,x46490)
    val x46491_x46464_d1 = WriteMem(x46464_d1, x46486).name("x46491_x46464_d1").ctrl(x46492).srcCtx("CellsPar.scala:207:15") // RegWrite(x46464,x46486,x46490)
    val x46524 = UnitController(style=SeqPipe, level=InnerControl).name("x46524").ctrl(x46525).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29847, b29843, b29799, b29800, b29006),Block(Const(())))
    val x46493 = OpDef(op=FixAdd, inputs=List(b29798, b29846)).name("x46493").ctrl(x46524).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29798,b29846)
    val x46494 = ReadMem(x46464_d0).name("x46494").ctrl(x46524).srcCtx("CellsPar.scala:210:28") // RegRead(x46464)
    val x46495 = OpDef(op=FixEql, inputs=List(b29797, Const(0))).name("x46495").ctrl(x46524).srcCtx("CellsPar.scala:210:42") // FixEql(b29797,Const(0))
    val x46496 = OpDef(op=FixAdd, inputs=List(x46493, Const(512))).name("x46496").ctrl(x46524).srcCtx("CellsPar.scala:210:66") // FixAdd(x46493,Const(512))
    val x46497 = OpDef(op=BitAnd, inputs=List(b29847, b29843)).name("x46497").ctrl(x46524).srcCtx("UnrollingBase.scala:28:66") // And(b29847,b29843)
    val x46498 = OpDef(op=BitAnd, inputs=List(b29799, b29800)).name("x46498").ctrl(x46524).srcCtx("UnrollingBase.scala:28:66") // And(b29799,b29800)
    val x46499 = OpDef(op=BitAnd, inputs=List(x46497, x46498)).name("x46499").ctrl(x46524).srcCtx("UnrollingBase.scala:28:66") // And(x46497,x46498)
    val x46500 = OpDef(op=BitAnd, inputs=List(x46499, b29006)).name("x46500").ctrl(x46524).srcCtx("UnrollingBase.scala:28:66") // And(x46499,b29006)
    val x46501 = LoadBanks(List(x45544_d2_b0), List(Const(0), x46496)).name("x46501").ctrl(x46524).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45544,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46496),Const(0),x46500)
    val x46502 = LoadBanks(List(x45541_d1_b0), List(b29842, x46493)).name("x46502").ctrl(x46524).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45541,ArrayBuffer(Const(1), Const(512)),List(b29842, x46493),Const(0),x46500)
    val x46503 = OpDef(op=MuxOp, inputs=List(x46495, x46501, x46502)).name("x46503").ctrl(x46524).srcCtx("CellsPar.scala:210:39") // Mux(x46495,x46501,x46502)
    val x46504 = OpDef(op=FixAdd, inputs=List(x46494, x46503)).name("x46504").ctrl(x46524).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x46494,x46503)
    val x46505 = OpDef(op=FixLeq, inputs=List(Const(1008), b29797)).name("x46505").ctrl(x46524).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29797)
    // x46506 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46506_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x46506_int1").ctrl(x46524).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46506_int2 = OpDef(op=FixSla, inputs=List(x46506_int1, Const(24))).name("x46506_int2").ctrl(x46524).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46506_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x46506_frac1").ctrl(x46524).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46506_frac2 = OpDef(op=FixSla, inputs=List(x46506_frac1, Const(24))).name("x46506_frac2").ctrl(x46524).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46506 = OpDef(op=BitOr, inputs=List(x46506_int2, x46506_frac2)).name("x46506").ctrl(x46524).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x46507 = OpDef(op=FixAdd, inputs=List(x46504, x46506)).name("x46507").ctrl(x46524).srcCtx("CellsPar.scala:218:87") // FixAdd(x46504,x46506)
    val x46508 = x46507 // FixConvert(x46507,TRUE,_8,_24) (Same Type. No op)
    val x46509 = OpDef(op=FixAbs, inputs=List(x46508)).name("x46509").ctrl(x46524).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46508)
    val x46510 = OpDef(op=FixLt, inputs=List(Const(2.5), x46509)).name("x46510").ctrl(x46524).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46509)
    val x46511 = OpDef(op=FixLt, inputs=List(Const(0.5), x46509)).name("x46511").ctrl(x46524).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46509)
    val x46512 = OpDef(op=FixLeq, inputs=List(x46509, Const(2.5))).name("x46512").ctrl(x46524).srcCtx("CellsPar.scala:54:52") // FixLeq(x46509,Const(2.5))
    val x46513 = OpDef(op=BitAnd, inputs=List(x46511, x46512)).name("x46513").ctrl(x46524).srcCtx("CellsPar.scala:54:43:cond2") // And(x46511,x46512)
    val x46514 = OpDef(op=FixSra, inputs=List(x46509, Const(2))).name("x46514").ctrl(x46524).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46509,Const(2))
    val x46515 = OpDef(op=FixAdd, inputs=List(x46514, Const(0.375))).name("x46515").ctrl(x46524).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46514,Const(0.375))
    val x46516 = OpDef(op=MuxOp, inputs=List(x46513, x46515, x46509)).name("x46516").ctrl(x46524).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46513,x46515,x46509)
    val x46517 = OpDef(op=MuxOp, inputs=List(x46510, Const(1.0), x46516)).name("x46517").ctrl(x46524).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46510,Const(1),x46516)
    val x46518 = OpDef(op=FixNeg, inputs=List(x46517)).name("x46518").ctrl(x46524).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46517)
    val x46519 = OpDef(op=FixLt, inputs=List(x46508, Const(0.0))).name("x46519").ctrl(x46524).srcCtx("CellsPar.scala:63:22") // FixLt(x46508,Const(0))
    val x46520 = OpDef(op=MuxOp, inputs=List(x46519, x46518, x46517)).name("x46520").ctrl(x46524).srcCtx("CellsPar.scala:63:17:re") // Mux(x46519,x46518,x46517)
    val x46521 = x46520 // FixConvert(x46520,TRUE,_8,_24) (Same Type. No op)
    val x46522 = OpDef(op=MuxOp, inputs=List(x46505, x46521, x46504)).name("x46522").ctrl(x46524).srcCtx("CellsPar.scala:218:43") // Mux(x46505,x46521,x46504)
    val x46523 = StoreBanks(List(x45541_d0_b0, x45541_d1_b0), List(b29842, x46493), x46522).name("x46523").ctrl(x46524).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45541,ArrayBuffer(Const(1), Const(512)),List(b29842, x46493),Const(0),x46522,x46500)
    val x46528 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x46528").ctrl(x46823).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x46529 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x46529").ctrl(x46823).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x46530 = CounterChain(List(x46529,x46528)).name("x46530").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x46529, x46528))
    val x46636 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46530).name("x46636").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x46530,Block(Const(())),List(List(b29917), List(b29918)),List(List(b29919), List(b29920)))
    val b29917 = CounterIter(x46529, Some(0)).name("b29917").ctrl(x46636) // b29917
    val b29919 = Const(true).name("b29919").ctrl(x46636) // b29919
    val b29918 = CounterIter(x46528, Some(0)).name("b29918").ctrl(x46636) // b29918
    val b29920 = Const(true).name("b29920").ctrl(x46636) // b29920
    val x46531_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x46531_d0_b0").ctrl(x46636).srcCtx("CellsPar.scala:191:33:tileKernel") // x46531 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x46531_d0_b0) = false
    bufferDepthOf(x46531_d0_b0) = 2
    val x46534 = UnitController(style=SeqPipe, level=InnerControl).name("x46534").ctrl(x46636).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b29919, b29920, b29006),Block(Const(())))
    val x46532 = OpDef(op=FixAdd, inputs=List(b29917, Const(16))).name("x46532").ctrl(x46534).srcCtx("CellsPar.scala:192:36") // FixAdd(b29917,Const(16))
    val x46533 = OpDef(op=FixAdd, inputs=List(b29918, Const(16))).name("x46533").ctrl(x46534).srcCtx("CellsPar.scala:192:45") // FixAdd(b29918,Const(16))
    val x46535 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46535").ctrl(x46636).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46536 = CounterChain(List(x46535)).name("x46536").ctrl(x46636).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46535))
    val x46565 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46536).name("x46565").ctrl(x46636).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29919, b29920, b29006),x46536,Block(Const(())),List(List(b29927)),List(List(b29928)))
    val b29927 = CounterIter(x46535, Some(0)).name("b29927").ctrl(x46565) // b29927
    val b29928 = Const(true).name("b29928").ctrl(x46565) // b29928
    val b48460 = StreamOut(field="offset").name("b48460").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // x46537 = StreamOutNew(BurstCmdBus)
    isAccum(b48460) = false
    bufferDepthOf(b48460) = 1
    val b48461 = StreamOut(field="size").name("b48461").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // x46537 = StreamOutNew(BurstCmdBus)
    isAccum(b48461) = false
    bufferDepthOf(b48461) = 1
    val x46538 = StreamIn(field="data").name("x46538").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // x46538 = StreamInNew(BurstDataBus())
    isAccum(x46538) = false
    bufferDepthOf(x46538) = 1
    val x46553 = UnitController(style=SeqPipe, level=InnerControl).name("x46553").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b29928, b29919, b29920, b29006),Block(x46552))
    val x46539 = OpDef(op=FixAdd, inputs=List(b29917, b29927)).name("x46539").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // FixAdd(b29917,b29927)
    val x46540 = x46539 // FixConvert(x46539,TRUE,_32,_0) (Same Type. No op)
    val x46541 = OpDef(op=FixSla, inputs=List(x46540, Const(9))).name("x46541").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // FixLsh(x46540,Const(9))
    val x46542 = b29918 // FixConvert(b29918,TRUE,_32,_0) (Same Type. No op)
    val x46543 = OpDef(op=FixAdd, inputs=List(x46541, x46542)).name("x46543").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // FixAdd(x46541,x46542)
    val x46544 = OpDef(op=FixSla, inputs=List(x46543, Const(2))).name("x46544").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // FixLsh(x46543,Const(2))
    val x46545 = x46544 // FixConvert(x46544,TRUE,_64,_0)
    val x46546 = DramAddress(x45255).name("x46546").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45255)
    val x46548_x46547 = OpDef(op=FixAdd, inputs=List(x46545, x46546)).name("x46548_x46547").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // FixAdd(x46545,x46546)
    // x46548 = SimpleStruct(ArrayBuffer((offset,x46547), (size,Const(64)), (isLoad,Const(true))))
    val x46549 = OpDef(op=BitAnd, inputs=List(b29928, b29919)).name("x46549").ctrl(x46553).srcCtx("UnrollingBase.scala:28:66") // And(b29928,b29919)
    val x46550 = OpDef(op=BitAnd, inputs=List(b29920, b29006)).name("x46550").ctrl(x46553).srcCtx("UnrollingBase.scala:28:66") // And(b29920,b29006)
    val x46551 = OpDef(op=BitAnd, inputs=List(x46549, x46550)).name("x46551").ctrl(x46553).srcCtx("UnrollingBase.scala:28:66") // And(x46549,x46550)
    val x46552_b48462_b48460 = WriteMem(b48460, x46548_x46547).name("x46552_b48462_b48460").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46537,x46548,x46551)
    val x46552_b48463_b48461 = WriteMem(b48461, Const(64)).name("x46552_b48463_b48461").ctrl(x46553).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46537,x46548,x46551)
    val x46554 = FringeDenseLoad(dram=List(x45255), cmdStream=List(b48460, b48461), dataStream=List(x46538)).name("x46554").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45255,x46537,x46538)
    val x46555 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46555").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46556 = CounterChain(List(x46555)).name("x46556").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46555))
    val x46564 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46556).name("x46564").ctrl(x46565).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b29928, b29919, b29920, b29006),x46556,Block(Const(())),List(List(b29949)),List(List(b29950)))
    val b29949 = CounterIter(x46555, None).name("b29949").ctrl(x46564) // b29949
    val b29950 = Const(true).name("b29950").ctrl(x46564) // b29950
    val x46557 = OpDef(op=BitAnd, inputs=List(b29950, b29928)).name("x46557").ctrl(x46564).srcCtx("UnrollingBase.scala:28:66") // And(b29950,b29928)
    val x46558 = OpDef(op=BitAnd, inputs=List(b29919, b29920)).name("x46558").ctrl(x46564).srcCtx("UnrollingBase.scala:28:66") // And(b29919,b29920)
    val x46559 = OpDef(op=BitAnd, inputs=List(x46557, x46558)).name("x46559").ctrl(x46564).srcCtx("UnrollingBase.scala:28:66") // And(x46557,x46558)
    val x46560 = OpDef(op=BitAnd, inputs=List(x46559, b29006)).name("x46560").ctrl(x46564).srcCtx("UnrollingBase.scala:28:66") // And(x46559,b29006)
    val x46561_x46561 = ReadMem(x46538).name("x46561_x46561").ctrl(x46564).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x46538,List(x46560))
    val x46562_x46562 = x46561_x46561 // x46562 = VectorApply(x46561,0)
    val x46563 = StoreBanks(List(x46531_d0_b0), List(b29927, b29949), x46562_x46562).name("x46563").ctrl(x46564).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x46531,List(List(b29927, b29949)),List(x46562),List(x46560))
    val x46566 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46566").ctrl(x46636).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46567 = CounterChain(List(x46566)).name("x46567").ctrl(x46636).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x46566))
    val x46635 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46567).name("x46635").ctrl(x46636).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b29919, b29920, b29006),x46567,Block(Const(())),List(List(b29962)),List(List(b29963)))
    val b29962 = CounterIter(x46566, Some(0)).name("b29962").ctrl(x46635) // b29962
    val b29963 = Const(true).name("b29963").ctrl(x46635) // b29963
    val x46568 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46568").ctrl(x46635).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46569 = CounterChain(List(x46568)).name("x46569").ctrl(x46635).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x46568))
    val x46634 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46569).name("x46634").ctrl(x46635).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b29963, b29919, b29920, b29006),x46569,Block(Const(())),List(List(b29966)),List(List(b29967)))
    val b29966 = CounterIter(x46568, Some(0)).name("b29966").ctrl(x46634) // b29966
    val b29967 = Const(true).name("b29967").ctrl(x46634) // b29967
    val x46570_d0 = Reg(init=Some(0.0)).name("x46570_d0").ctrl(x46634).srcCtx("CellsPar.scala:195:34:prod") // x46570 = RegNew(Const(0))
    isAccum(x46570_d0) = false
    bufferDepthOf(x46570_d0) = 2
    val x46570_d1 = Reg(init=Some(0.0)).name("x46570_d1").ctrl(x46634).srcCtx("CellsPar.scala:195:34:prod") // x46570 = RegNew(Const(0))
    isAccum(x46570_d1) = true
    bufferDepthOf(x46570_d1) = 1
    val x46571 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46571").ctrl(x46634).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46572 = CounterChain(List(x46571)).name("x46572").ctrl(x46634).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x46571))
    val x46598 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46572).name("x46598").ctrl(x46634).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b29967, b29963, b29919, b29920, b29006),x46572,x46570,Block((x46570) => Const(())),List(List(b29971)),List(List(b29972)))
    val b29971 = CounterIter(x46571, None).name("b29971").ctrl(x46598) // b29971
    val b29972 = Const(true).name("b29972").ctrl(x46598) // b29972
    val x46573 = OpDef(op=FixAdd, inputs=List(b29917, b29971)).name("x46573").ctrl(x46598).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b29917,b29971)
    val x46574 = OpDef(op=BitAnd, inputs=List(b29972, b29967)).name("x46574").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(b29972,b29967)
    val x46575 = OpDef(op=BitAnd, inputs=List(b29963, b29919)).name("x46575").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(b29963,b29919)
    val x46576 = OpDef(op=BitAnd, inputs=List(b29920, b29006)).name("x46576").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(b29920,b29006)
    val x46577 = OpDef(op=BitAnd, inputs=List(x46574, x46575)).name("x46577").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(x46574,x46575)
    val x46578 = OpDef(op=BitAnd, inputs=List(x46577, x46576)).name("x46578").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(x46577,x46576)
    val x46579 = LoadBanks(List(x46531_d0_b0), List(b29971, b29966)).name("x46579").ctrl(x46598).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x46531,List(List(b29971, b29966)),List(x46578))
    val x46580 = x46579 // x46580 = VectorApply(x46579,0)
    val x46581 = LoadBanks(List(x45537_d1_b0), List(b29962, x46573)).name("x46581").ctrl(x46598).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45537,List(List(b29962, x46573)),List(x46578))
    val x46582 = x46581 // x46582 = VectorApply(x46581,0)
    val x46583 = OpDef(op=FixMul, inputs=List(x46582, x46580)).name("x46583").ctrl(x46598).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x46582,x46580)
    val x46584 = OpDef(op=FixSub, inputs=List(x46573, Const(512))).name("x46584").ctrl(x46598).srcCtx("CellsPar.scala:205:51") // FixSub(x46573,Const(512))
    val x46585 = LoadBanks(List(x45538_d6_b0), List(b29962, x46584)).name("x46585").ctrl(x46598).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45538,List(List(b29962, x46584)),List(x46578))
    val x46586 = x46585 // x46586 = VectorApply(x46585,0)
    val x46587 = OpDef(op=FixMul, inputs=List(x46586, x46580)).name("x46587").ctrl(x46598).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x46586,x46580)
    val x46588 = OpDef(op=FixLt, inputs=List(x46573, Const(512))).name("x46588").ctrl(x46598).srcCtx("CellsPar.scala:206:38") // FixLt(x46573,Const(512))
    val x46589 = OpDef(op=MuxOp, inputs=List(x46588, x46583, x46587)).name("x46589").ctrl(x46598).srcCtx("CellsPar.scala:206:18") // Mux(x46588,x46583,x46587)
    val x46590 = ReadMem(x46570_d1).name("x46590").ctrl(x46598).srcCtx("CellsPar.scala:207:15") // RegRead(x46570)
    val x46591 = OpDef(op=FixEql, inputs=List(b29971, Const(0))).name("x46591").ctrl(x46598).srcCtx("CellsPar.scala:207:15") // FixEql(b29971,Const(0))
    val x46592 = ReduceAccumOp(op=FixAdd, input=x46589, accum=x46590).name("x46592").ctrl(x46598).srcCtx("CellsPar.scala:207:17") // FixAdd(x46589,x46590)
    val x46593 = OpDef(op=BitAnd, inputs=List(b29967, b29963)).name("x46593").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(b29967,b29963)
    val x46594 = OpDef(op=BitAnd, inputs=List(b29919, b29920)).name("x46594").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(b29919,b29920)
    val x46595 = OpDef(op=BitAnd, inputs=List(x46593, x46594)).name("x46595").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(x46593,x46594)
    val x46596 = OpDef(op=BitAnd, inputs=List(x46595, b29006)).name("x46596").ctrl(x46598).srcCtx("UnrollingBase.scala:28:66") // And(x46595,b29006)
    val x46597_x46570_d0 = WriteMem(x46570_d0, x46592).name("x46597_x46570_d0").ctrl(x46598).srcCtx("CellsPar.scala:207:15") // RegWrite(x46570,x46592,x46596)
    val x46597_x46570_d1 = WriteMem(x46570_d1, x46592).name("x46597_x46570_d1").ctrl(x46598).srcCtx("CellsPar.scala:207:15") // RegWrite(x46570,x46592,x46596)
    val x46633 = UnitController(style=SeqPipe, level=InnerControl).name("x46633").ctrl(x46634).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b29967, b29963, b29919, b29920, b29006),Block(Const(())))
    val x46599 = OpDef(op=FixAdd, inputs=List(b29918, b29966)).name("x46599").ctrl(x46633).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b29918,b29966)
    val x46600 = ReadMem(x46570_d0).name("x46600").ctrl(x46633).srcCtx("CellsPar.scala:210:28") // RegRead(x46570)
    val x46601 = OpDef(op=FixEql, inputs=List(b29917, Const(0))).name("x46601").ctrl(x46633).srcCtx("CellsPar.scala:210:42") // FixEql(b29917,Const(0))
    val x46602 = OpDef(op=FixAdd, inputs=List(x46599, Const(1024))).name("x46602").ctrl(x46633).srcCtx("CellsPar.scala:210:66") // FixAdd(x46599,Const(1024))
    val x46603 = OpDef(op=BitAnd, inputs=List(b29967, b29963)).name("x46603").ctrl(x46633).srcCtx("UnrollingBase.scala:28:66") // And(b29967,b29963)
    val x46604 = OpDef(op=BitAnd, inputs=List(b29919, b29920)).name("x46604").ctrl(x46633).srcCtx("UnrollingBase.scala:28:66") // And(b29919,b29920)
    val x46605 = OpDef(op=BitAnd, inputs=List(x46603, x46604)).name("x46605").ctrl(x46633).srcCtx("UnrollingBase.scala:28:66") // And(x46603,x46604)
    val x46606 = OpDef(op=BitAnd, inputs=List(x46605, b29006)).name("x46606").ctrl(x46633).srcCtx("UnrollingBase.scala:28:66") // And(x46605,b29006)
    val x46607 = LoadBanks(List(x45544_d1_b0), List(Const(0), x46602)).name("x46607").ctrl(x46633).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45544,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46602),Const(0),x46606)
    val x46608 = LoadBanks(List(x45542_d1_b0), List(b29962, x46599)).name("x46608").ctrl(x46633).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45542,ArrayBuffer(Const(1), Const(512)),List(b29962, x46599),Const(0),x46606)
    val x46609 = OpDef(op=MuxOp, inputs=List(x46601, x46607, x46608)).name("x46609").ctrl(x46633).srcCtx("CellsPar.scala:210:39") // Mux(x46601,x46607,x46608)
    val x46610 = OpDef(op=FixAdd, inputs=List(x46600, x46609)).name("x46610").ctrl(x46633).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x46600,x46609)
    val x46611 = OpDef(op=FixLeq, inputs=List(Const(1008), b29917)).name("x46611").ctrl(x46633).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b29917)
    // x46612 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46612_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x46612_int1").ctrl(x46633).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46612_int2 = OpDef(op=FixSla, inputs=List(x46612_int1, Const(24))).name("x46612_int2").ctrl(x46633).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46612_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x46612_frac1").ctrl(x46633).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46612_frac2 = OpDef(op=FixSla, inputs=List(x46612_frac1, Const(24))).name("x46612_frac2").ctrl(x46633).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x46612 = OpDef(op=BitOr, inputs=List(x46612_int2, x46612_frac2)).name("x46612").ctrl(x46633).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x46613 = OpDef(op=FixAdd, inputs=List(x46610, x46612)).name("x46613").ctrl(x46633).srcCtx("CellsPar.scala:218:87") // FixAdd(x46610,x46612)
    val x46614 = OpDef(op=FixSra, inputs=List(x46613, Const(1))).name("x46614").ctrl(x46633).srcCtx("CellsPar.scala:29:22") // FixRsh(x46613,Const(1))
    val x46615 = x46614 // FixConvert(x46614,TRUE,_8,_24) (Same Type. No op)
    val x46616 = OpDef(op=FixAbs, inputs=List(x46615)).name("x46616").ctrl(x46633).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46615)
    val x46617 = OpDef(op=FixLt, inputs=List(Const(2.5), x46616)).name("x46617").ctrl(x46633).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46616)
    val x46618 = OpDef(op=FixLt, inputs=List(Const(0.5), x46616)).name("x46618").ctrl(x46633).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46616)
    val x46619 = OpDef(op=FixLeq, inputs=List(x46616, Const(2.5))).name("x46619").ctrl(x46633).srcCtx("CellsPar.scala:54:52") // FixLeq(x46616,Const(2.5))
    val x46620 = OpDef(op=BitAnd, inputs=List(x46618, x46619)).name("x46620").ctrl(x46633).srcCtx("CellsPar.scala:54:43:cond2") // And(x46618,x46619)
    val x46621 = OpDef(op=FixSra, inputs=List(x46616, Const(2))).name("x46621").ctrl(x46633).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46616,Const(2))
    val x46622 = OpDef(op=FixAdd, inputs=List(x46621, Const(0.375))).name("x46622").ctrl(x46633).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46621,Const(0.375))
    val x46623 = OpDef(op=MuxOp, inputs=List(x46620, x46622, x46616)).name("x46623").ctrl(x46633).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46620,x46622,x46616)
    val x46624 = OpDef(op=MuxOp, inputs=List(x46617, Const(1.0), x46623)).name("x46624").ctrl(x46633).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46617,Const(1),x46623)
    val x46625 = OpDef(op=FixNeg, inputs=List(x46624)).name("x46625").ctrl(x46633).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46624)
    val x46626 = OpDef(op=FixLt, inputs=List(x46615, Const(0.0))).name("x46626").ctrl(x46633).srcCtx("CellsPar.scala:63:22") // FixLt(x46615,Const(0))
    val x46627 = OpDef(op=MuxOp, inputs=List(x46626, x46625, x46624)).name("x46627").ctrl(x46633).srcCtx("CellsPar.scala:63:17:re") // Mux(x46626,x46625,x46624)
    val x46628 = x46627 // FixConvert(x46627,TRUE,_8,_24) (Same Type. No op)
    val x46629 = OpDef(op=FixAdd, inputs=List(x46628, Const(1.0))).name("x46629").ctrl(x46633).srcCtx("CellsPar.scala:29:33") // FixAdd(x46628,Const(1))
    val x46630 = OpDef(op=FixSra, inputs=List(x46629, Const(1))).name("x46630").ctrl(x46633).srcCtx("CellsPar.scala:29:44") // FixRsh(x46629,Const(1))
    val x46631 = OpDef(op=MuxOp, inputs=List(x46611, x46630, x46610)).name("x46631").ctrl(x46633).srcCtx("CellsPar.scala:218:43") // Mux(x46611,x46630,x46610)
    val x46632 = StoreBanks(List(x45542_d0_b0, x45542_d1_b0), List(b29962, x46599), x46631).name("x46632").ctrl(x46633).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45542,ArrayBuffer(Const(1), Const(512)),List(b29962, x46599),Const(0),x46631,x46606)
    val x46637 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x46637").ctrl(x46823).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x46638 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x46638").ctrl(x46823).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x46639 = CounterChain(List(x46638,x46637)).name("x46639").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x46638, x46637))
    val x46745 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46639).name("x46745").ctrl(x46823).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b29006),x46639,Block(Const(())),List(List(b30040), List(b30041)),List(List(b30042), List(b30043)))
    val b30040 = CounterIter(x46638, Some(0)).name("b30040").ctrl(x46745) // b30040
    val b30042 = Const(true).name("b30042").ctrl(x46745) // b30042
    val b30041 = CounterIter(x46637, Some(0)).name("b30041").ctrl(x46745) // b30041
    val b30043 = Const(true).name("b30043").ctrl(x46745) // b30043
    val x46640_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x46640_d0_b0").ctrl(x46745).srcCtx("CellsPar.scala:191:33:tileKernel") // x46640 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x46640_d0_b0) = false
    bufferDepthOf(x46640_d0_b0) = 2
    val x46643 = UnitController(style=SeqPipe, level=InnerControl).name("x46643").ctrl(x46745).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b30042, b30043, b29006),Block(Const(())))
    val x46641 = OpDef(op=FixAdd, inputs=List(b30040, Const(16))).name("x46641").ctrl(x46643).srcCtx("CellsPar.scala:192:36") // FixAdd(b30040,Const(16))
    val x46642 = OpDef(op=FixAdd, inputs=List(b30041, Const(16))).name("x46642").ctrl(x46643).srcCtx("CellsPar.scala:192:45") // FixAdd(b30041,Const(16))
    val x46644 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46644").ctrl(x46745).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46645 = CounterChain(List(x46644)).name("x46645").ctrl(x46745).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46644))
    val x46674 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46645).name("x46674").ctrl(x46745).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b30042, b30043, b29006),x46645,Block(Const(())),List(List(b30050)),List(List(b30051)))
    val b30050 = CounterIter(x46644, Some(0)).name("b30050").ctrl(x46674) // b30050
    val b30051 = Const(true).name("b30051").ctrl(x46674) // b30051
    val b48464 = StreamOut(field="offset").name("b48464").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // x46646 = StreamOutNew(BurstCmdBus)
    isAccum(b48464) = false
    bufferDepthOf(b48464) = 1
    val b48465 = StreamOut(field="size").name("b48465").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // x46646 = StreamOutNew(BurstCmdBus)
    isAccum(b48465) = false
    bufferDepthOf(b48465) = 1
    val x46647 = StreamIn(field="data").name("x46647").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // x46647 = StreamInNew(BurstDataBus())
    isAccum(x46647) = false
    bufferDepthOf(x46647) = 1
    val x46662 = UnitController(style=SeqPipe, level=InnerControl).name("x46662").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b30051, b30042, b30043, b29006),Block(x46661))
    val x46648 = OpDef(op=FixAdd, inputs=List(b30040, b30050)).name("x46648").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // FixAdd(b30040,b30050)
    val x46649 = x46648 // FixConvert(x46648,TRUE,_32,_0) (Same Type. No op)
    val x46650 = OpDef(op=FixSla, inputs=List(x46649, Const(9))).name("x46650").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // FixLsh(x46649,Const(9))
    val x46651 = b30041 // FixConvert(b30041,TRUE,_32,_0) (Same Type. No op)
    val x46652 = OpDef(op=FixAdd, inputs=List(x46650, x46651)).name("x46652").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // FixAdd(x46650,x46651)
    val x46653 = OpDef(op=FixSla, inputs=List(x46652, Const(2))).name("x46653").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // FixLsh(x46652,Const(2))
    val x46654 = x46653 // FixConvert(x46653,TRUE,_64,_0)
    val x46655 = DramAddress(x45256).name("x46655").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45256)
    val x46657_x46656 = OpDef(op=FixAdd, inputs=List(x46654, x46655)).name("x46657_x46656").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // FixAdd(x46654,x46655)
    // x46657 = SimpleStruct(ArrayBuffer((offset,x46656), (size,Const(64)), (isLoad,Const(true))))
    val x46658 = OpDef(op=BitAnd, inputs=List(b30051, b30042)).name("x46658").ctrl(x46662).srcCtx("UnrollingBase.scala:28:66") // And(b30051,b30042)
    val x46659 = OpDef(op=BitAnd, inputs=List(b30043, b29006)).name("x46659").ctrl(x46662).srcCtx("UnrollingBase.scala:28:66") // And(b30043,b29006)
    val x46660 = OpDef(op=BitAnd, inputs=List(x46658, x46659)).name("x46660").ctrl(x46662).srcCtx("UnrollingBase.scala:28:66") // And(x46658,x46659)
    val x46661_b48466_b48464 = WriteMem(b48464, x46657_x46656).name("x46661_b48466_b48464").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46646,x46657,x46660)
    val x46661_b48467_b48465 = WriteMem(b48465, Const(64)).name("x46661_b48467_b48465").ctrl(x46662).srcCtx("CellsPar.scala:192:20") // StreamWrite(x46646,x46657,x46660)
    val x46663 = FringeDenseLoad(dram=List(x45256), cmdStream=List(b48464, b48465), dataStream=List(x46647)).name("x46663").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45256,x46646,x46647)
    val x46664 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46664").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46665 = CounterChain(List(x46664)).name("x46665").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x46664))
    val x46673 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46665).name("x46673").ctrl(x46674).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b30051, b30042, b30043, b29006),x46665,Block(Const(())),List(List(b30072)),List(List(b30073)))
    val b30072 = CounterIter(x46664, None).name("b30072").ctrl(x46673) // b30072
    val b30073 = Const(true).name("b30073").ctrl(x46673) // b30073
    val x46666 = OpDef(op=BitAnd, inputs=List(b30073, b30051)).name("x46666").ctrl(x46673).srcCtx("UnrollingBase.scala:28:66") // And(b30073,b30051)
    val x46667 = OpDef(op=BitAnd, inputs=List(b30042, b30043)).name("x46667").ctrl(x46673).srcCtx("UnrollingBase.scala:28:66") // And(b30042,b30043)
    val x46668 = OpDef(op=BitAnd, inputs=List(x46666, x46667)).name("x46668").ctrl(x46673).srcCtx("UnrollingBase.scala:28:66") // And(x46666,x46667)
    val x46669 = OpDef(op=BitAnd, inputs=List(x46668, b29006)).name("x46669").ctrl(x46673).srcCtx("UnrollingBase.scala:28:66") // And(x46668,b29006)
    val x46670_x46670 = ReadMem(x46647).name("x46670_x46670").ctrl(x46673).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x46647,List(x46669))
    val x46671_x46671 = x46670_x46670 // x46671 = VectorApply(x46670,0)
    val x46672 = StoreBanks(List(x46640_d0_b0), List(b30050, b30072), x46671_x46671).name("x46672").ctrl(x46673).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x46640,List(List(b30050, b30072)),List(x46671),List(x46669))
    val x46675 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46675").ctrl(x46745).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46676 = CounterChain(List(x46675)).name("x46676").ctrl(x46745).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x46675))
    val x46744 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46676).name("x46744").ctrl(x46745).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b30042, b30043, b29006),x46676,Block(Const(())),List(List(b30085)),List(List(b30086)))
    val b30085 = CounterIter(x46675, Some(0)).name("b30085").ctrl(x46744) // b30085
    val b30086 = Const(true).name("b30086").ctrl(x46744) // b30086
    val x46677 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46677").ctrl(x46744).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46678 = CounterChain(List(x46677)).name("x46678").ctrl(x46744).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x46677))
    val x46743 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46678).name("x46743").ctrl(x46744).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b30086, b30042, b30043, b29006),x46678,Block(Const(())),List(List(b30089)),List(List(b30090)))
    val b30089 = CounterIter(x46677, Some(0)).name("b30089").ctrl(x46743) // b30089
    val b30090 = Const(true).name("b30090").ctrl(x46743) // b30090
    val x46679_d0 = Reg(init=Some(0.0)).name("x46679_d0").ctrl(x46743).srcCtx("CellsPar.scala:195:34:prod") // x46679 = RegNew(Const(0))
    isAccum(x46679_d0) = false
    bufferDepthOf(x46679_d0) = 2
    val x46679_d1 = Reg(init=Some(0.0)).name("x46679_d1").ctrl(x46743).srcCtx("CellsPar.scala:195:34:prod") // x46679 = RegNew(Const(0))
    isAccum(x46679_d1) = true
    bufferDepthOf(x46679_d1) = 1
    val x46680 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46680").ctrl(x46743).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46681 = CounterChain(List(x46680)).name("x46681").ctrl(x46743).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x46680))
    val x46707 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46681).name("x46707").ctrl(x46743).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b30090, b30086, b30042, b30043, b29006),x46681,x46679,Block((x46679) => Const(())),List(List(b30094)),List(List(b30095)))
    val b30094 = CounterIter(x46680, None).name("b30094").ctrl(x46707) // b30094
    val b30095 = Const(true).name("b30095").ctrl(x46707) // b30095
    val x46682 = OpDef(op=FixAdd, inputs=List(b30040, b30094)).name("x46682").ctrl(x46707).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b30040,b30094)
    val x46683 = OpDef(op=BitAnd, inputs=List(b30095, b30090)).name("x46683").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(b30095,b30090)
    val x46684 = OpDef(op=BitAnd, inputs=List(b30086, b30042)).name("x46684").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(b30086,b30042)
    val x46685 = OpDef(op=BitAnd, inputs=List(b30043, b29006)).name("x46685").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(b30043,b29006)
    val x46686 = OpDef(op=BitAnd, inputs=List(x46683, x46684)).name("x46686").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(x46683,x46684)
    val x46687 = OpDef(op=BitAnd, inputs=List(x46686, x46685)).name("x46687").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(x46686,x46685)
    val x46688 = LoadBanks(List(x46640_d0_b0), List(b30094, b30089)).name("x46688").ctrl(x46707).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x46640,List(List(b30094, b30089)),List(x46687))
    val x46689 = x46688 // x46689 = VectorApply(x46688,0)
    val x46690 = LoadBanks(List(x45537_d0_b0), List(b30085, x46682)).name("x46690").ctrl(x46707).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45537,List(List(b30085, x46682)),List(x46687))
    val x46691 = x46690 // x46691 = VectorApply(x46690,0)
    val x46692 = OpDef(op=FixMul, inputs=List(x46691, x46689)).name("x46692").ctrl(x46707).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x46691,x46689)
    val x46693 = OpDef(op=FixSub, inputs=List(x46682, Const(512))).name("x46693").ctrl(x46707).srcCtx("CellsPar.scala:205:51") // FixSub(x46682,Const(512))
    val x46694 = LoadBanks(List(x45538_d5_b0), List(b30085, x46693)).name("x46694").ctrl(x46707).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45538,List(List(b30085, x46693)),List(x46687))
    val x46695 = x46694 // x46695 = VectorApply(x46694,0)
    val x46696 = OpDef(op=FixMul, inputs=List(x46695, x46689)).name("x46696").ctrl(x46707).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x46695,x46689)
    val x46697 = OpDef(op=FixLt, inputs=List(x46682, Const(512))).name("x46697").ctrl(x46707).srcCtx("CellsPar.scala:206:38") // FixLt(x46682,Const(512))
    val x46698 = OpDef(op=MuxOp, inputs=List(x46697, x46692, x46696)).name("x46698").ctrl(x46707).srcCtx("CellsPar.scala:206:18") // Mux(x46697,x46692,x46696)
    val x46699 = ReadMem(x46679_d1).name("x46699").ctrl(x46707).srcCtx("CellsPar.scala:207:15") // RegRead(x46679)
    val x46700 = OpDef(op=FixEql, inputs=List(b30094, Const(0))).name("x46700").ctrl(x46707).srcCtx("CellsPar.scala:207:15") // FixEql(b30094,Const(0))
    val x46701 = ReduceAccumOp(op=FixAdd, input=x46698, accum=x46699).name("x46701").ctrl(x46707).srcCtx("CellsPar.scala:207:17") // FixAdd(x46698,x46699)
    val x46702 = OpDef(op=BitAnd, inputs=List(b30090, b30086)).name("x46702").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(b30090,b30086)
    val x46703 = OpDef(op=BitAnd, inputs=List(b30042, b30043)).name("x46703").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(b30042,b30043)
    val x46704 = OpDef(op=BitAnd, inputs=List(x46702, x46703)).name("x46704").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(x46702,x46703)
    val x46705 = OpDef(op=BitAnd, inputs=List(x46704, b29006)).name("x46705").ctrl(x46707).srcCtx("UnrollingBase.scala:28:66") // And(x46704,b29006)
    val x46706_x46679_d0 = WriteMem(x46679_d0, x46701).name("x46706_x46679_d0").ctrl(x46707).srcCtx("CellsPar.scala:207:15") // RegWrite(x46679,x46701,x46705)
    val x46706_x46679_d1 = WriteMem(x46679_d1, x46701).name("x46706_x46679_d1").ctrl(x46707).srcCtx("CellsPar.scala:207:15") // RegWrite(x46679,x46701,x46705)
    val x46742 = UnitController(style=SeqPipe, level=InnerControl).name("x46742").ctrl(x46743).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b30090, b30086, b30042, b30043, b29006),Block(Const(())))
    val x46708 = OpDef(op=FixAdd, inputs=List(b30041, b30089)).name("x46708").ctrl(x46742).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b30041,b30089)
    val x46709 = ReadMem(x46679_d0).name("x46709").ctrl(x46742).srcCtx("CellsPar.scala:210:28") // RegRead(x46679)
    val x46710 = OpDef(op=FixEql, inputs=List(b30040, Const(0))).name("x46710").ctrl(x46742).srcCtx("CellsPar.scala:210:42") // FixEql(b30040,Const(0))
    val x46711 = OpDef(op=FixAdd, inputs=List(x46708, Const(1536))).name("x46711").ctrl(x46742).srcCtx("CellsPar.scala:210:66") // FixAdd(x46708,Const(1536))
    val x46712 = OpDef(op=BitAnd, inputs=List(b30090, b30086)).name("x46712").ctrl(x46742).srcCtx("UnrollingBase.scala:28:66") // And(b30090,b30086)
    val x46713 = OpDef(op=BitAnd, inputs=List(b30042, b30043)).name("x46713").ctrl(x46742).srcCtx("UnrollingBase.scala:28:66") // And(b30042,b30043)
    val x46714 = OpDef(op=BitAnd, inputs=List(x46712, x46713)).name("x46714").ctrl(x46742).srcCtx("UnrollingBase.scala:28:66") // And(x46712,x46713)
    val x46715 = OpDef(op=BitAnd, inputs=List(x46714, b29006)).name("x46715").ctrl(x46742).srcCtx("UnrollingBase.scala:28:66") // And(x46714,b29006)
    val x46716 = LoadBanks(List(x45544_d0_b0), List(Const(0), x46711)).name("x46716").ctrl(x46742).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45544,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46711),Const(0),x46715)
    val x46717 = LoadBanks(List(x45543_d1_b0), List(b30085, x46708)).name("x46717").ctrl(x46742).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45543,ArrayBuffer(Const(1), Const(512)),List(b30085, x46708),Const(0),x46715)
    val x46718 = OpDef(op=MuxOp, inputs=List(x46710, x46716, x46717)).name("x46718").ctrl(x46742).srcCtx("CellsPar.scala:210:39") // Mux(x46710,x46716,x46717)
    val x46719 = OpDef(op=FixAdd, inputs=List(x46709, x46718)).name("x46719").ctrl(x46742).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x46709,x46718)
    val x46720 = OpDef(op=FixLeq, inputs=List(Const(1008), b30040)).name("x46720").ctrl(x46742).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b30040)
    // x46721 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46721_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x46721_int1").ctrl(x46742).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46721_int2 = OpDef(op=FixSla, inputs=List(x46721_int1, Const(24))).name("x46721_int2").ctrl(x46742).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46721_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x46721_frac1").ctrl(x46742).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46721_frac2 = OpDef(op=FixSla, inputs=List(x46721_frac1, Const(24))).name("x46721_frac2").ctrl(x46742).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x46721 = OpDef(op=BitOr, inputs=List(x46721_int2, x46721_frac2)).name("x46721").ctrl(x46742).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x46722 = OpDef(op=FixAdd, inputs=List(x46719, x46721)).name("x46722").ctrl(x46742).srcCtx("CellsPar.scala:218:87") // FixAdd(x46719,x46721)
    val x46723 = OpDef(op=FixSra, inputs=List(x46722, Const(1))).name("x46723").ctrl(x46742).srcCtx("CellsPar.scala:29:22") // FixRsh(x46722,Const(1))
    val x46724 = x46723 // FixConvert(x46723,TRUE,_8,_24) (Same Type. No op)
    val x46725 = OpDef(op=FixAbs, inputs=List(x46724)).name("x46725").ctrl(x46742).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46724)
    val x46726 = OpDef(op=FixLt, inputs=List(Const(2.5), x46725)).name("x46726").ctrl(x46742).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46725)
    val x46727 = OpDef(op=FixLt, inputs=List(Const(0.5), x46725)).name("x46727").ctrl(x46742).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46725)
    val x46728 = OpDef(op=FixLeq, inputs=List(x46725, Const(2.5))).name("x46728").ctrl(x46742).srcCtx("CellsPar.scala:54:52") // FixLeq(x46725,Const(2.5))
    val x46729 = OpDef(op=BitAnd, inputs=List(x46727, x46728)).name("x46729").ctrl(x46742).srcCtx("CellsPar.scala:54:43:cond2") // And(x46727,x46728)
    val x46730 = OpDef(op=FixSra, inputs=List(x46725, Const(2))).name("x46730").ctrl(x46742).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46725,Const(2))
    val x46731 = OpDef(op=FixAdd, inputs=List(x46730, Const(0.375))).name("x46731").ctrl(x46742).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46730,Const(0.375))
    val x46732 = OpDef(op=MuxOp, inputs=List(x46729, x46731, x46725)).name("x46732").ctrl(x46742).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46729,x46731,x46725)
    val x46733 = OpDef(op=MuxOp, inputs=List(x46726, Const(1.0), x46732)).name("x46733").ctrl(x46742).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46726,Const(1),x46732)
    val x46734 = OpDef(op=FixNeg, inputs=List(x46733)).name("x46734").ctrl(x46742).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46733)
    val x46735 = OpDef(op=FixLt, inputs=List(x46724, Const(0.0))).name("x46735").ctrl(x46742).srcCtx("CellsPar.scala:63:22") // FixLt(x46724,Const(0))
    val x46736 = OpDef(op=MuxOp, inputs=List(x46735, x46734, x46733)).name("x46736").ctrl(x46742).srcCtx("CellsPar.scala:63:17:re") // Mux(x46735,x46734,x46733)
    val x46737 = x46736 // FixConvert(x46736,TRUE,_8,_24) (Same Type. No op)
    val x46738 = OpDef(op=FixAdd, inputs=List(x46737, Const(1.0))).name("x46738").ctrl(x46742).srcCtx("CellsPar.scala:29:33") // FixAdd(x46737,Const(1))
    val x46739 = OpDef(op=FixSra, inputs=List(x46738, Const(1))).name("x46739").ctrl(x46742).srcCtx("CellsPar.scala:29:44") // FixRsh(x46738,Const(1))
    val x46740 = OpDef(op=MuxOp, inputs=List(x46720, x46739, x46719)).name("x46740").ctrl(x46742).srcCtx("CellsPar.scala:218:43") // Mux(x46720,x46739,x46719)
    val x46741 = StoreBanks(List(x45543_d0_b0, x45543_d1_b0), List(b30085, x46708), x46740).name("x46741").ctrl(x46742).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45543,ArrayBuffer(Const(1), Const(512)),List(b30085, x46708),Const(0),x46740,x46715)
    val x46746 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46746").ctrl(x46823).srcCtx("CellsPar.scala:243:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46747 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46747").ctrl(x46823).srcCtx("CellsPar.scala:243:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46748 = CounterChain(List(x46747,x46746)).name("x46748").ctrl(x46823).srcCtx("CellsPar.scala:243:62") // CounterChainNew(List(x46747, x46746))
    val x46781 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46748).name("x46781").ctrl(x46823).srcCtx("CellsPar.scala:243:62") // UnrolledForeach(List(b29006),x46748,Block(Const(())),List(List(b30164), List(b30165)),List(List(b30166), List(b30167)))
    val b30164 = CounterIter(x46747, Some(0)).name("b30164").ctrl(x46781) // b30164
    val b30166 = Const(true).name("b30166").ctrl(x46781) // b30166
    val b30165 = CounterIter(x46746, None).name("b30165").ctrl(x46781) // b30165
    val b30167 = Const(true).name("b30167").ctrl(x46781) // b30167
    val x46749 = OpDef(op=BitAnd, inputs=List(b30166, b30167)).name("x46749").ctrl(x46781).srcCtx("UnrollingBase.scala:28:66") // And(b30166,b30167)
    val x46750 = OpDef(op=BitAnd, inputs=List(x46749, b29006)).name("x46750").ctrl(x46781).srcCtx("UnrollingBase.scala:28:66") // And(x46749,b29006)
    val x46751 = LoadBanks(List(x45539_d0_b0), List(b30164, b30165)).name("x46751").ctrl(x46781).srcCtx("CellsPar.scala:244:22") // ParSRAMLoad(x45539,List(List(b30164, b30165)),List(x46750))
    val x46752 = x46751 // x46752 = VectorApply(x46751,0)
    val x46753 = LoadBanks(List(x45542_d0_b0), List(b30164, b30165)).name("x46753").ctrl(x46781).srcCtx("CellsPar.scala:244:34") // ParSRAMLoad(x45542,List(List(b30164, b30165)),List(x46750))
    val x46754 = x46753 // x46754 = VectorApply(x46753,0)
    val x46755 = OpDef(op=FixMul, inputs=List(x46752, x46754)).name("x46755").ctrl(x46781).srcCtx("CellsPar.scala:244:28") // FixMul(x46752,x46754)
    val x46756 = LoadBanks(List(x45540_d0_b0), List(b30164, b30165)).name("x46756").ctrl(x46781).srcCtx("CellsPar.scala:244:46") // ParSRAMLoad(x45540,List(List(b30164, b30165)),List(x46750))
    val x46757 = x46756 // x46757 = VectorApply(x46756,0)
    val x46758 = LoadBanks(List(x45541_d0_b0), List(b30164, b30165)).name("x46758").ctrl(x46781).srcCtx("CellsPar.scala:244:59") // ParSRAMLoad(x45541,List(List(b30164, b30165)),List(x46750))
    val x46759 = x46758 // x46759 = VectorApply(x46758,0)
    val x46760 = OpDef(op=FixMul, inputs=List(x46757, x46759)).name("x46760").ctrl(x46781).srcCtx("CellsPar.scala:244:52") // FixMul(x46757,x46759)
    val x46761 = OpDef(op=FixAdd, inputs=List(x46755, x46760)).name("x46761").ctrl(x46781).srcCtx("CellsPar.scala:244:40:new_c") // FixAdd(x46755,x46760)
    val x46762 = x46761 // FixConvert(x46761,TRUE,_8,_24) (Same Type. No op)
    val x46763 = OpDef(op=FixAbs, inputs=List(x46762)).name("x46763").ctrl(x46781).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46762)
    val x46764 = OpDef(op=FixLt, inputs=List(Const(2.5), x46763)).name("x46764").ctrl(x46781).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46763)
    val x46765 = OpDef(op=FixLt, inputs=List(Const(0.5), x46763)).name("x46765").ctrl(x46781).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46763)
    val x46766 = OpDef(op=FixLeq, inputs=List(x46763, Const(2.5))).name("x46766").ctrl(x46781).srcCtx("CellsPar.scala:54:52") // FixLeq(x46763,Const(2.5))
    val x46767 = OpDef(op=BitAnd, inputs=List(x46765, x46766)).name("x46767").ctrl(x46781).srcCtx("CellsPar.scala:54:43:cond2") // And(x46765,x46766)
    val x46768 = OpDef(op=FixSra, inputs=List(x46763, Const(2))).name("x46768").ctrl(x46781).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46763,Const(2))
    val x46769 = OpDef(op=FixAdd, inputs=List(x46768, Const(0.375))).name("x46769").ctrl(x46781).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46768,Const(0.375))
    val x46770 = OpDef(op=MuxOp, inputs=List(x46767, x46769, x46763)).name("x46770").ctrl(x46781).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46767,x46769,x46763)
    val x46771 = OpDef(op=MuxOp, inputs=List(x46764, Const(1.0), x46770)).name("x46771").ctrl(x46781).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46764,Const(1),x46770)
    val x46772 = OpDef(op=FixNeg, inputs=List(x46771)).name("x46772").ctrl(x46781).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46771)
    val x46773 = OpDef(op=FixLt, inputs=List(x46762, Const(0.0))).name("x46773").ctrl(x46781).srcCtx("CellsPar.scala:63:22") // FixLt(x46762,Const(0))
    val x46774 = OpDef(op=MuxOp, inputs=List(x46773, x46772, x46771)).name("x46774").ctrl(x46781).srcCtx("CellsPar.scala:63:17:re") // Mux(x46773,x46772,x46771)
    val x46775 = x46774 // FixConvert(x46774,TRUE,_8,_24) (Same Type. No op)
    val x46776 = LoadBanks(List(x45543_d0_b0), List(b30164, b30165)).name("x46776").ctrl(x46781).srcCtx("CellsPar.scala:245:45") // ParSRAMLoad(x45543,List(List(b30164, b30165)),List(x46750))
    val x46777 = x46776 // x46777 = VectorApply(x46776,0)
    val x46778 = OpDef(op=FixMul, inputs=List(x46775, x46777)).name("x46778").ctrl(x46781).srcCtx("CellsPar.scala:245:39") // FixMul(x46775,x46777)
    val x46779 = StoreBanks(List(x45538_d0_b0, x45538_d5_b0, x45538_d1_b0, x45538_d6_b0, x45538_d2_b0, x45538_d7_b0, x45538_d3_b0, x45538_d8_b0, x45538_d4_b0), List(b30164, b30165), x46778).name("x46779").ctrl(x46781).srcCtx("CellsPar.scala:245:16") // ParSRAMStore(x45538,List(List(b30164, b30165)),List(x46778),List(x46750))
    val x46780 = StoreBanks(List(x45539_d0_b0), List(b30164, b30165), x46761).name("x46780").ctrl(x46781).srcCtx("CellsPar.scala:246:16") // ParSRAMStore(x45539,List(List(b30164, b30165)),List(x46761),List(x46750))
    val x46782 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46782").ctrl(x46823).srcCtx("NMTDSE.scala:452:83") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46783 = ReadMem(x46272_d0).name("x46783").ctrl(x46823).srcCtx("NMTDSE.scala:449:16") // RegRead(x46272)
    val x46784 = Counter(min=Const(0), max=x46783, step=Const(1), par=1).name("x46784").ctrl(x46823).srcCtx("NMTDSE.scala:452:83") // CounterNew(Const(0),x46783,Const(1),Const(1))
    val x46785 = CounterChain(List(x46782,x46784)).name("x46785").ctrl(x46823).srcCtx("NMTDSE.scala:452:83") // CounterChainNew(List(x46782, x46784))
    val x46822 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46785).name("x46822").ctrl(x46823).srcCtx("NMTDSE.scala:452:83") // UnrolledForeach(List(b29006),x46785,Block(Const(())),List(List(b30205), List(b30206)),List(List(b30207), List(b30208)))
    val b30205 = CounterIter(x46782, Some(0)).name("b30205").ctrl(x46822) // b30205
    val b30207 = Const(true).name("b30207").ctrl(x46822) // b30207
    val b30206 = CounterIter(x46784, Some(0)).name("b30206").ctrl(x46822) // b30206
    val b30208 = Const(true).name("b30208").ctrl(x46822) // b30208
    val b48468 = StreamOut(field="offset").name("b48468").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // x46786 = StreamOutNew(BurstCmdBus)
    isAccum(b48468) = false
    bufferDepthOf(b48468) = 1
    val b48469 = StreamOut(field="size").name("b48469").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // x46786 = StreamOutNew(BurstCmdBus)
    isAccum(b48469) = false
    bufferDepthOf(b48469) = 1
    val x46787 = StreamOut(field="data").name("x46787").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // x46787 = StreamOutNew(BurstFullDataBus())
    isAccum(x46787) = false
    bufferDepthOf(x46787) = 1
    val x46788 = StreamIn(field="ack").name("x46788").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // x46788 = StreamInNew(BurstAckBus)
    isAccum(x46788) = false
    bufferDepthOf(x46788) = 1
    val x46806 = UnitController(style=SeqPipe, level=InnerControl).name("x46806").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // UnitPipe(List(b30207, b30208, b29006),Block(x46805))
    val x46789 = ReadMem(x46271_d0).name("x46789").ctrl(x46806).srcCtx("NMTDSE.scala:449:16") // RegRead(x46271)
    val x46790 = OpDef(op=FixAdd, inputs=List(x46789, b30206)).name("x46790").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixAdd(x46789,b30206)
    val x46791 = b30205 // FixConvert(b30205,TRUE,_32,_0) (Same Type. No op)
    val x46792 = OpDef(op=FixSla, inputs=List(x46791, Const(11))).name("x46792").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixLsh(x46791,Const(11))
    val x46793 = x46790 // FixConvert(x46790,TRUE,_32,_0) (Same Type. No op)
    val x46794 = OpDef(op=FixSla, inputs=List(x46793, Const(9))).name("x46794").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixLsh(x46793,Const(9))
    val x46795 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x46796 = OpDef(op=FixAdd, inputs=List(x46792, x46794)).name("x46796").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixAdd(x46792,x46794)
    val x46797 = OpDef(op=FixAdd, inputs=List(x46796, x46795)).name("x46797").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixAdd(x46796,x46795)
    val x46798 = OpDef(op=FixSla, inputs=List(x46797, Const(2))).name("x46798").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixLsh(x46797,Const(2))
    val x46799 = x46798 // FixConvert(x46798,TRUE,_64,_0)
    val x46800 = DramAddress(x45148).name("x46800").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // GetDRAMAddress(x45148)
    val x46802_x46801 = OpDef(op=FixAdd, inputs=List(x46799, x46800)).name("x46802_x46801").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // FixAdd(x46799,x46800)
    // x46802 = SimpleStruct(ArrayBuffer((offset,x46801), (size,Const(2048)), (isLoad,Const(false))))
    val x46803 = OpDef(op=BitAnd, inputs=List(b30207, b30208)).name("x46803").ctrl(x46806).srcCtx("UnrollingBase.scala:28:66") // And(b30207,b30208)
    val x46804 = OpDef(op=BitAnd, inputs=List(x46803, b29006)).name("x46804").ctrl(x46806).srcCtx("UnrollingBase.scala:28:66") // And(x46803,b29006)
    val x46805_b48470_b48468 = WriteMem(b48468, x46802_x46801).name("x46805_b48470_b48468").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // StreamWrite(x46786,x46802,x46804)
    val x46805_b48471_b48469 = WriteMem(b48469, Const(2048)).name("x46805_b48471_b48469").ctrl(x46806).srcCtx("NMTDSE.scala:452:83") // StreamWrite(x46786,x46802,x46804)
    val x46807 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46807").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46808 = CounterChain(List(x46807)).name("x46808").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // CounterChainNew(List(x46807))
    val x46816 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46808).name("x46816").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // UnrolledForeach(List(b30207, b30208, b29006),x46808,Block(Const(())),List(List(b30232)),List(List(b30233)))
    val b30232 = CounterIter(x46807, None).name("b30232").ctrl(x46816) // b30232
    val b30233 = Const(true).name("b30233").ctrl(x46816) // b30233
    val x46809 = OpDef(op=BitAnd, inputs=List(b30233, b30207)).name("x46809").ctrl(x46816).srcCtx("UnrollingBase.scala:28:66") // And(b30233,b30207)
    val x46810 = OpDef(op=BitAnd, inputs=List(b30208, b29006)).name("x46810").ctrl(x46816).srcCtx("UnrollingBase.scala:28:66") // And(b30208,b29006)
    val x46811 = OpDef(op=BitAnd, inputs=List(x46809, x46810)).name("x46811").ctrl(x46816).srcCtx("UnrollingBase.scala:28:66") // And(x46809,x46810)
    val x46812 = LoadBanks(List(x45538_d4_b0), List(b30205, b30232)).name("x46812").ctrl(x46816).srcCtx("NMTDSE.scala:452:83") // ParSRAMLoad(x45538,List(List(b30205, b30232)),List(x46811))
    val x46814_x46813 = x46812 // x46813 = VectorApply(x46812,0)
    // x46814 = SimpleStruct(ArrayBuffer((_1,x46813), (_2,Const(true))))
    val x46815_x46815_x46787 = WriteMem(x46787, x46814_x46813).name("x46815_x46815_x46787").ctrl(x46816).srcCtx("NMTDSE.scala:452:83") // ParStreamWrite(x46787,List(x46814),List(x46811))
    val x46817 = FringeDenseStore(dram=List(x45148), cmdStream=List(b48468, b48469), dataStream=List(x46787), ackStream=List(x46788)).name("x46817").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // FringeDenseStore(x45148,x46786,x46787,x46788)
    val x46821 = UnitController(style=SeqPipe, level=InnerControl).name("x46821").ctrl(x46822).srcCtx("NMTDSE.scala:452:83") // UnitPipe(List(b30207, b30208, b29006),Block(Const(())))
    val x46818 = OpDef(op=BitAnd, inputs=List(b30207, b30208)).name("x46818").ctrl(x46821).srcCtx("UnrollingBase.scala:28:66") // And(b30207,b30208)
    val x46819 = OpDef(op=BitAnd, inputs=List(x46818, b29006)).name("x46819").ctrl(x46821).srcCtx("UnrollingBase.scala:28:66") // And(x46818,b29006)
    val x46820_x46820 = ReadMem(x46788).name("x46820_x46820").ctrl(x46821).srcCtx("NMTDSE.scala:452:83") // StreamRead(x46788,x46819)
    val x46825 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x46825").ctrl(x48394).srcCtx("NMTDSE.scala:457:34") // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x46826 = CounterChain(List(x46825)).name("x46826").ctrl(x48394).srcCtx("NMTDSE.scala:457:40") // CounterChainNew(List(x46825))
    val x48337 = LoopController(style=SeqPipe, level=OuterControl, cchain=x46826).name("x48337").ctrl(x48394).srcCtx("NMTDSE.scala:457:40") // UnrolledForeach(List(Const(true)),x46826,Block(Const(())),List(List(b30253)),List(List(b30254)))
    val b30253 = CounterIter(x46825, Some(0)).name("b30253").ctrl(x48337) // b30253
    val b30254 = Const(true).name("b30254").ctrl(x48337) // b30254
    val x46828 = UnitController(style=SeqPipe, level=InnerControl).name("x46828").ctrl(x48337).srcCtx("NMTDSE.scala:458:18") // UnitPipe(List(b30254),Block(Const(())))
    val x46827 = OpDef(op=FixAdd, inputs=List(b30253, Const(1))).name("x46827").ctrl(x46828).srcCtx("NMTDSE.scala:459:38") // FixAdd(b30253,Const(1))
    val x46829 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46829").ctrl(x48337).srcCtx("NMTDSE.scala:459:20") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46830 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46830").ctrl(x48337).srcCtx("NMTDSE.scala:459:20") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46831 = CounterChain(List(x46829,x46830)).name("x46831").ctrl(x48337).srcCtx("NMTDSE.scala:459:20") // CounterChainNew(List(x46829, x46830))
    val x46861 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46831).name("x46861").ctrl(x48337).srcCtx("NMTDSE.scala:459:20") // UnrolledForeach(List(b30254),x46831,Block(Const(())),List(List(b30260), List(b30261)),List(List(b30262), List(b30263)))
    val b30260 = CounterIter(x46829, Some(0)).name("b30260").ctrl(x46861) // b30260
    val b30262 = Const(true).name("b30262").ctrl(x46861) // b30262
    val b30261 = CounterIter(x46830, Some(0)).name("b30261").ctrl(x46861) // b30261
    val b30263 = Const(true).name("b30263").ctrl(x46861) // b30263
    val b48472 = StreamOut(field="offset").name("b48472").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // x46832 = StreamOutNew(BurstCmdBus)
    isAccum(b48472) = false
    bufferDepthOf(b48472) = 1
    val b48473 = StreamOut(field="size").name("b48473").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // x46832 = StreamOutNew(BurstCmdBus)
    isAccum(b48473) = false
    bufferDepthOf(b48473) = 1
    val x46833 = StreamIn(field="data").name("x46833").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // x46833 = StreamInNew(BurstDataBus())
    isAccum(x46833) = false
    bufferDepthOf(x46833) = 1
    val x46850 = UnitController(style=SeqPipe, level=InnerControl).name("x46850").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // UnitPipe(List(b30262, b30263, b30254),Block(x46849))
    val x46834 = OpDef(op=FixAdd, inputs=List(b30253, b30261)).name("x46834").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixAdd(b30253,b30261)
    val x46835 = b30260 // FixConvert(b30260,TRUE,_32,_0) (Same Type. No op)
    val x46836 = OpDef(op=FixSla, inputs=List(x46835, Const(11))).name("x46836").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixLsh(x46835,Const(11))
    val x46837 = x46834 // FixConvert(x46834,TRUE,_32,_0) (Same Type. No op)
    val x46838 = OpDef(op=FixSla, inputs=List(x46837, Const(9))).name("x46838").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixLsh(x46837,Const(9))
    val x46839 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x46840 = OpDef(op=FixAdd, inputs=List(x46836, x46838)).name("x46840").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixAdd(x46836,x46838)
    val x46841 = OpDef(op=FixAdd, inputs=List(x46840, x46839)).name("x46841").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixAdd(x46840,x46839)
    val x46842 = OpDef(op=FixSla, inputs=List(x46841, Const(2))).name("x46842").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixLsh(x46841,Const(2))
    val x46843 = x46842 // FixConvert(x46842,TRUE,_64,_0)
    val x46844 = DramAddress(x45147).name("x46844").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // GetDRAMAddress(x45147)
    val x46846_x46845 = OpDef(op=FixAdd, inputs=List(x46843, x46844)).name("x46846_x46845").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // FixAdd(x46843,x46844)
    // x46846 = SimpleStruct(ArrayBuffer((offset,x46845), (size,Const(2048)), (isLoad,Const(true))))
    val x46847 = OpDef(op=BitAnd, inputs=List(b30262, b30263)).name("x46847").ctrl(x46850).srcCtx("UnrollingBase.scala:28:66") // And(b30262,b30263)
    val x46848 = OpDef(op=BitAnd, inputs=List(x46847, b30254)).name("x46848").ctrl(x46850).srcCtx("UnrollingBase.scala:28:66") // And(x46847,b30254)
    val x46849_b48474_b48472 = WriteMem(b48472, x46846_x46845).name("x46849_b48474_b48472").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // StreamWrite(x46832,x46846,x46848)
    val x46849_b48475_b48473 = WriteMem(b48473, Const(2048)).name("x46849_b48475_b48473").ctrl(x46850).srcCtx("NMTDSE.scala:459:20") // StreamWrite(x46832,x46846,x46848)
    val x46851 = FringeDenseLoad(dram=List(x45147), cmdStream=List(b48472, b48473), dataStream=List(x46833)).name("x46851").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // FringeDenseLoad(x45147,x46832,x46833)
    val x46852 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46852").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46853 = CounterChain(List(x46852)).name("x46853").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // CounterChainNew(List(x46852))
    val x46860 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46853).name("x46860").ctrl(x46861).srcCtx("NMTDSE.scala:459:20") // UnrolledForeach(List(b30262, b30263, b30254),x46853,Block(Const(())),List(List(b30286)),List(List(b30287)))
    val b30286 = CounterIter(x46852, None).name("b30286").ctrl(x46860) // b30286
    val b30287 = Const(true).name("b30287").ctrl(x46860) // b30287
    val x46854 = OpDef(op=BitAnd, inputs=List(b30287, b30262)).name("x46854").ctrl(x46860).srcCtx("UnrollingBase.scala:28:66") // And(b30287,b30262)
    val x46855 = OpDef(op=BitAnd, inputs=List(b30263, b30254)).name("x46855").ctrl(x46860).srcCtx("UnrollingBase.scala:28:66") // And(b30263,b30254)
    val x46856 = OpDef(op=BitAnd, inputs=List(x46854, x46855)).name("x46856").ctrl(x46860).srcCtx("UnrollingBase.scala:28:66") // And(x46854,x46855)
    val x46857_x46857 = ReadMem(x46833).name("x46857_x46857").ctrl(x46860).srcCtx("NMTDSE.scala:459:20") // ParStreamRead(x46833,List(x46856))
    val x46858_x46858 = x46857_x46857 // x46858 = VectorApply(x46857,0)
    val x46859 = StoreBanks(List(x45530_d0_b0, x45530_d5_b0, x45530_d1_b0, x45530_d6_b0, x45530_d2_b0, x45530_d7_b0, x45530_d3_b0, x45530_d8_b0, x45530_d4_b0), List(b30260, b30286), x46858_x46858).name("x46859").ctrl(x46860).srcCtx("NMTDSE.scala:459:20") // ParSRAMStore(x45530,List(List(b30260, b30286)),List(x46858),List(x46856))
    val x46862 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46862").ctrl(x48337).srcCtx("NMTDSE.scala:460:20") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46863 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46863").ctrl(x48337).srcCtx("NMTDSE.scala:460:20") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46864 = CounterChain(List(x46862,x46863)).name("x46864").ctrl(x48337).srcCtx("NMTDSE.scala:460:20") // CounterChainNew(List(x46862, x46863))
    val x46894 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46864).name("x46894").ctrl(x48337).srcCtx("NMTDSE.scala:460:20") // UnrolledForeach(List(b30254),x46864,Block(Const(())),List(List(b30299), List(b30300)),List(List(b30301), List(b30302)))
    val b30299 = CounterIter(x46862, Some(0)).name("b30299").ctrl(x46894) // b30299
    val b30301 = Const(true).name("b30301").ctrl(x46894) // b30301
    val b30300 = CounterIter(x46863, Some(0)).name("b30300").ctrl(x46894) // b30300
    val b30302 = Const(true).name("b30302").ctrl(x46894) // b30302
    val b48476 = StreamOut(field="offset").name("b48476").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // x46865 = StreamOutNew(BurstCmdBus)
    isAccum(b48476) = false
    bufferDepthOf(b48476) = 1
    val b48477 = StreamOut(field="size").name("b48477").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // x46865 = StreamOutNew(BurstCmdBus)
    isAccum(b48477) = false
    bufferDepthOf(b48477) = 1
    val x46866 = StreamIn(field="data").name("x46866").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // x46866 = StreamInNew(BurstDataBus())
    isAccum(x46866) = false
    bufferDepthOf(x46866) = 1
    val x46883 = UnitController(style=SeqPipe, level=InnerControl).name("x46883").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // UnitPipe(List(b30301, b30302, b30254),Block(x46882))
    val x46867 = OpDef(op=FixAdd, inputs=List(b30253, b30300)).name("x46867").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixAdd(b30253,b30300)
    val x46868 = b30299 // FixConvert(b30299,TRUE,_32,_0) (Same Type. No op)
    val x46869 = OpDef(op=FixSla, inputs=List(x46868, Const(11))).name("x46869").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixLsh(x46868,Const(11))
    val x46870 = x46867 // FixConvert(x46867,TRUE,_32,_0) (Same Type. No op)
    val x46871 = OpDef(op=FixSla, inputs=List(x46870, Const(9))).name("x46871").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixLsh(x46870,Const(9))
    val x46872 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x46873 = OpDef(op=FixAdd, inputs=List(x46869, x46871)).name("x46873").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixAdd(x46869,x46871)
    val x46874 = OpDef(op=FixAdd, inputs=List(x46873, x46872)).name("x46874").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixAdd(x46873,x46872)
    val x46875 = OpDef(op=FixSla, inputs=List(x46874, Const(2))).name("x46875").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixLsh(x46874,Const(2))
    val x46876 = x46875 // FixConvert(x46875,TRUE,_64,_0)
    val x46877 = DramAddress(x45148).name("x46877").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // GetDRAMAddress(x45148)
    val x46879_x46878 = OpDef(op=FixAdd, inputs=List(x46876, x46877)).name("x46879_x46878").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // FixAdd(x46876,x46877)
    // x46879 = SimpleStruct(ArrayBuffer((offset,x46878), (size,Const(2048)), (isLoad,Const(true))))
    val x46880 = OpDef(op=BitAnd, inputs=List(b30301, b30302)).name("x46880").ctrl(x46883).srcCtx("UnrollingBase.scala:28:66") // And(b30301,b30302)
    val x46881 = OpDef(op=BitAnd, inputs=List(x46880, b30254)).name("x46881").ctrl(x46883).srcCtx("UnrollingBase.scala:28:66") // And(x46880,b30254)
    val x46882_b48478_b48476 = WriteMem(b48476, x46879_x46878).name("x46882_b48478_b48476").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // StreamWrite(x46865,x46879,x46881)
    val x46882_b48479_b48477 = WriteMem(b48477, Const(2048)).name("x46882_b48479_b48477").ctrl(x46883).srcCtx("NMTDSE.scala:460:20") // StreamWrite(x46865,x46879,x46881)
    val x46884 = FringeDenseLoad(dram=List(x45148), cmdStream=List(b48476, b48477), dataStream=List(x46866)).name("x46884").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // FringeDenseLoad(x45148,x46865,x46866)
    val x46885 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x46885").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x46886 = CounterChain(List(x46885)).name("x46886").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // CounterChainNew(List(x46885))
    val x46893 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46886).name("x46893").ctrl(x46894).srcCtx("NMTDSE.scala:460:20") // UnrolledForeach(List(b30301, b30302, b30254),x46886,Block(Const(())),List(List(b30325)),List(List(b30326)))
    val b30325 = CounterIter(x46885, None).name("b30325").ctrl(x46893) // b30325
    val b30326 = Const(true).name("b30326").ctrl(x46893) // b30326
    val x46887 = OpDef(op=BitAnd, inputs=List(b30326, b30301)).name("x46887").ctrl(x46893).srcCtx("UnrollingBase.scala:28:66") // And(b30326,b30301)
    val x46888 = OpDef(op=BitAnd, inputs=List(b30302, b30254)).name("x46888").ctrl(x46893).srcCtx("UnrollingBase.scala:28:66") // And(b30302,b30254)
    val x46889 = OpDef(op=BitAnd, inputs=List(x46887, x46888)).name("x46889").ctrl(x46893).srcCtx("UnrollingBase.scala:28:66") // And(x46887,x46888)
    val x46890_x46890 = ReadMem(x46866).name("x46890_x46890").ctrl(x46893).srcCtx("NMTDSE.scala:460:20") // ParStreamRead(x46866,List(x46889))
    val x46891_x46891 = x46890_x46890 // x46891 = VectorApply(x46890,0)
    val x46892 = StoreBanks(List(x45538_d0_b0, x45538_d5_b0, x45538_d1_b0, x45538_d6_b0, x45538_d2_b0, x45538_d7_b0, x45538_d3_b0, x45538_d8_b0, x45538_d4_b0), List(b30299, b30325), x46891_x46891).name("x46892").ctrl(x46893).srcCtx("NMTDSE.scala:460:20") // ParSRAMStore(x45538,List(List(b30299, b30325)),List(x46891),List(x46889))
    val x46895 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x46895").ctrl(x48337).srcCtx("CellsPar.scala:296:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x46896 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x46896").ctrl(x48337).srcCtx("CellsPar.scala:296:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x46897 = CounterChain(List(x46896,x46895)).name("x46897").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // CounterChainNew(List(x46896, x46895))
    val x47010 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46897).name("x47010").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // UnrolledForeach(List(b30254),x46897,Block(Const(())),List(List(b30339), List(b30340)),List(List(b30341), List(b30342)))
    val b30339 = CounterIter(x46896, Some(0)).name("b30339").ctrl(x47010) // b30339
    val b30341 = Const(true).name("b30341").ctrl(x47010) // b30341
    val b30340 = CounterIter(x46895, Some(0)).name("b30340").ctrl(x47010) // b30340
    val b30342 = Const(true).name("b30342").ctrl(x47010) // b30342
    val x46898_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x46898_d0_b0").ctrl(x47010).srcCtx("CellsPar.scala:297:33:tileKernel") // x46898 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x46898_d0_b0) = false
    bufferDepthOf(x46898_d0_b0) = 2
    val x46901 = UnitController(style=SeqPipe, level=InnerControl).name("x46901").ctrl(x47010).srcCtx("CellsPar.scala:296:72") // UnitPipe(List(b30341, b30342, b30254),Block(Const(())))
    val x46899 = OpDef(op=FixAdd, inputs=List(b30339, Const(16))).name("x46899").ctrl(x46901).srcCtx("CellsPar.scala:298:36") // FixAdd(b30339,Const(16))
    val x46900 = OpDef(op=FixAdd, inputs=List(b30340, Const(16))).name("x46900").ctrl(x46901).srcCtx("CellsPar.scala:298:45") // FixAdd(b30340,Const(16))
    val x46902 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46902").ctrl(x47010).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46903 = CounterChain(List(x46902)).name("x46903").ctrl(x47010).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x46902))
    val x46932 = LoopController(style=StreamPipe, level=OuterControl, cchain=x46903).name("x46932").ctrl(x47010).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30341, b30342, b30254),x46903,Block(Const(())),List(List(b30349)),List(List(b30350)))
    val b30349 = CounterIter(x46902, Some(0)).name("b30349").ctrl(x46932) // b30349
    val b30350 = Const(true).name("b30350").ctrl(x46932) // b30350
    val b48480 = StreamOut(field="offset").name("b48480").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // x46904 = StreamOutNew(BurstCmdBus)
    isAccum(b48480) = false
    bufferDepthOf(b48480) = 1
    val b48481 = StreamOut(field="size").name("b48481").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // x46904 = StreamOutNew(BurstCmdBus)
    isAccum(b48481) = false
    bufferDepthOf(b48481) = 1
    val x46905 = StreamIn(field="data").name("x46905").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // x46905 = StreamInNew(BurstDataBus())
    isAccum(x46905) = false
    bufferDepthOf(x46905) = 1
    val x46920 = UnitController(style=SeqPipe, level=InnerControl).name("x46920").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // UnitPipe(List(b30350, b30341, b30342, b30254),Block(x46919))
    val x46906 = OpDef(op=FixAdd, inputs=List(b30339, b30349)).name("x46906").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // FixAdd(b30339,b30349)
    val x46907 = x46906 // FixConvert(x46906,TRUE,_32,_0) (Same Type. No op)
    val x46908 = OpDef(op=FixSla, inputs=List(x46907, Const(9))).name("x46908").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // FixLsh(x46907,Const(9))
    val x46909 = b30340 // FixConvert(b30340,TRUE,_32,_0) (Same Type. No op)
    val x46910 = OpDef(op=FixAdd, inputs=List(x46908, x46909)).name("x46910").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // FixAdd(x46908,x46909)
    val x46911 = OpDef(op=FixSla, inputs=List(x46910, Const(2))).name("x46911").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // FixLsh(x46910,Const(2))
    val x46912 = x46911 // FixConvert(x46911,TRUE,_64,_0)
    val x46913 = DramAddress(x45329).name("x46913").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // GetDRAMAddress(x45329)
    val x46915_x46914 = OpDef(op=FixAdd, inputs=List(x46912, x46913)).name("x46915_x46914").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // FixAdd(x46912,x46913)
    // x46915 = SimpleStruct(ArrayBuffer((offset,x46914), (size,Const(64)), (isLoad,Const(true))))
    val x46916 = OpDef(op=BitAnd, inputs=List(b30350, b30341)).name("x46916").ctrl(x46920).srcCtx("UnrollingBase.scala:28:66") // And(b30350,b30341)
    val x46917 = OpDef(op=BitAnd, inputs=List(b30342, b30254)).name("x46917").ctrl(x46920).srcCtx("UnrollingBase.scala:28:66") // And(b30342,b30254)
    val x46918 = OpDef(op=BitAnd, inputs=List(x46916, x46917)).name("x46918").ctrl(x46920).srcCtx("UnrollingBase.scala:28:66") // And(x46916,x46917)
    val x46919_b48482_b48480 = WriteMem(b48480, x46915_x46914).name("x46919_b48482_b48480").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // StreamWrite(x46904,x46915,x46918)
    val x46919_b48483_b48481 = WriteMem(b48481, Const(64)).name("x46919_b48483_b48481").ctrl(x46920).srcCtx("CellsPar.scala:298:20") // StreamWrite(x46904,x46915,x46918)
    val x46921 = FringeDenseLoad(dram=List(x45329), cmdStream=List(b48480, b48481), dataStream=List(x46905)).name("x46921").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // FringeDenseLoad(x45329,x46904,x46905)
    val x46922 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46922").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46923 = CounterChain(List(x46922)).name("x46923").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x46922))
    val x46931 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46923).name("x46931").ctrl(x46932).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30350, b30341, b30342, b30254),x46923,Block(Const(())),List(List(b30371)),List(List(b30372)))
    val b30371 = CounterIter(x46922, None).name("b30371").ctrl(x46931) // b30371
    val b30372 = Const(true).name("b30372").ctrl(x46931) // b30372
    val x46924 = OpDef(op=BitAnd, inputs=List(b30372, b30350)).name("x46924").ctrl(x46931).srcCtx("UnrollingBase.scala:28:66") // And(b30372,b30350)
    val x46925 = OpDef(op=BitAnd, inputs=List(b30341, b30342)).name("x46925").ctrl(x46931).srcCtx("UnrollingBase.scala:28:66") // And(b30341,b30342)
    val x46926 = OpDef(op=BitAnd, inputs=List(x46924, x46925)).name("x46926").ctrl(x46931).srcCtx("UnrollingBase.scala:28:66") // And(x46924,x46925)
    val x46927 = OpDef(op=BitAnd, inputs=List(x46926, b30254)).name("x46927").ctrl(x46931).srcCtx("UnrollingBase.scala:28:66") // And(x46926,b30254)
    val x46928_x46928 = ReadMem(x46905).name("x46928_x46928").ctrl(x46931).srcCtx("CellsPar.scala:298:20") // ParStreamRead(x46905,List(x46927))
    val x46929_x46929 = x46928_x46928 // x46929 = VectorApply(x46928,0)
    val x46930 = StoreBanks(List(x46898_d0_b0), List(b30349, b30371), x46929_x46929).name("x46930").ctrl(x46931).srcCtx("CellsPar.scala:298:20") // ParSRAMStore(x46898,List(List(b30349, b30371)),List(x46929),List(x46927))
    val x46933 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x46933").ctrl(x47010).srcCtx("CellsPar.scala:299:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x46934 = CounterChain(List(x46933)).name("x46934").ctrl(x47010).srcCtx("CellsPar.scala:299:46") // CounterChainNew(List(x46933))
    val x47009 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46934).name("x47009").ctrl(x47010).srcCtx("CellsPar.scala:299:46") // UnrolledForeach(List(b30341, b30342, b30254),x46934,Block(Const(())),List(List(b30384)),List(List(b30385)))
    val b30384 = CounterIter(x46933, Some(0)).name("b30384").ctrl(x47009) // b30384
    val b30385 = Const(true).name("b30385").ctrl(x47009) // b30385
    val x46935 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46935").ctrl(x47009).srcCtx("CellsPar.scala:300:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46936 = CounterChain(List(x46935)).name("x46936").ctrl(x47009).srcCtx("CellsPar.scala:300:42") // CounterChainNew(List(x46935))
    val x47008 = LoopController(style=MetaPipe, level=OuterControl, cchain=x46936).name("x47008").ctrl(x47009).srcCtx("CellsPar.scala:300:42") // UnrolledForeach(List(b30385, b30341, b30342, b30254),x46936,Block(Const(())),List(List(b30388)),List(List(b30389)))
    val b30388 = CounterIter(x46935, Some(0)).name("b30388").ctrl(x47008) // b30388
    val b30389 = Const(true).name("b30389").ctrl(x47008) // b30389
    val x46937_d0 = Reg(init=Some(0.0)).name("x46937_d0").ctrl(x47008).srcCtx("CellsPar.scala:301:34:prod") // x46937 = RegNew(Const(0))
    isAccum(x46937_d0) = false
    bufferDepthOf(x46937_d0) = 2
    val x46937_d1 = Reg(init=Some(0.0)).name("x46937_d1").ctrl(x47008).srcCtx("CellsPar.scala:301:34:prod") // x46937 = RegNew(Const(0))
    isAccum(x46937_d1) = true
    bufferDepthOf(x46937_d1) = 1
    val x46938 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x46938").ctrl(x47008).srcCtx("CellsPar.scala:301:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x46939 = CounterChain(List(x46938)).name("x46939").ctrl(x47008).srcCtx("CellsPar.scala:321:15") // CounterChainNew(List(x46938))
    val x46973 = LoopController(style=InnerPipe, level=InnerControl, cchain=x46939).name("x46973").ctrl(x47008).srcCtx("CellsPar.scala:321:15") // UnrolledReduce(List(b30389, b30385, b30341, b30342, b30254),x46939,x46937,Block((x46937) => Const(())),List(List(b30393)),List(List(b30394)))
    val b30393 = CounterIter(x46938, None).name("b30393").ctrl(x46973) // b30393
    val b30394 = Const(true).name("b30394").ctrl(x46973) // b30394
    val x46940 = OpDef(op=FixAdd, inputs=List(b30339, b30393)).name("x46940").ctrl(x46973).srcCtx("CellsPar.scala:302:42:reduce_size_offset") // FixAdd(b30339,b30393)
    val x46941 = OpDef(op=BitAnd, inputs=List(b30394, b30389)).name("x46941").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(b30394,b30389)
    val x46942 = OpDef(op=BitAnd, inputs=List(b30385, b30341)).name("x46942").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(b30385,b30341)
    val x46943 = OpDef(op=BitAnd, inputs=List(b30342, b30254)).name("x46943").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(b30342,b30254)
    val x46944 = OpDef(op=BitAnd, inputs=List(x46941, x46942)).name("x46944").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(x46941,x46942)
    val x46945 = OpDef(op=BitAnd, inputs=List(x46944, x46943)).name("x46945").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(x46944,x46943)
    val x46946 = LoadBanks(List(x46898_d0_b0), List(b30393, b30388)).name("x46946").ctrl(x46973).srcCtx("CellsPar.scala:311:34:tk") // ParSRAMLoad(x46898,List(List(b30393, b30388)),List(x46945))
    val x46947 = x46946 // x46947 = VectorApply(x46946,0)
    val x46948 = OpDef(op=FixLt, inputs=List(x46940, Const(512))).name("x46948").ctrl(x46973).srcCtx("CellsPar.scala:313:36") // FixLt(x46940,Const(512))
    val x46949 = LoadBanks(List(x45530_d3_b0), List(b30384, x46940)).name("x46949").ctrl(x46973).srcCtx("CellsPar.scala:314:19") // ParSRAMLoad(x45530,List(List(b30384, x46940)),List(x46945))
    val x46950 = x46949 // x46950 = VectorApply(x46949,0)
    val x46951 = OpDef(op=FixMul, inputs=List(x46950, x46947)).name("x46951").ctrl(x46973).srcCtx("CellsPar.scala:314:43") // FixMul(x46950,x46947)
    val x46952 = OpDef(op=FixLeq, inputs=List(Const(512), x46940)).name("x46952").ctrl(x46973).srcCtx("CellsPar.scala:316:32") // FixLeq(Const(512),x46940)
    val x46953 = OpDef(op=FixLt, inputs=List(x46940, Const(1024))).name("x46953").ctrl(x46973).srcCtx("CellsPar.scala:316:76") // FixLt(x46940,Const(1024))
    val x46954 = OpDef(op=BitAnd, inputs=List(x46952, x46953)).name("x46954").ctrl(x46973).srcCtx("CellsPar.scala:316:54") // And(x46952,x46953)
    val x46955 = OpDef(op=FixSub, inputs=List(x46940, Const(512))).name("x46955").ctrl(x46973).srcCtx("CellsPar.scala:317:44") // FixSub(x46940,Const(512))
    val x46956 = LoadBanks(List(x45538_d3_b0), List(b30384, x46955)).name("x46956").ctrl(x46973).srcCtx("CellsPar.scala:317:21") // ParSRAMLoad(x45538,List(List(b30384, x46955)),List(x46945))
    val x46957 = x46956 // x46957 = VectorApply(x46956,0)
    val x46958 = OpDef(op=FixMul, inputs=List(x46957, x46947)).name("x46958").ctrl(x46973).srcCtx("CellsPar.scala:317:60") // FixMul(x46957,x46947)
    val x46959 = OpDef(op=FixSub, inputs=List(x46940, Const(1024))).name("x46959").ctrl(x46973).srcCtx("CellsPar.scala:318:43") // FixSub(x46940,Const(1024))
    val x46960 = LoadBanks(List(x45545_d8_b0), List(b30384, x46959)).name("x46960").ctrl(x46973).srcCtx("CellsPar.scala:318:20") // ParSRAMLoad(x45545,List(List(b30384, x46959)),List(x46945))
    val x46961 = x46960 // x46961 = VectorApply(x46960,0)
    val x46962 = OpDef(op=FixMul, inputs=List(x46961, x46947)).name("x46962").ctrl(x46973).srcCtx("CellsPar.scala:318:60") // FixMul(x46961,x46947)
    val x46963 = OpDef(op=MuxOp, inputs=List(x46954, x46958, x46962)).name("x46963").ctrl(x46973).srcCtx("CellsPar.scala:315:20") // Mux(x46954,x46958,x46962)
    val x46964 = OpDef(op=MuxOp, inputs=List(x46948, x46951, x46963)).name("x46964").ctrl(x46973).srcCtx("CellsPar.scala:312:18") // Mux(x46948,x46951,x46963)
    val x46965 = ReadMem(x46937_d1).name("x46965").ctrl(x46973).srcCtx("CellsPar.scala:321:15") // RegRead(x46937)
    val x46966 = OpDef(op=FixEql, inputs=List(b30393, Const(0))).name("x46966").ctrl(x46973).srcCtx("CellsPar.scala:321:15") // FixEql(b30393,Const(0))
    val x46967 = ReduceAccumOp(op=FixAdd, input=x46964, accum=x46965).name("x46967").ctrl(x46973).srcCtx("CellsPar.scala:321:17") // FixAdd(x46964,x46965)
    val x46968 = OpDef(op=BitAnd, inputs=List(b30389, b30385)).name("x46968").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(b30389,b30385)
    val x46969 = OpDef(op=BitAnd, inputs=List(b30341, b30342)).name("x46969").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(b30341,b30342)
    val x46970 = OpDef(op=BitAnd, inputs=List(x46968, x46969)).name("x46970").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(x46968,x46969)
    val x46971 = OpDef(op=BitAnd, inputs=List(x46970, b30254)).name("x46971").ctrl(x46973).srcCtx("UnrollingBase.scala:28:66") // And(x46970,b30254)
    val x46972_x46937_d0 = WriteMem(x46937_d0, x46967).name("x46972_x46937_d0").ctrl(x46973).srcCtx("CellsPar.scala:321:15") // RegWrite(x46937,x46967,x46971)
    val x46972_x46937_d1 = WriteMem(x46937_d1, x46967).name("x46972_x46937_d1").ctrl(x46973).srcCtx("CellsPar.scala:321:15") // RegWrite(x46937,x46967,x46971)
    val x47007 = UnitController(style=SeqPipe, level=InnerControl).name("x47007").ctrl(x47008).srcCtx("CellsPar.scala:300:42") // UnitPipe(List(b30389, b30385, b30341, b30342, b30254),Block(Const(())))
    val x46974 = OpDef(op=FixAdd, inputs=List(b30340, b30388)).name("x46974").ctrl(x47007).srcCtx("CellsPar.scala:323:31:colOffset") // FixAdd(b30340,b30388)
    val x46975 = ReadMem(x46937_d0).name("x46975").ctrl(x47007).srcCtx("CellsPar.scala:324:28") // RegRead(x46937)
    val x46976 = OpDef(op=FixEql, inputs=List(b30339, Const(0))).name("x46976").ctrl(x47007).srcCtx("CellsPar.scala:324:42") // FixEql(b30339,Const(0))
    val x46977 = OpDef(op=BitAnd, inputs=List(b30389, b30385)).name("x46977").ctrl(x47007).srcCtx("UnrollingBase.scala:28:66") // And(b30389,b30385)
    val x46978 = OpDef(op=BitAnd, inputs=List(b30341, b30342)).name("x46978").ctrl(x47007).srcCtx("UnrollingBase.scala:28:66") // And(b30341,b30342)
    val x46979 = OpDef(op=BitAnd, inputs=List(x46977, x46978)).name("x46979").ctrl(x47007).srcCtx("UnrollingBase.scala:28:66") // And(x46977,x46978)
    val x46980 = OpDef(op=BitAnd, inputs=List(x46979, b30254)).name("x46980").ctrl(x47007).srcCtx("UnrollingBase.scala:28:66") // And(x46979,b30254)
    val x46981 = LoadBanks(List(x45551_d3_b0), List(Const(0), x46974)).name("x46981").ctrl(x47007).srcCtx("CellsPar.scala:324:52") // SRAMLoad(x45551,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x46974),Const(0),x46980)
    val x46982 = LoadBanks(List(x45547_d1_b0), List(b30384, x46974)).name("x46982").ctrl(x47007).srcCtx("CellsPar.scala:324:91") // SRAMLoad(x45547,ArrayBuffer(Const(1), Const(512)),List(b30384, x46974),Const(0),x46980)
    val x46983 = OpDef(op=MuxOp, inputs=List(x46976, x46981, x46982)).name("x46983").ctrl(x47007).srcCtx("CellsPar.scala:324:39") // Mux(x46976,x46981,x46982)
    val x46984 = OpDef(op=FixAdd, inputs=List(x46975, x46983)).name("x46984").ctrl(x47007).srcCtx("CellsPar.scala:324:34:ele") // FixAdd(x46975,x46983)
    val x46985 = OpDef(op=FixLeq, inputs=List(Const(1520), b30339)).name("x46985").ctrl(x47007).srcCtx("CellsPar.scala:332:17") // FixLeq(Const(1520),b30339)
    // x46986 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x46986_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x46986_int1").ctrl(x47007).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x46986_int2 = OpDef(op=FixSla, inputs=List(x46986_int1, Const(24))).name("x46986_int2").ctrl(x47007).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x46986_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x46986_frac1").ctrl(x47007).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x46986_frac2 = OpDef(op=FixSla, inputs=List(x46986_frac1, Const(24))).name("x46986_frac2").ctrl(x47007).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x46986 = OpDef(op=BitOr, inputs=List(x46986_int2, x46986_frac2)).name("x46986").ctrl(x47007).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x46987 = OpDef(op=FixAdd, inputs=List(x46984, x46986)).name("x46987").ctrl(x47007).srcCtx("CellsPar.scala:333:30") // FixAdd(x46984,x46986)
    val x46988 = OpDef(op=FixSra, inputs=List(x46987, Const(1))).name("x46988").ctrl(x47007).srcCtx("CellsPar.scala:29:22") // FixRsh(x46987,Const(1))
    val x46989 = x46988 // FixConvert(x46988,TRUE,_8,_24) (Same Type. No op)
    val x46990 = OpDef(op=FixAbs, inputs=List(x46989)).name("x46990").ctrl(x47007).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x46989)
    val x46991 = OpDef(op=FixLt, inputs=List(Const(2.5), x46990)).name("x46991").ctrl(x47007).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x46990)
    val x46992 = OpDef(op=FixLt, inputs=List(Const(0.5), x46990)).name("x46992").ctrl(x47007).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x46990)
    val x46993 = OpDef(op=FixLeq, inputs=List(x46990, Const(2.5))).name("x46993").ctrl(x47007).srcCtx("CellsPar.scala:54:52") // FixLeq(x46990,Const(2.5))
    val x46994 = OpDef(op=BitAnd, inputs=List(x46992, x46993)).name("x46994").ctrl(x47007).srcCtx("CellsPar.scala:54:43:cond2") // And(x46992,x46993)
    val x46995 = OpDef(op=FixSra, inputs=List(x46990, Const(2))).name("x46995").ctrl(x47007).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x46990,Const(2))
    val x46996 = OpDef(op=FixAdd, inputs=List(x46995, Const(0.375))).name("x46996").ctrl(x47007).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x46995,Const(0.375))
    val x46997 = OpDef(op=MuxOp, inputs=List(x46994, x46996, x46990)).name("x46997").ctrl(x47007).srcCtx("CellsPar.scala:58:20:body2") // Mux(x46994,x46996,x46990)
    val x46998 = OpDef(op=MuxOp, inputs=List(x46991, Const(1.0), x46997)).name("x46998").ctrl(x47007).srcCtx("CellsPar.scala:60:20:absre") // Mux(x46991,Const(1),x46997)
    val x46999 = OpDef(op=FixNeg, inputs=List(x46998)).name("x46999").ctrl(x47007).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x46998)
    val x47000 = OpDef(op=FixLt, inputs=List(x46989, Const(0.0))).name("x47000").ctrl(x47007).srcCtx("CellsPar.scala:63:22") // FixLt(x46989,Const(0))
    val x47001 = OpDef(op=MuxOp, inputs=List(x47000, x46999, x46998)).name("x47001").ctrl(x47007).srcCtx("CellsPar.scala:63:17:re") // Mux(x47000,x46999,x46998)
    val x47002 = x47001 // FixConvert(x47001,TRUE,_8,_24) (Same Type. No op)
    val x47003 = OpDef(op=FixAdd, inputs=List(x47002, Const(1.0))).name("x47003").ctrl(x47007).srcCtx("CellsPar.scala:29:33") // FixAdd(x47002,Const(1))
    val x47004 = OpDef(op=FixSra, inputs=List(x47003, Const(1))).name("x47004").ctrl(x47007).srcCtx("CellsPar.scala:29:44") // FixRsh(x47003,Const(1))
    val x47005 = OpDef(op=MuxOp, inputs=List(x46985, x47004, x46984)).name("x47005").ctrl(x47007).srcCtx("CellsPar.scala:331:43") // Mux(x46985,x47004,x46984)
    val x47006 = StoreBanks(List(x45547_d0_b0, x45547_d1_b0), List(b30384, x46974), x47005).name("x47006").ctrl(x47007).srcCtx("CellsPar.scala:331:38") // SRAMStore(x45547,ArrayBuffer(Const(1), Const(512)),List(b30384, x46974),Const(0),x47005,x46980)
    val x47011 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47011").ctrl(x48337).srcCtx("CellsPar.scala:296:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47012 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x47012").ctrl(x48337).srcCtx("CellsPar.scala:296:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x47013 = CounterChain(List(x47012,x47011)).name("x47013").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // CounterChainNew(List(x47012, x47011))
    val x47124 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47013).name("x47124").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // UnrolledForeach(List(b30254),x47013,Block(Const(())),List(List(b30469), List(b30470)),List(List(b30471), List(b30472)))
    val b30469 = CounterIter(x47012, Some(0)).name("b30469").ctrl(x47124) // b30469
    val b30471 = Const(true).name("b30471").ctrl(x47124) // b30471
    val b30470 = CounterIter(x47011, Some(0)).name("b30470").ctrl(x47124) // b30470
    val b30472 = Const(true).name("b30472").ctrl(x47124) // b30472
    val x47014_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47014_d0_b0").ctrl(x47124).srcCtx("CellsPar.scala:297:33:tileKernel") // x47014 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47014_d0_b0) = false
    bufferDepthOf(x47014_d0_b0) = 2
    val x47017 = UnitController(style=SeqPipe, level=InnerControl).name("x47017").ctrl(x47124).srcCtx("CellsPar.scala:296:72") // UnitPipe(List(b30471, b30472, b30254),Block(Const(())))
    val x47015 = OpDef(op=FixAdd, inputs=List(b30469, Const(16))).name("x47015").ctrl(x47017).srcCtx("CellsPar.scala:298:36") // FixAdd(b30469,Const(16))
    val x47016 = OpDef(op=FixAdd, inputs=List(b30470, Const(16))).name("x47016").ctrl(x47017).srcCtx("CellsPar.scala:298:45") // FixAdd(b30470,Const(16))
    val x47018 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47018").ctrl(x47124).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47019 = CounterChain(List(x47018)).name("x47019").ctrl(x47124).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x47018))
    val x47048 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47019).name("x47048").ctrl(x47124).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30471, b30472, b30254),x47019,Block(Const(())),List(List(b30479)),List(List(b30480)))
    val b30479 = CounterIter(x47018, Some(0)).name("b30479").ctrl(x47048) // b30479
    val b30480 = Const(true).name("b30480").ctrl(x47048) // b30480
    val b48484 = StreamOut(field="offset").name("b48484").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // x47020 = StreamOutNew(BurstCmdBus)
    isAccum(b48484) = false
    bufferDepthOf(b48484) = 1
    val b48485 = StreamOut(field="size").name("b48485").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // x47020 = StreamOutNew(BurstCmdBus)
    isAccum(b48485) = false
    bufferDepthOf(b48485) = 1
    val x47021 = StreamIn(field="data").name("x47021").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // x47021 = StreamInNew(BurstDataBus())
    isAccum(x47021) = false
    bufferDepthOf(x47021) = 1
    val x47036 = UnitController(style=SeqPipe, level=InnerControl).name("x47036").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // UnitPipe(List(b30480, b30471, b30472, b30254),Block(x47035))
    val x47022 = OpDef(op=FixAdd, inputs=List(b30469, b30479)).name("x47022").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // FixAdd(b30469,b30479)
    val x47023 = x47022 // FixConvert(x47022,TRUE,_32,_0) (Same Type. No op)
    val x47024 = OpDef(op=FixSla, inputs=List(x47023, Const(9))).name("x47024").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // FixLsh(x47023,Const(9))
    val x47025 = b30470 // FixConvert(b30470,TRUE,_32,_0) (Same Type. No op)
    val x47026 = OpDef(op=FixAdd, inputs=List(x47024, x47025)).name("x47026").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // FixAdd(x47024,x47025)
    val x47027 = OpDef(op=FixSla, inputs=List(x47026, Const(2))).name("x47027").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // FixLsh(x47026,Const(2))
    val x47028 = x47027 // FixConvert(x47027,TRUE,_64,_0)
    val x47029 = DramAddress(x45330).name("x47029").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // GetDRAMAddress(x45330)
    val x47031_x47030 = OpDef(op=FixAdd, inputs=List(x47028, x47029)).name("x47031_x47030").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // FixAdd(x47028,x47029)
    // x47031 = SimpleStruct(ArrayBuffer((offset,x47030), (size,Const(64)), (isLoad,Const(true))))
    val x47032 = OpDef(op=BitAnd, inputs=List(b30480, b30471)).name("x47032").ctrl(x47036).srcCtx("UnrollingBase.scala:28:66") // And(b30480,b30471)
    val x47033 = OpDef(op=BitAnd, inputs=List(b30472, b30254)).name("x47033").ctrl(x47036).srcCtx("UnrollingBase.scala:28:66") // And(b30472,b30254)
    val x47034 = OpDef(op=BitAnd, inputs=List(x47032, x47033)).name("x47034").ctrl(x47036).srcCtx("UnrollingBase.scala:28:66") // And(x47032,x47033)
    val x47035_b48486_b48484 = WriteMem(b48484, x47031_x47030).name("x47035_b48486_b48484").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // StreamWrite(x47020,x47031,x47034)
    val x47035_b48487_b48485 = WriteMem(b48485, Const(64)).name("x47035_b48487_b48485").ctrl(x47036).srcCtx("CellsPar.scala:298:20") // StreamWrite(x47020,x47031,x47034)
    val x47037 = FringeDenseLoad(dram=List(x45330), cmdStream=List(b48484, b48485), dataStream=List(x47021)).name("x47037").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // FringeDenseLoad(x45330,x47020,x47021)
    val x47038 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47038").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47039 = CounterChain(List(x47038)).name("x47039").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x47038))
    val x47047 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47039).name("x47047").ctrl(x47048).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30480, b30471, b30472, b30254),x47039,Block(Const(())),List(List(b30501)),List(List(b30502)))
    val b30501 = CounterIter(x47038, None).name("b30501").ctrl(x47047) // b30501
    val b30502 = Const(true).name("b30502").ctrl(x47047) // b30502
    val x47040 = OpDef(op=BitAnd, inputs=List(b30502, b30480)).name("x47040").ctrl(x47047).srcCtx("UnrollingBase.scala:28:66") // And(b30502,b30480)
    val x47041 = OpDef(op=BitAnd, inputs=List(b30471, b30472)).name("x47041").ctrl(x47047).srcCtx("UnrollingBase.scala:28:66") // And(b30471,b30472)
    def split3 = {
    val x47042 = OpDef(op=BitAnd, inputs=List(x47040, x47041)).name("x47042").ctrl(x47047).srcCtx("UnrollingBase.scala:28:66") // And(x47040,x47041)
    val x47043 = OpDef(op=BitAnd, inputs=List(x47042, b30254)).name("x47043").ctrl(x47047).srcCtx("UnrollingBase.scala:28:66") // And(x47042,b30254)
    val x47044_x47044 = ReadMem(x47021).name("x47044_x47044").ctrl(x47047).srcCtx("CellsPar.scala:298:20") // ParStreamRead(x47021,List(x47043))
    val x47045_x47045 = x47044_x47044 // x47045 = VectorApply(x47044,0)
    val x47046 = StoreBanks(List(x47014_d0_b0), List(b30479, b30501), x47045_x47045).name("x47046").ctrl(x47047).srcCtx("CellsPar.scala:298:20") // ParSRAMStore(x47014,List(List(b30479, b30501)),List(x47045),List(x47043))
    val x47049 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47049").ctrl(x47124).srcCtx("CellsPar.scala:299:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47050 = CounterChain(List(x47049)).name("x47050").ctrl(x47124).srcCtx("CellsPar.scala:299:46") // CounterChainNew(List(x47049))
    val x47123 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47050).name("x47123").ctrl(x47124).srcCtx("CellsPar.scala:299:46") // UnrolledForeach(List(b30471, b30472, b30254),x47050,Block(Const(())),List(List(b30514)),List(List(b30515)))
    val b30514 = CounterIter(x47049, Some(0)).name("b30514").ctrl(x47123) // b30514
    val b30515 = Const(true).name("b30515").ctrl(x47123) // b30515
    val x47051 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47051").ctrl(x47123).srcCtx("CellsPar.scala:300:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47052 = CounterChain(List(x47051)).name("x47052").ctrl(x47123).srcCtx("CellsPar.scala:300:42") // CounterChainNew(List(x47051))
    val x47122 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47052).name("x47122").ctrl(x47123).srcCtx("CellsPar.scala:300:42") // UnrolledForeach(List(b30515, b30471, b30472, b30254),x47052,Block(Const(())),List(List(b30518)),List(List(b30519)))
    val b30518 = CounterIter(x47051, Some(0)).name("b30518").ctrl(x47122) // b30518
    val b30519 = Const(true).name("b30519").ctrl(x47122) // b30519
    val x47053_d0 = Reg(init=Some(0.0)).name("x47053_d0").ctrl(x47122).srcCtx("CellsPar.scala:301:34:prod") // x47053 = RegNew(Const(0))
    isAccum(x47053_d0) = false
    bufferDepthOf(x47053_d0) = 2
    val x47053_d1 = Reg(init=Some(0.0)).name("x47053_d1").ctrl(x47122).srcCtx("CellsPar.scala:301:34:prod") // x47053 = RegNew(Const(0))
    isAccum(x47053_d1) = true
    bufferDepthOf(x47053_d1) = 1
    val x47054 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47054").ctrl(x47122).srcCtx("CellsPar.scala:301:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47055 = CounterChain(List(x47054)).name("x47055").ctrl(x47122).srcCtx("CellsPar.scala:321:15") // CounterChainNew(List(x47054))
    val x47089 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47055).name("x47089").ctrl(x47122).srcCtx("CellsPar.scala:321:15") // UnrolledReduce(List(b30519, b30515, b30471, b30472, b30254),x47055,x47053,Block((x47053) => Const(())),List(List(b30523)),List(List(b30524)))
    val b30523 = CounterIter(x47054, None).name("b30523").ctrl(x47089) // b30523
    val b30524 = Const(true).name("b30524").ctrl(x47089) // b30524
    val x47056 = OpDef(op=FixAdd, inputs=List(b30469, b30523)).name("x47056").ctrl(x47089).srcCtx("CellsPar.scala:302:42:reduce_size_offset") // FixAdd(b30469,b30523)
    val x47057 = OpDef(op=BitAnd, inputs=List(b30524, b30519)).name("x47057").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(b30524,b30519)
    val x47058 = OpDef(op=BitAnd, inputs=List(b30515, b30471)).name("x47058").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(b30515,b30471)
    val x47059 = OpDef(op=BitAnd, inputs=List(b30472, b30254)).name("x47059").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(b30472,b30254)
    val x47060 = OpDef(op=BitAnd, inputs=List(x47057, x47058)).name("x47060").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(x47057,x47058)
    val x47061 = OpDef(op=BitAnd, inputs=List(x47060, x47059)).name("x47061").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(x47060,x47059)
    val x47062 = LoadBanks(List(x47014_d0_b0), List(b30523, b30518)).name("x47062").ctrl(x47089).srcCtx("CellsPar.scala:311:34:tk") // ParSRAMLoad(x47014,List(List(b30523, b30518)),List(x47061))
    val x47063 = x47062 // x47063 = VectorApply(x47062,0)
    val x47064 = OpDef(op=FixLt, inputs=List(x47056, Const(512))).name("x47064").ctrl(x47089).srcCtx("CellsPar.scala:313:36") // FixLt(x47056,Const(512))
    val x47065 = LoadBanks(List(x45530_d2_b0), List(b30514, x47056)).name("x47065").ctrl(x47089).srcCtx("CellsPar.scala:314:19") // ParSRAMLoad(x45530,List(List(b30514, x47056)),List(x47061))
    val x47066 = x47065 // x47066 = VectorApply(x47065,0)
    val x47067 = OpDef(op=FixMul, inputs=List(x47066, x47063)).name("x47067").ctrl(x47089).srcCtx("CellsPar.scala:314:43") // FixMul(x47066,x47063)
    val x47068 = OpDef(op=FixLeq, inputs=List(Const(512), x47056)).name("x47068").ctrl(x47089).srcCtx("CellsPar.scala:316:32") // FixLeq(Const(512),x47056)
    val x47069 = OpDef(op=FixLt, inputs=List(x47056, Const(1024))).name("x47069").ctrl(x47089).srcCtx("CellsPar.scala:316:76") // FixLt(x47056,Const(1024))
    val x47070 = OpDef(op=BitAnd, inputs=List(x47068, x47069)).name("x47070").ctrl(x47089).srcCtx("CellsPar.scala:316:54") // And(x47068,x47069)
    val x47071 = OpDef(op=FixSub, inputs=List(x47056, Const(512))).name("x47071").ctrl(x47089).srcCtx("CellsPar.scala:317:44") // FixSub(x47056,Const(512))
    val x47072 = LoadBanks(List(x45538_d2_b0), List(b30514, x47071)).name("x47072").ctrl(x47089).srcCtx("CellsPar.scala:317:21") // ParSRAMLoad(x45538,List(List(b30514, x47071)),List(x47061))
    val x47073 = x47072 // x47073 = VectorApply(x47072,0)
    val x47074 = OpDef(op=FixMul, inputs=List(x47073, x47063)).name("x47074").ctrl(x47089).srcCtx("CellsPar.scala:317:60") // FixMul(x47073,x47063)
    val x47075 = OpDef(op=FixSub, inputs=List(x47056, Const(1024))).name("x47075").ctrl(x47089).srcCtx("CellsPar.scala:318:43") // FixSub(x47056,Const(1024))
    val x47076 = LoadBanks(List(x45545_d7_b0), List(b30514, x47075)).name("x47076").ctrl(x47089).srcCtx("CellsPar.scala:318:20") // ParSRAMLoad(x45545,List(List(b30514, x47075)),List(x47061))
    val x47077 = x47076 // x47077 = VectorApply(x47076,0)
    val x47078 = OpDef(op=FixMul, inputs=List(x47077, x47063)).name("x47078").ctrl(x47089).srcCtx("CellsPar.scala:318:60") // FixMul(x47077,x47063)
    val x47079 = OpDef(op=MuxOp, inputs=List(x47070, x47074, x47078)).name("x47079").ctrl(x47089).srcCtx("CellsPar.scala:315:20") // Mux(x47070,x47074,x47078)
    val x47080 = OpDef(op=MuxOp, inputs=List(x47064, x47067, x47079)).name("x47080").ctrl(x47089).srcCtx("CellsPar.scala:312:18") // Mux(x47064,x47067,x47079)
    val x47081 = ReadMem(x47053_d1).name("x47081").ctrl(x47089).srcCtx("CellsPar.scala:321:15") // RegRead(x47053)
    val x47082 = OpDef(op=FixEql, inputs=List(b30523, Const(0))).name("x47082").ctrl(x47089).srcCtx("CellsPar.scala:321:15") // FixEql(b30523,Const(0))
    val x47083 = ReduceAccumOp(op=FixAdd, input=x47080, accum=x47081).name("x47083").ctrl(x47089).srcCtx("CellsPar.scala:321:17") // FixAdd(x47080,x47081)
    val x47084 = OpDef(op=BitAnd, inputs=List(b30519, b30515)).name("x47084").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(b30519,b30515)
    val x47085 = OpDef(op=BitAnd, inputs=List(b30471, b30472)).name("x47085").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(b30471,b30472)
    val x47086 = OpDef(op=BitAnd, inputs=List(x47084, x47085)).name("x47086").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(x47084,x47085)
    val x47087 = OpDef(op=BitAnd, inputs=List(x47086, b30254)).name("x47087").ctrl(x47089).srcCtx("UnrollingBase.scala:28:66") // And(x47086,b30254)
    val x47088_x47053_d0 = WriteMem(x47053_d0, x47083).name("x47088_x47053_d0").ctrl(x47089).srcCtx("CellsPar.scala:321:15") // RegWrite(x47053,x47083,x47087)
    val x47088_x47053_d1 = WriteMem(x47053_d1, x47083).name("x47088_x47053_d1").ctrl(x47089).srcCtx("CellsPar.scala:321:15") // RegWrite(x47053,x47083,x47087)
    val x47121 = UnitController(style=SeqPipe, level=InnerControl).name("x47121").ctrl(x47122).srcCtx("CellsPar.scala:300:42") // UnitPipe(List(b30519, b30515, b30471, b30472, b30254),Block(Const(())))
    val x47090 = OpDef(op=FixAdd, inputs=List(b30470, b30518)).name("x47090").ctrl(x47121).srcCtx("CellsPar.scala:323:31:colOffset") // FixAdd(b30470,b30518)
    val x47091 = ReadMem(x47053_d0).name("x47091").ctrl(x47121).srcCtx("CellsPar.scala:324:28") // RegRead(x47053)
    val x47092 = OpDef(op=FixEql, inputs=List(b30469, Const(0))).name("x47092").ctrl(x47121).srcCtx("CellsPar.scala:324:42") // FixEql(b30469,Const(0))
    val x47093 = OpDef(op=FixAdd, inputs=List(x47090, Const(512))).name("x47093").ctrl(x47121).srcCtx("CellsPar.scala:324:66") // FixAdd(x47090,Const(512))
    val x47094 = OpDef(op=BitAnd, inputs=List(b30519, b30515)).name("x47094").ctrl(x47121).srcCtx("UnrollingBase.scala:28:66") // And(b30519,b30515)
    val x47095 = OpDef(op=BitAnd, inputs=List(b30471, b30472)).name("x47095").ctrl(x47121).srcCtx("UnrollingBase.scala:28:66") // And(b30471,b30472)
    val x47096 = OpDef(op=BitAnd, inputs=List(x47094, x47095)).name("x47096").ctrl(x47121).srcCtx("UnrollingBase.scala:28:66") // And(x47094,x47095)
    val x47097 = OpDef(op=BitAnd, inputs=List(x47096, b30254)).name("x47097").ctrl(x47121).srcCtx("UnrollingBase.scala:28:66") // And(x47096,b30254)
    val x47098 = LoadBanks(List(x45551_d2_b0), List(Const(0), x47093)).name("x47098").ctrl(x47121).srcCtx("CellsPar.scala:324:52") // SRAMLoad(x45551,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47093),Const(0),x47097)
    val x47099 = LoadBanks(List(x45548_d1_b0), List(b30514, x47090)).name("x47099").ctrl(x47121).srcCtx("CellsPar.scala:324:91") // SRAMLoad(x45548,ArrayBuffer(Const(1), Const(512)),List(b30514, x47090),Const(0),x47097)
    val x47100 = OpDef(op=MuxOp, inputs=List(x47092, x47098, x47099)).name("x47100").ctrl(x47121).srcCtx("CellsPar.scala:324:39") // Mux(x47092,x47098,x47099)
    val x47101 = OpDef(op=FixAdd, inputs=List(x47091, x47100)).name("x47101").ctrl(x47121).srcCtx("CellsPar.scala:324:34:ele") // FixAdd(x47091,x47100)
    val x47102 = OpDef(op=FixLeq, inputs=List(Const(1520), b30469)).name("x47102").ctrl(x47121).srcCtx("CellsPar.scala:332:17") // FixLeq(Const(1520),b30469)
    // x47103 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47103_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x47103_int1").ctrl(x47121).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47103_int2 = OpDef(op=FixSla, inputs=List(x47103_int1, Const(24))).name("x47103_int2").ctrl(x47121).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47103_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x47103_frac1").ctrl(x47121).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47103_frac2 = OpDef(op=FixSla, inputs=List(x47103_frac1, Const(24))).name("x47103_frac2").ctrl(x47121).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47103 = OpDef(op=BitOr, inputs=List(x47103_int2, x47103_frac2)).name("x47103").ctrl(x47121).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x47104 = OpDef(op=FixAdd, inputs=List(x47101, x47103)).name("x47104").ctrl(x47121).srcCtx("CellsPar.scala:333:30") // FixAdd(x47101,x47103)
    val x47105 = x47104 // FixConvert(x47104,TRUE,_8,_24) (Same Type. No op)
    val x47106 = OpDef(op=FixAbs, inputs=List(x47105)).name("x47106").ctrl(x47121).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47105)
    val x47107 = OpDef(op=FixLt, inputs=List(Const(2.5), x47106)).name("x47107").ctrl(x47121).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47106)
    val x47108 = OpDef(op=FixLt, inputs=List(Const(0.5), x47106)).name("x47108").ctrl(x47121).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47106)
    val x47109 = OpDef(op=FixLeq, inputs=List(x47106, Const(2.5))).name("x47109").ctrl(x47121).srcCtx("CellsPar.scala:54:52") // FixLeq(x47106,Const(2.5))
    val x47110 = OpDef(op=BitAnd, inputs=List(x47108, x47109)).name("x47110").ctrl(x47121).srcCtx("CellsPar.scala:54:43:cond2") // And(x47108,x47109)
    val x47111 = OpDef(op=FixSra, inputs=List(x47106, Const(2))).name("x47111").ctrl(x47121).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47106,Const(2))
    val x47112 = OpDef(op=FixAdd, inputs=List(x47111, Const(0.375))).name("x47112").ctrl(x47121).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47111,Const(0.375))
    val x47113 = OpDef(op=MuxOp, inputs=List(x47110, x47112, x47106)).name("x47113").ctrl(x47121).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47110,x47112,x47106)
    val x47114 = OpDef(op=MuxOp, inputs=List(x47107, Const(1.0), x47113)).name("x47114").ctrl(x47121).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47107,Const(1),x47113)
    val x47115 = OpDef(op=FixNeg, inputs=List(x47114)).name("x47115").ctrl(x47121).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47114)
    val x47116 = OpDef(op=FixLt, inputs=List(x47105, Const(0.0))).name("x47116").ctrl(x47121).srcCtx("CellsPar.scala:63:22") // FixLt(x47105,Const(0))
    val x47117 = OpDef(op=MuxOp, inputs=List(x47116, x47115, x47114)).name("x47117").ctrl(x47121).srcCtx("CellsPar.scala:63:17:re") // Mux(x47116,x47115,x47114)
    val x47118 = x47117 // FixConvert(x47117,TRUE,_8,_24) (Same Type. No op)
    val x47119 = OpDef(op=MuxOp, inputs=List(x47102, x47118, x47101)).name("x47119").ctrl(x47121).srcCtx("CellsPar.scala:331:43") // Mux(x47102,x47118,x47101)
    val x47120 = StoreBanks(List(x45548_d0_b0, x45548_d1_b0), List(b30514, x47090), x47119).name("x47120").ctrl(x47121).srcCtx("CellsPar.scala:331:38") // SRAMStore(x45548,ArrayBuffer(Const(1), Const(512)),List(b30514, x47090),Const(0),x47119,x47097)
    val x47125 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47125").ctrl(x48337).srcCtx("CellsPar.scala:296:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47126 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x47126").ctrl(x48337).srcCtx("CellsPar.scala:296:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x47127 = CounterChain(List(x47126,x47125)).name("x47127").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // CounterChainNew(List(x47126, x47125))
    val x47241 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47127).name("x47241").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // UnrolledForeach(List(b30254),x47127,Block(Const(())),List(List(b30597), List(b30598)),List(List(b30599), List(b30600)))
    val b30597 = CounterIter(x47126, Some(0)).name("b30597").ctrl(x47241) // b30597
    val b30599 = Const(true).name("b30599").ctrl(x47241) // b30599
    val b30598 = CounterIter(x47125, Some(0)).name("b30598").ctrl(x47241) // b30598
    val b30600 = Const(true).name("b30600").ctrl(x47241) // b30600
    val x47128_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47128_d0_b0").ctrl(x47241).srcCtx("CellsPar.scala:297:33:tileKernel") // x47128 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47128_d0_b0) = false
    bufferDepthOf(x47128_d0_b0) = 2
    val x47131 = UnitController(style=SeqPipe, level=InnerControl).name("x47131").ctrl(x47241).srcCtx("CellsPar.scala:296:72") // UnitPipe(List(b30599, b30600, b30254),Block(Const(())))
    val x47129 = OpDef(op=FixAdd, inputs=List(b30597, Const(16))).name("x47129").ctrl(x47131).srcCtx("CellsPar.scala:298:36") // FixAdd(b30597,Const(16))
    val x47130 = OpDef(op=FixAdd, inputs=List(b30598, Const(16))).name("x47130").ctrl(x47131).srcCtx("CellsPar.scala:298:45") // FixAdd(b30598,Const(16))
    val x47132 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47132").ctrl(x47241).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47133 = CounterChain(List(x47132)).name("x47133").ctrl(x47241).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x47132))
    val x47162 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47133).name("x47162").ctrl(x47241).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30599, b30600, b30254),x47133,Block(Const(())),List(List(b30607)),List(List(b30608)))
    val b30607 = CounterIter(x47132, Some(0)).name("b30607").ctrl(x47162) // b30607
    val b30608 = Const(true).name("b30608").ctrl(x47162) // b30608
    val b48488 = StreamOut(field="offset").name("b48488").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // x47134 = StreamOutNew(BurstCmdBus)
    isAccum(b48488) = false
    bufferDepthOf(b48488) = 1
    val b48489 = StreamOut(field="size").name("b48489").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // x47134 = StreamOutNew(BurstCmdBus)
    isAccum(b48489) = false
    bufferDepthOf(b48489) = 1
    val x47135 = StreamIn(field="data").name("x47135").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // x47135 = StreamInNew(BurstDataBus())
    isAccum(x47135) = false
    bufferDepthOf(x47135) = 1
    val x47150 = UnitController(style=SeqPipe, level=InnerControl).name("x47150").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // UnitPipe(List(b30608, b30599, b30600, b30254),Block(x47149))
    val x47136 = OpDef(op=FixAdd, inputs=List(b30597, b30607)).name("x47136").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // FixAdd(b30597,b30607)
    val x47137 = x47136 // FixConvert(x47136,TRUE,_32,_0) (Same Type. No op)
    val x47138 = OpDef(op=FixSla, inputs=List(x47137, Const(9))).name("x47138").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // FixLsh(x47137,Const(9))
    val x47139 = b30598 // FixConvert(b30598,TRUE,_32,_0) (Same Type. No op)
    val x47140 = OpDef(op=FixAdd, inputs=List(x47138, x47139)).name("x47140").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // FixAdd(x47138,x47139)
    val x47141 = OpDef(op=FixSla, inputs=List(x47140, Const(2))).name("x47141").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // FixLsh(x47140,Const(2))
    val x47142 = x47141 // FixConvert(x47141,TRUE,_64,_0)
    val x47143 = DramAddress(x45331).name("x47143").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // GetDRAMAddress(x45331)
    val x47145_x47144 = OpDef(op=FixAdd, inputs=List(x47142, x47143)).name("x47145_x47144").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // FixAdd(x47142,x47143)
    // x47145 = SimpleStruct(ArrayBuffer((offset,x47144), (size,Const(64)), (isLoad,Const(true))))
    val x47146 = OpDef(op=BitAnd, inputs=List(b30608, b30599)).name("x47146").ctrl(x47150).srcCtx("UnrollingBase.scala:28:66") // And(b30608,b30599)
    val x47147 = OpDef(op=BitAnd, inputs=List(b30600, b30254)).name("x47147").ctrl(x47150).srcCtx("UnrollingBase.scala:28:66") // And(b30600,b30254)
    val x47148 = OpDef(op=BitAnd, inputs=List(x47146, x47147)).name("x47148").ctrl(x47150).srcCtx("UnrollingBase.scala:28:66") // And(x47146,x47147)
    val x47149_b48490_b48488 = WriteMem(b48488, x47145_x47144).name("x47149_b48490_b48488").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // StreamWrite(x47134,x47145,x47148)
    val x47149_b48491_b48489 = WriteMem(b48489, Const(64)).name("x47149_b48491_b48489").ctrl(x47150).srcCtx("CellsPar.scala:298:20") // StreamWrite(x47134,x47145,x47148)
    val x47151 = FringeDenseLoad(dram=List(x45331), cmdStream=List(b48488, b48489), dataStream=List(x47135)).name("x47151").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // FringeDenseLoad(x45331,x47134,x47135)
    val x47152 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47152").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47153 = CounterChain(List(x47152)).name("x47153").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x47152))
    val x47161 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47153).name("x47161").ctrl(x47162).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30608, b30599, b30600, b30254),x47153,Block(Const(())),List(List(b30629)),List(List(b30630)))
    val b30629 = CounterIter(x47152, None).name("b30629").ctrl(x47161) // b30629
    val b30630 = Const(true).name("b30630").ctrl(x47161) // b30630
    val x47154 = OpDef(op=BitAnd, inputs=List(b30630, b30608)).name("x47154").ctrl(x47161).srcCtx("UnrollingBase.scala:28:66") // And(b30630,b30608)
    val x47155 = OpDef(op=BitAnd, inputs=List(b30599, b30600)).name("x47155").ctrl(x47161).srcCtx("UnrollingBase.scala:28:66") // And(b30599,b30600)
    val x47156 = OpDef(op=BitAnd, inputs=List(x47154, x47155)).name("x47156").ctrl(x47161).srcCtx("UnrollingBase.scala:28:66") // And(x47154,x47155)
    val x47157 = OpDef(op=BitAnd, inputs=List(x47156, b30254)).name("x47157").ctrl(x47161).srcCtx("UnrollingBase.scala:28:66") // And(x47156,b30254)
    val x47158_x47158 = ReadMem(x47135).name("x47158_x47158").ctrl(x47161).srcCtx("CellsPar.scala:298:20") // ParStreamRead(x47135,List(x47157))
    val x47159_x47159 = x47158_x47158 // x47159 = VectorApply(x47158,0)
    val x47160 = StoreBanks(List(x47128_d0_b0), List(b30607, b30629), x47159_x47159).name("x47160").ctrl(x47161).srcCtx("CellsPar.scala:298:20") // ParSRAMStore(x47128,List(List(b30607, b30629)),List(x47159),List(x47157))
    val x47163 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47163").ctrl(x47241).srcCtx("CellsPar.scala:299:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47164 = CounterChain(List(x47163)).name("x47164").ctrl(x47241).srcCtx("CellsPar.scala:299:46") // CounterChainNew(List(x47163))
    val x47240 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47164).name("x47240").ctrl(x47241).srcCtx("CellsPar.scala:299:46") // UnrolledForeach(List(b30599, b30600, b30254),x47164,Block(Const(())),List(List(b30642)),List(List(b30643)))
    val b30642 = CounterIter(x47163, Some(0)).name("b30642").ctrl(x47240) // b30642
    val b30643 = Const(true).name("b30643").ctrl(x47240) // b30643
    val x47165 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47165").ctrl(x47240).srcCtx("CellsPar.scala:300:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47166 = CounterChain(List(x47165)).name("x47166").ctrl(x47240).srcCtx("CellsPar.scala:300:42") // CounterChainNew(List(x47165))
    val x47239 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47166).name("x47239").ctrl(x47240).srcCtx("CellsPar.scala:300:42") // UnrolledForeach(List(b30643, b30599, b30600, b30254),x47166,Block(Const(())),List(List(b30646)),List(List(b30647)))
    val b30646 = CounterIter(x47165, Some(0)).name("b30646").ctrl(x47239) // b30646
    val b30647 = Const(true).name("b30647").ctrl(x47239) // b30647
    val x47167_d0 = Reg(init=Some(0.0)).name("x47167_d0").ctrl(x47239).srcCtx("CellsPar.scala:301:34:prod") // x47167 = RegNew(Const(0))
    isAccum(x47167_d0) = false
    bufferDepthOf(x47167_d0) = 2
    val x47167_d1 = Reg(init=Some(0.0)).name("x47167_d1").ctrl(x47239).srcCtx("CellsPar.scala:301:34:prod") // x47167 = RegNew(Const(0))
    isAccum(x47167_d1) = true
    bufferDepthOf(x47167_d1) = 1
    val x47168 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47168").ctrl(x47239).srcCtx("CellsPar.scala:301:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47169 = CounterChain(List(x47168)).name("x47169").ctrl(x47239).srcCtx("CellsPar.scala:321:15") // CounterChainNew(List(x47168))
    val x47203 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47169).name("x47203").ctrl(x47239).srcCtx("CellsPar.scala:321:15") // UnrolledReduce(List(b30647, b30643, b30599, b30600, b30254),x47169,x47167,Block((x47167) => Const(())),List(List(b30651)),List(List(b30652)))
    val b30651 = CounterIter(x47168, None).name("b30651").ctrl(x47203) // b30651
    val b30652 = Const(true).name("b30652").ctrl(x47203) // b30652
    val x47170 = OpDef(op=FixAdd, inputs=List(b30597, b30651)).name("x47170").ctrl(x47203).srcCtx("CellsPar.scala:302:42:reduce_size_offset") // FixAdd(b30597,b30651)
    val x47171 = OpDef(op=BitAnd, inputs=List(b30652, b30647)).name("x47171").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(b30652,b30647)
    val x47172 = OpDef(op=BitAnd, inputs=List(b30643, b30599)).name("x47172").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(b30643,b30599)
    val x47173 = OpDef(op=BitAnd, inputs=List(b30600, b30254)).name("x47173").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(b30600,b30254)
    val x47174 = OpDef(op=BitAnd, inputs=List(x47171, x47172)).name("x47174").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(x47171,x47172)
    val x47175 = OpDef(op=BitAnd, inputs=List(x47174, x47173)).name("x47175").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(x47174,x47173)
    val x47176 = LoadBanks(List(x47128_d0_b0), List(b30651, b30646)).name("x47176").ctrl(x47203).srcCtx("CellsPar.scala:311:34:tk") // ParSRAMLoad(x47128,List(List(b30651, b30646)),List(x47175))
    val x47177 = x47176 // x47177 = VectorApply(x47176,0)
    val x47178 = OpDef(op=FixLt, inputs=List(x47170, Const(512))).name("x47178").ctrl(x47203).srcCtx("CellsPar.scala:313:36") // FixLt(x47170,Const(512))
    val x47179 = LoadBanks(List(x45530_d1_b0), List(b30642, x47170)).name("x47179").ctrl(x47203).srcCtx("CellsPar.scala:314:19") // ParSRAMLoad(x45530,List(List(b30642, x47170)),List(x47175))
    val x47180 = x47179 // x47180 = VectorApply(x47179,0)
    val x47181 = OpDef(op=FixMul, inputs=List(x47180, x47177)).name("x47181").ctrl(x47203).srcCtx("CellsPar.scala:314:43") // FixMul(x47180,x47177)
    val x47182 = OpDef(op=FixLeq, inputs=List(Const(512), x47170)).name("x47182").ctrl(x47203).srcCtx("CellsPar.scala:316:32") // FixLeq(Const(512),x47170)
    val x47183 = OpDef(op=FixLt, inputs=List(x47170, Const(1024))).name("x47183").ctrl(x47203).srcCtx("CellsPar.scala:316:76") // FixLt(x47170,Const(1024))
    val x47184 = OpDef(op=BitAnd, inputs=List(x47182, x47183)).name("x47184").ctrl(x47203).srcCtx("CellsPar.scala:316:54") // And(x47182,x47183)
    val x47185 = OpDef(op=FixSub, inputs=List(x47170, Const(512))).name("x47185").ctrl(x47203).srcCtx("CellsPar.scala:317:44") // FixSub(x47170,Const(512))
    val x47186 = LoadBanks(List(x45538_d1_b0), List(b30642, x47185)).name("x47186").ctrl(x47203).srcCtx("CellsPar.scala:317:21") // ParSRAMLoad(x45538,List(List(b30642, x47185)),List(x47175))
    val x47187 = x47186 // x47187 = VectorApply(x47186,0)
    val x47188 = OpDef(op=FixMul, inputs=List(x47187, x47177)).name("x47188").ctrl(x47203).srcCtx("CellsPar.scala:317:60") // FixMul(x47187,x47177)
    val x47189 = OpDef(op=FixSub, inputs=List(x47170, Const(1024))).name("x47189").ctrl(x47203).srcCtx("CellsPar.scala:318:43") // FixSub(x47170,Const(1024))
    val x47190 = LoadBanks(List(x45545_d6_b0), List(b30642, x47189)).name("x47190").ctrl(x47203).srcCtx("CellsPar.scala:318:20") // ParSRAMLoad(x45545,List(List(b30642, x47189)),List(x47175))
    val x47191 = x47190 // x47191 = VectorApply(x47190,0)
    val x47192 = OpDef(op=FixMul, inputs=List(x47191, x47177)).name("x47192").ctrl(x47203).srcCtx("CellsPar.scala:318:60") // FixMul(x47191,x47177)
    val x47193 = OpDef(op=MuxOp, inputs=List(x47184, x47188, x47192)).name("x47193").ctrl(x47203).srcCtx("CellsPar.scala:315:20") // Mux(x47184,x47188,x47192)
    val x47194 = OpDef(op=MuxOp, inputs=List(x47178, x47181, x47193)).name("x47194").ctrl(x47203).srcCtx("CellsPar.scala:312:18") // Mux(x47178,x47181,x47193)
    val x47195 = ReadMem(x47167_d1).name("x47195").ctrl(x47203).srcCtx("CellsPar.scala:321:15") // RegRead(x47167)
    val x47196 = OpDef(op=FixEql, inputs=List(b30651, Const(0))).name("x47196").ctrl(x47203).srcCtx("CellsPar.scala:321:15") // FixEql(b30651,Const(0))
    val x47197 = ReduceAccumOp(op=FixAdd, input=x47194, accum=x47195).name("x47197").ctrl(x47203).srcCtx("CellsPar.scala:321:17") // FixAdd(x47194,x47195)
    val x47198 = OpDef(op=BitAnd, inputs=List(b30647, b30643)).name("x47198").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(b30647,b30643)
    val x47199 = OpDef(op=BitAnd, inputs=List(b30599, b30600)).name("x47199").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(b30599,b30600)
    val x47200 = OpDef(op=BitAnd, inputs=List(x47198, x47199)).name("x47200").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(x47198,x47199)
    val x47201 = OpDef(op=BitAnd, inputs=List(x47200, b30254)).name("x47201").ctrl(x47203).srcCtx("UnrollingBase.scala:28:66") // And(x47200,b30254)
    val x47202_x47167_d0 = WriteMem(x47167_d0, x47197).name("x47202_x47167_d0").ctrl(x47203).srcCtx("CellsPar.scala:321:15") // RegWrite(x47167,x47197,x47201)
    val x47202_x47167_d1 = WriteMem(x47167_d1, x47197).name("x47202_x47167_d1").ctrl(x47203).srcCtx("CellsPar.scala:321:15") // RegWrite(x47167,x47197,x47201)
    val x47238 = UnitController(style=SeqPipe, level=InnerControl).name("x47238").ctrl(x47239).srcCtx("CellsPar.scala:300:42") // UnitPipe(List(b30647, b30643, b30599, b30600, b30254),Block(Const(())))
    val x47204 = OpDef(op=FixAdd, inputs=List(b30598, b30646)).name("x47204").ctrl(x47238).srcCtx("CellsPar.scala:323:31:colOffset") // FixAdd(b30598,b30646)
    val x47205 = ReadMem(x47167_d0).name("x47205").ctrl(x47238).srcCtx("CellsPar.scala:324:28") // RegRead(x47167)
    val x47206 = OpDef(op=FixEql, inputs=List(b30597, Const(0))).name("x47206").ctrl(x47238).srcCtx("CellsPar.scala:324:42") // FixEql(b30597,Const(0))
    val x47207 = OpDef(op=FixAdd, inputs=List(x47204, Const(1024))).name("x47207").ctrl(x47238).srcCtx("CellsPar.scala:324:66") // FixAdd(x47204,Const(1024))
    val x47208 = OpDef(op=BitAnd, inputs=List(b30647, b30643)).name("x47208").ctrl(x47238).srcCtx("UnrollingBase.scala:28:66") // And(b30647,b30643)
    val x47209 = OpDef(op=BitAnd, inputs=List(b30599, b30600)).name("x47209").ctrl(x47238).srcCtx("UnrollingBase.scala:28:66") // And(b30599,b30600)
    val x47210 = OpDef(op=BitAnd, inputs=List(x47208, x47209)).name("x47210").ctrl(x47238).srcCtx("UnrollingBase.scala:28:66") // And(x47208,x47209)
    val x47211 = OpDef(op=BitAnd, inputs=List(x47210, b30254)).name("x47211").ctrl(x47238).srcCtx("UnrollingBase.scala:28:66") // And(x47210,b30254)
    val x47212 = LoadBanks(List(x45551_d1_b0), List(Const(0), x47207)).name("x47212").ctrl(x47238).srcCtx("CellsPar.scala:324:52") // SRAMLoad(x45551,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47207),Const(0),x47211)
    val x47213 = LoadBanks(List(x45549_d1_b0), List(b30642, x47204)).name("x47213").ctrl(x47238).srcCtx("CellsPar.scala:324:91") // SRAMLoad(x45549,ArrayBuffer(Const(1), Const(512)),List(b30642, x47204),Const(0),x47211)
    val x47214 = OpDef(op=MuxOp, inputs=List(x47206, x47212, x47213)).name("x47214").ctrl(x47238).srcCtx("CellsPar.scala:324:39") // Mux(x47206,x47212,x47213)
    val x47215 = OpDef(op=FixAdd, inputs=List(x47205, x47214)).name("x47215").ctrl(x47238).srcCtx("CellsPar.scala:324:34:ele") // FixAdd(x47205,x47214)
    val x47216 = OpDef(op=FixLeq, inputs=List(Const(1520), b30597)).name("x47216").ctrl(x47238).srcCtx("CellsPar.scala:332:17") // FixLeq(Const(1520),b30597)
    // x47217 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47217_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x47217_int1").ctrl(x47238).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(1),TRUE,_8,_24)
    val x47217_int2 = OpDef(op=FixSla, inputs=List(x47217_int1, Const(24))).name("x47217_int2").ctrl(x47238).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(1),TRUE,_8,_24)
    val x47217_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x47217_frac1").ctrl(x47238).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(1),TRUE,_8,_24)
    val x47217_frac2 = OpDef(op=FixSla, inputs=List(x47217_frac1, Const(24))).name("x47217_frac2").ctrl(x47238).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(1),TRUE,_8,_24)
    val x47217 = OpDef(op=BitOr, inputs=List(x47217_int2, x47217_frac2)).name("x47217").ctrl(x47238).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x47218 = OpDef(op=FixAdd, inputs=List(x47215, x47217)).name("x47218").ctrl(x47238).srcCtx("CellsPar.scala:333:30") // FixAdd(x47215,x47217)
    val x47219 = OpDef(op=FixSra, inputs=List(x47218, Const(1))).name("x47219").ctrl(x47238).srcCtx("CellsPar.scala:29:22") // FixRsh(x47218,Const(1))
    val x47220 = x47219 // FixConvert(x47219,TRUE,_8,_24) (Same Type. No op)
    val x47221 = OpDef(op=FixAbs, inputs=List(x47220)).name("x47221").ctrl(x47238).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47220)
    val x47222 = OpDef(op=FixLt, inputs=List(Const(2.5), x47221)).name("x47222").ctrl(x47238).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47221)
    val x47223 = OpDef(op=FixLt, inputs=List(Const(0.5), x47221)).name("x47223").ctrl(x47238).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47221)
    val x47224 = OpDef(op=FixLeq, inputs=List(x47221, Const(2.5))).name("x47224").ctrl(x47238).srcCtx("CellsPar.scala:54:52") // FixLeq(x47221,Const(2.5))
    val x47225 = OpDef(op=BitAnd, inputs=List(x47223, x47224)).name("x47225").ctrl(x47238).srcCtx("CellsPar.scala:54:43:cond2") // And(x47223,x47224)
    val x47226 = OpDef(op=FixSra, inputs=List(x47221, Const(2))).name("x47226").ctrl(x47238).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47221,Const(2))
    val x47227 = OpDef(op=FixAdd, inputs=List(x47226, Const(0.375))).name("x47227").ctrl(x47238).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47226,Const(0.375))
    val x47228 = OpDef(op=MuxOp, inputs=List(x47225, x47227, x47221)).name("x47228").ctrl(x47238).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47225,x47227,x47221)
    val x47229 = OpDef(op=MuxOp, inputs=List(x47222, Const(1.0), x47228)).name("x47229").ctrl(x47238).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47222,Const(1),x47228)
    val x47230 = OpDef(op=FixNeg, inputs=List(x47229)).name("x47230").ctrl(x47238).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47229)
    val x47231 = OpDef(op=FixLt, inputs=List(x47220, Const(0.0))).name("x47231").ctrl(x47238).srcCtx("CellsPar.scala:63:22") // FixLt(x47220,Const(0))
    val x47232 = OpDef(op=MuxOp, inputs=List(x47231, x47230, x47229)).name("x47232").ctrl(x47238).srcCtx("CellsPar.scala:63:17:re") // Mux(x47231,x47230,x47229)
    val x47233 = x47232 // FixConvert(x47232,TRUE,_8,_24) (Same Type. No op)
    val x47234 = OpDef(op=FixAdd, inputs=List(x47233, Const(1.0))).name("x47234").ctrl(x47238).srcCtx("CellsPar.scala:29:33") // FixAdd(x47233,Const(1))
    val x47235 = OpDef(op=FixSra, inputs=List(x47234, Const(1))).name("x47235").ctrl(x47238).srcCtx("CellsPar.scala:29:44") // FixRsh(x47234,Const(1))
    val x47236 = OpDef(op=MuxOp, inputs=List(x47216, x47235, x47215)).name("x47236").ctrl(x47238).srcCtx("CellsPar.scala:331:43") // Mux(x47216,x47235,x47215)
    val x47237 = StoreBanks(List(x45549_d0_b0, x45549_d1_b0), List(b30642, x47204), x47236).name("x47237").ctrl(x47238).srcCtx("CellsPar.scala:331:38") // SRAMStore(x45549,ArrayBuffer(Const(1), Const(512)),List(b30642, x47204),Const(0),x47236,x47211)
    val x47242 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47242").ctrl(x48337).srcCtx("CellsPar.scala:296:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47243 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x47243").ctrl(x48337).srcCtx("CellsPar.scala:296:33") // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x47244 = CounterChain(List(x47243,x47242)).name("x47244").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // CounterChainNew(List(x47243, x47242))
    val x47358 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47244).name("x47358").ctrl(x48337).srcCtx("CellsPar.scala:296:72") // UnrolledForeach(List(b30254),x47244,Block(Const(())),List(List(b30728), List(b30729)),List(List(b30730), List(b30731)))
    val b30728 = CounterIter(x47243, Some(0)).name("b30728").ctrl(x47358) // b30728
    val b30730 = Const(true).name("b30730").ctrl(x47358) // b30730
    val b30729 = CounterIter(x47242, Some(0)).name("b30729").ctrl(x47358) // b30729
    val b30731 = Const(true).name("b30731").ctrl(x47358) // b30731
    val x47245_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47245_d0_b0").ctrl(x47358).srcCtx("CellsPar.scala:297:33:tileKernel") // x47245 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47245_d0_b0) = false
    bufferDepthOf(x47245_d0_b0) = 2
    val x47248 = UnitController(style=SeqPipe, level=InnerControl).name("x47248").ctrl(x47358).srcCtx("CellsPar.scala:296:72") // UnitPipe(List(b30730, b30731, b30254),Block(Const(())))
    val x47246 = OpDef(op=FixAdd, inputs=List(b30728, Const(16))).name("x47246").ctrl(x47248).srcCtx("CellsPar.scala:298:36") // FixAdd(b30728,Const(16))
    val x47247 = OpDef(op=FixAdd, inputs=List(b30729, Const(16))).name("x47247").ctrl(x47248).srcCtx("CellsPar.scala:298:45") // FixAdd(b30729,Const(16))
    val x47249 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47249").ctrl(x47358).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47250 = CounterChain(List(x47249)).name("x47250").ctrl(x47358).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x47249))
    val x47279 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47250).name("x47279").ctrl(x47358).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30730, b30731, b30254),x47250,Block(Const(())),List(List(b30738)),List(List(b30739)))
    val b30738 = CounterIter(x47249, Some(0)).name("b30738").ctrl(x47279) // b30738
    val b30739 = Const(true).name("b30739").ctrl(x47279) // b30739
    val b48492 = StreamOut(field="offset").name("b48492").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // x47251 = StreamOutNew(BurstCmdBus)
    isAccum(b48492) = false
    bufferDepthOf(b48492) = 1
    val b48493 = StreamOut(field="size").name("b48493").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // x47251 = StreamOutNew(BurstCmdBus)
    isAccum(b48493) = false
    bufferDepthOf(b48493) = 1
    val x47252 = StreamIn(field="data").name("x47252").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // x47252 = StreamInNew(BurstDataBus())
    isAccum(x47252) = false
    bufferDepthOf(x47252) = 1
    val x47267 = UnitController(style=SeqPipe, level=InnerControl).name("x47267").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // UnitPipe(List(b30739, b30730, b30731, b30254),Block(x47266))
    val x47253 = OpDef(op=FixAdd, inputs=List(b30728, b30738)).name("x47253").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // FixAdd(b30728,b30738)
    val x47254 = x47253 // FixConvert(x47253,TRUE,_32,_0) (Same Type. No op)
    val x47255 = OpDef(op=FixSla, inputs=List(x47254, Const(9))).name("x47255").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // FixLsh(x47254,Const(9))
    val x47256 = b30729 // FixConvert(b30729,TRUE,_32,_0) (Same Type. No op)
    val x47257 = OpDef(op=FixAdd, inputs=List(x47255, x47256)).name("x47257").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // FixAdd(x47255,x47256)
    val x47258 = OpDef(op=FixSla, inputs=List(x47257, Const(2))).name("x47258").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // FixLsh(x47257,Const(2))
    val x47259 = x47258 // FixConvert(x47258,TRUE,_64,_0)
    val x47260 = DramAddress(x45332).name("x47260").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // GetDRAMAddress(x45332)
    val x47262_x47261 = OpDef(op=FixAdd, inputs=List(x47259, x47260)).name("x47262_x47261").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // FixAdd(x47259,x47260)
    // x47262 = SimpleStruct(ArrayBuffer((offset,x47261), (size,Const(64)), (isLoad,Const(true))))
    val x47263 = OpDef(op=BitAnd, inputs=List(b30739, b30730)).name("x47263").ctrl(x47267).srcCtx("UnrollingBase.scala:28:66") // And(b30739,b30730)
    val x47264 = OpDef(op=BitAnd, inputs=List(b30731, b30254)).name("x47264").ctrl(x47267).srcCtx("UnrollingBase.scala:28:66") // And(b30731,b30254)
    val x47265 = OpDef(op=BitAnd, inputs=List(x47263, x47264)).name("x47265").ctrl(x47267).srcCtx("UnrollingBase.scala:28:66") // And(x47263,x47264)
    val x47266_b48494_b48492 = WriteMem(b48492, x47262_x47261).name("x47266_b48494_b48492").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // StreamWrite(x47251,x47262,x47265)
    val x47266_b48495_b48493 = WriteMem(b48493, Const(64)).name("x47266_b48495_b48493").ctrl(x47267).srcCtx("CellsPar.scala:298:20") // StreamWrite(x47251,x47262,x47265)
    val x47268 = FringeDenseLoad(dram=List(x45332), cmdStream=List(b48492, b48493), dataStream=List(x47252)).name("x47268").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // FringeDenseLoad(x45332,x47251,x47252)
    val x47269 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47269").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47270 = CounterChain(List(x47269)).name("x47270").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // CounterChainNew(List(x47269))
    val x47278 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47270).name("x47278").ctrl(x47279).srcCtx("CellsPar.scala:298:20") // UnrolledForeach(List(b30739, b30730, b30731, b30254),x47270,Block(Const(())),List(List(b30760)),List(List(b30761)))
    val b30760 = CounterIter(x47269, None).name("b30760").ctrl(x47278) // b30760
    val b30761 = Const(true).name("b30761").ctrl(x47278) // b30761
    val x47271 = OpDef(op=BitAnd, inputs=List(b30761, b30739)).name("x47271").ctrl(x47278).srcCtx("UnrollingBase.scala:28:66") // And(b30761,b30739)
    val x47272 = OpDef(op=BitAnd, inputs=List(b30730, b30731)).name("x47272").ctrl(x47278).srcCtx("UnrollingBase.scala:28:66") // And(b30730,b30731)
    val x47273 = OpDef(op=BitAnd, inputs=List(x47271, x47272)).name("x47273").ctrl(x47278).srcCtx("UnrollingBase.scala:28:66") // And(x47271,x47272)
    val x47274 = OpDef(op=BitAnd, inputs=List(x47273, b30254)).name("x47274").ctrl(x47278).srcCtx("UnrollingBase.scala:28:66") // And(x47273,b30254)
    val x47275_x47275 = ReadMem(x47252).name("x47275_x47275").ctrl(x47278).srcCtx("CellsPar.scala:298:20") // ParStreamRead(x47252,List(x47274))
    val x47276_x47276 = x47275_x47275 // x47276 = VectorApply(x47275,0)
    val x47277 = StoreBanks(List(x47245_d0_b0), List(b30738, b30760), x47276_x47276).name("x47277").ctrl(x47278).srcCtx("CellsPar.scala:298:20") // ParSRAMStore(x47245,List(List(b30738, b30760)),List(x47276),List(x47274))
    val x47280 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47280").ctrl(x47358).srcCtx("CellsPar.scala:299:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47281 = CounterChain(List(x47280)).name("x47281").ctrl(x47358).srcCtx("CellsPar.scala:299:46") // CounterChainNew(List(x47280))
    val x47357 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47281).name("x47357").ctrl(x47358).srcCtx("CellsPar.scala:299:46") // UnrolledForeach(List(b30730, b30731, b30254),x47281,Block(Const(())),List(List(b30773)),List(List(b30774)))
    val b30773 = CounterIter(x47280, Some(0)).name("b30773").ctrl(x47357) // b30773
    val b30774 = Const(true).name("b30774").ctrl(x47357) // b30774
    val x47282 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47282").ctrl(x47357).srcCtx("CellsPar.scala:300:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47283 = CounterChain(List(x47282)).name("x47283").ctrl(x47357).srcCtx("CellsPar.scala:300:42") // CounterChainNew(List(x47282))
    val x47356 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47283).name("x47356").ctrl(x47357).srcCtx("CellsPar.scala:300:42") // UnrolledForeach(List(b30774, b30730, b30731, b30254),x47283,Block(Const(())),List(List(b30777)),List(List(b30778)))
    val b30777 = CounterIter(x47282, Some(0)).name("b30777").ctrl(x47356) // b30777
    val b30778 = Const(true).name("b30778").ctrl(x47356) // b30778
    val x47284_d0 = Reg(init=Some(0.0)).name("x47284_d0").ctrl(x47356).srcCtx("CellsPar.scala:301:34:prod") // x47284 = RegNew(Const(0))
    isAccum(x47284_d0) = false
    bufferDepthOf(x47284_d0) = 2
    val x47284_d1 = Reg(init=Some(0.0)).name("x47284_d1").ctrl(x47356).srcCtx("CellsPar.scala:301:34:prod") // x47284 = RegNew(Const(0))
    isAccum(x47284_d1) = true
    bufferDepthOf(x47284_d1) = 1
    val x47285 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47285").ctrl(x47356).srcCtx("CellsPar.scala:301:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47286 = CounterChain(List(x47285)).name("x47286").ctrl(x47356).srcCtx("CellsPar.scala:321:15") // CounterChainNew(List(x47285))
    val x47320 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47286).name("x47320").ctrl(x47356).srcCtx("CellsPar.scala:321:15") // UnrolledReduce(List(b30778, b30774, b30730, b30731, b30254),x47286,x47284,Block((x47284) => Const(())),List(List(b30782)),List(List(b30783)))
    val b30782 = CounterIter(x47285, None).name("b30782").ctrl(x47320) // b30782
    val b30783 = Const(true).name("b30783").ctrl(x47320) // b30783
    val x47287 = OpDef(op=FixAdd, inputs=List(b30728, b30782)).name("x47287").ctrl(x47320).srcCtx("CellsPar.scala:302:42:reduce_size_offset") // FixAdd(b30728,b30782)
    val x47288 = OpDef(op=BitAnd, inputs=List(b30783, b30778)).name("x47288").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(b30783,b30778)
    val x47289 = OpDef(op=BitAnd, inputs=List(b30774, b30730)).name("x47289").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(b30774,b30730)
    val x47290 = OpDef(op=BitAnd, inputs=List(b30731, b30254)).name("x47290").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(b30731,b30254)
    val x47291 = OpDef(op=BitAnd, inputs=List(x47288, x47289)).name("x47291").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(x47288,x47289)
    val x47292 = OpDef(op=BitAnd, inputs=List(x47291, x47290)).name("x47292").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(x47291,x47290)
    val x47293 = LoadBanks(List(x47245_d0_b0), List(b30782, b30777)).name("x47293").ctrl(x47320).srcCtx("CellsPar.scala:311:34:tk") // ParSRAMLoad(x47245,List(List(b30782, b30777)),List(x47292))
    val x47294 = x47293 // x47294 = VectorApply(x47293,0)
    val x47295 = OpDef(op=FixLt, inputs=List(x47287, Const(512))).name("x47295").ctrl(x47320).srcCtx("CellsPar.scala:313:36") // FixLt(x47287,Const(512))
    val x47296 = LoadBanks(List(x45530_d0_b0), List(b30773, x47287)).name("x47296").ctrl(x47320).srcCtx("CellsPar.scala:314:19") // ParSRAMLoad(x45530,List(List(b30773, x47287)),List(x47292))
    val x47297 = x47296 // x47297 = VectorApply(x47296,0)
    val x47298 = OpDef(op=FixMul, inputs=List(x47297, x47294)).name("x47298").ctrl(x47320).srcCtx("CellsPar.scala:314:43") // FixMul(x47297,x47294)
    val x47299 = OpDef(op=FixLeq, inputs=List(Const(512), x47287)).name("x47299").ctrl(x47320).srcCtx("CellsPar.scala:316:32") // FixLeq(Const(512),x47287)
    val x47300 = OpDef(op=FixLt, inputs=List(x47287, Const(1024))).name("x47300").ctrl(x47320).srcCtx("CellsPar.scala:316:76") // FixLt(x47287,Const(1024))
    val x47301 = OpDef(op=BitAnd, inputs=List(x47299, x47300)).name("x47301").ctrl(x47320).srcCtx("CellsPar.scala:316:54") // And(x47299,x47300)
    val x47302 = OpDef(op=FixSub, inputs=List(x47287, Const(512))).name("x47302").ctrl(x47320).srcCtx("CellsPar.scala:317:44") // FixSub(x47287,Const(512))
    val x47303 = LoadBanks(List(x45538_d0_b0), List(b30773, x47302)).name("x47303").ctrl(x47320).srcCtx("CellsPar.scala:317:21") // ParSRAMLoad(x45538,List(List(b30773, x47302)),List(x47292))
    val x47304 = x47303 // x47304 = VectorApply(x47303,0)
    val x47305 = OpDef(op=FixMul, inputs=List(x47304, x47294)).name("x47305").ctrl(x47320).srcCtx("CellsPar.scala:317:60") // FixMul(x47304,x47294)
    val x47306 = OpDef(op=FixSub, inputs=List(x47287, Const(1024))).name("x47306").ctrl(x47320).srcCtx("CellsPar.scala:318:43") // FixSub(x47287,Const(1024))
    val x47307 = LoadBanks(List(x45545_d5_b0), List(b30773, x47306)).name("x47307").ctrl(x47320).srcCtx("CellsPar.scala:318:20") // ParSRAMLoad(x45545,List(List(b30773, x47306)),List(x47292))
    val x47308 = x47307 // x47308 = VectorApply(x47307,0)
    val x47309 = OpDef(op=FixMul, inputs=List(x47308, x47294)).name("x47309").ctrl(x47320).srcCtx("CellsPar.scala:318:60") // FixMul(x47308,x47294)
    val x47310 = OpDef(op=MuxOp, inputs=List(x47301, x47305, x47309)).name("x47310").ctrl(x47320).srcCtx("CellsPar.scala:315:20") // Mux(x47301,x47305,x47309)
    val x47311 = OpDef(op=MuxOp, inputs=List(x47295, x47298, x47310)).name("x47311").ctrl(x47320).srcCtx("CellsPar.scala:312:18") // Mux(x47295,x47298,x47310)
    val x47312 = ReadMem(x47284_d1).name("x47312").ctrl(x47320).srcCtx("CellsPar.scala:321:15") // RegRead(x47284)
    val x47313 = OpDef(op=FixEql, inputs=List(b30782, Const(0))).name("x47313").ctrl(x47320).srcCtx("CellsPar.scala:321:15") // FixEql(b30782,Const(0))
    val x47314 = ReduceAccumOp(op=FixAdd, input=x47311, accum=x47312).name("x47314").ctrl(x47320).srcCtx("CellsPar.scala:321:17") // FixAdd(x47311,x47312)
    val x47315 = OpDef(op=BitAnd, inputs=List(b30778, b30774)).name("x47315").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(b30778,b30774)
    val x47316 = OpDef(op=BitAnd, inputs=List(b30730, b30731)).name("x47316").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(b30730,b30731)
    val x47317 = OpDef(op=BitAnd, inputs=List(x47315, x47316)).name("x47317").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(x47315,x47316)
    val x47318 = OpDef(op=BitAnd, inputs=List(x47317, b30254)).name("x47318").ctrl(x47320).srcCtx("UnrollingBase.scala:28:66") // And(x47317,b30254)
    val x47319_x47284_d0 = WriteMem(x47284_d0, x47314).name("x47319_x47284_d0").ctrl(x47320).srcCtx("CellsPar.scala:321:15") // RegWrite(x47284,x47314,x47318)
    val x47319_x47284_d1 = WriteMem(x47284_d1, x47314).name("x47319_x47284_d1").ctrl(x47320).srcCtx("CellsPar.scala:321:15") // RegWrite(x47284,x47314,x47318)
    val x47355 = UnitController(style=SeqPipe, level=InnerControl).name("x47355").ctrl(x47356).srcCtx("CellsPar.scala:300:42") // UnitPipe(List(b30778, b30774, b30730, b30731, b30254),Block(Const(())))
    val x47321 = OpDef(op=FixAdd, inputs=List(b30729, b30777)).name("x47321").ctrl(x47355).srcCtx("CellsPar.scala:323:31:colOffset") // FixAdd(b30729,b30777)
    val x47322 = ReadMem(x47284_d0).name("x47322").ctrl(x47355).srcCtx("CellsPar.scala:324:28") // RegRead(x47284)
    val x47323 = OpDef(op=FixEql, inputs=List(b30728, Const(0))).name("x47323").ctrl(x47355).srcCtx("CellsPar.scala:324:42") // FixEql(b30728,Const(0))
    val x47324 = OpDef(op=FixAdd, inputs=List(x47321, Const(1536))).name("x47324").ctrl(x47355).srcCtx("CellsPar.scala:324:66") // FixAdd(x47321,Const(1536))
    val x47325 = OpDef(op=BitAnd, inputs=List(b30778, b30774)).name("x47325").ctrl(x47355).srcCtx("UnrollingBase.scala:28:66") // And(b30778,b30774)
    val x47326 = OpDef(op=BitAnd, inputs=List(b30730, b30731)).name("x47326").ctrl(x47355).srcCtx("UnrollingBase.scala:28:66") // And(b30730,b30731)
    val x47327 = OpDef(op=BitAnd, inputs=List(x47325, x47326)).name("x47327").ctrl(x47355).srcCtx("UnrollingBase.scala:28:66") // And(x47325,x47326)
    val x47328 = OpDef(op=BitAnd, inputs=List(x47327, b30254)).name("x47328").ctrl(x47355).srcCtx("UnrollingBase.scala:28:66") // And(x47327,b30254)
    val x47329 = LoadBanks(List(x45551_d0_b0), List(Const(0), x47324)).name("x47329").ctrl(x47355).srcCtx("CellsPar.scala:324:52") // SRAMLoad(x45551,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47324),Const(0),x47328)
    val x47330 = LoadBanks(List(x45550_d1_b0), List(b30773, x47321)).name("x47330").ctrl(x47355).srcCtx("CellsPar.scala:324:91") // SRAMLoad(x45550,ArrayBuffer(Const(1), Const(512)),List(b30773, x47321),Const(0),x47328)
    val x47331 = OpDef(op=MuxOp, inputs=List(x47323, x47329, x47330)).name("x47331").ctrl(x47355).srcCtx("CellsPar.scala:324:39") // Mux(x47323,x47329,x47330)
    val x47332 = OpDef(op=FixAdd, inputs=List(x47322, x47331)).name("x47332").ctrl(x47355).srcCtx("CellsPar.scala:324:34:ele") // FixAdd(x47322,x47331)
    val x47333 = OpDef(op=FixLeq, inputs=List(Const(1520), b30728)).name("x47333").ctrl(x47355).srcCtx("CellsPar.scala:332:17") // FixLeq(Const(1520),b30728)
    // x47334 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47334_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x47334_int1").ctrl(x47355).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47334_int2 = OpDef(op=FixSla, inputs=List(x47334_int1, Const(24))).name("x47334_int2").ctrl(x47355).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47334_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x47334_frac1").ctrl(x47355).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47334_frac2 = OpDef(op=FixSla, inputs=List(x47334_frac1, Const(24))).name("x47334_frac2").ctrl(x47355).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    val x47334 = OpDef(op=BitOr, inputs=List(x47334_int2, x47334_frac2)).name("x47334").ctrl(x47355).srcCtx("CellsPar.scala:333:45") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x47335 = OpDef(op=FixAdd, inputs=List(x47332, x47334)).name("x47335").ctrl(x47355).srcCtx("CellsPar.scala:333:30") // FixAdd(x47332,x47334)
    val x47336 = OpDef(op=FixSra, inputs=List(x47335, Const(1))).name("x47336").ctrl(x47355).srcCtx("CellsPar.scala:29:22") // FixRsh(x47335,Const(1))
    val x47337 = x47336 // FixConvert(x47336,TRUE,_8,_24) (Same Type. No op)
    val x47338 = OpDef(op=FixAbs, inputs=List(x47337)).name("x47338").ctrl(x47355).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47337)
    val x47339 = OpDef(op=FixLt, inputs=List(Const(2.5), x47338)).name("x47339").ctrl(x47355).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47338)
    val x47340 = OpDef(op=FixLt, inputs=List(Const(0.5), x47338)).name("x47340").ctrl(x47355).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47338)
    val x47341 = OpDef(op=FixLeq, inputs=List(x47338, Const(2.5))).name("x47341").ctrl(x47355).srcCtx("CellsPar.scala:54:52") // FixLeq(x47338,Const(2.5))
    val x47342 = OpDef(op=BitAnd, inputs=List(x47340, x47341)).name("x47342").ctrl(x47355).srcCtx("CellsPar.scala:54:43:cond2") // And(x47340,x47341)
    val x47343 = OpDef(op=FixSra, inputs=List(x47338, Const(2))).name("x47343").ctrl(x47355).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47338,Const(2))
    val x47344 = OpDef(op=FixAdd, inputs=List(x47343, Const(0.375))).name("x47344").ctrl(x47355).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47343,Const(0.375))
    val x47345 = OpDef(op=MuxOp, inputs=List(x47342, x47344, x47338)).name("x47345").ctrl(x47355).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47342,x47344,x47338)
    val x47346 = OpDef(op=MuxOp, inputs=List(x47339, Const(1.0), x47345)).name("x47346").ctrl(x47355).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47339,Const(1),x47345)
    val x47347 = OpDef(op=FixNeg, inputs=List(x47346)).name("x47347").ctrl(x47355).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47346)
    val x47348 = OpDef(op=FixLt, inputs=List(x47337, Const(0.0))).name("x47348").ctrl(x47355).srcCtx("CellsPar.scala:63:22") // FixLt(x47337,Const(0))
    val x47349 = OpDef(op=MuxOp, inputs=List(x47348, x47347, x47346)).name("x47349").ctrl(x47355).srcCtx("CellsPar.scala:63:17:re") // Mux(x47348,x47347,x47346)
    val x47350 = x47349 // FixConvert(x47349,TRUE,_8,_24) (Same Type. No op)
    val x47351 = OpDef(op=FixAdd, inputs=List(x47350, Const(1.0))).name("x47351").ctrl(x47355).srcCtx("CellsPar.scala:29:33") // FixAdd(x47350,Const(1))
    val x47352 = OpDef(op=FixSra, inputs=List(x47351, Const(1))).name("x47352").ctrl(x47355).srcCtx("CellsPar.scala:29:44") // FixRsh(x47351,Const(1))
    val x47353 = OpDef(op=MuxOp, inputs=List(x47333, x47352, x47332)).name("x47353").ctrl(x47355).srcCtx("CellsPar.scala:331:43") // Mux(x47333,x47352,x47332)
    val x47354 = StoreBanks(List(x45550_d0_b0, x45550_d1_b0), List(b30773, x47321), x47353).name("x47354").ctrl(x47355).srcCtx("CellsPar.scala:331:38") // SRAMStore(x45550,ArrayBuffer(Const(1), Const(512)),List(b30773, x47321),Const(0),x47353,x47328)
    val x47359 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x47359").ctrl(x48337).srcCtx("CellsPar.scala:350:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x47360 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47360").ctrl(x48337).srcCtx("CellsPar.scala:350:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47361 = CounterChain(List(x47360,x47359)).name("x47361").ctrl(x48337).srcCtx("CellsPar.scala:350:62") // CounterChainNew(List(x47360, x47359))
    val x47394 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47361).name("x47394").ctrl(x48337).srcCtx("CellsPar.scala:350:62") // UnrolledForeach(List(b30254),x47361,Block(Const(())),List(List(b30860), List(b30861)),List(List(b30862), List(b30863)))
    val b30860 = CounterIter(x47360, Some(0)).name("b30860").ctrl(x47394) // b30860
    val b30862 = Const(true).name("b30862").ctrl(x47394) // b30862
    val b30861 = CounterIter(x47359, None).name("b30861").ctrl(x47394) // b30861
    val b30863 = Const(true).name("b30863").ctrl(x47394) // b30863
    val x47362 = OpDef(op=BitAnd, inputs=List(b30862, b30863)).name("x47362").ctrl(x47394).srcCtx("UnrollingBase.scala:28:66") // And(b30862,b30863)
    val x47363 = OpDef(op=BitAnd, inputs=List(x47362, b30254)).name("x47363").ctrl(x47394).srcCtx("UnrollingBase.scala:28:66") // And(x47362,b30254)
    val x47364 = LoadBanks(List(x45546_d0_b0), List(b30860, b30861)).name("x47364").ctrl(x47394).srcCtx("CellsPar.scala:351:22") // ParSRAMLoad(x45546,List(List(b30860, b30861)),List(x47363))
    val x47365 = x47364 // x47365 = VectorApply(x47364,0)
    val x47366 = LoadBanks(List(x45549_d0_b0), List(b30860, b30861)).name("x47366").ctrl(x47394).srcCtx("CellsPar.scala:351:34") // ParSRAMLoad(x45549,List(List(b30860, b30861)),List(x47363))
    val x47367 = x47366 // x47367 = VectorApply(x47366,0)
    val x47368 = OpDef(op=FixMul, inputs=List(x47365, x47367)).name("x47368").ctrl(x47394).srcCtx("CellsPar.scala:351:28") // FixMul(x47365,x47367)
    val x47369 = LoadBanks(List(x45547_d0_b0), List(b30860, b30861)).name("x47369").ctrl(x47394).srcCtx("CellsPar.scala:351:46") // ParSRAMLoad(x45547,List(List(b30860, b30861)),List(x47363))
    val x47370 = x47369 // x47370 = VectorApply(x47369,0)
    val x47371 = LoadBanks(List(x45548_d0_b0), List(b30860, b30861)).name("x47371").ctrl(x47394).srcCtx("CellsPar.scala:351:59") // ParSRAMLoad(x45548,List(List(b30860, b30861)),List(x47363))
    val x47372 = x47371 // x47372 = VectorApply(x47371,0)
    val x47373 = OpDef(op=FixMul, inputs=List(x47370, x47372)).name("x47373").ctrl(x47394).srcCtx("CellsPar.scala:351:52") // FixMul(x47370,x47372)
    val x47374 = OpDef(op=FixAdd, inputs=List(x47368, x47373)).name("x47374").ctrl(x47394).srcCtx("CellsPar.scala:351:40:new_c") // FixAdd(x47368,x47373)
    val x47375 = x47374 // FixConvert(x47374,TRUE,_8,_24) (Same Type. No op)
    val x47376 = OpDef(op=FixAbs, inputs=List(x47375)).name("x47376").ctrl(x47394).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47375)
    val x47377 = OpDef(op=FixLt, inputs=List(Const(2.5), x47376)).name("x47377").ctrl(x47394).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47376)
    val x47378 = OpDef(op=FixLt, inputs=List(Const(0.5), x47376)).name("x47378").ctrl(x47394).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47376)
    val x47379 = OpDef(op=FixLeq, inputs=List(x47376, Const(2.5))).name("x47379").ctrl(x47394).srcCtx("CellsPar.scala:54:52") // FixLeq(x47376,Const(2.5))
    val x47380 = OpDef(op=BitAnd, inputs=List(x47378, x47379)).name("x47380").ctrl(x47394).srcCtx("CellsPar.scala:54:43:cond2") // And(x47378,x47379)
    val x47381 = OpDef(op=FixSra, inputs=List(x47376, Const(2))).name("x47381").ctrl(x47394).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47376,Const(2))
    val x47382 = OpDef(op=FixAdd, inputs=List(x47381, Const(0.375))).name("x47382").ctrl(x47394).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47381,Const(0.375))
    val x47383 = OpDef(op=MuxOp, inputs=List(x47380, x47382, x47376)).name("x47383").ctrl(x47394).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47380,x47382,x47376)
    val x47384 = OpDef(op=MuxOp, inputs=List(x47377, Const(1.0), x47383)).name("x47384").ctrl(x47394).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47377,Const(1),x47383)
    val x47385 = OpDef(op=FixNeg, inputs=List(x47384)).name("x47385").ctrl(x47394).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47384)
    val x47386 = OpDef(op=FixLt, inputs=List(x47375, Const(0.0))).name("x47386").ctrl(x47394).srcCtx("CellsPar.scala:63:22") // FixLt(x47375,Const(0))
    val x47387 = OpDef(op=MuxOp, inputs=List(x47386, x47385, x47384)).name("x47387").ctrl(x47394).srcCtx("CellsPar.scala:63:17:re") // Mux(x47386,x47385,x47384)
    val x47388 = x47387 // FixConvert(x47387,TRUE,_8,_24) (Same Type. No op)
    val x47389 = LoadBanks(List(x45550_d0_b0), List(b30860, b30861)).name("x47389").ctrl(x47394).srcCtx("CellsPar.scala:352:45") // ParSRAMLoad(x45550,List(List(b30860, b30861)),List(x47363))
    val x47390 = x47389 // x47390 = VectorApply(x47389,0)
    val x47391 = OpDef(op=FixMul, inputs=List(x47388, x47390)).name("x47391").ctrl(x47394).srcCtx("CellsPar.scala:352:39") // FixMul(x47388,x47390)
    val x47392 = StoreBanks(List(x45545_d0_b0, x45545_d5_b0, x45545_d1_b0, x45545_d6_b0, x45545_d2_b0, x45545_d7_b0, x45545_d3_b0, x45545_d8_b0, x45545_d4_b0), List(b30860, b30861), x47391).name("x47392").ctrl(x47394).srcCtx("CellsPar.scala:352:16") // ParSRAMStore(x45545,List(List(b30860, b30861)),List(x47391),List(x47363))
    val x47393 = StoreBanks(List(x45546_d0_b0), List(b30860, b30861), x47374).name("x47393").ctrl(x47394).srcCtx("CellsPar.scala:353:16") // ParSRAMStore(x45546,List(List(b30860, b30861)),List(x47374),List(x47363))
    val x47395 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47395").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47396 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x47396").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x47397 = CounterChain(List(x47396,x47395)).name("x47397").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x47396, x47395))
    val x47502 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47397).name("x47502").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x47397,Block(Const(())),List(List(b30900), List(b30901)),List(List(b30902), List(b30903)))
    val b30900 = CounterIter(x47396, Some(0)).name("b30900").ctrl(x47502) // b30900
    val b30902 = Const(true).name("b30902").ctrl(x47502) // b30902
    val b30901 = CounterIter(x47395, Some(0)).name("b30901").ctrl(x47502) // b30901
    val b30903 = Const(true).name("b30903").ctrl(x47502) // b30903
    val x47398_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47398_d0_b0").ctrl(x47502).srcCtx("CellsPar.scala:191:33:tileKernel") // x47398 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47398_d0_b0) = false
    bufferDepthOf(x47398_d0_b0) = 2
    val x47401 = UnitController(style=SeqPipe, level=InnerControl).name("x47401").ctrl(x47502).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b30902, b30903, b30254),Block(Const(())))
    val x47399 = OpDef(op=FixAdd, inputs=List(b30900, Const(16))).name("x47399").ctrl(x47401).srcCtx("CellsPar.scala:192:36") // FixAdd(b30900,Const(16))
    val x47400 = OpDef(op=FixAdd, inputs=List(b30901, Const(16))).name("x47400").ctrl(x47401).srcCtx("CellsPar.scala:192:45") // FixAdd(b30901,Const(16))
    val x47402 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47402").ctrl(x47502).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47403 = CounterChain(List(x47402)).name("x47403").ctrl(x47502).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47402))
    val x47432 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47403).name("x47432").ctrl(x47502).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b30902, b30903, b30254),x47403,Block(Const(())),List(List(b30910)),List(List(b30911)))
    val b30910 = CounterIter(x47402, Some(0)).name("b30910").ctrl(x47432) // b30910
    val b30911 = Const(true).name("b30911").ctrl(x47432) // b30911
    val b48496 = StreamOut(field="offset").name("b48496").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // x47404 = StreamOutNew(BurstCmdBus)
    isAccum(b48496) = false
    bufferDepthOf(b48496) = 1
    val b48497 = StreamOut(field="size").name("b48497").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // x47404 = StreamOutNew(BurstCmdBus)
    isAccum(b48497) = false
    bufferDepthOf(b48497) = 1
    val x47405 = StreamIn(field="data").name("x47405").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // x47405 = StreamInNew(BurstDataBus())
    isAccum(x47405) = false
    bufferDepthOf(x47405) = 1
    val x47420 = UnitController(style=SeqPipe, level=InnerControl).name("x47420").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b30911, b30902, b30903, b30254),Block(x47419))
    val x47406 = OpDef(op=FixAdd, inputs=List(b30900, b30910)).name("x47406").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // FixAdd(b30900,b30910)
    val x47407 = x47406 // FixConvert(x47406,TRUE,_32,_0) (Same Type. No op)
    val x47408 = OpDef(op=FixSla, inputs=List(x47407, Const(9))).name("x47408").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // FixLsh(x47407,Const(9))
    val x47409 = b30901 // FixConvert(b30901,TRUE,_32,_0) (Same Type. No op)
    val x47410 = OpDef(op=FixAdd, inputs=List(x47408, x47409)).name("x47410").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // FixAdd(x47408,x47409)
    val x47411 = OpDef(op=FixSla, inputs=List(x47410, Const(2))).name("x47411").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // FixLsh(x47410,Const(2))
    val x47412 = x47411 // FixConvert(x47411,TRUE,_64,_0)
    val x47413 = DramAddress(x45405).name("x47413").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45405)
    val x47415_x47414 = OpDef(op=FixAdd, inputs=List(x47412, x47413)).name("x47415_x47414").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // FixAdd(x47412,x47413)
    // x47415 = SimpleStruct(ArrayBuffer((offset,x47414), (size,Const(64)), (isLoad,Const(true))))
    val x47416 = OpDef(op=BitAnd, inputs=List(b30911, b30902)).name("x47416").ctrl(x47420).srcCtx("UnrollingBase.scala:28:66") // And(b30911,b30902)
    val x47417 = OpDef(op=BitAnd, inputs=List(b30903, b30254)).name("x47417").ctrl(x47420).srcCtx("UnrollingBase.scala:28:66") // And(b30903,b30254)
    val x47418 = OpDef(op=BitAnd, inputs=List(x47416, x47417)).name("x47418").ctrl(x47420).srcCtx("UnrollingBase.scala:28:66") // And(x47416,x47417)
    val x47419_b48498_b48496 = WriteMem(b48496, x47415_x47414).name("x47419_b48498_b48496").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47404,x47415,x47418)
    val x47419_b48499_b48497 = WriteMem(b48497, Const(64)).name("x47419_b48499_b48497").ctrl(x47420).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47404,x47415,x47418)
    val x47421 = FringeDenseLoad(dram=List(x45405), cmdStream=List(b48496, b48497), dataStream=List(x47405)).name("x47421").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45405,x47404,x47405)
    val x47422 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47422").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47423 = CounterChain(List(x47422)).name("x47423").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47422))
    val x47431 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47423).name("x47431").ctrl(x47432).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b30911, b30902, b30903, b30254),x47423,Block(Const(())),List(List(b30932)),List(List(b30933)))
    val b30932 = CounterIter(x47422, None).name("b30932").ctrl(x47431) // b30932
    val b30933 = Const(true).name("b30933").ctrl(x47431) // b30933
    val x47424 = OpDef(op=BitAnd, inputs=List(b30933, b30911)).name("x47424").ctrl(x47431).srcCtx("UnrollingBase.scala:28:66") // And(b30933,b30911)
    val x47425 = OpDef(op=BitAnd, inputs=List(b30902, b30903)).name("x47425").ctrl(x47431).srcCtx("UnrollingBase.scala:28:66") // And(b30902,b30903)
    val x47426 = OpDef(op=BitAnd, inputs=List(x47424, x47425)).name("x47426").ctrl(x47431).srcCtx("UnrollingBase.scala:28:66") // And(x47424,x47425)
    val x47427 = OpDef(op=BitAnd, inputs=List(x47426, b30254)).name("x47427").ctrl(x47431).srcCtx("UnrollingBase.scala:28:66") // And(x47426,b30254)
    val x47428_x47428 = ReadMem(x47405).name("x47428_x47428").ctrl(x47431).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x47405,List(x47427))
    val x47429_x47429 = x47428_x47428 // x47429 = VectorApply(x47428,0)
    val x47430 = StoreBanks(List(x47398_d0_b0), List(b30910, b30932), x47429_x47429).name("x47430").ctrl(x47431).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x47398,List(List(b30910, b30932)),List(x47429),List(x47427))
    val x47433 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47433").ctrl(x47502).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47434 = CounterChain(List(x47433)).name("x47434").ctrl(x47502).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x47433))
    val x47501 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47434).name("x47501").ctrl(x47502).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b30902, b30903, b30254),x47434,Block(Const(())),List(List(b30945)),List(List(b30946)))
    val b30945 = CounterIter(x47433, Some(0)).name("b30945").ctrl(x47501) // b30945
    val b30946 = Const(true).name("b30946").ctrl(x47501) // b30946
    val x47435 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47435").ctrl(x47501).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47436 = CounterChain(List(x47435)).name("x47436").ctrl(x47501).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x47435))
    val x47500 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47436).name("x47500").ctrl(x47501).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b30946, b30902, b30903, b30254),x47436,Block(Const(())),List(List(b30949)),List(List(b30950)))
    val b30949 = CounterIter(x47435, Some(0)).name("b30949").ctrl(x47500) // b30949
    val b30950 = Const(true).name("b30950").ctrl(x47500) // b30950
    val x47437_d0 = Reg(init=Some(0.0)).name("x47437_d0").ctrl(x47500).srcCtx("CellsPar.scala:195:34:prod") // x47437 = RegNew(Const(0))
    isAccum(x47437_d0) = false
    bufferDepthOf(x47437_d0) = 2
    val x47437_d1 = Reg(init=Some(0.0)).name("x47437_d1").ctrl(x47500).srcCtx("CellsPar.scala:195:34:prod") // x47437 = RegNew(Const(0))
    isAccum(x47437_d1) = true
    bufferDepthOf(x47437_d1) = 1
    val x47438 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47438").ctrl(x47500).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47439 = CounterChain(List(x47438)).name("x47439").ctrl(x47500).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x47438))
    val x47465 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47439).name("x47465").ctrl(x47500).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b30950, b30946, b30902, b30903, b30254),x47439,x47437,Block((x47437) => Const(())),List(List(b30954)),List(List(b30955)))
    val b30954 = CounterIter(x47438, None).name("b30954").ctrl(x47465) // b30954
    val b30955 = Const(true).name("b30955").ctrl(x47465) // b30955
    val x47440 = OpDef(op=FixAdd, inputs=List(b30900, b30954)).name("x47440").ctrl(x47465).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b30900,b30954)
    val x47441 = OpDef(op=BitAnd, inputs=List(b30955, b30950)).name("x47441").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(b30955,b30950)
    val x47442 = OpDef(op=BitAnd, inputs=List(b30946, b30902)).name("x47442").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(b30946,b30902)
    val x47443 = OpDef(op=BitAnd, inputs=List(b30903, b30254)).name("x47443").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(b30903,b30254)
    val x47444 = OpDef(op=BitAnd, inputs=List(x47441, x47442)).name("x47444").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(x47441,x47442)
    val x47445 = OpDef(op=BitAnd, inputs=List(x47444, x47443)).name("x47445").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(x47444,x47443)
    val x47446 = LoadBanks(List(x47398_d0_b0), List(b30954, b30949)).name("x47446").ctrl(x47465).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x47398,List(List(b30954, b30949)),List(x47445))
    val x47447 = x47446 // x47447 = VectorApply(x47446,0)
    val x47448 = LoadBanks(List(x45545_d4_b0), List(b30945, x47440)).name("x47448").ctrl(x47465).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45545,List(List(b30945, x47440)),List(x47445))
    val x47449 = x47448 // x47449 = VectorApply(x47448,0)
    val x47450 = OpDef(op=FixMul, inputs=List(x47449, x47447)).name("x47450").ctrl(x47465).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x47449,x47447)
    val x47451 = OpDef(op=FixSub, inputs=List(x47440, Const(512))).name("x47451").ctrl(x47465).srcCtx("CellsPar.scala:205:51") // FixSub(x47440,Const(512))
    val x47452 = LoadBanks(List(x45552_d8_b0), List(b30945, x47451)).name("x47452").ctrl(x47465).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45552,List(List(b30945, x47451)),List(x47445))
    val x47453 = x47452 // x47453 = VectorApply(x47452,0)
    val x47454 = OpDef(op=FixMul, inputs=List(x47453, x47447)).name("x47454").ctrl(x47465).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x47453,x47447)
    val x47455 = OpDef(op=FixLt, inputs=List(x47440, Const(512))).name("x47455").ctrl(x47465).srcCtx("CellsPar.scala:206:38") // FixLt(x47440,Const(512))
    val x47456 = OpDef(op=MuxOp, inputs=List(x47455, x47450, x47454)).name("x47456").ctrl(x47465).srcCtx("CellsPar.scala:206:18") // Mux(x47455,x47450,x47454)
    val x47457 = ReadMem(x47437_d1).name("x47457").ctrl(x47465).srcCtx("CellsPar.scala:207:15") // RegRead(x47437)
    val x47458 = OpDef(op=FixEql, inputs=List(b30954, Const(0))).name("x47458").ctrl(x47465).srcCtx("CellsPar.scala:207:15") // FixEql(b30954,Const(0))
    val x47459 = ReduceAccumOp(op=FixAdd, input=x47456, accum=x47457).name("x47459").ctrl(x47465).srcCtx("CellsPar.scala:207:17") // FixAdd(x47456,x47457)
    val x47460 = OpDef(op=BitAnd, inputs=List(b30950, b30946)).name("x47460").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(b30950,b30946)
    val x47461 = OpDef(op=BitAnd, inputs=List(b30902, b30903)).name("x47461").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(b30902,b30903)
    val x47462 = OpDef(op=BitAnd, inputs=List(x47460, x47461)).name("x47462").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(x47460,x47461)
    val x47463 = OpDef(op=BitAnd, inputs=List(x47462, b30254)).name("x47463").ctrl(x47465).srcCtx("UnrollingBase.scala:28:66") // And(x47462,b30254)
    val x47464_x47437_d0 = WriteMem(x47437_d0, x47459).name("x47464_x47437_d0").ctrl(x47465).srcCtx("CellsPar.scala:207:15") // RegWrite(x47437,x47459,x47463)
    val x47464_x47437_d1 = WriteMem(x47437_d1, x47459).name("x47464_x47437_d1").ctrl(x47465).srcCtx("CellsPar.scala:207:15") // RegWrite(x47437,x47459,x47463)
    val x47499 = UnitController(style=SeqPipe, level=InnerControl).name("x47499").ctrl(x47500).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b30950, b30946, b30902, b30903, b30254),Block(Const(())))
    val x47466 = OpDef(op=FixAdd, inputs=List(b30901, b30949)).name("x47466").ctrl(x47499).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b30901,b30949)
    val x47467 = ReadMem(x47437_d0).name("x47467").ctrl(x47499).srcCtx("CellsPar.scala:210:28") // RegRead(x47437)
    val x47468 = OpDef(op=FixEql, inputs=List(b30900, Const(0))).name("x47468").ctrl(x47499).srcCtx("CellsPar.scala:210:42") // FixEql(b30900,Const(0))
    val x47469 = OpDef(op=BitAnd, inputs=List(b30950, b30946)).name("x47469").ctrl(x47499).srcCtx("UnrollingBase.scala:28:66") // And(b30950,b30946)
    val x47470 = OpDef(op=BitAnd, inputs=List(b30902, b30903)).name("x47470").ctrl(x47499).srcCtx("UnrollingBase.scala:28:66") // And(b30902,b30903)
    val x47471 = OpDef(op=BitAnd, inputs=List(x47469, x47470)).name("x47471").ctrl(x47499).srcCtx("UnrollingBase.scala:28:66") // And(x47469,x47470)
    val x47472 = OpDef(op=BitAnd, inputs=List(x47471, b30254)).name("x47472").ctrl(x47499).srcCtx("UnrollingBase.scala:28:66") // And(x47471,b30254)
    val x47473 = LoadBanks(List(x45558_d3_b0), List(Const(0), x47466)).name("x47473").ctrl(x47499).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45558,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47466),Const(0),x47472)
    val x47474 = LoadBanks(List(x45554_d1_b0), List(b30945, x47466)).name("x47474").ctrl(x47499).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45554,ArrayBuffer(Const(1), Const(512)),List(b30945, x47466),Const(0),x47472)
    val x47475 = OpDef(op=MuxOp, inputs=List(x47468, x47473, x47474)).name("x47475").ctrl(x47499).srcCtx("CellsPar.scala:210:39") // Mux(x47468,x47473,x47474)
    val x47476 = OpDef(op=FixAdd, inputs=List(x47467, x47475)).name("x47476").ctrl(x47499).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x47467,x47475)
    val x47477 = OpDef(op=FixLeq, inputs=List(Const(1008), b30900)).name("x47477").ctrl(x47499).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b30900)
    // x47478 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47478_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x47478_int1").ctrl(x47499).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47478_int2 = OpDef(op=FixSla, inputs=List(x47478_int1, Const(24))).name("x47478_int2").ctrl(x47499).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47478_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x47478_frac1").ctrl(x47499).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47478_frac2 = OpDef(op=FixSla, inputs=List(x47478_frac1, Const(24))).name("x47478_frac2").ctrl(x47499).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47478 = OpDef(op=BitOr, inputs=List(x47478_int2, x47478_frac2)).name("x47478").ctrl(x47499).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x47479 = OpDef(op=FixAdd, inputs=List(x47476, x47478)).name("x47479").ctrl(x47499).srcCtx("CellsPar.scala:218:87") // FixAdd(x47476,x47478)
    val x47480 = OpDef(op=FixSra, inputs=List(x47479, Const(1))).name("x47480").ctrl(x47499).srcCtx("CellsPar.scala:29:22") // FixRsh(x47479,Const(1))
    val x47481 = x47480 // FixConvert(x47480,TRUE,_8,_24) (Same Type. No op)
    val x47482 = OpDef(op=FixAbs, inputs=List(x47481)).name("x47482").ctrl(x47499).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47481)
    val x47483 = OpDef(op=FixLt, inputs=List(Const(2.5), x47482)).name("x47483").ctrl(x47499).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47482)
    val x47484 = OpDef(op=FixLt, inputs=List(Const(0.5), x47482)).name("x47484").ctrl(x47499).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47482)
    val x47485 = OpDef(op=FixLeq, inputs=List(x47482, Const(2.5))).name("x47485").ctrl(x47499).srcCtx("CellsPar.scala:54:52") // FixLeq(x47482,Const(2.5))
    val x47486 = OpDef(op=BitAnd, inputs=List(x47484, x47485)).name("x47486").ctrl(x47499).srcCtx("CellsPar.scala:54:43:cond2") // And(x47484,x47485)
    val x47487 = OpDef(op=FixSra, inputs=List(x47482, Const(2))).name("x47487").ctrl(x47499).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47482,Const(2))
    val x47488 = OpDef(op=FixAdd, inputs=List(x47487, Const(0.375))).name("x47488").ctrl(x47499).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47487,Const(0.375))
    val x47489 = OpDef(op=MuxOp, inputs=List(x47486, x47488, x47482)).name("x47489").ctrl(x47499).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47486,x47488,x47482)
    val x47490 = OpDef(op=MuxOp, inputs=List(x47483, Const(1.0), x47489)).name("x47490").ctrl(x47499).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47483,Const(1),x47489)
    val x47491 = OpDef(op=FixNeg, inputs=List(x47490)).name("x47491").ctrl(x47499).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47490)
    val x47492 = OpDef(op=FixLt, inputs=List(x47481, Const(0.0))).name("x47492").ctrl(x47499).srcCtx("CellsPar.scala:63:22") // FixLt(x47481,Const(0))
    val x47493 = OpDef(op=MuxOp, inputs=List(x47492, x47491, x47490)).name("x47493").ctrl(x47499).srcCtx("CellsPar.scala:63:17:re") // Mux(x47492,x47491,x47490)
    val x47494 = x47493 // FixConvert(x47493,TRUE,_8,_24) (Same Type. No op)
    val x47495 = OpDef(op=FixAdd, inputs=List(x47494, Const(1.0))).name("x47495").ctrl(x47499).srcCtx("CellsPar.scala:29:33") // FixAdd(x47494,Const(1))
    val x47496 = OpDef(op=FixSra, inputs=List(x47495, Const(1))).name("x47496").ctrl(x47499).srcCtx("CellsPar.scala:29:44") // FixRsh(x47495,Const(1))
    val x47497 = OpDef(op=MuxOp, inputs=List(x47477, x47496, x47476)).name("x47497").ctrl(x47499).srcCtx("CellsPar.scala:218:43") // Mux(x47477,x47496,x47476)
    val x47498 = StoreBanks(List(x45554_d0_b0, x45554_d1_b0), List(b30945, x47466), x47497).name("x47498").ctrl(x47499).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45554,ArrayBuffer(Const(1), Const(512)),List(b30945, x47466),Const(0),x47497,x47472)
    val x47503 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47503").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47504 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x47504").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x47505 = CounterChain(List(x47504,x47503)).name("x47505").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x47504, x47503))
    val x47608 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47505).name("x47608").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x47505,Block(Const(())),List(List(b31022), List(b31023)),List(List(b31024), List(b31025)))
    val b31022 = CounterIter(x47504, Some(0)).name("b31022").ctrl(x47608) // b31022
    val b31024 = Const(true).name("b31024").ctrl(x47608) // b31024
    val b31023 = CounterIter(x47503, Some(0)).name("b31023").ctrl(x47608) // b31023
    val b31025 = Const(true).name("b31025").ctrl(x47608) // b31025
    val x47506_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47506_d0_b0").ctrl(x47608).srcCtx("CellsPar.scala:191:33:tileKernel") // x47506 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47506_d0_b0) = false
    bufferDepthOf(x47506_d0_b0) = 2
    val x47509 = UnitController(style=SeqPipe, level=InnerControl).name("x47509").ctrl(x47608).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31024, b31025, b30254),Block(Const(())))
    val x47507 = OpDef(op=FixAdd, inputs=List(b31022, Const(16))).name("x47507").ctrl(x47509).srcCtx("CellsPar.scala:192:36") // FixAdd(b31022,Const(16))
    val x47508 = OpDef(op=FixAdd, inputs=List(b31023, Const(16))).name("x47508").ctrl(x47509).srcCtx("CellsPar.scala:192:45") // FixAdd(b31023,Const(16))
    val x47510 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47510").ctrl(x47608).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47511 = CounterChain(List(x47510)).name("x47511").ctrl(x47608).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47510))
    val x47540 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47511).name("x47540").ctrl(x47608).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31024, b31025, b30254),x47511,Block(Const(())),List(List(b31032)),List(List(b31033)))
    val b31032 = CounterIter(x47510, Some(0)).name("b31032").ctrl(x47540) // b31032
    val b31033 = Const(true).name("b31033").ctrl(x47540) // b31033
    val b48500 = StreamOut(field="offset").name("b48500").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // x47512 = StreamOutNew(BurstCmdBus)
    isAccum(b48500) = false
    bufferDepthOf(b48500) = 1
    val b48501 = StreamOut(field="size").name("b48501").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // x47512 = StreamOutNew(BurstCmdBus)
    isAccum(b48501) = false
    bufferDepthOf(b48501) = 1
    val x47513 = StreamIn(field="data").name("x47513").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // x47513 = StreamInNew(BurstDataBus())
    isAccum(x47513) = false
    bufferDepthOf(x47513) = 1
    val x47528 = UnitController(style=SeqPipe, level=InnerControl).name("x47528").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31033, b31024, b31025, b30254),Block(x47527))
    val x47514 = OpDef(op=FixAdd, inputs=List(b31022, b31032)).name("x47514").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // FixAdd(b31022,b31032)
    val x47515 = x47514 // FixConvert(x47514,TRUE,_32,_0) (Same Type. No op)
    val x47516 = OpDef(op=FixSla, inputs=List(x47515, Const(9))).name("x47516").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // FixLsh(x47515,Const(9))
    val x47517 = b31023 // FixConvert(b31023,TRUE,_32,_0) (Same Type. No op)
    val x47518 = OpDef(op=FixAdd, inputs=List(x47516, x47517)).name("x47518").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // FixAdd(x47516,x47517)
    val x47519 = OpDef(op=FixSla, inputs=List(x47518, Const(2))).name("x47519").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // FixLsh(x47518,Const(2))
    val x47520 = x47519 // FixConvert(x47519,TRUE,_64,_0)
    val x47521 = DramAddress(x45406).name("x47521").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45406)
    val x47523_x47522 = OpDef(op=FixAdd, inputs=List(x47520, x47521)).name("x47523_x47522").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // FixAdd(x47520,x47521)
    // x47523 = SimpleStruct(ArrayBuffer((offset,x47522), (size,Const(64)), (isLoad,Const(true))))
    val x47524 = OpDef(op=BitAnd, inputs=List(b31033, b31024)).name("x47524").ctrl(x47528).srcCtx("UnrollingBase.scala:28:66") // And(b31033,b31024)
    val x47525 = OpDef(op=BitAnd, inputs=List(b31025, b30254)).name("x47525").ctrl(x47528).srcCtx("UnrollingBase.scala:28:66") // And(b31025,b30254)
    val x47526 = OpDef(op=BitAnd, inputs=List(x47524, x47525)).name("x47526").ctrl(x47528).srcCtx("UnrollingBase.scala:28:66") // And(x47524,x47525)
    val x47527_b48502_b48500 = WriteMem(b48500, x47523_x47522).name("x47527_b48502_b48500").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47512,x47523,x47526)
    val x47527_b48503_b48501 = WriteMem(b48501, Const(64)).name("x47527_b48503_b48501").ctrl(x47528).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47512,x47523,x47526)
    val x47529 = FringeDenseLoad(dram=List(x45406), cmdStream=List(b48500, b48501), dataStream=List(x47513)).name("x47529").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45406,x47512,x47513)
    val x47530 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47530").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47531 = CounterChain(List(x47530)).name("x47531").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47530))
    val x47539 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47531).name("x47539").ctrl(x47540).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31033, b31024, b31025, b30254),x47531,Block(Const(())),List(List(b31054)),List(List(b31055)))
    val b31054 = CounterIter(x47530, None).name("b31054").ctrl(x47539) // b31054
    val b31055 = Const(true).name("b31055").ctrl(x47539) // b31055
    val x47532 = OpDef(op=BitAnd, inputs=List(b31055, b31033)).name("x47532").ctrl(x47539).srcCtx("UnrollingBase.scala:28:66") // And(b31055,b31033)
    val x47533 = OpDef(op=BitAnd, inputs=List(b31024, b31025)).name("x47533").ctrl(x47539).srcCtx("UnrollingBase.scala:28:66") // And(b31024,b31025)
    val x47534 = OpDef(op=BitAnd, inputs=List(x47532, x47533)).name("x47534").ctrl(x47539).srcCtx("UnrollingBase.scala:28:66") // And(x47532,x47533)
    val x47535 = OpDef(op=BitAnd, inputs=List(x47534, b30254)).name("x47535").ctrl(x47539).srcCtx("UnrollingBase.scala:28:66") // And(x47534,b30254)
    val x47536_x47536 = ReadMem(x47513).name("x47536_x47536").ctrl(x47539).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x47513,List(x47535))
    val x47537_x47537 = x47536_x47536 // x47537 = VectorApply(x47536,0)
    val x47538 = StoreBanks(List(x47506_d0_b0), List(b31032, b31054), x47537_x47537).name("x47538").ctrl(x47539).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x47506,List(List(b31032, b31054)),List(x47537),List(x47535))
    val x47541 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47541").ctrl(x47608).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47542 = CounterChain(List(x47541)).name("x47542").ctrl(x47608).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x47541))
    val x47607 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47542).name("x47607").ctrl(x47608).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31024, b31025, b30254),x47542,Block(Const(())),List(List(b31067)),List(List(b31068)))
    val b31067 = CounterIter(x47541, Some(0)).name("b31067").ctrl(x47607) // b31067
    val b31068 = Const(true).name("b31068").ctrl(x47607) // b31068
    val x47543 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47543").ctrl(x47607).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47544 = CounterChain(List(x47543)).name("x47544").ctrl(x47607).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x47543))
    val x47606 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47544).name("x47606").ctrl(x47607).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31068, b31024, b31025, b30254),x47544,Block(Const(())),List(List(b31071)),List(List(b31072)))
    val b31071 = CounterIter(x47543, Some(0)).name("b31071").ctrl(x47606) // b31071
    val b31072 = Const(true).name("b31072").ctrl(x47606) // b31072
    val x47545_d0 = Reg(init=Some(0.0)).name("x47545_d0").ctrl(x47606).srcCtx("CellsPar.scala:195:34:prod") // x47545 = RegNew(Const(0))
    isAccum(x47545_d0) = false
    bufferDepthOf(x47545_d0) = 2
    val x47545_d1 = Reg(init=Some(0.0)).name("x47545_d1").ctrl(x47606).srcCtx("CellsPar.scala:195:34:prod") // x47545 = RegNew(Const(0))
    isAccum(x47545_d1) = true
    bufferDepthOf(x47545_d1) = 1
    val x47546 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47546").ctrl(x47606).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47547 = CounterChain(List(x47546)).name("x47547").ctrl(x47606).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x47546))
    val x47573 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47547).name("x47573").ctrl(x47606).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31072, b31068, b31024, b31025, b30254),x47547,x47545,Block((x47545) => Const(())),List(List(b31076)),List(List(b31077)))
    val b31076 = CounterIter(x47546, None).name("b31076").ctrl(x47573) // b31076
    val b31077 = Const(true).name("b31077").ctrl(x47573) // b31077
    val x47548 = OpDef(op=FixAdd, inputs=List(b31022, b31076)).name("x47548").ctrl(x47573).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31022,b31076)
    val x47549 = OpDef(op=BitAnd, inputs=List(b31077, b31072)).name("x47549").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(b31077,b31072)
    val x47550 = OpDef(op=BitAnd, inputs=List(b31068, b31024)).name("x47550").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(b31068,b31024)
    val x47551 = OpDef(op=BitAnd, inputs=List(b31025, b30254)).name("x47551").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(b31025,b30254)
    val x47552 = OpDef(op=BitAnd, inputs=List(x47549, x47550)).name("x47552").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(x47549,x47550)
    val x47553 = OpDef(op=BitAnd, inputs=List(x47552, x47551)).name("x47553").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(x47552,x47551)
    val x47554 = LoadBanks(List(x47506_d0_b0), List(b31076, b31071)).name("x47554").ctrl(x47573).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x47506,List(List(b31076, b31071)),List(x47553))
    val x47555 = x47554 // x47555 = VectorApply(x47554,0)
    val x47556 = LoadBanks(List(x45545_d3_b0), List(b31067, x47548)).name("x47556").ctrl(x47573).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45545,List(List(b31067, x47548)),List(x47553))
    val x47557 = x47556 // x47557 = VectorApply(x47556,0)
    val x47558 = OpDef(op=FixMul, inputs=List(x47557, x47555)).name("x47558").ctrl(x47573).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x47557,x47555)
    val x47559 = OpDef(op=FixSub, inputs=List(x47548, Const(512))).name("x47559").ctrl(x47573).srcCtx("CellsPar.scala:205:51") // FixSub(x47548,Const(512))
    val x47560 = LoadBanks(List(x45552_d7_b0), List(b31067, x47559)).name("x47560").ctrl(x47573).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45552,List(List(b31067, x47559)),List(x47553))
    val x47561 = x47560 // x47561 = VectorApply(x47560,0)
    val x47562 = OpDef(op=FixMul, inputs=List(x47561, x47555)).name("x47562").ctrl(x47573).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x47561,x47555)
    val x47563 = OpDef(op=FixLt, inputs=List(x47548, Const(512))).name("x47563").ctrl(x47573).srcCtx("CellsPar.scala:206:38") // FixLt(x47548,Const(512))
    val x47564 = OpDef(op=MuxOp, inputs=List(x47563, x47558, x47562)).name("x47564").ctrl(x47573).srcCtx("CellsPar.scala:206:18") // Mux(x47563,x47558,x47562)
    val x47565 = ReadMem(x47545_d1).name("x47565").ctrl(x47573).srcCtx("CellsPar.scala:207:15") // RegRead(x47545)
    val x47566 = OpDef(op=FixEql, inputs=List(b31076, Const(0))).name("x47566").ctrl(x47573).srcCtx("CellsPar.scala:207:15") // FixEql(b31076,Const(0))
    val x47567 = ReduceAccumOp(op=FixAdd, input=x47564, accum=x47565).name("x47567").ctrl(x47573).srcCtx("CellsPar.scala:207:17") // FixAdd(x47564,x47565)
    val x47568 = OpDef(op=BitAnd, inputs=List(b31072, b31068)).name("x47568").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(b31072,b31068)
    val x47569 = OpDef(op=BitAnd, inputs=List(b31024, b31025)).name("x47569").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(b31024,b31025)
    val x47570 = OpDef(op=BitAnd, inputs=List(x47568, x47569)).name("x47570").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(x47568,x47569)
    val x47571 = OpDef(op=BitAnd, inputs=List(x47570, b30254)).name("x47571").ctrl(x47573).srcCtx("UnrollingBase.scala:28:66") // And(x47570,b30254)
    val x47572_x47545_d0 = WriteMem(x47545_d0, x47567).name("x47572_x47545_d0").ctrl(x47573).srcCtx("CellsPar.scala:207:15") // RegWrite(x47545,x47567,x47571)
    val x47572_x47545_d1 = WriteMem(x47545_d1, x47567).name("x47572_x47545_d1").ctrl(x47573).srcCtx("CellsPar.scala:207:15") // RegWrite(x47545,x47567,x47571)
    val x47605 = UnitController(style=SeqPipe, level=InnerControl).name("x47605").ctrl(x47606).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31072, b31068, b31024, b31025, b30254),Block(Const(())))
    val x47574 = OpDef(op=FixAdd, inputs=List(b31023, b31071)).name("x47574").ctrl(x47605).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31023,b31071)
    val x47575 = ReadMem(x47545_d0).name("x47575").ctrl(x47605).srcCtx("CellsPar.scala:210:28") // RegRead(x47545)
    val x47576 = OpDef(op=FixEql, inputs=List(b31022, Const(0))).name("x47576").ctrl(x47605).srcCtx("CellsPar.scala:210:42") // FixEql(b31022,Const(0))
    val x47577 = OpDef(op=FixAdd, inputs=List(x47574, Const(512))).name("x47577").ctrl(x47605).srcCtx("CellsPar.scala:210:66") // FixAdd(x47574,Const(512))
    val x47578 = OpDef(op=BitAnd, inputs=List(b31072, b31068)).name("x47578").ctrl(x47605).srcCtx("UnrollingBase.scala:28:66") // And(b31072,b31068)
    val x47579 = OpDef(op=BitAnd, inputs=List(b31024, b31025)).name("x47579").ctrl(x47605).srcCtx("UnrollingBase.scala:28:66") // And(b31024,b31025)
    val x47580 = OpDef(op=BitAnd, inputs=List(x47578, x47579)).name("x47580").ctrl(x47605).srcCtx("UnrollingBase.scala:28:66") // And(x47578,x47579)
    val x47581 = OpDef(op=BitAnd, inputs=List(x47580, b30254)).name("x47581").ctrl(x47605).srcCtx("UnrollingBase.scala:28:66") // And(x47580,b30254)
    val x47582 = LoadBanks(List(x45558_d2_b0), List(Const(0), x47577)).name("x47582").ctrl(x47605).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45558,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47577),Const(0),x47581)
    val x47583 = LoadBanks(List(x45555_d1_b0), List(b31067, x47574)).name("x47583").ctrl(x47605).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45555,ArrayBuffer(Const(1), Const(512)),List(b31067, x47574),Const(0),x47581)
    val x47584 = OpDef(op=MuxOp, inputs=List(x47576, x47582, x47583)).name("x47584").ctrl(x47605).srcCtx("CellsPar.scala:210:39") // Mux(x47576,x47582,x47583)
    val x47585 = OpDef(op=FixAdd, inputs=List(x47575, x47584)).name("x47585").ctrl(x47605).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x47575,x47584)
    val x47586 = OpDef(op=FixLeq, inputs=List(Const(1008), b31022)).name("x47586").ctrl(x47605).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31022)
    // x47587 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47587_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x47587_int1").ctrl(x47605).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47587_int2 = OpDef(op=FixSla, inputs=List(x47587_int1, Const(24))).name("x47587_int2").ctrl(x47605).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47587_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x47587_frac1").ctrl(x47605).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47587_frac2 = OpDef(op=FixSla, inputs=List(x47587_frac1, Const(24))).name("x47587_frac2").ctrl(x47605).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47587 = OpDef(op=BitOr, inputs=List(x47587_int2, x47587_frac2)).name("x47587").ctrl(x47605).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x47588 = OpDef(op=FixAdd, inputs=List(x47585, x47587)).name("x47588").ctrl(x47605).srcCtx("CellsPar.scala:218:87") // FixAdd(x47585,x47587)
    val x47589 = x47588 // FixConvert(x47588,TRUE,_8,_24) (Same Type. No op)
    val x47590 = OpDef(op=FixAbs, inputs=List(x47589)).name("x47590").ctrl(x47605).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47589)
    val x47591 = OpDef(op=FixLt, inputs=List(Const(2.5), x47590)).name("x47591").ctrl(x47605).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47590)
    val x47592 = OpDef(op=FixLt, inputs=List(Const(0.5), x47590)).name("x47592").ctrl(x47605).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47590)
    val x47593 = OpDef(op=FixLeq, inputs=List(x47590, Const(2.5))).name("x47593").ctrl(x47605).srcCtx("CellsPar.scala:54:52") // FixLeq(x47590,Const(2.5))
    val x47594 = OpDef(op=BitAnd, inputs=List(x47592, x47593)).name("x47594").ctrl(x47605).srcCtx("CellsPar.scala:54:43:cond2") // And(x47592,x47593)
    val x47595 = OpDef(op=FixSra, inputs=List(x47590, Const(2))).name("x47595").ctrl(x47605).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47590,Const(2))
    val x47596 = OpDef(op=FixAdd, inputs=List(x47595, Const(0.375))).name("x47596").ctrl(x47605).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47595,Const(0.375))
    val x47597 = OpDef(op=MuxOp, inputs=List(x47594, x47596, x47590)).name("x47597").ctrl(x47605).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47594,x47596,x47590)
    val x47598 = OpDef(op=MuxOp, inputs=List(x47591, Const(1.0), x47597)).name("x47598").ctrl(x47605).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47591,Const(1),x47597)
    val x47599 = OpDef(op=FixNeg, inputs=List(x47598)).name("x47599").ctrl(x47605).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47598)
    val x47600 = OpDef(op=FixLt, inputs=List(x47589, Const(0.0))).name("x47600").ctrl(x47605).srcCtx("CellsPar.scala:63:22") // FixLt(x47589,Const(0))
    val x47601 = OpDef(op=MuxOp, inputs=List(x47600, x47599, x47598)).name("x47601").ctrl(x47605).srcCtx("CellsPar.scala:63:17:re") // Mux(x47600,x47599,x47598)
    val x47602 = x47601 // FixConvert(x47601,TRUE,_8,_24) (Same Type. No op)
    val x47603 = OpDef(op=MuxOp, inputs=List(x47586, x47602, x47585)).name("x47603").ctrl(x47605).srcCtx("CellsPar.scala:218:43") // Mux(x47586,x47602,x47585)
    val x47604 = StoreBanks(List(x45555_d0_b0, x45555_d1_b0), List(b31067, x47574), x47603).name("x47604").ctrl(x47605).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45555,ArrayBuffer(Const(1), Const(512)),List(b31067, x47574),Const(0),x47603,x47581)
    val x47609 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47609").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47610 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x47610").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x47611 = CounterChain(List(x47610,x47609)).name("x47611").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x47610, x47609))
    val x47717 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47611).name("x47717").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x47611,Block(Const(())),List(List(b31142), List(b31143)),List(List(b31144), List(b31145)))
    val b31142 = CounterIter(x47610, Some(0)).name("b31142").ctrl(x47717) // b31142
    val b31144 = Const(true).name("b31144").ctrl(x47717) // b31144
    val b31143 = CounterIter(x47609, Some(0)).name("b31143").ctrl(x47717) // b31143
    val b31145 = Const(true).name("b31145").ctrl(x47717) // b31145
    val x47612_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47612_d0_b0").ctrl(x47717).srcCtx("CellsPar.scala:191:33:tileKernel") // x47612 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47612_d0_b0) = false
    bufferDepthOf(x47612_d0_b0) = 2
    val x47615 = UnitController(style=SeqPipe, level=InnerControl).name("x47615").ctrl(x47717).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31144, b31145, b30254),Block(Const(())))
    val x47613 = OpDef(op=FixAdd, inputs=List(b31142, Const(16))).name("x47613").ctrl(x47615).srcCtx("CellsPar.scala:192:36") // FixAdd(b31142,Const(16))
    val x47614 = OpDef(op=FixAdd, inputs=List(b31143, Const(16))).name("x47614").ctrl(x47615).srcCtx("CellsPar.scala:192:45") // FixAdd(b31143,Const(16))
    val x47616 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47616").ctrl(x47717).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47617 = CounterChain(List(x47616)).name("x47617").ctrl(x47717).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47616))
    val x47646 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47617).name("x47646").ctrl(x47717).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31144, b31145, b30254),x47617,Block(Const(())),List(List(b31152)),List(List(b31153)))
    val b31152 = CounterIter(x47616, Some(0)).name("b31152").ctrl(x47646) // b31152
    val b31153 = Const(true).name("b31153").ctrl(x47646) // b31153
    val b48504 = StreamOut(field="offset").name("b48504").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // x47618 = StreamOutNew(BurstCmdBus)
    isAccum(b48504) = false
    bufferDepthOf(b48504) = 1
    val b48505 = StreamOut(field="size").name("b48505").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // x47618 = StreamOutNew(BurstCmdBus)
    isAccum(b48505) = false
    bufferDepthOf(b48505) = 1
    val x47619 = StreamIn(field="data").name("x47619").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // x47619 = StreamInNew(BurstDataBus())
    isAccum(x47619) = false
    bufferDepthOf(x47619) = 1
    val x47634 = UnitController(style=SeqPipe, level=InnerControl).name("x47634").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31153, b31144, b31145, b30254),Block(x47633))
    val x47620 = OpDef(op=FixAdd, inputs=List(b31142, b31152)).name("x47620").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // FixAdd(b31142,b31152)
    val x47621 = x47620 // FixConvert(x47620,TRUE,_32,_0) (Same Type. No op)
    val x47622 = OpDef(op=FixSla, inputs=List(x47621, Const(9))).name("x47622").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // FixLsh(x47621,Const(9))
    val x47623 = b31143 // FixConvert(b31143,TRUE,_32,_0) (Same Type. No op)
    val x47624 = OpDef(op=FixAdd, inputs=List(x47622, x47623)).name("x47624").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // FixAdd(x47622,x47623)
    val x47625 = OpDef(op=FixSla, inputs=List(x47624, Const(2))).name("x47625").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // FixLsh(x47624,Const(2))
    val x47626 = x47625 // FixConvert(x47625,TRUE,_64,_0)
    val x47627 = DramAddress(x45407).name("x47627").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45407)
    val x47629_x47628 = OpDef(op=FixAdd, inputs=List(x47626, x47627)).name("x47629_x47628").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // FixAdd(x47626,x47627)
    // x47629 = SimpleStruct(ArrayBuffer((offset,x47628), (size,Const(64)), (isLoad,Const(true))))
    val x47630 = OpDef(op=BitAnd, inputs=List(b31153, b31144)).name("x47630").ctrl(x47634).srcCtx("UnrollingBase.scala:28:66") // And(b31153,b31144)
    val x47631 = OpDef(op=BitAnd, inputs=List(b31145, b30254)).name("x47631").ctrl(x47634).srcCtx("UnrollingBase.scala:28:66") // And(b31145,b30254)
    val x47632 = OpDef(op=BitAnd, inputs=List(x47630, x47631)).name("x47632").ctrl(x47634).srcCtx("UnrollingBase.scala:28:66") // And(x47630,x47631)
    val x47633_b48506_b48504 = WriteMem(b48504, x47629_x47628).name("x47633_b48506_b48504").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47618,x47629,x47632)
    val x47633_b48507_b48505 = WriteMem(b48505, Const(64)).name("x47633_b48507_b48505").ctrl(x47634).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47618,x47629,x47632)
    val x47635 = FringeDenseLoad(dram=List(x45407), cmdStream=List(b48504, b48505), dataStream=List(x47619)).name("x47635").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45407,x47618,x47619)
    val x47636 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47636").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47637 = CounterChain(List(x47636)).name("x47637").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47636))
    val x47645 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47637).name("x47645").ctrl(x47646).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31153, b31144, b31145, b30254),x47637,Block(Const(())),List(List(b31174)),List(List(b31175)))
    val b31174 = CounterIter(x47636, None).name("b31174").ctrl(x47645) // b31174
    val b31175 = Const(true).name("b31175").ctrl(x47645) // b31175
    val x47638 = OpDef(op=BitAnd, inputs=List(b31175, b31153)).name("x47638").ctrl(x47645).srcCtx("UnrollingBase.scala:28:66") // And(b31175,b31153)
    val x47639 = OpDef(op=BitAnd, inputs=List(b31144, b31145)).name("x47639").ctrl(x47645).srcCtx("UnrollingBase.scala:28:66") // And(b31144,b31145)
    val x47640 = OpDef(op=BitAnd, inputs=List(x47638, x47639)).name("x47640").ctrl(x47645).srcCtx("UnrollingBase.scala:28:66") // And(x47638,x47639)
    val x47641 = OpDef(op=BitAnd, inputs=List(x47640, b30254)).name("x47641").ctrl(x47645).srcCtx("UnrollingBase.scala:28:66") // And(x47640,b30254)
    val x47642_x47642 = ReadMem(x47619).name("x47642_x47642").ctrl(x47645).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x47619,List(x47641))
    val x47643_x47643 = x47642_x47642 // x47643 = VectorApply(x47642,0)
    val x47644 = StoreBanks(List(x47612_d0_b0), List(b31152, b31174), x47643_x47643).name("x47644").ctrl(x47645).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x47612,List(List(b31152, b31174)),List(x47643),List(x47641))
    val x47647 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47647").ctrl(x47717).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47648 = CounterChain(List(x47647)).name("x47648").ctrl(x47717).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x47647))
    val x47716 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47648).name("x47716").ctrl(x47717).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31144, b31145, b30254),x47648,Block(Const(())),List(List(b31187)),List(List(b31188)))
    val b31187 = CounterIter(x47647, Some(0)).name("b31187").ctrl(x47716) // b31187
    val b31188 = Const(true).name("b31188").ctrl(x47716) // b31188
    val x47649 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47649").ctrl(x47716).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47650 = CounterChain(List(x47649)).name("x47650").ctrl(x47716).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x47649))
    val x47715 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47650).name("x47715").ctrl(x47716).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31188, b31144, b31145, b30254),x47650,Block(Const(())),List(List(b31191)),List(List(b31192)))
    val b31191 = CounterIter(x47649, Some(0)).name("b31191").ctrl(x47715) // b31191
    val b31192 = Const(true).name("b31192").ctrl(x47715) // b31192
    val x47651_d0 = Reg(init=Some(0.0)).name("x47651_d0").ctrl(x47715).srcCtx("CellsPar.scala:195:34:prod") // x47651 = RegNew(Const(0))
    isAccum(x47651_d0) = false
    bufferDepthOf(x47651_d0) = 2
    val x47651_d1 = Reg(init=Some(0.0)).name("x47651_d1").ctrl(x47715).srcCtx("CellsPar.scala:195:34:prod") // x47651 = RegNew(Const(0))
    def split4 = {
    isAccum(x47651_d1) = true
    bufferDepthOf(x47651_d1) = 1
    val x47652 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47652").ctrl(x47715).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47653 = CounterChain(List(x47652)).name("x47653").ctrl(x47715).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x47652))
    val x47679 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47653).name("x47679").ctrl(x47715).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31192, b31188, b31144, b31145, b30254),x47653,x47651,Block((x47651) => Const(())),List(List(b31196)),List(List(b31197)))
    val b31196 = CounterIter(x47652, None).name("b31196").ctrl(x47679) // b31196
    val b31197 = Const(true).name("b31197").ctrl(x47679) // b31197
    val x47654 = OpDef(op=FixAdd, inputs=List(b31142, b31196)).name("x47654").ctrl(x47679).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31142,b31196)
    val x47655 = OpDef(op=BitAnd, inputs=List(b31197, b31192)).name("x47655").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(b31197,b31192)
    val x47656 = OpDef(op=BitAnd, inputs=List(b31188, b31144)).name("x47656").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(b31188,b31144)
    val x47657 = OpDef(op=BitAnd, inputs=List(b31145, b30254)).name("x47657").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(b31145,b30254)
    val x47658 = OpDef(op=BitAnd, inputs=List(x47655, x47656)).name("x47658").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(x47655,x47656)
    val x47659 = OpDef(op=BitAnd, inputs=List(x47658, x47657)).name("x47659").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(x47658,x47657)
    val x47660 = LoadBanks(List(x47612_d0_b0), List(b31196, b31191)).name("x47660").ctrl(x47679).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x47612,List(List(b31196, b31191)),List(x47659))
    val x47661 = x47660 // x47661 = VectorApply(x47660,0)
    val x47662 = LoadBanks(List(x45545_d2_b0), List(b31187, x47654)).name("x47662").ctrl(x47679).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45545,List(List(b31187, x47654)),List(x47659))
    val x47663 = x47662 // x47663 = VectorApply(x47662,0)
    val x47664 = OpDef(op=FixMul, inputs=List(x47663, x47661)).name("x47664").ctrl(x47679).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x47663,x47661)
    val x47665 = OpDef(op=FixSub, inputs=List(x47654, Const(512))).name("x47665").ctrl(x47679).srcCtx("CellsPar.scala:205:51") // FixSub(x47654,Const(512))
    val x47666 = LoadBanks(List(x45552_d6_b0), List(b31187, x47665)).name("x47666").ctrl(x47679).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45552,List(List(b31187, x47665)),List(x47659))
    val x47667 = x47666 // x47667 = VectorApply(x47666,0)
    val x47668 = OpDef(op=FixMul, inputs=List(x47667, x47661)).name("x47668").ctrl(x47679).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x47667,x47661)
    val x47669 = OpDef(op=FixLt, inputs=List(x47654, Const(512))).name("x47669").ctrl(x47679).srcCtx("CellsPar.scala:206:38") // FixLt(x47654,Const(512))
    val x47670 = OpDef(op=MuxOp, inputs=List(x47669, x47664, x47668)).name("x47670").ctrl(x47679).srcCtx("CellsPar.scala:206:18") // Mux(x47669,x47664,x47668)
    val x47671 = ReadMem(x47651_d1).name("x47671").ctrl(x47679).srcCtx("CellsPar.scala:207:15") // RegRead(x47651)
    val x47672 = OpDef(op=FixEql, inputs=List(b31196, Const(0))).name("x47672").ctrl(x47679).srcCtx("CellsPar.scala:207:15") // FixEql(b31196,Const(0))
    val x47673 = ReduceAccumOp(op=FixAdd, input=x47670, accum=x47671).name("x47673").ctrl(x47679).srcCtx("CellsPar.scala:207:17") // FixAdd(x47670,x47671)
    val x47674 = OpDef(op=BitAnd, inputs=List(b31192, b31188)).name("x47674").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(b31192,b31188)
    val x47675 = OpDef(op=BitAnd, inputs=List(b31144, b31145)).name("x47675").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(b31144,b31145)
    val x47676 = OpDef(op=BitAnd, inputs=List(x47674, x47675)).name("x47676").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(x47674,x47675)
    val x47677 = OpDef(op=BitAnd, inputs=List(x47676, b30254)).name("x47677").ctrl(x47679).srcCtx("UnrollingBase.scala:28:66") // And(x47676,b30254)
    val x47678_x47651_d0 = WriteMem(x47651_d0, x47673).name("x47678_x47651_d0").ctrl(x47679).srcCtx("CellsPar.scala:207:15") // RegWrite(x47651,x47673,x47677)
    val x47678_x47651_d1 = WriteMem(x47651_d1, x47673).name("x47678_x47651_d1").ctrl(x47679).srcCtx("CellsPar.scala:207:15") // RegWrite(x47651,x47673,x47677)
    val x47714 = UnitController(style=SeqPipe, level=InnerControl).name("x47714").ctrl(x47715).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31192, b31188, b31144, b31145, b30254),Block(Const(())))
    val x47680 = OpDef(op=FixAdd, inputs=List(b31143, b31191)).name("x47680").ctrl(x47714).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31143,b31191)
    val x47681 = ReadMem(x47651_d0).name("x47681").ctrl(x47714).srcCtx("CellsPar.scala:210:28") // RegRead(x47651)
    val x47682 = OpDef(op=FixEql, inputs=List(b31142, Const(0))).name("x47682").ctrl(x47714).srcCtx("CellsPar.scala:210:42") // FixEql(b31142,Const(0))
    val x47683 = OpDef(op=FixAdd, inputs=List(x47680, Const(1024))).name("x47683").ctrl(x47714).srcCtx("CellsPar.scala:210:66") // FixAdd(x47680,Const(1024))
    val x47684 = OpDef(op=BitAnd, inputs=List(b31192, b31188)).name("x47684").ctrl(x47714).srcCtx("UnrollingBase.scala:28:66") // And(b31192,b31188)
    val x47685 = OpDef(op=BitAnd, inputs=List(b31144, b31145)).name("x47685").ctrl(x47714).srcCtx("UnrollingBase.scala:28:66") // And(b31144,b31145)
    val x47686 = OpDef(op=BitAnd, inputs=List(x47684, x47685)).name("x47686").ctrl(x47714).srcCtx("UnrollingBase.scala:28:66") // And(x47684,x47685)
    val x47687 = OpDef(op=BitAnd, inputs=List(x47686, b30254)).name("x47687").ctrl(x47714).srcCtx("UnrollingBase.scala:28:66") // And(x47686,b30254)
    val x47688 = LoadBanks(List(x45558_d1_b0), List(Const(0), x47683)).name("x47688").ctrl(x47714).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45558,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47683),Const(0),x47687)
    val x47689 = LoadBanks(List(x45556_d1_b0), List(b31187, x47680)).name("x47689").ctrl(x47714).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45556,ArrayBuffer(Const(1), Const(512)),List(b31187, x47680),Const(0),x47687)
    val x47690 = OpDef(op=MuxOp, inputs=List(x47682, x47688, x47689)).name("x47690").ctrl(x47714).srcCtx("CellsPar.scala:210:39") // Mux(x47682,x47688,x47689)
    val x47691 = OpDef(op=FixAdd, inputs=List(x47681, x47690)).name("x47691").ctrl(x47714).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x47681,x47690)
    val x47692 = OpDef(op=FixLeq, inputs=List(Const(1008), b31142)).name("x47692").ctrl(x47714).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31142)
    // x47693 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47693_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x47693_int1").ctrl(x47714).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x47693_int2 = OpDef(op=FixSla, inputs=List(x47693_int1, Const(24))).name("x47693_int2").ctrl(x47714).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x47693_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x47693_frac1").ctrl(x47714).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x47693_frac2 = OpDef(op=FixSla, inputs=List(x47693_frac1, Const(24))).name("x47693_frac2").ctrl(x47714).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x47693 = OpDef(op=BitOr, inputs=List(x47693_int2, x47693_frac2)).name("x47693").ctrl(x47714).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x47694 = OpDef(op=FixAdd, inputs=List(x47691, x47693)).name("x47694").ctrl(x47714).srcCtx("CellsPar.scala:218:87") // FixAdd(x47691,x47693)
    val x47695 = OpDef(op=FixSra, inputs=List(x47694, Const(1))).name("x47695").ctrl(x47714).srcCtx("CellsPar.scala:29:22") // FixRsh(x47694,Const(1))
    val x47696 = x47695 // FixConvert(x47695,TRUE,_8,_24) (Same Type. No op)
    val x47697 = OpDef(op=FixAbs, inputs=List(x47696)).name("x47697").ctrl(x47714).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47696)
    val x47698 = OpDef(op=FixLt, inputs=List(Const(2.5), x47697)).name("x47698").ctrl(x47714).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47697)
    val x47699 = OpDef(op=FixLt, inputs=List(Const(0.5), x47697)).name("x47699").ctrl(x47714).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47697)
    val x47700 = OpDef(op=FixLeq, inputs=List(x47697, Const(2.5))).name("x47700").ctrl(x47714).srcCtx("CellsPar.scala:54:52") // FixLeq(x47697,Const(2.5))
    val x47701 = OpDef(op=BitAnd, inputs=List(x47699, x47700)).name("x47701").ctrl(x47714).srcCtx("CellsPar.scala:54:43:cond2") // And(x47699,x47700)
    val x47702 = OpDef(op=FixSra, inputs=List(x47697, Const(2))).name("x47702").ctrl(x47714).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47697,Const(2))
    val x47703 = OpDef(op=FixAdd, inputs=List(x47702, Const(0.375))).name("x47703").ctrl(x47714).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47702,Const(0.375))
    val x47704 = OpDef(op=MuxOp, inputs=List(x47701, x47703, x47697)).name("x47704").ctrl(x47714).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47701,x47703,x47697)
    val x47705 = OpDef(op=MuxOp, inputs=List(x47698, Const(1.0), x47704)).name("x47705").ctrl(x47714).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47698,Const(1),x47704)
    val x47706 = OpDef(op=FixNeg, inputs=List(x47705)).name("x47706").ctrl(x47714).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47705)
    val x47707 = OpDef(op=FixLt, inputs=List(x47696, Const(0.0))).name("x47707").ctrl(x47714).srcCtx("CellsPar.scala:63:22") // FixLt(x47696,Const(0))
    val x47708 = OpDef(op=MuxOp, inputs=List(x47707, x47706, x47705)).name("x47708").ctrl(x47714).srcCtx("CellsPar.scala:63:17:re") // Mux(x47707,x47706,x47705)
    val x47709 = x47708 // FixConvert(x47708,TRUE,_8,_24) (Same Type. No op)
    val x47710 = OpDef(op=FixAdd, inputs=List(x47709, Const(1.0))).name("x47710").ctrl(x47714).srcCtx("CellsPar.scala:29:33") // FixAdd(x47709,Const(1))
    val x47711 = OpDef(op=FixSra, inputs=List(x47710, Const(1))).name("x47711").ctrl(x47714).srcCtx("CellsPar.scala:29:44") // FixRsh(x47710,Const(1))
    val x47712 = OpDef(op=MuxOp, inputs=List(x47692, x47711, x47691)).name("x47712").ctrl(x47714).srcCtx("CellsPar.scala:218:43") // Mux(x47692,x47711,x47691)
    val x47713 = StoreBanks(List(x45556_d0_b0, x45556_d1_b0), List(b31187, x47680), x47712).name("x47713").ctrl(x47714).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45556,ArrayBuffer(Const(1), Const(512)),List(b31187, x47680),Const(0),x47712,x47687)
    val x47718 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47718").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47719 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x47719").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x47720 = CounterChain(List(x47719,x47718)).name("x47720").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x47719, x47718))
    val x47826 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47720).name("x47826").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x47720,Block(Const(())),List(List(b31265), List(b31266)),List(List(b31267), List(b31268)))
    val b31265 = CounterIter(x47719, Some(0)).name("b31265").ctrl(x47826) // b31265
    val b31267 = Const(true).name("b31267").ctrl(x47826) // b31267
    val b31266 = CounterIter(x47718, Some(0)).name("b31266").ctrl(x47826) // b31266
    val b31268 = Const(true).name("b31268").ctrl(x47826) // b31268
    val x47721_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47721_d0_b0").ctrl(x47826).srcCtx("CellsPar.scala:191:33:tileKernel") // x47721 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47721_d0_b0) = false
    bufferDepthOf(x47721_d0_b0) = 2
    val x47724 = UnitController(style=SeqPipe, level=InnerControl).name("x47724").ctrl(x47826).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31267, b31268, b30254),Block(Const(())))
    val x47722 = OpDef(op=FixAdd, inputs=List(b31265, Const(16))).name("x47722").ctrl(x47724).srcCtx("CellsPar.scala:192:36") // FixAdd(b31265,Const(16))
    val x47723 = OpDef(op=FixAdd, inputs=List(b31266, Const(16))).name("x47723").ctrl(x47724).srcCtx("CellsPar.scala:192:45") // FixAdd(b31266,Const(16))
    val x47725 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47725").ctrl(x47826).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47726 = CounterChain(List(x47725)).name("x47726").ctrl(x47826).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47725))
    val x47755 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47726).name("x47755").ctrl(x47826).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31267, b31268, b30254),x47726,Block(Const(())),List(List(b31275)),List(List(b31276)))
    val b31275 = CounterIter(x47725, Some(0)).name("b31275").ctrl(x47755) // b31275
    val b31276 = Const(true).name("b31276").ctrl(x47755) // b31276
    val b48508 = StreamOut(field="offset").name("b48508").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // x47727 = StreamOutNew(BurstCmdBus)
    isAccum(b48508) = false
    bufferDepthOf(b48508) = 1
    val b48509 = StreamOut(field="size").name("b48509").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // x47727 = StreamOutNew(BurstCmdBus)
    isAccum(b48509) = false
    bufferDepthOf(b48509) = 1
    val x47728 = StreamIn(field="data").name("x47728").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // x47728 = StreamInNew(BurstDataBus())
    isAccum(x47728) = false
    bufferDepthOf(x47728) = 1
    val x47743 = UnitController(style=SeqPipe, level=InnerControl).name("x47743").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31276, b31267, b31268, b30254),Block(x47742))
    val x47729 = OpDef(op=FixAdd, inputs=List(b31265, b31275)).name("x47729").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // FixAdd(b31265,b31275)
    val x47730 = x47729 // FixConvert(x47729,TRUE,_32,_0) (Same Type. No op)
    val x47731 = OpDef(op=FixSla, inputs=List(x47730, Const(9))).name("x47731").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // FixLsh(x47730,Const(9))
    val x47732 = b31266 // FixConvert(b31266,TRUE,_32,_0) (Same Type. No op)
    val x47733 = OpDef(op=FixAdd, inputs=List(x47731, x47732)).name("x47733").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // FixAdd(x47731,x47732)
    val x47734 = OpDef(op=FixSla, inputs=List(x47733, Const(2))).name("x47734").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // FixLsh(x47733,Const(2))
    val x47735 = x47734 // FixConvert(x47734,TRUE,_64,_0)
    val x47736 = DramAddress(x45408).name("x47736").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45408)
    val x47738_x47737 = OpDef(op=FixAdd, inputs=List(x47735, x47736)).name("x47738_x47737").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // FixAdd(x47735,x47736)
    // x47738 = SimpleStruct(ArrayBuffer((offset,x47737), (size,Const(64)), (isLoad,Const(true))))
    val x47739 = OpDef(op=BitAnd, inputs=List(b31276, b31267)).name("x47739").ctrl(x47743).srcCtx("UnrollingBase.scala:28:66") // And(b31276,b31267)
    val x47740 = OpDef(op=BitAnd, inputs=List(b31268, b30254)).name("x47740").ctrl(x47743).srcCtx("UnrollingBase.scala:28:66") // And(b31268,b30254)
    val x47741 = OpDef(op=BitAnd, inputs=List(x47739, x47740)).name("x47741").ctrl(x47743).srcCtx("UnrollingBase.scala:28:66") // And(x47739,x47740)
    val x47742_b48510_b48508 = WriteMem(b48508, x47738_x47737).name("x47742_b48510_b48508").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47727,x47738,x47741)
    val x47742_b48511_b48509 = WriteMem(b48509, Const(64)).name("x47742_b48511_b48509").ctrl(x47743).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47727,x47738,x47741)
    val x47744 = FringeDenseLoad(dram=List(x45408), cmdStream=List(b48508, b48509), dataStream=List(x47728)).name("x47744").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45408,x47727,x47728)
    val x47745 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47745").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47746 = CounterChain(List(x47745)).name("x47746").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47745))
    val x47754 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47746).name("x47754").ctrl(x47755).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31276, b31267, b31268, b30254),x47746,Block(Const(())),List(List(b31297)),List(List(b31298)))
    val b31297 = CounterIter(x47745, None).name("b31297").ctrl(x47754) // b31297
    val b31298 = Const(true).name("b31298").ctrl(x47754) // b31298
    val x47747 = OpDef(op=BitAnd, inputs=List(b31298, b31276)).name("x47747").ctrl(x47754).srcCtx("UnrollingBase.scala:28:66") // And(b31298,b31276)
    val x47748 = OpDef(op=BitAnd, inputs=List(b31267, b31268)).name("x47748").ctrl(x47754).srcCtx("UnrollingBase.scala:28:66") // And(b31267,b31268)
    val x47749 = OpDef(op=BitAnd, inputs=List(x47747, x47748)).name("x47749").ctrl(x47754).srcCtx("UnrollingBase.scala:28:66") // And(x47747,x47748)
    val x47750 = OpDef(op=BitAnd, inputs=List(x47749, b30254)).name("x47750").ctrl(x47754).srcCtx("UnrollingBase.scala:28:66") // And(x47749,b30254)
    val x47751_x47751 = ReadMem(x47728).name("x47751_x47751").ctrl(x47754).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x47728,List(x47750))
    val x47752_x47752 = x47751_x47751 // x47752 = VectorApply(x47751,0)
    val x47753 = StoreBanks(List(x47721_d0_b0), List(b31275, b31297), x47752_x47752).name("x47753").ctrl(x47754).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x47721,List(List(b31275, b31297)),List(x47752),List(x47750))
    val x47756 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47756").ctrl(x47826).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47757 = CounterChain(List(x47756)).name("x47757").ctrl(x47826).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x47756))
    val x47825 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47757).name("x47825").ctrl(x47826).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31267, b31268, b30254),x47757,Block(Const(())),List(List(b31310)),List(List(b31311)))
    val b31310 = CounterIter(x47756, Some(0)).name("b31310").ctrl(x47825) // b31310
    val b31311 = Const(true).name("b31311").ctrl(x47825) // b31311
    val x47758 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47758").ctrl(x47825).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47759 = CounterChain(List(x47758)).name("x47759").ctrl(x47825).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x47758))
    val x47824 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47759).name("x47824").ctrl(x47825).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31311, b31267, b31268, b30254),x47759,Block(Const(())),List(List(b31314)),List(List(b31315)))
    val b31314 = CounterIter(x47758, Some(0)).name("b31314").ctrl(x47824) // b31314
    val b31315 = Const(true).name("b31315").ctrl(x47824) // b31315
    val x47760_d0 = Reg(init=Some(0.0)).name("x47760_d0").ctrl(x47824).srcCtx("CellsPar.scala:195:34:prod") // x47760 = RegNew(Const(0))
    isAccum(x47760_d0) = false
    bufferDepthOf(x47760_d0) = 2
    val x47760_d1 = Reg(init=Some(0.0)).name("x47760_d1").ctrl(x47824).srcCtx("CellsPar.scala:195:34:prod") // x47760 = RegNew(Const(0))
    isAccum(x47760_d1) = true
    bufferDepthOf(x47760_d1) = 1
    val x47761 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47761").ctrl(x47824).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47762 = CounterChain(List(x47761)).name("x47762").ctrl(x47824).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x47761))
    val x47788 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47762).name("x47788").ctrl(x47824).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31315, b31311, b31267, b31268, b30254),x47762,x47760,Block((x47760) => Const(())),List(List(b31319)),List(List(b31320)))
    val b31319 = CounterIter(x47761, None).name("b31319").ctrl(x47788) // b31319
    val b31320 = Const(true).name("b31320").ctrl(x47788) // b31320
    val x47763 = OpDef(op=FixAdd, inputs=List(b31265, b31319)).name("x47763").ctrl(x47788).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31265,b31319)
    val x47764 = OpDef(op=BitAnd, inputs=List(b31320, b31315)).name("x47764").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(b31320,b31315)
    val x47765 = OpDef(op=BitAnd, inputs=List(b31311, b31267)).name("x47765").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(b31311,b31267)
    val x47766 = OpDef(op=BitAnd, inputs=List(b31268, b30254)).name("x47766").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(b31268,b30254)
    val x47767 = OpDef(op=BitAnd, inputs=List(x47764, x47765)).name("x47767").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(x47764,x47765)
    val x47768 = OpDef(op=BitAnd, inputs=List(x47767, x47766)).name("x47768").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(x47767,x47766)
    val x47769 = LoadBanks(List(x47721_d0_b0), List(b31319, b31314)).name("x47769").ctrl(x47788).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x47721,List(List(b31319, b31314)),List(x47768))
    val x47770 = x47769 // x47770 = VectorApply(x47769,0)
    val x47771 = LoadBanks(List(x45545_d1_b0), List(b31310, x47763)).name("x47771").ctrl(x47788).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45545,List(List(b31310, x47763)),List(x47768))
    val x47772 = x47771 // x47772 = VectorApply(x47771,0)
    val x47773 = OpDef(op=FixMul, inputs=List(x47772, x47770)).name("x47773").ctrl(x47788).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x47772,x47770)
    val x47774 = OpDef(op=FixSub, inputs=List(x47763, Const(512))).name("x47774").ctrl(x47788).srcCtx("CellsPar.scala:205:51") // FixSub(x47763,Const(512))
    val x47775 = LoadBanks(List(x45552_d5_b0), List(b31310, x47774)).name("x47775").ctrl(x47788).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45552,List(List(b31310, x47774)),List(x47768))
    val x47776 = x47775 // x47776 = VectorApply(x47775,0)
    val x47777 = OpDef(op=FixMul, inputs=List(x47776, x47770)).name("x47777").ctrl(x47788).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x47776,x47770)
    val x47778 = OpDef(op=FixLt, inputs=List(x47763, Const(512))).name("x47778").ctrl(x47788).srcCtx("CellsPar.scala:206:38") // FixLt(x47763,Const(512))
    val x47779 = OpDef(op=MuxOp, inputs=List(x47778, x47773, x47777)).name("x47779").ctrl(x47788).srcCtx("CellsPar.scala:206:18") // Mux(x47778,x47773,x47777)
    val x47780 = ReadMem(x47760_d1).name("x47780").ctrl(x47788).srcCtx("CellsPar.scala:207:15") // RegRead(x47760)
    val x47781 = OpDef(op=FixEql, inputs=List(b31319, Const(0))).name("x47781").ctrl(x47788).srcCtx("CellsPar.scala:207:15") // FixEql(b31319,Const(0))
    val x47782 = ReduceAccumOp(op=FixAdd, input=x47779, accum=x47780).name("x47782").ctrl(x47788).srcCtx("CellsPar.scala:207:17") // FixAdd(x47779,x47780)
    val x47783 = OpDef(op=BitAnd, inputs=List(b31315, b31311)).name("x47783").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(b31315,b31311)
    val x47784 = OpDef(op=BitAnd, inputs=List(b31267, b31268)).name("x47784").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(b31267,b31268)
    val x47785 = OpDef(op=BitAnd, inputs=List(x47783, x47784)).name("x47785").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(x47783,x47784)
    val x47786 = OpDef(op=BitAnd, inputs=List(x47785, b30254)).name("x47786").ctrl(x47788).srcCtx("UnrollingBase.scala:28:66") // And(x47785,b30254)
    val x47787_x47760_d0 = WriteMem(x47760_d0, x47782).name("x47787_x47760_d0").ctrl(x47788).srcCtx("CellsPar.scala:207:15") // RegWrite(x47760,x47782,x47786)
    val x47787_x47760_d1 = WriteMem(x47760_d1, x47782).name("x47787_x47760_d1").ctrl(x47788).srcCtx("CellsPar.scala:207:15") // RegWrite(x47760,x47782,x47786)
    val x47823 = UnitController(style=SeqPipe, level=InnerControl).name("x47823").ctrl(x47824).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31315, b31311, b31267, b31268, b30254),Block(Const(())))
    val x47789 = OpDef(op=FixAdd, inputs=List(b31266, b31314)).name("x47789").ctrl(x47823).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31266,b31314)
    val x47790 = ReadMem(x47760_d0).name("x47790").ctrl(x47823).srcCtx("CellsPar.scala:210:28") // RegRead(x47760)
    val x47791 = OpDef(op=FixEql, inputs=List(b31265, Const(0))).name("x47791").ctrl(x47823).srcCtx("CellsPar.scala:210:42") // FixEql(b31265,Const(0))
    val x47792 = OpDef(op=FixAdd, inputs=List(x47789, Const(1536))).name("x47792").ctrl(x47823).srcCtx("CellsPar.scala:210:66") // FixAdd(x47789,Const(1536))
    val x47793 = OpDef(op=BitAnd, inputs=List(b31315, b31311)).name("x47793").ctrl(x47823).srcCtx("UnrollingBase.scala:28:66") // And(b31315,b31311)
    val x47794 = OpDef(op=BitAnd, inputs=List(b31267, b31268)).name("x47794").ctrl(x47823).srcCtx("UnrollingBase.scala:28:66") // And(b31267,b31268)
    val x47795 = OpDef(op=BitAnd, inputs=List(x47793, x47794)).name("x47795").ctrl(x47823).srcCtx("UnrollingBase.scala:28:66") // And(x47793,x47794)
    val x47796 = OpDef(op=BitAnd, inputs=List(x47795, b30254)).name("x47796").ctrl(x47823).srcCtx("UnrollingBase.scala:28:66") // And(x47795,b30254)
    val x47797 = LoadBanks(List(x45558_d0_b0), List(Const(0), x47792)).name("x47797").ctrl(x47823).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45558,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47792),Const(0),x47796)
    val x47798 = LoadBanks(List(x45557_d1_b0), List(b31310, x47789)).name("x47798").ctrl(x47823).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45557,ArrayBuffer(Const(1), Const(512)),List(b31310, x47789),Const(0),x47796)
    val x47799 = OpDef(op=MuxOp, inputs=List(x47791, x47797, x47798)).name("x47799").ctrl(x47823).srcCtx("CellsPar.scala:210:39") // Mux(x47791,x47797,x47798)
    val x47800 = OpDef(op=FixAdd, inputs=List(x47790, x47799)).name("x47800").ctrl(x47823).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x47790,x47799)
    val x47801 = OpDef(op=FixLeq, inputs=List(Const(1008), b31265)).name("x47801").ctrl(x47823).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31265)
    // x47802 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47802_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x47802_int1").ctrl(x47823).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47802_int2 = OpDef(op=FixSla, inputs=List(x47802_int1, Const(24))).name("x47802_int2").ctrl(x47823).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47802_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x47802_frac1").ctrl(x47823).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47802_frac2 = OpDef(op=FixSla, inputs=List(x47802_frac1, Const(24))).name("x47802_frac2").ctrl(x47823).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47802 = OpDef(op=BitOr, inputs=List(x47802_int2, x47802_frac2)).name("x47802").ctrl(x47823).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x47803 = OpDef(op=FixAdd, inputs=List(x47800, x47802)).name("x47803").ctrl(x47823).srcCtx("CellsPar.scala:218:87") // FixAdd(x47800,x47802)
    val x47804 = OpDef(op=FixSra, inputs=List(x47803, Const(1))).name("x47804").ctrl(x47823).srcCtx("CellsPar.scala:29:22") // FixRsh(x47803,Const(1))
    val x47805 = x47804 // FixConvert(x47804,TRUE,_8,_24) (Same Type. No op)
    val x47806 = OpDef(op=FixAbs, inputs=List(x47805)).name("x47806").ctrl(x47823).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47805)
    val x47807 = OpDef(op=FixLt, inputs=List(Const(2.5), x47806)).name("x47807").ctrl(x47823).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47806)
    val x47808 = OpDef(op=FixLt, inputs=List(Const(0.5), x47806)).name("x47808").ctrl(x47823).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47806)
    val x47809 = OpDef(op=FixLeq, inputs=List(x47806, Const(2.5))).name("x47809").ctrl(x47823).srcCtx("CellsPar.scala:54:52") // FixLeq(x47806,Const(2.5))
    val x47810 = OpDef(op=BitAnd, inputs=List(x47808, x47809)).name("x47810").ctrl(x47823).srcCtx("CellsPar.scala:54:43:cond2") // And(x47808,x47809)
    val x47811 = OpDef(op=FixSra, inputs=List(x47806, Const(2))).name("x47811").ctrl(x47823).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47806,Const(2))
    val x47812 = OpDef(op=FixAdd, inputs=List(x47811, Const(0.375))).name("x47812").ctrl(x47823).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47811,Const(0.375))
    val x47813 = OpDef(op=MuxOp, inputs=List(x47810, x47812, x47806)).name("x47813").ctrl(x47823).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47810,x47812,x47806)
    val x47814 = OpDef(op=MuxOp, inputs=List(x47807, Const(1.0), x47813)).name("x47814").ctrl(x47823).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47807,Const(1),x47813)
    val x47815 = OpDef(op=FixNeg, inputs=List(x47814)).name("x47815").ctrl(x47823).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47814)
    val x47816 = OpDef(op=FixLt, inputs=List(x47805, Const(0.0))).name("x47816").ctrl(x47823).srcCtx("CellsPar.scala:63:22") // FixLt(x47805,Const(0))
    val x47817 = OpDef(op=MuxOp, inputs=List(x47816, x47815, x47814)).name("x47817").ctrl(x47823).srcCtx("CellsPar.scala:63:17:re") // Mux(x47816,x47815,x47814)
    val x47818 = x47817 // FixConvert(x47817,TRUE,_8,_24) (Same Type. No op)
    val x47819 = OpDef(op=FixAdd, inputs=List(x47818, Const(1.0))).name("x47819").ctrl(x47823).srcCtx("CellsPar.scala:29:33") // FixAdd(x47818,Const(1))
    val x47820 = OpDef(op=FixSra, inputs=List(x47819, Const(1))).name("x47820").ctrl(x47823).srcCtx("CellsPar.scala:29:44") // FixRsh(x47819,Const(1))
    val x47821 = OpDef(op=MuxOp, inputs=List(x47801, x47820, x47800)).name("x47821").ctrl(x47823).srcCtx("CellsPar.scala:218:43") // Mux(x47801,x47820,x47800)
    val x47822 = StoreBanks(List(x45557_d0_b0, x45557_d1_b0), List(b31310, x47789), x47821).name("x47822").ctrl(x47823).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45557,ArrayBuffer(Const(1), Const(512)),List(b31310, x47789),Const(0),x47821,x47796)
    val x47827 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x47827").ctrl(x48337).srcCtx("CellsPar.scala:267:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x47828 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47828").ctrl(x48337).srcCtx("CellsPar.scala:267:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47829 = CounterChain(List(x47828,x47827)).name("x47829").ctrl(x48337).srcCtx("CellsPar.scala:267:62") // CounterChainNew(List(x47828, x47827))
    val x47865 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47829).name("x47865").ctrl(x48337).srcCtx("CellsPar.scala:267:62") // UnrolledForeach(List(b30254),x47829,Block(Const(())),List(List(b31389), List(b31390)),List(List(b31391), List(b31392)))
    val b31389 = CounterIter(x47828, Some(0)).name("b31389").ctrl(x47865) // b31389
    val b31391 = Const(true).name("b31391").ctrl(x47865) // b31391
    val b31390 = CounterIter(x47827, None).name("b31390").ctrl(x47865) // b31390
    val b31392 = Const(true).name("b31392").ctrl(x47865) // b31392
    val x47830 = OpDef(op=BitAnd, inputs=List(b31391, b31392)).name("x47830").ctrl(x47865).srcCtx("UnrollingBase.scala:28:66") // And(b31391,b31392)
    val x47831 = OpDef(op=BitAnd, inputs=List(x47830, b30254)).name("x47831").ctrl(x47865).srcCtx("UnrollingBase.scala:28:66") // And(x47830,b30254)
    val x47832 = LoadBanks(List(x45553_d0_b0), List(b31389, b31390)).name("x47832").ctrl(x47865).srcCtx("CellsPar.scala:268:22") // ParSRAMLoad(x45553,List(List(b31389, b31390)),List(x47831))
    val x47833 = x47832 // x47833 = VectorApply(x47832,0)
    val x47834 = LoadBanks(List(x45556_d0_b0), List(b31389, b31390)).name("x47834").ctrl(x47865).srcCtx("CellsPar.scala:268:34") // ParSRAMLoad(x45556,List(List(b31389, b31390)),List(x47831))
    val x47835 = x47834 // x47835 = VectorApply(x47834,0)
    val x47836 = OpDef(op=FixMul, inputs=List(x47833, x47835)).name("x47836").ctrl(x47865).srcCtx("CellsPar.scala:268:28") // FixMul(x47833,x47835)
    val x47837 = LoadBanks(List(x45554_d0_b0), List(b31389, b31390)).name("x47837").ctrl(x47865).srcCtx("CellsPar.scala:268:46") // ParSRAMLoad(x45554,List(List(b31389, b31390)),List(x47831))
    val x47838 = x47837 // x47838 = VectorApply(x47837,0)
    val x47839 = LoadBanks(List(x45555_d0_b0), List(b31389, b31390)).name("x47839").ctrl(x47865).srcCtx("CellsPar.scala:268:59") // ParSRAMLoad(x45555,List(List(b31389, b31390)),List(x47831))
    val x47840 = x47839 // x47840 = VectorApply(x47839,0)
    val x47841 = OpDef(op=FixMul, inputs=List(x47838, x47840)).name("x47841").ctrl(x47865).srcCtx("CellsPar.scala:268:52") // FixMul(x47838,x47840)
    val x47842 = OpDef(op=FixAdd, inputs=List(x47836, x47841)).name("x47842").ctrl(x47865).srcCtx("CellsPar.scala:268:40:new_c") // FixAdd(x47836,x47841)
    val x47843 = x47842 // FixConvert(x47842,TRUE,_8,_24) (Same Type. No op)
    val x47844 = OpDef(op=FixAbs, inputs=List(x47843)).name("x47844").ctrl(x47865).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47843)
    val x47845 = OpDef(op=FixLt, inputs=List(Const(2.5), x47844)).name("x47845").ctrl(x47865).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47844)
    val x47846 = OpDef(op=FixLt, inputs=List(Const(0.5), x47844)).name("x47846").ctrl(x47865).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47844)
    val x47847 = OpDef(op=FixLeq, inputs=List(x47844, Const(2.5))).name("x47847").ctrl(x47865).srcCtx("CellsPar.scala:54:52") // FixLeq(x47844,Const(2.5))
    val x47848 = OpDef(op=BitAnd, inputs=List(x47846, x47847)).name("x47848").ctrl(x47865).srcCtx("CellsPar.scala:54:43:cond2") // And(x47846,x47847)
    val x47849 = OpDef(op=FixSra, inputs=List(x47844, Const(2))).name("x47849").ctrl(x47865).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47844,Const(2))
    val x47850 = OpDef(op=FixAdd, inputs=List(x47849, Const(0.375))).name("x47850").ctrl(x47865).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47849,Const(0.375))
    val x47851 = OpDef(op=MuxOp, inputs=List(x47848, x47850, x47844)).name("x47851").ctrl(x47865).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47848,x47850,x47844)
    val x47852 = OpDef(op=MuxOp, inputs=List(x47845, Const(1.0), x47851)).name("x47852").ctrl(x47865).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47845,Const(1),x47851)
    val x47853 = OpDef(op=FixNeg, inputs=List(x47852)).name("x47853").ctrl(x47865).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47852)
    val x47854 = OpDef(op=FixLt, inputs=List(x47843, Const(0.0))).name("x47854").ctrl(x47865).srcCtx("CellsPar.scala:63:22") // FixLt(x47843,Const(0))
    val x47855 = OpDef(op=MuxOp, inputs=List(x47854, x47853, x47852)).name("x47855").ctrl(x47865).srcCtx("CellsPar.scala:63:17:re") // Mux(x47854,x47853,x47852)
    val x47856 = x47855 // FixConvert(x47855,TRUE,_8,_24) (Same Type. No op)
    val x47857 = LoadBanks(List(x45557_d0_b0), List(b31389, b31390)).name("x47857").ctrl(x47865).srcCtx("CellsPar.scala:275:47") // ParSRAMLoad(x45557,List(List(b31389, b31390)),List(x47831))
    val x47858 = x47857 // x47858 = VectorApply(x47857,0)
    val x47859 = OpDef(op=FixMul, inputs=List(x47856, x47858)).name("x47859").ctrl(x47865).srcCtx("CellsPar.scala:275:41:prod") // FixMul(x47856,x47858)
    val x47860 = LoadBanks(List(x45545_d0_b0), List(b31389, b31390)).name("x47860").ctrl(x47865).srcCtx("CellsPar.scala:276:43") // ParSRAMLoad(x45545,List(List(b31389, b31390)),List(x47831))
    val x47861 = x47860 // x47861 = VectorApply(x47860,0)
    val x47862 = OpDef(op=FixAdd, inputs=List(x47859, x47861)).name("x47862").ctrl(x47865).srcCtx("CellsPar.scala:276:40") // FixAdd(x47859,x47861)
    val x47863 = StoreBanks(List(x45552_d0_b0, x45552_d5_b0, x45552_d1_b0, x45552_d6_b0, x45552_d2_b0, x45552_d7_b0, x45552_d3_b0, x45552_d8_b0, x45552_d4_b0), List(b31389, b31390), x47859).name("x47863").ctrl(x47865).srcCtx("CellsPar.scala:276:17") // ParSRAMStore(x45552,List(List(b31389, b31390)),List(x47859),List(x47831))
    val x47864 = StoreBanks(List(x45553_d0_b0), List(b31389, b31390), x47842).name("x47864").ctrl(x47865).srcCtx("CellsPar.scala:277:16") // ParSRAMStore(x45553,List(List(b31389, b31390)),List(x47842),List(x47831))
    val x47866 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47866").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47867 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x47867").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x47868 = CounterChain(List(x47867,x47866)).name("x47868").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x47867, x47866))
    val x47973 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47868).name("x47973").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x47868,Block(Const(())),List(List(b31432), List(b31433)),List(List(b31434), List(b31435)))
    val b31432 = CounterIter(x47867, Some(0)).name("b31432").ctrl(x47973) // b31432
    val b31434 = Const(true).name("b31434").ctrl(x47973) // b31434
    val b31433 = CounterIter(x47866, Some(0)).name("b31433").ctrl(x47973) // b31433
    val b31435 = Const(true).name("b31435").ctrl(x47973) // b31435
    val x47869_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47869_d0_b0").ctrl(x47973).srcCtx("CellsPar.scala:191:33:tileKernel") // x47869 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47869_d0_b0) = false
    bufferDepthOf(x47869_d0_b0) = 2
    val x47872 = UnitController(style=SeqPipe, level=InnerControl).name("x47872").ctrl(x47973).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31434, b31435, b30254),Block(Const(())))
    val x47870 = OpDef(op=FixAdd, inputs=List(b31432, Const(16))).name("x47870").ctrl(x47872).srcCtx("CellsPar.scala:192:36") // FixAdd(b31432,Const(16))
    val x47871 = OpDef(op=FixAdd, inputs=List(b31433, Const(16))).name("x47871").ctrl(x47872).srcCtx("CellsPar.scala:192:45") // FixAdd(b31433,Const(16))
    val x47873 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47873").ctrl(x47973).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47874 = CounterChain(List(x47873)).name("x47874").ctrl(x47973).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47873))
    val x47903 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47874).name("x47903").ctrl(x47973).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31434, b31435, b30254),x47874,Block(Const(())),List(List(b31442)),List(List(b31443)))
    val b31442 = CounterIter(x47873, Some(0)).name("b31442").ctrl(x47903) // b31442
    val b31443 = Const(true).name("b31443").ctrl(x47903) // b31443
    val b48512 = StreamOut(field="offset").name("b48512").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // x47875 = StreamOutNew(BurstCmdBus)
    isAccum(b48512) = false
    bufferDepthOf(b48512) = 1
    val b48513 = StreamOut(field="size").name("b48513").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // x47875 = StreamOutNew(BurstCmdBus)
    isAccum(b48513) = false
    bufferDepthOf(b48513) = 1
    val x47876 = StreamIn(field="data").name("x47876").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // x47876 = StreamInNew(BurstDataBus())
    isAccum(x47876) = false
    bufferDepthOf(x47876) = 1
    val x47891 = UnitController(style=SeqPipe, level=InnerControl).name("x47891").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31443, b31434, b31435, b30254),Block(x47890))
    val x47877 = OpDef(op=FixAdd, inputs=List(b31432, b31442)).name("x47877").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // FixAdd(b31432,b31442)
    val x47878 = x47877 // FixConvert(x47877,TRUE,_32,_0) (Same Type. No op)
    val x47879 = OpDef(op=FixSla, inputs=List(x47878, Const(9))).name("x47879").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // FixLsh(x47878,Const(9))
    val x47880 = b31433 // FixConvert(b31433,TRUE,_32,_0) (Same Type. No op)
    val x47881 = OpDef(op=FixAdd, inputs=List(x47879, x47880)).name("x47881").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // FixAdd(x47879,x47880)
    val x47882 = OpDef(op=FixSla, inputs=List(x47881, Const(2))).name("x47882").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // FixLsh(x47881,Const(2))
    val x47883 = x47882 // FixConvert(x47882,TRUE,_64,_0)
    val x47884 = DramAddress(x45481).name("x47884").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45481)
    val x47886_x47885 = OpDef(op=FixAdd, inputs=List(x47883, x47884)).name("x47886_x47885").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // FixAdd(x47883,x47884)
    // x47886 = SimpleStruct(ArrayBuffer((offset,x47885), (size,Const(64)), (isLoad,Const(true))))
    val x47887 = OpDef(op=BitAnd, inputs=List(b31443, b31434)).name("x47887").ctrl(x47891).srcCtx("UnrollingBase.scala:28:66") // And(b31443,b31434)
    val x47888 = OpDef(op=BitAnd, inputs=List(b31435, b30254)).name("x47888").ctrl(x47891).srcCtx("UnrollingBase.scala:28:66") // And(b31435,b30254)
    val x47889 = OpDef(op=BitAnd, inputs=List(x47887, x47888)).name("x47889").ctrl(x47891).srcCtx("UnrollingBase.scala:28:66") // And(x47887,x47888)
    val x47890_b48514_b48512 = WriteMem(b48512, x47886_x47885).name("x47890_b48514_b48512").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47875,x47886,x47889)
    val x47890_b48515_b48513 = WriteMem(b48513, Const(64)).name("x47890_b48515_b48513").ctrl(x47891).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47875,x47886,x47889)
    val x47892 = FringeDenseLoad(dram=List(x45481), cmdStream=List(b48512, b48513), dataStream=List(x47876)).name("x47892").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45481,x47875,x47876)
    val x47893 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47893").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47894 = CounterChain(List(x47893)).name("x47894").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47893))
    val x47902 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47894).name("x47902").ctrl(x47903).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31443, b31434, b31435, b30254),x47894,Block(Const(())),List(List(b31464)),List(List(b31465)))
    val b31464 = CounterIter(x47893, None).name("b31464").ctrl(x47902) // b31464
    val b31465 = Const(true).name("b31465").ctrl(x47902) // b31465
    val x47895 = OpDef(op=BitAnd, inputs=List(b31465, b31443)).name("x47895").ctrl(x47902).srcCtx("UnrollingBase.scala:28:66") // And(b31465,b31443)
    val x47896 = OpDef(op=BitAnd, inputs=List(b31434, b31435)).name("x47896").ctrl(x47902).srcCtx("UnrollingBase.scala:28:66") // And(b31434,b31435)
    val x47897 = OpDef(op=BitAnd, inputs=List(x47895, x47896)).name("x47897").ctrl(x47902).srcCtx("UnrollingBase.scala:28:66") // And(x47895,x47896)
    val x47898 = OpDef(op=BitAnd, inputs=List(x47897, b30254)).name("x47898").ctrl(x47902).srcCtx("UnrollingBase.scala:28:66") // And(x47897,b30254)
    val x47899_x47899 = ReadMem(x47876).name("x47899_x47899").ctrl(x47902).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x47876,List(x47898))
    val x47900_x47900 = x47899_x47899 // x47900 = VectorApply(x47899,0)
    val x47901 = StoreBanks(List(x47869_d0_b0), List(b31442, b31464), x47900_x47900).name("x47901").ctrl(x47902).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x47869,List(List(b31442, b31464)),List(x47900),List(x47898))
    val x47904 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x47904").ctrl(x47973).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x47905 = CounterChain(List(x47904)).name("x47905").ctrl(x47973).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x47904))
    val x47972 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47905).name("x47972").ctrl(x47973).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31434, b31435, b30254),x47905,Block(Const(())),List(List(b31477)),List(List(b31478)))
    val b31477 = CounterIter(x47904, Some(0)).name("b31477").ctrl(x47972) // b31477
    val b31478 = Const(true).name("b31478").ctrl(x47972) // b31478
    val x47906 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47906").ctrl(x47972).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47907 = CounterChain(List(x47906)).name("x47907").ctrl(x47972).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x47906))
    val x47971 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47907).name("x47971").ctrl(x47972).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31478, b31434, b31435, b30254),x47907,Block(Const(())),List(List(b31481)),List(List(b31482)))
    val b31481 = CounterIter(x47906, Some(0)).name("b31481").ctrl(x47971) // b31481
    val b31482 = Const(true).name("b31482").ctrl(x47971) // b31482
    val x47908_d0 = Reg(init=Some(0.0)).name("x47908_d0").ctrl(x47971).srcCtx("CellsPar.scala:195:34:prod") // x47908 = RegNew(Const(0))
    isAccum(x47908_d0) = false
    bufferDepthOf(x47908_d0) = 2
    val x47908_d1 = Reg(init=Some(0.0)).name("x47908_d1").ctrl(x47971).srcCtx("CellsPar.scala:195:34:prod") // x47908 = RegNew(Const(0))
    isAccum(x47908_d1) = true
    bufferDepthOf(x47908_d1) = 1
    val x47909 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47909").ctrl(x47971).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47910 = CounterChain(List(x47909)).name("x47910").ctrl(x47971).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x47909))
    val x47936 = LoopController(style=InnerPipe, level=InnerControl, cchain=x47910).name("x47936").ctrl(x47971).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31482, b31478, b31434, b31435, b30254),x47910,x47908,Block((x47908) => Const(())),List(List(b31486)),List(List(b31487)))
    val b31486 = CounterIter(x47909, None).name("b31486").ctrl(x47936) // b31486
    val b31487 = Const(true).name("b31487").ctrl(x47936) // b31487
    val x47911 = OpDef(op=FixAdd, inputs=List(b31432, b31486)).name("x47911").ctrl(x47936).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31432,b31486)
    val x47912 = OpDef(op=BitAnd, inputs=List(b31487, b31482)).name("x47912").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(b31487,b31482)
    val x47913 = OpDef(op=BitAnd, inputs=List(b31478, b31434)).name("x47913").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(b31478,b31434)
    val x47914 = OpDef(op=BitAnd, inputs=List(b31435, b30254)).name("x47914").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(b31435,b30254)
    val x47915 = OpDef(op=BitAnd, inputs=List(x47912, x47913)).name("x47915").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(x47912,x47913)
    val x47916 = OpDef(op=BitAnd, inputs=List(x47915, x47914)).name("x47916").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(x47915,x47914)
    val x47917 = LoadBanks(List(x47869_d0_b0), List(b31486, b31481)).name("x47917").ctrl(x47936).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x47869,List(List(b31486, b31481)),List(x47916))
    val x47918 = x47917 // x47918 = VectorApply(x47917,0)
    val x47919 = LoadBanks(List(x45552_d4_b0), List(b31477, x47911)).name("x47919").ctrl(x47936).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45552,List(List(b31477, x47911)),List(x47916))
    val x47920 = x47919 // x47920 = VectorApply(x47919,0)
    val x47921 = OpDef(op=FixMul, inputs=List(x47920, x47918)).name("x47921").ctrl(x47936).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x47920,x47918)
    val x47922 = OpDef(op=FixSub, inputs=List(x47911, Const(512))).name("x47922").ctrl(x47936).srcCtx("CellsPar.scala:205:51") // FixSub(x47911,Const(512))
    val x47923 = LoadBanks(List(x45559_d4_b0), List(b31477, x47922)).name("x47923").ctrl(x47936).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45559,List(List(b31477, x47922)),List(x47916))
    val x47924 = x47923 // x47924 = VectorApply(x47923,0)
    val x47925 = OpDef(op=FixMul, inputs=List(x47924, x47918)).name("x47925").ctrl(x47936).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x47924,x47918)
    val x47926 = OpDef(op=FixLt, inputs=List(x47911, Const(512))).name("x47926").ctrl(x47936).srcCtx("CellsPar.scala:206:38") // FixLt(x47911,Const(512))
    val x47927 = OpDef(op=MuxOp, inputs=List(x47926, x47921, x47925)).name("x47927").ctrl(x47936).srcCtx("CellsPar.scala:206:18") // Mux(x47926,x47921,x47925)
    val x47928 = ReadMem(x47908_d1).name("x47928").ctrl(x47936).srcCtx("CellsPar.scala:207:15") // RegRead(x47908)
    val x47929 = OpDef(op=FixEql, inputs=List(b31486, Const(0))).name("x47929").ctrl(x47936).srcCtx("CellsPar.scala:207:15") // FixEql(b31486,Const(0))
    val x47930 = ReduceAccumOp(op=FixAdd, input=x47927, accum=x47928).name("x47930").ctrl(x47936).srcCtx("CellsPar.scala:207:17") // FixAdd(x47927,x47928)
    val x47931 = OpDef(op=BitAnd, inputs=List(b31482, b31478)).name("x47931").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(b31482,b31478)
    val x47932 = OpDef(op=BitAnd, inputs=List(b31434, b31435)).name("x47932").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(b31434,b31435)
    val x47933 = OpDef(op=BitAnd, inputs=List(x47931, x47932)).name("x47933").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(x47931,x47932)
    val x47934 = OpDef(op=BitAnd, inputs=List(x47933, b30254)).name("x47934").ctrl(x47936).srcCtx("UnrollingBase.scala:28:66") // And(x47933,b30254)
    val x47935_x47908_d0 = WriteMem(x47908_d0, x47930).name("x47935_x47908_d0").ctrl(x47936).srcCtx("CellsPar.scala:207:15") // RegWrite(x47908,x47930,x47934)
    val x47935_x47908_d1 = WriteMem(x47908_d1, x47930).name("x47935_x47908_d1").ctrl(x47936).srcCtx("CellsPar.scala:207:15") // RegWrite(x47908,x47930,x47934)
    val x47970 = UnitController(style=SeqPipe, level=InnerControl).name("x47970").ctrl(x47971).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31482, b31478, b31434, b31435, b30254),Block(Const(())))
    val x47937 = OpDef(op=FixAdd, inputs=List(b31433, b31481)).name("x47937").ctrl(x47970).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31433,b31481)
    val x47938 = ReadMem(x47908_d0).name("x47938").ctrl(x47970).srcCtx("CellsPar.scala:210:28") // RegRead(x47908)
    val x47939 = OpDef(op=FixEql, inputs=List(b31432, Const(0))).name("x47939").ctrl(x47970).srcCtx("CellsPar.scala:210:42") // FixEql(b31432,Const(0))
    val x47940 = OpDef(op=BitAnd, inputs=List(b31482, b31478)).name("x47940").ctrl(x47970).srcCtx("UnrollingBase.scala:28:66") // And(b31482,b31478)
    val x47941 = OpDef(op=BitAnd, inputs=List(b31434, b31435)).name("x47941").ctrl(x47970).srcCtx("UnrollingBase.scala:28:66") // And(b31434,b31435)
    val x47942 = OpDef(op=BitAnd, inputs=List(x47940, x47941)).name("x47942").ctrl(x47970).srcCtx("UnrollingBase.scala:28:66") // And(x47940,x47941)
    val x47943 = OpDef(op=BitAnd, inputs=List(x47942, b30254)).name("x47943").ctrl(x47970).srcCtx("UnrollingBase.scala:28:66") // And(x47942,b30254)
    val x47944 = LoadBanks(List(x45565_d3_b0), List(Const(0), x47937)).name("x47944").ctrl(x47970).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x47937),Const(0),x47943)
    val x47945 = LoadBanks(List(x45561_d1_b0), List(b31477, x47937)).name("x47945").ctrl(x47970).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45561,ArrayBuffer(Const(1), Const(512)),List(b31477, x47937),Const(0),x47943)
    val x47946 = OpDef(op=MuxOp, inputs=List(x47939, x47944, x47945)).name("x47946").ctrl(x47970).srcCtx("CellsPar.scala:210:39") // Mux(x47939,x47944,x47945)
    val x47947 = OpDef(op=FixAdd, inputs=List(x47938, x47946)).name("x47947").ctrl(x47970).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x47938,x47946)
    val x47948 = OpDef(op=FixLeq, inputs=List(Const(1008), b31432)).name("x47948").ctrl(x47970).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31432)
    // x47949 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x47949_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x47949_int1").ctrl(x47970).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47949_int2 = OpDef(op=FixSla, inputs=List(x47949_int1, Const(24))).name("x47949_int2").ctrl(x47970).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47949_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x47949_frac1").ctrl(x47970).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47949_frac2 = OpDef(op=FixSla, inputs=List(x47949_frac1, Const(24))).name("x47949_frac2").ctrl(x47970).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x47949 = OpDef(op=BitOr, inputs=List(x47949_int2, x47949_frac2)).name("x47949").ctrl(x47970).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x47950 = OpDef(op=FixAdd, inputs=List(x47947, x47949)).name("x47950").ctrl(x47970).srcCtx("CellsPar.scala:218:87") // FixAdd(x47947,x47949)
    val x47951 = OpDef(op=FixSra, inputs=List(x47950, Const(1))).name("x47951").ctrl(x47970).srcCtx("CellsPar.scala:29:22") // FixRsh(x47950,Const(1))
    val x47952 = x47951 // FixConvert(x47951,TRUE,_8,_24) (Same Type. No op)
    val x47953 = OpDef(op=FixAbs, inputs=List(x47952)).name("x47953").ctrl(x47970).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x47952)
    val x47954 = OpDef(op=FixLt, inputs=List(Const(2.5), x47953)).name("x47954").ctrl(x47970).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x47953)
    val x47955 = OpDef(op=FixLt, inputs=List(Const(0.5), x47953)).name("x47955").ctrl(x47970).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x47953)
    val x47956 = OpDef(op=FixLeq, inputs=List(x47953, Const(2.5))).name("x47956").ctrl(x47970).srcCtx("CellsPar.scala:54:52") // FixLeq(x47953,Const(2.5))
    val x47957 = OpDef(op=BitAnd, inputs=List(x47955, x47956)).name("x47957").ctrl(x47970).srcCtx("CellsPar.scala:54:43:cond2") // And(x47955,x47956)
    val x47958 = OpDef(op=FixSra, inputs=List(x47953, Const(2))).name("x47958").ctrl(x47970).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x47953,Const(2))
    val x47959 = OpDef(op=FixAdd, inputs=List(x47958, Const(0.375))).name("x47959").ctrl(x47970).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x47958,Const(0.375))
    val x47960 = OpDef(op=MuxOp, inputs=List(x47957, x47959, x47953)).name("x47960").ctrl(x47970).srcCtx("CellsPar.scala:58:20:body2") // Mux(x47957,x47959,x47953)
    val x47961 = OpDef(op=MuxOp, inputs=List(x47954, Const(1.0), x47960)).name("x47961").ctrl(x47970).srcCtx("CellsPar.scala:60:20:absre") // Mux(x47954,Const(1),x47960)
    val x47962 = OpDef(op=FixNeg, inputs=List(x47961)).name("x47962").ctrl(x47970).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x47961)
    val x47963 = OpDef(op=FixLt, inputs=List(x47952, Const(0.0))).name("x47963").ctrl(x47970).srcCtx("CellsPar.scala:63:22") // FixLt(x47952,Const(0))
    val x47964 = OpDef(op=MuxOp, inputs=List(x47963, x47962, x47961)).name("x47964").ctrl(x47970).srcCtx("CellsPar.scala:63:17:re") // Mux(x47963,x47962,x47961)
    val x47965 = x47964 // FixConvert(x47964,TRUE,_8,_24) (Same Type. No op)
    val x47966 = OpDef(op=FixAdd, inputs=List(x47965, Const(1.0))).name("x47966").ctrl(x47970).srcCtx("CellsPar.scala:29:33") // FixAdd(x47965,Const(1))
    val x47967 = OpDef(op=FixSra, inputs=List(x47966, Const(1))).name("x47967").ctrl(x47970).srcCtx("CellsPar.scala:29:44") // FixRsh(x47966,Const(1))
    val x47968 = OpDef(op=MuxOp, inputs=List(x47948, x47967, x47947)).name("x47968").ctrl(x47970).srcCtx("CellsPar.scala:218:43") // Mux(x47948,x47967,x47947)
    val x47969 = StoreBanks(List(x45561_d0_b0, x45561_d1_b0), List(b31477, x47937), x47968).name("x47969").ctrl(x47970).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45561,ArrayBuffer(Const(1), Const(512)),List(b31477, x47937),Const(0),x47968,x47943)
    val x47974 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x47974").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x47975 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x47975").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x47976 = CounterChain(List(x47975,x47974)).name("x47976").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x47975, x47974))
    val x48079 = LoopController(style=MetaPipe, level=OuterControl, cchain=x47976).name("x48079").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x47976,Block(Const(())),List(List(b31554), List(b31555)),List(List(b31556), List(b31557)))
    val b31554 = CounterIter(x47975, Some(0)).name("b31554").ctrl(x48079) // b31554
    val b31556 = Const(true).name("b31556").ctrl(x48079) // b31556
    val b31555 = CounterIter(x47974, Some(0)).name("b31555").ctrl(x48079) // b31555
    val b31557 = Const(true).name("b31557").ctrl(x48079) // b31557
    val x47977_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x47977_d0_b0").ctrl(x48079).srcCtx("CellsPar.scala:191:33:tileKernel") // x47977 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x47977_d0_b0) = false
    bufferDepthOf(x47977_d0_b0) = 2
    val x47980 = UnitController(style=SeqPipe, level=InnerControl).name("x47980").ctrl(x48079).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31556, b31557, b30254),Block(Const(())))
    val x47978 = OpDef(op=FixAdd, inputs=List(b31554, Const(16))).name("x47978").ctrl(x47980).srcCtx("CellsPar.scala:192:36") // FixAdd(b31554,Const(16))
    val x47979 = OpDef(op=FixAdd, inputs=List(b31555, Const(16))).name("x47979").ctrl(x47980).srcCtx("CellsPar.scala:192:45") // FixAdd(b31555,Const(16))
    val x47981 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x47981").ctrl(x48079).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x47982 = CounterChain(List(x47981)).name("x47982").ctrl(x48079).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x47981))
    val x48011 = LoopController(style=StreamPipe, level=OuterControl, cchain=x47982).name("x48011").ctrl(x48079).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31556, b31557, b30254),x47982,Block(Const(())),List(List(b31564)),List(List(b31565)))
    val b31564 = CounterIter(x47981, Some(0)).name("b31564").ctrl(x48011) // b31564
    val b31565 = Const(true).name("b31565").ctrl(x48011) // b31565
    val b48516 = StreamOut(field="offset").name("b48516").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // x47983 = StreamOutNew(BurstCmdBus)
    isAccum(b48516) = false
    bufferDepthOf(b48516) = 1
    val b48517 = StreamOut(field="size").name("b48517").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // x47983 = StreamOutNew(BurstCmdBus)
    isAccum(b48517) = false
    bufferDepthOf(b48517) = 1
    val x47984 = StreamIn(field="data").name("x47984").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // x47984 = StreamInNew(BurstDataBus())
    isAccum(x47984) = false
    bufferDepthOf(x47984) = 1
    val x47999 = UnitController(style=SeqPipe, level=InnerControl).name("x47999").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31565, b31556, b31557, b30254),Block(x47998))
    val x47985 = OpDef(op=FixAdd, inputs=List(b31554, b31564)).name("x47985").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // FixAdd(b31554,b31564)
    val x47986 = x47985 // FixConvert(x47985,TRUE,_32,_0) (Same Type. No op)
    val x47987 = OpDef(op=FixSla, inputs=List(x47986, Const(9))).name("x47987").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // FixLsh(x47986,Const(9))
    val x47988 = b31555 // FixConvert(b31555,TRUE,_32,_0) (Same Type. No op)
    val x47989 = OpDef(op=FixAdd, inputs=List(x47987, x47988)).name("x47989").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // FixAdd(x47987,x47988)
    val x47990 = OpDef(op=FixSla, inputs=List(x47989, Const(2))).name("x47990").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // FixLsh(x47989,Const(2))
    val x47991 = x47990 // FixConvert(x47990,TRUE,_64,_0)
    val x47992 = DramAddress(x45482).name("x47992").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45482)
    val x47994_x47993 = OpDef(op=FixAdd, inputs=List(x47991, x47992)).name("x47994_x47993").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // FixAdd(x47991,x47992)
    // x47994 = SimpleStruct(ArrayBuffer((offset,x47993), (size,Const(64)), (isLoad,Const(true))))
    val x47995 = OpDef(op=BitAnd, inputs=List(b31565, b31556)).name("x47995").ctrl(x47999).srcCtx("UnrollingBase.scala:28:66") // And(b31565,b31556)
    val x47996 = OpDef(op=BitAnd, inputs=List(b31557, b30254)).name("x47996").ctrl(x47999).srcCtx("UnrollingBase.scala:28:66") // And(b31557,b30254)
    val x47997 = OpDef(op=BitAnd, inputs=List(x47995, x47996)).name("x47997").ctrl(x47999).srcCtx("UnrollingBase.scala:28:66") // And(x47995,x47996)
    val x47998_b48518_b48516 = WriteMem(b48516, x47994_x47993).name("x47998_b48518_b48516").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47983,x47994,x47997)
    val x47998_b48519_b48517 = WriteMem(b48517, Const(64)).name("x47998_b48519_b48517").ctrl(x47999).srcCtx("CellsPar.scala:192:20") // StreamWrite(x47983,x47994,x47997)
    val x48000 = FringeDenseLoad(dram=List(x45482), cmdStream=List(b48516, b48517), dataStream=List(x47984)).name("x48000").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45482,x47983,x47984)
    val x48001 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48001").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48002 = CounterChain(List(x48001)).name("x48002").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x48001))
    val x48010 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48002).name("x48010").ctrl(x48011).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31565, b31556, b31557, b30254),x48002,Block(Const(())),List(List(b31586)),List(List(b31587)))
    val b31586 = CounterIter(x48001, None).name("b31586").ctrl(x48010) // b31586
    val b31587 = Const(true).name("b31587").ctrl(x48010) // b31587
    val x48003 = OpDef(op=BitAnd, inputs=List(b31587, b31565)).name("x48003").ctrl(x48010).srcCtx("UnrollingBase.scala:28:66") // And(b31587,b31565)
    val x48004 = OpDef(op=BitAnd, inputs=List(b31556, b31557)).name("x48004").ctrl(x48010).srcCtx("UnrollingBase.scala:28:66") // And(b31556,b31557)
    val x48005 = OpDef(op=BitAnd, inputs=List(x48003, x48004)).name("x48005").ctrl(x48010).srcCtx("UnrollingBase.scala:28:66") // And(x48003,x48004)
    val x48006 = OpDef(op=BitAnd, inputs=List(x48005, b30254)).name("x48006").ctrl(x48010).srcCtx("UnrollingBase.scala:28:66") // And(x48005,b30254)
    val x48007_x48007 = ReadMem(x47984).name("x48007_x48007").ctrl(x48010).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x47984,List(x48006))
    val x48008_x48008 = x48007_x48007 // x48008 = VectorApply(x48007,0)
    val x48009 = StoreBanks(List(x47977_d0_b0), List(b31564, b31586), x48008_x48008).name("x48009").ctrl(x48010).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x47977,List(List(b31564, b31586)),List(x48008),List(x48006))
    val x48012 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x48012").ctrl(x48079).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x48013 = CounterChain(List(x48012)).name("x48013").ctrl(x48079).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x48012))
    val x48078 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48013).name("x48078").ctrl(x48079).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31556, b31557, b30254),x48013,Block(Const(())),List(List(b31599)),List(List(b31600)))
    val b31599 = CounterIter(x48012, Some(0)).name("b31599").ctrl(x48078) // b31599
    val b31600 = Const(true).name("b31600").ctrl(x48078) // b31600
    val x48014 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48014").ctrl(x48078).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48015 = CounterChain(List(x48014)).name("x48015").ctrl(x48078).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x48014))
    val x48077 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48015).name("x48077").ctrl(x48078).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31600, b31556, b31557, b30254),x48015,Block(Const(())),List(List(b31603)),List(List(b31604)))
    val b31603 = CounterIter(x48014, Some(0)).name("b31603").ctrl(x48077) // b31603
    val b31604 = Const(true).name("b31604").ctrl(x48077) // b31604
    val x48016_d0 = Reg(init=Some(0.0)).name("x48016_d0").ctrl(x48077).srcCtx("CellsPar.scala:195:34:prod") // x48016 = RegNew(Const(0))
    isAccum(x48016_d0) = false
    bufferDepthOf(x48016_d0) = 2
    val x48016_d1 = Reg(init=Some(0.0)).name("x48016_d1").ctrl(x48077).srcCtx("CellsPar.scala:195:34:prod") // x48016 = RegNew(Const(0))
    isAccum(x48016_d1) = true
    bufferDepthOf(x48016_d1) = 1
    val x48017 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48017").ctrl(x48077).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48018 = CounterChain(List(x48017)).name("x48018").ctrl(x48077).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x48017))
    val x48044 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48018).name("x48044").ctrl(x48077).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31604, b31600, b31556, b31557, b30254),x48018,x48016,Block((x48016) => Const(())),List(List(b31608)),List(List(b31609)))
    val b31608 = CounterIter(x48017, None).name("b31608").ctrl(x48044) // b31608
    val b31609 = Const(true).name("b31609").ctrl(x48044) // b31609
    val x48019 = OpDef(op=FixAdd, inputs=List(b31554, b31608)).name("x48019").ctrl(x48044).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31554,b31608)
    val x48020 = OpDef(op=BitAnd, inputs=List(b31609, b31604)).name("x48020").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(b31609,b31604)
    val x48021 = OpDef(op=BitAnd, inputs=List(b31600, b31556)).name("x48021").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(b31600,b31556)
    val x48022 = OpDef(op=BitAnd, inputs=List(b31557, b30254)).name("x48022").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(b31557,b30254)
    val x48023 = OpDef(op=BitAnd, inputs=List(x48020, x48021)).name("x48023").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(x48020,x48021)
    val x48024 = OpDef(op=BitAnd, inputs=List(x48023, x48022)).name("x48024").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(x48023,x48022)
    val x48025 = LoadBanks(List(x47977_d0_b0), List(b31608, b31603)).name("x48025").ctrl(x48044).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x47977,List(List(b31608, b31603)),List(x48024))
    val x48026 = x48025 // x48026 = VectorApply(x48025,0)
    val x48027 = LoadBanks(List(x45552_d3_b0), List(b31599, x48019)).name("x48027").ctrl(x48044).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45552,List(List(b31599, x48019)),List(x48024))
    val x48028 = x48027 // x48028 = VectorApply(x48027,0)
    val x48029 = OpDef(op=FixMul, inputs=List(x48028, x48026)).name("x48029").ctrl(x48044).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x48028,x48026)
    val x48030 = OpDef(op=FixSub, inputs=List(x48019, Const(512))).name("x48030").ctrl(x48044).srcCtx("CellsPar.scala:205:51") // FixSub(x48019,Const(512))
    val x48031 = LoadBanks(List(x45559_d3_b0), List(b31599, x48030)).name("x48031").ctrl(x48044).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45559,List(List(b31599, x48030)),List(x48024))
    val x48032 = x48031 // x48032 = VectorApply(x48031,0)
    val x48033 = OpDef(op=FixMul, inputs=List(x48032, x48026)).name("x48033").ctrl(x48044).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x48032,x48026)
    val x48034 = OpDef(op=FixLt, inputs=List(x48019, Const(512))).name("x48034").ctrl(x48044).srcCtx("CellsPar.scala:206:38") // FixLt(x48019,Const(512))
    val x48035 = OpDef(op=MuxOp, inputs=List(x48034, x48029, x48033)).name("x48035").ctrl(x48044).srcCtx("CellsPar.scala:206:18") // Mux(x48034,x48029,x48033)
    val x48036 = ReadMem(x48016_d1).name("x48036").ctrl(x48044).srcCtx("CellsPar.scala:207:15") // RegRead(x48016)
    val x48037 = OpDef(op=FixEql, inputs=List(b31608, Const(0))).name("x48037").ctrl(x48044).srcCtx("CellsPar.scala:207:15") // FixEql(b31608,Const(0))
    val x48038 = ReduceAccumOp(op=FixAdd, input=x48035, accum=x48036).name("x48038").ctrl(x48044).srcCtx("CellsPar.scala:207:17") // FixAdd(x48035,x48036)
    val x48039 = OpDef(op=BitAnd, inputs=List(b31604, b31600)).name("x48039").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(b31604,b31600)
    val x48040 = OpDef(op=BitAnd, inputs=List(b31556, b31557)).name("x48040").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(b31556,b31557)
    val x48041 = OpDef(op=BitAnd, inputs=List(x48039, x48040)).name("x48041").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(x48039,x48040)
    val x48042 = OpDef(op=BitAnd, inputs=List(x48041, b30254)).name("x48042").ctrl(x48044).srcCtx("UnrollingBase.scala:28:66") // And(x48041,b30254)
    val x48043_x48016_d0 = WriteMem(x48016_d0, x48038).name("x48043_x48016_d0").ctrl(x48044).srcCtx("CellsPar.scala:207:15") // RegWrite(x48016,x48038,x48042)
    val x48043_x48016_d1 = WriteMem(x48016_d1, x48038).name("x48043_x48016_d1").ctrl(x48044).srcCtx("CellsPar.scala:207:15") // RegWrite(x48016,x48038,x48042)
    val x48076 = UnitController(style=SeqPipe, level=InnerControl).name("x48076").ctrl(x48077).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31604, b31600, b31556, b31557, b30254),Block(Const(())))
    val x48045 = OpDef(op=FixAdd, inputs=List(b31555, b31603)).name("x48045").ctrl(x48076).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31555,b31603)
    val x48046 = ReadMem(x48016_d0).name("x48046").ctrl(x48076).srcCtx("CellsPar.scala:210:28") // RegRead(x48016)
    val x48047 = OpDef(op=FixEql, inputs=List(b31554, Const(0))).name("x48047").ctrl(x48076).srcCtx("CellsPar.scala:210:42") // FixEql(b31554,Const(0))
    val x48048 = OpDef(op=FixAdd, inputs=List(x48045, Const(512))).name("x48048").ctrl(x48076).srcCtx("CellsPar.scala:210:66") // FixAdd(x48045,Const(512))
    val x48049 = OpDef(op=BitAnd, inputs=List(b31604, b31600)).name("x48049").ctrl(x48076).srcCtx("UnrollingBase.scala:28:66") // And(b31604,b31600)
    val x48050 = OpDef(op=BitAnd, inputs=List(b31556, b31557)).name("x48050").ctrl(x48076).srcCtx("UnrollingBase.scala:28:66") // And(b31556,b31557)
    val x48051 = OpDef(op=BitAnd, inputs=List(x48049, x48050)).name("x48051").ctrl(x48076).srcCtx("UnrollingBase.scala:28:66") // And(x48049,x48050)
    val x48052 = OpDef(op=BitAnd, inputs=List(x48051, b30254)).name("x48052").ctrl(x48076).srcCtx("UnrollingBase.scala:28:66") // And(x48051,b30254)
    val x48053 = LoadBanks(List(x45565_d2_b0), List(Const(0), x48048)).name("x48053").ctrl(x48076).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x48048),Const(0),x48052)
    val x48054 = LoadBanks(List(x45562_d1_b0), List(b31599, x48045)).name("x48054").ctrl(x48076).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45562,ArrayBuffer(Const(1), Const(512)),List(b31599, x48045),Const(0),x48052)
    val x48055 = OpDef(op=MuxOp, inputs=List(x48047, x48053, x48054)).name("x48055").ctrl(x48076).srcCtx("CellsPar.scala:210:39") // Mux(x48047,x48053,x48054)
    val x48056 = OpDef(op=FixAdd, inputs=List(x48046, x48055)).name("x48056").ctrl(x48076).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x48046,x48055)
    val x48057 = OpDef(op=FixLeq, inputs=List(Const(1008), b31554)).name("x48057").ctrl(x48076).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31554)
    // x48058 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x48058_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x48058_int1").ctrl(x48076).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48058_int2 = OpDef(op=FixSla, inputs=List(x48058_int1, Const(24))).name("x48058_int2").ctrl(x48076).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48058_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x48058_frac1").ctrl(x48076).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48058_frac2 = OpDef(op=FixSla, inputs=List(x48058_frac1, Const(24))).name("x48058_frac2").ctrl(x48076).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48058 = OpDef(op=BitOr, inputs=List(x48058_int2, x48058_frac2)).name("x48058").ctrl(x48076).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x48059 = OpDef(op=FixAdd, inputs=List(x48056, x48058)).name("x48059").ctrl(x48076).srcCtx("CellsPar.scala:218:87") // FixAdd(x48056,x48058)
    val x48060 = x48059 // FixConvert(x48059,TRUE,_8,_24) (Same Type. No op)
    val x48061 = OpDef(op=FixAbs, inputs=List(x48060)).name("x48061").ctrl(x48076).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x48060)
    val x48062 = OpDef(op=FixLt, inputs=List(Const(2.5), x48061)).name("x48062").ctrl(x48076).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x48061)
    val x48063 = OpDef(op=FixLt, inputs=List(Const(0.5), x48061)).name("x48063").ctrl(x48076).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x48061)
    val x48064 = OpDef(op=FixLeq, inputs=List(x48061, Const(2.5))).name("x48064").ctrl(x48076).srcCtx("CellsPar.scala:54:52") // FixLeq(x48061,Const(2.5))
    val x48065 = OpDef(op=BitAnd, inputs=List(x48063, x48064)).name("x48065").ctrl(x48076).srcCtx("CellsPar.scala:54:43:cond2") // And(x48063,x48064)
    val x48066 = OpDef(op=FixSra, inputs=List(x48061, Const(2))).name("x48066").ctrl(x48076).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x48061,Const(2))
    val x48067 = OpDef(op=FixAdd, inputs=List(x48066, Const(0.375))).name("x48067").ctrl(x48076).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x48066,Const(0.375))
    val x48068 = OpDef(op=MuxOp, inputs=List(x48065, x48067, x48061)).name("x48068").ctrl(x48076).srcCtx("CellsPar.scala:58:20:body2") // Mux(x48065,x48067,x48061)
    val x48069 = OpDef(op=MuxOp, inputs=List(x48062, Const(1.0), x48068)).name("x48069").ctrl(x48076).srcCtx("CellsPar.scala:60:20:absre") // Mux(x48062,Const(1),x48068)
    val x48070 = OpDef(op=FixNeg, inputs=List(x48069)).name("x48070").ctrl(x48076).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x48069)
    val x48071 = OpDef(op=FixLt, inputs=List(x48060, Const(0.0))).name("x48071").ctrl(x48076).srcCtx("CellsPar.scala:63:22") // FixLt(x48060,Const(0))
    val x48072 = OpDef(op=MuxOp, inputs=List(x48071, x48070, x48069)).name("x48072").ctrl(x48076).srcCtx("CellsPar.scala:63:17:re") // Mux(x48071,x48070,x48069)
    val x48073 = x48072 // FixConvert(x48072,TRUE,_8,_24) (Same Type. No op)
    val x48074 = OpDef(op=MuxOp, inputs=List(x48057, x48073, x48056)).name("x48074").ctrl(x48076).srcCtx("CellsPar.scala:218:43") // Mux(x48057,x48073,x48056)
    val x48075 = StoreBanks(List(x45562_d0_b0, x45562_d1_b0), List(b31599, x48045), x48074).name("x48075").ctrl(x48076).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45562,ArrayBuffer(Const(1), Const(512)),List(b31599, x48045),Const(0),x48074,x48052)
    val x48080 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x48080").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x48081 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x48081").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x48082 = CounterChain(List(x48081,x48080)).name("x48082").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x48081, x48080))
    val x48188 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48082).name("x48188").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x48082,Block(Const(())),List(List(b31674), List(b31675)),List(List(b31676), List(b31677)))
    val b31674 = CounterIter(x48081, Some(0)).name("b31674").ctrl(x48188) // b31674
    val b31676 = Const(true).name("b31676").ctrl(x48188) // b31676
    val b31675 = CounterIter(x48080, Some(0)).name("b31675").ctrl(x48188) // b31675
    val b31677 = Const(true).name("b31677").ctrl(x48188) // b31677
    val x48083_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x48083_d0_b0").ctrl(x48188).srcCtx("CellsPar.scala:191:33:tileKernel") // x48083 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x48083_d0_b0) = false
    bufferDepthOf(x48083_d0_b0) = 2
    val x48086 = UnitController(style=SeqPipe, level=InnerControl).name("x48086").ctrl(x48188).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31676, b31677, b30254),Block(Const(())))
    val x48084 = OpDef(op=FixAdd, inputs=List(b31674, Const(16))).name("x48084").ctrl(x48086).srcCtx("CellsPar.scala:192:36") // FixAdd(b31674,Const(16))
    val x48085 = OpDef(op=FixAdd, inputs=List(b31675, Const(16))).name("x48085").ctrl(x48086).srcCtx("CellsPar.scala:192:45") // FixAdd(b31675,Const(16))
    val x48087 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48087").ctrl(x48188).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48088 = CounterChain(List(x48087)).name("x48088").ctrl(x48188).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x48087))
    val x48117 = LoopController(style=StreamPipe, level=OuterControl, cchain=x48088).name("x48117").ctrl(x48188).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31676, b31677, b30254),x48088,Block(Const(())),List(List(b31684)),List(List(b31685)))
    val b31684 = CounterIter(x48087, Some(0)).name("b31684").ctrl(x48117) // b31684
    val b31685 = Const(true).name("b31685").ctrl(x48117) // b31685
    val b48520 = StreamOut(field="offset").name("b48520").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // x48089 = StreamOutNew(BurstCmdBus)
    isAccum(b48520) = false
    bufferDepthOf(b48520) = 1
    val b48521 = StreamOut(field="size").name("b48521").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // x48089 = StreamOutNew(BurstCmdBus)
    isAccum(b48521) = false
    bufferDepthOf(b48521) = 1
    val x48090 = StreamIn(field="data").name("x48090").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // x48090 = StreamInNew(BurstDataBus())
    isAccum(x48090) = false
    bufferDepthOf(x48090) = 1
    val x48105 = UnitController(style=SeqPipe, level=InnerControl).name("x48105").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31685, b31676, b31677, b30254),Block(x48104))
    val x48091 = OpDef(op=FixAdd, inputs=List(b31674, b31684)).name("x48091").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // FixAdd(b31674,b31684)
    val x48092 = x48091 // FixConvert(x48091,TRUE,_32,_0) (Same Type. No op)
    val x48093 = OpDef(op=FixSla, inputs=List(x48092, Const(9))).name("x48093").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // FixLsh(x48092,Const(9))
    val x48094 = b31675 // FixConvert(b31675,TRUE,_32,_0) (Same Type. No op)
    val x48095 = OpDef(op=FixAdd, inputs=List(x48093, x48094)).name("x48095").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // FixAdd(x48093,x48094)
    val x48096 = OpDef(op=FixSla, inputs=List(x48095, Const(2))).name("x48096").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // FixLsh(x48095,Const(2))
    val x48097 = x48096 // FixConvert(x48096,TRUE,_64,_0)
    val x48098 = DramAddress(x45483).name("x48098").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45483)
    val x48100_x48099 = OpDef(op=FixAdd, inputs=List(x48097, x48098)).name("x48100_x48099").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // FixAdd(x48097,x48098)
    // x48100 = SimpleStruct(ArrayBuffer((offset,x48099), (size,Const(64)), (isLoad,Const(true))))
    val x48101 = OpDef(op=BitAnd, inputs=List(b31685, b31676)).name("x48101").ctrl(x48105).srcCtx("UnrollingBase.scala:28:66") // And(b31685,b31676)
    val x48102 = OpDef(op=BitAnd, inputs=List(b31677, b30254)).name("x48102").ctrl(x48105).srcCtx("UnrollingBase.scala:28:66") // And(b31677,b30254)
    val x48103 = OpDef(op=BitAnd, inputs=List(x48101, x48102)).name("x48103").ctrl(x48105).srcCtx("UnrollingBase.scala:28:66") // And(x48101,x48102)
    val x48104_b48522_b48520 = WriteMem(b48520, x48100_x48099).name("x48104_b48522_b48520").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // StreamWrite(x48089,x48100,x48103)
    val x48104_b48523_b48521 = WriteMem(b48521, Const(64)).name("x48104_b48523_b48521").ctrl(x48105).srcCtx("CellsPar.scala:192:20") // StreamWrite(x48089,x48100,x48103)
    val x48106 = FringeDenseLoad(dram=List(x45483), cmdStream=List(b48520, b48521), dataStream=List(x48090)).name("x48106").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45483,x48089,x48090)
    val x48107 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48107").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48108 = CounterChain(List(x48107)).name("x48108").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x48107))
    val x48116 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48108).name("x48116").ctrl(x48117).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31685, b31676, b31677, b30254),x48108,Block(Const(())),List(List(b31706)),List(List(b31707)))
    val b31706 = CounterIter(x48107, None).name("b31706").ctrl(x48116) // b31706
    val b31707 = Const(true).name("b31707").ctrl(x48116) // b31707
    val x48109 = OpDef(op=BitAnd, inputs=List(b31707, b31685)).name("x48109").ctrl(x48116).srcCtx("UnrollingBase.scala:28:66") // And(b31707,b31685)
    val x48110 = OpDef(op=BitAnd, inputs=List(b31676, b31677)).name("x48110").ctrl(x48116).srcCtx("UnrollingBase.scala:28:66") // And(b31676,b31677)
    val x48111 = OpDef(op=BitAnd, inputs=List(x48109, x48110)).name("x48111").ctrl(x48116).srcCtx("UnrollingBase.scala:28:66") // And(x48109,x48110)
    val x48112 = OpDef(op=BitAnd, inputs=List(x48111, b30254)).name("x48112").ctrl(x48116).srcCtx("UnrollingBase.scala:28:66") // And(x48111,b30254)
    val x48113_x48113 = ReadMem(x48090).name("x48113_x48113").ctrl(x48116).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x48090,List(x48112))
    val x48114_x48114 = x48113_x48113 // x48114 = VectorApply(x48113,0)
    val x48115 = StoreBanks(List(x48083_d0_b0), List(b31684, b31706), x48114_x48114).name("x48115").ctrl(x48116).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x48083,List(List(b31684, b31706)),List(x48114),List(x48112))
    val x48118 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x48118").ctrl(x48188).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x48119 = CounterChain(List(x48118)).name("x48119").ctrl(x48188).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x48118))
    val x48187 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48119).name("x48187").ctrl(x48188).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31676, b31677, b30254),x48119,Block(Const(())),List(List(b31719)),List(List(b31720)))
    val b31719 = CounterIter(x48118, Some(0)).name("b31719").ctrl(x48187) // b31719
    val b31720 = Const(true).name("b31720").ctrl(x48187) // b31720
    val x48120 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48120").ctrl(x48187).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48121 = CounterChain(List(x48120)).name("x48121").ctrl(x48187).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x48120))
    val x48186 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48121).name("x48186").ctrl(x48187).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31720, b31676, b31677, b30254),x48121,Block(Const(())),List(List(b31723)),List(List(b31724)))
    val b31723 = CounterIter(x48120, Some(0)).name("b31723").ctrl(x48186) // b31723
    val b31724 = Const(true).name("b31724").ctrl(x48186) // b31724
    val x48122_d0 = Reg(init=Some(0.0)).name("x48122_d0").ctrl(x48186).srcCtx("CellsPar.scala:195:34:prod") // x48122 = RegNew(Const(0))
    isAccum(x48122_d0) = false
    bufferDepthOf(x48122_d0) = 2
    val x48122_d1 = Reg(init=Some(0.0)).name("x48122_d1").ctrl(x48186).srcCtx("CellsPar.scala:195:34:prod") // x48122 = RegNew(Const(0))
    isAccum(x48122_d1) = true
    bufferDepthOf(x48122_d1) = 1
    val x48123 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48123").ctrl(x48186).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48124 = CounterChain(List(x48123)).name("x48124").ctrl(x48186).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x48123))
    val x48150 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48124).name("x48150").ctrl(x48186).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31724, b31720, b31676, b31677, b30254),x48124,x48122,Block((x48122) => Const(())),List(List(b31728)),List(List(b31729)))
    val b31728 = CounterIter(x48123, None).name("b31728").ctrl(x48150) // b31728
    val b31729 = Const(true).name("b31729").ctrl(x48150) // b31729
    val x48125 = OpDef(op=FixAdd, inputs=List(b31674, b31728)).name("x48125").ctrl(x48150).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31674,b31728)
    val x48126 = OpDef(op=BitAnd, inputs=List(b31729, b31724)).name("x48126").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(b31729,b31724)
    val x48127 = OpDef(op=BitAnd, inputs=List(b31720, b31676)).name("x48127").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(b31720,b31676)
    val x48128 = OpDef(op=BitAnd, inputs=List(b31677, b30254)).name("x48128").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(b31677,b30254)
    val x48129 = OpDef(op=BitAnd, inputs=List(x48126, x48127)).name("x48129").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(x48126,x48127)
    val x48130 = OpDef(op=BitAnd, inputs=List(x48129, x48128)).name("x48130").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(x48129,x48128)
    val x48131 = LoadBanks(List(x48083_d0_b0), List(b31728, b31723)).name("x48131").ctrl(x48150).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x48083,List(List(b31728, b31723)),List(x48130))
    val x48132 = x48131 // x48132 = VectorApply(x48131,0)
    val x48133 = LoadBanks(List(x45552_d2_b0), List(b31719, x48125)).name("x48133").ctrl(x48150).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45552,List(List(b31719, x48125)),List(x48130))
    val x48134 = x48133 // x48134 = VectorApply(x48133,0)
    val x48135 = OpDef(op=FixMul, inputs=List(x48134, x48132)).name("x48135").ctrl(x48150).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x48134,x48132)
    val x48136 = OpDef(op=FixSub, inputs=List(x48125, Const(512))).name("x48136").ctrl(x48150).srcCtx("CellsPar.scala:205:51") // FixSub(x48125,Const(512))
    val x48137 = LoadBanks(List(x45559_d2_b0), List(b31719, x48136)).name("x48137").ctrl(x48150).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45559,List(List(b31719, x48136)),List(x48130))
    val x48138 = x48137 // x48138 = VectorApply(x48137,0)
    val x48139 = OpDef(op=FixMul, inputs=List(x48138, x48132)).name("x48139").ctrl(x48150).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x48138,x48132)
    val x48140 = OpDef(op=FixLt, inputs=List(x48125, Const(512))).name("x48140").ctrl(x48150).srcCtx("CellsPar.scala:206:38") // FixLt(x48125,Const(512))
    val x48141 = OpDef(op=MuxOp, inputs=List(x48140, x48135, x48139)).name("x48141").ctrl(x48150).srcCtx("CellsPar.scala:206:18") // Mux(x48140,x48135,x48139)
    val x48142 = ReadMem(x48122_d1).name("x48142").ctrl(x48150).srcCtx("CellsPar.scala:207:15") // RegRead(x48122)
    val x48143 = OpDef(op=FixEql, inputs=List(b31728, Const(0))).name("x48143").ctrl(x48150).srcCtx("CellsPar.scala:207:15") // FixEql(b31728,Const(0))
    val x48144 = ReduceAccumOp(op=FixAdd, input=x48141, accum=x48142).name("x48144").ctrl(x48150).srcCtx("CellsPar.scala:207:17") // FixAdd(x48141,x48142)
    val x48145 = OpDef(op=BitAnd, inputs=List(b31724, b31720)).name("x48145").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(b31724,b31720)
    val x48146 = OpDef(op=BitAnd, inputs=List(b31676, b31677)).name("x48146").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(b31676,b31677)
    val x48147 = OpDef(op=BitAnd, inputs=List(x48145, x48146)).name("x48147").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(x48145,x48146)
    val x48148 = OpDef(op=BitAnd, inputs=List(x48147, b30254)).name("x48148").ctrl(x48150).srcCtx("UnrollingBase.scala:28:66") // And(x48147,b30254)
    val x48149_x48122_d0 = WriteMem(x48122_d0, x48144).name("x48149_x48122_d0").ctrl(x48150).srcCtx("CellsPar.scala:207:15") // RegWrite(x48122,x48144,x48148)
    val x48149_x48122_d1 = WriteMem(x48122_d1, x48144).name("x48149_x48122_d1").ctrl(x48150).srcCtx("CellsPar.scala:207:15") // RegWrite(x48122,x48144,x48148)
    val x48185 = UnitController(style=SeqPipe, level=InnerControl).name("x48185").ctrl(x48186).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31724, b31720, b31676, b31677, b30254),Block(Const(())))
    val x48151 = OpDef(op=FixAdd, inputs=List(b31675, b31723)).name("x48151").ctrl(x48185).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31675,b31723)
    val x48152 = ReadMem(x48122_d0).name("x48152").ctrl(x48185).srcCtx("CellsPar.scala:210:28") // RegRead(x48122)
    val x48153 = OpDef(op=FixEql, inputs=List(b31674, Const(0))).name("x48153").ctrl(x48185).srcCtx("CellsPar.scala:210:42") // FixEql(b31674,Const(0))
    val x48154 = OpDef(op=FixAdd, inputs=List(x48151, Const(1024))).name("x48154").ctrl(x48185).srcCtx("CellsPar.scala:210:66") // FixAdd(x48151,Const(1024))
    val x48155 = OpDef(op=BitAnd, inputs=List(b31724, b31720)).name("x48155").ctrl(x48185).srcCtx("UnrollingBase.scala:28:66") // And(b31724,b31720)
    val x48156 = OpDef(op=BitAnd, inputs=List(b31676, b31677)).name("x48156").ctrl(x48185).srcCtx("UnrollingBase.scala:28:66") // And(b31676,b31677)
    val x48157 = OpDef(op=BitAnd, inputs=List(x48155, x48156)).name("x48157").ctrl(x48185).srcCtx("UnrollingBase.scala:28:66") // And(x48155,x48156)
    val x48158 = OpDef(op=BitAnd, inputs=List(x48157, b30254)).name("x48158").ctrl(x48185).srcCtx("UnrollingBase.scala:28:66") // And(x48157,b30254)
    val x48159 = LoadBanks(List(x45565_d1_b0), List(Const(0), x48154)).name("x48159").ctrl(x48185).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x48154),Const(0),x48158)
    val x48160 = LoadBanks(List(x45563_d1_b0), List(b31719, x48151)).name("x48160").ctrl(x48185).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45563,ArrayBuffer(Const(1), Const(512)),List(b31719, x48151),Const(0),x48158)
    val x48161 = OpDef(op=MuxOp, inputs=List(x48153, x48159, x48160)).name("x48161").ctrl(x48185).srcCtx("CellsPar.scala:210:39") // Mux(x48153,x48159,x48160)
    val x48162 = OpDef(op=FixAdd, inputs=List(x48152, x48161)).name("x48162").ctrl(x48185).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x48152,x48161)
    val x48163 = OpDef(op=FixLeq, inputs=List(Const(1008), b31674)).name("x48163").ctrl(x48185).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31674)
    // x48164 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x48164_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).name("x48164_int1").ctrl(x48185).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x48164_int2 = OpDef(op=FixSla, inputs=List(x48164_int1, Const(24))).name("x48164_int2").ctrl(x48185).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x48164_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).name("x48164_frac1").ctrl(x48185).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x48164_frac2 = OpDef(op=FixSla, inputs=List(x48164_frac1, Const(24))).name("x48164_frac2").ctrl(x48185).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    val x48164 = OpDef(op=BitOr, inputs=List(x48164_int2, x48164_frac2)).name("x48164").ctrl(x48185).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(1),TRUE,_8,_24)
    // }
    val x48165 = OpDef(op=FixAdd, inputs=List(x48162, x48164)).name("x48165").ctrl(x48185).srcCtx("CellsPar.scala:218:87") // FixAdd(x48162,x48164)
    val x48166 = OpDef(op=FixSra, inputs=List(x48165, Const(1))).name("x48166").ctrl(x48185).srcCtx("CellsPar.scala:29:22") // FixRsh(x48165,Const(1))
    val x48167 = x48166 // FixConvert(x48166,TRUE,_8,_24) (Same Type. No op)
    val x48168 = OpDef(op=FixAbs, inputs=List(x48167)).name("x48168").ctrl(x48185).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x48167)
    val x48169 = OpDef(op=FixLt, inputs=List(Const(2.5), x48168)).name("x48169").ctrl(x48185).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x48168)
    val x48170 = OpDef(op=FixLt, inputs=List(Const(0.5), x48168)).name("x48170").ctrl(x48185).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x48168)
    val x48171 = OpDef(op=FixLeq, inputs=List(x48168, Const(2.5))).name("x48171").ctrl(x48185).srcCtx("CellsPar.scala:54:52") // FixLeq(x48168,Const(2.5))
    val x48172 = OpDef(op=BitAnd, inputs=List(x48170, x48171)).name("x48172").ctrl(x48185).srcCtx("CellsPar.scala:54:43:cond2") // And(x48170,x48171)
    val x48173 = OpDef(op=FixSra, inputs=List(x48168, Const(2))).name("x48173").ctrl(x48185).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x48168,Const(2))
    val x48174 = OpDef(op=FixAdd, inputs=List(x48173, Const(0.375))).name("x48174").ctrl(x48185).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x48173,Const(0.375))
    val x48175 = OpDef(op=MuxOp, inputs=List(x48172, x48174, x48168)).name("x48175").ctrl(x48185).srcCtx("CellsPar.scala:58:20:body2") // Mux(x48172,x48174,x48168)
    val x48176 = OpDef(op=MuxOp, inputs=List(x48169, Const(1.0), x48175)).name("x48176").ctrl(x48185).srcCtx("CellsPar.scala:60:20:absre") // Mux(x48169,Const(1),x48175)
    val x48177 = OpDef(op=FixNeg, inputs=List(x48176)).name("x48177").ctrl(x48185).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x48176)
    val x48178 = OpDef(op=FixLt, inputs=List(x48167, Const(0.0))).name("x48178").ctrl(x48185).srcCtx("CellsPar.scala:63:22") // FixLt(x48167,Const(0))
    val x48179 = OpDef(op=MuxOp, inputs=List(x48178, x48177, x48176)).name("x48179").ctrl(x48185).srcCtx("CellsPar.scala:63:17:re") // Mux(x48178,x48177,x48176)
    val x48180 = x48179 // FixConvert(x48179,TRUE,_8,_24) (Same Type. No op)
    val x48181 = OpDef(op=FixAdd, inputs=List(x48180, Const(1.0))).name("x48181").ctrl(x48185).srcCtx("CellsPar.scala:29:33") // FixAdd(x48180,Const(1))
    val x48182 = OpDef(op=FixSra, inputs=List(x48181, Const(1))).name("x48182").ctrl(x48185).srcCtx("CellsPar.scala:29:44") // FixRsh(x48181,Const(1))
    val x48183 = OpDef(op=MuxOp, inputs=List(x48163, x48182, x48162)).name("x48183").ctrl(x48185).srcCtx("CellsPar.scala:218:43") // Mux(x48163,x48182,x48162)
    val x48184 = StoreBanks(List(x45563_d0_b0, x45563_d1_b0), List(b31719, x48151), x48183).name("x48184").ctrl(x48185).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45563,ArrayBuffer(Const(1), Const(512)),List(b31719, x48151),Const(0),x48183,x48158)
    val x48189 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x48189").ctrl(x48337).srcCtx("CellsPar.scala:190:58") // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x48190 = Counter(min=Const(0), max=Const(1024), step=Const(16), par=1).name("x48190").ctrl(x48337).srcCtx("CellsPar.scala:190:33") // CounterNew(Const(0),Const(1024),Const(16),Const(1))
    val x48191 = CounterChain(List(x48190,x48189)).name("x48191").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // CounterChainNew(List(x48190, x48189))
    val x48297 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48191).name("x48297").ctrl(x48337).srcCtx("CellsPar.scala:190:72") // UnrolledForeach(List(b30254),x48191,Block(Const(())),List(List(b31797), List(b31798)),List(List(b31799), List(b31800)))
    val b31797 = CounterIter(x48190, Some(0)).name("b31797").ctrl(x48297) // b31797
    val b31799 = Const(true).name("b31799").ctrl(x48297) // b31799
    val b31798 = CounterIter(x48189, Some(0)).name("b31798").ctrl(x48297) // b31798
    val b31800 = Const(true).name("b31800").ctrl(x48297) // b31800
    val x48192_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x48192_d0_b0").ctrl(x48297).srcCtx("CellsPar.scala:191:33:tileKernel") // x48192 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x48192_d0_b0) = false
    bufferDepthOf(x48192_d0_b0) = 2
    val x48195 = UnitController(style=SeqPipe, level=InnerControl).name("x48195").ctrl(x48297).srcCtx("CellsPar.scala:190:72") // UnitPipe(List(b31799, b31800, b30254),Block(Const(())))
    val x48193 = OpDef(op=FixAdd, inputs=List(b31797, Const(16))).name("x48193").ctrl(x48195).srcCtx("CellsPar.scala:192:36") // FixAdd(b31797,Const(16))
    val x48194 = OpDef(op=FixAdd, inputs=List(b31798, Const(16))).name("x48194").ctrl(x48195).srcCtx("CellsPar.scala:192:45") // FixAdd(b31798,Const(16))
    val x48196 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48196").ctrl(x48297).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48197 = CounterChain(List(x48196)).name("x48197").ctrl(x48297).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x48196))
    val x48226 = LoopController(style=StreamPipe, level=OuterControl, cchain=x48197).name("x48226").ctrl(x48297).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31799, b31800, b30254),x48197,Block(Const(())),List(List(b31807)),List(List(b31808)))
    val b31807 = CounterIter(x48196, Some(0)).name("b31807").ctrl(x48226) // b31807
    val b31808 = Const(true).name("b31808").ctrl(x48226) // b31808
    val b48524 = StreamOut(field="offset").name("b48524").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // x48198 = StreamOutNew(BurstCmdBus)
    isAccum(b48524) = false
    bufferDepthOf(b48524) = 1
    val b48525 = StreamOut(field="size").name("b48525").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // x48198 = StreamOutNew(BurstCmdBus)
    isAccum(b48525) = false
    bufferDepthOf(b48525) = 1
    val x48199 = StreamIn(field="data").name("x48199").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // x48199 = StreamInNew(BurstDataBus())
    isAccum(x48199) = false
    bufferDepthOf(x48199) = 1
    val x48214 = UnitController(style=SeqPipe, level=InnerControl).name("x48214").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // UnitPipe(List(b31808, b31799, b31800, b30254),Block(x48213))
    val x48200 = OpDef(op=FixAdd, inputs=List(b31797, b31807)).name("x48200").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // FixAdd(b31797,b31807)
    val x48201 = x48200 // FixConvert(x48200,TRUE,_32,_0) (Same Type. No op)
    val x48202 = OpDef(op=FixSla, inputs=List(x48201, Const(9))).name("x48202").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // FixLsh(x48201,Const(9))
    val x48203 = b31798 // FixConvert(b31798,TRUE,_32,_0) (Same Type. No op)
    val x48204 = OpDef(op=FixAdd, inputs=List(x48202, x48203)).name("x48204").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // FixAdd(x48202,x48203)
    val x48205 = OpDef(op=FixSla, inputs=List(x48204, Const(2))).name("x48205").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // FixLsh(x48204,Const(2))
    val x48206 = x48205 // FixConvert(x48205,TRUE,_64,_0)
    val x48207 = DramAddress(x45484).name("x48207").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // GetDRAMAddress(x45484)
    val x48209_x48208 = OpDef(op=FixAdd, inputs=List(x48206, x48207)).name("x48209_x48208").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // FixAdd(x48206,x48207)
    // x48209 = SimpleStruct(ArrayBuffer((offset,x48208), (size,Const(64)), (isLoad,Const(true))))
    val x48210 = OpDef(op=BitAnd, inputs=List(b31808, b31799)).name("x48210").ctrl(x48214).srcCtx("UnrollingBase.scala:28:66") // And(b31808,b31799)
    val x48211 = OpDef(op=BitAnd, inputs=List(b31800, b30254)).name("x48211").ctrl(x48214).srcCtx("UnrollingBase.scala:28:66") // And(b31800,b30254)
    val x48212 = OpDef(op=BitAnd, inputs=List(x48210, x48211)).name("x48212").ctrl(x48214).srcCtx("UnrollingBase.scala:28:66") // And(x48210,x48211)
    val x48213_b48526_b48524 = WriteMem(b48524, x48209_x48208).name("x48213_b48526_b48524").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // StreamWrite(x48198,x48209,x48212)
    val x48213_b48527_b48525 = WriteMem(b48525, Const(64)).name("x48213_b48527_b48525").ctrl(x48214).srcCtx("CellsPar.scala:192:20") // StreamWrite(x48198,x48209,x48212)
    val x48215 = FringeDenseLoad(dram=List(x45484), cmdStream=List(b48524, b48525), dataStream=List(x48199)).name("x48215").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // FringeDenseLoad(x45484,x48198,x48199)
    val x48216 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48216").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48217 = CounterChain(List(x48216)).name("x48217").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // CounterChainNew(List(x48216))
    val x48225 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48217).name("x48225").ctrl(x48226).srcCtx("CellsPar.scala:192:20") // UnrolledForeach(List(b31808, b31799, b31800, b30254),x48217,Block(Const(())),List(List(b31829)),List(List(b31830)))
    val b31829 = CounterIter(x48216, None).name("b31829").ctrl(x48225) // b31829
    val b31830 = Const(true).name("b31830").ctrl(x48225) // b31830
    val x48218 = OpDef(op=BitAnd, inputs=List(b31830, b31808)).name("x48218").ctrl(x48225).srcCtx("UnrollingBase.scala:28:66") // And(b31830,b31808)
    val x48219 = OpDef(op=BitAnd, inputs=List(b31799, b31800)).name("x48219").ctrl(x48225).srcCtx("UnrollingBase.scala:28:66") // And(b31799,b31800)
    val x48220 = OpDef(op=BitAnd, inputs=List(x48218, x48219)).name("x48220").ctrl(x48225).srcCtx("UnrollingBase.scala:28:66") // And(x48218,x48219)
    val x48221 = OpDef(op=BitAnd, inputs=List(x48220, b30254)).name("x48221").ctrl(x48225).srcCtx("UnrollingBase.scala:28:66") // And(x48220,b30254)
    val x48222_x48222 = ReadMem(x48199).name("x48222_x48222").ctrl(x48225).srcCtx("CellsPar.scala:192:20") // ParStreamRead(x48199,List(x48221))
    val x48223_x48223 = x48222_x48222 // x48223 = VectorApply(x48222,0)
    val x48224 = StoreBanks(List(x48192_d0_b0), List(b31807, b31829), x48223_x48223).name("x48224").ctrl(x48225).srcCtx("CellsPar.scala:192:20") // ParSRAMStore(x48192,List(List(b31807, b31829)),List(x48223),List(x48221))
    val x48227 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x48227").ctrl(x48297).srcCtx("CellsPar.scala:193:34") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x48228 = CounterChain(List(x48227)).name("x48228").ctrl(x48297).srcCtx("CellsPar.scala:193:46") // CounterChainNew(List(x48227))
    val x48296 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48228).name("x48296").ctrl(x48297).srcCtx("CellsPar.scala:193:46") // UnrolledForeach(List(b31799, b31800, b30254),x48228,Block(Const(())),List(List(b31842)),List(List(b31843)))
    val b31842 = CounterIter(x48227, Some(0)).name("b31842").ctrl(x48296) // b31842
    val b31843 = Const(true).name("b31843").ctrl(x48296) // b31843
    val x48229 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48229").ctrl(x48296).srcCtx("CellsPar.scala:194:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48230 = CounterChain(List(x48229)).name("x48230").ctrl(x48296).srcCtx("CellsPar.scala:194:42") // CounterChainNew(List(x48229))
    val x48295 = LoopController(style=MetaPipe, level=OuterControl, cchain=x48230).name("x48295").ctrl(x48296).srcCtx("CellsPar.scala:194:42") // UnrolledForeach(List(b31843, b31799, b31800, b30254),x48230,Block(Const(())),List(List(b31846)),List(List(b31847)))
    val b31846 = CounterIter(x48229, Some(0)).name("b31846").ctrl(x48295) // b31846
    val b31847 = Const(true).name("b31847").ctrl(x48295) // b31847
    val x48231_d0 = Reg(init=Some(0.0)).name("x48231_d0").ctrl(x48295).srcCtx("CellsPar.scala:195:34:prod") // x48231 = RegNew(Const(0))
    isAccum(x48231_d0) = false
    bufferDepthOf(x48231_d0) = 2
    val x48231_d1 = Reg(init=Some(0.0)).name("x48231_d1").ctrl(x48295).srcCtx("CellsPar.scala:195:34:prod") // x48231 = RegNew(Const(0))
    isAccum(x48231_d1) = true
    bufferDepthOf(x48231_d1) = 1
    val x48232 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x48232").ctrl(x48295).srcCtx("CellsPar.scala:195:43") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x48233 = CounterChain(List(x48232)).name("x48233").ctrl(x48295).srcCtx("CellsPar.scala:207:15") // CounterChainNew(List(x48232))
    val x48259 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48233).name("x48259").ctrl(x48295).srcCtx("CellsPar.scala:207:15") // UnrolledReduce(List(b31847, b31843, b31799, b31800, b30254),x48233,x48231,Block((x48231) => Const(())),List(List(b31851)),List(List(b31852)))
    val b31851 = CounterIter(x48232, None).name("b31851").ctrl(x48259) // b31851
    val b31852 = Const(true).name("b31852").ctrl(x48259) // b31852
    val x48234 = OpDef(op=FixAdd, inputs=List(b31797, b31851)).name("x48234").ctrl(x48259).srcCtx("CellsPar.scala:196:42:reduce_size_offset") // FixAdd(b31797,b31851)
    val x48235 = OpDef(op=BitAnd, inputs=List(b31852, b31847)).name("x48235").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(b31852,b31847)
    val x48236 = OpDef(op=BitAnd, inputs=List(b31843, b31799)).name("x48236").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(b31843,b31799)
    val x48237 = OpDef(op=BitAnd, inputs=List(b31800, b30254)).name("x48237").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(b31800,b30254)
    val x48238 = OpDef(op=BitAnd, inputs=List(x48235, x48236)).name("x48238").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(x48235,x48236)
    val x48239 = OpDef(op=BitAnd, inputs=List(x48238, x48237)).name("x48239").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(x48238,x48237)
    val x48240 = LoadBanks(List(x48192_d0_b0), List(b31851, b31846)).name("x48240").ctrl(x48259).srcCtx("CellsPar.scala:203:34:tk") // ParSRAMLoad(x48192,List(List(b31851, b31846)),List(x48239))
    val x48241 = x48240 // x48241 = VectorApply(x48240,0)
    val x48242 = LoadBanks(List(x45552_d1_b0), List(b31842, x48234)).name("x48242").ctrl(x48259).srcCtx("CellsPar.scala:204:28") // ParSRAMLoad(x45552,List(List(b31842, x48234)),List(x48239))
    val x48243 = x48242 // x48243 = VectorApply(x48242,0)
    val x48244 = OpDef(op=FixMul, inputs=List(x48243, x48241)).name("x48244").ctrl(x48259).srcCtx("CellsPar.scala:204:52:tempA") // FixMul(x48243,x48241)
    val x48245 = OpDef(op=FixSub, inputs=List(x48234, Const(512))).name("x48245").ctrl(x48259).srcCtx("CellsPar.scala:205:51") // FixSub(x48234,Const(512))
    val x48246 = LoadBanks(List(x45559_d1_b0), List(b31842, x48245)).name("x48246").ctrl(x48259).srcCtx("CellsPar.scala:205:28") // ParSRAMLoad(x45559,List(List(b31842, x48245)),List(x48239))
    val x48247 = x48246 // x48247 = VectorApply(x48246,0)
    val x48248 = OpDef(op=FixMul, inputs=List(x48247, x48241)).name("x48248").ctrl(x48259).srcCtx("CellsPar.scala:205:67:tempB") // FixMul(x48247,x48241)
    val x48249 = OpDef(op=FixLt, inputs=List(x48234, Const(512))).name("x48249").ctrl(x48259).srcCtx("CellsPar.scala:206:38") // FixLt(x48234,Const(512))
    val x48250 = OpDef(op=MuxOp, inputs=List(x48249, x48244, x48248)).name("x48250").ctrl(x48259).srcCtx("CellsPar.scala:206:18") // Mux(x48249,x48244,x48248)
    val x48251 = ReadMem(x48231_d1).name("x48251").ctrl(x48259).srcCtx("CellsPar.scala:207:15") // RegRead(x48231)
    val x48252 = OpDef(op=FixEql, inputs=List(b31851, Const(0))).name("x48252").ctrl(x48259).srcCtx("CellsPar.scala:207:15") // FixEql(b31851,Const(0))
    val x48253 = ReduceAccumOp(op=FixAdd, input=x48250, accum=x48251).name("x48253").ctrl(x48259).srcCtx("CellsPar.scala:207:17") // FixAdd(x48250,x48251)
    val x48254 = OpDef(op=BitAnd, inputs=List(b31847, b31843)).name("x48254").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(b31847,b31843)
    val x48255 = OpDef(op=BitAnd, inputs=List(b31799, b31800)).name("x48255").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(b31799,b31800)
    val x48256 = OpDef(op=BitAnd, inputs=List(x48254, x48255)).name("x48256").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(x48254,x48255)
    val x48257 = OpDef(op=BitAnd, inputs=List(x48256, b30254)).name("x48257").ctrl(x48259).srcCtx("UnrollingBase.scala:28:66") // And(x48256,b30254)
    val x48258_x48231_d0 = WriteMem(x48231_d0, x48253).name("x48258_x48231_d0").ctrl(x48259).srcCtx("CellsPar.scala:207:15") // RegWrite(x48231,x48253,x48257)
    val x48258_x48231_d1 = WriteMem(x48231_d1, x48253).name("x48258_x48231_d1").ctrl(x48259).srcCtx("CellsPar.scala:207:15") // RegWrite(x48231,x48253,x48257)
    val x48294 = UnitController(style=SeqPipe, level=InnerControl).name("x48294").ctrl(x48295).srcCtx("CellsPar.scala:194:42") // UnitPipe(List(b31847, b31843, b31799, b31800, b30254),Block(Const(())))
    val x48260 = OpDef(op=FixAdd, inputs=List(b31798, b31846)).name("x48260").ctrl(x48294).srcCtx("CellsPar.scala:209:31:colOffset") // FixAdd(b31798,b31846)
    val x48261 = ReadMem(x48231_d0).name("x48261").ctrl(x48294).srcCtx("CellsPar.scala:210:28") // RegRead(x48231)
    val x48262 = OpDef(op=FixEql, inputs=List(b31797, Const(0))).name("x48262").ctrl(x48294).srcCtx("CellsPar.scala:210:42") // FixEql(b31797,Const(0))
    def split5 = {
    val x48263 = OpDef(op=FixAdd, inputs=List(x48260, Const(1536))).name("x48263").ctrl(x48294).srcCtx("CellsPar.scala:210:66") // FixAdd(x48260,Const(1536))
    val x48264 = OpDef(op=BitAnd, inputs=List(b31847, b31843)).name("x48264").ctrl(x48294).srcCtx("UnrollingBase.scala:28:66") // And(b31847,b31843)
    val x48265 = OpDef(op=BitAnd, inputs=List(b31799, b31800)).name("x48265").ctrl(x48294).srcCtx("UnrollingBase.scala:28:66") // And(b31799,b31800)
    val x48266 = OpDef(op=BitAnd, inputs=List(x48264, x48265)).name("x48266").ctrl(x48294).srcCtx("UnrollingBase.scala:28:66") // And(x48264,x48265)
    val x48267 = OpDef(op=BitAnd, inputs=List(x48266, b30254)).name("x48267").ctrl(x48294).srcCtx("UnrollingBase.scala:28:66") // And(x48266,b30254)
    val x48268 = LoadBanks(List(x45565_d0_b0), List(Const(0), x48263)).name("x48268").ctrl(x48294).srcCtx("CellsPar.scala:210:52") // SRAMLoad(x45565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x48263),Const(0),x48267)
    val x48269 = LoadBanks(List(x45564_d1_b0), List(b31842, x48260)).name("x48269").ctrl(x48294).srcCtx("CellsPar.scala:210:91") // SRAMLoad(x45564,ArrayBuffer(Const(1), Const(512)),List(b31842, x48260),Const(0),x48267)
    val x48270 = OpDef(op=MuxOp, inputs=List(x48262, x48268, x48269)).name("x48270").ctrl(x48294).srcCtx("CellsPar.scala:210:39") // Mux(x48262,x48268,x48269)
    val x48271 = OpDef(op=FixAdd, inputs=List(x48261, x48270)).name("x48271").ctrl(x48294).srcCtx("CellsPar.scala:210:34:ele") // FixAdd(x48261,x48270)
    val x48272 = OpDef(op=FixLeq, inputs=List(Const(1008), b31797)).name("x48272").ctrl(x48294).srcCtx("CellsPar.scala:218:46") // FixLeq(Const(1008),b31797)
    // x48273 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x48273_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).name("x48273_int1").ctrl(x48294).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48273_int2 = OpDef(op=FixSla, inputs=List(x48273_int1, Const(24))).name("x48273_int2").ctrl(x48294).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48273_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).name("x48273_frac1").ctrl(x48294).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48273_frac2 = OpDef(op=FixSla, inputs=List(x48273_frac1, Const(24))).name("x48273_frac2").ctrl(x48294).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    val x48273 = OpDef(op=BitOr, inputs=List(x48273_int2, x48273_frac2)).name("x48273").ctrl(x48294).srcCtx("CellsPar.scala:218:102") // FixConvert(Const(0),TRUE,_8,_24)
    // }
    val x48274 = OpDef(op=FixAdd, inputs=List(x48271, x48273)).name("x48274").ctrl(x48294).srcCtx("CellsPar.scala:218:87") // FixAdd(x48271,x48273)
    val x48275 = OpDef(op=FixSra, inputs=List(x48274, Const(1))).name("x48275").ctrl(x48294).srcCtx("CellsPar.scala:29:22") // FixRsh(x48274,Const(1))
    val x48276 = x48275 // FixConvert(x48275,TRUE,_8,_24) (Same Type. No op)
    val x48277 = OpDef(op=FixAbs, inputs=List(x48276)).name("x48277").ctrl(x48294).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x48276)
    val x48278 = OpDef(op=FixLt, inputs=List(Const(2.5), x48277)).name("x48278").ctrl(x48294).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x48277)
    val x48279 = OpDef(op=FixLt, inputs=List(Const(0.5), x48277)).name("x48279").ctrl(x48294).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x48277)
    val x48280 = OpDef(op=FixLeq, inputs=List(x48277, Const(2.5))).name("x48280").ctrl(x48294).srcCtx("CellsPar.scala:54:52") // FixLeq(x48277,Const(2.5))
    val x48281 = OpDef(op=BitAnd, inputs=List(x48279, x48280)).name("x48281").ctrl(x48294).srcCtx("CellsPar.scala:54:43:cond2") // And(x48279,x48280)
    val x48282 = OpDef(op=FixSra, inputs=List(x48277, Const(2))).name("x48282").ctrl(x48294).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x48277,Const(2))
    val x48283 = OpDef(op=FixAdd, inputs=List(x48282, Const(0.375))).name("x48283").ctrl(x48294).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x48282,Const(0.375))
    val x48284 = OpDef(op=MuxOp, inputs=List(x48281, x48283, x48277)).name("x48284").ctrl(x48294).srcCtx("CellsPar.scala:58:20:body2") // Mux(x48281,x48283,x48277)
    val x48285 = OpDef(op=MuxOp, inputs=List(x48278, Const(1.0), x48284)).name("x48285").ctrl(x48294).srcCtx("CellsPar.scala:60:20:absre") // Mux(x48278,Const(1),x48284)
    val x48286 = OpDef(op=FixNeg, inputs=List(x48285)).name("x48286").ctrl(x48294).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x48285)
    val x48287 = OpDef(op=FixLt, inputs=List(x48276, Const(0.0))).name("x48287").ctrl(x48294).srcCtx("CellsPar.scala:63:22") // FixLt(x48276,Const(0))
    val x48288 = OpDef(op=MuxOp, inputs=List(x48287, x48286, x48285)).name("x48288").ctrl(x48294).srcCtx("CellsPar.scala:63:17:re") // Mux(x48287,x48286,x48285)
    val x48289 = x48288 // FixConvert(x48288,TRUE,_8,_24) (Same Type. No op)
    val x48290 = OpDef(op=FixAdd, inputs=List(x48289, Const(1.0))).name("x48290").ctrl(x48294).srcCtx("CellsPar.scala:29:33") // FixAdd(x48289,Const(1))
    val x48291 = OpDef(op=FixSra, inputs=List(x48290, Const(1))).name("x48291").ctrl(x48294).srcCtx("CellsPar.scala:29:44") // FixRsh(x48290,Const(1))
    val x48292 = OpDef(op=MuxOp, inputs=List(x48272, x48291, x48271)).name("x48292").ctrl(x48294).srcCtx("CellsPar.scala:218:43") // Mux(x48272,x48291,x48271)
    val x48293 = StoreBanks(List(x45564_d0_b0, x45564_d1_b0), List(b31842, x48260), x48292).name("x48293").ctrl(x48294).srcCtx("CellsPar.scala:218:38") // SRAMStore(x45564,ArrayBuffer(Const(1), Const(512)),List(b31842, x48260),Const(0),x48292,x48267)
    val x48298 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x48298").ctrl(x48337).srcCtx("CellsPar.scala:267:50") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x48299 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x48299").ctrl(x48337).srcCtx("CellsPar.scala:267:27") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x48300 = CounterChain(List(x48299,x48298)).name("x48300").ctrl(x48337).srcCtx("CellsPar.scala:267:62") // CounterChainNew(List(x48299, x48298))
    val x48336 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48300).name("x48336").ctrl(x48337).srcCtx("CellsPar.scala:267:62") // UnrolledForeach(List(b30254),x48300,Block(Const(())),List(List(b31921), List(b31922)),List(List(b31923), List(b31924)))
    val b31921 = CounterIter(x48299, Some(0)).name("b31921").ctrl(x48336) // b31921
    val b31923 = Const(true).name("b31923").ctrl(x48336) // b31923
    val b31922 = CounterIter(x48298, None).name("b31922").ctrl(x48336) // b31922
    val b31924 = Const(true).name("b31924").ctrl(x48336) // b31924
    val x48301 = OpDef(op=BitAnd, inputs=List(b31923, b31924)).name("x48301").ctrl(x48336).srcCtx("UnrollingBase.scala:28:66") // And(b31923,b31924)
    val x48302 = OpDef(op=BitAnd, inputs=List(x48301, b30254)).name("x48302").ctrl(x48336).srcCtx("UnrollingBase.scala:28:66") // And(x48301,b30254)
    val x48303 = LoadBanks(List(x45560_d1_b0), List(b31921, b31922)).name("x48303").ctrl(x48336).srcCtx("CellsPar.scala:268:22") // ParSRAMLoad(x45560,List(List(b31921, b31922)),List(x48302))
    val x48304 = x48303 // x48304 = VectorApply(x48303,0)
    val x48305 = LoadBanks(List(x45563_d0_b0), List(b31921, b31922)).name("x48305").ctrl(x48336).srcCtx("CellsPar.scala:268:34") // ParSRAMLoad(x45563,List(List(b31921, b31922)),List(x48302))
    val x48306 = x48305 // x48306 = VectorApply(x48305,0)
    val x48307 = OpDef(op=FixMul, inputs=List(x48304, x48306)).name("x48307").ctrl(x48336).srcCtx("CellsPar.scala:268:28") // FixMul(x48304,x48306)
    val x48308 = LoadBanks(List(x45561_d0_b0), List(b31921, b31922)).name("x48308").ctrl(x48336).srcCtx("CellsPar.scala:268:46") // ParSRAMLoad(x45561,List(List(b31921, b31922)),List(x48302))
    val x48309 = x48308 // x48309 = VectorApply(x48308,0)
    val x48310 = LoadBanks(List(x45562_d0_b0), List(b31921, b31922)).name("x48310").ctrl(x48336).srcCtx("CellsPar.scala:268:59") // ParSRAMLoad(x45562,List(List(b31921, b31922)),List(x48302))
    val x48311 = x48310 // x48311 = VectorApply(x48310,0)
    val x48312 = OpDef(op=FixMul, inputs=List(x48309, x48311)).name("x48312").ctrl(x48336).srcCtx("CellsPar.scala:268:52") // FixMul(x48309,x48311)
    val x48313 = OpDef(op=FixAdd, inputs=List(x48307, x48312)).name("x48313").ctrl(x48336).srcCtx("CellsPar.scala:268:40:new_c") // FixAdd(x48307,x48312)
    val x48314 = x48313 // FixConvert(x48313,TRUE,_8,_24) (Same Type. No op)
    val x48315 = OpDef(op=FixAbs, inputs=List(x48314)).name("x48315").ctrl(x48336).srcCtx("CellsPar.scala:37:19:absp") // FixAbs(x48314)
    val x48316 = OpDef(op=FixLt, inputs=List(Const(2.5), x48315)).name("x48316").ctrl(x48336).srcCtx("CellsPar.scala:51:23:cond1") // FixLt(Const(2.5),x48315)
    val x48317 = OpDef(op=FixLt, inputs=List(Const(0.5), x48315)).name("x48317").ctrl(x48336).srcCtx("CellsPar.scala:54:35") // FixLt(Const(0.5),x48315)
    val x48318 = OpDef(op=FixLeq, inputs=List(x48315, Const(2.5))).name("x48318").ctrl(x48336).srcCtx("CellsPar.scala:54:52") // FixLeq(x48315,Const(2.5))
    val x48319 = OpDef(op=BitAnd, inputs=List(x48317, x48318)).name("x48319").ctrl(x48336).srcCtx("CellsPar.scala:54:43:cond2") // And(x48317,x48318)
    val x48320 = OpDef(op=FixSra, inputs=List(x48315, Const(2))).name("x48320").ctrl(x48336).srcCtx("CellsPar.scala:56:21:div4") // FixRsh(x48315,Const(2))
    val x48321 = OpDef(op=FixAdd, inputs=List(x48320, Const(0.375))).name("x48321").ctrl(x48336).srcCtx("CellsPar.scala:57:27:div4Offset") // FixAdd(x48320,Const(0.375))
    val x48322 = OpDef(op=MuxOp, inputs=List(x48319, x48321, x48315)).name("x48322").ctrl(x48336).srcCtx("CellsPar.scala:58:20:body2") // Mux(x48319,x48321,x48315)
    val x48323 = OpDef(op=MuxOp, inputs=List(x48316, Const(1.0), x48322)).name("x48323").ctrl(x48336).srcCtx("CellsPar.scala:60:20:absre") // Mux(x48316,Const(1),x48322)
    val x48324 = OpDef(op=FixNeg, inputs=List(x48323)).name("x48324").ctrl(x48336).srcCtx("CellsPar.scala:62:29:mabre") // FixNeg(x48323)
    val x48325 = OpDef(op=FixLt, inputs=List(x48314, Const(0.0))).name("x48325").ctrl(x48336).srcCtx("CellsPar.scala:63:22") // FixLt(x48314,Const(0))
    val x48326 = OpDef(op=MuxOp, inputs=List(x48325, x48324, x48323)).name("x48326").ctrl(x48336).srcCtx("CellsPar.scala:63:17:re") // Mux(x48325,x48324,x48323)
    val x48327 = x48326 // FixConvert(x48326,TRUE,_8,_24) (Same Type. No op)
    val x48328 = LoadBanks(List(x45564_d0_b0), List(b31921, b31922)).name("x48328").ctrl(x48336).srcCtx("CellsPar.scala:275:47") // ParSRAMLoad(x45564,List(List(b31921, b31922)),List(x48302))
    val x48329 = x48328 // x48329 = VectorApply(x48328,0)
    val x48330 = OpDef(op=FixMul, inputs=List(x48327, x48329)).name("x48330").ctrl(x48336).srcCtx("CellsPar.scala:275:41:prod") // FixMul(x48327,x48329)
    val x48331 = LoadBanks(List(x45552_d0_b0), List(b31921, b31922)).name("x48331").ctrl(x48336).srcCtx("CellsPar.scala:276:43") // ParSRAMLoad(x45552,List(List(b31921, b31922)),List(x48302))
    val x48332 = x48331 // x48332 = VectorApply(x48331,0)
    val x48333 = OpDef(op=FixAdd, inputs=List(x48330, x48332)).name("x48333").ctrl(x48336).srcCtx("CellsPar.scala:276:40") // FixAdd(x48330,x48332)
    val x48334 = StoreBanks(List(x45559_d0_b0, x45559_d1_b0, x45559_d2_b0, x45559_d3_b0, x45559_d4_b0), List(b31921, b31922), x48330).name("x48334").ctrl(x48336).srcCtx("CellsPar.scala:276:17") // ParSRAMStore(x45559,List(List(b31921, b31922)),List(x48330),List(x48302))
    val x48335 = StoreBanks(List(x45560_d0_b0, x45560_d1_b0), List(b31921, b31922), x48313).name("x48335").ctrl(x48336).srcCtx("CellsPar.scala:277:16") // ParSRAMStore(x45560,List(List(b31921, b31922)),List(x48313),List(x48302))
    val x48338 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x48338").ctrl(x48394).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x48339 = CounterChain(List(x48338)).name("x48339").ctrl(x48394).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x48338))
    val x48365 = LoopController(style=StreamPipe, level=OuterControl, cchain=x48339).name("x48365").ctrl(x48394).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(Const(true)),x48339,Block(Const(())),List(List(b31964)),List(List(b31965)))
    val b31964 = CounterIter(x48338, Some(0)).name("b31964").ctrl(x48365) // b31964
    val b31965 = Const(true).name("b31965").ctrl(x48365) // b31965
    val b48528 = StreamOut(field="offset").name("b48528").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // x48340 = StreamOutNew(BurstCmdBus)
    isAccum(b48528) = false
    bufferDepthOf(b48528) = 1
    val b48529 = StreamOut(field="size").name("b48529").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // x48340 = StreamOutNew(BurstCmdBus)
    isAccum(b48529) = false
    bufferDepthOf(b48529) = 1
    val x48341 = StreamOut(field="data").name("x48341").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // x48341 = StreamOutNew(BurstFullDataBus())
    isAccum(x48341) = false
    bufferDepthOf(x48341) = 1
    val x48342 = StreamIn(field="ack").name("x48342").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // x48342 = StreamInNew(BurstAckBus)
    isAccum(x48342) = false
    bufferDepthOf(x48342) = 1
    val x48353 = UnitController(style=SeqPipe, level=InnerControl).name("x48353").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b31965),Block(x48352))
    val x48343 = b31964 // FixConvert(b31964,TRUE,_32,_0) (Same Type. No op)
    val x48344 = OpDef(op=FixSla, inputs=List(x48343, Const(9))).name("x48344").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // FixLsh(x48343,Const(9))
    val x48345 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x48346 = OpDef(op=FixAdd, inputs=List(x48344, x48345)).name("x48346").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // FixAdd(x48344,x48345)
    val x48347 = OpDef(op=FixSla, inputs=List(x48346, Const(2))).name("x48347").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // FixLsh(x48346,Const(2))
    val x48348 = x48347 // FixConvert(x48347,TRUE,_64,_0)
    val x48349 = DramAddress(x45453).name("x48349").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // GetDRAMAddress(x45453)
    val x48351_x48350 = OpDef(op=FixAdd, inputs=List(x48348, x48349)).name("x48351_x48350").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // FixAdd(x48348,x48349)
    // x48351 = SimpleStruct(ArrayBuffer((offset,x48350), (size,Const(2048)), (isLoad,Const(false))))
    val x48352_b48530_b48528 = WriteMem(b48528, x48351_x48350).name("x48352_b48530_b48528").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // StreamWrite(x48340,x48351,b31965)
    val x48352_b48531_b48529 = WriteMem(b48529, Const(2048)).name("x48352_b48531_b48529").ctrl(x48353).srcCtx("CellsPar.scala:175:46") // StreamWrite(x48340,x48351,b31965)
    val x48354 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x48354").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x48355 = CounterChain(List(x48354)).name("x48355").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // CounterChainNew(List(x48354))
    val x48361 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48355).name("x48361").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // UnrolledForeach(List(b31965),x48355,Block(Const(())),List(List(b31982)),List(List(b31983)))
    val b31982 = CounterIter(x48354, None).name("b31982").ctrl(x48361) // b31982
    val b31983 = Const(true).name("b31983").ctrl(x48361) // b31983
    val x48356 = OpDef(op=BitAnd, inputs=List(b31983, b31965)).name("x48356").ctrl(x48361).srcCtx("UnrollingBase.scala:28:66") // And(b31983,b31965)
    val x48357 = LoadBanks(List(x45560_d0_b0), List(b31964, b31982)).name("x48357").ctrl(x48361).srcCtx("CellsPar.scala:175:46") // ParSRAMLoad(x45560,List(List(b31964, b31982)),List(x48356))
    val x48359_x48358 = x48357 // x48358 = VectorApply(x48357,0)
    // x48359 = SimpleStruct(ArrayBuffer((_1,x48358), (_2,Const(true))))
    val x48360_x48360_x48341 = WriteMem(x48341, x48359_x48358).name("x48360_x48360_x48341").ctrl(x48361).srcCtx("CellsPar.scala:175:46") // ParStreamWrite(x48341,List(x48359),List(x48356))
    val x48362 = FringeDenseStore(dram=List(x45453), cmdStream=List(b48528, b48529), dataStream=List(x48341), ackStream=List(x48342)).name("x48362").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // FringeDenseStore(x45453,x48340,x48341,x48342)
    val x48364 = UnitController(style=SeqPipe, level=InnerControl).name("x48364").ctrl(x48365).srcCtx("CellsPar.scala:175:46") // UnitPipe(List(b31965),Block(Const(())))
    val x48363_x48363 = ReadMem(x48342).name("x48363_x48363").ctrl(x48364).srcCtx("CellsPar.scala:175:46") // StreamRead(x48342,b31965)
    val x48366 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x48366").ctrl(x48394).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x48367 = CounterChain(List(x48366)).name("x48367").ctrl(x48394).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x48366))
    val x48393 = LoopController(style=StreamPipe, level=OuterControl, cchain=x48367).name("x48393").ctrl(x48394).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(Const(true)),x48367,Block(Const(())),List(List(b31996)),List(List(b31997)))
    val b31996 = CounterIter(x48366, Some(0)).name("b31996").ctrl(x48393) // b31996
    val b31997 = Const(true).name("b31997").ctrl(x48393) // b31997
    val b48532 = StreamOut(field="offset").name("b48532").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // x48368 = StreamOutNew(BurstCmdBus)
    isAccum(b48532) = false
    bufferDepthOf(b48532) = 1
    val b48533 = StreamOut(field="size").name("b48533").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // x48368 = StreamOutNew(BurstCmdBus)
    isAccum(b48533) = false
    bufferDepthOf(b48533) = 1
    val x48369 = StreamOut(field="data").name("x48369").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // x48369 = StreamOutNew(BurstFullDataBus())
    isAccum(x48369) = false
    bufferDepthOf(x48369) = 1
    val x48370 = StreamIn(field="ack").name("x48370").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // x48370 = StreamInNew(BurstAckBus)
    isAccum(x48370) = false
    bufferDepthOf(x48370) = 1
    val x48381 = UnitController(style=SeqPipe, level=InnerControl).name("x48381").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b31997),Block(x48380))
    val x48371 = b31996 // FixConvert(b31996,TRUE,_32,_0) (Same Type. No op)
    val x48372 = OpDef(op=FixSla, inputs=List(x48371, Const(9))).name("x48372").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // FixLsh(x48371,Const(9))
    val x48373 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x48374 = OpDef(op=FixAdd, inputs=List(x48372, x48373)).name("x48374").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // FixAdd(x48372,x48373)
    val x48375 = OpDef(op=FixSla, inputs=List(x48374, Const(2))).name("x48375").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // FixLsh(x48374,Const(2))
    val x48376 = x48375 // FixConvert(x48375,TRUE,_64,_0)
    val x48377 = DramAddress(x45455).name("x48377").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // GetDRAMAddress(x45455)
    val x48379_x48378 = OpDef(op=FixAdd, inputs=List(x48376, x48377)).name("x48379_x48378").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // FixAdd(x48376,x48377)
    // x48379 = SimpleStruct(ArrayBuffer((offset,x48378), (size,Const(2048)), (isLoad,Const(false))))
    val x48380_b48534_b48532 = WriteMem(b48532, x48379_x48378).name("x48380_b48534_b48532").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // StreamWrite(x48368,x48379,b31997)
    val x48380_b48535_b48533 = WriteMem(b48533, Const(2048)).name("x48380_b48535_b48533").ctrl(x48381).srcCtx("CellsPar.scala:176:46") // StreamWrite(x48368,x48379,b31997)
    val x48382 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x48382").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x48383 = CounterChain(List(x48382)).name("x48383").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // CounterChainNew(List(x48382))
    val x48389 = LoopController(style=InnerPipe, level=InnerControl, cchain=x48383).name("x48389").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // UnrolledForeach(List(b31997),x48383,Block(Const(())),List(List(b32014)),List(List(b32015)))
    val b32014 = CounterIter(x48382, None).name("b32014").ctrl(x48389) // b32014
    val b32015 = Const(true).name("b32015").ctrl(x48389) // b32015
    val x48384 = OpDef(op=BitAnd, inputs=List(b32015, b31997)).name("x48384").ctrl(x48389).srcCtx("UnrollingBase.scala:28:66") // And(b32015,b31997)
    val x48385 = LoadBanks(List(x45559_d0_b0), List(b31996, b32014)).name("x48385").ctrl(x48389).srcCtx("CellsPar.scala:176:46") // ParSRAMLoad(x45559,List(List(b31996, b32014)),List(x48384))
    val x48387_x48386 = x48385 // x48386 = VectorApply(x48385,0)
    // x48387 = SimpleStruct(ArrayBuffer((_1,x48386), (_2,Const(true))))
    val x48388_x48388_x48369 = WriteMem(x48369, x48387_x48386).name("x48388_x48388_x48369").ctrl(x48389).srcCtx("CellsPar.scala:176:46") // ParStreamWrite(x48369,List(x48387),List(x48384))
    val x48390 = FringeDenseStore(dram=List(x45455), cmdStream=List(b48532, b48533), dataStream=List(x48369), ackStream=List(x48370)).name("x48390").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // FringeDenseStore(x45455,x48368,x48369,x48370)
    val x48392 = UnitController(style=SeqPipe, level=InnerControl).name("x48392").ctrl(x48393).srcCtx("CellsPar.scala:176:46") // UnitPipe(List(b31997),Block(Const(())))
    val x48391_x48391 = ReadMem(x48370).name("x48391_x48391").ctrl(x48392).srcCtx("CellsPar.scala:176:46") // StreamRead(x48370,b31997)
    }; split5
    }; split4
    }; split3
    }; split2
    }; split1
    
  }
}
