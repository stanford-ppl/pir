import pir._
import pir.node._
import arch._
import prism.enums._

object P4 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1271 = StreamIn(field="data").name("x1271").ctrl(top).srcCtx("SimpleP4.scala:101:43:stream_in0") // x1271 = StreamInNew(GPInput1)
    isAccum(x1271) = false
    bufferDepthOf(x1271) = 1
    val x1272 = StreamIn(field="data").name("x1272").ctrl(top).srcCtx("SimpleP4.scala:102:43:stream_in1") // x1272 = StreamInNew(GPInput2)
    isAccum(x1272) = false
    bufferDepthOf(x1272) = 1
    val x1273 = StreamOut(field="data").name("x1273").ctrl(top).srcCtx("SimpleP4.scala:104:44:stream_out0") // x1273 = StreamOutNew(GPOutput1)
    isAccum(x1273) = false
    bufferDepthOf(x1273) = 1
    val x1274 = StreamOut(field="data").name("x1274").ctrl(top).srcCtx("SimpleP4.scala:105:44:stream_out1") // x1274 = StreamOutNew(GPOutput2)
    isAccum(x1274) = false
    bufferDepthOf(x1274) = 1
    // x1275 = Forever() TODO: Unmatched Node
    val x1359 = ForeverController().name("x1359").ctrl(top).srcCtx("SimpleP4.scala:107:12") // Hwblock(Block(Const(())),true)
    val x1276_d0_b0 = SRAM(size=4, banking=Strided(banks=1, stride=2)).name("x1276_d0_b0").ctrl(x1359).srcCtx("SimpleP4.scala:109:35:Parser_SRAM") // x1276 = SRAMNew(ArrayBuffer(Const(2), Const(2)))
    isAccum(x1276_d0_b0) = false
    bufferDepthOf(x1276_d0_b0) = 3
    val x1276_d1_b0 = SRAM(size=4, banking=Strided(banks=1, stride=2)).name("x1276_d1_b0").ctrl(x1359).srcCtx("SimpleP4.scala:109:35:Parser_SRAM") // x1276 = SRAMNew(ArrayBuffer(Const(2), Const(2)))
    isAccum(x1276_d1_b0) = false
    bufferDepthOf(x1276_d1_b0) = 2
    val x1277_d0_b0 = SRAM(size=4, banking=Strided(banks=1, stride=2)).name("x1277_d0_b0").ctrl(x1359).srcCtx("SimpleP4.scala:110:37:Deparser_SRAM") // x1277 = SRAMNew(ArrayBuffer(Const(2), Const(2)))
    isAccum(x1277_d0_b0) = false
    bufferDepthOf(x1277_d0_b0) = 2
    val x1278 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1278").ctrl(x1359).srcCtx("SimpleP4.scala:117:35") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1279 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1279").ctrl(x1359).srcCtx("SimpleP4.scala:117:15") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1280 = CounterChain(List(x1279,x1278)).name("x1280").ctrl(x1359).srcCtx("SimpleP4.scala:117:53") // CounterChainNew(List(x1279, x1278))
    val x1296 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1280).name("x1296").ctrl(x1359).srcCtx("SimpleP4.scala:117:53") // UnrolledForeach(List(Const(true)),x1280,Block(Const(())),List(List(b820), List(b821)),List(List(b822), List(b823)))
    val b820 = CounterIter(x1279, Some(0)).name("b820").ctrl(x1296) // b820
    val b822 = Const(true).name("b822").ctrl(x1296) // b822
    val b821 = CounterIter(x1278, None).name("b821").ctrl(x1296) // b821
    val b823 = Const(true).name("b823").ctrl(x1296) // b823
    val x1281 = OpDef(op=BitAnd, inputs=List(b822, b823)).name("x1281").ctrl(x1296).srcCtx("UnrollingBase.scala:28:66") // And(b822,b823)
    val x1282_x1282 = ReadMem(x1271).name("x1282_x1282").ctrl(x1296).srcCtx("SimpleP4.scala:118:29:pkt0") // ParStreamRead(x1271,List(x1281))
    val x1283_x1283 = x1282_x1282 // x1283 = VectorApply(x1282,0)
    val x1284_x1284 = ReadMem(x1272).name("x1284_x1284").ctrl(x1296).srcCtx("SimpleP4.scala:119:29:pkt1") // ParStreamRead(x1272,List(x1281))
    val x1285_x1285 = x1284_x1284 // x1285 = VectorApply(x1284,0)
    val x1286 = OpDef(op=FixEql, inputs=List(b821, Const(0))).name("x1286").ctrl(x1296).srcCtx("SimpleP4.scala:121:23") // FixEql(b821,Const(0))
    val x1287 = OpDef(op=MuxOp, inputs=List(x1286, x1283_x1283, x1285_x1285)).name("x1287").ctrl(x1296).srcCtx("SimpleP4.scala:121:20:pkt") // Mux(x1286,x1283,x1285)
    val x1288 = OpDef(op=FixEql, inputs=List(b820, Const(0))).name("x1288").ctrl(x1296).srcCtx("SimpleP4.scala:128:23") // FixEql(b820,Const(0))
    val x1289 = x1287 // x1289 = DataAsBits(x1287)
    val x1290 = OpDef(op=BitAnd, inputs=List(x1289, Const(32767))).name("x1290").ctrl(x1296).srcCtx("SimpleP4.scala:128:32") // VectorSlice(x1289,15,0) strMask=00000000000000000111111111111111
    val x1291 = x1290 // x1291 = BitsAsData(x1290,FixPt[FALSE,_16,_0])
    val x1292 = OpDef(op=BitAnd, inputs=List(x1289, Const(2147418112))).name("x1292").ctrl(x1296).srcCtx("SimpleP4.scala:128:55") // VectorSlice(x1289,31,16) strMask=01111111111111110000000000000000
    val x1293 = x1292 // x1293 = BitsAsData(x1292,FixPt[FALSE,_16,_0])
    val x1294 = OpDef(op=MuxOp, inputs=List(x1288, x1291, x1293)).name("x1294").ctrl(x1296).srcCtx("SimpleP4.scala:128:20:fld") // Mux(x1288,x1291,x1293)
    val x1295 = StoreBanks(List(x1276_d0_b0, x1276_d1_b0), List(b820, b821), x1294).name("x1295").ctrl(x1296).srcCtx("SimpleP4.scala:129:25") // ParSRAMStore(x1276,List(List(b820, b821)),List(x1294),List(x1281))
    val x1297_d0_b0 = LUT(inits=List(0, 1), banking=Strided(banks=1, stride=1)).name("x1297_d0_b0").ctrl(x1359).srcCtx("SimpleP4.scala:134:47:match_table") // x1297 = LUTNew(List(2), Seq(Const(0),Const(1)))
    isAccum(x1297_d0_b0) = false
    bufferDepthOf(x1297_d0_b0) = 1
    val x1298_d0_b0 = SRAM(size=4, banking=Strided(banks=1, stride=2)).name("x1298_d0_b0").ctrl(x1359).srcCtx("SimpleP4.scala:135:35:mask_table") // x1298 = SRAMNew(ArrayBuffer(Const(2), Const(2)))
    isAccum(x1298_d0_b0) = false
    bufferDepthOf(x1298_d0_b0) = 2
    val x1299_d0_b0 = LUT(inits=List(0, 1), banking=Strided(banks=1, stride=1)).name("x1299_d0_b0").ctrl(x1359).srcCtx("SimpleP4.scala:136:61:action_table") // x1299 = LUTNew(List(2, 1), Seq(Const(0),Const(1)))
    isAccum(x1299_d0_b0) = false
    bufferDepthOf(x1299_d0_b0) = 1
    val x1300 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1300").ctrl(x1359).srcCtx("SimpleP4.scala:142:36") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1301 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1301").ctrl(x1359).srcCtx("SimpleP4.scala:142:15") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1302 = CounterChain(List(x1301,x1300)).name("x1302").ctrl(x1359).srcCtx("SimpleP4.scala:142:55") // CounterChainNew(List(x1301, x1300))
    val x1308 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1302).name("x1308").ctrl(x1359).srcCtx("SimpleP4.scala:142:55") // UnrolledForeach(List(Const(true)),x1302,Block(Const(())),List(List(b846), List(b847)),List(List(b848), List(b849)))
    val b846 = CounterIter(x1301, Some(0)).name("b846").ctrl(x1308) // b846
    val b848 = Const(true).name("b848").ctrl(x1308) // b848
    val b847 = CounterIter(x1300, None).name("b847").ctrl(x1308) // b847
    val b849 = Const(true).name("b849").ctrl(x1308) // b849
    val x1303 = OpDef(op=BitAnd, inputs=List(b848, b849)).name("x1303").ctrl(x1308).srcCtx("UnrollingBase.scala:28:66") // And(b848,b849)
    val x1304 = LoadBanks(List(x1276_d1_b0), List(Const(0), b846)).name("x1304").ctrl(x1308).srcCtx("SimpleP4.scala:144:37") // SRAMLoad(x1276,ArrayBuffer(Const(2), Const(2)),List(Const(0), b846),Const(0),x1303)
    val x1305 = LoadBanks(List(x1297_d0_b0), List(b847)).name("x1305").ctrl(x1308).srcCtx("SimpleP4.scala:144:58") // LUTLoad(x1297,List(b847),x1303)
    val x1306 = OpDef(op=FixEql, inputs=List(x1304, x1305)).name("x1306").ctrl(x1308).srcCtx("SimpleP4.scala:144:44") // FixEql(x1304,x1305)
    val x1307 = StoreBanks(List(x1298_d0_b0), List(b846, b847), x1306).name("x1307").ctrl(x1308).srcCtx("SimpleP4.scala:144:24") // ParSRAMStore(x1298,List(List(b846, b847)),List(x1306),List(x1303))
    val x1309 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1309").ctrl(x1359).srcCtx("SimpleP4.scala:150:36") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1310 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1310").ctrl(x1359).srcCtx("SimpleP4.scala:150:15") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1311 = CounterChain(List(x1310,x1309)).name("x1311").ctrl(x1359).srcCtx("SimpleP4.scala:150:55") // CounterChainNew(List(x1310, x1309))
    val x1327 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1311).name("x1327").ctrl(x1359).srcCtx("SimpleP4.scala:150:55") // UnrolledForeach(List(Const(true)),x1311,Block(Const(())),List(List(b859), List(b860)),List(List(b861), List(b862)))
    val b859 = CounterIter(x1310, Some(0)).name("b859").ctrl(x1327) // b859
    val b861 = Const(true).name("b861").ctrl(x1327) // b861
    val b860 = CounterIter(x1309, None).name("b860").ctrl(x1327) // b860
    val b862 = Const(true).name("b862").ctrl(x1327) // b862
    val x1312 = OpDef(op=BitAnd, inputs=List(b861, b862)).name("x1312").ctrl(x1327).srcCtx("UnrollingBase.scala:28:66") // And(b861,b862)
    val x1313 = LoadBanks(List(x1298_d0_b0), List(b859, b860)).name("x1313").ctrl(x1327).srcCtx("SimpleP4.scala:152:28:mask") // ParSRAMLoad(x1298,List(List(b859, b860)),List(x1312))
    val x1314 = x1313 // x1314 = VectorApply(x1313,0)
    val x1315 = LoadBanks(List(x1276_d0_b0), List(Const(0), b860)).name("x1315").ctrl(x1327).srcCtx("SimpleP4.scala:153:31:header") // ParSRAMLoad(x1276,List(List(Const(0), b860)),List(x1312))
    val x1316 = x1315 // x1316 = VectorApply(x1315,0)
    val x1317 = LoadBanks(List(x1299_d0_b0), List(b859, Const(0))).name("x1317").ctrl(x1327).srcCtx("SimpleP4.scala:154:32:action") // LUTLoad(x1299,List(b859, Const(0)),x1312)
    val x1318 = OpDef(op=FixEql, inputs=List(x1317, Const(1))).name("x1318").ctrl(x1327).srcCtx("SimpleP4.scala:155:31") // FixEql(x1317,Const(1))
    val x1319 = OpDef(op=FixAdd, inputs=List(x1316, Const(2))).name("x1319").ctrl(x1327).srcCtx("SimpleP4.scala:155:53") // FixAdd(x1316,Const(2))
    val x1320 = OpDef(op=FixAdd, inputs=List(x1316, Const(3))).name("x1320").ctrl(x1327).srcCtx("SimpleP4.scala:155:65") // FixAdd(x1316,Const(3))
    val x1321 = OpDef(op=MuxOp, inputs=List(x1318, x1319, x1320)).name("x1321").ctrl(x1327).srcCtx("SimpleP4.scala:155:24:action1") // Mux(x1318,x1319,x1320)
    val x1322 = OpDef(op=FixEql, inputs=List(x1317, Const(0))).name("x1322").ctrl(x1327).srcCtx("SimpleP4.scala:156:31") // FixEql(x1317,Const(0))
    val x1323 = OpDef(op=FixAdd, inputs=List(x1316, Const(1))).name("x1323").ctrl(x1327).srcCtx("SimpleP4.scala:156:54") // FixAdd(x1316,Const(1))
    val x1324 = OpDef(op=MuxOp, inputs=List(x1322, x1323, x1321)).name("x1324").ctrl(x1327).srcCtx("SimpleP4.scala:156:24:action0") // Mux(x1322,x1323,x1321)
    val x1325 = OpDef(op=MuxOp, inputs=List(x1314, x1324, x1316)).name("x1325").ctrl(x1327).srcCtx("SimpleP4.scala:157:28:new_header") // Mux(x1314,x1324,x1316)
    val x1326 = StoreBanks(List(x1277_d0_b0), List(Const(0), b859), x1325).name("x1326").ctrl(x1327).srcCtx("SimpleP4.scala:160:27") // ParSRAMStore(x1277,List(List(Const(0), b859)),List(x1325),List(x1312))
    val x1328 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1328").ctrl(x1359).srcCtx("SimpleP4.scala:163:35") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1329 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x1329").ctrl(x1359).srcCtx("SimpleP4.scala:163:15") // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x1330 = CounterChain(List(x1329,x1328)).name("x1330").ctrl(x1359).srcCtx("SimpleP4.scala:163:53") // CounterChainNew(List(x1329, x1328))
    val x1358 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1330).name("x1358").ctrl(x1359).srcCtx("SimpleP4.scala:163:53") // UnrolledForeach(List(Const(true)),x1330,Block(Const(())),List(List(b882), List(b883)),List(List(b884), List(b885)))
    val b882 = CounterIter(x1329, Some(0)).name("b882").ctrl(x1358) // b882
    val b884 = Const(true).name("b884").ctrl(x1358) // b884
    val b883 = CounterIter(x1328, Some(0)).name("b883").ctrl(x1358) // b883
    val b885 = Const(true).name("b885").ctrl(x1358) // b885
    val x1331_d0 = Reg(init=Some(0.0)).name("x1331_d0").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // x1331 = RegNew(Const(0))
    isAccum(x1331_d0) = false
    bufferDepthOf(x1331_d0) = 2
    val x1331_d1 = Reg(init=Some(0.0)).name("x1331_d1").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // x1331 = RegNew(Const(0))
    isAccum(x1331_d1) = false
    bufferDepthOf(x1331_d1) = 2
    val x1332_d0 = Reg(init=Some(false)).name("x1332_d0").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // x1332 = RegNew(Const(false))
    isAccum(x1332_d0) = false
    bufferDepthOf(x1332_d0) = 2
    val x1332_d1 = Reg(init=Some(false)).name("x1332_d1").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // x1332 = RegNew(Const(false))
    isAccum(x1332_d1) = false
    bufferDepthOf(x1332_d1) = 2
    val x1333_d0 = Reg(init=Some(false)).name("x1333_d0").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // x1333 = RegNew(Const(false))
    isAccum(x1333_d0) = false
    bufferDepthOf(x1333_d0) = 2
    val x1333_d1 = Reg(init=Some(false)).name("x1333_d1").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // x1333 = RegNew(Const(false))
    isAccum(x1333_d1) = false
    bufferDepthOf(x1333_d1) = 2
    val x1342 = UnitController(style=SeqPipe, level=InnerControl).name("x1342").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // UnitPipe(List(b884, b885),Block(Const(())))
    val x1334 = OpDef(op=BitAnd, inputs=List(b884, b885)).name("x1334").ctrl(x1342).srcCtx("UnrollingBase.scala:28:66") // And(b884,b885)
    val x1335 = LoadBanks(List(x1277_d0_b0), List(b882, b883)).name("x1335").ctrl(x1342).srcCtx("SimpleP4.scala:164:30:fld") // SRAMLoad(x1277,ArrayBuffer(Const(2), Const(2)),List(b882, b883),Const(0),x1334)
    // x1336 = FixConvert(x1335,FALSE,_32,_0) x.tp=FixPt[FALSE,_16,_0] {
    val x1336 = OpDef(op=FixSra, inputs=List(x1335, Const("16"))).name("x1336").ctrl(x1342).srcCtx("SimpleP4.scala:165:25:fld32") // FixConvert(x1335,FALSE,_32,_0)
    // }
    val x1337 = OpDef(op=FixEql, inputs=List(b883, Const(0))).name("x1337").ctrl(x1342).srcCtx("SimpleP4.scala:166:13") // FixEql(b883,Const(0))
    val x1338 = OpDef(op=BitNot, inputs=List(x1337)).name("x1338").ctrl(x1342).srcCtx("SimpleP4.scala:166:7") // Not(x1337)
    val x1339_x1331_d0 = WriteMem(x1331_d0, x1336).name("x1339_x1331_d0").ctrl(x1342).srcCtx("SimpleP4.scala:163:53") // RegWrite(x1331,x1336,x1334)
    val x1339_x1331_d1 = WriteMem(x1331_d1, x1336).name("x1339_x1331_d1").ctrl(x1342).srcCtx("SimpleP4.scala:163:53") // RegWrite(x1331,x1336,x1334)
    val x1340_x1332_d0 = WriteMem(x1332_d0, x1337).name("x1340_x1332_d0").ctrl(x1342).srcCtx("SimpleP4.scala:163:53") // RegWrite(x1332,x1337,x1334)
    val x1340_x1332_d1 = WriteMem(x1332_d1, x1337).name("x1340_x1332_d1").ctrl(x1342).srcCtx("SimpleP4.scala:163:53") // RegWrite(x1332,x1337,x1334)
    val x1341_x1333_d0 = WriteMem(x1333_d0, x1338).name("x1341_x1333_d0").ctrl(x1342).srcCtx("SimpleP4.scala:163:53") // RegWrite(x1333,x1338,x1334)
    val x1341_x1333_d1 = WriteMem(x1333_d1, x1338).name("x1341_x1333_d1").ctrl(x1342).srcCtx("SimpleP4.scala:163:53") // RegWrite(x1333,x1338,x1334)
    val x1343 = ReadMem(x1333_d1).name("x1343").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // RegRead(x1333)
    val x1344 = ReadMem(x1332_d1).name("x1344").ctrl(x1358).srcCtx("SimpleP4.scala:163:53") // RegRead(x1332)
    val x1357 = UnitController(style=ForkSwitch, level=OuterControl).name("x1357").ctrl(x1358).srcCtx("SimpleP4.scala:166:7") // //TODO Switch(Block(x1356),List(x1344, x1343),List(x1350, x1356))
    val x1350 = UnitController(style=MetaPipe, level=InnerControl).name("x1350").ctrl(x1357).srcCtx("SimpleP4.scala:166:7") // //TODO SwitchCase(Block(x1349))
    val x1345 = ReadMem(x1332_d0).name("x1345").ctrl(x1350).srcCtx("SimpleP4.scala:163:53") // RegRead(x1332)
    val x1346 = ReadMem(x1331_d1).name("x1346").ctrl(x1350).srcCtx("SimpleP4.scala:163:53") // RegRead(x1331)
    val x1347 = OpDef(op=BitAnd, inputs=List(b884, b885)).name("x1347").ctrl(x1350).srcCtx("UnrollingBase.scala:28:66") // And(b884,b885)
    val x1348 = OpDef(op=BitAnd, inputs=List(x1345, x1347)).name("x1348").ctrl(x1350) // And(x1345,x1347)
    val x1349_x1349_x1273 = WriteMem(x1273, x1346).name("x1349_x1349_x1273").ctrl(x1350).srcCtx("SimpleP4.scala:167:21") // StreamWrite(x1273,x1346,x1348)
    val x1356 = UnitController(style=MetaPipe, level=InnerControl).name("x1356").ctrl(x1357).srcCtx("SimpleP4.scala:166:7") // //TODO SwitchCase(Block(x1355))
    val x1351 = ReadMem(x1333_d0).name("x1351").ctrl(x1356).srcCtx("SimpleP4.scala:163:53") // RegRead(x1333)
    val x1352 = ReadMem(x1331_d0).name("x1352").ctrl(x1356).srcCtx("SimpleP4.scala:163:53") // RegRead(x1331)
    val x1353 = OpDef(op=BitAnd, inputs=List(b884, b885)).name("x1353").ctrl(x1356).srcCtx("UnrollingBase.scala:28:66") // And(b884,b885)
    val x1354 = OpDef(op=BitAnd, inputs=List(x1351, x1353)).name("x1354").ctrl(x1356) // And(x1351,x1353)
    val x1355_x1355_x1274 = WriteMem(x1274, x1352).name("x1355_x1355_x1274").ctrl(x1356).srcCtx("SimpleP4.scala:169:21") // StreamWrite(x1274,x1352,x1354)
    
  }
}
