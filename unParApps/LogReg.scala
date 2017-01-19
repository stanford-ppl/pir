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
    val x4426_vector = Vector("x4426")
    val x4338_oc = OffChip("x4338")
    val x4529_scalar = Scalar("x4529")
    val x4531_scalar = Scalar("x4531")
    val x4339_oc = OffChip("x4339")
    val x4337_oc = OffChip("x4337")
    val x4419_vector = Vector("x4419")
    val x4530_vector = Vector("x4530")
    val x4501_mc = MemoryController(TileLoad, x4338_oc)
    val x4456_mc = MemoryController(TileLoad, x4337_oc)
    val x4634_mc = MemoryController(TileStore, x4339_oc)
    val x4638 = Sequential(name = "x4638", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4638_unitcc = CounterChain(name = "x4638_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4616 = Sequential(name = "x4616", parent=x4638, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("5i").out, Const("1i").out) // Counter
      val x4421 = CounterChain(name = "x4421", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4614 = Sequential(name = "x4614", parent=x4616, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x4423 = CounterChain(name = "x4423", ctr3)
      val ctr5 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4425 = CounterChain(name = "x4425", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4594 = MetaPipeline(name = "x4594", parent=x4614, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("384000i").out, Const("10i").out) // Counter
      val x4428 = CounterChain(name = "x4428", ctr6)
      var stage: List[Stage] = Nil
    }
    val x4477 = StreamController(name = "x4477", parent=x4594, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("10i").out, Const("1i").out) // Counter
      val x4433 = CounterChain(name = "x4433", ctr8)
      var stage: List[Stage] = Nil
    }
    val x4452_0 = UnitPipeline(name = "x4452_0", parent=x4477, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr278 = CU.temp
      val tr277 = CU.temp
      val tr275 = CU.temp
      val tr274 = CU.temp
      val tr272 = CU.temp
      val tr268 = CU.temp
      val tr266 = CU.temp
      val tr264 = CU.temp
      val x4433 = CounterChain.copy(x4477, "x4433")
      val x4452_unitcc = CounterChain(name = "x4452_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4428 = CounterChain.copy(x4594, "x4428")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4428(0)), CU.ctr(stage(0), x4433(0))), op=FixAdd, results=List(CU.temp(stage(1), tr264)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr264), Const("192i")), op=FixMul, results=List(CU.temp(stage(2), tr266)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr266), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr268)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr266), CU.temp(stage(3), tr268)), op=FixSub, results=List(CU.scalarOut(stage(4), x4456_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr268), Const("192i")), op=FixAdd, results=List(CU.temp(stage(5), tr272)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr272), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr274)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr272), CU.temp(stage(6), tr274)), op=FixSub, results=List(CU.temp(stage(7), tr275)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr274), Const("0i")), op=FixNeq, results=List(CU.temp(stage(8), tr277)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr277), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(9), tr278)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr275), CU.temp(stage(9), tr278)), op=FixAdd, results=List(CU.scalarOut(stage(10), x4456_mc.len)))
    }
    val x4522 = StreamController(name = "x4522", parent=x4594, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4522_unitcc = CounterChain(name = "x4522_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4497_0 = UnitPipeline(name = "x4497_0", parent=x4522, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr304 = CU.temp
      val tr303 = CU.temp
      val tr301 = CU.temp
      val tr300 = CU.temp
      val tr298 = CU.temp
      val tr293 = CU.temp
      val x4497_unitcc = CounterChain(name = "x4497_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4428 = CounterChain.copy(x4594, "x4428")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4428(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr293)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4428(0)), CU.temp(stage(1), tr293)), op=FixSub, results=List(CU.scalarOut(stage(2), x4501_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr293), Const("10i")), op=FixAdd, results=List(CU.temp(stage(3), tr298)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr298), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr300)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr298), CU.temp(stage(4), tr300)), op=FixSub, results=List(CU.temp(stage(5), tr301)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr300), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr303)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr303), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr304)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr301), CU.temp(stage(7), tr304)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4501_mc.len)))
    }
    val x4592 = MetaPipeline(name = "x4592", parent=x4594, deps=List(x4477, x4522)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("10i").out, Const("1i").out) // Counter
      val x4526 = CounterChain(name = "x4526", ctr12)
      val ctr14 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4528 = CounterChain(name = "x4528", ctr14)
      var stage: List[Stage] = Nil
    }
    val x4547_0 = Pipeline(name = "x4547_0", parent=x4592, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr320 = CU.temp
      val ctr15 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4533 = CounterChain(name = "x4533", ctr15)
      val x4425 = CounterChain.copy(x4614, "x4425")
      val x4526 = CounterChain.copy(x4592, "x4526")
      val x4429_x4536 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4526(0))).wtPort(x4456_mc.vdata)
      val x4419_x4539 = SRAM(size = 192, writeCtr = x4425(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4419_vector).rdAddr(x4533(0)).wtAddr(x4425(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4526(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr320)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr320), CU.ctr(stage(1), x4533(0))), op=FixAdd, results=List(x4429_x4536.readAddr))
      Stage(stage(3), operands=List(x4429_x4536.load, CU.load(stage(2), x4419_x4539)), op=FltMul, results=List(CU.reduce(stage(3))))
      val (rs1, rr329) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(4), operands=List(rr329), op=Bypass, results=List(CU.scalarOut(stage(4), x4531_scalar)))
    }
    val x4558_0 = UnitPipeline(name = "x4558_0", parent=x4592, deps=List(x4547_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr341 = CU.temp
      val tr340 = CU.temp
      val tr338 = CU.temp
      val tr337 = CU.temp
      val x4558_unitcc = CounterChain(name = "x4558_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x4526 = CounterChain.copy(x4592, "x4526")
      val x4430_x4549 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4526(0))).wtPort(x4501_mc.vdata).rdAddr(x4526(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x4531_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr337)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr337)), op=FltExp, results=List(CU.temp(stage(2), tr338)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr338), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr340)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr340)), op=FltDiv, results=List(CU.temp(stage(4), tr341)))
      Stage(stage(5), operands=List(CU.load(stage(4), x4430_x4549), CU.temp(stage(4), tr341)), op=FltSub, results=List(CU.scalarOut(stage(5), x4529_scalar)))
    }
    val x4573_0 = Pipeline(name = "x4573_0", parent=x4592, deps=List(x4558_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr347 = CU.temp
      val ctr17 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4560 = CounterChain(name = "x4560", ctr17)
      val x4526 = CounterChain.copy(x4592, "x4526")
      val x4429_x4563 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4526(0))).wtPort(x4456_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4526(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr347)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr347), CU.ctr(stage(1), x4560(0))), op=FixAdd, results=List(x4429_x4563.readAddr))
      Stage(stage(3), operands=List(x4429_x4563.load, CU.scalarIn(stage(2), x4529_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x4530_vector)))
    }
    val x4590_0 = Pipeline(name = "x4590_0", parent=x4592, deps=List(x4573_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4560 = CounterChain.copy(x4573_0, "x4560")
      val ctr21 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4528 = CounterChain(name = "x4528", ctr21)
      val x4530_x4576 = SRAM(size = 192, writeCtr = x4560(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4528(0), swapWrite = x4560(0))).wtPort(x4530_vector).rdAddr(x4528(0)).wtAddr(x4560(0))
      val x4426_x4579 = SRAM(size = 192, writeCtr = x4528(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x4528(0))
      val wr369 = CU.wtAddr(x4426_x4579)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4530_x4576.load, x4426_x4579.load), op=FltAdd, results=List(CU.vecOut(stage(1), x4426_vector), CU.store(stage(1), x4426_x4579)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4528(0))), op=Bypass, results=List(CU.wtAddr(stage(2), wr369)))
    }
    val x4612_0 = Pipeline(name = "x4612_0", parent=x4614, deps=List(x4594)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr382 = CU.temp
      val x4528 = CounterChain.copy(x4592, "x4528")
      val ctr22 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4425 = CounterChain(name = "x4425", ctr22)
      val x4426_x4597 = SRAM(size = 192, writeCtr = x4528(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4426_vector).rdAddr(x4425(0)).wtAddr(x4528(0))
      val x4419_x4600 = SRAM(size = 192, writeCtr = x4425(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x4425(0))
      val wr384 = CU.wtAddr(x4419_x4600)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x4419_x4600.load, Const("1i")), op=FltMul, results=List(CU.temp(stage(1), tr382)))
      Stage(stage(2), operands=List(CU.load(stage(1), x4426_x4597), CU.temp(stage(1), tr382)), op=FltAdd, results=List(CU.vecOut(stage(2), x4419_vector), CU.store(stage(2), x4419_x4600)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x4425(0))), op=Bypass, results=List(CU.wtAddr(stage(3), wr384)))
    }
    val x4636 = StreamController(name = "x4636", parent=x4638, deps=List(x4616)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4636_unitcc = CounterChain(name = "x4636_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4621_0 = UnitPipeline(name = "x4621_0", parent=x4636, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4621_unitcc = CounterChain(name = "x4621_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x4634_mc.ofs)))
      Stage(stage(2), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(2), x4634_mc.len)))
    }
    val x4632_0 = Pipeline(name = "x4632_0", parent=x4636, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4425 = CounterChain.copy(x4614, "x4425")
      val ctr23 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x4623 = CounterChain(name = "x4623", ctr23)
      val x4419_x4626 = SRAM(size = 192, writeCtr = x4425(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x4419_vector).rdAddr(x4623(0)).wtAddr(x4425(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4419_x4626.load), op=Bypass, results=List(CU.vecOut(stage(1), x4634_mc.vdata)))
    }
    
  }
}
