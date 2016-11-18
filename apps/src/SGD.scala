import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SGDDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x6280_vector = Vector("x6280")
    val bus_5787_scalar = Scalar("bus_5787")
    val bus_5991_scalar = Scalar("bus_5991")
    val bus_5904_scalar = Scalar("bus_5904")
    val x6281_vector = Vector("x6281")
    val bus_5875_scalar = Scalar("bus_5875")
    val x7076_scalar = Scalar("x7076")
    val x6169_oc = OffChip("x6169")
    val x6274_scalar = Scalar("x6274")
    val x6285_vector = Vector("x6285")
    val x6277_scalar = Scalar("x6277")
    val x6291_vector = Vector("x6291")
    val x7102_scalar = Scalar("x7102")
    val x6920_scalar = Scalar("x6920")
    val x6275_scalar = Scalar("x6275")
    val x6278_scalar = Scalar("x6278")
    val bus_5758_scalar = Scalar("bus_5758")
    val x7128_scalar = Scalar("x7128")
    val bus_5788_scalar = Scalar("bus_5788")
    val bus_5845_scalar = Scalar("bus_5845")
    val bus_5990_scalar = Scalar("bus_5990")
    val bus_6500_vector = Vector("bus_6500")
    val bus_6020_scalar = Scalar("bus_6020")
    val bus_6511_vector = Vector("bus_6511")
    val bus_5816_scalar = Scalar("bus_5816")
    val x6946_scalar = Scalar("x6946")
    val bus_6489_vector = Vector("bus_6489")
    val x6276_scalar = Scalar("x6276")
    val bus_5903_scalar = Scalar("bus_5903")
    val x6283_vector = Vector("x6283")
    val bus_5961_scalar = Scalar("bus_5961")
    val x6170_oc = OffChip("x6170")
    val x6288_vector = Vector("x6288")
    val x6894_scalar = Scalar("x6894")
    val x6273_scalar = Scalar("x6273")
    val x6270_scalar = Scalar("x6270")
    val bus_5962_scalar = Scalar("bus_5962")
    val x6268_scalar = Scalar("x6268")
    val bus_6049_scalar = Scalar("bus_6049")
    val bus_5933_scalar = Scalar("bus_5933")
    val x6214_vector = Vector("x6214")
    val x6287_vector = Vector("x6287")
    val x7024_scalar = Scalar("x7024")
    val bus_6048_scalar = Scalar("bus_6048")
    val x7180_scalar = Scalar("x7180")
    val x6290_vector = Vector("x6290")
    val x6284_vector = Vector("x6284")
    val bus_5759_scalar = Scalar("bus_5759")
    val x6289_vector = Vector("x6289")
    val bus_5730_scalar = Scalar("bus_5730")
    val x6286_vector = Vector("x6286")
    val x6269_scalar = Scalar("x6269")
    val x6998_scalar = Scalar("x6998")
    val x7154_scalar = Scalar("x7154")
    val bus_5846_scalar = Scalar("bus_5846")
    val x6282_vector = Vector("x6282")
    val x6271_scalar = Scalar("x6271")
    val x6272_scalar = Scalar("x6272")
    val bus_6019_scalar = Scalar("bus_6019")
    val x6171_oc = OffChip("x6171")
    val bus_5817_scalar = Scalar("bus_5817")
    val bus_5932_scalar = Scalar("bus_5932")
    val bus_5874_scalar = Scalar("bus_5874")
    val x7050_scalar = Scalar("x7050")
    val x6279_scalar = Scalar("x6279")
    val bus_5729_scalar = Scalar("bus_5729")
    val x6972_scalar = Scalar("x6972")
    val x6819_mc = MemoryController(TileLoad, x6169_oc)
    val x6623_mc = MemoryController(TileLoad, x6169_oc)
    val x6329_mc = MemoryController(TileLoad, x6169_oc)
    val x6378_mc = MemoryController(TileLoad, x6169_oc)
    val x6525_mc = MemoryController(TileLoad, x6169_oc)
    val x6574_mc = MemoryController(TileLoad, x6169_oc)
    val x6868_mc = MemoryController(TileLoad, x6169_oc)
    val x6476_mc = MemoryController(TileLoad, x6169_oc)
    val x6721_mc = MemoryController(TileLoad, x6169_oc)
    val x7508_mc = MemoryController(TileStore, x6171_oc)
    val x6242_mc = MemoryController(TileLoad, x6170_oc)
    val x6770_mc = MemoryController(TileLoad, x6169_oc)
    val x6672_mc = MemoryController(TileLoad, x6169_oc)
    val x6427_mc = MemoryController(TileLoad, x6169_oc)
    val x7512 = Sequential(name = "x7512", parent=top, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7512_unitcc = CounterChain(name = "x7512_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7490 = Sequential(name = "x7490", parent=x7512, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("30i").out, Const("1i").out) // Counter
      val x6216 = CounterChain(name = "x6216", ctr1)
      var stage: List[Stage] = Nil
    }
    val x7488 = Sequential(name = "x7488", parent=x7490, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("38400i").out, Const("192i").out) // Counter
      val x6218 = CounterChain(name = "x6218", ctr3)
      var stage: List[Stage] = Nil
    }
    val x6263 = StreamController(name = "x6263", parent=x7488, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6263_unitcc = CounterChain(name = "x6263_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6238_0 = UnitPipeline(name = "x6238_0", parent=x6263, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5706 = CU.temp
      val tr5705 = CU.temp
      val tr5703 = CU.temp
      val tr5702 = CU.temp
      val tr5700 = CU.temp
      val tr5695 = CU.temp
      val x6238_unitcc = CounterChain(name = "x6238_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6218 = CounterChain.copy(x7488, "x6218")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr5695)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6218(0)), CU.temp(stage(1), tr5695)), op=FixSub, results=List(CU.scalarOut(stage(2), x6242_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5695), Const("192i")), op=FixAdd, results=List(CU.temp(stage(3), tr5700)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5700), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5702)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5700), CU.temp(stage(4), tr5702)), op=FixSub, results=List(CU.temp(stage(5), tr5703)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5702), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr5705)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5705), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr5706)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5703), CU.temp(stage(7), tr5706)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6242_mc.len)))
    }
    val x7486 = Sequential(name = "x7486", parent=x7488, deps=List(x6263)) { implicit CU =>
      val stage0 = CU.emptyStage
      //val ctr9 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      //val x6267 = CounterChain(name = "x6267", ctr9)
      val ctr7 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x6265 = CounterChain(name = "x6265", ctr7)
      var stage: List[Stage] = Nil
    }
    val x6350 = MetaPipeline(name = "x6350", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr34 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6306 = CounterChain(name = "x6306", ctr34)
      var stage: List[Stage] = Nil
    }
    val x6325 = StreamController(name = "x6325", parent=x6350, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6325_unitcc = CounterChain(name = "x6325_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6325_0 = StreamPipeline(name = "x6325_0", parent=x6325, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5729 = CU.temp
      val tr5727 = CU.temp
      val tr5723 = CU.temp
      val tr5721 = CU.temp
      val tr5719 = CU.temp
      val tr5718 = CU.temp
      val x6306 = CounterChain.copy(x6350, "x6306")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6325_unitcc = CounterChain.copy(x6325, "x6325_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5718)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5718), CU.ctr(stage(1), x6306(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5719)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5719), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5721)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5721), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5723)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5721), CU.temp(stage(4), tr5723)), op=FixSub, results=List(CU.scalarOut(stage(5), x6329_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5723), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5727)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5727), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5729), CU.scalarOut(stage(7), bus_5729_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5727), CU.temp(stage(7), tr5729)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5730_scalar)))
    }
    val x6325_1 = StreamPipeline(name = "x6325_1", parent=x6325, deps=List(x6325_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5733 = CU.temp
      val tr5732 = CU.temp
      val x6325_unitcc = CounterChain.copy(x6325, "x6325_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5729_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5732)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5732), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5733)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5730_scalar), CU.temp(stage(2), tr5733)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6329_mc.len)))
    }
    val x6399 = MetaPipeline(name = "x6399", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr39 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6355 = CounterChain(name = "x6355", ctr39)
      var stage: List[Stage] = Nil
    }
    val x6374 = StreamController(name = "x6374", parent=x6399, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6374_unitcc = CounterChain(name = "x6374_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6374_0 = StreamPipeline(name = "x6374_0", parent=x6374, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5758 = CU.temp
      val tr5756 = CU.temp
      val tr5752 = CU.temp
      val tr5750 = CU.temp
      val tr5748 = CU.temp
      val tr5747 = CU.temp
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6355 = CounterChain.copy(x6399, "x6355")
      val x6374_unitcc = CounterChain.copy(x6374, "x6374_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5747)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5747), CU.ctr(stage(1), x6355(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5748)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5748), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5750)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5750), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5752)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5750), CU.temp(stage(4), tr5752)), op=FixSub, results=List(CU.scalarOut(stage(5), x6378_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5752), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5756)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5756), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5758), CU.scalarOut(stage(7), bus_5758_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5756), CU.temp(stage(7), tr5758)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5759_scalar)))
    }
    val x6374_1 = StreamPipeline(name = "x6374_1", parent=x6374, deps=List(x6374_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5762 = CU.temp
      val tr5761 = CU.temp
      val x6374_unitcc = CounterChain.copy(x6374, "x6374_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5758_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5761)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5761), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5762)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5759_scalar), CU.temp(stage(2), tr5762)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6378_mc.len)))
    }
    val x6448 = MetaPipeline(name = "x6448", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr44 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6404 = CounterChain(name = "x6404", ctr44)
      var stage: List[Stage] = Nil
    }
    val x6423 = StreamController(name = "x6423", parent=x6448, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6423_unitcc = CounterChain(name = "x6423_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6423_0 = StreamPipeline(name = "x6423_0", parent=x6423, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5787 = CU.temp
      val tr5785 = CU.temp
      val tr5781 = CU.temp
      val tr5779 = CU.temp
      val tr5777 = CU.temp
      val tr5776 = CU.temp
      val x6423_unitcc = CounterChain.copy(x6423, "x6423_unitcc")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6404 = CounterChain.copy(x6448, "x6404")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5776)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5776), CU.ctr(stage(1), x6404(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5777)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5777), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5779)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5779), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5781)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5779), CU.temp(stage(4), tr5781)), op=FixSub, results=List(CU.scalarOut(stage(5), x6427_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5781), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5785)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5785), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5787), CU.scalarOut(stage(7), bus_5787_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5785), CU.temp(stage(7), tr5787)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5788_scalar)))
    }
    val x6423_1 = StreamPipeline(name = "x6423_1", parent=x6423, deps=List(x6423_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5791 = CU.temp
      val tr5790 = CU.temp
      val x6423_unitcc = CounterChain.copy(x6423, "x6423_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5787_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5790)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5790), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5791)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5788_scalar), CU.temp(stage(2), tr5791)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6427_mc.len)))
    }
    val x6497 = MetaPipeline(name = "x6497", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr49 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6453 = CounterChain(name = "x6453", ctr49)
      var stage: List[Stage] = Nil
    }
    val x6472 = StreamController(name = "x6472", parent=x6497, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6472_unitcc = CounterChain(name = "x6472_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6472_0 = StreamPipeline(name = "x6472_0", parent=x6472, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5816 = CU.temp
      val tr5814 = CU.temp
      val tr5810 = CU.temp
      val tr5808 = CU.temp
      val tr5806 = CU.temp
      val tr5805 = CU.temp
      val x6472_unitcc = CounterChain.copy(x6472, "x6472_unitcc")
      val x6453 = CounterChain.copy(x6497, "x6453")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5805)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5805), CU.ctr(stage(1), x6453(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5806)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5806), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5808)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5808), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5810)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5808), CU.temp(stage(4), tr5810)), op=FixSub, results=List(CU.scalarOut(stage(5), x6476_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5810), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5814)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5814), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5816), CU.scalarOut(stage(7), bus_5816_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5814), CU.temp(stage(7), tr5816)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5817_scalar)))
    }
    val x6472_1 = StreamPipeline(name = "x6472_1", parent=x6472, deps=List(x6472_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5820 = CU.temp
      val tr5819 = CU.temp
      val x6472_unitcc = CounterChain.copy(x6472, "x6472_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5816_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5819)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5819), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5820)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5817_scalar), CU.temp(stage(2), tr5820)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6476_mc.len)))
    }
    val x6546 = MetaPipeline(name = "x6546", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr54 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6502 = CounterChain(name = "x6502", ctr54)
      var stage: List[Stage] = Nil
    }
    val x6521 = StreamController(name = "x6521", parent=x6546, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6521_unitcc = CounterChain(name = "x6521_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6521_0 = StreamPipeline(name = "x6521_0", parent=x6521, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5845 = CU.temp
      val tr5843 = CU.temp
      val tr5839 = CU.temp
      val tr5837 = CU.temp
      val tr5835 = CU.temp
      val tr5834 = CU.temp
      val x6521_unitcc = CounterChain.copy(x6521, "x6521_unitcc")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6502 = CounterChain.copy(x6546, "x6502")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5834)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5834), CU.ctr(stage(1), x6502(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5835)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5835), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5837)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5837), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5839)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5837), CU.temp(stage(4), tr5839)), op=FixSub, results=List(CU.scalarOut(stage(5), x6525_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5839), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5843)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5843), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5845), CU.scalarOut(stage(7), bus_5845_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5843), CU.temp(stage(7), tr5845)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5846_scalar)))
    }
    val x6521_1 = StreamPipeline(name = "x6521_1", parent=x6521, deps=List(x6521_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5849 = CU.temp
      val tr5848 = CU.temp
      val x6521_unitcc = CounterChain.copy(x6521, "x6521_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5845_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5848)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5848), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5849)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5846_scalar), CU.temp(stage(2), tr5849)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6525_mc.len)))
    }
    val x6595 = MetaPipeline(name = "x6595", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr59 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6551 = CounterChain(name = "x6551", ctr59)
      var stage: List[Stage] = Nil
    }
    val x6570 = StreamController(name = "x6570", parent=x6595, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6570_unitcc = CounterChain(name = "x6570_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6570_0 = StreamPipeline(name = "x6570_0", parent=x6570, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5874 = CU.temp
      val tr5872 = CU.temp
      val tr5868 = CU.temp
      val tr5866 = CU.temp
      val tr5864 = CU.temp
      val tr5863 = CU.temp
      val x6551 = CounterChain.copy(x6595, "x6551")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6570_unitcc = CounterChain.copy(x6570, "x6570_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5863)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5863), CU.ctr(stage(1), x6551(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5864)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5864), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5866)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5866), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5868)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5866), CU.temp(stage(4), tr5868)), op=FixSub, results=List(CU.scalarOut(stage(5), x6574_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5868), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5872)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5872), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5874), CU.scalarOut(stage(7), bus_5874_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5872), CU.temp(stage(7), tr5874)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5875_scalar)))
    }
    val x6570_1 = StreamPipeline(name = "x6570_1", parent=x6570, deps=List(x6570_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5878 = CU.temp
      val tr5877 = CU.temp
      val x6570_unitcc = CounterChain.copy(x6570, "x6570_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5874_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5877)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5877), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5878)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5875_scalar), CU.temp(stage(2), tr5878)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6574_mc.len)))
    }
    val x6644 = MetaPipeline(name = "x6644", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr64 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6600 = CounterChain(name = "x6600", ctr64)
      var stage: List[Stage] = Nil
    }
    val x6619 = StreamController(name = "x6619", parent=x6644, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6619_unitcc = CounterChain(name = "x6619_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6619_0 = StreamPipeline(name = "x6619_0", parent=x6619, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5903 = CU.temp
      val tr5901 = CU.temp
      val tr5897 = CU.temp
      val tr5895 = CU.temp
      val tr5893 = CU.temp
      val tr5892 = CU.temp
      val x6619_unitcc = CounterChain.copy(x6619, "x6619_unitcc")
      val x6600 = CounterChain.copy(x6644, "x6600")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5892)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5892), CU.ctr(stage(1), x6600(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5893)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5893), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5895)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5895), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5897)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5895), CU.temp(stage(4), tr5897)), op=FixSub, results=List(CU.scalarOut(stage(5), x6623_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5897), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5901)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5901), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5903), CU.scalarOut(stage(7), bus_5903_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5901), CU.temp(stage(7), tr5903)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5904_scalar)))
    }
    val x6619_1 = StreamPipeline(name = "x6619_1", parent=x6619, deps=List(x6619_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5907 = CU.temp
      val tr5906 = CU.temp
      val x6619_unitcc = CounterChain.copy(x6619, "x6619_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5903_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5906)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5906), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5907)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5904_scalar), CU.temp(stage(2), tr5907)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6623_mc.len)))
    }
    val x6693 = MetaPipeline(name = "x6693", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr69 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6649 = CounterChain(name = "x6649", ctr69)
      var stage: List[Stage] = Nil
    }
    val x6668 = StreamController(name = "x6668", parent=x6693, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6668_unitcc = CounterChain(name = "x6668_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6668_0 = StreamPipeline(name = "x6668_0", parent=x6668, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5932 = CU.temp
      val tr5930 = CU.temp
      val tr5926 = CU.temp
      val tr5924 = CU.temp
      val tr5922 = CU.temp
      val tr5921 = CU.temp
      val x6668_unitcc = CounterChain.copy(x6668, "x6668_unitcc")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6649 = CounterChain.copy(x6693, "x6649")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5921)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5921), CU.ctr(stage(1), x6649(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5922)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5922), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5924)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5924), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5926)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5924), CU.temp(stage(4), tr5926)), op=FixSub, results=List(CU.scalarOut(stage(5), x6672_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5926), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5930)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5930), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5932), CU.scalarOut(stage(7), bus_5932_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5930), CU.temp(stage(7), tr5932)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5933_scalar)))
    }
    val x6668_1 = StreamPipeline(name = "x6668_1", parent=x6668, deps=List(x6668_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5936 = CU.temp
      val tr5935 = CU.temp
      val x6668_unitcc = CounterChain.copy(x6668, "x6668_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5932_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5935)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5935), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5936)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5933_scalar), CU.temp(stage(2), tr5936)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6672_mc.len)))
    }
    val x6742 = MetaPipeline(name = "x6742", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr74 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6698 = CounterChain(name = "x6698", ctr74)
      var stage: List[Stage] = Nil
    }
    val x6717 = StreamController(name = "x6717", parent=x6742, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6717_unitcc = CounterChain(name = "x6717_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6717_0 = StreamPipeline(name = "x6717_0", parent=x6717, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5961 = CU.temp
      val tr5959 = CU.temp
      val tr5955 = CU.temp
      val tr5953 = CU.temp
      val tr5951 = CU.temp
      val tr5950 = CU.temp
      val x6717_unitcc = CounterChain.copy(x6717, "x6717_unitcc")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6698 = CounterChain.copy(x6742, "x6698")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5950)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5950), CU.ctr(stage(1), x6698(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5951)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5951), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5953)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5953), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5955)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5953), CU.temp(stage(4), tr5955)), op=FixSub, results=List(CU.scalarOut(stage(5), x6721_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5955), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5959)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5959), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5961), CU.scalarOut(stage(7), bus_5961_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5959), CU.temp(stage(7), tr5961)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5962_scalar)))
    }
    val x6717_1 = StreamPipeline(name = "x6717_1", parent=x6717, deps=List(x6717_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5965 = CU.temp
      val tr5964 = CU.temp
      val x6717_unitcc = CounterChain.copy(x6717, "x6717_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5961_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5964)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5964), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5965)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5962_scalar), CU.temp(stage(2), tr5965)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6721_mc.len)))
    }
    val x6791 = MetaPipeline(name = "x6791", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr79 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6747 = CounterChain(name = "x6747", ctr79)
      var stage: List[Stage] = Nil
    }
    val x6766 = StreamController(name = "x6766", parent=x6791, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6766_unitcc = CounterChain(name = "x6766_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6766_0 = StreamPipeline(name = "x6766_0", parent=x6766, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5990 = CU.temp
      val tr5988 = CU.temp
      val tr5984 = CU.temp
      val tr5982 = CU.temp
      val tr5980 = CU.temp
      val tr5979 = CU.temp
      val x6766_unitcc = CounterChain.copy(x6766, "x6766_unitcc")
      val x6747 = CounterChain.copy(x6791, "x6747")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr5979)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5979), CU.ctr(stage(1), x6747(0))), op=FixAdd, results=List(CU.temp(stage(2), tr5980)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr5980), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr5982)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr5982), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr5984)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr5982), CU.temp(stage(4), tr5984)), op=FixSub, results=List(CU.scalarOut(stage(5), x6770_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr5984), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr5988)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr5988), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr5990), CU.scalarOut(stage(7), bus_5990_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr5988), CU.temp(stage(7), tr5990)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_5991_scalar)))
    }
    val x6766_1 = StreamPipeline(name = "x6766_1", parent=x6766, deps=List(x6766_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr5994 = CU.temp
      val tr5993 = CU.temp
      val x6766_unitcc = CounterChain.copy(x6766, "x6766_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_5990_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr5993)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr5993), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr5994)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_5991_scalar), CU.temp(stage(2), tr5994)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6770_mc.len)))
    }
    val x6840 = MetaPipeline(name = "x6840", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr84 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6796 = CounterChain(name = "x6796", ctr84)
      var stage: List[Stage] = Nil
    }
    val x6815 = StreamController(name = "x6815", parent=x6840, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6815_unitcc = CounterChain(name = "x6815_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6815_0 = StreamPipeline(name = "x6815_0", parent=x6815, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6019 = CU.temp
      val tr6017 = CU.temp
      val tr6013 = CU.temp
      val tr6011 = CU.temp
      val tr6009 = CU.temp
      val tr6008 = CU.temp
      val x6796 = CounterChain.copy(x6840, "x6796")
      val x6815_unitcc = CounterChain.copy(x6815, "x6815_unitcc")
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr6008)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6008), CU.ctr(stage(1), x6796(0))), op=FixAdd, results=List(CU.temp(stage(2), tr6009)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6009), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr6011)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6011), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr6013)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr6011), CU.temp(stage(4), tr6013)), op=FixSub, results=List(CU.scalarOut(stage(5), x6819_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6013), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr6017)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr6017), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr6019), CU.scalarOut(stage(7), bus_6019_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr6017), CU.temp(stage(7), tr6019)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_6020_scalar)))
    }
    val x6815_1 = StreamPipeline(name = "x6815_1", parent=x6815, deps=List(x6815_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6023 = CU.temp
      val tr6022 = CU.temp
      val x6815_unitcc = CounterChain.copy(x6815, "x6815_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_6019_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr6022)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6022), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr6023)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_6020_scalar), CU.temp(stage(2), tr6023)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6819_mc.len)))
    }
    val x6889 = MetaPipeline(name = "x6889", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr89 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x6845 = CounterChain(name = "x6845", ctr89)
      var stage: List[Stage] = Nil
    }
    val x6864 = StreamController(name = "x6864", parent=x6889, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6864_unitcc = CounterChain(name = "x6864_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6864_0 = StreamPipeline(name = "x6864_0", parent=x6864, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6048 = CU.temp
      val tr6046 = CU.temp
      val tr6042 = CU.temp
      val tr6040 = CU.temp
      val tr6038 = CU.temp
      val tr6037 = CU.temp
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6218 = CounterChain.copy(x7488, "x6218")
      val x6864_unitcc = CounterChain.copy(x6864, "x6864_unitcc")
      val x6845 = CounterChain.copy(x6889, "x6845")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6218(0)), CU.ctr(stage(0), x6265(0))), op=FixAdd, results=List(CU.temp(stage(1), tr6037)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6037), CU.ctr(stage(1), x6845(0))), op=FixAdd, results=List(CU.temp(stage(2), tr6038)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6038), Const("768i")), op=FixMul, results=List(CU.temp(stage(3), tr6040)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr6040), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr6042)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr6040), CU.temp(stage(4), tr6042)), op=FixSub, results=List(CU.scalarOut(stage(5), x6868_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr6042), Const("768i")), op=FixAdd, results=List(CU.temp(stage(6), tr6046)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr6046), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr6048), CU.scalarOut(stage(7), bus_6048_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr6046), CU.temp(stage(7), tr6048)), op=FixSub, results=List(CU.scalarOut(stage(8), bus_6049_scalar)))
    }
    val x6864_1 = StreamPipeline(name = "x6864_1", parent=x6864, deps=List(x6864_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6052 = CU.temp
      val tr6051 = CU.temp
      val x6864_unitcc = CounterChain.copy(x6864, "x6864_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_6048_scalar), Const("0i")), op=FixNeq, results=List(CU.temp(stage(1), tr6051)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6051), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(2), tr6052)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), bus_6049_scalar), CU.temp(stage(2), tr6052)), op=FixAdd, results=List(CU.scalarOut(stage(3), x6868_mc.len)))
    }
    val x6919 = Sequential(name = "x6919", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6919_unitcc = CounterChain(name = "x6919_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6910_0 = Pipeline(name = "x6910_0", parent=x6919, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x6896 = CounterChain(name = "x6896", ctr10)
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x6292_x6899 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6329_mc.vdata).rdAddr(x6896(0))
      val x6214_x6902 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x6896(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6292_x6899.load, x6214_x6902.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6076) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6076), op=Bypass, results=List(CU.scalarOut(stage(2), x6894_scalar)))
    }
    val x6917_0 = UnitPipeline(name = "x6917_0", parent=x6919, deps=List(x6910_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6917_unitcc = CounterChain(name = "x6917_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x6913 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6894_scalar), x6213_x6913.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6268_scalar)))
    }
    val x6945 = Sequential(name = "x6945", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6945_unitcc = CounterChain(name = "x6945_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6936_0 = Pipeline(name = "x6936_0", parent=x6945, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr12 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x6922 = CounterChain(name = "x6922", ctr12)
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x6293_x6925 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6378_mc.vdata).rdAddr(x6922(0))
      val x6214_x6928 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x6922(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6293_x6925.load, x6214_x6928.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6095) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6095), op=Bypass, results=List(CU.scalarOut(stage(2), x6920_scalar)))
    }
    val x6943_0 = UnitPipeline(name = "x6943_0", parent=x6945, deps=List(x6936_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6943_unitcc = CounterChain(name = "x6943_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x6939 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6920_scalar), x6213_x6939.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6269_scalar)))
    }
    val x6971 = Sequential(name = "x6971", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6971_unitcc = CounterChain(name = "x6971_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6962_0 = Pipeline(name = "x6962_0", parent=x6971, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr14 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x6948 = CounterChain(name = "x6948", ctr14)
      val x6294_x6951 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6427_mc.vdata).rdAddr(x6948(0))
      val x6214_x6954 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x6948(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6294_x6951.load, x6214_x6954.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6114) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6114), op=Bypass, results=List(CU.scalarOut(stage(2), x6946_scalar)))
    }
    val x6969_0 = UnitPipeline(name = "x6969_0", parent=x6971, deps=List(x6962_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6969_unitcc = CounterChain(name = "x6969_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x6965 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6946_scalar), x6213_x6965.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6270_scalar)))
    }
    val x6997 = Sequential(name = "x6997", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6997_unitcc = CounterChain(name = "x6997_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6988_0 = Pipeline(name = "x6988_0", parent=x6997, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr16 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x6974 = CounterChain(name = "x6974", ctr16)
      val x6295_x6977 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6476_mc.vdata).rdAddr(x6974(0))
      val x6214_x6980 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x6974(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6295_x6977.load, x6214_x6980.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6133) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6133), op=Bypass, results=List(CU.scalarOut(stage(2), x6972_scalar)))
    }
    val x6995_0 = UnitPipeline(name = "x6995_0", parent=x6997, deps=List(x6988_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6995_unitcc = CounterChain(name = "x6995_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x6991 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6972_scalar), x6213_x6991.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6271_scalar)))
    }
    val x7023 = Sequential(name = "x7023", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7023_unitcc = CounterChain(name = "x7023_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7014_0 = Pipeline(name = "x7014_0", parent=x7023, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr18 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7000 = CounterChain(name = "x7000", ctr18)
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x6296_x7003 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6525_mc.vdata).rdAddr(x7000(0))
      val x6214_x7006 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7000(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6296_x7003.load, x6214_x7006.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6152) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6152), op=Bypass, results=List(CU.scalarOut(stage(2), x6998_scalar)))
    }
    val x7021_0 = UnitPipeline(name = "x7021_0", parent=x7023, deps=List(x7014_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7021_unitcc = CounterChain(name = "x7021_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7017 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6998_scalar), x6213_x7017.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6272_scalar)))
    }
    val x7049 = Sequential(name = "x7049", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7049_unitcc = CounterChain(name = "x7049_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7040_0 = Pipeline(name = "x7040_0", parent=x7049, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr20 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7026 = CounterChain(name = "x7026", ctr20)
      val x6297_x7029 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6574_mc.vdata).rdAddr(x7026(0))
      val x6214_x7032 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7026(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6297_x7029.load, x6214_x7032.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6171) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6171), op=Bypass, results=List(CU.scalarOut(stage(2), x7024_scalar)))
    }
    val x7047_0 = UnitPipeline(name = "x7047_0", parent=x7049, deps=List(x7040_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x7047_unitcc = CounterChain(name = "x7047_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6213_x7043 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7024_scalar), x6213_x7043.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6273_scalar)))
    }
    val x7075 = Sequential(name = "x7075", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7075_unitcc = CounterChain(name = "x7075_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7066_0 = Pipeline(name = "x7066_0", parent=x7075, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr22 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7052 = CounterChain(name = "x7052", ctr22)
      val x6298_x7055 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6623_mc.vdata).rdAddr(x7052(0))
      val x6214_x7058 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7052(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6298_x7055.load, x6214_x7058.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6190) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6190), op=Bypass, results=List(CU.scalarOut(stage(2), x7050_scalar)))
    }
    val x7073_0 = UnitPipeline(name = "x7073_0", parent=x7075, deps=List(x7066_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7073_unitcc = CounterChain(name = "x7073_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7069 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7050_scalar), x6213_x7069.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6274_scalar)))
    }
    val x7101 = Sequential(name = "x7101", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7101_unitcc = CounterChain(name = "x7101_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7092_0 = Pipeline(name = "x7092_0", parent=x7101, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr24 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7078 = CounterChain(name = "x7078", ctr24)
      val x6299_x7081 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6672_mc.vdata).rdAddr(x7078(0))
      val x6214_x7084 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7078(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6299_x7081.load, x6214_x7084.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6209) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6209), op=Bypass, results=List(CU.scalarOut(stage(2), x7076_scalar)))
    }
    val x7099_0 = UnitPipeline(name = "x7099_0", parent=x7101, deps=List(x7092_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7099_unitcc = CounterChain(name = "x7099_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7095 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7076_scalar), x6213_x7095.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6275_scalar)))
    }
    val x7127 = Sequential(name = "x7127", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7127_unitcc = CounterChain(name = "x7127_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7118_0 = Pipeline(name = "x7118_0", parent=x7127, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr26 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7104 = CounterChain(name = "x7104", ctr26)
      val x6300_x7107 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6721_mc.vdata).rdAddr(x7104(0))
      val x6214_x7110 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7104(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6300_x7107.load, x6214_x7110.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6228) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6228), op=Bypass, results=List(CU.scalarOut(stage(2), x7102_scalar)))
    }
    val x7125_0 = UnitPipeline(name = "x7125_0", parent=x7127, deps=List(x7118_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7125_unitcc = CounterChain(name = "x7125_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7121 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7102_scalar), x6213_x7121.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6276_scalar)))
    }
    val x7153 = Sequential(name = "x7153", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7153_unitcc = CounterChain(name = "x7153_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7144_0 = Pipeline(name = "x7144_0", parent=x7153, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr28 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7130 = CounterChain(name = "x7130", ctr28)
      val x6301_x7133 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6770_mc.vdata).rdAddr(x7130(0))
      val x6214_x7136 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7130(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6301_x7133.load, x6214_x7136.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6247) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6247), op=Bypass, results=List(CU.scalarOut(stage(2), x7128_scalar)))
    }
    val x7151_0 = UnitPipeline(name = "x7151_0", parent=x7153, deps=List(x7144_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7151_unitcc = CounterChain(name = "x7151_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7147 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7128_scalar), x6213_x7147.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6277_scalar)))
    }
    val x7179 = Sequential(name = "x7179", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7179_unitcc = CounterChain(name = "x7179_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7170_0 = Pipeline(name = "x7170_0", parent=x7179, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr30 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7156 = CounterChain(name = "x7156", ctr30)
      val x6302_x7159 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6819_mc.vdata).rdAddr(x7156(0))
      val x6214_x7162 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7156(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6302_x7159.load, x6214_x7162.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6266) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6266), op=Bypass, results=List(CU.scalarOut(stage(2), x7154_scalar)))
    }
    val x7177_0 = UnitPipeline(name = "x7177_0", parent=x7179, deps=List(x7170_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7177_unitcc = CounterChain(name = "x7177_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7173 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7154_scalar), x6213_x7173.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6278_scalar)))
    }
    val x7205 = Sequential(name = "x7205", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7205_unitcc = CounterChain(name = "x7205_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7196_0 = Pipeline(name = "x7196_0", parent=x7205, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr32 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7182 = CounterChain(name = "x7182", ctr32)
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x6303_x7185 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6868_mc.vdata).rdAddr(x7182(0))
      val x6214_x7188 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7182(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6303_x7185.load, x6214_x7188.load), op=FltMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr6285) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr6285), op=Bypass, results=List(CU.scalarOut(stage(2), x7180_scalar)))
    }
    val x7203_0 = UnitPipeline(name = "x7203_0", parent=x7205, deps=List(x7196_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7203_unitcc = CounterChain(name = "x7203_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6265 = CounterChain.copy(x7486, "x6265")
      val x6213_x7199 = SemiFIFO(size = 192, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6242_mc.vdata).rdAddr(x6265(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7180_scalar), x6213_x7199.load), op=FltSub, results=List(CU.scalarOut(stage(1), x6279_scalar)))
    }
    val x7245_0 = Pipeline(name = "x7245_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6299 = CU.temp
      val ctr38 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7220 = CounterChain(name = "x7220", ctr38)
      val x6292_x7234 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6329_mc.vdata).rdAddr(x7220(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6292_x7234.load, CU.scalarIn(stage(0), x6268_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6299)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6299), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6280_vector)))
    }
    val x7259_0 = Pipeline(name = "x7259_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6311 = CU.temp
      val ctr43 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7221 = CounterChain(name = "x7221", ctr43)
      val x6293_x7248 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6378_mc.vdata).rdAddr(x7221(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6293_x7248.load, CU.scalarIn(stage(0), x6269_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6311)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6311), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6281_vector)))
    }
    val x7273_0 = Pipeline(name = "x7273_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6323 = CU.temp
      val ctr48 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7222 = CounterChain(name = "x7222", ctr48)
      val x6294_x7262 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6427_mc.vdata).rdAddr(x7222(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6294_x7262.load, CU.scalarIn(stage(0), x6270_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6323)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6323), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6282_vector)))
    }
    val x7287_0 = Pipeline(name = "x7287_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6335 = CU.temp
      val ctr53 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7223 = CounterChain(name = "x7223", ctr53)
      val x6295_x7276 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6476_mc.vdata).rdAddr(x7223(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6295_x7276.load, CU.scalarIn(stage(0), x6271_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6335)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6335), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6283_vector)))
    }
    val x7301_0 = Pipeline(name = "x7301_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6347 = CU.temp
      val ctr58 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7224 = CounterChain(name = "x7224", ctr58)
      val x6296_x7290 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6525_mc.vdata).rdAddr(x7224(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6296_x7290.load, CU.scalarIn(stage(0), x6272_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6347)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6347), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6284_vector)))
    }
    val x7315_0 = Pipeline(name = "x7315_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6359 = CU.temp
      val ctr63 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7225 = CounterChain(name = "x7225", ctr63)
      val x6297_x7304 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6574_mc.vdata).rdAddr(x7225(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6297_x7304.load, CU.scalarIn(stage(0), x6273_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6359)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6359), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6285_vector)))
    }
    val x7329_0 = Pipeline(name = "x7329_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6371 = CU.temp
      val ctr68 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7226 = CounterChain(name = "x7226", ctr68)
      val x6298_x7318 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6623_mc.vdata).rdAddr(x7226(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6298_x7318.load, CU.scalarIn(stage(0), x6274_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6371)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6371), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6286_vector)))
    }
    val x7343_0 = Pipeline(name = "x7343_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6383 = CU.temp
      val ctr73 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7227 = CounterChain(name = "x7227", ctr73)
      val x6299_x7332 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6672_mc.vdata).rdAddr(x7227(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6299_x7332.load, CU.scalarIn(stage(0), x6275_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6383)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6383), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6287_vector)))
    }
    val x7357_0 = Pipeline(name = "x7357_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6395 = CU.temp
      val ctr78 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7228 = CounterChain(name = "x7228", ctr78)
      val x6300_x7346 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6721_mc.vdata).rdAddr(x7228(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6300_x7346.load, CU.scalarIn(stage(0), x6276_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6395)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6395), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6288_vector)))
    }
    val x7371_0 = Pipeline(name = "x7371_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6407 = CU.temp
      val ctr83 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7229 = CounterChain(name = "x7229", ctr83)
      val x6301_x7360 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6770_mc.vdata).rdAddr(x7229(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6301_x7360.load, CU.scalarIn(stage(0), x6277_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6407)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6407), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6289_vector)))
    }
    val x7385_0 = Pipeline(name = "x7385_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6419 = CU.temp
      val ctr88 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7230 = CounterChain(name = "x7230", ctr88)
      val x6302_x7374 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6819_mc.vdata).rdAddr(x7230(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6302_x7374.load, CU.scalarIn(stage(0), x6278_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6419)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6419), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6290_vector)))
    }
    val x7399_0 = Pipeline(name = "x7399_0", parent=x7486, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6431 = CU.temp
      val ctr93 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7231 = CounterChain(name = "x7231", ctr93)
      val x6303_x7388 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6868_mc.vdata).rdAddr(x7231(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6303_x7388.load, CU.scalarIn(stage(0), x6279_scalar)), op=FltMul, results=List(CU.temp(stage(1), tr6431)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6431), Const("1i")), op=FltMul, results=List(CU.vecOut(stage(2), x6291_vector)))
    }
    val x7484 = StreamController(name = "x7484", parent=x7486, deps=List(x7329_0, x7385_0, x7371_0, x7287_0, x7273_0, x7301_0, x7357_0, x7259_0, x7245_0, x7315_0, x7399_0, x7343_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ctr94 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x6267 = CounterChain(name = "x6267", ctr94)
      var stage: List[Stage] = Nil
    }
    val x7484_0 = StreamPipeline(name = "x7484_0", parent=x7484, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6488 = CU.temp
      val tr6483 = CU.temp
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x7229 = CounterChain.copy(x7371_0, "x7229")
      val x7228 = CounterChain.copy(x7357_0, "x7228")
      val x7230 = CounterChain.copy(x7385_0, "x7230")
      val x7231 = CounterChain.copy(x7399_0, "x7231")
      val x6290_x7434 = SRAM(size = 768, writeCtr = x7230(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6290_vector).rdAddr(x6267(0)).wtAddr(x7230(0))
      val x6291_x7437 = SRAM(size = 768, writeCtr = x7231(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6291_vector).rdAddr(x6267(0)).wtAddr(x7231(0))
      val x6289_x7431 = SRAM(size = 768, writeCtr = x7229(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6289_vector).rdAddr(x6267(0)).wtAddr(x7229(0))
      val x6288_x7428 = SRAM(size = 768, writeCtr = x7228(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6288_vector).rdAddr(x6267(0)).wtAddr(x7228(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6288_x7428.load, x6289_x7431.load), op=FltAdd, results=List(CU.temp(stage(1), tr6483)))
      Stage(stage(2), operands=List(CU.load(stage(1), x6290_x7434), CU.load(stage(1), x6291_x7437)), op=FltAdd, results=List(CU.temp(stage(2), tr6488)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6483), CU.temp(stage(2), tr6488)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_6489_vector)))
    }
    val x7484_1 = StreamPipeline(name = "x7484_1", parent=x7484, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6499 = CU.temp
      val tr6494 = CU.temp
      val x7221 = CounterChain.copy(x7259_0, "x7221")
      val x7222 = CounterChain.copy(x7273_0, "x7222")
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x7223 = CounterChain.copy(x7287_0, "x7223")
      val x7220 = CounterChain.copy(x7245_0, "x7220")
      val x6282_x7410 = SRAM(size = 768, writeCtr = x7222(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6282_vector).rdAddr(x6267(0)).wtAddr(x7222(0))
      val x6283_x7413 = SRAM(size = 768, writeCtr = x7223(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6283_vector).rdAddr(x6267(0)).wtAddr(x7223(0))
      val x6280_x7404 = SRAM(size = 768, writeCtr = x7220(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6280_vector).rdAddr(x6267(0)).wtAddr(x7220(0))
      val x6281_x7407 = SRAM(size = 768, writeCtr = x7221(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6281_vector).rdAddr(x6267(0)).wtAddr(x7221(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6280_x7404.load, x6281_x7407.load), op=FltAdd, results=List(CU.temp(stage(1), tr6494)))
      Stage(stage(2), operands=List(CU.load(stage(1), x6282_x7410), CU.load(stage(1), x6283_x7413)), op=FltAdd, results=List(CU.temp(stage(2), tr6499)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6494), CU.temp(stage(2), tr6499)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_6500_vector)))
    }
    val x7484_2 = StreamPipeline(name = "x7484_2", parent=x7484, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6510 = CU.temp
      val tr6505 = CU.temp
      val x7225 = CounterChain.copy(x7315_0, "x7225")
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x7227 = CounterChain.copy(x7343_0, "x7227")
      val x7226 = CounterChain.copy(x7329_0, "x7226")
      val x7224 = CounterChain.copy(x7301_0, "x7224")
      val x6287_x7425 = SRAM(size = 768, writeCtr = x7227(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6287_vector).rdAddr(x6267(0)).wtAddr(x7227(0))
      val x6286_x7422 = SRAM(size = 768, writeCtr = x7226(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6286_vector).rdAddr(x6267(0)).wtAddr(x7226(0))
      val x6285_x7419 = SRAM(size = 768, writeCtr = x7225(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6285_vector).rdAddr(x6267(0)).wtAddr(x7225(0))
      val x6284_x7416 = SRAM(size = 768, writeCtr = x7224(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6284_vector).rdAddr(x6267(0)).wtAddr(x7224(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6284_x7416.load, x6285_x7419.load), op=FltAdd, results=List(CU.temp(stage(1), tr6505)))
      Stage(stage(2), operands=List(CU.load(stage(1), x6286_x7422), CU.load(stage(1), x6287_x7425)), op=FltAdd, results=List(CU.temp(stage(2), tr6510)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6505), CU.temp(stage(2), tr6510)), op=FltAdd, results=List(CU.vecOut(stage(3), bus_6511_vector)))
    }
    val x7484_3 = StreamPipeline(name = "x7484_3", parent=x7484, deps=List(x7484_1, x7484_2, x7484_0)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr6513 = CU.temp
      val tr6512 = CU.temp
      val x6267 = CounterChain.copy(x7484, "x6267")
      val x6214_x7440 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).rdAddr(x6267(0))
      val bus_6500_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6500_vector)
      val bus_6511_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6511_vector)
      val bus_6489_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_6489_vector)
      val wr6515 = CU.wtAddr(x6214_x7440)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(bus_6500_fifo.load, bus_6511_fifo.load), op=FltAdd, results=List(CU.temp(stage(1), tr6512)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr6512), bus_6489_fifo.load), op=FltAdd, results=List(CU.temp(stage(2), tr6513)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr6513), CU.load(stage(2), x6214_x7440)), op=FltAdd, results=List(CU.vecOut(stage(3), x6214_vector), CU.store(stage(3), x6214_x7440)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x6267(0))), op=Bypass, results=List(CU.wtAddr(stage(4), wr6515)))
    }
    val x7510 = StreamController(name = "x7510", parent=x7512, deps=List(x7490)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7510_unitcc = CounterChain(name = "x7510_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7512_leafX = UnitPipeline(name = "x7512_leafX", parent=x7512, deps=List(x7510)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7512_unitcc = CounterChain(name = "x7512_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7495_0 = UnitPipeline(name = "x7495_0", parent=x7510, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7495_unitcc = CounterChain(name = "x7495_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x7508_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x7508_mc.len)))
    }
    val x7506_0 = Pipeline(name = "x7506_0", parent=x7510, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x6267 = CounterChain.copy(x7484, "x6267")
      val ctr95 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7497 = CounterChain(name = "x7497", ctr95)
      val x6214_x7500 = SRAM(size = 768, writeCtr = x6267(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6214_vector).rdAddr(x7497(0)).wtAddr(x6267(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6214_x7500.load), op=Bypass, results=List(CU.vecOut(stage(1), x7508_mc.vdata)))
    }
    val x7486_leaf = UnitPipeline(name = "x7486_leaf", parent=x7486, deps=List(x6742, x7179, x7023, x6693, x7049, x6399, x6448, x6350, x6644, x7484, x7127, x6919, x7101, x7075, x6889, x7205, x6791, x6997, x6497, x6945, x7153, x6971, x6546, x6595, x6840)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x7486_unitcc = CounterChain(name = "x7486_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }

  }
}
