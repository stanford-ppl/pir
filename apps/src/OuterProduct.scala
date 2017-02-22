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
    val x965_0_wt_vector = Vector("x965_0_wt")
    val x943_argin = ArgIn("x943")
    val x963_0_rd_vector = Vector("x963_0_rd")
    val x965_0_rd_vector = Vector("x965_0_rd")
    val x944_argin = ArgIn("x944")
    val x964_0_rd_vector = Vector("x964_0_rd")
    val x953_oc = OffChip("x953")
    val x948_oc = OffChip("x948")
    val x950_oc = OffChip("x950")
    val x989_mc = MemoryController(TileLoad, x950_oc).parent("x997")
    val x973_mc = MemoryController(TileLoad, x948_oc).parent("x981")
    val x1029_mc = MemoryController(TileStore, x953_oc).parent("x1030")
    val x1032 = Sequential(name = "x1032", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1032_unitcc = CounterChain(name = "x1032_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1031 = MetaPipeline(name = "x1031", parent=x1032) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x943_argin), Const("64i").out) // Counter
      val ctr2 = (Const("0i").out, CU.scalarIn(stage0, x944_argin), Const("64i").out) // Counter
      val x962 = CounterChain(name = "x962", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x963_dsp0 = MemoryPipeline(name = "x963_dsp0", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1001 = CounterChain.copy("x1009", "x1001")
      val x963_x1002 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x973_mc.data).rdPort(x963_0_rd_vector).rdAddr(x1001(0))
      var stage: List[Stage] = Nil
    }
    val x964_dsp0 = MemoryPipeline(name = "x964_dsp0", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1001 = CounterChain.copy("x1009", "x1001")
      val x964_x1003 = SemiFIFO(size = 64, banking = Strided(1)).wtPort(x989_mc.data).rdPort(x964_0_rd_vector).rdAddr(x1001(1))
      var stage: List[Stage] = Nil
    }
    val x965_dsp0 = MemoryPipeline(name = "x965_dsp0", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr151 = CU.temp
      val tr154 = CU.temp
      val x1001 = CounterChain.copy("x1009", "x1001")
      val x1020 = CounterChain.copy("x1025", "x1020")
      val x1012 = CounterChain.copy("x1030", "x1012")
      val x965_x1021 = SRAM(size = 4096, banking = Strided(1)).wtPort(x965_0_wt_vector).rdPort(x965_0_rd_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x965_x1021))
      Stage(stage(1), operands=List(x1001(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr151)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr151), CU.ctr(stage(1), x1001(1))), op=FixAdd, results=List(x965_x1021.writeAddr))
      stage = stage0 +: RAStages(2, List(x965_x1021))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1012(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr154)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr154), CU.ctr(stage(1), x1020(0))), op=FixAdd, results=List(x965_x1021.readAddr))
    }
    val x981 = StreamController(name = "x981", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val x981_unitcc = CounterChain(name = "x981_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x969 = StreamPipeline(name = "x969", parent=x981) { implicit CU => 
      val stage0 = CU.emptyStage
      val x962 = CounterChain.copy("x1031", "x962")
      val x969_unitcc = CounterChain(name = "x969_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x962(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x973_mc.ofs)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x973_mc.len)))
    }
    val x997 = StreamController(name = "x997", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val x997_unitcc = CounterChain(name = "x997_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x985 = StreamPipeline(name = "x985", parent=x997) { implicit CU => 
      val stage0 = CU.emptyStage
      val x962 = CounterChain.copy("x1031", "x962")
      val x985_unitcc = CounterChain(name = "x985_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x962(1))), op=Bypass, results=List(CU.scalarOut(stage(1), x989_mc.ofs)))
      Stage(stage(2), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(2), x989_mc.len)))
    }
    val x1009 = Pipeline(name = "x1009", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr176 = CU.temp
      val ctr5 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1001 = CounterChain(name = "x1001", ctr5, ctr6)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x963_0_rd_vector), CU.vecIn(stage(0), x964_0_rd_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x965_0_wt_vector), CU.temp(stage(1), tr176)))
    }
    val x1030 = StreamController(name = "x1030", parent=x1031) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1012 = CounterChain(name = "x1012", ctr7)
      var stage: List[Stage] = Nil
    }
    val x1018 = StreamPipeline(name = "x1018", parent=x1030) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr179 = CU.temp
      val tr180 = CU.temp
      val tr181 = CU.temp
      val x962 = CounterChain.copy("x1031", "x962")
      val x1012 = CounterChain.copy("x1030", "x1012")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x962(0)), CU.ctr(stage(0), x1012(0))), op=FixAdd, results=List(CU.temp(stage(1), tr179)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr179), CU.scalarIn(stage(1), x944_argin)), op=FixMul, results=List(CU.temp(stage(2), tr180)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr180), CU.ctr(stage(2), x962(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1029_mc.ofs), CU.temp(stage(3), tr181)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1029_mc.len)))
    }
    val x1025 = StreamPipeline(name = "x1025", parent=x1030) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1020 = CounterChain(name = "x1020", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x965_0_rd_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1029_mc.data)))
    }
    
  }
}
