import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProductLarge extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1715_scalar = Scalar("x1715")
    val x1638_oc = OffChip("x1638")
    val x1634_argout = ArgOut("x1634")
    val x1723_scalar = Scalar("x1723")
    val x1816_scalar = Scalar("x1816")
    val x1633_argin = ArgIn("x1633")
    val x1637_oc = OffChip("x1637")
    val x1747_mc = MemoryController(TileLoad, x1637_oc)
    val x1789_mc = MemoryController(TileLoad, x1638_oc)
    val x1845 = Sequential(name = "x1845", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1845_unitcc = CounterChain(name = "x1845_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1839 = MetaPipeline(name = "x1839", parent=x1845, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1633_argin).out, Const("96i").out) // Counter
      val x1718 = CounterChain(name = "x1718", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1765 = StreamController(name = "x1765", parent=x1839, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1765_unitcc = CounterChain(name = "x1765_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1743_0 = UnitPipeline(name = "x1743_0", parent=x1765, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr68 = CU.temp
      val tr67 = CU.temp
      val tr65 = CU.temp
      val tr64 = CU.temp
      val tr62 = CU.temp
      val tr58 = CU.temp
      val x1718 = CounterChain.copy(x1839, "x1718")
      val x1743_unitcc = CounterChain(name = "x1743_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1718(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr58)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1718(0)), CU.temp(stage(1), tr58)), op=FixSub, results=List(CU.scalarOut(stage(2), x1747_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr58), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr62)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr62), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr64)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr62), CU.temp(stage(4), tr64)), op=FixSub, results=List(CU.temp(stage(5), tr65)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr64), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr67)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr67), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr68)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr65), CU.temp(stage(7), tr68)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1747_mc.len)))
    }
    val x1807 = StreamController(name = "x1807", parent=x1839, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1807_unitcc = CounterChain(name = "x1807_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1785_0 = UnitPipeline(name = "x1785_0", parent=x1807, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr90 = CU.temp
      val tr89 = CU.temp
      val tr87 = CU.temp
      val tr86 = CU.temp
      val tr84 = CU.temp
      val tr80 = CU.temp
      val x1718 = CounterChain.copy(x1839, "x1718")
      val x1785_unitcc = CounterChain(name = "x1785_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1718(0)), Const("96i")), op=FixMod, results=List(CU.temp(stage(1), tr80)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1718(0)), CU.temp(stage(1), tr80)), op=FixSub, results=List(CU.scalarOut(stage(2), x1789_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr80), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr84)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr84), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr86)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr84), CU.temp(stage(4), tr86)), op=FixSub, results=List(CU.temp(stage(5), tr87)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr86), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr89)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr89), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr90)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr87), CU.temp(stage(7), tr90)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1789_mc.len)))
    }
    val x1813_0 = UnitPipeline(name = "x1813_0", parent=x1839, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr102 = CU.temp
      val x1718 = CounterChain.copy(x1839, "x1718")
      val x1813_unitcc = CounterChain(name = "x1813_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1633_argin), CU.ctr(stage(0), x1718(0))), op=FixSub, results=List(CU.temp(stage(1), tr102)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr102), Const("96i")), op=FltMin, results=List(CU.scalarOut(stage(2), x1723_scalar)))
    }
    val x1831_0 = Pipeline(name = "x1831_0", parent=x1839, deps=List(x1765, x1807, x1813_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x1723_scalar).out, Const("16i").out) // Counter
      val x1819 = CounterChain(name = "x1819", ctr5)
      val x1721_x1821 = FIFO(size = 96, banking = Strided(1)).wtPort(x1747_mc.vdata)
      val x1722_x1823 = FIFO(size = 96, banking = Strided(1)).wtPort(x1789_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1721_x1821.load, x1722_x1823.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr117) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr117), op=Bypass, results=List(CU.scalarOut(stage(2), x1816_scalar)))
    }
    val x1837_0 = UnitPipeline(name = "x1837_0", parent=x1839, deps=List(x1831_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar121 = CU.accum(init = Const("0i"))
      val x1837_unitcc = CounterChain(name = "x1837_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1816_scalar), CU.accum(stage(1), ar121)), op=FixAdd, results=List(CU.scalarOut(stage(1), x1715_scalar), CU.accum(stage(1), ar121)))
    }
    val x1843_0 = UnitPipeline(name = "x1843_0", parent=x1845, deps=List(x1839)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1843_unitcc = CounterChain(name = "x1843_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1715_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x1634_argout)))
    }
    
  }
}
