import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object LogReg extends PIRApp {
  def main(top:Top) = {
    val x2407_x2484_x2495_v = Vector("x2407_x2484_x2495")
    val x2448_b2643_x2458_b2645_s = Scalar("x2448_b2643_x2458_b2645")
    val x2413_x2530_s = Scalar("x2413_x2530")
    val x2421_b2637_x2434_b2639_s = Scalar("x2421_b2637_x2434_b2639")
    val x2407_x2539_x2545_s = Scalar("x2407_x2539_x2545")
    val x2407_x2562_x2566_v = Vector("x2407_x2562_x2566")
    val x2413_x2526_x2531_s = Scalar("x2413_x2526_x2531")
    val x2449_x2460_data_v = Vector("x2449_x2460_data")
    val x2450_argin = ArgIn("x2450")
    val x2422_x2436_data_v = Vector("x2422_x2436_data")
    val x2448_b2642_x2458_b2644_s = Scalar("x2448_b2642_x2458_b2644")
    val x2475_x2525_x2531_s = Scalar("x2475_x2525_x2531")
    val x2413_x2538_x2545_s = Scalar("x2413_x2538_x2545")
    val x2474_x2506_s = Scalar("x2474_x2506")
    val x2476_x2493_s = Scalar("x2476_x2493")
    val x2417_x2483_x2495_v = Vector("x2417_x2483_x2495")
    val x2401_oc = OffChip("x2401")
    val x2475_x2518_v = Vector("x2475_x2518")
    val x2423_argin = ArgIn("x2423")
    val x2400_oc = OffChip("x2400")
    val x2421_b2636_x2434_b2638_s = Scalar("x2421_b2636_x2434_b2638")
    val x2550_b2650_x2558_b2652_s = Scalar("x2550_b2650_x2558_b2652")
    val x2407_x2544_s = Scalar("x2407_x2544")
    val x2398_oc = OffChip("x2398")
    val x2418_x2499_x2507_s = Scalar("x2418_x2499_x2507")
    val x2550_b2651_x2558_b2653_s = Scalar("x2550_b2651_x2558_b2653")
    val x2394_argin = ArgIn("x2394")
    val x2393_argin = ArgIn("x2393")
    val x2417_x2514_x2519_v = Vector("x2417_x2514_x2519")
    val x2553_argin = ArgIn("x2553")
    val x2575 = Sequential(name="x2575",parent=top) { implicit CU => 
      val x2575_unit = CounterChain(name = "x2575_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2407_dsp0 = MemoryPipeline(name="x2407_dsp0",parent="x2548") { implicit CU => 
      val x2544_x2544 = ScalarFIFO(size=1).wtPort(x2407_x2544_s)
      val x2561 = CounterChain.copy("x2566", "x2561")
      val x2536 = CounterChain.copy("x2545_0", "x2536")
      val x2407_x2562 = SRAM(size=192,banking = Strided(1)).wtPort(x2544_x2544.readPort).rdPort(x2407_x2562_x2566_v).rdAddr(x2561(0)).wtAddr(x2536(0))
    }
    val x2407_dsp1 = MemoryPipeline(name="x2407_dsp1",parent="x2548") { implicit CU => 
      val x2544_x2544 = ScalarFIFO(size=1).wtPort(x2407_x2544_s)
      val x2536 = CounterChain.copy("x2545_0", "x2536")
      val x2407_x2539 = SRAM(size=192,banking = NoBanking()).wtPort(x2544_x2544.readPort).rdPort(x2407_x2539_x2545_s).rdAddr(x2536(0)).wtAddr(x2536(0))
    }
    val x2407_dsp2 = MemoryPipeline(name="x2407_dsp2",parent="x2548") { implicit CU => 
      val x2544_x2544 = ScalarFIFO(size=1).wtPort(x2407_x2544_s)
      val x2536 = CounterChain.copy("x2545_0", "x2536")
      val x2478 = CounterChain.copy("x2495_0", "x2478")
      val x2407_x2484 = SRAM(size=192,banking = Strided(1)).wtPort(x2544_x2544.readPort).rdPort(x2407_x2484_x2495_v).rdAddr(x2478(0)).wtAddr(x2536(0))
    }
    val x2549 = Sequential(name="x2549",parent=x2575) { implicit CU => 
      val x2393_x2408 = ScalarBuffer().wtPort(x2393_argin)
      val ctr1 = Counter(min=Const(0), max=x2393_x2408.load, step=Const(1), par=1) // Counter
      val x2410 = CounterChain(name = "x2410", ctr1).iter(1)
    }
    val x2548 = Sequential(name="x2548",parent=x2549) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x2412 = CounterChain(name = "x2412", ctr2).iter(1)
    }
    val x2413_dsp0 = MemoryPipeline(name="x2413_dsp0",parent="x2533") { implicit CU => 
      val x2530_x2530 = ScalarFIFO(size=1).wtPort(x2413_x2530_s)
      val x2521 = CounterChain.copy("x2531_0", "x2521")
      val x2536 = CounterChain.copy("x2545_0", "x2536")
      val x2413_x2538 = SRAM(size=192,banking = NoBanking()).wtPort(x2530_x2530.readPort).rdPort(x2413_x2538_x2545_s).rdAddr(x2536(0)).wtAddr(x2521(0))
    }
    val x2413_dsp1 = MemoryPipeline(name="x2413_dsp1",parent="x2533") { implicit CU => 
      val x2530_x2530 = ScalarFIFO(size=1).wtPort(x2413_x2530_s)
      val x2521 = CounterChain.copy("x2531_0", "x2521")
      val x2413_x2526 = SRAM(size=192,banking = NoBanking()).wtPort(x2530_x2530.readPort).rdPort(x2413_x2526_x2531_s).rdAddr(x2521(0)).wtAddr(x2521(0))
    }
    val x2534 = MetaPipeline(name="x2534",parent=x2548) { implicit CU => 
      val x2394_x2414 = ScalarBuffer().wtPort(x2394_argin)
      val ctr3 = Counter(min=Const(0), max=x2394_x2414.load, step=Const(16), par=1) // Counter
      val x2416 = CounterChain(name = "x2416", ctr3).iter(1)
    }
    val x2417_dsp0 = MemoryPipeline(name="x2417_dsp0",parent="x2534") { implicit CU => 
      val b2640 = CU.temp()
      val b2648 = CU.temp()
      val x2445_x2445 = VectorFIFO(size=1).wtPort(x2422_x2436_data_v)
      val x2438 = CounterChain.copy("x2446", "x2438")
      val x2509 = CounterChain.copy("x2519_0", "x2509")
      val x2473 = CounterChain.copy("x2533", "x2473")
      val x2420 = CounterChain.copy("x2447", "x2420")
      val x2417_x2514 = SRAM(size=3072,banking = Strided(1)).wtPort(x2445_x2445.readPort).rdPort(x2417_x2514_x2519_v)
      WAStage(operands=List(CU.ctr(x2420(0)), Const(192)), op=FixMul, results=List(b2640))
      WAStage(operands=List(b2640, CU.ctr(x2438(0))), op=FixAdd, results=List(x2417_x2514.writeAddr))
      RAStage(operands=List(CU.ctr(x2473(0)), Const(192)), op=FixMul, results=List(b2648))
      RAStage(operands=List(b2648, CU.ctr(x2509(0))), op=FixAdd, results=List(x2417_x2514.readAddr))
    }
    val x2417_dsp1 = MemoryPipeline(name="x2417_dsp1",parent="x2534") { implicit CU => 
      val b2640 = CU.temp()
      val b2646 = CU.temp()
      val x2445_x2445 = VectorFIFO(size=1).wtPort(x2422_x2436_data_v)
      val x2438 = CounterChain.copy("x2446", "x2438")
      val x2473 = CounterChain.copy("x2533", "x2473")
      val x2478 = CounterChain.copy("x2495_0", "x2478")
      val x2420 = CounterChain.copy("x2447", "x2420")
      val x2417_x2483 = SRAM(size=3072,banking = Strided(1)).wtPort(x2445_x2445.readPort).rdPort(x2417_x2483_x2495_v)
      WAStage(operands=List(CU.ctr(x2420(0)), Const(192)), op=FixMul, results=List(b2640))
      WAStage(operands=List(b2640, CU.ctr(x2438(0))), op=FixAdd, results=List(x2417_x2483.writeAddr))
      RAStage(operands=List(CU.ctr(x2473(0)), Const(192)), op=FixMul, results=List(b2646))
      RAStage(operands=List(b2646, CU.ctr(x2478(0))), op=FixAdd, results=List(x2417_x2483.readAddr))
    }
    val x2418_dsp0 = MemoryPipeline(name="x2418_dsp0",parent="x2534") { implicit CU => 
      val x2468_x2468 = VectorFIFO(size=1).wtPort(x2449_x2460_data_v)
      val x2462 = CounterChain.copy("x2469", "x2462")
      val x2473 = CounterChain.copy("x2533", "x2473")
      val x2418_x2499 = SRAM(size=16,banking = Strided(1)).wtPort(x2468_x2468.readPort).rdPort(x2418_x2499_x2507_s).rdAddr(x2473(0)).wtAddr(x2462(0))
    }
    val x2447 = StreamController(name="x2447",parent=x2534) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2420 = CounterChain(name = "x2420", ctr4).iter(16)
    }
    val x2435_0 = Pipeline(name="x2435_0",parent=x2447) { implicit CU => 
      val x2426 = CU.temp()
      val x2424 = CU.temp()
      val x2425 = CU.temp()
      val x2423 = ScalarBuffer().wtPort(x2423_argin)
      val x2416 = CounterChain.copy("x2534", "x2416")
      val x2420 = CounterChain.copy("x2447", "x2420")
      val x2435_unit = CounterChain(name = "x2435_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2416(0)), CU.ctr(x2420(0))), op=FixAdd, results=List(x2424))
      Stage(operands=List(x2424, Const(192)), op=FixMul, results=List(x2425))
      Stage(operands=List(x2425, Const(4)), op=FixMul, results=List(x2426))
      Stage(operands=List(x2426, CU.load(x2423)), op=FixAdd, results=List(CU.scalarOut(x2421_b2636_x2434_b2638_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x2421_b2637_x2434_b2639_s)))
    }
    val x2436 = MemoryController(name="x2436",parent=x2447,offchip=x2398_oc, mctpe=TileLoad) { implicit CU => 
      val x2421_b2637_x2436 = ScalarFIFO(name="size",size=1).wtPort(x2421_b2637_x2434_b2639_s)
      val x2421_b2636_x2436 = ScalarFIFO(name="offset",size=1).wtPort(x2421_b2636_x2434_b2638_s)
      CU.newVout("data", x2422_x2436_data_v)
    }
    val x2446 = Pipeline(name="x2446",parent=x2447) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x2438 = CounterChain(name = "x2438", ctr5).iter(12)
    }
    val x2470 = StreamController(name="x2470",parent=x2534) { implicit CU => 
      val x2470_unit = CounterChain(name = "x2470_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2459_0 = Pipeline(name="x2459_0",parent=x2470) { implicit CU => 
      val x2451 = CU.temp()
      val x2450 = ScalarBuffer().wtPort(x2450_argin)
      val x2416 = CounterChain.copy("x2534", "x2416")
      val x2459_unit = CounterChain(name = "x2459_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x2416(0)), Const(4)), op=FixMul, results=List(x2451))
      Stage(operands=List(x2451, CU.load(x2450)), op=FixAdd, results=List(CU.scalarOut(x2448_b2642_x2458_b2644_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x2448_b2643_x2458_b2645_s)))
    }
    val x2460 = MemoryController(name="x2460",parent=x2470,offchip=x2400_oc, mctpe=TileLoad) { implicit CU => 
      val x2448_b2643_x2460 = ScalarFIFO(name="size",size=1).wtPort(x2448_b2643_x2458_b2645_s)
      val x2448_b2642_x2460 = ScalarFIFO(name="offset",size=1).wtPort(x2448_b2642_x2458_b2644_s)
      CU.newVout("data", x2449_x2460_data_v)
    }
    val x2469 = Pipeline(name="x2469",parent=x2470) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x2462 = CounterChain(name = "x2462", ctr6).iter(1)
    }
    val x2533 = MetaPipeline(name="x2533",parent=x2534) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x2473 = CounterChain(name = "x2473", ctr7).iter(16)
    }
    val x2475_dsp0 = MemoryPipeline(name="x2475_dsp0",parent="x2533") { implicit CU => 
      val x2518_x2518 = VectorFIFO(size=1).wtPort(x2475_x2518_v)
      val x2509 = CounterChain.copy("x2519_0", "x2509")
      val x2521 = CounterChain.copy("x2531_0", "x2521")
      val x2475_x2525 = SRAM(size=192,banking = Strided(1)).wtPort(x2518_x2518.readPort).rdPort(x2475_x2525_x2531_s).rdAddr(x2521(0)).wtAddr(x2509(0))
    }
    val x2495_0 = Pipeline(name="x2495_0",parent=x2533) { implicit CU => 
      val x2484_x2484 = VectorFIFO(size=1).wtPort(x2407_x2484_x2495_v)
      val x2483_x2483 = VectorFIFO(size=1).wtPort(x2417_x2483_x2495_v)
      val ctr8 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x2478 = CounterChain(name = "x2478", ctr8).iter(12)
      Stage(operands=List(CU.load(x2483_x2483), CU.load(x2484_x2484)), op=FltMul, results=List(CU.reduce))
      val (_, rr411) = Stage.reduce(op=FltAdd, init=Const(0), accumParent="x2495_0")
      Stage(operands=List(rr411), op=Bypass, results=List(CU.scalarOut(x2476_x2493_s)))
    }
    val x2507_0 = Pipeline(name="x2507_0",parent=x2533) { implicit CU => 
      val x2501 = CU.temp()
      val x2503 = CU.temp()
      val x2504 = CU.temp()
      val x2502 = CU.temp()
      val x2499_x2499 = ScalarFIFO(size=1).wtPort(x2418_x2499_x2507_s)
      val x2476_x2500 = ScalarBuffer().wtPort(x2476_x2493_s)
      val x2507_unit = CounterChain(name = "x2507_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x2476_x2500)), op=FltNeg, results=List(x2501))
      Stage(operands=List(x2501), op=FltExp, results=List(x2502))
      Stage(operands=List(x2502, Const(1)), op=FltAdd, results=List(x2503))
      Stage(operands=List(Const(1), x2503), op=FltDiv, results=List(x2504))
      Stage(operands=List(CU.load(x2499_x2499), x2504), op=FltSub, results=List(CU.scalarOut(x2474_x2506_s)))
    }
    val x2519_0 = Pipeline(name="x2519_0",parent=x2533) { implicit CU => 
      val x2514_x2514 = VectorFIFO(size=1).wtPort(x2417_x2514_x2519_v)
      val x2474_x2515 = ScalarBuffer().wtPort(x2474_x2506_s)
      val ctr9 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x2509 = CounterChain(name = "x2509", ctr9).iter(12)
      Stage(operands=List(CU.load(x2514_x2514), CU.load(x2474_x2515)), op=FltSub, results=List(CU.vecOut(x2475_x2518_v)))
    }
    val x2531_0 = Pipeline(name="x2531_0",parent=x2533) { implicit CU => 
      val x2526_x2526 = ScalarFIFO(size=1).wtPort(x2413_x2526_x2531_s)
      val x2525_x2525 = ScalarFIFO(size=1).wtPort(x2475_x2525_x2531_s)
      val ctr10 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x2521 = CounterChain(name = "x2521", ctr10).iter(192)
      Stage(operands=List(CU.load(x2525_x2525), CU.load(x2526_x2526)), op=FltAdd, results=List(CU.scalarOut(x2413_x2530_s)))
    }
    val x2545_0 = Pipeline(name="x2545_0",parent=x2548) { implicit CU => 
      val x2542 = CU.temp()
      val x2538_x2538 = ScalarFIFO(size=1).wtPort(x2413_x2538_x2545_s)
      val x2539_x2539 = ScalarFIFO(size=1).wtPort(x2407_x2539_x2545_s)
      val ctr11 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x2536 = CounterChain(name = "x2536", ctr11).iter(192)
      Stage(operands=List(CU.load(x2539_x2539), Const(1)), op=FltMul, results=List(x2542))
      Stage(operands=List(CU.load(x2538_x2538), x2542), op=FltAdd, results=List(CU.scalarOut(x2407_x2544_s)))
    }
    val x2574 = StreamController(name="x2574",parent=x2575) { implicit CU => 
      val x2574_unit = CounterChain(name = "x2574_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2567 = Sequential(name="x2567",parent=x2574) { implicit CU => 
      val x2567_unit = CounterChain(name = "x2567_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x2559_0 = Pipeline(name="x2559_0",parent=x2567) { implicit CU => 
      val x2553 = ScalarBuffer().wtPort(x2553_argin)
      val x2559_unit = CounterChain(name = "x2559_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), CU.load(x2553)), op=FixAdd, results=List(CU.scalarOut(x2550_b2650_x2558_b2652_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x2550_b2651_x2558_b2653_s)))
    }
    val x2566 = Pipeline(name="x2566",parent=x2567) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x2561 = CounterChain(name = "x2561", ctr12).iter(12)
    }
    val x2568 = MemoryController(name="x2568",parent=x2574,offchip=x2401_oc, mctpe=TileStore) { implicit CU => 
      val x2550_b2651_x2568 = ScalarFIFO(name="size",size=1).wtPort(x2550_b2651_x2558_b2653_s)
      val x2550_b2650_x2568 = ScalarFIFO(name="offset",size=1).wtPort(x2550_b2650_x2558_b2652_s)
      val x2551_x2568 = VectorFIFO(name="data",size=1).wtPort(x2407_x2562_x2566_v)
    }
    
  }
}
