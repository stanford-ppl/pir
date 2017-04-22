import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1067_oc = OffChip("x1067")
    val x1068_x1144_argout = ArgOut("x1068_x1144")
    val x1099_b1170_x1106_b1172_s = Scalar("x1099_b1170_x1106_b1172")
    val x1102_argin = ArgIn("x1102")
    val x1075_x1095_v = Vector("x1075_x1095")
    val x1075_x1126_x1135_v = Vector("x1075_x1126_x1135")
    val x1071_x1139_s = Scalar("x1071_x1139")
    val x1077_b1166_x1084_b1168_s = Scalar("x1077_b1166_x1084_b1168")
    val x1076_x1127_x1135_v = Vector("x1076_x1127_x1135")
    val x1076_x1117_v = Vector("x1076_x1117")
    //val x1078_x1085_s = Scalar("x1078_x1085")
    val x1122_x1133_s = Scalar("x1122_x1133")
    val x1065_oc = OffChip("x1065")
    val x1077_b1165_x1084_b1167_s = Scalar("x1077_b1165_x1084_b1167")
    val x1080_argin = ArgIn("x1080")
    val x1079_x1087_data_v = Vector("x1079_x1087_data")
    val x1099_b1169_x1106_b1171_s = Scalar("x1099_b1169_x1106_b1171")
    //val x1100_x1107_s = Scalar("x1100_x1107")
    val x1061_argin = ArgIn("x1061")
    val x1101_x1109_data_v = Vector("x1101_x1109_data")
    val x1146 = Sequential(name="x1146",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1146_unit = CounterChain(name = "x1146_unit", ctr1)
    }
    val x1142 = MetaPipeline(name="x1142",parent=x1146) { implicit CU => 
      val x1061_x1072 =  ScalarBuffer().wtPort(x1061_argin)
      val ctr2 = Counter(min=Const(0), max=x1061_x1072.load, step=Const(320), par=1) // Counter
      val x1074 = CounterChain(name = "x1074", ctr2)
    }
    val x1075_dsp0 = MemoryPipeline(name="x1075_dsp0",parent="x1142") { implicit CU => 
      val x1095_x1095 =  VectorFIFO(size = 1).wtPort(x1075_x1095_v)
      val x1091 = CounterChain.copy("x1096", "x1091")
      val x1124 = CounterChain.copy("x1135", "x1124")
      val x1075_x1126 =  SRAM(size = 320,banking = Strided(1)).wtPort(x1095_x1095.readPort).rdPort(x1075_x1126_x1135_v).rdAddr(x1124(0)).wtAddr(x1091(0))
      var stage: List[Stage] = Nil
    }
    val x1076_dsp0 = MemoryPipeline(name="x1076_dsp0",parent="x1142") { implicit CU => 
      val x1117_x1117 =  VectorFIFO(size = 1).wtPort(x1076_x1117_v)
      val x1113 = CounterChain.copy("x1118", "x1113")
      val x1124 = CounterChain.copy("x1135", "x1124")
      val x1076_x1127 =  SRAM(size = 320,banking = Strided(1)).wtPort(x1117_x1117.readPort).rdPort(x1076_x1127_x1135_v).rdAddr(x1124(0)).wtAddr(x1113(0))
      var stage: List[Stage] = Nil
    }
    val x1098 = StreamController(name="x1098",parent=x1142) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1098_unit = CounterChain(name = "x1098_unit", ctr3)
    }
    val x1086 = Pipeline(name="x1086",parent=x1098) { implicit CU => 
      val x1081 = CU.temp
      val x1082 = CU.temp
      val x1080 =  ScalarBuffer().wtPort(x1080_argin)
      val x1074 = CounterChain.copy("x1142", "x1074")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1086_unit = CounterChain(name = "x1086_unit", ctr4)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1074(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x1081)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1081), CU.load(stage(1), x1080)), op=FixAdd, results=List(CU.temp(stage(2), x1082), CU.scalarOut(stage(2), x1077_b1165_x1084_b1167_s)))
      Stage(stage(3), operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(stage(3), x1077_b1166_x1084_b1168_s)))
      //Stage(stage(4), operands=List(Const(320)), op=Bypass, results=List(CU.scalarOut(stage(4), x1078_x1085_s)))
    }
    val x1087 = MemoryController(name="x1087",parent=x1098,offchip=x1065_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1077_b1165_x1084_b1167_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1077_b1166_x1084_b1168_s)
      CU.mcvecs += "data" -> x1079_x1087_data_v
    }
    val x1097 = Sequential(name="x1097",parent=x1098) { implicit CU => 
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1097_unit = CounterChain(name = "x1097_unit", ctr5)
    }
    val x1089 = Pipeline(name="x1089",parent=x1097) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1089_unit = CounterChain(name = "x1089_unit", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1096 = Pipeline(name="x1096",parent=x1097) { implicit CU => 
      val x1079_x1093 =  VectorFIFO(size = 1).wtPort(x1079_x1087_data_v)
      val ctr7 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1091 = CounterChain(name = "x1091", ctr7)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1079_x1093.load), op=Bypass, results=List(CU.vecOut(stage(1), x1075_x1095_v)))
    }
    val x1120 = StreamController(name="x1120",parent=x1142) { implicit CU => 
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1120_unit = CounterChain(name = "x1120_unit", ctr8)
    }
    val x1108 = Pipeline(name="x1108",parent=x1120) { implicit CU => 
      val x1103 = CU.temp
      val x1104 = CU.temp
      val x1102 =  ScalarBuffer().wtPort(x1102_argin)
      val x1074 = CounterChain.copy("x1142", "x1074")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1108_unit = CounterChain(name = "x1108_unit", ctr9)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1074(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x1103)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x1103), CU.load(stage(1), x1102)), op=FixAdd, results=List(CU.temp(stage(2), x1104), CU.scalarOut(stage(2), x1099_b1169_x1106_b1171_s)))
      Stage(stage(3), operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(stage(3), x1099_b1170_x1106_b1172_s)))
      //Stage(stage(4), operands=List(Const(320)), op=Bypass, results=List(CU.scalarOut(stage(4), x1100_x1107_s)))
    }
    val x1109 = MemoryController(name="x1109",parent=x1120,offchip=x1067_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1099_b1169_x1106_b1171_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1099_b1170_x1106_b1172_s)
      CU.mcvecs += "data" -> x1101_x1109_data_v
    }
    val x1119 = Sequential(name="x1119",parent=x1120) { implicit CU => 
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1119_unit = CounterChain(name = "x1119_unit", ctr10)
    }
    val x1111 = Pipeline(name="x1111",parent=x1119) { implicit CU => 
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1111_unit = CounterChain(name = "x1111_unit", ctr11)
      var stage: List[Stage] = Nil
    }
    val x1118 = Pipeline(name="x1118",parent=x1119) { implicit CU => 
      val x1101_x1115 =  VectorFIFO(size = 1).wtPort(x1101_x1109_data_v)
      val ctr12 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1113 = CounterChain(name = "x1113", ctr12)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1101_x1115.load), op=Bypass, results=List(CU.vecOut(stage(1), x1076_x1117_v)))
    }
    val x1135 = Pipeline(name="x1135",parent=x1142) { implicit CU => 
      val x1131 = CU.temp
      val ar77 = CU.accum(init = Const(0))
      val x1076_x1127 =  VectorFIFO(size = 1).wtPort(x1076_x1127_x1135_v)
      val x1075_x1126 =  VectorFIFO(size = 1).wtPort(x1075_x1126_x1135_v)
      val ctr13 = Counter(min=Const(0), max=Const(320), step=Const(1), par=1) // Counter
      val x1124 = CounterChain(name = "x1124", ctr13)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(2)
      Stage(stage(1), operands=List(x1075_x1126.load, x1076_x1127.load), op=FixMul, results=List(CU.temp(stage(1), x1131), CU.reduce(stage(1))))
      val (rs1, rr128) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(stage(2), operands=List(rr128), op=Bypass, results=List(CU.scalarOut(stage(2), x1122_x1133_s)))
    }
    val x1140 = Pipeline(name="x1140",parent=x1142) { implicit CU => 
      val ar6 = CU.accum(init = Const(0))
      val x1122_x1137 =  ScalarBuffer().wtPort(x1122_x1133_s)
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1140_unit = CounterChain(name = "x1140_unit", ctr14)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(2)
      Stage(stage(1), operands=List(x1122_x1137.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr131) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(stage(2), operands=List(rr131), op=Bypass, results=List(CU.scalarOut(stage(2), x1071_x1139_s)))
    }
    val x1145 = Pipeline(name="x1145",parent=x1146) { implicit CU => 
      val x1071_x1143 =  ScalarBuffer().wtPort(x1071_x1139_s)
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1145_unit = CounterChain(name = "x1145_unit", ctr15)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1071_x1143.load), op=Bypass, results=List(CU.scalarOut(stage(1), x1068_x1144_argout)))
    }
    
  }
}
