import pir._
import pir.node._
import arch._
import prism.enums._

object DBLSTM_256_1_1_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3149 = withCtrl(design.top.topController) { DRAM(dims=List(Const(256))).name("x3149").srcCtx("DeepBenchLSTM256.scala:57:23:dout") } // x3149 = DRAMNew(ArrayBuffer(Const(256)),Const(0))
    val x3370 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x3370").srcCtx("DeepBenchLSTM256.scala:59:11") } // Hwblock(Block(Const(())),false)
    val x3150_d0_b0 = withCtrl(x3370) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3150_d0_b0").srcCtx("DataGenerator.scala:248:40:wh") } // x3150 = LUTNew(List(256, 4, 256), Seq(Const(0),Const(0),Const(0)... [262141 more]))
    isAccum(x3150_d0_b0) = false
    bufferDepthOf(x3150_d0_b0) = 1
    staticDimsOf(x3150_d0_b0) = List(256, 4, 256)
    val x3151_d0_b0 = withCtrl(x3370) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3151_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt0") } // x3151 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x3151_d0_b0) = false
    bufferDepthOf(x3151_d0_b0) = 1
    staticDimsOf(x3151_d0_b0) = List(64, 4, 256)
    val x3152_d0_b0 = withCtrl(x3370) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3152_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt1") } // x3152 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x3152_d0_b0) = false
    bufferDepthOf(x3152_d0_b0) = 1
    staticDimsOf(x3152_d0_b0) = List(64, 4, 256)
    val x3153_d0_b0 = withCtrl(x3370) { LUT(inits=Nil, banking=Strided(banks=16, stride=1)).name("x3153_d0_b0").srcCtx("DataGenerator.scala:248:40:wxt2") } // x3153 = LUTNew(List(64, 4, 256), Seq(Const(0),Const(0),Const(0)... [65533 more]))
    isAccum(x3153_d0_b0) = false
    bufferDepthOf(x3153_d0_b0) = 1
    staticDimsOf(x3153_d0_b0) = List(64, 4, 256)
    val x3154_d0_b0 = withCtrl(x3370) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x3154_d0_b0").srcCtx("DeepBenchLSTM256.scala:86:25:cBuf") } // x3154 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x3154_d0_b0) = false
    bufferDepthOf(x3154_d0_b0) = 1
    staticDimsOf(x3154_d0_b0) = List(256)
    val x3155_d0_b0 = withCtrl(x3370) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x3155_d0_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x3155 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x3155_d0_b0) = false
    bufferDepthOf(x3155_d0_b0) = 1
    staticDimsOf(x3155_d0_b0) = List(256)
    val x3155_d1_b0 = withCtrl(x3370) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x3155_d1_b0").srcCtx("DeepBenchLSTM256.scala:87:25:hBuf") } // x3155 = SRAMNew(ArrayBuffer(Const(256)))
    isAccum(x3155_d1_b0) = false
    bufferDepthOf(x3155_d1_b0) = 1
    staticDimsOf(x3155_d1_b0) = List(256)
    val x3156 = withCtrl(x3370) { Counter(min=Const(0), max=Const(150), step=Const(1), par=1).name("x3156").srcCtx("DeepBenchLSTM256.scala:90:34") } // CounterNew(Const(0),Const(150),Const(1),Const(1))
    val x3157 = withCtrl(x3370) { CounterChain(List(x3156)).name("x3157").srcCtx("DeepBenchLSTM256.scala:90:40") } // CounterChainNew(List(x3156))
    val x3347 = withCtrl(x3370) { LoopController(style=SeqPipe, level=OuterControl, cchain=x3157).name("x3347").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnrolledForeach(List(Const(true)),x3157,Block(Const(())),List(List(b1968)),List(List(b1969)))
    val b1968 = withCtrl(x3347) { CounterIter(x3156, Some(0)).name("b1968") } // b1968
    val b1969 = withCtrl(x3347) { Const(true).name("b1969") } // b1969
    val x3158_d0_b0 = withCtrl(x3347) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3158_d0_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x3158 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3158_d0_b0) = false
    bufferDepthOf(x3158_d0_b0) = 1
    staticDimsOf(x3158_d0_b0) = List(4, 256)
    val x3158_d1_b0 = withCtrl(x3347) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3158_d1_b0").srcCtx("DeepBenchLSTM256.scala:91:32:reduceMem") } // x3158 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3158_d1_b0) = true
    bufferDepthOf(x3158_d1_b0) = 1
    staticDimsOf(x3158_d1_b0) = List(4, 256)
    val x3159_d0_b0 = withCtrl(x3347) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3159_d0_b0").srcCtx("DeepBenchLSTM256.scala:92:30:foldMem") } // x3159 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3159_d0_b0) = false
    bufferDepthOf(x3159_d0_b0) = 1
    staticDimsOf(x3159_d0_b0) = List(4, 256)
    val x3160_d0 = withCtrl(x3347) { Reg(init=Some(false)).name("x3160_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3160 = RegNew(Const(false))
    isAccum(x3160_d0) = false
    bufferDepthOf(x3160_d0) = 1
    val x3160_d1 = withCtrl(x3347) { Reg(init=Some(false)).name("x3160_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3160 = RegNew(Const(false))
    isAccum(x3160_d1) = false
    bufferDepthOf(x3160_d1) = 1
    val x3161 = withCtrl(x3347) { Reg(init=Some(false)).name("x3161").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3161 = RegNew(Const(false))
    isAccum(x3161) = false
    bufferDepthOf(x3161) = 1
    val x3162 = withCtrl(x3347) { Reg(init=Some(false)).name("x3162").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3162 = RegNew(Const(false))
    isAccum(x3162) = false
    bufferDepthOf(x3162) = 1
    val x3163 = withCtrl(x3347) { Reg(init=Some(0)).name("x3163").srcCtx("DeepBenchLSTM256.scala:90:40") } // x3163 = RegNew(Const(0))
    isAccum(x3163) = false
    bufferDepthOf(x3163) = 1
    val x3175 = withCtrl(x3347) { UnitController(style=SeqPipe, level=InnerControl).name("x3175").srcCtx("DeepBenchLSTM256.scala:90:40") } // UnitPipe(List(b1969),Block(Const(())))
    val x3164 = withCtrl(x3175) { OpDef(op=FixEql, inputs=List(b1968, Const(0))).name("x3164").srcCtx("DeepBenchLSTM256.scala:93:33:isInitStep") } // FixEql(b1968,Const(0))
    val x3165 = withCtrl(x3175) { OpDef(op=FixLt, inputs=List(b1968, Const(64))).name("x3165").srcCtx("VecMatMultBiasAdd.scala:154:28:isFstRange") } // FixLt(b1968,Const(64))
    val x3166 = withCtrl(x3175) { OpDef(op=FixLt, inputs=List(Const(64), b1968)).name("x3166").srcCtx("VecMatMultBiasAdd.scala:155:28:isLstRange") } // FixLt(Const(64),b1968)
    val x3167 = withCtrl(x3175) { OpDef(op=FixSub, inputs=List(b1968, Const(64))).name("x3167").srcCtx("VecMatMultBiasAdd.scala:157:24:midIdx") } // FixSub(b1968,Const(64))
    val x3168 = withCtrl(x3175) { OpDef(op=FixSub, inputs=List(b1968, Const(128))).name("x3168").srcCtx("VecMatMultBiasAdd.scala:158:24:lstIdx") } // FixSub(b1968,Const(128))
    val x3169 = withCtrl(x3175) { OpDef(op=MuxOp, inputs=List(x3166, x3168, x3167)).name("x3169").srcCtx("VecMatMultBiasAdd.scala:160:30") } // Mux(x3166,x3168,x3167)
    val x3170 = withCtrl(x3175) { OpDef(op=MuxOp, inputs=List(x3165, b1968, x3169)).name("x3170").srcCtx("VecMatMultBiasAdd.scala:159:25:iTileStep") } // Mux(x3165,b1968,x3169)
    val x3171_x3160_d0 = withCtrl(x3175) { WriteMem(x3160_d0, x3164).name("x3171_x3160_d0").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3160,x3164,b1969)
    val x3171_x3160_d1 = withCtrl(x3175) { WriteMem(x3160_d1, x3164).name("x3171_x3160_d1").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3160,x3164,b1969)
    val x3172_x3161 = withCtrl(x3175) { WriteMem(x3161, x3165).name("x3172_x3161").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3161,x3165,b1969)
    val x3173_x3162 = withCtrl(x3175) { WriteMem(x3162, x3166).name("x3173_x3162").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3162,x3166,b1969)
    val x3174_x3163 = withCtrl(x3175) { WriteMem(x3163, x3170).name("x3174_x3163").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegWrite(x3163,x3170,b1969)
    val x3176 = withCtrl(x3347) { Counter(min=Const(0), max=Const(256), step=Const(1), par=1).name("x3176").srcCtx("VecMatMultBiasAdd.scala:165:59") } // CounterNew(Const(0),Const(256),Const(1),Const(1))
    val x3177 = withCtrl(x3347) { CounterChain(List(x3176)).name("x3177").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(List(x3176))
    val x3215 = withCtrl(x3347) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3177).name("x3215").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledReduce(List(b1969),x3177,x3158,Block((x3158) => Const(())),List(List(b1993)),List(List(b1994)))
    val b1993 = withCtrl(x3215) { CounterIter(x3176, Some(0)).name("b1993") } // b1993
    val b1994 = withCtrl(x3215) { Const(true).name("b1994") } // b1994
    val x3178_d0_b0 = withCtrl(x3215) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3178_d0_b0").srcCtx("VecMatMultBiasAdd.scala:166:23:re") } // x3178 = SRAMNew(ArrayBuffer(Const(4), Const(256)))
    isAccum(x3178_d0_b0) = false
    bufferDepthOf(x3178_d0_b0) = 2
    staticDimsOf(x3178_d0_b0) = List(4, 256)
    val x3179 = withCtrl(x3215) { Reg(init=Some(0.0)).name("x3179").srcCtx("VecMatMultBiasAdd.scala:179:6") } // x3179 = RegNew(Const(0))
    isAccum(x3179) = false
    bufferDepthOf(x3179) = 2
    val x3185 = withCtrl(x3215) { UnitController(style=SeqPipe, level=InnerControl).name("x3185").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnitPipe(List(b1994, b1969),Block(Const(())))
    val x3180 = withCtrl(x3185) { OpDef(op=BitAnd, inputs=List(b1994, b1969)).name("x3180").srcCtx("UnrollingBase.scala:28:66") } // And(b1994,b1969)
    val x3181 = withCtrl(x3185) { LoadBanks(List(x3155_d1_b0), List(b1993)).name("x3181").srcCtx("VecMatMultBiasAdd.scala:168:34") } // SRAMLoad(x3155,ArrayBuffer(Const(256)),List(b1993),Const(0),x3180)
    val x3182 = withCtrl(x3185) { ReadMem(x3160_d1).name("x3182").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3160)
    val x3183 = withCtrl(x3185) { OpDef(op=MuxOp, inputs=List(x3182, Const(0.0), x3181)).name("x3183").srcCtx("VecMatMultBiasAdd.scala:167:21:hEle") } // Mux(x3182,Const(0),x3181)
    val x3184_x3179 = withCtrl(x3185) { WriteMem(x3179, x3183).name("x3184_x3179").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegWrite(x3179,x3183,x3180)
    val x3186 = withCtrl(x3215) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3186").srcCtx("VecMatMultBiasAdd.scala:171:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3187 = withCtrl(x3215) { CounterChain(List(x3186)).name("x3187").srcCtx("VecMatMultBiasAdd.scala:171:41") } // CounterChainNew(List(x3186))
    val x3198 = withCtrl(x3215) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3187).name("x3198").srcCtx("VecMatMultBiasAdd.scala:171:41") } // UnrolledForeach(List(b1994, b1969),x3187,Block(Const(())),List(List(b2005)),List(List(b2006)))
    val b2005 = withCtrl(x3198) { CounterIter(x3186, Some(0)).name("b2005") } // b2005
    val b2006 = withCtrl(x3198) { Const(true).name("b2006") } // b2006
    val x3188 = withCtrl(x3198) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3188").srcCtx("VecMatMultBiasAdd.scala:172:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3189 = withCtrl(x3198) { CounterChain(List(x3188)).name("x3189").srcCtx("VecMatMultBiasAdd.scala:172:50") } // CounterChainNew(List(x3188))
    val x3197 = withCtrl(x3198) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3189).name("x3197").srcCtx("VecMatMultBiasAdd.scala:172:50") } // UnrolledForeach(List(b2006, b1994, b1969),x3189,Block(Const(())),List(List(b2009)),List(List(b2010)))
    val b2009 = withCtrl(x3197) { CounterIter(x3188, None).name("b2009") } // b2009
    val b2010 = withCtrl(x3197) { Const(true).name("b2010") } // b2010
    val x3190 = withCtrl(x3197) { OpDef(op=BitAnd, inputs=List(b2010, b2006)).name("x3190").srcCtx("UnrollingBase.scala:28:66") } // And(b2010,b2006)
    val x3191 = withCtrl(x3197) { OpDef(op=BitAnd, inputs=List(b1994, b1969)).name("x3191").srcCtx("UnrollingBase.scala:28:66") } // And(b1994,b1969)
    val x3192 = withCtrl(x3197) { OpDef(op=BitAnd, inputs=List(x3190, x3191)).name("x3192").srcCtx("UnrollingBase.scala:28:66") } // And(x3190,x3191)
    val x3193 = withCtrl(x3197) { LoadBanks(List(x3150_d0_b0), List(b1993, b2005, b2009)).name("x3193").srcCtx("VecMatMultBiasAdd.scala:173:25:whEle") } // LUTLoad(x3150,List(b1993, b2005, b2009),x3192)
    val x3194 = withCtrl(x3197) { ReadMem(x3179).name("x3194").srcCtx("VecMatMultBiasAdd.scala:179:6") } // RegRead(x3179)
    val x3195 = withCtrl(x3197) { OpDef(op=FixMul, inputs=List(x3193, x3194)).name("x3195").srcCtx("VecMatMultBiasAdd.scala:174:42") } // FixMul(x3193,x3194)
    val x3196 = withCtrl(x3197) { StoreBanks(List(List(x3178_d0_b0)), List(b2005, b2009), x3195).name("x3196").srcCtx("VecMatMultBiasAdd.scala:174:34") } // ParSRAMStore(x3178,List(List(b2005, b2009)),List(x3195),List(x3192))
    val x3199 = withCtrl(x3215) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3199").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3200 = withCtrl(x3215) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3200").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3201 = withCtrl(x3215) { CounterChain(List(x3200,x3199)).name("x3201").srcCtx("VecMatMultBiasAdd.scala:179:6") } // CounterChainNew(ArrayBuffer(x3200, x3199))
    val x3214 = withCtrl(x3215) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3201).name("x3214").srcCtx("VecMatMultBiasAdd.scala:179:6") } // UnrolledForeach(List(),x3201,Block(Const(())),ArrayBuffer(List(b2020), List(b2021)),ArrayBuffer(List(b2022), List(b2023)))
    val b2020 = withCtrl(x3214) { CounterIter(x3200, Some(0)).name("b2020") } // b2020
    val b2022 = withCtrl(x3214) { Const(true).name("b2022") } // b2022
    val b2021 = withCtrl(x3214) { CounterIter(x3199, None).name("b2021") } // b2021
    val b2023 = withCtrl(x3214) { Const(true).name("b2023") } // b2023
    val x3202 = withCtrl(x3214) { OpDef(op=BitAnd, inputs=List(b2022, b2023)).name("x3202").srcCtx("UnrollingBase.scala:28:66") } // And(b2022,b2023)
    val x3203 = withCtrl(x3214) { OpDef(op=BitAnd, inputs=List(x3202, b1969)).name("x3203").srcCtx("UnrollingBase.scala:28:66") } // And(x3202,b1969)
    val x3204 = withCtrl(x3214) { LoadBanks(List(x3178_d0_b0), List(b2020, b2021)).name("x3204").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3178,List(ArrayBuffer(b2020, b2021)),List(x3203))
    val x3205 = withCtrl(x3214) { x3204 } // VectorApply(x3204,0)
    val x3206 = withCtrl(x3214) { LoadBanks(List(x3158_d1_b0), List(b2020, b2021)).name("x3206").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMLoad(x3158,List(ArrayBuffer(b2020, b2021)),List(x3203))
    val x3207 = withCtrl(x3214) { x3206 } // VectorApply(x3206,0)
    val x3208 = withCtrl(x3214) { OpDef(op=BitAnd, inputs=List(b1994, b1969)).name("x3208").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(b1994,b1969)
    val x3209 = withCtrl(x3214) { OpDef(op=BitAnd, inputs=List(x3208, x3203)).name("x3209").srcCtx("VecMatMultBiasAdd.scala:179:6") } // And(x3208,x3203)
    val x3210 = withCtrl(x3214) { OpDef(op=FixEql, inputs=List(b1993, Const(0))).name("x3210").srcCtx("VecMatMultBiasAdd.scala:179:6") } // FixEql(b1993,Const(0))
    val x3211 = withCtrl(x3214) { OpDef(op=FixAdd, inputs=List(x3205, x3207)).name("x3211").srcCtx("VecMatMultBiasAdd.scala:179:8") } // FixAdd(x3205,x3207)
    val x3212 = withCtrl(x3214) { OpDef(op=MuxOp, inputs=List(x3210, x3205, x3211)).name("x3212").srcCtx("VecMatMultBiasAdd.scala:179:6") } // Mux(x3210,x3205,x3211)
    val x3213 = withCtrl(x3214) { StoreBanks(List(List(x3158_d0_b0), List(x3158_d1_b0)), List(b2020, b2021), x3212).name("x3213").srcCtx("VecMatMultBiasAdd.scala:179:6") } // ParSRAMStore(x3158,List(ArrayBuffer(b2020, b2021)),List(x3212),List(x3203))
    antiDepsOf(x3213)=List(x3206)
    val x3216 = withCtrl(x3347) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3216").srcCtx("VecMatMultBiasAdd.scala:181:26") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3217 = withCtrl(x3347) { CounterChain(List(x3216)).name("x3217").srcCtx("VecMatMultBiasAdd.scala:181:42") } // CounterChainNew(List(x3216))
    val x3235 = withCtrl(x3347) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3217).name("x3235").srcCtx("VecMatMultBiasAdd.scala:181:42") } // UnrolledForeach(List(b1969),x3217,Block(Const(())),List(List(b2040)),List(List(b2041)))
    val b2040 = withCtrl(x3235) { CounterIter(x3216, Some(0)).name("b2040") } // b2040
    val b2041 = withCtrl(x3235) { Const(true).name("b2041") } // b2041
    val x3218 = withCtrl(x3235) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3218").srcCtx("VecMatMultBiasAdd.scala:182:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3219 = withCtrl(x3235) { CounterChain(List(x3218)).name("x3219").srcCtx("VecMatMultBiasAdd.scala:182:48") } // CounterChainNew(List(x3218))
    val x3234 = withCtrl(x3235) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3219).name("x3234").srcCtx("VecMatMultBiasAdd.scala:182:48") } // UnrolledForeach(List(b2041, b1969),x3219,Block(Const(())),List(List(b2044)),List(List(b2045)))
    val b2044 = withCtrl(x3234) { CounterIter(x3218, None).name("b2044") } // b2044
    val b2045 = withCtrl(x3234) { Const(true).name("b2045") } // b2045
    val x3220 = withCtrl(x3234) { ReadMem(x3163).name("x3220").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3163)
    val x3221 = withCtrl(x3234) { OpDef(op=BitAnd, inputs=List(b2045, b2041)).name("x3221").srcCtx("UnrollingBase.scala:28:66") } // And(b2045,b2041)
    val x3222 = withCtrl(x3234) { OpDef(op=BitAnd, inputs=List(x3221, b1969)).name("x3222").srcCtx("UnrollingBase.scala:28:66") } // And(x3221,b1969)
    val x3223 = withCtrl(x3234) { LoadBanks(List(x3151_d0_b0), List(x3220, b2040, b2044)).name("x3223").srcCtx("VecMatMultBiasAdd.scala:183:28:wxt0Ele") } // LUTLoad(x3151,List(x3220, b2040, b2044),x3222)
    val x3224 = withCtrl(x3234) { LoadBanks(List(x3152_d0_b0), List(x3220, b2040, b2044)).name("x3224").srcCtx("VecMatMultBiasAdd.scala:186:28:wxt1Ele") } // LUTLoad(x3152,List(x3220, b2040, b2044),x3222)
    val x3225 = withCtrl(x3234) { LoadBanks(List(x3153_d0_b0), List(x3220, b2040, b2044)).name("x3225").srcCtx("VecMatMultBiasAdd.scala:189:28:wxt2Ele") } // LUTLoad(x3153,List(x3220, b2040, b2044),x3222)
    val x3226 = withCtrl(x3234) { ReadMem(x3162).name("x3226").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3162)
    val x3227 = withCtrl(x3234) { OpDef(op=MuxOp, inputs=List(x3226, x3225, x3224)).name("x3227").srcCtx("VecMatMultBiasAdd.scala:193:36") } // Mux(x3226,x3225,x3224)
    val x3228 = withCtrl(x3234) { ReadMem(x3161).name("x3228").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3161)
    val x3229 = withCtrl(x3234) { OpDef(op=MuxOp, inputs=List(x3228, x3223, x3227)).name("x3229").srcCtx("VecMatMultBiasAdd.scala:192:26:wxtEle") } // Mux(x3228,x3223,x3227)
    val x3230 = withCtrl(x3234) { LoadBanks(List(x3158_d0_b0), List(b2040, b2044)).name("x3230").srcCtx("VecMatMultBiasAdd.scala:198:32") } // ParSRAMLoad(x3158,List(List(b2040, b2044)),List(x3222))
    val x3231 = withCtrl(x3234) { x3230 } // VectorApply(x3230,0)
    val x3232 = withCtrl(x3234) { OpDef(op=FixAdd, inputs=List(x3231, x3229)).name("x3232").srcCtx("VecMatMultBiasAdd.scala:198:53:foldVal") } // FixAdd(x3231,x3229)
    val x3233 = withCtrl(x3234) { StoreBanks(List(List(x3159_d0_b0)), List(b2040, b2044), x3232).name("x3233").srcCtx("VecMatMultBiasAdd.scala:199:37") } // ParSRAMStore(x3159,List(List(b2040, b2044)),List(x3232),List(x3222))
    val x3236 = withCtrl(x3347) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x3236").srcCtx("GateMetaPipe.scala:143:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x3237 = withCtrl(x3347) { CounterChain(List(x3236)).name("x3237").srcCtx("GateMetaPipe.scala:143:27") } // CounterChainNew(List(x3236))
    val x3346 = withCtrl(x3347) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3237).name("x3346").srcCtx("GateMetaPipe.scala:143:27") } // UnrolledForeach(List(b1969),x3237,Block(Const(())),List(List(b2064)),List(List(b2065)))
    val b2064 = withCtrl(x3346) { CounterIter(x3236, Some(0)).name("b2064") } // b2064
    val b2065 = withCtrl(x3346) { Const(true).name("b2065") } // b2065
    val x3238 = withCtrl(x3346) { FIFO(size=64).name("x3238").srcCtx("GateMetaPipe.scala:144:25:sigQ") } // x3238 = FIFONew(Const(64))
    isAccum(x3238) = false
    bufferDepthOf(x3238) = 2
    val x3239 = withCtrl(x3346) { FIFO(size=64).name("x3239").srcCtx("GateMetaPipe.scala:145:26:sigQQ") } // x3239 = FIFONew(Const(64))
    isAccum(x3239) = false
    bufferDepthOf(x3239) = 2
    val x3240 = withCtrl(x3346) { FIFO(size=64).name("x3240").srcCtx("GateMetaPipe.scala:146:31:sigEleMuxQ") } // x3240 = FIFONew(Const(64))
    isAccum(x3240) = false
    bufferDepthOf(x3240) = 2
    val x3241 = withCtrl(x3346) { FIFO(size=64).name("x3241").srcCtx("GateMetaPipe.scala:147:27:tanhQ") } // x3241 = FIFONew(Const(64))
    isAccum(x3241) = false
    bufferDepthOf(x3241) = 2
    val x3242 = withCtrl(x3346) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3242").srcCtx("GateMetaPipe.scala:149:34") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3243 = withCtrl(x3346) { CounterChain(List(x3242)).name("x3243").srcCtx("GateMetaPipe.scala:149:48") } // CounterChainNew(List(x3242))
    val x3265 = withCtrl(x3346) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3243).name("x3265").srcCtx("GateMetaPipe.scala:149:48") } // UnrolledForeach(List(b2065, b1969),x3243,Block(Const(())),List(List(b2072)),List(List(b2073)))
    val b2072 = withCtrl(x3265) { CounterIter(x3242, None).name("b2072") } // b2072
    val b2073 = withCtrl(x3265) { Const(true).name("b2073") } // b2073
    val x3244 = withCtrl(x3265) { OpDef(op=BitAnd, inputs=List(b2073, b2065)).name("x3244").srcCtx("UnrollingBase.scala:28:66") } // And(b2073,b2065)
    val x3245 = withCtrl(x3265) { OpDef(op=BitAnd, inputs=List(x3244, b1969)).name("x3245").srcCtx("UnrollingBase.scala:28:66") } // And(x3244,b1969)
    val x3246 = withCtrl(x3265) { LoadBanks(List(x3159_d0_b0), List(b2064, b2072)).name("x3246").srcCtx("GateMetaPipe.scala:150:27:pEle") } // ParSRAMLoad(x3159,List(List(b2064, b2072)),List(x3245))
    val x3247 = withCtrl(x3265) { x3246 } // VectorApply(x3246,0)
    val x3248_d0_b0 = withCtrl(x3265) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3248_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x3248 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x3248_d0_b0) = false
    bufferDepthOf(x3248_d0_b0) = 1
    staticDimsOf(x3248_d0_b0) = List(1024)
    val x3249 = withCtrl(x3265) { OpDef(op=FixSub, inputs=List(x3247, Const(-8.0))).name("x3249").srcCtx("NonLinearity.scala:44:22") } // FixSub(x3247,Const(-8))
    val x3250 = withCtrl(x3265) { OpDef(op=FixSla, inputs=List(x3249, Const(6))).name("x3250").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x3249,Const(6))
    // x3251 = FixConvert(x3250,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x3251 = withCtrl(x3265) { OpDef(op=FixSra, inputs=List(x3250, Const("24"))).name("x3251").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x3250,TRUE,_32,_0)
    // }
    val x3252 = withCtrl(x3265) { LoadBanks(List(x3248_d0_b0), List(x3251)).name("x3252").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x3248,List(x3251),x3245)
    val x3253_d0_b0 = withCtrl(x3265) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3253_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x3253 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x3253_d0_b0) = false
    bufferDepthOf(x3253_d0_b0) = 1
    staticDimsOf(x3253_d0_b0) = List(1024)
    val x3254 = withCtrl(x3265) { LoadBanks(List(x3253_d0_b0), List(x3251)).name("x3254").srcCtx("NonLinearity.scala:45:17:tanhVal") } // LUTLoad(x3253,List(x3251),x3245)
    val x3255 = withCtrl(x3265) { OpDef(op=FixLt, inputs=List(Const(8.0), x3247)).name("x3255").srcCtx("GateMetaPipe.scala:154:27:isHigh") } // FixLt(Const(8),x3247)
    val x3256 = withCtrl(x3265) { OpDef(op=FixLt, inputs=List(x3247, Const(-8.0))).name("x3256").srcCtx("GateMetaPipe.scala:155:26:isLow") } // FixLt(x3247,Const(-8))
    val x3257 = withCtrl(x3265) { OpDef(op=MuxOp, inputs=List(x3256, Const(0.0), x3252)).name("x3257").srcCtx("GateMetaPipe.scala:157:46") } // Mux(x3256,Const(0),x3252)
    val x3258 = withCtrl(x3265) { OpDef(op=MuxOp, inputs=List(x3255, Const(1.0), x3257)).name("x3258").srcCtx("GateMetaPipe.scala:157:25:sigEle") } // Mux(x3255,Const(1),x3257)
    val x3259 = withCtrl(x3265) { OpDef(op=MuxOp, inputs=List(x3256, Const(-1.0), x3254)).name("x3259").srcCtx("GateMetaPipe.scala:158:47") } // Mux(x3256,Const(-1),x3254)
    val x3260 = withCtrl(x3265) { OpDef(op=MuxOp, inputs=List(x3255, Const(1.0), x3259)).name("x3260").srcCtx("GateMetaPipe.scala:158:26:tanhEle") } // Mux(x3255,Const(1),x3259)
    val x3261_x3238 = withCtrl(x3265) { WriteMem(x3238, x3258).name("x3261_x3238").srcCtx("GateMetaPipe.scala:160:17") } // ParFIFOEnq(x3238,List(x3258),List(x3245))
    val x3262_x3239 = withCtrl(x3265) { WriteMem(x3239, x3258).name("x3262_x3239").srcCtx("GateMetaPipe.scala:161:18") } // ParFIFOEnq(x3239,List(x3258),List(x3245))
    val x3263_x3240 = withCtrl(x3265) { WriteMem(x3240, x3258).name("x3263_x3240").srcCtx("GateMetaPipe.scala:162:23") } // ParFIFOEnq(x3240,List(x3258),List(x3245))
    val x3264_x3241 = withCtrl(x3265) { WriteMem(x3241, x3260).name("x3264_x3241").srcCtx("GateMetaPipe.scala:164:18") } // ParFIFOEnq(x3241,List(x3260),List(x3245))
    val x3345 = withCtrl(x3346) { UnitController(style=SeqPipe, level=OuterControl).name("x3345").srcCtx("GateMetaPipe.scala:167:12") } // UnitPipe(List(b2065, b1969),Block(Const(())))
    val x3266 = withCtrl(x3345) { FIFO(size=64).name("x3266").srcCtx("GateMetaPipe.scala:169:29:cLastQ") } // x3266 = FIFONew(Const(64))
    isAccum(x3266) = false
    bufferDepthOf(x3266) = 1
    val x3267 = withCtrl(x3345) { FIFO(size=64).name("x3267").srcCtx("GateMetaPipe.scala:170:35:cNewMultAddQ") } // x3267 = FIFONew(Const(64))
    isAccum(x3267) = false
    bufferDepthOf(x3267) = 1
    val x3268 = withCtrl(x3345) { FIFO(size=64).name("x3268").srcCtx("GateMetaPipe.scala:171:36:cNewMultAddQQ") } // x3268 = FIFONew(Const(64))
    isAccum(x3268) = false
    bufferDepthOf(x3268) = 1
    val x3269 = withCtrl(x3345) { FIFO(size=64).name("x3269").srcCtx("GateMetaPipe.scala:172:32:cNewMultQ") } // x3269 = FIFONew(Const(64))
    isAccum(x3269) = false
    bufferDepthOf(x3269) = 1
    val x3270 = withCtrl(x3345) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3270").srcCtx("GateMetaPipe.scala:174:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3271 = withCtrl(x3345) { CounterChain(List(x3270)).name("x3271").srcCtx("GateMetaPipe.scala:174:50") } // CounterChainNew(List(x3270))
    val x3291 = withCtrl(x3345) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3271).name("x3291").srcCtx("GateMetaPipe.scala:174:50") } // UnrolledForeach(List(b2065, b1969),x3271,Block(Const(())),List(List(b2102)),List(List(b2103)))
    val b2102 = withCtrl(x3291) { CounterIter(x3270, None).name("b2102") } // b2102
    val b2103 = withCtrl(x3291) { Const(true).name("b2103") } // b2103
    val x3272 = withCtrl(x3291) { OpDef(op=BitAnd, inputs=List(b2103, b2065)).name("x3272").srcCtx("UnrollingBase.scala:28:66") } // And(b2103,b2065)
    val x3273 = withCtrl(x3291) { OpDef(op=BitAnd, inputs=List(x3272, b1969)).name("x3273").srcCtx("UnrollingBase.scala:28:66") } // And(x3272,b1969)
    val x3274 = withCtrl(x3291) { ReadMem(x3238).name("x3274").srcCtx("GateMetaPipe.scala:175:32:sigEle") } // ParFIFODeq(x3238,List(x3273))
    val x3275 = withCtrl(x3291) { x3274 } // VectorApply(x3274,0)
    val x3276 = withCtrl(x3291) { ReadMem(x3241).name("x3276").srcCtx("GateMetaPipe.scala:176:34:tanhEle") } // ParFIFODeq(x3241,List(x3273))
    val x3277 = withCtrl(x3291) { x3276 } // VectorApply(x3276,0)
    val x3278 = withCtrl(x3291) { OpDef(op=FixEql, inputs=List(b2064, Const(0))).name("x3278").srcCtx("package.scala:110:40") } // FixEql(b2064,Const(0))
    val x3279 = withCtrl(x3291) { ReadMem(x3160_d0).name("x3279").srcCtx("DeepBenchLSTM256.scala:90:40") } // RegRead(x3160)
    val x3280 = withCtrl(x3291) { OpDef(op=BitAnd, inputs=List(x3279, x3278)).name("x3280").srcCtx("GateMetaPipe.scala:177:36:isInitC") } // And(x3279,x3278)
    val x3281 = withCtrl(x3291) { LoadBanks(List(x3154_d0_b0), List(b2102)).name("x3281").srcCtx("GateMetaPipe.scala:179:35") } // ParSRAMLoad(x3154,List(List(b2102)),List(x3273))
    val x3282 = withCtrl(x3291) { x3281 } // VectorApply(x3281,0)
    val x3283 = withCtrl(x3291) { OpDef(op=MuxOp, inputs=List(x3280, Const(0.0), x3282)).name("x3283").srcCtx("GateMetaPipe.scala:178:26:cLast") } // Mux(x3280,Const(0),x3282)
    val x3284 = withCtrl(x3291) { OpDef(op=FixMul, inputs=List(x3283, x3277)).name("x3284").srcCtx("GateMetaPipe.scala:181:32:cNewMult") } // FixMul(x3283,x3277)
    val x3285 = withCtrl(x3291) { OpDef(op=FixMul, inputs=List(x3275, x3283)).name("x3285").srcCtx("GateMetaPipe.scala:182:36") } // FixMul(x3275,x3283)
    val x3286 = withCtrl(x3291) { OpDef(op=FixAdd, inputs=List(x3285, x3284)).name("x3286").srcCtx("GateMetaPipe.scala:182:44:cNewMultAdd") } // FixAdd(x3285,x3284)
    val x3287_x3269 = withCtrl(x3291) { WriteMem(x3269, x3284).name("x3287_x3269").srcCtx("GateMetaPipe.scala:184:24") } // ParFIFOEnq(x3269,List(x3284),List(x3273))
    val x3288_x3267 = withCtrl(x3291) { WriteMem(x3267, x3286).name("x3288_x3267").srcCtx("GateMetaPipe.scala:185:27") } // ParFIFOEnq(x3267,List(x3286),List(x3273))
    val x3289_x3268 = withCtrl(x3291) { WriteMem(x3268, x3286).name("x3289_x3268").srcCtx("GateMetaPipe.scala:186:28") } // ParFIFOEnq(x3268,List(x3286),List(x3273))
    val x3290_x3266 = withCtrl(x3291) { WriteMem(x3266, x3283).name("x3290_x3266").srcCtx("GateMetaPipe.scala:187:21") } // ParFIFOEnq(x3266,List(x3283),List(x3273))
    val x3292 = withCtrl(x3345) { FIFO(size=64).name("x3292").srcCtx("GateMetaPipe.scala:190:31:cUpdateQ") } // x3292 = FIFONew(Const(64))
    isAccum(x3292) = false
    bufferDepthOf(x3292) = 1
    val x3293 = withCtrl(x3345) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3293").srcCtx("GateMetaPipe.scala:191:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3294 = withCtrl(x3345) { CounterChain(List(x3293)).name("x3294").srcCtx("GateMetaPipe.scala:191:50") } // CounterChainNew(List(x3293))
    val x3312 = withCtrl(x3345) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3294).name("x3312").srcCtx("GateMetaPipe.scala:191:50") } // UnrolledForeach(List(b2065, b1969),x3294,Block(Const(())),List(List(b2127)),List(List(b2128)))
    val b2127 = withCtrl(x3312) { CounterIter(x3293, None).name("b2127") } // b2127
    val b2128 = withCtrl(x3312) { Const(true).name("b2128") } // b2128
    val x3295 = withCtrl(x3312) { OpDef(op=BitAnd, inputs=List(b2128, b2065)).name("x3295").srcCtx("UnrollingBase.scala:28:66") } // And(b2128,b2065)
    val x3296 = withCtrl(x3312) { OpDef(op=BitAnd, inputs=List(x3295, b1969)).name("x3296").srcCtx("UnrollingBase.scala:28:66") } // And(x3295,b1969)
    val x3297 = withCtrl(x3312) { ReadMem(x3269).name("x3297").srcCtx("GateMetaPipe.scala:192:39:cNewMult") } // ParFIFODeq(x3269,List(x3296))
    val x3298 = withCtrl(x3312) { x3297 } // VectorApply(x3297,0)
    val x3299 = withCtrl(x3312) { ReadMem(x3240).name("x3299").srcCtx("GateMetaPipe.scala:193:38:sigEle") } // ParFIFODeq(x3240,List(x3296))
    val x3300 = withCtrl(x3312) { x3299 } // VectorApply(x3299,0)
    val x3301 = withCtrl(x3312) { ReadMem(x3267).name("x3301").srcCtx("GateMetaPipe.scala:194:45:cNewMultAdd") } // ParFIFODeq(x3267,List(x3296))
    val x3302 = withCtrl(x3312) { x3301 } // VectorApply(x3301,0)
    val x3303 = withCtrl(x3312) { ReadMem(x3266).name("x3303").srcCtx("GateMetaPipe.scala:195:33:cLast") } // ParFIFODeq(x3266,List(x3296))
    val x3304 = withCtrl(x3312) { x3303 } // VectorApply(x3303,0)
    val x3305 = withCtrl(x3312) { OpDef(op=FixEql, inputs=List(b2064, Const(0))).name("x3305").srcCtx("package.scala:110:40") } // FixEql(b2064,Const(0))
    val x3306 = withCtrl(x3312) { OpDef(op=FixEql, inputs=List(b2064, Const(1))).name("x3306").srcCtx("package.scala:113:40") } // FixEql(b2064,Const(1))
    val x3307 = withCtrl(x3312) { OpDef(op=FixEql, inputs=List(b2064, Const(2))).name("x3307").srcCtx("package.scala:116:40") } // FixEql(b2064,Const(2))
    val x3308 = withCtrl(x3312) { OpDef(op=MuxOp, inputs=List(x3307, x3302, x3304)).name("x3308").srcCtx("GateMetaPipe.scala:200:40") } // Mux(x3307,x3302,x3304)
    val x3309 = withCtrl(x3312) { OpDef(op=MuxOp, inputs=List(x3306, x3298, x3308)).name("x3309").srcCtx("GateMetaPipe.scala:199:36") } // Mux(x3306,x3298,x3308)
    val x3310 = withCtrl(x3312) { OpDef(op=MuxOp, inputs=List(x3305, x3300, x3309)).name("x3310").srcCtx("GateMetaPipe.scala:198:28:cUpdate") } // Mux(x3305,x3300,x3309)
    val x3311_x3292 = withCtrl(x3312) { WriteMem(x3292, x3310).name("x3311_x3292").srcCtx("GateMetaPipe.scala:206:23") } // ParFIFOEnq(x3292,List(x3310),List(x3296))
    val x3313 = withCtrl(x3345) { FIFO(size=64).name("x3313").srcCtx("GateMetaPipe.scala:209:31:hLinearQ") } // x3313 = FIFONew(Const(64))
    isAccum(x3313) = false
    bufferDepthOf(x3313) = 1
    val x3314 = withCtrl(x3345) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3314").srcCtx("GateMetaPipe.scala:210:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3315 = withCtrl(x3345) { CounterChain(List(x3314)).name("x3315").srcCtx("GateMetaPipe.scala:210:50") } // CounterChainNew(List(x3314))
    val x3330 = withCtrl(x3345) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3315).name("x3330").srcCtx("GateMetaPipe.scala:210:50") } // UnrolledForeach(List(b2065, b1969),x3315,Block(Const(())),List(List(b2150)),List(List(b2151)))
    val b2150 = withCtrl(x3330) { CounterIter(x3314, None).name("b2150") } // b2150
    val b2151 = withCtrl(x3330) { Const(true).name("b2151") } // b2151
    val x3316 = withCtrl(x3330) { OpDef(op=BitAnd, inputs=List(b2151, b2065)).name("x3316").srcCtx("UnrollingBase.scala:28:66") } // And(b2151,b2065)
    val x3317 = withCtrl(x3330) { OpDef(op=BitAnd, inputs=List(x3316, b1969)).name("x3317").srcCtx("UnrollingBase.scala:28:66") } // And(x3316,b1969)
    val x3318 = withCtrl(x3330) { ReadMem(x3268).name("x3318").srcCtx("GateMetaPipe.scala:211:46:cNewMultAdd") } // ParFIFODeq(x3268,List(x3317))
    val x3319 = withCtrl(x3330) { x3318 } // VectorApply(x3318,0)
    val x3320_d0_b0 = withCtrl(x3330) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x3320_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x3320 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x3320_d0_b0) = false
    bufferDepthOf(x3320_d0_b0) = 1
    staticDimsOf(x3320_d0_b0) = List(1024)
    val x3321 = withCtrl(x3330) { OpDef(op=FixSub, inputs=List(x3319, Const(-8.0))).name("x3321").srcCtx("NonLinearity.scala:44:22") } // FixSub(x3319,Const(-8))
    val x3322 = withCtrl(x3330) { OpDef(op=FixSla, inputs=List(x3321, Const(6))).name("x3322").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x3321,Const(6))
    // x3323 = FixConvert(x3322,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x3323 = withCtrl(x3330) { OpDef(op=FixSra, inputs=List(x3322, Const("24"))).name("x3323").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x3322,TRUE,_32,_0)
    // }
    val x3324 = withCtrl(x3330) { LoadBanks(List(x3320_d0_b0), List(x3323)).name("x3324").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x3320,List(x3323),x3317)
    val x3325 = withCtrl(x3330) { OpDef(op=FixLt, inputs=List(Const(8.0), x3319)).name("x3325").srcCtx("GateMetaPipe.scala:214:36:isHigh") } // FixLt(Const(8),x3319)
    val x3326 = withCtrl(x3330) { OpDef(op=FixLt, inputs=List(x3319, Const(-8.0))).name("x3326").srcCtx("GateMetaPipe.scala:215:35:isLow") } // FixLt(x3319,Const(-8))
    val x3327 = withCtrl(x3330) { OpDef(op=MuxOp, inputs=List(x3326, Const(-1.0), x3324)).name("x3327").srcCtx("GateMetaPipe.scala:217:33") } // Mux(x3326,Const(-1),x3324)
    val x3328 = withCtrl(x3330) { OpDef(op=MuxOp, inputs=List(x3325, Const(1.0), x3327)).name("x3328").srcCtx("GateMetaPipe.scala:216:28:hLinear") } // Mux(x3325,Const(1),x3327)
    val x3329_x3313 = withCtrl(x3330) { WriteMem(x3313, x3328).name("x3329_x3313").srcCtx("GateMetaPipe.scala:222:23") } // ParFIFOEnq(x3313,List(x3328),List(x3317))
    val x3331 = withCtrl(x3345) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3331").srcCtx("GateMetaPipe.scala:225:36") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3332 = withCtrl(x3345) { CounterChain(List(x3331)).name("x3332").srcCtx("GateMetaPipe.scala:225:50") } // CounterChainNew(List(x3331))
    val x3344 = withCtrl(x3345) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3332).name("x3344").srcCtx("GateMetaPipe.scala:225:50") } // UnrolledForeach(List(b2065, b1969),x3332,Block(Const(())),List(List(b2169)),List(List(b2170)))
    val b2169 = withCtrl(x3344) { CounterIter(x3331, None).name("b2169") } // b2169
    val b2170 = withCtrl(x3344) { Const(true).name("b2170") } // b2170
    val x3333 = withCtrl(x3344) { OpDef(op=BitAnd, inputs=List(b2170, b2065)).name("x3333").srcCtx("UnrollingBase.scala:28:66") } // And(b2170,b2065)
    val x3334 = withCtrl(x3344) { OpDef(op=BitAnd, inputs=List(x3333, b1969)).name("x3334").srcCtx("UnrollingBase.scala:28:66") } // And(x3333,b1969)
    val x3335 = withCtrl(x3344) { ReadMem(x3292).name("x3335").srcCtx("GateMetaPipe.scala:229:34:cNew") } // ParFIFODeq(x3292,List(x3334))
    val x3336 = withCtrl(x3344) { x3335 } // VectorApply(x3335,0)
    val x3337 = withCtrl(x3344) { ReadMem(x3313).name("x3337").srcCtx("GateMetaPipe.scala:230:37:hLinear") } // ParFIFODeq(x3313,List(x3334))
    val x3338 = withCtrl(x3344) { x3337 } // VectorApply(x3337,0)
    val x3339 = withCtrl(x3344) { ReadMem(x3239).name("x3339").srcCtx("GateMetaPipe.scala:231:33:sigEle") } // ParFIFODeq(x3239,List(x3334))
    val x3340 = withCtrl(x3344) { x3339 } // VectorApply(x3339,0)
    val x3341 = withCtrl(x3344) { OpDef(op=FixMul, inputs=List(x3338, x3340)).name("x3341").srcCtx("GateMetaPipe.scala:232:30:hNew") } // FixMul(x3338,x3340)
    val x3342 = withCtrl(x3344) { StoreBanks(List(List(x3155_d0_b0), List(x3155_d1_b0)), List(b2169), x3341).name("x3342").srcCtx("GateMetaPipe.scala:234:29") } // ParSRAMStore(x3155,List(List(b2169)),List(x3341),List(x3334))
    val x3343 = withCtrl(x3344) { StoreBanks(List(List(x3154_d0_b0)), List(b2169), x3336).name("x3343").srcCtx("GateMetaPipe.scala:235:29") } // ParSRAMStore(x3154,List(List(b2169)),List(x3336),List(x3334))
    val x3369 = withCtrl(x3370) { UnitController(style=StreamPipe, level=OuterControl).name("x3369").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b3381 = withCtrl(x3369) { StreamOut(field="offset").name("b3381").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3348 = StreamOutNew(BurstCmdBus)
    isAccum(b3381) = false
    bufferDepthOf(b3381) = 1
    val b3382 = withCtrl(x3369) { StreamOut(field="size").name("b3382").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3348 = StreamOutNew(BurstCmdBus)
    isAccum(b3382) = false
    bufferDepthOf(b3382) = 1
    val x3349 = withCtrl(x3369) { StreamOut(field="data").name("x3349").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3349 = StreamOutNew(BurstFullDataBus())
    isAccum(x3349) = false
    bufferDepthOf(x3349) = 1
    val x3350 = withCtrl(x3369) { StreamIn(field="ack").name("x3350").srcCtx("DeepBenchLSTM256.scala:119:39") } // x3350 = StreamInNew(BurstAckBus)
    isAccum(x3350) = false
    bufferDepthOf(x3350) = 1
    val x3358 = withCtrl(x3369) { UnitController(style=SeqPipe, level=InnerControl).name("x3358").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(x3357))
    val x3351 = withCtrl(x3358) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x3352 = withCtrl(x3358) { OpDef(op=FixSla, inputs=List(x3351, Const(2))).name("x3352").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixLsh(x3351,Const(2))
    val x3353 = withCtrl(x3358) { x3352 } // FixConvert(x3352,TRUE,_64,_0)
    val x3354 = withCtrl(x3358) { DramAddress(x3149).name("x3354").srcCtx("DeepBenchLSTM256.scala:119:39") } // GetDRAMAddress(x3149)
    val x3356_x3355 = withCtrl(x3358) { OpDef(op=FixAdd, inputs=List(x3353, x3354)).name("x3356_x3355").srcCtx("DeepBenchLSTM256.scala:119:39") } // FixAdd(x3353,x3354)
    // x3356 = SimpleStruct(ArrayBuffer((offset,x3355), (size,Const(1024)), (isLoad,Const(false))))
    val x3357_b3383_b3381 = withCtrl(x3358) { WriteMem(b3381, x3356_x3355).name("x3357_b3383_b3381").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x3348,x3356,Const(true))
    val x3357_b3384_b3382 = withCtrl(x3358) { WriteMem(b3382, Const(1024)).name("x3357_b3384_b3382").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamWrite(x3348,x3356,Const(true))
    val x3359 = withCtrl(x3369) { Counter(min=Const(0), max=Const(256), step=Const(1), par=16).name("x3359").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterNew(Const(0),Const(256),Const(1),Const(16))
    val x3360 = withCtrl(x3369) { CounterChain(List(x3359)).name("x3360").srcCtx("DeepBenchLSTM256.scala:119:39") } // CounterChainNew(List(x3359))
    val x3365 = withCtrl(x3369) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3360).name("x3365").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnrolledForeach(List(Const(true)),x3360,Block(Const(())),List(List(b2199)),List(List(b2200)))
    val b2199 = withCtrl(x3365) { CounterIter(x3359, None).name("b2199") } // b2199
    val b2200 = withCtrl(x3365) { Const(true).name("b2200") } // b2200
    val x3361 = withCtrl(x3365) { LoadBanks(List(x3155_d0_b0), List(b2199)).name("x3361").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParSRAMLoad(x3155,List(List(b2199)),List(b2200))
    val x3363_x3362 = withCtrl(x3365) { x3361 } // VectorApply(x3361,0)
    // x3363 = SimpleStruct(ArrayBuffer((_1,x3362), (_2,Const(true))))
    val x3364_x3364_x3349 = withCtrl(x3365) { WriteMem(x3349, x3363_x3362).name("x3364_x3364_x3349").srcCtx("DeepBenchLSTM256.scala:119:39") } // ParStreamWrite(x3349,List(x3363),List(b2200))
    val x3366 = withCtrl(x3369) { FringeDenseStore(dram=List(x3149), cmdStream=List(b3381, b3382), dataStream=List(x3349), ackStream=List(x3350)).name("x3366").srcCtx("DeepBenchLSTM256.scala:119:39") } // FringeDenseStore(x3149,x3348,x3349,x3350)
    val x3368 = withCtrl(x3369) { UnitController(style=SeqPipe, level=InnerControl).name("x3368").srcCtx("DeepBenchLSTM256.scala:119:39") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3367_x3367 = withCtrl(x3368) { ReadMem(x3350).name("x3367_x3367").srcCtx("DeepBenchLSTM256.scala:119:39") } // StreamRead(x3350,Const(true))
    
  }
}
