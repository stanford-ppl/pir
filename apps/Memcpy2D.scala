import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object Memcpy2DDesign extends PIRApp {
  override val arch = P2P_2CU_2TT
  def main(args: String*)(top:Top) = {
    val x986_vector = Vector()
    val x831_oc = OffChip()
    val x830_oc = OffChip()
    val x1013_vector = Vector()
    val x1031_scalar = Scalar()
    val x990_scalar = Scalar()
    val x1039_mc_mc = MemoryController(TileStore, x831_oc)
    val x998_mc_mc = MemoryController(TileLoad, x830_oc)
    val x1045 = Sequential(name="x1045", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1045_unitCC = CounterChain(name = "x1045_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1043 = Sequential(name="x1043", parent=x1045, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x870 = (Const("0i").out, Const("96i").out, Const("2i").out) // Counter
      val x871 = (Const("0i").out, Const("96i").out, Const("96i").out) // Counter
      val x872 = CounterChain(name = "x872", x870, x871)
    }
    val x1012 = MetaPipeline(name="x1012", parent=x1043, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x987 = (Const("0i").out, Const("2i").out, Const("1i").out) // Counter
      val x988 = CounterChain(name = "x988", x987)
    }
    val x996 = UnitPipeline(name ="x996", parent=x1012, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr60 = CU.temp
      val tr58 = CU.temp
      val x872 = CounterChain.copy(x1043, "x872")
      val x988 = CounterChain.copy(x1012, "x988")
      val x996_unitCC = CounterChain(name = "x996_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x872(0)), CU.ctr(stage(0), x988(0))), op=FixAdd, results=List(CU.temp(stage(1), tr58)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr58), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr60)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr60), CU.ctr(stage(2), x872(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x990_scalar)))
    }
    val x998 = TileTransfer(name="x998", parent=x1012, memctrl=x998_mc_mc, mctpe=TileLoad, deps=List(x996), vec=x986_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x998_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x998_cc = CounterChain(name = "x998_cc", x998_ctr)
      val x999 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1000 = CounterChain(name = "x1000", x999).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x990_scalar), CU.ctr(stage(0), x998_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x998_mc_mc.saddr)))
    }
    val x1041 = MetaPipeline(name="x1041", parent=x1043, deps=List(x1012)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1014 = (Const("0i").out, Const("2i").out, Const("1i").out) // Counter
      val x1015 = CounterChain(name = "x1015", x1014)
    }
    val x1030 = Pipeline(name="x1030", parent=x1041, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr69 = CU.temp
      val tr66 = CU.temp
      val x1015 = CounterChain.copy(x1041, "x1015")
      val x1000 = CounterChain.copy(x998, "x1000")
      val x988 = CounterChain.copy(x1012, "x988")
      val x1017 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1018 = CounterChain(name = "x1018", x1017)
      val x985_x1023 = SRAM(size = 192, writeCtr = x1000(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x986_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x985_x1023))
      Stage(stage(1), operands=List(x988(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr66)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr66), CU.ctr(stage(1), x1000(0))), op=FixAdd, results=List(x985_x1023.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1015(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr69)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr69), CU.ctr(stage(1), x1018(0))), op=FixAdd, results=List(x985_x1023.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x985_x1023)), op=Bypass, results=List(CU.vecOut(stage(3), x1013_vector)))
    }
    val x1037 = UnitPipeline(name ="x1037", parent=x1041, deps=List(x1030)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr77 = CU.temp
      val tr75 = CU.temp
      val x872 = CounterChain.copy(x1043, "x872")
      val x1015 = CounterChain.copy(x1041, "x1015")
      val x1037_unitCC = CounterChain(name = "x1037_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x872(0)), CU.ctr(stage(0), x1015(0))), op=FixAdd, results=List(CU.temp(stage(1), tr75)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr75), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr77)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr77), CU.ctr(stage(2), x872(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1031_scalar)))
    }
    val x1039 = TileTransfer(name="x1039", parent=x1041, memctrl=x1039_mc_mc, mctpe=TileStore, deps=List(x1037), vec=x1013_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1039_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1039_cc = CounterChain(name = "x1039_cc", x1039_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1031_scalar), CU.ctr(stage(0), x1039_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1039_mc_mc.saddr)))
    }
    
  }
}
