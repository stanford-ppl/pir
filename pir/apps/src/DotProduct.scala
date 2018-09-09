import pir._
import pir.node._
import arch._
import prism.enums._

object DotProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1057 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1024))).name("x1057").srcCtx("main.scala:170:33:din0") } // x1057 = DRAMNew(ArrayBuffer(Const(1024)),Const(0))
    val x1058 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1024))).name("x1058").srcCtx("main.scala:171:33:din1") } // x1058 = DRAMNew(ArrayBuffer(Const(1024)),Const(0))
    val x1059 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1024))).name("x1059").srcCtx("main.scala:172:33:dout") } // x1059 = DRAMNew(ArrayBuffer(Const(1024)),Const(0))
    val x1131 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x1131").srcCtx("main.scala:177:11") } // Hwblock(Block(Const(())),false)
    val x1062_d0_b0 = withCtrl(x1131) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x1062_d0_b0").srcCtx("main.scala:178:39:data0Mem") } // x1062 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x1062_d0_b0) = false
    bufferDepthOf(x1062_d0_b0) = 1
    staticDimsOf(x1062_d0_b0) = List(1024)
    val x1063_d0_b0 = withCtrl(x1131) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x1063_d0_b0").srcCtx("main.scala:179:39:data1Mem") } // x1063 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x1063_d0_b0) = false
    bufferDepthOf(x1063_d0_b0) = 1
    staticDimsOf(x1063_d0_b0) = List(1024)
    val x1064_d0_b0 = withCtrl(x1131) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x1064_d0_b0").srcCtx("main.scala:180:38:doutMem") } // x1064 = SRAMNew(ArrayBuffer(Const(1024)))
    isAccum(x1064_d0_b0) = false
    bufferDepthOf(x1064_d0_b0) = 1
    staticDimsOf(x1064_d0_b0) = List(1024)
    val x1082 = withCtrl(x1131) { UnitController(style=StreamPipe, level=OuterControl).name("x1082").srcCtx("main.scala:182:16") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1142 = withCtrl(x1082) { StreamOut(field="offset").name("b1142").srcCtx("main.scala:182:16") } // x1065 = StreamOutNew(BurstCmdBus)
    isAccum(b1142) = false
    bufferDepthOf(b1142) = 1
    val b1143 = withCtrl(x1082) { StreamOut(field="size").name("b1143").srcCtx("main.scala:182:16") } // x1065 = StreamOutNew(BurstCmdBus)
    isAccum(b1143) = false
    bufferDepthOf(b1143) = 1
    val x1066 = withCtrl(x1082) { StreamIn(field="data").name("x1066").srcCtx("main.scala:182:16") } // x1066 = StreamInNew(BurstDataBus())
    isAccum(x1066) = false
    bufferDepthOf(x1066) = 1
    val x1074 = withCtrl(x1082) { UnitController(style=SeqPipe, level=InnerControl).name("x1074").srcCtx("main.scala:182:16") } // UnitPipe(List(Const(true)),Block(x1073))
    val x1067 = withCtrl(x1074) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1068 = withCtrl(x1074) { OpDef(op=FixSla, inputs=List(x1067, Const(2))).name("x1068").srcCtx("main.scala:182:16") } // FixLsh(x1067,Const(2))
    val x1069 = withCtrl(x1074) { x1068 } // FixConvert(x1068,TRUE,_64,_0)
    val x1070 = withCtrl(x1074) { DramAddress(x1057).name("x1070").srcCtx("main.scala:182:16") } // GetDRAMAddress(x1057)
    val x1072_x1071 = withCtrl(x1074) { OpDef(op=FixAdd, inputs=List(x1069, x1070)).name("x1072_x1071").srcCtx("main.scala:182:16") } // FixAdd(x1069,x1070)
    // x1072 = SimpleStruct(ArrayBuffer((offset,x1071), (size,Const(4096)), (isLoad,Const(true))))
    val x1073_b1144_b1142 = withCtrl(x1074) { WriteMem(b1142, x1072_x1071).name("x1073_b1144_b1142").srcCtx("main.scala:182:16") } // StreamWrite(x1065,x1072,Const(true))
    val x1073_b1145_b1143 = withCtrl(x1074) { WriteMem(b1143, Const(4096)).name("x1073_b1145_b1143").srcCtx("main.scala:182:16") } // StreamWrite(x1065,x1072,Const(true))
    val x1075 = withCtrl(x1082) { FringeDenseLoad(dram=List(x1057), cmdStream=List(b1142, b1143), dataStream=List(x1066)).name("x1075").srcCtx("main.scala:182:16") } // FringeDenseLoad(x1057,x1065,x1066)
    val x1076 = withCtrl(x1082) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x1076").srcCtx("main.scala:182:16") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x1077 = withCtrl(x1082) { CounterChain(List(x1076)).name("x1077").srcCtx("main.scala:182:16") } // CounterChainNew(List(x1076))
    val x1081 = withCtrl(x1082) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1077).name("x1081").srcCtx("main.scala:182:16") } // UnrolledForeach(List(Const(true)),x1077,Block(Const(())),List(List(b625)),List(List(b626)))
    val b625 = withCtrl(x1081) { CounterIter(x1076, None).name("b625") } // b625
    val b626 = withCtrl(x1081) { Const(true).name("b626") } // b626
    val x1078_x1078 = withCtrl(x1081) { ReadMem(x1066).name("x1078_x1078").srcCtx("main.scala:182:16") } // ParStreamRead(x1066,List(b626))
    val x1079_x1079 = withCtrl(x1081) { x1078_x1078 } // VectorApply(x1078,0)
    val x1080 = withCtrl(x1081) { StoreBanks(List(List(x1062_d0_b0)), List(b625), x1079_x1079).name("x1080").srcCtx("main.scala:182:16") } // ParSRAMStore(x1062,List(List(b625)),List(x1079),List(b626))
    val x1100 = withCtrl(x1131) { UnitController(style=StreamPipe, level=OuterControl).name("x1100").srcCtx("main.scala:183:16") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1146 = withCtrl(x1100) { StreamOut(field="offset").name("b1146").srcCtx("main.scala:183:16") } // x1083 = StreamOutNew(BurstCmdBus)
    isAccum(b1146) = false
    bufferDepthOf(b1146) = 1
    val b1147 = withCtrl(x1100) { StreamOut(field="size").name("b1147").srcCtx("main.scala:183:16") } // x1083 = StreamOutNew(BurstCmdBus)
    isAccum(b1147) = false
    bufferDepthOf(b1147) = 1
    val x1084 = withCtrl(x1100) { StreamIn(field="data").name("x1084").srcCtx("main.scala:183:16") } // x1084 = StreamInNew(BurstDataBus())
    isAccum(x1084) = false
    bufferDepthOf(x1084) = 1
    val x1092 = withCtrl(x1100) { UnitController(style=SeqPipe, level=InnerControl).name("x1092").srcCtx("main.scala:183:16") } // UnitPipe(List(Const(true)),Block(x1091))
    val x1085 = withCtrl(x1092) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1086 = withCtrl(x1092) { OpDef(op=FixSla, inputs=List(x1085, Const(2))).name("x1086").srcCtx("main.scala:183:16") } // FixLsh(x1085,Const(2))
    val x1087 = withCtrl(x1092) { x1086 } // FixConvert(x1086,TRUE,_64,_0)
    val x1088 = withCtrl(x1092) { DramAddress(x1058).name("x1088").srcCtx("main.scala:183:16") } // GetDRAMAddress(x1058)
    val x1090_x1089 = withCtrl(x1092) { OpDef(op=FixAdd, inputs=List(x1087, x1088)).name("x1090_x1089").srcCtx("main.scala:183:16") } // FixAdd(x1087,x1088)
    // x1090 = SimpleStruct(ArrayBuffer((offset,x1089), (size,Const(4096)), (isLoad,Const(true))))
    val x1091_b1148_b1146 = withCtrl(x1092) { WriteMem(b1146, x1090_x1089).name("x1091_b1148_b1146").srcCtx("main.scala:183:16") } // StreamWrite(x1083,x1090,Const(true))
    val x1091_b1149_b1147 = withCtrl(x1092) { WriteMem(b1147, Const(4096)).name("x1091_b1149_b1147").srcCtx("main.scala:183:16") } // StreamWrite(x1083,x1090,Const(true))
    val x1093 = withCtrl(x1100) { FringeDenseLoad(dram=List(x1058), cmdStream=List(b1146, b1147), dataStream=List(x1084)).name("x1093").srcCtx("main.scala:183:16") } // FringeDenseLoad(x1058,x1083,x1084)
    val x1094 = withCtrl(x1100) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x1094").srcCtx("main.scala:183:16") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x1095 = withCtrl(x1100) { CounterChain(List(x1094)).name("x1095").srcCtx("main.scala:183:16") } // CounterChainNew(List(x1094))
    val x1099 = withCtrl(x1100) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1095).name("x1099").srcCtx("main.scala:183:16") } // UnrolledForeach(List(Const(true)),x1095,Block(Const(())),List(List(b645)),List(List(b646)))
    val b645 = withCtrl(x1099) { CounterIter(x1094, None).name("b645") } // b645
    val b646 = withCtrl(x1099) { Const(true).name("b646") } // b646
    val x1096_x1096 = withCtrl(x1099) { ReadMem(x1084).name("x1096_x1096").srcCtx("main.scala:183:16") } // ParStreamRead(x1084,List(b646))
    val x1097_x1097 = withCtrl(x1099) { x1096_x1096 } // VectorApply(x1096,0)
    val x1098 = withCtrl(x1099) { StoreBanks(List(List(x1063_d0_b0)), List(b645), x1097_x1097).name("x1098").srcCtx("main.scala:183:16") } // ParSRAMStore(x1063,List(List(b645)),List(x1097),List(b646))
    val x1101 = withCtrl(x1131) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x1101").srcCtx("main.scala:184:30") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x1102 = withCtrl(x1131) { CounterChain(List(x1101)).name("x1102").srcCtx("main.scala:184:44") } // CounterChainNew(List(x1101))
    val x1108 = withCtrl(x1131) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1102).name("x1108").srcCtx("main.scala:184:44") } // UnrolledForeach(List(Const(true)),x1102,Block(Const(())),List(List(b654)),List(List(b655)))
    val b654 = withCtrl(x1108) { CounterIter(x1101, None).name("b654") } // b654
    val b655 = withCtrl(x1108) { Const(true).name("b655") } // b655
    val x1103 = withCtrl(x1108) { LoadBanks(List(x1062_d0_b0), List(b654)).name("x1103").srcCtx("main.scala:185:33") } // ParSRAMLoad(x1062,List(List(b654)),List(b655))
    val x1104 = withCtrl(x1108) { x1103 } // VectorApply(x1103,0)
    val x1105 = withCtrl(x1108) { LoadBanks(List(x1063_d0_b0), List(Const(1))).name("x1105").srcCtx("main.scala:185:47") } // SRAMLoad(x1063,ArrayBuffer(Const(1024)),List(Const(1)),Const(0),b655)
    val x1106 = withCtrl(x1108) { OpDef(op=FixMul, inputs=List(x1104, x1105)).name("x1106").srcCtx("main.scala:185:37:result") } // FixMul(x1104,x1105)
    val x1107 = withCtrl(x1108) { StoreBanks(List(List(x1064_d0_b0)), List(b654), x1106).name("x1107").srcCtx("main.scala:186:20") } // ParSRAMStore(x1064,List(List(b654)),List(x1106),List(b655))
    val x1130 = withCtrl(x1131) { UnitController(style=StreamPipe, level=OuterControl).name("x1130").srcCtx("main.scala:189:35") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1150 = withCtrl(x1130) { StreamOut(field="offset").name("b1150").srcCtx("main.scala:189:35") } // x1109 = StreamOutNew(BurstCmdBus)
    isAccum(b1150) = false
    bufferDepthOf(b1150) = 1
    val b1151 = withCtrl(x1130) { StreamOut(field="size").name("b1151").srcCtx("main.scala:189:35") } // x1109 = StreamOutNew(BurstCmdBus)
    isAccum(b1151) = false
    bufferDepthOf(b1151) = 1
    val x1110 = withCtrl(x1130) { StreamOut(field="data").name("x1110").srcCtx("main.scala:189:35") } // x1110 = StreamOutNew(BurstFullDataBus())
    isAccum(x1110) = false
    bufferDepthOf(x1110) = 1
    val x1111 = withCtrl(x1130) { StreamIn(field="ack").name("x1111").srcCtx("main.scala:189:35") } // x1111 = StreamInNew(BurstAckBus)
    isAccum(x1111) = false
    bufferDepthOf(x1111) = 1
    val x1119 = withCtrl(x1130) { UnitController(style=SeqPipe, level=InnerControl).name("x1119").srcCtx("main.scala:189:35") } // UnitPipe(List(Const(true)),Block(x1118))
    val x1112 = withCtrl(x1119) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1113 = withCtrl(x1119) { OpDef(op=FixSla, inputs=List(x1112, Const(2))).name("x1113").srcCtx("main.scala:189:35") } // FixLsh(x1112,Const(2))
    val x1114 = withCtrl(x1119) { x1113 } // FixConvert(x1113,TRUE,_64,_0)
    val x1115 = withCtrl(x1119) { DramAddress(x1059).name("x1115").srcCtx("main.scala:189:35") } // GetDRAMAddress(x1059)
    val x1117_x1116 = withCtrl(x1119) { OpDef(op=FixAdd, inputs=List(x1114, x1115)).name("x1117_x1116").srcCtx("main.scala:189:35") } // FixAdd(x1114,x1115)
    // x1117 = SimpleStruct(ArrayBuffer((offset,x1116), (size,Const(4096)), (isLoad,Const(false))))
    val x1118_b1152_b1150 = withCtrl(x1119) { WriteMem(b1150, x1117_x1116).name("x1118_b1152_b1150").srcCtx("main.scala:189:35") } // StreamWrite(x1109,x1117,Const(true))
    val x1118_b1153_b1151 = withCtrl(x1119) { WriteMem(b1151, Const(4096)).name("x1118_b1153_b1151").srcCtx("main.scala:189:35") } // StreamWrite(x1109,x1117,Const(true))
    val x1120 = withCtrl(x1130) { Counter(min=Const(0), max=Const(1024), step=Const(1), par=16).name("x1120").srcCtx("main.scala:189:35") } // CounterNew(Const(0),Const(1024),Const(1),Const(16))
    val x1121 = withCtrl(x1130) { CounterChain(List(x1120)).name("x1121").srcCtx("main.scala:189:35") } // CounterChainNew(List(x1120))
    val x1126 = withCtrl(x1130) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1121).name("x1126").srcCtx("main.scala:189:35") } // UnrolledForeach(List(Const(true)),x1121,Block(Const(())),List(List(b675)),List(List(b676)))
    val b675 = withCtrl(x1126) { CounterIter(x1120, None).name("b675") } // b675
    val b676 = withCtrl(x1126) { Const(true).name("b676") } // b676
    val x1122 = withCtrl(x1126) { LoadBanks(List(x1064_d0_b0), List(b675)).name("x1122").srcCtx("main.scala:189:35") } // ParSRAMLoad(x1064,List(List(b675)),List(b676))
    val x1124_x1123 = withCtrl(x1126) { x1122 } // VectorApply(x1122,0)
    // x1124 = SimpleStruct(ArrayBuffer((_1,x1123), (_2,Const(true))))
    val x1125_x1125_x1110 = withCtrl(x1126) { WriteMem(x1110, x1124_x1123).name("x1125_x1125_x1110").srcCtx("main.scala:189:35") } // ParStreamWrite(x1110,List(x1124),List(b676))
    val x1127 = withCtrl(x1130) { FringeDenseStore(dram=List(x1059), cmdStream=List(b1150, b1151), dataStream=List(x1110), ackStream=List(x1111)).name("x1127").srcCtx("main.scala:189:35") } // FringeDenseStore(x1059,x1109,x1110,x1111)
    val x1129 = withCtrl(x1130) { UnitController(style=SeqPipe, level=InnerControl).name("x1129").srcCtx("main.scala:189:35") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x1128_x1128 = withCtrl(x1129) { ReadMem(x1111).name("x1128_x1128").srcCtx("main.scala:189:35") } // StreamRead(x1111,Const(true))
    
  }
}
