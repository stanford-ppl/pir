import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object SimpleSequentialTestDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x290_argout = ArgOut()
    val x288_argin = ArgIn()
    val x289_argin = ArgIn()
    val x293_vector = Vector()
    val x316 = ComputeUnit(name="x316", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x316_unitCC = CounterChain(name = "x316_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x309 = ComputeUnit(name="x309", parent=x316, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x294 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x295 = CounterChain(name = "x295", x294)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x288_argin), CU.ctr(stage(0), x295(0))), op=FixMul, results=List(CU.vecOut(stage(1), x293_vector)))
    }
    val x314 = UnitComputeUnit(name ="x314", parent=x316, deps=List(x309)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x314_unitCC = CounterChain(name = "x314_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      val x295 = CounterChain.copy(x309, "x295")
      val x293_x311 = SRAM(size = 96, vec = x293_vector, swapCtr = x295(0), writeCtr = x295(0), banking = Strided(1), doubleBuffer = false).wtAddr(x295(0)) 
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x289_argin)), op=Bypass, results=List(x293_x311.readAddr))
      Stage(stage(2), operands=List(x293_x311.load), op=Bypass, results=List(CU.scalarOut(stage(2), x290_argout)))
    }
    
  }
}
