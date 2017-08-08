import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object PageRank_plasticine extends PIRApp {
  def main(top:Top) = {
    val bus_606_s = Scalar("bus_606")
    val OCedgeLen_da = DRAMAddress("OCedgeLen", "OCedgeLen")
    val OCpages_da = DRAMAddress("OCpages", "OCpages")
    val bus_576_s = Scalar("bus_576")
    val bus_669_s = Scalar("bus_669")
    val bus_604_s = Scalar("bus_604")
    val bus_571_s = Scalar("bus_571")
    val x4373_b4823_x4383_b4825_s = Scalar("x4373_b4823_x4383_b4825")
    val x4417_b4831_x4427_b4833_s = Scalar("x4417_b4831_x4427_b4833")
    val x4370_x4370_dsp0_bank0_s = Scalar("x4370_x4370_dsp0_bank0")
    val x4355_x4359_s = Scalar("x4355_x4359")
    val x4461_b4837_x4491_b4845_s = Scalar("x4461_b4837_x4491_b4845")
    val bus_621_s = Scalar("bus_621")
    val bus_616_s = Scalar("bus_616")
    val x4396_x4407_data_s = Scalar("x4396_x4407_data")
    val x4598_x4620_s = Scalar("x4598_x4620")
    val bus_573_s = Scalar("bus_573")
    val x4460_b4836_x4489_b4844_s = Scalar("x4460_b4836_x4489_b4844")
    val x4461_b4839_x4491_b4847_s = Scalar("x4461_b4839_x4491_b4847")
    val x4454_x4454_dsp1_bank0_s = Scalar("x4454_x4454_dsp1_bank0")
    val x4462_x4493_data_s = Scalar("x4462_x4493_data")
    val x4417_b4832_x4427_b4834_s = Scalar("x4417_b4832_x4427_b4834")
    val x4691_x4720_s = Scalar("x4691_x4720")
    val bus_583_s = Scalar("bus_583")
    val x4395_b4828_x4405_b4830_s = Scalar("x4395_b4828_x4405_b4830")
    val x4356_x4356_dsp0_bank0_s = Scalar("x4356_x4356_dsp0_bank0")
    val x4454_x4454_dsp0_bank0_s = Scalar("x4454_x4454_dsp0_bank0")
    val x4455_x4455_dsp0_bank0_s = Scalar("x4455_x4455_dsp0_bank0")
    val bus_588_s = Scalar("bus_588")
    val bus_648_s = Scalar("bus_648")
    val bus_574_s = Scalar("bus_574")
    val NP_argin = ArgIn("NP")
    val x4356_x4356_dsp1_bank0_s = Scalar("x4356_x4356_dsp1_bank0")
    val x4395_b4827_x4405_b4829_s = Scalar("x4395_b4827_x4405_b4829")
    val x4599_x4733_s = Scalar("x4599_x4733")
    val OCcounts_oc = OffChip("OCcounts")
    val x4356_x4729_s = Scalar("x4356_x4729")
    val bus_582_s = Scalar("bus_582")
    val x4531_b4851_x4561_b4859_s = Scalar("x4531_b4851_x4561_b4859")
    val OCpages_oc = OffChip("OCpages")
    val x4598_x4598_dsp0_bank0_s = Scalar("x4598_x4598_dsp0_bank0")
    val bus_615_s = Scalar("bus_615")
    val bus_609_s = Scalar("bus_609")
    val OCedges_da = DRAMAddress("OCedges", "OCedges")
    val x4597_x4597_dsp1_bank0_s = Scalar("x4597_x4597_dsp1_bank0")
    val OCedgeLen_oc = OffChip("OCedgeLen")
    val iters_argin = ArgIn("iters")
    val x4461_b4838_x4491_b4846_s = Scalar("x4461_b4838_x4491_b4846")
    val x4374_x4385_data_s = Scalar("x4374_x4385_data")
    val x4530_b4849_x4559_b4857_s = Scalar("x4530_b4849_x4559_b4857")
    val x4597_x4597_dsp0_bank0_s = Scalar("x4597_x4597_dsp0_bank0")
    val x4355_x4355_dsp0_bank0_s = Scalar("x4355_x4355_dsp0_bank0")
    val x4418_x4429_data_s = Scalar("x4418_x4429_data")
    val x4597_x4618_s = Scalar("x4597_x4618")
    val x4738_b4861_x4749_b4863_s = Scalar("x4738_b4861_x4749_b4863")
    val x4531_b4852_x4561_b4860_s = Scalar("x4531_b4852_x4561_b4860")
    val x4460_b4835_x4489_b4843_s = Scalar("x4460_b4835_x4489_b4843")
    val x4531_b4850_x4561_b4858_s = Scalar("x4531_b4850_x4561_b4858")
    val bus_698_s = Scalar("bus_698")
    val OCedgeId_oc = OffChip("OCedgeId")
    val x4643_x4654_s = Scalar("x4643_x4654")
    val x4738_b4862_x4749_b4864_s = Scalar("x4738_b4862_x4749_b4864")
    val x4657_x4676_data_s = Scalar("x4657_x4676_data")
    val x4635_x4641_s = Scalar("x4635_x4641")
    val damp_argin = ArgIn("damp")
    val x4599_x4615_s = Scalar("x4599_x4615")
    val x4368_x4368_dsp0_bank0_s = Scalar("x4368_x4368_dsp0_bank0")
    val x4656_x4674_s = Scalar("x4656_x4674")
    val bus_675_s = Scalar("bus_675")
    val x4373_b4824_x4383_b4826_s = Scalar("x4373_b4824_x4383_b4826")
    val x4356_x4360_s = Scalar("x4356_x4360")
    val bus_607_s = Scalar("bus_607")
    val x4454_x4454_dsp2_bank0_s = Scalar("x4454_x4454_dsp2_bank0")
    val OCcounts_da = DRAMAddress("OCcounts", "OCcounts")
    val OCedges_oc = OffChip("OCedges")
    val OCedgeId_da = DRAMAddress("OCedgeId", "OCedgeId")
    val x4532_x4563_data_s = Scalar("x4532_x4563_data")
    val x4634_x4634_dsp0_bank0_s = Scalar("x4634_x4634_dsp0_bank0")
    val x4369_x4369_dsp0_bank0_s = Scalar("x4369_x4369_dsp0_bank0")
    val x4530_b4848_x4559_b4856_s = Scalar("x4530_b4848_x4559_b4856")
    val x4767 = Sequential(name="x4767",parent=top) { implicit CU => 
      val x4767_unit = CounterChain(name = "x4767_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4355_dsp0_bank0 = MemoryPipeline(name="x4355_dsp0_bank0",parent="x4767") { implicit CU => 
      val x4632 = ScalarFIFO(size=1,name="x4632").wtPort(x4454_x4454_dsp1_bank0_s)
      val x4359 = ScalarFIFO(size=1,name="x4359").wtPort(x4355_x4359_s)
      val x4628 = ScalarFIFO(size=1,name="x4628").wtPort(x4597_x4597_dsp1_bank0_s)
      val x4358 = CounterChain.copy("x4361_0", "x4358")
      val x4660 = CounterChain.copy("x4675_0", "x4660")
      val x4355 = SRAM(size=16,name="x4355",banking = NoBanking()).wtPort(x4359.readPort).wtPort(x4632.readPort).rdPort(x4355_x4355_dsp0_bank0_s).rdAddr(x4660(0)).wtAddr(x4358(0))
      WAStage(operands=List(CU.load(x4628)), op=Bypass, results=List(x4355.writeAddr))
    }
    val x4356_dsp0_bank0 = MemoryPipeline(name="x4356_dsp0_bank0",parent="x4767") { implicit CU => 
      val x4729 = ScalarFIFO(size=1,name="x4729").wtPort(x4356_x4729_s)
      val x4360 = ScalarFIFO(size=1,name="x4360").wtPort(x4356_x4360_s)
      val x4358 = CounterChain.copy("x4361_0", "x4358")
      val x4752 = CounterChain.copy("x4759", "x4752")
      val x4441 = CounterChain.copy("x4735", "x4441")
      val x4356 = SRAM(size=16,name="x4356",banking = NoBanking()).wtPort(x4360.readPort).wtPort(x4729.readPort).rdPort(x4356_x4356_dsp0_bank0_s).rdAddr(x4752(0)).wtAddr(x4358(0)).wtAddr(x4441(0))
    }
    val x4356_dsp1_bank0 = MemoryPipeline(name="x4356_dsp1_bank0",parent="x4767") { implicit CU => 
      val x4729 = ScalarFIFO(size=1,name="x4729").wtPort(x4356_x4729_s)
      val x4360 = ScalarFIFO(size=1,name="x4360").wtPort(x4356_x4360_s)
      val x4358 = CounterChain.copy("x4361_0", "x4358")
      val x4441 = CounterChain.copy("x4735", "x4441")
      val x4356 = SRAM(size=16,name="x4356",banking = NoBanking()).wtPort(x4360.readPort).wtPort(x4729.readPort).rdPort(x4356_x4356_dsp1_bank0_s).rdAddr(x4441(0)).wtAddr(x4358(0)).wtAddr(x4441(0))
    }
    val x4361_0 = Pipeline(name="x4361_0",parent=x4767) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4358 = CounterChain(name = "x4358", ctr1).iter(16)
      Stage(operands=List(Const(0)), op=Bypass, results=List(CU.scalarOut(x4355_x4359_s)))
      Stage(operands=List(Const(1)), op=Bypass, results=List(CU.scalarOut(x4356_x4360_s)))
    }
    val x4766 = Sequential(name="x4766",parent=x4767) { implicit CU => 
      val x4330 = ScalarBuffer(name="x4330").wtPort(iters_argin)
      val ctr2 = Counter(min=Const(0), max=x4330.readPort, step=Const(1), par=1) // Counter
      val x4364 = CounterChain(name = "x4364", ctr2).iter(1)
    }
    val x4765 = Sequential(name="x4765",parent=x4766) { implicit CU => 
      val x4331 = ScalarBuffer(name="x4331").wtPort(NP_argin)
      val ctr3 = Counter(min=Const(0), max=x4331.readPort, step=Const(16), par=1) // Counter
      val x4367 = CounterChain(name = "x4367", ctr3).iter(1)
    }
    val x4368_dsp0_bank0 = MemoryPipeline(name="x4368_dsp0_bank0",parent="x4765") { implicit CU => 
      val x4392 = ScalarFIFO(size=1,name="x4392").wtPort(x4374_x4385_data_s)
      val x4387 = CounterChain.copy("x4393", "x4387")
      val x4441 = CounterChain.copy("x4735", "x4441")
      val x4368 = SRAM(size=16,name="x4368",banking = NoBanking()).wtPort(x4392.readPort).rdPort(x4368_x4368_dsp0_bank0_s).rdAddr(x4441(0)).wtAddr(x4387(0))
    }
    val x4369_dsp0_bank0 = MemoryPipeline(name="x4369_dsp0_bank0",parent="x4765") { implicit CU => 
      val x4414 = ScalarFIFO(size=1,name="x4414").wtPort(x4396_x4407_data_s)
      val x4409 = CounterChain.copy("x4415", "x4409")
      val x4441 = CounterChain.copy("x4735", "x4441")
      val x4369 = SRAM(size=16,name="x4369",banking = NoBanking()).wtPort(x4414.readPort).rdPort(x4369_x4369_dsp0_bank0_s).rdAddr(x4441(0)).wtAddr(x4409(0))
    }
    val x4370_dsp0_bank0 = MemoryPipeline(name="x4370_dsp0_bank0",parent="x4765") { implicit CU => 
      val x4436 = ScalarFIFO(size=1,name="x4436").wtPort(x4418_x4429_data_s)
      val x4431 = CounterChain.copy("x4437", "x4431")
      val x4441 = CounterChain.copy("x4735", "x4441")
      val x4370 = SRAM(size=16,name="x4370",banking = NoBanking()).wtPort(x4436.readPort).rdPort(x4370_x4370_dsp0_bank0_s).rdAddr(x4441(0)).wtAddr(x4431(0))
    }
    val x4394 = StreamController(name="x4394",parent=x4765) { implicit CU => 
      val x4394_unit = CounterChain(name = "x4394_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4384_0 = Pipeline(name="x4384_0",parent=x4394) { implicit CU => 
      val x4376 = CU.temp(None)
      val x4378 = ScalarBuffer(name="x4378").wtPort(OCpages_da)
      val x4367 = CounterChain.copy("x4765", "x4367")
      val x4384_unit = CounterChain(name = "x4384_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4367(0)), Const(2)), op=FixSla, results=List(x4376))
      Stage(operands=List(x4376, CU.load(x4378)), op=FixAdd, results=List(CU.scalarOut(x4373_b4823_x4383_b4825_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4373_b4824_x4383_b4826_s)))
    }
    val x4385 = MemoryController(name="x4385",parent=x4394,offchip=OCpages_oc, mctpe=TileLoad) { implicit CU => 
      val x4373_b4823 = ScalarFIFO(size=1,name="offset").wtPort(x4373_b4823_x4383_b4825_s)
      val x4373_b4824 = ScalarFIFO(size=1,name="size").wtPort(x4373_b4824_x4383_b4826_s)
      CU.newSout("data", x4374_x4385_data_s)
    }
    val x4393 = Pipeline(name="x4393",parent=x4394) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4387 = CounterChain(name = "x4387", ctr4).iter(16)
    }
    val x4416 = StreamController(name="x4416",parent=x4765) { implicit CU => 
      val x4416_unit = CounterChain(name = "x4416_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4406_0 = Pipeline(name="x4406_0",parent=x4416) { implicit CU => 
      val x4398 = CU.temp(None)
      val x4400 = ScalarBuffer(name="x4400").wtPort(OCedgeId_da)
      val x4367 = CounterChain.copy("x4765", "x4367")
      val x4406_unit = CounterChain(name = "x4406_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4367(0)), Const(2)), op=FixSla, results=List(x4398))
      Stage(operands=List(x4398, CU.load(x4400)), op=FixAdd, results=List(CU.scalarOut(x4395_b4827_x4405_b4829_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4395_b4828_x4405_b4830_s)))
    }
    val x4407 = MemoryController(name="x4407",parent=x4416,offchip=OCedgeId_oc, mctpe=TileLoad) { implicit CU => 
      val x4395_b4828 = ScalarFIFO(size=1,name="size").wtPort(x4395_b4828_x4405_b4830_s)
      val x4395_b4827 = ScalarFIFO(size=1,name="offset").wtPort(x4395_b4827_x4405_b4829_s)
      CU.newSout("data", x4396_x4407_data_s)
    }
    val x4415 = Pipeline(name="x4415",parent=x4416) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4409 = CounterChain(name = "x4409", ctr5).iter(16)
    }
    val x4438 = StreamController(name="x4438",parent=x4765) { implicit CU => 
      val x4438_unit = CounterChain(name = "x4438_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4428_0 = Pipeline(name="x4428_0",parent=x4438) { implicit CU => 
      val x4420 = CU.temp(None)
      val x4422 = ScalarBuffer(name="x4422").wtPort(OCedgeLen_da)
      val x4367 = CounterChain.copy("x4765", "x4367")
      val x4428_unit = CounterChain(name = "x4428_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4367(0)), Const(2)), op=FixSla, results=List(x4420))
      Stage(operands=List(x4420, CU.load(x4422)), op=FixAdd, results=List(CU.scalarOut(x4417_b4831_x4427_b4833_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4417_b4832_x4427_b4834_s)))
    }
    val x4429 = MemoryController(name="x4429",parent=x4438,offchip=OCedgeLen_oc, mctpe=TileLoad) { implicit CU => 
      val x4417_b4832 = ScalarFIFO(size=1,name="size").wtPort(x4417_b4832_x4427_b4834_s)
      val x4417_b4831 = ScalarFIFO(size=1,name="offset").wtPort(x4417_b4831_x4427_b4833_s)
      CU.newSout("data", x4418_x4429_data_s)
    }
    val x4437 = Pipeline(name="x4437",parent=x4438) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4431 = CounterChain(name = "x4431", ctr6).iter(16)
    }
    val x4735 = Sequential(name="x4735",parent=x4765) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4441 = CounterChain(name = "x4441", ctr7).iter(16)
    }
    val x4454_dsp0_bank0 = MemoryPipeline(name="x4454_dsp0_bank0",parent="x4735") { implicit CU => 
      val x4494 = ScalarBuffer(name="x4494").wtPort(x4461_b4838_x4491_b4846_s)
      val x4522 = ScalarFIFO(size=1,name="x4522").wtPort(x4462_x4493_data_s)
      val x4694 = CounterChain.copy("x4721", "x4694")
      val x4509 = CounterChain.copy("x4523_0", "x4509")
      val x4454 = SRAM(size=16,name="x4454",banking = NoBanking()).wtPort(x4522.readPort).rdPort(x4454_x4454_dsp0_bank0_s).rdAddr(x4694(0))
      WAStage(operands=List(CU.ctr(x4509(0)), CU.load(x4494)), op=FixSub, results=List(x4454.writeAddr))
    }
    val x4454_dsp1_bank0 = MemoryPipeline(name="x4454_dsp1_bank0",parent="x4735") { implicit CU => 
      val x4494 = ScalarBuffer(name="x4494").wtPort(x4461_b4838_x4491_b4846_s)
      val x4522 = ScalarFIFO(size=1,name="x4522").wtPort(x4462_x4493_data_s)
      val x4624 = CounterChain.copy("x4633", "x4624")
      val x4509 = CounterChain.copy("x4523_0", "x4509")
      val x4454 = SRAM(size=16,name="x4454",banking = NoBanking()).wtPort(x4522.readPort).rdPort(x4454_x4454_dsp1_bank0_s).rdAddr(x4624(0))
      WAStage(operands=List(CU.ctr(x4509(0)), CU.load(x4494)), op=FixSub, results=List(x4454.writeAddr))
    }
    val x4454_dsp2_bank0 = MemoryPipeline(name="x4454_dsp2_bank0",parent="x4735") { implicit CU => 
      val x4494 = ScalarBuffer(name="x4494").wtPort(x4461_b4838_x4491_b4846_s)
      val x4522 = ScalarFIFO(size=1,name="x4522").wtPort(x4462_x4493_data_s)
      val x4602 = CounterChain.copy("x4621", "x4602")
      val x4509 = CounterChain.copy("x4523_0", "x4509")
      val x4454 = SRAM(size=16,name="x4454",banking = NoBanking()).wtPort(x4522.readPort).rdPort(x4454_x4454_dsp2_bank0_s).rdAddr(x4602(0))
      WAStage(operands=List(CU.ctr(x4509(0)), CU.load(x4494)), op=FixSub, results=List(x4454.writeAddr))
    }
    val x4455_dsp0_bank0 = MemoryPipeline(name="x4455_dsp0_bank0",parent="x4735") { implicit CU => 
      val x4592 = ScalarFIFO(size=1,name="x4592").wtPort(x4532_x4563_data_s)
      val x4564 = ScalarBuffer(name="x4564").wtPort(x4531_b4851_x4561_b4859_s)
      val x4694 = CounterChain.copy("x4721", "x4694")
      val x4579 = CounterChain.copy("x4593_0", "x4579")
      val x4455 = SRAM(size=16,name="x4455",banking = NoBanking()).wtPort(x4592.readPort).rdPort(x4455_x4455_dsp0_bank0_s).rdAddr(x4694(0))
      WAStage(operands=List(CU.ctr(x4579(0)), CU.load(x4564)), op=FixSub, results=List(x4455.writeAddr))
    }
    val x4525 = StreamController(name="x4525",parent=x4735) { implicit CU => 
      val x4525_unit = CounterChain(name = "x4525_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4492 = StreamController(name="x4492",parent=x4525) { implicit CU => 
      val x4492_unit = CounterChain(name = "x4492_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4492_0 = Pipeline(name="x4492_0",parent=x4492) { implicit CU => 
      val x4465 = CU.temp(None)
      val x4490_x4475 = CU.temp(None)
      val x4466 = CU.temp(None)
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val x4442 = ScalarBuffer(name="x4442").wtPort(x4369_x4369_dsp0_bank0_s)
      Stage(operands=List(CU.load(x4442), Const(2)), op=FixSla, results=List(x4465, CU.scalarOut(bus_571_s)))
      Stage(operands=List(x4465, Const(64)), op=FixMod, results=List(x4466, CU.scalarOut(bus_573_s)))
      Stage(operands=List(x4466, Const(2)), op=FixSra, results=List(x4490_x4475, CU.scalarOut(bus_574_s), CU.scalarOut(x4461_b4838_x4491_b4846_s)))
      Stage(operands=List(x4490_x4475, CU.load(x4443)), op=FixAdd, results=List(CU.scalarOut(x4461_b4839_x4491_b4847_s)))
      Stage(operands=List(CU.load(x4443), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_576_s)))
    }
    val x4492_1 = Pipeline(name="x4492_1",parent=x4492) { implicit CU => 
      val x4471 = CU.temp(None)
      val x4470 = CU.temp(None)
      val x4474 = CU.temp(None)
      val x4473 = CU.temp(None)
      val x4472 = CU.temp(None)
      val x4468 = ScalarFIFO(size=1,name="x4468").wtPort(bus_576_s)
      val x4465 = ScalarFIFO(size=1,name="x4465").wtPort(bus_571_s)
      Stage(operands=List(CU.load(x4465), CU.load(x4468)), op=FixAdd, results=List(x4470))
      Stage(operands=List(x4470, Const(64)), op=FixMod, results=List(x4471))
      Stage(operands=List(x4471, Const(0)), op=FixEql, results=List(x4472))
      Stage(operands=List(Const(64), x4471), op=FixSub, results=List(x4473))
      Stage(operands=List(x4472, Const(0), x4473), op=Mux, results=List(x4474, CU.scalarOut(bus_582_s)))
      Stage(operands=List(x4474, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_583_s)))
    }
    val x4492_2 = Pipeline(name="x4492_2",parent=x4492) { implicit CU => 
      val x4478 = CU.temp(None)
      val x4480 = CU.temp(None)
      val x4476 = ScalarFIFO(size=1,name="x4476").wtPort(bus_583_s)
      val x4468 = ScalarFIFO(size=1,name="x4468").wtPort(bus_576_s)
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val x4474 = ScalarFIFO(size=1,name="x4474").wtPort(bus_582_s)
      val x4465 = ScalarFIFO(size=1,name="x4465").wtPort(bus_571_s)
      val x4466 = ScalarFIFO(size=1,name="x4466").wtPort(bus_573_s)
      val x4475 = ScalarFIFO(size=1,name="x4475").wtPort(bus_574_s)
      Stage(operands=List(CU.load(x4443), CU.load(x4475)), op=FixAdd, results=List(x4478))
      Stage(operands=List(x4478, CU.load(x4476)), op=FixAdd, results=List(CU.scalarOut(x4461_b4837_x4491_b4845_s)))
      Stage(operands=List(CU.load(x4468), CU.load(x4466)), op=FixAdd, results=List(x4480))
      Stage(operands=List(x4480, CU.load(x4474)), op=FixAdd, results=List(CU.scalarOut(x4460_b4836_x4489_b4844_s)))
      Stage(operands=List(CU.load(x4465), CU.load(x4466)), op=FixSub, results=List(CU.scalarOut(bus_588_s)))
    }
    val x4492_3 = Pipeline(name="x4492_3",parent=x4492) { implicit CU => 
      val x4469 = ScalarFIFO(size=1,name="x4469").wtPort(bus_588_s)
      val x4483 = ScalarBuffer(name="x4483").wtPort(OCedges_da)
      Stage(operands=List(CU.load(x4469), CU.load(x4483)), op=FixAdd, results=List(CU.scalarOut(x4460_b4835_x4489_b4843_s)))
    }
    val x4493 = MemoryController(name="x4493",parent=x4525,offchip=OCedges_oc, mctpe=TileLoad) { implicit CU => 
      val x4460_b4835 = ScalarFIFO(size=1,name="offset").wtPort(x4460_b4835_x4489_b4843_s)
      val x4460_b4836 = ScalarFIFO(size=1,name="size").wtPort(x4460_b4836_x4489_b4844_s)
      CU.newSout("data", x4462_x4493_data_s)
    }
    val x4524 = Sequential(name="x4524",parent=x4525) { implicit CU => 
      val x4524_unit = CounterChain(name = "x4524_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4523_0 = Pipeline(name="x4523_0",parent=x4524) { implicit CU => 
      val x4494 = ScalarBuffer(name="x4494").wtPort(x4461_b4838_x4491_b4846_s)
      val x4496 = ScalarBuffer(name="x4496").wtPort(x4461_b4837_x4491_b4845_s)
      val x4495 = ScalarBuffer(name="x4495").wtPort(x4461_b4839_x4491_b4847_s)
      val ctr8 = Counter(min=Const(0), max=x4496.readPort, step=Const(1), par=1) // Counter
      val x4509 = CounterChain(name = "x4509", ctr8).iter(1)
      Stage(operands=List(CU.load(x4494), CU.ctr(x4509(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x4509(0)), CU.load(x4495)), op=FixLt, results=List())
    }
    val x4595 = StreamController(name="x4595",parent=x4735) { implicit CU => 
      val x4595_unit = CounterChain(name = "x4595_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4562 = StreamController(name="x4562",parent=x4595) { implicit CU => 
      val x4562_unit = CounterChain(name = "x4562_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4562_0 = Pipeline(name="x4562_0",parent=x4562) { implicit CU => 
      val x4535 = CU.temp(None)
      val x4560_x4545 = CU.temp(None)
      val x4536 = CU.temp(None)
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val x4442 = ScalarBuffer(name="x4442").wtPort(x4369_x4369_dsp0_bank0_s)
      Stage(operands=List(CU.load(x4442), Const(2)), op=FixSla, results=List(x4535, CU.scalarOut(bus_604_s)))
      Stage(operands=List(x4535, Const(64)), op=FixMod, results=List(x4536, CU.scalarOut(bus_606_s)))
      Stage(operands=List(x4536, Const(2)), op=FixSra, results=List(x4560_x4545, CU.scalarOut(bus_607_s), CU.scalarOut(x4531_b4851_x4561_b4859_s)))
      Stage(operands=List(x4560_x4545, CU.load(x4443)), op=FixAdd, results=List(CU.scalarOut(x4531_b4852_x4561_b4860_s)))
      Stage(operands=List(CU.load(x4443), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_609_s)))
    }
    val x4562_1 = Pipeline(name="x4562_1",parent=x4562) { implicit CU => 
      val x4540 = CU.temp(None)
      val x4542 = CU.temp(None)
      val x4544 = CU.temp(None)
      val x4541 = CU.temp(None)
      val x4543 = CU.temp(None)
      val x4535 = ScalarFIFO(size=1,name="x4535").wtPort(bus_604_s)
      val x4538 = ScalarFIFO(size=1,name="x4538").wtPort(bus_609_s)
      Stage(operands=List(CU.load(x4535), CU.load(x4538)), op=FixAdd, results=List(x4540))
      Stage(operands=List(x4540, Const(64)), op=FixMod, results=List(x4541))
      Stage(operands=List(x4541, Const(0)), op=FixEql, results=List(x4542))
      Stage(operands=List(Const(64), x4541), op=FixSub, results=List(x4543))
      Stage(operands=List(x4542, Const(0), x4543), op=Mux, results=List(x4544, CU.scalarOut(bus_615_s)))
      Stage(operands=List(x4544, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_616_s)))
    }
    val x4562_2 = Pipeline(name="x4562_2",parent=x4562) { implicit CU => 
      val x4548 = CU.temp(None)
      val x4550 = CU.temp(None)
      val x4535 = ScalarFIFO(size=1,name="x4535").wtPort(bus_604_s)
      val x4546 = ScalarFIFO(size=1,name="x4546").wtPort(bus_616_s)
      val x4536 = ScalarFIFO(size=1,name="x4536").wtPort(bus_606_s)
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val x4545 = ScalarFIFO(size=1,name="x4545").wtPort(bus_607_s)
      val x4538 = ScalarFIFO(size=1,name="x4538").wtPort(bus_609_s)
      val x4544 = ScalarFIFO(size=1,name="x4544").wtPort(bus_615_s)
      Stage(operands=List(CU.load(x4443), CU.load(x4545)), op=FixAdd, results=List(x4548))
      Stage(operands=List(x4548, CU.load(x4546)), op=FixAdd, results=List(CU.scalarOut(x4531_b4850_x4561_b4858_s)))
      Stage(operands=List(CU.load(x4538), CU.load(x4536)), op=FixAdd, results=List(x4550))
      Stage(operands=List(x4550, CU.load(x4544)), op=FixAdd, results=List(CU.scalarOut(x4530_b4849_x4559_b4857_s)))
      Stage(operands=List(CU.load(x4535), CU.load(x4536)), op=FixSub, results=List(CU.scalarOut(bus_621_s)))
    }
    val x4562_3 = Pipeline(name="x4562_3",parent=x4562) { implicit CU => 
      val x4539 = ScalarFIFO(size=1,name="x4539").wtPort(bus_621_s)
      val x4553 = ScalarBuffer(name="x4553").wtPort(OCcounts_da)
      Stage(operands=List(CU.load(x4539), CU.load(x4553)), op=FixAdd, results=List(CU.scalarOut(x4530_b4848_x4559_b4856_s)))
    }
    val x4563 = MemoryController(name="x4563",parent=x4595,offchip=OCcounts_oc, mctpe=TileLoad) { implicit CU => 
      val x4530_b4849 = ScalarFIFO(size=1,name="size").wtPort(x4530_b4849_x4559_b4857_s)
      val x4530_b4848 = ScalarFIFO(size=1,name="offset").wtPort(x4530_b4848_x4559_b4856_s)
      CU.newSout("data", x4532_x4563_data_s)
    }
    val x4594 = Sequential(name="x4594",parent=x4595) { implicit CU => 
      val x4594_unit = CounterChain(name = "x4594_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4593_0 = Pipeline(name="x4593_0",parent=x4594) { implicit CU => 
      val x4566 = ScalarBuffer(name="x4566").wtPort(x4531_b4850_x4561_b4858_s)
      val x4565 = ScalarBuffer(name="x4565").wtPort(x4531_b4852_x4561_b4860_s)
      val x4564 = ScalarBuffer(name="x4564").wtPort(x4531_b4851_x4561_b4859_s)
      val ctr9 = Counter(min=Const(0), max=x4566.readPort, step=Const(1), par=1) // Counter
      val x4579 = CounterChain(name = "x4579", ctr9).iter(1)
      Stage(operands=List(CU.load(x4564), CU.ctr(x4579(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x4579(0)), CU.load(x4565)), op=FixLt, results=List())
    }
    val x4597_dsp0_bank0 = MemoryPipeline(name="x4597_dsp0_bank0",parent="x4735") { implicit CU => 
      val x4618 = ScalarFIFO(size=1,name="x4618").wtPort(x4597_x4618_s)
      val x4602 = CounterChain.copy("x4621", "x4602")
      val x4694 = CounterChain.copy("x4721", "x4694")
      val x4597 = SRAM(size=16,name="x4597",banking = NoBanking()).wtPort(x4618.readPort).rdPort(x4597_x4597_dsp0_bank0_s).rdAddr(x4694(0)).wtAddr(x4602(0))
    }
    val x4597_dsp1_bank0 = MemoryPipeline(name="x4597_dsp1_bank0",parent="x4735") { implicit CU => 
      val x4618 = ScalarFIFO(size=1,name="x4618").wtPort(x4597_x4618_s)
      val x4624 = CounterChain.copy("x4633", "x4624")
      val x4602 = CounterChain.copy("x4621", "x4602")
      val x4597 = SRAM(size=16,name="x4597",banking = NoBanking()).wtPort(x4618.readPort).rdPort(x4597_x4597_dsp1_bank0_s).rdAddr(x4624(0)).wtAddr(x4602(0))
    }
    val x4598_dsp0_bank0 = MemoryPipeline(name="x4598_dsp0_bank0",parent="x4735") { implicit CU => 
      val x4620 = ScalarFIFO(size=1,name="x4620").wtPort(x4598_x4620_s)
      val x4602 = CounterChain.copy("x4621", "x4602")
      val x4694 = CounterChain.copy("x4721", "x4694")
      val x4598 = SRAM(size=16,name="x4598",banking = NoBanking()).wtPort(x4620.readPort).rdPort(x4598_x4598_dsp0_bank0_s).rdAddr(x4694(0)).wtAddr(x4602(0))
    }
    val x4621 = StreamController(name="x4621",parent=x4735) { implicit CU => 
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val ctr10 = Counter(min=Const(0), max=x4443.readPort, step=Const(1), par=1) // Counter
      val x4602 = CounterChain(name = "x4602", ctr10).iter(1)
    }
    val x4621_0 = Pipeline(name="x4621_0",parent=x4621) { implicit CU => 
      val x4610 = CU.temp(None)
      val x4609 = CU.temp(None)
      val x4599 = CU.temp(Some(-1))
      val x4611 = CU.temp(None)
      val x4608 = CU.temp(None)
      val x4606 = ScalarFIFO(size=1,name="x4606").wtPort(x4454_x4454_dsp2_bank0_s)
      val x4367 = CounterChain.copy("x4765", "x4367")
      Stage(operands=List(CU.ctr(x4367(0)), CU.load(x4606)), op=FixLeq, results=List(x4608))
      Stage(operands=List(CU.ctr(x4367(0)), Const(16)), op=FixAdd, results=List(x4609))
      Stage(operands=List(CU.load(x4606), x4609), op=FixLt, results=List(x4610))
      Stage(operands=List(x4608, x4610), op=BitAnd, results=List(x4611, CU.scalarOut(bus_648_s)))
      Stage(operands=List(x4611, Const(1), Const(0)), op=Mux, results=List(CU.scalarOut(x4598_x4620_s)))
      Stage(operands=List(x4611, x4599, Const(15)), op=Mux, results=List(CU.scalarOut(x4597_x4618_s)))
    }
    val x4621_1 = Pipeline(name="x4621_1",parent=x4621) { implicit CU => 
      val x4613 = CU.temp(None)
      val x4599 = ScalarBuffer(name="x4599").wtPort(x4599_x4733_s).wtPort(x4599_x4733_s)
      val x4611 = ScalarFIFO(size=1,name="x4611").wtPort(bus_648_s)
      Stage(operands=List(CU.load(x4611), Const(0), Const(1)), op=Mux, results=List(x4613))
      Stage(operands=List(CU.load(x4599), x4613), op=FixAdd, results=List(CU.scalarOut(x4599_x4615_s)))
    }
    val x4633 = Pipeline(name="x4633",parent=x4735) { implicit CU => 
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val ctr11 = Counter(min=Const(0), max=x4443.readPort, step=Const(1), par=1) // Counter
      val x4624 = CounterChain(name = "x4624", ctr11).iter(1)
    }
    val x4634_dsp0_bank0 = MemoryPipeline(name="x4634_dsp0_bank0",parent="x4735") { implicit CU => 
      val x4688 = ScalarFIFO(size=1,name="x4688").wtPort(x4657_x4676_data_s)
      val x4699 = ScalarFIFO(size=1,name="x4699").wtPort(x4597_x4597_dsp0_bank0_s)
      val x4679 = CounterChain.copy("x4689", "x4679")
      val x4634 = SRAM(size=16,name="x4634",banking = NoBanking()).wtPort(x4688.readPort).rdPort(x4634_x4634_dsp0_bank0_s).wtAddr(x4679(0))
      RAStage(operands=List(CU.load(x4699)), op=Bypass, results=List(x4634.readAddr))
    }
    val x4642_0 = Pipeline(name="x4642_0",parent=x4735) { implicit CU => 
      val x4637 = CU.temp(None)
      val x4599 = ScalarBuffer(name="x4599").wtPort(x4599_x4615_s).wtPort(x4599_x4733_s)
      val x4642_unit = CounterChain(name = "x4642_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x4599), Const(1)), op=FixAdd, results=List(x4637))
      Stage(operands=List(x4637, Const(0)), op=FixMax, results=List(CU.scalarOut(x4635_x4641_s)))
    }
    val x4655 = StreamController(name="x4655",parent=x4735) { implicit CU => 
      val x4655_unit = CounterChain(name = "x4655_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4655_0 = Pipeline(name="x4655_0",parent=x4655) { implicit CU => 
      val x4646 = CU.temp(None)
      val x4649 = CU.temp(None)
      val x4648 = CU.temp(None)
      val x4647 = CU.temp(None)
      val x4635 = ScalarBuffer(name="x4635").wtPort(x4635_x4641_s)
      Stage(operands=List(CU.load(x4635), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_669_s)))
      Stage(operands=List(CU.load(x4635), Const(16)), op=FixMod, results=List(x4646))
      Stage(operands=List(x4646, Const(0)), op=FixEql, results=List(x4647))
      Stage(operands=List(CU.load(x4635), Const(16)), op=FixAdd, results=List(x4648))
      Stage(operands=List(x4648, x4646), op=FixSub, results=List(x4649))
      Stage(operands=List(x4647, CU.load(x4635), x4649), op=Mux, results=List(CU.scalarOut(bus_675_s)))
    }
    val x4655_1 = Pipeline(name="x4655_1",parent=x4655) { implicit CU => 
      val x4650 = ScalarFIFO(size=1,name="x4650").wtPort(bus_675_s)
      val x4645 = ScalarFIFO(size=1,name="x4645").wtPort(bus_669_s)
      Stage(operands=List(CU.load(x4645), Const(16), CU.load(x4650)), op=Mux, results=List(CU.scalarOut(x4643_x4654_s)))
    }
    val x4690 = StreamController(name="x4690",parent=x4735) { implicit CU => 
      val x4690_unit = CounterChain(name = "x4690_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4675_0 = Pipeline(name="x4675_0",parent=x4690) { implicit CU => 
      val x4672 = CU.temp(None)
      val x4670 = CU.temp(None)
      val x4662 = CU.temp(None)
      val x4668 = ScalarFIFO(size=1,name="x4668").wtPort(x4355_x4355_dsp0_bank0_s)
      val x4635 = ScalarBuffer(name="x4635").wtPort(x4635_x4641_s)
      val x4643 = ScalarBuffer(name="x4643").wtPort(x4643_x4654_s)
      val x4663 = ScalarBuffer(name="x4663").wtPort(OCpages_da)
      val ctr12 = Counter(min=Const(0), max=x4643.readPort, step=Const(1), par=1) // Counter
      val x4660 = CounterChain(name = "x4660", ctr12).iter(1)
      Stage(operands=List(CU.load(x4635), CU.ctr(x4660(0))), op=FixLeq, results=List(x4662))
      Stage(operands=List(CU.load(x4668), Const(2)), op=FixSla, results=List(x4670))
      Stage(operands=List(x4670, CU.load(x4663)), op=FixAdd, results=List(x4672))
      Stage(operands=List(x4662, CU.load(x4663), x4672), op=Mux, results=List(CU.scalarOut(x4656_x4674_s)))
    }
    val x4676 = MemoryController(name="x4676",parent=x4690,offchip=OCpages_oc, mctpe=Gather) { implicit CU => 
      val x4656 = ScalarFIFO(size=1,name="addr").wtPort(x4656_x4674_s)
      CU.newSout("data", x4657_x4676_data_s)
    }
    val x4689 = Pipeline(name="x4689",parent=x4690) { implicit CU => 
      val x4635 = ScalarBuffer(name="x4635").wtPort(x4635_x4641_s)
      val x4643 = ScalarBuffer(name="x4643").wtPort(x4643_x4654_s)
      val ctr13 = Counter(min=Const(0), max=x4643.readPort, step=Const(1), par=1) // Counter
      val x4679 = CounterChain(name = "x4679", ctr13).iter(1)
    }
    val x4721 = StreamController(name="x4721",parent=x4735) { implicit CU => 
      val x4443 = ScalarBuffer(name="x4443").wtPort(x4370_x4370_dsp0_bank0_s)
      val ctr14 = Counter(min=Const(0), max=x4443.readPort, step=Const(1), par=1) // Counter
      val x4694 = CounterChain(name = "x4694", ctr14).iter(1)
    }
    val x4721_0 = Pipeline(name="x4721_0",parent=x4721) { implicit CU => 
      val x4709 = CU.temp(None)
      val x4710 = CU.temp(None)
      val x4708 = CU.temp(None)
      val x4713 = CU.temp(None)
      val x4704 = ScalarFIFO(size=1,name="x4704").wtPort(x4634_x4634_dsp0_bank0_s)
      val x4702 = ScalarFIFO(size=1,name="x4702").wtPort(x4356_x4356_dsp1_bank0_s)
      val x4700 = ScalarFIFO(size=1,name="x4700").wtPort(x4598_x4598_dsp0_bank0_s)
      val x4698 = ScalarFIFO(size=1,name="x4698").wtPort(x4454_x4454_dsp0_bank0_s)
      val x4701 = ScalarFIFO(size=1,name="x4701").wtPort(x4368_x4368_dsp0_bank0_s)
      val x4441 = CounterChain.copy("x4735", "x4441")
      val x4367 = CounterChain.copy("x4765", "x4367")
      Stage(operands=List(CU.load(x4698), CU.ctr(x4367(0))), op=FixSub, results=List(x4708))
      Stage(operands=List(x4708, CU.ctr(x4441(0))), op=FixLeq, results=List(x4709))
      Stage(operands=List(x4709, CU.load(x4701), CU.load(x4702)), op=Mux, results=List(x4710))
      Stage(operands=List(CU.load(x4700), Const(1)), op=FixEql, results=List(x4713))
      Stage(operands=List(x4713, x4710, CU.load(x4704)), op=Mux, results=List(CU.scalarOut(bus_698_s)))
    }
    val x4721_1 = Pipeline(name="x4721_1",parent=x4721) { implicit CU => 
      val x4705 = ScalarFIFO(size=1,name="x4705").wtPort(x4455_x4455_dsp0_bank0_s)
      val x4714 = ScalarFIFO(size=1,name="x4714").wtPort(bus_698_s)
      Stage(operands=List(CU.load(x4714), CU.load(x4705)), op=FixDiv, results=List(CU.reduce))
      val (_, rr702) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x4721")
      Stage(operands=List(rr702), op=Bypass, results=List(CU.scalarOut(x4691_x4720_s)))
    }
    val x4730_0 = Pipeline(name="x4730_0",parent=x4735) { implicit CU => 
      val x4725 = CU.temp(None)
      val x4724 = CU.temp(None)
      val x4691 = ScalarBuffer(name="x4691").wtPort(x4691_x4720_s)
      val x4332 = ScalarBuffer(name="x4332").wtPort(damp_argin)
      val x4730_unit = CounterChain(name = "x4730_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x4691), CU.load(x4332)), op=FixMul, results=List(x4724))
      Stage(operands=List(Const(1), CU.load(x4332)), op=FixSub, results=List(x4725))
      Stage(operands=List(x4724, x4725), op=FixAdd, results=List(CU.scalarOut(x4356_x4729_s)))
    }
    val x4734_0 = Pipeline(name="x4734_0",parent=x4735) { implicit CU => 
      val x4734_unit = CounterChain(name = "x4734_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(-1)), op=Bypass, results=List(CU.scalarOut(x4599_x4733_s)))
    }
    val x4764 = StreamController(name="x4764",parent=x4765) { implicit CU => 
      val x4764_unit = CounterChain(name = "x4764_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4750_0 = Pipeline(name="x4750_0",parent=x4764) { implicit CU => 
      val x4742 = CU.temp(None)
      val x4744 = ScalarBuffer(name="x4744").wtPort(OCpages_da)
      val x4367 = CounterChain.copy("x4765", "x4367")
      val x4750_unit = CounterChain(name = "x4750_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4367(0)), Const(2)), op=FixSla, results=List(x4742))
      Stage(operands=List(x4742, CU.load(x4744)), op=FixAdd, results=List(CU.scalarOut(x4738_b4861_x4749_b4863_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4738_b4862_x4749_b4864_s)))
    }
    val x4759 = Pipeline(name="x4759",parent=x4764) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4752 = CounterChain(name = "x4752", ctr15).iter(16)
    }
    val x4760 = MemoryController(name="x4760",parent=x4764,offchip=OCpages_oc, mctpe=TileStore) { implicit CU => 
      val x4738_b4862 = ScalarFIFO(size=1,name="size").wtPort(x4738_b4862_x4749_b4864_s)
      val x4739 = ScalarFIFO(size=1,name="data").wtPort(x4356_x4356_dsp0_bank0_s)
      val x4738_b4861 = ScalarFIFO(size=1,name="offset").wtPort(x4738_b4861_x4749_b4863_s)
    }
    
  }
}
