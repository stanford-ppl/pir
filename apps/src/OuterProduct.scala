import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1042_argin = ArgIn("x1042")
    val x1019_argin = ArgIn("x1019")
    val x1036_x1056_v = Vector("x1036_x1056")
    val x1025_oc = OffChip("x1025")
    val x1037_x1077_v = Vector("x1037_x1077")
    val x1038_x1091_v = Vector("x1038_x1091")
    val x1062_x1069_data_v = Vector("x1062_x1069_data")
    val x1037_x1086_x1092_v = Vector("x1037_x1086_x1092")
    val x1060_b1146_x1066_b1148_s = Scalar("x1060_b1146_x1066_b1148")
    val x1036_x1085_x1092_v = Vector("x1036_x1085_x1092")
    val x1041_x1048_data_v = Vector("x1041_x1048_data")
    val x1028_oc = OffChip("x1028")
    val x1018_argin = ArgIn("x1018")
    val x1038_x1111_x1116_v = Vector("x1038_x1111_x1116")
    val x1039_b1142_x1045_b1144_s = Scalar("x1039_b1142_x1045_b1144")
    val x1039_b1141_x1045_b1143_s = Scalar("x1039_b1141_x1045_b1143")
    val x1095_b1152_x1106_b1154_s = Scalar("x1095_b1152_x1106_b1154")
    val x1061_x1067_s = Scalar("x1061_x1067")
    val x1095_b1151_x1106_b1153_s = Scalar("x1095_b1151_x1106_b1153")
    val x1060_b1145_x1066_b1147_s = Scalar("x1060_b1145_x1066_b1147")
    val x1098_x1118_ack_v = Vector("x1098_x1118_ack")
    val x1096_x1107_s = Scalar("x1096_x1107")
    val x1040_x1046_s = Scalar("x1040_x1046")
    val x1097_x1115_s = Scalar("x1097_x1115")
    val x1063_argin = ArgIn("x1063")
    val x1099_argin = ArgIn("x1099")
    val x1023_oc = OffChip("x1023")
    val x1124 = Sequential(name="x1124",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1124_unit = CounterChain(name = "x1124_unit", ctr1)
    }
    val x1123 = MetaPipeline(name="x1123",parent=x1124) { implicit CU => 
      val x1019_x1031 =  ScalarBuffer().wtPort(x1019_argin)
      val x1018_x1033 =  ScalarBuffer().wtPort(x1018_argin)
      val ctr2 = Counter(min=Const(0), max=x1018_x1033.load, step=Const(64), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x1019_x1031.load, step=Const(64), par=1) // Counter
      val x1035 = CounterChain(name = "x1035", ctr2, ctr3)
    }
    val x1036_dsp0 = MemoryPipeline(name="x1036_dsp0",parent="x1123") { implicit CU => 
      val x1052 = CounterChain.copy("x1057", "x1052")
      val x1084 = CounterChain.copy("x1092", "x1084")
      val x1036_x1085 =  SRAM(size = 64,banking = NoBanking()).wtPort(x1036_x1056_v).rdPort(x1036_x1085_x1092_v).rdAddr(x1084(0)).wtAddr(x1052(0))
      var stage: List[Stage] = Nil
    }
    val x1037_dsp0 = MemoryPipeline(name="x1037_dsp0",parent="x1123") { implicit CU => 
      val x1073 = CounterChain.copy("x1078", "x1073")
      val x1084 = CounterChain.copy("x1092", "x1084")
      val x1037_x1086 =  SRAM(size = 64,banking = NoBanking()).wtPort(x1037_x1077_v).rdPort(x1037_x1086_x1092_v).rdAddr(x1084(1)).wtAddr(x1073(0))
      var stage: List[Stage] = Nil
    }
    val x1038_dsp0 = MemoryPipeline(name="x1038_dsp0",parent="x1123") { implicit CU => 
      val b1156 = CU.temp
      val b1150 = CU.temp
      val b1149 = CU.temp
      val b1155 = CU.temp
      val x1084 = CounterChain.copy("x1092", "x1084")
      val x1094 = CounterChain.copy("x1122", "x1094")
      val x1110 = CounterChain.copy("x1116", "x1110")
      val x1038_x1111 =  SRAM(size = 4096,banking = NoBanking()).wtPort(x1038_x1091_v).rdPort(x1038_x1111_x1116_v)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: WAStages(2, List(x1038_x1111))
      Stage(stage(1), operands=List(x1084(0), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1149)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1149), CU.ctr(stage(1), x1084(1))), op=FixAdd, results=List(x1038_x1111.writeAddr, CU.temp(stage(2), b1150)))
      stage = CU.emptyStage +: RAStages(2, List(x1038_x1111))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1094(0)), Const(64)), op=FixMul, results=List(CU.temp(stage(1), b1155)))
      Stage(stage(2), operands=List(CU.temp(stage(1), b1155), CU.ctr(stage(1), x1110(0))), op=FixAdd, results=List(x1038_x1111.readAddr, CU.temp(stage(2), b1156)))
    }
    val x1059 = StreamController(name="x1059",parent=x1123) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1059_unit = CounterChain(name = "x1059_unit", ctr4)
    }
    val x1047 = Pipeline(name="x1047",parent=x1059) { implicit CU => 
      val x646 = CU.temp
      val x1043 = CU.temp
      val x1042 =  ScalarBuffer().wtPort(x1042_argin)
      val x1035 = CounterChain.copy("x1123", "x1035")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1047_unit = CounterChain(name = "x1047_unit", ctr5)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1035(0)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x646)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x646), CU.load(stage(1), x1042)), op=FixAdd, results=List(CU.scalarOut(stage(2), x1039_b1141_x1045_b1143_s), CU.temp(stage(2), x1043)))
      Stage(stage(3), operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(stage(3), x1039_b1142_x1045_b1144_s)))
      Stage(stage(4), operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(stage(4), x1040_x1046_s)))
    }
    val x1048 = MemoryController(name="x1048",parent=x1059,offchip=x1023_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1039_b1142_x1045_b1144_s)
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1039_b1141_x1045_b1143_s)
      CU.mcvecs += "data" -> x1041_x1048_data_v
    }
    val x1058 = MetaPipeline(name="x1058",parent=x1059) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1058_unit = CounterChain(name = "x1058_unit", ctr6)
    }
    val x1050 = Pipeline(name="x1050",parent=x1058) { implicit CU => 
      val x1040_x1049 =  ScalarFIFO(size = 16).wtPort(x1040_x1046_s)
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1050_unit = CounterChain(name = "x1050_unit", ctr7)
      var stage: List[Stage] = Nil
    }
    val x1057 = Pipeline(name="x1057",parent=x1058) { implicit CU => 
      val x1041_x1053 =  VectorFIFO(size = 1).wtPort(x1041_x1048_data_v)
      val ctr8 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1052 = CounterChain(name = "x1052", ctr8)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1041_x1053.load), op=Bypass, results=List(CU.vecOut(stage(1), x1036_x1056_v)))
    }
    val x1080 = StreamController(name="x1080",parent=x1123) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1080_unit = CounterChain(name = "x1080_unit", ctr9)
    }
    val x1068 = Pipeline(name="x1068",parent=x1080) { implicit CU => 
      val x1064 = CU.temp
      val x673 = CU.temp
      val x1063 =  ScalarBuffer().wtPort(x1063_argin)
      val x1035 = CounterChain.copy("x1123", "x1035")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1068_unit = CounterChain(name = "x1068_unit", ctr10)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1035(1)), Const(4)), op=FixMul, results=List(CU.temp(stage(1), x673)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x673), CU.load(stage(1), x1063)), op=FixAdd, results=List(CU.scalarOut(stage(2), x1060_b1145_x1066_b1147_s), CU.temp(stage(2), x1064)))
      Stage(stage(3), operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(stage(3), x1060_b1146_x1066_b1148_s)))
      Stage(stage(4), operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(stage(4), x1061_x1067_s)))
    }
    val x1069 = MemoryController(name="x1069",parent=x1080,offchip=x1025_oc, mctpe=TileLoad) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1060_b1145_x1066_b1147_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1060_b1146_x1066_b1148_s)
      CU.mcvecs += "data" -> x1062_x1069_data_v
    }
    val x1079 = MetaPipeline(name="x1079",parent=x1080) { implicit CU => 
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1079_unit = CounterChain(name = "x1079_unit", ctr11)
    }
    val x1071 = Pipeline(name="x1071",parent=x1079) { implicit CU => 
      val x1061_x1070 =  ScalarFIFO(size = 16).wtPort(x1061_x1067_s)
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1071_unit = CounterChain(name = "x1071_unit", ctr12)
      var stage: List[Stage] = Nil
    }
    val x1078 = Pipeline(name="x1078",parent=x1079) { implicit CU => 
      val x1062_x1074 =  VectorFIFO(size = 1).wtPort(x1062_x1069_data_v)
      val ctr13 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1073 = CounterChain(name = "x1073", ctr13)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x1062_x1074.load), op=Bypass, results=List(CU.vecOut(stage(1), x1037_x1077_v)))
    }
    val x1092 = Pipeline(name="x1092",parent=x1123) { implicit CU => 
      val x1089 = CU.temp
      val ctr14 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr15 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1084 = CounterChain(name = "x1084", ctr14, ctr15)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1036_x1085_x1092_v), CU.vecIn(stage(0), x1037_x1086_x1092_v)), op=FixMul, results=List(CU.vecOut(stage(1), x1038_x1091_v), CU.temp(stage(1), x1089)))
    }
    val x1122 = StreamController(name="x1122",parent=x1123) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1094 = CounterChain(name = "x1094", ctr16)
    }
    val x1117 = MetaPipeline(name="x1117",parent=x1122) { implicit CU => 
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1117_unit = CounterChain(name = "x1117_unit", ctr17)
    }
    val x1108 = Pipeline(name="x1108",parent=x1117) { implicit CU => 
      val x1101 = CU.temp
      val x1102 = CU.temp
      val x1103 = CU.temp
      val x726 = CU.temp
      val x1104 = CU.temp
      val x1019_x1100 =  ScalarBuffer().wtPort(x1019_argin)
      val x1099 =  ScalarBuffer().wtPort(x1099_argin)
      val x1035 = CounterChain.copy("x1123", "x1035")
      val x1094 = CounterChain.copy("x1122", "x1094")
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1108_unit = CounterChain(name = "x1108_unit", ctr18)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1035(0)), CU.ctr(stage(0), x1094(0))), op=FixAdd, results=List(CU.temp(stage(1), x726)))
      Stage(stage(2), operands=List(CU.temp(stage(1), x726), CU.load(stage(1), x1019_x1100)), op=FixMul, results=List(CU.temp(stage(2), x1101)))
      Stage(stage(3), operands=List(CU.temp(stage(2), x1101), CU.ctr(stage(2), x1035(1))), op=FixAdd, results=List(CU.temp(stage(3), x1102)))
      Stage(stage(4), operands=List(CU.temp(stage(3), x1102), Const(4)), op=FixMul, results=List(CU.temp(stage(4), x1103)))
      Stage(stage(5), operands=List(CU.temp(stage(4), x1103), CU.load(stage(4), x1099)), op=FixAdd, results=List(CU.scalarOut(stage(5), x1095_b1151_x1106_b1153_s), CU.temp(stage(5), x1104)))
      Stage(stage(6), operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(stage(6), x1095_b1152_x1106_b1154_s)))
      Stage(stage(7), operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(stage(7), x1096_x1107_s)))
    }
    val x1116 = Pipeline(name="x1116",parent=x1117) { implicit CU => 
      val x1094 = CounterChain.copy("x1122", "x1094")
      val ctr19 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x1110 = CounterChain(name = "x1110", ctr19)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1038_x1111_x1116_v)), op=Bypass, results=List(CU.scalarOut(stage(1), x1097_x1115_s)))
    }
    val x1118 = MemoryController(name="x1118",parent=x1122,offchip=x1028_oc, mctpe=TileStore) { implicit CU => 
      CU.mcfifos += "offset" ->  ScalarFIFO(size = 1).wtPort(x1095_b1151_x1106_b1153_s)
      CU.mcfifos += "data" ->  ScalarFIFO(size = 1).wtPort(x1097_x1115_s)
      CU.mcfifos += "size" ->  ScalarFIFO(size = 1).wtPort(x1095_b1152_x1106_b1154_s)
      CU.mcvecs += "ack" -> x1098_x1118_ack_v
    }
    val x1121 = Pipeline(name="x1121",parent=x1122) { implicit CU => 
      val x1096_x1119 =  ScalarFIFO(size = 16).wtPort(x1096_x1107_s)
      val x1098_x1120 =  VectorFIFO(size = 1).wtPort(x1098_x1118_ack_v)
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1121_unit = CounterChain(name = "x1121_unit", ctr20)
      var stage: List[Stage] = Nil
    }
    
  }
}
