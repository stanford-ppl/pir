import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleSequentialDesign extends PIRApp {
  override val arch = SN_4x4 
  def main(args: String*)(top:Top) = {
    val x369_vector = Vector()
    val x364_argin = ArgIn()
    val x365_argin = ArgIn()
    val x366_argout = ArgOut()
    val x392 = Sequential(name="x392", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x392_unitCC = CounterChain(name = "x392_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x385 = Pipeline(name="x385", parent=x392, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x370 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x371 = CounterChain(name = "x371", x370)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x364_argin), CU.ctr(stage(0), x371(0))), op=FixMul, results=List(CU.vecOut(stage(1), x369_vector)))
    }
    val x390 = UnitPipeline(name ="x390", parent=x392, deps=List(x385)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x390_unitCC = CounterChain(name = "x390_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      val x371 = CounterChain.copy(x385, "x371")
      val x369_x387 = SRAM(size = 96, writeCtr = x371(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x369_vector).wtAddr(x371(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x365_argin)), op=Bypass, results=List(x369_x387.readAddr))
      Stage(stage(2), operands=List(x369_x387.load), op=Bypass, results=List(CU.scalarOut(stage(2), x366_argout)))
    }
    
  }
}
