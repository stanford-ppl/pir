import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object ParSRAMReadWrite_cb extends PIRApp {
  arch = new SN(2,2,pattern=Checkerboard)
  def main(top:Top) = {
    val x1034_x1079_x1087_v = Vector("x1034_x1079_x1087")
    val x1055_x1064_data_v = Vector("x1055_x1064_data")
    val x1026_x1096_argout = ArgOut("x1026_x1096")
    val x1098 = Sequential(name="x1098",parent=top) { implicit CU => 
      val x1098_unit = CounterChain(name = "x1098_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1052 = Pipeline(name="x1052",parent=x1098) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1047 = CounterChain(name = "x1047", ctr2).iter(2)
      Stage(operands=List(Const(0), CU.ctr(ctr2)), op=FixAdd, results=List(CU.vecOut(x1055_x1064_data_v)))
    }
    val x1034_dsp0 = MemoryPipeline(name="x1034_dsp0",parent=x1098) { implicit CU => 
      val x1070_x1070 =  VectorFIFO(size=1).wtPort(x1055_x1064_data_v)
      val x1047 = CounterChain.copy("x1052", "x1047")
      val x1076 = CounterChain.copy("x1087_0", "x1076")
      val x1034_x1079 =  SRAM(size=32,banking = Strided(1)).wtPort(x1070_x1070.readPort).rdPort(x1034_x1079_x1087_v).rdAddr(x1076(0)).wtAddr(x1047(0))
    }
    val x1087_0 = Pipeline(name="x1087_0",parent=x1098) { implicit CU => 
      val x1079_x1079 =  VectorFIFO(size=1).wtPort(x1034_x1079_x1087_v)
      val ctr4 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1076 = CounterChain(name = "x1076", ctr4).iter(2)
      Stage(operands=List(CU.load(x1079_x1079), CU.load(x1079_x1079)), op=FixMul, results=List(CU.reduce))
      val (_, rr91) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr91), op=Bypass, results=List(CU.scalarOut(x1026_x1096_argout)))
    }
    
  }
}
