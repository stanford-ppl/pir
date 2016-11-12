import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object KmeansCompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x1991_vector = Vector()
    val x1801_vector = Vector()
    val x1401_argin = ArgIn()
    val x1505_vector = Vector()
    val x1410_oc = OffChip()
    val x1835_scalar = Scalar()
    val x1843_scalar = Scalar()
    val x1827_vector = Vector()
    val x1399_argin = ArgIn()
    val x1408_oc = OffChip()
    val x1946_vector = Vector()
    val x2036_scalar = Scalar()
    val x1400_argin = ArgIn()
    val x1965_vector = Vector()
    val x1805_scalar = Scalar()
    val x1483_scalar = Scalar()
    val x1880_vector = Vector()
    val x1454_vector = Vector()
    val x2042_mc_mc = MemoryController(TileStore, x1410_oc)
    val x1812_mc_mc = MemoryController(TileLoad, x1408_oc)
    val x1489_mc_mc = MemoryController(TileLoad, x1408_oc)
    val x2048 = ComputeUnit(name="x2048", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2048_unitCC = CounterChain(name = "x2048_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1503 = ComputeUnit(name="x1503", parent=x2048, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1455 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x1456 = CounterChain(name = "x1456", x1455)
    }
    val x1487 = UnitComputeUnit(name ="x1487", parent=x1503, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1456 = CounterChain.copy(x1503, "x1456")
      val x1487_unitCC = CounterChain(name = "x1487_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1456(0)), CU.scalarIn(stage(0), x1401_argin)), op=FixMul, results=List(CU.scalarOut(stage(1), x1483_scalar)))
    }
    val x1489 = TileTransfer(name="x1489", parent=x1503, memctrl=x1489_mc_mc, mctpe=TileLoad, deps=List(x1487), vec=x1454_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1489_ctr = (Const("0l").out, Const("384i").out, Const("1l").out) // Counter
      val x1489_cc = CounterChain(name = "x1489_cc", x1489_ctr)
      val x1490 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x1491 = CounterChain(name = "x1491", x1490).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1483_scalar), CU.ctr(stage(0), x1489_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1489_mc_mc.saddr)))
    }
    val x1945 = ComputeUnit(name="x1945", parent=x2048, tpe = MetaPipeline, deps=List(x1503)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1506 = (Const("0i").out, CU.scalarIn(stage0, x1399_argin).out, Const("96i").out) // Counter
      val x1507 = CounterChain(name = "x1507", x1506)
    }
    val x1826 = ComputeUnit(name="x1826", parent=x1945, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1802 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1803 = CounterChain(name = "x1803", x1802)
    }
    val x1810 = UnitComputeUnit(name ="x1810", parent=x1826, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr190 = CU.temp
      val x1507 = CounterChain.copy(x1945, "x1507")
      val x1803 = CounterChain.copy(x1826, "x1803")
      val x1810_unitCC = CounterChain(name = "x1810_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1507(0)), CU.ctr(stage(0), x1803(0))), op=FixAdd, results=List(CU.temp(stage(1), tr190)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr190), CU.scalarIn(stage(1), x1401_argin)), op=FixMul, results=List(CU.scalarOut(stage(2), x1805_scalar)))
    }
    val x1812 = TileTransfer(name="x1812", parent=x1826, memctrl=x1812_mc_mc, mctpe=TileLoad, deps=List(x1810), vec=x1801_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1812_ctr = (Const("0l").out, Const("384i").out, Const("1l").out) // Counter
      val x1812_cc = CounterChain(name = "x1812_cc", x1812_ctr)
      val x1813 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x1814 = CounterChain(name = "x1814", x1813).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1805_scalar), CU.ctr(stage(0), x1812_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1812_mc_mc.saddr)))
    }
    val x1923 = ComputeUnit(name="x1923", parent=x1945, tpe = MetaPipeline, deps=List(x1826)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1828 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1829 = CounterChain(name = "x1829", x1828)
    }
    val x1878 = ComputeUnit(name="x1878", parent=x1923, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1837 = (Const("0i").out, CU.scalarIn(stage0, x1400_argin).out, Const("1i").out) // Counter
      val x1838 = CounterChain(name = "x1838", x1837)
    }
    val x1865 = ComputeUnit(name="x1865", parent=x1878, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr208 = CU.temp
      val tr203 = CU.temp
      val tr216 = CU.temp
      val tr212 = CU.temp
      val tr199 = CU.temp
      val x1491 = CounterChain.copy(x1489, "x1491")
      val x1838 = CounterChain.copy(x1878, "x1838")
      val x1814 = CounterChain.copy(x1812, "x1814")
      val x1841 = (Const("0i").out, CU.scalarIn(stage0, x1401_argin).out, Const("1i").out) // Counter
      val x1842 = CounterChain(name = "x1842", x1841)
      val x1456 = CounterChain.copy(x1503, "x1456")
      val x1803 = CounterChain.copy(x1826, "x1803")
      val x1829 = CounterChain.copy(x1923, "x1829")
      val x1453_x1855 = SRAM(size = 3072, swapCtr = x1456(0), writeCtr = x1491(0), banking = Strided(1), doubleBuffer = false).wtPort(x1454_vector)
      val x1800_x1851 = SRAM(size = 36864, swapCtr = x1803(0), writeCtr = x1814(0), banking = Strided(1), doubleBuffer = true).wtPort(x1801_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1800_x1851))
      Stage(stage(1), operands=List(x1803(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr199)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr199), CU.ctr(stage(1), x1814(0))), op=FixAdd, results=List(x1800_x1851.writeAddr))
      stage = stage0 +: WAStages(2, List(x1453_x1855))
      Stage(stage(1), operands=List(x1456(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr203)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr203), CU.ctr(stage(1), x1491(0))), op=FixAdd, results=List(x1453_x1855.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1829(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr208)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr208), CU.ctr(stage(1), x1842(0))), op=FixAdd, results=List(x1800_x1851.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x1838(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(3), tr212)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr212), CU.ctr(stage(3), x1842(0))), op=FixAdd, results=List(x1453_x1855.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x1800_x1851), x1453_x1855.load), op=FltSub, results=List(CU.temp(stage(5), tr216)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr216), CU.temp(stage(5), tr216)), op=FltMul, results=List(CU.reduce(stage(6))))
      val (rs1, rr220) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(7), operands=List(rr220), op=Bypass, results=List(CU.scalarOut(stage(7), x1843_scalar)))
    }
    val x1876 = UnitComputeUnit(name ="x1876", parent=x1878, deps=List(x1865)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr228 = CU.temp
      val ar224 = CU.accum(init = Const("-1i"))
      val ar227 = CU.accum(init = Const("0i"))
      val x1838 = CounterChain.copy(x1878, "x1838")
      val x1876_unitCC = CounterChain(name = "x1876_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1843_scalar), CU.accum(stage(1), ar224)), op=FltMin, results=List(CU.accum(stage(1), ar224)))
      Stage(stage(2), operands=List(CU.accum(stage(1), ar224), CU.scalarIn(stage(1), x1843_scalar)), op=FltEql, results=List(CU.temp(stage(2), tr228)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr228), CU.ctr(stage(2), x1838(0)), CU.accum(stage(3), ar227)), op=Mux, results=List(CU.scalarOut(stage(3), x1835_scalar), CU.accum(stage(3), ar227)))
    }
    val x1901 = ComputeUnit(name="x1901", parent=x1923, tpe = Pipe, deps=List(x1878)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr247 = CU.temp
      val tr245 = CU.temp
      val tr239 = CU.temp
      val tr246 = CU.temp
      val tr232 = CU.temp
      val x1881 = (Const("0i").out, CU.scalarIn(stage0, x1400_argin).out, Const("1i").out) // Counter
      val x1882 = (Const("0i").out, CU.scalarIn(stage0, x1401_argin).out, Const("1i").out) // Counter
      val x1883 = CounterChain(name = "x1883", x1881, x1882)
      val x1814 = CounterChain.copy(x1812, "x1814")
      val x1803 = CounterChain.copy(x1826, "x1803")
      val x1829 = CounterChain.copy(x1923, "x1829")
      val x1800_x1888 = SRAM(size = 36864, swapCtr = x1803(0), writeCtr = x1814(0), banking = Strided(1), doubleBuffer = true).wtPort(x1801_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1800_x1888))
      Stage(stage(1), operands=List(x1803(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr232)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr232), CU.ctr(stage(1), x1814(0))), op=FixAdd, results=List(x1800_x1888.writeAddr))
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1829(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr239)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr239), CU.ctr(stage(1), x1883(1))), op=FixAdd, results=List(x1800_x1888.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x1883(1)), Const("383i")), op=FixEql, results=List(CU.temp(stage(3), tr245)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr245), Const("1i"), CU.load(stage(3), x1800_x1888)), op=Mux, results=List(CU.temp(stage(4), tr246)))
      Stage(stage(5), operands=List(CU.ctr(stage(4), x1883(0)), CU.scalarIn(stage(4), x1835_scalar)), op=FixEql, results=List(CU.temp(stage(5), tr247)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr247), CU.temp(stage(5), tr246), Const("0i")), op=Mux, results=List(CU.vecOut(stage(6), x1880_vector)))
    }
    val x1921 = ComputeUnit(name="x1921", parent=x1923, tpe = Pipe, deps=List(x1901)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr263 = CU.temp
      val tr262 = CU.temp
      val tr265 = CU.temp
      val tr254 = CU.temp
      val tr251 = CU.temp
      val tr261 = CU.temp
      val x1830 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x1831 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x1832 = CounterChain(name = "x1832", x1830, x1831)
      val x1829 = CounterChain.copy(x1923, "x1829")
      val x1883 = CounterChain.copy(x1901, "x1883")
      val x1880_x1909 = SRAM(size = 3072, swapCtr = x1883(0), writeCtr = x1883(0), banking = Strided(1), doubleBuffer = true).wtPort(x1880_vector)
      val x1827_x1910 = SRAM(size = 3072, swapCtr = x1832(0), writeCtr = x1832(0), banking = Strided(1), doubleBuffer = false)
      val wr268 = CU.wtAddr(x1827_x1910)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1880_x1909))
      Stage(stage(1), operands=List(x1883(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr251)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr251), CU.ctr(stage(1), x1883(1))), op=FixAdd, results=List(x1880_x1909.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1832(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr254)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr254), CU.ctr(stage(1), x1832(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr268), x1827_x1910.readAddr, x1880_x1909.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x1829(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(3), tr261)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x1832(0)), Const("8i")), op=FixLt, results=List(CU.temp(stage(4), tr262)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr261), CU.temp(stage(4), tr262)), op=BitAnd, results=List(CU.temp(stage(5), tr263)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr263), CU.load(stage(5), x1880_x1909), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr265)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr265), CU.load(stage(6), x1827_x1910)), op=FltAdd, results=List(CU.vecOut(stage(7), x1827_vector), CU.store(stage(7), x1827_x1910)))
    }
    val x1943 = ComputeUnit(name="x1943", parent=x1945, tpe = Pipe, deps=List(x1923)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr274 = CU.temp
      val tr280 = CU.temp
      val tr270 = CU.temp
      val tr282 = CU.temp
      val tr284 = CU.temp
      val tr281 = CU.temp
      val x1507 = CounterChain.copy(x1945, "x1507")
      val x1508 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x1509 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x1510 = CounterChain(name = "x1510", x1508, x1509)
      val x1832 = CounterChain.copy(x1921, "x1832")
      val x1827_x1932 = SRAM(size = 3072, swapCtr = x1832(0), writeCtr = x1832(0), banking = Strided(1), doubleBuffer = true).wtPort(x1827_vector)
      val x1505_x1935 = SRAM(size = 3072, swapCtr = x1510(0), writeCtr = x1510(0), banking = Strided(1), doubleBuffer = false)
      val wr287 = CU.wtAddr(x1505_x1935)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1827_x1932))
      Stage(stage(1), operands=List(x1832(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr270)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr270), CU.ctr(stage(1), x1832(1))), op=FixAdd, results=List(x1827_x1932.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1510(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr274)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr274), CU.ctr(stage(1), x1510(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr287), x1505_x1935.readAddr, x1827_x1932.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x1507(0)), CU.scalarIn(stage(2), x1399_argin)), op=FixLt, results=List(CU.temp(stage(3), tr280)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x1510(0)), Const("8i")), op=FixLt, results=List(CU.temp(stage(4), tr281)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr280), CU.temp(stage(4), tr281)), op=BitAnd, results=List(CU.temp(stage(5), tr282)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr282), CU.load(stage(5), x1827_x1932), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr284)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr284), CU.load(stage(6), x1505_x1935)), op=FltAdd, results=List(CU.vecOut(stage(7), x1505_vector), CU.store(stage(7), x1505_x1935)))
    }
    val x1964 = ComputeUnit(name="x1964", parent=x2048, tpe = Pipe, deps=List(x1945)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr290 = CU.temp
      val tr296 = CU.temp
      val x1947 = (Const("0i").out, CU.scalarIn(stage0, x1400_argin).out, Const("1i").out) // Counter
      val x1948 = CounterChain(name = "x1948", x1947)
      val x1510 = CounterChain.copy(x1943, "x1510")
      val x1505_x1957 = SRAM(size = 3072, swapCtr = x1510(0), writeCtr = x1510(0), banking = Diagonal(1,384), doubleBuffer = false).wtPort(x1505_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1505_x1957))
      Stage(stage(1), operands=List(x1510(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr290)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr290), CU.ctr(stage(1), x1510(1))), op=FixAdd, results=List(x1505_x1957.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1948(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr296)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr296), Const("383i")), op=FixAdd, results=List(x1505_x1957.readAddr))
      Stage(stage(3), operands=List(x1505_x1957.load), op=Bypass, results=List(CU.vecOut(stage(3), x1946_vector)))
    }
    val x1990 = ComputeUnit(name="x1990", parent=x2048, tpe = Pipe, deps=List(x1964)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr302 = CU.temp
      val tr309 = CU.temp
      val x1966 = (Const("0i").out, CU.scalarIn(stage0, x1400_argin).out, Const("1i").out) // Counter
      val x1967 = (Const("0i").out, CU.scalarIn(stage0, x1401_argin).out, Const("1i").out) // Counter
      val x1968 = CounterChain(name = "x1968", x1966, x1967)
      val x1948 = CounterChain.copy(x1964, "x1948")
      val x1510 = CounterChain.copy(x1943, "x1510")
      val x1505_x1980 = SRAM(size = 3072, swapCtr = x1510(0), writeCtr = x1510(0), banking = Strided(1), doubleBuffer = false).wtPort(x1505_vector)
      val x1946_x1983 = SRAM(size = 8, swapCtr = x1948(0), writeCtr = x1948(0), banking = Duplicated(), doubleBuffer = false).wtPort(x1946_vector).rdAddr(x1968(0)).wtAddr(x1948(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1505_x1980))
      Stage(stage(1), operands=List(x1510(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr302)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr302), CU.ctr(stage(1), x1510(1))), op=FixAdd, results=List(x1505_x1980.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1968(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr309)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr309), CU.ctr(stage(1), x1968(1))), op=FixAdd, results=List(x1505_x1980.readAddr))
      Stage(stage(3), operands=List(x1505_x1980.load, CU.load(stage(2), x1946_x1983)), op=FltDiv, results=List(CU.vecOut(stage(3), x1965_vector)))
    }
    val x2045 = ComputeUnit(name="x2045", parent=x2048, tpe = MetaPipeline, deps=List(x1990)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1992 = (Const("0i").out, Const("8i").out, Const("1i").out) // Counter
      val x1993 = CounterChain(name = "x1993", x1992)
    }
    val x2035 = ComputeUnit(name="x2035", parent=x2045, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr320 = CU.temp
      val tr317 = CU.temp
      val x2022 = (Const("0i").out, Const("384i").out, Const("1i").out) // Counter
      val x2023 = CounterChain(name = "x2023", x2022)
      val x1968 = CounterChain.copy(x1990, "x1968")
      val x1993 = CounterChain.copy(x2045, "x1993")
      val x1965_x2028 = SRAM(size = 3072, swapCtr = x1968(0), writeCtr = x1968(0), banking = Strided(1), doubleBuffer = false).wtPort(x1965_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1965_x2028))
      Stage(stage(1), operands=List(x1968(0), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr317)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr317), CU.ctr(stage(1), x1968(1))), op=FixAdd, results=List(x1965_x2028.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1993(0)), Const("384i")), op=FixMul, results=List(CU.temp(stage(1), tr320)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr320), CU.ctr(stage(1), x2023(0))), op=FixAdd, results=List(x1965_x2028.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x1965_x2028)), op=Bypass, results=List(CU.vecOut(stage(3), x1991_vector)))
    }
    val x2040 = UnitComputeUnit(name ="x2040", parent=x2045, deps=List(x2035)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1993 = CounterChain.copy(x2045, "x1993")
      val x2040_unitCC = CounterChain(name = "x2040_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1993(0)), CU.scalarIn(stage(0), x1401_argin)), op=FixMul, results=List(CU.scalarOut(stage(1), x2036_scalar)))
    }
    val x2042 = TileTransfer(name="x2042", parent=x2045, memctrl=x2042_mc_mc, mctpe=TileStore, deps=List(x2040), vec=x1991_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2042_ctr = (Const("0l").out, Const("384i").out, Const("1l").out) // Counter
      val x2042_cc = CounterChain(name = "x2042_cc", x2042_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2036_scalar), CU.ctr(stage(0), x2042_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2042_mc_mc.saddr)))
    }
    
  }
}
