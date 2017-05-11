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
    val x2618_b2898_x2626_b2900_s = Scalar("x2618_b2898_x2626_b2900")
    val x2540_b2882_x2548_b2884_s = Scalar("x2540_b2882_x2548_b2884")
    val x2425_argin = ArgIn("x2425")
    val x2521_b2877_x2529_b2879_s = Scalar("x2521_b2877_x2529_b2879")
    val x2681_x2735_s = Scalar("x2681_x2735")
    val x2659_argin = ArgIn("x2659")
    val x2342_x2824_argout = ArgOut("x2342_x2824")
    val x2560_b2885_x2568_b2887_s = Scalar("x2560_b2885_x2568_b2887")
    val x2351_x2715_x2724_v = Vector("x2351_x2715_x2724")
    val x2684_x2774_s = Scalar("x2684_x2774")
    val x2638_b2902_x2646_b2904_s = Scalar("x2638_b2902_x2646_b2904")
    val x2335_argin = ArgIn("x2335")
    val x2462_b2865_x2470_b2867_s = Scalar("x2462_b2865_x2470_b2867")
    val x2367_argin = ArgIn("x2367")
    val x2581_argin = ArgIn("x2581")
    val x2619_x2628_data_v = Vector("x2619_x2628_data")
    val x2360_x2729_x2737_v = Vector("x2360_x2729_x2737")
    val x2523_argin = ArgIn("x2523")
    val x2683_x2761_s = Scalar("x2683_x2761")
    val x2350_x2702_x2711_v = Vector("x2350_x2702_x2711")
    val x2445_argin = ArgIn("x2445")
    val x2404_b2853_x2412_b2855_s = Scalar("x2404_b2853_x2412_b2855")
    val x2601_argin = ArgIn("x2601")
    val x2423_b2858_x2431_b2860_s = Scalar("x2423_b2858_x2431_b2860")
    val x2341_oc = OffChip("x2341")
    val x2679_x2709_s = Scalar("x2679_x2709")
    val x2562_argin = ArgIn("x2562")
    val x2639_x2648_data_v = Vector("x2639_x2648_data")
    val x2541_x2550_data_v = Vector("x2541_x2550_data")
    val x2349_x2689_x2698_v = Vector("x2349_x2689_x2698")
    val x2404_b2854_x2412_b2856_s = Scalar("x2404_b2854_x2412_b2856")
    val x2483_x2492_data_v = Vector("x2483_x2492_data")
    val x2599_b2893_x2607_b2895_s = Scalar("x2599_b2893_x2607_b2895")
    val x2355_x2767_x2776_v = Vector("x2355_x2767_x2776")
    val x2560_b2886_x2568_b2888_s = Scalar("x2560_b2886_x2568_b2888")
    val x2358_x2703_x2711_v = Vector("x2358_x2703_x2711")
    val x2364_x2781_x2789_v = Vector("x2364_x2781_x2789")
    val x2501_b2874_x2509_b2876_s = Scalar("x2501_b2874_x2509_b2876")
    val x2482_b2869_x2490_b2871_s = Scalar("x2482_b2869_x2490_b2871")
    val x2502_x2511_data_v = Vector("x2502_x2511_data")
    val x2352_x2728_x2737_v = Vector("x2352_x2728_x2737")
    val x2638_b2901_x2646_b2903_s = Scalar("x2638_b2901_x2646_b2903")
    val x2361_x2742_x2750_v = Vector("x2361_x2742_x2750")
    val x2580_x2589_data_v = Vector("x2580_x2589_data")
    val x2682_x2748_s = Scalar("x2682_x2748")
    val x2406_argin = ArgIn("x2406")
    val x2640_argin = ArgIn("x2640")
    val bus_1238_s = Scalar("bus_1238")
    val x2464_argin = ArgIn("x2464")
    val x2579_b2889_x2587_b2891_s = Scalar("x2579_b2889_x2587_b2891")
    val bus_1246_s = Scalar("bus_1246")
    val x2359_x2716_x2724_v = Vector("x2359_x2716_x2724")
    val x2482_b2870_x2490_b2872_s = Scalar("x2482_b2870_x2490_b2872")
    val x2484_argin = ArgIn("x2484")
    val x2444_x2453_data_v = Vector("x2444_x2453_data")
    val x2522_x2531_data_v = Vector("x2522_x2531_data")
    val x2384_b2850_x2392_b2852_s = Scalar("x2384_b2850_x2392_b2852")
    val x2353_x2741_x2750_v = Vector("x2353_x2741_x2750")
    val x2618_b2897_x2626_b2899_s = Scalar("x2618_b2897_x2626_b2899")
    val x2357_x2690_x2698_v = Vector("x2357_x2690_x2698")
    val x2423_b2857_x2431_b2859_s = Scalar("x2423_b2857_x2431_b2859")
    val x2339_oc = OffChip("x2339")
    val x2501_b2873_x2509_b2875_s = Scalar("x2501_b2873_x2509_b2875")
    val x2657_b2905_x2665_b2907_s = Scalar("x2657_b2905_x2665_b2907")
    val x2362_x2755_x2763_v = Vector("x2362_x2755_x2763")
    val x2540_b2881_x2548_b2883_s = Scalar("x2540_b2881_x2548_b2883")
    val x2405_x2414_data_v = Vector("x2405_x2414_data")
    val x2599_b2894_x2607_b2896_s = Scalar("x2599_b2894_x2607_b2896")
    val x2600_x2609_data_v = Vector("x2600_x2609_data")
    val x2521_b2878_x2529_b2880_s = Scalar("x2521_b2878_x2529_b2880")
    val bus_1242_s = Scalar("bus_1242")
    val x2366_x2375_data_v = Vector("x2366_x2375_data")
    val x2463_x2472_data_v = Vector("x2463_x2472_data")
    val x2365_b2846_x2373_b2848_s = Scalar("x2365_b2846_x2373_b2848")
    val x2354_x2754_x2763_v = Vector("x2354_x2754_x2763")
    val x2620_argin = ArgIn("x2620")
    val x2542_argin = ArgIn("x2542")
    val x2365_b2845_x2373_b2847_s = Scalar("x2365_b2845_x2373_b2847")
    val x2443_b2861_x2451_b2863_s = Scalar("x2443_b2861_x2451_b2863")
    val x2658_x2667_data_v = Vector("x2658_x2667_data")
    val x2385_x2394_data_v = Vector("x2385_x2394_data")
    val x2443_b2862_x2451_b2864_s = Scalar("x2443_b2862_x2451_b2864")
    val x2384_b2849_x2392_b2851_s = Scalar("x2384_b2849_x2392_b2851")
    val x2657_b2906_x2665_b2908_s = Scalar("x2657_b2906_x2665_b2908")
    val x2363_x2768_x2776_v = Vector("x2363_x2768_x2776")
    val x2356_x2780_x2789_v = Vector("x2356_x2780_x2789")
    val x2561_x2570_data_v = Vector("x2561_x2570_data")
    val x2579_b2890_x2587_b2892_s = Scalar("x2579_b2890_x2587_b2892")
    val x2685_x2787_s = Scalar("x2685_x2787")
    val x2503_argin = ArgIn("x2503")
    val x2386_argin = ArgIn("x2386")
    val x2424_x2433_data_v = Vector("x2424_x2433_data")
    val x2678_x2696_s = Scalar("x2678_x2696")
    val x2462_b2866_x2470_b2868_s = Scalar("x2462_b2866_x2470_b2868")
    val x2680_x2722_s = Scalar("x2680_x2722")
    val x2826 = Sequential(name="x2826",parent=top) { implicit CU => 
    }
    val x2822 = MetaPipeline(name="x2822",parent=x2826) { implicit CU => 
      val x2335_x2346 =  ScalarBuffer().wtPort(x2335_argin)
      val ctr1 = Counter(min=Const(0), max=x2335_x2346.load, step=Const(3200), par=8) // Counter
      val x2348 = CounterChain(name = "x2348", ctr1).iter(30)
    }
    val x2349_dsp0 = MemoryPipeline(name="x2349_dsp0",parent="x2822") { implicit CU => 
      val x2381_x2381 =  VectorFIFO(size=1).wtPort(x2366_x2375_data_v)
      val x2377 = CounterChain.copy("x2382", "x2377")
      val x2687 = CounterChain.copy("x2698_0", "x2687")
      val x2349_x2689 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2381_x2381.readPort).rdPort(x2349_x2689_x2698_v).rdAddr(x2687(0)).wtAddr(x2377(0))
      var stage: List[Stage] = Nil
    }
    val x2350_dsp0 = MemoryPipeline(name="x2350_dsp0",parent="x2822") { implicit CU => 
      val x2420_x2420 =  VectorFIFO(size=1).wtPort(x2405_x2414_data_v)
      val x2416 = CounterChain.copy("x2421", "x2416")
      val x2700 = CounterChain.copy("x2711_0", "x2700")
      val x2350_x2702 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2420_x2420.readPort).rdPort(x2350_x2702_x2711_v).rdAddr(x2700(0)).wtAddr(x2416(0))
      var stage: List[Stage] = Nil
    }
    val x2351_dsp0 = MemoryPipeline(name="x2351_dsp0",parent="x2822") { implicit CU => 
      val x2459_x2459 =  VectorFIFO(size=1).wtPort(x2444_x2453_data_v)
      val x2455 = CounterChain.copy("x2460", "x2455")
      val x2713 = CounterChain.copy("x2724_0", "x2713")
      val x2351_x2715 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2459_x2459.readPort).rdPort(x2351_x2715_x2724_v).rdAddr(x2713(0)).wtAddr(x2455(0))
      var stage: List[Stage] = Nil
    }
    val x2352_dsp0 = MemoryPipeline(name="x2352_dsp0",parent="x2822") { implicit CU => 
      val x2498_x2498 =  VectorFIFO(size=1).wtPort(x2483_x2492_data_v)
      val x2494 = CounterChain.copy("x2499", "x2494")
      val x2726 = CounterChain.copy("x2737_0", "x2726")
      val x2352_x2728 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2498_x2498.readPort).rdPort(x2352_x2728_x2737_v).rdAddr(x2726(0)).wtAddr(x2494(0))
      var stage: List[Stage] = Nil
    }
    val x2353_dsp0 = MemoryPipeline(name="x2353_dsp0",parent="x2822") { implicit CU => 
      val x2537_x2537 =  VectorFIFO(size=1).wtPort(x2522_x2531_data_v)
      val x2533 = CounterChain.copy("x2538", "x2533")
      val x2739 = CounterChain.copy("x2750_0", "x2739")
      val x2353_x2741 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2537_x2537.readPort).rdPort(x2353_x2741_x2750_v).rdAddr(x2739(0)).wtAddr(x2533(0))
      var stage: List[Stage] = Nil
    }
    val x2354_dsp0 = MemoryPipeline(name="x2354_dsp0",parent="x2822") { implicit CU => 
      val x2576_x2576 =  VectorFIFO(size=1).wtPort(x2561_x2570_data_v)
      val x2572 = CounterChain.copy("x2577", "x2572")
      val x2752 = CounterChain.copy("x2763_0", "x2752")
      val x2354_x2754 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2576_x2576.readPort).rdPort(x2354_x2754_x2763_v).rdAddr(x2752(0)).wtAddr(x2572(0))
      var stage: List[Stage] = Nil
    }
    val x2355_dsp0 = MemoryPipeline(name="x2355_dsp0",parent="x2822") { implicit CU => 
      val x2615_x2615 =  VectorFIFO(size=1).wtPort(x2600_x2609_data_v)
      val x2611 = CounterChain.copy("x2616", "x2611")
      val x2765 = CounterChain.copy("x2776_0", "x2765")
      val x2355_x2767 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2615_x2615.readPort).rdPort(x2355_x2767_x2776_v).rdAddr(x2765(0)).wtAddr(x2611(0))
      var stage: List[Stage] = Nil
    }
    val x2356_dsp0 = MemoryPipeline(name="x2356_dsp0",parent="x2822") { implicit CU => 
      val x2654_x2654 =  VectorFIFO(size=1).wtPort(x2639_x2648_data_v)
      val x2650 = CounterChain.copy("x2655", "x2650")
      val x2778 = CounterChain.copy("x2789_0", "x2778")
      val x2356_x2780 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2654_x2654.readPort).rdPort(x2356_x2780_x2789_v).rdAddr(x2778(0)).wtAddr(x2650(0))
      var stage: List[Stage] = Nil
    }
    val x2357_dsp0 = MemoryPipeline(name="x2357_dsp0",parent="x2822") { implicit CU => 
      val x2400_x2400 =  VectorFIFO(size=1).wtPort(x2385_x2394_data_v)
      val x2396 = CounterChain.copy("x2401", "x2396")
      val x2687 = CounterChain.copy("x2698_0", "x2687")
      val x2357_x2690 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2400_x2400.readPort).rdPort(x2357_x2690_x2698_v).rdAddr(x2687(0)).wtAddr(x2396(0))
      var stage: List[Stage] = Nil
    }
    val x2358_dsp0 = MemoryPipeline(name="x2358_dsp0",parent="x2822") { implicit CU => 
      val x2439_x2439 =  VectorFIFO(size=1).wtPort(x2424_x2433_data_v)
      val x2435 = CounterChain.copy("x2440", "x2435")
      val x2700 = CounterChain.copy("x2711_0", "x2700")
      val x2358_x2703 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2439_x2439.readPort).rdPort(x2358_x2703_x2711_v).rdAddr(x2700(0)).wtAddr(x2435(0))
      var stage: List[Stage] = Nil
    }
    val x2359_dsp0 = MemoryPipeline(name="x2359_dsp0",parent="x2822") { implicit CU => 
      val x2478_x2478 =  VectorFIFO(size=1).wtPort(x2463_x2472_data_v)
      val x2474 = CounterChain.copy("x2479", "x2474")
      val x2713 = CounterChain.copy("x2724_0", "x2713")
      val x2359_x2716 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2478_x2478.readPort).rdPort(x2359_x2716_x2724_v).rdAddr(x2713(0)).wtAddr(x2474(0))
      var stage: List[Stage] = Nil
    }
    val x2360_dsp0 = MemoryPipeline(name="x2360_dsp0",parent="x2822") { implicit CU => 
      val x2517_x2517 =  VectorFIFO(size=1).wtPort(x2502_x2511_data_v)
      val x2513 = CounterChain.copy("x2518", "x2513")
      val x2726 = CounterChain.copy("x2737_0", "x2726")
      val x2360_x2729 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2517_x2517.readPort).rdPort(x2360_x2729_x2737_v).rdAddr(x2726(0)).wtAddr(x2513(0))
      var stage: List[Stage] = Nil
    }
    val x2361_dsp0 = MemoryPipeline(name="x2361_dsp0",parent="x2822") { implicit CU => 
      val x2556_x2556 =  VectorFIFO(size=1).wtPort(x2541_x2550_data_v)
      val x2552 = CounterChain.copy("x2557", "x2552")
      val x2739 = CounterChain.copy("x2750_0", "x2739")
      val x2361_x2742 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2556_x2556.readPort).rdPort(x2361_x2742_x2750_v).rdAddr(x2739(0)).wtAddr(x2552(0))
      var stage: List[Stage] = Nil
    }
    val x2362_dsp0 = MemoryPipeline(name="x2362_dsp0",parent="x2822") { implicit CU => 
      val x2595_x2595 =  VectorFIFO(size=1).wtPort(x2580_x2589_data_v)
      val x2591 = CounterChain.copy("x2596", "x2591")
      val x2752 = CounterChain.copy("x2763_0", "x2752")
      val x2362_x2755 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2595_x2595.readPort).rdPort(x2362_x2755_x2763_v).rdAddr(x2752(0)).wtAddr(x2591(0))
      var stage: List[Stage] = Nil
    }
    val x2363_dsp0 = MemoryPipeline(name="x2363_dsp0",parent="x2822") { implicit CU => 
      val x2634_x2634 =  VectorFIFO(size=1).wtPort(x2619_x2628_data_v)
      val x2630 = CounterChain.copy("x2635", "x2630")
      val x2765 = CounterChain.copy("x2776_0", "x2765")
      val x2363_x2768 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2634_x2634.readPort).rdPort(x2363_x2768_x2776_v).rdAddr(x2765(0)).wtAddr(x2630(0))
      var stage: List[Stage] = Nil
    }
    val x2364_dsp0 = MemoryPipeline(name="x2364_dsp0",parent="x2822") { implicit CU => 
      val x2673_x2673 =  VectorFIFO(size=1).wtPort(x2658_x2667_data_v)
      val x2669 = CounterChain.copy("x2674", "x2669")
      val x2778 = CounterChain.copy("x2789_0", "x2778")
      val x2364_x2781 =  SRAM(size=3200,banking = Strided(1)).wtPort(x2673_x2673.readPort).rdPort(x2364_x2781_x2789_v).rdAddr(x2778(0)).wtAddr(x2669(0))
      var stage: List[Stage] = Nil
    }
    val x2383 = StreamController(name="x2383",parent=x2822) { implicit CU => 
    }
    val x2374_0 = Pipeline(name="x2374_0",parent=x2383) { implicit CU => 
      val x2368 = CU.temp
      val x2367 =  ScalarBuffer().wtPort(x2367_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2368))
      Stage(operands=List(x2368, CU.load(x2367)), op=FixAdd, results=List(CU.scalarOut(x2365_b2845_x2373_b2847_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2365_b2846_x2373_b2848_s)))
    }
    val x2375 = MemoryController(name="x2375",parent=x2383,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2365_b2846_x2375 =  ScalarFIFO(name="size",size=1).wtPort(x2365_b2846_x2373_b2848_s)
      val x2365_b2845_x2375 =  ScalarFIFO(name="offset",size=1).wtPort(x2365_b2845_x2373_b2847_s)
      CU.newVout("data", x2366_x2375_data_v)
    }
    val x2382 = Pipeline(name="x2382",parent=x2383) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2377 = CounterChain(name = "x2377", ctr2).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2402 = StreamController(name="x2402",parent=x2822) { implicit CU => 
    }
    val x2393_0 = Pipeline(name="x2393_0",parent=x2402) { implicit CU => 
      val x2387 = CU.temp
      val x2386 =  ScalarBuffer().wtPort(x2386_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2387))
      Stage(operands=List(x2387, CU.load(x2386)), op=FixAdd, results=List(CU.scalarOut(x2384_b2849_x2392_b2851_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2384_b2850_x2392_b2852_s)))
    }
    val x2394 = MemoryController(name="x2394",parent=x2402,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2384_b2849_x2394 =  ScalarFIFO(name="offset",size=1).wtPort(x2384_b2849_x2392_b2851_s)
      val x2384_b2850_x2394 =  ScalarFIFO(name="size",size=1).wtPort(x2384_b2850_x2392_b2852_s)
      CU.newVout("data", x2385_x2394_data_v)
    }
    val x2401 = Pipeline(name="x2401",parent=x2402) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2396 = CounterChain(name = "x2396", ctr3).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2422 = StreamController(name="x2422",parent=x2822) { implicit CU => 
    }
    val x2413_0 = Pipeline(name="x2413_0",parent=x2422) { implicit CU => 
      val x2407 = CU.temp
      val x2406 =  ScalarBuffer().wtPort(x2406_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2407))
      Stage(operands=List(x2407, CU.load(x2406)), op=FixAdd, results=List(CU.scalarOut(x2404_b2853_x2412_b2855_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2404_b2854_x2412_b2856_s)))
    }
    val x2414 = MemoryController(name="x2414",parent=x2422,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2404_b2854_x2414 =  ScalarFIFO(name="size",size=1).wtPort(x2404_b2854_x2412_b2856_s)
      val x2404_b2853_x2414 =  ScalarFIFO(name="offset",size=1).wtPort(x2404_b2853_x2412_b2855_s)
      CU.newVout("data", x2405_x2414_data_v)
    }
    val x2421 = Pipeline(name="x2421",parent=x2422) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2416 = CounterChain(name = "x2416", ctr4).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2441 = StreamController(name="x2441",parent=x2822) { implicit CU => 
    }
    val x2432_0 = Pipeline(name="x2432_0",parent=x2441) { implicit CU => 
      val x2426 = CU.temp
      val x2425 =  ScalarBuffer().wtPort(x2425_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2426))
      Stage(operands=List(x2426, CU.load(x2425)), op=FixAdd, results=List(CU.scalarOut(x2423_b2857_x2431_b2859_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2423_b2858_x2431_b2860_s)))
    }
    val x2433 = MemoryController(name="x2433",parent=x2441,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2423_b2858_x2433 =  ScalarFIFO(name="size",size=1).wtPort(x2423_b2858_x2431_b2860_s)
      val x2423_b2857_x2433 =  ScalarFIFO(name="offset",size=1).wtPort(x2423_b2857_x2431_b2859_s)
      CU.newVout("data", x2424_x2433_data_v)
    }
    val x2440 = Pipeline(name="x2440",parent=x2441) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2435 = CounterChain(name = "x2435", ctr5).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2461 = StreamController(name="x2461",parent=x2822) { implicit CU => 
    }
    val x2452_0 = Pipeline(name="x2452_0",parent=x2461) { implicit CU => 
      val x2446 = CU.temp
      val x2445 =  ScalarBuffer().wtPort(x2445_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2446))
      Stage(operands=List(x2446, CU.load(x2445)), op=FixAdd, results=List(CU.scalarOut(x2443_b2861_x2451_b2863_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2443_b2862_x2451_b2864_s)))
    }
    val x2453 = MemoryController(name="x2453",parent=x2461,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2443_b2861_x2453 =  ScalarFIFO(name="offset",size=1).wtPort(x2443_b2861_x2451_b2863_s)
      val x2443_b2862_x2453 =  ScalarFIFO(name="size",size=1).wtPort(x2443_b2862_x2451_b2864_s)
      CU.newVout("data", x2444_x2453_data_v)
    }
    val x2460 = Pipeline(name="x2460",parent=x2461) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2455 = CounterChain(name = "x2455", ctr6).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2480 = StreamController(name="x2480",parent=x2822) { implicit CU => 
    }
    val x2471_0 = Pipeline(name="x2471_0",parent=x2480) { implicit CU => 
      val x2465 = CU.temp
      val x2464 =  ScalarBuffer().wtPort(x2464_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2465))
      Stage(operands=List(x2465, CU.load(x2464)), op=FixAdd, results=List(CU.scalarOut(x2462_b2865_x2470_b2867_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2462_b2866_x2470_b2868_s)))
    }
    val x2472 = MemoryController(name="x2472",parent=x2480,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2462_b2866_x2472 =  ScalarFIFO(name="size",size=1).wtPort(x2462_b2866_x2470_b2868_s)
      val x2462_b2865_x2472 =  ScalarFIFO(name="offset",size=1).wtPort(x2462_b2865_x2470_b2867_s)
      CU.newVout("data", x2463_x2472_data_v)
    }
    val x2479 = Pipeline(name="x2479",parent=x2480) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2474 = CounterChain(name = "x2474", ctr7).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2500 = StreamController(name="x2500",parent=x2822) { implicit CU => 
    }
    val x2491_0 = Pipeline(name="x2491_0",parent=x2500) { implicit CU => 
      val x2485 = CU.temp
      val x2484 =  ScalarBuffer().wtPort(x2484_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2485))
      Stage(operands=List(x2485, CU.load(x2484)), op=FixAdd, results=List(CU.scalarOut(x2482_b2869_x2490_b2871_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2482_b2870_x2490_b2872_s)))
    }
    val x2492 = MemoryController(name="x2492",parent=x2500,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2482_b2870_x2492 =  ScalarFIFO(name="size",size=1).wtPort(x2482_b2870_x2490_b2872_s)
      val x2482_b2869_x2492 =  ScalarFIFO(name="offset",size=1).wtPort(x2482_b2869_x2490_b2871_s)
      CU.newVout("data", x2483_x2492_data_v)
    }
    val x2499 = Pipeline(name="x2499",parent=x2500) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2494 = CounterChain(name = "x2494", ctr8).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2519 = StreamController(name="x2519",parent=x2822) { implicit CU => 
    }
    val x2510_0 = Pipeline(name="x2510_0",parent=x2519) { implicit CU => 
      val x2504 = CU.temp
      val x2503 =  ScalarBuffer().wtPort(x2503_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2504))
      Stage(operands=List(x2504, CU.load(x2503)), op=FixAdd, results=List(CU.scalarOut(x2501_b2873_x2509_b2875_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2501_b2874_x2509_b2876_s)))
    }
    val x2511 = MemoryController(name="x2511",parent=x2519,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2501_b2873_x2511 =  ScalarFIFO(name="offset",size=1).wtPort(x2501_b2873_x2509_b2875_s)
      val x2501_b2874_x2511 =  ScalarFIFO(name="size",size=1).wtPort(x2501_b2874_x2509_b2876_s)
      CU.newVout("data", x2502_x2511_data_v)
    }
    val x2518 = Pipeline(name="x2518",parent=x2519) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2513 = CounterChain(name = "x2513", ctr9).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2539 = StreamController(name="x2539",parent=x2822) { implicit CU => 
    }
    val x2530_0 = Pipeline(name="x2530_0",parent=x2539) { implicit CU => 
      val x2524 = CU.temp
      val x2523 =  ScalarBuffer().wtPort(x2523_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2524))
      Stage(operands=List(x2524, CU.load(x2523)), op=FixAdd, results=List(CU.scalarOut(x2521_b2877_x2529_b2879_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2521_b2878_x2529_b2880_s)))
    }
    val x2531 = MemoryController(name="x2531",parent=x2539,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2521_b2878_x2531 =  ScalarFIFO(name="size",size=1).wtPort(x2521_b2878_x2529_b2880_s)
      val x2521_b2877_x2531 =  ScalarFIFO(name="offset",size=1).wtPort(x2521_b2877_x2529_b2879_s)
      CU.newVout("data", x2522_x2531_data_v)
    }
    val x2538 = Pipeline(name="x2538",parent=x2539) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2533 = CounterChain(name = "x2533", ctr10).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2558 = StreamController(name="x2558",parent=x2822) { implicit CU => 
    }
    val x2549_0 = Pipeline(name="x2549_0",parent=x2558) { implicit CU => 
      val x2543 = CU.temp
      val x2542 =  ScalarBuffer().wtPort(x2542_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2543))
      Stage(operands=List(x2543, CU.load(x2542)), op=FixAdd, results=List(CU.scalarOut(x2540_b2881_x2548_b2883_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2540_b2882_x2548_b2884_s)))
    }
    val x2550 = MemoryController(name="x2550",parent=x2558,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2540_b2882_x2550 =  ScalarFIFO(name="size",size=1).wtPort(x2540_b2882_x2548_b2884_s)
      val x2540_b2881_x2550 =  ScalarFIFO(name="offset",size=1).wtPort(x2540_b2881_x2548_b2883_s)
      CU.newVout("data", x2541_x2550_data_v)
    }
    val x2557 = Pipeline(name="x2557",parent=x2558) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2552 = CounterChain(name = "x2552", ctr11).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2578 = StreamController(name="x2578",parent=x2822) { implicit CU => 
    }
    val x2569_0 = Pipeline(name="x2569_0",parent=x2578) { implicit CU => 
      val x2563 = CU.temp
      val x2562 =  ScalarBuffer().wtPort(x2562_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2563))
      Stage(operands=List(x2563, CU.load(x2562)), op=FixAdd, results=List(CU.scalarOut(x2560_b2885_x2568_b2887_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2560_b2886_x2568_b2888_s)))
    }
    val x2570 = MemoryController(name="x2570",parent=x2578,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2560_b2885_x2570 =  ScalarFIFO(name="offset",size=1).wtPort(x2560_b2885_x2568_b2887_s)
      val x2560_b2886_x2570 =  ScalarFIFO(name="size",size=1).wtPort(x2560_b2886_x2568_b2888_s)
      CU.newVout("data", x2561_x2570_data_v)
    }
    val x2577 = Pipeline(name="x2577",parent=x2578) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2572 = CounterChain(name = "x2572", ctr12).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2597 = StreamController(name="x2597",parent=x2822) { implicit CU => 
    }
    val x2588_0 = Pipeline(name="x2588_0",parent=x2597) { implicit CU => 
      val x2582 = CU.temp
      val x2581 =  ScalarBuffer().wtPort(x2581_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2582))
      Stage(operands=List(x2582, CU.load(x2581)), op=FixAdd, results=List(CU.scalarOut(x2579_b2889_x2587_b2891_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2579_b2890_x2587_b2892_s)))
    }
    val x2589 = MemoryController(name="x2589",parent=x2597,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2579_b2890_x2589 =  ScalarFIFO(name="size",size=1).wtPort(x2579_b2890_x2587_b2892_s)
      val x2579_b2889_x2589 =  ScalarFIFO(name="offset",size=1).wtPort(x2579_b2889_x2587_b2891_s)
      CU.newVout("data", x2580_x2589_data_v)
    }
    val x2596 = Pipeline(name="x2596",parent=x2597) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2591 = CounterChain(name = "x2591", ctr13).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2617 = StreamController(name="x2617",parent=x2822) { implicit CU => 
    }
    val x2608_0 = Pipeline(name="x2608_0",parent=x2617) { implicit CU => 
      val x2602 = CU.temp
      val x2601 =  ScalarBuffer().wtPort(x2601_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2602))
      Stage(operands=List(x2602, CU.load(x2601)), op=FixAdd, results=List(CU.scalarOut(x2599_b2893_x2607_b2895_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2599_b2894_x2607_b2896_s)))
    }
    val x2609 = MemoryController(name="x2609",parent=x2617,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2599_b2894_x2609 =  ScalarFIFO(name="size",size=1).wtPort(x2599_b2894_x2607_b2896_s)
      val x2599_b2893_x2609 =  ScalarFIFO(name="offset",size=1).wtPort(x2599_b2893_x2607_b2895_s)
      CU.newVout("data", x2600_x2609_data_v)
    }
    val x2616 = Pipeline(name="x2616",parent=x2617) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2611 = CounterChain(name = "x2611", ctr14).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2636 = StreamController(name="x2636",parent=x2822) { implicit CU => 
    }
    val x2627_0 = Pipeline(name="x2627_0",parent=x2636) { implicit CU => 
      val x2621 = CU.temp
      val x2620 =  ScalarBuffer().wtPort(x2620_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2621))
      Stage(operands=List(x2621, CU.load(x2620)), op=FixAdd, results=List(CU.scalarOut(x2618_b2897_x2626_b2899_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2618_b2898_x2626_b2900_s)))
    }
    val x2628 = MemoryController(name="x2628",parent=x2636,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2618_b2897_x2628 =  ScalarFIFO(name="offset",size=1).wtPort(x2618_b2897_x2626_b2899_s)
      val x2618_b2898_x2628 =  ScalarFIFO(name="size",size=1).wtPort(x2618_b2898_x2626_b2900_s)
      CU.newVout("data", x2619_x2628_data_v)
    }
    val x2635 = Pipeline(name="x2635",parent=x2636) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2630 = CounterChain(name = "x2630", ctr15).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2656 = StreamController(name="x2656",parent=x2822) { implicit CU => 
    }
    val x2647_0 = Pipeline(name="x2647_0",parent=x2656) { implicit CU => 
      val x2641 = CU.temp
      val x2640 =  ScalarBuffer().wtPort(x2640_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2641))
      Stage(operands=List(x2641, CU.load(x2640)), op=FixAdd, results=List(CU.scalarOut(x2638_b2901_x2646_b2903_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2638_b2902_x2646_b2904_s)))
    }
    val x2648 = MemoryController(name="x2648",parent=x2656,offchip=x2339_oc, mctpe=TileLoad) { implicit CU => 
      val x2638_b2902_x2648 =  ScalarFIFO(name="size",size=1).wtPort(x2638_b2902_x2646_b2904_s)
      val x2638_b2901_x2648 =  ScalarFIFO(name="offset",size=1).wtPort(x2638_b2901_x2646_b2903_s)
      CU.newVout("data", x2639_x2648_data_v)
    }
    val x2655 = Pipeline(name="x2655",parent=x2656) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2650 = CounterChain(name = "x2650", ctr16).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2675 = StreamController(name="x2675",parent=x2822) { implicit CU => 
    }
    val x2666_0 = Pipeline(name="x2666_0",parent=x2675) { implicit CU => 
      val x2660 = CU.temp
      val x2659 =  ScalarBuffer().wtPort(x2659_argin)
      val x2348 = CounterChain.copy("x2822", "x2348")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x2348(0)), Const(4)), op=FixMul, results=List(x2660))
      Stage(operands=List(x2660, CU.load(x2659)), op=FixAdd, results=List(CU.scalarOut(x2657_b2905_x2665_b2907_s)))
      Stage(operands=List(Const(12800)), op=Bypass, results=List(CU.scalarOut(x2657_b2906_x2665_b2908_s)))
    }
    val x2667 = MemoryController(name="x2667",parent=x2675,offchip=x2341_oc, mctpe=TileLoad) { implicit CU => 
      val x2657_b2906_x2667 =  ScalarFIFO(name="size",size=1).wtPort(x2657_b2906_x2665_b2908_s)
      val x2657_b2905_x2667 =  ScalarFIFO(name="offset",size=1).wtPort(x2657_b2905_x2665_b2907_s)
      CU.newVout("data", x2658_x2667_data_v)
    }
    val x2674 = Pipeline(name="x2674",parent=x2675) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2669 = CounterChain(name = "x2669", ctr17).iter(200)
      var stage: List[Stage] = Nil
    }
    val x2698_0 = Pipeline(name="x2698_0",parent=x2822) { implicit CU => 
      val x2690_x2690 =  VectorFIFO(size=1).wtPort(x2357_x2690_x2698_v)
      val x2689_x2689 =  VectorFIFO(size=1).wtPort(x2349_x2689_x2698_v)
      val ctr18 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2687 = CounterChain(name = "x2687", ctr18).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2689_x2689), CU.load(x2690_x2690)), op=FixMul, results=List(CU.reduce))
      val (_, rr1199) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1199), op=Bypass, results=List(CU.scalarOut(x2678_x2696_s)))
    }
    val x2711_0 = Pipeline(name="x2711_0",parent=x2822) { implicit CU => 
      val x2703_x2703 =  VectorFIFO(size=1).wtPort(x2358_x2703_x2711_v)
      val x2702_x2702 =  VectorFIFO(size=1).wtPort(x2350_x2702_x2711_v)
      val ctr19 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2700 = CounterChain(name = "x2700", ctr19).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2702_x2702), CU.load(x2703_x2703)), op=FixMul, results=List(CU.reduce))
      val (_, rr1204) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1204), op=Bypass, results=List(CU.scalarOut(x2679_x2709_s)))
    }
    val x2724_0 = Pipeline(name="x2724_0",parent=x2822) { implicit CU => 
      val x2715_x2715 =  VectorFIFO(size=1).wtPort(x2351_x2715_x2724_v)
      val x2716_x2716 =  VectorFIFO(size=1).wtPort(x2359_x2716_x2724_v)
      val ctr20 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2713 = CounterChain(name = "x2713", ctr20).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2715_x2715), CU.load(x2716_x2716)), op=FixMul, results=List(CU.reduce))
      val (_, rr1209) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1209), op=Bypass, results=List(CU.scalarOut(x2680_x2722_s)))
    }
    val x2737_0 = Pipeline(name="x2737_0",parent=x2822) { implicit CU => 
      val x2729_x2729 =  VectorFIFO(size=1).wtPort(x2360_x2729_x2737_v)
      val x2728_x2728 =  VectorFIFO(size=1).wtPort(x2352_x2728_x2737_v)
      val ctr21 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2726 = CounterChain(name = "x2726", ctr21).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2728_x2728), CU.load(x2729_x2729)), op=FixMul, results=List(CU.reduce))
      val (_, rr1214) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1214), op=Bypass, results=List(CU.scalarOut(x2681_x2735_s)))
    }
    val x2750_0 = Pipeline(name="x2750_0",parent=x2822) { implicit CU => 
      val x2742_x2742 =  VectorFIFO(size=1).wtPort(x2361_x2742_x2750_v)
      val x2741_x2741 =  VectorFIFO(size=1).wtPort(x2353_x2741_x2750_v)
      val ctr22 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2739 = CounterChain(name = "x2739", ctr22).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2741_x2741), CU.load(x2742_x2742)), op=FixMul, results=List(CU.reduce))
      val (_, rr1219) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1219), op=Bypass, results=List(CU.scalarOut(x2682_x2748_s)))
    }
    val x2763_0 = Pipeline(name="x2763_0",parent=x2822) { implicit CU => 
      val x2755_x2755 =  VectorFIFO(size=1).wtPort(x2362_x2755_x2763_v)
      val x2754_x2754 =  VectorFIFO(size=1).wtPort(x2354_x2754_x2763_v)
      val ctr23 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2752 = CounterChain(name = "x2752", ctr23).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2754_x2754), CU.load(x2755_x2755)), op=FixMul, results=List(CU.reduce))
      val (_, rr1224) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1224), op=Bypass, results=List(CU.scalarOut(x2683_x2761_s)))
    }
    val x2776_0 = Pipeline(name="x2776_0",parent=x2822) { implicit CU => 
      val x2768_x2768 =  VectorFIFO(size=1).wtPort(x2363_x2768_x2776_v)
      val x2767_x2767 =  VectorFIFO(size=1).wtPort(x2355_x2767_x2776_v)
      val ctr24 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2765 = CounterChain(name = "x2765", ctr24).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2767_x2767), CU.load(x2768_x2768)), op=FixMul, results=List(CU.reduce))
      val (_, rr1229) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1229), op=Bypass, results=List(CU.scalarOut(x2684_x2774_s)))
    }
    val x2789_0 = Pipeline(name="x2789_0",parent=x2822) { implicit CU => 
      val x2780_x2780 =  VectorFIFO(size=1).wtPort(x2356_x2780_x2789_v)
      val x2781_x2781 =  VectorFIFO(size=1).wtPort(x2364_x2781_x2789_v)
      val ctr25 = Counter(min=Const(0), max=Const(3200), step=Const(1), par=16) // Counter
      val x2778 = CounterChain(name = "x2778", ctr25).iter(200)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2780_x2780), CU.load(x2781_x2781)), op=FixMul, results=List(CU.reduce))
      val (_, rr1234) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1234), op=Bypass, results=List(CU.scalarOut(x2685_x2787_s)))
    }
    val x2820 = StreamController(name="x2820",parent=x2822) { implicit CU => 
    }
    val x2820_0 = Pipeline(name="x2820_0",parent=x2820) { implicit CU => 
      val x2800 = CU.temp
      val x2803 = CU.temp
      val x2682_x2797 =  ScalarBuffer().wtPort(x2682_x2748_s)
      val x2679_x2792 =  ScalarBuffer().wtPort(x2679_x2709_s)
      val x2683_x2796 =  ScalarBuffer().wtPort(x2683_x2761_s)
      val x2678_x2793 =  ScalarBuffer().wtPort(x2678_x2696_s)
      val x2681_x2794 =  ScalarBuffer().wtPort(x2681_x2735_s)
      val x2680_x2795 =  ScalarBuffer().wtPort(x2680_x2722_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2678_x2793), CU.load(x2679_x2792)), op=FixAdd, results=List(x2800))
      Stage(operands=List(CU.load(x2680_x2795), CU.load(x2681_x2794)), op=FixAdd, results=List(x2803))
      Stage(operands=List(x2800, x2803), op=FixAdd, results=List(CU.scalarOut(bus_1238_s)))
      Stage(operands=List(CU.load(x2682_x2797), CU.load(x2683_x2796)), op=FixAdd, results=List(CU.scalarOut(bus_1242_s)))
    }
    val x2820_1 = Pipeline(name="x2820_1",parent=x2820) { implicit CU => 
      val x2812 = CU.temp
      val x2814 = CU.temp
      val x2685_x2798 =  ScalarBuffer().wtPort(x2685_x2787_s)
      val x2805 =  ScalarFIFO(size=1).wtPort(bus_1238_s)
      val x2684_x2799 =  ScalarBuffer().wtPort(x2684_x2774_s)
      val x2810 =  ScalarFIFO(size=1).wtPort(bus_1242_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x2684_x2799), CU.load(x2685_x2798)), op=FixAdd, results=List(x2812))
      Stage(operands=List(CU.load(x2810), x2812), op=FixAdd, results=List(x2814))
      Stage(operands=List(CU.load(x2805), x2814), op=FixAdd, results=List(CU.scalarOut(bus_1246_s)))
    }
    val x2820_2 = Pipeline(name="x2820_2",parent=x2820) { implicit CU => 
      val rr1246 =  ScalarFIFO(size=1).wtPort(bus_1246_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr1246)), op=Bypass, results=List(CU.reduce))
      val (_, rr1248) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1248), op=Bypass, results=List(CU.scalarOut(x2342_x2824_argout)))
    }
    
  }
}
