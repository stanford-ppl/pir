import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2121_b2379_x2129_b2381_s = Scalar("x2121_b2379_x2129_b2381")
    val x2065_argin = ArgIn("x2065")
    val x1985_b2352_x1993_b2354_s = Scalar("x1985_b2352_x1993_b2354")
    val x1966_x2330_argout = ArgOut("x1966_x2330")
    val x2043_b2363_x2051_b2365_s = Scalar("x2043_b2363_x2051_b2365")
    val x2004_b2356_x2012_b2358_s = Scalar("x2004_b2356_x2012_b2358")
    val x2064_x2073_data_v = Vector("x2064_x2073_data")
    val bus_894_s = Scalar("bus_894")
    val x2160_b2388_x2168_b2390_s = Scalar("x2160_b2388_x2168_b2390")
    val x1977_x2281_x2290_v = Vector("x1977_x2281_x2290")
    val x2004_b2355_x2012_b2357_s = Scalar("x2004_b2355_x2012_b2357")
    val x2082_b2371_x2090_b2373_s = Scalar("x2082_b2371_x2090_b2373")
    val x2006_argin = ArgIn("x2006")
    val x2141_b2383_x2149_b2385_s = Scalar("x2141_b2383_x2149_b2385")
    val x2026_argin = ArgIn("x2026")
    val x2063_b2368_x2071_b2370_s = Scalar("x2063_b2368_x2071_b2370")
    val x2181_x2190_data_v = Vector("x2181_x2190_data")
    val x2024_b2360_x2032_b2362_s = Scalar("x2024_b2360_x2032_b2362")
    val x2180_b2391_x2188_b2393_s = Scalar("x2180_b2391_x2188_b2393")
    val x2199_b2395_x2207_b2397_s = Scalar("x2199_b2395_x2207_b2397")
    val x2162_argin = ArgIn("x2162")
    val x2103_x2112_data_v = Vector("x2103_x2112_data")
    val x2143_argin = ArgIn("x2143")
    val x2082_b2372_x2090_b2374_s = Scalar("x2082_b2372_x2090_b2374")
    val x1974_x2242_x2251_v = Vector("x1974_x2242_x2251")
    val x2141_b2384_x2149_b2386_s = Scalar("x2141_b2384_x2149_b2386")
    val x2025_x2034_data_v = Vector("x2025_x2034_data")
    val x2044_x2053_data_v = Vector("x2044_x2053_data")
    val x2104_argin = ArgIn("x2104")
    val x2200_x2209_data_v = Vector("x2200_x2209_data")
    val x2102_b2375_x2110_b2377_s = Scalar("x2102_b2375_x2110_b2377")
    val x2221_x2249_s = Scalar("x2221_x2249")
    val x1965_oc = OffChip("x1965")
    val x1981_x2256_x2264_v = Vector("x1981_x2256_x2264")
    val x2122_x2131_data_v = Vector("x2122_x2131_data")
    val x1979_x2230_x2238_v = Vector("x1979_x2230_x2238")
    val x2182_argin = ArgIn("x2182")
    val x2005_x2014_data_v = Vector("x2005_x2014_data")
    val x2102_b2376_x2110_b2378_s = Scalar("x2102_b2376_x2110_b2378")
    val x1986_x1995_data_v = Vector("x1986_x1995_data")
    val x2223_x2275_s = Scalar("x2223_x2275")
    val x2201_argin = ArgIn("x2201")
    val x2199_b2396_x2207_b2398_s = Scalar("x2199_b2396_x2207_b2398")
    val x2142_x2151_data_v = Vector("x2142_x2151_data")
    val x1959_argin = ArgIn("x1959")
    val x1963_oc = OffChip("x1963")
    val x2224_x2288_s = Scalar("x2224_x2288")
    val x1978_x2294_x2303_v = Vector("x1978_x2294_x2303")
    val x1976_x2268_x2277_v = Vector("x1976_x2268_x2277")
    val x2222_x2262_s = Scalar("x2222_x2262")
    val x1983_x2282_x2290_v = Vector("x1983_x2282_x2290")
    val x1985_b2351_x1993_b2353_s = Scalar("x1985_b2351_x1993_b2353")
    val x1973_x2229_x2238_v = Vector("x1973_x2229_x2238")
    val x2024_b2359_x2032_b2361_s = Scalar("x2024_b2359_x2032_b2361")
    val x2045_argin = ArgIn("x2045")
    val x2123_argin = ArgIn("x2123")
    val x1980_x2243_x2251_v = Vector("x1980_x2243_x2251")
    val x2043_b2364_x2051_b2366_s = Scalar("x2043_b2364_x2051_b2366")
    val x1984_x2295_x2303_v = Vector("x1984_x2295_x2303")
    val x2160_b2387_x2168_b2389_s = Scalar("x2160_b2387_x2168_b2389")
    val x1975_x2255_x2264_v = Vector("x1975_x2255_x2264")
    val x2063_b2367_x2071_b2369_s = Scalar("x2063_b2367_x2071_b2369")
    val x1982_x2269_x2277_v = Vector("x1982_x2269_x2277")
    val x2084_argin = ArgIn("x2084")
    val x2161_x2170_data_v = Vector("x2161_x2170_data")
    val x2180_b2392_x2188_b2394_s = Scalar("x2180_b2392_x2188_b2394")
    val x2121_b2380_x2129_b2382_s = Scalar("x2121_b2380_x2129_b2382")
    val x2083_x2092_data_v = Vector("x2083_x2092_data")
    val bus_897_s = Scalar("bus_897")
    val x2225_x2301_s = Scalar("x2225_x2301")
    val x1987_argin = ArgIn("x1987")
    val x2220_x2236_s = Scalar("x2220_x2236")
    val x2332 = Sequential(name="x2332",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2332_unit = CounterChain(name = "x2332_unit", ctr1)
    }
    val x2328 = MetaPipeline(name="x2328",parent=x2332) { implicit CU => 
      val x1959_x1970 =  ScalarBuffer().wtPort(x1959_argin)
      val ctr2 = Counter(min=Const(0), max=x1959_x1970.load, step=Const(320), par=6) // Counter
      val x1972 = CounterChain(name = "x1972", ctr2)
    }
    val x1973_dsp0 = MemoryPipeline(name="x1973_dsp0",parent="x2328") { implicit CU => 
      val x2001_x2001 =  VectorFIFO(size=1).wtPort(x1986_x1995_data_v)
      val x1997 = CounterChain.copy("x2002", "x1997")
      val x2227 = CounterChain.copy("x2238_0", "x2227")
      val x1973_x2229 =  SRAM(size=320,banking = Strided(1)).wtPort(x2001_x2001.readPort).rdPort(x1973_x2229_x2238_v).rdAddr(x2227(0)).wtAddr(x1997(0))
      var stage: List[Stage] = Nil
    }
    val x1974_dsp0 = MemoryPipeline(name="x1974_dsp0",parent="x2328") { implicit CU => 
      val x2040_x2040 =  VectorFIFO(size=1).wtPort(x2025_x2034_data_v)
      val x2036 = CounterChain.copy("x2041", "x2036")
      val x2240 = CounterChain.copy("x2251_0", "x2240")
      val x1974_x2242 =  SRAM(size=320,banking = Strided(1)).wtPort(x2040_x2040.readPort).rdPort(x1974_x2242_x2251_v).rdAddr(x2240(0)).wtAddr(x2036(0))
      var stage: List[Stage] = Nil
    }
    val x1975_dsp0 = MemoryPipeline(name="x1975_dsp0",parent="x2328") { implicit CU => 
      val x2079_x2079 =  VectorFIFO(size=1).wtPort(x2064_x2073_data_v)
      val x2075 = CounterChain.copy("x2080", "x2075")
      val x2253 = CounterChain.copy("x2264_0", "x2253")
      val x1975_x2255 =  SRAM(size=320,banking = Strided(1)).wtPort(x2079_x2079.readPort).rdPort(x1975_x2255_x2264_v).rdAddr(x2253(0)).wtAddr(x2075(0))
      var stage: List[Stage] = Nil
    }
    val x1976_dsp0 = MemoryPipeline(name="x1976_dsp0",parent="x2328") { implicit CU => 
      val x2118_x2118 =  VectorFIFO(size=1).wtPort(x2103_x2112_data_v)
      val x2114 = CounterChain.copy("x2119", "x2114")
      val x2266 = CounterChain.copy("x2277_0", "x2266")
      val x1976_x2268 =  SRAM(size=320,banking = Strided(1)).wtPort(x2118_x2118.readPort).rdPort(x1976_x2268_x2277_v).rdAddr(x2266(0)).wtAddr(x2114(0))
      var stage: List[Stage] = Nil
    }
    val x1977_dsp0 = MemoryPipeline(name="x1977_dsp0",parent="x2328") { implicit CU => 
      val x2157_x2157 =  VectorFIFO(size=1).wtPort(x2142_x2151_data_v)
      val x2153 = CounterChain.copy("x2158", "x2153")
      val x2279 = CounterChain.copy("x2290_0", "x2279")
      val x1977_x2281 =  SRAM(size=320,banking = Strided(1)).wtPort(x2157_x2157.readPort).rdPort(x1977_x2281_x2290_v).rdAddr(x2279(0)).wtAddr(x2153(0))
      var stage: List[Stage] = Nil
    }
    val x1978_dsp0 = MemoryPipeline(name="x1978_dsp0",parent="x2328") { implicit CU => 
      val x2196_x2196 =  VectorFIFO(size=1).wtPort(x2181_x2190_data_v)
      val x2192 = CounterChain.copy("x2197", "x2192")
      val x2292 = CounterChain.copy("x2303_0", "x2292")
      val x1978_x2294 =  SRAM(size=320,banking = Strided(1)).wtPort(x2196_x2196.readPort).rdPort(x1978_x2294_x2303_v).rdAddr(x2292(0)).wtAddr(x2192(0))
      var stage: List[Stage] = Nil
    }
    val x1979_dsp0 = MemoryPipeline(name="x1979_dsp0",parent="x2328") { implicit CU => 
      val x2020_x2020 =  VectorFIFO(size=1).wtPort(x2005_x2014_data_v)
      val x2016 = CounterChain.copy("x2021", "x2016")
      val x2227 = CounterChain.copy("x2238_0", "x2227")
      val x1979_x2230 =  SRAM(size=320,banking = Strided(1)).wtPort(x2020_x2020.readPort).rdPort(x1979_x2230_x2238_v).rdAddr(x2227(0)).wtAddr(x2016(0))
      var stage: List[Stage] = Nil
    }
    val x1980_dsp0 = MemoryPipeline(name="x1980_dsp0",parent="x2328") { implicit CU => 
      val x2059_x2059 =  VectorFIFO(size=1).wtPort(x2044_x2053_data_v)
      val x2055 = CounterChain.copy("x2060", "x2055")
      val x2240 = CounterChain.copy("x2251_0", "x2240")
      val x1980_x2243 =  SRAM(size=320,banking = Strided(1)).wtPort(x2059_x2059.readPort).rdPort(x1980_x2243_x2251_v).rdAddr(x2240(0)).wtAddr(x2055(0))
      var stage: List[Stage] = Nil
    }
    val x1981_dsp0 = MemoryPipeline(name="x1981_dsp0",parent="x2328") { implicit CU => 
      val x2098_x2098 =  VectorFIFO(size=1).wtPort(x2083_x2092_data_v)
      val x2094 = CounterChain.copy("x2099", "x2094")
      val x2253 = CounterChain.copy("x2264_0", "x2253")
      val x1981_x2256 =  SRAM(size=320,banking = Strided(1)).wtPort(x2098_x2098.readPort).rdPort(x1981_x2256_x2264_v).rdAddr(x2253(0)).wtAddr(x2094(0))
      var stage: List[Stage] = Nil
    }
    val x1982_dsp0 = MemoryPipeline(name="x1982_dsp0",parent="x2328") { implicit CU => 
      val x2137_x2137 =  VectorFIFO(size=1).wtPort(x2122_x2131_data_v)
      val x2133 = CounterChain.copy("x2138", "x2133")
      val x2266 = CounterChain.copy("x2277_0", "x2266")
      val x1982_x2269 =  SRAM(size=320,banking = Strided(1)).wtPort(x2137_x2137.readPort).rdPort(x1982_x2269_x2277_v).rdAddr(x2266(0)).wtAddr(x2133(0))
      var stage: List[Stage] = Nil
    }
    val x1983_dsp0 = MemoryPipeline(name="x1983_dsp0",parent="x2328") { implicit CU => 
      val x2176_x2176 =  VectorFIFO(size=1).wtPort(x2161_x2170_data_v)
      val x2172 = CounterChain.copy("x2177", "x2172")
      val x2279 = CounterChain.copy("x2290_0", "x2279")
      val x1983_x2282 =  SRAM(size=320,banking = Strided(1)).wtPort(x2176_x2176.readPort).rdPort(x1983_x2282_x2290_v).rdAddr(x2279(0)).wtAddr(x2172(0))
      var stage: List[Stage] = Nil
    }
    val x1984_dsp0 = MemoryPipeline(name="x1984_dsp0",parent="x2328") { implicit CU => 
      val x2215_x2215 =  VectorFIFO(size=1).wtPort(x2200_x2209_data_v)
      val x2211 = CounterChain.copy("x2216", "x2211")
      val x2292 = CounterChain.copy("x2303_0", "x2292")
      val x1984_x2295 =  SRAM(size=320,banking = Strided(1)).wtPort(x2215_x2215.readPort).rdPort(x1984_x2295_x2303_v).rdAddr(x2292(0)).wtAddr(x2211(0))
      var stage: List[Stage] = Nil
    }
    val x2003 = StreamController(name="x2003",parent=x2328) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2003_unit = CounterChain(name = "x2003_unit", ctr3)
    }
    val x1994_0 = Pipeline(name="x1994_0",parent=x2003) { implicit CU => 
      val x1988 = CU.temp
      val x1987 =  ScalarBuffer().wtPort(x1987_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1994_unit = CounterChain(name = "x1994_unit", ctr4)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x1988))
      Stage(operands=List(x1988, CU.load(x1987)), op=FixAdd, results=List(CU.scalarOut(x1985_b2351_x1993_b2353_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1985_b2352_x1993_b2354_s)))
    }
    val x1995 = MemoryController(name="x1995",parent=x2003,offchip=x1963_oc, mctpe=TileLoad) { implicit CU => 
      val x1985_b2352_x1995 =  ScalarFIFO(name="size",size=1).wtPort(x1985_b2352_x1993_b2354_s)
      val x1985_b2351_x1995 =  ScalarFIFO(name="offset",size=1).wtPort(x1985_b2351_x1993_b2353_s)
      CU.newVout("data", x1986_x1995_data_v)
    }
    val x2002 = Pipeline(name="x2002",parent=x2003) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1997 = CounterChain(name = "x1997", ctr5)
      var stage: List[Stage] = Nil
    }
    val x2022 = StreamController(name="x2022",parent=x2328) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2022_unit = CounterChain(name = "x2022_unit", ctr6)
    }
    val x2013_0 = Pipeline(name="x2013_0",parent=x2022) { implicit CU => 
      val x2007 = CU.temp
      val x2006 =  ScalarBuffer().wtPort(x2006_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2013_unit = CounterChain(name = "x2013_unit", ctr7)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2007))
      Stage(operands=List(x2007, CU.load(x2006)), op=FixAdd, results=List(CU.scalarOut(x2004_b2355_x2012_b2357_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2004_b2356_x2012_b2358_s)))
    }
    val x2014 = MemoryController(name="x2014",parent=x2022,offchip=x1965_oc, mctpe=TileLoad) { implicit CU => 
      val x2004_b2356_x2014 =  ScalarFIFO(name="size",size=1).wtPort(x2004_b2356_x2012_b2358_s)
      val x2004_b2355_x2014 =  ScalarFIFO(name="offset",size=1).wtPort(x2004_b2355_x2012_b2357_s)
      CU.newVout("data", x2005_x2014_data_v)
    }
    val x2021 = Pipeline(name="x2021",parent=x2022) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2016 = CounterChain(name = "x2016", ctr8)
      var stage: List[Stage] = Nil
    }
    val x2042 = StreamController(name="x2042",parent=x2328) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2042_unit = CounterChain(name = "x2042_unit", ctr9)
    }
    val x2033_0 = Pipeline(name="x2033_0",parent=x2042) { implicit CU => 
      val x2027 = CU.temp
      val x2026 =  ScalarBuffer().wtPort(x2026_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2033_unit = CounterChain(name = "x2033_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2027))
      Stage(operands=List(x2027, CU.load(x2026)), op=FixAdd, results=List(CU.scalarOut(x2024_b2359_x2032_b2361_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2024_b2360_x2032_b2362_s)))
    }
    val x2034 = MemoryController(name="x2034",parent=x2042,offchip=x1963_oc, mctpe=TileLoad) { implicit CU => 
      val x2024_b2359_x2034 =  ScalarFIFO(name="offset",size=1).wtPort(x2024_b2359_x2032_b2361_s)
      val x2024_b2360_x2034 =  ScalarFIFO(name="size",size=1).wtPort(x2024_b2360_x2032_b2362_s)
      CU.newVout("data", x2025_x2034_data_v)
    }
    val x2041 = Pipeline(name="x2041",parent=x2042) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2036 = CounterChain(name = "x2036", ctr11)
      var stage: List[Stage] = Nil
    }
    val x2061 = StreamController(name="x2061",parent=x2328) { implicit CU => 
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2061_unit = CounterChain(name = "x2061_unit", ctr12)
    }
    val x2052_0 = Pipeline(name="x2052_0",parent=x2061) { implicit CU => 
      val x2046 = CU.temp
      val x2045 =  ScalarBuffer().wtPort(x2045_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2052_unit = CounterChain(name = "x2052_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2046))
      Stage(operands=List(x2046, CU.load(x2045)), op=FixAdd, results=List(CU.scalarOut(x2043_b2363_x2051_b2365_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2043_b2364_x2051_b2366_s)))
    }
    val x2053 = MemoryController(name="x2053",parent=x2061,offchip=x1965_oc, mctpe=TileLoad) { implicit CU => 
      val x2043_b2364_x2053 =  ScalarFIFO(name="size",size=1).wtPort(x2043_b2364_x2051_b2366_s)
      val x2043_b2363_x2053 =  ScalarFIFO(name="offset",size=1).wtPort(x2043_b2363_x2051_b2365_s)
      CU.newVout("data", x2044_x2053_data_v)
    }
    val x2060 = Pipeline(name="x2060",parent=x2061) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2055 = CounterChain(name = "x2055", ctr14)
      var stage: List[Stage] = Nil
    }
    val x2081 = StreamController(name="x2081",parent=x2328) { implicit CU => 
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2081_unit = CounterChain(name = "x2081_unit", ctr15)
    }
    val x2072_0 = Pipeline(name="x2072_0",parent=x2081) { implicit CU => 
      val x2066 = CU.temp
      val x2065 =  ScalarBuffer().wtPort(x2065_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2072_unit = CounterChain(name = "x2072_unit", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2066))
      Stage(operands=List(x2066, CU.load(x2065)), op=FixAdd, results=List(CU.scalarOut(x2063_b2367_x2071_b2369_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2063_b2368_x2071_b2370_s)))
    }
    val x2073 = MemoryController(name="x2073",parent=x2081,offchip=x1963_oc, mctpe=TileLoad) { implicit CU => 
      val x2063_b2368_x2073 =  ScalarFIFO(name="size",size=1).wtPort(x2063_b2368_x2071_b2370_s)
      val x2063_b2367_x2073 =  ScalarFIFO(name="offset",size=1).wtPort(x2063_b2367_x2071_b2369_s)
      CU.newVout("data", x2064_x2073_data_v)
    }
    val x2080 = Pipeline(name="x2080",parent=x2081) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2075 = CounterChain(name = "x2075", ctr17)
      var stage: List[Stage] = Nil
    }
    val x2100 = StreamController(name="x2100",parent=x2328) { implicit CU => 
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2100_unit = CounterChain(name = "x2100_unit", ctr18)
    }
    val x2091_0 = Pipeline(name="x2091_0",parent=x2100) { implicit CU => 
      val x2085 = CU.temp
      val x2084 =  ScalarBuffer().wtPort(x2084_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2091_unit = CounterChain(name = "x2091_unit", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2085))
      Stage(operands=List(x2085, CU.load(x2084)), op=FixAdd, results=List(CU.scalarOut(x2082_b2371_x2090_b2373_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2082_b2372_x2090_b2374_s)))
    }
    val x2092 = MemoryController(name="x2092",parent=x2100,offchip=x1965_oc, mctpe=TileLoad) { implicit CU => 
      val x2082_b2371_x2092 =  ScalarFIFO(name="offset",size=1).wtPort(x2082_b2371_x2090_b2373_s)
      val x2082_b2372_x2092 =  ScalarFIFO(name="size",size=1).wtPort(x2082_b2372_x2090_b2374_s)
      CU.newVout("data", x2083_x2092_data_v)
    }
    val x2099 = Pipeline(name="x2099",parent=x2100) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2094 = CounterChain(name = "x2094", ctr20)
      var stage: List[Stage] = Nil
    }
    val x2120 = StreamController(name="x2120",parent=x2328) { implicit CU => 
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2120_unit = CounterChain(name = "x2120_unit", ctr21)
    }
    val x2111_0 = Pipeline(name="x2111_0",parent=x2120) { implicit CU => 
      val x2105 = CU.temp
      val x2104 =  ScalarBuffer().wtPort(x2104_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2111_unit = CounterChain(name = "x2111_unit", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2105))
      Stage(operands=List(x2105, CU.load(x2104)), op=FixAdd, results=List(CU.scalarOut(x2102_b2375_x2110_b2377_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2102_b2376_x2110_b2378_s)))
    }
    val x2112 = MemoryController(name="x2112",parent=x2120,offchip=x1963_oc, mctpe=TileLoad) { implicit CU => 
      val x2102_b2376_x2112 =  ScalarFIFO(name="size",size=1).wtPort(x2102_b2376_x2110_b2378_s)
      val x2102_b2375_x2112 =  ScalarFIFO(name="offset",size=1).wtPort(x2102_b2375_x2110_b2377_s)
      CU.newVout("data", x2103_x2112_data_v)
    }
    val x2119 = Pipeline(name="x2119",parent=x2120) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2114 = CounterChain(name = "x2114", ctr23)
      var stage: List[Stage] = Nil
    }
    val x2139 = StreamController(name="x2139",parent=x2328) { implicit CU => 
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2139_unit = CounterChain(name = "x2139_unit", ctr24)
    }
    val x2130_0 = Pipeline(name="x2130_0",parent=x2139) { implicit CU => 
      val x2124 = CU.temp
      val x2123 =  ScalarBuffer().wtPort(x2123_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2130_unit = CounterChain(name = "x2130_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2124))
      Stage(operands=List(x2124, CU.load(x2123)), op=FixAdd, results=List(CU.scalarOut(x2121_b2379_x2129_b2381_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2121_b2380_x2129_b2382_s)))
    }
    val x2131 = MemoryController(name="x2131",parent=x2139,offchip=x1965_oc, mctpe=TileLoad) { implicit CU => 
      val x2121_b2380_x2131 =  ScalarFIFO(name="size",size=1).wtPort(x2121_b2380_x2129_b2382_s)
      val x2121_b2379_x2131 =  ScalarFIFO(name="offset",size=1).wtPort(x2121_b2379_x2129_b2381_s)
      CU.newVout("data", x2122_x2131_data_v)
    }
    val x2138 = Pipeline(name="x2138",parent=x2139) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2133 = CounterChain(name = "x2133", ctr26)
      var stage: List[Stage] = Nil
    }
    val x2159 = StreamController(name="x2159",parent=x2328) { implicit CU => 
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2159_unit = CounterChain(name = "x2159_unit", ctr27)
    }
    val x2150_0 = Pipeline(name="x2150_0",parent=x2159) { implicit CU => 
      val x2144 = CU.temp
      val x2143 =  ScalarBuffer().wtPort(x2143_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2150_unit = CounterChain(name = "x2150_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2144))
      Stage(operands=List(x2144, CU.load(x2143)), op=FixAdd, results=List(CU.scalarOut(x2141_b2383_x2149_b2385_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2141_b2384_x2149_b2386_s)))
    }
    val x2151 = MemoryController(name="x2151",parent=x2159,offchip=x1963_oc, mctpe=TileLoad) { implicit CU => 
      val x2141_b2383_x2151 =  ScalarFIFO(name="offset",size=1).wtPort(x2141_b2383_x2149_b2385_s)
      val x2141_b2384_x2151 =  ScalarFIFO(name="size",size=1).wtPort(x2141_b2384_x2149_b2386_s)
      CU.newVout("data", x2142_x2151_data_v)
    }
    val x2158 = Pipeline(name="x2158",parent=x2159) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2153 = CounterChain(name = "x2153", ctr29)
      var stage: List[Stage] = Nil
    }
    val x2178 = StreamController(name="x2178",parent=x2328) { implicit CU => 
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2178_unit = CounterChain(name = "x2178_unit", ctr30)
    }
    val x2169_0 = Pipeline(name="x2169_0",parent=x2178) { implicit CU => 
      val x2163 = CU.temp
      val x2162 =  ScalarBuffer().wtPort(x2162_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2169_unit = CounterChain(name = "x2169_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2163))
      Stage(operands=List(x2163, CU.load(x2162)), op=FixAdd, results=List(CU.scalarOut(x2160_b2387_x2168_b2389_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2160_b2388_x2168_b2390_s)))
    }
    val x2170 = MemoryController(name="x2170",parent=x2178,offchip=x1965_oc, mctpe=TileLoad) { implicit CU => 
      val x2160_b2388_x2170 =  ScalarFIFO(name="size",size=1).wtPort(x2160_b2388_x2168_b2390_s)
      val x2160_b2387_x2170 =  ScalarFIFO(name="offset",size=1).wtPort(x2160_b2387_x2168_b2389_s)
      CU.newVout("data", x2161_x2170_data_v)
    }
    val x2177 = Pipeline(name="x2177",parent=x2178) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2172 = CounterChain(name = "x2172", ctr32)
      var stage: List[Stage] = Nil
    }
    val x2198 = StreamController(name="x2198",parent=x2328) { implicit CU => 
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2198_unit = CounterChain(name = "x2198_unit", ctr33)
    }
    val x2189_0 = Pipeline(name="x2189_0",parent=x2198) { implicit CU => 
      val x2183 = CU.temp
      val x2182 =  ScalarBuffer().wtPort(x2182_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2189_unit = CounterChain(name = "x2189_unit", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2183))
      Stage(operands=List(x2183, CU.load(x2182)), op=FixAdd, results=List(CU.scalarOut(x2180_b2391_x2188_b2393_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2180_b2392_x2188_b2394_s)))
    }
    val x2190 = MemoryController(name="x2190",parent=x2198,offchip=x1963_oc, mctpe=TileLoad) { implicit CU => 
      val x2180_b2392_x2190 =  ScalarFIFO(name="size",size=1).wtPort(x2180_b2392_x2188_b2394_s)
      val x2180_b2391_x2190 =  ScalarFIFO(name="offset",size=1).wtPort(x2180_b2391_x2188_b2393_s)
      CU.newVout("data", x2181_x2190_data_v)
    }
    val x2197 = Pipeline(name="x2197",parent=x2198) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2192 = CounterChain(name = "x2192", ctr35)
      var stage: List[Stage] = Nil
    }
    val x2217 = StreamController(name="x2217",parent=x2328) { implicit CU => 
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2217_unit = CounterChain(name = "x2217_unit", ctr36)
    }
    val x2208_0 = Pipeline(name="x2208_0",parent=x2217) { implicit CU => 
      val x2202 = CU.temp
      val x2201 =  ScalarBuffer().wtPort(x2201_argin)
      val x1972 = CounterChain.copy("x2328", "x1972")
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2208_unit = CounterChain(name = "x2208_unit", ctr37)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1972(0)), Const(4)), op=FixMul, results=List(x2202))
      Stage(operands=List(x2202, CU.load(x2201)), op=FixAdd, results=List(CU.scalarOut(x2199_b2395_x2207_b2397_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x2199_b2396_x2207_b2398_s)))
    }
    val x2209 = MemoryController(name="x2209",parent=x2217,offchip=x1965_oc, mctpe=TileLoad) { implicit CU => 
      val x2199_b2396_x2209 =  ScalarFIFO(name="size",size=1).wtPort(x2199_b2396_x2207_b2398_s)
      val x2199_b2395_x2209 =  ScalarFIFO(name="offset",size=1).wtPort(x2199_b2395_x2207_b2397_s)
      CU.newVout("data", x2200_x2209_data_v)
    }
    val x2216 = Pipeline(name="x2216",parent=x2217) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2211 = CounterChain(name = "x2211", ctr38)
      var stage: List[Stage] = Nil
    }
    val x2238_0 = Pipeline(name="x2238_0",parent=x2328) { implicit CU => 
      val x1979_x2230 =  VectorFIFO(size=1).wtPort(x1979_x2230_x2238_v)
      val x1973_x2229 =  VectorFIFO(size=1).wtPort(x1973_x2229_x2238_v)
      val ctr39 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2227 = CounterChain(name = "x2227", ctr39)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1973_x2229), CU.load(x1979_x2230)), op=FixMul, results=List(CU.reduce))
      val (_, rr864) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr864), op=Bypass, results=List(CU.scalarOut(x2220_x2236_s)))
    }
    val x2251_0 = Pipeline(name="x2251_0",parent=x2328) { implicit CU => 
      val x1980_x2243 =  VectorFIFO(size=1).wtPort(x1980_x2243_x2251_v)
      val x1974_x2242 =  VectorFIFO(size=1).wtPort(x1974_x2242_x2251_v)
      val ctr40 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2240 = CounterChain(name = "x2240", ctr40)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1974_x2242), CU.load(x1980_x2243)), op=FixMul, results=List(CU.reduce))
      val (_, rr869) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr869), op=Bypass, results=List(CU.scalarOut(x2221_x2249_s)))
    }
    val x2264_0 = Pipeline(name="x2264_0",parent=x2328) { implicit CU => 
      val x1975_x2255 =  VectorFIFO(size=1).wtPort(x1975_x2255_x2264_v)
      val x1981_x2256 =  VectorFIFO(size=1).wtPort(x1981_x2256_x2264_v)
      val ctr41 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2253 = CounterChain(name = "x2253", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1975_x2255), CU.load(x1981_x2256)), op=FixMul, results=List(CU.reduce))
      val (_, rr874) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr874), op=Bypass, results=List(CU.scalarOut(x2222_x2262_s)))
    }
    val x2277_0 = Pipeline(name="x2277_0",parent=x2328) { implicit CU => 
      val x1982_x2269 =  VectorFIFO(size=1).wtPort(x1982_x2269_x2277_v)
      val x1976_x2268 =  VectorFIFO(size=1).wtPort(x1976_x2268_x2277_v)
      val ctr42 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2266 = CounterChain(name = "x2266", ctr42)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1976_x2268), CU.load(x1982_x2269)), op=FixMul, results=List(CU.reduce))
      val (_, rr879) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr879), op=Bypass, results=List(CU.scalarOut(x2223_x2275_s)))
    }
    val x2290_0 = Pipeline(name="x2290_0",parent=x2328) { implicit CU => 
      val x1983_x2282 =  VectorFIFO(size=1).wtPort(x1983_x2282_x2290_v)
      val x1977_x2281 =  VectorFIFO(size=1).wtPort(x1977_x2281_x2290_v)
      val ctr43 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2279 = CounterChain(name = "x2279", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1977_x2281), CU.load(x1983_x2282)), op=FixMul, results=List(CU.reduce))
      val (_, rr884) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr884), op=Bypass, results=List(CU.scalarOut(x2224_x2288_s)))
    }
    val x2303_0 = Pipeline(name="x2303_0",parent=x2328) { implicit CU => 
      val x1984_x2295 =  VectorFIFO(size=1).wtPort(x1984_x2295_x2303_v)
      val x1978_x2294 =  VectorFIFO(size=1).wtPort(x1978_x2294_x2303_v)
      val ctr44 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x2292 = CounterChain(name = "x2292", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1978_x2294), CU.load(x1984_x2295)), op=FixMul, results=List(CU.reduce))
      val (_, rr889) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr889), op=Bypass, results=List(CU.scalarOut(x2225_x2301_s)))
    }
    val x2326 = StreamController(name="x2326",parent=x2328) { implicit CU => 
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2326_unit = CounterChain(name = "x2326_unit", ctr45)
    }
    val x2326_0 = Pipeline(name="x2326_0",parent=x2326) { implicit CU => 
      val x2313 = CU.temp
      val x2316 = CU.temp
      val x2221_x2306 =  ScalarBuffer().wtPort(x2221_x2249_s)
      val x2220_x2307 =  ScalarBuffer().wtPort(x2220_x2236_s)
      val x2223_x2308 =  ScalarBuffer().wtPort(x2223_x2275_s)
      val x2222_x2309 =  ScalarBuffer().wtPort(x2222_x2262_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2220_x2307), CU.load(x2221_x2306)), op=FixAdd, results=List(x2313))
      Stage(operands=List(CU.load(x2222_x2309), CU.load(x2223_x2308)), op=FixAdd, results=List(x2316))
      Stage(operands=List(x2313, x2316), op=FixAdd, results=List(CU.scalarOut(bus_894_s)))
    }
    val x2326_1 = Pipeline(name="x2326_1",parent=x2326) { implicit CU => 
      val x2320 = CU.temp
      val x2224_x2311 =  ScalarBuffer().wtPort(x2224_x2288_s)
      val x2318 =  ScalarFIFO(size=1).wtPort(bus_894_s)
      val x2225_x2310 =  ScalarBuffer().wtPort(x2225_x2301_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2224_x2311), CU.load(x2225_x2310)), op=FixAdd, results=List(x2320))
      Stage(operands=List(CU.load(x2318), x2320), op=FixAdd, results=List(CU.scalarOut(bus_897_s)))
    }
    val x2326_2 = Pipeline(name="x2326_2",parent=x2326) { implicit CU => 
      val rr897 =  ScalarFIFO(size=1).wtPort(bus_897_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr897)), op=Bypass, results=List(CU.reduce))
      val (_, rr899) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr899), op=Bypass, results=List(CU.scalarOut(x1966_x2330_argout)))
    }
    
  }
}
