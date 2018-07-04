import pir._
import pir.node._
import arch._
import prism.enums._

object GEMM_Blocked extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3167_d0 = ArgIn(init=0).name("x3167_d0").ctrl(top).srcCtx("GEMM_Blocked.scala:213:20") // ArgInNew(Const(0))
    isAccum(x3167_d0) = false
    bufferDepthOf(x3167_d0) = 1
    boundOf(x3167_d0) = 1024
    val x3169 = ReadMem(x3167_d0).name("x3169").ctrl(top).srcCtx("GEMM_Blocked.scala:215:30") // RegRead(x3167)
    val x3170 = ReadMem(x3167_d0).name("x3170").ctrl(top).srcCtx("GEMM_Blocked.scala:215:26") // RegRead(x3167)
    val x3171 = DRAM(dims=List(x3170, x3169)).name("x3171").ctrl(top).srcCtx("GEMM_Blocked.scala:215:25") // x3171 = DRAMNew(ArrayBuffer(x3170, x3169),Const(0))
    val x3172 = ReadMem(x3167_d0).name("x3172").ctrl(top).srcCtx("GEMM_Blocked.scala:216:30") // RegRead(x3167)
    val x3173 = ReadMem(x3167_d0).name("x3173").ctrl(top).srcCtx("GEMM_Blocked.scala:216:26") // RegRead(x3167)
    val x3174 = DRAM(dims=List(x3173, x3172)).name("x3174").ctrl(top).srcCtx("GEMM_Blocked.scala:216:25") // x3174 = DRAMNew(ArrayBuffer(x3173, x3172),Const(0))
    val x3175 = ReadMem(x3167_d0).name("x3175").ctrl(top).srcCtx("GEMM_Blocked.scala:217:30") // RegRead(x3167)
    val x3176 = ReadMem(x3167_d0).name("x3176").ctrl(top).srcCtx("GEMM_Blocked.scala:217:26") // RegRead(x3167)
    val x3177 = DRAM(dims=List(x3176, x3175)).name("x3177").ctrl(top).srcCtx("GEMM_Blocked.scala:217:25") // x3177 = DRAMNew(ArrayBuffer(x3176, x3175),Const(0))
    val x3391 = UnitController(style=SeqPipe, level=OuterControl).name("x3391").ctrl(top).srcCtx("GEMM_Blocked.scala:223:10") // Hwblock(Block(Const(())),false)
    val x3181 = ReadMem(x3167_d0).name("x3181").ctrl(x3391).srcCtx("GEMM_Blocked.scala:224:15") // RegRead(x3167)
    val x3182 = Counter(min=Const(0), max=x3181, step=Const(64), par=1).name("x3182").ctrl(x3391).srcCtx("GEMM_Blocked.scala:224:26") // CounterNew(Const(0),x3181,Const(64),Const(1))
    val x3183 = CounterChain(List(x3182)).name("x3183").ctrl(x3391).srcCtx("GEMM_Blocked.scala:224:39") // CounterChainNew(List(x3182))
    val x3390 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3183).name("x3390").ctrl(x3391).srcCtx("GEMM_Blocked.scala:224:39") // UnrolledForeach(List(Const(true)),x3183,Block(Const(())),List(List(b1803)),List(List(b1804)))
    val b1803 = CounterIter(x3182, Some(0)).name("b1803").ctrl(x3390) // b1803
    val b1804 = Const(true).name("b1804").ctrl(x3390) // b1804
    val x3184 = ReadMem(x3167_d0).name("x3184").ctrl(x3390).srcCtx("GEMM_Blocked.scala:225:17") // RegRead(x3167)
    val x3185 = Counter(min=Const(0), max=x3184, step=Const(32), par=1).name("x3185").ctrl(x3390).srcCtx("GEMM_Blocked.scala:225:27") // CounterNew(Const(0),x3184,Const(32),Const(1))
    val x3186 = CounterChain(List(x3185)).name("x3186").ctrl(x3390).srcCtx("GEMM_Blocked.scala:225:40") // CounterChainNew(List(x3185))
    val x3389 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3186).name("x3389").ctrl(x3390).srcCtx("GEMM_Blocked.scala:225:40") // UnrolledForeach(List(b1804),x3186,Block(Const(())),List(List(b1808)),List(List(b1809)))
    val b1808 = CounterIter(x3185, Some(0)).name("b1808").ctrl(x3389) // b1808
    val b1809 = Const(true).name("b1809").ctrl(x3389) // b1809
    val x3187_d0_b0 = SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3187_d0_b0").ctrl(x3389).srcCtx("GEMM_Blocked.scala:226:30") // x3187 = SRAMNew(ArrayBuffer(Const(64), Const(32)))
    isAccum(x3187_d0_b0) = false
    bufferDepthOf(x3187_d0_b0) = 3
    val x3187_d1_b0 = SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3187_d1_b0").ctrl(x3389).srcCtx("GEMM_Blocked.scala:226:30") // x3187 = SRAMNew(ArrayBuffer(Const(64), Const(32)))
    isAccum(x3187_d1_b0) = true
    bufferDepthOf(x3187_d1_b0) = 1
    val x3188 = ReadMem(x3167_d0).name("x3188").ctrl(x3389).srcCtx("GEMM_Blocked.scala:227:43") // RegRead(x3167)
    val x3189 = Counter(min=Const(0), max=x3188, step=Const(32), par=1).name("x3189").ctrl(x3389).srcCtx("GEMM_Blocked.scala:227:53") // CounterNew(Const(0),x3188,Const(32),Const(1))
    val x3190 = CounterChain(List(x3189)).name("x3190").ctrl(x3389).srcCtx("GEMM_Blocked.scala:246:12") // CounterChainNew(List(x3189))
    val x3349 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3190).name("x3349").ctrl(x3389).srcCtx("GEMM_Blocked.scala:246:12") // UnrolledReduce(List(b1809, b1804),x3190,x3187,Block((x3187) => Const(())),List(List(b1817)),List(List(b1818)))
    val b1817 = CounterIter(x3189, Some(0)).name("b1817").ctrl(x3349) // b1817
    val b1818 = Const(true).name("b1818").ctrl(x3349) // b1818
    val x3191_d0_b0 = SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3191_d0_b0").ctrl(x3349).srcCtx("GEMM_Blocked.scala:228:40") // x3191 = SRAMNew(ArrayBuffer(Const(64), Const(32)))
    isAccum(x3191_d0_b0) = false
    bufferDepthOf(x3191_d0_b0) = 2
    val x3192_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3192_d0_b0").ctrl(x3349).srcCtx("GEMM_Blocked.scala:229:33") // x3192 = SRAMNew(ArrayBuffer(Const(32), Const(32)))
    isAccum(x3192_d0_b0) = false
    bufferDepthOf(x3192_d0_b0) = 2
    val x3195 = UnitController(style=SeqPipe, level=InnerControl).name("x3195").ctrl(x3349).srcCtx("GEMM_Blocked.scala:246:12") // UnitPipe(List(b1818, b1809, b1804),Block(Const(())))
    val x3193 = OpDef(op=FixAdd, inputs=List(b1817, Const(32))).name("x3193").ctrl(x3195).srcCtx("GEMM_Blocked.scala:230:38") // FixAdd(b1817,Const(32))
    val x3194 = OpDef(op=FixAdd, inputs=List(b1808, Const(32))).name("x3194").ctrl(x3195).srcCtx("GEMM_Blocked.scala:230:49") // FixAdd(b1808,Const(32))
    val x3196 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3196").ctrl(x3349).srcCtx("GEMM_Blocked.scala:230:20") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3197 = CounterChain(List(x3196)).name("x3197").ctrl(x3349).srcCtx("GEMM_Blocked.scala:230:20") // CounterChainNew(List(x3196))
    val x3227 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3197).name("x3227").ctrl(x3349).srcCtx("GEMM_Blocked.scala:230:20") // UnrolledForeach(List(b1818, b1809, b1804),x3197,Block(Const(())),List(List(b1826)),List(List(b1827)))
    val b1826 = CounterIter(x3196, Some(0)).name("b1826").ctrl(x3227) // b1826
    val b1827 = Const(true).name("b1827").ctrl(x3227) // b1827
    val b3432 = StreamOut(field="offset").name("b3432").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // x3198 = StreamOutNew(BurstCmdBus)
    isAccum(b3432) = false
    bufferDepthOf(b3432) = 1
    val b3433 = StreamOut(field="size").name("b3433").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // x3198 = StreamOutNew(BurstCmdBus)
    isAccum(b3433) = false
    bufferDepthOf(b3433) = 1
    val x3199 = StreamIn(field="data").name("x3199").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // x3199 = StreamInNew(BurstDataBus())
    isAccum(x3199) = false
    bufferDepthOf(x3199) = 1
    val x3215 = UnitController(style=SeqPipe, level=InnerControl).name("x3215").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // UnitPipe(List(b1827, b1818, b1809, b1804),Block(x3214))
    val x3200 = OpDef(op=FixAdd, inputs=List(b1817, b1826)).name("x3200").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // FixAdd(b1817,b1826)
    val x3201 = x3200 // FixConvert(x3200,TRUE,_32,_0) (Same Type. No op)
    val x3202 = ReadMem(x3167_d0).name("x3202").ctrl(x3215).srcCtx("GEMM_Blocked.scala:216:30") // RegRead(x3167)
    val x3203 = OpDef(op=FixMul, inputs=List(x3201, x3202)).name("x3203").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // FixMul(x3201,x3202)
    val x3204 = b1808 // FixConvert(b1808,TRUE,_32,_0) (Same Type. No op)
    val x3205 = OpDef(op=FixAdd, inputs=List(x3203, x3204)).name("x3205").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // FixAdd(x3203,x3204)
    val x3206 = OpDef(op=FixSla, inputs=List(x3205, Const(2))).name("x3206").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // FixLsh(x3205,Const(2))
    val x3207 = x3206 // FixConvert(x3206,TRUE,_64,_0)
    val x3208 = DramAddress(x3174).name("x3208").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // GetDRAMAddress(x3174)
    val x3210_x3209 = OpDef(op=FixAdd, inputs=List(x3207, x3208)).name("x3210_x3209").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // FixAdd(x3207,x3208)
    // x3210 = SimpleStruct(ArrayBuffer((offset,x3209), (size,Const(128)), (isLoad,Const(true))))
    val x3211 = OpDef(op=BitAnd, inputs=List(b1827, b1818)).name("x3211").ctrl(x3215).srcCtx("UnrollingBase.scala:28:66") // And(b1827,b1818)
    val x3212 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3212").ctrl(x3215).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3213 = OpDef(op=BitAnd, inputs=List(x3211, x3212)).name("x3213").ctrl(x3215).srcCtx("UnrollingBase.scala:28:66") // And(x3211,x3212)
    val x3214_b3434_b3432 = WriteMem(b3432, x3210_x3209).name("x3214_b3434_b3432").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // StreamWrite(x3198,x3210,x3213)
    val x3214_b3435_b3433 = WriteMem(b3433, Const(128)).name("x3214_b3435_b3433").ctrl(x3215).srcCtx("GEMM_Blocked.scala:230:20") // StreamWrite(x3198,x3210,x3213)
    val x3216 = FringeDenseLoad(dram=List(x3174), cmdStream=List(b3432, b3433), dataStream=List(x3199)).name("x3216").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // FringeDenseLoad(x3174,x3198,x3199)
    val x3217 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3217").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3218 = CounterChain(List(x3217)).name("x3218").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // CounterChainNew(List(x3217))
    val x3226 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3218).name("x3226").ctrl(x3227).srcCtx("GEMM_Blocked.scala:230:20") // UnrolledForeach(List(b1827, b1818, b1809, b1804),x3218,Block(Const(())),List(List(b1849)),List(List(b1850)))
    val b1849 = CounterIter(x3217, None).name("b1849").ctrl(x3226) // b1849
    val b1850 = Const(true).name("b1850").ctrl(x3226) // b1850
    val x3219 = OpDef(op=BitAnd, inputs=List(b1850, b1827)).name("x3219").ctrl(x3226).srcCtx("UnrollingBase.scala:28:66") // And(b1850,b1827)
    val x3220 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3220").ctrl(x3226).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3221 = OpDef(op=BitAnd, inputs=List(x3219, x3220)).name("x3221").ctrl(x3226).srcCtx("UnrollingBase.scala:28:66") // And(x3219,x3220)
    val x3222 = OpDef(op=BitAnd, inputs=List(x3221, b1804)).name("x3222").ctrl(x3226).srcCtx("UnrollingBase.scala:28:66") // And(x3221,b1804)
    val x3223_x3223 = ReadMem(x3199).name("x3223_x3223").ctrl(x3226).srcCtx("GEMM_Blocked.scala:230:20") // ParStreamRead(x3199,List(x3222))
    val x3224_x3224 = x3223_x3223 // x3224 = VectorApply(x3223,0)
    val x3225 = StoreBanks(List(x3192_d0_b0), List(b1826, b1849), x3224_x3224).name("x3225").ctrl(x3226).srcCtx("GEMM_Blocked.scala:230:20") // ParSRAMStore(x3192,List(List(b1826, b1849)),List(x3224),List(x3222))
    val x3228 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3228").ctrl(x3349).srcCtx("GEMM_Blocked.scala:231:30") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3229 = CounterChain(List(x3228)).name("x3229").ctrl(x3349).srcCtx("GEMM_Blocked.scala:231:42") // CounterChainNew(List(x3228))
    val x3330 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3229).name("x3330").ctrl(x3349).srcCtx("GEMM_Blocked.scala:231:42") // UnrolledForeach(List(b1818, b1809, b1804),x3229,Block(Const(())),List(List(b1862)),List(List(b1863)))
    val b1862 = CounterIter(x3228, Some(0)).name("b1862").ctrl(x3330) // b1862
    val b1863 = Const(true).name("b1863").ctrl(x3330) // b1863
    val x3230_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x3230_d0_b0").ctrl(x3330).srcCtx("GEMM_Blocked.scala:232:35") // x3230 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3230_d0_b0) = false
    bufferDepthOf(x3230_d0_b0) = 2
    val x3231 = Reg(init=Some(0)).name("x3231").ctrl(x3330).srcCtx("GEMM_Blocked.scala:231:42") // x3231 = RegNew(Const(0))
    isAccum(x3231) = false
    bufferDepthOf(x3231) = 2
    val x3239 = UnitController(style=SeqPipe, level=InnerControl).name("x3239").ctrl(x3330).srcCtx("GEMM_Blocked.scala:231:42") // UnitPipe(List(b1863, b1818, b1809, b1804),Block(Const(())))
    val x3232 = OpDef(op=FixAdd, inputs=List(b1803, b1862)).name("x3232").ctrl(x3239).srcCtx("GEMM_Blocked.scala:233:36") // FixAdd(b1803,b1862)
    val x3233 = OpDef(op=FixAdd, inputs=List(b1817, Const(32))).name("x3233").ctrl(x3239).srcCtx("GEMM_Blocked.scala:233:46") // FixAdd(b1817,Const(32))
    val x3234 = OpDef(op=FixAdd, inputs=List(x3232, Const(1))).name("x3234").ctrl(x3239).srcCtx("GEMM_Blocked.scala:233:33") // FixAdd(x3232,Const(1))
    val x3235 = OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3235").ctrl(x3239).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1818)
    val x3236 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3236").ctrl(x3239).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3237 = OpDef(op=BitAnd, inputs=List(x3235, x3236)).name("x3237").ctrl(x3239).srcCtx("UnrollingBase.scala:28:66") // And(x3235,x3236)
    val x3238_x3231 = WriteMem(x3231, x3232).name("x3238_x3231").ctrl(x3239).srcCtx("GEMM_Blocked.scala:231:42") // RegWrite(x3231,x3232,x3237)
    val x3240 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3240").ctrl(x3330).srcCtx("GEMM_Blocked.scala:233:22") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3241 = CounterChain(List(x3240)).name("x3241").ctrl(x3330).srcCtx("GEMM_Blocked.scala:233:22") // CounterChainNew(List(x3240))
    val x3274 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3241).name("x3274").ctrl(x3330).srcCtx("GEMM_Blocked.scala:233:22") // UnrolledForeach(List(b1863, b1818, b1809, b1804),x3241,Block(Const(())),List(List(b1876)),List(List(b1877)))
    val b1876 = CounterIter(x3240, Some(0)).name("b1876").ctrl(x3274) // b1876
    val b1877 = Const(true).name("b1877").ctrl(x3274) // b1877
    val b3436 = StreamOut(field="offset").name("b3436").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // x3242 = StreamOutNew(BurstCmdBus)
    isAccum(b3436) = false
    bufferDepthOf(b3436) = 1
    val b3437 = StreamOut(field="size").name("b3437").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // x3242 = StreamOutNew(BurstCmdBus)
    isAccum(b3437) = false
    bufferDepthOf(b3437) = 1
    val x3243 = StreamIn(field="data").name("x3243").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // x3243 = StreamInNew(BurstDataBus())
    isAccum(x3243) = false
    bufferDepthOf(x3243) = 1
    val x3261 = UnitController(style=SeqPipe, level=InnerControl).name("x3261").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // UnitPipe(List(b1877, b1863, b1818, b1809, b1804),Block(x3260))
    val x3244 = ReadMem(x3231).name("x3244").ctrl(x3261).srcCtx("GEMM_Blocked.scala:231:42") // RegRead(x3231)
    val x3245 = OpDef(op=FixAdd, inputs=List(x3244, b1876)).name("x3245").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // FixAdd(x3244,b1876)
    val x3246 = x3245 // FixConvert(x3245,TRUE,_32,_0) (Same Type. No op)
    val x3247 = ReadMem(x3167_d0).name("x3247").ctrl(x3261).srcCtx("GEMM_Blocked.scala:215:30") // RegRead(x3167)
    val x3248 = OpDef(op=FixMul, inputs=List(x3246, x3247)).name("x3248").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // FixMul(x3246,x3247)
    val x3249 = b1817 // FixConvert(b1817,TRUE,_32,_0) (Same Type. No op)
    val x3250 = OpDef(op=FixAdd, inputs=List(x3248, x3249)).name("x3250").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // FixAdd(x3248,x3249)
    val x3251 = OpDef(op=FixSla, inputs=List(x3250, Const(2))).name("x3251").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // FixLsh(x3250,Const(2))
    val x3252 = x3251 // FixConvert(x3251,TRUE,_64,_0)
    val x3253 = DramAddress(x3171).name("x3253").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // GetDRAMAddress(x3171)
    val x3255_x3254 = OpDef(op=FixAdd, inputs=List(x3252, x3253)).name("x3255_x3254").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // FixAdd(x3252,x3253)
    // x3255 = SimpleStruct(ArrayBuffer((offset,x3254), (size,Const(128)), (isLoad,Const(true))))
    val x3256 = OpDef(op=BitAnd, inputs=List(b1877, b1863)).name("x3256").ctrl(x3261).srcCtx("UnrollingBase.scala:28:66") // And(b1877,b1863)
    val x3257 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3257").ctrl(x3261).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3258 = OpDef(op=BitAnd, inputs=List(x3256, x3257)).name("x3258").ctrl(x3261).srcCtx("UnrollingBase.scala:28:66") // And(x3256,x3257)
    val x3259 = OpDef(op=BitAnd, inputs=List(x3258, b1804)).name("x3259").ctrl(x3261).srcCtx("UnrollingBase.scala:28:66") // And(x3258,b1804)
    val x3260_b3438_b3436 = WriteMem(b3436, x3255_x3254).name("x3260_b3438_b3436").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // StreamWrite(x3242,x3255,x3259)
    val x3260_b3439_b3437 = WriteMem(b3437, Const(128)).name("x3260_b3439_b3437").ctrl(x3261).srcCtx("GEMM_Blocked.scala:233:22") // StreamWrite(x3242,x3255,x3259)
    val x3262 = FringeDenseLoad(dram=List(x3171), cmdStream=List(b3436, b3437), dataStream=List(x3243)).name("x3262").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // FringeDenseLoad(x3171,x3242,x3243)
    val x3263 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3263").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3264 = CounterChain(List(x3263)).name("x3264").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // CounterChainNew(List(x3263))
    val x3273 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3264).name("x3273").ctrl(x3274).srcCtx("GEMM_Blocked.scala:233:22") // UnrolledForeach(List(b1877, b1863, b1818, b1809, b1804),x3264,Block(Const(())),List(List(b1901)),List(List(b1902)))
    val b1901 = CounterIter(x3263, None).name("b1901").ctrl(x3273) // b1901
    val b1902 = Const(true).name("b1902").ctrl(x3273) // b1902
    val x3265 = OpDef(op=BitAnd, inputs=List(b1902, b1877)).name("x3265").ctrl(x3273).srcCtx("UnrollingBase.scala:28:66") // And(b1902,b1877)
    val x3266 = OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3266").ctrl(x3273).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1818)
    val x3267 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3267").ctrl(x3273).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3268 = OpDef(op=BitAnd, inputs=List(x3265, x3266)).name("x3268").ctrl(x3273).srcCtx("UnrollingBase.scala:28:66") // And(x3265,x3266)
    val x3269 = OpDef(op=BitAnd, inputs=List(x3268, x3267)).name("x3269").ctrl(x3273).srcCtx("UnrollingBase.scala:28:66") // And(x3268,x3267)
    val x3270_x3270 = ReadMem(x3243).name("x3270_x3270").ctrl(x3273).srcCtx("GEMM_Blocked.scala:233:22") // ParStreamRead(x3243,List(x3269))
    val x3271_x3271 = x3270_x3270 // x3271 = VectorApply(x3270,0)
    val x3272 = StoreBanks(List(x3230_d0_b0), List(b1901), x3271_x3271).name("x3272").ctrl(x3273).srcCtx("GEMM_Blocked.scala:233:22") // ParSRAMStore(x3230,List(List(b1901)),List(x3271),List(x3269))
    val x3275_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3275_d0_b0").ctrl(x3330).srcCtx("GEMM_Blocked.scala:234:34") // x3275 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3275_d0_b0) = false
    bufferDepthOf(x3275_d0_b0) = 2
    val x3275_d1_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3275_d1_b0").ctrl(x3330).srcCtx("GEMM_Blocked.scala:234:34") // x3275 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3275_d1_b0) = true
    bufferDepthOf(x3275_d1_b0) = 1
    val x3276 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3276").ctrl(x3330).srcCtx("GEMM_Blocked.scala:235:55") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3277 = CounterChain(List(x3276)).name("x3277").ctrl(x3330).srcCtx("GEMM_Blocked.scala:242:16") // CounterChainNew(List(x3276))
    val x3319 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3277).name("x3319").ctrl(x3330).srcCtx("GEMM_Blocked.scala:242:16") // UnrolledReduce(List(b1863, b1818, b1809, b1804),x3277,x3275,Block((x3275) => Const(())),List(List(b1918)),List(List(b1919)))
    val b1918 = CounterIter(x3276, Some(0)).name("b1918").ctrl(x3319) // b1918
    val b1919 = Const(true).name("b1919").ctrl(x3319) // b1919
    val x3278_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3278_d0_b0").ctrl(x3319).srcCtx("GEMM_Blocked.scala:236:44") // x3278 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3278_d0_b0) = false
    bufferDepthOf(x3278_d0_b0) = 2
    val x3279 = Reg(init=Some(0.0)).name("x3279").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // x3279 = RegNew(Const(0))
    isAccum(x3279) = false
    bufferDepthOf(x3279) = 2
    val x3286 = UnitController(style=SeqPipe, level=InnerControl).name("x3286").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // UnitPipe(List(b1919, b1863, b1818, b1809, b1804),Block(Const(())))
    val x3280 = OpDef(op=BitAnd, inputs=List(b1919, b1863)).name("x3280").ctrl(x3286).srcCtx("UnrollingBase.scala:28:66") // And(b1919,b1863)
    val x3281 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3281").ctrl(x3286).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3282 = OpDef(op=BitAnd, inputs=List(x3280, x3281)).name("x3282").ctrl(x3286).srcCtx("UnrollingBase.scala:28:66") // And(x3280,x3281)
    val x3283 = OpDef(op=BitAnd, inputs=List(x3282, b1804)).name("x3283").ctrl(x3286).srcCtx("UnrollingBase.scala:28:66") // And(x3282,b1804)
    val x3284 = LoadBanks(List(x3230_d0_b0), List(b1918)).name("x3284").ctrl(x3286).srcCtx("GEMM_Blocked.scala:237:36") // SRAMLoad(x3230,ArrayBuffer(Const(32)),List(b1918),Const(0),x3283)
    val x3285_x3279 = WriteMem(x3279, x3284).name("x3285_x3279").ctrl(x3286).srcCtx("GEMM_Blocked.scala:242:16") // RegWrite(x3279,x3284,x3283)
    val x3287 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3287").ctrl(x3319).srcCtx("GEMM_Blocked.scala:238:33") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3288 = CounterChain(List(x3287)).name("x3288").ctrl(x3319).srcCtx("GEMM_Blocked.scala:238:45") // CounterChainNew(List(x3287))
    val x3299 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3288).name("x3299").ctrl(x3319).srcCtx("GEMM_Blocked.scala:238:45") // UnrolledForeach(List(b1919, b1863, b1818, b1809, b1804),x3288,Block(Const(())),List(List(b1931)),List(List(b1932)))
    val b1931 = CounterIter(x3287, None).name("b1931").ctrl(x3299) // b1931
    val b1932 = Const(true).name("b1932").ctrl(x3299) // b1932
    val x3289 = OpDef(op=BitAnd, inputs=List(b1932, b1919)).name("x3289").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(b1932,b1919)
    val x3290 = OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3290").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1818)
    val x3291 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3291").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3292 = OpDef(op=BitAnd, inputs=List(x3289, x3290)).name("x3292").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(x3289,x3290)
    val x3293 = OpDef(op=BitAnd, inputs=List(x3292, x3291)).name("x3293").ctrl(x3299).srcCtx("UnrollingBase.scala:28:66") // And(x3292,x3291)
    val x3294 = LoadBanks(List(x3192_d0_b0), List(b1918, b1931)).name("x3294").ctrl(x3299).srcCtx("GEMM_Blocked.scala:239:44") // ParSRAMLoad(x3192,List(List(b1918, b1931)),List(x3293))
    val x3295 = x3294 // x3295 = VectorApply(x3294,0)
    val x3296 = ReadMem(x3279).name("x3296").ctrl(x3299).srcCtx("GEMM_Blocked.scala:242:16") // RegRead(x3279)
    val x3297 = OpDef(op=FixMul, inputs=List(x3295, x3296)).name("x3297").ctrl(x3299).srcCtx("GEMM_Blocked.scala:239:51") // FixMul(x3295,x3296)
    val x3298 = StoreBanks(List(x3278_d0_b0), List(b1931), x3297).name("x3298").ctrl(x3299).srcCtx("GEMM_Blocked.scala:239:36") // ParSRAMStore(x3278,List(List(b1931)),List(x3297),List(x3293))
    val x3300 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3300").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3301 = CounterChain(List(x3300)).name("x3301").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // CounterChainNew(ArrayBuffer(x3300))
    val x3318 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3301).name("x3318").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // UnrolledForeach(List(),x3301,Block(Const(())),ArrayBuffer(List(b1944)),ArrayBuffer(List(b1945)))
    val b1944 = CounterIter(x3300, None).name("b1944").ctrl(x3318) // b1944
    val b1945 = Const(true).name("b1945").ctrl(x3318) // b1945
    val x3302 = OpDef(op=BitAnd, inputs=List(b1945, b1863)).name("x3302").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(b1945,b1863)
    val x3303 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3303").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3304 = OpDef(op=BitAnd, inputs=List(x3302, x3303)).name("x3304").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(x3302,x3303)
    val x3305 = OpDef(op=BitAnd, inputs=List(x3304, b1804)).name("x3305").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(x3304,b1804)
    val x3306 = LoadBanks(List(x3278_d0_b0), List(b1944)).name("x3306").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // ParSRAMLoad(x3278,List(ArrayBuffer(b1944)),List(x3305))
    val x3307 = x3306 // x3307 = VectorApply(x3306,0)
    val x3308 = LoadBanks(List(x3275_d1_b0), List(b1944)).name("x3308").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // ParSRAMLoad(x3275,List(ArrayBuffer(b1944)),List(x3305))
    val x3309 = x3308 // x3309 = VectorApply(x3308,0)
    val x3310 = OpDef(op=BitAnd, inputs=List(b1919, b1863)).name("x3310").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // And(b1919,b1863)
    val x3311 = OpDef(op=BitAnd, inputs=List(x3310, x3303)).name("x3311").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // And(x3310,x3303)
    val x3312 = OpDef(op=BitAnd, inputs=List(x3311, b1804)).name("x3312").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // And(x3311,b1804)
    val x3313 = OpDef(op=BitAnd, inputs=List(x3312, x3305)).name("x3313").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // And(x3312,x3305)
    val x3314 = OpDef(op=FixEql, inputs=List(b1918, Const(0))).name("x3314").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // FixEql(b1918,Const(0))
    val x3315 = OpDef(op=FixAdd, inputs=List(x3307, x3309)).name("x3315").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:18") // FixAdd(x3307,x3309)
    val x3316 = OpDef(op=MuxOp, inputs=List(x3314, x3307, x3315)).name("x3316").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // Mux(x3314,x3307,x3315)
    val x3317 = StoreBanks(List(x3275_d0_b0, x3275_d1_b0), List(b1944), x3316).name("x3317").ctrl(x3318).srcCtx("GEMM_Blocked.scala:242:16") // ParSRAMStore(x3275,List(ArrayBuffer(b1944)),List(x3316),List(x3305))
    val x3320 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3320").ctrl(x3330).srcCtx("GEMM_Blocked.scala:243:26") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3321 = CounterChain(List(x3320)).name("x3321").ctrl(x3330).srcCtx("GEMM_Blocked.scala:243:31") // CounterChainNew(List(x3320))
    val x3329 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3321).name("x3329").ctrl(x3330).srcCtx("GEMM_Blocked.scala:243:31") // UnrolledForeach(List(b1863, b1818, b1809, b1804),x3321,Block(Const(())),List(List(b1966)),List(List(b1967)))
    val b1966 = CounterIter(x3320, None).name("b1966").ctrl(x3329) // b1966
    val b1967 = Const(true).name("b1967").ctrl(x3329) // b1967
    val x3322 = OpDef(op=BitAnd, inputs=List(b1967, b1863)).name("x3322").ctrl(x3329).srcCtx("UnrollingBase.scala:28:66") // And(b1967,b1863)
    val x3323 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3323").ctrl(x3329).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3324 = OpDef(op=BitAnd, inputs=List(x3322, x3323)).name("x3324").ctrl(x3329).srcCtx("UnrollingBase.scala:28:66") // And(x3322,x3323)
    val x3325 = OpDef(op=BitAnd, inputs=List(x3324, b1804)).name("x3325").ctrl(x3329).srcCtx("UnrollingBase.scala:28:66") // And(x3324,b1804)
    val x3326 = LoadBanks(List(x3275_d0_b0), List(b1966)).name("x3326").ctrl(x3329).srcCtx("GEMM_Blocked.scala:243:67") // ParSRAMLoad(x3275,List(List(b1966)),List(x3325))
    val x3327 = x3326 // x3327 = VectorApply(x3326,0)
    val x3328 = StoreBanks(List(x3191_d0_b0), List(b1862, b1966), x3327).name("x3328").ctrl(x3329).srcCtx("GEMM_Blocked.scala:243:60") // ParSRAMStore(x3191,List(List(b1862, b1966)),List(x3327),List(x3325))
    val x3331 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3331").ctrl(x3349).srcCtx("GEMM_Blocked.scala:246:12") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3332 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3332").ctrl(x3349).srcCtx("GEMM_Blocked.scala:246:12") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3333 = CounterChain(List(x3332,x3331)).name("x3333").ctrl(x3349).srcCtx("GEMM_Blocked.scala:246:12") // CounterChainNew(ArrayBuffer(x3332, x3331))
    val x3348 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3333).name("x3348").ctrl(x3349).srcCtx("GEMM_Blocked.scala:246:12") // UnrolledForeach(List(),x3333,Block(Const(())),ArrayBuffer(List(b1977), List(b1978)),ArrayBuffer(List(b1979), List(b1980)))
    val b1977 = CounterIter(x3332, Some(0)).name("b1977").ctrl(x3348) // b1977
    val b1979 = Const(true).name("b1979").ctrl(x3348) // b1979
    val b1978 = CounterIter(x3331, None).name("b1978").ctrl(x3348) // b1978
    val b1980 = Const(true).name("b1980").ctrl(x3348) // b1980
    val x3334 = OpDef(op=BitAnd, inputs=List(b1979, b1980)).name("x3334").ctrl(x3348).srcCtx("UnrollingBase.scala:28:66") // And(b1979,b1980)
    val x3335 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3335").ctrl(x3348).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3336 = OpDef(op=BitAnd, inputs=List(x3334, x3335)).name("x3336").ctrl(x3348).srcCtx("UnrollingBase.scala:28:66") // And(x3334,x3335)
    val x3337 = LoadBanks(List(x3191_d0_b0), List(b1977, b1978)).name("x3337").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // ParSRAMLoad(x3191,List(ArrayBuffer(b1977, b1978)),List(x3336))
    val x3338 = x3337 // x3338 = VectorApply(x3337,0)
    val x3339 = LoadBanks(List(x3187_d1_b0), List(b1977, b1978)).name("x3339").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // ParSRAMLoad(x3187,List(ArrayBuffer(b1977, b1978)),List(x3336))
    val x3340 = x3339 // x3340 = VectorApply(x3339,0)
    val x3341 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3341").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // And(b1818,b1809)
    val x3342 = OpDef(op=BitAnd, inputs=List(x3341, b1804)).name("x3342").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // And(x3341,b1804)
    val x3343 = OpDef(op=BitAnd, inputs=List(x3342, x3336)).name("x3343").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // And(x3342,x3336)
    val x3344 = OpDef(op=FixEql, inputs=List(b1817, Const(0))).name("x3344").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // FixEql(b1817,Const(0))
    val x3345 = OpDef(op=FixAdd, inputs=List(x3338, x3340)).name("x3345").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:14") // FixAdd(x3338,x3340)
    val x3346 = OpDef(op=MuxOp, inputs=List(x3344, x3338, x3345)).name("x3346").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // Mux(x3344,x3338,x3345)
    val x3347 = StoreBanks(List(x3187_d0_b0, x3187_d1_b0), List(b1977, b1978), x3346).name("x3347").ctrl(x3348).srcCtx("GEMM_Blocked.scala:246:12") // ParSRAMStore(x3187,List(ArrayBuffer(b1977, b1978)),List(x3346),List(x3336))
    val x3352 = UnitController(style=SeqPipe, level=InnerControl).name("x3352").ctrl(x3389).srcCtx("GEMM_Blocked.scala:225:40") // UnitPipe(List(b1809, b1804),Block(Const(())))
    val x3350 = OpDef(op=FixAdd, inputs=List(b1803, Const(64))).name("x3350").ctrl(x3352).srcCtx("GEMM_Blocked.scala:247:24") // FixAdd(b1803,Const(64))
    val x3351 = OpDef(op=FixAdd, inputs=List(b1808, Const(32))).name("x3351").ctrl(x3352).srcCtx("GEMM_Blocked.scala:247:36") // FixAdd(b1808,Const(32))
    val x3353 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3353").ctrl(x3389).srcCtx("GEMM_Blocked.scala:247:55") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3354 = CounterChain(List(x3353)).name("x3354").ctrl(x3389).srcCtx("GEMM_Blocked.scala:247:55") // CounterChainNew(List(x3353))
    val x3388 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3354).name("x3388").ctrl(x3389).srcCtx("GEMM_Blocked.scala:247:55") // UnrolledForeach(List(b1809, b1804),x3354,Block(Const(())),List(List(b2002)),List(List(b2003)))
    val b2002 = CounterIter(x3353, Some(0)).name("b2002").ctrl(x3388) // b2002
    val b2003 = Const(true).name("b2003").ctrl(x3388) // b2003
    val b3440 = StreamOut(field="offset").name("b3440").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // x3355 = StreamOutNew(BurstCmdBus)
    isAccum(b3440) = false
    bufferDepthOf(b3440) = 1
    val b3441 = StreamOut(field="size").name("b3441").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // x3355 = StreamOutNew(BurstCmdBus)
    isAccum(b3441) = false
    bufferDepthOf(b3441) = 1
    val x3356 = StreamOut(field="data").name("x3356").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // x3356 = StreamOutNew(BurstFullDataBus())
    isAccum(x3356) = false
    bufferDepthOf(x3356) = 1
    val x3357 = StreamIn(field="ack").name("x3357").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // x3357 = StreamInNew(BurstAckBus)
    isAccum(x3357) = false
    bufferDepthOf(x3357) = 1
    val x3372 = UnitController(style=SeqPipe, level=InnerControl).name("x3372").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // UnitPipe(List(b2003, b1809, b1804),Block(x3371))
    val x3358 = OpDef(op=FixAdd, inputs=List(b1803, b2002)).name("x3358").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // FixAdd(b1803,b2002)
    val x3359 = x3358 // FixConvert(x3358,TRUE,_32,_0) (Same Type. No op)
    val x3360 = ReadMem(x3167_d0).name("x3360").ctrl(x3372).srcCtx("GEMM_Blocked.scala:217:30") // RegRead(x3167)
    val x3361 = OpDef(op=FixMul, inputs=List(x3359, x3360)).name("x3361").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // FixMul(x3359,x3360)
    val x3362 = b1808 // FixConvert(b1808,TRUE,_32,_0) (Same Type. No op)
    val x3363 = OpDef(op=FixAdd, inputs=List(x3361, x3362)).name("x3363").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // FixAdd(x3361,x3362)
    val x3364 = OpDef(op=FixSla, inputs=List(x3363, Const(2))).name("x3364").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // FixLsh(x3363,Const(2))
    val x3365 = x3364 // FixConvert(x3364,TRUE,_64,_0)
    val x3366 = DramAddress(x3177).name("x3366").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // GetDRAMAddress(x3177)
    val x3368_x3367 = OpDef(op=FixAdd, inputs=List(x3365, x3366)).name("x3368_x3367").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // FixAdd(x3365,x3366)
    // x3368 = SimpleStruct(ArrayBuffer((offset,x3367), (size,Const(128)), (isLoad,Const(false))))
    val x3369 = OpDef(op=BitAnd, inputs=List(b2003, b1809)).name("x3369").ctrl(x3372).srcCtx("UnrollingBase.scala:28:66") // And(b2003,b1809)
    val x3370 = OpDef(op=BitAnd, inputs=List(x3369, b1804)).name("x3370").ctrl(x3372).srcCtx("UnrollingBase.scala:28:66") // And(x3369,b1804)
    val x3371_b3442_b3440 = WriteMem(b3440, x3368_x3367).name("x3371_b3442_b3440").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // StreamWrite(x3355,x3368,x3370)
    val x3371_b3443_b3441 = WriteMem(b3441, Const(128)).name("x3371_b3443_b3441").ctrl(x3372).srcCtx("GEMM_Blocked.scala:247:55") // StreamWrite(x3355,x3368,x3370)
    val x3373 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3373").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3374 = CounterChain(List(x3373)).name("x3374").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // CounterChainNew(List(x3373))
    val x3382 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3374).name("x3382").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // UnrolledForeach(List(b2003, b1809, b1804),x3374,Block(Const(())),List(List(b2024)),List(List(b2025)))
    val b2024 = CounterIter(x3373, None).name("b2024").ctrl(x3382) // b2024
    val b2025 = Const(true).name("b2025").ctrl(x3382) // b2025
    val x3375 = OpDef(op=BitAnd, inputs=List(b2025, b2003)).name("x3375").ctrl(x3382).srcCtx("UnrollingBase.scala:28:66") // And(b2025,b2003)
    val x3376 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3376").ctrl(x3382).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3377 = OpDef(op=BitAnd, inputs=List(x3375, x3376)).name("x3377").ctrl(x3382).srcCtx("UnrollingBase.scala:28:66") // And(x3375,x3376)
    val x3378 = LoadBanks(List(x3187_d0_b0), List(b2002, b2024)).name("x3378").ctrl(x3382).srcCtx("GEMM_Blocked.scala:247:55") // ParSRAMLoad(x3187,List(List(b2002, b2024)),List(x3377))
    val x3380_x3379 = x3378 // x3379 = VectorApply(x3378,0)
    // x3380 = SimpleStruct(ArrayBuffer((_1,x3379), (_2,Const(true))))
    val x3381_x3381_x3356 = WriteMem(x3356, x3380_x3379).name("x3381_x3381_x3356").ctrl(x3382).srcCtx("GEMM_Blocked.scala:247:55") // ParStreamWrite(x3356,List(x3380),List(x3377))
    val x3383 = FringeDenseStore(dram=List(x3177), cmdStream=List(b3440, b3441), dataStream=List(x3356), ackStream=List(x3357)).name("x3383").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // FringeDenseStore(x3177,x3355,x3356,x3357)
    val x3387 = UnitController(style=SeqPipe, level=InnerControl).name("x3387").ctrl(x3388).srcCtx("GEMM_Blocked.scala:247:55") // UnitPipe(List(b2003, b1809, b1804),Block(Const(())))
    val x3384 = OpDef(op=BitAnd, inputs=List(b2003, b1809)).name("x3384").ctrl(x3387).srcCtx("UnrollingBase.scala:28:66") // And(b2003,b1809)
    val x3385 = OpDef(op=BitAnd, inputs=List(x3384, b1804)).name("x3385").ctrl(x3387).srcCtx("UnrollingBase.scala:28:66") // And(x3384,b1804)
    val x3386_x3386 = ReadMem(x3357).name("x3386_x3386").ctrl(x3387).srcCtx("GEMM_Blocked.scala:247:55") // StreamRead(x3357,x3385)
    
  }
}
