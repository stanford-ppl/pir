import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object NiterDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x514_argin = ArgIn("x514")
    val x515_argout = ArgOut("x515")
    val x537 = Sequential(name = "x537", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x537_unitcc = CounterChain(name = "x537_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x535 = Sequential(name = "x535", parent=x537, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x535_unitcc = CounterChain(name = "x535_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x533 = Sequential(name = "x533", parent=x535, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x514_argin).out, Const("96i").out) // Counter
      val x519 = CounterChain(name = "x519", ctr1)
      var stage: List[Stage] = Nil
    }
    val x527_0 = Pipeline(name = "x527_0", parent=x533, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x519 = CounterChain.copy(x533, "x519")
      val ctr3 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x522 = CounterChain(name = "x522", ctr3)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x519(0)), CU.ctr(stage(0), x522(0))), op=FixAdd, results=List(CU.reduce(stage(1))))
      val (rs1, rr19) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr19), op=Bypass, results=List(CU.scalarOut(stage(2), x515_argout)))
    }
    
  }
}
