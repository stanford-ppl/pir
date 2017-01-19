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
    val x4755_scalar = Scalar("x4755")
    val x4807_scalar = Scalar("x4807")
    val x4701_vector = Vector("x4701")
    val x4657_oc = OffChip("x4657")
    val x4656_oc = OffChip("x4656")
    val x4658_oc = OffChip("x4658")
    val x4756_vector = Vector("x4756")
    val x4729_mc = MemoryController(TileLoad, x4657_oc)
    val x4889_mc = MemoryController(TileStore, x4658_oc)
    val x4783_mc = MemoryController(TileLoad, x4656_oc)
    val x4893 = Sequential(name = "x4893", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4893_unitcc = CounterChain(name = "x4893_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4871 = Sequential(name = "x4871", parent=x4893, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("30i").out, Const("1i").out) // Counter
      val x4703 = CounterChain(name = "x4703", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4869 = Sequential(name = "x4869", parent=x4871, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("38400i").out, Const("192i").out) // Counter
      val x4705 = CounterChain(name = "x4705", ctr3)
      var stage: List[Stage] = Nil
    }
    val x4750 = StreamController(name = "x4750", parent=x4869, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4750_unitcc = CounterChain(name = "x4750_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4725_0 = UnitPipeline(name = "x4725_0", parent=x4750, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr217 = CU.temp
      val tr216 = CU.temp
      val tr214 = CU.temp
      val tr213 = CU.temp
      val tr211 = CU.temp
      val tr206 = CU.temp
      val x4725_unitcc = CounterChain(name = "x4725_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4705 = CounterChain.copy(x4869, "x4705")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4705(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr206)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4705(0)), CU.temp(stage(1), tr206)), op=FixSub, results=List(CU.scalarOut(stage(2), x4729_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr206), Const("192i")), op=FixAdd, results=List(CU.temp(stage(3), tr211)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr211), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr213)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr211), CU.temp(stage(4), tr213)), op=FixSub, results=List(CU.temp(stage(5), tr214)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr213), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr216)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr216), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr217)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr214), CU.temp(stage(7), tr217)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4729_mc.len)))
    }
    val x4867 = Sequential(name = "x4867", parent=x4869, deps=List(x4750)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4752 = CounterChain(name = "x4752", ctr7)
      val ctr9 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x4754 = CounterChain(name = "x4754", ctr9)
      var stage: List[Stage] = Nil
    }
    val x4804 = StreamController(name = "x4804", parent=x4867, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x4760 = CounterChain(name = "x4760", ctr10)
      var stage: List[Stage] = Nil
    }
    val x4779_0 = UnitPipeline(name = "x4779_0", parent=x4804, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr244 = CU.temp
      val tr243 = CU.temp
      val tr241 = CU.temp
      val tr240 = CU.temp
      val tr238 = CU.temp
      val tr234 = CU.temp
      val tr232 = CU.temp
      val tr230 = CU.temp
      val tr229 = CU.temp
      val x4760 = CounterChain.copy(x4804, "x4760")
      val x4752 = CounterChain.copy(x4867, "x4752")
      val x4779_unitcc = CounterChain(name = "x4779_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4705 = CounterChain.copy(x4869, "x4705")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4705(0)), CU.ctr(stage(0), x4752(0))), op=FixAdd, results=List(CU.temp(stage(1), tr229)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr229), CU.ctr(stage(1), x4760(0))), op=FixAdd, results=List(CU.temp(stage(2), tr230)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr230), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr232)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr232), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr234)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr232), CU.temp(stage(4), tr234)), op=FixSub, results=List(CU.scalarOut(stage(5), x4783_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr234), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr238)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr238), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr240)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr238), CU.temp(stage(7), tr240)), op=FixSub, results=List(CU.temp(stage(8), tr241)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr240), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr243)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr243), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr244)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr241), CU.temp(stage(10), tr244)), op=FixAdd, results=List(CU.scalarOut(stage(11), x4783_mc.len)))
    }
    val x4832 = Sequential(name = "x4832", parent=x4867, deps=List(x4804)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4832_unitcc = CounterChain(name = "x4832_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4823_0 = Pipeline(name = "x4823_0", parent=x4832, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4809 = CounterChain(name = "x4809", ctr12)
      val x4754 = CounterChain.copy("x4865_0", "x4754")
      val x4757_x4812 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4783_mc.vdata).rdAddr(x4809(0))
      val x4701_x4815 = SRAM(size = 768, writeCtr = x4754(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4701_vector).rdAddr(x4809(0)).wtAddr(x4754(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4757_x4812.load, x4701_x4815.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr268) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr268), op=Bypass, results=List(CU.scalarOut(stage(2), x4807_scalar)))
    }
    val x4830_0 = UnitPipeline(name = "x4830_0", parent=x4832, deps=List(x4823_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4830_unitcc = CounterChain(name = "x4830_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4752 = CounterChain.copy(x4867, "x4752")
      val x4700_x4826 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4729_mc.vdata).rdAddr(x4752(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x4807_scalar), x4700_x4826.load), op=FltSub, results=List(CU.scalarOut(stage(1), x4755_scalar)))
    }
    val x4848_0 = Pipeline(name = "x4848_0", parent=x4867, deps=List(x4832)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr282 = CU.temp
      val ctr16 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4834 = CounterChain(name = "x4834", ctr16)
      val x4757_x4837 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4783_mc.vdata).rdAddr(x4834(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4757_x4837.load, CU.scalarIn(stage(0), x4755_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr282)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr282), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x4756_vector)))
    }
    val x4865_0 = Pipeline(name = "x4865_0", parent=x4867, deps=List(x4848_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr18 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4754 = CounterChain(name = "x4754", ctr18)
      val x4834 = CounterChain.copy(x4848_0, "x4834")
      val x4756_x4851 = SRAM(size = 768, writeCtr = x4834(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4756_vector).rdAddr(x4754(0)).wtAddr(x4834(0))
      val x4701_x4854 = SRAM(size = 768, writeCtr = x4754(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x4754(0))
      val wr300 = CU.wtAddr(x4701_x4854)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4756_x4851.load, x4701_x4854.load), op=FltAdd, results=List(CU.vecOut(stage(1), x4701_vector), CU.store(stage(1), x4701_x4854)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4754(0))), op=Bypass, results=List(CU.wtAddr(stage(2), wr300)))
    }
    val x4891 = StreamController(name = "x4891", parent=x4893, deps=List(x4871)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4891_unitcc = CounterChain(name = "x4891_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4876_0 = UnitPipeline(name = "x4876_0", parent=x4891, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4876_unitcc = CounterChain(name = "x4876_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x4889_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x4889_mc.len)))
    }
    val x4887_0 = Pipeline(name = "x4887_0", parent=x4891, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr19 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x4878 = CounterChain(name = "x4878", ctr19)
      val x4754 = CounterChain.copy("x4865_0", "x4754")
      val x4701_x4881 = SRAM(size = 768, writeCtr = x4754(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4701_vector).rdAddr(x4878(0)).wtAddr(x4754(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4701_x4881.load), op=Bypass, results=List(CU.vecOut(stage(1), x4889_mc.vdata)))
    }
    
  }
}
