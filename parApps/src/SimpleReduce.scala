import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleReduceDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x467_argin = ArgIn("x467")
    val x468_argout = ArgOut("x468")
    val x485 = Sequential(name = "x485", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x485_unitcc = CounterChain(name = "x485_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x479_0 = Pipeline(name = "x479_0", parent=x485, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x472 = CounterChain(name = "x472", ctr1)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x467_argin), CU.ctr(stage(0), x472(0))), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr10) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr10), op=Bypass, results=List(CU.scalarOut(stage(2), x468_argout)))
    }
    
  }
}
