import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object LogRegDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4388_oc = OffChip("x4388")
    val x4469_vector = Vector("x4469")
    val x4582_vector = Vector("x4582")
    val x4477_vector = Vector("x4477")
    val x4583_scalar = Scalar("x4583")
    val x4382_argin = ArgIn("x4382")
    val x4381_argin = ArgIn("x4381")
    val x4389_oc = OffChip("x4389")
    val x4581_scalar = Scalar("x4581")
    val x4387_oc = OffChip("x4387")
    val x4686_mc = MemoryController(TileStore, x4389_oc)
    val x4553_mc = MemoryController(TileLoad, x4388_oc)
    val x4508_mc = MemoryController(TileLoad, x4387_oc)
    val x4690 = Sequential(name = "x4690", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4690_unitcc = CounterChain(name = "x4690_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4668 = Sequential(name = "x4668", parent=x4690, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x4381_argin).out, Const("1i").out) // Counter
      val x4472 = CounterChain(name = "x4472", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4666 = Sequential(name = "x4666", parent=x4668, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4476 = CounterChain(name = "x4476", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4646 = MetaPipeline(name = "x4646", parent=x4666, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, CU.scalarIn(stage0, x4382_argin).out, Const("40i").out) // Counter
      val x4480 = CounterChain(name = "x4480", ctr6)
      var stage: List[Stage] = Nil
    }
    val x4529 = StreamController(name = "x4529", parent=x4646, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("40i").out, Const("1i").out) // Counter
      val x4485 = CounterChain(name = "x4485", ctr8)
      var stage: List[Stage] = Nil
    }
    val x4504_0 = UnitPipeline(name = "x4504_0", parent=x4529, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr281 = CU.temp
      val tr280 = CU.temp
      val tr278 = CU.temp
      val tr277 = CU.temp
      val tr275 = CU.temp
      val tr271 = CU.temp
      val tr269 = CU.temp
      val tr267 = CU.temp
      val x4504_unitcc = CounterChain(name = "x4504_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4485 = CounterChain.copy(x4529, "x4485")
      val x4480 = CounterChain.copy(x4646, "x4480")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4480(0)), CU.ctr(stage(0), x4485(0))), op=FixAdd, results=List(CU.temp(stage(1), tr267)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr267), Const("192i")), op=FixMul, results=List(CU.temp(stage(2), tr269)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr269), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr271)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr269), CU.temp(stage(3), tr271)), op=FixSub, results=List(CU.scalarOut(stage(4), x4508_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr271), Const("192i")), op=FixAdd, results=List(CU.temp(stage(5), tr275)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr275), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr277)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr275), CU.temp(stage(6), tr277)), op=FixSub, results=List(CU.temp(stage(7), tr278)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr277), Const("0i")), op=FixNeq, results=List(CU.temp(stage(8), tr280)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr280), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(9), tr281)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr278), CU.temp(stage(9), tr281)), op=FixAdd, results=List(CU.scalarOut(stage(10), x4508_mc.len)))
    }
    val x4574 = StreamController(name = "x4574", parent=x4646, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4574_unitcc = CounterChain(name = "x4574_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4549_0 = UnitPipeline(name = "x4549_0", parent=x4574, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr307 = CU.temp
      val tr306 = CU.temp
      val tr304 = CU.temp
      val tr303 = CU.temp
      val tr301 = CU.temp
      val tr296 = CU.temp
      val x4480 = CounterChain.copy(x4646, "x4480")
      val x4549_unitcc = CounterChain(name = "x4549_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4480(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr296)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4480(0)), CU.temp(stage(1), tr296)), op=FixSub, results=List(CU.scalarOut(stage(2), x4553_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr296), Const("40i")), op=FixAdd, results=List(CU.temp(stage(3), tr301)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr301), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr303)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr301), CU.temp(stage(4), tr303)), op=FixSub, results=List(CU.temp(stage(5), tr304)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr303), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr306)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr306), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr307)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr304), CU.temp(stage(7), tr307)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4553_mc.len)))
    }
    val x4644 = MetaPipeline(name = "x4644", parent=x4646, deps=List(x4529, x4574)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("40i").out, Const("1i").out) // Counter
      val x4578 = CounterChain(name = "x4578", ctr12)
      val ctr14 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4580 = CounterChain(name = "x4580", ctr14)
      var stage: List[Stage] = Nil
    }
    val x4599_0:Pipeline = Pipeline(name = "x4599_0", parent=x4644, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr323 = CU.temp
      val x4578 = CounterChain.copy(x4644, "x4578")
      val x4476 = CounterChain.copy("x4664_0", "x4476")
      val ctr15 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4585 = CounterChain(name = "x4585", ctr15)
      val x4469_x4591 = SRAM(size = 192, writeCtr = x4476(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4469_vector).rdAddr(x4585(0)).wtAddr(x4476(0))
      val x4481_x4588 = SemiFIFO(size = 7680, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4578(0))).wtPort(x4508_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4578(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr323)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr323), CU.ctr(stage(1), x4585(0))), op=FixAdd, results=List(x4481_x4588.readAddr))
      Stage(stage(3), operands=List(x4481_x4588.load, CU.load(stage(2), x4469_x4591)), op=FltMul, results=List(CU.reduce(stage(3))))
      val (rs1, rr332) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(4), operands=List(rr332), op=Bypass, results=List(CU.scalarOut(stage(4), x4583_scalar)))
    }

    val x4610_0 = UnitPipeline(name = "x4610_0", parent=x4644, deps=List(x4599_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr344 = CU.temp
      val tr343 = CU.temp
      val tr341 = CU.temp
      val tr340 = CU.temp
      val x4578 = CounterChain.copy(x4644, "x4578")
      val x4610_unitcc = CounterChain(name = "x4610_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4482_x4601 = SemiFIFO(size = 40, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4578(0))).wtPort(x4553_mc.vdata).rdAddr(x4578(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x4583_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr340)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr340)), op=FltExp, results=List(CU.temp(stage(2), tr341)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr341), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr343)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr343)), op=FltDiv, results=List(CU.temp(stage(4), tr344)))
      Stage(stage(5), operands=List(CU.load(stage(4), x4482_x4601), CU.temp(stage(4), tr344)), op=FltSub, results=List(CU.scalarOut(stage(5), x4581_scalar)))
    }
    val x4625_0 = Pipeline(name = "x4625_0", parent=x4644, deps=List(x4610_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr350 = CU.temp
      val x4578 = CounterChain.copy(x4644, "x4578")
      val ctr17 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4612 = CounterChain(name = "x4612", ctr17)
      val x4481_x4615 = SemiFIFO(size = 7680, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4578(0))).wtPort(x4508_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4578(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr350)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr350), CU.ctr(stage(1), x4612(0))), op=FixAdd, results=List(x4481_x4615.readAddr))
      Stage(stage(3), operands=List(x4481_x4615.load, CU.scalarIn(stage(2), x4581_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x4582_vector)))
    }
    val x4642_0 = Pipeline(name = "x4642_0", parent=x4644, deps=List(x4625_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4612 = CounterChain.copy(x4625_0, "x4612")
      val ctr21 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4580 = CounterChain(name = "x4580", ctr21)
      val x4582_x4628 = SRAM(size = 192, writeCtr = x4612(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4580(0), swapWrite = x4612(0))).wtPort(x4582_vector).rdAddr(x4580(0)).wtAddr(x4612(0))
      val x4477_x4631 = SRAM(size = 192, writeCtr = x4580(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x4580(0))
      val wr372 = CU.wtAddr(x4477_x4631)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4582_x4628.load, x4477_x4631.load), op=FltAdd, results=List(CU.vecOut(stage(1), x4477_vector), CU.store(stage(1), x4477_x4631)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4580(0))), op=Bypass, results=List(CU.wtAddr(stage(2), wr372)))
    }
    val x4664_0 = Pipeline(name = "x4664_0", parent=x4666, deps=List(x4646)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr385 = CU.temp
      val ctr22 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4476 = CounterChain(name = "x4476", ctr22)
      val x4580 = CounterChain.copy(x4642_0, "x4580")
      val x4469_x4652 = SRAM(size = 192, writeCtr = x4476(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x4476(0))
      val x4477_x4649 = SRAM(size = 192, writeCtr = x4580(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4477_vector).rdAddr(x4476(0)).wtAddr(x4580(0))
      val wr387 = CU.wtAddr(x4469_x4652)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x4469_x4652.load, Const("1i")), op=FltMul, results=List(CU.temp(stage(1), tr385)))
      Stage(stage(2), operands=List(CU.load(stage(1), x4477_x4649), CU.temp(stage(1), tr385)), op=FltAdd, results=List(CU.vecOut(stage(2), x4469_vector), CU.store(stage(2), x4469_x4652)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4476(0))), op=Bypass, results=List(CU.wtAddr(stage(3), wr387)))
    }
    val x4688 = StreamController(name = "x4688", parent=x4690, deps=List(x4668)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4688_unitcc = CounterChain(name = "x4688_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4690_leafX = Pipeline(name = "x4690_leafX", parent=x4690, deps=List(x4688)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4690_unitcc = CounterChain.copy(x4690, "x4690_unitcc")
      var stage: List[Stage] = Nil
    }
    val x4673_0 = UnitPipeline(name = "x4673_0", parent=x4688, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4673_unitcc = CounterChain(name = "x4673_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x4686_mc.ofs)))
      Stage(stage(2), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(2), x4686_mc.len)))
    }
    val x4684_0 = Pipeline(name = "x4684_0", parent=x4688, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr23 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4675 = CounterChain(name = "x4675", ctr23)
      val x4476 = CounterChain.copy(x4664_0, "x4476")
      val x4469_x4678 = SRAM(size = 192, writeCtr = x4476(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4469_vector).rdAddr(x4675(0)).wtAddr(x4476(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4469_x4678.load), op=Bypass, results=List(CU.vecOut(stage(1), x4686_mc.vdata)))
    }
    
  }
}
