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
    val x1859_scalar = Scalar("x1859")
    val x1670_argin = ArgIn("x1670")
    val x1671_argout = ArgOut("x1671")
    val x1674_oc = OffChip("x1674")
    val x1760_scalar = Scalar("x1760")
    val x1675_oc = OffChip("x1675")
    val x1784_mc = MemoryController(TileLoad, x1674_oc)
    val x1829_mc = MemoryController(TileLoad, x1675_oc)
    val x1890 = Sequential(name = "x1890", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1890_unitcc = CounterChain(name = "x1890_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1884 = MetaPipeline(name = "x1884", parent=x1890, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x1670_argin).out, Const("8000i").out) // Counter
      val x1755 = CounterChain(name = "x1755", ctr1)
      var stage: List[Stage] = Nil
    }
    val x1805 = StreamController(name = "x1805", parent=x1884, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1805_unitcc = CounterChain(name = "x1805_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1780_0 = UnitPipeline(name = "x1780_0", parent=x1805, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr69 = CU.temp
      val tr68 = CU.temp
      val tr66 = CU.temp
      val tr65 = CU.temp
      val tr63 = CU.temp
      val tr58 = CU.temp
      val x1755 = CounterChain.copy(x1884, "x1755")
      val x1780_unitcc = CounterChain(name = "x1780_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1755(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr58)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1755(0)), CU.temp(stage(1), tr58)), op=FixSub, results=List(CU.scalarOut(stage(2), x1784_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr58), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr63)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr63), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr65)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr63), CU.temp(stage(4), tr65)), op=FixSub, results=List(CU.temp(stage(5), tr66)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr65), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr68)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr68), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr69)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr66), CU.temp(stage(7), tr69)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1784_mc.len)))
    }
    val x1850 = StreamController(name = "x1850", parent=x1884, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1850_unitcc = CounterChain(name = "x1850_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x1825_0 = UnitPipeline(name = "x1825_0", parent=x1850, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr92 = CU.temp
      val tr91 = CU.temp
      val tr89 = CU.temp
      val tr88 = CU.temp
      val tr86 = CU.temp
      val tr81 = CU.temp
      val x1755 = CounterChain.copy(x1884, "x1755")
      val x1825_unitcc = CounterChain(name = "x1825_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1755(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr81)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1755(0)), CU.temp(stage(1), tr81)), op=FixSub, results=List(CU.scalarOut(stage(2), x1829_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr81), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr86)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr86), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr88)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr86), CU.temp(stage(4), tr88)), op=FixSub, results=List(CU.temp(stage(5), tr89)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr88), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr91)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr91), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr92)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr89), CU.temp(stage(7), tr92)), op=FixAdd, results=List(CU.scalarOut(stage(8), x1829_mc.len)))
    }
    val x1856_0 = UnitPipeline(name = "x1856_0", parent=x1884, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr104 = CU.temp
      val x1755 = CounterChain.copy(x1884, "x1755")
      val x1856_unitcc = CounterChain(name = "x1856_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1670_argin), CU.ctr(stage(0), x1755(0))), op=FixSub, results=List(CU.temp(stage(1), tr104)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr104), Const("8000i")), op=FixMin, results=List(CU.scalarOut(stage(2), x1760_scalar)))
    }
    val x1876_0 = Pipeline(name = "x1876_0", parent=x1884, deps=List(x1805, x1850, x1856_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1755 = CounterChain.copy(x1884, "x1755")
      val ctr5 = (Const("0i").out, CU.scalarIn(stage0, x1760_scalar).out, Const("16i").out) // Counter
      val x1862 = CounterChain(name = "x1862", ctr5)
      val x1758_x1865 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x1862(0), swapWrite = x1755(0))).wtPort(x1784_mc.vdata).rdAddr(x1862(0))
      val x1759_x1868 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x1862(0), swapWrite = x1755(0))).wtPort(x1829_mc.vdata).rdAddr(x1862(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1758_x1865.load, x1759_x1868.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr120) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr120), op=Bypass, results=List(CU.scalarOut(stage(2), x1859_scalar)))
    }
    val x1882_0 = UnitPipeline(name = "x1882_0", parent=x1884, deps=List(x1876_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar124 = CU.accum(init = Const("0i"))
      val x1882_unitcc = CounterChain(name = "x1882_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1859_scalar), CU.accum(stage(1), ar124)), op=FixAdd, results=List(CU.scalarOut(stage(1), x1671_argout), CU.accum(stage(1), ar124)))
    }
    
  }
}
