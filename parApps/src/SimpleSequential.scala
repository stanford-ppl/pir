import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleSequentialDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x529_vector = Vector("x529")
    val x524_argin = ArgIn("x524")
    val x525_argin = ArgIn("x525")
    val x526_argout = ArgOut("x526")
    val x548 = Sequential(name = "x548", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x548_unitcc = CounterChain(name = "x548_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x540_0 = Pipeline(name = "x540_0", parent=x548, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x531 = CounterChain(name = "x531", ctr1)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x524_argin), CU.ctr(stage(0), x531(0))), op=FixMul, results=List(CU.vecOut(stage(1), x529_vector)))
    }
    val x546_0 = UnitPipeline(name = "x546_0", parent=x548, deps=List(x540_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x546_unitcc = CounterChain(name = "x546_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x531 = CounterChain.copy(x540_0, "x531")
      val x529_x543 = SRAM(size = 96, writeCtr = x531(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x529_vector).wtAddr(x531(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x525_argin)), op=Bypass, results=List(x529_x543.readAddr))
      Stage(stage(2), operands=List(x529_x543.load), op=Bypass, results=List(CU.scalarOut(stage(2), x526_argout)))
    }
    
  }
}
