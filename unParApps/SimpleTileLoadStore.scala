import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleTileLoadStoreDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1472_argin = ArgIn("x1472")
    val x1433_oc = OffChip("x1433")
    val x1525_vector = Vector("x1525")
    val x1432_oc = OffChip("x1432")
    val x1473_argin = ArgIn("x1473")
    val x1503_mc = MemoryController(TileLoad, x1432_oc)
    val x1558_mc = MemoryController(TileStore, x1433_oc)
    val x1564 = Sequential(name = "x1564", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1564_unitcc = CounterChain(name = "x1564_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1562 = Sequential(name = "x1562", parent=x1564, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1472_argin).out, Const("96i").out) // Counter
      val x1479 = CounterChain(name = "x1479", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1524 = StreamController(name = "x1524", parent=x1562, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1524_unitcc = CounterChain(name = "x1524_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1499_0 = UnitPipeline(name = "x1499_0", parent=x1524, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr65 = CU.temp
      val tr64 = CU.temp
      val tr62 = CU.temp
      val tr61 = CU.temp
      val tr59 = CU.temp
      val tr55 = CU.temp
      val x1479 = CounterChain.copy(x1562, "x1479")
      val x1499_unitcc = CounterChain(name = "x1499_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1479(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr55)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1479(0)), CU.temp(stage(1), tr55)), op=FixSub, results=List(CU.scalarOut(stage(2), x1503_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr55), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr59)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr59), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr61)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr59), CU.temp(stage(4), tr61)), op=FixSub, results=List(CU.temp(stage(5), tr62)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr61), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr64)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr64), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr65)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr62), CU.temp(stage(7), tr65)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1503_mc.len)))
    }
    val x1540_0 = Pipeline(name = "x1540_0", parent=x1562, deps=List(x1524)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x1527 = CounterChain(name = "x1527", ctr5)
      val x1476_x1530 = SemiFIFO(size = 96, banking = Strided(1), buffering = SingleBuffer()).wtPort(x1503_mc.vdata).rdAddr(x1527(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1476_x1530.load, CU.scalarIn(stage(0), x1473_argin)), op=FixMul, results=List(CU.vecOut(stage(1), x1525_vector)))
    }
    val x1560 = StreamController(name = "x1560", parent=x1562, deps=List(x1540_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1560_unitcc = CounterChain(name = "x1560_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1545_0 = UnitPipeline(name = "x1545_0", parent=x1560, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1479 = CounterChain.copy(x1562, "x1479")
      val x1545_unitcc = CounterChain(name = "x1545_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1479(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x1558_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x1558_mc.len)))
    }
    val x1556_0 = Pipeline(name = "x1556_0", parent=x1560, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1527 = CounterChain.copy(x1540_0, "x1527")
      val ctr7 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x1547 = CounterChain(name = "x1547", ctr7)
      val x1525_x1550 = SRAM(size = 96, writeCtr = x1527(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x1525_vector).rdAddr(x1547(0)).wtAddr(x1527(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1525_x1550.load), op=Bypass, results=List(CU.vecOut(stage(1), x1558_mc.vdata)))
    }
    
  }
}
