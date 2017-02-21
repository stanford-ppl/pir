import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x732_oc = OffChip("x732")
    val x727_argin = ArgIn("x727")
    val x733_argout = ArgOut("x733")
    val x742_0_rd_vector = Vector("x742_0_rd")
    val x741_0_rd_vector = Vector("x741_0_rd")
    val x776_scalar = Scalar("x776")
    val x730_oc = OffChip("x730")
    val x750_mc = MemoryController(TileLoad, x730_oc).parent("x758")
    val x766_mc = MemoryController(TileLoad, x732_oc).parent("x774")
    val x798 = Sequential(name = "x798", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x798_unitcc = CounterChain(name = "x798_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x794 = MetaPipeline(name = "x794", parent=x798) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x727_argin).out, Const("640i").out) // Counter
      val x740 = CounterChain(name = "x740", ctr1)
      var stage: List[Stage] = Nil
    }
    val x741_dsp0 = MemoryPipeline(name = "x741_dsp0", parent=x794) { implicit CU => 
      val stage0 = CU.emptyStage
      val x778 = CounterChain.copy("x787", "x778")
      val x741_x779 = SemiFIFO(size = 640, banking = Strided(1)).wtPort(x750_mc.data).rdPort(x741_0_rd_vector).rdAddr(x778(0))
      var stage: List[Stage] = Nil
    }
    val x742_dsp0 = MemoryPipeline(name = "x742_dsp0", parent=x794) { implicit CU => 
      val stage0 = CU.emptyStage
      val x778 = CounterChain.copy("x787", "x778")
      val x742_x780 = SemiFIFO(size = 640, banking = Strided(1)).wtPort(x766_mc.data).rdPort(x742_0_rd_vector).rdAddr(x778(0))
      var stage: List[Stage] = Nil
    }
    val x758 = StreamController(name = "x758", parent=x794) { implicit CU => 
      val stage0 = CU.emptyStage
      val x758_unitcc = CounterChain(name = "x758_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x746 = StreamPipeline(name = "x746", parent=x758) { implicit CU => 
      val stage0 = CU.emptyStage
      val x740 = CounterChain.copy("x794", "x740")
      val x746_unitcc = CounterChain(name = "x746_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x740(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x750_mc.ofs)))
      Stage(stage(2), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(2), x750_mc.len)))
    }
    val x774 = StreamController(name = "x774", parent=x794) { implicit CU => 
      val stage0 = CU.emptyStage
      val x774_unitcc = CounterChain(name = "x774_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x762 = StreamPipeline(name = "x762", parent=x774) { implicit CU => 
      val stage0 = CU.emptyStage
      val x740 = CounterChain.copy("x794", "x740")
      val x762_unitcc = CounterChain(name = "x762_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x740(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x766_mc.ofs)))
      Stage(stage(2), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(2), x766_mc.len)))
    }
    val x787 = Pipeline(name = "x787", parent=x794) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr78 = CU.temp
      val ctr4 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x778 = CounterChain(name = "x778", ctr4)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x741_0_rd_vector), CU.vecIn(stage(0), x742_0_rd_vector)), op=FixMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr78)))
      val (rs1, rr81) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr81), op=Bypass, results=List(CU.scalarOut(stage(2), x776_scalar)))
    }
    val x793 = UnitPipeline(name = "x793", parent=x794) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar85 = CU.accum(init = Const("0i"))
      val x793_unitcc = CounterChain(name = "x793_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x776_scalar)), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr88) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(CU.accum(stage(2), ar85)), op=Bypass, results=List(CU.scalarOut(stage(2), x733_argout)))
    }
    
  }
}
