import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object TPCHQ6CompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x1645_oc = OffChip()
    val x1646_oc = OffChip()
    val x1648_argin = ArgIn()
    val x1827_vector = Vector()
    val x1640_argin = ArgIn()
    val x1649_argout = ArgOut()
    val x1855_vector = Vector()
    val x1643_oc = OffChip()
    val x1658_scalar = Scalar()
    val x1841_vector = Vector()
    val x1644_oc = OffChip()
    val x1873_scalar = Scalar()
    val x1647_argin = ArgIn()
    val x1813_vector = Vector()
    val x1856_mc_mc = MemoryController(TileLoad, x1646_oc)
    val x1814_mc_mc = MemoryController(TileLoad, x1643_oc)
    val x1828_mc_mc = MemoryController(TileLoad, x1644_oc)
    val x1842_mc_mc = MemoryController(TileLoad, x1645_oc)
    val x1923 = ComputeUnit(name="x1923", parent=top, tpe = Sequential, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1923_unitCC = CounterChain(name = "x1923_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1916 = ComputeUnit(name="x1916", parent=x1923, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1660 = (Const("0i").out, CU.scalarIn(stage0, x1640_argin).out, Const("96i").out) // Counter
      val x1661 = CounterChain(name = "x1661", x1660)
    }
    val x1826 = ComputeUnit(name="x1826", parent=x1916, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1826_unitCC = CounterChain(name = "x1826_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1814 = TileTransfer(name="x1814", parent=x1826, memctrl=x1814_mc_mc, mctpe=TileLoad, deps=List(), vec=x1813_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1661 = CounterChain.copy(x1916, "x1661")
      val x1814_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1814_cc = CounterChain(name = "x1814_cc", x1814_ctr)
      val x1815 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1816 = CounterChain(name = "x1816", x1815)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1661(0)), CU.ctr(stage(0), x1814_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1814_mc_mc.saddr)))
    }
    val x1840 = ComputeUnit(name="x1840", parent=x1916, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1840_unitCC = CounterChain(name = "x1840_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1828 = TileTransfer(name="x1828", parent=x1840, memctrl=x1828_mc_mc, mctpe=TileLoad, deps=List(), vec=x1827_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1661 = CounterChain.copy(x1916, "x1661")
      val x1828_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1828_cc = CounterChain(name = "x1828_cc", x1828_ctr)
      val x1829 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1830 = CounterChain(name = "x1830", x1829)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1661(0)), CU.ctr(stage(0), x1828_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1828_mc_mc.saddr)))
    }
    val x1854 = ComputeUnit(name="x1854", parent=x1916, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1854_unitCC = CounterChain(name = "x1854_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1842 = TileTransfer(name="x1842", parent=x1854, memctrl=x1842_mc_mc, mctpe=TileLoad, deps=List(), vec=x1841_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1661 = CounterChain.copy(x1916, "x1661")
      val x1842_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1842_cc = CounterChain(name = "x1842_cc", x1842_ctr)
      val x1843 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1844 = CounterChain(name = "x1844", x1843)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1661(0)), CU.ctr(stage(0), x1842_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1842_mc_mc.saddr)))
    }
    val x1868 = ComputeUnit(name="x1868", parent=x1916, tpe = MetaPipeline, deps=List()) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1868_unitCC = CounterChain(name = "x1868_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1856 = TileTransfer(name="x1856", parent=x1868, memctrl=x1856_mc_mc, mctpe=TileLoad, deps=List(), vec=x1855_vector) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1661 = CounterChain.copy(x1916, "x1661")
      val x1856_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1856_cc = CounterChain(name = "x1856_cc", x1856_ctr)
      val x1857 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1858 = CounterChain(name = "x1858", x1857)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1661(0)), CU.ctr(stage(0), x1856_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1856_mc_mc.saddr)))
    }
    val x1903 = ComputeUnit(name="x1903", parent=x1916, tpe = Pipe, deps=List(x1826, x1840, x1854, x1868)) { implicit CU =>
      val stage0 = CU.emptyStage
      val tr92 = CU.temp
      val tr100 = CU.temp
      val tr97 = CU.temp
      val tr90 = CU.temp
      val tr91 = CU.temp
      val tr95 = CU.temp
      val tr98 = CU.temp
      val tr101 = CU.temp
      val tr102 = CU.temp
      val tr94 = CU.temp
      val x1844 = CounterChain.copy(x1842, "x1844")
      val x1816 = CounterChain.copy(x1814, "x1816")
      val x1858 = CounterChain.copy(x1856, "x1858")
      val x1830 = CounterChain.copy(x1828, "x1830")
      val x1871 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1872 = CounterChain(name = "x1872", x1871)
      val x1809_x1879 = SRAM(size = 96, vec = x1813_vector, readAddr = x1872(0), writeAddr = x1816(0), swapCtr = x1816(0), writeCtr = x1816(0), banking = Strided(1), doubleBuffer = true)
      val x1810_x1881 = SRAM(size = 96, vec = x1827_vector, readAddr = x1872(0), writeAddr = x1830(0), swapCtr = x1830(0), writeCtr = x1830(0), banking = Strided(1), doubleBuffer = true)
      val x1811_x1880 = SRAM(size = 96, vec = x1841_vector, readAddr = x1872(0), writeAddr = x1844(0), swapCtr = x1844(0), writeCtr = x1844(0), banking = Strided(1), doubleBuffer = true)
      val x1812_x1882 = SRAM(size = 96, vec = x1855_vector, readAddr = x1872(0), writeAddr = x1858(0), swapCtr = x1858(0), writeCtr = x1858(0), banking = Strided(1), doubleBuffer = true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1647_argin), x1809_x1879.load), op=FixLt, results=List(CU.temp(stage(1), tr90)))
      Stage(stage(2), operands=List(CU.load(stage(1), x1809_x1879), CU.scalarIn(stage(1), x1648_argin)), op=FixLt, results=List(CU.temp(stage(2), tr91)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr90), CU.temp(stage(2), tr91)), op=BitAnd, results=List(CU.temp(stage(3), tr92)))
      Stage(stage(4), operands=List(Const("0.05f"), CU.load(stage(3), x1811_x1880)), op=FltLeq, results=List(CU.temp(stage(4), tr94)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr92), CU.temp(stage(4), tr94)), op=BitAnd, results=List(CU.temp(stage(5), tr95)))
      Stage(stage(6), operands=List(CU.load(stage(5), x1811_x1880), Const("0.07f")), op=FltLeq, results=List(CU.temp(stage(6), tr97)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr95), CU.temp(stage(6), tr97)), op=BitAnd, results=List(CU.temp(stage(7), tr98)))
      Stage(stage(8), operands=List(CU.load(stage(7), x1810_x1881), Const("24i")), op=FixLt, results=List(CU.temp(stage(8), tr100)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr98), CU.temp(stage(8), tr100)), op=BitAnd, results=List(CU.temp(stage(9), tr101)))
      Stage(stage(10), operands=List(CU.load(stage(9), x1812_x1882), CU.load(stage(9), x1811_x1880)), op=FltMul, results=List(CU.temp(stage(10), tr102)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr101), CU.temp(stage(10), tr102), Const("0i")), op=Mux, results=List(CU.reduce(stage(11))))
      val (rs1, rr107) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(12), operands=List(rr107), op=Bypass, results=List(CU.scalarOut(stage(12), x1873_scalar)))
    }
    val x1914 = UnitComputeUnit(name ="x1914", parent=x1916, deps=List(x1903)) { implicit CU =>
      val stage0 = CU.emptyStage
      val ar111 = CU.accum(init = Const("0i"))
      val x1914_unitCC = CounterChain(name = "x1914_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1873_scalar), CU.accum(stage(1), ar111)), op=FltAdd, results=List(CU.scalarOut(stage(1), x1658_scalar), CU.accum(stage(1), ar111)))
    }
    val x1920 = UnitComputeUnit(name ="x1920", parent=x1923, deps=List(x1916)) { implicit CU =>
      val stage0 = CU.emptyStage
      val x1920_unitCC = CounterChain(name = "x1920_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1658_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x1649_argout)))
    }

  }
}
