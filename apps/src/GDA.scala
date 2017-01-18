import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object GDADesign extends PIRApp {
  override val arch = SN_4x4
  def main(args: String*)(top:Top) = {
    val x6842_scalar = Scalar("x6842")
    val x6926_vector = Vector("x6926")
    val x6885_scalar = Scalar("x6885")
    val x6936_vector = Vector("x6936")
    val x6883_scalar = Scalar("x6883")
    val x6776_argin = ArgIn("x6776")
    val x6935_scalar = Scalar("x6935")
    val x6988_scalar = Scalar("x6988")
    val x6982_vector = Vector("x6982")
    val x7047_vector = Vector("x7047")
    val x6841_scalar = Scalar("x6841")
    val x6844_scalar = Scalar("x6844")
    val x6882_vector = Vector("x6882")
    val x6790_oc = OffChip("x6790")
    val x6940_scalar = Scalar("x6940")
    val x6789_oc = OffChip("x6789")
    val x6987_scalar = Scalar("x6987")
    val x7039_vector = Vector("x7039")
    val x6840_vector = Vector("x6840")
    val x6884_scalar = Scalar("x6884")
    val x6937_scalar = Scalar("x6937")
    val x6986_scalar = Scalar("x6986")
    val x6775_argin = ArgIn("x6775")
    val x6792_oc = OffChip("x6792")
    val x6886_scalar = Scalar("x6886")
    val x6843_scalar = Scalar("x6843")
    val x6985_scalar = Scalar("x6985")
    val x6787_oc = OffChip("x6787")
    val x6938_scalar = Scalar("x6938")
    val x6788_oc = OffChip("x6788")
    val x7136_vector = Vector("x7136")
    val x7142_scalar = Scalar("x7142")
    val x7046_vector = Vector("x7046")
    val x6939_scalar = Scalar("x6939")
    val x6903_mc_mc = MemoryController(TileLoad, x6790_oc)
    val x7009_mc_mc = MemoryController(TileLoad, x6787_oc)
    val x6861_mc_mc = MemoryController(TileLoad, x6789_oc)
    val x7159_mc_mc = MemoryController(TileStore, x6792_oc)
    val x6959_mc_mc = MemoryController(TileLoad, x6788_oc)
    val x7163 = Sequential(name="x7163", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7163_unitCC = CounterChain(name = "x7163_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x6881 = MetaPipeline(name="x6881", parent=x7163, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6881_unitCC = CounterChain(name = "x6881_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x6857 = UnitPipeline(name ="x6857", parent=x6881, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr277 = CU.temp
      val tr276 = CU.temp
      val tr275 = CU.temp
      val tr274 = CU.temp
      val x6857_unitCC = CounterChain(name = "x6857_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x6841_scalar)))
      Stage(stage(2), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(2), x6843_scalar)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), x6776_argin)), op=Bypass, results=List(CU.scalarOut(stage(3), x6842_scalar)))
      Stage(stage(4), operands=List(CU.scalarIn(stage(3), x6776_argin), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr274)))
      //Stage(stage(4), operands=List(CU.scalarIn(stage(3), x6776_argin), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr274)))
      Stage(stage(5), operands=List(CU.scalarIn(stage(4), x6776_argin), CU.temp(stage(4), tr274)), op=FixSub, results=List(CU.temp(stage(5), tr275)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr274), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr276)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr276), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr277)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr275), CU.temp(stage(7), tr277)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6844_scalar)))
    }
    val x6861 = TileTransfer(name="x6861", parent=x6881, memctrl=x6861_mc_mc, mctpe=TileLoad, deps=List(x6857), vec=x6840_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6861_ctr = (Const("0l").out, CU.scalarIn(stage0, x6844_scalar).out, Const("1l").out) // Counter
      val x6861_cc = CounterChain(name = "x6861_cc", x6861_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6843_scalar), CU.ctr(stage(0), x6861_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x6861_mc_mc.ofs)))
    }
    val x6879 = Pipeline(name="x6879", parent=x6881, deps=List(x6861)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr288 = CU.temp
      val tr287 = CU.temp
      val tr286 = CU.temp
      val x6862 = (Const("0i").out, CU.scalarIn(stage0, x6844_scalar).out, Const("1i").out) // Counter
      val x6863 = CounterChain(name = "x6863", x6862)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6841_scalar), CU.ctr(stage(0), x6863(0))), op=FixLeq, results=List(CU.temp(stage(1), tr286)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6863(0)), CU.scalarIn(stage(1), x6842_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr287)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr286), CU.temp(stage(2), tr287)), op=BitAnd, results=List(CU.temp(stage(3), tr288)))
    }
    val x6923 = MetaPipeline(name="x6923", parent=x7163, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6923_unitCC = CounterChain(name = "x6923_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x6899 = UnitPipeline(name ="x6899", parent=x6923, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr300 = CU.temp
      val tr299 = CU.temp
      val tr298 = CU.temp
      val tr297 = CU.temp
      val x6899_unitCC = CounterChain(name = "x6899_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(1), x6883_scalar)))
      Stage(stage(2), operands=List(Const("0i")), op=Bypass, results=List(CU.scalarOut(stage(2), x6885_scalar)))
      Stage(stage(3), operands=List(CU.scalarIn(stage(2), x6776_argin)), op=Bypass, results=List(CU.scalarOut(stage(3), x6884_scalar)))
      Stage(stage(4), operands=List(CU.scalarIn(stage(3), x6776_argin), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr297)))
      //Stage(stage(4), operands=List(CU.scalarIn(stage(3), x6776_argin), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr297)))
      Stage(stage(5), operands=List(CU.scalarIn(stage(4), x6776_argin), CU.temp(stage(4), tr297)), op=FixSub, results=List(CU.temp(stage(5), tr298)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr297), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr299)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr299), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr300)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr298), CU.temp(stage(7), tr300)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6886_scalar)))
    }
    val x6903 = TileTransfer(name="x6903", parent=x6923, memctrl=x6903_mc_mc, mctpe=TileLoad, deps=List(x6899), vec=x6882_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6903_ctr = (Const("0l").out, CU.scalarIn(stage0, x6886_scalar).out, Const("1l").out) // Counter
      val x6903_cc = CounterChain(name = "x6903_cc", x6903_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6885_scalar), CU.ctr(stage(0), x6903_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x6903_mc_mc.ofs)))
    }
    val x6921 = Pipeline(name="x6921", parent=x6923, deps=List(x6903)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr311 = CU.temp
      val tr310 = CU.temp
      val tr309 = CU.temp
      val x6904 = (Const("0i").out, CU.scalarIn(stage0, x6886_scalar).out, Const("1i").out) // Counter
      val x6905 = CounterChain(name = "x6905", x6904)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6883_scalar), CU.ctr(stage(0), x6905(0))), op=FixLeq, results=List(CU.temp(stage(1), tr309)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6905(0)), CU.scalarIn(stage(1), x6884_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr310)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr309), CU.temp(stage(2), tr310)), op=BitAnd, results=List(CU.temp(stage(3), tr311)))
    }
    val x7135 = MetaPipeline(name="x7135", parent=x7163, deps=List(x6881, x6923)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6928 = (Const("0i").out, CU.scalarIn(stage0, x6775_argin).out, Const("96i").out) // Counter
      val x6929 = CounterChain(name = "x6929", x6928)
    }
    val x6980 = MetaPipeline(name="x6980", parent=x7135, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6980_unitCC = CounterChain(name = "x6980_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x6955 = UnitPipeline(name ="x6955", parent=x6980, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr326 = CU.temp
      val tr325 = CU.temp
      val tr323 = CU.temp
      val tr322 = CU.temp
      val tr320 = CU.temp
      val tr316 = CU.temp
      val x6929 = CounterChain.copy(x7135, "x6929")
      val x6955_unitCC = CounterChain(name = "x6955_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6929(0)), Const("64i")), op=FixMod, results=List(CU.scalarOut(stage(1), x6937_scalar), CU.temp(stage(1), tr316)))
      //Stage(stage(1), operands=List(CU.ctr(stage(0), x6929(0)), Const("96i")), op=FixMod, results=List(CU.scalarOut(stage(1), x6937_scalar), CU.temp(stage(1), tr316)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6929(0)), CU.temp(stage(1), tr316)), op=FixSub, results=List(CU.scalarOut(stage(2), x6939_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr316), Const("96i")), op=FixAdd, results=List(CU.scalarOut(stage(3), x6938_scalar), CU.temp(stage(3), tr320)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr320), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr322)))
      //Stage(stage(4), operands=List(CU.temp(stage(3), tr320), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr322)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr320), CU.temp(stage(4), tr322)), op=FixSub, results=List(CU.temp(stage(5), tr323)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr322), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr325)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr325), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr326)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr323), CU.temp(stage(7), tr326)), op=FixAdd, results=List(CU.scalarOut(stage(8), x6940_scalar)))
    }
    val x6959 = TileTransfer(name="x6959", parent=x6980, memctrl=x6959_mc_mc, mctpe=TileLoad, deps=List(x6955), vec=x6936_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6959_ctr = (Const("0l").out, CU.scalarIn(stage0, x6940_scalar).out, Const("1l").out) // Counter
      val x6959_cc = CounterChain(name = "x6959_cc", x6959_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6939_scalar), CU.ctr(stage(0), x6959_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x6959_mc_mc.ofs)))
    }
    val x6978 = Pipeline(name="x6978", parent=x6980, deps=List(x6959)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr339 = CU.temp
      val tr338 = CU.temp
      val tr337 = CU.temp
      val x6960 = (Const("0i").out, CU.scalarIn(stage0, x6940_scalar).out, Const("1i").out) // Counter
      val x6961 = CounterChain(name = "x6961", x6960)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6937_scalar), CU.ctr(stage(0), x6961(0))), op=FixLeq, results=List(CU.temp(stage(1), tr337)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6961(0)), CU.scalarIn(stage(1), x6938_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr338)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr337), CU.temp(stage(2), tr338)), op=BitAnd, results=List(CU.temp(stage(3), tr339)))
    }
    val x7030 = MetaPipeline(name="x7030", parent=x7135, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6983 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x6984 = CounterChain(name = "x6984", x6983)
    }
    val x7005 = UnitPipeline(name ="x7005", parent=x7030, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr356 = CU.temp
      val tr355 = CU.temp
      val tr353 = CU.temp
      val tr352 = CU.temp
      val tr350 = CU.temp
      val tr346 = CU.temp
      val tr344 = CU.temp
      val tr343 = CU.temp
      val x6929 = CounterChain.copy(x7135, "x6929")
      val x6984 = CounterChain.copy(x7030, "x6984")
      val x7005_unitCC = CounterChain(name = "x7005_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6929(0)), CU.ctr(stage(0), x6984(0))), op=FixAdd, results=List(CU.temp(stage(1), tr343)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr343), CU.scalarIn(stage(1), x6776_argin)), op=FixMul, results=List(CU.temp(stage(2), tr344)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr344), Const("64i")), op=FixMod, results=List(CU.scalarOut(stage(3), x6985_scalar), CU.temp(stage(3), tr346)))
      //Stage(stage(3), operands=List(CU.temp(stage(2), tr344), Const("96i")), op=FixMod, results=List(CU.scalarOut(stage(3), x6985_scalar), CU.temp(stage(3), tr346)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr344), CU.temp(stage(3), tr346)), op=FixSub, results=List(CU.scalarOut(stage(4), x6987_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr346), CU.scalarIn(stage(4), x6776_argin)), op=FixAdd, results=List(CU.scalarOut(stage(5), x6986_scalar), CU.temp(stage(5), tr350)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr350), Const("64i")), op=FixMod, results=List(CU.temp(stage(6), tr352)))
      //Stage(stage(6), operands=List(CU.temp(stage(5), tr350), Const("96i")), op=FixMod, results=List(CU.temp(stage(6), tr352)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr350), CU.temp(stage(6), tr352)), op=FixSub, results=List(CU.temp(stage(7), tr353)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr352), Const("0i")), op=FixNeq, results=List(CU.temp(stage(8), tr355)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr355), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(9), tr356)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr353), CU.temp(stage(9), tr356)), op=FixAdd, results=List(CU.scalarOut(stage(10), x6988_scalar)))
    }
    val x7009 = TileTransfer(name="x7009", parent=x7030, memctrl=x7009_mc_mc, mctpe=TileLoad, deps=List(x7005), vec=x6982_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7009_ctr = (Const("0l").out, CU.scalarIn(stage0, x6988_scalar).out, Const("1l").out) // Counter
      val x7009_cc = CounterChain(name = "x7009_cc", x7009_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6987_scalar), CU.ctr(stage(0), x7009_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x7009_mc_mc.ofs)))
    }
    val x7028 = Pipeline(name="x7028", parent=x7030, deps=List(x7009)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr370 = CU.temp
      val tr369 = CU.temp
      val tr368 = CU.temp
      val x7010 = (Const("0i").out, CU.scalarIn(stage0, x6988_scalar).out, Const("1i").out) // Counter
      val x7011 = CounterChain(name = "x7011", x7010)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6985_scalar), CU.ctr(stage(0), x7011(0))), op=FixLeq, results=List(CU.temp(stage(1), tr368)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x7011(0)), CU.scalarIn(stage(1), x6986_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr369)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr368), CU.temp(stage(2), tr369)), op=BitAnd, results=List(CU.temp(stage(3), tr370)))
    }
    val x7036 = UnitPipeline(name ="x7036", parent=x7135, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr374 = CU.temp
      val x6929 = CounterChain.copy(x7135, "x6929")
      val x7036_unitCC = CounterChain(name = "x7036_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x6775_argin), CU.ctr(stage(0), x6929(0))), op=FixSub, results=List(CU.temp(stage(1), tr374)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr374), Const("96i")), op=FltMin, results=List(CU.scalarOut(stage(2), x6935_scalar)))
    }
    val x7116 = MetaPipeline(name="x7116", parent=x7135, deps=List(x6980, x7030, x7036)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7041 = (Const("0i").out, CU.scalarIn(stage0, x6935_scalar).out, Const("1i").out) // Counter
      val x7042 = CounterChain(name = "x7042", x7041)
    }
    val x7078 = Pipeline(name="x7078", parent=x7116, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr385 = CU.temp
      val tr383 = CU.temp
      val tr403 = CU.temp
      val tr402 = CU.temp
      val tr392 = CU.temp
      val x6984 = CounterChain.copy(x7030, "x6984")
      val x7011 = CounterChain.copy(x7028, "x7011")
      val x6863 = CounterChain.copy(x6879, "x6863")
      val x6961 = CounterChain.copy(x6978, "x6961")
      val x7051 = (Const("0i").out, CU.scalarIn(stage0, x6776_argin).out, Const("1i").out) // Counter
      val x7052 = CounterChain(name = "x7052", x7051)
      val x6980_unitCC = CounterChain.copy(x6980, "x6980_unitCC")
      val x6905 = CounterChain.copy(x6921, "x6905")
      val x7042 = CounterChain.copy(x7116, "x7042")
      val x6836_x7064 = SRAM(size = 96, writeCtr = x6863(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x6840_vector).rdAddr(x7052(0)).wtAddr(x6863(0))
      val x6837_x7061 = SRAM(size = 96, writeCtr = x6905(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x6882_vector).rdAddr(x7052(0)).wtAddr(x6905(0))
      val x6933_x7058 = SRAM(size = 96, writeCtr = x6961(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x7042(0), swapWrite = x6980_unitCC(0))).wtPort(x6936_vector).rdAddr(x7042(0))
      val x6934_x7055 = SRAM(size = 9216, writeCtr = x7011(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x7042(0), swapWrite = x6984(0))).wtPort(x6982_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x6933_x7058))
      Stage(stage(1), operands=List(x6961(0), CU.scalarIn(stage(0), x6937_scalar)), op=FixSub, results=List(x6933_x7058.writeAddr))
      stage = stage0 +: WAStages(3, List(x6934_x7055))
      Stage(stage(1), operands=List(x7011(0), CU.scalarIn(stage(0), x6985_scalar)), op=FixSub, results=List(CU.temp(stage(1), tr383)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x6984(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(2), tr385)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr385), CU.temp(stage(2), tr383)), op=FixAdd, results=List(x6934_x7055.writeAddr))
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7042(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr392)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr392), CU.ctr(stage(1), x7052(0))), op=FixAdd, results=List(x6934_x7055.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x6933_x7058), Const("1i")), op=FixEql, results=List(CU.temp(stage(3), tr402)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr402), CU.load(stage(3), x6837_x7061), CU.load(stage(3), x6836_x7064)), op=Mux, results=List(CU.temp(stage(4), tr403)))
      Stage(stage(5), operands=List(CU.load(stage(4), x6934_x7055), CU.temp(stage(4), tr403)), op=FltSub, results=List(CU.vecOut(stage(5), x7046_vector)))
    }
    val x7097 = Pipeline(name="x7097", parent=x7116, deps=List(x7078)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7052 = CounterChain.copy(x7078, "x7052")
      val x7079 = (Const("0i").out, CU.scalarIn(stage0, x6776_argin).out, Const("1i").out) // Counter
      val x7080 = (Const("0i").out, CU.scalarIn(stage0, x6776_argin).out, Const("1i").out) // Counter
      val x7081 = CounterChain(name = "x7081", x7079, x7080)
      val x7046_x7084 = SRAM(size = 96, writeCtr = x7052(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x7081(0), swapWrite = x7052(0))).wtPort(x7046_vector).rdAddr(x7081(0)).wtAddr(x7052(0))
      val x7046_x7087 = SRAM(size = 96, writeCtr = x7052(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x7081(0), swapWrite = x7052(0))).wtPort(x7046_vector).rdAddr(x7081(1)).wtAddr(x7052(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x7046_x7084.load, x7046_x7087.load), op=FltMul, results=List(CU.vecOut(stage(1), x7047_vector)))
    }
    val x7114 = Pipeline(name="x7114", parent=x7116, deps=List(x7097)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr420 = CU.temp
      val tr438 = CU.temp
      val tr436 = CU.temp
      val tr427 = CU.temp
      val tr423 = CU.temp
      val x7043 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7044 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x7045 = CounterChain(name = "x7045", x7043, x7044)
      val x7081 = CounterChain.copy(x7097, "x7081")
      val x7047_x7100 = SRAM(size = 9216, writeCtr = x7081(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x7045(0), swapWrite = x7081(0))).wtPort(x7047_vector)
      val x7039_x7103 = SRAM(size = 9216, writeCtr = x7045(0), banking = Duplicated(), buffering = SingleBuffer())
      val wr440 = CU.wtAddr(x7039_x7103)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7047_x7100))
      Stage(stage(1), operands=List(x7081(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr420)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr420), CU.ctr(stage(1), x7081(1))), op=FixAdd, results=List(x7047_x7100.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7045(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr423)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr423), CU.ctr(stage(1), x7045(1))), op=FixAdd, results=List(x7047_x7100.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x7045(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr427)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr427), CU.ctr(stage(3), x7045(1))), op=FixAdd, results=List(x7039_x7103.readAddr))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr436), x7039_x7103.load), op=FltAdd, results=List(CU.vecOut(stage(5), x7039_vector), CU.store(stage(5), x7039_x7103)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x7045(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(6), tr438)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr438), CU.ctr(stage(6), x7045(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr440)))
    }
    val x7133 = Pipeline(name="x7133", parent=x7135, deps=List(x7116)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr443 = CU.temp
      val tr459 = CU.temp
      val tr457 = CU.temp
      val tr450 = CU.temp
      val tr446 = CU.temp
      val x7045 = CounterChain.copy(x7114, "x7045")
      val x6930 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x6931 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x6932 = CounterChain(name = "x6932", x6930, x6931)
      val x7042 = CounterChain.copy(x7116, "x7042")
      val x7039_x7119 = SRAM(size = 9216, writeCtr = x7045(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x6932(0), swapWrite = x7042(0))).wtPort(x7039_vector)
      val x6926_x7122 = SRAM(size = 9216, writeCtr = x6932(0), banking = Duplicated(), buffering = SingleBuffer())
      val wr461 = CU.wtAddr(x6926_x7122)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x7039_x7119))
      Stage(stage(1), operands=List(x7045(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr443)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr443), CU.ctr(stage(1), x7045(1))), op=FixAdd, results=List(x7039_x7119.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6932(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr446)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr446), CU.ctr(stage(1), x6932(1))), op=FixAdd, results=List(x7039_x7119.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6932(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(3), tr450)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr450), CU.ctr(stage(3), x6932(1))), op=FixAdd, results=List(x6926_x7122.readAddr))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr457), x6926_x7122.load), op=FltAdd, results=List(CU.vecOut(stage(5), x6926_vector), CU.store(stage(5), x6926_x7122)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6932(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(6), tr459)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr459), CU.ctr(stage(6), x6932(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr461)))
    }
    val x7161 = MetaPipeline(name="x7161", parent=x7163, deps=List(x7135)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7140 = (Const("0i").out, CU.scalarIn(stage0, x6776_argin).out, Const("1i").out) // Counter
      val x7141 = CounterChain(name = "x7141", x7140)
    }
    val x7146 = UnitPipeline(name ="x7146", parent=x7161, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7141 = CounterChain.copy(x7161, "x7141")
      val x7146_unitCC = CounterChain(name = "x7146_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7141(0)), CU.scalarIn(stage(0), x6776_argin)), op=FixMul, results=List(CU.scalarOut(stage(1), x7142_scalar)))
    }
    val x7157 = Pipeline(name="x7157", parent=x7161, deps=List(x7146)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr469 = CU.temp
      val tr474 = CU.temp
      val x7147 = (Const("0i").out, CU.scalarIn(stage0, x6776_argin).out, Const("1i").out) // Counter
      val x7148 = CounterChain(name = "x7148", x7147)
      val x7141 = CounterChain.copy(x7161, "x7141")
      val x6932 = CounterChain.copy(x7133, "x6932")
      val x6926_x7151 = SRAM(size = 9216, writeCtr = x6932(0), banking = Duplicated(), buffering = SingleBuffer()).wtPort(x6926_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6926_x7151))
      Stage(stage(1), operands=List(x6932(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr469)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr469), CU.ctr(stage(1), x6932(1))), op=FixAdd, results=List(x6926_x7151.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x7141(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr474)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr474), CU.ctr(stage(1), x7148(0))), op=FixAdd, results=List(x6926_x7151.readAddr))
      Stage(stage(3), operands=List(x6926_x7151.load), op=Bypass, results=List(CU.vecOut(stage(3), x7136_vector)))
    }
    val x7159 = TileTransfer(name="x7159", parent=x7161, memctrl=x7159_mc_mc, mctpe=TileStore, deps=List(x7157), vec=x7136_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x7159_ctr = (Const("0l").out, CU.scalarIn(stage0, x6776_argin).out, Const("1l").out) // Counter
      val x7159_cc = CounterChain(name = "x7159_cc", x7159_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x7142_scalar), CU.ctr(stage(0), x7159_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x7159_mc_mc.ofs)))
    }
    
  }
}
