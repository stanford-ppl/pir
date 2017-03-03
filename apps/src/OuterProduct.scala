import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x892_oc = OffChip("x892")
    val x905_0_rd_vector = Vector("x905_0_rd")
    val x886_argin = ArgIn("x886")
    val x885_argin = ArgIn("x885")
    val x890_oc = OffChip("x890")
    val x903_0_rd_vector = Vector("x903_0_rd")
    val x905_0_wt_vector = Vector("x905_0_wt")
    val x895_oc = OffChip("x895")
    val x904_0_rd_vector = Vector("x904_0_rd")
    val x927_mc = MemoryController(TileLoad, x892_oc).parent("x935")
    val x966_mc = MemoryController(TileStore, x895_oc).parent("x967")
    val x912_mc = MemoryController(TileLoad, x890_oc).parent("x920")
    val x969 = Sequential(name = "x969", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x969_unitcc = CounterChain(name = "x969_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x968 = MetaPipeline(name = "x968", parent=x969) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x885_argin), Const("64i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x886_argin), Const("64i").out) // Counter
      val x902 = CounterChain(name = "x902", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x903_dsp0 = MemoryPipeline(name = "x903_dsp0", parent="x968") { implicit CU => 
      val stage0 = CU.emptyStage
      val x939 = CounterChain.copy("x947", "x939")
      val x903_x940 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x912_mc.data).rdPort(x903_0_rd_vector).rdAddr(x939(0))
      var stage: List[Stage] = Nil
    }
    val x904_dsp0 = MemoryPipeline(name = "x904_dsp0", parent="x968") { implicit CU => 
      val stage0 = CU.emptyStage
      val x939 = CounterChain.copy("x947", "x939")
      val x904_x941 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x927_mc.data).rdPort(x904_0_rd_vector).rdAddr(x939(1))
      var stage: List[Stage] = Nil
    }
    val x905_dsp0 = MemoryPipeline(name = "x905_dsp0", parent="x968") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr61 = CU.temp
      val tr62 = CU.temp
      val tr66 = CU.temp
      val tr69 = CU.temp
      val x939 = CounterChain.copy("x947", "x939")
      val x950 = CounterChain.copy("x967", "x950")
      val x958 = CounterChain.copy("x963", "x958")
      val x905_x959 = SRAM(size = 4096, banking = Strided(1)).wtPort(x905_0_wt_vector).rdPort(x905_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x905_x959))
      Stage(stage(1), operands=List(x939(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr61)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr61), CU.ctr(stage(1), x939(1))), op=FixAdd, results=List(x905_x959.writeAddr, CU.temp(stage(2), tr62)))
      stage = stage0 +: RAStages(2, List(x905_x959))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x950(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr66)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr66), CU.ctr(stage(1), x958(0))), op=FixAdd, results=List(x905_x959.readAddr, CU.temp(stage(2), tr69)))
    }
    val x920 = StreamController(name = "x920", parent=x968) { implicit CU => 
      val stage0 = CU.emptyStage
      val x920_unitcc = CounterChain(name = "x920_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x909 = StreamPipeline(name = "x909", parent=x920) { implicit CU => 
      val stage0 = CU.emptyStage
      val x909_unitcc = CounterChain(name = "x909_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x902 = CounterChain.copy("x968", "x902")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x902(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x912_mc.ofs)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x912_mc.len)))
    }
    val x935 = StreamController(name = "x935", parent=x968) { implicit CU => 
      val stage0 = CU.emptyStage
      val x935_unitcc = CounterChain(name = "x935_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x924 = StreamPipeline(name = "x924", parent=x935) { implicit CU => 
      val stage0 = CU.emptyStage
      val x924_unitcc = CounterChain(name = "x924_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x902 = CounterChain.copy("x968", "x902")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x902(1))), op=Bypass, results=List(CU.scalarOut(stage(1), x927_mc.ofs)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x927_mc.len)))
    }
    val x947 = Pipeline(name = "x947", parent=x968) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr110 = CU.temp
      val ctr5 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x939 = CounterChain(name = "x939", ctr5, ctr6)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x903_0_rd_vector), CU.vecIn(stage(0), x904_0_rd_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x905_0_wt_vector), CU.temp(stage(1), tr110)))
    }
    val x967 = StreamController(name = "x967", parent=x968) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x950 = CounterChain(name = "x950", ctr7)
      var stage: List[Stage] = Nil
    }
    val x956 = StreamPipeline(name = "x956", parent=x967) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr119 = CU.temp
      val tr120 = CU.temp
      val tr121 = CU.temp
      val x956_unitcc = CounterChain(name = "x956_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x902 = CounterChain.copy("x968", "x902")
      val x950 = CounterChain.copy("x967", "x950")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x902(0)), CU.ctr(stage(0), x950(0))), op=FixAdd, results=List(CU.temp(stage(1), tr119)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr119), CU.scalarIn(stage(1), x886_argin)), op=FixMul, results=List(CU.temp(stage(2), tr120)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr120), CU.ctr(stage(2), x902(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x966_mc.ofs), CU.temp(stage(3), tr121)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x966_mc.len)))
    }
    val x963 = StreamPipeline(name = "x963", parent=x967) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x958 = CounterChain(name = "x958", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x905_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x966_mc.data)))
    }
    
  }
}
