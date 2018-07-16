import pir._
import pir.node._
import arch._
import prism.enums._

object PensieveStream extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x3094 = withCtrl(design.top.topController) { StreamIn(field="data").name("x3094").srcCtx("PensieveStream.scala:70:49:stream_in") } // x3094 = StreamInNew(GPInput1)
    countsOf(x3094) = Some(1024l)
    isAccum(x3094) = false
    bufferDepthOf(x3094) = 1
    val x3095 = withCtrl(design.top.topController) { StreamOut(field="data").name("x3095").srcCtx("PensieveStream.scala:72:50:stream_out") } // x3095 = StreamOutNew(GPOutput1)
    isAccum(x3095) = false
    bufferDepthOf(x3095) = 1
    // x3096 = Forever() TODO: Unmatched Node
    val x3285 = withCtrl(design.top.topController) { ForeverController().name("x3285").srcCtx("PensieveStream.scala:80:26") } // Hwblock(Block(Const(())),true)
    val x3097 = withCtrl(x3285) { Reg(init=Some(0.0)).name("x3097").srcCtx("PensieveStream.scala:82:47:s_reg") } // x3097 = RegNew(Const(0.0))
    isAccum(x3097) = false
    bufferDepthOf(x3097) = 2
    val x3098_d0_b0 = withCtrl(x3285) { RegFile(size=36, inits=None).name("x3098_d0_b0").srcCtx("PensieveStream.scala:84:52:L1_res") } // x3098 = RegFileNew(ArrayBuffer(Const(36)),None) banking:Strided(banks=1, stride=1)
    isAccum(x3098_d0_b0) = false
    bufferDepthOf(x3098_d0_b0) = 2
    staticDimsOf(x3098_d0_b0) = List(36)
    val x3099_d0_b0 = withCtrl(x3285) { RegFile(size=2, inits=None).name("x3099_d0_b0").srcCtx("PensieveStream.scala:85:52:L2_res") } // x3099 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x3099_d0_b0) = false
    bufferDepthOf(x3099_d0_b0) = 2
    staticDimsOf(x3099_d0_b0) = List(2)
    val x3100_d0_b0 = withCtrl(x3285) { RegFile(size=2, inits=None).name("x3100_d0_b0").srcCtx("PensieveStream.scala:86:52:L3_res") } // x3100 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x3100_d0_b0) = false
    bufferDepthOf(x3100_d0_b0) = 1
    staticDimsOf(x3100_d0_b0) = List(2)
    val x3100_d1_b0 = withCtrl(x3285) { RegFile(size=2, inits=None).name("x3100_d1_b0").srcCtx("PensieveStream.scala:86:52:L3_res") } // x3100 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x3100_d1_b0) = false
    bufferDepthOf(x3100_d1_b0) = 1
    staticDimsOf(x3100_d1_b0) = List(2)
    val x3101_d0_b0 = withCtrl(x3285) { LUT(inits=List(100.0, 2.0, 4.0, 5.0, 5.0, 23.0, 10.0, 2.0, 0.0), banking=Strided(banks=1, stride=1)).name("x3101_d0_b0").srcCtx("PensieveStream.scala:88:83:input_pad") } // x3101 = LUTNew(List(9), Seq(Const(100.0000),Const(2),Const(4)... [6 more]))
    isAccum(x3101_d0_b0) = false
    bufferDepthOf(x3101_d0_b0) = 1
    staticDimsOf(x3101_d0_b0) = List(9)
    val x3101_d1_b0 = withCtrl(x3285) { LUT(inits=List(100.0, 2.0, 4.0, 5.0, 5.0, 23.0, 10.0, 2.0, 0.0), banking=Strided(banks=1, stride=1)).name("x3101_d1_b0").srcCtx("PensieveStream.scala:88:83:input_pad") } // x3101 = LUTNew(List(9), Seq(Const(100.0000),Const(2),Const(4)... [6 more]))
    isAccum(x3101_d1_b0) = false
    bufferDepthOf(x3101_d1_b0) = 1
    staticDimsOf(x3101_d1_b0) = List(9)
    val x3102_d0_b0 = withCtrl(x3285) { LUT(inits=List(1.1070536, -1.1014725, 0.7829188, 0.06946874, 0.89654195, -0.13441932, -0.21465302, 0.65010464), banking=Strided(banks=1, stride=4)).name("x3102_d0_b0").srcCtx("PensieveStream.scala:90:107:Conv_Filter_LUT") } // x3102 = LUTNew(List(2, 4), Seq(Const(1.10705363750457763671875),Const(-1.10147249698638916015625),Const(0.78291881084442138671875)... [5 more]))
    isAccum(x3102_d0_b0) = false
    bufferDepthOf(x3102_d0_b0) = 1
    staticDimsOf(x3102_d0_b0) = List(2, 4)
    val x3103_d0_b0 = withCtrl(x3285) { LUT(inits=List(9.117608E-10, -3.757097E-9, -2.2367015E-9, 4.3611426E-9), banking=Strided(banks=1, stride=2)).name("x3103_d0_b0").srcCtx("PensieveStream.scala:91:88:Conv_B_LUT") } // x3103 = LUTNew(List(2, 2), Seq(Const(911.7607779884906449296977370977402E-12),Const(-3.757096855849795247195288538932800E-9),Const(-2.236701490687664772849529981613159E-9)... [1 more]))
    isAccum(x3103_d0_b0) = false
    bufferDepthOf(x3103_d0_b0) = 1
    staticDimsOf(x3103_d0_b0) = List(2, 2)
    val x3104_d0_b0 = withCtrl(x3285) { LUT(inits=List(-0.0039636497, -0.028346648, 0.020767855, -0.0075885993), banking=Strided(banks=1, stride=2)).name("x3104_d0_b0").srcCtx("PensieveStream.scala:93:98:L1_Neuron_W_LUT") } // x3104 = LUTNew(List(2, 2), Seq(Const(-0.0039636497385799884796142578125),Const(-0.02834664843976497650146484375),Const(0.02076785452663898468017578125)... [1 more]))
    isAccum(x3104_d0_b0) = false
    bufferDepthOf(x3104_d0_b0) = 1
    staticDimsOf(x3104_d0_b0) = List(2, 2)
    val x3105_d0_b0 = withCtrl(x3285) { LUT(inits=List(0.0, 0.0, -2.3394322E-9, 0.0), banking=Strided(banks=1, stride=2)).name("x3105_d0_b0").srcCtx("PensieveStream.scala:94:98:L1_Neuron_B_LUT") } // x3105 = LUTNew(List(2, 2), Seq(Const(0.0),Const(0.0),Const(-2.339432203513069907785393297672272E-9)... [1 more]))
    isAccum(x3105_d0_b0) = false
    bufferDepthOf(x3105_d0_b0) = 1
    staticDimsOf(x3105_d0_b0) = List(2, 2)
    val x3106_d0_b0 = withCtrl(x3285) { LUT(inits=List(0.0073153283, -0.018115155, 0.012374142, 0.014655014, 0.019269928, 0.019937111, -0.0037852898, -0.011396266, 0.00413625, 0.011667735, 0.013136478, 0.013895424, 0.010797741, -0.005167095, -0.012356101, -0.036091283, 0.02317957, 0.023949062, -0.0014171642, -0.020967398, 0.020974796, 0.027364682, 0.010442663, -0.008874505, -0.024496669, 0.00364893, -0.015907185, 0.009180543, -0.0025278341, -0.011214394, -0.010371242, -0.029516285, -0.019651568, -0.022036802, -0.021434795, -0.008742628, -0.0022201696, 0.009537035, -0.01794218, -0.019422796, 0.038061175, 0.0010102539, 0.004162226, 0.02918302, -0.009055124, -0.019078884, -0.0017742182, 0.0038822887, 0.0070317835, -0.0037937541, -0.0046850424, 0.017981764, -0.012270694, -0.014793315, 0.007864123, -0.0057645664, 0.0064337505, -0.022930298, 0.020787317, 0.0104682185, -0.0077496995, 0.0046270993, 0.0022271702, 0.0020911873, -0.022351695, 0.0038946508, 0.016547974, 0.0060300515, 0.015459504, 0.008641606, 0.012018808, 0.008022873), banking=Strided(banks=1, stride=36)).name("x3106_d0_b0").srcCtx("PensieveStream.scala:97:91:L2_W_LUT") } // x3106 = LUTNew(List(2, 36), Seq(Const(0.00731532834470272064208984375),Const(-0.018115155398845672607421875),Const(0.01237414218485355377197265625)... [69 more]))
    isAccum(x3106_d0_b0) = false
    bufferDepthOf(x3106_d0_b0) = 1
    staticDimsOf(x3106_d0_b0) = List(2, 36)
    val x3107_d0_b0 = withCtrl(x3285) { LUT(inits=List(0.0, -1.9464758E-7), banking=Strided(banks=1, stride=1)).name("x3107_d0_b0").srcCtx("PensieveStream.scala:98:75:L2_B_LUT") } // x3107 = LUTNew(List(2), Seq(Const(0.0),Const(-0.0000001946475833847216563299298286437988)))
    isAccum(x3107_d0_b0) = false
    bufferDepthOf(x3107_d0_b0) = 1
    staticDimsOf(x3107_d0_b0) = List(2)
    val x3108_d0_b0 = withCtrl(x3285) { LUT(inits=List(-0.008018219, -0.010223609, -0.016582794, -0.008717628), banking=Strided(banks=1, stride=2)).name("x3108_d0_b0").srcCtx("PensieveStream.scala:101:91:L3_SM_W_LUT") } // x3108 = LUTNew(List(2, 2), Seq(Const(-0.008018218912184238433837890625),Const(-0.010223609395325183868408203125),Const(-0.016582794487476348876953125)... [1 more]))
    isAccum(x3108_d0_b0) = false
    bufferDepthOf(x3108_d0_b0) = 1
    staticDimsOf(x3108_d0_b0) = List(2, 2)
    val x3109_d0_b0 = withCtrl(x3285) { LUT(inits=List(9.070664E-5, -8.4158484E-5), banking=Strided(banks=1, stride=1)).name("x3109_d0_b0").srcCtx("PensieveStream.scala:102:75:L3_SM_B_LUT") } // x3109 = LUTNew(List(2), Seq(Const(0.000090706642367877066135406494140625),Const(-0.000084158484241925179958343505859375)))
    isAccum(x3109_d0_b0) = false
    bufferDepthOf(x3109_d0_b0) = 1
    staticDimsOf(x3109_d0_b0) = List(2)
    val x3112 = withCtrl(x3285) { UnitController(style=SeqPipe, level=InnerControl).name("x3112").srcCtx("PensieveStream.scala:106:11") } // UnitPipe(List(Const(true)),Block(x3111))
    val x3110_x3110 = withCtrl(x3112) { ReadMem(x3094).name("x3110_x3110").srcCtx("PensieveStream.scala:107:37") } // StreamRead(x3094,Const(true))
    val x3111_x3097 = withCtrl(x3112) { WriteMem(x3097, x3110_x3110).name("x3111_x3097").srcCtx("PensieveStream.scala:107:24") } // RegWrite(x3097,x3110,Const(true))
    val x3180 = withCtrl(x3285) { UnitController(style=SeqPipe, level=OuterControl).name("x3180").srcCtx("PensieveStream.scala:110:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3113 = withCtrl(x3180) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3113").srcCtx("PensieveStream.scala:114:51") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3114 = withCtrl(x3180) { CounterChain(List(x3113)).name("x3114").srcCtx("PensieveStream.scala:114:70") } // CounterChainNew(List(x3113))
    val x3157 = withCtrl(x3180) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3114).name("x3157").srcCtx("PensieveStream.scala:114:70") } // UnrolledForeach(List(Const(true)),x3114,Block(Const(())),List(List(b2131)),List(List(b2132)))
    val b2131 = withCtrl(x3157) { CounterIter(x3113, Some(0)).name("b2131") } // b2131
    val b2132 = withCtrl(x3157) { Const(true).name("b2132") } // b2132
    val x3115 = withCtrl(x3157) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x3115").srcCtx("PensieveStream.scala:115:59") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x3116 = withCtrl(x3157) { CounterChain(List(x3115)).name("x3116").srcCtx("PensieveStream.scala:115:77") } // CounterChainNew(List(x3115))
    val x3156 = withCtrl(x3157) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3116).name("x3156").srcCtx("PensieveStream.scala:115:77") } // UnrolledForeach(List(b2132),x3116,Block(Const(())),List(List(b2135)),List(List(b2136)))
    val b2135 = withCtrl(x3156) { CounterIter(x3115, Some(0)).name("b2135") } // b2135
    val b2136 = withCtrl(x3156) { Const(true).name("b2136") } // b2136
    val x3117 = withCtrl(x3156) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3117").srcCtx("PensieveStream.scala:117:67") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3118 = withCtrl(x3156) { CounterChain(List(x3117)).name("x3118").srcCtx("PensieveStream.scala:117:85") } // CounterChainNew(List(x3117))
    val x3155 = withCtrl(x3156) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3118).name("x3155").srcCtx("PensieveStream.scala:117:85") } // UnrolledForeach(List(b2136, b2132),x3118,Block(Const(())),List(List(b2139)),List(List(b2140)))
    val b2139 = withCtrl(x3155) { CounterIter(x3117, Some(0)).name("b2139") } // b2139
    val b2140 = withCtrl(x3155) { Const(true).name("b2140") } // b2140
    val x3119 = withCtrl(x3155) { Reg(init=Some(0.0)).name("x3119").srcCtx("PensieveStream.scala:119:83:w") } // x3119 = RegNew(Const(0.0))
    isAccum(x3119) = false
    bufferDepthOf(x3119) = 1
    val x3120_d0 = withCtrl(x3155) { Reg(init=Some(0.0)).name("x3120_d0").srcCtx("PensieveStream.scala:120:87") } // x3120 = RegNew(Const(0.0))
    isAccum(x3120_d0) = false
    bufferDepthOf(x3120_d0) = 2
    val x3120_d1 = withCtrl(x3155) { Reg(init=Some(0.0)).name("x3120_d1").srcCtx("PensieveStream.scala:120:87") } // x3120 = RegNew(Const(0.0))
    isAccum(x3120_d1) = true
    bufferDepthOf(x3120_d1) = 1
    val x3121 = withCtrl(x3155) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3121").srcCtx("PensieveStream.scala:120:114") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3122 = withCtrl(x3155) { CounterChain(List(x3121)).name("x3122").srcCtx("PensieveStream.scala:123:66") } // CounterChainNew(List(x3121))
    val x3138 = withCtrl(x3155) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3122).name("x3138").srcCtx("PensieveStream.scala:123:66") } // UnrolledReduce(List(b2140, b2136, b2132),x3122,x3120,Block((x3120) => Const(())),List(List(b2145)),List(List(b2146)))
    val b2145 = withCtrl(x3138) { CounterIter(x3121, None).name("b2145") } // b2145
    val b2146 = withCtrl(x3138) { Const(true).name("b2146") } // b2146
    val x3123 = withCtrl(x3138) { OpDef(op=FixSla, inputs=List(b2139, Const(1))).name("x3123").srcCtx("PensieveStream.scala:121:94") } // FixLsh(b2139,Const(1))
    val x3124 = withCtrl(x3138) { OpDef(op=FixAdd, inputs=List(x3123, b2145)).name("x3124").srcCtx("PensieveStream.scala:121:108") } // FixAdd(x3123,b2145)
    val x3125 = withCtrl(x3138) { OpDef(op=BitAnd, inputs=List(b2146, b2140)).name("x3125").srcCtx("UnrollingBase.scala:28:66") } // And(b2146,b2140)
    val x3126 = withCtrl(x3138) { OpDef(op=BitAnd, inputs=List(b2136, b2132)).name("x3126").srcCtx("UnrollingBase.scala:28:66") } // And(b2136,b2132)
    val x3127 = withCtrl(x3138) { OpDef(op=BitAnd, inputs=List(x3125, x3126)).name("x3127").srcCtx("UnrollingBase.scala:28:66") } // And(x3125,x3126)
    val x3128 = withCtrl(x3138) { LoadBanks(List(x3102_d0_b0), List(b2131, x3124)).name("x3128").srcCtx("PensieveStream.scala:121:88") } // LUTLoad(x3102,List(b2131, x3124),x3127)
    val x3129 = withCtrl(x3138) { OpDef(op=FixAdd, inputs=List(b2135, b2145)).name("x3129").srcCtx("PensieveStream.scala:121:127") } // FixAdd(b2135,b2145)
    val x3130 = withCtrl(x3138) { LoadBanks(List(x3101_d1_b0), List(x3129)).name("x3130").srcCtx("PensieveStream.scala:121:124") } // LUTLoad(x3101,List(x3129),x3127)
    val x3131 = withCtrl(x3138) { OpDef(op=FltMul, inputs=List(x3128, x3130)).name("x3131").srcCtx("PensieveStream.scala:121:113") } // FltMul(x3128,x3130)
    val x3132 = withCtrl(x3138) { ReadMem(x3120_d1).name("x3132").srcCtx("PensieveStream.scala:123:66") } // RegRead(x3120)
    val x3133 = withCtrl(x3138) { OpDef(op=FixEql, inputs=List(b2145, Const(0))).name("x3133").srcCtx("PensieveStream.scala:123:66") } // FixEql(b2145,Const(0))
    val x3134 = withCtrl(x3138) { ReduceAccumOp(op=FltAdd, input=x3131, accum=x3132).name("x3134").srcCtx("PensieveStream.scala:123:69") } // FltAdd(x3131,x3132)
    val x3135 = withCtrl(x3138) { OpDef(op=BitAnd, inputs=List(b2140, b2136)).name("x3135").srcCtx("UnrollingBase.scala:28:66") } // And(b2140,b2136)
    val x3136 = withCtrl(x3138) { OpDef(op=BitAnd, inputs=List(x3135, b2132)).name("x3136").srcCtx("UnrollingBase.scala:28:66") } // And(x3135,b2132)
    val x3137_x3120_d0 = withCtrl(x3138) { WriteMem(x3120_d0, x3134).name("x3137_x3120_d0").srcCtx("PensieveStream.scala:123:66") } // RegWrite(x3120,x3134,x3136)
    antiDepsOf(x3137_x3120_d0)=List(x3132)
    val x3137_x3120_d1 = withCtrl(x3138) { WriteMem(x3120_d1, x3134).name("x3137_x3120_d1").srcCtx("PensieveStream.scala:123:66") } // RegWrite(x3120,x3134,x3136)
    antiDepsOf(x3137_x3120_d1)=List(x3132)
    val x3154 = withCtrl(x3155) { UnitController(style=SeqPipe, level=InnerControl).name("x3154").srcCtx("PensieveStream.scala:117:85") } // UnitPipe(List(b2140, b2136, b2132),Block(Const(())))
    val x3139 = withCtrl(x3154) { ReadMem(x3120_d0).name("x3139").srcCtx("PensieveStream.scala:123:66") } // RegRead(x3120)
    val x3140 = withCtrl(x3154) { OpDef(op=BitAnd, inputs=List(b2140, b2136)).name("x3140").srcCtx("UnrollingBase.scala:28:66") } // And(b2140,b2136)
    val x3141 = withCtrl(x3154) { OpDef(op=BitAnd, inputs=List(x3140, b2132)).name("x3141").srcCtx("UnrollingBase.scala:28:66") } // And(x3140,b2132)
    val x3142_x3119 = withCtrl(x3154) { WriteMem(x3119, x3139).name("x3142_x3119").srcCtx("PensieveStream.scala:120:67") } // RegWrite(x3119,x3139,x3141)
    val x3143 = withCtrl(x3154) { OpDef(op=FixSla, inputs=List(b2131, Const(4))).name("x3143").srcCtx("PensieveStream.scala:125:74") } // FixLsh(b2131,Const(4))
    val x3144 = withCtrl(x3154) { OpDef(op=FixSla, inputs=List(b2135, Const(1))).name("x3144").srcCtx("PensieveStream.scala:125:95") } // FixLsh(b2135,Const(1))
    val x3145 = withCtrl(x3154) { OpDef(op=FixAdd, inputs=List(x3143, x3144)).name("x3145").srcCtx("PensieveStream.scala:125:91") } // FixAdd(x3143,x3144)
    val x3146 = withCtrl(x3154) { OpDef(op=FixAdd, inputs=List(x3145, b2139)).name("x3146").srcCtx("PensieveStream.scala:125:109") } // FixAdd(x3145,b2139)
    val x3147 = withCtrl(x3154) { ReadMem(x3119).name("x3147").srcCtx("PensieveStream.scala:125:120") } // RegRead(x3119)
    antiDepsOf(x3147)=List(x3142_x3119)
    val x3148 = withCtrl(x3154) { LoadBanks(List(x3103_d0_b0), List(b2131, b2139)).name("x3148").srcCtx("PensieveStream.scala:125:134") } // LUTLoad(x3103,List(b2131, b2139),x3141)
    val x3149 = withCtrl(x3154) { OpDef(op=FltAdd, inputs=List(x3147, x3148)).name("x3149").srcCtx("PensieveStream.scala:125:122") } // FltAdd(x3147,x3148)
    val x3150 = withCtrl(x3154) { ReadMem(x3097).name("x3150").srcCtx("PensieveStream.scala:125:143") } // RegRead(x3097)
    val x3151 = withCtrl(x3154) { OpDef(op=FltAdd, inputs=List(x3149, x3150)).name("x3151").srcCtx("PensieveStream.scala:125:141") } // FltAdd(x3149,x3150)
    val x3152 = withCtrl(x3154) { OpDef(op=FltMax, inputs=List(x3151, Const(0.0))).name("x3152").srcCtx("PensieveStream.scala:125:119") } // Max(x3151,Const(0.0))
    val x3153 = withCtrl(x3154) { StoreBanks(List(List(x3098_d0_b0)), List(x3146), x3152).name("x3153").srcCtx("PensieveStream.scala:125:114") } // RegFileStore(x3098,List(x3146),x3152,x3141)
    val x3158 = withCtrl(x3180) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3158").srcCtx("PensieveStream.scala:132:51") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3159 = withCtrl(x3180) { CounterChain(List(x3158)).name("x3159").srcCtx("PensieveStream.scala:132:72") } // CounterChainNew(List(x3158))
    val x3179 = withCtrl(x3180) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3159).name("x3179").srcCtx("PensieveStream.scala:132:72") } // UnrolledForeach(List(Const(true)),x3159,Block(Const(())),List(List(b2184)),List(List(b2185)))
    val b2184 = withCtrl(x3179) { CounterIter(x3158, Some(0)).name("b2184") } // b2184
    val b2185 = withCtrl(x3179) { Const(true).name("b2185") } // b2185
    val x3160 = withCtrl(x3179) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3160").srcCtx("PensieveStream.scala:133:59") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3161 = withCtrl(x3179) { CounterChain(List(x3160)).name("x3161").srcCtx("PensieveStream.scala:133:80") } // CounterChainNew(List(x3160))
    val x3178 = withCtrl(x3179) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3161).name("x3178").srcCtx("PensieveStream.scala:133:80") } // UnrolledForeach(List(b2185),x3161,Block(Const(())),List(List(b2188)),List(List(b2189)))
    val b2188 = withCtrl(x3178) { CounterIter(x3160, None).name("b2188") } // b2188
    val b2189 = withCtrl(x3178) { Const(true).name("b2189") } // b2189
    val x3162 = withCtrl(x3178) { Reg(init=Some(0.0)).name("x3162").srcCtx("PensieveStream.scala:135:68:w") } // x3162 = RegNew(Const(0.0))
    isAccum(x3162) = false
    bufferDepthOf(x3162) = 1
    val x3163 = withCtrl(x3178) { OpDef(op=BitAnd, inputs=List(b2189, b2185)).name("x3163").srcCtx("UnrollingBase.scala:28:66") } // And(b2189,b2185)
    val x3164 = withCtrl(x3178) { LoadBanks(List(x3104_d0_b0), List(b2184, b2188)).name("x3164").srcCtx("PensieveStream.scala:137:77") } // LUTLoad(x3104,List(b2184, b2188),x3163)
    val x3165 = withCtrl(x3178) { x3164 } // FltConvert(x3164,_24,_8) //TODO
    val x3166 = withCtrl(x3178) { OpDef(op=FixAdd, inputs=List(b2184, Const(1))).name("x3166").srcCtx("PensieveStream.scala:137:106") } // FixAdd(b2184,Const(1))
    val x3167 = withCtrl(x3178) { LoadBanks(List(x3101_d0_b0), List(x3166)).name("x3167").srcCtx("PensieveStream.scala:137:104") } // LUTLoad(x3101,List(x3166),x3163)
    val x3168 = withCtrl(x3178) { OpDef(op=FltMul, inputs=List(x3165, x3167)).name("x3168").srcCtx("PensieveStream.scala:137:93") } // FltMul(x3165,x3167)
    val x3169_x3162 = withCtrl(x3178) { WriteMem(x3162, x3168).name("x3169_x3162").srcCtx("PensieveStream.scala:137:59") } // RegWrite(x3162,x3168,x3163)
    val x3170 = withCtrl(x3178) { OpDef(op=FixSla, inputs=List(b2184, Const(1))).name("x3170").srcCtx("PensieveStream.scala:139:93") } // FixLsh(b2184,Const(1))
    val x3171 = withCtrl(x3178) { OpDef(op=FixAdd, inputs=List(Const(32), x3170)).name("x3171").srcCtx("PensieveStream.scala:139:89") } // FixAdd(Const(32),x3170)
    val x3172 = withCtrl(x3178) { OpDef(op=FixAdd, inputs=List(x3171, b2188)).name("x3172").srcCtx("PensieveStream.scala:139:110") } // FixAdd(x3171,b2188)
    val x3173 = withCtrl(x3178) { ReadMem(x3162).name("x3173").srcCtx("PensieveStream.scala:139:121") } // RegRead(x3162)
    antiDepsOf(x3173)=List(x3169_x3162)
    val x3174 = withCtrl(x3178) { LoadBanks(List(x3105_d0_b0), List(b2184, b2188)).name("x3174").srcCtx("PensieveStream.scala:139:140") } // LUTLoad(x3105,List(b2184, b2188),x3163)
    val x3175 = withCtrl(x3178) { OpDef(op=FltAdd, inputs=List(x3173, x3174)).name("x3175").srcCtx("PensieveStream.scala:139:123") } // FltAdd(x3173,x3174)
    val x3176 = withCtrl(x3178) { OpDef(op=FltMax, inputs=List(x3175, Const(0.0))).name("x3176").srcCtx("PensieveStream.scala:139:120") } // Max(x3175,Const(0.0))
    val x3177 = withCtrl(x3178) { StoreBanks(List(List(x3098_d0_b0)), List(x3172), x3176).name("x3177").srcCtx("PensieveStream.scala:139:115") } // ParRegFileStore(x3098,List(List(x3172)),List(x3176),List(Const(true)))
    val x3207 = withCtrl(x3285) { UnitController(style=SeqPipe, level=OuterControl).name("x3207").srcCtx("PensieveStream.scala:157:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3181 = withCtrl(x3207) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3181").srcCtx("PensieveStream.scala:159:43") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3182 = withCtrl(x3207) { CounterChain(List(x3181)).name("x3182").srcCtx("PensieveStream.scala:159:64") } // CounterChainNew(List(x3181))
    val x3206 = withCtrl(x3207) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3182).name("x3206").srcCtx("PensieveStream.scala:159:64") } // UnrolledForeach(List(Const(true)),x3182,Block(Const(())),List(List(b2211)),List(List(b2212)))
    val b2211 = withCtrl(x3206) { CounterIter(x3181, Some(0)).name("b2211") } // b2211
    val b2212 = withCtrl(x3206) { Const(true).name("b2212") } // b2212
    val x3183 = withCtrl(x3206) { Reg(init=Some(0.0)).name("x3183").srcCtx("PensieveStream.scala:161:52:w") } // x3183 = RegNew(Const(0.0))
    isAccum(x3183) = false
    bufferDepthOf(x3183) = 1
    val x3184_d0 = withCtrl(x3206) { Reg(init=Some(0.0)).name("x3184_d0").srcCtx("PensieveStream.scala:163:63") } // x3184 = RegNew(Const(0.0))
    isAccum(x3184_d0) = false
    bufferDepthOf(x3184_d0) = 2
    val x3184_d1 = withCtrl(x3206) { Reg(init=Some(0.0)).name("x3184_d1").srcCtx("PensieveStream.scala:163:63") } // x3184 = RegNew(Const(0.0))
    isAccum(x3184_d1) = true
    bufferDepthOf(x3184_d1) = 1
    val x3185 = withCtrl(x3206) { Counter(min=Const(0), max=Const(36), step=Const(1), par=1).name("x3185").srcCtx("PensieveStream.scala:163:93") } // CounterNew(Const(0),Const(36),Const(1),Const(1))
    val x3186 = withCtrl(x3206) { CounterChain(List(x3185)).name("x3186").srcCtx("PensieveStream.scala:165:42") } // CounterChainNew(List(x3185))
    val x3197 = withCtrl(x3206) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3186).name("x3197").srcCtx("PensieveStream.scala:165:42") } // UnrolledReduce(List(b2212),x3186,x3184,Block((x3184) => Const(())),List(List(b2217)),List(List(b2218)))
    val b2217 = withCtrl(x3197) { CounterIter(x3185, None).name("b2217") } // b2217
    val b2218 = withCtrl(x3197) { Const(true).name("b2218") } // b2218
    val x3187 = withCtrl(x3197) { OpDef(op=BitAnd, inputs=List(b2218, b2212)).name("x3187").srcCtx("UnrollingBase.scala:28:66") } // And(b2218,b2212)
    val x3188 = withCtrl(x3197) { LoadBanks(List(x3106_d0_b0), List(b2211, b2217)).name("x3188").srcCtx("PensieveStream.scala:164:57") } // LUTLoad(x3106,List(b2211, b2217),x3187)
    val x3189 = withCtrl(x3197) { x3188 } // FltConvert(x3188,_24,_8) //TODO
    val x3190 = withCtrl(x3197) { LoadBanks(List(x3098_d0_b0), List(b2217)).name("x3190").srcCtx("PensieveStream.scala:164:81") } // ParRegFileLoad(x3098,List(List(b2217)),List(Const(true)))
    val x3191 = withCtrl(x3197) { x3190 } // VectorApply(x3190,0)
    val x3192 = withCtrl(x3197) { OpDef(op=FltMul, inputs=List(x3189, x3191)).name("x3192").srcCtx("PensieveStream.scala:164:73") } // FltMul(x3189,x3191)
    val x3193 = withCtrl(x3197) { ReadMem(x3184_d1).name("x3193").srcCtx("PensieveStream.scala:165:42") } // RegRead(x3184)
    val x3194 = withCtrl(x3197) { OpDef(op=FixEql, inputs=List(b2217, Const(0))).name("x3194").srcCtx("PensieveStream.scala:165:42") } // FixEql(b2217,Const(0))
    val x3195 = withCtrl(x3197) { ReduceAccumOp(op=FltAdd, input=x3192, accum=x3193).name("x3195").srcCtx("PensieveStream.scala:165:45") } // FltAdd(x3192,x3193)
    val x3196_x3184_d0 = withCtrl(x3197) { WriteMem(x3184_d0, x3195).name("x3196_x3184_d0").srcCtx("PensieveStream.scala:165:42") } // RegWrite(x3184,x3195,b2212)
    antiDepsOf(x3196_x3184_d0)=List(x3193)
    val x3196_x3184_d1 = withCtrl(x3197) { WriteMem(x3184_d1, x3195).name("x3196_x3184_d1").srcCtx("PensieveStream.scala:165:42") } // RegWrite(x3184,x3195,b2212)
    antiDepsOf(x3196_x3184_d1)=List(x3193)
    val x3205 = withCtrl(x3206) { UnitController(style=SeqPipe, level=InnerControl).name("x3205").srcCtx("PensieveStream.scala:159:64") } // UnitPipe(List(b2212),Block(Const(())))
    val x3198 = withCtrl(x3205) { ReadMem(x3184_d0).name("x3198").srcCtx("PensieveStream.scala:165:42") } // RegRead(x3184)
    val x3199_x3183 = withCtrl(x3205) { WriteMem(x3183, x3198).name("x3199_x3183").srcCtx("PensieveStream.scala:163:43") } // RegWrite(x3183,x3198,b2212)
    val x3200 = withCtrl(x3205) { ReadMem(x3183).name("x3200").srcCtx("PensieveStream.scala:167:57") } // RegRead(x3183)
    antiDepsOf(x3200)=List(x3199_x3183)
    val x3201 = withCtrl(x3205) { LoadBanks(List(x3107_d0_b0), List(b2211)).name("x3201").srcCtx("PensieveStream.scala:167:69") } // LUTLoad(x3107,List(b2211),b2212)
    val x3202 = withCtrl(x3205) { OpDef(op=FltAdd, inputs=List(x3200, x3201)).name("x3202").srcCtx("PensieveStream.scala:167:59") } // FltAdd(x3200,x3201)
    val x3203 = withCtrl(x3205) { OpDef(op=FltMax, inputs=List(x3202, Const(0.0))).name("x3203").srcCtx("PensieveStream.scala:167:56") } // Max(x3202,Const(0.0))
    val x3204 = withCtrl(x3205) { StoreBanks(List(List(x3099_d0_b0)), List(b2211), x3203).name("x3204").srcCtx("PensieveStream.scala:167:51") } // RegFileStore(x3099,List(b2211),x3203,b2212)
    val x3284 = withCtrl(x3285) { UnitController(style=SeqPipe, level=OuterControl).name("x3284").srcCtx("PensieveStream.scala:174:30") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3208_d0_b0 = withCtrl(x3284) { RegFile(size=2, inits=None).name("x3208_d0_b0").srcCtx("PensieveStream.scala:176:64:numerators") } // x3208 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x3208_d0_b0) = false
    bufferDepthOf(x3208_d0_b0) = 1
    staticDimsOf(x3208_d0_b0) = List(2)
    val x3208_d1_b0 = withCtrl(x3284) { RegFile(size=2, inits=None).name("x3208_d1_b0").srcCtx("PensieveStream.scala:176:64:numerators") } // x3208 = RegFileNew(ArrayBuffer(Const(2)),None) banking:Strided(banks=1, stride=1)
    isAccum(x3208_d1_b0) = false
    bufferDepthOf(x3208_d1_b0) = 1
    staticDimsOf(x3208_d1_b0) = List(2)
    val x3209 = withCtrl(x3284) { Reg(init=Some(0.0)).name("x3209").srcCtx("PensieveStream.scala:177:61:denominator") } // x3209 = RegNew(Const(0.0))
    isAccum(x3209) = false
    bufferDepthOf(x3209) = 1
    val x3210 = withCtrl(x3284) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3210").srcCtx("PensieveStream.scala:179:43") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3211 = withCtrl(x3284) { CounterChain(List(x3210)).name("x3211").srcCtx("PensieveStream.scala:179:61") } // CounterChainNew(List(x3210))
    val x3252 = withCtrl(x3284) { LoopController(style=MetaPipe, level=OuterControl, cchain=x3211).name("x3252").srcCtx("PensieveStream.scala:179:61") } // UnrolledForeach(List(Const(true)),x3211,Block(Const(())),List(List(b2244)),List(List(b2245)))
    val b2244 = withCtrl(x3252) { CounterIter(x3210, Some(0)).name("b2244") } // b2244
    val b2245 = withCtrl(x3252) { Const(true).name("b2245") } // b2245
    val x3212 = withCtrl(x3252) { Reg(init=Some(0.0)).name("x3212").srcCtx("PensieveStream.scala:181:53:wx") } // x3212 = RegNew(Const(0.0))
    isAccum(x3212) = false
    bufferDepthOf(x3212) = 1
    val x3213_d0 = withCtrl(x3252) { Reg(init=Some(0.0)).name("x3213_d0").srcCtx("PensieveStream.scala:183:64") } // x3213 = RegNew(Const(0.0))
    isAccum(x3213_d0) = false
    bufferDepthOf(x3213_d0) = 2
    val x3213_d1 = withCtrl(x3252) { Reg(init=Some(0.0)).name("x3213_d1").srcCtx("PensieveStream.scala:183:64") } // x3213 = RegNew(Const(0.0))
    isAccum(x3213_d1) = true
    bufferDepthOf(x3213_d1) = 1
    val x3214 = withCtrl(x3252) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3214").srcCtx("PensieveStream.scala:183:94") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3215 = withCtrl(x3252) { CounterChain(List(x3214)).name("x3215").srcCtx("PensieveStream.scala:185:42") } // CounterChainNew(List(x3214))
    val x3225 = withCtrl(x3252) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3215).name("x3225").srcCtx("PensieveStream.scala:185:42") } // UnrolledReduce(List(b2245),x3215,x3213,Block((x3213) => Const(())),List(List(b2250)),List(List(b2251)))
    val b2250 = withCtrl(x3225) { CounterIter(x3214, None).name("b2250") } // b2250
    val b2251 = withCtrl(x3225) { Const(true).name("b2251") } // b2251
    val x3216 = withCtrl(x3225) { OpDef(op=BitAnd, inputs=List(b2251, b2245)).name("x3216").srcCtx("UnrollingBase.scala:28:66") } // And(b2251,b2245)
    val x3217 = withCtrl(x3225) { LoadBanks(List(x3108_d0_b0), List(b2244, b2250)).name("x3217").srcCtx("PensieveStream.scala:184:60") } // LUTLoad(x3108,List(b2244, b2250),x3216)
    val x3218 = withCtrl(x3225) { LoadBanks(List(x3099_d0_b0), List(b2250)).name("x3218").srcCtx("PensieveStream.scala:184:75") } // ParRegFileLoad(x3099,List(List(b2250)),List(Const(true)))
    val x3219 = withCtrl(x3225) { x3218 } // VectorApply(x3218,0)
    val x3220 = withCtrl(x3225) { OpDef(op=FltMul, inputs=List(x3217, x3219)).name("x3220").srcCtx("PensieveStream.scala:184:67") } // FltMul(x3217,x3219)
    val x3221 = withCtrl(x3225) { ReadMem(x3213_d1).name("x3221").srcCtx("PensieveStream.scala:185:42") } // RegRead(x3213)
    val x3222 = withCtrl(x3225) { OpDef(op=FixEql, inputs=List(b2250, Const(0))).name("x3222").srcCtx("PensieveStream.scala:185:42") } // FixEql(b2250,Const(0))
    val x3223 = withCtrl(x3225) { ReduceAccumOp(op=FltAdd, input=x3220, accum=x3221).name("x3223").srcCtx("PensieveStream.scala:185:45") } // FltAdd(x3220,x3221)
    val x3224_x3213_d0 = withCtrl(x3225) { WriteMem(x3213_d0, x3223).name("x3224_x3213_d0").srcCtx("PensieveStream.scala:185:42") } // RegWrite(x3213,x3223,b2245)
    antiDepsOf(x3224_x3213_d0)=List(x3221)
    val x3224_x3213_d1 = withCtrl(x3225) { WriteMem(x3213_d1, x3223).name("x3224_x3213_d1").srcCtx("PensieveStream.scala:185:42") } // RegWrite(x3213,x3223,b2245)
    antiDepsOf(x3224_x3213_d1)=List(x3221)
    val x3251 = withCtrl(x3252) { UnitController(style=SeqPipe, level=InnerControl).name("x3251").srcCtx("PensieveStream.scala:179:61") } // UnitPipe(List(b2245),Block(Const(())))
    val x3226 = withCtrl(x3251) { ReadMem(x3213_d0).name("x3226").srcCtx("PensieveStream.scala:185:42") } // RegRead(x3213)
    val x3227_x3212 = withCtrl(x3251) { WriteMem(x3212, x3226).name("x3227_x3212").srcCtx("PensieveStream.scala:183:44") } // RegWrite(x3212,x3226,b2245)
    val x3228 = withCtrl(x3251) { ReadMem(x3212).name("x3228").srcCtx("PensieveStream.scala:187:68") } // RegRead(x3212)
    antiDepsOf(x3228)=List(x3227_x3212)
    val x3229 = withCtrl(x3251) { LoadBanks(List(x3109_d0_b0), List(b2244)).name("x3229").srcCtx("PensieveStream.scala:187:84") } // LUTLoad(x3109,List(b2244),b2245)
    val x3230 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(x3228, x3229)).name("x3230").srcCtx("PensieveStream.scala:187:71") } // FltAdd(x3228,x3229)
    val x3231 = withCtrl(x3251) { OpDef(op=FltLt, inputs=List(x3230, Const(-3.5))).name("x3231").srcCtx("PensieveStream.scala:187:67") } // FltLt(x3230,Const(-3.50))
    val x3232 = withCtrl(x3251) { OpDef(op=FltLt, inputs=List(x3230, Const(-1.2))).name("x3232").srcCtx("PensieveStream.scala:187:67") } // FltLt(x3230,Const(-1.2000000476837158203125))
    val x3233 = withCtrl(x3251) { OpDef(op=FltMul, inputs=List(x3230, Const(0.1))).name("x3233").srcCtx("PensieveStream.scala:187:67") } // FltMul(x3230,Const(0.100000001490116119384765625))
    val x3234 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(x3233, Const(0.35))).name("x3234").srcCtx("PensieveStream.scala:187:67") } // FltAdd(x3233,Const(0.3499999940395355224609375))
    val x3235 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(Const(1.0), x3230)).name("x3235").srcCtx("PensieveStream.scala:187:67") } // FltAdd(Const(1),x3230)
    val x3236 = withCtrl(x3251) { OpDef(op=FltMul, inputs=List(x3230, x3230)).name("x3236").srcCtx("PensieveStream.scala:187:67") } // FltMul(x3230,x3230)
    val x3237 = withCtrl(x3251) { OpDef(op=FltDiv, inputs=List(x3236, Const(2.0))).name("x3237").srcCtx("PensieveStream.scala:187:67") } // FltDiv(x3236,Const(2))
    val x3238 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(x3235, x3237)).name("x3238").srcCtx("PensieveStream.scala:187:67") } // FltAdd(x3235,x3237)
    val x3239 = withCtrl(x3251) { OpDef(op=FltMul, inputs=List(x3236, x3230)).name("x3239").srcCtx("PensieveStream.scala:187:67") } // FltMul(x3236,x3230)
    val x3240 = withCtrl(x3251) { OpDef(op=FltDiv, inputs=List(x3239, Const(6.0))).name("x3240").srcCtx("PensieveStream.scala:187:67") } // FltDiv(x3239,Const(6.0))
    val x3241 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(x3238, x3240)).name("x3241").srcCtx("PensieveStream.scala:187:67") } // FltAdd(x3238,x3240)
    val x3242 = withCtrl(x3251) { OpDef(op=FltMul, inputs=List(x3239, x3230)).name("x3242").srcCtx("PensieveStream.scala:187:67") } // FltMul(x3239,x3230)
    val x3243 = withCtrl(x3251) { OpDef(op=FltDiv, inputs=List(x3242, Const(24.0))).name("x3243").srcCtx("PensieveStream.scala:187:67") } // FltDiv(x3242,Const(24.0))
    val x3244 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(x3241, x3243)).name("x3244").srcCtx("PensieveStream.scala:187:67") } // FltAdd(x3241,x3243)
    val x3245 = withCtrl(x3251) { OpDef(op=FltMul, inputs=List(x3242, x3230)).name("x3245").srcCtx("PensieveStream.scala:187:67") } // FltMul(x3242,x3230)
    val x3246 = withCtrl(x3251) { OpDef(op=FltDiv, inputs=List(x3245, Const(120.0))).name("x3246").srcCtx("PensieveStream.scala:187:67") } // FltDiv(x3245,Const(120.000))
    val x3247 = withCtrl(x3251) { OpDef(op=FltAdd, inputs=List(x3244, x3246)).name("x3247").srcCtx("PensieveStream.scala:187:67") } // FltAdd(x3244,x3246)
    val x3248 = withCtrl(x3251) { OpDef(op=MuxOp, inputs=List(x3232, x3234, x3247)).name("x3248").srcCtx("PensieveStream.scala:187:67") } // Mux(x3232,x3234,x3247)
    val x3249 = withCtrl(x3251) { OpDef(op=MuxOp, inputs=List(x3231, Const(0.0), x3248)).name("x3249").srcCtx("PensieveStream.scala:187:67") } // Mux(x3231,Const(0.0),x3248)
    val x3250 = withCtrl(x3251) { StoreBanks(List(List(x3208_d0_b0), List(x3208_d1_b0)), List(b2244), x3249).name("x3250").srcCtx("PensieveStream.scala:187:55") } // RegFileStore(x3208,List(b2244),x3249,b2245)
    val x3266 = withCtrl(x3284) { UnitController(style=SeqPipe, level=OuterControl).name("x3266").srcCtx("PensieveStream.scala:192:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3253_d0 = withCtrl(x3266) { Reg(init=Some(0.0)).name("x3253_d0").srcCtx("PensieveStream.scala:193:73") } // x3253 = RegNew(Const(0.0))
    isAccum(x3253_d0) = false
    bufferDepthOf(x3253_d0) = 1
    val x3253_d1 = withCtrl(x3266) { Reg(init=Some(0.0)).name("x3253_d1").srcCtx("PensieveStream.scala:193:73") } // x3253 = RegNew(Const(0.0))
    isAccum(x3253_d1) = true
    bufferDepthOf(x3253_d1) = 1
    val x3254 = withCtrl(x3266) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3254").srcCtx("PensieveStream.scala:193:100") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3255 = withCtrl(x3266) { CounterChain(List(x3254)).name("x3255").srcCtx("PensieveStream.scala:195:42") } // CounterChainNew(List(x3254))
    val x3262 = withCtrl(x3266) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3255).name("x3262").srcCtx("PensieveStream.scala:195:42") } // UnrolledReduce(List(Const(true)),x3255,x3253,Block((x3253) => Const(())),List(List(b2292)),List(List(b2293)))
    val b2292 = withCtrl(x3262) { CounterIter(x3254, None).name("b2292") } // b2292
    val b2293 = withCtrl(x3262) { Const(true).name("b2293") } // b2293
    val x3256 = withCtrl(x3262) { LoadBanks(List(x3208_d1_b0), List(b2292)).name("x3256").srcCtx("PensieveStream.scala:194:59") } // ParRegFileLoad(x3208,List(List(b2292)),List(Const(true)))
    val x3257 = withCtrl(x3262) { x3256 } // VectorApply(x3256,0)
    val x3258 = withCtrl(x3262) { ReadMem(x3253_d1).name("x3258").srcCtx("PensieveStream.scala:195:42") } // RegRead(x3253)
    val x3259 = withCtrl(x3262) { OpDef(op=FixEql, inputs=List(b2292, Const(0))).name("x3259").srcCtx("PensieveStream.scala:195:42") } // FixEql(b2292,Const(0))
    val x3260 = withCtrl(x3262) { ReduceAccumOp(op=FltAdd, input=x3257, accum=x3258).name("x3260").srcCtx("PensieveStream.scala:195:45") } // FltAdd(x3257,x3258)
    val x3261_x3253_d0 = withCtrl(x3262) { WriteMem(x3253_d0, x3260).name("x3261_x3253_d0").srcCtx("PensieveStream.scala:195:42") } // RegWrite(x3253,x3260,Const(true))
    antiDepsOf(x3261_x3253_d0)=List(x3258)
    val x3261_x3253_d1 = withCtrl(x3262) { WriteMem(x3253_d1, x3260).name("x3261_x3253_d1").srcCtx("PensieveStream.scala:195:42") } // RegWrite(x3253,x3260,Const(true))
    antiDepsOf(x3261_x3253_d1)=List(x3258)
    val x3265 = withCtrl(x3266) { UnitController(style=SeqPipe, level=InnerControl).name("x3265").srcCtx("PensieveStream.scala:192:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3263 = withCtrl(x3265) { ReadMem(x3253_d0).name("x3263").srcCtx("PensieveStream.scala:195:42") } // RegRead(x3253)
    val x3264_x3209 = withCtrl(x3265) { WriteMem(x3209, x3263).name("x3264_x3209").srcCtx("PensieveStream.scala:193:53") } // RegWrite(x3209,x3263,Const(true))
    val x3283 = withCtrl(x3284) { UnitController(style=SeqPipe, level=OuterControl).name("x3283").srcCtx("PensieveStream.scala:198:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3267 = withCtrl(x3283) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x3267").srcCtx("PensieveStream.scala:200:51") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x3268 = withCtrl(x3283) { CounterChain(List(x3267)).name("x3268").srcCtx("PensieveStream.scala:200:69") } // CounterChainNew(List(x3267))
    val x3279 = withCtrl(x3283) { LoopController(style=InnerPipe, level=InnerControl, cchain=x3268).name("x3279").srcCtx("PensieveStream.scala:200:69") } // UnrolledForeach(List(Const(true)),x3268,Block(Const(())),List(List(b2307)),List(List(b2308)))
    val b2307 = withCtrl(x3279) { CounterIter(x3267, None).name("b2307") } // b2307
    val b2308 = withCtrl(x3279) { Const(true).name("b2308") } // b2308
    val x3269 = withCtrl(x3279) { LoadBanks(List(x3208_d0_b0), List(b2307)).name("x3269").srcCtx("PensieveStream.scala:201:71") } // ParRegFileLoad(x3208,List(List(b2307)),List(Const(true)))
    val x3270 = withCtrl(x3279) { x3269 } // VectorApply(x3269,0)
    val x3271 = withCtrl(x3279) { ReadMem(x3209).name("x3271").srcCtx("PensieveStream.scala:201:77") } // RegRead(x3209)
    val x3272 = withCtrl(x3279) { OpDef(op=FltDiv, inputs=List(x3270, x3271)).name("x3272").srcCtx("PensieveStream.scala:201:75") } // FltDiv(x3270,x3271)
    val x3273 = withCtrl(x3279) { StoreBanks(List(List(x3100_d0_b0), List(x3100_d1_b0)), List(b2307), x3272).name("x3273").srcCtx("PensieveStream.scala:201:59") } // ParRegFileStore(x3100,List(List(b2307)),List(x3272),List(Const(true)))
    val x3274 = withCtrl(x3279) { LoadBanks(List(x3100_d1_b0), List(b2307)).name("x3274").srcCtx("PensieveStream.scala:202:61") } // ParRegFileLoad(x3100,List(List(b2307)),List(Const(true)))
    antiDepsOf(x3274)=List(x3273)
    val x3275 = withCtrl(x3279) { x3274 } // VectorApply(x3274,0)
    val x3282 = withCtrl(x3283) { UnitController(style=SeqPipe, level=InnerControl).name("x3282").srcCtx("PensieveStream.scala:198:38") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x3280 = withCtrl(x3282) { LoadBanks(List(x3100_d0_b0), List(Const(0))).name("x3280").srcCtx("PensieveStream.scala:206:61") } // RegFileLoad(x3100,List(Const(0)),Const(true))
    val x3281_x3281_x3095 = withCtrl(x3282) { WriteMem(x3095, x3280).name("x3281_x3281_x3095").srcCtx("PensieveStream.scala:206:52") } // StreamWrite(x3095,x3280,Const(true))
    
  }
}
