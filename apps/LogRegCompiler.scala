import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object LogRegCompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x2013_vector = Vector()
    val x1586_oc = OffChip()
    val x1649_vector = Vector()
    val x2036_vector = Vector()
    val x1928_scalar = Scalar()
    val x1884_scalar = Scalar()
    val x1932_scalar = Scalar()
    val x1929_vector = Vector()
    val x1630_vector = Vector()
    val x1584_oc = OffChip()
    val x1880_vector = Vector()
    val x1585_oc = OffChip()
    val x1906_vector = Vector()
    val x1922_vector = Vector()
    val x1631_mc_mc = MemoryController(TileLoad, x1586_oc)
    val x1907_mc_mc = MemoryController(TileLoad, x1585_oc)
    val x1891_mc_mc = MemoryController(TileLoad, x1584_oc)
    val x2054_mc_mc = MemoryController(TileStore, x1586_oc)
    val x2059 = ComputeUnit(name="x2059", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2059_unitCC = CounterChain(name = "x2059_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1648 = ComputeUnit(name="x1648", parent=x2059, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1648_unitCC = CounterChain(name = "x1648_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1631 = TileTransfer(name="x1631", parent=x1648, memctrl=x1631_mc_mc, mctpe=TileLoad, deps=List(), vec=x1630_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1631_ctr = (Const("0l").out, Const("768i").out, Const("1l").out) // Counter
      val x1631_cc = CounterChain(name = "x1631_cc", x1631_ctr)
      val x1632 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x1633 = CounterChain(name = "x1633", x1632).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1631_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1631_mc_mc.saddr)))
    }
    val x2012 = ComputeUnit(name="x2012", parent=x2059, tpe = MetaPipeline, deps=List(x1648)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1650 = (Const("0i").out, Const("960000i").out, Const("96i").out) // Counter
      val x1651 = CounterChain(name = "x1651", x1650)
    }
    val x1905 = ComputeUnit(name="x1905", parent=x2012, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1881 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1882 = CounterChain(name = "x1882", x1881)
    }
    val x1889 = UnitComputeUnit(name ="x1889", parent=x1905, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr135 = CU.temp
      val x1651 = CounterChain.copy(x2012, "x1651")
      val x1882 = CounterChain.copy(x1905, "x1882")
      val x1889_unitCC = CounterChain(name = "x1889_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1651(0)), CU.ctr(stage(0), x1882(0))), op=FixAdd, results=List(CU.temp(stage(1), tr135)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr135), Const("768i")), op=FixMul, results=List(CU.scalarOut(stage(2), x1884_scalar)))
    }
    val x1891 = TileTransfer(name="x1891", parent=x1905, memctrl=x1891_mc_mc, mctpe=TileLoad, deps=List(x1889), vec=x1880_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1891_ctr = (Const("0l").out, Const("768i").out, Const("1l").out) // Counter
      val x1891_cc = CounterChain(name = "x1891_cc", x1891_ctr)
      val x1892 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x1893 = CounterChain(name = "x1893", x1892).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1884_scalar), CU.ctr(stage(0), x1891_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1891_mc_mc.saddr)))
    }
    val x1919 = ComputeUnit(name="x1919", parent=x2012, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1919_unitCC = CounterChain(name = "x1919_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1907 = TileTransfer(name="x1907", parent=x1919, memctrl=x1907_mc_mc, mctpe=TileLoad, deps=List(), vec=x1906_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1651 = CounterChain.copy(x2012, "x1651")
      val x1907_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1907_cc = CounterChain(name = "x1907_cc", x1907_ctr)
      val x1908 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1909 = CounterChain(name = "x1909", x1908).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1651(0)), CU.ctr(stage(0), x1907_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1907_mc_mc.saddr)))
    }
    val x1994 = ComputeUnit(name="x1994", parent=x2012, tpe = MetaPipeline, deps=List(x1905, x1919)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1923 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1924 = CounterChain(name = "x1924", x1923)
    }
    val x1951 = ComputeUnit(name="x1951", parent=x1994, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr145 = CU.temp
      val tr148 = CU.temp
      val x1633 = CounterChain.copy(x1631, "x1633")
      val x1882 = CounterChain.copy(x1905, "x1882")
      val x1930 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x1931 = CounterChain(name = "x1931", x1930)
      val x1924 = CounterChain.copy(x1994, "x1924")
      val x1893 = CounterChain.copy(x1891, "x1893")
      val x1629_x1942 = SRAM(size = 768, swapCtr = x1633(0), writeCtr = x1633(0), banking = Strided(1), doubleBuffer = false).wtPort(x1630_vector).rdAddr(x1931(0)).wtAddr(x1633(0))
      val x1878_x1940 = SRAM(size = 73728, swapCtr = x1882(0), writeCtr = x1893(0), banking = Strided(1), doubleBuffer = true).wtPort(x1880_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1878_x1940))
      Stage(stage(1), operands=List(x1882(0), Const("768i")), op=FixMul, results=List(CU.temp(stage(1), tr145)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr145), CU.ctr(stage(1), x1893(0))), op=FixAdd, results=List(x1878_x1940.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1924(0)), Const("768i")), op=FixMul, results=List(CU.temp(stage(1), tr148)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr148), CU.ctr(stage(1), x1931(0))), op=FixAdd, results=List(x1878_x1940.readAddr))
      Stage(stage(3), operands=List(x1878_x1940.load, CU.load(stage(2), x1629_x1942)), op=FltMul, results=List(CU.reduce(stage(3))))
      val (rs1, rr157) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(4), operands=List(rr157), op=Bypass, results=List(CU.scalarOut(stage(4), x1932_scalar)))
    }
    val x1961 = UnitComputeUnit(name ="x1961", parent=x1994, deps=List(x1951)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr165 = CU.temp
      val tr168 = CU.temp
      val tr164 = CU.temp
      val tr167 = CU.temp
      val x1961_unitCC = CounterChain(name = "x1961_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      val x1924 = CounterChain.copy(x1994, "x1924")
      val x1909 = CounterChain.copy(x1907, "x1909")
      val x1879_x1952 = SRAM(size = 96, swapCtr = x1909(0), writeCtr = x1909(0), banking = Strided(1), doubleBuffer = true).wtPort(x1906_vector).rdAddr(x1924(0)).wtAddr(x1909(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1932_scalar), Const("-1f")), op=FltMul, results=List(CU.temp(stage(1), tr164)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr164)), op=FltExp, results=List(CU.temp(stage(2), tr165)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr165), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr167)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr167)), op=FltDiv, results=List(CU.temp(stage(4), tr168)))
      Stage(stage(5), operands=List(CU.load(stage(4), x1879_x1952), CU.temp(stage(4), tr168)), op=FltSub, results=List(CU.scalarOut(stage(5), x1928_scalar)))
    }
    val x1975 = ComputeUnit(name="x1975", parent=x1994, tpe = Pipe, deps=List(x1961)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr171 = CU.temp
      val tr175 = CU.temp
      val x1882 = CounterChain.copy(x1905, "x1882")
      val x1924 = CounterChain.copy(x1994, "x1924")
      val x1962 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x1963 = CounterChain(name = "x1963", x1962)
      val x1893 = CounterChain.copy(x1891, "x1893")
      val x1878_x1967 = SRAM(size = 73728, swapCtr = x1882(0), writeCtr = x1893(0), banking = Strided(1), doubleBuffer = true).wtPort(x1880_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1878_x1967))
      Stage(stage(1), operands=List(x1882(0), Const("768i")), op=FixMul, results=List(CU.temp(stage(1), tr171)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr171), CU.ctr(stage(1), x1893(0))), op=FixAdd, results=List(x1878_x1967.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1924(0)), Const("768i")), op=FixMul, results=List(CU.temp(stage(1), tr175)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr175), CU.ctr(stage(1), x1963(0))), op=FixAdd, results=List(x1878_x1967.readAddr))
      Stage(stage(3), operands=List(x1878_x1967.load, CU.scalarIn(stage(2), x1928_scalar)), op=FltSub, results=List(CU.vecOut(stage(3), x1929_vector)))
    }
    val x1992 = ComputeUnit(name="x1992", parent=x1994, tpe = Pipe, deps=List(x1975)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr188 = CU.temp
      val tr187 = CU.temp
      val tr191 = CU.temp
      val tr189 = CU.temp
      val x1925 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x1926 = CounterChain(name = "x1926", x1925)
      val x1924 = CounterChain.copy(x1994, "x1924")
      val x1963 = CounterChain.copy(x1975, "x1963")
      val x1929_x1980 = SRAM(size = 768, swapCtr = x1963(0), writeCtr = x1963(0), banking = Duplicated(), doubleBuffer = true).wtPort(x1929_vector).rdAddr(x1926(0)).wtAddr(x1963(0))
      val x1922_x1981 = SRAM(size = 768, swapCtr = x1926(0), writeCtr = x1926(0), banking = Duplicated(), doubleBuffer = false).rdAddr(x1926(0))
      val wr194 = CU.wtAddr(x1922_x1981)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1924(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(1), tr187)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1926(0)), Const("768i")), op=FixLt, results=List(CU.temp(stage(2), tr188)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr187), CU.temp(stage(2), tr188)), op=BitAnd, results=List(CU.temp(stage(3), tr189)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr189), CU.load(stage(3), x1929_x1980), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr191)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr191), CU.load(stage(4), x1922_x1981)), op=FltAdd, results=List(CU.vecOut(stage(5), x1922_vector), CU.store(stage(5), x1922_x1981)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x1926(0))), op=Bypass, results=List(CU.wtAddr(stage(6), wr194)))
    }
    val x2010 = ComputeUnit(name="x2010", parent=x2012, tpe = Pipe, deps=List(x1994)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr202 = CU.temp
      val tr204 = CU.temp
      val tr206 = CU.temp
      val tr203 = CU.temp
      val x1652 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x1653 = CounterChain(name = "x1653", x1652)
      val x1651 = CounterChain.copy(x2012, "x1651")
      val x1926 = CounterChain.copy(x1992, "x1926")
      val x1922_x1999 = SRAM(size = 768, swapCtr = x1926(0), writeCtr = x1926(0), banking = Duplicated(), doubleBuffer = true).wtPort(x1922_vector).rdAddr(x1653(0)).wtAddr(x1926(0))
      val x1649_x2002 = SRAM(size = 768, swapCtr = x1653(0), writeCtr = x1653(0), banking = Duplicated(), doubleBuffer = false).rdAddr(x1653(0))
      val wr209 = CU.wtAddr(x1649_x2002)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1651(0)), Const("960000i")), op=FixLt, results=List(CU.temp(stage(1), tr202)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1653(0)), Const("768i")), op=FixLt, results=List(CU.temp(stage(2), tr203)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr202), CU.temp(stage(2), tr203)), op=BitAnd, results=List(CU.temp(stage(3), tr204)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr204), CU.load(stage(3), x1922_x1999), Const("0i")), op=Mux, results=List(CU.temp(stage(4), tr206)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr206), CU.load(stage(4), x1649_x2002)), op=FltAdd, results=List(CU.vecOut(stage(5), x1649_vector), CU.store(stage(5), x1649_x2002)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x1653(0))), op=Bypass, results=List(CU.wtAddr(stage(6), wr209)))
    }
    val x2035 = ComputeUnit(name="x2035", parent=x2059, tpe = Pipe, deps=List(x2012)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr218 = CU.temp
      val x1653 = CounterChain.copy(x2010, "x1653")
      val x1633 = CounterChain.copy(x1631, "x1633")
      val x2014 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x2015 = CounterChain(name = "x2015", x2014)
      val x1629_x2027 = SRAM(size = 768, swapCtr = x1633(0), writeCtr = x1633(0), banking = Strided(1), doubleBuffer = false).wtPort(x1630_vector).rdAddr(x2015(0)).wtAddr(x1633(0))
      val x1649_x2025 = SRAM(size = 768, swapCtr = x1653(0), writeCtr = x1653(0), banking = Duplicated(), doubleBuffer = false).wtPort(x1649_vector).rdAddr(x2015(0)).wtAddr(x1653(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1649_x2025.load, Const("1i")), op=FltMul, results=List(CU.temp(stage(1), tr218)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr218), CU.load(stage(1), x1629_x2027)), op=FltAdd, results=List(CU.vecOut(stage(2), x2013_vector)))
    }
    val x2056 = ComputeUnit(name="x2056", parent=x2059, tpe = MetaPipeline, deps=List(x2035)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2056_unitCC = CounterChain(name = "x2056_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2053 = ComputeUnit(name="x2053", parent=x2056, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2037 = (Const("0i").out, Const("768i").out, Const("1i").out) // Counter
      val x2038 = CounterChain(name = "x2038", x2037)
      val x2015 = CounterChain.copy(x2035, "x2015")
      val x2013_x2045 = SRAM(size = 768, swapCtr = x2015(0), writeCtr = x2015(0), banking = Strided(1), doubleBuffer = false).wtPort(x2013_vector).rdAddr(x2038(0)).wtAddr(x2015(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.load(stage(0), x2013_x2045)), op=Bypass, results=List(CU.vecOut(stage(1), x2036_vector)))
    }
    val x2054 = TileTransfer(name="x2054", parent=x2056, memctrl=x2054_mc_mc, mctpe=TileStore, deps=List(x2053), vec=x2036_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2054_ctr = (Const("0l").out, Const("768i").out, Const("1l").out) // Counter
      val x2054_cc = CounterChain(name = "x2054_cc", x2054_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x2054_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2054_mc_mc.saddr)))
    }
    
  }
}
