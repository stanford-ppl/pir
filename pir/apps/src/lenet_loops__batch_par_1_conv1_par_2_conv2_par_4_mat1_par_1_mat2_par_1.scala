import pir._
import pir.node._
import arch._
import prism.enums._

object lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x10987 = withCtrl(design.top.topController) { DRAM(dims=List(Const(20), Const(5), Const(16))).name("x10987").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:30:26:c0_DRAM") } // x10987 = DRAMNew(ArrayBuffer(Const(20), Const(5), Const(16)),Const(0))
    val x10988 = withCtrl(design.top.topController) { DRAM(dims=List(Const(3), Const(28), Const(32))).name("x10988").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:31:26:i0_DRAM") } // x10988 = DRAMNew(ArrayBuffer(Const(3), Const(28), Const(32)),Const(0))
    val x10989 = withCtrl(design.top.topController) { DRAM(dims=List(Const(32))).name("x10989").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:32:26:c1_DRAM") } // x10989 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x10990 = withCtrl(design.top.topController) { DRAM(dims=List(Const(50), Const(5), Const(16))).name("x10990").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:33:26:c2_DRAM") } // x10990 = DRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)),Const(0))
    val x10991 = withCtrl(design.top.topController) { DRAM(dims=List(Const(64))).name("x10991").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:34:26:c3_DRAM") } // x10991 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x10992 = withCtrl(design.top.topController) { DRAM(dims=List(Const(100), Const(4), Const(4), Const(4), Const(64))).name("x10992").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:35:26:c4_DRAM") } // x10992 = DRAMNew(ArrayBuffer(Const(100), Const(4), Const(4), Const(4), Const(64)),Const(0))
    val x10993 = withCtrl(design.top.topController) { DRAM(dims=List(Const(100), Const(5))).name("x10993").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:37:26:c5_DRAM") } // x10993 = DRAMNew(ArrayBuffer(Const(100), Const(5)),Const(0))
    val x10994 = withCtrl(design.top.topController) { DRAM(dims=List(Const(10), Const(100), Const(5))).name("x10994").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:38:26:c6_DRAM") } // x10994 = DRAMNew(ArrayBuffer(Const(10), Const(100), Const(5)),Const(0))
    val x10995 = withCtrl(design.top.topController) { DRAM(dims=List(Const(32))).name("x10995").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:40:26:c7_DRAM") } // x10995 = DRAMNew(ArrayBuffer(Const(32)),Const(0))
    val x10996 = withCtrl(design.top.topController) { DRAM(dims=List(Const(3), Const(32))).name("x10996").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:41:28:tmp5_DRAM") } // x10996 = DRAMNew(ArrayBuffer(Const(3), Const(32)),Const(0))
    val x12029 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x12029").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:69:11") } // Hwblock(Block(Const(())),false)
    val x11106_d0_b0 = withCtrl(x12029) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x11106_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:71:28:c1_SRAM") } // x11106 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x11106_d0_b0) = false
    bufferDepthOf(x11106_d0_b0) = 1
    staticDimsOf(x11106_d0_b0) = List(32)
    val x11124 = withCtrl(x12029) { UnitController(style=StreamPipe, level=OuterControl).name("x11124").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b12067 = withCtrl(x11124) { StreamOut(field="offset").name("b12067").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // x11107 = StreamOutNew(BurstCmdBus)
    isAccum(b12067) = false
    bufferDepthOf(b12067) = 1
    val b12068 = withCtrl(x11124) { StreamOut(field="size").name("b12068").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // x11107 = StreamOutNew(BurstCmdBus)
    isAccum(b12068) = false
    bufferDepthOf(b12068) = 1
    val x11108 = withCtrl(x11124) { StreamIn(field="data").name("x11108").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // x11108 = StreamInNew(BurstDataBus())
    isAccum(x11108) = false
    bufferDepthOf(x11108) = 1
    val x11116 = withCtrl(x11124) { UnitController(style=SeqPipe, level=InnerControl).name("x11116").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // UnitPipe(List(Const(true)),Block(x11115))
    val x11109 = withCtrl(x11116) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11110 = withCtrl(x11116) { OpDef(op=FixSla, inputs=List(x11109, Const(2))).name("x11110").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // FixLsh(x11109,Const(2))
    val x11111 = withCtrl(x11116) { x11110 } // FixConvert(x11110,TRUE,_64,_0)
    val x11112 = withCtrl(x11116) { DramAddress(x10989).name("x11112").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // GetDRAMAddress(x10989)
    val x11114_x11113 = withCtrl(x11116) { OpDef(op=FixAdd, inputs=List(x11111, x11112)).name("x11114_x11113").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // FixAdd(x11111,x11112)
    // x11114 = SimpleStruct(ArrayBuffer((offset,x11113), (size,Const(128)), (isLoad,Const(true))))
    val x11115_b12069_b12067 = withCtrl(x11116) { WriteMem(b12067, x11114_x11113).name("x11115_b12069_b12067").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // StreamWrite(x11107,x11114,Const(true))
    val x11115_b12070_b12068 = withCtrl(x11116) { WriteMem(b12068, Const(128)).name("x11115_b12070_b12068").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // StreamWrite(x11107,x11114,Const(true))
    val x11117 = withCtrl(x11124) { FringeDenseLoad(dram=List(x10989), cmdStream=List(b12067, b12068), dataStream=List(x11108)).name("x11117").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // FringeDenseLoad(x10989,x11107,x11108)
    val x11118 = withCtrl(x11124) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x11118").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x11119 = withCtrl(x11124) { CounterChain(List(x11118)).name("x11119").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // CounterChainNew(List(x11118))
    val x11123 = withCtrl(x11124) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11119).name("x11123").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // UnrolledForeach(List(Const(true)),x11119,Block(Const(())),List(List(b5363)),List(List(b5364)))
    val b5363 = withCtrl(x11123) { CounterIter(x11118, None).name("b5363") } // b5363
    val b5364 = withCtrl(x11123) { Const(true).name("b5364") } // b5364
    val x11120_x11120 = withCtrl(x11123) { ReadMem(x11108).name("x11120_x11120").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // ParStreamRead(x11108,List(b5364))
    val x11121_x11121 = withCtrl(x11123) { x11120_x11120 } // VectorApply(x11120,0)
    val x11122 = withCtrl(x11123) { StoreBanks(List(List(x11106_d0_b0)), List(b5363), x11121_x11121).name("x11122").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:72:15") } // ParSRAMStore(x11106,List(List(b5363)),List(x11121),List(b5364))
    val x11125_d0_b0 = withCtrl(x12029) { SRAM(size=64, banking=Strided(banks=16, stride=1)).name("x11125_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:74:28:c3_SRAM") } // x11125 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x11125_d0_b0) = false
    bufferDepthOf(x11125_d0_b0) = 1
    staticDimsOf(x11125_d0_b0) = List(64)
    val x11143 = withCtrl(x12029) { UnitController(style=StreamPipe, level=OuterControl).name("x11143").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b12071 = withCtrl(x11143) { StreamOut(field="offset").name("b12071").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // x11126 = StreamOutNew(BurstCmdBus)
    isAccum(b12071) = false
    bufferDepthOf(b12071) = 1
    val b12072 = withCtrl(x11143) { StreamOut(field="size").name("b12072").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // x11126 = StreamOutNew(BurstCmdBus)
    isAccum(b12072) = false
    bufferDepthOf(b12072) = 1
    val x11127 = withCtrl(x11143) { StreamIn(field="data").name("x11127").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // x11127 = StreamInNew(BurstDataBus())
    isAccum(x11127) = false
    bufferDepthOf(x11127) = 1
    val x11135 = withCtrl(x11143) { UnitController(style=SeqPipe, level=InnerControl).name("x11135").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // UnitPipe(List(Const(true)),Block(x11134))
    val x11128 = withCtrl(x11135) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11129 = withCtrl(x11135) { OpDef(op=FixSla, inputs=List(x11128, Const(2))).name("x11129").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // FixLsh(x11128,Const(2))
    val x11130 = withCtrl(x11135) { x11129 } // FixConvert(x11129,TRUE,_64,_0)
    val x11131 = withCtrl(x11135) { DramAddress(x10991).name("x11131").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // GetDRAMAddress(x10991)
    val x11133_x11132 = withCtrl(x11135) { OpDef(op=FixAdd, inputs=List(x11130, x11131)).name("x11133_x11132").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // FixAdd(x11130,x11131)
    // x11133 = SimpleStruct(ArrayBuffer((offset,x11132), (size,Const(256)), (isLoad,Const(true))))
    val x11134_b12073_b12071 = withCtrl(x11135) { WriteMem(b12071, x11133_x11132).name("x11134_b12073_b12071").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // StreamWrite(x11126,x11133,Const(true))
    val x11134_b12074_b12072 = withCtrl(x11135) { WriteMem(b12072, Const(256)).name("x11134_b12074_b12072").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // StreamWrite(x11126,x11133,Const(true))
    val x11136 = withCtrl(x11143) { FringeDenseLoad(dram=List(x10991), cmdStream=List(b12071, b12072), dataStream=List(x11127)).name("x11136").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // FringeDenseLoad(x10991,x11126,x11127)
    val x11137 = withCtrl(x11143) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x11137").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x11138 = withCtrl(x11143) { CounterChain(List(x11137)).name("x11138").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // CounterChainNew(List(x11137))
    val x11142 = withCtrl(x11143) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11138).name("x11142").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // UnrolledForeach(List(Const(true)),x11138,Block(Const(())),List(List(b5384)),List(List(b5385)))
    val b5384 = withCtrl(x11142) { CounterIter(x11137, None).name("b5384") } // b5384
    val b5385 = withCtrl(x11142) { Const(true).name("b5385") } // b5385
    val x11139_x11139 = withCtrl(x11142) { ReadMem(x11127).name("x11139_x11139").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // ParStreamRead(x11127,List(b5385))
    val x11140_x11140 = withCtrl(x11142) { x11139_x11139 } // VectorApply(x11139,0)
    val x11141 = withCtrl(x11142) { StoreBanks(List(List(x11125_d0_b0)), List(b5384), x11140_x11140).name("x11141").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:75:15") } // ParSRAMStore(x11125,List(List(b5384)),List(x11140),List(b5385))
    val x11144_d0_b0 = withCtrl(x12029) { SRAM(size=640, banking=Strided(banks=16, stride=1)).name("x11144_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:77:28:c5_SRAM") } // x11144 = SRAMNew(ArrayBuffer(Const(5), Const(128)))
    isAccum(x11144_d0_b0) = false
    bufferDepthOf(x11144_d0_b0) = 1
    staticDimsOf(x11144_d0_b0) = List(5, 128)
    val x11145 = withCtrl(x12029) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11145").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11146 = withCtrl(x12029) { CounterChain(List(x11145)).name("x11146").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // CounterChainNew(List(x11145))
    val x11168 = withCtrl(x12029) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11146).name("x11168").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // UnrolledForeach(List(Const(true)),x11146,Block(Const(())),List(List(b5394)),List(List(b5395)))
    val b5394 = withCtrl(x11168) { CounterIter(x11145, Some(0)).name("b5394") } // b5394
    val b5395 = withCtrl(x11168) { Const(true).name("b5395") } // b5395
    val b12075 = withCtrl(x11168) { StreamOut(field="offset").name("b12075").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // x11147 = StreamOutNew(BurstCmdBus)
    isAccum(b12075) = false
    bufferDepthOf(b12075) = 1
    val b12076 = withCtrl(x11168) { StreamOut(field="size").name("b12076").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // x11147 = StreamOutNew(BurstCmdBus)
    isAccum(b12076) = false
    bufferDepthOf(b12076) = 1
    val x11148 = withCtrl(x11168) { StreamIn(field="data").name("x11148").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // x11148 = StreamInNew(BurstDataBus())
    isAccum(x11148) = false
    bufferDepthOf(x11148) = 1
    val x11159 = withCtrl(x11168) { UnitController(style=SeqPipe, level=InnerControl).name("x11159").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // UnitPipe(List(b5395),Block(x11158))
    val x11149 = withCtrl(x11159) { b5394 } // FixConvert(b5394,TRUE,_32,_0) (Same Type. No op)
    val x11150 = withCtrl(x11159) { OpDef(op=FixMul, inputs=List(x11149, Const(5))).name("x11150").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // FixMul(x11149,Const(5))
    val x11151 = withCtrl(x11159) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11152 = withCtrl(x11159) { OpDef(op=FixAdd, inputs=List(x11150, x11151)).name("x11152").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // FixAdd(x11150,x11151)
    val x11153 = withCtrl(x11159) { OpDef(op=FixSla, inputs=List(x11152, Const(2))).name("x11153").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // FixLsh(x11152,Const(2))
    val x11154 = withCtrl(x11159) { x11153 } // FixConvert(x11153,TRUE,_64,_0)
    val x11155 = withCtrl(x11159) { DramAddress(x10993).name("x11155").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // GetDRAMAddress(x10993)
    val x11157_x11156 = withCtrl(x11159) { OpDef(op=FixAdd, inputs=List(x11154, x11155)).name("x11157_x11156").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // FixAdd(x11154,x11155)
    // x11157 = SimpleStruct(ArrayBuffer((offset,x11156), (size,Const(512)), (isLoad,Const(true))))
    val x11158_b12077_b12075 = withCtrl(x11159) { WriteMem(b12075, x11157_x11156).name("x11158_b12077_b12075").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // StreamWrite(x11147,x11157,b5395)
    val x11158_b12078_b12076 = withCtrl(x11159) { WriteMem(b12076, Const(512)).name("x11158_b12078_b12076").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // StreamWrite(x11147,x11157,b5395)
    val x11160 = withCtrl(x11168) { FringeDenseLoad(dram=List(x10993), cmdStream=List(b12075, b12076), dataStream=List(x11148)).name("x11160").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // FringeDenseLoad(x10993,x11147,x11148)
    val x11161 = withCtrl(x11168) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x11161").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x11162 = withCtrl(x11168) { CounterChain(List(x11161)).name("x11162").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // CounterChainNew(List(x11161))
    val x11167 = withCtrl(x11168) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11162).name("x11167").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // UnrolledForeach(List(b5395),x11162,Block(Const(())),List(List(b5412)),List(List(b5413)))
    val b5412 = withCtrl(x11167) { CounterIter(x11161, None).name("b5412") } // b5412
    val b5413 = withCtrl(x11167) { Const(true).name("b5413") } // b5413
    val x11163 = withCtrl(x11167) { OpDef(op=BitAnd, inputs=List(b5413, b5395)).name("x11163").srcCtx("UnrollingBase.scala:28:66") } // And(b5413,b5395)
    val x11164_x11164 = withCtrl(x11167) { ReadMem(x11148).name("x11164_x11164").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // ParStreamRead(x11148,List(x11163))
    val x11165_x11165 = withCtrl(x11167) { x11164_x11164 } // VectorApply(x11164,0)
    val x11166 = withCtrl(x11167) { StoreBanks(List(List(x11144_d0_b0)), List(b5394, b5412), x11165_x11165).name("x11166").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:78:15") } // ParSRAMStore(x11144,List(List(b5394, b5412)),List(x11165),List(x11163))
    val x11169_d0_b0 = withCtrl(x12029) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x11169_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:80:28:c7_SRAM") } // x11169 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x11169_d0_b0) = false
    bufferDepthOf(x11169_d0_b0) = 1
    staticDimsOf(x11169_d0_b0) = List(32)
    val x11187 = withCtrl(x12029) { UnitController(style=StreamPipe, level=OuterControl).name("x11187").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b12079 = withCtrl(x11187) { StreamOut(field="offset").name("b12079").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // x11170 = StreamOutNew(BurstCmdBus)
    isAccum(b12079) = false
    bufferDepthOf(b12079) = 1
    val b12080 = withCtrl(x11187) { StreamOut(field="size").name("b12080").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // x11170 = StreamOutNew(BurstCmdBus)
    isAccum(b12080) = false
    bufferDepthOf(b12080) = 1
    val x11171 = withCtrl(x11187) { StreamIn(field="data").name("x11171").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // x11171 = StreamInNew(BurstDataBus())
    isAccum(x11171) = false
    bufferDepthOf(x11171) = 1
    val x11179 = withCtrl(x11187) { UnitController(style=SeqPipe, level=InnerControl).name("x11179").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // UnitPipe(List(Const(true)),Block(x11178))
    val x11172 = withCtrl(x11179) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11173 = withCtrl(x11179) { OpDef(op=FixSla, inputs=List(x11172, Const(2))).name("x11173").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // FixLsh(x11172,Const(2))
    val x11174 = withCtrl(x11179) { x11173 } // FixConvert(x11173,TRUE,_64,_0)
    val x11175 = withCtrl(x11179) { DramAddress(x10995).name("x11175").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // GetDRAMAddress(x10995)
    val x11177_x11176 = withCtrl(x11179) { OpDef(op=FixAdd, inputs=List(x11174, x11175)).name("x11177_x11176").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // FixAdd(x11174,x11175)
    // x11177 = SimpleStruct(ArrayBuffer((offset,x11176), (size,Const(128)), (isLoad,Const(true))))
    val x11178_b12081_b12079 = withCtrl(x11179) { WriteMem(b12079, x11177_x11176).name("x11178_b12081_b12079").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // StreamWrite(x11170,x11177,Const(true))
    val x11178_b12082_b12080 = withCtrl(x11179) { WriteMem(b12080, Const(128)).name("x11178_b12082_b12080").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // StreamWrite(x11170,x11177,Const(true))
    val x11180 = withCtrl(x11187) { FringeDenseLoad(dram=List(x10995), cmdStream=List(b12079, b12080), dataStream=List(x11171)).name("x11180").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // FringeDenseLoad(x10995,x11170,x11171)
    val x11181 = withCtrl(x11187) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x11181").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x11182 = withCtrl(x11187) { CounterChain(List(x11181)).name("x11182").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // CounterChainNew(List(x11181))
    val x11186 = withCtrl(x11187) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11182).name("x11186").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // UnrolledForeach(List(Const(true)),x11182,Block(Const(())),List(List(b5434)),List(List(b5435)))
    val b5434 = withCtrl(x11186) { CounterIter(x11181, None).name("b5434") } // b5434
    val b5435 = withCtrl(x11186) { Const(true).name("b5435") } // b5435
    val x11183_x11183 = withCtrl(x11186) { ReadMem(x11171).name("x11183_x11183").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // ParStreamRead(x11171,List(b5435))
    val x11184_x11184 = withCtrl(x11186) { x11183_x11183 } // VectorApply(x11183,0)
    val x11185 = withCtrl(x11186) { StoreBanks(List(List(x11169_d0_b0)), List(b5434), x11184_x11184).name("x11185").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:81:15") } // ParSRAMStore(x11169,List(List(b5434)),List(x11184),List(b5435))
    val x11188 = withCtrl(x12029) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x11188").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:83:31") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x11189 = withCtrl(x12029) { CounterChain(List(x11188)).name("x11189").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:83:46") } // CounterChainNew(List(x11188))
    val x12028 = withCtrl(x12029) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11189).name("x12028").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:83:46") } // UnrolledForeach(List(Const(true)),x11189,Block(Const(())),List(List(b5443)),List(List(b5444)))
    val b5443 = withCtrl(x12028) { CounterIter(x11188, Some(0)).name("b5443") } // b5443
    val b5444 = withCtrl(x12028) { Const(true).name("b5444") } // b5444
    val x11190_d0_b0 = withCtrl(x12028) { SRAM(size=896, banking=Strided(banks=16, stride=1)).name("x11190_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:85:30:i0_SRAM") } // x11190 = SRAMNew(ArrayBuffer(Const(28), Const(32)))
    isAccum(x11190_d0_b0) = false
    bufferDepthOf(x11190_d0_b0) = 3
    staticDimsOf(x11190_d0_b0) = List(28, 32)
    val x11190_d1_b0 = withCtrl(x12028) { SRAM(size=896, banking=Strided(banks=16, stride=1)).name("x11190_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:85:30:i0_SRAM") } // x11190 = SRAMNew(ArrayBuffer(Const(28), Const(32)))
    isAccum(x11190_d1_b0) = false
    bufferDepthOf(x11190_d1_b0) = 3
    staticDimsOf(x11190_d1_b0) = List(28, 32)
    val x11192 = withCtrl(x12028) { UnitController(style=SeqPipe, level=InnerControl).name("x11192").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:83:46") } // UnitPipe(List(b5444),Block(Const(())))
    val x11191 = withCtrl(x11192) { OpDef(op=FixAdd, inputs=List(b5443, Const(1))).name("x11191").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:29") } // FixAdd(b5443,Const(1))
    val x11193 = withCtrl(x12028) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11193").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11194 = withCtrl(x12028) { Counter(min=Const(0), max=Const(28), step=Const(1), par=1).name("x11194").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // CounterNew(Const(0),Const(28),Const(1),Const(1))
    val x11195 = withCtrl(x12028) { CounterChain(List(x11193,x11194)).name("x11195").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // CounterChainNew(List(x11193, x11194))
    val x11225 = withCtrl(x12028) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11195).name("x11225").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // UnrolledForeach(List(b5444),x11195,Block(Const(())),List(List(b5451), List(b5452)),List(List(b5453), List(b5454)))
    val b5451 = withCtrl(x11225) { CounterIter(x11193, Some(0)).name("b5451") } // b5451
    val b5453 = withCtrl(x11225) { Const(true).name("b5453") } // b5453
    val b5452 = withCtrl(x11225) { CounterIter(x11194, Some(0)).name("b5452") } // b5452
    val b5454 = withCtrl(x11225) { Const(true).name("b5454") } // b5454
    val b12083 = withCtrl(x11225) { StreamOut(field="offset").name("b12083").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // x11196 = StreamOutNew(BurstCmdBus)
    isAccum(b12083) = false
    bufferDepthOf(b12083) = 1
    val b12084 = withCtrl(x11225) { StreamOut(field="size").name("b12084").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // x11196 = StreamOutNew(BurstCmdBus)
    isAccum(b12084) = false
    bufferDepthOf(b12084) = 1
    val x11197 = withCtrl(x11225) { StreamIn(field="data").name("x11197").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // x11197 = StreamInNew(BurstDataBus())
    isAccum(x11197) = false
    bufferDepthOf(x11197) = 1
    val x11214 = withCtrl(x11225) { UnitController(style=SeqPipe, level=InnerControl).name("x11214").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // UnitPipe(List(b5453, b5454, b5444),Block(x11213))
    val x11198 = withCtrl(x11214) { OpDef(op=FixAdd, inputs=List(b5443, b5451)).name("x11198").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixAdd(b5443,b5451)
    val x11199 = withCtrl(x11214) { x11198 } // FixConvert(x11198,TRUE,_32,_0) (Same Type. No op)
    val x11200 = withCtrl(x11214) { OpDef(op=FixMul, inputs=List(x11199, Const(896))).name("x11200").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixMul(x11199,Const(896))
    val x11201 = withCtrl(x11214) { b5452 } // FixConvert(b5452,TRUE,_32,_0) (Same Type. No op)
    val x11202 = withCtrl(x11214) { OpDef(op=FixSla, inputs=List(x11201, Const(5))).name("x11202").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixLsh(x11201,Const(5))
    val x11203 = withCtrl(x11214) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11204 = withCtrl(x11214) { OpDef(op=FixAdd, inputs=List(x11200, x11202)).name("x11204").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixAdd(x11200,x11202)
    val x11205 = withCtrl(x11214) { OpDef(op=FixAdd, inputs=List(x11204, x11203)).name("x11205").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixAdd(x11204,x11203)
    val x11206 = withCtrl(x11214) { OpDef(op=FixSla, inputs=List(x11205, Const(2))).name("x11206").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixLsh(x11205,Const(2))
    val x11207 = withCtrl(x11214) { x11206 } // FixConvert(x11206,TRUE,_64,_0)
    val x11208 = withCtrl(x11214) { DramAddress(x10988).name("x11208").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // GetDRAMAddress(x10988)
    val x11210_x11209 = withCtrl(x11214) { OpDef(op=FixAdd, inputs=List(x11207, x11208)).name("x11210_x11209").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FixAdd(x11207,x11208)
    // x11210 = SimpleStruct(ArrayBuffer((offset,x11209), (size,Const(128)), (isLoad,Const(true))))
    val x11211 = withCtrl(x11214) { OpDef(op=BitAnd, inputs=List(b5453, b5454)).name("x11211").srcCtx("UnrollingBase.scala:28:66") } // And(b5453,b5454)
    val x11212 = withCtrl(x11214) { OpDef(op=BitAnd, inputs=List(x11211, b5444)).name("x11212").srcCtx("UnrollingBase.scala:28:66") } // And(x11211,b5444)
    val x11213_b12085_b12083 = withCtrl(x11214) { WriteMem(b12083, x11210_x11209).name("x11213_b12085_b12083").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // StreamWrite(x11196,x11210,x11212)
    val x11213_b12086_b12084 = withCtrl(x11214) { WriteMem(b12084, Const(128)).name("x11213_b12086_b12084").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // StreamWrite(x11196,x11210,x11212)
    val x11215 = withCtrl(x11225) { FringeDenseLoad(dram=List(x10988), cmdStream=List(b12083, b12084), dataStream=List(x11197)).name("x11215").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // FringeDenseLoad(x10988,x11196,x11197)
    val x11216 = withCtrl(x11225) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x11216").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x11217 = withCtrl(x11225) { CounterChain(List(x11216)).name("x11217").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // CounterChainNew(List(x11216))
    val x11224 = withCtrl(x11225) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11217).name("x11224").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // UnrolledForeach(List(b5453, b5454, b5444),x11217,Block(Const(())),List(List(b5477)),List(List(b5478)))
    val b5477 = withCtrl(x11224) { CounterIter(x11216, None).name("b5477") } // b5477
    val b5478 = withCtrl(x11224) { Const(true).name("b5478") } // b5478
    val x11218 = withCtrl(x11224) { OpDef(op=BitAnd, inputs=List(b5478, b5453)).name("x11218").srcCtx("UnrollingBase.scala:28:66") } // And(b5478,b5453)
    val x11219 = withCtrl(x11224) { OpDef(op=BitAnd, inputs=List(b5454, b5444)).name("x11219").srcCtx("UnrollingBase.scala:28:66") } // And(b5454,b5444)
    val x11220 = withCtrl(x11224) { OpDef(op=BitAnd, inputs=List(x11218, x11219)).name("x11220").srcCtx("UnrollingBase.scala:28:66") } // And(x11218,x11219)
    val x11221_x11221 = withCtrl(x11224) { ReadMem(x11197).name("x11221_x11221").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // ParStreamRead(x11197,List(x11220))
    val x11222_x11222 = withCtrl(x11224) { x11221_x11221 } // VectorApply(x11221,0)
    val x11223 = withCtrl(x11224) { StoreBanks(List(List(x11190_d0_b0), List(x11190_d1_b0)), List(b5452, b5477), x11222_x11222).name("x11223").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:86:17") } // ParSRAMStore(x11190,List(List(b5452, b5477)),List(x11222),List(x11220))
    val x11226_d0_b0 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d0_b0) = false
    bufferDepthOf(x11226_d0_b0) = 3
    staticDimsOf(x11226_d0_b0) = List(20, 12, 12)
    val x11226_d0_b1 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d0_b1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d0_b1) = false
    bufferDepthOf(x11226_d0_b1) = 3
    staticDimsOf(x11226_d0_b1) = List(20, 12, 12)
    val x11226_d1_b0 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d1_b0) = false
    bufferDepthOf(x11226_d1_b0) = 3
    staticDimsOf(x11226_d1_b0) = List(20, 12, 12)
    val x11226_d1_b1 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d1_b1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d1_b1) = false
    bufferDepthOf(x11226_d1_b1) = 3
    staticDimsOf(x11226_d1_b1) = List(20, 12, 12)
    val x11226_d2_b0 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d2_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d2_b0) = false
    bufferDepthOf(x11226_d2_b0) = 3
    staticDimsOf(x11226_d2_b0) = List(20, 12, 12)
    val x11226_d2_b1 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d2_b1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d2_b1) = false
    bufferDepthOf(x11226_d2_b1) = 3
    staticDimsOf(x11226_d2_b1) = List(20, 12, 12)
    val x11226_d3_b0 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d3_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d3_b0) = false
    bufferDepthOf(x11226_d3_b0) = 3
    staticDimsOf(x11226_d3_b0) = List(20, 12, 12)
    val x11226_d3_b1 = withCtrl(x12028) { SRAM(size=1440, banking=Strided(banks=1, stride=1)).name("x11226_d3_b1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:88:32:tmp1_SRAM") } // x11226 = SRAMNew(ArrayBuffer(Const(20), Const(12), Const(12)))
    isAccum(x11226_d3_b1) = false
    bufferDepthOf(x11226_d3_b1) = 3
    staticDimsOf(x11226_d3_b1) = List(20, 12, 12)
    val x11227_d0_b0 = withCtrl(x12028) { SRAM(size=800, banking=Strided(banks=16, stride=1)).name("x11227_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:89:28:c0_RF") } // x11227 = SRAMNew(ArrayBuffer(Const(20), Const(5), Const(16)))
    isAccum(x11227_d0_b0) = false
    bufferDepthOf(x11227_d0_b0) = 2
    staticDimsOf(x11227_d0_b0) = List(20, 5, 16)
    val x11227_d0_b1 = withCtrl(x12028) { SRAM(size=800, banking=Strided(banks=16, stride=1)).name("x11227_d0_b1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:89:28:c0_RF") } // x11227 = SRAMNew(ArrayBuffer(Const(20), Const(5), Const(16)))
    isAccum(x11227_d0_b1) = false
    bufferDepthOf(x11227_d0_b1) = 2
    staticDimsOf(x11227_d0_b1) = List(20, 5, 16)
    val x11228 = withCtrl(x12028) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x11228").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x11229 = withCtrl(x12028) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11229").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11230 = withCtrl(x12028) { CounterChain(List(x11228,x11229)).name("x11230").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // CounterChainNew(List(x11228, x11229))
    val x11259 = withCtrl(x12028) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11230).name("x11259").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // UnrolledForeach(List(b5444),x11230,Block(Const(())),List(List(b5492), List(b5493)),List(List(b5494), List(b5495)))
    val b5492 = withCtrl(x11259) { CounterIter(x11228, Some(0)).name("b5492") } // b5492
    val b5494 = withCtrl(x11259) { Const(true).name("b5494") } // b5494
    val b5493 = withCtrl(x11259) { CounterIter(x11229, Some(0)).name("b5493") } // b5493
    val b5495 = withCtrl(x11259) { Const(true).name("b5495") } // b5495
    val b12087 = withCtrl(x11259) { StreamOut(field="offset").name("b12087").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // x11231 = StreamOutNew(BurstCmdBus)
    isAccum(b12087) = false
    bufferDepthOf(b12087) = 1
    val b12088 = withCtrl(x11259) { StreamOut(field="size").name("b12088").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // x11231 = StreamOutNew(BurstCmdBus)
    isAccum(b12088) = false
    bufferDepthOf(b12088) = 1
    val x11232 = withCtrl(x11259) { StreamIn(field="data").name("x11232").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // x11232 = StreamInNew(BurstDataBus())
    isAccum(x11232) = false
    bufferDepthOf(x11232) = 1
    val x11248 = withCtrl(x11259) { UnitController(style=SeqPipe, level=InnerControl).name("x11248").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // UnitPipe(List(b5494, b5495, b5444),Block(x11247))
    val x11233 = withCtrl(x11248) { b5492 } // FixConvert(b5492,TRUE,_32,_0) (Same Type. No op)
    val x11234 = withCtrl(x11248) { OpDef(op=FixMul, inputs=List(x11233, Const(80))).name("x11234").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FixMul(x11233,Const(80))
    val x11235 = withCtrl(x11248) { b5493 } // FixConvert(b5493,TRUE,_32,_0) (Same Type. No op)
    val x11236 = withCtrl(x11248) { OpDef(op=FixSla, inputs=List(x11235, Const(4))).name("x11236").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FixLsh(x11235,Const(4))
    val x11237 = withCtrl(x11248) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11238 = withCtrl(x11248) { OpDef(op=FixAdd, inputs=List(x11234, x11236)).name("x11238").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FixAdd(x11234,x11236)
    val x11239 = withCtrl(x11248) { OpDef(op=FixAdd, inputs=List(x11238, x11237)).name("x11239").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FixAdd(x11238,x11237)
    val x11240 = withCtrl(x11248) { OpDef(op=FixSla, inputs=List(x11239, Const(2))).name("x11240").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FixLsh(x11239,Const(2))
    val x11241 = withCtrl(x11248) { x11240 } // FixConvert(x11240,TRUE,_64,_0)
    val x11242 = withCtrl(x11248) { DramAddress(x10987).name("x11242").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // GetDRAMAddress(x10987)
    val x11244_x11243 = withCtrl(x11248) { OpDef(op=FixAdd, inputs=List(x11241, x11242)).name("x11244_x11243").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FixAdd(x11241,x11242)
    // x11244 = SimpleStruct(ArrayBuffer((offset,x11243), (size,Const(64)), (isLoad,Const(true))))
    val x11245 = withCtrl(x11248) { OpDef(op=BitAnd, inputs=List(b5494, b5495)).name("x11245").srcCtx("UnrollingBase.scala:28:66") } // And(b5494,b5495)
    val x11246 = withCtrl(x11248) { OpDef(op=BitAnd, inputs=List(x11245, b5444)).name("x11246").srcCtx("UnrollingBase.scala:28:66") } // And(x11245,b5444)
    val x11247_b12089_b12087 = withCtrl(x11248) { WriteMem(b12087, x11244_x11243).name("x11247_b12089_b12087").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // StreamWrite(x11231,x11244,x11246)
    val x11247_b12090_b12088 = withCtrl(x11248) { WriteMem(b12088, Const(64)).name("x11247_b12090_b12088").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // StreamWrite(x11231,x11244,x11246)
    val x11249 = withCtrl(x11259) { FringeDenseLoad(dram=List(x10987), cmdStream=List(b12087, b12088), dataStream=List(x11232)).name("x11249").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // FringeDenseLoad(x10987,x11231,x11232)
    val x11250 = withCtrl(x11259) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x11250").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x11251 = withCtrl(x11259) { CounterChain(List(x11250)).name("x11251").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // CounterChainNew(List(x11250))
    val x11258 = withCtrl(x11259) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11251).name("x11258").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // UnrolledForeach(List(b5494, b5495, b5444),x11251,Block(Const(())),List(List(b5517)),List(List(b5518)))
    val b5517 = withCtrl(x11258) { CounterIter(x11250, None).name("b5517") } // b5517
    val b5518 = withCtrl(x11258) { Const(true).name("b5518") } // b5518
    val x11252 = withCtrl(x11258) { OpDef(op=BitAnd, inputs=List(b5518, b5494)).name("x11252").srcCtx("UnrollingBase.scala:28:66") } // And(b5518,b5494)
    val x11253 = withCtrl(x11258) { OpDef(op=BitAnd, inputs=List(b5495, b5444)).name("x11253").srcCtx("UnrollingBase.scala:28:66") } // And(b5495,b5444)
    val x11254 = withCtrl(x11258) { OpDef(op=BitAnd, inputs=List(x11252, x11253)).name("x11254").srcCtx("UnrollingBase.scala:28:66") } // And(x11252,x11253)
    val x11255_x11255 = withCtrl(x11258) { ReadMem(x11232).name("x11255_x11255").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // ParStreamRead(x11232,List(x11254))
    val x11256_x11256 = withCtrl(x11258) { x11255_x11255 } // VectorApply(x11255,0)
    val x11257 = withCtrl(x11258) { StoreBanks(List(List(x11227_d0_b0, x11227_d0_b1)), List(b5492, b5493, b5517), x11256_x11256).name("x11257").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:90:15") } // ParSRAMStore(x11227,List(List(b5492, b5493, b5517)),List(x11256),List(x11254))
    val x11260 = withCtrl(x12028) { Counter(min=Const(0), max=Const(20), step=Const(1), par=2).name("x11260").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:91:25") } // CounterNew(Const(0),Const(20),Const(1),Const(2))
    val x11261 = withCtrl(x12028) { CounterChain(List(x11260)).name("x11261").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:91:40") } // CounterChainNew(List(x11260))
    val x11420 = withCtrl(x12028) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11261).name("x11420").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:91:40") } // UnrolledForeach(List(b5444),x11261,Block(Const(())),List(List(b5529, b5530)),List(List(b5531, b5532)))
    val b5529 = withCtrl(x11420) { CounterIter(x11260, Some(0)).name("b5529") } // b5529
    val b5531 = withCtrl(x11420) { Const(true).name("b5531") } // b5531
    val b5530 = withCtrl(x11420) { CounterIter(x11260, Some(1)).name("b5530") } // b5530
    val b5532 = withCtrl(x11420) { Const(true).name("b5532") } // b5532
    val x11262_d0_b0 = withCtrl(x11420) { SRAM(size=576, banking=Strided(banks=2, stride=1)).name("x11262_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:98:39:tmp1_SRAM_conv") } // x11262 = SRAMNew(ArrayBuffer(Const(12), Const(2), Const(12), Const(2)))
    isAccum(x11262_d0_b0) = false
    bufferDepthOf(x11262_d0_b0) = 2
    staticDimsOf(x11262_d0_b0) = List(12, 2, 12, 2)
    val x11263_d0_b0 = withCtrl(x11420) { SRAM(size=576, banking=Strided(banks=2, stride=1)).name("x11263_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:98:39:tmp1_SRAM_conv") } // x11263 = SRAMNew(ArrayBuffer(Const(12), Const(2), Const(12), Const(2)))
    isAccum(x11263_d0_b0) = false
    bufferDepthOf(x11263_d0_b0) = 2
    staticDimsOf(x11263_d0_b0) = List(12, 2, 12, 2)
    val x11354 = withCtrl(x11420) { UnitController(style=ForkJoin, level=OuterControl).name("x11354").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b5444),Block(Const(())))
    val x11264 = withCtrl(x11354) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x11264").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:38") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x11265 = withCtrl(x11354) { Counter(min=Const(0), max=Const(24), step=Const(2), par=1).name("x11265").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:30") } // CounterNew(Const(0),Const(24),Const(2),Const(1))
    val x11266 = withCtrl(x11354) { CounterChain(List(x11265,x11264)).name("x11266").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:44") } // CounterChainNew(List(x11265, x11264))
    val x11308 = withCtrl(x11354) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11266).name("x11308").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:44") } // UnrolledForeach(List(b5531, b5444),x11266,Block(Const(())),List(List(b5541), List(b5542)),List(List(b5543), List(b5544)))
    val b5541 = withCtrl(x11308) { CounterIter(x11265, Some(0)).name("b5541") } // b5541
    val b5543 = withCtrl(x11308) { Const(true).name("b5543") } // b5543
    val b5542 = withCtrl(x11308) { CounterIter(x11264, Some(0)).name("b5542") } // b5542
    val b5544 = withCtrl(x11308) { Const(true).name("b5544") } // b5544
    val x11267 = withCtrl(x11308) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x11267").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:40") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x11268 = withCtrl(x11308) { Counter(min=Const(0), max=Const(24), step=Const(2), par=1).name("x11268").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:32") } // CounterNew(Const(0),Const(24),Const(2),Const(1))
    val x11269 = withCtrl(x11308) { CounterChain(List(x11268,x11267)).name("x11269").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:46") } // CounterChainNew(List(x11268, x11267))
    val x11307 = withCtrl(x11308) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11269).name("x11307").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:46") } // UnrolledForeach(List(b5543, b5544, b5531, b5444),x11269,Block(Const(())),List(List(b5548), List(b5549)),List(List(b5550), List(b5551)))
    val b5548 = withCtrl(x11307) { CounterIter(x11268, Some(0)).name("b5548") } // b5548
    val b5550 = withCtrl(x11307) { Const(true).name("b5550") } // b5550
    val b5549 = withCtrl(x11307) { CounterIter(x11267, Some(0)).name("b5549") } // b5549
    val b5551 = withCtrl(x11307) { Const(true).name("b5551") } // b5551
    val x11270_d0 = withCtrl(x11307) { Reg(init=Some(0.0)).name("x11270_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:41:window") } // x11270 = RegNew(Const(0))
    isAccum(x11270_d0) = false
    bufferDepthOf(x11270_d0) = 2
    val x11270_d1 = withCtrl(x11307) { Reg(init=Some(0.0)).name("x11270_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:41:window") } // x11270 = RegNew(Const(0))
    isAccum(x11270_d1) = true
    bufferDepthOf(x11270_d1) = 1
    val x11271 = withCtrl(x11307) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x11271").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:75") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x11272 = withCtrl(x11307) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11272").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:54") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11273 = withCtrl(x11307) { CounterChain(List(x11272,x11271)).name("x11273").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // CounterChainNew(List(x11272, x11271))
    val x11298 = withCtrl(x11307) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11273).name("x11298").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // UnrolledReduce(List(b5550, b5551, b5543, b5544, b5531, b5444),x11273,x11270,Block((x11270) => Const(())),List(List(b5556), List(b5557)),List(List(b5558), List(b5559)))
    val b5556 = withCtrl(x11298) { CounterIter(x11272, Some(0)).name("b5556") } // b5556
    val b5558 = withCtrl(x11298) { Const(true).name("b5558") } // b5558
    val b5557 = withCtrl(x11298) { CounterIter(x11271, None).name("b5557") } // b5557
    val b5559 = withCtrl(x11298) { Const(true).name("b5559") } // b5559
    val x11274 = withCtrl(x11298) { OpDef(op=FixAdd, inputs=List(b5541, b5542)).name("x11274").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:27") } // FixAdd(b5541,b5542)
    val x11275 = withCtrl(x11298) { OpDef(op=FixAdd, inputs=List(x11274, b5556)).name("x11275").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:30") } // FixAdd(x11274,b5556)
    val x11276 = withCtrl(x11298) { OpDef(op=FixAdd, inputs=List(b5548, b5549)).name("x11276").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:35") } // FixAdd(b5548,b5549)
    val x11277 = withCtrl(x11298) { OpDef(op=FixAdd, inputs=List(x11276, b5557)).name("x11277").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:38") } // FixAdd(x11276,b5557)
    val x11278 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(b5558, b5559)).name("x11278").srcCtx("UnrollingBase.scala:28:66") } // And(b5558,b5559)
    val x11279 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(b5550, b5551)).name("x11279").srcCtx("UnrollingBase.scala:28:66") } // And(b5550,b5551)
    val x11280 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(b5543, b5544)).name("x11280").srcCtx("UnrollingBase.scala:28:66") } // And(b5543,b5544)
    val x11281 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(b5531, b5444)).name("x11281").srcCtx("UnrollingBase.scala:28:66") } // And(b5531,b5444)
    val x11282 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(x11278, x11279)).name("x11282").srcCtx("UnrollingBase.scala:28:66") } // And(x11278,x11279)
    val x11283 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(x11280, x11281)).name("x11283").srcCtx("UnrollingBase.scala:28:66") } // And(x11280,x11281)
    val x11284 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(x11282, x11283)).name("x11284").srcCtx("UnrollingBase.scala:28:66") } // And(x11282,x11283)
    val x11285 = withCtrl(x11298) { LoadBanks(List(x11190_d0_b0), List(x11275, x11277)).name("x11285").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:24") } // ParSRAMLoad(x11190,List(List(x11275, x11277)),List(x11284))
    val x11286 = withCtrl(x11298) { x11285 } // VectorApply(x11285,0)
    val x11287 = withCtrl(x11298) { LoadBanks(List(x11227_d0_b0), List(b5529, b5556, b5557)).name("x11287").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:49") } // ParSRAMLoad(x11227,List(List(b5529, b5556, b5557)),List(x11284))
    val x11288 = withCtrl(x11298) { x11287 } // VectorApply(x11287,0)
    val x11289 = withCtrl(x11298) { OpDef(op=FixMul, inputs=List(x11286, x11288)).name("x11289").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:42") } // FixMul(x11286,x11288)
    val x11290 = withCtrl(x11298) { ReadMem(x11270_d1).name("x11290").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // RegRead(x11270)
    val x11291 = withCtrl(x11298) { OpDef(op=FixEql, inputs=List(b5556, Const(0))).name("x11291").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // FixEql(b5556,Const(0))
    val x11292 = withCtrl(x11298) { OpDef(op=FixEql, inputs=List(b5557, Const(0))).name("x11292").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // FixEql(b5557,Const(0))
    val x11293 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(x11291, x11292)).name("x11293").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // And(x11291,x11292)
    val x11294 = withCtrl(x11298) { ReduceAccumOp(op=FixAdd, input=x11289, accum=x11290).name("x11294").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:18") } // FixAdd(x11289,x11290)
    val x11295 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(x11279, x11280)).name("x11295").srcCtx("UnrollingBase.scala:28:66") } // And(x11279,x11280)
    val x11296 = withCtrl(x11298) { OpDef(op=BitAnd, inputs=List(x11295, x11281)).name("x11296").srcCtx("UnrollingBase.scala:28:66") } // And(x11295,x11281)
    val x11297_x11270_d0 = withCtrl(x11298) { WriteMem(x11270_d0, x11294).name("x11297_x11270_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // RegWrite(x11270,x11294,x11296)
    antiDepsOf(x11297_x11270_d0)=List(x11290)
    val x11297_x11270_d1 = withCtrl(x11298) { WriteMem(x11270_d1, x11294).name("x11297_x11270_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // RegWrite(x11270,x11294,x11296)
    antiDepsOf(x11297_x11270_d1)=List(x11290)
    val x11306 = withCtrl(x11307) { UnitController(style=SeqPipe, level=InnerControl).name("x11306").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:46") } // UnitPipe(List(b5550, b5551, b5543, b5544, b5531, b5444),Block(Const(())))
    val x11299 = withCtrl(x11306) { ReadMem(x11270_d0).name("x11299").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:104:52") } // RegRead(x11270)
    val x11300 = withCtrl(x11306) { OpDef(op=BitAnd, inputs=List(b5550, b5551)).name("x11300").srcCtx("UnrollingBase.scala:28:66") } // And(b5550,b5551)
    val x11301 = withCtrl(x11306) { OpDef(op=BitAnd, inputs=List(b5543, b5544)).name("x11301").srcCtx("UnrollingBase.scala:28:66") } // And(b5543,b5544)
    val x11302 = withCtrl(x11306) { OpDef(op=BitAnd, inputs=List(b5531, b5444)).name("x11302").srcCtx("UnrollingBase.scala:28:66") } // And(b5531,b5444)
    val x11303 = withCtrl(x11306) { OpDef(op=BitAnd, inputs=List(x11300, x11301)).name("x11303").srcCtx("UnrollingBase.scala:28:66") } // And(x11300,x11301)
    val x11304 = withCtrl(x11306) { OpDef(op=BitAnd, inputs=List(x11303, x11302)).name("x11304").srcCtx("UnrollingBase.scala:28:66") } // And(x11303,x11302)
    val x11305 = withCtrl(x11306) { StoreBanks(List(List(x11262_d0_b0)), List(b5541, b5542, b5548, b5549), x11299).name("x11305").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:104:43") } // SRAMStore(x11262,ArrayBuffer(Const(12), Const(2), Const(12), Const(2)),List(b5541, b5542, b5548, b5549),Const(0),x11299,x11304)
    val x11309 = withCtrl(x11354) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x11309").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:38") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x11310 = withCtrl(x11354) { Counter(min=Const(0), max=Const(24), step=Const(2), par=1).name("x11310").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:30") } // CounterNew(Const(0),Const(24),Const(2),Const(1))
    val x11311 = withCtrl(x11354) { CounterChain(List(x11310,x11309)).name("x11311").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:44") } // CounterChainNew(List(x11310, x11309))
    val x11353 = withCtrl(x11354) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11311).name("x11353").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:99:44") } // UnrolledForeach(List(b5532, b5444),x11311,Block(Const(())),List(List(b5595), List(b5596)),List(List(b5597), List(b5598)))
    val b5595 = withCtrl(x11353) { CounterIter(x11310, Some(0)).name("b5595") } // b5595
    val b5597 = withCtrl(x11353) { Const(true).name("b5597") } // b5597
    val b5596 = withCtrl(x11353) { CounterIter(x11309, Some(0)).name("b5596") } // b5596
    val b5598 = withCtrl(x11353) { Const(true).name("b5598") } // b5598
    val x11312 = withCtrl(x11353) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x11312").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:40") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x11313 = withCtrl(x11353) { Counter(min=Const(0), max=Const(24), step=Const(2), par=1).name("x11313").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:32") } // CounterNew(Const(0),Const(24),Const(2),Const(1))
    val x11314 = withCtrl(x11353) { CounterChain(List(x11313,x11312)).name("x11314").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:46") } // CounterChainNew(List(x11313, x11312))
    val x11352 = withCtrl(x11353) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11314).name("x11352").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:46") } // UnrolledForeach(List(b5597, b5598, b5532, b5444),x11314,Block(Const(())),List(List(b5602), List(b5603)),List(List(b5604), List(b5605)))
    val b5602 = withCtrl(x11352) { CounterIter(x11313, Some(0)).name("b5602") } // b5602
    val b5604 = withCtrl(x11352) { Const(true).name("b5604") } // b5604
    val b5603 = withCtrl(x11352) { CounterIter(x11312, Some(0)).name("b5603") } // b5603
    val b5605 = withCtrl(x11352) { Const(true).name("b5605") } // b5605
    val x11315_d0 = withCtrl(x11352) { Reg(init=Some(0.0)).name("x11315_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:41:window") } // x11315 = RegNew(Const(0))
    isAccum(x11315_d0) = false
    bufferDepthOf(x11315_d0) = 2
    val x11315_d1 = withCtrl(x11352) { Reg(init=Some(0.0)).name("x11315_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:41:window") } // x11315 = RegNew(Const(0))
    isAccum(x11315_d1) = true
    bufferDepthOf(x11315_d1) = 1
    val x11316 = withCtrl(x11352) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x11316").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:75") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x11317 = withCtrl(x11352) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11317").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:101:54") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11318 = withCtrl(x11352) { CounterChain(List(x11317,x11316)).name("x11318").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // CounterChainNew(List(x11317, x11316))
    val x11343 = withCtrl(x11352) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11318).name("x11343").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // UnrolledReduce(List(b5604, b5605, b5597, b5598, b5532, b5444),x11318,x11315,Block((x11315) => Const(())),List(List(b5610), List(b5611)),List(List(b5612), List(b5613)))
    val b5610 = withCtrl(x11343) { CounterIter(x11317, Some(0)).name("b5610") } // b5610
    val b5612 = withCtrl(x11343) { Const(true).name("b5612") } // b5612
    val b5611 = withCtrl(x11343) { CounterIter(x11316, None).name("b5611") } // b5611
    val b5613 = withCtrl(x11343) { Const(true).name("b5613") } // b5613
    val x11319 = withCtrl(x11343) { OpDef(op=FixAdd, inputs=List(b5595, b5596)).name("x11319").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:27") } // FixAdd(b5595,b5596)
    val x11320 = withCtrl(x11343) { OpDef(op=FixAdd, inputs=List(x11319, b5610)).name("x11320").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:30") } // FixAdd(x11319,b5610)
    val x11321 = withCtrl(x11343) { OpDef(op=FixAdd, inputs=List(b5602, b5603)).name("x11321").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:35") } // FixAdd(b5602,b5603)
    val x11322 = withCtrl(x11343) { OpDef(op=FixAdd, inputs=List(x11321, b5611)).name("x11322").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:38") } // FixAdd(x11321,b5611)
    val x11323 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(b5612, b5613)).name("x11323").srcCtx("UnrollingBase.scala:28:66") } // And(b5612,b5613)
    val x11324 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(b5604, b5605)).name("x11324").srcCtx("UnrollingBase.scala:28:66") } // And(b5604,b5605)
    val x11325 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(b5597, b5598)).name("x11325").srcCtx("UnrollingBase.scala:28:66") } // And(b5597,b5598)
    val x11326 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(b5532, b5444)).name("x11326").srcCtx("UnrollingBase.scala:28:66") } // And(b5532,b5444)
    val x11327 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(x11323, x11324)).name("x11327").srcCtx("UnrollingBase.scala:28:66") } // And(x11323,x11324)
    val x11328 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(x11325, x11326)).name("x11328").srcCtx("UnrollingBase.scala:28:66") } // And(x11325,x11326)
    val x11329 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(x11327, x11328)).name("x11329").srcCtx("UnrollingBase.scala:28:66") } // And(x11327,x11328)
    val x11330 = withCtrl(x11343) { LoadBanks(List(x11190_d1_b0), List(x11320, x11322)).name("x11330").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:24") } // ParSRAMLoad(x11190,List(List(x11320, x11322)),List(x11329))
    val x11331 = withCtrl(x11343) { x11330 } // VectorApply(x11330,0)
    val x11332 = withCtrl(x11343) { LoadBanks(List(x11227_d0_b1), List(b5530, b5610, b5611)).name("x11332").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:49") } // ParSRAMLoad(x11227,List(List(b5530, b5610, b5611)),List(x11329))
    val x11333 = withCtrl(x11343) { x11332 } // VectorApply(x11332,0)
    val x11334 = withCtrl(x11343) { OpDef(op=FixMul, inputs=List(x11331, x11333)).name("x11334").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:102:42") } // FixMul(x11331,x11333)
    val x11335 = withCtrl(x11343) { ReadMem(x11315_d1).name("x11335").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // RegRead(x11315)
    val x11336 = withCtrl(x11343) { OpDef(op=FixEql, inputs=List(b5610, Const(0))).name("x11336").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // FixEql(b5610,Const(0))
    val x11337 = withCtrl(x11343) { OpDef(op=FixEql, inputs=List(b5611, Const(0))).name("x11337").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // FixEql(b5611,Const(0))
    val x11338 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(x11336, x11337)).name("x11338").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // And(x11336,x11337)
    val x11339 = withCtrl(x11343) { ReduceAccumOp(op=FixAdd, input=x11334, accum=x11335).name("x11339").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:18") } // FixAdd(x11334,x11335)
    val x11340 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(x11324, x11325)).name("x11340").srcCtx("UnrollingBase.scala:28:66") } // And(x11324,x11325)
    val x11341 = withCtrl(x11343) { OpDef(op=BitAnd, inputs=List(x11340, x11326)).name("x11341").srcCtx("UnrollingBase.scala:28:66") } // And(x11340,x11326)
    val x11342_x11315_d0 = withCtrl(x11343) { WriteMem(x11315_d0, x11339).name("x11342_x11315_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // RegWrite(x11315,x11339,x11341)
    antiDepsOf(x11342_x11315_d0)=List(x11335)
    val x11342_x11315_d1 = withCtrl(x11343) { WriteMem(x11315_d1, x11339).name("x11342_x11315_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:103:16") } // RegWrite(x11315,x11339,x11341)
    antiDepsOf(x11342_x11315_d1)=List(x11335)
    val x11351 = withCtrl(x11352) { UnitController(style=SeqPipe, level=InnerControl).name("x11351").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:100:46") } // UnitPipe(List(b5604, b5605, b5597, b5598, b5532, b5444),Block(Const(())))
    val x11344 = withCtrl(x11351) { ReadMem(x11315_d0).name("x11344").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:104:52") } // RegRead(x11315)
    val x11345 = withCtrl(x11351) { OpDef(op=BitAnd, inputs=List(b5604, b5605)).name("x11345").srcCtx("UnrollingBase.scala:28:66") } // And(b5604,b5605)
    val x11346 = withCtrl(x11351) { OpDef(op=BitAnd, inputs=List(b5597, b5598)).name("x11346").srcCtx("UnrollingBase.scala:28:66") } // And(b5597,b5598)
    val x11347 = withCtrl(x11351) { OpDef(op=BitAnd, inputs=List(b5532, b5444)).name("x11347").srcCtx("UnrollingBase.scala:28:66") } // And(b5532,b5444)
    val x11348 = withCtrl(x11351) { OpDef(op=BitAnd, inputs=List(x11345, x11346)).name("x11348").srcCtx("UnrollingBase.scala:28:66") } // And(x11345,x11346)
    val x11349 = withCtrl(x11351) { OpDef(op=BitAnd, inputs=List(x11348, x11347)).name("x11349").srcCtx("UnrollingBase.scala:28:66") } // And(x11348,x11347)
    val x11350 = withCtrl(x11351) { StoreBanks(List(List(x11263_d0_b0)), List(b5595, b5596, b5602, b5603), x11344).name("x11350").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:104:43") } // SRAMStore(x11263,ArrayBuffer(Const(12), Const(2), Const(12), Const(2)),List(b5595, b5596, b5602, b5603),Const(0),x11344,x11349)
    val x11419 = withCtrl(x11420) { UnitController(style=ForkJoin, level=OuterControl).name("x11419").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b5444),Block(Const(())))
    val x11355 = withCtrl(x11419) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x11355").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:31") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x11356 = withCtrl(x11419) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x11356").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:22") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x11357 = withCtrl(x11419) { CounterChain(List(x11356,x11355)).name("x11357").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:37") } // CounterChainNew(List(x11356, x11355))
    val x11386 = withCtrl(x11419) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11357).name("x11386").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:37") } // UnrolledForeach(List(b5531, b5444),x11357,Block(Const(())),List(List(b5656), List(b5657)),List(List(b5658), List(b5659)))
    val b5656 = withCtrl(x11386) { CounterIter(x11356, Some(0)).name("b5656") } // b5656
    val b5658 = withCtrl(x11386) { Const(true).name("b5658") } // b5658
    val b5657 = withCtrl(x11386) { CounterIter(x11355, Some(0)).name("b5657") } // b5657
    val b5659 = withCtrl(x11386) { Const(true).name("b5659") } // b5659
    val x11358_d0 = withCtrl(x11386) { Reg(init=Some(0.0)).name("x11358_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:36:out") } // x11358 = RegNew(Const(0))
    isAccum(x11358_d0) = false
    bufferDepthOf(x11358_d0) = 2
    val x11358_d1 = withCtrl(x11386) { Reg(init=Some(0.0)).name("x11358_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:36:out") } // x11358 = RegNew(Const(0))
    isAccum(x11358_d1) = true
    bufferDepthOf(x11358_d1) = 1
    val x11359 = withCtrl(x11386) { Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x11359").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:62") } // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x11360 = withCtrl(x11386) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x11360").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:49") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x11361 = withCtrl(x11386) { CounterChain(List(x11360,x11359)).name("x11361").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // CounterChainNew(List(x11360, x11359))
    val x11379 = withCtrl(x11386) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11361).name("x11379").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // UnrolledReduce(List(b5658, b5659, b5531, b5444),x11361,x11358,Block((x11358) => Const(())),List(List(b5664), List(b5665)),List(List(b5666), List(b5667)))
    val b5664 = withCtrl(x11379) { CounterIter(x11360, Some(0)).name("b5664") } // b5664
    val b5666 = withCtrl(x11379) { Const(true).name("b5666") } // b5666
    val b5665 = withCtrl(x11379) { CounterIter(x11359, None).name("b5665") } // b5665
    val b5667 = withCtrl(x11379) { Const(true).name("b5667") } // b5667
    val x11362 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(b5666, b5667)).name("x11362").srcCtx("UnrollingBase.scala:28:66") } // And(b5666,b5667)
    val x11363 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(b5658, b5659)).name("x11363").srcCtx("UnrollingBase.scala:28:66") } // And(b5658,b5659)
    val x11364 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(b5531, b5444)).name("x11364").srcCtx("UnrollingBase.scala:28:66") } // And(b5531,b5444)
    val x11365 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(x11362, x11363)).name("x11365").srcCtx("UnrollingBase.scala:28:66") } // And(x11362,x11363)
    val x11366 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(x11365, x11364)).name("x11366").srcCtx("UnrollingBase.scala:28:66") } // And(x11365,x11364)
    val x11367 = withCtrl(x11379) { LoadBanks(List(x11262_d0_b0), List(b5656, b5664, b5657, b5665)).name("x11367").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:42") } // ParSRAMLoad(x11262,List(List(b5656, b5664, b5657, b5665)),List(x11366))
    val x11368 = withCtrl(x11379) { x11367 } // VectorApply(x11367,0)
    val x11369 = withCtrl(x11379) { LoadBanks(List(x11106_d0_b0), List(b5529)).name("x11369").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:63") } // SRAMLoad(x11106,ArrayBuffer(Const(32)),List(b5529),Const(0),x11366)
    val x11370 = withCtrl(x11379) { OpDef(op=FixAdd, inputs=List(x11368, x11369)).name("x11370").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:54") } // FixAdd(x11368,x11369)
    val x11371 = withCtrl(x11379) { OpDef(op=FixMax, inputs=List(Const(0.0), x11370)).name("x11371").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:18") } // Max(Const(0),x11370)
    val x11372 = withCtrl(x11379) { ReadMem(x11358_d1).name("x11372").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // RegRead(x11358)
    val x11373 = withCtrl(x11379) { OpDef(op=FixEql, inputs=List(b5664, Const(0))).name("x11373").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // FixEql(b5664,Const(0))
    val x11374 = withCtrl(x11379) { OpDef(op=FixEql, inputs=List(b5665, Const(0))).name("x11374").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // FixEql(b5665,Const(0))
    val x11375 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(x11373, x11374)).name("x11375").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // And(x11373,x11374)
    val x11376 = withCtrl(x11379) { ReduceAccumOp(op=FixMax, input=x11371, accum=x11372).name("x11376").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:29") } // Max(x11371,x11372)
    val x11377 = withCtrl(x11379) { OpDef(op=BitAnd, inputs=List(x11363, x11364)).name("x11377").srcCtx("UnrollingBase.scala:28:66") } // And(x11363,x11364)
    val x11378_x11358_d0 = withCtrl(x11379) { WriteMem(x11358_d0, x11376).name("x11378_x11358_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // RegWrite(x11358,x11376,x11377)
    antiDepsOf(x11378_x11358_d0)=List(x11372)
    val x11378_x11358_d1 = withCtrl(x11379) { WriteMem(x11358_d1, x11376).name("x11378_x11358_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // RegWrite(x11358,x11376,x11377)
    antiDepsOf(x11378_x11358_d1)=List(x11372)
    val x11385 = withCtrl(x11386) { UnitController(style=SeqPipe, level=InnerControl).name("x11385").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:37") } // UnitPipe(List(b5658, b5659, b5531, b5444),Block(Const(())))
    val x11380 = withCtrl(x11385) { ReadMem(x11358_d0).name("x11380").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:111:43") } // RegRead(x11358)
    val x11381 = withCtrl(x11385) { OpDef(op=BitAnd, inputs=List(b5658, b5659)).name("x11381").srcCtx("UnrollingBase.scala:28:66") } // And(b5658,b5659)
    val x11382 = withCtrl(x11385) { OpDef(op=BitAnd, inputs=List(b5531, b5444)).name("x11382").srcCtx("UnrollingBase.scala:28:66") } // And(b5531,b5444)
    val x11383 = withCtrl(x11385) { OpDef(op=BitAnd, inputs=List(x11381, x11382)).name("x11383").srcCtx("UnrollingBase.scala:28:66") } // And(x11381,x11382)
    val x11384 = withCtrl(x11385) { StoreBanks(List(List(x11226_d0_b0), List(x11226_d1_b0), List(x11226_d2_b0), List(x11226_d3_b0)), List(b5529, b5656, b5657), x11380).name("x11384").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:111:37") } // SRAMStore(x11226,ArrayBuffer(Const(20), Const(12), Const(12)),List(b5529, b5656, b5657),Const(0),x11380,x11383)
    val x11387 = withCtrl(x11419) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x11387").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:31") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x11388 = withCtrl(x11419) { Counter(min=Const(0), max=Const(12), step=Const(1), par=1).name("x11388").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:22") } // CounterNew(Const(0),Const(12),Const(1),Const(1))
    val x11389 = withCtrl(x11419) { CounterChain(List(x11388,x11387)).name("x11389").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:37") } // CounterChainNew(List(x11388, x11387))
    val x11418 = withCtrl(x11419) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11389).name("x11418").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:37") } // UnrolledForeach(List(b5532, b5444),x11389,Block(Const(())),List(List(b5693), List(b5694)),List(List(b5695), List(b5696)))
    val b5693 = withCtrl(x11418) { CounterIter(x11388, Some(0)).name("b5693") } // b5693
    val b5695 = withCtrl(x11418) { Const(true).name("b5695") } // b5695
    val b5694 = withCtrl(x11418) { CounterIter(x11387, Some(0)).name("b5694") } // b5694
    val b5696 = withCtrl(x11418) { Const(true).name("b5696") } // b5696
    val x11390_d0 = withCtrl(x11418) { Reg(init=Some(0.0)).name("x11390_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:36:out") } // x11390 = RegNew(Const(0))
    isAccum(x11390_d0) = false
    bufferDepthOf(x11390_d0) = 2
    def split1 = {
    val x11390_d1 = withCtrl(x11418) { Reg(init=Some(0.0)).name("x11390_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:36:out") } // x11390 = RegNew(Const(0))
    isAccum(x11390_d1) = true
    bufferDepthOf(x11390_d1) = 1
    val x11391 = withCtrl(x11418) { Counter(min=Const(0), max=Const(2), step=Const(1), par=2).name("x11391").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:62") } // CounterNew(Const(0),Const(2),Const(1),Const(2))
    val x11392 = withCtrl(x11418) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x11392").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:108:49") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x11393 = withCtrl(x11418) { CounterChain(List(x11392,x11391)).name("x11393").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // CounterChainNew(List(x11392, x11391))
    val x11411 = withCtrl(x11418) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11393).name("x11411").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // UnrolledReduce(List(b5695, b5696, b5532, b5444),x11393,x11390,Block((x11390) => Const(())),List(List(b5701), List(b5702)),List(List(b5703), List(b5704)))
    val b5701 = withCtrl(x11411) { CounterIter(x11392, Some(0)).name("b5701") } // b5701
    val b5703 = withCtrl(x11411) { Const(true).name("b5703") } // b5703
    val b5702 = withCtrl(x11411) { CounterIter(x11391, None).name("b5702") } // b5702
    val b5704 = withCtrl(x11411) { Const(true).name("b5704") } // b5704
    val x11394 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(b5703, b5704)).name("x11394").srcCtx("UnrollingBase.scala:28:66") } // And(b5703,b5704)
    val x11395 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(b5695, b5696)).name("x11395").srcCtx("UnrollingBase.scala:28:66") } // And(b5695,b5696)
    val x11396 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(b5532, b5444)).name("x11396").srcCtx("UnrollingBase.scala:28:66") } // And(b5532,b5444)
    val x11397 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(x11394, x11395)).name("x11397").srcCtx("UnrollingBase.scala:28:66") } // And(x11394,x11395)
    val x11398 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(x11397, x11396)).name("x11398").srcCtx("UnrollingBase.scala:28:66") } // And(x11397,x11396)
    val x11399 = withCtrl(x11411) { LoadBanks(List(x11263_d0_b0), List(b5693, b5701, b5694, b5702)).name("x11399").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:42") } // ParSRAMLoad(x11263,List(List(b5693, b5701, b5694, b5702)),List(x11398))
    val x11400 = withCtrl(x11411) { x11399 } // VectorApply(x11399,0)
    val x11401 = withCtrl(x11411) { LoadBanks(List(x11106_d0_b0), List(b5530)).name("x11401").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:63") } // SRAMLoad(x11106,ArrayBuffer(Const(32)),List(b5530),Const(0),x11398)
    val x11402 = withCtrl(x11411) { OpDef(op=FixAdd, inputs=List(x11400, x11401)).name("x11402").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:54") } // FixAdd(x11400,x11401)
    val x11403 = withCtrl(x11411) { OpDef(op=FixMax, inputs=List(Const(0.0), x11402)).name("x11403").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:109:18") } // Max(Const(0),x11402)
    val x11404 = withCtrl(x11411) { ReadMem(x11390_d1).name("x11404").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // RegRead(x11390)
    val x11405 = withCtrl(x11411) { OpDef(op=FixEql, inputs=List(b5701, Const(0))).name("x11405").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // FixEql(b5701,Const(0))
    val x11406 = withCtrl(x11411) { OpDef(op=FixEql, inputs=List(b5702, Const(0))).name("x11406").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // FixEql(b5702,Const(0))
    val x11407 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(x11405, x11406)).name("x11407").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // And(x11405,x11406)
    val x11408 = withCtrl(x11411) { ReduceAccumOp(op=FixMax, input=x11403, accum=x11404).name("x11408").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:29") } // Max(x11403,x11404)
    val x11409 = withCtrl(x11411) { OpDef(op=BitAnd, inputs=List(x11395, x11396)).name("x11409").srcCtx("UnrollingBase.scala:28:66") } // And(x11395,x11396)
    val x11410_x11390_d0 = withCtrl(x11411) { WriteMem(x11390_d0, x11408).name("x11410_x11390_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // RegWrite(x11390,x11408,x11409)
    antiDepsOf(x11410_x11390_d0)=List(x11404)
    val x11410_x11390_d1 = withCtrl(x11411) { WriteMem(x11390_d1, x11408).name("x11410_x11390_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:110:15") } // RegWrite(x11390,x11408,x11409)
    antiDepsOf(x11410_x11390_d1)=List(x11404)
    val x11417 = withCtrl(x11418) { UnitController(style=SeqPipe, level=InnerControl).name("x11417").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:107:37") } // UnitPipe(List(b5695, b5696, b5532, b5444),Block(Const(())))
    val x11412 = withCtrl(x11417) { ReadMem(x11390_d0).name("x11412").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:111:43") } // RegRead(x11390)
    val x11413 = withCtrl(x11417) { OpDef(op=BitAnd, inputs=List(b5695, b5696)).name("x11413").srcCtx("UnrollingBase.scala:28:66") } // And(b5695,b5696)
    val x11414 = withCtrl(x11417) { OpDef(op=BitAnd, inputs=List(b5532, b5444)).name("x11414").srcCtx("UnrollingBase.scala:28:66") } // And(b5532,b5444)
    val x11415 = withCtrl(x11417) { OpDef(op=BitAnd, inputs=List(x11413, x11414)).name("x11415").srcCtx("UnrollingBase.scala:28:66") } // And(x11413,x11414)
    val x11416 = withCtrl(x11417) { StoreBanks(List(List(x11226_d0_b1), List(x11226_d1_b1), List(x11226_d2_b1), List(x11226_d3_b1)), List(b5530, b5693, b5694), x11412).name("x11416").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:111:37") } // SRAMStore(x11226,ArrayBuffer(Const(20), Const(12), Const(12)),List(b5530, b5693, b5694),Const(0),x11412,x11415)
    val x11421_d0_b0 = withCtrl(x12028) { SRAM(size=800, banking=Strided(banks=4, stride=16)).name("x11421_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:115:32:tmp2_SRAM") } // x11421 = SRAMNew(ArrayBuffer(Const(50), Const(4), Const(4)))
    isAccum(x11421_d0_b0) = false
    bufferDepthOf(x11421_d0_b0) = 2
    staticDimsOf(x11421_d0_b0) = List(50, 4, 4)
    val x11422_d0_b0 = withCtrl(x12028) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x11422_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:116:28:c2_RF") } // x11422 = SRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)))
    isAccum(x11422_d0_b0) = false
    bufferDepthOf(x11422_d0_b0) = 2
    staticDimsOf(x11422_d0_b0) = List(50, 5, 16)
    val x11422_d1_b0 = withCtrl(x12028) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x11422_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:116:28:c2_RF") } // x11422 = SRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)))
    isAccum(x11422_d1_b0) = false
    bufferDepthOf(x11422_d1_b0) = 2
    staticDimsOf(x11422_d1_b0) = List(50, 5, 16)
    val x11422_d2_b0 = withCtrl(x12028) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x11422_d2_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:116:28:c2_RF") } // x11422 = SRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)))
    isAccum(x11422_d2_b0) = false
    bufferDepthOf(x11422_d2_b0) = 2
    staticDimsOf(x11422_d2_b0) = List(50, 5, 16)
    val x11422_d3_b0 = withCtrl(x12028) { SRAM(size=4000, banking=Strided(banks=16, stride=1)).name("x11422_d3_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:116:28:c2_RF") } // x11422 = SRAMNew(ArrayBuffer(Const(50), Const(5), Const(16)))
    isAccum(x11422_d3_b0) = false
    bufferDepthOf(x11422_d3_b0) = 2
    staticDimsOf(x11422_d3_b0) = List(50, 5, 16)
    val x11423 = withCtrl(x12028) { Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x11423").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x11424 = withCtrl(x12028) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11424").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11425 = withCtrl(x12028) { CounterChain(List(x11423,x11424)).name("x11425").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // CounterChainNew(List(x11423, x11424))
    val x11454 = withCtrl(x12028) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11425).name("x11454").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // UnrolledForeach(List(b5444),x11425,Block(Const(())),List(List(b5737), List(b5738)),List(List(b5739), List(b5740)))
    val b5737 = withCtrl(x11454) { CounterIter(x11423, Some(0)).name("b5737") } // b5737
    val b5739 = withCtrl(x11454) { Const(true).name("b5739") } // b5739
    val b5738 = withCtrl(x11454) { CounterIter(x11424, Some(0)).name("b5738") } // b5738
    val b5740 = withCtrl(x11454) { Const(true).name("b5740") } // b5740
    val b12091 = withCtrl(x11454) { StreamOut(field="offset").name("b12091").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // x11426 = StreamOutNew(BurstCmdBus)
    isAccum(b12091) = false
    bufferDepthOf(b12091) = 1
    val b12092 = withCtrl(x11454) { StreamOut(field="size").name("b12092").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // x11426 = StreamOutNew(BurstCmdBus)
    isAccum(b12092) = false
    bufferDepthOf(b12092) = 1
    val x11427 = withCtrl(x11454) { StreamIn(field="data").name("x11427").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // x11427 = StreamInNew(BurstDataBus())
    isAccum(x11427) = false
    bufferDepthOf(x11427) = 1
    val x11443 = withCtrl(x11454) { UnitController(style=SeqPipe, level=InnerControl).name("x11443").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // UnitPipe(List(b5739, b5740, b5444),Block(x11442))
    val x11428 = withCtrl(x11443) { b5737 } // FixConvert(b5737,TRUE,_32,_0) (Same Type. No op)
    val x11429 = withCtrl(x11443) { OpDef(op=FixMul, inputs=List(x11428, Const(80))).name("x11429").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FixMul(x11428,Const(80))
    val x11430 = withCtrl(x11443) { b5738 } // FixConvert(b5738,TRUE,_32,_0) (Same Type. No op)
    val x11431 = withCtrl(x11443) { OpDef(op=FixSla, inputs=List(x11430, Const(4))).name("x11431").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FixLsh(x11430,Const(4))
    val x11432 = withCtrl(x11443) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11433 = withCtrl(x11443) { OpDef(op=FixAdd, inputs=List(x11429, x11431)).name("x11433").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FixAdd(x11429,x11431)
    val x11434 = withCtrl(x11443) { OpDef(op=FixAdd, inputs=List(x11433, x11432)).name("x11434").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FixAdd(x11433,x11432)
    val x11435 = withCtrl(x11443) { OpDef(op=FixSla, inputs=List(x11434, Const(2))).name("x11435").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FixLsh(x11434,Const(2))
    val x11436 = withCtrl(x11443) { x11435 } // FixConvert(x11435,TRUE,_64,_0)
    val x11437 = withCtrl(x11443) { DramAddress(x10990).name("x11437").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // GetDRAMAddress(x10990)
    val x11439_x11438 = withCtrl(x11443) { OpDef(op=FixAdd, inputs=List(x11436, x11437)).name("x11439_x11438").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FixAdd(x11436,x11437)
    // x11439 = SimpleStruct(ArrayBuffer((offset,x11438), (size,Const(64)), (isLoad,Const(true))))
    val x11440 = withCtrl(x11443) { OpDef(op=BitAnd, inputs=List(b5739, b5740)).name("x11440").srcCtx("UnrollingBase.scala:28:66") } // And(b5739,b5740)
    val x11441 = withCtrl(x11443) { OpDef(op=BitAnd, inputs=List(x11440, b5444)).name("x11441").srcCtx("UnrollingBase.scala:28:66") } // And(x11440,b5444)
    val x11442_b12093_b12091 = withCtrl(x11443) { WriteMem(b12091, x11439_x11438).name("x11442_b12093_b12091").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // StreamWrite(x11426,x11439,x11441)
    val x11442_b12094_b12092 = withCtrl(x11443) { WriteMem(b12092, Const(64)).name("x11442_b12094_b12092").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // StreamWrite(x11426,x11439,x11441)
    val x11444 = withCtrl(x11454) { FringeDenseLoad(dram=List(x10990), cmdStream=List(b12091, b12092), dataStream=List(x11427)).name("x11444").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // FringeDenseLoad(x10990,x11426,x11427)
    val x11445 = withCtrl(x11454) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x11445").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x11446 = withCtrl(x11454) { CounterChain(List(x11445)).name("x11446").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // CounterChainNew(List(x11445))
    val x11453 = withCtrl(x11454) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11446).name("x11453").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // UnrolledForeach(List(b5739, b5740, b5444),x11446,Block(Const(())),List(List(b5762)),List(List(b5763)))
    val b5762 = withCtrl(x11453) { CounterIter(x11445, None).name("b5762") } // b5762
    val b5763 = withCtrl(x11453) { Const(true).name("b5763") } // b5763
    val x11447 = withCtrl(x11453) { OpDef(op=BitAnd, inputs=List(b5763, b5739)).name("x11447").srcCtx("UnrollingBase.scala:28:66") } // And(b5763,b5739)
    val x11448 = withCtrl(x11453) { OpDef(op=BitAnd, inputs=List(b5740, b5444)).name("x11448").srcCtx("UnrollingBase.scala:28:66") } // And(b5740,b5444)
    val x11449 = withCtrl(x11453) { OpDef(op=BitAnd, inputs=List(x11447, x11448)).name("x11449").srcCtx("UnrollingBase.scala:28:66") } // And(x11447,x11448)
    val x11450_x11450 = withCtrl(x11453) { ReadMem(x11427).name("x11450_x11450").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // ParStreamRead(x11427,List(x11449))
    val x11451_x11451 = withCtrl(x11453) { x11450_x11450 } // VectorApply(x11450,0)
    val x11452 = withCtrl(x11453) { StoreBanks(List(List(x11422_d0_b0), List(x11422_d1_b0), List(x11422_d2_b0), List(x11422_d3_b0)), List(b5737, b5738, b5762), x11451_x11451).name("x11452").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:117:15") } // ParSRAMStore(x11422,List(List(b5737, b5738, b5762)),List(x11451),List(x11449))
    val x11455 = withCtrl(x12028) { Counter(min=Const(0), max=Const(64), step=Const(1), par=4).name("x11455").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:118:25") } // CounterNew(Const(0),Const(64),Const(1),Const(4))
    val x11456 = withCtrl(x12028) { CounterChain(List(x11455)).name("x11456").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:118:40") } // CounterChainNew(List(x11455))
    val x11843 = withCtrl(x12028) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11456).name("x11843").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:118:40") } // UnrolledForeach(List(b5444),x11456,Block(Const(())),List(List(b5774, b5775, b5776, b5777)),List(List(b5778, b5779, b5780, b5781)))
    val b5774 = withCtrl(x11843) { CounterIter(x11455, Some(0)).name("b5774") } // b5774
    val b5778 = withCtrl(x11843) { Const(true).name("b5778") } // b5778
    val b5775 = withCtrl(x11843) { CounterIter(x11455, Some(1)).name("b5775") } // b5775
    val b5779 = withCtrl(x11843) { Const(true).name("b5779") } // b5779
    val b5776 = withCtrl(x11843) { CounterIter(x11455, Some(2)).name("b5776") } // b5776
    val b5780 = withCtrl(x11843) { Const(true).name("b5780") } // b5780
    val b5777 = withCtrl(x11843) { CounterIter(x11455, Some(3)).name("b5777") } // b5777
    val b5781 = withCtrl(x11843) { Const(true).name("b5781") } // b5781
    val x11457_d0_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11457_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11457 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11457_d0_b0) = false
    bufferDepthOf(x11457_d0_b0) = 2
    staticDimsOf(x11457_d0_b0) = List(8, 8)
    val x11457_d1_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11457_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11457 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11457_d1_b0) = true
    bufferDepthOf(x11457_d1_b0) = 1
    staticDimsOf(x11457_d1_b0) = List(8, 8)
    val x11458_d0_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11458_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11458 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11458_d0_b0) = false
    bufferDepthOf(x11458_d0_b0) = 2
    staticDimsOf(x11458_d0_b0) = List(8, 8)
    val x11458_d1_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11458_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11458 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11458_d1_b0) = true
    bufferDepthOf(x11458_d1_b0) = 1
    staticDimsOf(x11458_d1_b0) = List(8, 8)
    val x11459_d0_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11459_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11459 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11459_d0_b0) = false
    bufferDepthOf(x11459_d0_b0) = 2
    staticDimsOf(x11459_d0_b0) = List(8, 8)
    val x11459_d1_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11459_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11459 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11459_d1_b0) = true
    bufferDepthOf(x11459_d1_b0) = 1
    staticDimsOf(x11459_d1_b0) = List(8, 8)
    val x11460_d0_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11460_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11460 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11460_d0_b0) = false
    bufferDepthOf(x11460_d0_b0) = 2
    staticDimsOf(x11460_d0_b0) = List(8, 8)
    val x11460_d1_b0 = withCtrl(x11843) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11460_d1_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:126:39:tmp2_SRAM_conv") } // x11460 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11460_d1_b0) = true
    bufferDepthOf(x11460_d1_b0) = 1
    staticDimsOf(x11460_d1_b0) = List(8, 8)
    val x11697 = withCtrl(x11843) { UnitController(style=ForkJoin, level=OuterControl).name("x11697").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b5444),Block(Const(())))
    val x11461 = withCtrl(x11697) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x11461").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:127:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x11462 = withCtrl(x11697) { CounterChain(List(x11461)).name("x11462").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(List(x11461))
    val x11519 = withCtrl(x11697) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11462).name("x11519").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledReduce(List(b5778, b5444),x11462,x11457,Block((x11457) => Const(())),List(List(b5806)),List(List(b5807)))
    val b5806 = withCtrl(x11519) { CounterIter(x11461, Some(0)).name("b5806") } // b5806
    val b5807 = withCtrl(x11519) { Const(true).name("b5807") } // b5807
    val x11463_d0_b0 = withCtrl(x11519) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11463_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:128:33:result") } // x11463 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11463_d0_b0) = false
    bufferDepthOf(x11463_d0_b0) = 2
    staticDimsOf(x11463_d0_b0) = List(8, 8)
    val x11464 = withCtrl(x11519) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11464").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11465 = withCtrl(x11519) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11465").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11466 = withCtrl(x11519) { CounterChain(List(x11465,x11464)).name("x11466").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // CounterChainNew(List(x11465, x11464))
    val x11500 = withCtrl(x11519) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11466).name("x11500").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnrolledForeach(List(b5807, b5778, b5444),x11466,Block(Const(())),List(List(b5812), List(b5813)),List(List(b5814), List(b5815)))
    val b5812 = withCtrl(x11500) { CounterIter(x11465, Some(0)).name("b5812") } // b5812
    val b5814 = withCtrl(x11500) { Const(true).name("b5814") } // b5814
    val b5813 = withCtrl(x11500) { CounterIter(x11464, Some(0)).name("b5813") } // b5813
    val b5815 = withCtrl(x11500) { Const(true).name("b5815") } // b5815
    val x11467_d0 = withCtrl(x11500) { Reg(init=Some(0.0)).name("x11467_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11467 = RegNew(Const(0))
    isAccum(x11467_d0) = false
    bufferDepthOf(x11467_d0) = 2
    val x11467_d1 = withCtrl(x11500) { Reg(init=Some(0.0)).name("x11467_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11467 = RegNew(Const(0))
    isAccum(x11467_d1) = true
    bufferDepthOf(x11467_d1) = 1
    val x11468 = withCtrl(x11500) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x11468").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:81") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x11469 = withCtrl(x11500) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11469").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11470 = withCtrl(x11500) { CounterChain(List(x11469,x11468)).name("x11470").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // CounterChainNew(List(x11469, x11468))
    val x11492 = withCtrl(x11500) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11470).name("x11492").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // UnrolledReduce(List(b5814, b5815, b5807, b5778, b5444),x11470,x11467,Block((x11467) => Const(())),List(List(b5820), List(b5821)),List(List(b5822), List(b5823)))
    val b5820 = withCtrl(x11492) { CounterIter(x11469, Some(0)).name("b5820") } // b5820
    val b5822 = withCtrl(x11492) { Const(true).name("b5822") } // b5822
    val b5821 = withCtrl(x11492) { CounterIter(x11468, None).name("b5821") } // b5821
    val b5823 = withCtrl(x11492) { Const(true).name("b5823") } // b5823
    val x11471 = withCtrl(x11492) { OpDef(op=FixAdd, inputs=List(b5812, b5820)).name("x11471").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:35") } // FixAdd(b5812,b5820)
    val x11472 = withCtrl(x11492) { OpDef(op=FixAdd, inputs=List(b5813, b5821)).name("x11472").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:39") } // FixAdd(b5813,b5821)
    val x11473 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(b5822, b5823)).name("x11473").srcCtx("UnrollingBase.scala:28:66") } // And(b5822,b5823)
    val x11474 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(b5814, b5815)).name("x11474").srcCtx("UnrollingBase.scala:28:66") } // And(b5814,b5815)
    val x11475 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(b5807, b5778)).name("x11475").srcCtx("UnrollingBase.scala:28:66") } // And(b5807,b5778)
    val x11476 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(x11473, x11474)).name("x11476").srcCtx("UnrollingBase.scala:28:66") } // And(x11473,x11474)
    val x11477 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(x11475, b5444)).name("x11477").srcCtx("UnrollingBase.scala:28:66") } // And(x11475,b5444)
    val x11478 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(x11476, x11477)).name("x11478").srcCtx("UnrollingBase.scala:28:66") } // And(x11476,x11477)
    val x11479 = withCtrl(x11492) { LoadBanks(List(x11226_d0_b0, x11226_d0_b1), List(b5806, x11471, x11472)).name("x11479").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:26") } // ParSRAMLoad(x11226,List(List(b5806, x11471, x11472)),List(x11478))
    val x11480 = withCtrl(x11492) { x11479 } // VectorApply(x11479,0)
    val x11481 = withCtrl(x11492) { LoadBanks(List(x11422_d0_b0), List(b5806, b5820, b5821)).name("x11481").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:50") } // ParSRAMLoad(x11422,List(List(b5806, b5820, b5821)),List(x11478))
    val x11482 = withCtrl(x11492) { x11481 } // VectorApply(x11481,0)
    val x11483 = withCtrl(x11492) { OpDef(op=FixMul, inputs=List(x11480, x11482)).name("x11483").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:43") } // FixMul(x11480,x11482)
    val x11484 = withCtrl(x11492) { ReadMem(x11467_d1).name("x11484").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegRead(x11467)
    val x11485 = withCtrl(x11492) { OpDef(op=FixEql, inputs=List(b5820, Const(0))).name("x11485").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b5820,Const(0))
    val x11486 = withCtrl(x11492) { OpDef(op=FixEql, inputs=List(b5821, Const(0))).name("x11486").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b5821,Const(0))
    val x11487 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(x11485, x11486)).name("x11487").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // And(x11485,x11486)
    val x11488 = withCtrl(x11492) { ReduceAccumOp(op=FixAdd, input=x11483, accum=x11484).name("x11488").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:18") } // FixAdd(x11483,x11484)
    val x11489 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(x11474, x11475)).name("x11489").srcCtx("UnrollingBase.scala:28:66") } // And(x11474,x11475)
    val x11490 = withCtrl(x11492) { OpDef(op=BitAnd, inputs=List(x11489, b5444)).name("x11490").srcCtx("UnrollingBase.scala:28:66") } // And(x11489,b5444)
    val x11491_x11467_d0 = withCtrl(x11492) { WriteMem(x11467_d0, x11488).name("x11491_x11467_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11467,x11488,x11490)
    antiDepsOf(x11491_x11467_d0)=List(x11484)
    val x11491_x11467_d1 = withCtrl(x11492) { WriteMem(x11467_d1, x11488).name("x11491_x11467_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11467,x11488,x11490)
    antiDepsOf(x11491_x11467_d1)=List(x11484)
    val x11499 = withCtrl(x11500) { UnitController(style=SeqPipe, level=InnerControl).name("x11499").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnitPipe(List(b5814, b5815, b5807, b5778, b5444),Block(Const(())))
    val x11493 = withCtrl(x11499) { ReadMem(x11467_d0).name("x11493").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:37") } // RegRead(x11467)
    val x11494 = withCtrl(x11499) { OpDef(op=BitAnd, inputs=List(b5814, b5815)).name("x11494").srcCtx("UnrollingBase.scala:28:66") } // And(b5814,b5815)
    val x11495 = withCtrl(x11499) { OpDef(op=BitAnd, inputs=List(b5807, b5778)).name("x11495").srcCtx("UnrollingBase.scala:28:66") } // And(b5807,b5778)
    val x11496 = withCtrl(x11499) { OpDef(op=BitAnd, inputs=List(x11494, x11495)).name("x11496").srcCtx("UnrollingBase.scala:28:66") } // And(x11494,x11495)
    val x11497 = withCtrl(x11499) { OpDef(op=BitAnd, inputs=List(x11496, b5444)).name("x11497").srcCtx("UnrollingBase.scala:28:66") } // And(x11496,b5444)
    val x11498 = withCtrl(x11499) { StoreBanks(List(List(x11463_d0_b0)), List(b5812, b5813), x11493).name("x11498").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:28") } // SRAMStore(x11463,ArrayBuffer(Const(8), Const(8)),List(b5812, b5813),Const(0),x11493,x11497)
    val x11501 = withCtrl(x11519) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11501").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11502 = withCtrl(x11519) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11502").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11503 = withCtrl(x11519) { CounterChain(List(x11502,x11501)).name("x11503").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(ArrayBuffer(x11502, x11501))
    val x11518 = withCtrl(x11519) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11503).name("x11518").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledForeach(List(),x11503,Block(Const(())),ArrayBuffer(List(b5854), List(b5855)),ArrayBuffer(List(b5856), List(b5857)))
    val b5854 = withCtrl(x11518) { CounterIter(x11502, Some(0)).name("b5854") } // b5854
    val b5856 = withCtrl(x11518) { Const(true).name("b5856") } // b5856
    val b5855 = withCtrl(x11518) { CounterIter(x11501, None).name("b5855") } // b5855
    val b5857 = withCtrl(x11518) { Const(true).name("b5857") } // b5857
    val x11504 = withCtrl(x11518) { OpDef(op=BitAnd, inputs=List(b5856, b5857)).name("x11504").srcCtx("UnrollingBase.scala:28:66") } // And(b5856,b5857)
    val x11505 = withCtrl(x11518) { OpDef(op=BitAnd, inputs=List(b5778, b5444)).name("x11505").srcCtx("UnrollingBase.scala:28:66") } // And(b5778,b5444)
    val x11506 = withCtrl(x11518) { OpDef(op=BitAnd, inputs=List(x11504, x11505)).name("x11506").srcCtx("UnrollingBase.scala:28:66") } // And(x11504,x11505)
    val x11507 = withCtrl(x11518) { LoadBanks(List(x11463_d0_b0), List(b5854, b5855)).name("x11507").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11463,List(ArrayBuffer(b5854, b5855)),List(x11506))
    val x11508 = withCtrl(x11518) { x11507 } // VectorApply(x11507,0)
    val x11509 = withCtrl(x11518) { LoadBanks(List(x11457_d1_b0), List(b5854, b5855)).name("x11509").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11457,List(ArrayBuffer(b5854, b5855)),List(x11506))
    val x11510 = withCtrl(x11518) { x11509 } // VectorApply(x11509,0)
    val x11511 = withCtrl(x11518) { OpDef(op=BitAnd, inputs=List(b5807, b5778)).name("x11511").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(b5807,b5778)
    val x11512 = withCtrl(x11518) { OpDef(op=BitAnd, inputs=List(x11511, b5444)).name("x11512").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11511,b5444)
    val x11513 = withCtrl(x11518) { OpDef(op=BitAnd, inputs=List(x11512, x11506)).name("x11513").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11512,x11506)
    val x11514 = withCtrl(x11518) { OpDef(op=FixEql, inputs=List(b5806, Const(0))).name("x11514").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // FixEql(b5806,Const(0))
    val x11515 = withCtrl(x11518) { OpDef(op=FixAdd, inputs=List(x11508, x11510)).name("x11515").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:14") } // FixAdd(x11508,x11510)
    val x11516 = withCtrl(x11518) { OpDef(op=MuxOp, inputs=List(x11514, x11508, x11515)).name("x11516").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // Mux(x11514,x11508,x11515)
    val x11517 = withCtrl(x11518) { StoreBanks(List(List(x11457_d0_b0), List(x11457_d1_b0)), List(b5854, b5855), x11516).name("x11517").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMStore(x11457,List(ArrayBuffer(b5854, b5855)),List(x11516),List(x11506))
    antiDepsOf(x11517)=List(x11509)
    val x11520 = withCtrl(x11697) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x11520").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:127:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x11521 = withCtrl(x11697) { CounterChain(List(x11520)).name("x11521").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(List(x11520))
    val x11578 = withCtrl(x11697) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11521).name("x11578").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledReduce(List(b5779, b5444),x11521,x11458,Block((x11458) => Const(())),List(List(b5874)),List(List(b5875)))
    val b5874 = withCtrl(x11578) { CounterIter(x11520, Some(0)).name("b5874") } // b5874
    val b5875 = withCtrl(x11578) { Const(true).name("b5875") } // b5875
    val x11522_d0_b0 = withCtrl(x11578) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11522_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:128:33:result") } // x11522 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11522_d0_b0) = false
    bufferDepthOf(x11522_d0_b0) = 2
    staticDimsOf(x11522_d0_b0) = List(8, 8)
    val x11523 = withCtrl(x11578) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11523").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11524 = withCtrl(x11578) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11524").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11525 = withCtrl(x11578) { CounterChain(List(x11524,x11523)).name("x11525").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // CounterChainNew(List(x11524, x11523))
    val x11559 = withCtrl(x11578) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11525).name("x11559").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnrolledForeach(List(b5875, b5779, b5444),x11525,Block(Const(())),List(List(b5880), List(b5881)),List(List(b5882), List(b5883)))
    val b5880 = withCtrl(x11559) { CounterIter(x11524, Some(0)).name("b5880") } // b5880
    val b5882 = withCtrl(x11559) { Const(true).name("b5882") } // b5882
    val b5881 = withCtrl(x11559) { CounterIter(x11523, Some(0)).name("b5881") } // b5881
    val b5883 = withCtrl(x11559) { Const(true).name("b5883") } // b5883
    val x11526_d0 = withCtrl(x11559) { Reg(init=Some(0.0)).name("x11526_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11526 = RegNew(Const(0))
    isAccum(x11526_d0) = false
    bufferDepthOf(x11526_d0) = 2
    val x11526_d1 = withCtrl(x11559) { Reg(init=Some(0.0)).name("x11526_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11526 = RegNew(Const(0))
    isAccum(x11526_d1) = true
    bufferDepthOf(x11526_d1) = 1
    val x11527 = withCtrl(x11559) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x11527").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:81") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x11528 = withCtrl(x11559) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11528").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11529 = withCtrl(x11559) { CounterChain(List(x11528,x11527)).name("x11529").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // CounterChainNew(List(x11528, x11527))
    val x11551 = withCtrl(x11559) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11529).name("x11551").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // UnrolledReduce(List(b5882, b5883, b5875, b5779, b5444),x11529,x11526,Block((x11526) => Const(())),List(List(b5888), List(b5889)),List(List(b5890), List(b5891)))
    val b5888 = withCtrl(x11551) { CounterIter(x11528, Some(0)).name("b5888") } // b5888
    val b5890 = withCtrl(x11551) { Const(true).name("b5890") } // b5890
    val b5889 = withCtrl(x11551) { CounterIter(x11527, None).name("b5889") } // b5889
    val b5891 = withCtrl(x11551) { Const(true).name("b5891") } // b5891
    val x11530 = withCtrl(x11551) { OpDef(op=FixAdd, inputs=List(b5880, b5888)).name("x11530").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:35") } // FixAdd(b5880,b5888)
    val x11531 = withCtrl(x11551) { OpDef(op=FixAdd, inputs=List(b5881, b5889)).name("x11531").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:39") } // FixAdd(b5881,b5889)
    val x11532 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(b5890, b5891)).name("x11532").srcCtx("UnrollingBase.scala:28:66") } // And(b5890,b5891)
    val x11533 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x11533").srcCtx("UnrollingBase.scala:28:66") } // And(b5882,b5883)
    val x11534 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(b5875, b5779)).name("x11534").srcCtx("UnrollingBase.scala:28:66") } // And(b5875,b5779)
    val x11535 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(x11532, x11533)).name("x11535").srcCtx("UnrollingBase.scala:28:66") } // And(x11532,x11533)
    val x11536 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(x11534, b5444)).name("x11536").srcCtx("UnrollingBase.scala:28:66") } // And(x11534,b5444)
    val x11537 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(x11535, x11536)).name("x11537").srcCtx("UnrollingBase.scala:28:66") } // And(x11535,x11536)
    val x11538 = withCtrl(x11551) { LoadBanks(List(x11226_d1_b0, x11226_d1_b1), List(b5874, x11530, x11531)).name("x11538").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:26") } // ParSRAMLoad(x11226,List(List(b5874, x11530, x11531)),List(x11537))
    val x11539 = withCtrl(x11551) { x11538 } // VectorApply(x11538,0)
    val x11540 = withCtrl(x11551) { LoadBanks(List(x11422_d1_b0), List(b5874, b5888, b5889)).name("x11540").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:50") } // ParSRAMLoad(x11422,List(List(b5874, b5888, b5889)),List(x11537))
    val x11541 = withCtrl(x11551) { x11540 } // VectorApply(x11540,0)
    val x11542 = withCtrl(x11551) { OpDef(op=FixMul, inputs=List(x11539, x11541)).name("x11542").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:43") } // FixMul(x11539,x11541)
    val x11543 = withCtrl(x11551) { ReadMem(x11526_d1).name("x11543").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegRead(x11526)
    val x11544 = withCtrl(x11551) { OpDef(op=FixEql, inputs=List(b5888, Const(0))).name("x11544").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b5888,Const(0))
    val x11545 = withCtrl(x11551) { OpDef(op=FixEql, inputs=List(b5889, Const(0))).name("x11545").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b5889,Const(0))
    val x11546 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(x11544, x11545)).name("x11546").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // And(x11544,x11545)
    val x11547 = withCtrl(x11551) { ReduceAccumOp(op=FixAdd, input=x11542, accum=x11543).name("x11547").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:18") } // FixAdd(x11542,x11543)
    val x11548 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(x11533, x11534)).name("x11548").srcCtx("UnrollingBase.scala:28:66") } // And(x11533,x11534)
    val x11549 = withCtrl(x11551) { OpDef(op=BitAnd, inputs=List(x11548, b5444)).name("x11549").srcCtx("UnrollingBase.scala:28:66") } // And(x11548,b5444)
    val x11550_x11526_d0 = withCtrl(x11551) { WriteMem(x11526_d0, x11547).name("x11550_x11526_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11526,x11547,x11549)
    antiDepsOf(x11550_x11526_d0)=List(x11543)
    val x11550_x11526_d1 = withCtrl(x11551) { WriteMem(x11526_d1, x11547).name("x11550_x11526_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11526,x11547,x11549)
    antiDepsOf(x11550_x11526_d1)=List(x11543)
    val x11558 = withCtrl(x11559) { UnitController(style=SeqPipe, level=InnerControl).name("x11558").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnitPipe(List(b5882, b5883, b5875, b5779, b5444),Block(Const(())))
    val x11552 = withCtrl(x11558) { ReadMem(x11526_d0).name("x11552").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:37") } // RegRead(x11526)
    val x11553 = withCtrl(x11558) { OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x11553").srcCtx("UnrollingBase.scala:28:66") } // And(b5882,b5883)
    val x11554 = withCtrl(x11558) { OpDef(op=BitAnd, inputs=List(b5875, b5779)).name("x11554").srcCtx("UnrollingBase.scala:28:66") } // And(b5875,b5779)
    val x11555 = withCtrl(x11558) { OpDef(op=BitAnd, inputs=List(x11553, x11554)).name("x11555").srcCtx("UnrollingBase.scala:28:66") } // And(x11553,x11554)
    val x11556 = withCtrl(x11558) { OpDef(op=BitAnd, inputs=List(x11555, b5444)).name("x11556").srcCtx("UnrollingBase.scala:28:66") } // And(x11555,b5444)
    val x11557 = withCtrl(x11558) { StoreBanks(List(List(x11522_d0_b0)), List(b5880, b5881), x11552).name("x11557").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:28") } // SRAMStore(x11522,ArrayBuffer(Const(8), Const(8)),List(b5880, b5881),Const(0),x11552,x11556)
    val x11560 = withCtrl(x11578) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11560").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11561 = withCtrl(x11578) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11561").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11562 = withCtrl(x11578) { CounterChain(List(x11561,x11560)).name("x11562").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(ArrayBuffer(x11561, x11560))
    val x11577 = withCtrl(x11578) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11562).name("x11577").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledForeach(List(),x11562,Block(Const(())),ArrayBuffer(List(b5922), List(b5923)),ArrayBuffer(List(b5924), List(b5925)))
    val b5922 = withCtrl(x11577) { CounterIter(x11561, Some(0)).name("b5922") } // b5922
    val b5924 = withCtrl(x11577) { Const(true).name("b5924") } // b5924
    val b5923 = withCtrl(x11577) { CounterIter(x11560, None).name("b5923") } // b5923
    val b5925 = withCtrl(x11577) { Const(true).name("b5925") } // b5925
    val x11563 = withCtrl(x11577) { OpDef(op=BitAnd, inputs=List(b5924, b5925)).name("x11563").srcCtx("UnrollingBase.scala:28:66") } // And(b5924,b5925)
    val x11564 = withCtrl(x11577) { OpDef(op=BitAnd, inputs=List(b5779, b5444)).name("x11564").srcCtx("UnrollingBase.scala:28:66") } // And(b5779,b5444)
    val x11565 = withCtrl(x11577) { OpDef(op=BitAnd, inputs=List(x11563, x11564)).name("x11565").srcCtx("UnrollingBase.scala:28:66") } // And(x11563,x11564)
    val x11566 = withCtrl(x11577) { LoadBanks(List(x11522_d0_b0), List(b5922, b5923)).name("x11566").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11522,List(ArrayBuffer(b5922, b5923)),List(x11565))
    val x11567 = withCtrl(x11577) { x11566 } // VectorApply(x11566,0)
    val x11568 = withCtrl(x11577) { LoadBanks(List(x11458_d1_b0), List(b5922, b5923)).name("x11568").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11458,List(ArrayBuffer(b5922, b5923)),List(x11565))
    val x11569 = withCtrl(x11577) { x11568 } // VectorApply(x11568,0)
    val x11570 = withCtrl(x11577) { OpDef(op=BitAnd, inputs=List(b5875, b5779)).name("x11570").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(b5875,b5779)
    val x11571 = withCtrl(x11577) { OpDef(op=BitAnd, inputs=List(x11570, b5444)).name("x11571").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11570,b5444)
    val x11572 = withCtrl(x11577) { OpDef(op=BitAnd, inputs=List(x11571, x11565)).name("x11572").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11571,x11565)
    val x11573 = withCtrl(x11577) { OpDef(op=FixEql, inputs=List(b5874, Const(0))).name("x11573").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // FixEql(b5874,Const(0))
    val x11574 = withCtrl(x11577) { OpDef(op=FixAdd, inputs=List(x11567, x11569)).name("x11574").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:14") } // FixAdd(x11567,x11569)
    val x11575 = withCtrl(x11577) { OpDef(op=MuxOp, inputs=List(x11573, x11567, x11574)).name("x11575").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // Mux(x11573,x11567,x11574)
    val x11576 = withCtrl(x11577) { StoreBanks(List(List(x11458_d0_b0), List(x11458_d1_b0)), List(b5922, b5923), x11575).name("x11576").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMStore(x11458,List(ArrayBuffer(b5922, b5923)),List(x11575),List(x11565))
    antiDepsOf(x11576)=List(x11568)
    val x11579 = withCtrl(x11697) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x11579").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:127:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x11580 = withCtrl(x11697) { CounterChain(List(x11579)).name("x11580").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(List(x11579))
    val x11637 = withCtrl(x11697) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11580).name("x11637").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledReduce(List(b5780, b5444),x11580,x11459,Block((x11459) => Const(())),List(List(b5942)),List(List(b5943)))
    val b5942 = withCtrl(x11637) { CounterIter(x11579, Some(0)).name("b5942") } // b5942
    val b5943 = withCtrl(x11637) { Const(true).name("b5943") } // b5943
    val x11581_d0_b0 = withCtrl(x11637) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11581_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:128:33:result") } // x11581 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11581_d0_b0) = false
    bufferDepthOf(x11581_d0_b0) = 2
    staticDimsOf(x11581_d0_b0) = List(8, 8)
    val x11582 = withCtrl(x11637) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11582").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11583 = withCtrl(x11637) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11583").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11584 = withCtrl(x11637) { CounterChain(List(x11583,x11582)).name("x11584").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // CounterChainNew(List(x11583, x11582))
    val x11618 = withCtrl(x11637) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11584).name("x11618").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnrolledForeach(List(b5943, b5780, b5444),x11584,Block(Const(())),List(List(b5948), List(b5949)),List(List(b5950), List(b5951)))
    val b5948 = withCtrl(x11618) { CounterIter(x11583, Some(0)).name("b5948") } // b5948
    val b5950 = withCtrl(x11618) { Const(true).name("b5950") } // b5950
    val b5949 = withCtrl(x11618) { CounterIter(x11582, Some(0)).name("b5949") } // b5949
    val b5951 = withCtrl(x11618) { Const(true).name("b5951") } // b5951
    val x11585_d0 = withCtrl(x11618) { Reg(init=Some(0.0)).name("x11585_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11585 = RegNew(Const(0))
    isAccum(x11585_d0) = false
    bufferDepthOf(x11585_d0) = 2
    val x11585_d1 = withCtrl(x11618) { Reg(init=Some(0.0)).name("x11585_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11585 = RegNew(Const(0))
    isAccum(x11585_d1) = true
    bufferDepthOf(x11585_d1) = 1
    val x11586 = withCtrl(x11618) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x11586").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:81") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x11587 = withCtrl(x11618) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11587").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11588 = withCtrl(x11618) { CounterChain(List(x11587,x11586)).name("x11588").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // CounterChainNew(List(x11587, x11586))
    val x11610 = withCtrl(x11618) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11588).name("x11610").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // UnrolledReduce(List(b5950, b5951, b5943, b5780, b5444),x11588,x11585,Block((x11585) => Const(())),List(List(b5956), List(b5957)),List(List(b5958), List(b5959)))
    val b5956 = withCtrl(x11610) { CounterIter(x11587, Some(0)).name("b5956") } // b5956
    val b5958 = withCtrl(x11610) { Const(true).name("b5958") } // b5958
    val b5957 = withCtrl(x11610) { CounterIter(x11586, None).name("b5957") } // b5957
    val b5959 = withCtrl(x11610) { Const(true).name("b5959") } // b5959
    val x11589 = withCtrl(x11610) { OpDef(op=FixAdd, inputs=List(b5948, b5956)).name("x11589").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:35") } // FixAdd(b5948,b5956)
    val x11590 = withCtrl(x11610) { OpDef(op=FixAdd, inputs=List(b5949, b5957)).name("x11590").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:39") } // FixAdd(b5949,b5957)
    val x11591 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(b5958, b5959)).name("x11591").srcCtx("UnrollingBase.scala:28:66") } // And(b5958,b5959)
    val x11592 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(b5950, b5951)).name("x11592").srcCtx("UnrollingBase.scala:28:66") } // And(b5950,b5951)
    val x11593 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(b5943, b5780)).name("x11593").srcCtx("UnrollingBase.scala:28:66") } // And(b5943,b5780)
    val x11594 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(x11591, x11592)).name("x11594").srcCtx("UnrollingBase.scala:28:66") } // And(x11591,x11592)
    val x11595 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(x11593, b5444)).name("x11595").srcCtx("UnrollingBase.scala:28:66") } // And(x11593,b5444)
    val x11596 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(x11594, x11595)).name("x11596").srcCtx("UnrollingBase.scala:28:66") } // And(x11594,x11595)
    val x11597 = withCtrl(x11610) { LoadBanks(List(x11226_d2_b0, x11226_d2_b1), List(b5942, x11589, x11590)).name("x11597").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:26") } // ParSRAMLoad(x11226,List(List(b5942, x11589, x11590)),List(x11596))
    val x11598 = withCtrl(x11610) { x11597 } // VectorApply(x11597,0)
    val x11599 = withCtrl(x11610) { LoadBanks(List(x11422_d2_b0), List(b5942, b5956, b5957)).name("x11599").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:50") } // ParSRAMLoad(x11422,List(List(b5942, b5956, b5957)),List(x11596))
    val x11600 = withCtrl(x11610) { x11599 } // VectorApply(x11599,0)
    val x11601 = withCtrl(x11610) { OpDef(op=FixMul, inputs=List(x11598, x11600)).name("x11601").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:43") } // FixMul(x11598,x11600)
    val x11602 = withCtrl(x11610) { ReadMem(x11585_d1).name("x11602").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegRead(x11585)
    val x11603 = withCtrl(x11610) { OpDef(op=FixEql, inputs=List(b5956, Const(0))).name("x11603").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b5956,Const(0))
    val x11604 = withCtrl(x11610) { OpDef(op=FixEql, inputs=List(b5957, Const(0))).name("x11604").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b5957,Const(0))
    val x11605 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(x11603, x11604)).name("x11605").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // And(x11603,x11604)
    val x11606 = withCtrl(x11610) { ReduceAccumOp(op=FixAdd, input=x11601, accum=x11602).name("x11606").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:18") } // FixAdd(x11601,x11602)
    val x11607 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(x11592, x11593)).name("x11607").srcCtx("UnrollingBase.scala:28:66") } // And(x11592,x11593)
    val x11608 = withCtrl(x11610) { OpDef(op=BitAnd, inputs=List(x11607, b5444)).name("x11608").srcCtx("UnrollingBase.scala:28:66") } // And(x11607,b5444)
    val x11609_x11585_d0 = withCtrl(x11610) { WriteMem(x11585_d0, x11606).name("x11609_x11585_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11585,x11606,x11608)
    antiDepsOf(x11609_x11585_d0)=List(x11602)
    val x11609_x11585_d1 = withCtrl(x11610) { WriteMem(x11585_d1, x11606).name("x11609_x11585_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11585,x11606,x11608)
    antiDepsOf(x11609_x11585_d1)=List(x11602)
    val x11617 = withCtrl(x11618) { UnitController(style=SeqPipe, level=InnerControl).name("x11617").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnitPipe(List(b5950, b5951, b5943, b5780, b5444),Block(Const(())))
    val x11611 = withCtrl(x11617) { ReadMem(x11585_d0).name("x11611").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:37") } // RegRead(x11585)
    val x11612 = withCtrl(x11617) { OpDef(op=BitAnd, inputs=List(b5950, b5951)).name("x11612").srcCtx("UnrollingBase.scala:28:66") } // And(b5950,b5951)
    val x11613 = withCtrl(x11617) { OpDef(op=BitAnd, inputs=List(b5943, b5780)).name("x11613").srcCtx("UnrollingBase.scala:28:66") } // And(b5943,b5780)
    val x11614 = withCtrl(x11617) { OpDef(op=BitAnd, inputs=List(x11612, x11613)).name("x11614").srcCtx("UnrollingBase.scala:28:66") } // And(x11612,x11613)
    val x11615 = withCtrl(x11617) { OpDef(op=BitAnd, inputs=List(x11614, b5444)).name("x11615").srcCtx("UnrollingBase.scala:28:66") } // And(x11614,b5444)
    val x11616 = withCtrl(x11617) { StoreBanks(List(List(x11581_d0_b0)), List(b5948, b5949), x11611).name("x11616").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:28") } // SRAMStore(x11581,ArrayBuffer(Const(8), Const(8)),List(b5948, b5949),Const(0),x11611,x11615)
    val x11619 = withCtrl(x11637) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11619").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11620 = withCtrl(x11637) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11620").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11621 = withCtrl(x11637) { CounterChain(List(x11620,x11619)).name("x11621").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(ArrayBuffer(x11620, x11619))
    val x11636 = withCtrl(x11637) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11621).name("x11636").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledForeach(List(),x11621,Block(Const(())),ArrayBuffer(List(b5990), List(b5991)),ArrayBuffer(List(b5992), List(b5993)))
    val b5990 = withCtrl(x11636) { CounterIter(x11620, Some(0)).name("b5990") } // b5990
    val b5992 = withCtrl(x11636) { Const(true).name("b5992") } // b5992
    val b5991 = withCtrl(x11636) { CounterIter(x11619, None).name("b5991") } // b5991
    val b5993 = withCtrl(x11636) { Const(true).name("b5993") } // b5993
    val x11622 = withCtrl(x11636) { OpDef(op=BitAnd, inputs=List(b5992, b5993)).name("x11622").srcCtx("UnrollingBase.scala:28:66") } // And(b5992,b5993)
    val x11623 = withCtrl(x11636) { OpDef(op=BitAnd, inputs=List(b5780, b5444)).name("x11623").srcCtx("UnrollingBase.scala:28:66") } // And(b5780,b5444)
    val x11624 = withCtrl(x11636) { OpDef(op=BitAnd, inputs=List(x11622, x11623)).name("x11624").srcCtx("UnrollingBase.scala:28:66") } // And(x11622,x11623)
    val x11625 = withCtrl(x11636) { LoadBanks(List(x11581_d0_b0), List(b5990, b5991)).name("x11625").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11581,List(ArrayBuffer(b5990, b5991)),List(x11624))
    val x11626 = withCtrl(x11636) { x11625 } // VectorApply(x11625,0)
    val x11627 = withCtrl(x11636) { LoadBanks(List(x11459_d1_b0), List(b5990, b5991)).name("x11627").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11459,List(ArrayBuffer(b5990, b5991)),List(x11624))
    val x11628 = withCtrl(x11636) { x11627 } // VectorApply(x11627,0)
    val x11629 = withCtrl(x11636) { OpDef(op=BitAnd, inputs=List(b5943, b5780)).name("x11629").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(b5943,b5780)
    val x11630 = withCtrl(x11636) { OpDef(op=BitAnd, inputs=List(x11629, b5444)).name("x11630").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11629,b5444)
    val x11631 = withCtrl(x11636) { OpDef(op=BitAnd, inputs=List(x11630, x11624)).name("x11631").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11630,x11624)
    val x11632 = withCtrl(x11636) { OpDef(op=FixEql, inputs=List(b5942, Const(0))).name("x11632").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // FixEql(b5942,Const(0))
    val x11633 = withCtrl(x11636) { OpDef(op=FixAdd, inputs=List(x11626, x11628)).name("x11633").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:14") } // FixAdd(x11626,x11628)
    val x11634 = withCtrl(x11636) { OpDef(op=MuxOp, inputs=List(x11632, x11626, x11633)).name("x11634").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // Mux(x11632,x11626,x11633)
    val x11635 = withCtrl(x11636) { StoreBanks(List(List(x11459_d0_b0), List(x11459_d1_b0)), List(b5990, b5991), x11634).name("x11635").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMStore(x11459,List(ArrayBuffer(b5990, b5991)),List(x11634),List(x11624))
    antiDepsOf(x11635)=List(x11627)
    val x11638 = withCtrl(x11697) { Counter(min=Const(0), max=Const(20), step=Const(1), par=1).name("x11638").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:127:44") } // CounterNew(Const(0),Const(20),Const(1),Const(1))
    val x11639 = withCtrl(x11697) { CounterChain(List(x11638)).name("x11639").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(List(x11638))
    val x11696 = withCtrl(x11697) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11639).name("x11696").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledReduce(List(b5781, b5444),x11639,x11460,Block((x11460) => Const(())),List(List(b6010)),List(List(b6011)))
    val b6010 = withCtrl(x11696) { CounterIter(x11638, Some(0)).name("b6010") } // b6010
    val b6011 = withCtrl(x11696) { Const(true).name("b6011") } // b6011
    val x11640_d0_b0 = withCtrl(x11696) { SRAM(size=64, banking=Strided(banks=1, stride=8)).name("x11640_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:128:33:result") } // x11640 = SRAMNew(ArrayBuffer(Const(8), Const(8)))
    isAccum(x11640_d0_b0) = false
    bufferDepthOf(x11640_d0_b0) = 2
    staticDimsOf(x11640_d0_b0) = List(8, 8)
    val x11641 = withCtrl(x11696) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11641").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:44") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11642 = withCtrl(x11696) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11642").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:23") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11643 = withCtrl(x11696) { CounterChain(List(x11642,x11641)).name("x11643").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // CounterChainNew(List(x11642, x11641))
    val x11677 = withCtrl(x11696) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11643).name("x11677").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnrolledForeach(List(b6011, b5781, b5444),x11643,Block(Const(())),List(List(b6016), List(b6017)),List(List(b6018), List(b6019)))
    val b6016 = withCtrl(x11677) { CounterIter(x11642, Some(0)).name("b6016") } // b6016
    val b6018 = withCtrl(x11677) { Const(true).name("b6018") } // b6018
    val b6017 = withCtrl(x11677) { CounterIter(x11641, Some(0)).name("b6017") } // b6017
    val b6019 = withCtrl(x11677) { Const(true).name("b6019") } // b6019
    val x11644_d0 = withCtrl(x11677) { Reg(init=Some(0.0)).name("x11644_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11644 = RegNew(Const(0))
    isAccum(x11644_d0) = false
    bufferDepthOf(x11644_d0) = 2
    val x11644_d1 = withCtrl(x11677) { Reg(init=Some(0.0)).name("x11644_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:41:window") } // x11644 = RegNew(Const(0))
    isAccum(x11644_d1) = true
    bufferDepthOf(x11644_d1) = 1
    val x11645 = withCtrl(x11677) { Counter(min=Const(0), max=Const(5), step=Const(1), par=5).name("x11645").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:81") } // CounterNew(Const(0),Const(5),Const(1),Const(5))
    val x11646 = withCtrl(x11677) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11646").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:130:63") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11647 = withCtrl(x11677) { CounterChain(List(x11646,x11645)).name("x11647").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // CounterChainNew(List(x11646, x11645))
    val x11669 = withCtrl(x11677) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11647).name("x11669").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // UnrolledReduce(List(b6018, b6019, b6011, b5781, b5444),x11647,x11644,Block((x11644) => Const(())),List(List(b6024), List(b6025)),List(List(b6026), List(b6027)))
    val b6024 = withCtrl(x11669) { CounterIter(x11646, Some(0)).name("b6024") } // b6024
    val b6026 = withCtrl(x11669) { Const(true).name("b6026") } // b6026
    val b6025 = withCtrl(x11669) { CounterIter(x11645, None).name("b6025") } // b6025
    val b6027 = withCtrl(x11669) { Const(true).name("b6027") } // b6027
    val x11648 = withCtrl(x11669) { OpDef(op=FixAdd, inputs=List(b6016, b6024)).name("x11648").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:35") } // FixAdd(b6016,b6024)
    val x11649 = withCtrl(x11669) { OpDef(op=FixAdd, inputs=List(b6017, b6025)).name("x11649").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:39") } // FixAdd(b6017,b6025)
    val x11650 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(b6026, b6027)).name("x11650").srcCtx("UnrollingBase.scala:28:66") } // And(b6026,b6027)
    val x11651 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(b6018, b6019)).name("x11651").srcCtx("UnrollingBase.scala:28:66") } // And(b6018,b6019)
    val x11652 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(b6011, b5781)).name("x11652").srcCtx("UnrollingBase.scala:28:66") } // And(b6011,b5781)
    val x11653 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(x11650, x11651)).name("x11653").srcCtx("UnrollingBase.scala:28:66") } // And(x11650,x11651)
    val x11654 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(x11652, b5444)).name("x11654").srcCtx("UnrollingBase.scala:28:66") } // And(x11652,b5444)
    val x11655 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(x11653, x11654)).name("x11655").srcCtx("UnrollingBase.scala:28:66") } // And(x11653,x11654)
    val x11656 = withCtrl(x11669) { LoadBanks(List(x11226_d3_b0, x11226_d3_b1), List(b6010, x11648, x11649)).name("x11656").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:26") } // ParSRAMLoad(x11226,List(List(b6010, x11648, x11649)),List(x11655))
    val x11657 = withCtrl(x11669) { x11656 } // VectorApply(x11656,0)
    val x11658 = withCtrl(x11669) { LoadBanks(List(x11422_d3_b0), List(b6010, b6024, b6025)).name("x11658").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:50") } // ParSRAMLoad(x11422,List(List(b6010, b6024, b6025)),List(x11655))
    val x11659 = withCtrl(x11669) { x11658 } // VectorApply(x11658,0)
    val x11660 = withCtrl(x11669) { OpDef(op=FixMul, inputs=List(x11657, x11659)).name("x11660").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:131:43") } // FixMul(x11657,x11659)
    val x11661 = withCtrl(x11669) { ReadMem(x11644_d1).name("x11661").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegRead(x11644)
    val x11662 = withCtrl(x11669) { OpDef(op=FixEql, inputs=List(b6024, Const(0))).name("x11662").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b6024,Const(0))
    val x11663 = withCtrl(x11669) { OpDef(op=FixEql, inputs=List(b6025, Const(0))).name("x11663").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // FixEql(b6025,Const(0))
    val x11664 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(x11662, x11663)).name("x11664").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // And(x11662,x11663)
    val x11665 = withCtrl(x11669) { ReduceAccumOp(op=FixAdd, input=x11660, accum=x11661).name("x11665").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:18") } // FixAdd(x11660,x11661)
    val x11666 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(x11651, x11652)).name("x11666").srcCtx("UnrollingBase.scala:28:66") } // And(x11651,x11652)
    val x11667 = withCtrl(x11669) { OpDef(op=BitAnd, inputs=List(x11666, b5444)).name("x11667").srcCtx("UnrollingBase.scala:28:66") } // And(x11666,b5444)
    val x11668_x11644_d0 = withCtrl(x11669) { WriteMem(x11644_d0, x11665).name("x11668_x11644_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11644,x11665,x11667)
    antiDepsOf(x11668_x11644_d0)=List(x11661)
    val x11668_x11644_d1 = withCtrl(x11669) { WriteMem(x11644_d1, x11665).name("x11668_x11644_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:132:16") } // RegWrite(x11644,x11665,x11667)
    antiDepsOf(x11668_x11644_d1)=List(x11661)
    val x11676 = withCtrl(x11677) { UnitController(style=SeqPipe, level=InnerControl).name("x11676").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:129:51") } // UnitPipe(List(b6018, b6019, b6011, b5781, b5444),Block(Const(())))
    val x11670 = withCtrl(x11676) { ReadMem(x11644_d0).name("x11670").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:37") } // RegRead(x11644)
    val x11671 = withCtrl(x11676) { OpDef(op=BitAnd, inputs=List(b6018, b6019)).name("x11671").srcCtx("UnrollingBase.scala:28:66") } // And(b6018,b6019)
    val x11672 = withCtrl(x11676) { OpDef(op=BitAnd, inputs=List(b6011, b5781)).name("x11672").srcCtx("UnrollingBase.scala:28:66") } // And(b6011,b5781)
    val x11673 = withCtrl(x11676) { OpDef(op=BitAnd, inputs=List(x11671, x11672)).name("x11673").srcCtx("UnrollingBase.scala:28:66") } // And(x11671,x11672)
    val x11674 = withCtrl(x11676) { OpDef(op=BitAnd, inputs=List(x11673, b5444)).name("x11674").srcCtx("UnrollingBase.scala:28:66") } // And(x11673,b5444)
    val x11675 = withCtrl(x11676) { StoreBanks(List(List(x11640_d0_b0)), List(b6016, b6017), x11670).name("x11675").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:133:28") } // SRAMStore(x11640,ArrayBuffer(Const(8), Const(8)),List(b6016, b6017),Const(0),x11670,x11674)
    val x11678 = withCtrl(x11696) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11678").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11679 = withCtrl(x11696) { Counter(min=Const(0), max=Const(8), step=Const(1), par=1).name("x11679").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterNew(Const(0),Const(8),Const(1),Const(1))
    val x11680 = withCtrl(x11696) { CounterChain(List(x11679,x11678)).name("x11680").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // CounterChainNew(ArrayBuffer(x11679, x11678))
    val x11695 = withCtrl(x11696) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11680).name("x11695").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // UnrolledForeach(List(),x11680,Block(Const(())),ArrayBuffer(List(b6058), List(b6059)),ArrayBuffer(List(b6060), List(b6061)))
    val b6058 = withCtrl(x11695) { CounterIter(x11679, Some(0)).name("b6058") } // b6058
    val b6060 = withCtrl(x11695) { Const(true).name("b6060") } // b6060
    val b6059 = withCtrl(x11695) { CounterIter(x11678, None).name("b6059") } // b6059
    val b6061 = withCtrl(x11695) { Const(true).name("b6061") } // b6061
    val x11681 = withCtrl(x11695) { OpDef(op=BitAnd, inputs=List(b6060, b6061)).name("x11681").srcCtx("UnrollingBase.scala:28:66") } // And(b6060,b6061)
    val x11682 = withCtrl(x11695) { OpDef(op=BitAnd, inputs=List(b5781, b5444)).name("x11682").srcCtx("UnrollingBase.scala:28:66") } // And(b5781,b5444)
    val x11683 = withCtrl(x11695) { OpDef(op=BitAnd, inputs=List(x11681, x11682)).name("x11683").srcCtx("UnrollingBase.scala:28:66") } // And(x11681,x11682)
    val x11684 = withCtrl(x11695) { LoadBanks(List(x11640_d0_b0), List(b6058, b6059)).name("x11684").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11640,List(ArrayBuffer(b6058, b6059)),List(x11683))
    val x11685 = withCtrl(x11695) { x11684 } // VectorApply(x11684,0)
    val x11686 = withCtrl(x11695) { LoadBanks(List(x11460_d1_b0), List(b6058, b6059)).name("x11686").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMLoad(x11460,List(ArrayBuffer(b6058, b6059)),List(x11683))
    val x11687 = withCtrl(x11695) { x11686 } // VectorApply(x11686,0)
    val x11688 = withCtrl(x11695) { OpDef(op=BitAnd, inputs=List(b6011, b5781)).name("x11688").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(b6011,b5781)
    val x11689 = withCtrl(x11695) { OpDef(op=BitAnd, inputs=List(x11688, b5444)).name("x11689").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11688,b5444)
    val x11690 = withCtrl(x11695) { OpDef(op=BitAnd, inputs=List(x11689, x11683)).name("x11690").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // And(x11689,x11683)
    val x11691 = withCtrl(x11695) { OpDef(op=FixEql, inputs=List(b6010, Const(0))).name("x11691").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // FixEql(b6010,Const(0))
    val x11692 = withCtrl(x11695) { OpDef(op=FixAdd, inputs=List(x11685, x11687)).name("x11692").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:14") } // FixAdd(x11685,x11687)
    val x11693 = withCtrl(x11695) { OpDef(op=MuxOp, inputs=List(x11691, x11685, x11692)).name("x11693").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // Mux(x11691,x11685,x11692)
    val x11694 = withCtrl(x11695) { StoreBanks(List(List(x11460_d0_b0), List(x11460_d1_b0)), List(b6058, b6059), x11693).name("x11694").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:136:12") } // ParSRAMStore(x11460,List(ArrayBuffer(b6058, b6059)),List(x11693),List(x11683))
    antiDepsOf(x11694)=List(x11686)
    val x11842 = withCtrl(x11843) { UnitController(style=ForkJoin, level=OuterControl).name("x11842").srcCtx("UnrollingTransformer.scala:431:43") } // ParallelPipe(List(b5444),Block(Const(())))
    val x11698 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11698").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11699 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11699").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11700 = withCtrl(x11842) { CounterChain(List(x11699,x11698)).name("x11700").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // CounterChainNew(List(x11699, x11698))
    val x11733 = withCtrl(x11842) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11700).name("x11733").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnrolledForeach(List(b5778, b5444),x11700,Block(Const(())),List(List(b6091), List(b6092)),List(List(b6093), List(b6094)))
    val b6091 = withCtrl(x11733) { CounterIter(x11699, Some(0)).name("b6091") } // b6091
    val b6093 = withCtrl(x11733) { Const(true).name("b6093") } // b6093
    val b6092 = withCtrl(x11733) { CounterIter(x11698, Some(0)).name("b6092") } // b6092
    val b6094 = withCtrl(x11733) { Const(true).name("b6094") } // b6094
    val x11701_d0 = withCtrl(x11733) { Reg(init=Some(0.0)).name("x11701_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11701 = RegNew(Const(0))
    isAccum(x11701_d0) = false
    bufferDepthOf(x11701_d0) = 2
    val x11701_d1 = withCtrl(x11733) { Reg(init=Some(0.0)).name("x11701_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11701 = RegNew(Const(0))
    isAccum(x11701_d1) = true
    def split2 = {
    bufferDepthOf(x11701_d1) = 1
    val x11702 = withCtrl(x11733) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11702").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:67") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11703 = withCtrl(x11733) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11703").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:54") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11704 = withCtrl(x11733) { CounterChain(List(x11703,x11702)).name("x11704").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // CounterChainNew(List(x11703, x11702))
    val x11726 = withCtrl(x11733) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11704).name("x11726").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // UnrolledReduce(List(b6093, b6094, b5778, b5444),x11704,x11701,Block((x11701) => Const(())),List(List(b6099), List(b6100)),List(List(b6101), List(b6102)))
    val b6099 = withCtrl(x11726) { CounterIter(x11703, Some(0)).name("b6099") } // b6099
    val b6101 = withCtrl(x11726) { Const(true).name("b6101") } // b6101
    val b6100 = withCtrl(x11726) { CounterIter(x11702, None).name("b6100") } // b6100
    val b6102 = withCtrl(x11726) { Const(true).name("b6102") } // b6102
    val x11705 = withCtrl(x11726) { OpDef(op=FixSla, inputs=List(b6091, Const(1))).name("x11705").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:44") } // FixLsh(b6091,Const(1))
    val x11706 = withCtrl(x11726) { OpDef(op=FixAdd, inputs=List(x11705, b6099)).name("x11706").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:47") } // FixAdd(x11705,b6099)
    val x11707 = withCtrl(x11726) { OpDef(op=FixSla, inputs=List(b6092, Const(1))).name("x11707").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:54") } // FixLsh(b6092,Const(1))
    val x11708 = withCtrl(x11726) { OpDef(op=FixAdd, inputs=List(x11707, b6100)).name("x11708").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:57") } // FixAdd(x11707,b6100)
    val x11709 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(b6101, b6102)).name("x11709").srcCtx("UnrollingBase.scala:28:66") } // And(b6101,b6102)
    val x11710 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(b6093, b6094)).name("x11710").srcCtx("UnrollingBase.scala:28:66") } // And(b6093,b6094)
    val x11711 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(b5778, b5444)).name("x11711").srcCtx("UnrollingBase.scala:28:66") } // And(b5778,b5444)
    val x11712 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(x11709, x11710)).name("x11712").srcCtx("UnrollingBase.scala:28:66") } // And(x11709,x11710)
    val x11713 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(x11712, x11711)).name("x11713").srcCtx("UnrollingBase.scala:28:66") } // And(x11712,x11711)
    val x11714 = withCtrl(x11726) { LoadBanks(List(x11457_d0_b0), List(x11706, x11708)).name("x11714").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:42") } // ParSRAMLoad(x11457,List(List(x11706, x11708)),List(x11713))
    val x11715 = withCtrl(x11726) { x11714 } // VectorApply(x11714,0)
    val x11716 = withCtrl(x11726) { LoadBanks(List(x11125_d0_b0), List(b5774)).name("x11716").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:72") } // SRAMLoad(x11125,ArrayBuffer(Const(64)),List(b5774),Const(0),x11713)
    val x11717 = withCtrl(x11726) { OpDef(op=FixAdd, inputs=List(x11715, x11716)).name("x11717").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:63") } // FixAdd(x11715,x11716)
    val x11718 = withCtrl(x11726) { OpDef(op=FixMax, inputs=List(Const(0.0), x11717)).name("x11718").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:18") } // Max(Const(0),x11717)
    val x11719 = withCtrl(x11726) { ReadMem(x11701_d1).name("x11719").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegRead(x11701)
    val x11720 = withCtrl(x11726) { OpDef(op=FixEql, inputs=List(b6099, Const(0))).name("x11720").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6099,Const(0))
    val x11721 = withCtrl(x11726) { OpDef(op=FixEql, inputs=List(b6100, Const(0))).name("x11721").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6100,Const(0))
    val x11722 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(x11720, x11721)).name("x11722").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // And(x11720,x11721)
    val x11723 = withCtrl(x11726) { ReduceAccumOp(op=FixMax, input=x11718, accum=x11719).name("x11723").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:29") } // Max(x11718,x11719)
    val x11724 = withCtrl(x11726) { OpDef(op=BitAnd, inputs=List(x11710, x11711)).name("x11724").srcCtx("UnrollingBase.scala:28:66") } // And(x11710,x11711)
    val x11725_x11701_d0 = withCtrl(x11726) { WriteMem(x11701_d0, x11723).name("x11725_x11701_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11701,x11723,x11724)
    antiDepsOf(x11725_x11701_d0)=List(x11719)
    val x11725_x11701_d1 = withCtrl(x11726) { WriteMem(x11701_d1, x11723).name("x11725_x11701_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11701,x11723,x11724)
    antiDepsOf(x11725_x11701_d1)=List(x11719)
    val x11732 = withCtrl(x11733) { UnitController(style=SeqPipe, level=InnerControl).name("x11732").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnitPipe(List(b6093, b6094, b5778, b5444),Block(Const(())))
    val x11727 = withCtrl(x11732) { ReadMem(x11701_d0).name("x11727").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:43") } // RegRead(x11701)
    val x11728 = withCtrl(x11732) { OpDef(op=BitAnd, inputs=List(b6093, b6094)).name("x11728").srcCtx("UnrollingBase.scala:28:66") } // And(b6093,b6094)
    val x11729 = withCtrl(x11732) { OpDef(op=BitAnd, inputs=List(b5778, b5444)).name("x11729").srcCtx("UnrollingBase.scala:28:66") } // And(b5778,b5444)
    val x11730 = withCtrl(x11732) { OpDef(op=BitAnd, inputs=List(x11728, x11729)).name("x11730").srcCtx("UnrollingBase.scala:28:66") } // And(x11728,x11729)
    val x11731 = withCtrl(x11732) { StoreBanks(List(List(x11421_d0_b0)), List(b5774, b6091, b6092), x11727).name("x11731").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:37") } // SRAMStore(x11421,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5774, b6091, b6092),Const(0),x11727,x11730)
    val x11734 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11734").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11735 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11735").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11736 = withCtrl(x11842) { CounterChain(List(x11735,x11734)).name("x11736").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // CounterChainNew(List(x11735, x11734))
    val x11769 = withCtrl(x11842) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11736).name("x11769").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnrolledForeach(List(b5779, b5444),x11736,Block(Const(())),List(List(b6132), List(b6133)),List(List(b6134), List(b6135)))
    val b6132 = withCtrl(x11769) { CounterIter(x11735, Some(0)).name("b6132") } // b6132
    val b6134 = withCtrl(x11769) { Const(true).name("b6134") } // b6134
    val b6133 = withCtrl(x11769) { CounterIter(x11734, Some(0)).name("b6133") } // b6133
    val b6135 = withCtrl(x11769) { Const(true).name("b6135") } // b6135
    val x11737_d0 = withCtrl(x11769) { Reg(init=Some(0.0)).name("x11737_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11737 = RegNew(Const(0))
    isAccum(x11737_d0) = false
    bufferDepthOf(x11737_d0) = 2
    val x11737_d1 = withCtrl(x11769) { Reg(init=Some(0.0)).name("x11737_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11737 = RegNew(Const(0))
    isAccum(x11737_d1) = true
    bufferDepthOf(x11737_d1) = 1
    val x11738 = withCtrl(x11769) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11738").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:67") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11739 = withCtrl(x11769) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11739").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:54") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11740 = withCtrl(x11769) { CounterChain(List(x11739,x11738)).name("x11740").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // CounterChainNew(List(x11739, x11738))
    val x11762 = withCtrl(x11769) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11740).name("x11762").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // UnrolledReduce(List(b6134, b6135, b5779, b5444),x11740,x11737,Block((x11737) => Const(())),List(List(b6140), List(b6141)),List(List(b6142), List(b6143)))
    val b6140 = withCtrl(x11762) { CounterIter(x11739, Some(0)).name("b6140") } // b6140
    val b6142 = withCtrl(x11762) { Const(true).name("b6142") } // b6142
    val b6141 = withCtrl(x11762) { CounterIter(x11738, None).name("b6141") } // b6141
    val b6143 = withCtrl(x11762) { Const(true).name("b6143") } // b6143
    val x11741 = withCtrl(x11762) { OpDef(op=FixSla, inputs=List(b6132, Const(1))).name("x11741").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:44") } // FixLsh(b6132,Const(1))
    val x11742 = withCtrl(x11762) { OpDef(op=FixAdd, inputs=List(x11741, b6140)).name("x11742").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:47") } // FixAdd(x11741,b6140)
    val x11743 = withCtrl(x11762) { OpDef(op=FixSla, inputs=List(b6133, Const(1))).name("x11743").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:54") } // FixLsh(b6133,Const(1))
    val x11744 = withCtrl(x11762) { OpDef(op=FixAdd, inputs=List(x11743, b6141)).name("x11744").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:57") } // FixAdd(x11743,b6141)
    val x11745 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(b6142, b6143)).name("x11745").srcCtx("UnrollingBase.scala:28:66") } // And(b6142,b6143)
    val x11746 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(b6134, b6135)).name("x11746").srcCtx("UnrollingBase.scala:28:66") } // And(b6134,b6135)
    val x11747 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(b5779, b5444)).name("x11747").srcCtx("UnrollingBase.scala:28:66") } // And(b5779,b5444)
    val x11748 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(x11745, x11746)).name("x11748").srcCtx("UnrollingBase.scala:28:66") } // And(x11745,x11746)
    val x11749 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(x11748, x11747)).name("x11749").srcCtx("UnrollingBase.scala:28:66") } // And(x11748,x11747)
    val x11750 = withCtrl(x11762) { LoadBanks(List(x11458_d0_b0), List(x11742, x11744)).name("x11750").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:42") } // ParSRAMLoad(x11458,List(List(x11742, x11744)),List(x11749))
    val x11751 = withCtrl(x11762) { x11750 } // VectorApply(x11750,0)
    val x11752 = withCtrl(x11762) { LoadBanks(List(x11125_d0_b0), List(b5775)).name("x11752").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:72") } // SRAMLoad(x11125,ArrayBuffer(Const(64)),List(b5775),Const(0),x11749)
    val x11753 = withCtrl(x11762) { OpDef(op=FixAdd, inputs=List(x11751, x11752)).name("x11753").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:63") } // FixAdd(x11751,x11752)
    val x11754 = withCtrl(x11762) { OpDef(op=FixMax, inputs=List(Const(0.0), x11753)).name("x11754").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:18") } // Max(Const(0),x11753)
    val x11755 = withCtrl(x11762) { ReadMem(x11737_d1).name("x11755").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegRead(x11737)
    val x11756 = withCtrl(x11762) { OpDef(op=FixEql, inputs=List(b6140, Const(0))).name("x11756").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6140,Const(0))
    val x11757 = withCtrl(x11762) { OpDef(op=FixEql, inputs=List(b6141, Const(0))).name("x11757").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6141,Const(0))
    val x11758 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(x11756, x11757)).name("x11758").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // And(x11756,x11757)
    val x11759 = withCtrl(x11762) { ReduceAccumOp(op=FixMax, input=x11754, accum=x11755).name("x11759").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:29") } // Max(x11754,x11755)
    val x11760 = withCtrl(x11762) { OpDef(op=BitAnd, inputs=List(x11746, x11747)).name("x11760").srcCtx("UnrollingBase.scala:28:66") } // And(x11746,x11747)
    val x11761_x11737_d0 = withCtrl(x11762) { WriteMem(x11737_d0, x11759).name("x11761_x11737_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11737,x11759,x11760)
    antiDepsOf(x11761_x11737_d0)=List(x11755)
    val x11761_x11737_d1 = withCtrl(x11762) { WriteMem(x11737_d1, x11759).name("x11761_x11737_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11737,x11759,x11760)
    antiDepsOf(x11761_x11737_d1)=List(x11755)
    val x11768 = withCtrl(x11769) { UnitController(style=SeqPipe, level=InnerControl).name("x11768").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnitPipe(List(b6134, b6135, b5779, b5444),Block(Const(())))
    val x11763 = withCtrl(x11768) { ReadMem(x11737_d0).name("x11763").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:43") } // RegRead(x11737)
    val x11764 = withCtrl(x11768) { OpDef(op=BitAnd, inputs=List(b6134, b6135)).name("x11764").srcCtx("UnrollingBase.scala:28:66") } // And(b6134,b6135)
    val x11765 = withCtrl(x11768) { OpDef(op=BitAnd, inputs=List(b5779, b5444)).name("x11765").srcCtx("UnrollingBase.scala:28:66") } // And(b5779,b5444)
    val x11766 = withCtrl(x11768) { OpDef(op=BitAnd, inputs=List(x11764, x11765)).name("x11766").srcCtx("UnrollingBase.scala:28:66") } // And(x11764,x11765)
    val x11767 = withCtrl(x11768) { StoreBanks(List(List(x11421_d0_b0)), List(b5775, b6132, b6133), x11763).name("x11767").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:37") } // SRAMStore(x11421,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5775, b6132, b6133),Const(0),x11763,x11766)
    val x11770 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11770").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11771 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11771").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11772 = withCtrl(x11842) { CounterChain(List(x11771,x11770)).name("x11772").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // CounterChainNew(List(x11771, x11770))
    val x11805 = withCtrl(x11842) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11772).name("x11805").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnrolledForeach(List(b5780, b5444),x11772,Block(Const(())),List(List(b6173), List(b6174)),List(List(b6175), List(b6176)))
    val b6173 = withCtrl(x11805) { CounterIter(x11771, Some(0)).name("b6173") } // b6173
    val b6175 = withCtrl(x11805) { Const(true).name("b6175") } // b6175
    val b6174 = withCtrl(x11805) { CounterIter(x11770, Some(0)).name("b6174") } // b6174
    val b6176 = withCtrl(x11805) { Const(true).name("b6176") } // b6176
    val x11773_d0 = withCtrl(x11805) { Reg(init=Some(0.0)).name("x11773_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11773 = RegNew(Const(0))
    isAccum(x11773_d0) = false
    bufferDepthOf(x11773_d0) = 2
    val x11773_d1 = withCtrl(x11805) { Reg(init=Some(0.0)).name("x11773_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11773 = RegNew(Const(0))
    isAccum(x11773_d1) = true
    bufferDepthOf(x11773_d1) = 1
    val x11774 = withCtrl(x11805) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11774").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:67") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11775 = withCtrl(x11805) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11775").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:54") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11776 = withCtrl(x11805) { CounterChain(List(x11775,x11774)).name("x11776").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // CounterChainNew(List(x11775, x11774))
    val x11798 = withCtrl(x11805) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11776).name("x11798").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // UnrolledReduce(List(b6175, b6176, b5780, b5444),x11776,x11773,Block((x11773) => Const(())),List(List(b6181), List(b6182)),List(List(b6183), List(b6184)))
    val b6181 = withCtrl(x11798) { CounterIter(x11775, Some(0)).name("b6181") } // b6181
    val b6183 = withCtrl(x11798) { Const(true).name("b6183") } // b6183
    val b6182 = withCtrl(x11798) { CounterIter(x11774, None).name("b6182") } // b6182
    val b6184 = withCtrl(x11798) { Const(true).name("b6184") } // b6184
    val x11777 = withCtrl(x11798) { OpDef(op=FixSla, inputs=List(b6173, Const(1))).name("x11777").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:44") } // FixLsh(b6173,Const(1))
    val x11778 = withCtrl(x11798) { OpDef(op=FixAdd, inputs=List(x11777, b6181)).name("x11778").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:47") } // FixAdd(x11777,b6181)
    val x11779 = withCtrl(x11798) { OpDef(op=FixSla, inputs=List(b6174, Const(1))).name("x11779").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:54") } // FixLsh(b6174,Const(1))
    val x11780 = withCtrl(x11798) { OpDef(op=FixAdd, inputs=List(x11779, b6182)).name("x11780").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:57") } // FixAdd(x11779,b6182)
    val x11781 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(b6183, b6184)).name("x11781").srcCtx("UnrollingBase.scala:28:66") } // And(b6183,b6184)
    val x11782 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(b6175, b6176)).name("x11782").srcCtx("UnrollingBase.scala:28:66") } // And(b6175,b6176)
    val x11783 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(b5780, b5444)).name("x11783").srcCtx("UnrollingBase.scala:28:66") } // And(b5780,b5444)
    val x11784 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(x11781, x11782)).name("x11784").srcCtx("UnrollingBase.scala:28:66") } // And(x11781,x11782)
    val x11785 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(x11784, x11783)).name("x11785").srcCtx("UnrollingBase.scala:28:66") } // And(x11784,x11783)
    val x11786 = withCtrl(x11798) { LoadBanks(List(x11459_d0_b0), List(x11778, x11780)).name("x11786").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:42") } // ParSRAMLoad(x11459,List(List(x11778, x11780)),List(x11785))
    val x11787 = withCtrl(x11798) { x11786 } // VectorApply(x11786,0)
    val x11788 = withCtrl(x11798) { LoadBanks(List(x11125_d0_b0), List(b5776)).name("x11788").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:72") } // SRAMLoad(x11125,ArrayBuffer(Const(64)),List(b5776),Const(0),x11785)
    val x11789 = withCtrl(x11798) { OpDef(op=FixAdd, inputs=List(x11787, x11788)).name("x11789").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:63") } // FixAdd(x11787,x11788)
    val x11790 = withCtrl(x11798) { OpDef(op=FixMax, inputs=List(Const(0.0), x11789)).name("x11790").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:18") } // Max(Const(0),x11789)
    val x11791 = withCtrl(x11798) { ReadMem(x11773_d1).name("x11791").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegRead(x11773)
    val x11792 = withCtrl(x11798) { OpDef(op=FixEql, inputs=List(b6181, Const(0))).name("x11792").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6181,Const(0))
    val x11793 = withCtrl(x11798) { OpDef(op=FixEql, inputs=List(b6182, Const(0))).name("x11793").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6182,Const(0))
    val x11794 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(x11792, x11793)).name("x11794").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // And(x11792,x11793)
    val x11795 = withCtrl(x11798) { ReduceAccumOp(op=FixMax, input=x11790, accum=x11791).name("x11795").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:29") } // Max(x11790,x11791)
    val x11796 = withCtrl(x11798) { OpDef(op=BitAnd, inputs=List(x11782, x11783)).name("x11796").srcCtx("UnrollingBase.scala:28:66") } // And(x11782,x11783)
    val x11797_x11773_d0 = withCtrl(x11798) { WriteMem(x11773_d0, x11795).name("x11797_x11773_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11773,x11795,x11796)
    antiDepsOf(x11797_x11773_d0)=List(x11791)
    val x11797_x11773_d1 = withCtrl(x11798) { WriteMem(x11773_d1, x11795).name("x11797_x11773_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11773,x11795,x11796)
    antiDepsOf(x11797_x11773_d1)=List(x11791)
    val x11804 = withCtrl(x11805) { UnitController(style=SeqPipe, level=InnerControl).name("x11804").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnitPipe(List(b6175, b6176, b5780, b5444),Block(Const(())))
    val x11799 = withCtrl(x11804) { ReadMem(x11773_d0).name("x11799").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:43") } // RegRead(x11773)
    val x11800 = withCtrl(x11804) { OpDef(op=BitAnd, inputs=List(b6175, b6176)).name("x11800").srcCtx("UnrollingBase.scala:28:66") } // And(b6175,b6176)
    val x11801 = withCtrl(x11804) { OpDef(op=BitAnd, inputs=List(b5780, b5444)).name("x11801").srcCtx("UnrollingBase.scala:28:66") } // And(b5780,b5444)
    val x11802 = withCtrl(x11804) { OpDef(op=BitAnd, inputs=List(x11800, x11801)).name("x11802").srcCtx("UnrollingBase.scala:28:66") } // And(x11800,x11801)
    val x11803 = withCtrl(x11804) { StoreBanks(List(List(x11421_d0_b0)), List(b5776, b6173, b6174), x11799).name("x11803").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:37") } // SRAMStore(x11421,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5776, b6173, b6174),Const(0),x11799,x11802)
    val x11806 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11806").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:29") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11807 = withCtrl(x11842) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11807").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:21") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11808 = withCtrl(x11842) { CounterChain(List(x11807,x11806)).name("x11808").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // CounterChainNew(List(x11807, x11806))
    val x11841 = withCtrl(x11842) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11808).name("x11841").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnrolledForeach(List(b5781, b5444),x11808,Block(Const(())),List(List(b6214), List(b6215)),List(List(b6216), List(b6217)))
    val b6214 = withCtrl(x11841) { CounterIter(x11807, Some(0)).name("b6214") } // b6214
    val b6216 = withCtrl(x11841) { Const(true).name("b6216") } // b6216
    val b6215 = withCtrl(x11841) { CounterIter(x11806, Some(0)).name("b6215") } // b6215
    val b6217 = withCtrl(x11841) { Const(true).name("b6217") } // b6217
    val x11809_d0 = withCtrl(x11841) { Reg(init=Some(0.0)).name("x11809_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11809 = RegNew(Const(0))
    isAccum(x11809_d0) = false
    bufferDepthOf(x11809_d0) = 2
    val x11809_d1 = withCtrl(x11841) { Reg(init=Some(0.0)).name("x11809_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:36:out") } // x11809 = RegNew(Const(0))
    isAccum(x11809_d1) = true
    bufferDepthOf(x11809_d1) = 1
    val x11810 = withCtrl(x11841) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11810").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:67") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11811 = withCtrl(x11841) { Counter(min=Const(0), max=Const(2), step=Const(2), par=1).name("x11811").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:139:54") } // CounterNew(Const(0),Const(2),Const(2),Const(1))
    val x11812 = withCtrl(x11841) { CounterChain(List(x11811,x11810)).name("x11812").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // CounterChainNew(List(x11811, x11810))
    val x11834 = withCtrl(x11841) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11812).name("x11834").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // UnrolledReduce(List(b6216, b6217, b5781, b5444),x11812,x11809,Block((x11809) => Const(())),List(List(b6222), List(b6223)),List(List(b6224), List(b6225)))
    val b6222 = withCtrl(x11834) { CounterIter(x11811, Some(0)).name("b6222") } // b6222
    val b6224 = withCtrl(x11834) { Const(true).name("b6224") } // b6224
    val b6223 = withCtrl(x11834) { CounterIter(x11810, None).name("b6223") } // b6223
    val b6225 = withCtrl(x11834) { Const(true).name("b6225") } // b6225
    val x11813 = withCtrl(x11834) { OpDef(op=FixSla, inputs=List(b6214, Const(1))).name("x11813").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:44") } // FixLsh(b6214,Const(1))
    val x11814 = withCtrl(x11834) { OpDef(op=FixAdd, inputs=List(x11813, b6222)).name("x11814").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:47") } // FixAdd(x11813,b6222)
    val x11815 = withCtrl(x11834) { OpDef(op=FixSla, inputs=List(b6215, Const(1))).name("x11815").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:54") } // FixLsh(b6215,Const(1))
    val x11816 = withCtrl(x11834) { OpDef(op=FixAdd, inputs=List(x11815, b6223)).name("x11816").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:57") } // FixAdd(x11815,b6223)
    val x11817 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(b6224, b6225)).name("x11817").srcCtx("UnrollingBase.scala:28:66") } // And(b6224,b6225)
    val x11818 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(b6216, b6217)).name("x11818").srcCtx("UnrollingBase.scala:28:66") } // And(b6216,b6217)
    val x11819 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(b5781, b5444)).name("x11819").srcCtx("UnrollingBase.scala:28:66") } // And(b5781,b5444)
    val x11820 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(x11817, x11818)).name("x11820").srcCtx("UnrollingBase.scala:28:66") } // And(x11817,x11818)
    val x11821 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(x11820, x11819)).name("x11821").srcCtx("UnrollingBase.scala:28:66") } // And(x11820,x11819)
    val x11822 = withCtrl(x11834) { LoadBanks(List(x11460_d0_b0), List(x11814, x11816)).name("x11822").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:42") } // ParSRAMLoad(x11460,List(List(x11814, x11816)),List(x11821))
    val x11823 = withCtrl(x11834) { x11822 } // VectorApply(x11822,0)
    val x11824 = withCtrl(x11834) { LoadBanks(List(x11125_d0_b0), List(b5777)).name("x11824").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:72") } // SRAMLoad(x11125,ArrayBuffer(Const(64)),List(b5777),Const(0),x11821)
    val x11825 = withCtrl(x11834) { OpDef(op=FixAdd, inputs=List(x11823, x11824)).name("x11825").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:63") } // FixAdd(x11823,x11824)
    val x11826 = withCtrl(x11834) { OpDef(op=FixMax, inputs=List(Const(0.0), x11825)).name("x11826").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:140:18") } // Max(Const(0),x11825)
    val x11827 = withCtrl(x11834) { ReadMem(x11809_d1).name("x11827").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegRead(x11809)
    val x11828 = withCtrl(x11834) { OpDef(op=FixEql, inputs=List(b6222, Const(0))).name("x11828").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6222,Const(0))
    val x11829 = withCtrl(x11834) { OpDef(op=FixEql, inputs=List(b6223, Const(0))).name("x11829").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // FixEql(b6223,Const(0))
    val x11830 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(x11828, x11829)).name("x11830").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // And(x11828,x11829)
    val x11831 = withCtrl(x11834) { ReduceAccumOp(op=FixMax, input=x11826, accum=x11827).name("x11831").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:29") } // Max(x11826,x11827)
    val x11832 = withCtrl(x11834) { OpDef(op=BitAnd, inputs=List(x11818, x11819)).name("x11832").srcCtx("UnrollingBase.scala:28:66") } // And(x11818,x11819)
    val x11833_x11809_d0 = withCtrl(x11834) { WriteMem(x11809_d0, x11831).name("x11833_x11809_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11809,x11831,x11832)
    antiDepsOf(x11833_x11809_d0)=List(x11827)
    val x11833_x11809_d1 = withCtrl(x11834) { WriteMem(x11809_d1, x11831).name("x11833_x11809_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:141:15") } // RegWrite(x11809,x11831,x11832)
    antiDepsOf(x11833_x11809_d1)=List(x11827)
    val x11840 = withCtrl(x11841) { UnitController(style=SeqPipe, level=InnerControl).name("x11840").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:138:35") } // UnitPipe(List(b6216, b6217, b5781, b5444),Block(Const(())))
    val x11835 = withCtrl(x11840) { ReadMem(x11809_d0).name("x11835").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:43") } // RegRead(x11809)
    val x11836 = withCtrl(x11840) { OpDef(op=BitAnd, inputs=List(b6216, b6217)).name("x11836").srcCtx("UnrollingBase.scala:28:66") } // And(b6216,b6217)
    val x11837 = withCtrl(x11840) { OpDef(op=BitAnd, inputs=List(b5781, b5444)).name("x11837").srcCtx("UnrollingBase.scala:28:66") } // And(b5781,b5444)
    val x11838 = withCtrl(x11840) { OpDef(op=BitAnd, inputs=List(x11836, x11837)).name("x11838").srcCtx("UnrollingBase.scala:28:66") } // And(x11836,x11837)
    val x11839 = withCtrl(x11840) { StoreBanks(List(List(x11421_d0_b0)), List(b5777, b6214, b6215), x11835).name("x11839").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:142:37") } // SRAMStore(x11421,ArrayBuffer(Const(50), Const(4), Const(4)),List(b5777, b6214, b6215),Const(0),x11835,x11838)
    val x11844_d0_b0 = withCtrl(x12028) { SRAM(size=640, banking=Strided(banks=1, stride=128)).name("x11844_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:147:32:tmp4_SRAM") } // x11844 = SRAMNew(ArrayBuffer(Const(5), Const(128)))
    isAccum(x11844_d0_b0) = false
    bufferDepthOf(x11844_d0_b0) = 3
    staticDimsOf(x11844_d0_b0) = List(5, 128)
    val x11845 = withCtrl(x12028) { Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x11845").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:148:26") } // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x11846 = withCtrl(x12028) { CounterChain(List(x11845)).name("x11846").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:148:39") } // CounterChainNew(List(x11845))
    val x11933 = withCtrl(x12028) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11846).name("x11933").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:148:39") } // UnrolledForeach(List(b5444),x11846,Block(Const(())),List(List(b6260)),List(List(b6261)))
    val b6260 = withCtrl(x11933) { CounterIter(x11845, Some(0)).name("b6260") } // b6260
    val b6261 = withCtrl(x11933) { Const(true).name("b6261") } // b6261
    val x11847_d0_b0 = withCtrl(x11933) { SRAM(size=5120, banking=Strided(banks=16, stride=1)).name("x11847_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:149:36:c4_row_SRAM") } // x11847 = SRAMNew(ArrayBuffer(Const(5), Const(4), Const(4), Const(64)))
    isAccum(x11847_d0_b0) = false
    bufferDepthOf(x11847_d0_b0) = 2
    staticDimsOf(x11847_d0_b0) = List(5, 4, 4, 64)
    val x11849 = withCtrl(x11933) { UnitController(style=SeqPipe, level=InnerControl).name("x11849").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:148:39") } // UnitPipe(List(b6261, b5444),Block(Const(())))
    val x11848 = withCtrl(x11849) { OpDef(op=FixAdd, inputs=List(b6260, Const(1))).name("x11848").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:35") } // FixAdd(b6260,Const(1))
    val x11850 = withCtrl(x11933) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11850").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11851 = withCtrl(x11933) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11851").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11852 = withCtrl(x11933) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11852").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11853 = withCtrl(x11933) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11853").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11854 = withCtrl(x11933) { CounterChain(List(x11850,x11851,x11852,x11853)).name("x11854").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterChainNew(List(x11850, x11851, x11852, x11853))
    val x11896 = withCtrl(x11933) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11854).name("x11896").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // UnrolledForeach(List(b6261, b5444),x11854,Block(Const(())),List(List(b6270), List(b6271), List(b6272), List(b6273)),List(List(b6274), List(b6275), List(b6276), List(b6277)))
    val b6270 = withCtrl(x11896) { CounterIter(x11850, Some(0)).name("b6270") } // b6270
    val b6274 = withCtrl(x11896) { Const(true).name("b6274") } // b6274
    val b6271 = withCtrl(x11896) { CounterIter(x11851, Some(0)).name("b6271") } // b6271
    val b6275 = withCtrl(x11896) { Const(true).name("b6275") } // b6275
    val b6272 = withCtrl(x11896) { CounterIter(x11852, Some(0)).name("b6272") } // b6272
    val b6276 = withCtrl(x11896) { Const(true).name("b6276") } // b6276
    val b6273 = withCtrl(x11896) { CounterIter(x11853, Some(0)).name("b6273") } // b6273
    val b6277 = withCtrl(x11896) { Const(true).name("b6277") } // b6277
    val b12095 = withCtrl(x11896) { StreamOut(field="offset").name("b12095").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // x11855 = StreamOutNew(BurstCmdBus)
    isAccum(b12095) = false
    bufferDepthOf(b12095) = 1
    val b12096 = withCtrl(x11896) { StreamOut(field="size").name("b12096").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // x11855 = StreamOutNew(BurstCmdBus)
    isAccum(b12096) = false
    bufferDepthOf(b12096) = 1
    val x11856 = withCtrl(x11896) { StreamIn(field="data").name("x11856").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // x11856 = StreamInNew(BurstDataBus())
    isAccum(x11856) = false
    bufferDepthOf(x11856) = 1
    val x11882 = withCtrl(x11896) { UnitController(style=SeqPipe, level=InnerControl).name("x11882").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // UnitPipe(List(b6274, b6275, b6276, b6277, b6261, b5444),Block(x11881))
    val x11857 = withCtrl(x11882) { OpDef(op=FixAdd, inputs=List(b6260, b6270)).name("x11857").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixAdd(b6260,b6270)
    val x11858 = withCtrl(x11882) { x11857 } // FixConvert(x11857,TRUE,_32,_0) (Same Type. No op)
    val x11859 = withCtrl(x11882) { OpDef(op=FixSla, inputs=List(x11858, Const(12))).name("x11859").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixLsh(x11858,Const(12))
    val x11860 = withCtrl(x11882) { b6271 } // FixConvert(b6271,TRUE,_32,_0) (Same Type. No op)
    val x11861 = withCtrl(x11882) { OpDef(op=FixSla, inputs=List(x11860, Const(10))).name("x11861").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixLsh(x11860,Const(10))
    val x11862 = withCtrl(x11882) { b6272 } // FixConvert(b6272,TRUE,_32,_0) (Same Type. No op)
    val x11863 = withCtrl(x11882) { OpDef(op=FixSla, inputs=List(x11862, Const(8))).name("x11863").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixLsh(x11862,Const(8))
    val x11864 = withCtrl(x11882) { b6273 } // FixConvert(b6273,TRUE,_32,_0) (Same Type. No op)
    val x11865 = withCtrl(x11882) { OpDef(op=FixSla, inputs=List(x11864, Const(6))).name("x11865").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixLsh(x11864,Const(6))
    val x11866 = withCtrl(x11882) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11867 = withCtrl(x11882) { OpDef(op=FixAdd, inputs=List(x11859, x11861)).name("x11867").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixAdd(x11859,x11861)
    val x11868 = withCtrl(x11882) { OpDef(op=FixAdd, inputs=List(x11863, x11865)).name("x11868").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixAdd(x11863,x11865)
    val x11869 = withCtrl(x11882) { OpDef(op=FixAdd, inputs=List(x11867, x11868)).name("x11869").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixAdd(x11867,x11868)
    val x11870 = withCtrl(x11882) { OpDef(op=FixAdd, inputs=List(x11869, x11866)).name("x11870").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixAdd(x11869,x11866)
    val x11871 = withCtrl(x11882) { OpDef(op=FixSla, inputs=List(x11870, Const(2))).name("x11871").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixLsh(x11870,Const(2))
    val x11872 = withCtrl(x11882) { x11871 } // FixConvert(x11871,TRUE,_64,_0)
    val x11873 = withCtrl(x11882) { DramAddress(x10992).name("x11873").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // GetDRAMAddress(x10992)
    val x11875_x11874 = withCtrl(x11882) { OpDef(op=FixAdd, inputs=List(x11872, x11873)).name("x11875_x11874").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FixAdd(x11872,x11873)
    // x11875 = SimpleStruct(ArrayBuffer((offset,x11874), (size,Const(256)), (isLoad,Const(true))))
    val x11876 = withCtrl(x11882) { OpDef(op=BitAnd, inputs=List(b6274, b6275)).name("x11876").srcCtx("UnrollingBase.scala:28:66") } // And(b6274,b6275)
    val x11877 = withCtrl(x11882) { OpDef(op=BitAnd, inputs=List(b6276, b6277)).name("x11877").srcCtx("UnrollingBase.scala:28:66") } // And(b6276,b6277)
    val x11878 = withCtrl(x11882) { OpDef(op=BitAnd, inputs=List(b6261, b5444)).name("x11878").srcCtx("UnrollingBase.scala:28:66") } // And(b6261,b5444)
    val x11879 = withCtrl(x11882) { OpDef(op=BitAnd, inputs=List(x11876, x11877)).name("x11879").srcCtx("UnrollingBase.scala:28:66") } // And(x11876,x11877)
    val x11880 = withCtrl(x11882) { OpDef(op=BitAnd, inputs=List(x11879, x11878)).name("x11880").srcCtx("UnrollingBase.scala:28:66") } // And(x11879,x11878)
    val x11881_b12097_b12095 = withCtrl(x11882) { WriteMem(b12095, x11875_x11874).name("x11881_b12097_b12095").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // StreamWrite(x11855,x11875,x11880)
    val x11881_b12098_b12096 = withCtrl(x11882) { WriteMem(b12096, Const(256)).name("x11881_b12098_b12096").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // StreamWrite(x11855,x11875,x11880)
    val x11883 = withCtrl(x11896) { FringeDenseLoad(dram=List(x10992), cmdStream=List(b12095, b12096), dataStream=List(x11856)).name("x11883").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // FringeDenseLoad(x10992,x11855,x11856)
    val x11884 = withCtrl(x11896) { Counter(min=Const(0), max=Const(64), step=Const(1), par=16).name("x11884").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterNew(Const(0),Const(64),Const(1),Const(16))
    val x11885 = withCtrl(x11896) { CounterChain(List(x11884)).name("x11885").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // CounterChainNew(List(x11884))
    val x11895 = withCtrl(x11896) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11885).name("x11895").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // UnrolledForeach(List(b6274, b6275, b6276, b6277, b6261, b5444),x11885,Block(Const(())),List(List(b6309)),List(List(b6310)))
    val b6309 = withCtrl(x11895) { CounterIter(x11884, None).name("b6309") } // b6309
    val b6310 = withCtrl(x11895) { Const(true).name("b6310") } // b6310
    val x11886 = withCtrl(x11895) { OpDef(op=BitAnd, inputs=List(b6310, b6274)).name("x11886").srcCtx("UnrollingBase.scala:28:66") } // And(b6310,b6274)
    val x11887 = withCtrl(x11895) { OpDef(op=BitAnd, inputs=List(b6275, b6276)).name("x11887").srcCtx("UnrollingBase.scala:28:66") } // And(b6275,b6276)
    val x11888 = withCtrl(x11895) { OpDef(op=BitAnd, inputs=List(b6277, b6261)).name("x11888").srcCtx("UnrollingBase.scala:28:66") } // And(b6277,b6261)
    val x11889 = withCtrl(x11895) { OpDef(op=BitAnd, inputs=List(x11886, x11887)).name("x11889").srcCtx("UnrollingBase.scala:28:66") } // And(x11886,x11887)
    val x11890 = withCtrl(x11895) { OpDef(op=BitAnd, inputs=List(x11888, b5444)).name("x11890").srcCtx("UnrollingBase.scala:28:66") } // And(x11888,b5444)
    val x11891 = withCtrl(x11895) { OpDef(op=BitAnd, inputs=List(x11889, x11890)).name("x11891").srcCtx("UnrollingBase.scala:28:66") } // And(x11889,x11890)
    val x11892_x11892 = withCtrl(x11895) { ReadMem(x11856).name("x11892_x11892").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // ParStreamRead(x11856,List(x11891))
    val x11893_x11893 = withCtrl(x11895) { x11892_x11892 } // VectorApply(x11892,0)
    val x11894 = withCtrl(x11895) { StoreBanks(List(List(x11847_d0_b0)), List(b6271, b6272, b6273, b6309), x11893_x11893).name("x11894").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:150:23") } // ParSRAMStore(x11847,List(List(b6271, b6272, b6273, b6309)),List(x11893),List(x11891))
    val x11897 = withCtrl(x11933) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11897").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:151:21") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11898 = withCtrl(x11933) { CounterChain(List(x11897)).name("x11898").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:151:26") } // CounterChainNew(List(x11897))
    val x11932 = withCtrl(x11933) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11898).name("x11932").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:151:26") } // UnrolledForeach(List(b6261, b5444),x11898,Block(Const(())),List(List(b6324)),List(List(b6325)))
    val b6324 = withCtrl(x11932) { CounterIter(x11897, Some(0)).name("b6324") } // b6324
    val b6325 = withCtrl(x11932) { Const(true).name("b6325") } // b6325
    val x11899_d0 = withCtrl(x11932) { Reg(init=Some(0.0)).name("x11899_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:152:37:prod") } // x11899 = RegNew(Const(0))
    isAccum(x11899_d0) = false
    bufferDepthOf(x11899_d0) = 2
    val x11899_d1 = withCtrl(x11932) { Reg(init=Some(0.0)).name("x11899_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:152:37:prod") } // x11899 = RegNew(Const(0))
    isAccum(x11899_d1) = true
    bufferDepthOf(x11899_d1) = 1
    val x11900 = withCtrl(x11932) { Counter(min=Const(0), max=Const(4), step=Const(1), par=4).name("x11900").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:152:72") } // CounterNew(Const(0),Const(4),Const(1),Const(4))
    val x11901 = withCtrl(x11932) { Counter(min=Const(0), max=Const(4), step=Const(1), par=1).name("x11901").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:152:59") } // CounterNew(Const(0),Const(4),Const(1),Const(1))
    val x11902 = withCtrl(x11932) { Counter(min=Const(0), max=Const(50), step=Const(1), par=1).name("x11902").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:152:51") } // CounterNew(Const(0),Const(50),Const(1),Const(1))
    val x11903 = withCtrl(x11932) { CounterChain(List(x11902,x11901,x11900)).name("x11903").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // CounterChainNew(List(x11902, x11901, x11900))
    val x11923 = withCtrl(x11932) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11903).name("x11923").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // UnrolledReduce(List(b6325, b6261, b5444),x11903,x11899,Block((x11899) => Const(())),List(List(b6331), List(b6332), List(b6333)),List(List(b6334), List(b6335), List(b6336)))
    val b6331 = withCtrl(x11923) { CounterIter(x11902, Some(0)).name("b6331") } // b6331
    val b6334 = withCtrl(x11923) { Const(true).name("b6334") } // b6334
    val b6332 = withCtrl(x11923) { CounterIter(x11901, Some(0)).name("b6332") } // b6332
    val b6335 = withCtrl(x11923) { Const(true).name("b6335") } // b6335
    val b6333 = withCtrl(x11923) { CounterIter(x11900, None).name("b6333") } // b6333
    val b6336 = withCtrl(x11923) { Const(true).name("b6336") } // b6336
    val x11904 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(b6334, b6335)).name("x11904").srcCtx("UnrollingBase.scala:28:66") } // And(b6334,b6335)
    val x11905 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(b6336, b6325)).name("x11905").srcCtx("UnrollingBase.scala:28:66") } // And(b6336,b6325)
    val x11906 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(b6261, b5444)).name("x11906").srcCtx("UnrollingBase.scala:28:66") } // And(b6261,b5444)
    val x11907 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(x11904, x11905)).name("x11907").srcCtx("UnrollingBase.scala:28:66") } // And(x11904,x11905)
    val x11908 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(x11907, x11906)).name("x11908").srcCtx("UnrollingBase.scala:28:66") } // And(x11907,x11906)
    val x11909 = withCtrl(x11923) { LoadBanks(List(x11421_d0_b0), List(b6332, b6332, b6331)).name("x11909").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:153:24") } // SRAMLoad(x11421,ArrayBuffer(Const(50), Const(4), Const(4)),List(b6332, b6332, b6331),Const(0),x11908)
    val x11910 = withCtrl(x11923) { LoadBanks(List(x11847_d0_b0), List(b6324, b6331, b6332, b6333)).name("x11910").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:153:45") } // ParSRAMLoad(x11847,List(List(b6324, b6331, b6332, b6333)),List(x11908))
    val x11911 = withCtrl(x11923) { x11910 } // VectorApply(x11910,0)
    val x11912 = withCtrl(x11923) { OpDef(op=FixMul, inputs=List(x11909, x11911)).name("x11912").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:153:32") } // FixMul(x11909,x11911)
    val x11913 = withCtrl(x11923) { ReadMem(x11899_d1).name("x11913").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // RegRead(x11899)
    val x11914 = withCtrl(x11923) { OpDef(op=FixEql, inputs=List(b6331, Const(0))).name("x11914").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // FixEql(b6331,Const(0))
    val x11915 = withCtrl(x11923) { OpDef(op=FixEql, inputs=List(b6332, Const(0))).name("x11915").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // FixEql(b6332,Const(0))
    val x11916 = withCtrl(x11923) { OpDef(op=FixEql, inputs=List(b6333, Const(0))).name("x11916").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // FixEql(b6333,Const(0))
    val x11917 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(x11914, x11915)).name("x11917").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // And(x11914,x11915)
    val x11918 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(x11917, x11916)).name("x11918").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // And(x11917,x11916)
    val x11919 = withCtrl(x11923) { ReduceAccumOp(op=FixAdd, input=x11912, accum=x11913).name("x11919").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:16") } // FixAdd(x11912,x11913)
    val x11920 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(b6325, b6261)).name("x11920").srcCtx("UnrollingBase.scala:28:66") } // And(b6325,b6261)
    val x11921 = withCtrl(x11923) { OpDef(op=BitAnd, inputs=List(x11920, b5444)).name("x11921").srcCtx("UnrollingBase.scala:28:66") } // And(x11920,b5444)
    val x11922_x11899_d0 = withCtrl(x11923) { WriteMem(x11899_d0, x11919).name("x11922_x11899_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // RegWrite(x11899,x11919,x11921)
    antiDepsOf(x11922_x11899_d0)=List(x11913)
    val x11922_x11899_d1 = withCtrl(x11923) { WriteMem(x11899_d1, x11919).name("x11922_x11899_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:154:14") } // RegWrite(x11899,x11919,x11921)
    antiDepsOf(x11922_x11899_d1)=List(x11913)
    val x11931 = withCtrl(x11932) { UnitController(style=SeqPipe, level=InnerControl).name("x11931").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:151:26") } // UnitPipe(List(b6325, b6261, b5444),Block(Const(())))
    val x11924 = withCtrl(x11931) { OpDef(op=BitAnd, inputs=List(b6325, b6261)).name("x11924").srcCtx("UnrollingBase.scala:28:66") } // And(b6325,b6261)
    val x11925 = withCtrl(x11931) { OpDef(op=BitAnd, inputs=List(x11924, b5444)).name("x11925").srcCtx("UnrollingBase.scala:28:66") } // And(x11924,b5444)
    val x11926 = withCtrl(x11931) { LoadBanks(List(x11144_d0_b0), List(b6324, b6260)).name("x11926").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:155:73") } // SRAMLoad(x11144,ArrayBuffer(Const(5), Const(128)),List(b6324, b6260),Const(0),x11925)
    val x11927 = withCtrl(x11931) { ReadMem(x11899_d0).name("x11927").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:155:58") } // RegRead(x11899)
    val x11928 = withCtrl(x11931) { OpDef(op=FixAdd, inputs=List(x11927, x11926)).name("x11928").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:155:64") } // FixAdd(x11927,x11926)
    val x11929 = withCtrl(x11931) { OpDef(op=FixMax, inputs=List(Const(0.0), x11928)).name("x11929").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:155:43") } // Max(Const(0),x11928)
    val x11930 = withCtrl(x11931) { StoreBanks(List(List(x11844_d0_b0)), List(b6324, b6260), x11929).name("x11930").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:155:38") } // SRAMStore(x11844,ArrayBuffer(Const(5), Const(128)),List(b6324, b6260),Const(0),x11929,x11925)
    val x11934_d0_b0 = withCtrl(x12028) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x11934_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:159:32:tmp5_SRAM") } // x11934 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x11934_d0_b0) = false
    bufferDepthOf(x11934_d0_b0) = 2
    staticDimsOf(x11934_d0_b0) = List(32)
    val x11935_d0_b0 = withCtrl(x12028) { SRAM(size=6400, banking=Strided(banks=16, stride=1)).name("x11935_d0_b0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:160:34:c6_row_SRAM") } // x11935 = SRAMNew(ArrayBuffer(Const(10), Const(5), Const(128)))
    isAccum(x11935_d0_b0) = false
    bufferDepthOf(x11935_d0_b0) = 2
    staticDimsOf(x11935_d0_b0) = List(10, 5, 128)
    val x11936 = withCtrl(x12028) { Counter(min=Const(0), max=Const(10), step=Const(1), par=1).name("x11936").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // CounterNew(Const(0),Const(10),Const(1),Const(1))
    val x11937 = withCtrl(x12028) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11937").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11938 = withCtrl(x12028) { CounterChain(List(x11936,x11937)).name("x11938").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // CounterChainNew(List(x11936, x11937))
    val x11967 = withCtrl(x12028) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11938).name("x11967").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // UnrolledForeach(List(b5444),x11938,Block(Const(())),List(List(b6372), List(b6373)),List(List(b6374), List(b6375)))
    val b6372 = withCtrl(x11967) { CounterIter(x11936, Some(0)).name("b6372") } // b6372
    val b6374 = withCtrl(x11967) { Const(true).name("b6374") } // b6374
    val b6373 = withCtrl(x11967) { CounterIter(x11937, Some(0)).name("b6373") } // b6373
    val b6375 = withCtrl(x11967) { Const(true).name("b6375") } // b6375
    val b12099 = withCtrl(x11967) { StreamOut(field="offset").name("b12099").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // x11939 = StreamOutNew(BurstCmdBus)
    isAccum(b12099) = false
    bufferDepthOf(b12099) = 1
    val b12100 = withCtrl(x11967) { StreamOut(field="size").name("b12100").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // x11939 = StreamOutNew(BurstCmdBus)
    isAccum(b12100) = false
    bufferDepthOf(b12100) = 1
    val x11940 = withCtrl(x11967) { StreamIn(field="data").name("x11940").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // x11940 = StreamInNew(BurstDataBus())
    isAccum(x11940) = false
    bufferDepthOf(x11940) = 1
    val x11956 = withCtrl(x11967) { UnitController(style=SeqPipe, level=InnerControl).name("x11956").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // UnitPipe(List(b6374, b6375, b5444),Block(x11955))
    val x11941 = withCtrl(x11956) { b6372 } // FixConvert(b6372,TRUE,_32,_0) (Same Type. No op)
    val x11942 = withCtrl(x11956) { OpDef(op=FixMul, inputs=List(x11941, Const(500))).name("x11942").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FixMul(x11941,Const(500))
    val x11943 = withCtrl(x11956) { b6373 } // FixConvert(b6373,TRUE,_32,_0) (Same Type. No op)
    val x11944 = withCtrl(x11956) { OpDef(op=FixMul, inputs=List(x11943, Const(5))).name("x11944").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FixMul(x11943,Const(5))
    val x11945 = withCtrl(x11956) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x11946 = withCtrl(x11956) { OpDef(op=FixAdd, inputs=List(x11942, x11944)).name("x11946").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FixAdd(x11942,x11944)
    val x11947 = withCtrl(x11956) { OpDef(op=FixAdd, inputs=List(x11946, x11945)).name("x11947").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FixAdd(x11946,x11945)
    val x11948 = withCtrl(x11956) { OpDef(op=FixSla, inputs=List(x11947, Const(2))).name("x11948").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FixLsh(x11947,Const(2))
    val x11949 = withCtrl(x11956) { x11948 } // FixConvert(x11948,TRUE,_64,_0)
    val x11950 = withCtrl(x11956) { DramAddress(x10994).name("x11950").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // GetDRAMAddress(x10994)
    val x11952_x11951 = withCtrl(x11956) { OpDef(op=FixAdd, inputs=List(x11949, x11950)).name("x11952_x11951").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FixAdd(x11949,x11950)
    // x11952 = SimpleStruct(ArrayBuffer((offset,x11951), (size,Const(512)), (isLoad,Const(true))))
    val x11953 = withCtrl(x11956) { OpDef(op=BitAnd, inputs=List(b6374, b6375)).name("x11953").srcCtx("UnrollingBase.scala:28:66") } // And(b6374,b6375)
    val x11954 = withCtrl(x11956) { OpDef(op=BitAnd, inputs=List(x11953, b5444)).name("x11954").srcCtx("UnrollingBase.scala:28:66") } // And(x11953,b5444)
    val x11955_b12101_b12099 = withCtrl(x11956) { WriteMem(b12099, x11952_x11951).name("x11955_b12101_b12099").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // StreamWrite(x11939,x11952,x11954)
    val x11955_b12102_b12100 = withCtrl(x11956) { WriteMem(b12100, Const(512)).name("x11955_b12102_b12100").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // StreamWrite(x11939,x11952,x11954)
    val x11957 = withCtrl(x11967) { FringeDenseLoad(dram=List(x10994), cmdStream=List(b12099, b12100), dataStream=List(x11940)).name("x11957").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // FringeDenseLoad(x10994,x11939,x11940)
    val x11958 = withCtrl(x11967) { Counter(min=Const(0), max=Const(128), step=Const(1), par=16).name("x11958").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // CounterNew(Const(0),Const(128),Const(1),Const(16))
    val x11959 = withCtrl(x11967) { CounterChain(List(x11958)).name("x11959").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // CounterChainNew(List(x11958))
    val x11966 = withCtrl(x11967) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11959).name("x11966").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // UnrolledForeach(List(b6374, b6375, b5444),x11959,Block(Const(())),List(List(b6397)),List(List(b6398)))
    val b6397 = withCtrl(x11966) { CounterIter(x11958, None).name("b6397") } // b6397
    val b6398 = withCtrl(x11966) { Const(true).name("b6398") } // b6398
    val x11960 = withCtrl(x11966) { OpDef(op=BitAnd, inputs=List(b6398, b6374)).name("x11960").srcCtx("UnrollingBase.scala:28:66") } // And(b6398,b6374)
    val x11961 = withCtrl(x11966) { OpDef(op=BitAnd, inputs=List(b6375, b5444)).name("x11961").srcCtx("UnrollingBase.scala:28:66") } // And(b6375,b5444)
    val x11962 = withCtrl(x11966) { OpDef(op=BitAnd, inputs=List(x11960, x11961)).name("x11962").srcCtx("UnrollingBase.scala:28:66") } // And(x11960,x11961)
    val x11963_x11963 = withCtrl(x11966) { ReadMem(x11940).name("x11963_x11963").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // ParStreamRead(x11940,List(x11962))
    val x11964_x11964 = withCtrl(x11966) { x11963_x11963 } // VectorApply(x11963,0)
    val x11965 = withCtrl(x11966) { StoreBanks(List(List(x11935_d0_b0)), List(b6372, b6373, b6397), x11964_x11964).name("x11965").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:161:21") } // ParSRAMStore(x11935,List(List(b6372, b6373, b6397)),List(x11964),List(x11962))
    val x11968 = withCtrl(x12028) { Counter(min=Const(0), max=Const(10), step=Const(1), par=1).name("x11968").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:162:25") } // CounterNew(Const(0),Const(10),Const(1),Const(1))
    val x11969 = withCtrl(x12028) { CounterChain(List(x11968)).name("x11969").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:162:38") } // CounterChainNew(List(x11968))
    val x11995 = withCtrl(x12028) { LoopController(style=MetaPipe, level=OuterControl, cchain=x11969).name("x11995").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:162:38") } // UnrolledForeach(List(b5444),x11969,Block(Const(())),List(List(b6409)),List(List(b6410)))
    val b6409 = withCtrl(x11995) { CounterIter(x11968, Some(0)).name("b6409") } // b6409
    val b6410 = withCtrl(x11995) { Const(true).name("b6410") } // b6410
    val x11970_d0 = withCtrl(x11995) { Reg(init=Some(0.0)).name("x11970_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:163:35:prod") } // x11970 = RegNew(Const(0))
    isAccum(x11970_d0) = false
    bufferDepthOf(x11970_d0) = 2
    val x11970_d1 = withCtrl(x11995) { Reg(init=Some(0.0)).name("x11970_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:163:35:prod") } // x11970 = RegNew(Const(0))
    isAccum(x11970_d1) = true
    bufferDepthOf(x11970_d1) = 1
    val x11971 = withCtrl(x11995) { Counter(min=Const(0), max=Const(128), step=Const(16), par=1).name("x11971").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:163:58") } // CounterNew(Const(0),Const(128),Const(16),Const(1))
    val x11972 = withCtrl(x11995) { Counter(min=Const(0), max=Const(5), step=Const(1), par=1).name("x11972").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:163:48") } // CounterNew(Const(0),Const(5),Const(1),Const(1))
    val x11973 = withCtrl(x11995) { CounterChain(List(x11972,x11971)).name("x11973").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // CounterChainNew(List(x11972, x11971))
    val x11988 = withCtrl(x11995) { LoopController(style=InnerPipe, level=InnerControl, cchain=x11973).name("x11988").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // UnrolledReduce(List(b6410, b5444),x11973,x11970,Block((x11970) => Const(())),List(List(b6415), List(b6416)),List(List(b6417), List(b6418)))
    val b6415 = withCtrl(x11988) { CounterIter(x11972, Some(0)).name("b6415") } // b6415
    val b6417 = withCtrl(x11988) { Const(true).name("b6417") } // b6417
    val b6416 = withCtrl(x11988) { CounterIter(x11971, None).name("b6416") } // b6416
    val b6418 = withCtrl(x11988) { Const(true).name("b6418") } // b6418
    val x11974 = withCtrl(x11988) { OpDef(op=BitAnd, inputs=List(b6417, b6418)).name("x11974").srcCtx("UnrollingBase.scala:28:66") } // And(b6417,b6418)
    val x11975 = withCtrl(x11988) { OpDef(op=BitAnd, inputs=List(b6410, b5444)).name("x11975").srcCtx("UnrollingBase.scala:28:66") } // And(b6410,b5444)
    val x11976 = withCtrl(x11988) { OpDef(op=BitAnd, inputs=List(x11974, x11975)).name("x11976").srcCtx("UnrollingBase.scala:28:66") } // And(x11974,x11975)
    val x11977 = withCtrl(x11988) { LoadBanks(List(x11844_d0_b0), List(b6415, b6416)).name("x11977").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:164:22") } // ParSRAMLoad(x11844,List(List(b6415, b6416)),List(x11976))
    val x11978 = withCtrl(x11988) { x11977 } // VectorApply(x11977,0)
    val x11979 = withCtrl(x11988) { LoadBanks(List(x11935_d0_b0), List(b6409, b6415, b6416)).name("x11979").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:164:47") } // ParSRAMLoad(x11935,List(List(b6409, b6415, b6416)),List(x11976))
    val x11980 = withCtrl(x11988) { x11979 } // VectorApply(x11979,0)
    val x11981 = withCtrl(x11988) { OpDef(op=FixMul, inputs=List(x11978, x11980)).name("x11981").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:164:34") } // FixMul(x11978,x11980)
    val x11982 = withCtrl(x11988) { ReadMem(x11970_d1).name("x11982").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // RegRead(x11970)
    val x11983 = withCtrl(x11988) { OpDef(op=FixEql, inputs=List(b6415, Const(0))).name("x11983").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // FixEql(b6415,Const(0))
    val x11984 = withCtrl(x11988) { OpDef(op=FixEql, inputs=List(b6416, Const(0))).name("x11984").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // FixEql(b6416,Const(0))
    val x11985 = withCtrl(x11988) { OpDef(op=BitAnd, inputs=List(x11983, x11984)).name("x11985").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // And(x11983,x11984)
    val x11986 = withCtrl(x11988) { ReduceAccumOp(op=FixAdd, input=x11981, accum=x11982).name("x11986").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:14") } // FixAdd(x11981,x11982)
    val x11987_x11970_d0 = withCtrl(x11988) { WriteMem(x11970_d0, x11986).name("x11987_x11970_d0").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // RegWrite(x11970,x11986,x11975)
    antiDepsOf(x11987_x11970_d0)=List(x11982)
    val x11987_x11970_d1 = withCtrl(x11988) { WriteMem(x11970_d1, x11986).name("x11987_x11970_d1").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:165:12") } // RegWrite(x11970,x11986,x11975)
    antiDepsOf(x11987_x11970_d1)=List(x11982)
    val x11994 = withCtrl(x11995) { UnitController(style=SeqPipe, level=InnerControl).name("x11994").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:162:38") } // UnitPipe(List(b6410, b5444),Block(Const(())))
    val x11989 = withCtrl(x11994) { OpDef(op=BitAnd, inputs=List(b6410, b5444)).name("x11989").srcCtx("UnrollingBase.scala:28:66") } // And(b6410,b5444)
    val x11990 = withCtrl(x11994) { LoadBanks(List(x11169_d0_b0), List(b6409)).name("x11990").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:166:50") } // SRAMLoad(x11169,ArrayBuffer(Const(32)),List(b6409),Const(0),x11989)
    val x11991 = withCtrl(x11994) { ReadMem(x11970_d0).name("x11991").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:166:35") } // RegRead(x11970)
    val x11992 = withCtrl(x11994) { OpDef(op=FixAdd, inputs=List(x11991, x11990)).name("x11992").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:166:41") } // FixAdd(x11991,x11990)
    val x11993 = withCtrl(x11994) { StoreBanks(List(List(x11934_d0_b0)), List(b6409), x11992).name("x11993").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:166:28") } // SRAMStore(x11934,ArrayBuffer(Const(32)),List(b6409),Const(0),x11992,x11989)
    val x11996 = withCtrl(x12028) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x11996").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x11997 = withCtrl(x12028) { CounterChain(List(x11996)).name("x11997").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // CounterChainNew(List(x11996))
    val x12027 = withCtrl(x12028) { LoopController(style=StreamPipe, level=OuterControl, cchain=x11997).name("x12027").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // UnrolledForeach(List(b5444),x11997,Block(Const(())),List(List(b6443)),List(List(b6444)))
    val b6443 = withCtrl(x12027) { CounterIter(x11996, Some(0)).name("b6443") } // b6443
    val b6444 = withCtrl(x12027) { Const(true).name("b6444") } // b6444
    val b12103 = withCtrl(x12027) { StreamOut(field="offset").name("b12103").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // x11998 = StreamOutNew(BurstCmdBus)
    isAccum(b12103) = false
    bufferDepthOf(b12103) = 1
    val b12104 = withCtrl(x12027) { StreamOut(field="size").name("b12104").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // x11998 = StreamOutNew(BurstCmdBus)
    isAccum(b12104) = false
    bufferDepthOf(b12104) = 1
    val x11999 = withCtrl(x12027) { StreamOut(field="data").name("x11999").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // x11999 = StreamOutNew(BurstFullDataBus())
    isAccum(x11999) = false
    bufferDepthOf(x11999) = 1
    val x12000 = withCtrl(x12027) { StreamIn(field="ack").name("x12000").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // x12000 = StreamInNew(BurstAckBus)
    isAccum(x12000) = false
    bufferDepthOf(x12000) = 1
    val x12013 = withCtrl(x12027) { UnitController(style=SeqPipe, level=InnerControl).name("x12013").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // UnitPipe(List(b6444, b5444),Block(x12012))
    val x12001 = withCtrl(x12013) { OpDef(op=FixAdd, inputs=List(b5443, b6443)).name("x12001").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // FixAdd(b5443,b6443)
    val x12002 = withCtrl(x12013) { x12001 } // FixConvert(x12001,TRUE,_32,_0) (Same Type. No op)
    val x12003 = withCtrl(x12013) { OpDef(op=FixSla, inputs=List(x12002, Const(5))).name("x12003").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // FixLsh(x12002,Const(5))
    val x12004 = withCtrl(x12013) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x12005 = withCtrl(x12013) { OpDef(op=FixAdd, inputs=List(x12003, x12004)).name("x12005").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // FixAdd(x12003,x12004)
    val x12006 = withCtrl(x12013) { OpDef(op=FixSla, inputs=List(x12005, Const(2))).name("x12006").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // FixLsh(x12005,Const(2))
    val x12007 = withCtrl(x12013) { x12006 } // FixConvert(x12006,TRUE,_64,_0)
    val x12008 = withCtrl(x12013) { DramAddress(x10996).name("x12008").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // GetDRAMAddress(x10996)
    val x12010_x12009 = withCtrl(x12013) { OpDef(op=FixAdd, inputs=List(x12007, x12008)).name("x12010_x12009").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // FixAdd(x12007,x12008)
    // x12010 = SimpleStruct(ArrayBuffer((offset,x12009), (size,Const(128)), (isLoad,Const(false))))
    val x12011 = withCtrl(x12013) { OpDef(op=BitAnd, inputs=List(b6444, b5444)).name("x12011").srcCtx("UnrollingBase.scala:28:66") } // And(b6444,b5444)
    val x12012_b12105_b12103 = withCtrl(x12013) { WriteMem(b12103, x12010_x12009).name("x12012_b12105_b12103").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // StreamWrite(x11998,x12010,x12011)
    val x12012_b12106_b12104 = withCtrl(x12013) { WriteMem(b12104, Const(128)).name("x12012_b12106_b12104").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // StreamWrite(x11998,x12010,x12011)
    val x12014 = withCtrl(x12027) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x12014").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x12015 = withCtrl(x12027) { CounterChain(List(x12014)).name("x12015").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // CounterChainNew(List(x12014))
    val x12022 = withCtrl(x12027) { LoopController(style=InnerPipe, level=InnerControl, cchain=x12015).name("x12022").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // UnrolledForeach(List(b6444, b5444),x12015,Block(Const(())),List(List(b6463)),List(List(b6464)))
    val b6463 = withCtrl(x12022) { CounterIter(x12014, None).name("b6463") } // b6463
    val b6464 = withCtrl(x12022) { Const(true).name("b6464") } // b6464
    val x12016 = withCtrl(x12022) { OpDef(op=BitAnd, inputs=List(b6464, b6444)).name("x12016").srcCtx("UnrollingBase.scala:28:66") } // And(b6464,b6444)
    val x12017 = withCtrl(x12022) { OpDef(op=BitAnd, inputs=List(x12016, b5444)).name("x12017").srcCtx("UnrollingBase.scala:28:66") } // And(x12016,b5444)
    val x12018 = withCtrl(x12022) { LoadBanks(List(x11934_d0_b0), List(b6463)).name("x12018").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // ParSRAMLoad(x11934,List(List(b6463)),List(x12017))
    val x12020_x12019 = withCtrl(x12022) { x12018 } // VectorApply(x12018,0)
    // x12020 = SimpleStruct(ArrayBuffer((_1,x12019), (_2,Const(true))))
    val x12021_x12021_x11999 = withCtrl(x12022) { WriteMem(x11999, x12020_x12019).name("x12021_x12021_x11999").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // ParStreamWrite(x11999,List(x12020),List(x12017))
    val x12023 = withCtrl(x12027) { FringeDenseStore(dram=List(x10996), cmdStream=List(b12103, b12104), dataStream=List(x11999), ackStream=List(x12000)).name("x12023").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // FringeDenseStore(x10996,x11998,x11999,x12000)
    val x12026 = withCtrl(x12027) { UnitController(style=SeqPipe, level=InnerControl).name("x12026").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // UnitPipe(List(b6444, b5444),Block(Const(())))
    val x12024 = withCtrl(x12026) { OpDef(op=BitAnd, inputs=List(b6444, b5444)).name("x12024").srcCtx("UnrollingBase.scala:28:66") } // And(b6444,b5444)
    val x12025_x12025 = withCtrl(x12026) { ReadMem(x12000).name("x12025_x12025").srcCtx("lenet_loops__batch_par_1_conv1_par_2_conv2_par_4_mat1_par_1_mat2_par_1.scala:169:44") } // StreamRead(x12000,x12024)
    }; split2
    }; split1
    
  }
}
