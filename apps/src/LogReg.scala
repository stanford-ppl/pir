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
    val bus_4745_scalar = Scalar("bus_4745")
    val x5676_scalar = Scalar("x5676")
    val bus_4881_vector = Vector("bus_4881")
    val x5716_scalar = Scalar("x5716")
    val x5717_scalar = Scalar("x5717")
    val x5686_scalar = Scalar("x5686")
    val bus_5502_vector = Vector("bus_5502")
    val bus_4825_vector = Vector("bus_4825")
    val bus_5503_vector = Vector("bus_5503")
    val x5694_vector = Vector("x5694")
    val x5710_scalar = Scalar("x5710")
    val x5718_scalar = Scalar("x5718")
    val bus_4965_vector = Vector("bus_4965")
    val x5700_vector = Vector("x5700")
    val bus_4811_vector = Vector("bus_4811")
    val bus_4979_vector = Vector("bus_4979")
    val x5683_scalar = Scalar("x5683")
    val x5681_scalar = Scalar("x5681")
    val x5572_vector = Vector("x5572")
    val x5695_vector = Vector("x5695")
    val x5697_vector = Vector("x5697")
    val x5684_scalar = Scalar("x5684")
    val bus_4923_vector = Vector("bus_4923")
    val bus_4951_vector = Vector("bus_4951")
    val x5696_vector = Vector("x5696")
    val x5720_scalar = Scalar("x5720")
    val x5688_scalar = Scalar("x5688")
    val x5679_scalar = Scalar("x5679")
    val x5708_scalar = Scalar("x5708")
    val bus_4895_vector = Vector("bus_4895")
    val bus_4867_vector = Vector("bus_4867")
    val x5719_scalar = Scalar("x5719")
    val bus_5525_vector = Vector("bus_5525")
    val x5703_vector = Vector("x5703")
    val x5702_vector = Vector("x5702")
    val x5715_scalar = Scalar("x5715")
    val x5691_vector = Vector("x5691")
    val x5680_scalar = Scalar("x5680")
    val x5714_scalar = Scalar("x5714")
    val x5699_vector = Vector("x5699")
    val bus_5514_vector = Vector("bus_5514")
    val x5692_vector = Vector("x5692")
    val x5678_scalar = Scalar("x5678")
    val x5721_scalar = Scalar("x5721")
    val bus_5491_vector = Vector("bus_5491")
    val bus_4853_vector = Vector("bus_4853")
    val x5713_scalar = Scalar("x5713")
    val x5689_scalar = Scalar("x5689")
    val bus_4747_scalar = Scalar("bus_4747")
    val x5705_vector = Vector("x5705")
    val x5722_scalar = Scalar("x5722")
    val x5685_scalar = Scalar("x5685")
    val bus_5007_vector = Vector("bus_5007")
    val x5701_vector = Vector("x5701")
    val x5707_scalar = Scalar("x5707")
    val x5693_vector = Vector("x5693")
    val x5698_vector = Vector("x5698")
    val x5709_scalar = Scalar("x5709")
    val x5690_scalar = Scalar("x5690")
    val x5711_scalar = Scalar("x5711")
    val x5712_scalar = Scalar("x5712")
    val bus_5508_vector = Vector("bus_5508")
    val x5483_oc = OffChip("x5483")
    val bus_4993_vector = Vector("bus_4993")
    val x5704_vector = Vector("x5704")
    val x5485_oc = OffChip("x5485")
    val x5706_vector = Vector("x5706")
    val x5677_scalar = Scalar("x5677")
    val x5687_scalar = Scalar("x5687")
    val x5484_oc = OffChip("x5484")
    val bus_4797_vector = Vector("bus_4797")
    val bus_4839_vector = Vector("bus_4839")
    val x5565_vector = Vector("x5565")
    val x5675_scalar = Scalar("x5675")
    val x5682_scalar = Scalar("x5682")
    val bus_4937_vector = Vector("bus_4937")
    val bus_4909_vector = Vector("bus_4909")
    val x5647_mc = MemoryController(TileLoad, x5484_oc)
    val x6551_mc = MemoryController(TileStore, x5485_oc)
    val x5602_mc = MemoryController(TileLoad, x5483_oc)
    val x6555 = Sequential(name = "x6555", parent=top, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6555_unitcc = CounterChain(name = "x6555_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6533 = Sequential(name = "x6533", parent=x6555, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("5i").out, Const("1i").out) // Counter
      val x5567 = CounterChain(name = "x5567", ctr1)
      var stage: List[Stage] = Nil
    }
    val x6531 = Sequential(name = "x6531", parent=x6533, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5571 = CounterChain(name = "x5571", ctr5)
      var stage: List[Stage] = Nil
    }
    val x6511 = MetaPipeline(name = "x6511", parent=x6531, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const(s"${384000/16}i").out, Const("10i").out) // Counter
      val x5574 = CounterChain(name = "x5574", ctr6)
      var stage: List[Stage] = Nil
    }
    val x5623 = MetaPipeline(name = "x5623", parent=x6511, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("10i").out, Const("1i").out) // Counter
      val x5579 = CounterChain(name = "x5579", ctr8)
      var stage: List[Stage] = Nil
    }
    val x5598 = StreamController(name = "x5598", parent=x5623, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5598_unitcc = CounterChain(name = "x5598_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5598_0 = StreamPipeline(name = "x5598_0", parent=x5598, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4744 = CU.temp
      val tr4742 = CU.temp
      val tr4738 = CU.temp
      val tr4736 = CU.temp
      val tr4734 = CU.temp
      val x5598_unitcc = CounterChain.copy(x5598, "x5598_unitcc")
      val x5574 = CounterChain.copy(x6511, "x5574")
      val x5579 = CounterChain.copy(x5623, "x5579")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5574(0)), CU.ctr(stage(0), x5579(0))), op=FixAdd, results=List(CU.temp(stage(1), tr4734)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4734), Const("192i")), op=FixMul, results=List(CU.temp(stage(2), tr4736)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4736), Const("64i")), op=FixMod, results=List(CU.temp(stage(3), tr4738)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr4736), CU.temp(stage(3), tr4738)), op=FixSub, results=List(CU.scalarOut(stage(4), x5602_mc.ofs)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr4738), Const("192i")), op=FixAdd, results=List(CU.temp(stage(5), tr4742)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr4742), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr4744)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr4742), CU.temp(stage(6), tr4744)), op=FixSub, results=List(CU.scalarOut(stage(7), bus_4745_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr4744), Const("0i")), op=FixNeq, results=List(CU.scalarOut(stage(8), bus_4747_scalar)))
    }
    val x5598_1 = StreamPipeline(name = "x5598_1", parent=x5598, deps=List(x5598_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4748 = CU.temp
      val x5598_unitcc = CounterChain.copy(x5598, "x5598_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_4747_scalar), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(1), tr4748)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), bus_4745_scalar), CU.temp(stage(1), tr4748)), op=FixAdd, results=List(CU.scalarOut(stage(2), x5602_mc.len)))
    }
    val x5668 = StreamController(name = "x5668", parent=x6511, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5668_unitcc = CounterChain(name = "x5668_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x5643_0 = UnitPipeline(name = "x5643_0", parent=x5668, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4774 = CU.temp
      val tr4773 = CU.temp
      val tr4771 = CU.temp
      val tr4770 = CU.temp
      val tr4768 = CU.temp
      val tr4763 = CU.temp
      val x5574 = CounterChain.copy(x6511, "x5574")
      val x5643_unitcc = CounterChain(name = "x5643_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5574(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr4763)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x5574(0)), CU.temp(stage(1), tr4763)), op=FixSub, results=List(CU.scalarOut(stage(2), x5647_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4763), Const("10i")), op=FixAdd, results=List(CU.temp(stage(3), tr4768)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr4768), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr4770)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr4768), CU.temp(stage(4), tr4770)), op=FixSub, results=List(CU.temp(stage(5), tr4771)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr4770), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr4773)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr4773), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr4774)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr4771), CU.temp(stage(7), tr4774)), op=FixAdd, results=List(CU.scalarOut(stage(8), x5647_mc.len)))
    }
    val x6509 = MetaPipeline(name = "x6509", parent=x6511, deps=List(x5623, x5668)) { implicit CU =>
      val stage0 = CU.emptyStage
      //val ctr14 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      //val x5674 = CounterChain(name = "x5674", ctr14)
      val ctr12 = (Const("0i").out, Const("10i").out, Const("1i").out) // Counter
      val x5672 = CounterChain(name = "x5672", ctr12)
      var stage: List[Stage] = Nil
    }
    val x5768 = StreamController(name = "x5768", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5739 = CounterChain(name = "x5739", ctr15)
      var stage: List[Stage] = Nil
    }
    val x5768_0 = StreamPipeline(name = "x5768_0", parent=x5768, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4790 = CU.temp
      val x5739 = CounterChain.copy(x5768, "x5739")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy(x6531, "x5571")
      val x5575_x5757 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5760 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5739(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4790)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4790), CU.ctr(stage(1), x5739(0))), op=FixAdd, results=List(x5575_x5757.readAddr))
      Stage(stage(3), operands=List(x5575_x5757.load, CU.load(stage(2), x5565_x5760)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4797_vector)))
    }
    val x5768_1 = StreamPipeline(name = "x5768_1", parent=x5768, deps=List(x5768_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5739 = CounterChain.copy(x5768, "x5739")
      val bus_4797_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4797_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4797_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4799) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4799), op=Bypass, results=List(CU.scalarOut(stage(2), x5707_scalar)))
    }
    val x5782 = StreamController(name = "x5782", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr16 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5740 = CounterChain(name = "x5740", ctr16)
      var stage: List[Stage] = Nil
    }
    val x5782_0 = StreamPipeline(name = "x5782_0", parent=x5782, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4804 = CU.temp
      val x5740 = CounterChain.copy(x5782, "x5740")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5771 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5774 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5740(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4804)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4804), CU.ctr(stage(1), x5740(0))), op=FixAdd, results=List(x5575_x5771.readAddr))
      Stage(stage(3), operands=List(x5575_x5771.load, CU.load(stage(2), x5565_x5774)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4811_vector)))
    }
    val x5782_1 = StreamPipeline(name = "x5782_1", parent=x5782, deps=List(x5782_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5740 = CounterChain.copy(x5782, "x5740")
      val bus_4811_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4811_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4811_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4813) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4813), op=Bypass, results=List(CU.scalarOut(stage(2), x5708_scalar)))
    }
    val x5796 = StreamController(name = "x5796", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr17 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5741 = CounterChain(name = "x5741", ctr17)
      var stage: List[Stage] = Nil
    }
    val x5796_0 = StreamPipeline(name = "x5796_0", parent=x5796, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4818 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5741 = CounterChain.copy(x5796, "x5741")
      val x5575_x5785 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5788 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5741(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4818)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4818), CU.ctr(stage(1), x5741(0))), op=FixAdd, results=List(x5575_x5785.readAddr))
      Stage(stage(3), operands=List(x5575_x5785.load, CU.load(stage(2), x5565_x5788)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4825_vector)))
    }
    val x5796_1 = StreamPipeline(name = "x5796_1", parent=x5796, deps=List(x5796_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5741 = CounterChain.copy(x5796, "x5741")
      val bus_4825_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4825_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4825_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4827) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4827), op=Bypass, results=List(CU.scalarOut(stage(2), x5709_scalar)))
    }
    val x5810 = StreamController(name = "x5810", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr18 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5742 = CounterChain(name = "x5742", ctr18)
      var stage: List[Stage] = Nil
    }
    val x5810_0 = StreamPipeline(name = "x5810_0", parent=x5810, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4832 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5742 = CounterChain.copy(x5810, "x5742")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5565_x5802 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5742(0)).wtAddr(x5571(0))
      val x5575_x5799 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4832)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4832), CU.ctr(stage(1), x5742(0))), op=FixAdd, results=List(x5575_x5799.readAddr))
      Stage(stage(3), operands=List(x5575_x5799.load, CU.load(stage(2), x5565_x5802)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4839_vector)))
    }
    val x5810_1 = StreamPipeline(name = "x5810_1", parent=x5810, deps=List(x5810_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5742 = CounterChain.copy(x5810, "x5742")
      val bus_4839_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4839_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4839_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4841) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4841), op=Bypass, results=List(CU.scalarOut(stage(2), x5710_scalar)))
    }
    val x5824 = StreamController(name = "x5824", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr19 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5743 = CounterChain(name = "x5743", ctr19)
      var stage: List[Stage] = Nil
    }
    val x5824_0 = StreamPipeline(name = "x5824_0", parent=x5824, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4846 = CU.temp
      val x5743 = CounterChain.copy(x5824, "x5743")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5565_x5816 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5743(0)).wtAddr(x5571(0))
      val x5575_x5813 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4846)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4846), CU.ctr(stage(1), x5743(0))), op=FixAdd, results=List(x5575_x5813.readAddr))
      Stage(stage(3), operands=List(x5575_x5813.load, CU.load(stage(2), x5565_x5816)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4853_vector)))
    }
    val x5824_1 = StreamPipeline(name = "x5824_1", parent=x5824, deps=List(x5824_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5743 = CounterChain.copy(x5824, "x5743")
      val bus_4853_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4853_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4853_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4855) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4855), op=Bypass, results=List(CU.scalarOut(stage(2), x5711_scalar)))
    }
    val x5838 = StreamController(name = "x5838", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr20 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5744 = CounterChain(name = "x5744", ctr20)
      var stage: List[Stage] = Nil
    }
    val x5838_0 = StreamPipeline(name = "x5838_0", parent=x5838, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4860 = CU.temp
      val x5744 = CounterChain.copy(x5838, "x5744")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5827 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5830 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5744(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4860)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4860), CU.ctr(stage(1), x5744(0))), op=FixAdd, results=List(x5575_x5827.readAddr))
      Stage(stage(3), operands=List(x5575_x5827.load, CU.load(stage(2), x5565_x5830)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4867_vector)))
    }
    val x5838_1 = StreamPipeline(name = "x5838_1", parent=x5838, deps=List(x5838_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5744 = CounterChain.copy(x5838, "x5744")
      val bus_4867_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4867_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4867_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4869) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4869), op=Bypass, results=List(CU.scalarOut(stage(2), x5712_scalar)))
    }
    val x5852 = StreamController(name = "x5852", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr21 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5745 = CounterChain(name = "x5745", ctr21)
      var stage: List[Stage] = Nil
    }
    val x5852_0 = StreamPipeline(name = "x5852_0", parent=x5852, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4874 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5745 = CounterChain.copy(x5852, "x5745")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5841 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5844 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5745(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4874)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4874), CU.ctr(stage(1), x5745(0))), op=FixAdd, results=List(x5575_x5841.readAddr))
      Stage(stage(3), operands=List(x5575_x5841.load, CU.load(stage(2), x5565_x5844)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4881_vector)))
    }
    val x5852_1 = StreamPipeline(name = "x5852_1", parent=x5852, deps=List(x5852_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5745 = CounterChain.copy(x5852, "x5745")
      val bus_4881_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4881_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4881_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4883) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4883), op=Bypass, results=List(CU.scalarOut(stage(2), x5713_scalar)))
    }
    val x5866 = StreamController(name = "x5866", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr22 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5746 = CounterChain(name = "x5746", ctr22)
      var stage: List[Stage] = Nil
    }
    val x5866_0 = StreamPipeline(name = "x5866_0", parent=x5866, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4888 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5746 = CounterChain.copy(x5866, "x5746")
      val x5565_x5858 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5746(0)).wtAddr(x5571(0))
      val x5575_x5855 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4888)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4888), CU.ctr(stage(1), x5746(0))), op=FixAdd, results=List(x5575_x5855.readAddr))
      Stage(stage(3), operands=List(x5575_x5855.load, CU.load(stage(2), x5565_x5858)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4895_vector)))
    }
    val x5866_1 = StreamPipeline(name = "x5866_1", parent=x5866, deps=List(x5866_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5746 = CounterChain.copy(x5866, "x5746")
      val bus_4895_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4895_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4895_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4897) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4897), op=Bypass, results=List(CU.scalarOut(stage(2), x5714_scalar)))
    }
    val x5880 = StreamController(name = "x5880", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr23 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5747 = CounterChain(name = "x5747", ctr23)
      var stage: List[Stage] = Nil
    }
    val x5880_0 = StreamPipeline(name = "x5880_0", parent=x5880, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4902 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5747 = CounterChain.copy(x5880, "x5747")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5869 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5872 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5747(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4902)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4902), CU.ctr(stage(1), x5747(0))), op=FixAdd, results=List(x5575_x5869.readAddr))
      Stage(stage(3), operands=List(x5575_x5869.load, CU.load(stage(2), x5565_x5872)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4909_vector)))
    }
    val x5880_1 = StreamPipeline(name = "x5880_1", parent=x5880, deps=List(x5880_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5747 = CounterChain.copy(x5880, "x5747")
      val bus_4909_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4909_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4909_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4911) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4911), op=Bypass, results=List(CU.scalarOut(stage(2), x5715_scalar)))
    }
    val x5894 = StreamController(name = "x5894", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr24 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5748 = CounterChain(name = "x5748", ctr24)
      var stage: List[Stage] = Nil
    }
    val x5894_0 = StreamPipeline(name = "x5894_0", parent=x5894, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4916 = CU.temp
      val x5748 = CounterChain.copy(x5894, "x5748")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5883 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5886 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5748(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4916)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4916), CU.ctr(stage(1), x5748(0))), op=FixAdd, results=List(x5575_x5883.readAddr))
      Stage(stage(3), operands=List(x5575_x5883.load, CU.load(stage(2), x5565_x5886)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4923_vector)))
    }
    val x5894_1 = StreamPipeline(name = "x5894_1", parent=x5894, deps=List(x5894_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5748 = CounterChain.copy(x5894, "x5748")
      val bus_4923_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4923_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4923_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4925) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4925), op=Bypass, results=List(CU.scalarOut(stage(2), x5716_scalar)))
    }
    val x5908 = StreamController(name = "x5908", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr25 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5749 = CounterChain(name = "x5749", ctr25)
      var stage: List[Stage] = Nil
    }
    val x5908_0 = StreamPipeline(name = "x5908_0", parent=x5908, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4930 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5749 = CounterChain.copy(x5908, "x5749")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5565_x5900 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5749(0)).wtAddr(x5571(0))
      val x5575_x5897 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4930)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4930), CU.ctr(stage(1), x5749(0))), op=FixAdd, results=List(x5575_x5897.readAddr))
      Stage(stage(3), operands=List(x5575_x5897.load, CU.load(stage(2), x5565_x5900)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4937_vector)))
    }
    val x5908_1 = StreamPipeline(name = "x5908_1", parent=x5908, deps=List(x5908_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5749 = CounterChain.copy(x5908, "x5749")
      val bus_4937_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4937_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4937_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4939) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4939), op=Bypass, results=List(CU.scalarOut(stage(2), x5717_scalar)))
    }
    val x5922 = StreamController(name = "x5922", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr26 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5750 = CounterChain(name = "x5750", ctr26)
      var stage: List[Stage] = Nil
    }
    val x5922_0 = StreamPipeline(name = "x5922_0", parent=x5922, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4944 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5750 = CounterChain.copy(x5922, "x5750")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5911 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5914 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5750(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4944)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4944), CU.ctr(stage(1), x5750(0))), op=FixAdd, results=List(x5575_x5911.readAddr))
      Stage(stage(3), operands=List(x5575_x5911.load, CU.load(stage(2), x5565_x5914)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4951_vector)))
    }
    val x5922_1 = StreamPipeline(name = "x5922_1", parent=x5922, deps=List(x5922_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5750 = CounterChain.copy(x5922, "x5750")
      val bus_4951_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4951_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4951_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4953) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4953), op=Bypass, results=List(CU.scalarOut(stage(2), x5718_scalar)))
    }
    val x5936 = StreamController(name = "x5936", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr27 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5751 = CounterChain(name = "x5751", ctr27)
      var stage: List[Stage] = Nil
    }
    val x5936_0 = StreamPipeline(name = "x5936_0", parent=x5936, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4958 = CU.temp
      val x5751 = CounterChain.copy(x5936, "x5751")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5565_x5928 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5751(0)).wtAddr(x5571(0))
      val x5575_x5925 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4958)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4958), CU.ctr(stage(1), x5751(0))), op=FixAdd, results=List(x5575_x5925.readAddr))
      Stage(stage(3), operands=List(x5575_x5925.load, CU.load(stage(2), x5565_x5928)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4965_vector)))
    }
    val x5936_1 = StreamPipeline(name = "x5936_1", parent=x5936, deps=List(x5936_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5751 = CounterChain.copy(x5936, "x5751")
      val bus_4965_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4965_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4965_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4967) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4967), op=Bypass, results=List(CU.scalarOut(stage(2), x5719_scalar)))
    }
    val x5950 = StreamController(name = "x5950", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr28 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5752 = CounterChain(name = "x5752", ctr28)
      var stage: List[Stage] = Nil
    }
    val x5950_0 = StreamPipeline(name = "x5950_0", parent=x5950, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4972 = CU.temp
      val x5752 = CounterChain.copy(x5950, "x5752")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5939 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5942 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5752(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4972)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4972), CU.ctr(stage(1), x5752(0))), op=FixAdd, results=List(x5575_x5939.readAddr))
      Stage(stage(3), operands=List(x5575_x5939.load, CU.load(stage(2), x5565_x5942)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4979_vector)))
    }
    val x5950_1 = StreamPipeline(name = "x5950_1", parent=x5950, deps=List(x5950_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5752 = CounterChain.copy(x5950, "x5752")
      val bus_4979_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4979_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4979_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4981) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4981), op=Bypass, results=List(CU.scalarOut(stage(2), x5720_scalar)))
    }
    val x5964 = StreamController(name = "x5964", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr29 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5753 = CounterChain(name = "x5753", ctr29)
      var stage: List[Stage] = Nil
    }
    val x5964_0 = StreamPipeline(name = "x5964_0", parent=x5964, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr4986 = CU.temp
      val x5753 = CounterChain.copy(x5964, "x5753")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5575_x5953 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      val x5565_x5956 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5753(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr4986)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4986), CU.ctr(stage(1), x5753(0))), op=FixAdd, results=List(x5575_x5953.readAddr))
      Stage(stage(3), operands=List(x5575_x5953.load, CU.load(stage(2), x5565_x5956)), op=FltMul, results=List(CU.vecOut(stage(3), bus_4993_vector)))
    }
    val x5964_1 = StreamPipeline(name = "x5964_1", parent=x5964, deps=List(x5964_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5753 = CounterChain.copy(x5964, "x5753")
      val bus_4993_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_4993_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_4993_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr4995) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4995), op=Bypass, results=List(CU.scalarOut(stage(2), x5721_scalar)))
    }
    val x5978 = StreamController(name = "x5978", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr30 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5754 = CounterChain(name = "x5754", ctr30)
      var stage: List[Stage] = Nil
    }
    val x5978_0 = StreamPipeline(name = "x5978_0", parent=x5978, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5000 = CU.temp
      val x5754 = CounterChain.copy(x5978, "x5754")
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5571 = CounterChain.copy("x6529_0", "x5571")
      val x5565_x5970 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x5754(0)).wtAddr(x5571(0))
      val x5575_x5967 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5000)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5000), CU.ctr(stage(1), x5754(0))), op=FixAdd, results=List(x5575_x5967.readAddr))
      Stage(stage(3), operands=List(x5575_x5967.load, CU.load(stage(2), x5565_x5970)), op=FltMul, results=List(CU.vecOut(stage(3), bus_5007_vector)))
    }
    val x5978_1 = StreamPipeline(name = "x5978_1", parent=x5978, deps=List(x5978_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5754 = CounterChain.copy(x5978, "x5754")
      val bus_5007_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5007_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_5007_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr5009) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr5009), op=Bypass, results=List(CU.scalarOut(stage(2), x5722_scalar)))
    }
    val x5991_0 = UnitPipeline(name = "x5991_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5021 = CU.temp
      val tr5020 = CU.temp
      val tr5018 = CU.temp
      val tr5017 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5991_unitcc = CounterChain(name = "x5991_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5576_x5982 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5707_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5017)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5017)), op=FltExp, results=List(CU.temp(stage(2), tr5018)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5018), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5020)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5020)), op=FltDiv, results=List(CU.temp(stage(4), tr5021)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x5982), CU.temp(stage(4), tr5021)), op=FltSub, results=List(CU.scalarOut(stage(5), x5675_scalar)))
    }
    val x6002_0 = UnitPipeline(name = "x6002_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5034 = CU.temp
      val tr5033 = CU.temp
      val tr5031 = CU.temp
      val tr5030 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x6002_unitcc = CounterChain(name = "x6002_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5576_x5993 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5708_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5030)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5030)), op=FltExp, results=List(CU.temp(stage(2), tr5031)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5031), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5033)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5033)), op=FltDiv, results=List(CU.temp(stage(4), tr5034)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x5993), CU.temp(stage(4), tr5034)), op=FltSub, results=List(CU.scalarOut(stage(5), x5676_scalar)))
    }
    val x6013_0 = UnitPipeline(name = "x6013_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5047 = CU.temp
      val tr5046 = CU.temp
      val tr5044 = CU.temp
      val tr5043 = CU.temp
      val x6013_unitcc = CounterChain(name = "x6013_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6004 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5709_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5043)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5043)), op=FltExp, results=List(CU.temp(stage(2), tr5044)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5044), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5046)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5046)), op=FltDiv, results=List(CU.temp(stage(4), tr5047)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6004), CU.temp(stage(4), tr5047)), op=FltSub, results=List(CU.scalarOut(stage(5), x5677_scalar)))
    }
    val x6024_0 = UnitPipeline(name = "x6024_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5060 = CU.temp
      val tr5059 = CU.temp
      val tr5057 = CU.temp
      val tr5056 = CU.temp
      val x6024_unitcc = CounterChain(name = "x6024_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6015 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5710_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5056)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5056)), op=FltExp, results=List(CU.temp(stage(2), tr5057)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5057), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5059)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5059)), op=FltDiv, results=List(CU.temp(stage(4), tr5060)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6015), CU.temp(stage(4), tr5060)), op=FltSub, results=List(CU.scalarOut(stage(5), x5678_scalar)))
    }
    val x6035_0 = UnitPipeline(name = "x6035_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5073 = CU.temp
      val tr5072 = CU.temp
      val tr5070 = CU.temp
      val tr5069 = CU.temp
      val x6035_unitcc = CounterChain(name = "x6035_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6026 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5711_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5069)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5069)), op=FltExp, results=List(CU.temp(stage(2), tr5070)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5070), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5072)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5072)), op=FltDiv, results=List(CU.temp(stage(4), tr5073)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6026), CU.temp(stage(4), tr5073)), op=FltSub, results=List(CU.scalarOut(stage(5), x5679_scalar)))
    }
    val x6046_0 = UnitPipeline(name = "x6046_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5086 = CU.temp
      val tr5085 = CU.temp
      val tr5083 = CU.temp
      val tr5082 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x6046_unitcc = CounterChain(name = "x6046_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6037 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5712_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5082)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5082)), op=FltExp, results=List(CU.temp(stage(2), tr5083)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5083), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5085)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5085)), op=FltDiv, results=List(CU.temp(stage(4), tr5086)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6037), CU.temp(stage(4), tr5086)), op=FltSub, results=List(CU.scalarOut(stage(5), x5680_scalar)))
    }
    val x6057_0 = UnitPipeline(name = "x6057_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5099 = CU.temp
      val tr5098 = CU.temp
      val tr5096 = CU.temp
      val tr5095 = CU.temp
      val x6057_unitcc = CounterChain(name = "x6057_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6048 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5713_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5095)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5095)), op=FltExp, results=List(CU.temp(stage(2), tr5096)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5096), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5098)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5098)), op=FltDiv, results=List(CU.temp(stage(4), tr5099)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6048), CU.temp(stage(4), tr5099)), op=FltSub, results=List(CU.scalarOut(stage(5), x5681_scalar)))
    }
    val x6068_0 = UnitPipeline(name = "x6068_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5112 = CU.temp
      val tr5111 = CU.temp
      val tr5109 = CU.temp
      val tr5108 = CU.temp
      val x6068_unitcc = CounterChain(name = "x6068_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6059 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5714_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5108)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5108)), op=FltExp, results=List(CU.temp(stage(2), tr5109)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5109), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5111)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5111)), op=FltDiv, results=List(CU.temp(stage(4), tr5112)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6059), CU.temp(stage(4), tr5112)), op=FltSub, results=List(CU.scalarOut(stage(5), x5682_scalar)))
    }
    val x6079_0 = UnitPipeline(name = "x6079_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5125 = CU.temp
      val tr5124 = CU.temp
      val tr5122 = CU.temp
      val tr5121 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x6079_unitcc = CounterChain(name = "x6079_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6070 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5715_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5121)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5121)), op=FltExp, results=List(CU.temp(stage(2), tr5122)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5122), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5124)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5124)), op=FltDiv, results=List(CU.temp(stage(4), tr5125)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6070), CU.temp(stage(4), tr5125)), op=FltSub, results=List(CU.scalarOut(stage(5), x5683_scalar)))
    }
    val x6090_0 = UnitPipeline(name = "x6090_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5138 = CU.temp
      val tr5137 = CU.temp
      val tr5135 = CU.temp
      val tr5134 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x6090_unitcc = CounterChain(name = "x6090_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6081 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5716_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5134)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5134)), op=FltExp, results=List(CU.temp(stage(2), tr5135)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5135), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5137)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5137)), op=FltDiv, results=List(CU.temp(stage(4), tr5138)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6081), CU.temp(stage(4), tr5138)), op=FltSub, results=List(CU.scalarOut(stage(5), x5684_scalar)))
    }
    val x6101_0 = UnitPipeline(name = "x6101_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5151 = CU.temp
      val tr5150 = CU.temp
      val tr5148 = CU.temp
      val tr5147 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x6101_unitcc = CounterChain(name = "x6101_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5576_x6092 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5717_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5147)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5147)), op=FltExp, results=List(CU.temp(stage(2), tr5148)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5148), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5150)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5150)), op=FltDiv, results=List(CU.temp(stage(4), tr5151)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6092), CU.temp(stage(4), tr5151)), op=FltSub, results=List(CU.scalarOut(stage(5), x5685_scalar)))
    }
    val x6112_0 = UnitPipeline(name = "x6112_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5164 = CU.temp
      val tr5163 = CU.temp
      val tr5161 = CU.temp
      val tr5160 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x6112_unitcc = CounterChain(name = "x6112_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5576_x6103 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5718_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5160)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5160)), op=FltExp, results=List(CU.temp(stage(2), tr5161)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5161), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5163)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5163)), op=FltDiv, results=List(CU.temp(stage(4), tr5164)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6103), CU.temp(stage(4), tr5164)), op=FltSub, results=List(CU.scalarOut(stage(5), x5686_scalar)))
    }
    val x6123_0 = UnitPipeline(name = "x6123_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5177 = CU.temp
      val tr5176 = CU.temp
      val tr5174 = CU.temp
      val tr5173 = CU.temp
      val x6123_unitcc = CounterChain(name = "x6123_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6114 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5719_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5173)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5173)), op=FltExp, results=List(CU.temp(stage(2), tr5174)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5174), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5176)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5176)), op=FltDiv, results=List(CU.temp(stage(4), tr5177)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6114), CU.temp(stage(4), tr5177)), op=FltSub, results=List(CU.scalarOut(stage(5), x5687_scalar)))
    }
    val x6134_0 = UnitPipeline(name = "x6134_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5190 = CU.temp
      val tr5189 = CU.temp
      val tr5187 = CU.temp
      val tr5186 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x6134_unitcc = CounterChain(name = "x6134_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5576_x6125 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5720_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5186)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5186)), op=FltExp, results=List(CU.temp(stage(2), tr5187)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5187), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5189)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5189)), op=FltDiv, results=List(CU.temp(stage(4), tr5190)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6125), CU.temp(stage(4), tr5190)), op=FltSub, results=List(CU.scalarOut(stage(5), x5688_scalar)))
    }
    val x6145_0 = UnitPipeline(name = "x6145_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5203 = CU.temp
      val tr5202 = CU.temp
      val tr5200 = CU.temp
      val tr5199 = CU.temp
      val x6145_unitcc = CounterChain(name = "x6145_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5576_x6136 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5721_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5199)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5199)), op=FltExp, results=List(CU.temp(stage(2), tr5200)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5200), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5202)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5202)), op=FltDiv, results=List(CU.temp(stage(4), tr5203)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6136), CU.temp(stage(4), tr5203)), op=FltSub, results=List(CU.scalarOut(stage(5), x5689_scalar)))
    }
    val x6156_0 = UnitPipeline(name = "x6156_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5216 = CU.temp
      val tr5215 = CU.temp
      val tr5213 = CU.temp
      val tr5212 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x6156_unitcc = CounterChain(name = "x6156_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5576_x6147 = SemiFIFO(size = 10, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5647_mc.vdata).rdAddr(x5672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(Const("-1.0f"), CU.scalarIn(stage(0), x5722_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr5212)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5212)), op=FltExp, results=List(CU.temp(stage(2), tr5213)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5213), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr5215)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr5215)), op=FltDiv, results=List(CU.temp(stage(4), tr5216)))
      Stage(stage(5), operands=List(CU.load(stage(4), x5576_x6147), CU.temp(stage(4), tr5216)), op=FltSub, results=List(CU.scalarOut(stage(5), x5690_scalar)))
    }
    val x6203_0 = Pipeline(name = "x6203_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5222 = CU.temp
      val ctr31 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6175 = CounterChain(name = "x6175", ctr31)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6193 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5222)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5222), CU.ctr(stage(1), x6175(0))), op=FixAdd, results=List(x5575_x6193.readAddr))
      Stage(stage(3), operands=List(x5575_x6193.load, CU.scalarIn(stage(2), x5675_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5691_vector)))
    }
    val x6216_0 = Pipeline(name = "x6216_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5235 = CU.temp
      val ctr32 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6176 = CounterChain(name = "x6176", ctr32)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6206 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5235)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5235), CU.ctr(stage(1), x6176(0))), op=FixAdd, results=List(x5575_x6206.readAddr))
      Stage(stage(3), operands=List(x5575_x6206.load, CU.scalarIn(stage(2), x5676_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5692_vector)))
    }
    val x6229_0 = Pipeline(name = "x6229_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5248 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val ctr33 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6177 = CounterChain(name = "x6177", ctr33)
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6219 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5248)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5248), CU.ctr(stage(1), x6177(0))), op=FixAdd, results=List(x5575_x6219.readAddr))
      Stage(stage(3), operands=List(x5575_x6219.load, CU.scalarIn(stage(2), x5677_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5693_vector)))
    }
    val x6242_0 = Pipeline(name = "x6242_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5261 = CU.temp
      val ctr34 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6178 = CounterChain(name = "x6178", ctr34)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6232 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5261)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5261), CU.ctr(stage(1), x6178(0))), op=FixAdd, results=List(x5575_x6232.readAddr))
      Stage(stage(3), operands=List(x5575_x6232.load, CU.scalarIn(stage(2), x5678_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5694_vector)))
    }
    val x6255_0 = Pipeline(name = "x6255_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5274 = CU.temp
      val ctr35 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6179 = CounterChain(name = "x6179", ctr35)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6245 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5274)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5274), CU.ctr(stage(1), x6179(0))), op=FixAdd, results=List(x5575_x6245.readAddr))
      Stage(stage(3), operands=List(x5575_x6245.load, CU.scalarIn(stage(2), x5679_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5695_vector)))
    }
    val x6268_0 = Pipeline(name = "x6268_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5287 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val ctr36 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6180 = CounterChain(name = "x6180", ctr36)
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6258 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5287)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5287), CU.ctr(stage(1), x6180(0))), op=FixAdd, results=List(x5575_x6258.readAddr))
      Stage(stage(3), operands=List(x5575_x6258.load, CU.scalarIn(stage(2), x5680_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5696_vector)))
    }
    val x6281_0 = Pipeline(name = "x6281_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5300 = CU.temp
      val ctr37 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6181 = CounterChain(name = "x6181", ctr37)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6271 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5300)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5300), CU.ctr(stage(1), x6181(0))), op=FixAdd, results=List(x5575_x6271.readAddr))
      Stage(stage(3), operands=List(x5575_x6271.load, CU.scalarIn(stage(2), x5681_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5697_vector)))
    }
    val x6294_0 = Pipeline(name = "x6294_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5313 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val ctr38 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6182 = CounterChain(name = "x6182", ctr38)
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6284 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5313)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5313), CU.ctr(stage(1), x6182(0))), op=FixAdd, results=List(x5575_x6284.readAddr))
      Stage(stage(3), operands=List(x5575_x6284.load, CU.scalarIn(stage(2), x5682_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5698_vector)))
    }
    val x6307_0 = Pipeline(name = "x6307_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5326 = CU.temp
      val ctr39 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6183 = CounterChain(name = "x6183", ctr39)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6297 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5326)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5326), CU.ctr(stage(1), x6183(0))), op=FixAdd, results=List(x5575_x6297.readAddr))
      Stage(stage(3), operands=List(x5575_x6297.load, CU.scalarIn(stage(2), x5683_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5699_vector)))
    }
    val x6320_0 = Pipeline(name = "x6320_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5339 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val ctr40 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6184 = CounterChain(name = "x6184", ctr40)
      val x5575_x6310 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5339)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5339), CU.ctr(stage(1), x6184(0))), op=FixAdd, results=List(x5575_x6310.readAddr))
      Stage(stage(3), operands=List(x5575_x6310.load, CU.scalarIn(stage(2), x5684_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5700_vector)))
    }
    val x6333_0 = Pipeline(name = "x6333_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5352 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val ctr41 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6185 = CounterChain(name = "x6185", ctr41)
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6323 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5352)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5352), CU.ctr(stage(1), x6185(0))), op=FixAdd, results=List(x5575_x6323.readAddr))
      Stage(stage(3), operands=List(x5575_x6323.load, CU.scalarIn(stage(2), x5685_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5701_vector)))
    }
    val x6346_0 = Pipeline(name = "x6346_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5365 = CU.temp
      val ctr42 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6186 = CounterChain(name = "x6186", ctr42)
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x5575_x6336 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5365)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5365), CU.ctr(stage(1), x6186(0))), op=FixAdd, results=List(x5575_x6336.readAddr))
      Stage(stage(3), operands=List(x5575_x6336.load, CU.scalarIn(stage(2), x5686_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5702_vector)))
    }
    val x6359_0 = Pipeline(name = "x6359_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5378 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val ctr43 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6187 = CounterChain(name = "x6187", ctr43)
      val x5575_x6349 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5378)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5378), CU.ctr(stage(1), x6187(0))), op=FixAdd, results=List(x5575_x6349.readAddr))
      Stage(stage(3), operands=List(x5575_x6349.load, CU.scalarIn(stage(2), x5687_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5703_vector)))
    }
    val x6372_0 = Pipeline(name = "x6372_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5391 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val ctr44 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6188 = CounterChain(name = "x6188", ctr44)
      val x5575_x6362 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5391)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5391), CU.ctr(stage(1), x6188(0))), op=FixAdd, results=List(x5575_x6362.readAddr))
      Stage(stage(3), operands=List(x5575_x6362.load, CU.scalarIn(stage(2), x5688_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5704_vector)))
    }
    val x6385_0 = Pipeline(name = "x6385_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5404 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val ctr45 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6189 = CounterChain(name = "x6189", ctr45)
      val x5575_x6375 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5404)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5404), CU.ctr(stage(1), x6189(0))), op=FixAdd, results=List(x5575_x6375.readAddr))
      Stage(stage(3), operands=List(x5575_x6375.load, CU.scalarIn(stage(2), x5689_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5705_vector)))
    }
    val x6398_0 = Pipeline(name = "x6398_0", parent=x6509, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5417 = CU.temp
      //val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val ctr46 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6190 = CounterChain(name = "x6190", ctr46)
      val x5575_x6388 = SemiFIFO(size = 1920, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5672(0))).wtPort(x5602_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5672(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr5417)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5417), CU.ctr(stage(1), x6190(0))), op=FixAdd, results=List(x5575_x6388.readAddr))
      Stage(stage(3), operands=List(x5575_x6388.load, CU.scalarIn(stage(2), x5690_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x5706_vector)))
    }
    val x6507 = StreamController(name = "x6507", parent=x6509, deps=List(x6307_0, x6281_0, x6398_0, x6385_0, x6359_0, x6255_0, x6216_0, x6268_0, x6294_0, x6346_0, x6242_0, x6372_0, x6229_0, x6333_0, x6320_0, x6203_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr49 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x5674 = CounterChain(name = "x5674", ctr49)
      var stage: List[Stage] = Nil
    }
    val x6507_0 = StreamPipeline(name = "x6507_0", parent=x6507, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5490 = CU.temp
      val tr5485 = CU.temp
      val x6177 = CounterChain.copy(x6229_0, "x6177")
      val x6176 = CounterChain.copy(x6216_0, "x6176")
      val x6178 = CounterChain.copy(x6242_0, "x6178")
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x6175 = CounterChain.copy(x6203_0, "x6175")
      val x5691_x6403 = SRAM(size = 192, writeCtr = x6175(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6175(0))).wtPort(x5691_vector).rdAddr(x5674(0)).wtAddr(x6175(0))
      val x5694_x6412 = SRAM(size = 192, writeCtr = x6178(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6178(0))).wtPort(x5694_vector).rdAddr(x5674(0)).wtAddr(x6178(0))
      val x5692_x6406 = SRAM(size = 192, writeCtr = x6176(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6176(0))).wtPort(x5692_vector).rdAddr(x5674(0)).wtAddr(x6176(0))
      val x5693_x6409 = SRAM(size = 192, writeCtr = x6177(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6177(0))).wtPort(x5693_vector).rdAddr(x5674(0)).wtAddr(x6177(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x5691_x6403.load, x5692_x6406.load), op=FltAdd, results=List(CU.temp(stage(1), tr5485)))
      Stage(stage(2), operands=List(CU.load(stage(1), x5693_x6409), CU.load(stage(1), x5694_x6412)), op=FltAdd, results=List(CU.temp(stage(2), tr5490)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5485), CU.temp(stage(2), tr5490)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_5491_vector)))
    }
    val x6507_1 = StreamPipeline(name = "x6507_1", parent=x6507, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5501 = CU.temp
      val tr5496 = CU.temp
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x6180 = CounterChain.copy(x6268_0, "x6180")
      val x6182 = CounterChain.copy(x6294_0, "x6182")
      val x6179 = CounterChain.copy(x6255_0, "x6179")
      val x6181 = CounterChain.copy(x6281_0, "x6181")
      val x5695_x6415 = SRAM(size = 192, writeCtr = x6179(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6179(0))).wtPort(x5695_vector).rdAddr(x5674(0)).wtAddr(x6179(0))
      val x5697_x6421 = SRAM(size = 192, writeCtr = x6181(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6181(0))).wtPort(x5697_vector).rdAddr(x5674(0)).wtAddr(x6181(0))
      val x5698_x6424 = SRAM(size = 192, writeCtr = x6182(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6182(0))).wtPort(x5698_vector).rdAddr(x5674(0)).wtAddr(x6182(0))
      val x5696_x6418 = SRAM(size = 192, writeCtr = x6180(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6180(0))).wtPort(x5696_vector).rdAddr(x5674(0)).wtAddr(x6180(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x5695_x6415.load, x5696_x6418.load), op=FltAdd, results=List(CU.temp(stage(1), tr5496)))
      Stage(stage(2), operands=List(CU.load(stage(1), x5697_x6421), CU.load(stage(1), x5698_x6424)), op=FltAdd, results=List(CU.temp(stage(2), tr5501)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5496), CU.temp(stage(2), tr5501)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_5502_vector)))
    }
    val x6507_2 = StreamPipeline(name = "x6507_2", parent=x6507, deps=List(x6507_0, x6507_1)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x6184 = CounterChain.copy(x6320_0, "x6184")
      val x6183 = CounterChain.copy(x6307_0, "x6183")
      val x5699_x6427 = SRAM(size = 192, writeCtr = x6183(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6183(0))).wtPort(x5699_vector).rdAddr(x5674(0)).wtAddr(x6183(0))
      val x5700_x6430 = SRAM(size = 192, writeCtr = x6184(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6184(0))).wtPort(x5700_vector).rdAddr(x5674(0)).wtAddr(x6184(0))
      val bus_5491_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5491_vector)
      val bus_5502_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5502_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_5491_fifo.load, bus_5502_fifo.load), op=FltAdd, results=List(CU.vecOut(stage(1), bus_5503_vector)))
      Stage(stage(2), operands=List(CU.load(stage(1), x5699_x6427), CU.load(stage(1), x5700_x6430)), op=FltAdd, results=List(CU.vecOut(stage(2), bus_5508_vector)))
    }
    val x6507_3 = StreamPipeline(name = "x6507_3", parent=x6507, deps=List(x6507_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5513 = CU.temp
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x6185 = CounterChain.copy(x6333_0, "x6185")
      val x6186 = CounterChain.copy(x6346_0, "x6186")
      val x5701_x6433 = SRAM(size = 192, writeCtr = x6185(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6185(0))).wtPort(x5701_vector).rdAddr(x5674(0)).wtAddr(x6185(0))
      val x5702_x6436 = SRAM(size = 192, writeCtr = x6186(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6186(0))).wtPort(x5702_vector).rdAddr(x5674(0)).wtAddr(x6186(0))
      val bus_5508_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5508_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x5701_x6433.load, x5702_x6436.load), op=FltAdd, results=List(CU.temp(stage(1), tr5513)))
      Stage(stage(2), operands=List(bus_5508_fifo.load, CU.temp(stage(1), tr5513)), op=FltAdd, results=List(CU.vecOut(stage(2), bus_5514_vector)))
    }
    val x6507_4 = StreamPipeline(name = "x6507_4", parent=x6507, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5524 = CU.temp
      val tr5519 = CU.temp
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x6189 = CounterChain.copy(x6385_0, "x6189")
      val x6190 = CounterChain.copy(x6398_0, "x6190")
      val x6187 = CounterChain.copy(x6359_0, "x6187")
      val x6188 = CounterChain.copy(x6372_0, "x6188")
      val x5706_x6448 = SRAM(size = 192, writeCtr = x6190(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6190(0))).wtPort(x5706_vector).rdAddr(x5674(0)).wtAddr(x6190(0))
      val x5703_x6439 = SRAM(size = 192, writeCtr = x6187(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6187(0))).wtPort(x5703_vector).rdAddr(x5674(0)).wtAddr(x6187(0))
      val x5704_x6442 = SRAM(size = 192, writeCtr = x6188(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6188(0))).wtPort(x5704_vector).rdAddr(x5674(0)).wtAddr(x6188(0))
      val x5705_x6445 = SRAM(size = 192, writeCtr = x6189(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x5674(0), swapWrite = x6189(0))).wtPort(x5705_vector).rdAddr(x5674(0)).wtAddr(x6189(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x5703_x6439.load, x5704_x6442.load), op=FltAdd, results=List(CU.temp(stage(1), tr5519)))
      Stage(stage(2), operands=List(CU.load(stage(1), x5705_x6445), CU.load(stage(1), x5706_x6448)), op=FltAdd, results=List(CU.temp(stage(2), tr5524)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5519), CU.temp(stage(2), tr5524)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_5525_vector)))
    }
    val x6507_5 = StreamPipeline(name = "x6507_5", parent=x6507, deps=List(x6507_3, x6507_4, x6507_2)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5527 = CU.temp
      val tr5526 = CU.temp
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x5572_x6451 = SRAM(size = 192, writeCtr = x5674(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x5674(0))
      val bus_5514_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5514_vector)
      val bus_5525_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5525_vector)
      val bus_5503_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_5503_vector)
      val wr5529 = CU.wtAddr(x5572_x6451)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_5514_fifo.load, bus_5525_fifo.load), op=FltAdd, results=List(CU.temp(stage(1), tr5526)))
      Stage(stage(2), operands=List(bus_5503_fifo.load, CU.temp(stage(1), tr5526)), op=FltAdd, results=List(CU.temp(stage(2), tr5527)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5527), CU.load(stage(2), x5572_x6451)), op=FltAdd, results=List(CU.vecOut(stage(3), x5572_vector), CU.store(stage(3), x5572_x6451)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x5674(0))), op=Bypass, results=List(CU.wtAddr(stage(4), wr5529)))
    }
    val x6529_0 = Pipeline(name = "x6529_0", parent=x6531, deps=List(x6511)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5542 = CU.temp
      val ctr50 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x5571 = CounterChain(name = "x5571", ctr50)
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x5572_x6514 = SRAM(size = 192, writeCtr = x5674(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5572_vector).rdAddr(x5571(0)).wtAddr(x5674(0))
      val x5565_x6517 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x5571(0))
      val wr5544 = CU.wtAddr(x5565_x6517)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x5565_x6517.load, Const("1i")), op=FltMul, results=List(CU.temp(stage(1), tr5542)))
      Stage(stage(2), operands=List(CU.load(stage(1), x5572_x6514), CU.temp(stage(1), tr5542)), op=FltAdd, results=List(CU.vecOut(stage(2), x5565_vector), CU.store(stage(2), x5565_x6517)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5571(0))), op=Bypass, results=List(CU.wtAddr(stage(3), wr5544)))
    }
    val x6553 = StreamController(name = "x6553", parent=x6555, deps=List(x6533)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6553_unitcc = CounterChain(name = "x6553_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6538_0 = UnitPipeline(name = "x6538_0", parent=x6553, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6538_unitcc = CounterChain(name = "x6538_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x6551_mc.ofs)))
      Stage(stage(2), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(2), x6551_mc.len)))
    }
    val x6549_0 = Pipeline(name = "x6549_0", parent=x6553, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr51 = (Const("0i").out, Const("192i").out, Const("16i").out) // Counter
      val x6540 = CounterChain(name = "x6540", ctr51)
      val x5571 = CounterChain.copy(x6531, "x5571")
      val x5565_x6543 = SRAM(size = 192, writeCtr = x5571(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x5565_vector).rdAddr(x6540(0)).wtAddr(x5571(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x5565_x6543.load), op=Bypass, results=List(CU.vecOut(stage(1), x6551_mc.vdata)))
    }
    val x6555_leafX = UnitPipeline(name = "x6555_leafX", parent=x6555, deps=List(x6553)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6555_unitcc = CounterChain(name = "x6555_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6509_leaf = UnitPipeline(name = "x6509_leaf", parent=x6509, deps=List(x6101_0, x5824, x6046_0, x5908, x5936, x5894, x6024_0, x5782, x6068_0, x5810, x6145_0, x6090_0, x5922, x6013_0, x6057_0, x5852, x6079_0, x5978, x6123_0, x6156_0, x5838, x5991_0, x5796, x5768, x6035_0, x6002_0, x6507, x5880, x5866, x6134_0, x5950, x5964, x6112_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x5674 = CounterChain.copy(x6507, "x5674")
      val x5672 = CounterChain.copy(x6509, "x5672")
      val x6509_unitcc = CounterChain(name = "x6509_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }

  }
}
