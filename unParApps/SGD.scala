import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SGDDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4728_oc = OffChip("x4728")
    val x4718_argin = ArgIn("x4718")
    val x4827_scalar = Scalar("x4827")
    val x4720_argin = ArgIn("x4720")
    val x4828_vector = Vector("x4828")
    val x4727_oc = OffChip("x4727")
    val x4879_scalar = Scalar("x4879")
    val x4719_argin = ArgIn("x4719")
    val x4726_oc = OffChip("x4726")
    val x4771_vector = Vector("x4771")
    val x4801_mc = MemoryController(TileLoad, x4727_oc)
    val x4855_mc = MemoryController(TileLoad, x4726_oc)
    val x4962_mc = MemoryController(TileStore, x4728_oc)
    val x4966 = Sequential(name = "x4966", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4966_unitcc = CounterChain(name = "x4966_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4944 = Sequential(name = "x4944", parent=x4966, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x4718_argin).out, Const("1i").out) // Counter
      val x4774 = CounterChain(name = "x4774", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4942 = Sequential(name = "x4942", parent=x4944, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x4719_argin).out, Const("192i").out) // Counter
      val x4777 = CounterChain(name = "x4777", ctr3)
      var stage: List[Stage] = Nil
    }
    val x4822 = StreamController(name = "x4822", parent=x4942, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4822_unitcc = CounterChain(name = "x4822_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4797_0 = UnitPipeline(name = "x4797_0", parent=x4822, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr220 = CU.temp
      val tr219 = CU.temp
      val tr217 = CU.temp
      val tr216 = CU.temp
      val tr214 = CU.temp
      val tr209 = CU.temp
      val x4777 = CounterChain.copy(x4942, "x4777")
      val x4797_unitcc = CounterChain(name = "x4797_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4777(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr209)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4777(0)), CU.temp(stage(1), tr209)), op=FixSub, results=List(CU.scalarOut(stage(2), x4801_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr209), Const("192i")), op=FixAdd, results=List(CU.temp(stage(3), tr214)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr214), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr216)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr214), CU.temp(stage(4), tr216)), op=FixSub, results=List(CU.temp(stage(5), tr217)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr216), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr219)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr219), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr220)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr217), CU.temp(stage(7), tr220)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4801_mc.len)))
    }
    val x4940 = Sequential(name = "x4940", parent=x4942, deps=List(x4822)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4824 = CounterChain(name = "x4824", ctr7)
      val ctr9 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x4826 = CounterChain(name = "x4826", ctr9)
      var stage: List[Stage] = Nil
    }
    val x4876 = StreamController(name = "x4876", parent=x4940, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x4832 = CounterChain(name = "x4832", ctr10)
      var stage: List[Stage] = Nil
    }
    val x4851_0 = UnitPipeline(name = "x4851_0", parent=x4876, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr247 = CU.temp
      val tr246 = CU.temp
      val tr244 = CU.temp
      val tr243 = CU.temp
      val tr241 = CU.temp
      val tr237 = CU.temp
      val tr235 = CU.temp
      val tr233 = CU.temp
      val tr232 = CU.temp
      val x4824 = CounterChain.copy(x4940, "x4824")
      val x4832 = CounterChain.copy(x4876, "x4832")
      val x4777 = CounterChain.copy(x4942, "x4777")
      val x4851_unitcc = CounterChain(name = "x4851_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4777(0)), CU.ctr(stage(0), x4824(0))), op=FixAdd, results=List(CU.temp(stage(1), tr232)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr232), CU.ctr(stage(1), x4832(0))), op=FixAdd, results=List(CU.temp(stage(2), tr233)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr233), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr235)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr235), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr237)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr235), CU.temp(stage(4), tr237)), op=FixSub, results=List(CU.scalarOut(stage(5), x4855_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr237), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr241)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr241), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr243)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr241), CU.temp(stage(7), tr243)), op=FixSub, results=List(CU.temp(stage(8), tr244)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr243), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr246)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr246), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr247)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr244), CU.temp(stage(10), tr247)), op=FixAdd, results=List(CU.scalarOut(stage(11), x4855_mc.len)))
    }
    val x4904 = Sequential(name = "x4904", parent=x4940, deps=List(x4876)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4904_unitcc = CounterChain(name = "x4904_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4895_0 = Pipeline(name = "x4895_0", parent=x4904, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4826 = CounterChain.copy(x4940, "x4826")
      val ctr12 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4881 = CounterChain(name = "x4881", ctr12)
      val x4829_x4884 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4855_mc.vdata).rdAddr(x4881(0))
      val x4771_x4887 = SRAM(size = 768, writeCtr = x4826(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4771_vector).rdAddr(x4881(0)).wtAddr(x4826(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4829_x4884.load, x4771_x4887.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr271) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr271), op=Bypass, results=List(CU.scalarOut(stage(2), x4879_scalar)))
    }
    val x4902_0 = UnitPipeline(name = "x4902_0", parent=x4904, deps=List(x4895_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4824 = CounterChain.copy(x4940, "x4824")
      val x4902_unitcc = CounterChain(name = "x4902_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4770_x4898 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4801_mc.vdata).rdAddr(x4824(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x4879_scalar), x4770_x4898.load), op=FltSub, results=List(CU.scalarOut(stage(1), x4827_scalar)))
    }
    val x4921_0 = Pipeline(name = "x4921_0", parent=x4940, deps=List(x4904)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr286 = CU.temp
      val ctr16 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4906 = CounterChain(name = "x4906", ctr16)
      val x4829_x4909 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4855_mc.vdata).rdAddr(x4906(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4829_x4909.load, CU.scalarIn(stage(0), x4827_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr286)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr286), CU.scalarIn(stage(1), x4720_argin)), op=FltMul, results=List(CU.vecOut(stage(2), x4828_vector)))
    }
    val x4938_0 = Pipeline(name = "x4938_0", parent=x4940, deps=List(x4921_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4906 = CounterChain.copy(x4921_0, "x4906")
      val ctr18 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4826 = CounterChain(name = "x4826", ctr18)
      val x4828_x4924 = SRAM(size = 768, writeCtr = x4906(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4828_vector).rdAddr(x4826(0)).wtAddr(x4906(0))
      val x4771_x4927 = SRAM(size = 768, writeCtr = x4826(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x4826(0))
      val wr303 = CU.wtAddr(x4771_x4927)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4828_x4924.load, x4771_x4927.load), op=FltAdd, results=List(CU.vecOut(stage(1), x4771_vector), CU.store(stage(1), x4771_x4927)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4826(0))), op=Bypass, results=List(CU.wtAddr(stage(2), wr303)))
    }
    val x4964 = StreamController(name = "x4964", parent=x4966, deps=List(x4944)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4964_unitcc = CounterChain(name = "x4964_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4966_leafX = Pipeline(name = "x4966_leafX", parent=x4966, deps=List(x4964)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4966_unitcc = CounterChain.copy(x4966, "x4966_unitcc")
      var stage: List[Stage] = Nil
    }
    val x4949_0 = UnitPipeline(name = "x4949_0", parent=x4964, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4949_unitcc = CounterChain(name = "x4949_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x4962_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x4962_mc.len)))
    }
    val x4960_0 = Pipeline(name = "x4960_0", parent=x4964, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4826 = CounterChain.copy(x4940, "x4826")
      val ctr19 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4951 = CounterChain(name = "x4951", ctr19)
      val x4771_x4954 = SRAM(size = 768, writeCtr = x4826(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4771_vector).rdAddr(x4951(0)).wtAddr(x4826(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4771_x4954.load), op=Bypass, results=List(CU.vecOut(stage(1), x4962_mc.vdata)))
    }
    
  }
}
