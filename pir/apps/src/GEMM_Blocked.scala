import pir._
import pir.node._
import arch._
import prism.enums._

object GEMM_Blocked extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3157_d0 = ArgIn(init=0).name("x3157_d0").ctrl(top).srcCtx("GEMM_Blocked.scala:213:20") // ArgInNew(Const(0))
    isAccum(x3157_d0) = false
    bufferDepthOf(x3157_d0) = 1
    boundOf(x3157_d0) = 1024
    val x3159 = ReadMem(x3157_d0).name("x3159").ctrl(top).srcCtx("GEMM_Blocked.scala:215:30") // RegRead(x3157)
    val x3160 = ReadMem(x3157_d0).name("x3160").ctrl(top).srcCtx("GEMM_Blocked.scala:215:26") // RegRead(x3157)
    val x3161 = DRAM(dims=List(x3160, x3159)).name("x3161").ctrl(top).srcCtx("GEMM_Blocked.scala:215:25") // x3161 = DRAMNew(ArrayBuffer(x3160, x3159),Const(0))
    val x3162 = ReadMem(x3157_d0).name("x3162").ctrl(top).srcCtx("GEMM_Blocked.scala:216:30") // RegRead(x3157)
    val x3163 = ReadMem(x3157_d0).name("x3163").ctrl(top).srcCtx("GEMM_Blocked.scala:216:26") // RegRead(x3157)
    val x3164 = DRAM(dims=List(x3163, x3162)).name("x3164").ctrl(top).srcCtx("GEMM_Blocked.scala:216:25") // x3164 = DRAMNew(ArrayBuffer(x3163, x3162),Const(0))
    val x3165 = ReadMem(x3157_d0).name("x3165").ctrl(top).srcCtx("GEMM_Blocked.scala:217:30") // RegRead(x3157)
    val x3166 = ReadMem(x3157_d0).name("x3166").ctrl(top).srcCtx("GEMM_Blocked.scala:217:26") // RegRead(x3157)
    val x3167 = DRAM(dims=List(x3166, x3165)).name("x3167").ctrl(top).srcCtx("GEMM_Blocked.scala:217:25") // x3167 = DRAMNew(ArrayBuffer(x3166, x3165),Const(0))
    val x3379 = UnitController(style=SeqPipe, level=OuterControl).name("x3379").ctrl(top).srcCtx("GEMM_Blocked.scala:223:10") // Hwblock(Block(Const(())),false)
    val x3171 = ReadMem(x3157_d0).name("x3171").ctrl(x3379).srcCtx("GEMM_Blocked.scala:224:15") // RegRead(x3157)
    val x3172 = Counter(min=Const(0), max=x3171, step=Const(64), par=1).name("x3172").ctrl(x3379).srcCtx("GEMM_Blocked.scala:224:26") // CounterNew(Const(0),x3171,Const(64),Const(1))
    val x3173 = CounterChain(List(x3172)).name("x3173").ctrl(x3379).srcCtx("GEMM_Blocked.scala:224:39") // CounterChainNew(List(x3172))
    val x3378 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3173).name("x3378").ctrl(x3379).srcCtx("GEMM_Blocked.scala:224:39") // UnrolledForeach(List(Const(true)),x3173,Block(Const(())),List(List(b1803)),List(List(b1804)))
    val b1803 = CounterIter(x3172, Some(0)).name("b1803").ctrl(x3378) // b1803
    val b1804 = Const(true).name("b1804").ctrl(x3378) // b1804
    val x3174 = ReadMem(x3157_d0).name("x3174").ctrl(x3378).srcCtx("GEMM_Blocked.scala:225:17") // RegRead(x3157)
    val x3175 = Counter(min=Const(0), max=x3174, step=Const(32), par=1).name("x3175").ctrl(x3378).srcCtx("GEMM_Blocked.scala:225:27") // CounterNew(Const(0),x3174,Const(32),Const(1))
    val x3176 = CounterChain(List(x3175)).name("x3176").ctrl(x3378).srcCtx("GEMM_Blocked.scala:225:40") // CounterChainNew(List(x3175))
    val x3377 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3176).name("x3377").ctrl(x3378).srcCtx("GEMM_Blocked.scala:225:40") // UnrolledForeach(List(b1804),x3176,Block(Const(())),List(List(b1808)),List(List(b1809)))
    val b1808 = CounterIter(x3175, Some(0)).name("b1808").ctrl(x3377) // b1808
    val b1809 = Const(true).name("b1809").ctrl(x3377) // b1809
    val x3177_d0_b0 = SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3177_d0_b0").ctrl(x3377).srcCtx("GEMM_Blocked.scala:226:30") // x3177 = SRAMNew(ArrayBuffer(Const(64), Const(32)))
    isAccum(x3177_d0_b0) = false
    bufferDepthOf(x3177_d0_b0) = 3
    val x3177_d1_b0 = SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3177_d1_b0").ctrl(x3377).srcCtx("GEMM_Blocked.scala:226:30") // x3177 = SRAMNew(ArrayBuffer(Const(64), Const(32)))
    isAccum(x3177_d1_b0) = true
    bufferDepthOf(x3177_d1_b0) = 1
    val x3178 = ReadMem(x3157_d0).name("x3178").ctrl(x3377).srcCtx("GEMM_Blocked.scala:227:43") // RegRead(x3157)
    val x3179 = Counter(min=Const(0), max=x3178, step=Const(32), par=1).name("x3179").ctrl(x3377).srcCtx("GEMM_Blocked.scala:227:53") // CounterNew(Const(0),x3178,Const(32),Const(1))
    val x3180 = CounterChain(List(x3179)).name("x3180").ctrl(x3377).srcCtx("GEMM_Blocked.scala:246:12") // CounterChainNew(List(x3179))
    val x3337 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3180).name("x3337").ctrl(x3377).srcCtx("GEMM_Blocked.scala:246:12") // UnrolledReduce(List(b1809, b1804),x3180,x3177,Block((x3177) => Const(())),List(List(b1817)),List(List(b1818)))
    val b1817 = CounterIter(x3179, Some(0)).name("b1817").ctrl(x3337) // b1817
    val b1818 = Const(true).name("b1818").ctrl(x3337) // b1818
    val x3181_d0_b0 = SRAM(size=2048, banking=Strided(banks=16, stride=1)).name("x3181_d0_b0").ctrl(x3337).srcCtx("GEMM_Blocked.scala:228:40") // x3181 = SRAMNew(ArrayBuffer(Const(64), Const(32)))
    isAccum(x3181_d0_b0) = false
    bufferDepthOf(x3181_d0_b0) = 2
    val x3182_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x3182_d0_b0").ctrl(x3337).srcCtx("GEMM_Blocked.scala:229:33") // x3182 = SRAMNew(ArrayBuffer(Const(32), Const(32)))
    isAccum(x3182_d0_b0) = false
    bufferDepthOf(x3182_d0_b0) = 2
    val x3185 = UnitController(style=SeqPipe, level=InnerControl).name("x3185").ctrl(x3337).srcCtx("GEMM_Blocked.scala:246:12") // UnitPipe(List(b1818, b1809, b1804),Block(Const(())))
    val x3183 = OpDef(op=FixAdd, inputs=List(b1817, Const(32))).name("x3183").ctrl(x3185).srcCtx("GEMM_Blocked.scala:230:38") // FixAdd(b1817,Const(32))
    val x3184 = OpDef(op=FixAdd, inputs=List(b1808, Const(32))).name("x3184").ctrl(x3185).srcCtx("GEMM_Blocked.scala:230:49") // FixAdd(b1808,Const(32))
    val x3186 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3186").ctrl(x3337).srcCtx("GEMM_Blocked.scala:230:20") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3187 = CounterChain(List(x3186)).name("x3187").ctrl(x3337).srcCtx("GEMM_Blocked.scala:230:20") // CounterChainNew(List(x3186))
    val x3217 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3187).name("x3217").ctrl(x3337).srcCtx("GEMM_Blocked.scala:230:20") // UnrolledForeach(List(b1818, b1809, b1804),x3187,Block(Const(())),List(List(b1826)),List(List(b1827)))
    val b1826 = CounterIter(x3186, Some(0)).name("b1826").ctrl(x3217) // b1826
    val b1827 = Const(true).name("b1827").ctrl(x3217) // b1827
    val b3420 = StreamOut(field="offset").name("b3420").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // x3188 = StreamOutNew(BurstCmdBus)
    isAccum(b3420) = false
    bufferDepthOf(b3420) = 1
    val b3421 = StreamOut(field="size").name("b3421").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // x3188 = StreamOutNew(BurstCmdBus)
    isAccum(b3421) = false
    bufferDepthOf(b3421) = 1
    val x3189 = StreamIn(field="data").name("x3189").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // x3189 = StreamInNew(BurstDataBus())
    isAccum(x3189) = false
    bufferDepthOf(x3189) = 1
    val x3205 = UnitController(style=SeqPipe, level=InnerControl).name("x3205").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // UnitPipe(List(b1827, b1818, b1809, b1804),Block(x3204))
    val x3190 = OpDef(op=FixAdd, inputs=List(b1817, b1826)).name("x3190").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // FixAdd(b1817,b1826)
    val x3191 = x3190 // FixConvert(x3190,TRUE,_32,_0) (Same Type. No op)
    val x3192 = ReadMem(x3157_d0).name("x3192").ctrl(x3205).srcCtx("GEMM_Blocked.scala:216:30") // RegRead(x3157)
    val x3193 = OpDef(op=FixMul, inputs=List(x3191, x3192)).name("x3193").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // FixMul(x3191,x3192)
    val x3194 = b1808 // FixConvert(b1808,TRUE,_32,_0) (Same Type. No op)
    val x3195 = OpDef(op=FixAdd, inputs=List(x3193, x3194)).name("x3195").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // FixAdd(x3193,x3194)
    val x3196 = OpDef(op=FixSla, inputs=List(x3195, Const(2))).name("x3196").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // FixLsh(x3195,Const(2))
    val x3197 = x3196 // FixConvert(x3196,TRUE,_64,_0)
    val x3198 = DramAddress(x3164).name("x3198").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // GetDRAMAddress(x3164)
    val x3200_x3199 = OpDef(op=FixAdd, inputs=List(x3197, x3198)).name("x3200_x3199").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // FixAdd(x3197,x3198)
    // x3200 = SimpleStruct(ArrayBuffer((offset,x3199), (size,Const(128)), (isLoad,Const(true))))
    val x3201 = OpDef(op=BitAnd, inputs=List(b1827, b1818)).name("x3201").ctrl(x3205).srcCtx("UnrollingBase.scala:28:66") // And(b1827,b1818)
    val x3202 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3202").ctrl(x3205).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3203 = OpDef(op=BitAnd, inputs=List(x3201, x3202)).name("x3203").ctrl(x3205).srcCtx("UnrollingBase.scala:28:66") // And(x3201,x3202)
    val x3204_b3422_b3420 = WriteMem(b3420, x3200_x3199).name("x3204_b3422_b3420").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // StreamWrite(x3188,x3200,x3203)
    val x3204_b3423_b3421 = WriteMem(b3421, Const(128)).name("x3204_b3423_b3421").ctrl(x3205).srcCtx("GEMM_Blocked.scala:230:20") // StreamWrite(x3188,x3200,x3203)
    val x3206 = FringeDenseLoad(dram=List(x3164), cmdStream=List(b3420, b3421), dataStream=List(x3189)).name("x3206").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // FringeDenseLoad(x3164,x3188,x3189)
    val x3207 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3207").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3208 = CounterChain(List(x3207)).name("x3208").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // CounterChainNew(List(x3207))
    val x3216 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3208).name("x3216").ctrl(x3217).srcCtx("GEMM_Blocked.scala:230:20") // UnrolledForeach(List(b1827, b1818, b1809, b1804),x3208,Block(Const(())),List(List(b1849)),List(List(b1850)))
    val b1849 = CounterIter(x3207, None).name("b1849").ctrl(x3216) // b1849
    val b1850 = Const(true).name("b1850").ctrl(x3216) // b1850
    val x3209 = OpDef(op=BitAnd, inputs=List(b1850, b1827)).name("x3209").ctrl(x3216).srcCtx("UnrollingBase.scala:28:66") // And(b1850,b1827)
    val x3210 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3210").ctrl(x3216).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3211 = OpDef(op=BitAnd, inputs=List(x3209, x3210)).name("x3211").ctrl(x3216).srcCtx("UnrollingBase.scala:28:66") // And(x3209,x3210)
    val x3212 = OpDef(op=BitAnd, inputs=List(x3211, b1804)).name("x3212").ctrl(x3216).srcCtx("UnrollingBase.scala:28:66") // And(x3211,b1804)
    val x3213_x3213 = ReadMem(x3189).name("x3213_x3213").ctrl(x3216).srcCtx("GEMM_Blocked.scala:230:20") // ParStreamRead(x3189,List(x3212))
    val x3214_x3214 = x3213_x3213 // x3214 = VectorApply(x3213,0)
    val x3215 = StoreBanks(List(x3182_d0_b0), List(b1826, b1849), x3214_x3214).name("x3215").ctrl(x3216).srcCtx("GEMM_Blocked.scala:230:20") // ParSRAMStore(x3182,List(List(b1826, b1849)),List(x3214),List(x3212))
    val x3218 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3218").ctrl(x3337).srcCtx("GEMM_Blocked.scala:231:30") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3219 = CounterChain(List(x3218)).name("x3219").ctrl(x3337).srcCtx("GEMM_Blocked.scala:231:42") // CounterChainNew(List(x3218))
    val x3319 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3219).name("x3319").ctrl(x3337).srcCtx("GEMM_Blocked.scala:231:42") // UnrolledForeach(List(b1818, b1809, b1804),x3219,Block(Const(())),List(List(b1862)),List(List(b1863)))
    val b1862 = CounterIter(x3218, Some(0)).name("b1862").ctrl(x3319) // b1862
    val b1863 = Const(true).name("b1863").ctrl(x3319) // b1863
    val x3220_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x3220_d0_b0").ctrl(x3319).srcCtx("GEMM_Blocked.scala:232:35") // x3220 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3220_d0_b0) = false
    bufferDepthOf(x3220_d0_b0) = 2
    val x3221 = Reg(init=Some(0)).name("x3221").ctrl(x3319).srcCtx("GEMM_Blocked.scala:231:42") // x3221 = RegNew(Const(0))
    isAccum(x3221) = false
    bufferDepthOf(x3221) = 2
    val x3229 = UnitController(style=SeqPipe, level=InnerControl).name("x3229").ctrl(x3319).srcCtx("GEMM_Blocked.scala:231:42") // UnitPipe(List(b1863, b1818, b1809, b1804),Block(Const(())))
    val x3222 = OpDef(op=FixAdd, inputs=List(b1803, b1862)).name("x3222").ctrl(x3229).srcCtx("GEMM_Blocked.scala:233:36") // FixAdd(b1803,b1862)
    val x3223 = OpDef(op=FixAdd, inputs=List(b1817, Const(32))).name("x3223").ctrl(x3229).srcCtx("GEMM_Blocked.scala:233:46") // FixAdd(b1817,Const(32))
    val x3224 = OpDef(op=FixAdd, inputs=List(x3222, Const(1))).name("x3224").ctrl(x3229).srcCtx("GEMM_Blocked.scala:233:33") // FixAdd(x3222,Const(1))
    val x3225 = OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3225").ctrl(x3229).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1818)
    val x3226 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3226").ctrl(x3229).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3227 = OpDef(op=BitAnd, inputs=List(x3225, x3226)).name("x3227").ctrl(x3229).srcCtx("UnrollingBase.scala:28:66") // And(x3225,x3226)
    val x3228_x3221 = WriteMem(x3221, x3222).name("x3228_x3221").ctrl(x3229).srcCtx("GEMM_Blocked.scala:231:42") // RegWrite(x3221,x3222,x3227)
    val x3230 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x3230").ctrl(x3319).srcCtx("GEMM_Blocked.scala:233:22") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x3231 = CounterChain(List(x3230)).name("x3231").ctrl(x3319).srcCtx("GEMM_Blocked.scala:233:22") // CounterChainNew(List(x3230))
    val x3264 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3231).name("x3264").ctrl(x3319).srcCtx("GEMM_Blocked.scala:233:22") // UnrolledForeach(List(b1863, b1818, b1809, b1804),x3231,Block(Const(())),List(List(b1876)),List(List(b1877)))
    val b1876 = CounterIter(x3230, Some(0)).name("b1876").ctrl(x3264) // b1876
    val b1877 = Const(true).name("b1877").ctrl(x3264) // b1877
    val b3424 = StreamOut(field="offset").name("b3424").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // x3232 = StreamOutNew(BurstCmdBus)
    isAccum(b3424) = false
    bufferDepthOf(b3424) = 1
    val b3425 = StreamOut(field="size").name("b3425").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // x3232 = StreamOutNew(BurstCmdBus)
    isAccum(b3425) = false
    bufferDepthOf(b3425) = 1
    val x3233 = StreamIn(field="data").name("x3233").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // x3233 = StreamInNew(BurstDataBus())
    isAccum(x3233) = false
    bufferDepthOf(x3233) = 1
    val x3251 = UnitController(style=SeqPipe, level=InnerControl).name("x3251").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // UnitPipe(List(b1877, b1863, b1818, b1809, b1804),Block(x3250))
    val x3234 = ReadMem(x3221).name("x3234").ctrl(x3251).srcCtx("GEMM_Blocked.scala:231:42") // RegRead(x3221)
    val x3235 = OpDef(op=FixAdd, inputs=List(x3234, b1876)).name("x3235").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // FixAdd(x3234,b1876)
    val x3236 = x3235 // FixConvert(x3235,TRUE,_32,_0) (Same Type. No op)
    val x3237 = ReadMem(x3157_d0).name("x3237").ctrl(x3251).srcCtx("GEMM_Blocked.scala:215:30") // RegRead(x3157)
    val x3238 = OpDef(op=FixMul, inputs=List(x3236, x3237)).name("x3238").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // FixMul(x3236,x3237)
    val x3239 = b1817 // FixConvert(b1817,TRUE,_32,_0) (Same Type. No op)
    val x3240 = OpDef(op=FixAdd, inputs=List(x3238, x3239)).name("x3240").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // FixAdd(x3238,x3239)
    val x3241 = OpDef(op=FixSla, inputs=List(x3240, Const(2))).name("x3241").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // FixLsh(x3240,Const(2))
    val x3242 = x3241 // FixConvert(x3241,TRUE,_64,_0)
    val x3243 = DramAddress(x3161).name("x3243").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // GetDRAMAddress(x3161)
    val x3245_x3244 = OpDef(op=FixAdd, inputs=List(x3242, x3243)).name("x3245_x3244").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // FixAdd(x3242,x3243)
    // x3245 = SimpleStruct(ArrayBuffer((offset,x3244), (size,Const(128)), (isLoad,Const(true))))
    val x3246 = OpDef(op=BitAnd, inputs=List(b1877, b1863)).name("x3246").ctrl(x3251).srcCtx("UnrollingBase.scala:28:66") // And(b1877,b1863)
    val x3247 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3247").ctrl(x3251).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3248 = OpDef(op=BitAnd, inputs=List(x3246, x3247)).name("x3248").ctrl(x3251).srcCtx("UnrollingBase.scala:28:66") // And(x3246,x3247)
    val x3249 = OpDef(op=BitAnd, inputs=List(x3248, b1804)).name("x3249").ctrl(x3251).srcCtx("UnrollingBase.scala:28:66") // And(x3248,b1804)
    val x3250_b3426_b3424 = WriteMem(b3424, x3245_x3244).name("x3250_b3426_b3424").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // StreamWrite(x3232,x3245,x3249)
    val x3250_b3427_b3425 = WriteMem(b3425, Const(128)).name("x3250_b3427_b3425").ctrl(x3251).srcCtx("GEMM_Blocked.scala:233:22") // StreamWrite(x3232,x3245,x3249)
    val x3252 = FringeDenseLoad(dram=List(x3161), cmdStream=List(b3424, b3425), dataStream=List(x3233)).name("x3252").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // FringeDenseLoad(x3161,x3232,x3233)
    val x3253 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3253").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3254 = CounterChain(List(x3253)).name("x3254").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // CounterChainNew(List(x3253))
    val x3263 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3254).name("x3263").ctrl(x3264).srcCtx("GEMM_Blocked.scala:233:22") // UnrolledForeach(List(b1877, b1863, b1818, b1809, b1804),x3254,Block(Const(())),List(List(b1901)),List(List(b1902)))
    val b1901 = CounterIter(x3253, None).name("b1901").ctrl(x3263) // b1901
    val b1902 = Const(true).name("b1902").ctrl(x3263) // b1902
    val x3255 = OpDef(op=BitAnd, inputs=List(b1902, b1877)).name("x3255").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(b1902,b1877)
    val x3256 = OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3256").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1818)
    val x3257 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3257").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3258 = OpDef(op=BitAnd, inputs=List(x3255, x3256)).name("x3258").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(x3255,x3256)
    val x3259 = OpDef(op=BitAnd, inputs=List(x3258, x3257)).name("x3259").ctrl(x3263).srcCtx("UnrollingBase.scala:28:66") // And(x3258,x3257)
    val x3260_x3260 = ReadMem(x3233).name("x3260_x3260").ctrl(x3263).srcCtx("GEMM_Blocked.scala:233:22") // ParStreamRead(x3233,List(x3259))
    val x3261_x3261 = x3260_x3260 // x3261 = VectorApply(x3260,0)
    val x3262 = StoreBanks(List(x3220_d0_b0), List(b1901), x3261_x3261).name("x3262").ctrl(x3263).srcCtx("GEMM_Blocked.scala:233:22") // ParSRAMStore(x3220,List(List(b1901)),List(x3261),List(x3259))
    val x3265_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3265_d0_b0").ctrl(x3319).srcCtx("GEMM_Blocked.scala:234:34") // x3265 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3265_d0_b0) = false
    bufferDepthOf(x3265_d0_b0) = 2
    val x3265_d1_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3265_d1_b0").ctrl(x3319).srcCtx("GEMM_Blocked.scala:234:34") // x3265 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3265_d1_b0) = true
    bufferDepthOf(x3265_d1_b0) = 1
    val x3266 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3266").ctrl(x3319).srcCtx("GEMM_Blocked.scala:235:55") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3267 = CounterChain(List(x3266)).name("x3267").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // CounterChainNew(List(x3266))
    val x3308 = LoopController(style=MetaPipe, level=OuterControl, cchain=x3267).name("x3308").ctrl(x3319).srcCtx("GEMM_Blocked.scala:242:16") // UnrolledReduce(List(b1863, b1818, b1809, b1804),x3267,x3265,Block((x3265) => Const(())),List(List(b1918)),List(List(b1919)))
    val b1918 = CounterIter(x3266, Some(0)).name("b1918").ctrl(x3308) // b1918
    val b1919 = Const(true).name("b1919").ctrl(x3308) // b1919
    val x3268_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x3268_d0_b0").ctrl(x3308).srcCtx("GEMM_Blocked.scala:236:44") // x3268 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x3268_d0_b0) = false
    bufferDepthOf(x3268_d0_b0) = 2
    val x3269 = Reg(init=Some(0.0)).name("x3269").ctrl(x3308).srcCtx("GEMM_Blocked.scala:242:16") // x3269 = RegNew(Const(0))
    isAccum(x3269) = false
    bufferDepthOf(x3269) = 2
    val x3276 = UnitController(style=SeqPipe, level=InnerControl).name("x3276").ctrl(x3308).srcCtx("GEMM_Blocked.scala:242:16") // UnitPipe(List(b1919, b1863, b1818, b1809, b1804),Block(Const(())))
    val x3270 = OpDef(op=BitAnd, inputs=List(b1919, b1863)).name("x3270").ctrl(x3276).srcCtx("UnrollingBase.scala:28:66") // And(b1919,b1863)
    val x3271 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3271").ctrl(x3276).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3272 = OpDef(op=BitAnd, inputs=List(x3270, x3271)).name("x3272").ctrl(x3276).srcCtx("UnrollingBase.scala:28:66") // And(x3270,x3271)
    val x3273 = OpDef(op=BitAnd, inputs=List(x3272, b1804)).name("x3273").ctrl(x3276).srcCtx("UnrollingBase.scala:28:66") // And(x3272,b1804)
    val x3274 = LoadBanks(List(x3220_d0_b0), List(b1918)).name("x3274").ctrl(x3276).srcCtx("GEMM_Blocked.scala:237:36") // SRAMLoad(x3220,ArrayBuffer(Const(32)),List(b1918),Const(0),x3273)
    val x3275_x3269 = WriteMem(x3269, x3274).name("x3275_x3269").ctrl(x3276).srcCtx("GEMM_Blocked.scala:242:16") // RegWrite(x3269,x3274,x3273)
    val x3277 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3277").ctrl(x3308).srcCtx("GEMM_Blocked.scala:238:33") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3278 = CounterChain(List(x3277)).name("x3278").ctrl(x3308).srcCtx("GEMM_Blocked.scala:238:45") // CounterChainNew(List(x3277))
    val x3289 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3278).name("x3289").ctrl(x3308).srcCtx("GEMM_Blocked.scala:238:45") // UnrolledForeach(List(b1919, b1863, b1818, b1809, b1804),x3278,Block(Const(())),List(List(b1931)),List(List(b1932)))
    val b1931 = CounterIter(x3277, None).name("b1931").ctrl(x3289) // b1931
    val b1932 = Const(true).name("b1932").ctrl(x3289) // b1932
    val x3279 = OpDef(op=BitAnd, inputs=List(b1932, b1919)).name("x3279").ctrl(x3289).srcCtx("UnrollingBase.scala:28:66") // And(b1932,b1919)
    val x3280 = OpDef(op=BitAnd, inputs=List(b1863, b1818)).name("x3280").ctrl(x3289).srcCtx("UnrollingBase.scala:28:66") // And(b1863,b1818)
    val x3281 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3281").ctrl(x3289).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3282 = OpDef(op=BitAnd, inputs=List(x3279, x3280)).name("x3282").ctrl(x3289).srcCtx("UnrollingBase.scala:28:66") // And(x3279,x3280)
    val x3283 = OpDef(op=BitAnd, inputs=List(x3282, x3281)).name("x3283").ctrl(x3289).srcCtx("UnrollingBase.scala:28:66") // And(x3282,x3281)
    val x3284 = LoadBanks(List(x3182_d0_b0), List(b1918, b1931)).name("x3284").ctrl(x3289).srcCtx("GEMM_Blocked.scala:239:44") // ParSRAMLoad(x3182,List(List(b1918, b1931)),List(x3283))
    val x3285 = x3284 // x3285 = VectorApply(x3284,0)
    val x3286 = ReadMem(x3269).name("x3286").ctrl(x3289).srcCtx("GEMM_Blocked.scala:242:16") // RegRead(x3269)
    val x3287 = OpDef(op=FixMul, inputs=List(x3285, x3286)).name("x3287").ctrl(x3289).srcCtx("GEMM_Blocked.scala:239:51") // FixMul(x3285,x3286)
    val x3288 = StoreBanks(List(x3268_d0_b0), List(b1931), x3287).name("x3288").ctrl(x3289).srcCtx("GEMM_Blocked.scala:239:36") // ParSRAMStore(x3268,List(List(b1931)),List(x3287),List(x3283))
    val x3290 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3290").ctrl(x3308).srcCtx("GEMM_Blocked.scala:242:16") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3291 = CounterChain(List(x3290)).name("x3291").ctrl(x3308).srcCtx("GEMM_Blocked.scala:242:16") // CounterChainNew(ArrayBuffer(x3290))
    val x3307 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3291).name("x3307").ctrl(x3308).srcCtx("GEMM_Blocked.scala:242:16") // UnrolledForeach(List(),x3291,Block(Const(())),ArrayBuffer(List(b1944)),ArrayBuffer(List(b1945)))
    val b1944 = CounterIter(x3290, None).name("b1944").ctrl(x3307) // b1944
    val b1945 = Const(true).name("b1945").ctrl(x3307) // b1945
    val x3292 = OpDef(op=BitAnd, inputs=List(b1945, b1863)).name("x3292").ctrl(x3307).srcCtx("UnrollingBase.scala:28:66") // And(b1945,b1863)
    val x3293 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3293").ctrl(x3307).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3294 = OpDef(op=BitAnd, inputs=List(x3292, x3293)).name("x3294").ctrl(x3307).srcCtx("UnrollingBase.scala:28:66") // And(x3292,x3293)
    val x3295 = OpDef(op=BitAnd, inputs=List(x3294, b1804)).name("x3295").ctrl(x3307).srcCtx("UnrollingBase.scala:28:66") // And(x3294,b1804)
    val x3296 = LoadBanks(List(x3268_d0_b0), List(b1944)).name("x3296").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // ParSRAMLoad(x3268,List(ArrayBuffer(b1944)),List(x3295))
    val x3297 = x3296 // x3297 = VectorApply(x3296,0)
    val x3298 = LoadBanks(List(x3265_d1_b0), List(b1944)).name("x3298").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // ParSRAMLoad(x3265,List(ArrayBuffer(b1944)),List(x3295))
    val x3299 = x3298 // x3299 = VectorApply(x3298,0)
    val x3300 = OpDef(op=BitAnd, inputs=List(b1919, b1863)).name("x3300").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // And(b1919,b1863)
    val x3301 = OpDef(op=BitAnd, inputs=List(x3300, x3293)).name("x3301").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // And(x3300,x3293)
    val x3302 = OpDef(op=BitAnd, inputs=List(x3301, b1804)).name("x3302").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // And(x3301,b1804)
    val x3303 = OpDef(op=BitAnd, inputs=List(x3302, x3295)).name("x3303").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // And(x3302,x3295)
    val x3304 = OpDef(op=FixEql, inputs=List(b1918, Const(0))).name("x3304").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // FixEql(b1918,Const(0))
    val x3305 = ReduceAccumOp(op=FixAdd, input=x3297, accum=x3299).name("x3305").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:18") // FixAdd(x3297,x3299)
    val x3306 = StoreBanks(List(x3265_d0_b0, x3265_d1_b0), List(b1944), x3305).name("x3306").ctrl(x3307).srcCtx("GEMM_Blocked.scala:242:16") // ParSRAMStore(x3265,List(ArrayBuffer(b1944)),List(x3305),List(x3295))
    val x3309 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x3309").ctrl(x3319).srcCtx("GEMM_Blocked.scala:243:24") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x3310 = CounterChain(List(x3309)).name("x3310").ctrl(x3319).srcCtx("GEMM_Blocked.scala:243:29") // CounterChainNew(List(x3309))
    val x3318 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3310).name("x3318").ctrl(x3319).srcCtx("GEMM_Blocked.scala:243:29") // UnrolledForeach(List(b1863, b1818, b1809, b1804),x3310,Block(Const(())),List(List(b1965)),List(List(b1966)))
    val b1965 = CounterIter(x3309, None).name("b1965").ctrl(x3318) // b1965
    val b1966 = Const(true).name("b1966").ctrl(x3318) // b1966
    val x3311 = OpDef(op=BitAnd, inputs=List(b1966, b1863)).name("x3311").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(b1966,b1863)
    val x3312 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3312").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(b1818,b1809)
    val x3313 = OpDef(op=BitAnd, inputs=List(x3311, x3312)).name("x3313").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(x3311,x3312)
    val x3314 = OpDef(op=BitAnd, inputs=List(x3313, b1804)).name("x3314").ctrl(x3318).srcCtx("UnrollingBase.scala:28:66") // And(x3313,b1804)
    val x3315 = LoadBanks(List(x3265_d0_b0), List(b1965)).name("x3315").ctrl(x3318).srcCtx("GEMM_Blocked.scala:243:65") // ParSRAMLoad(x3265,List(List(b1965)),List(x3314))
    val x3316 = x3315 // x3316 = VectorApply(x3315,0)
    val x3317 = StoreBanks(List(x3181_d0_b0), List(b1862, b1965), x3316).name("x3317").ctrl(x3318).srcCtx("GEMM_Blocked.scala:243:58") // ParSRAMStore(x3181,List(List(b1862, b1965)),List(x3316),List(x3314))
    val x3320 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3320").ctrl(x3337).srcCtx("GEMM_Blocked.scala:246:12") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3321 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3321").ctrl(x3337).srcCtx("GEMM_Blocked.scala:246:12") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3322 = CounterChain(List(x3321,x3320)).name("x3322").ctrl(x3337).srcCtx("GEMM_Blocked.scala:246:12") // CounterChainNew(ArrayBuffer(x3321, x3320))
    val x3336 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3322).name("x3336").ctrl(x3337).srcCtx("GEMM_Blocked.scala:246:12") // UnrolledForeach(List(),x3322,Block(Const(())),ArrayBuffer(List(b1976), List(b1977)),ArrayBuffer(List(b1978), List(b1979)))
    val b1976 = CounterIter(x3321, Some(0)).name("b1976").ctrl(x3336) // b1976
    val b1978 = Const(true).name("b1978").ctrl(x3336) // b1978
    val b1977 = CounterIter(x3320, None).name("b1977").ctrl(x3336) // b1977
    val b1979 = Const(true).name("b1979").ctrl(x3336) // b1979
    val x3323 = OpDef(op=BitAnd, inputs=List(b1978, b1979)).name("x3323").ctrl(x3336).srcCtx("UnrollingBase.scala:28:66") // And(b1978,b1979)
    val x3324 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3324").ctrl(x3336).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3325 = OpDef(op=BitAnd, inputs=List(x3323, x3324)).name("x3325").ctrl(x3336).srcCtx("UnrollingBase.scala:28:66") // And(x3323,x3324)
    val x3326 = LoadBanks(List(x3181_d0_b0), List(b1976, b1977)).name("x3326").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // ParSRAMLoad(x3181,List(ArrayBuffer(b1976, b1977)),List(x3325))
    val x3327 = x3326 // x3327 = VectorApply(x3326,0)
    val x3328 = LoadBanks(List(x3177_d1_b0), List(b1976, b1977)).name("x3328").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // ParSRAMLoad(x3177,List(ArrayBuffer(b1976, b1977)),List(x3325))
    val x3329 = x3328 // x3329 = VectorApply(x3328,0)
    val x3330 = OpDef(op=BitAnd, inputs=List(b1818, b1809)).name("x3330").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // And(b1818,b1809)
    val x3331 = OpDef(op=BitAnd, inputs=List(x3330, b1804)).name("x3331").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // And(x3330,b1804)
    val x3332 = OpDef(op=BitAnd, inputs=List(x3331, x3325)).name("x3332").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // And(x3331,x3325)
    val x3333 = OpDef(op=FixEql, inputs=List(b1817, Const(0))).name("x3333").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // FixEql(b1817,Const(0))
    val x3334 = ReduceAccumOp(op=FixAdd, input=x3327, accum=x3329).name("x3334").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:14") // FixAdd(x3327,x3329)
    val x3335 = StoreBanks(List(x3177_d0_b0, x3177_d1_b0), List(b1976, b1977), x3334).name("x3335").ctrl(x3336).srcCtx("GEMM_Blocked.scala:246:12") // ParSRAMStore(x3177,List(ArrayBuffer(b1976, b1977)),List(x3334),List(x3325))
    val x3340 = UnitController(style=SeqPipe, level=InnerControl).name("x3340").ctrl(x3377).srcCtx("GEMM_Blocked.scala:225:40") // UnitPipe(List(b1809, b1804),Block(Const(())))
    val x3338 = OpDef(op=FixAdd, inputs=List(b1803, Const(64))).name("x3338").ctrl(x3340).srcCtx("GEMM_Blocked.scala:247:24") // FixAdd(b1803,Const(64))
    val x3339 = OpDef(op=FixAdd, inputs=List(b1808, Const(32))).name("x3339").ctrl(x3340).srcCtx("GEMM_Blocked.scala:247:36") // FixAdd(b1808,Const(32))
    val x3341 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x3341").ctrl(x3377).srcCtx("GEMM_Blocked.scala:247:55") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x3342 = CounterChain(List(x3341)).name("x3342").ctrl(x3377).srcCtx("GEMM_Blocked.scala:247:55") // CounterChainNew(List(x3341))
    val x3376 = LoopController(style=StreamPipe, level=OuterControl, cchain=x3342).name("x3376").ctrl(x3377).srcCtx("GEMM_Blocked.scala:247:55") // UnrolledForeach(List(b1809, b1804),x3342,Block(Const(())),List(List(b2000)),List(List(b2001)))
    val b2000 = CounterIter(x3341, Some(0)).name("b2000").ctrl(x3376) // b2000
    val b2001 = Const(true).name("b2001").ctrl(x3376) // b2001
    val b3428 = StreamOut(field="offset").name("b3428").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // x3343 = StreamOutNew(BurstCmdBus)
    isAccum(b3428) = false
    bufferDepthOf(b3428) = 1
    val b3429 = StreamOut(field="size").name("b3429").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // x3343 = StreamOutNew(BurstCmdBus)
    isAccum(b3429) = false
    bufferDepthOf(b3429) = 1
    val x3344 = StreamOut(field="data").name("x3344").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // x3344 = StreamOutNew(BurstFullDataBus())
    isAccum(x3344) = false
    bufferDepthOf(x3344) = 1
    val x3345 = StreamIn(field="ack").name("x3345").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // x3345 = StreamInNew(BurstAckBus)
    isAccum(x3345) = false
    bufferDepthOf(x3345) = 1
    val x3360 = UnitController(style=SeqPipe, level=InnerControl).name("x3360").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // UnitPipe(List(b2001, b1809, b1804),Block(x3359))
    val x3346 = OpDef(op=FixAdd, inputs=List(b1803, b2000)).name("x3346").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // FixAdd(b1803,b2000)
    val x3347 = x3346 // FixConvert(x3346,TRUE,_32,_0) (Same Type. No op)
    val x3348 = ReadMem(x3157_d0).name("x3348").ctrl(x3360).srcCtx("GEMM_Blocked.scala:217:30") // RegRead(x3157)
    val x3349 = OpDef(op=FixMul, inputs=List(x3347, x3348)).name("x3349").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // FixMul(x3347,x3348)
    val x3350 = b1808 // FixConvert(b1808,TRUE,_32,_0) (Same Type. No op)
    val x3351 = OpDef(op=FixAdd, inputs=List(x3349, x3350)).name("x3351").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // FixAdd(x3349,x3350)
    val x3352 = OpDef(op=FixSla, inputs=List(x3351, Const(2))).name("x3352").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // FixLsh(x3351,Const(2))
    val x3353 = x3352 // FixConvert(x3352,TRUE,_64,_0)
    val x3354 = DramAddress(x3167).name("x3354").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // GetDRAMAddress(x3167)
    val x3356_x3355 = OpDef(op=FixAdd, inputs=List(x3353, x3354)).name("x3356_x3355").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // FixAdd(x3353,x3354)
    // x3356 = SimpleStruct(ArrayBuffer((offset,x3355), (size,Const(128)), (isLoad,Const(false))))
    val x3357 = OpDef(op=BitAnd, inputs=List(b2001, b1809)).name("x3357").ctrl(x3360).srcCtx("UnrollingBase.scala:28:66") // And(b2001,b1809)
    val x3358 = OpDef(op=BitAnd, inputs=List(x3357, b1804)).name("x3358").ctrl(x3360).srcCtx("UnrollingBase.scala:28:66") // And(x3357,b1804)
    val x3359_b3430_b3428 = WriteMem(b3428, x3356_x3355).name("x3359_b3430_b3428").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // StreamWrite(x3343,x3356,x3358)
    val x3359_b3431_b3429 = WriteMem(b3429, Const(128)).name("x3359_b3431_b3429").ctrl(x3360).srcCtx("GEMM_Blocked.scala:247:55") // StreamWrite(x3343,x3356,x3358)
    val x3361 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x3361").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x3362 = CounterChain(List(x3361)).name("x3362").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // CounterChainNew(List(x3361))
    val x3370 = LoopController(style=InnerPipe, level=InnerControl, cchain=x3362).name("x3370").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // UnrolledForeach(List(b2001, b1809, b1804),x3362,Block(Const(())),List(List(b2022)),List(List(b2023)))
    val b2022 = CounterIter(x3361, None).name("b2022").ctrl(x3370) // b2022
    val b2023 = Const(true).name("b2023").ctrl(x3370) // b2023
    val x3363 = OpDef(op=BitAnd, inputs=List(b2023, b2001)).name("x3363").ctrl(x3370).srcCtx("UnrollingBase.scala:28:66") // And(b2023,b2001)
    val x3364 = OpDef(op=BitAnd, inputs=List(b1809, b1804)).name("x3364").ctrl(x3370).srcCtx("UnrollingBase.scala:28:66") // And(b1809,b1804)
    val x3365 = OpDef(op=BitAnd, inputs=List(x3363, x3364)).name("x3365").ctrl(x3370).srcCtx("UnrollingBase.scala:28:66") // And(x3363,x3364)
    val x3366 = LoadBanks(List(x3177_d0_b0), List(b2000, b2022)).name("x3366").ctrl(x3370).srcCtx("GEMM_Blocked.scala:247:55") // ParSRAMLoad(x3177,List(List(b2000, b2022)),List(x3365))
    val x3368_x3367 = x3366 // x3367 = VectorApply(x3366,0)
    // x3368 = SimpleStruct(ArrayBuffer((_1,x3367), (_2,Const(true))))
    val x3369_x3369_x3344 = WriteMem(x3344, x3368_x3367).name("x3369_x3369_x3344").ctrl(x3370).srcCtx("GEMM_Blocked.scala:247:55") // ParStreamWrite(x3344,List(x3368),List(x3365))
    val x3371 = FringeDenseStore(dram=List(x3167), cmdStream=List(b3428, b3429), dataStream=List(x3344), ackStream=List(x3345)).name("x3371").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // FringeDenseStore(x3167,x3343,x3344,x3345)
    val x3375 = UnitController(style=SeqPipe, level=InnerControl).name("x3375").ctrl(x3376).srcCtx("GEMM_Blocked.scala:247:55") // UnitPipe(List(b2001, b1809, b1804),Block(Const(())))
    val x3372 = OpDef(op=BitAnd, inputs=List(b2001, b1809)).name("x3372").ctrl(x3375).srcCtx("UnrollingBase.scala:28:66") // And(b2001,b1809)
    val x3373 = OpDef(op=BitAnd, inputs=List(x3372, b1804)).name("x3373").ctrl(x3375).srcCtx("UnrollingBase.scala:28:66") // And(x3372,b1804)
    val x3374_x3374 = ReadMem(x3345).name("x3374_x3374").ctrl(x3375).srcCtx("GEMM_Blocked.scala:247:55") // StreamRead(x3345,x3373)
    
  }
}
