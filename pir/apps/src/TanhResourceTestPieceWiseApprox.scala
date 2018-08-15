import pir._
import pir.node._
import arch._
import prism.enums._

object TanhResourceTestPieceWiseApprox extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1087 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x1087").srcCtx("DataGenerator.scala:146:20:in") } // x1087 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x1091 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x1091").srcCtx("main.scala:43:22:out") } // x1091 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x1092 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x1092").srcCtx("main.scala:44:25:outRef") } // x1092 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x1175 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x1175").srcCtx("main.scala:46:11") } // Hwblock(Block(Const(())),false)
    val x1093_d0_b0 = withCtrl(x1175) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1093_d0_b0").srcCtx("DataGenerator.scala:28:21:mem") } // x1093 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x1093_d0_b0) = false
    bufferDepthOf(x1093_d0_b0) = 1
    staticDimsOf(x1093_d0_b0) = List(512)
    val x1093_d1_b0 = withCtrl(x1175) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1093_d1_b0").srcCtx("DataGenerator.scala:28:21:mem") } // x1093 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x1093_d1_b0) = false
    bufferDepthOf(x1093_d1_b0) = 1
    staticDimsOf(x1093_d1_b0) = List(512)
    val x1111 = withCtrl(x1175) { UnitController(style=StreamPipe, level=OuterControl).name("x1111").srcCtx("DataGenerator.scala:29:8") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1180 = withCtrl(x1111) { StreamOut(field="offset").name("b1180").srcCtx("DataGenerator.scala:29:8") } // x1094 = StreamOutNew(BurstCmdBus)
    isAccum(b1180) = false
    bufferDepthOf(b1180) = 1
    val b1181 = withCtrl(x1111) { StreamOut(field="size").name("b1181").srcCtx("DataGenerator.scala:29:8") } // x1094 = StreamOutNew(BurstCmdBus)
    isAccum(b1181) = false
    bufferDepthOf(b1181) = 1
    val x1095 = withCtrl(x1111) { StreamIn(field="data").name("x1095").srcCtx("DataGenerator.scala:29:8") } // x1095 = StreamInNew(BurstDataBus())
    isAccum(x1095) = false
    bufferDepthOf(x1095) = 1
    val x1103 = withCtrl(x1111) { UnitController(style=SeqPipe, level=InnerControl).name("x1103").srcCtx("DataGenerator.scala:29:8") } // UnitPipe(List(Const(true)),Block(x1102))
    val x1096 = withCtrl(x1103) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1097 = withCtrl(x1103) { OpDef(op=FixSla, inputs=List(x1096, Const(2))).name("x1097").srcCtx("DataGenerator.scala:29:8") } // FixLsh(x1096,Const(2))
    val x1098 = withCtrl(x1103) { x1097 } // FixConvert(x1097,TRUE,_64,_0)
    val x1099 = withCtrl(x1103) { DramAddress(x1087).name("x1099").srcCtx("DataGenerator.scala:29:8") } // GetDRAMAddress(x1087)
    val x1101_x1100 = withCtrl(x1103) { OpDef(op=FixAdd, inputs=List(x1098, x1099)).name("x1101_x1100").srcCtx("DataGenerator.scala:29:8") } // FixAdd(x1098,x1099)
    // x1101 = SimpleStruct(ArrayBuffer((offset,x1100), (size,Const(2048)), (isLoad,Const(true))))
    val x1102_b1182_b1180 = withCtrl(x1103) { WriteMem(b1180, x1101_x1100).name("x1102_b1182_b1180").srcCtx("DataGenerator.scala:29:8") } // StreamWrite(x1094,x1101,Const(true))
    val x1102_b1183_b1181 = withCtrl(x1103) { WriteMem(b1181, Const(2048)).name("x1102_b1183_b1181").srcCtx("DataGenerator.scala:29:8") } // StreamWrite(x1094,x1101,Const(true))
    val x1104 = withCtrl(x1111) { FringeDenseLoad(dram=List(x1087), cmdStream=List(b1180, b1181), dataStream=List(x1095)).name("x1104").srcCtx("DataGenerator.scala:29:8") } // FringeDenseLoad(x1087,x1094,x1095)
    val x1105 = withCtrl(x1111) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x1105").srcCtx("DataGenerator.scala:29:8") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x1106 = withCtrl(x1111) { CounterChain(List(x1105)).name("x1106").srcCtx("DataGenerator.scala:29:8") } // CounterChainNew(List(x1105))
    val x1110 = withCtrl(x1111) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1106).name("x1110").srcCtx("DataGenerator.scala:29:8") } // UnrolledForeach(List(Const(true)),x1106,Block(Const(())),List(List(b634)),List(List(b635)))
    val b634 = withCtrl(x1110) { CounterIter(x1105, None).name("b634") } // b634
    val b635 = withCtrl(x1110) { Const(true).name("b635") } // b635
    val x1107_x1107 = withCtrl(x1110) { ReadMem(x1095).name("x1107_x1107").srcCtx("DataGenerator.scala:29:8") } // ParStreamRead(x1095,List(b635))
    val x1108_x1108 = withCtrl(x1110) { x1107_x1107 } // VectorApply(x1107,0)
    val x1109 = withCtrl(x1110) { StoreBanks(List(List(x1093_d0_b0), List(x1093_d1_b0)), List(b634), x1108_x1108).name("x1109").srcCtx("DataGenerator.scala:29:8") } // ParSRAMStore(x1093,List(List(b634)),List(x1108),List(b635))
    val x1112_d0_b0 = withCtrl(x1175) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1112_d0_b0").srcCtx("main.scala:48:27:outmem") } // x1112 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x1112_d0_b0) = false
    bufferDepthOf(x1112_d0_b0) = 1
    staticDimsOf(x1112_d0_b0) = List(512)
    val x1113 = withCtrl(x1175) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x1113").srcCtx("main.scala:49:27") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x1114 = withCtrl(x1175) { CounterChain(List(x1113)).name("x1114").srcCtx("main.scala:49:35") } // CounterChainNew(List(x1113))
    val x1130 = withCtrl(x1175) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1114).name("x1130").srcCtx("main.scala:49:35") } // UnrolledForeach(List(Const(true)),x1114,Block(Const(())),List(List(b644)),List(List(b645)))
    val b644 = withCtrl(x1130) { CounterIter(x1113, None).name("b644") } // b644
    val b645 = withCtrl(x1130) { Const(true).name("b645") } // b645
    val x1115 = withCtrl(x1130) { LoadBanks(List(x1093_d1_b0), List(b644)).name("x1115").srcCtx("main.scala:50:24:inEle") } // ParSRAMLoad(x1093,List(List(b644)),List(b645))
    val x1116 = withCtrl(x1130) { x1115 } // VectorApply(x1115,0)
    val x1117 = withCtrl(x1130) { OpDef(op=FixAbs, inputs=List(x1116)).name("x1117").srcCtx("NonLinearity.scala:97:20:absin") } // FixAbs(x1116)
    val x1118 = withCtrl(x1130) { OpDef(op=FixSra, inputs=List(x1117, Const(2))).name("x1118").srcCtx("NonLinearity.scala:98:22:div4") } // FixRsh(x1117,Const(2))
    val x1119 = withCtrl(x1130) { OpDef(op=FixAdd, inputs=List(x1118, Const(0.375))).name("x1119").srcCtx("NonLinearity.scala:99:19:li") } // FixAdd(x1118,Const(0.375))
    val x1120 = withCtrl(x1130) { OpDef(op=FixLt, inputs=List(Const(2.5), x1117)).name("x1120").srcCtx("NonLinearity.scala:100:28") } // FixLt(Const(2.5),x1117)
    val x1121 = withCtrl(x1130) { OpDef(op=FixLt, inputs=List(Const(0.5), x1117)).name("x1121").srcCtx("NonLinearity.scala:101:14") } // FixLt(Const(0.5),x1117)
    val x1122 = withCtrl(x1130) { OpDef(op=FixLt, inputs=List(x1117, Const(2.5))).name("x1122").srcCtx("NonLinearity.scala:101:31") } // FixLt(x1117,Const(2.5))
    val x1123 = withCtrl(x1130) { OpDef(op=BitAnd, inputs=List(x1121, x1122)).name("x1123").srcCtx("NonLinearity.scala:101:22") } // And(x1121,x1122)
    val x1124 = withCtrl(x1130) { OpDef(op=MuxOp, inputs=List(x1123, x1119, x1117)).name("x1124").srcCtx("NonLinearity.scala:101:10") } // Mux(x1123,x1119,x1117)
    val x1125 = withCtrl(x1130) { OpDef(op=MuxOp, inputs=List(x1120, Const(1.0), x1124)).name("x1125").srcCtx("NonLinearity.scala:100:21:absout") } // Mux(x1120,Const(1),x1124)
    val x1126 = withCtrl(x1130) { OpDef(op=FixNeg, inputs=List(x1125)).name("x1126").srcCtx("NonLinearity.scala:104:23:negout") } // FixNeg(x1125)
    val x1127 = withCtrl(x1130) { OpDef(op=FixLt, inputs=List(x1116, Const(0.0))).name("x1127").srcCtx("NonLinearity.scala:105:12") } // FixLt(x1116,Const(0))
    val x1128 = withCtrl(x1130) { OpDef(op=MuxOp, inputs=List(x1127, x1126, x1125)).name("x1128").srcCtx("NonLinearity.scala:105:8") } // Mux(x1127,x1126,x1125)
    val x1129 = withCtrl(x1130) { StoreBanks(List(List(x1112_d0_b0)), List(b644), x1128).name("x1129").srcCtx("main.scala:51:19") } // ParSRAMStore(x1112,List(List(b644)),List(x1128),List(b645))
    val x1152 = withCtrl(x1175) { UnitController(style=StreamPipe, level=OuterControl).name("x1152").srcCtx("main.scala:54:21") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1184 = withCtrl(x1152) { StreamOut(field="offset").name("b1184").srcCtx("main.scala:54:21") } // x1131 = StreamOutNew(BurstCmdBus)
    isAccum(b1184) = false
    bufferDepthOf(b1184) = 1
    val b1185 = withCtrl(x1152) { StreamOut(field="size").name("b1185").srcCtx("main.scala:54:21") } // x1131 = StreamOutNew(BurstCmdBus)
    isAccum(b1185) = false
    bufferDepthOf(b1185) = 1
    val x1132 = withCtrl(x1152) { StreamOut(field="data").name("x1132").srcCtx("main.scala:54:21") } // x1132 = StreamOutNew(BurstFullDataBus())
    isAccum(x1132) = false
    bufferDepthOf(x1132) = 1
    val x1133 = withCtrl(x1152) { StreamIn(field="ack").name("x1133").srcCtx("main.scala:54:21") } // x1133 = StreamInNew(BurstAckBus)
    isAccum(x1133) = false
    bufferDepthOf(x1133) = 1
    val x1141 = withCtrl(x1152) { UnitController(style=SeqPipe, level=InnerControl).name("x1141").srcCtx("main.scala:54:21") } // UnitPipe(List(Const(true)),Block(x1140))
    val x1134 = withCtrl(x1141) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1135 = withCtrl(x1141) { OpDef(op=FixSla, inputs=List(x1134, Const(2))).name("x1135").srcCtx("main.scala:54:21") } // FixLsh(x1134,Const(2))
    val x1136 = withCtrl(x1141) { x1135 } // FixConvert(x1135,TRUE,_64,_0)
    val x1137 = withCtrl(x1141) { DramAddress(x1091).name("x1137").srcCtx("main.scala:54:21") } // GetDRAMAddress(x1091)
    val x1139_x1138 = withCtrl(x1141) { OpDef(op=FixAdd, inputs=List(x1136, x1137)).name("x1139_x1138").srcCtx("main.scala:54:21") } // FixAdd(x1136,x1137)
    // x1139 = SimpleStruct(ArrayBuffer((offset,x1138), (size,Const(2048)), (isLoad,Const(false))))
    val x1140_b1186_b1184 = withCtrl(x1141) { WriteMem(b1184, x1139_x1138).name("x1140_b1186_b1184").srcCtx("main.scala:54:21") } // StreamWrite(x1131,x1139,Const(true))
    val x1140_b1187_b1185 = withCtrl(x1141) { WriteMem(b1185, Const(2048)).name("x1140_b1187_b1185").srcCtx("main.scala:54:21") } // StreamWrite(x1131,x1139,Const(true))
    val x1142 = withCtrl(x1152) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x1142").srcCtx("main.scala:54:21") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x1143 = withCtrl(x1152) { CounterChain(List(x1142)).name("x1143").srcCtx("main.scala:54:21") } // CounterChainNew(List(x1142))
    val x1148 = withCtrl(x1152) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1143).name("x1148").srcCtx("main.scala:54:21") } // UnrolledForeach(List(Const(true)),x1143,Block(Const(())),List(List(b675)),List(List(b676)))
    val b675 = withCtrl(x1148) { CounterIter(x1142, None).name("b675") } // b675
    val b676 = withCtrl(x1148) { Const(true).name("b676") } // b676
    val x1144 = withCtrl(x1148) { LoadBanks(List(x1112_d0_b0), List(b675)).name("x1144").srcCtx("main.scala:54:21") } // ParSRAMLoad(x1112,List(List(b675)),List(b676))
    val x1146_x1145 = withCtrl(x1148) { x1144 } // VectorApply(x1144,0)
    // x1146 = SimpleStruct(ArrayBuffer((_1,x1145), (_2,Const(true))))
    val x1147_x1147_x1132 = withCtrl(x1148) { WriteMem(x1132, x1146_x1145).name("x1147_x1147_x1132").srcCtx("main.scala:54:21") } // ParStreamWrite(x1132,List(x1146),List(b676))
    val x1149 = withCtrl(x1152) { FringeDenseStore(dram=List(x1091), cmdStream=List(b1184, b1185), dataStream=List(x1132), ackStream=List(x1133)).name("x1149").srcCtx("main.scala:54:21") } // FringeDenseStore(x1091,x1131,x1132,x1133)
    val x1151 = withCtrl(x1152) { UnitController(style=SeqPipe, level=InnerControl).name("x1151").srcCtx("main.scala:54:21") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x1150_x1150 = withCtrl(x1151) { ReadMem(x1133).name("x1150_x1150").srcCtx("main.scala:54:21") } // StreamRead(x1133,Const(true))
    val x1174 = withCtrl(x1175) { UnitController(style=StreamPipe, level=OuterControl).name("x1174").srcCtx("main.scala:55:24") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1188 = withCtrl(x1174) { StreamOut(field="offset").name("b1188").srcCtx("main.scala:55:24") } // x1153 = StreamOutNew(BurstCmdBus)
    isAccum(b1188) = false
    bufferDepthOf(b1188) = 1
    val b1189 = withCtrl(x1174) { StreamOut(field="size").name("b1189").srcCtx("main.scala:55:24") } // x1153 = StreamOutNew(BurstCmdBus)
    isAccum(b1189) = false
    bufferDepthOf(b1189) = 1
    val x1154 = withCtrl(x1174) { StreamOut(field="data").name("x1154").srcCtx("main.scala:55:24") } // x1154 = StreamOutNew(BurstFullDataBus())
    isAccum(x1154) = false
    bufferDepthOf(x1154) = 1
    val x1155 = withCtrl(x1174) { StreamIn(field="ack").name("x1155").srcCtx("main.scala:55:24") } // x1155 = StreamInNew(BurstAckBus)
    isAccum(x1155) = false
    bufferDepthOf(x1155) = 1
    val x1163 = withCtrl(x1174) { UnitController(style=SeqPipe, level=InnerControl).name("x1163").srcCtx("main.scala:55:24") } // UnitPipe(List(Const(true)),Block(x1162))
    val x1156 = withCtrl(x1163) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1157 = withCtrl(x1163) { OpDef(op=FixSla, inputs=List(x1156, Const(2))).name("x1157").srcCtx("main.scala:55:24") } // FixLsh(x1156,Const(2))
    val x1158 = withCtrl(x1163) { x1157 } // FixConvert(x1157,TRUE,_64,_0)
    val x1159 = withCtrl(x1163) { DramAddress(x1092).name("x1159").srcCtx("main.scala:55:24") } // GetDRAMAddress(x1092)
    val x1161_x1160 = withCtrl(x1163) { OpDef(op=FixAdd, inputs=List(x1158, x1159)).name("x1161_x1160").srcCtx("main.scala:55:24") } // FixAdd(x1158,x1159)
    // x1161 = SimpleStruct(ArrayBuffer((offset,x1160), (size,Const(2048)), (isLoad,Const(false))))
    val x1162_b1190_b1188 = withCtrl(x1163) { WriteMem(b1188, x1161_x1160).name("x1162_b1190_b1188").srcCtx("main.scala:55:24") } // StreamWrite(x1153,x1161,Const(true))
    val x1162_b1191_b1189 = withCtrl(x1163) { WriteMem(b1189, Const(2048)).name("x1162_b1191_b1189").srcCtx("main.scala:55:24") } // StreamWrite(x1153,x1161,Const(true))
    val x1164 = withCtrl(x1174) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x1164").srcCtx("main.scala:55:24") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x1165 = withCtrl(x1174) { CounterChain(List(x1164)).name("x1165").srcCtx("main.scala:55:24") } // CounterChainNew(List(x1164))
    val x1170 = withCtrl(x1174) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1165).name("x1170").srcCtx("main.scala:55:24") } // UnrolledForeach(List(Const(true)),x1165,Block(Const(())),List(List(b699)),List(List(b700)))
    val b699 = withCtrl(x1170) { CounterIter(x1164, None).name("b699") } // b699
    val b700 = withCtrl(x1170) { Const(true).name("b700") } // b700
    val x1166 = withCtrl(x1170) { LoadBanks(List(x1093_d0_b0), List(b699)).name("x1166").srcCtx("main.scala:55:24") } // ParSRAMLoad(x1093,List(List(b699)),List(b700))
    val x1168_x1167 = withCtrl(x1170) { x1166 } // VectorApply(x1166,0)
    // x1168 = SimpleStruct(ArrayBuffer((_1,x1167), (_2,Const(true))))
    val x1169_x1169_x1154 = withCtrl(x1170) { WriteMem(x1154, x1168_x1167).name("x1169_x1169_x1154").srcCtx("main.scala:55:24") } // ParStreamWrite(x1154,List(x1168),List(b700))
    val x1171 = withCtrl(x1174) { FringeDenseStore(dram=List(x1092), cmdStream=List(b1188, b1189), dataStream=List(x1154), ackStream=List(x1155)).name("x1171").srcCtx("main.scala:55:24") } // FringeDenseStore(x1092,x1153,x1154,x1155)
    val x1173 = withCtrl(x1174) { UnitController(style=SeqPipe, level=InnerControl).name("x1173").srcCtx("main.scala:55:24") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x1172_x1172 = withCtrl(x1173) { ReadMem(x1155).name("x1172_x1172").srcCtx("main.scala:55:24") } // StreamRead(x1155,Const(true))
    
  }
}
