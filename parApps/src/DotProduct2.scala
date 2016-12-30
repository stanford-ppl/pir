import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProduct2Design extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1744_argout = ArgOut("x1744")
    val x1746_oc = OffChip("x1746")
    val x2018_scalar = Scalar("x2018")
    val x1745_oc = OffChip("x1745")
    val x2019_scalar = Scalar("x2019")
    val x1947_mc = MemoryController(TileLoad, x1745_oc)
    val x1992_mc = MemoryController(TileLoad, x1746_oc)
    val x1900_mc = MemoryController(TileLoad, x1746_oc)
    val x1855_mc = MemoryController(TileLoad, x1745_oc)
    val x2069 = Sequential(name = "x2069", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2069_unitcc = CounterChain(name = "x2069_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2063 = MetaPipeline(name = "x2063", parent=x2069, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("768000000i").out, Const("8000i").out) // Counter
      val x1825 = CounterChain(name = "x1825", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1876 = StreamController(name = "x1876", parent=x2063, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1876_unitcc = CounterChain(name = "x1876_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1851_0 = UnitPipeline(name = "x1851_0", parent=x1876, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr158 = CU.temp
      val tr157 = CU.temp
      val tr155 = CU.temp
      val tr154 = CU.temp
      val tr152 = CU.temp
      val tr147 = CU.temp
      val x1825 = CounterChain.copy(x2063, "x1825")
      val x1851_unitcc = CounterChain(name = "x1851_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1825(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr147)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1825(0)), CU.temp(stage(1), tr147)), op=FixSub, results=List(CU.scalarOut(stage(2), x1855_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr147), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr152)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr152), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr154)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr152), CU.temp(stage(4), tr154)), op=FixSub, results=List(CU.temp(stage(5), tr155)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr154), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr157)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr157), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr158)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr155), CU.temp(stage(7), tr158)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1855_mc.len)))
    }
    val x1921 = StreamController(name = "x1921", parent=x2063, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1921_unitcc = CounterChain(name = "x1921_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1896_0 = UnitPipeline(name = "x1896_0", parent=x1921, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr181 = CU.temp
      val tr180 = CU.temp
      val tr178 = CU.temp
      val tr177 = CU.temp
      val tr175 = CU.temp
      val tr170 = CU.temp
      val x1825 = CounterChain.copy(x2063, "x1825")
      val x1896_unitcc = CounterChain(name = "x1896_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1825(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr170)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1825(0)), CU.temp(stage(1), tr170)), op=FixSub, results=List(CU.scalarOut(stage(2), x1900_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr170), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr175)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr175), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr177)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr175), CU.temp(stage(4), tr177)), op=FixSub, results=List(CU.temp(stage(5), tr178)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr177), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr180)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr180), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr181)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr178), CU.temp(stage(7), tr181)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1900_mc.len)))
    }
    val x1968 = StreamController(name = "x1968", parent=x2063, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1968_unitcc = CounterChain(name = "x1968_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1943_0 = UnitPipeline(name = "x1943_0", parent=x1968, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr204 = CU.temp
      val tr203 = CU.temp
      val tr201 = CU.temp
      val tr200 = CU.temp
      val tr198 = CU.temp
      val tr193 = CU.temp
      val x1825 = CounterChain.copy(x2063, "x1825")
      val x1943_unitcc = CounterChain(name = "x1943_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1825(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr193)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1825(0)), CU.temp(stage(1), tr193)), op=FixSub, results=List(CU.scalarOut(stage(2), x1947_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr193), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr198)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr198), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr200)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr198), CU.temp(stage(4), tr200)), op=FixSub, results=List(CU.temp(stage(5), tr201)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr200), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr203)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr203), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr204)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr201), CU.temp(stage(7), tr204)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1947_mc.len)))
    }
    val x2013 = StreamController(name = "x2013", parent=x2063, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2013_unitcc = CounterChain(name = "x2013_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1988_0 = UnitPipeline(name = "x1988_0", parent=x2013, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr227 = CU.temp
      val tr226 = CU.temp
      val tr224 = CU.temp
      val tr223 = CU.temp
      val tr221 = CU.temp
      val tr216 = CU.temp
      val x1825 = CounterChain.copy(x2063, "x1825")
      val x1988_unitcc = CounterChain(name = "x1988_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1825(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr216)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1825(0)), CU.temp(stage(1), tr216)), op=FixSub, results=List(CU.scalarOut(stage(2), x1992_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr216), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr221)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr221), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr223)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr221), CU.temp(stage(4), tr223)), op=FixSub, results=List(CU.temp(stage(5), tr224)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr223), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr226)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr226), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr227)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr224), CU.temp(stage(7), tr227)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1992_mc.len)))
    }
    val x2037_0 = Pipeline(name = "x2037_0", parent=x2063, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x2022 = CounterChain(name = "x2022", ctr5)
      val x1828_x2026 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x2022(0))).wtPort(x1855_mc.vdata).rdAddr(x2022(0))
      val x1830_x2029 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x2022(0))).wtPort(x1900_mc.vdata).rdAddr(x2022(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1828_x2026.load, x1830_x2029.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr249) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr249), op=Bypass, results=List(CU.scalarOut(stage(2), x2018_scalar)))
    }
    val x2051_0 = Pipeline(name = "x2051_0", parent=x2063, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x2023 = CounterChain(name = "x2023", ctr10)
      val x1829_x2040 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x2023(0))).wtPort(x1947_mc.vdata).rdAddr(x2023(0))
      val x1831_x2043 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x2023(0))).wtPort(x1992_mc.vdata).rdAddr(x2023(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1829_x2040.load, x1831_x2043.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr262) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr262), op=Bypass, results=List(CU.scalarOut(stage(2), x2019_scalar)))
    }
    val x2061_0 = UnitPipeline(name = "x2061_0", parent=x2063, deps=List(x2037_0, x2051_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr268 = CU.temp
      val ar267 = CU.accum(init = Const("0i"))
      val x2061_unitcc = CounterChain(name = "x2061_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2018_scalar), CU.scalarIn(stage(0), x2019_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr268)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr268), CU.accum(stage(2), ar267)), op=FixAdd, results=List(CU.scalarOut(stage(2), x1744_argout), CU.accum(stage(2), ar267)))
    }
    val x2063_leaf = UnitPipeline(name = "x2063_leaf", parent=x2063, deps=List(x1921, x2061_0, x1876, x1968, x2013)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1825 = CounterChain.copy(x2063, "x1825")
      val x2063_unitcc = CounterChain(name = "x2063_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    
  }
}
