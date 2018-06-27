import pir._
import pir.node._
import arch._
import prism.enums._

object TPCHQ6 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2710 = ArgIn(init=0).name("x2710").ctrl(top).srcCtx("TPCHQ6.scala:23:25:dataSize") // ArgInNew(Const(0))
    isAccum(x2710) = false
    bufferDepthOf(x2710) = 1
    boundOf(x2710) = 1024
    val x2712 = ReadMem(x2710).name("x2712").ctrl(top).srcCtx("TPCHQ6.scala:26:28") // RegRead(x2710)
    val x2713 = DRAM(dims=List(x2712)).name("x2713").ctrl(top).srcCtx("TPCHQ6.scala:26:27:dates") // x2713 = DRAMNew(ArrayBuffer(x2712),Const(0))
    val x2714 = ReadMem(x2710).name("x2714").ctrl(top).srcCtx("TPCHQ6.scala:27:28") // RegRead(x2710)
    val x2715 = DRAM(dims=List(x2714)).name("x2715").ctrl(top).srcCtx("TPCHQ6.scala:27:27:quants") // x2715 = DRAMNew(ArrayBuffer(x2714),Const(0))
    val x2716 = ReadMem(x2710).name("x2716").ctrl(top).srcCtx("TPCHQ6.scala:28:26") // RegRead(x2710)
    val x2717 = DRAM(dims=List(x2716)).name("x2717").ctrl(top).srcCtx("TPCHQ6.scala:28:25:discts") // x2717 = DRAMNew(ArrayBuffer(x2716),Const(0))
    val x2718 = ReadMem(x2710).name("x2718").ctrl(top).srcCtx("TPCHQ6.scala:29:26") // RegRead(x2710)
    val x2719 = DRAM(dims=List(x2718)).name("x2719").ctrl(top).srcCtx("TPCHQ6.scala:29:25:prices") // x2719 = DRAMNew(ArrayBuffer(x2718),Const(0))
    val x2720 = ArgOut(init=0).name("x2720").ctrl(top).srcCtx("TPCHQ6.scala:32:21:out") // ArgOutNew(Const(0))
    isAccum(x2720) = false
    bufferDepthOf(x2720) = 1
    val x2967 = UnitController(style=SeqPipe, level=OuterControl).name("x2967").ctrl(top).srcCtx("TPCHQ6.scala:39:11") // Hwblock(Block(Const(())),false)
    val x2725_d0 = Reg(init=Some(0)).name("x2725_d0").ctrl(x2967).srcCtx("TPCHQ6.scala:43:20:acc") // x2725 = RegNew(Const(0))
    isAccum(x2725_d0) = false
    bufferDepthOf(x2725_d0) = 1
    val x2725_d1 = Reg(init=Some(0)).name("x2725_d1").ctrl(x2967).srcCtx("TPCHQ6.scala:43:20:acc") // x2725 = RegNew(Const(0))
    isAccum(x2725_d1) = true
    bufferDepthOf(x2725_d1) = 1
    val x2726 = ReadMem(x2710).name("x2726").ctrl(x2967).srcCtx("TPCHQ6.scala:44:19") // RegRead(x2710)
    val x2727 = Counter(min=Const(0), max=x2726, step=Const(32), par=2).name("x2727").ctrl(x2967).srcCtx("TPCHQ6.scala:44:34") // CounterNew(Const(0),x2726,Const(32),Const(2))
    val x2728 = CounterChain(List(x2727)).name("x2728").ctrl(x2967).srcCtx("TPCHQ6.scala:63:8") // CounterChainNew(List(x2727))
    val x2963 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2728).name("x2963").ctrl(x2967).srcCtx("TPCHQ6.scala:63:8") // UnrolledReduce(List(Const(true)),x2728,x2725,Block((x2725) => Const(())),List(List(b1269, b1270)),List(List(b1271, b1272)))
    val b1269 = CounterIter(x2727, Some(0)).name("b1269").ctrl(x2963) // b1269
    val b1271 = Const(true).name("b1271").ctrl(x2963) // b1271
    val b1270 = CounterIter(x2727, Some(1)).name("b1270").ctrl(x2963) // b1270
    val b1272 = Const(true).name("b1272").ctrl(x2963) // b1272
    val x2729_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2729_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:45:35:datesTile") // x2729 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2729_d0_b0) = false
    bufferDepthOf(x2729_d0_b0) = 2
    val x2730_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2730_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:45:35:datesTile") // x2730 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2730_d0_b0) = false
    bufferDepthOf(x2730_d0_b0) = 2
    val x2731_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2731_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:46:35:quantsTile") // x2731 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2731_d0_b0) = false
    bufferDepthOf(x2731_d0_b0) = 2
    val x2732_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2732_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:46:35:quantsTile") // x2732 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2732_d0_b0) = false
    bufferDepthOf(x2732_d0_b0) = 2
    val x2733_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2733_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:47:33:disctsTile") // x2733 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2733_d0_b0) = false
    bufferDepthOf(x2733_d0_b0) = 2
    val x2734_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2734_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:47:33:disctsTile") // x2734 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2734_d0_b0) = false
    bufferDepthOf(x2734_d0_b0) = 2
    val x2735_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2735_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:48:33:pricesTile") // x2735 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2735_d0_b0) = false
    bufferDepthOf(x2735_d0_b0) = 2
    val x2736_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x2736_d0_b0").ctrl(x2963).srcCtx("TPCHQ6.scala:48:33:pricesTile") // x2736 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2736_d0_b0) = false
    bufferDepthOf(x2736_d0_b0) = 2
    val x2895 = UnitController(style=ForkJoin, level=OuterControl).name("x2895").ctrl(x2963).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x2815 = UnitController(style=ForkJoin, level=OuterControl).name("x2815").ctrl(x2895).srcCtx("TPCHQ6.scala:49:18") // ParallelPipe(List(b1271),Block(Const(())))
    val x2738 = UnitController(style=SeqPipe, level=InnerControl).name("x2738").ctrl(x2815).srcCtx("TPCHQ6.scala:49:18") // UnitPipe(List(b1271),Block(Const(())))
    val x2737 = OpDef(op=FixAdd, inputs=List(b1269, Const(32))).name("x2737").ctrl(x2738).srcCtx("TPCHQ6.scala:50:37") // FixAdd(b1269,Const(32))
    val x2757 = UnitController(style=StreamPipe, level=OuterControl).name("x2757").ctrl(x2815).srcCtx("TPCHQ6.scala:50:22") // UnitPipe(List(b1271),Block(Const(())))
    val b2991 = StreamOut(field="offset").name("b2991").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // x2739 = StreamOutNew(BurstCmdBus)
    isAccum(b2991) = false
    bufferDepthOf(b2991) = 1
    val b2992 = StreamOut(field="size").name("b2992").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // x2739 = StreamOutNew(BurstCmdBus)
    isAccum(b2992) = false
    bufferDepthOf(b2992) = 1
    val x2740 = StreamIn(field="data").name("x2740").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // x2740 = StreamInNew(BurstDataBus())
    isAccum(x2740) = false
    bufferDepthOf(x2740) = 1
    val x2748 = UnitController(style=SeqPipe, level=InnerControl).name("x2748").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // UnitPipe(List(b1271),Block(x2747))
    val x2741 = b1269 // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    val x2742 = OpDef(op=FixSla, inputs=List(x2741, Const(2))).name("x2742").ctrl(x2748).srcCtx("TPCHQ6.scala:50:22") // FixLsh(x2741,Const(2))
    val x2743 = x2742 // FixConvert(x2742,TRUE,_64,_0)
    val x2744 = DramAddress(x2713).name("x2744").ctrl(x2748).srcCtx("TPCHQ6.scala:50:22") // GetDRAMAddress(x2713)
    val x2746_x2745 = OpDef(op=FixAdd, inputs=List(x2743, x2744)).name("x2746_x2745").ctrl(x2748).srcCtx("TPCHQ6.scala:50:22") // FixAdd(x2743,x2744)
    // x2746 = SimpleStruct(ArrayBuffer((offset,x2745), (size,Const(128)), (isLoad,Const(true))))
    val x2747_b2993_b2991 = WriteMem(b2991, x2746_x2745).name("x2747_b2993_b2991").ctrl(x2748).srcCtx("TPCHQ6.scala:50:22") // StreamWrite(x2739,x2746,b1271)
    val x2747_b2994_b2992 = WriteMem(b2992, Const(128)).name("x2747_b2994_b2992").ctrl(x2748).srcCtx("TPCHQ6.scala:50:22") // StreamWrite(x2739,x2746,b1271)
    val x2749 = FringeDenseLoad(dram=List(x2713), cmdStream=List(b2991, b2992), dataStream=List(x2740)).name("x2749").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // FringeDenseLoad(x2713,x2739,x2740)
    val x2750 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2750").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2751 = CounterChain(List(x2750)).name("x2751").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // CounterChainNew(List(x2750))
    val x2756 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2751).name("x2756").ctrl(x2757).srcCtx("TPCHQ6.scala:50:22") // UnrolledForeach(List(b1271),x2751,Block(Const(())),List(List(b1296)),List(List(b1297)))
    val b1296 = CounterIter(x2750, None).name("b1296").ctrl(x2756) // b1296
    val b1297 = Const(true).name("b1297").ctrl(x2756) // b1297
    val x2752 = OpDef(op=BitAnd, inputs=List(b1297, b1271)).name("x2752").ctrl(x2756).srcCtx("UnrollingBase.scala:28:66") // And(b1297,b1271)
    val x2753_x2753 = ReadMem(x2740).name("x2753_x2753").ctrl(x2756).srcCtx("TPCHQ6.scala:50:22") // ParStreamRead(x2740,List(x2752))
    val x2754_x2754 = x2753_x2753 // x2754 = VectorApply(x2753,0)
    val x2755 = StoreBanks(List(x2729_d0_b0), List(b1296), x2754_x2754).name("x2755").ctrl(x2756).srcCtx("TPCHQ6.scala:50:22") // ParSRAMStore(x2729,List(List(b1296)),List(x2754),List(x2752))
    val x2776 = UnitController(style=StreamPipe, level=OuterControl).name("x2776").ctrl(x2815).srcCtx("TPCHQ6.scala:51:22") // UnitPipe(List(b1271),Block(Const(())))
    val b2995 = StreamOut(field="offset").name("b2995").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // x2758 = StreamOutNew(BurstCmdBus)
    isAccum(b2995) = false
    bufferDepthOf(b2995) = 1
    val b2996 = StreamOut(field="size").name("b2996").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // x2758 = StreamOutNew(BurstCmdBus)
    isAccum(b2996) = false
    bufferDepthOf(b2996) = 1
    val x2759 = StreamIn(field="data").name("x2759").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // x2759 = StreamInNew(BurstDataBus())
    isAccum(x2759) = false
    bufferDepthOf(x2759) = 1
    val x2767 = UnitController(style=SeqPipe, level=InnerControl).name("x2767").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // UnitPipe(List(b1271),Block(x2766))
    val x2760 = b1269 // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    val x2761 = OpDef(op=FixSla, inputs=List(x2760, Const(2))).name("x2761").ctrl(x2767).srcCtx("TPCHQ6.scala:51:22") // FixLsh(x2760,Const(2))
    val x2762 = x2761 // FixConvert(x2761,TRUE,_64,_0)
    val x2763 = DramAddress(x2715).name("x2763").ctrl(x2767).srcCtx("TPCHQ6.scala:51:22") // GetDRAMAddress(x2715)
    val x2765_x2764 = OpDef(op=FixAdd, inputs=List(x2762, x2763)).name("x2765_x2764").ctrl(x2767).srcCtx("TPCHQ6.scala:51:22") // FixAdd(x2762,x2763)
    // x2765 = SimpleStruct(ArrayBuffer((offset,x2764), (size,Const(128)), (isLoad,Const(true))))
    val x2766_b2997_b2995 = WriteMem(b2995, x2765_x2764).name("x2766_b2997_b2995").ctrl(x2767).srcCtx("TPCHQ6.scala:51:22") // StreamWrite(x2758,x2765,b1271)
    val x2766_b2998_b2996 = WriteMem(b2996, Const(128)).name("x2766_b2998_b2996").ctrl(x2767).srcCtx("TPCHQ6.scala:51:22") // StreamWrite(x2758,x2765,b1271)
    val x2768 = FringeDenseLoad(dram=List(x2715), cmdStream=List(b2995, b2996), dataStream=List(x2759)).name("x2768").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // FringeDenseLoad(x2715,x2758,x2759)
    val x2769 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2769").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2770 = CounterChain(List(x2769)).name("x2770").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // CounterChainNew(List(x2769))
    val x2775 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2770).name("x2775").ctrl(x2776).srcCtx("TPCHQ6.scala:51:22") // UnrolledForeach(List(b1271),x2770,Block(Const(())),List(List(b1317)),List(List(b1318)))
    val b1317 = CounterIter(x2769, None).name("b1317").ctrl(x2775) // b1317
    val b1318 = Const(true).name("b1318").ctrl(x2775) // b1318
    val x2771 = OpDef(op=BitAnd, inputs=List(b1318, b1271)).name("x2771").ctrl(x2775).srcCtx("UnrollingBase.scala:28:66") // And(b1318,b1271)
    val x2772_x2772 = ReadMem(x2759).name("x2772_x2772").ctrl(x2775).srcCtx("TPCHQ6.scala:51:22") // ParStreamRead(x2759,List(x2771))
    val x2773_x2773 = x2772_x2772 // x2773 = VectorApply(x2772,0)
    val x2774 = StoreBanks(List(x2731_d0_b0), List(b1317), x2773_x2773).name("x2774").ctrl(x2775).srcCtx("TPCHQ6.scala:51:22") // ParSRAMStore(x2731,List(List(b1317)),List(x2773),List(x2771))
    val x2795 = UnitController(style=StreamPipe, level=OuterControl).name("x2795").ctrl(x2815).srcCtx("TPCHQ6.scala:52:22") // UnitPipe(List(b1271),Block(Const(())))
    val b2999 = StreamOut(field="offset").name("b2999").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // x2777 = StreamOutNew(BurstCmdBus)
    isAccum(b2999) = false
    bufferDepthOf(b2999) = 1
    val b3000 = StreamOut(field="size").name("b3000").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // x2777 = StreamOutNew(BurstCmdBus)
    isAccum(b3000) = false
    bufferDepthOf(b3000) = 1
    val x2778 = StreamIn(field="data").name("x2778").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // x2778 = StreamInNew(BurstDataBus())
    isAccum(x2778) = false
    bufferDepthOf(x2778) = 1
    val x2786 = UnitController(style=SeqPipe, level=InnerControl).name("x2786").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // UnitPipe(List(b1271),Block(x2785))
    val x2779 = b1269 // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    val x2780 = OpDef(op=FixSla, inputs=List(x2779, Const(2))).name("x2780").ctrl(x2786).srcCtx("TPCHQ6.scala:52:22") // FixLsh(x2779,Const(2))
    val x2781 = x2780 // FixConvert(x2780,TRUE,_64,_0)
    val x2782 = DramAddress(x2717).name("x2782").ctrl(x2786).srcCtx("TPCHQ6.scala:52:22") // GetDRAMAddress(x2717)
    val x2784_x2783 = OpDef(op=FixAdd, inputs=List(x2781, x2782)).name("x2784_x2783").ctrl(x2786).srcCtx("TPCHQ6.scala:52:22") // FixAdd(x2781,x2782)
    // x2784 = SimpleStruct(ArrayBuffer((offset,x2783), (size,Const(128)), (isLoad,Const(true))))
    val x2785_b3001_b2999 = WriteMem(b2999, x2784_x2783).name("x2785_b3001_b2999").ctrl(x2786).srcCtx("TPCHQ6.scala:52:22") // StreamWrite(x2777,x2784,b1271)
    val x2785_b3002_b3000 = WriteMem(b3000, Const(128)).name("x2785_b3002_b3000").ctrl(x2786).srcCtx("TPCHQ6.scala:52:22") // StreamWrite(x2777,x2784,b1271)
    val x2787 = FringeDenseLoad(dram=List(x2717), cmdStream=List(b2999, b3000), dataStream=List(x2778)).name("x2787").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // FringeDenseLoad(x2717,x2777,x2778)
    val x2788 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2788").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2789 = CounterChain(List(x2788)).name("x2789").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // CounterChainNew(List(x2788))
    val x2794 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2789).name("x2794").ctrl(x2795).srcCtx("TPCHQ6.scala:52:22") // UnrolledForeach(List(b1271),x2789,Block(Const(())),List(List(b1338)),List(List(b1339)))
    val b1338 = CounterIter(x2788, None).name("b1338").ctrl(x2794) // b1338
    val b1339 = Const(true).name("b1339").ctrl(x2794) // b1339
    val x2790 = OpDef(op=BitAnd, inputs=List(b1339, b1271)).name("x2790").ctrl(x2794).srcCtx("UnrollingBase.scala:28:66") // And(b1339,b1271)
    val x2791_x2791 = ReadMem(x2778).name("x2791_x2791").ctrl(x2794).srcCtx("TPCHQ6.scala:52:22") // ParStreamRead(x2778,List(x2790))
    val x2792_x2792 = x2791_x2791 // x2792 = VectorApply(x2791,0)
    val x2793 = StoreBanks(List(x2733_d0_b0), List(b1338), x2792_x2792).name("x2793").ctrl(x2794).srcCtx("TPCHQ6.scala:52:22") // ParSRAMStore(x2733,List(List(b1338)),List(x2792),List(x2790))
    val x2814 = UnitController(style=StreamPipe, level=OuterControl).name("x2814").ctrl(x2815).srcCtx("TPCHQ6.scala:53:22") // UnitPipe(List(b1271),Block(Const(())))
    val b3003 = StreamOut(field="offset").name("b3003").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // x2796 = StreamOutNew(BurstCmdBus)
    isAccum(b3003) = false
    bufferDepthOf(b3003) = 1
    val b3004 = StreamOut(field="size").name("b3004").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // x2796 = StreamOutNew(BurstCmdBus)
    isAccum(b3004) = false
    bufferDepthOf(b3004) = 1
    val x2797 = StreamIn(field="data").name("x2797").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // x2797 = StreamInNew(BurstDataBus())
    isAccum(x2797) = false
    bufferDepthOf(x2797) = 1
    val x2805 = UnitController(style=SeqPipe, level=InnerControl).name("x2805").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // UnitPipe(List(b1271),Block(x2804))
    val x2798 = b1269 // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    val x2799 = OpDef(op=FixSla, inputs=List(x2798, Const(2))).name("x2799").ctrl(x2805).srcCtx("TPCHQ6.scala:53:22") // FixLsh(x2798,Const(2))
    val x2800 = x2799 // FixConvert(x2799,TRUE,_64,_0)
    val x2801 = DramAddress(x2719).name("x2801").ctrl(x2805).srcCtx("TPCHQ6.scala:53:22") // GetDRAMAddress(x2719)
    val x2803_x2802 = OpDef(op=FixAdd, inputs=List(x2800, x2801)).name("x2803_x2802").ctrl(x2805).srcCtx("TPCHQ6.scala:53:22") // FixAdd(x2800,x2801)
    // x2803 = SimpleStruct(ArrayBuffer((offset,x2802), (size,Const(128)), (isLoad,Const(true))))
    val x2804_b3005_b3003 = WriteMem(b3003, x2803_x2802).name("x2804_b3005_b3003").ctrl(x2805).srcCtx("TPCHQ6.scala:53:22") // StreamWrite(x2796,x2803,b1271)
    val x2804_b3006_b3004 = WriteMem(b3004, Const(128)).name("x2804_b3006_b3004").ctrl(x2805).srcCtx("TPCHQ6.scala:53:22") // StreamWrite(x2796,x2803,b1271)
    val x2806 = FringeDenseLoad(dram=List(x2719), cmdStream=List(b3003, b3004), dataStream=List(x2797)).name("x2806").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // FringeDenseLoad(x2719,x2796,x2797)
    val x2807 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2807").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2808 = CounterChain(List(x2807)).name("x2808").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // CounterChainNew(List(x2807))
    val x2813 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2808).name("x2813").ctrl(x2814).srcCtx("TPCHQ6.scala:53:22") // UnrolledForeach(List(b1271),x2808,Block(Const(())),List(List(b1359)),List(List(b1360)))
    val b1359 = CounterIter(x2807, None).name("b1359").ctrl(x2813) // b1359
    val b1360 = Const(true).name("b1360").ctrl(x2813) // b1360
    val x2809 = OpDef(op=BitAnd, inputs=List(b1360, b1271)).name("x2809").ctrl(x2813).srcCtx("UnrollingBase.scala:28:66") // And(b1360,b1271)
    val x2810_x2810 = ReadMem(x2797).name("x2810_x2810").ctrl(x2813).srcCtx("TPCHQ6.scala:53:22") // ParStreamRead(x2797,List(x2809))
    val x2811_x2811 = x2810_x2810 // x2811 = VectorApply(x2810,0)
    val x2812 = StoreBanks(List(x2735_d0_b0), List(b1359), x2811_x2811).name("x2812").ctrl(x2813).srcCtx("TPCHQ6.scala:53:22") // ParSRAMStore(x2735,List(List(b1359)),List(x2811),List(x2809))
    val x2894 = UnitController(style=ForkJoin, level=OuterControl).name("x2894").ctrl(x2895).srcCtx("TPCHQ6.scala:49:18") // ParallelPipe(List(b1272),Block(Const(())))
    val x2817 = UnitController(style=SeqPipe, level=InnerControl).name("x2817").ctrl(x2894).srcCtx("TPCHQ6.scala:49:18") // UnitPipe(List(b1272),Block(Const(())))
    val x2816 = OpDef(op=FixAdd, inputs=List(b1270, Const(32))).name("x2816").ctrl(x2817).srcCtx("TPCHQ6.scala:50:37") // FixAdd(b1270,Const(32))
    val x2836 = UnitController(style=StreamPipe, level=OuterControl).name("x2836").ctrl(x2894).srcCtx("TPCHQ6.scala:50:22") // UnitPipe(List(b1272),Block(Const(())))
    val b3007 = StreamOut(field="offset").name("b3007").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // x2818 = StreamOutNew(BurstCmdBus)
    isAccum(b3007) = false
    bufferDepthOf(b3007) = 1
    val b3008 = StreamOut(field="size").name("b3008").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // x2818 = StreamOutNew(BurstCmdBus)
    isAccum(b3008) = false
    bufferDepthOf(b3008) = 1
    val x2819 = StreamIn(field="data").name("x2819").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // x2819 = StreamInNew(BurstDataBus())
    isAccum(x2819) = false
    bufferDepthOf(x2819) = 1
    val x2827 = UnitController(style=SeqPipe, level=InnerControl).name("x2827").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // UnitPipe(List(b1272),Block(x2826))
    val x2820 = b1270 // FixConvert(b1270,TRUE,_32,_0) (Same Type. No op)
    val x2821 = OpDef(op=FixSla, inputs=List(x2820, Const(2))).name("x2821").ctrl(x2827).srcCtx("TPCHQ6.scala:50:22") // FixLsh(x2820,Const(2))
    val x2822 = x2821 // FixConvert(x2821,TRUE,_64,_0)
    val x2823 = DramAddress(x2713).name("x2823").ctrl(x2827).srcCtx("TPCHQ6.scala:50:22") // GetDRAMAddress(x2713)
    val x2825_x2824 = OpDef(op=FixAdd, inputs=List(x2822, x2823)).name("x2825_x2824").ctrl(x2827).srcCtx("TPCHQ6.scala:50:22") // FixAdd(x2822,x2823)
    // x2825 = SimpleStruct(ArrayBuffer((offset,x2824), (size,Const(128)), (isLoad,Const(true))))
    val x2826_b3009_b3007 = WriteMem(b3007, x2825_x2824).name("x2826_b3009_b3007").ctrl(x2827).srcCtx("TPCHQ6.scala:50:22") // StreamWrite(x2818,x2825,b1272)
    val x2826_b3010_b3008 = WriteMem(b3008, Const(128)).name("x2826_b3010_b3008").ctrl(x2827).srcCtx("TPCHQ6.scala:50:22") // StreamWrite(x2818,x2825,b1272)
    val x2828 = FringeDenseLoad(dram=List(x2713), cmdStream=List(b3007, b3008), dataStream=List(x2819)).name("x2828").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // FringeDenseLoad(x2713,x2818,x2819)
    val x2829 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2829").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2830 = CounterChain(List(x2829)).name("x2830").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // CounterChainNew(List(x2829))
    val x2835 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2830).name("x2835").ctrl(x2836).srcCtx("TPCHQ6.scala:50:22") // UnrolledForeach(List(b1272),x2830,Block(Const(())),List(List(b1383)),List(List(b1384)))
    val b1383 = CounterIter(x2829, None).name("b1383").ctrl(x2835) // b1383
    val b1384 = Const(true).name("b1384").ctrl(x2835) // b1384
    val x2831 = OpDef(op=BitAnd, inputs=List(b1384, b1272)).name("x2831").ctrl(x2835).srcCtx("UnrollingBase.scala:28:66") // And(b1384,b1272)
    val x2832_x2832 = ReadMem(x2819).name("x2832_x2832").ctrl(x2835).srcCtx("TPCHQ6.scala:50:22") // ParStreamRead(x2819,List(x2831))
    val x2833_x2833 = x2832_x2832 // x2833 = VectorApply(x2832,0)
    val x2834 = StoreBanks(List(x2730_d0_b0), List(b1383), x2833_x2833).name("x2834").ctrl(x2835).srcCtx("TPCHQ6.scala:50:22") // ParSRAMStore(x2730,List(List(b1383)),List(x2833),List(x2831))
    val x2855 = UnitController(style=StreamPipe, level=OuterControl).name("x2855").ctrl(x2894).srcCtx("TPCHQ6.scala:51:22") // UnitPipe(List(b1272),Block(Const(())))
    val b3011 = StreamOut(field="offset").name("b3011").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // x2837 = StreamOutNew(BurstCmdBus)
    isAccum(b3011) = false
    bufferDepthOf(b3011) = 1
    val b3012 = StreamOut(field="size").name("b3012").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // x2837 = StreamOutNew(BurstCmdBus)
    isAccum(b3012) = false
    bufferDepthOf(b3012) = 1
    val x2838 = StreamIn(field="data").name("x2838").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // x2838 = StreamInNew(BurstDataBus())
    isAccum(x2838) = false
    bufferDepthOf(x2838) = 1
    val x2846 = UnitController(style=SeqPipe, level=InnerControl).name("x2846").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // UnitPipe(List(b1272),Block(x2845))
    val x2839 = b1270 // FixConvert(b1270,TRUE,_32,_0) (Same Type. No op)
    val x2840 = OpDef(op=FixSla, inputs=List(x2839, Const(2))).name("x2840").ctrl(x2846).srcCtx("TPCHQ6.scala:51:22") // FixLsh(x2839,Const(2))
    val x2841 = x2840 // FixConvert(x2840,TRUE,_64,_0)
    val x2842 = DramAddress(x2715).name("x2842").ctrl(x2846).srcCtx("TPCHQ6.scala:51:22") // GetDRAMAddress(x2715)
    val x2844_x2843 = OpDef(op=FixAdd, inputs=List(x2841, x2842)).name("x2844_x2843").ctrl(x2846).srcCtx("TPCHQ6.scala:51:22") // FixAdd(x2841,x2842)
    // x2844 = SimpleStruct(ArrayBuffer((offset,x2843), (size,Const(128)), (isLoad,Const(true))))
    val x2845_b3013_b3011 = WriteMem(b3011, x2844_x2843).name("x2845_b3013_b3011").ctrl(x2846).srcCtx("TPCHQ6.scala:51:22") // StreamWrite(x2837,x2844,b1272)
    val x2845_b3014_b3012 = WriteMem(b3012, Const(128)).name("x2845_b3014_b3012").ctrl(x2846).srcCtx("TPCHQ6.scala:51:22") // StreamWrite(x2837,x2844,b1272)
    val x2847 = FringeDenseLoad(dram=List(x2715), cmdStream=List(b3011, b3012), dataStream=List(x2838)).name("x2847").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // FringeDenseLoad(x2715,x2837,x2838)
    val x2848 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2848").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2849 = CounterChain(List(x2848)).name("x2849").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // CounterChainNew(List(x2848))
    val x2854 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2849).name("x2854").ctrl(x2855).srcCtx("TPCHQ6.scala:51:22") // UnrolledForeach(List(b1272),x2849,Block(Const(())),List(List(b1404)),List(List(b1405)))
    val b1404 = CounterIter(x2848, None).name("b1404").ctrl(x2854) // b1404
    val b1405 = Const(true).name("b1405").ctrl(x2854) // b1405
    val x2850 = OpDef(op=BitAnd, inputs=List(b1405, b1272)).name("x2850").ctrl(x2854).srcCtx("UnrollingBase.scala:28:66") // And(b1405,b1272)
    val x2851_x2851 = ReadMem(x2838).name("x2851_x2851").ctrl(x2854).srcCtx("TPCHQ6.scala:51:22") // ParStreamRead(x2838,List(x2850))
    val x2852_x2852 = x2851_x2851 // x2852 = VectorApply(x2851,0)
    val x2853 = StoreBanks(List(x2732_d0_b0), List(b1404), x2852_x2852).name("x2853").ctrl(x2854).srcCtx("TPCHQ6.scala:51:22") // ParSRAMStore(x2732,List(List(b1404)),List(x2852),List(x2850))
    val x2874 = UnitController(style=StreamPipe, level=OuterControl).name("x2874").ctrl(x2894).srcCtx("TPCHQ6.scala:52:22") // UnitPipe(List(b1272),Block(Const(())))
    val b3015 = StreamOut(field="offset").name("b3015").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // x2856 = StreamOutNew(BurstCmdBus)
    isAccum(b3015) = false
    bufferDepthOf(b3015) = 1
    val b3016 = StreamOut(field="size").name("b3016").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // x2856 = StreamOutNew(BurstCmdBus)
    isAccum(b3016) = false
    bufferDepthOf(b3016) = 1
    val x2857 = StreamIn(field="data").name("x2857").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // x2857 = StreamInNew(BurstDataBus())
    isAccum(x2857) = false
    bufferDepthOf(x2857) = 1
    val x2865 = UnitController(style=SeqPipe, level=InnerControl).name("x2865").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // UnitPipe(List(b1272),Block(x2864))
    val x2858 = b1270 // FixConvert(b1270,TRUE,_32,_0) (Same Type. No op)
    val x2859 = OpDef(op=FixSla, inputs=List(x2858, Const(2))).name("x2859").ctrl(x2865).srcCtx("TPCHQ6.scala:52:22") // FixLsh(x2858,Const(2))
    val x2860 = x2859 // FixConvert(x2859,TRUE,_64,_0)
    val x2861 = DramAddress(x2717).name("x2861").ctrl(x2865).srcCtx("TPCHQ6.scala:52:22") // GetDRAMAddress(x2717)
    val x2863_x2862 = OpDef(op=FixAdd, inputs=List(x2860, x2861)).name("x2863_x2862").ctrl(x2865).srcCtx("TPCHQ6.scala:52:22") // FixAdd(x2860,x2861)
    // x2863 = SimpleStruct(ArrayBuffer((offset,x2862), (size,Const(128)), (isLoad,Const(true))))
    val x2864_b3017_b3015 = WriteMem(b3015, x2863_x2862).name("x2864_b3017_b3015").ctrl(x2865).srcCtx("TPCHQ6.scala:52:22") // StreamWrite(x2856,x2863,b1272)
    val x2864_b3018_b3016 = WriteMem(b3016, Const(128)).name("x2864_b3018_b3016").ctrl(x2865).srcCtx("TPCHQ6.scala:52:22") // StreamWrite(x2856,x2863,b1272)
    val x2866 = FringeDenseLoad(dram=List(x2717), cmdStream=List(b3015, b3016), dataStream=List(x2857)).name("x2866").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // FringeDenseLoad(x2717,x2856,x2857)
    val x2867 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2867").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2868 = CounterChain(List(x2867)).name("x2868").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // CounterChainNew(List(x2867))
    val x2873 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2868).name("x2873").ctrl(x2874).srcCtx("TPCHQ6.scala:52:22") // UnrolledForeach(List(b1272),x2868,Block(Const(())),List(List(b1425)),List(List(b1426)))
    val b1425 = CounterIter(x2867, None).name("b1425").ctrl(x2873) // b1425
    val b1426 = Const(true).name("b1426").ctrl(x2873) // b1426
    val x2869 = OpDef(op=BitAnd, inputs=List(b1426, b1272)).name("x2869").ctrl(x2873).srcCtx("UnrollingBase.scala:28:66") // And(b1426,b1272)
    val x2870_x2870 = ReadMem(x2857).name("x2870_x2870").ctrl(x2873).srcCtx("TPCHQ6.scala:52:22") // ParStreamRead(x2857,List(x2869))
    val x2871_x2871 = x2870_x2870 // x2871 = VectorApply(x2870,0)
    val x2872 = StoreBanks(List(x2734_d0_b0), List(b1425), x2871_x2871).name("x2872").ctrl(x2873).srcCtx("TPCHQ6.scala:52:22") // ParSRAMStore(x2734,List(List(b1425)),List(x2871),List(x2869))
    val x2893 = UnitController(style=StreamPipe, level=OuterControl).name("x2893").ctrl(x2894).srcCtx("TPCHQ6.scala:53:22") // UnitPipe(List(b1272),Block(Const(())))
    val b3019 = StreamOut(field="offset").name("b3019").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // x2875 = StreamOutNew(BurstCmdBus)
    isAccum(b3019) = false
    bufferDepthOf(b3019) = 1
    val b3020 = StreamOut(field="size").name("b3020").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // x2875 = StreamOutNew(BurstCmdBus)
    isAccum(b3020) = false
    bufferDepthOf(b3020) = 1
    val x2876 = StreamIn(field="data").name("x2876").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // x2876 = StreamInNew(BurstDataBus())
    isAccum(x2876) = false
    bufferDepthOf(x2876) = 1
    val x2884 = UnitController(style=SeqPipe, level=InnerControl).name("x2884").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // UnitPipe(List(b1272),Block(x2883))
    val x2877 = b1270 // FixConvert(b1270,TRUE,_32,_0) (Same Type. No op)
    val x2878 = OpDef(op=FixSla, inputs=List(x2877, Const(2))).name("x2878").ctrl(x2884).srcCtx("TPCHQ6.scala:53:22") // FixLsh(x2877,Const(2))
    val x2879 = x2878 // FixConvert(x2878,TRUE,_64,_0)
    val x2880 = DramAddress(x2719).name("x2880").ctrl(x2884).srcCtx("TPCHQ6.scala:53:22") // GetDRAMAddress(x2719)
    val x2882_x2881 = OpDef(op=FixAdd, inputs=List(x2879, x2880)).name("x2882_x2881").ctrl(x2884).srcCtx("TPCHQ6.scala:53:22") // FixAdd(x2879,x2880)
    // x2882 = SimpleStruct(ArrayBuffer((offset,x2881), (size,Const(128)), (isLoad,Const(true))))
    val x2883_b3021_b3019 = WriteMem(b3019, x2882_x2881).name("x2883_b3021_b3019").ctrl(x2884).srcCtx("TPCHQ6.scala:53:22") // StreamWrite(x2875,x2882,b1272)
    val x2883_b3022_b3020 = WriteMem(b3020, Const(128)).name("x2883_b3022_b3020").ctrl(x2884).srcCtx("TPCHQ6.scala:53:22") // StreamWrite(x2875,x2882,b1272)
    val x2885 = FringeDenseLoad(dram=List(x2719), cmdStream=List(b3019, b3020), dataStream=List(x2876)).name("x2885").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // FringeDenseLoad(x2719,x2875,x2876)
    val x2886 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2886").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2887 = CounterChain(List(x2886)).name("x2887").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // CounterChainNew(List(x2886))
    val x2892 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2887).name("x2892").ctrl(x2893).srcCtx("TPCHQ6.scala:53:22") // UnrolledForeach(List(b1272),x2887,Block(Const(())),List(List(b1446)),List(List(b1447)))
    val b1446 = CounterIter(x2886, None).name("b1446").ctrl(x2892) // b1446
    val b1447 = Const(true).name("b1447").ctrl(x2892) // b1447
    val x2888 = OpDef(op=BitAnd, inputs=List(b1447, b1272)).name("x2888").ctrl(x2892).srcCtx("UnrollingBase.scala:28:66") // And(b1447,b1272)
    val x2889_x2889 = ReadMem(x2876).name("x2889_x2889").ctrl(x2892).srcCtx("TPCHQ6.scala:53:22") // ParStreamRead(x2876,List(x2888))
    val x2890_x2890 = x2889_x2889 // x2890 = VectorApply(x2889,0)
    val x2891 = StoreBanks(List(x2736_d0_b0), List(b1446), x2890_x2890).name("x2891").ctrl(x2892).srcCtx("TPCHQ6.scala:53:22") // ParSRAMStore(x2736,List(List(b1446)),List(x2890),List(x2888))
    val x2896_d0 = Reg(init=Some(0)).name("x2896_d0").ctrl(x2963).srcCtx("TPCHQ6.scala:55:19") // x2896 = RegNew(Const(0))
    isAccum(x2896_d0) = false
    bufferDepthOf(x2896_d0) = 2
    val x2896_d1 = Reg(init=Some(0)).name("x2896_d1").ctrl(x2963).srcCtx("TPCHQ6.scala:55:19") // x2896 = RegNew(Const(0))
    isAccum(x2896_d1) = true
    bufferDepthOf(x2896_d1) = 1
    val x2897_d0 = Reg(init=Some(0)).name("x2897_d0").ctrl(x2963).srcCtx("TPCHQ6.scala:55:19") // x2897 = RegNew(Const(0))
    isAccum(x2897_d0) = false
    bufferDepthOf(x2897_d0) = 2
    val x2897_d1 = Reg(init=Some(0)).name("x2897_d1").ctrl(x2963).srcCtx("TPCHQ6.scala:55:19") // x2897 = RegNew(Const(0))
    isAccum(x2897_d1) = true
    bufferDepthOf(x2897_d1) = 1
    val x2952 = UnitController(style=ForkJoin, level=OuterControl).name("x2952").ctrl(x2963).srcCtx("UnrollingTransformer.scala:431:43") // ParallelPipe(List(Const(true)),Block(Const(())))
    val x2898 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2898").ctrl(x2952).srcCtx("TPCHQ6.scala:55:27") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2899 = CounterChain(List(x2898)).name("x2899").ctrl(x2952).srcCtx("TPCHQ6.scala:62:10") // CounterChainNew(List(x2898))
    val x2924 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2899).name("x2924").ctrl(x2952).srcCtx("TPCHQ6.scala:62:10") // UnrolledReduce(List(b1271),x2899,x2896,Block((x2896) => Const(())),List(List(b1462)),List(List(b1463)))
    val b1462 = CounterIter(x2898, None).name("b1462").ctrl(x2924) // b1462
    val b1463 = Const(true).name("b1463").ctrl(x2924) // b1463
    val x2900 = OpDef(op=BitAnd, inputs=List(b1463, b1271)).name("x2900").ctrl(x2924).srcCtx("UnrollingBase.scala:28:66") // And(b1463,b1271)
    val x2901 = LoadBanks(List(x2729_d0_b0), List(b1462)).name("x2901").ctrl(x2924).srcCtx("TPCHQ6.scala:56:32:date") // ParSRAMLoad(x2729,List(List(b1462)),List(x2900))
    val x2902 = x2901 // x2902 = VectorApply(x2901,0)
    val x2903 = LoadBanks(List(x2733_d0_b0), List(b1462)).name("x2903").ctrl(x2924).srcCtx("TPCHQ6.scala:57:33:disct") // ParSRAMLoad(x2733,List(List(b1462)),List(x2900))
    val x2904 = x2903 // x2904 = VectorApply(x2903,0)
    val x2905 = LoadBanks(List(x2731_d0_b0), List(b1462)).name("x2905").ctrl(x2924).srcCtx("TPCHQ6.scala:58:33:quant") // ParSRAMLoad(x2731,List(List(b1462)),List(x2900))
    val x2906 = x2905 // x2906 = VectorApply(x2905,0)
    val x2907 = LoadBanks(List(x2735_d0_b0), List(b1462)).name("x2907").ctrl(x2924).srcCtx("TPCHQ6.scala:59:33:price") // ParSRAMLoad(x2735,List(List(b1462)),List(x2900))
    val x2908 = x2907 // x2908 = VectorApply(x2907,0)
    val x2909 = OpDef(op=FixLt, inputs=List(Const(0), x2902)).name("x2909").ctrl(x2924).srcCtx("TPCHQ6.scala:60:28") // FixLt(Const(0),x2902)
    val x2910 = OpDef(op=FixLt, inputs=List(x2902, Const(9999))).name("x2910").ctrl(x2924).srcCtx("TPCHQ6.scala:60:46") // FixLt(x2902,Const(9999))
    val x2911 = OpDef(op=BitAnd, inputs=List(x2909, x2910)).name("x2911").ctrl(x2924).srcCtx("TPCHQ6.scala:60:38") // And(x2909,x2910)
    val x2912 = OpDef(op=FixLeq, inputs=List(Const(0), x2904)).name("x2912").ctrl(x2924).srcCtx("TPCHQ6.scala:60:65") // FixLeq(Const(0),x2904)
    val x2913 = OpDef(op=BitAnd, inputs=List(x2911, x2912)).name("x2913").ctrl(x2924).srcCtx("TPCHQ6.scala:60:56") // And(x2911,x2912)
    val x2914 = OpDef(op=FixLeq, inputs=List(x2904, Const(9999))).name("x2914").ctrl(x2924).srcCtx("TPCHQ6.scala:60:92") // FixLeq(x2904,Const(9999))
    val x2915 = OpDef(op=BitAnd, inputs=List(x2913, x2914)).name("x2915").ctrl(x2924).srcCtx("TPCHQ6.scala:60:83") // And(x2913,x2914)
    val x2916 = OpDef(op=FixLt, inputs=List(x2906, Const(24))).name("x2916").ctrl(x2924).srcCtx("TPCHQ6.scala:60:119") // FixLt(x2906,Const(24))
    val x2917 = OpDef(op=BitAnd, inputs=List(x2915, x2916)).name("x2917").ctrl(x2924).srcCtx("TPCHQ6.scala:60:110:valid") // And(x2915,x2916)
    val x2918 = OpDef(op=FixMul, inputs=List(x2908, x2904)).name("x2918").ctrl(x2924).srcCtx("TPCHQ6.scala:61:28") // FixMul(x2908,x2904)
    val x2919 = OpDef(op=MuxOp, inputs=List(x2917, x2918, Const(0))).name("x2919").ctrl(x2924).srcCtx("TPCHQ6.scala:61:14") // Mux(x2917,x2918,Const(0))
    val x2920 = ReadMem(x2896_d1).name("x2920").ctrl(x2924).srcCtx("TPCHQ6.scala:62:10") // RegRead(x2896)
    val x2921 = OpDef(op=FixEql, inputs=List(b1462, Const(0))).name("x2921").ctrl(x2924).srcCtx("TPCHQ6.scala:62:10") // FixEql(b1462,Const(0))
    val x2922 = ReduceAccumOp(op=FixAdd, input=x2919, accum=x2920).name("x2922").ctrl(x2924).srcCtx("TPCHQ6.scala:62:12") // FixAdd(x2919,x2920)
    val x2923_x2896_d0 = WriteMem(x2896_d0, x2922).name("x2923_x2896_d0").ctrl(x2924).srcCtx("TPCHQ6.scala:62:10") // RegWrite(x2896,x2922,b1271)
    val x2923_x2896_d1 = WriteMem(x2896_d1, x2922).name("x2923_x2896_d1").ctrl(x2924).srcCtx("TPCHQ6.scala:62:10") // RegWrite(x2896,x2922,b1271)
    val x2925 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x2925").ctrl(x2952).srcCtx("TPCHQ6.scala:55:27") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x2926 = CounterChain(List(x2925)).name("x2926").ctrl(x2952).srcCtx("TPCHQ6.scala:62:10") // CounterChainNew(List(x2925))
    val x2951 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2926).name("x2951").ctrl(x2952).srcCtx("TPCHQ6.scala:62:10") // UnrolledReduce(List(b1272),x2926,x2897,Block((x2897) => Const(())),List(List(b1489)),List(List(b1490)))
    val b1489 = CounterIter(x2925, None).name("b1489").ctrl(x2951) // b1489
    val b1490 = Const(true).name("b1490").ctrl(x2951) // b1490
    val x2927 = OpDef(op=BitAnd, inputs=List(b1490, b1272)).name("x2927").ctrl(x2951).srcCtx("UnrollingBase.scala:28:66") // And(b1490,b1272)
    val x2928 = LoadBanks(List(x2730_d0_b0), List(b1489)).name("x2928").ctrl(x2951).srcCtx("TPCHQ6.scala:56:32:date") // ParSRAMLoad(x2730,List(List(b1489)),List(x2927))
    val x2929 = x2928 // x2929 = VectorApply(x2928,0)
    val x2930 = LoadBanks(List(x2734_d0_b0), List(b1489)).name("x2930").ctrl(x2951).srcCtx("TPCHQ6.scala:57:33:disct") // ParSRAMLoad(x2734,List(List(b1489)),List(x2927))
    val x2931 = x2930 // x2931 = VectorApply(x2930,0)
    val x2932 = LoadBanks(List(x2732_d0_b0), List(b1489)).name("x2932").ctrl(x2951).srcCtx("TPCHQ6.scala:58:33:quant") // ParSRAMLoad(x2732,List(List(b1489)),List(x2927))
    val x2933 = x2932 // x2933 = VectorApply(x2932,0)
    val x2934 = LoadBanks(List(x2736_d0_b0), List(b1489)).name("x2934").ctrl(x2951).srcCtx("TPCHQ6.scala:59:33:price") // ParSRAMLoad(x2736,List(List(b1489)),List(x2927))
    val x2935 = x2934 // x2935 = VectorApply(x2934,0)
    val x2936 = OpDef(op=FixLt, inputs=List(Const(0), x2929)).name("x2936").ctrl(x2951).srcCtx("TPCHQ6.scala:60:28") // FixLt(Const(0),x2929)
    val x2937 = OpDef(op=FixLt, inputs=List(x2929, Const(9999))).name("x2937").ctrl(x2951).srcCtx("TPCHQ6.scala:60:46") // FixLt(x2929,Const(9999))
    val x2938 = OpDef(op=BitAnd, inputs=List(x2936, x2937)).name("x2938").ctrl(x2951).srcCtx("TPCHQ6.scala:60:38") // And(x2936,x2937)
    val x2939 = OpDef(op=FixLeq, inputs=List(Const(0), x2931)).name("x2939").ctrl(x2951).srcCtx("TPCHQ6.scala:60:65") // FixLeq(Const(0),x2931)
    val x2940 = OpDef(op=BitAnd, inputs=List(x2938, x2939)).name("x2940").ctrl(x2951).srcCtx("TPCHQ6.scala:60:56") // And(x2938,x2939)
    val x2941 = OpDef(op=FixLeq, inputs=List(x2931, Const(9999))).name("x2941").ctrl(x2951).srcCtx("TPCHQ6.scala:60:92") // FixLeq(x2931,Const(9999))
    val x2942 = OpDef(op=BitAnd, inputs=List(x2940, x2941)).name("x2942").ctrl(x2951).srcCtx("TPCHQ6.scala:60:83") // And(x2940,x2941)
    val x2943 = OpDef(op=FixLt, inputs=List(x2933, Const(24))).name("x2943").ctrl(x2951).srcCtx("TPCHQ6.scala:60:119") // FixLt(x2933,Const(24))
    val x2944 = OpDef(op=BitAnd, inputs=List(x2942, x2943)).name("x2944").ctrl(x2951).srcCtx("TPCHQ6.scala:60:110:valid") // And(x2942,x2943)
    val x2945 = OpDef(op=FixMul, inputs=List(x2935, x2931)).name("x2945").ctrl(x2951).srcCtx("TPCHQ6.scala:61:28") // FixMul(x2935,x2931)
    val x2946 = OpDef(op=MuxOp, inputs=List(x2944, x2945, Const(0))).name("x2946").ctrl(x2951).srcCtx("TPCHQ6.scala:61:14") // Mux(x2944,x2945,Const(0))
    val x2947 = ReadMem(x2897_d1).name("x2947").ctrl(x2951).srcCtx("TPCHQ6.scala:62:10") // RegRead(x2897)
    val x2948 = OpDef(op=FixEql, inputs=List(b1489, Const(0))).name("x2948").ctrl(x2951).srcCtx("TPCHQ6.scala:62:10") // FixEql(b1489,Const(0))
    val x2949 = ReduceAccumOp(op=FixAdd, input=x2946, accum=x2947).name("x2949").ctrl(x2951).srcCtx("TPCHQ6.scala:62:12") // FixAdd(x2946,x2947)
    val x2950_x2897_d0 = WriteMem(x2897_d0, x2949).name("x2950_x2897_d0").ctrl(x2951).srcCtx("TPCHQ6.scala:62:10") // RegWrite(x2897,x2949,b1272)
    val x2950_x2897_d1 = WriteMem(x2897_d1, x2949).name("x2950_x2897_d1").ctrl(x2951).srcCtx("TPCHQ6.scala:62:10") // RegWrite(x2897,x2949,b1272)
    val x2962 = UnitController(style=SeqPipe, level=InnerControl).name("x2962").ctrl(x2963).srcCtx("TPCHQ6.scala:63:8") // UnitPipe(List(Const(true)),Block(x2961))
    val x2953 = ReadMem(x2897_d0).name("x2953").ctrl(x2962).srcCtx("TPCHQ6.scala:62:10") // RegRead(x2897)
    val x2954 = ReadMem(x2896_d0).name("x2954").ctrl(x2962).srcCtx("TPCHQ6.scala:62:10") // RegRead(x2896)
    val x2955 = OpDef(op=FixAdd, inputs=List(x2954, x2953)).name("x2955").ctrl(x2962).srcCtx("TPCHQ6.scala:63:10") // FixAdd(x2954,x2953)
    val x2956 = OpDef(op=MuxOp, inputs=List(b1272, x2955, x2954)).name("x2956").ctrl(x2962).srcCtx("TPCHQ6.scala:63:8") // Mux(b1272,x2955,x2954)
    val x2957 = OpDef(op=BitOr, inputs=List(b1271, b1272)).name("x2957").ctrl(x2962).srcCtx("TPCHQ6.scala:63:8") // Or(b1271,b1272)
    val x2958 = ReadMem(x2725_d1).name("x2958").ctrl(x2962).srcCtx("TPCHQ6.scala:63:8") // RegRead(x2725)
    val x2959 = OpDef(op=FixEql, inputs=List(b1269, Const(0))).name("x2959").ctrl(x2962).srcCtx("TPCHQ6.scala:63:8") // FixEql(b1269,Const(0))
    val x2960 = ReduceAccumOp(op=FixAdd, input=x2956, accum=x2958).name("x2960").ctrl(x2962).srcCtx("TPCHQ6.scala:63:10") // FixAdd(x2956,x2958)
    val x2961_x2725_d0 = WriteMem(x2725_d0, x2960).name("x2961_x2725_d0").ctrl(x2962).srcCtx("TPCHQ6.scala:63:8") // RegWrite(x2725,x2960,Const(true))
    val x2961_x2725_d1 = WriteMem(x2725_d1, x2960).name("x2961_x2725_d1").ctrl(x2962).srcCtx("TPCHQ6.scala:63:8") // RegWrite(x2725,x2960,Const(true))
    val x2966 = UnitController(style=SeqPipe, level=InnerControl).name("x2966").ctrl(x2967).srcCtx("TPCHQ6.scala:39:11") // UnitPipe(List(Const(true)),Block(Const(())))
    val x2964 = ReadMem(x2725_d0).name("x2964").ctrl(x2966).srcCtx("TPCHQ6.scala:65:14") // RegRead(x2725)
    val x2965_x2720 = WriteMem(x2720, x2964).name("x2965_x2720").ctrl(x2966).srcCtx("TPCHQ6.scala:65:11") // RegWrite(x2720,x2964,Const(true))
    
  }
}
