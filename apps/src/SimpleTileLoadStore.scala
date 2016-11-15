import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleTileLoadStoreDesign extends PIRApp {
  override val arch = SN_4x4 
  def main(args: String*)(top:Top) = {
    val x1105_vector = Vector()
    val x1034_argin = ArgIn()
    val x1132_vector = Vector()
    val x994_oc = OffChip()
    val x995_oc = OffChip()
    val x1119_vector = Vector()
    val x1035_argin = ArgIn()
    val x1145_mc_mc = MemoryController(TileStore, x995_oc)
    val x1106_mc_mc = MemoryController(TileLoad, x994_oc)
    val x1151 = Sequential(name="x1151", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1151_unitCC = CounterChain(name = "x1151_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1149 = Sequential(name="x1149", parent=x1151, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1040 = (Const("0i").out, CU.scalarIn(stage0, x1034_argin).out, Const("96i").out) // Counter
      val x1041 = CounterChain(name = "x1041", x1040)
    }
    val x1118 = MetaPipeline(name="x1118", parent=x1149, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1118_unitCC = CounterChain(name = "x1118_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1106 = TileTransfer(name="x1106", parent=x1118, memctrl=x1106_mc_mc, mctpe=TileLoad, deps=List(), vec=x1105_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1041 = CounterChain.copy(x1149, "x1041")
      val x1106_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1106_cc = CounterChain(name = "x1106_cc", x1106_ctr)
      val x1107 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1108 = CounterChain(name = "x1108", x1107).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1041(0)), CU.ctr(stage(0), x1106_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1106_mc_mc.ofs)))
    }
    val x1131 = Pipeline(name="x1131", parent=x1149, deps=List(x1118)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1120 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1121 = CounterChain(name = "x1121", x1120)
      val x1108 = CounterChain.copy(x1106, "x1108")
      val x1039_x1124 = SRAM(size = 96, writeCtr = x1108(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x1105_vector).rdAddr(x1121(0)).wtAddr(x1108(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1039_x1124.load, CU.scalarIn(stage(0), x1035_argin)), op=FixMul, results=List(CU.vecOut(stage(1), x1119_vector)))
    }
    val x1147 = MetaPipeline(name="x1147", parent=x1149, deps=List(x1131)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1147_unitCC = CounterChain(name = "x1147_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1144 = Pipeline(name="x1144", parent=x1147, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1133 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1134 = CounterChain(name = "x1134", x1133)
      val x1121 = CounterChain.copy(x1131, "x1121")
      val x1119_x1137 = SRAM(size = 96, writeCtr = x1121(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x1119_vector).rdAddr(x1134(0)).wtAddr(x1121(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1119_x1137.load), op=Bypass, results=List(CU.vecOut(stage(1), x1132_vector)))
    }
    val x1145 = TileTransfer(name="x1145", parent=x1147, memctrl=x1145_mc_mc, mctpe=TileStore, deps=List(x1144), vec=x1132_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1041 = CounterChain.copy(x1149, "x1041")
      val x1145_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1145_cc = CounterChain(name = "x1145_cc", x1145_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1041(0)), CU.ctr(stage(0), x1145_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1145_mc_mc.ofs)))
    }
    
  }
}
