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
    val x1034_x1079_x1087_v = Vector("x1034_x1079_x1087")
    val x1035_b1117_x1043_b1119_s = Scalar("x1035_b1117_x1043_b1119")
    val x1054_b1122_x1062_b1124_s = Scalar("x1054_b1122_x1062_b1124")
    val x1035_b1118_x1043_b1120_s = Scalar("x1035_b1118_x1043_b1120")
    val x1025_oc = OffChip("x1025")
    val x1019_argin = ArgIn("x1019").bound(768000)
    val x1037_argin = ArgIn("x1037")
    val x1074_x1085_s = Scalar("x1074_x1085")
    val x1036_x1045_data_v = Vector("x1036_x1045_data")
    val x1056_argin = ArgIn("x1056")
    val x1033_x1078_x1087_v = Vector("x1033_x1078_x1087")
    val x1054_b1121_x1062_b1123_s = Scalar("x1054_b1121_x1062_b1123")
    val x1023_oc = OffChip("x1023")
    val x1055_x1064_data_v = Vector("x1055_x1064_data")
    val x1026_x1096_argout = ArgOut("x1026_x1096")
    val x1098 = Sequential(name="x1098",parent=top) { implicit CU => 
      val x1098_unit = CounterChain(name = "x1098_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1094 = MetaPipeline(name="x1094",parent=x1098) { implicit CU => 
      val x1019_x1030 =  ScalarBuffer().wtPort(x1019_argin)
      val ctr1 = Counter(min=Const(0), max=x1019_x1030.load, step=Const(32), par=1) // Counter
      val x1032 = CounterChain(name = "x1032", ctr1).iter(24000)
    }
    val x1033_dsp0 = MemoryPipeline(name="x1033_dsp0",parent="x1094") { implicit CU => 
      val x1051_x1051 =  VectorFIFO(size=1).wtPort(x1036_x1045_data_v)
      val x1047 = CounterChain.copy("x1052", "x1047")
      val x1076 = CounterChain.copy("x1087_0", "x1076")
      val x1033_x1078 =  SRAM(size=32,banking = Strided(1)).wtPort(x1051_x1051.readPort).rdPort(x1033_x1078_x1087_v).rdAddr(x1076(0)).wtAddr(x1047(0))
    }
    val x1034_dsp0 = MemoryPipeline(name="x1034_dsp0",parent="x1094") { implicit CU => 
      val x1070_x1070 =  VectorFIFO(size=1).wtPort(x1055_x1064_data_v)
      val x1066 = CounterChain.copy("x1071", "x1066")
      val x1076 = CounterChain.copy("x1087_0", "x1076")
      val x1034_x1079 =  SRAM(size=32,banking = Strided(1)).wtPort(x1070_x1070.readPort).rdPort(x1034_x1079_x1087_v).rdAddr(x1076(0)).wtAddr(x1066(0))
    }
    val x1053 = StreamController(name="x1053",parent=x1094) { implicit CU => 
      val x1053_unit = CounterChain(name = "x1053_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1044_0 = Pipeline(name="x1044_0",parent=x1053) { implicit CU => 
      val x1038 = CU.temp
      val x1037 =  ScalarBuffer().wtPort(x1037_argin)
      val x1032 = CounterChain.copy("x1094", "x1032")
      val x1044_unit = CounterChain(name = "x1044_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1032(0)), Const(4)), op=FixMul, results=List(x1038))
      Stage(operands=List(x1038, CU.load(x1037)), op=FixAdd, results=List(CU.scalarOut(x1035_b1117_x1043_b1119_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x1035_b1118_x1043_b1120_s)))
    }
    val x1045 = MemoryController(name="x1045",parent=x1053,offchip=x1023_oc, mctpe=TileLoad) { implicit CU => 
      val x1035_b1118_x1045 =  ScalarFIFO(name="size",size=1).wtPort(x1035_b1118_x1043_b1120_s)
      val x1035_b1117_x1045 =  ScalarFIFO(name="offset",size=1).wtPort(x1035_b1117_x1043_b1119_s)
      CU.newVout("data", x1036_x1045_data_v)
    }
    val x1052 = Pipeline(name="x1052",parent=x1053) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1047 = CounterChain(name = "x1047", ctr2).iter(2)
    }
    val x1072 = StreamController(name="x1072",parent=x1094) { implicit CU => 
      val x1072_unit = CounterChain(name = "x1072_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1063_0 = Pipeline(name="x1063_0",parent=x1072) { implicit CU => 
      val x1057 = CU.temp
      val x1056 =  ScalarBuffer().wtPort(x1056_argin)
      val x1032 = CounterChain.copy("x1094", "x1032")
      val x1063_unit = CounterChain(name = "x1063_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1032(0)), Const(4)), op=FixMul, results=List(x1057))
      Stage(operands=List(x1057, CU.load(x1056)), op=FixAdd, results=List(CU.scalarOut(x1054_b1121_x1062_b1123_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x1054_b1122_x1062_b1124_s)))
    }
    val x1064 = MemoryController(name="x1064",parent=x1072,offchip=x1025_oc, mctpe=TileLoad) { implicit CU => 
      val x1054_b1122_x1064 =  ScalarFIFO(name="size",size=1).wtPort(x1054_b1122_x1062_b1124_s)
      val x1054_b1121_x1064 =  ScalarFIFO(name="offset",size=1).wtPort(x1054_b1121_x1062_b1123_s)
      CU.newVout("data", x1055_x1064_data_v)
    }
    val x1071 = Pipeline(name="x1071",parent=x1072) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1066 = CounterChain(name = "x1066", ctr3).iter(2)
    }
    val x1087_0 = Pipeline(name="x1087_0",parent=x1094) { implicit CU => 
      val x1079_x1079 =  VectorFIFO(size=1).wtPort(x1034_x1079_x1087_v)
      val x1078_x1078 =  VectorFIFO(size=1).wtPort(x1033_x1078_x1087_v)
      val ctr4 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1076 = CounterChain(name = "x1076", ctr4).iter(2)
      Stage(operands=List(CU.load(x1078_x1078), CU.load(x1079_x1079)), op=FixMul, results=List(CU.reduce))
      val (_, rr91) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr91), op=Bypass, results=List(CU.scalarOut(x1074_x1085_s)))
    }
    val x1092_0 = Pipeline(name="x1092_0",parent=x1094) { implicit CU => 
      val x1074_x1089 =  ScalarBuffer().wtPort(x1074_x1085_s)
      val x1092_unit = CounterChain(name = "x1092_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1074_x1089)), op=Bypass, results=List(CU.reduce))
      val (_, rr94) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr94), op=Bypass, results=List(CU.scalarOut(x1026_x1096_argout)))
    }
    
  }
}
