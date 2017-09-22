import pir._
import pir.node._
import arch._
import pirc.enums._

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val x1067_x1116_ra_s = Scalar("x1067_x1116_ra")
    val x1091_b1159_x1100_b1161_s = Scalar("x1091_b1159_x1100_b1161")
    val b_oc = OffChip("b")
    val x1112_x1123_s = Scalar("x1112_x1123")
    val N_argin = ArgIn("N").bound(1920000)
    val x1071_b1155_x1080_b1157_s = Scalar("x1071_b1155_x1080_b1157")
    val x1072_x1082_data_s = Scalar("x1072_x1082_data")
    val x1067_x1067_dsp0_bank0_data_s = Scalar("x1067_x1067_dsp0_bank0_data")
    val a_oc = OffChip("a")
    val x1071_b1154_x1080_b1156_s = Scalar("x1071_b1154_x1080_b1156")
    val x1068_x1068_dsp0_bank0_data_s = Scalar("x1068_x1068_dsp0_bank0_data")
    val a_da = DRAMAddress("a", "a")
    val x1068_x1117_ra_s = Scalar("x1068_x1117_ra")
    val x1091_b1160_x1100_b1162_s = Scalar("x1091_b1160_x1100_b1162")
    val b_da = DRAMAddress("b", "b")
    val x1067_x1088_wa_s = Scalar("x1067_x1088_wa")
    val x1068_x1108_wa_s = Scalar("x1068_x1108_wa")
    val x1092_x1102_data_s = Scalar("x1092_x1102_data")
    val x1060_x1133_argout = ArgOut("x1060_x1133")
    val x1135 = Sequential(name="x1135",parent=top) { implicit CU => 
      val x1135_unit = CounterChain(name = "x1135_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1131 = MetaPipeline(name="x1131",parent=x1135) { implicit CU => 
      val x1053 = ScalarBuffer(name="x1053").wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x1053.readPort, step=Const(16), par=1) // Counter
      val x1066 = CounterChain(name = "x1066", ctr1).iter(120000)
    }
    val x1067_dsp0_bank0 = MemoryPipeline(name="x1067_dsp0_bank0",parent="x1131") { implicit CU => 
      val x1088 = ScalarFIFO(size=1,name="x1088").wtPort(x1072_x1082_data_s)
      val b1158 = ScalarFIFO(size=1,name="b1158").wtPort(x1067_x1088_wa_s)
      val b1164 = ScalarFIFO(size=1,name="b1164").wtPort(x1067_x1116_ra_s)
      val x1067 = SRAM(size=1,name="x1067",banking = Strided(1,16)).wtPort(x1088.readPort).rdPort(x1067_x1067_dsp0_bank0_data_s).rdAddr(b1164.readPort).wtAddr(b1158.readPort)
    }
    val x1068_dsp0_bank0 = MemoryPipeline(name="x1068_dsp0_bank0",parent="x1131") { implicit CU => 
      val b1165 = ScalarFIFO(size=1,name="b1165").wtPort(x1068_x1117_ra_s)
      val x1108 = ScalarFIFO(size=1,name="x1108").wtPort(x1092_x1102_data_s)
      val b1163 = ScalarFIFO(size=1,name="b1163").wtPort(x1068_x1108_wa_s)
      val x1068 = SRAM(size=1,name="x1068",banking = Strided(1,16)).wtPort(x1108.readPort).rdPort(x1068_x1068_dsp0_bank0_data_s).rdAddr(b1165.readPort).wtAddr(b1163.readPort)
    }
    val x1090 = StreamController(name="x1090",parent=x1131) { implicit CU => 
      val x1090_unit = CounterChain(name = "x1090_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1081 = Pipeline(name="x1081",parent=x1090) { implicit CU => 
      val x1074 = CU.temp(None)
      val x1076 = ScalarBuffer(name="x1076").wtPort(a_da)
      val x1066 = CounterChain.copy("x1131", "x1066").iterIdx(0, 0)
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
    val x1088 = Pipeline(name="x1088",parent=x1090) { implicit CU => 
      val x1084 = CounterChain.copy("x1089", "x1084").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1084(0))), op=Bypass, results=List(CU.scalarOut(x1067_x1088_wa_s)))
    }
    val x1110 = StreamController(name="x1110",parent=x1131) { implicit CU => 
      val x1110_unit = CounterChain(name = "x1110_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1101 = Pipeline(name="x1101",parent=x1110) { implicit CU => 
      val x1094 = CU.temp(None)
      val x1096 = ScalarBuffer(name="x1096").wtPort(b_da)
      val x1066 = CounterChain.copy("x1131", "x1066").iterIdx(0, 0)
      val x1101_unit = CounterChain(name = "x1101_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1066(0)), Const(2)), op=FixSla, results=List(x1094))
      Stage(operands=List(x1094, CU.load(x1096)), op=FixAdd, results=List(CU.scalarOut(x1091_b1159_x1100_b1161_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1091_b1160_x1100_b1162_s)))
    }
    val x1102 = MemoryController(name="x1102",parent=x1110,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1091_b1160 = ScalarFIFO(size=1,name="size").wtPort(x1091_b1160_x1100_b1162_s)
      val x1091_b1159 = ScalarFIFO(size=1,name="offset").wtPort(x1091_b1159_x1100_b1161_s)
      CU.newSout("data", x1092_x1102_data_s)
    }
    val x1109 = Pipeline(name="x1109",parent=x1110) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1104 = CounterChain(name = "x1104", ctr3).iter(1)
    }
    val x1108 = Pipeline(name="x1108",parent=x1110) { implicit CU => 
      val x1104 = CounterChain.copy("x1109", "x1104").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1104(0))), op=Bypass, results=List(CU.scalarOut(x1068_x1108_wa_s)))
    }
    val x1124 = Pipeline(name="x1124",parent=x1131) { implicit CU => 
      val x1112 = CU.temp(Some(0))
      val x1117 = ScalarFIFO(size=1,name="x1117").wtPort(x1068_x1068_dsp0_bank0_data_s)
      val x1116 = ScalarFIFO(size=1,name="x1116").wtPort(x1067_x1067_dsp0_bank0_data_s)
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1114 = CounterChain(name = "x1114", ctr4).iter(1)
      Stage(operands=List(CU.load(x1116), CU.load(x1117)), op=FixMul, results=List(CU.reduce))
      val (_, rr96) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1124")
      Stage(operands=List(rr96), op=Bypass, results=List(CU.scalarOut(x1112_x1123_s)))
    }
    val x1116 = Pipeline(name="x1116",parent=x1131) { implicit CU => 
      val x1114 = CounterChain.copy("x1124", "x1114").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1114(0))), op=Bypass, results=List(CU.scalarOut(x1067_x1116_ra_s)))
    }
    val x1117 = Pipeline(name="x1117",parent=x1131) { implicit CU => 
      val x1114 = CounterChain.copy("x1124", "x1114").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1114(0))), op=Bypass, results=List(CU.scalarOut(x1068_x1117_ra_s)))
    }
    val x1130 = Pipeline(name="x1130",parent=x1131) { implicit CU => 
      val x1063 = CU.temp(Some(0))
      val x1112 = ScalarBuffer(name="x1112").wtPort(x1112_x1123_s)
      val x1130_unit = CounterChain(name = "x1130_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1112)), op=Bypass, results=List(CU.reduce))
      val (_, rr99) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1131")
      Stage(operands=List(rr99), op=Bypass, results=List(CU.scalarOut(x1060_x1133_argout)))
    }
    
  }
}
