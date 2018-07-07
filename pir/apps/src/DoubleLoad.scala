import pir._
import pir.node._
import arch._
import prism.enums._

object DoubleLoad extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x914 = ArgIn(init=0).name("x914").ctrl(top).srcCtx("DoubleLoad.scala:16:22:sizeA") // ArgInNew(Const(0))
    isAccum(x914) = false
    bufferDepthOf(x914) = 1
    boundOf(x914) = 1024
    val x916 = ReadMem(x914).name("x916").ctrl(top).srcCtx("DoubleLoad.scala:19:24") // RegRead(x914)
    val x917 = DRAM(dims=List(x916)).name("x917").ctrl(top).srcCtx("DoubleLoad.scala:19:23:vec1") // x917 = DRAMNew(ArrayBuffer(x916),Const(0))
    val x918 = ReadMem(x914).name("x918").ctrl(top).srcCtx("DoubleLoad.scala:20:23") // RegRead(x914)
    val x919 = DRAM(dims=List(x918)).name("x919").ctrl(top).srcCtx("DoubleLoad.scala:20:22:out") // x919 = DRAMNew(ArrayBuffer(x918),Const(0))
    val x986 = UnitController(style=SeqPipe, level=OuterControl).name("x986").ctrl(top).srcCtx("DoubleLoad.scala:24:11") // Hwblock(Block(Const(())),false)
    val x921 = ReadMem(x914).name("x921").ctrl(x986).srcCtx("DoubleLoad.scala:25:15") // RegRead(x914)
    val x922 = Counter(min=Const(0), max=x921, step=Const(32), par=1).name("x922").ctrl(x986).srcCtx("DoubleLoad.scala:25:27") // CounterNew(Const(0),x921,Const(32),Const(1))
    val x923 = CounterChain(List(x922)).name("x923").ctrl(x986).srcCtx("DoubleLoad.scala:25:34") // CounterChainNew(List(x922))
    val x985 = LoopController(style=MetaPipe, level=OuterControl, cchain=x923).name("x985").ctrl(x986).srcCtx("DoubleLoad.scala:25:34") // UnrolledForeach(List(Const(true)),x923,Block(Const(())),List(List(b533)),List(List(b534)))
    val b533 = CounterIter(x922, Some(0)).name("b533").ctrl(x985) // b533
    val b534 = Const(true).name("b534").ctrl(x985) // b534
    val x924_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x924_d0_b0").ctrl(x985).srcCtx("DoubleLoad.scala:26:25:b1") // x924 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x924_d0_b0) = false
    bufferDepthOf(x924_d0_b0) = 1
    val x925_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x925_d0_b0").ctrl(x985).srcCtx("DoubleLoad.scala:27:25:b2") // x925 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x925_d0_b0) = false
    bufferDepthOf(x925_d0_b0) = 1
    val x984 = UnitController(style=SeqPipe, level=OuterControl).name("x984").ctrl(x985).srcCtx("DoubleLoad.scala:28:20") // UnitPipe(List(b534),Block(Const(())))
    val x927 = UnitController(style=SeqPipe, level=InnerControl).name("x927").ctrl(x984).srcCtx("DoubleLoad.scala:28:20") // UnitPipe(List(b534),Block(Const(())))
    val x926 = OpDef(op=FixAdd, inputs=List(b533, Const(32))).name("x926").ctrl(x927).srcCtx("DoubleLoad.scala:29:28") // FixAdd(b533,Const(32))
    val x946 = UnitController(style=StreamPipe, level=OuterControl).name("x946").ctrl(x984).srcCtx("DoubleLoad.scala:29:14") // UnitPipe(List(b534),Block(Const(())))
    val b989 = StreamOut(field="offset").name("b989").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // x928 = StreamOutNew(BurstCmdBus)
    isAccum(b989) = false
    bufferDepthOf(b989) = 1
    val b990 = StreamOut(field="size").name("b990").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // x928 = StreamOutNew(BurstCmdBus)
    isAccum(b990) = false
    bufferDepthOf(b990) = 1
    val x929 = StreamIn(field="data").name("x929").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // x929 = StreamInNew(BurstDataBus())
    isAccum(x929) = false
    bufferDepthOf(x929) = 1
    val x937 = UnitController(style=SeqPipe, level=InnerControl).name("x937").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // UnitPipe(List(b534),Block(x936))
    val x930 = b533 // FixConvert(b533,TRUE,_32,_0) (Same Type. No op)
    val x931 = OpDef(op=FixSla, inputs=List(x930, Const(2))).name("x931").ctrl(x937).srcCtx("DoubleLoad.scala:29:14") // FixLsh(x930,Const(2))
    val x932 = x931 // FixConvert(x931,TRUE,_64,_0)
    val x933 = DramAddress(x917).name("x933").ctrl(x937).srcCtx("DoubleLoad.scala:29:14") // GetDRAMAddress(x917)
    val x935_x934 = OpDef(op=FixAdd, inputs=List(x932, x933)).name("x935_x934").ctrl(x937).srcCtx("DoubleLoad.scala:29:14") // FixAdd(x932,x933)
    // x935 = SimpleStruct(ArrayBuffer((offset,x934), (size,Const(128)), (isLoad,Const(true))))
    val x936_b991_b989 = WriteMem(b989, x935_x934).name("x936_b991_b989").ctrl(x937).srcCtx("DoubleLoad.scala:29:14") // StreamWrite(x928,x935,b534)
    val x936_b992_b990 = WriteMem(b990, Const(128)).name("x936_b992_b990").ctrl(x937).srcCtx("DoubleLoad.scala:29:14") // StreamWrite(x928,x935,b534)
    val x938 = FringeDenseLoad(dram=List(x917), cmdStream=List(b989, b990), dataStream=List(x929)).name("x938").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // FringeDenseLoad(x917,x928,x929)
    val x939 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x939").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x940 = CounterChain(List(x939)).name("x940").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // CounterChainNew(List(x939))
    val x945 = LoopController(style=InnerPipe, level=InnerControl, cchain=x940).name("x945").ctrl(x946).srcCtx("DoubleLoad.scala:29:14") // UnrolledForeach(List(b534),x940,Block(Const(())),List(List(b552)),List(List(b553)))
    val b552 = CounterIter(x939, None).name("b552").ctrl(x945) // b552
    val b553 = Const(true).name("b553").ctrl(x945) // b553
    val x941 = OpDef(op=BitAnd, inputs=List(b553, b534)).name("x941").ctrl(x945).srcCtx("UnrollingBase.scala:28:66") // And(b553,b534)
    val x942_x942 = ReadMem(x929).name("x942_x942").ctrl(x945).srcCtx("DoubleLoad.scala:29:14") // ParStreamRead(x929,List(x941))
    val x943_x943 = x942_x942 // x943 = VectorApply(x942,0)
    val x944 = StoreBanks(List(x924_d0_b0), List(b552), x943_x943).name("x944").ctrl(x945).srcCtx("DoubleLoad.scala:29:14") // ParSRAMStore(x924,List(List(b552)),List(x943),List(x941))
    val x960 = UnitController(style=SeqPipe, level=OuterControl).name("x960").ctrl(x984).srcCtx("DoubleLoad.scala:30:22") // UnitPipe(List(b534),Block(Const(())))
    val x947 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x947").ctrl(x960).srcCtx("DoubleLoad.scala:31:24") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x948 = CounterChain(List(x947)).name("x948").ctrl(x960).srcCtx("DoubleLoad.scala:31:31") // CounterChainNew(List(x947))
    val x951 = LoopController(style=InnerPipe, level=InnerControl, cchain=x948).name("x951").ctrl(x960).srcCtx("DoubleLoad.scala:31:31") // UnrolledForeach(List(b534),x948,Block(Const(())),List(List(b562)),List(List(b563)))
    val b562 = CounterIter(x947, None).name("b562").ctrl(x951) // b562
    val b563 = Const(true).name("b563").ctrl(x951) // b563
    val x949 = OpDef(op=BitAnd, inputs=List(b563, b534)).name("x949").ctrl(x951).srcCtx("UnrollingBase.scala:28:66") // And(b563,b534)
    val x950 = StoreBanks(List(x924_d0_b0), List(b562), Const(3)).name("x950").ctrl(x951).srcCtx("DoubleLoad.scala:32:22") // ParSRAMStore(x924,List(List(b562)),List(Const(3)),List(x949))
    val x952 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x952").ctrl(x960).srcCtx("DoubleLoad.scala:34:24") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x953 = CounterChain(List(x952)).name("x953").ctrl(x960).srcCtx("DoubleLoad.scala:34:31") // CounterChainNew(List(x952))
    val x959 = LoopController(style=InnerPipe, level=InnerControl, cchain=x953).name("x959").ctrl(x960).srcCtx("DoubleLoad.scala:34:31") // UnrolledForeach(List(b534),x953,Block(Const(())),List(List(b569)),List(List(b570)))
    val b569 = CounterIter(x952, None).name("b569").ctrl(x959) // b569
    val b570 = Const(true).name("b570").ctrl(x959) // b570
    val x954 = OpDef(op=BitAnd, inputs=List(b570, b534)).name("x954").ctrl(x959).srcCtx("UnrollingBase.scala:28:66") // And(b570,b534)
    val x955 = LoadBanks(List(x924_d0_b0), List(b569)).name("x955").ctrl(x959).srcCtx("DoubleLoad.scala:35:26") // ParSRAMLoad(x924,List(List(b569)),List(x954))
    val x956 = x955 // x956 = VectorApply(x955,0)
    val x957 = OpDef(op=FixAdd, inputs=List(x956, Const(4))).name("x957").ctrl(x959).srcCtx("DoubleLoad.scala:35:31") // FixAdd(x956,Const(4))
    val x958 = StoreBanks(List(x925_d0_b0), List(b569), x957).name("x958").ctrl(x959).srcCtx("DoubleLoad.scala:35:22") // ParSRAMStore(x925,List(List(b569)),List(x957),List(x954))
    val x983 = UnitController(style=StreamPipe, level=OuterControl).name("x983").ctrl(x984).srcCtx("DoubleLoad.scala:38:31") // UnitPipe(List(b534),Block(Const(())))
    val b993 = StreamOut(field="offset").name("b993").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // x961 = StreamOutNew(BurstCmdBus)
    isAccum(b993) = false
    bufferDepthOf(b993) = 1
    val b994 = StreamOut(field="size").name("b994").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // x961 = StreamOutNew(BurstCmdBus)
    isAccum(b994) = false
    bufferDepthOf(b994) = 1
    val x962 = StreamOut(field="data").name("x962").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // x962 = StreamOutNew(BurstFullDataBus())
    isAccum(x962) = false
    bufferDepthOf(x962) = 1
    val x963 = StreamIn(field="ack").name("x963").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // x963 = StreamInNew(BurstAckBus)
    isAccum(x963) = false
    bufferDepthOf(x963) = 1
    val x971 = UnitController(style=SeqPipe, level=InnerControl).name("x971").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // UnitPipe(List(b534),Block(x970))
    val x964 = b533 // FixConvert(b533,TRUE,_32,_0) (Same Type. No op)
    val x965 = OpDef(op=FixSla, inputs=List(x964, Const(2))).name("x965").ctrl(x971).srcCtx("DoubleLoad.scala:38:31") // FixLsh(x964,Const(2))
    val x966 = x965 // FixConvert(x965,TRUE,_64,_0)
    val x967 = DramAddress(x919).name("x967").ctrl(x971).srcCtx("DoubleLoad.scala:38:31") // GetDRAMAddress(x919)
    val x969_x968 = OpDef(op=FixAdd, inputs=List(x966, x967)).name("x969_x968").ctrl(x971).srcCtx("DoubleLoad.scala:38:31") // FixAdd(x966,x967)
    // x969 = SimpleStruct(ArrayBuffer((offset,x968), (size,Const(128)), (isLoad,Const(false))))
    val x970_b995_b993 = WriteMem(b993, x969_x968).name("x970_b995_b993").ctrl(x971).srcCtx("DoubleLoad.scala:38:31") // StreamWrite(x961,x969,b534)
    val x970_b996_b994 = WriteMem(b994, Const(128)).name("x970_b996_b994").ctrl(x971).srcCtx("DoubleLoad.scala:38:31") // StreamWrite(x961,x969,b534)
    val x972 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x972").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x973 = CounterChain(List(x972)).name("x973").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // CounterChainNew(List(x972))
    val x979 = LoopController(style=InnerPipe, level=InnerControl, cchain=x973).name("x979").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // UnrolledForeach(List(b534),x973,Block(Const(())),List(List(b591)),List(List(b592)))
    val b591 = CounterIter(x972, None).name("b591").ctrl(x979) // b591
    val b592 = Const(true).name("b592").ctrl(x979) // b592
    val x974 = OpDef(op=BitAnd, inputs=List(b592, b534)).name("x974").ctrl(x979).srcCtx("UnrollingBase.scala:28:66") // And(b592,b534)
    val x975 = LoadBanks(List(x925_d0_b0), List(b591)).name("x975").ctrl(x979).srcCtx("DoubleLoad.scala:38:31") // ParSRAMLoad(x925,List(List(b591)),List(x974))
    val x977_x976 = x975 // x976 = VectorApply(x975,0)
    // x977 = SimpleStruct(ArrayBuffer((_1,x976), (_2,Const(true))))
    val x978_x978_x962 = WriteMem(x962, x977_x976).name("x978_x978_x962").ctrl(x979).srcCtx("DoubleLoad.scala:38:31") // ParStreamWrite(x962,List(x977),List(x974))
    val x980 = FringeDenseStore(dram=List(x919), cmdStream=List(b993, b994), dataStream=List(x962), ackStream=List(x963)).name("x980").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // FringeDenseStore(x919,x961,x962,x963)
    val x982 = UnitController(style=SeqPipe, level=InnerControl).name("x982").ctrl(x983).srcCtx("DoubleLoad.scala:38:31") // UnitPipe(List(b534),Block(Const(())))
    val x981_x981 = ReadMem(x963).name("x981_x981").ctrl(x982).srcCtx("DoubleLoad.scala:38:31") // StreamRead(x963,b534)
    
  }
}
