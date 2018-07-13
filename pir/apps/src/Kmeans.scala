import pir._
import pir.node._
import arch._
import prism.enums._

object Kmeans extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x5159 = ArgIn(init=0).name("x5159").ctrl(top).srcCtx("Kmeans.scala:27:22:iters") // ArgInNew(Const(0))
    isAccum(x5159) = false
    bufferDepthOf(x5159) = 1
    boundOf(x5159) = 2
    val x5160 = ArgIn(init=0).name("x5160").ctrl(top).srcCtx("Kmeans.scala:28:22:N") // ArgInNew(Const(0))
    isAccum(x5160) = false
    bufferDepthOf(x5160) = 1
    boundOf(x5160) = 64
    val x5163 = ReadMem(x5160).name("x5163").ctrl(top).srcCtx("Kmeans.scala:33:26") // RegRead(x5160)
    val x5164 = DRAM(dims=List(x5163, Const(32))).name("x5164").ctrl(top).srcCtx("Kmeans.scala:33:25:points") // x5164 = DRAMNew(ArrayBuffer(x5163, Const(32)),Const(0))
    val x5165 = DRAM(dims=List(Const(16), Const(32))).name("x5165").ctrl(top).srcCtx("Kmeans.scala:34:28:centroids") // x5165 = DRAMNew(ArrayBuffer(Const(16), Const(32)),Const(0))
    val x5492 = UnitController(style=SeqPipe, level=OuterControl).name("x5492").ctrl(top).srcCtx("Kmeans.scala:38:11") // Hwblock(Block(Const(())),false)
    val x5169_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5169_d0_b0").ctrl(x5492).srcCtx("Kmeans.scala:39:24:cts") // x5169 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5169_d0_b0) = false
    bufferDepthOf(x5169_d0_b0) = 1
    staticDimsOf(x5169_d0_b0) = List(16, 32)
    val x5169_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5169_d1_b0").ctrl(x5492).srcCtx("Kmeans.scala:39:24:cts") // x5169 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5169_d1_b0) = false
    bufferDepthOf(x5169_d1_b0) = 1
    staticDimsOf(x5169_d1_b0) = List(16, 32)
    val x5169_d2_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5169_d2_b0").ctrl(x5492).srcCtx("Kmeans.scala:39:24:cts") // x5169 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5169_d2_b0) = false
    bufferDepthOf(x5169_d2_b0) = 1
    staticDimsOf(x5169_d2_b0) = List(16, 32)
    val x5170 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5170").ctrl(x5492).srcCtx("Kmeans.scala:42:11") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5171 = CounterChain(List(x5170)).name("x5171").ctrl(x5492).srcCtx("Kmeans.scala:42:11") // CounterChainNew(List(x5170))
    val x5193 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5171).name("x5193").ctrl(x5492).srcCtx("Kmeans.scala:42:11") // UnrolledForeach(List(Const(true)),x5171,Block(Const(())),List(List(b2881)),List(List(b2882)))
    val b2881 = CounterIter(x5170, Some(0)).name("b2881").ctrl(x5193) // b2881
    val b2882 = Const(true).name("b2882").ctrl(x5193) // b2882
    val b5596 = StreamOut(field="offset").name("b5596").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // x5172 = StreamOutNew(BurstCmdBus)
    isAccum(b5596) = false
    bufferDepthOf(b5596) = 1
    val b5597 = StreamOut(field="size").name("b5597").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // x5172 = StreamOutNew(BurstCmdBus)
    isAccum(b5597) = false
    bufferDepthOf(b5597) = 1
    val x5173 = StreamIn(field="data").name("x5173").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // x5173 = StreamInNew(BurstDataBus())
    isAccum(x5173) = false
    bufferDepthOf(x5173) = 1
    val x5184 = UnitController(style=SeqPipe, level=InnerControl).name("x5184").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // UnitPipe(List(b2882),Block(x5183))
    val x5174 = b2881 // FixConvert(b2881,TRUE,_32,_0) (Same Type. No op)
    val x5175 = OpDef(op=FixSla, inputs=List(x5174, Const(5))).name("x5175").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // FixLsh(x5174,Const(5))
    val x5176 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5177 = OpDef(op=FixAdd, inputs=List(x5175, x5176)).name("x5177").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // FixAdd(x5175,x5176)
    val x5178 = OpDef(op=FixSla, inputs=List(x5177, Const(2))).name("x5178").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // FixLsh(x5177,Const(2))
    val x5179 = x5178 // FixConvert(x5178,TRUE,_64,_0)
    val x5180 = DramAddress(x5164).name("x5180").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // GetDRAMAddress(x5164)
    val x5182_x5181 = OpDef(op=FixAdd, inputs=List(x5179, x5180)).name("x5182_x5181").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // FixAdd(x5179,x5180)
    // x5182 = SimpleStruct(ArrayBuffer((offset,x5181), (size,Const(128)), (isLoad,Const(true))))
    val x5183_b5598_b5596 = WriteMem(b5596, x5182_x5181).name("x5183_b5598_b5596").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // StreamWrite(x5172,x5182,b2882)
    val x5183_b5599_b5597 = WriteMem(b5597, Const(128)).name("x5183_b5599_b5597").ctrl(x5184).srcCtx("Kmeans.scala:42:11") // StreamWrite(x5172,x5182,b2882)
    val x5185 = FringeDenseLoad(dram=List(x5164), cmdStream=List(b5596, b5597), dataStream=List(x5173)).name("x5185").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // FringeDenseLoad(x5164,x5172,x5173)
    val x5186 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5186").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5187 = CounterChain(List(x5186)).name("x5187").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // CounterChainNew(List(x5186))
    val x5192 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5187).name("x5192").ctrl(x5193).srcCtx("Kmeans.scala:42:11") // UnrolledForeach(List(b2882),x5187,Block(Const(())),List(List(b2899)),List(List(b2900)))
    val b2899 = CounterIter(x5186, None).name("b2899").ctrl(x5192) // b2899
    val b2900 = Const(true).name("b2900").ctrl(x5192) // b2900
    val x5188 = OpDef(op=BitAnd, inputs=List(b2900, b2882)).name("x5188").ctrl(x5192).srcCtx("UnrollingBase.scala:28:66") // And(b2900,b2882)
    val x5189_x5189 = ReadMem(x5173).name("x5189_x5189").ctrl(x5192).srcCtx("Kmeans.scala:42:11") // ParStreamRead(x5173,List(x5188))
    val x5190_x5190 = x5189_x5189 // x5190 = VectorApply(x5189,0)
    val x5191 = StoreBanks(List(List(x5169_d0_b0), List(x5169_d1_b0), List(x5169_d2_b0)), List(b2881, b2899), x5190_x5190).name("x5191").ctrl(x5192).srcCtx("Kmeans.scala:42:11") // ParSRAMStore(x5169,List(List(b2881, b2899)),List(x5190),List(x5188))
    val x5194 = ReadMem(x5159).name("x5194").ctrl(x5492).srcCtx("Kmeans.scala:44:26") // RegRead(x5159)
    val x5195 = Counter(min=Const(0), max=x5194, step=Const(1), par=1).name("x5195").ctrl(x5492).srcCtx("Kmeans.scala:44:32") // CounterNew(Const(0),x5194,Const(1),Const(1))
    val x5196 = CounterChain(List(x5195)).name("x5196").ctrl(x5492).srcCtx("Kmeans.scala:44:37") // CounterChainNew(List(x5195))
    val x5463 = LoopController(style=SeqPipe, level=OuterControl, cchain=x5196).name("x5463").ctrl(x5492).srcCtx("Kmeans.scala:44:37") // UnrolledForeach(List(Const(true)),x5196,Block(Const(())),List(List(b2910)),List(List(b2911)))
    val b2910 = CounterIter(x5195, Some(0)).name("b2910").ctrl(x5463) // b2910
    val b2911 = Const(true).name("b2911").ctrl(x5463) // b2911
    val x5197_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5197_d0_b0").ctrl(x5463).srcCtx("Kmeans.scala:46:41:newCents") // x5197 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5197_d0_b0) = false
    bufferDepthOf(x5197_d0_b0) = 1
    staticDimsOf(x5197_d0_b0) = List(16, 32)
    val x5197_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5197_d1_b0").ctrl(x5463).srcCtx("Kmeans.scala:46:41:newCents") // x5197 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5197_d1_b0) = false
    bufferDepthOf(x5197_d1_b0) = 1
    staticDimsOf(x5197_d1_b0) = List(16, 32)
    val x5197_d2_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5197_d2_b0").ctrl(x5463).srcCtx("Kmeans.scala:46:41:newCents") // x5197 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5197_d2_b0) = true
    bufferDepthOf(x5197_d2_b0) = 1
    staticDimsOf(x5197_d2_b0) = List(16, 32)
    val x5198 = ReadMem(x5160).name("x5198").ctrl(x5463).srcCtx("Kmeans.scala:46:55") // RegRead(x5160)
    val x5199 = Counter(min=Const(0), max=x5198, step=Const(16), par=1).name("x5199").ctrl(x5463).srcCtx("Kmeans.scala:46:63") // CounterNew(Const(0),x5198,Const(16),Const(1))
    val x5200 = CounterChain(List(x5199)).name("x5200").ctrl(x5463).srcCtx("Kmeans.scala:70:10") // CounterChainNew(List(x5199))
    val x5441 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5200).name("x5441").ctrl(x5463).srcCtx("Kmeans.scala:70:10") // UnrolledReduce(List(b2911),x5200,x5197,Block((x5197) => Const(())),List(List(b2919)),List(List(b2920)))
    val b2919 = CounterIter(x5199, Some(0)).name("b2919").ctrl(x5441) // b2919
    val b2920 = Const(true).name("b2920").ctrl(x5441) // b2920
    val x5201_d0_b0 = SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5201_d0_b0").ctrl(x5441).srcCtx("Kmeans.scala:47:28:pts") // x5201 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5201_d0_b0) = false
    bufferDepthOf(x5201_d0_b0) = 2
    staticDimsOf(x5201_d0_b0) = List(16, 32)
    val x5201_d0_b1 = SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5201_d0_b1").ctrl(x5441).srcCtx("Kmeans.scala:47:28:pts") // x5201 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5201_d0_b1) = false
    bufferDepthOf(x5201_d0_b1) = 2
    staticDimsOf(x5201_d0_b1) = List(16, 32)
    val x5201_d1_b0 = SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5201_d1_b0").ctrl(x5441).srcCtx("Kmeans.scala:47:28:pts") // x5201 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5201_d1_b0) = false
    bufferDepthOf(x5201_d1_b0) = 2
    staticDimsOf(x5201_d1_b0) = List(16, 32)
    val x5201_d1_b1 = SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x5201_d1_b1").ctrl(x5441).srcCtx("Kmeans.scala:47:28:pts") // x5201 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5201_d1_b1) = false
    bufferDepthOf(x5201_d1_b1) = 2
    staticDimsOf(x5201_d1_b1) = List(16, 32)
    val x5203 = UnitController(style=SeqPipe, level=InnerControl).name("x5203").ctrl(x5441).srcCtx("Kmeans.scala:70:10") // UnitPipe(List(b2920, b2911),Block(Const(())))
    val x5202 = OpDef(op=FixAdd, inputs=List(b2919, Const(16))).name("x5202").ctrl(x5203).srcCtx("Kmeans.scala:48:31") // FixAdd(b2919,Const(16))
    val x5204 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5204").ctrl(x5441).srcCtx("Kmeans.scala:48:15") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5205 = CounterChain(List(x5204)).name("x5205").ctrl(x5441).srcCtx("Kmeans.scala:48:15") // CounterChainNew(List(x5204))
    val x5232 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5205).name("x5232").ctrl(x5441).srcCtx("Kmeans.scala:48:15") // UnrolledForeach(List(b2920, b2911),x5205,Block(Const(())),List(List(b2926)),List(List(b2927)))
    val b2926 = CounterIter(x5204, Some(0)).name("b2926").ctrl(x5232) // b2926
    val b2927 = Const(true).name("b2927").ctrl(x5232) // b2927
    val b5600 = StreamOut(field="offset").name("b5600").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // x5206 = StreamOutNew(BurstCmdBus)
    isAccum(b5600) = false
    bufferDepthOf(b5600) = 1
    val b5601 = StreamOut(field="size").name("b5601").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // x5206 = StreamOutNew(BurstCmdBus)
    isAccum(b5601) = false
    bufferDepthOf(b5601) = 1
    val x5207 = StreamIn(field="data").name("x5207").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // x5207 = StreamInNew(BurstDataBus())
    isAccum(x5207) = false
    bufferDepthOf(x5207) = 1
    val x5221 = UnitController(style=SeqPipe, level=InnerControl).name("x5221").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // UnitPipe(List(b2927, b2920, b2911),Block(x5220))
    val x5208 = OpDef(op=FixAdd, inputs=List(b2919, b2926)).name("x5208").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // FixAdd(b2919,b2926)
    val x5209 = x5208 // FixConvert(x5208,TRUE,_32,_0) (Same Type. No op)
    val x5210 = OpDef(op=FixSla, inputs=List(x5209, Const(5))).name("x5210").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // FixLsh(x5209,Const(5))
    val x5211 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5212 = OpDef(op=FixAdd, inputs=List(x5210, x5211)).name("x5212").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // FixAdd(x5210,x5211)
    val x5213 = OpDef(op=FixSla, inputs=List(x5212, Const(2))).name("x5213").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // FixLsh(x5212,Const(2))
    val x5214 = x5213 // FixConvert(x5213,TRUE,_64,_0)
    val x5215 = DramAddress(x5164).name("x5215").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // GetDRAMAddress(x5164)
    val x5217_x5216 = OpDef(op=FixAdd, inputs=List(x5214, x5215)).name("x5217_x5216").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // FixAdd(x5214,x5215)
    // x5217 = SimpleStruct(ArrayBuffer((offset,x5216), (size,Const(128)), (isLoad,Const(true))))
    val x5218 = OpDef(op=BitAnd, inputs=List(b2927, b2920)).name("x5218").ctrl(x5221).srcCtx("UnrollingBase.scala:28:66") // And(b2927,b2920)
    val x5219 = OpDef(op=BitAnd, inputs=List(x5218, b2911)).name("x5219").ctrl(x5221).srcCtx("UnrollingBase.scala:28:66") // And(x5218,b2911)
    val x5220_b5602_b5600 = WriteMem(b5600, x5217_x5216).name("x5220_b5602_b5600").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // StreamWrite(x5206,x5217,x5219)
    val x5220_b5603_b5601 = WriteMem(b5601, Const(128)).name("x5220_b5603_b5601").ctrl(x5221).srcCtx("Kmeans.scala:48:15") // StreamWrite(x5206,x5217,x5219)
    val x5222 = FringeDenseLoad(dram=List(x5164), cmdStream=List(b5600, b5601), dataStream=List(x5207)).name("x5222").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // FringeDenseLoad(x5164,x5206,x5207)
    val x5223 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5223").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5224 = CounterChain(List(x5223)).name("x5224").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // CounterChainNew(List(x5223))
    val x5231 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5224).name("x5231").ctrl(x5232).srcCtx("Kmeans.scala:48:15") // UnrolledForeach(List(b2927, b2920, b2911),x5224,Block(Const(())),List(List(b2947)),List(List(b2948)))
    val b2947 = CounterIter(x5223, None).name("b2947").ctrl(x5231) // b2947
    val b2948 = Const(true).name("b2948").ctrl(x5231) // b2948
    val x5225 = OpDef(op=BitAnd, inputs=List(b2948, b2927)).name("x5225").ctrl(x5231).srcCtx("UnrollingBase.scala:28:66") // And(b2948,b2927)
    val x5226 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5226").ctrl(x5231).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5227 = OpDef(op=BitAnd, inputs=List(x5225, x5226)).name("x5227").ctrl(x5231).srcCtx("UnrollingBase.scala:28:66") // And(x5225,x5226)
    val x5228_x5228 = ReadMem(x5207).name("x5228_x5228").ctrl(x5231).srcCtx("Kmeans.scala:48:15") // ParStreamRead(x5207,List(x5227))
    val x5229_x5229 = x5228_x5228 // x5229 = VectorApply(x5228,0)
    val x5230 = StoreBanks(List(List(x5201_d0_b0, x5201_d0_b1), List(x5201_d1_b0, x5201_d1_b1)), List(b2926, b2947), x5229_x5229).name("x5230").ctrl(x5231).srcCtx("Kmeans.scala:48:15") // ParSRAMStore(x5201,List(List(b2926, b2947)),List(x5229),List(x5227))
    val x5233_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5233_d0_b0").ctrl(x5441).srcCtx("Kmeans.scala:51:28") // x5233 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5233_d0_b0) = false
    bufferDepthOf(x5233_d0_b0) = 2
    staticDimsOf(x5233_d0_b0) = List(16, 32)
    val x5233_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5233_d1_b0").ctrl(x5441).srcCtx("Kmeans.scala:51:28") // x5233 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5233_d1_b0) = true
    bufferDepthOf(x5233_d1_b0) = 1
    staticDimsOf(x5233_d1_b0) = List(16, 32)
    val x5234 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2).name("x5234").ctrl(x5441).srcCtx("Kmeans.scala:51:45") // CounterNew(Const(0),Const(16),Const(1),Const(2))
    val x5235 = CounterChain(List(x5234)).name("x5235").ctrl(x5441).srcCtx("Kmeans.scala:69:12") // CounterChainNew(List(x5234))
    val x5424 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5235).name("x5424").ctrl(x5441).srcCtx("Kmeans.scala:69:12") // UnrolledReduce(List(b2920, b2911),x5235,x5233,Block((x5233) => Const(())),List(List(b2963, b2964)),List(List(b2965, b2966)))
    val b2963 = CounterIter(x5234, Some(0)).name("b2963").ctrl(x5424) // b2963
    val b2965 = Const(true).name("b2965").ctrl(x5424) // b2965
    val b2964 = CounterIter(x5234, Some(1)).name("b2964").ctrl(x5424) // b2964
    val b2966 = Const(true).name("b2966").ctrl(x5424) // b2966
    val x5236_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x5236_d0_b0").ctrl(x5424).srcCtx("Kmeans.scala:53:32:dists") // x5236 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x5236_d0_b0) = false
    bufferDepthOf(x5236_d0_b0) = 3
    staticDimsOf(x5236_d0_b0) = List(16)
    val x5236_d1_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x5236_d1_b0").ctrl(x5424).srcCtx("Kmeans.scala:53:32:dists") // x5236 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x5236_d1_b0) = false
    bufferDepthOf(x5236_d1_b0) = 2
    staticDimsOf(x5236_d1_b0) = List(16)
    val x5237_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x5237_d0_b0").ctrl(x5424).srcCtx("Kmeans.scala:53:32:dists") // x5237 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x5237_d0_b0) = false
    bufferDepthOf(x5237_d0_b0) = 3
    staticDimsOf(x5237_d0_b0) = List(16)
    val x5237_d1_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x5237_d1_b0").ctrl(x5424).srcCtx("Kmeans.scala:53:32:dists") // x5237 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x5237_d1_b0) = false
    bufferDepthOf(x5237_d1_b0) = 2
    staticDimsOf(x5237_d1_b0) = List(16)
    val x5298 = UnitController(style=ForkJoin, level=OuterControl).name("x5298").ctrl(x5424).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x5238 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5238").ctrl(x5298).srcCtx("Kmeans.scala:54:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5239 = CounterChain(List(x5238)).name("x5239").ctrl(x5298).srcCtx("Kmeans.scala:54:37") // CounterChainNew(List(x5238))
    val x5267 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5239).name("x5267").ctrl(x5298).srcCtx("Kmeans.scala:54:37") // UnrolledForeach(List(b2965, b2920, b2911),x5239,Block(Const(())),List(List(b2973)),List(List(b2974)))
    val b2973 = CounterIter(x5238, Some(0)).name("b2973").ctrl(x5267) // b2973
    val b2974 = Const(true).name("b2974").ctrl(x5267) // b2974
    val x5240_d0 = Reg(init=Some(0)).name("x5240_d0").ctrl(x5267).srcCtx("Kmeans.scala:55:36:dist") // x5240 = RegNew(Const(0))
    isAccum(x5240_d0) = false
    bufferDepthOf(x5240_d0) = 2
    val x5240_d1 = Reg(init=Some(0)).name("x5240_d1").ctrl(x5267).srcCtx("Kmeans.scala:55:36:dist") // x5240 = RegNew(Const(0))
    isAccum(x5240_d1) = true
    bufferDepthOf(x5240_d1) = 1
    val x5241 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5241").ctrl(x5267).srcCtx("Kmeans.scala:55:43") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5242 = CounterChain(List(x5241)).name("x5242").ctrl(x5267).srcCtx("Kmeans.scala:55:86") // CounterChainNew(List(x5241))
    val x5260 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5242).name("x5260").ctrl(x5267).srcCtx("Kmeans.scala:55:86") // UnrolledReduce(List(b2974, b2965, b2920, b2911),x5242,x5240,Block((x5240) => Const(())),List(List(b2978)),List(List(b2979)))
    val b2978 = CounterIter(x5241, None).name("b2978").ctrl(x5260) // b2978
    val b2979 = Const(true).name("b2979").ctrl(x5260) // b2979
    val x5243 = OpDef(op=BitAnd, inputs=List(b2979, b2974)).name("x5243").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(b2979,b2974)
    val x5244 = OpDef(op=BitAnd, inputs=List(b2965, b2920)).name("x5244").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(b2965,b2920)
    val x5245 = OpDef(op=BitAnd, inputs=List(x5243, x5244)).name("x5245").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(x5243,x5244)
    val x5246 = OpDef(op=BitAnd, inputs=List(x5245, b2911)).name("x5246").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(x5245,b2911)
    val x5247 = LoadBanks(List(x5201_d1_b0), List(b2963, b2978)).name("x5247").ctrl(x5260).srcCtx("Kmeans.scala:55:60") // ParSRAMLoad(x5201,List(List(b2963, b2978)),List(x5246))
    val x5248 = x5247 // x5248 = VectorApply(x5247,0)
    val x5249 = LoadBanks(List(x5169_d1_b0), List(b2973, b2978)).name("x5249").ctrl(x5260).srcCtx("Kmeans.scala:55:72") // ParSRAMLoad(x5169,List(List(b2973, b2978)),List(x5246))
    val x5250 = x5249 // x5250 = VectorApply(x5249,0)
    val x5251 = OpDef(op=FixSub, inputs=List(x5248, x5250)).name("x5251").ctrl(x5260).srcCtx("Kmeans.scala:55:67") // FixSub(x5248,x5250)
    val x5252 = OpDef(op=FixMul, inputs=List(x5251, x5251)).name("x5252").ctrl(x5260).srcCtx("Kmeans.scala:55:80") // FixMul(x5251,x5251)
    val x5253 = ReadMem(x5240_d1).name("x5253").ctrl(x5260).srcCtx("Kmeans.scala:55:86") // RegRead(x5240)
    val x5254 = OpDef(op=FixEql, inputs=List(b2978, Const(0))).name("x5254").ctrl(x5260).srcCtx("Kmeans.scala:55:86") // FixEql(b2978,Const(0))
    val x5255 = ReduceAccumOp(op=FixAdd, input=x5252, accum=x5253).name("x5255").ctrl(x5260).srcCtx("Kmeans.scala:55:88") // FixAdd(x5252,x5253)
    val x5256 = OpDef(op=BitAnd, inputs=List(b2974, b2965)).name("x5256").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(b2974,b2965)
    val x5257 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5257").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5258 = OpDef(op=BitAnd, inputs=List(x5256, x5257)).name("x5258").ctrl(x5260).srcCtx("UnrollingBase.scala:28:66") // And(x5256,x5257)
    val x5259_x5240_d0 = WriteMem(x5240_d0, x5255).name("x5259_x5240_d0").ctrl(x5260).srcCtx("Kmeans.scala:55:86") // RegWrite(x5240,x5255,x5258)
    antiDepsOf(x5259_x5240_d0)=List(x5253)
    val x5259_x5240_d1 = WriteMem(x5240_d1, x5255).name("x5259_x5240_d1").ctrl(x5260).srcCtx("Kmeans.scala:55:86") // RegWrite(x5240,x5255,x5258)
    antiDepsOf(x5259_x5240_d1)=List(x5253)
    val x5266 = UnitController(style=SeqPipe, level=InnerControl).name("x5266").ctrl(x5267).srcCtx("Kmeans.scala:54:37") // UnitPipe(List(b2974, b2965, b2920, b2911),Block(Const(())))
    val x5261 = ReadMem(x5240_d0).name("x5261").ctrl(x5266).srcCtx("Kmeans.scala:56:32") // RegRead(x5240)
    val x5262 = OpDef(op=BitAnd, inputs=List(b2974, b2965)).name("x5262").ctrl(x5266).srcCtx("UnrollingBase.scala:28:66") // And(b2974,b2965)
    val x5263 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5263").ctrl(x5266).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5264 = OpDef(op=BitAnd, inputs=List(x5262, x5263)).name("x5264").ctrl(x5266).srcCtx("UnrollingBase.scala:28:66") // And(x5262,x5263)
    val x5265 = StoreBanks(List(List(x5236_d0_b0), List(x5236_d1_b0)), List(b2973), x5261).name("x5265").ctrl(x5266).srcCtx("Kmeans.scala:56:25") // SRAMStore(x5236,ArrayBuffer(Const(16)),List(b2973),Const(0),x5261,x5264)
    val x5268 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5268").ctrl(x5298).srcCtx("Kmeans.scala:54:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5269 = CounterChain(List(x5268)).name("x5269").ctrl(x5298).srcCtx("Kmeans.scala:54:37") // CounterChainNew(List(x5268))
    val x5297 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5269).name("x5297").ctrl(x5298).srcCtx("Kmeans.scala:54:37") // UnrolledForeach(List(b2966, b2920, b2911),x5269,Block(Const(())),List(List(b3005)),List(List(b3006)))
    val b3005 = CounterIter(x5268, Some(0)).name("b3005").ctrl(x5297) // b3005
    val b3006 = Const(true).name("b3006").ctrl(x5297) // b3006
    val x5270_d0 = Reg(init=Some(0)).name("x5270_d0").ctrl(x5297).srcCtx("Kmeans.scala:55:36:dist") // x5270 = RegNew(Const(0))
    isAccum(x5270_d0) = false
    bufferDepthOf(x5270_d0) = 2
    val x5270_d1 = Reg(init=Some(0)).name("x5270_d1").ctrl(x5297).srcCtx("Kmeans.scala:55:36:dist") // x5270 = RegNew(Const(0))
    isAccum(x5270_d1) = true
    bufferDepthOf(x5270_d1) = 1
    val x5271 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5271").ctrl(x5297).srcCtx("Kmeans.scala:55:43") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5272 = CounterChain(List(x5271)).name("x5272").ctrl(x5297).srcCtx("Kmeans.scala:55:86") // CounterChainNew(List(x5271))
    val x5290 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5272).name("x5290").ctrl(x5297).srcCtx("Kmeans.scala:55:86") // UnrolledReduce(List(b3006, b2966, b2920, b2911),x5272,x5270,Block((x5270) => Const(())),List(List(b3010)),List(List(b3011)))
    val b3010 = CounterIter(x5271, None).name("b3010").ctrl(x5290) // b3010
    val b3011 = Const(true).name("b3011").ctrl(x5290) // b3011
    val x5273 = OpDef(op=BitAnd, inputs=List(b3011, b3006)).name("x5273").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(b3011,b3006)
    val x5274 = OpDef(op=BitAnd, inputs=List(b2966, b2920)).name("x5274").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(b2966,b2920)
    val x5275 = OpDef(op=BitAnd, inputs=List(x5273, x5274)).name("x5275").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(x5273,x5274)
    val x5276 = OpDef(op=BitAnd, inputs=List(x5275, b2911)).name("x5276").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(x5275,b2911)
    val x5277 = LoadBanks(List(x5201_d1_b1), List(b2964, b3010)).name("x5277").ctrl(x5290).srcCtx("Kmeans.scala:55:60") // ParSRAMLoad(x5201,List(List(b2964, b3010)),List(x5276))
    val x5278 = x5277 // x5278 = VectorApply(x5277,0)
    val x5279 = LoadBanks(List(x5169_d2_b0), List(b3005, b3010)).name("x5279").ctrl(x5290).srcCtx("Kmeans.scala:55:72") // ParSRAMLoad(x5169,List(List(b3005, b3010)),List(x5276))
    val x5280 = x5279 // x5280 = VectorApply(x5279,0)
    val x5281 = OpDef(op=FixSub, inputs=List(x5278, x5280)).name("x5281").ctrl(x5290).srcCtx("Kmeans.scala:55:67") // FixSub(x5278,x5280)
    val x5282 = OpDef(op=FixMul, inputs=List(x5281, x5281)).name("x5282").ctrl(x5290).srcCtx("Kmeans.scala:55:80") // FixMul(x5281,x5281)
    val x5283 = ReadMem(x5270_d1).name("x5283").ctrl(x5290).srcCtx("Kmeans.scala:55:86") // RegRead(x5270)
    val x5284 = OpDef(op=FixEql, inputs=List(b3010, Const(0))).name("x5284").ctrl(x5290).srcCtx("Kmeans.scala:55:86") // FixEql(b3010,Const(0))
    val x5285 = ReduceAccumOp(op=FixAdd, input=x5282, accum=x5283).name("x5285").ctrl(x5290).srcCtx("Kmeans.scala:55:88") // FixAdd(x5282,x5283)
    val x5286 = OpDef(op=BitAnd, inputs=List(b3006, b2966)).name("x5286").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(b3006,b2966)
    val x5287 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5287").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5288 = OpDef(op=BitAnd, inputs=List(x5286, x5287)).name("x5288").ctrl(x5290).srcCtx("UnrollingBase.scala:28:66") // And(x5286,x5287)
    val x5289_x5270_d0 = WriteMem(x5270_d0, x5285).name("x5289_x5270_d0").ctrl(x5290).srcCtx("Kmeans.scala:55:86") // RegWrite(x5270,x5285,x5288)
    antiDepsOf(x5289_x5270_d0)=List(x5283)
    val x5289_x5270_d1 = WriteMem(x5270_d1, x5285).name("x5289_x5270_d1").ctrl(x5290).srcCtx("Kmeans.scala:55:86") // RegWrite(x5270,x5285,x5288)
    antiDepsOf(x5289_x5270_d1)=List(x5283)
    val x5296 = UnitController(style=SeqPipe, level=InnerControl).name("x5296").ctrl(x5297).srcCtx("Kmeans.scala:54:37") // UnitPipe(List(b3006, b2966, b2920, b2911),Block(Const(())))
    val x5291 = ReadMem(x5270_d0).name("x5291").ctrl(x5296).srcCtx("Kmeans.scala:56:32") // RegRead(x5270)
    val x5292 = OpDef(op=BitAnd, inputs=List(b3006, b2966)).name("x5292").ctrl(x5296).srcCtx("UnrollingBase.scala:28:66") // And(b3006,b2966)
    val x5293 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5293").ctrl(x5296).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5294 = OpDef(op=BitAnd, inputs=List(x5292, x5293)).name("x5294").ctrl(x5296).srcCtx("UnrollingBase.scala:28:66") // And(x5292,x5293)
    val x5295 = StoreBanks(List(List(x5237_d0_b0), List(x5237_d1_b0)), List(b3005), x5291).name("x5295").ctrl(x5296).srcCtx("Kmeans.scala:56:25") // SRAMStore(x5237,ArrayBuffer(Const(16)),List(b3005),Const(0),x5291,x5294)
    val x5299_d0 = Reg(init=Some(0)).name("x5299_d0").ctrl(x5424).srcCtx("Kmeans.scala:58:37:minDist") // x5299 = RegNew(Const(0))
    isAccum(x5299_d0) = false
    bufferDepthOf(x5299_d0) = 2
    val x5299_d1 = Reg(init=Some(0)).name("x5299_d1").ctrl(x5424).srcCtx("Kmeans.scala:58:37:minDist") // x5299 = RegNew(Const(0))
    isAccum(x5299_d1) = true
    bufferDepthOf(x5299_d1) = 1
    val x5300_d0 = Reg(init=Some(0)).name("x5300_d0").ctrl(x5424).srcCtx("Kmeans.scala:58:37:minDist") // x5300 = RegNew(Const(0))
    isAccum(x5300_d0) = false
    bufferDepthOf(x5300_d0) = 2
    val x5300_d1 = Reg(init=Some(0)).name("x5300_d1").ctrl(x5424).srcCtx("Kmeans.scala:58:37:minDist") // x5300 = RegNew(Const(0))
    isAccum(x5300_d1) = true
    bufferDepthOf(x5300_d1) = 1
    val x5329 = UnitController(style=ForkJoin, level=OuterControl).name("x5329").ctrl(x5424).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x5301 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x5301").ctrl(x5329).srcCtx("Kmeans.scala:58:49") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x5302 = CounterChain(List(x5301)).name("x5302").ctrl(x5329).srcCtx("Kmeans.scala:58:77") // CounterChainNew(List(x5301))
    val x5314 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5302).name("x5314").ctrl(x5329).srcCtx("Kmeans.scala:58:77") // UnrolledReduce(List(b2965, b2920, b2911),x5302,x5299,Block((x5299) => Const(())),List(List(b3044)),List(List(b3045)))
    val b3044 = CounterIter(x5301, None).name("b3044").ctrl(x5314) // b3044
    val b3045 = Const(true).name("b3045").ctrl(x5314) // b3045
    val x5303 = OpDef(op=BitAnd, inputs=List(b3045, b2965)).name("x5303").ctrl(x5314).srcCtx("UnrollingBase.scala:28:66") // And(b3045,b2965)
    val x5304 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5304").ctrl(x5314).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5305 = OpDef(op=BitAnd, inputs=List(x5303, x5304)).name("x5305").ctrl(x5314).srcCtx("UnrollingBase.scala:28:66") // And(x5303,x5304)
    val x5306 = LoadBanks(List(x5236_d1_b0), List(b3044)).name("x5306").ctrl(x5314).srcCtx("Kmeans.scala:58:70") // ParSRAMLoad(x5236,List(List(b3044)),List(x5305))
    val x5307 = x5306 // x5307 = VectorApply(x5306,0)
    val x5308 = ReadMem(x5299_d1).name("x5308").ctrl(x5314).srcCtx("Kmeans.scala:58:77") // RegRead(x5299)
    val x5309 = OpDef(op=FixEql, inputs=List(b3044, Const(0))).name("x5309").ctrl(x5314).srcCtx("Kmeans.scala:58:77") // FixEql(b3044,Const(0))
    val x5310 = ReduceAccumOp(op=FixMin, input=x5307, accum=x5308).name("x5310").ctrl(x5314).srcCtx("Kmeans.scala:58:91") // Min(x5307,x5308)
    val x5311 = OpDef(op=BitAnd, inputs=List(b2965, b2920)).name("x5311").ctrl(x5314).srcCtx("UnrollingBase.scala:28:66") // And(b2965,b2920)
    val x5312 = OpDef(op=BitAnd, inputs=List(x5311, b2911)).name("x5312").ctrl(x5314).srcCtx("UnrollingBase.scala:28:66") // And(x5311,b2911)
    val x5313_x5299_d0 = WriteMem(x5299_d0, x5310).name("x5313_x5299_d0").ctrl(x5314).srcCtx("Kmeans.scala:58:77") // RegWrite(x5299,x5310,x5312)
    antiDepsOf(x5313_x5299_d0)=List(x5308)
    val x5313_x5299_d1 = WriteMem(x5299_d1, x5310).name("x5313_x5299_d1").ctrl(x5314).srcCtx("Kmeans.scala:58:77") // RegWrite(x5299,x5310,x5312)
    antiDepsOf(x5313_x5299_d1)=List(x5308)
    val x5315 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x5315").ctrl(x5329).srcCtx("Kmeans.scala:58:49") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x5316 = CounterChain(List(x5315)).name("x5316").ctrl(x5329).srcCtx("Kmeans.scala:58:77") // CounterChainNew(List(x5315))
    val x5328 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5316).name("x5328").ctrl(x5329).srcCtx("Kmeans.scala:58:77") // UnrolledReduce(List(b2966, b2920, b2911),x5316,x5300,Block((x5300) => Const(())),List(List(b3058)),List(List(b3059)))
    val b3058 = CounterIter(x5315, None).name("b3058").ctrl(x5328) // b3058
    val b3059 = Const(true).name("b3059").ctrl(x5328) // b3059
    val x5317 = OpDef(op=BitAnd, inputs=List(b3059, b2966)).name("x5317").ctrl(x5328).srcCtx("UnrollingBase.scala:28:66") // And(b3059,b2966)
    val x5318 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5318").ctrl(x5328).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5319 = OpDef(op=BitAnd, inputs=List(x5317, x5318)).name("x5319").ctrl(x5328).srcCtx("UnrollingBase.scala:28:66") // And(x5317,x5318)
    val x5320 = LoadBanks(List(x5237_d1_b0), List(b3058)).name("x5320").ctrl(x5328).srcCtx("Kmeans.scala:58:70") // ParSRAMLoad(x5237,List(List(b3058)),List(x5319))
    val x5321 = x5320 // x5321 = VectorApply(x5320,0)
    val x5322 = ReadMem(x5300_d1).name("x5322").ctrl(x5328).srcCtx("Kmeans.scala:58:77") // RegRead(x5300)
    val x5323 = OpDef(op=FixEql, inputs=List(b3058, Const(0))).name("x5323").ctrl(x5328).srcCtx("Kmeans.scala:58:77") // FixEql(b3058,Const(0))
    val x5324 = ReduceAccumOp(op=FixMin, input=x5321, accum=x5322).name("x5324").ctrl(x5328).srcCtx("Kmeans.scala:58:91") // Min(x5321,x5322)
    val x5325 = OpDef(op=BitAnd, inputs=List(b2966, b2920)).name("x5325").ctrl(x5328).srcCtx("UnrollingBase.scala:28:66") // And(b2966,b2920)
    val x5326 = OpDef(op=BitAnd, inputs=List(x5325, b2911)).name("x5326").ctrl(x5328).srcCtx("UnrollingBase.scala:28:66") // And(x5325,b2911)
    val x5327_x5300_d0 = WriteMem(x5300_d0, x5324).name("x5327_x5300_d0").ctrl(x5328).srcCtx("Kmeans.scala:58:77") // RegWrite(x5300,x5324,x5326)
    antiDepsOf(x5327_x5300_d0)=List(x5322)
    val x5327_x5300_d1 = WriteMem(x5300_d1, x5324).name("x5327_x5300_d1").ctrl(x5328).srcCtx("Kmeans.scala:58:77") // RegWrite(x5300,x5324,x5326)
    antiDepsOf(x5327_x5300_d1)=List(x5322)
    val x5330_d0 = Reg(init=Some(0)).name("x5330_d0").ctrl(x5424).srcCtx("Kmeans.scala:59:37:minCent") // x5330 = RegNew(Const(0))
    isAccum(x5330_d0) = false
    bufferDepthOf(x5330_d0) = 2
    val x5330_d1 = Reg(init=Some(0)).name("x5330_d1").ctrl(x5424).srcCtx("Kmeans.scala:59:37:minCent") // x5330 = RegNew(Const(0))
    isAccum(x5330_d1) = true
    bufferDepthOf(x5330_d1) = 1
    val x5331_d0 = Reg(init=Some(0)).name("x5331_d0").ctrl(x5424).srcCtx("Kmeans.scala:59:37:minCent") // x5331 = RegNew(Const(0))
    isAccum(x5331_d0) = false
    bufferDepthOf(x5331_d0) = 2
    val x5331_d1 = Reg(init=Some(0)).name("x5331_d1").ctrl(x5424).srcCtx("Kmeans.scala:59:37:minCent") // x5331 = RegNew(Const(0))
    isAccum(x5331_d1) = true
    bufferDepthOf(x5331_d1) = 1
    val x5366 = UnitController(style=ForkJoin, level=OuterControl).name("x5366").ctrl(x5424).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x5332 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x5332").ctrl(x5366).srcCtx("Kmeans.scala:59:53") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x5333 = CounterChain(List(x5332)).name("x5333").ctrl(x5366).srcCtx("Kmeans.scala:61:15") // CounterChainNew(List(x5332))
    val x5348 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5333).name("x5348").ctrl(x5366).srcCtx("Kmeans.scala:61:15") // UnrolledReduce(List(b2965, b2920, b2911),x5333,x5330,Block((x5330) => Const(())),List(List(b3079)),List(List(b3080)))
    val b3079 = CounterIter(x5332, None).name("b3079").ctrl(x5348) // b3079
    val b3080 = Const(true).name("b3080").ctrl(x5348) // b3080
    val x5334 = OpDef(op=BitAnd, inputs=List(b3080, b2965)).name("x5334").ctrl(x5348).srcCtx("UnrollingBase.scala:28:66") // And(b3080,b2965)
    val x5335 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5335").ctrl(x5348).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5336 = OpDef(op=BitAnd, inputs=List(x5334, x5335)).name("x5336").ctrl(x5348).srcCtx("UnrollingBase.scala:28:66") // And(x5334,x5335)
    val x5337 = LoadBanks(List(x5236_d0_b0), List(b3079)).name("x5337").ctrl(x5348).srcCtx("Kmeans.scala:60:24") // ParSRAMLoad(x5236,List(List(b3079)),List(x5336))
    val x5338 = x5337 // x5338 = VectorApply(x5337,0)
    val x5339 = ReadMem(x5299_d0).name("x5339").ctrl(x5348).srcCtx("Kmeans.scala:60:40") // RegRead(x5299)
    val x5340 = OpDef(op=FixEql, inputs=List(x5338, x5339)).name("x5340").ctrl(x5348).srcCtx("Kmeans.scala:60:29") // FixEql(x5338,x5339)
    val x5341 = OpDef(op=MuxOp, inputs=List(x5340, b3079, Const(-1))).name("x5341").ctrl(x5348).srcCtx("Kmeans.scala:60:18") // Mux(x5340,b3079,Const(-1))
    val x5342 = ReadMem(x5330_d1).name("x5342").ctrl(x5348).srcCtx("Kmeans.scala:61:15") // RegRead(x5330)
    val x5343 = OpDef(op=FixEql, inputs=List(b3079, Const(0))).name("x5343").ctrl(x5348).srcCtx("Kmeans.scala:61:15") // FixEql(b3079,Const(0))
    val x5344 = ReduceAccumOp(op=FixMax, input=x5341, accum=x5342).name("x5344").ctrl(x5348).srcCtx("Kmeans.scala:61:29") // Max(x5341,x5342)
    val x5345 = OpDef(op=BitAnd, inputs=List(b2965, b2920)).name("x5345").ctrl(x5348).srcCtx("UnrollingBase.scala:28:66") // And(b2965,b2920)
    val x5346 = OpDef(op=BitAnd, inputs=List(x5345, b2911)).name("x5346").ctrl(x5348).srcCtx("UnrollingBase.scala:28:66") // And(x5345,b2911)
    val x5347_x5330_d0 = WriteMem(x5330_d0, x5344).name("x5347_x5330_d0").ctrl(x5348).srcCtx("Kmeans.scala:61:15") // RegWrite(x5330,x5344,x5346)
    antiDepsOf(x5347_x5330_d0)=List(x5342)
    val x5347_x5330_d1 = WriteMem(x5330_d1, x5344).name("x5347_x5330_d1").ctrl(x5348).srcCtx("Kmeans.scala:61:15") // RegWrite(x5330,x5344,x5346)
    antiDepsOf(x5347_x5330_d1)=List(x5342)
    val x5349 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x5349").ctrl(x5366).srcCtx("Kmeans.scala:59:53") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x5350 = CounterChain(List(x5349)).name("x5350").ctrl(x5366).srcCtx("Kmeans.scala:61:15") // CounterChainNew(List(x5349))
    val x5365 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5350).name("x5365").ctrl(x5366).srcCtx("Kmeans.scala:61:15") // UnrolledReduce(List(b2966, b2920, b2911),x5350,x5331,Block((x5331) => Const(())),List(List(b3096)),List(List(b3097)))
    val b3096 = CounterIter(x5349, None).name("b3096").ctrl(x5365) // b3096
    val b3097 = Const(true).name("b3097").ctrl(x5365) // b3097
    val x5351 = OpDef(op=BitAnd, inputs=List(b3097, b2966)).name("x5351").ctrl(x5365).srcCtx("UnrollingBase.scala:28:66") // And(b3097,b2966)
    val x5352 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5352").ctrl(x5365).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5353 = OpDef(op=BitAnd, inputs=List(x5351, x5352)).name("x5353").ctrl(x5365).srcCtx("UnrollingBase.scala:28:66") // And(x5351,x5352)
    val x5354 = LoadBanks(List(x5237_d0_b0), List(b3096)).name("x5354").ctrl(x5365).srcCtx("Kmeans.scala:60:24") // ParSRAMLoad(x5237,List(List(b3096)),List(x5353))
    val x5355 = x5354 // x5355 = VectorApply(x5354,0)
    val x5356 = ReadMem(x5300_d0).name("x5356").ctrl(x5365).srcCtx("Kmeans.scala:60:40") // RegRead(x5300)
    val x5357 = OpDef(op=FixEql, inputs=List(x5355, x5356)).name("x5357").ctrl(x5365).srcCtx("Kmeans.scala:60:29") // FixEql(x5355,x5356)
    val x5358 = OpDef(op=MuxOp, inputs=List(x5357, b3096, Const(-1))).name("x5358").ctrl(x5365).srcCtx("Kmeans.scala:60:18") // Mux(x5357,b3096,Const(-1))
    val x5359 = ReadMem(x5331_d1).name("x5359").ctrl(x5365).srcCtx("Kmeans.scala:61:15") // RegRead(x5331)
    val x5360 = OpDef(op=FixEql, inputs=List(b3096, Const(0))).name("x5360").ctrl(x5365).srcCtx("Kmeans.scala:61:15") // FixEql(b3096,Const(0))
    val x5361 = ReduceAccumOp(op=FixMax, input=x5358, accum=x5359).name("x5361").ctrl(x5365).srcCtx("Kmeans.scala:61:29") // Max(x5358,x5359)
    val x5362 = OpDef(op=BitAnd, inputs=List(b2966, b2920)).name("x5362").ctrl(x5365).srcCtx("UnrollingBase.scala:28:66") // And(b2966,b2920)
    val x5363 = OpDef(op=BitAnd, inputs=List(x5362, b2911)).name("x5363").ctrl(x5365).srcCtx("UnrollingBase.scala:28:66") // And(x5362,b2911)
    val x5364_x5331_d0 = WriteMem(x5331_d0, x5361).name("x5364_x5331_d0").ctrl(x5365).srcCtx("Kmeans.scala:61:15") // RegWrite(x5331,x5361,x5363)
    antiDepsOf(x5364_x5331_d0)=List(x5359)
    val x5364_x5331_d1 = WriteMem(x5331_d1, x5361).name("x5364_x5331_d1").ctrl(x5365).srcCtx("Kmeans.scala:61:15") // RegWrite(x5331,x5361,x5363)
    antiDepsOf(x5364_x5331_d1)=List(x5359)
    val x5367_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5367_d0_b0").ctrl(x5424).srcCtx("Kmeans.scala:64:36:localCent") // x5367 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5367_d0_b0) = false
    bufferDepthOf(x5367_d0_b0) = 2
    staticDimsOf(x5367_d0_b0) = List(16, 32)
    val x5368_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x5368_d0_b0").ctrl(x5424).srcCtx("Kmeans.scala:64:36:localCent") // x5368 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x5368_d0_b0) = false
    bufferDepthOf(x5368_d0_b0) = 2
    staticDimsOf(x5368_d0_b0) = List(16, 32)
    val x5397 = UnitController(style=ForkJoin, level=OuterControl).name("x5397").ctrl(x5424).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(b2920, b2911),Block(Const(())))
    val x5369 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5369").ctrl(x5397).srcCtx("Kmeans.scala:65:31") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5370 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5370").ctrl(x5397).srcCtx("Kmeans.scala:65:23") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5371 = CounterChain(List(x5370,x5369)).name("x5371").ctrl(x5397).srcCtx("Kmeans.scala:65:38") // CounterChainNew(List(x5370, x5369))
    val x5382 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5371).name("x5382").ctrl(x5397).srcCtx("Kmeans.scala:65:38") // UnrolledForeach(List(b2965, b2920, b2911),x5371,Block(Const(())),List(List(b3122), List(b3123)),List(List(b3124), List(b3125)))
    val b3122 = CounterIter(x5370, Some(0)).name("b3122").ctrl(x5382) // b3122
    val b3124 = Const(true).name("b3124").ctrl(x5382) // b3124
    val b3123 = CounterIter(x5369, None).name("b3123").ctrl(x5382) // b3123
    val b3125 = Const(true).name("b3125").ctrl(x5382) // b3125
    val x5372 = ReadMem(x5330_d0).name("x5372").ctrl(x5382).srcCtx("Kmeans.scala:66:52") // RegRead(x5330)
    val x5373 = OpDef(op=FixEql, inputs=List(b3122, x5372)).name("x5373").ctrl(x5382).srcCtx("Kmeans.scala:66:41") // FixEql(b3122,x5372)
    val x5374 = OpDef(op=BitAnd, inputs=List(b3124, b3125)).name("x5374").ctrl(x5382).srcCtx("UnrollingBase.scala:28:66") // And(b3124,b3125)
    val x5375 = OpDef(op=BitAnd, inputs=List(b2965, b2920)).name("x5375").ctrl(x5382).srcCtx("UnrollingBase.scala:28:66") // And(b2965,b2920)
    val x5376 = OpDef(op=BitAnd, inputs=List(x5374, x5375)).name("x5376").ctrl(x5382).srcCtx("UnrollingBase.scala:28:66") // And(x5374,x5375)
    val x5377 = OpDef(op=BitAnd, inputs=List(x5376, b2911)).name("x5377").ctrl(x5382).srcCtx("UnrollingBase.scala:28:66") // And(x5376,b2911)
    val x5378 = LoadBanks(List(x5201_d0_b0), List(b2963, b3123)).name("x5378").ctrl(x5382).srcCtx("Kmeans.scala:66:62") // ParSRAMLoad(x5201,List(List(b2963, b3123)),List(x5377))
    val x5379 = x5378 // x5379 = VectorApply(x5378,0)
    val x5380 = OpDef(op=MuxOp, inputs=List(x5373, x5379, Const(0))).name("x5380").ctrl(x5382).srcCtx("Kmeans.scala:66:37") // Mux(x5373,x5379,Const(0))
    val x5381 = StoreBanks(List(List(x5367_d0_b0)), List(b3122, b3123), x5380).name("x5381").ctrl(x5382).srcCtx("Kmeans.scala:66:32") // ParSRAMStore(x5367,List(List(b3122, b3123)),List(x5380),List(x5377))
    val x5383 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5383").ctrl(x5397).srcCtx("Kmeans.scala:65:31") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5384 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5384").ctrl(x5397).srcCtx("Kmeans.scala:65:23") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5385 = CounterChain(List(x5384,x5383)).name("x5385").ctrl(x5397).srcCtx("Kmeans.scala:65:38") // CounterChainNew(List(x5384, x5383))
    val x5396 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5385).name("x5396").ctrl(x5397).srcCtx("Kmeans.scala:65:38") // UnrolledForeach(List(b2966, b2920, b2911),x5385,Block(Const(())),List(List(b3137), List(b3138)),List(List(b3139), List(b3140)))
    val b3137 = CounterIter(x5384, Some(0)).name("b3137").ctrl(x5396) // b3137
    val b3139 = Const(true).name("b3139").ctrl(x5396) // b3139
    val b3138 = CounterIter(x5383, None).name("b3138").ctrl(x5396) // b3138
    val b3140 = Const(true).name("b3140").ctrl(x5396) // b3140
    val x5386 = ReadMem(x5331_d0).name("x5386").ctrl(x5396).srcCtx("Kmeans.scala:66:52") // RegRead(x5331)
    val x5387 = OpDef(op=FixEql, inputs=List(b3137, x5386)).name("x5387").ctrl(x5396).srcCtx("Kmeans.scala:66:41") // FixEql(b3137,x5386)
    val x5388 = OpDef(op=BitAnd, inputs=List(b3139, b3140)).name("x5388").ctrl(x5396).srcCtx("UnrollingBase.scala:28:66") // And(b3139,b3140)
    val x5389 = OpDef(op=BitAnd, inputs=List(b2966, b2920)).name("x5389").ctrl(x5396).srcCtx("UnrollingBase.scala:28:66") // And(b2966,b2920)
    val x5390 = OpDef(op=BitAnd, inputs=List(x5388, x5389)).name("x5390").ctrl(x5396).srcCtx("UnrollingBase.scala:28:66") // And(x5388,x5389)
    val x5391 = OpDef(op=BitAnd, inputs=List(x5390, b2911)).name("x5391").ctrl(x5396).srcCtx("UnrollingBase.scala:28:66") // And(x5390,b2911)
    val x5392 = LoadBanks(List(x5201_d0_b1), List(b2964, b3138)).name("x5392").ctrl(x5396).srcCtx("Kmeans.scala:66:62") // ParSRAMLoad(x5201,List(List(b2964, b3138)),List(x5391))
    val x5393 = x5392 // x5393 = VectorApply(x5392,0)
    val x5394 = OpDef(op=MuxOp, inputs=List(x5387, x5393, Const(0))).name("x5394").ctrl(x5396).srcCtx("Kmeans.scala:66:37") // Mux(x5387,x5393,Const(0))
    val x5395 = StoreBanks(List(List(x5368_d0_b0)), List(b3137, b3138), x5394).name("x5395").ctrl(x5396).srcCtx("Kmeans.scala:66:32") // ParSRAMStore(x5368,List(List(b3137, b3138)),List(x5394),List(x5391))
    val x5398 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5398").ctrl(x5424).srcCtx("Kmeans.scala:69:12") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5399 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5399").ctrl(x5424).srcCtx("Kmeans.scala:69:12") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5400 = CounterChain(List(x5399,x5398)).name("x5400").ctrl(x5424).srcCtx("Kmeans.scala:69:12") // CounterChainNew(ArrayBuffer(x5399, x5398))
    val x5423 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5400).name("x5423").ctrl(x5424).srcCtx("Kmeans.scala:69:12") // UnrolledForeach(List(),x5400,Block(Const(())),ArrayBuffer(List(b3153), List(b3154)),ArrayBuffer(List(b3155), List(b3156)))
    val b3153 = CounterIter(x5399, Some(0)).name("b3153").ctrl(x5423) // b3153
    val b3155 = Const(true).name("b3155").ctrl(x5423) // b3155
    val b3154 = CounterIter(x5398, None).name("b3154").ctrl(x5423) // b3154
    val b3156 = Const(true).name("b3156").ctrl(x5423) // b3156
    val x5401 = OpDef(op=BitAnd, inputs=List(b3155, b3156)).name("x5401").ctrl(x5423).srcCtx("UnrollingBase.scala:28:66") // And(b3155,b3156)
    val x5402 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5402").ctrl(x5423).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x5403 = OpDef(op=BitAnd, inputs=List(x5401, x5402)).name("x5403").ctrl(x5423).srcCtx("UnrollingBase.scala:28:66") // And(x5401,x5402)
    val x5404 = LoadBanks(List(x5367_d0_b0), List(b3153, b3154)).name("x5404").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // ParSRAMLoad(x5367,List(ArrayBuffer(b3153, b3154)),List(x5403))
    val x5405 = x5404 // x5405 = VectorApply(x5404,0)
    val x5406 = LoadBanks(List(x5368_d0_b0), List(b3153, b3154)).name("x5406").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // ParSRAMLoad(x5368,List(ArrayBuffer(b3153, b3154)),List(x5403))
    val x5407 = x5406 // x5407 = VectorApply(x5406,0)
    val x5408 = LoadBanks(List(x5233_d1_b0), List(b3153, b3154)).name("x5408").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // ParSRAMLoad(x5233,List(ArrayBuffer(b3153, b3154)),List(x5403))
    val x5409 = x5408 // x5409 = VectorApply(x5408,0)
    val x5410 = OpDef(op=BitAnd, inputs=List(b2965, b2920)).name("x5410").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // And(b2965,b2920)
    val x5411 = OpDef(op=BitAnd, inputs=List(x5410, b2911)).name("x5411").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // And(x5410,b2911)
    val x5412 = OpDef(op=BitAnd, inputs=List(b2966, b2920)).name("x5412").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // And(b2966,b2920)
    val x5413 = OpDef(op=BitAnd, inputs=List(x5412, b2911)).name("x5413").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // And(x5412,b2911)
    val x5414 = OpDef(op=BitAnd, inputs=List(x5411, x5403)).name("x5414").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // And(x5411,x5403)
    val x5415 = OpDef(op=BitAnd, inputs=List(x5413, x5403)).name("x5415").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // And(x5413,x5403)
    val x5416 = OpDef(op=FixAdd, inputs=List(x5405, x5407)).name("x5416").ctrl(x5423).srcCtx("Kmeans.scala:69:14") // FixAdd(x5405,x5407)
    val x5417 = OpDef(op=MuxOp, inputs=List(x5415, x5416, x5405)).name("x5417").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // Mux(x5415,x5416,x5405)
    val x5418 = OpDef(op=BitOr, inputs=List(x5414, x5415)).name("x5418").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // Or(x5414,x5415)
    val x5419 = OpDef(op=FixEql, inputs=List(b2963, Const(0))).name("x5419").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // FixEql(b2963,Const(0))
    val x5420 = OpDef(op=FixAdd, inputs=List(x5417, x5409)).name("x5420").ctrl(x5423).srcCtx("Kmeans.scala:69:14") // FixAdd(x5417,x5409)
    val x5421 = OpDef(op=MuxOp, inputs=List(x5419, x5417, x5420)).name("x5421").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // Mux(x5419,x5417,x5420)
    val x5422 = StoreBanks(List(List(x5233_d0_b0), List(x5233_d1_b0)), List(b3153, b3154), x5421).name("x5422").ctrl(x5423).srcCtx("Kmeans.scala:69:12") // ParSRAMStore(x5233,List(ArrayBuffer(b3153, b3154)),List(x5421),List(x5403))
    antiDepsOf(x5422)=List(x5408)
    val x5425 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5425").ctrl(x5441).srcCtx("Kmeans.scala:70:10") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5426 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5426").ctrl(x5441).srcCtx("Kmeans.scala:70:10") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5427 = CounterChain(List(x5426,x5425)).name("x5427").ctrl(x5441).srcCtx("Kmeans.scala:70:10") // CounterChainNew(ArrayBuffer(x5426, x5425))
    val x5440 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5427).name("x5440").ctrl(x5441).srcCtx("Kmeans.scala:70:10") // UnrolledForeach(List(),x5427,Block(Const(())),ArrayBuffer(List(b3181), List(b3182)),ArrayBuffer(List(b3183), List(b3184)))
    val b3181 = CounterIter(x5426, Some(0)).name("b3181").ctrl(x5440) // b3181
    val b3183 = Const(true).name("b3183").ctrl(x5440) // b3183
    val b3182 = CounterIter(x5425, None).name("b3182").ctrl(x5440) // b3182
    val b3184 = Const(true).name("b3184").ctrl(x5440) // b3184
    val x5428 = OpDef(op=BitAnd, inputs=List(b3183, b3184)).name("x5428").ctrl(x5440).srcCtx("UnrollingBase.scala:28:66") // And(b3183,b3184)
    val x5429 = OpDef(op=BitAnd, inputs=List(x5428, b2911)).name("x5429").ctrl(x5440).srcCtx("UnrollingBase.scala:28:66") // And(x5428,b2911)
    val x5430 = LoadBanks(List(x5233_d0_b0), List(b3181, b3182)).name("x5430").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // ParSRAMLoad(x5233,List(ArrayBuffer(b3181, b3182)),List(x5429))
    val x5431 = x5430 // x5431 = VectorApply(x5430,0)
    val x5432 = LoadBanks(List(x5197_d2_b0), List(b3181, b3182)).name("x5432").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // ParSRAMLoad(x5197,List(ArrayBuffer(b3181, b3182)),List(x5429))
    val x5433 = x5432 // x5433 = VectorApply(x5432,0)
    val x5434 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x5434").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // And(b2920,b2911)
    val x5435 = OpDef(op=BitAnd, inputs=List(x5434, x5429)).name("x5435").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // And(x5434,x5429)
    val x5436 = OpDef(op=FixEql, inputs=List(b2919, Const(0))).name("x5436").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // FixEql(b2919,Const(0))
    val x5437 = OpDef(op=FixAdd, inputs=List(x5431, x5433)).name("x5437").ctrl(x5440).srcCtx("Kmeans.scala:70:12") // FixAdd(x5431,x5433)
    val x5438 = OpDef(op=MuxOp, inputs=List(x5436, x5431, x5437)).name("x5438").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // Mux(x5436,x5431,x5437)
    val x5439 = StoreBanks(List(List(x5197_d0_b0), List(x5197_d1_b0), List(x5197_d2_b0)), List(b3181, b3182), x5438).name("x5439").ctrl(x5440).srcCtx("Kmeans.scala:70:10") // ParSRAMStore(x5197,List(ArrayBuffer(b3181, b3182)),List(x5438),List(x5429))
    antiDepsOf(x5439)=List(x5432)
    val x5442 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5442").ctrl(x5463).srcCtx("Kmeans.scala:73:24") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5443 = CounterChain(List(x5442)).name("x5443").ctrl(x5463).srcCtx("Kmeans.scala:73:32") // CounterChainNew(List(x5442))
    val x5462 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5443).name("x5462").ctrl(x5463).srcCtx("Kmeans.scala:73:32") // UnrolledForeach(List(b2911),x5443,Block(Const(())),List(List(b3201)),List(List(b3202)))
    val b3201 = CounterIter(x5442, Some(0)).name("b3201").ctrl(x5462) // b3201
    val b3202 = Const(true).name("b3202").ctrl(x5462) // b3202
    val x5444 = Reg(init=Some(0)).name("x5444").ctrl(x5462).srcCtx("Kmeans.scala:74:33:centCount") // x5444 = RegNew(Const(0))
    isAccum(x5444) = false
    bufferDepthOf(x5444) = 2
    val x5449 = UnitController(style=SeqPipe, level=InnerControl).name("x5449").ctrl(x5462).srcCtx("Kmeans.scala:75:16") // UnitPipe(List(b3202, b2911),Block(x5448))
    val x5445 = OpDef(op=BitAnd, inputs=List(b3202, b2911)).name("x5445").ctrl(x5449).srcCtx("UnrollingBase.scala:28:66") // And(b3202,b2911)
    val x5446 = LoadBanks(List(x5197_d1_b0), List(b3201, Const(31))).name("x5446").ctrl(x5449).srcCtx("Kmeans.scala:76:38") // SRAMLoad(x5197,ArrayBuffer(Const(16), Const(32)),List(b3201, Const(31)),Const(0),x5445)
    val x5447 = OpDef(op=FixMax, inputs=List(x5446, Const(1))).name("x5447").ctrl(x5449).srcCtx("Kmeans.scala:76:29") // Max(x5446,Const(1))
    val x5448_x5444 = WriteMem(x5444, x5447).name("x5448_x5444").ctrl(x5449).srcCtx("Kmeans.scala:76:23") // RegWrite(x5444,x5447,x5445)
    val x5450 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5450").ctrl(x5462).srcCtx("Kmeans.scala:78:21") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5451 = CounterChain(List(x5450)).name("x5451").ctrl(x5462).srcCtx("Kmeans.scala:78:28") // CounterChainNew(List(x5450))
    val x5461 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5451).name("x5461").ctrl(x5462).srcCtx("Kmeans.scala:78:28") // UnrolledForeach(List(b3202, b2911),x5451,Block(Const(())),List(List(b3211)),List(List(b3212)))
    val b3211 = CounterIter(x5450, None).name("b3211").ctrl(x5461) // b3211
    val b3212 = Const(true).name("b3212").ctrl(x5461) // b3212
    val x5452 = ReadMem(x5444).name("x5452").ctrl(x5461).srcCtx("Kmeans.scala:79:40") // RegRead(x5444)
    val x5453 = OpDef(op=FixEql, inputs=List(x5452, Const(0))).name("x5453").ctrl(x5461).srcCtx("Kmeans.scala:79:40") // FixEql(x5452,Const(0))
    val x5454 = OpDef(op=BitAnd, inputs=List(b3212, b3202)).name("x5454").ctrl(x5461).srcCtx("UnrollingBase.scala:28:66") // And(b3212,b3202)
    val x5455 = OpDef(op=BitAnd, inputs=List(x5454, b2911)).name("x5455").ctrl(x5461).srcCtx("UnrollingBase.scala:28:66") // And(x5454,b2911)
    val x5456 = LoadBanks(List(x5197_d0_b0), List(b3201, b3211)).name("x5456").ctrl(x5461).srcCtx("Kmeans.scala:79:69") // ParSRAMLoad(x5197,List(List(b3201, b3211)),List(x5455))
    val x5457 = x5456 // x5457 = VectorApply(x5456,0)
    val x5458 = OpDef(op=FixDiv, inputs=List(x5457, x5452)).name("x5458").ctrl(x5461).srcCtx("Kmeans.scala:79:76") // FixDiv(x5457,x5452)
    val x5459 = OpDef(op=MuxOp, inputs=List(x5453, Const(0), x5458)).name("x5459").ctrl(x5461).srcCtx("Kmeans.scala:79:29") // Mux(x5453,Const(0),x5458)
    val x5460 = StoreBanks(List(List(x5169_d0_b0), List(x5169_d1_b0), List(x5169_d2_b0)), List(b3201, b3211), x5459).name("x5460").ctrl(x5461).srcCtx("Kmeans.scala:79:24") // ParSRAMStore(x5169,List(List(b3201, b3211)),List(x5459),List(x5455))
    val x5464 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x5464").ctrl(x5492).srcCtx("Kmeans.scala:85:33") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x5465 = CounterChain(List(x5464)).name("x5465").ctrl(x5492).srcCtx("Kmeans.scala:85:33") // CounterChainNew(List(x5464))
    val x5491 = LoopController(style=StreamPipe, level=OuterControl, cchain=x5465).name("x5491").ctrl(x5492).srcCtx("Kmeans.scala:85:33") // UnrolledForeach(List(Const(true)),x5465,Block(Const(())),List(List(b3227)),List(List(b3228)))
    val b3227 = CounterIter(x5464, Some(0)).name("b3227").ctrl(x5491) // b3227
    val b3228 = Const(true).name("b3228").ctrl(x5491) // b3228
    val b5604 = StreamOut(field="offset").name("b5604").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // x5466 = StreamOutNew(BurstCmdBus)
    isAccum(b5604) = false
    bufferDepthOf(b5604) = 1
    val b5605 = StreamOut(field="size").name("b5605").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // x5466 = StreamOutNew(BurstCmdBus)
    isAccum(b5605) = false
    def split1 = {
    bufferDepthOf(b5605) = 1
    val x5467 = StreamOut(field="data").name("x5467").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // x5467 = StreamOutNew(BurstFullDataBus())
    isAccum(x5467) = false
    bufferDepthOf(x5467) = 1
    val x5468 = StreamIn(field="ack").name("x5468").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // x5468 = StreamInNew(BurstAckBus)
    isAccum(x5468) = false
    bufferDepthOf(x5468) = 1
    val x5479 = UnitController(style=SeqPipe, level=InnerControl).name("x5479").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // UnitPipe(List(b3228),Block(x5478))
    val x5469 = b3227 // FixConvert(b3227,TRUE,_32,_0) (Same Type. No op)
    val x5470 = OpDef(op=FixSla, inputs=List(x5469, Const(5))).name("x5470").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // FixLsh(x5469,Const(5))
    val x5471 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x5472 = OpDef(op=FixAdd, inputs=List(x5470, x5471)).name("x5472").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // FixAdd(x5470,x5471)
    val x5473 = OpDef(op=FixSla, inputs=List(x5472, Const(2))).name("x5473").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // FixLsh(x5472,Const(2))
    val x5474 = x5473 // FixConvert(x5473,TRUE,_64,_0)
    val x5475 = DramAddress(x5165).name("x5475").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // GetDRAMAddress(x5165)
    val x5477_x5476 = OpDef(op=FixAdd, inputs=List(x5474, x5475)).name("x5477_x5476").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // FixAdd(x5474,x5475)
    // x5477 = SimpleStruct(ArrayBuffer((offset,x5476), (size,Const(128)), (isLoad,Const(false))))
    val x5478_b5606_b5604 = WriteMem(b5604, x5477_x5476).name("x5478_b5606_b5604").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // StreamWrite(x5466,x5477,b3228)
    val x5478_b5607_b5605 = WriteMem(b5605, Const(128)).name("x5478_b5607_b5605").ctrl(x5479).srcCtx("Kmeans.scala:85:33") // StreamWrite(x5466,x5477,b3228)
    val x5480 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x5480").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x5481 = CounterChain(List(x5480)).name("x5481").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // CounterChainNew(List(x5480))
    val x5487 = LoopController(style=InnerPipe, level=InnerControl, cchain=x5481).name("x5487").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // UnrolledForeach(List(b3228),x5481,Block(Const(())),List(List(b3245)),List(List(b3246)))
    val b3245 = CounterIter(x5480, None).name("b3245").ctrl(x5487) // b3245
    val b3246 = Const(true).name("b3246").ctrl(x5487) // b3246
    val x5482 = OpDef(op=BitAnd, inputs=List(b3246, b3228)).name("x5482").ctrl(x5487).srcCtx("UnrollingBase.scala:28:66") // And(b3246,b3228)
    val x5483 = LoadBanks(List(x5169_d0_b0), List(b3227, b3245)).name("x5483").ctrl(x5487).srcCtx("Kmeans.scala:85:33") // ParSRAMLoad(x5169,List(List(b3227, b3245)),List(x5482))
    val x5485_x5484 = x5483 // x5484 = VectorApply(x5483,0)
    // x5485 = SimpleStruct(ArrayBuffer((_1,x5484), (_2,Const(true))))
    val x5486_x5486_x5467 = WriteMem(x5467, x5485_x5484).name("x5486_x5486_x5467").ctrl(x5487).srcCtx("Kmeans.scala:85:33") // ParStreamWrite(x5467,List(x5485),List(x5482))
    val x5488 = FringeDenseStore(dram=List(x5165), cmdStream=List(b5604, b5605), dataStream=List(x5467), ackStream=List(x5468)).name("x5488").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // FringeDenseStore(x5165,x5466,x5467,x5468)
    val x5490 = UnitController(style=SeqPipe, level=InnerControl).name("x5490").ctrl(x5491).srcCtx("Kmeans.scala:85:33") // UnitPipe(List(b3228),Block(Const(())))
    val x5489_x5489 = ReadMem(x5468).name("x5489_x5489").ctrl(x5490).srcCtx("Kmeans.scala:85:33") // StreamRead(x5468,b3228)
    }; split1
    
  }
}
