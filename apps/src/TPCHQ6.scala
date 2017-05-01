import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object TPCHQ6 extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4081_x4856_x4878_v = Vector("x4081_x4856_x4878")
    val x4113_argin = ArgIn("x4113")
    val x4051_x4967_argout = ArgOut("x4051_x4967")
    val x4612_x4621_data_v = Vector("x4612_x4621_data")
    val x4284_b5033_x4292_b5035_s = Scalar("x4284_b5033_x4292_b5035")
    val x4131_x4140_data_v = Vector("x4131_x4140_data")
    val x4631_b5105_x4639_b5107_s = Scalar("x4631_b5105_x4639_b5107")
    val x4077_x4748_x4770_v = Vector("x4077_x4748_x4770")
    val x4554_b5089_x4562_b5091_s = Scalar("x4554_b5089_x4562_b5091")
    val x4050_oc = OffChip("x4050")
    val x4062_x4774_x4797_v = Vector("x4062_x4774_x4797")
    val x4342_b5046_x4350_b5048_s = Scalar("x4342_b5046_x4350_b5048")
    val x4477_b5073_x4485_b5075_s = Scalar("x4477_b5073_x4485_b5075")
    val x4285_x4294_data_v = Vector("x4285_x4294_data")
    val x4087_x4804_x4824_v = Vector("x4087_x4804_x4824")
    val x4284_b5034_x4292_b5036_s = Scalar("x4284_b5034_x4292_b5036")
    val x4226_b5021_x4234_b5023_s = Scalar("x4226_b5021_x4234_b5023")
    val x4303_b5037_x4311_b5039_s = Scalar("x4303_b5037_x4311_b5039")
    val x4068_x4722_x4743_v = Vector("x4068_x4722_x4743")
    val bus_2398_v = Vector("bus_2398")
    val x4534_b5085_x4542_b5087_s = Scalar("x4534_b5085_x4542_b5087")
    val x4671_argin = ArgIn("x4671")
    val x4207_b5018_x4215_b5020_s = Scalar("x4207_b5018_x4215_b5020")
    val x4652_argin = ArgIn("x4652")
    val x4074_x4884_x4905_v = Vector("x4074_x4884_x4905")
    val x4380_b5053_x4388_b5055_s = Scalar("x4380_b5053_x4388_b5055")
    val x4066_x4882_x4905_v = Vector("x4066_x4882_x4905")
    val x4536_argin = ArgIn("x4536")
    val bus_2389_v = Vector("bus_2389")
    val x4069_x4749_x4770_v = Vector("x4069_x4749_x4770")
    val bus_2440_v = Vector("bus_2440")
    val x4248_argin = ArgIn("x4248")
    val x4709_x4741_s = Scalar("x4709_x4741")
    val x4093_x4102_data_v = Vector("x4093_x4102_data")
    val x4082_x4883_x4905_v = Vector("x4082_x4883_x4905")
    val x4227_x4236_data_v = Vector("x4227_x4236_data")
    val bus_2469_s = Scalar("bus_2469")
    val x4344_argin = ArgIn("x4344")
    val x4515_b5082_x4523_b5084_s = Scalar("x4515_b5082_x4523_b5084")
    val x4246_b5026_x4254_b5028_s = Scalar("x4246_b5026_x4254_b5028")
    val x4208_x4217_data_v = Vector("x4208_x4217_data")
    val x4401_x4410_data_v = Vector("x4401_x4410_data")
    val x4479_argin = ArgIn("x4479")
    val x4593_x4602_data_v = Vector("x4593_x4602_data")
    val x4171_argin = ArgIn("x4171")
    val x4711_x4795_s = Scalar("x4711_x4795")
    val x4189_x4198_data_v = Vector("x4189_x4198_data")
    val x4362_x4371_data_v = Vector("x4362_x4371_data")
    val x4715_x4903_s = Scalar("x4715_x4903")
    val x4515_b5081_x4523_b5083_s = Scalar("x4515_b5081_x4523_b5083")
    val x4323_b5041_x4331_b5043_s = Scalar("x4323_b5041_x4331_b5043")
    val x4305_argin = ArgIn("x4305")
    val x4265_b5029_x4273_b5031_s = Scalar("x4265_b5029_x4273_b5031")
    val x4067_x4909_x4932_v = Vector("x4067_x4909_x4932")
    val x4573_b5094_x4581_b5096_s = Scalar("x4573_b5094_x4581_b5096")
    val x4497_x4506_data_v = Vector("x4497_x4506_data")
    val x4094_argin = ArgIn("x4094")
    val x4323_b5042_x4331_b5044_s = Scalar("x4323_b5042_x4331_b5044")
    val x4343_x4352_data_v = Vector("x4343_x4352_data")
    val bus_2449_v = Vector("bus_2449")
    val x4611_b5102_x4619_b5104_s = Scalar("x4611_b5102_x4619_b5104")
    val x4477_b5074_x4485_b5076_s = Scalar("x4477_b5074_x4485_b5076")
    val x4065_x4855_x4878_v = Vector("x4065_x4855_x4878")
    val x4111_b4998_x4119_b5000_s = Scalar("x4111_b4998_x4119_b5000")
    val x4688_b5118_x4696_b5120_s = Scalar("x4688_b5118_x4696_b5120")
    val x4651_x4660_data_v = Vector("x4651_x4660_data")
    val x4303_b5038_x4311_b5040_s = Scalar("x4303_b5038_x4311_b5040")
    val x4613_argin = ArgIn("x4613")
    val x4078_x4775_x4797_v = Vector("x4078_x4775_x4797")
    val x4594_argin = ArgIn("x4594")
    val x4265_b5030_x4273_b5032_s = Scalar("x4265_b5030_x4273_b5032")
    val x4633_argin = ArgIn("x4633")
    val x4170_x4179_data_v = Vector("x4170_x4179_data")
    val x4085_x4750_x4770_v = Vector("x4085_x4750_x4770")
    val x4496_b5078_x4504_b5080_s = Scalar("x4496_b5078_x4504_b5080")
    val x4112_x4121_data_v = Vector("x4112_x4121_data")
    val x4421_argin = ArgIn("x4421")
    val x4363_argin = ArgIn("x4363")
    val x4088_x4831_x4851_v = Vector("x4088_x4831_x4851")
    val x4092_b4993_x4100_b4995_s = Scalar("x4092_b4993_x4100_b4995")
    val x4286_argin = ArgIn("x4286")
    val x4402_argin = ArgIn("x4402")
    val x4041_argin = ArgIn("x4041")
    val x4064_x4828_x4851_v = Vector("x4064_x4828_x4851")
    val x4060_x4720_x4743_v = Vector("x4060_x4720_x4743")
    val x4061_x4747_x4770_v = Vector("x4061_x4747_x4770")
    val x4072_x4830_x4851_v = Vector("x4072_x4830_x4851")
    val bus_2364_v = Vector("bus_2364")
    val x4089_x4858_x4878_v = Vector("x4089_x4858_x4878")
    val x4266_x4275_data_v = Vector("x4266_x4275_data")
    val x4382_argin = ArgIn("x4382")
    val x4438_b5066_x4446_b5068_s = Scalar("x4438_b5066_x4446_b5068")
    val x4400_b5058_x4408_b5060_s = Scalar("x4400_b5058_x4408_b5060")
    val x4556_argin = ArgIn("x4556")
    val x4650_b5110_x4658_b5112_s = Scalar("x4650_b5110_x4658_b5112")
    val x4712_x4822_s = Scalar("x4712_x4822")
    val x4361_b5049_x4369_b5051_s = Scalar("x4361_b5049_x4369_b5051")
    val x4091_x4912_x4932_v = Vector("x4091_x4912_x4932")
    val x4516_x4525_data_v = Vector("x4516_x4525_data")
    val x4535_x4544_data_v = Vector("x4535_x4544_data")
    val bus_2381_v = Vector("bus_2381")
    val x4670_x4679_data_v = Vector("x4670_x4679_data")
    val bus_2355_v = Vector("bus_2355")
    val x4573_b5093_x4581_b5095_s = Scalar("x4573_b5093_x4581_b5095")
    val bus_2457_v = Vector("bus_2457")
    val x4044_oc = OffChip("x4044")
    val x4247_x4256_data_v = Vector("x4247_x4256_data")
    val x4342_b5045_x4350_b5047_s = Scalar("x4342_b5045_x4350_b5047")
    val x4439_x4448_data_v = Vector("x4439_x4448_data")
    val x4380_b5054_x4388_b5056_s = Scalar("x4380_b5054_x4388_b5056")
    val x4071_x4803_x4824_v = Vector("x4071_x4803_x4824")
    val bus_2372_v = Vector("bus_2372")
    val x4459_argin = ArgIn("x4459")
    val x4304_x4313_data_v = Vector("x4304_x4313_data")
    val x4438_b5065_x4446_b5067_s = Scalar("x4438_b5065_x4446_b5067")
    val x4457_b5069_x4465_b5071_s = Scalar("x4457_b5069_x4465_b5071")
    val x4478_x4487_data_v = Vector("x4478_x4487_data")
    val x4574_x4583_data_v = Vector("x4574_x4583_data")
    val x4632_x4641_data_v = Vector("x4632_x4641_data")
    val bus_2347_v = Vector("bus_2347")
    val x4555_x4564_data_v = Vector("x4555_x4564_data")
    val x4130_b5001_x4138_b5003_s = Scalar("x4130_b5001_x4138_b5003")
    val x4688_b5117_x4696_b5119_s = Scalar("x4688_b5117_x4696_b5119")
    val x4046_oc = OffChip("x4046")
    val x4086_x4777_x4797_v = Vector("x4086_x4777_x4797")
    val x4498_argin = ArgIn("x4498")
    val x4419_b5062_x4427_b5064_s = Scalar("x4419_b5062_x4427_b5064")
    val x4592_b5098_x4600_b5100_s = Scalar("x4592_b5098_x4600_b5100")
    val x4169_b5009_x4177_b5011_s = Scalar("x4169_b5009_x4177_b5011")
    val x4650_b5109_x4658_b5111_s = Scalar("x4650_b5109_x4658_b5111")
    val x4150_x4159_data_v = Vector("x4150_x4159_data")
    val x4458_x4467_data_v = Vector("x4458_x4467_data")
    val x4226_b5022_x4234_b5024_s = Scalar("x4226_b5022_x4234_b5024")
    val x4083_x4910_x4932_v = Vector("x4083_x4910_x4932")
    val x4517_argin = ArgIn("x4517")
    val x4063_x4801_x4824_v = Vector("x4063_x4801_x4824")
    val x4325_argin = ArgIn("x4325")
    val x4132_argin = ArgIn("x4132")
    val x4188_b5014_x4196_b5016_s = Scalar("x4188_b5014_x4196_b5016")
    val x4048_oc = OffChip("x4048")
    val x4190_argin = ArgIn("x4190")
    val bus_2432_v = Vector("bus_2432")
    val x4669_b5114_x4677_b5116_s = Scalar("x4669_b5114_x4677_b5116")
    val x4534_b5086_x4542_b5088_s = Scalar("x4534_b5086_x4542_b5088")
    val x4669_b5113_x4677_b5115_s = Scalar("x4669_b5113_x4677_b5115")
    val x4246_b5025_x4254_b5027_s = Scalar("x4246_b5025_x4254_b5027")
    val x4267_argin = ArgIn("x4267")
    val x4076_x4721_x4743_v = Vector("x4076_x4721_x4743")
    val x4381_x4390_data_v = Vector("x4381_x4390_data")
    val x4080_x4829_x4851_v = Vector("x4080_x4829_x4851")
    val x4690_argin = ArgIn("x4690")
    val x4592_b5097_x4600_b5099_s = Scalar("x4592_b5097_x4600_b5099")
    val x4361_b5050_x4369_b5052_s = Scalar("x4361_b5050_x4369_b5052")
    val x4440_argin = ArgIn("x4440")
    val x4554_b5090_x4562_b5092_s = Scalar("x4554_b5090_x4562_b5092")
    val x4419_b5061_x4427_b5063_s = Scalar("x4419_b5061_x4427_b5063")
    val bus_2338_v = Vector("bus_2338")
    val x4149_b5005_x4157_b5007_s = Scalar("x4149_b5005_x4157_b5007")
    val x4713_x4849_s = Scalar("x4713_x4849")
    val x4073_x4857_x4878_v = Vector("x4073_x4857_x4878")
    val x4090_x4885_x4905_v = Vector("x4090_x4885_x4905")
    val x4209_argin = ArgIn("x4209")
    val x4169_b5010_x4177_b5012_s = Scalar("x4169_b5010_x4177_b5012")
    val x4457_b5070_x4465_b5072_s = Scalar("x4457_b5070_x4465_b5072")
    val x4111_b4997_x4119_b4999_s = Scalar("x4111_b4997_x4119_b4999")
    val x4149_b5006_x4157_b5008_s = Scalar("x4149_b5006_x4157_b5008")
    val x4079_x4802_x4824_v = Vector("x4079_x4802_x4824")
    val x4092_b4994_x4100_b4996_s = Scalar("x4092_b4994_x4100_b4996")
    val x4611_b5101_x4619_b5103_s = Scalar("x4611_b5101_x4619_b5103")
    val x4324_x4333_data_v = Vector("x4324_x4333_data")
    val x4496_b5077_x4504_b5079_s = Scalar("x4496_b5077_x4504_b5079")
    val x4075_x4911_x4932_v = Vector("x4075_x4911_x4932")
    val x4207_b5017_x4215_b5019_s = Scalar("x4207_b5017_x4215_b5019")
    val x4084_x4723_x4743_v = Vector("x4084_x4723_x4743")
    val bus_2463_s = Scalar("bus_2463")
    val x4714_x4876_s = Scalar("x4714_x4876")
    val bus_2415_v = Vector("bus_2415")
    val x4420_x4429_data_v = Vector("x4420_x4429_data")
    val x4400_b5057_x4408_b5059_s = Scalar("x4400_b5057_x4408_b5059")
    val x4575_argin = ArgIn("x4575")
    val x4130_b5002_x4138_b5004_s = Scalar("x4130_b5002_x4138_b5004")
    val x4151_argin = ArgIn("x4151")
    val bus_2330_v = Vector("bus_2330")
    val x4070_x4776_x4797_v = Vector("x4070_x4776_x4797")
    val x4228_argin = ArgIn("x4228")
    val bus_2406_v = Vector("bus_2406")
    val x4689_x4698_data_v = Vector("x4689_x4698_data")
    val x4188_b5013_x4196_b5015_s = Scalar("x4188_b5013_x4196_b5015")
    val x4631_b5106_x4639_b5108_s = Scalar("x4631_b5106_x4639_b5108")
    val x4710_x4768_s = Scalar("x4710_x4768")
    val x4716_x4930_s = Scalar("x4716_x4930")
    val bus_2423_v = Vector("bus_2423")
    val x4969 = Sequential(name="x4969",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4969_unit = CounterChain(name = "x4969_unit", ctr1)
    }
    val x4965 = MetaPipeline(name="x4965",parent=x4969) { implicit CU => 
      val x4041_x4057 =  ScalarBuffer().wtPort(x4041_argin)
      val ctr2 = Counter(min=Const(0), max=x4041_x4057.load, step=Const(2000), par=8) // Counter
      val x4059 = CounterChain(name = "x4059", ctr2)
    }
    val x4060_dsp0 = MemoryPipeline(name="x4060_dsp0",parent="x4965") { implicit CU => 
      val x4108_x4108 =  VectorFIFO(size=1).wtPort(x4093_x4102_data_v)
      val x4104 = CounterChain.copy("x4109", "x4104")
      val x4718 = CounterChain.copy("x4743", "x4718")
      val x4060_x4720 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4108_x4108.readPort).rdPort(x4060_x4720_x4743_v).rdAddr(x4718(0)).wtAddr(x4104(0))
      var stage: List[Stage] = Nil
    }
    val x4061_dsp0 = MemoryPipeline(name="x4061_dsp0",parent="x4965") { implicit CU => 
      val x4185_x4185 =  VectorFIFO(size=1).wtPort(x4170_x4179_data_v)
      val x4181 = CounterChain.copy("x4186", "x4181")
      val x4745 = CounterChain.copy("x4770", "x4745")
      val x4061_x4747 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4185_x4185.readPort).rdPort(x4061_x4747_x4770_v).rdAddr(x4745(0)).wtAddr(x4181(0))
      var stage: List[Stage] = Nil
    }
    val x4062_dsp0 = MemoryPipeline(name="x4062_dsp0",parent="x4965") { implicit CU => 
      val x4262_x4262 =  VectorFIFO(size=1).wtPort(x4247_x4256_data_v)
      val x4258 = CounterChain.copy("x4263", "x4258")
      val x4772 = CounterChain.copy("x4797", "x4772")
      val x4062_x4774 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4262_x4262.readPort).rdPort(x4062_x4774_x4797_v).rdAddr(x4772(0)).wtAddr(x4258(0))
      var stage: List[Stage] = Nil
    }
    val x4063_dsp0 = MemoryPipeline(name="x4063_dsp0",parent="x4965") { implicit CU => 
      val x4339_x4339 =  VectorFIFO(size=1).wtPort(x4324_x4333_data_v)
      val x4335 = CounterChain.copy("x4340", "x4335")
      val x4799 = CounterChain.copy("x4824", "x4799")
      val x4063_x4801 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4339_x4339.readPort).rdPort(x4063_x4801_x4824_v).rdAddr(x4799(0)).wtAddr(x4335(0))
      var stage: List[Stage] = Nil
    }
    val x4064_dsp0 = MemoryPipeline(name="x4064_dsp0",parent="x4965") { implicit CU => 
      val x4416_x4416 =  VectorFIFO(size=1).wtPort(x4401_x4410_data_v)
      val x4412 = CounterChain.copy("x4417", "x4412")
      val x4826 = CounterChain.copy("x4851", "x4826")
      val x4064_x4828 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4416_x4416.readPort).rdPort(x4064_x4828_x4851_v).rdAddr(x4826(0)).wtAddr(x4412(0))
      var stage: List[Stage] = Nil
    }
    val x4065_dsp0 = MemoryPipeline(name="x4065_dsp0",parent="x4965") { implicit CU => 
      val x4493_x4493 =  VectorFIFO(size=1).wtPort(x4478_x4487_data_v)
      val x4489 = CounterChain.copy("x4494", "x4489")
      val x4853 = CounterChain.copy("x4878", "x4853")
      val x4065_x4855 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4493_x4493.readPort).rdPort(x4065_x4855_x4878_v).rdAddr(x4853(0)).wtAddr(x4489(0))
      var stage: List[Stage] = Nil
    }
    val x4066_dsp0 = MemoryPipeline(name="x4066_dsp0",parent="x4965") { implicit CU => 
      val x4570_x4570 =  VectorFIFO(size=1).wtPort(x4555_x4564_data_v)
      val x4566 = CounterChain.copy("x4571", "x4566")
      val x4880 = CounterChain.copy("x4905", "x4880")
      val x4066_x4882 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4570_x4570.readPort).rdPort(x4066_x4882_x4905_v).rdAddr(x4880(0)).wtAddr(x4566(0))
      var stage: List[Stage] = Nil
    }
    val x4067_dsp0 = MemoryPipeline(name="x4067_dsp0",parent="x4965") { implicit CU => 
      val x4647_x4647 =  VectorFIFO(size=1).wtPort(x4632_x4641_data_v)
      val x4643 = CounterChain.copy("x4648", "x4643")
      val x4907 = CounterChain.copy("x4932", "x4907")
      val x4067_x4909 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4647_x4647.readPort).rdPort(x4067_x4909_x4932_v).rdAddr(x4907(0)).wtAddr(x4643(0))
      var stage: List[Stage] = Nil
    }
    val x4068_dsp0 = MemoryPipeline(name="x4068_dsp0",parent="x4965") { implicit CU => 
      val x4127_x4127 =  VectorFIFO(size=1).wtPort(x4112_x4121_data_v)
      val x4123 = CounterChain.copy("x4128", "x4123")
      val x4718 = CounterChain.copy("x4743", "x4718")
      val x4068_x4722 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4127_x4127.readPort).rdPort(x4068_x4722_x4743_v).rdAddr(x4718(0)).wtAddr(x4123(0))
      var stage: List[Stage] = Nil
    }
    val x4069_dsp0 = MemoryPipeline(name="x4069_dsp0",parent="x4965") { implicit CU => 
      val x4204_x4204 =  VectorFIFO(size=1).wtPort(x4189_x4198_data_v)
      val x4200 = CounterChain.copy("x4205", "x4200")
      val x4745 = CounterChain.copy("x4770", "x4745")
      val x4069_x4749 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4204_x4204.readPort).rdPort(x4069_x4749_x4770_v).rdAddr(x4745(0)).wtAddr(x4200(0))
      var stage: List[Stage] = Nil
    }
    val x4070_dsp0 = MemoryPipeline(name="x4070_dsp0",parent="x4965") { implicit CU => 
      val x4281_x4281 =  VectorFIFO(size=1).wtPort(x4266_x4275_data_v)
      val x4277 = CounterChain.copy("x4282", "x4277")
      val x4772 = CounterChain.copy("x4797", "x4772")
      val x4070_x4776 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4281_x4281.readPort).rdPort(x4070_x4776_x4797_v).rdAddr(x4772(0)).wtAddr(x4277(0))
      var stage: List[Stage] = Nil
    }
    val x4071_dsp0 = MemoryPipeline(name="x4071_dsp0",parent="x4965") { implicit CU => 
      val x4358_x4358 =  VectorFIFO(size=1).wtPort(x4343_x4352_data_v)
      val x4354 = CounterChain.copy("x4359", "x4354")
      val x4799 = CounterChain.copy("x4824", "x4799")
      val x4071_x4803 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4358_x4358.readPort).rdPort(x4071_x4803_x4824_v).rdAddr(x4799(0)).wtAddr(x4354(0))
      var stage: List[Stage] = Nil
    }
    val x4072_dsp0 = MemoryPipeline(name="x4072_dsp0",parent="x4965") { implicit CU => 
      val x4435_x4435 =  VectorFIFO(size=1).wtPort(x4420_x4429_data_v)
      val x4431 = CounterChain.copy("x4436", "x4431")
      val x4826 = CounterChain.copy("x4851", "x4826")
      val x4072_x4830 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4435_x4435.readPort).rdPort(x4072_x4830_x4851_v).rdAddr(x4826(0)).wtAddr(x4431(0))
      var stage: List[Stage] = Nil
    }
    val x4073_dsp0 = MemoryPipeline(name="x4073_dsp0",parent="x4965") { implicit CU => 
      val x4512_x4512 =  VectorFIFO(size=1).wtPort(x4497_x4506_data_v)
      val x4508 = CounterChain.copy("x4513", "x4508")
      val x4853 = CounterChain.copy("x4878", "x4853")
      val x4073_x4857 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4512_x4512.readPort).rdPort(x4073_x4857_x4878_v).rdAddr(x4853(0)).wtAddr(x4508(0))
      var stage: List[Stage] = Nil
    }
    val x4074_dsp0 = MemoryPipeline(name="x4074_dsp0",parent="x4965") { implicit CU => 
      val x4589_x4589 =  VectorFIFO(size=1).wtPort(x4574_x4583_data_v)
      val x4585 = CounterChain.copy("x4590", "x4585")
      val x4880 = CounterChain.copy("x4905", "x4880")
      val x4074_x4884 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4589_x4589.readPort).rdPort(x4074_x4884_x4905_v).rdAddr(x4880(0)).wtAddr(x4585(0))
      var stage: List[Stage] = Nil
    }
    val x4075_dsp0 = MemoryPipeline(name="x4075_dsp0",parent="x4965") { implicit CU => 
      val x4666_x4666 =  VectorFIFO(size=1).wtPort(x4651_x4660_data_v)
      val x4662 = CounterChain.copy("x4667", "x4662")
      val x4907 = CounterChain.copy("x4932", "x4907")
      val x4075_x4911 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4666_x4666.readPort).rdPort(x4075_x4911_x4932_v).rdAddr(x4907(0)).wtAddr(x4662(0))
      var stage: List[Stage] = Nil
    }
    val x4076_dsp0 = MemoryPipeline(name="x4076_dsp0",parent="x4965") { implicit CU => 
      val x4146_x4146 =  VectorFIFO(size=1).wtPort(x4131_x4140_data_v)
      val x4142 = CounterChain.copy("x4147", "x4142")
      val x4718 = CounterChain.copy("x4743", "x4718")
      val x4076_x4721 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4146_x4146.readPort).rdPort(x4076_x4721_x4743_v).rdAddr(x4718(0)).wtAddr(x4142(0))
      var stage: List[Stage] = Nil
    }
    val x4077_dsp0 = MemoryPipeline(name="x4077_dsp0",parent="x4965") { implicit CU => 
      val x4223_x4223 =  VectorFIFO(size=1).wtPort(x4208_x4217_data_v)
      val x4219 = CounterChain.copy("x4224", "x4219")
      val x4745 = CounterChain.copy("x4770", "x4745")
      val x4077_x4748 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4223_x4223.readPort).rdPort(x4077_x4748_x4770_v).rdAddr(x4745(0)).wtAddr(x4219(0))
      var stage: List[Stage] = Nil
    }
    val x4078_dsp0 = MemoryPipeline(name="x4078_dsp0",parent="x4965") { implicit CU => 
      val x4300_x4300 =  VectorFIFO(size=1).wtPort(x4285_x4294_data_v)
      val x4296 = CounterChain.copy("x4301", "x4296")
      val x4772 = CounterChain.copy("x4797", "x4772")
      val x4078_x4775 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4300_x4300.readPort).rdPort(x4078_x4775_x4797_v).rdAddr(x4772(0)).wtAddr(x4296(0))
      var stage: List[Stage] = Nil
    }
    val x4079_dsp0 = MemoryPipeline(name="x4079_dsp0",parent="x4965") { implicit CU => 
      val x4377_x4377 =  VectorFIFO(size=1).wtPort(x4362_x4371_data_v)
      val x4373 = CounterChain.copy("x4378", "x4373")
      val x4799 = CounterChain.copy("x4824", "x4799")
      val x4079_x4802 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4377_x4377.readPort).rdPort(x4079_x4802_x4824_v).rdAddr(x4799(0)).wtAddr(x4373(0))
      var stage: List[Stage] = Nil
    }
    val x4080_dsp0 = MemoryPipeline(name="x4080_dsp0",parent="x4965") { implicit CU => 
      val x4454_x4454 =  VectorFIFO(size=1).wtPort(x4439_x4448_data_v)
      val x4450 = CounterChain.copy("x4455", "x4450")
      val x4826 = CounterChain.copy("x4851", "x4826")
      val x4080_x4829 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4454_x4454.readPort).rdPort(x4080_x4829_x4851_v).rdAddr(x4826(0)).wtAddr(x4450(0))
      var stage: List[Stage] = Nil
    }
    val x4081_dsp0 = MemoryPipeline(name="x4081_dsp0",parent="x4965") { implicit CU => 
      val x4531_x4531 =  VectorFIFO(size=1).wtPort(x4516_x4525_data_v)
      val x4527 = CounterChain.copy("x4532", "x4527")
      val x4853 = CounterChain.copy("x4878", "x4853")
      val x4081_x4856 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4531_x4531.readPort).rdPort(x4081_x4856_x4878_v).rdAddr(x4853(0)).wtAddr(x4527(0))
      var stage: List[Stage] = Nil
    }
    val x4082_dsp0 = MemoryPipeline(name="x4082_dsp0",parent="x4965") { implicit CU => 
      val x4608_x4608 =  VectorFIFO(size=1).wtPort(x4593_x4602_data_v)
      val x4604 = CounterChain.copy("x4609", "x4604")
      val x4880 = CounterChain.copy("x4905", "x4880")
      val x4082_x4883 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4608_x4608.readPort).rdPort(x4082_x4883_x4905_v).rdAddr(x4880(0)).wtAddr(x4604(0))
      var stage: List[Stage] = Nil
    }
    val x4083_dsp0 = MemoryPipeline(name="x4083_dsp0",parent="x4965") { implicit CU => 
      val x4685_x4685 =  VectorFIFO(size=1).wtPort(x4670_x4679_data_v)
      val x4681 = CounterChain.copy("x4686", "x4681")
      val x4907 = CounterChain.copy("x4932", "x4907")
      val x4083_x4910 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4685_x4685.readPort).rdPort(x4083_x4910_x4932_v).rdAddr(x4907(0)).wtAddr(x4681(0))
      var stage: List[Stage] = Nil
    }
    val x4084_dsp0 = MemoryPipeline(name="x4084_dsp0",parent="x4965") { implicit CU => 
      val x4165_x4165 =  VectorFIFO(size=1).wtPort(x4150_x4159_data_v)
      val x4161 = CounterChain.copy("x4166", "x4161")
      val x4718 = CounterChain.copy("x4743", "x4718")
      val x4084_x4723 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4165_x4165.readPort).rdPort(x4084_x4723_x4743_v).rdAddr(x4718(0)).wtAddr(x4161(0))
      var stage: List[Stage] = Nil
    }
    val x4085_dsp0 = MemoryPipeline(name="x4085_dsp0",parent="x4965") { implicit CU => 
      val x4242_x4242 =  VectorFIFO(size=1).wtPort(x4227_x4236_data_v)
      val x4238 = CounterChain.copy("x4243", "x4238")
      val x4745 = CounterChain.copy("x4770", "x4745")
      val x4085_x4750 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4242_x4242.readPort).rdPort(x4085_x4750_x4770_v).rdAddr(x4745(0)).wtAddr(x4238(0))
      var stage: List[Stage] = Nil
    }
    val x4086_dsp0 = MemoryPipeline(name="x4086_dsp0",parent="x4965") { implicit CU => 
      val x4319_x4319 =  VectorFIFO(size=1).wtPort(x4304_x4313_data_v)
      val x4315 = CounterChain.copy("x4320", "x4315")
      val x4772 = CounterChain.copy("x4797", "x4772")
      val x4086_x4777 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4319_x4319.readPort).rdPort(x4086_x4777_x4797_v).rdAddr(x4772(0)).wtAddr(x4315(0))
      var stage: List[Stage] = Nil
    }
    val x4087_dsp0 = MemoryPipeline(name="x4087_dsp0",parent="x4965") { implicit CU => 
      val x4396_x4396 =  VectorFIFO(size=1).wtPort(x4381_x4390_data_v)
      val x4392 = CounterChain.copy("x4397", "x4392")
      val x4799 = CounterChain.copy("x4824", "x4799")
      val x4087_x4804 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4396_x4396.readPort).rdPort(x4087_x4804_x4824_v).rdAddr(x4799(0)).wtAddr(x4392(0))
      var stage: List[Stage] = Nil
    }
    val x4088_dsp0 = MemoryPipeline(name="x4088_dsp0",parent="x4965") { implicit CU => 
      val x4473_x4473 =  VectorFIFO(size=1).wtPort(x4458_x4467_data_v)
      val x4469 = CounterChain.copy("x4474", "x4469")
      val x4826 = CounterChain.copy("x4851", "x4826")
      val x4088_x4831 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4473_x4473.readPort).rdPort(x4088_x4831_x4851_v).rdAddr(x4826(0)).wtAddr(x4469(0))
      var stage: List[Stage] = Nil
    }
    val x4089_dsp0 = MemoryPipeline(name="x4089_dsp0",parent="x4965") { implicit CU => 
      val x4550_x4550 =  VectorFIFO(size=1).wtPort(x4535_x4544_data_v)
      val x4546 = CounterChain.copy("x4551", "x4546")
      val x4853 = CounterChain.copy("x4878", "x4853")
      val x4089_x4858 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4550_x4550.readPort).rdPort(x4089_x4858_x4878_v).rdAddr(x4853(0)).wtAddr(x4546(0))
      var stage: List[Stage] = Nil
    }
    val x4090_dsp0 = MemoryPipeline(name="x4090_dsp0",parent="x4965") { implicit CU => 
      val x4627_x4627 =  VectorFIFO(size=1).wtPort(x4612_x4621_data_v)
      val x4623 = CounterChain.copy("x4628", "x4623")
      val x4880 = CounterChain.copy("x4905", "x4880")
      val x4090_x4885 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4627_x4627.readPort).rdPort(x4090_x4885_x4905_v).rdAddr(x4880(0)).wtAddr(x4623(0))
      var stage: List[Stage] = Nil
    }
    val x4091_dsp0 = MemoryPipeline(name="x4091_dsp0",parent="x4965") { implicit CU => 
      val x4704_x4704 =  VectorFIFO(size=1).wtPort(x4689_x4698_data_v)
      val x4700 = CounterChain.copy("x4705", "x4700")
      val x4907 = CounterChain.copy("x4932", "x4907")
      val x4091_x4912 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4704_x4704.readPort).rdPort(x4091_x4912_x4932_v).rdAddr(x4907(0)).wtAddr(x4700(0))
      var stage: List[Stage] = Nil
    }
    val x4110 = StreamController(name="x4110",parent=x4965) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4110_unit = CounterChain(name = "x4110_unit", ctr3)
    }
    val x4101_0 = Pipeline(name="x4101_0",parent=x4110) { implicit CU => 
      val x4095 = CU.temp
      val x4094 =  ScalarBuffer().wtPort(x4094_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4101_unit = CounterChain(name = "x4101_unit", ctr4)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4095))
      Stage(operands=List(x4095, CU.load(x4094)), op=FixAdd, results=List(CU.scalarOut(x4092_b4993_x4100_b4995_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4092_b4994_x4100_b4996_s)))
    }
    val x4102 = MemoryController(name="x4102",parent=x4110,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4092_b4993_x4102 =  ScalarFIFO(name="offset",size=1).wtPort(x4092_b4993_x4100_b4995_s)
      val x4092_b4994_x4102 =  ScalarFIFO(name="size",size=1).wtPort(x4092_b4994_x4100_b4996_s)
      CU.newVout("data", x4093_x4102_data_v)
    }
    val x4109 = Pipeline(name="x4109",parent=x4110) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4104 = CounterChain(name = "x4104", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4129 = StreamController(name="x4129",parent=x4965) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4129_unit = CounterChain(name = "x4129_unit", ctr6)
    }
    val x4120_0 = Pipeline(name="x4120_0",parent=x4129) { implicit CU => 
      val x4114 = CU.temp
      val x4113 =  ScalarBuffer().wtPort(x4113_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4120_unit = CounterChain(name = "x4120_unit", ctr7)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4114))
      Stage(operands=List(x4114, CU.load(x4113)), op=FixAdd, results=List(CU.scalarOut(x4111_b4997_x4119_b4999_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4111_b4998_x4119_b5000_s)))
    }
    val x4121 = MemoryController(name="x4121",parent=x4129,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4111_b4998_x4121 =  ScalarFIFO(name="size",size=1).wtPort(x4111_b4998_x4119_b5000_s)
      val x4111_b4997_x4121 =  ScalarFIFO(name="offset",size=1).wtPort(x4111_b4997_x4119_b4999_s)
      CU.newVout("data", x4112_x4121_data_v)
    }
    val x4128 = Pipeline(name="x4128",parent=x4129) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4123 = CounterChain(name = "x4123", ctr8)
      var stage: List[Stage] = Nil
    }
    val x4148 = StreamController(name="x4148",parent=x4965) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4148_unit = CounterChain(name = "x4148_unit", ctr9)
    }
    val x4139_0 = Pipeline(name="x4139_0",parent=x4148) { implicit CU => 
      val x4133 = CU.temp
      val x4132 =  ScalarBuffer().wtPort(x4132_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4139_unit = CounterChain(name = "x4139_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4133))
      Stage(operands=List(x4133, CU.load(x4132)), op=FixAdd, results=List(CU.scalarOut(x4130_b5001_x4138_b5003_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4130_b5002_x4138_b5004_s)))
    }
    val x4140 = MemoryController(name="x4140",parent=x4148,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4130_b5002_x4140 =  ScalarFIFO(name="size",size=1).wtPort(x4130_b5002_x4138_b5004_s)
      val x4130_b5001_x4140 =  ScalarFIFO(name="offset",size=1).wtPort(x4130_b5001_x4138_b5003_s)
      CU.newVout("data", x4131_x4140_data_v)
    }
    val x4147 = Pipeline(name="x4147",parent=x4148) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4142 = CounterChain(name = "x4142", ctr11)
      var stage: List[Stage] = Nil
    }
    val x4167 = StreamController(name="x4167",parent=x4965) { implicit CU => 
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4167_unit = CounterChain(name = "x4167_unit", ctr12)
    }
    val x4158_0 = Pipeline(name="x4158_0",parent=x4167) { implicit CU => 
      val x4152 = CU.temp
      val x4151 =  ScalarBuffer().wtPort(x4151_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4158_unit = CounterChain(name = "x4158_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4152))
      Stage(operands=List(x4152, CU.load(x4151)), op=FixAdd, results=List(CU.scalarOut(x4149_b5005_x4157_b5007_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4149_b5006_x4157_b5008_s)))
    }
    val x4159 = MemoryController(name="x4159",parent=x4167,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4149_b5005_x4159 =  ScalarFIFO(name="offset",size=1).wtPort(x4149_b5005_x4157_b5007_s)
      val x4149_b5006_x4159 =  ScalarFIFO(name="size",size=1).wtPort(x4149_b5006_x4157_b5008_s)
      CU.newVout("data", x4150_x4159_data_v)
    }
    val x4166 = Pipeline(name="x4166",parent=x4167) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4161 = CounterChain(name = "x4161", ctr14)
      var stage: List[Stage] = Nil
    }
    val x4187 = StreamController(name="x4187",parent=x4965) { implicit CU => 
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4187_unit = CounterChain(name = "x4187_unit", ctr15)
    }
    val x4178_0 = Pipeline(name="x4178_0",parent=x4187) { implicit CU => 
      val x4172 = CU.temp
      val x4171 =  ScalarBuffer().wtPort(x4171_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4178_unit = CounterChain(name = "x4178_unit", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4172))
      Stage(operands=List(x4172, CU.load(x4171)), op=FixAdd, results=List(CU.scalarOut(x4169_b5009_x4177_b5011_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4169_b5010_x4177_b5012_s)))
    }
    val x4179 = MemoryController(name="x4179",parent=x4187,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4169_b5009_x4179 =  ScalarFIFO(name="offset",size=1).wtPort(x4169_b5009_x4177_b5011_s)
      val x4169_b5010_x4179 =  ScalarFIFO(name="size",size=1).wtPort(x4169_b5010_x4177_b5012_s)
      CU.newVout("data", x4170_x4179_data_v)
    }
    val x4186 = Pipeline(name="x4186",parent=x4187) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4181 = CounterChain(name = "x4181", ctr17)
      var stage: List[Stage] = Nil
    }
    val x4206 = StreamController(name="x4206",parent=x4965) { implicit CU => 
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4206_unit = CounterChain(name = "x4206_unit", ctr18)
    }
    val x4197_0 = Pipeline(name="x4197_0",parent=x4206) { implicit CU => 
      val x4191 = CU.temp
      val x4190 =  ScalarBuffer().wtPort(x4190_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4197_unit = CounterChain(name = "x4197_unit", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4191))
      Stage(operands=List(x4191, CU.load(x4190)), op=FixAdd, results=List(CU.scalarOut(x4188_b5013_x4196_b5015_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4188_b5014_x4196_b5016_s)))
    }
    val x4198 = MemoryController(name="x4198",parent=x4206,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4188_b5014_x4198 =  ScalarFIFO(name="size",size=1).wtPort(x4188_b5014_x4196_b5016_s)
      val x4188_b5013_x4198 =  ScalarFIFO(name="offset",size=1).wtPort(x4188_b5013_x4196_b5015_s)
      CU.newVout("data", x4189_x4198_data_v)
    }
    val x4205 = Pipeline(name="x4205",parent=x4206) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4200 = CounterChain(name = "x4200", ctr20)
      var stage: List[Stage] = Nil
    }
    val x4225 = StreamController(name="x4225",parent=x4965) { implicit CU => 
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4225_unit = CounterChain(name = "x4225_unit", ctr21)
    }
    val x4216_0 = Pipeline(name="x4216_0",parent=x4225) { implicit CU => 
      val x4210 = CU.temp
      val x4209 =  ScalarBuffer().wtPort(x4209_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4216_unit = CounterChain(name = "x4216_unit", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4210))
      Stage(operands=List(x4210, CU.load(x4209)), op=FixAdd, results=List(CU.scalarOut(x4207_b5017_x4215_b5019_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4207_b5018_x4215_b5020_s)))
    }
    val x4217 = MemoryController(name="x4217",parent=x4225,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4207_b5018_x4217 =  ScalarFIFO(name="size",size=1).wtPort(x4207_b5018_x4215_b5020_s)
      val x4207_b5017_x4217 =  ScalarFIFO(name="offset",size=1).wtPort(x4207_b5017_x4215_b5019_s)
      CU.newVout("data", x4208_x4217_data_v)
    }
    val x4224 = Pipeline(name="x4224",parent=x4225) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4219 = CounterChain(name = "x4219", ctr23)
      var stage: List[Stage] = Nil
    }
    val x4244 = StreamController(name="x4244",parent=x4965) { implicit CU => 
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4244_unit = CounterChain(name = "x4244_unit", ctr24)
    }
    val x4235_0 = Pipeline(name="x4235_0",parent=x4244) { implicit CU => 
      val x4229 = CU.temp
      val x4228 =  ScalarBuffer().wtPort(x4228_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4235_unit = CounterChain(name = "x4235_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4229))
      Stage(operands=List(x4229, CU.load(x4228)), op=FixAdd, results=List(CU.scalarOut(x4226_b5021_x4234_b5023_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4226_b5022_x4234_b5024_s)))
    }
    val x4236 = MemoryController(name="x4236",parent=x4244,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4226_b5022_x4236 =  ScalarFIFO(name="size",size=1).wtPort(x4226_b5022_x4234_b5024_s)
      val x4226_b5021_x4236 =  ScalarFIFO(name="offset",size=1).wtPort(x4226_b5021_x4234_b5023_s)
      CU.newVout("data", x4227_x4236_data_v)
    }
    val x4243 = Pipeline(name="x4243",parent=x4244) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4238 = CounterChain(name = "x4238", ctr26)
      var stage: List[Stage] = Nil
    }
    val x4264 = StreamController(name="x4264",parent=x4965) { implicit CU => 
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4264_unit = CounterChain(name = "x4264_unit", ctr27)
    }
    val x4255_0 = Pipeline(name="x4255_0",parent=x4264) { implicit CU => 
      val x4249 = CU.temp
      val x4248 =  ScalarBuffer().wtPort(x4248_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4255_unit = CounterChain(name = "x4255_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4249))
      Stage(operands=List(x4249, CU.load(x4248)), op=FixAdd, results=List(CU.scalarOut(x4246_b5025_x4254_b5027_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4246_b5026_x4254_b5028_s)))
    }
    val x4256 = MemoryController(name="x4256",parent=x4264,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4246_b5026_x4256 =  ScalarFIFO(name="size",size=1).wtPort(x4246_b5026_x4254_b5028_s)
      val x4246_b5025_x4256 =  ScalarFIFO(name="offset",size=1).wtPort(x4246_b5025_x4254_b5027_s)
      CU.newVout("data", x4247_x4256_data_v)
    }
    val x4263 = Pipeline(name="x4263",parent=x4264) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4258 = CounterChain(name = "x4258", ctr29)
      var stage: List[Stage] = Nil
    }
    val x4283 = StreamController(name="x4283",parent=x4965) { implicit CU => 
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4283_unit = CounterChain(name = "x4283_unit", ctr30)
    }
    val x4274_0 = Pipeline(name="x4274_0",parent=x4283) { implicit CU => 
      val x4268 = CU.temp
      val x4267 =  ScalarBuffer().wtPort(x4267_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4274_unit = CounterChain(name = "x4274_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4268))
      Stage(operands=List(x4268, CU.load(x4267)), op=FixAdd, results=List(CU.scalarOut(x4265_b5029_x4273_b5031_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4265_b5030_x4273_b5032_s)))
    }
    val x4275 = MemoryController(name="x4275",parent=x4283,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4265_b5029_x4275 =  ScalarFIFO(name="offset",size=1).wtPort(x4265_b5029_x4273_b5031_s)
      val x4265_b5030_x4275 =  ScalarFIFO(name="size",size=1).wtPort(x4265_b5030_x4273_b5032_s)
      CU.newVout("data", x4266_x4275_data_v)
    }
    val x4282 = Pipeline(name="x4282",parent=x4283) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4277 = CounterChain(name = "x4277", ctr32)
      var stage: List[Stage] = Nil
    }
    val x4302 = StreamController(name="x4302",parent=x4965) { implicit CU => 
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4302_unit = CounterChain(name = "x4302_unit", ctr33)
    }
    val x4293_0 = Pipeline(name="x4293_0",parent=x4302) { implicit CU => 
      val x4287 = CU.temp
      val x4286 =  ScalarBuffer().wtPort(x4286_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4293_unit = CounterChain(name = "x4293_unit", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4287))
      Stage(operands=List(x4287, CU.load(x4286)), op=FixAdd, results=List(CU.scalarOut(x4284_b5033_x4292_b5035_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4284_b5034_x4292_b5036_s)))
    }
    val x4294 = MemoryController(name="x4294",parent=x4302,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4284_b5034_x4294 =  ScalarFIFO(name="size",size=1).wtPort(x4284_b5034_x4292_b5036_s)
      val x4284_b5033_x4294 =  ScalarFIFO(name="offset",size=1).wtPort(x4284_b5033_x4292_b5035_s)
      CU.newVout("data", x4285_x4294_data_v)
    }
    val x4301 = Pipeline(name="x4301",parent=x4302) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4296 = CounterChain(name = "x4296", ctr35)
      var stage: List[Stage] = Nil
    }
    val x4321 = StreamController(name="x4321",parent=x4965) { implicit CU => 
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4321_unit = CounterChain(name = "x4321_unit", ctr36)
    }
    val x4312_0 = Pipeline(name="x4312_0",parent=x4321) { implicit CU => 
      val x4306 = CU.temp
      val x4305 =  ScalarBuffer().wtPort(x4305_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4312_unit = CounterChain(name = "x4312_unit", ctr37)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4306))
      Stage(operands=List(x4306, CU.load(x4305)), op=FixAdd, results=List(CU.scalarOut(x4303_b5037_x4311_b5039_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4303_b5038_x4311_b5040_s)))
    }
    val x4313 = MemoryController(name="x4313",parent=x4321,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4303_b5038_x4313 =  ScalarFIFO(name="size",size=1).wtPort(x4303_b5038_x4311_b5040_s)
      val x4303_b5037_x4313 =  ScalarFIFO(name="offset",size=1).wtPort(x4303_b5037_x4311_b5039_s)
      CU.newVout("data", x4304_x4313_data_v)
    }
    val x4320 = Pipeline(name="x4320",parent=x4321) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4315 = CounterChain(name = "x4315", ctr38)
      var stage: List[Stage] = Nil
    }
    val x4341 = StreamController(name="x4341",parent=x4965) { implicit CU => 
      val ctr39 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4341_unit = CounterChain(name = "x4341_unit", ctr39)
    }
    val x4332_0 = Pipeline(name="x4332_0",parent=x4341) { implicit CU => 
      val x4326 = CU.temp
      val x4325 =  ScalarBuffer().wtPort(x4325_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4332_unit = CounterChain(name = "x4332_unit", ctr40)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4326))
      Stage(operands=List(x4326, CU.load(x4325)), op=FixAdd, results=List(CU.scalarOut(x4323_b5041_x4331_b5043_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4323_b5042_x4331_b5044_s)))
    }
    val x4333 = MemoryController(name="x4333",parent=x4341,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4323_b5041_x4333 =  ScalarFIFO(name="offset",size=1).wtPort(x4323_b5041_x4331_b5043_s)
      val x4323_b5042_x4333 =  ScalarFIFO(name="size",size=1).wtPort(x4323_b5042_x4331_b5044_s)
      CU.newVout("data", x4324_x4333_data_v)
    }
    val x4340 = Pipeline(name="x4340",parent=x4341) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4335 = CounterChain(name = "x4335", ctr41)
      var stage: List[Stage] = Nil
    }
    val x4360 = StreamController(name="x4360",parent=x4965) { implicit CU => 
      val ctr42 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4360_unit = CounterChain(name = "x4360_unit", ctr42)
    }
    val x4351_0 = Pipeline(name="x4351_0",parent=x4360) { implicit CU => 
      val x4345 = CU.temp
      val x4344 =  ScalarBuffer().wtPort(x4344_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4351_unit = CounterChain(name = "x4351_unit", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4345))
      Stage(operands=List(x4345, CU.load(x4344)), op=FixAdd, results=List(CU.scalarOut(x4342_b5045_x4350_b5047_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4342_b5046_x4350_b5048_s)))
    }
    val x4352 = MemoryController(name="x4352",parent=x4360,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4342_b5046_x4352 =  ScalarFIFO(name="size",size=1).wtPort(x4342_b5046_x4350_b5048_s)
      val x4342_b5045_x4352 =  ScalarFIFO(name="offset",size=1).wtPort(x4342_b5045_x4350_b5047_s)
      CU.newVout("data", x4343_x4352_data_v)
    }
    val x4359 = Pipeline(name="x4359",parent=x4360) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4354 = CounterChain(name = "x4354", ctr44)
      var stage: List[Stage] = Nil
    }
    val x4379 = StreamController(name="x4379",parent=x4965) { implicit CU => 
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4379_unit = CounterChain(name = "x4379_unit", ctr45)
    }
    val x4370_0 = Pipeline(name="x4370_0",parent=x4379) { implicit CU => 
      val x4364 = CU.temp
      val x4363 =  ScalarBuffer().wtPort(x4363_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4370_unit = CounterChain(name = "x4370_unit", ctr46)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4364))
      Stage(operands=List(x4364, CU.load(x4363)), op=FixAdd, results=List(CU.scalarOut(x4361_b5049_x4369_b5051_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4361_b5050_x4369_b5052_s)))
    }
    val x4371 = MemoryController(name="x4371",parent=x4379,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4361_b5050_x4371 =  ScalarFIFO(name="size",size=1).wtPort(x4361_b5050_x4369_b5052_s)
      val x4361_b5049_x4371 =  ScalarFIFO(name="offset",size=1).wtPort(x4361_b5049_x4369_b5051_s)
      CU.newVout("data", x4362_x4371_data_v)
    }
    val x4378 = Pipeline(name="x4378",parent=x4379) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4373 = CounterChain(name = "x4373", ctr47)
      var stage: List[Stage] = Nil
    }
    val x4398 = StreamController(name="x4398",parent=x4965) { implicit CU => 
      val ctr48 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4398_unit = CounterChain(name = "x4398_unit", ctr48)
    }
    val x4389_0 = Pipeline(name="x4389_0",parent=x4398) { implicit CU => 
      val x4383 = CU.temp
      val x4382 =  ScalarBuffer().wtPort(x4382_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4389_unit = CounterChain(name = "x4389_unit", ctr49)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4383))
      Stage(operands=List(x4383, CU.load(x4382)), op=FixAdd, results=List(CU.scalarOut(x4380_b5053_x4388_b5055_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4380_b5054_x4388_b5056_s)))
    }
    val x4390 = MemoryController(name="x4390",parent=x4398,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4380_b5053_x4390 =  ScalarFIFO(name="offset",size=1).wtPort(x4380_b5053_x4388_b5055_s)
      val x4380_b5054_x4390 =  ScalarFIFO(name="size",size=1).wtPort(x4380_b5054_x4388_b5056_s)
      CU.newVout("data", x4381_x4390_data_v)
    }
    val x4397 = Pipeline(name="x4397",parent=x4398) { implicit CU => 
      val ctr50 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4392 = CounterChain(name = "x4392", ctr50)
      var stage: List[Stage] = Nil
    }
    val x4418 = StreamController(name="x4418",parent=x4965) { implicit CU => 
      val ctr51 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4418_unit = CounterChain(name = "x4418_unit", ctr51)
    }
    val x4409_0 = Pipeline(name="x4409_0",parent=x4418) { implicit CU => 
      val x4403 = CU.temp
      val x4402 =  ScalarBuffer().wtPort(x4402_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4409_unit = CounterChain(name = "x4409_unit", ctr52)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4403))
      Stage(operands=List(x4403, CU.load(x4402)), op=FixAdd, results=List(CU.scalarOut(x4400_b5057_x4408_b5059_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4400_b5058_x4408_b5060_s)))
    }
    val x4410 = MemoryController(name="x4410",parent=x4418,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4400_b5058_x4410 =  ScalarFIFO(name="size",size=1).wtPort(x4400_b5058_x4408_b5060_s)
      val x4400_b5057_x4410 =  ScalarFIFO(name="offset",size=1).wtPort(x4400_b5057_x4408_b5059_s)
      CU.newVout("data", x4401_x4410_data_v)
    }
    val x4417 = Pipeline(name="x4417",parent=x4418) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4412 = CounterChain(name = "x4412", ctr53)
      var stage: List[Stage] = Nil
    }
    val x4437 = StreamController(name="x4437",parent=x4965) { implicit CU => 
      val ctr54 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4437_unit = CounterChain(name = "x4437_unit", ctr54)
    }
    val x4428_0 = Pipeline(name="x4428_0",parent=x4437) { implicit CU => 
      val x4422 = CU.temp
      val x4421 =  ScalarBuffer().wtPort(x4421_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr55 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4428_unit = CounterChain(name = "x4428_unit", ctr55)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4422))
      Stage(operands=List(x4422, CU.load(x4421)), op=FixAdd, results=List(CU.scalarOut(x4419_b5061_x4427_b5063_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4419_b5062_x4427_b5064_s)))
    }
    val x4429 = MemoryController(name="x4429",parent=x4437,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4419_b5062_x4429 =  ScalarFIFO(name="size",size=1).wtPort(x4419_b5062_x4427_b5064_s)
      val x4419_b5061_x4429 =  ScalarFIFO(name="offset",size=1).wtPort(x4419_b5061_x4427_b5063_s)
      CU.newVout("data", x4420_x4429_data_v)
    }
    val x4436 = Pipeline(name="x4436",parent=x4437) { implicit CU => 
      val ctr56 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4431 = CounterChain(name = "x4431", ctr56)
      var stage: List[Stage] = Nil
    }
    val x4456 = StreamController(name="x4456",parent=x4965) { implicit CU => 
      val ctr57 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4456_unit = CounterChain(name = "x4456_unit", ctr57)
    }
    val x4447_0 = Pipeline(name="x4447_0",parent=x4456) { implicit CU => 
      val x4441 = CU.temp
      val x4440 =  ScalarBuffer().wtPort(x4440_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr58 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4447_unit = CounterChain(name = "x4447_unit", ctr58)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4441))
      Stage(operands=List(x4441, CU.load(x4440)), op=FixAdd, results=List(CU.scalarOut(x4438_b5065_x4446_b5067_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4438_b5066_x4446_b5068_s)))
    }
    val x4448 = MemoryController(name="x4448",parent=x4456,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4438_b5065_x4448 =  ScalarFIFO(name="offset",size=1).wtPort(x4438_b5065_x4446_b5067_s)
      val x4438_b5066_x4448 =  ScalarFIFO(name="size",size=1).wtPort(x4438_b5066_x4446_b5068_s)
      CU.newVout("data", x4439_x4448_data_v)
    }
    val x4455 = Pipeline(name="x4455",parent=x4456) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4450 = CounterChain(name = "x4450", ctr59)
      var stage: List[Stage] = Nil
    }
    val x4475 = StreamController(name="x4475",parent=x4965) { implicit CU => 
      val ctr60 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4475_unit = CounterChain(name = "x4475_unit", ctr60)
    }
    val x4466_0 = Pipeline(name="x4466_0",parent=x4475) { implicit CU => 
      val x4460 = CU.temp
      val x4459 =  ScalarBuffer().wtPort(x4459_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr61 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4466_unit = CounterChain(name = "x4466_unit", ctr61)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4460))
      Stage(operands=List(x4460, CU.load(x4459)), op=FixAdd, results=List(CU.scalarOut(x4457_b5069_x4465_b5071_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4457_b5070_x4465_b5072_s)))
    }
    val x4467 = MemoryController(name="x4467",parent=x4475,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4457_b5070_x4467 =  ScalarFIFO(name="size",size=1).wtPort(x4457_b5070_x4465_b5072_s)
      val x4457_b5069_x4467 =  ScalarFIFO(name="offset",size=1).wtPort(x4457_b5069_x4465_b5071_s)
      CU.newVout("data", x4458_x4467_data_v)
    }
    val x4474 = Pipeline(name="x4474",parent=x4475) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4469 = CounterChain(name = "x4469", ctr62)
      var stage: List[Stage] = Nil
    }
    val x4495 = StreamController(name="x4495",parent=x4965) { implicit CU => 
      val ctr63 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4495_unit = CounterChain(name = "x4495_unit", ctr63)
    }
    val x4486_0 = Pipeline(name="x4486_0",parent=x4495) { implicit CU => 
      val x4480 = CU.temp
      val x4479 =  ScalarBuffer().wtPort(x4479_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr64 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4486_unit = CounterChain(name = "x4486_unit", ctr64)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4480))
      Stage(operands=List(x4480, CU.load(x4479)), op=FixAdd, results=List(CU.scalarOut(x4477_b5073_x4485_b5075_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4477_b5074_x4485_b5076_s)))
    }
    val x4487 = MemoryController(name="x4487",parent=x4495,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4477_b5074_x4487 =  ScalarFIFO(name="size",size=1).wtPort(x4477_b5074_x4485_b5076_s)
      val x4477_b5073_x4487 =  ScalarFIFO(name="offset",size=1).wtPort(x4477_b5073_x4485_b5075_s)
      CU.newVout("data", x4478_x4487_data_v)
    }
    val x4494 = Pipeline(name="x4494",parent=x4495) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4489 = CounterChain(name = "x4489", ctr65)
      var stage: List[Stage] = Nil
    }
    val x4514 = StreamController(name="x4514",parent=x4965) { implicit CU => 
      val ctr66 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4514_unit = CounterChain(name = "x4514_unit", ctr66)
    }
    val x4505_0 = Pipeline(name="x4505_0",parent=x4514) { implicit CU => 
      val x4499 = CU.temp
      val x4498 =  ScalarBuffer().wtPort(x4498_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr67 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4505_unit = CounterChain(name = "x4505_unit", ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4499))
      Stage(operands=List(x4499, CU.load(x4498)), op=FixAdd, results=List(CU.scalarOut(x4496_b5077_x4504_b5079_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4496_b5078_x4504_b5080_s)))
    }
    val x4506 = MemoryController(name="x4506",parent=x4514,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4496_b5077_x4506 =  ScalarFIFO(name="offset",size=1).wtPort(x4496_b5077_x4504_b5079_s)
      val x4496_b5078_x4506 =  ScalarFIFO(name="size",size=1).wtPort(x4496_b5078_x4504_b5080_s)
      CU.newVout("data", x4497_x4506_data_v)
    }
    val x4513 = Pipeline(name="x4513",parent=x4514) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4508 = CounterChain(name = "x4508", ctr68)
      var stage: List[Stage] = Nil
    }
    val x4533 = StreamController(name="x4533",parent=x4965) { implicit CU => 
      val ctr69 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4533_unit = CounterChain(name = "x4533_unit", ctr69)
    }
    val x4524_0 = Pipeline(name="x4524_0",parent=x4533) { implicit CU => 
      val x4518 = CU.temp
      val x4517 =  ScalarBuffer().wtPort(x4517_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr70 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4524_unit = CounterChain(name = "x4524_unit", ctr70)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4518))
      Stage(operands=List(x4518, CU.load(x4517)), op=FixAdd, results=List(CU.scalarOut(x4515_b5081_x4523_b5083_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4515_b5082_x4523_b5084_s)))
    }
    val x4525 = MemoryController(name="x4525",parent=x4533,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4515_b5082_x4525 =  ScalarFIFO(name="size",size=1).wtPort(x4515_b5082_x4523_b5084_s)
      val x4515_b5081_x4525 =  ScalarFIFO(name="offset",size=1).wtPort(x4515_b5081_x4523_b5083_s)
      CU.newVout("data", x4516_x4525_data_v)
    }
    val x4532 = Pipeline(name="x4532",parent=x4533) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4527 = CounterChain(name = "x4527", ctr71)
      var stage: List[Stage] = Nil
    }
    val x4552 = StreamController(name="x4552",parent=x4965) { implicit CU => 
      val ctr72 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4552_unit = CounterChain(name = "x4552_unit", ctr72)
    }
    val x4543_0 = Pipeline(name="x4543_0",parent=x4552) { implicit CU => 
      val x4537 = CU.temp
      val x4536 =  ScalarBuffer().wtPort(x4536_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr73 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4543_unit = CounterChain(name = "x4543_unit", ctr73)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4537))
      Stage(operands=List(x4537, CU.load(x4536)), op=FixAdd, results=List(CU.scalarOut(x4534_b5085_x4542_b5087_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4534_b5086_x4542_b5088_s)))
    }
    val x4544 = MemoryController(name="x4544",parent=x4552,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4534_b5086_x4544 =  ScalarFIFO(name="size",size=1).wtPort(x4534_b5086_x4542_b5088_s)
      val x4534_b5085_x4544 =  ScalarFIFO(name="offset",size=1).wtPort(x4534_b5085_x4542_b5087_s)
      CU.newVout("data", x4535_x4544_data_v)
    }
    val x4551 = Pipeline(name="x4551",parent=x4552) { implicit CU => 
      val ctr74 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4546 = CounterChain(name = "x4546", ctr74)
      var stage: List[Stage] = Nil
    }
    val x4572 = StreamController(name="x4572",parent=x4965) { implicit CU => 
      val ctr75 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4572_unit = CounterChain(name = "x4572_unit", ctr75)
    }
    val x4563_0 = Pipeline(name="x4563_0",parent=x4572) { implicit CU => 
      val x4557 = CU.temp
      val x4556 =  ScalarBuffer().wtPort(x4556_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr76 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4563_unit = CounterChain(name = "x4563_unit", ctr76)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4557))
      Stage(operands=List(x4557, CU.load(x4556)), op=FixAdd, results=List(CU.scalarOut(x4554_b5089_x4562_b5091_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4554_b5090_x4562_b5092_s)))
    }
    val x4564 = MemoryController(name="x4564",parent=x4572,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4554_b5089_x4564 =  ScalarFIFO(name="offset",size=1).wtPort(x4554_b5089_x4562_b5091_s)
      val x4554_b5090_x4564 =  ScalarFIFO(name="size",size=1).wtPort(x4554_b5090_x4562_b5092_s)
      CU.newVout("data", x4555_x4564_data_v)
    }
    val x4571 = Pipeline(name="x4571",parent=x4572) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4566 = CounterChain(name = "x4566", ctr77)
      var stage: List[Stage] = Nil
    }
    val x4591 = StreamController(name="x4591",parent=x4965) { implicit CU => 
      val ctr78 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4591_unit = CounterChain(name = "x4591_unit", ctr78)
    }
    val x4582_0 = Pipeline(name="x4582_0",parent=x4591) { implicit CU => 
      val x4576 = CU.temp
      val x4575 =  ScalarBuffer().wtPort(x4575_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr79 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4582_unit = CounterChain(name = "x4582_unit", ctr79)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4576))
      Stage(operands=List(x4576, CU.load(x4575)), op=FixAdd, results=List(CU.scalarOut(x4573_b5093_x4581_b5095_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4573_b5094_x4581_b5096_s)))
    }
    val x4583 = MemoryController(name="x4583",parent=x4591,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4573_b5094_x4583 =  ScalarFIFO(name="size",size=1).wtPort(x4573_b5094_x4581_b5096_s)
      val x4573_b5093_x4583 =  ScalarFIFO(name="offset",size=1).wtPort(x4573_b5093_x4581_b5095_s)
      CU.newVout("data", x4574_x4583_data_v)
    }
    val x4590 = Pipeline(name="x4590",parent=x4591) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4585 = CounterChain(name = "x4585", ctr80)
      var stage: List[Stage] = Nil
    }
    val x4610 = StreamController(name="x4610",parent=x4965) { implicit CU => 
      val ctr81 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4610_unit = CounterChain(name = "x4610_unit", ctr81)
    }
    val x4601_0 = Pipeline(name="x4601_0",parent=x4610) { implicit CU => 
      val x4595 = CU.temp
      val x4594 =  ScalarBuffer().wtPort(x4594_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr82 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4601_unit = CounterChain(name = "x4601_unit", ctr82)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4595))
      Stage(operands=List(x4595, CU.load(x4594)), op=FixAdd, results=List(CU.scalarOut(x4592_b5097_x4600_b5099_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4592_b5098_x4600_b5100_s)))
    }
    val x4602 = MemoryController(name="x4602",parent=x4610,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4592_b5098_x4602 =  ScalarFIFO(name="size",size=1).wtPort(x4592_b5098_x4600_b5100_s)
      val x4592_b5097_x4602 =  ScalarFIFO(name="offset",size=1).wtPort(x4592_b5097_x4600_b5099_s)
      CU.newVout("data", x4593_x4602_data_v)
    }
    val x4609 = Pipeline(name="x4609",parent=x4610) { implicit CU => 
      val ctr83 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4604 = CounterChain(name = "x4604", ctr83)
      var stage: List[Stage] = Nil
    }
    val x4629 = StreamController(name="x4629",parent=x4965) { implicit CU => 
      val ctr84 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4629_unit = CounterChain(name = "x4629_unit", ctr84)
    }
    val x4620_0 = Pipeline(name="x4620_0",parent=x4629) { implicit CU => 
      val x4614 = CU.temp
      val x4613 =  ScalarBuffer().wtPort(x4613_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr85 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4620_unit = CounterChain(name = "x4620_unit", ctr85)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4614))
      Stage(operands=List(x4614, CU.load(x4613)), op=FixAdd, results=List(CU.scalarOut(x4611_b5101_x4619_b5103_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4611_b5102_x4619_b5104_s)))
    }
    val x4621 = MemoryController(name="x4621",parent=x4629,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4611_b5101_x4621 =  ScalarFIFO(name="offset",size=1).wtPort(x4611_b5101_x4619_b5103_s)
      val x4611_b5102_x4621 =  ScalarFIFO(name="size",size=1).wtPort(x4611_b5102_x4619_b5104_s)
      CU.newVout("data", x4612_x4621_data_v)
    }
    val x4628 = Pipeline(name="x4628",parent=x4629) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4623 = CounterChain(name = "x4623", ctr86)
      var stage: List[Stage] = Nil
    }
    val x4649 = StreamController(name="x4649",parent=x4965) { implicit CU => 
      val ctr87 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4649_unit = CounterChain(name = "x4649_unit", ctr87)
    }
    val x4640_0 = Pipeline(name="x4640_0",parent=x4649) { implicit CU => 
      val x4634 = CU.temp
      val x4633 =  ScalarBuffer().wtPort(x4633_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr88 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4640_unit = CounterChain(name = "x4640_unit", ctr88)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4634))
      Stage(operands=List(x4634, CU.load(x4633)), op=FixAdd, results=List(CU.scalarOut(x4631_b5105_x4639_b5107_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4631_b5106_x4639_b5108_s)))
    }
    val x4641 = MemoryController(name="x4641",parent=x4649,offchip=x4044_oc, mctpe=TileLoad) { implicit CU => 
      val x4631_b5106_x4641 =  ScalarFIFO(name="size",size=1).wtPort(x4631_b5106_x4639_b5108_s)
      val x4631_b5105_x4641 =  ScalarFIFO(name="offset",size=1).wtPort(x4631_b5105_x4639_b5107_s)
      CU.newVout("data", x4632_x4641_data_v)
    }
    val x4648 = Pipeline(name="x4648",parent=x4649) { implicit CU => 
      val ctr89 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4643 = CounterChain(name = "x4643", ctr89)
      var stage: List[Stage] = Nil
    }
    val x4668 = StreamController(name="x4668",parent=x4965) { implicit CU => 
      val ctr90 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4668_unit = CounterChain(name = "x4668_unit", ctr90)
    }
    val x4659_0 = Pipeline(name="x4659_0",parent=x4668) { implicit CU => 
      val x4653 = CU.temp
      val x4652 =  ScalarBuffer().wtPort(x4652_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr91 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4659_unit = CounterChain(name = "x4659_unit", ctr91)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4653))
      Stage(operands=List(x4653, CU.load(x4652)), op=FixAdd, results=List(CU.scalarOut(x4650_b5109_x4658_b5111_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4650_b5110_x4658_b5112_s)))
    }
    val x4660 = MemoryController(name="x4660",parent=x4668,offchip=x4046_oc, mctpe=TileLoad) { implicit CU => 
      val x4650_b5110_x4660 =  ScalarFIFO(name="size",size=1).wtPort(x4650_b5110_x4658_b5112_s)
      val x4650_b5109_x4660 =  ScalarFIFO(name="offset",size=1).wtPort(x4650_b5109_x4658_b5111_s)
      CU.newVout("data", x4651_x4660_data_v)
    }
    val x4667 = Pipeline(name="x4667",parent=x4668) { implicit CU => 
      val ctr92 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4662 = CounterChain(name = "x4662", ctr92)
      var stage: List[Stage] = Nil
    }
    val x4687 = StreamController(name="x4687",parent=x4965) { implicit CU => 
      val ctr93 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4687_unit = CounterChain(name = "x4687_unit", ctr93)
    }
    val x4678_0 = Pipeline(name="x4678_0",parent=x4687) { implicit CU => 
      val x4672 = CU.temp
      val x4671 =  ScalarBuffer().wtPort(x4671_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr94 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4678_unit = CounterChain(name = "x4678_unit", ctr94)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4672))
      Stage(operands=List(x4672, CU.load(x4671)), op=FixAdd, results=List(CU.scalarOut(x4669_b5113_x4677_b5115_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4669_b5114_x4677_b5116_s)))
    }
    val x4679 = MemoryController(name="x4679",parent=x4687,offchip=x4048_oc, mctpe=TileLoad) { implicit CU => 
      val x4669_b5113_x4679 =  ScalarFIFO(name="offset",size=1).wtPort(x4669_b5113_x4677_b5115_s)
      val x4669_b5114_x4679 =  ScalarFIFO(name="size",size=1).wtPort(x4669_b5114_x4677_b5116_s)
      CU.newVout("data", x4670_x4679_data_v)
    }
    val x4686 = Pipeline(name="x4686",parent=x4687) { implicit CU => 
      val ctr95 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4681 = CounterChain(name = "x4681", ctr95)
      var stage: List[Stage] = Nil
    }
    val x4706 = StreamController(name="x4706",parent=x4965) { implicit CU => 
      val ctr96 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4706_unit = CounterChain(name = "x4706_unit", ctr96)
    }
    val x4697_0 = Pipeline(name="x4697_0",parent=x4706) { implicit CU => 
      val x4691 = CU.temp
      val x4690 =  ScalarBuffer().wtPort(x4690_argin)
      val x4059 = CounterChain.copy("x4965", "x4059")
      val ctr97 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4697_unit = CounterChain(name = "x4697_unit", ctr97)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4059(0)), Const(4)), op=FixMul, results=List(x4691))
      Stage(operands=List(x4691, CU.load(x4690)), op=FixAdd, results=List(CU.scalarOut(x4688_b5117_x4696_b5119_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4688_b5118_x4696_b5120_s)))
    }
    val x4698 = MemoryController(name="x4698",parent=x4706,offchip=x4050_oc, mctpe=TileLoad) { implicit CU => 
      val x4688_b5118_x4698 =  ScalarFIFO(name="size",size=1).wtPort(x4688_b5118_x4696_b5120_s)
      val x4688_b5117_x4698 =  ScalarFIFO(name="offset",size=1).wtPort(x4688_b5117_x4696_b5119_s)
      CU.newVout("data", x4689_x4698_data_v)
    }
    val x4705 = Pipeline(name="x4705",parent=x4706) { implicit CU => 
      val ctr98 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4700 = CounterChain(name = "x4700", ctr98)
      var stage: List[Stage] = Nil
    }
    val x4743 = StreamController(name="x4743",parent=x4965) { implicit CU => 
      val ctr99 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4718 = CounterChain(name = "x4718", ctr99)
    }
    val x4743_0 = Pipeline(name="x4743_0",parent=x4743) { implicit CU => 
      val x4730 = CU.temp
      val x4728 = CU.temp
      val x4727 = CU.temp
      val x4726 = CU.temp
      val x4076_x4721 =  VectorFIFO(size=1).wtPort(x4076_x4721_x4743_v)
      val x4060_x4720 =  VectorFIFO(size=1).wtPort(x4060_x4720_x4743_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4060_x4720)), op=FixLt, results=List(x4726))
      Stage(operands=List(CU.load(x4060_x4720), Const(9999)), op=FixLt, results=List(x4727))
      Stage(operands=List(x4726, x4727), op=BitAnd, results=List(x4728))
      Stage(operands=List(Const(0), CU.load(x4076_x4721)), op=FixLeq, results=List(x4730))
      Stage(operands=List(x4728, x4730), op=BitAnd, results=List(CU.vecOut(bus_2330_v)))
    }
    val x4743_1 = Pipeline(name="x4743_1",parent=x4743) { implicit CU => 
      val x4735 = CU.temp
      val x4738 = CU.temp
      val x4732 = CU.temp
      val x4733 = CU.temp
      val x4736 = CU.temp
      val x4076_x4721 =  VectorFIFO(size=1).wtPort(x4076_x4721_x4743_v)
      val x4084_x4723 =  VectorFIFO(size=1).wtPort(x4084_x4723_x4743_v)
      val x4731 =  VectorFIFO(size=1).wtPort(bus_2330_v)
      val x4068_x4722 =  VectorFIFO(size=1).wtPort(x4068_x4722_x4743_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4076_x4721), Const(9999)), op=FixLeq, results=List(x4732))
      Stage(operands=List(CU.load(x4731), x4732), op=BitAnd, results=List(x4733))
      Stage(operands=List(CU.load(x4068_x4722), Const(24)), op=FixLt, results=List(x4735))
      Stage(operands=List(x4733, x4735), op=BitAnd, results=List(x4736))
      Stage(operands=List(CU.load(x4084_x4723), CU.load(x4076_x4721)), op=FixMul, results=List(x4738))
      Stage(operands=List(x4736, x4738, Const(0)), op=Mux, results=List(CU.vecOut(bus_2338_v)))
    }
    val x4743_2 = Pipeline(name="x4743_2",parent=x4743) { implicit CU => 
      val rr2338 =  VectorFIFO(size=1).wtPort(bus_2338_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2338)), op=Bypass, results=List(CU.reduce))
      val (_, rr2340) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2340), op=Bypass, results=List(CU.scalarOut(x4709_x4741_s)))
    }
    val x4770 = StreamController(name="x4770",parent=x4965) { implicit CU => 
      val ctr100 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4745 = CounterChain(name = "x4745", ctr100)
    }
    val x4770_0 = Pipeline(name="x4770_0",parent=x4770) { implicit CU => 
      val x4754 = CU.temp
      val x4755 = CU.temp
      val x4757 = CU.temp
      val x4753 = CU.temp
      val x4077_x4748 =  VectorFIFO(size=1).wtPort(x4077_x4748_x4770_v)
      val x4061_x4747 =  VectorFIFO(size=1).wtPort(x4061_x4747_x4770_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4061_x4747)), op=FixLt, results=List(x4753))
      Stage(operands=List(CU.load(x4061_x4747), Const(9999)), op=FixLt, results=List(x4754))
      Stage(operands=List(x4753, x4754), op=BitAnd, results=List(x4755))
      Stage(operands=List(Const(0), CU.load(x4077_x4748)), op=FixLeq, results=List(x4757))
      Stage(operands=List(x4755, x4757), op=BitAnd, results=List(CU.vecOut(bus_2347_v)))
    }
    val x4770_1 = Pipeline(name="x4770_1",parent=x4770) { implicit CU => 
      val x4762 = CU.temp
      val x4760 = CU.temp
      val x4763 = CU.temp
      val x4759 = CU.temp
      val x4765 = CU.temp
      val x4069_x4749 =  VectorFIFO(size=1).wtPort(x4069_x4749_x4770_v)
      val x4077_x4748 =  VectorFIFO(size=1).wtPort(x4077_x4748_x4770_v)
      val x4758 =  VectorFIFO(size=1).wtPort(bus_2347_v)
      val x4085_x4750 =  VectorFIFO(size=1).wtPort(x4085_x4750_x4770_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4077_x4748), Const(9999)), op=FixLeq, results=List(x4759))
      Stage(operands=List(CU.load(x4758), x4759), op=BitAnd, results=List(x4760))
      Stage(operands=List(CU.load(x4069_x4749), Const(24)), op=FixLt, results=List(x4762))
      Stage(operands=List(x4760, x4762), op=BitAnd, results=List(x4763))
      Stage(operands=List(CU.load(x4085_x4750), CU.load(x4077_x4748)), op=FixMul, results=List(x4765))
      Stage(operands=List(x4763, x4765, Const(0)), op=Mux, results=List(CU.vecOut(bus_2355_v)))
    }
    val x4770_2 = Pipeline(name="x4770_2",parent=x4770) { implicit CU => 
      val rr2355 =  VectorFIFO(size=1).wtPort(bus_2355_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2355)), op=Bypass, results=List(CU.reduce))
      val (_, rr2357) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2357), op=Bypass, results=List(CU.scalarOut(x4710_x4768_s)))
    }
    val x4797 = StreamController(name="x4797",parent=x4965) { implicit CU => 
      val ctr101 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4772 = CounterChain(name = "x4772", ctr101)
    }
    val x4797_0 = Pipeline(name="x4797_0",parent=x4797) { implicit CU => 
      val x4781 = CU.temp
      val x4780 = CU.temp
      val x4782 = CU.temp
      val x4784 = CU.temp
      val x4078_x4775 =  VectorFIFO(size=1).wtPort(x4078_x4775_x4797_v)
      val x4062_x4774 =  VectorFIFO(size=1).wtPort(x4062_x4774_x4797_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4062_x4774)), op=FixLt, results=List(x4780))
      Stage(operands=List(CU.load(x4062_x4774), Const(9999)), op=FixLt, results=List(x4781))
      Stage(operands=List(x4780, x4781), op=BitAnd, results=List(x4782))
      Stage(operands=List(Const(0), CU.load(x4078_x4775)), op=FixLeq, results=List(x4784))
      Stage(operands=List(x4782, x4784), op=BitAnd, results=List(CU.vecOut(bus_2364_v)))
    }
    val x4797_1 = Pipeline(name="x4797_1",parent=x4797) { implicit CU => 
      val x4790 = CU.temp
      val x4789 = CU.temp
      val x4787 = CU.temp
      val x4792 = CU.temp
      val x4786 = CU.temp
      val x4078_x4775 =  VectorFIFO(size=1).wtPort(x4078_x4775_x4797_v)
      val x4086_x4777 =  VectorFIFO(size=1).wtPort(x4086_x4777_x4797_v)
      val x4785 =  VectorFIFO(size=1).wtPort(bus_2364_v)
      val x4070_x4776 =  VectorFIFO(size=1).wtPort(x4070_x4776_x4797_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4078_x4775), Const(9999)), op=FixLeq, results=List(x4786))
      Stage(operands=List(CU.load(x4785), x4786), op=BitAnd, results=List(x4787))
      Stage(operands=List(CU.load(x4070_x4776), Const(24)), op=FixLt, results=List(x4789))
      Stage(operands=List(x4787, x4789), op=BitAnd, results=List(x4790))
      Stage(operands=List(CU.load(x4086_x4777), CU.load(x4078_x4775)), op=FixMul, results=List(x4792))
      Stage(operands=List(x4790, x4792, Const(0)), op=Mux, results=List(CU.vecOut(bus_2372_v)))
    }
    val x4797_2 = Pipeline(name="x4797_2",parent=x4797) { implicit CU => 
      val rr2372 =  VectorFIFO(size=1).wtPort(bus_2372_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2372)), op=Bypass, results=List(CU.reduce))
      val (_, rr2374) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2374), op=Bypass, results=List(CU.scalarOut(x4711_x4795_s)))
    }
    val x4824 = StreamController(name="x4824",parent=x4965) { implicit CU => 
      val ctr102 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4799 = CounterChain(name = "x4799", ctr102)
    }
    val x4824_0 = Pipeline(name="x4824_0",parent=x4824) { implicit CU => 
      val x4807 = CU.temp
      val x4811 = CU.temp
      val x4809 = CU.temp
      val x4808 = CU.temp
      val x4063_x4801 =  VectorFIFO(size=1).wtPort(x4063_x4801_x4824_v)
      val x4079_x4802 =  VectorFIFO(size=1).wtPort(x4079_x4802_x4824_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4063_x4801)), op=FixLt, results=List(x4807))
      Stage(operands=List(CU.load(x4063_x4801), Const(9999)), op=FixLt, results=List(x4808))
      Stage(operands=List(x4807, x4808), op=BitAnd, results=List(x4809))
      Stage(operands=List(Const(0), CU.load(x4079_x4802)), op=FixLeq, results=List(x4811))
      Stage(operands=List(x4809, x4811), op=BitAnd, results=List(CU.vecOut(bus_2381_v)))
    }
    val x4824_1 = Pipeline(name="x4824_1",parent=x4824) { implicit CU => 
      val x4817 = CU.temp
      val x4819 = CU.temp
      val x4813 = CU.temp
      val x4814 = CU.temp
      val x4816 = CU.temp
      val x4812 =  VectorFIFO(size=1).wtPort(bus_2381_v)
      val x4087_x4804 =  VectorFIFO(size=1).wtPort(x4087_x4804_x4824_v)
      val x4071_x4803 =  VectorFIFO(size=1).wtPort(x4071_x4803_x4824_v)
      val x4079_x4802 =  VectorFIFO(size=1).wtPort(x4079_x4802_x4824_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4079_x4802), Const(9999)), op=FixLeq, results=List(x4813))
      Stage(operands=List(CU.load(x4812), x4813), op=BitAnd, results=List(x4814))
      Stage(operands=List(CU.load(x4071_x4803), Const(24)), op=FixLt, results=List(x4816))
      Stage(operands=List(x4814, x4816), op=BitAnd, results=List(x4817))
      Stage(operands=List(CU.load(x4087_x4804), CU.load(x4079_x4802)), op=FixMul, results=List(x4819))
      Stage(operands=List(x4817, x4819, Const(0)), op=Mux, results=List(CU.vecOut(bus_2389_v)))
    }
    val x4824_2 = Pipeline(name="x4824_2",parent=x4824) { implicit CU => 
      val rr2389 =  VectorFIFO(size=1).wtPort(bus_2389_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2389)), op=Bypass, results=List(CU.reduce))
      val (_, rr2391) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2391), op=Bypass, results=List(CU.scalarOut(x4712_x4822_s)))
    }
    val x4851 = StreamController(name="x4851",parent=x4965) { implicit CU => 
      val ctr103 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4826 = CounterChain(name = "x4826", ctr103)
    }
    val x4851_0 = Pipeline(name="x4851_0",parent=x4851) { implicit CU => 
      val x4835 = CU.temp
      val x4838 = CU.temp
      val x4836 = CU.temp
      val x4834 = CU.temp
      val x4080_x4829 =  VectorFIFO(size=1).wtPort(x4080_x4829_x4851_v)
      val x4064_x4828 =  VectorFIFO(size=1).wtPort(x4064_x4828_x4851_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4064_x4828)), op=FixLt, results=List(x4834))
      Stage(operands=List(CU.load(x4064_x4828), Const(9999)), op=FixLt, results=List(x4835))
      Stage(operands=List(x4834, x4835), op=BitAnd, results=List(x4836))
      Stage(operands=List(Const(0), CU.load(x4080_x4829)), op=FixLeq, results=List(x4838))
      Stage(operands=List(x4836, x4838), op=BitAnd, results=List(CU.vecOut(bus_2398_v)))
    }
    val x4851_1 = Pipeline(name="x4851_1",parent=x4851) { implicit CU => 
      val x4844 = CU.temp
      val x4846 = CU.temp
      val x4840 = CU.temp
      val x4843 = CU.temp
      val x4841 = CU.temp
      val x4839 =  VectorFIFO(size=1).wtPort(bus_2398_v)
      val x4072_x4830 =  VectorFIFO(size=1).wtPort(x4072_x4830_x4851_v)
      val x4080_x4829 =  VectorFIFO(size=1).wtPort(x4080_x4829_x4851_v)
      val x4088_x4831 =  VectorFIFO(size=1).wtPort(x4088_x4831_x4851_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4080_x4829), Const(9999)), op=FixLeq, results=List(x4840))
      Stage(operands=List(CU.load(x4839), x4840), op=BitAnd, results=List(x4841))
      Stage(operands=List(CU.load(x4072_x4830), Const(24)), op=FixLt, results=List(x4843))
      Stage(operands=List(x4841, x4843), op=BitAnd, results=List(x4844))
      Stage(operands=List(CU.load(x4088_x4831), CU.load(x4080_x4829)), op=FixMul, results=List(x4846))
      Stage(operands=List(x4844, x4846, Const(0)), op=Mux, results=List(CU.vecOut(bus_2406_v)))
    }
    val x4851_2 = Pipeline(name="x4851_2",parent=x4851) { implicit CU => 
      val rr2406 =  VectorFIFO(size=1).wtPort(bus_2406_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2406)), op=Bypass, results=List(CU.reduce))
      val (_, rr2408) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2408), op=Bypass, results=List(CU.scalarOut(x4713_x4849_s)))
    }
    val x4878 = StreamController(name="x4878",parent=x4965) { implicit CU => 
      val ctr104 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4853 = CounterChain(name = "x4853", ctr104)
    }
    val x4878_0 = Pipeline(name="x4878_0",parent=x4878) { implicit CU => 
      val x4863 = CU.temp
      val x4865 = CU.temp
      val x4861 = CU.temp
      val x4862 = CU.temp
      val x4081_x4856 =  VectorFIFO(size=1).wtPort(x4081_x4856_x4878_v)
      val x4065_x4855 =  VectorFIFO(size=1).wtPort(x4065_x4855_x4878_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4065_x4855)), op=FixLt, results=List(x4861))
      Stage(operands=List(CU.load(x4065_x4855), Const(9999)), op=FixLt, results=List(x4862))
      Stage(operands=List(x4861, x4862), op=BitAnd, results=List(x4863))
      Stage(operands=List(Const(0), CU.load(x4081_x4856)), op=FixLeq, results=List(x4865))
      Stage(operands=List(x4863, x4865), op=BitAnd, results=List(CU.vecOut(bus_2415_v)))
    }
    val x4878_1 = Pipeline(name="x4878_1",parent=x4878) { implicit CU => 
      val x4871 = CU.temp
      val x4870 = CU.temp
      val x4868 = CU.temp
      val x4873 = CU.temp
      val x4867 = CU.temp
      val x4866 =  VectorFIFO(size=1).wtPort(bus_2415_v)
      val x4081_x4856 =  VectorFIFO(size=1).wtPort(x4081_x4856_x4878_v)
      val x4089_x4858 =  VectorFIFO(size=1).wtPort(x4089_x4858_x4878_v)
      val x4073_x4857 =  VectorFIFO(size=1).wtPort(x4073_x4857_x4878_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4081_x4856), Const(9999)), op=FixLeq, results=List(x4867))
      Stage(operands=List(CU.load(x4866), x4867), op=BitAnd, results=List(x4868))
      Stage(operands=List(CU.load(x4073_x4857), Const(24)), op=FixLt, results=List(x4870))
      Stage(operands=List(x4868, x4870), op=BitAnd, results=List(x4871))
      Stage(operands=List(CU.load(x4089_x4858), CU.load(x4081_x4856)), op=FixMul, results=List(x4873))
      Stage(operands=List(x4871, x4873, Const(0)), op=Mux, results=List(CU.vecOut(bus_2423_v)))
    }
    val x4878_2 = Pipeline(name="x4878_2",parent=x4878) { implicit CU => 
      val rr2423 =  VectorFIFO(size=1).wtPort(bus_2423_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2423)), op=Bypass, results=List(CU.reduce))
      val (_, rr2425) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2425), op=Bypass, results=List(CU.scalarOut(x4714_x4876_s)))
    }
    val x4905 = StreamController(name="x4905",parent=x4965) { implicit CU => 
      val ctr105 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4880 = CounterChain(name = "x4880", ctr105)
    }
    val x4905_0 = Pipeline(name="x4905_0",parent=x4905) { implicit CU => 
      val x4890 = CU.temp
      val x4892 = CU.temp
      val x4888 = CU.temp
      val x4889 = CU.temp
      val x4066_x4882 =  VectorFIFO(size=1).wtPort(x4066_x4882_x4905_v)
      val x4082_x4883 =  VectorFIFO(size=1).wtPort(x4082_x4883_x4905_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4066_x4882)), op=FixLt, results=List(x4888))
      Stage(operands=List(CU.load(x4066_x4882), Const(9999)), op=FixLt, results=List(x4889))
      Stage(operands=List(x4888, x4889), op=BitAnd, results=List(x4890))
      Stage(operands=List(Const(0), CU.load(x4082_x4883)), op=FixLeq, results=List(x4892))
      Stage(operands=List(x4890, x4892), op=BitAnd, results=List(CU.vecOut(bus_2432_v)))
    }
    val x4905_1 = Pipeline(name="x4905_1",parent=x4905) { implicit CU => 
      val x4900 = CU.temp
      val x4894 = CU.temp
      val x4898 = CU.temp
      val x4895 = CU.temp
      val x4897 = CU.temp
      val x4090_x4885 =  VectorFIFO(size=1).wtPort(x4090_x4885_x4905_v)
      val x4074_x4884 =  VectorFIFO(size=1).wtPort(x4074_x4884_x4905_v)
      val x4893 =  VectorFIFO(size=1).wtPort(bus_2432_v)
      val x4082_x4883 =  VectorFIFO(size=1).wtPort(x4082_x4883_x4905_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4082_x4883), Const(9999)), op=FixLeq, results=List(x4894))
      Stage(operands=List(CU.load(x4893), x4894), op=BitAnd, results=List(x4895))
      Stage(operands=List(CU.load(x4074_x4884), Const(24)), op=FixLt, results=List(x4897))
      Stage(operands=List(x4895, x4897), op=BitAnd, results=List(x4898))
      Stage(operands=List(CU.load(x4090_x4885), CU.load(x4082_x4883)), op=FixMul, results=List(x4900))
      Stage(operands=List(x4898, x4900, Const(0)), op=Mux, results=List(CU.vecOut(bus_2440_v)))
    }
    val x4905_2 = Pipeline(name="x4905_2",parent=x4905) { implicit CU => 
      val rr2440 =  VectorFIFO(size=1).wtPort(bus_2440_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2440)), op=Bypass, results=List(CU.reduce))
      val (_, rr2442) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2442), op=Bypass, results=List(CU.scalarOut(x4715_x4903_s)))
    }
    val x4932 = StreamController(name="x4932",parent=x4965) { implicit CU => 
      val ctr106 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4907 = CounterChain(name = "x4907", ctr106)
    }
    val x4932_0 = Pipeline(name="x4932_0",parent=x4932) { implicit CU => 
      val x4919 = CU.temp
      val x4916 = CU.temp
      val x4915 = CU.temp
      val x4917 = CU.temp
      val x4067_x4909 =  VectorFIFO(size=1).wtPort(x4067_x4909_x4932_v)
      val x4083_x4910 =  VectorFIFO(size=1).wtPort(x4083_x4910_x4932_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4067_x4909)), op=FixLt, results=List(x4915))
      Stage(operands=List(CU.load(x4067_x4909), Const(9999)), op=FixLt, results=List(x4916))
      Stage(operands=List(x4915, x4916), op=BitAnd, results=List(x4917))
      Stage(operands=List(Const(0), CU.load(x4083_x4910)), op=FixLeq, results=List(x4919))
      Stage(operands=List(x4917, x4919), op=BitAnd, results=List(CU.vecOut(bus_2449_v)))
    }
    val x4932_1 = Pipeline(name="x4932_1",parent=x4932) { implicit CU => 
      val x4921 = CU.temp
      val x4927 = CU.temp
      val x4924 = CU.temp
      val x4925 = CU.temp
      val x4922 = CU.temp
      val x4920 =  VectorFIFO(size=1).wtPort(bus_2449_v)
      val x4075_x4911 =  VectorFIFO(size=1).wtPort(x4075_x4911_x4932_v)
      val x4083_x4910 =  VectorFIFO(size=1).wtPort(x4083_x4910_x4932_v)
      val x4091_x4912 =  VectorFIFO(size=1).wtPort(x4091_x4912_x4932_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4083_x4910), Const(9999)), op=FixLeq, results=List(x4921))
      Stage(operands=List(CU.load(x4920), x4921), op=BitAnd, results=List(x4922))
      Stage(operands=List(CU.load(x4075_x4911), Const(24)), op=FixLt, results=List(x4924))
      Stage(operands=List(x4922, x4924), op=BitAnd, results=List(x4925))
      Stage(operands=List(CU.load(x4091_x4912), CU.load(x4083_x4910)), op=FixMul, results=List(x4927))
      Stage(operands=List(x4925, x4927, Const(0)), op=Mux, results=List(CU.vecOut(bus_2457_v)))
    }
    val x4932_2 = Pipeline(name="x4932_2",parent=x4932) { implicit CU => 
      val rr2457 =  VectorFIFO(size=1).wtPort(bus_2457_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr2457)), op=Bypass, results=List(CU.reduce))
      val (_, rr2459) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2459), op=Bypass, results=List(CU.scalarOut(x4716_x4930_s)))
    }
    val x4963 = StreamController(name="x4963",parent=x4965) { implicit CU => 
      val ctr107 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4963_unit = CounterChain(name = "x4963_unit", ctr107)
    }
    val x4963_0 = Pipeline(name="x4963_0",parent=x4963) { implicit CU => 
      val x4946 = CU.temp
      val x4943 = CU.temp
      val x4712_x4937 =  ScalarBuffer().wtPort(x4712_x4822_s)
      val x4709_x4936 =  ScalarBuffer().wtPort(x4709_x4741_s)
      val x4711_x4938 =  ScalarBuffer().wtPort(x4711_x4795_s)
      val x4710_x4935 =  ScalarBuffer().wtPort(x4710_x4768_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4709_x4936), CU.load(x4710_x4935)), op=FixAdd, results=List(x4943))
      Stage(operands=List(CU.load(x4711_x4938), CU.load(x4712_x4937)), op=FixAdd, results=List(x4946))
      Stage(operands=List(x4943, x4946), op=FixAdd, results=List(CU.scalarOut(bus_2463_s)))
    }
    val x4963_1 = Pipeline(name="x4963_1",parent=x4963) { implicit CU => 
      val x4955 = CU.temp
      val x4953 = CU.temp
      val x4713_x4940 =  ScalarBuffer().wtPort(x4713_x4849_s)
      val x4715_x4942 =  ScalarBuffer().wtPort(x4715_x4903_s)
      val x4714_x4939 =  ScalarBuffer().wtPort(x4714_x4876_s)
      val x4716_x4941 =  ScalarBuffer().wtPort(x4716_x4930_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4713_x4940), CU.load(x4714_x4939)), op=FixAdd, results=List(x4953))
      Stage(operands=List(CU.load(x4715_x4942), CU.load(x4716_x4941)), op=FixAdd, results=List(x4955))
      Stage(operands=List(x4953, x4955), op=FixAdd, results=List(CU.scalarOut(bus_2469_s)))
    }
    val x4963_2 = Pipeline(name="x4963_2",parent=x4963) { implicit CU => 
      val x4957 =  ScalarFIFO(size=1).wtPort(bus_2469_s)
      val x4948 =  ScalarFIFO(size=1).wtPort(bus_2463_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4948), CU.load(x4957)), op=FixAdd, results=List(CU.reduce))
      val (_, rr2473) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr2473), op=Bypass, results=List(CU.scalarOut(x4051_x4967_argout)))
    }
    
  }
}
