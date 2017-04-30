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
    val x4037_x4592_x4597_v = Vector("x4037_x4592_x4597")
    val x4976_argin = ArgIn("x4976")
    val x4210_x4220_data_v = Vector("x4210_x4220_data")
    val x4736_argin = ArgIn("x4736")
    val x4211_argin = ArgIn("x4211")
    val x4295_b5075_x4304_b5077_s = Scalar("x4295_b5075_x4304_b5077")
    val x4467_b5107_x4476_b5109_s = Scalar("x4467_b5107_x4476_b5109")
    val x4656_argin = ArgIn("x4656")
    val x4296_x4306_data_v = Vector("x4296_x4306_data")
    val x4051_x4715_x4719_v = Vector("x4051_x4715_x4719")
    val x4317_b5078_x4326_b5080_s = Scalar("x4317_b5078_x4326_b5080")
    val x4080_b5034_x4089_b5036_s = Scalar("x4080_b5034_x4089_b5036")
    val x4043_x4545_x4549_v = Vector("x4043_x4545_x4549")
    val x4102_b5039_x4111_b5041_s = Scalar("x4102_b5039_x4111_b5041")
    val x4233_argin = ArgIn("x4233")
    val x4274_b5070_x4283_b5072_s = Scalar("x4274_b5070_x4283_b5072")
    val x4467_b5106_x4476_b5108_s = Scalar("x4467_b5106_x4476_b5108")
    val x4425_x4435_data_v = Vector("x4425_x4435_data")
    val x4188_b5054_x4197_b5056_s = Scalar("x4188_b5054_x4197_b5056")
    val x4016_oc = OffChip("x4016")
    val x4057_x4955_x4959_v = Vector("x4057_x4955_x4959")
    val x4018_oc = OffChip("x4018")
    val x4045_x4569_x4573_v = Vector("x4045_x4569_x4573")
    val x4102_b5038_x4111_b5040_s = Scalar("x4102_b5038_x4111_b5040")
    val x4147_argin = ArgIn("x4147")
    val x4060_x4070_data_v = Vector("x4060_x4070_data")
    val x4012_argin = ArgIn("x4012")
    val x4056_x4584_v = Vector("x4056_x4584")
    val x4146_x4156_data_v = Vector("x4146_x4156_data")
    val x4166_b5050_x4175_b5052_s = Scalar("x4166_b5050_x4175_b5052")
    val x4040_x4509_x4513_v = Vector("x4040_x4509_x4513")
    val x4055_x4875_x4879_v = Vector("x4055_x4875_x4879")
    val x4816_argin = ArgIn("x4816")
    val x4338_b5083_x4347_b5085_s = Scalar("x4338_b5083_x4347_b5085")
    val x4616_argin = ArgIn("x4616")
    val x4049_x4635_x4639_v = Vector("x4049_x4635_x4639")
    val x4033_x4544_x4549_v = Vector("x4033_x4544_x4549")
    val x4773_b5154_x4788_b5156_s = Scalar("x4773_b5154_x4788_b5156")
    val x4053_x4795_x4799_v = Vector("x4053_x4795_x4799")
    val x4653_b5136_x4668_b5138_s = Scalar("x4653_b5136_x4668_b5138")
    val x4813_b5161_x4828_b5163_s = Scalar("x4813_b5161_x4828_b5163")
    val x4933_b5179_x4948_b5181_s = Scalar("x4933_b5179_x4948_b5181")
    val x4933_b5178_x4948_b5180_s = Scalar("x4933_b5178_x4948_b5180")
    val x4039_x4497_x4501_v = Vector("x4039_x4497_x4501")
    val x4733_b5149_x4748_b5151_s = Scalar("x4733_b5149_x4748_b5151")
    val x4405_argin = ArgIn("x4405")
    val x4773_b5155_x4788_b5157_s = Scalar("x4773_b5155_x4788_b5157")
    val x4382_x4392_data_v = Vector("x4382_x4392_data")
    val x4853_b5167_x4868_b5169_s = Scalar("x4853_b5167_x4868_b5169")
    val x4055_x4572_v = Vector("x4055_x4572")
    val x4231_b5063_x4240_b5065_s = Scalar("x4231_b5063_x4240_b5065")
    val x4446_b5102_x4455_b5104_s = Scalar("x4446_b5102_x4455_b5104")
    val x4021_oc = OffChip("x4021")
    val x4167_x4177_data_v = Vector("x4167_x4177_data")
    val x4613_b5131_x4628_b5133_s = Scalar("x4613_b5131_x4628_b5133")
    val x4168_argin = ArgIn("x4168")
    val x4339_x4349_data_v = Vector("x4339_x4349_data")
    val x4123_b5042_x4132_b5044_s = Scalar("x4123_b5042_x4132_b5044")
    val x4319_argin = ArgIn("x4319")
    val x4276_argin = ArgIn("x4276")
    val x4048_x4605_x4609_v = Vector("x4048_x4605_x4609")
    val x4297_argin = ArgIn("x4297")
    val x4059_b5031_x4068_b5033_s = Scalar("x4059_b5031_x4068_b5033")
    val x4469_argin = ArgIn("x4469")
    val x4254_argin = ArgIn("x4254")
    val x4424_b5098_x4433_b5100_s = Scalar("x4424_b5098_x4433_b5100")
    val x4049_x4500_v = Vector("x4049_x4500")
    val x4034_x4556_x4561_v = Vector("x4034_x4556_x4561")
    val x4613_b5130_x4628_b5132_s = Scalar("x4613_b5130_x4628_b5132")
    val x4041_x4521_x4525_v = Vector("x4041_x4521_x4525")
    val x4052_x4755_x4759_v = Vector("x4052_x4755_x4759")
    val x4318_x4328_data_v = Vector("x4318_x4328_data")
    val x4231_b5062_x4240_b5064_s = Scalar("x4231_b5062_x4240_b5064")
    val x4693_b5142_x4708_b5144_s = Scalar("x4693_b5142_x4708_b5144")
    val x4447_x4457_data_v = Vector("x4447_x4457_data")
    val x4448_argin = ArgIn("x4448")
    val x4058_x4995_x4999_v = Vector("x4058_x4995_x4999")
    val x4733_b5148_x4748_b5150_s = Scalar("x4733_b5148_x4748_b5150")
    val x4340_argin = ArgIn("x4340")
    val x4124_x4134_data_v = Vector("x4124_x4134_data")
    val x4232_x4242_data_v = Vector("x4232_x4242_data")
    val x4054_x4560_v = Vector("x4054_x4560")
    val x4145_b5046_x4154_b5048_s = Scalar("x4145_b5046_x4154_b5048")
    val x4973_b5185_x4988_b5187_s = Scalar("x4973_b5185_x4988_b5187")
    val x4468_x4478_data_v = Vector("x4468_x4478_data")
    val x4189_x4199_data_v = Vector("x4189_x4199_data")
    val x4776_argin = ArgIn("x4776")
    val x4295_b5074_x4304_b5076_s = Scalar("x4295_b5074_x4304_b5076")
    val x4317_b5079_x4326_b5081_s = Scalar("x4317_b5079_x4326_b5081")
    val x4404_x4414_data_v = Vector("x4404_x4414_data")
    val x4403_b5094_x4412_b5096_s = Scalar("x4403_b5094_x4412_b5096")
    val x4362_argin = ArgIn("x4362")
    val x4030_x4508_x4513_v = Vector("x4030_x4508_x4513")
    val x4188_b5055_x4197_b5057_s = Scalar("x4188_b5055_x4197_b5057")
    val x4029_x4496_x4501_v = Vector("x4029_x4496_x4501")
    val x4082_argin = ArgIn("x4082")
    val x4403_b5095_x4412_b5097_s = Scalar("x4403_b5095_x4412_b5097")
    val x4042_x4533_x4537_v = Vector("x4042_x4533_x4537")
    val x4446_b5103_x4455_b5105_s = Scalar("x4446_b5103_x4455_b5105")
    val x4653_b5137_x4668_b5139_s = Scalar("x4653_b5137_x4668_b5139")
    val x4031_x4520_x4525_v = Vector("x4031_x4520_x4525")
    val x4080_b5035_x4089_b5037_s = Scalar("x4080_b5035_x4089_b5037")
    val x4361_x4371_data_v = Vector("x4361_x4371_data")
    val x4383_argin = ArgIn("x4383")
    val x4252_b5067_x4261_b5069_s = Scalar("x4252_b5067_x4261_b5069")
    val x4123_b5043_x4132_b5045_s = Scalar("x4123_b5043_x4132_b5045")
    val x4011_argin = ArgIn("x4011")
    val x4053_x4548_v = Vector("x4053_x4548")
    val x4145_b5047_x4154_b5049_s = Scalar("x4145_b5047_x4154_b5049")
    val x4081_x4091_data_v = Vector("x4081_x4091_data")
    val x4057_x4596_v = Vector("x4057_x4596")
    val x4853_b5166_x4868_b5168_s = Scalar("x4853_b5166_x4868_b5168")
    val x4896_argin = ArgIn("x4896")
    val x4054_x4835_x4839_v = Vector("x4054_x4835_x4839")
    val x4381_b5090_x4390_b5092_s = Scalar("x4381_b5090_x4390_b5092")
    val x4046_x4581_x4585_v = Vector("x4046_x4581_x4585")
    val x4190_argin = ArgIn("x4190")
    val x4209_b5059_x4218_b5061_s = Scalar("x4209_b5059_x4218_b5061")
    val x4166_b5051_x4175_b5053_s = Scalar("x4166_b5051_x4175_b5053")
    val x4103_x4113_data_v = Vector("x4103_x4113_data")
    val x4973_b5184_x4988_b5186_s = Scalar("x4973_b5184_x4988_b5186")
    val x4381_b5091_x4390_b5093_s = Scalar("x4381_b5091_x4390_b5093")
    val x4360_b5087_x4369_b5089_s = Scalar("x4360_b5087_x4369_b5089")
    val x4050_x4512_v = Vector("x4050_x4512")
    val x4125_argin = ArgIn("x4125")
    val x4936_argin = ArgIn("x4936")
    val x4056_x4915_x4919_v = Vector("x4056_x4915_x4919")
    val x4209_b5058_x4218_b5060_s = Scalar("x4209_b5058_x4218_b5060")
    val x4104_argin = ArgIn("x4104")
    val x4032_x4532_x4537_v = Vector("x4032_x4532_x4537")
    val x4424_b5099_x4433_b5101_s = Scalar("x4424_b5099_x4433_b5101")
    val x4038_x4604_x4609_v = Vector("x4038_x4604_x4609")
    val x4058_x4608_v = Vector("x4058_x4608")
    val x4696_argin = ArgIn("x4696")
    val x4252_b5066_x4261_b5068_s = Scalar("x4252_b5066_x4261_b5068")
    val x4338_b5082_x4347_b5084_s = Scalar("x4338_b5082_x4347_b5084")
    val x4893_b5173_x4908_b5175_s = Scalar("x4893_b5173_x4908_b5175")
    val x4856_argin = ArgIn("x4856")
    val x4061_argin = ArgIn("x4061")
    val x4059_b5030_x4068_b5032_s = Scalar("x4059_b5030_x4068_b5032")
    val x4035_x4568_x4573_v = Vector("x4035_x4568_x4573")
    val x4050_x4675_x4679_v = Vector("x4050_x4675_x4679")
    val x4275_x4285_data_v = Vector("x4275_x4285_data")
    val x4253_x4263_data_v = Vector("x4253_x4263_data")
    val x4044_x4557_x4561_v = Vector("x4044_x4557_x4561")
    val x4274_b5071_x4283_b5073_s = Scalar("x4274_b5071_x4283_b5073")
    val x4893_b5172_x4908_b5174_s = Scalar("x4893_b5172_x4908_b5174")
    val x4693_b5143_x4708_b5145_s = Scalar("x4693_b5143_x4708_b5145")
    val x4052_x4536_v = Vector("x4052_x4536")
    val x4426_argin = ArgIn("x4426")
    val x4047_x4593_x4597_v = Vector("x4047_x4593_x4597")
    val x4051_x4524_v = Vector("x4051_x4524")
    val x4036_x4580_x4585_v = Vector("x4036_x4580_x4585")
    val x4360_b5086_x4369_b5088_s = Scalar("x4360_b5086_x4369_b5088")
    val x4813_b5160_x4828_b5162_s = Scalar("x4813_b5160_x4828_b5162")
    val x5013 = Sequential(name="x5013",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5013_unit = CounterChain(name = "x5013_unit", ctr1)
    }
    val x5012 = MetaPipeline(name="x5012",parent=x5013) { implicit CU => 
      val x4012_x4024 =  ScalarBuffer().wtPort(x4012_argin)
      val x4011_x4026 =  ScalarBuffer().wtPort(x4011_argin)
      val ctr2 = Counter(min=Const(0), max=x4011_x4026.load, step=Const(48), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x4012_x4024.load, step=Const(48), par=10) // Counter
      val x4028 = CounterChain(name = "x4028", ctr2, ctr3)
    }
    val x4029_dsp0 = MemoryPipeline(name="x4029_dsp0",parent="x5012") { implicit CU => 
      val x4077_x4077 =  VectorFIFO(size=1).wtPort(x4060_x4070_data_v)
      val x4072 = CounterChain.copy("x4078", "x4072")
      val x4492 = CounterChain.copy("x4501_0", "x4492")
      val x4029_x4496 =  SRAM(size=48,banking = Strided(1)).wtPort(x4077_x4077.readPort).rdPort(x4029_x4496_x4501_v).rdAddr(x4492(0)).wtAddr(x4072(0))
      var stage: List[Stage] = Nil
    }
    val x4030_dsp0 = MemoryPipeline(name="x4030_dsp0",parent="x5012") { implicit CU => 
      val x4120_x4120 =  VectorFIFO(size=1).wtPort(x4103_x4113_data_v)
      val x4115 = CounterChain.copy("x4121", "x4115")
      val x4504 = CounterChain.copy("x4513_0", "x4504")
      val x4030_x4508 =  SRAM(size=48,banking = Strided(1)).wtPort(x4120_x4120.readPort).rdPort(x4030_x4508_x4513_v).rdAddr(x4504(0)).wtAddr(x4115(0))
      var stage: List[Stage] = Nil
    }
    val x4031_dsp0 = MemoryPipeline(name="x4031_dsp0",parent="x5012") { implicit CU => 
      val x4163_x4163 =  VectorFIFO(size=1).wtPort(x4146_x4156_data_v)
      val x4158 = CounterChain.copy("x4164", "x4158")
      val x4516 = CounterChain.copy("x4525_0", "x4516")
      val x4031_x4520 =  SRAM(size=48,banking = Strided(1)).wtPort(x4163_x4163.readPort).rdPort(x4031_x4520_x4525_v).rdAddr(x4516(0)).wtAddr(x4158(0))
      var stage: List[Stage] = Nil
    }
    val x4032_dsp0 = MemoryPipeline(name="x4032_dsp0",parent="x5012") { implicit CU => 
      val x4206_x4206 =  VectorFIFO(size=1).wtPort(x4189_x4199_data_v)
      val x4201 = CounterChain.copy("x4207", "x4201")
      val x4528 = CounterChain.copy("x4537_0", "x4528")
      val x4032_x4532 =  SRAM(size=48,banking = Strided(1)).wtPort(x4206_x4206.readPort).rdPort(x4032_x4532_x4537_v).rdAddr(x4528(0)).wtAddr(x4201(0))
      var stage: List[Stage] = Nil
    }
    val x4033_dsp0 = MemoryPipeline(name="x4033_dsp0",parent="x5012") { implicit CU => 
      val x4249_x4249 =  VectorFIFO(size=1).wtPort(x4232_x4242_data_v)
      val x4244 = CounterChain.copy("x4250", "x4244")
      val x4540 = CounterChain.copy("x4549_0", "x4540")
      val x4033_x4544 =  SRAM(size=48,banking = Strided(1)).wtPort(x4249_x4249.readPort).rdPort(x4033_x4544_x4549_v).rdAddr(x4540(0)).wtAddr(x4244(0))
      var stage: List[Stage] = Nil
    }
    val x4034_dsp0 = MemoryPipeline(name="x4034_dsp0",parent="x5012") { implicit CU => 
      val x4292_x4292 =  VectorFIFO(size=1).wtPort(x4275_x4285_data_v)
      val x4287 = CounterChain.copy("x4293", "x4287")
      val x4552 = CounterChain.copy("x4561_0", "x4552")
      val x4034_x4556 =  SRAM(size=48,banking = Strided(1)).wtPort(x4292_x4292.readPort).rdPort(x4034_x4556_x4561_v).rdAddr(x4552(0)).wtAddr(x4287(0))
      var stage: List[Stage] = Nil
    }
    val x4035_dsp0 = MemoryPipeline(name="x4035_dsp0",parent="x5012") { implicit CU => 
      val x4335_x4335 =  VectorFIFO(size=1).wtPort(x4318_x4328_data_v)
      val x4330 = CounterChain.copy("x4336", "x4330")
      val x4564 = CounterChain.copy("x4573_0", "x4564")
      val x4035_x4568 =  SRAM(size=48,banking = Strided(1)).wtPort(x4335_x4335.readPort).rdPort(x4035_x4568_x4573_v).rdAddr(x4564(0)).wtAddr(x4330(0))
      var stage: List[Stage] = Nil
    }
    val x4036_dsp0 = MemoryPipeline(name="x4036_dsp0",parent="x5012") { implicit CU => 
      val x4378_x4378 =  VectorFIFO(size=1).wtPort(x4361_x4371_data_v)
      val x4373 = CounterChain.copy("x4379", "x4373")
      val x4576 = CounterChain.copy("x4585_0", "x4576")
      val x4036_x4580 =  SRAM(size=48,banking = Strided(1)).wtPort(x4378_x4378.readPort).rdPort(x4036_x4580_x4585_v).rdAddr(x4576(0)).wtAddr(x4373(0))
      var stage: List[Stage] = Nil
    }
    val x4037_dsp0 = MemoryPipeline(name="x4037_dsp0",parent="x5012") { implicit CU => 
      val x4421_x4421 =  VectorFIFO(size=1).wtPort(x4404_x4414_data_v)
      val x4416 = CounterChain.copy("x4422", "x4416")
      val x4588 = CounterChain.copy("x4597_0", "x4588")
      val x4037_x4592 =  SRAM(size=48,banking = Strided(1)).wtPort(x4421_x4421.readPort).rdPort(x4037_x4592_x4597_v).rdAddr(x4588(0)).wtAddr(x4416(0))
      var stage: List[Stage] = Nil
    }
    val x4038_dsp0 = MemoryPipeline(name="x4038_dsp0",parent="x5012") { implicit CU => 
      val x4464_x4464 =  VectorFIFO(size=1).wtPort(x4447_x4457_data_v)
      val x4459 = CounterChain.copy("x4465", "x4459")
      val x4600 = CounterChain.copy("x4609_0", "x4600")
      val x4038_x4604 =  SRAM(size=48,banking = Strided(1)).wtPort(x4464_x4464.readPort).rdPort(x4038_x4604_x4609_v).rdAddr(x4600(0)).wtAddr(x4459(0))
      var stage: List[Stage] = Nil
    }
    val x4039_dsp0 = MemoryPipeline(name="x4039_dsp0",parent="x5012") { implicit CU => 
      val x4098_x4098 =  VectorFIFO(size=1).wtPort(x4081_x4091_data_v)
      val x4093 = CounterChain.copy("x4099", "x4093")
      val x4492 = CounterChain.copy("x4501_0", "x4492")
      val x4039_x4497 =  SRAM(size=48,banking = Strided(1)).wtPort(x4098_x4098.readPort).rdPort(x4039_x4497_x4501_v).rdAddr(x4492(1)).wtAddr(x4093(0))
      var stage: List[Stage] = Nil
    }
    val x4040_dsp0 = MemoryPipeline(name="x4040_dsp0",parent="x5012") { implicit CU => 
      val x4141_x4141 =  VectorFIFO(size=1).wtPort(x4124_x4134_data_v)
      val x4136 = CounterChain.copy("x4142", "x4136")
      val x4504 = CounterChain.copy("x4513_0", "x4504")
      val x4040_x4509 =  SRAM(size=48,banking = Strided(1)).wtPort(x4141_x4141.readPort).rdPort(x4040_x4509_x4513_v).rdAddr(x4504(1)).wtAddr(x4136(0))
      var stage: List[Stage] = Nil
    }
    val x4041_dsp0 = MemoryPipeline(name="x4041_dsp0",parent="x5012") { implicit CU => 
      val x4184_x4184 =  VectorFIFO(size=1).wtPort(x4167_x4177_data_v)
      val x4179 = CounterChain.copy("x4185", "x4179")
      val x4516 = CounterChain.copy("x4525_0", "x4516")
      val x4041_x4521 =  SRAM(size=48,banking = Strided(1)).wtPort(x4184_x4184.readPort).rdPort(x4041_x4521_x4525_v).rdAddr(x4516(1)).wtAddr(x4179(0))
      var stage: List[Stage] = Nil
    }
    val x4042_dsp0 = MemoryPipeline(name="x4042_dsp0",parent="x5012") { implicit CU => 
      val x4227_x4227 =  VectorFIFO(size=1).wtPort(x4210_x4220_data_v)
      val x4222 = CounterChain.copy("x4228", "x4222")
      val x4528 = CounterChain.copy("x4537_0", "x4528")
      val x4042_x4533 =  SRAM(size=48,banking = Strided(1)).wtPort(x4227_x4227.readPort).rdPort(x4042_x4533_x4537_v).rdAddr(x4528(1)).wtAddr(x4222(0))
      var stage: List[Stage] = Nil
    }
    val x4043_dsp0 = MemoryPipeline(name="x4043_dsp0",parent="x5012") { implicit CU => 
      val x4270_x4270 =  VectorFIFO(size=1).wtPort(x4253_x4263_data_v)
      val x4265 = CounterChain.copy("x4271", "x4265")
      val x4540 = CounterChain.copy("x4549_0", "x4540")
      val x4043_x4545 =  SRAM(size=48,banking = Strided(1)).wtPort(x4270_x4270.readPort).rdPort(x4043_x4545_x4549_v).rdAddr(x4540(1)).wtAddr(x4265(0))
      var stage: List[Stage] = Nil
    }
    val x4044_dsp0 = MemoryPipeline(name="x4044_dsp0",parent="x5012") { implicit CU => 
      val x4313_x4313 =  VectorFIFO(size=1).wtPort(x4296_x4306_data_v)
      val x4308 = CounterChain.copy("x4314", "x4308")
      val x4552 = CounterChain.copy("x4561_0", "x4552")
      val x4044_x4557 =  SRAM(size=48,banking = Strided(1)).wtPort(x4313_x4313.readPort).rdPort(x4044_x4557_x4561_v).rdAddr(x4552(1)).wtAddr(x4308(0))
      var stage: List[Stage] = Nil
    }
    val x4045_dsp0 = MemoryPipeline(name="x4045_dsp0",parent="x5012") { implicit CU => 
      val x4356_x4356 =  VectorFIFO(size=1).wtPort(x4339_x4349_data_v)
      val x4351 = CounterChain.copy("x4357", "x4351")
      val x4564 = CounterChain.copy("x4573_0", "x4564")
      val x4045_x4569 =  SRAM(size=48,banking = Strided(1)).wtPort(x4356_x4356.readPort).rdPort(x4045_x4569_x4573_v).rdAddr(x4564(1)).wtAddr(x4351(0))
      var stage: List[Stage] = Nil
    }
    val x4046_dsp0 = MemoryPipeline(name="x4046_dsp0",parent="x5012") { implicit CU => 
      val x4399_x4399 =  VectorFIFO(size=1).wtPort(x4382_x4392_data_v)
      val x4394 = CounterChain.copy("x4400", "x4394")
      val x4576 = CounterChain.copy("x4585_0", "x4576")
      val x4046_x4581 =  SRAM(size=48,banking = Strided(1)).wtPort(x4399_x4399.readPort).rdPort(x4046_x4581_x4585_v).rdAddr(x4576(1)).wtAddr(x4394(0))
      var stage: List[Stage] = Nil
    }
    val x4047_dsp0 = MemoryPipeline(name="x4047_dsp0",parent="x5012") { implicit CU => 
      val x4442_x4442 =  VectorFIFO(size=1).wtPort(x4425_x4435_data_v)
      val x4437 = CounterChain.copy("x4443", "x4437")
      val x4588 = CounterChain.copy("x4597_0", "x4588")
      val x4047_x4593 =  SRAM(size=48,banking = Strided(1)).wtPort(x4442_x4442.readPort).rdPort(x4047_x4593_x4597_v).rdAddr(x4588(1)).wtAddr(x4437(0))
      var stage: List[Stage] = Nil
    }
    val x4048_dsp0 = MemoryPipeline(name="x4048_dsp0",parent="x5012") { implicit CU => 
      val x4485_x4485 =  VectorFIFO(size=1).wtPort(x4468_x4478_data_v)
      val x4480 = CounterChain.copy("x4486", "x4480")
      val x4600 = CounterChain.copy("x4609_0", "x4600")
      val x4048_x4605 =  SRAM(size=48,banking = Strided(1)).wtPort(x4485_x4485.readPort).rdPort(x4048_x4605_x4609_v).rdAddr(x4600(1)).wtAddr(x4480(0))
      var stage: List[Stage] = Nil
    }
    val x4049_dsp0 = MemoryPipeline(name="x4049_dsp0",parent="x5012") { implicit CU => 
      val b5110 = CU.temp
      val b5134 = CU.temp
      val x4500_x4500 =  VectorFIFO(size=1).wtPort(x4049_x4500_v)
      val x4492 = CounterChain.copy("x4501_0", "x4492")
      val x4612 = CounterChain.copy("x4650", "x4612")
      val x4631 = CounterChain.copy("x4639", "x4631")
      val x4049_x4635 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4500_x4500.readPort).rdPort(x4049_x4635_x4639_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4492(0)), Const(48)), op=FixMul, results=List(b5110))
      WAStage(operands=List(b5110, CU.ctr(x4492(1))), op=FixAdd, results=List(x4049_x4635.writeAddr))
      RAStage(operands=List(CU.ctr(x4612(0)), Const(48)), op=FixMul, results=List(b5134))
      RAStage(operands=List(b5134, CU.ctr(x4631(0))), op=FixAdd, results=List(x4049_x4635.readAddr))
    }
    val x4050_dsp0 = MemoryPipeline(name="x4050_dsp0",parent="x5012") { implicit CU => 
      val b5112 = CU.temp
      val b5140 = CU.temp
      val x4512_x4512 =  VectorFIFO(size=1).wtPort(x4050_x4512_v)
      val x4504 = CounterChain.copy("x4513_0", "x4504")
      val x4652 = CounterChain.copy("x4690", "x4652")
      val x4671 = CounterChain.copy("x4679", "x4671")
      val x4050_x4675 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4512_x4512.readPort).rdPort(x4050_x4675_x4679_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4504(0)), Const(48)), op=FixMul, results=List(b5112))
      WAStage(operands=List(b5112, CU.ctr(x4504(1))), op=FixAdd, results=List(x4050_x4675.writeAddr))
      RAStage(operands=List(CU.ctr(x4652(0)), Const(48)), op=FixMul, results=List(b5140))
      RAStage(operands=List(b5140, CU.ctr(x4671(0))), op=FixAdd, results=List(x4050_x4675.readAddr))
    }
    val x4051_dsp0 = MemoryPipeline(name="x4051_dsp0",parent="x5012") { implicit CU => 
      val b5114 = CU.temp
      val b5146 = CU.temp
      val x4524_x4524 =  VectorFIFO(size=1).wtPort(x4051_x4524_v)
      val x4516 = CounterChain.copy("x4525_0", "x4516")
      val x4692 = CounterChain.copy("x4730", "x4692")
      val x4711 = CounterChain.copy("x4719", "x4711")
      val x4051_x4715 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4524_x4524.readPort).rdPort(x4051_x4715_x4719_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4516(0)), Const(48)), op=FixMul, results=List(b5114))
      WAStage(operands=List(b5114, CU.ctr(x4516(1))), op=FixAdd, results=List(x4051_x4715.writeAddr))
      RAStage(operands=List(CU.ctr(x4692(0)), Const(48)), op=FixMul, results=List(b5146))
      RAStage(operands=List(b5146, CU.ctr(x4711(0))), op=FixAdd, results=List(x4051_x4715.readAddr))
    }
    val x4052_dsp0 = MemoryPipeline(name="x4052_dsp0",parent="x5012") { implicit CU => 
      val b5152 = CU.temp
      val b5116 = CU.temp
      val x4536_x4536 =  VectorFIFO(size=1).wtPort(x4052_x4536_v)
      val x4528 = CounterChain.copy("x4537_0", "x4528")
      val x4732 = CounterChain.copy("x4770", "x4732")
      val x4751 = CounterChain.copy("x4759", "x4751")
      val x4052_x4755 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4536_x4536.readPort).rdPort(x4052_x4755_x4759_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4528(0)), Const(48)), op=FixMul, results=List(b5116))
      WAStage(operands=List(b5116, CU.ctr(x4528(1))), op=FixAdd, results=List(x4052_x4755.writeAddr))
      RAStage(operands=List(CU.ctr(x4732(0)), Const(48)), op=FixMul, results=List(b5152))
      RAStage(operands=List(b5152, CU.ctr(x4751(0))), op=FixAdd, results=List(x4052_x4755.readAddr))
    }
    val x4053_dsp0 = MemoryPipeline(name="x4053_dsp0",parent="x5012") { implicit CU => 
      val b5158 = CU.temp
      val b5118 = CU.temp
      val x4548_x4548 =  VectorFIFO(size=1).wtPort(x4053_x4548_v)
      val x4540 = CounterChain.copy("x4549_0", "x4540")
      val x4772 = CounterChain.copy("x4810", "x4772")
      val x4791 = CounterChain.copy("x4799", "x4791")
      val x4053_x4795 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4548_x4548.readPort).rdPort(x4053_x4795_x4799_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4540(0)), Const(48)), op=FixMul, results=List(b5118))
      WAStage(operands=List(b5118, CU.ctr(x4540(1))), op=FixAdd, results=List(x4053_x4795.writeAddr))
      RAStage(operands=List(CU.ctr(x4772(0)), Const(48)), op=FixMul, results=List(b5158))
      RAStage(operands=List(b5158, CU.ctr(x4791(0))), op=FixAdd, results=List(x4053_x4795.readAddr))
    }
    val x4054_dsp0 = MemoryPipeline(name="x4054_dsp0",parent="x5012") { implicit CU => 
      val b5120 = CU.temp
      val b5164 = CU.temp
      val x4560_x4560 =  VectorFIFO(size=1).wtPort(x4054_x4560_v)
      val x4552 = CounterChain.copy("x4561_0", "x4552")
      val x4812 = CounterChain.copy("x4850", "x4812")
      val x4831 = CounterChain.copy("x4839", "x4831")
      val x4054_x4835 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4560_x4560.readPort).rdPort(x4054_x4835_x4839_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4552(0)), Const(48)), op=FixMul, results=List(b5120))
      WAStage(operands=List(b5120, CU.ctr(x4552(1))), op=FixAdd, results=List(x4054_x4835.writeAddr))
      RAStage(operands=List(CU.ctr(x4812(0)), Const(48)), op=FixMul, results=List(b5164))
      RAStage(operands=List(b5164, CU.ctr(x4831(0))), op=FixAdd, results=List(x4054_x4835.readAddr))
    }
    val x4055_dsp0 = MemoryPipeline(name="x4055_dsp0",parent="x5012") { implicit CU => 
      val b5170 = CU.temp
      val b5122 = CU.temp
      val x4572_x4572 =  VectorFIFO(size=1).wtPort(x4055_x4572_v)
      val x4564 = CounterChain.copy("x4573_0", "x4564")
      val x4852 = CounterChain.copy("x4890", "x4852")
      val x4871 = CounterChain.copy("x4879", "x4871")
      val x4055_x4875 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4572_x4572.readPort).rdPort(x4055_x4875_x4879_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4564(0)), Const(48)), op=FixMul, results=List(b5122))
      WAStage(operands=List(b5122, CU.ctr(x4564(1))), op=FixAdd, results=List(x4055_x4875.writeAddr))
      RAStage(operands=List(CU.ctr(x4852(0)), Const(48)), op=FixMul, results=List(b5170))
      RAStage(operands=List(b5170, CU.ctr(x4871(0))), op=FixAdd, results=List(x4055_x4875.readAddr))
    }
    val x4056_dsp0 = MemoryPipeline(name="x4056_dsp0",parent="x5012") { implicit CU => 
      val b5124 = CU.temp
      val b5176 = CU.temp
      val x4584_x4584 =  VectorFIFO(size=1).wtPort(x4056_x4584_v)
      val x4576 = CounterChain.copy("x4585_0", "x4576")
      val x4892 = CounterChain.copy("x4930", "x4892")
      val x4911 = CounterChain.copy("x4919", "x4911")
      val x4056_x4915 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4584_x4584.readPort).rdPort(x4056_x4915_x4919_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4576(0)), Const(48)), op=FixMul, results=List(b5124))
      WAStage(operands=List(b5124, CU.ctr(x4576(1))), op=FixAdd, results=List(x4056_x4915.writeAddr))
      RAStage(operands=List(CU.ctr(x4892(0)), Const(48)), op=FixMul, results=List(b5176))
      RAStage(operands=List(b5176, CU.ctr(x4911(0))), op=FixAdd, results=List(x4056_x4915.readAddr))
    }
    val x4057_dsp0 = MemoryPipeline(name="x4057_dsp0",parent="x5012") { implicit CU => 
      val b5182 = CU.temp
      val b5126 = CU.temp
      val x4596_x4596 =  VectorFIFO(size=1).wtPort(x4057_x4596_v)
      val x4588 = CounterChain.copy("x4597_0", "x4588")
      val x4932 = CounterChain.copy("x4970", "x4932")
      val x4951 = CounterChain.copy("x4959", "x4951")
      val x4057_x4955 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4596_x4596.readPort).rdPort(x4057_x4955_x4959_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4588(0)), Const(48)), op=FixMul, results=List(b5126))
      WAStage(operands=List(b5126, CU.ctr(x4588(1))), op=FixAdd, results=List(x4057_x4955.writeAddr))
      RAStage(operands=List(CU.ctr(x4932(0)), Const(48)), op=FixMul, results=List(b5182))
      RAStage(operands=List(b5182, CU.ctr(x4951(0))), op=FixAdd, results=List(x4057_x4955.readAddr))
    }
    val x4058_dsp0 = MemoryPipeline(name="x4058_dsp0",parent="x5012") { implicit CU => 
      val b5188 = CU.temp
      val b5128 = CU.temp
      val x4608_x4608 =  VectorFIFO(size=1).wtPort(x4058_x4608_v)
      val x4600 = CounterChain.copy("x4609_0", "x4600")
      val x4972 = CounterChain.copy("x5010", "x4972")
      val x4991 = CounterChain.copy("x4999", "x4991")
      val x4058_x4995 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4608_x4608.readPort).rdPort(x4058_x4995_x4999_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4600(0)), Const(48)), op=FixMul, results=List(b5128))
      WAStage(operands=List(b5128, CU.ctr(x4600(1))), op=FixAdd, results=List(x4058_x4995.writeAddr))
      RAStage(operands=List(CU.ctr(x4972(0)), Const(48)), op=FixMul, results=List(b5188))
      RAStage(operands=List(b5188, CU.ctr(x4991(0))), op=FixAdd, results=List(x4058_x4995.readAddr))
    }
    val x4079 = StreamController(name="x4079",parent=x5012) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4079_unit = CounterChain(name = "x4079_unit", ctr4)
    }
    val x4069_0 = Pipeline(name="x4069_0",parent=x4079) { implicit CU => 
      val x4062 = CU.temp
      val x4061 =  ScalarBuffer().wtPort(x4061_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4069_unit = CounterChain(name = "x4069_unit", ctr5)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4062))
      Stage(operands=List(x4062, CU.load(x4061)), op=FixAdd, results=List(CU.scalarOut(x4059_b5030_x4068_b5032_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4059_b5031_x4068_b5033_s)))
    }
    val x4070 = MemoryController(name="x4070",parent=x4079,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4059_b5031_x4070 =  ScalarFIFO(name="size",size=1).wtPort(x4059_b5031_x4068_b5033_s)
      val x4059_b5030_x4070 =  ScalarFIFO(name="offset",size=1).wtPort(x4059_b5030_x4068_b5032_s)
      CU.newVout("data", x4060_x4070_data_v)
    }
    val x4078 = Pipeline(name="x4078",parent=x4079) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4072 = CounterChain(name = "x4072", ctr6)
      var stage: List[Stage] = Nil
    }
    val x4100 = StreamController(name="x4100",parent=x5012) { implicit CU => 
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4100_unit = CounterChain(name = "x4100_unit", ctr7)
    }
    val x4090_0 = Pipeline(name="x4090_0",parent=x4100) { implicit CU => 
      val x4083 = CU.temp
      val x4082 =  ScalarBuffer().wtPort(x4082_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4090_unit = CounterChain(name = "x4090_unit", ctr8)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4083))
      Stage(operands=List(x4083, CU.load(x4082)), op=FixAdd, results=List(CU.scalarOut(x4080_b5034_x4089_b5036_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4080_b5035_x4089_b5037_s)))
    }
    val x4091 = MemoryController(name="x4091",parent=x4100,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4080_b5035_x4091 =  ScalarFIFO(name="size",size=1).wtPort(x4080_b5035_x4089_b5037_s)
      val x4080_b5034_x4091 =  ScalarFIFO(name="offset",size=1).wtPort(x4080_b5034_x4089_b5036_s)
      CU.newVout("data", x4081_x4091_data_v)
    }
    val x4099 = Pipeline(name="x4099",parent=x4100) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4093 = CounterChain(name = "x4093", ctr9)
      var stage: List[Stage] = Nil
    }
    val x4122 = StreamController(name="x4122",parent=x5012) { implicit CU => 
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4122_unit = CounterChain(name = "x4122_unit", ctr10)
    }
    val x4112_0 = Pipeline(name="x4112_0",parent=x4122) { implicit CU => 
      val x4105 = CU.temp
      val x4104 =  ScalarBuffer().wtPort(x4104_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4112_unit = CounterChain(name = "x4112_unit", ctr11)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4105))
      Stage(operands=List(x4105, CU.load(x4104)), op=FixAdd, results=List(CU.scalarOut(x4102_b5038_x4111_b5040_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4102_b5039_x4111_b5041_s)))
    }
    val x4113 = MemoryController(name="x4113",parent=x4122,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4102_b5038_x4113 =  ScalarFIFO(name="offset",size=1).wtPort(x4102_b5038_x4111_b5040_s)
      val x4102_b5039_x4113 =  ScalarFIFO(name="size",size=1).wtPort(x4102_b5039_x4111_b5041_s)
      CU.newVout("data", x4103_x4113_data_v)
    }
    val x4121 = Pipeline(name="x4121",parent=x4122) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4115 = CounterChain(name = "x4115", ctr12)
      var stage: List[Stage] = Nil
    }
    val x4143 = StreamController(name="x4143",parent=x5012) { implicit CU => 
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4143_unit = CounterChain(name = "x4143_unit", ctr13)
    }
    val x4133_0 = Pipeline(name="x4133_0",parent=x4143) { implicit CU => 
      val x4126 = CU.temp
      val x4125 =  ScalarBuffer().wtPort(x4125_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4133_unit = CounterChain(name = "x4133_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4126))
      Stage(operands=List(x4126, CU.load(x4125)), op=FixAdd, results=List(CU.scalarOut(x4123_b5042_x4132_b5044_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4123_b5043_x4132_b5045_s)))
    }
    val x4134 = MemoryController(name="x4134",parent=x4143,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4123_b5043_x4134 =  ScalarFIFO(name="size",size=1).wtPort(x4123_b5043_x4132_b5045_s)
      val x4123_b5042_x4134 =  ScalarFIFO(name="offset",size=1).wtPort(x4123_b5042_x4132_b5044_s)
      CU.newVout("data", x4124_x4134_data_v)
    }
    val x4142 = Pipeline(name="x4142",parent=x4143) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4136 = CounterChain(name = "x4136", ctr15)
      var stage: List[Stage] = Nil
    }
    val x4165 = StreamController(name="x4165",parent=x5012) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4165_unit = CounterChain(name = "x4165_unit", ctr16)
    }
    val x4155_0 = Pipeline(name="x4155_0",parent=x4165) { implicit CU => 
      val x4148 = CU.temp
      val x4147 =  ScalarBuffer().wtPort(x4147_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4155_unit = CounterChain(name = "x4155_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4148))
      Stage(operands=List(x4148, CU.load(x4147)), op=FixAdd, results=List(CU.scalarOut(x4145_b5046_x4154_b5048_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4145_b5047_x4154_b5049_s)))
    }
    val x4156 = MemoryController(name="x4156",parent=x4165,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4145_b5047_x4156 =  ScalarFIFO(name="size",size=1).wtPort(x4145_b5047_x4154_b5049_s)
      val x4145_b5046_x4156 =  ScalarFIFO(name="offset",size=1).wtPort(x4145_b5046_x4154_b5048_s)
      CU.newVout("data", x4146_x4156_data_v)
    }
    val x4164 = Pipeline(name="x4164",parent=x4165) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4158 = CounterChain(name = "x4158", ctr18)
      var stage: List[Stage] = Nil
    }
    val x4186 = StreamController(name="x4186",parent=x5012) { implicit CU => 
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4186_unit = CounterChain(name = "x4186_unit", ctr19)
    }
    val x4176_0 = Pipeline(name="x4176_0",parent=x4186) { implicit CU => 
      val x4169 = CU.temp
      val x4168 =  ScalarBuffer().wtPort(x4168_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4176_unit = CounterChain(name = "x4176_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4169))
      Stage(operands=List(x4169, CU.load(x4168)), op=FixAdd, results=List(CU.scalarOut(x4166_b5050_x4175_b5052_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4166_b5051_x4175_b5053_s)))
    }
    val x4177 = MemoryController(name="x4177",parent=x4186,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4166_b5050_x4177 =  ScalarFIFO(name="offset",size=1).wtPort(x4166_b5050_x4175_b5052_s)
      val x4166_b5051_x4177 =  ScalarFIFO(name="size",size=1).wtPort(x4166_b5051_x4175_b5053_s)
      CU.newVout("data", x4167_x4177_data_v)
    }
    val x4185 = Pipeline(name="x4185",parent=x4186) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4179 = CounterChain(name = "x4179", ctr21)
      var stage: List[Stage] = Nil
    }
    val x4208 = StreamController(name="x4208",parent=x5012) { implicit CU => 
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4208_unit = CounterChain(name = "x4208_unit", ctr22)
    }
    val x4198_0 = Pipeline(name="x4198_0",parent=x4208) { implicit CU => 
      val x4191 = CU.temp
      val x4190 =  ScalarBuffer().wtPort(x4190_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4198_unit = CounterChain(name = "x4198_unit", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4191))
      Stage(operands=List(x4191, CU.load(x4190)), op=FixAdd, results=List(CU.scalarOut(x4188_b5054_x4197_b5056_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4188_b5055_x4197_b5057_s)))
    }
    val x4199 = MemoryController(name="x4199",parent=x4208,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4188_b5055_x4199 =  ScalarFIFO(name="size",size=1).wtPort(x4188_b5055_x4197_b5057_s)
      val x4188_b5054_x4199 =  ScalarFIFO(name="offset",size=1).wtPort(x4188_b5054_x4197_b5056_s)
      CU.newVout("data", x4189_x4199_data_v)
    }
    val x4207 = Pipeline(name="x4207",parent=x4208) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4201 = CounterChain(name = "x4201", ctr24)
      var stage: List[Stage] = Nil
    }
    val x4229 = StreamController(name="x4229",parent=x5012) { implicit CU => 
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4229_unit = CounterChain(name = "x4229_unit", ctr25)
    }
    val x4219_0 = Pipeline(name="x4219_0",parent=x4229) { implicit CU => 
      val x4212 = CU.temp
      val x4211 =  ScalarBuffer().wtPort(x4211_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4219_unit = CounterChain(name = "x4219_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4212))
      Stage(operands=List(x4212, CU.load(x4211)), op=FixAdd, results=List(CU.scalarOut(x4209_b5058_x4218_b5060_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4209_b5059_x4218_b5061_s)))
    }
    val x4220 = MemoryController(name="x4220",parent=x4229,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4209_b5059_x4220 =  ScalarFIFO(name="size",size=1).wtPort(x4209_b5059_x4218_b5061_s)
      val x4209_b5058_x4220 =  ScalarFIFO(name="offset",size=1).wtPort(x4209_b5058_x4218_b5060_s)
      CU.newVout("data", x4210_x4220_data_v)
    }
    val x4228 = Pipeline(name="x4228",parent=x4229) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4222 = CounterChain(name = "x4222", ctr27)
      var stage: List[Stage] = Nil
    }
    val x4251 = StreamController(name="x4251",parent=x5012) { implicit CU => 
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4251_unit = CounterChain(name = "x4251_unit", ctr28)
    }
    val x4241_0 = Pipeline(name="x4241_0",parent=x4251) { implicit CU => 
      val x4234 = CU.temp
      val x4233 =  ScalarBuffer().wtPort(x4233_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4241_unit = CounterChain(name = "x4241_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4234))
      Stage(operands=List(x4234, CU.load(x4233)), op=FixAdd, results=List(CU.scalarOut(x4231_b5062_x4240_b5064_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4231_b5063_x4240_b5065_s)))
    }
    val x4242 = MemoryController(name="x4242",parent=x4251,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4231_b5062_x4242 =  ScalarFIFO(name="offset",size=1).wtPort(x4231_b5062_x4240_b5064_s)
      val x4231_b5063_x4242 =  ScalarFIFO(name="size",size=1).wtPort(x4231_b5063_x4240_b5065_s)
      CU.newVout("data", x4232_x4242_data_v)
    }
    val x4250 = Pipeline(name="x4250",parent=x4251) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4244 = CounterChain(name = "x4244", ctr30)
      var stage: List[Stage] = Nil
    }
    val x4272 = StreamController(name="x4272",parent=x5012) { implicit CU => 
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4272_unit = CounterChain(name = "x4272_unit", ctr31)
    }
    val x4262_0 = Pipeline(name="x4262_0",parent=x4272) { implicit CU => 
      val x4255 = CU.temp
      val x4254 =  ScalarBuffer().wtPort(x4254_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr32 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4262_unit = CounterChain(name = "x4262_unit", ctr32)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4255))
      Stage(operands=List(x4255, CU.load(x4254)), op=FixAdd, results=List(CU.scalarOut(x4252_b5066_x4261_b5068_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4252_b5067_x4261_b5069_s)))
    }
    val x4263 = MemoryController(name="x4263",parent=x4272,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4252_b5067_x4263 =  ScalarFIFO(name="size",size=1).wtPort(x4252_b5067_x4261_b5069_s)
      val x4252_b5066_x4263 =  ScalarFIFO(name="offset",size=1).wtPort(x4252_b5066_x4261_b5068_s)
      CU.newVout("data", x4253_x4263_data_v)
    }
    val x4271 = Pipeline(name="x4271",parent=x4272) { implicit CU => 
      val ctr33 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4265 = CounterChain(name = "x4265", ctr33)
      var stage: List[Stage] = Nil
    }
    val x4294 = StreamController(name="x4294",parent=x5012) { implicit CU => 
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4294_unit = CounterChain(name = "x4294_unit", ctr34)
    }
    val x4284_0 = Pipeline(name="x4284_0",parent=x4294) { implicit CU => 
      val x4277 = CU.temp
      val x4276 =  ScalarBuffer().wtPort(x4276_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr35 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4284_unit = CounterChain(name = "x4284_unit", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4277))
      Stage(operands=List(x4277, CU.load(x4276)), op=FixAdd, results=List(CU.scalarOut(x4274_b5070_x4283_b5072_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4274_b5071_x4283_b5073_s)))
    }
    val x4285 = MemoryController(name="x4285",parent=x4294,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4274_b5071_x4285 =  ScalarFIFO(name="size",size=1).wtPort(x4274_b5071_x4283_b5073_s)
      val x4274_b5070_x4285 =  ScalarFIFO(name="offset",size=1).wtPort(x4274_b5070_x4283_b5072_s)
      CU.newVout("data", x4275_x4285_data_v)
    }
    val x4293 = Pipeline(name="x4293",parent=x4294) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4287 = CounterChain(name = "x4287", ctr36)
      var stage: List[Stage] = Nil
    }
    val x4315 = StreamController(name="x4315",parent=x5012) { implicit CU => 
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4315_unit = CounterChain(name = "x4315_unit", ctr37)
    }
    val x4305_0 = Pipeline(name="x4305_0",parent=x4315) { implicit CU => 
      val x4298 = CU.temp
      val x4297 =  ScalarBuffer().wtPort(x4297_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4305_unit = CounterChain(name = "x4305_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4298))
      Stage(operands=List(x4298, CU.load(x4297)), op=FixAdd, results=List(CU.scalarOut(x4295_b5074_x4304_b5076_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4295_b5075_x4304_b5077_s)))
    }
    val x4306 = MemoryController(name="x4306",parent=x4315,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4295_b5074_x4306 =  ScalarFIFO(name="offset",size=1).wtPort(x4295_b5074_x4304_b5076_s)
      val x4295_b5075_x4306 =  ScalarFIFO(name="size",size=1).wtPort(x4295_b5075_x4304_b5077_s)
      CU.newVout("data", x4296_x4306_data_v)
    }
    val x4314 = Pipeline(name="x4314",parent=x4315) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4308 = CounterChain(name = "x4308", ctr39)
      var stage: List[Stage] = Nil
    }
    val x4337 = StreamController(name="x4337",parent=x5012) { implicit CU => 
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4337_unit = CounterChain(name = "x4337_unit", ctr40)
    }
    val x4327_0 = Pipeline(name="x4327_0",parent=x4337) { implicit CU => 
      val x4320 = CU.temp
      val x4319 =  ScalarBuffer().wtPort(x4319_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4327_unit = CounterChain(name = "x4327_unit", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4320))
      Stage(operands=List(x4320, CU.load(x4319)), op=FixAdd, results=List(CU.scalarOut(x4317_b5078_x4326_b5080_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4317_b5079_x4326_b5081_s)))
    }
    val x4328 = MemoryController(name="x4328",parent=x4337,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4317_b5079_x4328 =  ScalarFIFO(name="size",size=1).wtPort(x4317_b5079_x4326_b5081_s)
      val x4317_b5078_x4328 =  ScalarFIFO(name="offset",size=1).wtPort(x4317_b5078_x4326_b5080_s)
      CU.newVout("data", x4318_x4328_data_v)
    }
    val x4336 = Pipeline(name="x4336",parent=x4337) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4330 = CounterChain(name = "x4330", ctr42)
      var stage: List[Stage] = Nil
    }
    val x4358 = StreamController(name="x4358",parent=x5012) { implicit CU => 
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4358_unit = CounterChain(name = "x4358_unit", ctr43)
    }
    val x4348_0 = Pipeline(name="x4348_0",parent=x4358) { implicit CU => 
      val x4341 = CU.temp
      val x4340 =  ScalarBuffer().wtPort(x4340_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4348_unit = CounterChain(name = "x4348_unit", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4341))
      Stage(operands=List(x4341, CU.load(x4340)), op=FixAdd, results=List(CU.scalarOut(x4338_b5082_x4347_b5084_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4338_b5083_x4347_b5085_s)))
    }
    val x4349 = MemoryController(name="x4349",parent=x4358,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4338_b5083_x4349 =  ScalarFIFO(name="size",size=1).wtPort(x4338_b5083_x4347_b5085_s)
      val x4338_b5082_x4349 =  ScalarFIFO(name="offset",size=1).wtPort(x4338_b5082_x4347_b5084_s)
      CU.newVout("data", x4339_x4349_data_v)
    }
    val x4357 = Pipeline(name="x4357",parent=x4358) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4351 = CounterChain(name = "x4351", ctr45)
      var stage: List[Stage] = Nil
    }
    val x4380 = StreamController(name="x4380",parent=x5012) { implicit CU => 
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4380_unit = CounterChain(name = "x4380_unit", ctr46)
    }
    val x4370_0 = Pipeline(name="x4370_0",parent=x4380) { implicit CU => 
      val x4363 = CU.temp
      val x4362 =  ScalarBuffer().wtPort(x4362_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr47 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4370_unit = CounterChain(name = "x4370_unit", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4363))
      Stage(operands=List(x4363, CU.load(x4362)), op=FixAdd, results=List(CU.scalarOut(x4360_b5086_x4369_b5088_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4360_b5087_x4369_b5089_s)))
    }
    val x4371 = MemoryController(name="x4371",parent=x4380,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4360_b5086_x4371 =  ScalarFIFO(name="offset",size=1).wtPort(x4360_b5086_x4369_b5088_s)
      val x4360_b5087_x4371 =  ScalarFIFO(name="size",size=1).wtPort(x4360_b5087_x4369_b5089_s)
      CU.newVout("data", x4361_x4371_data_v)
    }
    val x4379 = Pipeline(name="x4379",parent=x4380) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4373 = CounterChain(name = "x4373", ctr48)
      var stage: List[Stage] = Nil
    }
    val x4401 = StreamController(name="x4401",parent=x5012) { implicit CU => 
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4401_unit = CounterChain(name = "x4401_unit", ctr49)
    }
    val x4391_0 = Pipeline(name="x4391_0",parent=x4401) { implicit CU => 
      val x4384 = CU.temp
      val x4383 =  ScalarBuffer().wtPort(x4383_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4391_unit = CounterChain(name = "x4391_unit", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4384))
      Stage(operands=List(x4384, CU.load(x4383)), op=FixAdd, results=List(CU.scalarOut(x4381_b5090_x4390_b5092_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4381_b5091_x4390_b5093_s)))
    }
    val x4392 = MemoryController(name="x4392",parent=x4401,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4381_b5091_x4392 =  ScalarFIFO(name="size",size=1).wtPort(x4381_b5091_x4390_b5093_s)
      val x4381_b5090_x4392 =  ScalarFIFO(name="offset",size=1).wtPort(x4381_b5090_x4390_b5092_s)
      CU.newVout("data", x4382_x4392_data_v)
    }
    val x4400 = Pipeline(name="x4400",parent=x4401) { implicit CU => 
      val ctr51 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4394 = CounterChain(name = "x4394", ctr51)
      var stage: List[Stage] = Nil
    }
    val x4423 = StreamController(name="x4423",parent=x5012) { implicit CU => 
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4423_unit = CounterChain(name = "x4423_unit", ctr52)
    }
    val x4413_0 = Pipeline(name="x4413_0",parent=x4423) { implicit CU => 
      val x4406 = CU.temp
      val x4405 =  ScalarBuffer().wtPort(x4405_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr53 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4413_unit = CounterChain(name = "x4413_unit", ctr53)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4406))
      Stage(operands=List(x4406, CU.load(x4405)), op=FixAdd, results=List(CU.scalarOut(x4403_b5094_x4412_b5096_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4403_b5095_x4412_b5097_s)))
    }
    val x4414 = MemoryController(name="x4414",parent=x4423,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4403_b5095_x4414 =  ScalarFIFO(name="size",size=1).wtPort(x4403_b5095_x4412_b5097_s)
      val x4403_b5094_x4414 =  ScalarFIFO(name="offset",size=1).wtPort(x4403_b5094_x4412_b5096_s)
      CU.newVout("data", x4404_x4414_data_v)
    }
    val x4422 = Pipeline(name="x4422",parent=x4423) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4416 = CounterChain(name = "x4416", ctr54)
      var stage: List[Stage] = Nil
    }
    val x4444 = StreamController(name="x4444",parent=x5012) { implicit CU => 
      val ctr55 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4444_unit = CounterChain(name = "x4444_unit", ctr55)
    }
    val x4434_0 = Pipeline(name="x4434_0",parent=x4444) { implicit CU => 
      val x4427 = CU.temp
      val x4426 =  ScalarBuffer().wtPort(x4426_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr56 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4434_unit = CounterChain(name = "x4434_unit", ctr56)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4427))
      Stage(operands=List(x4427, CU.load(x4426)), op=FixAdd, results=List(CU.scalarOut(x4424_b5098_x4433_b5100_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4424_b5099_x4433_b5101_s)))
    }
    val x4435 = MemoryController(name="x4435",parent=x4444,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4424_b5098_x4435 =  ScalarFIFO(name="offset",size=1).wtPort(x4424_b5098_x4433_b5100_s)
      val x4424_b5099_x4435 =  ScalarFIFO(name="size",size=1).wtPort(x4424_b5099_x4433_b5101_s)
      CU.newVout("data", x4425_x4435_data_v)
    }
    val x4443 = Pipeline(name="x4443",parent=x4444) { implicit CU => 
      val ctr57 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4437 = CounterChain(name = "x4437", ctr57)
      var stage: List[Stage] = Nil
    }
    val x4466 = StreamController(name="x4466",parent=x5012) { implicit CU => 
      val ctr58 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4466_unit = CounterChain(name = "x4466_unit", ctr58)
    }
    val x4456_0 = Pipeline(name="x4456_0",parent=x4466) { implicit CU => 
      val x4449 = CU.temp
      val x4448 =  ScalarBuffer().wtPort(x4448_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr59 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4456_unit = CounterChain(name = "x4456_unit", ctr59)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), Const(4)), op=FixMul, results=List(x4449))
      Stage(operands=List(x4449, CU.load(x4448)), op=FixAdd, results=List(CU.scalarOut(x4446_b5102_x4455_b5104_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4446_b5103_x4455_b5105_s)))
    }
    val x4457 = MemoryController(name="x4457",parent=x4466,offchip=x4016_oc, mctpe=TileLoad) { implicit CU => 
      val x4446_b5103_x4457 =  ScalarFIFO(name="size",size=1).wtPort(x4446_b5103_x4455_b5105_s)
      val x4446_b5102_x4457 =  ScalarFIFO(name="offset",size=1).wtPort(x4446_b5102_x4455_b5104_s)
      CU.newVout("data", x4447_x4457_data_v)
    }
    val x4465 = Pipeline(name="x4465",parent=x4466) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4459 = CounterChain(name = "x4459", ctr60)
      var stage: List[Stage] = Nil
    }
    val x4487 = StreamController(name="x4487",parent=x5012) { implicit CU => 
      val ctr61 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4487_unit = CounterChain(name = "x4487_unit", ctr61)
    }
    val x4477_0 = Pipeline(name="x4477_0",parent=x4487) { implicit CU => 
      val x4470 = CU.temp
      val x4469 =  ScalarBuffer().wtPort(x4469_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val ctr62 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4477_unit = CounterChain(name = "x4477_unit", ctr62)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(1)), Const(4)), op=FixMul, results=List(x4470))
      Stage(operands=List(x4470, CU.load(x4469)), op=FixAdd, results=List(CU.scalarOut(x4467_b5106_x4476_b5108_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4467_b5107_x4476_b5109_s)))
    }
    val x4478 = MemoryController(name="x4478",parent=x4487,offchip=x4018_oc, mctpe=TileLoad) { implicit CU => 
      val x4467_b5107_x4478 =  ScalarFIFO(name="size",size=1).wtPort(x4467_b5107_x4476_b5109_s)
      val x4467_b5106_x4478 =  ScalarFIFO(name="offset",size=1).wtPort(x4467_b5106_x4476_b5108_s)
      CU.newVout("data", x4468_x4478_data_v)
    }
    val x4486 = Pipeline(name="x4486",parent=x4487) { implicit CU => 
      val ctr63 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4480 = CounterChain(name = "x4480", ctr63)
      var stage: List[Stage] = Nil
    }
    val x4501_0 = Pipeline(name="x4501_0",parent=x5012) { implicit CU => 
      val x4039_x4497 =  VectorFIFO(size=1).wtPort(x4039_x4497_x4501_v)
      val x4029_x4496 =  VectorFIFO(size=1).wtPort(x4029_x4496_x4501_v)
      val ctr64 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr65 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4492 = CounterChain(name = "x4492", ctr64, ctr65)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4029_x4496), CU.load(x4039_x4497)), op=FixMul, results=List(CU.vecOut(x4049_x4500_v)))
    }
    val x4513_0 = Pipeline(name="x4513_0",parent=x5012) { implicit CU => 
      val x4040_x4509 =  VectorFIFO(size=1).wtPort(x4040_x4509_x4513_v)
      val x4030_x4508 =  VectorFIFO(size=1).wtPort(x4030_x4508_x4513_v)
      val ctr66 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr67 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4504 = CounterChain(name = "x4504", ctr66, ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4030_x4508), CU.load(x4040_x4509)), op=FixMul, results=List(CU.vecOut(x4050_x4512_v)))
    }
    val x4525_0 = Pipeline(name="x4525_0",parent=x5012) { implicit CU => 
      val x4031_x4520 =  VectorFIFO(size=1).wtPort(x4031_x4520_x4525_v)
      val x4041_x4521 =  VectorFIFO(size=1).wtPort(x4041_x4521_x4525_v)
      val ctr68 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr69 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4516 = CounterChain(name = "x4516", ctr68, ctr69)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4031_x4520), CU.load(x4041_x4521)), op=FixMul, results=List(CU.vecOut(x4051_x4524_v)))
    }
    val x4537_0 = Pipeline(name="x4537_0",parent=x5012) { implicit CU => 
      val x4042_x4533 =  VectorFIFO(size=1).wtPort(x4042_x4533_x4537_v)
      val x4032_x4532 =  VectorFIFO(size=1).wtPort(x4032_x4532_x4537_v)
      val ctr70 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr71 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4528 = CounterChain(name = "x4528", ctr70, ctr71)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4032_x4532), CU.load(x4042_x4533)), op=FixMul, results=List(CU.vecOut(x4052_x4536_v)))
    }
    val x4549_0 = Pipeline(name="x4549_0",parent=x5012) { implicit CU => 
      val x4033_x4544 =  VectorFIFO(size=1).wtPort(x4033_x4544_x4549_v)
      val x4043_x4545 =  VectorFIFO(size=1).wtPort(x4043_x4545_x4549_v)
      val ctr72 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr73 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4540 = CounterChain(name = "x4540", ctr72, ctr73)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4033_x4544), CU.load(x4043_x4545)), op=FixMul, results=List(CU.vecOut(x4053_x4548_v)))
    }
    val x4561_0 = Pipeline(name="x4561_0",parent=x5012) { implicit CU => 
      val x4044_x4557 =  VectorFIFO(size=1).wtPort(x4044_x4557_x4561_v)
      val x4034_x4556 =  VectorFIFO(size=1).wtPort(x4034_x4556_x4561_v)
      val ctr74 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr75 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4552 = CounterChain(name = "x4552", ctr74, ctr75)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4034_x4556), CU.load(x4044_x4557)), op=FixMul, results=List(CU.vecOut(x4054_x4560_v)))
    }
    val x4573_0 = Pipeline(name="x4573_0",parent=x5012) { implicit CU => 
      val x4045_x4569 =  VectorFIFO(size=1).wtPort(x4045_x4569_x4573_v)
      val x4035_x4568 =  VectorFIFO(size=1).wtPort(x4035_x4568_x4573_v)
      val ctr76 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr77 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4564 = CounterChain(name = "x4564", ctr76, ctr77)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4035_x4568), CU.load(x4045_x4569)), op=FixMul, results=List(CU.vecOut(x4055_x4572_v)))
    }
    val x4585_0 = Pipeline(name="x4585_0",parent=x5012) { implicit CU => 
      val x4036_x4580 =  VectorFIFO(size=1).wtPort(x4036_x4580_x4585_v)
      val x4046_x4581 =  VectorFIFO(size=1).wtPort(x4046_x4581_x4585_v)
      val ctr78 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr79 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4576 = CounterChain(name = "x4576", ctr78, ctr79)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4036_x4580), CU.load(x4046_x4581)), op=FixMul, results=List(CU.vecOut(x4056_x4584_v)))
    }
    val x4597_0 = Pipeline(name="x4597_0",parent=x5012) { implicit CU => 
      val x4047_x4593 =  VectorFIFO(size=1).wtPort(x4047_x4593_x4597_v)
      val x4037_x4592 =  VectorFIFO(size=1).wtPort(x4037_x4592_x4597_v)
      val ctr80 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr81 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4588 = CounterChain(name = "x4588", ctr80, ctr81)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4037_x4592), CU.load(x4047_x4593)), op=FixMul, results=List(CU.vecOut(x4057_x4596_v)))
    }
    val x4609_0 = Pipeline(name="x4609_0",parent=x5012) { implicit CU => 
      val x4048_x4605 =  VectorFIFO(size=1).wtPort(x4048_x4605_x4609_v)
      val x4038_x4604 =  VectorFIFO(size=1).wtPort(x4038_x4604_x4609_v)
      val ctr82 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr83 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4600 = CounterChain(name = "x4600", ctr82, ctr83)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4038_x4604), CU.load(x4048_x4605)), op=FixMul, results=List(CU.vecOut(x4058_x4608_v)))
    }
    val x4650 = StreamController(name="x4650",parent=x5012) { implicit CU => 
      val ctr84 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4612 = CounterChain(name = "x4612", ctr84)
    }
    val x4640 = Sequential(name="x4640",parent=x4650) { implicit CU => 
      val ctr85 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4640_unit = CounterChain(name = "x4640_unit", ctr85)
    }
    val x4629_0 = Pipeline(name="x4629_0",parent=x4640) { implicit CU => 
      val x4620 = CU.temp
      val x4619 = CU.temp
      val x4621 = CU.temp
      val x4618 = CU.temp
      val x4012_x4617 =  ScalarBuffer().wtPort(x4012_argin)
      val x4616 =  ScalarBuffer().wtPort(x4616_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4612 = CounterChain.copy("x4650", "x4612")
      val ctr86 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4629_unit = CounterChain(name = "x4629_unit", ctr86)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4612(0))), op=FixAdd, results=List(x4618))
      Stage(operands=List(x4618, CU.load(x4012_x4617)), op=FixMul, results=List(x4619))
      Stage(operands=List(x4619, CU.ctr(x4028(1))), op=FixAdd, results=List(x4620))
      Stage(operands=List(x4620, Const(4)), op=FixMul, results=List(x4621))
      Stage(operands=List(x4621, CU.load(x4616)), op=FixAdd, results=List(CU.scalarOut(x4613_b5130_x4628_b5132_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4613_b5131_x4628_b5133_s)))
    }
    val x4639 = Pipeline(name="x4639",parent=x4640) { implicit CU => 
      val ctr87 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4631 = CounterChain(name = "x4631", ctr87)
      var stage: List[Stage] = Nil
    }
    val x4641 = MemoryController(name="x4641",parent=x4650,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4614_x4641 =  VectorFIFO(name="data",size=1).wtPort(x4049_x4635_x4639_v)
      val x4613_b5131_x4641 =  ScalarFIFO(name="size",size=1).wtPort(x4613_b5131_x4628_b5133_s)
      val x4613_b5130_x4641 =  ScalarFIFO(name="offset",size=1).wtPort(x4613_b5130_x4628_b5132_s)
    }
    val x4690 = StreamController(name="x4690",parent=x5012) { implicit CU => 
      val ctr90 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4652 = CounterChain(name = "x4652", ctr90)
    }
    val x4680 = Sequential(name="x4680",parent=x4690) { implicit CU => 
      val ctr91 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4680_unit = CounterChain(name = "x4680_unit", ctr91)
    }
    val x4669_0 = Pipeline(name="x4669_0",parent=x4680) { implicit CU => 
      val x4658 = CU.temp
      val x4659 = CU.temp
      val x4660 = CU.temp
      val x4661 = CU.temp
      val x4012_x4657 =  ScalarBuffer().wtPort(x4012_argin)
      val x4656 =  ScalarBuffer().wtPort(x4656_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4652 = CounterChain.copy("x4690", "x4652")
      val ctr92 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4669_unit = CounterChain(name = "x4669_unit", ctr92)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4652(0))), op=FixAdd, results=List(x4658))
      Stage(operands=List(x4658, CU.load(x4012_x4657)), op=FixMul, results=List(x4659))
      Stage(operands=List(x4659, CU.ctr(x4028(1))), op=FixAdd, results=List(x4660))
      Stage(operands=List(x4660, Const(4)), op=FixMul, results=List(x4661))
      Stage(operands=List(x4661, CU.load(x4656)), op=FixAdd, results=List(CU.scalarOut(x4653_b5136_x4668_b5138_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4653_b5137_x4668_b5139_s)))
    }
    val x4679 = Pipeline(name="x4679",parent=x4680) { implicit CU => 
      val ctr93 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4671 = CounterChain(name = "x4671", ctr93)
      var stage: List[Stage] = Nil
    }
    val x4681 = MemoryController(name="x4681",parent=x4690,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4653_b5137_x4681 =  ScalarFIFO(name="size",size=1).wtPort(x4653_b5137_x4668_b5139_s)
      val x4653_b5136_x4681 =  ScalarFIFO(name="offset",size=1).wtPort(x4653_b5136_x4668_b5138_s)
      val x4654_x4681 =  VectorFIFO(name="data",size=1).wtPort(x4050_x4675_x4679_v)
    }
    val x4730 = StreamController(name="x4730",parent=x5012) { implicit CU => 
      val ctr96 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4692 = CounterChain(name = "x4692", ctr96)
    }
    val x4720 = Sequential(name="x4720",parent=x4730) { implicit CU => 
      val ctr97 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4720_unit = CounterChain(name = "x4720_unit", ctr97)
    }
    val x4709_0 = Pipeline(name="x4709_0",parent=x4720) { implicit CU => 
      val x4699 = CU.temp
      val x4700 = CU.temp
      val x4698 = CU.temp
      val x4701 = CU.temp
      val x4012_x4697 =  ScalarBuffer().wtPort(x4012_argin)
      val x4696 =  ScalarBuffer().wtPort(x4696_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4692 = CounterChain.copy("x4730", "x4692")
      val ctr98 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4709_unit = CounterChain(name = "x4709_unit", ctr98)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4692(0))), op=FixAdd, results=List(x4698))
      Stage(operands=List(x4698, CU.load(x4012_x4697)), op=FixMul, results=List(x4699))
      Stage(operands=List(x4699, CU.ctr(x4028(1))), op=FixAdd, results=List(x4700))
      Stage(operands=List(x4700, Const(4)), op=FixMul, results=List(x4701))
      Stage(operands=List(x4701, CU.load(x4696)), op=FixAdd, results=List(CU.scalarOut(x4693_b5142_x4708_b5144_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4693_b5143_x4708_b5145_s)))
    }
    val x4719 = Pipeline(name="x4719",parent=x4720) { implicit CU => 
      val ctr99 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4711 = CounterChain(name = "x4711", ctr99)
      var stage: List[Stage] = Nil
    }
    val x4721 = MemoryController(name="x4721",parent=x4730,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4693_b5143_x4721 =  ScalarFIFO(name="size",size=1).wtPort(x4693_b5143_x4708_b5145_s)
      val x4694_x4721 =  VectorFIFO(name="data",size=1).wtPort(x4051_x4715_x4719_v)
      val x4693_b5142_x4721 =  ScalarFIFO(name="offset",size=1).wtPort(x4693_b5142_x4708_b5144_s)
    }
    val x4770 = StreamController(name="x4770",parent=x5012) { implicit CU => 
      val ctr102 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4732 = CounterChain(name = "x4732", ctr102)
    }
    val x4760 = Sequential(name="x4760",parent=x4770) { implicit CU => 
      val ctr103 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4760_unit = CounterChain(name = "x4760_unit", ctr103)
    }
    val x4749_0 = Pipeline(name="x4749_0",parent=x4760) { implicit CU => 
      val x4739 = CU.temp
      val x4740 = CU.temp
      val x4738 = CU.temp
      val x4741 = CU.temp
      val x4736 =  ScalarBuffer().wtPort(x4736_argin)
      val x4012_x4737 =  ScalarBuffer().wtPort(x4012_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4732 = CounterChain.copy("x4770", "x4732")
      val ctr104 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4749_unit = CounterChain(name = "x4749_unit", ctr104)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4732(0))), op=FixAdd, results=List(x4738))
      Stage(operands=List(x4738, CU.load(x4012_x4737)), op=FixMul, results=List(x4739))
      Stage(operands=List(x4739, CU.ctr(x4028(1))), op=FixAdd, results=List(x4740))
      Stage(operands=List(x4740, Const(4)), op=FixMul, results=List(x4741))
      Stage(operands=List(x4741, CU.load(x4736)), op=FixAdd, results=List(CU.scalarOut(x4733_b5148_x4748_b5150_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4733_b5149_x4748_b5151_s)))
    }
    val x4759 = Pipeline(name="x4759",parent=x4760) { implicit CU => 
      val ctr105 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4751 = CounterChain(name = "x4751", ctr105)
      var stage: List[Stage] = Nil
    }
    val x4761 = MemoryController(name="x4761",parent=x4770,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4733_b5149_x4761 =  ScalarFIFO(name="size",size=1).wtPort(x4733_b5149_x4748_b5151_s)
      val x4733_b5148_x4761 =  ScalarFIFO(name="offset",size=1).wtPort(x4733_b5148_x4748_b5150_s)
      val x4734_x4761 =  VectorFIFO(name="data",size=1).wtPort(x4052_x4755_x4759_v)
    }
    val x4810 = StreamController(name="x4810",parent=x5012) { implicit CU => 
      val ctr108 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4772 = CounterChain(name = "x4772", ctr108)
    }
    val x4800 = Sequential(name="x4800",parent=x4810) { implicit CU => 
      val ctr109 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4800_unit = CounterChain(name = "x4800_unit", ctr109)
    }
    val x4789_0 = Pipeline(name="x4789_0",parent=x4800) { implicit CU => 
      val x4781 = CU.temp
      val x4778 = CU.temp
      val x4780 = CU.temp
      val x4779 = CU.temp
      val x4012_x4777 =  ScalarBuffer().wtPort(x4012_argin)
      val x4776 =  ScalarBuffer().wtPort(x4776_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4772 = CounterChain.copy("x4810", "x4772")
      val ctr110 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4789_unit = CounterChain(name = "x4789_unit", ctr110)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4772(0))), op=FixAdd, results=List(x4778))
      Stage(operands=List(x4778, CU.load(x4012_x4777)), op=FixMul, results=List(x4779))
      Stage(operands=List(x4779, CU.ctr(x4028(1))), op=FixAdd, results=List(x4780))
      Stage(operands=List(x4780, Const(4)), op=FixMul, results=List(x4781))
      Stage(operands=List(x4781, CU.load(x4776)), op=FixAdd, results=List(CU.scalarOut(x4773_b5154_x4788_b5156_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4773_b5155_x4788_b5157_s)))
    }
    val x4799 = Pipeline(name="x4799",parent=x4800) { implicit CU => 
      val ctr111 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4791 = CounterChain(name = "x4791", ctr111)
      var stage: List[Stage] = Nil
    }
    val x4801 = MemoryController(name="x4801",parent=x4810,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4773_b5155_x4801 =  ScalarFIFO(name="size",size=1).wtPort(x4773_b5155_x4788_b5157_s)
      val x4773_b5154_x4801 =  ScalarFIFO(name="offset",size=1).wtPort(x4773_b5154_x4788_b5156_s)
      val x4774_x4801 =  VectorFIFO(name="data",size=1).wtPort(x4053_x4795_x4799_v)
    }
    val x4850 = StreamController(name="x4850",parent=x5012) { implicit CU => 
      val ctr114 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4812 = CounterChain(name = "x4812", ctr114)
    }
    val x4840 = Sequential(name="x4840",parent=x4850) { implicit CU => 
      val ctr115 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4840_unit = CounterChain(name = "x4840_unit", ctr115)
    }
    val x4829_0 = Pipeline(name="x4829_0",parent=x4840) { implicit CU => 
      val x4820 = CU.temp
      val x4819 = CU.temp
      val x4818 = CU.temp
      val x4821 = CU.temp
      val x4012_x4817 =  ScalarBuffer().wtPort(x4012_argin)
      val x4816 =  ScalarBuffer().wtPort(x4816_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4812 = CounterChain.copy("x4850", "x4812")
      val ctr116 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4829_unit = CounterChain(name = "x4829_unit", ctr116)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4812(0))), op=FixAdd, results=List(x4818))
      Stage(operands=List(x4818, CU.load(x4012_x4817)), op=FixMul, results=List(x4819))
      Stage(operands=List(x4819, CU.ctr(x4028(1))), op=FixAdd, results=List(x4820))
      Stage(operands=List(x4820, Const(4)), op=FixMul, results=List(x4821))
      Stage(operands=List(x4821, CU.load(x4816)), op=FixAdd, results=List(CU.scalarOut(x4813_b5160_x4828_b5162_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4813_b5161_x4828_b5163_s)))
    }
    val x4839 = Pipeline(name="x4839",parent=x4840) { implicit CU => 
      val ctr117 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4831 = CounterChain(name = "x4831", ctr117)
      var stage: List[Stage] = Nil
    }
    val x4841 = MemoryController(name="x4841",parent=x4850,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4814_x4841 =  VectorFIFO(name="data",size=1).wtPort(x4054_x4835_x4839_v)
      val x4813_b5161_x4841 =  ScalarFIFO(name="size",size=1).wtPort(x4813_b5161_x4828_b5163_s)
      val x4813_b5160_x4841 =  ScalarFIFO(name="offset",size=1).wtPort(x4813_b5160_x4828_b5162_s)
    }
    val x4890 = StreamController(name="x4890",parent=x5012) { implicit CU => 
      val ctr120 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4852 = CounterChain(name = "x4852", ctr120)
    }
    val x4880 = Sequential(name="x4880",parent=x4890) { implicit CU => 
      val ctr121 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4880_unit = CounterChain(name = "x4880_unit", ctr121)
    }
    val x4869_0 = Pipeline(name="x4869_0",parent=x4880) { implicit CU => 
      val x4858 = CU.temp
      val x4859 = CU.temp
      val x4861 = CU.temp
      val x4860 = CU.temp
      val x4856 =  ScalarBuffer().wtPort(x4856_argin)
      val x4012_x4857 =  ScalarBuffer().wtPort(x4012_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4852 = CounterChain.copy("x4890", "x4852")
      val ctr122 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4869_unit = CounterChain(name = "x4869_unit", ctr122)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4852(0))), op=FixAdd, results=List(x4858))
      Stage(operands=List(x4858, CU.load(x4012_x4857)), op=FixMul, results=List(x4859))
      Stage(operands=List(x4859, CU.ctr(x4028(1))), op=FixAdd, results=List(x4860))
      Stage(operands=List(x4860, Const(4)), op=FixMul, results=List(x4861))
      Stage(operands=List(x4861, CU.load(x4856)), op=FixAdd, results=List(CU.scalarOut(x4853_b5166_x4868_b5168_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4853_b5167_x4868_b5169_s)))
    }
    val x4879 = Pipeline(name="x4879",parent=x4880) { implicit CU => 
      val ctr123 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4871 = CounterChain(name = "x4871", ctr123)
      var stage: List[Stage] = Nil
    }
    val x4881 = MemoryController(name="x4881",parent=x4890,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4853_b5167_x4881 =  ScalarFIFO(name="size",size=1).wtPort(x4853_b5167_x4868_b5169_s)
      val x4853_b5166_x4881 =  ScalarFIFO(name="offset",size=1).wtPort(x4853_b5166_x4868_b5168_s)
      val x4854_x4881 =  VectorFIFO(name="data",size=1).wtPort(x4055_x4875_x4879_v)
    }
    val x4930 = StreamController(name="x4930",parent=x5012) { implicit CU => 
      val ctr126 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4892 = CounterChain(name = "x4892", ctr126)
    }
    val x4920 = Sequential(name="x4920",parent=x4930) { implicit CU => 
      val ctr127 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4920_unit = CounterChain(name = "x4920_unit", ctr127)
    }
    val x4909_0 = Pipeline(name="x4909_0",parent=x4920) { implicit CU => 
      val x4900 = CU.temp
      val x4898 = CU.temp
      val x4901 = CU.temp
      val x4899 = CU.temp
      val x4012_x4897 =  ScalarBuffer().wtPort(x4012_argin)
      val x4896 =  ScalarBuffer().wtPort(x4896_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4892 = CounterChain.copy("x4930", "x4892")
      val ctr128 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4909_unit = CounterChain(name = "x4909_unit", ctr128)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4892(0))), op=FixAdd, results=List(x4898))
      Stage(operands=List(x4898, CU.load(x4012_x4897)), op=FixMul, results=List(x4899))
      Stage(operands=List(x4899, CU.ctr(x4028(1))), op=FixAdd, results=List(x4900))
      Stage(operands=List(x4900, Const(4)), op=FixMul, results=List(x4901))
      Stage(operands=List(x4901, CU.load(x4896)), op=FixAdd, results=List(CU.scalarOut(x4893_b5172_x4908_b5174_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4893_b5173_x4908_b5175_s)))
    }
    val x4919 = Pipeline(name="x4919",parent=x4920) { implicit CU => 
      val ctr129 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4911 = CounterChain(name = "x4911", ctr129)
      var stage: List[Stage] = Nil
    }
    val x4921 = MemoryController(name="x4921",parent=x4930,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4893_b5173_x4921 =  ScalarFIFO(name="size",size=1).wtPort(x4893_b5173_x4908_b5175_s)
      val x4893_b5172_x4921 =  ScalarFIFO(name="offset",size=1).wtPort(x4893_b5172_x4908_b5174_s)
      val x4894_x4921 =  VectorFIFO(name="data",size=1).wtPort(x4056_x4915_x4919_v)
    }
    val x4970 = StreamController(name="x4970",parent=x5012) { implicit CU => 
      val ctr132 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4932 = CounterChain(name = "x4932", ctr132)
    }
    val x4960 = Sequential(name="x4960",parent=x4970) { implicit CU => 
      val ctr133 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4960_unit = CounterChain(name = "x4960_unit", ctr133)
    }
    val x4949_0 = Pipeline(name="x4949_0",parent=x4960) { implicit CU => 
      val x4939 = CU.temp
      val x4940 = CU.temp
      val x4938 = CU.temp
      val x4941 = CU.temp
      val x4936 =  ScalarBuffer().wtPort(x4936_argin)
      val x4012_x4937 =  ScalarBuffer().wtPort(x4012_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4932 = CounterChain.copy("x4970", "x4932")
      val ctr134 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4949_unit = CounterChain(name = "x4949_unit", ctr134)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4932(0))), op=FixAdd, results=List(x4938))
      Stage(operands=List(x4938, CU.load(x4012_x4937)), op=FixMul, results=List(x4939))
      Stage(operands=List(x4939, CU.ctr(x4028(1))), op=FixAdd, results=List(x4940))
      Stage(operands=List(x4940, Const(4)), op=FixMul, results=List(x4941))
      Stage(operands=List(x4941, CU.load(x4936)), op=FixAdd, results=List(CU.scalarOut(x4933_b5178_x4948_b5180_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4933_b5179_x4948_b5181_s)))
    }
    val x4959 = Pipeline(name="x4959",parent=x4960) { implicit CU => 
      val ctr135 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4951 = CounterChain(name = "x4951", ctr135)
      var stage: List[Stage] = Nil
    }
    val x4961 = MemoryController(name="x4961",parent=x4970,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4933_b5179_x4961 =  ScalarFIFO(name="size",size=1).wtPort(x4933_b5179_x4948_b5181_s)
      val x4933_b5178_x4961 =  ScalarFIFO(name="offset",size=1).wtPort(x4933_b5178_x4948_b5180_s)
      val x4934_x4961 =  VectorFIFO(name="data",size=1).wtPort(x4057_x4955_x4959_v)
    }
    val x5010 = StreamController(name="x5010",parent=x5012) { implicit CU => 
      val ctr138 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4972 = CounterChain(name = "x4972", ctr138)
    }
    val x5000 = Sequential(name="x5000",parent=x5010) { implicit CU => 
      val ctr139 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5000_unit = CounterChain(name = "x5000_unit", ctr139)
    }
    val x4989_0 = Pipeline(name="x4989_0",parent=x5000) { implicit CU => 
      val x4980 = CU.temp
      val x4978 = CU.temp
      val x4979 = CU.temp
      val x4981 = CU.temp
      val x4976 =  ScalarBuffer().wtPort(x4976_argin)
      val x4012_x4977 =  ScalarBuffer().wtPort(x4012_argin)
      val x4028 = CounterChain.copy("x5012", "x4028")
      val x4972 = CounterChain.copy("x5010", "x4972")
      val ctr140 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4989_unit = CounterChain(name = "x4989_unit", ctr140)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4028(0)), CU.ctr(x4972(0))), op=FixAdd, results=List(x4978))
      Stage(operands=List(x4978, CU.load(x4012_x4977)), op=FixMul, results=List(x4979))
      Stage(operands=List(x4979, CU.ctr(x4028(1))), op=FixAdd, results=List(x4980))
      Stage(operands=List(x4980, Const(4)), op=FixMul, results=List(x4981))
      Stage(operands=List(x4981, CU.load(x4976)), op=FixAdd, results=List(CU.scalarOut(x4973_b5184_x4988_b5186_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4973_b5185_x4988_b5187_s)))
    }
    val x4999 = Pipeline(name="x4999",parent=x5000) { implicit CU => 
      val ctr141 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4991 = CounterChain(name = "x4991", ctr141)
      var stage: List[Stage] = Nil
    }
    val x5001 = MemoryController(name="x5001",parent=x5010,offchip=x4021_oc, mctpe=TileStore) { implicit CU => 
      val x4973_b5185_x5001 =  ScalarFIFO(name="size",size=1).wtPort(x4973_b5185_x4988_b5187_s)
      val x4973_b5184_x5001 =  ScalarFIFO(name="offset",size=1).wtPort(x4973_b5184_x4988_b5186_s)
      val x4974_x5001 =  VectorFIFO(name="data",size=1).wtPort(x4058_x4995_x4999_v)
    }
    
  }
}
