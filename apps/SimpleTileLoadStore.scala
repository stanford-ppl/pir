import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleTileLoadStoreDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x1106_vector = Vector()
    val x1120_vector = Vector()
    val x1035_argin = ArgIn()
    val x996_oc = OffChip()
    val x1036_argin = ArgIn()
    val x1133_vector = Vector()
    val x995_oc = OffChip()
    val x1107_mc_mc = MemoryController(TileLoad, x995_oc)
    val x1146_mc_mc = MemoryController(TileStore, x996_oc)
    val x1152 = Sequential(name="x1152", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1152_unitCC = CounterChain(name = "x1152_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1150 = Sequential(name="x1150", parent=x1152, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1041 = (Const("0i").out, CU.scalarIn(stage0, x1035_argin).out, Const("96i").out) // Counter
      val x1042 = CounterChain(name = "x1042", x1041)
    }
    val x1119 = MetaPipeline(name="x1119", parent=x1150, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1119_unitCC = CounterChain(name = "x1119_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1107 = TileTransfer(name="x1107", parent=x1119, memctrl=x1107_mc_mc, mctpe=TileLoad, deps=List(), vec=x1106_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1042 = CounterChain.copy(x1150, "x1042")
      val x1107_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1107_cc = CounterChain(name = "x1107_cc", x1107_ctr)
      val x1108 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1109 = CounterChain(name = "x1109", x1108).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1042(0)), CU.ctr(stage(0), x1107_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1107_mc_mc.saddr)))
    }
    val x1132 = Pipeline(name="x1132", parent=x1150, deps=List(x1119)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1121 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1122 = CounterChain(name = "x1122", x1121)
      val x1109 = CounterChain.copy(x1107, "x1109")
      val x1040_x1125 = SRAM(size = 96, writeCtr = x1109(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x1106_vector).rdAddr(x1122(0)).wtAddr(x1109(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1040_x1125.load, CU.scalarIn(stage(0), x1036_argin)), op=FixMul, results=List(CU.vecOut(stage(1), x1120_vector)))
    }
    val x1148 = MetaPipeline(name="x1148", parent=x1150, deps=List(x1132)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1148_unitCC = CounterChain(name = "x1148_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1145 = Pipeline(name="x1145", parent=x1148, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1122 = CounterChain.copy(x1132, "x1122")
      val x1134 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1135 = CounterChain(name = "x1135", x1134)
      val x1120_x1138 = SRAM(size = 96, writeCtr = x1122(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x1120_vector).rdAddr(x1135(0)).wtAddr(x1122(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1120_x1138.load), op=Bypass, results=List(CU.vecOut(stage(1), x1133_vector)))
    }
    val x1146 = TileTransfer(name="x1146", parent=x1148, memctrl=x1146_mc_mc, mctpe=TileStore, deps=List(x1145), vec=x1133_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1042 = CounterChain.copy(x1150, "x1042")
      val x1146_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1146_cc = CounterChain(name = "x1146_cc", x1146_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1042(0)), CU.ctr(stage(0), x1146_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1146_mc_mc.saddr)))
    }
    
  }
}
