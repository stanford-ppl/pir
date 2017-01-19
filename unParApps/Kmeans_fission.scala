import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object Kmeans_fissionDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x5830_scalar = Scalar("x5830")
    val x5771_vector = Vector("x5771")
    val x5681_oc = OffChip("x5681")
    val x5834_scalar = Scalar("x5834")
    val x5774_vector = Vector("x5774")
    val x5945_vector = Vector("x5945")
    val bus_407_vector = Vector("bus_407")
    val x5910_vector = Vector("x5910")
    val x5870_vector = Vector("x5870")
    val x5723_x5839_addr_vector = Vector("x5723_x5839_addr")
    val x5682_oc = OffChip("x5682")
    val x5803_mc = MemoryController(TileLoad, x5681_oc)
    val x5977_mc = MemoryController(TileStore, x5682_oc)
    val x5749_mc = MemoryController(TileLoad, x5681_oc)
    val x5981 = Sequential(name = "x5981", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5981_unitcc = CounterChain(name = "x5981_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5770 = StreamController(name = "x5770", parent=x5981, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x5726 = CounterChain(name = "x5726", ctr1)
      var stage: List[Stage] = Nil
    }
    val x5745_0 = UnitPipeline(name = "x5745_0", parent=x5770, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr346 = CU.temp
      val tr345 = CU.temp
      val tr343 = CU.temp
      val tr342 = CU.temp
      val tr340 = CU.temp
      val tr336 = CU.temp
      val tr334 = CU.temp
      val x5726 = CounterChain.copy(x5770, "x5726")
      val x5745_unitcc = CounterChain(name = "x5745_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(9)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5726(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr334)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr334), Const("64i")), op=FixMod, results=List(CU.temp(stage(2), tr336)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr334), CU.temp(stage(2), tr336)), op=FixSub, results=List(CU.scalarOut(stage(3), x5749_mc.ofs)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr336), Const("96i")), op=FixAdd, results=List(CU.temp(stage(4), tr340)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr340), Const("64i")), op=FixMod, results=List(CU.temp(stage(5), tr342)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr340), CU.temp(stage(5), tr342)), op=FixSub, results=List(CU.temp(stage(6), tr343)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr342), Const("0i")), op=FixNeq, results=List(CU.temp(stage(7), tr345)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr345), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(8), tr346)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr343), CU.temp(stage(8), tr346)), op=FixAdd, results=List(CU.scalarOut(stage(9), x5749_mc.len)))
    }
    val x5944 = Sequential(name = "x5944", parent=x5981, deps=List(x5770)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("5i").out, Const("1i").out) // Counter
      val x5773 = CounterChain(name = "x5773", ctr5)
      var stage: List[Stage] = Nil
    }
    val x5909 = MetaPipeline(name = "x5909", parent=x5944, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("1536i").out, Const("20i").out) // Counter
      val x5776 = CounterChain(name = "x5776", ctr7)
      var stage: List[Stage] = Nil
    }
    val x5824 = StreamController(name = "x5824", parent=x5909, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x5780 = CounterChain(name = "x5780", ctr13)
      var stage: List[Stage] = Nil
    }
    val x5799_0 = UnitPipeline(name = "x5799_0", parent=x5824, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr371 = CU.temp
      val tr370 = CU.temp
      val tr368 = CU.temp
      val tr367 = CU.temp
      val tr365 = CU.temp
      val tr361 = CU.temp
      val tr359 = CU.temp
      val tr357 = CU.temp
      val x5799_unitcc = CounterChain(name = "x5799_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5776 = CounterChain.copy(x5909, "x5776")
      val x5780 = CounterChain.copy(x5824, "x5780")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5776(0)), CU.ctr(stage(0), x5780(0))), op=FixAdd, results=List(CU.temp(stage(1), tr357)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr357), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr359)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr359), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr361)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr359), CU.temp(stage(3), tr361)), op=FixSub, results=List(CU.scalarOut(stage(4), x5803_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr361), Const("96i")), op=FixAdd, results=List(CU.temp(stage(5), tr365)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr365), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr367)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr365), CU.temp(stage(6), tr367)), op=FixSub, results=List(CU.temp(stage(7), tr368)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr367), Const("0i")), op=FixNeq, results=List(CU.temp(stage(8), tr370)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr370), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(9), tr371)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr368), CU.temp(stage(9), tr371)), op=FixAdd, results=List(CU.scalarOut(stage(10), x5803_mc.len)))
    }
    val x5907 = MetaPipeline(name = "x5907", parent=x5909, deps=List(x5824)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x5829 = CounterChain(name = "x5829", ctr11, ctr12)
      val ctr9 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x5826 = CounterChain(name = "x5826", ctr9)
      var stage: List[Stage] = Nil
    }
    val x5869 = MetaPipeline(name = "x5869", parent=x5907, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x5833 = CounterChain(name = "x5833", ctr15)
      var stage: List[Stage] = Nil
    }
    val x5856 = StreamController(name = "x5856", parent=x5869, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr17 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x5836 = CounterChain(name = "x5836", ctr17)
      var stage: List[Stage] = Nil
    }
    val x5856_0 = StreamPipeline(name = "x5856_0", parent=x5856, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr391 = CU.temp
      val x5833 = CounterChain.copy(x5869, "x5833")
      val x5836 = CounterChain.copy(x5856, "x5836")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5833(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr391)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr391), CU.ctr(stage(1), x5836(0))), op=FixAdd, results=List(CU.vecOut(stage(2), x5723_x5839_addr_vector)))
    }
    val x5856_1 = StreamPipeline(name = "x5856_1", parent=x5856, deps=List(x5856_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr405 = CU.temp
      val tr404 = CU.temp
      val tr403 = CU.temp
      val tr399 = CU.temp
      val tr395 = CU.temp
      val tr389 = CU.temp
      val tr388 = CU.temp
      val x5829 = CounterChain.copy(x5907, "x5829")
      val x5833 = CounterChain.copy(x5869, "x5833")
      val x5773 = CounterChain.copy(x5944, "x5773")
      val x5826 = CounterChain.copy(x5907, "x5826")
      val x5836 = CounterChain.copy(x5856, "x5836")
      val x5926 = CounterChain.copy("x5942_0", "x5926")
      val x5771_x5842 = SRAM(size = 1920, writeCtr = x5926(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5771_vector)
      val x5723_x5839 = SemiFIFO(size = 1920, banking = Strided(1), buffering = SingleBuffer()).wtPort(x5749_mc.vdata)
      val x5777_x5845 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5829(0))).wtPort(x5803_mc.vdata)
      val x5723_x5839_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x5723_x5839_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5771_x5842))
      Stage(stage(1), operands=List(x5926(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr388)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr388), CU.ctr(stage(1), x5926(1))), op=FixAdd, results=List(x5771_x5842.writeAddr, CU.temp(stage(2), tr389)))
      stage = stage0 +: Stages(9)
      Stage(stage(1), operands=List(x5723_x5839_addr_fifo.load), op=Bypass, results=List(x5723_x5839.readAddr))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x5833(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr395)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr395), CU.ctr(stage(2), x5836(0))), op=FixAdd, results=List(x5771_x5842.readAddr))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x5826(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(4), tr399)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr399), CU.ctr(stage(4), x5836(0))), op=FixAdd, results=List(x5777_x5845.readAddr))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x5773(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(6), tr403)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr403), CU.load(stage(6), x5723_x5839), CU.load(stage(6), x5771_x5842)), op=Mux, results=List(CU.temp(stage(7), tr404)))
      Stage(stage(8), operands=List(CU.load(stage(7), x5777_x5845), CU.temp(stage(7), tr404)), op=FltSub, results=List(CU.temp(stage(8), tr405)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr405), CU.temp(stage(8), tr405)), op=FltMul, results=List(CU.vecOut(stage(9), bus_407_vector)))
    }
    val x5856_2 = StreamPipeline(name = "x5856_2", parent=x5856, deps=List(x5856_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5836 = CounterChain.copy(x5856, "x5836")
      val bus_407_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_407_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_407_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr409) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr409), op=Bypass, results=List(CU.scalarOut(stage(2), x5834_scalar)))
    }
    val x5867_0 = UnitPipeline(name = "x5867_0", parent=x5869, deps=List(x5856)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr417 = CU.temp
      val ar413 = CU.accum(init = Const("100000i"))
      val ar416 = CU.accum(init = Const("0i"))
      val x5833 = CounterChain.copy(x5869, "x5833")
      val x5867_unitcc = CounterChain(name = "x5867_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.accum(stage(1), ar413), CU.scalarIn(stage(0), x5834_scalar)), op=FltMin, results=List(CU.accum(stage(1), ar413)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar413), CU.scalarIn(stage(1), x5834_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr417)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr417), CU.ctr(stage(2), x5833(0)), CU.accum(stage(3), ar416)), op=Mux, results=List(CU.scalarOut(stage(3), x5830_scalar), CU.accum(stage(3), ar416)))
    }
    val x5888_0 = Pipeline(name = "x5888_0", parent=x5907, deps=List(x5869)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr431 = CU.temp
      val tr430 = CU.temp
      val tr428 = CU.temp
      val tr423 = CU.temp
      val x5829 = CounterChain.copy(x5907, "x5829")
      val x5826 = CounterChain.copy(x5907, "x5826")
      val ctr21 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr22 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x5873 = CounterChain(name = "x5873", ctr21, ctr22)
      val x5777_x5876 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5829(0))).wtPort(x5803_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5826(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr423)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr423), CU.ctr(stage(1), x5873(1))), op=FixAdd, results=List(x5777_x5876.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5873(1)), Const("95i")), op=FixEql, results=List(CU.temp(stage(3), tr428)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr428), Const("1i"), CU.load(stage(3), x5777_x5876)), op=Mux, results=List(CU.temp(stage(4), tr430)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x5873(0)), CU.scalarIn(stage(4), x5830_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr431)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr431), CU.temp(stage(5), tr430), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x5870_vector)))
    }
    val x5905_0 = Pipeline(name = "x5905_0", parent=x5907, deps=List(x5888_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr457 = CU.temp
      val tr446 = CU.temp
      val tr442 = CU.temp
      val tr440 = CU.temp
      val tr439 = CU.temp
      val x5873 = CounterChain.copy(x5888_0, "x5873")
      val ctr25 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr26 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x5829 = CounterChain(name = "x5829", ctr25, ctr26)
      val x5870_x5891 = SRAM(size = 1920, writeCtr = x5873(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5829(0), swapWrite = x5873(0))).wtPort(x5870_vector)
      val x5774_x5894 = SRAM(size = 1920, writeCtr = x5829(0), banking = Strided(1), buffering = SingleBuffer())
      val wr459 = CU.wtAddr(x5774_x5894)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5870_x5891))
      Stage(stage(1), operands=List(x5873(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr439)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr439), CU.ctr(stage(1), x5873(1))), op=FixAdd, results=List(x5870_x5891.writeAddr, CU.temp(stage(2), tr440)))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5829(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr442)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr442), CU.ctr(stage(1), x5829(1))), op=FixAdd, results=List(x5870_x5891.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5829(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr446)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr446), CU.ctr(stage(3), x5829(1))), op=FixAdd, results=List(x5774_x5894.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x5870_x5891), x5774_x5894.load), op=FltAdd, results=List(CU.vecOut(stage(5), x5774_vector), CU.store(stage(5), x5774_x5894)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x5829(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(6), tr457)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr457), CU.ctr(stage(6), x5829(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr459)))
    }
    val x5923_0 = Pipeline(name = "x5923_0", parent=x5944, deps=List(x5909)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr466 = CU.temp
      val tr464 = CU.temp
      val tr463 = CU.temp
      val x5829 = CounterChain.copy(x5907, "x5829")
      val ctr27 = (Const("0i").out, Const("20i").out, Const("16i").out) // Counter
      val x5912 = CounterChain(name = "x5912", ctr27)
      val x5774_x5915 = SRAM(size = 1920, writeCtr = x5829(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x5774_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5774_x5915))
      Stage(stage(1), operands=List(x5829(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr463)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr463), CU.ctr(stage(1), x5829(1))), op=FixAdd, results=List(x5774_x5915.writeAddr, CU.temp(stage(2), tr464)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5912(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr466)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr466), Const("95i")), op=FixAdd, results=List(x5774_x5915.readAddr))
      Stage(stage(3), operands=List(x5774_x5915.load), op=Bypass, results=List(CU.vecOut(stage(3), x5910_vector)))
    }
    val x5942_0 = Pipeline(name = "x5942_0", parent=x5944, deps=List(x5923_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr477 = CU.temp
      val tr474 = CU.temp
      val tr473 = CU.temp
      val x5829 = CounterChain.copy(x5907, "x5829")
      val x5912 = CounterChain.copy(x5923_0, "x5912")
      val ctr29 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr30 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x5926 = CounterChain(name = "x5926", ctr29, ctr30)
      val x5910_x5932 = SRAM(size = 20, writeCtr = x5912(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5910_vector).rdAddr(x5926(0)).wtAddr(x5912(0))
      val x5774_x5929 = SRAM(size = 1920, writeCtr = x5829(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5774_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5774_x5929))
      Stage(stage(1), operands=List(x5829(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr473)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr473), CU.ctr(stage(1), x5829(1))), op=FixAdd, results=List(x5774_x5929.writeAddr, CU.temp(stage(2), tr474)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5926(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr477)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr477), CU.ctr(stage(1), x5926(1))), op=FixAdd, results=List(x5774_x5929.readAddr))
      Stage(stage(3), operands=List(x5774_x5929.load, CU.load(stage(2), x5910_x5932)), op=FltDiv, results=List(CU.vecOut(stage(3), x5771_vector)))
    }
    val x5959_0 = Pipeline(name = "x5959_0", parent=x5981, deps=List(x5944)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr494 = CU.temp
      val tr490 = CU.temp
      val tr488 = CU.temp
      val tr487 = CU.temp
      val ctr33 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val ctr34 = (Const("0i").out, Const("96i").out, Const("16i").out) // Counter
      val x5948 = CounterChain(name = "x5948", ctr33, ctr34)
      val x5926 = CounterChain.copy("x5942_0", "x5926")
      val x5771_x5951 = SRAM(size = 1920, writeCtr = x5926(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5771_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5771_x5951))
      Stage(stage(1), operands=List(x5926(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr487)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr487), CU.ctr(stage(1), x5926(1))), op=FixAdd, results=List(x5771_x5951.writeAddr, CU.temp(stage(2), tr488)))
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5948(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr490)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr490), CU.ctr(stage(1), x5948(1))), op=FixAdd, results=List(x5771_x5951.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5948(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr494)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr494), CU.ctr(stage(3), x5948(1))), op=FixAdd, results=List())
      Stage(stage(5), operands=List(CU.load(stage(4), x5771_x5951)), op=Bypass, results=List(CU.vecOut(stage(5), x5945_vector)))
    }
    val x5979 = StreamController(name = "x5979", parent=x5981, deps=List(x5959_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5979_unitcc = CounterChain(name = "x5979_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5981_leafX = UnitPipeline(name = "x5981_leafX", parent=x5981, deps=List(x5979)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5981_unitcc = CounterChain.copy(x5981, "x5981_unitcc")
      var stage: List[Stage] = Nil
    }
    val x5964_0 = UnitPipeline(name = "x5964_0", parent=x5979, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x5964_unitcc = CounterChain(name = "x5964_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x5977_mc.ofs)))
      Stage(stage(2), operands=List(Const("1920i")), op=Bypass, results=List(CU.scalarOut(stage(2), x5977_mc.len)))
    }
    val x5975_0 = Pipeline(name = "x5975_0", parent=x5979, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr503 = CU.temp
      val tr502 = CU.temp
      val ctr37 = (Const("0i").out, Const("1920i").out, Const("16i").out) // Counter
      val x5966 = CounterChain(name = "x5966", ctr37)
      val x5948 = CounterChain.copy(x5959_0, "x5948")
      val x5945_x5969 = SRAM(size = 1920, writeCtr = x5948(0), banking = Diagonal(96,1), buffering = SingleBuffer()).wtPort(x5945_vector).rdAddr(x5966(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5945_x5969))
      Stage(stage(1), operands=List(x5948(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr502)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr502), CU.ctr(stage(1), x5948(1))), op=FixAdd, results=List(x5945_x5969.writeAddr, CU.temp(stage(2), tr503)))
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x5945_x5969.load), op=Bypass, results=List(CU.vecOut(stage(1), x5977_mc.vdata)))
    }
    
  }
}
