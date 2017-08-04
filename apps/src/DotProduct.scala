import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x1091_b1159_x1100_b1161_s = Scalar("x1091_b1159_x1100_b1161")
    val b_oc = OffChip("b")
    val x1091_b1158_x1100_b1160_s = Scalar("x1091_b1158_x1100_b1160")
    val x1112_x1123_s = Scalar("x1112_x1123")
    val N_argin = ArgIn("N").bound(1920000)
    val x1071_b1155_x1080_b1157_s = Scalar("x1071_b1155_x1080_b1157")
    val x1072_x1082_data_s = Scalar("x1072_x1082_data")
    val a_oc = OffChip("a")
    val x1071_b1154_x1080_b1156_s = Scalar("x1071_b1154_x1080_b1156")
    val a_da = DRAMAddress("a", "a")
    val x1068_0_s = Scalar("x1068_0")
    val b_da = DRAMAddress("b", "b")
    val x1067_0_s = Scalar("x1067_0")
    val x1060_x1133_argout = ArgOut("x1060_x1133")
    val x1092_x1102_data_s = Scalar("x1092_x1102_data")
    val x1135 = Sequential(name="x1135",parent=top) { implicit CU => 
      val x1135_unit = CounterChain(name = "x1135_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1131 = MetaPipeline(name="x1131",parent=x1135) { implicit CU => 
      val x1053 = ScalarBuffer(name="x1053").wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x1053.readPort, step=Const(16), par=1) // Counter
      val x1066 = CounterChain(name = "x1066", ctr1).iter(1)
    }
    val x1067_dsp0 = MemoryPipeline(name="x1067_dsp0",parent="x1131") { implicit CU => 
      val x1088 = ScalarFIFO(size=1,name="x1088").wtPort(x1072_x1082_data_s)
      val x1084 = CounterChain.copy("x1089", "x1084")
      val x1114 = CounterChain.copy("x1124_0", "x1114")
      val x1067 = SRAM(size=16,name="x1067",banking = Strided(1)).wtPort(x1088.readPort).rdPort(x1067_0_s).rdAddr(x1114(0)).wtAddr(x1084(0))
    }
    val x1068_dsp0 = MemoryPipeline(name="x1068_dsp0",parent="x1131") { implicit CU => 
      val x1108 = ScalarFIFO(size=1,name="x1108").wtPort(x1092_x1102_data_s)
      val x1104 = CounterChain.copy("x1109", "x1104")
      val x1114 = CounterChain.copy("x1124_0", "x1114")
      val x1068 = SRAM(size=16,name="x1068",banking = Strided(1)).wtPort(x1108.readPort).rdPort(x1068_0_s).rdAddr(x1114(0)).wtAddr(x1104(0))
    }
    val x1090 = StreamController(name="x1090",parent=x1131) { implicit CU => 
      val x1090_unit = CounterChain(name = "x1090_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1081_0 = Pipeline(name="x1081_0",parent=x1090) { implicit CU => 
      val x1074 = CU.temp(None)
      val x1076 = ScalarBuffer(name="x1076").wtPort(a_da)
      val x1066 = CounterChain.copy("x1131", "x1066")
      val x1081_unit = CounterChain(name = "x1081_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1066(0)), Const(2)), op=FixSla, results=List(x1074))
      Stage(operands=List(x1074, CU.load(x1076)), op=FixAdd, results=List(CU.scalarOut(x1071_b1154_x1080_b1156_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1071_b1155_x1080_b1157_s)))
    }
    val x1082 = MemoryController(name="x1082",parent=x1090,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1071_b1154 = ScalarFIFO(size=1,name="offset").wtPort(x1071_b1154_x1080_b1156_s)
      val x1071_b1155 = ScalarFIFO(size=1,name="size").wtPort(x1071_b1155_x1080_b1157_s)
      CU.newSout("data", x1072_x1082_data_s)
    }
    val x1089 = Pipeline(name="x1089",parent=x1090) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1084 = CounterChain(name = "x1084", ctr2).iter(1)
    }
    val x1110 = StreamController(name="x1110",parent=x1131) { implicit CU => 
      val x1110_unit = CounterChain(name = "x1110_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1101_0 = Pipeline(name="x1101_0",parent=x1110) { implicit CU => 
      val x1094 = CU.temp(None)
      val x1096 = ScalarBuffer(name="x1096").wtPort(b_da)
      val x1066 = CounterChain.copy("x1131", "x1066")
      val x1101_unit = CounterChain(name = "x1101_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1066(0)), Const(2)), op=FixSla, results=List(x1094))
      Stage(operands=List(x1094, CU.load(x1096)), op=FixAdd, results=List(CU.scalarOut(x1091_b1158_x1100_b1160_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1091_b1159_x1100_b1161_s)))
    }
    val x1102 = MemoryController(name="x1102",parent=x1110,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1091_b1159 = ScalarFIFO(size=1,name="size").wtPort(x1091_b1159_x1100_b1161_s)
      val x1091_b1158 = ScalarFIFO(size=1,name="offset").wtPort(x1091_b1158_x1100_b1160_s)
      CU.newSout("data", x1092_x1102_data_s)
    }
    val x1109 = Pipeline(name="x1109",parent=x1110) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1104 = CounterChain(name = "x1104", ctr3).iter(1)
    }
    val x1124_0 = Pipeline(name="x1124_0",parent=x1131) { implicit CU => 
      val x1117 = ScalarFIFO(size=1,name="x1117").wtPort(x1068_0_s)
      val x1116 = ScalarFIFO(size=1,name="x1116").wtPort(x1067_0_s)
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1114 = CounterChain(name = "x1114", ctr4).iter(1)
      Stage(operands=List(CU.load(x1116), CU.load(x1117)), op=FixMul, results=List(CU.reduce))
      val (_, rr92) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1124_0")
      Stage(operands=List(rr92), op=Bypass, results=List(CU.scalarOut(x1112_x1123_s)))
    }
    val x1130_0 = Pipeline(name="x1130_0",parent=x1131) { implicit CU => 
      val x1112 = ScalarBuffer(name="x1112").wtPort(x1112_x1123_s)
      val x1130_unit = CounterChain(name = "x1130_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1112)), op=Bypass, results=List(CU.reduce))
      val (_, rr95) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1131")
      Stage(operands=List(rr95), op=Bypass, results=List(CU.scalarOut(x1060_x1133_argout)))
    }
    
  }
}
