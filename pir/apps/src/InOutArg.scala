import pir._
import pir.node._
import arch._
import pirc.enums._

object InOutArg extends PIRApp {
  def main(top:Top) = {
    val x_argin = ArgIn("x")
    val x342_x348_argout = ArgOut("x342_x348")
    val x349_0 = Pipeline(name="x349_0",parent="top") { implicit CU => 
      val x341 = ScalarBuffer(name="x341").buffering(1).wtPort(x_argin)
      val x349_unit = CounterChain(name = "x349_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x341), Const(4)), op=FixAdd, results=List(CU.scalarOut(x342_x348_argout)))
    }
    
  }
}
