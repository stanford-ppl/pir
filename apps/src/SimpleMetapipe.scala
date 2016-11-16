import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleMetaPipe extends PIRApp {
  override val arch = SN_2x2
  def main(args: String*)(top:Top) = {
    val x1634_argout = ArgOut("x1634")
    val x1816_scalar = Scalar("x1816")
    val v0 = Vector("v0")
    val x1633_argin = ArgIn("x1633")
    val x1839 = MetaPipeline(name = "x1839", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1633_argin).out, Const("64i").out) // Counter
      val x1718 = CounterChain(name = "x1718", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1831 = Pipeline(name = "x1831", parent=x1839, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("64i").out, Const("16i").out) // Counter
      val x1819 = CounterChain(name = "x1819", ctr5)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("3i")), op=Bypass, results=List(CU.vecOut(stage(1), v0)))
    }
    val x1831_0 = Pipeline(name = "x1831_0", parent=x1839, deps=List(x1831)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("64i").out, Const("16i").out) // Counter
      val copy = CounterChain.copy(x1831, "x1819")
      val x1819 = CounterChain(name = "x1819", ctr5)
      val x1722_x1823 = SRAM(size = 64, banking = Strided(1), buffering=DoubleBuffer(swapRead=x1819.outer, swapWrite=copy(0))).wtPort(v0).rdAddr(x1819(0)).wtAddr(copy(0)).wtCtr(x1819.inner)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("3i"), x1722_x1823.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr117) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr117), op=Bypass, results=List(CU.scalarOut(stage(2), x1816_scalar)))
    }
    val x1837_0 = UnitPipeline(name = "x1837_0", parent=x1839, deps=List(x1831_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar121 = CU.accum(init = Const("0i"))
      val x1837_unitcc = CounterChain(name = "x1837_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1816_scalar), CU.accum(stage(1), ar121)), op=FixAdd, results=List(CU.scalarOut(stage(1), x1634_argout), CU.accum(stage(1), ar121)))
    }
    
  }
}
