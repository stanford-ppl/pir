import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object OuterProduct extends PIRApp {
  def main(top:Top) = {
    val x4555_b5450_x4564_b5452_s = Scalar("x4555_b5450_x4564_b5452")
    val x4362_x4930_v = Vector("x4362_x4930")
    val x5195_b5548_x5210_b5550_s = Scalar("x5195_b5548_x5210_b5550")
    val x4362_x5299_x5303_v = Vector("x4362_x5299_x5303")
    val x4719_b5478_x4728_b5480_s = Scalar("x4719_b5478_x4728_b5480")
    val vec2_oc = OffChip("vec2")
    val x4346_x4855_x4859_v = Vector("x4346_x4855_x4859")
    val x4790_b5489_x4799_b5491_s = Scalar("x4790_b5489_x4799_b5491")
    val x4358_x5135_x5139_v = Vector("x4358_x5135_x5139")
    val x4367_b5417_x4376_b5419_s = Scalar("x4367_b5417_x4376_b5419")
    val x5277_b5560_x5292_b5562_s = Scalar("x5277_b5560_x5292_b5562")
    val x4766_b5486_x4775_b5488_s = Scalar("x4766_b5486_x4775_b5488")
    val x4358_x4882_v = Vector("x4358_x4882")
    val x4357_x4870_v = Vector("x4357_x4870")
    val out_da = DRAMAddress("out", "out")
    val x4341_x4914_x4919_s = Scalar("x4341_x4914_x4919")
    val x4414_b5426_x4423_b5428_s = Scalar("x4414_b5426_x4423_b5428")
    val x4348_x4879_x4883_v = Vector("x4348_x4879_x4883")
    val x4532_x4542_data_v = Vector("x4532_x4542_data")
    val x4813_b5494_x4822_b5496_s = Scalar("x4813_b5494_x4822_b5496")
    val x4356_x4858_v = Vector("x4356_x4858")
    val x4814_x4824_data_v = Vector("x4814_x4824_data")
    val x4531_b5445_x4540_b5447_s = Scalar("x4531_b5445_x4540_b5447")
    val x4508_b5441_x4517_b5443_s = Scalar("x4508_b5441_x4517_b5443")
    val x4367_b5418_x4376_b5420_s = Scalar("x4367_b5418_x4376_b5420")
    val x4555_b5449_x4564_b5451_s = Scalar("x4555_b5449_x4564_b5451")
    val x4342_x4926_x4931_s = Scalar("x4342_x4926_x4931")
    val x5359_b5572_x5374_b5574_s = Scalar("x5359_b5572_x5374_b5574")
    val x4356_x5053_x5057_v = Vector("x4356_x5053_x5057")
    val sizeB_argin = ArgIn("sizeB").bound(38400)
    val x4368_x4378_data_v = Vector("x4368_x4378_data")
    val x4354_x4951_x4955_v = Vector("x4354_x4951_x4955")
    val x4578_b5454_x4587_b5456_s = Scalar("x4578_b5454_x4587_b5456")
    val x4696_b5473_x4705_b5475_s = Scalar("x4696_b5473_x4705_b5475")
    val x4337_x4866_x4871_s = Scalar("x4337_x4866_x4871")
    val x4359_x4894_v = Vector("x4359_x4894")
    val x4355_x4846_v = Vector("x4355_x4846")
    val x4363_x5340_x5344_v = Vector("x4363_x5340_x5344")
    val x5236_b5554_x5251_b5556_s = Scalar("x5236_b5554_x5251_b5556")
    val x4673_x4683_data_v = Vector("x4673_x4683_data")
    val x4485_x4495_data_v = Vector("x4485_x4495_data")
    val x4461_b5433_x4470_b5435_s = Scalar("x4461_b5433_x4470_b5435")
    val x4672_b5469_x4681_b5471_s = Scalar("x4672_b5469_x4681_b5471")
    val x4343_x4938_x4943_s = Scalar("x4343_x4938_x4943")
    val x4340_x4902_x4907_s = Scalar("x4340_x4902_x4907")
    val x4357_x5094_x5098_v = Vector("x4357_x5094_x5098")
    val x4578_b5453_x4587_b5455_s = Scalar("x4578_b5453_x4587_b5455")
    val x4744_x4754_data_v = Vector("x4744_x4754_data")
    val x4990_b5518_x5005_b5520_s = Scalar("x4990_b5518_x5005_b5520")
    val x4347_x4867_x4871_v = Vector("x4347_x4867_x4871")
    val x4556_x4566_data_v = Vector("x4556_x4566_data")
    val x4349_x4891_x4895_v = Vector("x4349_x4891_x4895")
    val vec1_da = DRAMAddress("vec1", "vec1")
    val x5318_b5565_x5333_b5567_s = Scalar("x5318_b5565_x5333_b5567")
    val x4390_b5421_x4399_b5423_s = Scalar("x4390_b5421_x4399_b5423")
    val x5154_b5541_x5169_b5543_s = Scalar("x5154_b5541_x5169_b5543")
    val x4352_x4927_x4931_v = Vector("x4352_x4927_x4931")
    val x4484_b5437_x4493_b5439_s = Scalar("x4484_b5437_x4493_b5439")
    val x4990_b5517_x5005_b5519_s = Scalar("x4990_b5517_x5005_b5519")
    val x4649_b5465_x4658_b5467_s = Scalar("x4649_b5465_x4658_b5467")
    val x4339_x4890_x4895_s = Scalar("x4339_x4890_x4895")
    val x4743_b5482_x4752_b5484_s = Scalar("x4743_b5482_x4752_b5484")
    val x4696_b5474_x4705_b5476_s = Scalar("x4696_b5474_x4705_b5476")
    val x4415_x4425_data_v = Vector("x4415_x4425_data")
    val x5113_b5536_x5128_b5538_s = Scalar("x5113_b5536_x5128_b5538")
    val x4579_x4589_data_v = Vector("x4579_x4589_data")
    val x4361_x4918_v = Vector("x4361_x4918")
    val x4344_x4950_x4955_s = Scalar("x4344_x4950_x4955")
    val x5359_b5571_x5374_b5573_s = Scalar("x5359_b5571_x5374_b5573")
    val x4508_b5442_x4517_b5444_s = Scalar("x4508_b5442_x4517_b5444")
    val x4603_x4613_data_v = Vector("x4603_x4613_data")
    val x4672_b5470_x4681_b5472_s = Scalar("x4672_b5470_x4681_b5472")
    val x4355_x5012_x5016_v = Vector("x4355_x5012_x5016")
    val x4462_x4472_data_v = Vector("x4462_x4472_data")
    val x4390_b5422_x4399_b5424_s = Scalar("x4390_b5422_x4399_b5424")
    val x5031_b5524_x5046_b5526_s = Scalar("x5031_b5524_x5046_b5526")
    val x4359_x5176_x5180_v = Vector("x4359_x5176_x5180")
    val x4625_b5461_x4634_b5463_s = Scalar("x4625_b5461_x4634_b5463")
    val x5195_b5547_x5210_b5549_s = Scalar("x5195_b5547_x5210_b5549")
    val x4602_b5457_x4611_b5459_s = Scalar("x4602_b5457_x4611_b5459")
    val x4361_x5258_x5262_v = Vector("x4361_x5258_x5262")
    val x4338_x4878_x4883_s = Scalar("x4338_x4878_x4883")
    val x4364_x5381_x5385_v = Vector("x4364_x5381_x5385")
    val x4602_b5458_x4611_b5460_s = Scalar("x4602_b5458_x4611_b5460")
    val x4360_x5217_x5221_v = Vector("x4360_x5217_x5221")
    val x4363_x4942_v = Vector("x4363_x4942")
    val x4391_x4401_data_v = Vector("x4391_x4401_data")
    val x4625_b5462_x4634_b5464_s = Scalar("x4625_b5462_x4634_b5464")
    val x4790_b5490_x4799_b5492_s = Scalar("x4790_b5490_x4799_b5492")
    val x5072_b5530_x5087_b5532_s = Scalar("x5072_b5530_x5087_b5532")
    val x4345_x4843_x4847_v = Vector("x4345_x4843_x4847")
    val x4335_x4842_x4847_s = Scalar("x4335_x4842_x4847")
    val x4438_x4448_data_v = Vector("x4438_x4448_data")
    val x5113_b5535_x5128_b5537_s = Scalar("x5113_b5535_x5128_b5537")
    val x4766_b5485_x4775_b5487_s = Scalar("x4766_b5485_x4775_b5487")
    val x4813_b5493_x4822_b5495_s = Scalar("x4813_b5493_x4822_b5495")
    val x4509_x4519_data_v = Vector("x4509_x4519_data")
    val sizeA_argin = ArgIn("sizeA").bound(38400)
    val x4350_x4903_x4907_v = Vector("x4350_x4903_x4907")
    val x4414_b5425_x4423_b5427_s = Scalar("x4414_b5425_x4423_b5427")
    val x4336_x4854_x4859_s = Scalar("x4336_x4854_x4859")
    val x4767_x4777_data_v = Vector("x4767_x4777_data")
    val x4697_x4707_data_v = Vector("x4697_x4707_data")
    val x4531_b5446_x4540_b5448_s = Scalar("x4531_b5446_x4540_b5448")
    val vec1_oc = OffChip("vec1")
    val x4437_b5429_x4446_b5431_s = Scalar("x4437_b5429_x4446_b5431")
    val x4626_x4636_data_v = Vector("x4626_x4636_data")
    val x4360_x4906_v = Vector("x4360_x4906")
    val x4743_b5481_x4752_b5483_s = Scalar("x4743_b5481_x4752_b5483")
    val x4791_x4801_data_v = Vector("x4791_x4801_data")
    val x4650_x4660_data_v = Vector("x4650_x4660_data")
    val x4437_b5430_x4446_b5432_s = Scalar("x4437_b5430_x4446_b5432")
    val x4649_b5466_x4658_b5468_s = Scalar("x4649_b5466_x4658_b5468")
    val x5236_b5553_x5251_b5555_s = Scalar("x5236_b5553_x5251_b5555")
    val x5072_b5529_x5087_b5531_s = Scalar("x5072_b5529_x5087_b5531")
    val x5031_b5523_x5046_b5525_s = Scalar("x5031_b5523_x5046_b5525")
    val x5277_b5559_x5292_b5561_s = Scalar("x5277_b5559_x5292_b5561")
    val x4353_x4939_x4943_v = Vector("x4353_x4939_x4943")
    val x5318_b5566_x5333_b5568_s = Scalar("x5318_b5566_x5333_b5568")
    val x4719_b5477_x4728_b5479_s = Scalar("x4719_b5477_x4728_b5479")
    val x4351_x4915_x4919_v = Vector("x4351_x4915_x4919")
    val x4364_x4954_v = Vector("x4364_x4954")
    val vec2_da = DRAMAddress("vec2", "vec2")
    val x4461_b5434_x4470_b5436_s = Scalar("x4461_b5434_x4470_b5436")
    val x5154_b5542_x5169_b5544_s = Scalar("x5154_b5542_x5169_b5544")
    val out_oc = OffChip("out")
    val x4484_b5438_x4493_b5440_s = Scalar("x4484_b5438_x4493_b5440")
    val x4720_x4730_data_v = Vector("x4720_x4730_data")
    val x5400 = Sequential(name="x5400",parent=top) { implicit CU => 
      val x5400_unit = CounterChain(name = "x5400_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5399 = MetaPipeline(name="x5399",parent=x5400) { implicit CU => 
      val x4318_x4330 = ScalarBuffer().wtPort(sizeB_argin)
      val x4317_x4332 = ScalarBuffer().wtPort(sizeA_argin)
      val ctr1 = Counter(min=Const(0), max=x4317_x4332.load, step=Const(16), par=1) // Counter
      val ctr2 = Counter(min=Const(0), max=x4318_x4330.load, step=Const(16), par=10) // Counter
      val x4334 = CounterChain(name = "x4334", ctr1, ctr2).iter(576000)
    }
    val x4335_dsp0 = MemoryPipeline(name="x4335_dsp0",parent="x5399") { implicit CU => 
      val x4385_x4385 = VectorFIFO(size=1).wtPort(x4368_x4378_data_v)
      val x4380 = CounterChain.copy("x4386", "x4380")
      val x4838 = CounterChain.copy("x4847_0", "x4838")
      val x4335_x4842 = SRAM(size=16,banking = Strided(1)).wtPort(x4385_x4385.readPort).rdPort(x4335_x4842_x4847_s).rdAddr(x4838(0)).wtAddr(x4380(0))
    }
    val x4336_dsp0 = MemoryPipeline(name="x4336_dsp0",parent="x5399") { implicit CU => 
      val x4432_x4432 = VectorFIFO(size=1).wtPort(x4415_x4425_data_v)
      val x4427 = CounterChain.copy("x4433", "x4427")
      val x4850 = CounterChain.copy("x4859_0", "x4850")
      val x4336_x4854 = SRAM(size=16,banking = Strided(1)).wtPort(x4432_x4432.readPort).rdPort(x4336_x4854_x4859_s).rdAddr(x4850(0)).wtAddr(x4427(0))
    }
    val x4337_dsp0 = MemoryPipeline(name="x4337_dsp0",parent="x5399") { implicit CU => 
      val x4479_x4479 = VectorFIFO(size=1).wtPort(x4462_x4472_data_v)
      val x4474 = CounterChain.copy("x4480", "x4474")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4337_x4866 = SRAM(size=16,banking = Strided(1)).wtPort(x4479_x4479.readPort).rdPort(x4337_x4866_x4871_s).rdAddr(x4862(0)).wtAddr(x4474(0))
    }
    val x4338_dsp0 = MemoryPipeline(name="x4338_dsp0",parent="x5399") { implicit CU => 
      val x4526_x4526 = VectorFIFO(size=1).wtPort(x4509_x4519_data_v)
      val x4521 = CounterChain.copy("x4527", "x4521")
      val x4874 = CounterChain.copy("x4883_0", "x4874")
      val x4338_x4878 = SRAM(size=16,banking = Strided(1)).wtPort(x4526_x4526.readPort).rdPort(x4338_x4878_x4883_s).rdAddr(x4874(0)).wtAddr(x4521(0))
    }
    val x4339_dsp0 = MemoryPipeline(name="x4339_dsp0",parent="x5399") { implicit CU => 
      val x4573_x4573 = VectorFIFO(size=1).wtPort(x4556_x4566_data_v)
      val x4568 = CounterChain.copy("x4574", "x4568")
      val x4886 = CounterChain.copy("x4895_0", "x4886")
      val x4339_x4890 = SRAM(size=16,banking = Strided(1)).wtPort(x4573_x4573.readPort).rdPort(x4339_x4890_x4895_s).rdAddr(x4886(0)).wtAddr(x4568(0))
    }
    val x4340_dsp0 = MemoryPipeline(name="x4340_dsp0",parent="x5399") { implicit CU => 
      val x4620_x4620 = VectorFIFO(size=1).wtPort(x4603_x4613_data_v)
      val x4615 = CounterChain.copy("x4621", "x4615")
      val x4898 = CounterChain.copy("x4907_0", "x4898")
      val x4340_x4902 = SRAM(size=16,banking = Strided(1)).wtPort(x4620_x4620.readPort).rdPort(x4340_x4902_x4907_s).rdAddr(x4898(0)).wtAddr(x4615(0))
    }
    val x4341_dsp0 = MemoryPipeline(name="x4341_dsp0",parent="x5399") { implicit CU => 
      val x4667_x4667 = VectorFIFO(size=1).wtPort(x4650_x4660_data_v)
      val x4662 = CounterChain.copy("x4668", "x4662")
      val x4910 = CounterChain.copy("x4919_0", "x4910")
      val x4341_x4914 = SRAM(size=16,banking = Strided(1)).wtPort(x4667_x4667.readPort).rdPort(x4341_x4914_x4919_s).rdAddr(x4910(0)).wtAddr(x4662(0))
    }
    val x4342_dsp0 = MemoryPipeline(name="x4342_dsp0",parent="x5399") { implicit CU => 
      val x4714_x4714 = VectorFIFO(size=1).wtPort(x4697_x4707_data_v)
      val x4709 = CounterChain.copy("x4715", "x4709")
      val x4922 = CounterChain.copy("x4931_0", "x4922")
      val x4342_x4926 = SRAM(size=16,banking = Strided(1)).wtPort(x4714_x4714.readPort).rdPort(x4342_x4926_x4931_s).rdAddr(x4922(0)).wtAddr(x4709(0))
    }
    val x4343_dsp0 = MemoryPipeline(name="x4343_dsp0",parent="x5399") { implicit CU => 
      val x4761_x4761 = VectorFIFO(size=1).wtPort(x4744_x4754_data_v)
      val x4756 = CounterChain.copy("x4762", "x4756")
      val x4934 = CounterChain.copy("x4943_0", "x4934")
      val x4343_x4938 = SRAM(size=16,banking = Strided(1)).wtPort(x4761_x4761.readPort).rdPort(x4343_x4938_x4943_s).rdAddr(x4934(0)).wtAddr(x4756(0))
    }
    val x4344_dsp0 = MemoryPipeline(name="x4344_dsp0",parent="x5399") { implicit CU => 
      val x4808_x4808 = VectorFIFO(size=1).wtPort(x4791_x4801_data_v)
      val x4803 = CounterChain.copy("x4809", "x4803")
      val x4946 = CounterChain.copy("x4955_0", "x4946")
      val x4344_x4950 = SRAM(size=16,banking = Strided(1)).wtPort(x4808_x4808.readPort).rdPort(x4344_x4950_x4955_s).rdAddr(x4946(0)).wtAddr(x4803(0))
    }
    val x4345_dsp0 = MemoryPipeline(name="x4345_dsp0",parent="x5399") { implicit CU => 
      val x4408_x4408 = VectorFIFO(size=1).wtPort(x4391_x4401_data_v)
      val x4403 = CounterChain.copy("x4409", "x4403")
      val x4838 = CounterChain.copy("x4847_0", "x4838")
      val x4345_x4843 = SRAM(size=16,banking = Strided(1)).wtPort(x4408_x4408.readPort).rdPort(x4345_x4843_x4847_v).rdAddr(x4838(1)).wtAddr(x4403(0))
    }
    val x4346_dsp0 = MemoryPipeline(name="x4346_dsp0",parent="x5399") { implicit CU => 
      val x4455_x4455 = VectorFIFO(size=1).wtPort(x4438_x4448_data_v)
      val x4450 = CounterChain.copy("x4456", "x4450")
      val x4850 = CounterChain.copy("x4859_0", "x4850")
      val x4346_x4855 = SRAM(size=16,banking = Strided(1)).wtPort(x4455_x4455.readPort).rdPort(x4346_x4855_x4859_v).rdAddr(x4850(1)).wtAddr(x4450(0))
    }
    val x4347_dsp0 = MemoryPipeline(name="x4347_dsp0",parent="x5399") { implicit CU => 
      val x4502_x4502 = VectorFIFO(size=1).wtPort(x4485_x4495_data_v)
      val x4497 = CounterChain.copy("x4503", "x4497")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4347_x4867 = SRAM(size=16,banking = Strided(1)).wtPort(x4502_x4502.readPort).rdPort(x4347_x4867_x4871_v).rdAddr(x4862(1)).wtAddr(x4497(0))
    }
    val x4348_dsp0 = MemoryPipeline(name="x4348_dsp0",parent="x5399") { implicit CU => 
      val x4549_x4549 = VectorFIFO(size=1).wtPort(x4532_x4542_data_v)
      val x4544 = CounterChain.copy("x4550", "x4544")
      val x4874 = CounterChain.copy("x4883_0", "x4874")
      val x4348_x4879 = SRAM(size=16,banking = Strided(1)).wtPort(x4549_x4549.readPort).rdPort(x4348_x4879_x4883_v).rdAddr(x4874(1)).wtAddr(x4544(0))
    }
    val x4349_dsp0 = MemoryPipeline(name="x4349_dsp0",parent="x5399") { implicit CU => 
      val x4596_x4596 = VectorFIFO(size=1).wtPort(x4579_x4589_data_v)
      val x4591 = CounterChain.copy("x4597", "x4591")
      val x4886 = CounterChain.copy("x4895_0", "x4886")
      val x4349_x4891 = SRAM(size=16,banking = Strided(1)).wtPort(x4596_x4596.readPort).rdPort(x4349_x4891_x4895_v).rdAddr(x4886(1)).wtAddr(x4591(0))
    }
    val x4350_dsp0 = MemoryPipeline(name="x4350_dsp0",parent="x5399") { implicit CU => 
      val x4643_x4643 = VectorFIFO(size=1).wtPort(x4626_x4636_data_v)
      val x4638 = CounterChain.copy("x4644", "x4638")
      val x4898 = CounterChain.copy("x4907_0", "x4898")
      val x4350_x4903 = SRAM(size=16,banking = Strided(1)).wtPort(x4643_x4643.readPort).rdPort(x4350_x4903_x4907_v).rdAddr(x4898(1)).wtAddr(x4638(0))
    }
    val x4351_dsp0 = MemoryPipeline(name="x4351_dsp0",parent="x5399") { implicit CU => 
      val x4690_x4690 = VectorFIFO(size=1).wtPort(x4673_x4683_data_v)
      val x4685 = CounterChain.copy("x4691", "x4685")
      val x4910 = CounterChain.copy("x4919_0", "x4910")
      val x4351_x4915 = SRAM(size=16,banking = Strided(1)).wtPort(x4690_x4690.readPort).rdPort(x4351_x4915_x4919_v).rdAddr(x4910(1)).wtAddr(x4685(0))
    }
    val x4352_dsp0 = MemoryPipeline(name="x4352_dsp0",parent="x5399") { implicit CU => 
      val x4737_x4737 = VectorFIFO(size=1).wtPort(x4720_x4730_data_v)
      val x4732 = CounterChain.copy("x4738", "x4732")
      val x4922 = CounterChain.copy("x4931_0", "x4922")
      val x4352_x4927 = SRAM(size=16,banking = Strided(1)).wtPort(x4737_x4737.readPort).rdPort(x4352_x4927_x4931_v).rdAddr(x4922(1)).wtAddr(x4732(0))
    }
    val x4353_dsp0 = MemoryPipeline(name="x4353_dsp0",parent="x5399") { implicit CU => 
      val x4784_x4784 = VectorFIFO(size=1).wtPort(x4767_x4777_data_v)
      val x4779 = CounterChain.copy("x4785", "x4779")
      val x4934 = CounterChain.copy("x4943_0", "x4934")
      val x4353_x4939 = SRAM(size=16,banking = Strided(1)).wtPort(x4784_x4784.readPort).rdPort(x4353_x4939_x4943_v).rdAddr(x4934(1)).wtAddr(x4779(0))
    }
    val x4354_dsp0 = MemoryPipeline(name="x4354_dsp0",parent="x5399") { implicit CU => 
      val x4831_x4831 = VectorFIFO(size=1).wtPort(x4814_x4824_data_v)
      val x4826 = CounterChain.copy("x4832", "x4826")
      val x4946 = CounterChain.copy("x4955_0", "x4946")
      val x4354_x4951 = SRAM(size=16,banking = Strided(1)).wtPort(x4831_x4831.readPort).rdPort(x4354_x4951_x4955_v).rdAddr(x4946(1)).wtAddr(x4826(0))
    }
    val x4355_dsp0 = MemoryPipeline(name="x4355_dsp0",parent="x5399") { implicit CU => 
      val b5521 = CU.temp()
      val b5497 = CU.temp()
      val x4846_x4846 = VectorFIFO(size=1).wtPort(x4355_x4846_v)
      val x4838 = CounterChain.copy("x4847_0", "x4838")
      val x4989 = CounterChain.copy("x5028", "x4989")
      val x5008 = CounterChain.copy("x5016", "x5008")
      val x4355_x5012 = SRAM(size=256,banking = Strided(1)).wtPort(x4846_x4846.readPort).rdPort(x4355_x5012_x5016_v)
      WAStage(operands=List(CU.ctr(x4838(0)), Const(16)), op=FixMul, results=List(b5497))
      WAStage(operands=List(b5497, CU.ctr(x4838(1))), op=FixAdd, results=List(x4355_x5012.writeAddr))
      RAStage(operands=List(CU.ctr(x4989(0)), Const(16)), op=FixMul, results=List(b5521))
      RAStage(operands=List(b5521, CU.ctr(x5008(0))), op=FixAdd, results=List(x4355_x5012.readAddr))
    }
    val x4356_dsp0 = MemoryPipeline(name="x4356_dsp0",parent="x5399") { implicit CU => 
      val b5499 = CU.temp()
      val b5527 = CU.temp()
      val x4858_x4858 = VectorFIFO(size=1).wtPort(x4356_x4858_v)
      val x4850 = CounterChain.copy("x4859_0", "x4850")
      val x5030 = CounterChain.copy("x5069", "x5030")
      val x5049 = CounterChain.copy("x5057", "x5049")
      val x4356_x5053 = SRAM(size=256,banking = Strided(1)).wtPort(x4858_x4858.readPort).rdPort(x4356_x5053_x5057_v)
      WAStage(operands=List(CU.ctr(x4850(0)), Const(16)), op=FixMul, results=List(b5499))
      WAStage(operands=List(b5499, CU.ctr(x4850(1))), op=FixAdd, results=List(x4356_x5053.writeAddr))
      RAStage(operands=List(CU.ctr(x5030(0)), Const(16)), op=FixMul, results=List(b5527))
      RAStage(operands=List(b5527, CU.ctr(x5049(0))), op=FixAdd, results=List(x4356_x5053.readAddr))
    }
    val x4357_dsp0 = MemoryPipeline(name="x4357_dsp0",parent="x5399") { implicit CU => 
      val b5533 = CU.temp()
      val b5501 = CU.temp()
      val x4870_x4870 = VectorFIFO(size=1).wtPort(x4357_x4870_v)
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x5071 = CounterChain.copy("x5110", "x5071")
      val x5090 = CounterChain.copy("x5098", "x5090")
      val x4357_x5094 = SRAM(size=256,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4357_x5094_x5098_v)
      WAStage(operands=List(CU.ctr(x4862(0)), Const(16)), op=FixMul, results=List(b5501))
      WAStage(operands=List(b5501, CU.ctr(x4862(1))), op=FixAdd, results=List(x4357_x5094.writeAddr))
      RAStage(operands=List(CU.ctr(x5071(0)), Const(16)), op=FixMul, results=List(b5533))
      RAStage(operands=List(b5533, CU.ctr(x5090(0))), op=FixAdd, results=List(x4357_x5094.readAddr))
    }
    val x4358_dsp0 = MemoryPipeline(name="x4358_dsp0",parent="x5399") { implicit CU => 
      val b5503 = CU.temp()
      val b5539 = CU.temp()
      val x4882_x4882 = VectorFIFO(size=1).wtPort(x4358_x4882_v)
      val x4874 = CounterChain.copy("x4883_0", "x4874")
      val x5112 = CounterChain.copy("x5151", "x5112")
      val x5131 = CounterChain.copy("x5139", "x5131")
      val x4358_x5135 = SRAM(size=256,banking = Strided(1)).wtPort(x4882_x4882.readPort).rdPort(x4358_x5135_x5139_v)
      WAStage(operands=List(CU.ctr(x4874(0)), Const(16)), op=FixMul, results=List(b5503))
      WAStage(operands=List(b5503, CU.ctr(x4874(1))), op=FixAdd, results=List(x4358_x5135.writeAddr))
      RAStage(operands=List(CU.ctr(x5112(0)), Const(16)), op=FixMul, results=List(b5539))
      RAStage(operands=List(b5539, CU.ctr(x5131(0))), op=FixAdd, results=List(x4358_x5135.readAddr))
    }
    val x4359_dsp0 = MemoryPipeline(name="x4359_dsp0",parent="x5399") { implicit CU => 
      val b5505 = CU.temp()
      val b5545 = CU.temp()
      val x4894_x4894 = VectorFIFO(size=1).wtPort(x4359_x4894_v)
      val x4886 = CounterChain.copy("x4895_0", "x4886")
      val x5153 = CounterChain.copy("x5192", "x5153")
      val x5172 = CounterChain.copy("x5180", "x5172")
      val x4359_x5176 = SRAM(size=256,banking = Strided(1)).wtPort(x4894_x4894.readPort).rdPort(x4359_x5176_x5180_v)
      WAStage(operands=List(CU.ctr(x4886(0)), Const(16)), op=FixMul, results=List(b5505))
      WAStage(operands=List(b5505, CU.ctr(x4886(1))), op=FixAdd, results=List(x4359_x5176.writeAddr))
      RAStage(operands=List(CU.ctr(x5153(0)), Const(16)), op=FixMul, results=List(b5545))
      RAStage(operands=List(b5545, CU.ctr(x5172(0))), op=FixAdd, results=List(x4359_x5176.readAddr))
    }
    val x4360_dsp0 = MemoryPipeline(name="x4360_dsp0",parent="x5399") { implicit CU => 
      val b5507 = CU.temp()
      val b5551 = CU.temp()
      val x4906_x4906 = VectorFIFO(size=1).wtPort(x4360_x4906_v)
      val x4898 = CounterChain.copy("x4907_0", "x4898")
      val x5194 = CounterChain.copy("x5233", "x5194")
      val x5213 = CounterChain.copy("x5221", "x5213")
      val x4360_x5217 = SRAM(size=256,banking = Strided(1)).wtPort(x4906_x4906.readPort).rdPort(x4360_x5217_x5221_v)
      WAStage(operands=List(CU.ctr(x4898(0)), Const(16)), op=FixMul, results=List(b5507))
      WAStage(operands=List(b5507, CU.ctr(x4898(1))), op=FixAdd, results=List(x4360_x5217.writeAddr))
      RAStage(operands=List(CU.ctr(x5194(0)), Const(16)), op=FixMul, results=List(b5551))
      RAStage(operands=List(b5551, CU.ctr(x5213(0))), op=FixAdd, results=List(x4360_x5217.readAddr))
    }
    val x4361_dsp0 = MemoryPipeline(name="x4361_dsp0",parent="x5399") { implicit CU => 
      val b5557 = CU.temp()
      val b5509 = CU.temp()
      val x4918_x4918 = VectorFIFO(size=1).wtPort(x4361_x4918_v)
      val x4910 = CounterChain.copy("x4919_0", "x4910")
      val x5235 = CounterChain.copy("x5274", "x5235")
      val x5254 = CounterChain.copy("x5262", "x5254")
      val x4361_x5258 = SRAM(size=256,banking = Strided(1)).wtPort(x4918_x4918.readPort).rdPort(x4361_x5258_x5262_v)
      WAStage(operands=List(CU.ctr(x4910(0)), Const(16)), op=FixMul, results=List(b5509))
      WAStage(operands=List(b5509, CU.ctr(x4910(1))), op=FixAdd, results=List(x4361_x5258.writeAddr))
      RAStage(operands=List(CU.ctr(x5235(0)), Const(16)), op=FixMul, results=List(b5557))
      RAStage(operands=List(b5557, CU.ctr(x5254(0))), op=FixAdd, results=List(x4361_x5258.readAddr))
    }
    val x4362_dsp0 = MemoryPipeline(name="x4362_dsp0",parent="x5399") { implicit CU => 
      val b5563 = CU.temp()
      val b5511 = CU.temp()
      val x4930_x4930 = VectorFIFO(size=1).wtPort(x4362_x4930_v)
      val x4922 = CounterChain.copy("x4931_0", "x4922")
      val x5276 = CounterChain.copy("x5315", "x5276")
      val x5295 = CounterChain.copy("x5303", "x5295")
      val x4362_x5299 = SRAM(size=256,banking = Strided(1)).wtPort(x4930_x4930.readPort).rdPort(x4362_x5299_x5303_v)
      WAStage(operands=List(CU.ctr(x4922(0)), Const(16)), op=FixMul, results=List(b5511))
      WAStage(operands=List(b5511, CU.ctr(x4922(1))), op=FixAdd, results=List(x4362_x5299.writeAddr))
      RAStage(operands=List(CU.ctr(x5276(0)), Const(16)), op=FixMul, results=List(b5563))
      RAStage(operands=List(b5563, CU.ctr(x5295(0))), op=FixAdd, results=List(x4362_x5299.readAddr))
    }
    val x4363_dsp0 = MemoryPipeline(name="x4363_dsp0",parent="x5399") { implicit CU => 
      val b5569 = CU.temp()
      val b5513 = CU.temp()
      val x4942_x4942 = VectorFIFO(size=1).wtPort(x4363_x4942_v)
      val x4934 = CounterChain.copy("x4943_0", "x4934")
      val x5317 = CounterChain.copy("x5356", "x5317")
      val x5336 = CounterChain.copy("x5344", "x5336")
      val x4363_x5340 = SRAM(size=256,banking = Strided(1)).wtPort(x4942_x4942.readPort).rdPort(x4363_x5340_x5344_v)
      WAStage(operands=List(CU.ctr(x4934(0)), Const(16)), op=FixMul, results=List(b5513))
      WAStage(operands=List(b5513, CU.ctr(x4934(1))), op=FixAdd, results=List(x4363_x5340.writeAddr))
      RAStage(operands=List(CU.ctr(x5317(0)), Const(16)), op=FixMul, results=List(b5569))
      RAStage(operands=List(b5569, CU.ctr(x5336(0))), op=FixAdd, results=List(x4363_x5340.readAddr))
    }
    val x4364_dsp0 = MemoryPipeline(name="x4364_dsp0",parent="x5399") { implicit CU => 
      val b5575 = CU.temp()
      val b5515 = CU.temp()
      val x4954_x4954 = VectorFIFO(size=1).wtPort(x4364_x4954_v)
      val x4946 = CounterChain.copy("x4955_0", "x4946")
      val x5358 = CounterChain.copy("x5397", "x5358")
      val x5377 = CounterChain.copy("x5385", "x5377")
      val x4364_x5381 = SRAM(size=256,banking = Strided(1)).wtPort(x4954_x4954.readPort).rdPort(x4364_x5381_x5385_v)
      WAStage(operands=List(CU.ctr(x4946(0)), Const(16)), op=FixMul, results=List(b5515))
      WAStage(operands=List(b5515, CU.ctr(x4946(1))), op=FixAdd, results=List(x4364_x5381.writeAddr))
      RAStage(operands=List(CU.ctr(x5358(0)), Const(16)), op=FixMul, results=List(b5575))
      RAStage(operands=List(b5575, CU.ctr(x5377(0))), op=FixAdd, results=List(x4364_x5381.readAddr))
    }
    val x4387 = StreamController(name="x4387",parent=x5399) { implicit CU => 
      val x4387_unit = CounterChain(name = "x4387_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4377_0 = Pipeline(name="x4377_0",parent=x4387) { implicit CU => 
      val x4369 = CU.temp()
      val x4371 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4377_unit = CounterChain(name = "x4377_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4369))
      Stage(operands=List(x4369, CU.load(x4371)), op=FixAdd, results=List(CU.scalarOut(x4367_b5417_x4376_b5419_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4367_b5418_x4376_b5420_s)))
    }
    val x4378 = MemoryController(name="x4378",parent=x4387,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4367_b5418_x4378 = ScalarFIFO(name="size",size=1).wtPort(x4367_b5418_x4376_b5420_s)
      val x4367_b5417_x4378 = ScalarFIFO(name="offset",size=1).wtPort(x4367_b5417_x4376_b5419_s)
      CU.newVout("data", x4368_x4378_data_v)
    }
    val x4386 = Pipeline(name="x4386",parent=x4387) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4380 = CounterChain(name = "x4380", ctr3).iter(1)
    }
    val x4410 = StreamController(name="x4410",parent=x5399) { implicit CU => 
      val x4410_unit = CounterChain(name = "x4410_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4400_0 = Pipeline(name="x4400_0",parent=x4410) { implicit CU => 
      val x4392 = CU.temp()
      val x4394 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4400_unit = CounterChain(name = "x4400_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4392))
      Stage(operands=List(x4392, CU.load(x4394)), op=FixAdd, results=List(CU.scalarOut(x4390_b5421_x4399_b5423_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4390_b5422_x4399_b5424_s)))
    }
    val x4401 = MemoryController(name="x4401",parent=x4410,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4390_b5421_x4401 = ScalarFIFO(name="offset",size=1).wtPort(x4390_b5421_x4399_b5423_s)
      val x4390_b5422_x4401 = ScalarFIFO(name="size",size=1).wtPort(x4390_b5422_x4399_b5424_s)
      CU.newVout("data", x4391_x4401_data_v)
    }
    val x4409 = Pipeline(name="x4409",parent=x4410) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4403 = CounterChain(name = "x4403", ctr4).iter(1)
    }
    val x4434 = StreamController(name="x4434",parent=x5399) { implicit CU => 
      val x4434_unit = CounterChain(name = "x4434_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4424_0 = Pipeline(name="x4424_0",parent=x4434) { implicit CU => 
      val x4416 = CU.temp()
      val x4418 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4424_unit = CounterChain(name = "x4424_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4416))
      Stage(operands=List(x4416, CU.load(x4418)), op=FixAdd, results=List(CU.scalarOut(x4414_b5425_x4423_b5427_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4414_b5426_x4423_b5428_s)))
    }
    val x4425 = MemoryController(name="x4425",parent=x4434,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4414_b5426_x4425 = ScalarFIFO(name="size",size=1).wtPort(x4414_b5426_x4423_b5428_s)
      val x4414_b5425_x4425 = ScalarFIFO(name="offset",size=1).wtPort(x4414_b5425_x4423_b5427_s)
      CU.newVout("data", x4415_x4425_data_v)
    }
    val x4433 = Pipeline(name="x4433",parent=x4434) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4427 = CounterChain(name = "x4427", ctr5).iter(1)
    }
    val x4457 = StreamController(name="x4457",parent=x5399) { implicit CU => 
      val x4457_unit = CounterChain(name = "x4457_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4447_0 = Pipeline(name="x4447_0",parent=x4457) { implicit CU => 
      val x4439 = CU.temp()
      val x4441 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4447_unit = CounterChain(name = "x4447_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4439))
      Stage(operands=List(x4439, CU.load(x4441)), op=FixAdd, results=List(CU.scalarOut(x4437_b5429_x4446_b5431_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4437_b5430_x4446_b5432_s)))
    }
    val x4448 = MemoryController(name="x4448",parent=x4457,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4437_b5430_x4448 = ScalarFIFO(name="size",size=1).wtPort(x4437_b5430_x4446_b5432_s)
      val x4437_b5429_x4448 = ScalarFIFO(name="offset",size=1).wtPort(x4437_b5429_x4446_b5431_s)
      CU.newVout("data", x4438_x4448_data_v)
    }
    val x4456 = Pipeline(name="x4456",parent=x4457) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4450 = CounterChain(name = "x4450", ctr6).iter(1)
    }
    val x4481 = StreamController(name="x4481",parent=x5399) { implicit CU => 
      val x4481_unit = CounterChain(name = "x4481_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4471_0 = Pipeline(name="x4471_0",parent=x4481) { implicit CU => 
      val x4463 = CU.temp()
      val x4465 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4471_unit = CounterChain(name = "x4471_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4463))
      Stage(operands=List(x4463, CU.load(x4465)), op=FixAdd, results=List(CU.scalarOut(x4461_b5433_x4470_b5435_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4461_b5434_x4470_b5436_s)))
    }
    val x4472 = MemoryController(name="x4472",parent=x4481,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4461_b5433_x4472 = ScalarFIFO(name="offset",size=1).wtPort(x4461_b5433_x4470_b5435_s)
      val x4461_b5434_x4472 = ScalarFIFO(name="size",size=1).wtPort(x4461_b5434_x4470_b5436_s)
      CU.newVout("data", x4462_x4472_data_v)
    }
    val x4480 = Pipeline(name="x4480",parent=x4481) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4474 = CounterChain(name = "x4474", ctr7).iter(1)
    }
    val x4504 = StreamController(name="x4504",parent=x5399) { implicit CU => 
      val x4504_unit = CounterChain(name = "x4504_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4494_0 = Pipeline(name="x4494_0",parent=x4504) { implicit CU => 
      val x4486 = CU.temp()
      val x4488 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4494_unit = CounterChain(name = "x4494_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4486))
      Stage(operands=List(x4486, CU.load(x4488)), op=FixAdd, results=List(CU.scalarOut(x4484_b5437_x4493_b5439_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4484_b5438_x4493_b5440_s)))
    }
    val x4495 = MemoryController(name="x4495",parent=x4504,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4484_b5438_x4495 = ScalarFIFO(name="size",size=1).wtPort(x4484_b5438_x4493_b5440_s)
      val x4484_b5437_x4495 = ScalarFIFO(name="offset",size=1).wtPort(x4484_b5437_x4493_b5439_s)
      CU.newVout("data", x4485_x4495_data_v)
    }
    val x4503 = Pipeline(name="x4503",parent=x4504) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4497 = CounterChain(name = "x4497", ctr8).iter(1)
    }
    val x4528 = StreamController(name="x4528",parent=x5399) { implicit CU => 
      val x4528_unit = CounterChain(name = "x4528_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4518_0 = Pipeline(name="x4518_0",parent=x4528) { implicit CU => 
      val x4510 = CU.temp()
      val x4512 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4518_unit = CounterChain(name = "x4518_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4510))
      Stage(operands=List(x4510, CU.load(x4512)), op=FixAdd, results=List(CU.scalarOut(x4508_b5441_x4517_b5443_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4508_b5442_x4517_b5444_s)))
    }
    val x4519 = MemoryController(name="x4519",parent=x4528,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4508_b5442_x4519 = ScalarFIFO(name="size",size=1).wtPort(x4508_b5442_x4517_b5444_s)
      val x4508_b5441_x4519 = ScalarFIFO(name="offset",size=1).wtPort(x4508_b5441_x4517_b5443_s)
      CU.newVout("data", x4509_x4519_data_v)
    }
    val x4527 = Pipeline(name="x4527",parent=x4528) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4521 = CounterChain(name = "x4521", ctr9).iter(1)
    }
    val x4551 = StreamController(name="x4551",parent=x5399) { implicit CU => 
      val x4551_unit = CounterChain(name = "x4551_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4541_0 = Pipeline(name="x4541_0",parent=x4551) { implicit CU => 
      val x4533 = CU.temp()
      val x4535 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4541_unit = CounterChain(name = "x4541_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4533))
      Stage(operands=List(x4533, CU.load(x4535)), op=FixAdd, results=List(CU.scalarOut(x4531_b5445_x4540_b5447_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4531_b5446_x4540_b5448_s)))
    }
    val x4542 = MemoryController(name="x4542",parent=x4551,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4531_b5445_x4542 = ScalarFIFO(name="offset",size=1).wtPort(x4531_b5445_x4540_b5447_s)
      val x4531_b5446_x4542 = ScalarFIFO(name="size",size=1).wtPort(x4531_b5446_x4540_b5448_s)
      CU.newVout("data", x4532_x4542_data_v)
    }
    val x4550 = Pipeline(name="x4550",parent=x4551) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4544 = CounterChain(name = "x4544", ctr10).iter(1)
    }
    val x4575 = StreamController(name="x4575",parent=x5399) { implicit CU => 
      val x4575_unit = CounterChain(name = "x4575_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4565_0 = Pipeline(name="x4565_0",parent=x4575) { implicit CU => 
      val x4557 = CU.temp()
      val x4559 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4565_unit = CounterChain(name = "x4565_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4557))
      Stage(operands=List(x4557, CU.load(x4559)), op=FixAdd, results=List(CU.scalarOut(x4555_b5449_x4564_b5451_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4555_b5450_x4564_b5452_s)))
    }
    val x4566 = MemoryController(name="x4566",parent=x4575,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4555_b5450_x4566 = ScalarFIFO(name="size",size=1).wtPort(x4555_b5450_x4564_b5452_s)
      val x4555_b5449_x4566 = ScalarFIFO(name="offset",size=1).wtPort(x4555_b5449_x4564_b5451_s)
      CU.newVout("data", x4556_x4566_data_v)
    }
    val x4574 = Pipeline(name="x4574",parent=x4575) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4568 = CounterChain(name = "x4568", ctr11).iter(1)
    }
    val x4598 = StreamController(name="x4598",parent=x5399) { implicit CU => 
      val x4598_unit = CounterChain(name = "x4598_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4588_0 = Pipeline(name="x4588_0",parent=x4598) { implicit CU => 
      val x4580 = CU.temp()
      val x4582 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4588_unit = CounterChain(name = "x4588_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4580))
      Stage(operands=List(x4580, CU.load(x4582)), op=FixAdd, results=List(CU.scalarOut(x4578_b5453_x4587_b5455_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4578_b5454_x4587_b5456_s)))
    }
    val x4589 = MemoryController(name="x4589",parent=x4598,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4578_b5454_x4589 = ScalarFIFO(name="size",size=1).wtPort(x4578_b5454_x4587_b5456_s)
      val x4578_b5453_x4589 = ScalarFIFO(name="offset",size=1).wtPort(x4578_b5453_x4587_b5455_s)
      CU.newVout("data", x4579_x4589_data_v)
    }
    val x4597 = Pipeline(name="x4597",parent=x4598) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4591 = CounterChain(name = "x4591", ctr12).iter(1)
    }
    val x4622 = StreamController(name="x4622",parent=x5399) { implicit CU => 
      val x4622_unit = CounterChain(name = "x4622_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4612_0 = Pipeline(name="x4612_0",parent=x4622) { implicit CU => 
      val x4604 = CU.temp()
      val x4606 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4612_unit = CounterChain(name = "x4612_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4604))
      Stage(operands=List(x4604, CU.load(x4606)), op=FixAdd, results=List(CU.scalarOut(x4602_b5457_x4611_b5459_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4602_b5458_x4611_b5460_s)))
    }
    val x4613 = MemoryController(name="x4613",parent=x4622,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4602_b5457_x4613 = ScalarFIFO(name="offset",size=1).wtPort(x4602_b5457_x4611_b5459_s)
      val x4602_b5458_x4613 = ScalarFIFO(name="size",size=1).wtPort(x4602_b5458_x4611_b5460_s)
      CU.newVout("data", x4603_x4613_data_v)
    }
    val x4621 = Pipeline(name="x4621",parent=x4622) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4615 = CounterChain(name = "x4615", ctr13).iter(1)
    }
    val x4645 = StreamController(name="x4645",parent=x5399) { implicit CU => 
      val x4645_unit = CounterChain(name = "x4645_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4635_0 = Pipeline(name="x4635_0",parent=x4645) { implicit CU => 
      val x4627 = CU.temp()
      val x4629 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4635_unit = CounterChain(name = "x4635_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4627))
      Stage(operands=List(x4627, CU.load(x4629)), op=FixAdd, results=List(CU.scalarOut(x4625_b5461_x4634_b5463_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4625_b5462_x4634_b5464_s)))
    }
    val x4636 = MemoryController(name="x4636",parent=x4645,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4625_b5462_x4636 = ScalarFIFO(name="size",size=1).wtPort(x4625_b5462_x4634_b5464_s)
      val x4625_b5461_x4636 = ScalarFIFO(name="offset",size=1).wtPort(x4625_b5461_x4634_b5463_s)
      CU.newVout("data", x4626_x4636_data_v)
    }
    val x4644 = Pipeline(name="x4644",parent=x4645) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4638 = CounterChain(name = "x4638", ctr14).iter(1)
    }
    val x4669 = StreamController(name="x4669",parent=x5399) { implicit CU => 
      val x4669_unit = CounterChain(name = "x4669_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4659_0 = Pipeline(name="x4659_0",parent=x4669) { implicit CU => 
      val x4651 = CU.temp()
      val x4653 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4659_unit = CounterChain(name = "x4659_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4651))
      Stage(operands=List(x4651, CU.load(x4653)), op=FixAdd, results=List(CU.scalarOut(x4649_b5465_x4658_b5467_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4649_b5466_x4658_b5468_s)))
    }
    val x4660 = MemoryController(name="x4660",parent=x4669,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4649_b5466_x4660 = ScalarFIFO(name="size",size=1).wtPort(x4649_b5466_x4658_b5468_s)
      val x4649_b5465_x4660 = ScalarFIFO(name="offset",size=1).wtPort(x4649_b5465_x4658_b5467_s)
      CU.newVout("data", x4650_x4660_data_v)
    }
    val x4668 = Pipeline(name="x4668",parent=x4669) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4662 = CounterChain(name = "x4662", ctr15).iter(1)
    }
    val x4692 = StreamController(name="x4692",parent=x5399) { implicit CU => 
      val x4692_unit = CounterChain(name = "x4692_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4682_0 = Pipeline(name="x4682_0",parent=x4692) { implicit CU => 
      val x4674 = CU.temp()
      val x4676 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4682_unit = CounterChain(name = "x4682_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4674))
      Stage(operands=List(x4674, CU.load(x4676)), op=FixAdd, results=List(CU.scalarOut(x4672_b5469_x4681_b5471_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4672_b5470_x4681_b5472_s)))
    }
    val x4683 = MemoryController(name="x4683",parent=x4692,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4672_b5469_x4683 = ScalarFIFO(name="offset",size=1).wtPort(x4672_b5469_x4681_b5471_s)
      val x4672_b5470_x4683 = ScalarFIFO(name="size",size=1).wtPort(x4672_b5470_x4681_b5472_s)
      CU.newVout("data", x4673_x4683_data_v)
    }
    val x4691 = Pipeline(name="x4691",parent=x4692) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4685 = CounterChain(name = "x4685", ctr16).iter(1)
    }
    val x4716 = StreamController(name="x4716",parent=x5399) { implicit CU => 
      val x4716_unit = CounterChain(name = "x4716_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4706_0 = Pipeline(name="x4706_0",parent=x4716) { implicit CU => 
      val x4698 = CU.temp()
      val x4700 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4706_unit = CounterChain(name = "x4706_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4698))
      Stage(operands=List(x4698, CU.load(x4700)), op=FixAdd, results=List(CU.scalarOut(x4696_b5473_x4705_b5475_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4696_b5474_x4705_b5476_s)))
    }
    val x4707 = MemoryController(name="x4707",parent=x4716,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4696_b5474_x4707 = ScalarFIFO(name="size",size=1).wtPort(x4696_b5474_x4705_b5476_s)
      val x4696_b5473_x4707 = ScalarFIFO(name="offset",size=1).wtPort(x4696_b5473_x4705_b5475_s)
      CU.newVout("data", x4697_x4707_data_v)
    }
    val x4715 = Pipeline(name="x4715",parent=x4716) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4709 = CounterChain(name = "x4709", ctr17).iter(1)
    }
    val x4739 = StreamController(name="x4739",parent=x5399) { implicit CU => 
      val x4739_unit = CounterChain(name = "x4739_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4729_0 = Pipeline(name="x4729_0",parent=x4739) { implicit CU => 
      val x4721 = CU.temp()
      val x4723 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4729_unit = CounterChain(name = "x4729_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4721))
      Stage(operands=List(x4721, CU.load(x4723)), op=FixAdd, results=List(CU.scalarOut(x4719_b5477_x4728_b5479_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4719_b5478_x4728_b5480_s)))
    }
    val x4730 = MemoryController(name="x4730",parent=x4739,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4719_b5478_x4730 = ScalarFIFO(name="size",size=1).wtPort(x4719_b5478_x4728_b5480_s)
      val x4719_b5477_x4730 = ScalarFIFO(name="offset",size=1).wtPort(x4719_b5477_x4728_b5479_s)
      CU.newVout("data", x4720_x4730_data_v)
    }
    val x4738 = Pipeline(name="x4738",parent=x4739) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4732 = CounterChain(name = "x4732", ctr18).iter(1)
    }
    val x4763 = StreamController(name="x4763",parent=x5399) { implicit CU => 
      val x4763_unit = CounterChain(name = "x4763_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4753_0 = Pipeline(name="x4753_0",parent=x4763) { implicit CU => 
      val x4745 = CU.temp()
      val x4747 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4753_unit = CounterChain(name = "x4753_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4745))
      Stage(operands=List(x4745, CU.load(x4747)), op=FixAdd, results=List(CU.scalarOut(x4743_b5481_x4752_b5483_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4743_b5482_x4752_b5484_s)))
    }
    val x4754 = MemoryController(name="x4754",parent=x4763,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4743_b5481_x4754 = ScalarFIFO(name="offset",size=1).wtPort(x4743_b5481_x4752_b5483_s)
      val x4743_b5482_x4754 = ScalarFIFO(name="size",size=1).wtPort(x4743_b5482_x4752_b5484_s)
      CU.newVout("data", x4744_x4754_data_v)
    }
    val x4762 = Pipeline(name="x4762",parent=x4763) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4756 = CounterChain(name = "x4756", ctr19).iter(1)
    }
    val x4786 = StreamController(name="x4786",parent=x5399) { implicit CU => 
      val x4786_unit = CounterChain(name = "x4786_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4776_0 = Pipeline(name="x4776_0",parent=x4786) { implicit CU => 
      val x4768 = CU.temp()
      val x4770 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4776_unit = CounterChain(name = "x4776_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4768))
      Stage(operands=List(x4768, CU.load(x4770)), op=FixAdd, results=List(CU.scalarOut(x4766_b5485_x4775_b5487_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4766_b5486_x4775_b5488_s)))
    }
    val x4777 = MemoryController(name="x4777",parent=x4786,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4766_b5486_x4777 = ScalarFIFO(name="size",size=1).wtPort(x4766_b5486_x4775_b5488_s)
      val x4766_b5485_x4777 = ScalarFIFO(name="offset",size=1).wtPort(x4766_b5485_x4775_b5487_s)
      CU.newVout("data", x4767_x4777_data_v)
    }
    val x4785 = Pipeline(name="x4785",parent=x4786) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4779 = CounterChain(name = "x4779", ctr20).iter(1)
    }
    val x4810 = StreamController(name="x4810",parent=x5399) { implicit CU => 
      val x4810_unit = CounterChain(name = "x4810_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4800_0 = Pipeline(name="x4800_0",parent=x4810) { implicit CU => 
      val x4792 = CU.temp()
      val x4794 = ScalarBuffer().wtPort(vec1_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4800_unit = CounterChain(name = "x4800_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), Const(4)), op=FixMul, results=List(x4792))
      Stage(operands=List(x4792, CU.load(x4794)), op=FixAdd, results=List(CU.scalarOut(x4790_b5489_x4799_b5491_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4790_b5490_x4799_b5492_s)))
    }
    val x4801 = MemoryController(name="x4801",parent=x4810,offchip=vec1_oc, mctpe=TileLoad) { implicit CU => 
      val x4790_b5490_x4801 = ScalarFIFO(name="size",size=1).wtPort(x4790_b5490_x4799_b5492_s)
      val x4790_b5489_x4801 = ScalarFIFO(name="offset",size=1).wtPort(x4790_b5489_x4799_b5491_s)
      CU.newVout("data", x4791_x4801_data_v)
    }
    val x4809 = Pipeline(name="x4809",parent=x4810) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4803 = CounterChain(name = "x4803", ctr21).iter(1)
    }
    val x4833 = StreamController(name="x4833",parent=x5399) { implicit CU => 
      val x4833_unit = CounterChain(name = "x4833_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4823_0 = Pipeline(name="x4823_0",parent=x4833) { implicit CU => 
      val x4815 = CU.temp()
      val x4817 = ScalarBuffer().wtPort(vec2_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4823_unit = CounterChain(name = "x4823_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(1)), Const(4)), op=FixMul, results=List(x4815))
      Stage(operands=List(x4815, CU.load(x4817)), op=FixAdd, results=List(CU.scalarOut(x4813_b5493_x4822_b5495_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4813_b5494_x4822_b5496_s)))
    }
    val x4824 = MemoryController(name="x4824",parent=x4833,offchip=vec2_oc, mctpe=TileLoad) { implicit CU => 
      val x4813_b5493_x4824 = ScalarFIFO(name="offset",size=1).wtPort(x4813_b5493_x4822_b5495_s)
      val x4813_b5494_x4824 = ScalarFIFO(name="size",size=1).wtPort(x4813_b5494_x4822_b5496_s)
      CU.newVout("data", x4814_x4824_data_v)
    }
    val x4832 = Pipeline(name="x4832",parent=x4833) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4826 = CounterChain(name = "x4826", ctr22).iter(1)
    }
    val x4847_0 = Pipeline(name="x4847_0",parent=x5399) { implicit CU => 
      val x4843_x4843 = VectorFIFO(size=1).wtPort(x4345_x4843_x4847_v)
      val x4842_x4842 = ScalarFIFO(size=1).wtPort(x4335_x4842_x4847_s)
      val ctr23 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr24 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4838 = CounterChain(name = "x4838", ctr23, ctr24).iter(16)
      Stage(operands=List(CU.load(x4842_x4842), CU.load(x4843_x4843)), op=FixMul, results=List(CU.vecOut(x4355_x4846_v)))
    }
    val x4859_0 = Pipeline(name="x4859_0",parent=x5399) { implicit CU => 
      val x4855_x4855 = VectorFIFO(size=1).wtPort(x4346_x4855_x4859_v)
      val x4854_x4854 = ScalarFIFO(size=1).wtPort(x4336_x4854_x4859_s)
      val ctr25 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr26 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4850 = CounterChain(name = "x4850", ctr25, ctr26).iter(16)
      Stage(operands=List(CU.load(x4854_x4854), CU.load(x4855_x4855)), op=FixMul, results=List(CU.vecOut(x4356_x4858_v)))
    }
    val x4871_0 = Pipeline(name="x4871_0",parent=x5399) { implicit CU => 
      val x4867_x4867 = VectorFIFO(size=1).wtPort(x4347_x4867_x4871_v)
      val x4866_x4866 = ScalarFIFO(size=1).wtPort(x4337_x4866_x4871_s)
      val ctr27 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr28 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4862 = CounterChain(name = "x4862", ctr27, ctr28).iter(16)
      Stage(operands=List(CU.load(x4866_x4866), CU.load(x4867_x4867)), op=FixMul, results=List(CU.vecOut(x4357_x4870_v)))
    }
    val x4883_0 = Pipeline(name="x4883_0",parent=x5399) { implicit CU => 
      val x4879_x4879 = VectorFIFO(size=1).wtPort(x4348_x4879_x4883_v)
      val x4878_x4878 = ScalarFIFO(size=1).wtPort(x4338_x4878_x4883_s)
      val ctr29 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr30 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4874 = CounterChain(name = "x4874", ctr29, ctr30).iter(16)
      Stage(operands=List(CU.load(x4878_x4878), CU.load(x4879_x4879)), op=FixMul, results=List(CU.vecOut(x4358_x4882_v)))
    }
    val x4895_0 = Pipeline(name="x4895_0",parent=x5399) { implicit CU => 
      val x4891_x4891 = VectorFIFO(size=1).wtPort(x4349_x4891_x4895_v)
      val x4890_x4890 = ScalarFIFO(size=1).wtPort(x4339_x4890_x4895_s)
      val ctr31 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr32 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4886 = CounterChain(name = "x4886", ctr31, ctr32).iter(16)
      Stage(operands=List(CU.load(x4890_x4890), CU.load(x4891_x4891)), op=FixMul, results=List(CU.vecOut(x4359_x4894_v)))
    }
    val x4907_0 = Pipeline(name="x4907_0",parent=x5399) { implicit CU => 
      val x4903_x4903 = VectorFIFO(size=1).wtPort(x4350_x4903_x4907_v)
      val x4902_x4902 = ScalarFIFO(size=1).wtPort(x4340_x4902_x4907_s)
      val ctr33 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr34 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4898 = CounterChain(name = "x4898", ctr33, ctr34).iter(16)
      Stage(operands=List(CU.load(x4902_x4902), CU.load(x4903_x4903)), op=FixMul, results=List(CU.vecOut(x4360_x4906_v)))
    }
    val x4919_0 = Pipeline(name="x4919_0",parent=x5399) { implicit CU => 
      val x4915_x4915 = VectorFIFO(size=1).wtPort(x4351_x4915_x4919_v)
      val x4914_x4914 = ScalarFIFO(size=1).wtPort(x4341_x4914_x4919_s)
      val ctr35 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr36 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4910 = CounterChain(name = "x4910", ctr35, ctr36).iter(16)
      Stage(operands=List(CU.load(x4914_x4914), CU.load(x4915_x4915)), op=FixMul, results=List(CU.vecOut(x4361_x4918_v)))
    }
    val x4931_0 = Pipeline(name="x4931_0",parent=x5399) { implicit CU => 
      val x4927_x4927 = VectorFIFO(size=1).wtPort(x4352_x4927_x4931_v)
      val x4926_x4926 = ScalarFIFO(size=1).wtPort(x4342_x4926_x4931_s)
      val ctr37 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr38 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4922 = CounterChain(name = "x4922", ctr37, ctr38).iter(16)
      Stage(operands=List(CU.load(x4926_x4926), CU.load(x4927_x4927)), op=FixMul, results=List(CU.vecOut(x4362_x4930_v)))
    }
    val x4943_0 = Pipeline(name="x4943_0",parent=x5399) { implicit CU => 
      val x4939_x4939 = VectorFIFO(size=1).wtPort(x4353_x4939_x4943_v)
      val x4938_x4938 = ScalarFIFO(size=1).wtPort(x4343_x4938_x4943_s)
      val ctr39 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr40 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4934 = CounterChain(name = "x4934", ctr39, ctr40).iter(16)
      Stage(operands=List(CU.load(x4938_x4938), CU.load(x4939_x4939)), op=FixMul, results=List(CU.vecOut(x4363_x4942_v)))
    }
    val x4955_0 = Pipeline(name="x4955_0",parent=x5399) { implicit CU => 
      val x4951_x4951 = VectorFIFO(size=1).wtPort(x4354_x4951_x4955_v)
      val x4950_x4950 = ScalarFIFO(size=1).wtPort(x4344_x4950_x4955_s)
      val ctr41 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr42 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4946 = CounterChain(name = "x4946", ctr41, ctr42).iter(16)
      Stage(operands=List(CU.load(x4950_x4950), CU.load(x4951_x4951)), op=FixMul, results=List(CU.vecOut(x4364_x4954_v)))
    }
    val x5028 = StreamController(name="x5028",parent=x5399) { implicit CU => 
      val ctr43 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4989 = CounterChain(name = "x4989", ctr43).iter(16)
    }
    val x5017 = Sequential(name="x5017",parent=x5028) { implicit CU => 
      val x5017_unit = CounterChain(name = "x5017_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5006_0 = Pipeline(name="x5006_0",parent=x5017) { implicit CU => 
      val x4996 = CU.temp()
      val x4997 = CU.temp()
      val x4995 = CU.temp()
      val x4993 = CU.temp()
      val x4318_x4994 = ScalarBuffer().wtPort(sizeB_argin)
      val x4999 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x4989 = CounterChain.copy("x5028", "x4989")
      val x5006_unit = CounterChain(name = "x5006_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x4989(0))), op=FixAdd, results=List(x4993))
      Stage(operands=List(x4993, CU.load(x4318_x4994)), op=FixMul, results=List(x4995))
      Stage(operands=List(x4995, CU.ctr(x4334(1))), op=FixAdd, results=List(x4996))
      Stage(operands=List(x4996, Const(4)), op=FixMul, results=List(x4997))
      Stage(operands=List(x4997, CU.load(x4999)), op=FixAdd, results=List(CU.scalarOut(x4990_b5517_x5005_b5519_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4990_b5518_x5005_b5520_s)))
    }
    val x5016 = Pipeline(name="x5016",parent=x5017) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5008 = CounterChain(name = "x5008", ctr44).iter(1)
    }
    val x5018 = MemoryController(name="x5018",parent=x5028,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x4991_x5018 = VectorFIFO(name="data",size=1).wtPort(x4355_x5012_x5016_v)
      val x4990_b5517_x5018 = ScalarFIFO(name="offset",size=1).wtPort(x4990_b5517_x5005_b5519_s)
      val x4990_b5518_x5018 = ScalarFIFO(name="size",size=1).wtPort(x4990_b5518_x5005_b5520_s)
    }
    val x5069 = StreamController(name="x5069",parent=x5399) { implicit CU => 
      val ctr46 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5030 = CounterChain(name = "x5030", ctr46).iter(16)
    }
    val x5058 = Sequential(name="x5058",parent=x5069) { implicit CU => 
      val x5058_unit = CounterChain(name = "x5058_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5047_0 = Pipeline(name="x5047_0",parent=x5058) { implicit CU => 
      val x5037 = CU.temp()
      val x5036 = CU.temp()
      val x5038 = CU.temp()
      val x5034 = CU.temp()
      val x4318_x5035 = ScalarBuffer().wtPort(sizeB_argin)
      val x5040 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5030 = CounterChain.copy("x5069", "x5030")
      val x5047_unit = CounterChain(name = "x5047_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5030(0))), op=FixAdd, results=List(x5034))
      Stage(operands=List(x5034, CU.load(x4318_x5035)), op=FixMul, results=List(x5036))
      Stage(operands=List(x5036, CU.ctr(x4334(1))), op=FixAdd, results=List(x5037))
      Stage(operands=List(x5037, Const(4)), op=FixMul, results=List(x5038))
      Stage(operands=List(x5038, CU.load(x5040)), op=FixAdd, results=List(CU.scalarOut(x5031_b5523_x5046_b5525_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5031_b5524_x5046_b5526_s)))
    }
    val x5057 = Pipeline(name="x5057",parent=x5058) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5049 = CounterChain(name = "x5049", ctr47).iter(1)
    }
    val x5059 = MemoryController(name="x5059",parent=x5069,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5031_b5523_x5059 = ScalarFIFO(name="offset",size=1).wtPort(x5031_b5523_x5046_b5525_s)
      val x5032_x5059 = VectorFIFO(name="data",size=1).wtPort(x4356_x5053_x5057_v)
      val x5031_b5524_x5059 = ScalarFIFO(name="size",size=1).wtPort(x5031_b5524_x5046_b5526_s)
    }
    val x5110 = StreamController(name="x5110",parent=x5399) { implicit CU => 
      val ctr49 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5071 = CounterChain(name = "x5071", ctr49).iter(16)
    }
    val x5099 = Sequential(name="x5099",parent=x5110) { implicit CU => 
      val x5099_unit = CounterChain(name = "x5099_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5088_0 = Pipeline(name="x5088_0",parent=x5099) { implicit CU => 
      val x5077 = CU.temp()
      val x5078 = CU.temp()
      val x5075 = CU.temp()
      val x5079 = CU.temp()
      val x4318_x5076 = ScalarBuffer().wtPort(sizeB_argin)
      val x5081 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5071 = CounterChain.copy("x5110", "x5071")
      val x5088_unit = CounterChain(name = "x5088_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5071(0))), op=FixAdd, results=List(x5075))
      Stage(operands=List(x5075, CU.load(x4318_x5076)), op=FixMul, results=List(x5077))
      Stage(operands=List(x5077, CU.ctr(x4334(1))), op=FixAdd, results=List(x5078))
      Stage(operands=List(x5078, Const(4)), op=FixMul, results=List(x5079))
      Stage(operands=List(x5079, CU.load(x5081)), op=FixAdd, results=List(CU.scalarOut(x5072_b5529_x5087_b5531_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5072_b5530_x5087_b5532_s)))
    }
    val x5098 = Pipeline(name="x5098",parent=x5099) { implicit CU => 
      val ctr50 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5090 = CounterChain(name = "x5090", ctr50).iter(1)
    }
    val x5100 = MemoryController(name="x5100",parent=x5110,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5072_b5529_x5100 = ScalarFIFO(name="offset",size=1).wtPort(x5072_b5529_x5087_b5531_s)
      val x5073_x5100 = VectorFIFO(name="data",size=1).wtPort(x4357_x5094_x5098_v)
      val x5072_b5530_x5100 = ScalarFIFO(name="size",size=1).wtPort(x5072_b5530_x5087_b5532_s)
    }
    val x5151 = StreamController(name="x5151",parent=x5399) { implicit CU => 
      val ctr52 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5112 = CounterChain(name = "x5112", ctr52).iter(16)
    }
    val x5140 = Sequential(name="x5140",parent=x5151) { implicit CU => 
      val x5140_unit = CounterChain(name = "x5140_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5129_0 = Pipeline(name="x5129_0",parent=x5140) { implicit CU => 
      val x5119 = CU.temp()
      val x5120 = CU.temp()
      val x5118 = CU.temp()
      val x5116 = CU.temp()
      val x4318_x5117 = ScalarBuffer().wtPort(sizeB_argin)
      val x5122 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5112 = CounterChain.copy("x5151", "x5112")
      val x5129_unit = CounterChain(name = "x5129_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5112(0))), op=FixAdd, results=List(x5116))
      Stage(operands=List(x5116, CU.load(x4318_x5117)), op=FixMul, results=List(x5118))
      Stage(operands=List(x5118, CU.ctr(x4334(1))), op=FixAdd, results=List(x5119))
      Stage(operands=List(x5119, Const(4)), op=FixMul, results=List(x5120))
      Stage(operands=List(x5120, CU.load(x5122)), op=FixAdd, results=List(CU.scalarOut(x5113_b5535_x5128_b5537_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5113_b5536_x5128_b5538_s)))
    }
    val x5139 = Pipeline(name="x5139",parent=x5140) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5131 = CounterChain(name = "x5131", ctr53).iter(1)
    }
    val x5141 = MemoryController(name="x5141",parent=x5151,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5113_b5535_x5141 = ScalarFIFO(name="offset",size=1).wtPort(x5113_b5535_x5128_b5537_s)
      val x5113_b5536_x5141 = ScalarFIFO(name="size",size=1).wtPort(x5113_b5536_x5128_b5538_s)
      val x5114_x5141 = VectorFIFO(name="data",size=1).wtPort(x4358_x5135_x5139_v)
    }
    val x5192 = StreamController(name="x5192",parent=x5399) { implicit CU => 
      val ctr55 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5153 = CounterChain(name = "x5153", ctr55).iter(16)
    }
    val x5181 = Sequential(name="x5181",parent=x5192) { implicit CU => 
      val x5181_unit = CounterChain(name = "x5181_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5170_0 = Pipeline(name="x5170_0",parent=x5181) { implicit CU => 
      val x5161 = CU.temp()
      val x5160 = CU.temp()
      val x5159 = CU.temp()
      val x5157 = CU.temp()
      val x4318_x5158 = ScalarBuffer().wtPort(sizeB_argin)
      val x5163 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5153 = CounterChain.copy("x5192", "x5153")
      val x5170_unit = CounterChain(name = "x5170_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5153(0))), op=FixAdd, results=List(x5157))
      Stage(operands=List(x5157, CU.load(x4318_x5158)), op=FixMul, results=List(x5159))
      Stage(operands=List(x5159, CU.ctr(x4334(1))), op=FixAdd, results=List(x5160))
      Stage(operands=List(x5160, Const(4)), op=FixMul, results=List(x5161))
      Stage(operands=List(x5161, CU.load(x5163)), op=FixAdd, results=List(CU.scalarOut(x5154_b5541_x5169_b5543_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5154_b5542_x5169_b5544_s)))
    }
    val x5180 = Pipeline(name="x5180",parent=x5181) { implicit CU => 
      val ctr56 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5172 = CounterChain(name = "x5172", ctr56).iter(1)
    }
    val x5182 = MemoryController(name="x5182",parent=x5192,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5154_b5541_x5182 = ScalarFIFO(name="offset",size=1).wtPort(x5154_b5541_x5169_b5543_s)
      val x5155_x5182 = VectorFIFO(name="data",size=1).wtPort(x4359_x5176_x5180_v)
      val x5154_b5542_x5182 = ScalarFIFO(name="size",size=1).wtPort(x5154_b5542_x5169_b5544_s)
    }
    val x5233 = StreamController(name="x5233",parent=x5399) { implicit CU => 
      val ctr58 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5194 = CounterChain(name = "x5194", ctr58).iter(16)
    }
    val x5222 = Sequential(name="x5222",parent=x5233) { implicit CU => 
      val x5222_unit = CounterChain(name = "x5222_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5211_0 = Pipeline(name="x5211_0",parent=x5222) { implicit CU => 
      val x5198 = CU.temp()
      val x5200 = CU.temp()
      val x5202 = CU.temp()
      val x5201 = CU.temp()
      val x4318_x5199 = ScalarBuffer().wtPort(sizeB_argin)
      val x5204 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5194 = CounterChain.copy("x5233", "x5194")
      val x5211_unit = CounterChain(name = "x5211_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5194(0))), op=FixAdd, results=List(x5198))
      Stage(operands=List(x5198, CU.load(x4318_x5199)), op=FixMul, results=List(x5200))
      Stage(operands=List(x5200, CU.ctr(x4334(1))), op=FixAdd, results=List(x5201))
      Stage(operands=List(x5201, Const(4)), op=FixMul, results=List(x5202))
      Stage(operands=List(x5202, CU.load(x5204)), op=FixAdd, results=List(CU.scalarOut(x5195_b5547_x5210_b5549_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5195_b5548_x5210_b5550_s)))
    }
    val x5221 = Pipeline(name="x5221",parent=x5222) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5213 = CounterChain(name = "x5213", ctr59).iter(1)
    }
    val x5223 = MemoryController(name="x5223",parent=x5233,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5195_b5547_x5223 = ScalarFIFO(name="offset",size=1).wtPort(x5195_b5547_x5210_b5549_s)
      val x5196_x5223 = VectorFIFO(name="data",size=1).wtPort(x4360_x5217_x5221_v)
      val x5195_b5548_x5223 = ScalarFIFO(name="size",size=1).wtPort(x5195_b5548_x5210_b5550_s)
    }
    val x5274 = StreamController(name="x5274",parent=x5399) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5235 = CounterChain(name = "x5235", ctr61).iter(16)
    }
    val x5263 = Sequential(name="x5263",parent=x5274) { implicit CU => 
      val x5263_unit = CounterChain(name = "x5263_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5252_0 = Pipeline(name="x5252_0",parent=x5263) { implicit CU => 
      val x5243 = CU.temp()
      val x5241 = CU.temp()
      val x5239 = CU.temp()
      val x5242 = CU.temp()
      val x4318_x5240 = ScalarBuffer().wtPort(sizeB_argin)
      val x5245 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5235 = CounterChain.copy("x5274", "x5235")
      val x5252_unit = CounterChain(name = "x5252_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5235(0))), op=FixAdd, results=List(x5239))
      Stage(operands=List(x5239, CU.load(x4318_x5240)), op=FixMul, results=List(x5241))
      Stage(operands=List(x5241, CU.ctr(x4334(1))), op=FixAdd, results=List(x5242))
      Stage(operands=List(x5242, Const(4)), op=FixMul, results=List(x5243))
      Stage(operands=List(x5243, CU.load(x5245)), op=FixAdd, results=List(CU.scalarOut(x5236_b5553_x5251_b5555_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5236_b5554_x5251_b5556_s)))
    }
    val x5262 = Pipeline(name="x5262",parent=x5263) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5254 = CounterChain(name = "x5254", ctr62).iter(1)
    }
    val x5264 = MemoryController(name="x5264",parent=x5274,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5236_b5553_x5264 = ScalarFIFO(name="offset",size=1).wtPort(x5236_b5553_x5251_b5555_s)
      val x5237_x5264 = VectorFIFO(name="data",size=1).wtPort(x4361_x5258_x5262_v)
      val x5236_b5554_x5264 = ScalarFIFO(name="size",size=1).wtPort(x5236_b5554_x5251_b5556_s)
    }
    val x5315 = StreamController(name="x5315",parent=x5399) { implicit CU => 
      val ctr64 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5276 = CounterChain(name = "x5276", ctr64).iter(16)
    }
    val x5304 = Sequential(name="x5304",parent=x5315) { implicit CU => 
      val x5304_unit = CounterChain(name = "x5304_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5293_0 = Pipeline(name="x5293_0",parent=x5304) { implicit CU => 
      val x5282 = CU.temp()
      val x5280 = CU.temp()
      val x5283 = CU.temp()
      val x5284 = CU.temp()
      val x4318_x5281 = ScalarBuffer().wtPort(sizeB_argin)
      val x5286 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5276 = CounterChain.copy("x5315", "x5276")
      val x5293_unit = CounterChain(name = "x5293_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5276(0))), op=FixAdd, results=List(x5280))
      Stage(operands=List(x5280, CU.load(x4318_x5281)), op=FixMul, results=List(x5282))
      Stage(operands=List(x5282, CU.ctr(x4334(1))), op=FixAdd, results=List(x5283))
      Stage(operands=List(x5283, Const(4)), op=FixMul, results=List(x5284))
      Stage(operands=List(x5284, CU.load(x5286)), op=FixAdd, results=List(CU.scalarOut(x5277_b5559_x5292_b5561_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5277_b5560_x5292_b5562_s)))
    }
    val x5303 = Pipeline(name="x5303",parent=x5304) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5295 = CounterChain(name = "x5295", ctr65).iter(1)
    }
    val x5305 = MemoryController(name="x5305",parent=x5315,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5278_x5305 = VectorFIFO(name="data",size=1).wtPort(x4362_x5299_x5303_v)
      val x5277_b5559_x5305 = ScalarFIFO(name="offset",size=1).wtPort(x5277_b5559_x5292_b5561_s)
      val x5277_b5560_x5305 = ScalarFIFO(name="size",size=1).wtPort(x5277_b5560_x5292_b5562_s)
    }
    val x5356 = StreamController(name="x5356",parent=x5399) { implicit CU => 
      val ctr67 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5317 = CounterChain(name = "x5317", ctr67).iter(16)
    }
    val x5345 = Sequential(name="x5345",parent=x5356) { implicit CU => 
      val x5345_unit = CounterChain(name = "x5345_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5334_0 = Pipeline(name="x5334_0",parent=x5345) { implicit CU => 
      val x5323 = CU.temp()
      val x5324 = CU.temp()
      val x5321 = CU.temp()
      val x5325 = CU.temp()
      val x4318_x5322 = ScalarBuffer().wtPort(sizeB_argin)
      val x5327 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5317 = CounterChain.copy("x5356", "x5317")
      val x5334_unit = CounterChain(name = "x5334_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5317(0))), op=FixAdd, results=List(x5321))
      Stage(operands=List(x5321, CU.load(x4318_x5322)), op=FixMul, results=List(x5323))
      Stage(operands=List(x5323, CU.ctr(x4334(1))), op=FixAdd, results=List(x5324))
      Stage(operands=List(x5324, Const(4)), op=FixMul, results=List(x5325))
      Stage(operands=List(x5325, CU.load(x5327)), op=FixAdd, results=List(CU.scalarOut(x5318_b5565_x5333_b5567_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5318_b5566_x5333_b5568_s)))
    }
    val x5344 = Pipeline(name="x5344",parent=x5345) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5336 = CounterChain(name = "x5336", ctr68).iter(1)
    }
    val x5346 = MemoryController(name="x5346",parent=x5356,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5318_b5565_x5346 = ScalarFIFO(name="offset",size=1).wtPort(x5318_b5565_x5333_b5567_s)
      val x5319_x5346 = VectorFIFO(name="data",size=1).wtPort(x4363_x5340_x5344_v)
      val x5318_b5566_x5346 = ScalarFIFO(name="size",size=1).wtPort(x5318_b5566_x5333_b5568_s)
    }
    val x5397 = StreamController(name="x5397",parent=x5399) { implicit CU => 
      val ctr70 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x5358 = CounterChain(name = "x5358", ctr70).iter(16)
    }
    val x5386 = Sequential(name="x5386",parent=x5397) { implicit CU => 
      val x5386_unit = CounterChain(name = "x5386_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5375_0 = Pipeline(name="x5375_0",parent=x5386) { implicit CU => 
      val x5362 = CU.temp()
      val x5364 = CU.temp()
      val x5365 = CU.temp()
      val x5366 = CU.temp()
      val x4318_x5363 = ScalarBuffer().wtPort(sizeB_argin)
      val x5368 = ScalarBuffer().wtPort(out_da)
      val x4334 = CounterChain.copy("x5399", "x4334")
      val x5358 = CounterChain.copy("x5397", "x5358")
      val x5375_unit = CounterChain(name = "x5375_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x4334(0)), CU.ctr(x5358(0))), op=FixAdd, results=List(x5362))
      Stage(operands=List(x5362, CU.load(x4318_x5363)), op=FixMul, results=List(x5364))
      Stage(operands=List(x5364, CU.ctr(x4334(1))), op=FixAdd, results=List(x5365))
      Stage(operands=List(x5365, Const(4)), op=FixMul, results=List(x5366))
      Stage(operands=List(x5366, CU.load(x5368)), op=FixAdd, results=List(CU.scalarOut(x5359_b5571_x5374_b5573_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x5359_b5572_x5374_b5574_s)))
    }
    val x5385 = Pipeline(name="x5385",parent=x5386) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x5377 = CounterChain(name = "x5377", ctr71).iter(1)
    }
    val x5387 = MemoryController(name="x5387",parent=x5397,offchip=out_oc, mctpe=TileStore) { implicit CU => 
      val x5360_x5387 = VectorFIFO(name="data",size=1).wtPort(x4364_x5381_x5385_v)
      val x5359_b5572_x5387 = ScalarFIFO(name="size",size=1).wtPort(x5359_b5572_x5374_b5574_s)
      val x5359_b5571_x5387 = ScalarFIFO(name="offset",size=1).wtPort(x5359_b5571_x5374_b5573_s)
    }
    
  }
}
