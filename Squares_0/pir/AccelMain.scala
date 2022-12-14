import pir._
import pir.node._
import spade.param._
import prism.graph._

object AccelMain extends PIRApp {
  def staging(top:Top) = {
    import pirgenStaging._
    import top._
    top.name("Squares_0")
    def split1 = {
    val x145 = save("x145", DRAM("rand_d").dims(List(131072)).tp(Fix(false, 32, 0)).parAllowed(1).sctx("Squares.scala:34:34").name("rand_d")) // [DRAM] x145 = DRAMHostNew(List(Const(131072)),Const(0))
    val x82 = save("x82", createCtrl(schedule=Sequenced)(UnitController()).sctx("Squares.scala:36:15")) // [UnitController] x82 = AccelScope(Block(Const(())))
    val x146 = save("x146", StridedCounter(par=1).min(Const(0).tp(Fix(true, 32, 0))).step(Const(8192).tp(Fix(true, 32, 0))).max(Const(131072).tp(Fix(true, 32, 0))).sctx("Squares.scala:37:37").progorder(0)) // [StridedCounter] x146 = CounterNew(Const(0),Const(131072),Const(8192),Const(1))
    val x147 = save("x147", List(x146).sctx("Squares.scala:37:44").progorder(1)) // [List[Counter]] x147 = CounterChainNew(List(x146))
    val x250 = save("x250", createCtrl(schedule=Pipelined)(LoopController().cchain(x147)).sctx("Squares.scala:37:44").progorder(2)) // [LoopController] x250 = UnrolledForeach(Set(),x147,Block(Const(())),List(List(b148)),List(List(b149)),List(),None)
    val b148 = save("b148", CounterIter(List(0)).counter(x250.cchain.T(0)).resetParent(x250).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b148
    val b149 = save("b149", CounterValid(List(0)).counter(x250.cchain.T(0)).resetParent(x250).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b149
    val x150 = save("x150", SRAM().depth(2).dims(List(8192)).banks(List(16)).tp(Fix(false, 32, 0)).isInnerAccum(false).sctx("Squares.scala:40:42").name("rand_s_0").progorder(3)) // [SRAM] x150 = SRAMNew(List(Const(8192)),SRAM1[Fix[FALSE,_32,_0]])
    val x151 = save("x151", StridedCounter(par=16).min(Const(0).tp(Fix(true, 32, 0))).step(Const(1).tp(Fix(true, 32, 0))).max(Const(8192).tp(Fix(true, 32, 0))).sctx("Squares.scala:42:39").progorder(4)) // [StridedCounter] x151 = CounterNew(Const(0),Const(8192),Const(1),Const(16))
    val x152 = save("x152", List(x151).sctx("Squares.scala:42:46").progorder(5)) // [List[Counter]] x152 = CounterChainNew(List(x151))
    val x220 = save("x220", createCtrl(schedule=Pipelined)(LoopController().cchain(x152).en(Set(b149))).sctx("Squares.scala:42:46").progorder(6)) // [LoopController] x220 = UnrolledForeach(Set(b149),x152,Block(Const(())),List(List(b153)),List(List(b154)),List(),None)
    }; split1
    def split2 = {
    val b153 = save("b153", CounterIter(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(lookup[LoopController]("x220").cchain.T(0)).resetParent(lookup[LoopController]("x220")).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b153
    val b154 = save("b154", CounterValid(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(lookup[LoopController]("x220").cchain.T(0)).resetParent(lookup[LoopController]("x220")).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b154
    val x155 = save("x155", OpDef(op=FixToFix).addInput(lookup[CounterIter]("b148")).tp(Fix(false, 32, 0)).sctx("Squares.scala:43:48").progorder(7)) // [OpDef] x155 = FixToFix(b148,FALSE,_32,_0)
    val x156 = save("x156", OpDef(op=FixToFix).addInput(b153).tp(Fix(false, 32, 0)).sctx("Squares.scala:43:63").progorder(8)) // [OpDef] x156 = FixToFix(b153,FALSE,_32,_0)
    val x157 = save("x157", OpDef(op=FixAdd).addInput(x155,x156).tp(Fix(false, 32, 0)).sctx("Squares.scala:43:57").name("_ctr_lo").progorder(9)) // [OpDef] x157 = FixAdd(x155,x156)
    val x158 = save("x158", OpDef(op=FixLst).addInput(x157,Const(0L).tp(Fix(false, 32, 0))).tp(Bool).sctx("Squares.scala:28:35").progorder(10)) // [OpDef] x158 = FixLst(x157,Const(0))
    val x159 = save("x159", OpDef(op=Mux).addInput(x158,Const(1L).tp(Fix(false, 32, 0)),Const(0L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:28:31").name("_ctr_hi").progorder(11)) // [OpDef] x159 = Mux(x158,Const(1),Const(0))
    val x160 = save("x160", OpDef(op=FixMulH).addInput(x157,Const(1289957229L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:14").name("ac_hi").progorder(12)) // [OpDef] x160 = FixMulH(x157,Const(1289957229))
    val x161 = save("x161", OpDef(op=FixMul).addInput(x157,Const(1289957229L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:24").name("y_lo").progorder(13)) // [OpDef] x161 = FixMul(x157,Const(1289957229))
    val x269 = save("x269", OpDef(op=FixFMA).addInput(x157,Const(3370450197L).tp(Fix(false, 32, 0)),x160).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").progorder(16)) // [OpDef] x269 = FixFMA(x157,Const(3370450197),x160)
    val x270 = save("x270", OpDef(op=FixFMA).addInput(x159,Const(1289957229L).tp(Fix(false, 32, 0)),x269).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").name("y_hi").progorder(17)) // [OpDef] x270 = FixFMA(x159,Const(1289957229),x269)
    }; split2
    def split3 = {
    val x166 = save("x166", OpDef(op=FixAdd).addInput(lookup[OpDef]("x161"),Const(1289957229L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:27:27").name("z_lo").progorder(18)) // [OpDef] x166 = FixAdd(x161,Const(1289957229))
    val x167 = save("x167", OpDef(op=FixLst).addInput(x166,lookup[OpDef]("x161")).tp(Bool).sctx("Squares.scala:28:35").progorder(19)) // [OpDef] x167 = FixLst(x166,x161)
    val x168 = save("x168", OpDef(op=Mux).addInput(x167,Const(1L).tp(Fix(false, 32, 0)),Const(0L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:28:31").name("carry").progorder(20)) // [OpDef] x168 = Mux(x167,Const(1),Const(0))
    val x169 = save("x169", OpDef(op=FixAdd).addInput(lookup[OpDef]("x270"),Const(3370450197L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:12").progorder(21)) // [OpDef] x169 = FixAdd(x270,Const(3370450197))
    val x170 = save("x170", OpDef(op=FixAdd).addInput(x169,x168).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:16").name("z_hi").progorder(22)) // [OpDef] x170 = FixAdd(x169,x168)
    val x171 = save("x171", OpDef(op=FixMulH).addInput(lookup[OpDef]("x161"),lookup[OpDef]("x161")).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:14").name("ac_hi").progorder(23)) // [OpDef] x171 = FixMulH(x161,x161)
    val x172 = save("x172", OpDef(op=FixMul).addInput(lookup[OpDef]("x161"),lookup[OpDef]("x161")).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:24").name("x_0_lo").progorder(24)) // [OpDef] x172 = FixMul(x161,x161)
    val x271 = save("x271", OpDef(op=FixFMA).addInput(lookup[OpDef]("x161"),lookup[OpDef]("x270"),x171).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").progorder(27)) // [OpDef] x271 = FixFMA(x161,x270,x171)
    val x272 = save("x272", OpDef(op=FixFMA).addInput(lookup[OpDef]("x270"),lookup[OpDef]("x161"),x271).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").name("x_0_hi").progorder(28)) // [OpDef] x272 = FixFMA(x270,x161,x271)
    val x177 = save("x177", OpDef(op=FixAdd).addInput(x172,lookup[OpDef]("x161")).tp(Fix(false, 32, 0)).sctx("Squares.scala:27:27").name("x_1_lo").progorder(29)) // [OpDef] x177 = FixAdd(x172,x161)
    val x178 = save("x178", OpDef(op=FixLst).addInput(x177,x172).tp(Bool).sctx("Squares.scala:28:35").progorder(30)) // [OpDef] x178 = FixLst(x177,x172)
    }; split3
    def split4 = {
    val x179 = save("x179", OpDef(op=Mux).addInput(lookup[OpDef]("x178"),Const(1L).tp(Fix(false, 32, 0)),Const(0L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:28:31").name("carry").progorder(31)) // [OpDef] x179 = Mux(x178,Const(1),Const(0))
    val x180 = save("x180", OpDef(op=FixAdd).addInput(lookup[OpDef]("x272"),lookup[OpDef]("x270")).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:12").progorder(32)) // [OpDef] x180 = FixAdd(x272,x270)
    val x181 = save("x181", OpDef(op=FixAdd).addInput(x180,x179).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:16").name("x_1_hi").progorder(33)) // [OpDef] x181 = FixAdd(x180,x179)
    val x182 = save("x182", OpDef(op=FixMulH).addInput(x181,x181).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:14").name("ac_hi").progorder(34)) // [OpDef] x182 = FixMulH(x181,x181)
    val x183 = save("x183", OpDef(op=FixMul).addInput(x181,x181).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:24").name("x_2_lo").progorder(35)) // [OpDef] x183 = FixMul(x181,x181)
    val x273 = save("x273", OpDef(op=FixFMA).addInput(x181,lookup[OpDef]("x177"),x182).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").progorder(38)) // [OpDef] x273 = FixFMA(x181,x177,x182)
    val x274 = save("x274", OpDef(op=FixFMA).addInput(lookup[OpDef]("x177"),x181,x273).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").name("x_2_hi").progorder(39)) // [OpDef] x274 = FixFMA(x177,x181,x273)
    val x188 = save("x188", OpDef(op=FixAdd).addInput(x183,lookup[OpDef]("x166")).tp(Fix(false, 32, 0)).sctx("Squares.scala:27:27").name("x_3_lo").progorder(40)) // [OpDef] x188 = FixAdd(x183,x166)
    val x189 = save("x189", OpDef(op=FixLst).addInput(x188,x183).tp(Bool).sctx("Squares.scala:28:35").progorder(41)) // [OpDef] x189 = FixLst(x188,x183)
    val x190 = save("x190", OpDef(op=Mux).addInput(x189,Const(1L).tp(Fix(false, 32, 0)),Const(0L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:28:31").name("carry").progorder(42)) // [OpDef] x190 = Mux(x189,Const(1),Const(0))
    val x191 = save("x191", OpDef(op=FixAdd).addInput(x274,lookup[OpDef]("x170")).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:12").progorder(43)) // [OpDef] x191 = FixAdd(x274,x170)
    }; split4
    def split5 = {
    val x192 = save("x192", OpDef(op=FixAdd).addInput(lookup[OpDef]("x191"),lookup[OpDef]("x190")).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:16").name("x_3_hi").progorder(44)) // [OpDef] x192 = FixAdd(x191,x190)
    val x193 = save("x193", OpDef(op=FixMulH).addInput(x192,x192).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:14").name("ac_hi").progorder(45)) // [OpDef] x193 = FixMulH(x192,x192)
    val x194 = save("x194", OpDef(op=FixMul).addInput(x192,x192).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:24").name("x_4_lo").progorder(46)) // [OpDef] x194 = FixMul(x192,x192)
    val x275 = save("x275", OpDef(op=FixFMA).addInput(x192,lookup[OpDef]("x188"),x193).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").progorder(49)) // [OpDef] x275 = FixFMA(x192,x188,x193)
    val x276 = save("x276", OpDef(op=FixFMA).addInput(lookup[OpDef]("x188"),x192,x275).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").name("x_4_hi").progorder(50)) // [OpDef] x276 = FixFMA(x188,x192,x275)
    val x199 = save("x199", OpDef(op=FixAdd).addInput(x194,lookup[OpDef]("x161")).tp(Fix(false, 32, 0)).sctx("Squares.scala:27:27").name("x_5_lo").progorder(51)) // [OpDef] x199 = FixAdd(x194,x161)
    val x200 = save("x200", OpDef(op=FixLst).addInput(x199,x194).tp(Bool).sctx("Squares.scala:28:35").progorder(52)) // [OpDef] x200 = FixLst(x199,x194)
    val x201 = save("x201", OpDef(op=Mux).addInput(x200,Const(1L).tp(Fix(false, 32, 0)),Const(0L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:28:31").name("carry").progorder(53)) // [OpDef] x201 = Mux(x200,Const(1),Const(0))
    val x202 = save("x202", OpDef(op=FixAdd).addInput(x276,lookup[OpDef]("x270")).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:12").progorder(54)) // [OpDef] x202 = FixAdd(x276,x270)
    val x203 = save("x203", OpDef(op=FixAdd).addInput(x202,x201).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:16").name("x_5_hi").progorder(55)) // [OpDef] x203 = FixAdd(x202,x201)
    val x204 = save("x204", OpDef(op=FixMulH).addInput(x203,x203).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:14").name("ac_hi").progorder(56)) // [OpDef] x204 = FixMulH(x203,x203)
    }; split5
    def split6 = {
    val x205 = save("x205", OpDef(op=FixMul).addInput(lookup[OpDef]("x203"),lookup[OpDef]("x203")).tp(Fix(false, 32, 0)).sctx("Squares.scala:16:24").name("x_6_lo").progorder(57)) // [OpDef] x205 = FixMul(x203,x203)
    val x277 = save("x277", OpDef(op=FixFMA).addInput(lookup[OpDef]("x203"),lookup[OpDef]("x199"),lookup[OpDef]("x204")).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").progorder(60)) // [OpDef] x277 = FixFMA(x203,x199,x204)
    val x278 = save("x278", OpDef(op=FixFMA).addInput(lookup[OpDef]("x199"),lookup[OpDef]("x203"),x277).tp(Fix(false, 32, 0)).sctx("RewriteTransformer.scala:56:10").name("x_6_hi").progorder(61)) // [OpDef] x278 = FixFMA(x199,x203,x277)
    val x210 = save("x210", OpDef(op=FixAdd).addInput(x205,lookup[OpDef]("x166")).tp(Fix(false, 32, 0)).sctx("Squares.scala:27:27").name("x_7_lo").progorder(62)) // [OpDef] x210 = FixAdd(x205,x166)
    val x211 = save("x211", OpDef(op=FixLst).addInput(x210,x205).tp(Bool).sctx("Squares.scala:28:35").progorder(63)) // [OpDef] x211 = FixLst(x210,x205)
    val x212 = save("x212", OpDef(op=Mux).addInput(x211,Const(1L).tp(Fix(false, 32, 0)),Const(0L).tp(Fix(false, 32, 0))).tp(Fix(false, 32, 0)).sctx("Squares.scala:28:31").name("carry").progorder(64)) // [OpDef] x212 = Mux(x211,Const(1),Const(0))
    val x213 = save("x213", OpDef(op=FixAdd).addInput(x278,lookup[OpDef]("x170")).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:12").progorder(65)) // [OpDef] x213 = FixAdd(x278,x170)
    val x214 = save("x214", OpDef(op=FixAdd).addInput(x213,x212).tp(Fix(false, 32, 0)).sctx("Squares.scala:29:16").name("x_7_hi").progorder(66)) // [OpDef] x214 = FixAdd(x213,x212)
    val x215 = save("x215", Const(List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)).sctx("CounterIterRewriteRule.scala:21:17")) // [Const] x215 = LaneStatic(b153,List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
    val x216 = save("x216", OpDef(op=FixSRA).addInput(lookup[CounterIter]("b153"),Const(4).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("Squares.scala:69:31")) // [OpDef] x216 = FixSRA(b153,Const(4))
    val x219 = save("x219", BankedWrite().bank(List(x215)).offset(x216).setMem(lookup[SRAM]("x150")).en(Set(lookup[CounterValid]("b154"), lookup[CounterValid]("b149"))).data(x214).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("Squares.scala:69:31").progorder(67)) // [BankedWrite] x219 = SRAMBankedWrite(x150,Vector(x214),Vector(List(x215)),Vector(x216),Vector(Set(b154, b149)))
    }; split6
    def split7 = {
    endState[Ctrl]
    val x249 = save("x249", createCtrl(schedule=Streaming)(UnitController().en(Set(lookup[CounterValid]("b149")))).sctx("Squares.scala:73:52").progorder(68)) // [UnitController] x249 = UnitPipe(Set(b149),Block(Const(())),None)
    val x221_offset = save("x221_offset", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Fix(true, 64, 0)).isInnerAccum(false).sctx("Squares.scala:73:52").name("offset").progorder(69)) // [FIFO] x221_offset = StreamOutNew(BurstCmdBus)
    val x221_size = save("x221_size", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Fix(true, 32, 0)).isInnerAccum(false).sctx("Squares.scala:73:52").name("size").progorder(69)) // [FIFO] x221_size = StreamOutNew(BurstCmdBus)
    val x221_isLoad = save("x221_isLoad", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Bool).isInnerAccum(false).sctx("Squares.scala:73:52").name("isLoad").progorder(69)) // [FIFO] x221_isLoad = StreamOutNew(BurstCmdBus)
    streamOut(List(x221_offset, x221_size, x221_isLoad), DRAMBus)
    val x222__1 = save("x222__1", FIFO().depth(1).dims(List(1)).banks(List(16)).tp(Fix(false, 32, 0)).isInnerAccum(false).sctx("Squares.scala:73:52").name("_1").progorder(70)) // [FIFO] x222__1 = StreamOutNew(BurstFullDataBus())
    val x222__2 = save("x222__2", FIFO().depth(1).dims(List(1)).banks(List(16)).tp(Bool).isInnerAccum(false).sctx("Squares.scala:73:52").name("_2").progorder(70)) // [FIFO] x222__2 = StreamOutNew(BurstFullDataBus())
    streamOut(List(x222__1, x222__2), DRAMBus)
    val x223 = save("x223", FIFO().depth(1).dims(List(1)).banks(List(1)).tp(Bool).isInnerAccum(false).sctx("Squares.scala:73:52").progorder(71)) // [FIFO] x223 = StreamInNew(BurstAckBus)
    streamIn(List(x223), DRAMBus)
    val x231 = save("x231", createCtrl(schedule=Sequenced)(UnitController()).sctx("Squares.scala:73:52").progorder(72)) // [UnitController] x231 = UnitPipe(Set(),Block(Const(())),None)
    val x224 = save("x224", OpDef(op=FixSLA).addInput(lookup[CounterIter]("b148"),Const(2).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("Squares.scala:73:52").progorder(73)) // [OpDef] x224 = FixSLA(b148,Const(2))
    val x225 = save("x225", OpDef(op=FixToFix).addInput(x224).tp(Fix(true, 64, 0)).sctx("Squares.scala:73:52").progorder(74)) // [OpDef] x225 = FixToFix(x224,TRUE,_64,_0)
    val x226 = save("x226", dramAddress(lookup[DRAM]("x145")).name("x145_addr").tp(Fix(true, 64, 0)).sctx("Squares.scala:73:52").progorder(75)) // [MemRead] x226 = DRAMAddress(x145)
    }; split7
    def split8 = {
    val x227 = save("x227", OpDef(op=FixAdd).addInput(lookup[OpDef]("x225"),lookup[MemRead]("x226")).tp(Fix(true, 64, 0)).sctx("Squares.scala:73:52").progorder(76)) // [OpDef] x227 = FixAdd(x225,x226)
    val x228_offset = save("x228_offset", x227) // [OpDef] x228_offset = SimpleStruct(ArrayBuffer((offset,x227), (size,Const(32768)), (isLoad,Const(false))))
    val x228_size = save("x228_size", Const(32768).tp(Fix(true, 32, 0)).sctx("Squares.scala:73:52").name("size").progorder(77)) // [Const] x228_size = SimpleStruct(ArrayBuffer((offset,x227), (size,Const(32768)), (isLoad,Const(false))))
    val x228_isLoad = save("x228_isLoad", Const(false).tp(Bool).sctx("Squares.scala:73:52").name("isLoad").progorder(77)) // [Const] x228_isLoad = SimpleStruct(ArrayBuffer((offset,x227), (size,Const(32768)), (isLoad,Const(false))))
    val x229 = save("x229", Const(true).tp(Bool).sctx("Squares.scala:73:52").progorder(78)) // [Const] x229 = DRAMIsAlloc(x145)
    val x230_offset = save("x230_offset", MemWrite().setMem(lookup[FIFO]("x221_offset")).en(Set(x229)).data(x228_offset).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("Squares.scala:73:52").name("offset").progorder(79)) // [MemWrite] x230_offset = StreamOutBankedWrite(x221,ArrayBuffer(x228),ArrayBuffer(Set(x229)))
    val x230_size = save("x230_size", MemWrite().setMem(lookup[FIFO]("x221_size")).en(Set(x229)).data(x228_size).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("Squares.scala:73:52").name("size").progorder(79)) // [MemWrite] x230_size = StreamOutBankedWrite(x221,ArrayBuffer(x228),ArrayBuffer(Set(x229)))
    val x230_isLoad = save("x230_isLoad", MemWrite().setMem(lookup[FIFO]("x221_isLoad")).en(Set(x229)).data(x228_isLoad).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("Squares.scala:73:52").name("isLoad").progorder(79)) // [MemWrite] x230_isLoad = StreamOutBankedWrite(x221,ArrayBuffer(x228),ArrayBuffer(Set(x229)))
    endState[Ctrl]
    val x232 = save("x232", StridedCounter(par=16).min(Const(0).tp(Fix(true, 32, 0))).step(Const(1).tp(Fix(true, 32, 0))).max(Const(8192).tp(Fix(true, 32, 0))).sctx("Squares.scala:73:52").progorder(80)) // [StridedCounter] x232 = CounterNew(Const(0),Const(8192),Const(1),Const(16))
    val x233 = save("x233", List(x232).sctx("Squares.scala:73:52").progorder(81)) // [List[Counter]] x233 = CounterChainNew(List(x232))
    val x244 = save("x244", createCtrl(schedule=Pipelined)(LoopController().cchain(x233)).sctx("Squares.scala:73:52").progorder(82)) // [LoopController] x244 = UnrolledForeach(Set(),x233,Block(Const(())),List(List(b234)),List(List(b235)),List(),None)
    }; split8
    def split9 = {
    val b234 = save("b234", CounterIter(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(lookup[LoopController]("x244").cchain.T(0)).resetParent(lookup[LoopController]("x244")).tp(Fix(true, 32, 0)).sctx("Staging.scala:26:82")) // [CounterIter] b234
    val b235 = save("b235", CounterValid(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).counter(lookup[LoopController]("x244").cchain.T(0)).resetParent(lookup[LoopController]("x244")).tp(Bool).sctx("Staging.scala:26:82")) // [CounterValid] b235
    val x236 = save("x236", Const(List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)).sctx("CounterIterRewriteRule.scala:21:17")) // [Const] x236 = LaneStatic(b234,List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
    val x237 = save("x237", OpDef(op=FixSRA).addInput(b234,Const(4).tp(Fix(true, 16, 0))).tp(Fix(true, 32, 0)).sctx("Squares.scala:73:52")) // [OpDef] x237 = FixSRA(b234,Const(4))
    val x240 = save("x240", BankedRead().bank(List(x236)).offset(x237).setMem(lookup[SRAM]("x150")).en(Set(b235)).port(Some(1)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("Squares.scala:73:52").progorder(83)) // [BankedRead] x240 = SRAMBankedRead(x150,Vector(List(x236)),Vector(x237),Vector(Set(b235)),Vec[Fix[FALSE,_32,_0]])
    val x241 = save("x241", x240) // [BankedRead] x241 = VecApply(x240,0)
    val x242__1 = save("x242__1", x241) // [BankedRead] x242__1 = SimpleStruct(ArrayBuffer((_1,x241), (_2,Const(true))))
    val x242__2 = save("x242__2", Const(true).tp(Bool).sctx("Squares.scala:73:52").name("_2").progorder(84)) // [Const] x242__2 = SimpleStruct(ArrayBuffer((_1,x241), (_2,Const(true))))
    val x243__1 = save("x243__1", MemWrite().setMem(lookup[FIFO]("x222__1")).en(Set(b235)).data(x242__1).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("Squares.scala:73:52").name("_1").progorder(85)) // [MemWrite] x243__1 = StreamOutBankedWrite(x222,ArrayBuffer(x242),ArrayBuffer(Set(b235)))
    val x243__2 = save("x243__2", MemWrite().setMem(lookup[FIFO]("x222__2")).en(Set(b235)).data(x242__2).port(Some(0)).muxPort(0).broadcast(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).castgroup(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)).sctx("Squares.scala:73:52").name("_2").progorder(85)) // [MemWrite] x243__2 = StreamOutBankedWrite(x222,ArrayBuffer(x242),ArrayBuffer(Set(b235)))
    endState[Ctrl]
    val x245 = save("x245", FringeDenseStore(lookup[DRAM]("x145")).offset(MemRead().setMem(lookup[FIFO]("x221_offset"))).size(MemRead().setMem(lookup[FIFO]("x221_size"))).data(MemRead().setMem(lookup[FIFO]("x222__1"))).valid(MemRead().setMem(lookup[FIFO]("x222__2"))).ack(MemWrite().setMem(lookup[FIFO]("x223")).data).sctx("Squares.scala:73:52").progorder(86)) // [FringeDenseStore] x245 = FringeDenseStore(x145,x221,x222,x223)
    }; split9
    def split10 = {
    val x248 = save("x248", createCtrl(schedule=Sequenced)(UnitController()).sctx("Squares.scala:73:52").progorder(87)) // [UnitController] x248 = UnitPipe(Set(),Block(Const(())),None)
    val x246 = save("x246", MemRead().setMem(lookup[FIFO]("x223")).en(Set()).port(Some(0)).muxPort(0).broadcast(List(0)).castgroup(List(0)).sctx("Squares.scala:73:52").progorder(88)) // [MemRead] x246 = StreamInBankedRead(x223,ArrayBuffer(Set()))
    endState[Ctrl]
    endState[Ctrl]
    endState[Ctrl]
    endState[Ctrl]
    }; split10
  }
}
