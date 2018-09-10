import pir._
import pir.node._
import arch._
import prism.enums._

object DBLSTM_256_1_1_8 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4181 = withCtrl(design.top.topController) { DRAM(dims=List(Const(256))).name("x4181").srcCtx("DeepBenchLSTM256.scala:57:23:dout") } // x4181 = DRAMNew(ArrayBuffer(Const(256)),Const(0))
    val x4600 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x4600").srcCtx("DeepBenchLSTM256.scala:59:11") } // Hwblock(Block(Const(())),false)
    val x4182_d0_b0 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b0").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b0) = false
    bufferDepthOf(x4182_d0_b0) = 1
    staticDimsOf(x4182_d0_b0) = List(256, 4, 256)
    val x4182_d0_b1 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b1").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b1) = false
    bufferDepthOf(x4182_d0_b1) = 1
    staticDimsOf(x4182_d0_b1) = List(256, 4, 256)
    val x4182_d0_b2 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b2").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b2) = false
    bufferDepthOf(x4182_d0_b2) = 1
    staticDimsOf(x4182_d0_b2) = List(256, 4, 256)
    val x4182_d0_b3 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b3").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b3) = false
    bufferDepthOf(x4182_d0_b3) = 1
    staticDimsOf(x4182_d0_b3) = List(256, 4, 256)
    val x4182_d0_b4 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b4").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b4) = false
    bufferDepthOf(x4182_d0_b4) = 1
    staticDimsOf(x4182_d0_b4) = List(256, 4, 256)
    val x4182_d0_b5 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b5").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b5) = false
    bufferDepthOf(x4182_d0_b5) = 1
    staticDimsOf(x4182_d0_b5) = List(256, 4, 256)
    val x4182_d0_b6 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b6").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b6) = false
    bufferDepthOf(x4182_d0_b6) = 1
    staticDimsOf(x4182_d0_b6) = List(256, 4, 256)
    val x4182_d0_b7 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4182_d0_b7").srcCtx("DataGenerator.scala:248:40:wh") } // x4182 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x4182_d0_b7) = false
    bufferDepthOf(x4182_d0_b7) = 1
    staticDimsOf(x4182_d0_b7) = List(256, 4, 256)
    val x4183_d0_b0 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4183_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt0") } // x4183 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x4183_d0_b0) = false
    bufferDepthOf(x4183_d0_b0) = 1
    staticDimsOf(x4183_d0_b0) = List(64, 4, 256)
    val x4184_d0_b0 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4184_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt1") } // x4184 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x4184_d0_b0) = false
    bufferDepthOf(x4184_d0_b0) = 1
    staticDimsOf(x4184_d0_b0) = List(64, 4, 256)
    val x4185_d0_b0 = withCtrl(x4600) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x4185_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt2") } // x4185 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x4185_d0_b0) = false
    bufferDepthOf(x4185_d0_b0) = 1
    staticDimsOf(x4185_d0_b0) = List(64, 4, 256)
    val x4186_d0_b0 = withCtrl(x4600) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x4186_d0_b0").srcCtx("DeepBenchLSTM256.scala:86:25:cBuf") } // x4186 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x4186_d0_b0) = false
    bufferDepthOf(x4186_d0_b0) = 1
    staticDimsOf(x4186_d0_b0) = List(256)
    val x4187_d0_b0 = withCtrl(x4600) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x4187_d0_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x4187 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x4187_d0_b0) = false
    bufferDepthOf(x4187_d0_b0) = 1
    staticDimsOf(x4187_d0_b0) = List(256)
    val x4187_d1_b0 = withCtrl(x4600) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x4187_d1_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x4187 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x4187_d1_b0) = false
    bufferDepthOf(x4187_d1_b0) = 1
    staticDimsOf(x4187_d1_b0) = List(256)
    val x4188 = withCtrl(x4600) { Counter(min=Const(0), max=Const(150), step=Const(1), par=1).name("x4188").srcCtx("DeepBenchLSTM256.scala:90:34") } // CounterNew(Const(0),Const(150),Const(1),Const(1))
    val x4189 = withCtrl(x4600) { CounterChain(List(x4188)).name("x4189").srcCtx("DeepBenchLSTM256.scala:90:40") } // CounterChainNew(List(x4188))
    val x4577 = withCtrl(x4600) { LoopController(style=SeqPipe, level=OuterControl, cchain=x4189).name("x4577").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnrolledForeach(List(Const(true)),x4189,Block(Const(())),List(List(b1968)),List(List(b1969)))
    val b1968 = withCtrl(x4577) { CounterIter(x4188, Some(0)).name("b1968") } // b1968
    val b1969 = withCtrl(x4577) { Const(true).name("b1969") } // b1969
    val x4190_d0_b0 = withCtrl(x4577) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4190_d0_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x4190 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4190_d0_b0) = false
    bufferDepthOf(x4190_d0_b0) = 1
    staticDimsOf(x4190_d0_b0) = List(4, 256)
    val x4190_d1_b0 = withCtrl(x4577) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4190_d1_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x4190 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4190_d1_b0) = true
    bufferDepthOf(x4190_d1_b0) = 1
    staticDimsOf(x4190_d1_b0) = List(4, 256)
    val x4191_d0_b0 = withCtrl(x4577) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4191_d0_b0").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x4191 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4191_d0_b0) = false
    bufferDepthOf(x4191_d0_b0) = 1
    staticDimsOf(x4191_d0_b0) = List(4, 256)
    val x4192_d0 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d0) = false
    bufferDepthOf(x4192_d0) = 1
    val x4192_d1 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d1) = false
    bufferDepthOf(x4192_d1) = 1
    val x4192_d2 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d2) = false
    bufferDepthOf(x4192_d2) = 1
    val x4192_d3 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d3) = false
    bufferDepthOf(x4192_d3) = 1
    val x4192_d4 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d4) = false
    bufferDepthOf(x4192_d4) = 1
    val x4192_d5 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d5").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d5) = false
    bufferDepthOf(x4192_d5) = 1
    val x4192_d6 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d6").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d6) = false
    bufferDepthOf(x4192_d6) = 1
    val x4192_d7 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d7").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d7) = false
    bufferDepthOf(x4192_d7) = 1
    val x4192_d8 = withCtrl(x4577) { Reg(init=Some(false)).name("x4192_d8").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4192 = RegNew(Const(false))
    isAccum(x4192_d8) = false
    bufferDepthOf(x4192_d8) = 1
    val x4193 = withCtrl(x4577) { Reg(init=Some(false)).name("x4193").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4193 = RegNew(Const(false))
    isAccum(x4193) = false
    bufferDepthOf(x4193) = 1
    val x4194 = withCtrl(x4577) { Reg(init=Some(false)).name("x4194").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4194 = RegNew(Const(false))
    isAccum(x4194) = false
    bufferDepthOf(x4194) = 1
    val x4195 = withCtrl(x4577) { Reg(init=Some(0)).name("x4195").srcCtx("DeepBenchLSTM256.scala:90:40") } // x4195 = RegNew(Const(0))
    isAccum(x4195) = false
    bufferDepthOf(x4195) = 1
    val x4207 = withCtrl(x4577) { UnitController(style=SeqPipe, level=InnerControl).name("x4207").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnitPipe(List(b1969),Block(Const(())))
    val x4196 = withCtrl(x4207) { OpDef(op=FixEql, inputs=List(b1968, Const(0))).name("x4196").srcCtx("DeepBenchLSTM256.scala:93:33:isInitStep") } // FixEql(b1968,Const(0))
    val x4197 = withCtrl(x4207) { OpDef(op=FixLt, inputs=List(b1968, Const(64))).name("x4197").srcCtx("VecMatMultBiasAdd.scala:154:28:isFstRange") } // FixLt(b1968,Const(64))
    val x4198 = withCtrl(x4207) { OpDef(op=FixLt, inputs=List(Const(64), b1968)).name("x4198").srcCtx("VecMatMultBiasAdd.scala:155:28:isLstRange") } // FixLt(Const(64),b1968)
    val x4199 = withCtrl(x4207) { OpDef(op=FixSub, inputs=List(b1968, Const(64))).name("x4199").srcCtx("VecMatMultBiasAdd.scala:157:24:midIdx") } // FixSub(b1968,Const(64))
    val x4200 = withCtrl(x4207) { OpDef(op=FixSub, inputs=List(b1968, Const(128))).name("x4200").srcCtx("VecMatMultBiasAdd.scala:158:24:lstIdx") } // FixSub(b1968,Const(128))
    val x4201 = withCtrl(x4207) { OpDef(op=MuxOp, inputs=List(x4198, x4200, x4199)).name("x4201").srcCtx("VecMatMultBiasAdd.scala:160:30") } // Mux(x4198,x4200,x4199)
    val x4202 = withCtrl(x4207) { OpDef(op=MuxOp, inputs=List(x4197, b1968, x4201)).name("x4202").srcCtx("VecMatMultBiasAdd.scala:159:25:iTileStep") } // Mux(x4197,b1968,x4201)
    val x4203_x4192_d0 = withCtrl(x4207) { WriteMem(x4192_d0, x4196).name("x4203_x4192_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d5 = withCtrl(x4207) { WriteMem(x4192_d5, x4196).name("x4203_x4192_d5").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d1 = withCtrl(x4207) { WriteMem(x4192_d1, x4196).name("x4203_x4192_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d6 = withCtrl(x4207) { WriteMem(x4192_d6, x4196).name("x4203_x4192_d6").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d2 = withCtrl(x4207) { WriteMem(x4192_d2, x4196).name("x4203_x4192_d2").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d7 = withCtrl(x4207) { WriteMem(x4192_d7, x4196).name("x4203_x4192_d7").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d3 = withCtrl(x4207) { WriteMem(x4192_d3, x4196).name("x4203_x4192_d3").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d8 = withCtrl(x4207) { WriteMem(x4192_d8, x4196).name("x4203_x4192_d8").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4203_x4192_d4 = withCtrl(x4207) { WriteMem(x4192_d4, x4196).name("x4203_x4192_d4").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4192,x4196,b1969)
    val x4204_x4193 = withCtrl(x4207) { WriteMem(x4193, x4197).name("x4204_x4193").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4193,x4197,b1969)
    val x4205_x4194 = withCtrl(x4207) { WriteMem(x4194, x4198).name("x4205_x4194").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4194,x4198,b1969)
    val x4206_x4195 = withCtrl(x4207) { WriteMem(x4195, x4202).name("x4206_x4195").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x4195,x4202,b1969)
    val x4208 = withCtrl(x4577) { Counter(min=Const(0), max=Const(256), step=Const(1), par=8).name("x4208").srcCtx("VecMatMultBiasAdd.scala:165:59") } // CounterNew(Const(0),Const(256),Const(1),Const(8))
    val x4209 = withCtrl(x4577) { CounterChain(List(x4208)).name("x4209").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(List(x4208))
    val x4445 = withCtrl(x4577) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4209).name("x4445").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledReduce(List(b1969),x4209,x4190,Block((x4190) => Const(())),List(List(b1993, b1994, b1995, b1996, b1997, b1998, b1999, b2000)),List(List(b2001, b2002, b2003, b2004, b2005, b2006, b2007, b2008)))
    val b1993 = withCtrl(x4445) { CounterIter(x4208, Some(0)).name("b1993") } // b1993
    val b2001 = withCtrl(x4445) { Const(true).name("b2001") } // b2001
    val b1994 = withCtrl(x4445) { CounterIter(x4208, Some(1)).name("b1994") } // b1994
    val b2002 = withCtrl(x4445) { Const(true).name("b2002") } // b2002
    val b1995 = withCtrl(x4445) { CounterIter(x4208, Some(2)).name("b1995") } // b1995
    val b2003 = withCtrl(x4445) { Const(true).name("b2003") } // b2003
    val b1996 = withCtrl(x4445) { CounterIter(x4208, Some(3)).name("b1996") } // b1996
    val b2004 = withCtrl(x4445) { Const(true).name("b2004") } // b2004
    val b1997 = withCtrl(x4445) { CounterIter(x4208, Some(4)).name("b1997") } // b1997
    val b2005 = withCtrl(x4445) { Const(true).name("b2005") } // b2005
    val b1998 = withCtrl(x4445) { CounterIter(x4208, Some(5)).name("b1998") } // b1998
    val b2006 = withCtrl(x4445) { Const(true).name("b2006") } // b2006
    val b1999 = withCtrl(x4445) { CounterIter(x4208, Some(6)).name("b1999") } // b1999
    val b2007 = withCtrl(x4445) { Const(true).name("b2007") } // b2007
    val b2000 = withCtrl(x4445) { CounterIter(x4208, Some(7)).name("b2000") } // b2000
    val b2008 = withCtrl(x4445) { Const(true).name("b2008") } // b2008
    val x4210_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4210_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4210 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4210_d0_b0) = false
    bufferDepthOf(x4210_d0_b0) = 2
    staticDimsOf(x4210_d0_b0) = List(4, 256)
    val x4211_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4211_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4211 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4211_d0_b0) = false
    bufferDepthOf(x4211_d0_b0) = 2
    staticDimsOf(x4211_d0_b0) = List(4, 256)
    val x4212_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4212_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4212 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4212_d0_b0) = false
    bufferDepthOf(x4212_d0_b0) = 2
    staticDimsOf(x4212_d0_b0) = List(4, 256)
    val x4213_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4213_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4213 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4213_d0_b0) = false
    bufferDepthOf(x4213_d0_b0) = 2
    staticDimsOf(x4213_d0_b0) = List(4, 256)
    val x4214_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4214_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4214 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4214_d0_b0) = false
    bufferDepthOf(x4214_d0_b0) = 2
    staticDimsOf(x4214_d0_b0) = List(4, 256)
    val x4215_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4215_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4215 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4215_d0_b0) = false
    bufferDepthOf(x4215_d0_b0) = 2
    staticDimsOf(x4215_d0_b0) = List(4, 256)
    val x4216_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4216_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4216 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4216_d0_b0) = false
    bufferDepthOf(x4216_d0_b0) = 2
    staticDimsOf(x4216_d0_b0) = List(4, 256)
    val x4217_d0_b0 = withCtrl(x4445) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x4217_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x4217 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x4217_d0_b0) = false
    bufferDepthOf(x4217_d0_b0) = 2
    staticDimsOf(x4217_d0_b0) = List(4, 256)
    val x4218 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4218").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4218 = RegNew(Const(0))
    isAccum(x4218) = false
    bufferDepthOf(x4218) = 2
    val x4219 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4219").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4219 = RegNew(Const(0))
    isAccum(x4219) = false
    bufferDepthOf(x4219) = 2
    val x4220 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4220").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4220 = RegNew(Const(0))
    isAccum(x4220) = false
    bufferDepthOf(x4220) = 2
    val x4221 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4221").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4221 = RegNew(Const(0))
    isAccum(x4221) = false
    bufferDepthOf(x4221) = 2
    val x4222 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4222").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4222 = RegNew(Const(0))
    isAccum(x4222) = false
    bufferDepthOf(x4222) = 2
    val x4223 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4223").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4223 = RegNew(Const(0))
    isAccum(x4223) = false
    bufferDepthOf(x4223) = 2
    val x4224 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4224").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4224 = RegNew(Const(0))
    isAccum(x4224) = false
    bufferDepthOf(x4224) = 2
    val x4225 = withCtrl(x4445) { Reg(init=Some(0.0)).name("x4225").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x4225 = RegNew(Const(0))
    isAccum(x4225) = false
    bufferDepthOf(x4225) = 2
    val x4274 = withCtrl(x4445) { UnitController(style=ForkJoin, level=OuterControl).name("x4274").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x4231 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4231").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2001, b1969),Block(Const(())))
    val x4226 = withCtrl(x4231) { OpDef(op=BitAnd, inputs=List(b2001, b1969)).name("x4226").srcCtx("UnrollingBase.scala:28:66") } // And(b2001,b1969)
    val x4227 = withCtrl(x4231) { LoadBanks(List(x4187_d1_b0), List(b1993)).name("x4227").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1993),Const(0),x4226)
    val x4228 = withCtrl(x4231) { ReadMem(x4192_d1).name("x4228").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4229 = withCtrl(x4231) { OpDef(op=MuxOp, inputs=List(x4228, Const(0.0), x4227)).name("x4229").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4228,Const(0),x4227)
    val x4230_x4218 = withCtrl(x4231) { WriteMem(x4218, x4229).name("x4230_x4218").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4218,x4229,x4226)
    val x4237 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4237").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2002, b1969),Block(Const(())))
    val x4232 = withCtrl(x4237) { OpDef(op=BitAnd, inputs=List(b2002, b1969)).name("x4232").srcCtx("UnrollingBase.scala:28:66") } // And(b2002,b1969)
    val x4233 = withCtrl(x4237) { LoadBanks(List(x4187_d1_b0), List(b1994)).name("x4233").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1994),Const(0),x4232)
    val x4234 = withCtrl(x4237) { ReadMem(x4192_d2).name("x4234").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4235 = withCtrl(x4237) { OpDef(op=MuxOp, inputs=List(x4234, Const(0.0), x4233)).name("x4235").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4234,Const(0),x4233)
    val x4236_x4219 = withCtrl(x4237) { WriteMem(x4219, x4235).name("x4236_x4219").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4219,x4235,x4232)
    val x4243 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4243").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2003, b1969),Block(Const(())))
    val x4238 = withCtrl(x4243) { OpDef(op=BitAnd, inputs=List(b2003, b1969)).name("x4238").srcCtx("UnrollingBase.scala:28:66") } // And(b2003,b1969)
    val x4239 = withCtrl(x4243) { LoadBanks(List(x4187_d1_b0), List(b1995)).name("x4239").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1995),Const(0),x4238)
    val x4240 = withCtrl(x4243) { ReadMem(x4192_d3).name("x4240").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4241 = withCtrl(x4243) { OpDef(op=MuxOp, inputs=List(x4240, Const(0.0), x4239)).name("x4241").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4240,Const(0),x4239)
    val x4242_x4220 = withCtrl(x4243) { WriteMem(x4220, x4241).name("x4242_x4220").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4220,x4241,x4238)
    val x4249 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4249").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2004, b1969),Block(Const(())))
    val x4244 = withCtrl(x4249) { OpDef(op=BitAnd, inputs=List(b2004, b1969)).name("x4244").srcCtx("UnrollingBase.scala:28:66") } // And(b2004,b1969)
    val x4245 = withCtrl(x4249) { LoadBanks(List(x4187_d1_b0), List(b1996)).name("x4245").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1996),Const(0),x4244)
    val x4246 = withCtrl(x4249) { ReadMem(x4192_d4).name("x4246").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4247 = withCtrl(x4249) { OpDef(op=MuxOp, inputs=List(x4246, Const(0.0), x4245)).name("x4247").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4246,Const(0),x4245)
    val x4248_x4221 = withCtrl(x4249) { WriteMem(x4221, x4247).name("x4248_x4221").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4221,x4247,x4244)
    val x4255 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4255").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2005, b1969),Block(Const(())))
    val x4250 = withCtrl(x4255) { OpDef(op=BitAnd, inputs=List(b2005, b1969)).name("x4250").srcCtx("UnrollingBase.scala:28:66") } // And(b2005,b1969)
    val x4251 = withCtrl(x4255) { LoadBanks(List(x4187_d1_b0), List(b1997)).name("x4251").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1997),Const(0),x4250)
    val x4252 = withCtrl(x4255) { ReadMem(x4192_d5).name("x4252").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4253 = withCtrl(x4255) { OpDef(op=MuxOp, inputs=List(x4252, Const(0.0), x4251)).name("x4253").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4252,Const(0),x4251)
    val x4254_x4222 = withCtrl(x4255) { WriteMem(x4222, x4253).name("x4254_x4222").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4222,x4253,x4250)
    val x4261 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4261").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2006, b1969),Block(Const(())))
    val x4256 = withCtrl(x4261) { OpDef(op=BitAnd, inputs=List(b2006, b1969)).name("x4256").srcCtx("UnrollingBase.scala:28:66") } // And(b2006,b1969)
    val x4257 = withCtrl(x4261) { LoadBanks(List(x4187_d1_b0), List(b1998)).name("x4257").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1998),Const(0),x4256)
    val x4258 = withCtrl(x4261) { ReadMem(x4192_d6).name("x4258").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4259 = withCtrl(x4261) { OpDef(op=MuxOp, inputs=List(x4258, Const(0.0), x4257)).name("x4259").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4258,Const(0),x4257)
    val x4260_x4223 = withCtrl(x4261) { WriteMem(x4223, x4259).name("x4260_x4223").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4223,x4259,x4256)
    val x4267 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4267").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2007, b1969),Block(Const(())))
    val x4262 = withCtrl(x4267) { OpDef(op=BitAnd, inputs=List(b2007, b1969)).name("x4262").srcCtx("UnrollingBase.scala:28:66") } // And(b2007,b1969)
    val x4263 = withCtrl(x4267) { LoadBanks(List(x4187_d1_b0), List(b1999)).name("x4263").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b1999),Const(0),x4262)
    val x4264 = withCtrl(x4267) { ReadMem(x4192_d7).name("x4264").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4265 = withCtrl(x4267) { OpDef(op=MuxOp, inputs=List(x4264, Const(0.0), x4263)).name("x4265").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4264,Const(0),x4263)
    val x4266_x4224 = withCtrl(x4267) { WriteMem(x4224, x4265).name("x4266_x4224").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4224,x4265,x4262)
    val x4273 = withCtrl(x4274) { UnitController(style=SeqPipe, level=InnerControl).name("x4273").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b2008, b1969),Block(Const(())))
    val x4268 = withCtrl(x4273) { OpDef(op=BitAnd, inputs=List(b2008, b1969)).name("x4268").srcCtx("UnrollingBase.scala:28:66") } // And(b2008,b1969)
    val x4269 = withCtrl(x4273) { LoadBanks(List(x4187_d1_b0), List(b2000)).name("x4269").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x4187,ArrayBuffer(Const(256)),List(b2000),Const(0),x4268)
    val x4270 = withCtrl(x4273) { ReadMem(x4192_d8).name("x4270").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4271 = withCtrl(x4273) { OpDef(op=MuxOp, inputs=List(x4270, Const(0.0), x4269)).name("x4271").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x4270,Const(0),x4269)
    val x4272_x4225 = withCtrl(x4273) { WriteMem(x4225, x4271).name("x4272_x4225").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x4225,x4271,x4268)
    val x4379 = withCtrl(x4445) { UnitController(style=ForkJoin, level=OuterControl).name("x4379").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b1969),Block(Const(())))
    val x4275 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4275").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4276 = withCtrl(x4379) { CounterChain(List(x4275)).name("x4276").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4275))
    val x4287 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4276).name("x4287").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2001, b1969),x4276,Block(Const(())),List(List(b2090)),List(List(b2091)))
    val b2090 = withCtrl(x4287) { CounterIter(x4275, Some(0)).name("b2090") } // b2090
    val b2091 = withCtrl(x4287) { Const(true).name("b2091") } // b2091
    val x4277 = withCtrl(x4287) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4277").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4278 = withCtrl(x4287) { CounterChain(List(x4277)).name("x4278").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4277))
    val x4286 = withCtrl(x4287) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4278).name("x4286").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2091, b2001, b1969),x4278,Block(Const(())),List(List(b2094)),List(List(b2095)))
    val b2094 = withCtrl(x4286) { CounterIter(x4277, None).name("b2094") } // b2094
    val b2095 = withCtrl(x4286) { Const(true).name("b2095") } // b2095
    val x4279 = withCtrl(x4286) { OpDef(op=BitAnd, inputs=List(b2095, b2091)).name("x4279").srcCtx("UnrollingBase.scala:28:66") } // And(b2095,b2091)
    val x4280 = withCtrl(x4286) { OpDef(op=BitAnd, inputs=List(b2001, b1969)).name("x4280").srcCtx("UnrollingBase.scala:28:66") } // And(b2001,b1969)
    val x4281 = withCtrl(x4286) { OpDef(op=BitAnd, inputs=List(x4279, x4280)).name("x4281").srcCtx("UnrollingBase.scala:28:66") } // And(x4279,x4280)
    val x4282 = withCtrl(x4286) { LoadBanks(List(x4182_d0_b0), List(b1993, b2090, b2094)).name("x4282").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1993, b2090, b2094),x4281)
    val x4283 = withCtrl(x4286) { ReadMem(x4218).name("x4283").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4218)
    val x4284 = withCtrl(x4286) { OpDef(op=FixMul, inputs=List(x4282, x4283)).name("x4284").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4282,x4283)
    val x4285 = withCtrl(x4286) { StoreBanks(List(List(x4210_d0_b0)), List(b2090, b2094), x4284).name("x4285").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4210,List(List(b2090, b2094)),List(x4284),List(x4281))
    val x4288 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4288").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4289 = withCtrl(x4379) { CounterChain(List(x4288)).name("x4289").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4288))
    val x4300 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4289).name("x4300").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2002, b1969),x4289,Block(Const(())),List(List(b2105)),List(List(b2106)))
    val b2105 = withCtrl(x4300) { CounterIter(x4288, Some(0)).name("b2105") } // b2105
    val b2106 = withCtrl(x4300) { Const(true).name("b2106") } // b2106
    val x4290 = withCtrl(x4300) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4290").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4291 = withCtrl(x4300) { CounterChain(List(x4290)).name("x4291").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4290))
    val x4299 = withCtrl(x4300) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4291).name("x4299").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2106, b2002, b1969),x4291,Block(Const(())),List(List(b2109)),List(List(b2110)))
    val b2109 = withCtrl(x4299) { CounterIter(x4290, None).name("b2109") } // b2109
    val b2110 = withCtrl(x4299) { Const(true).name("b2110") } // b2110
    val x4292 = withCtrl(x4299) { OpDef(op=BitAnd, inputs=List(b2110, b2106)).name("x4292").srcCtx("UnrollingBase.scala:28:66") } // And(b2110,b2106)
    val x4293 = withCtrl(x4299) { OpDef(op=BitAnd, inputs=List(b2002, b1969)).name("x4293").srcCtx("UnrollingBase.scala:28:66") } // And(b2002,b1969)
    val x4294 = withCtrl(x4299) { OpDef(op=BitAnd, inputs=List(x4292, x4293)).name("x4294").srcCtx("UnrollingBase.scala:28:66") } // And(x4292,x4293)
    val x4295 = withCtrl(x4299) { LoadBanks(List(x4182_d0_b1), List(b1994, b2105, b2109)).name("x4295").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1994, b2105, b2109),x4294)
    val x4296 = withCtrl(x4299) { ReadMem(x4219).name("x4296").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4219)
    val x4297 = withCtrl(x4299) { OpDef(op=FixMul, inputs=List(x4295, x4296)).name("x4297").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4295,x4296)
    val x4298 = withCtrl(x4299) { StoreBanks(List(List(x4211_d0_b0)), List(b2105, b2109), x4297).name("x4298").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4211,List(List(b2105, b2109)),List(x4297),List(x4294))
    val x4301 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4301").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4302 = withCtrl(x4379) { CounterChain(List(x4301)).name("x4302").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4301))
    val x4313 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4302).name("x4313").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2003, b1969),x4302,Block(Const(())),List(List(b2120)),List(List(b2121)))
    val b2120 = withCtrl(x4313) { CounterIter(x4301, Some(0)).name("b2120") } // b2120
    val b2121 = withCtrl(x4313) { Const(true).name("b2121") } // b2121
    val x4303 = withCtrl(x4313) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4303").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4304 = withCtrl(x4313) { CounterChain(List(x4303)).name("x4304").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4303))
    val x4312 = withCtrl(x4313) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4304).name("x4312").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2121, b2003, b1969),x4304,Block(Const(())),List(List(b2124)),List(List(b2125)))
    val b2124 = withCtrl(x4312) { CounterIter(x4303, None).name("b2124") } // b2124
    val b2125 = withCtrl(x4312) { Const(true).name("b2125") } // b2125
    val x4305 = withCtrl(x4312) { OpDef(op=BitAnd, inputs=List(b2125, b2121)).name("x4305").srcCtx("UnrollingBase.scala:28:66") } // And(b2125,b2121)
    val x4306 = withCtrl(x4312) { OpDef(op=BitAnd, inputs=List(b2003, b1969)).name("x4306").srcCtx("UnrollingBase.scala:28:66") } // And(b2003,b1969)
    val x4307 = withCtrl(x4312) { OpDef(op=BitAnd, inputs=List(x4305, x4306)).name("x4307").srcCtx("UnrollingBase.scala:28:66") } // And(x4305,x4306)
    val x4308 = withCtrl(x4312) { LoadBanks(List(x4182_d0_b2), List(b1995, b2120, b2124)).name("x4308").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1995, b2120, b2124),x4307)
    val x4309 = withCtrl(x4312) { ReadMem(x4220).name("x4309").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4220)
    val x4310 = withCtrl(x4312) { OpDef(op=FixMul, inputs=List(x4308, x4309)).name("x4310").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4308,x4309)
    val x4311 = withCtrl(x4312) { StoreBanks(List(List(x4212_d0_b0)), List(b2120, b2124), x4310).name("x4311").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4212,List(List(b2120, b2124)),List(x4310),List(x4307))
    val x4314 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4314").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4315 = withCtrl(x4379) { CounterChain(List(x4314)).name("x4315").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4314))
    val x4326 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4315).name("x4326").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2004, b1969),x4315,Block(Const(())),List(List(b2135)),List(List(b2136)))
    val b2135 = withCtrl(x4326) { CounterIter(x4314, Some(0)).name("b2135") } // b2135
    val b2136 = withCtrl(x4326) { Const(true).name("b2136") } // b2136
    val x4316 = withCtrl(x4326) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4316").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4317 = withCtrl(x4326) { CounterChain(List(x4316)).name("x4317").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4316))
    val x4325 = withCtrl(x4326) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4317).name("x4325").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2136, b2004, b1969),x4317,Block(Const(())),List(List(b2139)),List(List(b2140)))
    val b2139 = withCtrl(x4325) { CounterIter(x4316, None).name("b2139") } // b2139
    val b2140 = withCtrl(x4325) { Const(true).name("b2140") } // b2140
    val x4318 = withCtrl(x4325) { OpDef(op=BitAnd, inputs=List(b2140, b2136)).name("x4318").srcCtx("UnrollingBase.scala:28:66") } // And(b2140,b2136)
    val x4319 = withCtrl(x4325) { OpDef(op=BitAnd, inputs=List(b2004, b1969)).name("x4319").srcCtx("UnrollingBase.scala:28:66") } // And(b2004,b1969)
    val x4320 = withCtrl(x4325) { OpDef(op=BitAnd, inputs=List(x4318, x4319)).name("x4320").srcCtx("UnrollingBase.scala:28:66") } // And(x4318,x4319)
    val x4321 = withCtrl(x4325) { LoadBanks(List(x4182_d0_b3), List(b1996, b2135, b2139)).name("x4321").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1996, b2135, b2139),x4320)
    val x4322 = withCtrl(x4325) { ReadMem(x4221).name("x4322").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4221)
    val x4323 = withCtrl(x4325) { OpDef(op=FixMul, inputs=List(x4321, x4322)).name("x4323").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4321,x4322)
    val x4324 = withCtrl(x4325) { StoreBanks(List(List(x4213_d0_b0)), List(b2135, b2139), x4323).name("x4324").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4213,List(List(b2135, b2139)),List(x4323),List(x4320))
    val x4327 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4327").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4328 = withCtrl(x4379) { CounterChain(List(x4327)).name("x4328").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4327))
    val x4339 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4328).name("x4339").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2005, b1969),x4328,Block(Const(())),List(List(b2150)),List(List(b2151)))
    val b2150 = withCtrl(x4339) { CounterIter(x4327, Some(0)).name("b2150") } // b2150
    val b2151 = withCtrl(x4339) { Const(true).name("b2151") } // b2151
    val x4329 = withCtrl(x4339) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4329").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4330 = withCtrl(x4339) { CounterChain(List(x4329)).name("x4330").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4329))
    val x4338 = withCtrl(x4339) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4330).name("x4338").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2151, b2005, b1969),x4330,Block(Const(())),List(List(b2154)),List(List(b2155)))
    val b2154 = withCtrl(x4338) { CounterIter(x4329, None).name("b2154") } // b2154
    val b2155 = withCtrl(x4338) { Const(true).name("b2155") } // b2155
    val x4331 = withCtrl(x4338) { OpDef(op=BitAnd, inputs=List(b2155, b2151)).name("x4331").srcCtx("UnrollingBase.scala:28:66") } // And(b2155,b2151)
    val x4332 = withCtrl(x4338) { OpDef(op=BitAnd, inputs=List(b2005, b1969)).name("x4332").srcCtx("UnrollingBase.scala:28:66") } // And(b2005,b1969)
    val x4333 = withCtrl(x4338) { OpDef(op=BitAnd, inputs=List(x4331, x4332)).name("x4333").srcCtx("UnrollingBase.scala:28:66") } // And(x4331,x4332)
    val x4334 = withCtrl(x4338) { LoadBanks(List(x4182_d0_b4), List(b1997, b2150, b2154)).name("x4334").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1997, b2150, b2154),x4333)
    val x4335 = withCtrl(x4338) { ReadMem(x4222).name("x4335").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4222)
    val x4336 = withCtrl(x4338) { OpDef(op=FixMul, inputs=List(x4334, x4335)).name("x4336").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4334,x4335)
    val x4337 = withCtrl(x4338) { StoreBanks(List(List(x4214_d0_b0)), List(b2150, b2154), x4336).name("x4337").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4214,List(List(b2150, b2154)),List(x4336),List(x4333))
    val x4340 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4340").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4341 = withCtrl(x4379) { CounterChain(List(x4340)).name("x4341").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4340))
    val x4352 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4341).name("x4352").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2006, b1969),x4341,Block(Const(())),List(List(b2165)),List(List(b2166)))
    val b2165 = withCtrl(x4352) { CounterIter(x4340, Some(0)).name("b2165") } // b2165
    val b2166 = withCtrl(x4352) { Const(true).name("b2166") } // b2166
    val x4342 = withCtrl(x4352) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4342").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4343 = withCtrl(x4352) { CounterChain(List(x4342)).name("x4343").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4342))
    val x4351 = withCtrl(x4352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4343).name("x4351").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2166, b2006, b1969),x4343,Block(Const(())),List(List(b2169)),List(List(b2170)))
    val b2169 = withCtrl(x4351) { CounterIter(x4342, None).name("b2169") } // b2169
    val b2170 = withCtrl(x4351) { Const(true).name("b2170") } // b2170
    val x4344 = withCtrl(x4351) { OpDef(op=BitAnd, inputs=List(b2170, b2166)).name("x4344").srcCtx("UnrollingBase.scala:28:66") } // And(b2170,b2166)
    val x4345 = withCtrl(x4351) { OpDef(op=BitAnd, inputs=List(b2006, b1969)).name("x4345").srcCtx("UnrollingBase.scala:28:66") } // And(b2006,b1969)
    val x4346 = withCtrl(x4351) { OpDef(op=BitAnd, inputs=List(x4344, x4345)).name("x4346").srcCtx("UnrollingBase.scala:28:66") } // And(x4344,x4345)
    val x4347 = withCtrl(x4351) { LoadBanks(List(x4182_d0_b5), List(b1998, b2165, b2169)).name("x4347").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1998, b2165, b2169),x4346)
    val x4348 = withCtrl(x4351) { ReadMem(x4223).name("x4348").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4223)
    val x4349 = withCtrl(x4351) { OpDef(op=FixMul, inputs=List(x4347, x4348)).name("x4349").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4347,x4348)
    val x4350 = withCtrl(x4351) { StoreBanks(List(List(x4215_d0_b0)), List(b2165, b2169), x4349).name("x4350").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4215,List(List(b2165, b2169)),List(x4349),List(x4346))
    val x4353 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4353").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4354 = withCtrl(x4379) { CounterChain(List(x4353)).name("x4354").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4353))
    val x4365 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4354).name("x4365").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2007, b1969),x4354,Block(Const(())),List(List(b2180)),List(List(b2181)))
    val b2180 = withCtrl(x4365) { CounterIter(x4353, Some(0)).name("b2180") } // b2180
    val b2181 = withCtrl(x4365) { Const(true).name("b2181") } // b2181
    val x4355 = withCtrl(x4365) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4355").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4356 = withCtrl(x4365) { CounterChain(List(x4355)).name("x4356").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4355))
    val x4364 = withCtrl(x4365) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4356).name("x4364").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2181, b2007, b1969),x4356,Block(Const(())),List(List(b2184)),List(List(b2185)))
    val b2184 = withCtrl(x4364) { CounterIter(x4355, None).name("b2184") } // b2184
    val b2185 = withCtrl(x4364) { Const(true).name("b2185") } // b2185
    val x4357 = withCtrl(x4364) { OpDef(op=BitAnd, inputs=List(b2185, b2181)).name("x4357").srcCtx("UnrollingBase.scala:28:66") } // And(b2185,b2181)
    val x4358 = withCtrl(x4364) { OpDef(op=BitAnd, inputs=List(b2007, b1969)).name("x4358").srcCtx("UnrollingBase.scala:28:66") } // And(b2007,b1969)
    val x4359 = withCtrl(x4364) { OpDef(op=BitAnd, inputs=List(x4357, x4358)).name("x4359").srcCtx("UnrollingBase.scala:28:66") } // And(x4357,x4358)
    val x4360 = withCtrl(x4364) { LoadBanks(List(x4182_d0_b6), List(b1999, b2180, b2184)).name("x4360").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b1999, b2180, b2184),x4359)
    val x4361 = withCtrl(x4364) { ReadMem(x4224).name("x4361").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4224)
    val x4362 = withCtrl(x4364) { OpDef(op=FixMul, inputs=List(x4360, x4361)).name("x4362").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4360,x4361)
    val x4363 = withCtrl(x4364) { StoreBanks(List(List(x4216_d0_b0)), List(b2180, b2184), x4362).name("x4363").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4216,List(List(b2180, b2184)),List(x4362),List(x4359))
    val x4366 = withCtrl(x4379) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4366").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4367 = withCtrl(x4379) { CounterChain(List(x4366)).name("x4367").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x4366))
    val x4378 = withCtrl(x4379) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4367).name("x4378").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b2008, b1969),x4367,Block(Const(())),List(List(b2195)),List(List(b2196)))
    val b2195 = withCtrl(x4378) { CounterIter(x4366, Some(0)).name("b2195") } // b2195
    val b2196 = withCtrl(x4378) { Const(true).name("b2196") } // b2196
    val x4368 = withCtrl(x4378) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4368").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4369 = withCtrl(x4378) { CounterChain(List(x4368)).name("x4369").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x4368))
    val x4377 = withCtrl(x4378) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4369).name("x4377").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2196, b2008, b1969),x4369,Block(Const(())),List(List(b2199)),List(List(b2200)))
    val b2199 = withCtrl(x4377) { CounterIter(x4368, None).name("b2199") } // b2199
    val b2200 = withCtrl(x4377) { Const(true).name("b2200") } // b2200
    val x4370 = withCtrl(x4377) { OpDef(op=BitAnd, inputs=List(b2200, b2196)).name("x4370").srcCtx("UnrollingBase.scala:28:66") } // And(b2200,b2196)
    val x4371 = withCtrl(x4377) { OpDef(op=BitAnd, inputs=List(b2008, b1969)).name("x4371").srcCtx("UnrollingBase.scala:28:66") } // And(b2008,b1969)
    val x4372 = withCtrl(x4377) { OpDef(op=BitAnd, inputs=List(x4370, x4371)).name("x4372").srcCtx("UnrollingBase.scala:28:66") } // And(x4370,x4371)
    val x4373 = withCtrl(x4377) { LoadBanks(List(x4182_d0_b7), List(b2000, b2195, b2199)).name("x4373").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x4182,List(b2000, b2195, b2199),x4372)
    val x4374 = withCtrl(x4377) { ReadMem(x4225).name("x4374").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x4225)
    val x4375 = withCtrl(x4377) { OpDef(op=FixMul, inputs=List(x4373, x4374)).name("x4375").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x4373,x4374)
    val x4376 = withCtrl(x4377) { StoreBanks(List(List(x4217_d0_b0)), List(b2195, b2199), x4375).name("x4376").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x4217,List(List(b2195, b2199)),List(x4375),List(x4372))
    val x4380 = withCtrl(x4445) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4380").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4381 = withCtrl(x4445) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4381").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4382 = withCtrl(x4445) { CounterChain(List(x4381,x4380)).name("x4382").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(ArrayBuffer(x4381, x4380))
    val x4444 = withCtrl(x4445) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4382).name("x4444").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledForeach(List(),x4382,Block(Const(())),ArrayBuffer(List(b2211), List(b2212)),ArrayBuffer(List(b2213), List(b2214)))
    val b2211 = withCtrl(x4444) { CounterIter(x4381, Some(0)).name("b2211") } // b2211
    val b2213 = withCtrl(x4444) { Const(true).name("b2213") } // b2213
    val b2212 = withCtrl(x4444) { CounterIter(x4380, None).name("b2212") } // b2212
    val b2214 = withCtrl(x4444) { Const(true).name("b2214") } // b2214
    val x4383 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2213, b2214)).name("x4383").srcCtx("UnrollingBase.scala:28:66") } // And(b2213,b2214)
    val x4384 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4383, b1969)).name("x4384").srcCtx("UnrollingBase.scala:28:66") } // And(x4383,b1969)
    val x4385 = withCtrl(x4444) { LoadBanks(List(x4210_d0_b0), List(b2211, b2212)).name("x4385").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4210,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4386 = withCtrl(x4444) { x4385 } // VectorApply(x4385,0)
    val x4387 = withCtrl(x4444) { LoadBanks(List(x4211_d0_b0), List(b2211, b2212)).name("x4387").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4211,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4388 = withCtrl(x4444) { x4387 } // VectorApply(x4387,0)
    val x4389 = withCtrl(x4444) { LoadBanks(List(x4212_d0_b0), List(b2211, b2212)).name("x4389").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4212,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4390 = withCtrl(x4444) { x4389 } // VectorApply(x4389,0)
    val x4391 = withCtrl(x4444) { LoadBanks(List(x4213_d0_b0), List(b2211, b2212)).name("x4391").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4213,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4392 = withCtrl(x4444) { x4391 } // VectorApply(x4391,0)
    val x4393 = withCtrl(x4444) { LoadBanks(List(x4214_d0_b0), List(b2211, b2212)).name("x4393").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4214,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4394 = withCtrl(x4444) { x4393 } // VectorApply(x4393,0)
    val x4395 = withCtrl(x4444) { LoadBanks(List(x4215_d0_b0), List(b2211, b2212)).name("x4395").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4215,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4396 = withCtrl(x4444) { x4395 } // VectorApply(x4395,0)
    val x4397 = withCtrl(x4444) { LoadBanks(List(x4216_d0_b0), List(b2211, b2212)).name("x4397").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4216,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4398 = withCtrl(x4444) { x4397 } // VectorApply(x4397,0)
    val x4399 = withCtrl(x4444) { LoadBanks(List(x4217_d0_b0), List(b2211, b2212)).name("x4399").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4217,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4400 = withCtrl(x4444) { x4399 } // VectorApply(x4399,0)
    val x4401 = withCtrl(x4444) { LoadBanks(List(x4190_d1_b0), List(b2211, b2212)).name("x4401").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x4190,List(ArrayBuffer(b2211, b2212)),List(x4384))
    val x4402 = withCtrl(x4444) { x4401 } // VectorApply(x4401,0)
    val x4403 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2001, b1969)).name("x4403").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2001,b1969)
    val x4404 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2002, b1969)).name("x4404").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2002,b1969)
    val x4405 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2003, b1969)).name("x4405").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2003,b1969)
    val x4406 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2004, b1969)).name("x4406").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2004,b1969)
    val x4407 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2005, b1969)).name("x4407").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2005,b1969)
    val x4408 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2006, b1969)).name("x4408").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2006,b1969)
    val x4409 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2007, b1969)).name("x4409").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2007,b1969)
    val x4410 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(b2008, b1969)).name("x4410").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b2008,b1969)
    val x4411 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4403, x4384)).name("x4411").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4403,x4384)
    val x4412 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4404, x4384)).name("x4412").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4404,x4384)
    val x4413 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4405, x4384)).name("x4413").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4405,x4384)
    val x4414 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4406, x4384)).name("x4414").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4406,x4384)
    val x4415 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4407, x4384)).name("x4415").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4407,x4384)
    val x4416 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4408, x4384)).name("x4416").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4408,x4384)
    val x4417 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4409, x4384)).name("x4417").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4409,x4384)
    val x4418 = withCtrl(x4444) { OpDef(op=BitAnd, inputs=List(x4410, x4384)).name("x4418").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x4410,x4384)
    val x4419 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4386, x4388)).name("x4419").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4386,x4388)
    val x4420 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4412, x4419, x4386)).name("x4420").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4412,x4419,x4386)
    val x4421 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4411, x4412)).name("x4421").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4411,x4412)
    val x4422 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4390, x4392)).name("x4422").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4390,x4392)
    val x4423 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4414, x4422, x4390)).name("x4423").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4414,x4422,x4390)
    val x4424 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4413, x4414)).name("x4424").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4413,x4414)
    val x4425 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4394, x4396)).name("x4425").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4394,x4396)
    val x4426 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4416, x4425, x4394)).name("x4426").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4416,x4425,x4394)
    val x4427 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4415, x4416)).name("x4427").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4415,x4416)
    val x4428 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4398, x4400)).name("x4428").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4398,x4400)
    val x4429 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4418, x4428, x4398)).name("x4429").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4418,x4428,x4398)
    val x4430 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4417, x4418)).name("x4430").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4417,x4418)
    val x4431 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4420, x4423)).name("x4431").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4420,x4423)
    val x4432 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4424, x4431, x4420)).name("x4432").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4424,x4431,x4420)
    val x4433 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4421, x4424)).name("x4433").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4421,x4424)
    val x4434 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4426, x4429)).name("x4434").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4426,x4429)
    val x4435 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4430, x4434, x4426)).name("x4435").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4430,x4434,x4426)
    val x4436 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4427, x4430)).name("x4436").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4427,x4430)
    val x4437 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4432, x4435)).name("x4437").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4432,x4435)
    val x4438 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4436, x4437, x4432)).name("x4438").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4436,x4437,x4432)
    val x4439 = withCtrl(x4444) { OpDef(op=BitOr, inputs=List(x4433, x4436)).name("x4439").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Or(x4433,x4436)
    val x4440 = withCtrl(x4444) { OpDef(op=FixEql, inputs=List(b1993, Const(0))).name("x4440").srcCtx("VecMatMultBiasAdd.scala:179:6") } // FixEql(b1993,Const(0))
    val x4441 = withCtrl(x4444) { OpDef(op=FixAdd, inputs=List(x4438, x4402)).name("x4441").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x4438,x4402)
    val x4442 = withCtrl(x4444) { OpDef(op=MuxOp, inputs=List(x4440, x4438, x4441)).name("x4442").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x4440,x4438,x4441)
    val x4443 = withCtrl(x4444) { StoreBanks(List(List(x4190_d0_b0), List(x4190_d1_b0)), List(b2211, b2212), x4442).name("x4443").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMStore(x4190,List(ArrayBuffer(b2211, b2212)),List(x4442),List(x4384))
    antiDepsOf(x4443)=List(x4401)
    val x4446 = withCtrl(x4577) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4446").srcCtx("VecMatMultBiasAdd.scala:181:26") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4447 = withCtrl(x4577) { CounterChain(List(x4446)).name("x4447").srcCtx("VecMatMultBiasAdd.scala:181:42") } // CounterChainNew(List(x4446))
    val x4465 = withCtrl(x4577) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4447).name("x4465").srcCtx("VecMatMultBiasAdd.scala:181:42") } // UnrolledForeach(List(b1969),x4447,Block(Const(())),List(List(b2280)),List(List(b2281)))
    val b2280 = withCtrl(x4465) { CounterIter(x4446, Some(0)).name("b2280") } // b2280
    val b2281 = withCtrl(x4465) { Const(true).name("b2281") } // b2281
    val x4448 = withCtrl(x4465) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4448").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4449 = withCtrl(x4465) { CounterChain(List(x4448)).name("x4449").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x4448))
    val x4464 = withCtrl(x4465) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4449).name("x4464").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2281, b1969),x4449,Block(Const(())),List(List(b2284)),List(List(b2285)))
    val b2284 = withCtrl(x4464) { CounterIter(x4448, None).name("b2284") } // b2284
    val b2285 = withCtrl(x4464) { Const(true).name("b2285") } // b2285
    val x4450 = withCtrl(x4464) { ReadMem(x4195).name("x4450").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4195)
    val x4451 = withCtrl(x4464) { OpDef(op=BitAnd, inputs=List(b2285, b2281)).name("x4451").srcCtx("UnrollingBase.scala:28:66") } // And(b2285,b2281)
    val x4452 = withCtrl(x4464) { OpDef(op=BitAnd, inputs=List(x4451, b1969)).name("x4452").srcCtx("UnrollingBase.scala:28:66") } // And(x4451,b1969)
    val x4453 = withCtrl(x4464) { LoadBanks(List(x4183_d0_b0), List(x4450, b2280, b2284)).name("x4453").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x4183,List(x4450, b2280, b2284),x4452)
    val x4454 = withCtrl(x4464) { LoadBanks(List(x4184_d0_b0), List(x4450, b2280, b2284)).name("x4454").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x4184,List(x4450, b2280, b2284),x4452)
    val x4455 = withCtrl(x4464) { LoadBanks(List(x4185_d0_b0), List(x4450, b2280, b2284)).name("x4455").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x4185,List(x4450, b2280, b2284),x4452)
    val x4456 = withCtrl(x4464) { ReadMem(x4194).name("x4456").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4194)
    val x4457 = withCtrl(x4464) { OpDef(op=MuxOp, inputs=List(x4456, x4455, x4454)).name("x4457").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x4456,x4455,x4454)
    val x4458 = withCtrl(x4464) { ReadMem(x4193).name("x4458").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4193)
    val x4459 = withCtrl(x4464) { OpDef(op=MuxOp, inputs=List(x4458, x4453, x4457)).name("x4459").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x4458,x4453,x4457)
    val x4460 = withCtrl(x4464) { LoadBanks(List(x4190_d0_b0), List(b2280, b2284)).name("x4460").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x4190,List(List(b2280, b2284)),List(x4452))
    val x4461 = withCtrl(x4464) { x4460 } // VectorApply(x4460,0)
    val x4462 = withCtrl(x4464) { OpDef(op=FixAdd, inputs=List(x4461, x4459)).name("x4462").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x4461,x4459)
    val x4463 = withCtrl(x4464) { StoreBanks(List(List(x4191_d0_b0)), List(b2280, b2284), x4462).name("x4463").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x4191,List(List(b2280, b2284)),List(x4462),List(x4452))
    val x4466 = withCtrl(x4577) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x4466").srcCtx("GateMetaPipe.scala:143:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x4467 = withCtrl(x4577) { CounterChain(List(x4466)).name("x4467").srcCtx("GateMetaPipe.scala:143:27") } // CounterChainNew(List(x4466))
    val x4576 = withCtrl(x4577) { LoopController(style=MetaPipe, level=OuterControl, cchain=x4467).name("x4576").srcCtx("GateMetaPipe.scala:143:27") } // UnrolledForeach(List(b1969),x4467,Block(Const(())),List(List(b2304)),List(List(b2305)))
    val b2304 = withCtrl(x4576) { CounterIter(x4466, Some(0)).name("b2304") } // b2304
    val b2305 = withCtrl(x4576) { Const(true).name("b2305") } // b2305
    val x4468 = withCtrl(x4576) { FIFO(size=64).name("x4468").srcCtx("GateMetaPipe.scala:144:25:sigQ") } // x4468 = FIFONew(Const(64))
    isAccum(x4468) = false
    bufferDepthOf(x4468) = 2
    val x4469 = withCtrl(x4576) { FIFO(size=64).name("x4469").srcCtx("GateMetaPipe.scala:145:26:sigQQ") } // x4469 = FIFONew(Const(64))
    isAccum(x4469) = false
    bufferDepthOf(x4469) = 2
    val x4470 = withCtrl(x4576) { FIFO(size=64).name("x4470").srcCtx("GateMetaPipe.scala:146:31:sigEleMuxQ") } // x4470 = FIFONew(Const(64))
    isAccum(x4470) = false
    bufferDepthOf(x4470) = 2
    val x4471 = withCtrl(x4576) { FIFO(size=64).name("x4471").srcCtx("GateMetaPipe.scala:147:27:tanhQ") } // x4471 = FIFONew(Const(64))
    def split1 = {
    isAccum(x4471) = false
    bufferDepthOf(x4471) = 2
    val x4472 = withCtrl(x4576) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4472").srcCtx("GateMetaPipe.scala:149:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4473 = withCtrl(x4576) { CounterChain(List(x4472)).name("x4473").srcCtx("GateMetaPipe.scala:149:48") } // CounterChainNew(List(x4472))
    val x4495 = withCtrl(x4576) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4473).name("x4495").srcCtx("GateMetaPipe.scala:149:48") } // UnrolledForeach(List(b2305, b1969),x4473,Block(Const(())),List(List(b2312)),List(List(b2313)))
    val b2312 = withCtrl(x4495) { CounterIter(x4472, None).name("b2312") } // b2312
    val b2313 = withCtrl(x4495) { Const(true).name("b2313") } // b2313
    val x4474 = withCtrl(x4495) { OpDef(op=BitAnd, inputs=List(b2313, b2305)).name("x4474").srcCtx("UnrollingBase.scala:28:66") } // And(b2313,b2305)
    val x4475 = withCtrl(x4495) { OpDef(op=BitAnd, inputs=List(x4474, b1969)).name("x4475").srcCtx("UnrollingBase.scala:28:66") } // And(x4474,b1969)
    val x4476 = withCtrl(x4495) { LoadBanks(List(x4191_d0_b0), List(b2304, b2312)).name("x4476").srcCtx("GateMetaPipe.scala:150:27:pEle") } // ParSRAMLoad(x4191,List(List(b2304, b2312)),List(x4475))
    val x4477 = withCtrl(x4495) { x4476 } // VectorApply(x4476,0)
    val x4478_d0_b0 = withCtrl(x4495) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x4478_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x4478 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x4478_d0_b0) = false
    bufferDepthOf(x4478_d0_b0) = 1
    staticDimsOf(x4478_d0_b0) = List(1024)
    val x4479 = withCtrl(x4495) { OpDef(op=FixSub, inputs=List(x4477, Const(-8.0))).name("x4479").srcCtx("NonLinearity.scala:44:22") } // FixSub(x4477,Const(-8))
    val x4480 = withCtrl(x4495) { OpDef(op=FixSla, inputs=List(x4479, Const(6))).name("x4480").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x4479,Const(6))
    // x4481 = FixConvert(x4480,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x4481 = withCtrl(x4495) { OpDef(op=FixSra, inputs=List(x4480, Const("24"))).name("x4481").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x4480,TRUE,_32,_0)
    // }
    val x4482 = withCtrl(x4495) { LoadBanks(List(x4478_d0_b0), List(x4481)).name("x4482").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x4478,List(x4481),x4475)
    val x4483_d0_b0 = withCtrl(x4495) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x4483_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x4483 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x4483_d0_b0) = false
    bufferDepthOf(x4483_d0_b0) = 1
    staticDimsOf(x4483_d0_b0) = List(1024)
    val x4484 = withCtrl(x4495) { LoadBanks(List(x4483_d0_b0), List(x4481)).name("x4484").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x4483,List(x4481),x4475)
    val x4485 = withCtrl(x4495) { OpDef(op=FixLt, inputs=List(Const(8.0), x4477)).name("x4485").srcCtx("GateMetaPipe.scala:154:27:isHigh") } // FixLt(Const(8),x4477)
    val x4486 = withCtrl(x4495) { OpDef(op=FixLt, inputs=List(x4477, Const(-8.0))).name("x4486").srcCtx("GateMetaPipe.scala:155:26:isLow") } // FixLt(x4477,Const(-8))
    val x4487 = withCtrl(x4495) { OpDef(op=MuxOp, inputs=List(x4486, Const(0.0), x4482)).name("x4487").srcCtx("GateMetaPipe.scala:157:46") } // Mux(x4486,Const(0),x4482)
    val x4488 = withCtrl(x4495) { OpDef(op=MuxOp, inputs=List(x4485, Const(1.0), x4487)).name("x4488").srcCtx("GateMetaPipe.scala:157:25:sigEle") } // Mux(x4485,Const(1),x4487)
    val x4489 = withCtrl(x4495) { OpDef(op=MuxOp, inputs=List(x4486, Const(-1.0), x4484)).name("x4489").srcCtx("GateMetaPipe.scala:158:47") } // Mux(x4486,Const(-1),x4484)
    val x4490 = withCtrl(x4495) { OpDef(op=MuxOp, inputs=List(x4485, Const(1.0), x4489)).name("x4490").srcCtx("GateMetaPipe.scala:158:26:tanhEle") } // Mux(x4485,Const(1),x4489)
    val x4491_x4468 = withCtrl(x4495) { WriteMem(x4468, x4488).name("x4491_x4468").srcCtx("GateMetaPipe.scala:160:17") } // ParFIFOEnq(x4468,List(x4488),List(x4475))
    val x4492_x4469 = withCtrl(x4495) { WriteMem(x4469, x4488).name("x4492_x4469").srcCtx("GateMetaPipe.scala:161:18") } // ParFIFOEnq(x4469,List(x4488),List(x4475))
    val x4493_x4470 = withCtrl(x4495) { WriteMem(x4470, x4488).name("x4493_x4470").srcCtx("GateMetaPipe.scala:162:23") } // ParFIFOEnq(x4470,List(x4488),List(x4475))
    val x4494_x4471 = withCtrl(x4495) { WriteMem(x4471, x4490).name("x4494_x4471").srcCtx("GateMetaPipe.scala:164:18") } // ParFIFOEnq(x4471,List(x4490),List(x4475))
    val x4575 = withCtrl(x4576) { UnitController(style=SeqPipe, level=OuterControl).name("x4575").srcCtx("GateMetaPipe.scala:167:12") } // UnitPipe(List(b2305, b1969),Block(Const(())))
    val x4496 = withCtrl(x4575) { FIFO(size=64).name("x4496").srcCtx("GateMetaPipe.scala:169:29:cLastQ") } // x4496 = FIFONew(Const(64))
    isAccum(x4496) = false
    bufferDepthOf(x4496) = 1
    val x4497 = withCtrl(x4575) { FIFO(size=64).name("x4497").srcCtx("GateMetaPipe.scala:170:35:cNewMultAddQ") } // x4497 = FIFONew(Const(64))
    isAccum(x4497) = false
    bufferDepthOf(x4497) = 1
    val x4498 = withCtrl(x4575) { FIFO(size=64).name("x4498").srcCtx("GateMetaPipe.scala:171:36:cNewMultAddQQ") } // x4498 = FIFONew(Const(64))
    isAccum(x4498) = false
    bufferDepthOf(x4498) = 1
    val x4499 = withCtrl(x4575) { FIFO(size=64).name("x4499").srcCtx("GateMetaPipe.scala:172:32:cNewMultQ") } // x4499 = FIFONew(Const(64))
    isAccum(x4499) = false
    bufferDepthOf(x4499) = 1
    val x4500 = withCtrl(x4575) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4500").srcCtx("GateMetaPipe.scala:174:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4501 = withCtrl(x4575) { CounterChain(List(x4500)).name("x4501").srcCtx("GateMetaPipe.scala:174:50") } // CounterChainNew(List(x4500))
    val x4521 = withCtrl(x4575) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4501).name("x4521").srcCtx("GateMetaPipe.scala:174:50") } // UnrolledForeach(List(b2305, b1969),x4501,Block(Const(())),List(List(b2342)),List(List(b2343)))
    val b2342 = withCtrl(x4521) { CounterIter(x4500, None).name("b2342") } // b2342
    val b2343 = withCtrl(x4521) { Const(true).name("b2343") } // b2343
    val x4502 = withCtrl(x4521) { OpDef(op=BitAnd, inputs=List(b2343, b2305)).name("x4502").srcCtx("UnrollingBase.scala:28:66") } // And(b2343,b2305)
    val x4503 = withCtrl(x4521) { OpDef(op=BitAnd, inputs=List(x4502, b1969)).name("x4503").srcCtx("UnrollingBase.scala:28:66") } // And(x4502,b1969)
    val x4504 = withCtrl(x4521) { ReadMem(x4468).name("x4504").srcCtx("GateMetaPipe.scala:175:32:sigEle") } // ParFIFODeq(x4468,List(x4503))
    val x4505 = withCtrl(x4521) { x4504 } // VectorApply(x4504,0)
    val x4506 = withCtrl(x4521) { ReadMem(x4471).name("x4506").srcCtx("GateMetaPipe.scala:176:34:tanhEle") } // ParFIFODeq(x4471,List(x4503))
    val x4507 = withCtrl(x4521) { x4506 } // VectorApply(x4506,0)
    val x4508 = withCtrl(x4521) { OpDef(op=FixEql, inputs=List(b2304, Const(0))).name("x4508").srcCtx("package.scala:110:40") } // FixEql(b2304,Const(0))
    val x4509 = withCtrl(x4521) { ReadMem(x4192_d0).name("x4509").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x4192)
    val x4510 = withCtrl(x4521) { OpDef(op=BitAnd, inputs=List(x4509, x4508)).name("x4510").srcCtx("GateMetaPipe.scala:177:36:isInitC") } // And(x4509,x4508)
    val x4511 = withCtrl(x4521) { LoadBanks(List(x4186_d0_b0), List(b2342)).name("x4511").srcCtx("GateMetaPipe.scala:179:35") } // ParSRAMLoad(x4186,List(List(b2342)),List(x4503))
    val x4512 = withCtrl(x4521) { x4511 } // VectorApply(x4511,0)
    val x4513 = withCtrl(x4521) { OpDef(op=MuxOp, inputs=List(x4510, Const(0.0), x4512)).name("x4513").srcCtx("GateMetaPipe.scala:178:26:cLast") } // Mux(x4510,Const(0),x4512)
    val x4514 = withCtrl(x4521) { OpDef(op=FixMul, inputs=List(x4513, x4507)).name("x4514").srcCtx("GateMetaPipe.scala:181:32:cNewMult") } // FixMul(x4513,x4507)
    val x4515 = withCtrl(x4521) { OpDef(op=FixMul, inputs=List(x4505, x4513)).name("x4515").srcCtx("GateMetaPipe.scala:182:36") } // FixMul(x4505,x4513)
    val x4516 = withCtrl(x4521) { OpDef(op=FixAdd, inputs=List(x4515, x4514)).name("x4516").srcCtx("GateMetaPipe.scala:182:44:cNewMultAdd") } // FixAdd(x4515,x4514)
    val x4517_x4499 = withCtrl(x4521) { WriteMem(x4499, x4514).name("x4517_x4499").srcCtx("GateMetaPipe.scala:184:24") } // ParFIFOEnq(x4499,List(x4514),List(x4503))
    val x4518_x4497 = withCtrl(x4521) { WriteMem(x4497, x4516).name("x4518_x4497").srcCtx("GateMetaPipe.scala:185:27") } // ParFIFOEnq(x4497,List(x4516),List(x4503))
    val x4519_x4498 = withCtrl(x4521) { WriteMem(x4498, x4516).name("x4519_x4498").srcCtx("GateMetaPipe.scala:186:28") } // ParFIFOEnq(x4498,List(x4516),List(x4503))
    val x4520_x4496 = withCtrl(x4521) { WriteMem(x4496, x4513).name("x4520_x4496").srcCtx("GateMetaPipe.scala:187:21") } // ParFIFOEnq(x4496,List(x4513),List(x4503))
    val x4522 = withCtrl(x4575) { FIFO(size=64).name("x4522").srcCtx("GateMetaPipe.scala:190:31:cUpdateQ") } // x4522 = FIFONew(Const(64))
    isAccum(x4522) = false
    bufferDepthOf(x4522) = 1
    val x4523 = withCtrl(x4575) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4523").srcCtx("GateMetaPipe.scala:191:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4524 = withCtrl(x4575) { CounterChain(List(x4523)).name("x4524").srcCtx("GateMetaPipe.scala:191:50") } // CounterChainNew(List(x4523))
    val x4542 = withCtrl(x4575) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4524).name("x4542").srcCtx("GateMetaPipe.scala:191:50") } // UnrolledForeach(List(b2305, b1969),x4524,Block(Const(())),List(List(b2367)),List(List(b2368)))
    val b2367 = withCtrl(x4542) { CounterIter(x4523, None).name("b2367") } // b2367
    val b2368 = withCtrl(x4542) { Const(true).name("b2368") } // b2368
    val x4525 = withCtrl(x4542) { OpDef(op=BitAnd, inputs=List(b2368, b2305)).name("x4525").srcCtx("UnrollingBase.scala:28:66") } // And(b2368,b2305)
    val x4526 = withCtrl(x4542) { OpDef(op=BitAnd, inputs=List(x4525, b1969)).name("x4526").srcCtx("UnrollingBase.scala:28:66") } // And(x4525,b1969)
    val x4527 = withCtrl(x4542) { ReadMem(x4499).name("x4527").srcCtx("GateMetaPipe.scala:192:39:cNewMult") } // ParFIFODeq(x4499,List(x4526))
    val x4528 = withCtrl(x4542) { x4527 } // VectorApply(x4527,0)
    val x4529 = withCtrl(x4542) { ReadMem(x4470).name("x4529").srcCtx("GateMetaPipe.scala:193:38:sigEle") } // ParFIFODeq(x4470,List(x4526))
    val x4530 = withCtrl(x4542) { x4529 } // VectorApply(x4529,0)
    val x4531 = withCtrl(x4542) { ReadMem(x4497).name("x4531").srcCtx("GateMetaPipe.scala:194:45:cNewMultAdd") } // ParFIFODeq(x4497,List(x4526))
    val x4532 = withCtrl(x4542) { x4531 } // VectorApply(x4531,0)
    val x4533 = withCtrl(x4542) { ReadMem(x4496).name("x4533").srcCtx("GateMetaPipe.scala:195:33:cLast") } // ParFIFODeq(x4496,List(x4526))
    val x4534 = withCtrl(x4542) { x4533 } // VectorApply(x4533,0)
    val x4535 = withCtrl(x4542) { OpDef(op=FixEql, inputs=List(b2304, Const(0))).name("x4535").srcCtx("package.scala:110:40") } // FixEql(b2304,Const(0))
    val x4536 = withCtrl(x4542) { OpDef(op=FixEql, inputs=List(b2304, Const(1))).name("x4536").srcCtx("package.scala:113:40") } // FixEql(b2304,Const(1))
    val x4537 = withCtrl(x4542) { OpDef(op=FixEql, inputs=List(b2304, Const(2))).name("x4537").srcCtx("package.scala:116:40") } // FixEql(b2304,Const(2))
    val x4538 = withCtrl(x4542) { OpDef(op=MuxOp, inputs=List(x4537, x4532, x4534)).name("x4538").srcCtx("GateMetaPipe.scala:200:40") } // Mux(x4537,x4532,x4534)
    val x4539 = withCtrl(x4542) { OpDef(op=MuxOp, inputs=List(x4536, x4528, x4538)).name("x4539").srcCtx("GateMetaPipe.scala:199:36") } // Mux(x4536,x4528,x4538)
    val x4540 = withCtrl(x4542) { OpDef(op=MuxOp, inputs=List(x4535, x4530, x4539)).name("x4540").srcCtx("GateMetaPipe.scala:198:28:cUpdate") } // Mux(x4535,x4530,x4539)
    val x4541_x4522 = withCtrl(x4542) { WriteMem(x4522, x4540).name("x4541_x4522").srcCtx("GateMetaPipe.scala:206:23") } // ParFIFOEnq(x4522,List(x4540),List(x4526))
    val x4543 = withCtrl(x4575) { FIFO(size=64).name("x4543").srcCtx("GateMetaPipe.scala:209:31:hLinearQ") } // x4543 = FIFONew(Const(64))
    isAccum(x4543) = false
    bufferDepthOf(x4543) = 1
    val x4544 = withCtrl(x4575) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4544").srcCtx("GateMetaPipe.scala:210:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4545 = withCtrl(x4575) { CounterChain(List(x4544)).name("x4545").srcCtx("GateMetaPipe.scala:210:50") } // CounterChainNew(List(x4544))
    val x4560 = withCtrl(x4575) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4545).name("x4560").srcCtx("GateMetaPipe.scala:210:50") } // UnrolledForeach(List(b2305, b1969),x4545,Block(Const(())),List(List(b2390)),List(List(b2391)))
    val b2390 = withCtrl(x4560) { CounterIter(x4544, None).name("b2390") } // b2390
    val b2391 = withCtrl(x4560) { Const(true).name("b2391") } // b2391
    val x4546 = withCtrl(x4560) { OpDef(op=BitAnd, inputs=List(b2391, b2305)).name("x4546").srcCtx("UnrollingBase.scala:28:66") } // And(b2391,b2305)
    val x4547 = withCtrl(x4560) { OpDef(op=BitAnd, inputs=List(x4546, b1969)).name("x4547").srcCtx("UnrollingBase.scala:28:66") } // And(x4546,b1969)
    val x4548 = withCtrl(x4560) { ReadMem(x4498).name("x4548").srcCtx("GateMetaPipe.scala:211:46:cNewMultAdd") } // ParFIFODeq(x4498,List(x4547))
    val x4549 = withCtrl(x4560) { x4548 } // VectorApply(x4548,0)
    val x4550_d0_b0 = withCtrl(x4560) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x4550_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x4550 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x4550_d0_b0) = false
    bufferDepthOf(x4550_d0_b0) = 1
    staticDimsOf(x4550_d0_b0) = List(1024)
    val x4551 = withCtrl(x4560) { OpDef(op=FixSub, inputs=List(x4549, Const(-8.0))).name("x4551").srcCtx("NonLinearity.scala:44:22") } // FixSub(x4549,Const(-8))
    val x4552 = withCtrl(x4560) { OpDef(op=FixSla, inputs=List(x4551, Const(6))).name("x4552").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x4551,Const(6))
    // x4553 = FixConvert(x4552,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x4553 = withCtrl(x4560) { OpDef(op=FixSra, inputs=List(x4552, Const("24"))).name("x4553").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x4552,TRUE,_32,_0)
    // }
    val x4554 = withCtrl(x4560) { LoadBanks(List(x4550_d0_b0), List(x4553)).name("x4554").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x4550,List(x4553),x4547)
    val x4555 = withCtrl(x4560) { OpDef(op=FixLt, inputs=List(Const(8.0), x4549)).name("x4555").srcCtx("GateMetaPipe.scala:214:36:isHigh") } // FixLt(Const(8),x4549)
    val x4556 = withCtrl(x4560) { OpDef(op=FixLt, inputs=List(x4549, Const(-8.0))).name("x4556").srcCtx("GateMetaPipe.scala:215:35:isLow") } // FixLt(x4549,Const(-8))
    val x4557 = withCtrl(x4560) { OpDef(op=MuxOp, inputs=List(x4556, Const(-1.0), x4554)).name("x4557").srcCtx("GateMetaPipe.scala:217:33") } // Mux(x4556,Const(-1),x4554)
    val x4558 = withCtrl(x4560) { OpDef(op=MuxOp, inputs=List(x4555, Const(1.0), x4557)).name("x4558").srcCtx("GateMetaPipe.scala:216:28:hLinear") } // Mux(x4555,Const(1),x4557)
    val x4559_x4543 = withCtrl(x4560) { WriteMem(x4543, x4558).name("x4559_x4543").srcCtx("GateMetaPipe.scala:222:23") } // ParFIFOEnq(x4543,List(x4558),List(x4547))
    val x4561 = withCtrl(x4575) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4561").srcCtx("GateMetaPipe.scala:225:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4562 = withCtrl(x4575) { CounterChain(List(x4561)).name("x4562").srcCtx("GateMetaPipe.scala:225:50") } // CounterChainNew(List(x4561))
    val x4574 = withCtrl(x4575) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4562).name("x4574").srcCtx("GateMetaPipe.scala:225:50") } // UnrolledForeach(List(b2305, b1969),x4562,Block(Const(())),List(List(b2409)),List(List(b2410)))
    val b2409 = withCtrl(x4574) { CounterIter(x4561, None).name("b2409") } // b2409
    val b2410 = withCtrl(x4574) { Const(true).name("b2410") } // b2410
    val x4563 = withCtrl(x4574) { OpDef(op=BitAnd, inputs=List(b2410, b2305)).name("x4563").srcCtx("UnrollingBase.scala:28:66") } // And(b2410,b2305)
    val x4564 = withCtrl(x4574) { OpDef(op=BitAnd, inputs=List(x4563, b1969)).name("x4564").srcCtx("UnrollingBase.scala:28:66") } // And(x4563,b1969)
    val x4565 = withCtrl(x4574) { ReadMem(x4522).name("x4565").srcCtx("GateMetaPipe.scala:229:34:cNew") } // ParFIFODeq(x4522,List(x4564))
    val x4566 = withCtrl(x4574) { x4565 } // VectorApply(x4565,0)
    val x4567 = withCtrl(x4574) { ReadMem(x4543).name("x4567").srcCtx("GateMetaPipe.scala:230:37:hLinear") } // ParFIFODeq(x4543,List(x4564))
    val x4568 = withCtrl(x4574) { x4567 } // VectorApply(x4567,0)
    val x4569 = withCtrl(x4574) { ReadMem(x4469).name("x4569").srcCtx("GateMetaPipe.scala:231:33:sigEle") } // ParFIFODeq(x4469,List(x4564))
    val x4570 = withCtrl(x4574) { x4569 } // VectorApply(x4569,0)
    val x4571 = withCtrl(x4574) { OpDef(op=FixMul, inputs=List(x4568, x4570)).name("x4571").srcCtx("GateMetaPipe.scala:232:30:hNew") } // FixMul(x4568,x4570)
    val x4572 = withCtrl(x4574) { StoreBanks(List(List(x4187_d0_b0), List(x4187_d1_b0)), List(b2409), x4571).name("x4572").srcCtx("GateMetaPipe.scala:234:29") } // ParSRAMStore(x4187,List(List(b2409)),List(x4571),List(x4564))
    val x4573 = withCtrl(x4574) { StoreBanks(List(List(x4186_d0_b0)), List(b2409), x4566).name("x4573").srcCtx("GateMetaPipe.scala:235:29") } // ParSRAMStore(x4186,List(List(b2409)),List(x4566),List(x4564))
    val x4599 = withCtrl(x4600) { UnitController(style=StreamPipe, level=OuterControl).name("x4599").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b4611 = withCtrl(x4599) { StreamOut(field="offset").name("b4611").srcCtx("DeepBenchLSTM256.scala:119:39") } // x4578 = StreamOutNew(BurstCmdBus)
    isAccum(b4611) = false
    bufferDepthOf(b4611) = 1
    val b4612 = withCtrl(x4599) { StreamOut(field="size").name("b4612").srcCtx("DeepBenchLSTM256.scala:119:39") } // x4578 = StreamOutNew(BurstCmdBus)
    isAccum(b4612) = false
    bufferDepthOf(b4612) = 1
    val x4579 = withCtrl(x4599) { StreamOut(field="data").name("x4579").srcCtx("DeepBenchLSTM256.scala:119:39") } // x4579 = StreamOutNew(BurstFullDataBus())
    isAccum(x4579) = false
    bufferDepthOf(x4579) = 1
    val x4580 = withCtrl(x4599) { StreamIn(field="ack").name("x4580").srcCtx("DeepBenchLSTM256.scala:119:39") } // x4580 = StreamInNew(BurstAckBus)
    isAccum(x4580) = false
    bufferDepthOf(x4580) = 1
    val x4588 = withCtrl(x4599) { UnitController(style=SeqPipe, level=InnerControl).name("x4588").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(x4587))
    val x4581 = withCtrl(x4588) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4582 = withCtrl(x4588) { OpDef(op=FixSla, inputs=List(x4581, Const(2))).name("x4582").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixLsh(x4581,Const(2))
    val x4583 = withCtrl(x4588) { x4582 } // FixConvert(x4582,TRUE,_64,_0)
    val x4584 = withCtrl(x4588) { DramAddress(x4181).name("x4584").srcCtx("DeepBenchLSTM256.scala:119:39") } // GetDRAMAddress(x4181)
    val x4586_x4585 = withCtrl(x4588) { OpDef(op=FixAdd, inputs=List(x4583, x4584)).name("x4586_x4585").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixAdd(x4583,x4584)
    // x4586 = SimpleStruct(ArrayBuffer((offset,x4585), (size,Const(1024)), (isLoad,Const(false))))
    val x4587_b4613_b4611 = withCtrl(x4588) { WriteMem(b4611, x4586_x4585).name("x4587_b4613_b4611").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x4578,x4586,Const(true))
    val x4587_b4614_b4612 = withCtrl(x4588) { WriteMem(b4612, Const(1024)).name("x4587_b4614_b4612").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x4578,x4586,Const(true))
    val x4589 = withCtrl(x4599) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x4589").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x4590 = withCtrl(x4599) { CounterChain(List(x4589)).name("x4590").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterChainNew(List(x4589))
    val x4595 = withCtrl(x4599) { LoopController(style=InnerPipe, level=InnerControl, cchain=x4590).name("x4595").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnrolledForeach(List(Const(true)),x4590,Block(Const(())),List(List(b2439)),List(List(b2440)))
    val b2439 = withCtrl(x4595) { CounterIter(x4589, None).name("b2439") } // b2439
    val b2440 = withCtrl(x4595) { Const(true).name("b2440") } // b2440
    val x4591 = withCtrl(x4595) { LoadBanks(List(x4187_d0_b0), List(b2439)).name("x4591").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParSRAMLoad(x4187,List(List(b2439)),List(b2440))
    val x4593_x4592 = withCtrl(x4595) { x4591 } // VectorApply(x4591,0)
    // x4593 = SimpleStruct(ArrayBuffer((_1,x4592), (_2,Const(true))))
    val x4594_x4594_x4579 = withCtrl(x4595) { WriteMem(x4579, x4593_x4592).name("x4594_x4594_x4579").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParStreamWrite(x4579,List(x4593),List(b2440))
    val x4596 = withCtrl(x4599) { FringeDenseStore(dram=List(x4181), cmdStream=List(b4611, b4612), dataStream=List(x4579), ackStream=List(x4580)).name("x4596").srcCtx("DeepBenchLSTM256.scala:119:39") } // FringeDenseStore(x4181,x4578,x4579,x4580)
    val x4598 = withCtrl(x4599) { UnitController(style=SeqPipe, level=InnerControl).name("x4598").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x4597_x4597 = withCtrl(x4598) { ReadMem(x4580).name("x4597_x4597").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamRead(x4580,Const(true))
    }; split1
    
  }
}
