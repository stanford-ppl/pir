import pir._
import pir.node._
import arch._
import prism.enums._

object SPMV_CRS extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5130 = DRAM().name("x5130").ctrl(top) // x5130 = DRAMNew(ArrayBuffer(Const(1666)),Const(0))
    val x5131 = DRAM().name("x5131").ctrl(top) // x5131 = DRAMNew(ArrayBuffer(Const(1666)),Const(0))
    val x5132 = DRAM().name("x5132").ctrl(top) // x5132 = DRAMNew(ArrayBuffer(Const(495)),Const(0))
    val x5133 = DRAM().name("x5133").ctrl(top) // x5133 = DRAMNew(ArrayBuffer(Const(494)),Const(0))
    val x5134 = DRAM().name("x5134").ctrl(top) // x5134 = DRAMNew(ArrayBuffer(Const(494)),Const(0))
    val x5135 = ArgIn(init=0).name("x5135").ctrl(top) // ArgInNew(Const(0))
    boundOf(x5135) = 494
    val x5550 = UnitController(style=SeqPipe, level=OuterControl).name("x5550").ctrl(top) // Hwblock(Block(Const(())),false)
    val x5152 = Reg(init=Some(0)).name("x5152").ctrl(x5550) // x5152 = RegNew(Const(0))
    isAccum(x5152) = false
    bufferDepthOf(x5152) = 1
    val x5156 = UnitController(style=SeqPipe, level=InnerControl).name("x5156").ctrl(x5550) // UnitPipe(List(Const(true)),Block(Const(())))
    val x5153 = ReadMem(x5135).name("x5153").ctrl(x5156) // RegRead(x5135)
    val x5154 = OpDef(op=FixDiv, inputs=List(x5153, Const(494))).name("x5154").ctrl(x5156) // FixDiv(x5153,Const(494))
    val x5155_x5152 = WriteMem(x5152, x5154).name("x5155_x5152").ctrl(x5156) // RegWrite(x5152,x5154,Const(true))
    val x5157 = ReadMem(x5152).name("x5157").ctrl(x5550) // RegRead(x5152)
    val x5158 = Counter(min=Const(0), max=x5157, step=Const(1), par=1).name("x5158").ctrl(x5550) // CounterNew(Const(0),x5157,Const(1),Const(1))
    val x5159 = CounterChain(List(x5158)).name("x5159").ctrl(x5550) // CounterChainNew(List(x5158))
    val x5549 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5159).name("x5549").ctrl(x5550) // UnrolledForeach(List(Const(true)),x5159,Block(Const(())),List(List(b2865)),List(List(b2866)))
    val b2865 = CounterIter(x5158, Some(0)).ctrl(x5549).name("b2865")
    val b2866 = DummyOp().ctrl(x5549).name("b2866")
    val x5160_d0_b0 = SRAM(size=30, banking=Strided(banks=16, stride=1)).name("x5160_d0_b0").ctrl(x5549) // x5160 = SRAMNew(ArrayBuffer(Const(495)))
    isAccum(x5160_d0_b0) = false
    bufferDepthOf(x5160_d0_b0) = 2
    val x5160_d1_b0 = SRAM(size=30, banking=Strided(banks=16, stride=1)).name("x5160_d1_b0").ctrl(x5549) // x5160 = SRAMNew(ArrayBuffer(Const(495)))
    isAccum(x5160_d1_b0) = false
    bufferDepthOf(x5160_d1_b0) = 2
    val x5161_d0_b0 = SRAM(size=494, banking=NoBanking()).name("x5161_d0_b0").ctrl(x5549) // x5161 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x5161_d0_b0) = false
    bufferDepthOf(x5161_d0_b0) = 3
    val x5162 = Reg(init=Some(0)).name("x5162").ctrl(x5549) // x5162 = RegNew(Const(0))
    isAccum(x5162) = false
    bufferDepthOf(x5162) = 2
    val x5163 = Reg(init=Some(0)).name("x5163").ctrl(x5549) // x5163 = RegNew(Const(0))
    isAccum(x5163) = false
    bufferDepthOf(x5163) = 4
    val x5164 = Reg(init=Some(0)).name("x5164").ctrl(x5549) // x5164 = RegNew(Const(0))
    isAccum(x5164) = false
    bufferDepthOf(x5164) = 2
    val x5172 = UnitController(style=SeqPipe, level=InnerControl).name("x5172").ctrl(x5549) // UnitPipe(List(b2866),Block(Const(())))
    val x5165 = OpDef(op=FixMul, inputs=List(b2865, Const(495))).name("x5165").ctrl(x5172) // FixMul(b2865,Const(495))
    val x5166 = OpDef(op=FixAdd, inputs=List(b2865, Const(1))).name("x5166").ctrl(x5172) // FixAdd(b2865,Const(1))
    val x5167 = OpDef(op=FixMul, inputs=List(x5166, Const(495))).name("x5167").ctrl(x5172) // FixMul(x5166,Const(495))
    val x5168 = OpDef(op=FixSub, inputs=List(x5167, x5165)).name("x5168").ctrl(x5172) // FixSub(x5167,x5165)
    val x5169_x5162 = WriteMem(x5162, x5165).name("x5169_x5162").ctrl(x5172) // RegWrite(x5162,x5165,b2866)
    val x5170_x5163 = WriteMem(x5163, x5166).name("x5170_x5163").ctrl(x5172) // RegWrite(x5163,x5166,b2866)
    val x5171_x5164 = WriteMem(x5164, x5168).name("x5171_x5164").ctrl(x5172) // RegWrite(x5164,x5168,b2866)
    val x5236 = UnitController(style=StreamPipe, level=OuterControl).name("x5236").ctrl(x5549) // UnitPipe(List(b2866),Block(Const(())))
    val b5587 = StreamOut(field="offset").name("b5587").ctrl(x5236) // x5173 = StreamOutNew(BurstCmdBus)
    val b5588 = StreamOut(field="size").name("b5588").ctrl(x5236) // x5173 = StreamOutNew(BurstCmdBus)
    val b5589 = FIFO(size=16).name("b5589").ctrl(x5236) // x5174 = FIFONew(Const(16))
    isAccum(b5589) = false
    bufferDepthOf(b5589) = 2
    val b5590 = FIFO(size=16).name("b5590").ctrl(x5236) // x5174 = FIFONew(Const(16))
    isAccum(b5590) = false
    bufferDepthOf(b5590) = 2
    val b5591 = FIFO(size=16).name("b5591").ctrl(x5236) // x5174 = FIFONew(Const(16))
    isAccum(b5591) = false
    bufferDepthOf(b5591) = 2
    val x5175 = StreamIn(field="data").name("x5175").ctrl(x5236) // x5175 = StreamInNew(BurstDataBus())
    val x5207 = UnitController(style=SeqPipe, level=InnerControl).name("x5207").ctrl(x5236) // UnitPipe(List(b2866),Block(x5206))
    val x5176 = ReadMem(x5162).name("x5176").ctrl(x5207) // RegRead(x5162)
    val x5177 = x5176 // FixConvert(x5176,TRUE,_32,_0)
    val x5178 = OpDef(op=FixSla, inputs=List(x5177, Const(2))).name("x5178").ctrl(x5207) // FixLsh(x5177,Const(2))
    val x5179 = x5178 // x5179 = DataAsBits(x5178)
    val x5180 = OpDef(op=BitAnd, inputs=List(x5179, Const(31))).name("x5180").ctrl(x5207) // VectorSlice(x5179,5,0) strMask=00000000000000000000000000011111
    val x5181 = x5180 // x5181 = BitsAsData(x5180,FixPt[TRUE,_32,_0])
    val x5182 = ReadMem(x5164).name("x5182").ctrl(x5207) // RegRead(x5164)
    val x5183 = OpDef(op=FixSla, inputs=List(x5182, Const(2))).name("x5183").ctrl(x5207) // FixLsh(x5182,Const(2))
    val x5184 = OpDef(op=FixSub, inputs=List(x5178, x5181)).name("x5184").ctrl(x5207) // FixSub(x5178,x5181)
    val x5185 = OpDef(op=FixAdd, inputs=List(x5178, x5183)).name("x5185").ctrl(x5207) // FixAdd(x5178,x5183)
    val x5186 = x5185 // x5186 = DataAsBits(x5185)
    val x5187 = OpDef(op=BitAnd, inputs=List(x5186, Const(31))).name("x5187").ctrl(x5207) // VectorSlice(x5186,5,0) strMask=00000000000000000000000000011111
    val x5188 = x5187 // x5188 = BitsAsData(x5187,FixPt[TRUE,_32,_0])
    val x5189 = OpDef(op=FixEql, inputs=List(x5188, Const(0))).name("x5189").ctrl(x5207) // FixEql(x5188,Const(0))
    val x5190 = OpDef(op=FixSub, inputs=List(Const(64), x5188)).name("x5190").ctrl(x5207) // FixSub(Const(64),x5188)
    val x5191 = OpDef(op=MuxOp, inputs=List(x5189, Const(0), x5190)).name("x5191").ctrl(x5207) // Mux(x5189,Const(0),x5190)
    val x5205_x5192 = OpDef(op=FixSra, inputs=List(x5181, Const(2))).name("x5192").ctrl(x5207) // FixRsh(x5181,Const(2))
    val x5193 = OpDef(op=FixSra, inputs=List(x5191, Const(2))).name("x5193").ctrl(x5207) // FixRsh(x5191,Const(2))
    val x5205_x5194 = OpDef(op=FixAdd, inputs=List(x5205_x5192, x5182)).name("x5194").ctrl(x5207) // FixAdd(x5192,x5182)
    val x5195 = OpDef(op=FixAdd, inputs=List(x5182, x5205_x5192)).name("x5195").ctrl(x5207) // FixAdd(x5182,x5192)
    val x5205_x5196 = OpDef(op=FixAdd, inputs=List(x5195, x5193)).name("x5196").ctrl(x5207) // FixAdd(x5195,x5193)
    val x5197 = OpDef(op=FixAdd, inputs=List(x5183, x5181)).name("x5197").ctrl(x5207) // FixAdd(x5183,x5181)
    val x5203_x5198 = OpDef(op=FixAdd, inputs=List(x5197, x5191)).name("x5198").ctrl(x5207) // FixAdd(x5197,x5191)
    val x5199 = x5184 // FixConvert(x5184,TRUE,_64,_0)
    val x5200 = DramAddress(x5132).name("x5200").ctrl(x5207) // GetDRAMAddress(x5132)
    val x5201 = OpDef(op=FixAdd, inputs=List(x5199, x5200)).name("x5201").ctrl(x5207) // FixAdd(x5199,x5200)
    val x5203_x5202 = x5201 // FixConvert(x5201,TRUE,_64,_0)
    // x5203 = SimpleStruct(ArrayBuffer((offset,x5202), (size,x5198), (isLoad,Const(true))))
    val b5592_b5587 = WriteMem(b5587, x5203_x5202).name("b5592_b5587").ctrl(x5207) // StreamWrite(x5173,x5203,b2866)
    val b5593_b5588 = WriteMem(b5588, x5203_x5198).name("b5593_b5588").ctrl(x5207) // StreamWrite(x5173,x5203,b2866)
    // x5205 = SimpleStruct(ArrayBuffer((size,x5196), (start,x5192), (end,x5194)))
    val b5594_b5589 = WriteMem(b5589, x5205_x5196).name("b5594_b5589").ctrl(x5207) // FIFOEnq(x5174,x5205,b2866)
    val b5595_b5590 = WriteMem(b5590, x5205_x5192).name("b5595_b5590").ctrl(x5207) // FIFOEnq(x5174,x5205,b2866)
    val b5596_b5591 = WriteMem(b5591, x5205_x5194).name("b5596_b5591").ctrl(x5207) // FIFOEnq(x5174,x5205,b2866)
    val x5208 = FringeDenseLoad(dram=List(x5132), cmdStream=List(b5587, b5588), dataStream=List(x5175)).name("x5208").ctrl(x5236) // FringeDenseLoad(x5132,x5173,x5175)
    val x5235 = UnitController(style=SeqPipe, level=OuterControl).name("x5235").ctrl(x5236) // UnitPipe(List(b2866),Block(Const(())))
    val x5209 = Reg(init=Some(0)).name("x5209").ctrl(x5235) // x5209 = RegNew(Const(0))
    isAccum(x5209) = false
    bufferDepthOf(x5209) = 1
    val x5210 = Reg(init=Some(0)).name("x5210").ctrl(x5235) // x5210 = RegNew(Const(0))
    isAccum(x5210) = false
    bufferDepthOf(x5210) = 1
    val x5211 = Reg(init=Some(0)).name("x5211").ctrl(x5235) // x5211 = RegNew(Const(0))
    isAccum(x5211) = false
    bufferDepthOf(x5211) = 1
    val x5219 = UnitController(style=SeqPipe, level=InnerControl).name("x5219").ctrl(x5235) // UnitPipe(List(b2866),Block(x5218))
    val x5212_b5597 = ReadMem(b5589).name("b5597").ctrl(x5219) // FIFODeq(x5174,b2866)
    val x5212_b5598 = ReadMem(b5590).name("b5598").ctrl(x5219) // FIFODeq(x5174,b2866)
    val x5212_b5599 = ReadMem(b5591).name("b5599").ctrl(x5219) // FIFODeq(x5174,b2866)
    val x5213 = x5212_b5598 // x5213 = FieldApply(x5212,start)
    val x5214_x5209 = WriteMem(x5209, x5213).name("x5214_x5209").ctrl(x5219) // RegWrite(x5209,x5213,b2866)
    val x5215 = x5212_b5599 // x5215 = FieldApply(x5212,end)
    val x5216_x5210 = WriteMem(x5210, x5215).name("x5216_x5210").ctrl(x5219) // RegWrite(x5210,x5215,b2866)
    val x5217 = x5212_b5597 // x5217 = FieldApply(x5212,size)
    val x5218_x5211 = WriteMem(x5211, x5217).name("x5218_x5211").ctrl(x5219) // RegWrite(x5211,x5217,b2866)
    val x5220 = ReadMem(x5211).name("x5220").ctrl(x5235) // RegRead(x5211)
    val x5221 = Counter(min=Const(0), max=x5220, step=Const(1), par=16).name("x5221").ctrl(x5235) // CounterNew(Const(0),x5220,Const(1),Const(16))
    val x5222 = CounterChain(List(x5221)).name("x5222").ctrl(x5235) // CounterChainNew(List(x5221))
    val x5234 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5222).name("x5234").ctrl(x5235) // UnrolledForeach(List(b2866),x5222,Block(Const(())),List(List(b2926)),List(List(b2927)))
    val b2926 = CounterIter(x5221, None).ctrl(x5234).name("b2926")
    val b2927 = DummyOp().ctrl(x5234).name("b2927")
    val x5223 = ReadMem(x5209).name("x5223").ctrl(x5234) // RegRead(x5209)
    val x5224 = OpDef(op=FixLeq, inputs=List(x5223, b2926)).name("x5224").ctrl(x5234) // FixLeq(x5223,b2926)
    val x5225 = ReadMem(x5210).name("x5225").ctrl(x5234) // RegRead(x5210)
    val x5226 = OpDef(op=FixLt, inputs=List(b2926, x5225)).name("x5226").ctrl(x5234) // FixLt(b2926,x5225)
    val x5227 = OpDef(op=BitAnd, inputs=List(x5224, x5226)).name("x5227").ctrl(x5234) // And(x5224,x5226)
    val x5228 = OpDef(op=FixSub, inputs=List(b2926, x5223)).name("x5228").ctrl(x5234) // FixSub(b2926,x5223)
    val x5229 = OpDef(op=BitAnd, inputs=List(b2927, b2866)).name("x5229").ctrl(x5234) // And(b2927,b2866)
    val x5230_x5230 = ReadMem(x5175).name("x5230").ctrl(x5234) // ParStreamRead(x5175,List(x5229))
    val x5231_x5231 = x5230_x5230 // x5231 = VectorApply(x5230,0)
    val x5232 = OpDef(op=BitAnd, inputs=List(x5227, x5229)).name("x5232").ctrl(x5234) // And(x5227,x5229)
    val x5233 = StoreBanks(List(x5160_d0_b0, x5160_d1_b0), List(x5228), x5231_x5231).name("x5233").ctrl(x5234) // ParSRAMStore(x5160,List(List(x5228)),List(x5231),List(x5232))
    val x5237 = Counter(min=Const(0), max=Const(494), step=Const(1), par=1).name("x5237").ctrl(x5549) // CounterNew(Const(0),Const(494),Const(1),Const(1))
    val x5238 = CounterChain(List(x5237)).name("x5238").ctrl(x5549) // CounterChainNew(List(x5237))
    val x5477 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5238).name("x5477").ctrl(x5549) // UnrolledForeach(List(b2866),x5238,Block(Const(())),List(List(b2944)),List(List(b2945)))
    val b2944 = CounterIter(x5237, Some(0)).ctrl(x5477).name("b2944")
    val b2945 = DummyOp().ctrl(x5477).name("b2945")
    val x5239_d0_b0 = SRAM(size=494, banking=NoBanking()).name("x5239_d0_b0").ctrl(x5477) // x5239 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x5239_d0_b0) = false
    bufferDepthOf(x5239_d0_b0) = 4
    val x5240_d0_b0 = SRAM(size=247, banking=Strided(banks=2, stride=1)).name("x5240_d0_b0").ctrl(x5477) // x5240 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x5240_d0_b0) = false
    bufferDepthOf(x5240_d0_b0) = 6
    val x5241_d0_b0 = SRAM(size=247, banking=Strided(banks=2, stride=1)).name("x5241_d0_b0").ctrl(x5477) // x5241 = SRAMNew(ArrayBuffer(Const(494)))
    isAccum(x5241_d0_b0) = false
    bufferDepthOf(x5241_d0_b0) = 3
    val x5242_d0 = Reg(init=Some(0)).name("x5242_d0").ctrl(x5477) // x5242 = RegNew(Const(0))
    isAccum(x5242_d0) = false
    bufferDepthOf(x5242_d0) = 3
    val x5242_d1 = Reg(init=Some(0)).name("x5242_d1").ctrl(x5477) // x5242 = RegNew(Const(0))
    isAccum(x5242_d1) = false
    bufferDepthOf(x5242_d1) = 2
    val x5242_d2 = Reg(init=Some(0)).name("x5242_d2").ctrl(x5477) // x5242 = RegNew(Const(0))
    isAccum(x5242_d2) = false
    bufferDepthOf(x5242_d2) = 2
    val x5242_d3 = Reg(init=Some(0)).name("x5242_d3").ctrl(x5477) // x5242 = RegNew(Const(0))
    isAccum(x5242_d3) = false
    bufferDepthOf(x5242_d3) = 2
    val x5243_d0 = Reg(init=Some(0)).name("x5243_d0").ctrl(x5477) // x5243 = RegNew(Const(0))
    isAccum(x5243_d0) = false
    bufferDepthOf(x5243_d0) = 3
    val x5243_d1 = Reg(init=Some(0)).name("x5243_d1").ctrl(x5477) // x5243 = RegNew(Const(0))
    isAccum(x5243_d1) = false
    bufferDepthOf(x5243_d1) = 2
    val x5250 = UnitController(style=SeqPipe, level=InnerControl).name("x5250").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5244 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5244").ctrl(x5250) // And(b2945,b2866)
    val x5245 = LoadBanks(List(x5160_d1_b0), List(b2944)).name("x5245").ctrl(x5250) // SRAMLoad(x5160,ArrayBuffer(Const(495)),List(b2944),Const(0),x5244)
    val x5246 = OpDef(op=FixAdd, inputs=List(b2944, Const(1))).name("x5246").ctrl(x5250) // FixAdd(b2944,Const(1))
    val x5247 = LoadBanks(List(x5160_d0_b0), List(x5246)).name("x5247").ctrl(x5250) // SRAMLoad(x5160,ArrayBuffer(Const(495)),List(x5246),Const(0),x5244)
    val x5248_x5242_d0 = WriteMem(x5242_d0, x5245).name("x5248_x5242_d0").ctrl(x5250) // RegWrite(x5242,x5245,x5244)
    val x5248_x5242_d1 = WriteMem(x5242_d1, x5245).name("x5248_x5242_d1").ctrl(x5250) // RegWrite(x5242,x5245,x5244)
    val x5248_x5242_d2 = WriteMem(x5242_d2, x5245).name("x5248_x5242_d2").ctrl(x5250) // RegWrite(x5242,x5245,x5244)
    val x5248_x5242_d3 = WriteMem(x5242_d3, x5245).name("x5248_x5242_d3").ctrl(x5250) // RegWrite(x5242,x5245,x5244)
    val x5249_x5243_d0 = WriteMem(x5243_d0, x5247).name("x5249_x5243_d0").ctrl(x5250) // RegWrite(x5243,x5247,x5244)
    val x5249_x5243_d1 = WriteMem(x5243_d1, x5247).name("x5249_x5243_d1").ctrl(x5250) // RegWrite(x5243,x5247,x5244)
    val x5251_d0 = Reg(init=Some(0)).name("x5251_d0").ctrl(x5477) // x5251 = RegNew(Const(0))
    isAccum(x5251_d0) = false
    bufferDepthOf(x5251_d0) = 1
    val x5251_d1 = Reg(init=Some(0)).name("x5251_d1").ctrl(x5477) // x5251 = RegNew(Const(0))
    isAccum(x5251_d1) = false
    bufferDepthOf(x5251_d1) = 1
    val x5257 = UnitController(style=SeqPipe, level=InnerControl).name("x5257").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5252 = ReadMem(x5243_d1).name("x5252").ctrl(x5257) // RegRead(x5243)
    val x5253 = ReadMem(x5242_d3).name("x5253").ctrl(x5257) // RegRead(x5242)
    val x5254 = OpDef(op=FixSub, inputs=List(x5252, x5253)).name("x5254").ctrl(x5257) // FixSub(x5252,x5253)
    val x5255 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5255").ctrl(x5257) // And(b2945,b2866)
    val x5256_x5251_d0 = WriteMem(x5251_d0, x5254).name("x5256_x5251_d0").ctrl(x5257) // RegWrite(x5251,x5254,x5255)
    val x5256_x5251_d1 = WriteMem(x5251_d1, x5254).name("x5256_x5251_d1").ctrl(x5257) // RegWrite(x5251,x5254,x5255)
    val x5324 = UnitController(style=StreamPipe, level=OuterControl).name("x5324").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val b5600 = StreamOut(field="offset").name("b5600").ctrl(x5324) // x5258 = StreamOutNew(BurstCmdBus)
    val b5601 = StreamOut(field="size").name("b5601").ctrl(x5324) // x5258 = StreamOutNew(BurstCmdBus)
    val b5602 = FIFO(size=16).name("b5602").ctrl(x5324) // x5259 = FIFONew(Const(16))
    isAccum(b5602) = false
    bufferDepthOf(b5602) = 2
    val b5603 = FIFO(size=16).name("b5603").ctrl(x5324) // x5259 = FIFONew(Const(16))
    isAccum(b5603) = false
    bufferDepthOf(b5603) = 2
    val b5604 = FIFO(size=16).name("b5604").ctrl(x5324) // x5259 = FIFONew(Const(16))
    isAccum(b5604) = false
    bufferDepthOf(b5604) = 2
    val x5260 = StreamIn(field="data").name("x5260").ctrl(x5324) // x5260 = StreamInNew(BurstDataBus())
    val x5293 = UnitController(style=SeqPipe, level=InnerControl).name("x5293").ctrl(x5324) // UnitPipe(List(b2945, b2866),Block(x5292))
    val x5261 = ReadMem(x5242_d2).name("x5261").ctrl(x5293) // RegRead(x5242)
    val x5262 = x5261 // FixConvert(x5261,TRUE,_32,_0)
    val x5263 = OpDef(op=FixSla, inputs=List(x5262, Const(2))).name("x5263").ctrl(x5293) // FixLsh(x5262,Const(2))
    val x5264 = x5263 // x5264 = DataAsBits(x5263)
    val x5265 = OpDef(op=BitAnd, inputs=List(x5264, Const(31))).name("x5265").ctrl(x5293) // VectorSlice(x5264,5,0) strMask=00000000000000000000000000011111
    val x5266 = x5265 // x5266 = BitsAsData(x5265,FixPt[TRUE,_32,_0])
    val x5267 = ReadMem(x5251_d1).name("x5267").ctrl(x5293) // RegRead(x5251)
    val x5268 = OpDef(op=FixSla, inputs=List(x5267, Const(2))).name("x5268").ctrl(x5293) // FixLsh(x5267,Const(2))
    val x5269 = OpDef(op=FixSub, inputs=List(x5263, x5266)).name("x5269").ctrl(x5293) // FixSub(x5263,x5266)
    val x5270 = OpDef(op=FixAdd, inputs=List(x5263, x5268)).name("x5270").ctrl(x5293) // FixAdd(x5263,x5268)
    val x5271 = x5270 // x5271 = DataAsBits(x5270)
    val x5272 = OpDef(op=BitAnd, inputs=List(x5271, Const(31))).name("x5272").ctrl(x5293) // VectorSlice(x5271,5,0) strMask=00000000000000000000000000011111
    val x5273 = x5272 // x5273 = BitsAsData(x5272,FixPt[TRUE,_32,_0])
    val x5274 = OpDef(op=FixEql, inputs=List(x5273, Const(0))).name("x5274").ctrl(x5293) // FixEql(x5273,Const(0))
    val x5275 = OpDef(op=FixSub, inputs=List(Const(64), x5273)).name("x5275").ctrl(x5293) // FixSub(Const(64),x5273)
    val x5276 = OpDef(op=MuxOp, inputs=List(x5274, Const(0), x5275)).name("x5276").ctrl(x5293) // Mux(x5274,Const(0),x5275)
    val x5291_x5277 = OpDef(op=FixSra, inputs=List(x5266, Const(2))).name("x5277").ctrl(x5293) // FixRsh(x5266,Const(2))
    val x5278 = OpDef(op=FixSra, inputs=List(x5276, Const(2))).name("x5278").ctrl(x5293) // FixRsh(x5276,Const(2))
    val x5291_x5279 = OpDef(op=FixAdd, inputs=List(x5291_x5277, x5267)).name("x5279").ctrl(x5293) // FixAdd(x5277,x5267)
    val x5280 = OpDef(op=FixAdd, inputs=List(x5267, x5291_x5277)).name("x5280").ctrl(x5293) // FixAdd(x5267,x5277)
    val x5291_x5281 = OpDef(op=FixAdd, inputs=List(x5280, x5278)).name("x5281").ctrl(x5293) // FixAdd(x5280,x5278)
    val x5282 = OpDef(op=FixAdd, inputs=List(x5268, x5266)).name("x5282").ctrl(x5293) // FixAdd(x5268,x5266)
    val x5288_x5283 = OpDef(op=FixAdd, inputs=List(x5282, x5276)).name("x5283").ctrl(x5293) // FixAdd(x5282,x5276)
    val x5284 = x5269 // FixConvert(x5269,TRUE,_64,_0)
    val x5285 = DramAddress(x5131).name("x5285").ctrl(x5293) // GetDRAMAddress(x5131)
    val x5286 = OpDef(op=FixAdd, inputs=List(x5284, x5285)).name("x5286").ctrl(x5293) // FixAdd(x5284,x5285)
    val x5288_x5287 = x5286 // FixConvert(x5286,TRUE,_64,_0)
    // x5288 = SimpleStruct(ArrayBuffer((offset,x5287), (size,x5283), (isLoad,Const(true))))
    val x5289 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5289").ctrl(x5293) // And(b2945,b2866)
    val b5605_b5600 = WriteMem(b5600, x5288_x5287).name("b5605_b5600").ctrl(x5293) // StreamWrite(x5258,x5288,x5289)
    val b5606_b5601 = WriteMem(b5601, x5288_x5283).name("b5606_b5601").ctrl(x5293) // StreamWrite(x5258,x5288,x5289)
    // x5291 = SimpleStruct(ArrayBuffer((size,x5281), (start,x5277), (end,x5279)))
    val b5607_b5602 = WriteMem(b5602, x5291_x5281).name("b5607_b5602").ctrl(x5293) // FIFOEnq(x5259,x5291,x5289)
    val b5608_b5603 = WriteMem(b5603, x5291_x5277).name("b5608_b5603").ctrl(x5293) // FIFOEnq(x5259,x5291,x5289)
    val b5609_b5604 = WriteMem(b5604, x5291_x5279).name("b5609_b5604").ctrl(x5293) // FIFOEnq(x5259,x5291,x5289)
    val x5294 = FringeDenseLoad(dram=List(x5131), cmdStream=List(b5600, b5601), dataStream=List(x5260)).name("x5294").ctrl(x5324) // FringeDenseLoad(x5131,x5258,x5260)
    val x5323 = UnitController(style=SeqPipe, level=OuterControl).name("x5323").ctrl(x5324) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5295 = Reg(init=Some(0)).name("x5295").ctrl(x5323) // x5295 = RegNew(Const(0))
    isAccum(x5295) = false
    bufferDepthOf(x5295) = 1
    val x5296 = Reg(init=Some(0)).name("x5296").ctrl(x5323) // x5296 = RegNew(Const(0))
    isAccum(x5296) = false
    bufferDepthOf(x5296) = 1
    val x5297 = Reg(init=Some(0)).name("x5297").ctrl(x5323) // x5297 = RegNew(Const(0))
    isAccum(x5297) = false
    bufferDepthOf(x5297) = 1
    val x5306 = UnitController(style=SeqPipe, level=InnerControl).name("x5306").ctrl(x5323) // UnitPipe(List(b2945, b2866),Block(x5305))
    val x5298 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5298").ctrl(x5306) // And(b2945,b2866)
    val x5299_b5610 = ReadMem(b5602).name("b5610").ctrl(x5306) // FIFODeq(x5259,x5298)
    val x5299_b5611 = ReadMem(b5603).name("b5611").ctrl(x5306) // FIFODeq(x5259,x5298)
    val x5299_b5612 = ReadMem(b5604).name("b5612").ctrl(x5306) // FIFODeq(x5259,x5298)
    val x5300 = x5299_b5611 // x5300 = FieldApply(x5299,start)
    val x5301_x5295 = WriteMem(x5295, x5300).name("x5301_x5295").ctrl(x5306) // RegWrite(x5295,x5300,x5298)
    val x5302 = x5299_b5612 // x5302 = FieldApply(x5299,end)
    val x5303_x5296 = WriteMem(x5296, x5302).name("x5303_x5296").ctrl(x5306) // RegWrite(x5296,x5302,x5298)
    val x5304 = x5299_b5610 // x5304 = FieldApply(x5299,size)
    val x5305_x5297 = WriteMem(x5297, x5304).name("x5305_x5297").ctrl(x5306) // RegWrite(x5297,x5304,x5298)
    val x5307 = ReadMem(x5297).name("x5307").ctrl(x5323) // RegRead(x5297)
    val x5308 = Counter(min=Const(0), max=x5307, step=Const(1), par=1).name("x5308").ctrl(x5323) // CounterNew(Const(0),x5307,Const(1),Const(1))
    val x5309 = CounterChain(List(x5308)).name("x5309").ctrl(x5323) // CounterChainNew(List(x5308))
    val x5322 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5309).name("x5322").ctrl(x5323) // UnrolledForeach(List(b2945, b2866),x5309,Block(Const(())),List(List(b3013)),List(List(b3014)))
    val b3013 = CounterIter(x5308, None).ctrl(x5322).name("b3013")
    val b3014 = DummyOp().ctrl(x5322).name("b3014")
    val x5310 = ReadMem(x5295).name("x5310").ctrl(x5322) // RegRead(x5295)
    val x5311 = OpDef(op=FixLeq, inputs=List(x5310, b3013)).name("x5311").ctrl(x5322) // FixLeq(x5310,b3013)
    val x5312 = ReadMem(x5296).name("x5312").ctrl(x5322) // RegRead(x5296)
    val x5313 = OpDef(op=FixLt, inputs=List(b3013, x5312)).name("x5313").ctrl(x5322) // FixLt(b3013,x5312)
    val x5314 = OpDef(op=BitAnd, inputs=List(x5311, x5313)).name("x5314").ctrl(x5322) // And(x5311,x5313)
    val x5315 = OpDef(op=FixSub, inputs=List(b3013, x5310)).name("x5315").ctrl(x5322) // FixSub(b3013,x5310)
    val x5316 = OpDef(op=BitAnd, inputs=List(b3014, b2945)).name("x5316").ctrl(x5322) // And(b3014,b2945)
    val x5317 = OpDef(op=BitAnd, inputs=List(x5316, b2866)).name("x5317").ctrl(x5322) // And(x5316,b2866)
    val x5318_x5318 = ReadMem(x5260).name("x5318").ctrl(x5322) // ParStreamRead(x5260,List(x5317))
    val x5319_x5319 = x5318_x5318 // x5319 = VectorApply(x5318,0)
    val x5320 = OpDef(op=BitAnd, inputs=List(x5314, x5317)).name("x5320").ctrl(x5322) // And(x5314,x5317)
    val x5321 = StoreBanks(List(x5239_d0_b0), List(x5315), x5319_x5319).name("x5321").ctrl(x5322) // ParSRAMStore(x5239,List(List(x5315)),List(x5319),List(x5320))
    val x5391 = UnitController(style=StreamPipe, level=OuterControl).name("x5391").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val b5613 = StreamOut(field="offset").name("b5613").ctrl(x5391) // x5325 = StreamOutNew(BurstCmdBus)
    val b5614 = StreamOut(field="size").name("b5614").ctrl(x5391) // x5325 = StreamOutNew(BurstCmdBus)
    val b5615 = FIFO(size=16).name("b5615").ctrl(x5391) // x5326 = FIFONew(Const(16))
    isAccum(b5615) = false
    bufferDepthOf(b5615) = 2
    val b5616 = FIFO(size=16).name("b5616").ctrl(x5391) // x5326 = FIFONew(Const(16))
    isAccum(b5616) = false
    bufferDepthOf(b5616) = 2
    val b5617 = FIFO(size=16).name("b5617").ctrl(x5391) // x5326 = FIFONew(Const(16))
    isAccum(b5617) = false
    bufferDepthOf(b5617) = 2
    val x5327 = StreamIn(field="data").name("x5327").ctrl(x5391) // x5327 = StreamInNew(BurstDataBus())
    val x5360 = UnitController(style=SeqPipe, level=InnerControl).name("x5360").ctrl(x5391) // UnitPipe(List(b2945, b2866),Block(x5359))
    val x5328 = ReadMem(x5242_d1).name("x5328").ctrl(x5360) // RegRead(x5242)
    val x5329 = x5328 // FixConvert(x5328,TRUE,_32,_0)
    val x5330 = OpDef(op=FixSla, inputs=List(x5329, Const(2))).name("x5330").ctrl(x5360) // FixLsh(x5329,Const(2))
    val x5331 = x5330 // x5331 = DataAsBits(x5330)
    val x5332 = OpDef(op=BitAnd, inputs=List(x5331, Const(31))).name("x5332").ctrl(x5360) // VectorSlice(x5331,5,0) strMask=00000000000000000000000000011111
    val x5333 = x5332 // x5333 = BitsAsData(x5332,FixPt[TRUE,_32,_0])
    val x5334 = ReadMem(x5251_d0).name("x5334").ctrl(x5360) // RegRead(x5251)
    val x5335 = OpDef(op=FixSla, inputs=List(x5334, Const(2))).name("x5335").ctrl(x5360) // FixLsh(x5334,Const(2))
    val x5336 = OpDef(op=FixSub, inputs=List(x5330, x5333)).name("x5336").ctrl(x5360) // FixSub(x5330,x5333)
    val x5337 = OpDef(op=FixAdd, inputs=List(x5330, x5335)).name("x5337").ctrl(x5360) // FixAdd(x5330,x5335)
    val x5338 = x5337 // x5338 = DataAsBits(x5337)
    val x5339 = OpDef(op=BitAnd, inputs=List(x5338, Const(31))).name("x5339").ctrl(x5360) // VectorSlice(x5338,5,0) strMask=00000000000000000000000000011111
    val x5340 = x5339 // x5340 = BitsAsData(x5339,FixPt[TRUE,_32,_0])
    val x5341 = OpDef(op=FixEql, inputs=List(x5340, Const(0))).name("x5341").ctrl(x5360) // FixEql(x5340,Const(0))
    val x5342 = OpDef(op=FixSub, inputs=List(Const(64), x5340)).name("x5342").ctrl(x5360) // FixSub(Const(64),x5340)
    val x5343 = OpDef(op=MuxOp, inputs=List(x5341, Const(0), x5342)).name("x5343").ctrl(x5360) // Mux(x5341,Const(0),x5342)
    val x5358_x5344 = OpDef(op=FixSra, inputs=List(x5333, Const(2))).name("x5344").ctrl(x5360) // FixRsh(x5333,Const(2))
    val x5345 = OpDef(op=FixSra, inputs=List(x5343, Const(2))).name("x5345").ctrl(x5360) // FixRsh(x5343,Const(2))
    val x5358_x5346 = OpDef(op=FixAdd, inputs=List(x5358_x5344, x5334)).name("x5346").ctrl(x5360) // FixAdd(x5344,x5334)
    val x5347 = OpDef(op=FixAdd, inputs=List(x5334, x5358_x5344)).name("x5347").ctrl(x5360) // FixAdd(x5334,x5344)
    val x5358_x5348 = OpDef(op=FixAdd, inputs=List(x5347, x5345)).name("x5348").ctrl(x5360) // FixAdd(x5347,x5345)
    val x5349 = OpDef(op=FixAdd, inputs=List(x5335, x5333)).name("x5349").ctrl(x5360) // FixAdd(x5335,x5333)
    val x5355_x5350 = OpDef(op=FixAdd, inputs=List(x5349, x5343)).name("x5350").ctrl(x5360) // FixAdd(x5349,x5343)
    val x5351 = x5336 // FixConvert(x5336,TRUE,_64,_0)
    val x5352 = DramAddress(x5130).name("x5352").ctrl(x5360) // GetDRAMAddress(x5130)
    val x5353 = OpDef(op=FixAdd, inputs=List(x5351, x5352)).name("x5353").ctrl(x5360) // FixAdd(x5351,x5352)
    val x5355_x5354 = x5353 // FixConvert(x5353,TRUE,_64,_0)
    // x5355 = SimpleStruct(ArrayBuffer((offset,x5354), (size,x5350), (isLoad,Const(true))))
    val x5356 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5356").ctrl(x5360) // And(b2945,b2866)
    val b5618_b5613 = WriteMem(b5613, x5355_x5354).name("b5618_b5613").ctrl(x5360) // StreamWrite(x5325,x5355,x5356)
    val b5619_b5614 = WriteMem(b5614, x5355_x5350).name("b5619_b5614").ctrl(x5360) // StreamWrite(x5325,x5355,x5356)
    // x5358 = SimpleStruct(ArrayBuffer((size,x5348), (start,x5344), (end,x5346)))
    val b5620_b5615 = WriteMem(b5615, x5358_x5348).name("b5620_b5615").ctrl(x5360) // FIFOEnq(x5326,x5358,x5356)
    val b5621_b5616 = WriteMem(b5616, x5358_x5344).name("b5621_b5616").ctrl(x5360) // FIFOEnq(x5326,x5358,x5356)
    val b5622_b5617 = WriteMem(b5617, x5358_x5346).name("b5622_b5617").ctrl(x5360) // FIFOEnq(x5326,x5358,x5356)
    val x5361 = FringeDenseLoad(dram=List(x5130), cmdStream=List(b5613, b5614), dataStream=List(x5327)).name("x5361").ctrl(x5391) // FringeDenseLoad(x5130,x5325,x5327)
    val x5390 = UnitController(style=SeqPipe, level=OuterControl).name("x5390").ctrl(x5391) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5362 = Reg(init=Some(0)).name("x5362").ctrl(x5390) // x5362 = RegNew(Const(0))
    isAccum(x5362) = false
    bufferDepthOf(x5362) = 1
    val x5363 = Reg(init=Some(0)).name("x5363").ctrl(x5390) // x5363 = RegNew(Const(0))
    isAccum(x5363) = false
    bufferDepthOf(x5363) = 1
    val x5364 = Reg(init=Some(0)).name("x5364").ctrl(x5390) // x5364 = RegNew(Const(0))
    isAccum(x5364) = false
    bufferDepthOf(x5364) = 1
    val x5373 = UnitController(style=SeqPipe, level=InnerControl).name("x5373").ctrl(x5390) // UnitPipe(List(b2945, b2866),Block(x5372))
    val x5365 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5365").ctrl(x5373) // And(b2945,b2866)
    val x5366_b5623 = ReadMem(b5615).name("b5623").ctrl(x5373) // FIFODeq(x5326,x5365)
    val x5366_b5624 = ReadMem(b5616).name("b5624").ctrl(x5373) // FIFODeq(x5326,x5365)
    val x5366_b5625 = ReadMem(b5617).name("b5625").ctrl(x5373) // FIFODeq(x5326,x5365)
    val x5367 = x5366_b5624 // x5367 = FieldApply(x5366,start)
    val x5368_x5362 = WriteMem(x5362, x5367).name("x5368_x5362").ctrl(x5373) // RegWrite(x5362,x5367,x5365)
    val x5369 = x5366_b5625 // x5369 = FieldApply(x5366,end)
    val x5370_x5363 = WriteMem(x5363, x5369).name("x5370_x5363").ctrl(x5373) // RegWrite(x5363,x5369,x5365)
    val x5371 = x5366_b5623 // x5371 = FieldApply(x5366,size)
    val x5372_x5364 = WriteMem(x5364, x5371).name("x5372_x5364").ctrl(x5373) // RegWrite(x5364,x5371,x5365)
    val x5374 = ReadMem(x5364).name("x5374").ctrl(x5390) // RegRead(x5364)
    val x5375 = Counter(min=Const(0), max=x5374, step=Const(1), par=1).name("x5375").ctrl(x5390) // CounterNew(Const(0),x5374,Const(1),Const(1))
    val x5376 = CounterChain(List(x5375)).name("x5376").ctrl(x5390) // CounterChainNew(List(x5375))
    val x5389 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5376).name("x5389").ctrl(x5390) // UnrolledForeach(List(b2945, b2866),x5376,Block(Const(())),List(List(b3078)),List(List(b3079)))
    val b3078 = CounterIter(x5375, None).ctrl(x5389).name("b3078")
    val b3079 = DummyOp().ctrl(x5389).name("b3079")
    val x5377 = ReadMem(x5362).name("x5377").ctrl(x5389) // RegRead(x5362)
    val x5378 = OpDef(op=FixLeq, inputs=List(x5377, b3078)).name("x5378").ctrl(x5389) // FixLeq(x5377,b3078)
    val x5379 = ReadMem(x5363).name("x5379").ctrl(x5389) // RegRead(x5363)
    val x5380 = OpDef(op=FixLt, inputs=List(b3078, x5379)).name("x5380").ctrl(x5389) // FixLt(b3078,x5379)
    val x5381 = OpDef(op=BitAnd, inputs=List(x5378, x5380)).name("x5381").ctrl(x5389) // And(x5378,x5380)
    val x5382 = OpDef(op=FixSub, inputs=List(b3078, x5377)).name("x5382").ctrl(x5389) // FixSub(b3078,x5377)
    val x5383 = OpDef(op=BitAnd, inputs=List(b3079, b2945)).name("x5383").ctrl(x5389) // And(b3079,b2945)
    val x5384 = OpDef(op=BitAnd, inputs=List(x5383, b2866)).name("x5384").ctrl(x5389) // And(x5383,b2866)
    val x5385_x5385 = ReadMem(x5327).name("x5385").ctrl(x5389) // ParStreamRead(x5327,List(x5384))
    val x5386_x5386 = x5385_x5385 // x5386 = VectorApply(x5385,0)
    val x5387 = OpDef(op=BitAnd, inputs=List(x5381, x5384)).name("x5387").ctrl(x5389) // And(x5381,x5384)
    val x5388 = StoreBanks(List(x5240_d0_b0), List(x5382), x5386_x5386).name("x5388").ctrl(x5389) // ParSRAMStore(x5240,List(List(x5382)),List(x5386),List(x5387))
    val x5392_d0 = Reg(init=Some(0)).name("x5392_d0").ctrl(x5477) // x5392 = RegNew(Const(0))
    isAccum(x5392_d0) = false
    bufferDepthOf(x5392_d0) = 5
    val x5392_d1 = Reg(init=Some(0)).name("x5392_d1").ctrl(x5477) // x5392 = RegNew(Const(0))
    isAccum(x5392_d1) = false
    bufferDepthOf(x5392_d1) = 3
    val x5392_d2 = Reg(init=Some(0)).name("x5392_d2").ctrl(x5477) // x5392 = RegNew(Const(0))
    isAccum(x5392_d2) = false
    bufferDepthOf(x5392_d2) = 3
    val x5392_d3 = Reg(init=Some(0)).name("x5392_d3").ctrl(x5477) // x5392 = RegNew(Const(0))
    isAccum(x5392_d3) = false
    bufferDepthOf(x5392_d3) = 2
    val x5398 = UnitController(style=SeqPipe, level=InnerControl).name("x5398").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5393 = ReadMem(x5243_d0).name("x5393").ctrl(x5398) // RegRead(x5243)
    val x5394 = ReadMem(x5242_d0).name("x5394").ctrl(x5398) // RegRead(x5242)
    val x5395 = OpDef(op=FixSub, inputs=List(x5393, x5394)).name("x5395").ctrl(x5398) // FixSub(x5393,x5394)
    val x5396 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5396").ctrl(x5398) // And(b2945,b2866)
    val x5397_x5392_d0 = WriteMem(x5392_d0, x5395).name("x5397_x5392_d0").ctrl(x5398) // RegWrite(x5392,x5395,x5396)
    val x5397_x5392_d1 = WriteMem(x5392_d1, x5395).name("x5397_x5392_d1").ctrl(x5398) // RegWrite(x5392,x5395,x5396)
    val x5397_x5392_d2 = WriteMem(x5392_d2, x5395).name("x5397_x5392_d2").ctrl(x5398) // RegWrite(x5392,x5395,x5396)
    val x5397_x5392_d3 = WriteMem(x5392_d3, x5395).name("x5397_x5392_d3").ctrl(x5398) // RegWrite(x5392,x5395,x5396)
    val x5399_d0 = Reg(init=Some(0)).name("x5399_d0").ctrl(x5477) // x5399 = RegNew(Const(0))
    isAccum(x5399_d0) = false
    bufferDepthOf(x5399_d0) = 2
    val x5399_d1 = Reg(init=Some(0)).name("x5399_d1").ctrl(x5477) // x5399 = RegNew(Const(0))
    isAccum(x5399_d1) = false
    bufferDepthOf(x5399_d1) = 2
    val x5412 = UnitController(style=SeqPipe, level=InnerControl).name("x5412").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(x5411))
    val x5400 = ReadMem(x5392_d3).name("x5400").ctrl(x5412) // RegRead(x5392)
    val x5401 = OpDef(op=FixLt, inputs=List(x5400, Const(16))).name("x5401").ctrl(x5412) // FixLt(x5400,Const(16))
    val x5402 = x5400 // x5402 = DataAsBits(x5400)
    val x5403 = OpDef(op=BitAnd, inputs=List(x5402, Const(7))).name("x5403").ctrl(x5412) // VectorSlice(x5402,3,0) strMask=00000000000000000000000000000111
    val x5404 = x5403 // x5404 = BitsAsData(x5403,FixPt[TRUE,_32,_0])
    val x5405 = OpDef(op=FixEql, inputs=List(x5404, Const(0))).name("x5405").ctrl(x5412) // FixEql(x5404,Const(0))
    val x5406 = OpDef(op=FixAdd, inputs=List(x5400, Const(16))).name("x5406").ctrl(x5412) // FixAdd(x5400,Const(16))
    val x5407 = OpDef(op=FixSub, inputs=List(x5406, x5404)).name("x5407").ctrl(x5412) // FixSub(x5406,x5404)
    val x5408 = OpDef(op=MuxOp, inputs=List(x5405, x5400, x5407)).name("x5408").ctrl(x5412) // Mux(x5405,x5400,x5407)
    val x5409 = OpDef(op=MuxOp, inputs=List(x5401, Const(16), x5408)).name("x5409").ctrl(x5412) // Mux(x5401,Const(16),x5408)
    val x5410 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5410").ctrl(x5412) // And(b2945,b2866)
    val x5411_x5399_d0 = WriteMem(x5399_d0, x5409).name("x5411_x5399_d0").ctrl(x5412) // RegWrite(x5399,x5409,x5410)
    val x5411_x5399_d1 = WriteMem(x5399_d1, x5409).name("x5411_x5399_d1").ctrl(x5412) // RegWrite(x5399,x5409,x5410)
    val x5445 = UnitController(style=StreamPipe, level=OuterControl).name("x5445").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5413 = StreamOut(field="addr").name("x5413").ctrl(x5445) // x5413 = StreamOutNew(GatherAddrBus)
    val x5414 = StreamIn(field="data").name("x5414").ctrl(x5445) // x5414 = StreamInNew(GatherDataBus())
    val x5415 = ReadMem(x5399_d1).name("x5415").ctrl(x5445) // RegRead(x5399)
    val x5416 = Counter(min=Const(0), max=x5415, step=Const(1), par=1).name("x5416").ctrl(x5445) // CounterNew(Const(0),x5415,Const(1),Const(1))
    val x5417 = CounterChain(List(x5416)).name("x5417").ctrl(x5445) // CounterChainNew(List(x5416))
    val x5431 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5417).name("x5431").ctrl(x5445) // UnrolledForeach(List(b2945, b2866),x5417,Block(Const(())),List(List(b3120)),List(List(b3121)))
    val b3120 = CounterIter(x5416, None).ctrl(x5431).name("b3120")
    val b3121 = DummyOp().ctrl(x5431).name("b3121")
    val x5418 = ReadMem(x5392_d2).name("x5418").ctrl(x5431) // RegRead(x5392)
    val x5419 = OpDef(op=FixLeq, inputs=List(x5418, b3120)).name("x5419").ctrl(x5431) // FixLeq(x5418,b3120)
    val x5420 = DramAddress(x5133).name("x5420").ctrl(x5431) // GetDRAMAddress(x5133)
    val x5421 = x5420 // FixConvert(x5420,TRUE,_64,_0)
    val x5422 = OpDef(op=BitAnd, inputs=List(b3121, b2945)).name("x5422").ctrl(x5431) // And(b3121,b2945)
    val x5423 = OpDef(op=BitAnd, inputs=List(x5422, b2866)).name("x5423").ctrl(x5431) // And(x5422,b2866)
    val x5424 = LoadBanks(List(x5239_d0_b0), List(b3120)).name("x5424").ctrl(x5431) // ParSRAMLoad(x5239,List(List(b3120)),List(x5423))
    val x5425 = x5424 // x5425 = VectorApply(x5424,0)
    val x5426 = OpDef(op=FixSla, inputs=List(x5425, Const(2))).name("x5426").ctrl(x5431) // FixLsh(x5425,Const(2))
    val x5427 = x5426 // FixConvert(x5426,TRUE,_64,_0)
    val x5428 = OpDef(op=FixAdd, inputs=List(x5427, x5420)).name("x5428").ctrl(x5431) // FixAdd(x5427,x5420)
    val x5429 = OpDef(op=MuxOp, inputs=List(x5419, x5421, x5428)).name("x5429").ctrl(x5431) // Mux(x5419,x5421,x5428)
    val x5430_x5413 = WriteMem(x5413, x5429).name("x5430_x5413").ctrl(x5431) // ParStreamWrite(x5413,List(x5429),List(x5423))
    val x5432 = FringeSparseLoad(dram=List(x5133), addrStream=List(x5413), dataStream=List(x5414)).name("x5432").ctrl(x5445) // FringeSparseLoad(x5133,x5413,x5414)
    val x5433 = ReadMem(x5399_d0).name("x5433").ctrl(x5445) // RegRead(x5399)
    val x5434 = Counter(min=Const(0), max=x5433, step=Const(1), par=1).name("x5434").ctrl(x5445) // CounterNew(Const(0),x5433,Const(1),Const(1))
    val x5435 = CounterChain(List(x5434)).name("x5435").ctrl(x5445) // CounterChainNew(List(x5434))
    val x5444 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5435).name("x5444").ctrl(x5445) // UnrolledForeach(List(b2945, b2866),x5435,Block(Const(())),List(List(b3140)),List(List(b3141)))
    val b3140 = CounterIter(x5434, None).ctrl(x5444).name("b3140")
    val b3141 = DummyOp().ctrl(x5444).name("b3141")
    val x5436 = OpDef(op=BitAnd, inputs=List(b3141, b2945)).name("x5436").ctrl(x5444) // And(b3141,b2945)
    val x5437 = OpDef(op=BitAnd, inputs=List(x5436, b2866)).name("x5437").ctrl(x5444) // And(x5436,b2866)
    val x5438_x5438 = ReadMem(x5414).name("x5438").ctrl(x5444) // ParStreamRead(x5414,List(x5437))
    val x5439_x5439 = x5438_x5438 // x5439 = VectorApply(x5438,0)
    val x5440 = ReadMem(x5392_d1).name("x5440").ctrl(x5444) // RegRead(x5392)
    val x5441 = OpDef(op=FixLt, inputs=List(b3140, x5440)).name("x5441").ctrl(x5444) // FixLt(b3140,x5440)
    val x5442 = OpDef(op=BitAnd, inputs=List(x5441, x5437)).name("x5442").ctrl(x5444) // And(x5441,x5437)
    val x5443 = StoreBanks(List(x5241_d0_b0), List(b3140), x5439_x5439).name("x5443").ctrl(x5444) // ParSRAMStore(x5241,List(List(b3140)),List(x5439),List(x5442))
    val x5448_d0 = Reg(init=Some(0.0)).name("x5448_d0").ctrl(x5477) // x5448 = RegNew(Const(0))
    isAccum(x5448_d0) = false
    bufferDepthOf(x5448_d0) = 2
    val x5448_d1 = Reg(init=Some(0.0)).name("x5448_d1").ctrl(x5477) // x5448 = RegNew(Const(0))
    isAccum(x5448_d1) = true
    bufferDepthOf(x5448_d1) = 1
    val x5456 = UnitController(style=SeqPipe, level=InnerControl).name("x5456").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5449 = OpDef(op=FixAdd, inputs=List(b2944, b2865)).name("x5449").ctrl(x5456) // FixAdd(b2944,b2865)
    val x5452 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5452").ctrl(x5456) // And(b2945,b2866)
    val x5457 = ReadMem(x5392_d0).name("x5457").ctrl(x5477) // RegRead(x5392)
    val x5458 = Counter(min=Const(0), max=x5457, step=Const(1), par=2).name("x5458").ctrl(x5477) // CounterNew(Const(0),x5457,Const(1),Const(2))
    val x5459 = CounterChain(List(x5458)).name("x5459").ctrl(x5477) // CounterChainNew(List(x5458))
    val x5472 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5459).name("x5472").ctrl(x5477) // UnrolledReduce(List(b2945, b2866),x5459,x5448,Block((x5448) => Const(())),List(List(b3166)),List(List(b3167)))
    val b3166 = CounterIter(x5458, None).ctrl(x5472).name("b3166")
    val b3167 = DummyOp().ctrl(x5472).name("b3167")
    val x5460 = OpDef(op=BitAnd, inputs=List(b3167, b2945)).name("x5460").ctrl(x5472) // And(b3167,b2945)
    val x5461 = OpDef(op=BitAnd, inputs=List(x5460, b2866)).name("x5461").ctrl(x5472) // And(x5460,b2866)
    val x5462 = LoadBanks(List(x5240_d0_b0), List(b3166)).name("x5462").ctrl(x5472) // ParSRAMLoad(x5240,List(List(b3166)),List(x5461))
    val x5463 = x5462 // x5463 = VectorApply(x5462,0)
    val x5464 = LoadBanks(List(x5241_d0_b0), List(b3166)).name("x5464").ctrl(x5472) // ParSRAMLoad(x5241,List(List(b3166)),List(x5461))
    val x5465 = x5464 // x5465 = VectorApply(x5464,0)
    val x5466 = OpDef(op=FixMul, inputs=List(x5463, x5465)).name("x5466").ctrl(x5472) // FixMul(x5463,x5465)
    val x5467 = ReadMem(x5448_d1).name("x5467").ctrl(x5472) // RegRead(x5448)
    val x5468 = OpDef(op=FixEql, inputs=List(b3166, Const(0))).name("x5468").ctrl(x5472) // FixEql(b3166,Const(0))
    val x5469 = ReduceAccumOp(op=FixAdd, input=x5466, accum=x5467).name("x5469").ctrl(x5472) // FixAdd(x5466,x5467)
    val x5470 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5470").ctrl(x5472) // And(b2945,b2866)
    val x5471_x5448_d0 = WriteMem(x5448_d0, x5469).name("x5471_x5448_d0").ctrl(x5472) // RegWrite(x5448,x5469,x5470)
    val x5471_x5448_d1 = WriteMem(x5448_d1, x5469).name("x5471_x5448_d1").ctrl(x5472) // RegWrite(x5448,x5469,x5470)
    val x5476 = UnitController(style=SeqPipe, level=InnerControl).name("x5476").ctrl(x5477) // UnitPipe(List(b2945, b2866),Block(Const(())))
    val x5473 = ReadMem(x5448_d0).name("x5473").ctrl(x5476) // RegRead(x5448)
    val x5474 = OpDef(op=BitAnd, inputs=List(b2945, b2866)).name("x5474").ctrl(x5476) // And(b2945,b2866)
    val x5475 = StoreBanks(List(x5161_d0_b0), List(b2944), x5473).name("x5475").ctrl(x5476) // SRAMStore(x5161,ArrayBuffer(Const(494)),List(b2944),Const(0),x5473,x5474)
    val x5478 = Reg(init=Some(0)).name("x5478").ctrl(x5549) // x5478 = RegNew(Const(0))
    isAccum(x5478) = false
    bufferDepthOf(x5478) = 2
    val x5479 = Reg(init=Some(0)).name("x5479").ctrl(x5549) // x5479 = RegNew(Const(0))
    isAccum(x5479) = false
    bufferDepthOf(x5479) = 2
    val x5486 = UnitController(style=SeqPipe, level=InnerControl).name("x5486").ctrl(x5549) // UnitPipe(List(b2866),Block(Const(())))
    val x5480 = OpDef(op=FixMul, inputs=List(b2865, Const(494))).name("x5480").ctrl(x5486) // FixMul(b2865,Const(494))
    val x5481 = ReadMem(x5163).name("x5481").ctrl(x5486) // RegRead(x5163)
    val x5482 = OpDef(op=FixMul, inputs=List(x5481, Const(494))).name("x5482").ctrl(x5486) // FixMul(x5481,Const(494))
    val x5483 = OpDef(op=FixSub, inputs=List(x5482, x5480)).name("x5483").ctrl(x5486) // FixSub(x5482,x5480)
    val x5484_x5478 = WriteMem(x5478, x5480).name("x5484_x5478").ctrl(x5486) // RegWrite(x5478,x5480,b2866)
    val x5485_x5479 = WriteMem(x5479, x5483).name("x5485_x5479").ctrl(x5486) // RegWrite(x5479,x5483,b2866)
    val x5548 = UnitController(style=StreamPipe, level=OuterControl).name("x5548").ctrl(x5549) // UnitPipe(List(b2866),Block(Const(())))
    val b5626 = StreamOut(field="offset").name("b5626").ctrl(x5548) // x5487 = StreamOutNew(BurstCmdBus)
    val b5627 = StreamOut(field="size").name("b5627").ctrl(x5548) // x5487 = StreamOutNew(BurstCmdBus)
    val x5488 = StreamOut(field="data").name("x5488").ctrl(x5548) // x5488 = StreamOutNew(BurstFullDataBus())
    val x5489 = StreamIn(field="ack").name("x5489").ctrl(x5548) // x5489 = StreamInNew(BurstAckBus)
    val x5547 = UnitController(style=SeqPipe, level=OuterControl).name("x5547").ctrl(x5548) // UnitPipe(List(b2866),Block(Const(())))
    val x5543 = UnitController(style=SeqPipe, level=OuterControl).name("x5543").ctrl(x5547) // UnitPipe(List(b2866),Block(Const(())))
    val x5490 = Reg(init=Some(0)).name("x5490").ctrl(x5543) // x5490 = RegNew(Const(0))
    isAccum(x5490) = false
    bufferDepthOf(x5490) = 1
    val x5491 = Reg(init=Some(0)).name("x5491").ctrl(x5543) // x5491 = RegNew(Const(0))
    isAccum(x5491) = false
    bufferDepthOf(x5491) = 1
    val x5492 = Reg(init=Some(0)).name("x5492").ctrl(x5543) // x5492 = RegNew(Const(0))
    isAccum(x5492) = false
    bufferDepthOf(x5492) = 1
    val x5525 = UnitController(style=SeqPipe, level=InnerControl).name("x5525").ctrl(x5543) // UnitPipe(List(b2866),Block(x5524))
    val x5493 = ReadMem(x5478).name("x5493").ctrl(x5525) // RegRead(x5478)
    val x5494 = x5493 // FixConvert(x5493,TRUE,_32,_0)
    val x5495 = OpDef(op=FixSla, inputs=List(x5494, Const(2))).name("x5495").ctrl(x5525) // FixLsh(x5494,Const(2))
    val x5496 = x5495 // x5496 = DataAsBits(x5495)
    val x5497 = OpDef(op=BitAnd, inputs=List(x5496, Const(31))).name("x5497").ctrl(x5525) // VectorSlice(x5496,5,0) strMask=00000000000000000000000000011111
    val x5498 = x5497 // x5498 = BitsAsData(x5497,FixPt[TRUE,_32,_0])
    val x5499 = ReadMem(x5479).name("x5499").ctrl(x5525) // RegRead(x5479)
    val x5500 = OpDef(op=FixSla, inputs=List(x5499, Const(2))).name("x5500").ctrl(x5525) // FixLsh(x5499,Const(2))
    val x5501 = OpDef(op=FixSub, inputs=List(x5495, x5498)).name("x5501").ctrl(x5525) // FixSub(x5495,x5498)
    val x5502 = OpDef(op=FixAdd, inputs=List(x5495, x5500)).name("x5502").ctrl(x5525) // FixAdd(x5495,x5500)
    val x5503 = x5502 // x5503 = DataAsBits(x5502)
    val x5504 = OpDef(op=BitAnd, inputs=List(x5503, Const(31))).name("x5504").ctrl(x5525) // VectorSlice(x5503,5,0) strMask=00000000000000000000000000011111
    val x5505 = x5504 // x5505 = BitsAsData(x5504,FixPt[TRUE,_32,_0])
    val x5506 = OpDef(op=FixEql, inputs=List(x5505, Const(0))).name("x5506").ctrl(x5525) // FixEql(x5505,Const(0))
    val x5507 = OpDef(op=FixSub, inputs=List(Const(64), x5505)).name("x5507").ctrl(x5525) // FixSub(Const(64),x5505)
    val x5508 = OpDef(op=MuxOp, inputs=List(x5506, Const(0), x5507)).name("x5508").ctrl(x5525) // Mux(x5506,Const(0),x5507)
    val x5509 = OpDef(op=FixSra, inputs=List(x5498, Const(2))).name("x5509").ctrl(x5525) // FixRsh(x5498,Const(2))
    val x5510 = OpDef(op=FixSra, inputs=List(x5508, Const(2))).name("x5510").ctrl(x5525) // FixRsh(x5508,Const(2))
    val x5511 = OpDef(op=FixAdd, inputs=List(x5509, x5499)).name("x5511").ctrl(x5525) // FixAdd(x5509,x5499)
    val x5512 = OpDef(op=FixAdd, inputs=List(x5499, x5509)).name("x5512").ctrl(x5525) // FixAdd(x5499,x5509)
    val x5513 = OpDef(op=FixAdd, inputs=List(x5512, x5510)).name("x5513").ctrl(x5525) // FixAdd(x5512,x5510)
    val x5514 = OpDef(op=FixAdd, inputs=List(x5500, x5498)).name("x5514").ctrl(x5525) // FixAdd(x5500,x5498)
    val x5520_x5515 = OpDef(op=FixAdd, inputs=List(x5514, x5508)).name("x5515").ctrl(x5525) // FixAdd(x5514,x5508)
    val x5516 = x5501 // FixConvert(x5501,TRUE,_64,_0)
    val x5517 = DramAddress(x5134).name("x5517").ctrl(x5525) // GetDRAMAddress(x5134)
    val x5518 = OpDef(op=FixAdd, inputs=List(x5516, x5517)).name("x5518").ctrl(x5525) // FixAdd(x5516,x5517)
    val x5520_x5519 = x5518 // FixConvert(x5518,TRUE,_64,_0)
    // x5520 = SimpleStruct(ArrayBuffer((offset,x5519), (size,x5515), (isLoad,Const(false))))
    val b5628_b5626 = WriteMem(b5626, x5520_x5519).name("b5628_b5626").ctrl(x5525) // StreamWrite(x5487,x5520,b2866)
    val b5629_b5627 = WriteMem(b5627, x5520_x5515).name("b5629_b5627").ctrl(x5525) // StreamWrite(x5487,x5520,b2866)
    val x5522_x5490 = WriteMem(x5490, x5509).name("x5522_x5490").ctrl(x5525) // RegWrite(x5490,x5509,b2866)
    val x5523_x5491 = WriteMem(x5491, x5511).name("x5523_x5491").ctrl(x5525) // RegWrite(x5491,x5511,b2866)
    val x5524_x5492 = WriteMem(x5492, x5513).name("x5524_x5492").ctrl(x5525) // RegWrite(x5492,x5513,b2866)
    val x5526 = ReadMem(x5492).name("x5526").ctrl(x5543) // RegRead(x5492)
    val x5527 = Counter(min=Const(0), max=x5526, step=Const(1), par=1).name("x5527").ctrl(x5543) // CounterNew(Const(0),x5526,Const(1),Const(1))
    val x5528 = CounterChain(List(x5527)).name("x5528").ctrl(x5543) // CounterChainNew(List(x5527))
    val x5542 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5528).name("x5542").ctrl(x5543) // UnrolledForeach(List(b2866),x5528,Block(Const(())),List(List(b3233)),List(List(b3234)))
    val b3233 = CounterIter(x5527, None).ctrl(x5542).name("b3233")
    val b3234 = DummyOp().ctrl(x5542).name("b3234")
    val x5529 = ReadMem(x5490).name("x5529").ctrl(x5542) // RegRead(x5490)
    val x5530 = OpDef(op=FixLeq, inputs=List(x5529, b3233)).name("x5530").ctrl(x5542) // FixLeq(x5529,b3233)
    val x5531 = ReadMem(x5491).name("x5531").ctrl(x5542) // RegRead(x5491)
    val x5532 = OpDef(op=FixLt, inputs=List(b3233, x5531)).name("x5532").ctrl(x5542) // FixLt(b3233,x5531)
    val x5540_x5533 = OpDef(op=BitAnd, inputs=List(x5530, x5532)).name("x5533").ctrl(x5542) // And(x5530,x5532)
    val x5534 = OpDef(op=FixSub, inputs=List(b3233, x5529)).name("x5534").ctrl(x5542) // FixSub(b3233,x5529)
    val x5535 = OpDef(op=BitAnd, inputs=List(b3234, b2866)).name("x5535").ctrl(x5542) // And(b3234,b2866)
    val x5536 = OpDef(op=BitAnd, inputs=List(x5540_x5533, x5535)).name("x5536").ctrl(x5542) // And(x5533,x5535)
    val x5537 = LoadBanks(List(x5161_d0_b0), List(x5534)).name("x5537").ctrl(x5542) // ParSRAMLoad(x5161,List(List(x5534)),List(x5536))
    val x5538 = x5537 // x5538 = VectorApply(x5537,0)
    val x5540_x5539 = OpDef(op=MuxOp, inputs=List(x5540_x5533, x5538, Const(0.0))).name("x5539").ctrl(x5542) // Mux(x5533,x5538,Const(0))
    // x5540 = SimpleStruct(ArrayBuffer((_1,x5539), (_2,x5533)))
    val x5541_x5488 = WriteMem(x5488, x5540_x5539).name("x5541_x5488").ctrl(x5542) // ParStreamWrite(x5488,List(x5540),List(x5535))
    val x5544 = FringeDenseStore(dram=List(x5134), cmdStream=List(b5626, b5627), dataStream=List(x5488), ackStream=List(x5489)).name("x5544").ctrl(x5547) // FringeDenseStore(x5134,x5487,x5488,x5489)
    val x5546 = UnitController(style=SeqPipe, level=InnerControl).name("x5546").ctrl(x5547) // UnitPipe(List(b2866),Block(Const(())))
    val x5545_x5545 = ReadMem(x5489).name("x5545").ctrl(x5546) // StreamRead(x5489,b2866)
    
  }
}
