import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object InOutArgDesign extends PIRApp {
  override val arch = SN_4x4
  def main(args: String*)(top:Top) = {
    val x142_argin = ArgIn("x142")
    val x143_argout = ArgOut("x143")
    val x151 = Sequential(name = "x151", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x151_unitcc = CounterChain(name = "x151_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x149_0 = UnitPipeline(name = "x149_0", parent=x151, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x149_unitcc = CounterChain(name = "x149_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x142_argin), Const("4i")), op=FixAdd, results=List(CU.scalarOut(stage(1), x143_argout)))
    }
    
  }
}
