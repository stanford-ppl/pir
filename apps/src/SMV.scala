import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SMVDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4270_oc = OffChip("x4270")
    val x4556_scalar = Scalar("x4556")
    val x4662_scalar = Scalar("x4662")
    val x4265_oc = OffChip("x4265")
    val x4268_oc = OffChip("x4268")
    val x4267_oc = OffChip("x4267")
    val x4263_argin = ArgIn("x4263")
    val x4266_oc = OffChip("x4266")
    val x4528_mc = MemoryController(TileLoad, x4267_oc)
    val x4637_mc = MemoryController(TileLoad, x4266_oc)
    val x4590_mc = MemoryController(TileLoad, x4265_oc)
    val x4659_mc = MemoryController(Gather, x4268_oc)
    val x4703_mc = MemoryController(TileStore, x4270_oc)
    val x4709 = Sequential(name = "x4709", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4709_unitcc = CounterChain(name = "x4709_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4707 = MetaPipeline(name = "x4707", parent=x4709, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x4263_argin).out, Const("96i").out) // Counter
      val x4502 = CounterChain(name = "x4502", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4549 = StreamController(name = "x4549", parent=x4707, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4549_unitcc = CounterChain(name = "x4549_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4524_0 = UnitPipeline(name = "x4524_0", parent=x4549, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr182 = CU.temp
      val tr181 = CU.temp
      val tr179 = CU.temp
      val tr178 = CU.temp
      val tr176 = CU.temp
      val tr171 = CU.temp
      val x4502 = CounterChain.copy(x4707, "x4502")
      val x4524_unitcc = CounterChain(name = "x4524_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4502(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr171)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4502(0)), CU.temp(stage(1), tr171)), op=FixSub, results=List(CU.scalarOut(stage(2), x4528_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr171), Const("96i")), op=FixAdd, results=List(CU.temp(stage(3), tr176)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr176), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr178)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr176), CU.temp(stage(4), tr178)), op=FixSub, results=List(CU.temp(stage(5), tr179)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr178), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr181)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr181), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr182)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr179), CU.temp(stage(7), tr182)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4528_mc.len)))
    }
    val x4685 = Sequential(name = "x4685", parent=x4707, deps=List(x4549)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x4551 = CounterChain(name = "x4551", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4561_0 = UnitPipeline(name = "x4561_0", parent=x4685, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4551 = CounterChain.copy(x4685, "x4551")
      val x4561_unitcc = CounterChain(name = "x4561_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4504_x4558 = SemiFIFO(size = 96, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4551(0))).wtPort(x4528_mc.vdata).rdAddr(x4551(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4504_x4558.load), op=Bypass, results=List(CU.scalarOut(stage(1), x4556_scalar)))
    }
    val x4611 = StreamController(name = "x4611", parent=x4685, deps=List(x4561_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x4567 = CounterChain(name = "x4567", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4586_0 = UnitPipeline(name = "x4586_0", parent=x4611, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr214 = CU.temp
      val tr213 = CU.temp
      val tr211 = CU.temp
      val tr210 = CU.temp
      val tr208 = CU.temp
      val tr204 = CU.temp
      val tr202 = CU.temp
      val tr200 = CU.temp
      val tr199 = CU.temp
      val x4586_unitcc = CounterChain(name = "x4586_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4502 = CounterChain.copy(x4707, "x4502")
      val x4567 = CounterChain.copy(x4611, "x4567")
      val x4551 = CounterChain.copy(x4685, "x4551")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4502(0)), CU.ctr(stage(0), x4551(0))), op=FixAdd, results=List(CU.temp(stage(1), tr199)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr199), CU.ctr(stage(1), x4567(0))), op=FixAdd, results=List(CU.temp(stage(2), tr200)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr200), Const("50i")), op=FixMul, results=List(CU.temp(stage(3), tr202)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr202), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr204)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr202), CU.temp(stage(4), tr204)), op=FixSub, results=List(CU.scalarOut(stage(5), x4590_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr204), CU.scalarIn(stage(5), x4556_scalar)), op=FixAdd, results=List(CU.temp(stage(6), tr208)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr208), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr210)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr208), CU.temp(stage(7), tr210)), op=FixSub, results=List(CU.temp(stage(8), tr211)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr210), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr213)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr213), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr214)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr211), CU.temp(stage(10), tr214)), op=FixAdd, results=List(CU.scalarOut(stage(11), x4590_mc.len)))
    }
    val x4658 = StreamController(name = "x4658", parent=x4685, deps=List(x4611)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x4614 = CounterChain(name = "x4614", ctr9)
      var stage: List[Stage] = Nil
    }
    val x4633_0 = UnitPipeline(name = "x4633_0", parent=x4658, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr243 = CU.temp
      val tr242 = CU.temp
      val tr240 = CU.temp
      val tr239 = CU.temp
      val tr237 = CU.temp
      val tr233 = CU.temp
      val tr231 = CU.temp
      val tr229 = CU.temp
      val tr228 = CU.temp
      val x4633_unitcc = CounterChain(name = "x4633_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4502 = CounterChain.copy(x4707, "x4502")
      val x4551 = CounterChain.copy(x4685, "x4551")
      val x4614 = CounterChain.copy(x4658, "x4614")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4502(0)), CU.ctr(stage(0), x4551(0))), op=FixAdd, results=List(CU.temp(stage(1), tr228)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr228), CU.ctr(stage(1), x4614(0))), op=FixAdd, results=List(CU.temp(stage(2), tr229)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr229), Const("50i")), op=FixMul, results=List(CU.temp(stage(3), tr231)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr231), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr233)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr231), CU.temp(stage(4), tr233)), op=FixSub, results=List(CU.scalarOut(stage(5), x4637_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr233), CU.scalarIn(stage(5), x4556_scalar)), op=FixAdd, results=List(CU.temp(stage(6), tr237)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr237), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr239)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr237), CU.temp(stage(7), tr239)), op=FixSub, results=List(CU.temp(stage(8), tr240)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr239), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr242)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr242), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr243)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr240), CU.temp(stage(10), tr243)), op=FixAdd, results=List(CU.scalarOut(stage(11), x4637_mc.len)))
    }
    val streamCtrler = StreamController(name = "streamCtrler", parent=x4685, deps=List()) { implicit CU => 
    }
    val x4659_0 = StreamPipeline(name = "x4659_0", parent=streamCtrler, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr17 = (Const("0i").out, CU.scalarIn(stage0, x4556_scalar).out, Const("16i").out) // Counter
      val x4659_cc = CounterChain(name = "x4659_cc", ctr17)
      val x4552_x4659 = SemiFIFO(size = 96, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4590_mc.vdata).rdAddr(x4659_cc(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4552_x4659.load), op=Bypass, results=List(CU.vecOut(stage(1), x4659_mc.addrs)))
    }
    val x4678_0 = Pipeline(name = "x4678_0", parent=x4685, deps=List(x4659_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4659_cc = CounterChain.copy(x4659_0, "x4659_cc")
      val ctr11 = (Const("0i").out, CU.scalarIn(stage0, x4556_scalar).out, Const("16i").out) // Counter
      val x4664 = CounterChain(name = "x4664", ctr11)
      val x4553_x4667 = SemiFIFO(size = 96, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4637_mc.vdata).rdAddr(x4664(0))
      val x4554_x4670 = SemiFIFO(size = 96, banking = Strided(1), buffering = SingleBuffer()).wtPort(x4659_mc.vdata).rdAddr(x4664(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4553_x4667.load, x4554_x4670.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr269) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr269), op=Bypass, results=List(CU.scalarOut(stage(2), x4662_scalar)))
    }
    val x4705 = StreamController(name = "x4705", parent=x4707, deps=List(x4685)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4705_unitcc = CounterChain(name = "x4705_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4707_leafX = UnitPipeline(name = "x4707_leafX", parent=x4707, deps=List(x4705)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4502 = CounterChain.copy(x4707, "x4502")
      val x4707_unitcc = CounterChain(name = "x4707_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4690_0 = UnitPipeline(name = "x4690_0", parent=x4705, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4502 = CounterChain.copy(x4707, "x4502")
      val x4690_unitcc = CounterChain(name = "x4690_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4502(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x4703_mc.ofs)))
      Stage(stage(2), operands=List(Const("96i")), op=Bypass, results=List(CU.scalarOut(stage(2), x4703_mc.len)))
    }
    val x4701_0 = Pipeline(name = "x4701_0", parent=x4705, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4705_unitcc = CounterChain.copy(x4705, "x4705_unitcc")
      val x4551 = CounterChain.copy(x4685, "x4551")
      val x4683_unitcc = CounterChain.copy(x4690_0, "x4690_unitcc")
      val ctr18 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x4692 = CounterChain(name = "x4692", ctr18)
      val x4503_x4695 = SRAM(size = 96, writeCtr = x4683_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4705_unitcc(0), swapWrite = x4551(0))).wtPort(x4662_scalar).rdAddr(x4692(0)).wtAddr(x4551(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4503_x4695.load), op=Bypass, results=List(CU.vecOut(stage(1), x4703_mc.vdata)))
    }
    
  }
}
