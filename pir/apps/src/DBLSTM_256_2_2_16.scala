import pir._
import pir.node._
import arch._
import prism.enums._

object DBLSTM_256_2_2_16 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6387 = withCtrl(design.top.topController) { DRAM(dims=List(Const(256))).name("x6387").srcCtx("DeepBenchLSTM256.scala:57:23:dout") } // x6387 = DRAMNew(ArrayBuffer(Const(256)),Const(0))
    val x7224 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x7224").srcCtx("DeepBenchLSTM256.scala:59:11") } // Hwblock(Block(Const(())),false)
    val x6388_d0_b0 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b0").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b0) = false
    bufferDepthOf(x6388_d0_b0) = 1
    staticDimsOf(x6388_d0_b0) = List(256, 4, 256)
    val x6388_d0_b1 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b1").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b1) = false
    bufferDepthOf(x6388_d0_b1) = 1
    staticDimsOf(x6388_d0_b1) = List(256, 4, 256)
    val x6388_d0_b2 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b2").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b2) = false
    bufferDepthOf(x6388_d0_b2) = 1
    staticDimsOf(x6388_d0_b2) = List(256, 4, 256)
    val x6388_d0_b3 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b3").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b3) = false
    bufferDepthOf(x6388_d0_b3) = 1
    staticDimsOf(x6388_d0_b3) = List(256, 4, 256)
    val x6388_d0_b4 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b4").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b4) = false
    bufferDepthOf(x6388_d0_b4) = 1
    staticDimsOf(x6388_d0_b4) = List(256, 4, 256)
    val x6388_d0_b5 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b5").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b5) = false
    bufferDepthOf(x6388_d0_b5) = 1
    staticDimsOf(x6388_d0_b5) = List(256, 4, 256)
    val x6388_d0_b6 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b6").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b6) = false
    bufferDepthOf(x6388_d0_b6) = 1
    staticDimsOf(x6388_d0_b6) = List(256, 4, 256)
    val x6388_d0_b7 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b7").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b7) = false
    bufferDepthOf(x6388_d0_b7) = 1
    staticDimsOf(x6388_d0_b7) = List(256, 4, 256)
    val x6388_d0_b8 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b8").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b8) = false
    bufferDepthOf(x6388_d0_b8) = 1
    staticDimsOf(x6388_d0_b8) = List(256, 4, 256)
    val x6388_d0_b9 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b9").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b9) = false
    bufferDepthOf(x6388_d0_b9) = 1
    staticDimsOf(x6388_d0_b9) = List(256, 4, 256)
    val x6388_d0_b10 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b10").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b10) = false
    bufferDepthOf(x6388_d0_b10) = 1
    staticDimsOf(x6388_d0_b10) = List(256, 4, 256)
    val x6388_d0_b11 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b11").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b11) = false
    bufferDepthOf(x6388_d0_b11) = 1
    staticDimsOf(x6388_d0_b11) = List(256, 4, 256)
    val x6388_d0_b12 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b12").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b12) = false
    bufferDepthOf(x6388_d0_b12) = 1
    staticDimsOf(x6388_d0_b12) = List(256, 4, 256)
    val x6388_d0_b13 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b13").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b13) = false
    bufferDepthOf(x6388_d0_b13) = 1
    staticDimsOf(x6388_d0_b13) = List(256, 4, 256)
    val x6388_d0_b14 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b14").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b14) = false
    bufferDepthOf(x6388_d0_b14) = 1
    staticDimsOf(x6388_d0_b14) = List(256, 4, 256)
    val x6388_d0_b15 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b15").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b15) = false
    bufferDepthOf(x6388_d0_b15) = 1
    staticDimsOf(x6388_d0_b15) = List(256, 4, 256)
    val x6388_d0_b16 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b16").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b16) = false
    bufferDepthOf(x6388_d0_b16) = 1
    staticDimsOf(x6388_d0_b16) = List(256, 4, 256)
    val x6388_d0_b17 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b17").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b17) = false
    bufferDepthOf(x6388_d0_b17) = 1
    staticDimsOf(x6388_d0_b17) = List(256, 4, 256)
    val x6388_d0_b18 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b18").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b18) = false
    bufferDepthOf(x6388_d0_b18) = 1
    staticDimsOf(x6388_d0_b18) = List(256, 4, 256)
    val x6388_d0_b19 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b19").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b19) = false
    bufferDepthOf(x6388_d0_b19) = 1
    staticDimsOf(x6388_d0_b19) = List(256, 4, 256)
    val x6388_d0_b20 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b20").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b20) = false
    bufferDepthOf(x6388_d0_b20) = 1
    staticDimsOf(x6388_d0_b20) = List(256, 4, 256)
    val x6388_d0_b21 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b21").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b21) = false
    bufferDepthOf(x6388_d0_b21) = 1
    staticDimsOf(x6388_d0_b21) = List(256, 4, 256)
    val x6388_d0_b22 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b22").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b22) = false
    bufferDepthOf(x6388_d0_b22) = 1
    staticDimsOf(x6388_d0_b22) = List(256, 4, 256)
    val x6388_d0_b23 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b23").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b23) = false
    bufferDepthOf(x6388_d0_b23) = 1
    staticDimsOf(x6388_d0_b23) = List(256, 4, 256)
    val x6388_d0_b24 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b24").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b24) = false
    bufferDepthOf(x6388_d0_b24) = 1
    staticDimsOf(x6388_d0_b24) = List(256, 4, 256)
    val x6388_d0_b25 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b25").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b25) = false
    bufferDepthOf(x6388_d0_b25) = 1
    staticDimsOf(x6388_d0_b25) = List(256, 4, 256)
    val x6388_d0_b26 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b26").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b26) = false
    bufferDepthOf(x6388_d0_b26) = 1
    staticDimsOf(x6388_d0_b26) = List(256, 4, 256)
    val x6388_d0_b27 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b27").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b27) = false
    bufferDepthOf(x6388_d0_b27) = 1
    staticDimsOf(x6388_d0_b27) = List(256, 4, 256)
    val x6388_d0_b28 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b28").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b28) = false
    bufferDepthOf(x6388_d0_b28) = 1
    staticDimsOf(x6388_d0_b28) = List(256, 4, 256)
    val x6388_d0_b29 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b29").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b29) = false
    bufferDepthOf(x6388_d0_b29) = 1
    staticDimsOf(x6388_d0_b29) = List(256, 4, 256)
    val x6388_d0_b30 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b30").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b30) = false
    bufferDepthOf(x6388_d0_b30) = 1
    staticDimsOf(x6388_d0_b30) = List(256, 4, 256)
    val x6388_d0_b31 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6388_d0_b31").srcCtx("DataGenerator.scala:248:40:wh") } // x6388 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x6388_d0_b31) = false
    bufferDepthOf(x6388_d0_b31) = 1
    staticDimsOf(x6388_d0_b31) = List(256, 4, 256)
    val x6389_d0_b0 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6389_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt0") } // x6389 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x6389_d0_b0) = false
    bufferDepthOf(x6389_d0_b0) = 1
    staticDimsOf(x6389_d0_b0) = List(64, 4, 256)
    val x6389_d0_b1 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6389_d0_b1").srcCtx("DataGenerator.scala:248:40:wxt0") } // x6389 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x6389_d0_b1) = false
    bufferDepthOf(x6389_d0_b1) = 1
    staticDimsOf(x6389_d0_b1) = List(64, 4, 256)
    val x6390_d0_b0 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6390_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt1") } // x6390 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x6390_d0_b0) = false
    bufferDepthOf(x6390_d0_b0) = 1
    staticDimsOf(x6390_d0_b0) = List(64, 4, 256)
    val x6390_d0_b1 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6390_d0_b1").srcCtx("DataGenerator.scala:248:40:wxt1") } // x6390 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x6390_d0_b1) = false
    bufferDepthOf(x6390_d0_b1) = 1
    staticDimsOf(x6390_d0_b1) = List(64, 4, 256)
    val x6391_d0_b0 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6391_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt2") } // x6391 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x6391_d0_b0) = false
    bufferDepthOf(x6391_d0_b0) = 1
    staticDimsOf(x6391_d0_b0) = List(64, 4, 256)
    val x6391_d0_b1 = withCtrl(x7224) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x6391_d0_b1").srcCtx("DataGenerator.scala:248:40:wxt2") } // x6391 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x6391_d0_b1) = false
    bufferDepthOf(x6391_d0_b1) = 1
    staticDimsOf(x6391_d0_b1) = List(64, 4, 256)
    val x6392_d0_b0 = withCtrl(x7224) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6392_d0_b0").srcCtx("DeepBenchLSTM256.scala:86:25:cBuf") } // x6392 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x6392_d0_b0) = false
    bufferDepthOf(x6392_d0_b0) = 1
    staticDimsOf(x6392_d0_b0) = List(256)
    val x6393_d0_b0 = withCtrl(x7224) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6393_d0_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x6393 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x6393_d0_b0) = false
    bufferDepthOf(x6393_d0_b0) = 1
    staticDimsOf(x6393_d0_b0) = List(256)
    val x6393_d1_b0 = withCtrl(x7224) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6393_d1_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x6393 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x6393_d1_b0) = false
    bufferDepthOf(x6393_d1_b0) = 1
    staticDimsOf(x6393_d1_b0) = List(256)
    val x6394 = withCtrl(x7224) { Counter(min=Const(0), max=Const(150), step=Const(1), par=1).name("x6394").srcCtx("DeepBenchLSTM256.scala:90:34") } // CounterNew(Const(0),Const(150),Const(1),Const(1))
    val x6395 = withCtrl(x7224) { CounterChain(List(x6394)).name("x6395").srcCtx("DeepBenchLSTM256.scala:90:40") } // CounterChainNew(List(x6394))
    val x7201 = withCtrl(x7224) { LoopController(style=SeqPipe, level=OuterControl, cchain=x6395).name("x7201").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnrolledForeach(List(Const(true)),x6395,Block(Const(())),List(List(b1968)),List(List(b1969)))
    val b1968 = withCtrl(x7201) { CounterIter(x6394, Some(0)).name("b1968") } // b1968
    val b1969 = withCtrl(x7201) { Const(true).name("b1969") } // b1969
    val x6396_d0_b0 = withCtrl(x7201) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6396_d0_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x6396 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6396_d0_b0) = false
    bufferDepthOf(x6396_d0_b0) = 1
    staticDimsOf(x6396_d0_b0) = List(4, 256)
    val x6396_d0_b1 = withCtrl(x7201) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6396_d0_b1").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x6396 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6396_d0_b1) = false
    bufferDepthOf(x6396_d0_b1) = 1
    staticDimsOf(x6396_d0_b1) = List(4, 256)
    val x6396_d1_b0 = withCtrl(x7201) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6396_d1_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x6396 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6396_d1_b0) = true
    bufferDepthOf(x6396_d1_b0) = 1
    staticDimsOf(x6396_d1_b0) = List(4, 256)
    val x6397_d0_b0 = withCtrl(x7201) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6397_d0_b0").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x6397 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6397_d0_b0) = false
    bufferDepthOf(x6397_d0_b0) = 1
    staticDimsOf(x6397_d0_b0) = List(4, 256)
    val x6397_d0_b1 = withCtrl(x7201) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6397_d0_b1").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x6397 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6397_d0_b1) = false
    bufferDepthOf(x6397_d0_b1) = 1
    staticDimsOf(x6397_d0_b1) = List(4, 256)
    val x6398_d0 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d0) = false
    bufferDepthOf(x6398_d0) = 1
    val x6398_d1 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d1) = false
    bufferDepthOf(x6398_d1) = 1
    val x6398_d2 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d2) = false
    bufferDepthOf(x6398_d2) = 1
    val x6398_d3 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d3) = false
    bufferDepthOf(x6398_d3) = 1
    val x6398_d4 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d4) = false
    bufferDepthOf(x6398_d4) = 1
    val x6398_d5 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d5").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d5) = false
    bufferDepthOf(x6398_d5) = 1
    val x6398_d6 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d6").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d6) = false
    bufferDepthOf(x6398_d6) = 1
    val x6398_d7 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d7").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d7) = false
    bufferDepthOf(x6398_d7) = 1
    val x6398_d8 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d8").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d8) = false
    bufferDepthOf(x6398_d8) = 1
    val x6398_d9 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d9").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d9) = false
    bufferDepthOf(x6398_d9) = 1
    val x6398_d10 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d10").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d10) = false
    bufferDepthOf(x6398_d10) = 1
    val x6398_d11 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d11").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d11) = false
    bufferDepthOf(x6398_d11) = 1
    val x6398_d12 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d12").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d12) = false
    bufferDepthOf(x6398_d12) = 1
    val x6398_d13 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d13").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d13) = false
    bufferDepthOf(x6398_d13) = 1
    val x6398_d14 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d14").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d14) = false
    bufferDepthOf(x6398_d14) = 1
    val x6398_d15 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d15").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d15) = false
    bufferDepthOf(x6398_d15) = 1
    val x6398_d16 = withCtrl(x7201) { Reg(init=Some(false)).name("x6398_d16").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6398 = RegNew(Const(false))
    isAccum(x6398_d16) = false
    bufferDepthOf(x6398_d16) = 1
    val x6399_d0 = withCtrl(x7201) { Reg(init=Some(false)).name("x6399_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6399 = RegNew(Const(false))
    isAccum(x6399_d0) = false
    bufferDepthOf(x6399_d0) = 1
    val x6399_d1 = withCtrl(x7201) { Reg(init=Some(false)).name("x6399_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6399 = RegNew(Const(false))
    isAccum(x6399_d1) = false
    bufferDepthOf(x6399_d1) = 1
    val x6400_d0 = withCtrl(x7201) { Reg(init=Some(false)).name("x6400_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6400 = RegNew(Const(false))
    isAccum(x6400_d0) = false
    bufferDepthOf(x6400_d0) = 1
    val x6400_d1 = withCtrl(x7201) { Reg(init=Some(false)).name("x6400_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6400 = RegNew(Const(false))
    isAccum(x6400_d1) = false
    bufferDepthOf(x6400_d1) = 1
    val x6401_d0 = withCtrl(x7201) { Reg(init=Some(0)).name("x6401_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6401 = RegNew(Const(0))
    isAccum(x6401_d0) = false
    bufferDepthOf(x6401_d0) = 1
    val x6401_d1 = withCtrl(x7201) { Reg(init=Some(0)).name("x6401_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x6401 = RegNew(Const(0))
    isAccum(x6401_d1) = false
    bufferDepthOf(x6401_d1) = 1
    val x6413 = withCtrl(x7201) { UnitController(style=SeqPipe, level=InnerControl).name("x6413").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnitPipe(List(b1969),Block(Const(())))
    val x6402 = withCtrl(x6413) { OpDef(op=FixEql, inputs=List(b1968, Const(0))).name("x6402").srcCtx("DeepBenchLSTM256.scala:93:33:isInitStep") } // FixEql(b1968,Const(0))
    val x6403 = withCtrl(x6413) { OpDef(op=FixLt, inputs=List(b1968, Const(64))).name("x6403").srcCtx("VecMatMultBiasAdd.scala:154:28:isFstRange") } // FixLt(b1968,Const(64))
    val x6404 = withCtrl(x6413) { OpDef(op=FixLt, inputs=List(Const(64), b1968)).name("x6404").srcCtx("VecMatMultBiasAdd.scala:155:28:isLstRange") } // FixLt(Const(64),b1968)
    val x6405 = withCtrl(x6413) { OpDef(op=FixSub, inputs=List(b1968, Const(64))).name("x6405").srcCtx("VecMatMultBiasAdd.scala:157:24:midIdx") } // FixSub(b1968,Const(64))
    val x6406 = withCtrl(x6413) { OpDef(op=FixSub, inputs=List(b1968, Const(128))).name("x6406").srcCtx("VecMatMultBiasAdd.scala:158:24:lstIdx") } // FixSub(b1968,Const(128))
    val x6407 = withCtrl(x6413) { OpDef(op=MuxOp, inputs=List(x6404, x6406, x6405)).name("x6407").srcCtx("VecMatMultBiasAdd.scala:160:30") } // Mux(x6404,x6406,x6405)
    val x6408 = withCtrl(x6413) { OpDef(op=MuxOp, inputs=List(x6403, b1968, x6407)).name("x6408").srcCtx("VecMatMultBiasAdd.scala:159:25:iTileStep") } // Mux(x6403,b1968,x6407)
    val x6409_x6398_d0 = withCtrl(x6413) { WriteMem(x6398_d0, x6402).name("x6409_x6398_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d5 = withCtrl(x6413) { WriteMem(x6398_d5, x6402).name("x6409_x6398_d5").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d10 = withCtrl(x6413) { WriteMem(x6398_d10, x6402).name("x6409_x6398_d10").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d14 = withCtrl(x6413) { WriteMem(x6398_d14, x6402).name("x6409_x6398_d14").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d1 = withCtrl(x6413) { WriteMem(x6398_d1, x6402).name("x6409_x6398_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d6 = withCtrl(x6413) { WriteMem(x6398_d6, x6402).name("x6409_x6398_d6").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d9 = withCtrl(x6413) { WriteMem(x6398_d9, x6402).name("x6409_x6398_d9").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d13 = withCtrl(x6413) { WriteMem(x6398_d13, x6402).name("x6409_x6398_d13").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d2 = withCtrl(x6413) { WriteMem(x6398_d2, x6402).name("x6409_x6398_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d12 = withCtrl(x6413) { WriteMem(x6398_d12, x6402).name("x6409_x6398_d12").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d7 = withCtrl(x6413) { WriteMem(x6398_d7, x6402).name("x6409_x6398_d7").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d3 = withCtrl(x6413) { WriteMem(x6398_d3, x6402).name("x6409_x6398_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d16 = withCtrl(x6413) { WriteMem(x6398_d16, x6402).name("x6409_x6398_d16").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d11 = withCtrl(x6413) { WriteMem(x6398_d11, x6402).name("x6409_x6398_d11").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d8 = withCtrl(x6413) { WriteMem(x6398_d8, x6402).name("x6409_x6398_d8").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d4 = withCtrl(x6413) { WriteMem(x6398_d4, x6402).name("x6409_x6398_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6409_x6398_d15 = withCtrl(x6413) { WriteMem(x6398_d15, x6402).name("x6409_x6398_d15").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6398,x6402,b1969)
    val x6410_x6399_d0 = withCtrl(x6413) { WriteMem(x6399_d0, x6403).name("x6410_x6399_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6399,x6403,b1969)
    val x6410_x6399_d1 = withCtrl(x6413) { WriteMem(x6399_d1, x6403).name("x6410_x6399_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6399,x6403,b1969)
    val x6411_x6400_d0 = withCtrl(x6413) { WriteMem(x6400_d0, x6404).name("x6411_x6400_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6400,x6404,b1969)
    val x6411_x6400_d1 = withCtrl(x6413) { WriteMem(x6400_d1, x6404).name("x6411_x6400_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6400,x6404,b1969)
    val x6412_x6401_d0 = withCtrl(x6413) { WriteMem(x6401_d0, x6408).name("x6412_x6401_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6401,x6408,b1969)
    val x6412_x6401_d1 = withCtrl(x6413) { WriteMem(x6401_d1, x6408).name("x6412_x6401_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x6401,x6408,b1969)
    val x6414 = withCtrl(x7201) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6414").srcCtx("VecMatMultBiasAdd.scala:165:59") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6415 = withCtrl(x7201) { CounterChain(List(x6414)).name("x6415").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(List(x6414))
    val x7051 = withCtrl(x7201) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6415).name("x7051").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledReduce(List(b1969),x6415,x6396,Block((x6396) => Const(())),List(List(b1993, b1994, b1995, b1996, b1997, b1998, b1999, b2000, b2001, b2002, b2003, b2004, b2005, b2006, b2007, b2008)),List(List(b2009, b2010, b2011, b2012, b2013, b2014, b2015, b2016, b2017, b2018, b2019, b2020, b2021, b2022, b2023, b2024)))
    val b1993 = withCtrl(x7051) { CounterIter(x6414, Some(0)).name("b1993") } // b1993
    val b2009 = withCtrl(x7051) { Const(true).name("b2009") } // b2009
    val b1994 = withCtrl(x7051) { CounterIter(x6414, Some(1)).name("b1994") } // b1994
    val b2010 = withCtrl(x7051) { Const(true).name("b2010") } // b2010
    val b1995 = withCtrl(x7051) { CounterIter(x6414, Some(2)).name("b1995") } // b1995
    val b2011 = withCtrl(x7051) { Const(true).name("b2011") } // b2011
    val b1996 = withCtrl(x7051) { CounterIter(x6414, Some(3)).name("b1996") } // b1996
    val b2012 = withCtrl(x7051) { Const(true).name("b2012") } // b2012
    val b1997 = withCtrl(x7051) { CounterIter(x6414, Some(4)).name("b1997") } // b1997
    val b2013 = withCtrl(x7051) { Const(true).name("b2013") } // b2013
    val b1998 = withCtrl(x7051) { CounterIter(x6414, Some(5)).name("b1998") } // b1998
    val b2014 = withCtrl(x7051) { Const(true).name("b2014") } // b2014
    val b1999 = withCtrl(x7051) { CounterIter(x6414, Some(6)).name("b1999") } // b1999
    val b2015 = withCtrl(x7051) { Const(true).name("b2015") } // b2015
    val b2000 = withCtrl(x7051) { CounterIter(x6414, Some(7)).name("b2000") } // b2000
    val b2016 = withCtrl(x7051) { Const(true).name("b2016") } // b2016
    val b2001 = withCtrl(x7051) { CounterIter(x6414, Some(8)).name("b2001") } // b2001
    val b2017 = withCtrl(x7051) { Const(true).name("b2017") } // b2017
    val b2002 = withCtrl(x7051) { CounterIter(x6414, Some(9)).name("b2002") } // b2002
    val b2018 = withCtrl(x7051) { Const(true).name("b2018") } // b2018
    val b2003 = withCtrl(x7051) { CounterIter(x6414, Some(10)).name("b2003") } // b2003
    val b2019 = withCtrl(x7051) { Const(true).name("b2019") } // b2019
    val b2004 = withCtrl(x7051) { CounterIter(x6414, Some(11)).name("b2004") } // b2004
    val b2020 = withCtrl(x7051) { Const(true).name("b2020") } // b2020
    val b2005 = withCtrl(x7051) { CounterIter(x6414, Some(12)).name("b2005") } // b2005
    val b2021 = withCtrl(x7051) { Const(true).name("b2021") } // b2021
    val b2006 = withCtrl(x7051) { CounterIter(x6414, Some(13)).name("b2006") } // b2006
    val b2022 = withCtrl(x7051) { Const(true).name("b2022") } // b2022
    val b2007 = withCtrl(x7051) { CounterIter(x6414, Some(14)).name("b2007") } // b2007
    val b2023 = withCtrl(x7051) { Const(true).name("b2023") } // b2023
    val b2008 = withCtrl(x7051) { CounterIter(x6414, Some(15)).name("b2008") } // b2008
    val b2024 = withCtrl(x7051) { Const(true).name("b2024") } // b2024
    val x6416_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6416_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6416 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6416_d0_b0) = false
    bufferDepthOf(x6416_d0_b0) = 2
    staticDimsOf(x6416_d0_b0) = List(4, 256)
    val x6416_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6416_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6416 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6416_d0_b1) = false
    bufferDepthOf(x6416_d0_b1) = 2
    staticDimsOf(x6416_d0_b1) = List(4, 256)
    val x6417_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6417_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6417 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6417_d0_b0) = false
    bufferDepthOf(x6417_d0_b0) = 2
    staticDimsOf(x6417_d0_b0) = List(4, 256)
    val x6417_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6417_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6417 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6417_d0_b1) = false
    bufferDepthOf(x6417_d0_b1) = 2
    staticDimsOf(x6417_d0_b1) = List(4, 256)
    val x6418_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6418_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6418 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6418_d0_b0) = false
    bufferDepthOf(x6418_d0_b0) = 2
    staticDimsOf(x6418_d0_b0) = List(4, 256)
    val x6418_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6418_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6418 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6418_d0_b1) = false
    bufferDepthOf(x6418_d0_b1) = 2
    staticDimsOf(x6418_d0_b1) = List(4, 256)
    val x6419_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6419_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6419 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6419_d0_b0) = false
    bufferDepthOf(x6419_d0_b0) = 2
    staticDimsOf(x6419_d0_b0) = List(4, 256)
    val x6419_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6419_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6419 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6419_d0_b1) = false
    bufferDepthOf(x6419_d0_b1) = 2
    staticDimsOf(x6419_d0_b1) = List(4, 256)
    val x6420_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6420_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6420 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6420_d0_b0) = false
    bufferDepthOf(x6420_d0_b0) = 2
    staticDimsOf(x6420_d0_b0) = List(4, 256)
    val x6420_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6420_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6420 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6420_d0_b1) = false
    bufferDepthOf(x6420_d0_b1) = 2
    staticDimsOf(x6420_d0_b1) = List(4, 256)
    val x6421_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6421_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6421 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6421_d0_b0) = false
    bufferDepthOf(x6421_d0_b0) = 2
    staticDimsOf(x6421_d0_b0) = List(4, 256)
    val x6421_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6421_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6421 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6421_d0_b1) = false
    bufferDepthOf(x6421_d0_b1) = 2
    staticDimsOf(x6421_d0_b1) = List(4, 256)
    val x6422_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6422_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6422 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6422_d0_b0) = false
    bufferDepthOf(x6422_d0_b0) = 2
    staticDimsOf(x6422_d0_b0) = List(4, 256)
    val x6422_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6422_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6422 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6422_d0_b1) = false
    bufferDepthOf(x6422_d0_b1) = 2
    staticDimsOf(x6422_d0_b1) = List(4, 256)
    val x6423_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6423_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6423 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6423_d0_b0) = false
    bufferDepthOf(x6423_d0_b0) = 2
    staticDimsOf(x6423_d0_b0) = List(4, 256)
    val x6423_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6423_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6423 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6423_d0_b1) = false
    bufferDepthOf(x6423_d0_b1) = 2
    staticDimsOf(x6423_d0_b1) = List(4, 256)
    val x6424_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6424_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6424 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6424_d0_b0) = false
    bufferDepthOf(x6424_d0_b0) = 2
    staticDimsOf(x6424_d0_b0) = List(4, 256)
    val x6424_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6424_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6424 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6424_d0_b1) = false
    bufferDepthOf(x6424_d0_b1) = 2
    staticDimsOf(x6424_d0_b1) = List(4, 256)
    val x6425_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6425_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6425 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6425_d0_b0) = false
    bufferDepthOf(x6425_d0_b0) = 2
    staticDimsOf(x6425_d0_b0) = List(4, 256)
    val x6425_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6425_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6425 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6425_d0_b1) = false
    bufferDepthOf(x6425_d0_b1) = 2
    staticDimsOf(x6425_d0_b1) = List(4, 256)
    val x6426_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6426_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6426 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6426_d0_b0) = false
    bufferDepthOf(x6426_d0_b0) = 2
    staticDimsOf(x6426_d0_b0) = List(4, 256)
    val x6426_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6426_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6426 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6426_d0_b1) = false
    bufferDepthOf(x6426_d0_b1) = 2
    staticDimsOf(x6426_d0_b1) = List(4, 256)
    val x6427_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6427_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6427 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6427_d0_b0) = false
    bufferDepthOf(x6427_d0_b0) = 2
    staticDimsOf(x6427_d0_b0) = List(4, 256)
    val x6427_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6427_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6427 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6427_d0_b1) = false
    bufferDepthOf(x6427_d0_b1) = 2
    staticDimsOf(x6427_d0_b1) = List(4, 256)
    val x6428_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6428_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6428 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6428_d0_b0) = false
    bufferDepthOf(x6428_d0_b0) = 2
    staticDimsOf(x6428_d0_b0) = List(4, 256)
    val x6428_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6428_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6428 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6428_d0_b1) = false
    bufferDepthOf(x6428_d0_b1) = 2
    staticDimsOf(x6428_d0_b1) = List(4, 256)
    val x6429_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6429_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6429 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6429_d0_b0) = false
    bufferDepthOf(x6429_d0_b0) = 2
    staticDimsOf(x6429_d0_b0) = List(4, 256)
    val x6429_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6429_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6429 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6429_d0_b1) = false
    bufferDepthOf(x6429_d0_b1) = 2
    staticDimsOf(x6429_d0_b1) = List(4, 256)
    val x6430_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6430_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6430 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6430_d0_b0) = false
    bufferDepthOf(x6430_d0_b0) = 2
    staticDimsOf(x6430_d0_b0) = List(4, 256)
    val x6430_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6430_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6430 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6430_d0_b1) = false
    bufferDepthOf(x6430_d0_b1) = 2
    staticDimsOf(x6430_d0_b1) = List(4, 256)
    val x6431_d0_b0 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6431_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6431 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6431_d0_b0) = false
    bufferDepthOf(x6431_d0_b0) = 2
    staticDimsOf(x6431_d0_b0) = List(4, 256)
    val x6431_d0_b1 = withCtrl(x7051) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6431_d0_b1").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x6431 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x6431_d0_b1) = false
    bufferDepthOf(x6431_d0_b1) = 2
    staticDimsOf(x6431_d0_b1) = List(4, 256)
    val x6432_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6432_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6432 = RegNew(Const(0))
    isAccum(x6432_d0) = false
    bufferDepthOf(x6432_d0) = 2
    val x6432_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6432_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6432 = RegNew(Const(0))
    isAccum(x6432_d1) = false
    bufferDepthOf(x6432_d1) = 2
    val x6433_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6433_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6433 = RegNew(Const(0))
    isAccum(x6433_d0) = false
    bufferDepthOf(x6433_d0) = 2
    val x6433_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6433_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6433 = RegNew(Const(0))
    isAccum(x6433_d1) = false
    bufferDepthOf(x6433_d1) = 2
    val x6434_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6434_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6434 = RegNew(Const(0))
    isAccum(x6434_d0) = false
    bufferDepthOf(x6434_d0) = 2
    val x6434_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6434_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6434 = RegNew(Const(0))
    isAccum(x6434_d1) = false
    bufferDepthOf(x6434_d1) = 2
    val x6435_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6435_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6435 = RegNew(Const(0))
    isAccum(x6435_d0) = false
    bufferDepthOf(x6435_d0) = 2
    val x6435_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6435_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6435 = RegNew(Const(0))
    isAccum(x6435_d1) = false
    bufferDepthOf(x6435_d1) = 2
    val x6436_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6436_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6436 = RegNew(Const(0))
    isAccum(x6436_d0) = false
    bufferDepthOf(x6436_d0) = 2
    val x6436_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6436_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6436 = RegNew(Const(0))
    isAccum(x6436_d1) = false
    bufferDepthOf(x6436_d1) = 2
    val x6437_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6437_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6437 = RegNew(Const(0))
    isAccum(x6437_d0) = false
    bufferDepthOf(x6437_d0) = 2
    val x6437_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6437_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6437 = RegNew(Const(0))
    isAccum(x6437_d1) = false
    bufferDepthOf(x6437_d1) = 2
    val x6438_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6438_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6438 = RegNew(Const(0))
    isAccum(x6438_d0) = false
    bufferDepthOf(x6438_d0) = 2
    val x6438_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6438_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6438 = RegNew(Const(0))
    isAccum(x6438_d1) = false
    bufferDepthOf(x6438_d1) = 2
    val x6439_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6439_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6439 = RegNew(Const(0))
    isAccum(x6439_d0) = false
    bufferDepthOf(x6439_d0) = 2
    val x6439_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6439_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6439 = RegNew(Const(0))
    isAccum(x6439_d1) = false
    def split1 = {
    bufferDepthOf(x6439_d1) = 2
    val x6440_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6440_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6440 = RegNew(Const(0))
    isAccum(x6440_d0) = false
    bufferDepthOf(x6440_d0) = 2
    val x6440_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6440_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6440 = RegNew(Const(0))
    isAccum(x6440_d1) = false
    bufferDepthOf(x6440_d1) = 2
    val x6441_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6441_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6441 = RegNew(Const(0))
    isAccum(x6441_d0) = false
    bufferDepthOf(x6441_d0) = 2
    val x6441_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6441_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6441 = RegNew(Const(0))
    isAccum(x6441_d1) = false
    bufferDepthOf(x6441_d1) = 2
    val x6442_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6442_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6442 = RegNew(Const(0))
    isAccum(x6442_d0) = false
    bufferDepthOf(x6442_d0) = 2
    val x6442_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6442_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6442 = RegNew(Const(0))
    isAccum(x6442_d1) = false
    bufferDepthOf(x6442_d1) = 2
    val x6443_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6443_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6443 = RegNew(Const(0))
    isAccum(x6443_d0) = false
    bufferDepthOf(x6443_d0) = 2
    val x6443_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6443_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6443 = RegNew(Const(0))
    isAccum(x6443_d1) = false
    bufferDepthOf(x6443_d1) = 2
    val x6444_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6444_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6444 = RegNew(Const(0))
    isAccum(x6444_d0) = false
    bufferDepthOf(x6444_d0) = 2
    val x6444_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6444_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6444 = RegNew(Const(0))
    isAccum(x6444_d1) = false
    bufferDepthOf(x6444_d1) = 2
    val x6445_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6445_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6445 = RegNew(Const(0))
    isAccum(x6445_d0) = false
    bufferDepthOf(x6445_d0) = 2
    val x6445_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6445_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6445 = RegNew(Const(0))
    isAccum(x6445_d1) = false
    bufferDepthOf(x6445_d1) = 2
    val x6446_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6446_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6446 = RegNew(Const(0))
    isAccum(x6446_d0) = false
    bufferDepthOf(x6446_d0) = 2
    val x6446_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6446_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6446 = RegNew(Const(0))
    isAccum(x6446_d1) = false
    bufferDepthOf(x6446_d1) = 2
    val x6447_d0 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6447_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6447 = RegNew(Const(0))
    isAccum(x6447_d0) = false
    bufferDepthOf(x6447_d0) = 2
    val x6447_d1 = withCtrl(x7051) { Reg(init=Some(0.0)).name("x6447_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x6447 = RegNew(Const(0))
    isAccum(x6447_d1) = false
    bufferDepthOf(x6447_d1) = 2
    val x6544 = withCtrl(x7051) { UnitController(style=ForkJoin, level=OuterControl).name("x6544").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x6453 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6453").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2009, b1969),Block(Const(())))
    val x6448 = withCtrl(x6453) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x6448").srcCtx("UnrollingBase.scala:28:66") } // And(b2009,b1969)
    val x6449 = withCtrl(x6453) { LoadBanks(List(x6393_d1_b0), List(b1993)).name("x6449").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1993),Const(0),x6448)
    val x6450 = withCtrl(x6453) { ReadMem(x6398_d1).name("x6450").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6451 = withCtrl(x6453) { OpDef(op=MuxOp, inputs=List(x6450, Const(0.0), x6449)).name("x6451").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6450,Const(0),x6449)
    val x6452_x6432_d0 = withCtrl(x6453) { WriteMem(x6432_d0, x6451).name("x6452_x6432_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6432,x6451,x6448)
    val x6452_x6432_d1 = withCtrl(x6453) { WriteMem(x6432_d1, x6451).name("x6452_x6432_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6432,x6451,x6448)
    val x6459 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6459").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2010, b1969),Block(Const(())))
    val x6454 = withCtrl(x6459) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x6454").srcCtx("UnrollingBase.scala:28:66") } // And(b2010,b1969)
    val x6455 = withCtrl(x6459) { LoadBanks(List(x6393_d1_b0), List(b1994)).name("x6455").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1994),Const(0),x6454)
    val x6456 = withCtrl(x6459) { ReadMem(x6398_d2).name("x6456").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6457 = withCtrl(x6459) { OpDef(op=MuxOp, inputs=List(x6456, Const(0.0), x6455)).name("x6457").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6456,Const(0),x6455)
    val x6458_x6433_d0 = withCtrl(x6459) { WriteMem(x6433_d0, x6457).name("x6458_x6433_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6433,x6457,x6454)
    val x6458_x6433_d1 = withCtrl(x6459) { WriteMem(x6433_d1, x6457).name("x6458_x6433_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6433,x6457,x6454)
    val x6465 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6465").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2011, b1969),Block(Const(())))
    val x6460 = withCtrl(x6465) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x6460").srcCtx("UnrollingBase.scala:28:66") } // And(b2011,b1969)
    val x6461 = withCtrl(x6465) { LoadBanks(List(x6393_d1_b0), List(b1995)).name("x6461").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1995),Const(0),x6460)
    val x6462 = withCtrl(x6465) { ReadMem(x6398_d3).name("x6462").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6463 = withCtrl(x6465) { OpDef(op=MuxOp, inputs=List(x6462, Const(0.0), x6461)).name("x6463").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6462,Const(0),x6461)
    val x6464_x6434_d0 = withCtrl(x6465) { WriteMem(x6434_d0, x6463).name("x6464_x6434_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6434,x6463,x6460)
    val x6464_x6434_d1 = withCtrl(x6465) { WriteMem(x6434_d1, x6463).name("x6464_x6434_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6434,x6463,x6460)
    val x6471 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6471").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2012, b1969),Block(Const(())))
    val x6466 = withCtrl(x6471) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x6466").srcCtx("UnrollingBase.scala:28:66") } // And(b2012,b1969)
    val x6467 = withCtrl(x6471) { LoadBanks(List(x6393_d1_b0), List(b1996)).name("x6467").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1996),Const(0),x6466)
    val x6468 = withCtrl(x6471) { ReadMem(x6398_d4).name("x6468").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6469 = withCtrl(x6471) { OpDef(op=MuxOp, inputs=List(x6468, Const(0.0), x6467)).name("x6469").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6468,Const(0),x6467)
    val x6470_x6435_d0 = withCtrl(x6471) { WriteMem(x6435_d0, x6469).name("x6470_x6435_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6435,x6469,x6466)
    val x6470_x6435_d1 = withCtrl(x6471) { WriteMem(x6435_d1, x6469).name("x6470_x6435_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6435,x6469,x6466)
    val x6477 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6477").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2013, b1969),Block(Const(())))
    val x6472 = withCtrl(x6477) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x6472").srcCtx("UnrollingBase.scala:28:66") } // And(b2013,b1969)
    val x6473 = withCtrl(x6477) { LoadBanks(List(x6393_d1_b0), List(b1997)).name("x6473").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1997),Const(0),x6472)
    val x6474 = withCtrl(x6477) { ReadMem(x6398_d5).name("x6474").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6475 = withCtrl(x6477) { OpDef(op=MuxOp, inputs=List(x6474, Const(0.0), x6473)).name("x6475").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6474,Const(0),x6473)
    val x6476_x6436_d0 = withCtrl(x6477) { WriteMem(x6436_d0, x6475).name("x6476_x6436_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6436,x6475,x6472)
    val x6476_x6436_d1 = withCtrl(x6477) { WriteMem(x6436_d1, x6475).name("x6476_x6436_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6436,x6475,x6472)
    val x6483 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6483").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2014, b1969),Block(Const(())))
    val x6478 = withCtrl(x6483) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x6478").srcCtx("UnrollingBase.scala:28:66") } // And(b2014,b1969)
    val x6479 = withCtrl(x6483) { LoadBanks(List(x6393_d1_b0), List(b1998)).name("x6479").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1998),Const(0),x6478)
    val x6480 = withCtrl(x6483) { ReadMem(x6398_d6).name("x6480").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6481 = withCtrl(x6483) { OpDef(op=MuxOp, inputs=List(x6480, Const(0.0), x6479)).name("x6481").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6480,Const(0),x6479)
    val x6482_x6437_d0 = withCtrl(x6483) { WriteMem(x6437_d0, x6481).name("x6482_x6437_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6437,x6481,x6478)
    val x6482_x6437_d1 = withCtrl(x6483) { WriteMem(x6437_d1, x6481).name("x6482_x6437_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6437,x6481,x6478)
    val x6489 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6489").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2015, b1969),Block(Const(())))
    val x6484 = withCtrl(x6489) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x6484").srcCtx("UnrollingBase.scala:28:66") } // And(b2015,b1969)
    val x6485 = withCtrl(x6489) { LoadBanks(List(x6393_d1_b0), List(b1999)).name("x6485").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b1999),Const(0),x6484)
    val x6486 = withCtrl(x6489) { ReadMem(x6398_d7).name("x6486").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6487 = withCtrl(x6489) { OpDef(op=MuxOp, inputs=List(x6486, Const(0.0), x6485)).name("x6487").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6486,Const(0),x6485)
    val x6488_x6438_d0 = withCtrl(x6489) { WriteMem(x6438_d0, x6487).name("x6488_x6438_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6438,x6487,x6484)
    val x6488_x6438_d1 = withCtrl(x6489) { WriteMem(x6438_d1, x6487).name("x6488_x6438_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6438,x6487,x6484)
    val x6495 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6495").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2016, b1969),Block(Const(())))
    val x6490 = withCtrl(x6495) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x6490").srcCtx("UnrollingBase.scala:28:66") } // And(b2016,b1969)
    val x6491 = withCtrl(x6495) { LoadBanks(List(x6393_d1_b0), List(b2000)).name("x6491").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2000),Const(0),x6490)
    val x6492 = withCtrl(x6495) { ReadMem(x6398_d8).name("x6492").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6493 = withCtrl(x6495) { OpDef(op=MuxOp, inputs=List(x6492, Const(0.0), x6491)).name("x6493").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6492,Const(0),x6491)
    val x6494_x6439_d0 = withCtrl(x6495) { WriteMem(x6439_d0, x6493).name("x6494_x6439_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6439,x6493,x6490)
    val x6494_x6439_d1 = withCtrl(x6495) { WriteMem(x6439_d1, x6493).name("x6494_x6439_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6439,x6493,x6490)
    val x6501 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6501").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2017, b1969),Block(Const(())))
    val x6496 = withCtrl(x6501) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x6496").srcCtx("UnrollingBase.scala:28:66") } // And(b2017,b1969)
    val x6497 = withCtrl(x6501) { LoadBanks(List(x6393_d1_b0), List(b2001)).name("x6497").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2001),Const(0),x6496)
    val x6498 = withCtrl(x6501) { ReadMem(x6398_d9).name("x6498").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6499 = withCtrl(x6501) { OpDef(op=MuxOp, inputs=List(x6498, Const(0.0), x6497)).name("x6499").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6498,Const(0),x6497)
    val x6500_x6440_d0 = withCtrl(x6501) { WriteMem(x6440_d0, x6499).name("x6500_x6440_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6440,x6499,x6496)
    val x6500_x6440_d1 = withCtrl(x6501) { WriteMem(x6440_d1, x6499).name("x6500_x6440_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6440,x6499,x6496)
    val x6507 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6507").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2018, b1969),Block(Const(())))
    val x6502 = withCtrl(x6507) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x6502").srcCtx("UnrollingBase.scala:28:66") } // And(b2018,b1969)
    val x6503 = withCtrl(x6507) { LoadBanks(List(x6393_d1_b0), List(b2002)).name("x6503").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2002),Const(0),x6502)
    val x6504 = withCtrl(x6507) { ReadMem(x6398_d10).name("x6504").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6505 = withCtrl(x6507) { OpDef(op=MuxOp, inputs=List(x6504, Const(0.0), x6503)).name("x6505").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6504,Const(0),x6503)
    val x6506_x6441_d0 = withCtrl(x6507) { WriteMem(x6441_d0, x6505).name("x6506_x6441_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6441,x6505,x6502)
    val x6506_x6441_d1 = withCtrl(x6507) { WriteMem(x6441_d1, x6505).name("x6506_x6441_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6441,x6505,x6502)
    val x6513 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6513").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2019, b1969),Block(Const(())))
    val x6508 = withCtrl(x6513) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x6508").srcCtx("UnrollingBase.scala:28:66") } // And(b2019,b1969)
    val x6509 = withCtrl(x6513) { LoadBanks(List(x6393_d1_b0), List(b2003)).name("x6509").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2003),Const(0),x6508)
    val x6510 = withCtrl(x6513) { ReadMem(x6398_d11).name("x6510").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6511 = withCtrl(x6513) { OpDef(op=MuxOp, inputs=List(x6510, Const(0.0), x6509)).name("x6511").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6510,Const(0),x6509)
    val x6512_x6442_d0 = withCtrl(x6513) { WriteMem(x6442_d0, x6511).name("x6512_x6442_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6442,x6511,x6508)
    val x6512_x6442_d1 = withCtrl(x6513) { WriteMem(x6442_d1, x6511).name("x6512_x6442_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6442,x6511,x6508)
    val x6519 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6519").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2020, b1969),Block(Const(())))
    val x6514 = withCtrl(x6519) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x6514").srcCtx("UnrollingBase.scala:28:66") } // And(b2020,b1969)
    val x6515 = withCtrl(x6519) { LoadBanks(List(x6393_d1_b0), List(b2004)).name("x6515").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2004),Const(0),x6514)
    val x6516 = withCtrl(x6519) { ReadMem(x6398_d12).name("x6516").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6517 = withCtrl(x6519) { OpDef(op=MuxOp, inputs=List(x6516, Const(0.0), x6515)).name("x6517").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6516,Const(0),x6515)
    val x6518_x6443_d0 = withCtrl(x6519) { WriteMem(x6443_d0, x6517).name("x6518_x6443_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6443,x6517,x6514)
    val x6518_x6443_d1 = withCtrl(x6519) { WriteMem(x6443_d1, x6517).name("x6518_x6443_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6443,x6517,x6514)
    val x6525 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6525").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2021, b1969),Block(Const(())))
    val x6520 = withCtrl(x6525) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x6520").srcCtx("UnrollingBase.scala:28:66") } // And(b2021,b1969)
    val x6521 = withCtrl(x6525) { LoadBanks(List(x6393_d1_b0), List(b2005)).name("x6521").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2005),Const(0),x6520)
    val x6522 = withCtrl(x6525) { ReadMem(x6398_d13).name("x6522").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6523 = withCtrl(x6525) { OpDef(op=MuxOp, inputs=List(x6522, Const(0.0), x6521)).name("x6523").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6522,Const(0),x6521)
    val x6524_x6444_d0 = withCtrl(x6525) { WriteMem(x6444_d0, x6523).name("x6524_x6444_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6444,x6523,x6520)
    val x6524_x6444_d1 = withCtrl(x6525) { WriteMem(x6444_d1, x6523).name("x6524_x6444_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6444,x6523,x6520)
    val x6531 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6531").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2022, b1969),Block(Const(())))
    val x6526 = withCtrl(x6531) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x6526").srcCtx("UnrollingBase.scala:28:66") } // And(b2022,b1969)
    val x6527 = withCtrl(x6531) { LoadBanks(List(x6393_d1_b0), List(b2006)).name("x6527").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2006),Const(0),x6526)
    val x6528 = withCtrl(x6531) { ReadMem(x6398_d14).name("x6528").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6529 = withCtrl(x6531) { OpDef(op=MuxOp, inputs=List(x6528, Const(0.0), x6527)).name("x6529").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6528,Const(0),x6527)
    val x6530_x6445_d0 = withCtrl(x6531) { WriteMem(x6445_d0, x6529).name("x6530_x6445_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6445,x6529,x6526)
    val x6530_x6445_d1 = withCtrl(x6531) { WriteMem(x6445_d1, x6529).name("x6530_x6445_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6445,x6529,x6526)
    val x6537 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6537").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2023, b1969),Block(Const(())))
    val x6532 = withCtrl(x6537) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x6532").srcCtx("UnrollingBase.scala:28:66") } // And(b2023,b1969)
    val x6533 = withCtrl(x6537) { LoadBanks(List(x6393_d1_b0), List(b2007)).name("x6533").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2007),Const(0),x6532)
    val x6534 = withCtrl(x6537) { ReadMem(x6398_d15).name("x6534").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6535 = withCtrl(x6537) { OpDef(op=MuxOp, inputs=List(x6534, Const(0.0), x6533)).name("x6535").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6534,Const(0),x6533)
    val x6536_x6446_d0 = withCtrl(x6537) { WriteMem(x6446_d0, x6535).name("x6536_x6446_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6446,x6535,x6532)
    val x6536_x6446_d1 = withCtrl(x6537) { WriteMem(x6446_d1, x6535).name("x6536_x6446_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6446,x6535,x6532)
    val x6543 = withCtrl(x6544) { UnitController(style=SeqPipe, level=InnerControl).name("x6543").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2024, b1969),Block(Const(())))
    val x6538 = withCtrl(x6543) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x6538").srcCtx("UnrollingBase.scala:28:66") } // And(b2024,b1969)
    val x6539 = withCtrl(x6543) { LoadBanks(List(x6393_d1_b0), List(b2008)).name("x6539").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x6393,ArrayBuffer(Const(256)),List(b2008),Const(0),x6538)
    val x6540 = withCtrl(x6543) { ReadMem(x6398_d16).name("x6540").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x6541 = withCtrl(x6543) { OpDef(op=MuxOp, inputs=List(x6540, Const(0.0), x6539)).name("x6541").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x6540,Const(0),x6539)
    val x6542_x6447_d0 = withCtrl(x6543) { WriteMem(x6447_d0, x6541).name("x6542_x6447_d0").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6447,x6541,x6538)
    val x6542_x6447_d1 = withCtrl(x6543) { WriteMem(x6447_d1, x6541).name("x6542_x6447_d1").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x6447,x6541,x6538)
    val x6929 = withCtrl(x7051) { UnitController(style=ForkJoin, level=OuterControl).name("x6929").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x6545 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6545").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6546 = withCtrl(x6929) { CounterChain(List(x6545)).name("x6546").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6545))
    val x6568 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6546).name("x6568").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2009, b1969),x6546,Block(Const(())),List(List(b2186, b2187)),List(List(b2188, b2189)))
    val b2186 = withCtrl(x6568) { CounterIter(x6545, Some(0)).name("b2186") } // b2186
    val b2188 = withCtrl(x6568) { Const(true).name("b2188") } // b2188
    val b2187 = withCtrl(x6568) { CounterIter(x6545, Some(1)).name("b2187") } // b2187
    val b2189 = withCtrl(x6568) { Const(true).name("b2189") } // b2189
    val x6567 = withCtrl(x6568) { UnitController(style=ForkJoin, level=OuterControl).name("x6567").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2009, b1969),Block(Const(())))
    val x6547 = withCtrl(x6567) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6547").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6548 = withCtrl(x6567) { CounterChain(List(x6547)).name("x6548").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6547))
    val x6556 = withCtrl(x6567) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6548).name("x6556").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2188, b2009, b1969),x6548,Block(Const(())),List(List(b2194)),List(List(b2195)))
    val b2194 = withCtrl(x6556) { CounterIter(x6547, None).name("b2194") } // b2194
    val b2195 = withCtrl(x6556) { Const(true).name("b2195") } // b2195
    val x6549 = withCtrl(x6556) { OpDef(op=BitAnd, inputs=List(b2195, b2188)).name("x6549").srcCtx("UnrollingBase.scala:28:66") } // And(b2195,b2188)
    val x6550 = withCtrl(x6556) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x6550").srcCtx("UnrollingBase.scala:28:66") } // And(b2009,b1969)
    val x6551 = withCtrl(x6556) { OpDef(op=BitAnd, inputs=List(x6549, x6550)).name("x6551").srcCtx("UnrollingBase.scala:28:66") } // And(x6549,x6550)
    val x6552 = withCtrl(x6556) { LoadBanks(List(x6388_d0_b0), List(b1993, b2186, b2194)).name("x6552").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1993, b2186, b2194),x6551)
    val x6553 = withCtrl(x6556) { ReadMem(x6432_d0).name("x6553").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6432)
    val x6554 = withCtrl(x6556) { OpDef(op=FixMul, inputs=List(x6552, x6553)).name("x6554").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6552,x6553)
    val x6555 = withCtrl(x6556) { StoreBanks(List(List(x6416_d0_b0)), List(b2186, b2194), x6554).name("x6555").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6416,List(List(b2186, b2194)),List(x6554),List(x6551))
    val x6557 = withCtrl(x6567) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6557").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6558 = withCtrl(x6567) { CounterChain(List(x6557)).name("x6558").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6557))
    val x6566 = withCtrl(x6567) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6558).name("x6566").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2189, b2009, b1969),x6558,Block(Const(())),List(List(b2204)),List(List(b2205)))
    val b2204 = withCtrl(x6566) { CounterIter(x6557, None).name("b2204") } // b2204
    val b2205 = withCtrl(x6566) { Const(true).name("b2205") } // b2205
    val x6559 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(b2205, b2189)).name("x6559").srcCtx("UnrollingBase.scala:28:66") } // And(b2205,b2189)
    val x6560 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x6560").srcCtx("UnrollingBase.scala:28:66") } // And(b2009,b1969)
    val x6561 = withCtrl(x6566) { OpDef(op=BitAnd, inputs=List(x6559, x6560)).name("x6561").srcCtx("UnrollingBase.scala:28:66") } // And(x6559,x6560)
    val x6562 = withCtrl(x6566) { LoadBanks(List(x6388_d0_b1), List(b1993, b2187, b2204)).name("x6562").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1993, b2187, b2204),x6561)
    val x6563 = withCtrl(x6566) { ReadMem(x6432_d1).name("x6563").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6432)
    val x6564 = withCtrl(x6566) { OpDef(op=FixMul, inputs=List(x6562, x6563)).name("x6564").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6562,x6563)
    val x6565 = withCtrl(x6566) { StoreBanks(List(List(x6416_d0_b1)), List(b2187, b2204), x6564).name("x6565").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6416,List(List(b2187, b2204)),List(x6564),List(x6561))
    val x6569 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6569").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6570 = withCtrl(x6929) { CounterChain(List(x6569)).name("x6570").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6569))
    val x6592 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6570).name("x6592").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2010, b1969),x6570,Block(Const(())),List(List(b2216, b2217)),List(List(b2218, b2219)))
    val b2216 = withCtrl(x6592) { CounterIter(x6569, Some(0)).name("b2216") } // b2216
    val b2218 = withCtrl(x6592) { Const(true).name("b2218") } // b2218
    val b2217 = withCtrl(x6592) { CounterIter(x6569, Some(1)).name("b2217") } // b2217
    val b2219 = withCtrl(x6592) { Const(true).name("b2219") } // b2219
    val x6591 = withCtrl(x6592) { UnitController(style=ForkJoin, level=OuterControl).name("x6591").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2010, b1969),Block(Const(())))
    val x6571 = withCtrl(x6591) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6571").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6572 = withCtrl(x6591) { CounterChain(List(x6571)).name("x6572").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6571))
    val x6580 = withCtrl(x6591) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6572).name("x6580").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2218, b2010, b1969),x6572,Block(Const(())),List(List(b2224)),List(List(b2225)))
    val b2224 = withCtrl(x6580) { CounterIter(x6571, None).name("b2224") } // b2224
    val b2225 = withCtrl(x6580) { Const(true).name("b2225") } // b2225
    val x6573 = withCtrl(x6580) { OpDef(op=BitAnd, inputs=List(b2225, b2218)).name("x6573").srcCtx("UnrollingBase.scala:28:66") } // And(b2225,b2218)
    val x6574 = withCtrl(x6580) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x6574").srcCtx("UnrollingBase.scala:28:66") } // And(b2010,b1969)
    val x6575 = withCtrl(x6580) { OpDef(op=BitAnd, inputs=List(x6573, x6574)).name("x6575").srcCtx("UnrollingBase.scala:28:66") } // And(x6573,x6574)
    val x6576 = withCtrl(x6580) { LoadBanks(List(x6388_d0_b2), List(b1994, b2216, b2224)).name("x6576").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1994, b2216, b2224),x6575)
    val x6577 = withCtrl(x6580) { ReadMem(x6433_d0).name("x6577").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6433)
    val x6578 = withCtrl(x6580) { OpDef(op=FixMul, inputs=List(x6576, x6577)).name("x6578").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6576,x6577)
    val x6579 = withCtrl(x6580) { StoreBanks(List(List(x6417_d0_b0)), List(b2216, b2224), x6578).name("x6579").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6417,List(List(b2216, b2224)),List(x6578),List(x6575))
    val x6581 = withCtrl(x6591) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6581").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6582 = withCtrl(x6591) { CounterChain(List(x6581)).name("x6582").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6581))
    val x6590 = withCtrl(x6591) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6582).name("x6590").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2219, b2010, b1969),x6582,Block(Const(())),List(List(b2234)),List(List(b2235)))
    val b2234 = withCtrl(x6590) { CounterIter(x6581, None).name("b2234") } // b2234
    val b2235 = withCtrl(x6590) { Const(true).name("b2235") } // b2235
    val x6583 = withCtrl(x6590) { OpDef(op=BitAnd, inputs=List(b2235, b2219)).name("x6583").srcCtx("UnrollingBase.scala:28:66") } // And(b2235,b2219)
    val x6584 = withCtrl(x6590) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x6584").srcCtx("UnrollingBase.scala:28:66") } // And(b2010,b1969)
    val x6585 = withCtrl(x6590) { OpDef(op=BitAnd, inputs=List(x6583, x6584)).name("x6585").srcCtx("UnrollingBase.scala:28:66") } // And(x6583,x6584)
    val x6586 = withCtrl(x6590) { LoadBanks(List(x6388_d0_b3), List(b1994, b2217, b2234)).name("x6586").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1994, b2217, b2234),x6585)
    val x6587 = withCtrl(x6590) { ReadMem(x6433_d1).name("x6587").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6433)
    val x6588 = withCtrl(x6590) { OpDef(op=FixMul, inputs=List(x6586, x6587)).name("x6588").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6586,x6587)
    val x6589 = withCtrl(x6590) { StoreBanks(List(List(x6417_d0_b1)), List(b2217, b2234), x6588).name("x6589").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6417,List(List(b2217, b2234)),List(x6588),List(x6585))
    val x6593 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6593").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6594 = withCtrl(x6929) { CounterChain(List(x6593)).name("x6594").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6593))
    val x6616 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6594).name("x6616").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2011, b1969),x6594,Block(Const(())),List(List(b2246, b2247)),List(List(b2248, b2249)))
    val b2246 = withCtrl(x6616) { CounterIter(x6593, Some(0)).name("b2246") } // b2246
    val b2248 = withCtrl(x6616) { Const(true).name("b2248") } // b2248
    val b2247 = withCtrl(x6616) { CounterIter(x6593, Some(1)).name("b2247") } // b2247
    val b2249 = withCtrl(x6616) { Const(true).name("b2249") } // b2249
    val x6615 = withCtrl(x6616) { UnitController(style=ForkJoin, level=OuterControl).name("x6615").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2011, b1969),Block(Const(())))
    val x6595 = withCtrl(x6615) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6595").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6596 = withCtrl(x6615) { CounterChain(List(x6595)).name("x6596").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6595))
    val x6604 = withCtrl(x6615) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6596).name("x6604").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2248, b2011, b1969),x6596,Block(Const(())),List(List(b2254)),List(List(b2255)))
    val b2254 = withCtrl(x6604) { CounterIter(x6595, None).name("b2254") } // b2254
    val b2255 = withCtrl(x6604) { Const(true).name("b2255") } // b2255
    val x6597 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(b2255, b2248)).name("x6597").srcCtx("UnrollingBase.scala:28:66") } // And(b2255,b2248)
    val x6598 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x6598").srcCtx("UnrollingBase.scala:28:66") } // And(b2011,b1969)
    val x6599 = withCtrl(x6604) { OpDef(op=BitAnd, inputs=List(x6597, x6598)).name("x6599").srcCtx("UnrollingBase.scala:28:66") } // And(x6597,x6598)
    val x6600 = withCtrl(x6604) { LoadBanks(List(x6388_d0_b4), List(b1995, b2246, b2254)).name("x6600").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1995, b2246, b2254),x6599)
    val x6601 = withCtrl(x6604) { ReadMem(x6434_d0).name("x6601").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6434)
    val x6602 = withCtrl(x6604) { OpDef(op=FixMul, inputs=List(x6600, x6601)).name("x6602").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6600,x6601)
    val x6603 = withCtrl(x6604) { StoreBanks(List(List(x6418_d0_b0)), List(b2246, b2254), x6602).name("x6603").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6418,List(List(b2246, b2254)),List(x6602),List(x6599))
    val x6605 = withCtrl(x6615) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6605").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6606 = withCtrl(x6615) { CounterChain(List(x6605)).name("x6606").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6605))
    val x6614 = withCtrl(x6615) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6606).name("x6614").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2249, b2011, b1969),x6606,Block(Const(())),List(List(b2264)),List(List(b2265)))
    val b2264 = withCtrl(x6614) { CounterIter(x6605, None).name("b2264") } // b2264
    val b2265 = withCtrl(x6614) { Const(true).name("b2265") } // b2265
    val x6607 = withCtrl(x6614) { OpDef(op=BitAnd, inputs=List(b2265, b2249)).name("x6607").srcCtx("UnrollingBase.scala:28:66") } // And(b2265,b2249)
    val x6608 = withCtrl(x6614) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x6608").srcCtx("UnrollingBase.scala:28:66") } // And(b2011,b1969)
    val x6609 = withCtrl(x6614) { OpDef(op=BitAnd, inputs=List(x6607, x6608)).name("x6609").srcCtx("UnrollingBase.scala:28:66") } // And(x6607,x6608)
    val x6610 = withCtrl(x6614) { LoadBanks(List(x6388_d0_b5), List(b1995, b2247, b2264)).name("x6610").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1995, b2247, b2264),x6609)
    val x6611 = withCtrl(x6614) { ReadMem(x6434_d1).name("x6611").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6434)
    val x6612 = withCtrl(x6614) { OpDef(op=FixMul, inputs=List(x6610, x6611)).name("x6612").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6610,x6611)
    val x6613 = withCtrl(x6614) { StoreBanks(List(List(x6418_d0_b1)), List(b2247, b2264), x6612).name("x6613").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6418,List(List(b2247, b2264)),List(x6612),List(x6609))
    val x6617 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6617").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6618 = withCtrl(x6929) { CounterChain(List(x6617)).name("x6618").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6617))
    val x6640 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6618).name("x6640").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2012, b1969),x6618,Block(Const(())),List(List(b2276, b2277)),List(List(b2278, b2279)))
    val b2276 = withCtrl(x6640) { CounterIter(x6617, Some(0)).name("b2276") } // b2276
    val b2278 = withCtrl(x6640) { Const(true).name("b2278") } // b2278
    val b2277 = withCtrl(x6640) { CounterIter(x6617, Some(1)).name("b2277") } // b2277
    val b2279 = withCtrl(x6640) { Const(true).name("b2279") } // b2279
    val x6639 = withCtrl(x6640) { UnitController(style=ForkJoin, level=OuterControl).name("x6639").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2012, b1969),Block(Const(())))
    val x6619 = withCtrl(x6639) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6619").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6620 = withCtrl(x6639) { CounterChain(List(x6619)).name("x6620").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6619))
    val x6628 = withCtrl(x6639) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6620).name("x6628").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2278, b2012, b1969),x6620,Block(Const(())),List(List(b2284)),List(List(b2285)))
    val b2284 = withCtrl(x6628) { CounterIter(x6619, None).name("b2284") } // b2284
    val b2285 = withCtrl(x6628) { Const(true).name("b2285") } // b2285
    val x6621 = withCtrl(x6628) { OpDef(op=BitAnd, inputs=List(b2285, b2278)).name("x6621").srcCtx("UnrollingBase.scala:28:66") } // And(b2285,b2278)
    val x6622 = withCtrl(x6628) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x6622").srcCtx("UnrollingBase.scala:28:66") } // And(b2012,b1969)
    val x6623 = withCtrl(x6628) { OpDef(op=BitAnd, inputs=List(x6621, x6622)).name("x6623").srcCtx("UnrollingBase.scala:28:66") } // And(x6621,x6622)
    val x6624 = withCtrl(x6628) { LoadBanks(List(x6388_d0_b6), List(b1996, b2276, b2284)).name("x6624").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1996, b2276, b2284),x6623)
    val x6625 = withCtrl(x6628) { ReadMem(x6435_d0).name("x6625").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6435)
    val x6626 = withCtrl(x6628) { OpDef(op=FixMul, inputs=List(x6624, x6625)).name("x6626").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6624,x6625)
    val x6627 = withCtrl(x6628) { StoreBanks(List(List(x6419_d0_b0)), List(b2276, b2284), x6626).name("x6627").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6419,List(List(b2276, b2284)),List(x6626),List(x6623))
    val x6629 = withCtrl(x6639) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6629").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6630 = withCtrl(x6639) { CounterChain(List(x6629)).name("x6630").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6629))
    val x6638 = withCtrl(x6639) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6630).name("x6638").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2279, b2012, b1969),x6630,Block(Const(())),List(List(b2294)),List(List(b2295)))
    val b2294 = withCtrl(x6638) { CounterIter(x6629, None).name("b2294") } // b2294
    val b2295 = withCtrl(x6638) { Const(true).name("b2295") } // b2295
    val x6631 = withCtrl(x6638) { OpDef(op=BitAnd, inputs=List(b2295, b2279)).name("x6631").srcCtx("UnrollingBase.scala:28:66") } // And(b2295,b2279)
    val x6632 = withCtrl(x6638) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x6632").srcCtx("UnrollingBase.scala:28:66") } // And(b2012,b1969)
    val x6633 = withCtrl(x6638) { OpDef(op=BitAnd, inputs=List(x6631, x6632)).name("x6633").srcCtx("UnrollingBase.scala:28:66") } // And(x6631,x6632)
    val x6634 = withCtrl(x6638) { LoadBanks(List(x6388_d0_b7), List(b1996, b2277, b2294)).name("x6634").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1996, b2277, b2294),x6633)
    val x6635 = withCtrl(x6638) { ReadMem(x6435_d1).name("x6635").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6435)
    val x6636 = withCtrl(x6638) { OpDef(op=FixMul, inputs=List(x6634, x6635)).name("x6636").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6634,x6635)
    val x6637 = withCtrl(x6638) { StoreBanks(List(List(x6419_d0_b1)), List(b2277, b2294), x6636).name("x6637").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6419,List(List(b2277, b2294)),List(x6636),List(x6633))
    val x6641 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6641").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6642 = withCtrl(x6929) { CounterChain(List(x6641)).name("x6642").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6641))
    val x6664 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6642).name("x6664").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2013, b1969),x6642,Block(Const(())),List(List(b2306, b2307)),List(List(b2308, b2309)))
    val b2306 = withCtrl(x6664) { CounterIter(x6641, Some(0)).name("b2306") } // b2306
    val b2308 = withCtrl(x6664) { Const(true).name("b2308") } // b2308
    val b2307 = withCtrl(x6664) { CounterIter(x6641, Some(1)).name("b2307") } // b2307
    val b2309 = withCtrl(x6664) { Const(true).name("b2309") } // b2309
    val x6663 = withCtrl(x6664) { UnitController(style=ForkJoin, level=OuterControl).name("x6663").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2013, b1969),Block(Const(())))
    val x6643 = withCtrl(x6663) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6643").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6644 = withCtrl(x6663) { CounterChain(List(x6643)).name("x6644").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6643))
    val x6652 = withCtrl(x6663) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6644).name("x6652").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2308, b2013, b1969),x6644,Block(Const(())),List(List(b2314)),List(List(b2315)))
    val b2314 = withCtrl(x6652) { CounterIter(x6643, None).name("b2314") } // b2314
    val b2315 = withCtrl(x6652) { Const(true).name("b2315") } // b2315
    val x6645 = withCtrl(x6652) { OpDef(op=BitAnd, inputs=List(b2315, b2308)).name("x6645").srcCtx("UnrollingBase.scala:28:66") } // And(b2315,b2308)
    val x6646 = withCtrl(x6652) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x6646").srcCtx("UnrollingBase.scala:28:66") } // And(b2013,b1969)
    val x6647 = withCtrl(x6652) { OpDef(op=BitAnd, inputs=List(x6645, x6646)).name("x6647").srcCtx("UnrollingBase.scala:28:66") } // And(x6645,x6646)
    val x6648 = withCtrl(x6652) { LoadBanks(List(x6388_d0_b8), List(b1997, b2306, b2314)).name("x6648").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1997, b2306, b2314),x6647)
    val x6649 = withCtrl(x6652) { ReadMem(x6436_d0).name("x6649").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6436)
    val x6650 = withCtrl(x6652) { OpDef(op=FixMul, inputs=List(x6648, x6649)).name("x6650").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6648,x6649)
    val x6651 = withCtrl(x6652) { StoreBanks(List(List(x6420_d0_b0)), List(b2306, b2314), x6650).name("x6651").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6420,List(List(b2306, b2314)),List(x6650),List(x6647))
    val x6653 = withCtrl(x6663) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6653").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6654 = withCtrl(x6663) { CounterChain(List(x6653)).name("x6654").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6653))
    val x6662 = withCtrl(x6663) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6654).name("x6662").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2309, b2013, b1969),x6654,Block(Const(())),List(List(b2324)),List(List(b2325)))
    val b2324 = withCtrl(x6662) { CounterIter(x6653, None).name("b2324") } // b2324
    val b2325 = withCtrl(x6662) { Const(true).name("b2325") } // b2325
    val x6655 = withCtrl(x6662) { OpDef(op=BitAnd, inputs=List(b2325, b2309)).name("x6655").srcCtx("UnrollingBase.scala:28:66") } // And(b2325,b2309)
    val x6656 = withCtrl(x6662) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x6656").srcCtx("UnrollingBase.scala:28:66") } // And(b2013,b1969)
    val x6657 = withCtrl(x6662) { OpDef(op=BitAnd, inputs=List(x6655, x6656)).name("x6657").srcCtx("UnrollingBase.scala:28:66") } // And(x6655,x6656)
    val x6658 = withCtrl(x6662) { LoadBanks(List(x6388_d0_b9), List(b1997, b2307, b2324)).name("x6658").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1997, b2307, b2324),x6657)
    val x6659 = withCtrl(x6662) { ReadMem(x6436_d1).name("x6659").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6436)
    val x6660 = withCtrl(x6662) { OpDef(op=FixMul, inputs=List(x6658, x6659)).name("x6660").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6658,x6659)
    val x6661 = withCtrl(x6662) { StoreBanks(List(List(x6420_d0_b1)), List(b2307, b2324), x6660).name("x6661").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6420,List(List(b2307, b2324)),List(x6660),List(x6657))
    val x6665 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6665").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6666 = withCtrl(x6929) { CounterChain(List(x6665)).name("x6666").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6665))
    val x6688 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6666).name("x6688").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2014, b1969),x6666,Block(Const(())),List(List(b2336, b2337)),List(List(b2338, b2339)))
    val b2336 = withCtrl(x6688) { CounterIter(x6665, Some(0)).name("b2336") } // b2336
    val b2338 = withCtrl(x6688) { Const(true).name("b2338") } // b2338
    val b2337 = withCtrl(x6688) { CounterIter(x6665, Some(1)).name("b2337") } // b2337
    val b2339 = withCtrl(x6688) { Const(true).name("b2339") } // b2339
    val x6687 = withCtrl(x6688) { UnitController(style=ForkJoin, level=OuterControl).name("x6687").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2014, b1969),Block(Const(())))
    val x6667 = withCtrl(x6687) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6667").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6668 = withCtrl(x6687) { CounterChain(List(x6667)).name("x6668").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6667))
    val x6676 = withCtrl(x6687) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6668).name("x6676").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2338, b2014, b1969),x6668,Block(Const(())),List(List(b2344)),List(List(b2345)))
    val b2344 = withCtrl(x6676) { CounterIter(x6667, None).name("b2344") } // b2344
    val b2345 = withCtrl(x6676) { Const(true).name("b2345") } // b2345
    val x6669 = withCtrl(x6676) { OpDef(op=BitAnd, inputs=List(b2345, b2338)).name("x6669").srcCtx("UnrollingBase.scala:28:66") } // And(b2345,b2338)
    val x6670 = withCtrl(x6676) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x6670").srcCtx("UnrollingBase.scala:28:66") } // And(b2014,b1969)
    val x6671 = withCtrl(x6676) { OpDef(op=BitAnd, inputs=List(x6669, x6670)).name("x6671").srcCtx("UnrollingBase.scala:28:66") } // And(x6669,x6670)
    val x6672 = withCtrl(x6676) { LoadBanks(List(x6388_d0_b10), List(b1998, b2336, b2344)).name("x6672").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1998, b2336, b2344),x6671)
    val x6673 = withCtrl(x6676) { ReadMem(x6437_d0).name("x6673").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6437)
    val x6674 = withCtrl(x6676) { OpDef(op=FixMul, inputs=List(x6672, x6673)).name("x6674").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6672,x6673)
    val x6675 = withCtrl(x6676) { StoreBanks(List(List(x6421_d0_b0)), List(b2336, b2344), x6674).name("x6675").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6421,List(List(b2336, b2344)),List(x6674),List(x6671))
    val x6677 = withCtrl(x6687) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6677").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6678 = withCtrl(x6687) { CounterChain(List(x6677)).name("x6678").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6677))
    val x6686 = withCtrl(x6687) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6678).name("x6686").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2339, b2014, b1969),x6678,Block(Const(())),List(List(b2354)),List(List(b2355)))
    val b2354 = withCtrl(x6686) { CounterIter(x6677, None).name("b2354") } // b2354
    val b2355 = withCtrl(x6686) { Const(true).name("b2355") } // b2355
    val x6679 = withCtrl(x6686) { OpDef(op=BitAnd, inputs=List(b2355, b2339)).name("x6679").srcCtx("UnrollingBase.scala:28:66") } // And(b2355,b2339)
    val x6680 = withCtrl(x6686) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x6680").srcCtx("UnrollingBase.scala:28:66") } // And(b2014,b1969)
    val x6681 = withCtrl(x6686) { OpDef(op=BitAnd, inputs=List(x6679, x6680)).name("x6681").srcCtx("UnrollingBase.scala:28:66") } // And(x6679,x6680)
    val x6682 = withCtrl(x6686) { LoadBanks(List(x6388_d0_b11), List(b1998, b2337, b2354)).name("x6682").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1998, b2337, b2354),x6681)
    val x6683 = withCtrl(x6686) { ReadMem(x6437_d1).name("x6683").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6437)
    val x6684 = withCtrl(x6686) { OpDef(op=FixMul, inputs=List(x6682, x6683)).name("x6684").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6682,x6683)
    val x6685 = withCtrl(x6686) { StoreBanks(List(List(x6421_d0_b1)), List(b2337, b2354), x6684).name("x6685").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6421,List(List(b2337, b2354)),List(x6684),List(x6681))
    val x6689 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6689").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6690 = withCtrl(x6929) { CounterChain(List(x6689)).name("x6690").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6689))
    val x6712 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6690).name("x6712").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2015, b1969),x6690,Block(Const(())),List(List(b2366, b2367)),List(List(b2368, b2369)))
    val b2366 = withCtrl(x6712) { CounterIter(x6689, Some(0)).name("b2366") } // b2366
    val b2368 = withCtrl(x6712) { Const(true).name("b2368") } // b2368
    val b2367 = withCtrl(x6712) { CounterIter(x6689, Some(1)).name("b2367") } // b2367
    val b2369 = withCtrl(x6712) { Const(true).name("b2369") } // b2369
    val x6711 = withCtrl(x6712) { UnitController(style=ForkJoin, level=OuterControl).name("x6711").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2015, b1969),Block(Const(())))
    val x6691 = withCtrl(x6711) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6691").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6692 = withCtrl(x6711) { CounterChain(List(x6691)).name("x6692").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6691))
    val x6700 = withCtrl(x6711) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6692).name("x6700").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2368, b2015, b1969),x6692,Block(Const(())),List(List(b2374)),List(List(b2375)))
    val b2374 = withCtrl(x6700) { CounterIter(x6691, None).name("b2374") } // b2374
    val b2375 = withCtrl(x6700) { Const(true).name("b2375") } // b2375
    val x6693 = withCtrl(x6700) { OpDef(op=BitAnd, inputs=List(b2375, b2368)).name("x6693").srcCtx("UnrollingBase.scala:28:66") } // And(b2375,b2368)
    val x6694 = withCtrl(x6700) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x6694").srcCtx("UnrollingBase.scala:28:66") } // And(b2015,b1969)
    val x6695 = withCtrl(x6700) { OpDef(op=BitAnd, inputs=List(x6693, x6694)).name("x6695").srcCtx("UnrollingBase.scala:28:66") } // And(x6693,x6694)
    val x6696 = withCtrl(x6700) { LoadBanks(List(x6388_d0_b12), List(b1999, b2366, b2374)).name("x6696").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1999, b2366, b2374),x6695)
    val x6697 = withCtrl(x6700) { ReadMem(x6438_d0).name("x6697").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6438)
    val x6698 = withCtrl(x6700) { OpDef(op=FixMul, inputs=List(x6696, x6697)).name("x6698").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6696,x6697)
    val x6699 = withCtrl(x6700) { StoreBanks(List(List(x6422_d0_b0)), List(b2366, b2374), x6698).name("x6699").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6422,List(List(b2366, b2374)),List(x6698),List(x6695))
    val x6701 = withCtrl(x6711) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6701").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6702 = withCtrl(x6711) { CounterChain(List(x6701)).name("x6702").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6701))
    val x6710 = withCtrl(x6711) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6702).name("x6710").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2369, b2015, b1969),x6702,Block(Const(())),List(List(b2384)),List(List(b2385)))
    val b2384 = withCtrl(x6710) { CounterIter(x6701, None).name("b2384") } // b2384
    val b2385 = withCtrl(x6710) { Const(true).name("b2385") } // b2385
    val x6703 = withCtrl(x6710) { OpDef(op=BitAnd, inputs=List(b2385, b2369)).name("x6703").srcCtx("UnrollingBase.scala:28:66") } // And(b2385,b2369)
    val x6704 = withCtrl(x6710) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x6704").srcCtx("UnrollingBase.scala:28:66") } // And(b2015,b1969)
    val x6705 = withCtrl(x6710) { OpDef(op=BitAnd, inputs=List(x6703, x6704)).name("x6705").srcCtx("UnrollingBase.scala:28:66") } // And(x6703,x6704)
    val x6706 = withCtrl(x6710) { LoadBanks(List(x6388_d0_b13), List(b1999, b2367, b2384)).name("x6706").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b1999, b2367, b2384),x6705)
    val x6707 = withCtrl(x6710) { ReadMem(x6438_d1).name("x6707").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6438)
    val x6708 = withCtrl(x6710) { OpDef(op=FixMul, inputs=List(x6706, x6707)).name("x6708").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6706,x6707)
    val x6709 = withCtrl(x6710) { StoreBanks(List(List(x6422_d0_b1)), List(b2367, b2384), x6708).name("x6709").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6422,List(List(b2367, b2384)),List(x6708),List(x6705))
    val x6713 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6713").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6714 = withCtrl(x6929) { CounterChain(List(x6713)).name("x6714").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6713))
    val x6736 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6714).name("x6736").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2016, b1969),x6714,Block(Const(())),List(List(b2396, b2397)),List(List(b2398, b2399)))
    val b2396 = withCtrl(x6736) { CounterIter(x6713, Some(0)).name("b2396") } // b2396
    val b2398 = withCtrl(x6736) { Const(true).name("b2398") } // b2398
    val b2397 = withCtrl(x6736) { CounterIter(x6713, Some(1)).name("b2397") } // b2397
    val b2399 = withCtrl(x6736) { Const(true).name("b2399") } // b2399
    val x6735 = withCtrl(x6736) { UnitController(style=ForkJoin, level=OuterControl).name("x6735").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2016, b1969),Block(Const(())))
    val x6715 = withCtrl(x6735) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6715").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6716 = withCtrl(x6735) { CounterChain(List(x6715)).name("x6716").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6715))
    val x6724 = withCtrl(x6735) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6716).name("x6724").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2398, b2016, b1969),x6716,Block(Const(())),List(List(b2404)),List(List(b2405)))
    val b2404 = withCtrl(x6724) { CounterIter(x6715, None).name("b2404") } // b2404
    val b2405 = withCtrl(x6724) { Const(true).name("b2405") } // b2405
    val x6717 = withCtrl(x6724) { OpDef(op=BitAnd, inputs=List(b2405, b2398)).name("x6717").srcCtx("UnrollingBase.scala:28:66") } // And(b2405,b2398)
    val x6718 = withCtrl(x6724) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x6718").srcCtx("UnrollingBase.scala:28:66") } // And(b2016,b1969)
    val x6719 = withCtrl(x6724) { OpDef(op=BitAnd, inputs=List(x6717, x6718)).name("x6719").srcCtx("UnrollingBase.scala:28:66") } // And(x6717,x6718)
    val x6720 = withCtrl(x6724) { LoadBanks(List(x6388_d0_b14), List(b2000, b2396, b2404)).name("x6720").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2000, b2396, b2404),x6719)
    val x6721 = withCtrl(x6724) { ReadMem(x6439_d0).name("x6721").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6439)
    val x6722 = withCtrl(x6724) { OpDef(op=FixMul, inputs=List(x6720, x6721)).name("x6722").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6720,x6721)
    val x6723 = withCtrl(x6724) { StoreBanks(List(List(x6423_d0_b0)), List(b2396, b2404), x6722).name("x6723").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6423,List(List(b2396, b2404)),List(x6722),List(x6719))
    val x6725 = withCtrl(x6735) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6725").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6726 = withCtrl(x6735) { CounterChain(List(x6725)).name("x6726").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6725))
    val x6734 = withCtrl(x6735) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6726).name("x6734").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2399, b2016, b1969),x6726,Block(Const(())),List(List(b2414)),List(List(b2415)))
    val b2414 = withCtrl(x6734) { CounterIter(x6725, None).name("b2414") } // b2414
    val b2415 = withCtrl(x6734) { Const(true).name("b2415") } // b2415
    val x6727 = withCtrl(x6734) { OpDef(op=BitAnd, inputs=List(b2415, b2399)).name("x6727").srcCtx("UnrollingBase.scala:28:66") } // And(b2415,b2399)
    val x6728 = withCtrl(x6734) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x6728").srcCtx("UnrollingBase.scala:28:66") } // And(b2016,b1969)
    val x6729 = withCtrl(x6734) { OpDef(op=BitAnd, inputs=List(x6727, x6728)).name("x6729").srcCtx("UnrollingBase.scala:28:66") } // And(x6727,x6728)
    val x6730 = withCtrl(x6734) { LoadBanks(List(x6388_d0_b15), List(b2000, b2397, b2414)).name("x6730").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2000, b2397, b2414),x6729)
    val x6731 = withCtrl(x6734) { ReadMem(x6439_d1).name("x6731").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6439)
    val x6732 = withCtrl(x6734) { OpDef(op=FixMul, inputs=List(x6730, x6731)).name("x6732").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6730,x6731)
    val x6733 = withCtrl(x6734) { StoreBanks(List(List(x6423_d0_b1)), List(b2397, b2414), x6732).name("x6733").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6423,List(List(b2397, b2414)),List(x6732),List(x6729))
    val x6737 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6737").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6738 = withCtrl(x6929) { CounterChain(List(x6737)).name("x6738").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6737))
    val x6760 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6738).name("x6760").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2017, b1969),x6738,Block(Const(())),List(List(b2426, b2427)),List(List(b2428, b2429)))
    val b2426 = withCtrl(x6760) { CounterIter(x6737, Some(0)).name("b2426") } // b2426
    val b2428 = withCtrl(x6760) { Const(true).name("b2428") } // b2428
    val b2427 = withCtrl(x6760) { CounterIter(x6737, Some(1)).name("b2427") } // b2427
    val b2429 = withCtrl(x6760) { Const(true).name("b2429") } // b2429
    val x6759 = withCtrl(x6760) { UnitController(style=ForkJoin, level=OuterControl).name("x6759").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2017, b1969),Block(Const(())))
    val x6739 = withCtrl(x6759) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6739").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6740 = withCtrl(x6759) { CounterChain(List(x6739)).name("x6740").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6739))
    val x6748 = withCtrl(x6759) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6740).name("x6748").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2428, b2017, b1969),x6740,Block(Const(())),List(List(b2434)),List(List(b2435)))
    val b2434 = withCtrl(x6748) { CounterIter(x6739, None).name("b2434") } // b2434
    val b2435 = withCtrl(x6748) { Const(true).name("b2435") } // b2435
    val x6741 = withCtrl(x6748) { OpDef(op=BitAnd, inputs=List(b2435, b2428)).name("x6741").srcCtx("UnrollingBase.scala:28:66") } // And(b2435,b2428)
    val x6742 = withCtrl(x6748) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x6742").srcCtx("UnrollingBase.scala:28:66") } // And(b2017,b1969)
    val x6743 = withCtrl(x6748) { OpDef(op=BitAnd, inputs=List(x6741, x6742)).name("x6743").srcCtx("UnrollingBase.scala:28:66") } // And(x6741,x6742)
    val x6744 = withCtrl(x6748) { LoadBanks(List(x6388_d0_b16), List(b2001, b2426, b2434)).name("x6744").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2001, b2426, b2434),x6743)
    val x6745 = withCtrl(x6748) { ReadMem(x6440_d0).name("x6745").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6440)
    val x6746 = withCtrl(x6748) { OpDef(op=FixMul, inputs=List(x6744, x6745)).name("x6746").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6744,x6745)
    val x6747 = withCtrl(x6748) { StoreBanks(List(List(x6424_d0_b0)), List(b2426, b2434), x6746).name("x6747").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6424,List(List(b2426, b2434)),List(x6746),List(x6743))
    val x6749 = withCtrl(x6759) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6749").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6750 = withCtrl(x6759) { CounterChain(List(x6749)).name("x6750").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6749))
    val x6758 = withCtrl(x6759) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6750).name("x6758").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2429, b2017, b1969),x6750,Block(Const(())),List(List(b2444)),List(List(b2445)))
    val b2444 = withCtrl(x6758) { CounterIter(x6749, None).name("b2444") } // b2444
    val b2445 = withCtrl(x6758) { Const(true).name("b2445") } // b2445
    val x6751 = withCtrl(x6758) { OpDef(op=BitAnd, inputs=List(b2445, b2429)).name("x6751").srcCtx("UnrollingBase.scala:28:66") } // And(b2445,b2429)
    val x6752 = withCtrl(x6758) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x6752").srcCtx("UnrollingBase.scala:28:66") } // And(b2017,b1969)
    val x6753 = withCtrl(x6758) { OpDef(op=BitAnd, inputs=List(x6751, x6752)).name("x6753").srcCtx("UnrollingBase.scala:28:66") } // And(x6751,x6752)
    val x6754 = withCtrl(x6758) { LoadBanks(List(x6388_d0_b17), List(b2001, b2427, b2444)).name("x6754").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2001, b2427, b2444),x6753)
    val x6755 = withCtrl(x6758) { ReadMem(x6440_d1).name("x6755").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6440)
    val x6756 = withCtrl(x6758) { OpDef(op=FixMul, inputs=List(x6754, x6755)).name("x6756").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6754,x6755)
    val x6757 = withCtrl(x6758) { StoreBanks(List(List(x6424_d0_b1)), List(b2427, b2444), x6756).name("x6757").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6424,List(List(b2427, b2444)),List(x6756),List(x6753))
    val x6761 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6761").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6762 = withCtrl(x6929) { CounterChain(List(x6761)).name("x6762").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6761))
    val x6784 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6762).name("x6784").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2018, b1969),x6762,Block(Const(())),List(List(b2456, b2457)),List(List(b2458, b2459)))
    val b2456 = withCtrl(x6784) { CounterIter(x6761, Some(0)).name("b2456") } // b2456
    val b2458 = withCtrl(x6784) { Const(true).name("b2458") } // b2458
    val b2457 = withCtrl(x6784) { CounterIter(x6761, Some(1)).name("b2457") } // b2457
    val b2459 = withCtrl(x6784) { Const(true).name("b2459") } // b2459
    val x6783 = withCtrl(x6784) { UnitController(style=ForkJoin, level=OuterControl).name("x6783").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2018, b1969),Block(Const(())))
    val x6763 = withCtrl(x6783) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6763").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6764 = withCtrl(x6783) { CounterChain(List(x6763)).name("x6764").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6763))
    val x6772 = withCtrl(x6783) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6764).name("x6772").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2458, b2018, b1969),x6764,Block(Const(())),List(List(b2464)),List(List(b2465)))
    val b2464 = withCtrl(x6772) { CounterIter(x6763, None).name("b2464") } // b2464
    val b2465 = withCtrl(x6772) { Const(true).name("b2465") } // b2465
    val x6765 = withCtrl(x6772) { OpDef(op=BitAnd, inputs=List(b2465, b2458)).name("x6765").srcCtx("UnrollingBase.scala:28:66") } // And(b2465,b2458)
    val x6766 = withCtrl(x6772) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x6766").srcCtx("UnrollingBase.scala:28:66") } // And(b2018,b1969)
    val x6767 = withCtrl(x6772) { OpDef(op=BitAnd, inputs=List(x6765, x6766)).name("x6767").srcCtx("UnrollingBase.scala:28:66") } // And(x6765,x6766)
    val x6768 = withCtrl(x6772) { LoadBanks(List(x6388_d0_b18), List(b2002, b2456, b2464)).name("x6768").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2002, b2456, b2464),x6767)
    val x6769 = withCtrl(x6772) { ReadMem(x6441_d0).name("x6769").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6441)
    val x6770 = withCtrl(x6772) { OpDef(op=FixMul, inputs=List(x6768, x6769)).name("x6770").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6768,x6769)
    val x6771 = withCtrl(x6772) { StoreBanks(List(List(x6425_d0_b0)), List(b2456, b2464), x6770).name("x6771").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6425,List(List(b2456, b2464)),List(x6770),List(x6767))
    val x6773 = withCtrl(x6783) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6773").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6774 = withCtrl(x6783) { CounterChain(List(x6773)).name("x6774").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6773))
    val x6782 = withCtrl(x6783) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6774).name("x6782").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2459, b2018, b1969),x6774,Block(Const(())),List(List(b2474)),List(List(b2475)))
    val b2474 = withCtrl(x6782) { CounterIter(x6773, None).name("b2474") } // b2474
    val b2475 = withCtrl(x6782) { Const(true).name("b2475") } // b2475
    val x6775 = withCtrl(x6782) { OpDef(op=BitAnd, inputs=List(b2475, b2459)).name("x6775").srcCtx("UnrollingBase.scala:28:66") } // And(b2475,b2459)
    val x6776 = withCtrl(x6782) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x6776").srcCtx("UnrollingBase.scala:28:66") } // And(b2018,b1969)
    val x6777 = withCtrl(x6782) { OpDef(op=BitAnd, inputs=List(x6775, x6776)).name("x6777").srcCtx("UnrollingBase.scala:28:66") } // And(x6775,x6776)
    val x6778 = withCtrl(x6782) { LoadBanks(List(x6388_d0_b19), List(b2002, b2457, b2474)).name("x6778").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2002, b2457, b2474),x6777)
    val x6779 = withCtrl(x6782) { ReadMem(x6441_d1).name("x6779").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6441)
    val x6780 = withCtrl(x6782) { OpDef(op=FixMul, inputs=List(x6778, x6779)).name("x6780").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6778,x6779)
    val x6781 = withCtrl(x6782) { StoreBanks(List(List(x6425_d0_b1)), List(b2457, b2474), x6780).name("x6781").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6425,List(List(b2457, b2474)),List(x6780),List(x6777))
    val x6785 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6785").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6786 = withCtrl(x6929) { CounterChain(List(x6785)).name("x6786").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6785))
    val x6808 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6786).name("x6808").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2019, b1969),x6786,Block(Const(())),List(List(b2486, b2487)),List(List(b2488, b2489)))
    val b2486 = withCtrl(x6808) { CounterIter(x6785, Some(0)).name("b2486") } // b2486
    val b2488 = withCtrl(x6808) { Const(true).name("b2488") } // b2488
    val b2487 = withCtrl(x6808) { CounterIter(x6785, Some(1)).name("b2487") } // b2487
    val b2489 = withCtrl(x6808) { Const(true).name("b2489") } // b2489
    val x6807 = withCtrl(x6808) { UnitController(style=ForkJoin, level=OuterControl).name("x6807").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2019, b1969),Block(Const(())))
    val x6787 = withCtrl(x6807) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6787").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6788 = withCtrl(x6807) { CounterChain(List(x6787)).name("x6788").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6787))
    val x6796 = withCtrl(x6807) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6788).name("x6796").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2488, b2019, b1969),x6788,Block(Const(())),List(List(b2494)),List(List(b2495)))
    val b2494 = withCtrl(x6796) { CounterIter(x6787, None).name("b2494") } // b2494
    val b2495 = withCtrl(x6796) { Const(true).name("b2495") } // b2495
    val x6789 = withCtrl(x6796) { OpDef(op=BitAnd, inputs=List(b2495, b2488)).name("x6789").srcCtx("UnrollingBase.scala:28:66") } // And(b2495,b2488)
    val x6790 = withCtrl(x6796) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x6790").srcCtx("UnrollingBase.scala:28:66") } // And(b2019,b1969)
    val x6791 = withCtrl(x6796) { OpDef(op=BitAnd, inputs=List(x6789, x6790)).name("x6791").srcCtx("UnrollingBase.scala:28:66") } // And(x6789,x6790)
    val x6792 = withCtrl(x6796) { LoadBanks(List(x6388_d0_b20), List(b2003, b2486, b2494)).name("x6792").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2003, b2486, b2494),x6791)
    val x6793 = withCtrl(x6796) { ReadMem(x6442_d0).name("x6793").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6442)
    def split2 = {
    val x6794 = withCtrl(x6796) { OpDef(op=FixMul, inputs=List(x6792, x6793)).name("x6794").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6792,x6793)
    val x6795 = withCtrl(x6796) { StoreBanks(List(List(x6426_d0_b0)), List(b2486, b2494), x6794).name("x6795").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6426,List(List(b2486, b2494)),List(x6794),List(x6791))
    val x6797 = withCtrl(x6807) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6797").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6798 = withCtrl(x6807) { CounterChain(List(x6797)).name("x6798").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6797))
    val x6806 = withCtrl(x6807) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6798).name("x6806").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2489, b2019, b1969),x6798,Block(Const(())),List(List(b2504)),List(List(b2505)))
    val b2504 = withCtrl(x6806) { CounterIter(x6797, None).name("b2504") } // b2504
    val b2505 = withCtrl(x6806) { Const(true).name("b2505") } // b2505
    val x6799 = withCtrl(x6806) { OpDef(op=BitAnd, inputs=List(b2505, b2489)).name("x6799").srcCtx("UnrollingBase.scala:28:66") } // And(b2505,b2489)
    val x6800 = withCtrl(x6806) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x6800").srcCtx("UnrollingBase.scala:28:66") } // And(b2019,b1969)
    val x6801 = withCtrl(x6806) { OpDef(op=BitAnd, inputs=List(x6799, x6800)).name("x6801").srcCtx("UnrollingBase.scala:28:66") } // And(x6799,x6800)
    val x6802 = withCtrl(x6806) { LoadBanks(List(x6388_d0_b21), List(b2003, b2487, b2504)).name("x6802").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2003, b2487, b2504),x6801)
    val x6803 = withCtrl(x6806) { ReadMem(x6442_d1).name("x6803").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6442)
    val x6804 = withCtrl(x6806) { OpDef(op=FixMul, inputs=List(x6802, x6803)).name("x6804").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6802,x6803)
    val x6805 = withCtrl(x6806) { StoreBanks(List(List(x6426_d0_b1)), List(b2487, b2504), x6804).name("x6805").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6426,List(List(b2487, b2504)),List(x6804),List(x6801))
    val x6809 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6809").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6810 = withCtrl(x6929) { CounterChain(List(x6809)).name("x6810").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6809))
    val x6832 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6810).name("x6832").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2020, b1969),x6810,Block(Const(())),List(List(b2516, b2517)),List(List(b2518, b2519)))
    val b2516 = withCtrl(x6832) { CounterIter(x6809, Some(0)).name("b2516") } // b2516
    val b2518 = withCtrl(x6832) { Const(true).name("b2518") } // b2518
    val b2517 = withCtrl(x6832) { CounterIter(x6809, Some(1)).name("b2517") } // b2517
    val b2519 = withCtrl(x6832) { Const(true).name("b2519") } // b2519
    val x6831 = withCtrl(x6832) { UnitController(style=ForkJoin, level=OuterControl).name("x6831").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2020, b1969),Block(Const(())))
    val x6811 = withCtrl(x6831) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6811").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6812 = withCtrl(x6831) { CounterChain(List(x6811)).name("x6812").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6811))
    val x6820 = withCtrl(x6831) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6812).name("x6820").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2518, b2020, b1969),x6812,Block(Const(())),List(List(b2524)),List(List(b2525)))
    val b2524 = withCtrl(x6820) { CounterIter(x6811, None).name("b2524") } // b2524
    val b2525 = withCtrl(x6820) { Const(true).name("b2525") } // b2525
    val x6813 = withCtrl(x6820) { OpDef(op=BitAnd, inputs=List(b2525, b2518)).name("x6813").srcCtx("UnrollingBase.scala:28:66") } // And(b2525,b2518)
    val x6814 = withCtrl(x6820) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x6814").srcCtx("UnrollingBase.scala:28:66") } // And(b2020,b1969)
    val x6815 = withCtrl(x6820) { OpDef(op=BitAnd, inputs=List(x6813, x6814)).name("x6815").srcCtx("UnrollingBase.scala:28:66") } // And(x6813,x6814)
    val x6816 = withCtrl(x6820) { LoadBanks(List(x6388_d0_b22), List(b2004, b2516, b2524)).name("x6816").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2004, b2516, b2524),x6815)
    val x6817 = withCtrl(x6820) { ReadMem(x6443_d0).name("x6817").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6443)
    val x6818 = withCtrl(x6820) { OpDef(op=FixMul, inputs=List(x6816, x6817)).name("x6818").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6816,x6817)
    val x6819 = withCtrl(x6820) { StoreBanks(List(List(x6427_d0_b0)), List(b2516, b2524), x6818).name("x6819").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6427,List(List(b2516, b2524)),List(x6818),List(x6815))
    val x6821 = withCtrl(x6831) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6821").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6822 = withCtrl(x6831) { CounterChain(List(x6821)).name("x6822").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6821))
    val x6830 = withCtrl(x6831) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6822).name("x6830").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2519, b2020, b1969),x6822,Block(Const(())),List(List(b2534)),List(List(b2535)))
    val b2534 = withCtrl(x6830) { CounterIter(x6821, None).name("b2534") } // b2534
    val b2535 = withCtrl(x6830) { Const(true).name("b2535") } // b2535
    val x6823 = withCtrl(x6830) { OpDef(op=BitAnd, inputs=List(b2535, b2519)).name("x6823").srcCtx("UnrollingBase.scala:28:66") } // And(b2535,b2519)
    val x6824 = withCtrl(x6830) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x6824").srcCtx("UnrollingBase.scala:28:66") } // And(b2020,b1969)
    val x6825 = withCtrl(x6830) { OpDef(op=BitAnd, inputs=List(x6823, x6824)).name("x6825").srcCtx("UnrollingBase.scala:28:66") } // And(x6823,x6824)
    val x6826 = withCtrl(x6830) { LoadBanks(List(x6388_d0_b23), List(b2004, b2517, b2534)).name("x6826").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2004, b2517, b2534),x6825)
    val x6827 = withCtrl(x6830) { ReadMem(x6443_d1).name("x6827").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6443)
    val x6828 = withCtrl(x6830) { OpDef(op=FixMul, inputs=List(x6826, x6827)).name("x6828").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6826,x6827)
    val x6829 = withCtrl(x6830) { StoreBanks(List(List(x6427_d0_b1)), List(b2517, b2534), x6828).name("x6829").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6427,List(List(b2517, b2534)),List(x6828),List(x6825))
    val x6833 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6833").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6834 = withCtrl(x6929) { CounterChain(List(x6833)).name("x6834").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6833))
    val x6856 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6834).name("x6856").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2021, b1969),x6834,Block(Const(())),List(List(b2546, b2547)),List(List(b2548, b2549)))
    val b2546 = withCtrl(x6856) { CounterIter(x6833, Some(0)).name("b2546") } // b2546
    val b2548 = withCtrl(x6856) { Const(true).name("b2548") } // b2548
    val b2547 = withCtrl(x6856) { CounterIter(x6833, Some(1)).name("b2547") } // b2547
    val b2549 = withCtrl(x6856) { Const(true).name("b2549") } // b2549
    val x6855 = withCtrl(x6856) { UnitController(style=ForkJoin, level=OuterControl).name("x6855").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2021, b1969),Block(Const(())))
    val x6835 = withCtrl(x6855) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6835").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6836 = withCtrl(x6855) { CounterChain(List(x6835)).name("x6836").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6835))
    val x6844 = withCtrl(x6855) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6836).name("x6844").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2548, b2021, b1969),x6836,Block(Const(())),List(List(b2554)),List(List(b2555)))
    val b2554 = withCtrl(x6844) { CounterIter(x6835, None).name("b2554") } // b2554
    val b2555 = withCtrl(x6844) { Const(true).name("b2555") } // b2555
    val x6837 = withCtrl(x6844) { OpDef(op=BitAnd, inputs=List(b2555, b2548)).name("x6837").srcCtx("UnrollingBase.scala:28:66") } // And(b2555,b2548)
    val x6838 = withCtrl(x6844) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x6838").srcCtx("UnrollingBase.scala:28:66") } // And(b2021,b1969)
    val x6839 = withCtrl(x6844) { OpDef(op=BitAnd, inputs=List(x6837, x6838)).name("x6839").srcCtx("UnrollingBase.scala:28:66") } // And(x6837,x6838)
    val x6840 = withCtrl(x6844) { LoadBanks(List(x6388_d0_b24), List(b2005, b2546, b2554)).name("x6840").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2005, b2546, b2554),x6839)
    val x6841 = withCtrl(x6844) { ReadMem(x6444_d0).name("x6841").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6444)
    val x6842 = withCtrl(x6844) { OpDef(op=FixMul, inputs=List(x6840, x6841)).name("x6842").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6840,x6841)
    val x6843 = withCtrl(x6844) { StoreBanks(List(List(x6428_d0_b0)), List(b2546, b2554), x6842).name("x6843").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6428,List(List(b2546, b2554)),List(x6842),List(x6839))
    val x6845 = withCtrl(x6855) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6845").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6846 = withCtrl(x6855) { CounterChain(List(x6845)).name("x6846").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6845))
    val x6854 = withCtrl(x6855) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6846).name("x6854").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2549, b2021, b1969),x6846,Block(Const(())),List(List(b2564)),List(List(b2565)))
    val b2564 = withCtrl(x6854) { CounterIter(x6845, None).name("b2564") } // b2564
    val b2565 = withCtrl(x6854) { Const(true).name("b2565") } // b2565
    val x6847 = withCtrl(x6854) { OpDef(op=BitAnd, inputs=List(b2565, b2549)).name("x6847").srcCtx("UnrollingBase.scala:28:66") } // And(b2565,b2549)
    val x6848 = withCtrl(x6854) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x6848").srcCtx("UnrollingBase.scala:28:66") } // And(b2021,b1969)
    val x6849 = withCtrl(x6854) { OpDef(op=BitAnd, inputs=List(x6847, x6848)).name("x6849").srcCtx("UnrollingBase.scala:28:66") } // And(x6847,x6848)
    val x6850 = withCtrl(x6854) { LoadBanks(List(x6388_d0_b25), List(b2005, b2547, b2564)).name("x6850").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2005, b2547, b2564),x6849)
    val x6851 = withCtrl(x6854) { ReadMem(x6444_d1).name("x6851").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6444)
    val x6852 = withCtrl(x6854) { OpDef(op=FixMul, inputs=List(x6850, x6851)).name("x6852").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6850,x6851)
    val x6853 = withCtrl(x6854) { StoreBanks(List(List(x6428_d0_b1)), List(b2547, b2564), x6852).name("x6853").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6428,List(List(b2547, b2564)),List(x6852),List(x6849))
    val x6857 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6857").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6858 = withCtrl(x6929) { CounterChain(List(x6857)).name("x6858").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6857))
    val x6880 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6858).name("x6880").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2022, b1969),x6858,Block(Const(())),List(List(b2576, b2577)),List(List(b2578, b2579)))
    val b2576 = withCtrl(x6880) { CounterIter(x6857, Some(0)).name("b2576") } // b2576
    val b2578 = withCtrl(x6880) { Const(true).name("b2578") } // b2578
    val b2577 = withCtrl(x6880) { CounterIter(x6857, Some(1)).name("b2577") } // b2577
    val b2579 = withCtrl(x6880) { Const(true).name("b2579") } // b2579
    val x6879 = withCtrl(x6880) { UnitController(style=ForkJoin, level=OuterControl).name("x6879").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2022, b1969),Block(Const(())))
    val x6859 = withCtrl(x6879) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6859").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6860 = withCtrl(x6879) { CounterChain(List(x6859)).name("x6860").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6859))
    val x6868 = withCtrl(x6879) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6860).name("x6868").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2578, b2022, b1969),x6860,Block(Const(())),List(List(b2584)),List(List(b2585)))
    val b2584 = withCtrl(x6868) { CounterIter(x6859, None).name("b2584") } // b2584
    val b2585 = withCtrl(x6868) { Const(true).name("b2585") } // b2585
    val x6861 = withCtrl(x6868) { OpDef(op=BitAnd, inputs=List(b2585, b2578)).name("x6861").srcCtx("UnrollingBase.scala:28:66") } // And(b2585,b2578)
    val x6862 = withCtrl(x6868) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x6862").srcCtx("UnrollingBase.scala:28:66") } // And(b2022,b1969)
    val x6863 = withCtrl(x6868) { OpDef(op=BitAnd, inputs=List(x6861, x6862)).name("x6863").srcCtx("UnrollingBase.scala:28:66") } // And(x6861,x6862)
    val x6864 = withCtrl(x6868) { LoadBanks(List(x6388_d0_b26), List(b2006, b2576, b2584)).name("x6864").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2006, b2576, b2584),x6863)
    val x6865 = withCtrl(x6868) { ReadMem(x6445_d0).name("x6865").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6445)
    val x6866 = withCtrl(x6868) { OpDef(op=FixMul, inputs=List(x6864, x6865)).name("x6866").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6864,x6865)
    val x6867 = withCtrl(x6868) { StoreBanks(List(List(x6429_d0_b0)), List(b2576, b2584), x6866).name("x6867").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6429,List(List(b2576, b2584)),List(x6866),List(x6863))
    val x6869 = withCtrl(x6879) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6869").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6870 = withCtrl(x6879) { CounterChain(List(x6869)).name("x6870").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6869))
    val x6878 = withCtrl(x6879) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6870).name("x6878").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2579, b2022, b1969),x6870,Block(Const(())),List(List(b2594)),List(List(b2595)))
    val b2594 = withCtrl(x6878) { CounterIter(x6869, None).name("b2594") } // b2594
    val b2595 = withCtrl(x6878) { Const(true).name("b2595") } // b2595
    val x6871 = withCtrl(x6878) { OpDef(op=BitAnd, inputs=List(b2595, b2579)).name("x6871").srcCtx("UnrollingBase.scala:28:66") } // And(b2595,b2579)
    val x6872 = withCtrl(x6878) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x6872").srcCtx("UnrollingBase.scala:28:66") } // And(b2022,b1969)
    val x6873 = withCtrl(x6878) { OpDef(op=BitAnd, inputs=List(x6871, x6872)).name("x6873").srcCtx("UnrollingBase.scala:28:66") } // And(x6871,x6872)
    val x6874 = withCtrl(x6878) { LoadBanks(List(x6388_d0_b27), List(b2006, b2577, b2594)).name("x6874").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2006, b2577, b2594),x6873)
    val x6875 = withCtrl(x6878) { ReadMem(x6445_d1).name("x6875").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6445)
    val x6876 = withCtrl(x6878) { OpDef(op=FixMul, inputs=List(x6874, x6875)).name("x6876").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6874,x6875)
    val x6877 = withCtrl(x6878) { StoreBanks(List(List(x6429_d0_b1)), List(b2577, b2594), x6876).name("x6877").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6429,List(List(b2577, b2594)),List(x6876),List(x6873))
    val x6881 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6881").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6882 = withCtrl(x6929) { CounterChain(List(x6881)).name("x6882").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6881))
    val x6904 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6882).name("x6904").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2023, b1969),x6882,Block(Const(())),List(List(b2606, b2607)),List(List(b2608, b2609)))
    val b2606 = withCtrl(x6904) { CounterIter(x6881, Some(0)).name("b2606") } // b2606
    val b2608 = withCtrl(x6904) { Const(true).name("b2608") } // b2608
    val b2607 = withCtrl(x6904) { CounterIter(x6881, Some(1)).name("b2607") } // b2607
    val b2609 = withCtrl(x6904) { Const(true).name("b2609") } // b2609
    val x6903 = withCtrl(x6904) { UnitController(style=ForkJoin, level=OuterControl).name("x6903").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2023, b1969),Block(Const(())))
    val x6883 = withCtrl(x6903) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6883").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6884 = withCtrl(x6903) { CounterChain(List(x6883)).name("x6884").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6883))
    val x6892 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6884).name("x6892").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2608, b2023, b1969),x6884,Block(Const(())),List(List(b2614)),List(List(b2615)))
    val b2614 = withCtrl(x6892) { CounterIter(x6883, None).name("b2614") } // b2614
    val b2615 = withCtrl(x6892) { Const(true).name("b2615") } // b2615
    val x6885 = withCtrl(x6892) { OpDef(op=BitAnd, inputs=List(b2615, b2608)).name("x6885").srcCtx("UnrollingBase.scala:28:66") } // And(b2615,b2608)
    val x6886 = withCtrl(x6892) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x6886").srcCtx("UnrollingBase.scala:28:66") } // And(b2023,b1969)
    val x6887 = withCtrl(x6892) { OpDef(op=BitAnd, inputs=List(x6885, x6886)).name("x6887").srcCtx("UnrollingBase.scala:28:66") } // And(x6885,x6886)
    val x6888 = withCtrl(x6892) { LoadBanks(List(x6388_d0_b28), List(b2007, b2606, b2614)).name("x6888").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2007, b2606, b2614),x6887)
    val x6889 = withCtrl(x6892) { ReadMem(x6446_d0).name("x6889").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6446)
    val x6890 = withCtrl(x6892) { OpDef(op=FixMul, inputs=List(x6888, x6889)).name("x6890").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6888,x6889)
    val x6891 = withCtrl(x6892) { StoreBanks(List(List(x6430_d0_b0)), List(b2606, b2614), x6890).name("x6891").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6430,List(List(b2606, b2614)),List(x6890),List(x6887))
    val x6893 = withCtrl(x6903) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6893").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6894 = withCtrl(x6903) { CounterChain(List(x6893)).name("x6894").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6893))
    val x6902 = withCtrl(x6903) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6894).name("x6902").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2609, b2023, b1969),x6894,Block(Const(())),List(List(b2624)),List(List(b2625)))
    val b2624 = withCtrl(x6902) { CounterIter(x6893, None).name("b2624") } // b2624
    val b2625 = withCtrl(x6902) { Const(true).name("b2625") } // b2625
    val x6895 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b2625, b2609)).name("x6895").srcCtx("UnrollingBase.scala:28:66") } // And(b2625,b2609)
    val x6896 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x6896").srcCtx("UnrollingBase.scala:28:66") } // And(b2023,b1969)
    val x6897 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(x6895, x6896)).name("x6897").srcCtx("UnrollingBase.scala:28:66") } // And(x6895,x6896)
    val x6898 = withCtrl(x6902) { LoadBanks(List(x6388_d0_b29), List(b2007, b2607, b2624)).name("x6898").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2007, b2607, b2624),x6897)
    val x6899 = withCtrl(x6902) { ReadMem(x6446_d1).name("x6899").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6446)
    val x6900 = withCtrl(x6902) { OpDef(op=FixMul, inputs=List(x6898, x6899)).name("x6900").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6898,x6899)
    val x6901 = withCtrl(x6902) { StoreBanks(List(List(x6430_d0_b1)), List(b2607, b2624), x6900).name("x6901").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6430,List(List(b2607, b2624)),List(x6900),List(x6897))
    val x6905 = withCtrl(x6929) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x6905").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x6906 = withCtrl(x6929) { CounterChain(List(x6905)).name("x6906").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x6905))
    val x6928 = withCtrl(x6929) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6906).name("x6928").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2024, b1969),x6906,Block(Const(())),List(List(b2636, b2637)),List(List(b2638, b2639)))
    val b2636 = withCtrl(x6928) { CounterIter(x6905, Some(0)).name("b2636") } // b2636
    val b2638 = withCtrl(x6928) { Const(true).name("b2638") } // b2638
    val b2637 = withCtrl(x6928) { CounterIter(x6905, Some(1)).name("b2637") } // b2637
    val b2639 = withCtrl(x6928) { Const(true).name("b2639") } // b2639
    val x6927 = withCtrl(x6928) { UnitController(style=ForkJoin, level=OuterControl).name("x6927").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2024, b1969),Block(Const(())))
    val x6907 = withCtrl(x6927) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6907").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6908 = withCtrl(x6927) { CounterChain(List(x6907)).name("x6908").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6907))
    val x6916 = withCtrl(x6927) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6908).name("x6916").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2638, b2024, b1969),x6908,Block(Const(())),List(List(b2644)),List(List(b2645)))
    val b2644 = withCtrl(x6916) { CounterIter(x6907, None).name("b2644") } // b2644
    val b2645 = withCtrl(x6916) { Const(true).name("b2645") } // b2645
    val x6909 = withCtrl(x6916) { OpDef(op=BitAnd, inputs=List(b2645, b2638)).name("x6909").srcCtx("UnrollingBase.scala:28:66") } // And(b2645,b2638)
    val x6910 = withCtrl(x6916) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x6910").srcCtx("UnrollingBase.scala:28:66") } // And(b2024,b1969)
    val x6911 = withCtrl(x6916) { OpDef(op=BitAnd, inputs=List(x6909, x6910)).name("x6911").srcCtx("UnrollingBase.scala:28:66") } // And(x6909,x6910)
    val x6912 = withCtrl(x6916) { LoadBanks(List(x6388_d0_b30), List(b2008, b2636, b2644)).name("x6912").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2008, b2636, b2644),x6911)
    val x6913 = withCtrl(x6916) { ReadMem(x6447_d0).name("x6913").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6447)
    val x6914 = withCtrl(x6916) { OpDef(op=FixMul, inputs=List(x6912, x6913)).name("x6914").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6912,x6913)
    val x6915 = withCtrl(x6916) { StoreBanks(List(List(x6431_d0_b0)), List(b2636, b2644), x6914).name("x6915").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6431,List(List(b2636, b2644)),List(x6914),List(x6911))
    val x6917 = withCtrl(x6927) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6917").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6918 = withCtrl(x6927) { CounterChain(List(x6917)).name("x6918").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x6917))
    val x6926 = withCtrl(x6927) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6918).name("x6926").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2639, b2024, b1969),x6918,Block(Const(())),List(List(b2654)),List(List(b2655)))
    val b2654 = withCtrl(x6926) { CounterIter(x6917, None).name("b2654") } // b2654
    val b2655 = withCtrl(x6926) { Const(true).name("b2655") } // b2655
    val x6919 = withCtrl(x6926) { OpDef(op=BitAnd, inputs=List(b2655, b2639)).name("x6919").srcCtx("UnrollingBase.scala:28:66") } // And(b2655,b2639)
    val x6920 = withCtrl(x6926) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x6920").srcCtx("UnrollingBase.scala:28:66") } // And(b2024,b1969)
    val x6921 = withCtrl(x6926) { OpDef(op=BitAnd, inputs=List(x6919, x6920)).name("x6921").srcCtx("UnrollingBase.scala:28:66") } // And(x6919,x6920)
    val x6922 = withCtrl(x6926) { LoadBanks(List(x6388_d0_b31), List(b2008, b2637, b2654)).name("x6922").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x6388,List(b2008, b2637, b2654),x6921)
    val x6923 = withCtrl(x6926) { ReadMem(x6447_d1).name("x6923").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x6447)
    val x6924 = withCtrl(x6926) { OpDef(op=FixMul, inputs=List(x6922, x6923)).name("x6924").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x6922,x6923)
    val x6925 = withCtrl(x6926) { StoreBanks(List(List(x6431_d0_b1)), List(b2637, b2654), x6924).name("x6925").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x6431,List(List(b2637, b2654)),List(x6924),List(x6921))
    val x6930 = withCtrl(x7051) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x6930").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x6931 = withCtrl(x7051) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6931").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6932 = withCtrl(x7051) { CounterChain(List(x6931,x6930)).name("x6932").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(ArrayBuffer(x6931, x6930))
    val x7050 = withCtrl(x7051) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6932).name("x7050").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledForeach(List(),x6932,Block(Const(())),ArrayBuffer(List(b2667), List(b2668)),ArrayBuffer(List(b2669), List(b2670)))
    val b2667 = withCtrl(x7050) { CounterIter(x6931, Some(0)).name("b2667") } // b2667
    val b2669 = withCtrl(x7050) { Const(true).name("b2669") } // b2669
    val b2668 = withCtrl(x7050) { CounterIter(x6930, None).name("b2668") } // b2668
    val b2670 = withCtrl(x7050) { Const(true).name("b2670") } // b2670
    val x6933 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2669, b2670)).name("x6933").srcCtx("UnrollingBase.scala:28:66") } // And(b2669,b2670)
    val x6934 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6933, b1969)).name("x6934").srcCtx("UnrollingBase.scala:28:66") } // And(x6933,b1969)
    val x6935 = withCtrl(x7050) { LoadBanks(List(x6416_d0_b0, x6416_d0_b1), List(b2667, b2668)).name("x6935").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6416,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6936 = withCtrl(x7050) { x6935 } // VectorApply(x6935,0)
    val x6937 = withCtrl(x7050) { LoadBanks(List(x6417_d0_b0, x6417_d0_b1), List(b2667, b2668)).name("x6937").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6417,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6938 = withCtrl(x7050) { x6937 } // VectorApply(x6937,0)
    val x6939 = withCtrl(x7050) { LoadBanks(List(x6418_d0_b0, x6418_d0_b1), List(b2667, b2668)).name("x6939").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6418,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6940 = withCtrl(x7050) { x6939 } // VectorApply(x6939,0)
    val x6941 = withCtrl(x7050) { LoadBanks(List(x6419_d0_b0, x6419_d0_b1), List(b2667, b2668)).name("x6941").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6419,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6942 = withCtrl(x7050) { x6941 } // VectorApply(x6941,0)
    val x6943 = withCtrl(x7050) { LoadBanks(List(x6420_d0_b0, x6420_d0_b1), List(b2667, b2668)).name("x6943").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6420,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6944 = withCtrl(x7050) { x6943 } // VectorApply(x6943,0)
    val x6945 = withCtrl(x7050) { LoadBanks(List(x6421_d0_b0, x6421_d0_b1), List(b2667, b2668)).name("x6945").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6421,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6946 = withCtrl(x7050) { x6945 } // VectorApply(x6945,0)
    val x6947 = withCtrl(x7050) { LoadBanks(List(x6422_d0_b0, x6422_d0_b1), List(b2667, b2668)).name("x6947").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6422,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6948 = withCtrl(x7050) { x6947 } // VectorApply(x6947,0)
    val x6949 = withCtrl(x7050) { LoadBanks(List(x6423_d0_b0, x6423_d0_b1), List(b2667, b2668)).name("x6949").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6423,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6950 = withCtrl(x7050) { x6949 } // VectorApply(x6949,0)
    val x6951 = withCtrl(x7050) { LoadBanks(List(x6424_d0_b0, x6424_d0_b1), List(b2667, b2668)).name("x6951").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6424,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6952 = withCtrl(x7050) { x6951 } // VectorApply(x6951,0)
    val x6953 = withCtrl(x7050) { LoadBanks(List(x6425_d0_b0, x6425_d0_b1), List(b2667, b2668)).name("x6953").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6425,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6954 = withCtrl(x7050) { x6953 } // VectorApply(x6953,0)
    val x6955 = withCtrl(x7050) { LoadBanks(List(x6426_d0_b0, x6426_d0_b1), List(b2667, b2668)).name("x6955").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6426,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6956 = withCtrl(x7050) { x6955 } // VectorApply(x6955,0)
    val x6957 = withCtrl(x7050) { LoadBanks(List(x6427_d0_b0, x6427_d0_b1), List(b2667, b2668)).name("x6957").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6427,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6958 = withCtrl(x7050) { x6957 } // VectorApply(x6957,0)
    val x6959 = withCtrl(x7050) { LoadBanks(List(x6428_d0_b0, x6428_d0_b1), List(b2667, b2668)).name("x6959").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6428,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6960 = withCtrl(x7050) { x6959 } // VectorApply(x6959,0)
    val x6961 = withCtrl(x7050) { LoadBanks(List(x6429_d0_b0, x6429_d0_b1), List(b2667, b2668)).name("x6961").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6429,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6962 = withCtrl(x7050) { x6961 } // VectorApply(x6961,0)
    val x6963 = withCtrl(x7050) { LoadBanks(List(x6430_d0_b0, x6430_d0_b1), List(b2667, b2668)).name("x6963").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6430,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6964 = withCtrl(x7050) { x6963 } // VectorApply(x6963,0)
    val x6965 = withCtrl(x7050) { LoadBanks(List(x6431_d0_b0, x6431_d0_b1), List(b2667, b2668)).name("x6965").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6431,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6966 = withCtrl(x7050) { x6965 } // VectorApply(x6965,0)
    val x6967 = withCtrl(x7050) { LoadBanks(List(x6396_d1_b0), List(b2667, b2668)).name("x6967").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x6396,List(ArrayBuffer(b2667, b2668)),List(x6934))
    val x6968 = withCtrl(x7050) { x6967 } // VectorApply(x6967,0)
    val x6969 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2009, b1969)).name("x6969").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2009,b1969)
    val x6970 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2010, b1969)).name("x6970").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2010,b1969)
    val x6971 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2011, b1969)).name("x6971").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2011,b1969)
    val x6972 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2012, b1969)).name("x6972").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2012,b1969)
    val x6973 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2013, b1969)).name("x6973").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2013,b1969)
    val x6974 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2014, b1969)).name("x6974").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2014,b1969)
    val x6975 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2015, b1969)).name("x6975").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2015,b1969)
    val x6976 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2016, b1969)).name("x6976").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2016,b1969)
    val x6977 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2017, b1969)).name("x6977").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2017,b1969)
    val x6978 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2018, b1969)).name("x6978").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2018,b1969)
    val x6979 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2019, b1969)).name("x6979").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2019,b1969)
    val x6980 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2020, b1969)).name("x6980").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2020,b1969)
    val x6981 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2021, b1969)).name("x6981").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2021,b1969)
    val x6982 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2022, b1969)).name("x6982").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2022,b1969)
    val x6983 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2023, b1969)).name("x6983").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2023,b1969)
    val x6984 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(b2024, b1969)).name("x6984").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2024,b1969)
    val x6985 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6969, x6934)).name("x6985").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6969,x6934)
    val x6986 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6970, x6934)).name("x6986").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6970,x6934)
    val x6987 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6971, x6934)).name("x6987").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6971,x6934)
    val x6988 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6972, x6934)).name("x6988").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6972,x6934)
    val x6989 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6973, x6934)).name("x6989").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6973,x6934)
    val x6990 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6974, x6934)).name("x6990").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6974,x6934)
    val x6991 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6975, x6934)).name("x6991").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6975,x6934)
    val x6992 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6976, x6934)).name("x6992").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6976,x6934)
    val x6993 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6977, x6934)).name("x6993").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6977,x6934)
    val x6994 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6978, x6934)).name("x6994").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6978,x6934)
    val x6995 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6979, x6934)).name("x6995").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6979,x6934)
    val x6996 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6980, x6934)).name("x6996").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6980,x6934)
    val x6997 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6981, x6934)).name("x6997").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6981,x6934)
    val x6998 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6982, x6934)).name("x6998").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6982,x6934)
    val x6999 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6983, x6934)).name("x6999").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6983,x6934)
    val x7000 = withCtrl(x7050) { OpDef(op=BitAnd, inputs=List(x6984, x6934)).name("x7000").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x6984,x6934)
    val x7001 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6936, x6938)).name("x7001").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6936,x6938)
    val x7002 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6986, x7001, x6936)).name("x7002").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6986,x7001,x6936)
    val x7003 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6985, x6986)).name("x7003").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6985,x6986)
    val x7004 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6940, x6942)).name("x7004").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6940,x6942)
    val x7005 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6988, x7004, x6940)).name("x7005").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6988,x7004,x6940)
    val x7006 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6987, x6988)).name("x7006").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6987,x6988)
    val x7007 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6944, x6946)).name("x7007").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6944,x6946)
    val x7008 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6990, x7007, x6944)).name("x7008").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6990,x7007,x6944)
    val x7009 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6989, x6990)).name("x7009").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6989,x6990)
    val x7010 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6948, x6950)).name("x7010").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6948,x6950)
    val x7011 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6992, x7010, x6948)).name("x7011").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6992,x7010,x6948)
    val x7012 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6991, x6992)).name("x7012").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6991,x6992)
    val x7013 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6952, x6954)).name("x7013").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6952,x6954)
    val x7014 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6994, x7013, x6952)).name("x7014").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6994,x7013,x6952)
    val x7015 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6993, x6994)).name("x7015").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6993,x6994)
    val x7016 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6956, x6958)).name("x7016").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6956,x6958)
    val x7017 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6996, x7016, x6956)).name("x7017").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6996,x7016,x6956)
    val x7018 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6995, x6996)).name("x7018").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6995,x6996)
    val x7019 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6960, x6962)).name("x7019").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6960,x6962)
    val x7020 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x6998, x7019, x6960)).name("x7020").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x6998,x7019,x6960)
    val x7021 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6997, x6998)).name("x7021").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6997,x6998)
    val x7022 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x6964, x6966)).name("x7022").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x6964,x6966)
    val x7023 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7000, x7022, x6964)).name("x7023").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7000,x7022,x6964)
    val x7024 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x6999, x7000)).name("x7024").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x6999,x7000)
    val x7025 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7002, x7005)).name("x7025").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7002,x7005)
    val x7026 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7006, x7025, x7002)).name("x7026").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7006,x7025,x7002)
    val x7027 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7003, x7006)).name("x7027").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7003,x7006)
    val x7028 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7008, x7011)).name("x7028").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7008,x7011)
    val x7029 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7012, x7028, x7008)).name("x7029").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7012,x7028,x7008)
    val x7030 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7009, x7012)).name("x7030").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7009,x7012)
    val x7031 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7014, x7017)).name("x7031").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7014,x7017)
    val x7032 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7018, x7031, x7014)).name("x7032").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7018,x7031,x7014)
    val x7033 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7015, x7018)).name("x7033").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7015,x7018)
    val x7034 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7020, x7023)).name("x7034").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7020,x7023)
    val x7035 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7024, x7034, x7020)).name("x7035").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7024,x7034,x7020)
    val x7036 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7021, x7024)).name("x7036").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7021,x7024)
    val x7037 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7026, x7029)).name("x7037").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7026,x7029)
    val x7038 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7030, x7037, x7026)).name("x7038").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7030,x7037,x7026)
    val x7039 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7027, x7030)).name("x7039").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7027,x7030)
    val x7040 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7032, x7035)).name("x7040").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7032,x7035)
    val x7041 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7036, x7040, x7032)).name("x7041").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7036,x7040,x7032)
    val x7042 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7033, x7036)).name("x7042").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7033,x7036)
    val x7043 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7038, x7041)).name("x7043").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7038,x7041)
    val x7044 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7042, x7043, x7038)).name("x7044").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7042,x7043,x7038)
    val x7045 = withCtrl(x7050) { OpDef(op=BitOr, inputs=List(x7039, x7042)).name("x7045").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x7039,x7042)
    val x7046 = withCtrl(x7050) { OpDef(op=FixEql, inputs=List(b1993, Const(0))).name("x7046").srcCtx("VecMatMultBiasAdd.scala:179:6") } // FixEql(b1993,Const(0))
    val x7047 = withCtrl(x7050) { OpDef(op=FixAdd, inputs=List(x7044, x6968)).name("x7047").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x7044,x6968)
    val x7048 = withCtrl(x7050) { OpDef(op=MuxOp, inputs=List(x7046, x7044, x7047)).name("x7048").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x7046,x7044,x7047)
    val x7049 = withCtrl(x7050) { StoreBanks(List(List(x6396_d0_b0, x6396_d0_b1), List(x6396_d1_b0)), List(b2667, b2668), x7048).name("x7049").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMStore(x6396,List(ArrayBuffer(b2667, b2668)),List(x7048),List(x6934))
    antiDepsOf(x7049)=List(x6967)
    val x7052 = withCtrl(x7201) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x7052").srcCtx("VecMatMultBiasAdd.scala:181:26") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x7053 = withCtrl(x7201) { CounterChain(List(x7052)).name("x7053").srcCtx("VecMatMultBiasAdd.scala:181:42") } // CounterChainNew(List(x7052))
    val x7089 = withCtrl(x7201) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7053).name("x7089").srcCtx("VecMatMultBiasAdd.scala:181:42") } // UnrolledForeach(List(b1969),x7053,Block(Const(())),List(List(b2792, b2793)),List(List(b2794, b2795)))
    val b2792 = withCtrl(x7089) { CounterIter(x7052, Some(0)).name("b2792") } // b2792
    val b2794 = withCtrl(x7089) { Const(true).name("b2794") } // b2794
    val b2793 = withCtrl(x7089) { CounterIter(x7052, Some(1)).name("b2793") } // b2793
    val b2795 = withCtrl(x7089) { Const(true).name("b2795") } // b2795
    val x7088 = withCtrl(x7089) { UnitController(style=ForkJoin, level=OuterControl).name("x7088").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x7054 = withCtrl(x7088) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7054").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7055 = withCtrl(x7088) { CounterChain(List(x7054)).name("x7055").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x7054))
    val x7070 = withCtrl(x7088) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7055).name("x7070").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2794, b1969),x7055,Block(Const(())),List(List(b2800)),List(List(b2801)))
    val b2800 = withCtrl(x7070) { CounterIter(x7054, None).name("b2800") } // b2800
    val b2801 = withCtrl(x7070) { Const(true).name("b2801") } // b2801
    val x7056 = withCtrl(x7070) { ReadMem(x6401_d0).name("x7056").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6401)
    val x7057 = withCtrl(x7070) { OpDef(op=BitAnd, inputs=List(b2801, b2794)).name("x7057").srcCtx("UnrollingBase.scala:28:66") } // And(b2801,b2794)
    val x7058 = withCtrl(x7070) { OpDef(op=BitAnd, inputs=List(x7057, b1969)).name("x7058").srcCtx("UnrollingBase.scala:28:66") } // And(x7057,b1969)
    val x7059 = withCtrl(x7070) { LoadBanks(List(x6389_d0_b0), List(x7056, b2792, b2800)).name("x7059").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x6389,List(x7056, b2792, b2800),x7058)
    val x7060 = withCtrl(x7070) { LoadBanks(List(x6390_d0_b0), List(x7056, b2792, b2800)).name("x7060").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x6390,List(x7056, b2792, b2800),x7058)
    val x7061 = withCtrl(x7070) { LoadBanks(List(x6391_d0_b0), List(x7056, b2792, b2800)).name("x7061").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x6391,List(x7056, b2792, b2800),x7058)
    val x7062 = withCtrl(x7070) { ReadMem(x6400_d0).name("x7062").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6400)
    val x7063 = withCtrl(x7070) { OpDef(op=MuxOp, inputs=List(x7062, x7061, x7060)).name("x7063").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x7062,x7061,x7060)
    val x7064 = withCtrl(x7070) { ReadMem(x6399_d0).name("x7064").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6399)
    val x7065 = withCtrl(x7070) { OpDef(op=MuxOp, inputs=List(x7064, x7059, x7063)).name("x7065").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x7064,x7059,x7063)
    val x7066 = withCtrl(x7070) { LoadBanks(List(x6396_d0_b0), List(b2792, b2800)).name("x7066").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x6396,List(List(b2792, b2800)),List(x7058))
    val x7067 = withCtrl(x7070) { x7066 } // VectorApply(x7066,0)
    val x7068 = withCtrl(x7070) { OpDef(op=FixAdd, inputs=List(x7067, x7065)).name("x7068").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x7067,x7065)
    val x7069 = withCtrl(x7070) { StoreBanks(List(List(x6397_d0_b0)), List(b2792, b2800), x7068).name("x7069").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x6397,List(List(b2792, b2800)),List(x7068),List(x7058))
    val x7071 = withCtrl(x7088) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7071").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7072 = withCtrl(x7088) { CounterChain(List(x7071)).name("x7072").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x7071))
    val x7087 = withCtrl(x7088) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7072).name("x7087").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2795, b1969),x7072,Block(Const(())),List(List(b2817)),List(List(b2818)))
    val b2817 = withCtrl(x7087) { CounterIter(x7071, None).name("b2817") } // b2817
    val b2818 = withCtrl(x7087) { Const(true).name("b2818") } // b2818
    val x7073 = withCtrl(x7087) { ReadMem(x6401_d1).name("x7073").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6401)
    val x7074 = withCtrl(x7087) { OpDef(op=BitAnd, inputs=List(b2818, b2795)).name("x7074").srcCtx("UnrollingBase.scala:28:66") } // And(b2818,b2795)
    val x7075 = withCtrl(x7087) { OpDef(op=BitAnd, inputs=List(x7074, b1969)).name("x7075").srcCtx("UnrollingBase.scala:28:66") } // And(x7074,b1969)
    val x7076 = withCtrl(x7087) { LoadBanks(List(x6389_d0_b1), List(x7073, b2793, b2817)).name("x7076").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x6389,List(x7073, b2793, b2817),x7075)
    val x7077 = withCtrl(x7087) { LoadBanks(List(x6390_d0_b1), List(x7073, b2793, b2817)).name("x7077").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x6390,List(x7073, b2793, b2817),x7075)
    val x7078 = withCtrl(x7087) { LoadBanks(List(x6391_d0_b1), List(x7073, b2793, b2817)).name("x7078").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x6391,List(x7073, b2793, b2817),x7075)
    val x7079 = withCtrl(x7087) { ReadMem(x6400_d1).name("x7079").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6400)
    val x7080 = withCtrl(x7087) { OpDef(op=MuxOp, inputs=List(x7079, x7078, x7077)).name("x7080").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x7079,x7078,x7077)
    val x7081 = withCtrl(x7087) { ReadMem(x6399_d1).name("x7081").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6399)
    val x7082 = withCtrl(x7087) { OpDef(op=MuxOp, inputs=List(x7081, x7076, x7080)).name("x7082").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x7081,x7076,x7080)
    val x7083 = withCtrl(x7087) { LoadBanks(List(x6396_d0_b1), List(b2793, b2817)).name("x7083").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x6396,List(List(b2793, b2817)),List(x7075))
    val x7084 = withCtrl(x7087) { x7083 } // VectorApply(x7083,0)
    val x7085 = withCtrl(x7087) { OpDef(op=FixAdd, inputs=List(x7084, x7082)).name("x7085").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x7084,x7082)
    val x7086 = withCtrl(x7087) { StoreBanks(List(List(x6397_d0_b1)), List(b2793, b2817), x7085).name("x7086").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x6397,List(List(b2793, b2817)),List(x7085),List(x7075))
    val x7090 = withCtrl(x7201) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7090").srcCtx("GateMetaPipe.scala:143:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7091 = withCtrl(x7201) { CounterChain(List(x7090)).name("x7091").srcCtx("GateMetaPipe.scala:143:27") } // CounterChainNew(List(x7090))
    val x7200 = withCtrl(x7201) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7091).name("x7200").srcCtx("GateMetaPipe.scala:143:27") } // UnrolledForeach(List(b1969),x7091,Block(Const(())),List(List(b2838)),List(List(b2839)))
    val b2838 = withCtrl(x7200) { CounterIter(x7090, Some(0)).name("b2838") } // b2838
    val b2839 = withCtrl(x7200) { Const(true).name("b2839") } // b2839
    val x7092 = withCtrl(x7200) { FIFO(size=64).name("x7092").srcCtx("GateMetaPipe.scala:144:25:sigQ") } // x7092 = FIFONew(Const(64))
    isAccum(x7092) = false
    bufferDepthOf(x7092) = 2
    val x7093 = withCtrl(x7200) { FIFO(size=64).name("x7093").srcCtx("GateMetaPipe.scala:145:26:sigQQ") } // x7093 = FIFONew(Const(64))
    isAccum(x7093) = false
    bufferDepthOf(x7093) = 2
    val x7094 = withCtrl(x7200) { FIFO(size=64).name("x7094").srcCtx("GateMetaPipe.scala:146:31:sigEleMuxQ") } // x7094 = FIFONew(Const(64))
    isAccum(x7094) = false
    bufferDepthOf(x7094) = 2
    val x7095 = withCtrl(x7200) { FIFO(size=64).name("x7095").srcCtx("GateMetaPipe.scala:147:27:tanhQ") } // x7095 = FIFONew(Const(64))
    isAccum(x7095) = false
    bufferDepthOf(x7095) = 2
    val x7096 = withCtrl(x7200) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7096").srcCtx("GateMetaPipe.scala:149:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7097 = withCtrl(x7200) { CounterChain(List(x7096)).name("x7097").srcCtx("GateMetaPipe.scala:149:48") } // CounterChainNew(List(x7096))
    val x7119 = withCtrl(x7200) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7097).name("x7119").srcCtx("GateMetaPipe.scala:149:48") } // UnrolledForeach(List(b2839, b1969),x7097,Block(Const(())),List(List(b2846)),List(List(b2847)))
    val b2846 = withCtrl(x7119) { CounterIter(x7096, None).name("b2846") } // b2846
    val b2847 = withCtrl(x7119) { Const(true).name("b2847") } // b2847
    val x7098 = withCtrl(x7119) { OpDef(op=BitAnd, inputs=List(b2847, b2839)).name("x7098").srcCtx("UnrollingBase.scala:28:66") } // And(b2847,b2839)
    val x7099 = withCtrl(x7119) { OpDef(op=BitAnd, inputs=List(x7098, b1969)).name("x7099").srcCtx("UnrollingBase.scala:28:66") } // And(x7098,b1969)
    val x7100 = withCtrl(x7119) { LoadBanks(List(x6397_d0_b0, x6397_d0_b1), List(b2838, b2846)).name("x7100").srcCtx("GateMetaPipe.scala:150:27:pEle") } // ParSRAMLoad(x6397,List(List(b2838, b2846)),List(x7099))
    val x7101 = withCtrl(x7119) { x7100 } // VectorApply(x7100,0)
    val x7102_d0_b0 = withCtrl(x7119) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7102_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x7102 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x7102_d0_b0) = false
    bufferDepthOf(x7102_d0_b0) = 1
    staticDimsOf(x7102_d0_b0) = List(1024)
    val x7103 = withCtrl(x7119) { OpDef(op=FixSub, inputs=List(x7101, Const(-8.0))).name("x7103").srcCtx("NonLinearity.scala:44:22") } // FixSub(x7101,Const(-8))
    val x7104 = withCtrl(x7119) { OpDef(op=FixSla, inputs=List(x7103, Const(6))).name("x7104").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x7103,Const(6))
    // x7105 = FixConvert(x7104,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x7105 = withCtrl(x7119) { OpDef(op=FixSra, inputs=List(x7104, Const("24"))).name("x7105").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x7104,TRUE,_32,_0)
    // }
    val x7106 = withCtrl(x7119) { LoadBanks(List(x7102_d0_b0), List(x7105)).name("x7106").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x7102,List(x7105),x7099)
    val x7107_d0_b0 = withCtrl(x7119) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7107_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x7107 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x7107_d0_b0) = false
    bufferDepthOf(x7107_d0_b0) = 1
    staticDimsOf(x7107_d0_b0) = List(1024)
    val x7108 = withCtrl(x7119) { LoadBanks(List(x7107_d0_b0), List(x7105)).name("x7108").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x7107,List(x7105),x7099)
    val x7109 = withCtrl(x7119) { OpDef(op=FixLt, inputs=List(Const(8.0), x7101)).name("x7109").srcCtx("GateMetaPipe.scala:154:27:isHigh") } // FixLt(Const(8),x7101)
    val x7110 = withCtrl(x7119) { OpDef(op=FixLt, inputs=List(x7101, Const(-8.0))).name("x7110").srcCtx("GateMetaPipe.scala:155:26:isLow") } // FixLt(x7101,Const(-8))
    val x7111 = withCtrl(x7119) { OpDef(op=MuxOp, inputs=List(x7110, Const(0.0), x7106)).name("x7111").srcCtx("GateMetaPipe.scala:157:46") } // Mux(x7110,Const(0),x7106)
    val x7112 = withCtrl(x7119) { OpDef(op=MuxOp, inputs=List(x7109, Const(1.0), x7111)).name("x7112").srcCtx("GateMetaPipe.scala:157:25:sigEle") } // Mux(x7109,Const(1),x7111)
    val x7113 = withCtrl(x7119) { OpDef(op=MuxOp, inputs=List(x7110, Const(-1.0), x7108)).name("x7113").srcCtx("GateMetaPipe.scala:158:47") } // Mux(x7110,Const(-1),x7108)
    val x7114 = withCtrl(x7119) { OpDef(op=MuxOp, inputs=List(x7109, Const(1.0), x7113)).name("x7114").srcCtx("GateMetaPipe.scala:158:26:tanhEle") } // Mux(x7109,Const(1),x7113)
    val x7115_x7092 = withCtrl(x7119) { WriteMem(x7092, x7112).name("x7115_x7092").srcCtx("GateMetaPipe.scala:160:17") } // ParFIFOEnq(x7092,List(x7112),List(x7099))
    val x7116_x7093 = withCtrl(x7119) { WriteMem(x7093, x7112).name("x7116_x7093").srcCtx("GateMetaPipe.scala:161:18") } // ParFIFOEnq(x7093,List(x7112),List(x7099))
    val x7117_x7094 = withCtrl(x7119) { WriteMem(x7094, x7112).name("x7117_x7094").srcCtx("GateMetaPipe.scala:162:23") } // ParFIFOEnq(x7094,List(x7112),List(x7099))
    val x7118_x7095 = withCtrl(x7119) { WriteMem(x7095, x7114).name("x7118_x7095").srcCtx("GateMetaPipe.scala:164:18") } // ParFIFOEnq(x7095,List(x7114),List(x7099))
    val x7199 = withCtrl(x7200) { UnitController(style=SeqPipe, level=OuterControl).name("x7199").srcCtx("GateMetaPipe.scala:167:12") } // UnitPipe(List(b2839, b1969),Block(Const(())))
    val x7120 = withCtrl(x7199) { FIFO(size=64).name("x7120").srcCtx("GateMetaPipe.scala:169:29:cLastQ") } // x7120 = FIFONew(Const(64))
    isAccum(x7120) = false
    bufferDepthOf(x7120) = 1
    val x7121 = withCtrl(x7199) { FIFO(size=64).name("x7121").srcCtx("GateMetaPipe.scala:170:35:cNewMultAddQ") } // x7121 = FIFONew(Const(64))
    isAccum(x7121) = false
    bufferDepthOf(x7121) = 1
    val x7122 = withCtrl(x7199) { FIFO(size=64).name("x7122").srcCtx("GateMetaPipe.scala:171:36:cNewMultAddQQ") } // x7122 = FIFONew(Const(64))
    isAccum(x7122) = false
    bufferDepthOf(x7122) = 1
    val x7123 = withCtrl(x7199) { FIFO(size=64).name("x7123").srcCtx("GateMetaPipe.scala:172:32:cNewMultQ") } // x7123 = FIFONew(Const(64))
    isAccum(x7123) = false
    bufferDepthOf(x7123) = 1
    val x7124 = withCtrl(x7199) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7124").srcCtx("GateMetaPipe.scala:174:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7125 = withCtrl(x7199) { CounterChain(List(x7124)).name("x7125").srcCtx("GateMetaPipe.scala:174:50") } // CounterChainNew(List(x7124))
    val x7145 = withCtrl(x7199) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7125).name("x7145").srcCtx("GateMetaPipe.scala:174:50") } // UnrolledForeach(List(b2839, b1969),x7125,Block(Const(())),List(List(b2876)),List(List(b2877)))
    val b2876 = withCtrl(x7145) { CounterIter(x7124, None).name("b2876") } // b2876
    val b2877 = withCtrl(x7145) { Const(true).name("b2877") } // b2877
    val x7126 = withCtrl(x7145) { OpDef(op=BitAnd, inputs=List(b2877, b2839)).name("x7126").srcCtx("UnrollingBase.scala:28:66") } // And(b2877,b2839)
    val x7127 = withCtrl(x7145) { OpDef(op=BitAnd, inputs=List(x7126, b1969)).name("x7127").srcCtx("UnrollingBase.scala:28:66") } // And(x7126,b1969)
    val x7128 = withCtrl(x7145) { ReadMem(x7092).name("x7128").srcCtx("GateMetaPipe.scala:175:32:sigEle") } // ParFIFODeq(x7092,List(x7127))
    val x7129 = withCtrl(x7145) { x7128 } // VectorApply(x7128,0)
    val x7130 = withCtrl(x7145) { ReadMem(x7095).name("x7130").srcCtx("GateMetaPipe.scala:176:34:tanhEle") } // ParFIFODeq(x7095,List(x7127))
    val x7131 = withCtrl(x7145) { x7130 } // VectorApply(x7130,0)
    val x7132 = withCtrl(x7145) { OpDef(op=FixEql, inputs=List(b2838, Const(0))).name("x7132").srcCtx("package.scala:110:40") } // FixEql(b2838,Const(0))
    val x7133 = withCtrl(x7145) { ReadMem(x6398_d0).name("x7133").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x6398)
    val x7134 = withCtrl(x7145) { OpDef(op=BitAnd, inputs=List(x7133, x7132)).name("x7134").srcCtx("GateMetaPipe.scala:177:36:isInitC") } // And(x7133,x7132)
    val x7135 = withCtrl(x7145) { LoadBanks(List(x6392_d0_b0), List(b2876)).name("x7135").srcCtx("GateMetaPipe.scala:179:35") } // ParSRAMLoad(x6392,List(List(b2876)),List(x7127))
    val x7136 = withCtrl(x7145) { x7135 } // VectorApply(x7135,0)
    val x7137 = withCtrl(x7145) { OpDef(op=MuxOp, inputs=List(x7134, Const(0.0), x7136)).name("x7137").srcCtx("GateMetaPipe.scala:178:26:cLast") } // Mux(x7134,Const(0),x7136)
    val x7138 = withCtrl(x7145) { OpDef(op=FixMul, inputs=List(x7137, x7131)).name("x7138").srcCtx("GateMetaPipe.scala:181:32:cNewMult") } // FixMul(x7137,x7131)
    val x7139 = withCtrl(x7145) { OpDef(op=FixMul, inputs=List(x7129, x7137)).name("x7139").srcCtx("GateMetaPipe.scala:182:36") } // FixMul(x7129,x7137)
    val x7140 = withCtrl(x7145) { OpDef(op=FixAdd, inputs=List(x7139, x7138)).name("x7140").srcCtx("GateMetaPipe.scala:182:44:cNewMultAdd") } // FixAdd(x7139,x7138)
    val x7141_x7123 = withCtrl(x7145) { WriteMem(x7123, x7138).name("x7141_x7123").srcCtx("GateMetaPipe.scala:184:24") } // ParFIFOEnq(x7123,List(x7138),List(x7127))
    val x7142_x7121 = withCtrl(x7145) { WriteMem(x7121, x7140).name("x7142_x7121").srcCtx("GateMetaPipe.scala:185:27") } // ParFIFOEnq(x7121,List(x7140),List(x7127))
    val x7143_x7122 = withCtrl(x7145) { WriteMem(x7122, x7140).name("x7143_x7122").srcCtx("GateMetaPipe.scala:186:28") } // ParFIFOEnq(x7122,List(x7140),List(x7127))
    val x7144_x7120 = withCtrl(x7145) { WriteMem(x7120, x7137).name("x7144_x7120").srcCtx("GateMetaPipe.scala:187:21") } // ParFIFOEnq(x7120,List(x7137),List(x7127))
    val x7146 = withCtrl(x7199) { FIFO(size=64).name("x7146").srcCtx("GateMetaPipe.scala:190:31:cUpdateQ") } // x7146 = FIFONew(Const(64))
    isAccum(x7146) = false
    bufferDepthOf(x7146) = 1
    val x7147 = withCtrl(x7199) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7147").srcCtx("GateMetaPipe.scala:191:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7148 = withCtrl(x7199) { CounterChain(List(x7147)).name("x7148").srcCtx("GateMetaPipe.scala:191:50") } // CounterChainNew(List(x7147))
    val x7166 = withCtrl(x7199) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7148).name("x7166").srcCtx("GateMetaPipe.scala:191:50") } // UnrolledForeach(List(b2839, b1969),x7148,Block(Const(())),List(List(b2901)),List(List(b2902)))
    val b2901 = withCtrl(x7166) { CounterIter(x7147, None).name("b2901") } // b2901
    val b2902 = withCtrl(x7166) { Const(true).name("b2902") } // b2902
    val x7149 = withCtrl(x7166) { OpDef(op=BitAnd, inputs=List(b2902, b2839)).name("x7149").srcCtx("UnrollingBase.scala:28:66") } // And(b2902,b2839)
    val x7150 = withCtrl(x7166) { OpDef(op=BitAnd, inputs=List(x7149, b1969)).name("x7150").srcCtx("UnrollingBase.scala:28:66") } // And(x7149,b1969)
    val x7151 = withCtrl(x7166) { ReadMem(x7123).name("x7151").srcCtx("GateMetaPipe.scala:192:39:cNewMult") } // ParFIFODeq(x7123,List(x7150))
    val x7152 = withCtrl(x7166) { x7151 } // VectorApply(x7151,0)
    val x7153 = withCtrl(x7166) { ReadMem(x7094).name("x7153").srcCtx("GateMetaPipe.scala:193:38:sigEle") } // ParFIFODeq(x7094,List(x7150))
    val x7154 = withCtrl(x7166) { x7153 } // VectorApply(x7153,0)
    val x7155 = withCtrl(x7166) { ReadMem(x7121).name("x7155").srcCtx("GateMetaPipe.scala:194:45:cNewMultAdd") } // ParFIFODeq(x7121,List(x7150))
    val x7156 = withCtrl(x7166) { x7155 } // VectorApply(x7155,0)
    val x7157 = withCtrl(x7166) { ReadMem(x7120).name("x7157").srcCtx("GateMetaPipe.scala:195:33:cLast") } // ParFIFODeq(x7120,List(x7150))
    val x7158 = withCtrl(x7166) { x7157 } // VectorApply(x7157,0)
    val x7159 = withCtrl(x7166) { OpDef(op=FixEql, inputs=List(b2838, Const(0))).name("x7159").srcCtx("package.scala:110:40") } // FixEql(b2838,Const(0))
    val x7160 = withCtrl(x7166) { OpDef(op=FixEql, inputs=List(b2838, Const(1))).name("x7160").srcCtx("package.scala:113:40") } // FixEql(b2838,Const(1))
    val x7161 = withCtrl(x7166) { OpDef(op=FixEql, inputs=List(b2838, Const(2))).name("x7161").srcCtx("package.scala:116:40") } // FixEql(b2838,Const(2))
    val x7162 = withCtrl(x7166) { OpDef(op=MuxOp, inputs=List(x7161, x7156, x7158)).name("x7162").srcCtx("GateMetaPipe.scala:200:40") } // Mux(x7161,x7156,x7158)
    val x7163 = withCtrl(x7166) { OpDef(op=MuxOp, inputs=List(x7160, x7152, x7162)).name("x7163").srcCtx("GateMetaPipe.scala:199:36") } // Mux(x7160,x7152,x7162)
    val x7164 = withCtrl(x7166) { OpDef(op=MuxOp, inputs=List(x7159, x7154, x7163)).name("x7164").srcCtx("GateMetaPipe.scala:198:28:cUpdate") } // Mux(x7159,x7154,x7163)
    val x7165_x7146 = withCtrl(x7166) { WriteMem(x7146, x7164).name("x7165_x7146").srcCtx("GateMetaPipe.scala:206:23") } // ParFIFOEnq(x7146,List(x7164),List(x7150))
    val x7167 = withCtrl(x7199) { FIFO(size=64).name("x7167").srcCtx("GateMetaPipe.scala:209:31:hLinearQ") } // x7167 = FIFONew(Const(64))
    isAccum(x7167) = false
    bufferDepthOf(x7167) = 1
    val x7168 = withCtrl(x7199) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7168").srcCtx("GateMetaPipe.scala:210:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7169 = withCtrl(x7199) { CounterChain(List(x7168)).name("x7169").srcCtx("GateMetaPipe.scala:210:50") } // CounterChainNew(List(x7168))
    val x7184 = withCtrl(x7199) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7169).name("x7184").srcCtx("GateMetaPipe.scala:210:50") } // UnrolledForeach(List(b2839, b1969),x7169,Block(Const(())),List(List(b2924)),List(List(b2925)))
    val b2924 = withCtrl(x7184) { CounterIter(x7168, None).name("b2924") } // b2924
    val b2925 = withCtrl(x7184) { Const(true).name("b2925") } // b2925
    val x7170 = withCtrl(x7184) { OpDef(op=BitAnd, inputs=List(b2925, b2839)).name("x7170").srcCtx("UnrollingBase.scala:28:66") } // And(b2925,b2839)
    val x7171 = withCtrl(x7184) { OpDef(op=BitAnd, inputs=List(x7170, b1969)).name("x7171").srcCtx("UnrollingBase.scala:28:66") } // And(x7170,b1969)
    val x7172 = withCtrl(x7184) { ReadMem(x7122).name("x7172").srcCtx("GateMetaPipe.scala:211:46:cNewMultAdd") } // ParFIFODeq(x7122,List(x7171))
    val x7173 = withCtrl(x7184) { x7172 } // VectorApply(x7172,0)
    val x7174_d0_b0 = withCtrl(x7184) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7174_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x7174 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x7174_d0_b0) = false
    bufferDepthOf(x7174_d0_b0) = 1
    staticDimsOf(x7174_d0_b0) = List(1024)
    val x7175 = withCtrl(x7184) { OpDef(op=FixSub, inputs=List(x7173, Const(-8.0))).name("x7175").srcCtx("NonLinearity.scala:44:22") } // FixSub(x7173,Const(-8))
    val x7176 = withCtrl(x7184) { OpDef(op=FixSla, inputs=List(x7175, Const(6))).name("x7176").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x7175,Const(6))
    // x7177 = FixConvert(x7176,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x7177 = withCtrl(x7184) { OpDef(op=FixSra, inputs=List(x7176, Const("24"))).name("x7177").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x7176,TRUE,_32,_0)
    // }
    val x7178 = withCtrl(x7184) { LoadBanks(List(x7174_d0_b0), List(x7177)).name("x7178").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x7174,List(x7177),x7171)
    val x7179 = withCtrl(x7184) { OpDef(op=FixLt, inputs=List(Const(8.0), x7173)).name("x7179").srcCtx("GateMetaPipe.scala:214:36:isHigh") } // FixLt(Const(8),x7173)
    val x7180 = withCtrl(x7184) { OpDef(op=FixLt, inputs=List(x7173, Const(-8.0))).name("x7180").srcCtx("GateMetaPipe.scala:215:35:isLow") } // FixLt(x7173,Const(-8))
    val x7181 = withCtrl(x7184) { OpDef(op=MuxOp, inputs=List(x7180, Const(-1.0), x7178)).name("x7181").srcCtx("GateMetaPipe.scala:217:33") } // Mux(x7180,Const(-1),x7178)
    val x7182 = withCtrl(x7184) { OpDef(op=MuxOp, inputs=List(x7179, Const(1.0), x7181)).name("x7182").srcCtx("GateMetaPipe.scala:216:28:hLinear") } // Mux(x7179,Const(1),x7181)
    val x7183_x7167 = withCtrl(x7184) { WriteMem(x7167, x7182).name("x7183_x7167").srcCtx("GateMetaPipe.scala:222:23") } // ParFIFOEnq(x7167,List(x7182),List(x7171))
    val x7185 = withCtrl(x7199) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7185").srcCtx("GateMetaPipe.scala:225:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7186 = withCtrl(x7199) { CounterChain(List(x7185)).name("x7186").srcCtx("GateMetaPipe.scala:225:50") } // CounterChainNew(List(x7185))
    val x7198 = withCtrl(x7199) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7186).name("x7198").srcCtx("GateMetaPipe.scala:225:50") } // UnrolledForeach(List(b2839, b1969),x7186,Block(Const(())),List(List(b2943)),List(List(b2944)))
    val b2943 = withCtrl(x7198) { CounterIter(x7185, None).name("b2943") } // b2943
    val b2944 = withCtrl(x7198) { Const(true).name("b2944") } // b2944
    val x7187 = withCtrl(x7198) { OpDef(op=BitAnd, inputs=List(b2944, b2839)).name("x7187").srcCtx("UnrollingBase.scala:28:66") } // And(b2944,b2839)
    val x7188 = withCtrl(x7198) { OpDef(op=BitAnd, inputs=List(x7187, b1969)).name("x7188").srcCtx("UnrollingBase.scala:28:66") } // And(x7187,b1969)
    val x7189 = withCtrl(x7198) { ReadMem(x7146).name("x7189").srcCtx("GateMetaPipe.scala:229:34:cNew") } // ParFIFODeq(x7146,List(x7188))
    val x7190 = withCtrl(x7198) { x7189 } // VectorApply(x7189,0)
    val x7191 = withCtrl(x7198) { ReadMem(x7167).name("x7191").srcCtx("GateMetaPipe.scala:230:37:hLinear") } // ParFIFODeq(x7167,List(x7188))
    val x7192 = withCtrl(x7198) { x7191 } // VectorApply(x7191,0)
    val x7193 = withCtrl(x7198) { ReadMem(x7093).name("x7193").srcCtx("GateMetaPipe.scala:231:33:sigEle") } // ParFIFODeq(x7093,List(x7188))
    val x7194 = withCtrl(x7198) { x7193 } // VectorApply(x7193,0)
    val x7195 = withCtrl(x7198) { OpDef(op=FixMul, inputs=List(x7192, x7194)).name("x7195").srcCtx("GateMetaPipe.scala:232:30:hNew") } // FixMul(x7192,x7194)
    val x7196 = withCtrl(x7198) { StoreBanks(List(List(x6393_d0_b0), List(x6393_d1_b0)), List(b2943), x7195).name("x7196").srcCtx("GateMetaPipe.scala:234:29") } // ParSRAMStore(x6393,List(List(b2943)),List(x7195),List(x7188))
    def split3 = {
    val x7197 = withCtrl(x7198) { StoreBanks(List(List(x6392_d0_b0)), List(b2943), x7190).name("x7197").srcCtx("GateMetaPipe.scala:235:29") } // ParSRAMStore(x6392,List(List(b2943)),List(x7190),List(x7188))
    val x7223 = withCtrl(x7224) { UnitController(style=StreamPipe, level=OuterControl).name("x7223").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b7235 = withCtrl(x7223) { StreamOut(field="offset").name("b7235").srcCtx("DeepBenchLSTM256.scala:119:39") } // x7202 = StreamOutNew(BurstCmdBus)
    isAccum(b7235) = false
    bufferDepthOf(b7235) = 1
    val b7236 = withCtrl(x7223) { StreamOut(field="size").name("b7236").srcCtx("DeepBenchLSTM256.scala:119:39") } // x7202 = StreamOutNew(BurstCmdBus)
    isAccum(b7236) = false
    bufferDepthOf(b7236) = 1
    val x7203 = withCtrl(x7223) { StreamOut(field="data").name("x7203").srcCtx("DeepBenchLSTM256.scala:119:39") } // x7203 = StreamOutNew(BurstFullDataBus())
    isAccum(x7203) = false
    bufferDepthOf(x7203) = 1
    val x7204 = withCtrl(x7223) { StreamIn(field="ack").name("x7204").srcCtx("DeepBenchLSTM256.scala:119:39") } // x7204 = StreamInNew(BurstAckBus)
    isAccum(x7204) = false
    bufferDepthOf(x7204) = 1
    val x7212 = withCtrl(x7223) { UnitController(style=SeqPipe, level=InnerControl).name("x7212").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(x7211))
    val x7205 = withCtrl(x7212) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7206 = withCtrl(x7212) { OpDef(op=FixSla, inputs=List(x7205, Const(2))).name("x7206").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixLsh(x7205,Const(2))
    val x7207 = withCtrl(x7212) { x7206 } // FixConvert(x7206,TRUE,_64,_0)
    val x7208 = withCtrl(x7212) { DramAddress(x6387).name("x7208").srcCtx("DeepBenchLSTM256.scala:119:39") } // GetDRAMAddress(x6387)
    val x7210_x7209 = withCtrl(x7212) { OpDef(op=FixAdd, inputs=List(x7207, x7208)).name("x7210_x7209").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixAdd(x7207,x7208)
    // x7210 = SimpleStruct(ArrayBuffer((offset,x7209), (size,Const(1024)), (isLoad,Const(false))))
    val x7211_b7237_b7235 = withCtrl(x7212) { WriteMem(b7235, x7210_x7209).name("x7211_b7237_b7235").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x7202,x7210,Const(true))
    val x7211_b7238_b7236 = withCtrl(x7212) { WriteMem(b7236, Const(1024)).name("x7211_b7238_b7236").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x7202,x7210,Const(true))
    val x7213 = withCtrl(x7223) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x7213").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x7214 = withCtrl(x7223) { CounterChain(List(x7213)).name("x7214").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterChainNew(List(x7213))
    val x7219 = withCtrl(x7223) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7214).name("x7219").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnrolledForeach(List(Const(true)),x7214,Block(Const(())),List(List(b2973)),List(List(b2974)))
    val b2973 = withCtrl(x7219) { CounterIter(x7213, None).name("b2973") } // b2973
    val b2974 = withCtrl(x7219) { Const(true).name("b2974") } // b2974
    val x7215 = withCtrl(x7219) { LoadBanks(List(x6393_d0_b0), List(b2973)).name("x7215").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParSRAMLoad(x6393,List(List(b2973)),List(b2974))
    val x7217_x7216 = withCtrl(x7219) { x7215 } // VectorApply(x7215,0)
    // x7217 = SimpleStruct(ArrayBuffer((_1,x7216), (_2,Const(true))))
    val x7218_x7218_x7203 = withCtrl(x7219) { WriteMem(x7203, x7217_x7216).name("x7218_x7218_x7203").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParStreamWrite(x7203,List(x7217),List(b2974))
    val x7220 = withCtrl(x7223) { FringeDenseStore(dram=List(x6387), cmdStream=List(b7235, b7236), dataStream=List(x7203), ackStream=List(x7204)).name("x7220").srcCtx("DeepBenchLSTM256.scala:119:39") } // FringeDenseStore(x6387,x7202,x7203,x7204)
    val x7222 = withCtrl(x7223) { UnitController(style=SeqPipe, level=InnerControl).name("x7222").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x7221_x7221 = withCtrl(x7222) { ReadMem(x7204).name("x7221_x7221").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamRead(x7204,Const(true))
    }; split3
    }; split2
    }; split1
    
  }
}
