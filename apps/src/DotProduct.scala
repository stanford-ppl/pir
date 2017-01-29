import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProductDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1600_oc = OffChip("x1600")
    val x1599_argout = ArgOut("x1599")
    val x1777_scalar = Scalar("x1777").producer("x1793_0").consumer("x1799_0", true).buffering(DoubleBuffer())
    val x1601_oc = OffChip("x1601")
    val x1753_mc = MemoryController(TileLoad, x1601_oc)
    val x1708_mc = MemoryController(TileLoad, x1600_oc)
    val x1807 = Sequential(name = "x1807", parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1807_unitcc = CounterChain(name = "x1807_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1801 = MetaPipeline(name = "x1801", parent=x1807) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("768000000i").out, Const("2000i").out) // Counter
      val x1680 = CounterChain(name = "x1680", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1729 = StreamController(name = "x1729", parent=x1801) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1729_unitcc = CounterChain(name = "x1729_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1704_0 = UnitPipeline(name = "x1704_0", parent=x1729) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr65 = CU.temp
      val tr64 = CU.temp
      val tr62 = CU.temp
      val tr61 = CU.temp
      val tr59 = CU.temp
      val tr54 = CU.temp
      val x1680 = CounterChain.copy(x1801, "x1680")
      val x1704_unitcc = CounterChain(name = "x1704_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1680(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr54)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1680(0)), CU.temp(stage(1), tr54)), op=FixSub, results=List(CU.scalarOut(stage(2), x1708_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr54), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr59)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr59), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr61)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr59), CU.temp(stage(4), tr61)), op=FixSub, results=List(CU.temp(stage(5), tr62)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr61), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr64)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr64), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr65)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr62), CU.temp(stage(7), tr65)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1708_mc.len)))
    }
    val x1774 = StreamController(name = "x1774", parent=x1801) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1774_unitcc = CounterChain(name = "x1774_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1749_0 = UnitPipeline(name = "x1749_0", parent=x1774) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr88 = CU.temp
      val tr87 = CU.temp
      val tr85 = CU.temp
      val tr84 = CU.temp
      val tr82 = CU.temp
      val tr77 = CU.temp
      val x1680 = CounterChain.copy(x1801, "x1680")
      val x1749_unitcc = CounterChain(name = "x1749_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1680(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr77)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1680(0)), CU.temp(stage(1), tr77)), op=FixSub, results=List(CU.scalarOut(stage(2), x1753_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr77), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr82)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr82), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr84)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr82), CU.temp(stage(4), tr84)), op=FixSub, results=List(CU.temp(stage(5), tr85)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr84), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr87)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr87), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr88)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr85), CU.temp(stage(7), tr88)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1753_mc.len)))
    }

    val semiA = MemoryPipeline(name="semiA", parent=x1801){ implicit CU =>
      val x1779 = CounterChain.copy("x1793_0", "x1779")
      val x1683_x1782 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2)).wtPort(x1708_mc.vdata).rdAddr(x1779(0)).consumer("x1793_0", true).producer("x1729")
    }

    val semiB = MemoryPipeline(name="semiB", parent=x1801){ implicit CU =>
      val x1779 = CounterChain.copy("x1793_0", "x1779")
      val x1684_x1785 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2)).wtPort(x1753_mc.vdata).rdAddr(x1779(0)).consumer("x1793_0", true).producer("x1774")
    }

    val x1793_0 = Pipeline(name = "x1793_0", parent=x1801) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x1779 = CounterChain(name = "x1779", ctr5)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), semiA.data), CU.vecIn(stage(0), semiB.data)), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr110) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr110), op=Bypass, results=List(CU.scalarOut(stage(2), x1777_scalar)))
    }

    val x1799_0 = UnitPipeline(name = "x1799_0", parent=x1801) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar114 = CU.accum(init = Const("0i"))
      val x1799_unitcc = CounterChain(name = "x1799_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1777_scalar), CU.accum(stage(1), ar114)), op=FixAdd, results=List(CU.scalarOut(stage(1), x1599_argout), CU.accum(stage(1), ar114)))
    }
    
  }
}
