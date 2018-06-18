import pir._
import pir.node._
import arch._
import prism.enums._

object CellUnit extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x10474 = DRAM().name("x10474").ctrl(top) // x10474 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(512)),Const(0))
    val x10482 = DRAM().name("x10482").ctrl(top) // x10482 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x10483 = DRAM().name("x10483").ctrl(top) // x10483 = DRAMNew(ArrayBuffer(Const(1), Const(2048)),Const(0))
    val x10484 = DRAM().name("x10484").ctrl(top) // x10484 = DRAMNew(ArrayBuffer(Const(1), Const(512)),Const(0))
    val x10510 = DRAM().name("x10510").ctrl(top) // x10510 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x10511 = DRAM().name("x10511").ctrl(top) // x10511 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x10512 = DRAM().name("x10512").ctrl(top) // x10512 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x10513 = DRAM().name("x10513").ctrl(top) // x10513 = DRAMNew(ArrayBuffer(Const(1536), Const(512)),Const(0))
    val x10514 = DRAM().name("x10514").ctrl(top) // x10514 = DRAMNew(ArrayBuffer(Const(1536), Const(2048)),Const(0))
    val x11236 = UnitController(style=SeqPipe, level=OuterControl).name("x11236").ctrl(top) // Hwblock(Block(Const(())),false)
    val x10558_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10558_d0_b0").ctrl(x11236) // x10558 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10558_d0_b0) = false
    bufferDepthOf(x10558_d0_b0) = 1
    val x10558_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10558_d1_b0").ctrl(x11236) // x10558 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10558_d1_b0) = false
    bufferDepthOf(x10558_d1_b0) = 1
    val x10558_d2_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10558_d2_b0").ctrl(x11236) // x10558 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10558_d2_b0) = false
    bufferDepthOf(x10558_d2_b0) = 1
    val x10558_d3_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10558_d3_b0").ctrl(x11236) // x10558 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10558_d3_b0) = false
    bufferDepthOf(x10558_d3_b0) = 1
    val x10559_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b0").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b0) = false
    bufferDepthOf(x10559_d0_b0) = 1
    val x10559_d0_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b1").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b1) = false
    bufferDepthOf(x10559_d0_b1) = 1
    val x10559_d0_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b2").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b2) = false
    bufferDepthOf(x10559_d0_b2) = 1
    val x10559_d0_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b3").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b3) = false
    bufferDepthOf(x10559_d0_b3) = 1
    val x10559_d0_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b4").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b4) = false
    bufferDepthOf(x10559_d0_b4) = 1
    val x10559_d0_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b5").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b5) = false
    bufferDepthOf(x10559_d0_b5) = 1
    val x10559_d0_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b6").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b6) = false
    bufferDepthOf(x10559_d0_b6) = 1
    val x10559_d0_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b7").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b7) = false
    bufferDepthOf(x10559_d0_b7) = 1
    val x10559_d0_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b8").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b8) = false
    bufferDepthOf(x10559_d0_b8) = 1
    val x10559_d0_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b9").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b9) = false
    bufferDepthOf(x10559_d0_b9) = 1
    val x10559_d0_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b10").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b10) = false
    bufferDepthOf(x10559_d0_b10) = 1
    val x10559_d0_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b11").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b11) = false
    bufferDepthOf(x10559_d0_b11) = 1
    val x10559_d0_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b12").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b12) = false
    bufferDepthOf(x10559_d0_b12) = 1
    val x10559_d0_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b13").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b13) = false
    bufferDepthOf(x10559_d0_b13) = 1
    val x10559_d0_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b14").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b14) = false
    bufferDepthOf(x10559_d0_b14) = 1
    val x10559_d0_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d0_b15").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d0_b15) = false
    bufferDepthOf(x10559_d0_b15) = 1
    val x10559_d1_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b0").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b0) = false
    bufferDepthOf(x10559_d1_b0) = 1
    val x10559_d1_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b1").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b1) = false
    bufferDepthOf(x10559_d1_b1) = 1
    val x10559_d1_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b2").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b2) = false
    bufferDepthOf(x10559_d1_b2) = 1
    val x10559_d1_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b3").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b3) = false
    bufferDepthOf(x10559_d1_b3) = 1
    val x10559_d1_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b4").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b4) = false
    bufferDepthOf(x10559_d1_b4) = 1
    val x10559_d1_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b5").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b5) = false
    bufferDepthOf(x10559_d1_b5) = 1
    val x10559_d1_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b6").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b6) = false
    bufferDepthOf(x10559_d1_b6) = 1
    val x10559_d1_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b7").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b7) = false
    bufferDepthOf(x10559_d1_b7) = 1
    val x10559_d1_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b8").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b8) = false
    bufferDepthOf(x10559_d1_b8) = 1
    val x10559_d1_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b9").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b9) = false
    bufferDepthOf(x10559_d1_b9) = 1
    val x10559_d1_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b10").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b10) = false
    bufferDepthOf(x10559_d1_b10) = 1
    val x10559_d1_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b11").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b11) = false
    bufferDepthOf(x10559_d1_b11) = 1
    val x10559_d1_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b12").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b12) = false
    bufferDepthOf(x10559_d1_b12) = 1
    val x10559_d1_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b13").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b13) = false
    bufferDepthOf(x10559_d1_b13) = 1
    val x10559_d1_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b14").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b14) = false
    bufferDepthOf(x10559_d1_b14) = 1
    val x10559_d1_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d1_b15").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d1_b15) = false
    bufferDepthOf(x10559_d1_b15) = 1
    val x10559_d2_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b0").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b0) = false
    bufferDepthOf(x10559_d2_b0) = 1
    val x10559_d2_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b1").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b1) = false
    bufferDepthOf(x10559_d2_b1) = 1
    val x10559_d2_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b2").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b2) = false
    bufferDepthOf(x10559_d2_b2) = 1
    val x10559_d2_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b3").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b3) = false
    bufferDepthOf(x10559_d2_b3) = 1
    val x10559_d2_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b4").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b4) = false
    bufferDepthOf(x10559_d2_b4) = 1
    val x10559_d2_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b5").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b5) = false
    bufferDepthOf(x10559_d2_b5) = 1
    val x10559_d2_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b6").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b6) = false
    bufferDepthOf(x10559_d2_b6) = 1
    val x10559_d2_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b7").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b7) = false
    bufferDepthOf(x10559_d2_b7) = 1
    val x10559_d2_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b8").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b8) = false
    bufferDepthOf(x10559_d2_b8) = 1
    val x10559_d2_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b9").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b9) = false
    bufferDepthOf(x10559_d2_b9) = 1
    val x10559_d2_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b10").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b10) = false
    bufferDepthOf(x10559_d2_b10) = 1
    val x10559_d2_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b11").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b11) = false
    bufferDepthOf(x10559_d2_b11) = 1
    val x10559_d2_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b12").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b12) = false
    bufferDepthOf(x10559_d2_b12) = 1
    val x10559_d2_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b13").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b13) = false
    bufferDepthOf(x10559_d2_b13) = 1
    val x10559_d2_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b14").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b14) = false
    bufferDepthOf(x10559_d2_b14) = 1
    val x10559_d2_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d2_b15").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d2_b15) = false
    bufferDepthOf(x10559_d2_b15) = 1
    val x10559_d3_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b0").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b0) = false
    bufferDepthOf(x10559_d3_b0) = 1
    val x10559_d3_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b1").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b1) = false
    bufferDepthOf(x10559_d3_b1) = 1
    val x10559_d3_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b2").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b2) = false
    bufferDepthOf(x10559_d3_b2) = 1
    val x10559_d3_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b3").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b3) = false
    bufferDepthOf(x10559_d3_b3) = 1
    val x10559_d3_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b4").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b4) = false
    bufferDepthOf(x10559_d3_b4) = 1
    val x10559_d3_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b5").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b5) = false
    bufferDepthOf(x10559_d3_b5) = 1
    val x10559_d3_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b6").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b6) = false
    bufferDepthOf(x10559_d3_b6) = 1
    val x10559_d3_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b7").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b7) = false
    bufferDepthOf(x10559_d3_b7) = 1
    val x10559_d3_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b8").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b8) = false
    bufferDepthOf(x10559_d3_b8) = 1
    val x10559_d3_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b9").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b9) = false
    bufferDepthOf(x10559_d3_b9) = 1
    val x10559_d3_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b10").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b10) = false
    bufferDepthOf(x10559_d3_b10) = 1
    val x10559_d3_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b11").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b11) = false
    bufferDepthOf(x10559_d3_b11) = 1
    val x10559_d3_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b12").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b12) = false
    bufferDepthOf(x10559_d3_b12) = 1
    val x10559_d3_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b13").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b13) = false
    bufferDepthOf(x10559_d3_b13) = 1
    val x10559_d3_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b14").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b14) = false
    bufferDepthOf(x10559_d3_b14) = 1
    val x10559_d3_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d3_b15").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d3_b15) = false
    bufferDepthOf(x10559_d3_b15) = 1
    val x10559_d4_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b0").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b0) = false
    bufferDepthOf(x10559_d4_b0) = 1
    val x10559_d4_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b1").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b1) = false
    bufferDepthOf(x10559_d4_b1) = 1
    val x10559_d4_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b2").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b2) = false
    bufferDepthOf(x10559_d4_b2) = 1
    val x10559_d4_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b3").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b3) = false
    bufferDepthOf(x10559_d4_b3) = 1
    val x10559_d4_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b4").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b4) = false
    bufferDepthOf(x10559_d4_b4) = 1
    val x10559_d4_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b5").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b5) = false
    bufferDepthOf(x10559_d4_b5) = 1
    val x10559_d4_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b6").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b6) = false
    bufferDepthOf(x10559_d4_b6) = 1
    val x10559_d4_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b7").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b7) = false
    bufferDepthOf(x10559_d4_b7) = 1
    val x10559_d4_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b8").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b8) = false
    bufferDepthOf(x10559_d4_b8) = 1
    val x10559_d4_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b9").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b9) = false
    bufferDepthOf(x10559_d4_b9) = 1
    val x10559_d4_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b10").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b10) = false
    bufferDepthOf(x10559_d4_b10) = 1
    val x10559_d4_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b11").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b11) = false
    bufferDepthOf(x10559_d4_b11) = 1
    val x10559_d4_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b12").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b12) = false
    bufferDepthOf(x10559_d4_b12) = 1
    val x10559_d4_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b13").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b13) = false
    bufferDepthOf(x10559_d4_b13) = 1
    val x10559_d4_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b14").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b14) = false
    bufferDepthOf(x10559_d4_b14) = 1
    val x10559_d4_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10559_d4_b15").ctrl(x11236) // x10559 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10559_d4_b15) = false
    bufferDepthOf(x10559_d4_b15) = 1
    val x10560_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b0").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b0) = false
    bufferDepthOf(x10560_d0_b0) = 1
    val x10560_d0_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b1").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b1) = false
    bufferDepthOf(x10560_d0_b1) = 1
    val x10560_d0_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b2").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b2) = false
    bufferDepthOf(x10560_d0_b2) = 1
    val x10560_d0_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b3").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b3) = false
    bufferDepthOf(x10560_d0_b3) = 1
    val x10560_d0_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b4").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b4) = false
    bufferDepthOf(x10560_d0_b4) = 1
    val x10560_d0_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b5").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b5) = false
    bufferDepthOf(x10560_d0_b5) = 1
    val x10560_d0_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b6").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b6) = false
    bufferDepthOf(x10560_d0_b6) = 1
    val x10560_d0_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b7").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b7) = false
    bufferDepthOf(x10560_d0_b7) = 1
    val x10560_d0_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b8").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b8) = false
    bufferDepthOf(x10560_d0_b8) = 1
    val x10560_d0_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b9").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b9) = false
    bufferDepthOf(x10560_d0_b9) = 1
    val x10560_d0_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b10").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b10) = false
    bufferDepthOf(x10560_d0_b10) = 1
    val x10560_d0_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b11").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b11) = false
    bufferDepthOf(x10560_d0_b11) = 1
    val x10560_d0_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b12").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b12) = false
    bufferDepthOf(x10560_d0_b12) = 1
    val x10560_d0_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b13").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b13) = false
    bufferDepthOf(x10560_d0_b13) = 1
    val x10560_d0_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b14").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b14) = false
    bufferDepthOf(x10560_d0_b14) = 1
    val x10560_d0_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d0_b15").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d0_b15) = false
    bufferDepthOf(x10560_d0_b15) = 1
    val x10560_d1_b0 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b0").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b0) = true
    bufferDepthOf(x10560_d1_b0) = 1
    val x10560_d1_b1 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b1").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b1) = true
    bufferDepthOf(x10560_d1_b1) = 1
    val x10560_d1_b2 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b2").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b2) = true
    bufferDepthOf(x10560_d1_b2) = 1
    val x10560_d1_b3 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b3").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b3) = true
    bufferDepthOf(x10560_d1_b3) = 1
    val x10560_d1_b4 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b4").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b4) = true
    bufferDepthOf(x10560_d1_b4) = 1
    val x10560_d1_b5 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b5").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b5) = true
    bufferDepthOf(x10560_d1_b5) = 1
    val x10560_d1_b6 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b6").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b6) = true
    bufferDepthOf(x10560_d1_b6) = 1
    val x10560_d1_b7 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b7").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b7) = true
    bufferDepthOf(x10560_d1_b7) = 1
    val x10560_d1_b8 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b8").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b8) = true
    bufferDepthOf(x10560_d1_b8) = 1
    val x10560_d1_b9 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b9").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b9) = true
    bufferDepthOf(x10560_d1_b9) = 1
    val x10560_d1_b10 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b10").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b10) = true
    bufferDepthOf(x10560_d1_b10) = 1
    val x10560_d1_b11 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b11").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b11) = true
    bufferDepthOf(x10560_d1_b11) = 1
    val x10560_d1_b12 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b12").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b12) = true
    bufferDepthOf(x10560_d1_b12) = 1
    val x10560_d1_b13 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b13").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b13) = true
    bufferDepthOf(x10560_d1_b13) = 1
    val x10560_d1_b14 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b14").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b14) = true
    bufferDepthOf(x10560_d1_b14) = 1
    val x10560_d1_b15 = SRAM(size=32, banking=Strided(banks=1, stride=512)).name("x10560_d1_b15").ctrl(x11236) // x10560 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10560_d1_b15) = true
    bufferDepthOf(x10560_d1_b15) = 1
    val x10561_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10561_d0_b0").ctrl(x11236) // x10561 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10561_d0_b0) = false
    bufferDepthOf(x10561_d0_b0) = 1
    val x10561_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10561_d1_b0").ctrl(x11236) // x10561 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10561_d1_b0) = true
    bufferDepthOf(x10561_d1_b0) = 1
    val x10562_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10562_d0_b0").ctrl(x11236) // x10562 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10562_d0_b0) = false
    bufferDepthOf(x10562_d0_b0) = 1
    val x10562_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10562_d1_b0").ctrl(x11236) // x10562 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10562_d1_b0) = true
    bufferDepthOf(x10562_d1_b0) = 1
    val x10563_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10563_d0_b0").ctrl(x11236) // x10563 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10563_d0_b0) = false
    bufferDepthOf(x10563_d0_b0) = 1
    val x10563_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10563_d1_b0").ctrl(x11236) // x10563 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10563_d1_b0) = true
    bufferDepthOf(x10563_d1_b0) = 1
    val x10564_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10564_d0_b0").ctrl(x11236) // x10564 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10564_d0_b0) = false
    bufferDepthOf(x10564_d0_b0) = 1
    val x10564_d1_b0 = SRAM(size=512, banking=Strided(banks=1, stride=512)).name("x10564_d1_b0").ctrl(x11236) // x10564 = SRAMNew(ArrayBuffer(Const(1), Const(512)))
    isAccum(x10564_d1_b0) = true
    bufferDepthOf(x10564_d1_b0) = 1
    val x10565_d0_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x10565_d0_b0").ctrl(x11236) // x10565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x10565_d0_b0) = false
    bufferDepthOf(x10565_d0_b0) = 1
    val x10565_d1_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x10565_d1_b0").ctrl(x11236) // x10565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x10565_d1_b0) = false
    bufferDepthOf(x10565_d1_b0) = 1
    val x10565_d2_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x10565_d2_b0").ctrl(x11236) // x10565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x10565_d2_b0) = false
    bufferDepthOf(x10565_d2_b0) = 1
    val x10565_d3_b0 = SRAM(size=2048, banking=Strided(banks=1, stride=2048)).name("x10565_d3_b0").ctrl(x11236) // x10565 = SRAMNew(ArrayBuffer(Const(1), Const(2048)))
    isAccum(x10565_d3_b0) = false
    bufferDepthOf(x10565_d3_b0) = 1
    val x10566 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10566").ctrl(x11236) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10567 = CounterChain(List(x10566)).name("x10567").ctrl(x11236) // CounterChainNew(List(x10566))
    val x10589 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10567).name("x10589").ctrl(x11236) // UnrolledForeach(List(Const(true)),x10567,Block(Const(())),List(List(b6514)),List(List(b6515)))
    val b6514 = CounterIter(x10566, Some(0)).ctrl(x10589).name("b6514")
    val b6515 = Const(true).ctrl(x10589).name("b6515")
    val b11246 = StreamOut(field="offset").name("b11246").ctrl(x10589) // x10568 = StreamOutNew(BurstCmdBus)
    val b11247 = StreamOut(field="size").name("b11247").ctrl(x10589) // x10568 = StreamOutNew(BurstCmdBus)
    val x10569 = StreamIn(field="data").name("x10569").ctrl(x10589) // x10569 = StreamInNew(BurstDataBus())
    val x10580 = UnitController(style=SeqPipe, level=InnerControl).name("x10580").ctrl(x10589) // UnitPipe(List(b6515),Block(x10579))
    val x10570 = b6514 // FixConvert(b6514,TRUE,_32,_0) (Same Type. No op)
    val x10571 = OpDef(op=FixSla, inputs=List(x10570, Const(11))).name("x10571").ctrl(x10580) // FixLsh(x10570,Const(11))
    val x10572 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10573 = OpDef(op=FixAdd, inputs=List(x10571, x10572)).name("x10573").ctrl(x10580) // FixAdd(x10571,x10572)
    val x10574 = OpDef(op=FixSla, inputs=List(x10573, Const(2))).name("x10574").ctrl(x10580) // FixLsh(x10573,Const(2))
    val x10575 = x10574 // FixConvert(x10574,TRUE,_64,_0)
    val x10576 = DramAddress(x10483).name("x10576").ctrl(x10580) // GetDRAMAddress(x10483)
    val x10578_x10577 = OpDef(op=FixAdd, inputs=List(x10575, x10576)).name("x10577").ctrl(x10580) // FixAdd(x10575,x10576)
    // x10578 = SimpleStruct(ArrayBuffer((offset,x10577), (size,Const(8192)), (isLoad,Const(true))))
    val b11248_b11246 = WriteMem(b11246, x10578_x10577).name("b11248_b11246").ctrl(x10580) // StreamWrite(x10568,x10578,b6515)
    val b11249_b11247 = WriteMem(b11247, Const(8192)).name("b11249_b11247").ctrl(x10580) // StreamWrite(x10568,x10578,b6515)
    val x10581 = FringeDenseLoad(dram=List(x10483), cmdStream=List(b11246, b11247), dataStream=List(x10569)).name("x10581").ctrl(x10589) // FringeDenseLoad(x10483,x10568,x10569)
    val x10582 = Counter(min=Const(0), max=Const(2048), step=Const(1), par=1).name("x10582").ctrl(x10589) // CounterNew(Const(0),Const(2048),Const(1),Const(1))
    val x10583 = CounterChain(List(x10582)).name("x10583").ctrl(x10589) // CounterChainNew(List(x10582))
    val x10588 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10583).name("x10588").ctrl(x10589) // UnrolledForeach(List(b6515),x10583,Block(Const(())),List(List(b6532)),List(List(b6533)))
    val b6532 = CounterIter(x10582, None).ctrl(x10588).name("b6532")
    val b6533 = Const(true).ctrl(x10588).name("b6533")
    val x10584 = OpDef(op=BitAnd, inputs=List(b6533, b6515)).name("x10584").ctrl(x10588) // And(b6533,b6515)
    val x10585_x10585 = ReadMem(x10569).name("x10585").ctrl(x10588) // ParStreamRead(x10569,List(x10584))
    val x10586_x10586 = x10585_x10585 // x10586 = VectorApply(x10585,0)
    val x10587 = StoreBanks(List(x10565_d0_b0, x10565_d1_b0, x10565_d2_b0, x10565_d3_b0), List(b6514, b6532), x10586_x10586).name("x10587").ctrl(x10588) // ParSRAMStore(x10565,List(List(b6514, b6532)),List(x10586),List(x10584))
    val x10590 = Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x10590").ctrl(x11236) // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x10591 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10591").ctrl(x11236) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10592 = CounterChain(List(x10591,x10590)).name("x10592").ctrl(x11236) // CounterChainNew(List(x10591, x10590))
    val x10657 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10592).name("x10657").ctrl(x11236) // UnrolledForeach(List(Const(true)),x10592,Block(Const(())),List(List(b6543), List(b6544, b6545, b6546, b6547, b6548, b6549, b6550, b6551, b6552, b6553, b6554, b6555, b6556, b6557, b6558, b6559)),List(List(b6560), List(b6561, b6562, b6563, b6564, b6565, b6566, b6567, b6568, b6569, b6570, b6571, b6572, b6573, b6574, b6575, b6576)))
    val b6543 = CounterIter(x10591, Some(0)).ctrl(x10657).name("b6543")
    val b6560 = Const(true).ctrl(x10657).name("b6560")
    val b6544 = CounterIter(x10590, Some(0)).ctrl(x10657).name("b6544")
    val b6561 = Const(true).ctrl(x10657).name("b6561")
    val b6545 = CounterIter(x10590, Some(1)).ctrl(x10657).name("b6545")
    val b6562 = Const(true).ctrl(x10657).name("b6562")
    val b6546 = CounterIter(x10590, Some(2)).ctrl(x10657).name("b6546")
    val b6563 = Const(true).ctrl(x10657).name("b6563")
    val b6547 = CounterIter(x10590, Some(3)).ctrl(x10657).name("b6547")
    val b6564 = Const(true).ctrl(x10657).name("b6564")
    val b6548 = CounterIter(x10590, Some(4)).ctrl(x10657).name("b6548")
    val b6565 = Const(true).ctrl(x10657).name("b6565")
    val b6549 = CounterIter(x10590, Some(5)).ctrl(x10657).name("b6549")
    val b6566 = Const(true).ctrl(x10657).name("b6566")
    val b6550 = CounterIter(x10590, Some(6)).ctrl(x10657).name("b6550")
    val b6567 = Const(true).ctrl(x10657).name("b6567")
    val b6551 = CounterIter(x10590, Some(7)).ctrl(x10657).name("b6551")
    val b6568 = Const(true).ctrl(x10657).name("b6568")
    val b6552 = CounterIter(x10590, Some(8)).ctrl(x10657).name("b6552")
    val b6569 = Const(true).ctrl(x10657).name("b6569")
    val b6553 = CounterIter(x10590, Some(9)).ctrl(x10657).name("b6553")
    val b6570 = Const(true).ctrl(x10657).name("b6570")
    val b6554 = CounterIter(x10590, Some(10)).ctrl(x10657).name("b6554")
    val b6571 = Const(true).ctrl(x10657).name("b6571")
    val b6555 = CounterIter(x10590, Some(11)).ctrl(x10657).name("b6555")
    val b6572 = Const(true).ctrl(x10657).name("b6572")
    val b6556 = CounterIter(x10590, Some(12)).ctrl(x10657).name("b6556")
    val b6573 = Const(true).ctrl(x10657).name("b6573")
    val b6557 = CounterIter(x10590, Some(13)).ctrl(x10657).name("b6557")
    val b6574 = Const(true).ctrl(x10657).name("b6574")
    val b6558 = CounterIter(x10590, Some(14)).ctrl(x10657).name("b6558")
    val b6575 = Const(true).ctrl(x10657).name("b6575")
    val b6559 = CounterIter(x10590, Some(15)).ctrl(x10657).name("b6559")
    val b6576 = Const(true).ctrl(x10657).name("b6576")
    val x10596 = UnitController(style=SeqPipe, level=InnerControl).name("x10596").ctrl(x10657) // UnitPipe(List(b6560, b6561),Block(Const(())))
    val x10593 = OpDef(op=BitAnd, inputs=List(b6560, b6561)).name("x10593").ctrl(x10596) // And(b6560,b6561)
    val x10594 = StoreBanks(List(x10560_d0_b0, x10560_d1_b0), List(b6543, b6544), Const(0.0)).name("x10594").ctrl(x10596) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6544),Const(0),Const(0),x10593)
    val x10595 = StoreBanks(List(x10559_d0_b0, x10559_d1_b0, x10559_d2_b0, x10559_d3_b0, x10559_d4_b0), List(b6543, b6544), Const(0.0)).name("x10595").ctrl(x10596) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6544),Const(0),Const(0),x10593)
    val x10600 = UnitController(style=SeqPipe, level=InnerControl).name("x10600").ctrl(x10657) // UnitPipe(List(b6560, b6562),Block(Const(())))
    val x10597 = OpDef(op=BitAnd, inputs=List(b6560, b6562)).name("x10597").ctrl(x10600) // And(b6560,b6562)
    val x10598 = StoreBanks(List(x10560_d0_b1, x10560_d1_b1), List(b6543, b6545), Const(0.0)).name("x10598").ctrl(x10600) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6545),Const(0),Const(0),x10597)
    val x10599 = StoreBanks(List(x10559_d0_b1, x10559_d1_b1, x10559_d2_b1, x10559_d3_b1, x10559_d4_b1), List(b6543, b6545), Const(0.0)).name("x10599").ctrl(x10600) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6545),Const(0),Const(0),x10597)
    val x10604 = UnitController(style=SeqPipe, level=InnerControl).name("x10604").ctrl(x10657) // UnitPipe(List(b6560, b6563),Block(Const(())))
    val x10601 = OpDef(op=BitAnd, inputs=List(b6560, b6563)).name("x10601").ctrl(x10604) // And(b6560,b6563)
    val x10602 = StoreBanks(List(x10560_d0_b2, x10560_d1_b2), List(b6543, b6546), Const(0.0)).name("x10602").ctrl(x10604) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6546),Const(0),Const(0),x10601)
    val x10603 = StoreBanks(List(x10559_d0_b2, x10559_d1_b2, x10559_d2_b2, x10559_d3_b2, x10559_d4_b2), List(b6543, b6546), Const(0.0)).name("x10603").ctrl(x10604) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6546),Const(0),Const(0),x10601)
    val x10608 = UnitController(style=SeqPipe, level=InnerControl).name("x10608").ctrl(x10657) // UnitPipe(List(b6560, b6564),Block(Const(())))
    val x10605 = OpDef(op=BitAnd, inputs=List(b6560, b6564)).name("x10605").ctrl(x10608) // And(b6560,b6564)
    val x10606 = StoreBanks(List(x10560_d0_b3, x10560_d1_b3), List(b6543, b6547), Const(0.0)).name("x10606").ctrl(x10608) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6547),Const(0),Const(0),x10605)
    val x10607 = StoreBanks(List(x10559_d0_b3, x10559_d1_b3, x10559_d2_b3, x10559_d3_b3, x10559_d4_b3), List(b6543, b6547), Const(0.0)).name("x10607").ctrl(x10608) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6547),Const(0),Const(0),x10605)
    val x10612 = UnitController(style=SeqPipe, level=InnerControl).name("x10612").ctrl(x10657) // UnitPipe(List(b6560, b6565),Block(Const(())))
    val x10609 = OpDef(op=BitAnd, inputs=List(b6560, b6565)).name("x10609").ctrl(x10612) // And(b6560,b6565)
    val x10610 = StoreBanks(List(x10560_d0_b4, x10560_d1_b4), List(b6543, b6548), Const(0.0)).name("x10610").ctrl(x10612) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6548),Const(0),Const(0),x10609)
    val x10611 = StoreBanks(List(x10559_d0_b4, x10559_d1_b4, x10559_d2_b4, x10559_d3_b4, x10559_d4_b4), List(b6543, b6548), Const(0.0)).name("x10611").ctrl(x10612) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6548),Const(0),Const(0),x10609)
    val x10616 = UnitController(style=SeqPipe, level=InnerControl).name("x10616").ctrl(x10657) // UnitPipe(List(b6560, b6566),Block(Const(())))
    val x10613 = OpDef(op=BitAnd, inputs=List(b6560, b6566)).name("x10613").ctrl(x10616) // And(b6560,b6566)
    val x10614 = StoreBanks(List(x10560_d0_b5, x10560_d1_b5), List(b6543, b6549), Const(0.0)).name("x10614").ctrl(x10616) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6549),Const(0),Const(0),x10613)
    val x10615 = StoreBanks(List(x10559_d0_b5, x10559_d1_b5, x10559_d2_b5, x10559_d3_b5, x10559_d4_b5), List(b6543, b6549), Const(0.0)).name("x10615").ctrl(x10616) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6549),Const(0),Const(0),x10613)
    val x10620 = UnitController(style=SeqPipe, level=InnerControl).name("x10620").ctrl(x10657) // UnitPipe(List(b6560, b6567),Block(Const(())))
    val x10617 = OpDef(op=BitAnd, inputs=List(b6560, b6567)).name("x10617").ctrl(x10620) // And(b6560,b6567)
    val x10618 = StoreBanks(List(x10560_d0_b6, x10560_d1_b6), List(b6543, b6550), Const(0.0)).name("x10618").ctrl(x10620) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6550),Const(0),Const(0),x10617)
    val x10619 = StoreBanks(List(x10559_d0_b6, x10559_d1_b6, x10559_d2_b6, x10559_d3_b6, x10559_d4_b6), List(b6543, b6550), Const(0.0)).name("x10619").ctrl(x10620) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6550),Const(0),Const(0),x10617)
    val x10624 = UnitController(style=SeqPipe, level=InnerControl).name("x10624").ctrl(x10657) // UnitPipe(List(b6560, b6568),Block(Const(())))
    val x10621 = OpDef(op=BitAnd, inputs=List(b6560, b6568)).name("x10621").ctrl(x10624) // And(b6560,b6568)
    val x10622 = StoreBanks(List(x10560_d0_b7, x10560_d1_b7), List(b6543, b6551), Const(0.0)).name("x10622").ctrl(x10624) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6551),Const(0),Const(0),x10621)
    val x10623 = StoreBanks(List(x10559_d0_b7, x10559_d1_b7, x10559_d2_b7, x10559_d3_b7, x10559_d4_b7), List(b6543, b6551), Const(0.0)).name("x10623").ctrl(x10624) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6551),Const(0),Const(0),x10621)
    val x10628 = UnitController(style=SeqPipe, level=InnerControl).name("x10628").ctrl(x10657) // UnitPipe(List(b6560, b6569),Block(Const(())))
    val x10625 = OpDef(op=BitAnd, inputs=List(b6560, b6569)).name("x10625").ctrl(x10628) // And(b6560,b6569)
    val x10626 = StoreBanks(List(x10560_d0_b8, x10560_d1_b8), List(b6543, b6552), Const(0.0)).name("x10626").ctrl(x10628) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6552),Const(0),Const(0),x10625)
    val x10627 = StoreBanks(List(x10559_d0_b8, x10559_d1_b8, x10559_d2_b8, x10559_d3_b8, x10559_d4_b8), List(b6543, b6552), Const(0.0)).name("x10627").ctrl(x10628) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6552),Const(0),Const(0),x10625)
    val x10632 = UnitController(style=SeqPipe, level=InnerControl).name("x10632").ctrl(x10657) // UnitPipe(List(b6560, b6570),Block(Const(())))
    val x10629 = OpDef(op=BitAnd, inputs=List(b6560, b6570)).name("x10629").ctrl(x10632) // And(b6560,b6570)
    val x10630 = StoreBanks(List(x10560_d0_b9, x10560_d1_b9), List(b6543, b6553), Const(0.0)).name("x10630").ctrl(x10632) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6553),Const(0),Const(0),x10629)
    val x10631 = StoreBanks(List(x10559_d0_b9, x10559_d1_b9, x10559_d2_b9, x10559_d3_b9, x10559_d4_b9), List(b6543, b6553), Const(0.0)).name("x10631").ctrl(x10632) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6553),Const(0),Const(0),x10629)
    val x10636 = UnitController(style=SeqPipe, level=InnerControl).name("x10636").ctrl(x10657) // UnitPipe(List(b6560, b6571),Block(Const(())))
    val x10633 = OpDef(op=BitAnd, inputs=List(b6560, b6571)).name("x10633").ctrl(x10636) // And(b6560,b6571)
    val x10634 = StoreBanks(List(x10560_d0_b10, x10560_d1_b10), List(b6543, b6554), Const(0.0)).name("x10634").ctrl(x10636) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6554),Const(0),Const(0),x10633)
    val x10635 = StoreBanks(List(x10559_d0_b10, x10559_d1_b10, x10559_d2_b10, x10559_d3_b10, x10559_d4_b10), List(b6543, b6554), Const(0.0)).name("x10635").ctrl(x10636) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6554),Const(0),Const(0),x10633)
    val x10640 = UnitController(style=SeqPipe, level=InnerControl).name("x10640").ctrl(x10657) // UnitPipe(List(b6560, b6572),Block(Const(())))
    val x10637 = OpDef(op=BitAnd, inputs=List(b6560, b6572)).name("x10637").ctrl(x10640) // And(b6560,b6572)
    val x10638 = StoreBanks(List(x10560_d0_b11, x10560_d1_b11), List(b6543, b6555), Const(0.0)).name("x10638").ctrl(x10640) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6555),Const(0),Const(0),x10637)
    val x10639 = StoreBanks(List(x10559_d0_b11, x10559_d1_b11, x10559_d2_b11, x10559_d3_b11, x10559_d4_b11), List(b6543, b6555), Const(0.0)).name("x10639").ctrl(x10640) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6555),Const(0),Const(0),x10637)
    val x10644 = UnitController(style=SeqPipe, level=InnerControl).name("x10644").ctrl(x10657) // UnitPipe(List(b6560, b6573),Block(Const(())))
    val x10641 = OpDef(op=BitAnd, inputs=List(b6560, b6573)).name("x10641").ctrl(x10644) // And(b6560,b6573)
    val x10642 = StoreBanks(List(x10560_d0_b12, x10560_d1_b12), List(b6543, b6556), Const(0.0)).name("x10642").ctrl(x10644) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6556),Const(0),Const(0),x10641)
    val x10643 = StoreBanks(List(x10559_d0_b12, x10559_d1_b12, x10559_d2_b12, x10559_d3_b12, x10559_d4_b12), List(b6543, b6556), Const(0.0)).name("x10643").ctrl(x10644) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6556),Const(0),Const(0),x10641)
    val x10648 = UnitController(style=SeqPipe, level=InnerControl).name("x10648").ctrl(x10657) // UnitPipe(List(b6560, b6574),Block(Const(())))
    val x10645 = OpDef(op=BitAnd, inputs=List(b6560, b6574)).name("x10645").ctrl(x10648) // And(b6560,b6574)
    val x10646 = StoreBanks(List(x10560_d0_b13, x10560_d1_b13), List(b6543, b6557), Const(0.0)).name("x10646").ctrl(x10648) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6557),Const(0),Const(0),x10645)
    val x10647 = StoreBanks(List(x10559_d0_b13, x10559_d1_b13, x10559_d2_b13, x10559_d3_b13, x10559_d4_b13), List(b6543, b6557), Const(0.0)).name("x10647").ctrl(x10648) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6557),Const(0),Const(0),x10645)
    val x10652 = UnitController(style=SeqPipe, level=InnerControl).name("x10652").ctrl(x10657) // UnitPipe(List(b6560, b6575),Block(Const(())))
    val x10649 = OpDef(op=BitAnd, inputs=List(b6560, b6575)).name("x10649").ctrl(x10652) // And(b6560,b6575)
    val x10650 = StoreBanks(List(x10560_d0_b14, x10560_d1_b14), List(b6543, b6558), Const(0.0)).name("x10650").ctrl(x10652) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6558),Const(0),Const(0),x10649)
    val x10651 = StoreBanks(List(x10559_d0_b14, x10559_d1_b14, x10559_d2_b14, x10559_d3_b14, x10559_d4_b14), List(b6543, b6558), Const(0.0)).name("x10651").ctrl(x10652) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6558),Const(0),Const(0),x10649)
    val x10656 = UnitController(style=SeqPipe, level=InnerControl).name("x10656").ctrl(x10657) // UnitPipe(List(b6560, b6576),Block(Const(())))
    val x10653 = OpDef(op=BitAnd, inputs=List(b6560, b6576)).name("x10653").ctrl(x10656) // And(b6560,b6576)
    val x10654 = StoreBanks(List(x10560_d0_b15, x10560_d1_b15), List(b6543, b6559), Const(0.0)).name("x10654").ctrl(x10656) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b6543, b6559),Const(0),Const(0),x10653)
    val x10655 = StoreBanks(List(x10559_d0_b15, x10559_d1_b15, x10559_d2_b15, x10559_d3_b15, x10559_d4_b15), List(b6543, b6559), Const(0.0)).name("x10655").ctrl(x10656) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b6543, b6559),Const(0),Const(0),x10653)
    val x10658 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x10658").ctrl(x11236) // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x10659 = CounterChain(List(x10658)).name("x10659").ctrl(x11236) // CounterChainNew(List(x10658))
    val x11179 = LoopController(style=SeqPipe, level=OuterControl, cchain=x10659).name("x11179").ctrl(x11236) // UnrolledForeach(List(Const(true)),x10659,Block(Const(())),List(List(b6662)),List(List(b6663)))
    val b6662 = CounterIter(x10658, Some(0)).ctrl(x11179).name("b6662")
    val b6663 = Const(true).ctrl(x11179).name("b6663")
    val x10661 = UnitController(style=SeqPipe, level=InnerControl).name("x10661").ctrl(x11179) // UnitPipe(List(b6663),Block(Const(())))
    val x10660 = OpDef(op=FixAdd, inputs=List(b6662, Const(1))).name("x10660").ctrl(x10661) // FixAdd(b6662,Const(1))
    val x10662 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10662").ctrl(x11179) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10663 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10663").ctrl(x11179) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10664 = CounterChain(List(x10662,x10663)).name("x10664").ctrl(x11179) // CounterChainNew(List(x10662, x10663))
    val x10694 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10664).name("x10694").ctrl(x11179) // UnrolledForeach(List(b6663),x10664,Block(Const(())),List(List(b6669), List(b6670)),List(List(b6671), List(b6672)))
    val b6669 = CounterIter(x10662, Some(0)).ctrl(x10694).name("b6669")
    val b6671 = Const(true).ctrl(x10694).name("b6671")
    val b6670 = CounterIter(x10663, Some(0)).ctrl(x10694).name("b6670")
    val b6672 = Const(true).ctrl(x10694).name("b6672")
    val b11250 = StreamOut(field="offset").name("b11250").ctrl(x10694) // x10665 = StreamOutNew(BurstCmdBus)
    val b11251 = StreamOut(field="size").name("b11251").ctrl(x10694) // x10665 = StreamOutNew(BurstCmdBus)
    val x10666 = StreamIn(field="data").name("x10666").ctrl(x10694) // x10666 = StreamInNew(BurstDataBus())
    val x10683 = UnitController(style=SeqPipe, level=InnerControl).name("x10683").ctrl(x10694) // UnitPipe(List(b6671, b6672, b6663),Block(x10682))
    val x10667 = OpDef(op=FixAdd, inputs=List(b6662, b6670)).name("x10667").ctrl(x10683) // FixAdd(b6662,b6670)
    val x10668 = b6669 // FixConvert(b6669,TRUE,_32,_0) (Same Type. No op)
    val x10669 = OpDef(op=FixSla, inputs=List(x10668, Const(11))).name("x10669").ctrl(x10683) // FixLsh(x10668,Const(11))
    val x10670 = x10667 // FixConvert(x10667,TRUE,_32,_0) (Same Type. No op)
    val x10671 = OpDef(op=FixSla, inputs=List(x10670, Const(9))).name("x10671").ctrl(x10683) // FixLsh(x10670,Const(9))
    val x10672 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10673 = OpDef(op=FixAdd, inputs=List(x10669, x10671)).name("x10673").ctrl(x10683) // FixAdd(x10669,x10671)
    val x10674 = OpDef(op=FixAdd, inputs=List(x10673, x10672)).name("x10674").ctrl(x10683) // FixAdd(x10673,x10672)
    val x10675 = OpDef(op=FixSla, inputs=List(x10674, Const(2))).name("x10675").ctrl(x10683) // FixLsh(x10674,Const(2))
    val x10676 = x10675 // FixConvert(x10675,TRUE,_64,_0)
    val x10677 = DramAddress(x10474).name("x10677").ctrl(x10683) // GetDRAMAddress(x10474)
    val x10679_x10678 = OpDef(op=FixAdd, inputs=List(x10676, x10677)).name("x10678").ctrl(x10683) // FixAdd(x10676,x10677)
    // x10679 = SimpleStruct(ArrayBuffer((offset,x10678), (size,Const(2048)), (isLoad,Const(true))))
    val x10680 = OpDef(op=BitAnd, inputs=List(b6671, b6672)).name("x10680").ctrl(x10683) // And(b6671,b6672)
    val x10681 = OpDef(op=BitAnd, inputs=List(x10680, b6663)).name("x10681").ctrl(x10683) // And(x10680,b6663)
    val b11252_b11250 = WriteMem(b11250, x10679_x10678).name("b11252_b11250").ctrl(x10683) // StreamWrite(x10665,x10679,x10681)
    val b11253_b11251 = WriteMem(b11251, Const(2048)).name("b11253_b11251").ctrl(x10683) // StreamWrite(x10665,x10679,x10681)
    val x10684 = FringeDenseLoad(dram=List(x10474), cmdStream=List(b11250, b11251), dataStream=List(x10666)).name("x10684").ctrl(x10694) // FringeDenseLoad(x10474,x10665,x10666)
    val x10685 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x10685").ctrl(x10694) // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x10686 = CounterChain(List(x10685)).name("x10686").ctrl(x10694) // CounterChainNew(List(x10685))
    val x10693 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10686).name("x10693").ctrl(x10694) // UnrolledForeach(List(b6671, b6672, b6663),x10686,Block(Const(())),List(List(b6695)),List(List(b6696)))
    val b6695 = CounterIter(x10685, None).ctrl(x10693).name("b6695")
    val b6696 = Const(true).ctrl(x10693).name("b6696")
    val x10687 = OpDef(op=BitAnd, inputs=List(b6696, b6671)).name("x10687").ctrl(x10693) // And(b6696,b6671)
    val x10688 = OpDef(op=BitAnd, inputs=List(b6672, b6663)).name("x10688").ctrl(x10693) // And(b6672,b6663)
    val x10689 = OpDef(op=BitAnd, inputs=List(x10687, x10688)).name("x10689").ctrl(x10693) // And(x10687,x10688)
    val x10690_x10690 = ReadMem(x10666).name("x10690").ctrl(x10693) // ParStreamRead(x10666,List(x10689))
    val x10691_x10691 = x10690_x10690 // x10691 = VectorApply(x10690,0)
    val x10692 = StoreBanks(List(x10558_d0_b0, x10558_d1_b0, x10558_d2_b0, x10558_d3_b0), List(b6669, b6695), x10691_x10691).name("x10692").ctrl(x10693) // ParSRAMStore(x10558,List(List(b6669, b6695)),List(x10691),List(x10689))
    val x10695 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x10695").ctrl(x11179) // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x10696 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x10696").ctrl(x11179) // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x10697 = CounterChain(List(x10696,x10695)).name("x10697").ctrl(x11179) // CounterChainNew(List(x10696, x10695))
    val x10806 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10697).name("x10806").ctrl(x11179) // UnrolledForeach(List(b6663),x10697,Block(Const(())),List(List(b6708), List(b6709)),List(List(b6710), List(b6711)))
    val b6708 = CounterIter(x10696, Some(0)).ctrl(x10806).name("b6708")
    val b6710 = Const(true).ctrl(x10806).name("b6710")
    val b6709 = CounterIter(x10695, Some(0)).ctrl(x10806).name("b6709")
    val b6711 = Const(true).ctrl(x10806).name("b6711")
    val x10698_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x10698_d0_b0").ctrl(x10806) // x10698 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x10698_d0_b0) = false
    bufferDepthOf(x10698_d0_b0) = 2
    val x10701 = UnitController(style=SeqPipe, level=InnerControl).name("x10701").ctrl(x10806) // UnitPipe(List(b6710, b6711, b6663),Block(Const(())))
    val x10699 = OpDef(op=FixAdd, inputs=List(b6708, Const(16))).name("x10699").ctrl(x10701) // FixAdd(b6708,Const(16))
    val x10700 = OpDef(op=FixAdd, inputs=List(b6709, Const(16))).name("x10700").ctrl(x10701) // FixAdd(b6709,Const(16))
    val x10702 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10702").ctrl(x10806) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10703 = CounterChain(List(x10702)).name("x10703").ctrl(x10806) // CounterChainNew(List(x10702))
    val x10732 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10703).name("x10732").ctrl(x10806) // UnrolledForeach(List(b6710, b6711, b6663),x10703,Block(Const(())),List(List(b6718)),List(List(b6719)))
    val b6718 = CounterIter(x10702, Some(0)).ctrl(x10732).name("b6718")
    val b6719 = Const(true).ctrl(x10732).name("b6719")
    val b11254 = StreamOut(field="offset").name("b11254").ctrl(x10732) // x10704 = StreamOutNew(BurstCmdBus)
    val b11255 = StreamOut(field="size").name("b11255").ctrl(x10732) // x10704 = StreamOutNew(BurstCmdBus)
    val x10705 = StreamIn(field="data").name("x10705").ctrl(x10732) // x10705 = StreamInNew(BurstDataBus())
    val x10720 = UnitController(style=SeqPipe, level=InnerControl).name("x10720").ctrl(x10732) // UnitPipe(List(b6719, b6710, b6711, b6663),Block(x10719))
    val x10706 = OpDef(op=FixAdd, inputs=List(b6708, b6718)).name("x10706").ctrl(x10720) // FixAdd(b6708,b6718)
    val x10707 = x10706 // FixConvert(x10706,TRUE,_32,_0) (Same Type. No op)
    val x10708 = OpDef(op=FixSla, inputs=List(x10707, Const(9))).name("x10708").ctrl(x10720) // FixLsh(x10707,Const(9))
    val x10709 = b6709 // FixConvert(b6709,TRUE,_32,_0) (Same Type. No op)
    val x10710 = OpDef(op=FixAdd, inputs=List(x10708, x10709)).name("x10710").ctrl(x10720) // FixAdd(x10708,x10709)
    val x10711 = OpDef(op=FixSla, inputs=List(x10710, Const(2))).name("x10711").ctrl(x10720) // FixLsh(x10710,Const(2))
    val x10712 = x10711 // FixConvert(x10711,TRUE,_64,_0)
    val x10713 = DramAddress(x10510).name("x10713").ctrl(x10720) // GetDRAMAddress(x10510)
    val x10715_x10714 = OpDef(op=FixAdd, inputs=List(x10712, x10713)).name("x10714").ctrl(x10720) // FixAdd(x10712,x10713)
    // x10715 = SimpleStruct(ArrayBuffer((offset,x10714), (size,Const(64)), (isLoad,Const(true))))
    val x10716 = OpDef(op=BitAnd, inputs=List(b6719, b6710)).name("x10716").ctrl(x10720) // And(b6719,b6710)
    val x10717 = OpDef(op=BitAnd, inputs=List(b6711, b6663)).name("x10717").ctrl(x10720) // And(b6711,b6663)
    val x10718 = OpDef(op=BitAnd, inputs=List(x10716, x10717)).name("x10718").ctrl(x10720) // And(x10716,x10717)
    val b11256_b11254 = WriteMem(b11254, x10715_x10714).name("b11256_b11254").ctrl(x10720) // StreamWrite(x10704,x10715,x10718)
    val b11257_b11255 = WriteMem(b11255, Const(64)).name("b11257_b11255").ctrl(x10720) // StreamWrite(x10704,x10715,x10718)
    val x10721 = FringeDenseLoad(dram=List(x10510), cmdStream=List(b11254, b11255), dataStream=List(x10705)).name("x10721").ctrl(x10732) // FringeDenseLoad(x10510,x10704,x10705)
    val x10722 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10722").ctrl(x10732) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10723 = CounterChain(List(x10722)).name("x10723").ctrl(x10732) // CounterChainNew(List(x10722))
    val x10731 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10723).name("x10731").ctrl(x10732) // UnrolledForeach(List(b6719, b6710, b6711, b6663),x10723,Block(Const(())),List(List(b6740)),List(List(b6741)))
    val b6740 = CounterIter(x10722, None).ctrl(x10731).name("b6740")
    val b6741 = Const(true).ctrl(x10731).name("b6741")
    val x10724 = OpDef(op=BitAnd, inputs=List(b6741, b6719)).name("x10724").ctrl(x10731) // And(b6741,b6719)
    val x10725 = OpDef(op=BitAnd, inputs=List(b6710, b6711)).name("x10725").ctrl(x10731) // And(b6710,b6711)
    val x10726 = OpDef(op=BitAnd, inputs=List(x10724, x10725)).name("x10726").ctrl(x10731) // And(x10724,x10725)
    val x10727 = OpDef(op=BitAnd, inputs=List(x10726, b6663)).name("x10727").ctrl(x10731) // And(x10726,b6663)
    val x10728_x10728 = ReadMem(x10705).name("x10728").ctrl(x10731) // ParStreamRead(x10705,List(x10727))
    val x10729_x10729 = x10728_x10728 // x10729 = VectorApply(x10728,0)
    val x10730 = StoreBanks(List(x10698_d0_b0), List(b6718, b6740), x10729_x10729).name("x10730").ctrl(x10731) // ParSRAMStore(x10698,List(List(b6718, b6740)),List(x10729),List(x10727))
    val x10733 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10733").ctrl(x10806) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10734 = CounterChain(List(x10733)).name("x10734").ctrl(x10806) // CounterChainNew(List(x10733))
    val x10805 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10734).name("x10805").ctrl(x10806) // UnrolledForeach(List(b6710, b6711, b6663),x10734,Block(Const(())),List(List(b6753)),List(List(b6754)))
    val b6753 = CounterIter(x10733, Some(0)).ctrl(x10805).name("b6753")
    val b6754 = Const(true).ctrl(x10805).name("b6754")
    val x10735 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10735").ctrl(x10805) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10736 = CounterChain(List(x10735)).name("x10736").ctrl(x10805) // CounterChainNew(List(x10735))
    val x10804 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10736).name("x10804").ctrl(x10805) // UnrolledForeach(List(b6754, b6710, b6711, b6663),x10736,Block(Const(())),List(List(b6757)),List(List(b6758)))
    val b6757 = CounterIter(x10735, Some(0)).ctrl(x10804).name("b6757")
    val b6758 = Const(true).ctrl(x10804).name("b6758")
    val x10737_d0 = Reg(init=Some(0.0)).name("x10737_d0").ctrl(x10804) // x10737 = RegNew(Const(0))
    isAccum(x10737_d0) = false
    bufferDepthOf(x10737_d0) = 2
    val x10737_d1 = Reg(init=Some(0.0)).name("x10737_d1").ctrl(x10804) // x10737 = RegNew(Const(0))
    isAccum(x10737_d1) = true
    bufferDepthOf(x10737_d1) = 1
    val x10738 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10738").ctrl(x10804) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10739 = CounterChain(List(x10738)).name("x10739").ctrl(x10804) // CounterChainNew(List(x10738))
    val x10765 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10739).name("x10765").ctrl(x10804) // UnrolledReduce(List(b6758, b6754, b6710, b6711, b6663),x10739,x10737,Block((x10737) => Const(())),List(List(b6762)),List(List(b6763)))
    val b6762 = CounterIter(x10738, None).ctrl(x10765).name("b6762")
    val b6763 = Const(true).ctrl(x10765).name("b6763")
    val x10740 = OpDef(op=FixAdd, inputs=List(b6708, b6762)).name("x10740").ctrl(x10765) // FixAdd(b6708,b6762)
    val x10741 = OpDef(op=BitAnd, inputs=List(b6763, b6758)).name("x10741").ctrl(x10765) // And(b6763,b6758)
    val x10742 = OpDef(op=BitAnd, inputs=List(b6754, b6710)).name("x10742").ctrl(x10765) // And(b6754,b6710)
    val x10743 = OpDef(op=BitAnd, inputs=List(b6711, b6663)).name("x10743").ctrl(x10765) // And(b6711,b6663)
    val x10744 = OpDef(op=BitAnd, inputs=List(x10741, x10742)).name("x10744").ctrl(x10765) // And(x10741,x10742)
    val x10745 = OpDef(op=BitAnd, inputs=List(x10744, x10743)).name("x10745").ctrl(x10765) // And(x10744,x10743)
    val x10746 = LoadBanks(List(x10558_d3_b0), List(b6753, x10740)).name("x10746").ctrl(x10765) // ParSRAMLoad(x10558,List(List(b6753, x10740)),List(x10745))
    val x10747 = x10746 // x10747 = VectorApply(x10746,0)
    val x10748 = LoadBanks(List(x10698_d0_b0), List(b6762, b6757)).name("x10748").ctrl(x10765) // ParSRAMLoad(x10698,List(List(b6762, b6757)),List(x10745))
    val x10749 = x10748 // x10749 = VectorApply(x10748,0)
    val x10750 = OpDef(op=FixMul, inputs=List(x10747, x10749)).name("x10750").ctrl(x10765) // FixMul(x10747,x10749)
    val x10751 = OpDef(op=FixSub, inputs=List(x10740, Const(512))).name("x10751").ctrl(x10765) // FixSub(x10740,Const(512))
    val x10752 = LoadBanks(List(x10559_d4_b0, x10559_d4_b1, x10559_d4_b2, x10559_d4_b3, x10559_d4_b4, x10559_d4_b5, x10559_d4_b6, x10559_d4_b7, x10559_d4_b8, x10559_d4_b9, x10559_d4_b10, x10559_d4_b11, x10559_d4_b12, x10559_d4_b13, x10559_d4_b14, x10559_d4_b15), List(b6753, x10751)).name("x10752").ctrl(x10765) // ParSRAMLoad(x10559,List(List(b6753, x10751)),List(x10745))
    val x10753 = x10752 // x10753 = VectorApply(x10752,0)
    val x10754 = OpDef(op=FixMul, inputs=List(x10753, x10749)).name("x10754").ctrl(x10765) // FixMul(x10753,x10749)
    val x10755 = OpDef(op=FixLt, inputs=List(x10740, Const(512))).name("x10755").ctrl(x10765) // FixLt(x10740,Const(512))
    val x10756 = OpDef(op=MuxOp, inputs=List(x10755, x10750, x10754)).name("x10756").ctrl(x10765) // Mux(x10755,x10750,x10754)
    val x10757 = ReadMem(x10737_d1).name("x10757").ctrl(x10765) // RegRead(x10737)
    val x10758 = OpDef(op=FixEql, inputs=List(b6762, Const(0))).name("x10758").ctrl(x10765) // FixEql(b6762,Const(0))
    val x10759 = ReduceAccumOp(op=FixAdd, input=x10756, accum=x10757).name("x10759").ctrl(x10765) // FixAdd(x10756,x10757)
    val x10760 = OpDef(op=BitAnd, inputs=List(b6758, b6754)).name("x10760").ctrl(x10765) // And(b6758,b6754)
    val x10761 = OpDef(op=BitAnd, inputs=List(b6710, b6711)).name("x10761").ctrl(x10765) // And(b6710,b6711)
    val x10762 = OpDef(op=BitAnd, inputs=List(x10760, x10761)).name("x10762").ctrl(x10765) // And(x10760,x10761)
    val x10763 = OpDef(op=BitAnd, inputs=List(x10762, b6663)).name("x10763").ctrl(x10765) // And(x10762,b6663)
    val x10764_x10737_d0 = WriteMem(x10737_d0, x10759).name("x10764_x10737_d0").ctrl(x10765) // RegWrite(x10737,x10759,x10763)
    val x10764_x10737_d1 = WriteMem(x10737_d1, x10759).name("x10764_x10737_d1").ctrl(x10765) // RegWrite(x10737,x10759,x10763)
    val x10803 = UnitController(style=SeqPipe, level=InnerControl).name("x10803").ctrl(x10804) // UnitPipe(List(b6758, b6754, b6710, b6711, b6663),Block(Const(())))
    val x10766 = OpDef(op=FixAdd, inputs=List(b6709, b6757)).name("x10766").ctrl(x10803) // FixAdd(b6709,b6757)
    val x10767 = ReadMem(x10737_d0).name("x10767").ctrl(x10803) // RegRead(x10737)
    val x10768 = OpDef(op=FixEql, inputs=List(b6708, Const(0))).name("x10768").ctrl(x10803) // FixEql(b6708,Const(0))
    val x10769 = OpDef(op=BitAnd, inputs=List(b6758, b6754)).name("x10769").ctrl(x10803) // And(b6758,b6754)
    val x10770 = OpDef(op=BitAnd, inputs=List(b6710, b6711)).name("x10770").ctrl(x10803) // And(b6710,b6711)
    val x10771 = OpDef(op=BitAnd, inputs=List(x10769, x10770)).name("x10771").ctrl(x10803) // And(x10769,x10770)
    val x10772 = OpDef(op=BitAnd, inputs=List(x10771, b6663)).name("x10772").ctrl(x10803) // And(x10771,b6663)
    val x10773 = LoadBanks(List(x10565_d3_b0), List(Const(0), x10766)).name("x10773").ctrl(x10803) // SRAMLoad(x10565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x10766),Const(0),x10772)
    val x10774 = LoadBanks(List(x10561_d1_b0), List(b6753, x10766)).name("x10774").ctrl(x10803) // SRAMLoad(x10561,ArrayBuffer(Const(1), Const(512)),List(b6753, x10766),Const(0),x10772)
    val x10775 = OpDef(op=MuxOp, inputs=List(x10768, x10773, x10774)).name("x10775").ctrl(x10803) // Mux(x10768,x10773,x10774)
    val x10776 = OpDef(op=FixAdd, inputs=List(x10767, x10775)).name("x10776").ctrl(x10803) // FixAdd(x10767,x10775)
    val x10777 = OpDef(op=FixLeq, inputs=List(Const(1520), b6708)).name("x10777").ctrl(x10803) // FixLeq(Const(1520),b6708)
    // x10778 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x10778_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).ctrl(x10803).name("x10778_int1")
    val x10778_int2 = OpDef(op=FixSla, inputs=List(x10778_int1, Const(24))).ctrl(x10803).name("x10778_int2")
    val x10778_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).ctrl(x10803).name("x10778_frac1")
    val x10778_frac2 = OpDef(op=FixSla, inputs=List(x10778_frac1, Const(24))).ctrl(x10803).name("x10778_frac2")
    val x10778 = OpDef(op=BitOr, inputs=List(x10778_int2, x10778_frac2)).ctrl(x10803).name("x10778")
    // }
    val x10779 = OpDef(op=FixAdd, inputs=List(x10776, x10778)).name("x10779").ctrl(x10803) // FixAdd(x10776,x10778)
    val x10780 = OpDef(op=FixSra, inputs=List(x10779, Const(1))).name("x10780").ctrl(x10803) // FixRsh(x10779,Const(1))
    val x10781 = x10780 // FixConvert(x10780,TRUE,_8,_24) (Same Type. No op)
    val x10782 = OpDef(op=FixAbs, inputs=List(x10781)).name("x10782").ctrl(x10803) // FixAbs(x10781)
    val x10783 = OpDef(op=FixLt, inputs=List(Const(2.5), x10782)).name("x10783").ctrl(x10803) // FixLt(Const(2.5),x10782)
    val x10784 = OpDef(op=BitNot, inputs=List(x10783)).name("x10784").ctrl(x10803) // Not(x10783)
    val x10785 = OpDef(op=FixLt, inputs=List(Const(0.5), x10782)).name("x10785").ctrl(x10803) // FixLt(Const(0.5),x10782)
    val x10786 = OpDef(op=FixLeq, inputs=List(x10782, Const(2.5))).name("x10786").ctrl(x10803) // FixLeq(x10782,Const(2.5))
    val x10787 = OpDef(op=BitAnd, inputs=List(x10785, x10786)).name("x10787").ctrl(x10803) // And(x10785,x10786)
    val x10788 = OpDef(op=BitAnd, inputs=List(x10787, x10784)).name("x10788").ctrl(x10803) // And(x10787,x10784)
    val x10789 = OpDef(op=BitNot, inputs=List(x10787)).name("x10789").ctrl(x10803) // Not(x10787)
    val x10790 = OpDef(op=BitAnd, inputs=List(x10789, x10784)).name("x10790").ctrl(x10803) // And(x10789,x10784)
    val x10791 = OpDef(op=FixSra, inputs=List(x10782, Const(2))).name("x10791").ctrl(x10803) // FixRsh(x10782,Const(2))
    val x10792 = OpDef(op=FixAdd, inputs=List(x10791, Const(0.375))).name("x10792").ctrl(x10803) // FixAdd(x10791,Const(0.375))
    val x10793 = OpDef(op=MuxOp, inputs=List(x10788, x10792, x10782)).name("x10793").ctrl(x10803) // Mux(x10788,x10792,x10782)
    val x10794 = OpDef(op=MuxOp, inputs=List(x10783, Const(1.0), x10793)).name("x10794").ctrl(x10803) // Mux(x10783,Const(1),x10793)
    val x10795 = OpDef(op=FixNeg, inputs=List(x10794)).name("x10795").ctrl(x10803) // FixNeg(x10794)
    val x10796 = OpDef(op=FixLt, inputs=List(x10781, Const(0.0))).name("x10796").ctrl(x10803) // FixLt(x10781,Const(0))
    val x10797 = OpDef(op=MuxOp, inputs=List(x10796, x10795, x10794)).name("x10797").ctrl(x10803) // Mux(x10796,x10795,x10794)
    val x10798 = x10797 // FixConvert(x10797,TRUE,_8,_24) (Same Type. No op)
    val x10799 = OpDef(op=FixAdd, inputs=List(x10798, Const(1.0))).name("x10799").ctrl(x10803) // FixAdd(x10798,Const(1))
    val x10800 = OpDef(op=FixSra, inputs=List(x10799, Const(1))).name("x10800").ctrl(x10803) // FixRsh(x10799,Const(1))
    val x10801 = OpDef(op=MuxOp, inputs=List(x10777, x10800, x10776)).name("x10801").ctrl(x10803) // Mux(x10777,x10800,x10776)
    val x10802 = StoreBanks(List(x10561_d0_b0, x10561_d1_b0), List(b6753, x10766), x10801).name("x10802").ctrl(x10803) // SRAMStore(x10561,ArrayBuffer(Const(1), Const(512)),List(b6753, x10766),Const(0),x10801,x10772)
    val x10807 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x10807").ctrl(x11179) // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x10808 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x10808").ctrl(x11179) // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x10809 = CounterChain(List(x10808,x10807)).name("x10809").ctrl(x11179) // CounterChainNew(List(x10808, x10807))
    val x10916 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10809).name("x10916").ctrl(x11179) // UnrolledForeach(List(b6663),x10809,Block(Const(())),List(List(b6834), List(b6835)),List(List(b6836), List(b6837)))
    val b6834 = CounterIter(x10808, Some(0)).ctrl(x10916).name("b6834")
    val b6836 = Const(true).ctrl(x10916).name("b6836")
    val b6835 = CounterIter(x10807, Some(0)).ctrl(x10916).name("b6835")
    val b6837 = Const(true).ctrl(x10916).name("b6837")
    val x10810_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x10810_d0_b0").ctrl(x10916) // x10810 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x10810_d0_b0) = false
    bufferDepthOf(x10810_d0_b0) = 2
    val x10813 = UnitController(style=SeqPipe, level=InnerControl).name("x10813").ctrl(x10916) // UnitPipe(List(b6836, b6837, b6663),Block(Const(())))
    val x10811 = OpDef(op=FixAdd, inputs=List(b6834, Const(16))).name("x10811").ctrl(x10813) // FixAdd(b6834,Const(16))
    val x10812 = OpDef(op=FixAdd, inputs=List(b6835, Const(16))).name("x10812").ctrl(x10813) // FixAdd(b6835,Const(16))
    val x10814 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10814").ctrl(x10916) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10815 = CounterChain(List(x10814)).name("x10815").ctrl(x10916) // CounterChainNew(List(x10814))
    val x10844 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10815).name("x10844").ctrl(x10916) // UnrolledForeach(List(b6836, b6837, b6663),x10815,Block(Const(())),List(List(b6844)),List(List(b6845)))
    val b6844 = CounterIter(x10814, Some(0)).ctrl(x10844).name("b6844")
    val b6845 = Const(true).ctrl(x10844).name("b6845")
    val b11258 = StreamOut(field="offset").name("b11258").ctrl(x10844) // x10816 = StreamOutNew(BurstCmdBus)
    val b11259 = StreamOut(field="size").name("b11259").ctrl(x10844) // x10816 = StreamOutNew(BurstCmdBus)
    val x10817 = StreamIn(field="data").name("x10817").ctrl(x10844) // x10817 = StreamInNew(BurstDataBus())
    val x10832 = UnitController(style=SeqPipe, level=InnerControl).name("x10832").ctrl(x10844) // UnitPipe(List(b6845, b6836, b6837, b6663),Block(x10831))
    val x10818 = OpDef(op=FixAdd, inputs=List(b6834, b6844)).name("x10818").ctrl(x10832) // FixAdd(b6834,b6844)
    val x10819 = x10818 // FixConvert(x10818,TRUE,_32,_0) (Same Type. No op)
    val x10820 = OpDef(op=FixSla, inputs=List(x10819, Const(9))).name("x10820").ctrl(x10832) // FixLsh(x10819,Const(9))
    val x10821 = b6835 // FixConvert(b6835,TRUE,_32,_0) (Same Type. No op)
    val x10822 = OpDef(op=FixAdd, inputs=List(x10820, x10821)).name("x10822").ctrl(x10832) // FixAdd(x10820,x10821)
    val x10823 = OpDef(op=FixSla, inputs=List(x10822, Const(2))).name("x10823").ctrl(x10832) // FixLsh(x10822,Const(2))
    val x10824 = x10823 // FixConvert(x10823,TRUE,_64,_0)
    val x10825 = DramAddress(x10511).name("x10825").ctrl(x10832) // GetDRAMAddress(x10511)
    val x10827_x10826 = OpDef(op=FixAdd, inputs=List(x10824, x10825)).name("x10826").ctrl(x10832) // FixAdd(x10824,x10825)
    // x10827 = SimpleStruct(ArrayBuffer((offset,x10826), (size,Const(64)), (isLoad,Const(true))))
    val x10828 = OpDef(op=BitAnd, inputs=List(b6845, b6836)).name("x10828").ctrl(x10832) // And(b6845,b6836)
    val x10829 = OpDef(op=BitAnd, inputs=List(b6837, b6663)).name("x10829").ctrl(x10832) // And(b6837,b6663)
    val x10830 = OpDef(op=BitAnd, inputs=List(x10828, x10829)).name("x10830").ctrl(x10832) // And(x10828,x10829)
    val b11260_b11258 = WriteMem(b11258, x10827_x10826).name("b11260_b11258").ctrl(x10832) // StreamWrite(x10816,x10827,x10830)
    val b11261_b11259 = WriteMem(b11259, Const(64)).name("b11261_b11259").ctrl(x10832) // StreamWrite(x10816,x10827,x10830)
    val x10833 = FringeDenseLoad(dram=List(x10511), cmdStream=List(b11258, b11259), dataStream=List(x10817)).name("x10833").ctrl(x10844) // FringeDenseLoad(x10511,x10816,x10817)
    val x10834 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10834").ctrl(x10844) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10835 = CounterChain(List(x10834)).name("x10835").ctrl(x10844) // CounterChainNew(List(x10834))
    val x10843 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10835).name("x10843").ctrl(x10844) // UnrolledForeach(List(b6845, b6836, b6837, b6663),x10835,Block(Const(())),List(List(b6866)),List(List(b6867)))
    val b6866 = CounterIter(x10834, None).ctrl(x10843).name("b6866")
    val b6867 = Const(true).ctrl(x10843).name("b6867")
    val x10836 = OpDef(op=BitAnd, inputs=List(b6867, b6845)).name("x10836").ctrl(x10843) // And(b6867,b6845)
    val x10837 = OpDef(op=BitAnd, inputs=List(b6836, b6837)).name("x10837").ctrl(x10843) // And(b6836,b6837)
    val x10838 = OpDef(op=BitAnd, inputs=List(x10836, x10837)).name("x10838").ctrl(x10843) // And(x10836,x10837)
    val x10839 = OpDef(op=BitAnd, inputs=List(x10838, b6663)).name("x10839").ctrl(x10843) // And(x10838,b6663)
    val x10840_x10840 = ReadMem(x10817).name("x10840").ctrl(x10843) // ParStreamRead(x10817,List(x10839))
    val x10841_x10841 = x10840_x10840 // x10841 = VectorApply(x10840,0)
    val x10842 = StoreBanks(List(x10810_d0_b0), List(b6844, b6866), x10841_x10841).name("x10842").ctrl(x10843) // ParSRAMStore(x10810,List(List(b6844, b6866)),List(x10841),List(x10839))
    val x10845 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10845").ctrl(x10916) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10846 = CounterChain(List(x10845)).name("x10846").ctrl(x10916) // CounterChainNew(List(x10845))
    val x10915 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10846).name("x10915").ctrl(x10916) // UnrolledForeach(List(b6836, b6837, b6663),x10846,Block(Const(())),List(List(b6879)),List(List(b6880)))
    val b6879 = CounterIter(x10845, Some(0)).ctrl(x10915).name("b6879")
    val b6880 = Const(true).ctrl(x10915).name("b6880")
    val x10847 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10847").ctrl(x10915) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10848 = CounterChain(List(x10847)).name("x10848").ctrl(x10915) // CounterChainNew(List(x10847))
    val x10914 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10848).name("x10914").ctrl(x10915) // UnrolledForeach(List(b6880, b6836, b6837, b6663),x10848,Block(Const(())),List(List(b6883)),List(List(b6884)))
    val b6883 = CounterIter(x10847, Some(0)).ctrl(x10914).name("b6883")
    val b6884 = Const(true).ctrl(x10914).name("b6884")
    val x10849_d0 = Reg(init=Some(0.0)).name("x10849_d0").ctrl(x10914) // x10849 = RegNew(Const(0))
    isAccum(x10849_d0) = false
    bufferDepthOf(x10849_d0) = 2
    val x10849_d1 = Reg(init=Some(0.0)).name("x10849_d1").ctrl(x10914) // x10849 = RegNew(Const(0))
    isAccum(x10849_d1) = true
    bufferDepthOf(x10849_d1) = 1
    val x10850 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10850").ctrl(x10914) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10851 = CounterChain(List(x10850)).name("x10851").ctrl(x10914) // CounterChainNew(List(x10850))
    val x10877 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10851).name("x10877").ctrl(x10914) // UnrolledReduce(List(b6884, b6880, b6836, b6837, b6663),x10851,x10849,Block((x10849) => Const(())),List(List(b6888)),List(List(b6889)))
    val b6888 = CounterIter(x10850, None).ctrl(x10877).name("b6888")
    val b6889 = Const(true).ctrl(x10877).name("b6889")
    val x10852 = OpDef(op=FixAdd, inputs=List(b6834, b6888)).name("x10852").ctrl(x10877) // FixAdd(b6834,b6888)
    val x10853 = OpDef(op=BitAnd, inputs=List(b6889, b6884)).name("x10853").ctrl(x10877) // And(b6889,b6884)
    val x10854 = OpDef(op=BitAnd, inputs=List(b6880, b6836)).name("x10854").ctrl(x10877) // And(b6880,b6836)
    val x10855 = OpDef(op=BitAnd, inputs=List(b6837, b6663)).name("x10855").ctrl(x10877) // And(b6837,b6663)
    val x10856 = OpDef(op=BitAnd, inputs=List(x10853, x10854)).name("x10856").ctrl(x10877) // And(x10853,x10854)
    val x10857 = OpDef(op=BitAnd, inputs=List(x10856, x10855)).name("x10857").ctrl(x10877) // And(x10856,x10855)
    val x10858 = LoadBanks(List(x10558_d2_b0), List(b6879, x10852)).name("x10858").ctrl(x10877) // ParSRAMLoad(x10558,List(List(b6879, x10852)),List(x10857))
    val x10859 = x10858 // x10859 = VectorApply(x10858,0)
    val x10860 = LoadBanks(List(x10810_d0_b0), List(b6888, b6883)).name("x10860").ctrl(x10877) // ParSRAMLoad(x10810,List(List(b6888, b6883)),List(x10857))
    val x10861 = x10860 // x10861 = VectorApply(x10860,0)
    val x10862 = OpDef(op=FixMul, inputs=List(x10859, x10861)).name("x10862").ctrl(x10877) // FixMul(x10859,x10861)
    val x10863 = OpDef(op=FixSub, inputs=List(x10852, Const(512))).name("x10863").ctrl(x10877) // FixSub(x10852,Const(512))
    val x10864 = LoadBanks(List(x10559_d3_b0, x10559_d3_b1, x10559_d3_b2, x10559_d3_b3, x10559_d3_b4, x10559_d3_b5, x10559_d3_b6, x10559_d3_b7, x10559_d3_b8, x10559_d3_b9, x10559_d3_b10, x10559_d3_b11, x10559_d3_b12, x10559_d3_b13, x10559_d3_b14, x10559_d3_b15), List(b6879, x10863)).name("x10864").ctrl(x10877) // ParSRAMLoad(x10559,List(List(b6879, x10863)),List(x10857))
    val x10865 = x10864 // x10865 = VectorApply(x10864,0)
    val x10866 = OpDef(op=FixMul, inputs=List(x10865, x10861)).name("x10866").ctrl(x10877) // FixMul(x10865,x10861)
    val x10867 = OpDef(op=FixLt, inputs=List(x10852, Const(512))).name("x10867").ctrl(x10877) // FixLt(x10852,Const(512))
    val x10868 = OpDef(op=MuxOp, inputs=List(x10867, x10862, x10866)).name("x10868").ctrl(x10877) // Mux(x10867,x10862,x10866)
    val x10869 = ReadMem(x10849_d1).name("x10869").ctrl(x10877) // RegRead(x10849)
    val x10870 = OpDef(op=FixEql, inputs=List(b6888, Const(0))).name("x10870").ctrl(x10877) // FixEql(b6888,Const(0))
    val x10871 = ReduceAccumOp(op=FixAdd, input=x10868, accum=x10869).name("x10871").ctrl(x10877) // FixAdd(x10868,x10869)
    val x10872 = OpDef(op=BitAnd, inputs=List(b6884, b6880)).name("x10872").ctrl(x10877) // And(b6884,b6880)
    val x10873 = OpDef(op=BitAnd, inputs=List(b6836, b6837)).name("x10873").ctrl(x10877) // And(b6836,b6837)
    val x10874 = OpDef(op=BitAnd, inputs=List(x10872, x10873)).name("x10874").ctrl(x10877) // And(x10872,x10873)
    val x10875 = OpDef(op=BitAnd, inputs=List(x10874, b6663)).name("x10875").ctrl(x10877) // And(x10874,b6663)
    val x10876_x10849_d0 = WriteMem(x10849_d0, x10871).name("x10876_x10849_d0").ctrl(x10877) // RegWrite(x10849,x10871,x10875)
    val x10876_x10849_d1 = WriteMem(x10849_d1, x10871).name("x10876_x10849_d1").ctrl(x10877) // RegWrite(x10849,x10871,x10875)
    val x10913 = UnitController(style=SeqPipe, level=InnerControl).name("x10913").ctrl(x10914) // UnitPipe(List(b6884, b6880, b6836, b6837, b6663),Block(Const(())))
    val x10878 = OpDef(op=FixAdd, inputs=List(b6835, b6883)).name("x10878").ctrl(x10913) // FixAdd(b6835,b6883)
    val x10879 = ReadMem(x10849_d0).name("x10879").ctrl(x10913) // RegRead(x10849)
    val x10880 = OpDef(op=FixEql, inputs=List(b6834, Const(0))).name("x10880").ctrl(x10913) // FixEql(b6834,Const(0))
    val x10881 = OpDef(op=FixAdd, inputs=List(x10878, Const(512))).name("x10881").ctrl(x10913) // FixAdd(x10878,Const(512))
    val x10882 = OpDef(op=BitAnd, inputs=List(b6884, b6880)).name("x10882").ctrl(x10913) // And(b6884,b6880)
    val x10883 = OpDef(op=BitAnd, inputs=List(b6836, b6837)).name("x10883").ctrl(x10913) // And(b6836,b6837)
    val x10884 = OpDef(op=BitAnd, inputs=List(x10882, x10883)).name("x10884").ctrl(x10913) // And(x10882,x10883)
    val x10885 = OpDef(op=BitAnd, inputs=List(x10884, b6663)).name("x10885").ctrl(x10913) // And(x10884,b6663)
    val x10886 = LoadBanks(List(x10565_d2_b0), List(Const(0), x10881)).name("x10886").ctrl(x10913) // SRAMLoad(x10565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x10881),Const(0),x10885)
    val x10887 = LoadBanks(List(x10562_d1_b0), List(b6879, x10878)).name("x10887").ctrl(x10913) // SRAMLoad(x10562,ArrayBuffer(Const(1), Const(512)),List(b6879, x10878),Const(0),x10885)
    val x10888 = OpDef(op=MuxOp, inputs=List(x10880, x10886, x10887)).name("x10888").ctrl(x10913) // Mux(x10880,x10886,x10887)
    val x10889 = OpDef(op=FixAdd, inputs=List(x10879, x10888)).name("x10889").ctrl(x10913) // FixAdd(x10879,x10888)
    val x10890 = OpDef(op=FixLeq, inputs=List(Const(1520), b6834)).name("x10890").ctrl(x10913) // FixLeq(Const(1520),b6834)
    // x10891 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x10891_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).ctrl(x10913).name("x10891_int1")
    val x10891_int2 = OpDef(op=FixSla, inputs=List(x10891_int1, Const(24))).ctrl(x10913).name("x10891_int2")
    val x10891_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).ctrl(x10913).name("x10891_frac1")
    val x10891_frac2 = OpDef(op=FixSla, inputs=List(x10891_frac1, Const(24))).ctrl(x10913).name("x10891_frac2")
    val x10891 = OpDef(op=BitOr, inputs=List(x10891_int2, x10891_frac2)).ctrl(x10913).name("x10891")
    // }
    val x10892 = OpDef(op=FixAdd, inputs=List(x10889, x10891)).name("x10892").ctrl(x10913) // FixAdd(x10889,x10891)
    val x10893 = x10892 // FixConvert(x10892,TRUE,_8,_24) (Same Type. No op)
    val x10894 = OpDef(op=FixAbs, inputs=List(x10893)).name("x10894").ctrl(x10913) // FixAbs(x10893)
    val x10895 = OpDef(op=FixLt, inputs=List(Const(2.5), x10894)).name("x10895").ctrl(x10913) // FixLt(Const(2.5),x10894)
    val x10896 = OpDef(op=BitNot, inputs=List(x10895)).name("x10896").ctrl(x10913) // Not(x10895)
    val x10897 = OpDef(op=FixLt, inputs=List(Const(0.5), x10894)).name("x10897").ctrl(x10913) // FixLt(Const(0.5),x10894)
    val x10898 = OpDef(op=FixLeq, inputs=List(x10894, Const(2.5))).name("x10898").ctrl(x10913) // FixLeq(x10894,Const(2.5))
    val x10899 = OpDef(op=BitAnd, inputs=List(x10897, x10898)).name("x10899").ctrl(x10913) // And(x10897,x10898)
    val x10900 = OpDef(op=BitAnd, inputs=List(x10899, x10896)).name("x10900").ctrl(x10913) // And(x10899,x10896)
    val x10901 = OpDef(op=BitNot, inputs=List(x10899)).name("x10901").ctrl(x10913) // Not(x10899)
    val x10902 = OpDef(op=BitAnd, inputs=List(x10901, x10896)).name("x10902").ctrl(x10913) // And(x10901,x10896)
    val x10903 = OpDef(op=FixSra, inputs=List(x10894, Const(2))).name("x10903").ctrl(x10913) // FixRsh(x10894,Const(2))
    val x10904 = OpDef(op=FixAdd, inputs=List(x10903, Const(0.375))).name("x10904").ctrl(x10913) // FixAdd(x10903,Const(0.375))
    val x10905 = OpDef(op=MuxOp, inputs=List(x10900, x10904, x10894)).name("x10905").ctrl(x10913) // Mux(x10900,x10904,x10894)
    val x10906 = OpDef(op=MuxOp, inputs=List(x10895, Const(1.0), x10905)).name("x10906").ctrl(x10913) // Mux(x10895,Const(1),x10905)
    val x10907 = OpDef(op=FixNeg, inputs=List(x10906)).name("x10907").ctrl(x10913) // FixNeg(x10906)
    val x10908 = OpDef(op=FixLt, inputs=List(x10893, Const(0.0))).name("x10908").ctrl(x10913) // FixLt(x10893,Const(0))
    val x10909 = OpDef(op=MuxOp, inputs=List(x10908, x10907, x10906)).name("x10909").ctrl(x10913) // Mux(x10908,x10907,x10906)
    val x10910 = x10909 // FixConvert(x10909,TRUE,_8,_24) (Same Type. No op)
    val x10911 = OpDef(op=MuxOp, inputs=List(x10890, x10910, x10889)).name("x10911").ctrl(x10913) // Mux(x10890,x10910,x10889)
    val x10912 = StoreBanks(List(x10562_d0_b0, x10562_d1_b0), List(b6879, x10878), x10911).name("x10912").ctrl(x10913) // SRAMStore(x10562,ArrayBuffer(Const(1), Const(512)),List(b6879, x10878),Const(0),x10911,x10885)
    val x10917 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x10917").ctrl(x11179) // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x10918 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x10918").ctrl(x11179) // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x10919 = CounterChain(List(x10918,x10917)).name("x10919").ctrl(x11179) // CounterChainNew(List(x10918, x10917))
    val x11029 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10919).name("x11029").ctrl(x11179) // UnrolledForeach(List(b6663),x10919,Block(Const(())),List(List(b6958), List(b6959)),List(List(b6960), List(b6961)))
    val b6958 = CounterIter(x10918, Some(0)).ctrl(x11029).name("b6958")
    val b6960 = Const(true).ctrl(x11029).name("b6960")
    val b6959 = CounterIter(x10917, Some(0)).ctrl(x11029).name("b6959")
    val b6961 = Const(true).ctrl(x11029).name("b6961")
    val x10920_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x10920_d0_b0").ctrl(x11029) // x10920 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x10920_d0_b0) = false
    bufferDepthOf(x10920_d0_b0) = 2
    val x10923 = UnitController(style=SeqPipe, level=InnerControl).name("x10923").ctrl(x11029) // UnitPipe(List(b6960, b6961, b6663),Block(Const(())))
    val x10921 = OpDef(op=FixAdd, inputs=List(b6958, Const(16))).name("x10921").ctrl(x10923) // FixAdd(b6958,Const(16))
    val x10922 = OpDef(op=FixAdd, inputs=List(b6959, Const(16))).name("x10922").ctrl(x10923) // FixAdd(b6959,Const(16))
    val x10924 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10924").ctrl(x11029) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10925 = CounterChain(List(x10924)).name("x10925").ctrl(x11029) // CounterChainNew(List(x10924))
    val x10954 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10925).name("x10954").ctrl(x11029) // UnrolledForeach(List(b6960, b6961, b6663),x10925,Block(Const(())),List(List(b6968)),List(List(b6969)))
    val b6968 = CounterIter(x10924, Some(0)).ctrl(x10954).name("b6968")
    val b6969 = Const(true).ctrl(x10954).name("b6969")
    val b11262 = StreamOut(field="offset").name("b11262").ctrl(x10954) // x10926 = StreamOutNew(BurstCmdBus)
    val b11263 = StreamOut(field="size").name("b11263").ctrl(x10954) // x10926 = StreamOutNew(BurstCmdBus)
    val x10927 = StreamIn(field="data").name("x10927").ctrl(x10954) // x10927 = StreamInNew(BurstDataBus())
    val x10942 = UnitController(style=SeqPipe, level=InnerControl).name("x10942").ctrl(x10954) // UnitPipe(List(b6969, b6960, b6961, b6663),Block(x10941))
    val x10928 = OpDef(op=FixAdd, inputs=List(b6958, b6968)).name("x10928").ctrl(x10942) // FixAdd(b6958,b6968)
    val x10929 = x10928 // FixConvert(x10928,TRUE,_32,_0) (Same Type. No op)
    val x10930 = OpDef(op=FixSla, inputs=List(x10929, Const(9))).name("x10930").ctrl(x10942) // FixLsh(x10929,Const(9))
    val x10931 = b6959 // FixConvert(b6959,TRUE,_32,_0) (Same Type. No op)
    val x10932 = OpDef(op=FixAdd, inputs=List(x10930, x10931)).name("x10932").ctrl(x10942) // FixAdd(x10930,x10931)
    val x10933 = OpDef(op=FixSla, inputs=List(x10932, Const(2))).name("x10933").ctrl(x10942) // FixLsh(x10932,Const(2))
    val x10934 = x10933 // FixConvert(x10933,TRUE,_64,_0)
    val x10935 = DramAddress(x10512).name("x10935").ctrl(x10942) // GetDRAMAddress(x10512)
    val x10937_x10936 = OpDef(op=FixAdd, inputs=List(x10934, x10935)).name("x10936").ctrl(x10942) // FixAdd(x10934,x10935)
    // x10937 = SimpleStruct(ArrayBuffer((offset,x10936), (size,Const(64)), (isLoad,Const(true))))
    val x10938 = OpDef(op=BitAnd, inputs=List(b6969, b6960)).name("x10938").ctrl(x10942) // And(b6969,b6960)
    val x10939 = OpDef(op=BitAnd, inputs=List(b6961, b6663)).name("x10939").ctrl(x10942) // And(b6961,b6663)
    val x10940 = OpDef(op=BitAnd, inputs=List(x10938, x10939)).name("x10940").ctrl(x10942) // And(x10938,x10939)
    val b11264_b11262 = WriteMem(b11262, x10937_x10936).name("b11264_b11262").ctrl(x10942) // StreamWrite(x10926,x10937,x10940)
    val b11265_b11263 = WriteMem(b11263, Const(64)).name("b11265_b11263").ctrl(x10942) // StreamWrite(x10926,x10937,x10940)
    val x10943 = FringeDenseLoad(dram=List(x10512), cmdStream=List(b11262, b11263), dataStream=List(x10927)).name("x10943").ctrl(x10954) // FringeDenseLoad(x10512,x10926,x10927)
    val x10944 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10944").ctrl(x10954) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10945 = CounterChain(List(x10944)).name("x10945").ctrl(x10954) // CounterChainNew(List(x10944))
    val x10953 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10945).name("x10953").ctrl(x10954) // UnrolledForeach(List(b6969, b6960, b6961, b6663),x10945,Block(Const(())),List(List(b6990)),List(List(b6991)))
    val b6990 = CounterIter(x10944, None).ctrl(x10953).name("b6990")
    val b6991 = Const(true).ctrl(x10953).name("b6991")
    val x10946 = OpDef(op=BitAnd, inputs=List(b6991, b6969)).name("x10946").ctrl(x10953) // And(b6991,b6969)
    val x10947 = OpDef(op=BitAnd, inputs=List(b6960, b6961)).name("x10947").ctrl(x10953) // And(b6960,b6961)
    val x10948 = OpDef(op=BitAnd, inputs=List(x10946, x10947)).name("x10948").ctrl(x10953) // And(x10946,x10947)
    val x10949 = OpDef(op=BitAnd, inputs=List(x10948, b6663)).name("x10949").ctrl(x10953) // And(x10948,b6663)
    val x10950_x10950 = ReadMem(x10927).name("x10950").ctrl(x10953) // ParStreamRead(x10927,List(x10949))
    val x10951_x10951 = x10950_x10950 // x10951 = VectorApply(x10950,0)
    val x10952 = StoreBanks(List(x10920_d0_b0), List(b6968, b6990), x10951_x10951).name("x10952").ctrl(x10953) // ParSRAMStore(x10920,List(List(b6968, b6990)),List(x10951),List(x10949))
    val x10955 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10955").ctrl(x11029) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10956 = CounterChain(List(x10955)).name("x10956").ctrl(x11029) // CounterChainNew(List(x10955))
    val x11028 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10956).name("x11028").ctrl(x11029) // UnrolledForeach(List(b6960, b6961, b6663),x10956,Block(Const(())),List(List(b7003)),List(List(b7004)))
    val b7003 = CounterIter(x10955, Some(0)).ctrl(x11028).name("b7003")
    val b7004 = Const(true).ctrl(x11028).name("b7004")
    val x10957 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10957").ctrl(x11028) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10958 = CounterChain(List(x10957)).name("x10958").ctrl(x11028) // CounterChainNew(List(x10957))
    val x11027 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10958).name("x11027").ctrl(x11028) // UnrolledForeach(List(b7004, b6960, b6961, b6663),x10958,Block(Const(())),List(List(b7007)),List(List(b7008)))
    val b7007 = CounterIter(x10957, Some(0)).ctrl(x11027).name("b7007")
    val b7008 = Const(true).ctrl(x11027).name("b7008")
    val x10959_d0 = Reg(init=Some(0.0)).name("x10959_d0").ctrl(x11027) // x10959 = RegNew(Const(0))
    isAccum(x10959_d0) = false
    bufferDepthOf(x10959_d0) = 2
    val x10959_d1 = Reg(init=Some(0.0)).name("x10959_d1").ctrl(x11027) // x10959 = RegNew(Const(0))
    isAccum(x10959_d1) = true
    bufferDepthOf(x10959_d1) = 1
    val x10960 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10960").ctrl(x11027) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10961 = CounterChain(List(x10960)).name("x10961").ctrl(x11027) // CounterChainNew(List(x10960))
    val x10987 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10961).name("x10987").ctrl(x11027) // UnrolledReduce(List(b7008, b7004, b6960, b6961, b6663),x10961,x10959,Block((x10959) => Const(())),List(List(b7012)),List(List(b7013)))
    val b7012 = CounterIter(x10960, None).ctrl(x10987).name("b7012")
    val b7013 = Const(true).ctrl(x10987).name("b7013")
    val x10962 = OpDef(op=FixAdd, inputs=List(b6958, b7012)).name("x10962").ctrl(x10987) // FixAdd(b6958,b7012)
    val x10963 = OpDef(op=BitAnd, inputs=List(b7013, b7008)).name("x10963").ctrl(x10987) // And(b7013,b7008)
    val x10964 = OpDef(op=BitAnd, inputs=List(b7004, b6960)).name("x10964").ctrl(x10987) // And(b7004,b6960)
    val x10965 = OpDef(op=BitAnd, inputs=List(b6961, b6663)).name("x10965").ctrl(x10987) // And(b6961,b6663)
    val x10966 = OpDef(op=BitAnd, inputs=List(x10963, x10964)).name("x10966").ctrl(x10987) // And(x10963,x10964)
    val x10967 = OpDef(op=BitAnd, inputs=List(x10966, x10965)).name("x10967").ctrl(x10987) // And(x10966,x10965)
    val x10968 = LoadBanks(List(x10558_d1_b0), List(b7003, x10962)).name("x10968").ctrl(x10987) // ParSRAMLoad(x10558,List(List(b7003, x10962)),List(x10967))
    val x10969 = x10968 // x10969 = VectorApply(x10968,0)
    val x10970 = LoadBanks(List(x10920_d0_b0), List(b7012, b7007)).name("x10970").ctrl(x10987) // ParSRAMLoad(x10920,List(List(b7012, b7007)),List(x10967))
    val x10971 = x10970 // x10971 = VectorApply(x10970,0)
    val x10972 = OpDef(op=FixMul, inputs=List(x10969, x10971)).name("x10972").ctrl(x10987) // FixMul(x10969,x10971)
    val x10973 = OpDef(op=FixSub, inputs=List(x10962, Const(512))).name("x10973").ctrl(x10987) // FixSub(x10962,Const(512))
    val x10974 = LoadBanks(List(x10559_d2_b0, x10559_d2_b1, x10559_d2_b2, x10559_d2_b3, x10559_d2_b4, x10559_d2_b5, x10559_d2_b6, x10559_d2_b7, x10559_d2_b8, x10559_d2_b9, x10559_d2_b10, x10559_d2_b11, x10559_d2_b12, x10559_d2_b13, x10559_d2_b14, x10559_d2_b15), List(b7003, x10973)).name("x10974").ctrl(x10987) // ParSRAMLoad(x10559,List(List(b7003, x10973)),List(x10967))
    val x10975 = x10974 // x10975 = VectorApply(x10974,0)
    val x10976 = OpDef(op=FixMul, inputs=List(x10975, x10971)).name("x10976").ctrl(x10987) // FixMul(x10975,x10971)
    val x10977 = OpDef(op=FixLt, inputs=List(x10962, Const(512))).name("x10977").ctrl(x10987) // FixLt(x10962,Const(512))
    val x10978 = OpDef(op=MuxOp, inputs=List(x10977, x10972, x10976)).name("x10978").ctrl(x10987) // Mux(x10977,x10972,x10976)
    val x10979 = ReadMem(x10959_d1).name("x10979").ctrl(x10987) // RegRead(x10959)
    val x10980 = OpDef(op=FixEql, inputs=List(b7012, Const(0))).name("x10980").ctrl(x10987) // FixEql(b7012,Const(0))
    val x10981 = ReduceAccumOp(op=FixAdd, input=x10978, accum=x10979).name("x10981").ctrl(x10987) // FixAdd(x10978,x10979)
    val x10982 = OpDef(op=BitAnd, inputs=List(b7008, b7004)).name("x10982").ctrl(x10987) // And(b7008,b7004)
    val x10983 = OpDef(op=BitAnd, inputs=List(b6960, b6961)).name("x10983").ctrl(x10987) // And(b6960,b6961)
    val x10984 = OpDef(op=BitAnd, inputs=List(x10982, x10983)).name("x10984").ctrl(x10987) // And(x10982,x10983)
    val x10985 = OpDef(op=BitAnd, inputs=List(x10984, b6663)).name("x10985").ctrl(x10987) // And(x10984,b6663)
    val x10986_x10959_d0 = WriteMem(x10959_d0, x10981).name("x10986_x10959_d0").ctrl(x10987) // RegWrite(x10959,x10981,x10985)
    val x10986_x10959_d1 = WriteMem(x10959_d1, x10981).name("x10986_x10959_d1").ctrl(x10987) // RegWrite(x10959,x10981,x10985)
    val x11026 = UnitController(style=SeqPipe, level=InnerControl).name("x11026").ctrl(x11027) // UnitPipe(List(b7008, b7004, b6960, b6961, b6663),Block(Const(())))
    val x10988 = OpDef(op=FixAdd, inputs=List(b6959, b7007)).name("x10988").ctrl(x11026) // FixAdd(b6959,b7007)
    val x10989 = ReadMem(x10959_d0).name("x10989").ctrl(x11026) // RegRead(x10959)
    val x10990 = OpDef(op=FixEql, inputs=List(b6958, Const(0))).name("x10990").ctrl(x11026) // FixEql(b6958,Const(0))
    val x10991 = OpDef(op=FixAdd, inputs=List(x10988, Const(1024))).name("x10991").ctrl(x11026) // FixAdd(x10988,Const(1024))
    val x10992 = OpDef(op=BitAnd, inputs=List(b7008, b7004)).name("x10992").ctrl(x11026) // And(b7008,b7004)
    val x10993 = OpDef(op=BitAnd, inputs=List(b6960, b6961)).name("x10993").ctrl(x11026) // And(b6960,b6961)
    val x10994 = OpDef(op=BitAnd, inputs=List(x10992, x10993)).name("x10994").ctrl(x11026) // And(x10992,x10993)
    val x10995 = OpDef(op=BitAnd, inputs=List(x10994, b6663)).name("x10995").ctrl(x11026) // And(x10994,b6663)
    val x10996 = LoadBanks(List(x10565_d1_b0), List(Const(0), x10991)).name("x10996").ctrl(x11026) // SRAMLoad(x10565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x10991),Const(0),x10995)
    val x10997 = LoadBanks(List(x10563_d1_b0), List(b7003, x10988)).name("x10997").ctrl(x11026) // SRAMLoad(x10563,ArrayBuffer(Const(1), Const(512)),List(b7003, x10988),Const(0),x10995)
    val x10998 = OpDef(op=MuxOp, inputs=List(x10990, x10996, x10997)).name("x10998").ctrl(x11026) // Mux(x10990,x10996,x10997)
    val x10999 = OpDef(op=FixAdd, inputs=List(x10989, x10998)).name("x10999").ctrl(x11026) // FixAdd(x10989,x10998)
    val x11000 = OpDef(op=FixLeq, inputs=List(Const(1520), b6958)).name("x11000").ctrl(x11026) // FixLeq(Const(1520),b6958)
    // x11001 = FixConvert(Const(1),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x11001_int1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("01111111111111111111111111111111"))).ctrl(x11026).name("x11001_int1")
    val x11001_int2 = OpDef(op=FixSla, inputs=List(x11001_int1, Const(24))).ctrl(x11026).name("x11001_int2")
    val x11001_frac1 = OpDef(op=BitAnd, inputs=List(Const(1), Const("00000000000000000000000000000000"))).ctrl(x11026).name("x11001_frac1")
    val x11001_frac2 = OpDef(op=FixSla, inputs=List(x11001_frac1, Const(24))).ctrl(x11026).name("x11001_frac2")
    val x11001 = OpDef(op=BitOr, inputs=List(x11001_int2, x11001_frac2)).ctrl(x11026).name("x11001")
    // }
    val x11002 = OpDef(op=FixAdd, inputs=List(x10999, x11001)).name("x11002").ctrl(x11026) // FixAdd(x10999,x11001)
    val x11003 = OpDef(op=FixSra, inputs=List(x11002, Const(1))).name("x11003").ctrl(x11026) // FixRsh(x11002,Const(1))
    val x11004 = x11003 // FixConvert(x11003,TRUE,_8,_24) (Same Type. No op)
    val x11005 = OpDef(op=FixAbs, inputs=List(x11004)).name("x11005").ctrl(x11026) // FixAbs(x11004)
    val x11006 = OpDef(op=FixLt, inputs=List(Const(2.5), x11005)).name("x11006").ctrl(x11026) // FixLt(Const(2.5),x11005)
    val x11007 = OpDef(op=BitNot, inputs=List(x11006)).name("x11007").ctrl(x11026) // Not(x11006)
    val x11008 = OpDef(op=FixLt, inputs=List(Const(0.5), x11005)).name("x11008").ctrl(x11026) // FixLt(Const(0.5),x11005)
    val x11009 = OpDef(op=FixLeq, inputs=List(x11005, Const(2.5))).name("x11009").ctrl(x11026) // FixLeq(x11005,Const(2.5))
    val x11010 = OpDef(op=BitAnd, inputs=List(x11008, x11009)).name("x11010").ctrl(x11026) // And(x11008,x11009)
    val x11011 = OpDef(op=BitAnd, inputs=List(x11010, x11007)).name("x11011").ctrl(x11026) // And(x11010,x11007)
    val x11012 = OpDef(op=BitNot, inputs=List(x11010)).name("x11012").ctrl(x11026) // Not(x11010)
    val x11013 = OpDef(op=BitAnd, inputs=List(x11012, x11007)).name("x11013").ctrl(x11026) // And(x11012,x11007)
    val x11014 = OpDef(op=FixSra, inputs=List(x11005, Const(2))).name("x11014").ctrl(x11026) // FixRsh(x11005,Const(2))
    val x11015 = OpDef(op=FixAdd, inputs=List(x11014, Const(0.375))).name("x11015").ctrl(x11026) // FixAdd(x11014,Const(0.375))
    val x11016 = OpDef(op=MuxOp, inputs=List(x11011, x11015, x11005)).name("x11016").ctrl(x11026) // Mux(x11011,x11015,x11005)
    val x11017 = OpDef(op=MuxOp, inputs=List(x11006, Const(1.0), x11016)).name("x11017").ctrl(x11026) // Mux(x11006,Const(1),x11016)
    val x11018 = OpDef(op=FixNeg, inputs=List(x11017)).name("x11018").ctrl(x11026) // FixNeg(x11017)
    val x11019 = OpDef(op=FixLt, inputs=List(x11004, Const(0.0))).name("x11019").ctrl(x11026) // FixLt(x11004,Const(0))
    val x11020 = OpDef(op=MuxOp, inputs=List(x11019, x11018, x11017)).name("x11020").ctrl(x11026) // Mux(x11019,x11018,x11017)
    val x11021 = x11020 // FixConvert(x11020,TRUE,_8,_24) (Same Type. No op)
    val x11022 = OpDef(op=FixAdd, inputs=List(x11021, Const(1.0))).name("x11022").ctrl(x11026) // FixAdd(x11021,Const(1))
    val x11023 = OpDef(op=FixSra, inputs=List(x11022, Const(1))).name("x11023").ctrl(x11026) // FixRsh(x11022,Const(1))
    val x11024 = OpDef(op=MuxOp, inputs=List(x11000, x11023, x10999)).name("x11024").ctrl(x11026) // Mux(x11000,x11023,x10999)
    val x11025 = StoreBanks(List(x10563_d0_b0, x10563_d1_b0), List(b7003, x10988), x11024).name("x11025").ctrl(x11026) // SRAMStore(x10563,ArrayBuffer(Const(1), Const(512)),List(b7003, x10988),Const(0),x11024,x10995)
    val x11030 = Counter(min=Const(0), max=Const(512), step=Const(16), par=1).name("x11030").ctrl(x11179) // CounterNew(Const(0),Const(512),Const(16),Const(1))
    val x11031 = Counter(min=Const(0), max=Const(1536), step=Const(16), par=1).name("x11031").ctrl(x11179) // CounterNew(Const(0),Const(1536),Const(16),Const(1))
    val x11032 = CounterChain(List(x11031,x11030)).name("x11032").ctrl(x11179) // CounterChainNew(List(x11031, x11030))
    val x11142 = LoopController(style=MetaPipe, level=OuterControl, cchain=x11032).name("x11142").ctrl(x11179) // UnrolledForeach(List(b6663),x11032,Block(Const(())),List(List(b7085), List(b7086)),List(List(b7087), List(b7088)))
    val b7085 = CounterIter(x11031, Some(0)).ctrl(x11142).name("b7085")
    val b7087 = Const(true).ctrl(x11142).name("b7087")
    val b7086 = CounterIter(x11030, Some(0)).ctrl(x11142).name("b7086")
    val b7088 = Const(true).ctrl(x11142).name("b7088")
    val x11033_d0_b0 = SRAM(size=256, banking=Strided(banks=1, stride=16)).name("x11033_d0_b0").ctrl(x11142) // x11033 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x11033_d0_b0) = false
    bufferDepthOf(x11033_d0_b0) = 2
    val x11036 = UnitController(style=SeqPipe, level=InnerControl).name("x11036").ctrl(x11142) // UnitPipe(List(b7087, b7088, b6663),Block(Const(())))
    val x11034 = OpDef(op=FixAdd, inputs=List(b7085, Const(16))).name("x11034").ctrl(x11036) // FixAdd(b7085,Const(16))
    val x11035 = OpDef(op=FixAdd, inputs=List(b7086, Const(16))).name("x11035").ctrl(x11036) // FixAdd(b7086,Const(16))
    val x11037 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x11037").ctrl(x11142) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x11038 = CounterChain(List(x11037)).name("x11038").ctrl(x11142) // CounterChainNew(List(x11037))
    val x11067 = LoopController(style=StreamPipe, level=OuterControl, cchain=x11038).name("x11067").ctrl(x11142) // UnrolledForeach(List(b7087, b7088, b6663),x11038,Block(Const(())),List(List(b7095)),List(List(b7096)))
    val b7095 = CounterIter(x11037, Some(0)).ctrl(x11067).name("b7095")
    val b7096 = Const(true).ctrl(x11067).name("b7096")
    val b11266 = StreamOut(field="offset").name("b11266").ctrl(x11067) // x11039 = StreamOutNew(BurstCmdBus)
    val b11267 = StreamOut(field="size").name("b11267").ctrl(x11067) // x11039 = StreamOutNew(BurstCmdBus)
    val x11040 = StreamIn(field="data").name("x11040").ctrl(x11067) // x11040 = StreamInNew(BurstDataBus())
    val x11055 = UnitController(style=SeqPipe, level=InnerControl).name("x11055").ctrl(x11067) // UnitPipe(List(b7096, b7087, b7088, b6663),Block(x11054))
    val x11041 = OpDef(op=FixAdd, inputs=List(b7085, b7095)).name("x11041").ctrl(x11055) // FixAdd(b7085,b7095)
    val x11042 = x11041 // FixConvert(x11041,TRUE,_32,_0) (Same Type. No op)
    val x11043 = OpDef(op=FixSla, inputs=List(x11042, Const(9))).name("x11043").ctrl(x11055) // FixLsh(x11042,Const(9))
    val x11044 = b7086 // FixConvert(b7086,TRUE,_32,_0) (Same Type. No op)
    val x11045 = OpDef(op=FixAdd, inputs=List(x11043, x11044)).name("x11045").ctrl(x11055) // FixAdd(x11043,x11044)
    val x11046 = OpDef(op=FixSla, inputs=List(x11045, Const(2))).name("x11046").ctrl(x11055) // FixLsh(x11045,Const(2))
    val x11047 = x11046 // FixConvert(x11046,TRUE,_64,_0)
    val x11048 = DramAddress(x10513).name("x11048").ctrl(x11055) // GetDRAMAddress(x10513)
    val x11050_x11049 = OpDef(op=FixAdd, inputs=List(x11047, x11048)).name("x11049").ctrl(x11055) // FixAdd(x11047,x11048)
    // x11050 = SimpleStruct(ArrayBuffer((offset,x11049), (size,Const(64)), (isLoad,Const(true))))
    val x11051 = OpDef(op=BitAnd, inputs=List(b7096, b7087)).name("x11051").ctrl(x11055) // And(b7096,b7087)
    val x11052 = OpDef(op=BitAnd, inputs=List(b7088, b6663)).name("x11052").ctrl(x11055) // And(b7088,b6663)
    val x11053 = OpDef(op=BitAnd, inputs=List(x11051, x11052)).name("x11053").ctrl(x11055) // And(x11051,x11052)
    val b11268_b11266 = WriteMem(b11266, x11050_x11049).name("b11268_b11266").ctrl(x11055) // StreamWrite(x11039,x11050,x11053)
    val b11269_b11267 = WriteMem(b11267, Const(64)).name("b11269_b11267").ctrl(x11055) // StreamWrite(x11039,x11050,x11053)
    val x11056 = FringeDenseLoad(dram=List(x10513), cmdStream=List(b11266, b11267), dataStream=List(x11040)).name("x11056").ctrl(x11067) // FringeDenseLoad(x10513,x11039,x11040)
    val x11057 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x11057").ctrl(x11067) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x11058 = CounterChain(List(x11057)).name("x11058").ctrl(x11067) // CounterChainNew(List(x11057))
    val x11066 = LoopController(style=InnerPipe, level=InnerControl, cchain=x11058).name("x11066").ctrl(x11067) // UnrolledForeach(List(b7096, b7087, b7088, b6663),x11058,Block(Const(())),List(List(b7117)),List(List(b7118)))
    val b7117 = CounterIter(x11057, None).ctrl(x11066).name("b7117")
    val b7118 = Const(true).ctrl(x11066).name("b7118")
    val x11059 = OpDef(op=BitAnd, inputs=List(b7118, b7096)).name("x11059").ctrl(x11066) // And(b7118,b7096)
    val x11060 = OpDef(op=BitAnd, inputs=List(b7087, b7088)).name("x11060").ctrl(x11066) // And(b7087,b7088)
    val x11061 = OpDef(op=BitAnd, inputs=List(x11059, x11060)).name("x11061").ctrl(x11066) // And(x11059,x11060)
    val x11062 = OpDef(op=BitAnd, inputs=List(x11061, b6663)).name("x11062").ctrl(x11066) // And(x11061,b6663)
    val x11063_x11063 = ReadMem(x11040).name("x11063").ctrl(x11066) // ParStreamRead(x11040,List(x11062))
    val x11064_x11064 = x11063_x11063 // x11064 = VectorApply(x11063,0)
    val x11065 = StoreBanks(List(x11033_d0_b0), List(b7095, b7117), x11064_x11064).name("x11065").ctrl(x11066) // ParSRAMStore(x11033,List(List(b7095, b7117)),List(x11064),List(x11062))
    val x11068 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11068").ctrl(x11142) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11069 = CounterChain(List(x11068)).name("x11069").ctrl(x11142) // CounterChainNew(List(x11068))
    val x11141 = LoopController(style=MetaPipe, level=OuterControl, cchain=x11069).name("x11141").ctrl(x11142) // UnrolledForeach(List(b7087, b7088, b6663),x11069,Block(Const(())),List(List(b7130)),List(List(b7131)))
    val b7130 = CounterIter(x11068, Some(0)).ctrl(x11141).name("b7130")
    val b7131 = Const(true).ctrl(x11141).name("b7131")
    val x11070 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x11070").ctrl(x11141) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x11071 = CounterChain(List(x11070)).name("x11071").ctrl(x11141) // CounterChainNew(List(x11070))
    val x11140 = LoopController(style=MetaPipe, level=OuterControl, cchain=x11071).name("x11140").ctrl(x11141) // UnrolledForeach(List(b7131, b7087, b7088, b6663),x11071,Block(Const(())),List(List(b7134)),List(List(b7135)))
    val b7134 = CounterIter(x11070, Some(0)).ctrl(x11140).name("b7134")
    val b7135 = Const(true).ctrl(x11140).name("b7135")
    val x11072_d0 = Reg(init=Some(0.0)).name("x11072_d0").ctrl(x11140) // x11072 = RegNew(Const(0))
    isAccum(x11072_d0) = false
    bufferDepthOf(x11072_d0) = 2
    val x11072_d1 = Reg(init=Some(0.0)).name("x11072_d1").ctrl(x11140) // x11072 = RegNew(Const(0))
    isAccum(x11072_d1) = true
    bufferDepthOf(x11072_d1) = 1
    val x11073 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x11073").ctrl(x11140) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x11074 = CounterChain(List(x11073)).name("x11074").ctrl(x11140) // CounterChainNew(List(x11073))
    val x11100 = LoopController(style=InnerPipe, level=InnerControl, cchain=x11074).name("x11100").ctrl(x11140) // UnrolledReduce(List(b7135, b7131, b7087, b7088, b6663),x11074,x11072,Block((x11072) => Const(())),List(List(b7139)),List(List(b7140)))
    val b7139 = CounterIter(x11073, None).ctrl(x11100).name("b7139")
    val b7140 = Const(true).ctrl(x11100).name("b7140")
    val x11075 = OpDef(op=FixAdd, inputs=List(b7085, b7139)).name("x11075").ctrl(x11100) // FixAdd(b7085,b7139)
    val x11076 = OpDef(op=BitAnd, inputs=List(b7140, b7135)).name("x11076").ctrl(x11100) // And(b7140,b7135)
    val x11077 = OpDef(op=BitAnd, inputs=List(b7131, b7087)).name("x11077").ctrl(x11100) // And(b7131,b7087)
    val x11078 = OpDef(op=BitAnd, inputs=List(b7088, b6663)).name("x11078").ctrl(x11100) // And(b7088,b6663)
    val x11079 = OpDef(op=BitAnd, inputs=List(x11076, x11077)).name("x11079").ctrl(x11100) // And(x11076,x11077)
    val x11080 = OpDef(op=BitAnd, inputs=List(x11079, x11078)).name("x11080").ctrl(x11100) // And(x11079,x11078)
    val x11081 = LoadBanks(List(x10558_d0_b0), List(b7130, x11075)).name("x11081").ctrl(x11100) // ParSRAMLoad(x10558,List(List(b7130, x11075)),List(x11080))
    val x11082 = x11081 // x11082 = VectorApply(x11081,0)
    val x11083 = LoadBanks(List(x11033_d0_b0), List(b7139, b7134)).name("x11083").ctrl(x11100) // ParSRAMLoad(x11033,List(List(b7139, b7134)),List(x11080))
    val x11084 = x11083 // x11084 = VectorApply(x11083,0)
    val x11085 = OpDef(op=FixMul, inputs=List(x11082, x11084)).name("x11085").ctrl(x11100) // FixMul(x11082,x11084)
    val x11086 = OpDef(op=FixSub, inputs=List(x11075, Const(512))).name("x11086").ctrl(x11100) // FixSub(x11075,Const(512))
    val x11087 = LoadBanks(List(x10559_d1_b0, x10559_d1_b1, x10559_d1_b2, x10559_d1_b3, x10559_d1_b4, x10559_d1_b5, x10559_d1_b6, x10559_d1_b7, x10559_d1_b8, x10559_d1_b9, x10559_d1_b10, x10559_d1_b11, x10559_d1_b12, x10559_d1_b13, x10559_d1_b14, x10559_d1_b15), List(b7130, x11086)).name("x11087").ctrl(x11100) // ParSRAMLoad(x10559,List(List(b7130, x11086)),List(x11080))
    val x11088 = x11087 // x11088 = VectorApply(x11087,0)
    val x11089 = OpDef(op=FixMul, inputs=List(x11088, x11084)).name("x11089").ctrl(x11100) // FixMul(x11088,x11084)
    val x11090 = OpDef(op=FixLt, inputs=List(x11075, Const(512))).name("x11090").ctrl(x11100) // FixLt(x11075,Const(512))
    val x11091 = OpDef(op=MuxOp, inputs=List(x11090, x11085, x11089)).name("x11091").ctrl(x11100) // Mux(x11090,x11085,x11089)
    val x11092 = ReadMem(x11072_d1).name("x11092").ctrl(x11100) // RegRead(x11072)
    val x11093 = OpDef(op=FixEql, inputs=List(b7139, Const(0))).name("x11093").ctrl(x11100) // FixEql(b7139,Const(0))
    val x11094 = ReduceAccumOp(op=FixAdd, input=x11091, accum=x11092).name("x11094").ctrl(x11100) // FixAdd(x11091,x11092)
    val x11095 = OpDef(op=BitAnd, inputs=List(b7135, b7131)).name("x11095").ctrl(x11100) // And(b7135,b7131)
    val x11096 = OpDef(op=BitAnd, inputs=List(b7087, b7088)).name("x11096").ctrl(x11100) // And(b7087,b7088)
    val x11097 = OpDef(op=BitAnd, inputs=List(x11095, x11096)).name("x11097").ctrl(x11100) // And(x11095,x11096)
    val x11098 = OpDef(op=BitAnd, inputs=List(x11097, b6663)).name("x11098").ctrl(x11100) // And(x11097,b6663)
    val x11099_x11072_d0 = WriteMem(x11072_d0, x11094).name("x11099_x11072_d0").ctrl(x11100) // RegWrite(x11072,x11094,x11098)
    val x11099_x11072_d1 = WriteMem(x11072_d1, x11094).name("x11099_x11072_d1").ctrl(x11100) // RegWrite(x11072,x11094,x11098)
    val x11139 = UnitController(style=SeqPipe, level=InnerControl).name("x11139").ctrl(x11140) // UnitPipe(List(b7135, b7131, b7087, b7088, b6663),Block(Const(())))
    val x11101 = OpDef(op=FixAdd, inputs=List(b7086, b7134)).name("x11101").ctrl(x11139) // FixAdd(b7086,b7134)
    val x11102 = ReadMem(x11072_d0).name("x11102").ctrl(x11139) // RegRead(x11072)
    val x11103 = OpDef(op=FixEql, inputs=List(b7085, Const(0))).name("x11103").ctrl(x11139) // FixEql(b7085,Const(0))
    val x11104 = OpDef(op=FixAdd, inputs=List(x11101, Const(1536))).name("x11104").ctrl(x11139) // FixAdd(x11101,Const(1536))
    val x11105 = OpDef(op=BitAnd, inputs=List(b7135, b7131)).name("x11105").ctrl(x11139) // And(b7135,b7131)
    val x11106 = OpDef(op=BitAnd, inputs=List(b7087, b7088)).name("x11106").ctrl(x11139) // And(b7087,b7088)
    val x11107 = OpDef(op=BitAnd, inputs=List(x11105, x11106)).name("x11107").ctrl(x11139) // And(x11105,x11106)
    val x11108 = OpDef(op=BitAnd, inputs=List(x11107, b6663)).name("x11108").ctrl(x11139) // And(x11107,b6663)
    val x11109 = LoadBanks(List(x10565_d0_b0), List(Const(0), x11104)).name("x11109").ctrl(x11139) // SRAMLoad(x10565,ArrayBuffer(Const(1), Const(2048)),List(Const(0), x11104),Const(0),x11108)
    val x11110 = LoadBanks(List(x10564_d1_b0), List(b7130, x11101)).name("x11110").ctrl(x11139) // SRAMLoad(x10564,ArrayBuffer(Const(1), Const(512)),List(b7130, x11101),Const(0),x11108)
    val x11111 = OpDef(op=MuxOp, inputs=List(x11103, x11109, x11110)).name("x11111").ctrl(x11139) // Mux(x11103,x11109,x11110)
    val x11112 = OpDef(op=FixAdd, inputs=List(x11102, x11111)).name("x11112").ctrl(x11139) // FixAdd(x11102,x11111)
    val x11113 = OpDef(op=FixLeq, inputs=List(Const(1520), b7085)).name("x11113").ctrl(x11139) // FixLeq(Const(1520),b7085)
    // x11114 = FixConvert(Const(0),TRUE,_8,_24) x.tp=FixPt[TRUE,_32,_0] {
    val x11114_int1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("01111111111111111111111111111111"))).ctrl(x11139).name("x11114_int1")
    val x11114_int2 = OpDef(op=FixSla, inputs=List(x11114_int1, Const(24))).ctrl(x11139).name("x11114_int2")
    val x11114_frac1 = OpDef(op=BitAnd, inputs=List(Const(0), Const("00000000000000000000000000000000"))).ctrl(x11139).name("x11114_frac1")
    val x11114_frac2 = OpDef(op=FixSla, inputs=List(x11114_frac1, Const(24))).ctrl(x11139).name("x11114_frac2")
    val x11114 = OpDef(op=BitOr, inputs=List(x11114_int2, x11114_frac2)).ctrl(x11139).name("x11114")
    // }
    val x11115 = OpDef(op=FixAdd, inputs=List(x11112, x11114)).name("x11115").ctrl(x11139) // FixAdd(x11112,x11114)
    val x11116 = OpDef(op=FixSra, inputs=List(x11115, Const(1))).name("x11116").ctrl(x11139) // FixRsh(x11115,Const(1))
    val x11117 = x11116 // FixConvert(x11116,TRUE,_8,_24) (Same Type. No op)
    val x11118 = OpDef(op=FixAbs, inputs=List(x11117)).name("x11118").ctrl(x11139) // FixAbs(x11117)
    val x11119 = OpDef(op=FixLt, inputs=List(Const(2.5), x11118)).name("x11119").ctrl(x11139) // FixLt(Const(2.5),x11118)
    val x11120 = OpDef(op=BitNot, inputs=List(x11119)).name("x11120").ctrl(x11139) // Not(x11119)
    val x11121 = OpDef(op=FixLt, inputs=List(Const(0.5), x11118)).name("x11121").ctrl(x11139) // FixLt(Const(0.5),x11118)
    val x11122 = OpDef(op=FixLeq, inputs=List(x11118, Const(2.5))).name("x11122").ctrl(x11139) // FixLeq(x11118,Const(2.5))
    val x11123 = OpDef(op=BitAnd, inputs=List(x11121, x11122)).name("x11123").ctrl(x11139) // And(x11121,x11122)
    val x11124 = OpDef(op=BitAnd, inputs=List(x11123, x11120)).name("x11124").ctrl(x11139) // And(x11123,x11120)
    val x11125 = OpDef(op=BitNot, inputs=List(x11123)).name("x11125").ctrl(x11139) // Not(x11123)
    val x11126 = OpDef(op=BitAnd, inputs=List(x11125, x11120)).name("x11126").ctrl(x11139) // And(x11125,x11120)
    val x11127 = OpDef(op=FixSra, inputs=List(x11118, Const(2))).name("x11127").ctrl(x11139) // FixRsh(x11118,Const(2))
    val x11128 = OpDef(op=FixAdd, inputs=List(x11127, Const(0.375))).name("x11128").ctrl(x11139) // FixAdd(x11127,Const(0.375))
    val x11129 = OpDef(op=MuxOp, inputs=List(x11124, x11128, x11118)).name("x11129").ctrl(x11139) // Mux(x11124,x11128,x11118)
    val x11130 = OpDef(op=MuxOp, inputs=List(x11119, Const(1.0), x11129)).name("x11130").ctrl(x11139) // Mux(x11119,Const(1),x11129)
    val x11131 = OpDef(op=FixNeg, inputs=List(x11130)).name("x11131").ctrl(x11139) // FixNeg(x11130)
    val x11132 = OpDef(op=FixLt, inputs=List(x11117, Const(0.0))).name("x11132").ctrl(x11139) // FixLt(x11117,Const(0))
    val x11133 = OpDef(op=MuxOp, inputs=List(x11132, x11131, x11130)).name("x11133").ctrl(x11139) // Mux(x11132,x11131,x11130)
    val x11134 = x11133 // FixConvert(x11133,TRUE,_8,_24) (Same Type. No op)
    val x11135 = OpDef(op=FixAdd, inputs=List(x11134, Const(1.0))).name("x11135").ctrl(x11139) // FixAdd(x11134,Const(1))
    val x11136 = OpDef(op=FixSra, inputs=List(x11135, Const(1))).name("x11136").ctrl(x11139) // FixRsh(x11135,Const(1))
    val x11137 = OpDef(op=MuxOp, inputs=List(x11113, x11136, x11112)).name("x11137").ctrl(x11139) // Mux(x11113,x11136,x11112)
    val x11138 = StoreBanks(List(x10564_d0_b0, x10564_d1_b0), List(b7130, x11101), x11137).name("x11138").ctrl(x11139) // SRAMStore(x10564,ArrayBuffer(Const(1), Const(512)),List(b7130, x11101),Const(0),x11137,x11108)
    val x11143 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x11143").ctrl(x11179) // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x11144 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11144").ctrl(x11179) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11145 = CounterChain(List(x11144,x11143)).name("x11145").ctrl(x11179) // CounterChainNew(List(x11144, x11143))
    val x11178 = LoopController(style=MetaPipe, level=OuterControl, cchain=x11145).name("x11178").ctrl(x11179) // UnrolledForeach(List(b6663),x11145,Block(Const(())),List(List(b7213), List(b7214)),List(List(b7215), List(b7216)))
    val b7213 = CounterIter(x11144, Some(0)).ctrl(x11178).name("b7213")
    val b7215 = Const(true).ctrl(x11178).name("b7215")
    val b7214 = CounterIter(x11143, Some(0)).ctrl(x11178).name("b7214")
    val b7216 = Const(true).ctrl(x11178).name("b7216")
    val x11177 = UnitController(style=SeqPipe, level=InnerControl).name("x11177").ctrl(x11178) // UnitPipe(List(b7215, b7216, b6663),Block(Const(())))
    val x11146 = OpDef(op=BitAnd, inputs=List(b7215, b7216)).name("x11146").ctrl(x11177) // And(b7215,b7216)
    val x11147 = OpDef(op=BitAnd, inputs=List(x11146, b6663)).name("x11147").ctrl(x11177) // And(x11146,b6663)
    val x11148 = LoadBanks(List(x10560_d1_b0, x10560_d1_b1, x10560_d1_b2, x10560_d1_b3, x10560_d1_b4, x10560_d1_b5, x10560_d1_b6, x10560_d1_b7, x10560_d1_b8, x10560_d1_b9, x10560_d1_b10, x10560_d1_b11, x10560_d1_b12, x10560_d1_b13, x10560_d1_b14, x10560_d1_b15), List(b7213, b7214)).name("x11148").ctrl(x11177) // SRAMLoad(x10560,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11147)
    val x11149 = LoadBanks(List(x10563_d0_b0), List(b7213, b7214)).name("x11149").ctrl(x11177) // SRAMLoad(x10563,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11147)
    val x11150 = OpDef(op=FixMul, inputs=List(x11148, x11149)).name("x11150").ctrl(x11177) // FixMul(x11148,x11149)
    val x11151 = LoadBanks(List(x10561_d0_b0), List(b7213, b7214)).name("x11151").ctrl(x11177) // SRAMLoad(x10561,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11147)
    val x11152 = LoadBanks(List(x10562_d0_b0), List(b7213, b7214)).name("x11152").ctrl(x11177) // SRAMLoad(x10562,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11147)
    val x11153 = OpDef(op=FixMul, inputs=List(x11151, x11152)).name("x11153").ctrl(x11177) // FixMul(x11151,x11152)
    val x11154 = OpDef(op=FixAdd, inputs=List(x11150, x11153)).name("x11154").ctrl(x11177) // FixAdd(x11150,x11153)
    val x11155 = x11154 // FixConvert(x11154,TRUE,_8,_24) (Same Type. No op)
    val x11156 = OpDef(op=FixAbs, inputs=List(x11155)).name("x11156").ctrl(x11177) // FixAbs(x11155)
    val x11157 = OpDef(op=FixLt, inputs=List(Const(2.5), x11156)).name("x11157").ctrl(x11177) // FixLt(Const(2.5),x11156)
    val x11158 = OpDef(op=BitNot, inputs=List(x11157)).name("x11158").ctrl(x11177) // Not(x11157)
    val x11159 = OpDef(op=FixLt, inputs=List(Const(0.5), x11156)).name("x11159").ctrl(x11177) // FixLt(Const(0.5),x11156)
    val x11160 = OpDef(op=FixLeq, inputs=List(x11156, Const(2.5))).name("x11160").ctrl(x11177) // FixLeq(x11156,Const(2.5))
    val x11161 = OpDef(op=BitAnd, inputs=List(x11159, x11160)).name("x11161").ctrl(x11177) // And(x11159,x11160)
    val x11162 = OpDef(op=BitAnd, inputs=List(x11161, x11158)).name("x11162").ctrl(x11177) // And(x11161,x11158)
    val x11163 = OpDef(op=BitNot, inputs=List(x11161)).name("x11163").ctrl(x11177) // Not(x11161)
    val x11164 = OpDef(op=BitAnd, inputs=List(x11163, x11158)).name("x11164").ctrl(x11177) // And(x11163,x11158)
    val x11165 = OpDef(op=FixSra, inputs=List(x11156, Const(2))).name("x11165").ctrl(x11177) // FixRsh(x11156,Const(2))
    val x11166 = OpDef(op=FixAdd, inputs=List(x11165, Const(0.375))).name("x11166").ctrl(x11177) // FixAdd(x11165,Const(0.375))
    val x11167 = OpDef(op=MuxOp, inputs=List(x11162, x11166, x11156)).name("x11167").ctrl(x11177) // Mux(x11162,x11166,x11156)
    val x11168 = OpDef(op=MuxOp, inputs=List(x11157, Const(1.0), x11167)).name("x11168").ctrl(x11177) // Mux(x11157,Const(1),x11167)
    val x11169 = OpDef(op=FixNeg, inputs=List(x11168)).name("x11169").ctrl(x11177) // FixNeg(x11168)
    val x11170 = OpDef(op=FixLt, inputs=List(x11155, Const(0.0))).name("x11170").ctrl(x11177) // FixLt(x11155,Const(0))
    val x11171 = OpDef(op=MuxOp, inputs=List(x11170, x11169, x11168)).name("x11171").ctrl(x11177) // Mux(x11170,x11169,x11168)
    val x11172 = x11171 // FixConvert(x11171,TRUE,_8,_24) (Same Type. No op)
    val x11173 = LoadBanks(List(x10564_d0_b0), List(b7213, b7214)).name("x11173").ctrl(x11177) // SRAMLoad(x10564,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11147)
    val x11174 = OpDef(op=FixMul, inputs=List(x11172, x11173)).name("x11174").ctrl(x11177) // FixMul(x11172,x11173)
    val x11175 = StoreBanks(List(x10559_d0_b0, x10559_d0_b1, x10559_d0_b2, x10559_d0_b3, x10559_d0_b4, x10559_d0_b5, x10559_d0_b6, x10559_d0_b7, x10559_d0_b8, x10559_d0_b9, x10559_d0_b10, x10559_d0_b11, x10559_d0_b12, x10559_d0_b13, x10559_d0_b14, x10559_d0_b15, x10559_d1_b0, x10559_d1_b1, x10559_d1_b2, x10559_d1_b3, x10559_d1_b4, x10559_d1_b5, x10559_d1_b6, x10559_d1_b7, x10559_d1_b8, x10559_d1_b9, x10559_d1_b10, x10559_d1_b11, x10559_d1_b12, x10559_d1_b13, x10559_d1_b14, x10559_d1_b15, x10559_d2_b0, x10559_d2_b1, x10559_d2_b2, x10559_d2_b3, x10559_d2_b4, x10559_d2_b5, x10559_d2_b6, x10559_d2_b7, x10559_d2_b8, x10559_d2_b9, x10559_d2_b10, x10559_d2_b11, x10559_d2_b12, x10559_d2_b13, x10559_d2_b14, x10559_d2_b15, x10559_d3_b0, x10559_d3_b1, x10559_d3_b2, x10559_d3_b3, x10559_d3_b4, x10559_d3_b5, x10559_d3_b6, x10559_d3_b7, x10559_d3_b8, x10559_d3_b9, x10559_d3_b10, x10559_d3_b11, x10559_d3_b12, x10559_d3_b13, x10559_d3_b14, x10559_d3_b15, x10559_d4_b0, x10559_d4_b1, x10559_d4_b2, x10559_d4_b3, x10559_d4_b4, x10559_d4_b5, x10559_d4_b6, x10559_d4_b7, x10559_d4_b8, x10559_d4_b9, x10559_d4_b10, x10559_d4_b11, x10559_d4_b12, x10559_d4_b13, x10559_d4_b14, x10559_d4_b15), List(b7213, b7214), x11174).name("x11175").ctrl(x11177) // SRAMStore(x10559,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11174,x11147)
    val x11176 = StoreBanks(List(x10560_d0_b0, x10560_d0_b1, x10560_d0_b2, x10560_d0_b3, x10560_d0_b4, x10560_d0_b5, x10560_d0_b6, x10560_d0_b7, x10560_d0_b8, x10560_d0_b9, x10560_d0_b10, x10560_d0_b11, x10560_d0_b12, x10560_d0_b13, x10560_d0_b14, x10560_d0_b15, x10560_d1_b0, x10560_d1_b1, x10560_d1_b2, x10560_d1_b3, x10560_d1_b4, x10560_d1_b5, x10560_d1_b6, x10560_d1_b7, x10560_d1_b8, x10560_d1_b9, x10560_d1_b10, x10560_d1_b11, x10560_d1_b12, x10560_d1_b13, x10560_d1_b14, x10560_d1_b15), List(b7213, b7214), x11154).name("x11176").ctrl(x11177) // SRAMStore(x10560,ArrayBuffer(Const(1), Const(512)),List(b7213, b7214),Const(0),x11154,x11147)
    val x11180 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11180").ctrl(x11236) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11181 = CounterChain(List(x11180)).name("x11181").ctrl(x11236) // CounterChainNew(List(x11180))
    val x11207 = LoopController(style=StreamPipe, level=OuterControl, cchain=x11181).name("x11207").ctrl(x11236) // UnrolledForeach(List(Const(true)),x11181,Block(Const(())),List(List(b7253)),List(List(b7254)))
    val b7253 = CounterIter(x11180, Some(0)).ctrl(x11207).name("b7253")
    val b7254 = Const(true).ctrl(x11207).name("b7254")
    val b11270 = StreamOut(field="offset").name("b11270").ctrl(x11207) // x11182 = StreamOutNew(BurstCmdBus)
    val b11271 = StreamOut(field="size").name("b11271").ctrl(x11207) // x11182 = StreamOutNew(BurstCmdBus)
    val x11183 = StreamOut(field="data").name("x11183").ctrl(x11207) // x11183 = StreamOutNew(BurstFullDataBus())
    val x11184 = StreamIn(field="ack").name("x11184").ctrl(x11207) // x11184 = StreamInNew(BurstAckBus)
    val x11195 = UnitController(style=SeqPipe, level=InnerControl).name("x11195").ctrl(x11207) // UnitPipe(List(b7254),Block(x11194))
    val x11185 = b7253 // FixConvert(b7253,TRUE,_32,_0) (Same Type. No op)
    val x11186 = OpDef(op=FixSla, inputs=List(x11185, Const(9))).name("x11186").ctrl(x11195) // FixLsh(x11185,Const(9))
    val x11187 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11188 = OpDef(op=FixAdd, inputs=List(x11186, x11187)).name("x11188").ctrl(x11195) // FixAdd(x11186,x11187)
    val x11189 = OpDef(op=FixSla, inputs=List(x11188, Const(2))).name("x11189").ctrl(x11195) // FixLsh(x11188,Const(2))
    val x11190 = x11189 // FixConvert(x11189,TRUE,_64,_0)
    val x11191 = DramAddress(x10482).name("x11191").ctrl(x11195) // GetDRAMAddress(x10482)
    val x11193_x11192 = OpDef(op=FixAdd, inputs=List(x11190, x11191)).name("x11192").ctrl(x11195) // FixAdd(x11190,x11191)
    // x11193 = SimpleStruct(ArrayBuffer((offset,x11192), (size,Const(2048)), (isLoad,Const(false))))
    val b11272_b11270 = WriteMem(b11270, x11193_x11192).name("b11272_b11270").ctrl(x11195) // StreamWrite(x11182,x11193,b7254)
    val b11273_b11271 = WriteMem(b11271, Const(2048)).name("b11273_b11271").ctrl(x11195) // StreamWrite(x11182,x11193,b7254)
    val x11196 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x11196").ctrl(x11207) // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x11197 = CounterChain(List(x11196)).name("x11197").ctrl(x11207) // CounterChainNew(List(x11196))
    val x11203 = LoopController(style=InnerPipe, level=InnerControl, cchain=x11197).name("x11203").ctrl(x11207) // UnrolledForeach(List(b7254),x11197,Block(Const(())),List(List(b7271)),List(List(b7272)))
    val b7271 = CounterIter(x11196, None).ctrl(x11203).name("b7271")
    val b7272 = Const(true).ctrl(x11203).name("b7272")
    val x11198 = OpDef(op=BitAnd, inputs=List(b7272, b7254)).name("x11198").ctrl(x11203) // And(b7272,b7254)
    val x11199 = LoadBanks(List(x10560_d0_b0, x10560_d0_b1, x10560_d0_b2, x10560_d0_b3, x10560_d0_b4, x10560_d0_b5, x10560_d0_b6, x10560_d0_b7, x10560_d0_b8, x10560_d0_b9, x10560_d0_b10, x10560_d0_b11, x10560_d0_b12, x10560_d0_b13, x10560_d0_b14, x10560_d0_b15), List(b7253, b7271)).name("x11199").ctrl(x11203) // ParSRAMLoad(x10560,List(List(b7253, b7271)),List(x11198))
    val x11201_x11200 = x11199 // x11200 = VectorApply(x11199,0)
    // x11201 = SimpleStruct(ArrayBuffer((_1,x11200), (_2,Const(true))))
    val x11202_x11183 = WriteMem(x11183, x11201_x11200).name("x11202_x11183").ctrl(x11203) // ParStreamWrite(x11183,List(x11201),List(x11198))
    val x11204 = FringeDenseStore(dram=List(x10482), cmdStream=List(b11270, b11271), dataStream=List(x11183), ackStream=List(x11184)).name("x11204").ctrl(x11207) // FringeDenseStore(x10482,x11182,x11183,x11184)
    val x11206 = UnitController(style=SeqPipe, level=InnerControl).name("x11206").ctrl(x11207) // UnitPipe(List(b7254),Block(Const(())))
    val x11205_x11205 = ReadMem(x11184).name("x11205").ctrl(x11206) // StreamRead(x11184,b7254)
    val x11208 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11208").ctrl(x11236) // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11209 = CounterChain(List(x11208)).name("x11209").ctrl(x11236) // CounterChainNew(List(x11208))
    val x11235 = LoopController(style=StreamPipe, level=OuterControl, cchain=x11209).name("x11235").ctrl(x11236) // UnrolledForeach(List(Const(true)),x11209,Block(Const(())),List(List(b7285)),List(List(b7286)))
    val b7285 = CounterIter(x11208, Some(0)).ctrl(x11235).name("b7285")
    val b7286 = Const(true).ctrl(x11235).name("b7286")
    val b11274 = StreamOut(field="offset").name("b11274").ctrl(x11235) // x11210 = StreamOutNew(BurstCmdBus)
    val b11275 = StreamOut(field="size").name("b11275").ctrl(x11235) // x11210 = StreamOutNew(BurstCmdBus)
    val x11211 = StreamOut(field="data").name("x11211").ctrl(x11235) // x11211 = StreamOutNew(BurstFullDataBus())
    val x11212 = StreamIn(field="ack").name("x11212").ctrl(x11235) // x11212 = StreamInNew(BurstAckBus)
    val x11223 = UnitController(style=SeqPipe, level=InnerControl).name("x11223").ctrl(x11235) // UnitPipe(List(b7286),Block(x11222))
    val x11213 = b7285 // FixConvert(b7285,TRUE,_32,_0) (Same Type. No op)
    val x11214 = OpDef(op=FixSla, inputs=List(x11213, Const(9))).name("x11214").ctrl(x11223) // FixLsh(x11213,Const(9))
    val x11215 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11216 = OpDef(op=FixAdd, inputs=List(x11214, x11215)).name("x11216").ctrl(x11223) // FixAdd(x11214,x11215)
    val x11217 = OpDef(op=FixSla, inputs=List(x11216, Const(2))).name("x11217").ctrl(x11223) // FixLsh(x11216,Const(2))
    val x11218 = x11217 // FixConvert(x11217,TRUE,_64,_0)
    val x11219 = DramAddress(x10484).name("x11219").ctrl(x11223) // GetDRAMAddress(x10484)
    val x11221_x11220 = OpDef(op=FixAdd, inputs=List(x11218, x11219)).name("x11220").ctrl(x11223) // FixAdd(x11218,x11219)
    // x11221 = SimpleStruct(ArrayBuffer((offset,x11220), (size,Const(2048)), (isLoad,Const(false))))
    val b11276_b11274 = WriteMem(b11274, x11221_x11220).name("b11276_b11274").ctrl(x11223) // StreamWrite(x11210,x11221,b7286)
    val b11277_b11275 = WriteMem(b11275, Const(2048)).name("b11277_b11275").ctrl(x11223) // StreamWrite(x11210,x11221,b7286)
    val x11224 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x11224").ctrl(x11235) // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x11225 = CounterChain(List(x11224)).name("x11225").ctrl(x11235) // CounterChainNew(List(x11224))
    val x11231 = LoopController(style=InnerPipe, level=InnerControl, cchain=x11225).name("x11231").ctrl(x11235) // UnrolledForeach(List(b7286),x11225,Block(Const(())),List(List(b7303)),List(List(b7304)))
    val b7303 = CounterIter(x11224, None).ctrl(x11231).name("b7303")
    val b7304 = Const(true).ctrl(x11231).name("b7304")
    val x11226 = OpDef(op=BitAnd, inputs=List(b7304, b7286)).name("x11226").ctrl(x11231) // And(b7304,b7286)
    val x11227 = LoadBanks(List(x10559_d0_b0, x10559_d0_b1, x10559_d0_b2, x10559_d0_b3, x10559_d0_b4, x10559_d0_b5, x10559_d0_b6, x10559_d0_b7, x10559_d0_b8, x10559_d0_b9, x10559_d0_b10, x10559_d0_b11, x10559_d0_b12, x10559_d0_b13, x10559_d0_b14, x10559_d0_b15), List(b7285, b7303)).name("x11227").ctrl(x11231) // ParSRAMLoad(x10559,List(List(b7285, b7303)),List(x11226))
    val x11229_x11228 = x11227 // x11228 = VectorApply(x11227,0)
    // x11229 = SimpleStruct(ArrayBuffer((_1,x11228), (_2,Const(true))))
    val x11230_x11211 = WriteMem(x11211, x11229_x11228).name("x11230_x11211").ctrl(x11231) // ParStreamWrite(x11211,List(x11229),List(x11226))
    val x11232 = FringeDenseStore(dram=List(x10484), cmdStream=List(b11274, b11275), dataStream=List(x11211), ackStream=List(x11212)).name("x11232").ctrl(x11235) // FringeDenseStore(x10484,x11210,x11211,x11212)
    val x11234 = UnitController(style=SeqPipe, level=InnerControl).name("x11234").ctrl(x11235) // UnitPipe(List(b7286),Block(Const(())))
    val x11233_x11233 = ReadMem(x11212).name("x11233").ctrl(x11234) // StreamRead(x11212,b7286)
    
  }
}
