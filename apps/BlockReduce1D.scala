import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object BlockReduce1DDesign extends PIRApp {
  override val arch = P2P_4CU_4TT
  def main(args: String*)(top:Top) = {
    val x1109_vector = Vector()
    val x1042_vector = Vector()
    val x1076_vector = Vector()
    val x1001_oc = OffChip()
    val x998_argin = ArgIn()
    val x1002_oc = OffChip()
    val x1077_mc_mc = MemoryController(TileLoad, x1001_oc)
    val x1127_mc_mc = MemoryController(TileStore, x1002_oc)
    val x1131 = Sequential(name="x1131", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1131_unitCC = CounterChain(name = "x1131_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1108 = MetaPipeline(name="x1108", parent=x1131, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1043 = (Const("0i").out, CU.scalarIn(stage0, x998_argin).out, Const("96i").out) // Counter
      val x1044 = CounterChain(name = "x1044", x1043)
    }
    val x1089 = MetaPipeline(name="x1089", parent=x1108, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1089_unitCC = CounterChain(name = "x1089_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1077 = TileTransfer(name="x1077", parent=x1089, memctrl=x1077_mc_mc, mctpe=TileLoad, deps=List(), vec=x1076_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1044 = CounterChain.copy(x1108, "x1044")
      val x1077_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1077_cc = CounterChain(name = "x1077_cc", x1077_ctr)
      val x1078 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1079 = CounterChain(name = "x1079", x1078).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1044(0)), CU.ctr(stage(0), x1077_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1077_mc_mc.saddr)))
    }
    val x1106 = Pipeline(name="x1106", parent=x1108, deps=List(x1089)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr46 = CU.temp
      val tr47 = CU.temp
      val tr49 = CU.temp
      val tr48 = CU.temp
      val x1045 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1046 = CounterChain(name = "x1046", x1045)
      val x1079 = CounterChain.copy(x1077, "x1079")
      val x1089_unitCC = CounterChain.copy(x1089, "x1089_unitCC")
      val x1044 = CounterChain.copy(x1108, "x1044")
      val x1075_x1095 = SRAM(size = 96, writeCtr = x1079(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x1046(0), swapWrite = x1089_unitCC(0))).wtPort(x1076_vector).rdAddr(x1046(0)).wtAddr(x1079(0))
      val x1042_x1099 = SRAM(size = 96, writeCtr = x1046(0), banking = Duplicated(), buffering = SingleBuffer()).rdAddr(x1046(0))
      val wr52 = CU.wtAddr(x1042_x1099)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1044(0)), CU.scalarIn(stage(0), x998_argin)), op=FixLt, results=List(CU.temp(stage(1), tr46)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1046(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(2), tr47)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr46), CU.temp(stage(2), tr47)), op=BitAnd, results=List(CU.temp(stage(3), tr48)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr48), CU.load(stage(3), x1075_x1095), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr49)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr49), CU.load(stage(4), x1042_x1099)), op=FixAdd, results=List(CU.vecOut(stage(5), x1042_vector), CU.store(stage(5), x1042_x1099)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x1046(0))), op=Bypass, results=List(CU.wtAddr(stage(6), wr52)))
    }
    val x1129 = MetaPipeline(name="x1129", parent=x1131, deps=List(x1108)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1129_unitCC = CounterChain(name = "x1129_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1126 = Pipeline(name="x1126", parent=x1129, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1110 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1111 = CounterChain(name = "x1111", x1110)
      val x1046 = CounterChain.copy(x1106, "x1046")
      val x1042_x1118 = SRAM(size = 96, writeCtr = x1046(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x1042_vector).rdAddr(x1111(0)).wtAddr(x1046(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1042_x1118.load), op=Bypass, results=List(CU.vecOut(stage(1), x1109_vector)))
    }
    val x1127 = TileTransfer(name="x1127", parent=x1129, memctrl=x1127_mc_mc, mctpe=TileStore, deps=List(x1126), vec=x1109_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1127_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1127_cc = CounterChain(name = "x1127_cc", x1127_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1127_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1127_mc_mc.saddr)))
    }
    
  }
}
