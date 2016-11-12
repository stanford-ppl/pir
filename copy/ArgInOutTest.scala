import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object ArgInOutTestDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x61_argin = ArgIn()
    val x62_argout = ArgOut()
    val x70 = ComputeUnit(name="x70", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x70_unitCC = CounterChain(name = "x70_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x68 = UnitComputeUnit(name ="x68", parent=x70, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x68_unitCC = CounterChain(name = "x68_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x61_argin), Const("4i")), op=FixAdd, results=List(CU.scalarOut(stage(1), x62_argout)))
    }
    
  }
}
