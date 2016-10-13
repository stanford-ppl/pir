import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleReduceDesign extends PIRApp {
  override val arch = SN_4x4 
  def main(args: String*)(top:Top) = {
    val x382_argin = ArgIn()
    val x383_argout = ArgOut()
    val x415 = Sequential(name="x415", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x415_unitCC = CounterChain(name = "x415_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x408 = Pipeline(name="x408", parent=x415, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x385 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x386 = CounterChain(name = "x386", x385)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x382_argin), CU.ctr(stage(0), x386(0))), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr9) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr9), op=Bypass, results=List(CU.scalarOut(stage(2), x383_argout)))
    }
    
  }
}
