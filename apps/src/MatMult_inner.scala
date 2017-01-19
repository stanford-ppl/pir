import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_innerDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x5743_oc = OffChip("x5743")
    val x5975_scalar = Scalar("x5975")
    val x6620_scalar = Scalar("x6620")
    val x6488_scalar = Scalar("x6488")
    val x5973_scalar = Scalar("x5973")
    val x5742_oc = OffChip("x5742")
    val x5977_scalar = Scalar("x5977")
    val x6356_scalar = Scalar("x6356")
    val x5978_scalar = Scalar("x5978")
    val x5741_oc = OffChip("x5741")
    val x5974_scalar = Scalar("x5974")
    val x5976_scalar = Scalar("x5976")
    val x6224_scalar = Scalar("x6224")
    val x6752_scalar = Scalar("x6752")
    val x6092_scalar = Scalar("x6092")
    val x6018_mc = MemoryController(TileLoad, x5741_oc)
    val x6914_mc = MemoryController(TileStore, x5743_oc)
    val x6819_mc = MemoryController(TileStore, x5743_oc)
    val x6593_mc = MemoryController(TileLoad, x5742_oc)
    val x6329_mc = MemoryController(TileLoad, x5742_oc)
    val x6461_mc = MemoryController(TileLoad, x5742_oc)
    val x6678_mc = MemoryController(TileLoad, x5741_oc)
    val x6546_mc = MemoryController(TileLoad, x5741_oc)
    val x6282_mc = MemoryController(TileLoad, x5741_oc)
    val x6895_mc = MemoryController(TileStore, x5743_oc)
    val x6150_mc = MemoryController(TileLoad, x5741_oc)
    val x6876_mc = MemoryController(TileStore, x5743_oc)
    val x6725_mc = MemoryController(TileLoad, x5742_oc)
    val x6197_mc = MemoryController(TileLoad, x5742_oc)
    val x6857_mc = MemoryController(TileStore, x5743_oc)
    val x6065_mc = MemoryController(TileLoad, x5742_oc)
    val x6414_mc = MemoryController(TileLoad, x5741_oc)
    val x6838_mc = MemoryController(TileStore, x5743_oc)
    val x6922 = Sequential(name = "x6922", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x6922_unitcc = CounterChain(name = "x6922_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x6920 = MetaPipeline(name = "x6920", parent=x6922, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val ctr2 = (Const("0i").out, Const("7680i").out, Const("48i").out) // Counter
      val x5972 = CounterChain(name = "x5972", ctr1, ctr2)
      var stage: List[Stage] = Nil
    }
    val x6122 = MetaPipeline(name = "x6122", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5985 = CounterChain(name = "x5985", ctr5)
      var stage: List[Stage] = Nil
    }
    val x6039 = StreamController(name = "x6039", parent=x6122, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x5995 = CounterChain(name = "x5995", ctr6)
      var stage: List[Stage] = Nil
    }
    val x6014_0 = UnitPipeline(name = "x6014_0", parent=x6039, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2351 = CU.temp
      val tr2350 = CU.temp
      val tr2348 = CU.temp
      val tr2347 = CU.temp
      val tr2345 = CU.temp
      val tr2340 = CU.temp
      val tr2338 = CU.temp
      val tr2337 = CU.temp
      val tr2335 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x5995 = CounterChain.copy(x6039, "x5995")
      val x5985 = CounterChain.copy(x6122, "x5985")
      val x6014_unitcc = CounterChain(name = "x6014_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x5995(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2335)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2335), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2337)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2337), CU.ctr(stage(2), x5985(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2338)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2338), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2340)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2338), CU.temp(stage(4), tr2340)), op=FixSub, results=List(CU.scalarOut(stage(5), x6018_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2340), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2345)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2345), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2347)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2345), CU.temp(stage(7), tr2347)), op=FixSub, results=List(CU.temp(stage(8), tr2348)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2347), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2350)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2350), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2351)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2348), CU.temp(stage(10), tr2351)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6018_mc.len)))
    }
    val x6086 = StreamController(name = "x6086", parent=x6122, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr14 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6042 = CounterChain(name = "x6042", ctr14)
      var stage: List[Stage] = Nil
    }
    val x6061_0 = UnitPipeline(name = "x6061_0", parent=x6086, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2381 = CU.temp
      val tr2380 = CU.temp
      val tr2378 = CU.temp
      val tr2377 = CU.temp
      val tr2375 = CU.temp
      val tr2370 = CU.temp
      val tr2368 = CU.temp
      val tr2367 = CU.temp
      val tr2365 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x5985 = CounterChain.copy(x6122, "x5985")
      val x6061_unitcc = CounterChain(name = "x6061_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6042 = CounterChain.copy(x6086, "x6042")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5985(0)), CU.ctr(stage(0), x6042(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2365)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2365), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2367)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2367), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2368)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2368), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2370)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2368), CU.temp(stage(4), tr2370)), op=FixSub, results=List(CU.scalarOut(stage(5), x6065_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2370), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2375)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2375), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2377)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2375), CU.temp(stage(7), tr2377)), op=FixSub, results=List(CU.temp(stage(8), tr2378)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2377), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2380)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2380), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2381)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2378), CU.temp(stage(10), tr2381)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6065_mc.len)))
    }
    val x6120 = MetaPipeline(name = "x6120", parent=x6122, deps=List(x6039, x6086)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr11 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6091 = CounterChain(name = "x6091", ctr10, ctr11)
      var stage: List[Stage] = Nil
    }
    val x6108_0 = Pipeline(name = "x6108_0", parent=x6120, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2403 = CU.temp
      val tr2399 = CU.temp
      val x6091 = CounterChain.copy(x6120, "x6091")
      val ctr16 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6094 = CounterChain(name = "x6094", ctr16)
      val x5991_x6097 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6091(0))).wtPort(x6018_mc.vdata)
      val x5992_x6100 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6091(0))).wtPort(x6065_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6091(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2399)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2399), CU.ctr(stage(1), x6094(0))), op=FixAdd, results=List(x5991_x6097.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6094(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2403)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2403), CU.ctr(stage(3), x6091(1))), op=FixAdd, results=List(x5992_x6100.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x5991_x6097), x5992_x6100.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr2410) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr2410), op=Bypass, results=List(CU.scalarOut(stage(6), x6092_scalar)))
    }
    val x6118_0 = UnitPipeline(name = "x6118_0", parent=x6120, deps=List(x6108_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2422 = CU.temp
      val tr2420 = CU.temp
      val tr2419 = CU.temp
      val tr2414 = CU.temp
      val x6118_unitcc = CounterChain(name = "x6118_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5985 = CounterChain.copy(x6122, "x5985")
      val x6091 = CounterChain.copy(x6120, "x6091")
      val x5973_x6111 = SRAM(size = 768, writeCtr = x6118_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr2424 = CU.wtAddr(x5973_x6111)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6091(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2414)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2414), CU.ctr(stage(1), x6091(1))), op=FixAdd, results=List(x5973_x6111.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5985(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr2419)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2419), Const("0i"), CU.load(stage(3), x5973_x6111)), op=Mux, results=List(CU.temp(stage(4), tr2420)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2420), CU.scalarIn(stage(4), x6092_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x5973_scalar), CU.store(stage(5), x5973_x6111)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6091(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr2422)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2422), CU.ctr(stage(6), x6091(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr2424)))
    }
    val x6254 = MetaPipeline(name = "x6254", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr23 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5986 = CounterChain(name = "x5986", ctr23)
      var stage: List[Stage] = Nil
    }
    val x6171 = StreamController(name = "x6171", parent=x6254, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr24 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6127 = CounterChain(name = "x6127", ctr24)
      var stage: List[Stage] = Nil
    }
    val x6146_0 = UnitPipeline(name = "x6146_0", parent=x6171, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2443 = CU.temp
      val tr2442 = CU.temp
      val tr2440 = CU.temp
      val tr2439 = CU.temp
      val tr2437 = CU.temp
      val tr2432 = CU.temp
      val tr2430 = CU.temp
      val tr2429 = CU.temp
      val tr2427 = CU.temp
      val x5986 = CounterChain.copy(x6254, "x5986")
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6146_unitcc = CounterChain(name = "x6146_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6127 = CounterChain.copy(x6171, "x6127")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6127(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2427)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2427), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2429)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2429), CU.ctr(stage(2), x5986(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2430)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2430), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2432)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2430), CU.temp(stage(4), tr2432)), op=FixSub, results=List(CU.scalarOut(stage(5), x6150_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2432), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2437)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2437), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2439)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2437), CU.temp(stage(7), tr2439)), op=FixSub, results=List(CU.temp(stage(8), tr2440)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2439), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2442)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2442), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2443)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2440), CU.temp(stage(10), tr2443)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6150_mc.len)))
    }
    val x6218 = StreamController(name = "x6218", parent=x6254, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr32 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6174 = CounterChain(name = "x6174", ctr32)
      var stage: List[Stage] = Nil
    }
    val x6193_0 = UnitPipeline(name = "x6193_0", parent=x6218, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2473 = CU.temp
      val tr2472 = CU.temp
      val tr2470 = CU.temp
      val tr2469 = CU.temp
      val tr2467 = CU.temp
      val tr2462 = CU.temp
      val tr2460 = CU.temp
      val tr2459 = CU.temp
      val tr2457 = CU.temp
      val x6193_unitcc = CounterChain(name = "x6193_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5986 = CounterChain.copy(x6254, "x5986")
      val x6174 = CounterChain.copy(x6218, "x6174")
      val x5972 = CounterChain.copy(x6920, "x5972")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5986(0)), CU.ctr(stage(0), x6174(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2457)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2457), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2459)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2459), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2460)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2460), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2462)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2460), CU.temp(stage(4), tr2462)), op=FixSub, results=List(CU.scalarOut(stage(5), x6197_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2462), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2467)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2467), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2469)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2467), CU.temp(stage(7), tr2469)), op=FixSub, results=List(CU.temp(stage(8), tr2470)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2469), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2472)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2472), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2473)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2470), CU.temp(stage(10), tr2473)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6197_mc.len)))
    }
    val x6252 = MetaPipeline(name = "x6252", parent=x6254, deps=List(x6171, x6218)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr28 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr29 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6223 = CounterChain(name = "x6223", ctr28, ctr29)
      var stage: List[Stage] = Nil
    }
    val x6240_0 = Pipeline(name = "x6240_0", parent=x6252, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2495 = CU.temp
      val tr2491 = CU.temp
      val ctr34 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6226 = CounterChain(name = "x6226", ctr34)
      val x6223 = CounterChain.copy(x6252, "x6223")
      val x6124_x6232 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6223(0))).wtPort(x6197_mc.vdata)
      val x6123_x6229 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6223(0))).wtPort(x6150_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6223(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2491)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2491), CU.ctr(stage(1), x6226(0))), op=FixAdd, results=List(x6123_x6229.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6226(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2495)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2495), CU.ctr(stage(3), x6223(1))), op=FixAdd, results=List(x6124_x6232.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6123_x6229), x6124_x6232.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr2502) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr2502), op=Bypass, results=List(CU.scalarOut(stage(6), x6224_scalar)))
    }
    val x6250_0 = UnitPipeline(name = "x6250_0", parent=x6252, deps=List(x6240_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2514 = CU.temp
      val tr2512 = CU.temp
      val tr2511 = CU.temp
      val tr2506 = CU.temp
      val x5986 = CounterChain.copy(x6254, "x5986")
      val x6250_unitcc = CounterChain(name = "x6250_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6223 = CounterChain.copy(x6252, "x6223")
      val x5974_x6243 = SRAM(size = 768, writeCtr = x6250_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr2516 = CU.wtAddr(x5974_x6243)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6223(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2506)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2506), CU.ctr(stage(1), x6223(1))), op=FixAdd, results=List(x5974_x6243.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5986(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr2511)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2511), Const("0i"), CU.load(stage(3), x5974_x6243)), op=Mux, results=List(CU.temp(stage(4), tr2512)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2512), CU.scalarIn(stage(4), x6224_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x5974_scalar), CU.store(stage(5), x5974_x6243)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6223(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr2514)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2514), CU.ctr(stage(6), x6223(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr2516)))
    }
    val x6386 = MetaPipeline(name = "x6386", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr41 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5987 = CounterChain(name = "x5987", ctr41)
      var stage: List[Stage] = Nil
    }
    val x6303 = StreamController(name = "x6303", parent=x6386, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr42 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6259 = CounterChain(name = "x6259", ctr42)
      var stage: List[Stage] = Nil
    }
    val x6278_0 = UnitPipeline(name = "x6278_0", parent=x6303, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2535 = CU.temp
      val tr2534 = CU.temp
      val tr2532 = CU.temp
      val tr2531 = CU.temp
      val tr2529 = CU.temp
      val tr2524 = CU.temp
      val tr2522 = CU.temp
      val tr2521 = CU.temp
      val tr2519 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6259 = CounterChain.copy(x6303, "x6259")
      val x5987 = CounterChain.copy(x6386, "x5987")
      val x6278_unitcc = CounterChain(name = "x6278_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6259(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2519)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2519), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2521)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2521), CU.ctr(stage(2), x5987(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2522)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2522), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2524)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2522), CU.temp(stage(4), tr2524)), op=FixSub, results=List(CU.scalarOut(stage(5), x6282_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2524), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2529)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2529), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2531)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2529), CU.temp(stage(7), tr2531)), op=FixSub, results=List(CU.temp(stage(8), tr2532)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2531), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2534)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2534), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2535)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2532), CU.temp(stage(10), tr2535)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6282_mc.len)))
    }
    val x6350 = StreamController(name = "x6350", parent=x6386, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr50 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6306 = CounterChain(name = "x6306", ctr50)
      var stage: List[Stage] = Nil
    }
    val x6325_0 = UnitPipeline(name = "x6325_0", parent=x6350, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2565 = CU.temp
      val tr2564 = CU.temp
      val tr2562 = CU.temp
      val tr2561 = CU.temp
      val tr2559 = CU.temp
      val tr2554 = CU.temp
      val tr2552 = CU.temp
      val tr2551 = CU.temp
      val tr2549 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6306 = CounterChain.copy(x6350, "x6306")
      val x6325_unitcc = CounterChain(name = "x6325_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5987 = CounterChain.copy(x6386, "x5987")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5987(0)), CU.ctr(stage(0), x6306(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2549)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2549), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2551)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2551), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2552)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2552), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2554)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2552), CU.temp(stage(4), tr2554)), op=FixSub, results=List(CU.scalarOut(stage(5), x6329_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2554), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2559)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2559), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2561)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2559), CU.temp(stage(7), tr2561)), op=FixSub, results=List(CU.temp(stage(8), tr2562)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2561), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2564)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2564), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2565)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2562), CU.temp(stage(10), tr2565)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6329_mc.len)))
    }
    val x6384 = MetaPipeline(name = "x6384", parent=x6386, deps=List(x6303, x6350)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr46 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr47 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6355 = CounterChain(name = "x6355", ctr46, ctr47)
      var stage: List[Stage] = Nil
    }
    val x6372_0 = Pipeline(name = "x6372_0", parent=x6384, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2587 = CU.temp
      val tr2583 = CU.temp
      val ctr52 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6358 = CounterChain(name = "x6358", ctr52)
      val x6355 = CounterChain.copy(x6384, "x6355")
      val x6256_x6364 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6355(0))).wtPort(x6329_mc.vdata)
      val x6255_x6361 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6355(0))).wtPort(x6282_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2583)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2583), CU.ctr(stage(1), x6358(0))), op=FixAdd, results=List(x6255_x6361.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6358(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2587)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2587), CU.ctr(stage(3), x6355(1))), op=FixAdd, results=List(x6256_x6364.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6255_x6361), x6256_x6364.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr2594) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr2594), op=Bypass, results=List(CU.scalarOut(stage(6), x6356_scalar)))
    }
    val x6382_0 = UnitPipeline(name = "x6382_0", parent=x6384, deps=List(x6372_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2606 = CU.temp
      val tr2604 = CU.temp
      val tr2603 = CU.temp
      val tr2598 = CU.temp
      val x6382_unitcc = CounterChain(name = "x6382_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5987 = CounterChain.copy(x6386, "x5987")
      val x6355 = CounterChain.copy(x6384, "x6355")
      val x5975_x6375 = SRAM(size = 768, writeCtr = x6382_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr2608 = CU.wtAddr(x5975_x6375)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2598)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2598), CU.ctr(stage(1), x6355(1))), op=FixAdd, results=List(x5975_x6375.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5987(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr2603)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2603), Const("0i"), CU.load(stage(3), x5975_x6375)), op=Mux, results=List(CU.temp(stage(4), tr2604)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2604), CU.scalarIn(stage(4), x6356_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x5975_scalar), CU.store(stage(5), x5975_x6375)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6355(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr2606)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2606), CU.ctr(stage(6), x6355(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr2608)))
    }
    val x6518 = MetaPipeline(name = "x6518", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr59 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5988 = CounterChain(name = "x5988", ctr59)
      var stage: List[Stage] = Nil
    }
    val x6435 = StreamController(name = "x6435", parent=x6518, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr60 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6391 = CounterChain(name = "x6391", ctr60)
      var stage: List[Stage] = Nil
    }
    val x6410_0 = UnitPipeline(name = "x6410_0", parent=x6435, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2627 = CU.temp
      val tr2626 = CU.temp
      val tr2624 = CU.temp
      val tr2623 = CU.temp
      val tr2621 = CU.temp
      val tr2616 = CU.temp
      val tr2614 = CU.temp
      val tr2613 = CU.temp
      val tr2611 = CU.temp
      val x5988 = CounterChain.copy(x6518, "x5988")
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6410_unitcc = CounterChain(name = "x6410_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6391 = CounterChain.copy(x6435, "x6391")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6391(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2611)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2611), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2613)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2613), CU.ctr(stage(2), x5988(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2614)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2614), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2616)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2614), CU.temp(stage(4), tr2616)), op=FixSub, results=List(CU.scalarOut(stage(5), x6414_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2616), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2621)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2621), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2623)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2621), CU.temp(stage(7), tr2623)), op=FixSub, results=List(CU.temp(stage(8), tr2624)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2623), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2626)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2626), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2627)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2624), CU.temp(stage(10), tr2627)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6414_mc.len)))
    }
    val x6482 = StreamController(name = "x6482", parent=x6518, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr68 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6438 = CounterChain(name = "x6438", ctr68)
      var stage: List[Stage] = Nil
    }
    val x6457_0 = UnitPipeline(name = "x6457_0", parent=x6482, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2657 = CU.temp
      val tr2656 = CU.temp
      val tr2654 = CU.temp
      val tr2653 = CU.temp
      val tr2651 = CU.temp
      val tr2646 = CU.temp
      val tr2644 = CU.temp
      val tr2643 = CU.temp
      val tr2641 = CU.temp
      val x6438 = CounterChain.copy(x6482, "x6438")
      val x6457_unitcc = CounterChain(name = "x6457_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5988 = CounterChain.copy(x6518, "x5988")
      val x5972 = CounterChain.copy(x6920, "x5972")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5988(0)), CU.ctr(stage(0), x6438(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2641)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2641), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2643)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2643), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2644)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2644), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2646)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2644), CU.temp(stage(4), tr2646)), op=FixSub, results=List(CU.scalarOut(stage(5), x6461_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2646), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2651)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2651), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2653)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2651), CU.temp(stage(7), tr2653)), op=FixSub, results=List(CU.temp(stage(8), tr2654)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2653), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2656)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2656), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2657)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2654), CU.temp(stage(10), tr2657)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6461_mc.len)))
    }
    val x6516 = MetaPipeline(name = "x6516", parent=x6518, deps=List(x6435, x6482)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr64 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr65 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6487 = CounterChain(name = "x6487", ctr64, ctr65)
      var stage: List[Stage] = Nil
    }
    val x6504_0 = Pipeline(name = "x6504_0", parent=x6516, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2679 = CU.temp
      val tr2675 = CU.temp
      val ctr70 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6490 = CounterChain(name = "x6490", ctr70)
      val x6487 = CounterChain.copy(x6516, "x6487")
      val x6387_x6493 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6487(0))).wtPort(x6414_mc.vdata)
      val x6388_x6496 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6487(0))).wtPort(x6461_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6487(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2675)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2675), CU.ctr(stage(1), x6490(0))), op=FixAdd, results=List(x6387_x6493.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6490(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2679)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2679), CU.ctr(stage(3), x6487(1))), op=FixAdd, results=List(x6388_x6496.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6387_x6493), x6388_x6496.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr2686) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr2686), op=Bypass, results=List(CU.scalarOut(stage(6), x6488_scalar)))
    }
    val x6514_0 = UnitPipeline(name = "x6514_0", parent=x6516, deps=List(x6504_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2698 = CU.temp
      val tr2696 = CU.temp
      val tr2695 = CU.temp
      val tr2690 = CU.temp
      val x5988 = CounterChain.copy(x6518, "x5988")
      val x6487 = CounterChain.copy(x6516, "x6487")
      val x6514_unitcc = CounterChain(name = "x6514_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5976_x6507 = SRAM(size = 768, writeCtr = x6514_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr2700 = CU.wtAddr(x5976_x6507)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6487(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2690)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2690), CU.ctr(stage(1), x6487(1))), op=FixAdd, results=List(x5976_x6507.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5988(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr2695)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2695), Const("0i"), CU.load(stage(3), x5976_x6507)), op=Mux, results=List(CU.temp(stage(4), tr2696)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2696), CU.scalarIn(stage(4), x6488_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x5976_scalar), CU.store(stage(5), x5976_x6507)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6487(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr2698)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2698), CU.ctr(stage(6), x6487(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr2700)))
    }
    val x6650 = MetaPipeline(name = "x6650", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr77 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5989 = CounterChain(name = "x5989", ctr77)
      var stage: List[Stage] = Nil
    }
    val x6567 = StreamController(name = "x6567", parent=x6650, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr78 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6523 = CounterChain(name = "x6523", ctr78)
      var stage: List[Stage] = Nil
    }
    val x6542_0 = UnitPipeline(name = "x6542_0", parent=x6567, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2719 = CU.temp
      val tr2718 = CU.temp
      val tr2716 = CU.temp
      val tr2715 = CU.temp
      val tr2713 = CU.temp
      val tr2708 = CU.temp
      val tr2706 = CU.temp
      val tr2705 = CU.temp
      val tr2703 = CU.temp
      val x6523 = CounterChain.copy(x6567, "x6523")
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6542_unitcc = CounterChain(name = "x6542_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5989 = CounterChain.copy(x6650, "x5989")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6523(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2703)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2703), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2705)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2705), CU.ctr(stage(2), x5989(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2706)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2706), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2708)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2706), CU.temp(stage(4), tr2708)), op=FixSub, results=List(CU.scalarOut(stage(5), x6546_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2708), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2713)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2713), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2715)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2713), CU.temp(stage(7), tr2715)), op=FixSub, results=List(CU.temp(stage(8), tr2716)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2715), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2718)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2718), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2719)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2716), CU.temp(stage(10), tr2719)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6546_mc.len)))
    }
    val x6614 = StreamController(name = "x6614", parent=x6650, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr86 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6570 = CounterChain(name = "x6570", ctr86)
      var stage: List[Stage] = Nil
    }
    val x6589_0 = UnitPipeline(name = "x6589_0", parent=x6614, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2749 = CU.temp
      val tr2748 = CU.temp
      val tr2746 = CU.temp
      val tr2745 = CU.temp
      val tr2743 = CU.temp
      val tr2738 = CU.temp
      val tr2736 = CU.temp
      val tr2735 = CU.temp
      val tr2733 = CU.temp
      val x6589_unitcc = CounterChain(name = "x6589_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6570 = CounterChain.copy(x6614, "x6570")
      val x5989 = CounterChain.copy(x6650, "x5989")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5989(0)), CU.ctr(stage(0), x6570(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2733)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2733), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2735)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2735), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2736)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2736), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2738)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2736), CU.temp(stage(4), tr2738)), op=FixSub, results=List(CU.scalarOut(stage(5), x6593_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2738), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2743)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2743), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2745)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2743), CU.temp(stage(7), tr2745)), op=FixSub, results=List(CU.temp(stage(8), tr2746)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2745), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2748)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2748), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2749)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2746), CU.temp(stage(10), tr2749)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6593_mc.len)))
    }
    val x6648 = MetaPipeline(name = "x6648", parent=x6650, deps=List(x6567, x6614)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr82 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr83 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6619 = CounterChain(name = "x6619", ctr82, ctr83)
      var stage: List[Stage] = Nil
    }
    val x6636_0 = Pipeline(name = "x6636_0", parent=x6648, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2771 = CU.temp
      val tr2767 = CU.temp
      val ctr88 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6622 = CounterChain(name = "x6622", ctr88)
      val x6619 = CounterChain.copy(x6648, "x6619")
      val x6520_x6628 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6619(0))).wtPort(x6593_mc.vdata)
      val x6519_x6625 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6619(0))).wtPort(x6546_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6619(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2767)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2767), CU.ctr(stage(1), x6622(0))), op=FixAdd, results=List(x6519_x6625.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6622(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2771)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2771), CU.ctr(stage(3), x6619(1))), op=FixAdd, results=List(x6520_x6628.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6519_x6625), x6520_x6628.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr2778) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr2778), op=Bypass, results=List(CU.scalarOut(stage(6), x6620_scalar)))
    }
    val x6646_0 = UnitPipeline(name = "x6646_0", parent=x6648, deps=List(x6636_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2790 = CU.temp
      val tr2788 = CU.temp
      val tr2787 = CU.temp
      val tr2782 = CU.temp
      val x6646_unitcc = CounterChain(name = "x6646_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5989 = CounterChain.copy(x6650, "x5989")
      val x6619 = CounterChain.copy(x6648, "x6619")
      val x5977_x6639 = SRAM(size = 768, writeCtr = x6646_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr2792 = CU.wtAddr(x5977_x6639)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6619(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2782)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2782), CU.ctr(stage(1), x6619(1))), op=FixAdd, results=List(x5977_x6639.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5989(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr2787)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2787), Const("0i"), CU.load(stage(3), x5977_x6639)), op=Mux, results=List(CU.temp(stage(4), tr2788)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2788), CU.scalarIn(stage(4), x6620_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x5977_scalar), CU.store(stage(5), x5977_x6639)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6619(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr2790)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2790), CU.ctr(stage(6), x6619(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr2792)))
    }
    val x6782 = MetaPipeline(name = "x6782", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr95 = (Const("0i").out, Const("3840i").out, Const("48i").out) // Counter
      val x5990 = CounterChain(name = "x5990", ctr95)
      var stage: List[Stage] = Nil
    }
    val x6699 = StreamController(name = "x6699", parent=x6782, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr96 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6655 = CounterChain(name = "x6655", ctr96)
      var stage: List[Stage] = Nil
    }
    val x6674_0 = UnitPipeline(name = "x6674_0", parent=x6699, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2811 = CU.temp
      val tr2810 = CU.temp
      val tr2808 = CU.temp
      val tr2807 = CU.temp
      val tr2805 = CU.temp
      val tr2800 = CU.temp
      val tr2798 = CU.temp
      val tr2797 = CU.temp
      val tr2795 = CU.temp
      val x6674_unitcc = CounterChain(name = "x6674_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x5990 = CounterChain.copy(x6782, "x5990")
      val x6655 = CounterChain.copy(x6699, "x6655")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6655(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2795)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2795), Const("3840i")), op=FixMul, results=List(CU.temp(stage(2), tr2797)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2797), CU.ctr(stage(2), x5990(0))), op=FixAdd, results=List(CU.temp(stage(3), tr2798)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2798), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2800)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2798), CU.temp(stage(4), tr2800)), op=FixSub, results=List(CU.scalarOut(stage(5), x6678_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2800), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2805)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2805), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2807)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2805), CU.temp(stage(7), tr2807)), op=FixSub, results=List(CU.temp(stage(8), tr2808)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2807), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2810)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2810), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2811)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2808), CU.temp(stage(10), tr2811)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6678_mc.len)))
    }
    val x6746 = StreamController(name = "x6746", parent=x6782, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr104 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6702 = CounterChain(name = "x6702", ctr104)
      var stage: List[Stage] = Nil
    }
    val x6721_0 = UnitPipeline(name = "x6721_0", parent=x6746, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2841 = CU.temp
      val tr2840 = CU.temp
      val tr2838 = CU.temp
      val tr2837 = CU.temp
      val tr2835 = CU.temp
      val tr2830 = CU.temp
      val tr2828 = CU.temp
      val tr2827 = CU.temp
      val tr2825 = CU.temp
      val x6702 = CounterChain.copy(x6746, "x6702")
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x5990 = CounterChain.copy(x6782, "x5990")
      val x6721_unitcc = CounterChain(name = "x6721_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5990(0)), CU.ctr(stage(0), x6702(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2825)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2825), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2827)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2827), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.temp(stage(3), tr2828)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2828), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr2830)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2828), CU.temp(stage(4), tr2830)), op=FixSub, results=List(CU.scalarOut(stage(5), x6725_mc.ofs)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr2830), Const("48i")), op=FixAdd, results=List(CU.temp(stage(6), tr2835)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2835), Const("64i")), op=FixMod, results=List(CU.temp(stage(7), tr2837)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr2835), CU.temp(stage(7), tr2837)), op=FixSub, results=List(CU.temp(stage(8), tr2838)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr2837), Const("0i")), op=FixNeq, results=List(CU.temp(stage(9), tr2840)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr2840), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr2841)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr2838), CU.temp(stage(10), tr2841)), op=FixAdd, results=List(CU.scalarOut(stage(11), x6725_mc.len)))
    }
    val x6780 = MetaPipeline(name = "x6780", parent=x6782, deps=List(x6699, x6746)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr100 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val ctr101 = (Const("0i").out, Const("48i").out, Const("1i").out) // Counter
      val x6751 = CounterChain(name = "x6751", ctr100, ctr101)
      var stage: List[Stage] = Nil
    }
    val x6768_0 = Pipeline(name = "x6768_0", parent=x6780, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2863 = CU.temp
      val tr2859 = CU.temp
      val x6751 = CounterChain.copy(x6780, "x6751")
      val ctr106 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6754 = CounterChain(name = "x6754", ctr106)
      val x6651_x6757 = SemiFIFO(size = 768, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6751(0))).wtPort(x6678_mc.vdata)
      val x6652_x6760 = SemiFIFO(size = 2304, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6751(0))).wtPort(x6725_mc.vdata)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6751(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2859)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2859), CU.ctr(stage(1), x6754(0))), op=FixAdd, results=List(x6651_x6757.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x6754(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(3), tr2863)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2863), CU.ctr(stage(3), x6751(1))), op=FixAdd, results=List(x6652_x6760.readAddr))
      Stage(stage(5), operands=List(CU.load(stage(4), x6651_x6757), x6652_x6760.load), op=FixMul, results=List(CU.reduce(stage(5))))
      val (rs1, rr2870) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(6), operands=List(rr2870), op=Bypass, results=List(CU.scalarOut(stage(6), x6752_scalar)))
    }
    val x6778_0 = UnitPipeline(name = "x6778_0", parent=x6780, deps=List(x6768_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2882 = CU.temp
      val tr2880 = CU.temp
      val tr2879 = CU.temp
      val tr2874 = CU.temp
      val x6778_unitcc = CounterChain(name = "x6778_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      val x6751 = CounterChain.copy(x6780, "x6751")
      val x5990 = CounterChain.copy(x6782, "x5990")
      val x5978_x6771 = SRAM(size = 768, writeCtr = x6778_unitcc(0), banking = NoBanking(), buffering = SingleBuffer())
      val wr2884 = CU.wtAddr(x5978_x6771)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6751(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2874)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2874), CU.ctr(stage(1), x6751(1))), op=FixAdd, results=List(x5978_x6771.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x5990(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(3), tr2879)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr2879), Const("0i"), CU.load(stage(3), x5978_x6771)), op=Mux, results=List(CU.temp(stage(4), tr2880)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr2880), CU.scalarIn(stage(4), x6752_scalar)), op=FixAdd, results=List(CU.scalarOut(stage(5), x5978_scalar), CU.store(stage(5), x5978_x6771)))
      Stage(stage(6), operands=List(CU.ctr(stage(5), x6751(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(6), tr2882)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr2882), CU.ctr(stage(6), x6751(1))), op=FixAdd, results=List(CU.wtAddr(stage(7), wr2884)))
    }
    val x6821 = StreamController(name = "x6821", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr20 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6797 = CounterChain(name = "x6797", ctr20)
      var stage: List[Stage] = Nil
    }
    val x6806_0 = UnitPipeline(name = "x6806_0", parent=x6821, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2889 = CU.temp
      val tr2887 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6797 = CounterChain.copy(x6821, "x6797")
      val x6806_unitcc = CounterChain(name = "x6806_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6797(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2887)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2887), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2889)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2889), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6819_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6819_mc.len)))
    }
    val x6817_0 = Pipeline(name = "x6817_0", parent=x6821, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2896 = CU.temp
      val tr2894 = CU.temp
      val tr2893 = CU.temp
      val ctr21 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6808 = CounterChain(name = "x6808", ctr21)
      val x6118_unitcc = CounterChain.copy(x6118_0, "x6118_unitcc")
      val x6797 = CounterChain.copy(x6821, "x6797")
      val x5985 = CounterChain.copy(x6122, "x5985")
      val x6091 = CounterChain.copy(x6120, "x6091")
      val x5973_x6811 = SRAM(size = 768, writeCtr = x6118_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6797(0), swapWrite = x5985(0))).wtPort(x5973_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5973_x6811))
      Stage(stage(1), operands=List(x6091(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2893)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2893), CU.ctr(stage(1), x6091(1))), op=FixAdd, results=List(x5973_x6811.writeAddr, CU.temp(stage(2), tr2894)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6797(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2896)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2896), CU.ctr(stage(1), x6808(0))), op=FixAdd, results=List(x5973_x6811.readAddr))
      Stage(stage(3), operands=List(x5973_x6811.load), op=Bypass, results=List(CU.vecOut(stage(3), x6819_mc.vdata)))
    }
    val x6840 = StreamController(name = "x6840", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr38 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6798 = CounterChain(name = "x6798", ctr38)
      var stage: List[Stage] = Nil
    }
    val x6825_0 = UnitPipeline(name = "x6825_0", parent=x6840, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2907 = CU.temp
      val tr2905 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6798 = CounterChain.copy(x6840, "x6798")
      val x6825_unitcc = CounterChain(name = "x6825_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6798(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2905)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2905), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2907)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2907), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6838_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6838_mc.len)))
    }
    val x6836_0 = Pipeline(name = "x6836_0", parent=x6840, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2914 = CU.temp
      val tr2912 = CU.temp
      val tr2911 = CU.temp
      val x6250_unitcc = CounterChain.copy(x6250_0, "x6250_unitcc")
      val x5986 = CounterChain.copy(x6254, "x5986")
      val ctr39 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6827 = CounterChain(name = "x6827", ctr39)
      val x6798 = CounterChain.copy(x6840, "x6798")
      val x6223 = CounterChain.copy(x6252, "x6223")
      val x5974_x6830 = SRAM(size = 768, writeCtr = x6250_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6798(0), swapWrite = x5986(0))).wtPort(x5974_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5974_x6830))
      Stage(stage(1), operands=List(x6223(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2911)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2911), CU.ctr(stage(1), x6223(1))), op=FixAdd, results=List(x5974_x6830.writeAddr, CU.temp(stage(2), tr2912)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6798(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2914)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2914), CU.ctr(stage(1), x6827(0))), op=FixAdd, results=List(x5974_x6830.readAddr))
      Stage(stage(3), operands=List(x5974_x6830.load), op=Bypass, results=List(CU.vecOut(stage(3), x6838_mc.vdata)))
    }
    val x6859 = StreamController(name = "x6859", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr56 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6799 = CounterChain(name = "x6799", ctr56)
      var stage: List[Stage] = Nil
    }
    val x6844_0 = UnitPipeline(name = "x6844_0", parent=x6859, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2925 = CU.temp
      val tr2923 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6799 = CounterChain.copy(x6859, "x6799")
      val x6844_unitcc = CounterChain(name = "x6844_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6799(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2923)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2923), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2925)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2925), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6857_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6857_mc.len)))
    }
    val x6855_0 = Pipeline(name = "x6855_0", parent=x6859, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2932 = CU.temp
      val tr2930 = CU.temp
      val tr2929 = CU.temp
      val x6382_unitcc = CounterChain.copy(x6382_0, "x6382_unitcc")
      val ctr57 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6846 = CounterChain(name = "x6846", ctr57)
      val x5987 = CounterChain.copy(x6386, "x5987")
      val x6799 = CounterChain.copy(x6859, "x6799")
      val x6355 = CounterChain.copy(x6384, "x6355")
      val x5975_x6849 = SRAM(size = 768, writeCtr = x6382_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6799(0), swapWrite = x5987(0))).wtPort(x5975_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5975_x6849))
      Stage(stage(1), operands=List(x6355(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2929)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2929), CU.ctr(stage(1), x6355(1))), op=FixAdd, results=List(x5975_x6849.writeAddr, CU.temp(stage(2), tr2930)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6799(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2932)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2932), CU.ctr(stage(1), x6846(0))), op=FixAdd, results=List(x5975_x6849.readAddr))
      Stage(stage(3), operands=List(x5975_x6849.load), op=Bypass, results=List(CU.vecOut(stage(3), x6857_mc.vdata)))
    }
    val x6878 = StreamController(name = "x6878", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr74 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6800 = CounterChain(name = "x6800", ctr74)
      var stage: List[Stage] = Nil
    }
    val x6863_0 = UnitPipeline(name = "x6863_0", parent=x6878, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2943 = CU.temp
      val tr2941 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6800 = CounterChain.copy(x6878, "x6800")
      val x6863_unitcc = CounterChain(name = "x6863_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6800(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2941)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2941), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2943)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2943), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6876_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6876_mc.len)))
    }
    val x6874_0 = Pipeline(name = "x6874_0", parent=x6878, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2950 = CU.temp
      val tr2948 = CU.temp
      val tr2947 = CU.temp
      val x5988 = CounterChain.copy(x6518, "x5988")
      val ctr75 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6865 = CounterChain(name = "x6865", ctr75)
      val x6487 = CounterChain.copy(x6516, "x6487")
      val x6800 = CounterChain.copy(x6878, "x6800")
      val x6514_unitcc = CounterChain.copy(x6514_0, "x6514_unitcc")
      val x5976_x6868 = SRAM(size = 768, writeCtr = x6514_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6800(0), swapWrite = x5988(0))).wtPort(x5976_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5976_x6868))
      Stage(stage(1), operands=List(x6487(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2947)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2947), CU.ctr(stage(1), x6487(1))), op=FixAdd, results=List(x5976_x6868.writeAddr, CU.temp(stage(2), tr2948)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6800(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2950)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2950), CU.ctr(stage(1), x6865(0))), op=FixAdd, results=List(x5976_x6868.readAddr))
      Stage(stage(3), operands=List(x5976_x6868.load), op=Bypass, results=List(CU.vecOut(stage(3), x6876_mc.vdata)))
    }
    val x6897 = StreamController(name = "x6897", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr92 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6801 = CounterChain(name = "x6801", ctr92)
      var stage: List[Stage] = Nil
    }
    val x6882_0 = UnitPipeline(name = "x6882_0", parent=x6897, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2961 = CU.temp
      val tr2959 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6801 = CounterChain.copy(x6897, "x6801")
      val x6882_unitcc = CounterChain(name = "x6882_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6801(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2959)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2959), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2961)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2961), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6895_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6895_mc.len)))
    }
    val x6893_0 = Pipeline(name = "x6893_0", parent=x6897, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2968 = CU.temp
      val tr2966 = CU.temp
      val tr2965 = CU.temp
      val x6646_unitcc = CounterChain.copy(x6646_0, "x6646_unitcc")
      val ctr93 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6884 = CounterChain(name = "x6884", ctr93)
      val x5989 = CounterChain.copy(x6650, "x5989")
      val x6801 = CounterChain.copy(x6897, "x6801")
      val x6619 = CounterChain.copy(x6648, "x6619")
      val x5977_x6887 = SRAM(size = 768, writeCtr = x6646_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6801(0), swapWrite = x5989(0))).wtPort(x5977_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5977_x6887))
      Stage(stage(1), operands=List(x6619(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2965)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2965), CU.ctr(stage(1), x6619(1))), op=FixAdd, results=List(x5977_x6887.writeAddr, CU.temp(stage(2), tr2966)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6801(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2968)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2968), CU.ctr(stage(1), x6884(0))), op=FixAdd, results=List(x5977_x6887.readAddr))
      Stage(stage(3), operands=List(x5977_x6887.load), op=Bypass, results=List(CU.vecOut(stage(3), x6895_mc.vdata)))
    }
    val x6916 = StreamController(name = "x6916", parent=x6920, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr110 = (Const("0i").out, Const("16i").out, Const("1i").out) // Counter
      val x6802 = CounterChain(name = "x6802", ctr110)
      var stage: List[Stage] = Nil
    }
    val x6901_0 = UnitPipeline(name = "x6901_0", parent=x6916, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2979 = CU.temp
      val tr2977 = CU.temp
      val x5972 = CounterChain.copy(x6920, "x5972")
      val x6802 = CounterChain.copy(x6916, "x6802")
      val x6901_unitcc = CounterChain(name = "x6901_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x5972(0)), CU.ctr(stage(0), x6802(0))), op=FixAdd, results=List(CU.temp(stage(1), tr2977)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2977), Const("7680i")), op=FixMul, results=List(CU.temp(stage(2), tr2979)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr2979), CU.ctr(stage(2), x5972(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x6914_mc.ofs)))
      Stage(stage(4), operands=List(Const("48i")), op=Bypass, results=List(CU.scalarOut(stage(4), x6914_mc.len)))
    }
    val x6912_0 = Pipeline(name = "x6912_0", parent=x6916, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr2986 = CU.temp
      val tr2984 = CU.temp
      val tr2983 = CU.temp
      val ctr111 = (Const("0i").out, Const("48i").out, Const("16i").out) // Counter
      val x6903 = CounterChain(name = "x6903", ctr111)
      val x6802 = CounterChain.copy(x6916, "x6802")
      val x6778_unitcc = CounterChain.copy(x6778_0, "x6778_unitcc")
      val x6751 = CounterChain.copy(x6780, "x6751")
      val x5990 = CounterChain.copy(x6782, "x5990")
      val x5978_x6906 = SRAM(size = 768, writeCtr = x6778_unitcc(0), banking = Strided(1), buffering = MultiBuffer(2, swapRead = x6802(0), swapWrite = x5990(0))).wtPort(x5978_scalar)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x5978_x6906))
      Stage(stage(1), operands=List(x6751(0), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2983)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2983), CU.ctr(stage(1), x6751(1))), op=FixAdd, results=List(x5978_x6906.writeAddr, CU.temp(stage(2), tr2984)))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x6802(0)), Const("48i")), op=FixMul, results=List(CU.temp(stage(1), tr2986)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr2986), CU.ctr(stage(1), x6903(0))), op=FixAdd, results=List(x5978_x6906.readAddr))
      Stage(stage(3), operands=List(x5978_x6906.load), op=Bypass, results=List(CU.vecOut(stage(3), x6914_mc.vdata)))
    }
    
  }
}
