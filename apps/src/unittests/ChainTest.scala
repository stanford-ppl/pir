import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object ChainTest extends PIRApp {
  override val arch = SN(1,1)
  def main(top:Top) = {
    val x223_x227_argout = ArgOut("x223_x227")
    val x222_argin = ArgIn("ai_in").bound(4)
    val x221_argin = ArgIn("ai_out").bound(4)
    val x229 = Sequential(name="x229",parent=top) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(x221_argin)
      val ctr1 = Counter(Const(0), x222_x225.readPort, Const(1), par=1)
      val x229_unit = CounterChain(name = "x229_unit", ctr1)
    }
    val x228_0 = Pipeline(name="x228_0",parent=x229) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(x222_argin)
      val ctr1 = Counter(Const(0), x222_x225.readPort, Const(1), par=1)
      val ctr2 = Counter(Const(0), x222_x225.readPort, Const(1), par=1)
      val cchain = CounterChain(name = "x228_unit", ctr1, ctr2)
      val occhain = CounterChain.copy("x229", "x229_unit")
      val tmp = CU.temp
      Stage(operands=List(CU.ctr(cchain(0)), CU.ctr(cchain(1))), op=FixMul, results=List(tmp))
      Stage(operands=List(CU.ctr(occhain(0)), tmp), op=FixMul, results=List(CU.scalarOut(x223_x227_argout)))
    }
  }
}
