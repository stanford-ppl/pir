import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object LogReg extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4145_x4602_vector = Vector("x4145_x4602")
    val x4049_x4597_x4603_vector = Vector("x4049_x4597_x4603")
    val x4049_x4579_x4585_vector = Vector("x4049_x4579_x4585")
    val x4049_x4561_x4567_vector = Vector("x4049_x4561_x4567")
    val x4049_x4570_x4576_vector = Vector("x4049_x4570_x4576")
    val x4134_x4635_x4702_vector = Vector("x4134_x4635_x4702")
    val x4050_x4432_x4440_vector = Vector("x4050_x4432_x4440")
    val x4147_x4620_vector = Vector("x4147_x4620")
    val x4049_x4310_x4318_vector = Vector("x4049_x4310_x4318")
    val x4045_x4701_vector = Vector("x4045_x4701")
    val x4039_x4715_vector = Vector("x4039_x4715")
    val x4049_x4167_x4175_vector = Vector("x4049_x4167_x4175")
    val x4050_x4110_vector = Vector("x4050_x4110")
    val x4136_x4521_vector = Vector("x4136_x4521")
    val x4049_x4200_x4208_vector = Vector("x4049_x4200_x4208")
    val x4139_x4548_vector = Vector("x4139_x4548")
    val x4050_x4360_x4368_vector = Vector("x4050_x4360_x4368")
    val x4049_x4277_x4285_vector = Vector("x4049_x4277_x4285")
    val x4719_b4891_x4725_b4896_scalar = Scalar("x4719_b4891_x4725_b4896")
    val x4049_x4498_x4504_vector = Vector("x4049_x4498_x4504")
    val x4050_x4441_x4449_vector = Vector("x4050_x4441_x4449")
    val x4144_x4593_vector = Vector("x4144_x4593")
    val x4720_x4726_scalar = Scalar("x4720_x4726")
    val x4049_x4489_x4495_vector = Vector("x4049_x4489_x4495")
    val x4049_x4299_x4307_vector = Vector("x4049_x4299_x4307")
    val x4039_x4289_x4296_vector = Vector("x4039_x4289_x4296")
    val x4032_oc = OffChip("x4032")
    val x4118_x4358_scalar = Scalar("x4118_x4358")
    val x4143_x4584_vector = Vector("x4143_x4584")
    val x4159_x4284_vector = Vector("x4159_x4284")
    val x4050_x4342_x4350_vector = Vector("x4050_x4342_x4350")
    val x4132_x4484_scalar = Scalar("x4132_x4484")
    val x4137_x4638_x4702_vector = Vector("x4137_x4638_x4702")
    val x4122_x4394_scalar = Scalar("x4122_x4394")
    val x4137_x4530_vector = Vector("x4137_x4530")
    val x4050_x4477_x4485_vector = Vector("x4050_x4477_x4485")
    val x4141_x4566_vector = Vector("x4141_x4566")
    val x4117_x4349_scalar = Scalar("x4117_x4349")
    val x4053_b4804_x4059_b4807_scalar = Scalar("x4053_b4804_x4059_b4807")
    val x4053_b4803_x4059_b4806_scalar = Scalar("x4053_b4803_x4059_b4806")
    val x4049_x4507_x4513_vector = Vector("x4049_x4507_x4513")
    val x4126_x4430_scalar = Scalar("x4126_x4430")
    val x4156_x4251_vector = Vector("x4156_x4251")
    val x4164_x4339_vector = Vector("x4164_x4339")
    val x4130_x4466_scalar = Scalar("x4130_x4466")
    val x4140_x4557_vector = Vector("x4140_x4557")
    val x4074_b4811_x4081_b4820_scalar = Scalar("x4074_b4811_x4081_b4820")
    val x4150_x4185_vector = Vector("x4150_x4185")
    val x4086_x4092_scalar = Scalar("x4086_x4092")
    val x4049_x4516_x4522_vector = Vector("x4049_x4516_x4522")
    val x4054_x4060_scalar = Scalar("x4054_x4060")
    val x4050_x4423_x4431_vector = Vector("x4050_x4423_x4431")
    val x4045_x4650_x4702_vector = Vector("x4045_x4650_x4702")
    val x4124_x4412_scalar = Scalar("x4124_x4412")
    val x4049_x4534_x4540_vector = Vector("x4049_x4534_x4540")
    val x4050_x4351_x4359_vector = Vector("x4050_x4351_x4359")
    val x4155_x4240_vector = Vector("x4155_x4240")
    val x4125_x4421_scalar = Scalar("x4125_x4421")
    val x4146_x4611_vector = Vector("x4146_x4611")
    val x4039_x4201_x4208_vector = Vector("x4039_x4201_x4208")
    val x4049_x4543_x4549_vector = Vector("x4049_x4543_x4549")
    val x4148_x4629_vector = Vector("x4148_x4629")
    val x4039_x4300_x4307_vector = Vector("x4039_x4300_x4307")
    val x4143_x4644_x4702_vector = Vector("x4143_x4644_x4702")
    val x4144_x4645_x4702_vector = Vector("x4144_x4645_x4702")
    val x4141_x4642_x4702_vector = Vector("x4141_x4642_x4702")
    val x4074_b4812_x4081_b4821_scalar = Scalar("x4074_b4812_x4081_b4821")
    val x4045_x4707_x4716_vector = Vector("x4045_x4707_x4716")
    val x4050_x4369_x4377_vector = Vector("x4050_x4369_x4377")
    val x4025_argin = ArgIn("x4025")
    val x4153_x4218_vector = Vector("x4153_x4218")
    val x4075_b4814_x4082_b4823_scalar = Scalar("x4075_b4814_x4082_b4823")
    val x4039_x4267_x4274_vector = Vector("x4039_x4267_x4274")
    val x4049_x4624_x4630_vector = Vector("x4049_x4624_x4630")
    val x4147_x4648_x4702_vector = Vector("x4147_x4648_x4702")
    val x4049_x4255_x4263_vector = Vector("x4049_x4255_x4263")
    val x4085_x4090_scalar = Scalar("x4085_x4090")
    val x4050_x4450_x4458_vector = Vector("x4050_x4450_x4458")
    val x4049_x4332_x4340_vector = Vector("x4049_x4332_x4340")
    val x4039_x4212_x4219_vector = Vector("x4039_x4212_x4219")
    val x4135_x4512_vector = Vector("x4135_x4512")
    val x4077_argin = ArgIn("x4077")
    val x4039_x4179_x4186_vector = Vector("x4039_x4179_x4186")
    val x4146_x4647_x4702_vector = Vector("x4146_x4647_x4702")
    val x4163_x4328_vector = Vector("x4163_x4328")
    val x4039_x4333_x4340_vector = Vector("x4039_x4333_x4340")
    val x4135_x4636_x4702_vector = Vector("x4135_x4636_x4702")
    val x4133_x4634_x4702_vector = Vector("x4133_x4634_x4702")
    val x4049_x4189_x4197_vector = Vector("x4049_x4189_x4197")
    val x4050_x4396_x4404_vector = Vector("x4050_x4396_x4404")
    val x4120_x4376_scalar = Scalar("x4120_x4376")
    val x4133_x4494_vector = Vector("x4133_x4494")
    val x4030_oc = OffChip("x4030")
    val x4050_x4468_x4476_vector = Vector("x4050_x4468_x4476")
    val x4127_x4439_scalar = Scalar("x4127_x4439")
    val x4121_x4385_scalar = Scalar("x4121_x4385")
    val x4721_b4894_x4734_b4899_vector = Vector("x4721_b4894_x4734_b4899")
    val x4039_x4234_x4241_vector = Vector("x4039_x4234_x4241")
    val x4140_x4641_x4702_vector = Vector("x4140_x4641_x4702")
    val x4049_x4288_x4296_vector = Vector("x4049_x4288_x4296")
    val x4049_x4233_x4241_vector = Vector("x4049_x4233_x4241")
    val x4049_x4211_x4219_vector = Vector("x4049_x4211_x4219")
    val x4138_x4639_x4702_vector = Vector("x4138_x4639_x4702")
    val x4142_x4575_vector = Vector("x4142_x4575")
    val x4134_x4503_vector = Vector("x4134_x4503")
    val x4039_x4256_x4263_vector = Vector("x4039_x4256_x4263")
    val x4049_x4615_x4621_vector = Vector("x4049_x4615_x4621")
    val x4129_x4457_scalar = Scalar("x4129_x4457")
    val x4039_x4278_x4285_vector = Vector("x4039_x4278_x4285")
    val x4074_b4813_x4081_b4822_scalar = Scalar("x4074_b4813_x4081_b4822")
    val x4039_x4245_x4252_vector = Vector("x4039_x4245_x4252")
    val x4050_x4378_x4386_vector = Vector("x4050_x4378_x4386")
    val x4049_x4552_x4558_vector = Vector("x4049_x4552_x4558")
    val x4721_b4893_x4734_b4898_vector = Vector("x4721_b4893_x4734_b4898")
    val x4050_x4414_x4422_vector = Vector("x4050_x4414_x4422")
    val x4050_x4459_x4467_vector = Vector("x4050_x4459_x4467")
    val x4139_x4640_x4702_vector = Vector("x4139_x4640_x4702")
    val x4149_x4174_vector = Vector("x4149_x4174")
    val x4136_x4637_x4702_vector = Vector("x4136_x4637_x4702")
    val x4049_x4070_vector = Vector("x4049_x4070")
    val x4075_b4815_x4082_b4824_scalar = Scalar("x4075_b4815_x4082_b4824")
    val x4049_x4606_x4612_vector = Vector("x4049_x4606_x4612")
    val x4152_x4207_vector = Vector("x4152_x4207")
    val x4033_oc = OffChip("x4033")
    val x4148_x4649_x4702_vector = Vector("x4148_x4649_x4702")
    val x4049_x4266_x4274_vector = Vector("x4049_x4266_x4274")
    val x4123_x4403_scalar = Scalar("x4123_x4403")
    val x4049_x4222_x4230_vector = Vector("x4049_x4222_x4230")
    val x4158_x4273_vector = Vector("x4158_x4273")
    val x4039_x4311_x4318_vector = Vector("x4039_x4311_x4318")
    val x4075_b4816_x4082_b4825_scalar = Scalar("x4075_b4816_x4082_b4825")
    val x4039_x4190_x4197_vector = Vector("x4039_x4190_x4197")
    val x4162_x4317_vector = Vector("x4162_x4317")
    val x4723_argin = ArgIn("x4723")
    val x4056_argin = ArgIn("x4056")
    val x4128_x4448_scalar = Scalar("x4128_x4448")
    val x4049_x4178_x4186_vector = Vector("x4049_x4178_x4186")
    val x4719_b4892_x4725_b4897_scalar = Scalar("x4719_b4892_x4725_b4897")
    val x4154_x4229_vector = Vector("x4154_x4229")
    val x4049_x4525_x4531_vector = Vector("x4049_x4525_x4531")
    val x4138_x4539_vector = Vector("x4138_x4539")
    val x4050_x4405_x4413_vector = Vector("x4050_x4405_x4413")
    val x4039_x4730_x4735_vector = Vector("x4039_x4730_x4735")
    val x4119_x4367_scalar = Scalar("x4119_x4367")
    val x4039_x4223_x4230_vector = Vector("x4039_x4223_x4230")
    val x4160_x4295_vector = Vector("x4160_x4295")
    val x4053_b4805_x4059_b4808_scalar = Scalar("x4053_b4805_x4059_b4808")
    val x4050_x4387_x4395_vector = Vector("x4050_x4387_x4395")
    val x4039_x4322_x4329_vector = Vector("x4039_x4322_x4329")
    val x4157_x4262_vector = Vector("x4157_x4262")
    val x4131_x4475_scalar = Scalar("x4131_x4475")
    val x4049_x4244_x4252_vector = Vector("x4049_x4244_x4252")
    val x4142_x4643_x4702_vector = Vector("x4142_x4643_x4702")
    val x4049_x4321_x4329_vector = Vector("x4049_x4321_x4329")
    val x4151_x4196_vector = Vector("x4151_x4196")
    val x4039_x4168_x4175_vector = Vector("x4039_x4168_x4175")
    val x4039_x4708_x4716_vector = Vector("x4039_x4708_x4716")
    val x4049_x4588_x4594_vector = Vector("x4049_x4588_x4594")
    val x4087_x4094_scalar = Scalar("x4087_x4094")
    val x4145_x4646_x4702_vector = Vector("x4145_x4646_x4702")
    val x4719_b4890_x4725_b4895_scalar = Scalar("x4719_b4890_x4725_b4895")
    val x4026_argin = ArgIn("x4026")
    val x4161_x4306_vector = Vector("x4161_x4306")
    val x4742 = Sequential(name="x4742",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4039_dsp0 = MemoryPipeline(name="x4039_dsp0",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4730 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp1 = MemoryPipeline(name="x4039_dsp1",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4708 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp2 = MemoryPipeline(name="x4039_dsp2",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4333 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp3 = MemoryPipeline(name="x4039_dsp3",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4322 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp4 = MemoryPipeline(name="x4039_dsp4",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4311 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp5 = MemoryPipeline(name="x4039_dsp5",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4300 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp6 = MemoryPipeline(name="x4039_dsp6",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4289 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp7 = MemoryPipeline(name="x4039_dsp7",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4278 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp8 = MemoryPipeline(name="x4039_dsp8",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4267 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp9 = MemoryPipeline(name="x4039_dsp9",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4256 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp10 = MemoryPipeline(name="x4039_dsp10",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4245 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp11 = MemoryPipeline(name="x4039_dsp11",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4234 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp12 = MemoryPipeline(name="x4039_dsp12",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4223 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp13 = MemoryPipeline(name="x4039_dsp13",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4212 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp14 = MemoryPipeline(name="x4039_dsp14",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4201 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp15 = MemoryPipeline(name="x4039_dsp15",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4190 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp16 = MemoryPipeline(name="x4039_dsp16",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4179 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4039_dsp17 = MemoryPipeline(name="x4039_dsp17",parent="x4717") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4729 = CounterChain.copy("x4735", "x4729")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4039_x4168 = SRAM(size = 192, banking = Strided(1)).wtPort(x4039_x4715_vector).rdPort(x4039_x4730_x4735_vector)
      var stage: List[Stage] = Nil
    }
    val x4718 = Sequential(name="x4718",parent=x4742) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, x4025_x4040.load, Const("1i").out) // Counter
      val x4042 = CounterChain(name = "x4042", ctr1)
      val x4025_x4040 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4025_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x4717 = Sequential(name="x4717",parent=x4718) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, Const("1i").out, Const("1i").out) // Counter
      val x4044 = CounterChain(name = "x4044", ctr2)
      var stage: List[Stage] = Nil
    }
    val x4045_dsp0 = MemoryPipeline(name="x4045_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4045_x4707 = SRAM(size = 192, banking = Strided(1)).wtPort(x4045_x4701_vector).rdPort(x4045_x4707_x4716_vector)
      var stage: List[Stage] = Nil
    }
    val x4045_dsp1 = MemoryPipeline(name="x4045_dsp1",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4706 = CounterChain.copy("x4716", "x4706")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4045_x4650 = SRAM(size = 192, banking = Strided(1)).wtPort(x4045_x4701_vector).rdPort(x4045_x4707_x4716_vector)
      var stage: List[Stage] = Nil
    }
    val x4704 = MetaPipeline(name="x4704",parent=x4717) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, x4026_x4046.load, Const("40i").out) // Counter
      val x4048 = CounterChain(name = "x4048", ctr3)
      val x4026_x4046 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4026_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x4049_dsp0 = MemoryPipeline(name="x4049_dsp0",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51526 = CU.temp
      val tr51527 = CU.temp
      val tr51528 = CU.temp
      val tr51529 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4624 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4624))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51526)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51526), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4624.writeAddr, CU.temp(stage(2), tr51527)))
      stage = stage0 +: RAStages(2, List(x4049_x4624))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51528)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51528), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4624.readAddr, CU.temp(stage(2), tr51529)))
    }
    val x4049_dsp1 = MemoryPipeline(name="x4049_dsp1",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51531 = CU.temp
      val tr51532 = CU.temp
      val tr51533 = CU.temp
      val tr51534 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4615 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4615))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51531)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51531), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4615.writeAddr, CU.temp(stage(2), tr51532)))
      stage = stage0 +: RAStages(2, List(x4049_x4615))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51533)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51533), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4615.readAddr, CU.temp(stage(2), tr51534)))
    }
    val x4049_dsp2 = MemoryPipeline(name="x4049_dsp2",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51536 = CU.temp
      val tr51537 = CU.temp
      val tr51538 = CU.temp
      val tr51539 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4606 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4606))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51536)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51536), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4606.writeAddr, CU.temp(stage(2), tr51537)))
      stage = stage0 +: RAStages(2, List(x4049_x4606))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51538)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51538), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4606.readAddr, CU.temp(stage(2), tr51539)))
    }
    val x4049_dsp3 = MemoryPipeline(name="x4049_dsp3",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51541 = CU.temp
      val tr51542 = CU.temp
      val tr51543 = CU.temp
      val tr51544 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4597 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4597))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51541)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51541), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4597.writeAddr, CU.temp(stage(2), tr51542)))
      stage = stage0 +: RAStages(2, List(x4049_x4597))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51543)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51543), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4597.readAddr, CU.temp(stage(2), tr51544)))
    }
    val x4049_dsp4 = MemoryPipeline(name="x4049_dsp4",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51546 = CU.temp
      val tr51547 = CU.temp
      val tr51548 = CU.temp
      val tr51549 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4588 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4588))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51546)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51546), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4588.writeAddr, CU.temp(stage(2), tr51547)))
      stage = stage0 +: RAStages(2, List(x4049_x4588))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51548)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51548), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4588.readAddr, CU.temp(stage(2), tr51549)))
    }
    val x4049_dsp5 = MemoryPipeline(name="x4049_dsp5",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51551 = CU.temp
      val tr51552 = CU.temp
      val tr51553 = CU.temp
      val tr51554 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4579 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4579))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51551)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51551), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4579.writeAddr, CU.temp(stage(2), tr51552)))
      stage = stage0 +: RAStages(2, List(x4049_x4579))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51553)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51553), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4579.readAddr, CU.temp(stage(2), tr51554)))
    }
    val x4049_dsp6 = MemoryPipeline(name="x4049_dsp6",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51556 = CU.temp
      val tr51557 = CU.temp
      val tr51558 = CU.temp
      val tr51559 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4570 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4570))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51556)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51556), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4570.writeAddr, CU.temp(stage(2), tr51557)))
      stage = stage0 +: RAStages(2, List(x4049_x4570))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51558)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51558), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4570.readAddr, CU.temp(stage(2), tr51559)))
    }
    val x4049_dsp7 = MemoryPipeline(name="x4049_dsp7",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51561 = CU.temp
      val tr51562 = CU.temp
      val tr51563 = CU.temp
      val tr51564 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4561 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4561))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51561)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51561), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4561.writeAddr, CU.temp(stage(2), tr51562)))
      stage = stage0 +: RAStages(2, List(x4049_x4561))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51563)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51563), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4561.readAddr, CU.temp(stage(2), tr51564)))
    }
    val x4049_dsp8 = MemoryPipeline(name="x4049_dsp8",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51566 = CU.temp
      val tr51567 = CU.temp
      val tr51568 = CU.temp
      val tr51569 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4552 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4552))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51566)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51566), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4552.writeAddr, CU.temp(stage(2), tr51567)))
      stage = stage0 +: RAStages(2, List(x4049_x4552))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51568)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51568), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4552.readAddr, CU.temp(stage(2), tr51569)))
    }
    val x4049_dsp9 = MemoryPipeline(name="x4049_dsp9",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51571 = CU.temp
      val tr51572 = CU.temp
      val tr51573 = CU.temp
      val tr51574 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4543 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4543))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51571)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51571), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4543.writeAddr, CU.temp(stage(2), tr51572)))
      stage = stage0 +: RAStages(2, List(x4049_x4543))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51573)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51573), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4543.readAddr, CU.temp(stage(2), tr51574)))
    }
    val x4049_dsp10 = MemoryPipeline(name="x4049_dsp10",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51576 = CU.temp
      val tr51577 = CU.temp
      val tr51578 = CU.temp
      val tr51579 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4534 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4534))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51576)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51576), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4534.writeAddr, CU.temp(stage(2), tr51577)))
      stage = stage0 +: RAStages(2, List(x4049_x4534))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51578)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51578), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4534.readAddr, CU.temp(stage(2), tr51579)))
    }
    val x4049_dsp11 = MemoryPipeline(name="x4049_dsp11",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51581 = CU.temp
      val tr51582 = CU.temp
      val tr51583 = CU.temp
      val tr51584 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4525 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4525))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51581)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51581), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4525.writeAddr, CU.temp(stage(2), tr51582)))
      stage = stage0 +: RAStages(2, List(x4049_x4525))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51583)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51583), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4525.readAddr, CU.temp(stage(2), tr51584)))
    }
    val x4049_dsp12 = MemoryPipeline(name="x4049_dsp12",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51586 = CU.temp
      val tr51587 = CU.temp
      val tr51588 = CU.temp
      val tr51589 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4516 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4516))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51586)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51586), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4516.writeAddr, CU.temp(stage(2), tr51587)))
      stage = stage0 +: RAStages(2, List(x4049_x4516))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51588)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51588), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4516.readAddr, CU.temp(stage(2), tr51589)))
    }
    val x4049_dsp13 = MemoryPipeline(name="x4049_dsp13",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51591 = CU.temp
      val tr51592 = CU.temp
      val tr51593 = CU.temp
      val tr51594 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4507 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4507))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51591)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51591), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4507.writeAddr, CU.temp(stage(2), tr51592)))
      stage = stage0 +: RAStages(2, List(x4049_x4507))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51593)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51593), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4507.readAddr, CU.temp(stage(2), tr51594)))
    }
    val x4049_dsp14 = MemoryPipeline(name="x4049_dsp14",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51596 = CU.temp
      val tr51597 = CU.temp
      val tr51598 = CU.temp
      val tr51599 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4498 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4498))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51596)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51596), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4498.writeAddr, CU.temp(stage(2), tr51597)))
      stage = stage0 +: RAStages(2, List(x4049_x4498))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51598)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51598), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4498.readAddr, CU.temp(stage(2), tr51599)))
    }
    val x4049_dsp15 = MemoryPipeline(name="x4049_dsp15",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51601 = CU.temp
      val tr51602 = CU.temp
      val tr51603 = CU.temp
      val tr51604 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4489 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4489))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51601)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51601), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4489.writeAddr, CU.temp(stage(2), tr51602)))
      stage = stage0 +: RAStages(2, List(x4049_x4489))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51603)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51603), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4489.readAddr, CU.temp(stage(2), tr51604)))
    }
    val x4049_dsp16 = MemoryPipeline(name="x4049_dsp16",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51606 = CU.temp
      val tr51607 = CU.temp
      val tr51608 = CU.temp
      val tr51609 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4332 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4332))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51606)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51606), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4332.writeAddr, CU.temp(stage(2), tr51607)))
      stage = stage0 +: RAStages(2, List(x4049_x4332))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51608)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51608), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4332.readAddr, CU.temp(stage(2), tr51609)))
    }
    val x4049_dsp17 = MemoryPipeline(name="x4049_dsp17",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51611 = CU.temp
      val tr51612 = CU.temp
      val tr51613 = CU.temp
      val tr51614 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4321 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4321))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51611)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51611), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4321.writeAddr, CU.temp(stage(2), tr51612)))
      stage = stage0 +: RAStages(2, List(x4049_x4321))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51613)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51613), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4321.readAddr, CU.temp(stage(2), tr51614)))
    }
    val x4049_dsp18 = MemoryPipeline(name="x4049_dsp18",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51616 = CU.temp
      val tr51617 = CU.temp
      val tr51618 = CU.temp
      val tr51619 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4310 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4310))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51616)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51616), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4310.writeAddr, CU.temp(stage(2), tr51617)))
      stage = stage0 +: RAStages(2, List(x4049_x4310))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51618)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51618), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4310.readAddr, CU.temp(stage(2), tr51619)))
    }
    val x4049_dsp19 = MemoryPipeline(name="x4049_dsp19",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51621 = CU.temp
      val tr51622 = CU.temp
      val tr51623 = CU.temp
      val tr51624 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4299 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4299))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51621)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51621), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4299.writeAddr, CU.temp(stage(2), tr51622)))
      stage = stage0 +: RAStages(2, List(x4049_x4299))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51623)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51623), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4299.readAddr, CU.temp(stage(2), tr51624)))
    }
    val x4049_dsp20 = MemoryPipeline(name="x4049_dsp20",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51626 = CU.temp
      val tr51627 = CU.temp
      val tr51628 = CU.temp
      val tr51629 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4288 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4288))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51626)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51626), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4288.writeAddr, CU.temp(stage(2), tr51627)))
      stage = stage0 +: RAStages(2, List(x4049_x4288))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51628)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51628), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4288.readAddr, CU.temp(stage(2), tr51629)))
    }
    val x4049_dsp21 = MemoryPipeline(name="x4049_dsp21",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51631 = CU.temp
      val tr51632 = CU.temp
      val tr51633 = CU.temp
      val tr51634 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4277 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4277))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51631)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51631), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4277.writeAddr, CU.temp(stage(2), tr51632)))
      stage = stage0 +: RAStages(2, List(x4049_x4277))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51633)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51633), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4277.readAddr, CU.temp(stage(2), tr51634)))
    }
    val x4049_dsp22 = MemoryPipeline(name="x4049_dsp22",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51636 = CU.temp
      val tr51637 = CU.temp
      val tr51638 = CU.temp
      val tr51639 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4266 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4266))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51636)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51636), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4266.writeAddr, CU.temp(stage(2), tr51637)))
      stage = stage0 +: RAStages(2, List(x4049_x4266))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51638)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51638), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4266.readAddr, CU.temp(stage(2), tr51639)))
    }
    val x4049_dsp23 = MemoryPipeline(name="x4049_dsp23",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51641 = CU.temp
      val tr51642 = CU.temp
      val tr51643 = CU.temp
      val tr51644 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4255 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4255))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51641)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51641), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4255.writeAddr, CU.temp(stage(2), tr51642)))
      stage = stage0 +: RAStages(2, List(x4049_x4255))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51643)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51643), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4255.readAddr, CU.temp(stage(2), tr51644)))
    }
    val x4049_dsp24 = MemoryPipeline(name="x4049_dsp24",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51646 = CU.temp
      val tr51647 = CU.temp
      val tr51648 = CU.temp
      val tr51649 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4244 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4244))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51646)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51646), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4244.writeAddr, CU.temp(stage(2), tr51647)))
      stage = stage0 +: RAStages(2, List(x4049_x4244))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51648)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51648), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4244.readAddr, CU.temp(stage(2), tr51649)))
    }
    val x4049_dsp25 = MemoryPipeline(name="x4049_dsp25",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51651 = CU.temp
      val tr51652 = CU.temp
      val tr51653 = CU.temp
      val tr51654 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4233 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4233))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51651)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51651), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4233.writeAddr, CU.temp(stage(2), tr51652)))
      stage = stage0 +: RAStages(2, List(x4049_x4233))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51653)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51653), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4233.readAddr, CU.temp(stage(2), tr51654)))
    }
    val x4049_dsp26 = MemoryPipeline(name="x4049_dsp26",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51656 = CU.temp
      val tr51657 = CU.temp
      val tr51658 = CU.temp
      val tr51659 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4222 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4222))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51656)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51656), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4222.writeAddr, CU.temp(stage(2), tr51657)))
      stage = stage0 +: RAStages(2, List(x4049_x4222))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51658)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51658), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4222.readAddr, CU.temp(stage(2), tr51659)))
    }
    val x4049_dsp27 = MemoryPipeline(name="x4049_dsp27",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51661 = CU.temp
      val tr51662 = CU.temp
      val tr51663 = CU.temp
      val tr51664 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4211 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4211))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51661)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51661), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4211.writeAddr, CU.temp(stage(2), tr51662)))
      stage = stage0 +: RAStages(2, List(x4049_x4211))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51663)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51663), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4211.readAddr, CU.temp(stage(2), tr51664)))
    }
    val x4049_dsp28 = MemoryPipeline(name="x4049_dsp28",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51666 = CU.temp
      val tr51667 = CU.temp
      val tr51668 = CU.temp
      val tr51669 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4200 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4200))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51666)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51666), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4200.writeAddr, CU.temp(stage(2), tr51667)))
      stage = stage0 +: RAStages(2, List(x4049_x4200))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51668)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51668), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4200.readAddr, CU.temp(stage(2), tr51669)))
    }
    val x4049_dsp29 = MemoryPipeline(name="x4049_dsp29",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51671 = CU.temp
      val tr51672 = CU.temp
      val tr51673 = CU.temp
      val tr51674 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4189 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4189))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51671)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51671), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4189.writeAddr, CU.temp(stage(2), tr51672)))
      stage = stage0 +: RAStages(2, List(x4049_x4189))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51673)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51673), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4189.readAddr, CU.temp(stage(2), tr51674)))
    }
    val x4049_dsp30 = MemoryPipeline(name="x4049_dsp30",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51676 = CU.temp
      val tr51677 = CU.temp
      val tr51678 = CU.temp
      val tr51679 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4178 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4178))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51676)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51676), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4178.writeAddr, CU.temp(stage(2), tr51677)))
      stage = stage0 +: RAStages(2, List(x4049_x4178))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51678)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51678), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4178.readAddr, CU.temp(stage(2), tr51679)))
    }
    val x4049_dsp31 = MemoryPipeline(name="x4049_dsp31",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51681 = CU.temp
      val tr51682 = CU.temp
      val tr51683 = CU.temp
      val tr51684 = CU.temp
      val x4166 = CounterChain.copy("x4175", "x4166")
      val x4276 = CounterChain.copy("x4285", "x4276")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4177 = CounterChain.copy("x4186", "x4177")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4309 = CounterChain.copy("x4318", "x4309")
      val x4331 = CounterChain.copy("x4340", "x4331")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4298 = CounterChain.copy("x4307", "x4298")
      val x4265 = CounterChain.copy("x4274", "x4265")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4254 = CounterChain.copy("x4263", "x4254")
      val x4287 = CounterChain.copy("x4296", "x4287")
      val x4210 = CounterChain.copy("x4219", "x4210")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4066 = CounterChain.copy("x4071", "x4066")
      val x4232 = CounterChain.copy("x4241", "x4232")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4320 = CounterChain.copy("x4329", "x4320")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4188 = CounterChain.copy("x4197", "x4188")
      val x4243 = CounterChain.copy("x4252", "x4243")
      val x4199 = CounterChain.copy("x4208", "x4199")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4221 = CounterChain.copy("x4230", "x4221")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4049_x4167 = SRAM(size = 7680, banking = Strided(1)).wtPort(x4049_x4070_vector).rdPort(x4049_x4624_x4630_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4049_x4167))
      Stage(stage(1), operands=List(x4052(0), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51681)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51681), CU.ctr(stage(1), x4066(0))), op=FixAdd, results=List(x4049_x4167.writeAddr, CU.temp(stage(2), tr51682)))
      stage = stage0 +: RAStages(2, List(x4049_x4167))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("192i")), op=FixMul, results=List(CU.temp(stage(1), tr51683)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51683), CU.ctr(stage(1), x4623(0))), op=FixAdd, results=List(x4049_x4167.readAddr, CU.temp(stage(2), tr51684)))
    }
    val x4050_dsp0 = MemoryPipeline(name="x4050_dsp0",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51685 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4477 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4477))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4477.writeAddr, CU.temp(stage(1), tr51685)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51685)))
    }
    val x4050_dsp1 = MemoryPipeline(name="x4050_dsp1",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51686 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4468 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4468))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4468.writeAddr, CU.temp(stage(1), tr51686)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51686)))
    }
    val x4050_dsp2 = MemoryPipeline(name="x4050_dsp2",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51687 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4459 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4459))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4459.writeAddr, CU.temp(stage(1), tr51687)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51687)))
    }
    val x4050_dsp3 = MemoryPipeline(name="x4050_dsp3",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51688 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4450 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4450))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4450.writeAddr, CU.temp(stage(1), tr51688)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51688)))
    }
    val x4050_dsp4 = MemoryPipeline(name="x4050_dsp4",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51689 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4441 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4441))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4441.writeAddr, CU.temp(stage(1), tr51689)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51689)))
    }
    val x4050_dsp5 = MemoryPipeline(name="x4050_dsp5",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51690 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4432 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4432))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4432.writeAddr, CU.temp(stage(1), tr51690)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51690)))
    }
    val x4050_dsp6 = MemoryPipeline(name="x4050_dsp6",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51691 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4423 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4423))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4423.writeAddr, CU.temp(stage(1), tr51691)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51691)))
    }
    val x4050_dsp7 = MemoryPipeline(name="x4050_dsp7",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51692 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4414 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4414))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4414.writeAddr, CU.temp(stage(1), tr51692)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51692)))
    }
    val x4050_dsp8 = MemoryPipeline(name="x4050_dsp8",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51693 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4405 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4405))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4405.writeAddr, CU.temp(stage(1), tr51693)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51693)))
    }
    val x4050_dsp9 = MemoryPipeline(name="x4050_dsp9",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51694 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4396 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4396))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4396.writeAddr, CU.temp(stage(1), tr51694)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51694)))
    }
    val x4050_dsp10 = MemoryPipeline(name="x4050_dsp10",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51695 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4387 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4387))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4387.writeAddr, CU.temp(stage(1), tr51695)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51695)))
    }
    val x4050_dsp11 = MemoryPipeline(name="x4050_dsp11",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51696 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4378 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4378))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4378.writeAddr, CU.temp(stage(1), tr51696)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51696)))
    }
    val x4050_dsp12 = MemoryPipeline(name="x4050_dsp12",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51697 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4369 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4369))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4369.writeAddr, CU.temp(stage(1), tr51697)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51697)))
    }
    val x4050_dsp13 = MemoryPipeline(name="x4050_dsp13",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51698 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4360 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4360))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4360.writeAddr, CU.temp(stage(1), tr51698)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51698)))
    }
    val x4050_dsp14 = MemoryPipeline(name="x4050_dsp14",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51699 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4351 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4351))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4351.writeAddr, CU.temp(stage(1), tr51699)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51699)))
    }
    val x4050_dsp15 = MemoryPipeline(name="x4050_dsp15",parent="x4704") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51700 = CU.temp
      val x4098 = CounterChain.copy("x4111", "x4098")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4050_x4342 = SRAM(size = 40, banking = Strided(1)).wtPort(x4050_x4110_vector).rdPort(x4050_x4477_x4485_vector)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x4050_x4342))
      Stage(stage(1), operands=List(x4098(0), x4085_x4099.load), op=FixSub, results=List(x4050_x4342.writeAddr, CU.temp(stage(1), tr51700)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4098(0)), CU.load(stage(1), x4085_x4099)), op=FixSub, results=List(CU.temp(stage(2), tr51700)))
    }
    val x4073 = StreamController(name="x4073",parent=x4704) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("40i").out, Const("1i").out) // Counter
      val x4052 = CounterChain(name = "x4052", ctr4)
      var stage: List[Stage] = Nil
    }
    val x4061 = UnitPipeline(name="x4061",parent=x4073) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51701 = CU.temp
      val tr51703 = CU.temp
      val tr51705 = CU.temp
      val tr51706 = CU.temp
      val tr51707 = CU.temp
      val tr51708 = CU.temp
      val tr51709 = CU.temp
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4052 = CounterChain.copy("x4073", "x4052")
      val x4056 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4056_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4048(0)), CU.ctr(stage(0), x4052(0))), op=FixAdd, results=List(CU.temp(stage(1), tr51701)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51701), Const("192i")), op=FixMul, results=List(CU.temp(stage(2), tr51703)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51703), Const("4i")), op=FixMul, results=List(CU.temp(stage(3), tr51705)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr51705), CU.load(stage(3), x4056)), op=FixAdd, results=List(CU.temp(stage(4), tr51706)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr51707)), op=Bypass, results=List(CU.scalarOut(stage(5), x4053_b4803_x4059_b4806_scalar)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr51708)), op=Bypass, results=List(CU.scalarOut(stage(6), x4053_b4804_x4059_b4807_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr51709)), op=Bypass, results=List(CU.scalarOut(stage(7), x4053_b4805_x4059_b4808_scalar)))
      Stage(stage(8), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(8), x4054_x4060_scalar)))
    }
    val x4062 = Fringe(name="x4062",parent=x4073,dram=x4030_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4053_b4805_x4062 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4053_b4805_x4059_b4808_scalar).rdPort(None)
      val x4053_b4804_x4062 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4053_b4804_x4059_b4807_scalar).rdPort(None)
      val x4053_b4803_x4062 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4053_b4803_x4059_b4806_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x4072 = Sequential(name="x4072",parent=x4073) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4064 = Sequential(name="x4064",parent=x4072) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4071 = Pipeline(name="x4071",parent=x4072) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4052 = CounterChain.copy("x4073", "x4052")
      val ctr5 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4066 = CounterChain(name = "x4066", ctr5)
      val x4055_x4067 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4055_x4067.load), op=Bypass, results=List(CU.vecOut(stage(1), x4049_x4070_vector)))
    }
    val x4113 = Sequential(name="x4113",parent=x4704) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4083 = UnitPipeline(name="x4083",parent=x4113) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51711 = CU.temp
      val tr51713 = CU.temp
      val tr51715 = CU.temp
      val tr51716 = CU.temp
      val tr51717 = CU.temp
      val tr51718 = CU.temp
      val tr51719 = CU.temp
      val tr51720 = CU.temp
      val tr51721 = CU.temp
      val tr51722 = CU.temp
      val tr51723 = CU.temp
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4077 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4077_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(11)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4048(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr51711)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51711), Const("16i")), op=FixMod, results=List(CU.temp(stage(2), tr51713)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51713), Const("40i")), op=FixAdd, results=List(CU.temp(stage(3), tr51715)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr51711), CU.load(stage(3), x4077)), op=FixAdd, results=List(CU.temp(stage(4), tr51719)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr51719), CU.temp(stage(4), tr51717)), op=FixSub, results=List(CU.temp(stage(5), tr51720)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr51721)), op=Bypass, results=List(CU.scalarOut(stage(6), x4074_b4811_x4081_b4820_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr51722)), op=Bypass, results=List(CU.scalarOut(stage(7), x4074_b4812_x4081_b4821_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr51723)), op=Bypass, results=List(CU.scalarOut(stage(8), x4074_b4813_x4081_b4822_scalar)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr51716)), op=Bypass, results=List(CU.scalarOut(stage(9), x4075_b4814_x4082_b4823_scalar)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr51717)), op=Bypass, results=List(CU.scalarOut(stage(10), x4075_b4815_x4082_b4824_scalar)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr51718)), op=Bypass, results=List(CU.scalarOut(stage(11), x4075_b4816_x4082_b4825_scalar)))
    }
    val x4084 = Fringe(name="x4084",parent=x4113,dram=x4032_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4074_b4811_x4084 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4074_b4811_x4081_b4820_scalar).rdPort(None)
      val x4074_b4813_x4084 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4074_b4813_x4081_b4822_scalar).rdPort(None)
      val x4074_b4812_x4084 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4074_b4812_x4081_b4821_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x4112 = Sequential(name="x4112",parent=x4113) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4095 = UnitPipeline(name="x4095",parent=x4112) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51724 = CU.temp
      val tr51725 = CU.temp
      val tr51726 = CU.temp
      val x4075_b4814_x4088_b4817 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x4075_b4814_x4082_b4823_scalar).rdPort(None)
      val x4075_b4816_x4088_b4819 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x4075_b4816_x4082_b4825_scalar).rdPort(None)
      val x4075_b4815_x4088_b4818 = ScalarFIFO(size = 16, banking = Strided(1)).wtPort(x4075_b4815_x4082_b4824_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(x4075_b4815_x4088_b4818.load), op=Bypass, results=List(CU.scalarOut(stage(1), x4085_x4090_scalar)))
      Stage(stage(2), operands=List(x4075_b4816_x4088_b4819.load), op=Bypass, results=List(CU.scalarOut(stage(2), x4086_x4092_scalar)))
      Stage(stage(3), operands=List(x4075_b4814_x4088_b4817.load), op=Bypass, results=List(CU.scalarOut(stage(3), x4087_x4094_scalar)))
    }
    val x4111 = Pipeline(name="x4111",parent=x4112) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, x4087_x4096.load, Const("1i").out) // Counter
      val x4098 = CounterChain(name = "x4098", ctr6)
      val x4076_x4101 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      val x4087_x4096 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4087_x4094_scalar).rdPort(None)
      val x4086_x4100 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4086_x4092_scalar).rdPort(None)
      val x4085_x4099 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4085_x4090_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x4076_x4101.load), op=Bypass, results=List(CU.vecOut(stage(1), x4050_x4110_vector)))
    }
    val x4703 = MetaPipeline(name="x4703",parent=x4704) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("40i").out, Const("1i").out) // Counter
      val x4116 = CounterChain(name = "x4116", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4133_dsp0 = MemoryPipeline(name="x4133_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4488 = CounterChain.copy("x4495", "x4488")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4133_x4634 = SRAM(size = 192, banking = Strided(1)).wtPort(x4133_x4494_vector).rdPort(x4133_x4634_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4134_dsp0 = MemoryPipeline(name="x4134_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4497 = CounterChain.copy("x4504", "x4497")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4134_x4635 = SRAM(size = 192, banking = Strided(1)).wtPort(x4134_x4503_vector).rdPort(x4134_x4635_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4135_dsp0 = MemoryPipeline(name="x4135_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4506 = CounterChain.copy("x4513", "x4506")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4135_x4636 = SRAM(size = 192, banking = Strided(1)).wtPort(x4135_x4512_vector).rdPort(x4135_x4636_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4136_dsp0 = MemoryPipeline(name="x4136_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4515 = CounterChain.copy("x4522", "x4515")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4136_x4637 = SRAM(size = 192, banking = Strided(1)).wtPort(x4136_x4521_vector).rdPort(x4136_x4637_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4137_dsp0 = MemoryPipeline(name="x4137_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4524 = CounterChain.copy("x4531", "x4524")
      val x4137_x4638 = SRAM(size = 192, banking = Strided(1)).wtPort(x4137_x4530_vector).rdPort(x4137_x4638_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4138_dsp0 = MemoryPipeline(name="x4138_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4533 = CounterChain.copy("x4540", "x4533")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4138_x4639 = SRAM(size = 192, banking = Strided(1)).wtPort(x4138_x4539_vector).rdPort(x4138_x4639_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4139_dsp0 = MemoryPipeline(name="x4139_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4542 = CounterChain.copy("x4549", "x4542")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4139_x4640 = SRAM(size = 192, banking = Strided(1)).wtPort(x4139_x4548_vector).rdPort(x4139_x4640_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4140_dsp0 = MemoryPipeline(name="x4140_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4551 = CounterChain.copy("x4558", "x4551")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4140_x4641 = SRAM(size = 192, banking = Strided(1)).wtPort(x4140_x4557_vector).rdPort(x4140_x4641_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4141_dsp0 = MemoryPipeline(name="x4141_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4560 = CounterChain.copy("x4567", "x4560")
      val x4141_x4642 = SRAM(size = 192, banking = Strided(1)).wtPort(x4141_x4566_vector).rdPort(x4141_x4642_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4142_dsp0 = MemoryPipeline(name="x4142_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4569 = CounterChain.copy("x4576", "x4569")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4142_x4643 = SRAM(size = 192, banking = Strided(1)).wtPort(x4142_x4575_vector).rdPort(x4142_x4643_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4143_dsp0 = MemoryPipeline(name="x4143_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4578 = CounterChain.copy("x4585", "x4578")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4143_x4644 = SRAM(size = 192, banking = Strided(1)).wtPort(x4143_x4584_vector).rdPort(x4143_x4644_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4144_dsp0 = MemoryPipeline(name="x4144_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4587 = CounterChain.copy("x4594", "x4587")
      val x4144_x4645 = SRAM(size = 192, banking = Strided(1)).wtPort(x4144_x4593_vector).rdPort(x4144_x4645_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4145_dsp0 = MemoryPipeline(name="x4145_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4596 = CounterChain.copy("x4603", "x4596")
      val x4145_x4646 = SRAM(size = 192, banking = Strided(1)).wtPort(x4145_x4602_vector).rdPort(x4145_x4646_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4146_dsp0 = MemoryPipeline(name="x4146_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4605 = CounterChain.copy("x4612", "x4605")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4146_x4647 = SRAM(size = 192, banking = Strided(1)).wtPort(x4146_x4611_vector).rdPort(x4146_x4647_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4147_dsp0 = MemoryPipeline(name="x4147_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4614 = CounterChain.copy("x4621", "x4614")
      val x4147_x4648 = SRAM(size = 192, banking = Strided(1)).wtPort(x4147_x4620_vector).rdPort(x4147_x4648_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4148_dsp0 = MemoryPipeline(name="x4148_dsp0",parent="x4703") { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042 = CounterChain.copy("x4718", "x4042")
      val x4623 = CounterChain.copy("x4630", "x4623")
      val x4633 = CounterChain.copy("x4702", "x4633")
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      val x4148_x4649 = SRAM(size = 192, banking = Strided(1)).wtPort(x4148_x4629_vector).rdPort(x4148_x4649_x4702_vector)
      var stage: List[Stage] = Nil
    }
    val x4175 = Pipeline(name="x4175",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51727 = CU.temp
      val ar386 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr8 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4166 = CounterChain(name = "x4166", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4167_x4175_vector), CU.vecIn(stage(0), x4039_x4168_x4175_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51727)))
      val (rs1, rr51730) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51730), op=Bypass, results=List(CU.vecOut(stage(2), x4149_x4174_vector)))
    }
    val x4186 = Pipeline(name="x4186",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51731 = CU.temp
      val ar388 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr9 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4177 = CounterChain(name = "x4177", ctr9)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4178_x4186_vector), CU.vecIn(stage(0), x4039_x4179_x4186_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51731)))
      val (rs1, rr51734) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51734), op=Bypass, results=List(CU.vecOut(stage(2), x4150_x4185_vector)))
    }
    val x4197 = Pipeline(name="x4197",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51735 = CU.temp
      val ar390 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr10 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4188 = CounterChain(name = "x4188", ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4189_x4197_vector), CU.vecIn(stage(0), x4039_x4190_x4197_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51735)))
      val (rs1, rr51738) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51738), op=Bypass, results=List(CU.vecOut(stage(2), x4151_x4196_vector)))
    }
    val x4208 = Pipeline(name="x4208",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51739 = CU.temp
      val ar392 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr11 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4199 = CounterChain(name = "x4199", ctr11)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4200_x4208_vector), CU.vecIn(stage(0), x4039_x4201_x4208_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51739)))
      val (rs1, rr51742) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51742), op=Bypass, results=List(CU.vecOut(stage(2), x4152_x4207_vector)))
    }
    val x4219 = Pipeline(name="x4219",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51743 = CU.temp
      val ar394 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr12 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4210 = CounterChain(name = "x4210", ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4211_x4219_vector), CU.vecIn(stage(0), x4039_x4212_x4219_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51743)))
      val (rs1, rr51746) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51746), op=Bypass, results=List(CU.vecOut(stage(2), x4153_x4218_vector)))
    }
    val x4230 = Pipeline(name="x4230",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51747 = CU.temp
      val ar396 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr13 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4221 = CounterChain(name = "x4221", ctr13)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4222_x4230_vector), CU.vecIn(stage(0), x4039_x4223_x4230_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51747)))
      val (rs1, rr51750) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51750), op=Bypass, results=List(CU.vecOut(stage(2), x4154_x4229_vector)))
    }
    val x4241 = Pipeline(name="x4241",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51751 = CU.temp
      val ar398 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr14 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4232 = CounterChain(name = "x4232", ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4233_x4241_vector), CU.vecIn(stage(0), x4039_x4234_x4241_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51751)))
      val (rs1, rr51754) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51754), op=Bypass, results=List(CU.vecOut(stage(2), x4155_x4240_vector)))
    }
    val x4252 = Pipeline(name="x4252",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51755 = CU.temp
      val ar400 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr15 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4243 = CounterChain(name = "x4243", ctr15)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4244_x4252_vector), CU.vecIn(stage(0), x4039_x4245_x4252_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51755)))
      val (rs1, rr51758) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51758), op=Bypass, results=List(CU.vecOut(stage(2), x4156_x4251_vector)))
    }
    val x4263 = Pipeline(name="x4263",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51759 = CU.temp
      val ar402 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr16 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4254 = CounterChain(name = "x4254", ctr16)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4255_x4263_vector), CU.vecIn(stage(0), x4039_x4256_x4263_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51759)))
      val (rs1, rr51762) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51762), op=Bypass, results=List(CU.vecOut(stage(2), x4157_x4262_vector)))
    }
    val x4274 = Pipeline(name="x4274",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51763 = CU.temp
      val ar404 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr17 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4265 = CounterChain(name = "x4265", ctr17)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4266_x4274_vector), CU.vecIn(stage(0), x4039_x4267_x4274_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51763)))
      val (rs1, rr51766) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51766), op=Bypass, results=List(CU.vecOut(stage(2), x4158_x4273_vector)))
    }
    val x4285 = Pipeline(name="x4285",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51767 = CU.temp
      val ar406 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr18 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4276 = CounterChain(name = "x4276", ctr18)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4277_x4285_vector), CU.vecIn(stage(0), x4039_x4278_x4285_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51767)))
      val (rs1, rr51770) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51770), op=Bypass, results=List(CU.vecOut(stage(2), x4159_x4284_vector)))
    }
    val x4296 = Pipeline(name="x4296",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51771 = CU.temp
      val ar408 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr19 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4287 = CounterChain(name = "x4287", ctr19)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4288_x4296_vector), CU.vecIn(stage(0), x4039_x4289_x4296_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51771)))
      val (rs1, rr51774) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51774), op=Bypass, results=List(CU.vecOut(stage(2), x4160_x4295_vector)))
    }
    val x4307 = Pipeline(name="x4307",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51775 = CU.temp
      val ar410 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr20 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4298 = CounterChain(name = "x4298", ctr20)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4299_x4307_vector), CU.vecIn(stage(0), x4039_x4300_x4307_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51775)))
      val (rs1, rr51778) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51778), op=Bypass, results=List(CU.vecOut(stage(2), x4161_x4306_vector)))
    }
    val x4318 = Pipeline(name="x4318",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51779 = CU.temp
      val ar412 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr21 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4309 = CounterChain(name = "x4309", ctr21)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4310_x4318_vector), CU.vecIn(stage(0), x4039_x4311_x4318_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51779)))
      val (rs1, rr51782) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51782), op=Bypass, results=List(CU.vecOut(stage(2), x4162_x4317_vector)))
    }
    val x4329 = Pipeline(name="x4329",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51783 = CU.temp
      val ar414 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr22 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4320 = CounterChain(name = "x4320", ctr22)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4321_x4329_vector), CU.vecIn(stage(0), x4039_x4322_x4329_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51783)))
      val (rs1, rr51786) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51786), op=Bypass, results=List(CU.vecOut(stage(2), x4163_x4328_vector)))
    }
    val x4340 = Pipeline(name="x4340",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51787 = CU.temp
      val ar416 = CU.accum(init = Const("0i"))
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr23 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4331 = CounterChain(name = "x4331", ctr23)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4332_x4340_vector), CU.vecIn(stage(0), x4039_x4333_x4340_vector)), op=FltMul, results=List(CU.reduce(stage(1)), CU.temp(stage(1), tr51787)))
      val (rs1, rr51790) = Stage.reduce(op=FltAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr51790), op=Bypass, results=List(CU.vecOut(stage(2), x4164_x4339_vector)))
    }
    val x4350 = UnitPipeline(name="x4350",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51791 = CU.temp
      val tr51792 = CU.temp
      val tr51794 = CU.temp
      val tr51795 = CU.temp
      val tr51796 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4149_x4343 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4149_x4174_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4149_x4343.load), op=FltNeg, results=List(CU.temp(stage(1), tr51791)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51791)), op=FltExp, results=List(CU.temp(stage(2), tr51792)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51792), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51794)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51794)), op=FltDiv, results=List(CU.temp(stage(4), tr51795)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4342_x4350_vector), CU.temp(stage(4), tr51795)), op=FltSub, results=List(CU.scalarOut(stage(5), x4117_x4349_scalar), CU.temp(stage(5), tr51796)))
    }
    val x4359 = UnitPipeline(name="x4359",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51797 = CU.temp
      val tr51798 = CU.temp
      val tr51800 = CU.temp
      val tr51801 = CU.temp
      val tr51802 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4150_x4352 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4150_x4185_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4150_x4352.load), op=FltNeg, results=List(CU.temp(stage(1), tr51797)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51797)), op=FltExp, results=List(CU.temp(stage(2), tr51798)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51798), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51800)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51800)), op=FltDiv, results=List(CU.temp(stage(4), tr51801)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4351_x4359_vector), CU.temp(stage(4), tr51801)), op=FltSub, results=List(CU.scalarOut(stage(5), x4118_x4358_scalar), CU.temp(stage(5), tr51802)))
    }
    val x4368 = UnitPipeline(name="x4368",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51803 = CU.temp
      val tr51804 = CU.temp
      val tr51806 = CU.temp
      val tr51807 = CU.temp
      val tr51808 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4151_x4361 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4151_x4196_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4151_x4361.load), op=FltNeg, results=List(CU.temp(stage(1), tr51803)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51803)), op=FltExp, results=List(CU.temp(stage(2), tr51804)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51804), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51806)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51806)), op=FltDiv, results=List(CU.temp(stage(4), tr51807)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4360_x4368_vector), CU.temp(stage(4), tr51807)), op=FltSub, results=List(CU.scalarOut(stage(5), x4119_x4367_scalar), CU.temp(stage(5), tr51808)))
    }
    val x4377 = UnitPipeline(name="x4377",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51809 = CU.temp
      val tr51810 = CU.temp
      val tr51812 = CU.temp
      val tr51813 = CU.temp
      val tr51814 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4152_x4370 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4152_x4207_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4152_x4370.load), op=FltNeg, results=List(CU.temp(stage(1), tr51809)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51809)), op=FltExp, results=List(CU.temp(stage(2), tr51810)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51810), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51812)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51812)), op=FltDiv, results=List(CU.temp(stage(4), tr51813)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4369_x4377_vector), CU.temp(stage(4), tr51813)), op=FltSub, results=List(CU.scalarOut(stage(5), x4120_x4376_scalar), CU.temp(stage(5), tr51814)))
    }
    val x4386 = UnitPipeline(name="x4386",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51815 = CU.temp
      val tr51816 = CU.temp
      val tr51818 = CU.temp
      val tr51819 = CU.temp
      val tr51820 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4153_x4379 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4153_x4218_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4153_x4379.load), op=FltNeg, results=List(CU.temp(stage(1), tr51815)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51815)), op=FltExp, results=List(CU.temp(stage(2), tr51816)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51816), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51818)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51818)), op=FltDiv, results=List(CU.temp(stage(4), tr51819)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4378_x4386_vector), CU.temp(stage(4), tr51819)), op=FltSub, results=List(CU.scalarOut(stage(5), x4121_x4385_scalar), CU.temp(stage(5), tr51820)))
    }
    val x4395 = UnitPipeline(name="x4395",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51821 = CU.temp
      val tr51822 = CU.temp
      val tr51824 = CU.temp
      val tr51825 = CU.temp
      val tr51826 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4154_x4388 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4154_x4229_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4154_x4388.load), op=FltNeg, results=List(CU.temp(stage(1), tr51821)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51821)), op=FltExp, results=List(CU.temp(stage(2), tr51822)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51822), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51824)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51824)), op=FltDiv, results=List(CU.temp(stage(4), tr51825)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4387_x4395_vector), CU.temp(stage(4), tr51825)), op=FltSub, results=List(CU.scalarOut(stage(5), x4122_x4394_scalar), CU.temp(stage(5), tr51826)))
    }
    val x4404 = UnitPipeline(name="x4404",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51827 = CU.temp
      val tr51828 = CU.temp
      val tr51830 = CU.temp
      val tr51831 = CU.temp
      val tr51832 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4155_x4397 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4155_x4240_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4155_x4397.load), op=FltNeg, results=List(CU.temp(stage(1), tr51827)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51827)), op=FltExp, results=List(CU.temp(stage(2), tr51828)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51828), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51830)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51830)), op=FltDiv, results=List(CU.temp(stage(4), tr51831)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4396_x4404_vector), CU.temp(stage(4), tr51831)), op=FltSub, results=List(CU.scalarOut(stage(5), x4123_x4403_scalar), CU.temp(stage(5), tr51832)))
    }
    val x4413 = UnitPipeline(name="x4413",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51833 = CU.temp
      val tr51834 = CU.temp
      val tr51836 = CU.temp
      val tr51837 = CU.temp
      val tr51838 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4156_x4406 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4156_x4251_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4156_x4406.load), op=FltNeg, results=List(CU.temp(stage(1), tr51833)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51833)), op=FltExp, results=List(CU.temp(stage(2), tr51834)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51834), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51836)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51836)), op=FltDiv, results=List(CU.temp(stage(4), tr51837)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4405_x4413_vector), CU.temp(stage(4), tr51837)), op=FltSub, results=List(CU.scalarOut(stage(5), x4124_x4412_scalar), CU.temp(stage(5), tr51838)))
    }
    val x4422 = UnitPipeline(name="x4422",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51839 = CU.temp
      val tr51840 = CU.temp
      val tr51842 = CU.temp
      val tr51843 = CU.temp
      val tr51844 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4157_x4415 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4157_x4262_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4157_x4415.load), op=FltNeg, results=List(CU.temp(stage(1), tr51839)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51839)), op=FltExp, results=List(CU.temp(stage(2), tr51840)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51840), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51842)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51842)), op=FltDiv, results=List(CU.temp(stage(4), tr51843)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4414_x4422_vector), CU.temp(stage(4), tr51843)), op=FltSub, results=List(CU.scalarOut(stage(5), x4125_x4421_scalar), CU.temp(stage(5), tr51844)))
    }
    val x4431 = UnitPipeline(name="x4431",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51845 = CU.temp
      val tr51846 = CU.temp
      val tr51848 = CU.temp
      val tr51849 = CU.temp
      val tr51850 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4158_x4424 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4158_x4273_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4158_x4424.load), op=FltNeg, results=List(CU.temp(stage(1), tr51845)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51845)), op=FltExp, results=List(CU.temp(stage(2), tr51846)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51846), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51848)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51848)), op=FltDiv, results=List(CU.temp(stage(4), tr51849)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4423_x4431_vector), CU.temp(stage(4), tr51849)), op=FltSub, results=List(CU.scalarOut(stage(5), x4126_x4430_scalar), CU.temp(stage(5), tr51850)))
    }
    val x4440 = UnitPipeline(name="x4440",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51851 = CU.temp
      val tr51852 = CU.temp
      val tr51854 = CU.temp
      val tr51855 = CU.temp
      val tr51856 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4159_x4433 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4159_x4284_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4159_x4433.load), op=FltNeg, results=List(CU.temp(stage(1), tr51851)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51851)), op=FltExp, results=List(CU.temp(stage(2), tr51852)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51852), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51854)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51854)), op=FltDiv, results=List(CU.temp(stage(4), tr51855)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4432_x4440_vector), CU.temp(stage(4), tr51855)), op=FltSub, results=List(CU.scalarOut(stage(5), x4127_x4439_scalar), CU.temp(stage(5), tr51856)))
    }
    val x4449 = UnitPipeline(name="x4449",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51857 = CU.temp
      val tr51858 = CU.temp
      val tr51860 = CU.temp
      val tr51861 = CU.temp
      val tr51862 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4160_x4442 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4160_x4295_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4160_x4442.load), op=FltNeg, results=List(CU.temp(stage(1), tr51857)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51857)), op=FltExp, results=List(CU.temp(stage(2), tr51858)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51858), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51860)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51860)), op=FltDiv, results=List(CU.temp(stage(4), tr51861)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4441_x4449_vector), CU.temp(stage(4), tr51861)), op=FltSub, results=List(CU.scalarOut(stage(5), x4128_x4448_scalar), CU.temp(stage(5), tr51862)))
    }
    val x4458 = UnitPipeline(name="x4458",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51863 = CU.temp
      val tr51864 = CU.temp
      val tr51866 = CU.temp
      val tr51867 = CU.temp
      val tr51868 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4161_x4451 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4161_x4306_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4161_x4451.load), op=FltNeg, results=List(CU.temp(stage(1), tr51863)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51863)), op=FltExp, results=List(CU.temp(stage(2), tr51864)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51864), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51866)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51866)), op=FltDiv, results=List(CU.temp(stage(4), tr51867)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4450_x4458_vector), CU.temp(stage(4), tr51867)), op=FltSub, results=List(CU.scalarOut(stage(5), x4129_x4457_scalar), CU.temp(stage(5), tr51868)))
    }
    val x4467 = UnitPipeline(name="x4467",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51869 = CU.temp
      val tr51870 = CU.temp
      val tr51872 = CU.temp
      val tr51873 = CU.temp
      val tr51874 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4162_x4460 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4162_x4317_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4162_x4460.load), op=FltNeg, results=List(CU.temp(stage(1), tr51869)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51869)), op=FltExp, results=List(CU.temp(stage(2), tr51870)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51870), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51872)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51872)), op=FltDiv, results=List(CU.temp(stage(4), tr51873)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4459_x4467_vector), CU.temp(stage(4), tr51873)), op=FltSub, results=List(CU.scalarOut(stage(5), x4130_x4466_scalar), CU.temp(stage(5), tr51874)))
    }
    val x4476 = UnitPipeline(name="x4476",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51875 = CU.temp
      val tr51876 = CU.temp
      val tr51878 = CU.temp
      val tr51879 = CU.temp
      val tr51880 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4163_x4469 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4163_x4328_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4163_x4469.load), op=FltNeg, results=List(CU.temp(stage(1), tr51875)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51875)), op=FltExp, results=List(CU.temp(stage(2), tr51876)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51876), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51878)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51878)), op=FltDiv, results=List(CU.temp(stage(4), tr51879)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4468_x4476_vector), CU.temp(stage(4), tr51879)), op=FltSub, results=List(CU.scalarOut(stage(5), x4131_x4475_scalar), CU.temp(stage(5), tr51880)))
    }
    val x4485 = UnitPipeline(name="x4485",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51881 = CU.temp
      val tr51882 = CU.temp
      val tr51884 = CU.temp
      val tr51885 = CU.temp
      val tr51886 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4164_x4478 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4164_x4339_vector).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(5)
      Stage(stage(1), operands=List(x4164_x4478.load), op=FltNeg, results=List(CU.temp(stage(1), tr51881)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51881)), op=FltExp, results=List(CU.temp(stage(2), tr51882)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51882), Const("1i")), op=FltAdd, results=List(CU.temp(stage(3), tr51884)))
      Stage(stage(4), operands=List(Const("1i"), CU.temp(stage(3), tr51884)), op=FltDiv, results=List(CU.temp(stage(4), tr51885)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4050_x4477_x4485_vector), CU.temp(stage(4), tr51885)), op=FltSub, results=List(CU.scalarOut(stage(5), x4132_x4484_scalar), CU.temp(stage(5), tr51886)))
    }
    val x4495 = Pipeline(name="x4495",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51887 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr24 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4488 = CounterChain(name = "x4488", ctr24)
      val x4117_x4490 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4117_x4349_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4489_x4495_vector), x4117_x4490.load), op=FltSub, results=List(CU.vecOut(stage(1), x4133_x4494_vector), CU.temp(stage(1), tr51887)))
    }
    val x4504 = Pipeline(name="x4504",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51888 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr25 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4497 = CounterChain(name = "x4497", ctr25)
      val x4118_x4499 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4118_x4358_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4498_x4504_vector), x4118_x4499.load), op=FltSub, results=List(CU.vecOut(stage(1), x4134_x4503_vector), CU.temp(stage(1), tr51888)))
    }
    val x4513 = Pipeline(name="x4513",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51889 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr26 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4506 = CounterChain(name = "x4506", ctr26)
      val x4119_x4508 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4119_x4367_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4507_x4513_vector), x4119_x4508.load), op=FltSub, results=List(CU.vecOut(stage(1), x4135_x4512_vector), CU.temp(stage(1), tr51889)))
    }
    val x4522 = Pipeline(name="x4522",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51890 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr27 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4515 = CounterChain(name = "x4515", ctr27)
      val x4120_x4517 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4120_x4376_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4516_x4522_vector), x4120_x4517.load), op=FltSub, results=List(CU.vecOut(stage(1), x4136_x4521_vector), CU.temp(stage(1), tr51890)))
    }
    val x4531 = Pipeline(name="x4531",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51891 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr28 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4524 = CounterChain(name = "x4524", ctr28)
      val x4121_x4526 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4121_x4385_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4525_x4531_vector), x4121_x4526.load), op=FltSub, results=List(CU.vecOut(stage(1), x4137_x4530_vector), CU.temp(stage(1), tr51891)))
    }
    val x4540 = Pipeline(name="x4540",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51892 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr29 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4533 = CounterChain(name = "x4533", ctr29)
      val x4122_x4535 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4122_x4394_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4534_x4540_vector), x4122_x4535.load), op=FltSub, results=List(CU.vecOut(stage(1), x4138_x4539_vector), CU.temp(stage(1), tr51892)))
    }
    val x4549 = Pipeline(name="x4549",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51893 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr30 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4542 = CounterChain(name = "x4542", ctr30)
      val x4123_x4544 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4123_x4403_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4543_x4549_vector), x4123_x4544.load), op=FltSub, results=List(CU.vecOut(stage(1), x4139_x4548_vector), CU.temp(stage(1), tr51893)))
    }
    val x4558 = Pipeline(name="x4558",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51894 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr31 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4551 = CounterChain(name = "x4551", ctr31)
      val x4124_x4553 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4124_x4412_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4552_x4558_vector), x4124_x4553.load), op=FltSub, results=List(CU.vecOut(stage(1), x4140_x4557_vector), CU.temp(stage(1), tr51894)))
    }
    val x4567 = Pipeline(name="x4567",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51895 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr32 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4560 = CounterChain(name = "x4560", ctr32)
      val x4125_x4562 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4125_x4421_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4561_x4567_vector), x4125_x4562.load), op=FltSub, results=List(CU.vecOut(stage(1), x4141_x4566_vector), CU.temp(stage(1), tr51895)))
    }
    val x4576 = Pipeline(name="x4576",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51896 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr33 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4569 = CounterChain(name = "x4569", ctr33)
      val x4126_x4571 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4126_x4430_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4570_x4576_vector), x4126_x4571.load), op=FltSub, results=List(CU.vecOut(stage(1), x4142_x4575_vector), CU.temp(stage(1), tr51896)))
    }
    val x4585 = Pipeline(name="x4585",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51897 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr34 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4578 = CounterChain(name = "x4578", ctr34)
      val x4127_x4580 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4127_x4439_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4579_x4585_vector), x4127_x4580.load), op=FltSub, results=List(CU.vecOut(stage(1), x4143_x4584_vector), CU.temp(stage(1), tr51897)))
    }
    val x4594 = Pipeline(name="x4594",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51898 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr35 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4587 = CounterChain(name = "x4587", ctr35)
      val x4128_x4589 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4128_x4448_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4588_x4594_vector), x4128_x4589.load), op=FltSub, results=List(CU.vecOut(stage(1), x4144_x4593_vector), CU.temp(stage(1), tr51898)))
    }
    val x4603 = Pipeline(name="x4603",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51899 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr36 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4596 = CounterChain(name = "x4596", ctr36)
      val x4129_x4598 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4129_x4457_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4597_x4603_vector), x4129_x4598.load), op=FltSub, results=List(CU.vecOut(stage(1), x4145_x4602_vector), CU.temp(stage(1), tr51899)))
    }
    val x4612 = Pipeline(name="x4612",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51900 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr37 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4605 = CounterChain(name = "x4605", ctr37)
      val x4130_x4607 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4130_x4466_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4606_x4612_vector), x4130_x4607.load), op=FltSub, results=List(CU.vecOut(stage(1), x4146_x4611_vector), CU.temp(stage(1), tr51900)))
    }
    val x4621 = Pipeline(name="x4621",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51901 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr38 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4614 = CounterChain(name = "x4614", ctr38)
      val x4131_x4616 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4131_x4475_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4615_x4621_vector), x4131_x4616.load), op=FltSub, results=List(CU.vecOut(stage(1), x4147_x4620_vector), CU.temp(stage(1), tr51901)))
    }
    val x4630 = Pipeline(name="x4630",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51902 = CU.temp
      val x4116 = CounterChain.copy("x4703", "x4116")
      val ctr39 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4623 = CounterChain(name = "x4623", ctr39)
      val x4132_x4625 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4132_x4484_scalar).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x4049_x4624_x4630_vector), x4132_x4625.load), op=FltSub, results=List(CU.vecOut(stage(1), x4148_x4629_vector), CU.temp(stage(1), tr51902)))
    }
    val x4702 = Pipeline(name="x4702",parent=x4703) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51903 = CU.temp
      val tr51963 = CU.temp
      val tr51964 = CU.temp
      val tr51965 = CU.temp
      val tr51966 = CU.temp
      val tr51967 = CU.temp
      val tr51968 = CU.temp
      val tr51969 = CU.temp
      val tr51970 = CU.temp
      val tr51971 = CU.temp
      val tr51972 = CU.temp
      val tr51973 = CU.temp
      val tr51974 = CU.temp
      val tr51975 = CU.temp
      val tr51976 = CU.temp
      val tr51977 = CU.temp
      val tr51978 = CU.temp
      val tr51979 = CU.temp
      val x4042 = CounterChain.copy("x4718", "x4042")
      val ctr40 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4633 = CounterChain(name = "x4633", ctr40)
      val x4116 = CounterChain.copy("x4703", "x4116")
      val x4048 = CounterChain.copy("x4704", "x4048")
      val x4044 = CounterChain.copy("x4717", "x4044")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(18)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4116(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr51903)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x4133_x4634_x4702_vector), CU.vecIn(stage(1), x4134_x4635_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(2), tr51963)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x4135_x4636_x4702_vector), CU.vecIn(stage(2), x4136_x4637_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(3), tr51964)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr51963), CU.temp(stage(3), tr51964)), op=FltAdd, results=List(CU.temp(stage(4), tr51965)))
      Stage(stage(5), operands=List(CU.vecIn(stage(4), x4137_x4638_x4702_vector), CU.vecIn(stage(4), x4138_x4639_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(5), tr51966)))
      Stage(stage(6), operands=List(CU.vecIn(stage(5), x4139_x4640_x4702_vector), CU.vecIn(stage(5), x4140_x4641_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(6), tr51967)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr51966), CU.temp(stage(6), tr51967)), op=FltAdd, results=List(CU.temp(stage(7), tr51968)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr51965), CU.temp(stage(7), tr51968)), op=FltAdd, results=List(CU.temp(stage(8), tr51969)))
      Stage(stage(9), operands=List(CU.vecIn(stage(8), x4141_x4642_x4702_vector), CU.vecIn(stage(8), x4142_x4643_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(9), tr51970)))
      Stage(stage(10), operands=List(CU.vecIn(stage(9), x4143_x4644_x4702_vector), CU.vecIn(stage(9), x4144_x4645_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(10), tr51971)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr51970), CU.temp(stage(10), tr51971)), op=FltAdd, results=List(CU.temp(stage(11), tr51972)))
      Stage(stage(12), operands=List(CU.vecIn(stage(11), x4145_x4646_x4702_vector), CU.vecIn(stage(11), x4146_x4647_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(12), tr51973)))
      Stage(stage(13), operands=List(CU.vecIn(stage(12), x4147_x4648_x4702_vector), CU.vecIn(stage(12), x4148_x4649_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(13), tr51974)))
      Stage(stage(14), operands=List(CU.temp(stage(13), tr51973), CU.temp(stage(13), tr51974)), op=FltAdd, results=List(CU.temp(stage(14), tr51975)))
      Stage(stage(15), operands=List(CU.temp(stage(14), tr51972), CU.temp(stage(14), tr51975)), op=FltAdd, results=List(CU.temp(stage(15), tr51976)))
      Stage(stage(16), operands=List(CU.temp(stage(15), tr51969), CU.temp(stage(15), tr51976)), op=FltAdd, results=List(CU.temp(stage(16), tr51977)))
      Stage(stage(17), operands=List(CU.temp(stage(16), tr51977), CU.vecIn(stage(16), x4045_x4650_x4702_vector)), op=FltAdd, results=List(CU.temp(stage(17), tr51978)))
      Stage(stage(18), operands=List(CU.temp(stage(17), tr51903), CU.temp(stage(17), tr51977), CU.temp(stage(17), tr51978)), op=Mux, results=List(CU.vecOut(stage(18), x4045_x4701_vector), CU.temp(stage(18), tr51979)))
    }
    val x4716 = Pipeline(name="x4716",parent=x4717) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51980 = CU.temp
      val tr51982 = CU.temp
      val tr51983 = CU.temp
      val tr51984 = CU.temp
      val x4044 = CounterChain.copy("x4717", "x4044")
      val ctr41 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4706 = CounterChain(name = "x4706", ctr41)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4044(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr51980)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x4039_x4708_x4716_vector), Const("1i")), op=FltMul, results=List(CU.temp(stage(2), tr51982)))
      Stage(stage(3), operands=List(CU.vecIn(stage(2), x4045_x4707_x4716_vector), CU.temp(stage(2), tr51982)), op=FltAdd, results=List(CU.temp(stage(3), tr51983)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr51980), CU.vecIn(stage(3), x4045_x4707_x4716_vector), CU.temp(stage(3), tr51983)), op=Mux, results=List(CU.vecOut(stage(4), x4039_x4715_vector), CU.temp(stage(4), tr51984)))
    }
    val x4741 = Sequential(name="x4741",parent=x4742) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4736 = Sequential(name="x4736",parent=x4741) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x4727 = UnitPipeline(name="x4727",parent=x4736) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51985 = CU.temp
      val tr51986 = CU.temp
      val tr51987 = CU.temp
      val x4723 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x4723_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr51985)), op=Bypass, results=List(CU.scalarOut(stage(1), x4719_b4890_x4725_b4895_scalar)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51986)), op=Bypass, results=List(CU.scalarOut(stage(2), x4719_b4891_x4725_b4896_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr51987)), op=Bypass, results=List(CU.scalarOut(stage(3), x4719_b4892_x4725_b4897_scalar)))
      Stage(stage(4), operands=List(Const("192i")), op=Bypass, results=List(CU.scalarOut(stage(4), x4720_x4726_scalar)))
    }
    val x4735 = Pipeline(name="x4735",parent=x4736) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr51989 = CU.temp
      val tr51990 = CU.temp
      val ctr42 = (Const("0i").out, Const("192i").out, Const("1i").out) // Counter
      val x4729 = CounterChain(name = "x4729", ctr42)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr51989)), op=Bypass, results=List(CU.vecOut(stage(1), x4721_b4893_x4734_b4898_vector)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr51990)), op=Bypass, results=List(CU.vecOut(stage(2), x4721_b4894_x4734_b4899_vector)))
    }
    val x4737 = Fringe(name="x4737",parent=x4741,dram=x4033_oc, mode=TileStore) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4719_b4892_x4737 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4719_b4892_x4725_b4897_scalar).rdPort(None)
      val x4719_b4891_x4737 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4719_b4891_x4725_b4896_scalar).rdPort(None)
      val x4719_b4890_x4737 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x4719_b4890_x4725_b4895_scalar).rdPort(None)
      val x4721_b4893_x4737 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x4721_b4893_x4734_b4898_vector).rdPort(None)
      val x4721_b4894_x4737 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x4721_b4894_x4734_b4899_vector).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x4740 = Sequential(name="x4740",parent=x4741) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    
  }
}
