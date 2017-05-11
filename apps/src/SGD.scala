import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SGD extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1966_b2025_x1974_b2027_s = Scalar("x1966_b2025_x1974_b2027")
    val x1892_b2021_x1906_b2023_s = Scalar("x1892_b2021_x1906_b2023")
    val x1839_oc = OffChip("x1839")
    val x1883_x1953_x1962_v = Vector("x1883_x1953_x1962")
    val x1851_x1941_x1945_v = Vector("x1851_x1941_x1945")
    val x1893_x1908_data_v = Vector("x1893_x1908_data")
    val x1966_b2026_x1974_b2028_s = Scalar("x1966_b2026_x1974_b2028")
    val x1921_x1936_s = Scalar("x1921_x1936")
    val x1894_argin = ArgIn("x1894")
    val x1838_oc = OffChip("x1838")
    val x1829_argin = ArgIn("x1829")
    val x1884_x1888_s = Scalar("x1884_x1888")
    val x1852_x1961_v = Vector("x1852_x1961")
    val x1892_b2022_x1906_b2024_s = Scalar("x1892_b2022_x1906_b2024")
    val x1831_argin = ArgIn("x1831")
    val x1860_x1870_data_v = Vector("x1860_x1870_data")
    val x1852_x1928_x1938_v = Vector("x1852_x1928_x1938")
    val x1861_argin = ArgIn("x1861")
    val x1836_oc = OffChip("x1836")
    val x1883_x1927_x1938_v = Vector("x1883_x1927_x1938")
    val x1969_argin = ArgIn("x1969")
    val x1852_x1952_x1962_v = Vector("x1852_x1952_x1962")
    val x1830_argin = ArgIn("x1830")
    val x1882_x1944_s = Scalar("x1882_x1944")
    val x1859_b2018_x1868_b2020_s = Scalar("x1859_b2018_x1868_b2020")
    val x1852_x1978_x1982_v = Vector("x1852_x1978_x1982")
    val x1859_b2017_x1868_b2019_s = Scalar("x1859_b2017_x1868_b2019")
    val x1991 = Sequential(name="x1991",parent=top) { implicit CU => 
    }
    val x1851_dsp0 = MemoryPipeline(name="x1851_dsp0",parent="x1991") { implicit CU => 
      val x1877_x1877 =  VectorFIFO(size=1).wtPort(x1860_x1870_data_v)
      val x1872 = CounterChain.copy("x1878", "x1872")
      val x1881 = CounterChain.copy("x1963", "x1881")
      val x1851_x1941 =  SRAM(size=192,banking = Strided(1)).wtPort(x1877_x1877.readPort).rdPort(x1851_x1941_x1945_v).rdAddr(x1881(0)).wtAddr(x1872(0))
      var stage: List[Stage] = Nil
    }
    val x1852_dsp0 = MemoryPipeline(name="x1852_dsp0",parent="x1991") { implicit CU => 
      val x1961_x1961 =  VectorFIFO(size=1).wtPort(x1852_x1961_v)
      val x1977 = CounterChain.copy("x1982", "x1977")
      val x1948 = CounterChain.copy("x1962_0", "x1948")
      val x1852_x1978 =  SRAM(size=16,banking = Strided(1)).wtPort(x1961_x1961.readPort).rdPort(x1852_x1978_x1982_v).rdAddr(x1977(0)).wtAddr(x1948(0))
      var stage: List[Stage] = Nil
    }
    val x1852_dsp1 = MemoryPipeline(name="x1852_dsp1",parent="x1991") { implicit CU => 
      val x1961_x1961 =  VectorFIFO(size=1).wtPort(x1852_x1961_v)
      val x1948 = CounterChain.copy("x1962_0", "x1948")
      val x1852_x1952 =  SRAM(size=16,banking = Strided(1)).wtPort(x1961_x1961.readPort).rdPort(x1852_x1952_x1962_v).rdAddr(x1948(0)).wtAddr(x1948(0))
      var stage: List[Stage] = Nil
    }
    val x1852_dsp2 = MemoryPipeline(name="x1852_dsp2",parent="x1991") { implicit CU => 
      val x1961_x1961 =  VectorFIFO(size=1).wtPort(x1852_x1961_v)
      val x1948 = CounterChain.copy("x1962_0", "x1948")
      val x1923 = CounterChain.copy("x1938_0", "x1923")
      val x1852_x1928 =  SRAM(size=16,banking = Strided(1)).wtPort(x1961_x1961.readPort).rdPort(x1852_x1928_x1938_v).rdAddr(x1923(0)).wtAddr(x1948(0))
      var stage: List[Stage] = Nil
    }
    val x1965 = Sequential(name="x1965",parent=x1991) { implicit CU => 
      val x1829_x1853 =  ScalarBuffer().wtPort(x1829_argin)
      val ctr1 = Counter(min=Const(0), max=x1829_x1853.load, step=Const(1), par=1) // Counter
      val x1855 = CounterChain(name = "x1855", ctr1).iter(1)
    }
    val x1964 = Sequential(name="x1964",parent=x1965) { implicit CU => 
      val x1830_x1856 =  ScalarBuffer().wtPort(x1830_argin)
      val ctr2 = Counter(min=Const(0), max=x1830_x1856.load, step=Const(192), par=1) // Counter
      val x1858 = CounterChain(name = "x1858", ctr2).iter(1)
    }
    val x1879 = StreamController(name="x1879",parent=x1964) { implicit CU => 
    }
    val x1869_0 = Pipeline(name="x1869_0",parent=x1879) { implicit CU => 
      val x1862 = CU.temp
      val x1861 =  ScalarBuffer().wtPort(x1861_argin)
      val x1858 = CounterChain.copy("x1964", "x1858")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1858(0)), Const(4)), op=FixMul, results=List(x1862))
      Stage(operands=List(x1862, CU.load(x1861)), op=FixAdd, results=List(CU.scalarOut(x1859_b2017_x1868_b2019_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x1859_b2018_x1868_b2020_s)))
    }
    val x1870 = MemoryController(name="x1870",parent=x1879,offchip=x1838_oc, mctpe=TileLoad) { implicit CU => 
      val x1859_b2018_x1870 =  ScalarFIFO(name="size",size=1).wtPort(x1859_b2018_x1868_b2020_s)
      val x1859_b2017_x1870 =  ScalarFIFO(name="offset",size=1).wtPort(x1859_b2017_x1868_b2019_s)
      CU.newVout("data", x1860_x1870_data_v)
    }
    val x1878 = Pipeline(name="x1878",parent=x1879) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x1872 = CounterChain(name = "x1872", ctr3).iter(12)
      var stage: List[Stage] = Nil
    }
    val x1963 = Sequential(name="x1963",parent=x1964) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x1881 = CounterChain(name = "x1881", ctr4).iter(192)
    }
    val x1883_dsp0 = MemoryPipeline(name="x1883_dsp0",parent="x1963") { implicit CU => 
      val x1917_x1917 =  VectorFIFO(size=1).wtPort(x1893_x1908_data_v)
      val x1948 = CounterChain.copy("x1962_0", "x1948")
      val x1910 = CounterChain.copy("x1918", "x1910")
      val x1883_x1953 =  SRAM(size=16,banking = Strided(1)).wtPort(x1917_x1917.readPort).rdPort(x1883_x1953_x1962_v).rdAddr(x1948(0)).wtAddr(x1910(0))
      var stage: List[Stage] = Nil
    }
    val x1883_dsp1 = MemoryPipeline(name="x1883_dsp1",parent="x1963") { implicit CU => 
      val x1917_x1917 =  VectorFIFO(size=1).wtPort(x1893_x1908_data_v)
      val x1910 = CounterChain.copy("x1918", "x1910")
      val x1923 = CounterChain.copy("x1938_0", "x1923")
      val x1883_x1927 =  SRAM(size=16,banking = Strided(1)).wtPort(x1917_x1917.readPort).rdPort(x1883_x1927_x1938_v).rdAddr(x1923(0)).wtAddr(x1910(0))
      var stage: List[Stage] = Nil
    }
    val x1889_0 = Pipeline(name="x1889_0",parent=x1963) { implicit CU => 
      val x1858 = CounterChain.copy("x1964", "x1858")
      val x1881 = CounterChain.copy("x1963", "x1881")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1858(0)), CU.ctr(x1881(0))), op=FixAdd, results=List(CU.scalarOut(x1884_x1888_s)))
    }
    val x1919 = StreamController(name="x1919",parent=x1963) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x1891 = CounterChain(name = "x1891", ctr5).iter(1)
    }
    val x1907_0 = Pipeline(name="x1907_0",parent=x1919) { implicit CU => 
      val x1896 = CU.temp
      val x1897 = CU.temp
      val x1898 = CU.temp
      val x1894 =  ScalarBuffer().wtPort(x1894_argin)
      val x1884_x1895 =  ScalarBuffer().wtPort(x1884_x1888_s)
      val x1891 = CounterChain.copy("x1919", "x1891")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1884_x1895), CU.ctr(x1891(0))), op=FixAdd, results=List(x1896))
      Stage(operands=List(x1896, Const(16)), op=FixMul, results=List(x1897))
      Stage(operands=List(x1897, Const(4)), op=FixMul, results=List(x1898))
      Stage(operands=List(x1898, CU.load(x1894)), op=FixAdd, results=List(CU.scalarOut(x1892_b2021_x1906_b2023_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1892_b2022_x1906_b2024_s)))
    }
    val x1908 = MemoryController(name="x1908",parent=x1919,offchip=x1836_oc, mctpe=TileLoad) { implicit CU => 
      val x1892_b2021_x1908 =  ScalarFIFO(name="offset",size=1).wtPort(x1892_b2021_x1906_b2023_s)
      val x1892_b2022_x1908 =  ScalarFIFO(name="size",size=1).wtPort(x1892_b2022_x1906_b2024_s)
      CU.newVout("data", x1893_x1908_data_v)
    }
    val x1918 = Pipeline(name="x1918",parent=x1919) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1910 = CounterChain(name = "x1910", ctr6).iter(1)
      var stage: List[Stage] = Nil
    }
    val x1946 = Sequential(name="x1946",parent=x1963) { implicit CU => 
    }
    val x1938_0 = Pipeline(name="x1938_0",parent=x1946) { implicit CU => 
      val x1928_x1928 =  VectorFIFO(size=1).wtPort(x1852_x1928_x1938_v)
      val x1927_x1927 =  VectorFIFO(size=1).wtPort(x1883_x1927_x1938_v)
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1923 = CounterChain(name = "x1923", ctr7).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1927_x1927), CU.load(x1928_x1928)), op=FixMul, results=List(CU.reduce))
      val (_, rr269) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr269), op=Bypass, results=List(CU.scalarOut(x1921_x1936_s)))
    }
    val x1945_0 = Pipeline(name="x1945_0",parent=x1946) { implicit CU => 
      val x1921_x1942 =  ScalarBuffer().wtPort(x1921_x1936_s)
      val x1941_x1941 =  VectorFIFO(size=1).wtPort(x1851_x1941_x1945_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1941_x1941), CU.load(x1921_x1942)), op=FixSub, results=List(CU.scalarOut(x1882_x1944_s)))
    }
    val x1962_0 = Pipeline(name="x1962_0",parent=x1963) { implicit CU => 
      val x1958 = CU.temp
      val x1959 = CU.temp
      val x1952_x1952 =  VectorFIFO(size=1).wtPort(x1852_x1952_x1962_v)
      val x1882_x1954 =  ScalarBuffer().wtPort(x1882_x1944_s)
      val x1831_x1955 =  ScalarBuffer().wtPort(x1831_argin)
      val x1953_x1953 =  VectorFIFO(size=1).wtPort(x1883_x1953_x1962_v)
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1948 = CounterChain(name = "x1948", ctr8).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1953_x1953), CU.load(x1882_x1954)), op=FixMul, results=List(x1958))
      Stage(operands=List(x1958, CU.load(x1831_x1955)), op=FixMul, results=List(x1959))
      Stage(operands=List(CU.load(x1952_x1952), x1959), op=FixAdd, results=List(CU.vecOut(x1852_x1961_v)))
    }
    val x1990 = StreamController(name="x1990",parent=x1991) { implicit CU => 
    }
    val x1983 = Sequential(name="x1983",parent=x1990) { implicit CU => 
    }
    val x1975_0 = Pipeline(name="x1975_0",parent=x1983) { implicit CU => 
      val x1969 =  ScalarBuffer().wtPort(x1969_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x1969)), op=FixAdd, results=List(CU.scalarOut(x1966_b2025_x1974_b2027_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1966_b2026_x1974_b2028_s)))
    }
    val x1982 = Pipeline(name="x1982",parent=x1983) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1977 = CounterChain(name = "x1977", ctr9).iter(1)
      var stage: List[Stage] = Nil
    }
    val x1984 = MemoryController(name="x1984",parent=x1990,offchip=x1839_oc, mctpe=TileStore) { implicit CU => 
      val x1967_x1984 =  VectorFIFO(name="data",size=1).wtPort(x1852_x1978_x1982_v)
      val x1966_b2026_x1984 =  ScalarFIFO(name="size",size=1).wtPort(x1966_b2026_x1974_b2028_s)
      val x1966_b2025_x1984 =  ScalarFIFO(name="offset",size=1).wtPort(x1966_b2025_x1974_b2027_s)
    }
    
  }
}
