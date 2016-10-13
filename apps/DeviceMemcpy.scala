import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DeviceMemcpyDesign extends PIRApp {
  override val arch = SN_4x4 
  def main(args: String*)(top:Top) = {
    val x634_argout = ArgOut()
    val x639 = Sequential(name="x639", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x639_unitCC = CounterChain(name = "x639_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x637 = UnitPipeline(name ="x637", parent=x639, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x637_unitCC = CounterChain(name = "x637_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("10i")), op=Bypass, results=List(CU.scalarOut(stage(1), x634_argout)))
    }
    
  }
}
