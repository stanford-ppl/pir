import pir._
import pir.node._
import arch._
import prism.enums._

object DoubleLoad extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1049 = withCtrl(design.top.topController) { ArgIn(init=0).name("x1049").srcCtx("DoubleLoad.scala:16:22:sizeA") } // ArgInNew(Const(0))
    isAccum(x1049) = false
    bufferDepthOf(x1049) = 1
    boundOf(x1049) = 1024
    val x1051 = withCtrl(design.top.topController) { ReadMem(x1049).name("x1051").srcCtx("DoubleLoad.scala:19:24") } // RegRead(x1049)
    val x1052 = withCtrl(design.top.topController) { DRAM(dims=List(x1051)).name("x1052").srcCtx("DoubleLoad.scala:19:23:vec1") } // x1052 = DRAMNew(ArrayBuffer(x1051),Const(0))
    val x1053 = withCtrl(design.top.topController) { ReadMem(x1049).name("x1053").srcCtx("DoubleLoad.scala:20:23") } // RegRead(x1049)
    val x1054 = withCtrl(design.top.topController) { DRAM(dims=List(x1053)).name("x1054").srcCtx("DoubleLoad.scala:20:22:out") } // x1054 = DRAMNew(ArrayBuffer(x1053),Const(0))
    val x1132 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x1132").srcCtx("DoubleLoad.scala:24:11") } // Hwblock(Block(Const(())),false)
    val x1056 = withCtrl(x1132) { ReadMem(x1049).name("x1056").srcCtx("DoubleLoad.scala:25:26") } // RegRead(x1049)
    val x1057 = withCtrl(x1132) { Counter(min=Const(0), max=x1056, step=Const(32), par=1).name("x1057").srcCtx("DoubleLoad.scala:25:38") } // CounterNew(Const(0),x1056,Const(32),Const(1))
    val x1058 = withCtrl(x1132) { CounterChain(List(x1057)).name("x1058").srcCtx("DoubleLoad.scala:25:45") } // CounterChainNew(List(x1057))
    val x1131 = withCtrl(x1132) { LoopController(style=SeqPipe, level=OuterControl, cchain=x1058).name("x1131").srcCtx("DoubleLoad.scala:25:45") } // UnrolledForeach(List(Const(true)),x1058,Block(Const(())),List(List(b611)),List(List(b612)))
    val b611 = withCtrl(x1131) { CounterIter(x1057, Some(0)).name("b611") } // b611
    val b612 = withCtrl(x1131) { Const(true).name("b612") } // b612
    val x1059_d0_b0 = withCtrl(x1131) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1059_d0_b0").srcCtx("DoubleLoad.scala:26:25:b1") } // x1059 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1059_d0_b0) = false
    bufferDepthOf(x1059_d0_b0) = 1
    staticDimsOf(x1059_d0_b0) = List(32)
    val x1059_d1_b0 = withCtrl(x1131) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1059_d1_b0").srcCtx("DoubleLoad.scala:26:25:b1") } // x1059 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1059_d1_b0) = false
    bufferDepthOf(x1059_d1_b0) = 1
    staticDimsOf(x1059_d1_b0) = List(32)
    val x1059_d2_b0 = withCtrl(x1131) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1059_d2_b0").srcCtx("DoubleLoad.scala:26:25:b1") } // x1059 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1059_d2_b0) = false
    bufferDepthOf(x1059_d2_b0) = 1
    staticDimsOf(x1059_d2_b0) = List(32)
    val x1060_d0_b0 = withCtrl(x1131) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1060_d0_b0").srcCtx("DoubleLoad.scala:27:25:b2") } // x1060 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1060_d0_b0) = false
    bufferDepthOf(x1060_d0_b0) = 1
    staticDimsOf(x1060_d0_b0) = List(32)
    val x1062 = withCtrl(x1131) { UnitController(style=SeqPipe, level=InnerControl).name("x1062").srcCtx("DoubleLoad.scala:25:45") } // UnitPipe(List(b612),Block(Const(())))
    val x1061 = withCtrl(x1062) { OpDef(op=FixAdd, inputs=List(b611, Const(32))).name("x1061").srcCtx("DoubleLoad.scala:28:26") } // FixAdd(b611,Const(32))
    val x1081 = withCtrl(x1131) { UnitController(style=StreamPipe, level=OuterControl).name("x1081").srcCtx("DoubleLoad.scala:28:12") } // UnitPipe(List(b612),Block(Const(())))
    val b1135 = withCtrl(x1081) { StreamOut(field="offset").name("b1135").srcCtx("DoubleLoad.scala:28:12") } // x1063 = StreamOutNew(BurstCmdBus)
    isAccum(b1135) = false
    bufferDepthOf(b1135) = 1
    val b1136 = withCtrl(x1081) { StreamOut(field="size").name("b1136").srcCtx("DoubleLoad.scala:28:12") } // x1063 = StreamOutNew(BurstCmdBus)
    isAccum(b1136) = false
    bufferDepthOf(b1136) = 1
    val x1064 = withCtrl(x1081) { StreamIn(field="data").name("x1064").srcCtx("DoubleLoad.scala:28:12") } // x1064 = StreamInNew(BurstDataBus())
    isAccum(x1064) = false
    bufferDepthOf(x1064) = 1
    val x1072 = withCtrl(x1081) { UnitController(style=SeqPipe, level=InnerControl).name("x1072").srcCtx("DoubleLoad.scala:28:12") } // UnitPipe(List(b612),Block(x1071))
    val x1065 = withCtrl(x1072) { b611 } // FixConvert(b611,TRUE,_32,_0) (Same Type. No op)
    val x1066 = withCtrl(x1072) { OpDef(op=FixSla, inputs=List(x1065, Const(2))).name("x1066").srcCtx("DoubleLoad.scala:28:12") } // FixLsh(x1065,Const(2))
    val x1067 = withCtrl(x1072) { x1066 } // FixConvert(x1066,TRUE,_64,_0)
    val x1068 = withCtrl(x1072) { DramAddress(x1052).name("x1068").srcCtx("DoubleLoad.scala:28:12") } // GetDRAMAddress(x1052)
    val x1070_x1069 = withCtrl(x1072) { OpDef(op=FixAdd, inputs=List(x1067, x1068)).name("x1070_x1069").srcCtx("DoubleLoad.scala:28:12") } // FixAdd(x1067,x1068)
    // x1070 = SimpleStruct(ArrayBuffer((offset,x1069), (size,Const(128)), (isLoad,Const(true))))
    val x1071_b1137_b1135 = withCtrl(x1072) { WriteMem(b1135, x1070_x1069).name("x1071_b1137_b1135").srcCtx("DoubleLoad.scala:28:12") } // StreamWrite(x1063,x1070,b612)
    val x1071_b1138_b1136 = withCtrl(x1072) { WriteMem(b1136, Const(128)).name("x1071_b1138_b1136").srcCtx("DoubleLoad.scala:28:12") } // StreamWrite(x1063,x1070,b612)
    val x1073 = withCtrl(x1081) { FringeDenseLoad(dram=List(x1052), cmdStream=List(b1135, b1136), dataStream=List(x1064)).name("x1073").srcCtx("DoubleLoad.scala:28:12") } // FringeDenseLoad(x1052,x1063,x1064)
    val x1074 = withCtrl(x1081) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1074").srcCtx("DoubleLoad.scala:28:12") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1075 = withCtrl(x1081) { CounterChain(List(x1074)).name("x1075").srcCtx("DoubleLoad.scala:28:12") } // CounterChainNew(List(x1074))
    val x1080 = withCtrl(x1081) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1075).name("x1080").srcCtx("DoubleLoad.scala:28:12") } // UnrolledForeach(List(b612),x1075,Block(Const(())),List(List(b630)),List(List(b631)))
    val b630 = withCtrl(x1080) { CounterIter(x1074, None).name("b630") } // b630
    val b631 = withCtrl(x1080) { Const(true).name("b631") } // b631
    val x1076 = withCtrl(x1080) { OpDef(op=BitAnd, inputs=List(b631, b612)).name("x1076").srcCtx("UnrollingBase.scala:28:66") } // And(b631,b612)
    val x1077_x1077 = withCtrl(x1080) { ReadMem(x1064).name("x1077_x1077").srcCtx("DoubleLoad.scala:28:12") } // ParStreamRead(x1064,List(x1076))
    val x1078_x1078 = withCtrl(x1080) { x1077_x1077 } // VectorApply(x1077,0)
    val x1079 = withCtrl(x1080) { StoreBanks(List(List(x1059_d0_b0), List(x1059_d1_b0), List(x1059_d2_b0)), List(b630), x1078_x1078).name("x1079").srcCtx("DoubleLoad.scala:28:12") } // ParSRAMStore(x1059,List(List(b630)),List(x1078),List(x1076))
    val x1082 = withCtrl(x1131) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x1082").srcCtx("DoubleLoad.scala:29:30") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x1083 = withCtrl(x1131) { CounterChain(List(x1082)).name("x1083").srcCtx("DoubleLoad.scala:29:36") } // CounterChainNew(List(x1082))
    val x1107 = withCtrl(x1131) { LoopController(style=SeqPipe, level=OuterControl, cchain=x1083).name("x1107").srcCtx("DoubleLoad.scala:29:36") } // UnrolledForeach(List(b612),x1083,Block(Const(())),List(List(b640)),List(List(b641)))
    val b640 = withCtrl(x1107) { CounterIter(x1082, Some(0)).name("b640") } // b640
    val b641 = withCtrl(x1107) { Const(true).name("b641") } // b641
    val x1084 = withCtrl(x1107) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1084").srcCtx("DoubleLoad.scala:30:33") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1085 = withCtrl(x1107) { CounterChain(List(x1084)).name("x1085").srcCtx("DoubleLoad.scala:30:40") } // CounterChainNew(List(x1084))
    val x1097 = withCtrl(x1107) { LoopController(style=SeqPipe, level=InnerControl, cchain=x1085).name("x1097").srcCtx("DoubleLoad.scala:30:40") } // UnrolledForeach(List(b641, b612),x1085,Block(Const(())),List(List(b644)),List(List(b645)))
    val b644 = withCtrl(x1097) { CounterIter(x1084, None).name("b644") } // b644
    val b645 = withCtrl(x1097) { Const(true).name("b645") } // b645
    val x1086 = withCtrl(x1097) { OpDef(op=BitAnd, inputs=List(b645, b641)).name("x1086").srcCtx("UnrollingBase.scala:28:66") } // And(b645,b641)
    val x1087 = withCtrl(x1097) { OpDef(op=BitAnd, inputs=List(x1086, b612)).name("x1087").srcCtx("UnrollingBase.scala:28:66") } // And(x1086,b612)
    val x1088 = withCtrl(x1097) { StoreBanks(List(List(x1059_d0_b0), List(x1059_d1_b0), List(x1059_d2_b0)), List(b644), Const(3)).name("x1088").srcCtx("DoubleLoad.scala:31:20") } // ParSRAMStore(x1059,List(List(b644)),List(Const(3)),List(x1087))
    val x1089 = withCtrl(x1097) { LoadBanks(List(x1059_d2_b0), List(b644)).name("x1089").srcCtx("DoubleLoad.scala:32:23") } // ParSRAMLoad(x1059,List(List(b644)),List(x1087))
    antiDepsOf(x1089)=List(x1088)
    val x1090 = withCtrl(x1097) { x1089 } // VectorApply(x1089,0)
    val x1091 = withCtrl(x1097) { OpDef(op=FixAdd, inputs=List(x1090, Const(1))).name("x1091").srcCtx("DoubleLoad.scala:32:28:x") } // FixAdd(x1090,Const(1))
    val x1092 = withCtrl(x1097) { OpDef(op=FixAdd, inputs=List(b644, Const(1))).name("x1092").srcCtx("DoubleLoad.scala:33:27") } // FixAdd(b644,Const(1))
    val x1093 = withCtrl(x1097) { LoadBanks(List(x1059_d1_b0), List(x1092)).name("x1093").srcCtx("DoubleLoad.scala:33:24") } // ParSRAMLoad(x1059,List(List(x1092)),List(x1087))
    antiDepsOf(x1093)=List(x1088)
    val x1094 = withCtrl(x1097) { x1093 } // VectorApply(x1093,0)
    val x1095 = withCtrl(x1097) { OpDef(op=FixAdd, inputs=List(x1094, x1091)).name("x1095").srcCtx("DoubleLoad.scala:33:31") } // FixAdd(x1094,x1091)
    val x1096 = withCtrl(x1097) { StoreBanks(List(List(x1060_d0_b0)), List(b644), x1095).name("x1096").srcCtx("DoubleLoad.scala:33:20") } // ParSRAMStore(x1060,List(List(b644)),List(x1095),List(x1087))
    val x1098 = withCtrl(x1107) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1098").srcCtx("DoubleLoad.scala:35:22") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1099 = withCtrl(x1107) { CounterChain(List(x1098)).name("x1099").srcCtx("DoubleLoad.scala:35:29") } // CounterChainNew(List(x1098))
    val x1106 = withCtrl(x1107) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1099).name("x1106").srcCtx("DoubleLoad.scala:35:29") } // UnrolledForeach(List(b641, b612),x1099,Block(Const(())),List(List(b660)),List(List(b661)))
    val b660 = withCtrl(x1106) { CounterIter(x1098, None).name("b660") } // b660
    val b661 = withCtrl(x1106) { Const(true).name("b661") } // b661
    val x1100 = withCtrl(x1106) { OpDef(op=BitAnd, inputs=List(b661, b641)).name("x1100").srcCtx("UnrollingBase.scala:28:66") } // And(b661,b641)
    val x1101 = withCtrl(x1106) { OpDef(op=BitAnd, inputs=List(x1100, b612)).name("x1101").srcCtx("UnrollingBase.scala:28:66") } // And(x1100,b612)
    val x1102 = withCtrl(x1106) { LoadBanks(List(x1059_d0_b0), List(b660)).name("x1102").srcCtx("DoubleLoad.scala:36:24") } // ParSRAMLoad(x1059,List(List(b660)),List(x1101))
    val x1103 = withCtrl(x1106) { x1102 } // VectorApply(x1102,0)
    val x1104 = withCtrl(x1106) { OpDef(op=FixAdd, inputs=List(x1103, Const(4))).name("x1104").srcCtx("DoubleLoad.scala:36:29") } // FixAdd(x1103,Const(4))
    val x1105 = withCtrl(x1106) { StoreBanks(List(List(x1060_d0_b0)), List(b660), x1104).name("x1105").srcCtx("DoubleLoad.scala:36:20") } // ParSRAMStore(x1060,List(List(b660)),List(x1104),List(x1101))
    val x1130 = withCtrl(x1131) { UnitController(style=StreamPipe, level=OuterControl).name("x1130").srcCtx("DoubleLoad.scala:39:29") } // UnitPipe(List(b612),Block(Const(())))
    val b1139 = withCtrl(x1130) { StreamOut(field="offset").name("b1139").srcCtx("DoubleLoad.scala:39:29") } // x1108 = StreamOutNew(BurstCmdBus)
    isAccum(b1139) = false
    bufferDepthOf(b1139) = 1
    val b1140 = withCtrl(x1130) { StreamOut(field="size").name("b1140").srcCtx("DoubleLoad.scala:39:29") } // x1108 = StreamOutNew(BurstCmdBus)
    isAccum(b1140) = false
    bufferDepthOf(b1140) = 1
    val x1109 = withCtrl(x1130) { StreamOut(field="data").name("x1109").srcCtx("DoubleLoad.scala:39:29") } // x1109 = StreamOutNew(BurstFullDataBus())
    isAccum(x1109) = false
    bufferDepthOf(x1109) = 1
    val x1110 = withCtrl(x1130) { StreamIn(field="ack").name("x1110").srcCtx("DoubleLoad.scala:39:29") } // x1110 = StreamInNew(BurstAckBus)
    isAccum(x1110) = false
    bufferDepthOf(x1110) = 1
    val x1118 = withCtrl(x1130) { UnitController(style=SeqPipe, level=InnerControl).name("x1118").srcCtx("DoubleLoad.scala:39:29") } // UnitPipe(List(b612),Block(x1117))
    val x1111 = withCtrl(x1118) { b611 } // FixConvert(b611,TRUE,_32,_0) (Same Type. No op)
    val x1112 = withCtrl(x1118) { OpDef(op=FixSla, inputs=List(x1111, Const(2))).name("x1112").srcCtx("DoubleLoad.scala:39:29") } // FixLsh(x1111,Const(2))
    val x1113 = withCtrl(x1118) { x1112 } // FixConvert(x1112,TRUE,_64,_0)
    val x1114 = withCtrl(x1118) { DramAddress(x1054).name("x1114").srcCtx("DoubleLoad.scala:39:29") } // GetDRAMAddress(x1054)
    val x1116_x1115 = withCtrl(x1118) { OpDef(op=FixAdd, inputs=List(x1113, x1114)).name("x1116_x1115").srcCtx("DoubleLoad.scala:39:29") } // FixAdd(x1113,x1114)
    // x1116 = SimpleStruct(ArrayBuffer((offset,x1115), (size,Const(128)), (isLoad,Const(false))))
    val x1117_b1141_b1139 = withCtrl(x1118) { WriteMem(b1139, x1116_x1115).name("x1117_b1141_b1139").srcCtx("DoubleLoad.scala:39:29") } // StreamWrite(x1108,x1116,b612)
    val x1117_b1142_b1140 = withCtrl(x1118) { WriteMem(b1140, Const(128)).name("x1117_b1142_b1140").srcCtx("DoubleLoad.scala:39:29") } // StreamWrite(x1108,x1116,b612)
    val x1119 = withCtrl(x1130) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1119").srcCtx("DoubleLoad.scala:39:29") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1120 = withCtrl(x1130) { CounterChain(List(x1119)).name("x1120").srcCtx("DoubleLoad.scala:39:29") } // CounterChainNew(List(x1119))
    val x1126 = withCtrl(x1130) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1120).name("x1126").srcCtx("DoubleLoad.scala:39:29") } // UnrolledForeach(List(b612),x1120,Block(Const(())),List(List(b683)),List(List(b684)))
    val b683 = withCtrl(x1126) { CounterIter(x1119, None).name("b683") } // b683
    val b684 = withCtrl(x1126) { Const(true).name("b684") } // b684
    val x1121 = withCtrl(x1126) { OpDef(op=BitAnd, inputs=List(b684, b612)).name("x1121").srcCtx("UnrollingBase.scala:28:66") } // And(b684,b612)
    val x1122 = withCtrl(x1126) { LoadBanks(List(x1060_d0_b0), List(b683)).name("x1122").srcCtx("DoubleLoad.scala:39:29") } // ParSRAMLoad(x1060,List(List(b683)),List(x1121))
    val x1124_x1123 = withCtrl(x1126) { x1122 } // VectorApply(x1122,0)
    // x1124 = SimpleStruct(ArrayBuffer((_1,x1123), (_2,Const(true))))
    val x1125_x1125_x1109 = withCtrl(x1126) { WriteMem(x1109, x1124_x1123).name("x1125_x1125_x1109").srcCtx("DoubleLoad.scala:39:29") } // ParStreamWrite(x1109,List(x1124),List(x1121))
    val x1127 = withCtrl(x1130) { FringeDenseStore(dram=List(x1054), cmdStream=List(b1139, b1140), dataStream=List(x1109), ackStream=List(x1110)).name("x1127").srcCtx("DoubleLoad.scala:39:29") } // FringeDenseStore(x1054,x1108,x1109,x1110)
    val x1129 = withCtrl(x1130) { UnitController(style=SeqPipe, level=InnerControl).name("x1129").srcCtx("DoubleLoad.scala:39:29") } // UnitPipe(List(b612),Block(Const(())))
    val x1128_x1128 = withCtrl(x1129) { ReadMem(x1110).name("x1128_x1128").srcCtx("DoubleLoad.scala:39:29") } // StreamRead(x1110,b612)
    
  }
}
