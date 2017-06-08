import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object BFS extends PIRApp {
  def main(top:Top) = {
    val x2099_x2105_s = Scalar("x2099_x2105")
    val x1994_oc = OffChip("x1994")
    val x2011_argin = ArgIn("x2011")
    val x2003_x2057_x2066_v = Vector("x2003_x2057_x2066")
    val x2052_x2065_s = Scalar("x2052_x2065")
    val bus_312_s = Scalar("bus_312")
    val x2068_b2217_x2096_b2225_s = Scalar("x2068_b2217_x2096_b2225")
    val x2049_x2060_s = Scalar("x2049_x2060")
    val x2068_b2218_x2096_b2226_s = Scalar("x2068_b2218_x2096_b2226")
    val x2027_x2035_data_v = Vector("x2027_x2035_data")
    val x2069_x2098_data_v = Vector("x2069_x2098_data")
    val x1995_oc = OffChip("x1995")
    val x2070_argin = ArgIn("x2070")
    val x2026_b2211_x2033_b2213_s = Scalar("x2026_b2211_x2033_b2213")
    val x2004_x2174_v = Vector("x2004_x2174")
    val bus_313_s = Scalar("bus_313")
    val x2067_b2214_x2089_b2222_s = Scalar("x2067_b2214_x2089_b2222")
    val x2101_x2109_s = Scalar("x2101_x2109")
    val x2008_x2200_s = Scalar("x2008_x2200")
    val x1993_oc = OffChip("x1993")
    val x2026_b2210_x2033_b2212_s = Scalar("x2026_b2210_x2033_b2212")
    val bus_310_s = Scalar("bus_310")
    val x2010_x2018_data_v = Vector("x2010_x2018_data")
    val x1996_oc = OffChip("x1996")
    val x2007_x2157_s = Scalar("x2007_x2157")
    val x2050_x2058_s = Scalar("x2050_x2058")
    val x2002_x2061_x2066_v = Vector("x2002_x2061_x2066")
    val x2129_x2148_x2001_v = Vector("x2129_x2148_x2001")
    val x2004_x2183_x2191_v = Vector("x2004_x2183_x2191")
    val x2009_b2206_x2016_b2208_s = Scalar("x2009_b2206_x2016_b2208")
    val x2051_x2062_s = Scalar("x2051_x2062")
    val x2067_b2215_x2089_b2223_s = Scalar("x2067_b2215_x2089_b2223")
    val x2068_b2216_x2096_b2224_s = Scalar("x2068_b2216_x2096_b2224")
    val x2006_x2149_x2153_v = Vector("x2006_x2149_x2153")
    val x2005_x2181_x2191_v = Vector("x2005_x2181_x2191")
    val bus_314_s = Scalar("bus_314")
    val x2028_argin = ArgIn("x2028")
    val x2005_x2054_x2002_v = Vector("x2005_x2054_x2002")
    val x2005_x2054_x2003_v = Vector("x2005_x2054_x2003")
    val x2100_x2107_s = Scalar("x2100_x2107")
    val x2129_x2141_v = Vector("x2129_x2141")
    val x2009_b2207_x2016_b2209_s = Scalar("x2009_b2207_x2016_b2209")
    val bus_321_s = Scalar("bus_321")
    val x2005_x2056_x2002_v = Vector("x2005_x2056_x2002")
    val x2001_x2165_x2168_v = Vector("x2001_x2165_x2168")
    val x2182_argin = ArgIn("x2182")
    val bus_66_s = Scalar("bus_66")
    val x2002_x2059_x2066_v = Vector("x2002_x2059_x2066")
    val x2203 = Sequential(name="x2203",parent=top) { implicit CU => 
    }
    val x2001_dsp0 = MemoryPipeline(name="x2001_dsp0",parent="x2203") { implicit CU => 
      val x2152_x2152 =  VectorFIFO(size=1).wtPort(x2006_x2149_x2153_v)
      val x2148_x2148 =  VectorFIFO(size=1).wtPort(x2129_x2148_x2001_v)
      val x2163 = CounterChain.copy("x2168", "x2163")
      val x2001_x2165 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2152_x2152.readPort).rdPort(x2001_x2165_x2168_v).rdAddr(x2163(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.load(x2148_x2148)), op=Bypass, results=List(x2001_x2165.writeAddr))
    }
    val x2002_dsp0 = MemoryPipeline(name="x2002_dsp0",parent="x2203") { implicit CU => 
      val x2056_x2056 =  VectorFIFO(size=1).wtPort(x2005_x2056_x2002_v)
      val x2040_x2040 =  VectorFIFO(size=1).wtPort(x2027_x2035_data_v)
      val x2037 = CounterChain.copy("x2041", "x2037")
      val x2002_x2061 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2040_x2040.readPort).rdPort(x2002_x2061_x2066_v).wtAddr(x2037(0))
      var stage: List[Stage] = Nil
      RAStage(operands=List(CU.load(x2056_x2056)), op=Bypass, results=List(x2002_x2061.readAddr))
    }
    val x2002_dsp1 = MemoryPipeline(name="x2002_dsp1",parent="x2203") { implicit CU => 
      val x2040_x2040 =  VectorFIFO(size=1).wtPort(x2027_x2035_data_v)
      val x2054_x2054 =  VectorFIFO(size=1).wtPort(x2005_x2054_x2002_v)
      val x2037 = CounterChain.copy("x2041", "x2037")
      val x2002_x2059 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2040_x2040.readPort).rdPort(x2002_x2059_x2066_v).wtAddr(x2037(0))
      var stage: List[Stage] = Nil
      RAStage(operands=List(CU.load(x2054_x2054)), op=Bypass, results=List(x2002_x2059.readAddr))
    }
    val x2003_dsp0 = MemoryPipeline(name="x2003_dsp0",parent="x2203") { implicit CU => 
      val x2023_x2023 =  VectorFIFO(size=1).wtPort(x2010_x2018_data_v)
      val x2054_x2054 =  VectorFIFO(size=1).wtPort(x2005_x2054_x2003_v)
      val x2020 = CounterChain.copy("x2024", "x2020")
      val x2003_x2057 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2023_x2023.readPort).rdPort(x2003_x2057_x2066_v).wtAddr(x2020(0))
      var stage: List[Stage] = Nil
      RAStage(operands=List(CU.load(x2054_x2054)), op=Bypass, results=List(x2003_x2057.readAddr))
    }
    val x2004_dsp0 = MemoryPipeline(name="x2004_dsp0",parent="x2203") { implicit CU => 
      val x2174_x2174 =  VectorFIFO(size=1).wtPort(x2004_x2174_v)
      val x2171 = CounterChain.copy("x2175_0", "x2171")
      val x2179 = CounterChain.copy("x2191_0", "x2179")
      val x2004_x2183 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2174_x2174.readPort).rdPort(x2004_x2183_x2191_v).rdAddr(x2179(0)).wtAddr(x2171(0))
      var stage: List[Stage] = Nil
    }
    val x2005_dsp0 = MemoryPipeline(name="x2005_dsp0",parent="x2203") { implicit CU => 
      val x2167_x2167 =  VectorFIFO(size=1).wtPort(x2001_x2165_x2168_v)
      val x2163 = CounterChain.copy("x2168", "x2163")
      val x2179 = CounterChain.copy("x2191_0", "x2179")
      val x2005_x2181 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2167_x2167.readPort).rdPort(x2005_x2181_x2191_v).rdAddr(x2179(0)).wtAddr(x2163(0))
      var stage: List[Stage] = Nil
    }
    val x2005_dsp1 = MemoryPipeline(name="x2005_dsp1",parent="x2203") { implicit CU => 
      val x2167_x2167 =  VectorFIFO(size=1).wtPort(x2001_x2165_x2168_v)
      val x2048 = CounterChain.copy("x2160", "x2048")
      val x2163 = CounterChain.copy("x2168", "x2163")
      val x2005_x2056 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2167_x2167.readPort).wtAddr(x2163(0))
      var stage: List[Stage] = Nil
      RAStage(operands=List(CU.ctr(x2048(0)), Const(1)), op=FixSub, results=List(x2005_x2056.readAddr))
    }
    val x2005_dsp2 = MemoryPipeline(name="x2005_dsp2",parent="x2203") { implicit CU => 
      val x2167_x2167 =  VectorFIFO(size=1).wtPort(x2001_x2165_x2168_v)
      val x2048 = CounterChain.copy("x2160", "x2048")
      val x2163 = CounterChain.copy("x2168", "x2163")
      val x2005_x2054 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2167_x2167.readPort).rdAddr(x2048(0)).wtAddr(x2163(0))
      var stage: List[Stage] = Nil
    }
    val x2006_dsp0 = MemoryPipeline(name="x2006_dsp0",parent="x2203") { implicit CU => 
      val x2125_x2125 =  VectorFIFO(size=1).wtPort(x2069_x2098_data_v)
      val x2099_x2114 =  ScalarBuffer().wtPort(x2099_x2105_s)
      val x2113 = CounterChain.copy("x2126", "x2113")
      val x2145 = CounterChain.copy("x2153", "x2145")
      val x2006_x2149 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2125_x2125.readPort).rdPort(x2006_x2149_x2153_v).rdAddr(x2145(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2113(0)), CU.load(x2099_x2114)), op=FixSub, results=List(x2006_x2149.writeAddr))
    }
    val x2025 = StreamController(name="x2025",parent=x2203) { implicit CU => 
    }
    val x2017_0 = Pipeline(name="x2017_0",parent=x2025) { implicit CU => 
      val x2011 =  ScalarBuffer().wtPort(x2011_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2011)), op=FixAdd, results=List(CU.scalarOut(x2009_b2206_x2016_b2208_s)))
      Stage(operands=List(Const(32000)), op=Bypass, results=List(CU.scalarOut(x2009_b2207_x2016_b2209_s)))
    }
    val x2018 = MemoryController(name="x2018",parent=x2025,offchip=x1995_oc, mctpe=TileLoad) { implicit CU => 
      val x2009_b2206_x2018 =  ScalarFIFO(name="offset",size=1).wtPort(x2009_b2206_x2016_b2208_s)
      val x2009_b2207_x2018 =  ScalarFIFO(name="size",size=1).wtPort(x2009_b2207_x2016_b2209_s)
      CU.newVout("data", x2010_x2018_data_v)
    }
    val x2024 = Pipeline(name="x2024",parent=x2025) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(8000), step=Const(1), par=1) // Counter
      val x2020 = CounterChain(name = "x2020", ctr1).iter(8000)
      var stage: List[Stage] = Nil
    }
    val x2042 = StreamController(name="x2042",parent=x2203) { implicit CU => 
    }
    val x2034_0 = Pipeline(name="x2034_0",parent=x2042) { implicit CU => 
      val x2028 =  ScalarBuffer().wtPort(x2028_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x2028)), op=FixAdd, results=List(CU.scalarOut(x2026_b2210_x2033_b2212_s)))
      Stage(operands=List(Const(32000)), op=Bypass, results=List(CU.scalarOut(x2026_b2211_x2033_b2213_s)))
    }
    val x2035 = MemoryController(name="x2035",parent=x2042,offchip=x1994_oc, mctpe=TileLoad) { implicit CU => 
      val x2026_b2211_x2035 =  ScalarFIFO(name="size",size=1).wtPort(x2026_b2211_x2033_b2213_s)
      val x2026_b2210_x2035 =  ScalarFIFO(name="offset",size=1).wtPort(x2026_b2210_x2033_b2212_s)
      CU.newVout("data", x2027_x2035_data_v)
    }
    val x2041 = Pipeline(name="x2041",parent=x2042) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(8000), step=Const(1), par=1) // Counter
      val x2037 = CounterChain(name = "x2037", ctr2).iter(8000)
      var stage: List[Stage] = Nil
    }
    val x2202 = Sequential(name="x2202",parent=x2203) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(4), step=Const(1), par=1) // Counter
      val x2045 = CounterChain(name = "x2045", ctr3).iter(4)
    }
    val x2160 = MetaPipeline(name="x2160",parent=x2202) { implicit CU => 
      val x2008_x2046 =  ScalarBuffer().wtPort(x2008_x2200_s)
      val ctr4 = Counter(min=Const(0), max=x2008_x2046.load, step=Const(1), par=1) // Counter
      val x2048 = CounterChain(name = "x2048", ctr4).iter(1)
    }
    val x2066_0 = Pipeline(name="x2066_0",parent=x2160) { implicit CU => 
      val x2057_x2057 =  VectorFIFO(size=1).wtPort(x2003_x2057_x2066_v)
      val x2059_x2059 =  VectorFIFO(size=1).wtPort(x2002_x2059_x2066_v)
      val ar66 =  ScalarFIFO(size=1).wtPort(bus_66_s)
      val x2061_x2061 =  VectorFIFO(size=1).wtPort(x2002_x2061_x2066_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2057_x2057)), op=Bypass, results=List(CU.scalarOut(x2050_x2058_s)))
      Stage(operands=List(CU.load(x2059_x2059)), op=Bypass, results=List(CU.scalarOut(x2049_x2060_s)))
      Stage(operands=List(CU.load(x2061_x2061)), op=Bypass, results=List(CU.scalarOut(x2051_x2062_s)))
      Stage(operands=List(CU.load(ar66)), op=Bypass, results=List(CU.scalarOut(x2052_x2065_s)))
    }
    val x2128 = StreamController(name="x2128",parent=x2160) { implicit CU => 
    }
    val x2097 = StreamController(name="x2097",parent=x2128) { implicit CU => 
    }
    val x2097_0 = Pipeline(name="x2097_0",parent=x2097) { implicit CU => 
      val x2080 = CU.temp
      val x2073 = CU.temp
      val x2050_x2071 =  ScalarBuffer().wtPort(x2050_x2058_s)
      val x2052_x2072 =  ScalarBuffer().wtPort(x2052_x2065_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2050_x2071), Const(4)), op=FixMul, results=List(x2073, CU.scalarOut(bus_310_s)))
      Stage(operands=List(x2073, Const(64)), op=FixMod, results=List(x2080, CU.scalarOut(bus_312_s)))
      Stage(operands=List(x2080, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_313_s), CU.scalarOut(x2068_b2217_x2096_b2225_s)))
      Stage(operands=List(CU.load(x2052_x2072), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_314_s)))
    }
    val x2097_1 = Pipeline(name="x2097_1",parent=x2097) { implicit CU => 
      val x2077 = CU.temp
      val x2076 = CU.temp
      val x2078 = CU.temp
      val x2075 = CU.temp
      val x2074 =  ScalarFIFO(size=1).wtPort(bus_314_s)
      val x2073 =  ScalarFIFO(size=1).wtPort(bus_310_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2073), CU.load(x2074)), op=FixAdd, results=List(x2075))
      Stage(operands=List(x2075, Const(4)), op=FixDiv, results=List(CU.scalarOut(x2068_b2218_x2096_b2226_s)))
      Stage(operands=List(x2075, Const(64)), op=FixMod, results=List(x2076))
      Stage(operands=List(x2076, Const(0)), op=FixEql, results=List(x2077))
      Stage(operands=List(Const(64), x2076), op=FixSub, results=List(x2078))
      Stage(operands=List(x2077, Const(0), x2078), op=Mux, results=List(CU.scalarOut(bus_321_s)))
    }
    val x2097_2 = Pipeline(name="x2097_2",parent=x2097) { implicit CU => 
      val x2093 = CU.temp
      val x2081 = CU.temp
      val x2092 = CU.temp
      val x2080 =  ScalarFIFO(size=1).wtPort(bus_312_s)
      val x2090 =  ScalarFIFO(size=1).wtPort(bus_313_s)
      val x2074 =  ScalarFIFO(size=1).wtPort(bus_314_s)
      val x2052_x2072 =  ScalarBuffer().wtPort(x2052_x2065_s)
      val x2079 =  ScalarFIFO(size=1).wtPort(bus_321_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2079), Const(4)), op=FixDiv, results=List(x2092))
      Stage(operands=List(CU.load(x2052_x2072), CU.load(x2090)), op=FixAdd, results=List(x2093))
      Stage(operands=List(x2093, x2092), op=FixAdd, results=List(CU.scalarOut(x2068_b2216_x2096_b2224_s)))
      Stage(operands=List(CU.load(x2074), CU.load(x2080)), op=FixAdd, results=List(x2081))
      Stage(operands=List(x2081, CU.load(x2079)), op=FixAdd, results=List(CU.scalarOut(x2067_b2215_x2089_b2223_s)))
    }
    val x2097_3 = Pipeline(name="x2097_3",parent=x2097) { implicit CU => 
      val x2083 = CU.temp
      val x2070 =  ScalarBuffer().wtPort(x2070_argin)
      val x2080 =  ScalarFIFO(size=1).wtPort(bus_312_s)
      val x2073 =  ScalarFIFO(size=1).wtPort(bus_310_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2073), CU.load(x2080)), op=FixSub, results=List(x2083))
      Stage(operands=List(x2083, CU.load(x2070)), op=FixAdd, results=List(CU.scalarOut(x2067_b2214_x2089_b2222_s)))
    }
    val x2098 = MemoryController(name="x2098",parent=x2128,offchip=x1993_oc, mctpe=TileLoad) { implicit CU => 
      val x2067_b2215_x2098 =  ScalarFIFO(name="size",size=1).wtPort(x2067_b2215_x2089_b2223_s)
      val x2067_b2214_x2098 =  ScalarFIFO(name="offset",size=1).wtPort(x2067_b2214_x2089_b2222_s)
      CU.newVout("data", x2069_x2098_data_v)
    }
    val x2127 = Sequential(name="x2127",parent=x2128) { implicit CU => 
    }
    val x2110_0 = Pipeline(name="x2110_0",parent=x2127) { implicit CU => 
      val x2068_b2218_x2103_b2221 =  ScalarFIFO(size=16).wtPort(x2068_b2218_x2096_b2226_s)
      val x2068_b2217_x2103_b2220 =  ScalarFIFO(size=16).wtPort(x2068_b2217_x2096_b2225_s)
      val x2068_b2216_x2103_b2219 =  ScalarFIFO(size=16).wtPort(x2068_b2216_x2096_b2224_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2068_b2217_x2103_b2220)), op=Bypass, results=List(CU.scalarOut(x2099_x2105_s)))
      Stage(operands=List(CU.load(x2068_b2218_x2103_b2221)), op=Bypass, results=List(CU.scalarOut(x2100_x2107_s)))
      Stage(operands=List(CU.load(x2068_b2216_x2103_b2219)), op=Bypass, results=List(CU.scalarOut(x2101_x2109_s)))
    }
    val x2126 = Pipeline(name="x2126",parent=x2127) { implicit CU => 
      val x2101_x2111 =  ScalarBuffer().wtPort(x2101_x2109_s)
      val x2100_x2115 =  ScalarBuffer().wtPort(x2100_x2107_s)
      val x2099_x2114 =  ScalarBuffer().wtPort(x2099_x2105_s)
      val ctr5 = Counter(min=Const(0), max=x2101_x2111.load, step=Const(1), par=1) // Counter
      val x2113 = CounterChain(name = "x2113", ctr5).iter(1)
      var stage: List[Stage] = Nil
    }
    val x2129_dsp0 = MemoryPipeline(name="x2129_dsp0",parent="x2160") { implicit CU => 
      val x2141_x2141 =  VectorFIFO(size=1).wtPort(x2129_x2141_v)
      val x2132 = CounterChain.copy("x2142_0", "x2132")
      val x2145 = CounterChain.copy("x2153", "x2145")
      val x2129_x2148 =  SRAM(size=8000,banking = NoBanking()).wtPort(x2141_x2141.readPort).rdAddr(x2145(0)).wtAddr(x2132(0))
      var stage: List[Stage] = Nil
    }
    val x2142_0 = Pipeline(name="x2142_0",parent=x2160) { implicit CU => 
      val x2137 = CU.temp
      val x2135 = CU.temp
      val x2136 = CU.temp
      val x2051_x2133 =  ScalarBuffer().wtPort(x2051_x2062_s)
      val x2049_x2130 =  ScalarBuffer().wtPort(x2049_x2060_s)
      val x2007_x2134 =  ScalarBuffer().wtPort(x2007_x2157_s)
      val x2048 = CounterChain.copy("x2160", "x2048")
      val ctr6 = Counter(min=Const(0), max=x2049_x2130.load, step=Const(1), par=1) // Counter
      val x2132 = CounterChain(name = "x2132", ctr6).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2048(0)), Const(0)), op=FixEql, results=List(x2135))
      Stage(operands=List(x2135, Const(0), CU.load(x2051_x2133)), op=Mux, results=List(x2136))
      Stage(operands=List(CU.ctr(x2132(0)), CU.load(x2007_x2134)), op=FixAdd, results=List(x2137))
      Stage(operands=List(x2137, x2136), op=FixAdd, results=List(CU.vecOut(x2129_x2141_v)))
    }
    val x2153 = Pipeline(name="x2153",parent=x2160) { implicit CU => 
      val x2049_x2143 =  ScalarBuffer().wtPort(x2049_x2060_s)
      val ctr7 = Counter(min=Const(0), max=x2049_x2143.load, step=Const(1), par=1) // Counter
      val x2145 = CounterChain(name = "x2145", ctr7).iter(1)
      var stage: List[Stage] = Nil
    }
    val x2158_0 = Pipeline(name="x2158_0",parent=x2160) { implicit CU => 
      val x2049_x2155 =  ScalarBuffer().wtPort(x2049_x2060_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2049_x2155)), op=Bypass, results=List(CU.reduce))
      val (_, rr341) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr341), op=Bypass, results=List(CU.scalarOut(x2007_x2157_s)))
    }
    val x2168 = Pipeline(name="x2168",parent=x2202) { implicit CU => 
      val x2007_x2161 =  ScalarBuffer().wtPort(x2007_x2157_s)
      val ctr8 = Counter(min=Const(0), max=x2007_x2161.load, step=Const(1), par=1) // Counter
      val x2163 = CounterChain(name = "x2163", ctr8).iter(1)
      var stage: List[Stage] = Nil
    }
    val x2175_0 = Pipeline(name="x2175_0",parent=x2202) { implicit CU => 
      val x2007_x2169 =  ScalarBuffer().wtPort(x2007_x2157_s)
      val x2045 = CounterChain.copy("x2202", "x2045")
      val ctr9 = Counter(min=Const(0), max=x2007_x2169.load, step=Const(1), par=1) // Counter
      val x2171 = CounterChain(name = "x2171", ctr9).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2045(0)), Const(1)), op=FixAdd, results=List(CU.vecOut(x2004_x2174_v)))
    }
    val x2198 = StreamController(name="x2198",parent=x2202) { implicit CU => 
    }
    val x2191_0 = Pipeline(name="x2191_0",parent=x2198) { implicit CU => 
      val x2181_x2181 =  VectorFIFO(size=1).wtPort(x2005_x2181_x2191_v)
      val ctr10 = Counter(min=Const(0), max=Const(8000), step=Const(1), par=1) // Counter
      val x2179 = CounterChain(name = "x2179", ctr10).iter(8000)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2181_x2181), Const(4)), op=FixMul, results=List())
    }
    val x2192 = MemoryController(name="x2192",parent=x2198,offchip=x1996_oc, mctpe=Scatter) { implicit CU => 
      val x2176_x2192 =  VectorFIFO(name="data",size=1).wtPort(x2004_x2183_x2191_v)
    }
    val x2201_0 = Pipeline(name="x2201_0",parent=x2202) { implicit CU => 
      val x2007_x2199 =  ScalarBuffer().wtPort(x2007_x2157_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2007_x2199)), op=Bypass, results=List(CU.scalarOut(x2008_x2200_s)))
    }
    
  }
}
