import pir._
import pir.node._
import arch._
import prism.enums._

object SplitTest extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6081 = UnitController(style=SeqPipe, level=OuterControl).name("x6081").ctrl(top).srcCtx("lenetUnit.scala:61:11") // Hwblock(Block(Const(())),false)

    val x5853 = Counter(min=Const(0), max=Const(17), step=Const(1), par=1).name("x5853").ctrl(x6081).srcCtx("lenetUnit.scala:75:31") // CounterNew(Const(0),Const(17),Const(1),Const(1))
    val x5854 = CounterChain(List(x5853)).name("x5854").ctrl(x6081).srcCtx("lenetUnit.scala:75:38") // CounterChainNew(List(x5853))
    val x6080 = LoopController(style=MetaPipe, level=OuterControl, cchain=x5854).name("x6080").ctrl(x6081).srcCtx("lenetUnit.scala:75:38") // UnrolledForeach(List(Const(true)),x5854,Block(Const(())),List(List(b3616)),List(List(b3617)))
    val b3616 = CounterIter(x5853, Some(0)).name("b3616").ctrl(x6080) // b3616
    val b3617 = Const(true).name("b3617").ctrl(x6080) // b3617
    val x6006 = DRAM().name("x6006").ctrl(x6080).srcCtx("lenetUnit.scala:124:31:tmp_DRAM") // x6006 = DRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)),Const(0))
    val x6007 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x6007").ctrl(x6080).srcCtx("lenetUnit.scala:125:39") // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x6008 = Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x6008").ctrl(x6080).srcCtx("lenetUnit.scala:125:39") // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x6009 = CounterChain(List(x6007,x6008)).name("x6009").ctrl(x6080).srcCtx("lenetUnit.scala:125:39") // CounterChainNew(List(x6007, x6008))
    val x6079 = LoopController(style=StreamPipe, level=OuterControl, cchain=x6009).name("x6079").ctrl(x6080).srcCtx("lenetUnit.scala:125:39") // UnrolledForeach(List(b3617),x6009,Block(Const(())),List(List(b3799), List(b3800)),List(List(b3801), List(b3802)))
    val b3799 = CounterIter(x6007, Some(0)).name("b3799").ctrl(x6079) // b3799
    val b3801 = Const(true).name("b3801").ctrl(x6079) // b3801
    val b3800 = CounterIter(x6008, Some(0)).name("b3800").ctrl(x6079) // b3800
    val b3802 = Const(true).name("b3802").ctrl(x6079) // b3802
    val x6078 = UnitController(style=SeqPipe, level=OuterControl).name("x6078").ctrl(x6079).srcCtx("lenetUnit.scala:125:39") // UnitPipe(List(b3801, b3802, b3617),Block(Const(())))
    val x6072 = UnitController(style=SeqPipe, level=OuterControl).name("x6072").ctrl(x6078).srcCtx("lenetUnit.scala:125:39") // UnitPipe(List(b3801, b3802, b3617),Block(Const(())))
    val x6013 = Reg(init=Some(0)).name("x6013").ctrl(x6072).srcCtx("lenetUnit.scala:125:39") // x6013 = RegNew(Const(0))
    val x6014 = Reg(init=Some(0)).name("x6014").ctrl(x6072).srcCtx("lenetUnit.scala:125:39") // x6014 = RegNew(Const(0))
    val x6015 = Reg(init=Some(0)).name("x6015").ctrl(x6072).srcCtx("lenetUnit.scala:125:39") // x6015 = RegNew(Const(0))
    val x6052 = UnitController(style=SeqPipe, level=InnerControl).name("x6052").ctrl(x6072).srcCtx("lenetUnit.scala:125:39") // UnitPipe(List(b3801, b3802, b3617),Block(x6051))
    val x6016 = b3799 // FixConvert(b3799,TRUE,_32,_0) (Same Type. No op)
    val x6017 = OpDef(op=FixMul, inputs=List(x6016, Const(144))).name("x6017").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixMul(x6016,Const(144))
    val x6018 = b3800 // FixConvert(b3800,TRUE,_32,_0) (Same Type. No op)
    val x6019 = OpDef(op=FixMul, inputs=List(x6018, Const(12))).name("x6019").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixMul(x6018,Const(12))
    val x6020 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6021 = OpDef(op=FixAdd, inputs=List(x6017, x6019)).name("x6021").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6017,x6019)
    val x6022 = OpDef(op=FixAdd, inputs=List(x6021, x6020)).name("x6022").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6021,x6020)
    val x6023 = OpDef(op=FixSla, inputs=List(x6022, Const(1))).name("x6023").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixLsh(x6022,Const(1))
    val x6024 = x6023 // x6024 = DataAsBits(x6023)
    val x6025 = OpDef(op=BitAnd, inputs=List(x6024, Const(31))).name("x6025").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // VectorSlice(x6024,5,0) strMask=00000000000000000000000000011111
    val x6026 = x6025 // x6026 = BitsAsData(x6025,FixPt[TRUE,_32,_0])
    val x6027 = OpDef(op=FixSub, inputs=List(x6023, x6026)).name("x6027").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixSub(x6023,x6026)
    val x6028 = OpDef(op=FixAdd, inputs=List(x6023, Const(24))).name("x6028").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6023,Const(24))
    val x6029 = x6028 // x6029 = DataAsBits(x6028)
    val x6030 = OpDef(op=BitAnd, inputs=List(x6029, Const(31))).name("x6030").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // VectorSlice(x6029,5,0) strMask=00000000000000000000000000011111
    val x6031 = x6030 // x6031 = BitsAsData(x6030,FixPt[TRUE,_32,_0])
    val x6032 = OpDef(op=FixEql, inputs=List(x6031, Const(0))).name("x6032").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixEql(x6031,Const(0))
    val x6033 = OpDef(op=FixSub, inputs=List(Const(64), x6031)).name("x6033").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixSub(Const(64),x6031)
    val x6034 = OpDef(op=MuxOp, inputs=List(x6032, Const(0), x6033)).name("x6034").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // Mux(x6032,Const(0),x6033)
    val x6035 = OpDef(op=FixSra, inputs=List(x6026, Const(1))).name("x6035").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixRsh(x6026,Const(1))
    val x6036 = OpDef(op=FixSra, inputs=List(x6034, Const(1))).name("x6036").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixRsh(x6034,Const(1))
    val x6037 = OpDef(op=FixAdd, inputs=List(x6035, Const(12))).name("x6037").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6035,Const(12))
    val x6038 = OpDef(op=FixAdd, inputs=List(Const(12), x6035)).name("x6038").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(Const(12),x6035)
    val x6039 = OpDef(op=FixAdd, inputs=List(x6038, x6036)).name("x6039").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6038,x6036)
    val x6040 = OpDef(op=FixAdd, inputs=List(Const(24), x6026)).name("x6040").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(Const(24),x6026)
    val x6045_x6041 = OpDef(op=FixAdd, inputs=List(x6040, x6034)).name("x6045_x6041").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6040,x6034)
    val x6042 = x6027 // FixConvert(x6027,TRUE,_64,_0)
    val x6043 = DramAddress(x6006).name("x6043").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // GetDRAMAddress(x6006)
    val x6045_x6044 = OpDef(op=FixAdd, inputs=List(x6042, x6043)).name("x6045_x6044").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // FixAdd(x6042,x6043)
    // x6045 = SimpleStruct(ArrayBuffer((offset,x6044), (size,x6041), (isLoad,Const(false))))
    val x6046 = OpDef(op=BitAnd, inputs=List(b3801, b3802)).name("x6046").ctrl(x6052).srcCtx("UnrollingBase.scala:28:66") // And(b3801,b3802)
    val x6047 = OpDef(op=BitAnd, inputs=List(x6046, b3617)).name("x6047").ctrl(x6052).srcCtx("UnrollingBase.scala:28:66") // And(x6046,b3617)
    val b6131 = StreamOut(field="offset").name("b6131").ctrl(x6079).srcCtx("lenetUnit.scala:125:39") // x6010 = StreamOutNew(BurstCmdBus)
    val b6132 = StreamOut(field="size").name("b6132").ctrl(x6079).srcCtx("lenetUnit.scala:125:39") // x6010 = StreamOutNew(BurstCmdBus)
    val x6048_b6133_b6131 = WriteMem(b6131, x6045_x6044).name("x6048_b6133_b6131").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // StreamWrite(x6010,x6045,x6047)
    val x6048_b6134_b6132 = WriteMem(b6132, x6045_x6041).name("x6048_b6134_b6132").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // StreamWrite(x6010,x6045,x6047)
    val x6049_x6013 = WriteMem(x6013, x6035).name("x6049_x6013").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // RegWrite(x6013,x6035,x6047)
    val x6050_x6014 = WriteMem(x6014, x6037).name("x6050_x6014").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // RegWrite(x6014,x6037,x6047)
    val x6051_x6015 = WriteMem(x6015, x6039).name("x6051_x6015").ctrl(x6052).srcCtx("lenetUnit.scala:125:39") // RegWrite(x6015,x6039,x6047)
    
  }
}
