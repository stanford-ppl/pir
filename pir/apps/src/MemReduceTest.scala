import pir._
import pir.node._
import arch._
import prism.enums._

object MemReduceTest extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1137 = DRAM(dims=List(Const(64))).name("x1137").ctrl(top).srcCtx("MemReduceTest.scala:17:24:dramA") // x1137 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x1138 = DRAM(dims=List(Const(64))).name("x1138").ctrl(top).srcCtx("MemReduceTest.scala:18:24:dramB") // x1138 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x1139 = DRAM(dims=List(Const(16))).name("x1139").ctrl(top).srcCtx("MemReduceTest.scala:19:24:dramC") // x1139 = DRAMNew(ArrayBuffer(Const(16)),Const(0))
    val x1242 = UnitController(style=SeqPipe, level=OuterControl).name("x1242").ctrl(top).srcCtx("MemReduceTest.scala:21:10") // Hwblock(Block(Const(())),false)
    val x1140 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1140").ctrl(x1242).srcCtx("MemReduceTest.scala:23:17") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1141 = CounterChain(List(x1140)).name("x1141").ctrl(x1242).srcCtx("MemReduceTest.scala:23:23") // CounterChainNew(List(x1140))
    val x1241 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1141).name("x1241").ctrl(x1242).srcCtx("MemReduceTest.scala:23:23") // UnrolledForeach(List(Const(true)),x1141,Block(Const(())),List(List(b598)),List(List(b599)))
    val b598 = CounterIter(x1140, Some(0)).name("b598").ctrl(x1241) // b598
    val b599 = Const(true).name("b599").ctrl(x1241) // b599
    val x1142_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1142_d0_b0").ctrl(x1241).srcCtx("MemReduceTest.scala:24:28:sramA") // x1142 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1142_d0_b0) = false
    bufferDepthOf(x1142_d0_b0) = 3
    val x1143_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1143_d0_b0").ctrl(x1241).srcCtx("MemReduceTest.scala:25:28:sramB") // x1143 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1143_d0_b0) = false
    bufferDepthOf(x1143_d0_b0) = 2
    val x1144_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1144_d0_b0").ctrl(x1241).srcCtx("MemReduceTest.scala:26:28:accum") // x1144 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1144_d0_b0) = false
    bufferDepthOf(x1144_d0_b0) = 2
    val x1144_d1_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1144_d1_b0").ctrl(x1241).srcCtx("MemReduceTest.scala:26:28:accum") // x1144 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1144_d1_b0) = true
    bufferDepthOf(x1144_d1_b0) = 1
    val x1145 = Counter(min=Const(0), max=Const(64), step=Const(16), par=1).name("x1145").ctrl(x1241).srcCtx("MemReduceTest.scala:27:37") // CounterNew(Const(0),Const(64),Const(16),Const(1))
    val x1146 = CounterChain(List(x1145)).name("x1146").ctrl(x1241).srcCtx("MemReduceTest.scala:35:11") // CounterChainNew(List(x1145))
    val x1217 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1146).name("x1217").ctrl(x1241).srcCtx("MemReduceTest.scala:35:11") // UnrolledReduce(List(b599),x1146,x1144,Block((x1144) => Const(())),List(List(b607)),List(List(b608)))
    val b607 = CounterIter(x1145, Some(0)).name("b607").ctrl(x1217) // b607
    val b608 = Const(true).name("b608").ctrl(x1217) // b608
    val x1148 = UnitController(style=SeqPipe, level=InnerControl).name("x1148").ctrl(x1217).srcCtx("MemReduceTest.scala:35:11") // UnitPipe(List(b608, b599),Block(Const(())))
    val x1147 = OpDef(op=FixAdd, inputs=List(b607, Const(64))).name("x1147").ctrl(x1148).srcCtx("MemReduceTest.scala:28:32") // FixAdd(b607,Const(64))
    val x1169 = UnitController(style=StreamPipe, level=OuterControl).name("x1169").ctrl(x1217).srcCtx("MemReduceTest.scala:28:17") // UnitPipe(List(b608, b599),Block(Const(())))
    val b1243 = StreamOut(field="offset").name("b1243").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // x1149 = StreamOutNew(BurstCmdBus)
    isAccum(b1243) = false
    bufferDepthOf(b1243) = 1
    val b1244 = StreamOut(field="size").name("b1244").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // x1149 = StreamOutNew(BurstCmdBus)
    isAccum(b1244) = false
    bufferDepthOf(b1244) = 1
    val x1150 = StreamIn(field="data").name("x1150").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // x1150 = StreamInNew(BurstDataBus())
    isAccum(x1150) = false
    bufferDepthOf(x1150) = 1
    val x1159 = UnitController(style=SeqPipe, level=InnerControl).name("x1159").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // UnitPipe(List(b608, b599),Block(x1158))
    val x1151 = b607 // FixConvert(b607,TRUE,_32,_0) (Same Type. No op)
    val x1152 = OpDef(op=FixSla, inputs=List(x1151, Const(2))).name("x1152").ctrl(x1159).srcCtx("MemReduceTest.scala:28:17") // FixLsh(x1151,Const(2))
    val x1153 = x1152 // FixConvert(x1152,TRUE,_64,_0)
    val x1154 = DramAddress(x1137).name("x1154").ctrl(x1159).srcCtx("MemReduceTest.scala:28:17") // GetDRAMAddress(x1137)
    val x1156_x1155 = OpDef(op=FixAdd, inputs=List(x1153, x1154)).name("x1156_x1155").ctrl(x1159).srcCtx("MemReduceTest.scala:28:17") // FixAdd(x1153,x1154)
    // x1156 = SimpleStruct(ArrayBuffer((offset,x1155), (size,Const(256)), (isLoad,Const(true))))
    val x1157 = OpDef(op=BitAnd, inputs=List(b608, b599)).name("x1157").ctrl(x1159).srcCtx("UnrollingBase.scala:28:66") // And(b608,b599)
    val x1158_b1245_b1243 = WriteMem(b1243, x1156_x1155).name("x1158_b1245_b1243").ctrl(x1159).srcCtx("MemReduceTest.scala:28:17") // StreamWrite(x1149,x1156,x1157)
    val x1158_b1246_b1244 = WriteMem(b1244, Const(256)).name("x1158_b1246_b1244").ctrl(x1159).srcCtx("MemReduceTest.scala:28:17") // StreamWrite(x1149,x1156,x1157)
    val x1160 = FringeDenseLoad(dram=List(x1137), cmdStream=List(b1243, b1244), dataStream=List(x1150)).name("x1160").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // FringeDenseLoad(x1137,x1149,x1150)
    val x1161 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x1161").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x1162 = CounterChain(List(x1161)).name("x1162").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // CounterChainNew(List(x1161))
    val x1168 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1162).name("x1168").ctrl(x1169).srcCtx("MemReduceTest.scala:28:17") // UnrolledForeach(List(b608, b599),x1162,Block(Const(())),List(List(b625)),List(List(b626)))
    val b625 = CounterIter(x1161, None).name("b625").ctrl(x1168) // b625
    val b626 = Const(true).name("b626").ctrl(x1168) // b626
    val x1163 = OpDef(op=BitAnd, inputs=List(b626, b608)).name("x1163").ctrl(x1168).srcCtx("UnrollingBase.scala:28:66") // And(b626,b608)
    val x1164 = OpDef(op=BitAnd, inputs=List(x1163, b599)).name("x1164").ctrl(x1168).srcCtx("UnrollingBase.scala:28:66") // And(x1163,b599)
    val x1165_x1165 = ReadMem(x1150).name("x1165_x1165").ctrl(x1168).srcCtx("MemReduceTest.scala:28:17") // ParStreamRead(x1150,List(x1164))
    val x1166_x1166 = x1165_x1165 // x1166 = VectorApply(x1165,0)
    val x1167 = StoreBanks(List(x1142_d0_b0), List(b625), x1166_x1166).name("x1167").ctrl(x1168).srcCtx("MemReduceTest.scala:28:17") // ParSRAMStore(x1142,List(List(b625)),List(x1166),List(x1164))
    val x1190 = UnitController(style=StreamPipe, level=OuterControl).name("x1190").ctrl(x1217).srcCtx("MemReduceTest.scala:29:17") // UnitPipe(List(b608, b599),Block(Const(())))
    val b1247 = StreamOut(field="offset").name("b1247").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // x1170 = StreamOutNew(BurstCmdBus)
    isAccum(b1247) = false
    bufferDepthOf(b1247) = 1
    val b1248 = StreamOut(field="size").name("b1248").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // x1170 = StreamOutNew(BurstCmdBus)
    isAccum(b1248) = false
    bufferDepthOf(b1248) = 1
    val x1171 = StreamIn(field="data").name("x1171").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // x1171 = StreamInNew(BurstDataBus())
    isAccum(x1171) = false
    bufferDepthOf(x1171) = 1
    val x1180 = UnitController(style=SeqPipe, level=InnerControl).name("x1180").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // UnitPipe(List(b608, b599),Block(x1179))
    val x1172 = b607 // FixConvert(b607,TRUE,_32,_0) (Same Type. No op)
    val x1173 = OpDef(op=FixSla, inputs=List(x1172, Const(2))).name("x1173").ctrl(x1180).srcCtx("MemReduceTest.scala:29:17") // FixLsh(x1172,Const(2))
    val x1174 = x1173 // FixConvert(x1173,TRUE,_64,_0)
    val x1175 = DramAddress(x1138).name("x1175").ctrl(x1180).srcCtx("MemReduceTest.scala:29:17") // GetDRAMAddress(x1138)
    val x1177_x1176 = OpDef(op=FixAdd, inputs=List(x1174, x1175)).name("x1177_x1176").ctrl(x1180).srcCtx("MemReduceTest.scala:29:17") // FixAdd(x1174,x1175)
    // x1177 = SimpleStruct(ArrayBuffer((offset,x1176), (size,Const(256)), (isLoad,Const(true))))
    val x1178 = OpDef(op=BitAnd, inputs=List(b608, b599)).name("x1178").ctrl(x1180).srcCtx("UnrollingBase.scala:28:66") // And(b608,b599)
    val x1179_b1249_b1247 = WriteMem(b1247, x1177_x1176).name("x1179_b1249_b1247").ctrl(x1180).srcCtx("MemReduceTest.scala:29:17") // StreamWrite(x1170,x1177,x1178)
    val x1179_b1250_b1248 = WriteMem(b1248, Const(256)).name("x1179_b1250_b1248").ctrl(x1180).srcCtx("MemReduceTest.scala:29:17") // StreamWrite(x1170,x1177,x1178)
    val x1181 = FringeDenseLoad(dram=List(x1138), cmdStream=List(b1247, b1248), dataStream=List(x1171)).name("x1181").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // FringeDenseLoad(x1138,x1170,x1171)
    val x1182 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x1182").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x1183 = CounterChain(List(x1182)).name("x1183").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // CounterChainNew(List(x1182))
    val x1189 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1183).name("x1189").ctrl(x1190).srcCtx("MemReduceTest.scala:29:17") // UnrolledForeach(List(b608, b599),x1183,Block(Const(())),List(List(b648)),List(List(b649)))
    val b648 = CounterIter(x1182, None).name("b648").ctrl(x1189) // b648
    val b649 = Const(true).name("b649").ctrl(x1189) // b649
    val x1184 = OpDef(op=BitAnd, inputs=List(b649, b608)).name("x1184").ctrl(x1189).srcCtx("UnrollingBase.scala:28:66") // And(b649,b608)
    val x1185 = OpDef(op=BitAnd, inputs=List(x1184, b599)).name("x1185").ctrl(x1189).srcCtx("UnrollingBase.scala:28:66") // And(x1184,b599)
    val x1186_x1186 = ReadMem(x1171).name("x1186_x1186").ctrl(x1189).srcCtx("MemReduceTest.scala:29:17") // ParStreamRead(x1171,List(x1185))
    val x1187_x1187 = x1186_x1186 // x1187 = VectorApply(x1186,0)
    val x1188 = StoreBanks(List(x1143_d0_b0), List(b648), x1187_x1187).name("x1188").ctrl(x1189).srcCtx("MemReduceTest.scala:29:17") // ParSRAMStore(x1143,List(List(b648)),List(x1187),List(x1185))
    val x1191_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1191_d0_b0").ctrl(x1217).srcCtx("MemReduceTest.scala:30:30:sramC") // x1191 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1191_d0_b0) = false
    bufferDepthOf(x1191_d0_b0) = 2
    val x1192 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1192").ctrl(x1217).srcCtx("MemReduceTest.scala:31:27") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1193 = CounterChain(List(x1192)).name("x1193").ctrl(x1217).srcCtx("MemReduceTest.scala:31:35") // CounterChainNew(List(x1192))
    val x1202 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1193).name("x1202").ctrl(x1217).srcCtx("MemReduceTest.scala:31:35") // UnrolledForeach(List(b608, b599),x1193,Block(Const(())),List(List(b660)),List(List(b661)))
    val b660 = CounterIter(x1192, None).name("b660").ctrl(x1202) // b660
    val b661 = Const(true).name("b661").ctrl(x1202) // b661
    val x1194 = OpDef(op=BitAnd, inputs=List(b661, b608)).name("x1194").ctrl(x1202).srcCtx("UnrollingBase.scala:28:66") // And(b661,b608)
    val x1195 = OpDef(op=BitAnd, inputs=List(x1194, b599)).name("x1195").ctrl(x1202).srcCtx("UnrollingBase.scala:28:66") // And(x1194,b599)
    val x1196 = LoadBanks(List(x1142_d0_b0), List(b660)).name("x1196").ctrl(x1202).srcCtx("MemReduceTest.scala:32:29") // ParSRAMLoad(x1142,List(List(b660)),List(x1195))
    val x1197 = x1196 // x1197 = VectorApply(x1196,0)
    val x1198 = LoadBanks(List(x1143_d0_b0), List(b660)).name("x1198").ctrl(x1202).srcCtx("MemReduceTest.scala:32:40") // ParSRAMLoad(x1143,List(List(b660)),List(x1195))
    val x1199 = x1198 // x1199 = VectorApply(x1198,0)
    val x1200 = OpDef(op=FixMul, inputs=List(x1197, x1199)).name("x1200").ctrl(x1202).srcCtx("MemReduceTest.scala:32:33") // FixMul(x1197,x1199)
    val x1201 = StoreBanks(List(x1191_d0_b0), List(b660), x1200).name("x1201").ctrl(x1202).srcCtx("MemReduceTest.scala:32:22") // ParSRAMStore(x1191,List(List(b660)),List(x1200),List(x1195))
    val x1203 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1203").ctrl(x1217).srcCtx("MemReduceTest.scala:35:11") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1204 = CounterChain(List(x1203)).name("x1204").ctrl(x1217).srcCtx("MemReduceTest.scala:35:11") // CounterChainNew(ArrayBuffer(x1203))
    val x1216 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1204).name("x1216").ctrl(x1217).srcCtx("MemReduceTest.scala:35:11") // UnrolledForeach(List(),x1204,Block(Const(())),ArrayBuffer(List(b671)),ArrayBuffer(List(b672)))
    val b671 = CounterIter(x1203, None).name("b671").ctrl(x1216) // b671
    val b672 = Const(true).name("b672").ctrl(x1216) // b672
    val x1205 = OpDef(op=BitAnd, inputs=List(b672, b599)).name("x1205").ctrl(x1216).srcCtx("UnrollingBase.scala:28:66") // And(b672,b599)
    val x1206 = LoadBanks(List(x1191_d0_b0), List(b671)).name("x1206").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // ParSRAMLoad(x1191,List(ArrayBuffer(b671)),List(x1205))
    val x1207 = x1206 // x1207 = VectorApply(x1206,0)
    val x1208 = LoadBanks(List(x1144_d1_b0), List(b671)).name("x1208").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // ParSRAMLoad(x1144,List(ArrayBuffer(b671)),List(x1205))
    val x1209 = x1208 // x1209 = VectorApply(x1208,0)
    val x1210 = OpDef(op=BitAnd, inputs=List(b608, b599)).name("x1210").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // And(b608,b599)
    val x1211 = OpDef(op=BitAnd, inputs=List(x1210, x1205)).name("x1211").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // And(x1210,x1205)
    val x1212 = OpDef(op=FixEql, inputs=List(b607, Const(0))).name("x1212").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // FixEql(b607,Const(0))
    val x1213 = OpDef(op=FixAdd, inputs=List(x1207, x1209)).name("x1213").ctrl(x1216).srcCtx("MemReduceTest.scala:35:15") // FixAdd(x1207,x1209)
    val x1214 = OpDef(op=MuxOp, inputs=List(x1212, x1207, x1213)).name("x1214").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // Mux(x1212,x1207,x1213)
    // x1215.deps=List(x1208)
    val x1215 = StoreBanks(List(x1144_d0_b0, x1144_d1_b0), List(b671), x1214).name("x1215").ctrl(x1216).srcCtx("MemReduceTest.scala:35:11") // ParSRAMStore(x1144,List(ArrayBuffer(b671)),List(x1214),List(x1205))
    val x1240 = UnitController(style=StreamPipe, level=OuterControl).name("x1240").ctrl(x1241).srcCtx("MemReduceTest.scala:36:22") // UnitPipe(List(b599),Block(Const(())))
    val b1251 = StreamOut(field="offset").name("b1251").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // x1218 = StreamOutNew(BurstCmdBus)
    isAccum(b1251) = false
    bufferDepthOf(b1251) = 1
    val b1252 = StreamOut(field="size").name("b1252").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // x1218 = StreamOutNew(BurstCmdBus)
    isAccum(b1252) = false
    bufferDepthOf(b1252) = 1
    val x1219 = StreamOut(field="data").name("x1219").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // x1219 = StreamOutNew(BurstFullDataBus())
    isAccum(x1219) = false
    bufferDepthOf(x1219) = 1
    val x1220 = StreamIn(field="ack").name("x1220").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // x1220 = StreamInNew(BurstAckBus)
    isAccum(x1220) = false
    bufferDepthOf(x1220) = 1
    val x1228 = UnitController(style=SeqPipe, level=InnerControl).name("x1228").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // UnitPipe(List(b599),Block(x1227))
    val x1221 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1222 = OpDef(op=FixSla, inputs=List(x1221, Const(2))).name("x1222").ctrl(x1228).srcCtx("MemReduceTest.scala:36:22") // FixLsh(x1221,Const(2))
    val x1223 = x1222 // FixConvert(x1222,TRUE,_64,_0)
    val x1224 = DramAddress(x1139).name("x1224").ctrl(x1228).srcCtx("MemReduceTest.scala:36:22") // GetDRAMAddress(x1139)
    val x1226_x1225 = OpDef(op=FixAdd, inputs=List(x1223, x1224)).name("x1226_x1225").ctrl(x1228).srcCtx("MemReduceTest.scala:36:22") // FixAdd(x1223,x1224)
    // x1226 = SimpleStruct(ArrayBuffer((offset,x1225), (size,Const(64)), (isLoad,Const(false))))
    val x1227_b1253_b1251 = WriteMem(b1251, x1226_x1225).name("x1227_b1253_b1251").ctrl(x1228).srcCtx("MemReduceTest.scala:36:22") // StreamWrite(x1218,x1226,b599)
    val x1227_b1254_b1252 = WriteMem(b1252, Const(64)).name("x1227_b1254_b1252").ctrl(x1228).srcCtx("MemReduceTest.scala:36:22") // StreamWrite(x1218,x1226,b599)
    val x1229 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x1229").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x1230 = CounterChain(List(x1229)).name("x1230").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // CounterChainNew(List(x1229))
    val x1236 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1230).name("x1236").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // UnrolledForeach(List(b599),x1230,Block(Const(())),List(List(b699)),List(List(b700)))
    val b699 = CounterIter(x1229, None).name("b699").ctrl(x1236) // b699
    val b700 = Const(true).name("b700").ctrl(x1236) // b700
    val x1231 = OpDef(op=BitAnd, inputs=List(b700, b599)).name("x1231").ctrl(x1236).srcCtx("UnrollingBase.scala:28:66") // And(b700,b599)
    val x1232 = LoadBanks(List(x1144_d0_b0), List(b699)).name("x1232").ctrl(x1236).srcCtx("MemReduceTest.scala:36:22") // ParSRAMLoad(x1144,List(List(b699)),List(x1231))
    val x1234_x1233 = x1232 // x1233 = VectorApply(x1232,0)
    // x1234 = SimpleStruct(ArrayBuffer((_1,x1233), (_2,Const(true))))
    val x1235_x1235_x1219 = WriteMem(x1219, x1234_x1233).name("x1235_x1235_x1219").ctrl(x1236).srcCtx("MemReduceTest.scala:36:22") // ParStreamWrite(x1219,List(x1234),List(x1231))
    val x1237 = FringeDenseStore(dram=List(x1139), cmdStream=List(b1251, b1252), dataStream=List(x1219), ackStream=List(x1220)).name("x1237").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // FringeDenseStore(x1139,x1218,x1219,x1220)
    val x1239 = UnitController(style=SeqPipe, level=InnerControl).name("x1239").ctrl(x1240).srcCtx("MemReduceTest.scala:36:22") // UnitPipe(List(b599),Block(Const(())))
    val x1238_x1238 = ReadMem(x1220).name("x1238_x1238").ctrl(x1239).srcCtx("MemReduceTest.scala:36:22") // StreamRead(x1220,b599)
    
  }
}
