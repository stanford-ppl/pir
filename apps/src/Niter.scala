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
    val x419_argin = ArgIn()
    val x420_argout = ArgOut()
    val x476 = Sequential(name="x476", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x476_unitCC = CounterChain(name = "x476_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x474 = Sequential(name="x474", parent=x476, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x474_unitCC = CounterChain(name = "x474_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x471 = Sequential(name="x471", parent=x474, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x423 = (Const("0i").out, CU.scalarIn(stage0, x419_argin).out, Const("96i").out) // Counter
      val x424 = CounterChain(name = "x424", x423)
    }
    val x465 = Pipeline(name="x465", parent=x471, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x424 = CounterChain.copy(x471, "x424")
      val x452 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x453 = CounterChain(name = "x453", x452)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x424(0)), CU.ctr(stage(0), x453(0))), op=FixAdd, results=List(CU.reduce(stage(1))))
      val (rs1, rr15) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr15), op=Bypass, results=List(CU.scalarOut(stage(2), x420_argout)))
    }
    
  }
}
