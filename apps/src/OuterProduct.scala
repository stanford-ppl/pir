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
    val x4133_x4143_data_v = Vector("x4133_x4143_data")
    val x4239_b5098_x4248_b5100_s = Scalar("x4239_b5098_x4248_b5100")
    val x4933_b5212_x4948_b5214_s = Scalar("x4933_b5212_x4948_b5214")
    val x4973_b5218_x4988_b5220_s = Scalar("x4973_b5218_x4988_b5220")
    val x4976_argin = ArgIn("x4976")
    val x4736_argin = ArgIn("x4736")
    val x5013_b5224_x5028_b5226_s = Scalar("x5013_b5224_x5028_b5226")
    val x4239_b5099_x4248_b5101_s = Scalar("x4239_b5099_x4248_b5101")
    val x4455_x4465_data_v = Vector("x4455_x4465_data")
    val x4656_argin = ArgIn("x4656")
    val x4082_x4570_v = Vector("x4082_x4570")
    val x4283_x4293_data_v = Vector("x4283_x4293_data")
    val x4477_x4487_data_v = Vector("x4477_x4487_data")
    val x4411_b5130_x4420_b5132_s = Scalar("x4411_b5130_x4420_b5132")
    val x4499_argin = ArgIn("x4499")
    val x4325_b5114_x4334_b5116_s = Scalar("x4325_b5114_x4334_b5116")
    val x4282_b5106_x4291_b5108_s = Scalar("x4282_b5106_x4291_b5108")
    val x4368_b5123_x4377_b5125_s = Scalar("x4368_b5123_x4377_b5125")
    val x4326_x4336_data_v = Vector("x4326_x4336_data")
    val x4347_b5118_x4356_b5120_s = Scalar("x4347_b5118_x4356_b5120")
    val x4262_x4272_data_v = Vector("x4262_x4272_data")
    val x4733_b5183_x4748_b5185_s = Scalar("x4733_b5183_x4748_b5185")
    val x4070_x4540_x4545_v = Vector("x4070_x4540_x4545")
    val x4306_argin = ArgIn("x4306")
    val x4893_b5206_x4908_b5208_s = Scalar("x4893_b5206_x4908_b5208")
    val x4497_b5146_x4506_b5148_s = Scalar("x4497_b5146_x4506_b5148")
    val x4134_argin = ArgIn("x4134")
    val x4198_argin = ArgIn("x4198")
    val x4072_x4566_x4571_v = Vector("x4072_x4566_x4571")
    val x4066_x4617_x4623_v = Vector("x4066_x4617_x4623")
    val x4073_x4579_x4584_v = Vector("x4073_x4579_x4584")
    val x4433_b5135_x4442_b5137_s = Scalar("x4433_b5135_x4442_b5137")
    val x4078_x4644_x4649_v = Vector("x4078_x4644_x4649")
    val x4089_b5071_x4098_b5073_s = Scalar("x4089_b5071_x4098_b5073")
    val x4086_x4622_v = Vector("x4086_x4622")
    val x4816_argin = ArgIn("x4816")
    val x4087_x4995_x4999_v = Vector("x4087_x4995_x4999")
    val x4051_oc = OffChip("x4051")
    val x4853_b5200_x4868_b5202_s = Scalar("x4853_b5200_x4868_b5202")
    val x4218_b5095_x4227_b5097_s = Scalar("x4218_b5095_x4227_b5097")
    val x4132_b5079_x4141_b5081_s = Scalar("x4132_b5079_x4141_b5081")
    val x4154_x4164_data_v = Vector("x4154_x4164_data")
    val x4368_b5122_x4377_b5124_s = Scalar("x4368_b5122_x4377_b5124")
    val x4390_b5127_x4399_b5129_s = Scalar("x4390_b5127_x4399_b5129")
    val x4086_x4955_x4959_v = Vector("x4086_x4955_x4959")
    val x4413_argin = ArgIn("x4413")
    val x4175_b5086_x4184_b5088_s = Scalar("x4175_b5086_x4184_b5088")
    val x4240_x4250_data_v = Vector("x4240_x4250_data")
    val x4933_b5213_x4948_b5215_s = Scalar("x4933_b5213_x4948_b5215")
    val x4061_x4552_x4558_v = Vector("x4061_x4552_x4558")
    val x4284_argin = ArgIn("x4284")
    val x4110_b5074_x4119_b5076_s = Scalar("x4110_b5074_x4119_b5076")
    val x4176_x4186_data_v = Vector("x4176_x4186_data")
    val x4348_x4358_data_v = Vector("x4348_x4358_data")
    val x4064_x4591_x4597_v = Vector("x4064_x4591_x4597")
    val x4813_b5195_x4828_b5197_s = Scalar("x4813_b5195_x4828_b5197")
    val x4304_b5111_x4313_b5113_s = Scalar("x4304_b5111_x4313_b5113")
    val x4454_b5139_x4463_b5141_s = Scalar("x4454_b5139_x4463_b5141")
    val x4412_x4422_data_v = Vector("x4412_x4422_data")
    val x4062_x4565_x4571_v = Vector("x4062_x4565_x4571")
    val x4733_b5182_x4748_b5184_s = Scalar("x4733_b5182_x4748_b5184")
    val x4111_x4121_data_v = Vector("x4111_x4121_data")
    val x4327_argin = ArgIn("x4327")
    val x4059_x4526_x4532_v = Vector("x4059_x4526_x4532")
    val x4155_argin = ArgIn("x4155")
    val x4067_x4630_x4636_v = Vector("x4067_x4630_x4636")
    val x4088_x4648_v = Vector("x4088_x4648")
    val x4069_x4527_x4532_v = Vector("x4069_x4527_x4532")
    val x4476_b5142_x4485_b5144_s = Scalar("x4476_b5142_x4485_b5144")
    val x4110_b5075_x4119_b5077_s = Scalar("x4110_b5075_x4119_b5077")
    val x4041_argin = ArgIn("x4041")
    val x4091_argin = ArgIn("x4091")
    val x4497_b5147_x4506_b5149_s = Scalar("x4497_b5147_x4506_b5149")
    val x4060_x4539_x4545_v = Vector("x4060_x4539_x4545")
    val x4085_x4609_v = Vector("x4085_x4609")
    val x4083_x4835_x4839_v = Vector("x4083_x4835_x4839")
    val x4813_b5194_x4828_b5196_s = Scalar("x4813_b5194_x4828_b5196")
    val x4153_b5082_x4162_b5084_s = Scalar("x4153_b5082_x4162_b5084")
    val x4241_argin = ArgIn("x4241")
    val x4347_b5119_x4356_b5121_s = Scalar("x4347_b5119_x4356_b5121")
    val x4973_b5219_x4988_b5221_s = Scalar("x4973_b5219_x4988_b5221")
    val x4082_x4795_x4799_v = Vector("x4082_x4795_x4799")
    val x4071_x4553_x4558_v = Vector("x4071_x4553_x4558")
    val x4776_argin = ArgIn("x4776")
    val x4132_b5078_x4141_b5080_s = Scalar("x4132_b5078_x4141_b5080")
    val x4075_x4605_x4610_v = Vector("x4075_x4605_x4610")
    val x4084_x4596_v = Vector("x4084_x4596")
    val x4089_b5070_x4098_b5072_s = Scalar("x4089_b5070_x4098_b5072")
    val x4325_b5115_x4334_b5117_s = Scalar("x4325_b5115_x4334_b5117")
    val x4220_argin = ArgIn("x4220")
    val x4263_argin = ArgIn("x4263")
    val x4074_x4592_x4597_v = Vector("x4074_x4592_x4597")
    val x4433_b5134_x4442_b5136_s = Scalar("x4433_b5134_x4442_b5136")
    val x4282_b5107_x4291_b5109_s = Scalar("x4282_b5107_x4291_b5109")
    val x4219_x4229_data_v = Vector("x4219_x4229_data")
    val x4046_oc = OffChip("x4046")
    val x4370_argin = ArgIn("x4370")
    val x4390_b5126_x4399_b5128_s = Scalar("x4390_b5126_x4399_b5128")
    val x4079_x4531_v = Vector("x4079_x4531")
    val x5013_b5225_x5028_b5227_s = Scalar("x5013_b5225_x5028_b5227")
    val x4083_x4583_v = Vector("x4083_x4583")
    val x4773_b5188_x4788_b5190_s = Scalar("x4773_b5188_x4788_b5190")
    val x4261_b5102_x4270_b5104_s = Scalar("x4261_b5102_x4270_b5104")
    val x4090_x4100_data_v = Vector("x4090_x4100_data")
    val x4080_x4715_x4719_v = Vector("x4080_x4715_x4719")
    val x4079_x4675_x4679_v = Vector("x4079_x4675_x4679")
    val x4896_argin = ArgIn("x4896")
    val x4081_x4755_x4759_v = Vector("x4081_x4755_x4759")
    val x4048_oc = OffChip("x4048")
    val x4391_x4401_data_v = Vector("x4391_x4401_data")
    val x4063_x4578_x4584_v = Vector("x4063_x4578_x4584")
    val x4456_argin = ArgIn("x4456")
    val x4177_argin = ArgIn("x4177")
    val x4369_x4379_data_v = Vector("x4369_x4379_data")
    val x4498_x4508_data_v = Vector("x4498_x4508_data")
    val x4080_x4544_v = Vector("x4080_x4544")
    val x4087_x4635_v = Vector("x4087_x4635")
    val x4088_x5035_x5039_v = Vector("x4088_x5035_x5039")
    val x4434_x4444_data_v = Vector("x4434_x4444_data")
    val x4304_b5110_x4313_b5112_s = Scalar("x4304_b5110_x4313_b5112")
    val x4196_b5091_x4205_b5093_s = Scalar("x4196_b5091_x4205_b5093")
    val x4693_b5177_x4708_b5179_s = Scalar("x4693_b5177_x4708_b5179")
    val x4218_b5094_x4227_b5096_s = Scalar("x4218_b5094_x4227_b5096")
    val x4081_x4557_v = Vector("x4081_x4557")
    val x4936_argin = ArgIn("x4936")
    val x4076_x4618_x4623_v = Vector("x4076_x4618_x4623")
    val x4693_b5176_x4708_b5178_s = Scalar("x4693_b5176_x4708_b5178")
    val x4068_x4643_x4649_v = Vector("x4068_x4643_x4649")
    val x4653_b5170_x4668_b5172_s = Scalar("x4653_b5170_x4668_b5172")
    val x4112_argin = ArgIn("x4112")
    val x4349_argin = ArgIn("x4349")
    val x4085_x4915_x4919_v = Vector("x4085_x4915_x4919")
    val x4175_b5087_x4184_b5089_s = Scalar("x4175_b5087_x4184_b5089")
    val x4153_b5083_x4162_b5085_s = Scalar("x4153_b5083_x4162_b5085")
    val x4305_x4315_data_v = Vector("x4305_x4315_data")
    val x4696_argin = ArgIn("x4696")
    val x4196_b5090_x4205_b5092_s = Scalar("x4196_b5090_x4205_b5092")
    val x4893_b5207_x4908_b5209_s = Scalar("x4893_b5207_x4908_b5209")
    val x4077_x4631_x4636_v = Vector("x4077_x4631_x4636")
    val x4856_argin = ArgIn("x4856")
    val x4197_x4207_data_v = Vector("x4197_x4207_data")
    val x4084_x4875_x4879_v = Vector("x4084_x4875_x4879")
    val x4261_b5103_x4270_b5105_s = Scalar("x4261_b5103_x4270_b5105")
    val x4042_argin = ArgIn("x4042")
    val x4411_b5131_x4420_b5133_s = Scalar("x4411_b5131_x4420_b5133")
    val x4392_argin = ArgIn("x4392")
    val x4653_b5171_x4668_b5173_s = Scalar("x4653_b5171_x4668_b5173")
    val x5016_argin = ArgIn("x5016")
    val x4773_b5189_x4788_b5191_s = Scalar("x4773_b5189_x4788_b5191")
    val x4065_x4604_x4610_v = Vector("x4065_x4604_x4610")
    val x4853_b5201_x4868_b5203_s = Scalar("x4853_b5201_x4868_b5203")
    val x4435_argin = ArgIn("x4435")
    val x4478_argin = ArgIn("x4478")
    val x4454_b5138_x4463_b5140_s = Scalar("x4454_b5138_x4463_b5140")
    val x4476_b5143_x4485_b5145_s = Scalar("x4476_b5143_x4485_b5145")
    val x5053 = Sequential(name="x5053",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5053_unit = CounterChain(name = "x5053_unit", ctr1)
    }
    val x5052 = MetaPipeline(name="x5052",parent=x5053) { implicit CU => 
      val x4042_x4054 =  ScalarBuffer().wtPort(x4042_argin)
      val x4041_x4056 =  ScalarBuffer().wtPort(x4041_argin)
      val ctr2 = Counter(min=Const(0), max=x4041_x4056.load, step=Const(64), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x4042_x4054.load, step=Const(64), par=10) // Counter
      val x4058 = CounterChain(name = "x4058", ctr2, ctr3)
    }
    val x4059_dsp0 = MemoryPipeline(name="x4059_dsp0",parent="x5052") { implicit CU => 
      val x4107_x4107 =  VectorFIFO(size=1).wtPort(x4090_x4100_data_v)
      val x4102 = CounterChain.copy("x4108", "x4102")
      val x4522 = CounterChain.copy("x4532", "x4522")
      val x4059_x4526 =  SRAM(size=64,banking = Strided(1)).wtPort(x4107_x4107.readPort).rdPort(x4059_x4526_x4532_v).rdAddr(x4522(0)).wtAddr(x4102(0))
      var stage: List[Stage] = Nil
    }
    val x4060_dsp0 = MemoryPipeline(name="x4060_dsp0",parent="x5052") { implicit CU => 
      val x4150_x4150 =  VectorFIFO(size=1).wtPort(x4133_x4143_data_v)
      val x4145 = CounterChain.copy("x4151", "x4145")
      val x4535 = CounterChain.copy("x4545", "x4535")
      val x4060_x4539 =  SRAM(size=64,banking = Strided(1)).wtPort(x4150_x4150.readPort).rdPort(x4060_x4539_x4545_v).rdAddr(x4535(0)).wtAddr(x4145(0))
      var stage: List[Stage] = Nil
    }
    val x4061_dsp0 = MemoryPipeline(name="x4061_dsp0",parent="x5052") { implicit CU => 
      val x4193_x4193 =  VectorFIFO(size=1).wtPort(x4176_x4186_data_v)
      val x4188 = CounterChain.copy("x4194", "x4188")
      val x4548 = CounterChain.copy("x4558", "x4548")
      val x4061_x4552 =  SRAM(size=64,banking = Strided(1)).wtPort(x4193_x4193.readPort).rdPort(x4061_x4552_x4558_v).rdAddr(x4548(0)).wtAddr(x4188(0))
      var stage: List[Stage] = Nil
    }
    val x4062_dsp0 = MemoryPipeline(name="x4062_dsp0",parent="x5052") { implicit CU => 
      val x4236_x4236 =  VectorFIFO(size=1).wtPort(x4219_x4229_data_v)
      val x4231 = CounterChain.copy("x4237", "x4231")
      val x4561 = CounterChain.copy("x4571", "x4561")
      val x4062_x4565 =  SRAM(size=64,banking = Strided(1)).wtPort(x4236_x4236.readPort).rdPort(x4062_x4565_x4571_v).rdAddr(x4561(0)).wtAddr(x4231(0))
      var stage: List[Stage] = Nil
    }
    val x4063_dsp0 = MemoryPipeline(name="x4063_dsp0",parent="x5052") { implicit CU => 
      val x4279_x4279 =  VectorFIFO(size=1).wtPort(x4262_x4272_data_v)
      val x4274 = CounterChain.copy("x4280", "x4274")
      val x4574 = CounterChain.copy("x4584", "x4574")
      val x4063_x4578 =  SRAM(size=64,banking = Strided(1)).wtPort(x4279_x4279.readPort).rdPort(x4063_x4578_x4584_v).rdAddr(x4574(0)).wtAddr(x4274(0))
      var stage: List[Stage] = Nil
    }
    val x4064_dsp0 = MemoryPipeline(name="x4064_dsp0",parent="x5052") { implicit CU => 
      val x4322_x4322 =  VectorFIFO(size=1).wtPort(x4305_x4315_data_v)
      val x4317 = CounterChain.copy("x4323", "x4317")
      val x4587 = CounterChain.copy("x4597", "x4587")
      val x4064_x4591 =  SRAM(size=64,banking = Strided(1)).wtPort(x4322_x4322.readPort).rdPort(x4064_x4591_x4597_v).rdAddr(x4587(0)).wtAddr(x4317(0))
      var stage: List[Stage] = Nil
    }
    val x4065_dsp0 = MemoryPipeline(name="x4065_dsp0",parent="x5052") { implicit CU => 
      val x4365_x4365 =  VectorFIFO(size=1).wtPort(x4348_x4358_data_v)
      val x4360 = CounterChain.copy("x4366", "x4360")
      val x4600 = CounterChain.copy("x4610", "x4600")
      val x4065_x4604 =  SRAM(size=64,banking = Strided(1)).wtPort(x4365_x4365.readPort).rdPort(x4065_x4604_x4610_v).rdAddr(x4600(0)).wtAddr(x4360(0))
      var stage: List[Stage] = Nil
    }
    val x4066_dsp0 = MemoryPipeline(name="x4066_dsp0",parent="x5052") { implicit CU => 
      val x4408_x4408 =  VectorFIFO(size=1).wtPort(x4391_x4401_data_v)
      val x4403 = CounterChain.copy("x4409", "x4403")
      val x4613 = CounterChain.copy("x4623", "x4613")
      val x4066_x4617 =  SRAM(size=64,banking = Strided(1)).wtPort(x4408_x4408.readPort).rdPort(x4066_x4617_x4623_v).rdAddr(x4613(0)).wtAddr(x4403(0))
      var stage: List[Stage] = Nil
    }
    val x4067_dsp0 = MemoryPipeline(name="x4067_dsp0",parent="x5052") { implicit CU => 
      val x4451_x4451 =  VectorFIFO(size=1).wtPort(x4434_x4444_data_v)
      val x4446 = CounterChain.copy("x4452", "x4446")
      val x4626 = CounterChain.copy("x4636", "x4626")
      val x4067_x4630 =  SRAM(size=64,banking = Strided(1)).wtPort(x4451_x4451.readPort).rdPort(x4067_x4630_x4636_v).rdAddr(x4626(0)).wtAddr(x4446(0))
      var stage: List[Stage] = Nil
    }
    val x4068_dsp0 = MemoryPipeline(name="x4068_dsp0",parent="x5052") { implicit CU => 
      val x4494_x4494 =  VectorFIFO(size=1).wtPort(x4477_x4487_data_v)
      val x4489 = CounterChain.copy("x4495", "x4489")
      val x4639 = CounterChain.copy("x4649", "x4639")
      val x4068_x4643 =  SRAM(size=64,banking = Strided(1)).wtPort(x4494_x4494.readPort).rdPort(x4068_x4643_x4649_v).rdAddr(x4639(0)).wtAddr(x4489(0))
      var stage: List[Stage] = Nil
    }
    val x4069_dsp0 = MemoryPipeline(name="x4069_dsp0",parent="x5052") { implicit CU => 
      val x4128_x4128 =  VectorFIFO(size=1).wtPort(x4111_x4121_data_v)
      val x4123 = CounterChain.copy("x4129", "x4123")
      val x4522 = CounterChain.copy("x4532", "x4522")
      val x4069_x4527 =  SRAM(size=64,banking = Strided(1)).wtPort(x4128_x4128.readPort).rdPort(x4069_x4527_x4532_v).rdAddr(x4522(1)).wtAddr(x4123(0))
      var stage: List[Stage] = Nil
    }
    val x4070_dsp0 = MemoryPipeline(name="x4070_dsp0",parent="x5052") { implicit CU => 
      val x4171_x4171 =  VectorFIFO(size=1).wtPort(x4154_x4164_data_v)
      val x4166 = CounterChain.copy("x4172", "x4166")
      val x4535 = CounterChain.copy("x4545", "x4535")
      val x4070_x4540 =  SRAM(size=64,banking = Strided(1)).wtPort(x4171_x4171.readPort).rdPort(x4070_x4540_x4545_v).rdAddr(x4535(1)).wtAddr(x4166(0))
      var stage: List[Stage] = Nil
    }
    val x4071_dsp0 = MemoryPipeline(name="x4071_dsp0",parent="x5052") { implicit CU => 
      val x4214_x4214 =  VectorFIFO(size=1).wtPort(x4197_x4207_data_v)
      val x4209 = CounterChain.copy("x4215", "x4209")
      val x4548 = CounterChain.copy("x4558", "x4548")
      val x4071_x4553 =  SRAM(size=64,banking = Strided(1)).wtPort(x4214_x4214.readPort).rdPort(x4071_x4553_x4558_v).rdAddr(x4548(1)).wtAddr(x4209(0))
      var stage: List[Stage] = Nil
    }
    val x4072_dsp0 = MemoryPipeline(name="x4072_dsp0",parent="x5052") { implicit CU => 
      val x4257_x4257 =  VectorFIFO(size=1).wtPort(x4240_x4250_data_v)
      val x4252 = CounterChain.copy("x4258", "x4252")
      val x4561 = CounterChain.copy("x4571", "x4561")
      val x4072_x4566 =  SRAM(size=64,banking = Strided(1)).wtPort(x4257_x4257.readPort).rdPort(x4072_x4566_x4571_v).rdAddr(x4561(1)).wtAddr(x4252(0))
      var stage: List[Stage] = Nil
    }
    val x4073_dsp0 = MemoryPipeline(name="x4073_dsp0",parent="x5052") { implicit CU => 
      val x4300_x4300 =  VectorFIFO(size=1).wtPort(x4283_x4293_data_v)
      val x4295 = CounterChain.copy("x4301", "x4295")
      val x4574 = CounterChain.copy("x4584", "x4574")
      val x4073_x4579 =  SRAM(size=64,banking = Strided(1)).wtPort(x4300_x4300.readPort).rdPort(x4073_x4579_x4584_v).rdAddr(x4574(1)).wtAddr(x4295(0))
      var stage: List[Stage] = Nil
    }
    val x4074_dsp0 = MemoryPipeline(name="x4074_dsp0",parent="x5052") { implicit CU => 
      val x4343_x4343 =  VectorFIFO(size=1).wtPort(x4326_x4336_data_v)
      val x4338 = CounterChain.copy("x4344", "x4338")
      val x4587 = CounterChain.copy("x4597", "x4587")
      val x4074_x4592 =  SRAM(size=64,banking = Strided(1)).wtPort(x4343_x4343.readPort).rdPort(x4074_x4592_x4597_v).rdAddr(x4587(1)).wtAddr(x4338(0))
      var stage: List[Stage] = Nil
    }
    val x4075_dsp0 = MemoryPipeline(name="x4075_dsp0",parent="x5052") { implicit CU => 
      val x4386_x4386 =  VectorFIFO(size=1).wtPort(x4369_x4379_data_v)
      val x4381 = CounterChain.copy("x4387", "x4381")
      val x4600 = CounterChain.copy("x4610", "x4600")
      val x4075_x4605 =  SRAM(size=64,banking = Strided(1)).wtPort(x4386_x4386.readPort).rdPort(x4075_x4605_x4610_v).rdAddr(x4600(1)).wtAddr(x4381(0))
      var stage: List[Stage] = Nil
    }
    val x4076_dsp0 = MemoryPipeline(name="x4076_dsp0",parent="x5052") { implicit CU => 
      val x4429_x4429 =  VectorFIFO(size=1).wtPort(x4412_x4422_data_v)
      val x4424 = CounterChain.copy("x4430", "x4424")
      val x4613 = CounterChain.copy("x4623", "x4613")
      val x4076_x4618 =  SRAM(size=64,banking = Strided(1)).wtPort(x4429_x4429.readPort).rdPort(x4076_x4618_x4623_v).rdAddr(x4613(1)).wtAddr(x4424(0))
      var stage: List[Stage] = Nil
    }
    val x4077_dsp0 = MemoryPipeline(name="x4077_dsp0",parent="x5052") { implicit CU => 
      val x4472_x4472 =  VectorFIFO(size=1).wtPort(x4455_x4465_data_v)
      val x4467 = CounterChain.copy("x4473", "x4467")
      val x4626 = CounterChain.copy("x4636", "x4626")
      val x4077_x4631 =  SRAM(size=64,banking = Strided(1)).wtPort(x4472_x4472.readPort).rdPort(x4077_x4631_x4636_v).rdAddr(x4626(1)).wtAddr(x4467(0))
      var stage: List[Stage] = Nil
    }
    val x4078_dsp0 = MemoryPipeline(name="x4078_dsp0",parent="x5052") { implicit CU => 
      val x4515_x4515 =  VectorFIFO(size=1).wtPort(x4498_x4508_data_v)
      val x4510 = CounterChain.copy("x4516", "x4510")
      val x4639 = CounterChain.copy("x4649", "x4639")
      val x4078_x4644 =  SRAM(size=64,banking = Strided(1)).wtPort(x4515_x4515.readPort).rdPort(x4078_x4644_x4649_v).rdAddr(x4639(1)).wtAddr(x4510(0))
      var stage: List[Stage] = Nil
    }
    val x4079_dsp0 = MemoryPipeline(name="x4079_dsp0",parent="x5052") { implicit CU => 
      val b5150 = CU.temp
      val b5174 = CU.temp
      val x4531_x4531 =  VectorFIFO(size=1).wtPort(x4079_x4531_v)
      val x4522 = CounterChain.copy("x4532", "x4522")
      val x4652 = CounterChain.copy("x4690", "x4652")
      val x4671 = CounterChain.copy("x4679", "x4671")
      val x4079_x4675 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4531_x4531.readPort).rdPort(x4079_x4675_x4679_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4522(0)), Const(64)), op=FixMul, results=List(b5150))
      WAStage(operands=List(b5150, CU.ctr(x4522(1))), op=FixAdd, results=List(x4079_x4675.writeAddr))
      RAStage(operands=List(CU.ctr(x4652(0)), Const(64)), op=FixMul, results=List(b5174))
      RAStage(operands=List(b5174, CU.ctr(x4671(0))), op=FixAdd, results=List(x4079_x4675.readAddr))
    }
    val x4080_dsp0 = MemoryPipeline(name="x4080_dsp0",parent="x5052") { implicit CU => 
      val b5152 = CU.temp
      val b5180 = CU.temp
      val x4544_x4544 =  VectorFIFO(size=1).wtPort(x4080_x4544_v)
      val x4535 = CounterChain.copy("x4545", "x4535")
      val x4692 = CounterChain.copy("x4730", "x4692")
      val x4711 = CounterChain.copy("x4719", "x4711")
      val x4080_x4715 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4544_x4544.readPort).rdPort(x4080_x4715_x4719_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4535(0)), Const(64)), op=FixMul, results=List(b5152))
      WAStage(operands=List(b5152, CU.ctr(x4535(1))), op=FixAdd, results=List(x4080_x4715.writeAddr))
      RAStage(operands=List(CU.ctr(x4692(0)), Const(64)), op=FixMul, results=List(b5180))
      RAStage(operands=List(b5180, CU.ctr(x4711(0))), op=FixAdd, results=List(x4080_x4715.readAddr))
    }
    val x4081_dsp0 = MemoryPipeline(name="x4081_dsp0",parent="x5052") { implicit CU => 
      val b5154 = CU.temp
      val b5186 = CU.temp
      val x4557_x4557 =  VectorFIFO(size=1).wtPort(x4081_x4557_v)
      val x4548 = CounterChain.copy("x4558", "x4548")
      val x4732 = CounterChain.copy("x4770", "x4732")
      val x4751 = CounterChain.copy("x4759", "x4751")
      val x4081_x4755 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4557_x4557.readPort).rdPort(x4081_x4755_x4759_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4548(0)), Const(64)), op=FixMul, results=List(b5154))
      WAStage(operands=List(b5154, CU.ctr(x4548(1))), op=FixAdd, results=List(x4081_x4755.writeAddr))
      RAStage(operands=List(CU.ctr(x4732(0)), Const(64)), op=FixMul, results=List(b5186))
      RAStage(operands=List(b5186, CU.ctr(x4751(0))), op=FixAdd, results=List(x4081_x4755.readAddr))
    }
    val x4082_dsp0 = MemoryPipeline(name="x4082_dsp0",parent="x5052") { implicit CU => 
      val b5192 = CU.temp
      val b5156 = CU.temp
      val x4570_x4570 =  VectorFIFO(size=1).wtPort(x4082_x4570_v)
      val x4561 = CounterChain.copy("x4571", "x4561")
      val x4772 = CounterChain.copy("x4810", "x4772")
      val x4791 = CounterChain.copy("x4799", "x4791")
      val x4082_x4795 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4570_x4570.readPort).rdPort(x4082_x4795_x4799_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4561(0)), Const(64)), op=FixMul, results=List(b5156))
      WAStage(operands=List(b5156, CU.ctr(x4561(1))), op=FixAdd, results=List(x4082_x4795.writeAddr))
      RAStage(operands=List(CU.ctr(x4772(0)), Const(64)), op=FixMul, results=List(b5192))
      RAStage(operands=List(b5192, CU.ctr(x4791(0))), op=FixAdd, results=List(x4082_x4795.readAddr))
    }
    val x4083_dsp0 = MemoryPipeline(name="x4083_dsp0",parent="x5052") { implicit CU => 
      val b5198 = CU.temp
      val b5158 = CU.temp
      val x4583_x4583 =  VectorFIFO(size=1).wtPort(x4083_x4583_v)
      val x4574 = CounterChain.copy("x4584", "x4574")
      val x4812 = CounterChain.copy("x4850", "x4812")
      val x4831 = CounterChain.copy("x4839", "x4831")
      val x4083_x4835 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4583_x4583.readPort).rdPort(x4083_x4835_x4839_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4574(0)), Const(64)), op=FixMul, results=List(b5158))
      WAStage(operands=List(b5158, CU.ctr(x4574(1))), op=FixAdd, results=List(x4083_x4835.writeAddr))
      RAStage(operands=List(CU.ctr(x4812(0)), Const(64)), op=FixMul, results=List(b5198))
      RAStage(operands=List(b5198, CU.ctr(x4831(0))), op=FixAdd, results=List(x4083_x4835.readAddr))
    }
    val x4084_dsp0 = MemoryPipeline(name="x4084_dsp0",parent="x5052") { implicit CU => 
      val b5204 = CU.temp
      val b5160 = CU.temp
      val x4596_x4596 =  VectorFIFO(size=1).wtPort(x4084_x4596_v)
      val x4587 = CounterChain.copy("x4597", "x4587")
      val x4852 = CounterChain.copy("x4890", "x4852")
      val x4871 = CounterChain.copy("x4879", "x4871")
      val x4084_x4875 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4596_x4596.readPort).rdPort(x4084_x4875_x4879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4587(0)), Const(64)), op=FixMul, results=List(b5160))
      WAStage(operands=List(b5160, CU.ctr(x4587(1))), op=FixAdd, results=List(x4084_x4875.writeAddr))
      RAStage(operands=List(CU.ctr(x4852(0)), Const(64)), op=FixMul, results=List(b5204))
      RAStage(operands=List(b5204, CU.ctr(x4871(0))), op=FixAdd, results=List(x4084_x4875.readAddr))
    }
    val x4085_dsp0 = MemoryPipeline(name="x4085_dsp0",parent="x5052") { implicit CU => 
      val b5162 = CU.temp
      val b5210 = CU.temp
      val x4609_x4609 =  VectorFIFO(size=1).wtPort(x4085_x4609_v)
      val x4600 = CounterChain.copy("x4610", "x4600")
      val x4892 = CounterChain.copy("x4930", "x4892")
      val x4911 = CounterChain.copy("x4919", "x4911")
      val x4085_x4915 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4609_x4609.readPort).rdPort(x4085_x4915_x4919_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4600(0)), Const(64)), op=FixMul, results=List(b5162))
      WAStage(operands=List(b5162, CU.ctr(x4600(1))), op=FixAdd, results=List(x4085_x4915.writeAddr))
      RAStage(operands=List(CU.ctr(x4892(0)), Const(64)), op=FixMul, results=List(b5210))
      RAStage(operands=List(b5210, CU.ctr(x4911(0))), op=FixAdd, results=List(x4085_x4915.readAddr))
    }
    val x4086_dsp0 = MemoryPipeline(name="x4086_dsp0",parent="x5052") { implicit CU => 
      val b5164 = CU.temp
      val b5216 = CU.temp
      val x4622_x4622 =  VectorFIFO(size=1).wtPort(x4086_x4622_v)
      val x4613 = CounterChain.copy("x4623", "x4613")
      val x4932 = CounterChain.copy("x4970", "x4932")
      val x4951 = CounterChain.copy("x4959", "x4951")
      val x4086_x4955 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4622_x4622.readPort).rdPort(x4086_x4955_x4959_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4613(0)), Const(64)), op=FixMul, results=List(b5164))
      WAStage(operands=List(b5164, CU.ctr(x4613(1))), op=FixAdd, results=List(x4086_x4955.writeAddr))
      RAStage(operands=List(CU.ctr(x4932(0)), Const(64)), op=FixMul, results=List(b5216))
      RAStage(operands=List(b5216, CU.ctr(x4951(0))), op=FixAdd, results=List(x4086_x4955.readAddr))
    }
    val x4087_dsp0 = MemoryPipeline(name="x4087_dsp0",parent="x5052") { implicit CU => 
      val b5166 = CU.temp
      val b5222 = CU.temp
      val x4635_x4635 =  VectorFIFO(size=1).wtPort(x4087_x4635_v)
      val x4626 = CounterChain.copy("x4636", "x4626")
      val x4972 = CounterChain.copy("x5010", "x4972")
      val x4991 = CounterChain.copy("x4999", "x4991")
      val x4087_x4995 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4635_x4635.readPort).rdPort(x4087_x4995_x4999_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4626(0)), Const(64)), op=FixMul, results=List(b5166))
      WAStage(operands=List(b5166, CU.ctr(x4626(1))), op=FixAdd, results=List(x4087_x4995.writeAddr))
      RAStage(operands=List(CU.ctr(x4972(0)), Const(64)), op=FixMul, results=List(b5222))
      RAStage(operands=List(b5222, CU.ctr(x4991(0))), op=FixAdd, results=List(x4087_x4995.readAddr))
    }
    val x4088_dsp0 = MemoryPipeline(name="x4088_dsp0",parent="x5052") { implicit CU => 
      val b5228 = CU.temp
      val b5168 = CU.temp
      val x4648_x4648 =  VectorFIFO(size=1).wtPort(x4088_x4648_v)
      val x4639 = CounterChain.copy("x4649", "x4639")
      val x5012 = CounterChain.copy("x5050", "x5012")
      val x5031 = CounterChain.copy("x5039", "x5031")
      val x4088_x5035 =  SRAM(size=4096,banking = Strided(1)).wtPort(x4648_x4648.readPort).rdPort(x4088_x5035_x5039_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4639(0)), Const(64)), op=FixMul, results=List(b5168))
      WAStage(operands=List(b5168, CU.ctr(x4639(1))), op=FixAdd, results=List(x4088_x5035.writeAddr))
      RAStage(operands=List(CU.ctr(x5012(0)), Const(64)), op=FixMul, results=List(b5228))
      RAStage(operands=List(b5228, CU.ctr(x5031(0))), op=FixAdd, results=List(x4088_x5035.readAddr))
    }
    val x4109 = StreamController(name="x4109",parent=x5052) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4109_unit = CounterChain(name = "x4109_unit", ctr4)
    }
    val x4099 = Pipeline(name="x4099",parent=x4109) { implicit CU => 
      val x4092 = CU.temp
      val x4091 =  ScalarBuffer().wtPort(x4091_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4099_unit = CounterChain(name = "x4099_unit", ctr5)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4092))
      Stage(operands=List(x4092, CU.load(x4091)), op=FixAdd, results=List(CU.scalarOut(x4089_b5070_x4098_b5072_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4089_b5071_x4098_b5073_s)))
    }
    val x4100 = MemoryController(name="x4100",parent=x4109,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4089_b5071_x4100 =  ScalarFIFO(name="size",size=1).wtPort(x4089_b5071_x4098_b5073_s)
      val x4089_b5070_x4100 =  ScalarFIFO(name="offset",size=1).wtPort(x4089_b5070_x4098_b5072_s)
      CU.newVout("data", x4090_x4100_data_v)
    }
    val x4108 = Pipeline(name="x4108",parent=x4109) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4102 = CounterChain(name = "x4102", ctr6)
      var stage: List[Stage] = Nil
    }
    val x4130 = StreamController(name="x4130",parent=x5052) { implicit CU => 
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4130_unit = CounterChain(name = "x4130_unit", ctr7)
    }
    val x4120 = Pipeline(name="x4120",parent=x4130) { implicit CU => 
      val x4113 = CU.temp
      val x4112 =  ScalarBuffer().wtPort(x4112_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4120_unit = CounterChain(name = "x4120_unit", ctr8)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4113))
      Stage(operands=List(x4113, CU.load(x4112)), op=FixAdd, results=List(CU.scalarOut(x4110_b5074_x4119_b5076_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4110_b5075_x4119_b5077_s)))
    }
    val x4121 = MemoryController(name="x4121",parent=x4130,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4110_b5074_x4121 =  ScalarFIFO(name="offset",size=1).wtPort(x4110_b5074_x4119_b5076_s)
      val x4110_b5075_x4121 =  ScalarFIFO(name="size",size=1).wtPort(x4110_b5075_x4119_b5077_s)
      CU.newVout("data", x4111_x4121_data_v)
    }
    val x4129 = Pipeline(name="x4129",parent=x4130) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4123 = CounterChain(name = "x4123", ctr9)
      var stage: List[Stage] = Nil
    }
    val x4152 = StreamController(name="x4152",parent=x5052) { implicit CU => 
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4152_unit = CounterChain(name = "x4152_unit", ctr10)
    }
    val x4142 = Pipeline(name="x4142",parent=x4152) { implicit CU => 
      val x4135 = CU.temp
      val x4134 =  ScalarBuffer().wtPort(x4134_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4142_unit = CounterChain(name = "x4142_unit", ctr11)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4135))
      Stage(operands=List(x4135, CU.load(x4134)), op=FixAdd, results=List(CU.scalarOut(x4132_b5078_x4141_b5080_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4132_b5079_x4141_b5081_s)))
    }
    val x4143 = MemoryController(name="x4143",parent=x4152,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4132_b5079_x4143 =  ScalarFIFO(name="size",size=1).wtPort(x4132_b5079_x4141_b5081_s)
      val x4132_b5078_x4143 =  ScalarFIFO(name="offset",size=1).wtPort(x4132_b5078_x4141_b5080_s)
      CU.newVout("data", x4133_x4143_data_v)
    }
    val x4151 = Pipeline(name="x4151",parent=x4152) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4145 = CounterChain(name = "x4145", ctr12)
      var stage: List[Stage] = Nil
    }
    val x4173 = StreamController(name="x4173",parent=x5052) { implicit CU => 
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4173_unit = CounterChain(name = "x4173_unit", ctr13)
    }
    val x4163 = Pipeline(name="x4163",parent=x4173) { implicit CU => 
      val x4156 = CU.temp
      val x4155 =  ScalarBuffer().wtPort(x4155_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4163_unit = CounterChain(name = "x4163_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4156))
      Stage(operands=List(x4156, CU.load(x4155)), op=FixAdd, results=List(CU.scalarOut(x4153_b5082_x4162_b5084_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4153_b5083_x4162_b5085_s)))
    }
    val x4164 = MemoryController(name="x4164",parent=x4173,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4153_b5083_x4164 =  ScalarFIFO(name="size",size=1).wtPort(x4153_b5083_x4162_b5085_s)
      val x4153_b5082_x4164 =  ScalarFIFO(name="offset",size=1).wtPort(x4153_b5082_x4162_b5084_s)
      CU.newVout("data", x4154_x4164_data_v)
    }
    val x4172 = Pipeline(name="x4172",parent=x4173) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4166 = CounterChain(name = "x4166", ctr15)
      var stage: List[Stage] = Nil
    }
    val x4195 = StreamController(name="x4195",parent=x5052) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4195_unit = CounterChain(name = "x4195_unit", ctr16)
    }
    val x4185 = Pipeline(name="x4185",parent=x4195) { implicit CU => 
      val x4178 = CU.temp
      val x4177 =  ScalarBuffer().wtPort(x4177_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4185_unit = CounterChain(name = "x4185_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4178))
      Stage(operands=List(x4178, CU.load(x4177)), op=FixAdd, results=List(CU.scalarOut(x4175_b5086_x4184_b5088_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4175_b5087_x4184_b5089_s)))
    }
    val x4186 = MemoryController(name="x4186",parent=x4195,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4175_b5086_x4186 =  ScalarFIFO(name="offset",size=1).wtPort(x4175_b5086_x4184_b5088_s)
      val x4175_b5087_x4186 =  ScalarFIFO(name="size",size=1).wtPort(x4175_b5087_x4184_b5089_s)
      CU.newVout("data", x4176_x4186_data_v)
    }
    val x4194 = Pipeline(name="x4194",parent=x4195) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4188 = CounterChain(name = "x4188", ctr18)
      var stage: List[Stage] = Nil
    }
    val x4216 = StreamController(name="x4216",parent=x5052) { implicit CU => 
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4216_unit = CounterChain(name = "x4216_unit", ctr19)
    }
    val x4206 = Pipeline(name="x4206",parent=x4216) { implicit CU => 
      val x4199 = CU.temp
      val x4198 =  ScalarBuffer().wtPort(x4198_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4206_unit = CounterChain(name = "x4206_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4199))
      Stage(operands=List(x4199, CU.load(x4198)), op=FixAdd, results=List(CU.scalarOut(x4196_b5090_x4205_b5092_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4196_b5091_x4205_b5093_s)))
    }
    val x4207 = MemoryController(name="x4207",parent=x4216,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4196_b5091_x4207 =  ScalarFIFO(name="size",size=1).wtPort(x4196_b5091_x4205_b5093_s)
      val x4196_b5090_x4207 =  ScalarFIFO(name="offset",size=1).wtPort(x4196_b5090_x4205_b5092_s)
      CU.newVout("data", x4197_x4207_data_v)
    }
    val x4215 = Pipeline(name="x4215",parent=x4216) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4209 = CounterChain(name = "x4209", ctr21)
      var stage: List[Stage] = Nil
    }
    val x4238 = StreamController(name="x4238",parent=x5052) { implicit CU => 
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4238_unit = CounterChain(name = "x4238_unit", ctr22)
    }
    val x4228 = Pipeline(name="x4228",parent=x4238) { implicit CU => 
      val x4221 = CU.temp
      val x4220 =  ScalarBuffer().wtPort(x4220_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4228_unit = CounterChain(name = "x4228_unit", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4221))
      Stage(operands=List(x4221, CU.load(x4220)), op=FixAdd, results=List(CU.scalarOut(x4218_b5094_x4227_b5096_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4218_b5095_x4227_b5097_s)))
    }
    val x4229 = MemoryController(name="x4229",parent=x4238,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4218_b5095_x4229 =  ScalarFIFO(name="size",size=1).wtPort(x4218_b5095_x4227_b5097_s)
      val x4218_b5094_x4229 =  ScalarFIFO(name="offset",size=1).wtPort(x4218_b5094_x4227_b5096_s)
      CU.newVout("data", x4219_x4229_data_v)
    }
    val x4237 = Pipeline(name="x4237",parent=x4238) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4231 = CounterChain(name = "x4231", ctr24)
      var stage: List[Stage] = Nil
    }
    val x4259 = StreamController(name="x4259",parent=x5052) { implicit CU => 
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4259_unit = CounterChain(name = "x4259_unit", ctr25)
    }
    val x4249 = Pipeline(name="x4249",parent=x4259) { implicit CU => 
      val x4242 = CU.temp
      val x4241 =  ScalarBuffer().wtPort(x4241_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4249_unit = CounterChain(name = "x4249_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4242))
      Stage(operands=List(x4242, CU.load(x4241)), op=FixAdd, results=List(CU.scalarOut(x4239_b5098_x4248_b5100_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4239_b5099_x4248_b5101_s)))
    }
    val x4250 = MemoryController(name="x4250",parent=x4259,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4239_b5098_x4250 =  ScalarFIFO(name="offset",size=1).wtPort(x4239_b5098_x4248_b5100_s)
      val x4239_b5099_x4250 =  ScalarFIFO(name="size",size=1).wtPort(x4239_b5099_x4248_b5101_s)
      CU.newVout("data", x4240_x4250_data_v)
    }
    val x4258 = Pipeline(name="x4258",parent=x4259) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4252 = CounterChain(name = "x4252", ctr27)
      var stage: List[Stage] = Nil
    }
    val x4281 = StreamController(name="x4281",parent=x5052) { implicit CU => 
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4281_unit = CounterChain(name = "x4281_unit", ctr28)
    }
    val x4271 = Pipeline(name="x4271",parent=x4281) { implicit CU => 
      val x4264 = CU.temp
      val x4263 =  ScalarBuffer().wtPort(x4263_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4271_unit = CounterChain(name = "x4271_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4264))
      Stage(operands=List(x4264, CU.load(x4263)), op=FixAdd, results=List(CU.scalarOut(x4261_b5102_x4270_b5104_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4261_b5103_x4270_b5105_s)))
    }
    val x4272 = MemoryController(name="x4272",parent=x4281,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4261_b5103_x4272 =  ScalarFIFO(name="size",size=1).wtPort(x4261_b5103_x4270_b5105_s)
      val x4261_b5102_x4272 =  ScalarFIFO(name="offset",size=1).wtPort(x4261_b5102_x4270_b5104_s)
      CU.newVout("data", x4262_x4272_data_v)
    }
    val x4280 = Pipeline(name="x4280",parent=x4281) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4274 = CounterChain(name = "x4274", ctr30)
      var stage: List[Stage] = Nil
    }
    val x4302 = StreamController(name="x4302",parent=x5052) { implicit CU => 
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4302_unit = CounterChain(name = "x4302_unit", ctr31)
    }
    val x4292 = Pipeline(name="x4292",parent=x4302) { implicit CU => 
      val x4285 = CU.temp
      val x4284 =  ScalarBuffer().wtPort(x4284_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr32 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4292_unit = CounterChain(name = "x4292_unit", ctr32)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4285))
      Stage(operands=List(x4285, CU.load(x4284)), op=FixAdd, results=List(CU.scalarOut(x4282_b5106_x4291_b5108_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4282_b5107_x4291_b5109_s)))
    }
    val x4293 = MemoryController(name="x4293",parent=x4302,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4282_b5107_x4293 =  ScalarFIFO(name="size",size=1).wtPort(x4282_b5107_x4291_b5109_s)
      val x4282_b5106_x4293 =  ScalarFIFO(name="offset",size=1).wtPort(x4282_b5106_x4291_b5108_s)
      CU.newVout("data", x4283_x4293_data_v)
    }
    val x4301 = Pipeline(name="x4301",parent=x4302) { implicit CU => 
      val ctr33 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4295 = CounterChain(name = "x4295", ctr33)
      var stage: List[Stage] = Nil
    }
    val x4324 = StreamController(name="x4324",parent=x5052) { implicit CU => 
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4324_unit = CounterChain(name = "x4324_unit", ctr34)
    }
    val x4314 = Pipeline(name="x4314",parent=x4324) { implicit CU => 
      val x4307 = CU.temp
      val x4306 =  ScalarBuffer().wtPort(x4306_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr35 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4314_unit = CounterChain(name = "x4314_unit", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4307))
      Stage(operands=List(x4307, CU.load(x4306)), op=FixAdd, results=List(CU.scalarOut(x4304_b5110_x4313_b5112_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4304_b5111_x4313_b5113_s)))
    }
    val x4315 = MemoryController(name="x4315",parent=x4324,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4304_b5110_x4315 =  ScalarFIFO(name="offset",size=1).wtPort(x4304_b5110_x4313_b5112_s)
      val x4304_b5111_x4315 =  ScalarFIFO(name="size",size=1).wtPort(x4304_b5111_x4313_b5113_s)
      CU.newVout("data", x4305_x4315_data_v)
    }
    val x4323 = Pipeline(name="x4323",parent=x4324) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4317 = CounterChain(name = "x4317", ctr36)
      var stage: List[Stage] = Nil
    }
    val x4345 = StreamController(name="x4345",parent=x5052) { implicit CU => 
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4345_unit = CounterChain(name = "x4345_unit", ctr37)
    }
    val x4335 = Pipeline(name="x4335",parent=x4345) { implicit CU => 
      val x4328 = CU.temp
      val x4327 =  ScalarBuffer().wtPort(x4327_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4335_unit = CounterChain(name = "x4335_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4328))
      Stage(operands=List(x4328, CU.load(x4327)), op=FixAdd, results=List(CU.scalarOut(x4325_b5114_x4334_b5116_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4325_b5115_x4334_b5117_s)))
    }
    val x4336 = MemoryController(name="x4336",parent=x4345,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4325_b5115_x4336 =  ScalarFIFO(name="size",size=1).wtPort(x4325_b5115_x4334_b5117_s)
      val x4325_b5114_x4336 =  ScalarFIFO(name="offset",size=1).wtPort(x4325_b5114_x4334_b5116_s)
      CU.newVout("data", x4326_x4336_data_v)
    }
    val x4344 = Pipeline(name="x4344",parent=x4345) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4338 = CounterChain(name = "x4338", ctr39)
      var stage: List[Stage] = Nil
    }
    val x4367 = StreamController(name="x4367",parent=x5052) { implicit CU => 
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4367_unit = CounterChain(name = "x4367_unit", ctr40)
    }
    val x4357 = Pipeline(name="x4357",parent=x4367) { implicit CU => 
      val x4350 = CU.temp
      val x4349 =  ScalarBuffer().wtPort(x4349_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4357_unit = CounterChain(name = "x4357_unit", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4350))
      Stage(operands=List(x4350, CU.load(x4349)), op=FixAdd, results=List(CU.scalarOut(x4347_b5118_x4356_b5120_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4347_b5119_x4356_b5121_s)))
    }
    val x4358 = MemoryController(name="x4358",parent=x4367,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4347_b5119_x4358 =  ScalarFIFO(name="size",size=1).wtPort(x4347_b5119_x4356_b5121_s)
      val x4347_b5118_x4358 =  ScalarFIFO(name="offset",size=1).wtPort(x4347_b5118_x4356_b5120_s)
      CU.newVout("data", x4348_x4358_data_v)
    }
    val x4366 = Pipeline(name="x4366",parent=x4367) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4360 = CounterChain(name = "x4360", ctr42)
      var stage: List[Stage] = Nil
    }
    val x4388 = StreamController(name="x4388",parent=x5052) { implicit CU => 
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4388_unit = CounterChain(name = "x4388_unit", ctr43)
    }
    val x4378 = Pipeline(name="x4378",parent=x4388) { implicit CU => 
      val x4371 = CU.temp
      val x4370 =  ScalarBuffer().wtPort(x4370_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4378_unit = CounterChain(name = "x4378_unit", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4371))
      Stage(operands=List(x4371, CU.load(x4370)), op=FixAdd, results=List(CU.scalarOut(x4368_b5122_x4377_b5124_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4368_b5123_x4377_b5125_s)))
    }
    val x4379 = MemoryController(name="x4379",parent=x4388,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4368_b5122_x4379 =  ScalarFIFO(name="offset",size=1).wtPort(x4368_b5122_x4377_b5124_s)
      val x4368_b5123_x4379 =  ScalarFIFO(name="size",size=1).wtPort(x4368_b5123_x4377_b5125_s)
      CU.newVout("data", x4369_x4379_data_v)
    }
    val x4387 = Pipeline(name="x4387",parent=x4388) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4381 = CounterChain(name = "x4381", ctr45)
      var stage: List[Stage] = Nil
    }
    val x4410 = StreamController(name="x4410",parent=x5052) { implicit CU => 
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4410_unit = CounterChain(name = "x4410_unit", ctr46)
    }
    val x4400 = Pipeline(name="x4400",parent=x4410) { implicit CU => 
      val x4393 = CU.temp
      val x4392 =  ScalarBuffer().wtPort(x4392_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr47 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4400_unit = CounterChain(name = "x4400_unit", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4393))
      Stage(operands=List(x4393, CU.load(x4392)), op=FixAdd, results=List(CU.scalarOut(x4390_b5126_x4399_b5128_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4390_b5127_x4399_b5129_s)))
    }
    val x4401 = MemoryController(name="x4401",parent=x4410,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4390_b5127_x4401 =  ScalarFIFO(name="size",size=1).wtPort(x4390_b5127_x4399_b5129_s)
      val x4390_b5126_x4401 =  ScalarFIFO(name="offset",size=1).wtPort(x4390_b5126_x4399_b5128_s)
      CU.newVout("data", x4391_x4401_data_v)
    }
    val x4409 = Pipeline(name="x4409",parent=x4410) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4403 = CounterChain(name = "x4403", ctr48)
      var stage: List[Stage] = Nil
    }
    val x4431 = StreamController(name="x4431",parent=x5052) { implicit CU => 
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4431_unit = CounterChain(name = "x4431_unit", ctr49)
    }
    val x4421 = Pipeline(name="x4421",parent=x4431) { implicit CU => 
      val x4414 = CU.temp
      val x4413 =  ScalarBuffer().wtPort(x4413_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4421_unit = CounterChain(name = "x4421_unit", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4414))
      Stage(operands=List(x4414, CU.load(x4413)), op=FixAdd, results=List(CU.scalarOut(x4411_b5130_x4420_b5132_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4411_b5131_x4420_b5133_s)))
    }
    val x4422 = MemoryController(name="x4422",parent=x4431,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4411_b5131_x4422 =  ScalarFIFO(name="size",size=1).wtPort(x4411_b5131_x4420_b5133_s)
      val x4411_b5130_x4422 =  ScalarFIFO(name="offset",size=1).wtPort(x4411_b5130_x4420_b5132_s)
      CU.newVout("data", x4412_x4422_data_v)
    }
    val x4430 = Pipeline(name="x4430",parent=x4431) { implicit CU => 
      val ctr51 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4424 = CounterChain(name = "x4424", ctr51)
      var stage: List[Stage] = Nil
    }
    val x4453 = StreamController(name="x4453",parent=x5052) { implicit CU => 
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4453_unit = CounterChain(name = "x4453_unit", ctr52)
    }
    val x4443 = Pipeline(name="x4443",parent=x4453) { implicit CU => 
      val x4436 = CU.temp
      val x4435 =  ScalarBuffer().wtPort(x4435_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr53 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4443_unit = CounterChain(name = "x4443_unit", ctr53)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4436))
      Stage(operands=List(x4436, CU.load(x4435)), op=FixAdd, results=List(CU.scalarOut(x4433_b5134_x4442_b5136_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4433_b5135_x4442_b5137_s)))
    }
    val x4444 = MemoryController(name="x4444",parent=x4453,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4433_b5134_x4444 =  ScalarFIFO(name="offset",size=1).wtPort(x4433_b5134_x4442_b5136_s)
      val x4433_b5135_x4444 =  ScalarFIFO(name="size",size=1).wtPort(x4433_b5135_x4442_b5137_s)
      CU.newVout("data", x4434_x4444_data_v)
    }
    val x4452 = Pipeline(name="x4452",parent=x4453) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4446 = CounterChain(name = "x4446", ctr54)
      var stage: List[Stage] = Nil
    }
    val x4474 = StreamController(name="x4474",parent=x5052) { implicit CU => 
      val ctr55 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4474_unit = CounterChain(name = "x4474_unit", ctr55)
    }
    val x4464 = Pipeline(name="x4464",parent=x4474) { implicit CU => 
      val x4457 = CU.temp
      val x4456 =  ScalarBuffer().wtPort(x4456_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr56 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4464_unit = CounterChain(name = "x4464_unit", ctr56)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4457))
      Stage(operands=List(x4457, CU.load(x4456)), op=FixAdd, results=List(CU.scalarOut(x4454_b5138_x4463_b5140_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4454_b5139_x4463_b5141_s)))
    }
    val x4465 = MemoryController(name="x4465",parent=x4474,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4454_b5139_x4465 =  ScalarFIFO(name="size",size=1).wtPort(x4454_b5139_x4463_b5141_s)
      val x4454_b5138_x4465 =  ScalarFIFO(name="offset",size=1).wtPort(x4454_b5138_x4463_b5140_s)
      CU.newVout("data", x4455_x4465_data_v)
    }
    val x4473 = Pipeline(name="x4473",parent=x4474) { implicit CU => 
      val ctr57 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4467 = CounterChain(name = "x4467", ctr57)
      var stage: List[Stage] = Nil
    }
    val x4496 = StreamController(name="x4496",parent=x5052) { implicit CU => 
      val ctr58 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4496_unit = CounterChain(name = "x4496_unit", ctr58)
    }
    val x4486 = Pipeline(name="x4486",parent=x4496) { implicit CU => 
      val x4479 = CU.temp
      val x4478 =  ScalarBuffer().wtPort(x4478_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr59 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4486_unit = CounterChain(name = "x4486_unit", ctr59)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), Const(4)), op=FixMul, results=List(x4479))
      Stage(operands=List(x4479, CU.load(x4478)), op=FixAdd, results=List(CU.scalarOut(x4476_b5142_x4485_b5144_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4476_b5143_x4485_b5145_s)))
    }
    val x4487 = MemoryController(name="x4487",parent=x4496,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4476_b5143_x4487 =  ScalarFIFO(name="size",size=1).wtPort(x4476_b5143_x4485_b5145_s)
      val x4476_b5142_x4487 =  ScalarFIFO(name="offset",size=1).wtPort(x4476_b5142_x4485_b5144_s)
      CU.newVout("data", x4477_x4487_data_v)
    }
    val x4495 = Pipeline(name="x4495",parent=x4496) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4489 = CounterChain(name = "x4489", ctr60)
      var stage: List[Stage] = Nil
    }
    val x4517 = StreamController(name="x4517",parent=x5052) { implicit CU => 
      val ctr61 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4517_unit = CounterChain(name = "x4517_unit", ctr61)
    }
    val x4507 = Pipeline(name="x4507",parent=x4517) { implicit CU => 
      val x4500 = CU.temp
      val x4499 =  ScalarBuffer().wtPort(x4499_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val ctr62 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4507_unit = CounterChain(name = "x4507_unit", ctr62)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(1)), Const(4)), op=FixMul, results=List(x4500))
      Stage(operands=List(x4500, CU.load(x4499)), op=FixAdd, results=List(CU.scalarOut(x4497_b5146_x4506_b5148_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4497_b5147_x4506_b5149_s)))
    }
    val x4508 = MemoryController(name="x4508",parent=x4517,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4497_b5146_x4508 =  ScalarFIFO(name="offset",size=1).wtPort(x4497_b5146_x4506_b5148_s)
      val x4497_b5147_x4508 =  ScalarFIFO(name="size",size=1).wtPort(x4497_b5147_x4506_b5149_s)
      CU.newVout("data", x4498_x4508_data_v)
    }
    val x4516 = Pipeline(name="x4516",parent=x4517) { implicit CU => 
      val ctr63 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4510 = CounterChain(name = "x4510", ctr63)
      var stage: List[Stage] = Nil
    }
    val x4532 = Pipeline(name="x4532",parent=x5052) { implicit CU => 
      val x4527_x4527 =  VectorFIFO(size=1).wtPort(x4069_x4527_x4532_v)
      val x4526_x4526 =  VectorFIFO(size=1).wtPort(x4059_x4526_x4532_v)
      val ctr64 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr65 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4522 = CounterChain(name = "x4522", ctr64, ctr65)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4526_x4526), CU.load(x4527_x4527)), op=FixMul, results=List(CU.vecOut(x4079_x4531_v)))
    }
    val x4545 = Pipeline(name="x4545",parent=x5052) { implicit CU => 
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4060_x4539_x4545_v)
      val x4540_x4540 =  VectorFIFO(size=1).wtPort(x4070_x4540_x4545_v)
      val ctr66 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr67 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4535 = CounterChain(name = "x4535", ctr66, ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4539_x4539), CU.load(x4540_x4540)), op=FixMul, results=List(CU.vecOut(x4080_x4544_v)))
    }
    val x4558 = Pipeline(name="x4558",parent=x5052) { implicit CU => 
      val x4553_x4553 =  VectorFIFO(size=1).wtPort(x4071_x4553_x4558_v)
      val x4552_x4552 =  VectorFIFO(size=1).wtPort(x4061_x4552_x4558_v)
      val ctr68 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr69 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4548 = CounterChain(name = "x4548", ctr68, ctr69)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4552_x4552), CU.load(x4553_x4553)), op=FixMul, results=List(CU.vecOut(x4081_x4557_v)))
    }
    val x4571 = Pipeline(name="x4571",parent=x5052) { implicit CU => 
      val x4566_x4566 =  VectorFIFO(size=1).wtPort(x4072_x4566_x4571_v)
      val x4565_x4565 =  VectorFIFO(size=1).wtPort(x4062_x4565_x4571_v)
      val ctr70 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr71 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4561 = CounterChain(name = "x4561", ctr70, ctr71)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4565_x4565), CU.load(x4566_x4566)), op=FixMul, results=List(CU.vecOut(x4082_x4570_v)))
    }
    val x4584 = Pipeline(name="x4584",parent=x5052) { implicit CU => 
      val x4578_x4578 =  VectorFIFO(size=1).wtPort(x4063_x4578_x4584_v)
      val x4579_x4579 =  VectorFIFO(size=1).wtPort(x4073_x4579_x4584_v)
      val ctr72 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr73 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4574 = CounterChain(name = "x4574", ctr72, ctr73)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4578_x4578), CU.load(x4579_x4579)), op=FixMul, results=List(CU.vecOut(x4083_x4583_v)))
    }
    val x4597 = Pipeline(name="x4597",parent=x5052) { implicit CU => 
      val x4592_x4592 =  VectorFIFO(size=1).wtPort(x4074_x4592_x4597_v)
      val x4591_x4591 =  VectorFIFO(size=1).wtPort(x4064_x4591_x4597_v)
      val ctr74 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr75 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4587 = CounterChain(name = "x4587", ctr74, ctr75)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4591_x4591), CU.load(x4592_x4592)), op=FixMul, results=List(CU.vecOut(x4084_x4596_v)))
    }
    val x4610 = Pipeline(name="x4610",parent=x5052) { implicit CU => 
      val x4605_x4605 =  VectorFIFO(size=1).wtPort(x4075_x4605_x4610_v)
      val x4604_x4604 =  VectorFIFO(size=1).wtPort(x4065_x4604_x4610_v)
      val ctr76 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr77 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4600 = CounterChain(name = "x4600", ctr76, ctr77)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4604_x4604), CU.load(x4605_x4605)), op=FixMul, results=List(CU.vecOut(x4085_x4609_v)))
    }
    val x4623 = Pipeline(name="x4623",parent=x5052) { implicit CU => 
      val x4617_x4617 =  VectorFIFO(size=1).wtPort(x4066_x4617_x4623_v)
      val x4618_x4618 =  VectorFIFO(size=1).wtPort(x4076_x4618_x4623_v)
      val ctr78 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr79 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4613 = CounterChain(name = "x4613", ctr78, ctr79)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4617_x4617), CU.load(x4618_x4618)), op=FixMul, results=List(CU.vecOut(x4086_x4622_v)))
    }
    val x4636 = Pipeline(name="x4636",parent=x5052) { implicit CU => 
      val x4631_x4631 =  VectorFIFO(size=1).wtPort(x4077_x4631_x4636_v)
      val x4630_x4630 =  VectorFIFO(size=1).wtPort(x4067_x4630_x4636_v)
      val ctr80 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr81 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4626 = CounterChain(name = "x4626", ctr80, ctr81)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4630_x4630), CU.load(x4631_x4631)), op=FixMul, results=List(CU.vecOut(x4087_x4635_v)))
    }
    val x4649 = Pipeline(name="x4649",parent=x5052) { implicit CU => 
      val x4644_x4644 =  VectorFIFO(size=1).wtPort(x4078_x4644_x4649_v)
      val x4643_x4643 =  VectorFIFO(size=1).wtPort(x4068_x4643_x4649_v)
      val ctr82 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr83 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4639 = CounterChain(name = "x4639", ctr82, ctr83)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4643_x4643), CU.load(x4644_x4644)), op=FixMul, results=List(CU.vecOut(x4088_x4648_v)))
    }
    val x4690 = StreamController(name="x4690",parent=x5052) { implicit CU => 
      val ctr84 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4652 = CounterChain(name = "x4652", ctr84)
    }
    val x4680 = Sequential(name="x4680",parent=x4690) { implicit CU => 
      val ctr85 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4680_unit = CounterChain(name = "x4680_unit", ctr85)
    }
    val x4669 = Pipeline(name="x4669",parent=x4680) { implicit CU => 
      val x4658 = CU.temp
      val x4659 = CU.temp
      val x4660 = CU.temp
      val x4661 = CU.temp
      val x4042_x4657 =  ScalarBuffer().wtPort(x4042_argin)
      val x4656 =  ScalarBuffer().wtPort(x4656_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4652 = CounterChain.copy("x4690", "x4652")
      val ctr86 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4669_unit = CounterChain(name = "x4669_unit", ctr86)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4652(0))), op=FixAdd, results=List(x4658))
      Stage(operands=List(x4658, CU.load(x4042_x4657)), op=FixMul, results=List(x4659))
      Stage(operands=List(x4659, CU.ctr(x4058(1))), op=FixAdd, results=List(x4660))
      Stage(operands=List(x4660, Const(4)), op=FixMul, results=List(x4661))
      Stage(operands=List(x4661, CU.load(x4656)), op=FixAdd, results=List(CU.scalarOut(x4653_b5170_x4668_b5172_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4653_b5171_x4668_b5173_s)))
    }
    val x4679 = Pipeline(name="x4679",parent=x4680) { implicit CU => 
      val ctr87 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4671 = CounterChain(name = "x4671", ctr87)
      var stage: List[Stage] = Nil
    }
    val x4681 = MemoryController(name="x4681",parent=x4690,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4653_b5170_x4681 =  ScalarFIFO(name="offset",size=1).wtPort(x4653_b5170_x4668_b5172_s)
      val x4654_x4681 =  VectorFIFO(name="data",size=1).wtPort(x4079_x4675_x4679_v)
      val x4653_b5171_x4681 =  ScalarFIFO(name="size",size=1).wtPort(x4653_b5171_x4668_b5173_s)
    }
    val x4730 = StreamController(name="x4730",parent=x5052) { implicit CU => 
      val ctr90 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4692 = CounterChain(name = "x4692", ctr90)
    }
    val x4720 = Sequential(name="x4720",parent=x4730) { implicit CU => 
      val ctr91 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4720_unit = CounterChain(name = "x4720_unit", ctr91)
    }
    val x4709 = Pipeline(name="x4709",parent=x4720) { implicit CU => 
      val x4699 = CU.temp
      val x4700 = CU.temp
      val x4698 = CU.temp
      val x4701 = CU.temp
      val x4042_x4697 =  ScalarBuffer().wtPort(x4042_argin)
      val x4696 =  ScalarBuffer().wtPort(x4696_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4692 = CounterChain.copy("x4730", "x4692")
      val ctr92 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4709_unit = CounterChain(name = "x4709_unit", ctr92)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4692(0))), op=FixAdd, results=List(x4698))
      Stage(operands=List(x4698, CU.load(x4042_x4697)), op=FixMul, results=List(x4699))
      Stage(operands=List(x4699, CU.ctr(x4058(1))), op=FixAdd, results=List(x4700))
      Stage(operands=List(x4700, Const(4)), op=FixMul, results=List(x4701))
      Stage(operands=List(x4701, CU.load(x4696)), op=FixAdd, results=List(CU.scalarOut(x4693_b5176_x4708_b5178_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4693_b5177_x4708_b5179_s)))
    }
    val x4719 = Pipeline(name="x4719",parent=x4720) { implicit CU => 
      val ctr93 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4711 = CounterChain(name = "x4711", ctr93)
      var stage: List[Stage] = Nil
    }
    val x4721 = MemoryController(name="x4721",parent=x4730,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4693_b5176_x4721 =  ScalarFIFO(name="offset",size=1).wtPort(x4693_b5176_x4708_b5178_s)
      val x4694_x4721 =  VectorFIFO(name="data",size=1).wtPort(x4080_x4715_x4719_v)
      val x4693_b5177_x4721 =  ScalarFIFO(name="size",size=1).wtPort(x4693_b5177_x4708_b5179_s)
    }
    val x4770 = StreamController(name="x4770",parent=x5052) { implicit CU => 
      val ctr96 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4732 = CounterChain(name = "x4732", ctr96)
    }
    val x4760 = Sequential(name="x4760",parent=x4770) { implicit CU => 
      val ctr97 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4760_unit = CounterChain(name = "x4760_unit", ctr97)
    }
    val x4749 = Pipeline(name="x4749",parent=x4760) { implicit CU => 
      val x4739 = CU.temp
      val x4740 = CU.temp
      val x4738 = CU.temp
      val x4741 = CU.temp
      val x4042_x4737 =  ScalarBuffer().wtPort(x4042_argin)
      val x4736 =  ScalarBuffer().wtPort(x4736_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4732 = CounterChain.copy("x4770", "x4732")
      val ctr98 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4749_unit = CounterChain(name = "x4749_unit", ctr98)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4732(0))), op=FixAdd, results=List(x4738))
      Stage(operands=List(x4738, CU.load(x4042_x4737)), op=FixMul, results=List(x4739))
      Stage(operands=List(x4739, CU.ctr(x4058(1))), op=FixAdd, results=List(x4740))
      Stage(operands=List(x4740, Const(4)), op=FixMul, results=List(x4741))
      Stage(operands=List(x4741, CU.load(x4736)), op=FixAdd, results=List(CU.scalarOut(x4733_b5182_x4748_b5184_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4733_b5183_x4748_b5185_s)))
    }
    val x4759 = Pipeline(name="x4759",parent=x4760) { implicit CU => 
      val ctr99 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4751 = CounterChain(name = "x4751", ctr99)
      var stage: List[Stage] = Nil
    }
    val x4761 = MemoryController(name="x4761",parent=x4770,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4733_b5182_x4761 =  ScalarFIFO(name="offset",size=1).wtPort(x4733_b5182_x4748_b5184_s)
      val x4733_b5183_x4761 =  ScalarFIFO(name="size",size=1).wtPort(x4733_b5183_x4748_b5185_s)
      val x4734_x4761 =  VectorFIFO(name="data",size=1).wtPort(x4081_x4755_x4759_v)
    }
    val x4810 = StreamController(name="x4810",parent=x5052) { implicit CU => 
      val ctr102 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4772 = CounterChain(name = "x4772", ctr102)
    }
    val x4800 = Sequential(name="x4800",parent=x4810) { implicit CU => 
      val ctr103 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4800_unit = CounterChain(name = "x4800_unit", ctr103)
    }
    val x4789 = Pipeline(name="x4789",parent=x4800) { implicit CU => 
      val x4781 = CU.temp
      val x4778 = CU.temp
      val x4780 = CU.temp
      val x4779 = CU.temp
      val x4042_x4777 =  ScalarBuffer().wtPort(x4042_argin)
      val x4776 =  ScalarBuffer().wtPort(x4776_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4772 = CounterChain.copy("x4810", "x4772")
      val ctr104 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4789_unit = CounterChain(name = "x4789_unit", ctr104)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4772(0))), op=FixAdd, results=List(x4778))
      Stage(operands=List(x4778, CU.load(x4042_x4777)), op=FixMul, results=List(x4779))
      Stage(operands=List(x4779, CU.ctr(x4058(1))), op=FixAdd, results=List(x4780))
      Stage(operands=List(x4780, Const(4)), op=FixMul, results=List(x4781))
      Stage(operands=List(x4781, CU.load(x4776)), op=FixAdd, results=List(CU.scalarOut(x4773_b5188_x4788_b5190_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4773_b5189_x4788_b5191_s)))
    }
    val x4799 = Pipeline(name="x4799",parent=x4800) { implicit CU => 
      val ctr105 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4791 = CounterChain(name = "x4791", ctr105)
      var stage: List[Stage] = Nil
    }
    val x4801 = MemoryController(name="x4801",parent=x4810,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4773_b5188_x4801 =  ScalarFIFO(name="offset",size=1).wtPort(x4773_b5188_x4788_b5190_s)
      val x4774_x4801 =  VectorFIFO(name="data",size=1).wtPort(x4082_x4795_x4799_v)
      val x4773_b5189_x4801 =  ScalarFIFO(name="size",size=1).wtPort(x4773_b5189_x4788_b5191_s)
    }
    val x4850 = StreamController(name="x4850",parent=x5052) { implicit CU => 
      val ctr108 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4812 = CounterChain(name = "x4812", ctr108)
    }
    val x4840 = Sequential(name="x4840",parent=x4850) { implicit CU => 
      val ctr109 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4840_unit = CounterChain(name = "x4840_unit", ctr109)
    }
    val x4829 = Pipeline(name="x4829",parent=x4840) { implicit CU => 
      val x4820 = CU.temp
      val x4819 = CU.temp
      val x4818 = CU.temp
      val x4821 = CU.temp
      val x4042_x4817 =  ScalarBuffer().wtPort(x4042_argin)
      val x4816 =  ScalarBuffer().wtPort(x4816_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4812 = CounterChain.copy("x4850", "x4812")
      val ctr110 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4829_unit = CounterChain(name = "x4829_unit", ctr110)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4812(0))), op=FixAdd, results=List(x4818))
      Stage(operands=List(x4818, CU.load(x4042_x4817)), op=FixMul, results=List(x4819))
      Stage(operands=List(x4819, CU.ctr(x4058(1))), op=FixAdd, results=List(x4820))
      Stage(operands=List(x4820, Const(4)), op=FixMul, results=List(x4821))
      Stage(operands=List(x4821, CU.load(x4816)), op=FixAdd, results=List(CU.scalarOut(x4813_b5194_x4828_b5196_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4813_b5195_x4828_b5197_s)))
    }
    val x4839 = Pipeline(name="x4839",parent=x4840) { implicit CU => 
      val ctr111 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4831 = CounterChain(name = "x4831", ctr111)
      var stage: List[Stage] = Nil
    }
    val x4841 = MemoryController(name="x4841",parent=x4850,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4814_x4841 =  VectorFIFO(name="data",size=1).wtPort(x4083_x4835_x4839_v)
      val x4813_b5194_x4841 =  ScalarFIFO(name="offset",size=1).wtPort(x4813_b5194_x4828_b5196_s)
      val x4813_b5195_x4841 =  ScalarFIFO(name="size",size=1).wtPort(x4813_b5195_x4828_b5197_s)
    }
    val x4890 = StreamController(name="x4890",parent=x5052) { implicit CU => 
      val ctr114 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4852 = CounterChain(name = "x4852", ctr114)
    }
    val x4880 = Sequential(name="x4880",parent=x4890) { implicit CU => 
      val ctr115 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4880_unit = CounterChain(name = "x4880_unit", ctr115)
    }
    val x4869 = Pipeline(name="x4869",parent=x4880) { implicit CU => 
      val x4858 = CU.temp
      val x4859 = CU.temp
      val x4861 = CU.temp
      val x4860 = CU.temp
      val x4856 =  ScalarBuffer().wtPort(x4856_argin)
      val x4042_x4857 =  ScalarBuffer().wtPort(x4042_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4852 = CounterChain.copy("x4890", "x4852")
      val ctr116 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4869_unit = CounterChain(name = "x4869_unit", ctr116)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4852(0))), op=FixAdd, results=List(x4858))
      Stage(operands=List(x4858, CU.load(x4042_x4857)), op=FixMul, results=List(x4859))
      Stage(operands=List(x4859, CU.ctr(x4058(1))), op=FixAdd, results=List(x4860))
      Stage(operands=List(x4860, Const(4)), op=FixMul, results=List(x4861))
      Stage(operands=List(x4861, CU.load(x4856)), op=FixAdd, results=List(CU.scalarOut(x4853_b5200_x4868_b5202_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4853_b5201_x4868_b5203_s)))
    }
    val x4879 = Pipeline(name="x4879",parent=x4880) { implicit CU => 
      val ctr117 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4871 = CounterChain(name = "x4871", ctr117)
      var stage: List[Stage] = Nil
    }
    val x4881 = MemoryController(name="x4881",parent=x4890,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4853_b5200_x4881 =  ScalarFIFO(name="offset",size=1).wtPort(x4853_b5200_x4868_b5202_s)
      val x4853_b5201_x4881 =  ScalarFIFO(name="size",size=1).wtPort(x4853_b5201_x4868_b5203_s)
      val x4854_x4881 =  VectorFIFO(name="data",size=1).wtPort(x4084_x4875_x4879_v)
    }
    val x4930 = StreamController(name="x4930",parent=x5052) { implicit CU => 
      val ctr120 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4892 = CounterChain(name = "x4892", ctr120)
    }
    val x4920 = Sequential(name="x4920",parent=x4930) { implicit CU => 
      val ctr121 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4920_unit = CounterChain(name = "x4920_unit", ctr121)
    }
    val x4909 = Pipeline(name="x4909",parent=x4920) { implicit CU => 
      val x4900 = CU.temp
      val x4898 = CU.temp
      val x4901 = CU.temp
      val x4899 = CU.temp
      val x4042_x4897 =  ScalarBuffer().wtPort(x4042_argin)
      val x4896 =  ScalarBuffer().wtPort(x4896_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4892 = CounterChain.copy("x4930", "x4892")
      val ctr122 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4909_unit = CounterChain(name = "x4909_unit", ctr122)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4892(0))), op=FixAdd, results=List(x4898))
      Stage(operands=List(x4898, CU.load(x4042_x4897)), op=FixMul, results=List(x4899))
      Stage(operands=List(x4899, CU.ctr(x4058(1))), op=FixAdd, results=List(x4900))
      Stage(operands=List(x4900, Const(4)), op=FixMul, results=List(x4901))
      Stage(operands=List(x4901, CU.load(x4896)), op=FixAdd, results=List(CU.scalarOut(x4893_b5206_x4908_b5208_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4893_b5207_x4908_b5209_s)))
    }
    val x4919 = Pipeline(name="x4919",parent=x4920) { implicit CU => 
      val ctr123 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4911 = CounterChain(name = "x4911", ctr123)
      var stage: List[Stage] = Nil
    }
    val x4921 = MemoryController(name="x4921",parent=x4930,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4893_b5206_x4921 =  ScalarFIFO(name="offset",size=1).wtPort(x4893_b5206_x4908_b5208_s)
      val x4894_x4921 =  VectorFIFO(name="data",size=1).wtPort(x4085_x4915_x4919_v)
      val x4893_b5207_x4921 =  ScalarFIFO(name="size",size=1).wtPort(x4893_b5207_x4908_b5209_s)
    }
    val x4970 = StreamController(name="x4970",parent=x5052) { implicit CU => 
      val ctr126 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4932 = CounterChain(name = "x4932", ctr126)
    }
    val x4960 = Sequential(name="x4960",parent=x4970) { implicit CU => 
      val ctr127 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4960_unit = CounterChain(name = "x4960_unit", ctr127)
    }
    val x4949 = Pipeline(name="x4949",parent=x4960) { implicit CU => 
      val x4939 = CU.temp
      val x4940 = CU.temp
      val x4938 = CU.temp
      val x4941 = CU.temp
      val x4042_x4937 =  ScalarBuffer().wtPort(x4042_argin)
      val x4936 =  ScalarBuffer().wtPort(x4936_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4932 = CounterChain.copy("x4970", "x4932")
      val ctr128 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4949_unit = CounterChain(name = "x4949_unit", ctr128)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4932(0))), op=FixAdd, results=List(x4938))
      Stage(operands=List(x4938, CU.load(x4042_x4937)), op=FixMul, results=List(x4939))
      Stage(operands=List(x4939, CU.ctr(x4058(1))), op=FixAdd, results=List(x4940))
      Stage(operands=List(x4940, Const(4)), op=FixMul, results=List(x4941))
      Stage(operands=List(x4941, CU.load(x4936)), op=FixAdd, results=List(CU.scalarOut(x4933_b5212_x4948_b5214_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4933_b5213_x4948_b5215_s)))
    }
    val x4959 = Pipeline(name="x4959",parent=x4960) { implicit CU => 
      val ctr129 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4951 = CounterChain(name = "x4951", ctr129)
      var stage: List[Stage] = Nil
    }
    val x4961 = MemoryController(name="x4961",parent=x4970,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4933_b5212_x4961 =  ScalarFIFO(name="offset",size=1).wtPort(x4933_b5212_x4948_b5214_s)
      val x4933_b5213_x4961 =  ScalarFIFO(name="size",size=1).wtPort(x4933_b5213_x4948_b5215_s)
      val x4934_x4961 =  VectorFIFO(name="data",size=1).wtPort(x4086_x4955_x4959_v)
    }
    val x5010 = StreamController(name="x5010",parent=x5052) { implicit CU => 
      val ctr132 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x4972 = CounterChain(name = "x4972", ctr132)
    }
    val x5000 = Sequential(name="x5000",parent=x5010) { implicit CU => 
      val ctr133 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5000_unit = CounterChain(name = "x5000_unit", ctr133)
    }
    val x4989 = Pipeline(name="x4989",parent=x5000) { implicit CU => 
      val x4980 = CU.temp
      val x4978 = CU.temp
      val x4979 = CU.temp
      val x4981 = CU.temp
      val x4976 =  ScalarBuffer().wtPort(x4976_argin)
      val x4042_x4977 =  ScalarBuffer().wtPort(x4042_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x4972 = CounterChain.copy("x5010", "x4972")
      val ctr134 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4989_unit = CounterChain(name = "x4989_unit", ctr134)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x4972(0))), op=FixAdd, results=List(x4978))
      Stage(operands=List(x4978, CU.load(x4042_x4977)), op=FixMul, results=List(x4979))
      Stage(operands=List(x4979, CU.ctr(x4058(1))), op=FixAdd, results=List(x4980))
      Stage(operands=List(x4980, Const(4)), op=FixMul, results=List(x4981))
      Stage(operands=List(x4981, CU.load(x4976)), op=FixAdd, results=List(CU.scalarOut(x4973_b5218_x4988_b5220_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x4973_b5219_x4988_b5221_s)))
    }
    val x4999 = Pipeline(name="x4999",parent=x5000) { implicit CU => 
      val ctr135 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x4991 = CounterChain(name = "x4991", ctr135)
      var stage: List[Stage] = Nil
    }
    val x5001 = MemoryController(name="x5001",parent=x5010,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x4973_b5218_x5001 =  ScalarFIFO(name="offset",size=1).wtPort(x4973_b5218_x4988_b5220_s)
      val x4974_x5001 =  VectorFIFO(name="data",size=1).wtPort(x4087_x4995_x4999_v)
      val x4973_b5219_x5001 =  ScalarFIFO(name="size",size=1).wtPort(x4973_b5219_x4988_b5221_s)
    }
    val x5050 = StreamController(name="x5050",parent=x5052) { implicit CU => 
      val ctr138 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x5012 = CounterChain(name = "x5012", ctr138)
    }
    val x5040 = Sequential(name="x5040",parent=x5050) { implicit CU => 
      val ctr139 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5040_unit = CounterChain(name = "x5040_unit", ctr139)
    }
    val x5029 = Pipeline(name="x5029",parent=x5040) { implicit CU => 
      val x5020 = CU.temp
      val x5019 = CU.temp
      val x5021 = CU.temp
      val x5018 = CU.temp
      val x4042_x5017 =  ScalarBuffer().wtPort(x4042_argin)
      val x5016 =  ScalarBuffer().wtPort(x5016_argin)
      val x4058 = CounterChain.copy("x5052", "x4058")
      val x5012 = CounterChain.copy("x5050", "x5012")
      val ctr140 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5029_unit = CounterChain(name = "x5029_unit", ctr140)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4058(0)), CU.ctr(x5012(0))), op=FixAdd, results=List(x5018))
      Stage(operands=List(x5018, CU.load(x4042_x5017)), op=FixMul, results=List(x5019))
      Stage(operands=List(x5019, CU.ctr(x4058(1))), op=FixAdd, results=List(x5020))
      Stage(operands=List(x5020, Const(4)), op=FixMul, results=List(x5021))
      Stage(operands=List(x5021, CU.load(x5016)), op=FixAdd, results=List(CU.scalarOut(x5013_b5224_x5028_b5226_s)))
      Stage(operands=List(Const(256)), op=Bypass, results=List(CU.scalarOut(x5013_b5225_x5028_b5227_s)))
    }
    val x5039 = Pipeline(name="x5039",parent=x5040) { implicit CU => 
      val ctr141 = Counter(min=Const(0), max=Const(64), step=Const(1), par=16) // Counter
      val x5031 = CounterChain(name = "x5031", ctr141)
      var stage: List[Stage] = Nil
    }
    val x5041 = MemoryController(name="x5041",parent=x5050,offchip=x4051_oc, mctpe=TileStore) { implicit CU => 
      val x5013_b5224_x5041 =  ScalarFIFO(name="offset",size=1).wtPort(x5013_b5224_x5028_b5226_s)
      val x5014_x5041 =  VectorFIFO(name="data",size=1).wtPort(x4088_x5035_x5039_v)
      val x5013_b5225_x5041 =  ScalarFIFO(name="size",size=1).wtPort(x5013_b5225_x5028_b5227_s)
    }
    
  }
}
