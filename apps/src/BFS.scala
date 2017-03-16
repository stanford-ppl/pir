import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object BFS extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1673_x1836_x1846_vector = Vector("x1673_x1836_x1846")
    val x1762_x1767_scalar = Scalar("x1762_x1767")
    val x1663_oc = OffChip("x1663")
    val x1763_x1769_scalar = Scalar("x1763_x1769")
    val x1673_x1727_x1670_vector = Vector("x1673_x1727_x1670")
    val x1740_b1876_x1759_b1885_scalar = Scalar("x1740_b1876_x1759_b1885")
    val x1677_b1862_x1682_b1865_scalar = Scalar("x1677_b1862_x1682_b1865")
    val x1673_x1728_x1670_vector = Vector("x1673_x1728_x1670")
    val x1670_x1731_x1738_vector = Vector("x1670_x1731_x1738")
    val x1672_x1830_vector = Vector("x1672_x1830")
    val x1671_x1729_x1738_vector = Vector("x1671_x1729_x1738")
    val x1661_oc = OffChip("x1661")
    val x1664_oc = OffChip("x1664")
    val x1680_argin = ArgIn("x1680")
    val x1677_b1860_x1682_b1863_scalar = Scalar("x1677_b1860_x1682_b1863")
    val x1724_x1730_scalar = Scalar("x1724_x1730")
    val x1739_b1874_x1756_b1883_scalar = Scalar("x1739_b1874_x1756_b1883")
    val x1764_x1771_scalar = Scalar("x1764_x1771")
    val x1669_x1822_x1826_vector = Vector("x1669_x1822_x1826")
    val x1832_b1887_x1845_b1889_vector = Vector("x1832_b1887_x1845_b1889")
    val x1726_x1737_scalar = Scalar("x1726_x1737")
    val x1672_x1838_x1846_vector = Vector("x1672_x1838_x1846")
    val x1671_x1693_vector = Vector("x1671_x1693")
    val x1676_x1854_scalar = Scalar("x1676_x1854")
    val x1832_b1888_x1845_b1890_vector = Vector("x1832_b1888_x1845_b1890")
    val x1697_b1866_x1702_b1869_scalar = Scalar("x1697_b1866_x1702_b1869")
    val x1725_x1734_scalar = Scalar("x1725_x1734")
    val x1678_x1683_scalar = Scalar("x1678_x1683")
    val x1791_x1801_vector = Vector("x1791_x1801")
    val x1673_x1825_vector = Vector("x1673_x1825")
    val x1675_x1816_scalar = Scalar("x1675_x1816")
    val x1700_argin = ArgIn("x1700")
    val x1723_x1732_scalar = Scalar("x1723_x1732")
    val x1739_b1873_x1756_b1882_scalar = Scalar("x1739_b1873_x1756_b1882")
    val x1669_x1811_vector = Vector("x1669_x1811")
    val x1670_x1733_x1738_vector = Vector("x1670_x1733_x1738")
    val x1740_b1877_x1759_b1886_scalar = Scalar("x1740_b1877_x1759_b1886")
    val x1837_argin = ArgIn("x1837")
    val x1791_x1806_x1669_vector = Vector("x1791_x1806_x1669")
    val x1674_x1787_vector = Vector("x1674_x1787")
    val x1674_x1807_x1812_vector = Vector("x1674_x1807_x1812")
    val x1662_oc = OffChip("x1662")
    val x1697_b1868_x1702_b1871_scalar = Scalar("x1697_b1868_x1702_b1871")
    val x1670_x1713_vector = Vector("x1670_x1713")
    val x1677_b1861_x1682_b1864_scalar = Scalar("x1677_b1861_x1682_b1864")
    val x1698_x1703_scalar = Scalar("x1698_x1703")
    val x1742_argin = ArgIn("x1742")
    val x1791_x1806_x1812_vector = Vector("x1791_x1806_x1812")
    val x1739_b1872_x1756_b1881_scalar = Scalar("x1739_b1872_x1756_b1881")
    val x1673_x1727_x1671_vector = Vector("x1673_x1727_x1671")
    val x1697_b1867_x1702_b1870_scalar = Scalar("x1697_b1867_x1702_b1870")
    val x1740_b1875_x1759_b1884_scalar = Scalar("x1740_b1875_x1759_b1884")
    val x1857 = Sequential(name="x1857",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1669_dsp0 = MemoryPipeline(name="x1669_dsp0",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1805 = CounterChain.copy("x1812", "x1805")
      val x1821 = CounterChain.copy("x1826", "x1821")
      val x1669_x1822 = SRAM(size = 8000, banking = Duplicated()).wtPort(x1669_x1811_vector).rdPort(x1669_x1822_x1826_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x1669_x1822))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1791_x1806_x1669_vector)), op=Bypass, results=List(x1669_x1822.writeAddr))
    }
    val x1670_dsp0 = MemoryPipeline(name="x1670_dsp0",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr220 = CU.temp
      val x1709 = CounterChain.copy("x1714", "x1709")
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1670_x1733 = SRAM(size = 8000, banking = Duplicated()).wtPort(x1670_x1713_vector).rdPort(x1670_x1733_x1738_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1670_x1733))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1722(0)), Const("1i")), op=FixSub, results=List(CU.temp(stage(1), tr220)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1673_x1728_x1670_vector)), op=Bypass, results=List(x1670_x1733.readAddr))
    }
    val x1670_dsp1 = MemoryPipeline(name="x1670_dsp1",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr222 = CU.temp
      val x1709 = CounterChain.copy("x1714", "x1709")
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1670_x1731 = SRAM(size = 8000, banking = Duplicated()).wtPort(x1670_x1713_vector).rdPort(x1670_x1733_x1738_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(2, List(x1670_x1731))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1722(0)), Const("1i")), op=FixSub, results=List(CU.temp(stage(1), tr222)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1673_x1728_x1670_vector)), op=Bypass, results=List(x1670_x1731.readAddr))
    }
    val x1671_dsp0 = MemoryPipeline(name="x1671_dsp0",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1689 = CounterChain.copy("x1694", "x1689")
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1671_x1729 = SRAM(size = 8000, banking = Duplicated()).wtPort(x1671_x1693_vector).rdPort(x1671_x1729_x1738_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: RAStages(1, List(x1671_x1729))
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1673_x1727_x1671_vector)), op=Bypass, results=List(x1671_x1729.readAddr))
    }
    val x1672_dsp0 = MemoryPipeline(name="x1672_dsp0",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1719 = CounterChain.copy("x1856", "x1719")
      val x1829 = CounterChain.copy("x1831", "x1829")
      val x1835 = CounterChain.copy("x1846", "x1835")
      val x1672_x1838 = SRAM(size = 8000, banking = Strided(1)).wtPort(x1672_x1830_vector).rdPort(x1672_x1838_x1846_vector)
      var stage: List[Stage] = Nil
    }
    val x1673_dsp0 = MemoryPipeline(name="x1673_dsp0",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1821 = CounterChain.copy("x1826", "x1821")
      val x1835 = CounterChain.copy("x1846", "x1835")
      val x1673_x1836 = SRAM(size = 8000, banking = Strided(1)).wtPort(x1673_x1825_vector).rdPort(x1673_x1836_x1846_vector)
      var stage: List[Stage] = Nil
    }
    val x1673_dsp1 = MemoryPipeline(name="x1673_dsp1",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1821 = CounterChain.copy("x1826", "x1821")
      val x1835 = CounterChain.copy("x1846", "x1835")
      val x1673_x1728 = SRAM(size = 8000, banking = Strided(1)).wtPort(x1673_x1825_vector).rdPort(x1673_x1836_x1846_vector)
      var stage: List[Stage] = Nil
    }
    val x1673_dsp2 = MemoryPipeline(name="x1673_dsp2",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1821 = CounterChain.copy("x1826", "x1821")
      val x1835 = CounterChain.copy("x1846", "x1835")
      val x1673_x1727 = SRAM(size = 8000, banking = Strided(1)).wtPort(x1673_x1825_vector).rdPort(x1673_x1836_x1846_vector)
      var stage: List[Stage] = Nil
    }
    val x1674_dsp0 = MemoryPipeline(name="x1674_dsp0",parent="x1857") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr223 = CU.temp
      val x1775 = CounterChain.copy("x1788", "x1775")
      val x1805 = CounterChain.copy("x1812", "x1805")
      val x1674_x1807 = SRAM(size = 8000, banking = Strided(1)).wtPort(x1674_x1787_vector).rdPort(x1674_x1807_x1812_vector)
      val x1762_x1776 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1762_x1767_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1674_x1807))
      Stage(stage(1), operands=List(x1775(0), x1762_x1776.load), op=FixSub, results=List(x1674_x1807.writeAddr, CU.temp(stage(1), tr223)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x1775(0)), CU.load(stage(1), x1762_x1776)), op=FixSub, results=List(CU.temp(stage(2), tr223)))
    }
    val x1696 = Sequential(name="x1696",parent=x1857) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1684 = UnitPipeline(name="x1684",parent=x1696) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr224 = CU.temp
      val tr225 = CU.temp
      val tr226 = CU.temp
      val x1680 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1680_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr224)), op=Bypass, results=List(CU.scalarOut(stage(1), x1677_b1860_x1682_b1863_scalar)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr225)), op=Bypass, results=List(CU.scalarOut(stage(2), x1677_b1861_x1682_b1864_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr226)), op=Bypass, results=List(CU.scalarOut(stage(3), x1677_b1862_x1682_b1865_scalar)))
      Stage(stage(4), operands=List(Const("8000i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1678_x1683_scalar)))
    }
    val x1685 = Fringe(name="x1685",parent=x1696,dram=x1663_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1677_b1862_x1685 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1677_b1862_x1682_b1865_scalar).rdPort(None)
      val x1677_b1861_x1685 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1677_b1861_x1682_b1864_scalar).rdPort(None)
      val x1677_b1860_x1685 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1677_b1860_x1682_b1863_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1695 = Sequential(name="x1695",parent=x1696) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1687 = Sequential(name="x1687",parent=x1695) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1694 = Pipeline(name="x1694",parent=x1695) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("8000i").out, Const("1i").out) // Counter
      val x1689 = CounterChain(name = "x1689", ctr1)
      val x1679_x1690 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1679_x1690.load), op=Bypass, results=List(CU.vecOut(stage(1), x1671_x1693_vector)))
    }
    val x1716 = Sequential(name="x1716",parent=x1857) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1704 = UnitPipeline(name="x1704",parent=x1716) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr228 = CU.temp
      val tr229 = CU.temp
      val tr230 = CU.temp
      val x1700 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1700_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr228)), op=Bypass, results=List(CU.scalarOut(stage(1), x1697_b1866_x1702_b1869_scalar)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr229)), op=Bypass, results=List(CU.scalarOut(stage(2), x1697_b1867_x1702_b1870_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr230)), op=Bypass, results=List(CU.scalarOut(stage(3), x1697_b1868_x1702_b1871_scalar)))
      Stage(stage(4), operands=List(Const("8000i")), op=Bypass, results=List(CU.scalarOut(stage(4), x1698_x1703_scalar)))
    }
    val x1705 = Fringe(name="x1705",parent=x1716,dram=x1662_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1697_b1868_x1705 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1697_b1868_x1702_b1871_scalar).rdPort(None)
      val x1697_b1867_x1705 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1697_b1867_x1702_b1870_scalar).rdPort(None)
      val x1697_b1866_x1705 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1697_b1866_x1702_b1869_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1715 = Sequential(name="x1715",parent=x1716) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1707 = Sequential(name="x1707",parent=x1715) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1714 = Pipeline(name="x1714",parent=x1715) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, Const("8000i").out, Const("1i").out) // Counter
      val x1709 = CounterChain(name = "x1709", ctr2)
      val x1699_x1710 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1699_x1710.load), op=Bypass, results=List(CU.vecOut(stage(1), x1670_x1713_vector)))
    }
    val x1856 = Sequential(name="x1856",parent=x1857) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1719 = CounterChain(name = "x1719", ctr3)
      var stage: List[Stage] = Nil
    }
    val x1818 = MetaPipeline(name="x1818",parent=x1856) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, x1676_x1720.load, Const("1i").out) // Counter
      val x1722 = CounterChain(name = "x1722", ctr4)
      val x1676_x1720 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1676_x1854_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1738 = UnitPipeline(name="x1738",parent=x1818) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr233 = CU.temp
      val ar47 = CU.accum(init = Const("0i"))
      val x1722 = CounterChain.copy("x1818", "x1722")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1722(0)), Const("1i")), op=FixSub, results=List(CU.temp(stage(1), tr233)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1671_x1729_x1738_vector)), op=Bypass, results=List(CU.scalarOut(stage(2), x1724_x1730_scalar)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x1670_x1731_x1738_vector)), op=Bypass, results=List(CU.scalarOut(stage(3), x1723_x1732_scalar)))
      Stage(stage(4), operands=List(CU.vecIn(stage(3), x1670_x1733_x1738_vector)), op=Bypass, results=List(CU.scalarOut(stage(4), x1725_x1734_scalar)))
      Stage(stage(5), operands=List(CU.accum(stage(5), ar47)), op=Bypass, results=List(CU.scalarOut(stage(5), x1726_x1737_scalar)))
    }
    val x1790 = Sequential(name="x1790",parent=x1818) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1760 = UnitPipeline(name="x1760",parent=x1790) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr235 = CU.temp
      val tr237 = CU.temp
      val tr238 = CU.temp
      val tr239 = CU.temp
      val tr241 = CU.temp
      val tr242 = CU.temp
      val tr243 = CU.temp
      val tr244 = CU.temp
      val tr245 = CU.temp
      val tr246 = CU.temp
      val tr247 = CU.temp
      val tr248 = CU.temp
      val tr249 = CU.temp
      val tr250 = CU.temp
      val tr251 = CU.temp
      val tr252 = CU.temp
      val tr253 = CU.temp
      val x1724_x1743 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1724_x1730_scalar).rdPort(None)
      val x1742 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1742_argin).rdPort(None)
      val x1726_x1744 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1726_x1737_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(17)
      Stage(stage(1), operands=List(x1724_x1743.load, Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr235)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr235), Const("16i")), op=FixMod, results=List(CU.temp(stage(2), tr237)))
      Stage(stage(3), operands=List(CU.load(stage(2), x1726_x1744), Const("16i")), op=FixMod, results=List(CU.temp(stage(3), tr238)))
      Stage(stage(4), operands=List(Const("16i"), CU.temp(stage(3), tr238)), op=FixSub, results=List(CU.temp(stage(4), tr239)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr238), Const("0i")), op=FixEql, results=List(CU.temp(stage(5), tr241)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr241), Const("0i"), CU.temp(stage(5), tr239)), op=Mux, results=List(CU.temp(stage(6), tr242)))
      Stage(stage(7), operands=List(CU.load(stage(6), x1726_x1744), CU.temp(stage(6), tr242)), op=FixAdd, results=List(CU.temp(stage(7), tr243)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr237), CU.load(stage(7), x1726_x1744)), op=FixAdd, results=List(CU.temp(stage(8), tr244)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr235), CU.load(stage(8), x1742)), op=FixAdd, results=List(CU.temp(stage(9), tr248)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr248), CU.temp(stage(9), tr246)), op=FixSub, results=List(CU.temp(stage(10), tr249)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr245), Const("4i")), op=FixMul, results=List(CU.temp(stage(11), tr250)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr251)), op=Bypass, results=List(CU.scalarOut(stage(12), x1739_b1872_x1756_b1881_scalar)))
      Stage(stage(13), operands=List(CU.temp(stage(12), tr252)), op=Bypass, results=List(CU.scalarOut(stage(13), x1739_b1873_x1756_b1882_scalar)))
      Stage(stage(14), operands=List(CU.temp(stage(13), tr253)), op=Bypass, results=List(CU.scalarOut(stage(14), x1739_b1874_x1756_b1883_scalar)))
      Stage(stage(15), operands=List(CU.temp(stage(14), tr245)), op=Bypass, results=List(CU.scalarOut(stage(15), x1740_b1875_x1759_b1884_scalar)))
      Stage(stage(16), operands=List(CU.temp(stage(15), tr246)), op=Bypass, results=List(CU.scalarOut(stage(16), x1740_b1876_x1759_b1885_scalar)))
      Stage(stage(17), operands=List(CU.temp(stage(16), tr247)), op=Bypass, results=List(CU.scalarOut(stage(17), x1740_b1877_x1759_b1886_scalar)))
    }
    val x1761 = Fringe(name="x1761",parent=x1790,dram=x1661_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1739_b1874_x1761 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1739_b1874_x1756_b1883_scalar).rdPort(None)
      val x1739_b1873_x1761 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1739_b1873_x1756_b1882_scalar).rdPort(None)
      val x1739_b1872_x1761 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1739_b1872_x1756_b1881_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1789 = Sequential(name="x1789",parent=x1790) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1772 = UnitPipeline(name="x1772",parent=x1789) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr254 = CU.temp
      val tr255 = CU.temp
      val tr256 = CU.temp
      val x1740_b1877_x1765_b1880 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x1740_b1877_x1759_b1886_scalar).rdPort(None)
      val x1740_b1876_x1765_b1879 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x1740_b1876_x1759_b1885_scalar).rdPort(None)
      val x1740_b1875_x1765_b1878 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x1740_b1875_x1759_b1884_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x1740_b1876_x1765_b1879.load), op=Bypass, results=List(CU.scalarOut(stage(1), x1762_x1767_scalar)))
      Stage(stage(2), operands=List(x1740_b1877_x1765_b1880.load), op=Bypass, results=List(CU.scalarOut(stage(2), x1763_x1769_scalar)))
      Stage(stage(3), operands=List(x1740_b1875_x1765_b1878.load), op=Bypass, results=List(CU.scalarOut(stage(3), x1764_x1771_scalar)))
    }
    val x1788 = Pipeline(name="x1788",parent=x1789) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, x1764_x1773.load, Const("1i").out) // Counter
      val x1775 = CounterChain(name = "x1775", ctr5)
      val x1764_x1773 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1764_x1771_scalar).rdPort(None)
      val x1763_x1777 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1763_x1769_scalar).rdPort(None)
      val x1762_x1776 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1762_x1767_scalar).rdPort(None)
      val x1741_x1778 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1741_x1778.load), op=Bypass, results=List(CU.vecOut(stage(1), x1674_x1787_vector)))
    }
    val x1791_dsp0 = MemoryPipeline(name="x1791_dsp0",parent="x1818") { implicit CU => 
      val stage0 = CU.emptyStage
      val x1722 = CounterChain.copy("x1818", "x1722")
      val x1794 = CounterChain.copy("x1802", "x1794")
      val x1805 = CounterChain.copy("x1812", "x1805")
      val x1791_x1806 = SRAM(size = 8000, banking = Strided(1)).wtPort(x1791_x1801_vector).rdPort(x1791_x1806_x1669_vector)
      var stage: List[Stage] = Nil
    }
    val x1802 = Pipeline(name="x1802",parent=x1818) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr257 = CU.temp
      val tr258 = CU.temp
      val tr259 = CU.temp
      val tr260 = CU.temp
      val x1722 = CounterChain.copy("x1818", "x1722")
      val ctr6 = (Const("0i").out, x1723_x1792.load, Const("1i").out) // Counter
      val x1794 = CounterChain(name = "x1794", ctr6)
      val x1725_x1795 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1725_x1734_scalar).rdPort(None)
      val x1675_x1796 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1675_x1816_scalar).rdPort(None)
      val x1723_x1792 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1723_x1732_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1722(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr257)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr257), Const("0i"), CU.load(stage(1), x1725_x1795)), op=Mux, results=List(CU.temp(stage(2), tr258)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x1794(0)), CU.load(stage(2), x1675_x1796)), op=FixAdd, results=List(CU.temp(stage(3), tr259)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr259), CU.temp(stage(3), tr258)), op=FixAdd, results=List(CU.vecOut(stage(4), x1791_x1801_vector), CU.temp(stage(4), tr260)))
    }
    val x1812 = Pipeline(name="x1812",parent=x1818) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, x1723_x1803.load, Const("1i").out) // Counter
      val x1805 = CounterChain(name = "x1805", ctr7)
      val x1723_x1803 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1723_x1732_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1674_x1807_x1812_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1669_x1811_vector)))
    }
    val x1817 = UnitPipeline(name="x1817",parent=x1818) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar2 = CU.accum(init = Const("0i"))
      val x1723_x1814 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1723_x1732_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1723_x1814.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr263) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr263), op=Bypass, results=List(CU.scalarOut(stage(2), x1675_x1816_scalar)))
    }
    val x1826 = Pipeline(name="x1826",parent=x1856) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, x1675_x1819.load, Const("1i").out) // Counter
      val x1821 = CounterChain(name = "x1821", ctr8)
      val x1675_x1819 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1675_x1816_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1669_x1822_x1826_vector)), op=Bypass, results=List(CU.vecOut(stage(1), x1673_x1825_vector)))
    }
    val x1831 = Pipeline(name="x1831",parent=x1856) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr264 = CU.temp
      val x1719 = CounterChain.copy("x1856", "x1719")
      val ctr9 = (Const("0i").out, x1675_x1827.load, Const("1i").out) // Counter
      val x1829 = CounterChain(name = "x1829", ctr9)
      val x1675_x1827 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1675_x1816_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1719(0)), Const("1i")), op=FixAdd, results=List(CU.vecOut(stage(1), x1672_x1830_vector), CU.temp(stage(1), tr264)))
    }
    val x1852 = Sequential(name="x1852",parent=x1856) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1846 = Pipeline(name="x1846",parent=x1852) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr266 = CU.temp
      val tr267 = CU.temp
      val tr268 = CU.temp
      val tr269 = CU.temp
      val ctr10 = (Const("0i").out, Const("8000i").out, Const("1i").out) // Counter
      val x1835 = CounterChain(name = "x1835", ctr10)
      val x1837 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1837_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1673_x1836_x1846_vector), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr266)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr266), CU.load(stage(1), x1837)), op=FixAdd, results=List(CU.temp(stage(2), tr267)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr268)), op=Bypass, results=List(CU.vecOut(stage(3), x1832_b1887_x1845_b1889_vector)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr269)), op=Bypass, results=List(CU.vecOut(stage(4), x1832_b1888_x1845_b1890_vector)))
    }
    val x1847 = Fringe(name="x1847",parent=x1852,dram=x1664_oc, mode=Scatter) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1832_b1888_x1847 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x1832_b1888_x1845_b1890_vector).rdPort(None)
      val x1832_b1887_x1847 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x1832_b1887_x1845_b1889_vector).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1851 = Pipeline(name="x1851",parent=x1852) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr11 = (Const("0i").out, Const("8000i").out, Const("1i").out) // Counter
      val x1849 = CounterChain(name = "x1849", ctr11)
      var stage: List[Stage] = Nil
    }
    val x1855 = UnitPipeline(name="x1855",parent=x1856) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1675_x1853 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1675_x1816_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1675_x1853.load), op=Bypass, results=List(CU.scalarOut(stage(1), x1676_x1854_scalar)))
    }
    
  }
}
