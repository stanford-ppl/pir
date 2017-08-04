import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object PageRank_plasticine extends PIRApp {
  def main(top:Top) = {
    val bus_601_s = Scalar("bus_601")
    val x4603_x4609_s = Scalar("x4603_x4609")
    val x4706_b4830_x4717_b4832_s = Scalar("x4706_b4830_x4717_b4832")
    val x4432_b4804_x4459_b4812_s = Scalar("x4432_b4804_x4459_b4812")
    val bus_691_s = Scalar("bus_691")
    val bus_605_s = Scalar("bus_605")
    val x4368_x4379_data_s = Scalar("x4368_x4379_data")
    val x4624_x4642_s = Scalar("x4624_x4642")
    val iters_argin = ArgIn("iters")
    val OCedges_da = DRAMAddress("OCedges", "OCedges")
    val x4426_2_s = Scalar("x4426_2")
    val x4367_b4795_x4377_b4797_s = Scalar("x4367_b4795_x4377_b4797")
    val x4566_x4588_s = Scalar("x4566_x4588")
    val x4500_b4816_x4527_b4824_s = Scalar("x4500_b4816_x4527_b4824")
    val x4611_x4622_s = Scalar("x4611_x4622")
    val bus_608_s = Scalar("bus_608")
    val x4328_0_s = Scalar("x4328_0")
    val x4500_b4817_x4527_b4825_s = Scalar("x4500_b4817_x4527_b4825")
    val bus_568_s = Scalar("bus_568")
    val x4389_b4800_x4399_b4802_s = Scalar("x4389_b4800_x4399_b4802")
    val x4345_b4792_x4355_b4794_s = Scalar("x4345_b4792_x4355_b4794")
    val x4602_0_s = Scalar("x4602_0")
    val x4328_1_s = Scalar("x4328_1")
    val x4433_b4807_x4461_b4815_s = Scalar("x4433_b4807_x4461_b4815")
    val damp_argin = ArgIn("damp")
    val bus_662_s = Scalar("bus_662")
    val x4567_x4583_s = Scalar("x4567_x4583")
    val bus_599_s = Scalar("bus_599")
    val x4432_b4803_x4459_b4811_s = Scalar("x4432_b4803_x4459_b4811")
    val x4433_b4805_x4461_b4813_s = Scalar("x4433_b4805_x4461_b4813")
    val x4501_b4820_x4529_b4828_s = Scalar("x4501_b4820_x4529_b4828")
    val x4625_x4644_data_s = Scalar("x4625_x4644_data")
    val NP_argin = ArgIn("NP")
    val x4367_b4796_x4377_b4798_s = Scalar("x4367_b4796_x4377_b4798")
    val x4345_b4791_x4355_b4793_s = Scalar("x4345_b4791_x4355_b4793")
    val x4327_0_s = Scalar("x4327_0")
    val bus_574_s = Scalar("bus_574")
    val x4341_0_s = Scalar("x4341_0")
    val x4390_x4401_data_s = Scalar("x4390_x4401_data")
    val x4426_0_s = Scalar("x4426_0")
    val x4566_0_s = Scalar("x4566_0")
    val OCcounts_oc = OffChip("OCcounts")
    val OCedgeId_da = DRAMAddress("OCedgeId", "OCedgeId")
    val x4565_0_s = Scalar("x4565_0")
    val x4501_b4818_x4529_b4826_s = Scalar("x4501_b4818_x4529_b4826")
    val OCedgeLen_da = DRAMAddress("OCedgeLen", "OCedgeLen")
    val OCpages_oc = OffChip("OCpages")
    val x4659_x4688_s = Scalar("x4659_x4688")
    val x4567_x4701_s = Scalar("x4567_x4701")
    val OCpages_da = DRAMAddress("OCpages", "OCpages")
    val x4433_b4806_x4461_b4814_s = Scalar("x4433_b4806_x4461_b4814")
    val bus_572_s = Scalar("bus_572")
    val OCedgeLen_oc = OffChip("OCedgeLen")
    val x4502_x4531_data_s = Scalar("x4502_x4531_data")
    val bus_641_s = Scalar("bus_641")
    val x4342_0_s = Scalar("x4342_0")
    val OCcounts_da = DRAMAddress("OCcounts", "OCcounts")
    val bus_603_s = Scalar("bus_603")
    val x4327_x4331_s = Scalar("x4327_x4331")
    val x4565_x4586_s = Scalar("x4565_x4586")
    val x4340_0_s = Scalar("x4340_0")
    val x4427_0_s = Scalar("x4427_0")
    val x4328_x4332_s = Scalar("x4328_x4332")
    val bus_570_s = Scalar("bus_570")
    val x4389_b4799_x4399_b4801_s = Scalar("x4389_b4799_x4399_b4801")
    val x4706_b4829_x4717_b4831_s = Scalar("x4706_b4829_x4717_b4831")
    val x4501_b4819_x4529_b4827_s = Scalar("x4501_b4819_x4529_b4827")
    val x4426_1_s = Scalar("x4426_1")
    val x4565_1_s = Scalar("x4565_1")
    val OCedgeId_oc = OffChip("OCedgeId")
    val x4346_x4357_data_s = Scalar("x4346_x4357_data")
    val x4434_x4463_data_s = Scalar("x4434_x4463_data")
    val bus_577_s = Scalar("bus_577")
    val bus_668_s = Scalar("bus_668")
    val OCedges_oc = OffChip("OCedges")
    val x4328_x4697_s = Scalar("x4328_x4697")
    val x4735 = Sequential(name="x4735",parent=top) { implicit CU => 
      val x4735_unit = CounterChain(name = "x4735_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4327_dsp0 = MemoryPipeline(name="x4327_dsp0",parent="x4735") { implicit CU => 
      val x4596 = ScalarFIFO(size=1,name="x4596").wtPort(x4565_1_s)
      val x4331 = ScalarFIFO(size=1,name="x4331").wtPort(x4327_x4331_s)
      val x4600 = ScalarFIFO(size=1,name="x4600").wtPort(x4426_1_s)
      val x4628 = CounterChain.copy("x4643_0", "x4628")
      val x4327 = SRAM(size=16,name="x4327",banking = Strided(1)).wtPort(x4331.readPort).wtPort(x4600.readPort).rdPort(x4327_0_s).rdAddr(x4628(0))
      WAStage(operands=List(CU.load(x4596)), op=Bypass, results=List(x4327.writeAddr))
    }
    val x4328_dsp0 = MemoryPipeline(name="x4328_dsp0",parent="x4735") { implicit CU => 
      val x4697 = ScalarFIFO(size=1,name="x4697").wtPort(x4328_x4697_s)
      val x4332 = ScalarFIFO(size=1,name="x4332").wtPort(x4328_x4332_s)
      val x4720 = CounterChain.copy("x4727", "x4720")
      val x4413 = CounterChain.copy("x4703", "x4413")
      val x4328 = SRAM(size=16,name="x4328",banking = Strided(1)).wtPort(x4332.readPort).wtPort(x4697.readPort).rdPort(x4328_0_s).rdAddr(x4720(0)).wtAddr(x4413(0))
    }
    val x4328_dsp1 = MemoryPipeline(name="x4328_dsp1",parent="x4735") { implicit CU => 
      val x4697 = ScalarFIFO(size=1,name="x4697").wtPort(x4328_x4697_s)
      val x4332 = ScalarFIFO(size=1,name="x4332").wtPort(x4328_x4332_s)
      val x4413 = CounterChain.copy("x4703", "x4413")
      val x4328 = SRAM(size=16,name="x4328",banking = Strided(1)).wtPort(x4332.readPort).wtPort(x4697.readPort).rdPort(x4328_1_s).rdAddr(x4413(0)).wtAddr(x4413(0))
    }
    val x4333_0 = Pipeline(name="x4333_0",parent=x4735) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4330 = CounterChain(name = "x4330", ctr1).iter(16)
      Stage(operands=List(Const(0)), op=Bypass, results=List(CU.scalarOut(x4327_x4331_s)))
      Stage(operands=List(Const(1)), op=Bypass, results=List(CU.scalarOut(x4328_x4332_s)))
    }
    val x4734 = Sequential(name="x4734",parent=x4735) { implicit CU => 
      val x4302 = ScalarBuffer(name="x4302").wtPort(iters_argin)
      val ctr2 = Counter(min=Const(0), max=x4302.readPort, step=Const(1), par=1) // Counter
      val x4336 = CounterChain(name = "x4336", ctr2).iter(1)
    }
    val x4733 = Sequential(name="x4733",parent=x4734) { implicit CU => 
      val x4303 = ScalarBuffer(name="x4303").wtPort(NP_argin)
      val ctr3 = Counter(min=Const(0), max=x4303.readPort, step=Const(16), par=1) // Counter
      val x4339 = CounterChain(name = "x4339", ctr3).iter(1)
    }
    val x4340_dsp0 = MemoryPipeline(name="x4340_dsp0",parent="x4733") { implicit CU => 
      val x4364 = ScalarFIFO(size=1,name="x4364").wtPort(x4346_x4357_data_s)
      val x4359 = CounterChain.copy("x4365", "x4359")
      val x4413 = CounterChain.copy("x4703", "x4413")
      val x4340 = SRAM(size=16,name="x4340",banking = Strided(1)).wtPort(x4364.readPort).rdPort(x4340_0_s).rdAddr(x4413(0)).wtAddr(x4359(0))
    }
    val x4341_dsp0 = MemoryPipeline(name="x4341_dsp0",parent="x4733") { implicit CU => 
      val x4386 = ScalarFIFO(size=1,name="x4386").wtPort(x4368_x4379_data_s)
      val x4381 = CounterChain.copy("x4387", "x4381")
      val x4413 = CounterChain.copy("x4703", "x4413")
      val x4341 = SRAM(size=16,name="x4341",banking = Strided(1)).wtPort(x4386.readPort).rdPort(x4341_0_s).rdAddr(x4413(0)).wtAddr(x4381(0))
    }
    val x4342_dsp0 = MemoryPipeline(name="x4342_dsp0",parent="x4733") { implicit CU => 
      val x4408 = ScalarFIFO(size=1,name="x4408").wtPort(x4390_x4401_data_s)
      val x4403 = CounterChain.copy("x4409", "x4403")
      val x4413 = CounterChain.copy("x4703", "x4413")
      val x4342 = SRAM(size=16,name="x4342",banking = Strided(1)).wtPort(x4408.readPort).rdPort(x4342_0_s).rdAddr(x4413(0)).wtAddr(x4403(0))
    }
    val x4366 = StreamController(name="x4366",parent=x4733) { implicit CU => 
      val x4366_unit = CounterChain(name = "x4366_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4356_0 = Pipeline(name="x4356_0",parent=x4366) { implicit CU => 
      val x4348 = CU.temp(None)
      val x4350 = ScalarBuffer(name="x4350").wtPort(OCpages_da)
      val x4339 = CounterChain.copy("x4733", "x4339")
      val x4356_unit = CounterChain(name = "x4356_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4339(0)), Const(2)), op=FixSla, results=List(x4348))
      Stage(operands=List(x4348, CU.load(x4350)), op=FixAdd, results=List(CU.scalarOut(x4345_b4791_x4355_b4793_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4345_b4792_x4355_b4794_s)))
    }
    val x4357 = MemoryController(name="x4357",parent=x4366,offchip=OCpages_oc, mctpe=TileLoad) { implicit CU => 
      val x4345_b4792 = ScalarFIFO(size=1,name="size").wtPort(x4345_b4792_x4355_b4794_s)
      val x4345_b4791 = ScalarFIFO(size=1,name="offset").wtPort(x4345_b4791_x4355_b4793_s)
      CU.newSout("data", x4346_x4357_data_s)
    }
    val x4365 = Pipeline(name="x4365",parent=x4366) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4359 = CounterChain(name = "x4359", ctr4).iter(16)
    }
    val x4388 = StreamController(name="x4388",parent=x4733) { implicit CU => 
      val x4388_unit = CounterChain(name = "x4388_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4378_0 = Pipeline(name="x4378_0",parent=x4388) { implicit CU => 
      val x4370 = CU.temp(None)
      val x4372 = ScalarBuffer(name="x4372").wtPort(OCedgeId_da)
      val x4339 = CounterChain.copy("x4733", "x4339")
      val x4378_unit = CounterChain(name = "x4378_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4339(0)), Const(2)), op=FixSla, results=List(x4370))
      Stage(operands=List(x4370, CU.load(x4372)), op=FixAdd, results=List(CU.scalarOut(x4367_b4795_x4377_b4797_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4367_b4796_x4377_b4798_s)))
    }
    val x4379 = MemoryController(name="x4379",parent=x4388,offchip=OCedgeId_oc, mctpe=TileLoad) { implicit CU => 
      val x4367_b4796 = ScalarFIFO(size=1,name="size").wtPort(x4367_b4796_x4377_b4798_s)
      val x4367_b4795 = ScalarFIFO(size=1,name="offset").wtPort(x4367_b4795_x4377_b4797_s)
      CU.newSout("data", x4368_x4379_data_s)
    }
    val x4387 = Pipeline(name="x4387",parent=x4388) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4381 = CounterChain(name = "x4381", ctr5).iter(16)
    }
    val x4410 = StreamController(name="x4410",parent=x4733) { implicit CU => 
      val x4410_unit = CounterChain(name = "x4410_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4400_0 = Pipeline(name="x4400_0",parent=x4410) { implicit CU => 
      val x4392 = CU.temp(None)
      val x4394 = ScalarBuffer(name="x4394").wtPort(OCedgeLen_da)
      val x4339 = CounterChain.copy("x4733", "x4339")
      val x4400_unit = CounterChain(name = "x4400_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4339(0)), Const(2)), op=FixSla, results=List(x4392))
      Stage(operands=List(x4392, CU.load(x4394)), op=FixAdd, results=List(CU.scalarOut(x4389_b4799_x4399_b4801_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4389_b4800_x4399_b4802_s)))
    }
    val x4401 = MemoryController(name="x4401",parent=x4410,offchip=OCedgeLen_oc, mctpe=TileLoad) { implicit CU => 
      val x4389_b4799 = ScalarFIFO(size=1,name="offset").wtPort(x4389_b4799_x4399_b4801_s)
      val x4389_b4800 = ScalarFIFO(size=1,name="size").wtPort(x4389_b4800_x4399_b4802_s)
      CU.newSout("data", x4390_x4401_data_s)
    }
    val x4409 = Pipeline(name="x4409",parent=x4410) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4403 = CounterChain(name = "x4403", ctr6).iter(16)
    }
    val x4703 = Sequential(name="x4703",parent=x4733) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4413 = CounterChain(name = "x4413", ctr7).iter(16)
    }
    val x4426_dsp0 = MemoryPipeline(name="x4426_dsp0",parent="x4703") { implicit CU => 
      val x4464 = ScalarBuffer(name="x4464").wtPort(x4433_b4806_x4461_b4814_s)
      val x4492 = ScalarFIFO(size=1,name="x4492").wtPort(x4434_x4463_data_s)
      val x4662 = CounterChain.copy("x4689", "x4662")
      val x4479 = CounterChain.copy("x4493_0", "x4479")
      val x4426 = SRAM(size=16,name="x4426",banking = Strided(1)).wtPort(x4492.readPort).rdPort(x4426_0_s).rdAddr(x4662(0))
      WAStage(operands=List(CU.ctr(x4479(0)), CU.load(x4464)), op=FixSub, results=List(x4426.writeAddr))
    }
    val x4426_dsp1 = MemoryPipeline(name="x4426_dsp1",parent="x4703") { implicit CU => 
      val x4464 = ScalarBuffer(name="x4464").wtPort(x4433_b4806_x4461_b4814_s)
      val x4492 = ScalarFIFO(size=1,name="x4492").wtPort(x4434_x4463_data_s)
      val x4592 = CounterChain.copy("x4601", "x4592")
      val x4479 = CounterChain.copy("x4493_0", "x4479")
      val x4426 = SRAM(size=16,name="x4426",banking = Strided(1)).wtPort(x4492.readPort).rdPort(x4426_1_s).rdAddr(x4592(0))
      WAStage(operands=List(CU.ctr(x4479(0)), CU.load(x4464)), op=FixSub, results=List(x4426.writeAddr))
    }
    val x4426_dsp2 = MemoryPipeline(name="x4426_dsp2",parent="x4703") { implicit CU => 
      val x4464 = ScalarBuffer(name="x4464").wtPort(x4433_b4806_x4461_b4814_s)
      val x4492 = ScalarFIFO(size=1,name="x4492").wtPort(x4434_x4463_data_s)
      val x4570 = CounterChain.copy("x4589", "x4570")
      val x4479 = CounterChain.copy("x4493_0", "x4479")
      val x4426 = SRAM(size=16,name="x4426",banking = Strided(1)).wtPort(x4492.readPort).rdPort(x4426_2_s).rdAddr(x4570(0))
      WAStage(operands=List(CU.ctr(x4479(0)), CU.load(x4464)), op=FixSub, results=List(x4426.writeAddr))
    }
    val x4427_dsp0 = MemoryPipeline(name="x4427_dsp0",parent="x4703") { implicit CU => 
      val x4560 = ScalarFIFO(size=1,name="x4560").wtPort(x4502_x4531_data_s)
      val x4532 = ScalarBuffer(name="x4532").wtPort(x4501_b4819_x4529_b4827_s)
      val x4662 = CounterChain.copy("x4689", "x4662")
      val x4547 = CounterChain.copy("x4561_0", "x4547")
      val x4427 = SRAM(size=16,name="x4427",banking = Strided(1)).wtPort(x4560.readPort).rdPort(x4427_0_s).rdAddr(x4662(0))
      WAStage(operands=List(CU.ctr(x4547(0)), CU.load(x4532)), op=FixSub, results=List(x4427.writeAddr))
    }
    val x4495 = StreamController(name="x4495",parent=x4703) { implicit CU => 
      val x4495_unit = CounterChain(name = "x4495_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4462 = StreamController(name="x4462",parent=x4495) { implicit CU => 
      val x4462_unit = CounterChain(name = "x4462_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4462_0 = Pipeline(name="x4462_0",parent=x4462) { implicit CU => 
      val x4437 = CU.temp(None)
      val x4460_x4445 = CU.temp(None)
      val x4438 = CU.temp(None)
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val x4414 = ScalarBuffer(name="x4414").wtPort(x4341_0_s)
      Stage(operands=List(CU.load(x4414), Const(2)), op=FixSla, results=List(x4437, CU.scalarOut(bus_568_s)))
      Stage(operands=List(x4437, Const(64)), op=FixMod, results=List(x4438, CU.scalarOut(bus_570_s)))
      Stage(operands=List(x4438, Const(4)), op=FixDiv, results=List(x4460_x4445, CU.scalarOut(bus_572_s), CU.scalarOut(x4433_b4806_x4461_b4814_s)))
      Stage(operands=List(x4460_x4445, CU.load(x4415)), op=FixAdd, results=List(CU.scalarOut(x4433_b4807_x4461_b4815_s)))
      Stage(operands=List(CU.load(x4415), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_574_s)))
    }
    val x4462_1 = Pipeline(name="x4462_1",parent=x4462) { implicit CU => 
      val x4442 = CU.temp(None)
      val x4444 = CU.temp(None)
      val x4443 = CU.temp(None)
      val x4446 = CU.temp(None)
      val x4448 = CU.temp(None)
      val x4437 = ScalarFIFO(size=1,name="x4437").wtPort(bus_568_s)
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val x4440 = ScalarFIFO(size=1,name="x4440").wtPort(bus_574_s)
      val x4445 = ScalarFIFO(size=1,name="x4445").wtPort(bus_572_s)
      Stage(operands=List(CU.load(x4437), CU.load(x4440)), op=FixAdd, results=List(x4442))
      Stage(operands=List(x4442, Const(64)), op=FixMod, results=List(x4443))
      Stage(operands=List(Const(64), x4443), op=FixSub, results=List(x4444, CU.scalarOut(bus_577_s)))
      Stage(operands=List(x4444, Const(4)), op=FixDiv, results=List(x4446))
      Stage(operands=List(CU.load(x4415), CU.load(x4445)), op=FixAdd, results=List(x4448))
      Stage(operands=List(x4448, x4446), op=FixAdd, results=List(CU.scalarOut(x4433_b4805_x4461_b4813_s)))
    }
    val x4462_2 = Pipeline(name="x4462_2",parent=x4462) { implicit CU => 
      val x4441 = CU.temp(None)
      val x4450 = CU.temp(None)
      val x4440 = ScalarFIFO(size=1,name="x4440").wtPort(bus_574_s)
      val x4438 = ScalarFIFO(size=1,name="x4438").wtPort(bus_570_s)
      val x4444 = ScalarFIFO(size=1,name="x4444").wtPort(bus_577_s)
      val x4437 = ScalarFIFO(size=1,name="x4437").wtPort(bus_568_s)
      val x4453 = ScalarBuffer(name="x4453").wtPort(OCedges_da)
      Stage(operands=List(CU.load(x4440), CU.load(x4438)), op=FixAdd, results=List(x4450))
      Stage(operands=List(x4450, CU.load(x4444)), op=FixAdd, results=List(CU.scalarOut(x4432_b4804_x4459_b4812_s)))
      Stage(operands=List(CU.load(x4437), CU.load(x4438)), op=FixSub, results=List(x4441))
      Stage(operands=List(x4441, CU.load(x4453)), op=FixAdd, results=List(CU.scalarOut(x4432_b4803_x4459_b4811_s)))
    }
    val x4463 = MemoryController(name="x4463",parent=x4495,offchip=OCedges_oc, mctpe=TileLoad) { implicit CU => 
      val x4432_b4804 = ScalarFIFO(size=1,name="size").wtPort(x4432_b4804_x4459_b4812_s)
      val x4432_b4803 = ScalarFIFO(size=1,name="offset").wtPort(x4432_b4803_x4459_b4811_s)
      CU.newSout("data", x4434_x4463_data_s)
    }
    val x4494 = Sequential(name="x4494",parent=x4495) { implicit CU => 
      val x4494_unit = CounterChain(name = "x4494_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4493_0 = Pipeline(name="x4493_0",parent=x4494) { implicit CU => 
      val x4464 = ScalarBuffer(name="x4464").wtPort(x4433_b4806_x4461_b4814_s)
      val x4466 = ScalarBuffer(name="x4466").wtPort(x4433_b4805_x4461_b4813_s)
      val x4465 = ScalarBuffer(name="x4465").wtPort(x4433_b4807_x4461_b4815_s)
      val ctr8 = Counter(min=Const(0), max=x4466.readPort, step=Const(1), par=1) // Counter
      val x4479 = CounterChain(name = "x4479", ctr8).iter(1)
      Stage(operands=List(CU.load(x4464), CU.ctr(x4479(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x4479(0)), CU.load(x4465)), op=FixLt, results=List())
    }
    val x4563 = StreamController(name="x4563",parent=x4703) { implicit CU => 
      val x4563_unit = CounterChain(name = "x4563_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4530 = StreamController(name="x4530",parent=x4563) { implicit CU => 
      val x4530_unit = CounterChain(name = "x4530_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4530_0 = Pipeline(name="x4530_0",parent=x4530) { implicit CU => 
      val x4528_x4513 = CU.temp(None)
      val x4505 = CU.temp(None)
      val x4506 = CU.temp(None)
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val x4414 = ScalarBuffer(name="x4414").wtPort(x4341_0_s)
      Stage(operands=List(CU.load(x4414), Const(2)), op=FixSla, results=List(x4505, CU.scalarOut(bus_599_s)))
      Stage(operands=List(x4505, Const(64)), op=FixMod, results=List(x4506, CU.scalarOut(bus_601_s)))
      Stage(operands=List(x4506, Const(4)), op=FixDiv, results=List(x4528_x4513, CU.scalarOut(bus_603_s), CU.scalarOut(x4501_b4819_x4529_b4827_s)))
      Stage(operands=List(x4528_x4513, CU.load(x4415)), op=FixAdd, results=List(CU.scalarOut(x4501_b4820_x4529_b4828_s)))
      Stage(operands=List(CU.load(x4415), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_605_s)))
    }
    val x4530_1 = Pipeline(name="x4530_1",parent=x4530) { implicit CU => 
      val x4512 = CU.temp(None)
      val x4514 = CU.temp(None)
      val x4516 = CU.temp(None)
      val x4511 = CU.temp(None)
      val x4510 = CU.temp(None)
      val x4505 = ScalarFIFO(size=1,name="x4505").wtPort(bus_599_s)
      val x4508 = ScalarFIFO(size=1,name="x4508").wtPort(bus_605_s)
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val x4513 = ScalarFIFO(size=1,name="x4513").wtPort(bus_603_s)
      Stage(operands=List(CU.load(x4505), CU.load(x4508)), op=FixAdd, results=List(x4510))
      Stage(operands=List(x4510, Const(64)), op=FixMod, results=List(x4511))
      Stage(operands=List(Const(64), x4511), op=FixSub, results=List(x4512, CU.scalarOut(bus_608_s)))
      Stage(operands=List(x4512, Const(4)), op=FixDiv, results=List(x4514))
      Stage(operands=List(CU.load(x4415), CU.load(x4513)), op=FixAdd, results=List(x4516))
      Stage(operands=List(x4516, x4514), op=FixAdd, results=List(CU.scalarOut(x4501_b4818_x4529_b4826_s)))
    }
    val x4530_2 = Pipeline(name="x4530_2",parent=x4530) { implicit CU => 
      val x4509 = CU.temp(None)
      val x4518 = CU.temp(None)
      val x4512 = ScalarFIFO(size=1,name="x4512").wtPort(bus_608_s)
      val x4521 = ScalarBuffer(name="x4521").wtPort(OCcounts_da)
      val x4505 = ScalarFIFO(size=1,name="x4505").wtPort(bus_599_s)
      val x4506 = ScalarFIFO(size=1,name="x4506").wtPort(bus_601_s)
      val x4508 = ScalarFIFO(size=1,name="x4508").wtPort(bus_605_s)
      Stage(operands=List(CU.load(x4508), CU.load(x4506)), op=FixAdd, results=List(x4518))
      Stage(operands=List(x4518, CU.load(x4512)), op=FixAdd, results=List(CU.scalarOut(x4500_b4817_x4527_b4825_s)))
      Stage(operands=List(CU.load(x4505), CU.load(x4506)), op=FixSub, results=List(x4509))
      Stage(operands=List(x4509, CU.load(x4521)), op=FixAdd, results=List(CU.scalarOut(x4500_b4816_x4527_b4824_s)))
    }
    val x4531 = MemoryController(name="x4531",parent=x4563,offchip=OCcounts_oc, mctpe=TileLoad) { implicit CU => 
      val x4500_b4817 = ScalarFIFO(size=1,name="size").wtPort(x4500_b4817_x4527_b4825_s)
      val x4500_b4816 = ScalarFIFO(size=1,name="offset").wtPort(x4500_b4816_x4527_b4824_s)
      CU.newSout("data", x4502_x4531_data_s)
    }
    val x4562 = Sequential(name="x4562",parent=x4563) { implicit CU => 
      val x4562_unit = CounterChain(name = "x4562_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4561_0 = Pipeline(name="x4561_0",parent=x4562) { implicit CU => 
      val x4533 = ScalarBuffer(name="x4533").wtPort(x4501_b4820_x4529_b4828_s)
      val x4532 = ScalarBuffer(name="x4532").wtPort(x4501_b4819_x4529_b4827_s)
      val x4534 = ScalarBuffer(name="x4534").wtPort(x4501_b4818_x4529_b4826_s)
      val ctr9 = Counter(min=Const(0), max=x4534.readPort, step=Const(1), par=1) // Counter
      val x4547 = CounterChain(name = "x4547", ctr9).iter(1)
      Stage(operands=List(CU.load(x4532), CU.ctr(x4547(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x4547(0)), CU.load(x4533)), op=FixLt, results=List())
    }
    val x4565_dsp0 = MemoryPipeline(name="x4565_dsp0",parent="x4703") { implicit CU => 
      val x4586 = ScalarFIFO(size=1,name="x4586").wtPort(x4565_x4586_s)
      val x4662 = CounterChain.copy("x4689", "x4662")
      val x4570 = CounterChain.copy("x4589", "x4570")
      val x4565 = SRAM(size=16,name="x4565",banking = Strided(1)).wtPort(x4586.readPort).rdPort(x4565_0_s).rdAddr(x4662(0)).wtAddr(x4570(0))
    }
    val x4565_dsp1 = MemoryPipeline(name="x4565_dsp1",parent="x4703") { implicit CU => 
      val x4586 = ScalarFIFO(size=1,name="x4586").wtPort(x4565_x4586_s)
      val x4592 = CounterChain.copy("x4601", "x4592")
      val x4570 = CounterChain.copy("x4589", "x4570")
      val x4565 = SRAM(size=16,name="x4565",banking = Strided(1)).wtPort(x4586.readPort).rdPort(x4565_1_s).rdAddr(x4592(0)).wtAddr(x4570(0))
    }
    val x4566_dsp0 = MemoryPipeline(name="x4566_dsp0",parent="x4703") { implicit CU => 
      val x4588 = ScalarFIFO(size=1,name="x4588").wtPort(x4566_x4588_s)
      val x4662 = CounterChain.copy("x4689", "x4662")
      val x4570 = CounterChain.copy("x4589", "x4570")
      val x4566 = SRAM(size=16,name="x4566",banking = Strided(1)).wtPort(x4588.readPort).rdPort(x4566_0_s).rdAddr(x4662(0)).wtAddr(x4570(0))
    }
    val x4589 = StreamController(name="x4589",parent=x4703) { implicit CU => 
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val ctr10 = Counter(min=Const(0), max=x4415.readPort, step=Const(1), par=1) // Counter
      val x4570 = CounterChain(name = "x4570", ctr10).iter(1)
    }
    val x4589_0 = Pipeline(name="x4589_0",parent=x4589) { implicit CU => 
      val x4579 = CU.temp(None)
      val x4576 = CU.temp(None)
      val x4577 = CU.temp(None)
      val x4567 = CU.temp(Some(-1))
      val x4578 = CU.temp(None)
      val x4574 = ScalarFIFO(size=1,name="x4574").wtPort(x4426_2_s)
      val x4339 = CounterChain.copy("x4733", "x4339")
      Stage(operands=List(CU.ctr(x4339(0)), CU.load(x4574)), op=FixLeq, results=List(x4576))
      Stage(operands=List(CU.ctr(x4339(0)), Const(16)), op=FixAdd, results=List(x4577))
      Stage(operands=List(CU.load(x4574), x4577), op=FixLt, results=List(x4578))
      Stage(operands=List(x4576, x4578), op=BitAnd, results=List(x4579, CU.scalarOut(bus_641_s)))
      Stage(operands=List(x4579, Const(1), Const(0)), op=Mux, results=List(CU.scalarOut(x4566_x4588_s)))
      Stage(operands=List(x4579, x4567, Const(15)), op=Mux, results=List(CU.scalarOut(x4565_x4586_s)))
    }
    val x4589_1 = Pipeline(name="x4589_1",parent=x4589) { implicit CU => 
      val x4581 = CU.temp(None)
      val x4579 = ScalarFIFO(size=1,name="x4579").wtPort(bus_641_s)
      val x4567 = ScalarBuffer(name="x4567").wtPort(x4567_x4701_s).wtPort(x4567_x4701_s)
      Stage(operands=List(CU.load(x4579), Const(0), Const(1)), op=Mux, results=List(x4581))
      Stage(operands=List(CU.load(x4567), x4581), op=FixAdd, results=List(CU.scalarOut(x4567_x4583_s)))
    }
    val x4601 = Pipeline(name="x4601",parent=x4703) { implicit CU => 
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val ctr11 = Counter(min=Const(0), max=x4415.readPort, step=Const(1), par=1) // Counter
      val x4592 = CounterChain(name = "x4592", ctr11).iter(1)
    }
    val x4602_dsp0 = MemoryPipeline(name="x4602_dsp0",parent="x4703") { implicit CU => 
      val x4667 = ScalarFIFO(size=1,name="x4667").wtPort(x4565_0_s)
      val x4656 = ScalarFIFO(size=1,name="x4656").wtPort(x4625_x4644_data_s)
      val x4647 = CounterChain.copy("x4657", "x4647")
      val x4602 = SRAM(size=16,name="x4602",banking = Strided(1)).wtPort(x4656.readPort).rdPort(x4602_0_s).wtAddr(x4647(0))
      RAStage(operands=List(CU.load(x4667)), op=Bypass, results=List(x4602.readAddr))
    }
    val x4610_0 = Pipeline(name="x4610_0",parent=x4703) { implicit CU => 
      val x4605 = CU.temp(None)
      val x4567 = ScalarBuffer(name="x4567").wtPort(x4567_x4583_s).wtPort(x4567_x4701_s)
      val x4610_unit = CounterChain(name = "x4610_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x4567), Const(1)), op=FixAdd, results=List(x4605))
      Stage(operands=List(x4605, Const(0)), op=FixMax, results=List(CU.scalarOut(x4603_x4609_s)))
    }
    val x4623 = StreamController(name="x4623",parent=x4703) { implicit CU => 
      val x4623_unit = CounterChain(name = "x4623_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4623_0 = Pipeline(name="x4623_0",parent=x4623) { implicit CU => 
      val x4616 = CU.temp(None)
      val x4615 = CU.temp(None)
      val x4617 = CU.temp(None)
      val x4614 = CU.temp(None)
      val x4603 = ScalarBuffer(name="x4603").wtPort(x4603_x4609_s)
      Stage(operands=List(CU.load(x4603), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_662_s)))
      Stage(operands=List(CU.load(x4603), Const(16)), op=FixMod, results=List(x4614))
      Stage(operands=List(x4614, Const(0)), op=FixEql, results=List(x4615))
      Stage(operands=List(CU.load(x4603), Const(16)), op=FixAdd, results=List(x4616))
      Stage(operands=List(x4616, x4614), op=FixSub, results=List(x4617))
      Stage(operands=List(x4615, CU.load(x4603), x4617), op=Mux, results=List(CU.scalarOut(bus_668_s)))
    }
    val x4623_1 = Pipeline(name="x4623_1",parent=x4623) { implicit CU => 
      val x4618 = ScalarFIFO(size=1,name="x4618").wtPort(bus_668_s)
      val x4613 = ScalarFIFO(size=1,name="x4613").wtPort(bus_662_s)
      Stage(operands=List(CU.load(x4613), Const(16), CU.load(x4618)), op=Mux, results=List(CU.scalarOut(x4611_x4622_s)))
    }
    val x4658 = StreamController(name="x4658",parent=x4703) { implicit CU => 
      val x4658_unit = CounterChain(name = "x4658_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4643_0 = Pipeline(name="x4643_0",parent=x4658) { implicit CU => 
      val x4640 = CU.temp(None)
      val x4630 = CU.temp(None)
      val x4638 = CU.temp(None)
      val x4631 = ScalarBuffer(name="x4631").wtPort(OCpages_da)
      val x4636 = ScalarFIFO(size=1,name="x4636").wtPort(x4327_0_s)
      val x4603 = ScalarBuffer(name="x4603").wtPort(x4603_x4609_s)
      val x4611 = ScalarBuffer(name="x4611").wtPort(x4611_x4622_s)
      val ctr12 = Counter(min=Const(0), max=x4611.readPort, step=Const(1), par=1) // Counter
      val x4628 = CounterChain(name = "x4628", ctr12).iter(1)
      Stage(operands=List(CU.load(x4603), CU.ctr(x4628(0))), op=FixLeq, results=List(x4630))
      Stage(operands=List(CU.load(x4636), Const(2)), op=FixSla, results=List(x4638))
      Stage(operands=List(x4638, CU.load(x4631)), op=FixAdd, results=List(x4640))
      Stage(operands=List(x4630, CU.load(x4631), x4640), op=Mux, results=List(CU.scalarOut(x4624_x4642_s)))
    }
    val x4644 = MemoryController(name="x4644",parent=x4658,offchip=OCpages_oc, mctpe=Gather) { implicit CU => 
      val x4624 = ScalarFIFO(size=1,name="addr").wtPort(x4624_x4642_s)
      CU.newSout("data", x4625_x4644_data_s)
    }
    val x4657 = Pipeline(name="x4657",parent=x4658) { implicit CU => 
      val x4603 = ScalarBuffer(name="x4603").wtPort(x4603_x4609_s)
      val x4611 = ScalarBuffer(name="x4611").wtPort(x4611_x4622_s)
      val ctr13 = Counter(min=Const(0), max=x4611.readPort, step=Const(1), par=1) // Counter
      val x4647 = CounterChain(name = "x4647", ctr13).iter(1)
    }
    val x4689 = StreamController(name="x4689",parent=x4703) { implicit CU => 
      val x4415 = ScalarBuffer(name="x4415").wtPort(x4342_0_s)
      val ctr14 = Counter(min=Const(0), max=x4415.readPort, step=Const(1), par=1) // Counter
      val x4662 = CounterChain(name = "x4662", ctr14).iter(1)
    }
    val x4689_0 = Pipeline(name="x4689_0",parent=x4689) { implicit CU => 
      val x4681 = CU.temp(None)
      val x4678 = CU.temp(None)
      val x4676 = CU.temp(None)
      val x4677 = CU.temp(None)
      val x4672 = ScalarFIFO(size=1,name="x4672").wtPort(x4602_0_s)
      val x4668 = ScalarFIFO(size=1,name="x4668").wtPort(x4566_0_s)
      val x4669 = ScalarFIFO(size=1,name="x4669").wtPort(x4340_0_s)
      val x4666 = ScalarFIFO(size=1,name="x4666").wtPort(x4426_0_s)
      val x4670 = ScalarFIFO(size=1,name="x4670").wtPort(x4328_1_s)
      val x4413 = CounterChain.copy("x4703", "x4413")
      val x4339 = CounterChain.copy("x4733", "x4339")
      Stage(operands=List(CU.load(x4666), CU.ctr(x4339(0))), op=FixSub, results=List(x4676))
      Stage(operands=List(x4676, CU.ctr(x4413(0))), op=FixLeq, results=List(x4677))
      Stage(operands=List(x4677, CU.load(x4669), CU.load(x4670)), op=Mux, results=List(x4678))
      Stage(operands=List(CU.load(x4668), Const(1)), op=FixEql, results=List(x4681))
      Stage(operands=List(x4681, x4678, CU.load(x4672)), op=Mux, results=List(CU.scalarOut(bus_691_s)))
    }
    val x4689_1 = Pipeline(name="x4689_1",parent=x4689) { implicit CU => 
      val x4673 = ScalarFIFO(size=1,name="x4673").wtPort(x4427_0_s)
      val x4682 = ScalarFIFO(size=1,name="x4682").wtPort(bus_691_s)
      Stage(operands=List(CU.load(x4682), CU.load(x4673)), op=FixDiv, results=List(CU.reduce))
      val (_, rr695) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x4689")
      Stage(operands=List(rr695), op=Bypass, results=List(CU.scalarOut(x4659_x4688_s)))
    }
    val x4698_0 = Pipeline(name="x4698_0",parent=x4703) { implicit CU => 
      val x4693 = CU.temp(None)
      val x4692 = CU.temp(None)
      val x4659 = ScalarBuffer(name="x4659").wtPort(x4659_x4688_s)
      val x4304 = ScalarBuffer(name="x4304").wtPort(damp_argin)
      val x4698_unit = CounterChain(name = "x4698_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x4659), CU.load(x4304)), op=FixMul, results=List(x4692))
      Stage(operands=List(Const(1), CU.load(x4304)), op=FixSub, results=List(x4693))
      Stage(operands=List(x4692, x4693), op=FixAdd, results=List(CU.scalarOut(x4328_x4697_s)))
    }
    val x4702_0 = Pipeline(name="x4702_0",parent=x4703) { implicit CU => 
      val x4702_unit = CounterChain(name = "x4702_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(-1)), op=Bypass, results=List(CU.scalarOut(x4567_x4701_s)))
    }
    val x4732 = StreamController(name="x4732",parent=x4733) { implicit CU => 
      val x4732_unit = CounterChain(name = "x4732_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4718_0 = Pipeline(name="x4718_0",parent=x4732) { implicit CU => 
      val x4710 = CU.temp(None)
      val x4712 = ScalarBuffer(name="x4712").wtPort(OCpages_da)
      val x4339 = CounterChain.copy("x4733", "x4339")
      val x4718_unit = CounterChain(name = "x4718_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4339(0)), Const(2)), op=FixSla, results=List(x4710))
      Stage(operands=List(x4710, CU.load(x4712)), op=FixAdd, results=List(CU.scalarOut(x4706_b4829_x4717_b4831_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4706_b4830_x4717_b4832_s)))
    }
    val x4727 = Pipeline(name="x4727",parent=x4732) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4720 = CounterChain(name = "x4720", ctr15).iter(16)
    }
    val x4728 = MemoryController(name="x4728",parent=x4732,offchip=OCpages_oc, mctpe=TileStore) { implicit CU => 
      val x4706_b4829 = ScalarFIFO(size=1,name="offset").wtPort(x4706_b4829_x4717_b4831_s)
      val x4706_b4830 = ScalarFIFO(size=1,name="size").wtPort(x4706_b4830_x4717_b4832_s)
      val x4707 = ScalarFIFO(size=1,name="data").wtPort(x4328_0_s)
    }
    
  }
}
