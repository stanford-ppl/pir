import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object InOutArg extends PIRApp {
  override val arch = SN1x1
  def main(args: String*)(top:Top) = {
    val x223_x227_argout = ArgOut("x223_x227")
    val x222_argin = ArgIn("x222").bound(3)
    val x229 = Sequential(name="x229",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x229_unit = CounterChain(name = "x229_unit", ctr1)
    }
    val x228 = Pipeline(name="x228",parent=x229) { implicit CU => 
      val x222_x225 =  ScalarBuffer().wtPort(x222_argin)
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x228_unit = CounterChain(name = "x228_unit", ctr2)
      var stage: List[Stage] = Nil
      val temp = CU.temp
      Stage(operands=List(CU.load(x222_x225), ctr2.out), op=FixAdd, results=List(temp))
      Stage(operands=List(CU.load(x222_x225), temp), op=FixAdd, results=List(CU.scalarOut(x223_x227_argout)))
    }
    
  }
}
