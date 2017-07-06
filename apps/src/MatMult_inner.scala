import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object MatMult_inner extends PIRApp {
  def main(top:Top) = {
    val x1818_argin = ArgIn("x1818")
    val x1842_x1918_x1931_v = Vector("x1842_x1918_x1931")
    val x1846_b2015_x1861_b2017_s = Scalar("x1846_b2015_x1861_b2017")
    val x1910_x1929_s = Scalar("x1910_x1929")
    val x1817_argin = ArgIn("x1817")
    val x1950_argin = ArgIn("x1950")
    val x1827_oc = OffChip("x1827")
    val x1878_x1894_data_v = Vector("x1878_x1894_data")
    val x1947_b2036_x1962_b2038_s = Scalar("x1947_b2036_x1962_b2038")
    val x1838_x1936_x1942_s = Scalar("x1838_x1936_x1942")
    val x1848_argin = ArgIn("x1848")
    val x1816_argin = ArgIn("x1816")
    val x1879_argin = ArgIn("x1879")
    val x1843_x1919_x1931_v = Vector("x1843_x1919_x1931")
    val x1838_x1941_s = Scalar("x1838_x1941")
    val x1846_b2016_x1861_b2018_s = Scalar("x1846_b2016_x1861_b2018")
    val x1847_x1863_data_v = Vector("x1847_x1863_data")
    val x1824_oc = OffChip("x1824")
    val x1877_b2022_x1892_b2024_s = Scalar("x1877_b2022_x1892_b2024")
    val x1830_oc = OffChip("x1830")
    val x1947_b2035_x1962_b2037_s = Scalar("x1947_b2035_x1962_b2037")
    val x1838_x1969_x1973_v = Vector("x1838_x1969_x1973")
    val x1877_b2021_x1892_b2023_s = Scalar("x1877_b2021_x1892_b2023")
    val x1986 = Sequential(name="x1986",parent=top) { implicit CU => 
      val x1986_unit = CounterChain(name = "x1986_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1985 = MetaPipeline(name="x1985",parent=x1986) { implicit CU => 
      val x1817_x1833 = ScalarBuffer().wtPort(x1817_argin)
      val x1816_x1835 = ScalarBuffer().wtPort(x1816_argin)
      val ctr1 = Counter(min=Const(0), max=x1816_x1835.load, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x1817_x1833.load, step=Const(48), par=1) // Counter
      val x1837 = CounterChain(name = "x1837", ctr1, ctr2).iter(1)
    }
    val x1838_dsp0 = MemoryPipeline(name="x1838_dsp0",parent="x1985") { implicit CU => 
      val b2033 = CU.temp
      val b2039 = CU.temp
      val x1941_x1941 = ScalarFIFO(size=1).wtPort(x1838_x1941_s)
      val x1909 = CounterChain.copy("x1943", "x1909")
      val x1946 = CounterChain.copy("x1984", "x1946")
      val x1965 = CounterChain.copy("x1973", "x1965")
      val x1838_x1969 = SRAM(size=768,banking = Strided(1)).wtPort(x1941_x1941.readPort).rdPort(x1838_x1969_x1973_v)
      WAStage(operands=List(CU.ctr(x1909(0)), Const(48)), op=FixMul, results=List(b2033))
      WAStage(operands=List(b2033, CU.ctr(x1909(1))), op=FixAdd, results=List(x1838_x1969.writeAddr))
      RAStage(operands=List(CU.ctr(x1946(0)), Const(48)), op=FixMul, results=List(b2039))
      RAStage(operands=List(b2039, CU.ctr(x1965(0))), op=FixAdd, results=List(x1838_x1969.readAddr))
    }
    val x1838_dsp1 = MemoryPipeline(name="x1838_dsp1",parent="x1985") { implicit CU => 
      val b2031 = CU.temp
      val b2033 = CU.temp
      val x1941_x1941 = ScalarFIFO(size=1).wtPort(x1838_x1941_s)
      val x1909 = CounterChain.copy("x1943", "x1909")
      val x1838_x1936 = SRAM(size=768,banking = NoBanking()).wtPort(x1941_x1941.readPort).rdPort(x1838_x1936_x1942_s)
      WAStage(operands=List(CU.ctr(x1909(0)), Const(48)), op=FixMul, results=List(b2033))
      WAStage(operands=List(b2033, CU.ctr(x1909(1))), op=FixAdd, results=List(x1838_x1936.writeAddr))
      RAStage(operands=List(CU.ctr(x1909(0)), Const(48)), op=FixMul, results=List(b2031))
      RAStage(operands=List(b2031, CU.ctr(x1909(1))), op=FixAdd, results=List(x1838_x1936.readAddr))
    }
    val x1944 = MetaPipeline(name="x1944",parent=x1985) { implicit CU => 
      val x1818_x1839 = ScalarBuffer().wtPort(x1818_argin)
      val ctr3 = Counter(min=Const(0), max=x1818_x1839.load, step=Const(48), par=1) // Counter
      val x1841 = CounterChain(name = "x1841", ctr3).iter(1)
    }
    val x1842_dsp0 = MemoryPipeline(name="x1842_dsp0",parent="x1944") { implicit CU => 
      val b2027 = CU.temp
      val b2019 = CU.temp
      val x1872_x1872 = VectorFIFO(size=1).wtPort(x1847_x1863_data_v)
      val x1912 = CounterChain.copy("x1931_0", "x1912")
      val x1909 = CounterChain.copy("x1943", "x1909")
      val x1865 = CounterChain.copy("x1873", "x1865")
      val x1845 = CounterChain.copy("x1874", "x1845")
      val x1842_x1918 = SRAM(size=768,banking = Strided(1)).wtPort(x1872_x1872.readPort).rdPort(x1842_x1918_x1931_v)
      WAStage(operands=List(CU.ctr(x1845(0)), Const(48)), op=FixMul, results=List(b2019))
      WAStage(operands=List(b2019, CU.ctr(x1865(0))), op=FixAdd, results=List(x1842_x1918.writeAddr))
      RAStage(operands=List(CU.ctr(x1909(0)), Const(48)), op=FixMul, results=List(b2027))
      RAStage(operands=List(b2027, CU.ctr(x1912(0))), op=FixAdd, results=List(x1842_x1918.readAddr))
    }
    val x1843_dsp0 = MemoryPipeline(name="x1843_dsp0",parent="x1944") { implicit CU => 
      val b2029 = CU.temp
      val b2025 = CU.temp
      val x1903_x1903 = VectorFIFO(size=1).wtPort(x1878_x1894_data_v)
      val x1876 = CounterChain.copy("x1905", "x1876")
      val x1912 = CounterChain.copy("x1931_0", "x1912")
      val x1909 = CounterChain.copy("x1943", "x1909")
      val x1896 = CounterChain.copy("x1904", "x1896")
      val x1843_x1919 = SRAM(size=2304,banking = Strided(1)).wtPort(x1903_x1903.readPort).rdPort(x1843_x1919_x1931_v)
      WAStage(operands=List(CU.ctr(x1876(0)), Const(48)), op=FixMul, results=List(b2025))
      WAStage(operands=List(b2025, CU.ctr(x1896(0))), op=FixAdd, results=List(x1843_x1919.writeAddr))
      RAStage(operands=List(CU.ctr(x1912(0)), Const(48)), op=FixMul, results=List(b2029))
      RAStage(operands=List(b2029, CU.ctr(x1909(1))), op=FixAdd, results=List(x1843_x1919.readAddr))
    }
    val x1874 = StreamController(name="x1874",parent=x1944) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1845 = CounterChain(name = "x1845", ctr4).iter(16)
    }
    val x1862_0 = Pipeline(name="x1862_0",parent=x1874) { implicit CU => 
      val x1851 = CU.temp
      val x1853 = CU.temp
      val x1852 = CU.temp
      val x1850 = CU.temp
      val x1818_x1849 = ScalarBuffer().wtPort(x1818_argin)
      val x1848 = ScalarBuffer().wtPort(x1848_argin)
      val x1837 = CounterChain.copy("x1985", "x1837")
      val x1845 = CounterChain.copy("x1874", "x1845")
      val x1841 = CounterChain.copy("x1944", "x1841")
      val x1862_unit = CounterChain(name = "x1862_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1837(0)), CU.ctr(x1845(0))), op=FixAdd, results=List(x1850))
      Stage(operands=List(x1850, CU.load(x1818_x1849)), op=FixMul, results=List(x1851))
      Stage(operands=List(x1851, CU.ctr(x1841(0))), op=FixAdd, results=List(x1852))
      Stage(operands=List(x1852, Const(4)), op=FixMul, results=List(x1853))
      Stage(operands=List(x1853, CU.load(x1848)), op=FixAdd, results=List(CU.scalarOut(x1846_b2015_x1861_b2017_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x1846_b2016_x1861_b2018_s)))
    }
    val x1863 = MemoryController(name="x1863",parent=x1874,offchip=x1824_oc, mctpe=TileLoad) { implicit CU => 
      val x1846_b2015_x1863 = ScalarFIFO(name="offset",size=1).wtPort(x1846_b2015_x1861_b2017_s)
      val x1846_b2016_x1863 = ScalarFIFO(name="size",size=1).wtPort(x1846_b2016_x1861_b2018_s)
      CU.newVout("data", x1847_x1863_data_v)
    }
    val x1873 = Pipeline(name="x1873",parent=x1874) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x1865 = CounterChain(name = "x1865", ctr5).iter(3)
    }
    val x1905 = StreamController(name="x1905",parent=x1944) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x1876 = CounterChain(name = "x1876", ctr6).iter(48)
    }
    val x1893_0 = Pipeline(name="x1893_0",parent=x1905) { implicit CU => 
      val x1883 = CU.temp
      val x1882 = CU.temp
      val x1884 = CU.temp
      val x1881 = CU.temp
      val x1817_x1880 = ScalarBuffer().wtPort(x1817_argin)
      val x1879 = ScalarBuffer().wtPort(x1879_argin)
      val x1841 = CounterChain.copy("x1944", "x1841")
      val x1876 = CounterChain.copy("x1905", "x1876")
      val x1837 = CounterChain.copy("x1985", "x1837")
      val x1893_unit = CounterChain(name = "x1893_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1841(0)), CU.ctr(x1876(0))), op=FixAdd, results=List(x1881))
      Stage(operands=List(x1881, CU.load(x1817_x1880)), op=FixMul, results=List(x1882))
      Stage(operands=List(x1882, CU.ctr(x1837(1))), op=FixAdd, results=List(x1883))
      Stage(operands=List(x1883, Const(4)), op=FixMul, results=List(x1884))
      Stage(operands=List(x1884, CU.load(x1879)), op=FixAdd, results=List(CU.scalarOut(x1877_b2021_x1892_b2023_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x1877_b2022_x1892_b2024_s)))
    }
    val x1894 = MemoryController(name="x1894",parent=x1905,offchip=x1827_oc, mctpe=TileLoad) { implicit CU => 
      val x1877_b2021_x1894 = ScalarFIFO(name="offset",size=1).wtPort(x1877_b2021_x1892_b2023_s)
      val x1877_b2022_x1894 = ScalarFIFO(name="size",size=1).wtPort(x1877_b2022_x1892_b2024_s)
      CU.newVout("data", x1878_x1894_data_v)
    }
    val x1904 = Pipeline(name="x1904",parent=x1905) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x1896 = CounterChain(name = "x1896", ctr7).iter(3)
    }
    val x1943 = MetaPipeline(name="x1943",parent=x1944) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr9 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x1909 = CounterChain(name = "x1909", ctr8, ctr9).iter(768)
    }
    val x1931_0 = Pipeline(name="x1931_0",parent=x1943) { implicit CU => 
      val x1919_x1919 = VectorFIFO(size=1).wtPort(x1843_x1919_x1931_v)
      val x1918_x1918 = VectorFIFO(size=1).wtPort(x1842_x1918_x1931_v)
      val ctr10 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x1912 = CounterChain(name = "x1912", ctr10).iter(3)
      Stage(operands=List(CU.load(x1918_x1918), CU.load(x1919_x1919)), op=FixMul, results=List(CU.reduce))
      val (_, rr303) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1931_0")
      Stage(operands=List(rr303), op=Bypass, results=List(CU.scalarOut(x1910_x1929_s)))
    }
    val x1942_0 = Pipeline(name="x1942_0",parent=x1943) { implicit CU => 
      val x1938 = CU.temp
      val x1939 = CU.temp
      val x1910_x1937 = ScalarBuffer().wtPort(x1910_x1929_s)
      val x1936_x1936 = ScalarFIFO(size=1).wtPort(x1838_x1936_x1942_s)
      val x1841 = CounterChain.copy("x1944", "x1841")
      val x1942_unit = CounterChain(name = "x1942_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1841(0)), Const(0)), op=FixEql, results=List(x1938))
      Stage(operands=List(x1938, Const(0), CU.load(x1936_x1936)), op=Mux, results=List(x1939))
      Stage(operands=List(x1939, CU.load(x1910_x1937)), op=FixAdd, results=List(CU.scalarOut(x1838_x1941_s)))
    }
    val x1984 = StreamController(name="x1984",parent=x1985) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x1946 = CounterChain(name = "x1946", ctr11).iter(16)
    }
    val x1974 = Sequential(name="x1974",parent=x1984) { implicit CU => 
      val x1974_unit = CounterChain(name = "x1974_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1963_0 = Pipeline(name="x1963_0",parent=x1974) { implicit CU => 
      val x1955 = CU.temp
      val x1953 = CU.temp
      val x1952 = CU.temp
      val x1954 = CU.temp
      val x1817_x1951 = ScalarBuffer().wtPort(x1817_argin)
      val x1950 = ScalarBuffer().wtPort(x1950_argin)
      val x1837 = CounterChain.copy("x1985", "x1837")
      val x1946 = CounterChain.copy("x1984", "x1946")
      val x1963_unit = CounterChain(name = "x1963_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1837(0)), CU.ctr(x1946(0))), op=FixAdd, results=List(x1952))
      Stage(operands=List(x1952, CU.load(x1817_x1951)), op=FixMul, results=List(x1953))
      Stage(operands=List(x1953, CU.ctr(x1837(1))), op=FixAdd, results=List(x1954))
      Stage(operands=List(x1954, Const(4)), op=FixMul, results=List(x1955))
      Stage(operands=List(x1955, CU.load(x1950)), op=FixAdd, results=List(CU.scalarOut(x1947_b2035_x1962_b2037_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x1947_b2036_x1962_b2038_s)))
    }
    val x1973 = Pipeline(name="x1973",parent=x1974) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x1965 = CounterChain(name = "x1965", ctr12).iter(3)
    }
    val x1975 = MemoryController(name="x1975",parent=x1984,offchip=x1830_oc, mctpe=TileStore) { implicit CU => 
      val x1947_b2036_x1975 = ScalarFIFO(name="size",size=1).wtPort(x1947_b2036_x1962_b2038_s)
      val x1947_b2035_x1975 = ScalarFIFO(name="offset",size=1).wtPort(x1947_b2035_x1962_b2037_s)
      val x1948_x1975 = VectorFIFO(name="data",size=1).wtPort(x1838_x1969_x1973_v)
    }
    
  }
}
