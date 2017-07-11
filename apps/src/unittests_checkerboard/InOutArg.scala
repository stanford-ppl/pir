import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object InOutArg_cb extends PIRApp {
  override val arch = SN(1,1,pattern=Checkerboard)
  def main(top:Top) = {
    val x223_x227_argout = ArgOut("x223_x227")
    val x222_argin = ArgIn("x222").bound(4)
    val x229 = Sequential(name="x229",parent=top) { implicit CU => 
      val x229_unit = CounterChain(name = "x229_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x228_0 = Pipeline(name="x228_0",parent=x229) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(x222_argin)
      val x228_unit = CounterChain(name = "x228_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x222_x225), Const(4)), op=FixAdd, results=List(CU.scalarOut(x223_x227_argout)))
    }
    
  }
}
