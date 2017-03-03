import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object LogReg extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2009_2_wt_vector = Vector("x2009_2_wt")
    val x2009_1_rd_vector = Vector("x2009_1_rd")
    val x2009_1_wt_vector = Vector("x2009_1_wt")
    val x2019_0_rd_vector = Vector("x2019_0_rd")
    val x2003_oc = OffChip("x2003")
    val x2009_2_rd_vector = Vector("x2009_2_rd")
    val x2000_oc = OffChip("x2000")
    val x2009_0_wt_vector = Vector("x2009_0_wt")
    val x2015_1_wt_vector = Vector("x2015_1_wt")
    val x2002_oc = OffChip("x2002")
    val x2015_1_rd_vector = Vector("x2015_1_rd")
    val x2019_1_rd_vector = Vector("x2019_1_rd")
    val x1996_argin = ArgIn("x1996")
    val x2015_0_rd_vector = Vector("x2015_0_rd")
    val x2015_0_wt_vector = Vector("x2015_0_wt")
    val x2082_0_wt_vector = Vector("x2082_0_wt")
    val x1995_argin = ArgIn("x1995")
    val x2020_0_rd_vector = Vector("x2020_0_rd")
    val x2083_scalar = Scalar("x2083")
    val x2081_scalar = Scalar("x2081")
    val x2082_0_rd_vector = Vector("x2082_0_rd")
    val x2009_0_rd_vector = Vector("x2009_0_rd")
    val x2153_mc = MemoryController(TileStore, x2003_oc).parent("x2154")
    val x2029_mc = MemoryController(TileLoad, x2000_oc).parent("x2037")
    val x2060_mc = MemoryController(TileLoad, x2002_oc).parent("x2077")
    val x2155 = Sequential(name = "x2155", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2155_unitcc = CounterChain(name = "x2155_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2009_dsp0 = MemoryPipeline(name = "x2009_dsp0", parent="x2138") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2127 = CounterChain.copy("x2137", "x2127")
      val x2145 = CounterChain.copy("x2150", "x2145")
      val x2009_x2146 = SRAM(size = 192, banking = Strided(1)).wtPort(x2009_0_wt_vector).rdPort(x2009_0_rd_vector).rdAddr(x2145(0)).wtAddr(x2127(0))
      var stage: List[Stage] = Nil
    }
    val x2009_dsp1 = MemoryPipeline(name = "x2009_dsp1", parent="x2138") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2127 = CounterChain.copy("x2137", "x2127")
      val x2009_x2129 = SRAM(size = 192, banking = Strided(1)).wtPort(x2009_1_wt_vector).rdPort(x2009_1_rd_vector).rdAddr(x2127(0)).wtAddr(x2127(0))
      var stage: List[Stage] = Nil
    }
    val x2009_dsp2 = MemoryPipeline(name = "x2009_dsp2", parent="x2138") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2127 = CounterChain.copy("x2137", "x2127")
      val x2085 = CounterChain.copy("x2094", "x2085")
      val x2009_x2087 = SRAM(size = 192, banking = Strided(1)).wtPort(x2009_2_wt_vector).rdPort(x2009_2_rd_vector).rdAddr(x2085(0)).wtAddr(x2127(0))
      var stage: List[Stage] = Nil
    }
    val x2139 = Sequential(name = "x2139", parent=x2155) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1995_argin), Const("1i").out) // Counter
      val x2012 = CounterChain(name = "x2012", ctr1)
      var stage: List[Stage] = Nil
    }
    val x2138 = Sequential(name = "x2138", parent=x2139) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x2014 = CounterChain(name = "x2014", ctr2)
      var stage: List[Stage] = Nil
    }
    val x2015_dsp0 = MemoryPipeline(name = "x2015_dsp0", parent="x2124") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2114 = CounterChain.copy("x2123", "x2114")
      val x2127 = CounterChain.copy("x2137", "x2127")
      val x2015_x2128 = SRAM(size = 192, banking = Strided(1)).wtPort(x2015_0_wt_vector).rdPort(x2015_0_rd_vector).rdAddr(x2127(0)).wtAddr(x2114(0))
      var stage: List[Stage] = Nil
    }
    val x2015_dsp1 = MemoryPipeline(name = "x2015_dsp1", parent="x2124") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2114 = CounterChain.copy("x2123", "x2114")
      val x2015_x2116 = SRAM(size = 192, banking = Strided(1)).wtPort(x2015_1_wt_vector).rdPort(x2015_1_rd_vector).rdAddr(x2114(0)).wtAddr(x2114(0))
      var stage: List[Stage] = Nil
    }
    val x2125 = MetaPipeline(name = "x2125", parent=x2138) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x1996_argin), Const("40i").out) // Counter
      val x2018 = CounterChain(name = "x2018", ctr3)
      var stage: List[Stage] = Nil
    }
    val x2019_dsp0 = MemoryPipeline(name = "x2019_dsp0", parent="x2125") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr97 = CU.temp
      val tr100 = CU.temp
      val x2080 = CounterChain.copy("x2124", "x2080")
      val x2105 = CounterChain.copy("x2112", "x2105")
      val x2019_x2106 = SemiFIFO(size = 7680, banking = Strided(1)).wtPort(x2029_mc.data).rdPort(x2019_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x2019_x2106))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2080(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr97)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr97), CU.ctr(stage(1), x2105(0))), op=FixAdd, results=List(x2019_x2106.readAddr, CU.temp(stage(2), tr100)))
    }
    val x2019_dsp1 = MemoryPipeline(name = "x2019_dsp1", parent="x2125") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr105 = CU.temp
      val tr108 = CU.temp
      val x2080 = CounterChain.copy("x2124", "x2080")
      val x2085 = CounterChain.copy("x2094", "x2085")
      val x2019_x2086 = SemiFIFO(size = 7680, banking = Strided(1)).wtPort(x2029_mc.data).rdPort(x2019_1_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x2019_x2086))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2080(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr105)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr105), CU.ctr(stage(1), x2085(0))), op=FixAdd, results=List(x2019_x2086.readAddr, CU.temp(stage(2), tr108)))
    }
    val x2020_dsp0 = MemoryPipeline(name = "x2020_dsp0", parent="x2125") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2080 = CounterChain.copy("x2124", "x2080")
      val x2020_x2095 = SemiFIFO(size = 40, banking = Duplicated()).wtPort(x2060_mc.data).rdPort(x2020_0_rd_vector).rdAddr(x2080(0))
      var stage: List[Stage] = Nil
    }
    val x2037 = StreamController(name = "x2037", parent=x2125) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("40i").out, Const("1i").out) // Counter
      val x2023 = CounterChain(name = "x2023", ctr5)
      var stage: List[Stage] = Nil
    }
    val x2026 = StreamPipeline(name = "x2026", parent=x2037) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr119 = CU.temp
      val tr121 = CU.temp
      val x2026_unitcc = CounterChain(name = "x2026_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2018 = CounterChain.copy("x2125", "x2018")
      val x2023 = CounterChain.copy("x2037", "x2023")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2018(0)), CU.ctr(stage(0), x2023(0))), op=FixAdd, results=List(CU.temp(stage(1), tr119)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr119), Const("192i")), op=FixMul, results=List(CU.scalarOut(stage(2), x2029_mc.ofs), CU.temp(stage(2), tr121)))
      Stage(stage(3), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(3), x2029_mc.len)))
    }
    val x2077 = StreamController(name = "x2077", parent=x2125) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2077_unitcc = CounterChain(name = "x2077_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2056 = StreamPipeline(name = "x2056", parent=x2077) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr142 = CU.temp
      val tr144 = CU.temp
      val tr147 = CU.temp
      val tr149 = CU.temp
      val tr151 = CU.temp
      val tr152 = CU.temp
      val tr153 = CU.temp
      val tr154 = CU.temp
      val x2056_unitcc = CounterChain(name = "x2056_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2018 = CounterChain.copy("x2125", "x2018")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2018(0)), Const("16i")), op=FixMod, results=List(CU.temp(stage(1), tr142)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2018(0)), CU.temp(stage(1), tr142)), op=FixSub, results=List(CU.scalarOut(stage(2), x2060_mc.ofs), CU.temp(stage(2), tr144)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr142), Const("40i")), op=FixAdd, results=List(CU.temp(stage(3), tr147)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr147), Const("16i")), op=FixMod, results=List(CU.temp(stage(4), tr149)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr149), Const("0i")), op=FixNeq, results=List(CU.temp(stage(5), tr151)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr151), Const("16i"), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr152)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr147), CU.temp(stage(6), tr149)), op=FixSub, results=List(CU.temp(stage(7), tr153)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr153), CU.temp(stage(7), tr152)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2060_mc.len), CU.temp(stage(8), tr154)))
    }
    val x2124 = MetaPipeline(name = "x2124", parent=x2125) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("40i").out, Const("1i").out) // Counter
      val x2080 = CounterChain(name = "x2080", ctr4)
      var stage: List[Stage] = Nil
    }
    val x2082_dsp0 = MemoryPipeline(name = "x2082_dsp0", parent="x2124") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2105 = CounterChain.copy("x2112", "x2105")
      val x2114 = CounterChain.copy("x2123", "x2114")
      val x2082_x2115 = SRAM(size = 192, banking = Strided(1)).wtPort(x2082_0_wt_vector).rdPort(x2082_0_rd_vector).rdAddr(x2114(0)).wtAddr(x2105(0))
      var stage: List[Stage] = Nil
    }
    val x2094 = Pipeline(name = "x2094", parent=x2124) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr179 = CU.temp
      val ctr8 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x2085 = CounterChain(name = "x2085", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2019_1_rd_vector), CU.vecIn(stage(0), x2009_2_rd_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr179)))
      val (rs1, rr182) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr182), op=Bypass, results=List(CU.scalarOut(stage(2), x2083_scalar)))
    }
    val x2103 = UnitPipeline(name = "x2103", parent=x2124) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr186 = CU.temp
      val tr187 = CU.temp
      val tr189 = CU.temp
      val tr190 = CU.temp
      val tr191 = CU.temp
      val x2103_unitcc = CounterChain(name = "x2103_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2083_scalar)), op=FltNeg, results=List(CU.temp(stage(1), tr186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr186)), op=FltExp, results=List(CU.temp(stage(2), tr187)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr187), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr189)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr189)), op=FltDiv, results=List(CU.temp(stage(4), tr190)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x2020_0_rd_vector), CU.temp(stage(4), tr190)), op=FltSub, results=List(CU.scalarOut(stage(5), x2081_scalar), CU.temp(stage(5), tr191)))
    }
    val x2112 = Pipeline(name = "x2112", parent=x2124) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr207 = CU.temp
      val ctr9 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x2105 = CounterChain(name = "x2105", ctr9)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2019_0_rd_vector), CU.scalarIn(stage(0), x2081_scalar)), op=FltSub, results=List(CU.vecOut(stage(1), x2082_0_wt_vector), CU.temp(stage(1), tr207)))
    }
    val x2123 = Pipeline(name = "x2123", parent=x2124) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr222 = CU.temp
      val tr223 = CU.temp
      val tr224 = CU.temp
      val x2080 = CounterChain.copy("x2124", "x2080")
      val ctr10 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x2114 = CounterChain(name = "x2114", ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2080(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr222)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x2082_0_rd_vector), CU.vecIn(stage(1), x2015_1_rd_vector)), op=FltAdd, results=List(CU.temp(stage(2), tr223)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr222), CU.vecIn(stage(2), x2082_0_rd_vector), CU.temp(stage(2), tr223)), op=Mux, results=List(CU.vecOut(stage(3), x2015_1_wt_vector), CU.vecOut(stage(3), x2015_0_wt_vector), CU.temp(stage(3), tr224)))
    }
    val x2137 = Pipeline(name = "x2137", parent=x2138) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr234 = CU.temp
      val tr236 = CU.temp
      val tr237 = CU.temp
      val tr238 = CU.temp
      val ctr11 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x2127 = CounterChain(name = "x2127", ctr11)
      val x2014 = CounterChain.copy("x2138", "x2014")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2014(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr234)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x2009_1_rd_vector), Const("1i")), op=FltMul, results=List(CU.temp(stage(2), tr236)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x2015_0_rd_vector), CU.temp(stage(2), tr236)), op=FltAdd, results=List(CU.temp(stage(3), tr237)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr234), CU.vecIn(stage(3), x2015_0_rd_vector), CU.temp(stage(3), tr237)), op=Mux, results=List(CU.vecOut(stage(4), x2009_2_wt_vector), CU.vecOut(stage(4), x2009_1_wt_vector), CU.vecOut(stage(4), x2009_0_wt_vector), CU.temp(stage(4), tr238)))
    }
    val x2154 = StreamController(name = "x2154", parent=x2155) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2154_unitcc = CounterChain(name = "x2154_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2143 = StreamPipeline(name = "x2143", parent=x2154) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2143_unitcc = CounterChain(name = "x2143_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x2153_mc.ofs)))
      Stage(stage(2), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(2), x2153_mc.len)))
    }
    val x2150 = StreamPipeline(name = "x2150", parent=x2154) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x2145 = CounterChain(name = "x2145", ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2009_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x2153_mc.data)))
    }
    
  }
}
