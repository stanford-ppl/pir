import pir._
import pir.node._
import arch._
import prism.enums._

object SPMV_ELL extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2828 = DRAM(dims=List(Const(64), Const(16))).name("x2828").ctrl(top).srcCtx("SPMV_ELL.scala:70:30:values_dram") // x2828 = DRAMNew(ArrayBuffer(Const(64), Const(16)),Const(0))
    val x2829 = DRAM(dims=List(Const(64), Const(16))).name("x2829").ctrl(top).srcCtx("SPMV_ELL.scala:71:30:cols_dram") // x2829 = DRAMNew(ArrayBuffer(Const(64), Const(16)),Const(0))
    val x2830 = DRAM(dims=List(Const(64))).name("x2830").ctrl(top).srcCtx("SPMV_ELL.scala:72:27:vec_dram") // x2830 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x2831 = DRAM(dims=List(Const(64))).name("x2831").ctrl(top).srcCtx("SPMV_ELL.scala:73:30:result_dram") // x2831 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x2997 = UnitController(style=SeqPipe, level=OuterControl).name("x2997").ctrl(top).srcCtx("SPMV_ELL.scala:79:11") // Hwblock(Block(Const(())),false)
    val x2844 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x2844").ctrl(x2997).srcCtx("SPMV_ELL.scala:80:20") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x2845 = CounterChain(List(x2844)).name("x2845").ctrl(x2997).srcCtx("SPMV_ELL.scala:80:25") // CounterChainNew(List(x2844))
    val x2996 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2845).name("x2996").ctrl(x2997).srcCtx("SPMV_ELL.scala:80:25") // UnrolledForeach(List(Const(true)),x2845,Block(Const(())),List(List(b1679)),List(List(b1680)))
    val b1679 = CounterIter(x2844, Some(0)).name("b1679").ctrl(x2996) // b1679
    val b1680 = Const(true).name("b1680").ctrl(x2996) // b1680
    val x2846_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=16)).name("x2846_d0_b0").ctrl(x2996).srcCtx("SPMV_ELL.scala:81:34:cols_sram") // x2846 = SRAMNew(ArrayBuffer(Const(32), Const(16)))
    isAccum(x2846_d0_b0) = false
    bufferDepthOf(x2846_d0_b0) = 3
    staticDimsOf(x2846_d0_b0) = List(32, 16)
    val x2847_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=16)).name("x2847_d0_b0").ctrl(x2996).srcCtx("SPMV_ELL.scala:82:34:values_sram") // x2847 = SRAMNew(ArrayBuffer(Const(32), Const(16)))
    isAccum(x2847_d0_b0) = false
    bufferDepthOf(x2847_d0_b0) = 2
    staticDimsOf(x2847_d0_b0) = List(32, 16)
    val x2848_d0_b0 = SRAM(size=32, banking=Strided(banks=1, stride=1)).name("x2848_d0_b0").ctrl(x2996).srcCtx("SPMV_ELL.scala:83:34:result_sram") // x2848 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x2848_d0_b0) = false
    bufferDepthOf(x2848_d0_b0) = 2
    staticDimsOf(x2848_d0_b0) = List(32)
    val x2850 = UnitController(style=SeqPipe, level=InnerControl).name("x2850").ctrl(x2996).srcCtx("SPMV_ELL.scala:80:25") // UnitPipe(List(b1680),Block(Const(())))
    val x2849 = OpDef(op=FixAdd, inputs=List(b1679, Const(32))).name("x2849").ctrl(x2850).srcCtx("SPMV_ELL.scala:85:44") // FixAdd(b1679,Const(32))
    val x2851 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x2851").ctrl(x2996).srcCtx("SPMV_ELL.scala:85:19") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x2852 = CounterChain(List(x2851)).name("x2852").ctrl(x2996).srcCtx("SPMV_ELL.scala:85:19") // CounterChainNew(List(x2851))
    val x2877 = LoopController(style=StreamPipe, level=OuterControl, cchain=x2852).name("x2877").ctrl(x2996).srcCtx("SPMV_ELL.scala:85:19") // UnrolledForeach(List(b1680),x2852,Block(Const(())),List(List(b1688)),List(List(b1689)))
    val b1688 = CounterIter(x2851, Some(0)).name("b1688").ctrl(x2877) // b1688
    val b1689 = Const(true).name("b1689").ctrl(x2877) // b1689
    val b3046 = StreamOut(field="offset").name("b3046").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // x2853 = StreamOutNew(BurstCmdBus)
    isAccum(b3046) = false
    bufferDepthOf(b3046) = 1
    val b3047 = StreamOut(field="size").name("b3047").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // x2853 = StreamOutNew(BurstCmdBus)
    isAccum(b3047) = false
    bufferDepthOf(b3047) = 1
    val x2854 = StreamIn(field="data").name("x2854").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // x2854 = StreamInNew(BurstDataBus())
    isAccum(x2854) = false
    bufferDepthOf(x2854) = 1
    val x2867 = UnitController(style=SeqPipe, level=InnerControl).name("x2867").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // UnitPipe(List(b1689, b1680),Block(x2866))
    val x2855 = OpDef(op=FixAdd, inputs=List(b1679, b1688)).name("x2855").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // FixAdd(b1679,b1688)
    val x2856 = x2855 // FixConvert(x2855,TRUE,_32,_0) (Same Type. No op)
    val x2857 = OpDef(op=FixSla, inputs=List(x2856, Const(4))).name("x2857").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // FixLsh(x2856,Const(4))
    val x2858 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2859 = OpDef(op=FixAdd, inputs=List(x2857, x2858)).name("x2859").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // FixAdd(x2857,x2858)
    val x2860 = OpDef(op=FixSla, inputs=List(x2859, Const(2))).name("x2860").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // FixLsh(x2859,Const(2))
    val x2861 = x2860 // FixConvert(x2860,TRUE,_64,_0)
    val x2862 = DramAddress(x2829).name("x2862").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // GetDRAMAddress(x2829)
    val x2864_x2863 = OpDef(op=FixAdd, inputs=List(x2861, x2862)).name("x2864_x2863").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // FixAdd(x2861,x2862)
    // x2864 = SimpleStruct(ArrayBuffer((offset,x2863), (size,Const(64)), (isLoad,Const(true))))
    val x2865 = OpDef(op=BitAnd, inputs=List(b1689, b1680)).name("x2865").ctrl(x2867).srcCtx("UnrollingBase.scala:28:66") // And(b1689,b1680)
    val x2866_b3048_b3046 = WriteMem(b3046, x2864_x2863).name("x2866_b3048_b3046").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // StreamWrite(x2853,x2864,x2865)
    val x2866_b3049_b3047 = WriteMem(b3047, Const(64)).name("x2866_b3049_b3047").ctrl(x2867).srcCtx("SPMV_ELL.scala:85:19") // StreamWrite(x2853,x2864,x2865)
    val x2868 = FringeDenseLoad(dram=List(x2829), cmdStream=List(b3046, b3047), dataStream=List(x2854)).name("x2868").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // FringeDenseLoad(x2829,x2853,x2854)
    val x2869 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2869").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2870 = CounterChain(List(x2869)).name("x2870").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // CounterChainNew(List(x2869))
    val x2876 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2870).name("x2876").ctrl(x2877).srcCtx("SPMV_ELL.scala:85:19") // UnrolledForeach(List(b1689, b1680),x2870,Block(Const(())),List(List(b1708)),List(List(b1709)))
    val b1708 = CounterIter(x2869, None).name("b1708").ctrl(x2876) // b1708
    val b1709 = Const(true).name("b1709").ctrl(x2876) // b1709
    val x2871 = OpDef(op=BitAnd, inputs=List(b1709, b1689)).name("x2871").ctrl(x2876).srcCtx("UnrollingBase.scala:28:66") // And(b1709,b1689)
    val x2872 = OpDef(op=BitAnd, inputs=List(x2871, b1680)).name("x2872").ctrl(x2876).srcCtx("UnrollingBase.scala:28:66") // And(x2871,b1680)
    val x2873_x2873 = ReadMem(x2854).name("x2873_x2873").ctrl(x2876).srcCtx("SPMV_ELL.scala:85:19") // ParStreamRead(x2854,List(x2872))
    val x2874_x2874 = x2873_x2873 // x2874 = VectorApply(x2873,0)
    val x2875 = StoreBanks(List(List(x2846_d0_b0)), List(b1688, b1708), x2874_x2874).name("x2875").ctrl(x2876).srcCtx("SPMV_ELL.scala:85:19") // ParSRAMStore(x2846,List(List(b1688, b1708)),List(x2874),List(x2872))
    val x2878 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x2878").ctrl(x2996).srcCtx("SPMV_ELL.scala:86:21") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x2879 = CounterChain(List(x2878)).name("x2879").ctrl(x2996).srcCtx("SPMV_ELL.scala:86:21") // CounterChainNew(List(x2878))
    val x2904 = LoopController(style=StreamPipe, level=OuterControl, cchain=x2879).name("x2904").ctrl(x2996).srcCtx("SPMV_ELL.scala:86:21") // UnrolledForeach(List(b1680),x2879,Block(Const(())),List(List(b1719)),List(List(b1720)))
    val b1719 = CounterIter(x2878, Some(0)).name("b1719").ctrl(x2904) // b1719
    val b1720 = Const(true).name("b1720").ctrl(x2904) // b1720
    val b3050 = StreamOut(field="offset").name("b3050").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // x2880 = StreamOutNew(BurstCmdBus)
    isAccum(b3050) = false
    bufferDepthOf(b3050) = 1
    val b3051 = StreamOut(field="size").name("b3051").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // x2880 = StreamOutNew(BurstCmdBus)
    isAccum(b3051) = false
    bufferDepthOf(b3051) = 1
    val x2881 = StreamIn(field="data").name("x2881").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // x2881 = StreamInNew(BurstDataBus())
    isAccum(x2881) = false
    bufferDepthOf(x2881) = 1
    val x2894 = UnitController(style=SeqPipe, level=InnerControl).name("x2894").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // UnitPipe(List(b1720, b1680),Block(x2893))
    val x2882 = OpDef(op=FixAdd, inputs=List(b1679, b1719)).name("x2882").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // FixAdd(b1679,b1719)
    val x2883 = x2882 // FixConvert(x2882,TRUE,_32,_0) (Same Type. No op)
    val x2884 = OpDef(op=FixSla, inputs=List(x2883, Const(4))).name("x2884").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // FixLsh(x2883,Const(4))
    val x2885 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2886 = OpDef(op=FixAdd, inputs=List(x2884, x2885)).name("x2886").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // FixAdd(x2884,x2885)
    val x2887 = OpDef(op=FixSla, inputs=List(x2886, Const(2))).name("x2887").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // FixLsh(x2886,Const(2))
    val x2888 = x2887 // FixConvert(x2887,TRUE,_64,_0)
    val x2889 = DramAddress(x2828).name("x2889").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // GetDRAMAddress(x2828)
    val x2891_x2890 = OpDef(op=FixAdd, inputs=List(x2888, x2889)).name("x2891_x2890").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // FixAdd(x2888,x2889)
    // x2891 = SimpleStruct(ArrayBuffer((offset,x2890), (size,Const(64)), (isLoad,Const(true))))
    val x2892 = OpDef(op=BitAnd, inputs=List(b1720, b1680)).name("x2892").ctrl(x2894).srcCtx("UnrollingBase.scala:28:66") // And(b1720,b1680)
    val x2893_b3052_b3050 = WriteMem(b3050, x2891_x2890).name("x2893_b3052_b3050").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // StreamWrite(x2880,x2891,x2892)
    val x2893_b3053_b3051 = WriteMem(b3051, Const(64)).name("x2893_b3053_b3051").ctrl(x2894).srcCtx("SPMV_ELL.scala:86:21") // StreamWrite(x2880,x2891,x2892)
    val x2895 = FringeDenseLoad(dram=List(x2828), cmdStream=List(b3050, b3051), dataStream=List(x2881)).name("x2895").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // FringeDenseLoad(x2828,x2880,x2881)
    val x2896 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2896").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2897 = CounterChain(List(x2896)).name("x2897").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // CounterChainNew(List(x2896))
    val x2903 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2897).name("x2903").ctrl(x2904).srcCtx("SPMV_ELL.scala:86:21") // UnrolledForeach(List(b1720, b1680),x2897,Block(Const(())),List(List(b1739)),List(List(b1740)))
    val b1739 = CounterIter(x2896, None).name("b1739").ctrl(x2903) // b1739
    val b1740 = Const(true).name("b1740").ctrl(x2903) // b1740
    val x2898 = OpDef(op=BitAnd, inputs=List(b1740, b1720)).name("x2898").ctrl(x2903).srcCtx("UnrollingBase.scala:28:66") // And(b1740,b1720)
    val x2899 = OpDef(op=BitAnd, inputs=List(x2898, b1680)).name("x2899").ctrl(x2903).srcCtx("UnrollingBase.scala:28:66") // And(x2898,b1680)
    val x2900_x2900 = ReadMem(x2881).name("x2900_x2900").ctrl(x2903).srcCtx("SPMV_ELL.scala:86:21") // ParStreamRead(x2881,List(x2899))
    val x2901_x2901 = x2900_x2900 // x2901 = VectorApply(x2900,0)
    val x2902 = StoreBanks(List(List(x2847_d0_b0)), List(b1719, b1739), x2901_x2901).name("x2902").ctrl(x2903).srcCtx("SPMV_ELL.scala:86:21") // ParSRAMStore(x2847,List(List(b1719, b1739)),List(x2901),List(x2899))
    val x2905 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x2905").ctrl(x2996).srcCtx("SPMV_ELL.scala:88:20") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x2906 = CounterChain(List(x2905)).name("x2906").ctrl(x2996).srcCtx("SPMV_ELL.scala:88:26") // CounterChainNew(List(x2905))
    val x2972 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2906).name("x2972").ctrl(x2996).srcCtx("SPMV_ELL.scala:88:26") // UnrolledForeach(List(b1680),x2906,Block(Const(())),List(List(b1750)),List(List(b1751)))
    val b1750 = CounterIter(x2905, Some(0)).name("b1750").ctrl(x2972) // b1750
    val b1751 = Const(true).name("b1751").ctrl(x2972) // b1751
    val x2907_d0_b0 = SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2907_d0_b0").ctrl(x2972).srcCtx("SPMV_ELL.scala:89:33:vec_sram") // x2907 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2907_d0_b0) = false
    bufferDepthOf(x2907_d0_b0) = 2
    staticDimsOf(x2907_d0_b0) = List(16)
    val x2908_d0_b0 = SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2908_d0_b0").ctrl(x2972).srcCtx("SPMV_ELL.scala:90:39:gather_addrs") // x2908 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2908_d0_b0) = false
    bufferDepthOf(x2908_d0_b0) = 3
    staticDimsOf(x2908_d0_b0) = List(16)
    val x2909 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2909").ctrl(x2972).srcCtx("SPMV_ELL.scala:91:21") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2910 = CounterChain(List(x2909)).name("x2910").ctrl(x2972).srcCtx("SPMV_ELL.scala:91:27") // CounterChainNew(List(x2909))
    val x2916 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2910).name("x2916").ctrl(x2972).srcCtx("SPMV_ELL.scala:91:27") // UnrolledForeach(List(b1751, b1680),x2910,Block(Const(())),List(List(b1756)),List(List(b1757)))
    val b1756 = CounterIter(x2909, None).name("b1756").ctrl(x2916) // b1756
    val b1757 = Const(true).name("b1757").ctrl(x2916) // b1757
    val x2911 = OpDef(op=BitAnd, inputs=List(b1757, b1751)).name("x2911").ctrl(x2916).srcCtx("UnrollingBase.scala:28:66") // And(b1757,b1751)
    val x2912 = OpDef(op=BitAnd, inputs=List(x2911, b1680)).name("x2912").ctrl(x2916).srcCtx("UnrollingBase.scala:28:66") // And(x2911,b1680)
    val x2913 = LoadBanks(List(x2846_d0_b0), List(b1750, b1756)).name("x2913").ctrl(x2916).srcCtx("SPMV_ELL.scala:91:61") // ParSRAMLoad(x2846,List(List(b1750, b1756)),List(x2912))
    val x2914 = x2913 // x2914 = VectorApply(x2913,0)
    val x2915 = StoreBanks(List(List(x2908_d0_b0)), List(b1756), x2914).name("x2915").ctrl(x2916).srcCtx("SPMV_ELL.scala:91:50") // ParSRAMStore(x2908,List(List(b1756)),List(x2914),List(x2912))
    val x2917_d0 = Reg(init=Some(0)).name("x2917_d0").ctrl(x2972).srcCtx("SPMV_ELL.scala:92:20") // x2917 = RegNew(Const(0))
    isAccum(x2917_d0) = false
    bufferDepthOf(x2917_d0) = 2
    val x2917_d1 = Reg(init=Some(0)).name("x2917_d1").ctrl(x2972).srcCtx("SPMV_ELL.scala:92:20") // x2917 = RegNew(Const(0))
    isAccum(x2917_d1) = false
    bufferDepthOf(x2917_d1) = 2
    val x2920 = UnitController(style=SeqPipe, level=InnerControl).name("x2920").ctrl(x2972).srcCtx("SPMV_ELL.scala:92:20") // UnitPipe(List(b1751, b1680),Block(x2919))
    val x2918 = OpDef(op=BitAnd, inputs=List(b1751, b1680)).name("x2918").ctrl(x2920).srcCtx("UnrollingBase.scala:28:66") // And(b1751,b1680)
    val x2919_x2917_d0 = WriteMem(x2917_d0, Const(16)).name("x2919_x2917_d0").ctrl(x2920).srcCtx("SPMV_ELL.scala:92:20") // RegWrite(x2917,Const(16),x2918)
    val x2919_x2917_d1 = WriteMem(x2917_d1, Const(16)).name("x2919_x2917_d1").ctrl(x2920).srcCtx("SPMV_ELL.scala:92:20") // RegWrite(x2917,Const(16),x2918)
    val x2951 = UnitController(style=StreamPipe, level=OuterControl).name("x2951").ctrl(x2972).srcCtx("SPMV_ELL.scala:92:20") // UnitPipe(List(b1751, b1680),Block(Const(())))
    val x2921 = StreamOut(field="addr").name("x2921").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // x2921 = StreamOutNew(GatherAddrBus)
    isAccum(x2921) = false
    bufferDepthOf(x2921) = 1
    val x2922 = StreamIn(field="data").name("x2922").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // x2922 = StreamInNew(GatherDataBus())
    isAccum(x2922) = false
    bufferDepthOf(x2922) = 1
    val x2923 = ReadMem(x2917_d1).name("x2923").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // RegRead(x2917)
    val x2924 = Counter(min=Const(0), max=x2923, step=Const(1), par=1).name("x2924").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // CounterNew(Const(0),x2923,Const(1),Const(1))
    val x2925 = CounterChain(List(x2924)).name("x2925").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // CounterChainNew(List(x2924))
    val x2938 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2925).name("x2938").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // UnrolledForeach(List(b1751, b1680),x2925,Block(Const(())),List(List(b1773)),List(List(b1774)))
    val b1773 = CounterIter(x2924, None).name("b1773").ctrl(x2938) // b1773
    val b1774 = Const(true).name("b1774").ctrl(x2938) // b1774
    val x2926 = OpDef(op=FixLeq, inputs=List(Const(16), b1773)).name("x2926").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // FixLeq(Const(16),b1773)
    val x2927 = DramAddress(x2830).name("x2927").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // GetDRAMAddress(x2830)
    val x2928 = x2927 // FixConvert(x2927,TRUE,_64,_0) (Same Type. No op)
    val x2929 = OpDef(op=BitAnd, inputs=List(b1774, b1751)).name("x2929").ctrl(x2938).srcCtx("UnrollingBase.scala:28:66") // And(b1774,b1751)
    val x2930 = OpDef(op=BitAnd, inputs=List(x2929, b1680)).name("x2930").ctrl(x2938).srcCtx("UnrollingBase.scala:28:66") // And(x2929,b1680)
    val x2931 = LoadBanks(List(x2908_d0_b0), List(b1773)).name("x2931").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // ParSRAMLoad(x2908,List(List(b1773)),List(x2930))
    val x2932 = x2931 // x2932 = VectorApply(x2931,0)
    val x2933 = OpDef(op=FixSla, inputs=List(x2932, Const(2))).name("x2933").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // FixLsh(x2932,Const(2))
    val x2934 = x2933 // FixConvert(x2933,TRUE,_64,_0)
    val x2935 = OpDef(op=FixAdd, inputs=List(x2934, x2927)).name("x2935").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // FixAdd(x2934,x2927)
    val x2936 = OpDef(op=MuxOp, inputs=List(x2926, x2928, x2935)).name("x2936").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // Mux(x2926,x2928,x2935)
    val x2937_x2937_x2921 = WriteMem(x2921, x2936).name("x2937_x2937_x2921").ctrl(x2938).srcCtx("SPMV_ELL.scala:92:20") // ParStreamWrite(x2921,List(x2936),List(x2930))
    val x2939 = FringeSparseLoad(dram=List(x2830), addrStream=List(x2921), dataStream=List(x2922)).name("x2939").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // FringeSparseLoad(x2830,x2921,x2922)
    val x2940 = ReadMem(x2917_d0).name("x2940").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // RegRead(x2917)
    val x2941 = Counter(min=Const(0), max=x2940, step=Const(1), par=1).name("x2941").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // CounterNew(Const(0),x2940,Const(1),Const(1))
    val x2942 = CounterChain(List(x2941)).name("x2942").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // CounterChainNew(List(x2941))
    val x2950 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2942).name("x2950").ctrl(x2951).srcCtx("SPMV_ELL.scala:92:20") // UnrolledForeach(List(b1751, b1680),x2942,Block(Const(())),List(List(b1792)),List(List(b1793)))
    val b1792 = CounterIter(x2941, None).name("b1792").ctrl(x2950) // b1792
    val b1793 = Const(true).name("b1793").ctrl(x2950) // b1793
    val x2943 = OpDef(op=BitAnd, inputs=List(b1793, b1751)).name("x2943").ctrl(x2950).srcCtx("UnrollingBase.scala:28:66") // And(b1793,b1751)
    val x2944 = OpDef(op=BitAnd, inputs=List(x2943, b1680)).name("x2944").ctrl(x2950).srcCtx("UnrollingBase.scala:28:66") // And(x2943,b1680)
    val x2945_x2945 = ReadMem(x2922).name("x2945_x2945").ctrl(x2950).srcCtx("SPMV_ELL.scala:92:20") // ParStreamRead(x2922,List(x2944))
    val x2946_x2946 = x2945_x2945 // x2946 = VectorApply(x2945,0)
    val x2947 = OpDef(op=FixLt, inputs=List(b1792, Const(16))).name("x2947").ctrl(x2950).srcCtx("SPMV_ELL.scala:92:20") // FixLt(b1792,Const(16))
    val x2948 = OpDef(op=BitAnd, inputs=List(x2947, x2944)).name("x2948").ctrl(x2950).srcCtx("UnrollingTransformer.scala:239:41") // And(x2947,x2944)
    val x2949 = StoreBanks(List(List(x2907_d0_b0)), List(b1792), x2946_x2946).name("x2949").ctrl(x2950).srcCtx("SPMV_ELL.scala:92:20") // ParSRAMStore(x2907,List(List(b1792)),List(x2946),List(x2948))
    val x2952_d0 = Reg(init=Some(0)).name("x2952_d0").ctrl(x2972).srcCtx("SPMV_ELL.scala:93:38:element") // x2952 = RegNew(Const(0))
    isAccum(x2952_d0) = false
    bufferDepthOf(x2952_d0) = 2
    val x2952_d1 = Reg(init=Some(0)).name("x2952_d1").ctrl(x2972).srcCtx("SPMV_ELL.scala:93:38:element") // x2952 = RegNew(Const(0))
    isAccum(x2952_d1) = true
    bufferDepthOf(x2952_d1) = 1
    val x2953 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2953").ctrl(x2972).srcCtx("SPMV_ELL.scala:93:45") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2954 = CounterChain(List(x2953)).name("x2954").ctrl(x2972).srcCtx("SPMV_ELL.scala:93:90") // CounterChainNew(List(x2953))
    val x2967 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2954).name("x2967").ctrl(x2972).srcCtx("SPMV_ELL.scala:93:90") // UnrolledReduce(List(b1751, b1680),x2954,x2952,Block((x2952) => Const(())),List(List(b1806)),List(List(b1807)))
    val b1806 = CounterIter(x2953, None).name("b1806").ctrl(x2967) // b1806
    val b1807 = Const(true).name("b1807").ctrl(x2967) // b1807
    val x2955 = OpDef(op=BitAnd, inputs=List(b1807, b1751)).name("x2955").ctrl(x2967).srcCtx("UnrollingBase.scala:28:66") // And(b1807,b1751)
    val x2956 = OpDef(op=BitAnd, inputs=List(x2955, b1680)).name("x2956").ctrl(x2967).srcCtx("UnrollingBase.scala:28:66") // And(x2955,b1680)
    val x2957 = LoadBanks(List(x2847_d0_b0), List(b1750, b1806)).name("x2957").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:69") // ParSRAMLoad(x2847,List(List(b1750, b1806)),List(x2956))
    val x2958 = x2957 // x2958 = VectorApply(x2957,0)
    val x2959 = LoadBanks(List(x2907_d0_b0), List(b1806)).name("x2959").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:85") // ParSRAMLoad(x2907,List(List(b1806)),List(x2956))
    val x2960 = x2959 // x2960 = VectorApply(x2959,0)
    val x2961 = OpDef(op=FixMul, inputs=List(x2958, x2960)).name("x2961").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:75") // FixMul(x2958,x2960)
    val x2962 = ReadMem(x2952_d1).name("x2962").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:90") // RegRead(x2952)
    val x2963 = OpDef(op=FixEql, inputs=List(b1806, Const(0))).name("x2963").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:90") // FixEql(b1806,Const(0))
    val x2964 = ReduceAccumOp(op=FixAdd, input=x2961, accum=x2962).name("x2964").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:92") // FixAdd(x2961,x2962)
    val x2965 = OpDef(op=BitAnd, inputs=List(b1751, b1680)).name("x2965").ctrl(x2967).srcCtx("UnrollingBase.scala:28:66") // And(b1751,b1680)
    val x2966_x2952_d0 = WriteMem(x2952_d0, x2964).name("x2966_x2952_d0").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:90") // RegWrite(x2952,x2964,x2965)
    antiDepsOf(x2966_x2952_d0)=List(x2962)
    val x2966_x2952_d1 = WriteMem(x2952_d1, x2964).name("x2966_x2952_d1").ctrl(x2967).srcCtx("SPMV_ELL.scala:93:90") // RegWrite(x2952,x2964,x2965)
    antiDepsOf(x2966_x2952_d1)=List(x2962)
    val x2971 = UnitController(style=SeqPipe, level=InnerControl).name("x2971").ctrl(x2972).srcCtx("SPMV_ELL.scala:88:26") // UnitPipe(List(b1751, b1680),Block(Const(())))
    val x2968 = ReadMem(x2952_d0).name("x2968").ctrl(x2971).srcCtx("SPMV_ELL.scala:94:28") // RegRead(x2952)
    val x2969 = OpDef(op=BitAnd, inputs=List(b1751, b1680)).name("x2969").ctrl(x2971).srcCtx("UnrollingBase.scala:28:66") // And(b1751,b1680)
    val x2970 = StoreBanks(List(List(x2848_d0_b0)), List(b1750), x2968).name("x2970").ctrl(x2971).srcCtx("SPMV_ELL.scala:94:26") // SRAMStore(x2848,ArrayBuffer(Const(32)),List(b1750),Const(0),x2968,x2969)
    val x2995 = UnitController(style=StreamPipe, level=OuterControl).name("x2995").ctrl(x2996).srcCtx("SPMV_ELL.scala:97:36") // UnitPipe(List(b1680),Block(Const(())))
    val b3054 = StreamOut(field="offset").name("b3054").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // x2973 = StreamOutNew(BurstCmdBus)
    isAccum(b3054) = false
    bufferDepthOf(b3054) = 1
    val b3055 = StreamOut(field="size").name("b3055").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // x2973 = StreamOutNew(BurstCmdBus)
    isAccum(b3055) = false
    bufferDepthOf(b3055) = 1
    val x2974 = StreamOut(field="data").name("x2974").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // x2974 = StreamOutNew(BurstFullDataBus())
    isAccum(x2974) = false
    bufferDepthOf(x2974) = 1
    val x2975 = StreamIn(field="ack").name("x2975").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // x2975 = StreamInNew(BurstAckBus)
    isAccum(x2975) = false
    bufferDepthOf(x2975) = 1
    val x2983 = UnitController(style=SeqPipe, level=InnerControl).name("x2983").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // UnitPipe(List(b1680),Block(x2982))
    val x2976 = b1679 // FixConvert(b1679,TRUE,_32,_0) (Same Type. No op)
    val x2977 = OpDef(op=FixSla, inputs=List(x2976, Const(2))).name("x2977").ctrl(x2983).srcCtx("SPMV_ELL.scala:97:36") // FixLsh(x2976,Const(2))
    val x2978 = x2977 // FixConvert(x2977,TRUE,_64,_0)
    val x2979 = DramAddress(x2831).name("x2979").ctrl(x2983).srcCtx("SPMV_ELL.scala:97:36") // GetDRAMAddress(x2831)
    val x2981_x2980 = OpDef(op=FixAdd, inputs=List(x2978, x2979)).name("x2981_x2980").ctrl(x2983).srcCtx("SPMV_ELL.scala:97:36") // FixAdd(x2978,x2979)
    // x2981 = SimpleStruct(ArrayBuffer((offset,x2980), (size,Const(128)), (isLoad,Const(false))))
    val x2982_b3056_b3054 = WriteMem(b3054, x2981_x2980).name("x2982_b3056_b3054").ctrl(x2983).srcCtx("SPMV_ELL.scala:97:36") // StreamWrite(x2973,x2981,b1680)
    val x2982_b3057_b3055 = WriteMem(b3055, Const(128)).name("x2982_b3057_b3055").ctrl(x2983).srcCtx("SPMV_ELL.scala:97:36") // StreamWrite(x2973,x2981,b1680)
    val x2984 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x2984").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x2985 = CounterChain(List(x2984)).name("x2985").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // CounterChainNew(List(x2984))
    val x2991 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2985).name("x2991").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // UnrolledForeach(List(b1680),x2985,Block(Const(())),List(List(b1839)),List(List(b1840)))
    val b1839 = CounterIter(x2984, None).name("b1839").ctrl(x2991) // b1839
    val b1840 = Const(true).name("b1840").ctrl(x2991) // b1840
    val x2986 = OpDef(op=BitAnd, inputs=List(b1840, b1680)).name("x2986").ctrl(x2991).srcCtx("UnrollingBase.scala:28:66") // And(b1840,b1680)
    val x2987 = LoadBanks(List(x2848_d0_b0), List(b1839)).name("x2987").ctrl(x2991).srcCtx("SPMV_ELL.scala:97:36") // ParSRAMLoad(x2848,List(List(b1839)),List(x2986))
    val x2989_x2988 = x2987 // x2988 = VectorApply(x2987,0)
    // x2989 = SimpleStruct(ArrayBuffer((_1,x2988), (_2,Const(true))))
    val x2990_x2990_x2974 = WriteMem(x2974, x2989_x2988).name("x2990_x2990_x2974").ctrl(x2991).srcCtx("SPMV_ELL.scala:97:36") // ParStreamWrite(x2974,List(x2989),List(x2986))
    val x2992 = FringeDenseStore(dram=List(x2831), cmdStream=List(b3054, b3055), dataStream=List(x2974), ackStream=List(x2975)).name("x2992").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // FringeDenseStore(x2831,x2973,x2974,x2975)
    val x2994 = UnitController(style=SeqPipe, level=InnerControl).name("x2994").ctrl(x2995).srcCtx("SPMV_ELL.scala:97:36") // UnitPipe(List(b1680),Block(Const(())))
    val x2993_x2993 = ReadMem(x2975).name("x2993_x2993").ctrl(x2994).srcCtx("SPMV_ELL.scala:97:36") // StreamRead(x2975,b1680)
    
  }
}
