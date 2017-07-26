import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(top:Top) = {
    val a_da = DRAMAddress("a", "a")
    val x1831_b2146_x1839_b2148_s = Scalar("x1831_b2146_x1839_b2148")
    val b_oc = OffChip("b")
    val x1996_x2005_data_v = Vector("x1996_x2005_data")
    val x1891_b2159_x1899_b2161_s = Scalar("x1891_b2159_x1899_b2161")
    val x2014_b2182_x2022_b2184_s = Scalar("x2014_b2182_x2022_b2184")
    val x1824_x2044_x2051_v = Vector("x1824_x2044_x2051")
    val a_oc = OffChip("a")
    val x1892_x1901_data_v = Vector("x1892_x1901_data")
    val x2037_x2074_s = Scalar("x2037_x2074")
    val x1955_x1964_data_v = Vector("x1955_x1964_data")
    val x2014_b2183_x2022_b2185_s = Scalar("x2014_b2183_x2022_b2185")
    val x1819_x2043_x2051_v = Vector("x1819_x2043_x2051")
    val x1851_x1860_data_v = Vector("x1851_x1860_data")
    val x1828_x2092_x2099_v = Vector("x1828_x2092_x2099")
    val x1850_b2150_x1858_b2152_s = Scalar("x1850_b2150_x1858_b2152")
    val x1831_b2147_x1839_b2149_s = Scalar("x1831_b2147_x1839_b2149")
    val x1973_b2175_x1981_b2177_s = Scalar("x1973_b2175_x1981_b2177")
    val x1933_x1942_data_v = Vector("x1933_x1942_data")
    val x2038_x2086_s = Scalar("x2038_x2086")
    val x1823_x2091_x2099_v = Vector("x1823_x2091_x2099")
    val x1974_x1983_data_v = Vector("x1974_x1983_data")
    val x2035_x2050_s = Scalar("x2035_x2050")
    val x1913_b2163_x1921_b2165_s = Scalar("x1913_b2163_x1921_b2165")
    val x1995_b2179_x2003_b2181_s = Scalar("x1995_b2179_x2003_b2181")
    val x2015_x2024_data_v = Vector("x2015_x2024_data")
    val x2039_x2098_s = Scalar("x2039_x2098")
    val x1891_b2158_x1899_b2160_s = Scalar("x1891_b2158_x1899_b2160")
    val x1995_b2178_x2003_b2180_s = Scalar("x1995_b2178_x2003_b2180")
    val x1820_x2055_x2063_v = Vector("x1820_x2055_x2063")
    val x1932_b2167_x1940_b2169_s = Scalar("x1932_b2167_x1940_b2169")
    val x1954_b2171_x1962_b2173_s = Scalar("x1954_b2171_x1962_b2173")
    val x1822_x2079_x2087_v = Vector("x1822_x2079_x2087")
    val x1832_x1841_data_v = Vector("x1832_x1841_data")
    val x1973_b2174_x1981_b2176_s = Scalar("x1973_b2174_x1981_b2176")
    val N_argin = ArgIn("N").bound(1920000)
    val x1913_b2162_x1921_b2164_s = Scalar("x1913_b2162_x1921_b2164")
    val x1932_b2166_x1940_b2168_s = Scalar("x1932_b2166_x1940_b2168")
    val x1872_b2154_x1880_b2156_s = Scalar("x1872_b2154_x1880_b2156")
    val x1827_x2080_x2087_v = Vector("x1827_x2080_x2087")
    val x1826_x2068_x2075_v = Vector("x1826_x2068_x2075")
    val x1825_x2056_x2063_v = Vector("x1825_x2056_x2063")
    val x1821_x2067_x2075_v = Vector("x1821_x2067_x2075")
    val x1850_b2151_x1858_b2153_s = Scalar("x1850_b2151_x1858_b2153")
    val b_da = DRAMAddress("b", "b")
    val x1873_x1882_data_v = Vector("x1873_x1882_data")
    val x2036_x2062_s = Scalar("x2036_x2062")
    val x1954_b2170_x1962_b2172_s = Scalar("x1954_b2170_x1962_b2172")
    val x1872_b2155_x1880_b2157_s = Scalar("x1872_b2155_x1880_b2157")
    val x1812_x2125_argout = ArgOut("x1812_x2125")
    val x1914_x1923_data_v = Vector("x1914_x1923_data")
    val bus_941_s = Scalar("bus_941")
    val x2127 = Sequential(name="x2127",parent=top) { implicit CU => 
      val x2127_unit = CounterChain(name = "x2127_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2123 = MetaPipeline(name="x2123",parent=x2127) { implicit CU => 
      val x1805_x1816 = ScalarBuffer().wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x1805_x1816.load, step=Const(16), par=5) // Counter
      val x1818 = CounterChain(name = "x1818", ctr1).iter(24000)
    }
    val x1819_dsp0 = MemoryPipeline(name="x1819_dsp0",parent="x2123") { implicit CU => 
      val x1847_x1847 = VectorFIFO(size=1).wtPort(x1832_x1841_data_v)
      val x1843 = CounterChain.copy("x1848", "x1843")
      val x2041 = CounterChain.copy("x2051_0", "x2041")
      val x1819_x2043 = SRAM(size=16,banking = Strided(1)).wtPort(x1847_x1847.readPort).rdPort(x1819_x2043_x2051_v).rdAddr(x2041(0)).wtAddr(x1843(0))
    }
    val x1820_dsp0 = MemoryPipeline(name="x1820_dsp0",parent="x2123") { implicit CU => 
      val x1888_x1888 = VectorFIFO(size=1).wtPort(x1873_x1882_data_v)
      val x1884 = CounterChain.copy("x1889", "x1884")
      val x2053 = CounterChain.copy("x2063_0", "x2053")
      val x1820_x2055 = SRAM(size=16,banking = Strided(1)).wtPort(x1888_x1888.readPort).rdPort(x1820_x2055_x2063_v).rdAddr(x2053(0)).wtAddr(x1884(0))
    }
    val x1821_dsp0 = MemoryPipeline(name="x1821_dsp0",parent="x2123") { implicit CU => 
      val x1929_x1929 = VectorFIFO(size=1).wtPort(x1914_x1923_data_v)
      val x1925 = CounterChain.copy("x1930", "x1925")
      val x2065 = CounterChain.copy("x2075_0", "x2065")
      val x1821_x2067 = SRAM(size=16,banking = Strided(1)).wtPort(x1929_x1929.readPort).rdPort(x1821_x2067_x2075_v).rdAddr(x2065(0)).wtAddr(x1925(0))
    }
    val x1822_dsp0 = MemoryPipeline(name="x1822_dsp0",parent="x2123") { implicit CU => 
      val x1970_x1970 = VectorFIFO(size=1).wtPort(x1955_x1964_data_v)
      val x1966 = CounterChain.copy("x1971", "x1966")
      val x2077 = CounterChain.copy("x2087_0", "x2077")
      val x1822_x2079 = SRAM(size=16,banking = Strided(1)).wtPort(x1970_x1970.readPort).rdPort(x1822_x2079_x2087_v).rdAddr(x2077(0)).wtAddr(x1966(0))
    }
    val x1823_dsp0 = MemoryPipeline(name="x1823_dsp0",parent="x2123") { implicit CU => 
      val x2011_x2011 = VectorFIFO(size=1).wtPort(x1996_x2005_data_v)
      val x2007 = CounterChain.copy("x2012", "x2007")
      val x2089 = CounterChain.copy("x2099_0", "x2089")
      val x1823_x2091 = SRAM(size=16,banking = Strided(1)).wtPort(x2011_x2011.readPort).rdPort(x1823_x2091_x2099_v).rdAddr(x2089(0)).wtAddr(x2007(0))
    }
    val x1824_dsp0 = MemoryPipeline(name="x1824_dsp0",parent="x2123") { implicit CU => 
      val x1866_x1866 = VectorFIFO(size=1).wtPort(x1851_x1860_data_v)
      val x1862 = CounterChain.copy("x1867", "x1862")
      val x2041 = CounterChain.copy("x2051_0", "x2041")
      val x1824_x2044 = SRAM(size=16,banking = Strided(1)).wtPort(x1866_x1866.readPort).rdPort(x1824_x2044_x2051_v).rdAddr(x2041(0)).wtAddr(x1862(0))
    }
    val x1825_dsp0 = MemoryPipeline(name="x1825_dsp0",parent="x2123") { implicit CU => 
      val x1907_x1907 = VectorFIFO(size=1).wtPort(x1892_x1901_data_v)
      val x1903 = CounterChain.copy("x1908", "x1903")
      val x2053 = CounterChain.copy("x2063_0", "x2053")
      val x1825_x2056 = SRAM(size=16,banking = Strided(1)).wtPort(x1907_x1907.readPort).rdPort(x1825_x2056_x2063_v).rdAddr(x2053(0)).wtAddr(x1903(0))
    }
    val x1826_dsp0 = MemoryPipeline(name="x1826_dsp0",parent="x2123") { implicit CU => 
      val x1948_x1948 = VectorFIFO(size=1).wtPort(x1933_x1942_data_v)
      val x1944 = CounterChain.copy("x1949", "x1944")
      val x2065 = CounterChain.copy("x2075_0", "x2065")
      val x1826_x2068 = SRAM(size=16,banking = Strided(1)).wtPort(x1948_x1948.readPort).rdPort(x1826_x2068_x2075_v).rdAddr(x2065(0)).wtAddr(x1944(0))
    }
    val x1827_dsp0 = MemoryPipeline(name="x1827_dsp0",parent="x2123") { implicit CU => 
      val x1989_x1989 = VectorFIFO(size=1).wtPort(x1974_x1983_data_v)
      val x1985 = CounterChain.copy("x1990", "x1985")
      val x2077 = CounterChain.copy("x2087_0", "x2077")
      val x1827_x2080 = SRAM(size=16,banking = Strided(1)).wtPort(x1989_x1989.readPort).rdPort(x1827_x2080_x2087_v).rdAddr(x2077(0)).wtAddr(x1985(0))
    }
    val x1828_dsp0 = MemoryPipeline(name="x1828_dsp0",parent="x2123") { implicit CU => 
      val x2030_x2030 = VectorFIFO(size=1).wtPort(x2015_x2024_data_v)
      val x2026 = CounterChain.copy("x2031", "x2026")
      val x2089 = CounterChain.copy("x2099_0", "x2089")
      val x1828_x2092 = SRAM(size=16,banking = Strided(1)).wtPort(x2030_x2030.readPort).rdPort(x1828_x2092_x2099_v).rdAddr(x2089(0)).wtAddr(x2026(0))
    }
    val x1849 = StreamController(name="x1849",parent=x2123) { implicit CU => 
      val x1849_unit = CounterChain(name = "x1849_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1840_0 = Pipeline(name="x1840_0",parent=x1849) { implicit CU => 
      val x1833 = CU.temp
      val x1835 = ScalarBuffer().wtPort(a_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1840_unit = CounterChain(name = "x1840_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1833))
      Stage(operands=List(x1833, CU.load(x1835)), op=FixAdd, results=List(CU.scalarOut(x1831_b2146_x1839_b2148_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1831_b2147_x1839_b2149_s)))
    }
    val x1841 = MemoryController(name="x1841",parent=x1849,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1831_b2147_x1841 = ScalarFIFO(name="size",size=1).wtPort(x1831_b2147_x1839_b2149_s)
      val x1831_b2146_x1841 = ScalarFIFO(name="offset",size=1).wtPort(x1831_b2146_x1839_b2148_s)
      CU.newVout("data", x1832_x1841_data_v)
    }
    val x1848 = Pipeline(name="x1848",parent=x1849) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1843 = CounterChain(name = "x1843", ctr2).iter(1)
    }
    val x1868 = StreamController(name="x1868",parent=x2123) { implicit CU => 
      val x1868_unit = CounterChain(name = "x1868_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1859_0 = Pipeline(name="x1859_0",parent=x1868) { implicit CU => 
      val x1852 = CU.temp
      val x1854 = ScalarBuffer().wtPort(b_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1859_unit = CounterChain(name = "x1859_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1852))
      Stage(operands=List(x1852, CU.load(x1854)), op=FixAdd, results=List(CU.scalarOut(x1850_b2150_x1858_b2152_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1850_b2151_x1858_b2153_s)))
    }
    val x1860 = MemoryController(name="x1860",parent=x1868,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1850_b2151_x1860 = ScalarFIFO(name="size",size=1).wtPort(x1850_b2151_x1858_b2153_s)
      val x1850_b2150_x1860 = ScalarFIFO(name="offset",size=1).wtPort(x1850_b2150_x1858_b2152_s)
      CU.newVout("data", x1851_x1860_data_v)
    }
    val x1867 = Pipeline(name="x1867",parent=x1868) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1862 = CounterChain(name = "x1862", ctr3).iter(1)
    }
    val x1890 = StreamController(name="x1890",parent=x2123) { implicit CU => 
      val x1890_unit = CounterChain(name = "x1890_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1881_0 = Pipeline(name="x1881_0",parent=x1890) { implicit CU => 
      val x1874 = CU.temp
      val x1876 = ScalarBuffer().wtPort(a_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1881_unit = CounterChain(name = "x1881_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1874))
      Stage(operands=List(x1874, CU.load(x1876)), op=FixAdd, results=List(CU.scalarOut(x1872_b2154_x1880_b2156_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1872_b2155_x1880_b2157_s)))
    }
    val x1882 = MemoryController(name="x1882",parent=x1890,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1872_b2155_x1882 = ScalarFIFO(name="size",size=1).wtPort(x1872_b2155_x1880_b2157_s)
      val x1872_b2154_x1882 = ScalarFIFO(name="offset",size=1).wtPort(x1872_b2154_x1880_b2156_s)
      CU.newVout("data", x1873_x1882_data_v)
    }
    val x1889 = Pipeline(name="x1889",parent=x1890) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1884 = CounterChain(name = "x1884", ctr4).iter(1)
    }
    val x1909 = StreamController(name="x1909",parent=x2123) { implicit CU => 
      val x1909_unit = CounterChain(name = "x1909_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1900_0 = Pipeline(name="x1900_0",parent=x1909) { implicit CU => 
      val x1893 = CU.temp
      val x1895 = ScalarBuffer().wtPort(b_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1900_unit = CounterChain(name = "x1900_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1893))
      Stage(operands=List(x1893, CU.load(x1895)), op=FixAdd, results=List(CU.scalarOut(x1891_b2158_x1899_b2160_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1891_b2159_x1899_b2161_s)))
    }
    val x1901 = MemoryController(name="x1901",parent=x1909,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1891_b2158_x1901 = ScalarFIFO(name="offset",size=1).wtPort(x1891_b2158_x1899_b2160_s)
      val x1891_b2159_x1901 = ScalarFIFO(name="size",size=1).wtPort(x1891_b2159_x1899_b2161_s)
      CU.newVout("data", x1892_x1901_data_v)
    }
    val x1908 = Pipeline(name="x1908",parent=x1909) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1903 = CounterChain(name = "x1903", ctr5).iter(1)
    }
    val x1931 = StreamController(name="x1931",parent=x2123) { implicit CU => 
      val x1931_unit = CounterChain(name = "x1931_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1922_0 = Pipeline(name="x1922_0",parent=x1931) { implicit CU => 
      val x1915 = CU.temp
      val x1917 = ScalarBuffer().wtPort(a_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1922_unit = CounterChain(name = "x1922_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1915))
      Stage(operands=List(x1915, CU.load(x1917)), op=FixAdd, results=List(CU.scalarOut(x1913_b2162_x1921_b2164_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1913_b2163_x1921_b2165_s)))
    }
    val x1923 = MemoryController(name="x1923",parent=x1931,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1913_b2163_x1923 = ScalarFIFO(name="size",size=1).wtPort(x1913_b2163_x1921_b2165_s)
      val x1913_b2162_x1923 = ScalarFIFO(name="offset",size=1).wtPort(x1913_b2162_x1921_b2164_s)
      CU.newVout("data", x1914_x1923_data_v)
    }
    val x1930 = Pipeline(name="x1930",parent=x1931) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1925 = CounterChain(name = "x1925", ctr6).iter(1)
    }
    val x1950 = StreamController(name="x1950",parent=x2123) { implicit CU => 
      val x1950_unit = CounterChain(name = "x1950_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1941_0 = Pipeline(name="x1941_0",parent=x1950) { implicit CU => 
      val x1934 = CU.temp
      val x1936 = ScalarBuffer().wtPort(b_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1941_unit = CounterChain(name = "x1941_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1934))
      Stage(operands=List(x1934, CU.load(x1936)), op=FixAdd, results=List(CU.scalarOut(x1932_b2166_x1940_b2168_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1932_b2167_x1940_b2169_s)))
    }
    val x1942 = MemoryController(name="x1942",parent=x1950,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1932_b2167_x1942 = ScalarFIFO(name="size",size=1).wtPort(x1932_b2167_x1940_b2169_s)
      val x1932_b2166_x1942 = ScalarFIFO(name="offset",size=1).wtPort(x1932_b2166_x1940_b2168_s)
      CU.newVout("data", x1933_x1942_data_v)
    }
    val x1949 = Pipeline(name="x1949",parent=x1950) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1944 = CounterChain(name = "x1944", ctr7).iter(1)
    }
    val x1972 = StreamController(name="x1972",parent=x2123) { implicit CU => 
      val x1972_unit = CounterChain(name = "x1972_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1963_0 = Pipeline(name="x1963_0",parent=x1972) { implicit CU => 
      val x1956 = CU.temp
      val x1958 = ScalarBuffer().wtPort(a_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1963_unit = CounterChain(name = "x1963_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1956))
      Stage(operands=List(x1956, CU.load(x1958)), op=FixAdd, results=List(CU.scalarOut(x1954_b2170_x1962_b2172_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1954_b2171_x1962_b2173_s)))
    }
    val x1964 = MemoryController(name="x1964",parent=x1972,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1954_b2170_x1964 = ScalarFIFO(name="offset",size=1).wtPort(x1954_b2170_x1962_b2172_s)
      val x1954_b2171_x1964 = ScalarFIFO(name="size",size=1).wtPort(x1954_b2171_x1962_b2173_s)
      CU.newVout("data", x1955_x1964_data_v)
    }
    val x1971 = Pipeline(name="x1971",parent=x1972) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1966 = CounterChain(name = "x1966", ctr8).iter(1)
    }
    val x1991 = StreamController(name="x1991",parent=x2123) { implicit CU => 
      val x1991_unit = CounterChain(name = "x1991_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1982_0 = Pipeline(name="x1982_0",parent=x1991) { implicit CU => 
      val x1975 = CU.temp
      val x1977 = ScalarBuffer().wtPort(b_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x1982_unit = CounterChain(name = "x1982_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1975))
      Stage(operands=List(x1975, CU.load(x1977)), op=FixAdd, results=List(CU.scalarOut(x1973_b2174_x1981_b2176_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1973_b2175_x1981_b2177_s)))
    }
    val x1983 = MemoryController(name="x1983",parent=x1991,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x1973_b2175_x1983 = ScalarFIFO(name="size",size=1).wtPort(x1973_b2175_x1981_b2177_s)
      val x1973_b2174_x1983 = ScalarFIFO(name="offset",size=1).wtPort(x1973_b2174_x1981_b2176_s)
      CU.newVout("data", x1974_x1983_data_v)
    }
    val x1990 = Pipeline(name="x1990",parent=x1991) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x1985 = CounterChain(name = "x1985", ctr9).iter(1)
    }
    val x2013 = StreamController(name="x2013",parent=x2123) { implicit CU => 
      val x2013_unit = CounterChain(name = "x2013_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2004_0 = Pipeline(name="x2004_0",parent=x2013) { implicit CU => 
      val x1997 = CU.temp
      val x1999 = ScalarBuffer().wtPort(a_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x2004_unit = CounterChain(name = "x2004_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x1997))
      Stage(operands=List(x1997, CU.load(x1999)), op=FixAdd, results=List(CU.scalarOut(x1995_b2178_x2003_b2180_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x1995_b2179_x2003_b2181_s)))
    }
    val x2005 = MemoryController(name="x2005",parent=x2013,offchip=a_oc, mctpe=TileLoad) { implicit CU => 
      val x1995_b2179_x2005 = ScalarFIFO(name="size",size=1).wtPort(x1995_b2179_x2003_b2181_s)
      val x1995_b2178_x2005 = ScalarFIFO(name="offset",size=1).wtPort(x1995_b2178_x2003_b2180_s)
      CU.newVout("data", x1996_x2005_data_v)
    }
    val x2012 = Pipeline(name="x2012",parent=x2013) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2007 = CounterChain(name = "x2007", ctr10).iter(1)
    }
    val x2032 = StreamController(name="x2032",parent=x2123) { implicit CU => 
      val x2032_unit = CounterChain(name = "x2032_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2023_0 = Pipeline(name="x2023_0",parent=x2032) { implicit CU => 
      val x2016 = CU.temp
      val x2018 = ScalarBuffer().wtPort(b_da)
      val x1818 = CounterChain.copy("x2123", "x1818")
      val x2023_unit = CounterChain(name = "x2023_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1818(0)), Const(4)), op=FixMul, results=List(x2016))
      Stage(operands=List(x2016, CU.load(x2018)), op=FixAdd, results=List(CU.scalarOut(x2014_b2182_x2022_b2184_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2014_b2183_x2022_b2185_s)))
    }
    val x2024 = MemoryController(name="x2024",parent=x2032,offchip=b_oc, mctpe=TileLoad) { implicit CU => 
      val x2014_b2182_x2024 = ScalarFIFO(name="offset",size=1).wtPort(x2014_b2182_x2022_b2184_s)
      val x2014_b2183_x2024 = ScalarFIFO(name="size",size=1).wtPort(x2014_b2183_x2022_b2185_s)
      CU.newVout("data", x2015_x2024_data_v)
    }
    val x2031 = Pipeline(name="x2031",parent=x2032) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2026 = CounterChain(name = "x2026", ctr11).iter(1)
    }
    val x2051_0 = Pipeline(name="x2051_0",parent=x2123) { implicit CU => 
      val x2044_x2044 = VectorFIFO(size=1).wtPort(x1824_x2044_x2051_v)
      val x2043_x2043 = VectorFIFO(size=1).wtPort(x1819_x2043_x2051_v)
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2041 = CounterChain(name = "x2041", ctr12).iter(1)
      Stage(operands=List(CU.load(x2043_x2043), CU.load(x2044_x2044)), op=FixMul, results=List(CU.reduce))
      val (_, rr915) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2051_0")
      Stage(operands=List(rr915), op=Bypass, results=List(CU.scalarOut(x2035_x2050_s)))
    }
    val x2063_0 = Pipeline(name="x2063_0",parent=x2123) { implicit CU => 
      val x2056_x2056 = VectorFIFO(size=1).wtPort(x1825_x2056_x2063_v)
      val x2055_x2055 = VectorFIFO(size=1).wtPort(x1820_x2055_x2063_v)
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2053 = CounterChain(name = "x2053", ctr13).iter(1)
      Stage(operands=List(CU.load(x2055_x2055), CU.load(x2056_x2056)), op=FixMul, results=List(CU.reduce))
      val (_, rr920) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2063_0")
      Stage(operands=List(rr920), op=Bypass, results=List(CU.scalarOut(x2036_x2062_s)))
    }
    val x2075_0 = Pipeline(name="x2075_0",parent=x2123) { implicit CU => 
      val x2068_x2068 = VectorFIFO(size=1).wtPort(x1826_x2068_x2075_v)
      val x2067_x2067 = VectorFIFO(size=1).wtPort(x1821_x2067_x2075_v)
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2065 = CounterChain(name = "x2065", ctr14).iter(1)
      Stage(operands=List(CU.load(x2067_x2067), CU.load(x2068_x2068)), op=FixMul, results=List(CU.reduce))
      val (_, rr925) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2075_0")
      Stage(operands=List(rr925), op=Bypass, results=List(CU.scalarOut(x2037_x2074_s)))
    }
    val x2087_0 = Pipeline(name="x2087_0",parent=x2123) { implicit CU => 
      val x2080_x2080 = VectorFIFO(size=1).wtPort(x1827_x2080_x2087_v)
      val x2079_x2079 = VectorFIFO(size=1).wtPort(x1822_x2079_x2087_v)
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2077 = CounterChain(name = "x2077", ctr15).iter(1)
      Stage(operands=List(CU.load(x2079_x2079), CU.load(x2080_x2080)), op=FixMul, results=List(CU.reduce))
      val (_, rr930) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2087_0")
      Stage(operands=List(rr930), op=Bypass, results=List(CU.scalarOut(x2038_x2086_s)))
    }
    val x2099_0 = Pipeline(name="x2099_0",parent=x2123) { implicit CU => 
      val x2092_x2092 = VectorFIFO(size=1).wtPort(x1828_x2092_x2099_v)
      val x2091_x2091 = VectorFIFO(size=1).wtPort(x1823_x2091_x2099_v)
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2089 = CounterChain(name = "x2089", ctr16).iter(1)
      Stage(operands=List(CU.load(x2091_x2091), CU.load(x2092_x2092)), op=FixMul, results=List(CU.reduce))
      val (_, rr935) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2099_0")
      Stage(operands=List(rr935), op=Bypass, results=List(CU.scalarOut(x2039_x2098_s)))
    }
    val x2122 = StreamController(name="x2122",parent=x2123) { implicit CU => 
      val x2122_unit = CounterChain(name = "x2122_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2122_0 = Pipeline(name="x2122_0",parent=x2122) { implicit CU => 
      val x2103 = CU.temp
      val x2111 = CU.temp
      val x2108 = CU.temp
      val x2035_x2102 = ScalarBuffer().wtPort(x2035_x2050_s)
      val x2038_x2106 = ScalarBuffer().wtPort(x2038_x2086_s)
      val x2036_x2101 = ScalarBuffer().wtPort(x2036_x2062_s)
      val x2037_x2107 = ScalarBuffer().wtPort(x2037_x2074_s)
      val x2039_x2114 = ScalarBuffer().wtPort(x2039_x2098_s)
      Stage(operands=List(CU.load(x2035_x2102), CU.load(x2036_x2101)), op=FixAdd, results=List(x2103))
      Stage(operands=List(CU.load(x2037_x2107), CU.load(x2038_x2106)), op=FixAdd, results=List(x2108))
      Stage(operands=List(x2103, x2108), op=FixAdd, results=List(x2111))
      Stage(operands=List(x2111, CU.load(x2039_x2114)), op=FixAdd, results=List(CU.scalarOut(bus_941_s)))
    }
    val x2122_1 = Pipeline(name="x2122_1",parent=x2122) { implicit CU => 
      val rr941 = ScalarFIFO(size=1).wtPort(bus_941_s)
      Stage(operands=List(CU.load(rr941)), op=Bypass, results=List(CU.reduce))
      val (_, rr943) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2123")
      Stage(operands=List(rr943), op=Bypass, results=List(CU.scalarOut(x1812_x2125_argout)))
    }
    
  }
}
