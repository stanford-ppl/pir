import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object NiterDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x420_argin = ArgIn()
    val x421_argout = ArgOut()
    val x477 = Sequential(name="x477", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x477_unitCC = CounterChain(name = "x477_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x475 = Sequential(name="x475", parent=x477, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x475_unitCC = CounterChain(name = "x475_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x472 = Sequential(name="x472", parent=x475, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x424 = (Const("0i").out, CU.scalarIn(stage0, x420_argin).out, Const("96i").out) // Counter
      val x425 = CounterChain(name = "x425", x424)
    }
    val x466 = Pipeline(name="x466", parent=x472, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x425 = CounterChain.copy(x472, "x425")
      val x453 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x454 = CounterChain(name = "x454", x453)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x425(0)), CU.ctr(stage(0), x454(0))), op=FixAdd, results=List(CU.reduce(stage(1))))
      val (rs1, rr15) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr15), op=Bypass, results=List(CU.scalarOut(stage(2), x421_argout)))
    }
    
  }
}
