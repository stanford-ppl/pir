import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object BlackScholesCompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x2464_vector = Vector()
    val x2436_vector = Vector()
    val x2508_vector = Vector()
    val x1833_oc = OffChip()
    val x1827_oc = OffChip()
    val x2422_vector = Vector()
    val x1829_oc = OffChip()
    val x1828_oc = OffChip()
    val x1832_oc = OffChip()
    val x1831_oc = OffChip()
    val x2609_vector = Vector()
    val x2450_vector = Vector()
    val x2478_vector = Vector()
    val x1830_oc = OffChip()
    val x2492_vector = Vector()
    val x2493_mc_mc = MemoryController(TileLoad, x1832_oc)
    val x2465_mc_mc = MemoryController(TileLoad, x1830_oc)
    val x2479_mc_mc = MemoryController(TileLoad, x1831_oc)
    val x2622_mc_mc = MemoryController(TileStore, x1833_oc)
    val x2437_mc_mc = MemoryController(TileLoad, x1828_oc)
    val x2451_mc_mc = MemoryController(TileLoad, x1829_oc)
    val x2423_mc_mc = MemoryController(TileLoad, x1827_oc)
    val x2628 = ComputeUnit(name="x2628", parent=top, tpe = Sequential, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2628_unitCC = CounterChain(name = "x2628_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2626 = ComputeUnit(name="x2626", parent=x2628, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2076 = (Const("0i").out, Const("7680000i").out, Const("7104i").out) // Counter
      val x2077 = CounterChain(name = "x2077", x2076)
    }
    val x2435 = ComputeUnit(name="x2435", parent=x2626, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2435_unitCC = CounterChain(name = "x2435_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2423 = TileTransfer(name="x2423", parent=x2435, memctrl=x2423_mc_mc, mctpe=TileLoad, deps=List(), vec=x2422_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2423_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2423_cc = CounterChain(name = "x2423_cc", x2423_ctr)
      val x2424 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2425 = CounterChain(name = "x2425", x2424)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2423_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2423_mc_mc.saddr)))
    }
    val x2449 = ComputeUnit(name="x2449", parent=x2626, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2449_unitCC = CounterChain(name = "x2449_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2437 = TileTransfer(name="x2437", parent=x2449, memctrl=x2437_mc_mc, mctpe=TileLoad, deps=List(), vec=x2436_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2437_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2437_cc = CounterChain(name = "x2437_cc", x2437_ctr)
      val x2438 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2439 = CounterChain(name = "x2439", x2438)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2437_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2437_mc_mc.saddr)))
    }
    val x2463 = ComputeUnit(name="x2463", parent=x2626, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2463_unitCC = CounterChain(name = "x2463_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2451 = TileTransfer(name="x2451", parent=x2463, memctrl=x2451_mc_mc, mctpe=TileLoad, deps=List(), vec=x2450_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2451_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2451_cc = CounterChain(name = "x2451_cc", x2451_ctr)
      val x2452 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2453 = CounterChain(name = "x2453", x2452)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2451_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2451_mc_mc.saddr)))
    }
    val x2477 = ComputeUnit(name="x2477", parent=x2626, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2477_unitCC = CounterChain(name = "x2477_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2465 = TileTransfer(name="x2465", parent=x2477, memctrl=x2465_mc_mc, mctpe=TileLoad, deps=List(), vec=x2464_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2465_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2465_cc = CounterChain(name = "x2465_cc", x2465_ctr)
      val x2466 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2467 = CounterChain(name = "x2467", x2466)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2465_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2465_mc_mc.saddr)))
    }
    val x2491 = ComputeUnit(name="x2491", parent=x2626, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2491_unitCC = CounterChain(name = "x2491_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2479 = TileTransfer(name="x2479", parent=x2491, memctrl=x2479_mc_mc, mctpe=TileLoad, deps=List(), vec=x2478_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2479_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2479_cc = CounterChain(name = "x2479_cc", x2479_ctr)
      val x2480 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2481 = CounterChain(name = "x2481", x2480)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2479_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2479_mc_mc.saddr)))
    }
    val x2505 = ComputeUnit(name="x2505", parent=x2626, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2505_unitCC = CounterChain(name = "x2505_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2493 = TileTransfer(name="x2493", parent=x2505, memctrl=x2493_mc_mc, mctpe=TileLoad, deps=List(), vec=x2492_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2493_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2493_cc = CounterChain(name = "x2493_cc", x2493_ctr)
      val x2494 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2495 = CounterChain(name = "x2495", x2494)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2493_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2493_mc_mc.saddr)))
    }
    val x2608 = ComputeUnit(name="x2608", parent=x2626, tpe = Pipe, deps=List(x2449, x2491, x2463, x2435, x2477, x2505)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr172 = CU.temp
      val tr179 = CU.temp
      val tr207 = CU.temp
      val tr204 = CU.temp
      val tr243 = CU.temp
      val tr226 = CU.temp
      val tr160 = CU.temp
      val tr177 = CU.temp
      val tr240 = CU.temp
      val tr245 = CU.temp
      val tr227 = CU.temp
      val tr195 = CU.temp
      val tr192 = CU.temp
      val tr229 = CU.temp
      val tr187 = CU.temp
      val tr194 = CU.temp
      val tr225 = CU.temp
      val tr166 = CU.temp
      val tr158 = CU.temp
      val tr215 = CU.temp
      val tr219 = CU.temp
      val tr241 = CU.temp
      val tr208 = CU.temp
      val tr205 = CU.temp
      val tr211 = CU.temp
      val tr167 = CU.temp
      val tr209 = CU.temp
      val tr218 = CU.temp
      val tr161 = CU.temp
      val tr164 = CU.temp
      val tr197 = CU.temp
      val tr189 = CU.temp
      val tr230 = CU.temp
      val tr175 = CU.temp
      val tr200 = CU.temp
      val tr202 = CU.temp
      val tr162 = CU.temp
      val tr178 = CU.temp
      val tr159 = CU.temp
      val tr155 = CU.temp
      val tr191 = CU.temp
      val tr216 = CU.temp
      val tr183 = CU.temp
      val tr238 = CU.temp
      val tr180 = CU.temp
      val tr153 = CU.temp
      val tr222 = CU.temp
      val tr152 = CU.temp
      val tr231 = CU.temp
      val tr185 = CU.temp
      val tr237 = CU.temp
      val tr214 = CU.temp
      val tr174 = CU.temp
      val tr201 = CU.temp
      val tr188 = CU.temp
      val tr217 = CU.temp
      val tr232 = CU.temp
      val tr190 = CU.temp
      val tr221 = CU.temp
      val tr157 = CU.temp
      val tr163 = CU.temp
      val tr213 = CU.temp
      val tr220 = CU.temp
      val tr198 = CU.temp
      val tr168 = CU.temp
      val tr203 = CU.temp
      val tr156 = CU.temp
      val tr224 = CU.temp
      val tr212 = CU.temp
      val tr242 = CU.temp
      val tr210 = CU.temp
      val tr233 = CU.temp
      val tr234 = CU.temp
      val tr206 = CU.temp
      val tr165 = CU.temp
      val tr181 = CU.temp
      val tr173 = CU.temp
      val tr236 = CU.temp
      val tr199 = CU.temp
      val x2467 = CounterChain.copy(x2465, "x2467")
      val x2453 = CounterChain.copy(x2451, "x2453")
      val x2439 = CounterChain.copy(x2437, "x2439")
      val x2481 = CounterChain.copy(x2479, "x2481")
      val x2425 = CounterChain.copy(x2423, "x2425")
      val x2509 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2510 = CounterChain(name = "x2510", x2509)
      val x2495 = CounterChain.copy(x2493, "x2495")
      val x2418_x2514 = SRAM(size = 7104, vec = x2450_vector, readAddr = x2510(0), writeAddr = x2453(0), swapCtr = x2453(0), writeCtr = x2453(0), banking = Strided(1), doubleBuffer = true)
      val x2416_x2518 = SRAM(size = 7104, vec = x2422_vector, readAddr = x2510(0), writeAddr = x2425(0), swapCtr = x2425(0), writeCtr = x2425(0), banking = Strided(1), doubleBuffer = true)
      val x2421_x2517 = SRAM(size = 7104, vec = x2492_vector, readAddr = x2510(0), writeAddr = x2495(0), swapCtr = x2495(0), writeCtr = x2495(0), banking = Strided(1), doubleBuffer = true)
      val x2417_x2513 = SRAM(size = 7104, vec = x2436_vector, readAddr = x2510(0), writeAddr = x2439(0), swapCtr = x2439(0), writeCtr = x2439(0), banking = Strided(1), doubleBuffer = true)
      val x2419_x2515 = SRAM(size = 7104, vec = x2464_vector, readAddr = x2510(0), writeAddr = x2467(0), swapCtr = x2467(0), writeCtr = x2467(0), banking = Strided(1), doubleBuffer = true)
      val x2420_x2516 = SRAM(size = 7104, vec = x2478_vector, readAddr = x2510(0), writeAddr = x2481(0), swapCtr = x2481(0), writeCtr = x2481(0), banking = Strided(1), doubleBuffer = true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(80)
      Stage(stage(1), operands=List(x2417_x2513.load, x2418_x2514.load), op=FltDiv, results=List(CU.temp(stage(1), tr152)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr152)), op=FltExp, results=List(CU.temp(stage(2), tr153)))
      Stage(stage(3), operands=List(CU.load(stage(2), x2420_x2516), CU.load(stage(2), x2420_x2516)), op=FltMul, results=List(CU.temp(stage(3), tr155)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr155), Const("0.5f")), op=FltMul, results=List(CU.temp(stage(4), tr156)))
      Stage(stage(5), operands=List(CU.load(stage(4), x2419_x2515), CU.temp(stage(4), tr156)), op=FltAdd, results=List(CU.temp(stage(5), tr157)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr157), CU.load(stage(5), x2421_x2517)), op=FltMul, results=List(CU.temp(stage(6), tr158)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr158), CU.temp(stage(6), tr153)), op=FltAdd, results=List(CU.temp(stage(7), tr159)))
      Stage(stage(8), operands=List(CU.load(stage(7), x2421_x2517)), op=FltExp, results=List(CU.temp(stage(8), tr160)))
      Stage(stage(9), operands=List(CU.load(stage(8), x2420_x2516), CU.temp(stage(8), tr160)), op=FltMul, results=List(CU.temp(stage(9), tr161)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr161), CU.temp(stage(9), tr161)), op=FltMul, results=List(CU.temp(stage(10), tr162)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr159), CU.temp(stage(10), tr162)), op=FltDiv, results=List(CU.temp(stage(11), tr163)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr163)), op=FltAbs, results=List(CU.temp(stage(12), tr164)))
      Stage(stage(13), operands=List(CU.temp(stage(12), tr164), CU.temp(stage(12), tr164)), op=FltMul, results=List(CU.temp(stage(13), tr165)))
      Stage(stage(14), operands=List(CU.temp(stage(13), tr165), Const("-0.05f")), op=FltMul, results=List(CU.temp(stage(14), tr166)))
      Stage(stage(15), operands=List(CU.temp(stage(14), tr166)), op=FltExp, results=List(CU.temp(stage(15), tr167)))
      Stage(stage(16), operands=List(CU.temp(stage(15), tr167), Const("0.3989423f")), op=FltMul, results=List(CU.temp(stage(16), tr168)))
      Stage(stage(17), operands=List(CU.temp(stage(16), tr164), Const("0.2316419f")), op=FltMul, results=List(CU.temp(stage(17), tr172)))
      Stage(stage(18), operands=List(CU.temp(stage(17), tr172), Const("1i")), op=FltAdd, results=List(CU.temp(stage(18), tr173)))
      Stage(stage(19), operands=List(Const("1i"), CU.temp(stage(18), tr173)), op=FltDiv, results=List(CU.temp(stage(19), tr174)))
      Stage(stage(20), operands=List(CU.temp(stage(19), tr174), Const("0.31938154f")), op=FltMul, results=List(CU.temp(stage(20), tr175)))
      Stage(stage(21), operands=List(CU.temp(stage(20), tr174), CU.temp(stage(20), tr174)), op=FltMul, results=List(CU.temp(stage(21), tr177)))
      Stage(stage(22), operands=List(CU.temp(stage(21), tr177), CU.temp(stage(21), tr174)), op=FltMul, results=List(CU.temp(stage(22), tr178)))
      Stage(stage(23), operands=List(CU.temp(stage(22), tr178), CU.temp(stage(22), tr174)), op=FltMul, results=List(CU.temp(stage(23), tr179)))
      Stage(stage(24), operands=List(CU.temp(stage(23), tr179), CU.temp(stage(23), tr174)), op=FltMul, results=List(CU.temp(stage(24), tr180)))
      Stage(stage(25), operands=List(CU.temp(stage(24), tr180), Const("1.3302745f")), op=FltMul, results=List(CU.temp(stage(25), tr181)))
      Stage(stage(26), operands=List(CU.temp(stage(25), tr179), Const("-1.8212559f")), op=FltMul, results=List(CU.temp(stage(26), tr183)))
      Stage(stage(27), operands=List(CU.temp(stage(26), tr177), Const("-0.35656378f")), op=FltMul, results=List(CU.temp(stage(27), tr185)))
      Stage(stage(28), operands=List(CU.temp(stage(27), tr178), Const("1.7814779f")), op=FltMul, results=List(CU.temp(stage(28), tr187)))
      Stage(stage(29), operands=List(CU.temp(stage(28), tr185), CU.temp(stage(28), tr187)), op=FltAdd, results=List(CU.temp(stage(29), tr188)))
      Stage(stage(30), operands=List(CU.temp(stage(29), tr188), CU.temp(stage(29), tr183)), op=FltAdd, results=List(CU.temp(stage(30), tr189)))
      Stage(stage(31), operands=List(CU.temp(stage(30), tr189), CU.temp(stage(30), tr181)), op=FltAdd, results=List(CU.temp(stage(31), tr190)))
      Stage(stage(32), operands=List(CU.temp(stage(31), tr190), CU.temp(stage(31), tr175)), op=FltAdd, results=List(CU.temp(stage(32), tr191)))
      Stage(stage(33), operands=List(CU.temp(stage(32), tr191), CU.temp(stage(32), tr168)), op=FltMul, results=List(CU.temp(stage(33), tr192)))
      Stage(stage(34), operands=List(CU.temp(stage(33), tr192), Const("-1f")), op=FltMul, results=List(CU.temp(stage(34), tr194)))
      Stage(stage(35), operands=List(CU.temp(stage(34), tr194), Const("1i")), op=FltAdd, results=List(CU.temp(stage(35), tr195)))
      Stage(stage(36), operands=List(CU.temp(stage(35), tr163), Const("0i")), op=FltLt, results=List(CU.temp(stage(36), tr197)))
      Stage(stage(37), operands=List(CU.temp(stage(36), tr197), CU.temp(stage(36), tr192), CU.temp(stage(36), tr195)), op=Mux, results=List(CU.temp(stage(37), tr198)))
      Stage(stage(38), operands=List(CU.load(stage(37), x2417_x2513), CU.temp(stage(37), tr198)), op=FltMul, results=List(CU.temp(stage(38), tr199)))
      Stage(stage(39), operands=List(CU.temp(stage(38), tr163), CU.temp(stage(38), tr161)), op=FltSub, results=List(CU.temp(stage(39), tr200)))
      Stage(stage(40), operands=List(CU.temp(stage(39), tr200)), op=FltAbs, results=List(CU.temp(stage(40), tr201)))
      Stage(stage(41), operands=List(CU.temp(stage(40), tr201), CU.temp(stage(40), tr201)), op=FltMul, results=List(CU.temp(stage(41), tr202)))
      Stage(stage(42), operands=List(CU.temp(stage(41), tr202), Const("-0.05f")), op=FltMul, results=List(CU.temp(stage(42), tr203)))
      Stage(stage(43), operands=List(CU.temp(stage(42), tr203)), op=FltExp, results=List(CU.temp(stage(43), tr204)))
      Stage(stage(44), operands=List(CU.temp(stage(43), tr204), Const("0.3989423f")), op=FltMul, results=List(CU.temp(stage(44), tr205)))
      Stage(stage(45), operands=List(CU.temp(stage(44), tr201), Const("0.2316419f")), op=FltMul, results=List(CU.temp(stage(45), tr206)))
      Stage(stage(46), operands=List(CU.temp(stage(45), tr206), Const("1i")), op=FltAdd, results=List(CU.temp(stage(46), tr207)))
      Stage(stage(47), operands=List(Const("1i"), CU.temp(stage(46), tr207)), op=FltDiv, results=List(CU.temp(stage(47), tr208)))
      Stage(stage(48), operands=List(CU.temp(stage(47), tr208), Const("0.31938154f")), op=FltMul, results=List(CU.temp(stage(48), tr209)))
      Stage(stage(49), operands=List(CU.temp(stage(48), tr208), CU.temp(stage(48), tr208)), op=FltMul, results=List(CU.temp(stage(49), tr210)))
      Stage(stage(50), operands=List(CU.temp(stage(49), tr210), CU.temp(stage(49), tr208)), op=FltMul, results=List(CU.temp(stage(50), tr211)))
      Stage(stage(51), operands=List(CU.temp(stage(50), tr211), CU.temp(stage(50), tr208)), op=FltMul, results=List(CU.temp(stage(51), tr212)))
      Stage(stage(52), operands=List(CU.temp(stage(51), tr212), CU.temp(stage(51), tr208)), op=FltMul, results=List(CU.temp(stage(52), tr213)))
      Stage(stage(53), operands=List(CU.temp(stage(52), tr213), Const("1.3302745f")), op=FltMul, results=List(CU.temp(stage(53), tr214)))
      Stage(stage(54), operands=List(CU.temp(stage(53), tr212), Const("-1.8212559f")), op=FltMul, results=List(CU.temp(stage(54), tr215)))
      Stage(stage(55), operands=List(CU.temp(stage(54), tr210), Const("-0.35656378f")), op=FltMul, results=List(CU.temp(stage(55), tr216)))
      Stage(stage(56), operands=List(CU.temp(stage(55), tr211), Const("1.7814779f")), op=FltMul, results=List(CU.temp(stage(56), tr217)))
      Stage(stage(57), operands=List(CU.temp(stage(56), tr216), CU.temp(stage(56), tr217)), op=FltAdd, results=List(CU.temp(stage(57), tr218)))
      Stage(stage(58), operands=List(CU.temp(stage(57), tr218), CU.temp(stage(57), tr215)), op=FltAdd, results=List(CU.temp(stage(58), tr219)))
      Stage(stage(59), operands=List(CU.temp(stage(58), tr219), CU.temp(stage(58), tr214)), op=FltAdd, results=List(CU.temp(stage(59), tr220)))
      Stage(stage(60), operands=List(CU.temp(stage(59), tr220), CU.temp(stage(59), tr209)), op=FltAdd, results=List(CU.temp(stage(60), tr221)))
      Stage(stage(61), operands=List(CU.temp(stage(60), tr221), CU.temp(stage(60), tr205)), op=FltMul, results=List(CU.temp(stage(61), tr222)))
      Stage(stage(62), operands=List(CU.temp(stage(61), tr222), Const("-1f")), op=FltMul, results=List(CU.temp(stage(62), tr224)))
      Stage(stage(63), operands=List(CU.temp(stage(62), tr224), Const("1i")), op=FltAdd, results=List(CU.temp(stage(63), tr225)))
      Stage(stage(64), operands=List(CU.temp(stage(63), tr200), Const("0i")), op=FltLt, results=List(CU.temp(stage(64), tr226)))
      Stage(stage(65), operands=List(CU.temp(stage(64), tr226), CU.temp(stage(64), tr222), CU.temp(stage(64), tr225)), op=Mux, results=List(CU.temp(stage(65), tr227)))
      Stage(stage(66), operands=List(CU.load(stage(65), x2419_x2515), Const("-1f")), op=FltMul, results=List(CU.temp(stage(66), tr229)))
      Stage(stage(67), operands=List(CU.temp(stage(66), tr229), CU.load(stage(66), x2421_x2517)), op=FltMul, results=List(CU.temp(stage(67), tr230)))
      Stage(stage(68), operands=List(CU.temp(stage(67), tr230)), op=FltExp, results=List(CU.temp(stage(68), tr231)))
      Stage(stage(69), operands=List(CU.load(stage(68), x2418_x2514), CU.temp(stage(68), tr231)), op=FltMul, results=List(CU.temp(stage(69), tr232)))
      Stage(stage(70), operands=List(CU.temp(stage(69), tr232), CU.temp(stage(69), tr227)), op=FltMul, results=List(CU.temp(stage(70), tr233)))
      Stage(stage(71), operands=List(CU.temp(stage(70), tr199), CU.temp(stage(70), tr233)), op=FltSub, results=List(CU.temp(stage(71), tr234)))
      Stage(stage(72), operands=List(CU.temp(stage(71), tr227), Const("-1f")), op=FltMul, results=List(CU.temp(stage(72), tr236)))
      Stage(stage(73), operands=List(CU.temp(stage(72), tr236), Const("1i")), op=FltAdd, results=List(CU.temp(stage(73), tr237)))
      Stage(stage(74), operands=List(CU.temp(stage(73), tr232), CU.temp(stage(73), tr237)), op=FltMul, results=List(CU.temp(stage(74), tr238)))
      Stage(stage(75), operands=List(CU.temp(stage(74), tr198), Const("-1f")), op=FltMul, results=List(CU.temp(stage(75), tr240)))
      Stage(stage(76), operands=List(CU.temp(stage(75), tr240), Const("1i")), op=FltAdd, results=List(CU.temp(stage(76), tr241)))
      Stage(stage(77), operands=List(CU.load(stage(76), x2417_x2513), CU.temp(stage(76), tr241)), op=FltMul, results=List(CU.temp(stage(77), tr242)))
      Stage(stage(78), operands=List(CU.temp(stage(77), tr238), CU.temp(stage(77), tr242)), op=FltSub, results=List(CU.temp(stage(78), tr243)))
      Stage(stage(79), operands=List(CU.load(stage(78), x2416_x2518), Const("0i")), op=FixEql, results=List(CU.temp(stage(79), tr245)))
      Stage(stage(80), operands=List(CU.temp(stage(79), tr245), CU.temp(stage(79), tr243), CU.temp(stage(79), tr234)), op=Mux, results=List(CU.vecOut(stage(80), x2508_vector)))
    }
    val x2624 = ComputeUnit(name="x2624", parent=x2626, tpe = MetaPipeline, deps=List(x2608)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2624_unitCC = CounterChain(name = "x2624_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2621 = ComputeUnit(name="x2621", parent=x2624, tpe = Pipe, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2610 = (Const("0i").out, Const("7104i").out, Const("1i").out) // Counter
      val x2611 = CounterChain(name = "x2611", x2610)
      val x2510 = CounterChain.copy(x2608, "x2510")
      val x2508_x2614 = SRAM(size = 7104, vec = x2508_vector, readAddr = x2611(0), writeAddr = x2510(0), swapCtr = x2510(0), writeCtr = x2510(0), banking = Strided(1), doubleBuffer = true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.load(stage(0), x2508_x2614)), op=Bypass, results=List(CU.vecOut(stage(1), x2609_vector)))
    }
    val x2622 = TileTransfer(name="x2622", parent=x2624, memctrl=x2622_mc_mc, mctpe=TileStore, deps=List(x2621), vec=x2609_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x2077 = CounterChain.copy(x2626, "x2077")
      val x2622_ctr = (Const("0l").out, Const("7104i").out, Const("1l").out) // Counter
      val x2622_cc = CounterChain(name = "x2622_cc", x2622_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2077(0)), CU.ctr(stage(0), x2622_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2622_mc_mc.saddr)))
    }

  }
}
