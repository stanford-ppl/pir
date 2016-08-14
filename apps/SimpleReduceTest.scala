import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object SimpleReduceTestDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x305_argout = ArgOut()
    val x304_argin = ArgIn()
    val x337 = ComputeUnit(name="x337", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x337_unitCC = CounterChain(name = "x337_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x330 = ComputeUnit(name="x330", parent=x337, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x307 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x308 = CounterChain(name = "x308", x307)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x304_argin), CU.ctr(stage(0), x308(0))), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr9) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr9), op=Bypass, results=List(CU.scalarOut(stage(1), x305_argout)))
    }
    
  }
}
