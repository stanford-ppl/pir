import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object TPCHQ6 extends PIRApp {
  def main(top:Top) = {
    val discts_oc = OffChip("discts")
    val x2014_b2272_x2023_b2274_s = Scalar("x2014_b2272_x2023_b2274")
    val x2137_b2296_x2146_b2298_s = Scalar("x2137_b2296_x2146_b2298")
    val x2009_x2009_dsp0_bank0_s = Scalar("x2009_x2009_dsp0_bank0")
    val bus_370_v = Vector("bus_370")
    val x2004_x2004_dsp0_bank0_s = Scalar("x2004_x2004_dsp0_bank0")
    val bus_378_v = Vector("bus_378")
    val x2055_x2065_data_s = Scalar("x2055_x2065_data")
    val x2157_b2301_x2166_b2303_s = Scalar("x2157_b2301_x2166_b2303")
    val x2010_x2010_dsp0_bank0_s = Scalar("x2010_x2010_dsp0_bank0")
    val x2097_b2288_x2106_b2290_s = Scalar("x2097_b2288_x2106_b2290")
    val x2034_b2276_x2043_b2278_s = Scalar("x2034_b2276_x2043_b2278")
    val x2138_x2148_data_s = Scalar("x2138_x2148_data")
    val x2054_b2280_x2063_b2282_s = Scalar("x2054_b2280_x2063_b2282")
    val bus_388_v = Vector("bus_388")
    val x2074_b2284_x2083_b2286_s = Scalar("x2074_b2284_x2083_b2286")
    val x2179_x2205_s = Scalar("x2179_x2205")
    val x2118_x2128_data_s = Scalar("x2118_x2128_data")
    val x2117_b2292_x2126_b2294_s = Scalar("x2117_b2292_x2126_b2294")
    val x2014_b2273_x2023_b2275_s = Scalar("x2014_b2273_x2023_b2275")
    val x2074_b2285_x2083_b2287_s = Scalar("x2074_b2285_x2083_b2287")
    val x2097_b2289_x2106_b2291_s = Scalar("x2097_b2289_x2106_b2291")
    val x2008_x2008_dsp0_bank0_s = Scalar("x2008_x2008_dsp0_bank0")
    val quants_da = DRAMAddress("quants", "quants")
    val dates_oc = OffChip("dates")
    val x2137_b2297_x2146_b2299_s = Scalar("x2137_b2297_x2146_b2299")
    val x2117_b2293_x2126_b2295_s = Scalar("x2117_b2293_x2126_b2295")
    val x2158_x2168_data_s = Scalar("x2158_x2168_data")
    val bus_387_v = Vector("bus_387")
    val discts_da = DRAMAddress("discts", "discts")
    val x2006_x2006_dsp0_bank0_s = Scalar("x2006_x2006_dsp0_bank0")
    val x2180_x2231_s = Scalar("x2180_x2231")
    val bus_371_v = Vector("bus_371")
    val x2034_b2277_x2043_b2279_s = Scalar("x2034_b2277_x2043_b2279")
    val dataSize_argin = ArgIn("dataSize")
    val prices_da = DRAMAddress("prices", "prices")
    val x2007_x2007_dsp0_bank0_s = Scalar("x2007_x2007_dsp0_bank0")
    val prices_oc = OffChip("prices")
    val x2015_x2025_data_s = Scalar("x2015_x2025_data")
    val x2005_x2005_dsp0_bank0_s = Scalar("x2005_x2005_dsp0_bank0")
    val x2098_x2108_data_s = Scalar("x2098_x2108_data")
    val bus_395_v = Vector("bus_395")
    val dates_da = DRAMAddress("dates", "dates")
    val quants_oc = OffChip("quants")
    val x2075_x2085_data_s = Scalar("x2075_x2085_data")
    val x2054_b2281_x2063_b2283_s = Scalar("x2054_b2281_x2063_b2283")
    val x2011_x2011_dsp0_bank0_s = Scalar("x2011_x2011_dsp0_bank0")
    val x2157_b2300_x2166_b2302_s = Scalar("x2157_b2300_x2166_b2302")
    val x1995_x2246_argout = ArgOut("x1995_x2246")
    val x2035_x2045_data_s = Scalar("x2035_x2045_data")
    val x2248 = Sequential(name="x2248",parent=top) { implicit CU => 
      val x2248_unit = CounterChain(name = "x2248_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2244 = MetaPipeline(name="x2244",parent=x2248) { implicit CU => 
      val x1985 = ScalarBuffer(name="x1985").wtPort(dataSize_argin)
      val ctr1 = Counter(min=Const(0), max=x1985.readPort, step=Const(384), par=2) // Counter
      val x2003 = CounterChain(name = "x2003", ctr1).iter(1)
    }
    val x2004_dsp0_bank0 = MemoryPipeline(name="x2004_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2031 = ScalarFIFO(size=1,name="x2031").wtPort(x2015_x2025_data_s)
      val x2027 = CounterChain.copy("x2032", "x2027")
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2004 = SRAM(size=384,name="x2004",banking = Strided(1)).wtPort(x2031.readPort).rdPort(x2004_x2004_dsp0_bank0_s).rdAddr(x2182(0)).wtAddr(x2027(0))
    }
    val x2005_dsp0_bank0 = MemoryPipeline(name="x2005_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2114 = ScalarFIFO(size=1,name="x2114").wtPort(x2098_x2108_data_s)
      val x2110 = CounterChain.copy("x2115", "x2110")
      val x2208 = CounterChain.copy("x2232", "x2208")
      val x2005 = SRAM(size=384,name="x2005",banking = Strided(1)).wtPort(x2114.readPort).rdPort(x2005_x2005_dsp0_bank0_s).rdAddr(x2208(0)).wtAddr(x2110(0))
    }
    val x2006_dsp0_bank0 = MemoryPipeline(name="x2006_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2051 = ScalarFIFO(size=1,name="x2051").wtPort(x2035_x2045_data_s)
      val x2047 = CounterChain.copy("x2052", "x2047")
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2006 = SRAM(size=384,name="x2006",banking = Strided(1)).wtPort(x2051.readPort).rdPort(x2006_x2006_dsp0_bank0_s).rdAddr(x2182(0)).wtAddr(x2047(0))
    }
    val x2007_dsp0_bank0 = MemoryPipeline(name="x2007_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2134 = ScalarFIFO(size=1,name="x2134").wtPort(x2118_x2128_data_s)
      val x2130 = CounterChain.copy("x2135", "x2130")
      val x2208 = CounterChain.copy("x2232", "x2208")
      val x2007 = SRAM(size=384,name="x2007",banking = Strided(1)).wtPort(x2134.readPort).rdPort(x2007_x2007_dsp0_bank0_s).rdAddr(x2208(0)).wtAddr(x2130(0))
    }
    val x2008_dsp0_bank0 = MemoryPipeline(name="x2008_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2071 = ScalarFIFO(size=1,name="x2071").wtPort(x2055_x2065_data_s)
      val x2067 = CounterChain.copy("x2072", "x2067")
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2008 = SRAM(size=384,name="x2008",banking = Strided(1)).wtPort(x2071.readPort).rdPort(x2008_x2008_dsp0_bank0_s).rdAddr(x2182(0)).wtAddr(x2067(0))
    }
    val x2009_dsp0_bank0 = MemoryPipeline(name="x2009_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2154 = ScalarFIFO(size=1,name="x2154").wtPort(x2138_x2148_data_s)
      val x2150 = CounterChain.copy("x2155", "x2150")
      val x2208 = CounterChain.copy("x2232", "x2208")
      val x2009 = SRAM(size=384,name="x2009",banking = Strided(1)).wtPort(x2154.readPort).rdPort(x2009_x2009_dsp0_bank0_s).rdAddr(x2208(0)).wtAddr(x2150(0))
    }
    val x2010_dsp0_bank0 = MemoryPipeline(name="x2010_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2091 = ScalarFIFO(size=1,name="x2091").wtPort(x2075_x2085_data_s)
      val x2087 = CounterChain.copy("x2092", "x2087")
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2010 = SRAM(size=384,name="x2010",banking = Strided(1)).wtPort(x2091.readPort).rdPort(x2010_x2010_dsp0_bank0_s).rdAddr(x2182(0)).wtAddr(x2087(0))
    }
    val x2011_dsp0_bank0 = MemoryPipeline(name="x2011_dsp0_bank0",parent="x2244") { implicit CU => 
      val x2174 = ScalarFIFO(size=1,name="x2174").wtPort(x2158_x2168_data_s)
      val x2170 = CounterChain.copy("x2175", "x2170")
      val x2208 = CounterChain.copy("x2232", "x2208")
      val x2011 = SRAM(size=384,name="x2011",banking = Strided(1)).wtPort(x2174.readPort).rdPort(x2011_x2011_dsp0_bank0_s).rdAddr(x2208(0)).wtAddr(x2170(0))
    }
    val x2033 = StreamController(name="x2033",parent=x2244) { implicit CU => 
      val x2033_unit = CounterChain(name = "x2033_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2024_0 = Pipeline(name="x2024_0",parent=x2033) { implicit CU => 
      val x2017 = CU.temp(None)
      val x2019 = ScalarBuffer(name="x2019").wtPort(dates_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2024_unit = CounterChain(name = "x2024_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2017))
      Stage(operands=List(x2017, CU.load(x2019)), op=FixAdd, results=List(CU.scalarOut(x2014_b2272_x2023_b2274_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2014_b2273_x2023_b2275_s)))
    }
    val x2025 = MemoryController(name="x2025",parent=x2033,offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2014_b2272 = ScalarFIFO(size=1,name="offset").wtPort(x2014_b2272_x2023_b2274_s)
      val x2014_b2273 = ScalarFIFO(size=1,name="size").wtPort(x2014_b2273_x2023_b2275_s)
      CU.newSout("data", x2015_x2025_data_s)
    }
    val x2032 = Pipeline(name="x2032",parent=x2033) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2027 = CounterChain(name = "x2027", ctr2).iter(24)
    }
    val x2053 = StreamController(name="x2053",parent=x2244) { implicit CU => 
      val x2053_unit = CounterChain(name = "x2053_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2044_0 = Pipeline(name="x2044_0",parent=x2053) { implicit CU => 
      val x2037 = CU.temp(None)
      val x2039 = ScalarBuffer(name="x2039").wtPort(quants_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2044_unit = CounterChain(name = "x2044_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2037))
      Stage(operands=List(x2037, CU.load(x2039)), op=FixAdd, results=List(CU.scalarOut(x2034_b2276_x2043_b2278_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2034_b2277_x2043_b2279_s)))
    }
    val x2045 = MemoryController(name="x2045",parent=x2053,offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2034_b2277 = ScalarFIFO(size=1,name="size").wtPort(x2034_b2277_x2043_b2279_s)
      val x2034_b2276 = ScalarFIFO(size=1,name="offset").wtPort(x2034_b2276_x2043_b2278_s)
      CU.newSout("data", x2035_x2045_data_s)
    }
    val x2052 = Pipeline(name="x2052",parent=x2053) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2047 = CounterChain(name = "x2047", ctr3).iter(24)
    }
    val x2073 = StreamController(name="x2073",parent=x2244) { implicit CU => 
      val x2073_unit = CounterChain(name = "x2073_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2064_0 = Pipeline(name="x2064_0",parent=x2073) { implicit CU => 
      val x2057 = CU.temp(None)
      val x2059 = ScalarBuffer(name="x2059").wtPort(discts_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2064_unit = CounterChain(name = "x2064_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2057))
      Stage(operands=List(x2057, CU.load(x2059)), op=FixAdd, results=List(CU.scalarOut(x2054_b2280_x2063_b2282_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2054_b2281_x2063_b2283_s)))
    }
    val x2065 = MemoryController(name="x2065",parent=x2073,offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x2054_b2281 = ScalarFIFO(size=1,name="size").wtPort(x2054_b2281_x2063_b2283_s)
      val x2054_b2280 = ScalarFIFO(size=1,name="offset").wtPort(x2054_b2280_x2063_b2282_s)
      CU.newSout("data", x2055_x2065_data_s)
    }
    val x2072 = Pipeline(name="x2072",parent=x2073) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2067 = CounterChain(name = "x2067", ctr4).iter(24)
    }
    val x2093 = StreamController(name="x2093",parent=x2244) { implicit CU => 
      val x2093_unit = CounterChain(name = "x2093_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2084_0 = Pipeline(name="x2084_0",parent=x2093) { implicit CU => 
      val x2077 = CU.temp(None)
      val x2079 = ScalarBuffer(name="x2079").wtPort(prices_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2084_unit = CounterChain(name = "x2084_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2077))
      Stage(operands=List(x2077, CU.load(x2079)), op=FixAdd, results=List(CU.scalarOut(x2074_b2284_x2083_b2286_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2074_b2285_x2083_b2287_s)))
    }
    val x2085 = MemoryController(name="x2085",parent=x2093,offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x2074_b2284 = ScalarFIFO(size=1,name="offset").wtPort(x2074_b2284_x2083_b2286_s)
      val x2074_b2285 = ScalarFIFO(size=1,name="size").wtPort(x2074_b2285_x2083_b2287_s)
      CU.newSout("data", x2075_x2085_data_s)
    }
    val x2092 = Pipeline(name="x2092",parent=x2093) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2087 = CounterChain(name = "x2087", ctr5).iter(24)
    }
    val x2116 = StreamController(name="x2116",parent=x2244) { implicit CU => 
      val x2116_unit = CounterChain(name = "x2116_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2107_0 = Pipeline(name="x2107_0",parent=x2116) { implicit CU => 
      val x2100 = CU.temp(None)
      val x2102 = ScalarBuffer(name="x2102").wtPort(dates_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2107_unit = CounterChain(name = "x2107_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2100))
      Stage(operands=List(x2100, CU.load(x2102)), op=FixAdd, results=List(CU.scalarOut(x2097_b2288_x2106_b2290_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2097_b2289_x2106_b2291_s)))
    }
    val x2108 = MemoryController(name="x2108",parent=x2116,offchip=dates_oc, mctpe=TileLoad) { implicit CU => 
      val x2097_b2289 = ScalarFIFO(size=1,name="size").wtPort(x2097_b2289_x2106_b2291_s)
      val x2097_b2288 = ScalarFIFO(size=1,name="offset").wtPort(x2097_b2288_x2106_b2290_s)
      CU.newSout("data", x2098_x2108_data_s)
    }
    val x2115 = Pipeline(name="x2115",parent=x2116) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2110 = CounterChain(name = "x2110", ctr6).iter(24)
    }
    val x2136 = StreamController(name="x2136",parent=x2244) { implicit CU => 
      val x2136_unit = CounterChain(name = "x2136_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2127_0 = Pipeline(name="x2127_0",parent=x2136) { implicit CU => 
      val x2120 = CU.temp(None)
      val x2122 = ScalarBuffer(name="x2122").wtPort(quants_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2127_unit = CounterChain(name = "x2127_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2120))
      Stage(operands=List(x2120, CU.load(x2122)), op=FixAdd, results=List(CU.scalarOut(x2117_b2292_x2126_b2294_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2117_b2293_x2126_b2295_s)))
    }
    val x2128 = MemoryController(name="x2128",parent=x2136,offchip=quants_oc, mctpe=TileLoad) { implicit CU => 
      val x2117_b2293 = ScalarFIFO(size=1,name="size").wtPort(x2117_b2293_x2126_b2295_s)
      val x2117_b2292 = ScalarFIFO(size=1,name="offset").wtPort(x2117_b2292_x2126_b2294_s)
      CU.newSout("data", x2118_x2128_data_s)
    }
    val x2135 = Pipeline(name="x2135",parent=x2136) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2130 = CounterChain(name = "x2130", ctr7).iter(24)
    }
    val x2156 = StreamController(name="x2156",parent=x2244) { implicit CU => 
      val x2156_unit = CounterChain(name = "x2156_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2147_0 = Pipeline(name="x2147_0",parent=x2156) { implicit CU => 
      val x2140 = CU.temp(None)
      val x2142 = ScalarBuffer(name="x2142").wtPort(discts_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2147_unit = CounterChain(name = "x2147_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2140))
      Stage(operands=List(x2140, CU.load(x2142)), op=FixAdd, results=List(CU.scalarOut(x2137_b2296_x2146_b2298_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2137_b2297_x2146_b2299_s)))
    }
    val x2148 = MemoryController(name="x2148",parent=x2156,offchip=discts_oc, mctpe=TileLoad) { implicit CU => 
      val x2137_b2296 = ScalarFIFO(size=1,name="offset").wtPort(x2137_b2296_x2146_b2298_s)
      val x2137_b2297 = ScalarFIFO(size=1,name="size").wtPort(x2137_b2297_x2146_b2299_s)
      CU.newSout("data", x2138_x2148_data_s)
    }
    val x2155 = Pipeline(name="x2155",parent=x2156) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2150 = CounterChain(name = "x2150", ctr8).iter(24)
    }
    val x2176 = StreamController(name="x2176",parent=x2244) { implicit CU => 
      val x2176_unit = CounterChain(name = "x2176_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2167_0 = Pipeline(name="x2167_0",parent=x2176) { implicit CU => 
      val x2160 = CU.temp(None)
      val x2162 = ScalarBuffer(name="x2162").wtPort(prices_da)
      val x2003 = CounterChain.copy("x2244", "x2003")
      val x2167_unit = CounterChain(name = "x2167_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2003(0)), Const(2)), op=FixSla, results=List(x2160))
      Stage(operands=List(x2160, CU.load(x2162)), op=FixAdd, results=List(CU.scalarOut(x2157_b2300_x2166_b2302_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x2157_b2301_x2166_b2303_s)))
    }
    val x2168 = MemoryController(name="x2168",parent=x2176,offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x2157_b2301 = ScalarFIFO(size=1,name="size").wtPort(x2157_b2301_x2166_b2303_s)
      val x2157_b2300 = ScalarFIFO(size=1,name="offset").wtPort(x2157_b2300_x2166_b2302_s)
      CU.newSout("data", x2158_x2168_data_s)
    }
    val x2175 = Pipeline(name="x2175",parent=x2176) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2170 = CounterChain(name = "x2170", ctr9).iter(24)
    }
    val x2206 = StreamController(name="x2206",parent=x2244) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2182 = CounterChain(name = "x2182", ctr10).iter(24)
    }
    val x2206_0 = Pipeline(name="x2206_0",parent=x2206) { implicit CU => 
      val x2191 = CU.temp(None)
      val x2194 = CU.temp(None)
      val x2192 = CU.temp(None)
      val x2190 = CU.temp(None)
      val x2185 = ScalarFIFO(size=1,name="x2185").wtPort(x2008_x2008_dsp0_bank0_s)
      val x2184 = ScalarFIFO(size=1,name="x2184").wtPort(x2004_x2004_dsp0_bank0_s)
      Stage(operands=List(Const(0), CU.load(x2184)), op=FixLt, results=List(x2190))
      Stage(operands=List(CU.load(x2184), Const(9999)), op=FixLt, results=List(x2191))
      Stage(operands=List(x2190, x2191), op=BitAnd, results=List(x2192))
      Stage(operands=List(Const(0), CU.load(x2185)), op=FixLeq, results=List(x2194))
      Stage(operands=List(x2192, x2194), op=BitAnd, results=List(CU.vecOut(bus_370_v)))
      Stage(operands=List(CU.load(x2185), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_371_v)))
    }
    val x2206_1 = Pipeline(name="x2206_1",parent=x2206) { implicit CU => 
      val x2200 = CU.temp(None)
      val x2199 = CU.temp(None)
      val x2202 = CU.temp(None)
      val x2197 = CU.temp(None)
      val x2187 = ScalarFIFO(size=1,name="x2187").wtPort(x2010_x2010_dsp0_bank0_s)
      val x2185 = ScalarFIFO(size=1,name="x2185").wtPort(x2008_x2008_dsp0_bank0_s)
      val x2195 = VectorFIFO(size=1,name="x2195").wtPort(bus_370_v)
      val x2186 = ScalarFIFO(size=1,name="x2186").wtPort(x2006_x2006_dsp0_bank0_s)
      val x2196 = VectorFIFO(size=1,name="x2196").wtPort(bus_371_v)
      Stage(operands=List(CU.load(x2195), CU.load(x2196)), op=BitAnd, results=List(x2197))
      Stage(operands=List(CU.load(x2186), Const(24)), op=FixLt, results=List(x2199))
      Stage(operands=List(x2197, x2199), op=BitAnd, results=List(x2200))
      Stage(operands=List(CU.load(x2187), CU.load(x2185)), op=FixMul, results=List(x2202))
      Stage(operands=List(x2200, x2202, Const(0)), op=Mux, results=List(CU.vecOut(bus_378_v)))
    }
    val x2206_2 = Pipeline(name="x2206_2",parent=x2206) { implicit CU => 
      val rr378 = VectorFIFO(size=1,name="rr378").wtPort(bus_378_v)
      Stage(operands=List(CU.load(rr378)), op=Bypass, results=List(CU.reduce))
      val (_, rr380) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2206")
      Stage(operands=List(rr380), op=Bypass, results=List(CU.scalarOut(x2179_x2205_s)))
    }
    val x2232 = StreamController(name="x2232",parent=x2244) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x2208 = CounterChain(name = "x2208", ctr11).iter(24)
    }
    val x2232_0 = Pipeline(name="x2232_0",parent=x2232) { implicit CU => 
      val x2220 = CU.temp(None)
      val x2218 = CU.temp(None)
      val x2217 = CU.temp(None)
      val x2216 = CU.temp(None)
      val x2211 = ScalarFIFO(size=1,name="x2211").wtPort(x2009_x2009_dsp0_bank0_s)
      val x2210 = ScalarFIFO(size=1,name="x2210").wtPort(x2005_x2005_dsp0_bank0_s)
      Stage(operands=List(Const(0), CU.load(x2210)), op=FixLt, results=List(x2216))
      Stage(operands=List(CU.load(x2210), Const(9999)), op=FixLt, results=List(x2217))
      Stage(operands=List(x2216, x2217), op=BitAnd, results=List(x2218))
      Stage(operands=List(Const(0), CU.load(x2211)), op=FixLeq, results=List(x2220))
      Stage(operands=List(x2218, x2220), op=BitAnd, results=List(CU.vecOut(bus_387_v)))
      Stage(operands=List(CU.load(x2211), Const(9999)), op=FixLeq, results=List(CU.vecOut(bus_388_v)))
    }
    val x2232_1 = Pipeline(name="x2232_1",parent=x2232) { implicit CU => 
      val x2223 = CU.temp(None)
      val x2226 = CU.temp(None)
      val x2225 = CU.temp(None)
      val x2228 = CU.temp(None)
      val x2222 = VectorFIFO(size=1,name="x2222").wtPort(bus_388_v)
      val x2211 = ScalarFIFO(size=1,name="x2211").wtPort(x2009_x2009_dsp0_bank0_s)
      val x2221 = VectorFIFO(size=1,name="x2221").wtPort(bus_387_v)
      val x2213 = ScalarFIFO(size=1,name="x2213").wtPort(x2011_x2011_dsp0_bank0_s)
      val x2212 = ScalarFIFO(size=1,name="x2212").wtPort(x2007_x2007_dsp0_bank0_s)
      Stage(operands=List(CU.load(x2221), CU.load(x2222)), op=BitAnd, results=List(x2223))
      Stage(operands=List(CU.load(x2212), Const(24)), op=FixLt, results=List(x2225))
      Stage(operands=List(x2223, x2225), op=BitAnd, results=List(x2226))
      Stage(operands=List(CU.load(x2213), CU.load(x2211)), op=FixMul, results=List(x2228))
      Stage(operands=List(x2226, x2228, Const(0)), op=Mux, results=List(CU.vecOut(bus_395_v)))
    }
    val x2232_2 = Pipeline(name="x2232_2",parent=x2232) { implicit CU => 
      val rr395 = VectorFIFO(size=1,name="rr395").wtPort(bus_395_v)
      Stage(operands=List(CU.load(rr395)), op=Bypass, results=List(CU.reduce))
      val (_, rr397) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2232")
      Stage(operands=List(rr397), op=Bypass, results=List(CU.scalarOut(x2180_x2231_s)))
    }
    val x2243_0 = Pipeline(name="x2243_0",parent=x2244) { implicit CU => 
      val x2179 = ScalarBuffer(name="x2179").wtPort(x2179_x2205_s)
      val x2180 = ScalarBuffer(name="x2180").wtPort(x2180_x2231_s)
      val x2243_unit = CounterChain(name = "x2243_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x2179), CU.load(x2180)), op=FixAdd, results=List(CU.reduce))
      val (_, rr401) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x2244")
      Stage(operands=List(rr401), op=Bypass, results=List(CU.scalarOut(x1995_x2246_argout)))
    }
    
  }
}
