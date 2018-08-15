import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMPipeTest extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6267 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1), Const(128))).name("x6267").srcCtx("LSTMPipe.scala:28:23:dout") } // x6267 = DRAMNew(ArrayBuffer(Const(1), Const(128)),Const(0))
    val x6848 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x6848").srcCtx("LSTMPipe.scala:30:11") } // Hwblock(Block(Const(())),false)
    val x6268 = withCtrl(x6848) { DRAM(dims=List(Const(1), Const(128))).name("x6268").srcCtx("DataGenerator.scala:156:20:k") } // x6268 = DRAMNew(ArrayBuffer(Const(1), Const(128)),Const(0))
    val x6270_d0_b0 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6270_d0_b0").srcCtx("DataGenerator.scala:43:21:x") } // x6270 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x6270_d0_b0) = false
    bufferDepthOf(x6270_d0_b0) = 1
    staticDimsOf(x6270_d0_b0) = List(1, 128)
    val x6270_d1_b0 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6270_d1_b0").srcCtx("DataGenerator.scala:43:21:x") } // x6270 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x6270_d1_b0) = false
    bufferDepthOf(x6270_d1_b0) = 1
    staticDimsOf(x6270_d1_b0) = List(1, 128)
    val x6278 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6278").srcCtx("LSTMPipe.scala:30:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6279 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6279").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6280 = withCtrl(x6848) { CounterChain(List(x6279)).name("x6280").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6279))
    val x6302 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6280).name("x6302").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6280,Block(Const(())),List(List(b3212)),List(List(b3213)))
    val b3212 = withCtrl(x6302) { CounterIter(x6279, Some(0)).name("b3212") } // b3212
    val b3213 = withCtrl(x6302) { Const(true).name("b3213") } // b3213
    val b6859 = withCtrl(x6302) { StreamOut(field="offset").name("b6859").srcCtx("DataGenerator.scala:44:8") } // x6281 = StreamOutNew(BurstCmdBus)
    isAccum(b6859) = false
    bufferDepthOf(b6859) = 1
    val b6860 = withCtrl(x6302) { StreamOut(field="size").name("b6860").srcCtx("DataGenerator.scala:44:8") } // x6281 = StreamOutNew(BurstCmdBus)
    isAccum(b6860) = false
    bufferDepthOf(b6860) = 1
    val x6282 = withCtrl(x6302) { StreamIn(field="data").name("x6282").srcCtx("DataGenerator.scala:44:8") } // x6282 = StreamInNew(BurstDataBus())
    isAccum(x6282) = false
    bufferDepthOf(x6282) = 1
    val x6293 = withCtrl(x6302) { UnitController(style=SeqPipe, level=InnerControl).name("x6293").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3213),Block(x6292))
    val x6283 = withCtrl(x6293) { b3212 } // FixConvert(b3212,TRUE,_32,_0) (Same Type. No op)
    val x6284 = withCtrl(x6293) { OpDef(op=FixSla, inputs=List(x6283, Const(7))).name("x6284").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6283,Const(7))
    val x6285 = withCtrl(x6293) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6286 = withCtrl(x6293) { OpDef(op=FixAdd, inputs=List(x6284, x6285)).name("x6286").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6284,x6285)
    val x6287 = withCtrl(x6293) { OpDef(op=FixSla, inputs=List(x6286, Const(2))).name("x6287").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6286,Const(2))
    val x6288 = withCtrl(x6293) { x6287 } // FixConvert(x6287,TRUE,_64,_0)
    val x6289 = withCtrl(x6293) { DramAddress(x6268).name("x6289").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6268)
    val x6291_x6290 = withCtrl(x6293) { OpDef(op=FixAdd, inputs=List(x6288, x6289)).name("x6291_x6290").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6288,x6289)
    // x6291 = SimpleStruct(ArrayBuffer((offset,x6290), (size,Const(512)), (isLoad,Const(true))))
    val x6292_b6861_b6859 = withCtrl(x6293) { WriteMem(b6859, x6291_x6290).name("x6292_b6861_b6859").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6281,x6291,b3213)
    val x6292_b6862_b6860 = withCtrl(x6293) { WriteMem(b6860, Const(512)).name("x6292_b6862_b6860").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6281,x6291,b3213)
    val x6294 = withCtrl(x6302) { FringeDenseLoad(dram=List(x6268), cmdStream=List(b6859, b6860), dataStream=List(x6282)).name("x6294").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6268,x6281,x6282)
    val x6295 = withCtrl(x6302) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6295").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6296 = withCtrl(x6302) { CounterChain(List(x6295)).name("x6296").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6295))
    val x6301 = withCtrl(x6302) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6296).name("x6301").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3213),x6296,Block(Const(())),List(List(b3230)),List(List(b3231)))
    val b3230 = withCtrl(x6301) { CounterIter(x6295, None).name("b3230") } // b3230
    val b3231 = withCtrl(x6301) { Const(true).name("b3231") } // b3231
    val x6297 = withCtrl(x6301) { OpDef(op=BitAnd, inputs=List(b3231, b3213)).name("x6297").srcCtx("UnrollingBase.scala:28:66") } // And(b3231,b3213)
    val x6298_x6298 = withCtrl(x6301) { ReadMem(x6282).name("x6298_x6298").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6282,List(x6297))
    val x6299_x6299 = withCtrl(x6301) { x6298_x6298 } // VectorApply(x6298,0)
    val x6300 = withCtrl(x6301) { StoreBanks(List(List(x6270_d0_b0), List(x6270_d1_b0)), List(b3212, b3230), x6299_x6299).name("x6300").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6270,List(List(b3212, b3230)),List(x6299),List(x6297))
    val x6303 = withCtrl(x6848) { DRAM(dims=List(Const(1), Const(128))).name("x6303").srcCtx("DataGenerator.scala:156:20:k") } // x6303 = DRAMNew(ArrayBuffer(Const(1), Const(128)),Const(0))
    val x6305_d0_b0 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6305_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6305 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x6305_d0_b0) = false
    bufferDepthOf(x6305_d0_b0) = 1
    staticDimsOf(x6305_d0_b0) = List(1, 128)
    val x6313 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6313").srcCtx("LSTMPipe.scala:30:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6314 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6314").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6315 = withCtrl(x6848) { CounterChain(List(x6314)).name("x6315").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6314))
    val x6337 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6315).name("x6337").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6315,Block(Const(())),List(List(b3251)),List(List(b3252)))
    val b3251 = withCtrl(x6337) { CounterIter(x6314, Some(0)).name("b3251") } // b3251
    val b3252 = withCtrl(x6337) { Const(true).name("b3252") } // b3252
    val b6863 = withCtrl(x6337) { StreamOut(field="offset").name("b6863").srcCtx("DataGenerator.scala:44:8") } // x6316 = StreamOutNew(BurstCmdBus)
    isAccum(b6863) = false
    bufferDepthOf(b6863) = 1
    val b6864 = withCtrl(x6337) { StreamOut(field="size").name("b6864").srcCtx("DataGenerator.scala:44:8") } // x6316 = StreamOutNew(BurstCmdBus)
    isAccum(b6864) = false
    bufferDepthOf(b6864) = 1
    val x6317 = withCtrl(x6337) { StreamIn(field="data").name("x6317").srcCtx("DataGenerator.scala:44:8") } // x6317 = StreamInNew(BurstDataBus())
    isAccum(x6317) = false
    bufferDepthOf(x6317) = 1
    val x6328 = withCtrl(x6337) { UnitController(style=SeqPipe, level=InnerControl).name("x6328").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3252),Block(x6327))
    val x6318 = withCtrl(x6328) { b3251 } // FixConvert(b3251,TRUE,_32,_0) (Same Type. No op)
    val x6319 = withCtrl(x6328) { OpDef(op=FixSla, inputs=List(x6318, Const(7))).name("x6319").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6318,Const(7))
    val x6320 = withCtrl(x6328) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6321 = withCtrl(x6328) { OpDef(op=FixAdd, inputs=List(x6319, x6320)).name("x6321").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6319,x6320)
    val x6322 = withCtrl(x6328) { OpDef(op=FixSla, inputs=List(x6321, Const(2))).name("x6322").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6321,Const(2))
    val x6323 = withCtrl(x6328) { x6322 } // FixConvert(x6322,TRUE,_64,_0)
    val x6324 = withCtrl(x6328) { DramAddress(x6303).name("x6324").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6303)
    val x6326_x6325 = withCtrl(x6328) { OpDef(op=FixAdd, inputs=List(x6323, x6324)).name("x6326_x6325").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6323,x6324)
    // x6326 = SimpleStruct(ArrayBuffer((offset,x6325), (size,Const(512)), (isLoad,Const(true))))
    val x6327_b6865_b6863 = withCtrl(x6328) { WriteMem(b6863, x6326_x6325).name("x6327_b6865_b6863").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6316,x6326,b3252)
    val x6327_b6866_b6864 = withCtrl(x6328) { WriteMem(b6864, Const(512)).name("x6327_b6866_b6864").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6316,x6326,b3252)
    val x6329 = withCtrl(x6337) { FringeDenseLoad(dram=List(x6303), cmdStream=List(b6863, b6864), dataStream=List(x6317)).name("x6329").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6303,x6316,x6317)
    val x6330 = withCtrl(x6337) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6330").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6331 = withCtrl(x6337) { CounterChain(List(x6330)).name("x6331").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6330))
    val x6336 = withCtrl(x6337) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6331).name("x6336").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3252),x6331,Block(Const(())),List(List(b3269)),List(List(b3270)))
    val b3269 = withCtrl(x6336) { CounterIter(x6330, None).name("b3269") } // b3269
    val b3270 = withCtrl(x6336) { Const(true).name("b3270") } // b3270
    val x6332 = withCtrl(x6336) { OpDef(op=BitAnd, inputs=List(b3270, b3252)).name("x6332").srcCtx("UnrollingBase.scala:28:66") } // And(b3270,b3252)
    val x6333_x6333 = withCtrl(x6336) { ReadMem(x6317).name("x6333_x6333").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6317,List(x6332))
    val x6334_x6334 = withCtrl(x6336) { x6333_x6333 } // VectorApply(x6333,0)
    val x6335 = withCtrl(x6336) { StoreBanks(List(List(x6305_d0_b0)), List(b3251, b3269), x6334_x6334).name("x6335").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6305,List(List(b3251, b3269)),List(x6334),List(x6332))
    val x6338 = withCtrl(x6848) { DRAM(dims=List(Const(1), Const(128))).name("x6338").srcCtx("DataGenerator.scala:156:20:k") } // x6338 = DRAMNew(ArrayBuffer(Const(1), Const(128)),Const(0))
    val x6340_d0_b0 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6340_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6340 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x6340_d0_b0) = false
    bufferDepthOf(x6340_d0_b0) = 1
    staticDimsOf(x6340_d0_b0) = List(1, 128)
    val x6340_d1_b0 = withCtrl(x6848) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6340_d1_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6340 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x6340_d1_b0) = false
    bufferDepthOf(x6340_d1_b0) = 1
    staticDimsOf(x6340_d1_b0) = List(1, 128)
    val x6348 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6348").srcCtx("LSTMPipe.scala:30:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6349 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6349").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6350 = withCtrl(x6848) { CounterChain(List(x6349)).name("x6350").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6349))
    val x6372 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6350).name("x6372").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6350,Block(Const(())),List(List(b3290)),List(List(b3291)))
    val b3290 = withCtrl(x6372) { CounterIter(x6349, Some(0)).name("b3290") } // b3290
    val b3291 = withCtrl(x6372) { Const(true).name("b3291") } // b3291
    val b6867 = withCtrl(x6372) { StreamOut(field="offset").name("b6867").srcCtx("DataGenerator.scala:44:8") } // x6351 = StreamOutNew(BurstCmdBus)
    isAccum(b6867) = false
    bufferDepthOf(b6867) = 1
    val b6868 = withCtrl(x6372) { StreamOut(field="size").name("b6868").srcCtx("DataGenerator.scala:44:8") } // x6351 = StreamOutNew(BurstCmdBus)
    isAccum(b6868) = false
    bufferDepthOf(b6868) = 1
    val x6352 = withCtrl(x6372) { StreamIn(field="data").name("x6352").srcCtx("DataGenerator.scala:44:8") } // x6352 = StreamInNew(BurstDataBus())
    isAccum(x6352) = false
    bufferDepthOf(x6352) = 1
    val x6363 = withCtrl(x6372) { UnitController(style=SeqPipe, level=InnerControl).name("x6363").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b3291),Block(x6362))
    val x6353 = withCtrl(x6363) { b3290 } // FixConvert(b3290,TRUE,_32,_0) (Same Type. No op)
    val x6354 = withCtrl(x6363) { OpDef(op=FixSla, inputs=List(x6353, Const(7))).name("x6354").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6353,Const(7))
    val x6355 = withCtrl(x6363) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6356 = withCtrl(x6363) { OpDef(op=FixAdd, inputs=List(x6354, x6355)).name("x6356").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6354,x6355)
    val x6357 = withCtrl(x6363) { OpDef(op=FixSla, inputs=List(x6356, Const(2))).name("x6357").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6356,Const(2))
    val x6358 = withCtrl(x6363) { x6357 } // FixConvert(x6357,TRUE,_64,_0)
    val x6359 = withCtrl(x6363) { DramAddress(x6338).name("x6359").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6338)
    val x6361_x6360 = withCtrl(x6363) { OpDef(op=FixAdd, inputs=List(x6358, x6359)).name("x6361_x6360").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6358,x6359)
    // x6361 = SimpleStruct(ArrayBuffer((offset,x6360), (size,Const(512)), (isLoad,Const(true))))
    val x6362_b6869_b6867 = withCtrl(x6363) { WriteMem(b6867, x6361_x6360).name("x6362_b6869_b6867").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6351,x6361,b3291)
    val x6362_b6870_b6868 = withCtrl(x6363) { WriteMem(b6868, Const(512)).name("x6362_b6870_b6868").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6351,x6361,b3291)
    val x6364 = withCtrl(x6372) { FringeDenseLoad(dram=List(x6338), cmdStream=List(b6867, b6868), dataStream=List(x6352)).name("x6364").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6338,x6351,x6352)
    val x6365 = withCtrl(x6372) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6365").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6366 = withCtrl(x6372) { CounterChain(List(x6365)).name("x6366").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6365))
    val x6371 = withCtrl(x6372) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6366).name("x6371").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b3291),x6366,Block(Const(())),List(List(b3308)),List(List(b3309)))
    val b3308 = withCtrl(x6371) { CounterIter(x6365, None).name("b3308") } // b3308
    val b3309 = withCtrl(x6371) { Const(true).name("b3309") } // b3309
    val x6367 = withCtrl(x6371) { OpDef(op=BitAnd, inputs=List(b3309, b3291)).name("x6367").srcCtx("UnrollingBase.scala:28:66") } // And(b3309,b3291)
    val x6368_x6368 = withCtrl(x6371) { ReadMem(x6352).name("x6368_x6368").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6352,List(x6367))
    val x6369_x6369 = withCtrl(x6371) { x6368_x6368 } // VectorApply(x6368,0)
    val x6370 = withCtrl(x6371) { StoreBanks(List(List(x6340_d0_b0), List(x6340_d1_b0)), List(b3290, b3308), x6369_x6369).name("x6370").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6340,List(List(b3290, b3308)),List(x6369),List(x6367))
    val x6373 = withCtrl(x6848) { DRAM(dims=List(Const(1), Const(128), Const(4), Const(128))).name("x6373").srcCtx("DataGenerator.scala:182:20:k") } // x6373 = DRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)),Const(0))
    val x6375_d0_b0 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b0").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b0) = false
    bufferDepthOf(x6375_d0_b0) = 1
    staticDimsOf(x6375_d0_b0) = List(1, 128, 4, 128)
    val x6375_d0_b1 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b1").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b1) = false
    bufferDepthOf(x6375_d0_b1) = 1
    staticDimsOf(x6375_d0_b1) = List(1, 128, 4, 128)
    val x6375_d0_b2 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b2").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b2) = false
    bufferDepthOf(x6375_d0_b2) = 1
    staticDimsOf(x6375_d0_b2) = List(1, 128, 4, 128)
    val x6375_d0_b3 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b3").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b3) = false
    bufferDepthOf(x6375_d0_b3) = 1
    staticDimsOf(x6375_d0_b3) = List(1, 128, 4, 128)
    val x6375_d0_b4 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b4").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b4) = false
    bufferDepthOf(x6375_d0_b4) = 1
    staticDimsOf(x6375_d0_b4) = List(1, 128, 4, 128)
    val x6375_d0_b5 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b5").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b5) = false
    bufferDepthOf(x6375_d0_b5) = 1
    staticDimsOf(x6375_d0_b5) = List(1, 128, 4, 128)
    val x6375_d0_b6 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b6").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b6) = false
    bufferDepthOf(x6375_d0_b6) = 1
    staticDimsOf(x6375_d0_b6) = List(1, 128, 4, 128)
    val x6375_d0_b7 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6375_d0_b7").srcCtx("DataGenerator.scala:76:21:wx") } // x6375 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6375_d0_b7) = false
    bufferDepthOf(x6375_d0_b7) = 1
    staticDimsOf(x6375_d0_b7) = List(1, 128, 4, 128)
    val x6389 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6389").srcCtx("LSTMPipe.scala:30:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6390 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6390").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6391 = withCtrl(x6848) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6391").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6392 = withCtrl(x6848) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6392").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6393 = withCtrl(x6848) { CounterChain(List(x6390,x6391,x6392)).name("x6393").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6390, x6391, x6392))
    val x6425 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6393).name("x6425").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6393,Block(Const(())),List(List(b3337), List(b3338), List(b3339)),List(List(b3340), List(b3341), List(b3342)))
    val b3337 = withCtrl(x6425) { CounterIter(x6390, Some(0)).name("b3337") } // b3337
    val b3340 = withCtrl(x6425) { Const(true).name("b3340") } // b3340
    val b3338 = withCtrl(x6425) { CounterIter(x6391, Some(0)).name("b3338") } // b3338
    val b3341 = withCtrl(x6425) { Const(true).name("b3341") } // b3341
    val b3339 = withCtrl(x6425) { CounterIter(x6392, Some(0)).name("b3339") } // b3339
    val b3342 = withCtrl(x6425) { Const(true).name("b3342") } // b3342
    val b6871 = withCtrl(x6425) { StreamOut(field="offset").name("b6871").srcCtx("DataGenerator.scala:77:8") } // x6394 = StreamOutNew(BurstCmdBus)
    isAccum(b6871) = false
    bufferDepthOf(b6871) = 1
    val b6872 = withCtrl(x6425) { StreamOut(field="size").name("b6872").srcCtx("DataGenerator.scala:77:8") } // x6394 = StreamOutNew(BurstCmdBus)
    isAccum(b6872) = false
    bufferDepthOf(b6872) = 1
    val x6395 = withCtrl(x6425) { StreamIn(field="data").name("x6395").srcCtx("DataGenerator.scala:77:8") } // x6395 = StreamInNew(BurstDataBus())
    isAccum(x6395) = false
    bufferDepthOf(x6395) = 1
    val x6414 = withCtrl(x6425) { UnitController(style=SeqPipe, level=InnerControl).name("x6414").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3340, b3341, b3342),Block(x6413))
    val x6396 = withCtrl(x6414) { b3337 } // FixConvert(b3337,TRUE,_32,_0) (Same Type. No op)
    val x6397 = withCtrl(x6414) { OpDef(op=FixSla, inputs=List(x6396, Const(16))).name("x6397").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6396,Const(16))
    val x6398 = withCtrl(x6414) { b3338 } // FixConvert(b3338,TRUE,_32,_0) (Same Type. No op)
    val x6399 = withCtrl(x6414) { OpDef(op=FixSla, inputs=List(x6398, Const(9))).name("x6399").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6398,Const(9))
    val x6400 = withCtrl(x6414) { b3339 } // FixConvert(b3339,TRUE,_32,_0) (Same Type. No op)
    val x6401 = withCtrl(x6414) { OpDef(op=FixSla, inputs=List(x6400, Const(7))).name("x6401").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6400,Const(7))
    val x6402 = withCtrl(x6414) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6403 = withCtrl(x6414) { OpDef(op=FixAdd, inputs=List(x6397, x6399)).name("x6403").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6397,x6399)
    val x6404 = withCtrl(x6414) { OpDef(op=FixAdd, inputs=List(x6401, x6402)).name("x6404").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6401,x6402)
    val x6405 = withCtrl(x6414) { OpDef(op=FixAdd, inputs=List(x6403, x6404)).name("x6405").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6403,x6404)
    val x6406 = withCtrl(x6414) { OpDef(op=FixSla, inputs=List(x6405, Const(2))).name("x6406").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6405,Const(2))
    val x6407 = withCtrl(x6414) { x6406 } // FixConvert(x6406,TRUE,_64,_0)
    val x6408 = withCtrl(x6414) { DramAddress(x6373).name("x6408").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6373)
    val x6410_x6409 = withCtrl(x6414) { OpDef(op=FixAdd, inputs=List(x6407, x6408)).name("x6410_x6409").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6407,x6408)
    // x6410 = SimpleStruct(ArrayBuffer((offset,x6409), (size,Const(512)), (isLoad,Const(true))))
    val x6411 = withCtrl(x6414) { OpDef(op=BitAnd, inputs=List(b3340, b3341)).name("x6411").srcCtx("UnrollingBase.scala:28:66") } // And(b3340,b3341)
    val x6412 = withCtrl(x6414) { OpDef(op=BitAnd, inputs=List(x6411, b3342)).name("x6412").srcCtx("UnrollingBase.scala:28:66") } // And(x6411,b3342)
    val x6413_b6873_b6871 = withCtrl(x6414) { WriteMem(b6871, x6410_x6409).name("x6413_b6873_b6871").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6394,x6410,x6412)
    val x6413_b6874_b6872 = withCtrl(x6414) { WriteMem(b6872, Const(512)).name("x6413_b6874_b6872").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6394,x6410,x6412)
    val x6415 = withCtrl(x6425) { FringeDenseLoad(dram=List(x6373), cmdStream=List(b6871, b6872), dataStream=List(x6395)).name("x6415").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6373,x6394,x6395)
    val x6416 = withCtrl(x6425) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6416").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6417 = withCtrl(x6425) { CounterChain(List(x6416)).name("x6417").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6416))
    val x6424 = withCtrl(x6425) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6417).name("x6424").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3340, b3341, b3342),x6417,Block(Const(())),List(List(b3367)),List(List(b3368)))
    val b3367 = withCtrl(x6424) { CounterIter(x6416, None).name("b3367") } // b3367
    val b3368 = withCtrl(x6424) { Const(true).name("b3368") } // b3368
    val x6418 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(b3368, b3340)).name("x6418").srcCtx("UnrollingBase.scala:28:66") } // And(b3368,b3340)
    val x6419 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(b3341, b3342)).name("x6419").srcCtx("UnrollingBase.scala:28:66") } // And(b3341,b3342)
    val x6420 = withCtrl(x6424) { OpDef(op=BitAnd, inputs=List(x6418, x6419)).name("x6420").srcCtx("UnrollingBase.scala:28:66") } // And(x6418,x6419)
    val x6421_x6421 = withCtrl(x6424) { ReadMem(x6395).name("x6421_x6421").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6395,List(x6420))
    val x6422_x6422 = withCtrl(x6424) { x6421_x6421 } // VectorApply(x6421,0)
    val x6423 = withCtrl(x6424) { StoreBanks(List(List(x6375_d0_b0, x6375_d0_b1, x6375_d0_b2, x6375_d0_b3, x6375_d0_b4, x6375_d0_b5, x6375_d0_b6, x6375_d0_b7)), List(b3337, b3338, b3339, b3367), x6422_x6422).name("x6423").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6375,List(List(b3337, b3338, b3339, b3367)),List(x6422),List(x6420))
    val x6426 = withCtrl(x6848) { DRAM(dims=List(Const(1), Const(128), Const(4), Const(128))).name("x6426").srcCtx("DataGenerator.scala:182:20:k") } // x6426 = DRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)),Const(0))
    val x6428_d0_b0 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b0").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b0) = false
    bufferDepthOf(x6428_d0_b0) = 1
    staticDimsOf(x6428_d0_b0) = List(1, 128, 4, 128)
    val x6428_d0_b1 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b1").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b1) = false
    bufferDepthOf(x6428_d0_b1) = 1
    staticDimsOf(x6428_d0_b1) = List(1, 128, 4, 128)
    val x6428_d0_b2 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b2").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b2) = false
    bufferDepthOf(x6428_d0_b2) = 1
    staticDimsOf(x6428_d0_b2) = List(1, 128, 4, 128)
    val x6428_d0_b3 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b3").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b3) = false
    bufferDepthOf(x6428_d0_b3) = 1
    staticDimsOf(x6428_d0_b3) = List(1, 128, 4, 128)
    val x6428_d0_b4 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b4").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b4) = false
    bufferDepthOf(x6428_d0_b4) = 1
    staticDimsOf(x6428_d0_b4) = List(1, 128, 4, 128)
    val x6428_d0_b5 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b5").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b5) = false
    bufferDepthOf(x6428_d0_b5) = 1
    staticDimsOf(x6428_d0_b5) = List(1, 128, 4, 128)
    val x6428_d0_b6 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b6").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b6) = false
    bufferDepthOf(x6428_d0_b6) = 1
    staticDimsOf(x6428_d0_b6) = List(1, 128, 4, 128)
    val x6428_d0_b7 = withCtrl(x6848) { SRAM(size=8192, banking=Strided(banks=16, stride=1)).name("x6428_d0_b7").srcCtx("DataGenerator.scala:76:21:wh") } // x6428 = SRAMNew(ArrayBuffer(Const(1), Const(128), Const(4), Const(128)))
    isAccum(x6428_d0_b7) = false
    bufferDepthOf(x6428_d0_b7) = 1
    staticDimsOf(x6428_d0_b7) = List(1, 128, 4, 128)
    val x6442 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6442").srcCtx("LSTMPipe.scala:30:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6443 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6443").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6444 = withCtrl(x6848) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6444").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6445 = withCtrl(x6848) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6445").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6446 = withCtrl(x6848) { CounterChain(List(x6443,x6444,x6445)).name("x6446").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6443, x6444, x6445))
    val x6478 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6446).name("x6478").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6446,Block(Const(())),List(List(b3398), List(b3399), List(b3400)),List(List(b3401), List(b3402), List(b3403)))
    val b3398 = withCtrl(x6478) { CounterIter(x6443, Some(0)).name("b3398") } // b3398
    val b3401 = withCtrl(x6478) { Const(true).name("b3401") } // b3401
    val b3399 = withCtrl(x6478) { CounterIter(x6444, Some(0)).name("b3399") } // b3399
    val b3402 = withCtrl(x6478) { Const(true).name("b3402") } // b3402
    val b3400 = withCtrl(x6478) { CounterIter(x6445, Some(0)).name("b3400") } // b3400
    val b3403 = withCtrl(x6478) { Const(true).name("b3403") } // b3403
    val b6875 = withCtrl(x6478) { StreamOut(field="offset").name("b6875").srcCtx("DataGenerator.scala:77:8") } // x6447 = StreamOutNew(BurstCmdBus)
    isAccum(b6875) = false
    bufferDepthOf(b6875) = 1
    val b6876 = withCtrl(x6478) { StreamOut(field="size").name("b6876").srcCtx("DataGenerator.scala:77:8") } // x6447 = StreamOutNew(BurstCmdBus)
    isAccum(b6876) = false
    bufferDepthOf(b6876) = 1
    val x6448 = withCtrl(x6478) { StreamIn(field="data").name("x6448").srcCtx("DataGenerator.scala:77:8") } // x6448 = StreamInNew(BurstDataBus())
    isAccum(x6448) = false
    bufferDepthOf(x6448) = 1
    val x6467 = withCtrl(x6478) { UnitController(style=SeqPipe, level=InnerControl).name("x6467").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b3401, b3402, b3403),Block(x6466))
    val x6449 = withCtrl(x6467) { b3398 } // FixConvert(b3398,TRUE,_32,_0) (Same Type. No op)
    val x6450 = withCtrl(x6467) { OpDef(op=FixSla, inputs=List(x6449, Const(16))).name("x6450").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6449,Const(16))
    val x6451 = withCtrl(x6467) { b3399 } // FixConvert(b3399,TRUE,_32,_0) (Same Type. No op)
    val x6452 = withCtrl(x6467) { OpDef(op=FixSla, inputs=List(x6451, Const(9))).name("x6452").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6451,Const(9))
    val x6453 = withCtrl(x6467) { b3400 } // FixConvert(b3400,TRUE,_32,_0) (Same Type. No op)
    val x6454 = withCtrl(x6467) { OpDef(op=FixSla, inputs=List(x6453, Const(7))).name("x6454").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6453,Const(7))
    val x6455 = withCtrl(x6467) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6456 = withCtrl(x6467) { OpDef(op=FixAdd, inputs=List(x6450, x6452)).name("x6456").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6450,x6452)
    val x6457 = withCtrl(x6467) { OpDef(op=FixAdd, inputs=List(x6454, x6455)).name("x6457").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6454,x6455)
    val x6458 = withCtrl(x6467) { OpDef(op=FixAdd, inputs=List(x6456, x6457)).name("x6458").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6456,x6457)
    val x6459 = withCtrl(x6467) { OpDef(op=FixSla, inputs=List(x6458, Const(2))).name("x6459").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6458,Const(2))
    val x6460 = withCtrl(x6467) { x6459 } // FixConvert(x6459,TRUE,_64,_0)
    val x6461 = withCtrl(x6467) { DramAddress(x6426).name("x6461").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6426)
    val x6463_x6462 = withCtrl(x6467) { OpDef(op=FixAdd, inputs=List(x6460, x6461)).name("x6463_x6462").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6460,x6461)
    // x6463 = SimpleStruct(ArrayBuffer((offset,x6462), (size,Const(512)), (isLoad,Const(true))))
    val x6464 = withCtrl(x6467) { OpDef(op=BitAnd, inputs=List(b3401, b3402)).name("x6464").srcCtx("UnrollingBase.scala:28:66") } // And(b3401,b3402)
    val x6465 = withCtrl(x6467) { OpDef(op=BitAnd, inputs=List(x6464, b3403)).name("x6465").srcCtx("UnrollingBase.scala:28:66") } // And(x6464,b3403)
    val x6466_b6877_b6875 = withCtrl(x6467) { WriteMem(b6875, x6463_x6462).name("x6466_b6877_b6875").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6447,x6463,x6465)
    val x6466_b6878_b6876 = withCtrl(x6467) { WriteMem(b6876, Const(512)).name("x6466_b6878_b6876").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6447,x6463,x6465)
    val x6468 = withCtrl(x6478) { FringeDenseLoad(dram=List(x6426), cmdStream=List(b6875, b6876), dataStream=List(x6448)).name("x6468").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6426,x6447,x6448)
    val x6469 = withCtrl(x6478) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6469").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6470 = withCtrl(x6478) { CounterChain(List(x6469)).name("x6470").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6469))
    val x6477 = withCtrl(x6478) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6470).name("x6477").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b3401, b3402, b3403),x6470,Block(Const(())),List(List(b3428)),List(List(b3429)))
    val b3428 = withCtrl(x6477) { CounterIter(x6469, None).name("b3428") } // b3428
    val b3429 = withCtrl(x6477) { Const(true).name("b3429") } // b3429
    val x6471 = withCtrl(x6477) { OpDef(op=BitAnd, inputs=List(b3429, b3401)).name("x6471").srcCtx("UnrollingBase.scala:28:66") } // And(b3429,b3401)
    val x6472 = withCtrl(x6477) { OpDef(op=BitAnd, inputs=List(b3402, b3403)).name("x6472").srcCtx("UnrollingBase.scala:28:66") } // And(b3402,b3403)
    val x6473 = withCtrl(x6477) { OpDef(op=BitAnd, inputs=List(x6471, x6472)).name("x6473").srcCtx("UnrollingBase.scala:28:66") } // And(x6471,x6472)
    val x6474_x6474 = withCtrl(x6477) { ReadMem(x6448).name("x6474_x6474").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6448,List(x6473))
    val x6475_x6475 = withCtrl(x6477) { x6474_x6474 } // VectorApply(x6474,0)
    val x6476 = withCtrl(x6477) { StoreBanks(List(List(x6428_d0_b0, x6428_d0_b1, x6428_d0_b2, x6428_d0_b3, x6428_d0_b4, x6428_d0_b5, x6428_d0_b6, x6428_d0_b7)), List(b3398, b3399, b3400, b3428), x6475_x6475).name("x6476").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6428,List(List(b3398, b3399, b3400, b3428)),List(x6475),List(x6473))
    val x6479 = withCtrl(x6848) { DRAM(dims=List(Const(1), Const(4), Const(128))).name("x6479").srcCtx("DataGenerator.scala:168:20:k") } // x6479 = DRAMNew(ArrayBuffer(Const(1), Const(4), Const(128)),Const(0))
    val x6481_d0_b0 = withCtrl(x6848) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6481_d0_b0").srcCtx("DataGenerator.scala:59:21:b") } // x6481 = SRAMNew(ArrayBuffer(Const(1), Const(4), Const(128)))
    isAccum(x6481_d0_b0) = false
    bufferDepthOf(x6481_d0_b0) = 1
    staticDimsOf(x6481_d0_b0) = List(1, 4, 128)
    val x6492 = withCtrl(x6848) { UnitController(style=SeqPipe, level=InnerControl).name("x6492").srcCtx("LSTMPipe.scala:30:11") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x6493 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6493").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6494 = withCtrl(x6848) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6494").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6495 = withCtrl(x6848) { CounterChain(List(x6493,x6494)).name("x6495").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6493, x6494))
    val x6522 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6495).name("x6522").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(Const(true)),x6495,Block(Const(())),List(List(b3455), List(b3456)),List(List(b3457), List(b3458)))
    val b3455 = withCtrl(x6522) { CounterIter(x6493, Some(0)).name("b3455") } // b3455
    val b3457 = withCtrl(x6522) { Const(true).name("b3457") } // b3457
    val b3456 = withCtrl(x6522) { CounterIter(x6494, Some(0)).name("b3456") } // b3456
    val b3458 = withCtrl(x6522) { Const(true).name("b3458") } // b3458
    val b6879 = withCtrl(x6522) { StreamOut(field="offset").name("b6879").srcCtx("DataGenerator.scala:60:8") } // x6496 = StreamOutNew(BurstCmdBus)
    isAccum(b6879) = false
    bufferDepthOf(b6879) = 1
    val b6880 = withCtrl(x6522) { StreamOut(field="size").name("b6880").srcCtx("DataGenerator.scala:60:8") } // x6496 = StreamOutNew(BurstCmdBus)
    isAccum(b6880) = false
    bufferDepthOf(b6880) = 1
    val x6497 = withCtrl(x6522) { StreamIn(field="data").name("x6497").srcCtx("DataGenerator.scala:60:8") } // x6497 = StreamInNew(BurstDataBus())
    isAccum(x6497) = false
    bufferDepthOf(x6497) = 1
    val x6512 = withCtrl(x6522) { UnitController(style=SeqPipe, level=InnerControl).name("x6512").srcCtx("DataGenerator.scala:60:8") } // UnitPipe(List(b3457, b3458),Block(x6511))
    val x6498 = withCtrl(x6512) { b3455 } // FixConvert(b3455,TRUE,_32,_0) (Same Type. No op)
    val x6499 = withCtrl(x6512) { OpDef(op=FixSla, inputs=List(x6498, Const(9))).name("x6499").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6498,Const(9))
    val x6500 = withCtrl(x6512) { b3456 } // FixConvert(b3456,TRUE,_32,_0) (Same Type. No op)
    val x6501 = withCtrl(x6512) { OpDef(op=FixSla, inputs=List(x6500, Const(7))).name("x6501").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6500,Const(7))
    val x6502 = withCtrl(x6512) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6503 = withCtrl(x6512) { OpDef(op=FixAdd, inputs=List(x6499, x6501)).name("x6503").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6499,x6501)
    val x6504 = withCtrl(x6512) { OpDef(op=FixAdd, inputs=List(x6503, x6502)).name("x6504").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6503,x6502)
    val x6505 = withCtrl(x6512) { OpDef(op=FixSla, inputs=List(x6504, Const(2))).name("x6505").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6504,Const(2))
    val x6506 = withCtrl(x6512) { x6505 } // FixConvert(x6505,TRUE,_64,_0)
    val x6507 = withCtrl(x6512) { DramAddress(x6479).name("x6507").srcCtx("DataGenerator.scala:60:8") } // GetDRAMAddress(x6479)
    val x6509_x6508 = withCtrl(x6512) { OpDef(op=FixAdd, inputs=List(x6506, x6507)).name("x6509_x6508").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6506,x6507)
    // x6509 = SimpleStruct(ArrayBuffer((offset,x6508), (size,Const(512)), (isLoad,Const(true))))
    val x6510 = withCtrl(x6512) { OpDef(op=BitAnd, inputs=List(b3457, b3458)).name("x6510").srcCtx("UnrollingBase.scala:28:66") } // And(b3457,b3458)
    val x6511_b6881_b6879 = withCtrl(x6512) { WriteMem(b6879, x6509_x6508).name("x6511_b6881_b6879").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6496,x6509,x6510)
    val x6511_b6882_b6880 = withCtrl(x6512) { WriteMem(b6880, Const(512)).name("x6511_b6882_b6880").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6496,x6509,x6510)
    val x6513 = withCtrl(x6522) { FringeDenseLoad(dram=List(x6479), cmdStream=List(b6879, b6880), dataStream=List(x6497)).name("x6513").srcCtx("DataGenerator.scala:60:8") } // FringeDenseLoad(x6479,x6496,x6497)
    val x6514 = withCtrl(x6522) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6514").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6515 = withCtrl(x6522) { CounterChain(List(x6514)).name("x6515").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6514))
    val x6521 = withCtrl(x6522) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6515).name("x6521").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(b3457, b3458),x6515,Block(Const(())),List(List(b3479)),List(List(b3480)))
    val b3479 = withCtrl(x6521) { CounterIter(x6514, None).name("b3479") } // b3479
    val b3480 = withCtrl(x6521) { Const(true).name("b3480") } // b3480
    val x6516 = withCtrl(x6521) { OpDef(op=BitAnd, inputs=List(b3480, b3457)).name("x6516").srcCtx("UnrollingBase.scala:28:66") } // And(b3480,b3457)
    val x6517 = withCtrl(x6521) { OpDef(op=BitAnd, inputs=List(x6516, b3458)).name("x6517").srcCtx("UnrollingBase.scala:28:66") } // And(x6516,b3458)
    val x6518_x6518 = withCtrl(x6521) { ReadMem(x6497).name("x6518_x6518").srcCtx("DataGenerator.scala:60:8") } // ParStreamRead(x6497,List(x6517))
    val x6519_x6519 = withCtrl(x6521) { x6518_x6518 } // VectorApply(x6518,0)
    val x6520 = withCtrl(x6521) { StoreBanks(List(List(x6481_d0_b0)), List(b3455, b3456, b3479), x6519_x6519).name("x6520").srcCtx("DataGenerator.scala:60:8") } // ParSRAMStore(x6481,List(List(b3455, b3456, b3479)),List(x6519),List(x6517))
    val x6523 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6523").srcCtx("LSTMPipe.scala:38:28") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6524 = withCtrl(x6848) { CounterChain(List(x6523)).name("x6524").srcCtx("LSTMPipe.scala:38:41") } // CounterChainNew(List(x6523))
    val x6819 = withCtrl(x6848) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6524).name("x6819").srcCtx("LSTMPipe.scala:38:41") } // UnrolledForeach(List(Const(true)),x6524,Block(Const(())),List(List(b3490)),List(List(b3491)))
    val b3490 = withCtrl(x6819) { CounterIter(x6523, Some(0)).name("b3490") } // b3490
    val b3491 = withCtrl(x6819) { Const(true).name("b3491") } // b3491
    val x6525 = withCtrl(x6819) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6525").srcCtx("LSTMPipe.scala:39:31") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6526 = withCtrl(x6819) { CounterChain(List(x6525)).name("x6526").srcCtx("LSTMPipe.scala:39:45") } // CounterChainNew(List(x6525))
    val x6818 = withCtrl(x6819) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6526).name("x6818").srcCtx("LSTMPipe.scala:39:45") } // UnrolledForeach(List(b3491),x6526,Block(Const(())),List(List(b3494)),List(List(b3495)))
    val b3494 = withCtrl(x6818) { CounterIter(x6525, Some(0)).name("b3494") } // b3494
    val b3495 = withCtrl(x6818) { Const(true).name("b3495") } // b3495
    val x6817 = withCtrl(x6818) { UnitController(style=SeqPipe, level=OuterControl).name("x6817").srcCtx("LSTMPipe.scala:41:16") } // UnitPipe(List(b3495, b3491),Block(Const(())))
    val x6527_d0_b0 = withCtrl(x6817) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6527_d0_b0").srcCtx("LSTMPipe.scala:42:34:foldMem") } // x6527 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6527_d0_b0) = false
    bufferDepthOf(x6527_d0_b0) = 1
    staticDimsOf(x6527_d0_b0) = List(4, 128)
    val x6527_d1_b0 = withCtrl(x6817) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6527_d1_b0").srcCtx("LSTMPipe.scala:42:34:foldMem") } // x6527 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6527_d1_b0) = true
    bufferDepthOf(x6527_d1_b0) = 1
    staticDimsOf(x6527_d1_b0) = List(4, 128)
    val x6528 = withCtrl(x6817) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6528").srcCtx("LSTMPipe.scala:43:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6529 = withCtrl(x6817) { CounterChain(List(x6528)).name("x6529").srcCtx("LSTMPipe.scala:43:35") } // CounterChainNew(List(x6528))
    val x6539 = withCtrl(x6817) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6529).name("x6539").srcCtx("LSTMPipe.scala:43:35") } // UnrolledForeach(List(b3495, b3491),x6529,Block(Const(())),List(List(b3499)),List(List(b3500)))
    val b3499 = withCtrl(x6539) { CounterIter(x6528, Some(0)).name("b3499") } // b3499
    val b3500 = withCtrl(x6539) { Const(true).name("b3500") } // b3500
    val x6530 = withCtrl(x6539) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6530").srcCtx("LSTMPipe.scala:44:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6531 = withCtrl(x6539) { CounterChain(List(x6530)).name("x6531").srcCtx("LSTMPipe.scala:44:56") } // CounterChainNew(List(x6530))
    val x6538 = withCtrl(x6539) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6531).name("x6538").srcCtx("LSTMPipe.scala:44:56") } // UnrolledForeach(List(b3500, b3495, b3491),x6531,Block(Const(())),List(List(b3503)),List(List(b3504)))
    val b3503 = withCtrl(x6538) { CounterIter(x6530, None).name("b3503") } // b3503
    val b3504 = withCtrl(x6538) { Const(true).name("b3504") } // b3504
    val x6532 = withCtrl(x6538) { OpDef(op=BitAnd, inputs=List(b3504, b3500)).name("x6532").srcCtx("UnrollingBase.scala:28:66") } // And(b3504,b3500)
    val x6533 = withCtrl(x6538) { OpDef(op=BitAnd, inputs=List(b3495, b3491)).name("x6533").srcCtx("UnrollingBase.scala:28:66") } // And(b3495,b3491)
    val x6534 = withCtrl(x6538) { OpDef(op=BitAnd, inputs=List(x6532, x6533)).name("x6534").srcCtx("UnrollingBase.scala:28:66") } // And(x6532,x6533)
    val x6535 = withCtrl(x6538) { LoadBanks(List(x6481_d0_b0), List(b3494, b3499, b3503)).name("x6535").srcCtx("LSTMPipe.scala:45:48") } // ParSRAMLoad(x6481,List(List(b3494, b3499, b3503)),List(x6534))
    val x6536 = withCtrl(x6538) { x6535 } // VectorApply(x6535,0)
    val x6537 = withCtrl(x6538) { StoreBanks(List(List(x6527_d0_b0), List(x6527_d1_b0)), List(b3499, b3503), x6536).name("x6537").srcCtx("LSTMPipe.scala:45:45") } // ParSRAMStore(x6527,List(List(b3499, b3503)),List(x6536),List(x6534))
    val x6540 = withCtrl(x6817) { Counter(min=Const(0), max=Const(128), step=Const(1), par=2).name("x6540").srcCtx("LSTMPipe.scala:49:59") } // CounterNew(Const(0),Const(128),Const(1),Const(2))
    val x6541 = withCtrl(x6817) { CounterChain(List(x6540)).name("x6541").srcCtx("LSTMPipe.scala:63:14") } // CounterChainNew(List(x6540))
    val x6733 = withCtrl(x6817) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6541).name("x6733").srcCtx("LSTMPipe.scala:63:14") } // UnrolledReduce(List(b3495, b3491),x6541,x6527,Block((x6527) => Const(())),List(List(b3518, b3519)),List(List(b3520, b3521)))
    val b3518 = withCtrl(x6733) { CounterIter(x6540, Some(0)).name("b3518") } // b3518
    val b3520 = withCtrl(x6733) { Const(true).name("b3520") } // b3520
    val b3519 = withCtrl(x6733) { CounterIter(x6540, Some(1)).name("b3519") } // b3519
    val b3521 = withCtrl(x6733) { Const(true).name("b3521") } // b3521
    val x6542_d0_b0 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6542_d0_b0").srcCtx("LSTMPipe.scala:51:31:re") } // x6542 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6542_d0_b0) = false
    bufferDepthOf(x6542_d0_b0) = 2
    staticDimsOf(x6542_d0_b0) = List(4, 128)
    val x6542_d0_b1 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6542_d0_b1").srcCtx("LSTMPipe.scala:51:31:re") } // x6542 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6542_d0_b1) = false
    bufferDepthOf(x6542_d0_b1) = 2
    staticDimsOf(x6542_d0_b1) = List(4, 128)
    val x6542_d0_b2 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6542_d0_b2").srcCtx("LSTMPipe.scala:51:31:re") } // x6542 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6542_d0_b2) = false
    bufferDepthOf(x6542_d0_b2) = 2
    staticDimsOf(x6542_d0_b2) = List(4, 128)
    val x6542_d0_b3 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6542_d0_b3").srcCtx("LSTMPipe.scala:51:31:re") } // x6542 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6542_d0_b3) = false
    bufferDepthOf(x6542_d0_b3) = 2
    staticDimsOf(x6542_d0_b3) = List(4, 128)
    val x6543_d0_b0 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6543_d0_b0").srcCtx("LSTMPipe.scala:51:31:re") } // x6543 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6543_d0_b0) = false
    bufferDepthOf(x6543_d0_b0) = 2
    staticDimsOf(x6543_d0_b0) = List(4, 128)
    val x6543_d0_b1 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6543_d0_b1").srcCtx("LSTMPipe.scala:51:31:re") } // x6543 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6543_d0_b1) = false
    bufferDepthOf(x6543_d0_b1) = 2
    staticDimsOf(x6543_d0_b1) = List(4, 128)
    val x6543_d0_b2 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6543_d0_b2").srcCtx("LSTMPipe.scala:51:31:re") } // x6543 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6543_d0_b2) = false
    bufferDepthOf(x6543_d0_b2) = 2
    staticDimsOf(x6543_d0_b2) = List(4, 128)
    val x6543_d0_b3 = withCtrl(x6733) { SRAM(size=128, banking=Strided(banks=16, stride=1)).name("x6543_d0_b3").srcCtx("LSTMPipe.scala:51:31:re") } // x6543 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6543_d0_b3) = false
    bufferDepthOf(x6543_d0_b3) = 2
    staticDimsOf(x6543_d0_b3) = List(4, 128)
    val x6544_d0 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6544_d0").srcCtx("LSTMPipe.scala:63:14") } // x6544 = RegNew(Const(0))
    isAccum(x6544_d0) = false
    bufferDepthOf(x6544_d0) = 2
    val x6544_d1 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6544_d1").srcCtx("LSTMPipe.scala:63:14") } // x6544 = RegNew(Const(0))
    isAccum(x6544_d1) = false
    bufferDepthOf(x6544_d1) = 2
    val x6544_d2 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6544_d2").srcCtx("LSTMPipe.scala:63:14") } // x6544 = RegNew(Const(0))
    isAccum(x6544_d2) = false
    bufferDepthOf(x6544_d2) = 2
    val x6544_d3 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6544_d3").srcCtx("LSTMPipe.scala:63:14") } // x6544 = RegNew(Const(0))
    isAccum(x6544_d3) = false
    bufferDepthOf(x6544_d3) = 2
    val x6545_d0 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6545_d0").srcCtx("LSTMPipe.scala:63:14") } // x6545 = RegNew(Const(0))
    isAccum(x6545_d0) = false
    bufferDepthOf(x6545_d0) = 2
    val x6545_d1 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6545_d1").srcCtx("LSTMPipe.scala:63:14") } // x6545 = RegNew(Const(0))
    isAccum(x6545_d1) = false
    bufferDepthOf(x6545_d1) = 2
    val x6545_d2 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6545_d2").srcCtx("LSTMPipe.scala:63:14") } // x6545 = RegNew(Const(0))
    isAccum(x6545_d2) = false
    bufferDepthOf(x6545_d2) = 2
    val x6545_d3 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6545_d3").srcCtx("LSTMPipe.scala:63:14") } // x6545 = RegNew(Const(0))
    isAccum(x6545_d3) = false
    bufferDepthOf(x6545_d3) = 2
    val x6546_d0 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6546_d0").srcCtx("LSTMPipe.scala:63:14") } // x6546 = RegNew(Const(0))
    isAccum(x6546_d0) = false
    bufferDepthOf(x6546_d0) = 2
    val x6546_d1 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6546_d1").srcCtx("LSTMPipe.scala:63:14") } // x6546 = RegNew(Const(0))
    isAccum(x6546_d1) = false
    bufferDepthOf(x6546_d1) = 2
    val x6546_d2 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6546_d2").srcCtx("LSTMPipe.scala:63:14") } // x6546 = RegNew(Const(0))
    isAccum(x6546_d2) = false
    bufferDepthOf(x6546_d2) = 2
    val x6546_d3 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6546_d3").srcCtx("LSTMPipe.scala:63:14") } // x6546 = RegNew(Const(0))
    isAccum(x6546_d3) = false
    bufferDepthOf(x6546_d3) = 2
    val x6547_d0 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6547_d0").srcCtx("LSTMPipe.scala:63:14") } // x6547 = RegNew(Const(0))
    isAccum(x6547_d0) = false
    bufferDepthOf(x6547_d0) = 2
    val x6547_d1 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6547_d1").srcCtx("LSTMPipe.scala:63:14") } // x6547 = RegNew(Const(0))
    isAccum(x6547_d1) = false
    bufferDepthOf(x6547_d1) = 2
    val x6547_d2 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6547_d2").srcCtx("LSTMPipe.scala:63:14") } // x6547 = RegNew(Const(0))
    isAccum(x6547_d2) = false
    bufferDepthOf(x6547_d2) = 2
    val x6547_d3 = withCtrl(x6733) { Reg(init=Some(0.0)).name("x6547_d3").srcCtx("LSTMPipe.scala:63:14") } // x6547 = RegNew(Const(0))
    isAccum(x6547_d3) = false
    bufferDepthOf(x6547_d3) = 2
    val x6562 = withCtrl(x6733) { UnitController(style=ForkJoin, level=OuterControl).name("x6562").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3495, b3491),Block(Const(())))
    val x6554 = withCtrl(x6562) { UnitController(style=SeqPipe, level=InnerControl).name("x6554").srcCtx("LSTMPipe.scala:63:14") } // UnitPipe(List(b3520, b3495, b3491),Block(Const(())))
    val x6548 = withCtrl(x6554) { OpDef(op=BitAnd, inputs=List(b3520, b3495)).name("x6548").srcCtx("UnrollingBase.scala:28:66") } // And(b3520,b3495)
    val x6549 = withCtrl(x6554) { OpDef(op=BitAnd, inputs=List(x6548, b3491)).name("x6549").srcCtx("UnrollingBase.scala:28:66") } // And(x6548,b3491)
    val x6550 = withCtrl(x6554) { LoadBanks(List(x6270_d1_b0), List(b3490, b3518)).name("x6550").srcCtx("LSTMPipe.scala:52:27:xEle") } // SRAMLoad(x6270,ArrayBuffer(Const(1), Const(128)),List(b3490, b3518),Const(0),x6549)
    val x6551 = withCtrl(x6554) { LoadBanks(List(x6340_d1_b0), List(b3494, b3518)).name("x6551").srcCtx("LSTMPipe.scala:53:27:hEle") } // SRAMLoad(x6340,ArrayBuffer(Const(1), Const(128)),List(b3494, b3518),Const(0),x6549)
    val x6552_x6544_d0 = withCtrl(x6554) { WriteMem(x6544_d0, x6550).name("x6552_x6544_d0").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6544,x6550,x6549)
    val x6552_x6544_d1 = withCtrl(x6554) { WriteMem(x6544_d1, x6550).name("x6552_x6544_d1").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6544,x6550,x6549)
    val x6552_x6544_d2 = withCtrl(x6554) { WriteMem(x6544_d2, x6550).name("x6552_x6544_d2").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6544,x6550,x6549)
    val x6552_x6544_d3 = withCtrl(x6554) { WriteMem(x6544_d3, x6550).name("x6552_x6544_d3").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6544,x6550,x6549)
    val x6553_x6546_d0 = withCtrl(x6554) { WriteMem(x6546_d0, x6551).name("x6553_x6546_d0").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6546,x6551,x6549)
    val x6553_x6546_d1 = withCtrl(x6554) { WriteMem(x6546_d1, x6551).name("x6553_x6546_d1").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6546,x6551,x6549)
    val x6553_x6546_d2 = withCtrl(x6554) { WriteMem(x6546_d2, x6551).name("x6553_x6546_d2").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6546,x6551,x6549)
    val x6553_x6546_d3 = withCtrl(x6554) { WriteMem(x6546_d3, x6551).name("x6553_x6546_d3").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6546,x6551,x6549)
    val x6561 = withCtrl(x6562) { UnitController(style=SeqPipe, level=InnerControl).name("x6561").srcCtx("LSTMPipe.scala:63:14") } // UnitPipe(List(b3521, b3495, b3491),Block(Const(())))
    val x6555 = withCtrl(x6561) { OpDef(op=BitAnd, inputs=List(b3521, b3495)).name("x6555").srcCtx("UnrollingBase.scala:28:66") } // And(b3521,b3495)
    val x6556 = withCtrl(x6561) { OpDef(op=BitAnd, inputs=List(x6555, b3491)).name("x6556").srcCtx("UnrollingBase.scala:28:66") } // And(x6555,b3491)
    val x6557 = withCtrl(x6561) { LoadBanks(List(x6270_d1_b0), List(b3490, b3519)).name("x6557").srcCtx("LSTMPipe.scala:52:27:xEle") } // SRAMLoad(x6270,ArrayBuffer(Const(1), Const(128)),List(b3490, b3519),Const(0),x6556)
    val x6558 = withCtrl(x6561) { LoadBanks(List(x6340_d1_b0), List(b3494, b3519)).name("x6558").srcCtx("LSTMPipe.scala:53:27:hEle") } // SRAMLoad(x6340,ArrayBuffer(Const(1), Const(128)),List(b3494, b3519),Const(0),x6556)
    val x6559_x6545_d0 = withCtrl(x6561) { WriteMem(x6545_d0, x6557).name("x6559_x6545_d0").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6545,x6557,x6556)
    val x6559_x6545_d1 = withCtrl(x6561) { WriteMem(x6545_d1, x6557).name("x6559_x6545_d1").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6545,x6557,x6556)
    def split1 = {
    val x6559_x6545_d2 = withCtrl(x6561) { WriteMem(x6545_d2, x6557).name("x6559_x6545_d2").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6545,x6557,x6556)
    val x6559_x6545_d3 = withCtrl(x6561) { WriteMem(x6545_d3, x6557).name("x6559_x6545_d3").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6545,x6557,x6556)
    val x6560_x6547_d0 = withCtrl(x6561) { WriteMem(x6547_d0, x6558).name("x6560_x6547_d0").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6547,x6558,x6556)
    val x6560_x6547_d1 = withCtrl(x6561) { WriteMem(x6547_d1, x6558).name("x6560_x6547_d1").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6547,x6558,x6556)
    val x6560_x6547_d2 = withCtrl(x6561) { WriteMem(x6547_d2, x6558).name("x6560_x6547_d2").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6547,x6558,x6556)
    val x6560_x6547_d3 = withCtrl(x6561) { WriteMem(x6547_d3, x6558).name("x6560_x6547_d3").srcCtx("LSTMPipe.scala:63:14") } // RegWrite(x6547,x6558,x6556)
    val x6707 = withCtrl(x6733) { UnitController(style=ForkJoin, level=OuterControl).name("x6707").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3495, b3491),Block(Const(())))
    val x6563 = withCtrl(x6707) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x6563").srcCtx("LSTMPipe.scala:54:36") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x6564 = withCtrl(x6707) { CounterChain(List(x6563)).name("x6564").srcCtx("LSTMPipe.scala:54:49") } // CounterChainNew(List(x6563))
    val x6634 = withCtrl(x6707) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6564).name("x6634").srcCtx("LSTMPipe.scala:54:49") } // UnrolledForeach(List(b3520, b3495, b3491),x6564,Block(Const(())),List(List(b3547, b3548, b3549, b3550)),List(List(b3551, b3552, b3553, b3554)))
    val b3547 = withCtrl(x6634) { CounterIter(x6563, Some(0)).name("b3547") } // b3547
    val b3551 = withCtrl(x6634) { Const(true).name("b3551") } // b3551
    val b3548 = withCtrl(x6634) { CounterIter(x6563, Some(1)).name("b3548") } // b3548
    val b3552 = withCtrl(x6634) { Const(true).name("b3552") } // b3552
    val b3549 = withCtrl(x6634) { CounterIter(x6563, Some(2)).name("b3549") } // b3549
    val b3553 = withCtrl(x6634) { Const(true).name("b3553") } // b3553
    val b3550 = withCtrl(x6634) { CounterIter(x6563, Some(3)).name("b3550") } // b3550
    val b3554 = withCtrl(x6634) { Const(true).name("b3554") } // b3554
    val x6633 = withCtrl(x6634) { UnitController(style=ForkJoin, level=OuterControl).name("x6633").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3520, b3495, b3491),Block(Const(())))
    val x6565 = withCtrl(x6633) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6565").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6566 = withCtrl(x6633) { CounterChain(List(x6565)).name("x6566").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6565))
    val x6581 = withCtrl(x6633) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6566).name("x6581").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3551, b3520, b3495, b3491),x6566,Block(Const(())),List(List(b3563)),List(List(b3564)))
    val b3563 = withCtrl(x6581) { CounterIter(x6565, None).name("b3563") } // b3563
    val b3564 = withCtrl(x6581) { Const(true).name("b3564") } // b3564
    val x6567 = withCtrl(x6581) { OpDef(op=BitAnd, inputs=List(b3564, b3551)).name("x6567").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3551)
    val x6568 = withCtrl(x6581) { OpDef(op=BitAnd, inputs=List(b3520, b3495)).name("x6568").srcCtx("UnrollingBase.scala:28:66") } // And(b3520,b3495)
    val x6569 = withCtrl(x6581) { OpDef(op=BitAnd, inputs=List(x6567, x6568)).name("x6569").srcCtx("UnrollingBase.scala:28:66") } // And(x6567,x6568)
    val x6570 = withCtrl(x6581) { OpDef(op=BitAnd, inputs=List(x6569, b3491)).name("x6570").srcCtx("UnrollingBase.scala:28:66") } // And(x6569,b3491)
    val x6571 = withCtrl(x6581) { LoadBanks(List(x6375_d0_b0), List(b3494, b3518, b3547, b3563)).name("x6571").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3518, b3547, b3563)),List(x6570))
    val x6572 = withCtrl(x6581) { x6571 } // VectorApply(x6571,0)
    val x6573 = withCtrl(x6581) { ReadMem(x6544_d0).name("x6573").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6544)
    val x6574 = withCtrl(x6581) { OpDef(op=FixMul, inputs=List(x6572, x6573)).name("x6574").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6572,x6573)
    val x6575 = withCtrl(x6581) { LoadBanks(List(x6428_d0_b0), List(b3494, b3518, b3547, b3563)).name("x6575").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3518, b3547, b3563)),List(x6570))
    val x6576 = withCtrl(x6581) { x6575 } // VectorApply(x6575,0)
    val x6577 = withCtrl(x6581) { ReadMem(x6546_d0).name("x6577").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6546)
    val x6578 = withCtrl(x6581) { OpDef(op=FixMul, inputs=List(x6576, x6577)).name("x6578").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6576,x6577)
    val x6579 = withCtrl(x6581) { OpDef(op=FixAdd, inputs=List(x6574, x6578)).name("x6579").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6574,x6578)
    val x6580 = withCtrl(x6581) { StoreBanks(List(List(x6542_d0_b0)), List(b3547, b3563), x6579).name("x6580").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6542,List(List(b3547, b3563)),List(x6579),List(x6570))
    val x6582 = withCtrl(x6633) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6582").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6583 = withCtrl(x6633) { CounterChain(List(x6582)).name("x6583").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6582))
    val x6598 = withCtrl(x6633) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6583).name("x6598").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3552, b3520, b3495, b3491),x6583,Block(Const(())),List(List(b3580)),List(List(b3581)))
    val b3580 = withCtrl(x6598) { CounterIter(x6582, None).name("b3580") } // b3580
    val b3581 = withCtrl(x6598) { Const(true).name("b3581") } // b3581
    val x6584 = withCtrl(x6598) { OpDef(op=BitAnd, inputs=List(b3581, b3552)).name("x6584").srcCtx("UnrollingBase.scala:28:66") } // And(b3581,b3552)
    val x6585 = withCtrl(x6598) { OpDef(op=BitAnd, inputs=List(b3520, b3495)).name("x6585").srcCtx("UnrollingBase.scala:28:66") } // And(b3520,b3495)
    val x6586 = withCtrl(x6598) { OpDef(op=BitAnd, inputs=List(x6584, x6585)).name("x6586").srcCtx("UnrollingBase.scala:28:66") } // And(x6584,x6585)
    val x6587 = withCtrl(x6598) { OpDef(op=BitAnd, inputs=List(x6586, b3491)).name("x6587").srcCtx("UnrollingBase.scala:28:66") } // And(x6586,b3491)
    val x6588 = withCtrl(x6598) { LoadBanks(List(x6375_d0_b1), List(b3494, b3518, b3548, b3580)).name("x6588").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3518, b3548, b3580)),List(x6587))
    val x6589 = withCtrl(x6598) { x6588 } // VectorApply(x6588,0)
    val x6590 = withCtrl(x6598) { ReadMem(x6544_d1).name("x6590").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6544)
    val x6591 = withCtrl(x6598) { OpDef(op=FixMul, inputs=List(x6589, x6590)).name("x6591").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6589,x6590)
    val x6592 = withCtrl(x6598) { LoadBanks(List(x6428_d0_b1), List(b3494, b3518, b3548, b3580)).name("x6592").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3518, b3548, b3580)),List(x6587))
    val x6593 = withCtrl(x6598) { x6592 } // VectorApply(x6592,0)
    val x6594 = withCtrl(x6598) { ReadMem(x6546_d1).name("x6594").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6546)
    val x6595 = withCtrl(x6598) { OpDef(op=FixMul, inputs=List(x6593, x6594)).name("x6595").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6593,x6594)
    val x6596 = withCtrl(x6598) { OpDef(op=FixAdd, inputs=List(x6591, x6595)).name("x6596").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6591,x6595)
    val x6597 = withCtrl(x6598) { StoreBanks(List(List(x6542_d0_b1)), List(b3548, b3580), x6596).name("x6597").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6542,List(List(b3548, b3580)),List(x6596),List(x6587))
    val x6599 = withCtrl(x6633) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6599").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6600 = withCtrl(x6633) { CounterChain(List(x6599)).name("x6600").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6599))
    val x6615 = withCtrl(x6633) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6600).name("x6615").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3553, b3520, b3495, b3491),x6600,Block(Const(())),List(List(b3597)),List(List(b3598)))
    val b3597 = withCtrl(x6615) { CounterIter(x6599, None).name("b3597") } // b3597
    val b3598 = withCtrl(x6615) { Const(true).name("b3598") } // b3598
    val x6601 = withCtrl(x6615) { OpDef(op=BitAnd, inputs=List(b3598, b3553)).name("x6601").srcCtx("UnrollingBase.scala:28:66") } // And(b3598,b3553)
    val x6602 = withCtrl(x6615) { OpDef(op=BitAnd, inputs=List(b3520, b3495)).name("x6602").srcCtx("UnrollingBase.scala:28:66") } // And(b3520,b3495)
    val x6603 = withCtrl(x6615) { OpDef(op=BitAnd, inputs=List(x6601, x6602)).name("x6603").srcCtx("UnrollingBase.scala:28:66") } // And(x6601,x6602)
    val x6604 = withCtrl(x6615) { OpDef(op=BitAnd, inputs=List(x6603, b3491)).name("x6604").srcCtx("UnrollingBase.scala:28:66") } // And(x6603,b3491)
    val x6605 = withCtrl(x6615) { LoadBanks(List(x6375_d0_b2), List(b3494, b3518, b3549, b3597)).name("x6605").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3518, b3549, b3597)),List(x6604))
    val x6606 = withCtrl(x6615) { x6605 } // VectorApply(x6605,0)
    val x6607 = withCtrl(x6615) { ReadMem(x6544_d2).name("x6607").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6544)
    val x6608 = withCtrl(x6615) { OpDef(op=FixMul, inputs=List(x6606, x6607)).name("x6608").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6606,x6607)
    val x6609 = withCtrl(x6615) { LoadBanks(List(x6428_d0_b2), List(b3494, b3518, b3549, b3597)).name("x6609").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3518, b3549, b3597)),List(x6604))
    val x6610 = withCtrl(x6615) { x6609 } // VectorApply(x6609,0)
    val x6611 = withCtrl(x6615) { ReadMem(x6546_d2).name("x6611").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6546)
    val x6612 = withCtrl(x6615) { OpDef(op=FixMul, inputs=List(x6610, x6611)).name("x6612").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6610,x6611)
    val x6613 = withCtrl(x6615) { OpDef(op=FixAdd, inputs=List(x6608, x6612)).name("x6613").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6608,x6612)
    val x6614 = withCtrl(x6615) { StoreBanks(List(List(x6542_d0_b2)), List(b3549, b3597), x6613).name("x6614").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6542,List(List(b3549, b3597)),List(x6613),List(x6604))
    val x6616 = withCtrl(x6633) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6616").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6617 = withCtrl(x6633) { CounterChain(List(x6616)).name("x6617").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6616))
    val x6632 = withCtrl(x6633) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6617).name("x6632").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3554, b3520, b3495, b3491),x6617,Block(Const(())),List(List(b3614)),List(List(b3615)))
    val b3614 = withCtrl(x6632) { CounterIter(x6616, None).name("b3614") } // b3614
    val b3615 = withCtrl(x6632) { Const(true).name("b3615") } // b3615
    val x6618 = withCtrl(x6632) { OpDef(op=BitAnd, inputs=List(b3615, b3554)).name("x6618").srcCtx("UnrollingBase.scala:28:66") } // And(b3615,b3554)
    val x6619 = withCtrl(x6632) { OpDef(op=BitAnd, inputs=List(b3520, b3495)).name("x6619").srcCtx("UnrollingBase.scala:28:66") } // And(b3520,b3495)
    val x6620 = withCtrl(x6632) { OpDef(op=BitAnd, inputs=List(x6618, x6619)).name("x6620").srcCtx("UnrollingBase.scala:28:66") } // And(x6618,x6619)
    val x6621 = withCtrl(x6632) { OpDef(op=BitAnd, inputs=List(x6620, b3491)).name("x6621").srcCtx("UnrollingBase.scala:28:66") } // And(x6620,b3491)
    val x6622 = withCtrl(x6632) { LoadBanks(List(x6375_d0_b3), List(b3494, b3518, b3550, b3614)).name("x6622").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3518, b3550, b3614)),List(x6621))
    val x6623 = withCtrl(x6632) { x6622 } // VectorApply(x6622,0)
    val x6624 = withCtrl(x6632) { ReadMem(x6544_d3).name("x6624").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6544)
    val x6625 = withCtrl(x6632) { OpDef(op=FixMul, inputs=List(x6623, x6624)).name("x6625").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6623,x6624)
    val x6626 = withCtrl(x6632) { LoadBanks(List(x6428_d0_b3), List(b3494, b3518, b3550, b3614)).name("x6626").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3518, b3550, b3614)),List(x6621))
    val x6627 = withCtrl(x6632) { x6626 } // VectorApply(x6626,0)
    val x6628 = withCtrl(x6632) { ReadMem(x6546_d3).name("x6628").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6546)
    val x6629 = withCtrl(x6632) { OpDef(op=FixMul, inputs=List(x6627, x6628)).name("x6629").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6627,x6628)
    val x6630 = withCtrl(x6632) { OpDef(op=FixAdd, inputs=List(x6625, x6629)).name("x6630").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6625,x6629)
    val x6631 = withCtrl(x6632) { StoreBanks(List(List(x6542_d0_b3)), List(b3550, b3614), x6630).name("x6631").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6542,List(List(b3550, b3614)),List(x6630),List(x6621))
    val x6635 = withCtrl(x6707) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x6635").srcCtx("LSTMPipe.scala:54:36") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x6636 = withCtrl(x6707) { CounterChain(List(x6635)).name("x6636").srcCtx("LSTMPipe.scala:54:49") } // CounterChainNew(List(x6635))
    val x6706 = withCtrl(x6707) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6636).name("x6706").srcCtx("LSTMPipe.scala:54:49") } // UnrolledForeach(List(b3521, b3495, b3491),x6636,Block(Const(())),List(List(b3633, b3634, b3635, b3636)),List(List(b3637, b3638, b3639, b3640)))
    val b3633 = withCtrl(x6706) { CounterIter(x6635, Some(0)).name("b3633") } // b3633
    val b3637 = withCtrl(x6706) { Const(true).name("b3637") } // b3637
    val b3634 = withCtrl(x6706) { CounterIter(x6635, Some(1)).name("b3634") } // b3634
    val b3638 = withCtrl(x6706) { Const(true).name("b3638") } // b3638
    val b3635 = withCtrl(x6706) { CounterIter(x6635, Some(2)).name("b3635") } // b3635
    val b3639 = withCtrl(x6706) { Const(true).name("b3639") } // b3639
    val b3636 = withCtrl(x6706) { CounterIter(x6635, Some(3)).name("b3636") } // b3636
    val b3640 = withCtrl(x6706) { Const(true).name("b3640") } // b3640
    val x6705 = withCtrl(x6706) { UnitController(style=ForkJoin, level=OuterControl).name("x6705").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3521, b3495, b3491),Block(Const(())))
    val x6637 = withCtrl(x6705) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6637").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6638 = withCtrl(x6705) { CounterChain(List(x6637)).name("x6638").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6637))
    val x6653 = withCtrl(x6705) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6638).name("x6653").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3637, b3521, b3495, b3491),x6638,Block(Const(())),List(List(b3649)),List(List(b3650)))
    val b3649 = withCtrl(x6653) { CounterIter(x6637, None).name("b3649") } // b3649
    val b3650 = withCtrl(x6653) { Const(true).name("b3650") } // b3650
    val x6639 = withCtrl(x6653) { OpDef(op=BitAnd, inputs=List(b3650, b3637)).name("x6639").srcCtx("UnrollingBase.scala:28:66") } // And(b3650,b3637)
    val x6640 = withCtrl(x6653) { OpDef(op=BitAnd, inputs=List(b3521, b3495)).name("x6640").srcCtx("UnrollingBase.scala:28:66") } // And(b3521,b3495)
    val x6641 = withCtrl(x6653) { OpDef(op=BitAnd, inputs=List(x6639, x6640)).name("x6641").srcCtx("UnrollingBase.scala:28:66") } // And(x6639,x6640)
    val x6642 = withCtrl(x6653) { OpDef(op=BitAnd, inputs=List(x6641, b3491)).name("x6642").srcCtx("UnrollingBase.scala:28:66") } // And(x6641,b3491)
    val x6643 = withCtrl(x6653) { LoadBanks(List(x6375_d0_b4), List(b3494, b3519, b3633, b3649)).name("x6643").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3519, b3633, b3649)),List(x6642))
    val x6644 = withCtrl(x6653) { x6643 } // VectorApply(x6643,0)
    val x6645 = withCtrl(x6653) { ReadMem(x6545_d0).name("x6645").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6545)
    val x6646 = withCtrl(x6653) { OpDef(op=FixMul, inputs=List(x6644, x6645)).name("x6646").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6644,x6645)
    val x6647 = withCtrl(x6653) { LoadBanks(List(x6428_d0_b4), List(b3494, b3519, b3633, b3649)).name("x6647").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3519, b3633, b3649)),List(x6642))
    val x6648 = withCtrl(x6653) { x6647 } // VectorApply(x6647,0)
    val x6649 = withCtrl(x6653) { ReadMem(x6547_d0).name("x6649").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6547)
    val x6650 = withCtrl(x6653) { OpDef(op=FixMul, inputs=List(x6648, x6649)).name("x6650").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6648,x6649)
    val x6651 = withCtrl(x6653) { OpDef(op=FixAdd, inputs=List(x6646, x6650)).name("x6651").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6646,x6650)
    val x6652 = withCtrl(x6653) { StoreBanks(List(List(x6543_d0_b0)), List(b3633, b3649), x6651).name("x6652").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6543,List(List(b3633, b3649)),List(x6651),List(x6642))
    val x6654 = withCtrl(x6705) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6654").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6655 = withCtrl(x6705) { CounterChain(List(x6654)).name("x6655").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6654))
    val x6670 = withCtrl(x6705) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6655).name("x6670").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3638, b3521, b3495, b3491),x6655,Block(Const(())),List(List(b3666)),List(List(b3667)))
    val b3666 = withCtrl(x6670) { CounterIter(x6654, None).name("b3666") } // b3666
    val b3667 = withCtrl(x6670) { Const(true).name("b3667") } // b3667
    val x6656 = withCtrl(x6670) { OpDef(op=BitAnd, inputs=List(b3667, b3638)).name("x6656").srcCtx("UnrollingBase.scala:28:66") } // And(b3667,b3638)
    val x6657 = withCtrl(x6670) { OpDef(op=BitAnd, inputs=List(b3521, b3495)).name("x6657").srcCtx("UnrollingBase.scala:28:66") } // And(b3521,b3495)
    val x6658 = withCtrl(x6670) { OpDef(op=BitAnd, inputs=List(x6656, x6657)).name("x6658").srcCtx("UnrollingBase.scala:28:66") } // And(x6656,x6657)
    val x6659 = withCtrl(x6670) { OpDef(op=BitAnd, inputs=List(x6658, b3491)).name("x6659").srcCtx("UnrollingBase.scala:28:66") } // And(x6658,b3491)
    val x6660 = withCtrl(x6670) { LoadBanks(List(x6375_d0_b5), List(b3494, b3519, b3634, b3666)).name("x6660").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3519, b3634, b3666)),List(x6659))
    val x6661 = withCtrl(x6670) { x6660 } // VectorApply(x6660,0)
    val x6662 = withCtrl(x6670) { ReadMem(x6545_d1).name("x6662").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6545)
    val x6663 = withCtrl(x6670) { OpDef(op=FixMul, inputs=List(x6661, x6662)).name("x6663").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6661,x6662)
    val x6664 = withCtrl(x6670) { LoadBanks(List(x6428_d0_b5), List(b3494, b3519, b3634, b3666)).name("x6664").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3519, b3634, b3666)),List(x6659))
    val x6665 = withCtrl(x6670) { x6664 } // VectorApply(x6664,0)
    val x6666 = withCtrl(x6670) { ReadMem(x6547_d1).name("x6666").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6547)
    val x6667 = withCtrl(x6670) { OpDef(op=FixMul, inputs=List(x6665, x6666)).name("x6667").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6665,x6666)
    val x6668 = withCtrl(x6670) { OpDef(op=FixAdd, inputs=List(x6663, x6667)).name("x6668").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6663,x6667)
    val x6669 = withCtrl(x6670) { StoreBanks(List(List(x6543_d0_b1)), List(b3634, b3666), x6668).name("x6669").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6543,List(List(b3634, b3666)),List(x6668),List(x6659))
    val x6671 = withCtrl(x6705) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6671").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6672 = withCtrl(x6705) { CounterChain(List(x6671)).name("x6672").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6671))
    val x6687 = withCtrl(x6705) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6672).name("x6687").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3639, b3521, b3495, b3491),x6672,Block(Const(())),List(List(b3683)),List(List(b3684)))
    val b3683 = withCtrl(x6687) { CounterIter(x6671, None).name("b3683") } // b3683
    val b3684 = withCtrl(x6687) { Const(true).name("b3684") } // b3684
    val x6673 = withCtrl(x6687) { OpDef(op=BitAnd, inputs=List(b3684, b3639)).name("x6673").srcCtx("UnrollingBase.scala:28:66") } // And(b3684,b3639)
    val x6674 = withCtrl(x6687) { OpDef(op=BitAnd, inputs=List(b3521, b3495)).name("x6674").srcCtx("UnrollingBase.scala:28:66") } // And(b3521,b3495)
    val x6675 = withCtrl(x6687) { OpDef(op=BitAnd, inputs=List(x6673, x6674)).name("x6675").srcCtx("UnrollingBase.scala:28:66") } // And(x6673,x6674)
    val x6676 = withCtrl(x6687) { OpDef(op=BitAnd, inputs=List(x6675, b3491)).name("x6676").srcCtx("UnrollingBase.scala:28:66") } // And(x6675,b3491)
    val x6677 = withCtrl(x6687) { LoadBanks(List(x6375_d0_b6), List(b3494, b3519, b3635, b3683)).name("x6677").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3519, b3635, b3683)),List(x6676))
    val x6678 = withCtrl(x6687) { x6677 } // VectorApply(x6677,0)
    val x6679 = withCtrl(x6687) { ReadMem(x6545_d2).name("x6679").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6545)
    val x6680 = withCtrl(x6687) { OpDef(op=FixMul, inputs=List(x6678, x6679)).name("x6680").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6678,x6679)
    val x6681 = withCtrl(x6687) { LoadBanks(List(x6428_d0_b6), List(b3494, b3519, b3635, b3683)).name("x6681").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3519, b3635, b3683)),List(x6676))
    val x6682 = withCtrl(x6687) { x6681 } // VectorApply(x6681,0)
    val x6683 = withCtrl(x6687) { ReadMem(x6547_d2).name("x6683").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6547)
    val x6684 = withCtrl(x6687) { OpDef(op=FixMul, inputs=List(x6682, x6683)).name("x6684").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6682,x6683)
    val x6685 = withCtrl(x6687) { OpDef(op=FixAdd, inputs=List(x6680, x6684)).name("x6685").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6680,x6684)
    val x6686 = withCtrl(x6687) { StoreBanks(List(List(x6543_d0_b2)), List(b3635, b3683), x6685).name("x6686").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6543,List(List(b3635, b3683)),List(x6685),List(x6676))
    val x6688 = withCtrl(x6705) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6688").srcCtx("LSTMPipe.scala:55:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6689 = withCtrl(x6705) { CounterChain(List(x6688)).name("x6689").srcCtx("LSTMPipe.scala:55:58") } // CounterChainNew(List(x6688))
    val x6704 = withCtrl(x6705) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6689).name("x6704").srcCtx("LSTMPipe.scala:55:58") } // UnrolledForeach(List(b3640, b3521, b3495, b3491),x6689,Block(Const(())),List(List(b3700)),List(List(b3701)))
    val b3700 = withCtrl(x6704) { CounterIter(x6688, None).name("b3700") } // b3700
    val b3701 = withCtrl(x6704) { Const(true).name("b3701") } // b3701
    val x6690 = withCtrl(x6704) { OpDef(op=BitAnd, inputs=List(b3701, b3640)).name("x6690").srcCtx("UnrollingBase.scala:28:66") } // And(b3701,b3640)
    val x6691 = withCtrl(x6704) { OpDef(op=BitAnd, inputs=List(b3521, b3495)).name("x6691").srcCtx("UnrollingBase.scala:28:66") } // And(b3521,b3495)
    val x6692 = withCtrl(x6704) { OpDef(op=BitAnd, inputs=List(x6690, x6691)).name("x6692").srcCtx("UnrollingBase.scala:28:66") } // And(x6690,x6691)
    val x6693 = withCtrl(x6704) { OpDef(op=BitAnd, inputs=List(x6692, b3491)).name("x6693").srcCtx("UnrollingBase.scala:28:66") } // And(x6692,b3491)
    val x6694 = withCtrl(x6704) { LoadBanks(List(x6375_d0_b7), List(b3494, b3519, b3636, b3700)).name("x6694").srcCtx("LSTMPipe.scala:56:31") } // ParSRAMLoad(x6375,List(List(b3494, b3519, b3636, b3700)),List(x6693))
    val x6695 = withCtrl(x6704) { x6694 } // VectorApply(x6694,0)
    val x6696 = withCtrl(x6704) { ReadMem(x6545_d3).name("x6696").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6545)
    val x6697 = withCtrl(x6704) { OpDef(op=FixMul, inputs=List(x6695, x6696)).name("x6697").srcCtx("LSTMPipe.scala:56:69:reX") } // FixMul(x6695,x6696)
    val x6698 = withCtrl(x6704) { LoadBanks(List(x6428_d0_b7), List(b3494, b3519, b3636, b3700)).name("x6698").srcCtx("LSTMPipe.scala:57:31") } // ParSRAMLoad(x6428,List(List(b3494, b3519, b3636, b3700)),List(x6693))
    val x6699 = withCtrl(x6704) { x6698 } // VectorApply(x6698,0)
    val x6700 = withCtrl(x6704) { ReadMem(x6547_d3).name("x6700").srcCtx("LSTMPipe.scala:63:14") } // RegRead(x6547)
    val x6701 = withCtrl(x6704) { OpDef(op=FixMul, inputs=List(x6699, x6700)).name("x6701").srcCtx("LSTMPipe.scala:57:69:reH") } // FixMul(x6699,x6700)
    val x6702 = withCtrl(x6704) { OpDef(op=FixAdd, inputs=List(x6697, x6701)).name("x6702").srcCtx("LSTMPipe.scala:58:48") } // FixAdd(x6697,x6701)
    val x6703 = withCtrl(x6704) { StoreBanks(List(List(x6543_d0_b3)), List(b3636, b3700), x6702).name("x6703").srcCtx("LSTMPipe.scala:58:42") } // ParSRAMStore(x6543,List(List(b3636, b3700)),List(x6702),List(x6693))
    val x6708 = withCtrl(x6733) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6708").srcCtx("LSTMPipe.scala:63:14") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6709 = withCtrl(x6733) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6709").srcCtx("LSTMPipe.scala:63:14") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6710 = withCtrl(x6733) { CounterChain(List(x6709,x6708)).name("x6710").srcCtx("LSTMPipe.scala:63:14") } // CounterChainNew(ArrayBuffer(x6709, x6708))
    val x6732 = withCtrl(x6733) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6710).name("x6732").srcCtx("LSTMPipe.scala:63:14") } // UnrolledForeach(List(),x6710,Block(Const(())),ArrayBuffer(List(b3720), List(b3721)),ArrayBuffer(List(b3722), List(b3723)))
    val b3720 = withCtrl(x6732) { CounterIter(x6709, Some(0)).name("b3720") } // b3720
    val b3722 = withCtrl(x6732) { Const(true).name("b3722") } // b3722
    val b3721 = withCtrl(x6732) { CounterIter(x6708, None).name("b3721") } // b3721
    val b3723 = withCtrl(x6732) { Const(true).name("b3723") } // b3723
    val x6711 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(b3722, b3723)).name("x6711").srcCtx("UnrollingBase.scala:28:66") } // And(b3722,b3723)
    val x6712 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(b3495, b3491)).name("x6712").srcCtx("UnrollingBase.scala:28:66") } // And(b3495,b3491)
    val x6713 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(x6711, x6712)).name("x6713").srcCtx("UnrollingBase.scala:28:66") } // And(x6711,x6712)
    val x6714 = withCtrl(x6732) { LoadBanks(List(x6542_d0_b0, x6542_d0_b1, x6542_d0_b2, x6542_d0_b3), List(b3720, b3721)).name("x6714").srcCtx("LSTMPipe.scala:63:14") } // ParSRAMLoad(x6542,List(ArrayBuffer(b3720, b3721)),List(x6713))
    val x6715 = withCtrl(x6732) { x6714 } // VectorApply(x6714,0)
    val x6716 = withCtrl(x6732) { LoadBanks(List(x6543_d0_b0, x6543_d0_b1, x6543_d0_b2, x6543_d0_b3), List(b3720, b3721)).name("x6716").srcCtx("LSTMPipe.scala:63:14") } // ParSRAMLoad(x6543,List(ArrayBuffer(b3720, b3721)),List(x6713))
    val x6717 = withCtrl(x6732) { x6716 } // VectorApply(x6716,0)
    val x6718 = withCtrl(x6732) { LoadBanks(List(x6527_d1_b0), List(b3720, b3721)).name("x6718").srcCtx("LSTMPipe.scala:63:14") } // ParSRAMLoad(x6527,List(ArrayBuffer(b3720, b3721)),List(x6713))
    val x6719 = withCtrl(x6732) { x6718 } // VectorApply(x6718,0)
    val x6720 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(b3520, b3495)).name("x6720").srcCtx("LSTMPipe.scala:63:14") } // And(b3520,b3495)
    val x6721 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(x6720, b3491)).name("x6721").srcCtx("LSTMPipe.scala:63:14") } // And(x6720,b3491)
    val x6722 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(b3521, b3495)).name("x6722").srcCtx("LSTMPipe.scala:63:14") } // And(b3521,b3495)
    val x6723 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(x6722, b3491)).name("x6723").srcCtx("LSTMPipe.scala:63:14") } // And(x6722,b3491)
    val x6724 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(x6721, x6713)).name("x6724").srcCtx("LSTMPipe.scala:63:14") } // And(x6721,x6713)
    val x6725 = withCtrl(x6732) { OpDef(op=BitAnd, inputs=List(x6723, x6713)).name("x6725").srcCtx("LSTMPipe.scala:63:14") } // And(x6723,x6713)
    val x6726 = withCtrl(x6732) { OpDef(op=FixAdd, inputs=List(x6715, x6717)).name("x6726").srcCtx("LSTMPipe.scala:63:16") } // FixAdd(x6715,x6717)
    val x6727 = withCtrl(x6732) { OpDef(op=MuxOp, inputs=List(x6725, x6726, x6715)).name("x6727").srcCtx("LSTMPipe.scala:63:14") } // Mux(x6725,x6726,x6715)
    val x6728 = withCtrl(x6732) { OpDef(op=BitOr, inputs=List(x6724, x6725)).name("x6728").srcCtx("LSTMPipe.scala:63:14") } // Or(x6724,x6725)
    val x6729 = withCtrl(x6732) { OpDef(op=FixEql, inputs=List(b3518, Const(0))).name("x6729").srcCtx("LSTMPipe.scala:63:14") } // FixEql(b3518,Const(0))
    val x6730 = withCtrl(x6732) { OpDef(op=FixAdd, inputs=List(x6727, x6719)).name("x6730").srcCtx("LSTMPipe.scala:63:16") } // FixAdd(x6727,x6719)
    val x6731 = withCtrl(x6732) { StoreBanks(List(List(x6527_d0_b0), List(x6527_d1_b0)), List(b3720, b3721), x6730).name("x6731").srcCtx("LSTMPipe.scala:63:14") } // ParSRAMStore(x6527,List(ArrayBuffer(b3720, b3721)),List(x6730),List(x6713))
    antiDepsOf(x6731)=List(x6718)
    val x6734 = withCtrl(x6817) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6734").srcCtx("LSTMPipe.scala:66:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6735 = withCtrl(x6817) { CounterChain(List(x6734)).name("x6735").srcCtx("LSTMPipe.scala:66:35") } // CounterChainNew(List(x6734))
    val x6816 = withCtrl(x6817) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6735).name("x6816").srcCtx("LSTMPipe.scala:66:35") } // UnrolledForeach(List(b3495, b3491),x6735,Block(Const(())),List(List(b3749)),List(List(b3750)))
    val b3749 = withCtrl(x6816) { CounterIter(x6734, Some(0)).name("b3749") } // b3749
    val b3750 = withCtrl(x6816) { Const(true).name("b3750") } // b3750
    val x6736 = withCtrl(x6816) { FIFO(size=16).name("x6736").srcCtx("LSTMPipe.scala:67:33:sigQ") } // x6736 = FIFONew(Const(16))
    isAccum(x6736) = false
    bufferDepthOf(x6736) = 2
    val x6737 = withCtrl(x6816) { FIFO(size=16).name("x6737").srcCtx("LSTMPipe.scala:68:33:actQ") } // x6737 = FIFONew(Const(16))
    isAccum(x6737) = false
    bufferDepthOf(x6737) = 2
    val x6738 = withCtrl(x6816) { FIFO(size=16).name("x6738").srcCtx("LSTMPipe.scala:69:37:hUpdateQ") } // x6738 = FIFONew(Const(16))
    isAccum(x6738) = false
    bufferDepthOf(x6738) = 1
    val x6739 = withCtrl(x6816) { FIFO(size=16).name("x6739").srcCtx("LSTMPipe.scala:70:37:cUpdateQ") } // x6739 = FIFONew(Const(16))
    isAccum(x6739) = false
    bufferDepthOf(x6739) = 1
    val x6740 = withCtrl(x6816) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6740").srcCtx("LSTMPipe.scala:72:42") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6741 = withCtrl(x6816) { CounterChain(List(x6740)).name("x6741").srcCtx("LSTMPipe.scala:72:56") } // CounterChainNew(List(x6740))
    val x6762 = withCtrl(x6816) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6741).name("x6762").srcCtx("LSTMPipe.scala:72:56") } // UnrolledForeach(List(b3750, b3495, b3491),x6741,Block(Const(())),List(List(b3757)),List(List(b3758)))
    val b3757 = withCtrl(x6762) { CounterIter(x6740, None).name("b3757") } // b3757
    val b3758 = withCtrl(x6762) { Const(true).name("b3758") } // b3758
    val x6742 = withCtrl(x6762) { OpDef(op=BitAnd, inputs=List(b3758, b3750)).name("x6742").srcCtx("UnrollingBase.scala:28:66") } // And(b3758,b3750)
    val x6743 = withCtrl(x6762) { OpDef(op=BitAnd, inputs=List(b3495, b3491)).name("x6743").srcCtx("UnrollingBase.scala:28:66") } // And(b3495,b3491)
    val x6744 = withCtrl(x6762) { OpDef(op=BitAnd, inputs=List(x6742, x6743)).name("x6744").srcCtx("UnrollingBase.scala:28:66") } // And(x6742,x6743)
    val x6745 = withCtrl(x6762) { LoadBanks(List(x6527_d0_b0), List(b3749, b3757)).name("x6745").srcCtx("LSTMPipe.scala:73:35:pEle") } // ParSRAMLoad(x6527,List(List(b3749, b3757)),List(x6744))
    val x6746 = withCtrl(x6762) { x6745 } // VectorApply(x6745,0)
    val x6747_d0_b0 = withCtrl(x6762) { LUT(inits=List(-0.99999976, -0.99999976, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.99999964, -0.99999964, -0.99999964, -0.99999964, -0.99999964, -0.99999964, -0.9999996, -0.9999996, -0.9999996, -0.9999996, -0.9999996, -0.9999995, -0.9999995, -0.9999995, -0.9999995, -0.9999995, -0.99999946, -0.99999946, -0.99999946, -0.9999994, -0.9999994, -0.9999994, -0.9999994, -0.99999934, -0.99999934, -0.99999934, -0.9999993, -0.9999993, -0.9999992, -0.9999992, -0.9999992, -0.99999917, -0.99999917, -0.9999991, -0.9999991, -0.9999991, -0.99999905, -0.99999905, -0.999999, -0.999999, -0.9999989, -0.99999887, -0.99999887, -0.9999988, -0.9999988, -0.99999875, -0.9999987, -0.9999987, -0.9999986, -0.99999857, -0.99999857, -0.9999985, -0.99999845, -0.9999984, -0.99999833, -0.99999833, -0.9999983, -0.9999982, -0.99999815, -0.9999981, -0.99999803, -0.999998, -0.9999979, -0.99999785, -0.99999774, -0.9999977, -0.9999976, -0.99999756, -0.9999975, -0.9999974, -0.9999973, -0.9999972, -0.99999714, -0.999997, -0.99999696, -0.99999684, -0.9999968, -0.99999666, -0.99999654, -0.9999964, -0.9999963, -0.9999962, -0.99999607, -0.99999595, -0.9999958, -0.9999957, -0.9999956, -0.9999954, -0.9999953, -0.9999952, -0.999995, -0.9999948, -0.9999947, -0.9999945, -0.99999434, -0.99999416, -0.999994, -0.99999374, -0.99999356, -0.9999934, -0.99999315, -0.99999297, -0.9999927, -0.9999925, -0.99999225, -0.999992, -0.9999918, -0.9999915, -0.99999124, -0.99999094, -0.99999064, -0.99999034, -0.99999005, -0.99998975, -0.9999894, -0.9999891, -0.99998873, -0.9999884, -0.999988, -0.99998766, -0.99998724, -0.9999868, -0.9999864, -0.999986, -0.9999855, -0.9999851, -0.9999846, -0.99998415, -0.9999836, -0.99998313, -0.9999826, -0.999982, -0.99998146, -0.99998087, -0.9999803, -0.9999796, -0.99997896, -0.9999783, -0.99997765, -0.99997693, -0.99997616, -0.99997544, -0.99997467, -0.99997383, -0.999973, -0.99997216, -0.9999713, -0.9999704, -0.9999694, -0.9999684, -0.99996746, -0.9999664, -0.9999653, -0.99996424, -0.9999631, -0.9999619, -0.9999607, -0.99995947, -0.99995816, -0.99995685, -0.9999555, -0.9999541, -0.9999526, -0.9999511, -0.9999496, -0.99994797, -0.9999463, -0.9999446, -0.99994284, -0.99994105, -0.99993914, -0.99993724, -0.9999352, -0.9999332, -0.99993104, -0.99992883, -0.9999266, -0.99992424, -0.99992186, -0.99991935, -0.9999168, -0.99991417, -0.9999114, -0.9999086, -0.99990577, -0.9999027, -0.9998996, -0.99989647, -0.9998932, -0.9998898, -0.9998863, -0.9998827, -0.99987894, -0.9998751, -0.99987113, -0.999867, -0.9998628, -0.99985844, -0.99985397, -0.9998493, -0.99984455, -0.9998396, -0.9998345, -0.99982923, -0.9998238, -0.9998182, -0.9998124, -0.99980646, -0.9998003, -0.999794, -0.99978745, -0.9997807, -0.99977374, -0.9997665, -0.99975914, -0.9997515, -0.9997436, -0.9997355, -0.9997271, -0.99971837, -0.9997094, -0.9997002, -0.99969065, -0.9996808, -0.9996707, -0.99966025, -0.99964947, -0.9996383, -0.9996268, -0.99961495, -0.99960274, -0.9995901, -0.9995771, -0.9995637, -0.9995498, -0.9995355, -0.9995208, -0.9995055, -0.99948984, -0.99947363, -0.9994569, -0.99943966, -0.99942183, -0.9994035, -0.9993845, -0.999365, -0.9993448, -0.999324, -0.9993025, -0.9992804, -0.9992575, -0.99923396, -0.99920964, -0.9991845, -0.9991586, -0.99913186, -0.9991043, -0.9990759, -0.9990465, -0.9990162, -0.998985, -0.99895275, -0.9989195, -0.9988852, -0.9988498, -0.9988133, -0.9987756, -0.9987367, -0.99869657, -0.9986552, -0.9986125, -0.9985685, -0.998523, -0.9984761, -0.9984277, -0.9983778, -0.9983263, -0.99827313, -0.99821836, -0.9981618, -0.99810344, -0.99804324, -0.99798113, -0.99791706, -0.9978509, -0.9977827, -0.9977123, -0.9976397, -0.99756485, -0.99748755, -0.9974078, -0.99732554, -0.9972407, -0.99715316, -0.9970628, -0.99696964, -0.9968735, -0.9967743, -0.996672, -0.9965664, -0.9964575, -0.99634516, -0.99622923, -0.99610966, -0.9959863, -0.995859, -0.9957277, -0.9955923, -0.9954525, -0.9953084, -0.9951597, -0.99500626, -0.994848, -0.99468476, -0.9945163, -0.99434257, -0.99416333, -0.99397844, -0.99378765, -0.9935909, -0.99338794, -0.99317855, -0.9929626, -0.9927398, -0.99250996, -0.99227285, -0.9920283, -0.99177605, -0.9915159, -0.9912475, -0.9909706, -0.99068505, -0.9903905, -0.9900866, -0.9897733, -0.98945004, -0.9891166, -0.98877275, -0.98841804, -0.98805225, -0.98767495, -0.9872858, -0.9868845, -0.9864705, -0.98604363, -0.9856034, -0.9851493, -0.98468107, -0.98419815, -0.98370016, -0.98318654, -0.98265696, -0.9821108, -0.9815476, -0.98096687, -0.980368, -0.9797506, -0.9791139, -0.97845733, -0.97778046, -0.9770825, -0.9763629, -0.975621, -0.9748561, -0.9740675, -0.9732545, -0.9724164, -0.97155243, -0.97066176, -0.9697436, -0.96879727, -0.9678217, -0.96681625, -0.96577984, -0.9647117, -0.9636108, -0.96247625, -0.961307, -0.9601021, -0.9588604, -0.9575809, -0.9562625, -0.9549041, -0.9535045, -0.95206255, -0.95057696, -0.94904655, -0.94747, -0.9458461, -0.9441734, -0.9424505, -0.94067615, -0.9388488, -0.93696696, -0.93502915, -0.9330338, -0.93097943, -0.9288643, -0.9266868, -0.92444533, -0.92213804, -0.9197632, -0.9173191, -0.91480386, -0.91221553, -0.90955234, -0.9068123, -0.9039934, -0.90109366, -0.89811105, -0.89504343, -0.89188874, -0.88864475, -0.88530934, -0.8818803, -0.8783553, -0.8747322, -0.8710085, -0.867182, -0.8632503, -0.85921097, -0.8550616, -0.85079974, -0.846423, -0.8419288, -0.8373146, -0.83257806, -0.82771647, -0.8227274, -0.8176083, -0.81235653, -0.80696964, -0.801445, -0.7957802, -0.78997254, -0.78401965, -0.77791893, -0.77166796, -0.7652642, -0.7587054, -0.75198907, -0.74511284, -0.7380745, -0.73087174, -0.72350246, -0.7159645, -0.7082558, -0.7003745, -0.69231856, -0.68408626, -0.6756759, -0.66708595, -0.6583149, -0.6493613, -0.640224, -0.6309019, -0.621394, -0.61169946, -0.60181767, -0.5917481, -0.5814904, -0.57104445, -0.5604102, -0.54958797, -0.53857803, -0.52738106, -0.5159979, -0.50442946, -0.4926771, -0.48074222, -0.4686265, -0.4563319, -0.44386053, -0.4312148, -0.41839743, -0.40541112, -0.39225912, -0.3789448, -0.36547172, -0.3518437, -0.3380649, -0.32413965, -0.31007248, -0.29586822, -0.28153187, -0.26706874, -0.25248426, -0.2377842, -0.22297436, -0.20806092, -0.19305009, -0.1779483, -0.16276228, -0.14749873, -0.1321646, -0.11676693, -0.101312935, -0.085809946, -0.07026523, -0.054686308, -0.03908074, -0.023456097, -0.007819951, 0.007819951, 0.023456097, 0.03908074, 0.054686308, 0.07026523, 0.085809946, 0.101312935, 0.11676693, 0.1321646, 0.14749873, 0.16276228, 0.1779483, 0.19305009, 0.20806092, 0.22297436, 0.2377842, 0.25248426, 0.26706874, 0.28153187, 0.29586822, 0.31007248, 0.32413965, 0.3380649, 0.3518437, 0.36547172, 0.3789448, 0.39225912, 0.40541112, 0.41839743, 0.4312148, 0.44386053, 0.4563319, 0.4686265, 0.48074222, 0.4926771, 0.50442946, 0.5159979, 0.52738106, 0.53857803, 0.54958797, 0.5604102, 0.57104445, 0.5814904, 0.5917481, 0.60181767, 0.61169946, 0.621394, 0.6309019, 0.640224, 0.6493613, 0.6583149, 0.66708595, 0.6756759, 0.68408626, 0.69231856, 0.7003745, 0.7082558, 0.7159645, 0.72350246, 0.73087174, 0.7380745, 0.74511284, 0.75198907, 0.7587054, 0.7652642, 0.77166796, 0.77791893, 0.78401965, 0.78997254, 0.7957802, 0.801445, 0.80696964, 0.81235653, 0.8176083, 0.8227274, 0.82771647, 0.83257806, 0.8373146, 0.8419288, 0.846423, 0.85079974, 0.8550616, 0.85921097, 0.8632503, 0.867182, 0.8710085, 0.8747322, 0.8783553, 0.8818803, 0.88530934, 0.88864475, 0.89188874, 0.89504343, 0.89811105, 0.90109366, 0.9039934, 0.9068123, 0.90955234, 0.91221553, 0.91480386, 0.9173191, 0.9197632, 0.92213804, 0.92444533, 0.9266868, 0.9288643, 0.93097943, 0.9330338, 0.93502915, 0.93696696, 0.9388488, 0.94067615, 0.9424505, 0.9441734, 0.9458461, 0.94747, 0.94904655, 0.95057696, 0.95206255, 0.9535045, 0.9549041, 0.9562625, 0.9575809, 0.9588604, 0.9601021, 0.961307, 0.96247625, 0.9636108, 0.9647117, 0.96577984, 0.96681625, 0.9678217, 0.96879727, 0.9697436, 0.97066176, 0.97155243, 0.9724164, 0.9732545, 0.9740675, 0.9748561, 0.975621, 0.9763629, 0.9770825, 0.97778046, 0.97845733, 0.9791139, 0.9797506, 0.980368, 0.98096687, 0.9815476, 0.9821108, 0.98265696, 0.98318654, 0.98370016, 0.98419815, 0.98468107, 0.9851493, 0.9856034, 0.98604363, 0.9864705, 0.9868845, 0.9872858, 0.98767495, 0.98805225, 0.98841804, 0.98877275, 0.9891166, 0.98945004, 0.9897733, 0.9900866, 0.9903905, 0.99068505, 0.9909706, 0.9912475, 0.9915159, 0.99177605, 0.9920283, 0.99227285, 0.99250996, 0.9927398, 0.9929626, 0.99317855, 0.99338794, 0.9935909, 0.99378765, 0.99397844, 0.99416333, 0.99434257, 0.9945163, 0.99468476, 0.994848, 0.99500626, 0.9951597, 0.9953084, 0.9954525, 0.9955923, 0.9957277, 0.995859, 0.9959863, 0.99610966, 0.99622923, 0.99634516, 0.9964575, 0.9965664, 0.996672, 0.9967743, 0.9968735, 0.99696964, 0.9970628, 0.99715316, 0.9972407, 0.99732554, 0.9974078, 0.99748755, 0.99756485, 0.9976397, 0.9977123, 0.9977827, 0.9978509, 0.99791706, 0.99798113, 0.99804324, 0.99810344, 0.9981618, 0.99821836, 0.99827313, 0.9983263, 0.9983778, 0.9984277, 0.9984761, 0.998523, 0.9985685, 0.9986125, 0.9986552, 0.99869657, 0.9987367, 0.9987756, 0.9988133, 0.9988498, 0.9988852, 0.9989195, 0.99895275, 0.998985, 0.9990162, 0.9990465, 0.9990759, 0.9991043, 0.99913186, 0.9991586, 0.9991845, 0.99920964, 0.99923396, 0.9992575, 0.9992804, 0.9993025, 0.999324, 0.9993448, 0.999365, 0.9993845, 0.9994035, 0.99942183, 0.99943966, 0.9994569, 0.99947363, 0.99948984, 0.9995055, 0.9995208, 0.9995355, 0.9995498, 0.9995637, 0.9995771, 0.9995901, 0.99960274, 0.99961495, 0.9996268, 0.9996383, 0.99964947, 0.99966025, 0.9996707, 0.9996808, 0.99969065, 0.9997002, 0.9997094, 0.99971837, 0.9997271, 0.9997355, 0.9997436, 0.9997515, 0.99975914, 0.9997665, 0.99977374, 0.9997807, 0.99978745, 0.999794, 0.9998003, 0.99980646, 0.9998124, 0.9998182, 0.9998238, 0.99982923, 0.9998345, 0.9998396, 0.99984455, 0.9998493, 0.99985397, 0.99985844, 0.9998628, 0.999867, 0.99987113, 0.9998751, 0.99987894, 0.9998827, 0.9998863, 0.9998898, 0.9998932, 0.99989647, 0.9998996, 0.9999027, 0.99990577, 0.9999086, 0.9999114, 0.99991417, 0.9999168, 0.99991935, 0.99992186, 0.99992424, 0.9999266, 0.99992883, 0.99993104, 0.9999332, 0.9999352, 0.99993724, 0.99993914, 0.99994105, 0.99994284, 0.9999446, 0.9999463, 0.99994797, 0.9999496, 0.9999511, 0.9999526, 0.9999541, 0.9999555, 0.99995685, 0.99995816, 0.99995947, 0.9999607, 0.9999619, 0.9999631, 0.99996424, 0.9999653, 0.9999664, 0.99996746, 0.9999684, 0.9999694, 0.9999704, 0.9999713, 0.99997216, 0.999973, 0.99997383, 0.99997467, 0.99997544, 0.99997616, 0.99997693, 0.99997765, 0.9999783, 0.99997896, 0.9999796, 0.9999803, 0.99998087, 0.99998146, 0.999982, 0.9999826, 0.99998313, 0.9999836, 0.99998415, 0.9999846, 0.9999851, 0.9999855, 0.999986, 0.9999864, 0.9999868, 0.99998724, 0.99998766, 0.999988, 0.9999884, 0.99998873, 0.9999891, 0.9999894, 0.99998975, 0.99999005, 0.99999034, 0.99999064, 0.99999094, 0.99999124, 0.9999915, 0.9999918, 0.999992, 0.99999225, 0.9999925, 0.9999927, 0.99999297, 0.99999315, 0.9999934, 0.99999356, 0.99999374, 0.999994, 0.99999416, 0.99999434, 0.9999945, 0.9999947, 0.9999948, 0.999995, 0.9999952, 0.9999953, 0.9999954, 0.9999956, 0.9999957, 0.9999958, 0.99999595, 0.99999607, 0.9999962, 0.9999963, 0.9999964, 0.99999654, 0.99999666, 0.9999968, 0.99999684, 0.99999696, 0.999997, 0.99999714, 0.9999972, 0.9999973, 0.9999974, 0.9999975, 0.99999756, 0.9999976, 0.9999977, 0.99999774, 0.99999785, 0.9999979, 0.999998, 0.99999803, 0.9999981, 0.99999815, 0.9999982, 0.9999983, 0.99999833, 0.99999833, 0.9999984, 0.99999845, 0.9999985, 0.99999857, 0.99999857, 0.9999986, 0.9999987, 0.9999987, 0.99999875, 0.9999988, 0.9999988, 0.99999887, 0.99999887, 0.9999989, 0.999999, 0.999999, 0.99999905, 0.99999905, 0.9999991, 0.9999991, 0.9999991, 0.99999917, 0.99999917, 0.9999992, 0.9999992, 0.9999992, 0.9999993, 0.9999993, 0.99999934, 0.99999934, 0.99999934, 0.9999994, 0.9999994, 0.9999994, 0.9999994, 0.99999946, 0.99999946, 0.99999946, 0.9999995, 0.9999995, 0.9999995, 0.9999995, 0.9999995, 0.9999996, 0.9999996, 0.9999996, 0.9999996, 0.9999996, 0.99999964, 0.99999964, 0.99999964, 0.99999964, 0.99999964, 0.99999964, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.99999976, 0.99999976), banking=Strided(banks=1, stride=1)).name("x6747_d0_b0").srcCtx("NonLinearity.scala:48:37:lut") } // x6747 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x6747_d0_b0) = false
    bufferDepthOf(x6747_d0_b0) = 1
    staticDimsOf(x6747_d0_b0) = List(1024)
    val x6748 = withCtrl(x6762) { OpDef(op=FixSub, inputs=List(x6746, Const(-4.0))).name("x6748").srcCtx("NonLinearity.scala:50:22") } // FixSub(x6746,Const(-4))
    val x6749 = withCtrl(x6762) { OpDef(op=FixMul, inputs=List(x6748, Const(-128.0))).name("x6749").srcCtx("NonLinearity.scala:50:30") } // FixMul(x6748,Const(-128))
    // x6750 = FixConvert(x6749,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x6750 = withCtrl(x6762) { OpDef(op=FixSra, inputs=List(x6749, Const("24"))).name("x6750").srcCtx("NonLinearity.scala:50:41:index") } // FixConvert(x6749,TRUE,_32,_0)
    // }
    val x6751 = withCtrl(x6762) { LoadBanks(List(x6747_d0_b0), List(x6750)).name("x6751").srcCtx("NonLinearity.scala:51:17:sigVal") } // LUTLoad(x6747,List(x6750),x6744)
    val x6752_d0_b0 = withCtrl(x6762) { LUT(inits=List(-0.99999976, -0.99999976, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.9999997, -0.99999964, -0.99999964, -0.99999964, -0.99999964, -0.99999964, -0.99999964, -0.9999996, -0.9999996, -0.9999996, -0.9999996, -0.9999996, -0.9999995, -0.9999995, -0.9999995, -0.9999995, -0.9999995, -0.99999946, -0.99999946, -0.99999946, -0.9999994, -0.9999994, -0.9999994, -0.9999994, -0.99999934, -0.99999934, -0.99999934, -0.9999993, -0.9999993, -0.9999992, -0.9999992, -0.9999992, -0.99999917, -0.99999917, -0.9999991, -0.9999991, -0.9999991, -0.99999905, -0.99999905, -0.999999, -0.999999, -0.9999989, -0.99999887, -0.99999887, -0.9999988, -0.9999988, -0.99999875, -0.9999987, -0.9999987, -0.9999986, -0.99999857, -0.99999857, -0.9999985, -0.99999845, -0.9999984, -0.99999833, -0.99999833, -0.9999983, -0.9999982, -0.99999815, -0.9999981, -0.99999803, -0.999998, -0.9999979, -0.99999785, -0.99999774, -0.9999977, -0.9999976, -0.99999756, -0.9999975, -0.9999974, -0.9999973, -0.9999972, -0.99999714, -0.999997, -0.99999696, -0.99999684, -0.9999968, -0.99999666, -0.99999654, -0.9999964, -0.9999963, -0.9999962, -0.99999607, -0.99999595, -0.9999958, -0.9999957, -0.9999956, -0.9999954, -0.9999953, -0.9999952, -0.999995, -0.9999948, -0.9999947, -0.9999945, -0.99999434, -0.99999416, -0.999994, -0.99999374, -0.99999356, -0.9999934, -0.99999315, -0.99999297, -0.9999927, -0.9999925, -0.99999225, -0.999992, -0.9999918, -0.9999915, -0.99999124, -0.99999094, -0.99999064, -0.99999034, -0.99999005, -0.99998975, -0.9999894, -0.9999891, -0.99998873, -0.9999884, -0.999988, -0.99998766, -0.99998724, -0.9999868, -0.9999864, -0.999986, -0.9999855, -0.9999851, -0.9999846, -0.99998415, -0.9999836, -0.99998313, -0.9999826, -0.999982, -0.99998146, -0.99998087, -0.9999803, -0.9999796, -0.99997896, -0.9999783, -0.99997765, -0.99997693, -0.99997616, -0.99997544, -0.99997467, -0.99997383, -0.999973, -0.99997216, -0.9999713, -0.9999704, -0.9999694, -0.9999684, -0.99996746, -0.9999664, -0.9999653, -0.99996424, -0.9999631, -0.9999619, -0.9999607, -0.99995947, -0.99995816, -0.99995685, -0.9999555, -0.9999541, -0.9999526, -0.9999511, -0.9999496, -0.99994797, -0.9999463, -0.9999446, -0.99994284, -0.99994105, -0.99993914, -0.99993724, -0.9999352, -0.9999332, -0.99993104, -0.99992883, -0.9999266, -0.99992424, -0.99992186, -0.99991935, -0.9999168, -0.99991417, -0.9999114, -0.9999086, -0.99990577, -0.9999027, -0.9998996, -0.99989647, -0.9998932, -0.9998898, -0.9998863, -0.9998827, -0.99987894, -0.9998751, -0.99987113, -0.999867, -0.9998628, -0.99985844, -0.99985397, -0.9998493, -0.99984455, -0.9998396, -0.9998345, -0.99982923, -0.9998238, -0.9998182, -0.9998124, -0.99980646, -0.9998003, -0.999794, -0.99978745, -0.9997807, -0.99977374, -0.9997665, -0.99975914, -0.9997515, -0.9997436, -0.9997355, -0.9997271, -0.99971837, -0.9997094, -0.9997002, -0.99969065, -0.9996808, -0.9996707, -0.99966025, -0.99964947, -0.9996383, -0.9996268, -0.99961495, -0.99960274, -0.9995901, -0.9995771, -0.9995637, -0.9995498, -0.9995355, -0.9995208, -0.9995055, -0.99948984, -0.99947363, -0.9994569, -0.99943966, -0.99942183, -0.9994035, -0.9993845, -0.999365, -0.9993448, -0.999324, -0.9993025, -0.9992804, -0.9992575, -0.99923396, -0.99920964, -0.9991845, -0.9991586, -0.99913186, -0.9991043, -0.9990759, -0.9990465, -0.9990162, -0.998985, -0.99895275, -0.9989195, -0.9988852, -0.9988498, -0.9988133, -0.9987756, -0.9987367, -0.99869657, -0.9986552, -0.9986125, -0.9985685, -0.998523, -0.9984761, -0.9984277, -0.9983778, -0.9983263, -0.99827313, -0.99821836, -0.9981618, -0.99810344, -0.99804324, -0.99798113, -0.99791706, -0.9978509, -0.9977827, -0.9977123, -0.9976397, -0.99756485, -0.99748755, -0.9974078, -0.99732554, -0.9972407, -0.99715316, -0.9970628, -0.99696964, -0.9968735, -0.9967743, -0.996672, -0.9965664, -0.9964575, -0.99634516, -0.99622923, -0.99610966, -0.9959863, -0.995859, -0.9957277, -0.9955923, -0.9954525, -0.9953084, -0.9951597, -0.99500626, -0.994848, -0.99468476, -0.9945163, -0.99434257, -0.99416333, -0.99397844, -0.99378765, -0.9935909, -0.99338794, -0.99317855, -0.9929626, -0.9927398, -0.99250996, -0.99227285, -0.9920283, -0.99177605, -0.9915159, -0.9912475, -0.9909706, -0.99068505, -0.9903905, -0.9900866, -0.9897733, -0.98945004, -0.9891166, -0.98877275, -0.98841804, -0.98805225, -0.98767495, -0.9872858, -0.9868845, -0.9864705, -0.98604363, -0.9856034, -0.9851493, -0.98468107, -0.98419815, -0.98370016, -0.98318654, -0.98265696, -0.9821108, -0.9815476, -0.98096687, -0.980368, -0.9797506, -0.9791139, -0.97845733, -0.97778046, -0.9770825, -0.9763629, -0.975621, -0.9748561, -0.9740675, -0.9732545, -0.9724164, -0.97155243, -0.97066176, -0.9697436, -0.96879727, -0.9678217, -0.96681625, -0.96577984, -0.9647117, -0.9636108, -0.96247625, -0.961307, -0.9601021, -0.9588604, -0.9575809, -0.9562625, -0.9549041, -0.9535045, -0.95206255, -0.95057696, -0.94904655, -0.94747, -0.9458461, -0.9441734, -0.9424505, -0.94067615, -0.9388488, -0.93696696, -0.93502915, -0.9330338, -0.93097943, -0.9288643, -0.9266868, -0.92444533, -0.92213804, -0.9197632, -0.9173191, -0.91480386, -0.91221553, -0.90955234, -0.9068123, -0.9039934, -0.90109366, -0.89811105, -0.89504343, -0.89188874, -0.88864475, -0.88530934, -0.8818803, -0.8783553, -0.8747322, -0.8710085, -0.867182, -0.8632503, -0.85921097, -0.8550616, -0.85079974, -0.846423, -0.8419288, -0.8373146, -0.83257806, -0.82771647, -0.8227274, -0.8176083, -0.81235653, -0.80696964, -0.801445, -0.7957802, -0.78997254, -0.78401965, -0.77791893, -0.77166796, -0.7652642, -0.7587054, -0.75198907, -0.74511284, -0.7380745, -0.73087174, -0.72350246, -0.7159645, -0.7082558, -0.7003745, -0.69231856, -0.68408626, -0.6756759, -0.66708595, -0.6583149, -0.6493613, -0.640224, -0.6309019, -0.621394, -0.61169946, -0.60181767, -0.5917481, -0.5814904, -0.57104445, -0.5604102, -0.54958797, -0.53857803, -0.52738106, -0.5159979, -0.50442946, -0.4926771, -0.48074222, -0.4686265, -0.4563319, -0.44386053, -0.4312148, -0.41839743, -0.40541112, -0.39225912, -0.3789448, -0.36547172, -0.3518437, -0.3380649, -0.32413965, -0.31007248, -0.29586822, -0.28153187, -0.26706874, -0.25248426, -0.2377842, -0.22297436, -0.20806092, -0.19305009, -0.1779483, -0.16276228, -0.14749873, -0.1321646, -0.11676693, -0.101312935, -0.085809946, -0.07026523, -0.054686308, -0.03908074, -0.023456097, -0.007819951, 0.007819951, 0.023456097, 0.03908074, 0.054686308, 0.07026523, 0.085809946, 0.101312935, 0.11676693, 0.1321646, 0.14749873, 0.16276228, 0.1779483, 0.19305009, 0.20806092, 0.22297436, 0.2377842, 0.25248426, 0.26706874, 0.28153187, 0.29586822, 0.31007248, 0.32413965, 0.3380649, 0.3518437, 0.36547172, 0.3789448, 0.39225912, 0.40541112, 0.41839743, 0.4312148, 0.44386053, 0.4563319, 0.4686265, 0.48074222, 0.4926771, 0.50442946, 0.5159979, 0.52738106, 0.53857803, 0.54958797, 0.5604102, 0.57104445, 0.5814904, 0.5917481, 0.60181767, 0.61169946, 0.621394, 0.6309019, 0.640224, 0.6493613, 0.6583149, 0.66708595, 0.6756759, 0.68408626, 0.69231856, 0.7003745, 0.7082558, 0.7159645, 0.72350246, 0.73087174, 0.7380745, 0.74511284, 0.75198907, 0.7587054, 0.7652642, 0.77166796, 0.77791893, 0.78401965, 0.78997254, 0.7957802, 0.801445, 0.80696964, 0.81235653, 0.8176083, 0.8227274, 0.82771647, 0.83257806, 0.8373146, 0.8419288, 0.846423, 0.85079974, 0.8550616, 0.85921097, 0.8632503, 0.867182, 0.8710085, 0.8747322, 0.8783553, 0.8818803, 0.88530934, 0.88864475, 0.89188874, 0.89504343, 0.89811105, 0.90109366, 0.9039934, 0.9068123, 0.90955234, 0.91221553, 0.91480386, 0.9173191, 0.9197632, 0.92213804, 0.92444533, 0.9266868, 0.9288643, 0.93097943, 0.9330338, 0.93502915, 0.93696696, 0.9388488, 0.94067615, 0.9424505, 0.9441734, 0.9458461, 0.94747, 0.94904655, 0.95057696, 0.95206255, 0.9535045, 0.9549041, 0.9562625, 0.9575809, 0.9588604, 0.9601021, 0.961307, 0.96247625, 0.9636108, 0.9647117, 0.96577984, 0.96681625, 0.9678217, 0.96879727, 0.9697436, 0.97066176, 0.97155243, 0.9724164, 0.9732545, 0.9740675, 0.9748561, 0.975621, 0.9763629, 0.9770825, 0.97778046, 0.97845733, 0.9791139, 0.9797506, 0.980368, 0.98096687, 0.9815476, 0.9821108, 0.98265696, 0.98318654, 0.98370016, 0.98419815, 0.98468107, 0.9851493, 0.9856034, 0.98604363, 0.9864705, 0.9868845, 0.9872858, 0.98767495, 0.98805225, 0.98841804, 0.98877275, 0.9891166, 0.98945004, 0.9897733, 0.9900866, 0.9903905, 0.99068505, 0.9909706, 0.9912475, 0.9915159, 0.99177605, 0.9920283, 0.99227285, 0.99250996, 0.9927398, 0.9929626, 0.99317855, 0.99338794, 0.9935909, 0.99378765, 0.99397844, 0.99416333, 0.99434257, 0.9945163, 0.99468476, 0.994848, 0.99500626, 0.9951597, 0.9953084, 0.9954525, 0.9955923, 0.9957277, 0.995859, 0.9959863, 0.99610966, 0.99622923, 0.99634516, 0.9964575, 0.9965664, 0.996672, 0.9967743, 0.9968735, 0.99696964, 0.9970628, 0.99715316, 0.9972407, 0.99732554, 0.9974078, 0.99748755, 0.99756485, 0.9976397, 0.9977123, 0.9977827, 0.9978509, 0.99791706, 0.99798113, 0.99804324, 0.99810344, 0.9981618, 0.99821836, 0.99827313, 0.9983263, 0.9983778, 0.9984277, 0.9984761, 0.998523, 0.9985685, 0.9986125, 0.9986552, 0.99869657, 0.9987367, 0.9987756, 0.9988133, 0.9988498, 0.9988852, 0.9989195, 0.99895275, 0.998985, 0.9990162, 0.9990465, 0.9990759, 0.9991043, 0.99913186, 0.9991586, 0.9991845, 0.99920964, 0.99923396, 0.9992575, 0.9992804, 0.9993025, 0.999324, 0.9993448, 0.999365, 0.9993845, 0.9994035, 0.99942183, 0.99943966, 0.9994569, 0.99947363, 0.99948984, 0.9995055, 0.9995208, 0.9995355, 0.9995498, 0.9995637, 0.9995771, 0.9995901, 0.99960274, 0.99961495, 0.9996268, 0.9996383, 0.99964947, 0.99966025, 0.9996707, 0.9996808, 0.99969065, 0.9997002, 0.9997094, 0.99971837, 0.9997271, 0.9997355, 0.9997436, 0.9997515, 0.99975914, 0.9997665, 0.99977374, 0.9997807, 0.99978745, 0.999794, 0.9998003, 0.99980646, 0.9998124, 0.9998182, 0.9998238, 0.99982923, 0.9998345, 0.9998396, 0.99984455, 0.9998493, 0.99985397, 0.99985844, 0.9998628, 0.999867, 0.99987113, 0.9998751, 0.99987894, 0.9998827, 0.9998863, 0.9998898, 0.9998932, 0.99989647, 0.9998996, 0.9999027, 0.99990577, 0.9999086, 0.9999114, 0.99991417, 0.9999168, 0.99991935, 0.99992186, 0.99992424, 0.9999266, 0.99992883, 0.99993104, 0.9999332, 0.9999352, 0.99993724, 0.99993914, 0.99994105, 0.99994284, 0.9999446, 0.9999463, 0.99994797, 0.9999496, 0.9999511, 0.9999526, 0.9999541, 0.9999555, 0.99995685, 0.99995816, 0.99995947, 0.9999607, 0.9999619, 0.9999631, 0.99996424, 0.9999653, 0.9999664, 0.99996746, 0.9999684, 0.9999694, 0.9999704, 0.9999713, 0.99997216, 0.999973, 0.99997383, 0.99997467, 0.99997544, 0.99997616, 0.99997693, 0.99997765, 0.9999783, 0.99997896, 0.9999796, 0.9999803, 0.99998087, 0.99998146, 0.999982, 0.9999826, 0.99998313, 0.9999836, 0.99998415, 0.9999846, 0.9999851, 0.9999855, 0.999986, 0.9999864, 0.9999868, 0.99998724, 0.99998766, 0.999988, 0.9999884, 0.99998873, 0.9999891, 0.9999894, 0.99998975, 0.99999005, 0.99999034, 0.99999064, 0.99999094, 0.99999124, 0.9999915, 0.9999918, 0.999992, 0.99999225, 0.9999925, 0.9999927, 0.99999297, 0.99999315, 0.9999934, 0.99999356, 0.99999374, 0.999994, 0.99999416, 0.99999434, 0.9999945, 0.9999947, 0.9999948, 0.999995, 0.9999952, 0.9999953, 0.9999954, 0.9999956, 0.9999957, 0.9999958, 0.99999595, 0.99999607, 0.9999962, 0.9999963, 0.9999964, 0.99999654, 0.99999666, 0.9999968, 0.99999684, 0.99999696, 0.999997, 0.99999714, 0.9999972, 0.9999973, 0.9999974, 0.9999975, 0.99999756, 0.9999976, 0.9999977, 0.99999774, 0.99999785, 0.9999979, 0.999998, 0.99999803, 0.9999981, 0.99999815, 0.9999982, 0.9999983, 0.99999833, 0.99999833, 0.9999984, 0.99999845, 0.9999985, 0.99999857, 0.99999857, 0.9999986, 0.9999987, 0.9999987, 0.99999875, 0.9999988, 0.9999988, 0.99999887, 0.99999887, 0.9999989, 0.999999, 0.999999, 0.99999905, 0.99999905, 0.9999991, 0.9999991, 0.9999991, 0.99999917, 0.99999917, 0.9999992, 0.9999992, 0.9999992, 0.9999993, 0.9999993, 0.99999934, 0.99999934, 0.99999934, 0.9999994, 0.9999994, 0.9999994, 0.9999994, 0.99999946, 0.99999946, 0.99999946, 0.9999995, 0.9999995, 0.9999995, 0.9999995, 0.9999995, 0.9999996, 0.9999996, 0.9999996, 0.9999996, 0.9999996, 0.99999964, 0.99999964, 0.99999964, 0.99999964, 0.99999964, 0.99999964, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.9999997, 0.99999976, 0.99999976), banking=Strided(banks=1, stride=1)).name("x6752_d0_b0").srcCtx("NonLinearity.scala:36:37:lut") } // x6752 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x6752_d0_b0) = false
    bufferDepthOf(x6752_d0_b0) = 1
    staticDimsOf(x6752_d0_b0) = List(1024)
    val x6753 = withCtrl(x6762) { LoadBanks(List(x6752_d0_b0), List(x6750)).name("x6753").srcCtx("NonLinearity.scala:39:17:actVal") } // LUTLoad(x6752,List(x6750),x6744)
    val x6754 = withCtrl(x6762) { OpDef(op=FixLt, inputs=List(Const(8.0), x6746)).name("x6754").srcCtx("LSTMPipe.scala:77:35:isHigh") } // FixLt(Const(8),x6746)
    val x6755 = withCtrl(x6762) { OpDef(op=FixLt, inputs=List(x6746, Const(-8.0))).name("x6755").srcCtx("LSTMPipe.scala:78:34:isLow") } // FixLt(x6746,Const(-8))
    val x6756 = withCtrl(x6762) { OpDef(op=MuxOp, inputs=List(x6755, Const(0.0), x6751)).name("x6756").srcCtx("LSTMPipe.scala:80:54") } // Mux(x6755,Const(0),x6751)
    val x6757 = withCtrl(x6762) { OpDef(op=MuxOp, inputs=List(x6754, Const(1.0), x6756)).name("x6757").srcCtx("LSTMPipe.scala:80:33:sigEle") } // Mux(x6754,Const(1),x6756)
    val x6758 = withCtrl(x6762) { OpDef(op=MuxOp, inputs=List(x6755, Const(-1.0), x6753)).name("x6758").srcCtx("LSTMPipe.scala:81:54") } // Mux(x6755,Const(-1),x6753)
    val x6759 = withCtrl(x6762) { OpDef(op=MuxOp, inputs=List(x6754, Const(1.0), x6758)).name("x6759").srcCtx("LSTMPipe.scala:81:33:actEle") } // Mux(x6754,Const(1),x6758)
    val x6760_x6736 = withCtrl(x6762) { WriteMem(x6736, x6757).name("x6760_x6736").srcCtx("LSTMPipe.scala:83:25") } // ParFIFOEnq(x6736,List(x6757),List(x6744))
    val x6761_x6737 = withCtrl(x6762) { WriteMem(x6737, x6759).name("x6761_x6737").srcCtx("LSTMPipe.scala:84:25") } // ParFIFOEnq(x6737,List(x6759),List(x6744))
    val x6815 = withCtrl(x6816) { UnitController(style=SeqPipe, level=OuterControl).name("x6815").srcCtx("LSTMPipe.scala:87:20") } // UnitPipe(List(b3750, b3495, b3491),Block(Const(())))
    val x6763 = withCtrl(x6815) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6763").srcCtx("LSTMPipe.scala:89:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6764 = withCtrl(x6815) { CounterChain(List(x6763)).name("x6764").srcCtx("LSTMPipe.scala:89:58") } // CounterChainNew(List(x6763))
    val x6801 = withCtrl(x6815) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6764).name("x6801").srcCtx("LSTMPipe.scala:89:58") } // UnrolledForeach(List(b3750, b3495, b3491),x6764,Block(Const(())),List(List(b3782)),List(List(b3783)))
    val b3782 = withCtrl(x6801) { CounterIter(x6763, None).name("b3782") } // b3782
    val b3783 = withCtrl(x6801) { Const(true).name("b3783") } // b3783
    val x6765 = withCtrl(x6801) { OpDef(op=BitAnd, inputs=List(b3783, b3750)).name("x6765").srcCtx("UnrollingBase.scala:28:66") } // And(b3783,b3750)
    val x6766 = withCtrl(x6801) { OpDef(op=BitAnd, inputs=List(b3495, b3491)).name("x6766").srcCtx("UnrollingBase.scala:28:66") } // And(b3495,b3491)
    val x6767 = withCtrl(x6801) { OpDef(op=BitAnd, inputs=List(x6765, x6766)).name("x6767").srcCtx("UnrollingBase.scala:28:66") } // And(x6765,x6766)
    val x6768 = withCtrl(x6801) { ReadMem(x6736).name("x6768").srcCtx("LSTMPipe.scala:90:40:sigEle") } // ParFIFODeq(x6736,List(x6767))
    val x6769 = withCtrl(x6801) { x6768 } // VectorApply(x6768,0)
    val x6770 = withCtrl(x6801) { ReadMem(x6737).name("x6770").srcCtx("LSTMPipe.scala:91:40:actEle") } // ParFIFODeq(x6737,List(x6767))
    val x6771 = withCtrl(x6801) { x6770 } // VectorApply(x6770,0)
    val x6772 = withCtrl(x6801) { LoadBanks(List(x6305_d0_b0), List(b3494, b3782)).name("x6772").srcCtx("LSTMPipe.scala:93:32:cLast") } // ParSRAMLoad(x6305,List(List(b3494, b3782)),List(x6767))
    val x6773 = withCtrl(x6801) { x6772 } // VectorApply(x6772,0)
    val x6774 = withCtrl(x6801) { OpDef(op=FixMul, inputs=List(x6773, x6771)).name("x6774").srcCtx("LSTMPipe.scala:94:40:cNewMult") } // FixMul(x6773,x6771)
    val x6775 = withCtrl(x6801) { OpDef(op=FixMul, inputs=List(x6769, x6773)).name("x6775").srcCtx("LSTMPipe.scala:95:44") } // FixMul(x6769,x6773)
    val x6776 = withCtrl(x6801) { OpDef(op=FixAdd, inputs=List(x6775, x6773)).name("x6776").srcCtx("LSTMPipe.scala:95:52:cNewMultAdd") } // FixAdd(x6775,x6773)
    val x6777 = withCtrl(x6801) { OpDef(op=FixEql, inputs=List(b3749, Const(0))).name("x6777").srcCtx("LSTMPipe.scala:11:38") } // FixEql(b3749,Const(0))
    val x6778 = withCtrl(x6801) { OpDef(op=FixEql, inputs=List(b3749, Const(2))).name("x6778").srcCtx("LSTMPipe.scala:17:38") } // FixEql(b3749,Const(2))
    val x6779 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6778, x6776, x6773)).name("x6779").srcCtx("LSTMPipe.scala:99:26") } // Mux(x6778,x6776,x6773)
    val x6780 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6777, x6774, x6779)).name("x6780").srcCtx("LSTMPipe.scala:98:24") } // Mux(x6777,x6774,x6779)
    val x6781 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6777, x6769, x6780)).name("x6781").srcCtx("LSTMPipe.scala:97:36:cUpdate") } // Mux(x6777,x6769,x6780)
    val x6782 = withCtrl(x6801) { OpDef(op=FixAbs, inputs=List(x6776)).name("x6782").srcCtx("NonLinearity.scala:99:20:absin") } // FixAbs(x6776)
    val x6783 = withCtrl(x6801) { OpDef(op=FixSra, inputs=List(x6782, Const(2))).name("x6783").srcCtx("NonLinearity.scala:100:22:div4") } // FixRsh(x6782,Const(2))
    val x6784 = withCtrl(x6801) { OpDef(op=FixAdd, inputs=List(x6783, Const(0.375))).name("x6784").srcCtx("NonLinearity.scala:101:19:li") } // FixAdd(x6783,Const(0.375))
    val x6785 = withCtrl(x6801) { OpDef(op=FixLt, inputs=List(Const(2.5), x6782)).name("x6785").srcCtx("NonLinearity.scala:102:28") } // FixLt(Const(2.5),x6782)
    val x6786 = withCtrl(x6801) { OpDef(op=FixLt, inputs=List(Const(0.5), x6782)).name("x6786").srcCtx("NonLinearity.scala:103:14") } // FixLt(Const(0.5),x6782)
    val x6787 = withCtrl(x6801) { OpDef(op=FixLt, inputs=List(x6782, Const(2.5))).name("x6787").srcCtx("NonLinearity.scala:103:31") } // FixLt(x6782,Const(2.5))
    val x6788 = withCtrl(x6801) { OpDef(op=BitAnd, inputs=List(x6786, x6787)).name("x6788").srcCtx("NonLinearity.scala:103:22") } // And(x6786,x6787)
    val x6789 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6788, x6784, x6782)).name("x6789").srcCtx("NonLinearity.scala:103:10") } // Mux(x6788,x6784,x6782)
    val x6790 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6785, Const(1.0), x6789)).name("x6790").srcCtx("NonLinearity.scala:102:21:absout") } // Mux(x6785,Const(1),x6789)
    val x6791 = withCtrl(x6801) { OpDef(op=FixNeg, inputs=List(x6790)).name("x6791").srcCtx("NonLinearity.scala:106:23:negout") } // FixNeg(x6790)
    val x6792 = withCtrl(x6801) { OpDef(op=FixLt, inputs=List(x6776, Const(0.0))).name("x6792").srcCtx("NonLinearity.scala:107:12") } // FixLt(x6776,Const(0))
    val x6793 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6792, x6791, x6790)).name("x6793").srcCtx("NonLinearity.scala:107:8") } // Mux(x6792,x6791,x6790)
    val x6794 = withCtrl(x6801) { OpDef(op=FixAdd, inputs=List(x6793, x6769)).name("x6794").srcCtx("LSTMPipe.scala:102:54:hNew") } // FixAdd(x6793,x6769)
    val x6795 = withCtrl(x6801) { LoadBanks(List(x6340_d0_b0), List(b3494, b3782)).name("x6795").srcCtx("LSTMPipe.scala:103:32:hLast") } // ParSRAMLoad(x6340,List(List(b3494, b3782)),List(x6767))
    val x6796 = withCtrl(x6801) { x6795 } // VectorApply(x6795,0)
    val x6797 = withCtrl(x6801) { OpDef(op=FixEql, inputs=List(b3749, Const(3))).name("x6797").srcCtx("LSTMPipe.scala:20:38") } // FixEql(b3749,Const(3))
    val x6798 = withCtrl(x6801) { OpDef(op=MuxOp, inputs=List(x6797, x6794, x6796)).name("x6798").srcCtx("LSTMPipe.scala:104:36:hUpdate") } // Mux(x6797,x6794,x6796)
    val x6799_x6739 = withCtrl(x6801) { WriteMem(x6739, x6781).name("x6799_x6739").srcCtx("LSTMPipe.scala:106:31") } // ParFIFOEnq(x6739,List(x6781),List(x6767))
    val x6800_x6738 = withCtrl(x6801) { WriteMem(x6738, x6798).name("x6800_x6738").srcCtx("LSTMPipe.scala:107:31") } // ParFIFOEnq(x6738,List(x6798),List(x6767))
    val x6802 = withCtrl(x6815) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6802").srcCtx("LSTMPipe.scala:110:44") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6803 = withCtrl(x6815) { CounterChain(List(x6802)).name("x6803").srcCtx("LSTMPipe.scala:110:58") } // CounterChainNew(List(x6802))
    val x6814 = withCtrl(x6815) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6803).name("x6814").srcCtx("LSTMPipe.scala:110:58") } // UnrolledForeach(List(b3750, b3495, b3491),x6803,Block(Const(())),List(List(b3823)),List(List(b3824)))
    val b3823 = withCtrl(x6814) { CounterIter(x6802, None).name("b3823") } // b3823
    val b3824 = withCtrl(x6814) { Const(true).name("b3824") } // b3824
    val x6804 = withCtrl(x6814) { OpDef(op=BitAnd, inputs=List(b3824, b3750)).name("x6804").srcCtx("UnrollingBase.scala:28:66") } // And(b3824,b3750)
    val x6805 = withCtrl(x6814) { OpDef(op=BitAnd, inputs=List(b3495, b3491)).name("x6805").srcCtx("UnrollingBase.scala:28:66") } // And(b3495,b3491)
    val x6806 = withCtrl(x6814) { OpDef(op=BitAnd, inputs=List(x6804, x6805)).name("x6806").srcCtx("UnrollingBase.scala:28:66") } // And(x6804,x6805)
    val x6807 = withCtrl(x6814) { ReadMem(x6738).name("x6807").srcCtx("LSTMPipe.scala:111:39:hNew") } // ParFIFODeq(x6738,List(x6806))
    val x6808 = withCtrl(x6814) { x6807 } // VectorApply(x6807,0)
    val x6809 = withCtrl(x6814) { ReadMem(x6739).name("x6809").srcCtx("LSTMPipe.scala:112:39:cNew") } // ParFIFODeq(x6739,List(x6806))
    val x6810 = withCtrl(x6814) { x6809 } // VectorApply(x6809,0)
    val x6811 = withCtrl(x6814) { StoreBanks(List(List(x6340_d0_b0), List(x6340_d1_b0)), List(b3494, b3823), x6808).name("x6811").srcCtx("LSTMPipe.scala:113:42") } // ParSRAMStore(x6340,List(List(b3494, b3823)),List(x6808),List(x6806))
    val x6812 = withCtrl(x6814) { StoreBanks(List(List(x6270_d0_b0), List(x6270_d1_b0)), List(b3490, b3823), x6808).name("x6812").srcCtx("LSTMPipe.scala:114:41") } // ParSRAMStore(x6270,List(List(b3490, b3823)),List(x6808),List(x6806))
    val x6813 = withCtrl(x6814) { StoreBanks(List(List(x6305_d0_b0)), List(b3494, b3823), x6810).name("x6813").srcCtx("LSTMPipe.scala:115:42") } // ParSRAMStore(x6305,List(List(b3494, b3823)),List(x6810),List(x6806))
    val x6820 = withCtrl(x6848) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6820").srcCtx("LSTMPipe.scala:123:47") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6821 = withCtrl(x6848) { CounterChain(List(x6820)).name("x6821").srcCtx("LSTMPipe.scala:123:47") } // CounterChainNew(List(x6820))
    val x6847 = withCtrl(x6848) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6821).name("x6847").srcCtx("LSTMPipe.scala:123:47") } // UnrolledForeach(List(Const(true)),x6821,Block(Const(())),List(List(b3843)),List(List(b3844)))
    val b3843 = withCtrl(x6847) { CounterIter(x6820, Some(0)).name("b3843") } // b3843
    val b3844 = withCtrl(x6847) { Const(true).name("b3844") } // b3844
    val b6883 = withCtrl(x6847) { StreamOut(field="offset").name("b6883").srcCtx("LSTMPipe.scala:123:47") } // x6822 = StreamOutNew(BurstCmdBus)
    isAccum(b6883) = false
    bufferDepthOf(b6883) = 1
    val b6884 = withCtrl(x6847) { StreamOut(field="size").name("b6884").srcCtx("LSTMPipe.scala:123:47") } // x6822 = StreamOutNew(BurstCmdBus)
    isAccum(b6884) = false
    bufferDepthOf(b6884) = 1
    val x6823 = withCtrl(x6847) { StreamOut(field="data").name("x6823").srcCtx("LSTMPipe.scala:123:47") } // x6823 = StreamOutNew(BurstFullDataBus())
    isAccum(x6823) = false
    bufferDepthOf(x6823) = 1
    val x6824 = withCtrl(x6847) { StreamIn(field="ack").name("x6824").srcCtx("LSTMPipe.scala:123:47") } // x6824 = StreamInNew(BurstAckBus)
    isAccum(x6824) = false
    bufferDepthOf(x6824) = 1
    val x6835 = withCtrl(x6847) { UnitController(style=SeqPipe, level=InnerControl).name("x6835").srcCtx("LSTMPipe.scala:123:47") } // UnitPipe(List(b3844),Block(x6834))
    val x6825 = withCtrl(x6835) { b3843 } // FixConvert(b3843,TRUE,_32,_0) (Same Type. No op)
    val x6826 = withCtrl(x6835) { OpDef(op=FixSla, inputs=List(x6825, Const(7))).name("x6826").srcCtx("LSTMPipe.scala:123:47") } // FixLsh(x6825,Const(7))
    val x6827 = withCtrl(x6835) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6828 = withCtrl(x6835) { OpDef(op=FixAdd, inputs=List(x6826, x6827)).name("x6828").srcCtx("LSTMPipe.scala:123:47") } // FixAdd(x6826,x6827)
    val x6829 = withCtrl(x6835) { OpDef(op=FixSla, inputs=List(x6828, Const(2))).name("x6829").srcCtx("LSTMPipe.scala:123:47") } // FixLsh(x6828,Const(2))
    val x6830 = withCtrl(x6835) { x6829 } // FixConvert(x6829,TRUE,_64,_0)
    val x6831 = withCtrl(x6835) { DramAddress(x6267).name("x6831").srcCtx("LSTMPipe.scala:123:47") } // GetDRAMAddress(x6267)
    val x6833_x6832 = withCtrl(x6835) { OpDef(op=FixAdd, inputs=List(x6830, x6831)).name("x6833_x6832").srcCtx("LSTMPipe.scala:123:47") } // FixAdd(x6830,x6831)
    // x6833 = SimpleStruct(ArrayBuffer((offset,x6832), (size,Const(512)), (isLoad,Const(false))))
    val x6834_b6885_b6883 = withCtrl(x6835) { WriteMem(b6883, x6833_x6832).name("x6834_b6885_b6883").srcCtx("LSTMPipe.scala:123:47") } // StreamWrite(x6822,x6833,b3844)
    val x6834_b6886_b6884 = withCtrl(x6835) { WriteMem(b6884, Const(512)).name("x6834_b6886_b6884").srcCtx("LSTMPipe.scala:123:47") } // StreamWrite(x6822,x6833,b3844)
    val x6836 = withCtrl(x6847) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6836").srcCtx("LSTMPipe.scala:123:47") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6837 = withCtrl(x6847) { CounterChain(List(x6836)).name("x6837").srcCtx("LSTMPipe.scala:123:47") } // CounterChainNew(List(x6836))
    val x6843 = withCtrl(x6847) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6837).name("x6843").srcCtx("LSTMPipe.scala:123:47") } // UnrolledForeach(List(b3844),x6837,Block(Const(())),List(List(b3861)),List(List(b3862)))
    val b3861 = withCtrl(x6843) { CounterIter(x6836, None).name("b3861") } // b3861
    val b3862 = withCtrl(x6843) { Const(true).name("b3862") } // b3862
    val x6838 = withCtrl(x6843) { OpDef(op=BitAnd, inputs=List(b3862, b3844)).name("x6838").srcCtx("UnrollingBase.scala:28:66") } // And(b3862,b3844)
    val x6839 = withCtrl(x6843) { LoadBanks(List(x6270_d0_b0), List(b3843, b3861)).name("x6839").srcCtx("LSTMPipe.scala:123:47") } // ParSRAMLoad(x6270,List(List(b3843, b3861)),List(x6838))
    val x6841_x6840 = withCtrl(x6843) { x6839 } // VectorApply(x6839,0)
    // x6841 = SimpleStruct(ArrayBuffer((_1,x6840), (_2,Const(true))))
    val x6842_x6842_x6823 = withCtrl(x6843) { WriteMem(x6823, x6841_x6840).name("x6842_x6842_x6823").srcCtx("LSTMPipe.scala:123:47") } // ParStreamWrite(x6823,List(x6841),List(x6838))
    val x6844 = withCtrl(x6847) { FringeDenseStore(dram=List(x6267), cmdStream=List(b6883, b6884), dataStream=List(x6823), ackStream=List(x6824)).name("x6844").srcCtx("LSTMPipe.scala:123:47") } // FringeDenseStore(x6267,x6822,x6823,x6824)
    val x6846 = withCtrl(x6847) { UnitController(style=SeqPipe, level=InnerControl).name("x6846").srcCtx("LSTMPipe.scala:123:47") } // UnitPipe(List(b3844),Block(Const(())))
    val x6845_x6845 = withCtrl(x6846) { ReadMem(x6824).name("x6845_x6845").srcCtx("LSTMPipe.scala:123:47") } // StreamRead(x6824,b3844)
    }; split1
    
  }
}
