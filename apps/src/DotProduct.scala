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
    val x3872_x4593_x4601_v = Vector("x3872_x4593_x4601")
    val x3857_x4605_x4614_v = Vector("x3857_x4605_x4614")
    val x4429_b4931_x4437_b4933_s = Scalar("x4429_b4931_x4437_b4933")
    val x4215_x4224_data_v = Vector("x4215_x4224_data")
    val x4331_b4911_x4339_b4913_s = Scalar("x4331_b4911_x4339_b4913")
    val x3863_x4683_x4692_v = Vector("x3863_x4683_x4692")
    val x4352_x4361_data_v = Vector("x4352_x4361_data")
    val x4410_x4419_data_v = Vector("x4410_x4419_data")
    val x4040_x4049_data_v = Vector("x4040_x4049_data")
    val x4333_argin = ArgIn("x4333")
    val x4520_x4690_s = Scalar("x4520_x4690")
    val x4449_x4458_data_v = Vector("x4449_x4458_data")
    val x4517_x4651_s = Scalar("x4517_x4651")
    val x4255_argin = ArgIn("x4255")
    val x4097_b4862_x4105_b4864_s = Scalar("x4097_b4862_x4105_b4864")
    val x3923_x3932_data_v = Vector("x3923_x3932_data")
    val x4099_argin = ArgIn("x4099")
    val x4275_argin = ArgIn("x4275")
    val x4294_argin = ArgIn("x4294")
    val x4370_b4919_x4378_b4921_s = Scalar("x4370_b4919_x4378_b4921")
    val x4431_argin = ArgIn("x4431")
    val x4468_b4938_x4476_b4940_s = Scalar("x4468_b4938_x4476_b4940")
    val x3871_x4580_x4588_v = Vector("x3871_x4580_x4588")
    val x3943_argin = ArgIn("x3943")
    val x3854_x4566_x4575_v = Vector("x3854_x4566_x4575")
    val x4390_b4922_x4398_b4924_s = Scalar("x4390_b4922_x4398_b4924")
    val x4353_argin = ArgIn("x4353")
    val x3941_b4830_x3949_b4832_s = Scalar("x3941_b4830_x3949_b4832")
    val x4293_x4302_data_v = Vector("x4293_x4302_data")
    val x3883_b4819_x3891_b4821_s = Scalar("x3883_b4819_x3891_b4821")
    val bus_3966_s = Scalar("bus_3966")
    val x3885_argin = ArgIn("x3885")
    val x3858_x4618_x4627_v = Vector("x3858_x4618_x4627")
    val x3841_oc = OffChip("x3841")
    val x4118_x4127_data_v = Vector("x4118_x4127_data")
    val x4236_argin = ArgIn("x4236")
    val x3853_x4553_x4562_v = Vector("x3853_x4553_x4562")
    val x3856_x4592_x4601_v = Vector("x3856_x4592_x4601")
    val bus_3949_s = Scalar("bus_3949")
    val x4411_argin = ArgIn("x4411")
    val x4020_x4029_data_v = Vector("x4020_x4029_data")
    val x4513_x4599_s = Scalar("x4513_x4599")
    val x4175_b4878_x4183_b4880_s = Scalar("x4175_b4878_x4183_b4880")
    val x4195_b4883_x4203_b4885_s = Scalar("x4195_b4883_x4203_b4885")
    val x4450_argin = ArgIn("x4450")
    val x4510_x4560_s = Scalar("x4510_x4560")
    val x4488_x4497_data_v = Vector("x4488_x4497_data")
    val x4512_x4586_s = Scalar("x4512_x4586")
    val x4312_b4906_x4320_b4908_s = Scalar("x4312_b4906_x4320_b4908")
    val x4312_b4907_x4320_b4909_s = Scalar("x4312_b4907_x4320_b4909")
    val x4136_b4871_x4144_b4873_s = Scalar("x4136_b4871_x4144_b4873")
    val x3941_b4831_x3949_b4833_s = Scalar("x3941_b4831_x3949_b4833")
    val x3903_x3912_data_v = Vector("x3903_x3912_data")
    val x4117_b4867_x4125_b4869_s = Scalar("x4117_b4867_x4125_b4869")
    val x4487_b4942_x4495_b4944_s = Scalar("x4487_b4942_x4495_b4944")
    val x3869_x4554_x4562_v = Vector("x3869_x4554_x4562")
    val x4137_x4146_data_v = Vector("x4137_x4146_data")
    val x3852_x4540_x4549_v = Vector("x3852_x4540_x4549")
    val x4002_argin = ArgIn("x4002")
    val x3881_x4710_x4718_v = Vector("x3881_x4710_x4718")
    val x4314_argin = ArgIn("x4314")
    val x4372_argin = ArgIn("x4372")
    val x3880_x4697_x4705_v = Vector("x3880_x4697_x4705")
    val x4001_x4010_data_v = Vector("x4001_x4010_data")
    val x3879_x4684_x4692_v = Vector("x3879_x4684_x4692")
    val x4274_x4283_data_v = Vector("x4274_x4283_data")
    val x4351_b4915_x4359_b4917_s = Scalar("x4351_b4915_x4359_b4917")
    val x4021_argin = ArgIn("x4021")
    val x3883_b4818_x3891_b4820_s = Scalar("x3883_b4818_x3891_b4820")
    val x4292_b4902_x4300_b4904_s = Scalar("x4292_b4902_x4300_b4904")
    val x3875_x4632_x4640_v = Vector("x3875_x4632_x4640")
    val x3922_b4827_x3930_b4829_s = Scalar("x3922_b4827_x3930_b4829")
    val x3865_x4709_x4718_v = Vector("x3865_x4709_x4718")
    val x3876_x4645_x4653_v = Vector("x3876_x4645_x4653")
    val x4522_x4716_s = Scalar("x4522_x4716")
    val x4370_b4918_x4378_b4920_s = Scalar("x4370_b4918_x4378_b4920")
    val x4058_b4855_x4066_b4857_s = Scalar("x4058_b4855_x4066_b4857")
    val x4351_b4914_x4359_b4916_s = Scalar("x4351_b4914_x4359_b4916")
    val x3962_x3971_data_v = Vector("x3962_x3971_data")
    val x4234_b4891_x4242_b4893_s = Scalar("x4234_b4891_x4242_b4893")
    val x3855_x4579_x4588_v = Vector("x3855_x4579_x4588")
    val x4516_x4638_s = Scalar("x4516_x4638")
    val bus_3943_s = Scalar("bus_3943")
    val x4514_x4612_s = Scalar("x4514_x4612")
    val x4216_argin = ArgIn("x4216")
    val x4508_x4534_s = Scalar("x4508_x4534")
    val x3882_x4723_x4731_v = Vector("x3882_x4723_x4731")
    val x4138_argin = ArgIn("x4138")
    val x4390_b4923_x4398_b4925_s = Scalar("x4390_b4923_x4398_b4925")
    val bus_3963_s = Scalar("bus_3963")
    val x4019_b4846_x4027_b4848_s = Scalar("x4019_b4846_x4027_b4848")
    val x3844_x4797_argout = ArgOut("x3844_x4797")
    val x4509_x4547_s = Scalar("x4509_x4547")
    val x4521_x4703_s = Scalar("x4521_x4703")
    val x4254_x4263_data_v = Vector("x4254_x4263_data")
    val x4448_b4935_x4456_b4937_s = Scalar("x4448_b4935_x4456_b4937")
    val x4041_argin = ArgIn("x4041")
    val x4197_argin = ArgIn("x4197")
    val x4469_x4478_data_v = Vector("x4469_x4478_data")
    val x4292_b4903_x4300_b4905_s = Scalar("x4292_b4903_x4300_b4905")
    val x4234_b4890_x4242_b4892_s = Scalar("x4234_b4890_x4242_b4892")
    val x4409_b4927_x4417_b4929_s = Scalar("x4409_b4927_x4417_b4929")
    val x4176_x4185_data_v = Vector("x4176_x4185_data")
    val x4487_b4943_x4495_b4945_s = Scalar("x4487_b4943_x4495_b4945")
    val x4519_x4677_s = Scalar("x4519_x4677")
    val x4331_b4910_x4339_b4912_s = Scalar("x4331_b4910_x4339_b4912")
    val x4313_x4322_data_v = Vector("x4313_x4322_data")
    val x4468_b4939_x4476_b4941_s = Scalar("x4468_b4939_x4476_b4941")
    val x3961_b4834_x3969_b4836_s = Scalar("x3961_b4834_x3969_b4836")
    val bus_3958_s = Scalar("bus_3958")
    val x3961_b4835_x3969_b4837_s = Scalar("x3961_b4835_x3969_b4837")
    val x3902_b4822_x3910_b4824_s = Scalar("x3902_b4822_x3910_b4824")
    val x4253_b4894_x4261_b4896_s = Scalar("x4253_b4894_x4261_b4896")
    val x4059_x4068_data_v = Vector("x4059_x4068_data")
    val x3874_x4619_x4627_v = Vector("x3874_x4619_x4627")
    val x4039_b4851_x4047_b4853_s = Scalar("x4039_b4851_x4047_b4853")
    val x3861_x4657_x4666_v = Vector("x3861_x4657_x4666")
    val x4136_b4870_x4144_b4872_s = Scalar("x4136_b4870_x4144_b4872")
    val x3862_x4670_x4679_v = Vector("x3862_x4670_x4679")
    val x4098_x4107_data_v = Vector("x4098_x4107_data")
    val x3981_x3990_data_v = Vector("x3981_x3990_data")
    val x4429_b4930_x4437_b4932_s = Scalar("x4429_b4930_x4437_b4932")
    val x4253_b4895_x4261_b4897_s = Scalar("x4253_b4895_x4261_b4897")
    val x4080_argin = ArgIn("x4080")
    val x3870_x4567_x4575_v = Vector("x3870_x4567_x4575")
    val x4273_b4899_x4281_b4901_s = Scalar("x4273_b4899_x4281_b4901")
    val x4195_b4882_x4203_b4884_s = Scalar("x4195_b4882_x4203_b4884")
    val x4156_b4874_x4164_b4876_s = Scalar("x4156_b4874_x4164_b4876")
    val x4332_x4341_data_v = Vector("x4332_x4341_data")
    val x4060_argin = ArgIn("x4060")
    val bus_3960_s = Scalar("bus_3960")
    val x4158_argin = ArgIn("x4158")
    val x4214_b4886_x4222_b4888_s = Scalar("x4214_b4886_x4222_b4888")
    val x4523_x4729_s = Scalar("x4523_x4729")
    val x4177_argin = ArgIn("x4177")
    val x4470_argin = ArgIn("x4470")
    val x4078_b4859_x4086_b4861_s = Scalar("x4078_b4859_x4086_b4861")
    val x4156_b4875_x4164_b4877_s = Scalar("x4156_b4875_x4164_b4877")
    val x4371_x4380_data_v = Vector("x4371_x4380_data")
    val x4515_x4625_s = Scalar("x4515_x4625")
    val x3878_x4671_x4679_v = Vector("x3878_x4671_x4679")
    val x3867_x4528_x4536_v = Vector("x3867_x4528_x4536")
    val x3860_x4644_x4653_v = Vector("x3860_x4644_x4653")
    val x3877_x4658_x4666_v = Vector("x3877_x4658_x4666")
    val x4518_x4664_s = Scalar("x4518_x4664")
    val x3843_oc = OffChip("x3843")
    val x4214_b4887_x4222_b4889_s = Scalar("x4214_b4887_x4222_b4889")
    val x3868_x4541_x4549_v = Vector("x3868_x4541_x4549")
    val x4409_b4926_x4417_b4928_s = Scalar("x4409_b4926_x4417_b4928")
    val x4058_b4854_x4066_b4856_s = Scalar("x4058_b4854_x4066_b4856")
    val x3866_x4722_x4731_v = Vector("x3866_x4722_x4731")
    val bus_3950_s = Scalar("bus_3950")
    val x4000_b4842_x4008_b4844_s = Scalar("x4000_b4842_x4008_b4844")
    val x3904_argin = ArgIn("x3904")
    val x4235_x4244_data_v = Vector("x4235_x4244_data")
    val x4078_b4858_x4086_b4860_s = Scalar("x4078_b4858_x4086_b4860")
    val x4019_b4847_x4027_b4849_s = Scalar("x4019_b4847_x4027_b4849")
    val x3873_x4606_x4614_v = Vector("x3873_x4606_x4614")
    val x3902_b4823_x3910_b4825_s = Scalar("x3902_b4823_x3910_b4825")
    val x4391_x4400_data_v = Vector("x4391_x4400_data")
    val x4511_x4573_s = Scalar("x4511_x4573")
    val x4119_argin = ArgIn("x4119")
    val x3922_b4826_x3930_b4828_s = Scalar("x3922_b4826_x3930_b4828")
    val x4448_b4934_x4456_b4936_s = Scalar("x4448_b4934_x4456_b4936")
    val x4175_b4879_x4183_b4881_s = Scalar("x4175_b4879_x4183_b4881")
    val x4157_x4166_data_v = Vector("x4157_x4166_data")
    val x4117_b4866_x4125_b4868_s = Scalar("x4117_b4866_x4125_b4868")
    val x4000_b4843_x4008_b4845_s = Scalar("x4000_b4843_x4008_b4845")
    val x4273_b4898_x4281_b4900_s = Scalar("x4273_b4898_x4281_b4900")
    val x4196_x4205_data_v = Vector("x4196_x4205_data")
    val x3837_argin = ArgIn("x3837")
    val x4392_argin = ArgIn("x4392")
    val x3859_x4631_x4640_v = Vector("x3859_x4631_x4640")
    val x3942_x3951_data_v = Vector("x3942_x3951_data")
    val x3924_argin = ArgIn("x3924")
    val x3963_argin = ArgIn("x3963")
    val x4489_argin = ArgIn("x4489")
    val x3982_argin = ArgIn("x3982")
    val x4079_x4088_data_v = Vector("x4079_x4088_data")
    val x3884_x3893_data_v = Vector("x3884_x3893_data")
    val x4039_b4850_x4047_b4852_s = Scalar("x4039_b4850_x4047_b4852")
    val x4097_b4863_x4105_b4865_s = Scalar("x4097_b4863_x4105_b4865")
    val x3851_x4527_x4536_v = Vector("x3851_x4527_x4536")
    val x3864_x4696_x4705_v = Vector("x3864_x4696_x4705")
    val x4430_x4439_data_v = Vector("x4430_x4439_data")
    val x3980_b4839_x3988_b4841_s = Scalar("x3980_b4839_x3988_b4841")
    val x3980_b4838_x3988_b4840_s = Scalar("x3980_b4838_x3988_b4840")
    val x4799 = Sequential(name="x4799",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4799_unit = CounterChain(name = "x4799_unit", ctr1)
    }
    val x4795 = MetaPipeline(name="x4795",parent=x4799) { implicit CU => 
      val x3837_x3848 =  ScalarBuffer().wtPort(x3837_argin)
      val ctr2 = Counter(min=Const(0), max=x3837_x3848.load, step=Const(320), par=16) // Counter
      val x3850 = CounterChain(name = "x3850", ctr2)
    }
    val x3851_dsp0 = MemoryPipeline(name="x3851_dsp0",parent="x4795") { implicit CU => 
      val x3899_x3899 =  VectorFIFO(size=1).wtPort(x3884_x3893_data_v)
      val x3895 = CounterChain.copy("x3900", "x3895")
      val x4525 = CounterChain.copy("x4536_0", "x4525")
      val x3851_x4527 =  SRAM(size=320,banking = Strided(1)).wtPort(x3899_x3899.readPort).rdPort(x3851_x4527_x4536_v).rdAddr(x4525(0)).wtAddr(x3895(0))
      var stage: List[Stage] = Nil
    }
    val x3852_dsp0 = MemoryPipeline(name="x3852_dsp0",parent="x4795") { implicit CU => 
      val x3938_x3938 =  VectorFIFO(size=1).wtPort(x3923_x3932_data_v)
      val x3934 = CounterChain.copy("x3939", "x3934")
      val x4538 = CounterChain.copy("x4549_0", "x4538")
      val x3852_x4540 =  SRAM(size=320,banking = Strided(1)).wtPort(x3938_x3938.readPort).rdPort(x3852_x4540_x4549_v).rdAddr(x4538(0)).wtAddr(x3934(0))
      var stage: List[Stage] = Nil
    }
    val x3853_dsp0 = MemoryPipeline(name="x3853_dsp0",parent="x4795") { implicit CU => 
      val x3977_x3977 =  VectorFIFO(size=1).wtPort(x3962_x3971_data_v)
      val x3973 = CounterChain.copy("x3978", "x3973")
      val x4551 = CounterChain.copy("x4562_0", "x4551")
      val x3853_x4553 =  SRAM(size=320,banking = Strided(1)).wtPort(x3977_x3977.readPort).rdPort(x3853_x4553_x4562_v).rdAddr(x4551(0)).wtAddr(x3973(0))
      var stage: List[Stage] = Nil
    }
    val x3854_dsp0 = MemoryPipeline(name="x3854_dsp0",parent="x4795") { implicit CU => 
      val x4016_x4016 =  VectorFIFO(size=1).wtPort(x4001_x4010_data_v)
      val x4012 = CounterChain.copy("x4017", "x4012")
      val x4564 = CounterChain.copy("x4575_0", "x4564")
      val x3854_x4566 =  SRAM(size=320,banking = Strided(1)).wtPort(x4016_x4016.readPort).rdPort(x3854_x4566_x4575_v).rdAddr(x4564(0)).wtAddr(x4012(0))
      var stage: List[Stage] = Nil
    }
    val x3855_dsp0 = MemoryPipeline(name="x3855_dsp0",parent="x4795") { implicit CU => 
      val x4055_x4055 =  VectorFIFO(size=1).wtPort(x4040_x4049_data_v)
      val x4051 = CounterChain.copy("x4056", "x4051")
      val x4577 = CounterChain.copy("x4588_0", "x4577")
      val x3855_x4579 =  SRAM(size=320,banking = Strided(1)).wtPort(x4055_x4055.readPort).rdPort(x3855_x4579_x4588_v).rdAddr(x4577(0)).wtAddr(x4051(0))
      var stage: List[Stage] = Nil
    }
    val x3856_dsp0 = MemoryPipeline(name="x3856_dsp0",parent="x4795") { implicit CU => 
      val x4094_x4094 =  VectorFIFO(size=1).wtPort(x4079_x4088_data_v)
      val x4090 = CounterChain.copy("x4095", "x4090")
      val x4590 = CounterChain.copy("x4601_0", "x4590")
      val x3856_x4592 =  SRAM(size=320,banking = Strided(1)).wtPort(x4094_x4094.readPort).rdPort(x3856_x4592_x4601_v).rdAddr(x4590(0)).wtAddr(x4090(0))
      var stage: List[Stage] = Nil
    }
    val x3857_dsp0 = MemoryPipeline(name="x3857_dsp0",parent="x4795") { implicit CU => 
      val x4133_x4133 =  VectorFIFO(size=1).wtPort(x4118_x4127_data_v)
      val x4129 = CounterChain.copy("x4134", "x4129")
      val x4603 = CounterChain.copy("x4614_0", "x4603")
      val x3857_x4605 =  SRAM(size=320,banking = Strided(1)).wtPort(x4133_x4133.readPort).rdPort(x3857_x4605_x4614_v).rdAddr(x4603(0)).wtAddr(x4129(0))
      var stage: List[Stage] = Nil
    }
    val x3858_dsp0 = MemoryPipeline(name="x3858_dsp0",parent="x4795") { implicit CU => 
      val x4172_x4172 =  VectorFIFO(size=1).wtPort(x4157_x4166_data_v)
      val x4168 = CounterChain.copy("x4173", "x4168")
      val x4616 = CounterChain.copy("x4627_0", "x4616")
      val x3858_x4618 =  SRAM(size=320,banking = Strided(1)).wtPort(x4172_x4172.readPort).rdPort(x3858_x4618_x4627_v).rdAddr(x4616(0)).wtAddr(x4168(0))
      var stage: List[Stage] = Nil
    }
    val x3859_dsp0 = MemoryPipeline(name="x3859_dsp0",parent="x4795") { implicit CU => 
      val x4211_x4211 =  VectorFIFO(size=1).wtPort(x4196_x4205_data_v)
      val x4207 = CounterChain.copy("x4212", "x4207")
      val x4629 = CounterChain.copy("x4640_0", "x4629")
      val x3859_x4631 =  SRAM(size=320,banking = Strided(1)).wtPort(x4211_x4211.readPort).rdPort(x3859_x4631_x4640_v).rdAddr(x4629(0)).wtAddr(x4207(0))
      var stage: List[Stage] = Nil
    }
    val x3860_dsp0 = MemoryPipeline(name="x3860_dsp0",parent="x4795") { implicit CU => 
      val x4250_x4250 =  VectorFIFO(size=1).wtPort(x4235_x4244_data_v)
      val x4246 = CounterChain.copy("x4251", "x4246")
      val x4642 = CounterChain.copy("x4653_0", "x4642")
      val x3860_x4644 =  SRAM(size=320,banking = Strided(1)).wtPort(x4250_x4250.readPort).rdPort(x3860_x4644_x4653_v).rdAddr(x4642(0)).wtAddr(x4246(0))
      var stage: List[Stage] = Nil
    }
    val x3861_dsp0 = MemoryPipeline(name="x3861_dsp0",parent="x4795") { implicit CU => 
      val x4289_x4289 =  VectorFIFO(size=1).wtPort(x4274_x4283_data_v)
      val x4285 = CounterChain.copy("x4290", "x4285")
      val x4655 = CounterChain.copy("x4666_0", "x4655")
      val x3861_x4657 =  SRAM(size=320,banking = Strided(1)).wtPort(x4289_x4289.readPort).rdPort(x3861_x4657_x4666_v).rdAddr(x4655(0)).wtAddr(x4285(0))
      var stage: List[Stage] = Nil
    }
    val x3862_dsp0 = MemoryPipeline(name="x3862_dsp0",parent="x4795") { implicit CU => 
      val x4328_x4328 =  VectorFIFO(size=1).wtPort(x4313_x4322_data_v)
      val x4324 = CounterChain.copy("x4329", "x4324")
      val x4668 = CounterChain.copy("x4679_0", "x4668")
      val x3862_x4670 =  SRAM(size=320,banking = Strided(1)).wtPort(x4328_x4328.readPort).rdPort(x3862_x4670_x4679_v).rdAddr(x4668(0)).wtAddr(x4324(0))
      var stage: List[Stage] = Nil
    }
    val x3863_dsp0 = MemoryPipeline(name="x3863_dsp0",parent="x4795") { implicit CU => 
      val x4367_x4367 =  VectorFIFO(size=1).wtPort(x4352_x4361_data_v)
      val x4363 = CounterChain.copy("x4368", "x4363")
      val x4681 = CounterChain.copy("x4692_0", "x4681")
      val x3863_x4683 =  SRAM(size=320,banking = Strided(1)).wtPort(x4367_x4367.readPort).rdPort(x3863_x4683_x4692_v).rdAddr(x4681(0)).wtAddr(x4363(0))
      var stage: List[Stage] = Nil
    }
    val x3864_dsp0 = MemoryPipeline(name="x3864_dsp0",parent="x4795") { implicit CU => 
      val x4406_x4406 =  VectorFIFO(size=1).wtPort(x4391_x4400_data_v)
      val x4402 = CounterChain.copy("x4407", "x4402")
      val x4694 = CounterChain.copy("x4705_0", "x4694")
      val x3864_x4696 =  SRAM(size=320,banking = Strided(1)).wtPort(x4406_x4406.readPort).rdPort(x3864_x4696_x4705_v).rdAddr(x4694(0)).wtAddr(x4402(0))
      var stage: List[Stage] = Nil
    }
    val x3865_dsp0 = MemoryPipeline(name="x3865_dsp0",parent="x4795") { implicit CU => 
      val x4445_x4445 =  VectorFIFO(size=1).wtPort(x4430_x4439_data_v)
      val x4441 = CounterChain.copy("x4446", "x4441")
      val x4707 = CounterChain.copy("x4718_0", "x4707")
      val x3865_x4709 =  SRAM(size=320,banking = Strided(1)).wtPort(x4445_x4445.readPort).rdPort(x3865_x4709_x4718_v).rdAddr(x4707(0)).wtAddr(x4441(0))
      var stage: List[Stage] = Nil
    }
    val x3866_dsp0 = MemoryPipeline(name="x3866_dsp0",parent="x4795") { implicit CU => 
      val x4484_x4484 =  VectorFIFO(size=1).wtPort(x4469_x4478_data_v)
      val x4480 = CounterChain.copy("x4485", "x4480")
      val x4720 = CounterChain.copy("x4731_0", "x4720")
      val x3866_x4722 =  SRAM(size=320,banking = Strided(1)).wtPort(x4484_x4484.readPort).rdPort(x3866_x4722_x4731_v).rdAddr(x4720(0)).wtAddr(x4480(0))
      var stage: List[Stage] = Nil
    }
    val x3867_dsp0 = MemoryPipeline(name="x3867_dsp0",parent="x4795") { implicit CU => 
      val x3918_x3918 =  VectorFIFO(size=1).wtPort(x3903_x3912_data_v)
      val x3914 = CounterChain.copy("x3919", "x3914")
      val x4525 = CounterChain.copy("x4536_0", "x4525")
      val x3867_x4528 =  SRAM(size=320,banking = Strided(1)).wtPort(x3918_x3918.readPort).rdPort(x3867_x4528_x4536_v).rdAddr(x4525(0)).wtAddr(x3914(0))
      var stage: List[Stage] = Nil
    }
    val x3868_dsp0 = MemoryPipeline(name="x3868_dsp0",parent="x4795") { implicit CU => 
      val x3957_x3957 =  VectorFIFO(size=1).wtPort(x3942_x3951_data_v)
      val x3953 = CounterChain.copy("x3958", "x3953")
      val x4538 = CounterChain.copy("x4549_0", "x4538")
      val x3868_x4541 =  SRAM(size=320,banking = Strided(1)).wtPort(x3957_x3957.readPort).rdPort(x3868_x4541_x4549_v).rdAddr(x4538(0)).wtAddr(x3953(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp0 = MemoryPipeline(name="x3869_dsp0",parent="x4795") { implicit CU => 
      val x3996_x3996 =  VectorFIFO(size=1).wtPort(x3981_x3990_data_v)
      val x3992 = CounterChain.copy("x3997", "x3992")
      val x4551 = CounterChain.copy("x4562_0", "x4551")
      val x3869_x4554 =  SRAM(size=320,banking = Strided(1)).wtPort(x3996_x3996.readPort).rdPort(x3869_x4554_x4562_v).rdAddr(x4551(0)).wtAddr(x3992(0))
      var stage: List[Stage] = Nil
    }
    val x3870_dsp0 = MemoryPipeline(name="x3870_dsp0",parent="x4795") { implicit CU => 
      val x4035_x4035 =  VectorFIFO(size=1).wtPort(x4020_x4029_data_v)
      val x4031 = CounterChain.copy("x4036", "x4031")
      val x4564 = CounterChain.copy("x4575_0", "x4564")
      val x3870_x4567 =  SRAM(size=320,banking = Strided(1)).wtPort(x4035_x4035.readPort).rdPort(x3870_x4567_x4575_v).rdAddr(x4564(0)).wtAddr(x4031(0))
      var stage: List[Stage] = Nil
    }
    val x3871_dsp0 = MemoryPipeline(name="x3871_dsp0",parent="x4795") { implicit CU => 
      val x4074_x4074 =  VectorFIFO(size=1).wtPort(x4059_x4068_data_v)
      val x4070 = CounterChain.copy("x4075", "x4070")
      val x4577 = CounterChain.copy("x4588_0", "x4577")
      val x3871_x4580 =  SRAM(size=320,banking = Strided(1)).wtPort(x4074_x4074.readPort).rdPort(x3871_x4580_x4588_v).rdAddr(x4577(0)).wtAddr(x4070(0))
      var stage: List[Stage] = Nil
    }
    val x3872_dsp0 = MemoryPipeline(name="x3872_dsp0",parent="x4795") { implicit CU => 
      val x4113_x4113 =  VectorFIFO(size=1).wtPort(x4098_x4107_data_v)
      val x4109 = CounterChain.copy("x4114", "x4109")
      val x4590 = CounterChain.copy("x4601_0", "x4590")
      val x3872_x4593 =  SRAM(size=320,banking = Strided(1)).wtPort(x4113_x4113.readPort).rdPort(x3872_x4593_x4601_v).rdAddr(x4590(0)).wtAddr(x4109(0))
      var stage: List[Stage] = Nil
    }
    val x3873_dsp0 = MemoryPipeline(name="x3873_dsp0",parent="x4795") { implicit CU => 
      val x4152_x4152 =  VectorFIFO(size=1).wtPort(x4137_x4146_data_v)
      val x4148 = CounterChain.copy("x4153", "x4148")
      val x4603 = CounterChain.copy("x4614_0", "x4603")
      val x3873_x4606 =  SRAM(size=320,banking = Strided(1)).wtPort(x4152_x4152.readPort).rdPort(x3873_x4606_x4614_v).rdAddr(x4603(0)).wtAddr(x4148(0))
      var stage: List[Stage] = Nil
    }
    val x3874_dsp0 = MemoryPipeline(name="x3874_dsp0",parent="x4795") { implicit CU => 
      val x4191_x4191 =  VectorFIFO(size=1).wtPort(x4176_x4185_data_v)
      val x4187 = CounterChain.copy("x4192", "x4187")
      val x4616 = CounterChain.copy("x4627_0", "x4616")
      val x3874_x4619 =  SRAM(size=320,banking = Strided(1)).wtPort(x4191_x4191.readPort).rdPort(x3874_x4619_x4627_v).rdAddr(x4616(0)).wtAddr(x4187(0))
      var stage: List[Stage] = Nil
    }
    val x3875_dsp0 = MemoryPipeline(name="x3875_dsp0",parent="x4795") { implicit CU => 
      val x4230_x4230 =  VectorFIFO(size=1).wtPort(x4215_x4224_data_v)
      val x4226 = CounterChain.copy("x4231", "x4226")
      val x4629 = CounterChain.copy("x4640_0", "x4629")
      val x3875_x4632 =  SRAM(size=320,banking = Strided(1)).wtPort(x4230_x4230.readPort).rdPort(x3875_x4632_x4640_v).rdAddr(x4629(0)).wtAddr(x4226(0))
      var stage: List[Stage] = Nil
    }
    val x3876_dsp0 = MemoryPipeline(name="x3876_dsp0",parent="x4795") { implicit CU => 
      val x4269_x4269 =  VectorFIFO(size=1).wtPort(x4254_x4263_data_v)
      val x4265 = CounterChain.copy("x4270", "x4265")
      val x4642 = CounterChain.copy("x4653_0", "x4642")
      val x3876_x4645 =  SRAM(size=320,banking = Strided(1)).wtPort(x4269_x4269.readPort).rdPort(x3876_x4645_x4653_v).rdAddr(x4642(0)).wtAddr(x4265(0))
      var stage: List[Stage] = Nil
    }
    val x3877_dsp0 = MemoryPipeline(name="x3877_dsp0",parent="x4795") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4293_x4302_data_v)
      val x4304 = CounterChain.copy("x4309", "x4304")
      val x4655 = CounterChain.copy("x4666_0", "x4655")
      val x3877_x4658 =  SRAM(size=320,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x3877_x4658_x4666_v).rdAddr(x4655(0)).wtAddr(x4304(0))
      var stage: List[Stage] = Nil
    }
    val x3878_dsp0 = MemoryPipeline(name="x3878_dsp0",parent="x4795") { implicit CU => 
      val x4347_x4347 =  VectorFIFO(size=1).wtPort(x4332_x4341_data_v)
      val x4343 = CounterChain.copy("x4348", "x4343")
      val x4668 = CounterChain.copy("x4679_0", "x4668")
      val x3878_x4671 =  SRAM(size=320,banking = Strided(1)).wtPort(x4347_x4347.readPort).rdPort(x3878_x4671_x4679_v).rdAddr(x4668(0)).wtAddr(x4343(0))
      var stage: List[Stage] = Nil
    }
    val x3879_dsp0 = MemoryPipeline(name="x3879_dsp0",parent="x4795") { implicit CU => 
      val x4386_x4386 =  VectorFIFO(size=1).wtPort(x4371_x4380_data_v)
      val x4382 = CounterChain.copy("x4387", "x4382")
      val x4681 = CounterChain.copy("x4692_0", "x4681")
      val x3879_x4684 =  SRAM(size=320,banking = Strided(1)).wtPort(x4386_x4386.readPort).rdPort(x3879_x4684_x4692_v).rdAddr(x4681(0)).wtAddr(x4382(0))
      var stage: List[Stage] = Nil
    }
    val x3880_dsp0 = MemoryPipeline(name="x3880_dsp0",parent="x4795") { implicit CU => 
      val x4425_x4425 =  VectorFIFO(size=1).wtPort(x4410_x4419_data_v)
      val x4421 = CounterChain.copy("x4426", "x4421")
      val x4694 = CounterChain.copy("x4705_0", "x4694")
      val x3880_x4697 =  SRAM(size=320,banking = Strided(1)).wtPort(x4425_x4425.readPort).rdPort(x3880_x4697_x4705_v).rdAddr(x4694(0)).wtAddr(x4421(0))
      var stage: List[Stage] = Nil
    }
    val x3881_dsp0 = MemoryPipeline(name="x3881_dsp0",parent="x4795") { implicit CU => 
      val x4464_x4464 =  VectorFIFO(size=1).wtPort(x4449_x4458_data_v)
      val x4460 = CounterChain.copy("x4465", "x4460")
      val x4707 = CounterChain.copy("x4718_0", "x4707")
      val x3881_x4710 =  SRAM(size=320,banking = Strided(1)).wtPort(x4464_x4464.readPort).rdPort(x3881_x4710_x4718_v).rdAddr(x4707(0)).wtAddr(x4460(0))
      var stage: List[Stage] = Nil
    }
    val x3882_dsp0 = MemoryPipeline(name="x3882_dsp0",parent="x4795") { implicit CU => 
      val x4503_x4503 =  VectorFIFO(size=1).wtPort(x4488_x4497_data_v)
      val x4499 = CounterChain.copy("x4504", "x4499")
      val x4720 = CounterChain.copy("x4731_0", "x4720")
      val x3882_x4723 =  SRAM(size=320,banking = Strided(1)).wtPort(x4503_x4503.readPort).rdPort(x3882_x4723_x4731_v).rdAddr(x4720(0)).wtAddr(x4499(0))
      var stage: List[Stage] = Nil
    }
    val x3901 = StreamController(name="x3901",parent=x4795) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3901_unit = CounterChain(name = "x3901_unit", ctr3)
    }
    val x3892_0 = Pipeline(name="x3892_0",parent=x3901) { implicit CU => 
      val x3886 = CU.temp
      val x3885 =  ScalarBuffer().wtPort(x3885_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3892_unit = CounterChain(name = "x3892_unit", ctr4)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x3886))
      Stage(operands=List(x3886, CU.load(x3885)), op=FixAdd, results=List(CU.scalarOut(x3883_b4818_x3891_b4820_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x3883_b4819_x3891_b4821_s)))
    }
    val x3893 = MemoryController(name="x3893",parent=x3901,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x3883_b4819_x3893 =  ScalarFIFO(name="size",size=1).wtPort(x3883_b4819_x3891_b4821_s)
      val x3883_b4818_x3893 =  ScalarFIFO(name="offset",size=1).wtPort(x3883_b4818_x3891_b4820_s)
      CU.newVout("data", x3884_x3893_data_v)
    }
    val x3900 = Pipeline(name="x3900",parent=x3901) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x3895 = CounterChain(name = "x3895", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3920 = StreamController(name="x3920",parent=x4795) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3920_unit = CounterChain(name = "x3920_unit", ctr6)
    }
    val x3911_0 = Pipeline(name="x3911_0",parent=x3920) { implicit CU => 
      val x3905 = CU.temp
      val x3904 =  ScalarBuffer().wtPort(x3904_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3911_unit = CounterChain(name = "x3911_unit", ctr7)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x3905))
      Stage(operands=List(x3905, CU.load(x3904)), op=FixAdd, results=List(CU.scalarOut(x3902_b4822_x3910_b4824_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x3902_b4823_x3910_b4825_s)))
    }
    val x3912 = MemoryController(name="x3912",parent=x3920,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x3902_b4823_x3912 =  ScalarFIFO(name="size",size=1).wtPort(x3902_b4823_x3910_b4825_s)
      val x3902_b4822_x3912 =  ScalarFIFO(name="offset",size=1).wtPort(x3902_b4822_x3910_b4824_s)
      CU.newVout("data", x3903_x3912_data_v)
    }
    val x3919 = Pipeline(name="x3919",parent=x3920) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x3914 = CounterChain(name = "x3914", ctr8)
      var stage: List[Stage] = Nil
    }
    val x3940 = StreamController(name="x3940",parent=x4795) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3940_unit = CounterChain(name = "x3940_unit", ctr9)
    }
    val x3931_0 = Pipeline(name="x3931_0",parent=x3940) { implicit CU => 
      val x3925 = CU.temp
      val x3924 =  ScalarBuffer().wtPort(x3924_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3931_unit = CounterChain(name = "x3931_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x3925))
      Stage(operands=List(x3925, CU.load(x3924)), op=FixAdd, results=List(CU.scalarOut(x3922_b4826_x3930_b4828_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x3922_b4827_x3930_b4829_s)))
    }
    val x3932 = MemoryController(name="x3932",parent=x3940,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x3922_b4826_x3932 =  ScalarFIFO(name="offset",size=1).wtPort(x3922_b4826_x3930_b4828_s)
      val x3922_b4827_x3932 =  ScalarFIFO(name="size",size=1).wtPort(x3922_b4827_x3930_b4829_s)
      CU.newVout("data", x3923_x3932_data_v)
    }
    val x3939 = Pipeline(name="x3939",parent=x3940) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x3934 = CounterChain(name = "x3934", ctr11)
      var stage: List[Stage] = Nil
    }
    val x3959 = StreamController(name="x3959",parent=x4795) { implicit CU => 
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3959_unit = CounterChain(name = "x3959_unit", ctr12)
    }
    val x3950_0 = Pipeline(name="x3950_0",parent=x3959) { implicit CU => 
      val x3944 = CU.temp
      val x3943 =  ScalarBuffer().wtPort(x3943_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3950_unit = CounterChain(name = "x3950_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x3944))
      Stage(operands=List(x3944, CU.load(x3943)), op=FixAdd, results=List(CU.scalarOut(x3941_b4830_x3949_b4832_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x3941_b4831_x3949_b4833_s)))
    }
    val x3951 = MemoryController(name="x3951",parent=x3959,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x3941_b4831_x3951 =  ScalarFIFO(name="size",size=1).wtPort(x3941_b4831_x3949_b4833_s)
      val x3941_b4830_x3951 =  ScalarFIFO(name="offset",size=1).wtPort(x3941_b4830_x3949_b4832_s)
      CU.newVout("data", x3942_x3951_data_v)
    }
    val x3958 = Pipeline(name="x3958",parent=x3959) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x3953 = CounterChain(name = "x3953", ctr14)
      var stage: List[Stage] = Nil
    }
    val x3979 = StreamController(name="x3979",parent=x4795) { implicit CU => 
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3979_unit = CounterChain(name = "x3979_unit", ctr15)
    }
    val x3970_0 = Pipeline(name="x3970_0",parent=x3979) { implicit CU => 
      val x3964 = CU.temp
      val x3963 =  ScalarBuffer().wtPort(x3963_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3970_unit = CounterChain(name = "x3970_unit", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x3964))
      Stage(operands=List(x3964, CU.load(x3963)), op=FixAdd, results=List(CU.scalarOut(x3961_b4834_x3969_b4836_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x3961_b4835_x3969_b4837_s)))
    }
    val x3971 = MemoryController(name="x3971",parent=x3979,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x3961_b4835_x3971 =  ScalarFIFO(name="size",size=1).wtPort(x3961_b4835_x3969_b4837_s)
      val x3961_b4834_x3971 =  ScalarFIFO(name="offset",size=1).wtPort(x3961_b4834_x3969_b4836_s)
      CU.newVout("data", x3962_x3971_data_v)
    }
    val x3978 = Pipeline(name="x3978",parent=x3979) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x3973 = CounterChain(name = "x3973", ctr17)
      var stage: List[Stage] = Nil
    }
    val x3998 = StreamController(name="x3998",parent=x4795) { implicit CU => 
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3998_unit = CounterChain(name = "x3998_unit", ctr18)
    }
    val x3989_0 = Pipeline(name="x3989_0",parent=x3998) { implicit CU => 
      val x3983 = CU.temp
      val x3982 =  ScalarBuffer().wtPort(x3982_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3989_unit = CounterChain(name = "x3989_unit", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x3983))
      Stage(operands=List(x3983, CU.load(x3982)), op=FixAdd, results=List(CU.scalarOut(x3980_b4838_x3988_b4840_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x3980_b4839_x3988_b4841_s)))
    }
    val x3990 = MemoryController(name="x3990",parent=x3998,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x3980_b4838_x3990 =  ScalarFIFO(name="offset",size=1).wtPort(x3980_b4838_x3988_b4840_s)
      val x3980_b4839_x3990 =  ScalarFIFO(name="size",size=1).wtPort(x3980_b4839_x3988_b4841_s)
      CU.newVout("data", x3981_x3990_data_v)
    }
    val x3997 = Pipeline(name="x3997",parent=x3998) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x3992 = CounterChain(name = "x3992", ctr20)
      var stage: List[Stage] = Nil
    }
    val x4018 = StreamController(name="x4018",parent=x4795) { implicit CU => 
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4018_unit = CounterChain(name = "x4018_unit", ctr21)
    }
    val x4009_0 = Pipeline(name="x4009_0",parent=x4018) { implicit CU => 
      val x4003 = CU.temp
      val x4002 =  ScalarBuffer().wtPort(x4002_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4009_unit = CounterChain(name = "x4009_unit", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4003))
      Stage(operands=List(x4003, CU.load(x4002)), op=FixAdd, results=List(CU.scalarOut(x4000_b4842_x4008_b4844_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4000_b4843_x4008_b4845_s)))
    }
    val x4010 = MemoryController(name="x4010",parent=x4018,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4000_b4843_x4010 =  ScalarFIFO(name="size",size=1).wtPort(x4000_b4843_x4008_b4845_s)
      val x4000_b4842_x4010 =  ScalarFIFO(name="offset",size=1).wtPort(x4000_b4842_x4008_b4844_s)
      CU.newVout("data", x4001_x4010_data_v)
    }
    val x4017 = Pipeline(name="x4017",parent=x4018) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4012 = CounterChain(name = "x4012", ctr23)
      var stage: List[Stage] = Nil
    }
    val x4037 = StreamController(name="x4037",parent=x4795) { implicit CU => 
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4037_unit = CounterChain(name = "x4037_unit", ctr24)
    }
    val x4028_0 = Pipeline(name="x4028_0",parent=x4037) { implicit CU => 
      val x4022 = CU.temp
      val x4021 =  ScalarBuffer().wtPort(x4021_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4028_unit = CounterChain(name = "x4028_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4022))
      Stage(operands=List(x4022, CU.load(x4021)), op=FixAdd, results=List(CU.scalarOut(x4019_b4846_x4027_b4848_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4019_b4847_x4027_b4849_s)))
    }
    val x4029 = MemoryController(name="x4029",parent=x4037,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4019_b4847_x4029 =  ScalarFIFO(name="size",size=1).wtPort(x4019_b4847_x4027_b4849_s)
      val x4019_b4846_x4029 =  ScalarFIFO(name="offset",size=1).wtPort(x4019_b4846_x4027_b4848_s)
      CU.newVout("data", x4020_x4029_data_v)
    }
    val x4036 = Pipeline(name="x4036",parent=x4037) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4031 = CounterChain(name = "x4031", ctr26)
      var stage: List[Stage] = Nil
    }
    val x4057 = StreamController(name="x4057",parent=x4795) { implicit CU => 
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4057_unit = CounterChain(name = "x4057_unit", ctr27)
    }
    val x4048_0 = Pipeline(name="x4048_0",parent=x4057) { implicit CU => 
      val x4042 = CU.temp
      val x4041 =  ScalarBuffer().wtPort(x4041_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4048_unit = CounterChain(name = "x4048_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4042))
      Stage(operands=List(x4042, CU.load(x4041)), op=FixAdd, results=List(CU.scalarOut(x4039_b4850_x4047_b4852_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4039_b4851_x4047_b4853_s)))
    }
    val x4049 = MemoryController(name="x4049",parent=x4057,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4039_b4850_x4049 =  ScalarFIFO(name="offset",size=1).wtPort(x4039_b4850_x4047_b4852_s)
      val x4039_b4851_x4049 =  ScalarFIFO(name="size",size=1).wtPort(x4039_b4851_x4047_b4853_s)
      CU.newVout("data", x4040_x4049_data_v)
    }
    val x4056 = Pipeline(name="x4056",parent=x4057) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4051 = CounterChain(name = "x4051", ctr29)
      var stage: List[Stage] = Nil
    }
    val x4076 = StreamController(name="x4076",parent=x4795) { implicit CU => 
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4076_unit = CounterChain(name = "x4076_unit", ctr30)
    }
    val x4067_0 = Pipeline(name="x4067_0",parent=x4076) { implicit CU => 
      val x4061 = CU.temp
      val x4060 =  ScalarBuffer().wtPort(x4060_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4067_unit = CounterChain(name = "x4067_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4061))
      Stage(operands=List(x4061, CU.load(x4060)), op=FixAdd, results=List(CU.scalarOut(x4058_b4854_x4066_b4856_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4058_b4855_x4066_b4857_s)))
    }
    val x4068 = MemoryController(name="x4068",parent=x4076,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4058_b4855_x4068 =  ScalarFIFO(name="size",size=1).wtPort(x4058_b4855_x4066_b4857_s)
      val x4058_b4854_x4068 =  ScalarFIFO(name="offset",size=1).wtPort(x4058_b4854_x4066_b4856_s)
      CU.newVout("data", x4059_x4068_data_v)
    }
    val x4075 = Pipeline(name="x4075",parent=x4076) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4070 = CounterChain(name = "x4070", ctr32)
      var stage: List[Stage] = Nil
    }
    val x4096 = StreamController(name="x4096",parent=x4795) { implicit CU => 
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4096_unit = CounterChain(name = "x4096_unit", ctr33)
    }
    val x4087_0 = Pipeline(name="x4087_0",parent=x4096) { implicit CU => 
      val x4081 = CU.temp
      val x4080 =  ScalarBuffer().wtPort(x4080_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4087_unit = CounterChain(name = "x4087_unit", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4081))
      Stage(operands=List(x4081, CU.load(x4080)), op=FixAdd, results=List(CU.scalarOut(x4078_b4858_x4086_b4860_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4078_b4859_x4086_b4861_s)))
    }
    val x4088 = MemoryController(name="x4088",parent=x4096,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4078_b4859_x4088 =  ScalarFIFO(name="size",size=1).wtPort(x4078_b4859_x4086_b4861_s)
      val x4078_b4858_x4088 =  ScalarFIFO(name="offset",size=1).wtPort(x4078_b4858_x4086_b4860_s)
      CU.newVout("data", x4079_x4088_data_v)
    }
    val x4095 = Pipeline(name="x4095",parent=x4096) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4090 = CounterChain(name = "x4090", ctr35)
      var stage: List[Stage] = Nil
    }
    val x4115 = StreamController(name="x4115",parent=x4795) { implicit CU => 
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4115_unit = CounterChain(name = "x4115_unit", ctr36)
    }
    val x4106_0 = Pipeline(name="x4106_0",parent=x4115) { implicit CU => 
      val x4100 = CU.temp
      val x4099 =  ScalarBuffer().wtPort(x4099_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4106_unit = CounterChain(name = "x4106_unit", ctr37)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4100))
      Stage(operands=List(x4100, CU.load(x4099)), op=FixAdd, results=List(CU.scalarOut(x4097_b4862_x4105_b4864_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4097_b4863_x4105_b4865_s)))
    }
    val x4107 = MemoryController(name="x4107",parent=x4115,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4097_b4862_x4107 =  ScalarFIFO(name="offset",size=1).wtPort(x4097_b4862_x4105_b4864_s)
      val x4097_b4863_x4107 =  ScalarFIFO(name="size",size=1).wtPort(x4097_b4863_x4105_b4865_s)
      CU.newVout("data", x4098_x4107_data_v)
    }
    val x4114 = Pipeline(name="x4114",parent=x4115) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4109 = CounterChain(name = "x4109", ctr38)
      var stage: List[Stage] = Nil
    }
    val x4135 = StreamController(name="x4135",parent=x4795) { implicit CU => 
      val ctr39 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4135_unit = CounterChain(name = "x4135_unit", ctr39)
    }
    val x4126_0 = Pipeline(name="x4126_0",parent=x4135) { implicit CU => 
      val x4120 = CU.temp
      val x4119 =  ScalarBuffer().wtPort(x4119_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4126_unit = CounterChain(name = "x4126_unit", ctr40)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4120))
      Stage(operands=List(x4120, CU.load(x4119)), op=FixAdd, results=List(CU.scalarOut(x4117_b4866_x4125_b4868_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4117_b4867_x4125_b4869_s)))
    }
    val x4127 = MemoryController(name="x4127",parent=x4135,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4117_b4867_x4127 =  ScalarFIFO(name="size",size=1).wtPort(x4117_b4867_x4125_b4869_s)
      val x4117_b4866_x4127 =  ScalarFIFO(name="offset",size=1).wtPort(x4117_b4866_x4125_b4868_s)
      CU.newVout("data", x4118_x4127_data_v)
    }
    val x4134 = Pipeline(name="x4134",parent=x4135) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4129 = CounterChain(name = "x4129", ctr41)
      var stage: List[Stage] = Nil
    }
    val x4154 = StreamController(name="x4154",parent=x4795) { implicit CU => 
      val ctr42 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4154_unit = CounterChain(name = "x4154_unit", ctr42)
    }
    val x4145_0 = Pipeline(name="x4145_0",parent=x4154) { implicit CU => 
      val x4139 = CU.temp
      val x4138 =  ScalarBuffer().wtPort(x4138_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4145_unit = CounterChain(name = "x4145_unit", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4139))
      Stage(operands=List(x4139, CU.load(x4138)), op=FixAdd, results=List(CU.scalarOut(x4136_b4870_x4144_b4872_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4136_b4871_x4144_b4873_s)))
    }
    val x4146 = MemoryController(name="x4146",parent=x4154,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4136_b4871_x4146 =  ScalarFIFO(name="size",size=1).wtPort(x4136_b4871_x4144_b4873_s)
      val x4136_b4870_x4146 =  ScalarFIFO(name="offset",size=1).wtPort(x4136_b4870_x4144_b4872_s)
      CU.newVout("data", x4137_x4146_data_v)
    }
    val x4153 = Pipeline(name="x4153",parent=x4154) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4148 = CounterChain(name = "x4148", ctr44)
      var stage: List[Stage] = Nil
    }
    val x4174 = StreamController(name="x4174",parent=x4795) { implicit CU => 
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4174_unit = CounterChain(name = "x4174_unit", ctr45)
    }
    val x4165_0 = Pipeline(name="x4165_0",parent=x4174) { implicit CU => 
      val x4159 = CU.temp
      val x4158 =  ScalarBuffer().wtPort(x4158_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4165_unit = CounterChain(name = "x4165_unit", ctr46)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4159))
      Stage(operands=List(x4159, CU.load(x4158)), op=FixAdd, results=List(CU.scalarOut(x4156_b4874_x4164_b4876_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4156_b4875_x4164_b4877_s)))
    }
    val x4166 = MemoryController(name="x4166",parent=x4174,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4156_b4874_x4166 =  ScalarFIFO(name="offset",size=1).wtPort(x4156_b4874_x4164_b4876_s)
      val x4156_b4875_x4166 =  ScalarFIFO(name="size",size=1).wtPort(x4156_b4875_x4164_b4877_s)
      CU.newVout("data", x4157_x4166_data_v)
    }
    val x4173 = Pipeline(name="x4173",parent=x4174) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4168 = CounterChain(name = "x4168", ctr47)
      var stage: List[Stage] = Nil
    }
    val x4193 = StreamController(name="x4193",parent=x4795) { implicit CU => 
      val ctr48 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4193_unit = CounterChain(name = "x4193_unit", ctr48)
    }
    val x4184_0 = Pipeline(name="x4184_0",parent=x4193) { implicit CU => 
      val x4178 = CU.temp
      val x4177 =  ScalarBuffer().wtPort(x4177_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4184_unit = CounterChain(name = "x4184_unit", ctr49)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4178))
      Stage(operands=List(x4178, CU.load(x4177)), op=FixAdd, results=List(CU.scalarOut(x4175_b4878_x4183_b4880_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4175_b4879_x4183_b4881_s)))
    }
    val x4185 = MemoryController(name="x4185",parent=x4193,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4175_b4879_x4185 =  ScalarFIFO(name="size",size=1).wtPort(x4175_b4879_x4183_b4881_s)
      val x4175_b4878_x4185 =  ScalarFIFO(name="offset",size=1).wtPort(x4175_b4878_x4183_b4880_s)
      CU.newVout("data", x4176_x4185_data_v)
    }
    val x4192 = Pipeline(name="x4192",parent=x4193) { implicit CU => 
      val ctr50 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4187 = CounterChain(name = "x4187", ctr50)
      var stage: List[Stage] = Nil
    }
    val x4213 = StreamController(name="x4213",parent=x4795) { implicit CU => 
      val ctr51 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4213_unit = CounterChain(name = "x4213_unit", ctr51)
    }
    val x4204_0 = Pipeline(name="x4204_0",parent=x4213) { implicit CU => 
      val x4198 = CU.temp
      val x4197 =  ScalarBuffer().wtPort(x4197_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4204_unit = CounterChain(name = "x4204_unit", ctr52)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4198))
      Stage(operands=List(x4198, CU.load(x4197)), op=FixAdd, results=List(CU.scalarOut(x4195_b4882_x4203_b4884_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4195_b4883_x4203_b4885_s)))
    }
    val x4205 = MemoryController(name="x4205",parent=x4213,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4195_b4883_x4205 =  ScalarFIFO(name="size",size=1).wtPort(x4195_b4883_x4203_b4885_s)
      val x4195_b4882_x4205 =  ScalarFIFO(name="offset",size=1).wtPort(x4195_b4882_x4203_b4884_s)
      CU.newVout("data", x4196_x4205_data_v)
    }
    val x4212 = Pipeline(name="x4212",parent=x4213) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4207 = CounterChain(name = "x4207", ctr53)
      var stage: List[Stage] = Nil
    }
    val x4232 = StreamController(name="x4232",parent=x4795) { implicit CU => 
      val ctr54 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4232_unit = CounterChain(name = "x4232_unit", ctr54)
    }
    val x4223_0 = Pipeline(name="x4223_0",parent=x4232) { implicit CU => 
      val x4217 = CU.temp
      val x4216 =  ScalarBuffer().wtPort(x4216_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr55 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4223_unit = CounterChain(name = "x4223_unit", ctr55)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4217))
      Stage(operands=List(x4217, CU.load(x4216)), op=FixAdd, results=List(CU.scalarOut(x4214_b4886_x4222_b4888_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4214_b4887_x4222_b4889_s)))
    }
    val x4224 = MemoryController(name="x4224",parent=x4232,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4214_b4886_x4224 =  ScalarFIFO(name="offset",size=1).wtPort(x4214_b4886_x4222_b4888_s)
      val x4214_b4887_x4224 =  ScalarFIFO(name="size",size=1).wtPort(x4214_b4887_x4222_b4889_s)
      CU.newVout("data", x4215_x4224_data_v)
    }
    val x4231 = Pipeline(name="x4231",parent=x4232) { implicit CU => 
      val ctr56 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4226 = CounterChain(name = "x4226", ctr56)
      var stage: List[Stage] = Nil
    }
    val x4252 = StreamController(name="x4252",parent=x4795) { implicit CU => 
      val ctr57 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4252_unit = CounterChain(name = "x4252_unit", ctr57)
    }
    val x4243_0 = Pipeline(name="x4243_0",parent=x4252) { implicit CU => 
      val x4237 = CU.temp
      val x4236 =  ScalarBuffer().wtPort(x4236_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr58 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4243_unit = CounterChain(name = "x4243_unit", ctr58)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4237))
      Stage(operands=List(x4237, CU.load(x4236)), op=FixAdd, results=List(CU.scalarOut(x4234_b4890_x4242_b4892_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4234_b4891_x4242_b4893_s)))
    }
    val x4244 = MemoryController(name="x4244",parent=x4252,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4234_b4891_x4244 =  ScalarFIFO(name="size",size=1).wtPort(x4234_b4891_x4242_b4893_s)
      val x4234_b4890_x4244 =  ScalarFIFO(name="offset",size=1).wtPort(x4234_b4890_x4242_b4892_s)
      CU.newVout("data", x4235_x4244_data_v)
    }
    val x4251 = Pipeline(name="x4251",parent=x4252) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4246 = CounterChain(name = "x4246", ctr59)
      var stage: List[Stage] = Nil
    }
    val x4271 = StreamController(name="x4271",parent=x4795) { implicit CU => 
      val ctr60 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4271_unit = CounterChain(name = "x4271_unit", ctr60)
    }
    val x4262_0 = Pipeline(name="x4262_0",parent=x4271) { implicit CU => 
      val x4256 = CU.temp
      val x4255 =  ScalarBuffer().wtPort(x4255_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr61 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4262_unit = CounterChain(name = "x4262_unit", ctr61)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4256))
      Stage(operands=List(x4256, CU.load(x4255)), op=FixAdd, results=List(CU.scalarOut(x4253_b4894_x4261_b4896_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4253_b4895_x4261_b4897_s)))
    }
    val x4263 = MemoryController(name="x4263",parent=x4271,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4253_b4895_x4263 =  ScalarFIFO(name="size",size=1).wtPort(x4253_b4895_x4261_b4897_s)
      val x4253_b4894_x4263 =  ScalarFIFO(name="offset",size=1).wtPort(x4253_b4894_x4261_b4896_s)
      CU.newVout("data", x4254_x4263_data_v)
    }
    val x4270 = Pipeline(name="x4270",parent=x4271) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4265 = CounterChain(name = "x4265", ctr62)
      var stage: List[Stage] = Nil
    }
    val x4291 = StreamController(name="x4291",parent=x4795) { implicit CU => 
      val ctr63 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4291_unit = CounterChain(name = "x4291_unit", ctr63)
    }
    val x4282_0 = Pipeline(name="x4282_0",parent=x4291) { implicit CU => 
      val x4276 = CU.temp
      val x4275 =  ScalarBuffer().wtPort(x4275_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr64 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4282_unit = CounterChain(name = "x4282_unit", ctr64)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4276))
      Stage(operands=List(x4276, CU.load(x4275)), op=FixAdd, results=List(CU.scalarOut(x4273_b4898_x4281_b4900_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4273_b4899_x4281_b4901_s)))
    }
    val x4283 = MemoryController(name="x4283",parent=x4291,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4273_b4898_x4283 =  ScalarFIFO(name="offset",size=1).wtPort(x4273_b4898_x4281_b4900_s)
      val x4273_b4899_x4283 =  ScalarFIFO(name="size",size=1).wtPort(x4273_b4899_x4281_b4901_s)
      CU.newVout("data", x4274_x4283_data_v)
    }
    val x4290 = Pipeline(name="x4290",parent=x4291) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4285 = CounterChain(name = "x4285", ctr65)
      var stage: List[Stage] = Nil
    }
    val x4310 = StreamController(name="x4310",parent=x4795) { implicit CU => 
      val ctr66 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4310_unit = CounterChain(name = "x4310_unit", ctr66)
    }
    val x4301_0 = Pipeline(name="x4301_0",parent=x4310) { implicit CU => 
      val x4295 = CU.temp
      val x4294 =  ScalarBuffer().wtPort(x4294_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr67 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4301_unit = CounterChain(name = "x4301_unit", ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4295))
      Stage(operands=List(x4295, CU.load(x4294)), op=FixAdd, results=List(CU.scalarOut(x4292_b4902_x4300_b4904_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4292_b4903_x4300_b4905_s)))
    }
    val x4302 = MemoryController(name="x4302",parent=x4310,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4292_b4903_x4302 =  ScalarFIFO(name="size",size=1).wtPort(x4292_b4903_x4300_b4905_s)
      val x4292_b4902_x4302 =  ScalarFIFO(name="offset",size=1).wtPort(x4292_b4902_x4300_b4904_s)
      CU.newVout("data", x4293_x4302_data_v)
    }
    val x4309 = Pipeline(name="x4309",parent=x4310) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4304 = CounterChain(name = "x4304", ctr68)
      var stage: List[Stage] = Nil
    }
    val x4330 = StreamController(name="x4330",parent=x4795) { implicit CU => 
      val ctr69 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4330_unit = CounterChain(name = "x4330_unit", ctr69)
    }
    val x4321_0 = Pipeline(name="x4321_0",parent=x4330) { implicit CU => 
      val x4315 = CU.temp
      val x4314 =  ScalarBuffer().wtPort(x4314_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr70 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4321_unit = CounterChain(name = "x4321_unit", ctr70)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4315))
      Stage(operands=List(x4315, CU.load(x4314)), op=FixAdd, results=List(CU.scalarOut(x4312_b4906_x4320_b4908_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4312_b4907_x4320_b4909_s)))
    }
    val x4322 = MemoryController(name="x4322",parent=x4330,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4312_b4907_x4322 =  ScalarFIFO(name="size",size=1).wtPort(x4312_b4907_x4320_b4909_s)
      val x4312_b4906_x4322 =  ScalarFIFO(name="offset",size=1).wtPort(x4312_b4906_x4320_b4908_s)
      CU.newVout("data", x4313_x4322_data_v)
    }
    val x4329 = Pipeline(name="x4329",parent=x4330) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4324 = CounterChain(name = "x4324", ctr71)
      var stage: List[Stage] = Nil
    }
    val x4349 = StreamController(name="x4349",parent=x4795) { implicit CU => 
      val ctr72 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4349_unit = CounterChain(name = "x4349_unit", ctr72)
    }
    val x4340_0 = Pipeline(name="x4340_0",parent=x4349) { implicit CU => 
      val x4334 = CU.temp
      val x4333 =  ScalarBuffer().wtPort(x4333_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr73 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4340_unit = CounterChain(name = "x4340_unit", ctr73)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4334))
      Stage(operands=List(x4334, CU.load(x4333)), op=FixAdd, results=List(CU.scalarOut(x4331_b4910_x4339_b4912_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4331_b4911_x4339_b4913_s)))
    }
    val x4341 = MemoryController(name="x4341",parent=x4349,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4331_b4910_x4341 =  ScalarFIFO(name="offset",size=1).wtPort(x4331_b4910_x4339_b4912_s)
      val x4331_b4911_x4341 =  ScalarFIFO(name="size",size=1).wtPort(x4331_b4911_x4339_b4913_s)
      CU.newVout("data", x4332_x4341_data_v)
    }
    val x4348 = Pipeline(name="x4348",parent=x4349) { implicit CU => 
      val ctr74 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4343 = CounterChain(name = "x4343", ctr74)
      var stage: List[Stage] = Nil
    }
    val x4369 = StreamController(name="x4369",parent=x4795) { implicit CU => 
      val ctr75 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4369_unit = CounterChain(name = "x4369_unit", ctr75)
    }
    val x4360_0 = Pipeline(name="x4360_0",parent=x4369) { implicit CU => 
      val x4354 = CU.temp
      val x4353 =  ScalarBuffer().wtPort(x4353_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr76 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4360_unit = CounterChain(name = "x4360_unit", ctr76)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4354))
      Stage(operands=List(x4354, CU.load(x4353)), op=FixAdd, results=List(CU.scalarOut(x4351_b4914_x4359_b4916_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4351_b4915_x4359_b4917_s)))
    }
    val x4361 = MemoryController(name="x4361",parent=x4369,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4351_b4915_x4361 =  ScalarFIFO(name="size",size=1).wtPort(x4351_b4915_x4359_b4917_s)
      val x4351_b4914_x4361 =  ScalarFIFO(name="offset",size=1).wtPort(x4351_b4914_x4359_b4916_s)
      CU.newVout("data", x4352_x4361_data_v)
    }
    val x4368 = Pipeline(name="x4368",parent=x4369) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4363 = CounterChain(name = "x4363", ctr77)
      var stage: List[Stage] = Nil
    }
    val x4388 = StreamController(name="x4388",parent=x4795) { implicit CU => 
      val ctr78 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4388_unit = CounterChain(name = "x4388_unit", ctr78)
    }
    val x4379_0 = Pipeline(name="x4379_0",parent=x4388) { implicit CU => 
      val x4373 = CU.temp
      val x4372 =  ScalarBuffer().wtPort(x4372_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr79 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4379_unit = CounterChain(name = "x4379_unit", ctr79)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4373))
      Stage(operands=List(x4373, CU.load(x4372)), op=FixAdd, results=List(CU.scalarOut(x4370_b4918_x4378_b4920_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4370_b4919_x4378_b4921_s)))
    }
    val x4380 = MemoryController(name="x4380",parent=x4388,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4370_b4919_x4380 =  ScalarFIFO(name="size",size=1).wtPort(x4370_b4919_x4378_b4921_s)
      val x4370_b4918_x4380 =  ScalarFIFO(name="offset",size=1).wtPort(x4370_b4918_x4378_b4920_s)
      CU.newVout("data", x4371_x4380_data_v)
    }
    val x4387 = Pipeline(name="x4387",parent=x4388) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4382 = CounterChain(name = "x4382", ctr80)
      var stage: List[Stage] = Nil
    }
    val x4408 = StreamController(name="x4408",parent=x4795) { implicit CU => 
      val ctr81 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4408_unit = CounterChain(name = "x4408_unit", ctr81)
    }
    val x4399_0 = Pipeline(name="x4399_0",parent=x4408) { implicit CU => 
      val x4393 = CU.temp
      val x4392 =  ScalarBuffer().wtPort(x4392_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr82 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4399_unit = CounterChain(name = "x4399_unit", ctr82)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4393))
      Stage(operands=List(x4393, CU.load(x4392)), op=FixAdd, results=List(CU.scalarOut(x4390_b4922_x4398_b4924_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4390_b4923_x4398_b4925_s)))
    }
    val x4400 = MemoryController(name="x4400",parent=x4408,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4390_b4922_x4400 =  ScalarFIFO(name="offset",size=1).wtPort(x4390_b4922_x4398_b4924_s)
      val x4390_b4923_x4400 =  ScalarFIFO(name="size",size=1).wtPort(x4390_b4923_x4398_b4925_s)
      CU.newVout("data", x4391_x4400_data_v)
    }
    val x4407 = Pipeline(name="x4407",parent=x4408) { implicit CU => 
      val ctr83 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4402 = CounterChain(name = "x4402", ctr83)
      var stage: List[Stage] = Nil
    }
    val x4427 = StreamController(name="x4427",parent=x4795) { implicit CU => 
      val ctr84 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4427_unit = CounterChain(name = "x4427_unit", ctr84)
    }
    val x4418_0 = Pipeline(name="x4418_0",parent=x4427) { implicit CU => 
      val x4412 = CU.temp
      val x4411 =  ScalarBuffer().wtPort(x4411_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr85 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4418_unit = CounterChain(name = "x4418_unit", ctr85)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4412))
      Stage(operands=List(x4412, CU.load(x4411)), op=FixAdd, results=List(CU.scalarOut(x4409_b4926_x4417_b4928_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4409_b4927_x4417_b4929_s)))
    }
    val x4419 = MemoryController(name="x4419",parent=x4427,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4409_b4927_x4419 =  ScalarFIFO(name="size",size=1).wtPort(x4409_b4927_x4417_b4929_s)
      val x4409_b4926_x4419 =  ScalarFIFO(name="offset",size=1).wtPort(x4409_b4926_x4417_b4928_s)
      CU.newVout("data", x4410_x4419_data_v)
    }
    val x4426 = Pipeline(name="x4426",parent=x4427) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4421 = CounterChain(name = "x4421", ctr86)
      var stage: List[Stage] = Nil
    }
    val x4447 = StreamController(name="x4447",parent=x4795) { implicit CU => 
      val ctr87 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4447_unit = CounterChain(name = "x4447_unit", ctr87)
    }
    val x4438_0 = Pipeline(name="x4438_0",parent=x4447) { implicit CU => 
      val x4432 = CU.temp
      val x4431 =  ScalarBuffer().wtPort(x4431_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr88 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4438_unit = CounterChain(name = "x4438_unit", ctr88)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4432))
      Stage(operands=List(x4432, CU.load(x4431)), op=FixAdd, results=List(CU.scalarOut(x4429_b4930_x4437_b4932_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4429_b4931_x4437_b4933_s)))
    }
    val x4439 = MemoryController(name="x4439",parent=x4447,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4429_b4931_x4439 =  ScalarFIFO(name="size",size=1).wtPort(x4429_b4931_x4437_b4933_s)
      val x4429_b4930_x4439 =  ScalarFIFO(name="offset",size=1).wtPort(x4429_b4930_x4437_b4932_s)
      CU.newVout("data", x4430_x4439_data_v)
    }
    val x4446 = Pipeline(name="x4446",parent=x4447) { implicit CU => 
      val ctr89 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4441 = CounterChain(name = "x4441", ctr89)
      var stage: List[Stage] = Nil
    }
    val x4466 = StreamController(name="x4466",parent=x4795) { implicit CU => 
      val ctr90 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4466_unit = CounterChain(name = "x4466_unit", ctr90)
    }
    val x4457_0 = Pipeline(name="x4457_0",parent=x4466) { implicit CU => 
      val x4451 = CU.temp
      val x4450 =  ScalarBuffer().wtPort(x4450_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr91 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4457_unit = CounterChain(name = "x4457_unit", ctr91)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4451))
      Stage(operands=List(x4451, CU.load(x4450)), op=FixAdd, results=List(CU.scalarOut(x4448_b4934_x4456_b4936_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4448_b4935_x4456_b4937_s)))
    }
    val x4458 = MemoryController(name="x4458",parent=x4466,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4448_b4935_x4458 =  ScalarFIFO(name="size",size=1).wtPort(x4448_b4935_x4456_b4937_s)
      val x4448_b4934_x4458 =  ScalarFIFO(name="offset",size=1).wtPort(x4448_b4934_x4456_b4936_s)
      CU.newVout("data", x4449_x4458_data_v)
    }
    val x4465 = Pipeline(name="x4465",parent=x4466) { implicit CU => 
      val ctr92 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4460 = CounterChain(name = "x4460", ctr92)
      var stage: List[Stage] = Nil
    }
    val x4486 = StreamController(name="x4486",parent=x4795) { implicit CU => 
      val ctr93 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4486_unit = CounterChain(name = "x4486_unit", ctr93)
    }
    val x4477_0 = Pipeline(name="x4477_0",parent=x4486) { implicit CU => 
      val x4471 = CU.temp
      val x4470 =  ScalarBuffer().wtPort(x4470_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr94 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4477_unit = CounterChain(name = "x4477_unit", ctr94)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4471))
      Stage(operands=List(x4471, CU.load(x4470)), op=FixAdd, results=List(CU.scalarOut(x4468_b4938_x4476_b4940_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4468_b4939_x4476_b4941_s)))
    }
    val x4478 = MemoryController(name="x4478",parent=x4486,offchip=x3841_oc, mctpe=TileLoad) { implicit CU => 
      val x4468_b4939_x4478 =  ScalarFIFO(name="size",size=1).wtPort(x4468_b4939_x4476_b4941_s)
      val x4468_b4938_x4478 =  ScalarFIFO(name="offset",size=1).wtPort(x4468_b4938_x4476_b4940_s)
      CU.newVout("data", x4469_x4478_data_v)
    }
    val x4485 = Pipeline(name="x4485",parent=x4486) { implicit CU => 
      val ctr95 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4480 = CounterChain(name = "x4480", ctr95)
      var stage: List[Stage] = Nil
    }
    val x4505 = StreamController(name="x4505",parent=x4795) { implicit CU => 
      val ctr96 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4505_unit = CounterChain(name = "x4505_unit", ctr96)
    }
    val x4496_0 = Pipeline(name="x4496_0",parent=x4505) { implicit CU => 
      val x4490 = CU.temp
      val x4489 =  ScalarBuffer().wtPort(x4489_argin)
      val x3850 = CounterChain.copy("x4795", "x3850")
      val ctr97 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4496_unit = CounterChain(name = "x4496_unit", ctr97)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3850(0)), Const(4)), op=FixMul, results=List(x4490))
      Stage(operands=List(x4490, CU.load(x4489)), op=FixAdd, results=List(CU.scalarOut(x4487_b4942_x4495_b4944_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x4487_b4943_x4495_b4945_s)))
    }
    val x4497 = MemoryController(name="x4497",parent=x4505,offchip=x3843_oc, mctpe=TileLoad) { implicit CU => 
      val x4487_b4942_x4497 =  ScalarFIFO(name="offset",size=1).wtPort(x4487_b4942_x4495_b4944_s)
      val x4487_b4943_x4497 =  ScalarFIFO(name="size",size=1).wtPort(x4487_b4943_x4495_b4945_s)
      CU.newVout("data", x4488_x4497_data_v)
    }
    val x4504 = Pipeline(name="x4504",parent=x4505) { implicit CU => 
      val ctr98 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4499 = CounterChain(name = "x4499", ctr98)
      var stage: List[Stage] = Nil
    }
    val x4536_0 = Pipeline(name="x4536_0",parent=x4795) { implicit CU => 
      val x4527_x4527 =  VectorFIFO(size=1).wtPort(x3851_x4527_x4536_v)
      val x4528_x4528 =  VectorFIFO(size=1).wtPort(x3867_x4528_x4536_v)
      val ctr99 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4525 = CounterChain(name = "x4525", ctr99)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4527_x4527), CU.load(x4528_x4528)), op=FixMul, results=List(CU.reduce))
      val (_, rr3864) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3864), op=Bypass, results=List(CU.scalarOut(x4508_x4534_s)))
    }
    val x4549_0 = Pipeline(name="x4549_0",parent=x4795) { implicit CU => 
      val x4541_x4541 =  VectorFIFO(size=1).wtPort(x3868_x4541_x4549_v)
      val x4540_x4540 =  VectorFIFO(size=1).wtPort(x3852_x4540_x4549_v)
      val ctr100 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4538 = CounterChain(name = "x4538", ctr100)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4540_x4540), CU.load(x4541_x4541)), op=FixMul, results=List(CU.reduce))
      val (_, rr3869) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3869), op=Bypass, results=List(CU.scalarOut(x4509_x4547_s)))
    }
    val x4562_0 = Pipeline(name="x4562_0",parent=x4795) { implicit CU => 
      val x4554_x4554 =  VectorFIFO(size=1).wtPort(x3869_x4554_x4562_v)
      val x4553_x4553 =  VectorFIFO(size=1).wtPort(x3853_x4553_x4562_v)
      val ctr101 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4551 = CounterChain(name = "x4551", ctr101)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4553_x4553), CU.load(x4554_x4554)), op=FixMul, results=List(CU.reduce))
      val (_, rr3874) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3874), op=Bypass, results=List(CU.scalarOut(x4510_x4560_s)))
    }
    val x4575_0 = Pipeline(name="x4575_0",parent=x4795) { implicit CU => 
      val x4566_x4566 =  VectorFIFO(size=1).wtPort(x3854_x4566_x4575_v)
      val x4567_x4567 =  VectorFIFO(size=1).wtPort(x3870_x4567_x4575_v)
      val ctr102 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4564 = CounterChain(name = "x4564", ctr102)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4566_x4566), CU.load(x4567_x4567)), op=FixMul, results=List(CU.reduce))
      val (_, rr3879) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3879), op=Bypass, results=List(CU.scalarOut(x4511_x4573_s)))
    }
    val x4588_0 = Pipeline(name="x4588_0",parent=x4795) { implicit CU => 
      val x4580_x4580 =  VectorFIFO(size=1).wtPort(x3871_x4580_x4588_v)
      val x4579_x4579 =  VectorFIFO(size=1).wtPort(x3855_x4579_x4588_v)
      val ctr103 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4577 = CounterChain(name = "x4577", ctr103)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4579_x4579), CU.load(x4580_x4580)), op=FixMul, results=List(CU.reduce))
      val (_, rr3884) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3884), op=Bypass, results=List(CU.scalarOut(x4512_x4586_s)))
    }
    val x4601_0 = Pipeline(name="x4601_0",parent=x4795) { implicit CU => 
      val x4593_x4593 =  VectorFIFO(size=1).wtPort(x3872_x4593_x4601_v)
      val x4592_x4592 =  VectorFIFO(size=1).wtPort(x3856_x4592_x4601_v)
      val ctr104 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4590 = CounterChain(name = "x4590", ctr104)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4592_x4592), CU.load(x4593_x4593)), op=FixMul, results=List(CU.reduce))
      val (_, rr3889) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3889), op=Bypass, results=List(CU.scalarOut(x4513_x4599_s)))
    }
    val x4614_0 = Pipeline(name="x4614_0",parent=x4795) { implicit CU => 
      val x4605_x4605 =  VectorFIFO(size=1).wtPort(x3857_x4605_x4614_v)
      val x4606_x4606 =  VectorFIFO(size=1).wtPort(x3873_x4606_x4614_v)
      val ctr105 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4603 = CounterChain(name = "x4603", ctr105)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4605_x4605), CU.load(x4606_x4606)), op=FixMul, results=List(CU.reduce))
      val (_, rr3894) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3894), op=Bypass, results=List(CU.scalarOut(x4514_x4612_s)))
    }
    val x4627_0 = Pipeline(name="x4627_0",parent=x4795) { implicit CU => 
      val x4619_x4619 =  VectorFIFO(size=1).wtPort(x3874_x4619_x4627_v)
      val x4618_x4618 =  VectorFIFO(size=1).wtPort(x3858_x4618_x4627_v)
      val ctr106 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4616 = CounterChain(name = "x4616", ctr106)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4618_x4618), CU.load(x4619_x4619)), op=FixMul, results=List(CU.reduce))
      val (_, rr3899) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3899), op=Bypass, results=List(CU.scalarOut(x4515_x4625_s)))
    }
    val x4640_0 = Pipeline(name="x4640_0",parent=x4795) { implicit CU => 
      val x4632_x4632 =  VectorFIFO(size=1).wtPort(x3875_x4632_x4640_v)
      val x4631_x4631 =  VectorFIFO(size=1).wtPort(x3859_x4631_x4640_v)
      val ctr107 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4629 = CounterChain(name = "x4629", ctr107)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4631_x4631), CU.load(x4632_x4632)), op=FixMul, results=List(CU.reduce))
      val (_, rr3904) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3904), op=Bypass, results=List(CU.scalarOut(x4516_x4638_s)))
    }
    val x4653_0 = Pipeline(name="x4653_0",parent=x4795) { implicit CU => 
      val x4644_x4644 =  VectorFIFO(size=1).wtPort(x3860_x4644_x4653_v)
      val x4645_x4645 =  VectorFIFO(size=1).wtPort(x3876_x4645_x4653_v)
      val ctr108 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4642 = CounterChain(name = "x4642", ctr108)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4644_x4644), CU.load(x4645_x4645)), op=FixMul, results=List(CU.reduce))
      val (_, rr3909) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3909), op=Bypass, results=List(CU.scalarOut(x4517_x4651_s)))
    }
    val x4666_0 = Pipeline(name="x4666_0",parent=x4795) { implicit CU => 
      val x4658_x4658 =  VectorFIFO(size=1).wtPort(x3877_x4658_x4666_v)
      val x4657_x4657 =  VectorFIFO(size=1).wtPort(x3861_x4657_x4666_v)
      val ctr109 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4655 = CounterChain(name = "x4655", ctr109)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4657_x4657), CU.load(x4658_x4658)), op=FixMul, results=List(CU.reduce))
      val (_, rr3914) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3914), op=Bypass, results=List(CU.scalarOut(x4518_x4664_s)))
    }
    val x4679_0 = Pipeline(name="x4679_0",parent=x4795) { implicit CU => 
      val x4670_x4670 =  VectorFIFO(size=1).wtPort(x3862_x4670_x4679_v)
      val x4671_x4671 =  VectorFIFO(size=1).wtPort(x3878_x4671_x4679_v)
      val ctr110 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4668 = CounterChain(name = "x4668", ctr110)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4670_x4670), CU.load(x4671_x4671)), op=FixMul, results=List(CU.reduce))
      val (_, rr3919) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3919), op=Bypass, results=List(CU.scalarOut(x4519_x4677_s)))
    }
    val x4692_0 = Pipeline(name="x4692_0",parent=x4795) { implicit CU => 
      val x4684_x4684 =  VectorFIFO(size=1).wtPort(x3879_x4684_x4692_v)
      val x4683_x4683 =  VectorFIFO(size=1).wtPort(x3863_x4683_x4692_v)
      val ctr111 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4681 = CounterChain(name = "x4681", ctr111)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4683_x4683), CU.load(x4684_x4684)), op=FixMul, results=List(CU.reduce))
      val (_, rr3924) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3924), op=Bypass, results=List(CU.scalarOut(x4520_x4690_s)))
    }
    val x4705_0 = Pipeline(name="x4705_0",parent=x4795) { implicit CU => 
      val x4697_x4697 =  VectorFIFO(size=1).wtPort(x3880_x4697_x4705_v)
      val x4696_x4696 =  VectorFIFO(size=1).wtPort(x3864_x4696_x4705_v)
      val ctr112 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4694 = CounterChain(name = "x4694", ctr112)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4696_x4696), CU.load(x4697_x4697)), op=FixMul, results=List(CU.reduce))
      val (_, rr3929) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3929), op=Bypass, results=List(CU.scalarOut(x4521_x4703_s)))
    }
    val x4718_0 = Pipeline(name="x4718_0",parent=x4795) { implicit CU => 
      val x4709_x4709 =  VectorFIFO(size=1).wtPort(x3865_x4709_x4718_v)
      val x4710_x4710 =  VectorFIFO(size=1).wtPort(x3881_x4710_x4718_v)
      val ctr113 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4707 = CounterChain(name = "x4707", ctr113)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4709_x4709), CU.load(x4710_x4710)), op=FixMul, results=List(CU.reduce))
      val (_, rr3934) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3934), op=Bypass, results=List(CU.scalarOut(x4522_x4716_s)))
    }
    val x4731_0 = Pipeline(name="x4731_0",parent=x4795) { implicit CU => 
      val x4723_x4723 =  VectorFIFO(size=1).wtPort(x3882_x4723_x4731_v)
      val x4722_x4722 =  VectorFIFO(size=1).wtPort(x3866_x4722_x4731_v)
      val ctr114 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x4720 = CounterChain(name = "x4720", ctr114)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4722_x4722), CU.load(x4723_x4723)), op=FixMul, results=List(CU.reduce))
      val (_, rr3939) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3939), op=Bypass, results=List(CU.scalarOut(x4523_x4729_s)))
    }
    val x4793 = StreamController(name="x4793",parent=x4795) { implicit CU => 
      val ctr115 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4793_unit = CounterChain(name = "x4793_unit", ctr115)
    }
    val x4793_0 = Pipeline(name="x4793_0",parent=x4793) { implicit CU => 
      val x4750 = CU.temp
      val x4753 = CU.temp
      val x4509_x4734 =  ScalarBuffer().wtPort(x4509_x4547_s)
      val x4508_x4735 =  ScalarBuffer().wtPort(x4508_x4534_s)
      val x4511_x4736 =  ScalarBuffer().wtPort(x4511_x4573_s)
      val x4510_x4737 =  ScalarBuffer().wtPort(x4510_x4560_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4508_x4735), CU.load(x4509_x4734)), op=FixAdd, results=List(x4750))
      Stage(operands=List(CU.load(x4510_x4737), CU.load(x4511_x4736)), op=FixAdd, results=List(x4753))
      Stage(operands=List(x4750, x4753), op=FixAdd, results=List(CU.scalarOut(bus_3943_s)))
    }
    val x4793_1 = Pipeline(name="x4793_1",parent=x4793) { implicit CU => 
      val x4762 = CU.temp
      val x4760 = CU.temp
      val x4512_x4739 =  ScalarBuffer().wtPort(x4512_x4586_s)
      val x4515_x4740 =  ScalarBuffer().wtPort(x4515_x4625_s)
      val x4514_x4741 =  ScalarBuffer().wtPort(x4514_x4612_s)
      val x4513_x4738 =  ScalarBuffer().wtPort(x4513_x4599_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4512_x4739), CU.load(x4513_x4738)), op=FixAdd, results=List(x4760))
      Stage(operands=List(CU.load(x4514_x4741), CU.load(x4515_x4740)), op=FixAdd, results=List(x4762))
      Stage(operands=List(x4760, x4762), op=FixAdd, results=List(CU.scalarOut(bus_3949_s)))
    }
    val x4793_2 = Pipeline(name="x4793_2",parent=x4793) { implicit CU => 
      val x4764 =  ScalarFIFO(size=1).wtPort(bus_3949_s)
      val x4517_x4742 =  ScalarBuffer().wtPort(x4517_x4651_s)
      val x4755 =  ScalarFIFO(size=1).wtPort(bus_3943_s)
      val x4516_x4743 =  ScalarBuffer().wtPort(x4516_x4638_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4755), CU.load(x4764)), op=FixAdd, results=List(CU.scalarOut(bus_3950_s)))
      Stage(operands=List(CU.load(x4516_x4743), CU.load(x4517_x4742)), op=FixAdd, results=List(CU.scalarOut(bus_3958_s)))
    }
    val x4793_3 = Pipeline(name="x4793_3",parent=x4793) { implicit CU => 
      val x4777 = CU.temp
      val x4518_x4745 =  ScalarBuffer().wtPort(x4518_x4664_s)
      val x4775 =  ScalarFIFO(size=1).wtPort(bus_3958_s)
      val x4519_x4744 =  ScalarBuffer().wtPort(x4519_x4677_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4518_x4745), CU.load(x4519_x4744)), op=FixAdd, results=List(x4777))
      Stage(operands=List(CU.load(x4775), x4777), op=FixAdd, results=List(CU.scalarOut(bus_3960_s)))
    }
    val x4793_4 = Pipeline(name="x4793_4",parent=x4793) { implicit CU => 
      val x4781 = CU.temp
      val x4783 = CU.temp
      val x4521_x4746 =  ScalarBuffer().wtPort(x4521_x4703_s)
      val x4523_x4748 =  ScalarBuffer().wtPort(x4523_x4729_s)
      val x4520_x4747 =  ScalarBuffer().wtPort(x4520_x4690_s)
      val x4522_x4749 =  ScalarBuffer().wtPort(x4522_x4716_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4520_x4747), CU.load(x4521_x4746)), op=FixAdd, results=List(x4781))
      Stage(operands=List(CU.load(x4522_x4749), CU.load(x4523_x4748)), op=FixAdd, results=List(x4783))
      Stage(operands=List(x4781, x4783), op=FixAdd, results=List(CU.scalarOut(bus_3963_s)))
    }
    val x4793_5 = Pipeline(name="x4793_5",parent=x4793) { implicit CU => 
      val x4787 = CU.temp
      val x4779 =  ScalarFIFO(size=1).wtPort(bus_3960_s)
      val x4766 =  ScalarFIFO(size=1).wtPort(bus_3950_s)
      val x4785 =  ScalarFIFO(size=1).wtPort(bus_3963_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4779), CU.load(x4785)), op=FixAdd, results=List(x4787))
      Stage(operands=List(CU.load(x4766), x4787), op=FixAdd, results=List(CU.scalarOut(bus_3966_s)))
    }
    val x4793_6 = Pipeline(name="x4793_6",parent=x4793) { implicit CU => 
      val rr3966 =  ScalarFIFO(size=1).wtPort(bus_3966_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr3966)), op=Bypass, results=List(CU.reduce))
      val (_, rr3968) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr3968), op=Bypass, results=List(CU.scalarOut(x3844_x4797_argout)))
    }
    
  }
}
