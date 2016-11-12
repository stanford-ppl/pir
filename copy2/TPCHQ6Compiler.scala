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
    val x1798_vector = Vector()
    val x1624_argout = ArgOut()
    val x1617_argin = ArgIn()
    val x1784_vector = Vector()
    val x1812_vector = Vector()
    val x1620_oc = OffChip()
    val x1630_scalar = Scalar()
    val x1622_oc = OffChip()
    val x1844_scalar = Scalar()
    val x1826_vector = Vector()
    val x1621_oc = OffChip()
    val x1623_oc = OffChip()
    val x1799_mc_mc = MemoryController(TileLoad, x1621_oc)
    val x1813_mc_mc = MemoryController(TileLoad, x1622_oc)
    val x1785_mc_mc = MemoryController(TileLoad, x1620_oc)
    val x1827_mc_mc = MemoryController(TileLoad, x1623_oc)
    val x1894 = ComputeUnit(name="x1894", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1894_unitCC = CounterChain(name = "x1894_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1887 = ComputeUnit(name="x1887", parent=x1894, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1631 = (Const("0i").out, CU.scalarIn(stage0, x1617_argin).out, Const("2400i").out) // Counter
      val x1632 = CounterChain(name = "x1632", x1631)
    }
    val x1797 = ComputeUnit(name="x1797", parent=x1887, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1797_unitCC = CounterChain(name = "x1797_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1785 = TileTransfer(name="x1785", parent=x1797, memctrl=x1785_mc_mc, mctpe=TileLoad, deps=List(), vec=x1784_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1632 = CounterChain.copy(x1887, "x1632")
      val x1785_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1785_cc = CounterChain(name = "x1785_cc", x1785_ctr)
      val x1786 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1787 = CounterChain(name = "x1787", x1786).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1632(0)), CU.ctr(stage(0), x1785_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1785_mc_mc.saddr)))
    }
    val x1811 = ComputeUnit(name="x1811", parent=x1887, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1811_unitCC = CounterChain(name = "x1811_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1799 = TileTransfer(name="x1799", parent=x1811, memctrl=x1799_mc_mc, mctpe=TileLoad, deps=List(), vec=x1798_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1632 = CounterChain.copy(x1887, "x1632")
      val x1799_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1799_cc = CounterChain(name = "x1799_cc", x1799_ctr)
      val x1800 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1801 = CounterChain(name = "x1801", x1800).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1632(0)), CU.ctr(stage(0), x1799_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1799_mc_mc.saddr)))
    }
    val x1825 = ComputeUnit(name="x1825", parent=x1887, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1825_unitCC = CounterChain(name = "x1825_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1813 = TileTransfer(name="x1813", parent=x1825, memctrl=x1813_mc_mc, mctpe=TileLoad, deps=List(), vec=x1812_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1632 = CounterChain.copy(x1887, "x1632")
      val x1813_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1813_cc = CounterChain(name = "x1813_cc", x1813_ctr)
      val x1814 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1815 = CounterChain(name = "x1815", x1814).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1632(0)), CU.ctr(stage(0), x1813_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1813_mc_mc.saddr)))
    }
    val x1839 = ComputeUnit(name="x1839", parent=x1887, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1839_unitCC = CounterChain(name = "x1839_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1827 = TileTransfer(name="x1827", parent=x1839, memctrl=x1827_mc_mc, mctpe=TileLoad, deps=List(), vec=x1826_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1632 = CounterChain.copy(x1887, "x1632")
      val x1827_ctr = (Const("0l").out, Const("2400i").out, Const("1l").out) // Counter
      val x1827_cc = CounterChain(name = "x1827_cc", x1827_ctr)
      val x1828 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1829 = CounterChain(name = "x1829", x1828).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1632(0)), CU.ctr(stage(0), x1827_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1827_mc_mc.saddr)))
    }
    val x1874 = ComputeUnit(name="x1874", parent=x1887, tpe = Pipe, deps=List(x1797, x1811, x1825, x1839)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr97 = CU.temp
      val tr101 = CU.temp
      val tr92 = CU.temp
      val tr89 = CU.temp
      val tr98 = CU.temp
      val tr102 = CU.temp
      val tr91 = CU.temp
      val tr94 = CU.temp
      val tr100 = CU.temp
      val tr95 = CU.temp
      val x1787 = CounterChain.copy(x1785, "x1787")
      val x1815 = CounterChain.copy(x1813, "x1815")
      val x1842 = (Const("0i").out, Const("2400i").out, Const("1i").out) // Counter
      val x1843 = CounterChain(name = "x1843", x1842)
      val x1829 = CounterChain.copy(x1827, "x1829")
      val x1801 = CounterChain.copy(x1799, "x1801")
      val x1780_x1850 = SRAM(size = 2400, swapCtr = x1787(0), writeCtr = x1787(0), banking = Strided(1), doubleBuffer = true).wtPort(x1784_vector).rdAddr(x1843(0)).wtAddr(x1787(0))
      val x1781_x1852 = SRAM(size = 2400, swapCtr = x1801(0), writeCtr = x1801(0), banking = Strided(1), doubleBuffer = true).wtPort(x1798_vector).rdAddr(x1843(0)).wtAddr(x1801(0))
      val x1782_x1851 = SRAM(size = 2400, swapCtr = x1815(0), writeCtr = x1815(0), banking = Strided(1), doubleBuffer = true).wtPort(x1812_vector).rdAddr(x1843(0)).wtAddr(x1815(0))
      val x1783_x1853 = SRAM(size = 2400, swapCtr = x1829(0), writeCtr = x1829(0), banking = Strided(1), doubleBuffer = true).wtPort(x1826_vector).rdAddr(x1843(0)).wtAddr(x1829(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(Const("1i"), x1780_x1850.load), op=FixLt, results=List(CU.temp(stage(1), tr89)))
      Stage(stage(2), operands=List(CU.load(stage(1), x1780_x1850), Const("8i")), op=FixLt, results=List(CU.temp(stage(2), tr91)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr89), CU.temp(stage(2), tr91)), op=BitAnd, results=List(CU.temp(stage(3), tr92)))
      Stage(stage(4), operands=List(Const("0.05f"), CU.load(stage(3), x1782_x1851)), op=FltLeq, results=List(CU.temp(stage(4), tr94)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr92), CU.temp(stage(4), tr94)), op=BitAnd, results=List(CU.temp(stage(5), tr95)))
      Stage(stage(6), operands=List(CU.load(stage(5), x1782_x1851), Const("0.07f")), op=FltLeq, results=List(CU.temp(stage(6), tr97)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr95), CU.temp(stage(6), tr97)), op=BitAnd, results=List(CU.temp(stage(7), tr98)))
      Stage(stage(8), operands=List(CU.load(stage(7), x1781_x1852), Const("24i")), op=FixLt, results=List(CU.temp(stage(8), tr100)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr98), CU.temp(stage(8), tr100)), op=BitAnd, results=List(CU.temp(stage(9), tr101)))
      Stage(stage(10), operands=List(CU.load(stage(9), x1783_x1853), CU.load(stage(9), x1782_x1851)), op=FltMul, results=List(CU.temp(stage(10), tr102)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr101), CU.temp(stage(10), tr102), Const("0i")), op=Mux, results=List(CU.reduce(stage(11))))
      val (rs1, rr107) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(12), operands=List(rr107), op=Bypass, results=List(CU.scalarOut(stage(12), x1844_scalar)))
    }
    val x1885 = UnitComputeUnit(name ="x1885", parent=x1887, deps=List(x1874)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar111 = CU.accum(init = Const("0i"))
      val x1885_unitCC = CounterChain(name = "x1885_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1844_scalar), CU.accum(stage(1), ar111)), op=FltAdd, results=List(CU.scalarOut(stage(1), x1630_scalar), CU.accum(stage(1), ar111)))
    }
    val x1891 = UnitComputeUnit(name ="x1891", parent=x1894, deps=List(x1887)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1891_unitCC = CounterChain(name = "x1891_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x1630_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x1624_argout)))
    }
    
  }
}
