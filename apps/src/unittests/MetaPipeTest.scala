import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.spade.config._
import pir.util.enums._
import pir.util._
import pir.PIRApp

object MetaPipeTest extends PIRApp {
  arch = SN1x2
  def main(top:Top) = {
    val scalar1 = Scalar()
    val x223_x227_argout = ArgOut("x223_x227")
    val x222_argin = ArgIn("x222").bound(4)
    val x229 = MetaPipeline(name="x229",parent=top) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(x222_argin)
      val x229_unit = CounterChain(name = "x229_unit", Counter(Const(0), x222_x225.readPort, Const(1), par=1))
    }
    val x228_0 = Pipeline(name="x228_0",parent=x229) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(x222_argin)
      val x228_unit = CounterChain(name = "x228_unit", Counter(Const(0), Const(1), Const(1), par=1))
      val x229_unit = CounterChain.copy("x229", "x229_unit")
      Stage(operands=List(CU.ctr(x229_unit(0))), op=Bypass, results=List(CU.scalarOut(scalar1)))
    }
    val x228_1 = Pipeline(name="x228_1",parent=x229) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(scalar1)
      val x228_unit = CounterChain(name = "x228_unit", Counter(Const(0), Const(1), Const(1), par=1))
      Stage(operands=List(CU.load(x222_x225)), op=Bypass, results=List(CU.scalarOut(x223_x227_argout)))
    }
    
  }
}
