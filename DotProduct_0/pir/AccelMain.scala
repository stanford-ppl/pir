import pir._
import pir.node._
import spade.param._
import prism.graph._

object AccelMain extends PIRApp {
  def staging(top:Top) = {
    import pirgenStaging._
    import top._
    top.name("DotProduct_0")
    def split1 = {
    val x126 = save("x126", DRAM("a").dims(List(1024)).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:23:20").name("a")) // [DRAM] x126 = DRAMHostNew(List(Const(1024)),Const(0))
    val x127 = save("x127", DRAM("b").dims(List(1024)).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:24:20").name("b")) // [DRAM] x127 = DRAMHostNew(List(Const(1024)),Const(0))
    val x128 = save("x128", argOut().inits(0).depth(1).dims(List()).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:25:21").name("out")) // [Reg] x128 = ArgOutNew(Const(0))
    val x44 = save("x44", createCtrl(schedule=Sequenced)(UnitController()).sctx("DotProduct.scala:29:11")) // [UnitController] x44 = AccelScope(Block(Const(())))
    val x131 = save("x131", Reg().inits(0).depth(1).dims(List()).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:30:24").name("accO_0").progorder(0)) // [Reg] x131 = RegNew(Const(0))
    val x132 = save("x132", Reg().inits(0).depth(1).dims(List()).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:30:24").name("accO_1").progorder(0)) // [Reg] x132 = RegNew(Const(0))
    val x133 = save("x133", Counter(par=1).min(Const(0).tp(Fix(true, 32, 0))).step(Const(32).tp(Fix(true, 32, 0))).max(Const(1024).tp(Fix(true, 32, 0))).sctx("DotProduct.scala:31:35").progorder(1)) // [Counter] x133 = CounterNew(Const(0),Const(1024),Const(32),Const(1))
    val x134 = save("x134", List(x133).sctx("DotProduct.scala:39:8").progorder(2)) // [List[Counter]] x134 = CounterChainNew(List(x133))
    val x218 = save("x218", createCtrl(schedule=Pipelined)(LoopController().cchain(x134)).sctx("DotProduct.scala:39:8").progorder(60)) // [LoopController] x218 = UnrolledReduce(Set(),x134,Block((x131) => Const(())),List(List(b135)),List(List(b136)),None)
    val b135 = save("b135", CounterIter(List(0)).counter(x218.cchain.T(0)).resetParent(x218).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b135
    val b136 = save("b136", CounterValid(List(0)).counter(x218.cchain.T(0)).resetParent(x218).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b136
    }; split1
    def split2 = {
    val x137 = save("x137", SRAM().depth(2).dims(List(32)).banks(List(16)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:32:27").name("aBlk_0").progorder(61)) // [SRAM] x137 = SRAMNew(List(Const(32)),SRAM1[Fix[TRUE,_32,_0]])
    val x138 = save("x138", SRAM().depth(2).dims(List(32)).banks(List(16)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:33:27").name("bBlk_0").progorder(62)) // [SRAM] x138 = SRAMNew(List(Const(32)),SRAM1[Fix[TRUE,_32,_0]])
    val x187 = save("x187", createCtrl(schedule=ForkJoin)(UnitController().en(Set(lookup[CounterValid]("b136")))).sctx("DotProduct.scala:34:18").progorder(63)) // [UnitController] x187 = ParallelPipe(Set(b136),Block(Const(())))
    val x162 = save("x162", createCtrl(schedule=Streaming)(UnitController()).sctx("DotProduct.scala:35:16").progorder(64)) // [UnitController] x162 = UnitPipe(Set(),Block(Const(())),None)
    val x139_offset = save("x139_offset", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Fix(true, 64, 0)).isInnerAccum(false).sctx("DotProduct.scala:35:16").name("offset").progorder(65)) // [FIFO] x139_offset = StreamOutNew(BurstCmdBus)
    val x139_size = save("x139_size", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:35:16").name("size").progorder(65)) // [FIFO] x139_size = StreamOutNew(BurstCmdBus)
    val x139_isLoad = save("x139_isLoad", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Bool).isInnerAccum(false).sctx("DotProduct.scala:35:16").name("isLoad").progorder(65)) // [FIFO] x139_isLoad = StreamOutNew(BurstCmdBus)
    streamOut(List(x139_offset, x139_size, x139_isLoad), DRAMBus)
    val x140 = save("x140", FIFO().depth(1).dims(List(1)).banks(List(16)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:35:16").progorder(66)) // [FIFO] x140 = StreamInNew(BurstDataBus())
    streamIn(List(x140), DRAMBus)
    val x148 = save("x148", createCtrl(schedule=Sequenced)(UnitController()).sctx("DotProduct.scala:35:16").progorder(67)) // [UnitController] x148 = UnitPipe(Set(),Block(Const(())),None)
    val x141 = save("x141", OpDef(op=FixSLA).addInput(lookup[CounterIter]("b135"),Const(2).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:35:16").progorder(68)) // [OpDef] x141 = FixSLA(b135,Const(2))
    val x142 = save("x142", OpDef(op=FixToFix).addInput(x141).tp(Fix(true, 64, 0)).sctx("DotProduct.scala:35:16").progorder(69)) // [OpDef] x142 = FixToFix(x141,TRUE,_64,_0)
    }; split2
    def split3 = {
    val x143 = save("x143", dramAddress(lookup[DRAM]("x126")).name("x126_addr").tp(Fix(true, 64, 0)).sctx("DotProduct.scala:35:16").progorder(70)) // [MemRead] x143 = DRAMAddress(x126)
    val x144 = save("x144", OpDef(op=FixAdd).addInput(lookup[OpDef]("x142"),x143).tp(Fix(true, 64, 0)).sctx("DotProduct.scala:35:16").progorder(71)) // [OpDef] x144 = FixAdd(x142,x143)
    val x145_offset = save("x145_offset", x144) // [OpDef] x145_offset = SimpleStruct(ArrayBuffer((offset,x144), (size,Const(128)), (isLoad,Const(true))))
    val x145_size = save("x145_size", Const(128).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:35:16").name("size").progorder(72)) // [Const] x145_size = SimpleStruct(ArrayBuffer((offset,x144), (size,Const(128)), (isLoad,Const(true))))
    val x145_isLoad = save("x145_isLoad", Const(true).tp(Bool).sctx("DotProduct.scala:35:16").name("isLoad").progorder(72)) // [Const] x145_isLoad = SimpleStruct(ArrayBuffer((offset,x144), (size,Const(128)), (isLoad,Const(true))))
    val x146 = save("x146", Const(true).tp(Bool).sctx("DotProduct.scala:35:16").progorder(73)) // [Const] x146 = DRAMIsAlloc(x126)
    val x147_offset = save("x147_offset", MemWrite().setMem(lookup[FIFO]("x139_offset")).en(Set(x146)).data(x145_offset).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:35:16").name("offset").progorder(74)) // [MemWrite] x147_offset = StreamOutBankedWrite(x139,ArrayBuffer(x145),ArrayBuffer(Set(x146)))
    val x147_size = save("x147_size", MemWrite().setMem(lookup[FIFO]("x139_size")).en(Set(x146)).data(x145_size).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:35:16").name("size").progorder(74)) // [MemWrite] x147_size = StreamOutBankedWrite(x139,ArrayBuffer(x145),ArrayBuffer(Set(x146)))
    val x147_isLoad = save("x147_isLoad", MemWrite().setMem(lookup[FIFO]("x139_isLoad")).en(Set(x146)).data(x145_isLoad).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:35:16").name("isLoad").progorder(74)) // [MemWrite] x147_isLoad = StreamOutBankedWrite(x139,ArrayBuffer(x145),ArrayBuffer(Set(x146)))
    endState[Ctrl]
    val x149 = save("x149", FringeDenseLoad(lookup[DRAM]("x126")).offset(MemRead().setMem(lookup[FIFO]("x139_offset"))).size(MemRead().setMem(lookup[FIFO]("x139_size"))).data(MemWrite().setMem(lookup[FIFO]("x140")).data).sctx("DotProduct.scala:35:16").progorder(75)) // [FringeDenseLoad] x149 = FringeDenseLoad(x126,x139,x140)
    val x150 = save("x150", Counter(par=16).min(Const(0).tp(Fix(true, 32, 0))).step(Const(1).tp(Fix(true, 32, 0))).max(Const(32).tp(Fix(true, 32, 0))).sctx("DotProduct.scala:35:16").progorder(76)) // [Counter] x150 = CounterNew(Const(0),Const(32),Const(1),Const(16))
    }; split3
    def split4 = {
    val x151 = save("x151", List(lookup[Counter]("x150")).sctx("DotProduct.scala:35:16").progorder(77)) // [List[Counter]] x151 = CounterChainNew(List(x150))
    val x161 = save("x161", createCtrl(schedule=Pipelined)(LoopController().cchain(x151)).sctx("DotProduct.scala:35:16").progorder(78)) // [LoopController] x161 = UnrolledForeach(Set(),x151,Block(Const(())),List(List(b152)),List(List(b153)),None)
    val b152 = save("b152", CounterIter(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(x161.cchain.T(0)).resetParent(x161).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b152
    val b153 = save("b153", CounterValid(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(x161.cchain.T(0)).resetParent(x161).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b153
    val x154 = save("x154", MemRead().setMem(lookup[FIFO]("x140")).en(Set(b153)).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("DotProduct.scala:35:16").progorder(79)) // [MemRead] x154 = StreamInBankedRead(x140,ArrayBuffer(Set(b153)))
    val x155 = save("x155", x154) // [MemRead] x155 = VecApply(x154,0)
    val x156 = save("x156", Const(List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)).sctx("CounterIterRewriteRule.scala:21:17")) // [Const] x156 = LaneStatic(b152,List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
    val x157 = save("x157", OpDef(op=FixSRA).addInput(b152,Const(4).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:35:16")) // [OpDef] x157 = FixSRA(b152,Const(4))
    val x160 = save("x160", BankedWrite().bank(List(x156)).offset(x157).setMem(lookup[SRAM]("x137")).en(Set(b153)).data(x155).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("DotProduct.scala:35:16").progorder(80)) // [BankedWrite] x160 = SRAMBankedWrite(x137,Vector(x155),Vector(List(x156)),Vector(x157),Vector(Set(b153)))
    endState[Ctrl]
    endState[Ctrl]
    val x186 = save("x186", createCtrl(schedule=Streaming)(UnitController()).sctx("DotProduct.scala:36:16").progorder(81)) // [UnitController] x186 = UnitPipe(Set(),Block(Const(())),None)
    val x163_offset = save("x163_offset", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Fix(true, 64, 0)).isInnerAccum(false).sctx("DotProduct.scala:36:16").name("offset").progorder(82)) // [FIFO] x163_offset = StreamOutNew(BurstCmdBus)
    }; split4
    def split5 = {
    val x163_size = save("x163_size", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:36:16").name("size").progorder(82)) // [FIFO] x163_size = StreamOutNew(BurstCmdBus)
    val x163_isLoad = save("x163_isLoad", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Bool).isInnerAccum(false).sctx("DotProduct.scala:36:16").name("isLoad").progorder(82)) // [FIFO] x163_isLoad = StreamOutNew(BurstCmdBus)
    streamOut(List(lookup[FIFO]("x163_offset"), x163_size, x163_isLoad), DRAMBus)
    val x164 = save("x164", FIFO().depth(1).dims(List(1)).banks(List(16)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:36:16").progorder(83)) // [FIFO] x164 = StreamInNew(BurstDataBus())
    streamIn(List(x164), DRAMBus)
    val x172 = save("x172", createCtrl(schedule=Sequenced)(UnitController()).sctx("DotProduct.scala:36:16").progorder(84)) // [UnitController] x172 = UnitPipe(Set(),Block(Const(())),None)
    val x165 = save("x165", OpDef(op=FixSLA).addInput(lookup[CounterIter]("b135"),Const(2).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:36:16").progorder(85)) // [OpDef] x165 = FixSLA(b135,Const(2))
    val x166 = save("x166", OpDef(op=FixToFix).addInput(x165).tp(Fix(true, 64, 0)).sctx("DotProduct.scala:36:16").progorder(86)) // [OpDef] x166 = FixToFix(x165,TRUE,_64,_0)
    val x167 = save("x167", dramAddress(lookup[DRAM]("x127")).name("x127_addr").tp(Fix(true, 64, 0)).sctx("DotProduct.scala:36:16").progorder(87)) // [MemRead] x167 = DRAMAddress(x127)
    val x168 = save("x168", OpDef(op=FixAdd).addInput(x166,x167).tp(Fix(true, 64, 0)).sctx("DotProduct.scala:36:16").progorder(88)) // [OpDef] x168 = FixAdd(x166,x167)
    val x169_offset = save("x169_offset", x168) // [OpDef] x169_offset = SimpleStruct(ArrayBuffer((offset,x168), (size,Const(128)), (isLoad,Const(true))))
    val x169_size = save("x169_size", Const(128).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:36:16").name("size").progorder(89)) // [Const] x169_size = SimpleStruct(ArrayBuffer((offset,x168), (size,Const(128)), (isLoad,Const(true))))
    val x169_isLoad = save("x169_isLoad", Const(true).tp(Bool).sctx("DotProduct.scala:36:16").name("isLoad").progorder(89)) // [Const] x169_isLoad = SimpleStruct(ArrayBuffer((offset,x168), (size,Const(128)), (isLoad,Const(true))))
    }; split5
    def split6 = {
    val x170 = save("x170", Const(true).tp(Bool).sctx("DotProduct.scala:36:16").progorder(90)) // [Const] x170 = DRAMIsAlloc(x127)
    val x171_offset = save("x171_offset", MemWrite().setMem(lookup[FIFO]("x163_offset")).en(Set(x170)).data(lookup[OpDef]("x169_offset")).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:36:16").name("offset").progorder(91)) // [MemWrite] x171_offset = StreamOutBankedWrite(x163,ArrayBuffer(x169),ArrayBuffer(Set(x170)))
    val x171_size = save("x171_size", MemWrite().setMem(lookup[FIFO]("x163_size")).en(Set(x170)).data(lookup[Const]("x169_size")).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:36:16").name("size").progorder(91)) // [MemWrite] x171_size = StreamOutBankedWrite(x163,ArrayBuffer(x169),ArrayBuffer(Set(x170)))
    val x171_isLoad = save("x171_isLoad", MemWrite().setMem(lookup[FIFO]("x163_isLoad")).en(Set(x170)).data(lookup[Const]("x169_isLoad")).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:36:16").name("isLoad").progorder(91)) // [MemWrite] x171_isLoad = StreamOutBankedWrite(x163,ArrayBuffer(x169),ArrayBuffer(Set(x170)))
    endState[Ctrl]
    val x173 = save("x173", FringeDenseLoad(lookup[DRAM]("x127")).offset(MemRead().setMem(lookup[FIFO]("x163_offset"))).size(MemRead().setMem(lookup[FIFO]("x163_size"))).data(MemWrite().setMem(lookup[FIFO]("x164")).data).sctx("DotProduct.scala:36:16").progorder(92)) // [FringeDenseLoad] x173 = FringeDenseLoad(x127,x163,x164)
    val x174 = save("x174", Counter(par=16).min(Const(0).tp(Fix(true, 32, 0))).step(Const(1).tp(Fix(true, 32, 0))).max(Const(32).tp(Fix(true, 32, 0))).sctx("DotProduct.scala:36:16").progorder(93)) // [Counter] x174 = CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x175 = save("x175", List(x174).sctx("DotProduct.scala:36:16").progorder(94)) // [List[Counter]] x175 = CounterChainNew(List(x174))
    val x185 = save("x185", createCtrl(schedule=Pipelined)(LoopController().cchain(x175)).sctx("DotProduct.scala:36:16").progorder(95)) // [LoopController] x185 = UnrolledForeach(Set(),x175,Block(Const(())),List(List(b176)),List(List(b177)),None)
    val b176 = save("b176", CounterIter(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(x185.cchain.T(0)).resetParent(x185).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b176
    val b177 = save("b177", CounterValid(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(x185.cchain.T(0)).resetParent(x185).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b177
    val x178 = save("x178", MemRead().setMem(lookup[FIFO]("x164")).en(Set(b177)).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("DotProduct.scala:36:16").progorder(96)) // [MemRead] x178 = StreamInBankedRead(x164,ArrayBuffer(Set(b177)))
    }; split6
    def split7 = {
    val x179 = save("x179", lookup[MemRead]("x178")) // [MemRead] x179 = VecApply(x178,0)
    val x180 = save("x180", Const(List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)).sctx("CounterIterRewriteRule.scala:21:17")) // [Const] x180 = LaneStatic(b176,List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
    val x181 = save("x181", OpDef(op=FixSRA).addInput(lookup[CounterIter]("b176"),Const(4).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:36:16")) // [OpDef] x181 = FixSRA(b176,Const(4))
    val x184 = save("x184", BankedWrite().bank(List(x180)).offset(x181).setMem(lookup[SRAM]("x138")).en(Set(lookup[CounterValid]("b177"))).data(x179).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("DotProduct.scala:36:16").progorder(97)) // [BankedWrite] x184 = SRAMBankedWrite(x138,Vector(x179),Vector(List(x180)),Vector(x181),Vector(Set(b177)))
    endState[Ctrl]
    endState[Ctrl]
    endState[Ctrl]
    val x188 = save("x188", Reg().inits(0).depth(2).dims(List()).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("DotProduct.scala:38:25").progorder(98)) // [Reg] x188 = RegNew(Const(0))
    val x189 = save("x189", Reg().inits(0).depth(1).dims(List()).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(true).sctx("DotProduct.scala:38:25").progorder(98)) // [Reg] x189 = RegNew(Const(0))
    val x191 = save("x191", Counter(par=16).min(Const(0).tp(Fix(true, 32, 0))).step(Const(1).tp(Fix(true, 32, 0))).max(Const(32).tp(Fix(true, 32, 0))).sctx("DotProduct.scala:38:25").progorder(100)) // [Counter] x191 = CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x192 = save("x192", List(x191).sctx("DotProduct.scala:38:25").progorder(101)) // [List[Counter]] x192 = CounterChainNew(List(x191))
    val x210 = save("x210", createCtrl(schedule=Pipelined)(LoopController().cchain(x192).en(Set(lookup[CounterValid]("b136")))).sctx("DotProduct.scala:38:25").progorder(108)) // [LoopController] x210 = UnrolledReduce(Set(b136),x192,Block((x189) => Const(())),List(List(b193)),List(List(b194)),None)
    val b193 = save("b193", CounterIter(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(x210.cchain.T(0)).resetParent(x210).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b193
    val b194 = save("b194", CounterValid(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(x210.cchain.T(0)).resetParent(x210).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b194
    }; split7
    def split8 = {
    val x195 = save("x195", Const(List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)).sctx("CounterIterRewriteRule.scala:21:17")) // [Const] x195 = LaneStatic(b193,List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
    val x196 = save("x196", OpDef(op=FixSRA).addInput(lookup[CounterIter]("b193"),Const(4).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:38:38")) // [OpDef] x196 = FixSRA(b193,Const(4))
    val x199 = save("x199", BankedRead().bank(List(x195)).offset(x196).setMem(lookup[SRAM]("x137")).en(Set(lookup[CounterValid]("b194"), lookup[CounterValid]("b136"))).port(Some(1)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("DotProduct.scala:38:38").progorder(109)) // [BankedRead] x199 = SRAMBankedRead(x137,Vector(List(x195)),Vector(x196),Vector(Set(b194, b136)),Vec[Fix[TRUE,_32,_0]])
    val x200 = save("x200", x199) // [BankedRead] x200 = VecApply(x199,0)
    val x201 = save("x201", BankedRead().bank(List(x195)).offset(x196).setMem(lookup[SRAM]("x138")).en(Set(lookup[CounterValid]("b194"), lookup[CounterValid]("b136"))).port(Some(1)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("DotProduct.scala:38:48").progorder(110)) // [BankedRead] x201 = SRAMBankedRead(x138,Vector(List(x195)),Vector(x196),Vector(Set(b194, b136)),Vec[Fix[TRUE,_32,_0]])
    val x202 = save("x202", x201) // [BankedRead] x202 = VecApply(x201,0)
    val x203 = save("x203", OpDef(op=FixMul).addInput(x200,x202).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:38:25").progorder(111)) // [OpDef] x203 = FixMul(x200,x202)
    val x205 = save("x205", MemRead().setMem(lookup[Reg]("x189")).en(Set()).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).isInnerReduceOp(true).sctx("DotProduct.scala:38:25").progorder(112)) // [MemRead] x205 = RegRead(x189)
    val x207 = save("x207", OpDef(op=FixAdd).addInput(x203,x205).tp(Fix(true, 32, 0)).isInnerReduceOp(true).sctx("DotProduct.scala:38:25").progorder(113)) // [OpDef] x207 = FixAdd(x203,x205)
    val x208 = save("x208", MemWrite().setMem(lookup[Reg]("x189")).en(Set()).data(x207).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).isInnerReduceOp(true).sctx("DotProduct.scala:38:25").progorder(114)) // [MemWrite] x208 = RegWrite(x189,x207,Set())
    val x209 = save("x209", MemWrite().setMem(lookup[Reg]("x188")).en(Set()).data(x207).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).isInnerReduceOp(true).sctx("DotProduct.scala:38:25").progorder(114)) // [MemWrite] x209 = RegWrite(x188,x207,Set())
    }; split8
    def split9 = {
    endState[Ctrl]
    val x217 = save("x217", createCtrl(schedule=Sequenced)(UnitController()).sctx("DotProduct.scala:39:8")) // [UnitController] x217 = UnitPipe(Set(),Block(Const(())),None)
    val x211 = save("x211", MemRead().setMem(lookup[Reg]("x131")).en(Set()).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:39:8").progorder(115)) // [MemRead] x211 = RegRead(x131)
    val x212 = save("x212", OpDef(op=FixEql).addInput(lookup[CounterIter]("b135"),Const(0).tp(Fix(true, 32, 0))).tp(Bool).sctx("DotProduct.scala:39:8")) // [OpDef] x212 = FixEql(b135,Const(0))
    val x247 = save("x247", MemRead().setMem(lookup[Reg]("x188")).en(Set()).port(Some(1)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:38:25").progorder(99)) // [MemRead] x247 = RegRead(x188)
    val x213 = save("x213", OpDef(op=FixAdd).addInput(x247,x211).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:39:10").progorder(116)) // [OpDef] x213 = FixAdd(x247,x211)
    val x214 = save("x214", OpDef(op=Mux).addInput(x212,x247,x213).tp(Fix(true, 32, 0)).sctx("DotProduct.scala:39:8")) // [OpDef] x214 = Mux(x212,x247,x213)
    val x215 = save("x215", MemWrite().setMem(lookup[Reg]("x131")).en(Set()).data(x214).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:39:8").progorder(117)) // [MemWrite] x215 = RegWrite(x131,x214,Set())
    val x216 = save("x216", MemWrite().setMem(lookup[Reg]("x132")).en(Set()).data(x214).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:39:8").progorder(117)) // [MemWrite] x216 = RegWrite(x132,x214,Set())
    endState[Ctrl]
    endState[Ctrl]
    val x221 = save("x221", createCtrl(schedule=Sequenced)(UnitController()).sctx("PipeInserter.scala:176:18").progorder(118)) // [UnitController] x221 = UnitPipe(Set(),Block(Const(())),None)
    val x219 = save("x219", MemRead().setMem(lookup[Reg]("x132")).en(Set()).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:39:8").progorder(119)) // [MemRead] x219 = RegRead(x132)
    val x220 = save("x220", MemWrite().setMem(lookup[Reg]("x128")).en(Set()).data(x219).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("DotProduct.scala:31:11").progorder(120)) // [MemWrite] x220 = RegWrite(x128,x219,Set())
    }; split9
    def split10 = {
    endState[Ctrl]
    endState[Ctrl]
    }; split10
  }
}
