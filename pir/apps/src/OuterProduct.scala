import pir._
import pir.node._
import arch._
import pirc.enums._

object OuterProduct extends PIRApp {
  def main(top:Top) = {
    val x2394_b2572_x2404_b2574_s = Scalar("x2394_b2572_x2404_b2574")
    val x2343_x2462_wa_v = Vector("x2343_x2462_wa")
    val x2342_x2450_wa_v = Vector("x2342_x2450_wa")
    val x2418_b2577_x2428_b2579_s = Scalar("x2418_b2577_x2428_b2579")
    val x2338_x2365_wa_v = Vector("x2338_x2365_wa")
    val x2346_b2562_x2356_b2564_s = Scalar("x2346_b2562_x2356_b2564")
    val vec2_oc = OffChip("vec2")
    val x2472_b2590_x2489_b2592_s = Scalar("x2472_b2590_x2489_b2592")
    val x2338_x2446_ra_s = Scalar("x2338_x2446_ra")
    val x2341_x2437_wa_v = Vector("x2341_x2437_wa")
    val x2338_x2338_dsp0_bank0_data_s = Scalar("x2338_x2338_dsp0_bank0_data")
    val x2339_x2339_dsp0_bank0_data_s = Scalar("x2339_x2339_dsp0_bank0_data")
    val x2394_b2573_x2404_b2575_s = Scalar("x2394_b2573_x2404_b2575")
    val x2347_x2358_data_v = Vector("x2347_x2358_data")
    val vec2_da = DRAMAddress("vec2", "vec2")
    val x2342_x2450_data_v = Vector("x2342_x2450_data")
    val sizeB_argin = ArgIn("sizeB").bound(38400)
    val x2509_b2596_x2526_b2598_s = Scalar("x2509_b2596_x2526_b2598")
    val vec1_oc = OffChip("vec1")
    val x2341_x2341_dsp0_bank0_data_v = Vector("x2341_x2341_dsp0_bank0_data")
    val out_da = DRAMAddress("out", "out")
    val x2343_x2533_ra_v = Vector("x2343_x2533_ra")
    val x2370_b2567_x2380_b2569_s = Scalar("x2370_b2567_x2380_b2569")
    val x2339_x2413_wa_v = Vector("x2339_x2413_wa")
    val x2509_b2597_x2526_b2599_s = Scalar("x2509_b2597_x2526_b2599")
    val x2343_x2343_dsp0_bank0_data_v = Vector("x2343_x2343_dsp0_bank0_data")
    val x2343_x2462_data_v = Vector("x2343_x2462_data")
    val x2340_x2389_wa_v = Vector("x2340_x2389_wa")
    val x2346_b2563_x2356_b2565_s = Scalar("x2346_b2563_x2356_b2565")
    val vec1_da = DRAMAddress("vec1", "vec1")
    val x2371_x2382_data_v = Vector("x2371_x2382_data")
    val x2472_b2591_x2489_b2593_s = Scalar("x2472_b2591_x2489_b2593")
    val sizeA_argin = ArgIn("sizeA").bound(38400)
    val x2340_x2447_ra_v = Vector("x2340_x2447_ra")
    val x2341_x2459_ra_v = Vector("x2341_x2459_ra")
    val x2370_b2568_x2380_b2570_s = Scalar("x2370_b2568_x2380_b2570")
    val x2395_x2406_data_v = Vector("x2395_x2406_data")
    val x2418_b2578_x2428_b2580_s = Scalar("x2418_b2578_x2428_b2580")
    val x2340_x2340_dsp0_bank0_data_v = Vector("x2340_x2340_dsp0_bank0_data")
    val x2342_x2342_dsp0_bank0_data_v = Vector("x2342_x2342_dsp0_bank0_data")
    val x2339_x2458_ra_s = Scalar("x2339_x2458_ra")
    val x2342_x2496_ra_v = Vector("x2342_x2496_ra")
    val out_oc = OffChip("out")
    val x2419_x2430_data_v = Vector("x2419_x2430_data")
    val x2544 = MetaPipeline(name="x2544",parent="top") { implicit CU => 
      val x2320 = ScalarBuffer(name="x2320")
        .buffering(1)
        .store(sizeA_argin, None, None)
      val x2321 = ScalarBuffer(name="x2321")
        .buffering(1)
        .store(sizeB_argin, None, None)
      val ctr1 = Counter(min=Const(0), max=x2320.readPort, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x2321.readPort, step=Const(16), par=2) // Counter
      val x2337 = CounterChain(name = "x2337", ctr1, ctr2).iter(2880000)
    }
    val x2338_dsp0_bank0 = MemoryPipeline(name="x2338_dsp0_bank0",parent="x2544") { implicit CU => 
      val x2365 = VectorFIFO(size=1,name="x2365")
        .store(x2347_x2358_data_v, None, None)
      val b2582 = ScalarFIFO(size=1,name="b2582")
        .store(x2338_x2446_ra_s, None, None)
      val b2566 = VectorFIFO(size=1,name="b2566")
        .store(x2338_x2365_wa_v, None, None)
      val x2338 = SRAM(size=1,name="x2338",banking = Strided(1,16))
        .buffering(2)
        .store(x2365.readPort, Some(b2566.readPort), Some("x2367"))
        .load(x2338_x2338_dsp0_bank0_data_s, Some(b2582.readPort), Some("x2451_0"))
    }
    val x2339_dsp0_bank0 = MemoryPipeline(name="x2339_dsp0_bank0",parent="x2544") { implicit CU => 
      val b2586 = ScalarFIFO(size=1,name="b2586")
        .store(x2339_x2458_ra_s, None, None)
      val b2576 = VectorFIFO(size=1,name="b2576")
        .store(x2339_x2413_wa_v, None, None)
      val x2413 = VectorFIFO(size=1,name="x2413")
        .store(x2395_x2406_data_v, None, None)
      val x2339 = SRAM(size=1,name="x2339",banking = Strided(1,16))
        .buffering(2)
        .store(x2413.readPort, Some(b2576.readPort), Some("x2415"))
        .load(x2339_x2339_dsp0_bank0_data_s, Some(b2586.readPort), Some("x2463_0"))
    }
    val x2340_dsp0_bank0 = MemoryPipeline(name="x2340_dsp0_bank0",parent="x2544") { implicit CU => 
      val x2389 = VectorFIFO(size=1,name="x2389")
        .store(x2371_x2382_data_v, None, None)
      val b2571 = VectorFIFO(size=1,name="b2571")
        .store(x2340_x2389_wa_v, None, None)
      val b2583 = VectorFIFO(size=1,name="b2583")
        .store(x2340_x2447_ra_v, None, None)
      val x2340 = SRAM(size=1,name="x2340",banking = Strided(1,16))
        .buffering(2)
        .store(x2389.readPort, Some(b2571.readPort), Some("x2391"))
        .load(x2340_x2340_dsp0_bank0_data_v, Some(b2583.readPort), Some("x2451_0"))
    }
    val x2341_dsp0_bank0 = MemoryPipeline(name="x2341_dsp0_bank0",parent="x2544") { implicit CU => 
      val b2587 = VectorFIFO(size=1,name="b2587")
        .store(x2341_x2459_ra_v, None, None)
      val x2437 = VectorFIFO(size=1,name="x2437")
        .store(x2419_x2430_data_v, None, None)
      val b2581 = VectorFIFO(size=1,name="b2581")
        .store(x2341_x2437_wa_v, None, None)
      val x2341 = SRAM(size=1,name="x2341",banking = Strided(1,16))
        .buffering(2)
        .store(x2437.readPort, Some(b2581.readPort), Some("x2439"))
        .load(x2341_x2341_dsp0_bank0_data_v, Some(b2587.readPort), Some("x2463_0"))
    }
    val x2342_dsp0_bank0 = MemoryPipeline(name="x2342_dsp0_bank0",parent="x2544") { implicit CU => 
      val b2595 = VectorFIFO(size=1,name="b2595")
        .store(x2342_x2496_ra_v, None, None)
      val x2450 = VectorFIFO(size=1,name="x2450")
        .store(x2342_x2450_data_v, None, None)
      val b2585 = VectorFIFO(size=1,name="b2585")
        .store(x2342_x2450_wa_v, None, None)
      val x2342 = SRAM(size=16,name="x2342",banking = Strided(1,16))
        .buffering(3)
        .store(x2450.readPort, Some(b2585.readPort), Some("x2451_0"))
        .load(x2342_x2342_dsp0_bank0_data_v, Some(b2595.readPort), Some("x2506"))
    }
    val x2343_dsp0_bank0 = MemoryPipeline(name="x2343_dsp0_bank0",parent="x2544") { implicit CU => 
      val b2589 = VectorFIFO(size=1,name="b2589")
        .store(x2343_x2462_wa_v, None, None)
      val b2601 = VectorFIFO(size=1,name="b2601")
        .store(x2343_x2533_ra_v, None, None)
      val x2462 = VectorFIFO(size=1,name="x2462")
        .store(x2343_x2462_data_v, None, None)
      val x2343 = SRAM(size=16,name="x2343",banking = Strided(1,16))
        .buffering(3)
        .store(x2462.readPort, Some(b2589.readPort), Some("x2463_0"))
        .load(x2343_x2343_dsp0_bank0_data_v, Some(b2601.readPort), Some("x2543"))
    }
    val x2367 = StreamController(name="x2367",parent="x2544") { implicit CU => 
      val x2367_unit = CounterChain(name = "x2367_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2357_0 = Pipeline(name="x2357_0",parent="x2367") { implicit CU => 
      val x2349 = CU.temp(None).name("x2349")
      val x2351 = ScalarBuffer(name="x2351")
        .buffering(1)
        .store(vec1_da, None, None)
      val x2337 = CounterChain.copy("x2544", "x2337").iterIdx(0, 0)
      val x2357_unit = CounterChain(name = "x2357_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2337(0), Const(2)), op=FixSla, results=List(x2349))
      Stage(operands=List(x2349, x2351), op=FixAdd, results=List(CU.scalarOut(x2346_b2562_x2356_b2564_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2346_b2563_x2356_b2565_s)))
    }
    val x2358 = MemoryController(name="x2358",parent="x2367",offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x2346_b2562 = ScalarFIFO(size=1,name="offset")
        .store(x2346_b2562_x2356_b2564_s, None, None)
      val x2346_b2563 = ScalarFIFO(size=1,name="size")
        .store(x2346_b2563_x2356_b2565_s, None, None)
      CU.newOut("data", x2347_x2358_data_v)
    }
    val x2366_0 = Pipeline(name="x2366_0",parent="x2367") { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2360 = CounterChain(name = "x2360", ctr3).iter(1)
      Stage(operands=List(x2360(0)), op=Bypass, results=List(CU.vecOut(x2338_x2365_wa_v)))
    }
    val x2391 = StreamController(name="x2391",parent="x2544") { implicit CU => 
      val x2391_unit = CounterChain(name = "x2391_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2381_0 = Pipeline(name="x2381_0",parent="x2391") { implicit CU => 
      val x2373 = CU.temp(None).name("x2373")
      val x2375 = ScalarBuffer(name="x2375")
        .buffering(1)
        .store(vec2_da, None, None)
      val x2337 = CounterChain.copy("x2544", "x2337").iterIdx(1, 0)
      val x2381_unit = CounterChain(name = "x2381_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2337(1), Const(2)), op=FixSla, results=List(x2373))
      Stage(operands=List(x2373, x2375), op=FixAdd, results=List(CU.scalarOut(x2370_b2567_x2380_b2569_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2370_b2568_x2380_b2570_s)))
    }
    val x2382 = MemoryController(name="x2382",parent="x2391",offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x2370_b2568 = ScalarFIFO(size=1,name="size")
        .store(x2370_b2568_x2380_b2570_s, None, None)
      val x2370_b2567 = ScalarFIFO(size=1,name="offset")
        .store(x2370_b2567_x2380_b2569_s, None, None)
      CU.newOut("data", x2371_x2382_data_v)
    }
    val x2390_0 = Pipeline(name="x2390_0",parent="x2391") { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2384 = CounterChain(name = "x2384", ctr4).iter(1)
      Stage(operands=List(x2384(0)), op=Bypass, results=List(CU.vecOut(x2340_x2389_wa_v)))
    }
    val x2415 = StreamController(name="x2415",parent="x2544") { implicit CU => 
      val x2415_unit = CounterChain(name = "x2415_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2405_0 = Pipeline(name="x2405_0",parent="x2415") { implicit CU => 
      val x2397 = CU.temp(None).name("x2397")
      val x2399 = ScalarBuffer(name="x2399")
        .buffering(1)
        .store(vec1_da, None, None)
      val x2337 = CounterChain.copy("x2544", "x2337").iterIdx(0, 0)
      val x2405_unit = CounterChain(name = "x2405_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2337(0), Const(2)), op=FixSla, results=List(x2397))
      Stage(operands=List(x2397, x2399), op=FixAdd, results=List(CU.scalarOut(x2394_b2572_x2404_b2574_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2394_b2573_x2404_b2575_s)))
    }
    val x2406 = MemoryController(name="x2406",parent="x2415",offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x2394_b2573 = ScalarFIFO(size=1,name="size")
        .store(x2394_b2573_x2404_b2575_s, None, None)
      val x2394_b2572 = ScalarFIFO(size=1,name="offset")
        .store(x2394_b2572_x2404_b2574_s, None, None)
      CU.newOut("data", x2395_x2406_data_v)
    }
    val x2414_0 = Pipeline(name="x2414_0",parent="x2415") { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2408 = CounterChain(name = "x2408", ctr5).iter(1)
      Stage(operands=List(x2408(0)), op=Bypass, results=List(CU.vecOut(x2339_x2413_wa_v)))
    }
    val x2439 = StreamController(name="x2439",parent="x2544") { implicit CU => 
      val x2439_unit = CounterChain(name = "x2439_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2429_0 = Pipeline(name="x2429_0",parent="x2439") { implicit CU => 
      val x2421 = CU.temp(None).name("x2421")
      val x2423 = ScalarBuffer(name="x2423")
        .buffering(1)
        .store(vec2_da, None, None)
      val x2337 = CounterChain.copy("x2544", "x2337").iterIdx(1, 1)
      val x2429_unit = CounterChain(name = "x2429_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2337(1), Const(2)), op=FixSla, results=List(x2421))
      Stage(operands=List(x2421, x2423), op=FixAdd, results=List(CU.scalarOut(x2418_b2577_x2428_b2579_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2418_b2578_x2428_b2580_s)))
    }
    val x2430 = MemoryController(name="x2430",parent="x2439",offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x2418_b2577 = ScalarFIFO(size=1,name="offset")
        .store(x2418_b2577_x2428_b2579_s, None, None)
      val x2418_b2578 = ScalarFIFO(size=1,name="size")
        .store(x2418_b2578_x2428_b2580_s, None, None)
      CU.newOut("data", x2419_x2430_data_v)
    }
    val x2438_0 = Pipeline(name="x2438_0",parent="x2439") { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2432 = CounterChain(name = "x2432", ctr6).iter(1)
      Stage(operands=List(x2432(0)), op=Bypass, results=List(CU.vecOut(x2341_x2437_wa_v)))
    }
    val x2451_0 = Pipeline(name="x2451_0",parent="x2544") { implicit CU => 
      val b2584 = CU.temp(None).name("b2584")
      val x2447 = VectorFIFO(size=1,name="x2447")
        .store(x2340_x2340_dsp0_bank0_data_v, None, None)
      val x2446 = ScalarFIFO(size=1,name="x2446")
        .store(x2338_x2338_dsp0_bank0_data_s, None, None)
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2442 = CounterChain(name = "x2442", ctr7, ctr8).iter(16)
      Stage(operands=List(x2446, x2447), op=FixMul, results=List(CU.vecOut(x2342_x2450_data_v)))
      Stage(operands=List(x2442(0), Const(16)), op=FixMul, results=List(b2584))
      Stage(operands=List(b2584, x2442(1)), op=FixAdd, results=List(CU.vecOut(x2342_x2450_wa_v)))
    }
    val x2446_0 = Pipeline(name="x2446_0",parent="x2544") { implicit CU => 
      val x2442 = CounterChain.copy("x2451_0", "x2442").iterIdx(0, 0)
      Stage(operands=List(x2442(0)), op=Bypass, results=List(CU.scalarOut(x2338_x2446_ra_s)))
    }
    val x2447_0 = Pipeline(name="x2447_0",parent="x2544") { implicit CU => 
      val x2442 = CounterChain.copy("x2451_0", "x2442").iterIdx(1, 0)
      Stage(operands=List(x2442(1)), op=Bypass, results=List(CU.vecOut(x2340_x2447_ra_v)))
    }
    val x2463_0 = Pipeline(name="x2463_0",parent="x2544") { implicit CU => 
      val b2588 = CU.temp(None).name("b2588")
      val x2459 = VectorFIFO(size=1,name="x2459")
        .store(x2341_x2341_dsp0_bank0_data_v, None, None)
      val x2458 = ScalarFIFO(size=1,name="x2458")
        .store(x2339_x2339_dsp0_bank0_data_s, None, None)
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2454 = CounterChain(name = "x2454", ctr9, ctr10).iter(16)
      Stage(operands=List(x2458, x2459), op=FixMul, results=List(CU.vecOut(x2343_x2462_data_v)))
      Stage(operands=List(x2454(0), Const(16)), op=FixMul, results=List(b2588))
      Stage(operands=List(b2588, x2454(1)), op=FixAdd, results=List(CU.vecOut(x2343_x2462_wa_v)))
    }
    val x2458_0 = Pipeline(name="x2458_0",parent="x2544") { implicit CU => 
      val x2454 = CounterChain.copy("x2463_0", "x2454").iterIdx(0, 0)
      Stage(operands=List(x2454(0)), op=Bypass, results=List(CU.scalarOut(x2339_x2458_ra_s)))
    }
    val x2459_0 = Pipeline(name="x2459_0",parent="x2544") { implicit CU => 
      val x2454 = CounterChain.copy("x2463_0", "x2454").iterIdx(1, 0)
      Stage(operands=List(x2454(1)), op=Bypass, results=List(CU.vecOut(x2341_x2459_ra_v)))
    }
    val x2506 = StreamController(name="x2506",parent="x2544") { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2471 = CounterChain(name = "x2471", ctr11).iter(16)
    }
    val x2490_0 = Pipeline(name="x2490_0",parent="x2506") { implicit CU => 
      val x2481 = CU.temp(None).name("x2481")
      val x2480 = CU.temp(None).name("x2480")
      val x2478 = CU.temp(None).name("x2478")
      val x2475 = CU.temp(None).name("x2475")
      val x2483 = ScalarBuffer(name="x2483")
        .buffering(1)
        .store(out_da, None, None)
      val x2321 = ScalarBuffer(name="x2321")
        .buffering(1)
        .store(sizeB_argin, None, None)
      val x2337 = CounterChain.copy("x2544", "x2337").iterIdx(0, 0)
      val x2471 = CounterChain.copy("x2506", "x2471").iterIdx(0, 0)
      val x2490_unit = CounterChain(name = "x2490_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2337(0), x2471(0)), op=FixAdd, results=List(x2475))
      Stage(operands=List(x2475, x2321), op=FixMul, results=List(x2478))
      Stage(operands=List(x2478, x2337(1)), op=FixAdd, results=List(x2480))
      Stage(operands=List(x2480, Const(2)), op=FixSla, results=List(x2481))
      Stage(operands=List(x2481, x2483), op=FixAdd, results=List(CU.scalarOut(x2472_b2590_x2489_b2592_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2472_b2591_x2489_b2593_s)))
    }
    val x2500 = Pipeline(name="x2500",parent="x2506") { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2492 = CounterChain(name = "x2492", ctr12).iter(1)
    }
    val x2496_0 = Pipeline(name="x2496_0",parent="x2506") { implicit CU => 
      val b2594 = CU.temp(None).name("b2594")
      val x2471 = CounterChain.copy("x2506", "x2471").iterIdx(0, 0)
      val x2492 = CounterChain.copy("x2500", "x2492").iterIdx(0, 0)
      Stage(operands=List(x2471(0), Const(16)), op=FixMul, results=List(b2594))
      Stage(operands=List(b2594, x2492(0)), op=FixAdd, results=List(CU.vecOut(x2342_x2496_ra_v)))
    }
    val x2501 = MemoryController(name="x2501",parent="x2506",offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x2472_b2591 = ScalarFIFO(size=1,name="size")
        .store(x2472_b2591_x2489_b2593_s, None, None)
      val x2473 = VectorFIFO(size=1,name="data")
        .store(x2342_x2342_dsp0_bank0_data_v, None, None)
      val x2472_b2590 = ScalarFIFO(size=1,name="offset")
        .store(x2472_b2590_x2489_b2592_s, None, None)
    }
    val x2543 = StreamController(name="x2543",parent="x2544") { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2508 = CounterChain(name = "x2508", ctr13).iter(16)
    }
    val x2527_0 = Pipeline(name="x2527_0",parent="x2543") { implicit CU => 
      val x2517 = CU.temp(None).name("x2517")
      val x2512 = CU.temp(None).name("x2512")
      val x2515 = CU.temp(None).name("x2515")
      val x2518 = CU.temp(None).name("x2518")
      val x2520 = ScalarBuffer(name="x2520")
        .buffering(1)
        .store(out_da, None, None)
      val x2321 = ScalarBuffer(name="x2321")
        .buffering(1)
        .store(sizeB_argin, None, None)
      val x2337 = CounterChain.copy("x2544", "x2337").iterIdx(0, 0)
      val x2508 = CounterChain.copy("x2543", "x2508").iterIdx(0, 0)
      val x2527_unit = CounterChain(name = "x2527_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x2337(0), x2508(0)), op=FixAdd, results=List(x2512))
      Stage(operands=List(x2512, x2321), op=FixMul, results=List(x2515))
      Stage(operands=List(x2515, x2337(1)), op=FixAdd, results=List(x2517))
      Stage(operands=List(x2517, Const(2)), op=FixSla, results=List(x2518))
      Stage(operands=List(x2518, x2520), op=FixAdd, results=List(CU.scalarOut(x2509_b2596_x2526_b2598_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2509_b2597_x2526_b2599_s)))
    }
    val x2537 = Pipeline(name="x2537",parent="x2543") { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2529 = CounterChain(name = "x2529", ctr14).iter(1)
    }
    val x2533_0 = Pipeline(name="x2533_0",parent="x2543") { implicit CU => 
      val b2600 = CU.temp(None).name("b2600")
      val x2508 = CounterChain.copy("x2543", "x2508").iterIdx(0, 0)
      val x2529 = CounterChain.copy("x2537", "x2529").iterIdx(0, 0)
      Stage(operands=List(x2508(0), Const(16)), op=FixMul, results=List(b2600))
      Stage(operands=List(b2600, x2529(0)), op=FixAdd, results=List(CU.vecOut(x2343_x2533_ra_v)))
    }
    val x2538 = MemoryController(name="x2538",parent="x2543",offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x2509_b2597 = ScalarFIFO(size=1,name="size")
        .store(x2509_b2597_x2526_b2599_s, None, None)
      val x2510 = VectorFIFO(size=1,name="data")
        .store(x2343_x2343_dsp0_bank0_data_v, None, None)
      val x2509_b2596 = ScalarFIFO(size=1,name="offset")
        .store(x2509_b2596_x2526_b2598_s, None, None)
    }
    
  }
}
