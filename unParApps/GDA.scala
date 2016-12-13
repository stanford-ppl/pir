import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object GDADesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3696_x3811_addr_vector = Vector("x3696_x3811_addr")
    val x3798_vector = Vector("x3798")
    val x3552_oc = OffChip("x3552")
    val x3554_oc = OffChip("x3554")
    val x3688_vector = Vector("x3688")
    val x3546_argin = ArgIn("x3546")
    val x3556_oc = OffChip("x3556")
    val x3805_vector = Vector("x3805")
    val x3697_scalar = Scalar("x3697")
    val bus_376_vector = Vector("bus_376")
    val x3806_vector = Vector("x3806")
    val x3553_oc = OffChip("x3553")
    val x3555_oc = OffChip("x3555")
    val x3665_mc = MemoryController(TileLoad, x3555_oc)
    val x3721_mc = MemoryController(TileLoad, x3553_oc)
    val x3911_mc = MemoryController(TileStore, x3556_oc)
    val x3623_mc = MemoryController(TileLoad, x3554_oc)
    val x3768_mc = MemoryController(TileLoad, x3552_oc)
    val x3915 = Sequential(name = "x3915", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3915_unitcc = CounterChain(name = "x3915_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3643 = StreamController(name = "x3643", parent=x3915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3643_unitcc = CounterChain(name = "x3643_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3619_0 = UnitPipeline(name = "x3619_0", parent=x3643, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr264 = CU.temp
      val tr263 = CU.temp
      val tr262 = CU.temp
      val tr261 = CU.temp
      val x3619_unitcc = CounterChain(name = "x3619_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(Const("192i"), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr261)))
      Stage(stage(2), operands=List(Const("192i"), CU.temp(stage(1), tr261)), op=FixSub, results=List(CU.temp(stage(2), tr262)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr261), Const("0i")), op=FixNeq, results=List(CU.temp(stage(3), tr263)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr263), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr264)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr262), CU.temp(stage(4), tr264)), op=FixAdd, results=List(CU.scalarOut(stage(5), x3623_mc.len)))
      Stage(stage(6), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3623_mc.ofs)))
    }
    val x3685 = StreamController(name = "x3685", parent=x3915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3685_unitcc = CounterChain(name = "x3685_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3661_0 = UnitPipeline(name = "x3661_0", parent=x3685, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr285 = CU.temp
      val tr284 = CU.temp
      val tr283 = CU.temp
      val tr282 = CU.temp
      val x3661_unitcc = CounterChain(name = "x3661_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(Const("192i"), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr282)))
      Stage(stage(2), operands=List(Const("192i"), CU.temp(stage(1), tr282)), op=FixSub, results=List(CU.temp(stage(2), tr283)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr282), Const("0i")), op=FixNeq, results=List(CU.temp(stage(3), tr284)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr284), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr285)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr283), CU.temp(stage(4), tr285)), op=FixAdd, results=List(CU.scalarOut(stage(5), x3665_mc.len)))
      Stage(stage(6), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3665_mc.ofs)))
    }
    val x3891 = MetaPipeline(name = "x3891", parent=x3915, deps=List(x3643, x3685)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x3546_argin).out, Const("20i").out) // Counter
      val x3691 = CounterChain(name = "x3691", ctr3)
      val ctr5 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val ctr6 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3694 = CounterChain(name = "x3694", ctr5, ctr6)
      var stage: List[Stage] = Nil
    }
    val x3742 = StreamController(name = "x3742", parent=x3891, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3742_unitcc = CounterChain(name = "x3742_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3717_0 = UnitPipeline(name = "x3717_0", parent=x3742, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr311 = CU.temp
      val tr310 = CU.temp
      val tr308 = CU.temp
      val tr307 = CU.temp
      val tr305 = CU.temp
      val tr300 = CU.temp
      val x3717_unitcc = CounterChain(name = "x3717_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x3691 = CounterChain.copy(x3891, "x3691")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3691(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr300)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3691(0)), CU.temp(stage(1), tr300)), op=FixSub, results=List(CU.scalarOut(stage(2), x3721_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr300), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr305)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr305), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr307)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr305), CU.temp(stage(4), tr307)), op=FixSub, results=List(CU.temp(stage(5), tr308)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr307), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr310)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr310), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr311)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr308), CU.temp(stage(7), tr311)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3721_mc.len)))
    }
    val x3789 = StreamController(name = "x3789", parent=x3891, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x3745 = CounterChain(name = "x3745", ctr11)
      var stage: List[Stage] = Nil
    }
    val x3764_0 = UnitPipeline(name = "x3764_0", parent=x3789, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr338 = CU.temp
      val tr337 = CU.temp
      val tr335 = CU.temp
      val tr334 = CU.temp
      val tr332 = CU.temp
      val tr328 = CU.temp
      val tr327 = CU.temp
      val tr325 = CU.temp
      val x3745 = CounterChain.copy(x3789, "x3745")
      val x3764_unitcc = CounterChain(name = "x3764_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x3691 = CounterChain.copy(x3891, "x3691")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3691(0)), CU.ctr(stage(0), x3745(0))), op=FixAdd, results=List(CU.temp(stage(1), tr325)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr325), Const("192i")), op=FixMul, results=List(CU.temp(stage(2), tr327)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr327), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr328)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr327), CU.temp(stage(3), tr328)), op=FixSub, results=List(CU.scalarOut(stage(4), x3768_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr328), Const("192i")), op=FixAdd, results=List(CU.temp(stage(5), tr332)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr332), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr334)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr332), CU.temp(stage(6), tr334)), op=FixSub, results=List(CU.temp(stage(7), tr335)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr334), Const("0i")), op=FixNeq, results=List(CU.temp(stage(8), tr337)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr337), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(9), tr338)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr335), CU.temp(stage(9), tr338)), op=FixAdd, results=List(CU.scalarOut(stage(10), x3768_mc.len)))
    }
    val x3795_0 = UnitPipeline(name = "x3795_0", parent=x3891, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr352 = CU.temp
      val x3691 = CounterChain.copy(x3891, "x3691")
      val x3795_unitcc = CounterChain(name = "x3795_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3546_argin), CU.ctr(stage(0), x3691(0))), op=FixSub, results=List(CU.temp(stage(1), tr352)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr352), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x3697_scalar)))
    }
    val x3872 = MetaPipeline(name = "x3872", parent=x3891, deps=List(x3742, x3789, x3795_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3804 = CounterChain(name = "x3804", ctr9, ctr10)
      val ctr7 = (Const("0i").out, CU.scalarIn(stage0, x3697_scalar).out, Const("1i").out) // Counter
      val x3801 = CounterChain(name = "x3801", ctr7)
      var stage: List[Stage] = Nil
    }
    val x3834 = StreamController(name = "x3834", parent=x3872, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3808 = CounterChain(name = "x3808", ctr13)
      var stage: List[Stage] = Nil
    }
    val x3834_0 = StreamPipeline(name = "x3834_0", parent=x3834, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr365 = CU.temp
      val x3808 = CounterChain.copy(x3834, "x3808")
      val x3801 = CounterChain.copy(x3872, "x3801")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3801(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr365)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr365), CU.ctr(stage(1), x3808(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x3696_x3811_addr_vector)))
    }
    val x3834_1 = StreamPipeline(name = "x3834_1", parent=x3834, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr375 = CU.temp
      val x3808 = CounterChain.copy(x3834, "x3808")
      val x3804 = CounterChain.copy(x3872, "x3804")
      val x3801 = CounterChain.copy(x3872, "x3801")
      val x3600_x3820 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x3623_mc.vdata).rdAddr(x3808(0))
      val x3695_x3814 = SemiFIFO(size = 20, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3804(0))).wtPort(x3721_mc.vdata).rdAddr(x3801(0))
      val x3601_x3817 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x3665_mc.vdata).rdAddr(x3808(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3695_x3814.load, Const("1i")), op=FixEql, results=List(CU.temp(stage(1), tr375)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr375), CU.load(stage(1), x3601_x3817), CU.load(stage(1), x3600_x3820)), op=Mux, results=List(CU.vecOut(stage(2), bus_376_vector)))
    }
    val x3834_2 = StreamPipeline(name = "x3834_2", parent=x3834, deps=List(x3834_0, x3834_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3808 = CounterChain.copy(x3834, "x3808")
      val x3804 = CounterChain.copy(x3872, "x3804")
      val x3696_x3811 = SemiFIFO(size = 3840, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3804(0))).wtPort(x3768_mc.vdata)
      val x3696_x3811_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x3696_x3811_addr_vector)
      val bus_376_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_376_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3696_x3811_addr_fifo.load), op=Bypass, results=List(x3696_x3811.readAddr))
      Stage(stage(2), operands=List(x3696_x3811.load, bus_376_fifo.load), op=FltSub, results=List(CU.vecOut(stage(2), x3805_vector)))
    }
    val x3853_0 = Pipeline(name = "x3853_0", parent=x3872, deps=List(x3834)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3808 = CounterChain.copy(x3834, "x3808")
      val ctr21 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val ctr22 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x3837 = CounterChain(name = "x3837", ctr21, ctr22)
      val x3805_x3840 = SRAM(size = 192, writeCtr = x3808(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3837(0), swapWrite = x3808(0))).wtPort(x3805_vector).rdAddr(x3837(0)).wtAddr(x3808(0))
      val x3805_x3843 = SRAM(size = 192, writeCtr = x3808(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3837(0), swapWrite = x3808(0))).wtPort(x3805_vector).rdAddr(x3837(1)).wtAddr(x3808(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3805_x3840.load, x3805_x3843.load), op=FltMul, results=List(CU.vecOut(stage(1), x3806_vector)))
    }
    val x3870_0 = Pipeline(name = "x3870_0", parent=x3872, deps=List(x3853_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr409 = CU.temp
      val tr399 = CU.temp
      val tr395 = CU.temp
      val tr393 = CU.temp
      val tr392 = CU.temp
      val ctr25 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val ctr26 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x3804 = CounterChain(name = "x3804", ctr25, ctr26)
      val x3837 = CounterChain.copy(x3853_0, "x3837")
      val x3806_x3856 = SRAM(size = 36864, writeCtr = x3837(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3804(0), swapWrite = x3837(0))).wtPort(x3806_vector)
      val x3798_x3859 = SRAM(size = 36864, writeCtr = x3804(0), banking = Strided(1), buffering = SingleBuffer())
      val wr411 = CU.wtAddr(x3798_x3859)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3806_x3856))
      Stage(stage(1), operands=List(x3837(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr392)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr392), CU.ctr(stage(1), x3837(1))), op=FixAdd, results=List(x3806_x3856.writeAddr, CU.temp(stage(2), tr393)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3804(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr395)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr395), CU.ctr(stage(1), x3804(1))), op=FixAdd, results=List(x3806_x3856.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x3804(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(3), tr399)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr399), CU.ctr(stage(3), x3804(1))), op=FixAdd, results=List(x3798_x3859.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x3806_x3856), x3798_x3859.load), op=FltAdd, results=List(CU.vecOut(stage(5), x3798_vector), CU.store(stage(5), x3798_x3859)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x3804(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(6), tr409)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr409), CU.ctr(stage(6), x3804(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr411)))
    }
    val x3889_0 = Pipeline(name = "x3889_0", parent=x3891, deps=List(x3872)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr429 = CU.temp
      val tr421 = CU.temp
      val tr417 = CU.temp
      val tr415 = CU.temp
      val tr414 = CU.temp
      val x3804 = CounterChain.copy(x3870_0, "x3804")
      val ctr27 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val ctr28 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x3694 = CounterChain(name = "x3694", ctr27, ctr28)
      val x3798_x3875 = SRAM(size = 36864, writeCtr = x3804(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x3694(0), swapWrite = x3804(0))).wtPort(x3798_vector)
      val x3688_x3878 = SRAM(size = 36864, writeCtr = x3694(0), banking = Strided(1), buffering = SingleBuffer())
      val wr431 = CU.wtAddr(x3688_x3878)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3798_x3875))
      Stage(stage(1), operands=List(x3804(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr414)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr414), CU.ctr(stage(1), x3804(1))), op=FixAdd, results=List(x3798_x3875.writeAddr, CU.temp(stage(2), tr415)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3694(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr417)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr417), CU.ctr(stage(1), x3694(1))), op=FixAdd, results=List(x3798_x3875.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x3694(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(3), tr421)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr421), CU.ctr(stage(3), x3694(1))), op=FixAdd, results=List(x3688_x3878.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x3798_x3875), x3688_x3878.load), op=FltAdd, results=List(CU.vecOut(stage(5), x3688_vector), CU.store(stage(5), x3688_x3878)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x3694(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(6), tr429)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr429), CU.ctr(stage(6), x3694(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr431)))
    }
    val x3913 = StreamController(name = "x3913", parent=x3915, deps=List(x3891)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr29 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x3894 = CounterChain(name = "x3894", ctr29)
      var stage: List[Stage] = Nil
    }
    val x3915_leafX = Pipeline(name = "x3915_leafX", parent=x3915, deps=List(x3913)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3915_unitcc = CounterChain.copy(x3915, "x3915_unitcc")
      var stage: List[Stage] = Nil
    }
    val x3898_0 = UnitPipeline(name = "x3898_0", parent=x3913, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3894 = CounterChain.copy(x3913, "x3894")
      val x3898_unitcc = CounterChain(name = "x3898_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3894(0)), Const("192i")), op=FixMul, results=List(CU.scalarOut(stage(1), x3911_mc.ofs)))
      Stage(stage(2), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(2), x3911_mc.len)))
    }
    val x3909_0 = Pipeline(name = "x3909_0", parent=x3913, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr441 = CU.temp
      val tr439 = CU.temp
      val tr438 = CU.temp
      val x3694 = CounterChain.copy(x3891, "x3694")
      val ctr31 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x3900 = CounterChain(name = "x3900", ctr31)
      val x3894 = CounterChain.copy(x3913, "x3894")
      val x3688_x3903 = SRAM(size = 36864, writeCtr = x3694(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x3688_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3688_x3903))
      Stage(stage(1), operands=List(x3694(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr438)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr438), CU.ctr(stage(1), x3694(1))), op=FixAdd, results=List(x3688_x3903.writeAddr, CU.temp(stage(2), tr439)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3894(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr441)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr441), CU.ctr(stage(1), x3900(0))), op=FixAdd, results=List(x3688_x3903.readAddr))
      Stage(stage(3), operands=List(x3688_x3903.load), op=Bypass, results=List(CU.vecOut(stage(3), x3911_mc.vdata)))
    }
    
  }
}
