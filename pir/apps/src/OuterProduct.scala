import pir._
import pir.node._
import arch._
import pirc.enums._

object OuterProduct extends PIRApp {
  def main(top:Top) = {
    val x1790_x1847_ra_v = Vector("x1790_x1847_ra")
    val x1857_b1925_x1874_b1927_s = Scalar("x1857_b1925_x1874_b1927")
    val x1819_x1830_data_v = Vector("x1819_x1830_data")
    val out_da = DRAMAddress("out", "out")
    val vec2_oc = OffChip("vec2")
    val x1790_x1837_wa_v = Vector("x1790_x1837_wa")
    val x1791_x1850_data_v = Vector("x1791_x1850_data")
    val x1794_b1911_x1804_b1913_s = Scalar("x1794_b1911_x1804_b1913")
    val x1795_x1806_data_v = Vector("x1795_x1806_data")
    val vec1_da = DRAMAddress("vec1", "vec1")
    val x1791_x1850_wa_v = Vector("x1791_x1850_wa")
    val x1818_b1916_x1828_b1918_s = Scalar("x1818_b1916_x1828_b1918")
    val sizeA_argin = ArgIn("sizeA").bound(38400)
    val x1789_x1813_wa_v = Vector("x1789_x1813_wa")
    val vec1_oc = OffChip("vec1")
    val sizeB_argin = ArgIn("sizeB").bound(38400)
    val x1791_x1791_dsp0_bank0_data_v = Vector("x1791_x1791_dsp0_bank0_data")
    val x1818_b1915_x1828_b1917_s = Scalar("x1818_b1915_x1828_b1917")
    val x1789_x1789_dsp0_bank0_data_s = Scalar("x1789_x1789_dsp0_bank0_data")
    val x1857_b1924_x1874_b1926_s = Scalar("x1857_b1924_x1874_b1926")
    val x1791_x1881_ra_v = Vector("x1791_x1881_ra")
    val x1794_b1910_x1804_b1912_s = Scalar("x1794_b1910_x1804_b1912")
    val x1789_x1846_ra_s = Scalar("x1789_x1846_ra")
    val out_oc = OffChip("out")
    val vec2_da = DRAMAddress("vec2", "vec2")
    val x1790_x1790_dsp0_bank0_data_v = Vector("x1790_x1790_dsp0_bank0_data")
    val x1892 = MetaPipeline(name="x1892",parent="top") { implicit CU => 
      val x1772 = ScalarBuffer(name="x1772").buffering(1).wtPort(sizeB_argin)
      val x1771 = ScalarBuffer(name="x1771").buffering(1).wtPort(sizeA_argin)
      val ctr1 = Counter(min=Const(0), max=x1771.readPort, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x1772.readPort, step=Const(16), par=1) // Counter
      val x1788 = CounterChain(name = "x1788", ctr1, ctr2).iter(5760000)
    }
    val x1789_dsp0_bank0 = MemoryPipeline(name="x1789_dsp0_bank0",parent="x1892") { implicit CU => 
      val x1813 = VectorFIFO(size=1,name="x1813").wtPort(x1795_x1806_data_v)
      val b1920 = ScalarFIFO(size=1,name="b1920").wtPort(x1789_x1846_ra_s)
      val b1914 = VectorFIFO(size=1,name="b1914").wtPort(x1789_x1813_wa_v)
      val x1789 = SRAM(size=1,name="x1789",banking = Strided(1,16)).buffering(2).wtPort(x1813.readPort).rdPort(x1789_x1789_dsp0_bank0_data_s).rdAddr(b1920.readPort).wtAddr(b1914.readPort).consumer("x1846_0", "x1851_0").producer("x1814_0", "x1815")
    }
    val x1790_dsp0_bank0 = MemoryPipeline(name="x1790_dsp0_bank0",parent="x1892") { implicit CU => 
      val b1919 = VectorFIFO(size=1,name="b1919").wtPort(x1790_x1837_wa_v)
      val x1837 = VectorFIFO(size=1,name="x1837").wtPort(x1819_x1830_data_v)
      val b1921 = VectorFIFO(size=1,name="b1921").wtPort(x1790_x1847_ra_v)
      val x1790 = SRAM(size=1,name="x1790",banking = Strided(1,16)).buffering(2).wtPort(x1837.readPort).rdPort(x1790_x1790_dsp0_bank0_data_v).rdAddr(b1921.readPort).wtAddr(b1919.readPort).consumer("x1847_0", "x1851_0").producer("x1838_0", "x1839")
    }
    val x1791_dsp0_bank0 = MemoryPipeline(name="x1791_dsp0_bank0",parent="x1892") { implicit CU => 
      val x1850 = VectorFIFO(size=1,name="x1850").wtPort(x1791_x1850_data_v)
      val b1929 = VectorFIFO(size=1,name="b1929").wtPort(x1791_x1881_ra_v)
      val b1923 = VectorFIFO(size=1,name="b1923").wtPort(x1791_x1850_wa_v)
      val x1791 = SRAM(size=16,name="x1791",banking = Strided(1,16)).buffering(3).wtPort(x1850.readPort).rdPort(x1791_x1791_dsp0_bank0_data_v).rdAddr(b1929.readPort).wtAddr(b1923.readPort).consumer("x1881_0", "x1891").producer("x1851_0", "x1851_0")
    }
    val x1815 = StreamController(name="x1815",parent="x1892") { implicit CU => 
      val x1815_unit = CounterChain(name = "x1815_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1805_0 = Pipeline(name="x1805_0",parent="x1815") { implicit CU => 
      val x1797 = CU.temp(None)
      val x1799 = ScalarBuffer(name="x1799").buffering(1).wtPort(vec1_da)
      val x1788 = CounterChain.copy("x1892", "x1788").iterIdx(0, 0)
      val x1805_unit = CounterChain(name = "x1805_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1788(0)), Const(2)), op=FixSla, results=List(x1797))
      Stage(operands=List(x1797, CU.load(x1799)), op=FixAdd, results=List(CU.scalarOut(x1794_b1910_x1804_b1912_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1794_b1911_x1804_b1913_s)))
    }
    val x1806 = MemoryController(name="x1806",parent="x1815",offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x1794_b1910 = ScalarFIFO(size=1,name="offset").wtPort(x1794_b1910_x1804_b1912_s)
      val x1794_b1911 = ScalarFIFO(size=1,name="size").wtPort(x1794_b1911_x1804_b1913_s)
      CU.newOut("data", x1795_x1806_data_v)
    }
    val x1814_0 = Pipeline(name="x1814_0",parent="x1815") { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1808 = CounterChain(name = "x1808", ctr3).iter(1)
      Stage(operands=List(CU.ctr(x1808(0))), op=Bypass, results=List(CU.vecOut(x1789_x1813_wa_v)))
    }
    val x1839 = StreamController(name="x1839",parent="x1892") { implicit CU => 
      val x1839_unit = CounterChain(name = "x1839_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1829_0 = Pipeline(name="x1829_0",parent="x1839") { implicit CU => 
      val x1821 = CU.temp(None)
      val x1823 = ScalarBuffer(name="x1823").buffering(1).wtPort(vec2_da)
      val x1788 = CounterChain.copy("x1892", "x1788").iterIdx(1, 0)
      val x1829_unit = CounterChain(name = "x1829_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1788(1)), Const(2)), op=FixSla, results=List(x1821))
      Stage(operands=List(x1821, CU.load(x1823)), op=FixAdd, results=List(CU.scalarOut(x1818_b1915_x1828_b1917_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1818_b1916_x1828_b1918_s)))
    }
    val x1830 = MemoryController(name="x1830",parent="x1839",offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x1818_b1916 = ScalarFIFO(size=1,name="size").wtPort(x1818_b1916_x1828_b1918_s)
      val x1818_b1915 = ScalarFIFO(size=1,name="offset").wtPort(x1818_b1915_x1828_b1917_s)
      CU.newOut("data", x1819_x1830_data_v)
    }
    val x1838_0 = Pipeline(name="x1838_0",parent="x1839") { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1832 = CounterChain(name = "x1832", ctr4).iter(1)
      Stage(operands=List(CU.ctr(x1832(0))), op=Bypass, results=List(CU.vecOut(x1790_x1837_wa_v)))
    }
    val x1851_0 = Pipeline(name="x1851_0",parent="x1892") { implicit CU => 
      val b1922 = CU.temp(None)
      val x1847 = VectorFIFO(size=1,name="x1847").wtPort(x1790_x1790_dsp0_bank0_data_v)
      val x1846 = ScalarFIFO(size=1,name="x1846").wtPort(x1789_x1789_dsp0_bank0_data_s)
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1842 = CounterChain(name = "x1842", ctr5, ctr6).iter(16)
      Stage(operands=List(CU.load(x1846), CU.load(x1847)), op=FixMul, results=List(CU.vecOut(x1791_x1850_data_v)))
      Stage(operands=List(CU.ctr(x1842(0)), Const(16)), op=FixMul, results=List(b1922))
      Stage(operands=List(b1922, CU.ctr(x1842(1))), op=FixAdd, results=List(CU.vecOut(x1791_x1850_wa_v)))
    }
    val x1846_0 = Pipeline(name="x1846_0",parent="x1892") { implicit CU => 
      val x1842 = CounterChain.copy("x1851_0", "x1842").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1842(0))), op=Bypass, results=List(CU.scalarOut(x1789_x1846_ra_s)))
    }
    val x1847_0 = Pipeline(name="x1847_0",parent="x1892") { implicit CU => 
      val x1842 = CounterChain.copy("x1851_0", "x1842").iterIdx(1, 0)
      Stage(operands=List(CU.ctr(x1842(1))), op=Bypass, results=List(CU.vecOut(x1790_x1847_ra_v)))
    }
    val x1891 = StreamController(name="x1891",parent="x1892") { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1856 = CounterChain(name = "x1856", ctr7).iter(16)
    }
    val x1875_0 = Pipeline(name="x1875_0",parent="x1891") { implicit CU => 
      val x1865 = CU.temp(None)
      val x1860 = CU.temp(None)
      val x1863 = CU.temp(None)
      val x1866 = CU.temp(None)
      val x1868 = ScalarBuffer(name="x1868").buffering(1).wtPort(out_da)
      val x1772 = ScalarBuffer(name="x1772").buffering(1).wtPort(sizeB_argin)
      val x1788 = CounterChain.copy("x1892", "x1788").iterIdx(0, 0)
      val x1856 = CounterChain.copy("x1891", "x1856").iterIdx(0, 0)
      val x1875_unit = CounterChain(name = "x1875_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1788(0)), CU.ctr(x1856(0))), op=FixAdd, results=List(x1860))
      Stage(operands=List(x1860, CU.load(x1772)), op=FixMul, results=List(x1863))
      Stage(operands=List(x1863, CU.ctr(x1788(1))), op=FixAdd, results=List(x1865))
      Stage(operands=List(x1865, Const(2)), op=FixSla, results=List(x1866))
      Stage(operands=List(x1866, CU.load(x1868)), op=FixAdd, results=List(CU.scalarOut(x1857_b1924_x1874_b1926_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1857_b1925_x1874_b1927_s)))
    }
    val x1885 = Pipeline(name="x1885",parent="x1891") { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1877 = CounterChain(name = "x1877", ctr8).iter(1)
    }
    val x1881_0 = Pipeline(name="x1881_0",parent="x1891") { implicit CU => 
      val b1928 = CU.temp(None)
      val x1856 = CounterChain.copy("x1891", "x1856").iterIdx(0, 0)
      val x1877 = CounterChain.copy("x1885", "x1877").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x1856(0)), Const(16)), op=FixMul, results=List(b1928))
      Stage(operands=List(b1928, CU.ctr(x1877(0))), op=FixAdd, results=List(CU.vecOut(x1791_x1881_ra_v)))
    }
    val x1886 = MemoryController(name="x1886",parent="x1891",offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x1857_b1925 = ScalarFIFO(size=1,name="size").wtPort(x1857_b1925_x1874_b1927_s)
      val x1857_b1924 = ScalarFIFO(size=1,name="offset").wtPort(x1857_b1924_x1874_b1926_s)
      val x1858 = VectorFIFO(size=1,name="data").wtPort(x1791_x1791_dsp0_bank0_data_v)
    }
    
  }
}
