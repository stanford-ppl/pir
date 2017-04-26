import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object MatMult_inner extends PIRApp {
  override val arch = SN_4x4

  def main(args: String*)(top:Top) = {
    val x1768_argin = ArgIn("x1768")
    val x1799_x1812_data_v = Vector("x1799_x1812_data")
    val x1885_b1971_x1898_b1973_s = Scalar("x1885_b1971_x1898_b1973")
    val x1776_oc = OffChip("x1776")
    val x1794_x1864_x1873_v = Vector("x1794_x1864_x1873")
    val x1795_x1865_x1873_v = Vector("x1795_x1865_x1873")
    val x1826_b1957_x1838_b1959_s = Scalar("x1826_b1957_x1838_b1959")
    val x1790_x1874_x1880_v = Vector("x1790_x1874_x1880")
    val x1770_argin = ArgIn("x1770")
    val x1798_b1951_x1810_b1953_s = Scalar("x1798_b1951_x1810_b1953")
    val x1790_x1879_v = Vector("x1790_x1879")
    val x1782_oc = OffChip("x1782")
    val x1790_x1905_x1909_v = Vector("x1790_x1905_x1909")
    val x1828_argin = ArgIn("x1828")
    val x1798_b1952_x1810_b1954_s = Scalar("x1798_b1952_x1810_b1954")
    val x1888_argin = ArgIn("x1888")
    val x1827_x1840_data_v = Vector("x1827_x1840_data")
    val x1885_b1972_x1898_b1974_s = Scalar("x1885_b1972_x1898_b1974")
    val x1769_argin = ArgIn("x1769")
    val x1856_x1871_s = Scalar("x1856_x1871")
    val x1779_oc = OffChip("x1779")
    val x1826_b1958_x1838_b1960_s = Scalar("x1826_b1958_x1838_b1960")
    val x1800_argin = ArgIn("x1800")
    val x1922 = Sequential(name="x1922",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1922_unit = CounterChain(name = "x1922_unit", ctr1)
    }
    val x1921 = MetaPipeline(name="x1921",parent=x1922) { implicit CU => 
      val x1769_x1785 =  ScalarBuffer().wtPort(x1769_argin)
      val x1768_x1787 =  ScalarBuffer().wtPort(x1768_argin)
      val ctr2 = Counter(min=Const(0), max=x1768_x1787.load, step=Const(4), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x1769_x1785.load, step=Const(16), par=1) // Counter
      val x1789 = CounterChain(name = "x1789", ctr2, ctr3)
    }
    val x1790_dsp0 = MemoryPipeline(name="x1790_dsp0",parent="x1921") { implicit CU => 
      val b1969 = CU.temp
      val b1975 = CU.temp
      val x1879_x1879 =  VectorFIFO(size=1).wtPort(x1790_x1879_v)
      val x1855 = CounterChain.copy("x1881", "x1855")
      val x1884 = CounterChain.copy("x1920", "x1884")
      val x1901 = CounterChain.copy("x1909", "x1901")
      val x1790_x1905 =  SRAM(size=64,banking = Strided(1)).wtPort(x1879_x1879.readPort).rdPort(x1790_x1905_x1909_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x1855(0)), Const(16)), op=FixMul, results=List(b1969))
      WAStage(operands=List(b1969, CU.ctr(x1855(1))), op=FixAdd, results=List(x1790_x1905.writeAddr))
      RAStage(operands=List(CU.ctr(x1884(0)), Const(16)), op=FixMul, results=List(b1975))
      RAStage(operands=List(b1975, CU.ctr(x1901(0))), op=FixAdd, results=List(x1790_x1905.readAddr))
    }
    val x1790_dsp1 = MemoryPipeline(name="x1790_dsp1",parent="x1921") { implicit CU => 
      val b1969 = CU.temp
      val b1967 = CU.temp
      val x1879_x1879 =  VectorFIFO(size=1).wtPort(x1790_x1879_v)
      val x1855 = CounterChain.copy("x1881", "x1855")
      val x1790_x1874 =  SRAM(size=64,banking = NoBanking()).wtPort(x1879_x1879.readPort).rdPort(x1790_x1874_x1880_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x1855(0)), Const(16)), op=FixMul, results=List(b1969))
      WAStage(operands=List(b1969, CU.ctr(x1855(1))), op=FixAdd, results=List(x1790_x1874.writeAddr))
      RAStage(operands=List(CU.ctr(x1855(0)), Const(16)), op=FixMul, results=List(b1967))
      RAStage(operands=List(b1967, CU.ctr(x1855(1))), op=FixAdd, results=List(x1790_x1874.readAddr))
    }
    val x1882 = MetaPipeline(name="x1882",parent=x1921) { implicit CU => 
      val x1770_x1791 =  ScalarBuffer().wtPort(x1770_argin)
      val ctr4 = Counter(min=Const(0), max=x1770_x1791.load, step=Const(16), par=1) // Counter
      val x1793 = CounterChain(name = "x1793", ctr4)
    }
    val x1794_dsp0 = MemoryPipeline(name="x1794_dsp0",parent="x1882") { implicit CU => 
      val b1955 = CU.temp
      val b1963 = CU.temp
      val x1821_x1821 =  VectorFIFO(size=1).wtPort(x1799_x1812_data_v)
      val x1858 = CounterChain.copy("x1873", "x1858")
      val x1814 = CounterChain.copy("x1822", "x1814")
      val x1797 = CounterChain.copy("x1823", "x1797")
      val x1855 = CounterChain.copy("x1881", "x1855")
      val x1794_x1864 =  SRAM(size=64,banking = Strided(1)).wtPort(x1821_x1821.readPort).rdPort(x1794_x1864_x1873_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x1797(0)), Const(16)), op=FixMul, results=List(b1955))
      WAStage(operands=List(b1955, CU.ctr(x1814(0))), op=FixAdd, results=List(x1794_x1864.writeAddr))
      RAStage(operands=List(CU.ctr(x1855(0)), Const(16)), op=FixMul, results=List(b1963))
      RAStage(operands=List(b1963, CU.ctr(x1858(0))), op=FixAdd, results=List(x1794_x1864.readAddr))
    }
    val x1795_dsp0 = MemoryPipeline(name="x1795_dsp0",parent="x1882") { implicit CU => 
      val b1961 = CU.temp
      val b1965 = CU.temp
      val x1849_x1849 =  VectorFIFO(size=1).wtPort(x1827_x1840_data_v)
      val x1858 = CounterChain.copy("x1873", "x1858")
      val x1825 = CounterChain.copy("x1851", "x1825")
      val x1855 = CounterChain.copy("x1881", "x1855")
      val x1842 = CounterChain.copy("x1850", "x1842")
      val x1795_x1865 =  SRAM(size=256,banking = Strided(1)).wtPort(x1849_x1849.readPort).rdPort(x1795_x1865_x1873_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x1825(0)), Const(16)), op=FixMul, results=List(b1961))
      WAStage(operands=List(b1961, CU.ctr(x1842(0))), op=FixAdd, results=List(x1795_x1865.writeAddr))
      RAStage(operands=List(CU.ctr(x1858(0)), Const(16)), op=FixMul, results=List(b1965))
      RAStage(operands=List(b1965, CU.ctr(x1855(1))), op=FixAdd, results=List(x1795_x1865.readAddr))
    }
    val x1823 = StreamController(name="x1823",parent=x1882) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val x1797 = CounterChain(name = "x1797", ctr5)
    }
    val x1811 = Pipeline(name="x1811",parent=x1823) { implicit CU => 
      val x1804 = CU.temp
      val x1803 = CU.temp
      val x1802 = CU.temp
      val x1805 = CU.temp
      val x1770_x1801 =  ScalarBuffer().wtPort(x1770_argin)
      val x1800 =  ScalarBuffer().wtPort(x1800_argin)
      val x1789 = CounterChain.copy("x1921", "x1789")
      val x1797 = CounterChain.copy("x1823", "x1797")
      val x1793 = CounterChain.copy("x1882", "x1793")
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1811_unit = CounterChain(name = "x1811_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1789(0)), CU.ctr(x1797(0))), op=FixAdd, results=List(x1802))
      Stage(operands=List(x1802, CU.load(x1770_x1801)), op=FixMul, results=List(x1803))
      Stage(operands=List(x1803, CU.ctr(x1793(0))), op=FixAdd, results=List(x1804))
      Stage(operands=List(x1804, Const(4)), op=FixMul, results=List(x1805))
      Stage(operands=List(x1805, CU.load(x1800)), op=FixAdd, results=List(CU.scalarOut(x1798_b1951_x1810_b1953_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1798_b1952_x1810_b1954_s)))
    }
    val x1812 = MemoryController(name="x1812",parent=x1823,offchip=x1776_oc, mctpe=TileLoad) { implicit CU => 
      val x1798_b1952_x1812 =  ScalarFIFO(name="size",size=1).wtPort(x1798_b1952_x1810_b1954_s)
      val x1798_b1951_x1812 =  ScalarFIFO(name="offset",size=1).wtPort(x1798_b1951_x1810_b1953_s)
      CU.newVout("data", x1799_x1812_data_v)
    }
    val x1822 = Pipeline(name="x1822",parent=x1823) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1814 = CounterChain(name = "x1814", ctr7)
      var stage: List[Stage] = Nil
    }
    val x1851 = StreamController(name="x1851",parent=x1882) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1825 = CounterChain(name = "x1825", ctr8)
    }
    val x1839 = Pipeline(name="x1839",parent=x1851) { implicit CU => 
      val x1833 = CU.temp
      val x1832 = CU.temp
      val x1830 = CU.temp
      val x1831 = CU.temp
      val x1769_x1829 =  ScalarBuffer().wtPort(x1769_argin)
      val x1828 =  ScalarBuffer().wtPort(x1828_argin)
      val x1793 = CounterChain.copy("x1882", "x1793")
      val x1825 = CounterChain.copy("x1851", "x1825")
      val x1789 = CounterChain.copy("x1921", "x1789")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1839_unit = CounterChain(name = "x1839_unit", ctr9)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1793(0)), CU.ctr(x1825(0))), op=FixAdd, results=List(x1830))
      Stage(operands=List(x1830, CU.load(x1769_x1829)), op=FixMul, results=List(x1831))
      Stage(operands=List(x1831, CU.ctr(x1789(1))), op=FixAdd, results=List(x1832))
      Stage(operands=List(x1832, Const(4)), op=FixMul, results=List(x1833))
      Stage(operands=List(x1833, CU.load(x1828)), op=FixAdd, results=List(CU.scalarOut(x1826_b1957_x1838_b1959_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1826_b1958_x1838_b1960_s)))
    }
    val x1840 = MemoryController(name="x1840",parent=x1851,offchip=x1779_oc, mctpe=TileLoad) { implicit CU => 
      val x1826_b1958_x1840 =  ScalarFIFO(name="size",size=1).wtPort(x1826_b1958_x1838_b1960_s)
      val x1826_b1957_x1840 =  ScalarFIFO(name="offset",size=1).wtPort(x1826_b1957_x1838_b1959_s)
      CU.newVout("data", x1827_x1840_data_v)
    }
    val x1850 = Pipeline(name="x1850",parent=x1851) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1842 = CounterChain(name = "x1842", ctr10)
      var stage: List[Stage] = Nil
    }
    val x1881 = MetaPipeline(name="x1881",parent=x1882) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1855 = CounterChain(name = "x1855", ctr11, ctr12)
    }
    val x1873 = Pipeline(name="x1873",parent=x1881) { implicit CU => 
      val x1795_x1865 =  VectorFIFO(size=1).wtPort(x1795_x1865_x1873_v)
      val x1794_x1864 =  VectorFIFO(size=1).wtPort(x1794_x1864_x1873_v)
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1858 = CounterChain(name = "x1858", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1794_x1864), CU.load(x1795_x1865)), op=FixMul, results=List(CU.reduce))
      val (_, rr311) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr311), op=Bypass, results=List(CU.scalarOut(x1856_x1871_s)))
    }
    val x1880 = Pipeline(name="x1880",parent=x1881) { implicit CU => 
      val x1877 = CU.temp
      val x1876 = CU.temp
      val x1790_x1874 =  VectorFIFO(size=1).wtPort(x1790_x1874_x1880_v)
      val x1856_x1875 =  ScalarBuffer().wtPort(x1856_x1871_s)
      val x1793 = CounterChain.copy("x1882", "x1793")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1880_unit = CounterChain(name = "x1880_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1793(0)), Const(0)), op=FixEql, results=List(x1876))
      Stage(operands=List(x1876, Const(0), CU.load(x1790_x1874)), op=Mux, results=List(x1877))
      Stage(operands=List(x1877, CU.load(x1856_x1875)), op=FixAdd, results=List(CU.vecOut(x1790_x1879_v)))
    }
    val x1920 = StreamController(name="x1920",parent=x1921) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val x1884 = CounterChain(name = "x1884", ctr15)
    }
    val x1910 = Sequential(name="x1910",parent=x1920) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1910_unit = CounterChain(name = "x1910_unit", ctr16)
    }
    val x1899 = Pipeline(name="x1899",parent=x1910) { implicit CU => 
      val x1893 = CU.temp
      val x1891 = CU.temp
      val x1890 = CU.temp
      val x1892 = CU.temp
      val x1769_x1889 =  ScalarBuffer().wtPort(x1769_argin)
      val x1888 =  ScalarBuffer().wtPort(x1888_argin)
      val x1789 = CounterChain.copy("x1921", "x1789")
      val x1884 = CounterChain.copy("x1920", "x1884")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1899_unit = CounterChain(name = "x1899_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1789(0)), CU.ctr(x1884(0))), op=FixAdd, results=List(x1890))
      Stage(operands=List(x1890, CU.load(x1769_x1889)), op=FixMul, results=List(x1891))
      Stage(operands=List(x1891, CU.ctr(x1789(1))), op=FixAdd, results=List(x1892))
      Stage(operands=List(x1892, Const(4)), op=FixMul, results=List(x1893))
      Stage(operands=List(x1893, CU.load(x1888)), op=FixAdd, results=List(CU.scalarOut(x1885_b1971_x1898_b1973_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1885_b1972_x1898_b1974_s)))
    }
    val x1909 = Pipeline(name="x1909",parent=x1910) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1901 = CounterChain(name = "x1901", ctr18)
      var stage: List[Stage] = Nil
    }
    val x1911 = MemoryController(name="x1911",parent=x1920,offchip=x1782_oc, mctpe=TileStore) { implicit CU => 
      val x1886_x1911 =  VectorFIFO(name="data",size=1).wtPort(x1790_x1905_x1909_v)
      val x1885_b1972_x1911 =  ScalarFIFO(name="size",size=1).wtPort(x1885_b1972_x1898_b1974_s)
      val x1885_b1971_x1911 =  ScalarFIFO(name="offset",size=1).wtPort(x1885_b1971_x1898_b1973_s)
    }
    
  }
}
