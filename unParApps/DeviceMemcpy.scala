import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DeviceMemcpyDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x747_argout = ArgOut("x747")
    val x752 = Sequential(name = "x752", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x752_unitcc = CounterChain(name = "x752_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x750_0 = UnitPipeline(name = "x750_0", parent=x752, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x750_unitcc = CounterChain(name = "x750_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("10i")), op=Bypass, results=List(CU.scalarOut(stage(1), x747_argout)))
    }
    
  }
}
