import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object GDA extends PIRApp {
  override val arch = SN_8x8

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
    val x1892_scalar = Scalar("x1892")
    val x1846_oc = OffChip("x1846")
    val x1954_1_wt_vector = Vector("x1954_1_wt")
    val x1852_0_rd_vector = Vector("x1852_0_rd")
    val x1895_scalar = Scalar("x1895")
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
    val x1851_dsp0 = MemoryPipeline(name = "x1851_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1851_x1965 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x1859_mc.data).rdPort(x1851_0_rd_vector).rdAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1852_dsp0 = MemoryPipeline(name = "x1852_dsp0") { implicit CU => 
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
    val x1884_dsp0 = MemoryPipeline(name = "x1884_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr277 = CU.temp
      val tr278 = CU.temp
      val tr280 = CU.temp
      val tr281 = CU.temp
      val x2020 = CounterChain.copy("x2025", "x2020")
      val x2015 = CounterChain.copy("x2029", "x2015")
      val x2002 = CounterChain.copy("x2011", "x2002")
      val x1884_x2021 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1884_0_wt_vector).rdPort(x1884_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1884_x2021))
      Stage(stage(1), operands=List(x2002(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr277)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr277), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1884_x2021.writeAddr, CU.temp(stage(2), tr278)))
      stage = stage0 +: RAStages(2, List(x1884_x2021))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2015(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr280)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr280), CU.ctr(stage(1), x2020(0))), op=FixAdd, results=List(x1884_x2021.readAddr, CU.temp(stage(2), tr281)))
    }
    val x1884_dsp1 = MemoryPipeline(name = "x1884_dsp1") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr284 = CU.temp
      val tr285 = CU.temp
      val tr287 = CU.temp
      val tr288 = CU.temp
      val x2002 = CounterChain.copy("x2011", "x2002")
      val x1884_x2004 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1884_1_wt_vector).rdPort(x1884_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1884_x2004))
      Stage(stage(1), operands=List(x2002(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr284)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr284), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1884_x2004.writeAddr, CU.temp(stage(2), tr285)))
      stage = stage0 +: RAStages(2, List(x1884_x2004))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2002(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr287)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr287), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1884_x2004.readAddr, CU.temp(stage(2), tr288)))
    }
    val x2012 = MetaPipeline(name = "x2012", parent=x2030) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1838_argin), Const("20i").out) // Counter
      val x1887 = CounterChain(name = "x1887", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1888_dsp0 = MemoryPipeline(name = "x1888_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1957 = CounterChain.copy("x1999", "x1957")
      val x1888_x1963 = SemiFIFO(size = 20, banking = Duplicated()).wtPort(x1913_mc.data).rdPort(x1888_0_rd_vector).rdAddr(x1957(0))
      var stage: List[Stage] = Nil
    }
    val x1889_dsp0 = MemoryPipeline(name = "x1889_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr295 = CU.temp
      val tr296 = CU.temp
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1957 = CounterChain.copy("x1999", "x1957")
      val x1889_x1962 = SemiFIFO(size = 1280, banking = Strided(1)).wtPort(x1939_mc.data).rdPort(x1889_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1889_x1962))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1957(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr295)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr295), CU.ctr(stage(1), x1961(0))), op=FixAdd, results=List(x1889_x1962.readAddr, CU.temp(stage(2), tr296)))
    }
    val x1930 = StreamController(name = "x1930", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1930_unitcc = CounterChain(name = "x1930_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1909 = StreamPipeline(name = "x1909", parent=x1930) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr299 = CU.temp
      val tr301 = CU.temp
      val tr304 = CU.temp
      val tr306 = CU.temp
      val tr308 = CU.temp
      val tr309 = CU.temp
      val tr310 = CU.temp
      val tr311 = CU.temp
      val x1887 = CounterChain.copy("x2012", "x1887")
      val x1909_unitcc = CounterChain(name = "x1909_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1887(0)), Const("16i")), op=FixMod, results=List(CU.scalarOut(stage(1), x1892_scalar), CU.temp(stage(1), tr299)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1887(0)), CU.temp(stage(1), tr299)), op=FixSub, results=List(CU.scalarOut(stage(2), x1913_mc.ofs), CU.temp(stage(2), tr301)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr299), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr304)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr304), Const("16i")), op=FixMod, results=List(CU.temp(stage(4), tr306)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr306), Const("0i")), op=FixNeq, results=List(CU.temp(stage(5), tr308)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr308), Const("16i"), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr309)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr304), CU.temp(stage(6), tr306)), op=FixSub, results=List(CU.temp(stage(7), tr310)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr310), CU.temp(stage(7), tr309)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1913_mc.len), CU.scalarOut(stage(8), x1895_scalar), CU.temp(stage(8), tr311)))
    }
    val x1929 = StreamPipeline(name = "x1929", parent=x1930) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr320 = CU.temp
      val ctr4 = (Const("0i").out, CU.scalarIn(stage0, x1895_scalar), Const("1i").out) // Counter
      val x1916 = CounterChain(name = "x1916", ctr4)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1916(0)), CU.scalarIn(stage(0), x1892_scalar)), op=FixSub, results=List(CU.temp(stage(1), tr320)))
    }
    val x1947 = StreamController(name = "x1947", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x1933 = CounterChain(name = "x1933", ctr5)
      var stage: List[Stage] = Nil
    }
    val x1936 = StreamPipeline(name = "x1936", parent=x1947) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr322 = CU.temp
      val tr324 = CU.temp
      val x1887 = CounterChain.copy("x2012", "x1887")
      val x1933 = CounterChain.copy("x1947", "x1933")
      val x1936_unitcc = CounterChain(name = "x1936_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1887(0)), CU.ctr(stage(0), x1933(0))), op=FixAdd, results=List(CU.temp(stage(1), tr322)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr322), Const("64i")), op=FixMul, results=List(CU.scalarOut(stage(2), x1939_mc.ofs), CU.temp(stage(2), tr324)))
      Stage(stage(3), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(3), x1939_mc.len)))
    }
    val x1952 = UnitPipeline(name = "x1952", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr332 = CU.temp
      val tr334 = CU.temp
      val x1887 = CounterChain.copy("x2012", "x1887")
      val x1952_unitcc = CounterChain(name = "x1952_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1838_argin), CU.ctr(stage(0), x1887(0))), op=FixSub, results=List(CU.temp(stage(1), tr332)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr332), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x1890_scalar), CU.temp(stage(2), tr334)))
    }
    val x1954_dsp0 = MemoryPipeline(name = "x1954_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr337 = CU.temp
      val tr338 = CU.temp
      val tr340 = CU.temp
      val tr341 = CU.temp
      val x1989 = CounterChain.copy("x1998", "x1989")
      val x1957 = CounterChain.copy("x1999", "x1957")
      val x2002 = CounterChain.copy("x2011", "x2002")
      val x1954_x2003 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1954_0_wt_vector).rdPort(x1954_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1954_x2003))
      Stage(stage(1), operands=List(x1989(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr337)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr337), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1954_x2003.writeAddr, CU.temp(stage(2), tr338)))
      stage = stage0 +: RAStages(2, List(x1954_x2003))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2002(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr340)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr340), CU.ctr(stage(1), x2002(1))), op=FixAdd, results=List(x1954_x2003.readAddr, CU.temp(stage(2), tr341)))
    }
    val x1954_dsp1 = MemoryPipeline(name = "x1954_dsp1") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr344 = CU.temp
      val tr345 = CU.temp
      val tr347 = CU.temp
      val tr348 = CU.temp
      val x1989 = CounterChain.copy("x1998", "x1989")
      val x1954_x1991 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1954_1_wt_vector).rdPort(x1954_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1954_x1991))
      Stage(stage(1), operands=List(x1989(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr344)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr344), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1954_x1991.writeAddr, CU.temp(stage(2), tr345)))
      stage = stage0 +: RAStages(2, List(x1954_x1991))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1989(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr347)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr347), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1954_x1991.readAddr, CU.temp(stage(2), tr348)))
    }
    val x1999 = MetaPipeline(name = "x1999", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x1890_scalar), Const("1i").out) // Counter
      val x1957 = CounterChain(name = "x1957", ctr7)
      var stage: List[Stage] = Nil
    }
    val x1958_dsp0 = MemoryPipeline(name = "x1958_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1978 = CounterChain.copy("x1986", "x1978")
      val x1958_x1980 = SRAM(size = 64, banking = Strided(1)).wtPort(x1958_0_wt_vector).rdPort(x1958_0_rd_vector).rdAddr(x1978(1)).wtAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1958_dsp1 = MemoryPipeline(name = "x1958_dsp1") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1961 = CounterChain.copy("x1975", "x1961")
      val x1978 = CounterChain.copy("x1986", "x1978")
      val x1958_x1979 = SRAM(size = 64, banking = Strided(1)).wtPort(x1958_1_wt_vector).rdPort(x1958_1_rd_vector).rdAddr(x1978(0)).wtAddr(x1961(0))
      var stage: List[Stage] = Nil
    }
    val x1959_dsp0 = MemoryPipeline(name = "x1959_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr356 = CU.temp
      val tr357 = CU.temp
      val tr359 = CU.temp
      val tr360 = CU.temp
      val x1989 = CounterChain.copy("x1998", "x1989")
      val x1978 = CounterChain.copy("x1986", "x1978")
      val x1959_x1990 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1959_0_wt_vector).rdPort(x1959_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1959_x1990))
      Stage(stage(1), operands=List(x1978(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr356)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr356), CU.ctr(stage(1), x1978(1))), op=FixAdd, results=List(x1959_x1990.writeAddr, CU.temp(stage(2), tr357)))
      stage = stage0 +: RAStages(2, List(x1959_x1990))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1989(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr359)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr359), CU.ctr(stage(1), x1989(1))), op=FixAdd, results=List(x1959_x1990.readAddr, CU.temp(stage(2), tr360)))
    }
    val x1975 = Pipeline(name = "x1975", parent=x1999) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr368 = CU.temp
      val tr369 = CU.temp
      val tr370 = CU.temp
      val ctr8 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1961 = CounterChain(name = "x1961", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1888_0_rd_vector), Const("1i")), op=FixEql, results=List(CU.temp(stage(1), tr368)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr368), CU.vecIn(stage(1), x1852_0_rd_vector), CU.vecIn(stage(1), x1851_0_rd_vector)), op=Mux, results=List(CU.temp(stage(2), tr369)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x1889_0_rd_vector), CU.temp(stage(2), tr369)), op=FixSub, results=List(CU.vecOut(stage(3), x1958_1_wt_vector), CU.vecOut(stage(3), x1958_0_wt_vector), CU.temp(stage(3), tr370)))
    }
    val x1986 = Pipeline(name = "x1986", parent=x1999) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr378 = CU.temp
      val ctr9 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1978 = CounterChain(name = "x1978", ctr9, ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1958_1_rd_vector), CU.vecIn(stage(0), x1958_0_rd_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x1959_0_wt_vector), CU.temp(stage(1), tr378)))
    }
    val x1998 = Pipeline(name = "x1998", parent=x1999) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr384 = CU.temp
      val tr385 = CU.temp
      val tr386 = CU.temp
      val x1957 = CounterChain.copy("x1999", "x1957")
      val ctr11 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1989 = CounterChain(name = "x1989", ctr11, ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1957(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr384)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1959_0_rd_vector), CU.vecIn(stage(1), x1954_1_rd_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr385)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr384), CU.vecIn(stage(2), x1959_0_rd_vector), CU.temp(stage(2), tr385)), op=Mux, results=List(CU.vecOut(stage(3), x1954_1_wt_vector), CU.vecOut(stage(3), x1954_0_wt_vector), CU.temp(stage(3), tr386)))
    }
    val x2011 = Pipeline(name = "x2011", parent=x2012) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr392 = CU.temp
      val tr393 = CU.temp
      val tr394 = CU.temp
      val x1887 = CounterChain.copy("x2012", "x1887")
      val ctr13 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr14 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2002 = CounterChain(name = "x2002", ctr13, ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1887(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr392)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1954_0_rd_vector), CU.vecIn(stage(1), x1884_1_rd_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr393)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr392), CU.vecIn(stage(2), x1954_0_rd_vector), CU.temp(stage(2), tr393)), op=Mux, results=List(CU.vecOut(stage(3), x1884_1_wt_vector), CU.vecOut(stage(3), x1884_0_wt_vector), CU.temp(stage(3), tr394)))
    }
    val x2029 = StreamController(name = "x2029", parent=x2030) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2015 = CounterChain(name = "x2015", ctr15)
      var stage: List[Stage] = Nil
    }
    val x2018 = StreamPipeline(name = "x2018", parent=x2029) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr398 = CU.temp
      val x2015 = CounterChain.copy("x2029", "x2015")
      val x2018_unitcc = CounterChain(name = "x2018_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2015(0)), Const("64i")), op=FixMul, results=List(CU.scalarOut(stage(1), x2028_mc.ofs), CU.temp(stage(1), tr398)))
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
