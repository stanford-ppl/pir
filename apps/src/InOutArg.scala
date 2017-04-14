import pir.graph
import pir.graph._
import pir.util.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util._
import pir.PIRApp

object InOutArg extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x187_x191_argout = ArgOut("x187_x191")
    val x186_argin = ArgIn("x186")
    val x193 = Sequential(name="x193",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x193_unit = CounterChain(name = "x193_unit", ctr1)
    }
    val x192 = Pipeline(name="x192",parent=x193) { implicit CU => 
      val x190 = CU.temp
      val x186_x189 =  ScalarBuffer().wtPort(x186_argin)
      val ctr2 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x192_unit = CounterChain(name = "x192_unit", ctr2)
      var stage: List[Stage] = Nil
      stage = CU.emptyStage +: Stages(1)
      Stage(stage(1), operands=List(x186_x189.load, Const(4)), op=FixAdd, results=List(CU.scalarOut(stage(1), x187_x191_argout), CU.temp(stage(1), x190)))
    }
    
  }
}
