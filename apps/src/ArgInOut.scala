import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object ArgInOutDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x99_argin = ArgIn("x99")
    val x100_argout = ArgOut("x100")
    val x108 = Sequential(name = "x108", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x108_unitcc = CounterChain(name = "x108_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x106_0 = UnitPipeline(name = "x106_0", parent=x108, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x106_unitcc = CounterChain(name = "x106_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x99_argin), Const("4i")), op=FixAdd, results=List(CU.scalarOut(stage(1), x100_argout)))
    }
    
  }
}
