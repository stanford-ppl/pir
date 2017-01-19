import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object BlackScholesDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val bus_360_vector = Vector("bus_360")
    val bus_412_vector = Vector("bus_412")
    val bus_350_vector = Vector("bus_350")
    val x4154_oc = OffChip("x4154")
    val bus_395_vector = Vector("bus_395")
    val bus_418_vector = Vector("bus_418")
    val bus_384_vector = Vector("bus_384")
    val bus_354_vector = Vector("bus_354")
    val x4170_x4460_data_vector = Vector("x4170_x4460_data")
    val bus_342_vector = Vector("bus_342")
    val bus_349_vector = Vector("bus_349")
    val bus_391_vector = Vector("bus_391")
    val bus_425_vector = Vector("bus_425")
    val bus_377_vector = Vector("bus_377")
    val x4167_x4451_data_vector = Vector("x4167_x4451_data")
    val x4171_vector = Vector("x4171")
    val bus_361_vector = Vector("bus_361")
    val x4166_x4448_data_vector = Vector("x4166_x4448_data")
    val x4155_oc = OffChip("x4155")
    val bus_387_vector = Vector("bus_387")
    val bus_386_vector = Vector("bus_386")
    val x4168_x4454_data_vector = Vector("x4168_x4454_data")
    val bus_416_vector = Vector("bus_416")
    val x4153_oc = OffChip("x4153")
    val bus_407_vector = Vector("bus_407")
    val bus_347_vector = Vector("bus_347")
    val x4169_x4457_data_vector = Vector("x4169_x4457_data")
    val bus_339_vector = Vector("bus_339")
    val x4152_oc = OffChip("x4152")
    val x4151_oc = OffChip("x4151")
    val bus_345_vector = Vector("bus_345")
    val bus_343_vector = Vector("bus_343")
    val bus_394_vector = Vector("bus_394")
    val bus_385_vector = Vector("bus_385")
    val x4156_oc = OffChip("x4156")
    val x4150_oc = OffChip("x4150")
    val x4240_mc = MemoryController(TileLoad, x4151_oc)
    val x4375_mc = MemoryController(TileLoad, x4154_oc)
    val x4420_mc = MemoryController(TileLoad, x4155_oc)
    val x4330_mc = MemoryController(TileLoad, x4153_oc)
    val x4195_mc = MemoryController(TileLoad, x4150_oc)
    val x4285_mc = MemoryController(TileLoad, x4152_oc)
    val x4574_mc = MemoryController(TileStore, x4156_oc)
    val x4580 = Sequential(name = "x4580", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4580_unitcc = CounterChain(name = "x4580_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4578 = MetaPipeline(name = "x4578", parent=x4580, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("96000000i").out, Const("2000i").out) // Counter
      val x4164 = CounterChain(name = "x4164", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4216 = StreamController(name = "x4216", parent=x4578, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4216_unitcc = CounterChain(name = "x4216_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4191_0 = UnitPipeline(name = "x4191_0", parent=x4216, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr176 = CU.temp
      val tr175 = CU.temp
      val tr173 = CU.temp
      val tr172 = CU.temp
      val tr170 = CU.temp
      val tr165 = CU.temp
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4191_unitcc = CounterChain(name = "x4191_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr165)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4164(0)), CU.temp(stage(1), tr165)), op=FixSub, results=List(CU.scalarOut(stage(2), x4195_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr165), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr170)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr170), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr172)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr170), CU.temp(stage(4), tr172)), op=FixSub, results=List(CU.temp(stage(5), tr173)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr172), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr175)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr175), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr176)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr173), CU.temp(stage(7), tr176)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4195_mc.len)))
    }
    val x4261 = StreamController(name = "x4261", parent=x4578, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4261_unitcc = CounterChain(name = "x4261_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4236_0 = UnitPipeline(name = "x4236_0", parent=x4261, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr201 = CU.temp
      val tr200 = CU.temp
      val tr198 = CU.temp
      val tr197 = CU.temp
      val tr195 = CU.temp
      val tr190 = CU.temp
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4236_unitcc = CounterChain(name = "x4236_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr190)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4164(0)), CU.temp(stage(1), tr190)), op=FixSub, results=List(CU.scalarOut(stage(2), x4240_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr190), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr195)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr195), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr197)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr195), CU.temp(stage(4), tr197)), op=FixSub, results=List(CU.temp(stage(5), tr198)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr197), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr200)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr200), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr201)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr198), CU.temp(stage(7), tr201)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4240_mc.len)))
    }
    val x4306 = StreamController(name = "x4306", parent=x4578, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4306_unitcc = CounterChain(name = "x4306_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4281_0 = UnitPipeline(name = "x4281_0", parent=x4306, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr226 = CU.temp
      val tr225 = CU.temp
      val tr223 = CU.temp
      val tr222 = CU.temp
      val tr220 = CU.temp
      val tr215 = CU.temp
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4281_unitcc = CounterChain(name = "x4281_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr215)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4164(0)), CU.temp(stage(1), tr215)), op=FixSub, results=List(CU.scalarOut(stage(2), x4285_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr215), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr220)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr220), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr222)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr220), CU.temp(stage(4), tr222)), op=FixSub, results=List(CU.temp(stage(5), tr223)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr222), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr225)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr225), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr226)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr223), CU.temp(stage(7), tr226)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4285_mc.len)))
    }
    val x4351 = StreamController(name = "x4351", parent=x4578, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4351_unitcc = CounterChain(name = "x4351_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4326_0 = UnitPipeline(name = "x4326_0", parent=x4351, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr251 = CU.temp
      val tr250 = CU.temp
      val tr248 = CU.temp
      val tr247 = CU.temp
      val tr245 = CU.temp
      val tr240 = CU.temp
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4326_unitcc = CounterChain(name = "x4326_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr240)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4164(0)), CU.temp(stage(1), tr240)), op=FixSub, results=List(CU.scalarOut(stage(2), x4330_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr240), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr245)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr245), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr247)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr245), CU.temp(stage(4), tr247)), op=FixSub, results=List(CU.temp(stage(5), tr248)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr247), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr250)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr250), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr251)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr248), CU.temp(stage(7), tr251)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4330_mc.len)))
    }
    val x4396 = StreamController(name = "x4396", parent=x4578, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4396_unitcc = CounterChain(name = "x4396_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4371_0 = UnitPipeline(name = "x4371_0", parent=x4396, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr276 = CU.temp
      val tr275 = CU.temp
      val tr273 = CU.temp
      val tr272 = CU.temp
      val tr270 = CU.temp
      val tr265 = CU.temp
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4371_unitcc = CounterChain(name = "x4371_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr265)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4164(0)), CU.temp(stage(1), tr265)), op=FixSub, results=List(CU.scalarOut(stage(2), x4375_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr265), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr270)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr270), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr272)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr270), CU.temp(stage(4), tr272)), op=FixSub, results=List(CU.temp(stage(5), tr273)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr272), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr275)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr275), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr276)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr273), CU.temp(stage(7), tr276)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4375_mc.len)))
    }
    val x4441 = StreamController(name = "x4441", parent=x4578, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4441_unitcc = CounterChain(name = "x4441_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4416_0 = UnitPipeline(name = "x4416_0", parent=x4441, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr301 = CU.temp
      val tr300 = CU.temp
      val tr298 = CU.temp
      val tr297 = CU.temp
      val tr295 = CU.temp
      val tr290 = CU.temp
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4416_unitcc = CounterChain(name = "x4416_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr290)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4164(0)), CU.temp(stage(1), tr290)), op=FixSub, results=List(CU.scalarOut(stage(2), x4420_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr290), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr295)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr295), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr297)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr295), CU.temp(stage(4), tr297)), op=FixSub, results=List(CU.temp(stage(5), tr298)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr297), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr300)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr300), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr301)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr298), CU.temp(stage(7), tr301)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4420_mc.len)))
    }
    val x4556 = StreamController(name = "x4556", parent=x4578, deps=List(x4441, x4351, x4261, x4216, x4396, x4306)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("2000i").out, Const("1i").out) // Counter
      val x4445 = CounterChain(name = "x4445", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4556_0 = StreamPipeline(name = "x4556_0", parent=x4556, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4445 = CounterChain.copy(x4556, "x4445")
      val x4166_x4448 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4445(0))).wtPort(x4240_mc.vdata).rdAddr(x4445(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4166_x4448.load), op=Bypass, results=List(CU.vecOut(stage(1), x4166_x4448_data_vector)))
    }
    val x4556_1 = StreamPipeline(name = "x4556_1", parent=x4556, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4445 = CounterChain.copy(x4556, "x4445")
      val x4167_x4451 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4445(0))).wtPort(x4285_mc.vdata).rdAddr(x4445(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4167_x4451.load), op=Bypass, results=List(CU.vecOut(stage(1), x4167_x4451_data_vector)))
    }
    val x4556_2 = StreamPipeline(name = "x4556_2", parent=x4556, deps=List(x4556_0, x4556_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr338 = CU.temp
      val x4166_x4448_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4166_x4448_data_vector)
      val x4167_x4451_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4167_x4451_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4166_x4448_data_fifo.load, x4167_x4451_data_fifo.load), op=FltDiv, results=List(CU.temp(stage(1), tr338)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr338)), op=FltLog, results=List(CU.vecOut(stage(2), bus_339_vector)))
    }
    val x4556_3 = StreamPipeline(name = "x4556_3", parent=x4556, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4445 = CounterChain.copy(x4556, "x4445")
      val x4169_x4457 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4445(0))).wtPort(x4375_mc.vdata).rdAddr(x4445(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4169_x4457.load), op=Bypass, results=List(CU.vecOut(stage(1), x4169_x4457_data_vector)))
    }
    val x4556_4 = StreamPipeline(name = "x4556_4", parent=x4556, deps=List(x4556_3)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr341 = CU.temp
      val x4169_x4457_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4169_x4457_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4169_x4457_data_fifo.load, x4169_x4457_data_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr341)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr341), Const("0.5f")), op=FltMul, results=List(CU.vecOut(stage(2), bus_342_vector)))
    }
    val x4556_5 = StreamPipeline(name = "x4556_5", parent=x4556, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4445 = CounterChain.copy(x4556, "x4445")
      val x4168_x4454 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4445(0))).wtPort(x4330_mc.vdata).rdAddr(x4445(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4168_x4454.load), op=Bypass, results=List(CU.vecOut(stage(1), x4168_x4454_data_vector)))
    }
    val x4556_6 = StreamPipeline(name = "x4556_6", parent=x4556, deps=List(x4556_5, x4556_4)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4168_x4454_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4168_x4454_data_vector)
      val bus_342_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_342_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4168_x4454_data_fifo.load, bus_342_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(1), bus_343_vector)))
    }
    val x4556_7 = StreamPipeline(name = "x4556_7", parent=x4556, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4445 = CounterChain.copy(x4556, "x4445")
      val x4170_x4460 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4445(0))).wtPort(x4420_mc.vdata).rdAddr(x4445(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4170_x4460.load), op=Bypass, results=List(CU.vecOut(stage(1), x4170_x4460_data_vector)))
    }
    val x4556_8 = StreamPipeline(name = "x4556_8", parent=x4556, deps=List(x4556_6, x4556_7, x4556_2)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr344 = CU.temp
      val bus_343_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_343_vector)
      val x4170_x4460_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4170_x4460_data_vector)
      val bus_339_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_339_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_343_fifo.load, x4170_x4460_data_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr344)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr344), bus_339_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(2), bus_345_vector)))
    }
    val x4556_9 = StreamPipeline(name = "x4556_9", parent=x4556, deps=List(x4556_7, x4556_3)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr346 = CU.temp
      val x4170_x4460_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4170_x4460_data_vector)
      val x4169_x4457_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4169_x4457_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4170_x4460_data_fifo.load), op=FltSqrt, results=List(CU.temp(stage(1), tr346)))
      Stage(stage(2), operands=List(x4169_x4457_data_fifo.load, CU.temp(stage(1), tr346)), op=FltMul, results=List(CU.vecOut(stage(2), bus_347_vector)))
    }
    val x4556_10 = StreamPipeline(name = "x4556_10", parent=x4556, deps=List(x4556_9, x4556_8)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr348 = CU.temp
      val bus_347_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_347_vector)
      val bus_345_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_345_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_347_fifo.load, bus_347_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr348)))
      Stage(stage(2), operands=List(bus_345_fifo.load, CU.temp(stage(1), tr348)), op=FltDiv, results=List(CU.vecOut(stage(2), bus_349_vector)))
    }
    val x4556_11 = StreamPipeline(name = "x4556_11", parent=x4556, deps=List(x4556_10)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_349_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_349_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_349_fifo.load), op=FltAbs, results=List(CU.vecOut(stage(1), bus_350_vector)))
    }
    val x4556_12 = StreamPipeline(name = "x4556_12", parent=x4556, deps=List(x4556_11)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr353 = CU.temp
      val tr352 = CU.temp
      val tr351 = CU.temp
      val bus_350_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_350_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_350_fifo.load, bus_350_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr351)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr351), Const("-0.05f")), op=FltMul, results=List(CU.temp(stage(2), tr352)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr352)), op=FltExp, results=List(CU.temp(stage(3), tr353)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr353), Const("0.3989423f")), op=FltMul, results=List(CU.vecOut(stage(4), bus_354_vector)))
    }
    val x4556_13 = StreamPipeline(name = "x4556_13", parent=x4556, deps=List(x4556_11)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr359 = CU.temp
      val tr358 = CU.temp
      val bus_350_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_350_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(bus_350_fifo.load, Const("0.2316419f")), op=FltMul, results=List(CU.temp(stage(1), tr358)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr358), Const("1i")), op=FltAdd, results=List(CU.temp(stage(2), tr359)))
      Stage(stage(3), operands=List(Const("1i"), CU.temp(stage(2), tr359)), op=FltDiv, results=List(CU.vecOut(stage(3), bus_360_vector)))
    }
    val x4556_14 = StreamPipeline(name = "x4556_14", parent=x4556, deps=List(x4556_13)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_360_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_360_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_360_fifo.load, Const("0.31938154f")), op=FltMul, results=List(CU.vecOut(stage(1), bus_361_vector)))
    }
    val x4556_15 = StreamPipeline(name = "x4556_15", parent=x4556, deps=List(x4556_13, x4556_14)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr376 = CU.temp
      val tr375 = CU.temp
      val tr374 = CU.temp
      val tr373 = CU.temp
      val tr371 = CU.temp
      val tr369 = CU.temp
      val tr367 = CU.temp
      val tr366 = CU.temp
      val tr365 = CU.temp
      val tr364 = CU.temp
      val tr363 = CU.temp
      val bus_360_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_360_vector)
      val bus_361_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_361_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(bus_360_fifo.load, bus_360_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr363)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr363), bus_360_fifo.load), op=FltMul, results=List(CU.temp(stage(2), tr364)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr364), bus_360_fifo.load), op=FltMul, results=List(CU.temp(stage(3), tr365)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr365), bus_360_fifo.load), op=FltMul, results=List(CU.temp(stage(4), tr366)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr366), Const("1.3302745f")), op=FltMul, results=List(CU.temp(stage(5), tr367)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr365), Const("-1.8212559f")), op=FltMul, results=List(CU.temp(stage(6), tr369)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr363), Const("-0.35656378f")), op=FltMul, results=List(CU.temp(stage(7), tr371)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr364), Const("1.7814779f")), op=FltMul, results=List(CU.temp(stage(8), tr373)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr371), CU.temp(stage(8), tr373)), op=FltAdd, results=List(CU.temp(stage(9), tr374)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr374), CU.temp(stage(9), tr369)), op=FltAdd, results=List(CU.temp(stage(10), tr375)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr375), CU.temp(stage(10), tr367)), op=FltAdd, results=List(CU.temp(stage(11), tr376)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr376), bus_361_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(12), bus_377_vector)))
    }
    val x4556_16 = StreamPipeline(name = "x4556_16", parent=x4556, deps=List(x4556_15, x4556_12, x4556_10)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr383 = CU.temp
      val tr381 = CU.temp
      val tr380 = CU.temp
      val tr378 = CU.temp
      val bus_377_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_377_vector)
      val bus_354_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_354_vector)
      val bus_349_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_349_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(bus_377_fifo.load, bus_354_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr378)))
      Stage(stage(2), operands=List(Const("-1.0f"), CU.temp(stage(1), tr378)), op=FltMul, results=List(CU.temp(stage(2), tr380)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr380), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr381)))
      Stage(stage(4), operands=List(bus_349_fifo.load, Const("0i")), op=FltLt, results=List(CU.temp(stage(4), tr383)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr383), CU.temp(stage(4), tr378), CU.temp(stage(4), tr381)), op=Mux, results=List(CU.vecOut(stage(5), bus_384_vector)))
    }
    val x4556_17 = StreamPipeline(name = "x4556_17", parent=x4556, deps=List(x4556_0, x4556_16)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166_x4448_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4166_x4448_data_vector)
      val bus_384_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_384_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4166_x4448_data_fifo.load, bus_384_fifo.load), op=FltMul, results=List(CU.vecOut(stage(1), bus_385_vector)))
    }
    val x4556_18 = StreamPipeline(name = "x4556_18", parent=x4556, deps=List(x4556_10, x4556_9)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_349_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_349_vector)
      val bus_347_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_347_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_349_fifo.load, bus_347_fifo.load), op=FltSub, results=List(CU.vecOut(stage(1), bus_386_vector)))
    }
    val x4556_19 = StreamPipeline(name = "x4556_19", parent=x4556, deps=List(x4556_18)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_386_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_386_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_386_fifo.load), op=FltAbs, results=List(CU.vecOut(stage(1), bus_387_vector)))
    }
    val x4556_20 = StreamPipeline(name = "x4556_20", parent=x4556, deps=List(x4556_19)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr390 = CU.temp
      val tr389 = CU.temp
      val tr388 = CU.temp
      val bus_387_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_387_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_387_fifo.load, bus_387_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr388)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr388), Const("-0.05f")), op=FltMul, results=List(CU.temp(stage(2), tr389)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr389)), op=FltExp, results=List(CU.temp(stage(3), tr390)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr390), Const("0.3989423f")), op=FltMul, results=List(CU.vecOut(stage(4), bus_391_vector)))
    }
    val x4556_21 = StreamPipeline(name = "x4556_21", parent=x4556, deps=List(x4556_19)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr393 = CU.temp
      val tr392 = CU.temp
      val bus_387_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_387_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(bus_387_fifo.load, Const("0.2316419f")), op=FltMul, results=List(CU.temp(stage(1), tr392)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr392), Const("1i")), op=FltAdd, results=List(CU.temp(stage(2), tr393)))
      Stage(stage(3), operands=List(Const("1i"), CU.temp(stage(2), tr393)), op=FltDiv, results=List(CU.vecOut(stage(3), bus_394_vector)))
    }
    val x4556_22 = StreamPipeline(name = "x4556_22", parent=x4556, deps=List(x4556_21)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_394_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_394_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_394_fifo.load, Const("0.31938154f")), op=FltMul, results=List(CU.vecOut(stage(1), bus_395_vector)))
    }
    val x4556_23 = StreamPipeline(name = "x4556_23", parent=x4556, deps=List(x4556_21, x4556_22)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr406 = CU.temp
      val tr405 = CU.temp
      val tr404 = CU.temp
      val tr403 = CU.temp
      val tr402 = CU.temp
      val tr401 = CU.temp
      val tr400 = CU.temp
      val tr399 = CU.temp
      val tr398 = CU.temp
      val tr397 = CU.temp
      val tr396 = CU.temp
      val bus_394_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_394_vector)
      val bus_395_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_395_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(bus_394_fifo.load, bus_394_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr396)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr396), bus_394_fifo.load), op=FltMul, results=List(CU.temp(stage(2), tr397)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr397), bus_394_fifo.load), op=FltMul, results=List(CU.temp(stage(3), tr398)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr398), bus_394_fifo.load), op=FltMul, results=List(CU.temp(stage(4), tr399)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr399), Const("1.3302745f")), op=FltMul, results=List(CU.temp(stage(5), tr400)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr398), Const("-1.8212559f")), op=FltMul, results=List(CU.temp(stage(6), tr401)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr396), Const("-0.35656378f")), op=FltMul, results=List(CU.temp(stage(7), tr402)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr397), Const("1.7814779f")), op=FltMul, results=List(CU.temp(stage(8), tr403)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr402), CU.temp(stage(8), tr403)), op=FltAdd, results=List(CU.temp(stage(9), tr404)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr404), CU.temp(stage(9), tr401)), op=FltAdd, results=List(CU.temp(stage(10), tr405)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr405), CU.temp(stage(10), tr400)), op=FltAdd, results=List(CU.temp(stage(11), tr406)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr406), bus_395_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(12), bus_407_vector)))
    }
    val x4556_24 = StreamPipeline(name = "x4556_24", parent=x4556, deps=List(x4556_23, x4556_20, x4556_18)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr411 = CU.temp
      val tr410 = CU.temp
      val tr409 = CU.temp
      val tr408 = CU.temp
      val bus_407_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_407_vector)
      val bus_391_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_391_vector)
      val bus_386_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_386_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(bus_407_fifo.load, bus_391_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr408)))
      Stage(stage(2), operands=List(Const("-1.0f"), CU.temp(stage(1), tr408)), op=FltMul, results=List(CU.temp(stage(2), tr409)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr409), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr410)))
      Stage(stage(4), operands=List(bus_386_fifo.load, Const("0i")), op=FltLt, results=List(CU.temp(stage(4), tr411)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr411), CU.temp(stage(4), tr408), CU.temp(stage(4), tr410)), op=Mux, results=List(CU.vecOut(stage(5), bus_412_vector)))
    }
    val x4556_25 = StreamPipeline(name = "x4556_25", parent=x4556, deps=List(x4556_5, x4556_7, x4556_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr415 = CU.temp
      val tr414 = CU.temp
      val tr413 = CU.temp
      val x4168_x4454_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4168_x4454_data_vector)
      val x4170_x4460_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4170_x4460_data_vector)
      val x4167_x4451_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4167_x4451_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(Const("-1.0f"), x4168_x4454_data_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr413)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr413), x4170_x4460_data_fifo.load), op=FltMul, results=List(CU.temp(stage(2), tr414)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr414)), op=FltExp, results=List(CU.temp(stage(3), tr415)))
      Stage(stage(4), operands=List(x4167_x4451_data_fifo.load, CU.temp(stage(3), tr415)), op=FltMul, results=List(CU.vecOut(stage(4), bus_416_vector)))
    }
    val x4556_26 = StreamPipeline(name = "x4556_26", parent=x4556, deps=List(x4556_25, x4556_24, x4556_17)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr417 = CU.temp
      val bus_416_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_416_vector)
      val bus_412_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_412_vector)
      val bus_385_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_385_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_416_fifo.load, bus_412_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr417)))
      Stage(stage(2), operands=List(bus_385_fifo.load, CU.temp(stage(1), tr417)), op=FltSub, results=List(CU.vecOut(stage(2), bus_418_vector)))
    }
    val x4556_27 = StreamPipeline(name = "x4556_27", parent=x4556, deps=List(x4556_24, x4556_25, x4556_16, x4556_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr424 = CU.temp
      val tr423 = CU.temp
      val tr422 = CU.temp
      val tr421 = CU.temp
      val tr420 = CU.temp
      val tr419 = CU.temp
      val bus_412_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_412_vector)
      val bus_416_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_416_vector)
      val bus_384_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_384_vector)
      val x4166_x4448_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4166_x4448_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(Const("-1.0f"), bus_412_fifo.load), op=FltMul, results=List(CU.temp(stage(1), tr419)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr419), Const("1i")), op=FltAdd, results=List(CU.temp(stage(2), tr420)))
      Stage(stage(3), operands=List(bus_416_fifo.load, CU.temp(stage(2), tr420)), op=FltMul, results=List(CU.temp(stage(3), tr421)))
      Stage(stage(4), operands=List(Const("-1.0f"), bus_384_fifo.load), op=FltMul, results=List(CU.temp(stage(4), tr422)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr422), Const("1i")), op=FltAdd, results=List(CU.temp(stage(5), tr423)))
      Stage(stage(6), operands=List(x4166_x4448_data_fifo.load, CU.temp(stage(5), tr423)), op=FltMul, results=List(CU.temp(stage(6), tr424)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr421), CU.temp(stage(6), tr424)), op=FltSub, results=List(CU.vecOut(stage(7), bus_425_vector)))
    }
    val x4556_28 = StreamPipeline(name = "x4556_28", parent=x4556, deps=List(x4556_27, x4556_26)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr427 = CU.temp
      val x4445 = CounterChain.copy(x4556, "x4445")
      val x4165_x4463 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4445(0))).wtPort(x4195_mc.vdata).rdAddr(x4445(0))
      val bus_425_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_425_vector)
      val bus_418_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_418_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x4165_x4463.load, Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr427)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr427), bus_425_fifo.load, bus_418_fifo.load), op=Mux, results=List(CU.vecOut(stage(2), x4171_vector)))
    }
    val x4576 = StreamController(name = "x4576", parent=x4578, deps=List(x4556)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4576_unitcc = CounterChain(name = "x4576_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4561_0 = UnitPipeline(name = "x4561_0", parent=x4576, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4164 = CounterChain.copy(x4578, "x4164")
      val x4561_unitcc = CounterChain(name = "x4561_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4164(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x4574_mc.ofs)))
      Stage(stage(2), operands=List(Const("2000i")), op=Bypass, results=List(CU.scalarOut(stage(2), x4574_mc.len)))
    }
    val x4572_0 = Pipeline(name = "x4572_0", parent=x4576, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4445 = CounterChain.copy(x4556, "x4445")
      val ctr17 = (Const("0i").out, Const("2000i").out, Const("16i").out) // Counter
      val x4563 = CounterChain(name = "x4563", ctr17)
      val x4576_unitcc = CounterChain.copy(x4576, "x4576_unitcc")
      val x4171_x4566 = SRAM(size = 2000, writeCtr = x4445(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4576_unitcc(0), swapWrite = x4445(0))).wtPort(x4171_vector).rdAddr(x4563(0)).wtAddr(x4445(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4171_x4566.load), op=Bypass, results=List(CU.vecOut(stage(1), x4574_mc.vdata)))
    }
    
  }
}
