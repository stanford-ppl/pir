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
    val x1624_oc = OffChip()
    val x1831_vector = Vector()
    val x1789_vector = Vector()
    val x1817_vector = Vector()
    val x1634_scalar = Scalar()
    val x1803_vector = Vector()
    val x1625_oc = OffChip()
    val x1627_argout = ArgOut()
    val x1626_oc = OffChip()
    val x1623_oc = OffChip()
    val x1849_scalar = Scalar()
    val x1832_mc_mc = MemoryController(TileLoad, x1626_oc)
    val x1790_mc_mc = MemoryController(TileLoad, x1623_oc)
    val x1818_mc_mc = MemoryController(TileLoad, x1625_oc)
    val x1804_mc_mc = MemoryController(TileLoad, x1624_oc)
    val x1899 = ComputeUnit(name="x1899", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1899_unitCC = CounterChain(name = "x1899_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1892 = ComputeUnit(name="x1892", parent=x1899, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1635 = (Const("0i").out, Const("93600000i").out, Const("2400i").out) // Counter
      val x1636 = CounterChain(name = "x1636", x1635)
    }
    val x1802 = ComputeUnit(name="x1802", parent=x1892, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1802_unitCC = CounterChain(name = "x1802_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1790 = TileTransfer(name="x1790", parent=x1802, memctrl=x1790_mc_mc, mctpe=TileLoad, deps=List(), vec=x1789_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1636 = CounterChain.copy(x1892, "x1636")
      val x1790_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1790_cc = CounterChain(name = "x1790_cc", x1790_ctr)
      val x1791 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1792 = CounterChain(name = "x1792", x1791).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1636(0)), CU.ctr(stage(0), x1790_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1790_mc_mc.saddr)))
    }
    val x1816 = ComputeUnit(name="x1816", parent=x1892, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1816_unitCC = CounterChain(name = "x1816_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1804 = TileTransfer(name="x1804", parent=x1816, memctrl=x1804_mc_mc, mctpe=TileLoad, deps=List(), vec=x1803_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1636 = CounterChain.copy(x1892, "x1636")
      val x1804_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1804_cc = CounterChain(name = "x1804_cc", x1804_ctr)
      val x1805 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1806 = CounterChain(name = "x1806", x1805).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1636(0)), CU.ctr(stage(0), x1804_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1804_mc_mc.saddr)))
    }
    val x1830 = ComputeUnit(name="x1830", parent=x1892, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1830_unitCC = CounterChain(name = "x1830_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1818 = TileTransfer(name="x1818", parent=x1830, memctrl=x1818_mc_mc, mctpe=TileLoad, deps=List(), vec=x1817_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1636 = CounterChain.copy(x1892, "x1636")
      val x1818_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1818_cc = CounterChain(name = "x1818_cc", x1818_ctr)
      val x1819 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1820 = CounterChain(name = "x1820", x1819).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1636(0)), CU.ctr(stage(0), x1818_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1818_mc_mc.saddr)))
    }
    val x1844 = ComputeUnit(name="x1844", parent=x1892, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1844_unitCC = CounterChain(name = "x1844_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1832 = TileTransfer(name="x1832", parent=x1844, memctrl=x1832_mc_mc, mctpe=TileLoad, deps=List(), vec=x1831_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1636 = CounterChain.copy(x1892, "x1636")
      val x1832_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1832_cc = CounterChain(name = "x1832_cc", x1832_ctr)
      val x1833 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1834 = CounterChain(name = "x1834", x1833).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1636(0)), CU.ctr(stage(0), x1832_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1832_mc_mc.saddr)))
    }
    val x1879 = ComputeUnit(name="x1879", parent=x1892, tpe = Pipe, deps=List(x1802, x1816, x1830, x1844)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr101 = CU.temp
      val tr91 = CU.temp
      val tr95 = CU.temp
      val tr92 = CU.temp
      val tr102 = CU.temp
      val tr98 = CU.temp
      val tr97 = CU.temp
      val tr100 = CU.temp
      val tr94 = CU.temp
      val tr89 = CU.temp
      val x1792 = CounterChain.copy(x1790, "x1792")
      val x1806 = CounterChain.copy(x1804, "x1806")
      val x1834 = CounterChain.copy(x1832, "x1834")
      val x1847 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1848 = CounterChain(name = "x1848", x1847)
      val x1820 = CounterChain.copy(x1818, "x1820")
      val x1785_x1855 = SRAM(size = 2400, swapCtr = x1792(0), writeCtr = x1792(0), banking = Strided(1), doubleBuffer = true).wtPort(x1789_vector).rdAddr(x1848(0)).wtAddr(x1792(0))
      val x1786_x1857 = SRAM(size = 2400, swapCtr = x1806(0), writeCtr = x1806(0), banking = Strided(1), doubleBuffer = true).wtPort(x1803_vector).rdAddr(x1848(0)).wtAddr(x1806(0))
      val x1787_x1856 = SRAM(size = 2400, swapCtr = x1820(0), writeCtr = x1820(0), banking = Strided(1), doubleBuffer = true).wtPort(x1817_vector).rdAddr(x1848(0)).wtAddr(x1820(0))
      val x1788_x1858 = SRAM(size = 2400, swapCtr = x1834(0), writeCtr = x1834(0), banking = Strided(1), doubleBuffer = true).wtPort(x1831_vector).rdAddr(x1848(0)).wtAddr(x1834(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(Const("1i"), x1785_x1855.load), op=FixLt, results=List(CU.temp(stage(1), tr89)))
      Stage(stage(2), operands=List(CU.load(stage(1), x1785_x1855), Const("8i")), op=FixLt, results=List(CU.temp(stage(2), tr91)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr89), CU.temp(stage(2), tr91)), op=BitAnd, results=List(CU.temp(stage(3), tr92)))
      Stage(stage(4), operands=List(Const("0.05f"), CU.load(stage(3), x1787_x1856)), op=FltLeq, results=List(CU.temp(stage(4), tr94)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr92), CU.temp(stage(4), tr94)), op=BitAnd, results=List(CU.temp(stage(5), tr95)))
      Stage(stage(6), operands=List(CU.load(stage(5), x1787_x1856), Const("0.07f")), op=FltLeq, results=List(CU.temp(stage(6), tr97)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr95), CU.temp(stage(6), tr97)), op=BitAnd, results=List(CU.temp(stage(7), tr98)))
      Stage(stage(8), operands=List(CU.load(stage(7), x1786_x1857), Const("24i")), op=FixLt, results=List(CU.temp(stage(8), tr100)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr98), CU.temp(stage(8), tr100)), op=BitAnd, results=List(CU.temp(stage(9), tr101)))
      Stage(stage(10), operands=List(CU.load(stage(9), x1788_x1858), CU.load(stage(9), x1787_x1856)), op=FltMul, results=List(CU.temp(stage(10), tr102)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr101), CU.temp(stage(10), tr102), Const("0i")), op=Mux, results=List(CU.reduce(stage(11))))
      val (rs1, rr107) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(12), operands=List(rr107), op=Bypass, results=List(CU.scalarOut(stage(12), x1849_scalar)))
    }
    val x1890 = UnitComputeUnit(name ="x1890", parent=x1892, deps=List(x1879)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar111 = CU.accum(init = Const("0i"))
      val x1890_unitCC = CounterChain(name = "x1890_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1849_scalar), CU.accum(stage(1), ar111)), op=FltAdd, results=List(CU.scalarOut(stage(1), x1634_scalar), CU.accum(stage(1), ar111)))
    }
    val x1896 = UnitComputeUnit(name ="x1896", parent=x1899, deps=List(x1892)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1896_unitCC = CounterChain(name = "x1896_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1634_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x1627_argout)))
    }
    
  }
}
