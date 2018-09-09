import pir._
import pir.node._
import arch._
import prism.enums._

object LSTMPipeStep extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x6643 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6643").srcCtx("LSTMPipeStep.scala:32:34:cInit") } // x6643 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6644 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128))).name("x6644").srcCtx("LSTMPipeStep.scala:33:34:hInit") } // x6644 = DRAMNew(ArrayBuffer(Const(2), Const(128)),Const(0))
    val x6645 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x6645").srcCtx("LSTMPipeStep.scala:34:35:wxInit") } // x6645 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x6646 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(128), Const(4), Const(128))).name("x6646").srcCtx("LSTMPipeStep.scala:35:35:whInit") } // x6646 = DRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)),Const(0))
    val x6647 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1), Const(8), Const(128))).name("x6647").srcCtx("LSTMPipeStep.scala:36:35:xbInit") } // x6647 = DRAMNew(ArrayBuffer(Const(1), Const(8), Const(128)),Const(0))
    val x6648 = withCtrl(design.top.topController) { DRAM(dims=List(Const(2), Const(4), Const(128))).name("x6648").srcCtx("LSTMPipeStep.scala:37:34:bInit") } // x6648 = DRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)),Const(0))
    val x6649 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1), Const(8), Const(128))).name("x6649").srcCtx("LSTMPipeStep.scala:38:23:dout") } // x6649 = DRAMNew(ArrayBuffer(Const(1), Const(8), Const(128)),Const(0))
    val x7358 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x7358").srcCtx("LSTMPipeStep.scala:40:11") } // Hwblock(Block(Const(())),false)
    val x6650_d0_b0 = withCtrl(x7358) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6650_d0_b0").srcCtx("DataGenerator.scala:43:21:c") } // x6650 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6650_d0_b0) = false
    bufferDepthOf(x6650_d0_b0) = 1
    staticDimsOf(x6650_d0_b0) = List(2, 128)
    val x6651 = withCtrl(x7358) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6651").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6652 = withCtrl(x7358) { CounterChain(List(x6651)).name("x6652").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6651))
    val x6674 = withCtrl(x7358) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6652).name("x6674").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6652,Block(Const(())),List(List(b2883)),List(List(b2884)))
    val b2883 = withCtrl(x6674) { CounterIter(x6651, Some(0)).name("b2883") } // b2883
    val b2884 = withCtrl(x6674) { Const(true).name("b2884") } // b2884
    val b7373 = withCtrl(x6674) { StreamOut(field="offset").name("b7373").srcCtx("DataGenerator.scala:44:8") } // x6653 = StreamOutNew(BurstCmdBus)
    isAccum(b7373) = false
    bufferDepthOf(b7373) = 1
    val b7374 = withCtrl(x6674) { StreamOut(field="size").name("b7374").srcCtx("DataGenerator.scala:44:8") } // x6653 = StreamOutNew(BurstCmdBus)
    isAccum(b7374) = false
    bufferDepthOf(b7374) = 1
    val x6654 = withCtrl(x6674) { StreamIn(field="data").name("x6654").srcCtx("DataGenerator.scala:44:8") } // x6654 = StreamInNew(BurstDataBus())
    isAccum(x6654) = false
    bufferDepthOf(x6654) = 1
    val x6665 = withCtrl(x6674) { UnitController(style=SeqPipe, level=InnerControl).name("x6665").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b2884),Block(x6664))
    val x6655 = withCtrl(x6665) { b2883 } // FixConvert(b2883,TRUE,_32,_0) (Same Type. No op)
    val x6656 = withCtrl(x6665) { OpDef(op=FixSla, inputs=List(x6655, Const(7))).name("x6656").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6655,Const(7))
    val x6657 = withCtrl(x6665) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6658 = withCtrl(x6665) { OpDef(op=FixAdd, inputs=List(x6656, x6657)).name("x6658").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6656,x6657)
    val x6659 = withCtrl(x6665) { OpDef(op=FixSla, inputs=List(x6658, Const(2))).name("x6659").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6658,Const(2))
    val x6660 = withCtrl(x6665) { x6659 } // FixConvert(x6659,TRUE,_64,_0)
    val x6661 = withCtrl(x6665) { DramAddress(x6643).name("x6661").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6643)
    val x6663_x6662 = withCtrl(x6665) { OpDef(op=FixAdd, inputs=List(x6660, x6661)).name("x6663_x6662").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6660,x6661)
    // x6663 = SimpleStruct(ArrayBuffer((offset,x6662), (size,Const(512)), (isLoad,Const(true))))
    val x6664_b7375_b7373 = withCtrl(x6665) { WriteMem(b7373, x6663_x6662).name("x6664_b7375_b7373").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6653,x6663,b2884)
    val x6664_b7376_b7374 = withCtrl(x6665) { WriteMem(b7374, Const(512)).name("x6664_b7376_b7374").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6653,x6663,b2884)
    val x6666 = withCtrl(x6674) { FringeDenseLoad(dram=List(x6643), cmdStream=List(b7373, b7374), dataStream=List(x6654)).name("x6666").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6643,x6653,x6654)
    val x6667 = withCtrl(x6674) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6667").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6668 = withCtrl(x6674) { CounterChain(List(x6667)).name("x6668").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6667))
    val x6673 = withCtrl(x6674) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6668).name("x6673").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b2884),x6668,Block(Const(())),List(List(b2901)),List(List(b2902)))
    val b2901 = withCtrl(x6673) { CounterIter(x6667, None).name("b2901") } // b2901
    val b2902 = withCtrl(x6673) { Const(true).name("b2902") } // b2902
    val x6669 = withCtrl(x6673) { OpDef(op=BitAnd, inputs=List(b2902, b2884)).name("x6669").srcCtx("UnrollingBase.scala:28:66") } // And(b2902,b2884)
    val x6670_x6670 = withCtrl(x6673) { ReadMem(x6654).name("x6670_x6670").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6654,List(x6669))
    val x6671_x6671 = withCtrl(x6673) { x6670_x6670 } // VectorApply(x6670,0)
    val x6672 = withCtrl(x6673) { StoreBanks(List(List(x6650_d0_b0)), List(b2883, b2901), x6671_x6671).name("x6672").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6650,List(List(b2883, b2901)),List(x6671),List(x6669))
    val x6675_d0_b0 = withCtrl(x7358) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6675_d0_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6675 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6675_d0_b0) = false
    bufferDepthOf(x6675_d0_b0) = 1
    staticDimsOf(x6675_d0_b0) = List(2, 128)
    val x6675_d1_b0 = withCtrl(x7358) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6675_d1_b0").srcCtx("DataGenerator.scala:43:21:h") } // x6675 = SRAMNew(ArrayBuffer(Const(2), Const(128)))
    isAccum(x6675_d1_b0) = false
    bufferDepthOf(x6675_d1_b0) = 1
    staticDimsOf(x6675_d1_b0) = List(2, 128)
    val x6676 = withCtrl(x7358) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6676").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6677 = withCtrl(x7358) { CounterChain(List(x6676)).name("x6677").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6676))
    val x6699 = withCtrl(x7358) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6677).name("x6699").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(Const(true)),x6677,Block(Const(())),List(List(b2912)),List(List(b2913)))
    val b2912 = withCtrl(x6699) { CounterIter(x6676, Some(0)).name("b2912") } // b2912
    val b2913 = withCtrl(x6699) { Const(true).name("b2913") } // b2913
    val b7377 = withCtrl(x6699) { StreamOut(field="offset").name("b7377").srcCtx("DataGenerator.scala:44:8") } // x6678 = StreamOutNew(BurstCmdBus)
    isAccum(b7377) = false
    bufferDepthOf(b7377) = 1
    val b7378 = withCtrl(x6699) { StreamOut(field="size").name("b7378").srcCtx("DataGenerator.scala:44:8") } // x6678 = StreamOutNew(BurstCmdBus)
    isAccum(b7378) = false
    bufferDepthOf(b7378) = 1
    val x6679 = withCtrl(x6699) { StreamIn(field="data").name("x6679").srcCtx("DataGenerator.scala:44:8") } // x6679 = StreamInNew(BurstDataBus())
    isAccum(x6679) = false
    bufferDepthOf(x6679) = 1
    val x6690 = withCtrl(x6699) { UnitController(style=SeqPipe, level=InnerControl).name("x6690").srcCtx("DataGenerator.scala:44:8") } // UnitPipe(List(b2913),Block(x6689))
    val x6680 = withCtrl(x6690) { b2912 } // FixConvert(b2912,TRUE,_32,_0) (Same Type. No op)
    val x6681 = withCtrl(x6690) { OpDef(op=FixSla, inputs=List(x6680, Const(7))).name("x6681").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6680,Const(7))
    val x6682 = withCtrl(x6690) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6683 = withCtrl(x6690) { OpDef(op=FixAdd, inputs=List(x6681, x6682)).name("x6683").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6681,x6682)
    val x6684 = withCtrl(x6690) { OpDef(op=FixSla, inputs=List(x6683, Const(2))).name("x6684").srcCtx("DataGenerator.scala:44:8") } // FixLsh(x6683,Const(2))
    val x6685 = withCtrl(x6690) { x6684 } // FixConvert(x6684,TRUE,_64,_0)
    val x6686 = withCtrl(x6690) { DramAddress(x6644).name("x6686").srcCtx("DataGenerator.scala:44:8") } // GetDRAMAddress(x6644)
    val x6688_x6687 = withCtrl(x6690) { OpDef(op=FixAdd, inputs=List(x6685, x6686)).name("x6688_x6687").srcCtx("DataGenerator.scala:44:8") } // FixAdd(x6685,x6686)
    // x6688 = SimpleStruct(ArrayBuffer((offset,x6687), (size,Const(512)), (isLoad,Const(true))))
    val x6689_b7379_b7377 = withCtrl(x6690) { WriteMem(b7377, x6688_x6687).name("x6689_b7379_b7377").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6678,x6688,b2913)
    val x6689_b7380_b7378 = withCtrl(x6690) { WriteMem(b7378, Const(512)).name("x6689_b7380_b7378").srcCtx("DataGenerator.scala:44:8") } // StreamWrite(x6678,x6688,b2913)
    val x6691 = withCtrl(x6699) { FringeDenseLoad(dram=List(x6644), cmdStream=List(b7377, b7378), dataStream=List(x6679)).name("x6691").srcCtx("DataGenerator.scala:44:8") } // FringeDenseLoad(x6644,x6678,x6679)
    val x6692 = withCtrl(x6699) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6692").srcCtx("DataGenerator.scala:44:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6693 = withCtrl(x6699) { CounterChain(List(x6692)).name("x6693").srcCtx("DataGenerator.scala:44:8") } // CounterChainNew(List(x6692))
    val x6698 = withCtrl(x6699) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6693).name("x6698").srcCtx("DataGenerator.scala:44:8") } // UnrolledForeach(List(b2913),x6693,Block(Const(())),List(List(b2930)),List(List(b2931)))
    val b2930 = withCtrl(x6698) { CounterIter(x6692, None).name("b2930") } // b2930
    val b2931 = withCtrl(x6698) { Const(true).name("b2931") } // b2931
    val x6694 = withCtrl(x6698) { OpDef(op=BitAnd, inputs=List(b2931, b2913)).name("x6694").srcCtx("UnrollingBase.scala:28:66") } // And(b2931,b2913)
    val x6695_x6695 = withCtrl(x6698) { ReadMem(x6679).name("x6695_x6695").srcCtx("DataGenerator.scala:44:8") } // ParStreamRead(x6679,List(x6694))
    val x6696_x6696 = withCtrl(x6698) { x6695_x6695 } // VectorApply(x6695,0)
    val x6697 = withCtrl(x6698) { StoreBanks(List(List(x6675_d0_b0), List(x6675_d1_b0)), List(b2912, b2930), x6696_x6696).name("x6697").srcCtx("DataGenerator.scala:44:8") } // ParSRAMStore(x6675,List(List(b2912, b2930)),List(x6696),List(x6694))
    val x6700_d0_b0 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b0").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b0) = false
    bufferDepthOf(x6700_d0_b0) = 1
    staticDimsOf(x6700_d0_b0) = List(2, 128, 4, 128)
    val x6700_d0_b1 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b1").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b1) = false
    bufferDepthOf(x6700_d0_b1) = 1
    staticDimsOf(x6700_d0_b1) = List(2, 128, 4, 128)
    val x6700_d0_b2 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b2").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b2) = false
    bufferDepthOf(x6700_d0_b2) = 1
    staticDimsOf(x6700_d0_b2) = List(2, 128, 4, 128)
    val x6700_d0_b3 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b3").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b3) = false
    bufferDepthOf(x6700_d0_b3) = 1
    staticDimsOf(x6700_d0_b3) = List(2, 128, 4, 128)
    val x6700_d0_b4 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b4").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b4) = false
    bufferDepthOf(x6700_d0_b4) = 1
    staticDimsOf(x6700_d0_b4) = List(2, 128, 4, 128)
    val x6700_d0_b5 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b5").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b5) = false
    bufferDepthOf(x6700_d0_b5) = 1
    staticDimsOf(x6700_d0_b5) = List(2, 128, 4, 128)
    val x6700_d0_b6 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b6").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b6) = false
    bufferDepthOf(x6700_d0_b6) = 1
    staticDimsOf(x6700_d0_b6) = List(2, 128, 4, 128)
    val x6700_d0_b7 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6700_d0_b7").srcCtx("DataGenerator.scala:76:21:wx") } // x6700 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6700_d0_b7) = false
    bufferDepthOf(x6700_d0_b7) = 1
    staticDimsOf(x6700_d0_b7) = List(2, 128, 4, 128)
    val x6701 = withCtrl(x7358) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6701").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6702 = withCtrl(x7358) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6702").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6703 = withCtrl(x7358) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6703").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6704 = withCtrl(x7358) { CounterChain(List(x6701,x6702,x6703)).name("x6704").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6701, x6702, x6703))
    val x6736 = withCtrl(x7358) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6704).name("x6736").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6704,Block(Const(())),List(List(b2943), List(b2944), List(b2945)),List(List(b2946), List(b2947), List(b2948)))
    val b2943 = withCtrl(x6736) { CounterIter(x6701, Some(0)).name("b2943") } // b2943
    val b2946 = withCtrl(x6736) { Const(true).name("b2946") } // b2946
    val b2944 = withCtrl(x6736) { CounterIter(x6702, Some(0)).name("b2944") } // b2944
    val b2947 = withCtrl(x6736) { Const(true).name("b2947") } // b2947
    val b2945 = withCtrl(x6736) { CounterIter(x6703, Some(0)).name("b2945") } // b2945
    val b2948 = withCtrl(x6736) { Const(true).name("b2948") } // b2948
    val b7381 = withCtrl(x6736) { StreamOut(field="offset").name("b7381").srcCtx("DataGenerator.scala:77:8") } // x6705 = StreamOutNew(BurstCmdBus)
    isAccum(b7381) = false
    bufferDepthOf(b7381) = 1
    val b7382 = withCtrl(x6736) { StreamOut(field="size").name("b7382").srcCtx("DataGenerator.scala:77:8") } // x6705 = StreamOutNew(BurstCmdBus)
    isAccum(b7382) = false
    bufferDepthOf(b7382) = 1
    val x6706 = withCtrl(x6736) { StreamIn(field="data").name("x6706").srcCtx("DataGenerator.scala:77:8") } // x6706 = StreamInNew(BurstDataBus())
    isAccum(x6706) = false
    bufferDepthOf(x6706) = 1
    val x6725 = withCtrl(x6736) { UnitController(style=SeqPipe, level=InnerControl).name("x6725").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b2946, b2947, b2948),Block(x6724))
    val x6707 = withCtrl(x6725) { b2943 } // FixConvert(b2943,TRUE,_32,_0) (Same Type. No op)
    val x6708 = withCtrl(x6725) { OpDef(op=FixSla, inputs=List(x6707, Const(16))).name("x6708").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6707,Const(16))
    val x6709 = withCtrl(x6725) { b2944 } // FixConvert(b2944,TRUE,_32,_0) (Same Type. No op)
    val x6710 = withCtrl(x6725) { OpDef(op=FixSla, inputs=List(x6709, Const(9))).name("x6710").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6709,Const(9))
    val x6711 = withCtrl(x6725) { b2945 } // FixConvert(b2945,TRUE,_32,_0) (Same Type. No op)
    val x6712 = withCtrl(x6725) { OpDef(op=FixSla, inputs=List(x6711, Const(7))).name("x6712").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6711,Const(7))
    val x6713 = withCtrl(x6725) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6714 = withCtrl(x6725) { OpDef(op=FixAdd, inputs=List(x6708, x6710)).name("x6714").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6708,x6710)
    val x6715 = withCtrl(x6725) { OpDef(op=FixAdd, inputs=List(x6712, x6713)).name("x6715").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6712,x6713)
    val x6716 = withCtrl(x6725) { OpDef(op=FixAdd, inputs=List(x6714, x6715)).name("x6716").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6714,x6715)
    val x6717 = withCtrl(x6725) { OpDef(op=FixSla, inputs=List(x6716, Const(2))).name("x6717").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6716,Const(2))
    val x6718 = withCtrl(x6725) { x6717 } // FixConvert(x6717,TRUE,_64,_0)
    val x6719 = withCtrl(x6725) { DramAddress(x6645).name("x6719").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6645)
    val x6721_x6720 = withCtrl(x6725) { OpDef(op=FixAdd, inputs=List(x6718, x6719)).name("x6721_x6720").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6718,x6719)
    // x6721 = SimpleStruct(ArrayBuffer((offset,x6720), (size,Const(512)), (isLoad,Const(true))))
    val x6722 = withCtrl(x6725) { OpDef(op=BitAnd, inputs=List(b2946, b2947)).name("x6722").srcCtx("UnrollingBase.scala:28:66") } // And(b2946,b2947)
    val x6723 = withCtrl(x6725) { OpDef(op=BitAnd, inputs=List(x6722, b2948)).name("x6723").srcCtx("UnrollingBase.scala:28:66") } // And(x6722,b2948)
    val x6724_b7383_b7381 = withCtrl(x6725) { WriteMem(b7381, x6721_x6720).name("x6724_b7383_b7381").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6705,x6721,x6723)
    val x6724_b7384_b7382 = withCtrl(x6725) { WriteMem(b7382, Const(512)).name("x6724_b7384_b7382").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6705,x6721,x6723)
    val x6726 = withCtrl(x6736) { FringeDenseLoad(dram=List(x6645), cmdStream=List(b7381, b7382), dataStream=List(x6706)).name("x6726").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6645,x6705,x6706)
    val x6727 = withCtrl(x6736) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6727").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6728 = withCtrl(x6736) { CounterChain(List(x6727)).name("x6728").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6727))
    val x6735 = withCtrl(x6736) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6728).name("x6735").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b2946, b2947, b2948),x6728,Block(Const(())),List(List(b2973)),List(List(b2974)))
    val b2973 = withCtrl(x6735) { CounterIter(x6727, None).name("b2973") } // b2973
    val b2974 = withCtrl(x6735) { Const(true).name("b2974") } // b2974
    val x6729 = withCtrl(x6735) { OpDef(op=BitAnd, inputs=List(b2974, b2946)).name("x6729").srcCtx("UnrollingBase.scala:28:66") } // And(b2974,b2946)
    val x6730 = withCtrl(x6735) { OpDef(op=BitAnd, inputs=List(b2947, b2948)).name("x6730").srcCtx("UnrollingBase.scala:28:66") } // And(b2947,b2948)
    val x6731 = withCtrl(x6735) { OpDef(op=BitAnd, inputs=List(x6729, x6730)).name("x6731").srcCtx("UnrollingBase.scala:28:66") } // And(x6729,x6730)
    val x6732_x6732 = withCtrl(x6735) { ReadMem(x6706).name("x6732_x6732").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6706,List(x6731))
    val x6733_x6733 = withCtrl(x6735) { x6732_x6732 } // VectorApply(x6732,0)
    val x6734 = withCtrl(x6735) { StoreBanks(List(List(x6700_d0_b0, x6700_d0_b1, x6700_d0_b2, x6700_d0_b3, x6700_d0_b4, x6700_d0_b5, x6700_d0_b6, x6700_d0_b7)), List(b2943, b2944, b2945, b2973), x6733_x6733).name("x6734").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6700,List(List(b2943, b2944, b2945, b2973)),List(x6733),List(x6731))
    val x6737_d0_b0 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b0").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b0) = false
    bufferDepthOf(x6737_d0_b0) = 1
    staticDimsOf(x6737_d0_b0) = List(2, 128, 4, 128)
    val x6737_d0_b1 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b1").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b1) = false
    bufferDepthOf(x6737_d0_b1) = 1
    staticDimsOf(x6737_d0_b1) = List(2, 128, 4, 128)
    val x6737_d0_b2 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b2").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b2) = false
    bufferDepthOf(x6737_d0_b2) = 1
    staticDimsOf(x6737_d0_b2) = List(2, 128, 4, 128)
    val x6737_d0_b3 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b3").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b3) = false
    bufferDepthOf(x6737_d0_b3) = 1
    staticDimsOf(x6737_d0_b3) = List(2, 128, 4, 128)
    val x6737_d0_b4 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b4").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b4) = false
    bufferDepthOf(x6737_d0_b4) = 1
    staticDimsOf(x6737_d0_b4) = List(2, 128, 4, 128)
    val x6737_d0_b5 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b5").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b5) = false
    bufferDepthOf(x6737_d0_b5) = 1
    staticDimsOf(x6737_d0_b5) = List(2, 128, 4, 128)
    val x6737_d0_b6 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b6").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b6) = false
    bufferDepthOf(x6737_d0_b6) = 1
    staticDimsOf(x6737_d0_b6) = List(2, 128, 4, 128)
    val x6737_d0_b7 = withCtrl(x7358) { SRAM(size=16384, banking=Strided(banks=16, stride=1)).name("x6737_d0_b7").srcCtx("DataGenerator.scala:76:21:wh") } // x6737 = SRAMNew(ArrayBuffer(Const(2), Const(128), Const(4), Const(128)))
    isAccum(x6737_d0_b7) = false
    bufferDepthOf(x6737_d0_b7) = 1
    staticDimsOf(x6737_d0_b7) = List(2, 128, 4, 128)
    val x6738 = withCtrl(x7358) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6738").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6739 = withCtrl(x7358) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x6739").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x6740 = withCtrl(x7358) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6740").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6741 = withCtrl(x7358) { CounterChain(List(x6738,x6739,x6740)).name("x6741").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6738, x6739, x6740))
    val x6773 = withCtrl(x7358) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6741).name("x6773").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(Const(true)),x6741,Block(Const(())),List(List(b2988), List(b2989), List(b2990)),List(List(b2991), List(b2992), List(b2993)))
    val b2988 = withCtrl(x6773) { CounterIter(x6738, Some(0)).name("b2988") } // b2988
    val b2991 = withCtrl(x6773) { Const(true).name("b2991") } // b2991
    val b2989 = withCtrl(x6773) { CounterIter(x6739, Some(0)).name("b2989") } // b2989
    val b2992 = withCtrl(x6773) { Const(true).name("b2992") } // b2992
    val b2990 = withCtrl(x6773) { CounterIter(x6740, Some(0)).name("b2990") } // b2990
    val b2993 = withCtrl(x6773) { Const(true).name("b2993") } // b2993
    val b7385 = withCtrl(x6773) { StreamOut(field="offset").name("b7385").srcCtx("DataGenerator.scala:77:8") } // x6742 = StreamOutNew(BurstCmdBus)
    isAccum(b7385) = false
    bufferDepthOf(b7385) = 1
    val b7386 = withCtrl(x6773) { StreamOut(field="size").name("b7386").srcCtx("DataGenerator.scala:77:8") } // x6742 = StreamOutNew(BurstCmdBus)
    isAccum(b7386) = false
    bufferDepthOf(b7386) = 1
    val x6743 = withCtrl(x6773) { StreamIn(field="data").name("x6743").srcCtx("DataGenerator.scala:77:8") } // x6743 = StreamInNew(BurstDataBus())
    isAccum(x6743) = false
    bufferDepthOf(x6743) = 1
    val x6762 = withCtrl(x6773) { UnitController(style=SeqPipe, level=InnerControl).name("x6762").srcCtx("DataGenerator.scala:77:8") } // UnitPipe(List(b2991, b2992, b2993),Block(x6761))
    val x6744 = withCtrl(x6762) { b2988 } // FixConvert(b2988,TRUE,_32,_0) (Same Type. No op)
    val x6745 = withCtrl(x6762) { OpDef(op=FixSla, inputs=List(x6744, Const(16))).name("x6745").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6744,Const(16))
    val x6746 = withCtrl(x6762) { b2989 } // FixConvert(b2989,TRUE,_32,_0) (Same Type. No op)
    val x6747 = withCtrl(x6762) { OpDef(op=FixSla, inputs=List(x6746, Const(9))).name("x6747").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6746,Const(9))
    val x6748 = withCtrl(x6762) { b2990 } // FixConvert(b2990,TRUE,_32,_0) (Same Type. No op)
    val x6749 = withCtrl(x6762) { OpDef(op=FixSla, inputs=List(x6748, Const(7))).name("x6749").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6748,Const(7))
    val x6750 = withCtrl(x6762) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6751 = withCtrl(x6762) { OpDef(op=FixAdd, inputs=List(x6745, x6747)).name("x6751").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6745,x6747)
    val x6752 = withCtrl(x6762) { OpDef(op=FixAdd, inputs=List(x6749, x6750)).name("x6752").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6749,x6750)
    val x6753 = withCtrl(x6762) { OpDef(op=FixAdd, inputs=List(x6751, x6752)).name("x6753").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6751,x6752)
    val x6754 = withCtrl(x6762) { OpDef(op=FixSla, inputs=List(x6753, Const(2))).name("x6754").srcCtx("DataGenerator.scala:77:8") } // FixLsh(x6753,Const(2))
    val x6755 = withCtrl(x6762) { x6754 } // FixConvert(x6754,TRUE,_64,_0)
    val x6756 = withCtrl(x6762) { DramAddress(x6646).name("x6756").srcCtx("DataGenerator.scala:77:8") } // GetDRAMAddress(x6646)
    val x6758_x6757 = withCtrl(x6762) { OpDef(op=FixAdd, inputs=List(x6755, x6756)).name("x6758_x6757").srcCtx("DataGenerator.scala:77:8") } // FixAdd(x6755,x6756)
    // x6758 = SimpleStruct(ArrayBuffer((offset,x6757), (size,Const(512)), (isLoad,Const(true))))
    val x6759 = withCtrl(x6762) { OpDef(op=BitAnd, inputs=List(b2991, b2992)).name("x6759").srcCtx("UnrollingBase.scala:28:66") } // And(b2991,b2992)
    val x6760 = withCtrl(x6762) { OpDef(op=BitAnd, inputs=List(x6759, b2993)).name("x6760").srcCtx("UnrollingBase.scala:28:66") } // And(x6759,b2993)
    val x6761_b7387_b7385 = withCtrl(x6762) { WriteMem(b7385, x6758_x6757).name("x6761_b7387_b7385").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6742,x6758,x6760)
    val x6761_b7388_b7386 = withCtrl(x6762) { WriteMem(b7386, Const(512)).name("x6761_b7388_b7386").srcCtx("DataGenerator.scala:77:8") } // StreamWrite(x6742,x6758,x6760)
    val x6763 = withCtrl(x6773) { FringeDenseLoad(dram=List(x6646), cmdStream=List(b7385, b7386), dataStream=List(x6743)).name("x6763").srcCtx("DataGenerator.scala:77:8") } // FringeDenseLoad(x6646,x6742,x6743)
    val x6764 = withCtrl(x6773) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6764").srcCtx("DataGenerator.scala:77:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6765 = withCtrl(x6773) { CounterChain(List(x6764)).name("x6765").srcCtx("DataGenerator.scala:77:8") } // CounterChainNew(List(x6764))
    val x6772 = withCtrl(x6773) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6765).name("x6772").srcCtx("DataGenerator.scala:77:8") } // UnrolledForeach(List(b2991, b2992, b2993),x6765,Block(Const(())),List(List(b3018)),List(List(b3019)))
    val b3018 = withCtrl(x6772) { CounterIter(x6764, None).name("b3018") } // b3018
    val b3019 = withCtrl(x6772) { Const(true).name("b3019") } // b3019
    val x6766 = withCtrl(x6772) { OpDef(op=BitAnd, inputs=List(b3019, b2991)).name("x6766").srcCtx("UnrollingBase.scala:28:66") } // And(b3019,b2991)
    val x6767 = withCtrl(x6772) { OpDef(op=BitAnd, inputs=List(b2992, b2993)).name("x6767").srcCtx("UnrollingBase.scala:28:66") } // And(b2992,b2993)
    val x6768 = withCtrl(x6772) { OpDef(op=BitAnd, inputs=List(x6766, x6767)).name("x6768").srcCtx("UnrollingBase.scala:28:66") } // And(x6766,x6767)
    val x6769_x6769 = withCtrl(x6772) { ReadMem(x6743).name("x6769_x6769").srcCtx("DataGenerator.scala:77:8") } // ParStreamRead(x6743,List(x6768))
    val x6770_x6770 = withCtrl(x6772) { x6769_x6769 } // VectorApply(x6769,0)
    val x6771 = withCtrl(x6772) { StoreBanks(List(List(x6737_d0_b0, x6737_d0_b1, x6737_d0_b2, x6737_d0_b3, x6737_d0_b4, x6737_d0_b5, x6737_d0_b6, x6737_d0_b7)), List(b2988, b2989, b2990, b3018), x6770_x6770).name("x6771").srcCtx("DataGenerator.scala:77:8") } // ParSRAMStore(x6737,List(List(b2988, b2989, b2990, b3018)),List(x6770),List(x6768))
    val x6774_d0_b0 = withCtrl(x7358) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6774_d0_b0").srcCtx("DataGenerator.scala:59:21:b") } // x6774 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6774_d0_b0) = false
    bufferDepthOf(x6774_d0_b0) = 1
    staticDimsOf(x6774_d0_b0) = List(2, 4, 128)
    val x6774_d0_b1 = withCtrl(x7358) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6774_d0_b1").srcCtx("DataGenerator.scala:59:21:b") } // x6774 = SRAMNew(ArrayBuffer(Const(2), Const(4), Const(128)))
    isAccum(x6774_d0_b1) = false
    bufferDepthOf(x6774_d0_b1) = 1
    staticDimsOf(x6774_d0_b1) = List(2, 4, 128)
    val x6775 = withCtrl(x7358) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6775").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6776 = withCtrl(x7358) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6776").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6777 = withCtrl(x7358) { CounterChain(List(x6775,x6776)).name("x6777").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6775, x6776))
    val x6804 = withCtrl(x7358) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6777).name("x6804").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(Const(true)),x6777,Block(Const(())),List(List(b3032), List(b3033)),List(List(b3034), List(b3035)))
    val b3032 = withCtrl(x6804) { CounterIter(x6775, Some(0)).name("b3032") } // b3032
    val b3034 = withCtrl(x6804) { Const(true).name("b3034") } // b3034
    val b3033 = withCtrl(x6804) { CounterIter(x6776, Some(0)).name("b3033") } // b3033
    val b3035 = withCtrl(x6804) { Const(true).name("b3035") } // b3035
    val b7389 = withCtrl(x6804) { StreamOut(field="offset").name("b7389").srcCtx("DataGenerator.scala:60:8") } // x6778 = StreamOutNew(BurstCmdBus)
    isAccum(b7389) = false
    bufferDepthOf(b7389) = 1
    val b7390 = withCtrl(x6804) { StreamOut(field="size").name("b7390").srcCtx("DataGenerator.scala:60:8") } // x6778 = StreamOutNew(BurstCmdBus)
    isAccum(b7390) = false
    bufferDepthOf(b7390) = 1
    val x6779 = withCtrl(x6804) { StreamIn(field="data").name("x6779").srcCtx("DataGenerator.scala:60:8") } // x6779 = StreamInNew(BurstDataBus())
    isAccum(x6779) = false
    bufferDepthOf(x6779) = 1
    val x6794 = withCtrl(x6804) { UnitController(style=SeqPipe, level=InnerControl).name("x6794").srcCtx("DataGenerator.scala:60:8") } // UnitPipe(List(b3034, b3035),Block(x6793))
    val x6780 = withCtrl(x6794) { b3032 } // FixConvert(b3032,TRUE,_32,_0) (Same Type. No op)
    val x6781 = withCtrl(x6794) { OpDef(op=FixSla, inputs=List(x6780, Const(9))).name("x6781").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6780,Const(9))
    val x6782 = withCtrl(x6794) { b3033 } // FixConvert(b3033,TRUE,_32,_0) (Same Type. No op)
    val x6783 = withCtrl(x6794) { OpDef(op=FixSla, inputs=List(x6782, Const(7))).name("x6783").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6782,Const(7))
    val x6784 = withCtrl(x6794) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6785 = withCtrl(x6794) { OpDef(op=FixAdd, inputs=List(x6781, x6783)).name("x6785").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6781,x6783)
    val x6786 = withCtrl(x6794) { OpDef(op=FixAdd, inputs=List(x6785, x6784)).name("x6786").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6785,x6784)
    val x6787 = withCtrl(x6794) { OpDef(op=FixSla, inputs=List(x6786, Const(2))).name("x6787").srcCtx("DataGenerator.scala:60:8") } // FixLsh(x6786,Const(2))
    val x6788 = withCtrl(x6794) { x6787 } // FixConvert(x6787,TRUE,_64,_0)
    val x6789 = withCtrl(x6794) { DramAddress(x6648).name("x6789").srcCtx("DataGenerator.scala:60:8") } // GetDRAMAddress(x6648)
    val x6791_x6790 = withCtrl(x6794) { OpDef(op=FixAdd, inputs=List(x6788, x6789)).name("x6791_x6790").srcCtx("DataGenerator.scala:60:8") } // FixAdd(x6788,x6789)
    // x6791 = SimpleStruct(ArrayBuffer((offset,x6790), (size,Const(512)), (isLoad,Const(true))))
    val x6792 = withCtrl(x6794) { OpDef(op=BitAnd, inputs=List(b3034, b3035)).name("x6792").srcCtx("UnrollingBase.scala:28:66") } // And(b3034,b3035)
    val x6793_b7391_b7389 = withCtrl(x6794) { WriteMem(b7389, x6791_x6790).name("x6793_b7391_b7389").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6778,x6791,x6792)
    val x6793_b7392_b7390 = withCtrl(x6794) { WriteMem(b7390, Const(512)).name("x6793_b7392_b7390").srcCtx("DataGenerator.scala:60:8") } // StreamWrite(x6778,x6791,x6792)
    val x6795 = withCtrl(x6804) { FringeDenseLoad(dram=List(x6648), cmdStream=List(b7389, b7390), dataStream=List(x6779)).name("x6795").srcCtx("DataGenerator.scala:60:8") } // FringeDenseLoad(x6648,x6778,x6779)
    val x6796 = withCtrl(x6804) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6796").srcCtx("DataGenerator.scala:60:8") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6797 = withCtrl(x6804) { CounterChain(List(x6796)).name("x6797").srcCtx("DataGenerator.scala:60:8") } // CounterChainNew(List(x6796))
    val x6803 = withCtrl(x6804) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6797).name("x6803").srcCtx("DataGenerator.scala:60:8") } // UnrolledForeach(List(b3034, b3035),x6797,Block(Const(())),List(List(b3056)),List(List(b3057)))
    val b3056 = withCtrl(x6803) { CounterIter(x6796, None).name("b3056") } // b3056
    val b3057 = withCtrl(x6803) { Const(true).name("b3057") } // b3057
    val x6798 = withCtrl(x6803) { OpDef(op=BitAnd, inputs=List(b3057, b3034)).name("x6798").srcCtx("UnrollingBase.scala:28:66") } // And(b3057,b3034)
    val x6799 = withCtrl(x6803) { OpDef(op=BitAnd, inputs=List(x6798, b3035)).name("x6799").srcCtx("UnrollingBase.scala:28:66") } // And(x6798,b3035)
    val x6800_x6800 = withCtrl(x6803) { ReadMem(x6779).name("x6800_x6800").srcCtx("DataGenerator.scala:60:8") } // ParStreamRead(x6779,List(x6799))
    val x6801_x6801 = withCtrl(x6803) { x6800_x6800 } // VectorApply(x6800,0)
    val x6802 = withCtrl(x6803) { StoreBanks(List(List(x6774_d0_b0, x6774_d0_b1)), List(b3032, b3033, b3056), x6801_x6801).name("x6802").srcCtx("DataGenerator.scala:60:8") } // ParSRAMStore(x6774,List(List(b3032, b3033, b3056)),List(x6801),List(x6799))
    val x6805_d0_b0 = withCtrl(x7358) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6805_d0_b0").srcCtx("LSTMPipeStep.scala:47:22:x") } // x6805 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6805_d0_b0) = false
    bufferDepthOf(x6805_d0_b0) = 2
    staticDimsOf(x6805_d0_b0) = List(8, 128)
    val x6805_d1_b0 = withCtrl(x7358) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x6805_d1_b0").srcCtx("LSTMPipeStep.scala:47:22:x") } // x6805 = SRAMNew(ArrayBuffer(Const(8), Const(128)))
    isAccum(x6805_d1_b0) = false
    bufferDepthOf(x6805_d1_b0) = 1
    staticDimsOf(x6805_d1_b0) = List(8, 128)
    val x6806 = withCtrl(x7358) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x6806").srcCtx("LSTMPipeStep.scala:48:9") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x6807 = withCtrl(x7358) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6807").srcCtx("LSTMPipeStep.scala:48:9") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6808 = withCtrl(x7358) { CounterChain(List(x6806,x6807)).name("x6808").srcCtx("LSTMPipeStep.scala:48:9") } // CounterChainNew(List(x6806, x6807))
    val x6835 = withCtrl(x7358) { LoopController(style=StreamPipe, level=OuterControl, cchain=x6808).name("x6835").srcCtx("LSTMPipeStep.scala:48:9") } // UnrolledForeach(List(Const(true)),x6808,Block(Const(())),List(List(b3069), List(b3070)),List(List(b3071), List(b3072)))
    val b3069 = withCtrl(x6835) { CounterIter(x6806, Some(0)).name("b3069") } // b3069
    val b3071 = withCtrl(x6835) { Const(true).name("b3071") } // b3071
    val b3070 = withCtrl(x6835) { CounterIter(x6807, Some(0)).name("b3070") } // b3070
    val b3072 = withCtrl(x6835) { Const(true).name("b3072") } // b3072
    val b7393 = withCtrl(x6835) { StreamOut(field="offset").name("b7393").srcCtx("LSTMPipeStep.scala:48:9") } // x6809 = StreamOutNew(BurstCmdBus)
    isAccum(b7393) = false
    bufferDepthOf(b7393) = 1
    val b7394 = withCtrl(x6835) { StreamOut(field="size").name("b7394").srcCtx("LSTMPipeStep.scala:48:9") } // x6809 = StreamOutNew(BurstCmdBus)
    isAccum(b7394) = false
    bufferDepthOf(b7394) = 1
    val x6810 = withCtrl(x6835) { StreamIn(field="data").name("x6810").srcCtx("LSTMPipeStep.scala:48:9") } // x6810 = StreamInNew(BurstDataBus())
    isAccum(x6810) = false
    bufferDepthOf(x6810) = 1
    val x6825 = withCtrl(x6835) { UnitController(style=SeqPipe, level=InnerControl).name("x6825").srcCtx("LSTMPipeStep.scala:48:9") } // UnitPipe(List(b3071, b3072),Block(x6824))
    val x6811 = withCtrl(x6825) { b3069 } // FixConvert(b3069,TRUE,_32,_0) (Same Type. No op)
    val x6812 = withCtrl(x6825) { OpDef(op=FixSla, inputs=List(x6811, Const(10))).name("x6812").srcCtx("LSTMPipeStep.scala:48:9") } // FixLsh(x6811,Const(10))
    val x6813 = withCtrl(x6825) { b3070 } // FixConvert(b3070,TRUE,_32,_0) (Same Type. No op)
    val x6814 = withCtrl(x6825) { OpDef(op=FixSla, inputs=List(x6813, Const(7))).name("x6814").srcCtx("LSTMPipeStep.scala:48:9") } // FixLsh(x6813,Const(7))
    val x6815 = withCtrl(x6825) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x6816 = withCtrl(x6825) { OpDef(op=FixAdd, inputs=List(x6812, x6814)).name("x6816").srcCtx("LSTMPipeStep.scala:48:9") } // FixAdd(x6812,x6814)
    val x6817 = withCtrl(x6825) { OpDef(op=FixAdd, inputs=List(x6816, x6815)).name("x6817").srcCtx("LSTMPipeStep.scala:48:9") } // FixAdd(x6816,x6815)
    val x6818 = withCtrl(x6825) { OpDef(op=FixSla, inputs=List(x6817, Const(2))).name("x6818").srcCtx("LSTMPipeStep.scala:48:9") } // FixLsh(x6817,Const(2))
    val x6819 = withCtrl(x6825) { x6818 } // FixConvert(x6818,TRUE,_64,_0)
    val x6820 = withCtrl(x6825) { DramAddress(x6647).name("x6820").srcCtx("LSTMPipeStep.scala:48:9") } // GetDRAMAddress(x6647)
    val x6822_x6821 = withCtrl(x6825) { OpDef(op=FixAdd, inputs=List(x6819, x6820)).name("x6822_x6821").srcCtx("LSTMPipeStep.scala:48:9") } // FixAdd(x6819,x6820)
    // x6822 = SimpleStruct(ArrayBuffer((offset,x6821), (size,Const(512)), (isLoad,Const(true))))
    val x6823 = withCtrl(x6825) { OpDef(op=BitAnd, inputs=List(b3071, b3072)).name("x6823").srcCtx("UnrollingBase.scala:28:66") } // And(b3071,b3072)
    val x6824_b7395_b7393 = withCtrl(x6825) { WriteMem(b7393, x6822_x6821).name("x6824_b7395_b7393").srcCtx("LSTMPipeStep.scala:48:9") } // StreamWrite(x6809,x6822,x6823)
    val x6824_b7396_b7394 = withCtrl(x6825) { WriteMem(b7394, Const(512)).name("x6824_b7396_b7394").srcCtx("LSTMPipeStep.scala:48:9") } // StreamWrite(x6809,x6822,x6823)
    val x6826 = withCtrl(x6835) { FringeDenseLoad(dram=List(x6647), cmdStream=List(b7393, b7394), dataStream=List(x6810)).name("x6826").srcCtx("LSTMPipeStep.scala:48:9") } // FringeDenseLoad(x6647,x6809,x6810)
    val x6827 = withCtrl(x6835) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6827").srcCtx("LSTMPipeStep.scala:48:9") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6828 = withCtrl(x6835) { CounterChain(List(x6827)).name("x6828").srcCtx("LSTMPipeStep.scala:48:9") } // CounterChainNew(List(x6827))
    val x6834 = withCtrl(x6835) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6828).name("x6834").srcCtx("LSTMPipeStep.scala:48:9") } // UnrolledForeach(List(b3071, b3072),x6828,Block(Const(())),List(List(b3093)),List(List(b3094)))
    val b3093 = withCtrl(x6834) { CounterIter(x6827, None).name("b3093") } // b3093
    val b3094 = withCtrl(x6834) { Const(true).name("b3094") } // b3094
    val x6829 = withCtrl(x6834) { OpDef(op=BitAnd, inputs=List(b3094, b3071)).name("x6829").srcCtx("UnrollingBase.scala:28:66") } // And(b3094,b3071)
    val x6830 = withCtrl(x6834) { OpDef(op=BitAnd, inputs=List(x6829, b3072)).name("x6830").srcCtx("UnrollingBase.scala:28:66") } // And(x6829,b3072)
    val x6831_x6831 = withCtrl(x6834) { ReadMem(x6810).name("x6831_x6831").srcCtx("LSTMPipeStep.scala:48:9") } // ParStreamRead(x6810,List(x6830))
    val x6832_x6832 = withCtrl(x6834) { x6831_x6831 } // VectorApply(x6831,0)
    val x6833 = withCtrl(x6834) { StoreBanks(List(List(x6805_d0_b0), List(x6805_d1_b0)), List(b3070, b3093), x6832_x6832).name("x6833").srcCtx("LSTMPipeStep.scala:48:9") } // ParSRAMStore(x6805,List(List(b3070, b3093)),List(x6832),List(x6830))
    val x6836 = withCtrl(x7358) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x6836").srcCtx("LSTMPipeStep.scala:49:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x6837 = withCtrl(x7358) { CounterChain(List(x6836)).name("x6837").srcCtx("LSTMPipeStep.scala:49:29") } // CounterChainNew(List(x6836))
    val x7357 = withCtrl(x7358) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6837).name("x7357").srcCtx("LSTMPipeStep.scala:49:29") } // UnrolledForeach(List(Const(true)),x6837,Block(Const(())),List(List(b3104)),List(List(b3105)))
    val b3104 = withCtrl(x7357) { CounterIter(x6836, Some(0)).name("b3104") } // b3104
    val b3105 = withCtrl(x7357) { Const(true).name("b3105") } // b3105
    val x7318 = withCtrl(x7357) { UnitController(style=SeqPipe, level=OuterControl).name("x7318").srcCtx("LSTMPipeStep.scala:15:10") } // UnitPipe(List(b3105),Block(Const(())))
    val x6838 = withCtrl(x7318) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x6838").srcCtx("LSTMPipeStep.scala:16:24") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x6839 = withCtrl(x7318) { CounterChain(List(x6838)).name("x6839").srcCtx("LSTMPipeStep.scala:16:30") } // CounterChainNew(List(x6838))
    val x7317 = withCtrl(x7318) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6839).name("x7317").srcCtx("LSTMPipeStep.scala:16:30") } // UnrolledForeach(List(b3105),x6839,Block(Const(())),List(List(b3108)),List(List(b3109)))
    val b3108 = withCtrl(x7317) { CounterIter(x6838, Some(0)).name("b3108") } // b3108
    val b3109 = withCtrl(x7317) { Const(true).name("b3109") } // b3109
    val x7316 = withCtrl(x7317) { UnitController(style=SeqPipe, level=OuterControl).name("x7316").srcCtx("LSTMPipeStep.scala:17:14") } // UnitPipe(List(b3109, b3105),Block(Const(())))
    val x6840_d0_b0 = withCtrl(x7316) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6840_d0_b0").srcCtx("LSTMPipeStep.scala:18:44:reduceMem") } // x6840 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6840_d0_b0) = false
    bufferDepthOf(x6840_d0_b0) = 1
    staticDimsOf(x6840_d0_b0) = List(4, 128)
    val x6840_d0_b1 = withCtrl(x7316) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6840_d0_b1").srcCtx("LSTMPipeStep.scala:18:44:reduceMem") } // x6840 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6840_d0_b1) = false
    bufferDepthOf(x6840_d0_b1) = 1
    staticDimsOf(x6840_d0_b1) = List(4, 128)
    val x6840_d1_b0 = withCtrl(x7316) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6840_d1_b0").srcCtx("LSTMPipeStep.scala:18:44:reduceMem") } // x6840 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6840_d1_b0) = true
    bufferDepthOf(x6840_d1_b0) = 1
    staticDimsOf(x6840_d1_b0) = List(4, 128)
    val x6841_d0_b0 = withCtrl(x7316) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6841_d0_b0").srcCtx("LSTMPipeStep.scala:19:42:foldMem") } // x6841 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6841_d0_b0) = false
    bufferDepthOf(x6841_d0_b0) = 1
    staticDimsOf(x6841_d0_b0) = List(4, 128)
    val x6841_d0_b1 = withCtrl(x7316) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x6841_d0_b1").srcCtx("LSTMPipeStep.scala:19:42:foldMem") } // x6841 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6841_d0_b1) = false
    bufferDepthOf(x6841_d0_b1) = 1
    staticDimsOf(x6841_d0_b1) = List(4, 128)
    val x6842 = withCtrl(x7316) { Counter(min=Const(0), max=Const(128), step=Const(1), par=8).name("x6842").srcCtx("VecMatMultBiasAdd.scala:28:53") } // CounterNew(Const(0),Const(128),Const(1),Const(8))
    val x6843 = withCtrl(x7316) { CounterChain(List(x6842)).name("x6843").srcCtx("VecMatMultBiasAdd.scala:42:6") } // CounterChainNew(List(x6842))
    val x7160 = withCtrl(x7316) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6843).name("x7160").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnrolledReduce(List(b3109, b3105),x6843,x6840,Block((x6840) => Const(())),List(List(b3117, b3118, b3119, b3120, b3121, b3122, b3123, b3124)),List(List(b3125, b3126, b3127, b3128, b3129, b3130, b3131, b3132)))
    val b3117 = withCtrl(x7160) { CounterIter(x6842, Some(0)).name("b3117") } // b3117
    val b3125 = withCtrl(x7160) { Const(true).name("b3125") } // b3125
    val b3118 = withCtrl(x7160) { CounterIter(x6842, Some(1)).name("b3118") } // b3118
    val b3126 = withCtrl(x7160) { Const(true).name("b3126") } // b3126
    val b3119 = withCtrl(x7160) { CounterIter(x6842, Some(2)).name("b3119") } // b3119
    val b3127 = withCtrl(x7160) { Const(true).name("b3127") } // b3127
    val b3120 = withCtrl(x7160) { CounterIter(x6842, Some(3)).name("b3120") } // b3120
    val b3128 = withCtrl(x7160) { Const(true).name("b3128") } // b3128
    val b3121 = withCtrl(x7160) { CounterIter(x6842, Some(4)).name("b3121") } // b3121
    val b3129 = withCtrl(x7160) { Const(true).name("b3129") } // b3129
    val b3122 = withCtrl(x7160) { CounterIter(x6842, Some(5)).name("b3122") } // b3122
    val b3130 = withCtrl(x7160) { Const(true).name("b3130") } // b3130
    val b3123 = withCtrl(x7160) { CounterIter(x6842, Some(6)).name("b3123") } // b3123
    val b3131 = withCtrl(x7160) { Const(true).name("b3131") } // b3131
    val b3124 = withCtrl(x7160) { CounterIter(x6842, Some(7)).name("b3124") } // b3124
    val b3132 = withCtrl(x7160) { Const(true).name("b3132") } // b3132
    val x6844_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6844_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6844 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6844_d0_b0) = false
    bufferDepthOf(x6844_d0_b0) = 2
    staticDimsOf(x6844_d0_b0) = List(4, 128)
    val x6845_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6845_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6845 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6845_d0_b0) = false
    bufferDepthOf(x6845_d0_b0) = 2
    staticDimsOf(x6845_d0_b0) = List(4, 128)
    val x6846_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6846_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6846 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6846_d0_b0) = false
    bufferDepthOf(x6846_d0_b0) = 2
    staticDimsOf(x6846_d0_b0) = List(4, 128)
    val x6847_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6847_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6847 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6847_d0_b0) = false
    bufferDepthOf(x6847_d0_b0) = 2
    staticDimsOf(x6847_d0_b0) = List(4, 128)
    val x6848_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6848_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6848 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6848_d0_b0) = false
    bufferDepthOf(x6848_d0_b0) = 2
    staticDimsOf(x6848_d0_b0) = List(4, 128)
    val x6849_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6849_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6849 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6849_d0_b0) = false
    bufferDepthOf(x6849_d0_b0) = 2
    staticDimsOf(x6849_d0_b0) = List(4, 128)
    val x6850_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6850_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6850 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6850_d0_b0) = false
    bufferDepthOf(x6850_d0_b0) = 2
    staticDimsOf(x6850_d0_b0) = List(4, 128)
    val x6851_d0_b0 = withCtrl(x7160) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x6851_d0_b0").srcCtx("VecMatMultBiasAdd.scala:29:23:re") } // x6851 = SRAMNew(ArrayBuffer(Const(4), Const(128)))
    isAccum(x6851_d0_b0) = false
    bufferDepthOf(x6851_d0_b0) = 2
    staticDimsOf(x6851_d0_b0) = List(4, 128)
    val x6852 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6852").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6852 = RegNew(Const(0))
    isAccum(x6852) = false
    bufferDepthOf(x6852) = 2
    val x6853 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6853").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6853 = RegNew(Const(0))
    isAccum(x6853) = false
    bufferDepthOf(x6853) = 2
    val x6854 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6854").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6854 = RegNew(Const(0))
    isAccum(x6854) = false
    bufferDepthOf(x6854) = 2
    val x6855 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6855").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6855 = RegNew(Const(0))
    isAccum(x6855) = false
    bufferDepthOf(x6855) = 2
    val x6856 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6856").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6856 = RegNew(Const(0))
    isAccum(x6856) = false
    bufferDepthOf(x6856) = 2
    val x6857 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6857").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6857 = RegNew(Const(0))
    isAccum(x6857) = false
    bufferDepthOf(x6857) = 2
    val x6858 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6858").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6858 = RegNew(Const(0))
    isAccum(x6858) = false
    bufferDepthOf(x6858) = 2
    val x6859 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6859").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6859 = RegNew(Const(0))
    isAccum(x6859) = false
    bufferDepthOf(x6859) = 2
    val x6860 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6860").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6860 = RegNew(Const(0))
    isAccum(x6860) = false
    bufferDepthOf(x6860) = 2
    val x6861 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6861").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6861 = RegNew(Const(0))
    isAccum(x6861) = false
    bufferDepthOf(x6861) = 2
    val x6862 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6862").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6862 = RegNew(Const(0))
    isAccum(x6862) = false
    bufferDepthOf(x6862) = 2
    val x6863 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6863").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6863 = RegNew(Const(0))
    isAccum(x6863) = false
    bufferDepthOf(x6863) = 2
    val x6864 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6864").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6864 = RegNew(Const(0))
    isAccum(x6864) = false
    bufferDepthOf(x6864) = 2
    val x6865 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6865").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6865 = RegNew(Const(0))
    isAccum(x6865) = false
    bufferDepthOf(x6865) = 2
    val x6866 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6866").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6866 = RegNew(Const(0))
    isAccum(x6866) = false
    bufferDepthOf(x6866) = 2
    val x6867 = withCtrl(x7160) { Reg(init=Some(0.0)).name("x6867").srcCtx("VecMatMultBiasAdd.scala:42:6") } // x6867 = RegNew(Const(0))
    isAccum(x6867) = false
    bufferDepthOf(x6867) = 2
    val x6924 = withCtrl(x7160) { UnitController(style=ForkJoin, level=OuterControl).name("x6924").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3109, b3105),Block(Const(())))
    val x6874 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6874").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3125, b3109, b3105),Block(Const(())))
    val x6868 = withCtrl(x6874) { OpDef(op=BitAnd, inputs=List(b3125, b3109)).name("x6868").srcCtx("UnrollingBase.scala:28:66") } // And(b3125,b3109)
    val x6869 = withCtrl(x6874) { OpDef(op=BitAnd, inputs=List(x6868, b3105)).name("x6869").srcCtx("UnrollingBase.scala:28:66") } // And(x6868,b3105)
    val x6870 = withCtrl(x6874) { LoadBanks(List(x6805_d1_b0), List(b3104, b3117)).name("x6870").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3117),Const(0),x6869)
    val x6871 = withCtrl(x6874) { LoadBanks(List(x6675_d1_b0), List(b3108, b3117)).name("x6871").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3117),Const(0),x6869)
    def split1 = {
    val x6872_x6852 = withCtrl(x6874) { WriteMem(x6852, x6870).name("x6872_x6852").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6852,x6870,x6869)
    val x6873_x6860 = withCtrl(x6874) { WriteMem(x6860, x6871).name("x6873_x6860").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6860,x6871,x6869)
    val x6881 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6881").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3126, b3109, b3105),Block(Const(())))
    val x6875 = withCtrl(x6881) { OpDef(op=BitAnd, inputs=List(b3126, b3109)).name("x6875").srcCtx("UnrollingBase.scala:28:66") } // And(b3126,b3109)
    val x6876 = withCtrl(x6881) { OpDef(op=BitAnd, inputs=List(x6875, b3105)).name("x6876").srcCtx("UnrollingBase.scala:28:66") } // And(x6875,b3105)
    val x6877 = withCtrl(x6881) { LoadBanks(List(x6805_d1_b0), List(b3104, b3118)).name("x6877").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3118),Const(0),x6876)
    val x6878 = withCtrl(x6881) { LoadBanks(List(x6675_d1_b0), List(b3108, b3118)).name("x6878").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3118),Const(0),x6876)
    val x6879_x6853 = withCtrl(x6881) { WriteMem(x6853, x6877).name("x6879_x6853").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6853,x6877,x6876)
    val x6880_x6861 = withCtrl(x6881) { WriteMem(x6861, x6878).name("x6880_x6861").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6861,x6878,x6876)
    val x6888 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6888").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3127, b3109, b3105),Block(Const(())))
    val x6882 = withCtrl(x6888) { OpDef(op=BitAnd, inputs=List(b3127, b3109)).name("x6882").srcCtx("UnrollingBase.scala:28:66") } // And(b3127,b3109)
    val x6883 = withCtrl(x6888) { OpDef(op=BitAnd, inputs=List(x6882, b3105)).name("x6883").srcCtx("UnrollingBase.scala:28:66") } // And(x6882,b3105)
    val x6884 = withCtrl(x6888) { LoadBanks(List(x6805_d1_b0), List(b3104, b3119)).name("x6884").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3119),Const(0),x6883)
    val x6885 = withCtrl(x6888) { LoadBanks(List(x6675_d1_b0), List(b3108, b3119)).name("x6885").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3119),Const(0),x6883)
    val x6886_x6854 = withCtrl(x6888) { WriteMem(x6854, x6884).name("x6886_x6854").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6854,x6884,x6883)
    val x6887_x6862 = withCtrl(x6888) { WriteMem(x6862, x6885).name("x6887_x6862").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6862,x6885,x6883)
    val x6895 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6895").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3128, b3109, b3105),Block(Const(())))
    val x6889 = withCtrl(x6895) { OpDef(op=BitAnd, inputs=List(b3128, b3109)).name("x6889").srcCtx("UnrollingBase.scala:28:66") } // And(b3128,b3109)
    val x6890 = withCtrl(x6895) { OpDef(op=BitAnd, inputs=List(x6889, b3105)).name("x6890").srcCtx("UnrollingBase.scala:28:66") } // And(x6889,b3105)
    val x6891 = withCtrl(x6895) { LoadBanks(List(x6805_d1_b0), List(b3104, b3120)).name("x6891").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3120),Const(0),x6890)
    val x6892 = withCtrl(x6895) { LoadBanks(List(x6675_d1_b0), List(b3108, b3120)).name("x6892").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3120),Const(0),x6890)
    val x6893_x6855 = withCtrl(x6895) { WriteMem(x6855, x6891).name("x6893_x6855").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6855,x6891,x6890)
    val x6894_x6863 = withCtrl(x6895) { WriteMem(x6863, x6892).name("x6894_x6863").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6863,x6892,x6890)
    val x6902 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6902").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3129, b3109, b3105),Block(Const(())))
    val x6896 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(b3129, b3109)).name("x6896").srcCtx("UnrollingBase.scala:28:66") } // And(b3129,b3109)
    val x6897 = withCtrl(x6902) { OpDef(op=BitAnd, inputs=List(x6896, b3105)).name("x6897").srcCtx("UnrollingBase.scala:28:66") } // And(x6896,b3105)
    val x6898 = withCtrl(x6902) { LoadBanks(List(x6805_d1_b0), List(b3104, b3121)).name("x6898").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3121),Const(0),x6897)
    val x6899 = withCtrl(x6902) { LoadBanks(List(x6675_d1_b0), List(b3108, b3121)).name("x6899").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3121),Const(0),x6897)
    val x6900_x6856 = withCtrl(x6902) { WriteMem(x6856, x6898).name("x6900_x6856").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6856,x6898,x6897)
    val x6901_x6864 = withCtrl(x6902) { WriteMem(x6864, x6899).name("x6901_x6864").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6864,x6899,x6897)
    val x6909 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6909").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3130, b3109, b3105),Block(Const(())))
    val x6903 = withCtrl(x6909) { OpDef(op=BitAnd, inputs=List(b3130, b3109)).name("x6903").srcCtx("UnrollingBase.scala:28:66") } // And(b3130,b3109)
    val x6904 = withCtrl(x6909) { OpDef(op=BitAnd, inputs=List(x6903, b3105)).name("x6904").srcCtx("UnrollingBase.scala:28:66") } // And(x6903,b3105)
    val x6905 = withCtrl(x6909) { LoadBanks(List(x6805_d1_b0), List(b3104, b3122)).name("x6905").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3122),Const(0),x6904)
    val x6906 = withCtrl(x6909) { LoadBanks(List(x6675_d1_b0), List(b3108, b3122)).name("x6906").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3122),Const(0),x6904)
    val x6907_x6857 = withCtrl(x6909) { WriteMem(x6857, x6905).name("x6907_x6857").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6857,x6905,x6904)
    val x6908_x6865 = withCtrl(x6909) { WriteMem(x6865, x6906).name("x6908_x6865").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6865,x6906,x6904)
    val x6916 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6916").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3131, b3109, b3105),Block(Const(())))
    val x6910 = withCtrl(x6916) { OpDef(op=BitAnd, inputs=List(b3131, b3109)).name("x6910").srcCtx("UnrollingBase.scala:28:66") } // And(b3131,b3109)
    val x6911 = withCtrl(x6916) { OpDef(op=BitAnd, inputs=List(x6910, b3105)).name("x6911").srcCtx("UnrollingBase.scala:28:66") } // And(x6910,b3105)
    val x6912 = withCtrl(x6916) { LoadBanks(List(x6805_d1_b0), List(b3104, b3123)).name("x6912").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3123),Const(0),x6911)
    val x6913 = withCtrl(x6916) { LoadBanks(List(x6675_d1_b0), List(b3108, b3123)).name("x6913").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3123),Const(0),x6911)
    val x6914_x6858 = withCtrl(x6916) { WriteMem(x6858, x6912).name("x6914_x6858").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6858,x6912,x6911)
    val x6915_x6866 = withCtrl(x6916) { WriteMem(x6866, x6913).name("x6915_x6866").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6866,x6913,x6911)
    val x6923 = withCtrl(x6924) { UnitController(style=SeqPipe, level=InnerControl).name("x6923").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnitPipe(List(b3132, b3109, b3105),Block(Const(())))
    val x6917 = withCtrl(x6923) { OpDef(op=BitAnd, inputs=List(b3132, b3109)).name("x6917").srcCtx("UnrollingBase.scala:28:66") } // And(b3132,b3109)
    val x6918 = withCtrl(x6923) { OpDef(op=BitAnd, inputs=List(x6917, b3105)).name("x6918").srcCtx("UnrollingBase.scala:28:66") } // And(x6917,b3105)
    val x6919 = withCtrl(x6923) { LoadBanks(List(x6805_d1_b0), List(b3104, b3124)).name("x6919").srcCtx("VecMatMultBiasAdd.scala:31:19:xEle") } // SRAMLoad(x6805,ArrayBuffer(Const(8), Const(128)),List(b3104, b3124),Const(0),x6918)
    val x6920 = withCtrl(x6923) { LoadBanks(List(x6675_d1_b0), List(b3108, b3124)).name("x6920").srcCtx("VecMatMultBiasAdd.scala:32:19:hEle") } // SRAMLoad(x6675,ArrayBuffer(Const(2), Const(128)),List(b3108, b3124),Const(0),x6918)
    val x6921_x6859 = withCtrl(x6923) { WriteMem(x6859, x6919).name("x6921_x6859").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6859,x6919,x6918)
    val x6922_x6867 = withCtrl(x6923) { WriteMem(x6867, x6920).name("x6922_x6867").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegWrite(x6867,x6920,x6918)
    val x7085 = withCtrl(x7160) { UnitController(style=ForkJoin, level=OuterControl).name("x7085").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3109, b3105),Block(Const(())))
    val x6925 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6925").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6926 = withCtrl(x7085) { CounterChain(List(x6925)).name("x6926").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x6925))
    val x6944 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6926).name("x6944").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3125, b3109, b3105),x6926,Block(Const(())),List(List(b3230)),List(List(b3231)))
    val b3230 = withCtrl(x6944) { CounterIter(x6925, Some(0)).name("b3230") } // b3230
    val b3231 = withCtrl(x6944) { Const(true).name("b3231") } // b3231
    val x6927 = withCtrl(x6944) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6927").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6928 = withCtrl(x6944) { CounterChain(List(x6927)).name("x6928").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x6927))
    val x6943 = withCtrl(x6944) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6928).name("x6943").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3231, b3125, b3109, b3105),x6928,Block(Const(())),List(List(b3234)),List(List(b3235)))
    val b3234 = withCtrl(x6943) { CounterIter(x6927, None).name("b3234") } // b3234
    val b3235 = withCtrl(x6943) { Const(true).name("b3235") } // b3235
    val x6929 = withCtrl(x6943) { OpDef(op=BitAnd, inputs=List(b3235, b3231)).name("x6929").srcCtx("UnrollingBase.scala:28:66") } // And(b3235,b3231)
    val x6930 = withCtrl(x6943) { OpDef(op=BitAnd, inputs=List(b3125, b3109)).name("x6930").srcCtx("UnrollingBase.scala:28:66") } // And(b3125,b3109)
    val x6931 = withCtrl(x6943) { OpDef(op=BitAnd, inputs=List(x6929, x6930)).name("x6931").srcCtx("UnrollingBase.scala:28:66") } // And(x6929,x6930)
    val x6932 = withCtrl(x6943) { OpDef(op=BitAnd, inputs=List(x6931, b3105)).name("x6932").srcCtx("UnrollingBase.scala:28:66") } // And(x6931,b3105)
    val x6933 = withCtrl(x6943) { LoadBanks(List(x6700_d0_b0), List(b3108, b3117, b3230, b3234)).name("x6933").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3117, b3230, b3234)),List(x6932))
    val x6934 = withCtrl(x6943) { x6933 } // VectorApply(x6933,0)
    val x6935 = withCtrl(x6943) { ReadMem(x6852).name("x6935").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6852)
    val x6936 = withCtrl(x6943) { OpDef(op=FixMul, inputs=List(x6934, x6935)).name("x6936").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x6934,x6935)
    val x6937 = withCtrl(x6943) { LoadBanks(List(x6737_d0_b0), List(b3108, b3117, b3230, b3234)).name("x6937").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3117, b3230, b3234)),List(x6932))
    val x6938 = withCtrl(x6943) { x6937 } // VectorApply(x6937,0)
    val x6939 = withCtrl(x6943) { ReadMem(x6860).name("x6939").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6860)
    val x6940 = withCtrl(x6943) { OpDef(op=FixMul, inputs=List(x6938, x6939)).name("x6940").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x6938,x6939)
    val x6941 = withCtrl(x6943) { OpDef(op=FixAdd, inputs=List(x6936, x6940)).name("x6941").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x6936,x6940)
    val x6942 = withCtrl(x6943) { StoreBanks(List(List(x6844_d0_b0)), List(b3230, b3234), x6941).name("x6942").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6844,List(List(b3230, b3234)),List(x6941),List(x6932))
    val x6945 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6945").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6946 = withCtrl(x7085) { CounterChain(List(x6945)).name("x6946").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x6945))
    val x6964 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6946).name("x6964").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3126, b3109, b3105),x6946,Block(Const(())),List(List(b3252)),List(List(b3253)))
    val b3252 = withCtrl(x6964) { CounterIter(x6945, Some(0)).name("b3252") } // b3252
    val b3253 = withCtrl(x6964) { Const(true).name("b3253") } // b3253
    val x6947 = withCtrl(x6964) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6947").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6948 = withCtrl(x6964) { CounterChain(List(x6947)).name("x6948").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x6947))
    val x6963 = withCtrl(x6964) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6948).name("x6963").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3253, b3126, b3109, b3105),x6948,Block(Const(())),List(List(b3256)),List(List(b3257)))
    val b3256 = withCtrl(x6963) { CounterIter(x6947, None).name("b3256") } // b3256
    val b3257 = withCtrl(x6963) { Const(true).name("b3257") } // b3257
    val x6949 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(b3257, b3253)).name("x6949").srcCtx("UnrollingBase.scala:28:66") } // And(b3257,b3253)
    val x6950 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(b3126, b3109)).name("x6950").srcCtx("UnrollingBase.scala:28:66") } // And(b3126,b3109)
    val x6951 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(x6949, x6950)).name("x6951").srcCtx("UnrollingBase.scala:28:66") } // And(x6949,x6950)
    val x6952 = withCtrl(x6963) { OpDef(op=BitAnd, inputs=List(x6951, b3105)).name("x6952").srcCtx("UnrollingBase.scala:28:66") } // And(x6951,b3105)
    val x6953 = withCtrl(x6963) { LoadBanks(List(x6700_d0_b1), List(b3108, b3118, b3252, b3256)).name("x6953").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3118, b3252, b3256)),List(x6952))
    val x6954 = withCtrl(x6963) { x6953 } // VectorApply(x6953,0)
    val x6955 = withCtrl(x6963) { ReadMem(x6853).name("x6955").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6853)
    val x6956 = withCtrl(x6963) { OpDef(op=FixMul, inputs=List(x6954, x6955)).name("x6956").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x6954,x6955)
    val x6957 = withCtrl(x6963) { LoadBanks(List(x6737_d0_b1), List(b3108, b3118, b3252, b3256)).name("x6957").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3118, b3252, b3256)),List(x6952))
    val x6958 = withCtrl(x6963) { x6957 } // VectorApply(x6957,0)
    val x6959 = withCtrl(x6963) { ReadMem(x6861).name("x6959").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6861)
    val x6960 = withCtrl(x6963) { OpDef(op=FixMul, inputs=List(x6958, x6959)).name("x6960").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x6958,x6959)
    val x6961 = withCtrl(x6963) { OpDef(op=FixAdd, inputs=List(x6956, x6960)).name("x6961").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x6956,x6960)
    val x6962 = withCtrl(x6963) { StoreBanks(List(List(x6845_d0_b0)), List(b3252, b3256), x6961).name("x6962").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6845,List(List(b3252, b3256)),List(x6961),List(x6952))
    val x6965 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6965").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6966 = withCtrl(x7085) { CounterChain(List(x6965)).name("x6966").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x6965))
    val x6984 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6966).name("x6984").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3127, b3109, b3105),x6966,Block(Const(())),List(List(b3274)),List(List(b3275)))
    val b3274 = withCtrl(x6984) { CounterIter(x6965, Some(0)).name("b3274") } // b3274
    val b3275 = withCtrl(x6984) { Const(true).name("b3275") } // b3275
    val x6967 = withCtrl(x6984) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6967").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6968 = withCtrl(x6984) { CounterChain(List(x6967)).name("x6968").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x6967))
    val x6983 = withCtrl(x6984) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6968).name("x6983").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3275, b3127, b3109, b3105),x6968,Block(Const(())),List(List(b3278)),List(List(b3279)))
    val b3278 = withCtrl(x6983) { CounterIter(x6967, None).name("b3278") } // b3278
    val b3279 = withCtrl(x6983) { Const(true).name("b3279") } // b3279
    val x6969 = withCtrl(x6983) { OpDef(op=BitAnd, inputs=List(b3279, b3275)).name("x6969").srcCtx("UnrollingBase.scala:28:66") } // And(b3279,b3275)
    val x6970 = withCtrl(x6983) { OpDef(op=BitAnd, inputs=List(b3127, b3109)).name("x6970").srcCtx("UnrollingBase.scala:28:66") } // And(b3127,b3109)
    val x6971 = withCtrl(x6983) { OpDef(op=BitAnd, inputs=List(x6969, x6970)).name("x6971").srcCtx("UnrollingBase.scala:28:66") } // And(x6969,x6970)
    val x6972 = withCtrl(x6983) { OpDef(op=BitAnd, inputs=List(x6971, b3105)).name("x6972").srcCtx("UnrollingBase.scala:28:66") } // And(x6971,b3105)
    val x6973 = withCtrl(x6983) { LoadBanks(List(x6700_d0_b2), List(b3108, b3119, b3274, b3278)).name("x6973").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3119, b3274, b3278)),List(x6972))
    val x6974 = withCtrl(x6983) { x6973 } // VectorApply(x6973,0)
    val x6975 = withCtrl(x6983) { ReadMem(x6854).name("x6975").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6854)
    val x6976 = withCtrl(x6983) { OpDef(op=FixMul, inputs=List(x6974, x6975)).name("x6976").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x6974,x6975)
    val x6977 = withCtrl(x6983) { LoadBanks(List(x6737_d0_b2), List(b3108, b3119, b3274, b3278)).name("x6977").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3119, b3274, b3278)),List(x6972))
    val x6978 = withCtrl(x6983) { x6977 } // VectorApply(x6977,0)
    val x6979 = withCtrl(x6983) { ReadMem(x6862).name("x6979").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6862)
    val x6980 = withCtrl(x6983) { OpDef(op=FixMul, inputs=List(x6978, x6979)).name("x6980").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x6978,x6979)
    val x6981 = withCtrl(x6983) { OpDef(op=FixAdd, inputs=List(x6976, x6980)).name("x6981").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x6976,x6980)
    val x6982 = withCtrl(x6983) { StoreBanks(List(List(x6846_d0_b0)), List(b3274, b3278), x6981).name("x6982").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6846,List(List(b3274, b3278)),List(x6981),List(x6972))
    val x6985 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x6985").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x6986 = withCtrl(x7085) { CounterChain(List(x6985)).name("x6986").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x6985))
    val x7004 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x6986).name("x7004").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3128, b3109, b3105),x6986,Block(Const(())),List(List(b3296)),List(List(b3297)))
    val b3296 = withCtrl(x7004) { CounterIter(x6985, Some(0)).name("b3296") } // b3296
    val b3297 = withCtrl(x7004) { Const(true).name("b3297") } // b3297
    val x6987 = withCtrl(x7004) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x6987").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x6988 = withCtrl(x7004) { CounterChain(List(x6987)).name("x6988").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x6987))
    val x7003 = withCtrl(x7004) { LoopController(style=InnerPipe, level=InnerControl, cchain=x6988).name("x7003").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3297, b3128, b3109, b3105),x6988,Block(Const(())),List(List(b3300)),List(List(b3301)))
    val b3300 = withCtrl(x7003) { CounterIter(x6987, None).name("b3300") } // b3300
    val b3301 = withCtrl(x7003) { Const(true).name("b3301") } // b3301
    val x6989 = withCtrl(x7003) { OpDef(op=BitAnd, inputs=List(b3301, b3297)).name("x6989").srcCtx("UnrollingBase.scala:28:66") } // And(b3301,b3297)
    val x6990 = withCtrl(x7003) { OpDef(op=BitAnd, inputs=List(b3128, b3109)).name("x6990").srcCtx("UnrollingBase.scala:28:66") } // And(b3128,b3109)
    val x6991 = withCtrl(x7003) { OpDef(op=BitAnd, inputs=List(x6989, x6990)).name("x6991").srcCtx("UnrollingBase.scala:28:66") } // And(x6989,x6990)
    val x6992 = withCtrl(x7003) { OpDef(op=BitAnd, inputs=List(x6991, b3105)).name("x6992").srcCtx("UnrollingBase.scala:28:66") } // And(x6991,b3105)
    val x6993 = withCtrl(x7003) { LoadBanks(List(x6700_d0_b3), List(b3108, b3120, b3296, b3300)).name("x6993").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3120, b3296, b3300)),List(x6992))
    val x6994 = withCtrl(x7003) { x6993 } // VectorApply(x6993,0)
    val x6995 = withCtrl(x7003) { ReadMem(x6855).name("x6995").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6855)
    val x6996 = withCtrl(x7003) { OpDef(op=FixMul, inputs=List(x6994, x6995)).name("x6996").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x6994,x6995)
    val x6997 = withCtrl(x7003) { LoadBanks(List(x6737_d0_b3), List(b3108, b3120, b3296, b3300)).name("x6997").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3120, b3296, b3300)),List(x6992))
    val x6998 = withCtrl(x7003) { x6997 } // VectorApply(x6997,0)
    val x6999 = withCtrl(x7003) { ReadMem(x6863).name("x6999").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6863)
    val x7000 = withCtrl(x7003) { OpDef(op=FixMul, inputs=List(x6998, x6999)).name("x7000").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x6998,x6999)
    val x7001 = withCtrl(x7003) { OpDef(op=FixAdd, inputs=List(x6996, x7000)).name("x7001").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x6996,x7000)
    val x7002 = withCtrl(x7003) { StoreBanks(List(List(x6847_d0_b0)), List(b3296, b3300), x7001).name("x7002").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6847,List(List(b3296, b3300)),List(x7001),List(x6992))
    val x7005 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7005").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7006 = withCtrl(x7085) { CounterChain(List(x7005)).name("x7006").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x7005))
    val x7024 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7006).name("x7024").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3129, b3109, b3105),x7006,Block(Const(())),List(List(b3318)),List(List(b3319)))
    val b3318 = withCtrl(x7024) { CounterIter(x7005, Some(0)).name("b3318") } // b3318
    val b3319 = withCtrl(x7024) { Const(true).name("b3319") } // b3319
    val x7007 = withCtrl(x7024) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7007").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7008 = withCtrl(x7024) { CounterChain(List(x7007)).name("x7008").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x7007))
    val x7023 = withCtrl(x7024) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7008).name("x7023").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3319, b3129, b3109, b3105),x7008,Block(Const(())),List(List(b3322)),List(List(b3323)))
    val b3322 = withCtrl(x7023) { CounterIter(x7007, None).name("b3322") } // b3322
    val b3323 = withCtrl(x7023) { Const(true).name("b3323") } // b3323
    val x7009 = withCtrl(x7023) { OpDef(op=BitAnd, inputs=List(b3323, b3319)).name("x7009").srcCtx("UnrollingBase.scala:28:66") } // And(b3323,b3319)
    val x7010 = withCtrl(x7023) { OpDef(op=BitAnd, inputs=List(b3129, b3109)).name("x7010").srcCtx("UnrollingBase.scala:28:66") } // And(b3129,b3109)
    val x7011 = withCtrl(x7023) { OpDef(op=BitAnd, inputs=List(x7009, x7010)).name("x7011").srcCtx("UnrollingBase.scala:28:66") } // And(x7009,x7010)
    val x7012 = withCtrl(x7023) { OpDef(op=BitAnd, inputs=List(x7011, b3105)).name("x7012").srcCtx("UnrollingBase.scala:28:66") } // And(x7011,b3105)
    val x7013 = withCtrl(x7023) { LoadBanks(List(x6700_d0_b4), List(b3108, b3121, b3318, b3322)).name("x7013").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3121, b3318, b3322)),List(x7012))
    val x7014 = withCtrl(x7023) { x7013 } // VectorApply(x7013,0)
    val x7015 = withCtrl(x7023) { ReadMem(x6856).name("x7015").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6856)
    val x7016 = withCtrl(x7023) { OpDef(op=FixMul, inputs=List(x7014, x7015)).name("x7016").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x7014,x7015)
    val x7017 = withCtrl(x7023) { LoadBanks(List(x6737_d0_b4), List(b3108, b3121, b3318, b3322)).name("x7017").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3121, b3318, b3322)),List(x7012))
    val x7018 = withCtrl(x7023) { x7017 } // VectorApply(x7017,0)
    val x7019 = withCtrl(x7023) { ReadMem(x6864).name("x7019").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6864)
    val x7020 = withCtrl(x7023) { OpDef(op=FixMul, inputs=List(x7018, x7019)).name("x7020").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x7018,x7019)
    val x7021 = withCtrl(x7023) { OpDef(op=FixAdd, inputs=List(x7016, x7020)).name("x7021").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x7016,x7020)
    val x7022 = withCtrl(x7023) { StoreBanks(List(List(x6848_d0_b0)), List(b3318, b3322), x7021).name("x7022").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6848,List(List(b3318, b3322)),List(x7021),List(x7012))
    val x7025 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7025").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7026 = withCtrl(x7085) { CounterChain(List(x7025)).name("x7026").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x7025))
    val x7044 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7026).name("x7044").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3130, b3109, b3105),x7026,Block(Const(())),List(List(b3340)),List(List(b3341)))
    val b3340 = withCtrl(x7044) { CounterIter(x7025, Some(0)).name("b3340") } // b3340
    val b3341 = withCtrl(x7044) { Const(true).name("b3341") } // b3341
    val x7027 = withCtrl(x7044) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7027").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7028 = withCtrl(x7044) { CounterChain(List(x7027)).name("x7028").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x7027))
    val x7043 = withCtrl(x7044) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7028).name("x7043").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3341, b3130, b3109, b3105),x7028,Block(Const(())),List(List(b3344)),List(List(b3345)))
    val b3344 = withCtrl(x7043) { CounterIter(x7027, None).name("b3344") } // b3344
    val b3345 = withCtrl(x7043) { Const(true).name("b3345") } // b3345
    val x7029 = withCtrl(x7043) { OpDef(op=BitAnd, inputs=List(b3345, b3341)).name("x7029").srcCtx("UnrollingBase.scala:28:66") } // And(b3345,b3341)
    val x7030 = withCtrl(x7043) { OpDef(op=BitAnd, inputs=List(b3130, b3109)).name("x7030").srcCtx("UnrollingBase.scala:28:66") } // And(b3130,b3109)
    val x7031 = withCtrl(x7043) { OpDef(op=BitAnd, inputs=List(x7029, x7030)).name("x7031").srcCtx("UnrollingBase.scala:28:66") } // And(x7029,x7030)
    val x7032 = withCtrl(x7043) { OpDef(op=BitAnd, inputs=List(x7031, b3105)).name("x7032").srcCtx("UnrollingBase.scala:28:66") } // And(x7031,b3105)
    val x7033 = withCtrl(x7043) { LoadBanks(List(x6700_d0_b5), List(b3108, b3122, b3340, b3344)).name("x7033").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3122, b3340, b3344)),List(x7032))
    val x7034 = withCtrl(x7043) { x7033 } // VectorApply(x7033,0)
    val x7035 = withCtrl(x7043) { ReadMem(x6857).name("x7035").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6857)
    val x7036 = withCtrl(x7043) { OpDef(op=FixMul, inputs=List(x7034, x7035)).name("x7036").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x7034,x7035)
    val x7037 = withCtrl(x7043) { LoadBanks(List(x6737_d0_b5), List(b3108, b3122, b3340, b3344)).name("x7037").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3122, b3340, b3344)),List(x7032))
    val x7038 = withCtrl(x7043) { x7037 } // VectorApply(x7037,0)
    val x7039 = withCtrl(x7043) { ReadMem(x6865).name("x7039").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6865)
    val x7040 = withCtrl(x7043) { OpDef(op=FixMul, inputs=List(x7038, x7039)).name("x7040").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x7038,x7039)
    val x7041 = withCtrl(x7043) { OpDef(op=FixAdd, inputs=List(x7036, x7040)).name("x7041").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x7036,x7040)
    val x7042 = withCtrl(x7043) { StoreBanks(List(List(x6849_d0_b0)), List(b3340, b3344), x7041).name("x7042").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6849,List(List(b3340, b3344)),List(x7041),List(x7032))
    val x7045 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7045").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7046 = withCtrl(x7085) { CounterChain(List(x7045)).name("x7046").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x7045))
    val x7064 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7046).name("x7064").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3131, b3109, b3105),x7046,Block(Const(())),List(List(b3362)),List(List(b3363)))
    val b3362 = withCtrl(x7064) { CounterIter(x7045, Some(0)).name("b3362") } // b3362
    val b3363 = withCtrl(x7064) { Const(true).name("b3363") } // b3363
    val x7047 = withCtrl(x7064) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7047").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7048 = withCtrl(x7064) { CounterChain(List(x7047)).name("x7048").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x7047))
    val x7063 = withCtrl(x7064) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7048).name("x7063").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3363, b3131, b3109, b3105),x7048,Block(Const(())),List(List(b3366)),List(List(b3367)))
    val b3366 = withCtrl(x7063) { CounterIter(x7047, None).name("b3366") } // b3366
    val b3367 = withCtrl(x7063) { Const(true).name("b3367") } // b3367
    val x7049 = withCtrl(x7063) { OpDef(op=BitAnd, inputs=List(b3367, b3363)).name("x7049").srcCtx("UnrollingBase.scala:28:66") } // And(b3367,b3363)
    val x7050 = withCtrl(x7063) { OpDef(op=BitAnd, inputs=List(b3131, b3109)).name("x7050").srcCtx("UnrollingBase.scala:28:66") } // And(b3131,b3109)
    val x7051 = withCtrl(x7063) { OpDef(op=BitAnd, inputs=List(x7049, x7050)).name("x7051").srcCtx("UnrollingBase.scala:28:66") } // And(x7049,x7050)
    val x7052 = withCtrl(x7063) { OpDef(op=BitAnd, inputs=List(x7051, b3105)).name("x7052").srcCtx("UnrollingBase.scala:28:66") } // And(x7051,b3105)
    val x7053 = withCtrl(x7063) { LoadBanks(List(x6700_d0_b6), List(b3108, b3123, b3362, b3366)).name("x7053").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3123, b3362, b3366)),List(x7052))
    val x7054 = withCtrl(x7063) { x7053 } // VectorApply(x7053,0)
    val x7055 = withCtrl(x7063) { ReadMem(x6858).name("x7055").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6858)
    val x7056 = withCtrl(x7063) { OpDef(op=FixMul, inputs=List(x7054, x7055)).name("x7056").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x7054,x7055)
    val x7057 = withCtrl(x7063) { LoadBanks(List(x6737_d0_b6), List(b3108, b3123, b3362, b3366)).name("x7057").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3123, b3362, b3366)),List(x7052))
    val x7058 = withCtrl(x7063) { x7057 } // VectorApply(x7057,0)
    val x7059 = withCtrl(x7063) { ReadMem(x6866).name("x7059").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6866)
    val x7060 = withCtrl(x7063) { OpDef(op=FixMul, inputs=List(x7058, x7059)).name("x7060").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x7058,x7059)
    val x7061 = withCtrl(x7063) { OpDef(op=FixAdd, inputs=List(x7056, x7060)).name("x7061").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x7056,x7060)
    val x7062 = withCtrl(x7063) { StoreBanks(List(List(x6850_d0_b0)), List(b3362, b3366), x7061).name("x7062").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6850,List(List(b3362, b3366)),List(x7061),List(x7052))
    val x7065 = withCtrl(x7085) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7065").srcCtx("VecMatMultBiasAdd.scala:33:28") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7066 = withCtrl(x7085) { CounterChain(List(x7065)).name("x7066").srcCtx("VecMatMultBiasAdd.scala:33:41") } // CounterChainNew(List(x7065))
    val x7084 = withCtrl(x7085) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7066).name("x7084").srcCtx("VecMatMultBiasAdd.scala:33:41") } // UnrolledForeach(List(b3132, b3109, b3105),x7066,Block(Const(())),List(List(b3384)),List(List(b3385)))
    val b3384 = withCtrl(x7084) { CounterIter(x7065, Some(0)).name("b3384") } // b3384
    val b3385 = withCtrl(x7084) { Const(true).name("b3385") } // b3385
    val x7067 = withCtrl(x7084) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7067").srcCtx("VecMatMultBiasAdd.scala:34:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7068 = withCtrl(x7084) { CounterChain(List(x7067)).name("x7068").srcCtx("VecMatMultBiasAdd.scala:34:50") } // CounterChainNew(List(x7067))
    val x7083 = withCtrl(x7084) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7068).name("x7083").srcCtx("VecMatMultBiasAdd.scala:34:50") } // UnrolledForeach(List(b3385, b3132, b3109, b3105),x7068,Block(Const(())),List(List(b3388)),List(List(b3389)))
    val b3388 = withCtrl(x7083) { CounterIter(x7067, None).name("b3388") } // b3388
    val b3389 = withCtrl(x7083) { Const(true).name("b3389") } // b3389
    val x7069 = withCtrl(x7083) { OpDef(op=BitAnd, inputs=List(b3389, b3385)).name("x7069").srcCtx("UnrollingBase.scala:28:66") } // And(b3389,b3385)
    val x7070 = withCtrl(x7083) { OpDef(op=BitAnd, inputs=List(b3132, b3109)).name("x7070").srcCtx("UnrollingBase.scala:28:66") } // And(b3132,b3109)
    val x7071 = withCtrl(x7083) { OpDef(op=BitAnd, inputs=List(x7069, x7070)).name("x7071").srcCtx("UnrollingBase.scala:28:66") } // And(x7069,x7070)
    val x7072 = withCtrl(x7083) { OpDef(op=BitAnd, inputs=List(x7071, b3105)).name("x7072").srcCtx("UnrollingBase.scala:28:66") } // And(x7071,b3105)
    val x7073 = withCtrl(x7083) { LoadBanks(List(x6700_d0_b7), List(b3108, b3124, b3384, b3388)).name("x7073").srcCtx("VecMatMultBiasAdd.scala:35:23") } // ParSRAMLoad(x6700,List(List(b3108, b3124, b3384, b3388)),List(x7072))
    val x7074 = withCtrl(x7083) { x7073 } // VectorApply(x7073,0)
    val x7075 = withCtrl(x7083) { ReadMem(x6859).name("x7075").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6859)
    val x7076 = withCtrl(x7083) { OpDef(op=FixMul, inputs=List(x7074, x7075)).name("x7076").srcCtx("VecMatMultBiasAdd.scala:35:61:reX") } // FixMul(x7074,x7075)
    val x7077 = withCtrl(x7083) { LoadBanks(List(x6737_d0_b7), List(b3108, b3124, b3384, b3388)).name("x7077").srcCtx("VecMatMultBiasAdd.scala:36:23") } // ParSRAMLoad(x6737,List(List(b3108, b3124, b3384, b3388)),List(x7072))
    val x7078 = withCtrl(x7083) { x7077 } // VectorApply(x7077,0)
    val x7079 = withCtrl(x7083) { ReadMem(x6867).name("x7079").srcCtx("VecMatMultBiasAdd.scala:42:6") } // RegRead(x6867)
    val x7080 = withCtrl(x7083) { OpDef(op=FixMul, inputs=List(x7078, x7079)).name("x7080").srcCtx("VecMatMultBiasAdd.scala:36:61:reH") } // FixMul(x7078,x7079)
    val x7081 = withCtrl(x7083) { OpDef(op=FixAdd, inputs=List(x7076, x7080)).name("x7081").srcCtx("VecMatMultBiasAdd.scala:37:40") } // FixAdd(x7076,x7080)
    val x7082 = withCtrl(x7083) { StoreBanks(List(List(x6851_d0_b0)), List(b3384, b3388), x7081).name("x7082").srcCtx("VecMatMultBiasAdd.scala:37:34") } // ParSRAMStore(x6851,List(List(b3384, b3388)),List(x7081),List(x7072))
    val x7086 = withCtrl(x7160) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7086").srcCtx("VecMatMultBiasAdd.scala:42:6") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7087 = withCtrl(x7160) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7087").srcCtx("VecMatMultBiasAdd.scala:42:6") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7088 = withCtrl(x7160) { CounterChain(List(x7087,x7086)).name("x7088").srcCtx("VecMatMultBiasAdd.scala:42:6") } // CounterChainNew(ArrayBuffer(x7087, x7086))
    val x7159 = withCtrl(x7160) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7088).name("x7159").srcCtx("VecMatMultBiasAdd.scala:42:6") } // UnrolledForeach(List(),x7088,Block(Const(())),ArrayBuffer(List(b3407), List(b3408)),ArrayBuffer(List(b3409), List(b3410)))
    val b3407 = withCtrl(x7159) { CounterIter(x7087, Some(0)).name("b3407") } // b3407
    val b3409 = withCtrl(x7159) { Const(true).name("b3409") } // b3409
    val b3408 = withCtrl(x7159) { CounterIter(x7086, None).name("b3408") } // b3408
    val b3410 = withCtrl(x7159) { Const(true).name("b3410") } // b3410
    val x7089 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3409, b3410)).name("x7089").srcCtx("UnrollingBase.scala:28:66") } // And(b3409,b3410)
    val x7090 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7090").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7091 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7089, x7090)).name("x7091").srcCtx("UnrollingBase.scala:28:66") } // And(x7089,x7090)
    val x7092 = withCtrl(x7159) { LoadBanks(List(x6844_d0_b0), List(b3407, b3408)).name("x7092").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6844,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7093 = withCtrl(x7159) { x7092 } // VectorApply(x7092,0)
    val x7094 = withCtrl(x7159) { LoadBanks(List(x6845_d0_b0), List(b3407, b3408)).name("x7094").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6845,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7095 = withCtrl(x7159) { x7094 } // VectorApply(x7094,0)
    val x7096 = withCtrl(x7159) { LoadBanks(List(x6846_d0_b0), List(b3407, b3408)).name("x7096").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6846,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7097 = withCtrl(x7159) { x7096 } // VectorApply(x7096,0)
    val x7098 = withCtrl(x7159) { LoadBanks(List(x6847_d0_b0), List(b3407, b3408)).name("x7098").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6847,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7099 = withCtrl(x7159) { x7098 } // VectorApply(x7098,0)
    val x7100 = withCtrl(x7159) { LoadBanks(List(x6848_d0_b0), List(b3407, b3408)).name("x7100").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6848,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7101 = withCtrl(x7159) { x7100 } // VectorApply(x7100,0)
    val x7102 = withCtrl(x7159) { LoadBanks(List(x6849_d0_b0), List(b3407, b3408)).name("x7102").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6849,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7103 = withCtrl(x7159) { x7102 } // VectorApply(x7102,0)
    val x7104 = withCtrl(x7159) { LoadBanks(List(x6850_d0_b0), List(b3407, b3408)).name("x7104").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6850,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7105 = withCtrl(x7159) { x7104 } // VectorApply(x7104,0)
    val x7106 = withCtrl(x7159) { LoadBanks(List(x6851_d0_b0), List(b3407, b3408)).name("x7106").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6851,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7107 = withCtrl(x7159) { x7106 } // VectorApply(x7106,0)
    val x7108 = withCtrl(x7159) { LoadBanks(List(x6840_d1_b0), List(b3407, b3408)).name("x7108").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMLoad(x6840,List(ArrayBuffer(b3407, b3408)),List(x7091))
    val x7109 = withCtrl(x7159) { x7108 } // VectorApply(x7108,0)
    val x7110 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3125, b3109)).name("x7110").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3125,b3109)
    val x7111 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7110, b3105)).name("x7111").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7110,b3105)
    val x7112 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3126, b3109)).name("x7112").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3126,b3109)
    val x7113 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7112, b3105)).name("x7113").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7112,b3105)
    val x7114 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3127, b3109)).name("x7114").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3127,b3109)
    val x7115 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7114, b3105)).name("x7115").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7114,b3105)
    val x7116 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3128, b3109)).name("x7116").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3128,b3109)
    val x7117 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7116, b3105)).name("x7117").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7116,b3105)
    val x7118 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3129, b3109)).name("x7118").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3129,b3109)
    val x7119 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7118, b3105)).name("x7119").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7118,b3105)
    val x7120 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3130, b3109)).name("x7120").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3130,b3109)
    val x7121 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7120, b3105)).name("x7121").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7120,b3105)
    val x7122 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3131, b3109)).name("x7122").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3131,b3109)
    val x7123 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7122, b3105)).name("x7123").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7122,b3105)
    val x7124 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(b3132, b3109)).name("x7124").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(b3132,b3109)
    val x7125 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7124, b3105)).name("x7125").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7124,b3105)
    val x7126 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7111, x7091)).name("x7126").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7111,x7091)
    val x7127 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7113, x7091)).name("x7127").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7113,x7091)
    val x7128 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7115, x7091)).name("x7128").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7115,x7091)
    val x7129 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7117, x7091)).name("x7129").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7117,x7091)
    val x7130 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7119, x7091)).name("x7130").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7119,x7091)
    val x7131 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7121, x7091)).name("x7131").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7121,x7091)
    val x7132 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7123, x7091)).name("x7132").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7123,x7091)
    val x7133 = withCtrl(x7159) { OpDef(op=BitAnd, inputs=List(x7125, x7091)).name("x7133").srcCtx("VecMatMultBiasAdd.scala:42:6") } // And(x7125,x7091)
    val x7134 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7093, x7095)).name("x7134").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7093,x7095)
    val x7135 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7127, x7134, x7093)).name("x7135").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7127,x7134,x7093)
    val x7136 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7126, x7127)).name("x7136").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7126,x7127)
    val x7137 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7097, x7099)).name("x7137").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7097,x7099)
    val x7138 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7129, x7137, x7097)).name("x7138").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7129,x7137,x7097)
    val x7139 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7128, x7129)).name("x7139").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7128,x7129)
    val x7140 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7101, x7103)).name("x7140").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7101,x7103)
    val x7141 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7131, x7140, x7101)).name("x7141").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7131,x7140,x7101)
    val x7142 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7130, x7131)).name("x7142").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7130,x7131)
    val x7143 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7105, x7107)).name("x7143").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7105,x7107)
    val x7144 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7133, x7143, x7105)).name("x7144").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7133,x7143,x7105)
    val x7145 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7132, x7133)).name("x7145").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7132,x7133)
    val x7146 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7135, x7138)).name("x7146").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7135,x7138)
    val x7147 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7139, x7146, x7135)).name("x7147").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7139,x7146,x7135)
    val x7148 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7136, x7139)).name("x7148").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7136,x7139)
    val x7149 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7141, x7144)).name("x7149").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7141,x7144)
    val x7150 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7145, x7149, x7141)).name("x7150").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7145,x7149,x7141)
    val x7151 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7142, x7145)).name("x7151").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7142,x7145)
    val x7152 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7147, x7150)).name("x7152").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7147,x7150)
    val x7153 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7151, x7152, x7147)).name("x7153").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7151,x7152,x7147)
    val x7154 = withCtrl(x7159) { OpDef(op=BitOr, inputs=List(x7148, x7151)).name("x7154").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Or(x7148,x7151)
    val x7155 = withCtrl(x7159) { OpDef(op=FixEql, inputs=List(b3117, Const(0))).name("x7155").srcCtx("VecMatMultBiasAdd.scala:42:6") } // FixEql(b3117,Const(0))
    val x7156 = withCtrl(x7159) { OpDef(op=FixAdd, inputs=List(x7153, x7109)).name("x7156").srcCtx("VecMatMultBiasAdd.scala:42:8") } // FixAdd(x7153,x7109)
    val x7157 = withCtrl(x7159) { OpDef(op=MuxOp, inputs=List(x7155, x7153, x7156)).name("x7157").srcCtx("VecMatMultBiasAdd.scala:42:6") } // Mux(x7155,x7153,x7156)
    val x7158 = withCtrl(x7159) { StoreBanks(List(List(x6840_d0_b0, x6840_d0_b1), List(x6840_d1_b0)), List(b3407, b3408), x7157).name("x7158").srcCtx("VecMatMultBiasAdd.scala:42:6") } // ParSRAMStore(x6840,List(ArrayBuffer(b3407, b3408)),List(x7157),List(x7091))
    antiDepsOf(x7158)=List(x7108)
    val x7161 = withCtrl(x7316) { Counter(min=Const(0), max=Const(4), step=Const(1), par=2).name("x7161").srcCtx("VecMatMultBiasAdd.scala:44:26") } // CounterNew(Const(0),Const(4),Const(1),Const(2))
    val x7162 = withCtrl(x7316) { CounterChain(List(x7161)).name("x7162").srcCtx("VecMatMultBiasAdd.scala:44:42") } // CounterChainNew(List(x7161))
    val x7188 = withCtrl(x7316) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7162).name("x7188").srcCtx("VecMatMultBiasAdd.scala:44:42") } // UnrolledForeach(List(b3109, b3105),x7162,Block(Const(())),List(List(b3485, b3486)),List(List(b3487, b3488)))
    val b3485 = withCtrl(x7188) { CounterIter(x7161, Some(0)).name("b3485") } // b3485
    val b3487 = withCtrl(x7188) { Const(true).name("b3487") } // b3487
    val b3486 = withCtrl(x7188) { CounterIter(x7161, Some(1)).name("b3486") } // b3486
    val b3488 = withCtrl(x7188) { Const(true).name("b3488") } // b3488
    val x7187 = withCtrl(x7188) { UnitController(style=ForkJoin, level=OuterControl).name("x7187").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b3109, b3105),Block(Const(())))
    val x7163 = withCtrl(x7187) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7163").srcCtx("VecMatMultBiasAdd.scala:45:34") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7164 = withCtrl(x7187) { CounterChain(List(x7163)).name("x7164").srcCtx("VecMatMultBiasAdd.scala:45:48") } // CounterChainNew(List(x7163))
    val x7174 = withCtrl(x7187) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7164).name("x7174").srcCtx("VecMatMultBiasAdd.scala:45:48") } // UnrolledForeach(List(b3487, b3109, b3105),x7164,Block(Const(())),List(List(b3493)),List(List(b3494)))
    val b3493 = withCtrl(x7174) { CounterIter(x7163, None).name("b3493") } // b3493
    val b3494 = withCtrl(x7174) { Const(true).name("b3494") } // b3494
    val x7165 = withCtrl(x7174) { OpDef(op=BitAnd, inputs=List(b3494, b3487)).name("x7165").srcCtx("UnrollingBase.scala:28:66") } // And(b3494,b3487)
    val x7166 = withCtrl(x7174) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7166").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7167 = withCtrl(x7174) { OpDef(op=BitAnd, inputs=List(x7165, x7166)).name("x7167").srcCtx("UnrollingBase.scala:28:66") } // And(x7165,x7166)
    val x7168 = withCtrl(x7174) { LoadBanks(List(x6840_d0_b0), List(b3485, b3493)).name("x7168").srcCtx("VecMatMultBiasAdd.scala:46:32") } // ParSRAMLoad(x6840,List(List(b3485, b3493)),List(x7167))
    val x7169 = withCtrl(x7174) { x7168 } // VectorApply(x7168,0)
    val x7170 = withCtrl(x7174) { LoadBanks(List(x6774_d0_b0), List(b3108, b3485, b3493)).name("x7170").srcCtx("VecMatMultBiasAdd.scala:46:56") } // ParSRAMLoad(x6774,List(List(b3108, b3485, b3493)),List(x7167))
    val x7171 = withCtrl(x7174) { x7170 } // VectorApply(x7170,0)
    val x7172 = withCtrl(x7174) { OpDef(op=FixAdd, inputs=List(x7169, x7171)).name("x7172").srcCtx("VecMatMultBiasAdd.scala:46:53:foldVal") } // FixAdd(x7169,x7171)
    val x7173 = withCtrl(x7174) { StoreBanks(List(List(x6841_d0_b0)), List(b3485, b3493), x7172).name("x7173").srcCtx("VecMatMultBiasAdd.scala:47:37") } // ParSRAMStore(x6841,List(List(b3485, b3493)),List(x7172),List(x7167))
    val x7175 = withCtrl(x7187) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7175").srcCtx("VecMatMultBiasAdd.scala:45:34") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7176 = withCtrl(x7187) { CounterChain(List(x7175)).name("x7176").srcCtx("VecMatMultBiasAdd.scala:45:48") } // CounterChainNew(List(x7175))
    val x7186 = withCtrl(x7187) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7176).name("x7186").srcCtx("VecMatMultBiasAdd.scala:45:48") } // UnrolledForeach(List(b3488, b3109, b3105),x7176,Block(Const(())),List(List(b3505)),List(List(b3506)))
    val b3505 = withCtrl(x7186) { CounterIter(x7175, None).name("b3505") } // b3505
    val b3506 = withCtrl(x7186) { Const(true).name("b3506") } // b3506
    val x7177 = withCtrl(x7186) { OpDef(op=BitAnd, inputs=List(b3506, b3488)).name("x7177").srcCtx("UnrollingBase.scala:28:66") } // And(b3506,b3488)
    val x7178 = withCtrl(x7186) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7178").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7179 = withCtrl(x7186) { OpDef(op=BitAnd, inputs=List(x7177, x7178)).name("x7179").srcCtx("UnrollingBase.scala:28:66") } // And(x7177,x7178)
    val x7180 = withCtrl(x7186) { LoadBanks(List(x6840_d0_b1), List(b3486, b3505)).name("x7180").srcCtx("VecMatMultBiasAdd.scala:46:32") } // ParSRAMLoad(x6840,List(List(b3486, b3505)),List(x7179))
    val x7181 = withCtrl(x7186) { x7180 } // VectorApply(x7180,0)
    val x7182 = withCtrl(x7186) { LoadBanks(List(x6774_d0_b1), List(b3108, b3486, b3505)).name("x7182").srcCtx("VecMatMultBiasAdd.scala:46:56") } // ParSRAMLoad(x6774,List(List(b3108, b3486, b3505)),List(x7179))
    val x7183 = withCtrl(x7186) { x7182 } // VectorApply(x7182,0)
    val x7184 = withCtrl(x7186) { OpDef(op=FixAdd, inputs=List(x7181, x7183)).name("x7184").srcCtx("VecMatMultBiasAdd.scala:46:53:foldVal") } // FixAdd(x7181,x7183)
    val x7185 = withCtrl(x7186) { StoreBanks(List(List(x6841_d0_b1)), List(b3486, b3505), x7184).name("x7185").srcCtx("VecMatMultBiasAdd.scala:47:37") } // ParSRAMStore(x6841,List(List(b3486, b3505)),List(x7184),List(x7179))
    val x7189 = withCtrl(x7316) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x7189").srcCtx("GateMetaPipe.scala:13:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x7190 = withCtrl(x7316) { CounterChain(List(x7189)).name("x7190").srcCtx("GateMetaPipe.scala:13:27") } // CounterChainNew(List(x7189))
    val x7315 = withCtrl(x7316) { LoopController(style=MetaPipe, level=OuterControl, cchain=x7190).name("x7315").srcCtx("GateMetaPipe.scala:13:27") } // UnrolledForeach(List(b3109, b3105),x7190,Block(Const(())),List(List(b3521)),List(List(b3522)))
    val b3521 = withCtrl(x7315) { CounterIter(x7189, Some(0)).name("b3521") } // b3521
    val b3522 = withCtrl(x7315) { Const(true).name("b3522") } // b3522
    val x7191 = withCtrl(x7315) { FIFO(size=16).name("x7191").srcCtx("GateMetaPipe.scala:14:25:sigQ") } // x7191 = FIFONew(Const(16))
    isAccum(x7191) = false
    bufferDepthOf(x7191) = 2
    val x7192 = withCtrl(x7315) { FIFO(size=16).name("x7192").srcCtx("GateMetaPipe.scala:15:26:sigQQ") } // x7192 = FIFONew(Const(16))
    isAccum(x7192) = false
    bufferDepthOf(x7192) = 2
    val x7193 = withCtrl(x7315) { FIFO(size=16).name("x7193").srcCtx("GateMetaPipe.scala:16:31:sigEleMuxQ") } // x7193 = FIFONew(Const(16))
    isAccum(x7193) = false
    bufferDepthOf(x7193) = 2
    val x7194 = withCtrl(x7315) { FIFO(size=16).name("x7194").srcCtx("GateMetaPipe.scala:18:25:actQ") } // x7194 = FIFONew(Const(16))
    isAccum(x7194) = false
    bufferDepthOf(x7194) = 2
    val x7195 = withCtrl(x7315) { FIFO(size=16).name("x7195").srcCtx("GateMetaPipe.scala:20:29:hUpdateQ") } // x7195 = FIFONew(Const(16))
    isAccum(x7195) = false
    bufferDepthOf(x7195) = 1
    val x7196 = withCtrl(x7315) { FIFO(size=16).name("x7196").srcCtx("GateMetaPipe.scala:21:29:hLinearQ") } // x7196 = FIFONew(Const(16))
    isAccum(x7196) = false
    bufferDepthOf(x7196) = 1
    val x7197 = withCtrl(x7315) { FIFO(size=16).name("x7197").srcCtx("GateMetaPipe.scala:23:29:cUpdateQ") } // x7197 = FIFONew(Const(16))
    isAccum(x7197) = false
    bufferDepthOf(x7197) = 1
    val x7198 = withCtrl(x7315) { FIFO(size=16).name("x7198").srcCtx("GateMetaPipe.scala:24:27:cLastQ") } // x7198 = FIFONew(Const(16))
    isAccum(x7198) = false
    bufferDepthOf(x7198) = 1
    val x7199 = withCtrl(x7315) { FIFO(size=16).name("x7199").srcCtx("GateMetaPipe.scala:26:33:cNewMultAddQ") } // x7199 = FIFONew(Const(16))
    isAccum(x7199) = false
    bufferDepthOf(x7199) = 1
    val x7200 = withCtrl(x7315) { FIFO(size=16).name("x7200").srcCtx("GateMetaPipe.scala:27:34:cNewMultAddQQ") } // x7200 = FIFONew(Const(16))
    isAccum(x7200) = false
    bufferDepthOf(x7200) = 1
    val x7201 = withCtrl(x7315) { FIFO(size=16).name("x7201").srcCtx("GateMetaPipe.scala:28:30:cNewMultQ") } // x7201 = FIFONew(Const(16))
    isAccum(x7201) = false
    bufferDepthOf(x7201) = 1
    val x7202 = withCtrl(x7315) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7202").srcCtx("GateMetaPipe.scala:30:34") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7203 = withCtrl(x7315) { CounterChain(List(x7202)).name("x7203").srcCtx("GateMetaPipe.scala:30:48") } // CounterChainNew(List(x7202))
    val x7226 = withCtrl(x7315) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7203).name("x7226").srcCtx("GateMetaPipe.scala:30:48") } // UnrolledForeach(List(b3522, b3109, b3105),x7203,Block(Const(())),List(List(b3536)),List(List(b3537)))
    val b3536 = withCtrl(x7226) { CounterIter(x7202, None).name("b3536") } // b3536
    val b3537 = withCtrl(x7226) { Const(true).name("b3537") } // b3537
    val x7204 = withCtrl(x7226) { OpDef(op=BitAnd, inputs=List(b3537, b3522)).name("x7204").srcCtx("UnrollingBase.scala:28:66") } // And(b3537,b3522)
    val x7205 = withCtrl(x7226) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7205").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7206 = withCtrl(x7226) { OpDef(op=BitAnd, inputs=List(x7204, x7205)).name("x7206").srcCtx("UnrollingBase.scala:28:66") } // And(x7204,x7205)
    val x7207 = withCtrl(x7226) { LoadBanks(List(x6841_d0_b0, x6841_d0_b1), List(b3521, b3536)).name("x7207").srcCtx("GateMetaPipe.scala:31:27:pEle") } // ParSRAMLoad(x6841,List(List(b3521, b3536)),List(x7206))
    val x7208 = withCtrl(x7226) { x7207 } // VectorApply(x7207,0)
    val x7209_d0_b0 = withCtrl(x7226) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7209_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x7209 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x7209_d0_b0) = false
    bufferDepthOf(x7209_d0_b0) = 1
    staticDimsOf(x7209_d0_b0) = List(1024)
    val x7210 = withCtrl(x7226) { OpDef(op=FixSub, inputs=List(x7208, Const(-8.0))).name("x7210").srcCtx("NonLinearity.scala:44:22") } // FixSub(x7208,Const(-8))
    val x7211 = withCtrl(x7226) { OpDef(op=FixSla, inputs=List(x7210, Const(6))).name("x7211").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x7210,Const(6))
    // x7212 = FixConvert(x7211,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x7212 = withCtrl(x7226) { OpDef(op=FixSra, inputs=List(x7211, Const("24"))).name("x7212").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x7211,TRUE,_32,_0)
    // }
    val x7213 = withCtrl(x7226) { LoadBanks(List(x7209_d0_b0), List(x7212)).name("x7213").srcCtx("NonLinearity.scala:45:17:sigVal") } // LUTLoad(x7209,List(x7212),x7206)
    val x7214_d0_b0 = withCtrl(x7226) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7214_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x7214 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x7214_d0_b0) = false
    bufferDepthOf(x7214_d0_b0) = 1
    staticDimsOf(x7214_d0_b0) = List(1024)
    val x7215 = withCtrl(x7226) { LoadBanks(List(x7214_d0_b0), List(x7212)).name("x7215").srcCtx("NonLinearity.scala:45:17:actVal") } // LUTLoad(x7214,List(x7212),x7206)
    val x7216 = withCtrl(x7226) { OpDef(op=FixLt, inputs=List(Const(8.0), x7208)).name("x7216").srcCtx("GateMetaPipe.scala:35:27:isHigh") } // FixLt(Const(8),x7208)
    val x7217 = withCtrl(x7226) { OpDef(op=FixLt, inputs=List(x7208, Const(-8.0))).name("x7217").srcCtx("GateMetaPipe.scala:36:26:isLow") } // FixLt(x7208,Const(-8))
    val x7218 = withCtrl(x7226) { OpDef(op=MuxOp, inputs=List(x7217, Const(0.0), x7213)).name("x7218").srcCtx("GateMetaPipe.scala:38:46") } // Mux(x7217,Const(0),x7213)
    val x7219 = withCtrl(x7226) { OpDef(op=MuxOp, inputs=List(x7216, Const(1.0), x7218)).name("x7219").srcCtx("GateMetaPipe.scala:38:25:sigEle") } // Mux(x7216,Const(1),x7218)
    val x7220 = withCtrl(x7226) { OpDef(op=MuxOp, inputs=List(x7217, Const(-1.0), x7215)).name("x7220").srcCtx("GateMetaPipe.scala:39:46") } // Mux(x7217,Const(-1),x7215)
    val x7221 = withCtrl(x7226) { OpDef(op=MuxOp, inputs=List(x7216, Const(1.0), x7220)).name("x7221").srcCtx("GateMetaPipe.scala:39:25:actEle") } // Mux(x7216,Const(1),x7220)
    val x7222_x7191 = withCtrl(x7226) { WriteMem(x7191, x7219).name("x7222_x7191").srcCtx("GateMetaPipe.scala:41:17") } // ParFIFOEnq(x7191,List(x7219),List(x7206))
    val x7223_x7192 = withCtrl(x7226) { WriteMem(x7192, x7219).name("x7223_x7192").srcCtx("GateMetaPipe.scala:42:18") } // ParFIFOEnq(x7192,List(x7219),List(x7206))
    val x7224_x7193 = withCtrl(x7226) { WriteMem(x7193, x7219).name("x7224_x7193").srcCtx("GateMetaPipe.scala:43:23") } // ParFIFOEnq(x7193,List(x7219),List(x7206))
    val x7225_x7194 = withCtrl(x7226) { WriteMem(x7194, x7221).name("x7225_x7194").srcCtx("GateMetaPipe.scala:45:17") } // ParFIFOEnq(x7194,List(x7221),List(x7206))
    val x7314 = withCtrl(x7315) { UnitController(style=SeqPipe, level=OuterControl).name("x7314").srcCtx("GateMetaPipe.scala:48:12") } // UnitPipe(List(b3522, b3109, b3105),Block(Const(())))
    val x7227 = withCtrl(x7314) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7227").srcCtx("GateMetaPipe.scala:49:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7228 = withCtrl(x7314) { CounterChain(List(x7227)).name("x7228").srcCtx("GateMetaPipe.scala:49:50") } // CounterChainNew(List(x7227))
    val x7245 = withCtrl(x7314) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7228).name("x7245").srcCtx("GateMetaPipe.scala:49:50") } // UnrolledForeach(List(b3522, b3109, b3105),x7228,Block(Const(())),List(List(b3563)),List(List(b3564)))
    val b3563 = withCtrl(x7245) { CounterIter(x7227, None).name("b3563") } // b3563
    val b3564 = withCtrl(x7245) { Const(true).name("b3564") } // b3564
    val x7229 = withCtrl(x7245) { OpDef(op=BitAnd, inputs=List(b3564, b3522)).name("x7229").srcCtx("UnrollingBase.scala:28:66") } // And(b3564,b3522)
    val x7230 = withCtrl(x7245) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7230").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7231 = withCtrl(x7245) { OpDef(op=BitAnd, inputs=List(x7229, x7230)).name("x7231").srcCtx("UnrollingBase.scala:28:66") } // And(x7229,x7230)
    val x7232 = withCtrl(x7245) { ReadMem(x7191).name("x7232").srcCtx("GateMetaPipe.scala:50:32:sigEle") } // ParFIFODeq(x7191,List(x7231))
    val x7233 = withCtrl(x7245) { x7232 } // VectorApply(x7232,0)
    val x7234 = withCtrl(x7245) { ReadMem(x7194).name("x7234").srcCtx("GateMetaPipe.scala:51:32:actEle") } // ParFIFODeq(x7194,List(x7231))
    val x7235 = withCtrl(x7245) { x7234 } // VectorApply(x7234,0)
    val x7236 = withCtrl(x7245) { LoadBanks(List(x6650_d0_b0), List(b3104, b3563)).name("x7236").srcCtx("GateMetaPipe.scala:53:24:cLast") } // ParSRAMLoad(x6650,List(List(b3104, b3563)),List(x7231))
    val x7237 = withCtrl(x7245) { x7236 } // VectorApply(x7236,0)
    val x7238 = withCtrl(x7245) { OpDef(op=FixMul, inputs=List(x7237, x7235)).name("x7238").srcCtx("GateMetaPipe.scala:54:32:cNewMult") } // FixMul(x7237,x7235)
    val x7239 = withCtrl(x7245) { OpDef(op=FixMul, inputs=List(x7233, x7237)).name("x7239").srcCtx("GateMetaPipe.scala:55:36") } // FixMul(x7233,x7237)
    val x7240 = withCtrl(x7245) { OpDef(op=FixAdd, inputs=List(x7239, x7238)).name("x7240").srcCtx("GateMetaPipe.scala:55:44:cNewMultAdd") } // FixAdd(x7239,x7238)
    val x7241_x7201 = withCtrl(x7245) { WriteMem(x7201, x7238).name("x7241_x7201").srcCtx("GateMetaPipe.scala:57:24") } // ParFIFOEnq(x7201,List(x7238),List(x7231))
    val x7242_x7199 = withCtrl(x7245) { WriteMem(x7199, x7240).name("x7242_x7199").srcCtx("GateMetaPipe.scala:58:27") } // ParFIFOEnq(x7199,List(x7240),List(x7231))
    val x7243_x7200 = withCtrl(x7245) { WriteMem(x7200, x7240).name("x7243_x7200").srcCtx("GateMetaPipe.scala:59:28") } // ParFIFOEnq(x7200,List(x7240),List(x7231))
    val x7244_x7198 = withCtrl(x7245) { WriteMem(x7198, x7237).name("x7244_x7198").srcCtx("GateMetaPipe.scala:60:21") } // ParFIFOEnq(x7198,List(x7237),List(x7231))
    val x7246 = withCtrl(x7314) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7246").srcCtx("GateMetaPipe.scala:63:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7247 = withCtrl(x7314) { CounterChain(List(x7246)).name("x7247").srcCtx("GateMetaPipe.scala:63:50") } // CounterChainNew(List(x7246))
    val x7266 = withCtrl(x7314) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7247).name("x7266").srcCtx("GateMetaPipe.scala:63:50") } // UnrolledForeach(List(b3522, b3109, b3105),x7247,Block(Const(())),List(List(b3584)),List(List(b3585)))
    val b3584 = withCtrl(x7266) { CounterIter(x7246, None).name("b3584") } // b3584
    val b3585 = withCtrl(x7266) { Const(true).name("b3585") } // b3585
    val x7248 = withCtrl(x7266) { OpDef(op=BitAnd, inputs=List(b3585, b3522)).name("x7248").srcCtx("UnrollingBase.scala:28:66") } // And(b3585,b3522)
    val x7249 = withCtrl(x7266) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7249").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7250 = withCtrl(x7266) { OpDef(op=BitAnd, inputs=List(x7248, x7249)).name("x7250").srcCtx("UnrollingBase.scala:28:66") } // And(x7248,x7249)
    val x7251 = withCtrl(x7266) { ReadMem(x7201).name("x7251").srcCtx("GateMetaPipe.scala:64:39:cNewMult") } // ParFIFODeq(x7201,List(x7250))
    val x7252 = withCtrl(x7266) { x7251 } // VectorApply(x7251,0)
    val x7253 = withCtrl(x7266) { ReadMem(x7193).name("x7253").srcCtx("GateMetaPipe.scala:65:38:sigEle") } // ParFIFODeq(x7193,List(x7250))
    val x7254 = withCtrl(x7266) { x7253 } // VectorApply(x7253,0)
    val x7255 = withCtrl(x7266) { ReadMem(x7199).name("x7255").srcCtx("GateMetaPipe.scala:66:45:cNewMultAdd") } // ParFIFODeq(x7199,List(x7250))
    val x7256 = withCtrl(x7266) { x7255 } // VectorApply(x7255,0)
    val x7257 = withCtrl(x7266) { ReadMem(x7198).name("x7257").srcCtx("GateMetaPipe.scala:67:33:cLast") } // ParFIFODeq(x7198,List(x7250))
    val x7258 = withCtrl(x7266) { x7257 } // VectorApply(x7257,0)
    val x7259 = withCtrl(x7266) { OpDef(op=FixEql, inputs=List(b3521, Const(0))).name("x7259").srcCtx("package.scala:110:40") } // FixEql(b3521,Const(0))
    val x7260 = withCtrl(x7266) { OpDef(op=FixEql, inputs=List(b3521, Const(1))).name("x7260").srcCtx("package.scala:113:40") } // FixEql(b3521,Const(1))
    val x7261 = withCtrl(x7266) { OpDef(op=FixEql, inputs=List(b3521, Const(2))).name("x7261").srcCtx("package.scala:116:40") } // FixEql(b3521,Const(2))
    val x7262 = withCtrl(x7266) { OpDef(op=MuxOp, inputs=List(x7261, x7256, x7258)).name("x7262").srcCtx("GateMetaPipe.scala:72:30") } // Mux(x7261,x7256,x7258)
    val x7263 = withCtrl(x7266) { OpDef(op=MuxOp, inputs=List(x7260, x7252, x7262)).name("x7263").srcCtx("GateMetaPipe.scala:71:30") } // Mux(x7260,x7252,x7262)
    val x7264 = withCtrl(x7266) { OpDef(op=MuxOp, inputs=List(x7259, x7254, x7263)).name("x7264").srcCtx("GateMetaPipe.scala:70:28:cUpdate") } // Mux(x7259,x7254,x7263)
    val x7265_x7197 = withCtrl(x7266) { WriteMem(x7197, x7264).name("x7265_x7197").srcCtx("GateMetaPipe.scala:75:23") } // ParFIFOEnq(x7197,List(x7264),List(x7250))
    val x7267 = withCtrl(x7314) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7267").srcCtx("GateMetaPipe.scala:78:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7268 = withCtrl(x7314) { CounterChain(List(x7267)).name("x7268").srcCtx("GateMetaPipe.scala:78:50") } // CounterChainNew(List(x7267))
    val x7284 = withCtrl(x7314) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7268).name("x7284").srcCtx("GateMetaPipe.scala:78:50") } // UnrolledForeach(List(b3522, b3109, b3105),x7268,Block(Const(())),List(List(b3607)),List(List(b3608)))
    val b3607 = withCtrl(x7284) { CounterIter(x7267, None).name("b3607") } // b3607
    val b3608 = withCtrl(x7284) { Const(true).name("b3608") } // b3608
    val x7269 = withCtrl(x7284) { OpDef(op=BitAnd, inputs=List(b3608, b3522)).name("x7269").srcCtx("UnrollingBase.scala:28:66") } // And(b3608,b3522)
    val x7270 = withCtrl(x7284) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7270").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7271 = withCtrl(x7284) { OpDef(op=BitAnd, inputs=List(x7269, x7270)).name("x7271").srcCtx("UnrollingBase.scala:28:66") } // And(x7269,x7270)
    val x7272 = withCtrl(x7284) { ReadMem(x7200).name("x7272").srcCtx("GateMetaPipe.scala:79:46:cNewMultAdd") } // ParFIFODeq(x7200,List(x7271))
    val x7273 = withCtrl(x7284) { x7272 } // VectorApply(x7272,0)
    val x7274_d0_b0 = withCtrl(x7284) { LUT(inits=Nil, banking=Strided(banks=1, stride=1)).name("x7274_d0_b0").srcCtx("NonLinearity.scala:43:37:lut") } // x7274 = LUTNew(List(1024), Seq(Const(-0.9999997615814208984375),Const(-0.9999997615814208984375),Const(-0.999999701976776123046875)... [1021 more]))
    isAccum(x7274_d0_b0) = false
    bufferDepthOf(x7274_d0_b0) = 1
    staticDimsOf(x7274_d0_b0) = List(1024)
    val x7275 = withCtrl(x7284) { OpDef(op=FixSub, inputs=List(x7273, Const(-8.0))).name("x7275").srcCtx("NonLinearity.scala:44:22") } // FixSub(x7273,Const(-8))
    val x7276 = withCtrl(x7284) { OpDef(op=FixSla, inputs=List(x7275, Const(6))).name("x7276").srcCtx("NonLinearity.scala:44:30") } // FixLsh(x7275,Const(6))
    // x7277 = FixConvert(x7276,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x7277 = withCtrl(x7284) { OpDef(op=FixSra, inputs=List(x7276, Const("24"))).name("x7277").srcCtx("NonLinearity.scala:44:41:index") } // FixConvert(x7276,TRUE,_32,_0)
    // }
    val x7278 = withCtrl(x7284) { LoadBanks(List(x7274_d0_b0), List(x7277)).name("x7278").srcCtx("NonLinearity.scala:45:17:actValMultAdd") } // LUTLoad(x7274,List(x7277),x7271)
    val x7279 = withCtrl(x7284) { OpDef(op=FixLt, inputs=List(Const(8.0), x7273)).name("x7279").srcCtx("GateMetaPipe.scala:82:36:isHigh") } // FixLt(Const(8),x7273)
    val x7280 = withCtrl(x7284) { OpDef(op=FixLt, inputs=List(x7273, Const(-8.0))).name("x7280").srcCtx("GateMetaPipe.scala:83:35:isLow") } // FixLt(x7273,Const(-8))
    val x7281 = withCtrl(x7284) { OpDef(op=MuxOp, inputs=List(x7280, Const(-1.0), x7278)).name("x7281").srcCtx("GateMetaPipe.scala:84:49") } // Mux(x7280,Const(-1),x7278)
    val x7282 = withCtrl(x7284) { OpDef(op=MuxOp, inputs=List(x7279, Const(1.0), x7281)).name("x7282").srcCtx("GateMetaPipe.scala:84:28:hLinear") } // Mux(x7279,Const(1),x7281)
    def split2 = {
    val x7283_x7196 = withCtrl(x7284) { WriteMem(x7196, x7282).name("x7283_x7196").srcCtx("GateMetaPipe.scala:86:23") } // ParFIFOEnq(x7196,List(x7282),List(x7271))
    val x7285 = withCtrl(x7314) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7285").srcCtx("GateMetaPipe.scala:89:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7286 = withCtrl(x7314) { CounterChain(List(x7285)).name("x7286").srcCtx("GateMetaPipe.scala:89:50") } // CounterChainNew(List(x7285))
    val x7300 = withCtrl(x7314) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7286).name("x7300").srcCtx("GateMetaPipe.scala:89:50") } // UnrolledForeach(List(b3522, b3109, b3105),x7286,Block(Const(())),List(List(b3627)),List(List(b3628)))
    val b3627 = withCtrl(x7300) { CounterIter(x7285, None).name("b3627") } // b3627
    val b3628 = withCtrl(x7300) { Const(true).name("b3628") } // b3628
    val x7287 = withCtrl(x7300) { OpDef(op=BitAnd, inputs=List(b3628, b3522)).name("x7287").srcCtx("UnrollingBase.scala:28:66") } // And(b3628,b3522)
    val x7288 = withCtrl(x7300) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7288").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7289 = withCtrl(x7300) { OpDef(op=BitAnd, inputs=List(x7287, x7288)).name("x7289").srcCtx("UnrollingBase.scala:28:66") } // And(x7287,x7288)
    val x7290 = withCtrl(x7300) { ReadMem(x7196).name("x7290").srcCtx("GateMetaPipe.scala:91:37:hLinear") } // ParFIFODeq(x7196,List(x7289))
    val x7291 = withCtrl(x7300) { x7290 } // VectorApply(x7290,0)
    val x7292 = withCtrl(x7300) { ReadMem(x7192).name("x7292").srcCtx("GateMetaPipe.scala:92:33:sigEle") } // ParFIFODeq(x7192,List(x7289))
    val x7293 = withCtrl(x7300) { x7292 } // VectorApply(x7292,0)
    val x7294 = withCtrl(x7300) { LoadBanks(List(x6675_d0_b0), List(b3104, b3627)).name("x7294").srcCtx("GateMetaPipe.scala:94:24:hLast") } // ParSRAMLoad(x6675,List(List(b3104, b3627)),List(x7289))
    val x7295 = withCtrl(x7300) { x7294 } // VectorApply(x7294,0)
    val x7296 = withCtrl(x7300) { OpDef(op=FixMul, inputs=List(x7291, x7293)).name("x7296").srcCtx("GateMetaPipe.scala:95:30:hNew") } // FixMul(x7291,x7293)
    val x7297 = withCtrl(x7300) { OpDef(op=FixEql, inputs=List(b3521, Const(3))).name("x7297").srcCtx("package.scala:119:40") } // FixEql(b3521,Const(3))
    val x7298 = withCtrl(x7300) { OpDef(op=MuxOp, inputs=List(x7297, x7296, x7295)).name("x7298").srcCtx("GateMetaPipe.scala:96:28:hUpdate") } // Mux(x7297,x7296,x7295)
    val x7299_x7195 = withCtrl(x7300) { WriteMem(x7195, x7298).name("x7299_x7195").srcCtx("GateMetaPipe.scala:98:23") } // ParFIFOEnq(x7195,List(x7298),List(x7289))
    val x7301 = withCtrl(x7314) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7301").srcCtx("GateMetaPipe.scala:101:36") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7302 = withCtrl(x7314) { CounterChain(List(x7301)).name("x7302").srcCtx("GateMetaPipe.scala:101:50") } // CounterChainNew(List(x7301))
    val x7313 = withCtrl(x7314) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7302).name("x7313").srcCtx("GateMetaPipe.scala:101:50") } // UnrolledForeach(List(b3522, b3109, b3105),x7302,Block(Const(())),List(List(b3645)),List(List(b3646)))
    val b3645 = withCtrl(x7313) { CounterIter(x7301, None).name("b3645") } // b3645
    val b3646 = withCtrl(x7313) { Const(true).name("b3646") } // b3646
    val x7303 = withCtrl(x7313) { OpDef(op=BitAnd, inputs=List(b3646, b3522)).name("x7303").srcCtx("UnrollingBase.scala:28:66") } // And(b3646,b3522)
    val x7304 = withCtrl(x7313) { OpDef(op=BitAnd, inputs=List(b3109, b3105)).name("x7304").srcCtx("UnrollingBase.scala:28:66") } // And(b3109,b3105)
    val x7305 = withCtrl(x7313) { OpDef(op=BitAnd, inputs=List(x7303, x7304)).name("x7305").srcCtx("UnrollingBase.scala:28:66") } // And(x7303,x7304)
    val x7306 = withCtrl(x7313) { ReadMem(x7195).name("x7306").srcCtx("GateMetaPipe.scala:102:34:hNew") } // ParFIFODeq(x7195,List(x7305))
    val x7307 = withCtrl(x7313) { x7306 } // VectorApply(x7306,0)
    val x7308 = withCtrl(x7313) { ReadMem(x7197).name("x7308").srcCtx("GateMetaPipe.scala:103:34:cNew") } // ParFIFODeq(x7197,List(x7305))
    val x7309 = withCtrl(x7313) { x7308 } // VectorApply(x7308,0)
    val x7310 = withCtrl(x7313) { StoreBanks(List(List(x6675_d0_b0), List(x6675_d1_b0)), List(b3104, b3645), x7307).name("x7310").srcCtx("GateMetaPipe.scala:104:34") } // ParSRAMStore(x6675,List(List(b3104, b3645)),List(x7307),List(x7305))
    val x7311 = withCtrl(x7313) { StoreBanks(List(List(x6805_d0_b0), List(x6805_d1_b0)), List(b3108, b3645), x7307).name("x7311").srcCtx("GateMetaPipe.scala:105:33") } // ParSRAMStore(x6805,List(List(b3108, b3645)),List(x7307),List(x7305))
    val x7312 = withCtrl(x7313) { StoreBanks(List(List(x6650_d0_b0)), List(b3104, b3645), x7309).name("x7312").srcCtx("GateMetaPipe.scala:106:34") } // ParSRAMStore(x6650,List(List(b3104, b3645)),List(x7309),List(x7305))
    val x7319 = withCtrl(x7357) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x7319").srcCtx("LSTMPipeStep.scala:26:59") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x7320 = withCtrl(x7357) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x7320").srcCtx("LSTMPipeStep.scala:26:59") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x7321 = withCtrl(x7357) { CounterChain(List(x7319,x7320)).name("x7321").srcCtx("LSTMPipeStep.scala:26:59") } // CounterChainNew(List(x7319, x7320))
    val x7356 = withCtrl(x7357) { LoopController(style=StreamPipe, level=OuterControl, cchain=x7321).name("x7356").srcCtx("LSTMPipeStep.scala:26:59") } // UnrolledForeach(List(b3105),x7321,Block(Const(())),List(List(b3666), List(b3667)),List(List(b3668), List(b3669)))
    val b3666 = withCtrl(x7356) { CounterIter(x7319, Some(0)).name("b3666") } // b3666
    val b3668 = withCtrl(x7356) { Const(true).name("b3668") } // b3668
    val b3667 = withCtrl(x7356) { CounterIter(x7320, Some(0)).name("b3667") } // b3667
    val b3669 = withCtrl(x7356) { Const(true).name("b3669") } // b3669
    val b7397 = withCtrl(x7356) { StreamOut(field="offset").name("b7397").srcCtx("LSTMPipeStep.scala:26:59") } // x7322 = StreamOutNew(BurstCmdBus)
    isAccum(b7397) = false
    bufferDepthOf(b7397) = 1
    val b7398 = withCtrl(x7356) { StreamOut(field="size").name("b7398").srcCtx("LSTMPipeStep.scala:26:59") } // x7322 = StreamOutNew(BurstCmdBus)
    isAccum(b7398) = false
    bufferDepthOf(b7398) = 1
    val x7323 = withCtrl(x7356) { StreamOut(field="data").name("x7323").srcCtx("LSTMPipeStep.scala:26:59") } // x7323 = StreamOutNew(BurstFullDataBus())
    isAccum(x7323) = false
    bufferDepthOf(x7323) = 1
    val x7324 = withCtrl(x7356) { StreamIn(field="ack").name("x7324").srcCtx("LSTMPipeStep.scala:26:59") } // x7324 = StreamInNew(BurstAckBus)
    isAccum(x7324) = false
    bufferDepthOf(x7324) = 1
    val x7340 = withCtrl(x7356) { UnitController(style=SeqPipe, level=InnerControl).name("x7340").srcCtx("LSTMPipeStep.scala:26:59") } // UnitPipe(List(b3668, b3669, b3105),Block(x7339))
    val x7325 = withCtrl(x7340) { b3666 } // FixConvert(b3666,TRUE,_32,_0) (Same Type. No op)
    val x7326 = withCtrl(x7340) { OpDef(op=FixSla, inputs=List(x7325, Const(10))).name("x7326").srcCtx("LSTMPipeStep.scala:26:59") } // FixLsh(x7325,Const(10))
    val x7327 = withCtrl(x7340) { b3667 } // FixConvert(b3667,TRUE,_32,_0) (Same Type. No op)
    val x7328 = withCtrl(x7340) { OpDef(op=FixSla, inputs=List(x7327, Const(7))).name("x7328").srcCtx("LSTMPipeStep.scala:26:59") } // FixLsh(x7327,Const(7))
    val x7329 = withCtrl(x7340) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x7330 = withCtrl(x7340) { OpDef(op=FixAdd, inputs=List(x7326, x7328)).name("x7330").srcCtx("LSTMPipeStep.scala:26:59") } // FixAdd(x7326,x7328)
    val x7331 = withCtrl(x7340) { OpDef(op=FixAdd, inputs=List(x7330, x7329)).name("x7331").srcCtx("LSTMPipeStep.scala:26:59") } // FixAdd(x7330,x7329)
    val x7332 = withCtrl(x7340) { OpDef(op=FixSla, inputs=List(x7331, Const(2))).name("x7332").srcCtx("LSTMPipeStep.scala:26:59") } // FixLsh(x7331,Const(2))
    val x7333 = withCtrl(x7340) { x7332 } // FixConvert(x7332,TRUE,_64,_0)
    val x7334 = withCtrl(x7340) { DramAddress(x6649).name("x7334").srcCtx("LSTMPipeStep.scala:26:59") } // GetDRAMAddress(x6649)
    val x7336_x7335 = withCtrl(x7340) { OpDef(op=FixAdd, inputs=List(x7333, x7334)).name("x7336_x7335").srcCtx("LSTMPipeStep.scala:26:59") } // FixAdd(x7333,x7334)
    // x7336 = SimpleStruct(ArrayBuffer((offset,x7335), (size,Const(512)), (isLoad,Const(false))))
    val x7337 = withCtrl(x7340) { OpDef(op=BitAnd, inputs=List(b3668, b3669)).name("x7337").srcCtx("UnrollingBase.scala:28:66") } // And(b3668,b3669)
    val x7338 = withCtrl(x7340) { OpDef(op=BitAnd, inputs=List(x7337, b3105)).name("x7338").srcCtx("UnrollingBase.scala:28:66") } // And(x7337,b3105)
    val x7339_b7399_b7397 = withCtrl(x7340) { WriteMem(b7397, x7336_x7335).name("x7339_b7399_b7397").srcCtx("LSTMPipeStep.scala:26:59") } // StreamWrite(x7322,x7336,x7338)
    val x7339_b7400_b7398 = withCtrl(x7340) { WriteMem(b7398, Const(512)).name("x7339_b7400_b7398").srcCtx("LSTMPipeStep.scala:26:59") } // StreamWrite(x7322,x7336,x7338)
    val x7341 = withCtrl(x7356) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x7341").srcCtx("LSTMPipeStep.scala:26:59") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x7342 = withCtrl(x7356) { CounterChain(List(x7341)).name("x7342").srcCtx("LSTMPipeStep.scala:26:59") } // CounterChainNew(List(x7341))
    val x7350 = withCtrl(x7356) { LoopController(style=InnerPipe, level=InnerControl, cchain=x7342).name("x7350").srcCtx("LSTMPipeStep.scala:26:59") } // UnrolledForeach(List(b3668, b3669, b3105),x7342,Block(Const(())),List(List(b3691)),List(List(b3692)))
    val b3691 = withCtrl(x7350) { CounterIter(x7341, None).name("b3691") } // b3691
    val b3692 = withCtrl(x7350) { Const(true).name("b3692") } // b3692
    val x7343 = withCtrl(x7350) { OpDef(op=BitAnd, inputs=List(b3692, b3668)).name("x7343").srcCtx("UnrollingBase.scala:28:66") } // And(b3692,b3668)
    val x7344 = withCtrl(x7350) { OpDef(op=BitAnd, inputs=List(b3669, b3105)).name("x7344").srcCtx("UnrollingBase.scala:28:66") } // And(b3669,b3105)
    val x7345 = withCtrl(x7350) { OpDef(op=BitAnd, inputs=List(x7343, x7344)).name("x7345").srcCtx("UnrollingBase.scala:28:66") } // And(x7343,x7344)
    val x7346 = withCtrl(x7350) { LoadBanks(List(x6805_d0_b0), List(b3667, b3691)).name("x7346").srcCtx("LSTMPipeStep.scala:26:59") } // ParSRAMLoad(x6805,List(List(b3667, b3691)),List(x7345))
    val x7348_x7347 = withCtrl(x7350) { x7346 } // VectorApply(x7346,0)
    // x7348 = SimpleStruct(ArrayBuffer((_1,x7347), (_2,Const(true))))
    val x7349_x7349_x7323 = withCtrl(x7350) { WriteMem(x7323, x7348_x7347).name("x7349_x7349_x7323").srcCtx("LSTMPipeStep.scala:26:59") } // ParStreamWrite(x7323,List(x7348),List(x7345))
    val x7351 = withCtrl(x7356) { FringeDenseStore(dram=List(x6649), cmdStream=List(b7397, b7398), dataStream=List(x7323), ackStream=List(x7324)).name("x7351").srcCtx("LSTMPipeStep.scala:26:59") } // FringeDenseStore(x6649,x7322,x7323,x7324)
    val x7355 = withCtrl(x7356) { UnitController(style=SeqPipe, level=InnerControl).name("x7355").srcCtx("LSTMPipeStep.scala:26:59") } // UnitPipe(List(b3668, b3669, b3105),Block(Const(())))
    val x7352 = withCtrl(x7355) { OpDef(op=BitAnd, inputs=List(b3668, b3669)).name("x7352").srcCtx("UnrollingBase.scala:28:66") } // And(b3668,b3669)
    val x7353 = withCtrl(x7355) { OpDef(op=BitAnd, inputs=List(x7352, b3105)).name("x7353").srcCtx("UnrollingBase.scala:28:66") } // And(x7352,b3105)
    val x7354_x7354 = withCtrl(x7355) { ReadMem(x7324).name("x7354_x7354").srcCtx("LSTMPipeStep.scala:26:59") } // StreamRead(x7324,x7353)
    }; split2
    }; split1
    
  }
}
