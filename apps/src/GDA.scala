import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object GDA extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1997_oc = OffChip("x1997")
    val x2052_x2119_scalar = Scalar("x2052_x2119")
    val x2183_b2286_x2190_b2291_scalar = Scalar("x2183_b2286_x2190_b2291")
    val x2185_b2289_x2199_b2296_vector = Vector("x2185_b2289_x2199_b2296")
    val x2122_x2159_x2166_vector = Vector("x2122_x2159_x2166")
    val x2054_b2252_x2061_b2261_scalar = Scalar("x2054_b2252_x2061_b2261")
    val x1995_oc = OffChip("x1995")
    val x2066_x2073_scalar = Scalar("x2066_x2073")
    val x2050_x2131_x2143_vector = Vector("x2050_x2131_x2143")
    val x2051_x2112_vector = Vector("x2051_x2112")
    val x2054_b2251_x2061_b2260_scalar = Scalar("x2054_b2251_x2061_b2260")
    val x2056_argin = ArgIn("x2056")
    val x2185_b2290_x2199_b2297_vector = Vector("x2185_b2290_x2199_b2297")
    val x2026_x2031_scalar = Scalar("x2026_x2031")
    val x2126_x2147_x2154_vector = Vector("x2126_x2147_x2154")
    val x2046_x2178_vector = Vector("x2046_x2178")
    val x2046_x2172_x2179_vector = Vector("x2046_x2172_x2179")
    val x2025_b2241_x2030_b2244_scalar = Scalar("x2025_b2241_x2030_b2244")
    val x2053_b2247_x2060_b2256_scalar = Scalar("x2053_b2247_x2060_b2256")
    val x2004_x2041_vector = Vector("x2004_x2041")
    val x2122_x2171_x2179_vector = Vector("x2122_x2171_x2179")
    val x2127_x2153_vector = Vector("x2127_x2153")
    val x2005_b2235_x2010_b2238_scalar = Scalar("x2005_b2235_x2010_b2238")
    val x2025_b2243_x2030_b2246_scalar = Scalar("x2025_b2243_x2030_b2246")
    val x2051_x2130_x2143_vector = Vector("x2051_x2130_x2143")
    val x2098_argin = ArgIn("x2098")
    val x2126_x2148_x2154_vector = Vector("x2126_x2148_x2154")
    val x2183_b2288_x2190_b2293_scalar = Scalar("x2183_b2288_x2190_b2293")
    val x2004_x2132_x2143_vector = Vector("x2004_x2132_x2143")
    val x2005_b2236_x2010_b2239_scalar = Scalar("x2005_b2236_x2010_b2239")
    val x2046_x2195_x2200_vector = Vector("x2046_x2195_x2200")
    val x2008_argin = ArgIn("x2008")
    val x2122_x2165_vector = Vector("x2122_x2165")
    val x2025_b2242_x2030_b2245_scalar = Scalar("x2025_b2242_x2030_b2245")
    val x1998_oc = OffChip("x1998")
    val x2095_b2263_x2101_b2266_scalar = Scalar("x2095_b2263_x2101_b2266")
    val x1990_argin = ArgIn("x1990")
    val x2126_x2142_vector = Vector("x2126_x2142")
    val x2053_b2248_x2060_b2257_scalar = Scalar("x2053_b2248_x2060_b2257")
    val x1993_oc = OffChip("x1993")
    val x2095_b2262_x2101_b2265_scalar = Scalar("x2095_b2262_x2101_b2265")
    val x2187_argin = ArgIn("x2187")
    val x1996_oc = OffChip("x1996")
    val x2183_b2287_x2190_b2292_scalar = Scalar("x2183_b2287_x2190_b2292")
    val x2095_b2264_x2101_b2267_scalar = Scalar("x2095_b2264_x2101_b2267")
    val x2096_x2102_scalar = Scalar("x2096_x2102")
    val x2050_x2089_vector = Vector("x2050_x2089")
    val x2006_x2011_scalar = Scalar("x2006_x2011")
    val x2028_argin = ArgIn("x2028")
    val x2003_x2133_x2143_vector = Vector("x2003_x2133_x2143")
    val x2003_x2021_vector = Vector("x2003_x2021")
    val x2064_x2069_scalar = Scalar("x2064_x2069")
    val x2127_x2158_x2166_vector = Vector("x2127_x2158_x2166")
    val x2184_x2191_scalar = Scalar("x2184_x2191")
    val x2053_b2249_x2060_b2258_scalar = Scalar("x2053_b2249_x2060_b2258")
    val x2065_x2071_scalar = Scalar("x2065_x2071")
    val x2005_b2237_x2010_b2240_scalar = Scalar("x2005_b2237_x2010_b2240")
    val x2054_b2250_x2061_b2259_scalar = Scalar("x2054_b2250_x2061_b2259")
    val x2207 = Sequential(name="x2207",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2003_dsp0 = MemoryPipeline(name="x2003_dsp0",parent="x2207") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2017 = CounterChain.copy("x2022", "x2017")
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2129 = CounterChain.copy("x2143", "x2129")
      val x2003_x2133 = SRAM(size = 64, banking = Strided(1)).wtPort(x2003_x2021_vector).rdPort(x2003_x2133_x2143_vector)
      var stage: List[Stage] = Nil
    }
    val x2004_dsp0 = MemoryPipeline(name="x2004_dsp0",parent="x2207") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2037 = CounterChain.copy("x2042", "x2037")
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2129 = CounterChain.copy("x2143", "x2129")
      val x2004_x2132 = SRAM(size = 64, banking = Strided(1)).wtPort(x2004_x2041_vector).rdPort(x2004_x2132_x2143_vector)
      var stage: List[Stage] = Nil
    }
    val x2024 = Sequential(name="x2024",parent=x2207) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2012 = UnitPipeline(name="x2012",parent=x2024) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr285 = CU.temp
      val tr286 = CU.temp
      val tr287 = CU.temp
      val x2008 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2008_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr285)), op=Bypass, results=List(CU.scalarOut(stage(1), x2005_b2235_x2010_b2238_scalar)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr286)), op=Bypass, results=List(CU.scalarOut(stage(2), x2005_b2236_x2010_b2239_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr287)), op=Bypass, results=List(CU.scalarOut(stage(3), x2005_b2237_x2010_b2240_scalar)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x2006_x2011_scalar)))
    }
    val x2013 = Fringe(name="x2013",parent=x2024,dram=x1996_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2005_b2236_x2013 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2005_b2236_x2010_b2239_scalar).rdPort(None)
      val x2005_b2235_x2013 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2005_b2235_x2010_b2238_scalar).rdPort(None)
      val x2005_b2237_x2013 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2005_b2237_x2010_b2240_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2023 = Sequential(name="x2023",parent=x2024) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2015 = Sequential(name="x2015",parent=x2023) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2022 = Pipeline(name="x2022",parent=x2023) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2017 = CounterChain(name = "x2017", ctr1)
      val x2007_x2018 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2007_x2018.load), op=Bypass, results=List(CU.vecOut(stage(1), x2003_x2021_vector)))
    }
    val x2044 = Sequential(name="x2044",parent=x2207) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2032 = UnitPipeline(name="x2032",parent=x2044) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr289 = CU.temp
      val tr290 = CU.temp
      val tr291 = CU.temp
      val x2028 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2028_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr289)), op=Bypass, results=List(CU.scalarOut(stage(1), x2025_b2241_x2030_b2244_scalar)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr290)), op=Bypass, results=List(CU.scalarOut(stage(2), x2025_b2242_x2030_b2245_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr291)), op=Bypass, results=List(CU.scalarOut(stage(3), x2025_b2243_x2030_b2246_scalar)))
      Stage(stage(4), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(4), x2026_x2031_scalar)))
    }
    val x2033 = Fringe(name="x2033",parent=x2044,dram=x1997_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2025_b2242_x2033 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2025_b2242_x2030_b2245_scalar).rdPort(None)
      val x2025_b2241_x2033 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2025_b2241_x2030_b2244_scalar).rdPort(None)
      val x2025_b2243_x2033 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2025_b2243_x2030_b2246_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2043 = Sequential(name="x2043",parent=x2044) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2035 = Sequential(name="x2035",parent=x2043) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2042 = Pipeline(name="x2042",parent=x2043) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2037 = CounterChain(name = "x2037", ctr2)
      val x2027_x2038 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2027_x2038.load), op=Bypass, results=List(CU.vecOut(stage(1), x2004_x2041_vector)))
    }
    val x2046_dsp0 = MemoryPipeline(name="x2046_dsp0",parent="x2180") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr294 = CU.temp
      val tr295 = CU.temp
      val tr296 = CU.temp
      val tr297 = CU.temp
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x2170 = CounterChain.copy("x2179", "x2170")
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2194 = CounterChain.copy("x2200", "x2194")
      val x2046_x2195 = SRAM(size = 4096, banking = Strided(1)).wtPort(x2046_x2178_vector).rdPort(x2046_x2195_x2200_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2046_x2195))
      Stage(stage(1), operands=List(x2170(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr294)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr294), CU.ctr(stage(1), x2170(1))), op=FixAdd, results=List(x2046_x2195.writeAddr, CU.temp(stage(2), tr295)))
      stage = stage0 +: RAStages(2, List(x2046_x2195))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2182(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr296)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr296), CU.ctr(stage(1), x2194(0))), op=FixAdd, results=List(x2046_x2195.readAddr, CU.temp(stage(2), tr297)))
    }
    val x2046_dsp1 = MemoryPipeline(name="x2046_dsp1",parent="x2180") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr299 = CU.temp
      val tr300 = CU.temp
      val tr301 = CU.temp
      val tr302 = CU.temp
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x2170 = CounterChain.copy("x2179", "x2170")
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2194 = CounterChain.copy("x2200", "x2194")
      val x2046_x2172 = SRAM(size = 4096, banking = Strided(1)).wtPort(x2046_x2178_vector).rdPort(x2046_x2195_x2200_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2046_x2172))
      Stage(stage(1), operands=List(x2170(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr299)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr299), CU.ctr(stage(1), x2170(1))), op=FixAdd, results=List(x2046_x2172.writeAddr, CU.temp(stage(2), tr300)))
      stage = stage0 +: RAStages(2, List(x2046_x2172))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2182(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr301)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr301), CU.ctr(stage(1), x2194(0))), op=FixAdd, results=List(x2046_x2172.readAddr, CU.temp(stage(2), tr302)))
    }
    val x2180 = MetaPipeline(name="x2180",parent=x2207) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, x1990_x2047.load, Const("20i").out) // Counter
      val x2049 = CounterChain(name = "x2049", ctr3)
      val x1990_x2047 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1990_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2050_dsp0 = MemoryPipeline(name="x2050_dsp0",parent="x2180") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr303 = CU.temp
      val x2077 = CounterChain.copy("x2090", "x2077")
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2050_x2131 = SRAM(size = 20, banking = Strided(1)).wtPort(x2050_x2089_vector).rdPort(x2050_x2131_x2143_vector)
      val x2064_x2078 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2064_x2069_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2050_x2131))
      Stage(stage(1), operands=List(x2077(0), x2064_x2078.load), op=FixSub, results=List(x2050_x2131.writeAddr, CU.temp(stage(1), tr303)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x2077(0)), CU.load(stage(1), x2064_x2078)), op=FixSub, results=List(CU.temp(stage(2), tr303)))
    }
    val x2051_dsp0 = MemoryPipeline(name="x2051_dsp0",parent="x2180") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr305 = CU.temp
      val tr306 = CU.temp
      val tr307 = CU.temp
      val tr308 = CU.temp
      val x2094 = CounterChain.copy("x2115", "x2094")
      val x2108 = CounterChain.copy("x2113", "x2108")
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2129 = CounterChain.copy("x2143", "x2129")
      val x2051_x2130 = SRAM(size = 1280, banking = Strided(1)).wtPort(x2051_x2112_vector).rdPort(x2051_x2130_x2143_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2051_x2130))
      Stage(stage(1), operands=List(x2094(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr305)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr305), CU.ctr(stage(1), x2108(0))), op=FixAdd, results=List(x2051_x2130.writeAddr, CU.temp(stage(2), tr306)))
      stage = stage0 +: RAStages(2, List(x2051_x2130))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2125(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr307)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr307), CU.ctr(stage(1), x2129(0))), op=FixAdd, results=List(x2051_x2130.readAddr, CU.temp(stage(2), tr308)))
    }
    val x2092 = Sequential(name="x2092",parent=x2180) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2062 = UnitPipeline(name="x2062",parent=x2092) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr310 = CU.temp
      val tr312 = CU.temp
      val tr314 = CU.temp
      val tr315 = CU.temp
      val tr316 = CU.temp
      val tr317 = CU.temp
      val tr318 = CU.temp
      val tr319 = CU.temp
      val tr320 = CU.temp
      val tr321 = CU.temp
      val tr322 = CU.temp
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x2056 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2056_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2049(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr310)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr310), Const("16i")), op=FixMod, results=List(CU.temp(stage(2), tr312)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr312), Const("20i")), op=FixAdd, results=List(CU.temp(stage(3), tr314)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr310), CU.load(stage(3), x2056)), op=FixAdd, results=List(CU.temp(stage(4), tr318)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr318), CU.temp(stage(4), tr316)), op=FixSub, results=List(CU.temp(stage(5), tr319)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr320)), op=Bypass, results=List(CU.scalarOut(stage(6), x2053_b2247_x2060_b2256_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr321)), op=Bypass, results=List(CU.scalarOut(stage(7), x2053_b2248_x2060_b2257_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr322)), op=Bypass, results=List(CU.scalarOut(stage(8), x2053_b2249_x2060_b2258_scalar)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr315)), op=Bypass, results=List(CU.scalarOut(stage(9), x2054_b2250_x2061_b2259_scalar)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr316)), op=Bypass, results=List(CU.scalarOut(stage(10), x2054_b2251_x2061_b2260_scalar)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr317)), op=Bypass, results=List(CU.scalarOut(stage(11), x2054_b2252_x2061_b2261_scalar)))
    }
    val x2063 = Fringe(name="x2063",parent=x2092,dram=x1995_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2053_b2248_x2063 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2053_b2248_x2060_b2257_scalar).rdPort(None)
      val x2053_b2247_x2063 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2053_b2247_x2060_b2256_scalar).rdPort(None)
      val x2053_b2249_x2063 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2053_b2249_x2060_b2258_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2091 = Sequential(name="x2091",parent=x2092) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2074 = UnitPipeline(name="x2074",parent=x2091) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr323 = CU.temp
      val tr324 = CU.temp
      val tr325 = CU.temp
      val x2054_b2251_x2067_b2254 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x2054_b2251_x2061_b2260_scalar).rdPort(None)
      val x2054_b2250_x2067_b2253 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x2054_b2250_x2061_b2259_scalar).rdPort(None)
      val x2054_b2252_x2067_b2255 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x2054_b2252_x2061_b2261_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x2054_b2251_x2067_b2254.load), op=Bypass, results=List(CU.scalarOut(stage(1), x2064_x2069_scalar)))
      Stage(stage(2), operands=List(x2054_b2252_x2067_b2255.load), op=Bypass, results=List(CU.scalarOut(stage(2), x2065_x2071_scalar)))
      Stage(stage(3), operands=List(x2054_b2250_x2067_b2253.load), op=Bypass, results=List(CU.scalarOut(stage(3), x2066_x2073_scalar)))
    }
    val x2090 = Pipeline(name="x2090",parent=x2091) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, x2066_x2075.load, Const("1i").out) // Counter
      val x2077 = CounterChain(name = "x2077", ctr4)
      val x2066_x2075 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2066_x2073_scalar).rdPort(None)
      val x2065_x2079 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2065_x2071_scalar).rdPort(None)
      val x2055_x2080 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      val x2064_x2078 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2064_x2069_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2055_x2080.load), op=Bypass, results=List(CU.vecOut(stage(1), x2050_x2089_vector)))
    }
    val x2115 = StreamController(name="x2115",parent=x2180) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("20i").out, Const("1i").out) // Counter
      val x2094 = CounterChain(name = "x2094", ctr5)
      var stage: List[Stage] = Nil
    }
    val x2103 = UnitPipeline(name="x2103",parent=x2115) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr326 = CU.temp
      val tr328 = CU.temp
      val tr330 = CU.temp
      val tr331 = CU.temp
      val tr332 = CU.temp
      val tr333 = CU.temp
      val tr334 = CU.temp
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x2094 = CounterChain.copy("x2115", "x2094")
      val x2098 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2098_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2049(0)), CU.ctr(stage(0), x2094(0))), op=FixAdd, results=List(CU.temp(stage(1), tr326)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr326), Const("64i")), op=FixMul, results=List(CU.temp(stage(2), tr328)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr328), Const("4i")), op=FixMul, results=List(CU.temp(stage(3), tr330)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr330), CU.load(stage(3), x2098)), op=FixAdd, results=List(CU.temp(stage(4), tr331)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr332)), op=Bypass, results=List(CU.scalarOut(stage(5), x2095_b2262_x2101_b2265_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr333)), op=Bypass, results=List(CU.scalarOut(stage(6), x2095_b2263_x2101_b2266_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr334)), op=Bypass, results=List(CU.scalarOut(stage(7), x2095_b2264_x2101_b2267_scalar)))
      Stage(stage(8), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(8), x2096_x2102_scalar)))
    }
    val x2104 = Fringe(name="x2104",parent=x2115,dram=x1993_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2095_b2263_x2104 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2095_b2263_x2101_b2266_scalar).rdPort(None)
      val x2095_b2262_x2104 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2095_b2262_x2101_b2265_scalar).rdPort(None)
      val x2095_b2264_x2104 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2095_b2264_x2101_b2267_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2114 = Sequential(name="x2114",parent=x2115) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2106 = Sequential(name="x2106",parent=x2114) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2113 = Pipeline(name="x2113",parent=x2114) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2094 = CounterChain.copy("x2115", "x2094")
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2108 = CounterChain(name = "x2108", ctr6)
      val x2097_x2109 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2097_x2109.load), op=Bypass, results=List(CU.vecOut(stage(1), x2051_x2112_vector)))
    }
    val x2120 = UnitPipeline(name="x2120",parent=x2180) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr335 = CU.temp
      val tr337 = CU.temp
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x1990_x2116 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1990_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x1990_x2116.load, CU.ctr(stage(0), x2049(0))), op=FixSub, results=List(CU.temp(stage(1), tr335)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr335), Const("20i")), op=FixMin, results=List(CU.scalarOut(stage(2), x2052_x2119_scalar), CU.temp(stage(2), tr337)))
    }
    val x2122_dsp0 = MemoryPipeline(name="x2122_dsp0",parent="x2167") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr339 = CU.temp
      val tr340 = CU.temp
      val tr341 = CU.temp
      val tr342 = CU.temp
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2157 = CounterChain.copy("x2166", "x2157")
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x2170 = CounterChain.copy("x2179", "x2170")
      val x2122_x2171 = SRAM(size = 4096, banking = Strided(1)).wtPort(x2122_x2165_vector).rdPort(x2122_x2171_x2179_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2122_x2171))
      Stage(stage(1), operands=List(x2157(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr339)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr339), CU.ctr(stage(1), x2157(1))), op=FixAdd, results=List(x2122_x2171.writeAddr, CU.temp(stage(2), tr340)))
      stage = stage0 +: RAStages(2, List(x2122_x2171))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2170(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr341)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr341), CU.ctr(stage(1), x2170(1))), op=FixAdd, results=List(x2122_x2171.readAddr, CU.temp(stage(2), tr342)))
    }
    val x2122_dsp1 = MemoryPipeline(name="x2122_dsp1",parent="x2167") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr344 = CU.temp
      val tr345 = CU.temp
      val tr346 = CU.temp
      val tr347 = CU.temp
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2157 = CounterChain.copy("x2166", "x2157")
      val x2049 = CounterChain.copy("x2180", "x2049")
      val x2170 = CounterChain.copy("x2179", "x2170")
      val x2122_x2159 = SRAM(size = 4096, banking = Strided(1)).wtPort(x2122_x2165_vector).rdPort(x2122_x2171_x2179_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2122_x2159))
      Stage(stage(1), operands=List(x2157(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr344)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr344), CU.ctr(stage(1), x2157(1))), op=FixAdd, results=List(x2122_x2159.writeAddr, CU.temp(stage(2), tr345)))
      stage = stage0 +: RAStages(2, List(x2122_x2159))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2170(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr346)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr346), CU.ctr(stage(1), x2170(1))), op=FixAdd, results=List(x2122_x2159.readAddr, CU.temp(stage(2), tr347)))
    }
    val x2167 = MetaPipeline(name="x2167",parent=x2180) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, x2052_x2123.load, Const("1i").out) // Counter
      val x2125 = CounterChain(name = "x2125", ctr7)
      val x2052_x2123 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2052_x2119_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2126_dsp0 = MemoryPipeline(name="x2126_dsp0",parent="x2167") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2129 = CounterChain.copy("x2143", "x2129")
      val x2146 = CounterChain.copy("x2154", "x2146")
      val x2126_x2148 = SRAM(size = 64, banking = Strided(1)).wtPort(x2126_x2142_vector).rdPort(x2126_x2148_x2154_vector)
      var stage: List[Stage] = Nil
    }
    val x2126_dsp1 = MemoryPipeline(name="x2126_dsp1",parent="x2167") { implicit CU => 
      val stage0 = CU.emptyStage
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2129 = CounterChain.copy("x2143", "x2129")
      val x2146 = CounterChain.copy("x2154", "x2146")
      val x2126_x2147 = SRAM(size = 64, banking = Strided(1)).wtPort(x2126_x2142_vector).rdPort(x2126_x2148_x2154_vector)
      var stage: List[Stage] = Nil
    }
    val x2127_dsp0 = MemoryPipeline(name="x2127_dsp0",parent="x2167") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr349 = CU.temp
      val tr350 = CU.temp
      val tr351 = CU.temp
      val tr352 = CU.temp
      val x2146 = CounterChain.copy("x2154", "x2146")
      val x2125 = CounterChain.copy("x2167", "x2125")
      val x2157 = CounterChain.copy("x2166", "x2157")
      val x2127_x2158 = SRAM(size = 4096, banking = Strided(1)).wtPort(x2127_x2153_vector).rdPort(x2127_x2158_x2166_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2127_x2158))
      Stage(stage(1), operands=List(x2146(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr349)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr349), CU.ctr(stage(1), x2146(1))), op=FixAdd, results=List(x2127_x2158.writeAddr, CU.temp(stage(2), tr350)))
      stage = stage0 +: RAStages(2, List(x2127_x2158))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2157(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr351)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr351), CU.ctr(stage(1), x2157(1))), op=FixAdd, results=List(x2127_x2158.readAddr, CU.temp(stage(2), tr352)))
    }
    val x2143 = Pipeline(name="x2143",parent=x2167) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr353 = CU.temp
      val tr354 = CU.temp
      val tr355 = CU.temp
      val x2125 = CounterChain.copy("x2167", "x2125")
      val ctr8 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2129 = CounterChain(name = "x2129", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2050_x2131_x2143_vector), Const("1i")), op=FixEql, results=List(CU.temp(stage(1), tr353)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr353), CU.vecIn(stage(1), x2004_x2132_x2143_vector), CU.vecIn(stage(1), x2003_x2133_x2143_vector)), op=Mux, results=List(CU.temp(stage(2), tr354)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x2051_x2130_x2143_vector), CU.temp(stage(2), tr354)), op=FixSub, results=List(CU.vecOut(stage(3), x2126_x2142_vector), CU.temp(stage(3), tr355)))
    }
    val x2154 = Pipeline(name="x2154",parent=x2167) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr356 = CU.temp
      val ctr9 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2146 = CounterChain(name = "x2146", ctr9, ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x2126_x2147_x2154_vector), CU.vecIn(stage(0), x2126_x2148_x2154_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x2127_x2153_vector), CU.temp(stage(1), tr356)))
    }
    val x2166 = Pipeline(name="x2166",parent=x2167) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr357 = CU.temp
      val tr358 = CU.temp
      val tr359 = CU.temp
      val x2125 = CounterChain.copy("x2167", "x2125")
      val ctr11 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2157 = CounterChain(name = "x2157", ctr11, ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2125(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr357)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x2127_x2158_x2166_vector), CU.vecIn(stage(1), x2122_x2159_x2166_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr358)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr357), CU.vecIn(stage(2), x2127_x2158_x2166_vector), CU.temp(stage(2), tr358)), op=Mux, results=List(CU.vecOut(stage(3), x2122_x2165_vector), CU.temp(stage(3), tr359)))
    }
    val x2179 = Pipeline(name="x2179",parent=x2180) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr360 = CU.temp
      val tr361 = CU.temp
      val tr362 = CU.temp
      val x2049 = CounterChain.copy("x2180", "x2049")
      val ctr13 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val ctr14 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2170 = CounterChain(name = "x2170", ctr13, ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2049(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr360)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x2122_x2171_x2179_vector), CU.vecIn(stage(1), x2046_x2172_x2179_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr361)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr360), CU.vecIn(stage(2), x2122_x2171_x2179_vector), CU.temp(stage(2), tr361)), op=Mux, results=List(CU.vecOut(stage(3), x2046_x2178_vector), CU.temp(stage(3), tr362)))
    }
    val x2206 = StreamController(name="x2206",parent=x2207) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2182 = CounterChain(name = "x2182", ctr15)
      var stage: List[Stage] = Nil
    }
    val x2201 = Sequential(name="x2201",parent=x2206) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x2192 = UnitPipeline(name="x2192",parent=x2201) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr364 = CU.temp
      val tr366 = CU.temp
      val tr367 = CU.temp
      val tr368 = CU.temp
      val tr369 = CU.temp
      val tr370 = CU.temp
      val x2182 = CounterChain.copy("x2206", "x2182")
      val x2187 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x2187_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2182(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr364)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr364), Const("4i")), op=FixMul, results=List(CU.temp(stage(2), tr366)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr366), CU.load(stage(2), x2187)), op=FixAdd, results=List(CU.temp(stage(3), tr367)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr368)), op=Bypass, results=List(CU.scalarOut(stage(4), x2183_b2286_x2190_b2291_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr369)), op=Bypass, results=List(CU.scalarOut(stage(5), x2183_b2287_x2190_b2292_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr370)), op=Bypass, results=List(CU.scalarOut(stage(6), x2183_b2288_x2190_b2293_scalar)))
      Stage(stage(7), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(7), x2184_x2191_scalar)))
    }
    val x2200 = Pipeline(name="x2200",parent=x2201) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr371 = CU.temp
      val tr372 = CU.temp
      val x2182 = CounterChain.copy("x2206", "x2182")
      val ctr16 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x2194 = CounterChain(name = "x2194", ctr16)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr371)), op=Bypass, results=List(CU.vecOut(stage(1), x2185_b2289_x2199_b2296_vector)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr372)), op=Bypass, results=List(CU.vecOut(stage(2), x2185_b2290_x2199_b2297_vector)))
    }
    val x2202 = Fringe(name="x2202",parent=x2206,dram=x1998_oc, mode=TileStore) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2185_b2289_x2202 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x2185_b2289_x2199_b2296_vector).rdPort(None)
      val x2185_b2290_x2202 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x2185_b2290_x2199_b2297_vector).rdPort(None)
      val x2183_b2287_x2202 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2183_b2287_x2190_b2292_scalar).rdPort(None)
      val x2183_b2286_x2202 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2183_b2286_x2190_b2291_scalar).rdPort(None)
      val x2183_b2288_x2202 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x2183_b2288_x2190_b2293_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x2205 = Sequential(name="x2205",parent=x2206) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    
  }
}
