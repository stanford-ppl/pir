import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object Memcpy2DDesign extends PIRApp {
  override val arch = P2P_4CU_4TT
  def main(args: String*)(top:Top) = {
    val x828_oc = OffChip()
    val x988_scalar = Scalar()
    val x1029_scalar = Scalar()
    val x1011_vector = Vector()
    val x984_vector = Vector()
    val x829_oc = OffChip()
    val x1037_mc_mc = MemoryController(TileStore, x829_oc)
    val x996_mc_mc = MemoryController(TileLoad, x828_oc)
    val x1043 = Sequential(name="x1043", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1043_unitCC = CounterChain(name = "x1043_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1041 = Sequential(name="x1041", parent=x1043, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x868 = (Const("0i").out, Const("96i").out, Const("2i").out) // Counter
      val x869 = (Const("0i").out, Const("96i").out, Const("96i").out) // Counter
      val x870 = CounterChain(name = "x870", x868, x869)
    }
    val x1010 = MetaPipeline(name="x1010", parent=x1041, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x985 = (Const("0i").out, Const("2i").out, Const("1i").out) // Counter
      val x986 = CounterChain(name = "x986", x985)
    }
    val x994 = UnitPipeline(name ="x994", parent=x1010, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr60 = CU.temp
      val tr58 = CU.temp
      val x870 = CounterChain.copy(x1041, "x870")
      val x986 = CounterChain.copy(x1010, "x986")
      val x994_unitCC = CounterChain(name = "x994_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x870(0)), CU.ctr(stage(0), x986(0))), op=FixAdd, results=List(CU.temp(stage(1), tr58)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr58), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr60)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr60), CU.ctr(stage(2), x870(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x988_scalar)))
    }
    val x996 = TileTransfer(name="x996", parent=x1010, memctrl=x996_mc_mc, mctpe=TileLoad, deps=List(x994), vec=x984_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x996_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x996_cc = CounterChain(name = "x996_cc", x996_ctr)
      val x997 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x998 = CounterChain(name = "x998", x997).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x988_scalar), CU.ctr(stage(0), x996_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x996_mc_mc.saddr)))
    }
    val x1039 = MetaPipeline(name="x1039", parent=x1041, deps=List(x1010)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1012 = (Const("0i").out, Const("2i").out, Const("1i").out) // Counter
      val x1013 = CounterChain(name = "x1013", x1012)
    }
    val x1028 = Pipeline(name="x1028", parent=x1039, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr69 = CU.temp
      val tr66 = CU.temp
      val x998 = CounterChain.copy(x996, "x998")
      val x1013 = CounterChain.copy(x1039, "x1013")
      val x986 = CounterChain.copy(x1010, "x986")
      val x1015 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1016 = CounterChain(name = "x1016", x1015)
      val x983_x1021 = SRAM(size = 192, writeCtr = x998(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x984_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x983_x1021))
      Stage(stage(1), operands=List(x986(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr66)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr66), CU.ctr(stage(1), x998(0))), op=FixAdd, results=List(x983_x1021.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1013(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr69)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr69), CU.ctr(stage(1), x1016(0))), op=FixAdd, results=List(x983_x1021.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x983_x1021)), op=Bypass, results=List(CU.vecOut(stage(3), x1011_vector)))
    }
    val x1035 = UnitPipeline(name ="x1035", parent=x1039, deps=List(x1028)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr75 = CU.temp
      val tr77 = CU.temp
      val x870 = CounterChain.copy(x1041, "x870")
      val x1013 = CounterChain.copy(x1039, "x1013")
      val x1035_unitCC = CounterChain(name = "x1035_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x870(0)), CU.ctr(stage(0), x1013(0))), op=FixAdd, results=List(CU.temp(stage(1), tr75)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr75), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr77)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr77), CU.ctr(stage(2), x870(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x1029_scalar)))
    }
    val x1037 = TileTransfer(name="x1037", parent=x1039, memctrl=x1037_mc_mc, mctpe=TileStore, deps=List(x1035), vec=x1011_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1037_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1037_cc = CounterChain(name = "x1037_cc", x1037_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1029_scalar), CU.ctr(stage(0), x1037_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1037_mc_mc.saddr)))
    }
    
  }
}
