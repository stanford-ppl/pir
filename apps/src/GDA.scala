import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object GDA extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1958_1_wt_vector = Vector("x1958_1_wt")
    val x1851_0_rd_vector = Vector("x1851_0_rd")
    val x1890_scalar = Scalar("x1890")
    val x1884_0_wt_vector = Vector("x1884_0_wt")
    val x1844_oc = OffChip("x1844")
    val x1958_0_rd_vector = Vector("x1958_0_rd")
    val x1884_1_rd_vector = Vector("x1884_1_rd")
    val x1959_0_rd_vector = Vector("x1959_0_rd")
    val x1884_0_rd_vector = Vector("x1884_0_rd")
    val x1884_1_wt_vector = Vector("x1884_1_wt")
    val x1845_oc = OffChip("x1845")
    val x1954_0_wt_vector = Vector("x1954_0_wt")
    val x1958_0_wt_vector = Vector("x1958_0_wt")
    val x1843_oc = OffChip("x1843")
    val x1841_oc = OffChip("x1841")
    val x1846_oc = OffChip("x1846")
    val x1954_1_wt_vector = Vector("x1954_1_wt")
    val x1852_0_rd_vector = Vector("x1852_0_rd")
    val x1838_argin = ArgIn("x1838")
    val x1954_0_rd_vector = Vector("x1954_0_rd")
    val x1959_0_wt_vector = Vector("x1959_0_wt")
    val x1954_1_rd_vector = Vector("x1954_1_rd")
    val x1958_1_rd_vector = Vector("x1958_1_rd")
    val x1888_0_rd_vector = Vector("x1888_0_rd")
    val x1889_0_rd_vector = Vector("x1889_0_rd")
    val x1913_mc = MemoryController(TileLoad, x1843_oc).parent("x1930")
    val x1859_mc = MemoryController(TileLoad, x1844_oc).parent("x1867")
    val x1874_mc = MemoryController(TileLoad, x1845_oc).parent("x1882")
    val x2028_mc = MemoryController(TileStore, x1846_oc).parent("x2029")
    val x1939_mc = MemoryController(TileLoad, x1841_oc).parent("x1947")
    val x2030 = Sequential(name = "x2030", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2030_unitcc = CounterChain(name = "x2030_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1851_dsp0 = MemoryPipeline(name = "x1851_dsp0", parent="x2030") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1851_x1965 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x1859_mc.data).rdPort(x1851_0_rd_vector).rdAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1852_dsp0 = MemoryPipeline(name = "x1852_dsp0", parent="x2030") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1852_x1964 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x1874_mc.data).rdPort(x1852_0_rd_vector).rdAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1867 = StreamController(name = "x1867", parent=x2030) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1867_unitcc = CounterChain(name = "x1867_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1856 = StreamPipeline(name = "x1856", parent=x1867) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1856_unitcc = CounterChain(name = "x1856_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x1859_mc.ofs)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1859_mc.len)))
    }
    val x1882 = StreamController(name = "x1882", parent=x2030) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1882_unitcc = CounterChain(name = "x1882_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1871 = StreamPipeline(name = "x1871", parent=x1882) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1871_unitcc = CounterChain(name = "x1871_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x1874_mc.ofs)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1874_mc.len)))
    }
    val x1884_dsp0 = MemoryPipeline(name = "x1884_dsp0", parent="x2012") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr105 = CU.temp
      val tr106 = CU.temp
      val tr110 = CU.temp
      val tr113 = CU.temp
      val x2002 = CounterChain.copy("x2011", "x2002")
      val x2015 = CounterChain.copy("x2029", "x2015")
      val x2020 = CounterChain.copy("x2025", "x2020")
      val x1884_x2021 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1884_0_wt_vector).rdPort(x1884_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1884_x2021))
      Stage(stage(1), operands=List(x2002(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr105)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr105), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1884_x2021.writeAddr, CU.temp(stage(2), tr106)))
      stage = stage0 +: RAStages(2, List(x1884_x2021))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2015(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr110)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr110), CU.ctr(stage(1), x2020(0))), op=FixAdd, results=List(x1884_x2021.readAddr, CU.temp(stage(2), tr113)))
    }
    val x1884_dsp1 = MemoryPipeline(name = "x1884_dsp1", parent="x2012") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr120 = CU.temp
      val tr121 = CU.temp
      val tr123 = CU.temp
      val tr124 = CU.temp
      val x2002 = CounterChain.copy("x2011", "x2002")
      val x1884_x2004 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1884_1_wt_vector).rdPort(x1884_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1884_x2004))
      Stage(stage(1), operands=List(x2002(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr120)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr120), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1884_x2004.writeAddr, CU.temp(stage(2), tr121)))
      stage = stage0 +: RAStages(2, List(x1884_x2004))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2002(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr123)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr123), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1884_x2004.readAddr, CU.temp(stage(2), tr124)))
    }
    val x2012 = MetaPipeline(name = "x2012", parent=x2030) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1838_argin), Const("20i").out) // Counter
      val x1887 = CounterChain(name = "x1887", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1888_dsp0 = MemoryPipeline(name = "x1888_dsp0", parent="x2012") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1957 = CounterChain.copy("x1999", "x1957")
      val x1888_x1963 = SemiFIFO(size = 20, banking = Duplicated()).wtPort(x1913_mc.data).rdPort(x1888_0_rd_vector).rdAddr(x1957(0))
      var stage: List[Stage] = Nil
    }
    val x1889_dsp0 = MemoryPipeline(name = "x1889_dsp0", parent="x2012") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr135 = CU.temp
      val tr138 = CU.temp
      val x1957 = CounterChain.copy("x1999", "x1957")
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1889_x1962 = SemiFIFO(size = 1280, banking = Strided(1)).wtPort(x1939_mc.data).rdPort(x1889_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1889_x1962))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1957(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr135)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr135), CU.ctr(stage(1), x1961(0))), op=FixAdd, results=List(x1889_x1962.readAddr, CU.temp(stage(2), tr138)))
    }
    val x1930 = StreamController(name = "x1930", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1930_unitcc = CounterChain(name = "x1930_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1909 = StreamPipeline(name = "x1909", parent=x1930) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr143 = CU.temp
      val tr145 = CU.temp
      val tr148 = CU.temp
      val tr150 = CU.temp
      val tr152 = CU.temp
      val tr153 = CU.temp
      val tr154 = CU.temp
      val tr155 = CU.temp
      val x1909_unitcc = CounterChain(name = "x1909_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1887 = CounterChain.copy("x2012", "x1887")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1887(0)), Const("16i")), op=FixMod, results=List(CU.temp(stage(1), tr143)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1887(0)), CU.temp(stage(1), tr143)), op=FixSub, results=List(CU.scalarOut(stage(2), x1913_mc.ofs), CU.temp(stage(2), tr145)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr143), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr148)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr148), Const("16i")), op=FixMod, results=List(CU.temp(stage(4), tr150)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr150), Const("0i")), op=FixNeq, results=List(CU.temp(stage(5), tr152)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr152), Const("16i"), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr153)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr148), CU.temp(stage(6), tr150)), op=FixSub, results=List(CU.temp(stage(7), tr154)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr154), CU.temp(stage(7), tr153)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1913_mc.len), CU.temp(stage(8), tr155)))
    }
    val x1947 = StreamController(name = "x1947", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x1933 = CounterChain(name = "x1933", ctr5)
      var stage: List[Stage] = Nil
    }
    val x1936 = StreamPipeline(name = "x1936", parent=x1947) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr170 = CU.temp
      val tr172 = CU.temp
      val x1936_unitcc = CounterChain(name = "x1936_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1887 = CounterChain.copy("x2012", "x1887")
      val x1933 = CounterChain.copy("x1947", "x1933")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1887(0)), CU.ctr(stage(0), x1933(0))), op=FixAdd, results=List(CU.temp(stage(1), tr170)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr170), Const("64i")), op=FixMul, results=List(CU.scalarOut(stage(2), x1939_mc.ofs), CU.temp(stage(2), tr172)))
      Stage(stage(3), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(3), x1939_mc.len)))
    }
    val x1952 = UnitPipeline(name = "x1952", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr186 = CU.temp
      val tr188 = CU.temp
      val x1952_unitcc = CounterChain(name = "x1952_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x1887 = CounterChain.copy("x2012", "x1887")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1838_argin), CU.ctr(stage(0), x1887(0))), op=FixSub, results=List(CU.temp(stage(1), tr186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr186), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x1890_scalar), CU.temp(stage(2), tr188)))
    }
    val x1954_dsp0 = MemoryPipeline(name = "x1954_dsp0", parent="x1999") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr195 = CU.temp
      val tr196 = CU.temp
      val tr202 = CU.temp
      val tr203 = CU.temp
      val x1989 = CounterChain.copy("x1998", "x1989")
      val x2002 = CounterChain.copy("x2011", "x2002")
      val x1954_x2003 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1954_0_wt_vector).rdPort(x1954_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1954_x2003))
      Stage(stage(1), operands=List(x1989(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr195)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr195), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1954_x2003.writeAddr, CU.temp(stage(2), tr196)))
      stage = stage0 +: RAStages(2, List(x1954_x2003))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2002(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr202)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr202), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1954_x2003.readAddr, CU.temp(stage(2), tr203)))
    }
    val x1954_dsp1 = MemoryPipeline(name = "x1954_dsp1", parent="x1999") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr210 = CU.temp
      val tr211 = CU.temp
      val tr213 = CU.temp
      val tr214 = CU.temp
      val x1989 = CounterChain.copy("x1998", "x1989")
      val x1954_x1991 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1954_1_wt_vector).rdPort(x1954_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1954_x1991))
      Stage(stage(1), operands=List(x1989(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr210)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr210), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1954_x1991.writeAddr, CU.temp(stage(2), tr211)))
      stage = stage0 +: RAStages(2, List(x1954_x1991))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1989(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr213)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr213), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1954_x1991.readAddr, CU.temp(stage(2), tr214)))
    }
    val x1999 = MetaPipeline(name = "x1999", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x1890_scalar), Const("1i").out) // Counter
      val x1957 = CounterChain(name = "x1957", ctr7)
      var stage: List[Stage] = Nil
    }
    val x1958_dsp0 = MemoryPipeline(name = "x1958_dsp0", parent="x1999") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1978 = CounterChain.copy("x1986", "x1978")
      val x1958_x1980 = SRAM(size = 64, banking = Strided(1)).wtPort(x1958_0_wt_vector).rdPort(x1958_0_rd_vector).rdAddr(x1978(1)).wtAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1958_dsp1 = MemoryPipeline(name = "x1958_dsp1", parent="x1999") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1978 = CounterChain.copy("x1986", "x1978")
      val x1958_x1979 = SRAM(size = 64, banking = Strided(1)).wtPort(x1958_1_wt_vector).rdPort(x1958_1_rd_vector).rdAddr(x1978(0)).wtAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1959_dsp0 = MemoryPipeline(name = "x1959_dsp0", parent="x1999") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr238 = CU.temp
      val tr239 = CU.temp
      val tr245 = CU.temp
      val tr246 = CU.temp
      val x1978 = CounterChain.copy("x1986", "x1978")
      val x1989 = CounterChain.copy("x1998", "x1989")
      val x1959_x1990 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1959_0_wt_vector).rdPort(x1959_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1959_x1990))
      Stage(stage(1), operands=List(x1978(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr238)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr238), CU.ctr(stage(1), x1978(1))), op=FixAdd, results=List(x1959_x1990.writeAddr, CU.temp(stage(2), tr239)))
      stage = stage0 +: RAStages(2, List(x1959_x1990))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1989(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr245)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr245), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1959_x1990.readAddr, CU.temp(stage(2), tr246)))
    }
    val x1975 = Pipeline(name = "x1975", parent=x1999) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr258 = CU.temp
      val tr259 = CU.temp
      val tr260 = CU.temp
      val ctr8 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1961 = CounterChain(name = "x1961", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1888_0_rd_vector), Const("1i")), op=FixEql, results=List(CU.temp(stage(1), tr258)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr258), CU.vecIn(stage(1), x1852_0_rd_vector), CU.vecIn(stage(1), x1851_0_rd_vector)), op=Mux, results=List(CU.temp(stage(2), tr259)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x1889_0_rd_vector), CU.temp(stage(2), tr259)), op=FixSub, results=List(CU.vecOut(stage(3), x1958_1_wt_vector), CU.vecOut(stage(3), x1958_0_wt_vector), CU.temp(stage(3), tr260)))
    }
    val x1986 = Pipeline(name = "x1986", parent=x1999) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr272 = CU.temp
      val ctr9 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1978 = CounterChain(name = "x1978", ctr9, ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1958_1_rd_vector), CU.vecIn(stage(0), x1958_0_rd_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x1959_0_wt_vector), CU.temp(stage(1), tr272)))
    }
    val x1998 = Pipeline(name = "x1998", parent=x1999) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr282 = CU.temp
      val tr283 = CU.temp
      val tr284 = CU.temp
      val ctr11 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1989 = CounterChain(name = "x1989", ctr11, ctr12)
      val x1957 = CounterChain.copy("x1999", "x1957")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1957(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr282)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1959_0_rd_vector), CU.vecIn(stage(1), x1954_1_rd_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr283)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr282), CU.vecIn(stage(2), x1959_0_rd_vector), CU.temp(stage(2), tr283)), op=Mux, results=List(CU.vecOut(stage(3), x1954_1_wt_vector), CU.vecOut(stage(3), x1954_0_wt_vector), CU.temp(stage(3), tr284)))
    }
    val x2011 = Pipeline(name = "x2011", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr292 = CU.temp
      val tr293 = CU.temp
      val tr294 = CU.temp
      val ctr13 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr14 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2002 = CounterChain(name = "x2002", ctr13, ctr14)
      val x1887 = CounterChain.copy("x2012", "x1887")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1887(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr292)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1954_0_rd_vector), CU.vecIn(stage(1), x1884_1_rd_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr293)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr292), CU.vecIn(stage(2), x1954_0_rd_vector), CU.temp(stage(2), tr293)), op=Mux, results=List(CU.vecOut(stage(3), x1884_1_wt_vector), CU.vecOut(stage(3), x1884_0_wt_vector), CU.temp(stage(3), tr294)))
    }
    val x2029 = StreamController(name = "x2029", parent=x2030) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2015 = CounterChain(name = "x2015", ctr15)
      var stage: List[Stage] = Nil
    }
    val x2018 = StreamPipeline(name = "x2018", parent=x2029) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr300 = CU.temp
      val x2018_unitcc = CounterChain(name = "x2018_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2015 = CounterChain.copy("x2029", "x2015")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2015(0)), Const("64i")), op=FixMul, results=List(CU.scalarOut(stage(1), x2028_mc.ofs), CU.temp(stage(1), tr300)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x2028_mc.len)))
    }
    val x2025 = StreamPipeline(name = "x2025", parent=x2029) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr16 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2020 = CounterChain(name = "x2020", ctr16)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1884_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x2028_mc.data)))
    }
    
  }
}
