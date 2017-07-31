import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SRAMReadWrite extends PIRApp {
  arch = SN2x3
  def main(top:Top) = {
    val x1034_x1079_x1087_s = Scalar("x1034_x1079_x1087")
    val x1055_x1064_data_s = Scalar("x1055_x1064_data")
    val x1055_x1064_ra_s = Scalar("x1055_x1064_ra")
    val x1055_x1064_wa_s = Scalar("x1055_x1064_wa")
    val x1026_x1096_argout = ArgOut("x1026_x1096")
    val x1098 = Sequential(name="x1098",parent=top) { implicit CU => 
      val x1098_unit = CounterChain(name = "x1098_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1052 = Pipeline(name="x1052",parent=x1098) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1) // Counter
      val x1047 = CounterChain(name = "x1047", ctr2).iter(2)
      Stage(operands=List(Const(4), CU.ctr(ctr2)), op=FixAdd, results=List(CU.scalarOut(x1055_x1064_data_s)))
    }
    val x1052_wa = Pipeline(name="x1052_wa",parent=x1098) { implicit CU => 
      val x1047 = CounterChain.copy("x1052", "x1047")
      Stage(operands=List(CU.ctr(x1047(0))), op=Bypass, results=List(CU.scalarOut(x1055_x1064_wa_s)))
    }
    val x1052_ra = Pipeline(name="x1052_ra",parent=x1098) { implicit CU => 
      val x1076 = CounterChain.copy("x1087_0", "x1076")
      Stage(operands=List(CU.ctr(x1076(0))), op=Bypass, results=List(CU.scalarOut(x1055_x1064_ra_s)))
    }
    val x1034_dsp0 = MemoryPipeline(name="x1034_dsp0",parent=x1098) { implicit CU => 
      val x1070_x1070 =  ScalarFIFO(size=1).wtPort(x1055_x1064_data_s)
      val wa =  ScalarFIFO(size=1).wtPort(x1055_x1064_wa_s)
      val ra =  ScalarFIFO(size=1).wtPort(x1055_x1064_ra_s)
      val x1034_x1079 =  SRAM(size=2,banking = NoBanking()).wtPort(x1070_x1070.readPort).rdPort(x1034_x1079_x1087_s).rdAddr(ra.readPort).wtAddr(wa.readPort)
    }
    val x1087_0 = Pipeline(name="x1087_0",parent=x1098) { implicit CU => 
      val x1079_x1079 =  ScalarFIFO(size=1).wtPort(x1034_x1079_x1087_s)
      val ctr4 = Counter(min=Const(0), max=Const(2), step=Const(1), par=1) // Counter
      val x1076 = CounterChain(name = "x1076", ctr4)
      Stage(operands=List(CU.load(x1079_x1079), CU.load(x1079_x1079)), op=FixMul, results=List(CU.reduce))
      val (_, rr91) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr91), op=Bypass, results=List(CU.scalarOut(x1026_x1096_argout)))
    }
    
  }
}
