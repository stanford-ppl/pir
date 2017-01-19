import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_outerDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x6506_x6842_addr_vector = Vector("x6506_x6842_addr")
    val bus_2687_vector = Vector("bus_2687")
    val x6508_vector = Vector("x6508")
    val bus_2716_vector = Vector("bus_2716")
    val x6511_x6857_addr_vector = Vector("x6511_x6857_addr")
    val x6502_x6830_addr_vector = Vector("x6502_x6830_addr")
    val bus_2710_vector = Vector("bus_2710")
    val x6507_x6845_addr_vector = Vector("x6507_x6845_addr")
    val x6510_vector = Vector("x6510")
    val x6512_x6860_addr_vector = Vector("x6512_x6860_addr")
    val bus_2700_vector = Vector("bus_2700")
    val x6511_vector = Vector("x6511")
    val bus_2724_vector = Vector("bus_2724")
    val x6394_vector = Vector("x6394")
    val x6507_vector = Vector("x6507")
    val x6514_vector = Vector("x6514")
    val x6503_x6833_addr_vector = Vector("x6503_x6833_addr")
    val x6513_vector = Vector("x6513")
    val x6509_vector = Vector("x6509")
    val x6505_x6839_addr_vector = Vector("x6505_x6839_addr")
    val x6049_oc = OffChip("x6049")
    val x6504_vector = Vector("x6504")
    val x6510_x6854_addr_vector = Vector("x6510_x6854_addr")
    val x6509_x6851_addr_vector = Vector("x6509_x6851_addr")
    val x6508_x6848_addr_vector = Vector("x6508_x6848_addr")
    val bus_2693_vector = Vector("bus_2693")
    val x6515_vector = Vector("x6515")
    val x6394_x6872_addr_vector = Vector("x6394_x6872_addr")
    val x6506_vector = Vector("x6506")
    val x6501_vector = Vector("x6501")
    val bus_2688_vector = Vector("bus_2688")
    val bus_2721_vector = Vector("bus_2721")
    val bus_2682_vector = Vector("bus_2682")
    val bus_2705_vector = Vector("bus_2705")
    val x6048_oc = OffChip("x6048")
    val bus_2698_vector = Vector("bus_2698")
    val x6514_x6866_addr_vector = Vector("x6514_x6866_addr")
    val x6513_x6863_addr_vector = Vector("x6513_x6863_addr")
    val x6503_vector = Vector("x6503")
    val x6500_vector = Vector("x6500")
    val x6515_x6869_addr_vector = Vector("x6515_x6869_addr")
    val x6051_oc = OffChip("x6051")
    val bus_2711_vector = Vector("bus_2711")
    val x6504_x6836_addr_vector = Vector("x6504_x6836_addr")
    val x6505_vector = Vector("x6505")
    val x6512_vector = Vector("x6512")
    val x6501_x6827_addr_vector = Vector("x6501_x6827_addr")
    val x6502_vector = Vector("x6502")
    val x6500_x6824_addr_vector = Vector("x6500_x6824_addr")
    val x6471_mc = MemoryController(TileLoad, x6049_oc)
    val x6424_mc = MemoryController(TileLoad, x6048_oc)
    val x6952_mc = MemoryController(TileStore, x6051_oc)
    val x6958 = Sequential(name = "x6958", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6958_unitcc = CounterChain(name = "x6958_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6956 = MetaPipeline(name = "x6956", parent=x6958, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val ctr2 = (Const("0i").out, Const("7680i").out, Const("48i").out) // Counter
      val x6393 = CounterChain(name = "x6393", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x6932 = MetaPipeline(name = "x6932", parent=x6956, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x6396 = CounterChain(name = "x6396", ctr5)
      var stage: List[Stage] = Nil
    }
    val x6445 = StreamController(name = "x6445", parent=x6932, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6401 = CounterChain(name = "x6401", ctr7)
      var stage: List[Stage] = Nil
    }
    val x6420_0 = UnitPipeline(name = "x6420_0", parent=x6445, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2210 = CU.temp
      val tr2209 = CU.temp
      val tr2207 = CU.temp
      val tr2206 = CU.temp
      val tr2204 = CU.temp
      val tr2199 = CU.temp
      val tr2197 = CU.temp
      val tr2196 = CU.temp
      val tr2194 = CU.temp
      val x6401 = CounterChain.copy(x6445, "x6401")
      val x6396 = CounterChain.copy(x6932, "x6396")
      val x6393 = CounterChain.copy(x6956, "x6393")
      val x6420_unitcc = CounterChain(name = "x6420_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6393(0)), CU.ctr(stage(0), x6401(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2194)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2194), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2196)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2196), CU.ctr(stage(2), x6396(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2197)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2197), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2199)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2197), CU.temp(stage(4), tr2199)), op=FixSub, results=List(CU.scalarOut(stage(5), x6424_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2199), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2204)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2204), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2206)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2204), CU.temp(stage(7), tr2206)), op=FixSub, results=List(CU.temp(stage(8), tr2207)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2206), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2209)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2209), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2210)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2207), CU.temp(stage(10), tr2210)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6424_mc.len)))
    }
    val x6492 = StreamController(name = "x6492", parent=x6932, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6448 = CounterChain(name = "x6448", ctr15)
      var stage: List[Stage] = Nil
    }
    val x6467_0 = UnitPipeline(name = "x6467_0", parent=x6492, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2240 = CU.temp
      val tr2239 = CU.temp
      val tr2237 = CU.temp
      val tr2236 = CU.temp
      val tr2234 = CU.temp
      val tr2229 = CU.temp
      val tr2227 = CU.temp
      val tr2226 = CU.temp
      val tr2224 = CU.temp
      val x6448 = CounterChain.copy(x6492, "x6448")
      val x6467_unitcc = CounterChain(name = "x6467_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6396 = CounterChain.copy(x6932, "x6396")
      val x6393 = CounterChain.copy(x6956, "x6393")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6396(0)), CU.ctr(stage(0), x6448(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2224)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2224), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2226)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2226), CU.ctr(stage(2), x6393(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2227)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2227), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2229)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2227), CU.temp(stage(4), tr2229)), op=FixSub, results=List(CU.scalarOut(stage(5), x6471_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2229), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2234)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2234), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2236)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2234), CU.temp(stage(7), tr2236)), op=FixSub, results=List(CU.temp(stage(8), tr2237)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2236), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2239)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2239), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2240)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2237), CU.temp(stage(10), tr2240)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6471_mc.len)))
    }
    val x6930 = MetaPipeline(name = "x6930", parent=x6932, deps=List(x6445, x6492)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr14 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6499 = CounterChain(name = "x6499", ctr13, ctr14)
      val ctr11 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6496 = CounterChain(name = "x6496", ctr11)
      var stage: List[Stage] = Nil
    }
    val x6579_0 = Pipeline(name = "x6579_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2262 = CU.temp
      val tr2258 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr17 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr18 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6548 = CounterChain(name = "x6548", ctr17, ctr18)
      val x6397_x6566 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      val x6398_x6569 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6548(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2258)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2258), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6566.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2262)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2262), CU.ctr(stage(3), x6548(1))), op=FixAdd, results=List(x6398_x6569.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6566), x6398_x6569.load), op=FixMul, results=List(CU.vecOut(stage(5), x6500_vector)))
    }
    val x6595_0 = Pipeline(name = "x6595_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2281 = CU.temp
      val tr2277 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr19 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr20 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6549 = CounterChain(name = "x6549", ctr19, ctr20)
      val x6397_x6582 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      val x6398_x6585 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6549(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2277)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2277), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6582.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2281)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2281), CU.ctr(stage(3), x6549(1))), op=FixAdd, results=List(x6398_x6585.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6582), x6398_x6585.load), op=FixMul, results=List(CU.vecOut(stage(5), x6501_vector)))
    }
    val x6611_0 = Pipeline(name = "x6611_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2300 = CU.temp
      val tr2296 = CU.temp
      val ctr21 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr22 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6550 = CounterChain(name = "x6550", ctr21, ctr22)
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val x6398_x6601 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6598 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6550(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2296)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2296), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6598.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2300)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2300), CU.ctr(stage(3), x6550(1))), op=FixAdd, results=List(x6398_x6601.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6598), x6398_x6601.load), op=FixMul, results=List(CU.vecOut(stage(5), x6502_vector)))
    }
    val x6627_0 = Pipeline(name = "x6627_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2319 = CU.temp
      val tr2315 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr23 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr24 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6551 = CounterChain(name = "x6551", ctr23, ctr24)
      val x6398_x6617 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6614 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6551(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2315)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2315), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6614.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2319)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2319), CU.ctr(stage(3), x6551(1))), op=FixAdd, results=List(x6398_x6617.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6614), x6398_x6617.load), op=FixMul, results=List(CU.vecOut(stage(5), x6503_vector)))
    }
    val x6643_0 = Pipeline(name = "x6643_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2338 = CU.temp
      val tr2334 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr25 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr26 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6552 = CounterChain(name = "x6552", ctr25, ctr26)
      val x6398_x6633 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6630 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6552(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2334)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2334), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6630.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2338)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2338), CU.ctr(stage(3), x6552(1))), op=FixAdd, results=List(x6398_x6633.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6630), x6398_x6633.load), op=FixMul, results=List(CU.vecOut(stage(5), x6504_vector)))
    }
    val x6659_0 = Pipeline(name = "x6659_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2357 = CU.temp
      val tr2353 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr27 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr28 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6553 = CounterChain(name = "x6553", ctr27, ctr28)
      val x6398_x6649 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6646 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6553(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2353)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2353), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6646.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2357)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2357), CU.ctr(stage(3), x6553(1))), op=FixAdd, results=List(x6398_x6649.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6646), x6398_x6649.load), op=FixMul, results=List(CU.vecOut(stage(5), x6505_vector)))
    }
    val x6675_0 = Pipeline(name = "x6675_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2376 = CU.temp
      val tr2372 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr29 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr30 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6554 = CounterChain(name = "x6554", ctr29, ctr30)
      val x6397_x6662 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      val x6398_x6665 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6554(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2372)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2372), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6662.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2376)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2376), CU.ctr(stage(3), x6554(1))), op=FixAdd, results=List(x6398_x6665.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6662), x6398_x6665.load), op=FixMul, results=List(CU.vecOut(stage(5), x6506_vector)))
    }
    val x6691_0 = Pipeline(name = "x6691_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2395 = CU.temp
      val tr2391 = CU.temp
      val ctr31 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr32 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6555 = CounterChain(name = "x6555", ctr31, ctr32)
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val x6398_x6681 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6678 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6555(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2391)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2391), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6678.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2395)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2395), CU.ctr(stage(3), x6555(1))), op=FixAdd, results=List(x6398_x6681.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6678), x6398_x6681.load), op=FixMul, results=List(CU.vecOut(stage(5), x6507_vector)))
    }
    val x6707_0 = Pipeline(name = "x6707_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2414 = CU.temp
      val tr2410 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr33 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr34 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6556 = CounterChain(name = "x6556", ctr33, ctr34)
      val x6398_x6697 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6694 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6556(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2410)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2410), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6694.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2414)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2414), CU.ctr(stage(3), x6556(1))), op=FixAdd, results=List(x6398_x6697.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6694), x6398_x6697.load), op=FixMul, results=List(CU.vecOut(stage(5), x6508_vector)))
    }
    val x6723_0 = Pipeline(name = "x6723_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2433 = CU.temp
      val tr2429 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr35 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr36 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6557 = CounterChain(name = "x6557", ctr35, ctr36)
      val x6397_x6710 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      val x6398_x6713 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6557(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2429)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2429), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6710.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2433)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2433), CU.ctr(stage(3), x6557(1))), op=FixAdd, results=List(x6398_x6713.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6710), x6398_x6713.load), op=FixMul, results=List(CU.vecOut(stage(5), x6509_vector)))
    }
    val x6739_0 = Pipeline(name = "x6739_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2452 = CU.temp
      val tr2448 = CU.temp
      val ctr37 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr38 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6558 = CounterChain(name = "x6558", ctr37, ctr38)
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val x6398_x6729 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6726 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6558(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2448)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2448), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6726.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2452)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2452), CU.ctr(stage(3), x6558(1))), op=FixAdd, results=List(x6398_x6729.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6726), x6398_x6729.load), op=FixMul, results=List(CU.vecOut(stage(5), x6510_vector)))
    }
    val x6755_0 = Pipeline(name = "x6755_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2471 = CU.temp
      val tr2467 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr39 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr40 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6559 = CounterChain(name = "x6559", ctr39, ctr40)
      val x6398_x6745 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6742 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6559(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2467)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2467), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6742.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2471)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2471), CU.ctr(stage(3), x6559(1))), op=FixAdd, results=List(x6398_x6745.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6742), x6398_x6745.load), op=FixMul, results=List(CU.vecOut(stage(5), x6511_vector)))
    }
    val x6771_0 = Pipeline(name = "x6771_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2490 = CU.temp
      val tr2486 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr41 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr42 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6560 = CounterChain(name = "x6560", ctr41, ctr42)
      val x6397_x6758 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      val x6398_x6761 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6560(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2486)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2486), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6758.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2490)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2490), CU.ctr(stage(3), x6560(1))), op=FixAdd, results=List(x6398_x6761.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6758), x6398_x6761.load), op=FixMul, results=List(CU.vecOut(stage(5), x6512_vector)))
    }
    val x6787_0 = Pipeline(name = "x6787_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2509 = CU.temp
      val tr2505 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr43 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr44 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6561 = CounterChain(name = "x6561", ctr43, ctr44)
      val x6398_x6777 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6774 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6561(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2505)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2505), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6774.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2509)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2509), CU.ctr(stage(3), x6561(1))), op=FixAdd, results=List(x6398_x6777.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6774), x6398_x6777.load), op=FixMul, results=List(CU.vecOut(stage(5), x6513_vector)))
    }
    val x6803_0 = Pipeline(name = "x6803_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2528 = CU.temp
      val tr2524 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr45 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr46 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6562 = CounterChain(name = "x6562", ctr45, ctr46)
      val x6397_x6790 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      val x6398_x6793 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6562(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2524)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2524), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6790.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2528)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2528), CU.ctr(stage(3), x6562(1))), op=FixAdd, results=List(x6398_x6793.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6790), x6398_x6793.load), op=FixMul, results=List(CU.vecOut(stage(5), x6514_vector)))
    }
    val x6819_0 = Pipeline(name = "x6819_0", parent=x6930, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2547 = CU.temp
      val tr2543 = CU.temp
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6496 = CounterChain.copy(x6930, "x6496")
      val ctr47 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr48 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6563 = CounterChain(name = "x6563", ctr47, ctr48)
      val x6398_x6809 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6471_mc.vdata)
      val x6397_x6806 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0))).wtPort(x6424_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6563(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2543)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2543), CU.ctr(stage(1), x6496(0))), op=FixAdd, results=List(x6397_x6806.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6496(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2547)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2547), CU.ctr(stage(3), x6563(1))), op=FixAdd, results=List(x6398_x6809.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6397_x6806), x6398_x6809.load), op=FixMul, results=List(CU.vecOut(stage(5), x6515_vector)))
    }
    val x6928 = StreamController(name = "x6928", parent=x6930, deps=List(x6723_0, x6707_0, x6755_0, x6595_0, x6787_0, x6643_0, x6659_0, x6819_0, x6627_0, x6691_0, x6739_0, x6579_0, x6803_0, x6675_0, x6611_0, x6771_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr51 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr52 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6499 = CounterChain(name = "x6499", ctr51, ctr52)
      var stage: List[Stage] = Nil
    }
    val x6928_0 = StreamPipeline(name = "x6928_0", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2606 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2606)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2606), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6500_x6824_addr_vector)))
    }
    val x6928_1 = StreamPipeline(name = "x6928_1", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2610 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2610)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2610), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6501_x6827_addr_vector)))
    }
    val x6928_2 = StreamPipeline(name = "x6928_2", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2614 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2614)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2614), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6502_x6830_addr_vector)))
    }
    val x6928_3 = StreamPipeline(name = "x6928_3", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2618 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2618)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2618), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6503_x6833_addr_vector)))
    }
    val x6928_4 = StreamPipeline(name = "x6928_4", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2622 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2622)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2622), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6504_x6836_addr_vector)))
    }
    val x6928_5 = StreamPipeline(name = "x6928_5", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2626 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2626)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2626), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6505_x6839_addr_vector)))
    }
    val x6928_6 = StreamPipeline(name = "x6928_6", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2630 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2630)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2630), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6506_x6842_addr_vector)))
    }
    val x6928_7 = StreamPipeline(name = "x6928_7", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2634 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2634)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2634), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6507_x6845_addr_vector)))
    }
    val x6928_8 = StreamPipeline(name = "x6928_8", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2638 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2638)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2638), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6508_x6848_addr_vector)))
    }
    val x6928_9 = StreamPipeline(name = "x6928_9", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2642 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2642)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2642), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6509_x6851_addr_vector)))
    }
    val x6928_10 = StreamPipeline(name = "x6928_10", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2646 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2646)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2646), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6510_x6854_addr_vector)))
    }
    val x6928_11 = StreamPipeline(name = "x6928_11", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2650 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2650)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2650), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6511_x6857_addr_vector)))
    }
    val x6928_12 = StreamPipeline(name = "x6928_12", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2654 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2654)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2654), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6512_x6860_addr_vector)))
    }
    val x6928_13 = StreamPipeline(name = "x6928_13", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2658 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2658)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2658), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6513_x6863_addr_vector)))
    }
    val x6928_14 = StreamPipeline(name = "x6928_14", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2662 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2662)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2662), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6514_x6866_addr_vector)))
    }
    val x6928_15 = StreamPipeline(name = "x6928_15", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2666 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2666)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2666), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6515_x6869_addr_vector)))
    }
    val x6928_16 = StreamPipeline(name = "x6928_16", parent=x6928, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2670 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2670)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2670), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(CU.vecOut(stage(2), x6394_x6872_addr_vector)))
    }
    val x6928_17 = StreamPipeline(name = "x6928_17", parent=x6928, deps=List(x6928_0, x6928_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2604 = CU.temp
      val tr2603 = CU.temp
      val tr2565 = CU.temp
      val tr2564 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6549 = CounterChain.copy(x6595_0, "x6549")
      val x6548 = CounterChain.copy(x6579_0, "x6548")
      val x6500_x6824 = SRAM(size = 768, writeCtr = x6548(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6548(0))).wtPort(x6500_vector)
      val x6501_x6827 = SRAM(size = 768, writeCtr = x6549(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6549(0))).wtPort(x6501_vector)
      val x6500_x6824_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6500_x6824_addr_vector)
      val x6501_x6827_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6501_x6827_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6501_x6827))
      Stage(stage(1), operands=List(x6549(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2564)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2564), CU.ctr(stage(1), x6549(1))), op=FixAdd, results=List(x6501_x6827.writeAddr, CU.temp(stage(2), tr2565)))
      stage = stage0 +: WAStages(2, List(x6500_x6824))
      Stage(stage(1), operands=List(x6548(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2603)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2603), CU.ctr(stage(1), x6548(1))), op=FixAdd, results=List(x6500_x6824.writeAddr, CU.temp(stage(2), tr2604)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6500_x6824_addr_fifo.load), op=Bypass, results=List(x6500_x6824.readAddr))
      Stage(stage(2), operands=List(x6501_x6827_addr_fifo.load), op=Bypass, results=List(x6501_x6827.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x6500_x6824), x6501_x6827.load), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2682_vector)))
    }
    val x6928_18 = StreamPipeline(name = "x6928_18", parent=x6928, deps=List(x6928_2, x6928_3)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2592 = CU.temp
      val tr2591 = CU.temp
      val tr2586 = CU.temp
      val tr2585 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6551 = CounterChain.copy(x6627_0, "x6551")
      val x6550 = CounterChain.copy(x6611_0, "x6550")
      val x6502_x6830 = SRAM(size = 768, writeCtr = x6550(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6550(0))).wtPort(x6502_vector)
      val x6503_x6833 = SRAM(size = 768, writeCtr = x6551(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6551(0))).wtPort(x6503_vector)
      val x6502_x6830_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6502_x6830_addr_vector)
      val x6503_x6833_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6503_x6833_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6503_x6833))
      Stage(stage(1), operands=List(x6551(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2585)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2585), CU.ctr(stage(1), x6551(1))), op=FixAdd, results=List(x6503_x6833.writeAddr, CU.temp(stage(2), tr2586)))
      stage = stage0 +: WAStages(2, List(x6502_x6830))
      Stage(stage(1), operands=List(x6550(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2591)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2591), CU.ctr(stage(1), x6550(1))), op=FixAdd, results=List(x6502_x6830.writeAddr, CU.temp(stage(2), tr2592)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6502_x6830_addr_fifo.load), op=Bypass, results=List(x6502_x6830.readAddr))
      Stage(stage(2), operands=List(x6503_x6833_addr_fifo.load), op=Bypass, results=List(x6503_x6833.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x6502_x6830), x6503_x6833.load), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2687_vector)))
    }
    val x6928_19 = StreamPipeline(name = "x6928_19", parent=x6928, deps=List(x6928_17, x6928_18)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_2682_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2682_vector)
      val bus_2687_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2687_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_2682_fifo.load, bus_2687_fifo.load), op=FixAdd, results=List(CU.vecOut(stage(1), bus_2688_vector)))
    }
    val x6928_20 = StreamPipeline(name = "x6928_20", parent=x6928, deps=List(x6928_5, x6928_4)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2601 = CU.temp
      val tr2600 = CU.temp
      val tr2559 = CU.temp
      val tr2558 = CU.temp
      val x6552 = CounterChain.copy(x6643_0, "x6552")
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6553 = CounterChain.copy(x6659_0, "x6553")
      val x6505_x6839 = SRAM(size = 768, writeCtr = x6553(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6553(0))).wtPort(x6505_vector)
      val x6504_x6836 = SRAM(size = 768, writeCtr = x6552(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6552(0))).wtPort(x6504_vector)
      val x6505_x6839_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6505_x6839_addr_vector)
      val x6504_x6836_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6504_x6836_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6505_x6839))
      Stage(stage(1), operands=List(x6553(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2558)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2558), CU.ctr(stage(1), x6553(1))), op=FixAdd, results=List(x6505_x6839.writeAddr, CU.temp(stage(2), tr2559)))
      stage = stage0 +: WAStages(2, List(x6504_x6836))
      Stage(stage(1), operands=List(x6552(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2600)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2600), CU.ctr(stage(1), x6552(1))), op=FixAdd, results=List(x6504_x6836.writeAddr, CU.temp(stage(2), tr2601)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6505_x6839_addr_fifo.load), op=Bypass, results=List(x6505_x6839.readAddr))
      Stage(stage(2), operands=List(x6504_x6836_addr_fifo.load), op=Bypass, results=List(x6504_x6836.readAddr))
      Stage(stage(3), operands=List(x6504_x6836.load, CU.load(stage(2), x6505_x6839)), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2693_vector)))
    }
    val x6928_21 = StreamPipeline(name = "x6928_21", parent=x6928, deps=List(x6928_7, x6928_6)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2574 = CU.temp
      val tr2573 = CU.temp
      val tr2562 = CU.temp
      val tr2561 = CU.temp
      val x6555 = CounterChain.copy(x6691_0, "x6555")
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6554 = CounterChain.copy(x6675_0, "x6554")
      val x6507_x6845 = SRAM(size = 768, writeCtr = x6555(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6555(0))).wtPort(x6507_vector)
      val x6506_x6842 = SRAM(size = 768, writeCtr = x6554(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6554(0))).wtPort(x6506_vector)
      val x6507_x6845_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6507_x6845_addr_vector)
      val x6506_x6842_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6506_x6842_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6506_x6842))
      Stage(stage(1), operands=List(x6554(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2561)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2561), CU.ctr(stage(1), x6554(1))), op=FixAdd, results=List(x6506_x6842.writeAddr, CU.temp(stage(2), tr2562)))
      stage = stage0 +: WAStages(2, List(x6507_x6845))
      Stage(stage(1), operands=List(x6555(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2573)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2573), CU.ctr(stage(1), x6555(1))), op=FixAdd, results=List(x6507_x6845.writeAddr, CU.temp(stage(2), tr2574)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6507_x6845_addr_fifo.load), op=Bypass, results=List(x6507_x6845.readAddr))
      Stage(stage(2), operands=List(x6506_x6842_addr_fifo.load), op=Bypass, results=List(x6506_x6842.readAddr))
      Stage(stage(3), operands=List(x6506_x6842.load, CU.load(stage(2), x6507_x6845)), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2698_vector)))
    }
    val x6928_22 = StreamPipeline(name = "x6928_22", parent=x6928, deps=List(x6928_20, x6928_21, x6928_19)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2699 = CU.temp
      val bus_2693_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2693_vector)
      val bus_2698_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2698_vector)
      val bus_2688_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2688_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_2693_fifo.load, bus_2698_fifo.load), op=FixAdd, results=List(CU.temp(stage(1), tr2699)))
      Stage(stage(2), operands=List(bus_2688_fifo.load, CU.temp(stage(1), tr2699)), op=FixAdd, results=List(CU.vecOut(stage(2), bus_2700_vector)))
    }
    val x6928_23 = StreamPipeline(name = "x6928_23", parent=x6928, deps=List(x6928_9, x6928_8)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2598 = CU.temp
      val tr2597 = CU.temp
      val tr2595 = CU.temp
      val tr2594 = CU.temp
      val x6556 = CounterChain.copy(x6707_0, "x6556")
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6557 = CounterChain.copy(x6723_0, "x6557")
      val x6509_x6851 = SRAM(size = 768, writeCtr = x6557(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6557(0))).wtPort(x6509_vector)
      val x6508_x6848 = SRAM(size = 768, writeCtr = x6556(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6556(0))).wtPort(x6508_vector)
      val x6509_x6851_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6509_x6851_addr_vector)
      val x6508_x6848_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6508_x6848_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6509_x6851))
      Stage(stage(1), operands=List(x6557(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2594)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2594), CU.ctr(stage(1), x6557(1))), op=FixAdd, results=List(x6509_x6851.writeAddr, CU.temp(stage(2), tr2595)))
      stage = stage0 +: WAStages(2, List(x6508_x6848))
      Stage(stage(1), operands=List(x6556(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2597)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2597), CU.ctr(stage(1), x6556(1))), op=FixAdd, results=List(x6508_x6848.writeAddr, CU.temp(stage(2), tr2598)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6509_x6851_addr_fifo.load), op=Bypass, results=List(x6509_x6851.readAddr))
      Stage(stage(2), operands=List(x6508_x6848_addr_fifo.load), op=Bypass, results=List(x6508_x6848.readAddr))
      Stage(stage(3), operands=List(x6508_x6848.load, CU.load(stage(2), x6509_x6851)), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2705_vector)))
    }
    val x6928_24 = StreamPipeline(name = "x6928_24", parent=x6928, deps=List(x6928_11, x6928_10)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2583 = CU.temp
      val tr2582 = CU.temp
      val tr2577 = CU.temp
      val tr2576 = CU.temp
      val x6559 = CounterChain.copy(x6755_0, "x6559")
      val x6558 = CounterChain.copy(x6739_0, "x6558")
      val x6499 = CounterChain.copy(x6930, "x6499")
      val x6511_x6857 = SRAM(size = 768, writeCtr = x6559(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6559(0))).wtPort(x6511_vector)
      val x6510_x6854 = SRAM(size = 768, writeCtr = x6558(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6558(0))).wtPort(x6510_vector)
      val x6511_x6857_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6511_x6857_addr_vector)
      val x6510_x6854_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6510_x6854_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6511_x6857))
      Stage(stage(1), operands=List(x6559(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2576)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2576), CU.ctr(stage(1), x6559(1))), op=FixAdd, results=List(x6511_x6857.writeAddr, CU.temp(stage(2), tr2577)))
      stage = stage0 +: WAStages(2, List(x6510_x6854))
      Stage(stage(1), operands=List(x6558(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2582)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2582), CU.ctr(stage(1), x6558(1))), op=FixAdd, results=List(x6510_x6854.writeAddr, CU.temp(stage(2), tr2583)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6511_x6857_addr_fifo.load), op=Bypass, results=List(x6511_x6857.readAddr))
      Stage(stage(2), operands=List(x6510_x6854_addr_fifo.load), op=Bypass, results=List(x6510_x6854.readAddr))
      Stage(stage(3), operands=List(x6510_x6854.load, CU.load(stage(2), x6511_x6857)), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2710_vector)))
    }
    val x6928_25 = StreamPipeline(name = "x6928_25", parent=x6928, deps=List(x6928_23, x6928_24)) { implicit CU => 
      val stage0 = CU.emptyStage
      val bus_2705_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2705_vector)
      val bus_2710_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2710_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(bus_2705_fifo.load, bus_2710_fifo.load), op=FixAdd, results=List(CU.vecOut(stage(1), bus_2711_vector)))
    }
    val x6928_26 = StreamPipeline(name = "x6928_26", parent=x6928, deps=List(x6928_12, x6928_13)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2568 = CU.temp
      val tr2567 = CU.temp
      val tr2571 = CU.temp
      val tr2570 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6560 = CounterChain.copy(x6771_0, "x6560")
      val x6561 = CounterChain.copy(x6787_0, "x6561")
      val x6512_x6860 = SRAM(size = 768, writeCtr = x6560(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6560(0))).wtPort(x6512_vector)
      val x6513_x6863 = SRAM(size = 768, writeCtr = x6561(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6561(0))).wtPort(x6513_vector)
      val x6512_x6860_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6512_x6860_addr_vector)
      val x6513_x6863_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6513_x6863_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6513_x6863))
      Stage(stage(1), operands=List(x6561(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2570)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2570), CU.ctr(stage(1), x6561(1))), op=FixAdd, results=List(x6513_x6863.writeAddr, CU.temp(stage(2), tr2571)))
      stage = stage0 +: WAStages(2, List(x6512_x6860))
      Stage(stage(1), operands=List(x6560(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2567)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2567), CU.ctr(stage(1), x6560(1))), op=FixAdd, results=List(x6512_x6860.writeAddr, CU.temp(stage(2), tr2568)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6512_x6860_addr_fifo.load), op=Bypass, results=List(x6512_x6860.readAddr))
      Stage(stage(2), operands=List(x6513_x6863_addr_fifo.load), op=Bypass, results=List(x6513_x6863.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x6512_x6860), x6513_x6863.load), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2716_vector)))
    }
    val x6928_27 = StreamPipeline(name = "x6928_27", parent=x6928, deps=List(x6928_14, x6928_15)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2589 = CU.temp
      val tr2588 = CU.temp
      val tr2580 = CU.temp
      val tr2579 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6562 = CounterChain.copy(x6803_0, "x6562")
      val x6563 = CounterChain.copy(x6819_0, "x6563")
      val x6514_x6866 = SRAM(size = 768, writeCtr = x6562(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6562(0))).wtPort(x6514_vector)
      val x6515_x6869 = SRAM(size = 768, writeCtr = x6563(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6499(0), swapWrite = x6563(0))).wtPort(x6515_vector)
      val x6514_x6866_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6514_x6866_addr_vector)
      val x6515_x6869_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6515_x6869_addr_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6515_x6869))
      Stage(stage(1), operands=List(x6563(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2579)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2579), CU.ctr(stage(1), x6563(1))), op=FixAdd, results=List(x6515_x6869.writeAddr, CU.temp(stage(2), tr2580)))
      stage = stage0 +: WAStages(2, List(x6514_x6866))
      Stage(stage(1), operands=List(x6562(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2588)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2588), CU.ctr(stage(1), x6562(1))), op=FixAdd, results=List(x6514_x6866.writeAddr, CU.temp(stage(2), tr2589)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x6514_x6866_addr_fifo.load), op=Bypass, results=List(x6514_x6866.readAddr))
      Stage(stage(2), operands=List(x6515_x6869_addr_fifo.load), op=Bypass, results=List(x6515_x6869.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x6514_x6866), x6515_x6869.load), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2721_vector)))
    }
    val x6928_28 = StreamPipeline(name = "x6928_28", parent=x6928, deps=List(x6928_26, x6928_27, x6928_25, x6928_22)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2723 = CU.temp
      val tr2722 = CU.temp
      val bus_2716_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2716_vector)
      val bus_2721_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2721_vector)
      val bus_2711_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2711_vector)
      val bus_2700_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2700_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(bus_2716_fifo.load, bus_2721_fifo.load), op=FixAdd, results=List(CU.temp(stage(1), tr2722)))
      Stage(stage(2), operands=List(bus_2711_fifo.load, CU.temp(stage(1), tr2722)), op=FixAdd, results=List(CU.temp(stage(2), tr2723)))
      Stage(stage(3), operands=List(bus_2700_fifo.load, CU.temp(stage(2), tr2723)), op=FixAdd, results=List(CU.vecOut(stage(3), bus_2724_vector)))
    }
    val x6928_29 = StreamPipeline(name = "x6928_29", parent=x6928, deps=List(x6928_16, x6928_28)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2726 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6394_x6872 = SRAM(size = 768, writeCtr = x6499(0), banking = Strided(1), buffering = SingleBuffer())
      val x6394_x6872_addr_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x6394_x6872_addr_vector)
      val bus_2724_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_2724_vector)
      val wr2728 = CU.wtAddr(x6394_x6872)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(x6394_x6872_addr_fifo.load), op=Bypass, results=List(x6394_x6872.readAddr))
      Stage(stage(2), operands=List(bus_2724_fifo.load, x6394_x6872.load), op=FixAdd, results=List(CU.vecOut(stage(2), x6394_vector), CU.store(stage(2), x6394_x6872)))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6499(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2726)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2726), CU.ctr(stage(3), x6499(1))), op=FixAdd, results=List(CU.wtAddr(stage(4), wr2728)))
    }
    val x6954 = StreamController(name = "x6954", parent=x6956, deps=List(x6932)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr53 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6935 = CounterChain(name = "x6935", ctr53)
      var stage: List[Stage] = Nil
    }
    val x6956_leafX = UnitPipeline(name = "x6956_leafX", parent=x6956, deps=List(x6954)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6393 = CounterChain.copy(x6956, "x6393")
      val x6956_unitcc = CounterChain(name = "x6956_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6939_0 = UnitPipeline(name = "x6939_0", parent=x6954, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2733 = CU.temp
      val tr2731 = CU.temp
      val x6393 = CounterChain.copy(x6956, "x6393")
      val x6935 = CounterChain.copy(x6954, "x6935")
      val x6939_unitcc = CounterChain(name = "x6939_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6393(0)), CU.ctr(stage(0), x6935(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2731)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2731), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2733)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2733), CU.ctr(stage(2), x6393(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6952_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6952_mc.len)))
    }
    val x6950_0 = Pipeline(name = "x6950_0", parent=x6954, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2740 = CU.temp
      val tr2738 = CU.temp
      val tr2737 = CU.temp
      val x6499 = CounterChain.copy(x6928, "x6499")
      val x6396 = CounterChain.copy(x6932, "x6396")
      val ctr55 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6941 = CounterChain(name = "x6941", ctr55)
      val x6935 = CounterChain.copy(x6954, "x6935")
      val x6394_x6944 = SRAM(size = 768, writeCtr = x6499(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6935(0), swapWrite = x6396(0))).wtPort(x6394_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x6394_x6944))
      Stage(stage(1), operands=List(x6499(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2737)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2737), CU.ctr(stage(1), x6499(1))), op=FixAdd, results=List(x6394_x6944.writeAddr, CU.temp(stage(2), tr2738)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6935(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2740)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2740), CU.ctr(stage(1), x6941(0))), op=FixAdd, results=List(x6394_x6944.readAddr))
      Stage(stage(3), operands=List(x6394_x6944.load), op=Bypass, results=List(CU.vecOut(stage(3), x6952_mc.vdata)))
    }
    
  }
}
