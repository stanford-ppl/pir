package apps
import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object InOutArgDesign extends PIRApp {
  override val arch = P2P_4CU_4TT
  def main(args: String*)(top:Top) = {
    val x103_argin = ArgIn()
    val x104_argout = ArgOut()
    val x112 = Sequential(name="x112", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x112_unitCC = CounterChain(name = "x112_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x110 = UnitPipeline(name ="x110", parent=x112, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x110_unitCC = CounterChain(name = "x110_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x103_argin), Const("4i")), op=FixAdd, results=List(CU.scalarOut(stage(1), x104_argout)))
    }
    
  }
}
