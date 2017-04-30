import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object OuterProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x2165_x2378_x2383_v = Vector("x2165_x2378_x2383")
    val x2479_b2628_x2494_b2630_s = Scalar("x2479_b2628_x2494_b2630")
    val x2261_b2592_x2270_b2594_s = Scalar("x2261_b2592_x2270_b2594")
    val x2239_b2589_x2248_b2591_s = Scalar("x2239_b2589_x2248_b2591")
    val x2519_b2634_x2534_b2636_s = Scalar("x2519_b2634_x2534_b2636")
    val x2196_b2581_x2205_b2583_s = Scalar("x2196_b2581_x2205_b2583")
    val x2152_oc = OffChip("x2152")
    val x2282_b2596_x2291_b2598_s = Scalar("x2282_b2596_x2291_b2598")
    val x2164_x2366_x2371_v = Vector("x2164_x2366_x2371")
    val x2283_x2293_data_v = Vector("x2283_x2293_data")
    val x2304_b2600_x2313_b2602_s = Scalar("x2304_b2600_x2313_b2602")
    val x2327_argin = ArgIn("x2327")
    val x2399_b2616_x2414_b2618_s = Scalar("x2399_b2616_x2414_b2618")
    val x2305_x2315_data_v = Vector("x2305_x2315_data")
    val x2304_b2601_x2313_b2603_s = Scalar("x2304_b2601_x2313_b2603")
    val x2439_b2622_x2454_b2624_s = Scalar("x2439_b2622_x2454_b2624")
    val x2155_oc = OffChip("x2155")
    val x2306_argin = ArgIn("x2306")
    val x2173_x2501_x2505_v = Vector("x2173_x2501_x2505")
    val x2482_argin = ArgIn("x2482")
    val x2325_b2605_x2334_b2607_s = Scalar("x2325_b2605_x2334_b2607")
    val x2175_b2576_x2184_b2578_s = Scalar("x2175_b2576_x2184_b2578")
    val x2218_b2585_x2227_b2587_s = Scalar("x2218_b2585_x2227_b2587")
    val x2442_argin = ArgIn("x2442")
    val x2284_argin = ArgIn("x2284")
    val x2479_b2629_x2494_b2631_s = Scalar("x2479_b2629_x2494_b2631")
    val x2282_b2597_x2291_b2599_s = Scalar("x2282_b2597_x2291_b2599")
    val x2174_x2541_x2545_v = Vector("x2174_x2541_x2545")
    val x2170_x2391_x2395_v = Vector("x2170_x2391_x2395")
    val x2239_b2588_x2248_b2590_s = Scalar("x2239_b2588_x2248_b2590")
    val x2263_argin = ArgIn("x2263")
    val x2325_b2604_x2334_b2606_s = Scalar("x2325_b2604_x2334_b2606")
    val x2326_x2336_data_v = Vector("x2326_x2336_data")
    val x2172_x2370_v = Vector("x2172_x2370")
    val x2171_x2358_v = Vector("x2171_x2358")
    val x2174_x2394_v = Vector("x2174_x2394")
    val x2196_b2580_x2205_b2582_s = Scalar("x2196_b2580_x2205_b2582")
    val x2402_argin = ArgIn("x2402")
    val x2219_x2229_data_v = Vector("x2219_x2229_data")
    val x2240_x2250_data_v = Vector("x2240_x2250_data")
    val x2261_b2593_x2270_b2595_s = Scalar("x2261_b2593_x2270_b2595")
    val x2168_x2367_x2371_v = Vector("x2168_x2367_x2371")
    val x2172_x2461_x2465_v = Vector("x2172_x2461_x2465")
    val x2171_x2421_x2425_v = Vector("x2171_x2421_x2425")
    val x2150_oc = OffChip("x2150")
    val x2262_x2272_data_v = Vector("x2262_x2272_data")
    val x2146_argin = ArgIn("x2146")
    val x2522_argin = ArgIn("x2522")
    val x2177_argin = ArgIn("x2177")
    val x2163_x2354_x2359_v = Vector("x2163_x2354_x2359")
    val x2169_x2379_x2383_v = Vector("x2169_x2379_x2383")
    val x2176_x2186_data_v = Vector("x2176_x2186_data")
    val x2241_argin = ArgIn("x2241")
    val x2145_argin = ArgIn("x2145")
    val x2175_b2577_x2184_b2579_s = Scalar("x2175_b2577_x2184_b2579")
    val x2173_x2382_v = Vector("x2173_x2382")
    val x2197_x2207_data_v = Vector("x2197_x2207_data")
    val x2439_b2623_x2454_b2625_s = Scalar("x2439_b2623_x2454_b2625")
    val x2220_argin = ArgIn("x2220")
    val x2519_b2635_x2534_b2637_s = Scalar("x2519_b2635_x2534_b2637")
    val x2198_argin = ArgIn("x2198")
    val x2166_x2390_x2395_v = Vector("x2166_x2390_x2395")
    val x2399_b2617_x2414_b2619_s = Scalar("x2399_b2617_x2414_b2619")
    val x2167_x2355_x2359_v = Vector("x2167_x2355_x2359")
    val x2218_b2584_x2227_b2586_s = Scalar("x2218_b2584_x2227_b2586")
    val x2559 = Sequential(name="x2559",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2559_unit = CounterChain(name = "x2559_unit", ctr1)
    }
    val x2558 = MetaPipeline(name="x2558",parent=x2559) { implicit CU => 
      val x2146_x2158 =  ScalarBuffer().wtPort(x2146_argin)
      val x2145_x2160 =  ScalarBuffer().wtPort(x2145_argin)
      val ctr2 = Counter(min=Const(0), max=x2145_x2160.load, step=Const(48), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x2146_x2158.load, step=Const(48), par=4) // Counter
      val x2162 = CounterChain(name = "x2162", ctr2, ctr3)
    }
    val x2163_dsp0 = MemoryPipeline(name="x2163_dsp0",parent="x2558") { implicit CU => 
      val x2193_x2193 =  VectorFIFO(size=1).wtPort(x2176_x2186_data_v)
      val x2188 = CounterChain.copy("x2194", "x2188")
      val x2350 = CounterChain.copy("x2359_0", "x2350")
      val x2163_x2354 =  SRAM(size=48,banking = Strided(1)).wtPort(x2193_x2193.readPort).rdPort(x2163_x2354_x2359_v).rdAddr(x2350(0)).wtAddr(x2188(0))
      var stage: List[Stage] = Nil
    }
    val x2164_dsp0 = MemoryPipeline(name="x2164_dsp0",parent="x2558") { implicit CU => 
      val x2236_x2236 =  VectorFIFO(size=1).wtPort(x2219_x2229_data_v)
      val x2231 = CounterChain.copy("x2237", "x2231")
      val x2362 = CounterChain.copy("x2371_0", "x2362")
      val x2164_x2366 =  SRAM(size=48,banking = Strided(1)).wtPort(x2236_x2236.readPort).rdPort(x2164_x2366_x2371_v).rdAddr(x2362(0)).wtAddr(x2231(0))
      var stage: List[Stage] = Nil
    }
    val x2165_dsp0 = MemoryPipeline(name="x2165_dsp0",parent="x2558") { implicit CU => 
      val x2279_x2279 =  VectorFIFO(size=1).wtPort(x2262_x2272_data_v)
      val x2274 = CounterChain.copy("x2280", "x2274")
      val x2374 = CounterChain.copy("x2383_0", "x2374")
      val x2165_x2378 =  SRAM(size=48,banking = Strided(1)).wtPort(x2279_x2279.readPort).rdPort(x2165_x2378_x2383_v).rdAddr(x2374(0)).wtAddr(x2274(0))
      var stage: List[Stage] = Nil
    }
    val x2166_dsp0 = MemoryPipeline(name="x2166_dsp0",parent="x2558") { implicit CU => 
      val x2322_x2322 =  VectorFIFO(size=1).wtPort(x2305_x2315_data_v)
      val x2317 = CounterChain.copy("x2323", "x2317")
      val x2386 = CounterChain.copy("x2395_0", "x2386")
      val x2166_x2390 =  SRAM(size=48,banking = Strided(1)).wtPort(x2322_x2322.readPort).rdPort(x2166_x2390_x2395_v).rdAddr(x2386(0)).wtAddr(x2317(0))
      var stage: List[Stage] = Nil
    }
    val x2167_dsp0 = MemoryPipeline(name="x2167_dsp0",parent="x2558") { implicit CU => 
      val x2214_x2214 =  VectorFIFO(size=1).wtPort(x2197_x2207_data_v)
      val x2209 = CounterChain.copy("x2215", "x2209")
      val x2350 = CounterChain.copy("x2359_0", "x2350")
      val x2167_x2355 =  SRAM(size=48,banking = Strided(1)).wtPort(x2214_x2214.readPort).rdPort(x2167_x2355_x2359_v).rdAddr(x2350(1)).wtAddr(x2209(0))
      var stage: List[Stage] = Nil
    }
    val x2168_dsp0 = MemoryPipeline(name="x2168_dsp0",parent="x2558") { implicit CU => 
      val x2257_x2257 =  VectorFIFO(size=1).wtPort(x2240_x2250_data_v)
      val x2252 = CounterChain.copy("x2258", "x2252")
      val x2362 = CounterChain.copy("x2371_0", "x2362")
      val x2168_x2367 =  SRAM(size=48,banking = Strided(1)).wtPort(x2257_x2257.readPort).rdPort(x2168_x2367_x2371_v).rdAddr(x2362(1)).wtAddr(x2252(0))
      var stage: List[Stage] = Nil
    }
    val x2169_dsp0 = MemoryPipeline(name="x2169_dsp0",parent="x2558") { implicit CU => 
      val x2300_x2300 =  VectorFIFO(size=1).wtPort(x2283_x2293_data_v)
      val x2295 = CounterChain.copy("x2301", "x2295")
      val x2374 = CounterChain.copy("x2383_0", "x2374")
      val x2169_x2379 =  SRAM(size=48,banking = Strided(1)).wtPort(x2300_x2300.readPort).rdPort(x2169_x2379_x2383_v).rdAddr(x2374(1)).wtAddr(x2295(0))
      var stage: List[Stage] = Nil
    }
    val x2170_dsp0 = MemoryPipeline(name="x2170_dsp0",parent="x2558") { implicit CU => 
      val x2343_x2343 =  VectorFIFO(size=1).wtPort(x2326_x2336_data_v)
      val x2338 = CounterChain.copy("x2344", "x2338")
      val x2386 = CounterChain.copy("x2395_0", "x2386")
      val x2170_x2391 =  SRAM(size=48,banking = Strided(1)).wtPort(x2343_x2343.readPort).rdPort(x2170_x2391_x2395_v).rdAddr(x2386(1)).wtAddr(x2338(0))
      var stage: List[Stage] = Nil
    }
    val x2171_dsp0 = MemoryPipeline(name="x2171_dsp0",parent="x2558") { implicit CU => 
      val b2620 = CU.temp
      val b2608 = CU.temp
      val x2358_x2358 =  VectorFIFO(size=1).wtPort(x2171_x2358_v)
      val x2350 = CounterChain.copy("x2359_0", "x2350")
      val x2398 = CounterChain.copy("x2436", "x2398")
      val x2417 = CounterChain.copy("x2425", "x2417")
      val x2171_x2421 =  SRAM(size=2304,banking = Strided(1)).wtPort(x2358_x2358.readPort).rdPort(x2171_x2421_x2425_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2350(0)), Const(48)), op=FixMul, results=List(b2608))
      WAStage(operands=List(b2608, CU.ctr(x2350(1))), op=FixAdd, results=List(x2171_x2421.writeAddr))
      RAStage(operands=List(CU.ctr(x2398(0)), Const(48)), op=FixMul, results=List(b2620))
      RAStage(operands=List(b2620, CU.ctr(x2417(0))), op=FixAdd, results=List(x2171_x2421.readAddr))
    }
    val x2172_dsp0 = MemoryPipeline(name="x2172_dsp0",parent="x2558") { implicit CU => 
      val b2626 = CU.temp
      val b2610 = CU.temp
      val x2370_x2370 =  VectorFIFO(size=1).wtPort(x2172_x2370_v)
      val x2362 = CounterChain.copy("x2371_0", "x2362")
      val x2438 = CounterChain.copy("x2476", "x2438")
      val x2457 = CounterChain.copy("x2465", "x2457")
      val x2172_x2461 =  SRAM(size=2304,banking = Strided(1)).wtPort(x2370_x2370.readPort).rdPort(x2172_x2461_x2465_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2362(0)), Const(48)), op=FixMul, results=List(b2610))
      WAStage(operands=List(b2610, CU.ctr(x2362(1))), op=FixAdd, results=List(x2172_x2461.writeAddr))
      RAStage(operands=List(CU.ctr(x2438(0)), Const(48)), op=FixMul, results=List(b2626))
      RAStage(operands=List(b2626, CU.ctr(x2457(0))), op=FixAdd, results=List(x2172_x2461.readAddr))
    }
    val x2173_dsp0 = MemoryPipeline(name="x2173_dsp0",parent="x2558") { implicit CU => 
      val b2612 = CU.temp
      val b2632 = CU.temp
      val x2382_x2382 =  VectorFIFO(size=1).wtPort(x2173_x2382_v)
      val x2374 = CounterChain.copy("x2383_0", "x2374")
      val x2478 = CounterChain.copy("x2516", "x2478")
      val x2497 = CounterChain.copy("x2505", "x2497")
      val x2173_x2501 =  SRAM(size=2304,banking = Strided(1)).wtPort(x2382_x2382.readPort).rdPort(x2173_x2501_x2505_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2374(0)), Const(48)), op=FixMul, results=List(b2612))
      WAStage(operands=List(b2612, CU.ctr(x2374(1))), op=FixAdd, results=List(x2173_x2501.writeAddr))
      RAStage(operands=List(CU.ctr(x2478(0)), Const(48)), op=FixMul, results=List(b2632))
      RAStage(operands=List(b2632, CU.ctr(x2497(0))), op=FixAdd, results=List(x2173_x2501.readAddr))
    }
    val x2174_dsp0 = MemoryPipeline(name="x2174_dsp0",parent="x2558") { implicit CU => 
      val b2614 = CU.temp
      val b2638 = CU.temp
      val x2394_x2394 =  VectorFIFO(size=1).wtPort(x2174_x2394_v)
      val x2386 = CounterChain.copy("x2395_0", "x2386")
      val x2518 = CounterChain.copy("x2556", "x2518")
      val x2537 = CounterChain.copy("x2545", "x2537")
      val x2174_x2541 =  SRAM(size=2304,banking = Strided(1)).wtPort(x2394_x2394.readPort).rdPort(x2174_x2541_x2545_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x2386(0)), Const(48)), op=FixMul, results=List(b2614))
      WAStage(operands=List(b2614, CU.ctr(x2386(1))), op=FixAdd, results=List(x2174_x2541.writeAddr))
      RAStage(operands=List(CU.ctr(x2518(0)), Const(48)), op=FixMul, results=List(b2638))
      RAStage(operands=List(b2638, CU.ctr(x2537(0))), op=FixAdd, results=List(x2174_x2541.readAddr))
    }
    val x2195 = StreamController(name="x2195",parent=x2558) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2195_unit = CounterChain(name = "x2195_unit", ctr4)
    }
    val x2185_0 = Pipeline(name="x2185_0",parent=x2195) { implicit CU => 
      val x2178 = CU.temp
      val x2177 =  ScalarBuffer().wtPort(x2177_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2185_unit = CounterChain(name = "x2185_unit", ctr5)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), Const(4)), op=FixMul, results=List(x2178))
      Stage(operands=List(x2178, CU.load(x2177)), op=FixAdd, results=List(CU.scalarOut(x2175_b2576_x2184_b2578_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2175_b2577_x2184_b2579_s)))
    }
    val x2186 = MemoryController(name="x2186",parent=x2195,offchip=x2150_oc, mctpe=TileLoad) { implicit CU => 
      val x2175_b2577_x2186 =  ScalarFIFO(name="size",size=1).wtPort(x2175_b2577_x2184_b2579_s)
      val x2175_b2576_x2186 =  ScalarFIFO(name="offset",size=1).wtPort(x2175_b2576_x2184_b2578_s)
      CU.newVout("data", x2176_x2186_data_v)
    }
    val x2194 = Pipeline(name="x2194",parent=x2195) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2188 = CounterChain(name = "x2188", ctr6)
      var stage: List[Stage] = Nil
    }
    val x2216 = StreamController(name="x2216",parent=x2558) { implicit CU => 
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2216_unit = CounterChain(name = "x2216_unit", ctr7)
    }
    val x2206_0 = Pipeline(name="x2206_0",parent=x2216) { implicit CU => 
      val x2199 = CU.temp
      val x2198 =  ScalarBuffer().wtPort(x2198_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2206_unit = CounterChain(name = "x2206_unit", ctr8)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(1)), Const(4)), op=FixMul, results=List(x2199))
      Stage(operands=List(x2199, CU.load(x2198)), op=FixAdd, results=List(CU.scalarOut(x2196_b2580_x2205_b2582_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2196_b2581_x2205_b2583_s)))
    }
    val x2207 = MemoryController(name="x2207",parent=x2216,offchip=x2152_oc, mctpe=TileLoad) { implicit CU => 
      val x2196_b2580_x2207 =  ScalarFIFO(name="offset",size=1).wtPort(x2196_b2580_x2205_b2582_s)
      val x2196_b2581_x2207 =  ScalarFIFO(name="size",size=1).wtPort(x2196_b2581_x2205_b2583_s)
      CU.newVout("data", x2197_x2207_data_v)
    }
    val x2215 = Pipeline(name="x2215",parent=x2216) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2209 = CounterChain(name = "x2209", ctr9)
      var stage: List[Stage] = Nil
    }
    val x2238 = StreamController(name="x2238",parent=x2558) { implicit CU => 
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2238_unit = CounterChain(name = "x2238_unit", ctr10)
    }
    val x2228_0 = Pipeline(name="x2228_0",parent=x2238) { implicit CU => 
      val x2221 = CU.temp
      val x2220 =  ScalarBuffer().wtPort(x2220_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2228_unit = CounterChain(name = "x2228_unit", ctr11)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), Const(4)), op=FixMul, results=List(x2221))
      Stage(operands=List(x2221, CU.load(x2220)), op=FixAdd, results=List(CU.scalarOut(x2218_b2584_x2227_b2586_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2218_b2585_x2227_b2587_s)))
    }
    val x2229 = MemoryController(name="x2229",parent=x2238,offchip=x2150_oc, mctpe=TileLoad) { implicit CU => 
      val x2218_b2585_x2229 =  ScalarFIFO(name="size",size=1).wtPort(x2218_b2585_x2227_b2587_s)
      val x2218_b2584_x2229 =  ScalarFIFO(name="offset",size=1).wtPort(x2218_b2584_x2227_b2586_s)
      CU.newVout("data", x2219_x2229_data_v)
    }
    val x2237 = Pipeline(name="x2237",parent=x2238) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2231 = CounterChain(name = "x2231", ctr12)
      var stage: List[Stage] = Nil
    }
    val x2259 = StreamController(name="x2259",parent=x2558) { implicit CU => 
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2259_unit = CounterChain(name = "x2259_unit", ctr13)
    }
    val x2249_0 = Pipeline(name="x2249_0",parent=x2259) { implicit CU => 
      val x2242 = CU.temp
      val x2241 =  ScalarBuffer().wtPort(x2241_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2249_unit = CounterChain(name = "x2249_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(1)), Const(4)), op=FixMul, results=List(x2242))
      Stage(operands=List(x2242, CU.load(x2241)), op=FixAdd, results=List(CU.scalarOut(x2239_b2588_x2248_b2590_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2239_b2589_x2248_b2591_s)))
    }
    val x2250 = MemoryController(name="x2250",parent=x2259,offchip=x2152_oc, mctpe=TileLoad) { implicit CU => 
      val x2239_b2589_x2250 =  ScalarFIFO(name="size",size=1).wtPort(x2239_b2589_x2248_b2591_s)
      val x2239_b2588_x2250 =  ScalarFIFO(name="offset",size=1).wtPort(x2239_b2588_x2248_b2590_s)
      CU.newVout("data", x2240_x2250_data_v)
    }
    val x2258 = Pipeline(name="x2258",parent=x2259) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2252 = CounterChain(name = "x2252", ctr15)
      var stage: List[Stage] = Nil
    }
    val x2281 = StreamController(name="x2281",parent=x2558) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2281_unit = CounterChain(name = "x2281_unit", ctr16)
    }
    val x2271_0 = Pipeline(name="x2271_0",parent=x2281) { implicit CU => 
      val x2264 = CU.temp
      val x2263 =  ScalarBuffer().wtPort(x2263_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2271_unit = CounterChain(name = "x2271_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), Const(4)), op=FixMul, results=List(x2264))
      Stage(operands=List(x2264, CU.load(x2263)), op=FixAdd, results=List(CU.scalarOut(x2261_b2592_x2270_b2594_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2261_b2593_x2270_b2595_s)))
    }
    val x2272 = MemoryController(name="x2272",parent=x2281,offchip=x2150_oc, mctpe=TileLoad) { implicit CU => 
      val x2261_b2592_x2272 =  ScalarFIFO(name="offset",size=1).wtPort(x2261_b2592_x2270_b2594_s)
      val x2261_b2593_x2272 =  ScalarFIFO(name="size",size=1).wtPort(x2261_b2593_x2270_b2595_s)
      CU.newVout("data", x2262_x2272_data_v)
    }
    val x2280 = Pipeline(name="x2280",parent=x2281) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2274 = CounterChain(name = "x2274", ctr18)
      var stage: List[Stage] = Nil
    }
    val x2302 = StreamController(name="x2302",parent=x2558) { implicit CU => 
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2302_unit = CounterChain(name = "x2302_unit", ctr19)
    }
    val x2292_0 = Pipeline(name="x2292_0",parent=x2302) { implicit CU => 
      val x2285 = CU.temp
      val x2284 =  ScalarBuffer().wtPort(x2284_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2292_unit = CounterChain(name = "x2292_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(1)), Const(4)), op=FixMul, results=List(x2285))
      Stage(operands=List(x2285, CU.load(x2284)), op=FixAdd, results=List(CU.scalarOut(x2282_b2596_x2291_b2598_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2282_b2597_x2291_b2599_s)))
    }
    val x2293 = MemoryController(name="x2293",parent=x2302,offchip=x2152_oc, mctpe=TileLoad) { implicit CU => 
      val x2282_b2597_x2293 =  ScalarFIFO(name="size",size=1).wtPort(x2282_b2597_x2291_b2599_s)
      val x2282_b2596_x2293 =  ScalarFIFO(name="offset",size=1).wtPort(x2282_b2596_x2291_b2598_s)
      CU.newVout("data", x2283_x2293_data_v)
    }
    val x2301 = Pipeline(name="x2301",parent=x2302) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2295 = CounterChain(name = "x2295", ctr21)
      var stage: List[Stage] = Nil
    }
    val x2324 = StreamController(name="x2324",parent=x2558) { implicit CU => 
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2324_unit = CounterChain(name = "x2324_unit", ctr22)
    }
    val x2314_0 = Pipeline(name="x2314_0",parent=x2324) { implicit CU => 
      val x2307 = CU.temp
      val x2306 =  ScalarBuffer().wtPort(x2306_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2314_unit = CounterChain(name = "x2314_unit", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), Const(4)), op=FixMul, results=List(x2307))
      Stage(operands=List(x2307, CU.load(x2306)), op=FixAdd, results=List(CU.scalarOut(x2304_b2600_x2313_b2602_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2304_b2601_x2313_b2603_s)))
    }
    val x2315 = MemoryController(name="x2315",parent=x2324,offchip=x2150_oc, mctpe=TileLoad) { implicit CU => 
      val x2304_b2601_x2315 =  ScalarFIFO(name="size",size=1).wtPort(x2304_b2601_x2313_b2603_s)
      val x2304_b2600_x2315 =  ScalarFIFO(name="offset",size=1).wtPort(x2304_b2600_x2313_b2602_s)
      CU.newVout("data", x2305_x2315_data_v)
    }
    val x2323 = Pipeline(name="x2323",parent=x2324) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2317 = CounterChain(name = "x2317", ctr24)
      var stage: List[Stage] = Nil
    }
    val x2345 = StreamController(name="x2345",parent=x2558) { implicit CU => 
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2345_unit = CounterChain(name = "x2345_unit", ctr25)
    }
    val x2335_0 = Pipeline(name="x2335_0",parent=x2345) { implicit CU => 
      val x2328 = CU.temp
      val x2327 =  ScalarBuffer().wtPort(x2327_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2335_unit = CounterChain(name = "x2335_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(1)), Const(4)), op=FixMul, results=List(x2328))
      Stage(operands=List(x2328, CU.load(x2327)), op=FixAdd, results=List(CU.scalarOut(x2325_b2604_x2334_b2606_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2325_b2605_x2334_b2607_s)))
    }
    val x2336 = MemoryController(name="x2336",parent=x2345,offchip=x2152_oc, mctpe=TileLoad) { implicit CU => 
      val x2325_b2604_x2336 =  ScalarFIFO(name="offset",size=1).wtPort(x2325_b2604_x2334_b2606_s)
      val x2325_b2605_x2336 =  ScalarFIFO(name="size",size=1).wtPort(x2325_b2605_x2334_b2607_s)
      CU.newVout("data", x2326_x2336_data_v)
    }
    val x2344 = Pipeline(name="x2344",parent=x2345) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2338 = CounterChain(name = "x2338", ctr27)
      var stage: List[Stage] = Nil
    }
    val x2359_0 = Pipeline(name="x2359_0",parent=x2558) { implicit CU => 
      val x2167_x2355 =  VectorFIFO(size=1).wtPort(x2167_x2355_x2359_v)
      val x2163_x2354 =  VectorFIFO(size=1).wtPort(x2163_x2354_x2359_v)
      val ctr28 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr29 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2350 = CounterChain(name = "x2350", ctr28, ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2163_x2354), CU.load(x2167_x2355)), op=FixMul, results=List(CU.vecOut(x2171_x2358_v)))
    }
    val x2371_0 = Pipeline(name="x2371_0",parent=x2558) { implicit CU => 
      val x2164_x2366 =  VectorFIFO(size=1).wtPort(x2164_x2366_x2371_v)
      val x2168_x2367 =  VectorFIFO(size=1).wtPort(x2168_x2367_x2371_v)
      val ctr30 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr31 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2362 = CounterChain(name = "x2362", ctr30, ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2164_x2366), CU.load(x2168_x2367)), op=FixMul, results=List(CU.vecOut(x2172_x2370_v)))
    }
    val x2383_0 = Pipeline(name="x2383_0",parent=x2558) { implicit CU => 
      val x2165_x2378 =  VectorFIFO(size=1).wtPort(x2165_x2378_x2383_v)
      val x2169_x2379 =  VectorFIFO(size=1).wtPort(x2169_x2379_x2383_v)
      val ctr32 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr33 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2374 = CounterChain(name = "x2374", ctr32, ctr33)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2165_x2378), CU.load(x2169_x2379)), op=FixMul, results=List(CU.vecOut(x2173_x2382_v)))
    }
    val x2395_0 = Pipeline(name="x2395_0",parent=x2558) { implicit CU => 
      val x2170_x2391 =  VectorFIFO(size=1).wtPort(x2170_x2391_x2395_v)
      val x2166_x2390 =  VectorFIFO(size=1).wtPort(x2166_x2390_x2395_v)
      val ctr34 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr35 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2386 = CounterChain(name = "x2386", ctr34, ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2166_x2390), CU.load(x2170_x2391)), op=FixMul, results=List(CU.vecOut(x2174_x2394_v)))
    }
    val x2436 = StreamController(name="x2436",parent=x2558) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x2398 = CounterChain(name = "x2398", ctr36)
    }
    val x2426 = Sequential(name="x2426",parent=x2436) { implicit CU => 
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2426_unit = CounterChain(name = "x2426_unit", ctr37)
    }
    val x2415_0 = Pipeline(name="x2415_0",parent=x2426) { implicit CU => 
      val x2404 = CU.temp
      val x2406 = CU.temp
      val x2405 = CU.temp
      val x2407 = CU.temp
      val x2146_x2403 =  ScalarBuffer().wtPort(x2146_argin)
      val x2402 =  ScalarBuffer().wtPort(x2402_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val x2398 = CounterChain.copy("x2436", "x2398")
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2415_unit = CounterChain(name = "x2415_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), CU.ctr(x2398(0))), op=FixAdd, results=List(x2404))
      Stage(operands=List(x2404, CU.load(x2146_x2403)), op=FixMul, results=List(x2405))
      Stage(operands=List(x2405, CU.ctr(x2162(1))), op=FixAdd, results=List(x2406))
      Stage(operands=List(x2406, Const(4)), op=FixMul, results=List(x2407))
      Stage(operands=List(x2407, CU.load(x2402)), op=FixAdd, results=List(CU.scalarOut(x2399_b2616_x2414_b2618_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2399_b2617_x2414_b2619_s)))
    }
    val x2425 = Pipeline(name="x2425",parent=x2426) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2417 = CounterChain(name = "x2417", ctr39)
      var stage: List[Stage] = Nil
    }
    val x2427 = MemoryController(name="x2427",parent=x2436,offchip=x2155_oc, mctpe=TileStore) { implicit CU => 
      val x2399_b2616_x2427 =  ScalarFIFO(name="offset",size=1).wtPort(x2399_b2616_x2414_b2618_s)
      val x2400_x2427 =  VectorFIFO(name="data",size=1).wtPort(x2171_x2421_x2425_v)
      val x2399_b2617_x2427 =  ScalarFIFO(name="size",size=1).wtPort(x2399_b2617_x2414_b2619_s)
    }
    val x2476 = StreamController(name="x2476",parent=x2558) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x2438 = CounterChain(name = "x2438", ctr42)
    }
    val x2466 = Sequential(name="x2466",parent=x2476) { implicit CU => 
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2466_unit = CounterChain(name = "x2466_unit", ctr43)
    }
    val x2455_0 = Pipeline(name="x2455_0",parent=x2466) { implicit CU => 
      val x2447 = CU.temp
      val x2446 = CU.temp
      val x2444 = CU.temp
      val x2445 = CU.temp
      val x2146_x2443 =  ScalarBuffer().wtPort(x2146_argin)
      val x2442 =  ScalarBuffer().wtPort(x2442_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val x2438 = CounterChain.copy("x2476", "x2438")
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2455_unit = CounterChain(name = "x2455_unit", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), CU.ctr(x2438(0))), op=FixAdd, results=List(x2444))
      Stage(operands=List(x2444, CU.load(x2146_x2443)), op=FixMul, results=List(x2445))
      Stage(operands=List(x2445, CU.ctr(x2162(1))), op=FixAdd, results=List(x2446))
      Stage(operands=List(x2446, Const(4)), op=FixMul, results=List(x2447))
      Stage(operands=List(x2447, CU.load(x2442)), op=FixAdd, results=List(CU.scalarOut(x2439_b2622_x2454_b2624_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2439_b2623_x2454_b2625_s)))
    }
    val x2465 = Pipeline(name="x2465",parent=x2466) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2457 = CounterChain(name = "x2457", ctr45)
      var stage: List[Stage] = Nil
    }
    val x2467 = MemoryController(name="x2467",parent=x2476,offchip=x2155_oc, mctpe=TileStore) { implicit CU => 
      val x2439_b2622_x2467 =  ScalarFIFO(name="offset",size=1).wtPort(x2439_b2622_x2454_b2624_s)
      val x2439_b2623_x2467 =  ScalarFIFO(name="size",size=1).wtPort(x2439_b2623_x2454_b2625_s)
      val x2440_x2467 =  VectorFIFO(name="data",size=1).wtPort(x2172_x2461_x2465_v)
    }
    val x2516 = StreamController(name="x2516",parent=x2558) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x2478 = CounterChain(name = "x2478", ctr48)
    }
    val x2506 = Sequential(name="x2506",parent=x2516) { implicit CU => 
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2506_unit = CounterChain(name = "x2506_unit", ctr49)
    }
    val x2495_0 = Pipeline(name="x2495_0",parent=x2506) { implicit CU => 
      val x2486 = CU.temp
      val x2484 = CU.temp
      val x2487 = CU.temp
      val x2485 = CU.temp
      val x2146_x2483 =  ScalarBuffer().wtPort(x2146_argin)
      val x2482 =  ScalarBuffer().wtPort(x2482_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val x2478 = CounterChain.copy("x2516", "x2478")
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2495_unit = CounterChain(name = "x2495_unit", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), CU.ctr(x2478(0))), op=FixAdd, results=List(x2484))
      Stage(operands=List(x2484, CU.load(x2146_x2483)), op=FixMul, results=List(x2485))
      Stage(operands=List(x2485, CU.ctr(x2162(1))), op=FixAdd, results=List(x2486))
      Stage(operands=List(x2486, Const(4)), op=FixMul, results=List(x2487))
      Stage(operands=List(x2487, CU.load(x2482)), op=FixAdd, results=List(CU.scalarOut(x2479_b2628_x2494_b2630_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2479_b2629_x2494_b2631_s)))
    }
    val x2505 = Pipeline(name="x2505",parent=x2506) { implicit CU => 
      val ctr51 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2497 = CounterChain(name = "x2497", ctr51)
      var stage: List[Stage] = Nil
    }
    val x2507 = MemoryController(name="x2507",parent=x2516,offchip=x2155_oc, mctpe=TileStore) { implicit CU => 
      val x2479_b2628_x2507 =  ScalarFIFO(name="offset",size=1).wtPort(x2479_b2628_x2494_b2630_s)
      val x2480_x2507 =  VectorFIFO(name="data",size=1).wtPort(x2173_x2501_x2505_v)
      val x2479_b2629_x2507 =  ScalarFIFO(name="size",size=1).wtPort(x2479_b2629_x2494_b2631_s)
    }
    val x2556 = StreamController(name="x2556",parent=x2558) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x2518 = CounterChain(name = "x2518", ctr54)
    }
    val x2546 = Sequential(name="x2546",parent=x2556) { implicit CU => 
      val ctr55 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2546_unit = CounterChain(name = "x2546_unit", ctr55)
    }
    val x2535_0 = Pipeline(name="x2535_0",parent=x2546) { implicit CU => 
      val x2527 = CU.temp
      val x2525 = CU.temp
      val x2524 = CU.temp
      val x2526 = CU.temp
      val x2146_x2523 =  ScalarBuffer().wtPort(x2146_argin)
      val x2522 =  ScalarBuffer().wtPort(x2522_argin)
      val x2162 = CounterChain.copy("x2558", "x2162")
      val x2518 = CounterChain.copy("x2556", "x2518")
      val ctr56 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x2535_unit = CounterChain(name = "x2535_unit", ctr56)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2162(0)), CU.ctr(x2518(0))), op=FixAdd, results=List(x2524))
      Stage(operands=List(x2524, CU.load(x2146_x2523)), op=FixMul, results=List(x2525))
      Stage(operands=List(x2525, CU.ctr(x2162(1))), op=FixAdd, results=List(x2526))
      Stage(operands=List(x2526, Const(4)), op=FixMul, results=List(x2527))
      Stage(operands=List(x2527, CU.load(x2522)), op=FixAdd, results=List(CU.scalarOut(x2519_b2634_x2534_b2636_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x2519_b2635_x2534_b2637_s)))
    }
    val x2545 = Pipeline(name="x2545",parent=x2546) { implicit CU => 
      val ctr57 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x2537 = CounterChain(name = "x2537", ctr57)
      var stage: List[Stage] = Nil
    }
    val x2547 = MemoryController(name="x2547",parent=x2556,offchip=x2155_oc, mctpe=TileStore) { implicit CU => 
      val x2519_b2634_x2547 =  ScalarFIFO(name="offset",size=1).wtPort(x2519_b2634_x2534_b2636_s)
      val x2520_x2547 =  VectorFIFO(name="data",size=1).wtPort(x2174_x2541_x2545_v)
      val x2519_b2635_x2547 =  ScalarFIFO(name="size",size=1).wtPort(x2519_b2635_x2534_b2637_s)
    }
    
  }
}
