import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.spade.config._
import pir.util.enums._
import pir.util._
import pir.PIRApp

object ParSRAMReadWrite2D_cb extends PIRApp {
  arch = new SN(2,2,pattern=Checkerboard)
  def main(top:Top) = {
    val M = ArgIn("M")
    val N = ArgIn("N")
    val x1034_x1079_x1087_v = Vector("x1034_x1079_x1087")
    val x1055_x1064_data_v = Vector("x1055_x1064_data")
    val x1026_x1096_argout = ArgOut("x1026_x1096")
    val x1098 = MetaPipeline(name="x1098",parent=top) { implicit CU => 
      val x1098_unit = CounterChain(name = "x1098_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1052 = Pipeline(name="x1052",parent=x1098) { implicit CU => 
      val Mbuf =  ScalarBuffer().wtPort(M)
      val Nbuf =  ScalarBuffer().wtPort(N)
      val ctr1 = Counter(min=Const(0), max=Mbuf.readPort, step=Const(1), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=Nbuf.readPort, step=Const(1), par=16) // Counter
      val x1047 = CounterChain(name = "x1047", ctr1, ctr2).iter(2)
      val temp1 = CU.temp()
      Stage(operands=List(CU.ctr(x1047(0)), Nbuf.readPort), op=FixMul, results=List(temp1))
      Stage(operands=List(temp1, CU.ctr(x1047(1))), op=FixAdd, results=List(CU.vecOut(x1055_x1064_data_v)))
    }
    val x1034_dsp0 = MemoryPipeline(name="x1034_dsp0",parent=x1098) { implicit CU => 
      val Mbuf =  ScalarBuffer().wtPort(M)
      val Nbuf =  ScalarBuffer().wtPort(N)
      val x1070_x1070 =  VectorFIFO(size=1).wtPort(x1055_x1064_data_v)
      val x1047 = CounterChain.copy("x1052", "x1047")
      val x1076 = CounterChain.copy("x1087_0", "x1076")
      val x1034_x1079 =  SRAM(size=64,banking = Strided(1)).wtPort(x1070_x1070.readPort).rdPort(x1034_x1079_x1087_v)
      val temp1 = CU.temp()
      val temp2 = CU.temp()
      WAStage(operands=List(CU.ctr(x1047(0)), Nbuf.readPort), op=FixMul, results=List(temp1))
      WAStage(operands=List(temp1, CU.ctr(x1047(1))), op=FixAdd, results=List(x1034_x1079.writeAddr))
      RAStage(operands=List(CU.ctr(x1076(0)), Nbuf.readPort), op=FixMul, results=List(temp2))
      RAStage(operands=List(temp2, CU.ctr(x1076(1))), op=FixAdd, results=List(x1034_x1079.readAddr))
    }
    val x1087_0 = Pipeline(name="x1087_0",parent=x1098) { implicit CU => 
      val x1079_x1079 =  VectorFIFO(size=1).wtPort(x1034_x1079_x1087_v)
      val Mbuf =  ScalarBuffer().wtPort(M)
      val Nbuf =  ScalarBuffer().wtPort(N)
      val ctr3 = Counter(min=Const(0), max=Mbuf.readPort, step=Const(1), par=1) // Counter
      val ctr4 = Counter(min=Const(0), max=Nbuf.readPort, step=Const(1), par=16) // Counter
      val x1076 = CounterChain(name = "x1076", ctr3, ctr4)
      Stage(operands=List(CU.load(x1079_x1079), CU.load(x1079_x1079)), op=FixMul, results=List(CU.reduce))
      val (_, rr91) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr91), op=Bypass, results=List(CU.scalarOut(x1026_x1096_argout)))
    }
    
  }
}
