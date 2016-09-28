import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleReduceDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x384_argout = ArgOut()
    val x383_argin = ArgIn()
    val x416 = Sequential(name="x416", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x416_unitCC = CounterChain(name = "x416_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x409 = Pipeline(name="x409", parent=x416, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x386 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x387 = CounterChain(name = "x387", x386)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x383_argin), CU.ctr(stage(0), x387(0))), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr9) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr9), op=Bypass, results=List(CU.scalarOut(stage(2), x384_argout)))
    }
    
  }
}
