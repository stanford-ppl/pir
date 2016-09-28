import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object ArgInOutDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x84_argout = ArgOut()
    val x83_argin = ArgIn()
    val x92 = Sequential(name="x92", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x92_unitCC = CounterChain(name = "x92_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x90 = UnitPipeline(name ="x90", parent=x92, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x90_unitCC = CounterChain(name = "x90_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x83_argin), Const("4i")), op=FixAdd, results=List(CU.scalarOut(stage(1), x84_argout)))
    }
    
  }
}
