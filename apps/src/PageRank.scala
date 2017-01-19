import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object PageRankDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x6326_argin = ArgIn("x6326")
    val x6948_x6973_data_vector = Vector("x6948_x6973_data")
    val x6335_oc = OffChip("x6335")
    val x6333_oc = OffChip("x6333")
    val bus_588_vector = Vector("bus_588")
    val x6646_scalar = Scalar("x6646")
    val x6909_scalar = Scalar("x6909")
    val x6933_scalar = Scalar("x6933")
    val x6332_oc = OffChip("x6332")
    val bus_584_vector = Vector("bus_584")
    val x6794_scalar = Scalar("x6794")
    val x6334_oc = OffChip("x6334")
    val bus_585_vector = Vector("bus_585")
    val x6955_scalar = Scalar("x6955")
    val x6908_x6963_data_vector = Vector("x6908_x6963_data")
    val x6327_argin = ArgIn("x6327")
    val x6325_argin = ArgIn("x6325")
    val x6948_x6973_addr_vector = Vector("x6948_x6973_addr")
    val bus_587_vector = Vector("bus_587")
    val x6795_scalar = Scalar("x6795")
    val x6336_oc = OffChip("x6336")
    val x6908_scalar = Scalar("x6908")
    val x7023_mc = MemoryController(TileStore, x6332_oc)
    val x6836_mc = MemoryController(TileLoad, x6333_oc)
    val x6884_mc = MemoryController(TileLoad, x6334_oc)
    val x6673_mc = MemoryController(TileLoad, x6332_oc)
    val x6767_mc = MemoryController(TileLoad, x6336_oc)
    val x6950_mc = MemoryController(Gather, x6332_oc)
    val x6720_mc = MemoryController(TileLoad, x6335_oc)
    val x7031 = Sequential(name = "x7031", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7031_unitcc = CounterChain(name = "x7031_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7029 = Sequential(name = "x7029", parent=x7031, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, CU.scalarIn(stage0, x6325_argin).out, Const("1i").out) // Counter
      val x6642 = CounterChain(name = "x6642", ctr1)
      var stage: List[Stage] = Nil
    }
    val x7027 = Sequential(name = "x7027", parent=x7029, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, CU.scalarIn(stage0, x6326_argin).out, Const("768i").out) // Counter
      val x6645 = CounterChain(name = "x6645", ctr3)
      var stage: List[Stage] = Nil
    }
    val x6696 = Sequential(name = "x6696", parent=x7027, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6696_unitcc = CounterChain(name = "x6696_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6694 = StreamController(name = "x6694", parent=x6696, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6694_unitcc = CounterChain(name = "x6694_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6669_0 = UnitPipeline(name = "x6669_0", parent=x6694, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr406 = CU.temp
      val tr405 = CU.temp
      val tr403 = CU.temp
      val tr402 = CU.temp
      val tr400 = CU.temp
      val tr395 = CU.temp
      val x6645 = CounterChain.copy(x7027, "x6645")
      val x6669_unitcc = CounterChain(name = "x6669_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6645(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr395)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6645(0)), CU.temp(stage(1), tr395)), op=FixSub, results=List(CU.scalarOut(stage(2), x6673_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr395), Const("768i")), op=FixAdd, results=List(CU.temp(stage(3), tr400)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr400), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr402)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr400), CU.temp(stage(4), tr402)), op=FixSub, results=List(CU.temp(stage(5), tr403)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr402), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr405)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr405), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr406)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr403), CU.temp(stage(7), tr406)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6673_mc.len)))
    }
    val x6743 = Sequential(name = "x6743", parent=x7027, deps=List(x6696)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6743_unitcc = CounterChain(name = "x6743_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6741 = StreamController(name = "x6741", parent=x6743, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6741_unitcc = CounterChain(name = "x6741_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6716_0 = UnitPipeline(name = "x6716_0", parent=x6741, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr430 = CU.temp
      val tr429 = CU.temp
      val tr427 = CU.temp
      val tr426 = CU.temp
      val tr424 = CU.temp
      val tr419 = CU.temp
      val x6716_unitcc = CounterChain(name = "x6716_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6645 = CounterChain.copy(x7027, "x6645")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6645(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr419)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6645(0)), CU.temp(stage(1), tr419)), op=FixSub, results=List(CU.scalarOut(stage(2), x6720_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr419), Const("768i")), op=FixAdd, results=List(CU.temp(stage(3), tr424)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr424), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr426)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr424), CU.temp(stage(4), tr426)), op=FixSub, results=List(CU.temp(stage(5), tr427)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr426), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr429)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr429), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr430)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr427), CU.temp(stage(7), tr430)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6720_mc.len)))
    }
    val x6790 = Sequential(name = "x6790", parent=x7027, deps=List(x6743)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6790_unitcc = CounterChain(name = "x6790_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6788 = StreamController(name = "x6788", parent=x6790, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6788_unitcc = CounterChain(name = "x6788_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6763_0 = UnitPipeline(name = "x6763_0", parent=x6788, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr454 = CU.temp
      val tr453 = CU.temp
      val tr451 = CU.temp
      val tr450 = CU.temp
      val tr448 = CU.temp
      val tr443 = CU.temp
      val x6763_unitcc = CounterChain(name = "x6763_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6645 = CounterChain.copy(x7027, "x6645")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6645(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr443)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6645(0)), CU.temp(stage(1), tr443)), op=FixSub, results=List(CU.scalarOut(stage(2), x6767_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr443), Const("768i")), op=FixAdd, results=List(CU.temp(stage(3), tr448)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr448), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr450)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr448), CU.temp(stage(4), tr450)), op=FixSub, results=List(CU.temp(stage(5), tr451)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr450), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr453)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr453), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr454)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr451), CU.temp(stage(7), tr454)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6767_mc.len)))
    }
    val x7005 = Sequential(name = "x7005", parent=x7027, deps=List(x6790)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x6792 = CounterChain(name = "x6792", ctr7)
      var stage: List[Stage] = Nil
    }
    val x6799_0 = UnitPipeline(name = "x6799_0", parent=x7005, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6792 = CounterChain.copy(x7005, "x6792")
      val x6799_unitcc = CounterChain(name = "x6799_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6648_x6796 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6720_mc.vdata).rdAddr(x6792(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6648_x6796.load), op=Bypass, results=List(CU.scalarOut(stage(1), x6794_scalar)))
    }
    val x6808_0 = UnitPipeline(name = "x6808_0", parent=x7005, deps=List(x6799_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6792 = CounterChain.copy(x7005, "x6792")
      val x6808_unitcc = CounterChain(name = "x6808_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6649_x6805 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6767_mc.vdata).rdAddr(x6792(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6649_x6805.load), op=Bypass, results=List(CU.scalarOut(stage(1), x6795_scalar)))
    }
    val x6859 = Sequential(name = "x6859", parent=x7005, deps=List(x6808_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6859_unitcc = CounterChain(name = "x6859_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6857 = StreamController(name = "x6857", parent=x6859, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6857_unitcc = CounterChain(name = "x6857_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6832_0 = UnitPipeline(name = "x6832_0", parent=x6857, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr489 = CU.temp
      val tr488 = CU.temp
      val tr486 = CU.temp
      val tr485 = CU.temp
      val tr483 = CU.temp
      val tr479 = CU.temp
      val x6832_unitcc = CounterChain(name = "x6832_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6794_scalar), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr479)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x6794_scalar), CU.temp(stage(1), tr479)), op=FixSub, results=List(CU.scalarOut(stage(2), x6836_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr479), CU.scalarIn(stage(2), x6795_scalar)), op=FixAdd, results=List(CU.temp(stage(3), tr483)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr483), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr485)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr483), CU.temp(stage(4), tr485)), op=FixSub, results=List(CU.temp(stage(5), tr486)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr485), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr488)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr488), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr489)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr486), CU.temp(stage(7), tr489)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6836_mc.len)))
    }
    val x6907 = Sequential(name = "x6907", parent=x7005, deps=List(x6859)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6907_unitcc = CounterChain(name = "x6907_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6905 = StreamController(name = "x6905", parent=x6907, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6905_unitcc = CounterChain(name = "x6905_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6880_0 = UnitPipeline(name = "x6880_0", parent=x6905, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr515 = CU.temp
      val tr514 = CU.temp
      val tr512 = CU.temp
      val tr511 = CU.temp
      val tr509 = CU.temp
      val tr505 = CU.temp
      val x6880_unitcc = CounterChain(name = "x6880_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6794_scalar), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr505)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x6794_scalar), CU.temp(stage(1), tr505)), op=FixSub, results=List(CU.scalarOut(stage(2), x6884_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr505), CU.scalarIn(stage(2), x6795_scalar)), op=FixAdd, results=List(CU.temp(stage(3), tr509)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr509), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr511)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr509), CU.temp(stage(4), tr511)), op=FixSub, results=List(CU.temp(stage(5), tr512)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr511), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr514)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr514), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr515)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr512), CU.temp(stage(7), tr515)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6884_mc.len)))
    }
    val x6932 = Sequential(name = "x6932", parent=x7005, deps=List(x6907)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, CU.scalarIn(stage0, x6795_scalar).out, Const("1i").out) // Counter
      val x6914 = CounterChain(name = "x6914", ctr13)
      var stage: List[Stage] = Nil
    }
    val x6930_0 = UnitPipeline(name = "x6930_0", parent=x6932, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr542 = CU.temp
      val tr539 = CU.temp
      val tr538 = CU.temp
      val tr537 = CU.temp
      val tr535 = CU.temp
      val ar534 = CU.accum(init = Const("-1i"))
      val x6645 = CounterChain.copy(x7027, "x6645")
      val x6930_unitcc = CounterChain(name = "x6930_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6914 = CounterChain.copy(x6932, "x6914")
      val x6809_x6917 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6836_mc.vdata).rdAddr(x6914(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6645(0)), x6809_x6917.load), op=FixLeq, results=List(CU.temp(stage(1), tr535)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6645(0)), Const("768i")), op=FixAdd, results=List(CU.temp(stage(2), tr537)))
      Stage(stage(3), operands=List(CU.load(stage(2), x6809_x6917), CU.temp(stage(2), tr537)), op=FixLt, results=List(CU.temp(stage(3), tr538)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr535), CU.temp(stage(3), tr538)), op=BitAnd, results=List(CU.temp(stage(4), tr539)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr539), Const("0i"), Const("1i")), op=Mux, results=List(CU.temp(stage(5), tr542)))
      Stage(stage(6), operands=List(CU.accum(stage(6), ar534), CU.temp(stage(5), tr542)), op=FixAdd, results=List(CU.scalarOut(stage(6), x6909_scalar), CU.accum(stage(6), ar534)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr539), CU.accum(stage(6), ar534), Const("-1i")), op=Mux, results=List(CU.scalarOut(stage(7), x6908_scalar)))
    }
    val x6947 = Sequential(name = "x6947", parent=x7005, deps=List(x6932)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, CU.scalarIn(stage0, x6795_scalar).out, Const("1i").out) // Counter
      val x6935 = CounterChain(name = "x6935", ctr11)
      var stage: List[Stage] = Nil
    }
    val x6945_0 = UnitPipeline(name = "x6945_0", parent=x6947, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6930_unitcc = CounterChain.copy(x6930_0, "x6930_unitcc")
      val x6935 = CounterChain.copy(x6947, "x6935")
      val x6914 = CounterChain.copy(x6932, "x6914")
      val x6945_unitcc = CounterChain(name = "x6945_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6809_x6941 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6836_mc.vdata).rdAddr(x6935(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6809_x6941.load), op=Bypass, results=List(CU.scalarOut(stage(1), x6933_scalar)))
    }
    val x6954 = StreamController(name = "x6954", parent=x7005, deps=List(x6947)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6954_unitcc = CounterChain(name = "x6954_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6950_0 = StreamPipeline(name = "x6950_0", parent=x6954, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6930_unitcc = CounterChain.copy(x6930_0, "x6930_unitcc")
      val x6945_unitcc = CounterChain.copy(x6945_0, "x6945_unitcc")
      val x6935 = CounterChain.copy(x6947, "x6935")
      val ctr23 = (Const("0i").out, CU.scalarIn(stage0, x6909_scalar).out, Const("16i").out) // Counter
      val x6950_cc = CounterChain(name = "x6950_cc", ctr23)
      val x6914 = CounterChain.copy(x6932, "x6914")
      val x6933_x6950 = SRAM(size = 768, writeCtr = x6945_unitcc(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6933_scalar).rdAddr(x6950_cc(0))
      val x6908_x6939_x6950 = SRAM(size = 768, writeCtr = x6930_unitcc(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6908_scalar).rdAddr(x6935(0)).wtAddr(x6914(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x6933_x6950))
      Stage(stage(1), operands=List(x6908_x6939_x6950.load), op=Bypass, results=List(x6933_x6950.writeAddr))
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6933_x6950.load), op=Bypass, results=List(CU.vecOut(stage(1), x6950_mc.addrs)))
    }
    val x6992 = StreamController(name = "x6992", parent=x7005, deps=List(x6954)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr9 = (Const("0i").out, CU.scalarIn(stage0, x6795_scalar).out, Const("1i").out) // Counter
      val x6957 = CounterChain(name = "x6957", ctr9)
      var stage: List[Stage] = Nil
    }
    val x6992_0 = StreamPipeline(name = "x6992_0", parent=x6992, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6930_unitcc = CounterChain.copy(x6930_0, "x6930_unitcc")
      val x6957 = CounterChain.copy(x6992, "x6957")
      val x6914 = CounterChain.copy(x6932, "x6914")
      val x6908_x6963 = SRAM(size = 768, writeCtr = x6930_unitcc(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6908_scalar).rdAddr(x6957(0)).wtAddr(x6914(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6908_x6963.load), op=Bypass, results=List(CU.vecOut(stage(1), x6908_x6963_data_vector)))
    }
    val x6992_1 = StreamPipeline(name = "x6992_1", parent=x6992, deps=List(x6992_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6908_x6963_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6908_x6963_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6908_x6963_data_fifo.load), op=Bypass, results=List(CU.vecOut(stage(1), x6948_x6973_addr_vector)))
    }
    val x6992_2 = StreamPipeline(name = "x6992_2", parent=x6992, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr583 = CU.temp
      val x6792 = CounterChain.copy(x7005, "x6792")
      val x6645 = CounterChain.copy(x7027, "x6645")
      val x6957 = CounterChain.copy(x6992, "x6957")
      val x6809_x6960 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6836_mc.vdata).rdAddr(x6957(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6809_x6960.load, CU.ctr(stage(0), x6645(0))), op=FixSub, results=List(CU.temp(stage(1), tr583)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr583), CU.ctr(stage(1), x6792(0))), op=FixLeq, results=List(CU.vecOut(stage(2), bus_584_vector)))
    }
    val x6992_3 = StreamPipeline(name = "x6992_3", parent=x6992, deps=List(x6992_2)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6792 = CounterChain.copy(x7005, "x6792")
      val x7002_unitcc = CounterChain.copy("x7002_0", "x7002_unitcc")
      val x6647_x6966 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6673_mc.vdata).rdAddr(x6792(0))
      val x6646_x6969 = SRAM(size = 768, writeCtr = x7002_unitcc(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6646_scalar).rdAddr(x6792(0)).wtAddr(x6792(0))
      val bus_584_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_584_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_584_fifo.load, x6647_x6966.load, x6646_x6969.load), op=Mux, results=List(CU.vecOut(stage(1), bus_585_vector)))
    }
    val x6992_4 = StreamPipeline(name = "x6992_4", parent=x6992, deps=List(x6992_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6908_x6963_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6908_x6963_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6908_x6963_data_fifo.load, Const("-1i")), op=FixEql, results=List(CU.vecOut(stage(1), bus_587_vector)))
    }
    val x6992_5 = StreamPipeline(name = "x6992_5", parent=x6992, deps=List(x6992_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6950_cc = CounterChain.copy(x6950_0, "x6950_cc")
      val x6948_x6973 = SemiFIFO(size = 768, banking = Duplicated(), buffering = SingleBuffer()).wtPort(x6950_mc.vdata)
      val x6948_x6973_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6948_x6973_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x6948_x6973_addr_fifo.load), op=Bypass, results=List(x6948_x6973.readAddr))
      Stage(stage(2), operands=List(x6948_x6973.load), op=Bypass, results=List(CU.vecOut(stage(2), x6948_x6973_data_vector)))
    }
    val x6992_6 = StreamPipeline(name = "x6992_6", parent=x6992, deps=List(x6992_4, x6992_3, x6992_5)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_587_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_587_vector)
      val bus_585_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_585_vector)
      val x6948_x6973_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6948_x6973_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_587_fifo.load, bus_585_fifo.load, x6948_x6973_data_fifo.load), op=Mux, results=List(CU.vecOut(stage(1), bus_588_vector)))
    }
    val x6992_7 = StreamPipeline(name = "x6992_7", parent=x6992, deps=List(x6992_6)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6957 = CounterChain.copy(x6992, "x6957")
      val x6810_x6976 = SemiFIFO(size = 768, banking = Strided(1), buffering = SingleBuffer()).wtPort(x6884_mc.vdata).rdAddr(x6957(0))
      val bus_588_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_588_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_588_fifo.load, x6810_x6976.load), op=FltDiv, results=List(CU.reduce(stage(1))))
      val (rs1, rr592) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr592), op=Bypass, results=List(CU.scalarOut(stage(2), x6955_scalar)))
    }
    val x7002_0 = UnitPipeline(name = "x7002_0", parent=x7005, deps=List(x6992)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr598 = CU.temp
      val tr597 = CU.temp
      val x7002_unitcc = CounterChain(name = "x7002_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(Const("1i"), CU.scalarIn(stage(0), x6327_argin)), op=FltSub, results=List(CU.temp(stage(1), tr597)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x6955_scalar), CU.scalarIn(stage(1), x6327_argin)), op=FltMul, results=List(CU.temp(stage(2), tr598)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr598), CU.temp(stage(2), tr597)), op=FltAdd, results=List(CU.scalarOut(stage(3), x6646_scalar)))
    }
    val x7025 = StreamController(name = "x7025", parent=x7027, deps=List(x7005)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7025_unitcc = CounterChain(name = "x7025_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7027_leafX = UnitPipeline(name = "x7027_leafX", parent=x7027, deps=List(x7025)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6645 = CounterChain.copy(x7027, "x6645")
      val x7027_unitcc = CounterChain(name = "x7027_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x7010_0 = UnitPipeline(name = "x7010_0", parent=x7025, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7010_unitcc = CounterChain(name = "x7010_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6645 = CounterChain.copy(x7027, "x6645")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6645(0))), op=Bypass, results=List(CU.scalarOut(stage(1), x7023_mc.ofs)))
      Stage(stage(2), operands=List(Const("768i")), op=Bypass, results=List(CU.scalarOut(stage(2), x7023_mc.len)))
    }
    val x7021_0 = Pipeline(name = "x7021_0", parent=x7025, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6792 = CounterChain.copy(x7005, "x6792")
      val x7002_unitcc = CounterChain.copy(x7002_0, "x7002_unitcc")
      val ctr24 = (Const("0i").out, Const("768i").out, Const("16i").out) // Counter
      val x7012 = CounterChain(name = "x7012", ctr24)
      val x6646_x7015 = SRAM(size = 768, writeCtr = x7002_unitcc(0), banking = Strided(1), buffering = SingleBuffer()).wtPort(x6646_scalar).rdAddr(x7012(0)).wtAddr(x6792(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x6646_x7015.load), op=Bypass, results=List(CU.vecOut(stage(1), x7023_mc.vdata)))
    }
    
  }
}
