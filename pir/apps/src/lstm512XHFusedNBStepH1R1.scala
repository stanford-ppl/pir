import pir._
import pir.node._
import arch._
import prism.enums._

object lstm512XHFusedNBStepH1R1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x7715 = withCtrl(design.top.topController) { ArgOut(init=0.0).name("x7715").srcCtx("sysml.scala:359:22:cArg") } // ArgOutNew(Const(0.0))
    isAccum(x7715) = false
    bufferDepthOf(x7715) = 1
    val x7716 = withCtrl(design.top.topController) { ArgOut(init=0.0).name("x7716").srcCtx("sysml.scala:360:22:hArg") } // ArgOutNew(Const(0.0))
    isAccum(x7716) = false
    bufferDepthOf(x7716) = 1
    val x8813 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x8813").srcCtx("sysml.scala:362:11") } // Hwblock(Block(Const(())),false)
    val x7717_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d0_b0").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d0_b0) = false
    bufferDepthOf(x7717_d0_b0) = 1
    staticDimsOf(x7717_d0_b0) = List(512, 1024)
    val x7717_d0_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d0_b1").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d0_b1) = false
    bufferDepthOf(x7717_d0_b1) = 1
    staticDimsOf(x7717_d0_b1) = List(512, 1024)
    val x7717_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d1_b0").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d1_b0) = false
    bufferDepthOf(x7717_d1_b0) = 1
    staticDimsOf(x7717_d1_b0) = List(512, 1024)
    val x7717_d1_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d1_b1").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d1_b1) = false
    bufferDepthOf(x7717_d1_b1) = 1
    staticDimsOf(x7717_d1_b1) = List(512, 1024)
    val x7717_d2_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d2_b0").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d2_b0) = false
    bufferDepthOf(x7717_d2_b0) = 1
    staticDimsOf(x7717_d2_b0) = List(512, 1024)
    val x7717_d2_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d2_b1").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d2_b1) = false
    bufferDepthOf(x7717_d2_b1) = 1
    staticDimsOf(x7717_d2_b1) = List(512, 1024)
    val x7717_d3_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d3_b0").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d3_b0) = false
    bufferDepthOf(x7717_d3_b0) = 1
    staticDimsOf(x7717_d3_b0) = List(512, 1024)
    val x7717_d3_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d3_b1").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d3_b1) = false
    bufferDepthOf(x7717_d3_b1) = 1
    staticDimsOf(x7717_d3_b1) = List(512, 1024)
    val x7717_d4_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d4_b0").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d4_b0) = false
    bufferDepthOf(x7717_d4_b0) = 1
    staticDimsOf(x7717_d4_b0) = List(512, 1024)
    val x7717_d4_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7717_d4_b1").srcCtx("DataGenerator.scala:236:4") } // x7717 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7717_d4_b1) = false
    bufferDepthOf(x7717_d4_b1) = 1
    staticDimsOf(x7717_d4_b1) = List(512, 1024)
    val x7718_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d0_b0").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d0_b0) = false
    bufferDepthOf(x7718_d0_b0) = 1
    staticDimsOf(x7718_d0_b0) = List(512, 1024)
    val x7718_d0_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d0_b1").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d0_b1) = false
    bufferDepthOf(x7718_d0_b1) = 1
    staticDimsOf(x7718_d0_b1) = List(512, 1024)
    val x7718_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d1_b0").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d1_b0) = false
    bufferDepthOf(x7718_d1_b0) = 1
    staticDimsOf(x7718_d1_b0) = List(512, 1024)
    val x7718_d1_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d1_b1").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d1_b1) = false
    bufferDepthOf(x7718_d1_b1) = 1
    staticDimsOf(x7718_d1_b1) = List(512, 1024)
    val x7718_d2_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d2_b0").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d2_b0) = false
    bufferDepthOf(x7718_d2_b0) = 1
    staticDimsOf(x7718_d2_b0) = List(512, 1024)
    val x7718_d2_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d2_b1").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d2_b1) = false
    bufferDepthOf(x7718_d2_b1) = 1
    staticDimsOf(x7718_d2_b1) = List(512, 1024)
    val x7718_d3_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d3_b0").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d3_b0) = false
    bufferDepthOf(x7718_d3_b0) = 1
    staticDimsOf(x7718_d3_b0) = List(512, 1024)
    val x7718_d3_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d3_b1").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d3_b1) = false
    bufferDepthOf(x7718_d3_b1) = 1
    staticDimsOf(x7718_d3_b1) = List(512, 1024)
    val x7718_d4_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d4_b0").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d4_b0) = false
    bufferDepthOf(x7718_d4_b0) = 1
    staticDimsOf(x7718_d4_b0) = List(512, 1024)
    val x7718_d4_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7718_d4_b1").srcCtx("DataGenerator.scala:236:4") } // x7718 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7718_d4_b1) = false
    bufferDepthOf(x7718_d4_b1) = 1
    staticDimsOf(x7718_d4_b1) = List(512, 1024)
    val x7719_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d0_b0").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d0_b0) = false
    bufferDepthOf(x7719_d0_b0) = 1
    staticDimsOf(x7719_d0_b0) = List(512, 1024)
    val x7719_d0_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d0_b1").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d0_b1) = false
    bufferDepthOf(x7719_d0_b1) = 1
    staticDimsOf(x7719_d0_b1) = List(512, 1024)
    val x7719_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d1_b0").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d1_b0) = false
    bufferDepthOf(x7719_d1_b0) = 1
    staticDimsOf(x7719_d1_b0) = List(512, 1024)
    val x7719_d1_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d1_b1").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d1_b1) = false
    bufferDepthOf(x7719_d1_b1) = 1
    staticDimsOf(x7719_d1_b1) = List(512, 1024)
    val x7719_d2_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d2_b0").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d2_b0) = false
    bufferDepthOf(x7719_d2_b0) = 1
    staticDimsOf(x7719_d2_b0) = List(512, 1024)
    val x7719_d2_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d2_b1").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d2_b1) = false
    bufferDepthOf(x7719_d2_b1) = 1
    staticDimsOf(x7719_d2_b1) = List(512, 1024)
    val x7719_d3_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d3_b0").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d3_b0) = false
    bufferDepthOf(x7719_d3_b0) = 1
    staticDimsOf(x7719_d3_b0) = List(512, 1024)
    val x7719_d3_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d3_b1").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d3_b1) = false
    bufferDepthOf(x7719_d3_b1) = 1
    staticDimsOf(x7719_d3_b1) = List(512, 1024)
    val x7719_d4_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d4_b0").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d4_b0) = false
    bufferDepthOf(x7719_d4_b0) = 1
    staticDimsOf(x7719_d4_b0) = List(512, 1024)
    val x7719_d4_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7719_d4_b1").srcCtx("DataGenerator.scala:236:4") } // x7719 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7719_d4_b1) = false
    bufferDepthOf(x7719_d4_b1) = 1
    staticDimsOf(x7719_d4_b1) = List(512, 1024)
    val x7720_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d0_b0").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d0_b0) = false
    bufferDepthOf(x7720_d0_b0) = 1
    staticDimsOf(x7720_d0_b0) = List(512, 1024)
    val x7720_d0_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d0_b1").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d0_b1) = false
    bufferDepthOf(x7720_d0_b1) = 1
    staticDimsOf(x7720_d0_b1) = List(512, 1024)
    val x7720_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d1_b0").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d1_b0) = false
    bufferDepthOf(x7720_d1_b0) = 1
    staticDimsOf(x7720_d1_b0) = List(512, 1024)
    val x7720_d1_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d1_b1").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d1_b1) = false
    bufferDepthOf(x7720_d1_b1) = 1
    staticDimsOf(x7720_d1_b1) = List(512, 1024)
    val x7720_d2_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d2_b0").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d2_b0) = false
    bufferDepthOf(x7720_d2_b0) = 1
    staticDimsOf(x7720_d2_b0) = List(512, 1024)
    val x7720_d2_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d2_b1").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d2_b1) = false
    bufferDepthOf(x7720_d2_b1) = 1
    staticDimsOf(x7720_d2_b1) = List(512, 1024)
    val x7720_d3_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d3_b0").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d3_b0) = false
    bufferDepthOf(x7720_d3_b0) = 1
    staticDimsOf(x7720_d3_b0) = List(512, 1024)
    val x7720_d3_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d3_b1").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d3_b1) = false
    bufferDepthOf(x7720_d3_b1) = 1
    staticDimsOf(x7720_d3_b1) = List(512, 1024)
    val x7720_d4_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d4_b0").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d4_b0) = false
    bufferDepthOf(x7720_d4_b0) = 1
    staticDimsOf(x7720_d4_b0) = List(512, 1024)
    val x7720_d4_b1 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7720_d4_b1").srcCtx("DataGenerator.scala:236:4") } // x7720 = LUTNew(List(512, 1024), Seq(Const(0.0)))
    isAccum(x7720_d4_b1) = false
    bufferDepthOf(x7720_d4_b1) = 1
    staticDimsOf(x7720_d4_b1) = List(512, 1024)
    val x7721_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=2, stride=1)).name("x7721_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x7721 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x7721_d0_b0) = false
    bufferDepthOf(x7721_d0_b0) = 1
    staticDimsOf(x7721_d0_b0) = List(512)
    val x7722_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=2, stride=1)).name("x7722_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x7722 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x7722_d0_b0) = false
    bufferDepthOf(x7722_d0_b0) = 1
    staticDimsOf(x7722_d0_b0) = List(512)
    val x7723_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=2, stride=1)).name("x7723_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x7723 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x7723_d0_b0) = false
    bufferDepthOf(x7723_d0_b0) = 1
    staticDimsOf(x7723_d0_b0) = List(512)
    val x7724_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=2, stride=1)).name("x7724_d0_b0").srcCtx("DataGenerator.scala:220:28") } // x7724 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x7724_d0_b0) = false
    bufferDepthOf(x7724_d0_b0) = 1
    staticDimsOf(x7724_d0_b0) = List(512)
    val x7725_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d0_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d0_b0) = false
    bufferDepthOf(x7725_d0_b0) = 1
    staticDimsOf(x7725_d0_b0) = List(1024)
    val x7725_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d1_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d1_b0) = false
    bufferDepthOf(x7725_d1_b0) = 1
    staticDimsOf(x7725_d1_b0) = List(1024)
    val x7725_d2_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d2_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d2_b0) = false
    bufferDepthOf(x7725_d2_b0) = 1
    staticDimsOf(x7725_d2_b0) = List(1024)
    val x7725_d3_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d3_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d3_b0) = false
    bufferDepthOf(x7725_d3_b0) = 1
    staticDimsOf(x7725_d3_b0) = List(1024)
    val x7725_d4_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d4_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d4_b0) = false
    bufferDepthOf(x7725_d4_b0) = 1
    staticDimsOf(x7725_d4_b0) = List(1024)
    val x7725_d5_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d5_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d5_b0) = false
    bufferDepthOf(x7725_d5_b0) = 1
    staticDimsOf(x7725_d5_b0) = List(1024)
    val x7725_d6_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d6_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d6_b0) = false
    bufferDepthOf(x7725_d6_b0) = 1
    staticDimsOf(x7725_d6_b0) = List(1024)
    val x7725_d7_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d7_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d7_b0) = false
    bufferDepthOf(x7725_d7_b0) = 1
    staticDimsOf(x7725_d7_b0) = List(1024)
    val x7725_d8_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d8_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d8_b0) = false
    bufferDepthOf(x7725_d8_b0) = 1
    staticDimsOf(x7725_d8_b0) = List(1024)
    val x7725_d9_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d9_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d9_b0) = false
    bufferDepthOf(x7725_d9_b0) = 1
    staticDimsOf(x7725_d9_b0) = List(1024)
    val x7725_d10_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d10_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d10_b0) = false
    bufferDepthOf(x7725_d10_b0) = 1
    staticDimsOf(x7725_d10_b0) = List(1024)
    val x7725_d11_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d11_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d11_b0) = false
    bufferDepthOf(x7725_d11_b0) = 1
    staticDimsOf(x7725_d11_b0) = List(1024)
    val x7725_d12_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d12_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d12_b0) = false
    bufferDepthOf(x7725_d12_b0) = 1
    staticDimsOf(x7725_d12_b0) = List(1024)
    val x7725_d13_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d13_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d13_b0) = false
    bufferDepthOf(x7725_d13_b0) = 1
    staticDimsOf(x7725_d13_b0) = List(1024)
    val x7725_d14_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d14_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d14_b0) = false
    bufferDepthOf(x7725_d14_b0) = 1
    staticDimsOf(x7725_d14_b0) = List(1024)
    val x7725_d15_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d15_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d15_b0) = false
    bufferDepthOf(x7725_d15_b0) = 1
    staticDimsOf(x7725_d15_b0) = List(1024)
    val x7725_d16_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d16_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d16_b0) = false
    bufferDepthOf(x7725_d16_b0) = 1
    staticDimsOf(x7725_d16_b0) = List(1024)
    val x7725_d17_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d17_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d17_b0) = false
    bufferDepthOf(x7725_d17_b0) = 1
    staticDimsOf(x7725_d17_b0) = List(1024)
    val x7725_d18_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d18_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d18_b0) = false
    bufferDepthOf(x7725_d18_b0) = 1
    staticDimsOf(x7725_d18_b0) = List(1024)
    val x7725_d19_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d19_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d19_b0) = false
    bufferDepthOf(x7725_d19_b0) = 1
    staticDimsOf(x7725_d19_b0) = List(1024)
    val x7725_d20_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d20_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d20_b0) = false
    bufferDepthOf(x7725_d20_b0) = 1
    staticDimsOf(x7725_d20_b0) = List(1024)
    val x7725_d21_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d21_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d21_b0) = false
    bufferDepthOf(x7725_d21_b0) = 1
    staticDimsOf(x7725_d21_b0) = List(1024)
    val x7725_d22_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d22_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d22_b0) = false
    bufferDepthOf(x7725_d22_b0) = 1
    staticDimsOf(x7725_d22_b0) = List(1024)
    val x7725_d23_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d23_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d23_b0) = false
    bufferDepthOf(x7725_d23_b0) = 1
    staticDimsOf(x7725_d23_b0) = List(1024)
    val x7725_d24_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d24_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d24_b0) = false
    bufferDepthOf(x7725_d24_b0) = 1
    staticDimsOf(x7725_d24_b0) = List(1024)
    val x7725_d25_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d25_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d25_b0) = false
    bufferDepthOf(x7725_d25_b0) = 1
    staticDimsOf(x7725_d25_b0) = List(1024)
    val x7725_d26_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d26_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d26_b0) = false
    bufferDepthOf(x7725_d26_b0) = 1
    staticDimsOf(x7725_d26_b0) = List(1024)
    val x7725_d27_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d27_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d27_b0) = false
    bufferDepthOf(x7725_d27_b0) = 1
    staticDimsOf(x7725_d27_b0) = List(1024)
    val x7725_d28_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d28_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d28_b0) = false
    bufferDepthOf(x7725_d28_b0) = 1
    staticDimsOf(x7725_d28_b0) = List(1024)
    val x7725_d29_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d29_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d29_b0) = false
    bufferDepthOf(x7725_d29_b0) = 1
    staticDimsOf(x7725_d29_b0) = List(1024)
    val x7725_d30_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d30_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d30_b0) = false
    bufferDepthOf(x7725_d30_b0) = 1
    staticDimsOf(x7725_d30_b0) = List(1024)
    val x7725_d31_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d31_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d31_b0) = false
    bufferDepthOf(x7725_d31_b0) = 1
    staticDimsOf(x7725_d31_b0) = List(1024)
    val x7725_d32_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d32_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d32_b0) = false
    bufferDepthOf(x7725_d32_b0) = 1
    staticDimsOf(x7725_d32_b0) = List(1024)
    val x7725_d33_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d33_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d33_b0) = false
    bufferDepthOf(x7725_d33_b0) = 1
    staticDimsOf(x7725_d33_b0) = List(1024)
    val x7725_d34_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d34_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d34_b0) = false
    bufferDepthOf(x7725_d34_b0) = 1
    staticDimsOf(x7725_d34_b0) = List(1024)
    val x7725_d35_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d35_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d35_b0) = false
    bufferDepthOf(x7725_d35_b0) = 1
    staticDimsOf(x7725_d35_b0) = List(1024)
    val x7725_d36_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d36_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d36_b0) = false
    bufferDepthOf(x7725_d36_b0) = 1
    staticDimsOf(x7725_d36_b0) = List(1024)
    val x7725_d37_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d37_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d37_b0) = false
    bufferDepthOf(x7725_d37_b0) = 1
    staticDimsOf(x7725_d37_b0) = List(1024)
    val x7725_d38_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d38_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d38_b0) = false
    bufferDepthOf(x7725_d38_b0) = 1
    staticDimsOf(x7725_d38_b0) = List(1024)
    val x7725_d39_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7725_d39_b0").srcCtx("DataGenerator.scala:220:28:xhInit") } // x7725 = LUTNew(List(1024), Seq(Const(0.0)))
    isAccum(x7725_d39_b0) = false
    bufferDepthOf(x7725_d39_b0) = 1
    staticDimsOf(x7725_d39_b0) = List(1024)
    val x7726_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=2, stride=1)).name("x7726_d0_b0").srcCtx("DataGenerator.scala:220:28:cInit") } // x7726 = LUTNew(List(512), Seq(Const(0.0)))
    isAccum(x7726_d0_b0) = false
    bufferDepthOf(x7726_d0_b0) = 1
    staticDimsOf(x7726_d0_b0) = List(512)
    val x7727_d0_b0 = withCtrl(x8813) { SRAM(size=512, banking=Strided(banks=2, stride=1)).name("x7727_d0_b0").srcCtx("sysml.scala:378:22:c") } // x7727 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x7727_d0_b0) = false
    bufferDepthOf(x7727_d0_b0) = 1
    staticDimsOf(x7727_d0_b0) = List(512)
    val x7728_d0_b0 = withCtrl(x8813) { SRAM(size=512, banking=Strided(banks=2, stride=1)).name("x7728_d0_b0").srcCtx("sysml.scala:379:22:h") } // x7728 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x7728_d0_b0) = false
    bufferDepthOf(x7728_d0_b0) = 1
    staticDimsOf(x7728_d0_b0) = List(512)
    val x7729_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7729_d0_b0").srcCtx("sysml.scala:380:41:sigLUT") } // x7729 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x7729_d0_b0) = false
    bufferDepthOf(x7729_d0_b0) = 1
    staticDimsOf(x7729_d0_b0) = List(1024)
    val x7729_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7729_d1_b0").srcCtx("sysml.scala:380:41:sigLUT") } // x7729 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x7729_d1_b0) = false
    bufferDepthOf(x7729_d1_b0) = 1
    staticDimsOf(x7729_d1_b0) = List(1024)
    val x7730_d0_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7730_d0_b0").srcCtx("sysml.scala:381:42:tanhLUT") } // x7730 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x7730_d0_b0) = false
    bufferDepthOf(x7730_d0_b0) = 1
    staticDimsOf(x7730_d0_b0) = List(1024)
    val x7730_d1_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7730_d1_b0").srcCtx("sysml.scala:381:42:tanhLUT") } // x7730 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x7730_d1_b0) = false
    bufferDepthOf(x7730_d1_b0) = 1
    staticDimsOf(x7730_d1_b0) = List(1024)
    val x7730_d2_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7730_d2_b0").srcCtx("sysml.scala:381:42:tanhLUT") } // x7730 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x7730_d2_b0) = false
    bufferDepthOf(x7730_d2_b0) = 1
    staticDimsOf(x7730_d2_b0) = List(1024)
    val x7730_d3_b0 = withCtrl(x8813) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7730_d3_b0").srcCtx("sysml.scala:381:42:tanhLUT") } // x7730 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.9999997615814208984375)... [1021 more]))
    isAccum(x7730_d3_b0) = false
    bufferDepthOf(x7730_d3_b0) = 1
    staticDimsOf(x7730_d3_b0) = List(1024)
    val x7731 = withCtrl(x8813) { Counter(min=Const(0), max=Const(512), step=Const(1), par=2).name("x7731").srcCtx("sysml.scala:382:34") } // CounterNew(Const(0),Const(512),Const(1),Const(2))
    val x7732 = withCtrl(x8813) { CounterChain(List(x7731)).name("x7732").srcCtx("sysml.scala:382:49") } // CounterChainNew(List(x7731))
    val x8807 = withCtrl(x8813) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7732).name("x8807").srcCtx("sysml.scala:382:49") } // UnrolledForeach(List(Const(true)),x7732,Block(Const(())),List(List(b2062, b2063)),List(List(b2064, b2065)))
    val b2062 = withCtrl(x8807) { CounterIter(x7731, Some(0)).name("b2062") } // b2062
    val b2064 = withCtrl(x8807) { Const(true).name("b2064") } // b2064
    val b2063 = withCtrl(x8807) { CounterIter(x7731, Some(1)).name("b2063") } // b2063
    val b2065 = withCtrl(x8807) { Const(true).name("b2065") } // b2065
    val x7733_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7733_d0").srcCtx("sysml.scala:597:32:g") } // x7733 = RegNew(Const(0.0))
    isAccum(x7733_d0) = false
    bufferDepthOf(x7733_d0) = 5
    val x7733_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7733_d1").srcCtx("sysml.scala:597:32:g") } // x7733 = RegNew(Const(0.0))
    isAccum(x7733_d1) = true
    bufferDepthOf(x7733_d1) = 1
    val x7734_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7734_d0").srcCtx("sysml.scala:597:32:g") } // x7734 = RegNew(Const(0.0))
    isAccum(x7734_d0) = false
    bufferDepthOf(x7734_d0) = 5
    val x7734_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7734_d1").srcCtx("sysml.scala:597:32:g") } // x7734 = RegNew(Const(0.0))
    isAccum(x7734_d1) = true
    bufferDepthOf(x7734_d1) = 1
    val x7957 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x7957").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x7735 = withCtrl(x7957) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x7735").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x7736 = withCtrl(x7957) { CounterChain(List(x7735)).name("x7736").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x7735))
    val x7845 = withCtrl(x7957) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7736).name("x7845").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2064),x7736,x7733,Block((x7733) => Const(())),List(List(b2072, b2073, b2074, b2075, b2076)),List(List(b2077, b2078, b2079, b2080, b2081)))
    val b2072 = withCtrl(x7845) { CounterIter(x7735, Some(0)).name("b2072") } // b2072
    val b2077 = withCtrl(x7845) { Const(true).name("b2077") } // b2077
    val b2073 = withCtrl(x7845) { CounterIter(x7735, Some(1)).name("b2073") } // b2073
    val b2078 = withCtrl(x7845) { Const(true).name("b2078") } // b2078
    val b2074 = withCtrl(x7845) { CounterIter(x7735, Some(2)).name("b2074") } // b2074
    val b2079 = withCtrl(x7845) { Const(true).name("b2079") } // b2079
    val b2075 = withCtrl(x7845) { CounterIter(x7735, Some(3)).name("b2075") } // b2075
    val b2080 = withCtrl(x7845) { Const(true).name("b2080") } // b2080
    val b2076 = withCtrl(x7845) { CounterIter(x7735, Some(4)).name("b2076") } // b2076
    val b2081 = withCtrl(x7845) { Const(true).name("b2081") } // b2081
    val x7737_d0 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7737_d0").srcCtx("sysml.scala:600:39:gInner") } // x7737 = RegNew(Const(0.0))
    isAccum(x7737_d0) = false
    bufferDepthOf(x7737_d0) = 1
    val x7737_d1 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7737_d1").srcCtx("sysml.scala:600:39:gInner") } // x7737 = RegNew(Const(0.0))
    isAccum(x7737_d1) = true
    bufferDepthOf(x7737_d1) = 1
    val x7738_d0 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7738_d0").srcCtx("sysml.scala:600:39:gInner") } // x7738 = RegNew(Const(0.0))
    isAccum(x7738_d0) = false
    bufferDepthOf(x7738_d0) = 1
    val x7738_d1 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7738_d1").srcCtx("sysml.scala:600:39:gInner") } // x7738 = RegNew(Const(0.0))
    isAccum(x7738_d1) = true
    bufferDepthOf(x7738_d1) = 1
    val x7739_d0 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7739_d0").srcCtx("sysml.scala:600:39:gInner") } // x7739 = RegNew(Const(0.0))
    isAccum(x7739_d0) = false
    bufferDepthOf(x7739_d0) = 1
    val x7739_d1 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7739_d1").srcCtx("sysml.scala:600:39:gInner") } // x7739 = RegNew(Const(0.0))
    isAccum(x7739_d1) = true
    bufferDepthOf(x7739_d1) = 1
    val x7740_d0 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7740_d0").srcCtx("sysml.scala:600:39:gInner") } // x7740 = RegNew(Const(0.0))
    isAccum(x7740_d0) = false
    bufferDepthOf(x7740_d0) = 1
    val x7740_d1 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7740_d1").srcCtx("sysml.scala:600:39:gInner") } // x7740 = RegNew(Const(0.0))
    isAccum(x7740_d1) = true
    bufferDepthOf(x7740_d1) = 1
    val x7741_d0 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7741_d0").srcCtx("sysml.scala:600:39:gInner") } // x7741 = RegNew(Const(0.0))
    isAccum(x7741_d0) = false
    bufferDepthOf(x7741_d0) = 1
    val x7741_d1 = withCtrl(x7845) { Reg(init=Some(0.0)).name("x7741_d1").srcCtx("sysml.scala:600:39:gInner") } // x7741 = RegNew(Const(0.0))
    isAccum(x7741_d1) = true
    bufferDepthOf(x7741_d1) = 1
    val x7817 = withCtrl(x7845) { UnitController(style=ForkJoin, level=OuterControl).name("x7817").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2064),Block(Const(())))
    val x7742 = withCtrl(x7817) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7742").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7743 = withCtrl(x7817) { CounterChain(List(x7742)).name("x7743").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7742))
    val x7756 = withCtrl(x7817) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7743).name("x7756").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2077, b2064),x7743,x7737,Block((x7737) => Const(())),List(List(b2097)),List(List(b2098)))
    val b2097 = withCtrl(x7756) { CounterIter(x7742, None).name("b2097") } // b2097
    val b2098 = withCtrl(x7756) { Const(true).name("b2098") } // b2098
    val x7744 = withCtrl(x7756) { OpDef(op=FixMul, inputs=List(b2072, Const(204))).name("x7744").srcCtx("sysml.scala:603:42") } // FixMul(b2072,Const(204))
    val x7745 = withCtrl(x7756) { OpDef(op=FixAdd, inputs=List(x7744, b2097)).name("x7745").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7744,b2097)
    val x7746 = withCtrl(x7756) { OpDef(op=BitAnd, inputs=List(b2098, b2077)).name("x7746").srcCtx("UnrollingBase.scala:28:66") } // And(b2098,b2077)
    val x7747 = withCtrl(x7756) { OpDef(op=BitAnd, inputs=List(x7746, b2064)).name("x7747").srcCtx("UnrollingBase.scala:28:66") } // And(x7746,b2064)
    val x7748 = withCtrl(x7756) { LoadBanks(List(x7717_d0_b0), List(b2062, x7745)).name("x7748").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2062, x7745),x7747)
    val x7749 = withCtrl(x7756) { LoadBanks(List(x7725_d30_b0), List(x7745)).name("x7749").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7745),x7747)
    val x7750 = withCtrl(x7756) { OpDef(op=FltMul, inputs=List(x7748, x7749)).name("x7750").srcCtx("sysml.scala:606:20") } // FltMul(x7748,x7749)
    val x7751 = withCtrl(x7756) { ReadMem(x7737_d1).name("x7751").srcCtx("sysml.scala:607:13") } // RegRead(x7737)
    val x7752 = withCtrl(x7756) { OpDef(op=FixEql, inputs=List(b2097, Const(0))).name("x7752").srcCtx("sysml.scala:607:13") } // FixEql(b2097,Const(0))
    val x7753 = withCtrl(x7756) { ReduceAccumOp(op=FltAdd, input=x7750, accum=x7751).name("x7753").srcCtx("sysml.scala:607:15") } // FltAdd(x7750,x7751)
    val x7754 = withCtrl(x7756) { OpDef(op=BitAnd, inputs=List(b2077, b2064)).name("x7754").srcCtx("UnrollingBase.scala:28:66") } // And(b2077,b2064)
    val x7755_x7737_d0 = withCtrl(x7756) { WriteMem(x7737_d0, x7753).name("x7755_x7737_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7737,x7753,x7754)
    antiDepsOf(x7755_x7737_d0)=List(x7751)
    val x7755_x7737_d1 = withCtrl(x7756) { WriteMem(x7737_d1, x7753).name("x7755_x7737_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7737,x7753,x7754)
    antiDepsOf(x7755_x7737_d1)=List(x7751)
    val x7757 = withCtrl(x7817) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7757").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7758 = withCtrl(x7817) { CounterChain(List(x7757)).name("x7758").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7757))
    val x7771 = withCtrl(x7817) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7758).name("x7771").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2078, b2064),x7758,x7738,Block((x7738) => Const(())),List(List(b2112)),List(List(b2113)))
    val b2112 = withCtrl(x7771) { CounterIter(x7757, None).name("b2112") } // b2112
    val b2113 = withCtrl(x7771) { Const(true).name("b2113") } // b2113
    val x7759 = withCtrl(x7771) { OpDef(op=FixMul, inputs=List(b2073, Const(204))).name("x7759").srcCtx("sysml.scala:603:42") } // FixMul(b2073,Const(204))
    val x7760 = withCtrl(x7771) { OpDef(op=FixAdd, inputs=List(x7759, b2112)).name("x7760").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7759,b2112)
    val x7761 = withCtrl(x7771) { OpDef(op=BitAnd, inputs=List(b2113, b2078)).name("x7761").srcCtx("UnrollingBase.scala:28:66") } // And(b2113,b2078)
    val x7762 = withCtrl(x7771) { OpDef(op=BitAnd, inputs=List(x7761, b2064)).name("x7762").srcCtx("UnrollingBase.scala:28:66") } // And(x7761,b2064)
    val x7763 = withCtrl(x7771) { LoadBanks(List(x7717_d1_b0), List(b2062, x7760)).name("x7763").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2062, x7760),x7762)
    val x7764 = withCtrl(x7771) { LoadBanks(List(x7725_d31_b0), List(x7760)).name("x7764").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7760),x7762)
    val x7765 = withCtrl(x7771) { OpDef(op=FltMul, inputs=List(x7763, x7764)).name("x7765").srcCtx("sysml.scala:606:20") } // FltMul(x7763,x7764)
    val x7766 = withCtrl(x7771) { ReadMem(x7738_d1).name("x7766").srcCtx("sysml.scala:607:13") } // RegRead(x7738)
    val x7767 = withCtrl(x7771) { OpDef(op=FixEql, inputs=List(b2112, Const(0))).name("x7767").srcCtx("sysml.scala:607:13") } // FixEql(b2112,Const(0))
    val x7768 = withCtrl(x7771) { ReduceAccumOp(op=FltAdd, input=x7765, accum=x7766).name("x7768").srcCtx("sysml.scala:607:15") } // FltAdd(x7765,x7766)
    val x7769 = withCtrl(x7771) { OpDef(op=BitAnd, inputs=List(b2078, b2064)).name("x7769").srcCtx("UnrollingBase.scala:28:66") } // And(b2078,b2064)
    val x7770_x7738_d0 = withCtrl(x7771) { WriteMem(x7738_d0, x7768).name("x7770_x7738_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7738,x7768,x7769)
    antiDepsOf(x7770_x7738_d0)=List(x7766)
    val x7770_x7738_d1 = withCtrl(x7771) { WriteMem(x7738_d1, x7768).name("x7770_x7738_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7738,x7768,x7769)
    antiDepsOf(x7770_x7738_d1)=List(x7766)
    val x7772 = withCtrl(x7817) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7772").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7773 = withCtrl(x7817) { CounterChain(List(x7772)).name("x7773").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7772))
    val x7786 = withCtrl(x7817) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7773).name("x7786").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2079, b2064),x7773,x7739,Block((x7739) => Const(())),List(List(b2127)),List(List(b2128)))
    val b2127 = withCtrl(x7786) { CounterIter(x7772, None).name("b2127") } // b2127
    val b2128 = withCtrl(x7786) { Const(true).name("b2128") } // b2128
    val x7774 = withCtrl(x7786) { OpDef(op=FixMul, inputs=List(b2074, Const(204))).name("x7774").srcCtx("sysml.scala:603:42") } // FixMul(b2074,Const(204))
    val x7775 = withCtrl(x7786) { OpDef(op=FixAdd, inputs=List(x7774, b2127)).name("x7775").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7774,b2127)
    val x7776 = withCtrl(x7786) { OpDef(op=BitAnd, inputs=List(b2128, b2079)).name("x7776").srcCtx("UnrollingBase.scala:28:66") } // And(b2128,b2079)
    val x7777 = withCtrl(x7786) { OpDef(op=BitAnd, inputs=List(x7776, b2064)).name("x7777").srcCtx("UnrollingBase.scala:28:66") } // And(x7776,b2064)
    val x7778 = withCtrl(x7786) { LoadBanks(List(x7717_d2_b0), List(b2062, x7775)).name("x7778").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2062, x7775),x7777)
    val x7779 = withCtrl(x7786) { LoadBanks(List(x7725_d32_b0), List(x7775)).name("x7779").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7775),x7777)
    val x7780 = withCtrl(x7786) { OpDef(op=FltMul, inputs=List(x7778, x7779)).name("x7780").srcCtx("sysml.scala:606:20") } // FltMul(x7778,x7779)
    val x7781 = withCtrl(x7786) { ReadMem(x7739_d1).name("x7781").srcCtx("sysml.scala:607:13") } // RegRead(x7739)
    val x7782 = withCtrl(x7786) { OpDef(op=FixEql, inputs=List(b2127, Const(0))).name("x7782").srcCtx("sysml.scala:607:13") } // FixEql(b2127,Const(0))
    val x7783 = withCtrl(x7786) { ReduceAccumOp(op=FltAdd, input=x7780, accum=x7781).name("x7783").srcCtx("sysml.scala:607:15") } // FltAdd(x7780,x7781)
    val x7784 = withCtrl(x7786) { OpDef(op=BitAnd, inputs=List(b2079, b2064)).name("x7784").srcCtx("UnrollingBase.scala:28:66") } // And(b2079,b2064)
    val x7785_x7739_d0 = withCtrl(x7786) { WriteMem(x7739_d0, x7783).name("x7785_x7739_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7739,x7783,x7784)
    antiDepsOf(x7785_x7739_d0)=List(x7781)
    def split1 = {
    val x7785_x7739_d1 = withCtrl(x7786) { WriteMem(x7739_d1, x7783).name("x7785_x7739_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7739,x7783,x7784)
    antiDepsOf(x7785_x7739_d1)=List(x7781)
    val x7787 = withCtrl(x7817) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7787").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7788 = withCtrl(x7817) { CounterChain(List(x7787)).name("x7788").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7787))
    val x7801 = withCtrl(x7817) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7788).name("x7801").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2080, b2064),x7788,x7740,Block((x7740) => Const(())),List(List(b2142)),List(List(b2143)))
    val b2142 = withCtrl(x7801) { CounterIter(x7787, None).name("b2142") } // b2142
    val b2143 = withCtrl(x7801) { Const(true).name("b2143") } // b2143
    val x7789 = withCtrl(x7801) { OpDef(op=FixMul, inputs=List(b2075, Const(204))).name("x7789").srcCtx("sysml.scala:603:42") } // FixMul(b2075,Const(204))
    val x7790 = withCtrl(x7801) { OpDef(op=FixAdd, inputs=List(x7789, b2142)).name("x7790").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7789,b2142)
    val x7791 = withCtrl(x7801) { OpDef(op=BitAnd, inputs=List(b2143, b2080)).name("x7791").srcCtx("UnrollingBase.scala:28:66") } // And(b2143,b2080)
    val x7792 = withCtrl(x7801) { OpDef(op=BitAnd, inputs=List(x7791, b2064)).name("x7792").srcCtx("UnrollingBase.scala:28:66") } // And(x7791,b2064)
    val x7793 = withCtrl(x7801) { LoadBanks(List(x7717_d3_b0), List(b2062, x7790)).name("x7793").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2062, x7790),x7792)
    val x7794 = withCtrl(x7801) { LoadBanks(List(x7725_d33_b0), List(x7790)).name("x7794").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7790),x7792)
    val x7795 = withCtrl(x7801) { OpDef(op=FltMul, inputs=List(x7793, x7794)).name("x7795").srcCtx("sysml.scala:606:20") } // FltMul(x7793,x7794)
    val x7796 = withCtrl(x7801) { ReadMem(x7740_d1).name("x7796").srcCtx("sysml.scala:607:13") } // RegRead(x7740)
    val x7797 = withCtrl(x7801) { OpDef(op=FixEql, inputs=List(b2142, Const(0))).name("x7797").srcCtx("sysml.scala:607:13") } // FixEql(b2142,Const(0))
    val x7798 = withCtrl(x7801) { ReduceAccumOp(op=FltAdd, input=x7795, accum=x7796).name("x7798").srcCtx("sysml.scala:607:15") } // FltAdd(x7795,x7796)
    val x7799 = withCtrl(x7801) { OpDef(op=BitAnd, inputs=List(b2080, b2064)).name("x7799").srcCtx("UnrollingBase.scala:28:66") } // And(b2080,b2064)
    val x7800_x7740_d0 = withCtrl(x7801) { WriteMem(x7740_d0, x7798).name("x7800_x7740_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7740,x7798,x7799)
    antiDepsOf(x7800_x7740_d0)=List(x7796)
    val x7800_x7740_d1 = withCtrl(x7801) { WriteMem(x7740_d1, x7798).name("x7800_x7740_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7740,x7798,x7799)
    antiDepsOf(x7800_x7740_d1)=List(x7796)
    val x7802 = withCtrl(x7817) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7802").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7803 = withCtrl(x7817) { CounterChain(List(x7802)).name("x7803").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7802))
    val x7816 = withCtrl(x7817) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7803).name("x7816").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2081, b2064),x7803,x7741,Block((x7741) => Const(())),List(List(b2157)),List(List(b2158)))
    val b2157 = withCtrl(x7816) { CounterIter(x7802, None).name("b2157") } // b2157
    val b2158 = withCtrl(x7816) { Const(true).name("b2158") } // b2158
    val x7804 = withCtrl(x7816) { OpDef(op=FixMul, inputs=List(b2076, Const(204))).name("x7804").srcCtx("sysml.scala:603:42") } // FixMul(b2076,Const(204))
    val x7805 = withCtrl(x7816) { OpDef(op=FixAdd, inputs=List(x7804, b2157)).name("x7805").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7804,b2157)
    val x7806 = withCtrl(x7816) { OpDef(op=BitAnd, inputs=List(b2158, b2081)).name("x7806").srcCtx("UnrollingBase.scala:28:66") } // And(b2158,b2081)
    val x7807 = withCtrl(x7816) { OpDef(op=BitAnd, inputs=List(x7806, b2064)).name("x7807").srcCtx("UnrollingBase.scala:28:66") } // And(x7806,b2064)
    val x7808 = withCtrl(x7816) { LoadBanks(List(x7717_d4_b0), List(b2062, x7805)).name("x7808").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2062, x7805),x7807)
    val x7809 = withCtrl(x7816) { LoadBanks(List(x7725_d34_b0), List(x7805)).name("x7809").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7805),x7807)
    val x7810 = withCtrl(x7816) { OpDef(op=FltMul, inputs=List(x7808, x7809)).name("x7810").srcCtx("sysml.scala:606:20") } // FltMul(x7808,x7809)
    val x7811 = withCtrl(x7816) { ReadMem(x7741_d1).name("x7811").srcCtx("sysml.scala:607:13") } // RegRead(x7741)
    val x7812 = withCtrl(x7816) { OpDef(op=FixEql, inputs=List(b2157, Const(0))).name("x7812").srcCtx("sysml.scala:607:13") } // FixEql(b2157,Const(0))
    val x7813 = withCtrl(x7816) { ReduceAccumOp(op=FltAdd, input=x7810, accum=x7811).name("x7813").srcCtx("sysml.scala:607:15") } // FltAdd(x7810,x7811)
    val x7814 = withCtrl(x7816) { OpDef(op=BitAnd, inputs=List(b2081, b2064)).name("x7814").srcCtx("UnrollingBase.scala:28:66") } // And(b2081,b2064)
    val x7815_x7741_d0 = withCtrl(x7816) { WriteMem(x7741_d0, x7813).name("x7815_x7741_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7741,x7813,x7814)
    antiDepsOf(x7815_x7741_d0)=List(x7811)
    val x7815_x7741_d1 = withCtrl(x7816) { WriteMem(x7741_d1, x7813).name("x7815_x7741_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7741,x7813,x7814)
    antiDepsOf(x7815_x7741_d1)=List(x7811)
    val x7844 = withCtrl(x7845) { UnitController(style=SeqPipe, level=InnerControl).name("x7844").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2064),Block(x7843))
    val x7818 = withCtrl(x7844) { OpDef(op=BitAnd, inputs=List(b2077, b2064)).name("x7818").srcCtx("sysml.scala:610:11") } // And(b2077,b2064)
    val x7819 = withCtrl(x7844) { OpDef(op=BitAnd, inputs=List(b2078, b2064)).name("x7819").srcCtx("sysml.scala:610:11") } // And(b2078,b2064)
    val x7820 = withCtrl(x7844) { OpDef(op=BitAnd, inputs=List(b2079, b2064)).name("x7820").srcCtx("sysml.scala:610:11") } // And(b2079,b2064)
    val x7821 = withCtrl(x7844) { OpDef(op=BitAnd, inputs=List(b2080, b2064)).name("x7821").srcCtx("sysml.scala:610:11") } // And(b2080,b2064)
    val x7822 = withCtrl(x7844) { OpDef(op=BitAnd, inputs=List(b2081, b2064)).name("x7822").srcCtx("sysml.scala:610:11") } // And(b2081,b2064)
    val x7823 = withCtrl(x7844) { ReadMem(x7738_d0).name("x7823").srcCtx("sysml.scala:609:11") } // RegRead(x7738)
    val x7824 = withCtrl(x7844) { ReadMem(x7737_d0).name("x7824").srcCtx("sysml.scala:609:11") } // RegRead(x7737)
    val x7825 = withCtrl(x7844) { OpDef(op=FltAdd, inputs=List(x7824, x7823)).name("x7825").srcCtx("sysml.scala:610:13") } // FltAdd(x7824,x7823)
    val x7826 = withCtrl(x7844) { OpDef(op=MuxOp, inputs=List(x7819, x7825, x7824)).name("x7826").srcCtx("sysml.scala:610:11") } // Mux(x7819,x7825,x7824)
    val x7827 = withCtrl(x7844) { OpDef(op=BitOr, inputs=List(x7818, x7819)).name("x7827").srcCtx("sysml.scala:610:11") } // Or(x7818,x7819)
    val x7828 = withCtrl(x7844) { ReadMem(x7740_d0).name("x7828").srcCtx("sysml.scala:609:11") } // RegRead(x7740)
    val x7829 = withCtrl(x7844) { ReadMem(x7739_d0).name("x7829").srcCtx("sysml.scala:609:11") } // RegRead(x7739)
    val x7830 = withCtrl(x7844) { OpDef(op=FltAdd, inputs=List(x7829, x7828)).name("x7830").srcCtx("sysml.scala:610:13") } // FltAdd(x7829,x7828)
    val x7831 = withCtrl(x7844) { OpDef(op=MuxOp, inputs=List(x7821, x7830, x7829)).name("x7831").srcCtx("sysml.scala:610:11") } // Mux(x7821,x7830,x7829)
    val x7832 = withCtrl(x7844) { OpDef(op=BitOr, inputs=List(x7820, x7821)).name("x7832").srcCtx("sysml.scala:610:11") } // Or(x7820,x7821)
    val x7833 = withCtrl(x7844) { OpDef(op=FltAdd, inputs=List(x7826, x7831)).name("x7833").srcCtx("sysml.scala:610:13") } // FltAdd(x7826,x7831)
    val x7834 = withCtrl(x7844) { OpDef(op=MuxOp, inputs=List(x7832, x7833, x7826)).name("x7834").srcCtx("sysml.scala:610:11") } // Mux(x7832,x7833,x7826)
    val x7835 = withCtrl(x7844) { OpDef(op=BitOr, inputs=List(x7827, x7832)).name("x7835").srcCtx("sysml.scala:610:11") } // Or(x7827,x7832)
    val x7836 = withCtrl(x7844) { ReadMem(x7741_d0).name("x7836").srcCtx("sysml.scala:609:11") } // RegRead(x7741)
    val x7837 = withCtrl(x7844) { OpDef(op=FltAdd, inputs=List(x7834, x7836)).name("x7837").srcCtx("sysml.scala:610:13") } // FltAdd(x7834,x7836)
    val x7838 = withCtrl(x7844) { OpDef(op=MuxOp, inputs=List(x7822, x7837, x7834)).name("x7838").srcCtx("sysml.scala:610:11") } // Mux(x7822,x7837,x7834)
    val x7839 = withCtrl(x7844) { OpDef(op=BitOr, inputs=List(x7835, x7822)).name("x7839").srcCtx("sysml.scala:610:11") } // Or(x7835,x7822)
    val x7840 = withCtrl(x7844) { ReadMem(x7733_d1).name("x7840").srcCtx("sysml.scala:610:11") } // RegRead(x7733)
    val x7841 = withCtrl(x7844) { OpDef(op=FixEql, inputs=List(b2072, Const(0))).name("x7841").srcCtx("sysml.scala:610:11") } // FixEql(b2072,Const(0))
    val x7842 = withCtrl(x7844) { OpDef(op=FltAdd, inputs=List(x7838, x7840)).name("x7842").srcCtx("sysml.scala:610:13") } // FltAdd(x7838,x7840)
    val x7843_x7733_d0 = withCtrl(x7844) { WriteMem(x7733_d0, x7842).name("x7843_x7733_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x7733,x7842,b2064)
    antiDepsOf(x7843_x7733_d0)=List(x7840)
    val x7843_x7733_d1 = withCtrl(x7844) { WriteMem(x7733_d1, x7842).name("x7843_x7733_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x7733,x7842,b2064)
    antiDepsOf(x7843_x7733_d1)=List(x7840)
    val x7846 = withCtrl(x7957) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x7846").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x7847 = withCtrl(x7957) { CounterChain(List(x7846)).name("x7847").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x7846))
    val x7956 = withCtrl(x7957) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7847).name("x7956").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2065),x7847,x7734,Block((x7734) => Const(())),List(List(b2201, b2202, b2203, b2204, b2205)),List(List(b2206, b2207, b2208, b2209, b2210)))
    val b2201 = withCtrl(x7956) { CounterIter(x7846, Some(0)).name("b2201") } // b2201
    val b2206 = withCtrl(x7956) { Const(true).name("b2206") } // b2206
    val b2202 = withCtrl(x7956) { CounterIter(x7846, Some(1)).name("b2202") } // b2202
    val b2207 = withCtrl(x7956) { Const(true).name("b2207") } // b2207
    val b2203 = withCtrl(x7956) { CounterIter(x7846, Some(2)).name("b2203") } // b2203
    val b2208 = withCtrl(x7956) { Const(true).name("b2208") } // b2208
    val b2204 = withCtrl(x7956) { CounterIter(x7846, Some(3)).name("b2204") } // b2204
    val b2209 = withCtrl(x7956) { Const(true).name("b2209") } // b2209
    val b2205 = withCtrl(x7956) { CounterIter(x7846, Some(4)).name("b2205") } // b2205
    val b2210 = withCtrl(x7956) { Const(true).name("b2210") } // b2210
    val x7848_d0 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7848_d0").srcCtx("sysml.scala:600:39:gInner") } // x7848 = RegNew(Const(0.0))
    isAccum(x7848_d0) = false
    bufferDepthOf(x7848_d0) = 1
    val x7848_d1 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7848_d1").srcCtx("sysml.scala:600:39:gInner") } // x7848 = RegNew(Const(0.0))
    isAccum(x7848_d1) = true
    bufferDepthOf(x7848_d1) = 1
    val x7849_d0 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7849_d0").srcCtx("sysml.scala:600:39:gInner") } // x7849 = RegNew(Const(0.0))
    isAccum(x7849_d0) = false
    bufferDepthOf(x7849_d0) = 1
    val x7849_d1 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7849_d1").srcCtx("sysml.scala:600:39:gInner") } // x7849 = RegNew(Const(0.0))
    isAccum(x7849_d1) = true
    bufferDepthOf(x7849_d1) = 1
    val x7850_d0 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7850_d0").srcCtx("sysml.scala:600:39:gInner") } // x7850 = RegNew(Const(0.0))
    isAccum(x7850_d0) = false
    bufferDepthOf(x7850_d0) = 1
    val x7850_d1 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7850_d1").srcCtx("sysml.scala:600:39:gInner") } // x7850 = RegNew(Const(0.0))
    isAccum(x7850_d1) = true
    bufferDepthOf(x7850_d1) = 1
    val x7851_d0 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7851_d0").srcCtx("sysml.scala:600:39:gInner") } // x7851 = RegNew(Const(0.0))
    isAccum(x7851_d0) = false
    bufferDepthOf(x7851_d0) = 1
    val x7851_d1 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7851_d1").srcCtx("sysml.scala:600:39:gInner") } // x7851 = RegNew(Const(0.0))
    isAccum(x7851_d1) = true
    bufferDepthOf(x7851_d1) = 1
    val x7852_d0 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7852_d0").srcCtx("sysml.scala:600:39:gInner") } // x7852 = RegNew(Const(0.0))
    isAccum(x7852_d0) = false
    bufferDepthOf(x7852_d0) = 1
    val x7852_d1 = withCtrl(x7956) { Reg(init=Some(0.0)).name("x7852_d1").srcCtx("sysml.scala:600:39:gInner") } // x7852 = RegNew(Const(0.0))
    isAccum(x7852_d1) = true
    bufferDepthOf(x7852_d1) = 1
    val x7928 = withCtrl(x7956) { UnitController(style=ForkJoin, level=OuterControl).name("x7928").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2065),Block(Const(())))
    val x7853 = withCtrl(x7928) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7853").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7854 = withCtrl(x7928) { CounterChain(List(x7853)).name("x7854").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7853))
    val x7867 = withCtrl(x7928) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7854).name("x7867").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2206, b2065),x7854,x7848,Block((x7848) => Const(())),List(List(b2226)),List(List(b2227)))
    val b2226 = withCtrl(x7867) { CounterIter(x7853, None).name("b2226") } // b2226
    val b2227 = withCtrl(x7867) { Const(true).name("b2227") } // b2227
    val x7855 = withCtrl(x7867) { OpDef(op=FixMul, inputs=List(b2201, Const(204))).name("x7855").srcCtx("sysml.scala:603:42") } // FixMul(b2201,Const(204))
    val x7856 = withCtrl(x7867) { OpDef(op=FixAdd, inputs=List(x7855, b2226)).name("x7856").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7855,b2226)
    val x7857 = withCtrl(x7867) { OpDef(op=BitAnd, inputs=List(b2227, b2206)).name("x7857").srcCtx("UnrollingBase.scala:28:66") } // And(b2227,b2206)
    val x7858 = withCtrl(x7867) { OpDef(op=BitAnd, inputs=List(x7857, b2065)).name("x7858").srcCtx("UnrollingBase.scala:28:66") } // And(x7857,b2065)
    val x7859 = withCtrl(x7867) { LoadBanks(List(x7717_d0_b1), List(b2063, x7856)).name("x7859").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2063, x7856),x7858)
    val x7860 = withCtrl(x7867) { LoadBanks(List(x7725_d35_b0), List(x7856)).name("x7860").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7856),x7858)
    val x7861 = withCtrl(x7867) { OpDef(op=FltMul, inputs=List(x7859, x7860)).name("x7861").srcCtx("sysml.scala:606:20") } // FltMul(x7859,x7860)
    val x7862 = withCtrl(x7867) { ReadMem(x7848_d1).name("x7862").srcCtx("sysml.scala:607:13") } // RegRead(x7848)
    val x7863 = withCtrl(x7867) { OpDef(op=FixEql, inputs=List(b2226, Const(0))).name("x7863").srcCtx("sysml.scala:607:13") } // FixEql(b2226,Const(0))
    val x7864 = withCtrl(x7867) { ReduceAccumOp(op=FltAdd, input=x7861, accum=x7862).name("x7864").srcCtx("sysml.scala:607:15") } // FltAdd(x7861,x7862)
    val x7865 = withCtrl(x7867) { OpDef(op=BitAnd, inputs=List(b2206, b2065)).name("x7865").srcCtx("UnrollingBase.scala:28:66") } // And(b2206,b2065)
    val x7866_x7848_d0 = withCtrl(x7867) { WriteMem(x7848_d0, x7864).name("x7866_x7848_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7848,x7864,x7865)
    antiDepsOf(x7866_x7848_d0)=List(x7862)
    val x7866_x7848_d1 = withCtrl(x7867) { WriteMem(x7848_d1, x7864).name("x7866_x7848_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7848,x7864,x7865)
    antiDepsOf(x7866_x7848_d1)=List(x7862)
    val x7868 = withCtrl(x7928) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7868").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7869 = withCtrl(x7928) { CounterChain(List(x7868)).name("x7869").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7868))
    val x7882 = withCtrl(x7928) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7869).name("x7882").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2207, b2065),x7869,x7849,Block((x7849) => Const(())),List(List(b2241)),List(List(b2242)))
    val b2241 = withCtrl(x7882) { CounterIter(x7868, None).name("b2241") } // b2241
    val b2242 = withCtrl(x7882) { Const(true).name("b2242") } // b2242
    val x7870 = withCtrl(x7882) { OpDef(op=FixMul, inputs=List(b2202, Const(204))).name("x7870").srcCtx("sysml.scala:603:42") } // FixMul(b2202,Const(204))
    val x7871 = withCtrl(x7882) { OpDef(op=FixAdd, inputs=List(x7870, b2241)).name("x7871").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7870,b2241)
    val x7872 = withCtrl(x7882) { OpDef(op=BitAnd, inputs=List(b2242, b2207)).name("x7872").srcCtx("UnrollingBase.scala:28:66") } // And(b2242,b2207)
    val x7873 = withCtrl(x7882) { OpDef(op=BitAnd, inputs=List(x7872, b2065)).name("x7873").srcCtx("UnrollingBase.scala:28:66") } // And(x7872,b2065)
    val x7874 = withCtrl(x7882) { LoadBanks(List(x7717_d1_b1), List(b2063, x7871)).name("x7874").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2063, x7871),x7873)
    val x7875 = withCtrl(x7882) { LoadBanks(List(x7725_d36_b0), List(x7871)).name("x7875").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7871),x7873)
    val x7876 = withCtrl(x7882) { OpDef(op=FltMul, inputs=List(x7874, x7875)).name("x7876").srcCtx("sysml.scala:606:20") } // FltMul(x7874,x7875)
    val x7877 = withCtrl(x7882) { ReadMem(x7849_d1).name("x7877").srcCtx("sysml.scala:607:13") } // RegRead(x7849)
    val x7878 = withCtrl(x7882) { OpDef(op=FixEql, inputs=List(b2241, Const(0))).name("x7878").srcCtx("sysml.scala:607:13") } // FixEql(b2241,Const(0))
    val x7879 = withCtrl(x7882) { ReduceAccumOp(op=FltAdd, input=x7876, accum=x7877).name("x7879").srcCtx("sysml.scala:607:15") } // FltAdd(x7876,x7877)
    val x7880 = withCtrl(x7882) { OpDef(op=BitAnd, inputs=List(b2207, b2065)).name("x7880").srcCtx("UnrollingBase.scala:28:66") } // And(b2207,b2065)
    val x7881_x7849_d0 = withCtrl(x7882) { WriteMem(x7849_d0, x7879).name("x7881_x7849_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7849,x7879,x7880)
    antiDepsOf(x7881_x7849_d0)=List(x7877)
    val x7881_x7849_d1 = withCtrl(x7882) { WriteMem(x7849_d1, x7879).name("x7881_x7849_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7849,x7879,x7880)
    antiDepsOf(x7881_x7849_d1)=List(x7877)
    val x7883 = withCtrl(x7928) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7883").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7884 = withCtrl(x7928) { CounterChain(List(x7883)).name("x7884").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7883))
    val x7897 = withCtrl(x7928) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7884).name("x7897").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2208, b2065),x7884,x7850,Block((x7850) => Const(())),List(List(b2256)),List(List(b2257)))
    val b2256 = withCtrl(x7897) { CounterIter(x7883, None).name("b2256") } // b2256
    val b2257 = withCtrl(x7897) { Const(true).name("b2257") } // b2257
    val x7885 = withCtrl(x7897) { OpDef(op=FixMul, inputs=List(b2203, Const(204))).name("x7885").srcCtx("sysml.scala:603:42") } // FixMul(b2203,Const(204))
    val x7886 = withCtrl(x7897) { OpDef(op=FixAdd, inputs=List(x7885, b2256)).name("x7886").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7885,b2256)
    val x7887 = withCtrl(x7897) { OpDef(op=BitAnd, inputs=List(b2257, b2208)).name("x7887").srcCtx("UnrollingBase.scala:28:66") } // And(b2257,b2208)
    val x7888 = withCtrl(x7897) { OpDef(op=BitAnd, inputs=List(x7887, b2065)).name("x7888").srcCtx("UnrollingBase.scala:28:66") } // And(x7887,b2065)
    val x7889 = withCtrl(x7897) { LoadBanks(List(x7717_d2_b1), List(b2063, x7886)).name("x7889").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2063, x7886),x7888)
    val x7890 = withCtrl(x7897) { LoadBanks(List(x7725_d37_b0), List(x7886)).name("x7890").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7886),x7888)
    val x7891 = withCtrl(x7897) { OpDef(op=FltMul, inputs=List(x7889, x7890)).name("x7891").srcCtx("sysml.scala:606:20") } // FltMul(x7889,x7890)
    val x7892 = withCtrl(x7897) { ReadMem(x7850_d1).name("x7892").srcCtx("sysml.scala:607:13") } // RegRead(x7850)
    val x7893 = withCtrl(x7897) { OpDef(op=FixEql, inputs=List(b2256, Const(0))).name("x7893").srcCtx("sysml.scala:607:13") } // FixEql(b2256,Const(0))
    val x7894 = withCtrl(x7897) { ReduceAccumOp(op=FltAdd, input=x7891, accum=x7892).name("x7894").srcCtx("sysml.scala:607:15") } // FltAdd(x7891,x7892)
    val x7895 = withCtrl(x7897) { OpDef(op=BitAnd, inputs=List(b2208, b2065)).name("x7895").srcCtx("UnrollingBase.scala:28:66") } // And(b2208,b2065)
    val x7896_x7850_d0 = withCtrl(x7897) { WriteMem(x7850_d0, x7894).name("x7896_x7850_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7850,x7894,x7895)
    antiDepsOf(x7896_x7850_d0)=List(x7892)
    val x7896_x7850_d1 = withCtrl(x7897) { WriteMem(x7850_d1, x7894).name("x7896_x7850_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7850,x7894,x7895)
    antiDepsOf(x7896_x7850_d1)=List(x7892)
    val x7898 = withCtrl(x7928) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7898").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7899 = withCtrl(x7928) { CounterChain(List(x7898)).name("x7899").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7898))
    val x7912 = withCtrl(x7928) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7899).name("x7912").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2209, b2065),x7899,x7851,Block((x7851) => Const(())),List(List(b2271)),List(List(b2272)))
    val b2271 = withCtrl(x7912) { CounterIter(x7898, None).name("b2271") } // b2271
    val b2272 = withCtrl(x7912) { Const(true).name("b2272") } // b2272
    val x7900 = withCtrl(x7912) { OpDef(op=FixMul, inputs=List(b2204, Const(204))).name("x7900").srcCtx("sysml.scala:603:42") } // FixMul(b2204,Const(204))
    val x7901 = withCtrl(x7912) { OpDef(op=FixAdd, inputs=List(x7900, b2271)).name("x7901").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7900,b2271)
    val x7902 = withCtrl(x7912) { OpDef(op=BitAnd, inputs=List(b2272, b2209)).name("x7902").srcCtx("UnrollingBase.scala:28:66") } // And(b2272,b2209)
    val x7903 = withCtrl(x7912) { OpDef(op=BitAnd, inputs=List(x7902, b2065)).name("x7903").srcCtx("UnrollingBase.scala:28:66") } // And(x7902,b2065)
    val x7904 = withCtrl(x7912) { LoadBanks(List(x7717_d3_b1), List(b2063, x7901)).name("x7904").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2063, x7901),x7903)
    val x7905 = withCtrl(x7912) { LoadBanks(List(x7725_d38_b0), List(x7901)).name("x7905").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7901),x7903)
    val x7906 = withCtrl(x7912) { OpDef(op=FltMul, inputs=List(x7904, x7905)).name("x7906").srcCtx("sysml.scala:606:20") } // FltMul(x7904,x7905)
    val x7907 = withCtrl(x7912) { ReadMem(x7851_d1).name("x7907").srcCtx("sysml.scala:607:13") } // RegRead(x7851)
    val x7908 = withCtrl(x7912) { OpDef(op=FixEql, inputs=List(b2271, Const(0))).name("x7908").srcCtx("sysml.scala:607:13") } // FixEql(b2271,Const(0))
    val x7909 = withCtrl(x7912) { ReduceAccumOp(op=FltAdd, input=x7906, accum=x7907).name("x7909").srcCtx("sysml.scala:607:15") } // FltAdd(x7906,x7907)
    val x7910 = withCtrl(x7912) { OpDef(op=BitAnd, inputs=List(b2209, b2065)).name("x7910").srcCtx("UnrollingBase.scala:28:66") } // And(b2209,b2065)
    val x7911_x7851_d0 = withCtrl(x7912) { WriteMem(x7851_d0, x7909).name("x7911_x7851_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7851,x7909,x7910)
    antiDepsOf(x7911_x7851_d0)=List(x7907)
    val x7911_x7851_d1 = withCtrl(x7912) { WriteMem(x7851_d1, x7909).name("x7911_x7851_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7851,x7909,x7910)
    antiDepsOf(x7911_x7851_d1)=List(x7907)
    val x7913 = withCtrl(x7928) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7913").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7914 = withCtrl(x7928) { CounterChain(List(x7913)).name("x7914").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7913))
    val x7927 = withCtrl(x7928) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7914).name("x7927").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2210, b2065),x7914,x7852,Block((x7852) => Const(())),List(List(b2286)),List(List(b2287)))
    val b2286 = withCtrl(x7927) { CounterIter(x7913, None).name("b2286") } // b2286
    val b2287 = withCtrl(x7927) { Const(true).name("b2287") } // b2287
    val x7915 = withCtrl(x7927) { OpDef(op=FixMul, inputs=List(b2205, Const(204))).name("x7915").srcCtx("sysml.scala:603:42") } // FixMul(b2205,Const(204))
    val x7916 = withCtrl(x7927) { OpDef(op=FixAdd, inputs=List(x7915, b2286)).name("x7916").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7915,b2286)
    val x7917 = withCtrl(x7927) { OpDef(op=BitAnd, inputs=List(b2287, b2210)).name("x7917").srcCtx("UnrollingBase.scala:28:66") } // And(b2287,b2210)
    val x7918 = withCtrl(x7927) { OpDef(op=BitAnd, inputs=List(x7917, b2065)).name("x7918").srcCtx("UnrollingBase.scala:28:66") } // And(x7917,b2065)
    val x7919 = withCtrl(x7927) { LoadBanks(List(x7717_d4_b1), List(b2063, x7916)).name("x7919").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7717,List(b2063, x7916),x7918)
    val x7920 = withCtrl(x7927) { LoadBanks(List(x7725_d39_b0), List(x7916)).name("x7920").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7916),x7918)
    val x7921 = withCtrl(x7927) { OpDef(op=FltMul, inputs=List(x7919, x7920)).name("x7921").srcCtx("sysml.scala:606:20") } // FltMul(x7919,x7920)
    val x7922 = withCtrl(x7927) { ReadMem(x7852_d1).name("x7922").srcCtx("sysml.scala:607:13") } // RegRead(x7852)
    val x7923 = withCtrl(x7927) { OpDef(op=FixEql, inputs=List(b2286, Const(0))).name("x7923").srcCtx("sysml.scala:607:13") } // FixEql(b2286,Const(0))
    val x7924 = withCtrl(x7927) { ReduceAccumOp(op=FltAdd, input=x7921, accum=x7922).name("x7924").srcCtx("sysml.scala:607:15") } // FltAdd(x7921,x7922)
    val x7925 = withCtrl(x7927) { OpDef(op=BitAnd, inputs=List(b2210, b2065)).name("x7925").srcCtx("UnrollingBase.scala:28:66") } // And(b2210,b2065)
    val x7926_x7852_d0 = withCtrl(x7927) { WriteMem(x7852_d0, x7924).name("x7926_x7852_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7852,x7924,x7925)
    antiDepsOf(x7926_x7852_d0)=List(x7922)
    val x7926_x7852_d1 = withCtrl(x7927) { WriteMem(x7852_d1, x7924).name("x7926_x7852_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7852,x7924,x7925)
    antiDepsOf(x7926_x7852_d1)=List(x7922)
    val x7955 = withCtrl(x7956) { UnitController(style=SeqPipe, level=InnerControl).name("x7955").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2065),Block(x7954))
    val x7929 = withCtrl(x7955) { OpDef(op=BitAnd, inputs=List(b2206, b2065)).name("x7929").srcCtx("sysml.scala:610:11") } // And(b2206,b2065)
    val x7930 = withCtrl(x7955) { OpDef(op=BitAnd, inputs=List(b2207, b2065)).name("x7930").srcCtx("sysml.scala:610:11") } // And(b2207,b2065)
    val x7931 = withCtrl(x7955) { OpDef(op=BitAnd, inputs=List(b2208, b2065)).name("x7931").srcCtx("sysml.scala:610:11") } // And(b2208,b2065)
    val x7932 = withCtrl(x7955) { OpDef(op=BitAnd, inputs=List(b2209, b2065)).name("x7932").srcCtx("sysml.scala:610:11") } // And(b2209,b2065)
    val x7933 = withCtrl(x7955) { OpDef(op=BitAnd, inputs=List(b2210, b2065)).name("x7933").srcCtx("sysml.scala:610:11") } // And(b2210,b2065)
    val x7934 = withCtrl(x7955) { ReadMem(x7849_d0).name("x7934").srcCtx("sysml.scala:609:11") } // RegRead(x7849)
    val x7935 = withCtrl(x7955) { ReadMem(x7848_d0).name("x7935").srcCtx("sysml.scala:609:11") } // RegRead(x7848)
    val x7936 = withCtrl(x7955) { OpDef(op=FltAdd, inputs=List(x7935, x7934)).name("x7936").srcCtx("sysml.scala:610:13") } // FltAdd(x7935,x7934)
    val x7937 = withCtrl(x7955) { OpDef(op=MuxOp, inputs=List(x7930, x7936, x7935)).name("x7937").srcCtx("sysml.scala:610:11") } // Mux(x7930,x7936,x7935)
    val x7938 = withCtrl(x7955) { OpDef(op=BitOr, inputs=List(x7929, x7930)).name("x7938").srcCtx("sysml.scala:610:11") } // Or(x7929,x7930)
    val x7939 = withCtrl(x7955) { ReadMem(x7851_d0).name("x7939").srcCtx("sysml.scala:609:11") } // RegRead(x7851)
    val x7940 = withCtrl(x7955) { ReadMem(x7850_d0).name("x7940").srcCtx("sysml.scala:609:11") } // RegRead(x7850)
    val x7941 = withCtrl(x7955) { OpDef(op=FltAdd, inputs=List(x7940, x7939)).name("x7941").srcCtx("sysml.scala:610:13") } // FltAdd(x7940,x7939)
    val x7942 = withCtrl(x7955) { OpDef(op=MuxOp, inputs=List(x7932, x7941, x7940)).name("x7942").srcCtx("sysml.scala:610:11") } // Mux(x7932,x7941,x7940)
    val x7943 = withCtrl(x7955) { OpDef(op=BitOr, inputs=List(x7931, x7932)).name("x7943").srcCtx("sysml.scala:610:11") } // Or(x7931,x7932)
    val x7944 = withCtrl(x7955) { OpDef(op=FltAdd, inputs=List(x7937, x7942)).name("x7944").srcCtx("sysml.scala:610:13") } // FltAdd(x7937,x7942)
    val x7945 = withCtrl(x7955) { OpDef(op=MuxOp, inputs=List(x7943, x7944, x7937)).name("x7945").srcCtx("sysml.scala:610:11") } // Mux(x7943,x7944,x7937)
    val x7946 = withCtrl(x7955) { OpDef(op=BitOr, inputs=List(x7938, x7943)).name("x7946").srcCtx("sysml.scala:610:11") } // Or(x7938,x7943)
    val x7947 = withCtrl(x7955) { ReadMem(x7852_d0).name("x7947").srcCtx("sysml.scala:609:11") } // RegRead(x7852)
    val x7948 = withCtrl(x7955) { OpDef(op=FltAdd, inputs=List(x7945, x7947)).name("x7948").srcCtx("sysml.scala:610:13") } // FltAdd(x7945,x7947)
    val x7949 = withCtrl(x7955) { OpDef(op=MuxOp, inputs=List(x7933, x7948, x7945)).name("x7949").srcCtx("sysml.scala:610:11") } // Mux(x7933,x7948,x7945)
    val x7950 = withCtrl(x7955) { OpDef(op=BitOr, inputs=List(x7946, x7933)).name("x7950").srcCtx("sysml.scala:610:11") } // Or(x7946,x7933)
    val x7951 = withCtrl(x7955) { ReadMem(x7734_d1).name("x7951").srcCtx("sysml.scala:610:11") } // RegRead(x7734)
    val x7952 = withCtrl(x7955) { OpDef(op=FixEql, inputs=List(b2201, Const(0))).name("x7952").srcCtx("sysml.scala:610:11") } // FixEql(b2201,Const(0))
    val x7953 = withCtrl(x7955) { OpDef(op=FltAdd, inputs=List(x7949, x7951)).name("x7953").srcCtx("sysml.scala:610:13") } // FltAdd(x7949,x7951)
    val x7954_x7734_d0 = withCtrl(x7955) { WriteMem(x7734_d0, x7953).name("x7954_x7734_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x7734,x7953,b2065)
    antiDepsOf(x7954_x7734_d0)=List(x7951)
    val x7954_x7734_d1 = withCtrl(x7955) { WriteMem(x7734_d1, x7953).name("x7954_x7734_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x7734,x7953,b2065)
    antiDepsOf(x7954_x7734_d1)=List(x7951)
    val x7958_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7958_d0").srcCtx("sysml.scala:597:32:g") } // x7958 = RegNew(Const(0.0))
    isAccum(x7958_d0) = false
    bufferDepthOf(x7958_d0) = 4
    val x7958_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7958_d1").srcCtx("sysml.scala:597:32:g") } // x7958 = RegNew(Const(0.0))
    isAccum(x7958_d1) = true
    bufferDepthOf(x7958_d1) = 1
    val x7959_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7959_d0").srcCtx("sysml.scala:597:32:g") } // x7959 = RegNew(Const(0.0))
    isAccum(x7959_d0) = false
    bufferDepthOf(x7959_d0) = 4
    val x7959_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x7959_d1").srcCtx("sysml.scala:597:32:g") } // x7959 = RegNew(Const(0.0))
    isAccum(x7959_d1) = true
    bufferDepthOf(x7959_d1) = 1
    val x8182 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8182").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x7960 = withCtrl(x8182) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x7960").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x7961 = withCtrl(x8182) { CounterChain(List(x7960)).name("x7961").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x7960))
    val x8070 = withCtrl(x8182) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7961).name("x8070").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2064),x7961,x7958,Block((x7958) => Const(())),List(List(b2337, b2338, b2339, b2340, b2341)),List(List(b2342, b2343, b2344, b2345, b2346)))
    val b2337 = withCtrl(x8070) { CounterIter(x7960, Some(0)).name("b2337") } // b2337
    val b2342 = withCtrl(x8070) { Const(true).name("b2342") } // b2342
    val b2338 = withCtrl(x8070) { CounterIter(x7960, Some(1)).name("b2338") } // b2338
    val b2343 = withCtrl(x8070) { Const(true).name("b2343") } // b2343
    val b2339 = withCtrl(x8070) { CounterIter(x7960, Some(2)).name("b2339") } // b2339
    val b2344 = withCtrl(x8070) { Const(true).name("b2344") } // b2344
    val b2340 = withCtrl(x8070) { CounterIter(x7960, Some(3)).name("b2340") } // b2340
    val b2345 = withCtrl(x8070) { Const(true).name("b2345") } // b2345
    val b2341 = withCtrl(x8070) { CounterIter(x7960, Some(4)).name("b2341") } // b2341
    val b2346 = withCtrl(x8070) { Const(true).name("b2346") } // b2346
    val x7962_d0 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7962_d0").srcCtx("sysml.scala:600:39:gInner") } // x7962 = RegNew(Const(0.0))
    isAccum(x7962_d0) = false
    bufferDepthOf(x7962_d0) = 1
    val x7962_d1 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7962_d1").srcCtx("sysml.scala:600:39:gInner") } // x7962 = RegNew(Const(0.0))
    isAccum(x7962_d1) = true
    bufferDepthOf(x7962_d1) = 1
    val x7963_d0 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7963_d0").srcCtx("sysml.scala:600:39:gInner") } // x7963 = RegNew(Const(0.0))
    isAccum(x7963_d0) = false
    bufferDepthOf(x7963_d0) = 1
    val x7963_d1 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7963_d1").srcCtx("sysml.scala:600:39:gInner") } // x7963 = RegNew(Const(0.0))
    isAccum(x7963_d1) = true
    bufferDepthOf(x7963_d1) = 1
    val x7964_d0 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7964_d0").srcCtx("sysml.scala:600:39:gInner") } // x7964 = RegNew(Const(0.0))
    isAccum(x7964_d0) = false
    bufferDepthOf(x7964_d0) = 1
    val x7964_d1 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7964_d1").srcCtx("sysml.scala:600:39:gInner") } // x7964 = RegNew(Const(0.0))
    isAccum(x7964_d1) = true
    bufferDepthOf(x7964_d1) = 1
    val x7965_d0 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7965_d0").srcCtx("sysml.scala:600:39:gInner") } // x7965 = RegNew(Const(0.0))
    isAccum(x7965_d0) = false
    bufferDepthOf(x7965_d0) = 1
    val x7965_d1 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7965_d1").srcCtx("sysml.scala:600:39:gInner") } // x7965 = RegNew(Const(0.0))
    isAccum(x7965_d1) = true
    bufferDepthOf(x7965_d1) = 1
    val x7966_d0 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7966_d0").srcCtx("sysml.scala:600:39:gInner") } // x7966 = RegNew(Const(0.0))
    isAccum(x7966_d0) = false
    bufferDepthOf(x7966_d0) = 1
    val x7966_d1 = withCtrl(x8070) { Reg(init=Some(0.0)).name("x7966_d1").srcCtx("sysml.scala:600:39:gInner") } // x7966 = RegNew(Const(0.0))
    isAccum(x7966_d1) = true
    bufferDepthOf(x7966_d1) = 1
    val x8042 = withCtrl(x8070) { UnitController(style=ForkJoin, level=OuterControl).name("x8042").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2064),Block(Const(())))
    val x7967 = withCtrl(x8042) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7967").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7968 = withCtrl(x8042) { CounterChain(List(x7967)).name("x7968").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7967))
    val x7981 = withCtrl(x8042) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7968).name("x7981").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2342, b2064),x7968,x7962,Block((x7962) => Const(())),List(List(b2362)),List(List(b2363)))
    val b2362 = withCtrl(x7981) { CounterIter(x7967, None).name("b2362") } // b2362
    val b2363 = withCtrl(x7981) { Const(true).name("b2363") } // b2363
    val x7969 = withCtrl(x7981) { OpDef(op=FixMul, inputs=List(b2337, Const(204))).name("x7969").srcCtx("sysml.scala:603:42") } // FixMul(b2337,Const(204))
    val x7970 = withCtrl(x7981) { OpDef(op=FixAdd, inputs=List(x7969, b2362)).name("x7970").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7969,b2362)
    val x7971 = withCtrl(x7981) { OpDef(op=BitAnd, inputs=List(b2363, b2342)).name("x7971").srcCtx("UnrollingBase.scala:28:66") } // And(b2363,b2342)
    val x7972 = withCtrl(x7981) { OpDef(op=BitAnd, inputs=List(x7971, b2064)).name("x7972").srcCtx("UnrollingBase.scala:28:66") } // And(x7971,b2064)
    val x7973 = withCtrl(x7981) { LoadBanks(List(x7718_d0_b0), List(b2062, x7970)).name("x7973").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2062, x7970),x7972)
    val x7974 = withCtrl(x7981) { LoadBanks(List(x7725_d20_b0), List(x7970)).name("x7974").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7970),x7972)
    val x7975 = withCtrl(x7981) { OpDef(op=FltMul, inputs=List(x7973, x7974)).name("x7975").srcCtx("sysml.scala:606:20") } // FltMul(x7973,x7974)
    val x7976 = withCtrl(x7981) { ReadMem(x7962_d1).name("x7976").srcCtx("sysml.scala:607:13") } // RegRead(x7962)
    val x7977 = withCtrl(x7981) { OpDef(op=FixEql, inputs=List(b2362, Const(0))).name("x7977").srcCtx("sysml.scala:607:13") } // FixEql(b2362,Const(0))
    val x7978 = withCtrl(x7981) { ReduceAccumOp(op=FltAdd, input=x7975, accum=x7976).name("x7978").srcCtx("sysml.scala:607:15") } // FltAdd(x7975,x7976)
    val x7979 = withCtrl(x7981) { OpDef(op=BitAnd, inputs=List(b2342, b2064)).name("x7979").srcCtx("UnrollingBase.scala:28:66") } // And(b2342,b2064)
    val x7980_x7962_d0 = withCtrl(x7981) { WriteMem(x7962_d0, x7978).name("x7980_x7962_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7962,x7978,x7979)
    antiDepsOf(x7980_x7962_d0)=List(x7976)
    val x7980_x7962_d1 = withCtrl(x7981) { WriteMem(x7962_d1, x7978).name("x7980_x7962_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7962,x7978,x7979)
    antiDepsOf(x7980_x7962_d1)=List(x7976)
    val x7982 = withCtrl(x8042) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7982").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7983 = withCtrl(x8042) { CounterChain(List(x7982)).name("x7983").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7982))
    val x7996 = withCtrl(x8042) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7983).name("x7996").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2343, b2064),x7983,x7963,Block((x7963) => Const(())),List(List(b2377)),List(List(b2378)))
    val b2377 = withCtrl(x7996) { CounterIter(x7982, None).name("b2377") } // b2377
    val b2378 = withCtrl(x7996) { Const(true).name("b2378") } // b2378
    val x7984 = withCtrl(x7996) { OpDef(op=FixMul, inputs=List(b2338, Const(204))).name("x7984").srcCtx("sysml.scala:603:42") } // FixMul(b2338,Const(204))
    val x7985 = withCtrl(x7996) { OpDef(op=FixAdd, inputs=List(x7984, b2377)).name("x7985").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7984,b2377)
    val x7986 = withCtrl(x7996) { OpDef(op=BitAnd, inputs=List(b2378, b2343)).name("x7986").srcCtx("UnrollingBase.scala:28:66") } // And(b2378,b2343)
    val x7987 = withCtrl(x7996) { OpDef(op=BitAnd, inputs=List(x7986, b2064)).name("x7987").srcCtx("UnrollingBase.scala:28:66") } // And(x7986,b2064)
    val x7988 = withCtrl(x7996) { LoadBanks(List(x7718_d1_b0), List(b2062, x7985)).name("x7988").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2062, x7985),x7987)
    val x7989 = withCtrl(x7996) { LoadBanks(List(x7725_d21_b0), List(x7985)).name("x7989").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x7985),x7987)
    val x7990 = withCtrl(x7996) { OpDef(op=FltMul, inputs=List(x7988, x7989)).name("x7990").srcCtx("sysml.scala:606:20") } // FltMul(x7988,x7989)
    val x7991 = withCtrl(x7996) { ReadMem(x7963_d1).name("x7991").srcCtx("sysml.scala:607:13") } // RegRead(x7963)
    val x7992 = withCtrl(x7996) { OpDef(op=FixEql, inputs=List(b2377, Const(0))).name("x7992").srcCtx("sysml.scala:607:13") } // FixEql(b2377,Const(0))
    val x7993 = withCtrl(x7996) { ReduceAccumOp(op=FltAdd, input=x7990, accum=x7991).name("x7993").srcCtx("sysml.scala:607:15") } // FltAdd(x7990,x7991)
    val x7994 = withCtrl(x7996) { OpDef(op=BitAnd, inputs=List(b2343, b2064)).name("x7994").srcCtx("UnrollingBase.scala:28:66") } // And(b2343,b2064)
    val x7995_x7963_d0 = withCtrl(x7996) { WriteMem(x7963_d0, x7993).name("x7995_x7963_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7963,x7993,x7994)
    antiDepsOf(x7995_x7963_d0)=List(x7991)
    val x7995_x7963_d1 = withCtrl(x7996) { WriteMem(x7963_d1, x7993).name("x7995_x7963_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7963,x7993,x7994)
    antiDepsOf(x7995_x7963_d1)=List(x7991)
    val x7997 = withCtrl(x8042) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x7997").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x7998 = withCtrl(x8042) { CounterChain(List(x7997)).name("x7998").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x7997))
    val x8011 = withCtrl(x8042) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7998).name("x8011").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2344, b2064),x7998,x7964,Block((x7964) => Const(())),List(List(b2392)),List(List(b2393)))
    val b2392 = withCtrl(x8011) { CounterIter(x7997, None).name("b2392") } // b2392
    val b2393 = withCtrl(x8011) { Const(true).name("b2393") } // b2393
    val x7999 = withCtrl(x8011) { OpDef(op=FixMul, inputs=List(b2339, Const(204))).name("x7999").srcCtx("sysml.scala:603:42") } // FixMul(b2339,Const(204))
    val x8000 = withCtrl(x8011) { OpDef(op=FixAdd, inputs=List(x7999, b2392)).name("x8000").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x7999,b2392)
    val x8001 = withCtrl(x8011) { OpDef(op=BitAnd, inputs=List(b2393, b2344)).name("x8001").srcCtx("UnrollingBase.scala:28:66") } // And(b2393,b2344)
    val x8002 = withCtrl(x8011) { OpDef(op=BitAnd, inputs=List(x8001, b2064)).name("x8002").srcCtx("UnrollingBase.scala:28:66") } // And(x8001,b2064)
    val x8003 = withCtrl(x8011) { LoadBanks(List(x7718_d2_b0), List(b2062, x8000)).name("x8003").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2062, x8000),x8002)
    val x8004 = withCtrl(x8011) { LoadBanks(List(x7725_d22_b0), List(x8000)).name("x8004").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8000),x8002)
    val x8005 = withCtrl(x8011) { OpDef(op=FltMul, inputs=List(x8003, x8004)).name("x8005").srcCtx("sysml.scala:606:20") } // FltMul(x8003,x8004)
    val x8006 = withCtrl(x8011) { ReadMem(x7964_d1).name("x8006").srcCtx("sysml.scala:607:13") } // RegRead(x7964)
    val x8007 = withCtrl(x8011) { OpDef(op=FixEql, inputs=List(b2392, Const(0))).name("x8007").srcCtx("sysml.scala:607:13") } // FixEql(b2392,Const(0))
    val x8008 = withCtrl(x8011) { ReduceAccumOp(op=FltAdd, input=x8005, accum=x8006).name("x8008").srcCtx("sysml.scala:607:15") } // FltAdd(x8005,x8006)
    val x8009 = withCtrl(x8011) { OpDef(op=BitAnd, inputs=List(b2344, b2064)).name("x8009").srcCtx("UnrollingBase.scala:28:66") } // And(b2344,b2064)
    val x8010_x7964_d0 = withCtrl(x8011) { WriteMem(x7964_d0, x8008).name("x8010_x7964_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7964,x8008,x8009)
    antiDepsOf(x8010_x7964_d0)=List(x8006)
    val x8010_x7964_d1 = withCtrl(x8011) { WriteMem(x7964_d1, x8008).name("x8010_x7964_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7964,x8008,x8009)
    antiDepsOf(x8010_x7964_d1)=List(x8006)
    val x8012 = withCtrl(x8042) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8012").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8013 = withCtrl(x8042) { CounterChain(List(x8012)).name("x8013").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8012))
    val x8026 = withCtrl(x8042) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8013).name("x8026").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2345, b2064),x8013,x7965,Block((x7965) => Const(())),List(List(b2407)),List(List(b2408)))
    val b2407 = withCtrl(x8026) { CounterIter(x8012, None).name("b2407") } // b2407
    val b2408 = withCtrl(x8026) { Const(true).name("b2408") } // b2408
    val x8014 = withCtrl(x8026) { OpDef(op=FixMul, inputs=List(b2340, Const(204))).name("x8014").srcCtx("sysml.scala:603:42") } // FixMul(b2340,Const(204))
    val x8015 = withCtrl(x8026) { OpDef(op=FixAdd, inputs=List(x8014, b2407)).name("x8015").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8014,b2407)
    val x8016 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(b2408, b2345)).name("x8016").srcCtx("UnrollingBase.scala:28:66") } // And(b2408,b2345)
    val x8017 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(x8016, b2064)).name("x8017").srcCtx("UnrollingBase.scala:28:66") } // And(x8016,b2064)
    val x8018 = withCtrl(x8026) { LoadBanks(List(x7718_d3_b0), List(b2062, x8015)).name("x8018").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2062, x8015),x8017)
    val x8019 = withCtrl(x8026) { LoadBanks(List(x7725_d23_b0), List(x8015)).name("x8019").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8015),x8017)
    val x8020 = withCtrl(x8026) { OpDef(op=FltMul, inputs=List(x8018, x8019)).name("x8020").srcCtx("sysml.scala:606:20") } // FltMul(x8018,x8019)
    val x8021 = withCtrl(x8026) { ReadMem(x7965_d1).name("x8021").srcCtx("sysml.scala:607:13") } // RegRead(x7965)
    val x8022 = withCtrl(x8026) { OpDef(op=FixEql, inputs=List(b2407, Const(0))).name("x8022").srcCtx("sysml.scala:607:13") } // FixEql(b2407,Const(0))
    val x8023 = withCtrl(x8026) { ReduceAccumOp(op=FltAdd, input=x8020, accum=x8021).name("x8023").srcCtx("sysml.scala:607:15") } // FltAdd(x8020,x8021)
    val x8024 = withCtrl(x8026) { OpDef(op=BitAnd, inputs=List(b2345, b2064)).name("x8024").srcCtx("UnrollingBase.scala:28:66") } // And(b2345,b2064)
    val x8025_x7965_d0 = withCtrl(x8026) { WriteMem(x7965_d0, x8023).name("x8025_x7965_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7965,x8023,x8024)
    antiDepsOf(x8025_x7965_d0)=List(x8021)
    val x8025_x7965_d1 = withCtrl(x8026) { WriteMem(x7965_d1, x8023).name("x8025_x7965_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7965,x8023,x8024)
    antiDepsOf(x8025_x7965_d1)=List(x8021)
    val x8027 = withCtrl(x8042) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8027").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8028 = withCtrl(x8042) { CounterChain(List(x8027)).name("x8028").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8027))
    val x8041 = withCtrl(x8042) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8028).name("x8041").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2346, b2064),x8028,x7966,Block((x7966) => Const(())),List(List(b2422)),List(List(b2423)))
    val b2422 = withCtrl(x8041) { CounterIter(x8027, None).name("b2422") } // b2422
    val b2423 = withCtrl(x8041) { Const(true).name("b2423") } // b2423
    val x8029 = withCtrl(x8041) { OpDef(op=FixMul, inputs=List(b2341, Const(204))).name("x8029").srcCtx("sysml.scala:603:42") } // FixMul(b2341,Const(204))
    val x8030 = withCtrl(x8041) { OpDef(op=FixAdd, inputs=List(x8029, b2422)).name("x8030").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8029,b2422)
    val x8031 = withCtrl(x8041) { OpDef(op=BitAnd, inputs=List(b2423, b2346)).name("x8031").srcCtx("UnrollingBase.scala:28:66") } // And(b2423,b2346)
    val x8032 = withCtrl(x8041) { OpDef(op=BitAnd, inputs=List(x8031, b2064)).name("x8032").srcCtx("UnrollingBase.scala:28:66") } // And(x8031,b2064)
    val x8033 = withCtrl(x8041) { LoadBanks(List(x7718_d4_b0), List(b2062, x8030)).name("x8033").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2062, x8030),x8032)
    val x8034 = withCtrl(x8041) { LoadBanks(List(x7725_d24_b0), List(x8030)).name("x8034").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8030),x8032)
    val x8035 = withCtrl(x8041) { OpDef(op=FltMul, inputs=List(x8033, x8034)).name("x8035").srcCtx("sysml.scala:606:20") } // FltMul(x8033,x8034)
    val x8036 = withCtrl(x8041) { ReadMem(x7966_d1).name("x8036").srcCtx("sysml.scala:607:13") } // RegRead(x7966)
    val x8037 = withCtrl(x8041) { OpDef(op=FixEql, inputs=List(b2422, Const(0))).name("x8037").srcCtx("sysml.scala:607:13") } // FixEql(b2422,Const(0))
    val x8038 = withCtrl(x8041) { ReduceAccumOp(op=FltAdd, input=x8035, accum=x8036).name("x8038").srcCtx("sysml.scala:607:15") } // FltAdd(x8035,x8036)
    val x8039 = withCtrl(x8041) { OpDef(op=BitAnd, inputs=List(b2346, b2064)).name("x8039").srcCtx("UnrollingBase.scala:28:66") } // And(b2346,b2064)
    val x8040_x7966_d0 = withCtrl(x8041) { WriteMem(x7966_d0, x8038).name("x8040_x7966_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x7966,x8038,x8039)
    antiDepsOf(x8040_x7966_d0)=List(x8036)
    val x8040_x7966_d1 = withCtrl(x8041) { WriteMem(x7966_d1, x8038).name("x8040_x7966_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x7966,x8038,x8039)
    antiDepsOf(x8040_x7966_d1)=List(x8036)
    val x8069 = withCtrl(x8070) { UnitController(style=SeqPipe, level=InnerControl).name("x8069").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2064),Block(x8068))
    val x8043 = withCtrl(x8069) { OpDef(op=BitAnd, inputs=List(b2342, b2064)).name("x8043").srcCtx("sysml.scala:610:11") } // And(b2342,b2064)
    val x8044 = withCtrl(x8069) { OpDef(op=BitAnd, inputs=List(b2343, b2064)).name("x8044").srcCtx("sysml.scala:610:11") } // And(b2343,b2064)
    val x8045 = withCtrl(x8069) { OpDef(op=BitAnd, inputs=List(b2344, b2064)).name("x8045").srcCtx("sysml.scala:610:11") } // And(b2344,b2064)
    val x8046 = withCtrl(x8069) { OpDef(op=BitAnd, inputs=List(b2345, b2064)).name("x8046").srcCtx("sysml.scala:610:11") } // And(b2345,b2064)
    val x8047 = withCtrl(x8069) { OpDef(op=BitAnd, inputs=List(b2346, b2064)).name("x8047").srcCtx("sysml.scala:610:11") } // And(b2346,b2064)
    val x8048 = withCtrl(x8069) { ReadMem(x7963_d0).name("x8048").srcCtx("sysml.scala:609:11") } // RegRead(x7963)
    val x8049 = withCtrl(x8069) { ReadMem(x7962_d0).name("x8049").srcCtx("sysml.scala:609:11") } // RegRead(x7962)
    val x8050 = withCtrl(x8069) { OpDef(op=FltAdd, inputs=List(x8049, x8048)).name("x8050").srcCtx("sysml.scala:610:13") } // FltAdd(x8049,x8048)
    val x8051 = withCtrl(x8069) { OpDef(op=MuxOp, inputs=List(x8044, x8050, x8049)).name("x8051").srcCtx("sysml.scala:610:11") } // Mux(x8044,x8050,x8049)
    val x8052 = withCtrl(x8069) { OpDef(op=BitOr, inputs=List(x8043, x8044)).name("x8052").srcCtx("sysml.scala:610:11") } // Or(x8043,x8044)
    val x8053 = withCtrl(x8069) { ReadMem(x7965_d0).name("x8053").srcCtx("sysml.scala:609:11") } // RegRead(x7965)
    val x8054 = withCtrl(x8069) { ReadMem(x7964_d0).name("x8054").srcCtx("sysml.scala:609:11") } // RegRead(x7964)
    val x8055 = withCtrl(x8069) { OpDef(op=FltAdd, inputs=List(x8054, x8053)).name("x8055").srcCtx("sysml.scala:610:13") } // FltAdd(x8054,x8053)
    val x8056 = withCtrl(x8069) { OpDef(op=MuxOp, inputs=List(x8046, x8055, x8054)).name("x8056").srcCtx("sysml.scala:610:11") } // Mux(x8046,x8055,x8054)
    val x8057 = withCtrl(x8069) { OpDef(op=BitOr, inputs=List(x8045, x8046)).name("x8057").srcCtx("sysml.scala:610:11") } // Or(x8045,x8046)
    val x8058 = withCtrl(x8069) { OpDef(op=FltAdd, inputs=List(x8051, x8056)).name("x8058").srcCtx("sysml.scala:610:13") } // FltAdd(x8051,x8056)
    val x8059 = withCtrl(x8069) { OpDef(op=MuxOp, inputs=List(x8057, x8058, x8051)).name("x8059").srcCtx("sysml.scala:610:11") } // Mux(x8057,x8058,x8051)
    val x8060 = withCtrl(x8069) { OpDef(op=BitOr, inputs=List(x8052, x8057)).name("x8060").srcCtx("sysml.scala:610:11") } // Or(x8052,x8057)
    val x8061 = withCtrl(x8069) { ReadMem(x7966_d0).name("x8061").srcCtx("sysml.scala:609:11") } // RegRead(x7966)
    val x8062 = withCtrl(x8069) { OpDef(op=FltAdd, inputs=List(x8059, x8061)).name("x8062").srcCtx("sysml.scala:610:13") } // FltAdd(x8059,x8061)
    val x8063 = withCtrl(x8069) { OpDef(op=MuxOp, inputs=List(x8047, x8062, x8059)).name("x8063").srcCtx("sysml.scala:610:11") } // Mux(x8047,x8062,x8059)
    val x8064 = withCtrl(x8069) { OpDef(op=BitOr, inputs=List(x8060, x8047)).name("x8064").srcCtx("sysml.scala:610:11") } // Or(x8060,x8047)
    val x8065 = withCtrl(x8069) { ReadMem(x7958_d1).name("x8065").srcCtx("sysml.scala:610:11") } // RegRead(x7958)
    val x8066 = withCtrl(x8069) { OpDef(op=FixEql, inputs=List(b2337, Const(0))).name("x8066").srcCtx("sysml.scala:610:11") } // FixEql(b2337,Const(0))
    val x8067 = withCtrl(x8069) { OpDef(op=FltAdd, inputs=List(x8063, x8065)).name("x8067").srcCtx("sysml.scala:610:13") } // FltAdd(x8063,x8065)
    val x8068_x7958_d0 = withCtrl(x8069) { WriteMem(x7958_d0, x8067).name("x8068_x7958_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x7958,x8067,b2064)
    antiDepsOf(x8068_x7958_d0)=List(x8065)
    val x8068_x7958_d1 = withCtrl(x8069) { WriteMem(x7958_d1, x8067).name("x8068_x7958_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x7958,x8067,b2064)
    antiDepsOf(x8068_x7958_d1)=List(x8065)
    val x8071 = withCtrl(x8182) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x8071").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x8072 = withCtrl(x8182) { CounterChain(List(x8071)).name("x8072").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x8071))
    val x8181 = withCtrl(x8182) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8072).name("x8181").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2065),x8072,x7959,Block((x7959) => Const(())),List(List(b2466, b2467, b2468, b2469, b2470)),List(List(b2471, b2472, b2473, b2474, b2475)))
    val b2466 = withCtrl(x8181) { CounterIter(x8071, Some(0)).name("b2466") } // b2466
    val b2471 = withCtrl(x8181) { Const(true).name("b2471") } // b2471
    val b2467 = withCtrl(x8181) { CounterIter(x8071, Some(1)).name("b2467") } // b2467
    val b2472 = withCtrl(x8181) { Const(true).name("b2472") } // b2472
    val b2468 = withCtrl(x8181) { CounterIter(x8071, Some(2)).name("b2468") } // b2468
    val b2473 = withCtrl(x8181) { Const(true).name("b2473") } // b2473
    val b2469 = withCtrl(x8181) { CounterIter(x8071, Some(3)).name("b2469") } // b2469
    val b2474 = withCtrl(x8181) { Const(true).name("b2474") } // b2474
    val b2470 = withCtrl(x8181) { CounterIter(x8071, Some(4)).name("b2470") } // b2470
    val b2475 = withCtrl(x8181) { Const(true).name("b2475") } // b2475
    val x8073_d0 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8073_d0").srcCtx("sysml.scala:600:39:gInner") } // x8073 = RegNew(Const(0.0))
    isAccum(x8073_d0) = false
    bufferDepthOf(x8073_d0) = 1
    val x8073_d1 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8073_d1").srcCtx("sysml.scala:600:39:gInner") } // x8073 = RegNew(Const(0.0))
    isAccum(x8073_d1) = true
    bufferDepthOf(x8073_d1) = 1
    val x8074_d0 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8074_d0").srcCtx("sysml.scala:600:39:gInner") } // x8074 = RegNew(Const(0.0))
    isAccum(x8074_d0) = false
    bufferDepthOf(x8074_d0) = 1
    val x8074_d1 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8074_d1").srcCtx("sysml.scala:600:39:gInner") } // x8074 = RegNew(Const(0.0))
    isAccum(x8074_d1) = true
    bufferDepthOf(x8074_d1) = 1
    val x8075_d0 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8075_d0").srcCtx("sysml.scala:600:39:gInner") } // x8075 = RegNew(Const(0.0))
    isAccum(x8075_d0) = false
    bufferDepthOf(x8075_d0) = 1
    val x8075_d1 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8075_d1").srcCtx("sysml.scala:600:39:gInner") } // x8075 = RegNew(Const(0.0))
    isAccum(x8075_d1) = true
    bufferDepthOf(x8075_d1) = 1
    val x8076_d0 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8076_d0").srcCtx("sysml.scala:600:39:gInner") } // x8076 = RegNew(Const(0.0))
    isAccum(x8076_d0) = false
    bufferDepthOf(x8076_d0) = 1
    val x8076_d1 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8076_d1").srcCtx("sysml.scala:600:39:gInner") } // x8076 = RegNew(Const(0.0))
    isAccum(x8076_d1) = true
    bufferDepthOf(x8076_d1) = 1
    val x8077_d0 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8077_d0").srcCtx("sysml.scala:600:39:gInner") } // x8077 = RegNew(Const(0.0))
    isAccum(x8077_d0) = false
    bufferDepthOf(x8077_d0) = 1
    val x8077_d1 = withCtrl(x8181) { Reg(init=Some(0.0)).name("x8077_d1").srcCtx("sysml.scala:600:39:gInner") } // x8077 = RegNew(Const(0.0))
    isAccum(x8077_d1) = true
    bufferDepthOf(x8077_d1) = 1
    val x8153 = withCtrl(x8181) { UnitController(style=ForkJoin, level=OuterControl).name("x8153").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2065),Block(Const(())))
    val x8078 = withCtrl(x8153) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8078").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8079 = withCtrl(x8153) { CounterChain(List(x8078)).name("x8079").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8078))
    val x8092 = withCtrl(x8153) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8079).name("x8092").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2471, b2065),x8079,x8073,Block((x8073) => Const(())),List(List(b2491)),List(List(b2492)))
    val b2491 = withCtrl(x8092) { CounterIter(x8078, None).name("b2491") } // b2491
    val b2492 = withCtrl(x8092) { Const(true).name("b2492") } // b2492
    val x8080 = withCtrl(x8092) { OpDef(op=FixMul, inputs=List(b2466, Const(204))).name("x8080").srcCtx("sysml.scala:603:42") } // FixMul(b2466,Const(204))
    val x8081 = withCtrl(x8092) { OpDef(op=FixAdd, inputs=List(x8080, b2491)).name("x8081").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8080,b2491)
    val x8082 = withCtrl(x8092) { OpDef(op=BitAnd, inputs=List(b2492, b2471)).name("x8082").srcCtx("UnrollingBase.scala:28:66") } // And(b2492,b2471)
    val x8083 = withCtrl(x8092) { OpDef(op=BitAnd, inputs=List(x8082, b2065)).name("x8083").srcCtx("UnrollingBase.scala:28:66") } // And(x8082,b2065)
    val x8084 = withCtrl(x8092) { LoadBanks(List(x7718_d0_b1), List(b2063, x8081)).name("x8084").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2063, x8081),x8083)
    val x8085 = withCtrl(x8092) { LoadBanks(List(x7725_d25_b0), List(x8081)).name("x8085").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8081),x8083)
    val x8086 = withCtrl(x8092) { OpDef(op=FltMul, inputs=List(x8084, x8085)).name("x8086").srcCtx("sysml.scala:606:20") } // FltMul(x8084,x8085)
    val x8087 = withCtrl(x8092) { ReadMem(x8073_d1).name("x8087").srcCtx("sysml.scala:607:13") } // RegRead(x8073)
    val x8088 = withCtrl(x8092) { OpDef(op=FixEql, inputs=List(b2491, Const(0))).name("x8088").srcCtx("sysml.scala:607:13") } // FixEql(b2491,Const(0))
    val x8089 = withCtrl(x8092) { ReduceAccumOp(op=FltAdd, input=x8086, accum=x8087).name("x8089").srcCtx("sysml.scala:607:15") } // FltAdd(x8086,x8087)
    val x8090 = withCtrl(x8092) { OpDef(op=BitAnd, inputs=List(b2471, b2065)).name("x8090").srcCtx("UnrollingBase.scala:28:66") } // And(b2471,b2065)
    val x8091_x8073_d0 = withCtrl(x8092) { WriteMem(x8073_d0, x8089).name("x8091_x8073_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8073,x8089,x8090)
    antiDepsOf(x8091_x8073_d0)=List(x8087)
    val x8091_x8073_d1 = withCtrl(x8092) { WriteMem(x8073_d1, x8089).name("x8091_x8073_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8073,x8089,x8090)
    antiDepsOf(x8091_x8073_d1)=List(x8087)
    val x8093 = withCtrl(x8153) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8093").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8094 = withCtrl(x8153) { CounterChain(List(x8093)).name("x8094").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8093))
    val x8107 = withCtrl(x8153) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8094).name("x8107").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2472, b2065),x8094,x8074,Block((x8074) => Const(())),List(List(b2506)),List(List(b2507)))
    val b2506 = withCtrl(x8107) { CounterIter(x8093, None).name("b2506") } // b2506
    def split2 = {
    val b2507 = withCtrl(x8107) { Const(true).name("b2507") } // b2507
    val x8095 = withCtrl(x8107) { OpDef(op=FixMul, inputs=List(b2467, Const(204))).name("x8095").srcCtx("sysml.scala:603:42") } // FixMul(b2467,Const(204))
    val x8096 = withCtrl(x8107) { OpDef(op=FixAdd, inputs=List(x8095, b2506)).name("x8096").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8095,b2506)
    val x8097 = withCtrl(x8107) { OpDef(op=BitAnd, inputs=List(b2507, b2472)).name("x8097").srcCtx("UnrollingBase.scala:28:66") } // And(b2507,b2472)
    val x8098 = withCtrl(x8107) { OpDef(op=BitAnd, inputs=List(x8097, b2065)).name("x8098").srcCtx("UnrollingBase.scala:28:66") } // And(x8097,b2065)
    val x8099 = withCtrl(x8107) { LoadBanks(List(x7718_d1_b1), List(b2063, x8096)).name("x8099").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2063, x8096),x8098)
    val x8100 = withCtrl(x8107) { LoadBanks(List(x7725_d26_b0), List(x8096)).name("x8100").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8096),x8098)
    val x8101 = withCtrl(x8107) { OpDef(op=FltMul, inputs=List(x8099, x8100)).name("x8101").srcCtx("sysml.scala:606:20") } // FltMul(x8099,x8100)
    val x8102 = withCtrl(x8107) { ReadMem(x8074_d1).name("x8102").srcCtx("sysml.scala:607:13") } // RegRead(x8074)
    val x8103 = withCtrl(x8107) { OpDef(op=FixEql, inputs=List(b2506, Const(0))).name("x8103").srcCtx("sysml.scala:607:13") } // FixEql(b2506,Const(0))
    val x8104 = withCtrl(x8107) { ReduceAccumOp(op=FltAdd, input=x8101, accum=x8102).name("x8104").srcCtx("sysml.scala:607:15") } // FltAdd(x8101,x8102)
    val x8105 = withCtrl(x8107) { OpDef(op=BitAnd, inputs=List(b2472, b2065)).name("x8105").srcCtx("UnrollingBase.scala:28:66") } // And(b2472,b2065)
    val x8106_x8074_d0 = withCtrl(x8107) { WriteMem(x8074_d0, x8104).name("x8106_x8074_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8074,x8104,x8105)
    antiDepsOf(x8106_x8074_d0)=List(x8102)
    val x8106_x8074_d1 = withCtrl(x8107) { WriteMem(x8074_d1, x8104).name("x8106_x8074_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8074,x8104,x8105)
    antiDepsOf(x8106_x8074_d1)=List(x8102)
    val x8108 = withCtrl(x8153) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8108").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8109 = withCtrl(x8153) { CounterChain(List(x8108)).name("x8109").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8108))
    val x8122 = withCtrl(x8153) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8109).name("x8122").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2473, b2065),x8109,x8075,Block((x8075) => Const(())),List(List(b2521)),List(List(b2522)))
    val b2521 = withCtrl(x8122) { CounterIter(x8108, None).name("b2521") } // b2521
    val b2522 = withCtrl(x8122) { Const(true).name("b2522") } // b2522
    val x8110 = withCtrl(x8122) { OpDef(op=FixMul, inputs=List(b2468, Const(204))).name("x8110").srcCtx("sysml.scala:603:42") } // FixMul(b2468,Const(204))
    val x8111 = withCtrl(x8122) { OpDef(op=FixAdd, inputs=List(x8110, b2521)).name("x8111").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8110,b2521)
    val x8112 = withCtrl(x8122) { OpDef(op=BitAnd, inputs=List(b2522, b2473)).name("x8112").srcCtx("UnrollingBase.scala:28:66") } // And(b2522,b2473)
    val x8113 = withCtrl(x8122) { OpDef(op=BitAnd, inputs=List(x8112, b2065)).name("x8113").srcCtx("UnrollingBase.scala:28:66") } // And(x8112,b2065)
    val x8114 = withCtrl(x8122) { LoadBanks(List(x7718_d2_b1), List(b2063, x8111)).name("x8114").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2063, x8111),x8113)
    val x8115 = withCtrl(x8122) { LoadBanks(List(x7725_d27_b0), List(x8111)).name("x8115").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8111),x8113)
    val x8116 = withCtrl(x8122) { OpDef(op=FltMul, inputs=List(x8114, x8115)).name("x8116").srcCtx("sysml.scala:606:20") } // FltMul(x8114,x8115)
    val x8117 = withCtrl(x8122) { ReadMem(x8075_d1).name("x8117").srcCtx("sysml.scala:607:13") } // RegRead(x8075)
    val x8118 = withCtrl(x8122) { OpDef(op=FixEql, inputs=List(b2521, Const(0))).name("x8118").srcCtx("sysml.scala:607:13") } // FixEql(b2521,Const(0))
    val x8119 = withCtrl(x8122) { ReduceAccumOp(op=FltAdd, input=x8116, accum=x8117).name("x8119").srcCtx("sysml.scala:607:15") } // FltAdd(x8116,x8117)
    val x8120 = withCtrl(x8122) { OpDef(op=BitAnd, inputs=List(b2473, b2065)).name("x8120").srcCtx("UnrollingBase.scala:28:66") } // And(b2473,b2065)
    val x8121_x8075_d0 = withCtrl(x8122) { WriteMem(x8075_d0, x8119).name("x8121_x8075_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8075,x8119,x8120)
    antiDepsOf(x8121_x8075_d0)=List(x8117)
    val x8121_x8075_d1 = withCtrl(x8122) { WriteMem(x8075_d1, x8119).name("x8121_x8075_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8075,x8119,x8120)
    antiDepsOf(x8121_x8075_d1)=List(x8117)
    val x8123 = withCtrl(x8153) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8123").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8124 = withCtrl(x8153) { CounterChain(List(x8123)).name("x8124").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8123))
    val x8137 = withCtrl(x8153) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8124).name("x8137").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2474, b2065),x8124,x8076,Block((x8076) => Const(())),List(List(b2536)),List(List(b2537)))
    val b2536 = withCtrl(x8137) { CounterIter(x8123, None).name("b2536") } // b2536
    val b2537 = withCtrl(x8137) { Const(true).name("b2537") } // b2537
    val x8125 = withCtrl(x8137) { OpDef(op=FixMul, inputs=List(b2469, Const(204))).name("x8125").srcCtx("sysml.scala:603:42") } // FixMul(b2469,Const(204))
    val x8126 = withCtrl(x8137) { OpDef(op=FixAdd, inputs=List(x8125, b2536)).name("x8126").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8125,b2536)
    val x8127 = withCtrl(x8137) { OpDef(op=BitAnd, inputs=List(b2537, b2474)).name("x8127").srcCtx("UnrollingBase.scala:28:66") } // And(b2537,b2474)
    val x8128 = withCtrl(x8137) { OpDef(op=BitAnd, inputs=List(x8127, b2065)).name("x8128").srcCtx("UnrollingBase.scala:28:66") } // And(x8127,b2065)
    val x8129 = withCtrl(x8137) { LoadBanks(List(x7718_d3_b1), List(b2063, x8126)).name("x8129").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2063, x8126),x8128)
    val x8130 = withCtrl(x8137) { LoadBanks(List(x7725_d28_b0), List(x8126)).name("x8130").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8126),x8128)
    val x8131 = withCtrl(x8137) { OpDef(op=FltMul, inputs=List(x8129, x8130)).name("x8131").srcCtx("sysml.scala:606:20") } // FltMul(x8129,x8130)
    val x8132 = withCtrl(x8137) { ReadMem(x8076_d1).name("x8132").srcCtx("sysml.scala:607:13") } // RegRead(x8076)
    val x8133 = withCtrl(x8137) { OpDef(op=FixEql, inputs=List(b2536, Const(0))).name("x8133").srcCtx("sysml.scala:607:13") } // FixEql(b2536,Const(0))
    val x8134 = withCtrl(x8137) { ReduceAccumOp(op=FltAdd, input=x8131, accum=x8132).name("x8134").srcCtx("sysml.scala:607:15") } // FltAdd(x8131,x8132)
    val x8135 = withCtrl(x8137) { OpDef(op=BitAnd, inputs=List(b2474, b2065)).name("x8135").srcCtx("UnrollingBase.scala:28:66") } // And(b2474,b2065)
    val x8136_x8076_d0 = withCtrl(x8137) { WriteMem(x8076_d0, x8134).name("x8136_x8076_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8076,x8134,x8135)
    antiDepsOf(x8136_x8076_d0)=List(x8132)
    val x8136_x8076_d1 = withCtrl(x8137) { WriteMem(x8076_d1, x8134).name("x8136_x8076_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8076,x8134,x8135)
    antiDepsOf(x8136_x8076_d1)=List(x8132)
    val x8138 = withCtrl(x8153) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8138").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8139 = withCtrl(x8153) { CounterChain(List(x8138)).name("x8139").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8138))
    val x8152 = withCtrl(x8153) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8139).name("x8152").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2475, b2065),x8139,x8077,Block((x8077) => Const(())),List(List(b2551)),List(List(b2552)))
    val b2551 = withCtrl(x8152) { CounterIter(x8138, None).name("b2551") } // b2551
    val b2552 = withCtrl(x8152) { Const(true).name("b2552") } // b2552
    val x8140 = withCtrl(x8152) { OpDef(op=FixMul, inputs=List(b2470, Const(204))).name("x8140").srcCtx("sysml.scala:603:42") } // FixMul(b2470,Const(204))
    val x8141 = withCtrl(x8152) { OpDef(op=FixAdd, inputs=List(x8140, b2551)).name("x8141").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8140,b2551)
    val x8142 = withCtrl(x8152) { OpDef(op=BitAnd, inputs=List(b2552, b2475)).name("x8142").srcCtx("UnrollingBase.scala:28:66") } // And(b2552,b2475)
    val x8143 = withCtrl(x8152) { OpDef(op=BitAnd, inputs=List(x8142, b2065)).name("x8143").srcCtx("UnrollingBase.scala:28:66") } // And(x8142,b2065)
    val x8144 = withCtrl(x8152) { LoadBanks(List(x7718_d4_b1), List(b2063, x8141)).name("x8144").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7718,List(b2063, x8141),x8143)
    val x8145 = withCtrl(x8152) { LoadBanks(List(x7725_d29_b0), List(x8141)).name("x8145").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8141),x8143)
    val x8146 = withCtrl(x8152) { OpDef(op=FltMul, inputs=List(x8144, x8145)).name("x8146").srcCtx("sysml.scala:606:20") } // FltMul(x8144,x8145)
    val x8147 = withCtrl(x8152) { ReadMem(x8077_d1).name("x8147").srcCtx("sysml.scala:607:13") } // RegRead(x8077)
    val x8148 = withCtrl(x8152) { OpDef(op=FixEql, inputs=List(b2551, Const(0))).name("x8148").srcCtx("sysml.scala:607:13") } // FixEql(b2551,Const(0))
    val x8149 = withCtrl(x8152) { ReduceAccumOp(op=FltAdd, input=x8146, accum=x8147).name("x8149").srcCtx("sysml.scala:607:15") } // FltAdd(x8146,x8147)
    val x8150 = withCtrl(x8152) { OpDef(op=BitAnd, inputs=List(b2475, b2065)).name("x8150").srcCtx("UnrollingBase.scala:28:66") } // And(b2475,b2065)
    val x8151_x8077_d0 = withCtrl(x8152) { WriteMem(x8077_d0, x8149).name("x8151_x8077_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8077,x8149,x8150)
    antiDepsOf(x8151_x8077_d0)=List(x8147)
    val x8151_x8077_d1 = withCtrl(x8152) { WriteMem(x8077_d1, x8149).name("x8151_x8077_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8077,x8149,x8150)
    antiDepsOf(x8151_x8077_d1)=List(x8147)
    val x8180 = withCtrl(x8181) { UnitController(style=SeqPipe, level=InnerControl).name("x8180").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2065),Block(x8179))
    val x8154 = withCtrl(x8180) { OpDef(op=BitAnd, inputs=List(b2471, b2065)).name("x8154").srcCtx("sysml.scala:610:11") } // And(b2471,b2065)
    val x8155 = withCtrl(x8180) { OpDef(op=BitAnd, inputs=List(b2472, b2065)).name("x8155").srcCtx("sysml.scala:610:11") } // And(b2472,b2065)
    val x8156 = withCtrl(x8180) { OpDef(op=BitAnd, inputs=List(b2473, b2065)).name("x8156").srcCtx("sysml.scala:610:11") } // And(b2473,b2065)
    val x8157 = withCtrl(x8180) { OpDef(op=BitAnd, inputs=List(b2474, b2065)).name("x8157").srcCtx("sysml.scala:610:11") } // And(b2474,b2065)
    val x8158 = withCtrl(x8180) { OpDef(op=BitAnd, inputs=List(b2475, b2065)).name("x8158").srcCtx("sysml.scala:610:11") } // And(b2475,b2065)
    val x8159 = withCtrl(x8180) { ReadMem(x8074_d0).name("x8159").srcCtx("sysml.scala:609:11") } // RegRead(x8074)
    val x8160 = withCtrl(x8180) { ReadMem(x8073_d0).name("x8160").srcCtx("sysml.scala:609:11") } // RegRead(x8073)
    val x8161 = withCtrl(x8180) { OpDef(op=FltAdd, inputs=List(x8160, x8159)).name("x8161").srcCtx("sysml.scala:610:13") } // FltAdd(x8160,x8159)
    val x8162 = withCtrl(x8180) { OpDef(op=MuxOp, inputs=List(x8155, x8161, x8160)).name("x8162").srcCtx("sysml.scala:610:11") } // Mux(x8155,x8161,x8160)
    val x8163 = withCtrl(x8180) { OpDef(op=BitOr, inputs=List(x8154, x8155)).name("x8163").srcCtx("sysml.scala:610:11") } // Or(x8154,x8155)
    val x8164 = withCtrl(x8180) { ReadMem(x8076_d0).name("x8164").srcCtx("sysml.scala:609:11") } // RegRead(x8076)
    val x8165 = withCtrl(x8180) { ReadMem(x8075_d0).name("x8165").srcCtx("sysml.scala:609:11") } // RegRead(x8075)
    val x8166 = withCtrl(x8180) { OpDef(op=FltAdd, inputs=List(x8165, x8164)).name("x8166").srcCtx("sysml.scala:610:13") } // FltAdd(x8165,x8164)
    val x8167 = withCtrl(x8180) { OpDef(op=MuxOp, inputs=List(x8157, x8166, x8165)).name("x8167").srcCtx("sysml.scala:610:11") } // Mux(x8157,x8166,x8165)
    val x8168 = withCtrl(x8180) { OpDef(op=BitOr, inputs=List(x8156, x8157)).name("x8168").srcCtx("sysml.scala:610:11") } // Or(x8156,x8157)
    val x8169 = withCtrl(x8180) { OpDef(op=FltAdd, inputs=List(x8162, x8167)).name("x8169").srcCtx("sysml.scala:610:13") } // FltAdd(x8162,x8167)
    val x8170 = withCtrl(x8180) { OpDef(op=MuxOp, inputs=List(x8168, x8169, x8162)).name("x8170").srcCtx("sysml.scala:610:11") } // Mux(x8168,x8169,x8162)
    val x8171 = withCtrl(x8180) { OpDef(op=BitOr, inputs=List(x8163, x8168)).name("x8171").srcCtx("sysml.scala:610:11") } // Or(x8163,x8168)
    val x8172 = withCtrl(x8180) { ReadMem(x8077_d0).name("x8172").srcCtx("sysml.scala:609:11") } // RegRead(x8077)
    val x8173 = withCtrl(x8180) { OpDef(op=FltAdd, inputs=List(x8170, x8172)).name("x8173").srcCtx("sysml.scala:610:13") } // FltAdd(x8170,x8172)
    val x8174 = withCtrl(x8180) { OpDef(op=MuxOp, inputs=List(x8158, x8173, x8170)).name("x8174").srcCtx("sysml.scala:610:11") } // Mux(x8158,x8173,x8170)
    val x8175 = withCtrl(x8180) { OpDef(op=BitOr, inputs=List(x8171, x8158)).name("x8175").srcCtx("sysml.scala:610:11") } // Or(x8171,x8158)
    val x8176 = withCtrl(x8180) { ReadMem(x7959_d1).name("x8176").srcCtx("sysml.scala:610:11") } // RegRead(x7959)
    val x8177 = withCtrl(x8180) { OpDef(op=FixEql, inputs=List(b2466, Const(0))).name("x8177").srcCtx("sysml.scala:610:11") } // FixEql(b2466,Const(0))
    val x8178 = withCtrl(x8180) { OpDef(op=FltAdd, inputs=List(x8174, x8176)).name("x8178").srcCtx("sysml.scala:610:13") } // FltAdd(x8174,x8176)
    val x8179_x7959_d0 = withCtrl(x8180) { WriteMem(x7959_d0, x8178).name("x8179_x7959_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x7959,x8178,b2065)
    antiDepsOf(x8179_x7959_d0)=List(x8176)
    val x8179_x7959_d1 = withCtrl(x8180) { WriteMem(x7959_d1, x8178).name("x8179_x7959_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x7959,x8178,b2065)
    antiDepsOf(x8179_x7959_d1)=List(x8176)
    val x8183_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8183_d0").srcCtx("sysml.scala:597:32:g") } // x8183 = RegNew(Const(0.0))
    isAccum(x8183_d0) = false
    bufferDepthOf(x8183_d0) = 3
    val x8183_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8183_d1").srcCtx("sysml.scala:597:32:g") } // x8183 = RegNew(Const(0.0))
    isAccum(x8183_d1) = true
    bufferDepthOf(x8183_d1) = 1
    val x8184_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8184_d0").srcCtx("sysml.scala:597:32:g") } // x8184 = RegNew(Const(0.0))
    isAccum(x8184_d0) = false
    bufferDepthOf(x8184_d0) = 3
    val x8184_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8184_d1").srcCtx("sysml.scala:597:32:g") } // x8184 = RegNew(Const(0.0))
    isAccum(x8184_d1) = true
    bufferDepthOf(x8184_d1) = 1
    val x8407 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8407").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x8185 = withCtrl(x8407) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x8185").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x8186 = withCtrl(x8407) { CounterChain(List(x8185)).name("x8186").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x8185))
    val x8295 = withCtrl(x8407) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8186).name("x8295").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2064),x8186,x8183,Block((x8183) => Const(())),List(List(b2602, b2603, b2604, b2605, b2606)),List(List(b2607, b2608, b2609, b2610, b2611)))
    val b2602 = withCtrl(x8295) { CounterIter(x8185, Some(0)).name("b2602") } // b2602
    val b2607 = withCtrl(x8295) { Const(true).name("b2607") } // b2607
    val b2603 = withCtrl(x8295) { CounterIter(x8185, Some(1)).name("b2603") } // b2603
    val b2608 = withCtrl(x8295) { Const(true).name("b2608") } // b2608
    val b2604 = withCtrl(x8295) { CounterIter(x8185, Some(2)).name("b2604") } // b2604
    val b2609 = withCtrl(x8295) { Const(true).name("b2609") } // b2609
    val b2605 = withCtrl(x8295) { CounterIter(x8185, Some(3)).name("b2605") } // b2605
    val b2610 = withCtrl(x8295) { Const(true).name("b2610") } // b2610
    val b2606 = withCtrl(x8295) { CounterIter(x8185, Some(4)).name("b2606") } // b2606
    val b2611 = withCtrl(x8295) { Const(true).name("b2611") } // b2611
    val x8187_d0 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8187_d0").srcCtx("sysml.scala:600:39:gInner") } // x8187 = RegNew(Const(0.0))
    isAccum(x8187_d0) = false
    bufferDepthOf(x8187_d0) = 1
    val x8187_d1 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8187_d1").srcCtx("sysml.scala:600:39:gInner") } // x8187 = RegNew(Const(0.0))
    isAccum(x8187_d1) = true
    bufferDepthOf(x8187_d1) = 1
    val x8188_d0 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8188_d0").srcCtx("sysml.scala:600:39:gInner") } // x8188 = RegNew(Const(0.0))
    isAccum(x8188_d0) = false
    bufferDepthOf(x8188_d0) = 1
    val x8188_d1 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8188_d1").srcCtx("sysml.scala:600:39:gInner") } // x8188 = RegNew(Const(0.0))
    isAccum(x8188_d1) = true
    bufferDepthOf(x8188_d1) = 1
    val x8189_d0 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8189_d0").srcCtx("sysml.scala:600:39:gInner") } // x8189 = RegNew(Const(0.0))
    isAccum(x8189_d0) = false
    bufferDepthOf(x8189_d0) = 1
    val x8189_d1 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8189_d1").srcCtx("sysml.scala:600:39:gInner") } // x8189 = RegNew(Const(0.0))
    isAccum(x8189_d1) = true
    bufferDepthOf(x8189_d1) = 1
    val x8190_d0 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8190_d0").srcCtx("sysml.scala:600:39:gInner") } // x8190 = RegNew(Const(0.0))
    isAccum(x8190_d0) = false
    bufferDepthOf(x8190_d0) = 1
    val x8190_d1 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8190_d1").srcCtx("sysml.scala:600:39:gInner") } // x8190 = RegNew(Const(0.0))
    isAccum(x8190_d1) = true
    bufferDepthOf(x8190_d1) = 1
    val x8191_d0 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8191_d0").srcCtx("sysml.scala:600:39:gInner") } // x8191 = RegNew(Const(0.0))
    isAccum(x8191_d0) = false
    bufferDepthOf(x8191_d0) = 1
    val x8191_d1 = withCtrl(x8295) { Reg(init=Some(0.0)).name("x8191_d1").srcCtx("sysml.scala:600:39:gInner") } // x8191 = RegNew(Const(0.0))
    isAccum(x8191_d1) = true
    bufferDepthOf(x8191_d1) = 1
    val x8267 = withCtrl(x8295) { UnitController(style=ForkJoin, level=OuterControl).name("x8267").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2064),Block(Const(())))
    val x8192 = withCtrl(x8267) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8192").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8193 = withCtrl(x8267) { CounterChain(List(x8192)).name("x8193").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8192))
    val x8206 = withCtrl(x8267) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8193).name("x8206").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2607, b2064),x8193,x8187,Block((x8187) => Const(())),List(List(b2627)),List(List(b2628)))
    val b2627 = withCtrl(x8206) { CounterIter(x8192, None).name("b2627") } // b2627
    val b2628 = withCtrl(x8206) { Const(true).name("b2628") } // b2628
    val x8194 = withCtrl(x8206) { OpDef(op=FixMul, inputs=List(b2602, Const(204))).name("x8194").srcCtx("sysml.scala:603:42") } // FixMul(b2602,Const(204))
    val x8195 = withCtrl(x8206) { OpDef(op=FixAdd, inputs=List(x8194, b2627)).name("x8195").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8194,b2627)
    val x8196 = withCtrl(x8206) { OpDef(op=BitAnd, inputs=List(b2628, b2607)).name("x8196").srcCtx("UnrollingBase.scala:28:66") } // And(b2628,b2607)
    val x8197 = withCtrl(x8206) { OpDef(op=BitAnd, inputs=List(x8196, b2064)).name("x8197").srcCtx("UnrollingBase.scala:28:66") } // And(x8196,b2064)
    val x8198 = withCtrl(x8206) { LoadBanks(List(x7719_d0_b0), List(b2062, x8195)).name("x8198").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2062, x8195),x8197)
    val x8199 = withCtrl(x8206) { LoadBanks(List(x7725_d10_b0), List(x8195)).name("x8199").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8195),x8197)
    val x8200 = withCtrl(x8206) { OpDef(op=FltMul, inputs=List(x8198, x8199)).name("x8200").srcCtx("sysml.scala:606:20") } // FltMul(x8198,x8199)
    val x8201 = withCtrl(x8206) { ReadMem(x8187_d1).name("x8201").srcCtx("sysml.scala:607:13") } // RegRead(x8187)
    val x8202 = withCtrl(x8206) { OpDef(op=FixEql, inputs=List(b2627, Const(0))).name("x8202").srcCtx("sysml.scala:607:13") } // FixEql(b2627,Const(0))
    val x8203 = withCtrl(x8206) { ReduceAccumOp(op=FltAdd, input=x8200, accum=x8201).name("x8203").srcCtx("sysml.scala:607:15") } // FltAdd(x8200,x8201)
    val x8204 = withCtrl(x8206) { OpDef(op=BitAnd, inputs=List(b2607, b2064)).name("x8204").srcCtx("UnrollingBase.scala:28:66") } // And(b2607,b2064)
    val x8205_x8187_d0 = withCtrl(x8206) { WriteMem(x8187_d0, x8203).name("x8205_x8187_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8187,x8203,x8204)
    antiDepsOf(x8205_x8187_d0)=List(x8201)
    val x8205_x8187_d1 = withCtrl(x8206) { WriteMem(x8187_d1, x8203).name("x8205_x8187_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8187,x8203,x8204)
    antiDepsOf(x8205_x8187_d1)=List(x8201)
    val x8207 = withCtrl(x8267) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8207").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8208 = withCtrl(x8267) { CounterChain(List(x8207)).name("x8208").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8207))
    val x8221 = withCtrl(x8267) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8208).name("x8221").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2608, b2064),x8208,x8188,Block((x8188) => Const(())),List(List(b2642)),List(List(b2643)))
    val b2642 = withCtrl(x8221) { CounterIter(x8207, None).name("b2642") } // b2642
    val b2643 = withCtrl(x8221) { Const(true).name("b2643") } // b2643
    val x8209 = withCtrl(x8221) { OpDef(op=FixMul, inputs=List(b2603, Const(204))).name("x8209").srcCtx("sysml.scala:603:42") } // FixMul(b2603,Const(204))
    val x8210 = withCtrl(x8221) { OpDef(op=FixAdd, inputs=List(x8209, b2642)).name("x8210").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8209,b2642)
    val x8211 = withCtrl(x8221) { OpDef(op=BitAnd, inputs=List(b2643, b2608)).name("x8211").srcCtx("UnrollingBase.scala:28:66") } // And(b2643,b2608)
    val x8212 = withCtrl(x8221) { OpDef(op=BitAnd, inputs=List(x8211, b2064)).name("x8212").srcCtx("UnrollingBase.scala:28:66") } // And(x8211,b2064)
    val x8213 = withCtrl(x8221) { LoadBanks(List(x7719_d1_b0), List(b2062, x8210)).name("x8213").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2062, x8210),x8212)
    val x8214 = withCtrl(x8221) { LoadBanks(List(x7725_d11_b0), List(x8210)).name("x8214").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8210),x8212)
    val x8215 = withCtrl(x8221) { OpDef(op=FltMul, inputs=List(x8213, x8214)).name("x8215").srcCtx("sysml.scala:606:20") } // FltMul(x8213,x8214)
    val x8216 = withCtrl(x8221) { ReadMem(x8188_d1).name("x8216").srcCtx("sysml.scala:607:13") } // RegRead(x8188)
    val x8217 = withCtrl(x8221) { OpDef(op=FixEql, inputs=List(b2642, Const(0))).name("x8217").srcCtx("sysml.scala:607:13") } // FixEql(b2642,Const(0))
    val x8218 = withCtrl(x8221) { ReduceAccumOp(op=FltAdd, input=x8215, accum=x8216).name("x8218").srcCtx("sysml.scala:607:15") } // FltAdd(x8215,x8216)
    val x8219 = withCtrl(x8221) { OpDef(op=BitAnd, inputs=List(b2608, b2064)).name("x8219").srcCtx("UnrollingBase.scala:28:66") } // And(b2608,b2064)
    val x8220_x8188_d0 = withCtrl(x8221) { WriteMem(x8188_d0, x8218).name("x8220_x8188_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8188,x8218,x8219)
    antiDepsOf(x8220_x8188_d0)=List(x8216)
    val x8220_x8188_d1 = withCtrl(x8221) { WriteMem(x8188_d1, x8218).name("x8220_x8188_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8188,x8218,x8219)
    antiDepsOf(x8220_x8188_d1)=List(x8216)
    val x8222 = withCtrl(x8267) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8222").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8223 = withCtrl(x8267) { CounterChain(List(x8222)).name("x8223").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8222))
    val x8236 = withCtrl(x8267) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8223).name("x8236").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2609, b2064),x8223,x8189,Block((x8189) => Const(())),List(List(b2657)),List(List(b2658)))
    val b2657 = withCtrl(x8236) { CounterIter(x8222, None).name("b2657") } // b2657
    val b2658 = withCtrl(x8236) { Const(true).name("b2658") } // b2658
    val x8224 = withCtrl(x8236) { OpDef(op=FixMul, inputs=List(b2604, Const(204))).name("x8224").srcCtx("sysml.scala:603:42") } // FixMul(b2604,Const(204))
    val x8225 = withCtrl(x8236) { OpDef(op=FixAdd, inputs=List(x8224, b2657)).name("x8225").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8224,b2657)
    val x8226 = withCtrl(x8236) { OpDef(op=BitAnd, inputs=List(b2658, b2609)).name("x8226").srcCtx("UnrollingBase.scala:28:66") } // And(b2658,b2609)
    val x8227 = withCtrl(x8236) { OpDef(op=BitAnd, inputs=List(x8226, b2064)).name("x8227").srcCtx("UnrollingBase.scala:28:66") } // And(x8226,b2064)
    val x8228 = withCtrl(x8236) { LoadBanks(List(x7719_d2_b0), List(b2062, x8225)).name("x8228").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2062, x8225),x8227)
    val x8229 = withCtrl(x8236) { LoadBanks(List(x7725_d12_b0), List(x8225)).name("x8229").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8225),x8227)
    val x8230 = withCtrl(x8236) { OpDef(op=FltMul, inputs=List(x8228, x8229)).name("x8230").srcCtx("sysml.scala:606:20") } // FltMul(x8228,x8229)
    val x8231 = withCtrl(x8236) { ReadMem(x8189_d1).name("x8231").srcCtx("sysml.scala:607:13") } // RegRead(x8189)
    val x8232 = withCtrl(x8236) { OpDef(op=FixEql, inputs=List(b2657, Const(0))).name("x8232").srcCtx("sysml.scala:607:13") } // FixEql(b2657,Const(0))
    val x8233 = withCtrl(x8236) { ReduceAccumOp(op=FltAdd, input=x8230, accum=x8231).name("x8233").srcCtx("sysml.scala:607:15") } // FltAdd(x8230,x8231)
    val x8234 = withCtrl(x8236) { OpDef(op=BitAnd, inputs=List(b2609, b2064)).name("x8234").srcCtx("UnrollingBase.scala:28:66") } // And(b2609,b2064)
    val x8235_x8189_d0 = withCtrl(x8236) { WriteMem(x8189_d0, x8233).name("x8235_x8189_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8189,x8233,x8234)
    antiDepsOf(x8235_x8189_d0)=List(x8231)
    val x8235_x8189_d1 = withCtrl(x8236) { WriteMem(x8189_d1, x8233).name("x8235_x8189_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8189,x8233,x8234)
    antiDepsOf(x8235_x8189_d1)=List(x8231)
    val x8237 = withCtrl(x8267) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8237").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8238 = withCtrl(x8267) { CounterChain(List(x8237)).name("x8238").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8237))
    val x8251 = withCtrl(x8267) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8238).name("x8251").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2610, b2064),x8238,x8190,Block((x8190) => Const(())),List(List(b2672)),List(List(b2673)))
    val b2672 = withCtrl(x8251) { CounterIter(x8237, None).name("b2672") } // b2672
    val b2673 = withCtrl(x8251) { Const(true).name("b2673") } // b2673
    val x8239 = withCtrl(x8251) { OpDef(op=FixMul, inputs=List(b2605, Const(204))).name("x8239").srcCtx("sysml.scala:603:42") } // FixMul(b2605,Const(204))
    val x8240 = withCtrl(x8251) { OpDef(op=FixAdd, inputs=List(x8239, b2672)).name("x8240").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8239,b2672)
    val x8241 = withCtrl(x8251) { OpDef(op=BitAnd, inputs=List(b2673, b2610)).name("x8241").srcCtx("UnrollingBase.scala:28:66") } // And(b2673,b2610)
    val x8242 = withCtrl(x8251) { OpDef(op=BitAnd, inputs=List(x8241, b2064)).name("x8242").srcCtx("UnrollingBase.scala:28:66") } // And(x8241,b2064)
    val x8243 = withCtrl(x8251) { LoadBanks(List(x7719_d3_b0), List(b2062, x8240)).name("x8243").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2062, x8240),x8242)
    val x8244 = withCtrl(x8251) { LoadBanks(List(x7725_d13_b0), List(x8240)).name("x8244").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8240),x8242)
    val x8245 = withCtrl(x8251) { OpDef(op=FltMul, inputs=List(x8243, x8244)).name("x8245").srcCtx("sysml.scala:606:20") } // FltMul(x8243,x8244)
    val x8246 = withCtrl(x8251) { ReadMem(x8190_d1).name("x8246").srcCtx("sysml.scala:607:13") } // RegRead(x8190)
    val x8247 = withCtrl(x8251) { OpDef(op=FixEql, inputs=List(b2672, Const(0))).name("x8247").srcCtx("sysml.scala:607:13") } // FixEql(b2672,Const(0))
    val x8248 = withCtrl(x8251) { ReduceAccumOp(op=FltAdd, input=x8245, accum=x8246).name("x8248").srcCtx("sysml.scala:607:15") } // FltAdd(x8245,x8246)
    val x8249 = withCtrl(x8251) { OpDef(op=BitAnd, inputs=List(b2610, b2064)).name("x8249").srcCtx("UnrollingBase.scala:28:66") } // And(b2610,b2064)
    val x8250_x8190_d0 = withCtrl(x8251) { WriteMem(x8190_d0, x8248).name("x8250_x8190_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8190,x8248,x8249)
    antiDepsOf(x8250_x8190_d0)=List(x8246)
    val x8250_x8190_d1 = withCtrl(x8251) { WriteMem(x8190_d1, x8248).name("x8250_x8190_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8190,x8248,x8249)
    antiDepsOf(x8250_x8190_d1)=List(x8246)
    val x8252 = withCtrl(x8267) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8252").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8253 = withCtrl(x8267) { CounterChain(List(x8252)).name("x8253").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8252))
    val x8266 = withCtrl(x8267) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8253).name("x8266").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2611, b2064),x8253,x8191,Block((x8191) => Const(())),List(List(b2687)),List(List(b2688)))
    val b2687 = withCtrl(x8266) { CounterIter(x8252, None).name("b2687") } // b2687
    val b2688 = withCtrl(x8266) { Const(true).name("b2688") } // b2688
    val x8254 = withCtrl(x8266) { OpDef(op=FixMul, inputs=List(b2606, Const(204))).name("x8254").srcCtx("sysml.scala:603:42") } // FixMul(b2606,Const(204))
    val x8255 = withCtrl(x8266) { OpDef(op=FixAdd, inputs=List(x8254, b2687)).name("x8255").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8254,b2687)
    val x8256 = withCtrl(x8266) { OpDef(op=BitAnd, inputs=List(b2688, b2611)).name("x8256").srcCtx("UnrollingBase.scala:28:66") } // And(b2688,b2611)
    val x8257 = withCtrl(x8266) { OpDef(op=BitAnd, inputs=List(x8256, b2064)).name("x8257").srcCtx("UnrollingBase.scala:28:66") } // And(x8256,b2064)
    val x8258 = withCtrl(x8266) { LoadBanks(List(x7719_d4_b0), List(b2062, x8255)).name("x8258").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2062, x8255),x8257)
    val x8259 = withCtrl(x8266) { LoadBanks(List(x7725_d14_b0), List(x8255)).name("x8259").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8255),x8257)
    val x8260 = withCtrl(x8266) { OpDef(op=FltMul, inputs=List(x8258, x8259)).name("x8260").srcCtx("sysml.scala:606:20") } // FltMul(x8258,x8259)
    val x8261 = withCtrl(x8266) { ReadMem(x8191_d1).name("x8261").srcCtx("sysml.scala:607:13") } // RegRead(x8191)
    val x8262 = withCtrl(x8266) { OpDef(op=FixEql, inputs=List(b2687, Const(0))).name("x8262").srcCtx("sysml.scala:607:13") } // FixEql(b2687,Const(0))
    val x8263 = withCtrl(x8266) { ReduceAccumOp(op=FltAdd, input=x8260, accum=x8261).name("x8263").srcCtx("sysml.scala:607:15") } // FltAdd(x8260,x8261)
    val x8264 = withCtrl(x8266) { OpDef(op=BitAnd, inputs=List(b2611, b2064)).name("x8264").srcCtx("UnrollingBase.scala:28:66") } // And(b2611,b2064)
    val x8265_x8191_d0 = withCtrl(x8266) { WriteMem(x8191_d0, x8263).name("x8265_x8191_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8191,x8263,x8264)
    antiDepsOf(x8265_x8191_d0)=List(x8261)
    val x8265_x8191_d1 = withCtrl(x8266) { WriteMem(x8191_d1, x8263).name("x8265_x8191_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8191,x8263,x8264)
    antiDepsOf(x8265_x8191_d1)=List(x8261)
    val x8294 = withCtrl(x8295) { UnitController(style=SeqPipe, level=InnerControl).name("x8294").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2064),Block(x8293))
    val x8268 = withCtrl(x8294) { OpDef(op=BitAnd, inputs=List(b2607, b2064)).name("x8268").srcCtx("sysml.scala:610:11") } // And(b2607,b2064)
    val x8269 = withCtrl(x8294) { OpDef(op=BitAnd, inputs=List(b2608, b2064)).name("x8269").srcCtx("sysml.scala:610:11") } // And(b2608,b2064)
    val x8270 = withCtrl(x8294) { OpDef(op=BitAnd, inputs=List(b2609, b2064)).name("x8270").srcCtx("sysml.scala:610:11") } // And(b2609,b2064)
    val x8271 = withCtrl(x8294) { OpDef(op=BitAnd, inputs=List(b2610, b2064)).name("x8271").srcCtx("sysml.scala:610:11") } // And(b2610,b2064)
    val x8272 = withCtrl(x8294) { OpDef(op=BitAnd, inputs=List(b2611, b2064)).name("x8272").srcCtx("sysml.scala:610:11") } // And(b2611,b2064)
    val x8273 = withCtrl(x8294) { ReadMem(x8188_d0).name("x8273").srcCtx("sysml.scala:609:11") } // RegRead(x8188)
    val x8274 = withCtrl(x8294) { ReadMem(x8187_d0).name("x8274").srcCtx("sysml.scala:609:11") } // RegRead(x8187)
    val x8275 = withCtrl(x8294) { OpDef(op=FltAdd, inputs=List(x8274, x8273)).name("x8275").srcCtx("sysml.scala:610:13") } // FltAdd(x8274,x8273)
    val x8276 = withCtrl(x8294) { OpDef(op=MuxOp, inputs=List(x8269, x8275, x8274)).name("x8276").srcCtx("sysml.scala:610:11") } // Mux(x8269,x8275,x8274)
    val x8277 = withCtrl(x8294) { OpDef(op=BitOr, inputs=List(x8268, x8269)).name("x8277").srcCtx("sysml.scala:610:11") } // Or(x8268,x8269)
    val x8278 = withCtrl(x8294) { ReadMem(x8190_d0).name("x8278").srcCtx("sysml.scala:609:11") } // RegRead(x8190)
    val x8279 = withCtrl(x8294) { ReadMem(x8189_d0).name("x8279").srcCtx("sysml.scala:609:11") } // RegRead(x8189)
    val x8280 = withCtrl(x8294) { OpDef(op=FltAdd, inputs=List(x8279, x8278)).name("x8280").srcCtx("sysml.scala:610:13") } // FltAdd(x8279,x8278)
    val x8281 = withCtrl(x8294) { OpDef(op=MuxOp, inputs=List(x8271, x8280, x8279)).name("x8281").srcCtx("sysml.scala:610:11") } // Mux(x8271,x8280,x8279)
    val x8282 = withCtrl(x8294) { OpDef(op=BitOr, inputs=List(x8270, x8271)).name("x8282").srcCtx("sysml.scala:610:11") } // Or(x8270,x8271)
    val x8283 = withCtrl(x8294) { OpDef(op=FltAdd, inputs=List(x8276, x8281)).name("x8283").srcCtx("sysml.scala:610:13") } // FltAdd(x8276,x8281)
    val x8284 = withCtrl(x8294) { OpDef(op=MuxOp, inputs=List(x8282, x8283, x8276)).name("x8284").srcCtx("sysml.scala:610:11") } // Mux(x8282,x8283,x8276)
    val x8285 = withCtrl(x8294) { OpDef(op=BitOr, inputs=List(x8277, x8282)).name("x8285").srcCtx("sysml.scala:610:11") } // Or(x8277,x8282)
    val x8286 = withCtrl(x8294) { ReadMem(x8191_d0).name("x8286").srcCtx("sysml.scala:609:11") } // RegRead(x8191)
    val x8287 = withCtrl(x8294) { OpDef(op=FltAdd, inputs=List(x8284, x8286)).name("x8287").srcCtx("sysml.scala:610:13") } // FltAdd(x8284,x8286)
    val x8288 = withCtrl(x8294) { OpDef(op=MuxOp, inputs=List(x8272, x8287, x8284)).name("x8288").srcCtx("sysml.scala:610:11") } // Mux(x8272,x8287,x8284)
    val x8289 = withCtrl(x8294) { OpDef(op=BitOr, inputs=List(x8285, x8272)).name("x8289").srcCtx("sysml.scala:610:11") } // Or(x8285,x8272)
    val x8290 = withCtrl(x8294) { ReadMem(x8183_d1).name("x8290").srcCtx("sysml.scala:610:11") } // RegRead(x8183)
    val x8291 = withCtrl(x8294) { OpDef(op=FixEql, inputs=List(b2602, Const(0))).name("x8291").srcCtx("sysml.scala:610:11") } // FixEql(b2602,Const(0))
    val x8292 = withCtrl(x8294) { OpDef(op=FltAdd, inputs=List(x8288, x8290)).name("x8292").srcCtx("sysml.scala:610:13") } // FltAdd(x8288,x8290)
    val x8293_x8183_d0 = withCtrl(x8294) { WriteMem(x8183_d0, x8292).name("x8293_x8183_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x8183,x8292,b2064)
    antiDepsOf(x8293_x8183_d0)=List(x8290)
    val x8293_x8183_d1 = withCtrl(x8294) { WriteMem(x8183_d1, x8292).name("x8293_x8183_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x8183,x8292,b2064)
    antiDepsOf(x8293_x8183_d1)=List(x8290)
    val x8296 = withCtrl(x8407) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x8296").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x8297 = withCtrl(x8407) { CounterChain(List(x8296)).name("x8297").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x8296))
    val x8406 = withCtrl(x8407) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8297).name("x8406").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2065),x8297,x8184,Block((x8184) => Const(())),List(List(b2731, b2732, b2733, b2734, b2735)),List(List(b2736, b2737, b2738, b2739, b2740)))
    val b2731 = withCtrl(x8406) { CounterIter(x8296, Some(0)).name("b2731") } // b2731
    val b2736 = withCtrl(x8406) { Const(true).name("b2736") } // b2736
    val b2732 = withCtrl(x8406) { CounterIter(x8296, Some(1)).name("b2732") } // b2732
    val b2737 = withCtrl(x8406) { Const(true).name("b2737") } // b2737
    val b2733 = withCtrl(x8406) { CounterIter(x8296, Some(2)).name("b2733") } // b2733
    val b2738 = withCtrl(x8406) { Const(true).name("b2738") } // b2738
    val b2734 = withCtrl(x8406) { CounterIter(x8296, Some(3)).name("b2734") } // b2734
    val b2739 = withCtrl(x8406) { Const(true).name("b2739") } // b2739
    val b2735 = withCtrl(x8406) { CounterIter(x8296, Some(4)).name("b2735") } // b2735
    val b2740 = withCtrl(x8406) { Const(true).name("b2740") } // b2740
    val x8298_d0 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8298_d0").srcCtx("sysml.scala:600:39:gInner") } // x8298 = RegNew(Const(0.0))
    isAccum(x8298_d0) = false
    bufferDepthOf(x8298_d0) = 1
    val x8298_d1 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8298_d1").srcCtx("sysml.scala:600:39:gInner") } // x8298 = RegNew(Const(0.0))
    isAccum(x8298_d1) = true
    bufferDepthOf(x8298_d1) = 1
    val x8299_d0 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8299_d0").srcCtx("sysml.scala:600:39:gInner") } // x8299 = RegNew(Const(0.0))
    isAccum(x8299_d0) = false
    bufferDepthOf(x8299_d0) = 1
    val x8299_d1 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8299_d1").srcCtx("sysml.scala:600:39:gInner") } // x8299 = RegNew(Const(0.0))
    isAccum(x8299_d1) = true
    bufferDepthOf(x8299_d1) = 1
    val x8300_d0 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8300_d0").srcCtx("sysml.scala:600:39:gInner") } // x8300 = RegNew(Const(0.0))
    isAccum(x8300_d0) = false
    bufferDepthOf(x8300_d0) = 1
    val x8300_d1 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8300_d1").srcCtx("sysml.scala:600:39:gInner") } // x8300 = RegNew(Const(0.0))
    isAccum(x8300_d1) = true
    bufferDepthOf(x8300_d1) = 1
    val x8301_d0 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8301_d0").srcCtx("sysml.scala:600:39:gInner") } // x8301 = RegNew(Const(0.0))
    isAccum(x8301_d0) = false
    bufferDepthOf(x8301_d0) = 1
    val x8301_d1 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8301_d1").srcCtx("sysml.scala:600:39:gInner") } // x8301 = RegNew(Const(0.0))
    isAccum(x8301_d1) = true
    bufferDepthOf(x8301_d1) = 1
    val x8302_d0 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8302_d0").srcCtx("sysml.scala:600:39:gInner") } // x8302 = RegNew(Const(0.0))
    isAccum(x8302_d0) = false
    bufferDepthOf(x8302_d0) = 1
    val x8302_d1 = withCtrl(x8406) { Reg(init=Some(0.0)).name("x8302_d1").srcCtx("sysml.scala:600:39:gInner") } // x8302 = RegNew(Const(0.0))
    isAccum(x8302_d1) = true
    bufferDepthOf(x8302_d1) = 1
    val x8378 = withCtrl(x8406) { UnitController(style=ForkJoin, level=OuterControl).name("x8378").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2065),Block(Const(())))
    val x8303 = withCtrl(x8378) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8303").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8304 = withCtrl(x8378) { CounterChain(List(x8303)).name("x8304").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8303))
    val x8317 = withCtrl(x8378) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8304).name("x8317").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2736, b2065),x8304,x8298,Block((x8298) => Const(())),List(List(b2756)),List(List(b2757)))
    val b2756 = withCtrl(x8317) { CounterIter(x8303, None).name("b2756") } // b2756
    val b2757 = withCtrl(x8317) { Const(true).name("b2757") } // b2757
    val x8305 = withCtrl(x8317) { OpDef(op=FixMul, inputs=List(b2731, Const(204))).name("x8305").srcCtx("sysml.scala:603:42") } // FixMul(b2731,Const(204))
    val x8306 = withCtrl(x8317) { OpDef(op=FixAdd, inputs=List(x8305, b2756)).name("x8306").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8305,b2756)
    val x8307 = withCtrl(x8317) { OpDef(op=BitAnd, inputs=List(b2757, b2736)).name("x8307").srcCtx("UnrollingBase.scala:28:66") } // And(b2757,b2736)
    val x8308 = withCtrl(x8317) { OpDef(op=BitAnd, inputs=List(x8307, b2065)).name("x8308").srcCtx("UnrollingBase.scala:28:66") } // And(x8307,b2065)
    val x8309 = withCtrl(x8317) { LoadBanks(List(x7719_d0_b1), List(b2063, x8306)).name("x8309").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2063, x8306),x8308)
    val x8310 = withCtrl(x8317) { LoadBanks(List(x7725_d15_b0), List(x8306)).name("x8310").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8306),x8308)
    val x8311 = withCtrl(x8317) { OpDef(op=FltMul, inputs=List(x8309, x8310)).name("x8311").srcCtx("sysml.scala:606:20") } // FltMul(x8309,x8310)
    val x8312 = withCtrl(x8317) { ReadMem(x8298_d1).name("x8312").srcCtx("sysml.scala:607:13") } // RegRead(x8298)
    val x8313 = withCtrl(x8317) { OpDef(op=FixEql, inputs=List(b2756, Const(0))).name("x8313").srcCtx("sysml.scala:607:13") } // FixEql(b2756,Const(0))
    val x8314 = withCtrl(x8317) { ReduceAccumOp(op=FltAdd, input=x8311, accum=x8312).name("x8314").srcCtx("sysml.scala:607:15") } // FltAdd(x8311,x8312)
    val x8315 = withCtrl(x8317) { OpDef(op=BitAnd, inputs=List(b2736, b2065)).name("x8315").srcCtx("UnrollingBase.scala:28:66") } // And(b2736,b2065)
    val x8316_x8298_d0 = withCtrl(x8317) { WriteMem(x8298_d0, x8314).name("x8316_x8298_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8298,x8314,x8315)
    antiDepsOf(x8316_x8298_d0)=List(x8312)
    val x8316_x8298_d1 = withCtrl(x8317) { WriteMem(x8298_d1, x8314).name("x8316_x8298_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8298,x8314,x8315)
    antiDepsOf(x8316_x8298_d1)=List(x8312)
    val x8318 = withCtrl(x8378) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8318").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8319 = withCtrl(x8378) { CounterChain(List(x8318)).name("x8319").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8318))
    val x8332 = withCtrl(x8378) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8319).name("x8332").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2737, b2065),x8319,x8299,Block((x8299) => Const(())),List(List(b2771)),List(List(b2772)))
    val b2771 = withCtrl(x8332) { CounterIter(x8318, None).name("b2771") } // b2771
    val b2772 = withCtrl(x8332) { Const(true).name("b2772") } // b2772
    val x8320 = withCtrl(x8332) { OpDef(op=FixMul, inputs=List(b2732, Const(204))).name("x8320").srcCtx("sysml.scala:603:42") } // FixMul(b2732,Const(204))
    val x8321 = withCtrl(x8332) { OpDef(op=FixAdd, inputs=List(x8320, b2771)).name("x8321").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8320,b2771)
    val x8322 = withCtrl(x8332) { OpDef(op=BitAnd, inputs=List(b2772, b2737)).name("x8322").srcCtx("UnrollingBase.scala:28:66") } // And(b2772,b2737)
    val x8323 = withCtrl(x8332) { OpDef(op=BitAnd, inputs=List(x8322, b2065)).name("x8323").srcCtx("UnrollingBase.scala:28:66") } // And(x8322,b2065)
    val x8324 = withCtrl(x8332) { LoadBanks(List(x7719_d1_b1), List(b2063, x8321)).name("x8324").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2063, x8321),x8323)
    val x8325 = withCtrl(x8332) { LoadBanks(List(x7725_d16_b0), List(x8321)).name("x8325").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8321),x8323)
    val x8326 = withCtrl(x8332) { OpDef(op=FltMul, inputs=List(x8324, x8325)).name("x8326").srcCtx("sysml.scala:606:20") } // FltMul(x8324,x8325)
    val x8327 = withCtrl(x8332) { ReadMem(x8299_d1).name("x8327").srcCtx("sysml.scala:607:13") } // RegRead(x8299)
    val x8328 = withCtrl(x8332) { OpDef(op=FixEql, inputs=List(b2771, Const(0))).name("x8328").srcCtx("sysml.scala:607:13") } // FixEql(b2771,Const(0))
    val x8329 = withCtrl(x8332) { ReduceAccumOp(op=FltAdd, input=x8326, accum=x8327).name("x8329").srcCtx("sysml.scala:607:15") } // FltAdd(x8326,x8327)
    val x8330 = withCtrl(x8332) { OpDef(op=BitAnd, inputs=List(b2737, b2065)).name("x8330").srcCtx("UnrollingBase.scala:28:66") } // And(b2737,b2065)
    val x8331_x8299_d0 = withCtrl(x8332) { WriteMem(x8299_d0, x8329).name("x8331_x8299_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8299,x8329,x8330)
    antiDepsOf(x8331_x8299_d0)=List(x8327)
    val x8331_x8299_d1 = withCtrl(x8332) { WriteMem(x8299_d1, x8329).name("x8331_x8299_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8299,x8329,x8330)
    antiDepsOf(x8331_x8299_d1)=List(x8327)
    val x8333 = withCtrl(x8378) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8333").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8334 = withCtrl(x8378) { CounterChain(List(x8333)).name("x8334").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8333))
    val x8347 = withCtrl(x8378) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8334).name("x8347").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2738, b2065),x8334,x8300,Block((x8300) => Const(())),List(List(b2786)),List(List(b2787)))
    val b2786 = withCtrl(x8347) { CounterIter(x8333, None).name("b2786") } // b2786
    val b2787 = withCtrl(x8347) { Const(true).name("b2787") } // b2787
    val x8335 = withCtrl(x8347) { OpDef(op=FixMul, inputs=List(b2733, Const(204))).name("x8335").srcCtx("sysml.scala:603:42") } // FixMul(b2733,Const(204))
    val x8336 = withCtrl(x8347) { OpDef(op=FixAdd, inputs=List(x8335, b2786)).name("x8336").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8335,b2786)
    val x8337 = withCtrl(x8347) { OpDef(op=BitAnd, inputs=List(b2787, b2738)).name("x8337").srcCtx("UnrollingBase.scala:28:66") } // And(b2787,b2738)
    val x8338 = withCtrl(x8347) { OpDef(op=BitAnd, inputs=List(x8337, b2065)).name("x8338").srcCtx("UnrollingBase.scala:28:66") } // And(x8337,b2065)
    val x8339 = withCtrl(x8347) { LoadBanks(List(x7719_d2_b1), List(b2063, x8336)).name("x8339").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2063, x8336),x8338)
    val x8340 = withCtrl(x8347) { LoadBanks(List(x7725_d17_b0), List(x8336)).name("x8340").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8336),x8338)
    val x8341 = withCtrl(x8347) { OpDef(op=FltMul, inputs=List(x8339, x8340)).name("x8341").srcCtx("sysml.scala:606:20") } // FltMul(x8339,x8340)
    val x8342 = withCtrl(x8347) { ReadMem(x8300_d1).name("x8342").srcCtx("sysml.scala:607:13") } // RegRead(x8300)
    val x8343 = withCtrl(x8347) { OpDef(op=FixEql, inputs=List(b2786, Const(0))).name("x8343").srcCtx("sysml.scala:607:13") } // FixEql(b2786,Const(0))
    val x8344 = withCtrl(x8347) { ReduceAccumOp(op=FltAdd, input=x8341, accum=x8342).name("x8344").srcCtx("sysml.scala:607:15") } // FltAdd(x8341,x8342)
    val x8345 = withCtrl(x8347) { OpDef(op=BitAnd, inputs=List(b2738, b2065)).name("x8345").srcCtx("UnrollingBase.scala:28:66") } // And(b2738,b2065)
    val x8346_x8300_d0 = withCtrl(x8347) { WriteMem(x8300_d0, x8344).name("x8346_x8300_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8300,x8344,x8345)
    antiDepsOf(x8346_x8300_d0)=List(x8342)
    val x8346_x8300_d1 = withCtrl(x8347) { WriteMem(x8300_d1, x8344).name("x8346_x8300_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8300,x8344,x8345)
    antiDepsOf(x8346_x8300_d1)=List(x8342)
    val x8348 = withCtrl(x8378) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8348").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8349 = withCtrl(x8378) { CounterChain(List(x8348)).name("x8349").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8348))
    val x8362 = withCtrl(x8378) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8349).name("x8362").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2739, b2065),x8349,x8301,Block((x8301) => Const(())),List(List(b2801)),List(List(b2802)))
    val b2801 = withCtrl(x8362) { CounterIter(x8348, None).name("b2801") } // b2801
    val b2802 = withCtrl(x8362) { Const(true).name("b2802") } // b2802
    val x8350 = withCtrl(x8362) { OpDef(op=FixMul, inputs=List(b2734, Const(204))).name("x8350").srcCtx("sysml.scala:603:42") } // FixMul(b2734,Const(204))
    val x8351 = withCtrl(x8362) { OpDef(op=FixAdd, inputs=List(x8350, b2801)).name("x8351").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8350,b2801)
    val x8352 = withCtrl(x8362) { OpDef(op=BitAnd, inputs=List(b2802, b2739)).name("x8352").srcCtx("UnrollingBase.scala:28:66") } // And(b2802,b2739)
    val x8353 = withCtrl(x8362) { OpDef(op=BitAnd, inputs=List(x8352, b2065)).name("x8353").srcCtx("UnrollingBase.scala:28:66") } // And(x8352,b2065)
    val x8354 = withCtrl(x8362) { LoadBanks(List(x7719_d3_b1), List(b2063, x8351)).name("x8354").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2063, x8351),x8353)
    val x8355 = withCtrl(x8362) { LoadBanks(List(x7725_d18_b0), List(x8351)).name("x8355").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8351),x8353)
    val x8356 = withCtrl(x8362) { OpDef(op=FltMul, inputs=List(x8354, x8355)).name("x8356").srcCtx("sysml.scala:606:20") } // FltMul(x8354,x8355)
    val x8357 = withCtrl(x8362) { ReadMem(x8301_d1).name("x8357").srcCtx("sysml.scala:607:13") } // RegRead(x8301)
    val x8358 = withCtrl(x8362) { OpDef(op=FixEql, inputs=List(b2801, Const(0))).name("x8358").srcCtx("sysml.scala:607:13") } // FixEql(b2801,Const(0))
    val x8359 = withCtrl(x8362) { ReduceAccumOp(op=FltAdd, input=x8356, accum=x8357).name("x8359").srcCtx("sysml.scala:607:15") } // FltAdd(x8356,x8357)
    val x8360 = withCtrl(x8362) { OpDef(op=BitAnd, inputs=List(b2739, b2065)).name("x8360").srcCtx("UnrollingBase.scala:28:66") } // And(b2739,b2065)
    val x8361_x8301_d0 = withCtrl(x8362) { WriteMem(x8301_d0, x8359).name("x8361_x8301_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8301,x8359,x8360)
    antiDepsOf(x8361_x8301_d0)=List(x8357)
    val x8361_x8301_d1 = withCtrl(x8362) { WriteMem(x8301_d1, x8359).name("x8361_x8301_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8301,x8359,x8360)
    antiDepsOf(x8361_x8301_d1)=List(x8357)
    val x8363 = withCtrl(x8378) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8363").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8364 = withCtrl(x8378) { CounterChain(List(x8363)).name("x8364").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8363))
    val x8377 = withCtrl(x8378) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8364).name("x8377").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2740, b2065),x8364,x8302,Block((x8302) => Const(())),List(List(b2816)),List(List(b2817)))
    val b2816 = withCtrl(x8377) { CounterIter(x8363, None).name("b2816") } // b2816
    val b2817 = withCtrl(x8377) { Const(true).name("b2817") } // b2817
    val x8365 = withCtrl(x8377) { OpDef(op=FixMul, inputs=List(b2735, Const(204))).name("x8365").srcCtx("sysml.scala:603:42") } // FixMul(b2735,Const(204))
    val x8366 = withCtrl(x8377) { OpDef(op=FixAdd, inputs=List(x8365, b2816)).name("x8366").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8365,b2816)
    val x8367 = withCtrl(x8377) { OpDef(op=BitAnd, inputs=List(b2817, b2740)).name("x8367").srcCtx("UnrollingBase.scala:28:66") } // And(b2817,b2740)
    val x8368 = withCtrl(x8377) { OpDef(op=BitAnd, inputs=List(x8367, b2065)).name("x8368").srcCtx("UnrollingBase.scala:28:66") } // And(x8367,b2065)
    val x8369 = withCtrl(x8377) { LoadBanks(List(x7719_d4_b1), List(b2063, x8366)).name("x8369").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7719,List(b2063, x8366),x8368)
    val x8370 = withCtrl(x8377) { LoadBanks(List(x7725_d19_b0), List(x8366)).name("x8370").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8366),x8368)
    val x8371 = withCtrl(x8377) { OpDef(op=FltMul, inputs=List(x8369, x8370)).name("x8371").srcCtx("sysml.scala:606:20") } // FltMul(x8369,x8370)
    val x8372 = withCtrl(x8377) { ReadMem(x8302_d1).name("x8372").srcCtx("sysml.scala:607:13") } // RegRead(x8302)
    val x8373 = withCtrl(x8377) { OpDef(op=FixEql, inputs=List(b2816, Const(0))).name("x8373").srcCtx("sysml.scala:607:13") } // FixEql(b2816,Const(0))
    val x8374 = withCtrl(x8377) { ReduceAccumOp(op=FltAdd, input=x8371, accum=x8372).name("x8374").srcCtx("sysml.scala:607:15") } // FltAdd(x8371,x8372)
    val x8375 = withCtrl(x8377) { OpDef(op=BitAnd, inputs=List(b2740, b2065)).name("x8375").srcCtx("UnrollingBase.scala:28:66") } // And(b2740,b2065)
    val x8376_x8302_d0 = withCtrl(x8377) { WriteMem(x8302_d0, x8374).name("x8376_x8302_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8302,x8374,x8375)
    antiDepsOf(x8376_x8302_d0)=List(x8372)
    val x8376_x8302_d1 = withCtrl(x8377) { WriteMem(x8302_d1, x8374).name("x8376_x8302_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8302,x8374,x8375)
    antiDepsOf(x8376_x8302_d1)=List(x8372)
    val x8405 = withCtrl(x8406) { UnitController(style=SeqPipe, level=InnerControl).name("x8405").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2065),Block(x8404))
    val x8379 = withCtrl(x8405) { OpDef(op=BitAnd, inputs=List(b2736, b2065)).name("x8379").srcCtx("sysml.scala:610:11") } // And(b2736,b2065)
    val x8380 = withCtrl(x8405) { OpDef(op=BitAnd, inputs=List(b2737, b2065)).name("x8380").srcCtx("sysml.scala:610:11") } // And(b2737,b2065)
    val x8381 = withCtrl(x8405) { OpDef(op=BitAnd, inputs=List(b2738, b2065)).name("x8381").srcCtx("sysml.scala:610:11") } // And(b2738,b2065)
    val x8382 = withCtrl(x8405) { OpDef(op=BitAnd, inputs=List(b2739, b2065)).name("x8382").srcCtx("sysml.scala:610:11") } // And(b2739,b2065)
    val x8383 = withCtrl(x8405) { OpDef(op=BitAnd, inputs=List(b2740, b2065)).name("x8383").srcCtx("sysml.scala:610:11") } // And(b2740,b2065)
    val x8384 = withCtrl(x8405) { ReadMem(x8299_d0).name("x8384").srcCtx("sysml.scala:609:11") } // RegRead(x8299)
    val x8385 = withCtrl(x8405) { ReadMem(x8298_d0).name("x8385").srcCtx("sysml.scala:609:11") } // RegRead(x8298)
    val x8386 = withCtrl(x8405) { OpDef(op=FltAdd, inputs=List(x8385, x8384)).name("x8386").srcCtx("sysml.scala:610:13") } // FltAdd(x8385,x8384)
    val x8387 = withCtrl(x8405) { OpDef(op=MuxOp, inputs=List(x8380, x8386, x8385)).name("x8387").srcCtx("sysml.scala:610:11") } // Mux(x8380,x8386,x8385)
    val x8388 = withCtrl(x8405) { OpDef(op=BitOr, inputs=List(x8379, x8380)).name("x8388").srcCtx("sysml.scala:610:11") } // Or(x8379,x8380)
    val x8389 = withCtrl(x8405) { ReadMem(x8301_d0).name("x8389").srcCtx("sysml.scala:609:11") } // RegRead(x8301)
    val x8390 = withCtrl(x8405) { ReadMem(x8300_d0).name("x8390").srcCtx("sysml.scala:609:11") } // RegRead(x8300)
    val x8391 = withCtrl(x8405) { OpDef(op=FltAdd, inputs=List(x8390, x8389)).name("x8391").srcCtx("sysml.scala:610:13") } // FltAdd(x8390,x8389)
    val x8392 = withCtrl(x8405) { OpDef(op=MuxOp, inputs=List(x8382, x8391, x8390)).name("x8392").srcCtx("sysml.scala:610:11") } // Mux(x8382,x8391,x8390)
    val x8393 = withCtrl(x8405) { OpDef(op=BitOr, inputs=List(x8381, x8382)).name("x8393").srcCtx("sysml.scala:610:11") } // Or(x8381,x8382)
    val x8394 = withCtrl(x8405) { OpDef(op=FltAdd, inputs=List(x8387, x8392)).name("x8394").srcCtx("sysml.scala:610:13") } // FltAdd(x8387,x8392)
    val x8395 = withCtrl(x8405) { OpDef(op=MuxOp, inputs=List(x8393, x8394, x8387)).name("x8395").srcCtx("sysml.scala:610:11") } // Mux(x8393,x8394,x8387)
    val x8396 = withCtrl(x8405) { OpDef(op=BitOr, inputs=List(x8388, x8393)).name("x8396").srcCtx("sysml.scala:610:11") } // Or(x8388,x8393)
    val x8397 = withCtrl(x8405) { ReadMem(x8302_d0).name("x8397").srcCtx("sysml.scala:609:11") } // RegRead(x8302)
    val x8398 = withCtrl(x8405) { OpDef(op=FltAdd, inputs=List(x8395, x8397)).name("x8398").srcCtx("sysml.scala:610:13") } // FltAdd(x8395,x8397)
    val x8399 = withCtrl(x8405) { OpDef(op=MuxOp, inputs=List(x8383, x8398, x8395)).name("x8399").srcCtx("sysml.scala:610:11") } // Mux(x8383,x8398,x8395)
    val x8400 = withCtrl(x8405) { OpDef(op=BitOr, inputs=List(x8396, x8383)).name("x8400").srcCtx("sysml.scala:610:11") } // Or(x8396,x8383)
    val x8401 = withCtrl(x8405) { ReadMem(x8184_d1).name("x8401").srcCtx("sysml.scala:610:11") } // RegRead(x8184)
    val x8402 = withCtrl(x8405) { OpDef(op=FixEql, inputs=List(b2731, Const(0))).name("x8402").srcCtx("sysml.scala:610:11") } // FixEql(b2731,Const(0))
    val x8403 = withCtrl(x8405) { OpDef(op=FltAdd, inputs=List(x8399, x8401)).name("x8403").srcCtx("sysml.scala:610:13") } // FltAdd(x8399,x8401)
    val x8404_x8184_d0 = withCtrl(x8405) { WriteMem(x8184_d0, x8403).name("x8404_x8184_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x8184,x8403,b2065)
    antiDepsOf(x8404_x8184_d0)=List(x8401)
    val x8404_x8184_d1 = withCtrl(x8405) { WriteMem(x8184_d1, x8403).name("x8404_x8184_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x8184,x8403,b2065)
    antiDepsOf(x8404_x8184_d1)=List(x8401)
    val x8408_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8408_d0").srcCtx("sysml.scala:597:32:g") } // x8408 = RegNew(Const(0.0))
    isAccum(x8408_d0) = false
    bufferDepthOf(x8408_d0) = 2
    val x8408_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8408_d1").srcCtx("sysml.scala:597:32:g") } // x8408 = RegNew(Const(0.0))
    isAccum(x8408_d1) = true
    bufferDepthOf(x8408_d1) = 1
    val x8409_d0 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8409_d0").srcCtx("sysml.scala:597:32:g") } // x8409 = RegNew(Const(0.0))
    isAccum(x8409_d0) = false
    bufferDepthOf(x8409_d0) = 2
    val x8409_d1 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8409_d1").srcCtx("sysml.scala:597:32:g") } // x8409 = RegNew(Const(0.0))
    isAccum(x8409_d1) = true
    bufferDepthOf(x8409_d1) = 1
    val x8632 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8632").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x8410 = withCtrl(x8632) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x8410").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x8411 = withCtrl(x8632) { CounterChain(List(x8410)).name("x8411").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x8410))
    val x8520 = withCtrl(x8632) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8411).name("x8520").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2064),x8411,x8408,Block((x8408) => Const(())),List(List(b2867, b2868, b2869, b2870, b2871)),List(List(b2872, b2873, b2874, b2875, b2876)))
    val b2867 = withCtrl(x8520) { CounterIter(x8410, Some(0)).name("b2867") } // b2867
    val b2872 = withCtrl(x8520) { Const(true).name("b2872") } // b2872
    val b2868 = withCtrl(x8520) { CounterIter(x8410, Some(1)).name("b2868") } // b2868
    val b2873 = withCtrl(x8520) { Const(true).name("b2873") } // b2873
    val b2869 = withCtrl(x8520) { CounterIter(x8410, Some(2)).name("b2869") } // b2869
    val b2874 = withCtrl(x8520) { Const(true).name("b2874") } // b2874
    val b2870 = withCtrl(x8520) { CounterIter(x8410, Some(3)).name("b2870") } // b2870
    val b2875 = withCtrl(x8520) { Const(true).name("b2875") } // b2875
    val b2871 = withCtrl(x8520) { CounterIter(x8410, Some(4)).name("b2871") } // b2871
    val b2876 = withCtrl(x8520) { Const(true).name("b2876") } // b2876
    val x8412_d0 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8412_d0").srcCtx("sysml.scala:600:39:gInner") } // x8412 = RegNew(Const(0.0))
    isAccum(x8412_d0) = false
    bufferDepthOf(x8412_d0) = 1
    val x8412_d1 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8412_d1").srcCtx("sysml.scala:600:39:gInner") } // x8412 = RegNew(Const(0.0))
    isAccum(x8412_d1) = true
    bufferDepthOf(x8412_d1) = 1
    val x8413_d0 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8413_d0").srcCtx("sysml.scala:600:39:gInner") } // x8413 = RegNew(Const(0.0))
    isAccum(x8413_d0) = false
    def split3 = {
    bufferDepthOf(x8413_d0) = 1
    val x8413_d1 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8413_d1").srcCtx("sysml.scala:600:39:gInner") } // x8413 = RegNew(Const(0.0))
    isAccum(x8413_d1) = true
    bufferDepthOf(x8413_d1) = 1
    val x8414_d0 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8414_d0").srcCtx("sysml.scala:600:39:gInner") } // x8414 = RegNew(Const(0.0))
    isAccum(x8414_d0) = false
    bufferDepthOf(x8414_d0) = 1
    val x8414_d1 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8414_d1").srcCtx("sysml.scala:600:39:gInner") } // x8414 = RegNew(Const(0.0))
    isAccum(x8414_d1) = true
    bufferDepthOf(x8414_d1) = 1
    val x8415_d0 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8415_d0").srcCtx("sysml.scala:600:39:gInner") } // x8415 = RegNew(Const(0.0))
    isAccum(x8415_d0) = false
    bufferDepthOf(x8415_d0) = 1
    val x8415_d1 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8415_d1").srcCtx("sysml.scala:600:39:gInner") } // x8415 = RegNew(Const(0.0))
    isAccum(x8415_d1) = true
    bufferDepthOf(x8415_d1) = 1
    val x8416_d0 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8416_d0").srcCtx("sysml.scala:600:39:gInner") } // x8416 = RegNew(Const(0.0))
    isAccum(x8416_d0) = false
    bufferDepthOf(x8416_d0) = 1
    val x8416_d1 = withCtrl(x8520) { Reg(init=Some(0.0)).name("x8416_d1").srcCtx("sysml.scala:600:39:gInner") } // x8416 = RegNew(Const(0.0))
    isAccum(x8416_d1) = true
    bufferDepthOf(x8416_d1) = 1
    val x8492 = withCtrl(x8520) { UnitController(style=ForkJoin, level=OuterControl).name("x8492").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2064),Block(Const(())))
    val x8417 = withCtrl(x8492) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8417").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8418 = withCtrl(x8492) { CounterChain(List(x8417)).name("x8418").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8417))
    val x8431 = withCtrl(x8492) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8418).name("x8431").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2872, b2064),x8418,x8412,Block((x8412) => Const(())),List(List(b2892)),List(List(b2893)))
    val b2892 = withCtrl(x8431) { CounterIter(x8417, None).name("b2892") } // b2892
    val b2893 = withCtrl(x8431) { Const(true).name("b2893") } // b2893
    val x8419 = withCtrl(x8431) { OpDef(op=FixMul, inputs=List(b2867, Const(204))).name("x8419").srcCtx("sysml.scala:603:42") } // FixMul(b2867,Const(204))
    val x8420 = withCtrl(x8431) { OpDef(op=FixAdd, inputs=List(x8419, b2892)).name("x8420").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8419,b2892)
    val x8421 = withCtrl(x8431) { OpDef(op=BitAnd, inputs=List(b2893, b2872)).name("x8421").srcCtx("UnrollingBase.scala:28:66") } // And(b2893,b2872)
    val x8422 = withCtrl(x8431) { OpDef(op=BitAnd, inputs=List(x8421, b2064)).name("x8422").srcCtx("UnrollingBase.scala:28:66") } // And(x8421,b2064)
    val x8423 = withCtrl(x8431) { LoadBanks(List(x7720_d0_b0), List(b2062, x8420)).name("x8423").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2062, x8420),x8422)
    val x8424 = withCtrl(x8431) { LoadBanks(List(x7725_d0_b0), List(x8420)).name("x8424").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8420),x8422)
    val x8425 = withCtrl(x8431) { OpDef(op=FltMul, inputs=List(x8423, x8424)).name("x8425").srcCtx("sysml.scala:606:20") } // FltMul(x8423,x8424)
    val x8426 = withCtrl(x8431) { ReadMem(x8412_d1).name("x8426").srcCtx("sysml.scala:607:13") } // RegRead(x8412)
    val x8427 = withCtrl(x8431) { OpDef(op=FixEql, inputs=List(b2892, Const(0))).name("x8427").srcCtx("sysml.scala:607:13") } // FixEql(b2892,Const(0))
    val x8428 = withCtrl(x8431) { ReduceAccumOp(op=FltAdd, input=x8425, accum=x8426).name("x8428").srcCtx("sysml.scala:607:15") } // FltAdd(x8425,x8426)
    val x8429 = withCtrl(x8431) { OpDef(op=BitAnd, inputs=List(b2872, b2064)).name("x8429").srcCtx("UnrollingBase.scala:28:66") } // And(b2872,b2064)
    val x8430_x8412_d0 = withCtrl(x8431) { WriteMem(x8412_d0, x8428).name("x8430_x8412_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8412,x8428,x8429)
    antiDepsOf(x8430_x8412_d0)=List(x8426)
    val x8430_x8412_d1 = withCtrl(x8431) { WriteMem(x8412_d1, x8428).name("x8430_x8412_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8412,x8428,x8429)
    antiDepsOf(x8430_x8412_d1)=List(x8426)
    val x8432 = withCtrl(x8492) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8432").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8433 = withCtrl(x8492) { CounterChain(List(x8432)).name("x8433").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8432))
    val x8446 = withCtrl(x8492) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8433).name("x8446").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2873, b2064),x8433,x8413,Block((x8413) => Const(())),List(List(b2907)),List(List(b2908)))
    val b2907 = withCtrl(x8446) { CounterIter(x8432, None).name("b2907") } // b2907
    val b2908 = withCtrl(x8446) { Const(true).name("b2908") } // b2908
    val x8434 = withCtrl(x8446) { OpDef(op=FixMul, inputs=List(b2868, Const(204))).name("x8434").srcCtx("sysml.scala:603:42") } // FixMul(b2868,Const(204))
    val x8435 = withCtrl(x8446) { OpDef(op=FixAdd, inputs=List(x8434, b2907)).name("x8435").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8434,b2907)
    val x8436 = withCtrl(x8446) { OpDef(op=BitAnd, inputs=List(b2908, b2873)).name("x8436").srcCtx("UnrollingBase.scala:28:66") } // And(b2908,b2873)
    val x8437 = withCtrl(x8446) { OpDef(op=BitAnd, inputs=List(x8436, b2064)).name("x8437").srcCtx("UnrollingBase.scala:28:66") } // And(x8436,b2064)
    val x8438 = withCtrl(x8446) { LoadBanks(List(x7720_d1_b0), List(b2062, x8435)).name("x8438").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2062, x8435),x8437)
    val x8439 = withCtrl(x8446) { LoadBanks(List(x7725_d1_b0), List(x8435)).name("x8439").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8435),x8437)
    val x8440 = withCtrl(x8446) { OpDef(op=FltMul, inputs=List(x8438, x8439)).name("x8440").srcCtx("sysml.scala:606:20") } // FltMul(x8438,x8439)
    val x8441 = withCtrl(x8446) { ReadMem(x8413_d1).name("x8441").srcCtx("sysml.scala:607:13") } // RegRead(x8413)
    val x8442 = withCtrl(x8446) { OpDef(op=FixEql, inputs=List(b2907, Const(0))).name("x8442").srcCtx("sysml.scala:607:13") } // FixEql(b2907,Const(0))
    val x8443 = withCtrl(x8446) { ReduceAccumOp(op=FltAdd, input=x8440, accum=x8441).name("x8443").srcCtx("sysml.scala:607:15") } // FltAdd(x8440,x8441)
    val x8444 = withCtrl(x8446) { OpDef(op=BitAnd, inputs=List(b2873, b2064)).name("x8444").srcCtx("UnrollingBase.scala:28:66") } // And(b2873,b2064)
    val x8445_x8413_d0 = withCtrl(x8446) { WriteMem(x8413_d0, x8443).name("x8445_x8413_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8413,x8443,x8444)
    antiDepsOf(x8445_x8413_d0)=List(x8441)
    val x8445_x8413_d1 = withCtrl(x8446) { WriteMem(x8413_d1, x8443).name("x8445_x8413_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8413,x8443,x8444)
    antiDepsOf(x8445_x8413_d1)=List(x8441)
    val x8447 = withCtrl(x8492) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8447").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8448 = withCtrl(x8492) { CounterChain(List(x8447)).name("x8448").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8447))
    val x8461 = withCtrl(x8492) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8448).name("x8461").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2874, b2064),x8448,x8414,Block((x8414) => Const(())),List(List(b2922)),List(List(b2923)))
    val b2922 = withCtrl(x8461) { CounterIter(x8447, None).name("b2922") } // b2922
    val b2923 = withCtrl(x8461) { Const(true).name("b2923") } // b2923
    val x8449 = withCtrl(x8461) { OpDef(op=FixMul, inputs=List(b2869, Const(204))).name("x8449").srcCtx("sysml.scala:603:42") } // FixMul(b2869,Const(204))
    val x8450 = withCtrl(x8461) { OpDef(op=FixAdd, inputs=List(x8449, b2922)).name("x8450").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8449,b2922)
    val x8451 = withCtrl(x8461) { OpDef(op=BitAnd, inputs=List(b2923, b2874)).name("x8451").srcCtx("UnrollingBase.scala:28:66") } // And(b2923,b2874)
    val x8452 = withCtrl(x8461) { OpDef(op=BitAnd, inputs=List(x8451, b2064)).name("x8452").srcCtx("UnrollingBase.scala:28:66") } // And(x8451,b2064)
    val x8453 = withCtrl(x8461) { LoadBanks(List(x7720_d2_b0), List(b2062, x8450)).name("x8453").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2062, x8450),x8452)
    val x8454 = withCtrl(x8461) { LoadBanks(List(x7725_d2_b0), List(x8450)).name("x8454").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8450),x8452)
    val x8455 = withCtrl(x8461) { OpDef(op=FltMul, inputs=List(x8453, x8454)).name("x8455").srcCtx("sysml.scala:606:20") } // FltMul(x8453,x8454)
    val x8456 = withCtrl(x8461) { ReadMem(x8414_d1).name("x8456").srcCtx("sysml.scala:607:13") } // RegRead(x8414)
    val x8457 = withCtrl(x8461) { OpDef(op=FixEql, inputs=List(b2922, Const(0))).name("x8457").srcCtx("sysml.scala:607:13") } // FixEql(b2922,Const(0))
    val x8458 = withCtrl(x8461) { ReduceAccumOp(op=FltAdd, input=x8455, accum=x8456).name("x8458").srcCtx("sysml.scala:607:15") } // FltAdd(x8455,x8456)
    val x8459 = withCtrl(x8461) { OpDef(op=BitAnd, inputs=List(b2874, b2064)).name("x8459").srcCtx("UnrollingBase.scala:28:66") } // And(b2874,b2064)
    val x8460_x8414_d0 = withCtrl(x8461) { WriteMem(x8414_d0, x8458).name("x8460_x8414_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8414,x8458,x8459)
    antiDepsOf(x8460_x8414_d0)=List(x8456)
    val x8460_x8414_d1 = withCtrl(x8461) { WriteMem(x8414_d1, x8458).name("x8460_x8414_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8414,x8458,x8459)
    antiDepsOf(x8460_x8414_d1)=List(x8456)
    val x8462 = withCtrl(x8492) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8462").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8463 = withCtrl(x8492) { CounterChain(List(x8462)).name("x8463").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8462))
    val x8476 = withCtrl(x8492) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8463).name("x8476").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2875, b2064),x8463,x8415,Block((x8415) => Const(())),List(List(b2937)),List(List(b2938)))
    val b2937 = withCtrl(x8476) { CounterIter(x8462, None).name("b2937") } // b2937
    val b2938 = withCtrl(x8476) { Const(true).name("b2938") } // b2938
    val x8464 = withCtrl(x8476) { OpDef(op=FixMul, inputs=List(b2870, Const(204))).name("x8464").srcCtx("sysml.scala:603:42") } // FixMul(b2870,Const(204))
    val x8465 = withCtrl(x8476) { OpDef(op=FixAdd, inputs=List(x8464, b2937)).name("x8465").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8464,b2937)
    val x8466 = withCtrl(x8476) { OpDef(op=BitAnd, inputs=List(b2938, b2875)).name("x8466").srcCtx("UnrollingBase.scala:28:66") } // And(b2938,b2875)
    val x8467 = withCtrl(x8476) { OpDef(op=BitAnd, inputs=List(x8466, b2064)).name("x8467").srcCtx("UnrollingBase.scala:28:66") } // And(x8466,b2064)
    val x8468 = withCtrl(x8476) { LoadBanks(List(x7720_d3_b0), List(b2062, x8465)).name("x8468").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2062, x8465),x8467)
    val x8469 = withCtrl(x8476) { LoadBanks(List(x7725_d3_b0), List(x8465)).name("x8469").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8465),x8467)
    val x8470 = withCtrl(x8476) { OpDef(op=FltMul, inputs=List(x8468, x8469)).name("x8470").srcCtx("sysml.scala:606:20") } // FltMul(x8468,x8469)
    val x8471 = withCtrl(x8476) { ReadMem(x8415_d1).name("x8471").srcCtx("sysml.scala:607:13") } // RegRead(x8415)
    val x8472 = withCtrl(x8476) { OpDef(op=FixEql, inputs=List(b2937, Const(0))).name("x8472").srcCtx("sysml.scala:607:13") } // FixEql(b2937,Const(0))
    val x8473 = withCtrl(x8476) { ReduceAccumOp(op=FltAdd, input=x8470, accum=x8471).name("x8473").srcCtx("sysml.scala:607:15") } // FltAdd(x8470,x8471)
    val x8474 = withCtrl(x8476) { OpDef(op=BitAnd, inputs=List(b2875, b2064)).name("x8474").srcCtx("UnrollingBase.scala:28:66") } // And(b2875,b2064)
    val x8475_x8415_d0 = withCtrl(x8476) { WriteMem(x8415_d0, x8473).name("x8475_x8415_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8415,x8473,x8474)
    antiDepsOf(x8475_x8415_d0)=List(x8471)
    val x8475_x8415_d1 = withCtrl(x8476) { WriteMem(x8415_d1, x8473).name("x8475_x8415_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8415,x8473,x8474)
    antiDepsOf(x8475_x8415_d1)=List(x8471)
    val x8477 = withCtrl(x8492) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8477").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8478 = withCtrl(x8492) { CounterChain(List(x8477)).name("x8478").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8477))
    val x8491 = withCtrl(x8492) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8478).name("x8491").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b2876, b2064),x8478,x8416,Block((x8416) => Const(())),List(List(b2952)),List(List(b2953)))
    val b2952 = withCtrl(x8491) { CounterIter(x8477, None).name("b2952") } // b2952
    val b2953 = withCtrl(x8491) { Const(true).name("b2953") } // b2953
    val x8479 = withCtrl(x8491) { OpDef(op=FixMul, inputs=List(b2871, Const(204))).name("x8479").srcCtx("sysml.scala:603:42") } // FixMul(b2871,Const(204))
    val x8480 = withCtrl(x8491) { OpDef(op=FixAdd, inputs=List(x8479, b2952)).name("x8480").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8479,b2952)
    val x8481 = withCtrl(x8491) { OpDef(op=BitAnd, inputs=List(b2953, b2876)).name("x8481").srcCtx("UnrollingBase.scala:28:66") } // And(b2953,b2876)
    val x8482 = withCtrl(x8491) { OpDef(op=BitAnd, inputs=List(x8481, b2064)).name("x8482").srcCtx("UnrollingBase.scala:28:66") } // And(x8481,b2064)
    val x8483 = withCtrl(x8491) { LoadBanks(List(x7720_d4_b0), List(b2062, x8480)).name("x8483").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2062, x8480),x8482)
    val x8484 = withCtrl(x8491) { LoadBanks(List(x7725_d4_b0), List(x8480)).name("x8484").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8480),x8482)
    val x8485 = withCtrl(x8491) { OpDef(op=FltMul, inputs=List(x8483, x8484)).name("x8485").srcCtx("sysml.scala:606:20") } // FltMul(x8483,x8484)
    val x8486 = withCtrl(x8491) { ReadMem(x8416_d1).name("x8486").srcCtx("sysml.scala:607:13") } // RegRead(x8416)
    val x8487 = withCtrl(x8491) { OpDef(op=FixEql, inputs=List(b2952, Const(0))).name("x8487").srcCtx("sysml.scala:607:13") } // FixEql(b2952,Const(0))
    val x8488 = withCtrl(x8491) { ReduceAccumOp(op=FltAdd, input=x8485, accum=x8486).name("x8488").srcCtx("sysml.scala:607:15") } // FltAdd(x8485,x8486)
    val x8489 = withCtrl(x8491) { OpDef(op=BitAnd, inputs=List(b2876, b2064)).name("x8489").srcCtx("UnrollingBase.scala:28:66") } // And(b2876,b2064)
    val x8490_x8416_d0 = withCtrl(x8491) { WriteMem(x8416_d0, x8488).name("x8490_x8416_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8416,x8488,x8489)
    antiDepsOf(x8490_x8416_d0)=List(x8486)
    val x8490_x8416_d1 = withCtrl(x8491) { WriteMem(x8416_d1, x8488).name("x8490_x8416_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8416,x8488,x8489)
    antiDepsOf(x8490_x8416_d1)=List(x8486)
    val x8519 = withCtrl(x8520) { UnitController(style=SeqPipe, level=InnerControl).name("x8519").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2064),Block(x8518))
    val x8493 = withCtrl(x8519) { OpDef(op=BitAnd, inputs=List(b2872, b2064)).name("x8493").srcCtx("sysml.scala:610:11") } // And(b2872,b2064)
    val x8494 = withCtrl(x8519) { OpDef(op=BitAnd, inputs=List(b2873, b2064)).name("x8494").srcCtx("sysml.scala:610:11") } // And(b2873,b2064)
    val x8495 = withCtrl(x8519) { OpDef(op=BitAnd, inputs=List(b2874, b2064)).name("x8495").srcCtx("sysml.scala:610:11") } // And(b2874,b2064)
    val x8496 = withCtrl(x8519) { OpDef(op=BitAnd, inputs=List(b2875, b2064)).name("x8496").srcCtx("sysml.scala:610:11") } // And(b2875,b2064)
    val x8497 = withCtrl(x8519) { OpDef(op=BitAnd, inputs=List(b2876, b2064)).name("x8497").srcCtx("sysml.scala:610:11") } // And(b2876,b2064)
    val x8498 = withCtrl(x8519) { ReadMem(x8413_d0).name("x8498").srcCtx("sysml.scala:609:11") } // RegRead(x8413)
    val x8499 = withCtrl(x8519) { ReadMem(x8412_d0).name("x8499").srcCtx("sysml.scala:609:11") } // RegRead(x8412)
    val x8500 = withCtrl(x8519) { OpDef(op=FltAdd, inputs=List(x8499, x8498)).name("x8500").srcCtx("sysml.scala:610:13") } // FltAdd(x8499,x8498)
    val x8501 = withCtrl(x8519) { OpDef(op=MuxOp, inputs=List(x8494, x8500, x8499)).name("x8501").srcCtx("sysml.scala:610:11") } // Mux(x8494,x8500,x8499)
    val x8502 = withCtrl(x8519) { OpDef(op=BitOr, inputs=List(x8493, x8494)).name("x8502").srcCtx("sysml.scala:610:11") } // Or(x8493,x8494)
    val x8503 = withCtrl(x8519) { ReadMem(x8415_d0).name("x8503").srcCtx("sysml.scala:609:11") } // RegRead(x8415)
    val x8504 = withCtrl(x8519) { ReadMem(x8414_d0).name("x8504").srcCtx("sysml.scala:609:11") } // RegRead(x8414)
    val x8505 = withCtrl(x8519) { OpDef(op=FltAdd, inputs=List(x8504, x8503)).name("x8505").srcCtx("sysml.scala:610:13") } // FltAdd(x8504,x8503)
    val x8506 = withCtrl(x8519) { OpDef(op=MuxOp, inputs=List(x8496, x8505, x8504)).name("x8506").srcCtx("sysml.scala:610:11") } // Mux(x8496,x8505,x8504)
    val x8507 = withCtrl(x8519) { OpDef(op=BitOr, inputs=List(x8495, x8496)).name("x8507").srcCtx("sysml.scala:610:11") } // Or(x8495,x8496)
    val x8508 = withCtrl(x8519) { OpDef(op=FltAdd, inputs=List(x8501, x8506)).name("x8508").srcCtx("sysml.scala:610:13") } // FltAdd(x8501,x8506)
    val x8509 = withCtrl(x8519) { OpDef(op=MuxOp, inputs=List(x8507, x8508, x8501)).name("x8509").srcCtx("sysml.scala:610:11") } // Mux(x8507,x8508,x8501)
    val x8510 = withCtrl(x8519) { OpDef(op=BitOr, inputs=List(x8502, x8507)).name("x8510").srcCtx("sysml.scala:610:11") } // Or(x8502,x8507)
    val x8511 = withCtrl(x8519) { ReadMem(x8416_d0).name("x8511").srcCtx("sysml.scala:609:11") } // RegRead(x8416)
    val x8512 = withCtrl(x8519) { OpDef(op=FltAdd, inputs=List(x8509, x8511)).name("x8512").srcCtx("sysml.scala:610:13") } // FltAdd(x8509,x8511)
    val x8513 = withCtrl(x8519) { OpDef(op=MuxOp, inputs=List(x8497, x8512, x8509)).name("x8513").srcCtx("sysml.scala:610:11") } // Mux(x8497,x8512,x8509)
    val x8514 = withCtrl(x8519) { OpDef(op=BitOr, inputs=List(x8510, x8497)).name("x8514").srcCtx("sysml.scala:610:11") } // Or(x8510,x8497)
    val x8515 = withCtrl(x8519) { ReadMem(x8408_d1).name("x8515").srcCtx("sysml.scala:610:11") } // RegRead(x8408)
    val x8516 = withCtrl(x8519) { OpDef(op=FixEql, inputs=List(b2867, Const(0))).name("x8516").srcCtx("sysml.scala:610:11") } // FixEql(b2867,Const(0))
    val x8517 = withCtrl(x8519) { OpDef(op=FltAdd, inputs=List(x8513, x8515)).name("x8517").srcCtx("sysml.scala:610:13") } // FltAdd(x8513,x8515)
    val x8518_x8408_d0 = withCtrl(x8519) { WriteMem(x8408_d0, x8517).name("x8518_x8408_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x8408,x8517,b2064)
    antiDepsOf(x8518_x8408_d0)=List(x8515)
    val x8518_x8408_d1 = withCtrl(x8519) { WriteMem(x8408_d1, x8517).name("x8518_x8408_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x8408,x8517,b2064)
    antiDepsOf(x8518_x8408_d1)=List(x8515)
    val x8521 = withCtrl(x8632) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x8521").srcCtx("sysml.scala:598:26") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x8522 = withCtrl(x8632) { CounterChain(List(x8521)).name("x8522").srcCtx("sysml.scala:610:11") } // CounterChainNew(List(x8521))
    val x8631 = withCtrl(x8632) { LoopController(style=MetaPipe, level=OuterControl, cchain=x8522).name("x8631").srcCtx("sysml.scala:610:11") } // UnrolledReduce(List(b2065),x8522,x8409,Block((x8409) => Const(())),List(List(b2996, b2997, b2998, b2999, b3000)),List(List(b3001, b3002, b3003, b3004, b3005)))
    val b2996 = withCtrl(x8631) { CounterIter(x8521, Some(0)).name("b2996") } // b2996
    val b3001 = withCtrl(x8631) { Const(true).name("b3001") } // b3001
    val b2997 = withCtrl(x8631) { CounterIter(x8521, Some(1)).name("b2997") } // b2997
    val b3002 = withCtrl(x8631) { Const(true).name("b3002") } // b3002
    val b2998 = withCtrl(x8631) { CounterIter(x8521, Some(2)).name("b2998") } // b2998
    val b3003 = withCtrl(x8631) { Const(true).name("b3003") } // b3003
    val b2999 = withCtrl(x8631) { CounterIter(x8521, Some(3)).name("b2999") } // b2999
    val b3004 = withCtrl(x8631) { Const(true).name("b3004") } // b3004
    val b3000 = withCtrl(x8631) { CounterIter(x8521, Some(4)).name("b3000") } // b3000
    val b3005 = withCtrl(x8631) { Const(true).name("b3005") } // b3005
    val x8523_d0 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8523_d0").srcCtx("sysml.scala:600:39:gInner") } // x8523 = RegNew(Const(0.0))
    isAccum(x8523_d0) = false
    bufferDepthOf(x8523_d0) = 1
    val x8523_d1 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8523_d1").srcCtx("sysml.scala:600:39:gInner") } // x8523 = RegNew(Const(0.0))
    isAccum(x8523_d1) = true
    bufferDepthOf(x8523_d1) = 1
    val x8524_d0 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8524_d0").srcCtx("sysml.scala:600:39:gInner") } // x8524 = RegNew(Const(0.0))
    isAccum(x8524_d0) = false
    bufferDepthOf(x8524_d0) = 1
    val x8524_d1 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8524_d1").srcCtx("sysml.scala:600:39:gInner") } // x8524 = RegNew(Const(0.0))
    isAccum(x8524_d1) = true
    bufferDepthOf(x8524_d1) = 1
    val x8525_d0 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8525_d0").srcCtx("sysml.scala:600:39:gInner") } // x8525 = RegNew(Const(0.0))
    isAccum(x8525_d0) = false
    bufferDepthOf(x8525_d0) = 1
    val x8525_d1 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8525_d1").srcCtx("sysml.scala:600:39:gInner") } // x8525 = RegNew(Const(0.0))
    isAccum(x8525_d1) = true
    bufferDepthOf(x8525_d1) = 1
    val x8526_d0 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8526_d0").srcCtx("sysml.scala:600:39:gInner") } // x8526 = RegNew(Const(0.0))
    isAccum(x8526_d0) = false
    bufferDepthOf(x8526_d0) = 1
    val x8526_d1 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8526_d1").srcCtx("sysml.scala:600:39:gInner") } // x8526 = RegNew(Const(0.0))
    isAccum(x8526_d1) = true
    bufferDepthOf(x8526_d1) = 1
    val x8527_d0 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8527_d0").srcCtx("sysml.scala:600:39:gInner") } // x8527 = RegNew(Const(0.0))
    isAccum(x8527_d0) = false
    bufferDepthOf(x8527_d0) = 1
    val x8527_d1 = withCtrl(x8631) { Reg(init=Some(0.0)).name("x8527_d1").srcCtx("sysml.scala:600:39:gInner") } // x8527 = RegNew(Const(0.0))
    isAccum(x8527_d1) = true
    bufferDepthOf(x8527_d1) = 1
    val x8603 = withCtrl(x8631) { UnitController(style=ForkJoin, level=OuterControl).name("x8603").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b2065),Block(Const(())))
    val x8528 = withCtrl(x8603) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8528").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8529 = withCtrl(x8603) { CounterChain(List(x8528)).name("x8529").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8528))
    val x8542 = withCtrl(x8603) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8529).name("x8542").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b3001, b2065),x8529,x8523,Block((x8523) => Const(())),List(List(b3021)),List(List(b3022)))
    val b3021 = withCtrl(x8542) { CounterIter(x8528, None).name("b3021") } // b3021
    val b3022 = withCtrl(x8542) { Const(true).name("b3022") } // b3022
    val x8530 = withCtrl(x8542) { OpDef(op=FixMul, inputs=List(b2996, Const(204))).name("x8530").srcCtx("sysml.scala:603:42") } // FixMul(b2996,Const(204))
    val x8531 = withCtrl(x8542) { OpDef(op=FixAdd, inputs=List(x8530, b3021)).name("x8531").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8530,b3021)
    val x8532 = withCtrl(x8542) { OpDef(op=BitAnd, inputs=List(b3022, b3001)).name("x8532").srcCtx("UnrollingBase.scala:28:66") } // And(b3022,b3001)
    val x8533 = withCtrl(x8542) { OpDef(op=BitAnd, inputs=List(x8532, b2065)).name("x8533").srcCtx("UnrollingBase.scala:28:66") } // And(x8532,b2065)
    val x8534 = withCtrl(x8542) { LoadBanks(List(x7720_d0_b1), List(b2063, x8531)).name("x8534").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2063, x8531),x8533)
    val x8535 = withCtrl(x8542) { LoadBanks(List(x7725_d5_b0), List(x8531)).name("x8535").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8531),x8533)
    val x8536 = withCtrl(x8542) { OpDef(op=FltMul, inputs=List(x8534, x8535)).name("x8536").srcCtx("sysml.scala:606:20") } // FltMul(x8534,x8535)
    val x8537 = withCtrl(x8542) { ReadMem(x8523_d1).name("x8537").srcCtx("sysml.scala:607:13") } // RegRead(x8523)
    val x8538 = withCtrl(x8542) { OpDef(op=FixEql, inputs=List(b3021, Const(0))).name("x8538").srcCtx("sysml.scala:607:13") } // FixEql(b3021,Const(0))
    val x8539 = withCtrl(x8542) { ReduceAccumOp(op=FltAdd, input=x8536, accum=x8537).name("x8539").srcCtx("sysml.scala:607:15") } // FltAdd(x8536,x8537)
    val x8540 = withCtrl(x8542) { OpDef(op=BitAnd, inputs=List(b3001, b2065)).name("x8540").srcCtx("UnrollingBase.scala:28:66") } // And(b3001,b2065)
    val x8541_x8523_d0 = withCtrl(x8542) { WriteMem(x8523_d0, x8539).name("x8541_x8523_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8523,x8539,x8540)
    antiDepsOf(x8541_x8523_d0)=List(x8537)
    val x8541_x8523_d1 = withCtrl(x8542) { WriteMem(x8523_d1, x8539).name("x8541_x8523_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8523,x8539,x8540)
    antiDepsOf(x8541_x8523_d1)=List(x8537)
    val x8543 = withCtrl(x8603) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8543").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8544 = withCtrl(x8603) { CounterChain(List(x8543)).name("x8544").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8543))
    val x8557 = withCtrl(x8603) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8544).name("x8557").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b3002, b2065),x8544,x8524,Block((x8524) => Const(())),List(List(b3036)),List(List(b3037)))
    val b3036 = withCtrl(x8557) { CounterIter(x8543, None).name("b3036") } // b3036
    val b3037 = withCtrl(x8557) { Const(true).name("b3037") } // b3037
    val x8545 = withCtrl(x8557) { OpDef(op=FixMul, inputs=List(b2997, Const(204))).name("x8545").srcCtx("sysml.scala:603:42") } // FixMul(b2997,Const(204))
    val x8546 = withCtrl(x8557) { OpDef(op=FixAdd, inputs=List(x8545, b3036)).name("x8546").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8545,b3036)
    val x8547 = withCtrl(x8557) { OpDef(op=BitAnd, inputs=List(b3037, b3002)).name("x8547").srcCtx("UnrollingBase.scala:28:66") } // And(b3037,b3002)
    val x8548 = withCtrl(x8557) { OpDef(op=BitAnd, inputs=List(x8547, b2065)).name("x8548").srcCtx("UnrollingBase.scala:28:66") } // And(x8547,b2065)
    val x8549 = withCtrl(x8557) { LoadBanks(List(x7720_d1_b1), List(b2063, x8546)).name("x8549").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2063, x8546),x8548)
    val x8550 = withCtrl(x8557) { LoadBanks(List(x7725_d6_b0), List(x8546)).name("x8550").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8546),x8548)
    val x8551 = withCtrl(x8557) { OpDef(op=FltMul, inputs=List(x8549, x8550)).name("x8551").srcCtx("sysml.scala:606:20") } // FltMul(x8549,x8550)
    val x8552 = withCtrl(x8557) { ReadMem(x8524_d1).name("x8552").srcCtx("sysml.scala:607:13") } // RegRead(x8524)
    val x8553 = withCtrl(x8557) { OpDef(op=FixEql, inputs=List(b3036, Const(0))).name("x8553").srcCtx("sysml.scala:607:13") } // FixEql(b3036,Const(0))
    val x8554 = withCtrl(x8557) { ReduceAccumOp(op=FltAdd, input=x8551, accum=x8552).name("x8554").srcCtx("sysml.scala:607:15") } // FltAdd(x8551,x8552)
    val x8555 = withCtrl(x8557) { OpDef(op=BitAnd, inputs=List(b3002, b2065)).name("x8555").srcCtx("UnrollingBase.scala:28:66") } // And(b3002,b2065)
    val x8556_x8524_d0 = withCtrl(x8557) { WriteMem(x8524_d0, x8554).name("x8556_x8524_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8524,x8554,x8555)
    antiDepsOf(x8556_x8524_d0)=List(x8552)
    val x8556_x8524_d1 = withCtrl(x8557) { WriteMem(x8524_d1, x8554).name("x8556_x8524_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8524,x8554,x8555)
    antiDepsOf(x8556_x8524_d1)=List(x8552)
    val x8558 = withCtrl(x8603) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8558").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8559 = withCtrl(x8603) { CounterChain(List(x8558)).name("x8559").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8558))
    val x8572 = withCtrl(x8603) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8559).name("x8572").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b3003, b2065),x8559,x8525,Block((x8525) => Const(())),List(List(b3051)),List(List(b3052)))
    val b3051 = withCtrl(x8572) { CounterIter(x8558, None).name("b3051") } // b3051
    val b3052 = withCtrl(x8572) { Const(true).name("b3052") } // b3052
    val x8560 = withCtrl(x8572) { OpDef(op=FixMul, inputs=List(b2998, Const(204))).name("x8560").srcCtx("sysml.scala:603:42") } // FixMul(b2998,Const(204))
    val x8561 = withCtrl(x8572) { OpDef(op=FixAdd, inputs=List(x8560, b3051)).name("x8561").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8560,b3051)
    val x8562 = withCtrl(x8572) { OpDef(op=BitAnd, inputs=List(b3052, b3003)).name("x8562").srcCtx("UnrollingBase.scala:28:66") } // And(b3052,b3003)
    val x8563 = withCtrl(x8572) { OpDef(op=BitAnd, inputs=List(x8562, b2065)).name("x8563").srcCtx("UnrollingBase.scala:28:66") } // And(x8562,b2065)
    val x8564 = withCtrl(x8572) { LoadBanks(List(x7720_d2_b1), List(b2063, x8561)).name("x8564").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2063, x8561),x8563)
    val x8565 = withCtrl(x8572) { LoadBanks(List(x7725_d7_b0), List(x8561)).name("x8565").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8561),x8563)
    val x8566 = withCtrl(x8572) { OpDef(op=FltMul, inputs=List(x8564, x8565)).name("x8566").srcCtx("sysml.scala:606:20") } // FltMul(x8564,x8565)
    val x8567 = withCtrl(x8572) { ReadMem(x8525_d1).name("x8567").srcCtx("sysml.scala:607:13") } // RegRead(x8525)
    val x8568 = withCtrl(x8572) { OpDef(op=FixEql, inputs=List(b3051, Const(0))).name("x8568").srcCtx("sysml.scala:607:13") } // FixEql(b3051,Const(0))
    val x8569 = withCtrl(x8572) { ReduceAccumOp(op=FltAdd, input=x8566, accum=x8567).name("x8569").srcCtx("sysml.scala:607:15") } // FltAdd(x8566,x8567)
    val x8570 = withCtrl(x8572) { OpDef(op=BitAnd, inputs=List(b3003, b2065)).name("x8570").srcCtx("UnrollingBase.scala:28:66") } // And(b3003,b2065)
    val x8571_x8525_d0 = withCtrl(x8572) { WriteMem(x8525_d0, x8569).name("x8571_x8525_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8525,x8569,x8570)
    antiDepsOf(x8571_x8525_d0)=List(x8567)
    val x8571_x8525_d1 = withCtrl(x8572) { WriteMem(x8525_d1, x8569).name("x8571_x8525_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8525,x8569,x8570)
    antiDepsOf(x8571_x8525_d1)=List(x8567)
    val x8573 = withCtrl(x8603) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8573").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8574 = withCtrl(x8603) { CounterChain(List(x8573)).name("x8574").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8573))
    val x8587 = withCtrl(x8603) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8574).name("x8587").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b3004, b2065),x8574,x8526,Block((x8526) => Const(())),List(List(b3066)),List(List(b3067)))
    val b3066 = withCtrl(x8587) { CounterIter(x8573, None).name("b3066") } // b3066
    val b3067 = withCtrl(x8587) { Const(true).name("b3067") } // b3067
    val x8575 = withCtrl(x8587) { OpDef(op=FixMul, inputs=List(b2999, Const(204))).name("x8575").srcCtx("sysml.scala:603:42") } // FixMul(b2999,Const(204))
    val x8576 = withCtrl(x8587) { OpDef(op=FixAdd, inputs=List(x8575, b3066)).name("x8576").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8575,b3066)
    val x8577 = withCtrl(x8587) { OpDef(op=BitAnd, inputs=List(b3067, b3004)).name("x8577").srcCtx("UnrollingBase.scala:28:66") } // And(b3067,b3004)
    val x8578 = withCtrl(x8587) { OpDef(op=BitAnd, inputs=List(x8577, b2065)).name("x8578").srcCtx("UnrollingBase.scala:28:66") } // And(x8577,b2065)
    val x8579 = withCtrl(x8587) { LoadBanks(List(x7720_d3_b1), List(b2063, x8576)).name("x8579").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2063, x8576),x8578)
    val x8580 = withCtrl(x8587) { LoadBanks(List(x7725_d8_b0), List(x8576)).name("x8580").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8576),x8578)
    val x8581 = withCtrl(x8587) { OpDef(op=FltMul, inputs=List(x8579, x8580)).name("x8581").srcCtx("sysml.scala:606:20") } // FltMul(x8579,x8580)
    val x8582 = withCtrl(x8587) { ReadMem(x8526_d1).name("x8582").srcCtx("sysml.scala:607:13") } // RegRead(x8526)
    val x8583 = withCtrl(x8587) { OpDef(op=FixEql, inputs=List(b3066, Const(0))).name("x8583").srcCtx("sysml.scala:607:13") } // FixEql(b3066,Const(0))
    val x8584 = withCtrl(x8587) { ReduceAccumOp(op=FltAdd, input=x8581, accum=x8582).name("x8584").srcCtx("sysml.scala:607:15") } // FltAdd(x8581,x8582)
    val x8585 = withCtrl(x8587) { OpDef(op=BitAnd, inputs=List(b3004, b2065)).name("x8585").srcCtx("UnrollingBase.scala:28:66") } // And(b3004,b2065)
    val x8586_x8526_d0 = withCtrl(x8587) { WriteMem(x8526_d0, x8584).name("x8586_x8526_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8526,x8584,x8585)
    antiDepsOf(x8586_x8526_d0)=List(x8582)
    val x8586_x8526_d1 = withCtrl(x8587) { WriteMem(x8526_d1, x8584).name("x8586_x8526_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8526,x8584,x8585)
    antiDepsOf(x8586_x8526_d1)=List(x8582)
    val x8588 = withCtrl(x8603) { Counter(min=Const(0), max=Const(204), step=Const(1), par=64).name("x8588").srcCtx("sysml.scala:601:38") } // CounterNew(Const(0),Const(204),Const(1),Const(64))
    val x8589 = withCtrl(x8603) { CounterChain(List(x8588)).name("x8589").srcCtx("sysml.scala:607:13") } // CounterChainNew(List(x8588))
    val x8602 = withCtrl(x8603) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8589).name("x8602").srcCtx("sysml.scala:607:13") } // UnrolledReduce(List(b3005, b2065),x8589,x8527,Block((x8527) => Const(())),List(List(b3081)),List(List(b3082)))
    val b3081 = withCtrl(x8602) { CounterIter(x8588, None).name("b3081") } // b3081
    val b3082 = withCtrl(x8602) { Const(true).name("b3082") } // b3082
    val x8590 = withCtrl(x8602) { OpDef(op=FixMul, inputs=List(b3000, Const(204))).name("x8590").srcCtx("sysml.scala:603:42") } // FixMul(b3000,Const(204))
    val x8591 = withCtrl(x8602) { OpDef(op=FixAdd, inputs=List(x8590, b3081)).name("x8591").srcCtx("sysml.scala:603:64:iReduceIdx") } // FixAdd(x8590,b3081)
    val x8592 = withCtrl(x8602) { OpDef(op=BitAnd, inputs=List(b3082, b3005)).name("x8592").srcCtx("UnrollingBase.scala:28:66") } // And(b3082,b3005)
    val x8593 = withCtrl(x8602) { OpDef(op=BitAnd, inputs=List(x8592, b2065)).name("x8593").srcCtx("UnrollingBase.scala:28:66") } // And(x8592,b2065)
    val x8594 = withCtrl(x8602) { LoadBanks(List(x7720_d4_b1), List(b2063, x8591)).name("x8594").srcCtx("sysml.scala:604:33:wxhEle") } // LUTLoad(x7720,List(b2063, x8591),x8593)
    val x8595 = withCtrl(x8602) { LoadBanks(List(x7725_d9_b0), List(x8591)).name("x8595").srcCtx("sysml.scala:605:32:xhEle") } // LUTLoad(x7725,List(x8591),x8593)
    val x8596 = withCtrl(x8602) { OpDef(op=FltMul, inputs=List(x8594, x8595)).name("x8596").srcCtx("sysml.scala:606:20") } // FltMul(x8594,x8595)
    val x8597 = withCtrl(x8602) { ReadMem(x8527_d1).name("x8597").srcCtx("sysml.scala:607:13") } // RegRead(x8527)
    val x8598 = withCtrl(x8602) { OpDef(op=FixEql, inputs=List(b3081, Const(0))).name("x8598").srcCtx("sysml.scala:607:13") } // FixEql(b3081,Const(0))
    val x8599 = withCtrl(x8602) { ReduceAccumOp(op=FltAdd, input=x8596, accum=x8597).name("x8599").srcCtx("sysml.scala:607:15") } // FltAdd(x8596,x8597)
    val x8600 = withCtrl(x8602) { OpDef(op=BitAnd, inputs=List(b3005, b2065)).name("x8600").srcCtx("UnrollingBase.scala:28:66") } // And(b3005,b2065)
    val x8601_x8527_d0 = withCtrl(x8602) { WriteMem(x8527_d0, x8599).name("x8601_x8527_d0").srcCtx("sysml.scala:607:13") } // RegWrite(x8527,x8599,x8600)
    antiDepsOf(x8601_x8527_d0)=List(x8597)
    val x8601_x8527_d1 = withCtrl(x8602) { WriteMem(x8527_d1, x8599).name("x8601_x8527_d1").srcCtx("sysml.scala:607:13") } // RegWrite(x8527,x8599,x8600)
    antiDepsOf(x8601_x8527_d1)=List(x8597)
    val x8630 = withCtrl(x8631) { UnitController(style=SeqPipe, level=InnerControl).name("x8630").srcCtx("sysml.scala:610:11") } // UnitPipe(List(b2065),Block(x8629))
    val x8604 = withCtrl(x8630) { OpDef(op=BitAnd, inputs=List(b3001, b2065)).name("x8604").srcCtx("sysml.scala:610:11") } // And(b3001,b2065)
    val x8605 = withCtrl(x8630) { OpDef(op=BitAnd, inputs=List(b3002, b2065)).name("x8605").srcCtx("sysml.scala:610:11") } // And(b3002,b2065)
    val x8606 = withCtrl(x8630) { OpDef(op=BitAnd, inputs=List(b3003, b2065)).name("x8606").srcCtx("sysml.scala:610:11") } // And(b3003,b2065)
    val x8607 = withCtrl(x8630) { OpDef(op=BitAnd, inputs=List(b3004, b2065)).name("x8607").srcCtx("sysml.scala:610:11") } // And(b3004,b2065)
    val x8608 = withCtrl(x8630) { OpDef(op=BitAnd, inputs=List(b3005, b2065)).name("x8608").srcCtx("sysml.scala:610:11") } // And(b3005,b2065)
    val x8609 = withCtrl(x8630) { ReadMem(x8524_d0).name("x8609").srcCtx("sysml.scala:609:11") } // RegRead(x8524)
    val x8610 = withCtrl(x8630) { ReadMem(x8523_d0).name("x8610").srcCtx("sysml.scala:609:11") } // RegRead(x8523)
    val x8611 = withCtrl(x8630) { OpDef(op=FltAdd, inputs=List(x8610, x8609)).name("x8611").srcCtx("sysml.scala:610:13") } // FltAdd(x8610,x8609)
    val x8612 = withCtrl(x8630) { OpDef(op=MuxOp, inputs=List(x8605, x8611, x8610)).name("x8612").srcCtx("sysml.scala:610:11") } // Mux(x8605,x8611,x8610)
    val x8613 = withCtrl(x8630) { OpDef(op=BitOr, inputs=List(x8604, x8605)).name("x8613").srcCtx("sysml.scala:610:11") } // Or(x8604,x8605)
    val x8614 = withCtrl(x8630) { ReadMem(x8526_d0).name("x8614").srcCtx("sysml.scala:609:11") } // RegRead(x8526)
    val x8615 = withCtrl(x8630) { ReadMem(x8525_d0).name("x8615").srcCtx("sysml.scala:609:11") } // RegRead(x8525)
    val x8616 = withCtrl(x8630) { OpDef(op=FltAdd, inputs=List(x8615, x8614)).name("x8616").srcCtx("sysml.scala:610:13") } // FltAdd(x8615,x8614)
    val x8617 = withCtrl(x8630) { OpDef(op=MuxOp, inputs=List(x8607, x8616, x8615)).name("x8617").srcCtx("sysml.scala:610:11") } // Mux(x8607,x8616,x8615)
    val x8618 = withCtrl(x8630) { OpDef(op=BitOr, inputs=List(x8606, x8607)).name("x8618").srcCtx("sysml.scala:610:11") } // Or(x8606,x8607)
    val x8619 = withCtrl(x8630) { OpDef(op=FltAdd, inputs=List(x8612, x8617)).name("x8619").srcCtx("sysml.scala:610:13") } // FltAdd(x8612,x8617)
    val x8620 = withCtrl(x8630) { OpDef(op=MuxOp, inputs=List(x8618, x8619, x8612)).name("x8620").srcCtx("sysml.scala:610:11") } // Mux(x8618,x8619,x8612)
    val x8621 = withCtrl(x8630) { OpDef(op=BitOr, inputs=List(x8613, x8618)).name("x8621").srcCtx("sysml.scala:610:11") } // Or(x8613,x8618)
    val x8622 = withCtrl(x8630) { ReadMem(x8527_d0).name("x8622").srcCtx("sysml.scala:609:11") } // RegRead(x8527)
    val x8623 = withCtrl(x8630) { OpDef(op=FltAdd, inputs=List(x8620, x8622)).name("x8623").srcCtx("sysml.scala:610:13") } // FltAdd(x8620,x8622)
    val x8624 = withCtrl(x8630) { OpDef(op=MuxOp, inputs=List(x8608, x8623, x8620)).name("x8624").srcCtx("sysml.scala:610:11") } // Mux(x8608,x8623,x8620)
    val x8625 = withCtrl(x8630) { OpDef(op=BitOr, inputs=List(x8621, x8608)).name("x8625").srcCtx("sysml.scala:610:11") } // Or(x8621,x8608)
    val x8626 = withCtrl(x8630) { ReadMem(x8409_d1).name("x8626").srcCtx("sysml.scala:610:11") } // RegRead(x8409)
    val x8627 = withCtrl(x8630) { OpDef(op=FixEql, inputs=List(b2996, Const(0))).name("x8627").srcCtx("sysml.scala:610:11") } // FixEql(b2996,Const(0))
    val x8628 = withCtrl(x8630) { OpDef(op=FltAdd, inputs=List(x8624, x8626)).name("x8628").srcCtx("sysml.scala:610:13") } // FltAdd(x8624,x8626)
    val x8629_x8409_d0 = withCtrl(x8630) { WriteMem(x8409_d0, x8628).name("x8629_x8409_d0").srcCtx("sysml.scala:610:11") } // RegWrite(x8409,x8628,b2065)
    antiDepsOf(x8629_x8409_d0)=List(x8626)
    val x8629_x8409_d1 = withCtrl(x8630) { WriteMem(x8409_d1, x8628).name("x8629_x8409_d1").srcCtx("sysml.scala:610:11") } // RegWrite(x8409,x8628,b2065)
    antiDepsOf(x8629_x8409_d1)=List(x8626)
    val x8633 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8633").srcCtx("sysml.scala:1091:24:siReg") } // x8633 = RegNew(Const(0.0))
    isAccum(x8633) = false
    bufferDepthOf(x8633) = 1
    val x8634 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8634").srcCtx("sysml.scala:1091:24:siReg") } // x8634 = RegNew(Const(0.0))
    isAccum(x8634) = false
    bufferDepthOf(x8634) = 1
    val x8635 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8635").srcCtx("sysml.scala:1092:24:tjReg") } // x8635 = RegNew(Const(0.0))
    isAccum(x8635) = false
    bufferDepthOf(x8635) = 1
    val x8636 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8636").srcCtx("sysml.scala:1092:24:tjReg") } // x8636 = RegNew(Const(0.0))
    isAccum(x8636) = false
    bufferDepthOf(x8636) = 1
    val x8637 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8637").srcCtx("sysml.scala:1093:24:sfReg") } // x8637 = RegNew(Const(0.0))
    isAccum(x8637) = false
    bufferDepthOf(x8637) = 1
    val x8638 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8638").srcCtx("sysml.scala:1093:24:sfReg") } // x8638 = RegNew(Const(0.0))
    isAccum(x8638) = false
    bufferDepthOf(x8638) = 1
    val x8639 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8639").srcCtx("sysml.scala:1094:24:soReg") } // x8639 = RegNew(Const(0.0))
    isAccum(x8639) = false
    bufferDepthOf(x8639) = 2
    val x8640 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8640").srcCtx("sysml.scala:1094:24:soReg") } // x8640 = RegNew(Const(0.0))
    isAccum(x8640) = false
    bufferDepthOf(x8640) = 2
    val x8641 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8641").srcCtx("sysml.scala:382:49") } // x8641 = RegNew(Const(0.0))
    isAccum(x8641) = false
    bufferDepthOf(x8641) = 2
    val x8642 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8642").srcCtx("sysml.scala:382:49") } // x8642 = RegNew(Const(0.0))
    isAccum(x8642) = false
    bufferDepthOf(x8642) = 2
    val x8643 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8643").srcCtx("sysml.scala:382:49") } // x8643 = RegNew(Const(0.0))
    isAccum(x8643) = false
    bufferDepthOf(x8643) = 2
    val x8644 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8644").srcCtx("sysml.scala:382:49") } // x8644 = RegNew(Const(0.0))
    isAccum(x8644) = false
    bufferDepthOf(x8644) = 2
    val x8645 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8645").srcCtx("sysml.scala:382:49") } // x8645 = RegNew(Const(0.0))
    isAccum(x8645) = false
    bufferDepthOf(x8645) = 2
    val x8646 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8646").srcCtx("sysml.scala:382:49") } // x8646 = RegNew(Const(0.0))
    isAccum(x8646) = false
    bufferDepthOf(x8646) = 2
    val x8647 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8647").srcCtx("sysml.scala:382:49") } // x8647 = RegNew(Const(0.0))
    isAccum(x8647) = false
    bufferDepthOf(x8647) = 2
    val x8648 = withCtrl(x8807) { Reg(init=Some(0.0)).name("x8648").srcCtx("sysml.scala:382:49") } // x8648 = RegNew(Const(0.0))
    isAccum(x8648) = false
    bufferDepthOf(x8648) = 2
    val x8649 = withCtrl(x8807) { FIFO(size=1).name("x8649").srcCtx("sysml.scala:1100:27:indexQ") } // x8649 = FIFONew(Const(1))
    isAccum(x8649) = false
    bufferDepthOf(x8649) = 2
    val x8650 = withCtrl(x8807) { FIFO(size=1).name("x8650").srcCtx("sysml.scala:1100:27:indexQ") } // x8650 = FIFONew(Const(1))
    isAccum(x8650) = false
    bufferDepthOf(x8650) = 2
    val x8651 = withCtrl(x8807) { FIFO(size=1).name("x8651").srcCtx("sysml.scala:1103:28:cIndexQ") } // x8651 = FIFONew(Const(1))
    isAccum(x8651) = false
    bufferDepthOf(x8651) = 2
    val x8652 = withCtrl(x8807) { FIFO(size=1).name("x8652").srcCtx("sysml.scala:1103:28:cIndexQ") } // x8652 = FIFONew(Const(1))
    isAccum(x8652) = false
    bufferDepthOf(x8652) = 2
    val x8687 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8687").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x8669 = withCtrl(x8687) { UnitController(style=SeqPipe, level=InnerControl).name("x8669").srcCtx("sysml.scala:382:49") } // UnitPipe(List(b2064),Block(Const(())))
    val x8653 = withCtrl(x8669) { LoadBanks(List(x7721_d0_b0), List(b2062)).name("x8653").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7721,List(b2062),b2064)
    val x8654 = withCtrl(x8669) { ReadMem(x7733_d0).name("x8654").srcCtx("sysml.scala:612:11") } // RegRead(x7733)
    val x8655 = withCtrl(x8669) { OpDef(op=FltAdd, inputs=List(x8653, x8654)).name("x8655").srcCtx("sysml.scala:1097:31") } // FltAdd(x8653,x8654)
    val x8656 = withCtrl(x8669) { LoadBanks(List(x7722_d0_b0), List(b2062)).name("x8656").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7722,List(b2062),b2064)
    val x8657 = withCtrl(x8669) { ReadMem(x7958_d0).name("x8657").srcCtx("sysml.scala:612:11") } // RegRead(x7958)
    val x8658 = withCtrl(x8669) { OpDef(op=FltAdd, inputs=List(x8656, x8657)).name("x8658").srcCtx("sysml.scala:1097:31") } // FltAdd(x8656,x8657)
    val x8659 = withCtrl(x8669) { LoadBanks(List(x7723_d0_b0), List(b2062)).name("x8659").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7723,List(b2062),b2064)
    val x8660 = withCtrl(x8669) { ReadMem(x8183_d0).name("x8660").srcCtx("sysml.scala:612:11") } // RegRead(x8183)
    val x8661 = withCtrl(x8669) { OpDef(op=FltAdd, inputs=List(x8659, x8660)).name("x8661").srcCtx("sysml.scala:1097:31") } // FltAdd(x8659,x8660)
    val x8662 = withCtrl(x8669) { LoadBanks(List(x7724_d0_b0), List(b2062)).name("x8662").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7724,List(b2062),b2064)
    val x8663 = withCtrl(x8669) { ReadMem(x8408_d0).name("x8663").srcCtx("sysml.scala:612:11") } // RegRead(x8408)
    val x8664 = withCtrl(x8669) { OpDef(op=FltAdd, inputs=List(x8662, x8663)).name("x8664").srcCtx("sysml.scala:1097:31") } // FltAdd(x8662,x8663)
    val x8665_x8641 = withCtrl(x8669) { WriteMem(x8641, x8655).name("x8665_x8641").srcCtx("sysml.scala:382:49") } // RegWrite(x8641,x8655,b2064)
    val x8666_x8643 = withCtrl(x8669) { WriteMem(x8643, x8658).name("x8666_x8643").srcCtx("sysml.scala:382:49") } // RegWrite(x8643,x8658,b2064)
    val x8667_x8645 = withCtrl(x8669) { WriteMem(x8645, x8661).name("x8667_x8645").srcCtx("sysml.scala:382:49") } // RegWrite(x8645,x8661,b2064)
    val x8668_x8647 = withCtrl(x8669) { WriteMem(x8647, x8664).name("x8668_x8647").srcCtx("sysml.scala:382:49") } // RegWrite(x8647,x8664,b2064)
    val x8686 = withCtrl(x8687) { UnitController(style=SeqPipe, level=InnerControl).name("x8686").srcCtx("sysml.scala:382:49") } // UnitPipe(List(b2065),Block(Const(())))
    val x8670 = withCtrl(x8686) { LoadBanks(List(x7721_d0_b0), List(b2063)).name("x8670").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7721,List(b2063),b2065)
    val x8671 = withCtrl(x8686) { ReadMem(x7734_d0).name("x8671").srcCtx("sysml.scala:612:11") } // RegRead(x7734)
    val x8672 = withCtrl(x8686) { OpDef(op=FltAdd, inputs=List(x8670, x8671)).name("x8672").srcCtx("sysml.scala:1097:31") } // FltAdd(x8670,x8671)
    val x8673 = withCtrl(x8686) { LoadBanks(List(x7722_d0_b0), List(b2063)).name("x8673").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7722,List(b2063),b2065)
    val x8674 = withCtrl(x8686) { ReadMem(x7959_d0).name("x8674").srcCtx("sysml.scala:612:11") } // RegRead(x7959)
    val x8675 = withCtrl(x8686) { OpDef(op=FltAdd, inputs=List(x8673, x8674)).name("x8675").srcCtx("sysml.scala:1097:31") } // FltAdd(x8673,x8674)
    val x8676 = withCtrl(x8686) { LoadBanks(List(x7723_d0_b0), List(b2063)).name("x8676").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7723,List(b2063),b2065)
    val x8677 = withCtrl(x8686) { ReadMem(x8184_d0).name("x8677").srcCtx("sysml.scala:612:11") } // RegRead(x8184)
    val x8678 = withCtrl(x8686) { OpDef(op=FltAdd, inputs=List(x8676, x8677)).name("x8678").srcCtx("sysml.scala:1097:31") } // FltAdd(x8676,x8677)
    val x8679 = withCtrl(x8686) { LoadBanks(List(x7724_d0_b0), List(b2063)).name("x8679").srcCtx("sysml.scala:1097:17") } // LUTLoad(x7724,List(b2063),b2065)
    val x8680 = withCtrl(x8686) { ReadMem(x8409_d0).name("x8680").srcCtx("sysml.scala:612:11") } // RegRead(x8409)
    val x8681 = withCtrl(x8686) { OpDef(op=FltAdd, inputs=List(x8679, x8680)).name("x8681").srcCtx("sysml.scala:1097:31") } // FltAdd(x8679,x8680)
    val x8682_x8642 = withCtrl(x8686) { WriteMem(x8642, x8672).name("x8682_x8642").srcCtx("sysml.scala:382:49") } // RegWrite(x8642,x8672,b2065)
    val x8683_x8644 = withCtrl(x8686) { WriteMem(x8644, x8675).name("x8683_x8644").srcCtx("sysml.scala:382:49") } // RegWrite(x8644,x8675,b2065)
    val x8684_x8646 = withCtrl(x8686) { WriteMem(x8646, x8678).name("x8684_x8646").srcCtx("sysml.scala:382:49") } // RegWrite(x8646,x8678,b2065)
    val x8685_x8648 = withCtrl(x8686) { WriteMem(x8648, x8681).name("x8685_x8648").srcCtx("sysml.scala:382:49") } // RegWrite(x8648,x8681,b2065)
    val x8728 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8728").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x8688 = withCtrl(x8728) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8688").srcCtx("sysml.scala:1104:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8689 = withCtrl(x8728) { CounterChain(List(x8688)).name("x8689").srcCtx("sysml.scala:1104:28") } // CounterChainNew(List(x8688))
    val x8707 = withCtrl(x8728) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8689).name("x8707").srcCtx("sysml.scala:1104:28") } // UnrolledForeach(List(b2064),x8689,Block(Const(())),List(List(b3185)),List(List(b3186)))
    val b3185 = withCtrl(x8707) { CounterIter(x8688, None).name("b3185") } // b3185
    val b3186 = withCtrl(x8707) { Const(true).name("b3186") } // b3186
    val x8690 = withCtrl(x8707) { OpDef(op=FixEql, inputs=List(b3185, Const(0))).name("x8690").srcCtx("sysml.scala:1106:15") } // FixEql(b3185,Const(0))
    val x8691 = withCtrl(x8707) { OpDef(op=FixEql, inputs=List(b3185, Const(1))).name("x8691").srcCtx("sysml.scala:1107:17") } // FixEql(b3185,Const(1))
    val x8692 = withCtrl(x8707) { OpDef(op=FixEql, inputs=List(b3185, Const(2))).name("x8692").srcCtx("sysml.scala:1108:19") } // FixEql(b3185,Const(2))
    val x8693 = withCtrl(x8707) { ReadMem(x8647).name("x8693").srcCtx("sysml.scala:382:49") } // RegRead(x8647)
    val x8694 = withCtrl(x8707) { ReadMem(x8645).name("x8694").srcCtx("sysml.scala:382:49") } // RegRead(x8645)
    val x8695 = withCtrl(x8707) { OpDef(op=MuxOp, inputs=List(x8692, x8694, x8693)).name("x8695").srcCtx("sysml.scala:1107:38") } // Mux(x8692,x8694,x8693)
    val x8696 = withCtrl(x8707) { ReadMem(x8643).name("x8696").srcCtx("sysml.scala:382:49") } // RegRead(x8643)
    val x8697 = withCtrl(x8707) { OpDef(op=MuxOp, inputs=List(x8691, x8696, x8695)).name("x8697").srcCtx("sysml.scala:1106:36") } // Mux(x8691,x8696,x8695)
    val x8698 = withCtrl(x8707) { ReadMem(x8641).name("x8698").srcCtx("sysml.scala:382:49") } // RegRead(x8641)
    val x8699 = withCtrl(x8707) { OpDef(op=MuxOp, inputs=List(x8690, x8698, x8697)).name("x8699").srcCtx("sysml.scala:1105:22:gateIndex") } // Mux(x8690,x8698,x8697)
    val x8700 = withCtrl(x8707) { OpDef(op=FltSub, inputs=List(x8699, Const(-8))).name("x8700").srcCtx("sysml.scala:1121:34") } // FltSub(x8699,Const(-8))
    val x8701 = withCtrl(x8707) { OpDef(op=FltMul, inputs=List(x8700, Const(64))).name("x8701").srcCtx("sysml.scala:1121:44") } // FltMul(x8700,Const(64))
    val x8702 = withCtrl(x8707) { OpDef(op=FltPtToFixPt, inputs=List(x8701, Const(true), Const(32), Const(0))).name("x8702").srcCtx("sysml.scala:1121:57") } // FltPtToFixPt(x8701,TRUE,_32,_0)
    val x8703 = withCtrl(x8707) { OpDef(op=FixMax, inputs=List(Const(0), x8702)).name("x8703").srcCtx("sysml.scala:1120:27") } // Max(Const(0),x8702)
    val x8704 = withCtrl(x8707) { OpDef(op=FixMin, inputs=List(Const(1023), x8703)).name("x8704").srcCtx("sysml.scala:1119:23:index") } // Min(Const(1023),x8703)
    val x8705 = withCtrl(x8707) { OpDef(op=BitAnd, inputs=List(b3186, b2064)).name("x8705").srcCtx("UnrollingBase.scala:28:66") } // And(b3186,b2064)
    val x8706_x8649 = withCtrl(x8707) { WriteMem(x8649, x8704).name("x8706_x8649").srcCtx("sysml.scala:1124:17") } // ParFIFOEnq(x8649,List(x8704),List(x8705))
    val x8708 = withCtrl(x8728) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8708").srcCtx("sysml.scala:1104:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8709 = withCtrl(x8728) { CounterChain(List(x8708)).name("x8709").srcCtx("sysml.scala:1104:28") } // CounterChainNew(List(x8708))
    val x8727 = withCtrl(x8728) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8709).name("x8727").srcCtx("sysml.scala:1104:28") } // UnrolledForeach(List(b2065),x8709,Block(Const(())),List(List(b3205)),List(List(b3206)))
    val b3205 = withCtrl(x8727) { CounterIter(x8708, None).name("b3205") } // b3205
    val b3206 = withCtrl(x8727) { Const(true).name("b3206") } // b3206
    val x8710 = withCtrl(x8727) { OpDef(op=FixEql, inputs=List(b3205, Const(0))).name("x8710").srcCtx("sysml.scala:1106:15") } // FixEql(b3205,Const(0))
    val x8711 = withCtrl(x8727) { OpDef(op=FixEql, inputs=List(b3205, Const(1))).name("x8711").srcCtx("sysml.scala:1107:17") } // FixEql(b3205,Const(1))
    val x8712 = withCtrl(x8727) { OpDef(op=FixEql, inputs=List(b3205, Const(2))).name("x8712").srcCtx("sysml.scala:1108:19") } // FixEql(b3205,Const(2))
    val x8713 = withCtrl(x8727) { ReadMem(x8648).name("x8713").srcCtx("sysml.scala:382:49") } // RegRead(x8648)
    val x8714 = withCtrl(x8727) { ReadMem(x8646).name("x8714").srcCtx("sysml.scala:382:49") } // RegRead(x8646)
    val x8715 = withCtrl(x8727) { OpDef(op=MuxOp, inputs=List(x8712, x8714, x8713)).name("x8715").srcCtx("sysml.scala:1107:38") } // Mux(x8712,x8714,x8713)
    val x8716 = withCtrl(x8727) { ReadMem(x8644).name("x8716").srcCtx("sysml.scala:382:49") } // RegRead(x8644)
    val x8717 = withCtrl(x8727) { OpDef(op=MuxOp, inputs=List(x8711, x8716, x8715)).name("x8717").srcCtx("sysml.scala:1106:36") } // Mux(x8711,x8716,x8715)
    val x8718 = withCtrl(x8727) { ReadMem(x8642).name("x8718").srcCtx("sysml.scala:382:49") } // RegRead(x8642)
    val x8719 = withCtrl(x8727) { OpDef(op=MuxOp, inputs=List(x8710, x8718, x8717)).name("x8719").srcCtx("sysml.scala:1105:22:gateIndex") } // Mux(x8710,x8718,x8717)
    val x8720 = withCtrl(x8727) { OpDef(op=FltSub, inputs=List(x8719, Const(-8))).name("x8720").srcCtx("sysml.scala:1121:34") } // FltSub(x8719,Const(-8))
    val x8721 = withCtrl(x8727) { OpDef(op=FltMul, inputs=List(x8720, Const(64))).name("x8721").srcCtx("sysml.scala:1121:44") } // FltMul(x8720,Const(64))
    val x8722 = withCtrl(x8727) { OpDef(op=FltPtToFixPt, inputs=List(x8721, Const(true), Const(32), Const(0))).name("x8722").srcCtx("sysml.scala:1121:57") } // FltPtToFixPt(x8721,TRUE,_32,_0)
    val x8723 = withCtrl(x8727) { OpDef(op=FixMax, inputs=List(Const(0), x8722)).name("x8723").srcCtx("sysml.scala:1120:27") } // Max(Const(0),x8722)
    val x8724 = withCtrl(x8727) { OpDef(op=FixMin, inputs=List(Const(1023), x8723)).name("x8724").srcCtx("sysml.scala:1119:23:index") } // Min(Const(1023),x8723)
    val x8725 = withCtrl(x8727) { OpDef(op=BitAnd, inputs=List(b3206, b2065)).name("x8725").srcCtx("UnrollingBase.scala:28:66") } // And(b3206,b2065)
    val x8726_x8650 = withCtrl(x8727) { WriteMem(x8650, x8724).name("x8726_x8650").srcCtx("sysml.scala:1124:17") } // ParFIFOEnq(x8650,List(x8724),List(x8725))
    val x8785 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8785").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x8729 = withCtrl(x8785) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8729").srcCtx("sysml.scala:1128:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8730 = withCtrl(x8785) { CounterChain(List(x8729)).name("x8730").srcCtx("sysml.scala:1128:28") } // CounterChainNew(List(x8729))
    val x8756 = withCtrl(x8785) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8730).name("x8756").srcCtx("sysml.scala:1128:28") } // UnrolledForeach(List(b2064),x8730,Block(Const(())),List(List(b3230)),List(List(b3231)))
    val b3230 = withCtrl(x8756) { CounterIter(x8729, None).name("b3230") } // b3230
    val b3231 = withCtrl(x8756) { Const(true).name("b3231") } // b3231
    val x8731 = withCtrl(x8756) { OpDef(op=BitAnd, inputs=List(b3231, b2064)).name("x8731").srcCtx("UnrollingBase.scala:28:66") } // And(b3231,b2064)
    val x8732 = withCtrl(x8756) { ReadMem(x8649).name("x8732").srcCtx("sysml.scala:1130:29:index") } // ParFIFODeq(x8649,List(x8731))
    val x8733 = withCtrl(x8756) { x8732 } // VectorApply(x8732,0)
    val x8734 = withCtrl(x8756) { LoadBanks(List(x7729_d0_b0), List(x8733)).name("x8734").srcCtx("sysml.scala:1133:27:sigEle") } // LUTLoad(x7729,List(x8733),x8731)
    val x8735 = withCtrl(x8756) { LoadBanks(List(x7730_d2_b0), List(x8733)).name("x8735").srcCtx("sysml.scala:1134:29:tanhEle") } // LUTLoad(x7730,List(x8733),x8731)
    val x8736 = withCtrl(x8756) { OpDef(op=FixEql, inputs=List(b3230, Const(1))).name("x8736").srcCtx("sysml.scala:1148:15") } // FixEql(b3230,Const(1))
    val x8737 = withCtrl(x8756) { OpDef(op=MuxOp, inputs=List(x8736, x8735, x8734)).name("x8737").srcCtx("sysml.scala:1147:24:actEle") } // Mux(x8736,x8735,x8734)
    val x8738_x8633 = withCtrl(x8756) { WriteMem(x8633, x8737).name("x8738_x8633").srcCtx("sysml.scala:1152:13") } // RegWrite(x8633,x8737,x8731)
    val x8739_x8635 = withCtrl(x8756) { WriteMem(x8635, x8737).name("x8739_x8635").srcCtx("sysml.scala:1153:13") } // RegWrite(x8635,x8737,x8731)
    val x8740_x8637 = withCtrl(x8756) { WriteMem(x8637, x8737).name("x8740_x8637").srcCtx("sysml.scala:1154:13") } // RegWrite(x8637,x8737,x8731)
    val x8741_x8639 = withCtrl(x8756) { WriteMem(x8639, x8737).name("x8741_x8639").srcCtx("sysml.scala:1155:13") } // RegWrite(x8639,x8737,x8731)
    val x8742 = withCtrl(x8756) { ReadMem(x8633).name("x8742").srcCtx("sysml.scala:1157:22:si") } // RegRead(x8633)
    antiDepsOf(x8742)=List(x8738_x8633)
    val x8743 = withCtrl(x8756) { ReadMem(x8635).name("x8743").srcCtx("sysml.scala:1158:22:tj") } // RegRead(x8635)
    antiDepsOf(x8743)=List(x8739_x8635)
    val x8744 = withCtrl(x8756) { ReadMem(x8637).name("x8744").srcCtx("sysml.scala:1159:22:sf") } // RegRead(x8637)
    antiDepsOf(x8744)=List(x8740_x8637)
    val x8745 = withCtrl(x8756) { OpDef(op=FltMul, inputs=List(x8742, x8743)).name("x8745").srcCtx("sysml.scala:1161:23:sitjEM") } // FltMul(x8742,x8743)
    val x8746 = withCtrl(x8756) { LoadBanks(List(x7726_d0_b0), List(b2062)).name("x8746").srcCtx("sysml.scala:1162:26") } // LUTLoad(x7726,List(b2062),x8731)
    val x8747 = withCtrl(x8756) { OpDef(op=FltMul, inputs=List(x8746, x8744)).name("x8747").srcCtx("sysml.scala:1162:40:ctsfEM") } // FltMul(x8746,x8744)
    val x8748 = withCtrl(x8756) { OpDef(op=FltAdd, inputs=List(x8745, x8747)).name("x8748").srcCtx("sysml.scala:1163:25:cNew") } // FltAdd(x8745,x8747)
    val x8749 = withCtrl(x8756) { StoreBanks(List(List(x7727_d0_b0)), List(b2062), x8748).name("x8749").srcCtx("sysml.scala:1164:23") } // SRAMStore(x7727,ArrayBuffer(Const(512)),List(b2062),Const(0),x8748,x8731)
    val x8750 = withCtrl(x8756) { OpDef(op=FltSub, inputs=List(x8748, Const(-8))).name("x8750").srcCtx("sysml.scala:1168:29") } // FltSub(x8748,Const(-8))
    val x8751 = withCtrl(x8756) { OpDef(op=FltMul, inputs=List(x8750, Const(64))).name("x8751").srcCtx("sysml.scala:1168:43") } // FltMul(x8750,Const(64))
    val x8752 = withCtrl(x8756) { OpDef(op=FltPtToFixPt, inputs=List(x8751, Const(true), Const(32), Const(0))).name("x8752").srcCtx("sysml.scala:1168:57") } // FltPtToFixPt(x8751,TRUE,_32,_0)
    val x8753 = withCtrl(x8756) { OpDef(op=FixMax, inputs=List(Const(0), x8752)).name("x8753").srcCtx("sysml.scala:1167:27") } // Max(Const(0),x8752)
    val x8754 = withCtrl(x8756) { OpDef(op=FixMin, inputs=List(Const(1023), x8753)).name("x8754").srcCtx("sysml.scala:1166:24:cIndex") } // Min(Const(1023),x8753)
    val x8755_x8651 = withCtrl(x8756) { WriteMem(x8651, x8754).name("x8755_x8651").srcCtx("sysml.scala:1172:19") } // ParFIFOEnq(x8651,List(x8754),List(x8731))
    def split4 = {
    val x8757 = withCtrl(x8785) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8757").srcCtx("sysml.scala:1128:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8758 = withCtrl(x8785) { CounterChain(List(x8757)).name("x8758").srcCtx("sysml.scala:1128:28") } // CounterChainNew(List(x8757))
    val x8784 = withCtrl(x8785) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8758).name("x8784").srcCtx("sysml.scala:1128:28") } // UnrolledForeach(List(b2065),x8758,Block(Const(())),List(List(b3258)),List(List(b3259)))
    val b3258 = withCtrl(x8784) { CounterIter(x8757, None).name("b3258") } // b3258
    val b3259 = withCtrl(x8784) { Const(true).name("b3259") } // b3259
    val x8759 = withCtrl(x8784) { OpDef(op=BitAnd, inputs=List(b3259, b2065)).name("x8759").srcCtx("UnrollingBase.scala:28:66") } // And(b3259,b2065)
    val x8760 = withCtrl(x8784) { ReadMem(x8650).name("x8760").srcCtx("sysml.scala:1130:29:index") } // ParFIFODeq(x8650,List(x8759))
    val x8761 = withCtrl(x8784) { x8760 } // VectorApply(x8760,0)
    val x8762 = withCtrl(x8784) { LoadBanks(List(x7729_d1_b0), List(x8761)).name("x8762").srcCtx("sysml.scala:1133:27:sigEle") } // LUTLoad(x7729,List(x8761),x8759)
    val x8763 = withCtrl(x8784) { LoadBanks(List(x7730_d3_b0), List(x8761)).name("x8763").srcCtx("sysml.scala:1134:29:tanhEle") } // LUTLoad(x7730,List(x8761),x8759)
    val x8764 = withCtrl(x8784) { OpDef(op=FixEql, inputs=List(b3258, Const(1))).name("x8764").srcCtx("sysml.scala:1148:15") } // FixEql(b3258,Const(1))
    val x8765 = withCtrl(x8784) { OpDef(op=MuxOp, inputs=List(x8764, x8763, x8762)).name("x8765").srcCtx("sysml.scala:1147:24:actEle") } // Mux(x8764,x8763,x8762)
    val x8766_x8634 = withCtrl(x8784) { WriteMem(x8634, x8765).name("x8766_x8634").srcCtx("sysml.scala:1152:13") } // RegWrite(x8634,x8765,x8759)
    val x8767_x8636 = withCtrl(x8784) { WriteMem(x8636, x8765).name("x8767_x8636").srcCtx("sysml.scala:1153:13") } // RegWrite(x8636,x8765,x8759)
    val x8768_x8638 = withCtrl(x8784) { WriteMem(x8638, x8765).name("x8768_x8638").srcCtx("sysml.scala:1154:13") } // RegWrite(x8638,x8765,x8759)
    val x8769_x8640 = withCtrl(x8784) { WriteMem(x8640, x8765).name("x8769_x8640").srcCtx("sysml.scala:1155:13") } // RegWrite(x8640,x8765,x8759)
    val x8770 = withCtrl(x8784) { ReadMem(x8634).name("x8770").srcCtx("sysml.scala:1157:22:si") } // RegRead(x8634)
    antiDepsOf(x8770)=List(x8766_x8634)
    val x8771 = withCtrl(x8784) { ReadMem(x8636).name("x8771").srcCtx("sysml.scala:1158:22:tj") } // RegRead(x8636)
    antiDepsOf(x8771)=List(x8767_x8636)
    val x8772 = withCtrl(x8784) { ReadMem(x8638).name("x8772").srcCtx("sysml.scala:1159:22:sf") } // RegRead(x8638)
    antiDepsOf(x8772)=List(x8768_x8638)
    val x8773 = withCtrl(x8784) { OpDef(op=FltMul, inputs=List(x8770, x8771)).name("x8773").srcCtx("sysml.scala:1161:23:sitjEM") } // FltMul(x8770,x8771)
    val x8774 = withCtrl(x8784) { LoadBanks(List(x7726_d0_b0), List(b2063)).name("x8774").srcCtx("sysml.scala:1162:26") } // LUTLoad(x7726,List(b2063),x8759)
    val x8775 = withCtrl(x8784) { OpDef(op=FltMul, inputs=List(x8774, x8772)).name("x8775").srcCtx("sysml.scala:1162:40:ctsfEM") } // FltMul(x8774,x8772)
    val x8776 = withCtrl(x8784) { OpDef(op=FltAdd, inputs=List(x8773, x8775)).name("x8776").srcCtx("sysml.scala:1163:25:cNew") } // FltAdd(x8773,x8775)
    val x8777 = withCtrl(x8784) { StoreBanks(List(List(x7727_d0_b0)), List(b2063), x8776).name("x8777").srcCtx("sysml.scala:1164:23") } // SRAMStore(x7727,ArrayBuffer(Const(512)),List(b2063),Const(0),x8776,x8759)
    val x8778 = withCtrl(x8784) { OpDef(op=FltSub, inputs=List(x8776, Const(-8))).name("x8778").srcCtx("sysml.scala:1168:29") } // FltSub(x8776,Const(-8))
    val x8779 = withCtrl(x8784) { OpDef(op=FltMul, inputs=List(x8778, Const(64))).name("x8779").srcCtx("sysml.scala:1168:43") } // FltMul(x8778,Const(64))
    val x8780 = withCtrl(x8784) { OpDef(op=FltPtToFixPt, inputs=List(x8779, Const(true), Const(32), Const(0))).name("x8780").srcCtx("sysml.scala:1168:57") } // FltPtToFixPt(x8779,TRUE,_32,_0)
    val x8781 = withCtrl(x8784) { OpDef(op=FixMax, inputs=List(Const(0), x8780)).name("x8781").srcCtx("sysml.scala:1167:27") } // Max(Const(0),x8780)
    val x8782 = withCtrl(x8784) { OpDef(op=FixMin, inputs=List(Const(1023), x8781)).name("x8782").srcCtx("sysml.scala:1166:24:cIndex") } // Min(Const(1023),x8781)
    val x8783_x8652 = withCtrl(x8784) { WriteMem(x8652, x8782).name("x8783_x8652").srcCtx("sysml.scala:1172:19") } // ParFIFOEnq(x8652,List(x8782),List(x8759))
    val x8806 = withCtrl(x8807) { UnitController(style=ForkJoin, level=OuterControl).name("x8806").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(Const(true)),Block(Const(())))
    val x8786 = withCtrl(x8806) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8786").srcCtx("sysml.scala:1175:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8787 = withCtrl(x8806) { CounterChain(List(x8786)).name("x8787").srcCtx("sysml.scala:1175:28") } // CounterChainNew(List(x8786))
    val x8795 = withCtrl(x8806) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8787).name("x8795").srcCtx("sysml.scala:1175:28") } // UnrolledForeach(List(b2064),x8787,Block(Const(())),List(List(b3291)),List(List(b3292)))
    val b3291 = withCtrl(x8795) { CounterIter(x8786, None).name("b3291") } // b3291
    val b3292 = withCtrl(x8795) { Const(true).name("b3292") } // b3292
    val x8788 = withCtrl(x8795) { ReadMem(x8639).name("x8788").srcCtx("sysml.scala:1176:22:so") } // RegRead(x8639)
    val x8789 = withCtrl(x8795) { OpDef(op=BitAnd, inputs=List(b3292, b2064)).name("x8789").srcCtx("UnrollingBase.scala:28:66") } // And(b3292,b2064)
    val x8790 = withCtrl(x8795) { ReadMem(x8651).name("x8790").srcCtx("sysml.scala:1178:31:cIndex") } // ParFIFODeq(x8651,List(x8789))
    val x8791 = withCtrl(x8795) { x8790 } // VectorApply(x8790,0)
    val x8792 = withCtrl(x8795) { LoadBanks(List(x7730_d0_b0), List(x8791)).name("x8792").srcCtx("sysml.scala:1179:27:cTanh") } // LUTLoad(x7730,List(x8791),x8789)
    val x8793 = withCtrl(x8795) { OpDef(op=FltMul, inputs=List(x8792, x8788)).name("x8793").srcCtx("sysml.scala:1181:24:hNew") } // FltMul(x8792,x8788)
    val x8794 = withCtrl(x8795) { StoreBanks(List(List(x7728_d0_b0)), List(b2062), x8793).name("x8794").srcCtx("sysml.scala:1182:23") } // SRAMStore(x7728,ArrayBuffer(Const(512)),List(b2062),Const(0),x8793,x8789)
    val x8796 = withCtrl(x8806) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x8796").srcCtx("sysml.scala:1175:21") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x8797 = withCtrl(x8806) { CounterChain(List(x8796)).name("x8797").srcCtx("sysml.scala:1175:28") } // CounterChainNew(List(x8796))
    val x8805 = withCtrl(x8806) { LoopController(style=InnerPipe, level=InnerControl, cchain=x8797).name("x8805").srcCtx("sysml.scala:1175:28") } // UnrolledForeach(List(b2065),x8797,Block(Const(())),List(List(b3301)),List(List(b3302)))
    val b3301 = withCtrl(x8805) { CounterIter(x8796, None).name("b3301") } // b3301
    val b3302 = withCtrl(x8805) { Const(true).name("b3302") } // b3302
    val x8798 = withCtrl(x8805) { ReadMem(x8640).name("x8798").srcCtx("sysml.scala:1176:22:so") } // RegRead(x8640)
    val x8799 = withCtrl(x8805) { OpDef(op=BitAnd, inputs=List(b3302, b2065)).name("x8799").srcCtx("UnrollingBase.scala:28:66") } // And(b3302,b2065)
    val x8800 = withCtrl(x8805) { ReadMem(x8652).name("x8800").srcCtx("sysml.scala:1178:31:cIndex") } // ParFIFODeq(x8652,List(x8799))
    val x8801 = withCtrl(x8805) { x8800 } // VectorApply(x8800,0)
    val x8802 = withCtrl(x8805) { LoadBanks(List(x7730_d1_b0), List(x8801)).name("x8802").srcCtx("sysml.scala:1179:27:cTanh") } // LUTLoad(x7730,List(x8801),x8799)
    val x8803 = withCtrl(x8805) { OpDef(op=FltMul, inputs=List(x8802, x8798)).name("x8803").srcCtx("sysml.scala:1181:24:hNew") } // FltMul(x8802,x8798)
    val x8804 = withCtrl(x8805) { StoreBanks(List(List(x7728_d0_b0)), List(b2063), x8803).name("x8804").srcCtx("sysml.scala:1182:23") } // SRAMStore(x7728,ArrayBuffer(Const(512)),List(b2063),Const(0),x8803,x8799)
    val x8812 = withCtrl(x8813) { UnitController(style=SeqPipe, level=InnerControl).name("x8812").srcCtx("sysml.scala:362:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x8808 = withCtrl(x8812) { LoadBanks(List(x7727_d0_b0), List(Const(0))).name("x8808").srcCtx("sysml.scala:404:17") } // SRAMLoad(x7727,ArrayBuffer(Const(512)),List(Const(0)),Const(0),Const(true))
    val x8809_x7715 = withCtrl(x8812) { WriteMem(x7715, x8808).name("x8809_x7715").srcCtx("sysml.scala:404:12") } // RegWrite(x7715,x8808,Const(true))
    val x8810 = withCtrl(x8812) { LoadBanks(List(x7728_d0_b0), List(Const(0))).name("x8810").srcCtx("sysml.scala:405:17") } // SRAMLoad(x7728,ArrayBuffer(Const(512)),List(Const(0)),Const(0),Const(true))
    val x8811_x7716 = withCtrl(x8812) { WriteMem(x7716, x8810).name("x8811_x7716").srcCtx("sysml.scala:405:12") } // RegWrite(x7716,x8810,Const(true))
    }; split4
    }; split3
    }; split2
    }; split1
    
  }
}
