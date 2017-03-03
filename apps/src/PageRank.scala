import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object PageRank extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2632_1_wt_vector = Vector("x2632_1_wt")
    val x2615_oc = OffChip("x2615")
    val x2634_0_rd_vector = Vector("x2634_0_rd")
    val x2782_1_rd_vector = Vector("x2782_1_rd")
    val x2688_1_rd_vector = Vector("x2688_1_rd")
    val x2689_0_rd_vector = Vector("x2689_0_rd")
    val x2614_oc = OffChip("x2614")
    val x2609_argin = ArgIn("x2609")
    val x2632_1_rd_vector = Vector("x2632_1_rd")
    val x2635_0_rd_vector = Vector("x2635_0_rd")
    val x2632_0_rd_vector = Vector("x2632_0_rd")
    val x2607_argin = ArgIn("x2607")
    val x2688_0_rd_vector = Vector("x2688_0_rd")
    val x2782_0_rd_vector = Vector("x2782_0_rd")
    val x2690_scalar = Scalar("x2690")
    val x2800_0_wt_vector = Vector("x2800_0_wt")
    val x2782_0_wt_vector = Vector("x2782_0_wt")
    val x2813_scalar = Scalar("x2813")
    val x2616_oc = OffChip("x2616")
    val x2800_0_rd_vector = Vector("x2800_0_rd")
    val x2782_1_wt_vector = Vector("x2782_1_wt")
    val x2620_oc = OffChip("x2620")
    val x2633_0_rd_vector = Vector("x2633_0_rd")
    val x2684_scalar = Scalar("x2684")
    val x2608_argin = ArgIn("x2608")
    val x2632_0_wt_vector = Vector("x2632_0_wt")
    val x2688_2_rd_vector = Vector("x2688_2_rd")
    val x2809_0_rd_vector = Vector("x2809_0_rd")
    val x2618_oc = OffChip("x2618")
    val x2720_mc = MemoryController(TileLoad, x2615_oc).parent("x2737")
    val x2672_mc = MemoryController(TileLoad, x2620_oc).parent("x2680")
    val x2860_mc = MemoryController(TileStore, x2614_oc).parent("x2861")
    val x2763_mc = MemoryController(TileLoad, x2616_oc).parent("x2780")
    val x2657_mc = MemoryController(TileLoad, x2618_oc).parent("x2665")
    val x2642_mc = MemoryController(TileLoad, x2614_oc).parent("x2650")
    val x2811_mc = MemoryController(Gather, x2614_oc).parent("x2846")
    val x2864 = Sequential(name = "x2864", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2864_unitcc = CounterChain(name = "x2864_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2863 = Sequential(name = "x2863", parent=x2864) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x2607_argin), Const("1i").out) // Counter
      val x2628 = CounterChain(name = "x2628", ctr1)
      var stage: List[Stage] = Nil
    }
    val x2862 = Sequential(name = "x2862", parent=x2863) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x2608_argin), Const("768i").out) // Counter
      val x2631 = CounterChain(name = "x2631", ctr2)
      var stage: List[Stage] = Nil
    }
    val x2632_dsp0 = MemoryPipeline(name = "x2632_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2683 = CounterChain.copy("x2846", "x2683")
      val x2852 = CounterChain.copy("x2857", "x2852")
      val x2632_x2853 = SRAM(size = 768, banking = Strided(1)).wtPort(x2632_0_wt_vector).rdPort(x2632_0_rd_vector).rdAddr(x2852(0)).wtAddr(x2683(0))
      var stage: List[Stage] = Nil
    }
    val x2632_dsp1 = MemoryPipeline(name = "x2632_dsp1") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2683 = CounterChain.copy("x2846", "x2683")
      val x2632_x2820 = SRAM(size = 768, banking = Strided(1)).wtPort(x2632_1_wt_vector).rdPort(x2632_1_rd_vector).rdAddr(x2683(0)).wtAddr(x2683(0))
      var stage: List[Stage] = Nil
    }
    val x2633_dsp0 = MemoryPipeline(name = "x2633_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2683 = CounterChain.copy("x2846", "x2683")
      val x2633_x2819 = SemiFIFO(size = 768, banking = Strided(1)).wtPort(x2642_mc.data).rdPort(x2633_0_rd_vector).rdAddr(x2683(0))
      var stage: List[Stage] = Nil
    }
    val x2634_dsp0 = MemoryPipeline(name = "x2634_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2683 = CounterChain.copy("x2846", "x2683")
      val x2634_x2691 = SemiFIFO(size = 768, banking = Strided(1)).wtPort(x2657_mc.data).rdPort(x2634_0_rd_vector).rdAddr(x2683(0))
      var stage: List[Stage] = Nil
    }
    val x2635_dsp0 = MemoryPipeline(name = "x2635_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2683 = CounterChain.copy("x2846", "x2683")
      val x2635_x2685 = SemiFIFO(size = 768, banking = Strided(1)).wtPort(x2672_mc.data).rdPort(x2635_0_rd_vector).rdAddr(x2683(0))
      var stage: List[Stage] = Nil
    }
    val x2650 = StreamController(name = "x2650", parent=x2862) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2650_unitcc = CounterChain(name = "x2650_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2639 = StreamPipeline(name = "x2639", parent=x2650) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2639_unitcc = CounterChain(name = "x2639_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2631 = CounterChain.copy("x2862", "x2631")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2631(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x2642_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x2642_mc.len)))
    }
    val x2665 = StreamController(name = "x2665", parent=x2862) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2665_unitcc = CounterChain(name = "x2665_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2654 = StreamPipeline(name = "x2654", parent=x2665) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2654_unitcc = CounterChain(name = "x2654_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2631 = CounterChain.copy("x2862", "x2631")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2631(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x2657_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x2657_mc.len)))
    }
    val x2680 = StreamController(name = "x2680", parent=x2862) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2680_unitcc = CounterChain(name = "x2680_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2669 = StreamPipeline(name = "x2669", parent=x2680) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2669_unitcc = CounterChain(name = "x2669_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2631 = CounterChain.copy("x2862", "x2631")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2631(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x2672_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x2672_mc.len)))
    }
    val x2846 = StreamController(name = "x2846", parent=x2862) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x2683 = CounterChain(name = "x2683", ctr6)
      var stage: List[Stage] = Nil
    }
    val x2687 = StreamPipeline(name = "x2687", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2687_unitcc = CounterChain(name = "x2687_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2635_0_rd_vector)), op=Bypass, results=List(CU.scalarOut(stage(1), x2684_scalar)))
    }
    val x2688_dsp0 = MemoryPipeline(name = "x2688_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2816 = CounterChain.copy("x2838", "x2816")
      val x2688_x2817 = SemiFIFO(size = 768, banking = Duplicated()).wtPort(x2720_mc.data).rdPort(x2688_0_rd_vector).rdAddr(x2816(0))
      var stage: List[Stage] = Nil
    }
    val x2688_dsp1 = MemoryPipeline(name = "x2688_dsp1") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2803 = CounterChain.copy("x2808", "x2803")
      val x2688_x2805 = SemiFIFO(size = 768, banking = Duplicated()).wtPort(x2720_mc.data).rdPort(x2688_1_rd_vector).rdAddr(x2803(0))
      var stage: List[Stage] = Nil
    }
    val x2688_dsp2 = MemoryPipeline(name = "x2688_dsp2") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2786 = CounterChain.copy("x2799", "x2786")
      val x2688_x2787 = SemiFIFO(size = 768, banking = Duplicated()).wtPort(x2720_mc.data).rdPort(x2688_2_rd_vector).rdAddr(x2786(0))
      var stage: List[Stage] = Nil
    }
    val x2689_dsp0 = MemoryPipeline(name = "x2689_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2816 = CounterChain.copy("x2838", "x2816")
      val x2689_x2823 = SemiFIFO(size = 768, banking = Duplicated()).wtPort(x2763_mc.data).rdPort(x2689_0_rd_vector).rdAddr(x2816(0))
      var stage: List[Stage] = Nil
    }
    val x2694 = StreamPipeline(name = "x2694", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2694_unitcc = CounterChain(name = "x2694_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2634_0_rd_vector)), op=Bypass, results=List(CU.scalarOut(stage(1), x2690_scalar)))
    }
    val x2737 = StreamController(name = "x2737", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2737_unitcc = CounterChain(name = "x2737_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2716 = StreamPipeline(name = "x2716", parent=x2737) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr167 = CU.temp
      val tr169 = CU.temp
      val tr171 = CU.temp
      val tr173 = CU.temp
      val tr175 = CU.temp
      val tr176 = CU.temp
      val tr177 = CU.temp
      val tr178 = CU.temp
      val x2716_unitcc = CounterChain(name = "x2716_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2690_scalar), Const("16i")), op=FixMod, results=List(CU.temp(stage(1), tr167)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x2690_scalar), CU.temp(stage(1), tr167)), op=FixSub, results=List(CU.scalarOut(stage(2), x2720_mc.ofs), CU.temp(stage(2), tr169)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr167), CU.scalarIn(stage(2), x2684_scalar)), op=FixAdd, results=List(CU.temp(stage(3), tr171)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr171), Const("16i")), op=FixMod, results=List(CU.temp(stage(4), tr173)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr173), Const("0i")), op=FixNeq, results=List(CU.temp(stage(5), tr175)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr175), Const("16i"), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr176)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr171), CU.temp(stage(6), tr173)), op=FixSub, results=List(CU.temp(stage(7), tr177)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr177), CU.temp(stage(7), tr176)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2720_mc.len), CU.temp(stage(8), tr178)))
    }
    val x2780 = StreamController(name = "x2780", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2780_unitcc = CounterChain(name = "x2780_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2759 = StreamPipeline(name = "x2759", parent=x2780) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr200 = CU.temp
      val tr202 = CU.temp
      val tr204 = CU.temp
      val tr206 = CU.temp
      val tr208 = CU.temp
      val tr209 = CU.temp
      val tr210 = CU.temp
      val tr211 = CU.temp
      val x2759_unitcc = CounterChain(name = "x2759_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2690_scalar), Const("16i")), op=FixMod, results=List(CU.temp(stage(1), tr200)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x2690_scalar), CU.temp(stage(1), tr200)), op=FixSub, results=List(CU.scalarOut(stage(2), x2763_mc.ofs), CU.temp(stage(2), tr202)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr200), CU.scalarIn(stage(2), x2684_scalar)), op=FixAdd, results=List(CU.temp(stage(3), tr204)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr204), Const("16i")), op=FixMod, results=List(CU.temp(stage(4), tr206)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr206), Const("0i")), op=FixNeq, results=List(CU.temp(stage(5), tr208)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr208), Const("16i"), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr209)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr204), CU.temp(stage(6), tr206)), op=FixSub, results=List(CU.temp(stage(7), tr210)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr210), CU.temp(stage(7), tr209)), op=FixAdd, results=List(CU.scalarOut(stage(8), x2763_mc.len), CU.temp(stage(8), tr211)))
    }
    val x2782_dsp0 = MemoryPipeline(name = "x2782_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2786 = CounterChain.copy("x2799", "x2786")
      val x2816 = CounterChain.copy("x2838", "x2816")
      val x2782_x2818 = SRAM(size = 768, banking = Strided(1)).wtPort(x2782_0_wt_vector).rdPort(x2782_0_rd_vector).rdAddr(x2816(0)).wtAddr(x2786(0))
      var stage: List[Stage] = Nil
    }
    val x2782_dsp1 = MemoryPipeline(name = "x2782_dsp1") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2786 = CounterChain.copy("x2799", "x2786")
      val x2803 = CounterChain.copy("x2808", "x2803")
      val x2782_x2804 = SRAM(size = 768, banking = Strided(1)).wtPort(x2782_1_wt_vector).rdPort(x2782_1_rd_vector).rdAddr(x2803(0)).wtAddr(x2786(0))
      var stage: List[Stage] = Nil
    }
    val x2799 = StreamController(name = "x2799", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, CU.scalarIn(stage0, x2684_scalar), Const("1i").out) // Counter
      val x2786 = CounterChain(name = "x2786", ctr9)
      var stage: List[Stage] = Nil
    }
    val x2798 = UnitPipeline(name = "x2798", parent=x2799) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr246 = CU.temp
      val tr248 = CU.temp
      val tr249 = CU.temp
      val tr250 = CU.temp
      val tr253 = CU.temp
      val tr254 = CU.temp
      val tr256 = CU.temp
      val ar243 = CU.accum(init = Const("-1i"))
      val x2798_unitcc = CounterChain(name = "x2798_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2631 = CounterChain.copy("x2862", "x2631")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2631(0)), CU.vecIn(stage(0), x2688_2_rd_vector)), op=FixLeq, results=List(CU.temp(stage(1), tr246)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2631(0)), Const("768i")), op=FixAdd, results=List(CU.temp(stage(2), tr248)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x2688_2_rd_vector), CU.temp(stage(2), tr248)), op=FixLt, results=List(CU.temp(stage(3), tr249)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr246), CU.temp(stage(3), tr249)), op=BitAnd, results=List(CU.temp(stage(4), tr250)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr250), Const("0i"), Const("1i")), op=Mux, results=List(CU.temp(stage(5), tr253)))
      Stage(stage(6), operands=List(CU.accum(stage(6), ar243), CU.temp(stage(5), tr253)), op=FixAdd, results=List(CU.accum(stage(6), ar243), CU.temp(stage(6), tr254)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr250), CU.accum(stage(6), ar243), Const("-1i")), op=Mux, results=List(CU.vecOut(stage(7), x2782_1_wt_vector), CU.vecOut(stage(7), x2782_0_wt_vector), CU.temp(stage(7), tr256)))
    }
    val x2800_dsp0 = MemoryPipeline(name = "x2800_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2786 = CounterChain.copy("x2799", "x2786")
      val x2811_cc = CounterChain.copy("x2811","x2811_cc")
      val x2800_x2811 = SRAM(size = 768, banking = Strided(1)).wtPort(x2800_0_wt_vector).rdPort(x2800_0_rd_vector).rdAddr(x2811_cc(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x2800_x2811))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2782_1_rd_vector)), op=Bypass, results=List(x2800_x2811.writeAddr))
    }
    val x2808 = StreamController(name = "x2808", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, CU.scalarIn(stage0, x2684_scalar), Const("1i").out) // Counter
      val x2803 = CounterChain(name = "x2803", ctr10)
      var stage: List[Stage] = Nil
    }
    val x2807 = UnitPipeline(name = "x2807", parent=x2808) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2807_unitcc = CounterChain(name = "x2807_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2688_1_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x2800_0_wt_vector)))
    }
    val x2809_dsp0 = MemoryPipeline(name = "x2809_dsp0") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2809_x2822 = SemiFIFO(size = 768, banking = Duplicated()).wtPort(x2811_mc.data).rdPort(x2809_0_rd_vector)
      var stage: List[Stage] = Nil
    }
    val x2811 = StreamPipeline(name = "x2811", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x2811_cc = CounterChain(name = "x2811_cc", ctr11)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage0, x2800_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x2811_mc.addrs)))
    }
    val x2838 = StreamPipeline(name = "x2838", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr279 = CU.temp
      val tr282 = CU.temp
      val tr283 = CU.temp
      val tr285 = CU.temp
      val tr286 = CU.temp
      val tr287 = CU.temp
      val ctr12 = (Const("0i").out, CU.scalarIn(stage0, x2684_scalar), Const("1i").out) // Counter
      val x2816 = CounterChain(name = "x2816", ctr12)
      val x2631 = CounterChain.copy("x2862", "x2631")
      val x2683 = CounterChain.copy("x2846", "x2683")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2688_0_rd_vector), CU.ctr(stage(0), x2631(0))), op=FixSub, results=List(CU.temp(stage(1), tr279)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr279), CU.ctr(stage(1), x2683(0))), op=FixLeq, results=List(CU.temp(stage(2), tr282)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr282), CU.vecIn(stage(2), x2633_0_rd_vector), CU.vecIn(stage(2), x2632_1_rd_vector)), op=Mux, results=List(CU.temp(stage(3), tr283)))
      Stage(stage(4), operands=List(CU.vecIn(stage(3), x2782_0_rd_vector), Const("-1i")), op=FixEql, results=List(CU.temp(stage(4), tr285)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr285), CU.temp(stage(4), tr283), CU.vecIn(stage(4), x2809_0_rd_vector)), op=Mux, results=List(CU.temp(stage(5), tr286)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr286), CU.vecIn(stage(5), x2689_0_rd_vector)), op=FltDiv, results=List(CU.reduce(stage(6)), CU.temp(stage(6), tr287)))
      val (rs1, rr290) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(7), operands=List(rr290), op=Bypass, results=List(CU.scalarOut(stage(7), x2813_scalar)))
    }
    val x2845 = StreamPipeline(name = "x2845", parent=x2846) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr294 = CU.temp
      val tr296 = CU.temp
      val tr297 = CU.temp
      val x2845_unitcc = CounterChain(name = "x2845_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2813_scalar), CU.scalarIn(stage(0), x2609_argin)), op=FltMul, results=List(CU.temp(stage(1), tr294)))
      Stage(stage(2), operands=List(Const("1i"), CU.scalarIn(stage(1), x2609_argin)), op=FltSub, results=List(CU.temp(stage(2), tr296)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr294), CU.temp(stage(2), tr296)), op=FltAdd, results=List(CU.vecOut(stage(3), x2632_1_wt_vector), CU.vecOut(stage(3), x2632_0_wt_vector), CU.temp(stage(3), tr297)))
    }
    val x2861 = StreamController(name = "x2861", parent=x2862) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2861_unitcc = CounterChain(name = "x2861_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x2850 = StreamPipeline(name = "x2850", parent=x2861) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2850_unitcc = CounterChain(name = "x2850_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x2631 = CounterChain.copy("x2862", "x2631")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2631(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x2860_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x2860_mc.len)))
    }
    val x2857 = StreamPipeline(name = "x2857", parent=x2861) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x2852 = CounterChain(name = "x2852", ctr13)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2632_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x2860_mc.data)))
    }
    
  }
}
