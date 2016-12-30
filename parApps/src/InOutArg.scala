import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object InOutArgDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x123_argin = ArgIn("x123")
    val x124_argout = ArgOut("x124")
    val x132 = Sequential(name = "x132", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x132_unitcc = CounterChain(name = "x132_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x130_0 = UnitPipeline(name = "x130_0", parent=x132, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x130_unitcc = CounterChain(name = "x130_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x123_argin), Const("4i")), op=FixAdd, results=List(CU.scalarOut(stage(1), x124_argout)))
    }
    
  }
}
