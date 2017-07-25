import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SimpleReduce_cb extends PIRApp {
  arch = new SN(2,2,pattern=Checkerboard)
  def main(top:Top) = {
    val x351_x365_argout = ArgOut("x351_x365")
    val x350_argin = ArgIn("x350").bound(10)
    val x367 = Sequential(name="x367",parent=top) { implicit CU => 
      val x367_unit = CounterChain(name = "x367_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x363_0 = Pipeline(name="x363_0",parent=x367) { implicit CU => 
      val x350_x356 =  ScalarBuffer().wtPort(x350_argin)
      val ctr1 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x355 = CounterChain(name = "x355", ctr1).iter(16)
      Stage(operands=List(CU.load(x350_x356), CU.ctr(x355(0))), op=FixMul, results=List(CU.reduce))
      val (_, rr18) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr18), op=Bypass, results=List(CU.scalarOut(x351_x365_argout)))
    }
    
  }
}
