import pir._
import pir.node._
import arch._
import prism.enums._

object DBLSTM_256_2_1_16 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5443 = withCtrl(design.top.topController) { DRAM(dims=List(Const(256))).name("x5443").srcCtx("DeepBenchLSTM256.scala:57:23:dout") } // x5443 = DRAMNew(ArrayBuffer(Const(256)),Const(0))
    val x6104 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6104").srcCtx("DeepBenchLSTM256.scala:59:11") } // Hwblock(Block(Const(())),false)
    val x5444_d0_b0 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b0").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b0) = false
    bufferDepthOf(x5444_d0_b0) = 1
    staticDimsOf(x5444_d0_b0) = List(256, 4, 256)
    val x5444_d0_b1 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b1").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b1) = false
    bufferDepthOf(x5444_d0_b1) = 1
    staticDimsOf(x5444_d0_b1) = List(256, 4, 256)
    val x5444_d0_b2 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b2").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b2) = false
    bufferDepthOf(x5444_d0_b2) = 1
    staticDimsOf(x5444_d0_b2) = List(256, 4, 256)
    val x5444_d0_b3 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b3").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b3) = false
    bufferDepthOf(x5444_d0_b3) = 1
    staticDimsOf(x5444_d0_b3) = List(256, 4, 256)
    val x5444_d0_b4 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b4").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b4) = false
    bufferDepthOf(x5444_d0_b4) = 1
    staticDimsOf(x5444_d0_b4) = List(256, 4, 256)
    val x5444_d0_b5 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b5").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b5) = false
    bufferDepthOf(x5444_d0_b5) = 1
    staticDimsOf(x5444_d0_b5) = List(256, 4, 256)
    val x5444_d0_b6 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b6").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b6) = false
    bufferDepthOf(x5444_d0_b6) = 1
    staticDimsOf(x5444_d0_b6) = List(256, 4, 256)
    val x5444_d0_b7 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b7").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b7) = false
    bufferDepthOf(x5444_d0_b7) = 1
    staticDimsOf(x5444_d0_b7) = List(256, 4, 256)
    val x5444_d0_b8 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b8").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b8) = false
    bufferDepthOf(x5444_d0_b8) = 1
    staticDimsOf(x5444_d0_b8) = List(256, 4, 256)
    val x5444_d0_b9 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b9").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b9) = false
    bufferDepthOf(x5444_d0_b9) = 1
    staticDimsOf(x5444_d0_b9) = List(256, 4, 256)
    val x5444_d0_b10 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b10").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b10) = false
    bufferDepthOf(x5444_d0_b10) = 1
    staticDimsOf(x5444_d0_b10) = List(256, 4, 256)
    val x5444_d0_b11 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b11").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b11) = false
    bufferDepthOf(x5444_d0_b11) = 1
    staticDimsOf(x5444_d0_b11) = List(256, 4, 256)
    val x5444_d0_b12 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b12").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b12) = false
    bufferDepthOf(x5444_d0_b12) = 1
    staticDimsOf(x5444_d0_b12) = List(256, 4, 256)
    val x5444_d0_b13 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b13").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b13) = false
    bufferDepthOf(x5444_d0_b13) = 1
    staticDimsOf(x5444_d0_b13) = List(256, 4, 256)
    val x5444_d0_b14 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b14").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b14) = false
    bufferDepthOf(x5444_d0_b14) = 1
    staticDimsOf(x5444_d0_b14) = List(256, 4, 256)
    val x5444_d0_b15 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5444_d0_b15").srcCtx("DataGenerator.scala:248:40:wh") } // x5444 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x5444_d0_b15) = false
    bufferDepthOf(x5444_d0_b15) = 1
    staticDimsOf(x5444_d0_b15) = List(256, 4, 256)
    val x5445_d0_b0 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5445_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt0") } // x5445 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x5445_d0_b0) = false
    bufferDepthOf(x5445_d0_b0) = 1
    staticDimsOf(x5445_d0_b0) = List(64, 4, 256)
    val x5445_d0_b1 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5445_d0_b1").srcCtx("DataGenerator.scala:248:40:wxt0") } // x5445 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x5445_d0_b1) = false
    bufferDepthOf(x5445_d0_b1) = 1
    staticDimsOf(x5445_d0_b1) = List(64, 4, 256)
    val x5446_d0_b0 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5446_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt1") } // x5446 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x5446_d0_b0) = false
    bufferDepthOf(x5446_d0_b0) = 1
    staticDimsOf(x5446_d0_b0) = List(64, 4, 256)
    val x5446_d0_b1 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5446_d0_b1").srcCtx("DataGenerator.scala:248:40:wxt1") } // x5446 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x5446_d0_b1) = false
    bufferDepthOf(x5446_d0_b1) = 1
    staticDimsOf(x5446_d0_b1) = List(64, 4, 256)
    val x5447_d0_b0 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5447_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt2") } // x5447 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x5447_d0_b0) = false
    bufferDepthOf(x5447_d0_b0) = 1
    staticDimsOf(x5447_d0_b0) = List(64, 4, 256)
    val x5447_d0_b1 = withCtrl(x6104) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x5447_d0_b1").srcCtx("DataGenerator.scala:248:40:wxt2") } // x5447 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x5447_d0_b1) = false
    bufferDepthOf(x5447_d0_b1) = 1
    staticDimsOf(x5447_d0_b1) = List(64, 4, 256)
    val x5448_d0_b0 = withCtrl(x6104) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5448_d0_b0").srcCtx("DeepBenchLSTM256.scala:86:25:cBuf") } // x5448 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x5448_d0_b0) = false
    bufferDepthOf(x5448_d0_b0) = 1
    staticDimsOf(x5448_d0_b0) = List(256)
    val x5449_d0_b0 = withCtrl(x6104) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5449_d0_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x5449 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x5449_d0_b0) = false
    bufferDepthOf(x5449_d0_b0) = 1
    staticDimsOf(x5449_d0_b0) = List(256)
    val x5449_d1_b0 = withCtrl(x6104) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5449_d1_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x5449 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x5449_d1_b0) = false
    bufferDepthOf(x5449_d1_b0) = 1
    staticDimsOf(x5449_d1_b0) = List(256)
    val x5450 = withCtrl(x6104) { Counter(min=Const(0), max=Const(150), step=Const(1), par=1).name("x5450").srcCtx("DeepBenchLSTM256.scala:90:34") } // CounterNew(Const(0),Const(150),Const(1),Const(1))
    val x5451 = withCtrl(x6104) { CounterChain(List(x5450)).name("x5451").srcCtx("DeepBenchLSTM256.scala:90:40") } // CounterChainNew(List(x5450))
    val x6081 = withCtrl(x6104) { LoopController(style=SeqPipe, level=OuterControl, cchain=x5451).name("x6081").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnrolledForeach(List(Const(true)),x5451,Block(Const(())),List(List(b1968)),List(List(b1969)))
    val b1968 = withCtrl(x6081) { CounterIter(x5450, Some(0)).name("b1968") } // b1968
    val b1969 = withCtrl(x6081) { Const(true).name("b1969") } // b1969
    val x5452_d0_b0 = withCtrl(x6081) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5452_d0_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x5452 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5452_d0_b0) = false
    bufferDepthOf(x5452_d0_b0) = 1
    staticDimsOf(x5452_d0_b0) = List(4, 256)
    val x5452_d0_b1 = withCtrl(x6081) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5452_d0_b1").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x5452 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5452_d0_b1) = false
    bufferDepthOf(x5452_d0_b1) = 1
    staticDimsOf(x5452_d0_b1) = List(4, 256)
    val x5452_d1_b0 = withCtrl(x6081) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5452_d1_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x5452 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5452_d1_b0) = true
    bufferDepthOf(x5452_d1_b0) = 1
    staticDimsOf(x5452_d1_b0) = List(4, 256)
    val x5453_d0_b0 = withCtrl(x6081) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5453_d0_b0").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x5453 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5453_d0_b0) = false
    bufferDepthOf(x5453_d0_b0) = 1
    staticDimsOf(x5453_d0_b0) = List(4, 256)
    val x5453_d0_b1 = withCtrl(x6081) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5453_d0_b1").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x5453 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5453_d0_b1) = false
    bufferDepthOf(x5453_d0_b1) = 1
    staticDimsOf(x5453_d0_b1) = List(4, 256)
    val x5454_d0 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d0) = false
    bufferDepthOf(x5454_d0) = 1
    val x5454_d1 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d1) = false
    bufferDepthOf(x5454_d1) = 1
    val x5454_d2 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d2) = false
    bufferDepthOf(x5454_d2) = 1
    val x5454_d3 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d3) = false
    bufferDepthOf(x5454_d3) = 1
    val x5454_d4 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d4) = false
    bufferDepthOf(x5454_d4) = 1
    val x5454_d5 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d5").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d5) = false
    bufferDepthOf(x5454_d5) = 1
    val x5454_d6 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d6").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d6) = false
    bufferDepthOf(x5454_d6) = 1
    val x5454_d7 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d7").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d7) = false
    bufferDepthOf(x5454_d7) = 1
    val x5454_d8 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d8").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d8) = false
    bufferDepthOf(x5454_d8) = 1
    val x5454_d9 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d9").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d9) = false
    bufferDepthOf(x5454_d9) = 1
    val x5454_d10 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d10").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d10) = false
    bufferDepthOf(x5454_d10) = 1
    val x5454_d11 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d11").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d11) = false
    bufferDepthOf(x5454_d11) = 1
    val x5454_d12 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d12").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d12) = false
    bufferDepthOf(x5454_d12) = 1
    val x5454_d13 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d13").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d13) = false
    bufferDepthOf(x5454_d13) = 1
    val x5454_d14 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d14").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d14) = false
    bufferDepthOf(x5454_d14) = 1
    val x5454_d15 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d15").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d15) = false
    bufferDepthOf(x5454_d15) = 1
    val x5454_d16 = withCtrl(x6081) { Reg(init=Some(false)).name("x5454_d16").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5454 = RegNew(Const(false))
    isAccum(x5454_d16) = false
    bufferDepthOf(x5454_d16) = 1
    val x5455_d0 = withCtrl(x6081) { Reg(init=Some(false)).name("x5455_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5455 = RegNew(Const(false))
    isAccum(x5455_d0) = false
    bufferDepthOf(x5455_d0) = 1
    val x5455_d1 = withCtrl(x6081) { Reg(init=Some(false)).name("x5455_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5455 = RegNew(Const(false))
    isAccum(x5455_d1) = false
    bufferDepthOf(x5455_d1) = 1
    val x5456_d0 = withCtrl(x6081) { Reg(init=Some(false)).name("x5456_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5456 = RegNew(Const(false))
    isAccum(x5456_d0) = false
    bufferDepthOf(x5456_d0) = 1
    val x5456_d1 = withCtrl(x6081) { Reg(init=Some(false)).name("x5456_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5456 = RegNew(Const(false))
    isAccum(x5456_d1) = false
    bufferDepthOf(x5456_d1) = 1
    val x5457_d0 = withCtrl(x6081) { Reg(init=Some(0)).name("x5457_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5457 = RegNew(Const(0))
    isAccum(x5457_d0) = false
    bufferDepthOf(x5457_d0) = 1
    val x5457_d1 = withCtrl(x6081) { Reg(init=Some(0)).name("x5457_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x5457 = RegNew(Const(0))
    isAccum(x5457_d1) = false
    bufferDepthOf(x5457_d1) = 1
    val x5469 = withCtrl(x6081) { UnitController(style=SeqPipe, level=InnerControl).name("x5469").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnitPipe(List(b1969),Block(Const(())))
    val x5458 = withCtrl(x5469) { OpDef(op=FixEql, inputs=List(b1968, Const(0))).name("x5458").srcCtx("DeepBenchLSTM256.scala:93:33:isInitStep") } // FixEql(b1968,Const(0))
    val x5459 = withCtrl(x5469) { OpDef(op=FixLt, inputs=List(b1968, Const(64))).name("x5459").srcCtx("VecMatMultBiasAdd.scala:154:28:isFstRange") } // FixLt(b1968,Const(64))
    val x5460 = withCtrl(x5469) { OpDef(op=FixLt, inputs=List(Const(64), b1968)).name("x5460").srcCtx("VecMatMultBiasAdd.scala:155:28:isLstRange") } // FixLt(Const(64),b1968)
    val x5461 = withCtrl(x5469) { OpDef(op=FixSub, inputs=List(b1968, Const(64))).name("x5461").srcCtx("VecMatMultBiasAdd.scala:157:24:midIdx") } // FixSub(b1968,Const(64))
    val x5462 = withCtrl(x5469) { OpDef(op=FixSub, inputs=List(b1968, Const(128))).name("x5462").srcCtx("VecMatMultBiasAdd.scala:158:24:lstIdx") } // FixSub(b1968,Const(128))
    val x5463 = withCtrl(x5469) { OpDef(op=MuxOp, inputs=List(x5460, x5462, x5461)).name("x5463").srcCtx("VecMatMultBiasAdd.scala:160:30") } // Mux(x5460,x5462,x5461)
    val x5464 = withCtrl(x5469) { OpDef(op=MuxOp, inputs=List(x5459, b1968, x5463)).name("x5464").srcCtx("VecMatMultBiasAdd.scala:159:25:iTileStep") } // Mux(x5459,b1968,x5463)
    val x5465_x5454_d0 = withCtrl(x5469) { WriteMem(x5454_d0, x5458).name("x5465_x5454_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d5 = withCtrl(x5469) { WriteMem(x5454_d5, x5458).name("x5465_x5454_d5").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d10 = withCtrl(x5469) { WriteMem(x5454_d10, x5458).name("x5465_x5454_d10").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d14 = withCtrl(x5469) { WriteMem(x5454_d14, x5458).name("x5465_x5454_d14").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d1 = withCtrl(x5469) { WriteMem(x5454_d1, x5458).name("x5465_x5454_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d6 = withCtrl(x5469) { WriteMem(x5454_d6, x5458).name("x5465_x5454_d6").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d9 = withCtrl(x5469) { WriteMem(x5454_d9, x5458).name("x5465_x5454_d9").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d13 = withCtrl(x5469) { WriteMem(x5454_d13, x5458).name("x5465_x5454_d13").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d2 = withCtrl(x5469) { WriteMem(x5454_d2, x5458).name("x5465_x5454_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d12 = withCtrl(x5469) { WriteMem(x5454_d12, x5458).name("x5465_x5454_d12").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d7 = withCtrl(x5469) { WriteMem(x5454_d7, x5458).name("x5465_x5454_d7").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d3 = withCtrl(x5469) { WriteMem(x5454_d3, x5458).name("x5465_x5454_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d16 = withCtrl(x5469) { WriteMem(x5454_d16, x5458).name("x5465_x5454_d16").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d11 = withCtrl(x5469) { WriteMem(x5454_d11, x5458).name("x5465_x5454_d11").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d8 = withCtrl(x5469) { WriteMem(x5454_d8, x5458).name("x5465_x5454_d8").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d4 = withCtrl(x5469) { WriteMem(x5454_d4, x5458).name("x5465_x5454_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5465_x5454_d15 = withCtrl(x5469) { WriteMem(x5454_d15, x5458).name("x5465_x5454_d15").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5454,x5458,b1969)
    val x5466_x5455_d0 = withCtrl(x5469) { WriteMem(x5455_d0, x5459).name("x5466_x5455_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5455,x5459,b1969)
    val x5466_x5455_d1 = withCtrl(x5469) { WriteMem(x5455_d1, x5459).name("x5466_x5455_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5455,x5459,b1969)
    val x5467_x5456_d0 = withCtrl(x5469) { WriteMem(x5456_d0, x5460).name("x5467_x5456_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5456,x5460,b1969)
    val x5467_x5456_d1 = withCtrl(x5469) { WriteMem(x5456_d1, x5460).name("x5467_x5456_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5456,x5460,b1969)
    val x5468_x5457_d0 = withCtrl(x5469) { WriteMem(x5457_d0, x5464).name("x5468_x5457_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5457,x5464,b1969)
    val x5468_x5457_d1 = withCtrl(x5469) { WriteMem(x5457_d1, x5464).name("x5468_x5457_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x5457,x5464,b1969)
    val x5470 = withCtrl(x6081) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5470").srcCtx("VecMatMultBiasAdd.scala:165:59") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5471 = withCtrl(x6081) { CounterChain(List(x5470)).name("x5471").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(List(x5470))
    val x5931 = withCtrl(x6081) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5471).name("x5931").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledReduce(List(b1969),x5471,x5452,Block((x5452) => Const(())),List(List(b1993, b1994, b1995, b1996, b1997, b1998, b1999, b2000, b2001, b2002, b2003, b2004, b2005, b2006, b2007, b2008)),List(List(b2009, b2010, b2011, b2012, b2013, b2014, b2015, b2016, b2017, b2018, b2019, b2020, b2021, b2022, b2023, b2024)))
    val b1993 = withCtrl(x5931) { CounterIter(x5470, Some(0)).name("b1993") } // b1993
    val b2009 = withCtrl(x5931) { Const(true).name("b2009") } // b2009
    val b1994 = withCtrl(x5931) { CounterIter(x5470, Some(1)).name("b1994") } // b1994
    val b2010 = withCtrl(x5931) { Const(true).name("b2010") } // b2010
    val b1995 = withCtrl(x5931) { CounterIter(x5470, Some(2)).name("b1995") } // b1995
    val b2011 = withCtrl(x5931) { Const(true).name("b2011") } // b2011
    val b1996 = withCtrl(x5931) { CounterIter(x5470, Some(3)).name("b1996") } // b1996
    val b2012 = withCtrl(x5931) { Const(true).name("b2012") } // b2012
    val b1997 = withCtrl(x5931) { CounterIter(x5470, Some(4)).name("b1997") } // b1997
    val b2013 = withCtrl(x5931) { Const(true).name("b2013") } // b2013
    val b1998 = withCtrl(x5931) { CounterIter(x5470, Some(5)).name("b1998") } // b1998
    val b2014 = withCtrl(x5931) { Const(true).name("b2014") } // b2014
    val b1999 = withCtrl(x5931) { CounterIter(x5470, Some(6)).name("b1999") } // b1999
    val b2015 = withCtrl(x5931) { Const(true).name("b2015") } // b2015
    val b2000 = withCtrl(x5931) { CounterIter(x5470, Some(7)).name("b2000") } // b2000
    val b2016 = withCtrl(x5931) { Const(true).name("b2016") } // b2016
    val b2001 = withCtrl(x5931) { CounterIter(x5470, Some(8)).name("b2001") } // b2001
    val b2017 = withCtrl(x5931) { Const(true).name("b2017") } // b2017
    val b2002 = withCtrl(x5931) { CounterIter(x5470, Some(9)).name("b2002") } // b2002
    val b2018 = withCtrl(x5931) { Const(true).name("b2018") } // b2018
    val b2003 = withCtrl(x5931) { CounterIter(x5470, Some(10)).name("b2003") } // b2003
    val b2019 = withCtrl(x5931) { Const(true).name("b2019") } // b2019
    val b2004 = withCtrl(x5931) { CounterIter(x5470, Some(11)).name("b2004") } // b2004
    val b2020 = withCtrl(x5931) { Const(true).name("b2020") } // b2020
    val b2005 = withCtrl(x5931) { CounterIter(x5470, Some(12)).name("b2005") } // b2005
    val b2021 = withCtrl(x5931) { Const(true).name("b2021") } // b2021
    val b2006 = withCtrl(x5931) { CounterIter(x5470, Some(13)).name("b2006") } // b2006
    val b2022 = withCtrl(x5931) { Const(true).name("b2022") } // b2022
    val b2007 = withCtrl(x5931) { CounterIter(x5470, Some(14)).name("b2007") } // b2007
    val b2023 = withCtrl(x5931) { Const(true).name("b2023") } // b2023
    val b2008 = withCtrl(x5931) { CounterIter(x5470, Some(15)).name("b2008") } // b2008
    val b2024 = withCtrl(x5931) { Const(true).name("b2024") } // b2024
    val x5472_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5472_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5472 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5472_d0_b0) = false
    bufferDepthOf(x5472_d0_b0) = 2
    staticDimsOf(x5472_d0_b0) = List(4, 256)
    val x5473_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5473_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5473 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5473_d0_b0) = false
    bufferDepthOf(x5473_d0_b0) = 2
    staticDimsOf(x5473_d0_b0) = List(4, 256)
    val x5474_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5474_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5474 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5474_d0_b0) = false
    bufferDepthOf(x5474_d0_b0) = 2
    staticDimsOf(x5474_d0_b0) = List(4, 256)
    val x5475_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5475_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5475 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5475_d0_b0) = false
    bufferDepthOf(x5475_d0_b0) = 2
    staticDimsOf(x5475_d0_b0) = List(4, 256)
    val x5476_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5476_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5476 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5476_d0_b0) = false
    bufferDepthOf(x5476_d0_b0) = 2
    staticDimsOf(x5476_d0_b0) = List(4, 256)
    val x5477_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5477_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5477 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5477_d0_b0) = false
    bufferDepthOf(x5477_d0_b0) = 2
    staticDimsOf(x5477_d0_b0) = List(4, 256)
    val x5478_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5478_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5478 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5478_d0_b0) = false
    bufferDepthOf(x5478_d0_b0) = 2
    staticDimsOf(x5478_d0_b0) = List(4, 256)
    val x5479_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5479_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5479 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5479_d0_b0) = false
    bufferDepthOf(x5479_d0_b0) = 2
    staticDimsOf(x5479_d0_b0) = List(4, 256)
    val x5480_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5480_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5480 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5480_d0_b0) = false
    bufferDepthOf(x5480_d0_b0) = 2
    staticDimsOf(x5480_d0_b0) = List(4, 256)
    val x5481_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5481_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5481 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5481_d0_b0) = false
    bufferDepthOf(x5481_d0_b0) = 2
    staticDimsOf(x5481_d0_b0) = List(4, 256)
    val x5482_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5482_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5482 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5482_d0_b0) = false
    bufferDepthOf(x5482_d0_b0) = 2
    staticDimsOf(x5482_d0_b0) = List(4, 256)
    val x5483_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5483_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5483 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5483_d0_b0) = false
    bufferDepthOf(x5483_d0_b0) = 2
    staticDimsOf(x5483_d0_b0) = List(4, 256)
    val x5484_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5484_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5484 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5484_d0_b0) = false
    bufferDepthOf(x5484_d0_b0) = 2
    staticDimsOf(x5484_d0_b0) = List(4, 256)
    val x5485_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5485_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5485 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5485_d0_b0) = false
    bufferDepthOf(x5485_d0_b0) = 2
    staticDimsOf(x5485_d0_b0) = List(4, 256)
    val x5486_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5486_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5486 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5486_d0_b0) = false
    bufferDepthOf(x5486_d0_b0) = 2
    staticDimsOf(x5486_d0_b0) = List(4, 256)
    val x5487_d0_b0 = withCtrl(x5931) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x5487_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x5487 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x5487_d0_b0) = false
    bufferDepthOf(x5487_d0_b0) = 2
    staticDimsOf(x5487_d0_b0) = List(4, 256)
    val x5488 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5488").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5488 = RegNew(Const(0))
    isAccum(x5488) = false
    bufferDepthOf(x5488) = 2
    val x5489 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5489").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5489 = RegNew(Const(0))
    isAccum(x5489) = false
    bufferDepthOf(x5489) = 2
    val x5490 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5490").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5490 = RegNew(Const(0))
    isAccum(x5490) = false
    bufferDepthOf(x5490) = 2
    val x5491 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5491").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5491 = RegNew(Const(0))
    isAccum(x5491) = false
    bufferDepthOf(x5491) = 2
    val x5492 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5492").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5492 = RegNew(Const(0))
    isAccum(x5492) = false
    bufferDepthOf(x5492) = 2
    val x5493 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5493").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5493 = RegNew(Const(0))
    isAccum(x5493) = false
    bufferDepthOf(x5493) = 2
    val x5494 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5494").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5494 = RegNew(Const(0))
    isAccum(x5494) = false
    bufferDepthOf(x5494) = 2
    val x5495 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5495").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5495 = RegNew(Const(0))
    isAccum(x5495) = false
    bufferDepthOf(x5495) = 2
    val x5496 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5496").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5496 = RegNew(Const(0))
    isAccum(x5496) = false
    bufferDepthOf(x5496) = 2
    val x5497 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5497").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5497 = RegNew(Const(0))
    isAccum(x5497) = false
    bufferDepthOf(x5497) = 2
    val x5498 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5498").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5498 = RegNew(Const(0))
    isAccum(x5498) = false
    bufferDepthOf(x5498) = 2
    val x5499 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5499").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5499 = RegNew(Const(0))
    isAccum(x5499) = false
    bufferDepthOf(x5499) = 2
    val x5500 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5500").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5500 = RegNew(Const(0))
    isAccum(x5500) = false
    bufferDepthOf(x5500) = 2
    val x5501 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5501").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5501 = RegNew(Const(0))
    isAccum(x5501) = false
    bufferDepthOf(x5501) = 2
    val x5502 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5502").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5502 = RegNew(Const(0))
    isAccum(x5502) = false
    bufferDepthOf(x5502) = 2
    val x5503 = withCtrl(x5931) { Reg(init=Some(0.0)).name("x5503").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x5503 = RegNew(Const(0))
    isAccum(x5503) = false
    bufferDepthOf(x5503) = 2
    val x5600 = withCtrl(x5931) { UnitController(style=ForkJoin, level=OuterControl).name("x5600").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x5509 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5509").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2009, b1969),Block(Const(())))
    val x5504 = withCtrl(x5509) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x5504").srcCtx("UnrollingBase.scala:28:66") } // And(b2009,b1969)
    val x5505 = withCtrl(x5509) { LoadBanks(List(x5449_d1_b0), List(b1993)).name("x5505").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1993),Const(0),x5504)
    val x5506 = withCtrl(x5509) { ReadMem(x5454_d1).name("x5506").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5507 = withCtrl(x5509) { OpDef(op=MuxOp, inputs=List(x5506, Const(0.0), x5505)).name("x5507").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5506,Const(0),x5505)
    val x5508_x5488 = withCtrl(x5509) { WriteMem(x5488, x5507).name("x5508_x5488").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5488,x5507,x5504)
    val x5515 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5515").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2010, b1969),Block(Const(())))
    val x5510 = withCtrl(x5515) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x5510").srcCtx("UnrollingBase.scala:28:66") } // And(b2010,b1969)
    val x5511 = withCtrl(x5515) { LoadBanks(List(x5449_d1_b0), List(b1994)).name("x5511").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1994),Const(0),x5510)
    val x5512 = withCtrl(x5515) { ReadMem(x5454_d2).name("x5512").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5513 = withCtrl(x5515) { OpDef(op=MuxOp, inputs=List(x5512, Const(0.0), x5511)).name("x5513").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5512,Const(0),x5511)
    val x5514_x5489 = withCtrl(x5515) { WriteMem(x5489, x5513).name("x5514_x5489").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5489,x5513,x5510)
    val x5521 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5521").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2011, b1969),Block(Const(())))
    val x5516 = withCtrl(x5521) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x5516").srcCtx("UnrollingBase.scala:28:66") } // And(b2011,b1969)
    val x5517 = withCtrl(x5521) { LoadBanks(List(x5449_d1_b0), List(b1995)).name("x5517").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1995),Const(0),x5516)
    val x5518 = withCtrl(x5521) { ReadMem(x5454_d3).name("x5518").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5519 = withCtrl(x5521) { OpDef(op=MuxOp, inputs=List(x5518, Const(0.0), x5517)).name("x5519").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5518,Const(0),x5517)
    val x5520_x5490 = withCtrl(x5521) { WriteMem(x5490, x5519).name("x5520_x5490").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5490,x5519,x5516)
    val x5527 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5527").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2012, b1969),Block(Const(())))
    val x5522 = withCtrl(x5527) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x5522").srcCtx("UnrollingBase.scala:28:66") } // And(b2012,b1969)
    val x5523 = withCtrl(x5527) { LoadBanks(List(x5449_d1_b0), List(b1996)).name("x5523").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1996),Const(0),x5522)
    val x5524 = withCtrl(x5527) { ReadMem(x5454_d4).name("x5524").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5525 = withCtrl(x5527) { OpDef(op=MuxOp, inputs=List(x5524, Const(0.0), x5523)).name("x5525").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5524,Const(0),x5523)
    val x5526_x5491 = withCtrl(x5527) { WriteMem(x5491, x5525).name("x5526_x5491").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5491,x5525,x5522)
    val x5533 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5533").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2013, b1969),Block(Const(())))
    val x5528 = withCtrl(x5533) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x5528").srcCtx("UnrollingBase.scala:28:66") } // And(b2013,b1969)
    val x5529 = withCtrl(x5533) { LoadBanks(List(x5449_d1_b0), List(b1997)).name("x5529").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1997),Const(0),x5528)
    val x5530 = withCtrl(x5533) { ReadMem(x5454_d5).name("x5530").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5531 = withCtrl(x5533) { OpDef(op=MuxOp, inputs=List(x5530, Const(0.0), x5529)).name("x5531").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5530,Const(0),x5529)
    val x5532_x5492 = withCtrl(x5533) { WriteMem(x5492, x5531).name("x5532_x5492").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5492,x5531,x5528)
    val x5539 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5539").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2014, b1969),Block(Const(())))
    val x5534 = withCtrl(x5539) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x5534").srcCtx("UnrollingBase.scala:28:66") } // And(b2014,b1969)
    val x5535 = withCtrl(x5539) { LoadBanks(List(x5449_d1_b0), List(b1998)).name("x5535").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1998),Const(0),x5534)
    val x5536 = withCtrl(x5539) { ReadMem(x5454_d6).name("x5536").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5537 = withCtrl(x5539) { OpDef(op=MuxOp, inputs=List(x5536, Const(0.0), x5535)).name("x5537").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5536,Const(0),x5535)
    val x5538_x5493 = withCtrl(x5539) { WriteMem(x5493, x5537).name("x5538_x5493").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5493,x5537,x5534)
    val x5545 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5545").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2015, b1969),Block(Const(())))
    val x5540 = withCtrl(x5545) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x5540").srcCtx("UnrollingBase.scala:28:66") } // And(b2015,b1969)
    val x5541 = withCtrl(x5545) { LoadBanks(List(x5449_d1_b0), List(b1999)).name("x5541").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b1999),Const(0),x5540)
    val x5542 = withCtrl(x5545) { ReadMem(x5454_d7).name("x5542").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5543 = withCtrl(x5545) { OpDef(op=MuxOp, inputs=List(x5542, Const(0.0), x5541)).name("x5543").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5542,Const(0),x5541)
    val x5544_x5494 = withCtrl(x5545) { WriteMem(x5494, x5543).name("x5544_x5494").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5494,x5543,x5540)
    val x5551 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5551").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2016, b1969),Block(Const(())))
    val x5546 = withCtrl(x5551) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x5546").srcCtx("UnrollingBase.scala:28:66") } // And(b2016,b1969)
    val x5547 = withCtrl(x5551) { LoadBanks(List(x5449_d1_b0), List(b2000)).name("x5547").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2000),Const(0),x5546)
    val x5548 = withCtrl(x5551) { ReadMem(x5454_d8).name("x5548").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5549 = withCtrl(x5551) { OpDef(op=MuxOp, inputs=List(x5548, Const(0.0), x5547)).name("x5549").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5548,Const(0),x5547)
    val x5550_x5495 = withCtrl(x5551) { WriteMem(x5495, x5549).name("x5550_x5495").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5495,x5549,x5546)
    val x5557 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5557").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2017, b1969),Block(Const(())))
    val x5552 = withCtrl(x5557) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x5552").srcCtx("UnrollingBase.scala:28:66") } // And(b2017,b1969)
    val x5553 = withCtrl(x5557) { LoadBanks(List(x5449_d1_b0), List(b2001)).name("x5553").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2001),Const(0),x5552)
    val x5554 = withCtrl(x5557) { ReadMem(x5454_d9).name("x5554").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5555 = withCtrl(x5557) { OpDef(op=MuxOp, inputs=List(x5554, Const(0.0), x5553)).name("x5555").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5554,Const(0),x5553)
    val x5556_x5496 = withCtrl(x5557) { WriteMem(x5496, x5555).name("x5556_x5496").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5496,x5555,x5552)
    val x5563 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5563").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2018, b1969),Block(Const(())))
    val x5558 = withCtrl(x5563) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x5558").srcCtx("UnrollingBase.scala:28:66") } // And(b2018,b1969)
    val x5559 = withCtrl(x5563) { LoadBanks(List(x5449_d1_b0), List(b2002)).name("x5559").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2002),Const(0),x5558)
    val x5560 = withCtrl(x5563) { ReadMem(x5454_d10).name("x5560").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5561 = withCtrl(x5563) { OpDef(op=MuxOp, inputs=List(x5560, Const(0.0), x5559)).name("x5561").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5560,Const(0),x5559)
    val x5562_x5497 = withCtrl(x5563) { WriteMem(x5497, x5561).name("x5562_x5497").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5497,x5561,x5558)
    val x5569 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5569").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2019, b1969),Block(Const(())))
    val x5564 = withCtrl(x5569) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x5564").srcCtx("UnrollingBase.scala:28:66") } // And(b2019,b1969)
    val x5565 = withCtrl(x5569) { LoadBanks(List(x5449_d1_b0), List(b2003)).name("x5565").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2003),Const(0),x5564)
    val x5566 = withCtrl(x5569) { ReadMem(x5454_d11).name("x5566").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5567 = withCtrl(x5569) { OpDef(op=MuxOp, inputs=List(x5566, Const(0.0), x5565)).name("x5567").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5566,Const(0),x5565)
    val x5568_x5498 = withCtrl(x5569) { WriteMem(x5498, x5567).name("x5568_x5498").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5498,x5567,x5564)
    val x5575 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5575").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2020, b1969),Block(Const(())))
    val x5570 = withCtrl(x5575) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x5570").srcCtx("UnrollingBase.scala:28:66") } // And(b2020,b1969)
    val x5571 = withCtrl(x5575) { LoadBanks(List(x5449_d1_b0), List(b2004)).name("x5571").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2004),Const(0),x5570)
    val x5572 = withCtrl(x5575) { ReadMem(x5454_d12).name("x5572").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5573 = withCtrl(x5575) { OpDef(op=MuxOp, inputs=List(x5572, Const(0.0), x5571)).name("x5573").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5572,Const(0),x5571)
    val x5574_x5499 = withCtrl(x5575) { WriteMem(x5499, x5573).name("x5574_x5499").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5499,x5573,x5570)
    val x5581 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5581").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2021, b1969),Block(Const(())))
    val x5576 = withCtrl(x5581) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x5576").srcCtx("UnrollingBase.scala:28:66") } // And(b2021,b1969)
    val x5577 = withCtrl(x5581) { LoadBanks(List(x5449_d1_b0), List(b2005)).name("x5577").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2005),Const(0),x5576)
    val x5578 = withCtrl(x5581) { ReadMem(x5454_d13).name("x5578").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5579 = withCtrl(x5581) { OpDef(op=MuxOp, inputs=List(x5578, Const(0.0), x5577)).name("x5579").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5578,Const(0),x5577)
    val x5580_x5500 = withCtrl(x5581) { WriteMem(x5500, x5579).name("x5580_x5500").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5500,x5579,x5576)
    val x5587 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5587").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2022, b1969),Block(Const(())))
    val x5582 = withCtrl(x5587) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x5582").srcCtx("UnrollingBase.scala:28:66") } // And(b2022,b1969)
    val x5583 = withCtrl(x5587) { LoadBanks(List(x5449_d1_b0), List(b2006)).name("x5583").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2006),Const(0),x5582)
    val x5584 = withCtrl(x5587) { ReadMem(x5454_d14).name("x5584").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5585 = withCtrl(x5587) { OpDef(op=MuxOp, inputs=List(x5584, Const(0.0), x5583)).name("x5585").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5584,Const(0),x5583)
    val x5586_x5501 = withCtrl(x5587) { WriteMem(x5501, x5585).name("x5586_x5501").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5501,x5585,x5582)
    val x5593 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5593").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2023, b1969),Block(Const(())))
    val x5588 = withCtrl(x5593) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x5588").srcCtx("UnrollingBase.scala:28:66") } // And(b2023,b1969)
    val x5589 = withCtrl(x5593) { LoadBanks(List(x5449_d1_b0), List(b2007)).name("x5589").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2007),Const(0),x5588)
    val x5590 = withCtrl(x5593) { ReadMem(x5454_d15).name("x5590").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5591 = withCtrl(x5593) { OpDef(op=MuxOp, inputs=List(x5590, Const(0.0), x5589)).name("x5591").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5590,Const(0),x5589)
    val x5592_x5502 = withCtrl(x5593) { WriteMem(x5502, x5591).name("x5592_x5502").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5502,x5591,x5588)
    val x5599 = withCtrl(x5600) { UnitController(style=SeqPipe, level=InnerControl).name("x5599").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2024, b1969),Block(Const(())))
    val x5594 = withCtrl(x5599) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x5594").srcCtx("UnrollingBase.scala:28:66") } // And(b2024,b1969)
    val x5595 = withCtrl(x5599) { LoadBanks(List(x5449_d1_b0), List(b2008)).name("x5595").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x5449,ArrayBuffer(Const(256)),List(b2008),Const(0),x5594)
    val x5596 = withCtrl(x5599) { ReadMem(x5454_d16).name("x5596").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x5597 = withCtrl(x5599) { OpDef(op=MuxOp, inputs=List(x5596, Const(0.0), x5595)).name("x5597").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x5596,Const(0),x5595)
    val x5598_x5503 = withCtrl(x5599) { WriteMem(x5503, x5597).name("x5598_x5503").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x5503,x5597,x5594)
    val x5809 = withCtrl(x5931) { UnitController(style=ForkJoin, level=OuterControl).name("x5809").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x5601 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5601").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5602 = withCtrl(x5809) { CounterChain(List(x5601)).name("x5602").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5601))
    val x5613 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5602).name("x5613").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2009, b1969),x5602,Block(Const(())),List(List(b2186)),List(List(b2187)))
    val b2186 = withCtrl(x5613) { CounterIter(x5601, Some(0)).name("b2186") } // b2186
    val b2187 = withCtrl(x5613) { Const(true).name("b2187") } // b2187
    val x5603 = withCtrl(x5613) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5603").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5604 = withCtrl(x5613) { CounterChain(List(x5603)).name("x5604").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5603))
    val x5612 = withCtrl(x5613) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5604).name("x5612").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2187, b2009, b1969),x5604,Block(Const(())),List(List(b2190)),List(List(b2191)))
    val b2190 = withCtrl(x5612) { CounterIter(x5603, None).name("b2190") } // b2190
    val b2191 = withCtrl(x5612) { Const(true).name("b2191") } // b2191
    val x5605 = withCtrl(x5612) { OpDef(op=BitAnd, inputs=List(b2191, b2187)).name("x5605").srcCtx("UnrollingBase.scala:28:66") } // And(b2191,b2187)
    val x5606 = withCtrl(x5612) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x5606").srcCtx("UnrollingBase.scala:28:66") } // And(b2009,b1969)
    val x5607 = withCtrl(x5612) { OpDef(op=BitAnd, inputs=List(x5605, x5606)).name("x5607").srcCtx("UnrollingBase.scala:28:66") } // And(x5605,x5606)
    val x5608 = withCtrl(x5612) { LoadBanks(List(x5444_d0_b0), List(b1993, b2186, b2190)).name("x5608").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1993, b2186, b2190),x5607)
    val x5609 = withCtrl(x5612) { ReadMem(x5488).name("x5609").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5488)
    val x5610 = withCtrl(x5612) { OpDef(op=FixMul, inputs=List(x5608, x5609)).name("x5610").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5608,x5609)
    val x5611 = withCtrl(x5612) { StoreBanks(List(List(x5472_d0_b0)), List(b2186, b2190), x5610).name("x5611").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5472,List(List(b2186, b2190)),List(x5610),List(x5607))
    val x5614 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5614").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5615 = withCtrl(x5809) { CounterChain(List(x5614)).name("x5615").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5614))
    val x5626 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5615).name("x5626").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2010, b1969),x5615,Block(Const(())),List(List(b2201)),List(List(b2202)))
    val b2201 = withCtrl(x5626) { CounterIter(x5614, Some(0)).name("b2201") } // b2201
    val b2202 = withCtrl(x5626) { Const(true).name("b2202") } // b2202
    val x5616 = withCtrl(x5626) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5616").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5617 = withCtrl(x5626) { CounterChain(List(x5616)).name("x5617").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5616))
    val x5625 = withCtrl(x5626) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5617).name("x5625").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2202, b2010, b1969),x5617,Block(Const(())),List(List(b2205)),List(List(b2206)))
    val b2205 = withCtrl(x5625) { CounterIter(x5616, None).name("b2205") } // b2205
    val b2206 = withCtrl(x5625) { Const(true).name("b2206") } // b2206
    val x5618 = withCtrl(x5625) { OpDef(op=BitAnd, inputs=List(b2206, b2202)).name("x5618").srcCtx("UnrollingBase.scala:28:66") } // And(b2206,b2202)
    val x5619 = withCtrl(x5625) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x5619").srcCtx("UnrollingBase.scala:28:66") } // And(b2010,b1969)
    def split1 = {
    val x5620 = withCtrl(x5625) { OpDef(op=BitAnd, inputs=List(x5618, x5619)).name("x5620").srcCtx("UnrollingBase.scala:28:66") } // And(x5618,x5619)
    val x5621 = withCtrl(x5625) { LoadBanks(List(x5444_d0_b1), List(b1994, b2201, b2205)).name("x5621").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1994, b2201, b2205),x5620)
    val x5622 = withCtrl(x5625) { ReadMem(x5489).name("x5622").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5489)
    val x5623 = withCtrl(x5625) { OpDef(op=FixMul, inputs=List(x5621, x5622)).name("x5623").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5621,x5622)
    val x5624 = withCtrl(x5625) { StoreBanks(List(List(x5473_d0_b0)), List(b2201, b2205), x5623).name("x5624").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5473,List(List(b2201, b2205)),List(x5623),List(x5620))
    val x5627 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5627").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5628 = withCtrl(x5809) { CounterChain(List(x5627)).name("x5628").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5627))
    val x5639 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5628).name("x5639").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2011, b1969),x5628,Block(Const(())),List(List(b2216)),List(List(b2217)))
    val b2216 = withCtrl(x5639) { CounterIter(x5627, Some(0)).name("b2216") } // b2216
    val b2217 = withCtrl(x5639) { Const(true).name("b2217") } // b2217
    val x5629 = withCtrl(x5639) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5629").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5630 = withCtrl(x5639) { CounterChain(List(x5629)).name("x5630").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5629))
    val x5638 = withCtrl(x5639) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5630).name("x5638").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2217, b2011, b1969),x5630,Block(Const(())),List(List(b2220)),List(List(b2221)))
    val b2220 = withCtrl(x5638) { CounterIter(x5629, None).name("b2220") } // b2220
    val b2221 = withCtrl(x5638) { Const(true).name("b2221") } // b2221
    val x5631 = withCtrl(x5638) { OpDef(op=BitAnd, inputs=List(b2221, b2217)).name("x5631").srcCtx("UnrollingBase.scala:28:66") } // And(b2221,b2217)
    val x5632 = withCtrl(x5638) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x5632").srcCtx("UnrollingBase.scala:28:66") } // And(b2011,b1969)
    val x5633 = withCtrl(x5638) { OpDef(op=BitAnd, inputs=List(x5631, x5632)).name("x5633").srcCtx("UnrollingBase.scala:28:66") } // And(x5631,x5632)
    val x5634 = withCtrl(x5638) { LoadBanks(List(x5444_d0_b2), List(b1995, b2216, b2220)).name("x5634").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1995, b2216, b2220),x5633)
    val x5635 = withCtrl(x5638) { ReadMem(x5490).name("x5635").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5490)
    val x5636 = withCtrl(x5638) { OpDef(op=FixMul, inputs=List(x5634, x5635)).name("x5636").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5634,x5635)
    val x5637 = withCtrl(x5638) { StoreBanks(List(List(x5474_d0_b0)), List(b2216, b2220), x5636).name("x5637").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5474,List(List(b2216, b2220)),List(x5636),List(x5633))
    val x5640 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5640").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5641 = withCtrl(x5809) { CounterChain(List(x5640)).name("x5641").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5640))
    val x5652 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5641).name("x5652").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2012, b1969),x5641,Block(Const(())),List(List(b2231)),List(List(b2232)))
    val b2231 = withCtrl(x5652) { CounterIter(x5640, Some(0)).name("b2231") } // b2231
    val b2232 = withCtrl(x5652) { Const(true).name("b2232") } // b2232
    val x5642 = withCtrl(x5652) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5642").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5643 = withCtrl(x5652) { CounterChain(List(x5642)).name("x5643").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5642))
    val x5651 = withCtrl(x5652) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5643).name("x5651").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2232, b2012, b1969),x5643,Block(Const(())),List(List(b2235)),List(List(b2236)))
    val b2235 = withCtrl(x5651) { CounterIter(x5642, None).name("b2235") } // b2235
    val b2236 = withCtrl(x5651) { Const(true).name("b2236") } // b2236
    val x5644 = withCtrl(x5651) { OpDef(op=BitAnd, inputs=List(b2236, b2232)).name("x5644").srcCtx("UnrollingBase.scala:28:66") } // And(b2236,b2232)
    val x5645 = withCtrl(x5651) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x5645").srcCtx("UnrollingBase.scala:28:66") } // And(b2012,b1969)
    val x5646 = withCtrl(x5651) { OpDef(op=BitAnd, inputs=List(x5644, x5645)).name("x5646").srcCtx("UnrollingBase.scala:28:66") } // And(x5644,x5645)
    val x5647 = withCtrl(x5651) { LoadBanks(List(x5444_d0_b3), List(b1996, b2231, b2235)).name("x5647").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1996, b2231, b2235),x5646)
    val x5648 = withCtrl(x5651) { ReadMem(x5491).name("x5648").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5491)
    val x5649 = withCtrl(x5651) { OpDef(op=FixMul, inputs=List(x5647, x5648)).name("x5649").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5647,x5648)
    val x5650 = withCtrl(x5651) { StoreBanks(List(List(x5475_d0_b0)), List(b2231, b2235), x5649).name("x5650").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5475,List(List(b2231, b2235)),List(x5649),List(x5646))
    val x5653 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5653").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5654 = withCtrl(x5809) { CounterChain(List(x5653)).name("x5654").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5653))
    val x5665 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5654).name("x5665").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2013, b1969),x5654,Block(Const(())),List(List(b2246)),List(List(b2247)))
    val b2246 = withCtrl(x5665) { CounterIter(x5653, Some(0)).name("b2246") } // b2246
    val b2247 = withCtrl(x5665) { Const(true).name("b2247") } // b2247
    val x5655 = withCtrl(x5665) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5655").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5656 = withCtrl(x5665) { CounterChain(List(x5655)).name("x5656").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5655))
    val x5664 = withCtrl(x5665) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5656).name("x5664").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2247, b2013, b1969),x5656,Block(Const(())),List(List(b2250)),List(List(b2251)))
    val b2250 = withCtrl(x5664) { CounterIter(x5655, None).name("b2250") } // b2250
    val b2251 = withCtrl(x5664) { Const(true).name("b2251") } // b2251
    val x5657 = withCtrl(x5664) { OpDef(op=BitAnd, inputs=List(b2251, b2247)).name("x5657").srcCtx("UnrollingBase.scala:28:66") } // And(b2251,b2247)
    val x5658 = withCtrl(x5664) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x5658").srcCtx("UnrollingBase.scala:28:66") } // And(b2013,b1969)
    val x5659 = withCtrl(x5664) { OpDef(op=BitAnd, inputs=List(x5657, x5658)).name("x5659").srcCtx("UnrollingBase.scala:28:66") } // And(x5657,x5658)
    val x5660 = withCtrl(x5664) { LoadBanks(List(x5444_d0_b4), List(b1997, b2246, b2250)).name("x5660").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1997, b2246, b2250),x5659)
    val x5661 = withCtrl(x5664) { ReadMem(x5492).name("x5661").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5492)
    val x5662 = withCtrl(x5664) { OpDef(op=FixMul, inputs=List(x5660, x5661)).name("x5662").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5660,x5661)
    val x5663 = withCtrl(x5664) { StoreBanks(List(List(x5476_d0_b0)), List(b2246, b2250), x5662).name("x5663").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5476,List(List(b2246, b2250)),List(x5662),List(x5659))
    val x5666 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5666").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5667 = withCtrl(x5809) { CounterChain(List(x5666)).name("x5667").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5666))
    val x5678 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5667).name("x5678").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2014, b1969),x5667,Block(Const(())),List(List(b2261)),List(List(b2262)))
    val b2261 = withCtrl(x5678) { CounterIter(x5666, Some(0)).name("b2261") } // b2261
    val b2262 = withCtrl(x5678) { Const(true).name("b2262") } // b2262
    val x5668 = withCtrl(x5678) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5668").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5669 = withCtrl(x5678) { CounterChain(List(x5668)).name("x5669").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5668))
    val x5677 = withCtrl(x5678) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5669).name("x5677").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2262, b2014, b1969),x5669,Block(Const(())),List(List(b2265)),List(List(b2266)))
    val b2265 = withCtrl(x5677) { CounterIter(x5668, None).name("b2265") } // b2265
    val b2266 = withCtrl(x5677) { Const(true).name("b2266") } // b2266
    val x5670 = withCtrl(x5677) { OpDef(op=BitAnd, inputs=List(b2266, b2262)).name("x5670").srcCtx("UnrollingBase.scala:28:66") } // And(b2266,b2262)
    val x5671 = withCtrl(x5677) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x5671").srcCtx("UnrollingBase.scala:28:66") } // And(b2014,b1969)
    val x5672 = withCtrl(x5677) { OpDef(op=BitAnd, inputs=List(x5670, x5671)).name("x5672").srcCtx("UnrollingBase.scala:28:66") } // And(x5670,x5671)
    val x5673 = withCtrl(x5677) { LoadBanks(List(x5444_d0_b5), List(b1998, b2261, b2265)).name("x5673").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1998, b2261, b2265),x5672)
    val x5674 = withCtrl(x5677) { ReadMem(x5493).name("x5674").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5493)
    val x5675 = withCtrl(x5677) { OpDef(op=FixMul, inputs=List(x5673, x5674)).name("x5675").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5673,x5674)
    val x5676 = withCtrl(x5677) { StoreBanks(List(List(x5477_d0_b0)), List(b2261, b2265), x5675).name("x5676").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5477,List(List(b2261, b2265)),List(x5675),List(x5672))
    val x5679 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5679").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5680 = withCtrl(x5809) { CounterChain(List(x5679)).name("x5680").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5679))
    val x5691 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5680).name("x5691").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2015, b1969),x5680,Block(Const(())),List(List(b2276)),List(List(b2277)))
    val b2276 = withCtrl(x5691) { CounterIter(x5679, Some(0)).name("b2276") } // b2276
    val b2277 = withCtrl(x5691) { Const(true).name("b2277") } // b2277
    val x5681 = withCtrl(x5691) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5681").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5682 = withCtrl(x5691) { CounterChain(List(x5681)).name("x5682").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5681))
    val x5690 = withCtrl(x5691) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5682).name("x5690").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2277, b2015, b1969),x5682,Block(Const(())),List(List(b2280)),List(List(b2281)))
    val b2280 = withCtrl(x5690) { CounterIter(x5681, None).name("b2280") } // b2280
    val b2281 = withCtrl(x5690) { Const(true).name("b2281") } // b2281
    val x5683 = withCtrl(x5690) { OpDef(op=BitAnd, inputs=List(b2281, b2277)).name("x5683").srcCtx("UnrollingBase.scala:28:66") } // And(b2281,b2277)
    val x5684 = withCtrl(x5690) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x5684").srcCtx("UnrollingBase.scala:28:66") } // And(b2015,b1969)
    val x5685 = withCtrl(x5690) { OpDef(op=BitAnd, inputs=List(x5683, x5684)).name("x5685").srcCtx("UnrollingBase.scala:28:66") } // And(x5683,x5684)
    val x5686 = withCtrl(x5690) { LoadBanks(List(x5444_d0_b6), List(b1999, b2276, b2280)).name("x5686").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b1999, b2276, b2280),x5685)
    val x5687 = withCtrl(x5690) { ReadMem(x5494).name("x5687").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5494)
    val x5688 = withCtrl(x5690) { OpDef(op=FixMul, inputs=List(x5686, x5687)).name("x5688").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5686,x5687)
    val x5689 = withCtrl(x5690) { StoreBanks(List(List(x5478_d0_b0)), List(b2276, b2280), x5688).name("x5689").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5478,List(List(b2276, b2280)),List(x5688),List(x5685))
    val x5692 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5692").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5693 = withCtrl(x5809) { CounterChain(List(x5692)).name("x5693").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5692))
    val x5704 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5693).name("x5704").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2016, b1969),x5693,Block(Const(())),List(List(b2291)),List(List(b2292)))
    val b2291 = withCtrl(x5704) { CounterIter(x5692, Some(0)).name("b2291") } // b2291
    val b2292 = withCtrl(x5704) { Const(true).name("b2292") } // b2292
    val x5694 = withCtrl(x5704) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5694").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5695 = withCtrl(x5704) { CounterChain(List(x5694)).name("x5695").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5694))
    val x5703 = withCtrl(x5704) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5695).name("x5703").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2292, b2016, b1969),x5695,Block(Const(())),List(List(b2295)),List(List(b2296)))
    val b2295 = withCtrl(x5703) { CounterIter(x5694, None).name("b2295") } // b2295
    val b2296 = withCtrl(x5703) { Const(true).name("b2296") } // b2296
    val x5696 = withCtrl(x5703) { OpDef(op=BitAnd, inputs=List(b2296, b2292)).name("x5696").srcCtx("UnrollingBase.scala:28:66") } // And(b2296,b2292)
    val x5697 = withCtrl(x5703) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x5697").srcCtx("UnrollingBase.scala:28:66") } // And(b2016,b1969)
    val x5698 = withCtrl(x5703) { OpDef(op=BitAnd, inputs=List(x5696, x5697)).name("x5698").srcCtx("UnrollingBase.scala:28:66") } // And(x5696,x5697)
    val x5699 = withCtrl(x5703) { LoadBanks(List(x5444_d0_b7), List(b2000, b2291, b2295)).name("x5699").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2000, b2291, b2295),x5698)
    val x5700 = withCtrl(x5703) { ReadMem(x5495).name("x5700").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5495)
    val x5701 = withCtrl(x5703) { OpDef(op=FixMul, inputs=List(x5699, x5700)).name("x5701").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5699,x5700)
    val x5702 = withCtrl(x5703) { StoreBanks(List(List(x5479_d0_b0)), List(b2291, b2295), x5701).name("x5702").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5479,List(List(b2291, b2295)),List(x5701),List(x5698))
    val x5705 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5705").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5706 = withCtrl(x5809) { CounterChain(List(x5705)).name("x5706").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5705))
    val x5717 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5706).name("x5717").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2017, b1969),x5706,Block(Const(())),List(List(b2306)),List(List(b2307)))
    val b2306 = withCtrl(x5717) { CounterIter(x5705, Some(0)).name("b2306") } // b2306
    val b2307 = withCtrl(x5717) { Const(true).name("b2307") } // b2307
    val x5707 = withCtrl(x5717) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5707").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5708 = withCtrl(x5717) { CounterChain(List(x5707)).name("x5708").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5707))
    val x5716 = withCtrl(x5717) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5708).name("x5716").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2307, b2017, b1969),x5708,Block(Const(())),List(List(b2310)),List(List(b2311)))
    val b2310 = withCtrl(x5716) { CounterIter(x5707, None).name("b2310") } // b2310
    val b2311 = withCtrl(x5716) { Const(true).name("b2311") } // b2311
    val x5709 = withCtrl(x5716) { OpDef(op=BitAnd, inputs=List(b2311, b2307)).name("x5709").srcCtx("UnrollingBase.scala:28:66") } // And(b2311,b2307)
    val x5710 = withCtrl(x5716) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x5710").srcCtx("UnrollingBase.scala:28:66") } // And(b2017,b1969)
    val x5711 = withCtrl(x5716) { OpDef(op=BitAnd, inputs=List(x5709, x5710)).name("x5711").srcCtx("UnrollingBase.scala:28:66") } // And(x5709,x5710)
    val x5712 = withCtrl(x5716) { LoadBanks(List(x5444_d0_b8), List(b2001, b2306, b2310)).name("x5712").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2001, b2306, b2310),x5711)
    val x5713 = withCtrl(x5716) { ReadMem(x5496).name("x5713").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5496)
    val x5714 = withCtrl(x5716) { OpDef(op=FixMul, inputs=List(x5712, x5713)).name("x5714").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5712,x5713)
    val x5715 = withCtrl(x5716) { StoreBanks(List(List(x5480_d0_b0)), List(b2306, b2310), x5714).name("x5715").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5480,List(List(b2306, b2310)),List(x5714),List(x5711))
    val x5718 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5718").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5719 = withCtrl(x5809) { CounterChain(List(x5718)).name("x5719").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5718))
    val x5730 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5719).name("x5730").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2018, b1969),x5719,Block(Const(())),List(List(b2321)),List(List(b2322)))
    val b2321 = withCtrl(x5730) { CounterIter(x5718, Some(0)).name("b2321") } // b2321
    val b2322 = withCtrl(x5730) { Const(true).name("b2322") } // b2322
    val x5720 = withCtrl(x5730) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5720").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5721 = withCtrl(x5730) { CounterChain(List(x5720)).name("x5721").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5720))
    val x5729 = withCtrl(x5730) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5721).name("x5729").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2322, b2018, b1969),x5721,Block(Const(())),List(List(b2325)),List(List(b2326)))
    val b2325 = withCtrl(x5729) { CounterIter(x5720, None).name("b2325") } // b2325
    val b2326 = withCtrl(x5729) { Const(true).name("b2326") } // b2326
    val x5722 = withCtrl(x5729) { OpDef(op=BitAnd, inputs=List(b2326, b2322)).name("x5722").srcCtx("UnrollingBase.scala:28:66") } // And(b2326,b2322)
    val x5723 = withCtrl(x5729) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x5723").srcCtx("UnrollingBase.scala:28:66") } // And(b2018,b1969)
    val x5724 = withCtrl(x5729) { OpDef(op=BitAnd, inputs=List(x5722, x5723)).name("x5724").srcCtx("UnrollingBase.scala:28:66") } // And(x5722,x5723)
    val x5725 = withCtrl(x5729) { LoadBanks(List(x5444_d0_b9), List(b2002, b2321, b2325)).name("x5725").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2002, b2321, b2325),x5724)
    val x5726 = withCtrl(x5729) { ReadMem(x5497).name("x5726").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5497)
    val x5727 = withCtrl(x5729) { OpDef(op=FixMul, inputs=List(x5725, x5726)).name("x5727").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5725,x5726)
    val x5728 = withCtrl(x5729) { StoreBanks(List(List(x5481_d0_b0)), List(b2321, b2325), x5727).name("x5728").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5481,List(List(b2321, b2325)),List(x5727),List(x5724))
    val x5731 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5731").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5732 = withCtrl(x5809) { CounterChain(List(x5731)).name("x5732").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5731))
    val x5743 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5732).name("x5743").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2019, b1969),x5732,Block(Const(())),List(List(b2336)),List(List(b2337)))
    val b2336 = withCtrl(x5743) { CounterIter(x5731, Some(0)).name("b2336") } // b2336
    val b2337 = withCtrl(x5743) { Const(true).name("b2337") } // b2337
    val x5733 = withCtrl(x5743) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5733").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5734 = withCtrl(x5743) { CounterChain(List(x5733)).name("x5734").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5733))
    val x5742 = withCtrl(x5743) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5734).name("x5742").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2337, b2019, b1969),x5734,Block(Const(())),List(List(b2340)),List(List(b2341)))
    val b2340 = withCtrl(x5742) { CounterIter(x5733, None).name("b2340") } // b2340
    val b2341 = withCtrl(x5742) { Const(true).name("b2341") } // b2341
    val x5735 = withCtrl(x5742) { OpDef(op=BitAnd, inputs=List(b2341, b2337)).name("x5735").srcCtx("UnrollingBase.scala:28:66") } // And(b2341,b2337)
    val x5736 = withCtrl(x5742) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x5736").srcCtx("UnrollingBase.scala:28:66") } // And(b2019,b1969)
    val x5737 = withCtrl(x5742) { OpDef(op=BitAnd, inputs=List(x5735, x5736)).name("x5737").srcCtx("UnrollingBase.scala:28:66") } // And(x5735,x5736)
    val x5738 = withCtrl(x5742) { LoadBanks(List(x5444_d0_b10), List(b2003, b2336, b2340)).name("x5738").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2003, b2336, b2340),x5737)
    val x5739 = withCtrl(x5742) { ReadMem(x5498).name("x5739").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5498)
    val x5740 = withCtrl(x5742) { OpDef(op=FixMul, inputs=List(x5738, x5739)).name("x5740").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5738,x5739)
    val x5741 = withCtrl(x5742) { StoreBanks(List(List(x5482_d0_b0)), List(b2336, b2340), x5740).name("x5741").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5482,List(List(b2336, b2340)),List(x5740),List(x5737))
    val x5744 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5744").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5745 = withCtrl(x5809) { CounterChain(List(x5744)).name("x5745").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5744))
    val x5756 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5745).name("x5756").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2020, b1969),x5745,Block(Const(())),List(List(b2351)),List(List(b2352)))
    val b2351 = withCtrl(x5756) { CounterIter(x5744, Some(0)).name("b2351") } // b2351
    val b2352 = withCtrl(x5756) { Const(true).name("b2352") } // b2352
    val x5746 = withCtrl(x5756) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5746").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5747 = withCtrl(x5756) { CounterChain(List(x5746)).name("x5747").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5746))
    val x5755 = withCtrl(x5756) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5747).name("x5755").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2352, b2020, b1969),x5747,Block(Const(())),List(List(b2355)),List(List(b2356)))
    val b2355 = withCtrl(x5755) { CounterIter(x5746, None).name("b2355") } // b2355
    val b2356 = withCtrl(x5755) { Const(true).name("b2356") } // b2356
    val x5748 = withCtrl(x5755) { OpDef(op=BitAnd, inputs=List(b2356, b2352)).name("x5748").srcCtx("UnrollingBase.scala:28:66") } // And(b2356,b2352)
    val x5749 = withCtrl(x5755) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x5749").srcCtx("UnrollingBase.scala:28:66") } // And(b2020,b1969)
    val x5750 = withCtrl(x5755) { OpDef(op=BitAnd, inputs=List(x5748, x5749)).name("x5750").srcCtx("UnrollingBase.scala:28:66") } // And(x5748,x5749)
    val x5751 = withCtrl(x5755) { LoadBanks(List(x5444_d0_b11), List(b2004, b2351, b2355)).name("x5751").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2004, b2351, b2355),x5750)
    val x5752 = withCtrl(x5755) { ReadMem(x5499).name("x5752").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5499)
    val x5753 = withCtrl(x5755) { OpDef(op=FixMul, inputs=List(x5751, x5752)).name("x5753").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5751,x5752)
    val x5754 = withCtrl(x5755) { StoreBanks(List(List(x5483_d0_b0)), List(b2351, b2355), x5753).name("x5754").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5483,List(List(b2351, b2355)),List(x5753),List(x5750))
    val x5757 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5757").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5758 = withCtrl(x5809) { CounterChain(List(x5757)).name("x5758").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5757))
    val x5769 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5758).name("x5769").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2021, b1969),x5758,Block(Const(())),List(List(b2366)),List(List(b2367)))
    val b2366 = withCtrl(x5769) { CounterIter(x5757, Some(0)).name("b2366") } // b2366
    val b2367 = withCtrl(x5769) { Const(true).name("b2367") } // b2367
    val x5759 = withCtrl(x5769) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5759").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5760 = withCtrl(x5769) { CounterChain(List(x5759)).name("x5760").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5759))
    val x5768 = withCtrl(x5769) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5760).name("x5768").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2367, b2021, b1969),x5760,Block(Const(())),List(List(b2370)),List(List(b2371)))
    val b2370 = withCtrl(x5768) { CounterIter(x5759, None).name("b2370") } // b2370
    val b2371 = withCtrl(x5768) { Const(true).name("b2371") } // b2371
    val x5761 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(b2371, b2367)).name("x5761").srcCtx("UnrollingBase.scala:28:66") } // And(b2371,b2367)
    val x5762 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x5762").srcCtx("UnrollingBase.scala:28:66") } // And(b2021,b1969)
    val x5763 = withCtrl(x5768) { OpDef(op=BitAnd, inputs=List(x5761, x5762)).name("x5763").srcCtx("UnrollingBase.scala:28:66") } // And(x5761,x5762)
    val x5764 = withCtrl(x5768) { LoadBanks(List(x5444_d0_b12), List(b2005, b2366, b2370)).name("x5764").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2005, b2366, b2370),x5763)
    val x5765 = withCtrl(x5768) { ReadMem(x5500).name("x5765").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5500)
    val x5766 = withCtrl(x5768) { OpDef(op=FixMul, inputs=List(x5764, x5765)).name("x5766").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5764,x5765)
    val x5767 = withCtrl(x5768) { StoreBanks(List(List(x5484_d0_b0)), List(b2366, b2370), x5766).name("x5767").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5484,List(List(b2366, b2370)),List(x5766),List(x5763))
    val x5770 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5770").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5771 = withCtrl(x5809) { CounterChain(List(x5770)).name("x5771").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5770))
    val x5782 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5771).name("x5782").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2022, b1969),x5771,Block(Const(())),List(List(b2381)),List(List(b2382)))
    val b2381 = withCtrl(x5782) { CounterIter(x5770, Some(0)).name("b2381") } // b2381
    val b2382 = withCtrl(x5782) { Const(true).name("b2382") } // b2382
    val x5772 = withCtrl(x5782) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5772").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5773 = withCtrl(x5782) { CounterChain(List(x5772)).name("x5773").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5772))
    val x5781 = withCtrl(x5782) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5773).name("x5781").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2382, b2022, b1969),x5773,Block(Const(())),List(List(b2385)),List(List(b2386)))
    val b2385 = withCtrl(x5781) { CounterIter(x5772, None).name("b2385") } // b2385
    val b2386 = withCtrl(x5781) { Const(true).name("b2386") } // b2386
    val x5774 = withCtrl(x5781) { OpDef(op=BitAnd, inputs=List(b2386, b2382)).name("x5774").srcCtx("UnrollingBase.scala:28:66") } // And(b2386,b2382)
    val x5775 = withCtrl(x5781) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x5775").srcCtx("UnrollingBase.scala:28:66") } // And(b2022,b1969)
    val x5776 = withCtrl(x5781) { OpDef(op=BitAnd, inputs=List(x5774, x5775)).name("x5776").srcCtx("UnrollingBase.scala:28:66") } // And(x5774,x5775)
    val x5777 = withCtrl(x5781) { LoadBanks(List(x5444_d0_b13), List(b2006, b2381, b2385)).name("x5777").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2006, b2381, b2385),x5776)
    val x5778 = withCtrl(x5781) { ReadMem(x5501).name("x5778").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5501)
    val x5779 = withCtrl(x5781) { OpDef(op=FixMul, inputs=List(x5777, x5778)).name("x5779").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5777,x5778)
    val x5780 = withCtrl(x5781) { StoreBanks(List(List(x5485_d0_b0)), List(b2381, b2385), x5779).name("x5780").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5485,List(List(b2381, b2385)),List(x5779),List(x5776))
    val x5783 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5783").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5784 = withCtrl(x5809) { CounterChain(List(x5783)).name("x5784").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5783))
    val x5795 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5784).name("x5795").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2023, b1969),x5784,Block(Const(())),List(List(b2396)),List(List(b2397)))
    val b2396 = withCtrl(x5795) { CounterIter(x5783, Some(0)).name("b2396") } // b2396
    val b2397 = withCtrl(x5795) { Const(true).name("b2397") } // b2397
    val x5785 = withCtrl(x5795) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5785").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5786 = withCtrl(x5795) { CounterChain(List(x5785)).name("x5786").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5785))
    val x5794 = withCtrl(x5795) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5786).name("x5794").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2397, b2023, b1969),x5786,Block(Const(())),List(List(b2400)),List(List(b2401)))
    val b2400 = withCtrl(x5794) { CounterIter(x5785, None).name("b2400") } // b2400
    val b2401 = withCtrl(x5794) { Const(true).name("b2401") } // b2401
    val x5787 = withCtrl(x5794) { OpDef(op=BitAnd, inputs=List(b2401, b2397)).name("x5787").srcCtx("UnrollingBase.scala:28:66") } // And(b2401,b2397)
    val x5788 = withCtrl(x5794) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x5788").srcCtx("UnrollingBase.scala:28:66") } // And(b2023,b1969)
    val x5789 = withCtrl(x5794) { OpDef(op=BitAnd, inputs=List(x5787, x5788)).name("x5789").srcCtx("UnrollingBase.scala:28:66") } // And(x5787,x5788)
    val x5790 = withCtrl(x5794) { LoadBanks(List(x5444_d0_b14), List(b2007, b2396, b2400)).name("x5790").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2007, b2396, b2400),x5789)
    val x5791 = withCtrl(x5794) { ReadMem(x5502).name("x5791").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5502)
    val x5792 = withCtrl(x5794) { OpDef(op=FixMul, inputs=List(x5790, x5791)).name("x5792").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5790,x5791)
    val x5793 = withCtrl(x5794) { StoreBanks(List(List(x5486_d0_b0)), List(b2396, b2400), x5792).name("x5793").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5486,List(List(b2396, b2400)),List(x5792),List(x5789))
    val x5796 = withCtrl(x5809) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5796").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5797 = withCtrl(x5809) { CounterChain(List(x5796)).name("x5797").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x5796))
    val x5808 = withCtrl(x5809) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5797).name("x5808").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2024, b1969),x5797,Block(Const(())),List(List(b2411)),List(List(b2412)))
    val b2411 = withCtrl(x5808) { CounterIter(x5796, Some(0)).name("b2411") } // b2411
    val b2412 = withCtrl(x5808) { Const(true).name("b2412") } // b2412
    val x5798 = withCtrl(x5808) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5798").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5799 = withCtrl(x5808) { CounterChain(List(x5798)).name("x5799").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x5798))
    val x5807 = withCtrl(x5808) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5799).name("x5807").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2412, b2024, b1969),x5799,Block(Const(())),List(List(b2415)),List(List(b2416)))
    val b2415 = withCtrl(x5807) { CounterIter(x5798, None).name("b2415") } // b2415
    val b2416 = withCtrl(x5807) { Const(true).name("b2416") } // b2416
    val x5800 = withCtrl(x5807) { OpDef(op=BitAnd, inputs=List(b2416, b2412)).name("x5800").srcCtx("UnrollingBase.scala:28:66") } // And(b2416,b2412)
    val x5801 = withCtrl(x5807) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x5801").srcCtx("UnrollingBase.scala:28:66") } // And(b2024,b1969)
    val x5802 = withCtrl(x5807) { OpDef(op=BitAnd, inputs=List(x5800, x5801)).name("x5802").srcCtx("UnrollingBase.scala:28:66") } // And(x5800,x5801)
    val x5803 = withCtrl(x5807) { LoadBanks(List(x5444_d0_b15), List(b2008, b2411, b2415)).name("x5803").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x5444,List(b2008, b2411, b2415),x5802)
    val x5804 = withCtrl(x5807) { ReadMem(x5503).name("x5804").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x5503)
    val x5805 = withCtrl(x5807) { OpDef(op=FixMul, inputs=List(x5803, x5804)).name("x5805").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x5803,x5804)
    val x5806 = withCtrl(x5807) { StoreBanks(List(List(x5487_d0_b0)), List(b2411, b2415), x5805).name("x5806").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x5487,List(List(b2411, b2415)),List(x5805),List(x5802))
    val x5810 = withCtrl(x5931) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5810").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5811 = withCtrl(x5931) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5811").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5812 = withCtrl(x5931) { CounterChain(List(x5811,x5810)).name("x5812").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(ArrayBuffer(x5811, x5810))
    val x5930 = withCtrl(x5931) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5812).name("x5930").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledForeach(List(),x5812,Block(Const(())),ArrayBuffer(List(b2427), List(b2428)),ArrayBuffer(List(b2429), List(b2430)))
    val b2427 = withCtrl(x5930) { CounterIter(x5811, Some(0)).name("b2427") } // b2427
    val b2429 = withCtrl(x5930) { Const(true).name("b2429") } // b2429
    val b2428 = withCtrl(x5930) { CounterIter(x5810, None).name("b2428") } // b2428
    val b2430 = withCtrl(x5930) { Const(true).name("b2430") } // b2430
    val x5813 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2429, b2430)).name("x5813").srcCtx("UnrollingBase.scala:28:66") } // And(b2429,b2430)
    val x5814 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5813, b1969)).name("x5814").srcCtx("UnrollingBase.scala:28:66") } // And(x5813,b1969)
    val x5815 = withCtrl(x5930) { LoadBanks(List(x5472_d0_b0), List(b2427, b2428)).name("x5815").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5472,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5816 = withCtrl(x5930) { x5815 } // VectorApply(x5815,0)
    val x5817 = withCtrl(x5930) { LoadBanks(List(x5473_d0_b0), List(b2427, b2428)).name("x5817").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5473,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5818 = withCtrl(x5930) { x5817 } // VectorApply(x5817,0)
    val x5819 = withCtrl(x5930) { LoadBanks(List(x5474_d0_b0), List(b2427, b2428)).name("x5819").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5474,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5820 = withCtrl(x5930) { x5819 } // VectorApply(x5819,0)
    val x5821 = withCtrl(x5930) { LoadBanks(List(x5475_d0_b0), List(b2427, b2428)).name("x5821").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5475,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5822 = withCtrl(x5930) { x5821 } // VectorApply(x5821,0)
    val x5823 = withCtrl(x5930) { LoadBanks(List(x5476_d0_b0), List(b2427, b2428)).name("x5823").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5476,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5824 = withCtrl(x5930) { x5823 } // VectorApply(x5823,0)
    val x5825 = withCtrl(x5930) { LoadBanks(List(x5477_d0_b0), List(b2427, b2428)).name("x5825").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5477,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5826 = withCtrl(x5930) { x5825 } // VectorApply(x5825,0)
    val x5827 = withCtrl(x5930) { LoadBanks(List(x5478_d0_b0), List(b2427, b2428)).name("x5827").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5478,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5828 = withCtrl(x5930) { x5827 } // VectorApply(x5827,0)
    val x5829 = withCtrl(x5930) { LoadBanks(List(x5479_d0_b0), List(b2427, b2428)).name("x5829").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5479,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5830 = withCtrl(x5930) { x5829 } // VectorApply(x5829,0)
    val x5831 = withCtrl(x5930) { LoadBanks(List(x5480_d0_b0), List(b2427, b2428)).name("x5831").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5480,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5832 = withCtrl(x5930) { x5831 } // VectorApply(x5831,0)
    val x5833 = withCtrl(x5930) { LoadBanks(List(x5481_d0_b0), List(b2427, b2428)).name("x5833").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5481,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5834 = withCtrl(x5930) { x5833 } // VectorApply(x5833,0)
    val x5835 = withCtrl(x5930) { LoadBanks(List(x5482_d0_b0), List(b2427, b2428)).name("x5835").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5482,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5836 = withCtrl(x5930) { x5835 } // VectorApply(x5835,0)
    val x5837 = withCtrl(x5930) { LoadBanks(List(x5483_d0_b0), List(b2427, b2428)).name("x5837").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5483,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5838 = withCtrl(x5930) { x5837 } // VectorApply(x5837,0)
    val x5839 = withCtrl(x5930) { LoadBanks(List(x5484_d0_b0), List(b2427, b2428)).name("x5839").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5484,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5840 = withCtrl(x5930) { x5839 } // VectorApply(x5839,0)
    val x5841 = withCtrl(x5930) { LoadBanks(List(x5485_d0_b0), List(b2427, b2428)).name("x5841").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5485,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5842 = withCtrl(x5930) { x5841 } // VectorApply(x5841,0)
    val x5843 = withCtrl(x5930) { LoadBanks(List(x5486_d0_b0), List(b2427, b2428)).name("x5843").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5486,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5844 = withCtrl(x5930) { x5843 } // VectorApply(x5843,0)
    val x5845 = withCtrl(x5930) { LoadBanks(List(x5487_d0_b0), List(b2427, b2428)).name("x5845").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5487,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5846 = withCtrl(x5930) { x5845 } // VectorApply(x5845,0)
    val x5847 = withCtrl(x5930) { LoadBanks(List(x5452_d1_b0), List(b2427, b2428)).name("x5847").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x5452,List(ArrayBuffer(b2427, b2428)),List(x5814))
    val x5848 = withCtrl(x5930) { x5847 } // VectorApply(x5847,0)
    val x5849 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x5849").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2009,b1969)
    val x5850 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x5850").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2010,b1969)
    val x5851 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x5851").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2011,b1969)
    val x5852 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x5852").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2012,b1969)
    val x5853 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x5853").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2013,b1969)
    val x5854 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x5854").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2014,b1969)
    val x5855 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x5855").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2015,b1969)
    val x5856 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x5856").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2016,b1969)
    val x5857 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x5857").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2017,b1969)
    val x5858 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x5858").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2018,b1969)
    val x5859 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x5859").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2019,b1969)
    val x5860 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x5860").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2020,b1969)
    val x5861 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x5861").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2021,b1969)
    val x5862 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x5862").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2022,b1969)
    val x5863 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x5863").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2023,b1969)
    val x5864 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x5864").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2024,b1969)
    val x5865 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5849, x5814)).name("x5865").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5849,x5814)
    val x5866 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5850, x5814)).name("x5866").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5850,x5814)
    val x5867 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5851, x5814)).name("x5867").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5851,x5814)
    val x5868 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5852, x5814)).name("x5868").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5852,x5814)
    val x5869 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5853, x5814)).name("x5869").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5853,x5814)
    val x5870 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5854, x5814)).name("x5870").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5854,x5814)
    val x5871 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5855, x5814)).name("x5871").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5855,x5814)
    val x5872 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5856, x5814)).name("x5872").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5856,x5814)
    val x5873 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5857, x5814)).name("x5873").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5857,x5814)
    val x5874 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5858, x5814)).name("x5874").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5858,x5814)
    val x5875 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5859, x5814)).name("x5875").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5859,x5814)
    val x5876 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5860, x5814)).name("x5876").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5860,x5814)
    val x5877 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5861, x5814)).name("x5877").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5861,x5814)
    val x5878 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5862, x5814)).name("x5878").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5862,x5814)
    val x5879 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5863, x5814)).name("x5879").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5863,x5814)
    val x5880 = withCtrl(x5930) { OpDef(op=BitAnd, inputs=List(x5864, x5814)).name("x5880").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x5864,x5814)
    val x5881 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5816, x5818)).name("x5881").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5816,x5818)
    val x5882 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5866, x5881, x5816)).name("x5882").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5866,x5881,x5816)
    val x5883 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5865, x5866)).name("x5883").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5865,x5866)
    val x5884 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5820, x5822)).name("x5884").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5820,x5822)
    val x5885 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5868, x5884, x5820)).name("x5885").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5868,x5884,x5820)
    val x5886 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5867, x5868)).name("x5886").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5867,x5868)
    val x5887 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5824, x5826)).name("x5887").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5824,x5826)
    val x5888 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5870, x5887, x5824)).name("x5888").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5870,x5887,x5824)
    val x5889 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5869, x5870)).name("x5889").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5869,x5870)
    val x5890 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5828, x5830)).name("x5890").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5828,x5830)
    val x5891 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5872, x5890, x5828)).name("x5891").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5872,x5890,x5828)
    val x5892 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5871, x5872)).name("x5892").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5871,x5872)
    val x5893 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5832, x5834)).name("x5893").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5832,x5834)
    val x5894 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5874, x5893, x5832)).name("x5894").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5874,x5893,x5832)
    val x5895 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5873, x5874)).name("x5895").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5873,x5874)
    val x5896 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5836, x5838)).name("x5896").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5836,x5838)
    val x5897 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5876, x5896, x5836)).name("x5897").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5876,x5896,x5836)
    val x5898 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5875, x5876)).name("x5898").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5875,x5876)
    val x5899 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5840, x5842)).name("x5899").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5840,x5842)
    val x5900 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5878, x5899, x5840)).name("x5900").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5878,x5899,x5840)
    val x5901 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5877, x5878)).name("x5901").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5877,x5878)
    val x5902 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5844, x5846)).name("x5902").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5844,x5846)
    val x5903 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5880, x5902, x5844)).name("x5903").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5880,x5902,x5844)
    val x5904 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5879, x5880)).name("x5904").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5879,x5880)
    val x5905 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5882, x5885)).name("x5905").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5882,x5885)
    val x5906 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5886, x5905, x5882)).name("x5906").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5886,x5905,x5882)
    val x5907 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5883, x5886)).name("x5907").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5883,x5886)
    val x5908 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5888, x5891)).name("x5908").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5888,x5891)
    val x5909 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5892, x5908, x5888)).name("x5909").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5892,x5908,x5888)
    val x5910 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5889, x5892)).name("x5910").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5889,x5892)
    val x5911 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5894, x5897)).name("x5911").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5894,x5897)
    val x5912 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5898, x5911, x5894)).name("x5912").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5898,x5911,x5894)
    val x5913 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5895, x5898)).name("x5913").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5895,x5898)
    val x5914 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5900, x5903)).name("x5914").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5900,x5903)
    val x5915 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5904, x5914, x5900)).name("x5915").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5904,x5914,x5900)
    val x5916 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5901, x5904)).name("x5916").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5901,x5904)
    val x5917 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5906, x5909)).name("x5917").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5906,x5909)
    val x5918 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5910, x5917, x5906)).name("x5918").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5910,x5917,x5906)
    val x5919 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5907, x5910)).name("x5919").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5907,x5910)
    val x5920 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5912, x5915)).name("x5920").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5912,x5915)
    val x5921 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5916, x5920, x5912)).name("x5921").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5916,x5920,x5912)
    val x5922 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5913, x5916)).name("x5922").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5913,x5916)
    val x5923 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5918, x5921)).name("x5923").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5918,x5921)
    val x5924 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5922, x5923, x5918)).name("x5924").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5922,x5923,x5918)
    val x5925 = withCtrl(x5930) { OpDef(op=BitOr, inputs=List(x5919, x5922)).name("x5925").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x5919,x5922)
    val x5926 = withCtrl(x5930) { OpDef(op=FixEql, inputs=List(b1993, Const(0))).name("x5926").srcCtx("VecMatMultBiasAdd.scala:179:6") } // FixEql(b1993,Const(0))
    val x5927 = withCtrl(x5930) { OpDef(op=FixAdd, inputs=List(x5924, x5848)).name("x5927").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x5924,x5848)
    val x5928 = withCtrl(x5930) { OpDef(op=MuxOp, inputs=List(x5926, x5924, x5927)).name("x5928").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x5926,x5924,x5927)
    val x5929 = withCtrl(x5930) { StoreBanks(List(List(x5452_d0_b0, x5452_d0_b1), List(x5452_d1_b0)), List(b2427, b2428), x5928).name("x5929").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMStore(x5452,List(ArrayBuffer(b2427, b2428)),List(x5928),List(x5814))
    antiDepsOf(x5929)=List(x5847)
    val x5932 = withCtrl(x6081) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x5932").srcCtx("VecMatMultBiasAdd.scala:181:26") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x5933 = withCtrl(x6081) { CounterChain(List(x5932)).name("x5933").srcCtx("VecMatMultBiasAdd.scala:181:42") } // CounterChainNew(List(x5932))
    val x5969 = withCtrl(x6081) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5933).name("x5969").srcCtx("VecMatMultBiasAdd.scala:181:42") } // UnrolledForeach(List(b1969),x5933,Block(Const(())),List(List(b2552, b2553)),List(List(b2554, b2555)))
    val b2552 = withCtrl(x5969) { CounterIter(x5932, Some(0)).name("b2552") } // b2552
    val b2554 = withCtrl(x5969) { Const(true).name("b2554") } // b2554
    val b2553 = withCtrl(x5969) { CounterIter(x5932, Some(1)).name("b2553") } // b2553
    val b2555 = withCtrl(x5969) { Const(true).name("b2555") } // b2555
    val x5968 = withCtrl(x5969) { UnitController(style=ForkJoin, level=OuterControl).name("x5968").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x5934 = withCtrl(x5968) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5934").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5935 = withCtrl(x5968) { CounterChain(List(x5934)).name("x5935").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x5934))
    val x5950 = withCtrl(x5968) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5935).name("x5950").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2554, b1969),x5935,Block(Const(())),List(List(b2560)),List(List(b2561)))
    val b2560 = withCtrl(x5950) { CounterIter(x5934, None).name("b2560") } // b2560
    val b2561 = withCtrl(x5950) { Const(true).name("b2561") } // b2561
    val x5936 = withCtrl(x5950) { ReadMem(x5457_d0).name("x5936").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5457)
    val x5937 = withCtrl(x5950) { OpDef(op=BitAnd, inputs=List(b2561, b2554)).name("x5937").srcCtx("UnrollingBase.scala:28:66") } // And(b2561,b2554)
    val x5938 = withCtrl(x5950) { OpDef(op=BitAnd, inputs=List(x5937, b1969)).name("x5938").srcCtx("UnrollingBase.scala:28:66") } // And(x5937,b1969)
    val x5939 = withCtrl(x5950) { LoadBanks(List(x5445_d0_b0), List(x5936, b2552, b2560)).name("x5939").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x5445,List(x5936, b2552, b2560),x5938)
    val x5940 = withCtrl(x5950) { LoadBanks(List(x5446_d0_b0), List(x5936, b2552, b2560)).name("x5940").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x5446,List(x5936, b2552, b2560),x5938)
    val x5941 = withCtrl(x5950) { LoadBanks(List(x5447_d0_b0), List(x5936, b2552, b2560)).name("x5941").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x5447,List(x5936, b2552, b2560),x5938)
    val x5942 = withCtrl(x5950) { ReadMem(x5456_d0).name("x5942").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5456)
    val x5943 = withCtrl(x5950) { OpDef(op=MuxOp, inputs=List(x5942, x5941, x5940)).name("x5943").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x5942,x5941,x5940)
    val x5944 = withCtrl(x5950) { ReadMem(x5455_d0).name("x5944").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5455)
    val x5945 = withCtrl(x5950) { OpDef(op=MuxOp, inputs=List(x5944, x5939, x5943)).name("x5945").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x5944,x5939,x5943)
    val x5946 = withCtrl(x5950) { LoadBanks(List(x5452_d0_b0), List(b2552, b2560)).name("x5946").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x5452,List(List(b2552, b2560)),List(x5938))
    val x5947 = withCtrl(x5950) { x5946 } // VectorApply(x5946,0)
    val x5948 = withCtrl(x5950) { OpDef(op=FixAdd, inputs=List(x5947, x5945)).name("x5948").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x5947,x5945)
    val x5949 = withCtrl(x5950) { StoreBanks(List(List(x5453_d0_b0)), List(b2552, b2560), x5948).name("x5949").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x5453,List(List(b2552, b2560)),List(x5948),List(x5938))
    val x5951 = withCtrl(x5968) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5951").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5952 = withCtrl(x5968) { CounterChain(List(x5951)).name("x5952").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x5951))
    val x5967 = withCtrl(x5968) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5952).name("x5967").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2555, b1969),x5952,Block(Const(())),List(List(b2577)),List(List(b2578)))
    val b2577 = withCtrl(x5967) { CounterIter(x5951, None).name("b2577") } // b2577
    val b2578 = withCtrl(x5967) { Const(true).name("b2578") } // b2578
    val x5953 = withCtrl(x5967) { ReadMem(x5457_d1).name("x5953").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5457)
    val x5954 = withCtrl(x5967) { OpDef(op=BitAnd, inputs=List(b2578, b2555)).name("x5954").srcCtx("UnrollingBase.scala:28:66") } // And(b2578,b2555)
    val x5955 = withCtrl(x5967) { OpDef(op=BitAnd, inputs=List(x5954, b1969)).name("x5955").srcCtx("UnrollingBase.scala:28:66") } // And(x5954,b1969)
    val x5956 = withCtrl(x5967) { LoadBanks(List(x5445_d0_b1), List(x5953, b2553, b2577)).name("x5956").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x5445,List(x5953, b2553, b2577),x5955)
    val x5957 = withCtrl(x5967) { LoadBanks(List(x5446_d0_b1), List(x5953, b2553, b2577)).name("x5957").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x5446,List(x5953, b2553, b2577),x5955)
    val x5958 = withCtrl(x5967) { LoadBanks(List(x5447_d0_b1), List(x5953, b2553, b2577)).name("x5958").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x5447,List(x5953, b2553, b2577),x5955)
    val x5959 = withCtrl(x5967) { ReadMem(x5456_d1).name("x5959").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5456)
    val x5960 = withCtrl(x5967) { OpDef(op=MuxOp, inputs=List(x5959, x5958, x5957)).name("x5960").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x5959,x5958,x5957)
    val x5961 = withCtrl(x5967) { ReadMem(x5455_d1).name("x5961").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5455)
    val x5962 = withCtrl(x5967) { OpDef(op=MuxOp, inputs=List(x5961, x5956, x5960)).name("x5962").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x5961,x5956,x5960)
    val x5963 = withCtrl(x5967) { LoadBanks(List(x5452_d0_b1), List(b2553, b2577)).name("x5963").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x5452,List(List(b2553, b2577)),List(x5955))
    val x5964 = withCtrl(x5967) { x5963 } // VectorApply(x5963,0)
    val x5965 = withCtrl(x5967) { OpDef(op=FixAdd, inputs=List(x5964, x5962)).name("x5965").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x5964,x5962)
    val x5966 = withCtrl(x5967) { StoreBanks(List(List(x5453_d0_b1)), List(b2553, b2577), x5965).name("x5966").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x5453,List(List(b2553, b2577)),List(x5965),List(x5955))
    val x5970 = withCtrl(x6081) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x5970").srcCtx("GateMetaPipe.scala:143:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x5971 = withCtrl(x6081) { CounterChain(List(x5970)).name("x5971").srcCtx("GateMetaPipe.scala:143:27") } // CounterChainNew(List(x5970))
    val x6080 = withCtrl(x6081) { LoopController(style=MetaPipe, level=OuterControl, cchain=x5971).name("x6080").srcCtx("GateMetaPipe.scala:143:27") } // UnrolledForeach(List(b1969),x5971,Block(Const(())),List(List(b2598)),List(List(b2599)))
    val b2598 = withCtrl(x6080) { CounterIter(x5970, Some(0)).name("b2598") } // b2598
    val b2599 = withCtrl(x6080) { Const(true).name("b2599") } // b2599
    val x5972 = withCtrl(x6080) { FIFO(size=64).name("x5972").srcCtx("GateMetaPipe.scala:144:25:sigQ") } // x5972 = FIFONew(Const(64))
    isAccum(x5972) = false
    bufferDepthOf(x5972) = 2
    val x5973 = withCtrl(x6080) { FIFO(size=64).name("x5973").srcCtx("GateMetaPipe.scala:145:26:sigQQ") } // x5973 = FIFONew(Const(64))
    isAccum(x5973) = false
    bufferDepthOf(x5973) = 2
    val x5974 = withCtrl(x6080) { FIFO(size=64).name("x5974").srcCtx("GateMetaPipe.scala:146:31:sigEleMuxQ") } // x5974 = FIFONew(Const(64))
    isAccum(x5974) = false
    bufferDepthOf(x5974) = 2
    val x5975 = withCtrl(x6080) { FIFO(size=64).name("x5975").srcCtx("GateMetaPipe.scala:147:27:tanhQ") } // x5975 = FIFONew(Const(64))
    isAccum(x5975) = false
    bufferDepthOf(x5975) = 2
    val x5976 = withCtrl(x6080) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x5976").srcCtx("GateMetaPipe.scala:149:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x5977 = withCtrl(x6080) { CounterChain(List(x5976)).name("x5977").srcCtx("GateMetaPipe.scala:149:48") } // CounterChainNew(List(x5976))
    val x5999 = withCtrl(x6080) { LoopController(style=InnerPipe, level=InnerControl, cchain=x5977).name("x5999").srcCtx("GateMetaPipe.scala:149:48") } // UnrolledForeach(List(b2599, b1969),x5977,Block(Const(())),List(List(b2606)),List(List(b2607)))
    val b2606 = withCtrl(x5999) { CounterIter(x5976, None).name("b2606") } // b2606
    val b2607 = withCtrl(x5999) { Const(true).name("b2607") } // b2607
    val x5978 = withCtrl(x5999) { OpDef(op=BitAnd, inputs=List(b2607, b2599)).name("x5978").srcCtx("UnrollingBase.scala:28:66") } // And(b2607,b2599)
    val x5979 = withCtrl(x5999) { OpDef(op=BitAnd, inputs=List(x5978, b1969)).name("x5979").srcCtx("UnrollingBase.scala:28:66") } // And(x5978,b1969)
    val x5980 = withCtrl(x5999) { LoadBanks(List(x5453_d0_b0, x5453_d0_b1), List(b2598, b2606)).name("x5980").srcCtx("GateMetaPipe.scala:150:27:pEle") } // ParSRAMLoad(x5453,List(List(b2598, b2606)),List(x5979))
    val x5981 = withCtrl(x5999) { x5980 } // VectorApply(x5980,0)
    val x5982_d0_b0 = withCtrl(x5999) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x5982_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x5982 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x5982_d0_b0) = false
    bufferDepthOf(x5982_d0_b0) = 1
    staticDimsOf(x5982_d0_b0) = List(1024)
    val x5983 = withCtrl(x5999) { OpDef(op=FixSub, inputs=List(x5981, Const(-8.0))).name("x5983").srcCtx("NonLinearity.scala:44:22") } // FixSub(x5981,Const(-8))
    val x5984 = withCtrl(x5999) { OpDef(op=FixSla, inputs=List(x5983, Const(6))).name("x5984").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x5983,Const(6))
    // x5985 = FixConvert(x5984,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x5985 = withCtrl(x5999) { OpDef(op=FixSra, inputs=List(x5984, Const("24"))).name("x5985").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x5984,TRUE,_32,_0)
    // }
    val x5986 = withCtrl(x5999) { LoadBanks(List(x5982_d0_b0), List(x5985)).name("x5986").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x5982,List(x5985),x5979)
    val x5987_d0_b0 = withCtrl(x5999) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x5987_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x5987 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x5987_d0_b0) = false
    bufferDepthOf(x5987_d0_b0) = 1
    staticDimsOf(x5987_d0_b0) = List(1024)
    val x5988 = withCtrl(x5999) { LoadBanks(List(x5987_d0_b0), List(x5985)).name("x5988").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x5987,List(x5985),x5979)
    val x5989 = withCtrl(x5999) { OpDef(op=FixLt, inputs=List(Const(8.0), x5981)).name("x5989").srcCtx("GateMetaPipe.scala:154:27:isHigh") } // FixLt(Const(8),x5981)
    val x5990 = withCtrl(x5999) { OpDef(op=FixLt, inputs=List(x5981, Const(-8.0))).name("x5990").srcCtx("GateMetaPipe.scala:155:26:isLow") } // FixLt(x5981,Const(-8))
    val x5991 = withCtrl(x5999) { OpDef(op=MuxOp, inputs=List(x5990, Const(0.0), x5986)).name("x5991").srcCtx("GateMetaPipe.scala:157:46") } // Mux(x5990,Const(0),x5986)
    val x5992 = withCtrl(x5999) { OpDef(op=MuxOp, inputs=List(x5989, Const(1.0), x5991)).name("x5992").srcCtx("GateMetaPipe.scala:157:25:sigEle") } // Mux(x5989,Const(1),x5991)
    val x5993 = withCtrl(x5999) { OpDef(op=MuxOp, inputs=List(x5990, Const(-1.0), x5988)).name("x5993").srcCtx("GateMetaPipe.scala:158:47") } // Mux(x5990,Const(-1),x5988)
    val x5994 = withCtrl(x5999) { OpDef(op=MuxOp, inputs=List(x5989, Const(1.0), x5993)).name("x5994").srcCtx("GateMetaPipe.scala:158:26:tanhEle") } // Mux(x5989,Const(1),x5993)
    val x5995_x5972 = withCtrl(x5999) { WriteMem(x5972, x5992).name("x5995_x5972").srcCtx("GateMetaPipe.scala:160:17") } // ParFIFOEnq(x5972,List(x5992),List(x5979))
    val x5996_x5973 = withCtrl(x5999) { WriteMem(x5973, x5992).name("x5996_x5973").srcCtx("GateMetaPipe.scala:161:18") } // ParFIFOEnq(x5973,List(x5992),List(x5979))
    val x5997_x5974 = withCtrl(x5999) { WriteMem(x5974, x5992).name("x5997_x5974").srcCtx("GateMetaPipe.scala:162:23") } // ParFIFOEnq(x5974,List(x5992),List(x5979))
    val x5998_x5975 = withCtrl(x5999) { WriteMem(x5975, x5994).name("x5998_x5975").srcCtx("GateMetaPipe.scala:164:18") } // ParFIFOEnq(x5975,List(x5994),List(x5979))
    val x6079 = withCtrl(x6080) { UnitController(style=SeqPipe, level=OuterControl).name("x6079").srcCtx("GateMetaPipe.scala:167:12") } // UnitPipe(List(b2599, b1969),Block(Const(())))
    val x6000 = withCtrl(x6079) { FIFO(size=64).name("x6000").srcCtx("GateMetaPipe.scala:169:29:cLastQ") } // x6000 = FIFONew(Const(64))
    isAccum(x6000) = false
    bufferDepthOf(x6000) = 1
    val x6001 = withCtrl(x6079) { FIFO(size=64).name("x6001").srcCtx("GateMetaPipe.scala:170:35:cNewMultAddQ") } // x6001 = FIFONew(Const(64))
    isAccum(x6001) = false
    bufferDepthOf(x6001) = 1
    val x6002 = withCtrl(x6079) { FIFO(size=64).name("x6002").srcCtx("GateMetaPipe.scala:171:36:cNewMultAddQQ") } // x6002 = FIFONew(Const(64))
    isAccum(x6002) = false
    bufferDepthOf(x6002) = 1
    val x6003 = withCtrl(x6079) { FIFO(size=64).name("x6003").srcCtx("GateMetaPipe.scala:172:32:cNewMultQ") } // x6003 = FIFONew(Const(64))
    isAccum(x6003) = false
    bufferDepthOf(x6003) = 1
    val x6004 = withCtrl(x6079) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6004").srcCtx("GateMetaPipe.scala:174:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6005 = withCtrl(x6079) { CounterChain(List(x6004)).name("x6005").srcCtx("GateMetaPipe.scala:174:50") } // CounterChainNew(List(x6004))
    val x6025 = withCtrl(x6079) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6005).name("x6025").srcCtx("GateMetaPipe.scala:174:50") } // UnrolledForeach(List(b2599, b1969),x6005,Block(Const(())),List(List(b2636)),List(List(b2637)))
    val b2636 = withCtrl(x6025) { CounterIter(x6004, None).name("b2636") } // b2636
    val b2637 = withCtrl(x6025) { Const(true).name("b2637") } // b2637
    val x6006 = withCtrl(x6025) { OpDef(op=BitAnd, inputs=List(b2637, b2599)).name("x6006").srcCtx("UnrollingBase.scala:28:66") } // And(b2637,b2599)
    val x6007 = withCtrl(x6025) { OpDef(op=BitAnd, inputs=List(x6006, b1969)).name("x6007").srcCtx("UnrollingBase.scala:28:66") } // And(x6006,b1969)
    val x6008 = withCtrl(x6025) { ReadMem(x5972).name("x6008").srcCtx("GateMetaPipe.scala:175:32:sigEle") } // ParFIFODeq(x5972,List(x6007))
    val x6009 = withCtrl(x6025) { x6008 } // VectorApply(x6008,0)
    val x6010 = withCtrl(x6025) { ReadMem(x5975).name("x6010").srcCtx("GateMetaPipe.scala:176:34:tanhEle") } // ParFIFODeq(x5975,List(x6007))
    val x6011 = withCtrl(x6025) { x6010 } // VectorApply(x6010,0)
    val x6012 = withCtrl(x6025) { OpDef(op=FixEql, inputs=List(b2598, Const(0))).name("x6012").srcCtx("package.scala:110:40") } // FixEql(b2598,Const(0))
    val x6013 = withCtrl(x6025) { ReadMem(x5454_d0).name("x6013").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x5454)
    val x6014 = withCtrl(x6025) { OpDef(op=BitAnd, inputs=List(x6013, x6012)).name("x6014").srcCtx("GateMetaPipe.scala:177:36:isInitC") } // And(x6013,x6012)
    val x6015 = withCtrl(x6025) { LoadBanks(List(x5448_d0_b0), List(b2636)).name("x6015").srcCtx("GateMetaPipe.scala:179:35") } // ParSRAMLoad(x5448,List(List(b2636)),List(x6007))
    val x6016 = withCtrl(x6025) { x6015 } // VectorApply(x6015,0)
    val x6017 = withCtrl(x6025) { OpDef(op=MuxOp, inputs=List(x6014, Const(0.0), x6016)).name("x6017").srcCtx("GateMetaPipe.scala:178:26:cLast") } // Mux(x6014,Const(0),x6016)
    val x6018 = withCtrl(x6025) { OpDef(op=FixMul, inputs=List(x6017, x6011)).name("x6018").srcCtx("GateMetaPipe.scala:181:32:cNewMult") } // FixMul(x6017,x6011)
    val x6019 = withCtrl(x6025) { OpDef(op=FixMul, inputs=List(x6009, x6017)).name("x6019").srcCtx("GateMetaPipe.scala:182:36") } // FixMul(x6009,x6017)
    val x6020 = withCtrl(x6025) { OpDef(op=FixAdd, inputs=List(x6019, x6018)).name("x6020").srcCtx("GateMetaPipe.scala:182:44:cNewMultAdd") } // FixAdd(x6019,x6018)
    val x6021_x6003 = withCtrl(x6025) { WriteMem(x6003, x6018).name("x6021_x6003").srcCtx("GateMetaPipe.scala:184:24") } // ParFIFOEnq(x6003,List(x6018),List(x6007))
    val x6022_x6001 = withCtrl(x6025) { WriteMem(x6001, x6020).name("x6022_x6001").srcCtx("GateMetaPipe.scala:185:27") } // ParFIFOEnq(x6001,List(x6020),List(x6007))
    def split2 = {
    val x6023_x6002 = withCtrl(x6025) { WriteMem(x6002, x6020).name("x6023_x6002").srcCtx("GateMetaPipe.scala:186:28") } // ParFIFOEnq(x6002,List(x6020),List(x6007))
    val x6024_x6000 = withCtrl(x6025) { WriteMem(x6000, x6017).name("x6024_x6000").srcCtx("GateMetaPipe.scala:187:21") } // ParFIFOEnq(x6000,List(x6017),List(x6007))
    val x6026 = withCtrl(x6079) { FIFO(size=64).name("x6026").srcCtx("GateMetaPipe.scala:190:31:cUpdateQ") } // x6026 = FIFONew(Const(64))
    isAccum(x6026) = false
    bufferDepthOf(x6026) = 1
    val x6027 = withCtrl(x6079) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6027").srcCtx("GateMetaPipe.scala:191:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6028 = withCtrl(x6079) { CounterChain(List(x6027)).name("x6028").srcCtx("GateMetaPipe.scala:191:50") } // CounterChainNew(List(x6027))
    val x6046 = withCtrl(x6079) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6028).name("x6046").srcCtx("GateMetaPipe.scala:191:50") } // UnrolledForeach(List(b2599, b1969),x6028,Block(Const(())),List(List(b2661)),List(List(b2662)))
    val b2661 = withCtrl(x6046) { CounterIter(x6027, None).name("b2661") } // b2661
    val b2662 = withCtrl(x6046) { Const(true).name("b2662") } // b2662
    val x6029 = withCtrl(x6046) { OpDef(op=BitAnd, inputs=List(b2662, b2599)).name("x6029").srcCtx("UnrollingBase.scala:28:66") } // And(b2662,b2599)
    val x6030 = withCtrl(x6046) { OpDef(op=BitAnd, inputs=List(x6029, b1969)).name("x6030").srcCtx("UnrollingBase.scala:28:66") } // And(x6029,b1969)
    val x6031 = withCtrl(x6046) { ReadMem(x6003).name("x6031").srcCtx("GateMetaPipe.scala:192:39:cNewMult") } // ParFIFODeq(x6003,List(x6030))
    val x6032 = withCtrl(x6046) { x6031 } // VectorApply(x6031,0)
    val x6033 = withCtrl(x6046) { ReadMem(x5974).name("x6033").srcCtx("GateMetaPipe.scala:193:38:sigEle") } // ParFIFODeq(x5974,List(x6030))
    val x6034 = withCtrl(x6046) { x6033 } // VectorApply(x6033,0)
    val x6035 = withCtrl(x6046) { ReadMem(x6001).name("x6035").srcCtx("GateMetaPipe.scala:194:45:cNewMultAdd") } // ParFIFODeq(x6001,List(x6030))
    val x6036 = withCtrl(x6046) { x6035 } // VectorApply(x6035,0)
    val x6037 = withCtrl(x6046) { ReadMem(x6000).name("x6037").srcCtx("GateMetaPipe.scala:195:33:cLast") } // ParFIFODeq(x6000,List(x6030))
    val x6038 = withCtrl(x6046) { x6037 } // VectorApply(x6037,0)
    val x6039 = withCtrl(x6046) { OpDef(op=FixEql, inputs=List(b2598, Const(0))).name("x6039").srcCtx("package.scala:110:40") } // FixEql(b2598,Const(0))
    val x6040 = withCtrl(x6046) { OpDef(op=FixEql, inputs=List(b2598, Const(1))).name("x6040").srcCtx("package.scala:113:40") } // FixEql(b2598,Const(1))
    val x6041 = withCtrl(x6046) { OpDef(op=FixEql, inputs=List(b2598, Const(2))).name("x6041").srcCtx("package.scala:116:40") } // FixEql(b2598,Const(2))
    val x6042 = withCtrl(x6046) { OpDef(op=MuxOp, inputs=List(x6041, x6036, x6038)).name("x6042").srcCtx("GateMetaPipe.scala:200:40") } // Mux(x6041,x6036,x6038)
    val x6043 = withCtrl(x6046) { OpDef(op=MuxOp, inputs=List(x6040, x6032, x6042)).name("x6043").srcCtx("GateMetaPipe.scala:199:36") } // Mux(x6040,x6032,x6042)
    val x6044 = withCtrl(x6046) { OpDef(op=MuxOp, inputs=List(x6039, x6034, x6043)).name("x6044").srcCtx("GateMetaPipe.scala:198:28:cUpdate") } // Mux(x6039,x6034,x6043)
    val x6045_x6026 = withCtrl(x6046) { WriteMem(x6026, x6044).name("x6045_x6026").srcCtx("GateMetaPipe.scala:206:23") } // ParFIFOEnq(x6026,List(x6044),List(x6030))
    val x6047 = withCtrl(x6079) { FIFO(size=64).name("x6047").srcCtx("GateMetaPipe.scala:209:31:hLinearQ") } // x6047 = FIFONew(Const(64))
    isAccum(x6047) = false
    bufferDepthOf(x6047) = 1
    val x6048 = withCtrl(x6079) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6048").srcCtx("GateMetaPipe.scala:210:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6049 = withCtrl(x6079) { CounterChain(List(x6048)).name("x6049").srcCtx("GateMetaPipe.scala:210:50") } // CounterChainNew(List(x6048))
    val x6064 = withCtrl(x6079) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6049).name("x6064").srcCtx("GateMetaPipe.scala:210:50") } // UnrolledForeach(List(b2599, b1969),x6049,Block(Const(())),List(List(b2684)),List(List(b2685)))
    val b2684 = withCtrl(x6064) { CounterIter(x6048, None).name("b2684") } // b2684
    val b2685 = withCtrl(x6064) { Const(true).name("b2685") } // b2685
    val x6050 = withCtrl(x6064) { OpDef(op=BitAnd, inputs=List(b2685, b2599)).name("x6050").srcCtx("UnrollingBase.scala:28:66") } // And(b2685,b2599)
    val x6051 = withCtrl(x6064) { OpDef(op=BitAnd, inputs=List(x6050, b1969)).name("x6051").srcCtx("UnrollingBase.scala:28:66") } // And(x6050,b1969)
    val x6052 = withCtrl(x6064) { ReadMem(x6002).name("x6052").srcCtx("GateMetaPipe.scala:211:46:cNewMultAdd") } // ParFIFODeq(x6002,List(x6051))
    val x6053 = withCtrl(x6064) { x6052 } // VectorApply(x6052,0)
    val x6054_d0_b0 = withCtrl(x6064) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x6054_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x6054 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x6054_d0_b0) = false
    bufferDepthOf(x6054_d0_b0) = 1
    staticDimsOf(x6054_d0_b0) = List(1024)
    val x6055 = withCtrl(x6064) { OpDef(op=FixSub, inputs=List(x6053, Const(-8.0))).name("x6055").srcCtx("NonLinearity.scala:44:22") } // FixSub(x6053,Const(-8))
    val x6056 = withCtrl(x6064) { OpDef(op=FixSla, inputs=List(x6055, Const(6))).name("x6056").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x6055,Const(6))
    // x6057 = FixConvert(x6056,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x6057 = withCtrl(x6064) { OpDef(op=FixSra, inputs=List(x6056, Const("24"))).name("x6057").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x6056,TRUE,_32,_0)
    // }
    val x6058 = withCtrl(x6064) { LoadBanks(List(x6054_d0_b0), List(x6057)).name("x6058").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x6054,List(x6057),x6051)
    val x6059 = withCtrl(x6064) { OpDef(op=FixLt, inputs=List(Const(8.0), x6053)).name("x6059").srcCtx("GateMetaPipe.scala:214:36:isHigh") } // FixLt(Const(8),x6053)
    val x6060 = withCtrl(x6064) { OpDef(op=FixLt, inputs=List(x6053, Const(-8.0))).name("x6060").srcCtx("GateMetaPipe.scala:215:35:isLow") } // FixLt(x6053,Const(-8))
    val x6061 = withCtrl(x6064) { OpDef(op=MuxOp, inputs=List(x6060, Const(-1.0), x6058)).name("x6061").srcCtx("GateMetaPipe.scala:217:33") } // Mux(x6060,Const(-1),x6058)
    val x6062 = withCtrl(x6064) { OpDef(op=MuxOp, inputs=List(x6059, Const(1.0), x6061)).name("x6062").srcCtx("GateMetaPipe.scala:216:28:hLinear") } // Mux(x6059,Const(1),x6061)
    val x6063_x6047 = withCtrl(x6064) { WriteMem(x6047, x6062).name("x6063_x6047").srcCtx("GateMetaPipe.scala:222:23") } // ParFIFOEnq(x6047,List(x6062),List(x6051))
    val x6065 = withCtrl(x6079) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6065").srcCtx("GateMetaPipe.scala:225:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6066 = withCtrl(x6079) { CounterChain(List(x6065)).name("x6066").srcCtx("GateMetaPipe.scala:225:50") } // CounterChainNew(List(x6065))
    val x6078 = withCtrl(x6079) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6066).name("x6078").srcCtx("GateMetaPipe.scala:225:50") } // UnrolledForeach(List(b2599, b1969),x6066,Block(Const(())),List(List(b2703)),List(List(b2704)))
    val b2703 = withCtrl(x6078) { CounterIter(x6065, None).name("b2703") } // b2703
    val b2704 = withCtrl(x6078) { Const(true).name("b2704") } // b2704
    val x6067 = withCtrl(x6078) { OpDef(op=BitAnd, inputs=List(b2704, b2599)).name("x6067").srcCtx("UnrollingBase.scala:28:66") } // And(b2704,b2599)
    val x6068 = withCtrl(x6078) { OpDef(op=BitAnd, inputs=List(x6067, b1969)).name("x6068").srcCtx("UnrollingBase.scala:28:66") } // And(x6067,b1969)
    val x6069 = withCtrl(x6078) { ReadMem(x6026).name("x6069").srcCtx("GateMetaPipe.scala:229:34:cNew") } // ParFIFODeq(x6026,List(x6068))
    val x6070 = withCtrl(x6078) { x6069 } // VectorApply(x6069,0)
    val x6071 = withCtrl(x6078) { ReadMem(x6047).name("x6071").srcCtx("GateMetaPipe.scala:230:37:hLinear") } // ParFIFODeq(x6047,List(x6068))
    val x6072 = withCtrl(x6078) { x6071 } // VectorApply(x6071,0)
    val x6073 = withCtrl(x6078) { ReadMem(x5973).name("x6073").srcCtx("GateMetaPipe.scala:231:33:sigEle") } // ParFIFODeq(x5973,List(x6068))
    val x6074 = withCtrl(x6078) { x6073 } // VectorApply(x6073,0)
    val x6075 = withCtrl(x6078) { OpDef(op=FixMul, inputs=List(x6072, x6074)).name("x6075").srcCtx("GateMetaPipe.scala:232:30:hNew") } // FixMul(x6072,x6074)
    val x6076 = withCtrl(x6078) { StoreBanks(List(List(x5449_d0_b0), List(x5449_d1_b0)), List(b2703), x6075).name("x6076").srcCtx("GateMetaPipe.scala:234:29") } // ParSRAMStore(x5449,List(List(b2703)),List(x6075),List(x6068))
    val x6077 = withCtrl(x6078) { StoreBanks(List(List(x5448_d0_b0)), List(b2703), x6070).name("x6077").srcCtx("GateMetaPipe.scala:235:29") } // ParSRAMStore(x5448,List(List(b2703)),List(x6070),List(x6068))
    val x6103 = withCtrl(x6104) { UnitController(style=StreamPipe, level=OuterControl).name("x6103").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b6115 = withCtrl(x6103) { StreamOut(field="offset").name("b6115").srcCtx("DeepBenchLSTM256.scala:119:39") } // x6082 = StreamOutNew(BurstCmdBus)
    isAccum(b6115) = false
    bufferDepthOf(b6115) = 1
    val b6116 = withCtrl(x6103) { StreamOut(field="size").name("b6116").srcCtx("DeepBenchLSTM256.scala:119:39") } // x6082 = StreamOutNew(BurstCmdBus)
    isAccum(b6116) = false
    bufferDepthOf(b6116) = 1
    val x6083 = withCtrl(x6103) { StreamOut(field="data").name("x6083").srcCtx("DeepBenchLSTM256.scala:119:39") } // x6083 = StreamOutNew(BurstFullDataBus())
    isAccum(x6083) = false
    bufferDepthOf(x6083) = 1
    val x6084 = withCtrl(x6103) { StreamIn(field="ack").name("x6084").srcCtx("DeepBenchLSTM256.scala:119:39") } // x6084 = StreamInNew(BurstAckBus)
    isAccum(x6084) = false
    bufferDepthOf(x6084) = 1
    val x6092 = withCtrl(x6103) { UnitController(style=SeqPipe, level=InnerControl).name("x6092").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(x6091))
    val x6085 = withCtrl(x6092) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6086 = withCtrl(x6092) { OpDef(op=FixSla, inputs=List(x6085, Const(2))).name("x6086").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixLsh(x6085,Const(2))
    val x6087 = withCtrl(x6092) { x6086 } // FixConvert(x6086,TRUE,_64,_0)
    val x6088 = withCtrl(x6092) { DramAddress(x5443).name("x6088").srcCtx("DeepBenchLSTM256.scala:119:39") } // GetDRAMAddress(x5443)
    val x6090_x6089 = withCtrl(x6092) { OpDef(op=FixAdd, inputs=List(x6087, x6088)).name("x6090_x6089").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixAdd(x6087,x6088)
    // x6090 = SimpleStruct(ArrayBuffer((offset,x6089), (size,Const(1024)), (isLoad,Const(false))))
    val x6091_b6117_b6115 = withCtrl(x6092) { WriteMem(b6115, x6090_x6089).name("x6091_b6117_b6115").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x6082,x6090,Const(true))
    val x6091_b6118_b6116 = withCtrl(x6092) { WriteMem(b6116, Const(1024)).name("x6091_b6118_b6116").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x6082,x6090,Const(true))
    val x6093 = withCtrl(x6103) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6093").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6094 = withCtrl(x6103) { CounterChain(List(x6093)).name("x6094").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterChainNew(List(x6093))
    val x6099 = withCtrl(x6103) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6094).name("x6099").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnrolledForeach(List(Const(true)),x6094,Block(Const(())),List(List(b2733)),List(List(b2734)))
    val b2733 = withCtrl(x6099) { CounterIter(x6093, None).name("b2733") } // b2733
    val b2734 = withCtrl(x6099) { Const(true).name("b2734") } // b2734
    val x6095 = withCtrl(x6099) { LoadBanks(List(x5449_d0_b0), List(b2733)).name("x6095").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParSRAMLoad(x5449,List(List(b2733)),List(b2734))
    val x6097_x6096 = withCtrl(x6099) { x6095 } // VectorApply(x6095,0)
    // x6097 = SimpleStruct(ArrayBuffer((_1,x6096), (_2,Const(true))))
    val x6098_x6098_x6083 = withCtrl(x6099) { WriteMem(x6083, x6097_x6096).name("x6098_x6098_x6083").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParStreamWrite(x6083,List(x6097),List(b2734))
    val x6100 = withCtrl(x6103) { FringeDenseStore(dram=List(x5443), cmdStream=List(b6115, b6116), dataStream=List(x6083), ackStream=List(x6084)).name("x6100").srcCtx("DeepBenchLSTM256.scala:119:39") } // FringeDenseStore(x5443,x6082,x6083,x6084)
    val x6102 = withCtrl(x6103) { UnitController(style=SeqPipe, level=InnerControl).name("x6102").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6101_x6101 = withCtrl(x6102) { ReadMem(x6084).name("x6101_x6101").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamRead(x6084,Const(true))
    }; split2
    }; split1
    
  }
}
