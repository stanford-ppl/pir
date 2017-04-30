import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object MatMult_inner extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4083_argin = ArgIn("x4083")
    val x3871_argin = ArgIn("x3871")
    val x4047_x4123_x4135_v = Vector("x4047_x4123_x4135")
    val x3712_oc = OffChip("x3712")
    val x4052_argin = ArgIn("x4052")
    val x3700_argin = ArgIn("x3700")
    val x3732_b4487_x3747_b4489_s = Scalar("x3732_b4487_x3747_b4489")
    val x4050_b4548_x4065_b4550_s = Scalar("x4050_b4548_x4065_b4550")
    val x3699_argin = ArgIn("x3699")
    val x3765_argin = ArgIn("x3765")
    val x3721_x3933_v = Vector("x3721_x3933")
    val x3975_b4534_x3990_b4536_s = Scalar("x3975_b4534_x3990_b4536")
    val x4156_b4568_x4171_b4570_s = Scalar("x4156_b4568_x4171_b4570")
    val x3722_x4360_x4364_v = Vector("x3722_x4360_x4364")
    val x4189_argin = ArgIn("x4189")
    val x3724_x4440_x4444_v = Vector("x3724_x4440_x4444")
    val x3729_x3805_x3817_v = Vector("x3729_x3805_x3817")
    val x3720_x3827_v = Vector("x3720_x3827")
    val x3709_oc = OffChip("x3709")
    val x4418_b4611_x4433_b4613_s = Scalar("x4418_b4611_x4433_b4613")
    val x4008_x4027_s = Scalar("x4008_x4027")
    val x4220_x4239_s = Scalar("x4220_x4239")
    val x4378_b4606_x4393_b4608_s = Scalar("x4378_b4606_x4393_b4608")
    val x3721_x4320_x4324_v = Vector("x3721_x4320_x4324")
    val x4156_b4567_x4171_b4569_s = Scalar("x4156_b4567_x4171_b4569")
    val x4081_b4554_x4096_b4556_s = Scalar("x4081_b4554_x4096_b4556")
    val x4081_b4553_x4096_b4555_s = Scalar("x4081_b4553_x4096_b4555")
    val x3723_x4140_x4146_v = Vector("x3723_x4140_x4146")
    val x3977_argin = ArgIn("x3977")
    val x4187_b4573_x4202_b4575_s = Scalar("x4187_b4573_x4202_b4575")
    val x4051_x4067_data_v = Vector("x4051_x4067_data")
    val x3723_x4145_v = Vector("x3723_x4145")
    val x4261_argin = ArgIn("x4261")
    val x3764_x3780_data_v = Vector("x3764_x3780_data")
    val x4158_argin = ArgIn("x4158")
    val x4046_x4122_x4135_v = Vector("x4046_x4122_x4135")
    val x4378_b4605_x4393_b4607_s = Scalar("x4378_b4605_x4393_b4607")
    val x4050_b4547_x4065_b4549_s = Scalar("x4050_b4547_x4065_b4549")
    val x4157_x4173_data_v = Vector("x4157_x4173_data")
    val x3706_oc = OffChip("x3706")
    val x3763_b4493_x3778_b4495_s = Scalar("x3763_b4493_x3778_b4495")
    val x3945_x3961_data_v = Vector("x3945_x3961_data")
    val x3721_x3928_x3934_v = Vector("x3721_x3928_x3934")
    val x4338_b4599_x4353_b4601_s = Scalar("x4338_b4599_x4353_b4601")
    val x3869_b4513_x3884_b4515_s = Scalar("x3869_b4513_x3884_b4515")
    val x3722_x4034_x4040_v = Vector("x3722_x4034_x4040")
    val x4258_b4587_x4273_b4589_s = Scalar("x4258_b4587_x4273_b4589")
    val x3840_argin = ArgIn("x3840")
    val x3723_x4400_x4404_v = Vector("x3723_x4400_x4404")
    val x4301_argin = ArgIn("x4301")
    val x3946_argin = ArgIn("x3946")
    val x4114_x4133_s = Scalar("x4114_x4133")
    val x3940_x4016_x4029_v = Vector("x3940_x4016_x4029")
    val x4188_x4204_data_v = Vector("x4188_x4204_data")
    val x3724_x4246_x4252_v = Vector("x3724_x4246_x4252")
    val x4258_b4588_x4273_b4590_s = Scalar("x4258_b4588_x4273_b4590")
    val x4187_b4574_x4202_b4576_s = Scalar("x4187_b4574_x4202_b4576")
    val x4152_x4228_x4241_v = Vector("x4152_x4228_x4241")
    val x3944_b4528_x3959_b4530_s = Scalar("x3944_b4528_x3959_b4530")
    val x4381_argin = ArgIn("x4381")
    val x4341_argin = ArgIn("x4341")
    val x3698_argin = ArgIn("x3698")
    val x3835_x3911_x3923_v = Vector("x3835_x3911_x3923")
    val x3869_b4514_x3884_b4516_s = Scalar("x3869_b4514_x3884_b4516")
    val x4298_b4593_x4313_b4595_s = Scalar("x4298_b4593_x4313_b4595")
    val x3838_b4508_x3853_b4510_s = Scalar("x3838_b4508_x3853_b4510")
    val x3732_b4488_x3747_b4490_s = Scalar("x3732_b4488_x3747_b4490")
    val x3734_argin = ArgIn("x3734")
    val x3976_x3992_data_v = Vector("x3976_x3992_data")
    val x4338_b4600_x4353_b4602_s = Scalar("x4338_b4600_x4353_b4602")
    val x3870_x3886_data_v = Vector("x3870_x3886_data")
    val x3944_b4527_x3959_b4529_s = Scalar("x3944_b4527_x3959_b4529")
    val x3838_b4507_x3853_b4509_s = Scalar("x3838_b4507_x3853_b4509")
    val x3839_x3855_data_v = Vector("x3839_x3855_data")
    val x4421_argin = ArgIn("x4421")
    val x3728_x3804_x3817_v = Vector("x3728_x3804_x3817")
    val x3724_x4251_v = Vector("x3724_x4251")
    val x3834_x3910_x3923_v = Vector("x3834_x3910_x3923")
    val x3722_x4039_v = Vector("x3722_x4039")
    val x3733_x3749_data_v = Vector("x3733_x3749_data")
    val x4418_b4612_x4433_b4614_s = Scalar("x4418_b4612_x4433_b4614")
    val x3763_b4494_x3778_b4496_s = Scalar("x3763_b4494_x3778_b4496")
    val x3902_x3921_s = Scalar("x3902_x3921")
    val x3941_x4017_x4029_v = Vector("x3941_x4017_x4029")
    val x3720_x4280_x4284_v = Vector("x3720_x4280_x4284")
    val x3975_b4533_x3990_b4535_s = Scalar("x3975_b4533_x3990_b4535")
    val x4153_x4229_x4241_v = Vector("x4153_x4229_x4241")
    val x4298_b4594_x4313_b4596_s = Scalar("x4298_b4594_x4313_b4596")
    val x3796_x3815_s = Scalar("x3796_x3815")
    val x3720_x3822_x3828_v = Vector("x3720_x3822_x3828")
    val x4082_x4098_data_v = Vector("x4082_x4098_data")
    val x4458 = Sequential(name="x4458",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4458_unit = CounterChain(name = "x4458_unit", ctr1)
    }
    val x4457 = MetaPipeline(name="x4457",parent=x4458) { implicit CU => 
      val x3699_x3715 =  ScalarBuffer().wtPort(x3699_argin)
      val x3698_x3717 =  ScalarBuffer().wtPort(x3698_argin)
      val ctr2 = Counter(min=Const(0), max=x3698_x3717.load, step=Const(16), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x3699_x3715.load, step=Const(48), par=5) // Counter
      val x3719 = CounterChain(name = "x3719", ctr2, ctr3)
    }
    val x3720_dsp0 = MemoryPipeline(name="x3720_dsp0",parent="x4457") { implicit CU => 
      val b4505 = CU.temp
      val b4591 = CU.temp
      val x3827_x3827 =  VectorFIFO(size=1).wtPort(x3720_x3827_v)
      val x3795 = CounterChain.copy("x3829", "x3795")
      val x4257 = CounterChain.copy("x4295", "x4257")
      val x4276 = CounterChain.copy("x4284", "x4276")
      val x3720_x4280 =  SRAM(size=768,banking = NoBanking()).wtPort(x3827_x3827.readPort).rdPort(x3720_x4280_x4284_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3795(0)), Const(48)), op=FixMul, results=List(b4505))
      WAStage(operands=List(b4505, CU.ctr(x3795(1))), op=FixAdd, results=List(x3720_x4280.writeAddr))
      RAStage(operands=List(CU.ctr(x4257(0)), Const(48)), op=FixMul, results=List(b4591))
      RAStage(operands=List(b4591, CU.ctr(x4276(0))), op=FixAdd, results=List(x3720_x4280.readAddr))
    }
    val x3720_dsp1 = MemoryPipeline(name="x3720_dsp1",parent="x4457") { implicit CU => 
      val b4503 = CU.temp
      val b4505 = CU.temp
      val x3827_x3827 =  VectorFIFO(size=1).wtPort(x3720_x3827_v)
      val x3795 = CounterChain.copy("x3829", "x3795")
      val x3720_x3822 =  SRAM(size=768,banking = NoBanking()).wtPort(x3827_x3827.readPort).rdPort(x3720_x3822_x3828_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3795(0)), Const(48)), op=FixMul, results=List(b4505))
      WAStage(operands=List(b4505, CU.ctr(x3795(1))), op=FixAdd, results=List(x3720_x3822.writeAddr))
      RAStage(operands=List(CU.ctr(x3795(0)), Const(48)), op=FixMul, results=List(b4503))
      RAStage(operands=List(b4503, CU.ctr(x3795(1))), op=FixAdd, results=List(x3720_x3822.readAddr))
    }
    val x3721_dsp0 = MemoryPipeline(name="x3721_dsp0",parent="x4457") { implicit CU => 
      val b4525 = CU.temp
      val b4597 = CU.temp
      val x3933_x3933 =  VectorFIFO(size=1).wtPort(x3721_x3933_v)
      val x3901 = CounterChain.copy("x3935", "x3901")
      val x4297 = CounterChain.copy("x4335", "x4297")
      val x4316 = CounterChain.copy("x4324", "x4316")
      val x3721_x4320 =  SRAM(size=768,banking = NoBanking()).wtPort(x3933_x3933.readPort).rdPort(x3721_x4320_x4324_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3901(0)), Const(48)), op=FixMul, results=List(b4525))
      WAStage(operands=List(b4525, CU.ctr(x3901(1))), op=FixAdd, results=List(x3721_x4320.writeAddr))
      RAStage(operands=List(CU.ctr(x4297(0)), Const(48)), op=FixMul, results=List(b4597))
      RAStage(operands=List(b4597, CU.ctr(x4316(0))), op=FixAdd, results=List(x3721_x4320.readAddr))
    }
    val x3721_dsp1 = MemoryPipeline(name="x3721_dsp1",parent="x4457") { implicit CU => 
      val b4523 = CU.temp
      val b4525 = CU.temp
      val x3933_x3933 =  VectorFIFO(size=1).wtPort(x3721_x3933_v)
      val x3901 = CounterChain.copy("x3935", "x3901")
      val x3721_x3928 =  SRAM(size=768,banking = NoBanking()).wtPort(x3933_x3933.readPort).rdPort(x3721_x3928_x3934_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3901(0)), Const(48)), op=FixMul, results=List(b4525))
      WAStage(operands=List(b4525, CU.ctr(x3901(1))), op=FixAdd, results=List(x3721_x3928.writeAddr))
      RAStage(operands=List(CU.ctr(x3901(0)), Const(48)), op=FixMul, results=List(b4523))
      RAStage(operands=List(b4523, CU.ctr(x3901(1))), op=FixAdd, results=List(x3721_x3928.readAddr))
    }
    val x3722_dsp0 = MemoryPipeline(name="x3722_dsp0",parent="x4457") { implicit CU => 
      val b4603 = CU.temp
      val b4545 = CU.temp
      val x4039_x4039 =  VectorFIFO(size=1).wtPort(x3722_x4039_v)
      val x4007 = CounterChain.copy("x4041", "x4007")
      val x4337 = CounterChain.copy("x4375", "x4337")
      val x4356 = CounterChain.copy("x4364", "x4356")
      val x3722_x4360 =  SRAM(size=768,banking = NoBanking()).wtPort(x4039_x4039.readPort).rdPort(x3722_x4360_x4364_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4007(0)), Const(48)), op=FixMul, results=List(b4545))
      WAStage(operands=List(b4545, CU.ctr(x4007(1))), op=FixAdd, results=List(x3722_x4360.writeAddr))
      RAStage(operands=List(CU.ctr(x4337(0)), Const(48)), op=FixMul, results=List(b4603))
      RAStage(operands=List(b4603, CU.ctr(x4356(0))), op=FixAdd, results=List(x3722_x4360.readAddr))
    }
    val x3722_dsp1 = MemoryPipeline(name="x3722_dsp1",parent="x4457") { implicit CU => 
      val b4543 = CU.temp
      val b4545 = CU.temp
      val x4039_x4039 =  VectorFIFO(size=1).wtPort(x3722_x4039_v)
      val x4007 = CounterChain.copy("x4041", "x4007")
      val x3722_x4034 =  SRAM(size=768,banking = NoBanking()).wtPort(x4039_x4039.readPort).rdPort(x3722_x4034_x4040_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4007(0)), Const(48)), op=FixMul, results=List(b4545))
      WAStage(operands=List(b4545, CU.ctr(x4007(1))), op=FixAdd, results=List(x3722_x4034.writeAddr))
      RAStage(operands=List(CU.ctr(x4007(0)), Const(48)), op=FixMul, results=List(b4543))
      RAStage(operands=List(b4543, CU.ctr(x4007(1))), op=FixAdd, results=List(x3722_x4034.readAddr))
    }
    val x3723_dsp0 = MemoryPipeline(name="x3723_dsp0",parent="x4457") { implicit CU => 
      val b4609 = CU.temp
      val b4565 = CU.temp
      val x4145_x4145 =  VectorFIFO(size=1).wtPort(x3723_x4145_v)
      val x4113 = CounterChain.copy("x4147", "x4113")
      val x4377 = CounterChain.copy("x4415", "x4377")
      val x4396 = CounterChain.copy("x4404", "x4396")
      val x3723_x4400 =  SRAM(size=768,banking = NoBanking()).wtPort(x4145_x4145.readPort).rdPort(x3723_x4400_x4404_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4113(0)), Const(48)), op=FixMul, results=List(b4565))
      WAStage(operands=List(b4565, CU.ctr(x4113(1))), op=FixAdd, results=List(x3723_x4400.writeAddr))
      RAStage(operands=List(CU.ctr(x4377(0)), Const(48)), op=FixMul, results=List(b4609))
      RAStage(operands=List(b4609, CU.ctr(x4396(0))), op=FixAdd, results=List(x3723_x4400.readAddr))
    }
    val x3723_dsp1 = MemoryPipeline(name="x3723_dsp1",parent="x4457") { implicit CU => 
      val b4563 = CU.temp
      val b4565 = CU.temp
      val x4145_x4145 =  VectorFIFO(size=1).wtPort(x3723_x4145_v)
      val x4113 = CounterChain.copy("x4147", "x4113")
      val x3723_x4140 =  SRAM(size=768,banking = NoBanking()).wtPort(x4145_x4145.readPort).rdPort(x3723_x4140_x4146_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4113(0)), Const(48)), op=FixMul, results=List(b4565))
      WAStage(operands=List(b4565, CU.ctr(x4113(1))), op=FixAdd, results=List(x3723_x4140.writeAddr))
      RAStage(operands=List(CU.ctr(x4113(0)), Const(48)), op=FixMul, results=List(b4563))
      RAStage(operands=List(b4563, CU.ctr(x4113(1))), op=FixAdd, results=List(x3723_x4140.readAddr))
    }
    val x3724_dsp0 = MemoryPipeline(name="x3724_dsp0",parent="x4457") { implicit CU => 
      val b4615 = CU.temp
      val b4585 = CU.temp
      val x4251_x4251 =  VectorFIFO(size=1).wtPort(x3724_x4251_v)
      val x4219 = CounterChain.copy("x4253", "x4219")
      val x4417 = CounterChain.copy("x4455", "x4417")
      val x4436 = CounterChain.copy("x4444", "x4436")
      val x3724_x4440 =  SRAM(size=768,banking = NoBanking()).wtPort(x4251_x4251.readPort).rdPort(x3724_x4440_x4444_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4219(0)), Const(48)), op=FixMul, results=List(b4585))
      WAStage(operands=List(b4585, CU.ctr(x4219(1))), op=FixAdd, results=List(x3724_x4440.writeAddr))
      RAStage(operands=List(CU.ctr(x4417(0)), Const(48)), op=FixMul, results=List(b4615))
      RAStage(operands=List(b4615, CU.ctr(x4436(0))), op=FixAdd, results=List(x3724_x4440.readAddr))
    }
    val x3724_dsp1 = MemoryPipeline(name="x3724_dsp1",parent="x4457") { implicit CU => 
      val b4583 = CU.temp
      val b4585 = CU.temp
      val x4251_x4251 =  VectorFIFO(size=1).wtPort(x3724_x4251_v)
      val x4219 = CounterChain.copy("x4253", "x4219")
      val x3724_x4246 =  SRAM(size=768,banking = NoBanking()).wtPort(x4251_x4251.readPort).rdPort(x3724_x4246_x4252_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4219(0)), Const(48)), op=FixMul, results=List(b4585))
      WAStage(operands=List(b4585, CU.ctr(x4219(1))), op=FixAdd, results=List(x3724_x4246.writeAddr))
      RAStage(operands=List(CU.ctr(x4219(0)), Const(48)), op=FixMul, results=List(b4583))
      RAStage(operands=List(b4583, CU.ctr(x4219(1))), op=FixAdd, results=List(x3724_x4246.readAddr))
    }
    val x3830 = MetaPipeline(name="x3830",parent=x4457) { implicit CU => 
      val x3700_x3725 =  ScalarBuffer().wtPort(x3700_argin)
      val ctr4 = Counter(min=Const(0), max=x3700_x3725.load, step=Const(48), par=1) // Counter
      val x3727 = CounterChain(name = "x3727", ctr4)
    }
    val x3728_dsp0 = MemoryPipeline(name="x3728_dsp0",parent="x3830") { implicit CU => 
      val b4499 = CU.temp
      val b4491 = CU.temp
      val x3758_x3758 =  VectorFIFO(size=1).wtPort(x3733_x3749_data_v)
      val x3798 = CounterChain.copy("x3817", "x3798")
      val x3795 = CounterChain.copy("x3829", "x3795")
      val x3751 = CounterChain.copy("x3759", "x3751")
      val x3731 = CounterChain.copy("x3760", "x3731")
      val x3728_x3804 =  SRAM(size=768,banking = Strided(1)).wtPort(x3758_x3758.readPort).rdPort(x3728_x3804_x3817_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3731(0)), Const(48)), op=FixMul, results=List(b4491))
      WAStage(operands=List(b4491, CU.ctr(x3751(0))), op=FixAdd, results=List(x3728_x3804.writeAddr))
      RAStage(operands=List(CU.ctr(x3795(0)), Const(48)), op=FixMul, results=List(b4499))
      RAStage(operands=List(b4499, CU.ctr(x3798(0))), op=FixAdd, results=List(x3728_x3804.readAddr))
    }
    val x3729_dsp0 = MemoryPipeline(name="x3729_dsp0",parent="x3830") { implicit CU => 
      val b4497 = CU.temp
      val b4501 = CU.temp
      val x3789_x3789 =  VectorFIFO(size=1).wtPort(x3764_x3780_data_v)
      val x3798 = CounterChain.copy("x3817", "x3798")
      val x3795 = CounterChain.copy("x3829", "x3795")
      val x3782 = CounterChain.copy("x3790", "x3782")
      val x3762 = CounterChain.copy("x3791", "x3762")
      val x3729_x3805 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3789_x3789.readPort).rdPort(x3729_x3805_x3817_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3762(0)), Const(48)), op=FixMul, results=List(b4497))
      WAStage(operands=List(b4497, CU.ctr(x3782(0))), op=FixAdd, results=List(x3729_x3805.writeAddr))
      RAStage(operands=List(CU.ctr(x3798(0)), Const(48)), op=FixMul, results=List(b4501))
      RAStage(operands=List(b4501, CU.ctr(x3795(1))), op=FixAdd, results=List(x3729_x3805.readAddr))
    }
    val x3760 = StreamController(name="x3760",parent=x3830) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x3731 = CounterChain(name = "x3731", ctr5)
    }
    val x3748 = Pipeline(name="x3748",parent=x3760) { implicit CU => 
      val x3739 = CU.temp
      val x3738 = CU.temp
      val x3737 = CU.temp
      val x3736 = CU.temp
      val x3734 =  ScalarBuffer().wtPort(x3734_argin)
      val x3700_x3735 =  ScalarBuffer().wtPort(x3700_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x3731 = CounterChain.copy("x3760", "x3731")
      val x3727 = CounterChain.copy("x3830", "x3727")
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3748_unit = CounterChain(name = "x3748_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x3731(0))), op=FixAdd, results=List(x3736))
      Stage(operands=List(x3736, CU.load(x3700_x3735)), op=FixMul, results=List(x3737))
      Stage(operands=List(x3737, CU.ctr(x3727(0))), op=FixAdd, results=List(x3738))
      Stage(operands=List(x3738, Const(4)), op=FixMul, results=List(x3739))
      Stage(operands=List(x3739, CU.load(x3734)), op=FixAdd, results=List(CU.scalarOut(x3732_b4487_x3747_b4489_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3732_b4488_x3747_b4490_s)))
    }
    val x3749 = MemoryController(name="x3749",parent=x3760,offchip=x3706_oc, mctpe=TileLoad) { implicit CU => 
      val x3732_b4488_x3749 =  ScalarFIFO(name="size",size=1).wtPort(x3732_b4488_x3747_b4490_s)
      val x3732_b4487_x3749 =  ScalarFIFO(name="offset",size=1).wtPort(x3732_b4487_x3747_b4489_s)
      CU.newVout("data", x3733_x3749_data_v)
    }
    val x3759 = Pipeline(name="x3759",parent=x3760) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3751 = CounterChain(name = "x3751", ctr7)
      var stage: List[Stage] = Nil
    }
    val x3791 = StreamController(name="x3791",parent=x3830) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3762 = CounterChain(name = "x3762", ctr8)
    }
    val x3779 = Pipeline(name="x3779",parent=x3791) { implicit CU => 
      val x3768 = CU.temp
      val x3769 = CU.temp
      val x3770 = CU.temp
      val x3767 = CU.temp
      val x3699_x3766 =  ScalarBuffer().wtPort(x3699_argin)
      val x3765 =  ScalarBuffer().wtPort(x3765_argin)
      val x3727 = CounterChain.copy("x3830", "x3727")
      val x3762 = CounterChain.copy("x3791", "x3762")
      val x3719 = CounterChain.copy("x4457", "x3719")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3779_unit = CounterChain(name = "x3779_unit", ctr9)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3727(0)), CU.ctr(x3762(0))), op=FixAdd, results=List(x3767))
      Stage(operands=List(x3767, CU.load(x3699_x3766)), op=FixMul, results=List(x3768))
      Stage(operands=List(x3768, CU.ctr(x3719(1))), op=FixAdd, results=List(x3769))
      Stage(operands=List(x3769, Const(4)), op=FixMul, results=List(x3770))
      Stage(operands=List(x3770, CU.load(x3765)), op=FixAdd, results=List(CU.scalarOut(x3763_b4493_x3778_b4495_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3763_b4494_x3778_b4496_s)))
    }
    val x3780 = MemoryController(name="x3780",parent=x3791,offchip=x3709_oc, mctpe=TileLoad) { implicit CU => 
      val x3763_b4494_x3780 =  ScalarFIFO(name="size",size=1).wtPort(x3763_b4494_x3778_b4496_s)
      val x3763_b4493_x3780 =  ScalarFIFO(name="offset",size=1).wtPort(x3763_b4493_x3778_b4495_s)
      CU.newVout("data", x3764_x3780_data_v)
    }
    val x3790 = Pipeline(name="x3790",parent=x3791) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3782 = CounterChain(name = "x3782", ctr10)
      var stage: List[Stage] = Nil
    }
    val x3829 = MetaPipeline(name="x3829",parent=x3830) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr12 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3795 = CounterChain(name = "x3795", ctr11, ctr12)
    }
    val x3817 = Pipeline(name="x3817",parent=x3829) { implicit CU => 
      val x3728_x3804 =  VectorFIFO(size=1).wtPort(x3728_x3804_x3817_v)
      val x3729_x3805 =  VectorFIFO(size=1).wtPort(x3729_x3805_x3817_v)
      val ctr13 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3798 = CounterChain(name = "x3798", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3728_x3804), CU.load(x3729_x3805)), op=FixMul, results=List(CU.reduce))
      val (_, rr1762) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1762), op=Bypass, results=List(CU.scalarOut(x3796_x3815_s)))
    }
    val x3828 = Pipeline(name="x3828",parent=x3829) { implicit CU => 
      val x3825 = CU.temp
      val x3824 = CU.temp
      val x3796_x3823 =  ScalarBuffer().wtPort(x3796_x3815_s)
      val x3720_x3822 =  VectorFIFO(size=1).wtPort(x3720_x3822_x3828_v)
      val x3727 = CounterChain.copy("x3830", "x3727")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3828_unit = CounterChain(name = "x3828_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3727(0)), Const(0)), op=FixEql, results=List(x3824))
      Stage(operands=List(x3824, Const(0), CU.load(x3720_x3822)), op=Mux, results=List(x3825))
      Stage(operands=List(x3825, CU.load(x3796_x3823)), op=FixAdd, results=List(CU.vecOut(x3720_x3827_v)))
    }
    val x3936 = MetaPipeline(name="x3936",parent=x4457) { implicit CU => 
      val x3700_x3831 =  ScalarBuffer().wtPort(x3700_argin)
      val ctr15 = Counter(min=Const(0), max=x3700_x3831.load, step=Const(48), par=1) // Counter
      val x3833 = CounterChain(name = "x3833", ctr15)
    }
    val x3834_dsp0 = MemoryPipeline(name="x3834_dsp0",parent="x3936") { implicit CU => 
      val b4519 = CU.temp
      val b4511 = CU.temp
      val x3864_x3864 =  VectorFIFO(size=1).wtPort(x3839_x3855_data_v)
      val x3904 = CounterChain.copy("x3923", "x3904")
      val x3857 = CounterChain.copy("x3865", "x3857")
      val x3901 = CounterChain.copy("x3935", "x3901")
      val x3837 = CounterChain.copy("x3866", "x3837")
      val x3834_x3910 =  SRAM(size=768,banking = Strided(1)).wtPort(x3864_x3864.readPort).rdPort(x3834_x3910_x3923_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3837(0)), Const(48)), op=FixMul, results=List(b4511))
      WAStage(operands=List(b4511, CU.ctr(x3857(0))), op=FixAdd, results=List(x3834_x3910.writeAddr))
      RAStage(operands=List(CU.ctr(x3901(0)), Const(48)), op=FixMul, results=List(b4519))
      RAStage(operands=List(b4519, CU.ctr(x3904(0))), op=FixAdd, results=List(x3834_x3910.readAddr))
    }
    val x3835_dsp0 = MemoryPipeline(name="x3835_dsp0",parent="x3936") { implicit CU => 
      val b4517 = CU.temp
      val b4521 = CU.temp
      val x3895_x3895 =  VectorFIFO(size=1).wtPort(x3870_x3886_data_v)
      val x3888 = CounterChain.copy("x3896", "x3888")
      val x3904 = CounterChain.copy("x3923", "x3904")
      val x3868 = CounterChain.copy("x3897", "x3868")
      val x3901 = CounterChain.copy("x3935", "x3901")
      val x3835_x3911 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3895_x3895.readPort).rdPort(x3835_x3911_x3923_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3868(0)), Const(48)), op=FixMul, results=List(b4517))
      WAStage(operands=List(b4517, CU.ctr(x3888(0))), op=FixAdd, results=List(x3835_x3911.writeAddr))
      RAStage(operands=List(CU.ctr(x3904(0)), Const(48)), op=FixMul, results=List(b4521))
      RAStage(operands=List(b4521, CU.ctr(x3901(1))), op=FixAdd, results=List(x3835_x3911.readAddr))
    }
    val x3866 = StreamController(name="x3866",parent=x3936) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x3837 = CounterChain(name = "x3837", ctr16)
    }
    val x3854 = Pipeline(name="x3854",parent=x3866) { implicit CU => 
      val x3843 = CU.temp
      val x3844 = CU.temp
      val x3845 = CU.temp
      val x3842 = CU.temp
      val x3700_x3841 =  ScalarBuffer().wtPort(x3700_argin)
      val x3840 =  ScalarBuffer().wtPort(x3840_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x3837 = CounterChain.copy("x3866", "x3837")
      val x3833 = CounterChain.copy("x3936", "x3833")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3854_unit = CounterChain(name = "x3854_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x3837(0))), op=FixAdd, results=List(x3842))
      Stage(operands=List(x3842, CU.load(x3700_x3841)), op=FixMul, results=List(x3843))
      Stage(operands=List(x3843, CU.ctr(x3833(0))), op=FixAdd, results=List(x3844))
      Stage(operands=List(x3844, Const(4)), op=FixMul, results=List(x3845))
      Stage(operands=List(x3845, CU.load(x3840)), op=FixAdd, results=List(CU.scalarOut(x3838_b4507_x3853_b4509_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3838_b4508_x3853_b4510_s)))
    }
    val x3855 = MemoryController(name="x3855",parent=x3866,offchip=x3706_oc, mctpe=TileLoad) { implicit CU => 
      val x3838_b4508_x3855 =  ScalarFIFO(name="size",size=1).wtPort(x3838_b4508_x3853_b4510_s)
      val x3838_b4507_x3855 =  ScalarFIFO(name="offset",size=1).wtPort(x3838_b4507_x3853_b4509_s)
      CU.newVout("data", x3839_x3855_data_v)
    }
    val x3865 = Pipeline(name="x3865",parent=x3866) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3857 = CounterChain(name = "x3857", ctr18)
      var stage: List[Stage] = Nil
    }
    val x3897 = StreamController(name="x3897",parent=x3936) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3868 = CounterChain(name = "x3868", ctr19)
    }
    val x3885 = Pipeline(name="x3885",parent=x3897) { implicit CU => 
      val x3876 = CU.temp
      val x3874 = CU.temp
      val x3873 = CU.temp
      val x3875 = CU.temp
      val x3699_x3872 =  ScalarBuffer().wtPort(x3699_argin)
      val x3871 =  ScalarBuffer().wtPort(x3871_argin)
      val x3833 = CounterChain.copy("x3936", "x3833")
      val x3868 = CounterChain.copy("x3897", "x3868")
      val x3719 = CounterChain.copy("x4457", "x3719")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3885_unit = CounterChain(name = "x3885_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3833(0)), CU.ctr(x3868(0))), op=FixAdd, results=List(x3873))
      Stage(operands=List(x3873, CU.load(x3699_x3872)), op=FixMul, results=List(x3874))
      Stage(operands=List(x3874, CU.ctr(x3719(1))), op=FixAdd, results=List(x3875))
      Stage(operands=List(x3875, Const(4)), op=FixMul, results=List(x3876))
      Stage(operands=List(x3876, CU.load(x3871)), op=FixAdd, results=List(CU.scalarOut(x3869_b4513_x3884_b4515_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3869_b4514_x3884_b4516_s)))
    }
    val x3886 = MemoryController(name="x3886",parent=x3897,offchip=x3709_oc, mctpe=TileLoad) { implicit CU => 
      val x3869_b4514_x3886 =  ScalarFIFO(name="size",size=1).wtPort(x3869_b4514_x3884_b4516_s)
      val x3869_b4513_x3886 =  ScalarFIFO(name="offset",size=1).wtPort(x3869_b4513_x3884_b4515_s)
      CU.newVout("data", x3870_x3886_data_v)
    }
    val x3896 = Pipeline(name="x3896",parent=x3897) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3888 = CounterChain(name = "x3888", ctr21)
      var stage: List[Stage] = Nil
    }
    val x3935 = MetaPipeline(name="x3935",parent=x3936) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr23 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3901 = CounterChain(name = "x3901", ctr22, ctr23)
    }
    val x3923 = Pipeline(name="x3923",parent=x3935) { implicit CU => 
      val x3835_x3911 =  VectorFIFO(size=1).wtPort(x3835_x3911_x3923_v)
      val x3834_x3910 =  VectorFIFO(size=1).wtPort(x3834_x3910_x3923_v)
      val ctr24 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3904 = CounterChain(name = "x3904", ctr24)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3834_x3910), CU.load(x3835_x3911)), op=FixMul, results=List(CU.reduce))
      val (_, rr1813) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1813), op=Bypass, results=List(CU.scalarOut(x3902_x3921_s)))
    }
    val x3934 = Pipeline(name="x3934",parent=x3935) { implicit CU => 
      val x3931 = CU.temp
      val x3930 = CU.temp
      val x3902_x3929 =  ScalarBuffer().wtPort(x3902_x3921_s)
      val x3721_x3928 =  VectorFIFO(size=1).wtPort(x3721_x3928_x3934_v)
      val x3833 = CounterChain.copy("x3936", "x3833")
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3934_unit = CounterChain(name = "x3934_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3833(0)), Const(0)), op=FixEql, results=List(x3930))
      Stage(operands=List(x3930, Const(0), CU.load(x3721_x3928)), op=Mux, results=List(x3931))
      Stage(operands=List(x3931, CU.load(x3902_x3929)), op=FixAdd, results=List(CU.vecOut(x3721_x3933_v)))
    }
    val x4042 = MetaPipeline(name="x4042",parent=x4457) { implicit CU => 
      val x3700_x3937 =  ScalarBuffer().wtPort(x3700_argin)
      val ctr26 = Counter(min=Const(0), max=x3700_x3937.load, step=Const(48), par=1) // Counter
      val x3939 = CounterChain(name = "x3939", ctr26)
    }
    val x3940_dsp0 = MemoryPipeline(name="x3940_dsp0",parent="x4042") { implicit CU => 
      val b4539 = CU.temp
      val b4531 = CU.temp
      val x3970_x3970 =  VectorFIFO(size=1).wtPort(x3945_x3961_data_v)
      val x3943 = CounterChain.copy("x3972", "x3943")
      val x3963 = CounterChain.copy("x3971", "x3963")
      val x4010 = CounterChain.copy("x4029", "x4010")
      val x4007 = CounterChain.copy("x4041", "x4007")
      val x3940_x4016 =  SRAM(size=768,banking = Strided(1)).wtPort(x3970_x3970.readPort).rdPort(x3940_x4016_x4029_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3943(0)), Const(48)), op=FixMul, results=List(b4531))
      WAStage(operands=List(b4531, CU.ctr(x3963(0))), op=FixAdd, results=List(x3940_x4016.writeAddr))
      RAStage(operands=List(CU.ctr(x4007(0)), Const(48)), op=FixMul, results=List(b4539))
      RAStage(operands=List(b4539, CU.ctr(x4010(0))), op=FixAdd, results=List(x3940_x4016.readAddr))
    }
    val x3941_dsp0 = MemoryPipeline(name="x3941_dsp0",parent="x4042") { implicit CU => 
      val b4537 = CU.temp
      val b4541 = CU.temp
      val x4001_x4001 =  VectorFIFO(size=1).wtPort(x3976_x3992_data_v)
      val x3994 = CounterChain.copy("x4002", "x3994")
      val x3974 = CounterChain.copy("x4003", "x3974")
      val x4010 = CounterChain.copy("x4029", "x4010")
      val x4007 = CounterChain.copy("x4041", "x4007")
      val x3941_x4017 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4001_x4001.readPort).rdPort(x3941_x4017_x4029_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3974(0)), Const(48)), op=FixMul, results=List(b4537))
      WAStage(operands=List(b4537, CU.ctr(x3994(0))), op=FixAdd, results=List(x3941_x4017.writeAddr))
      RAStage(operands=List(CU.ctr(x4010(0)), Const(48)), op=FixMul, results=List(b4541))
      RAStage(operands=List(b4541, CU.ctr(x4007(1))), op=FixAdd, results=List(x3941_x4017.readAddr))
    }
    val x3972 = StreamController(name="x3972",parent=x4042) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x3943 = CounterChain(name = "x3943", ctr27)
    }
    val x3960 = Pipeline(name="x3960",parent=x3972) { implicit CU => 
      val x3950 = CU.temp
      val x3949 = CU.temp
      val x3948 = CU.temp
      val x3951 = CU.temp
      val x3946 =  ScalarBuffer().wtPort(x3946_argin)
      val x3700_x3947 =  ScalarBuffer().wtPort(x3700_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x3943 = CounterChain.copy("x3972", "x3943")
      val x3939 = CounterChain.copy("x4042", "x3939")
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3960_unit = CounterChain(name = "x3960_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x3943(0))), op=FixAdd, results=List(x3948))
      Stage(operands=List(x3948, CU.load(x3700_x3947)), op=FixMul, results=List(x3949))
      Stage(operands=List(x3949, CU.ctr(x3939(0))), op=FixAdd, results=List(x3950))
      Stage(operands=List(x3950, Const(4)), op=FixMul, results=List(x3951))
      Stage(operands=List(x3951, CU.load(x3946)), op=FixAdd, results=List(CU.scalarOut(x3944_b4527_x3959_b4529_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3944_b4528_x3959_b4530_s)))
    }
    val x3961 = MemoryController(name="x3961",parent=x3972,offchip=x3706_oc, mctpe=TileLoad) { implicit CU => 
      val x3944_b4527_x3961 =  ScalarFIFO(name="offset",size=1).wtPort(x3944_b4527_x3959_b4529_s)
      val x3944_b4528_x3961 =  ScalarFIFO(name="size",size=1).wtPort(x3944_b4528_x3959_b4530_s)
      CU.newVout("data", x3945_x3961_data_v)
    }
    val x3971 = Pipeline(name="x3971",parent=x3972) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3963 = CounterChain(name = "x3963", ctr29)
      var stage: List[Stage] = Nil
    }
    val x4003 = StreamController(name="x4003",parent=x4042) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3974 = CounterChain(name = "x3974", ctr30)
    }
    val x3991 = Pipeline(name="x3991",parent=x4003) { implicit CU => 
      val x3982 = CU.temp
      val x3981 = CU.temp
      val x3979 = CU.temp
      val x3980 = CU.temp
      val x3699_x3978 =  ScalarBuffer().wtPort(x3699_argin)
      val x3977 =  ScalarBuffer().wtPort(x3977_argin)
      val x3939 = CounterChain.copy("x4042", "x3939")
      val x3974 = CounterChain.copy("x4003", "x3974")
      val x3719 = CounterChain.copy("x4457", "x3719")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3991_unit = CounterChain(name = "x3991_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3939(0)), CU.ctr(x3974(0))), op=FixAdd, results=List(x3979))
      Stage(operands=List(x3979, CU.load(x3699_x3978)), op=FixMul, results=List(x3980))
      Stage(operands=List(x3980, CU.ctr(x3719(1))), op=FixAdd, results=List(x3981))
      Stage(operands=List(x3981, Const(4)), op=FixMul, results=List(x3982))
      Stage(operands=List(x3982, CU.load(x3977)), op=FixAdd, results=List(CU.scalarOut(x3975_b4533_x3990_b4535_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3975_b4534_x3990_b4536_s)))
    }
    val x3992 = MemoryController(name="x3992",parent=x4003,offchip=x3709_oc, mctpe=TileLoad) { implicit CU => 
      val x3975_b4533_x3992 =  ScalarFIFO(name="offset",size=1).wtPort(x3975_b4533_x3990_b4535_s)
      val x3975_b4534_x3992 =  ScalarFIFO(name="size",size=1).wtPort(x3975_b4534_x3990_b4536_s)
      CU.newVout("data", x3976_x3992_data_v)
    }
    val x4002 = Pipeline(name="x4002",parent=x4003) { implicit CU => 
      val ctr32 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3994 = CounterChain(name = "x3994", ctr32)
      var stage: List[Stage] = Nil
    }
    val x4041 = MetaPipeline(name="x4041",parent=x4042) { implicit CU => 
      val ctr33 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr34 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4007 = CounterChain(name = "x4007", ctr33, ctr34)
    }
    val x4029 = Pipeline(name="x4029",parent=x4041) { implicit CU => 
      val x3941_x4017 =  VectorFIFO(size=1).wtPort(x3941_x4017_x4029_v)
      val x3940_x4016 =  VectorFIFO(size=1).wtPort(x3940_x4016_x4029_v)
      val ctr35 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4010 = CounterChain(name = "x4010", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3940_x4016), CU.load(x3941_x4017)), op=FixMul, results=List(CU.reduce))
      val (_, rr1864) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1864), op=Bypass, results=List(CU.scalarOut(x4008_x4027_s)))
    }
    val x4040 = Pipeline(name="x4040",parent=x4041) { implicit CU => 
      val x4036 = CU.temp
      val x4037 = CU.temp
      val x3722_x4034 =  VectorFIFO(size=1).wtPort(x3722_x4034_x4040_v)
      val x4008_x4035 =  ScalarBuffer().wtPort(x4008_x4027_s)
      val x3939 = CounterChain.copy("x4042", "x3939")
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4040_unit = CounterChain(name = "x4040_unit", ctr36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3939(0)), Const(0)), op=FixEql, results=List(x4036))
      Stage(operands=List(x4036, Const(0), CU.load(x3722_x4034)), op=Mux, results=List(x4037))
      Stage(operands=List(x4037, CU.load(x4008_x4035)), op=FixAdd, results=List(CU.vecOut(x3722_x4039_v)))
    }
    val x4148 = MetaPipeline(name="x4148",parent=x4457) { implicit CU => 
      val x3700_x4043 =  ScalarBuffer().wtPort(x3700_argin)
      val ctr37 = Counter(min=Const(0), max=x3700_x4043.load, step=Const(48), par=1) // Counter
      val x4045 = CounterChain(name = "x4045", ctr37)
    }
    val x4046_dsp0 = MemoryPipeline(name="x4046_dsp0",parent="x4148") { implicit CU => 
      val b4559 = CU.temp
      val b4551 = CU.temp
      val x4076_x4076 =  VectorFIFO(size=1).wtPort(x4051_x4067_data_v)
      val x4069 = CounterChain.copy("x4077", "x4069")
      val x4116 = CounterChain.copy("x4135", "x4116")
      val x4113 = CounterChain.copy("x4147", "x4113")
      val x4049 = CounterChain.copy("x4078", "x4049")
      val x4046_x4122 =  SRAM(size=768,banking = Strided(1)).wtPort(x4076_x4076.readPort).rdPort(x4046_x4122_x4135_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4049(0)), Const(48)), op=FixMul, results=List(b4551))
      WAStage(operands=List(b4551, CU.ctr(x4069(0))), op=FixAdd, results=List(x4046_x4122.writeAddr))
      RAStage(operands=List(CU.ctr(x4113(0)), Const(48)), op=FixMul, results=List(b4559))
      RAStage(operands=List(b4559, CU.ctr(x4116(0))), op=FixAdd, results=List(x4046_x4122.readAddr))
    }
    val x4047_dsp0 = MemoryPipeline(name="x4047_dsp0",parent="x4148") { implicit CU => 
      val b4557 = CU.temp
      val b4561 = CU.temp
      val x4107_x4107 =  VectorFIFO(size=1).wtPort(x4082_x4098_data_v)
      val x4116 = CounterChain.copy("x4135", "x4116")
      val x4080 = CounterChain.copy("x4109", "x4080")
      val x4113 = CounterChain.copy("x4147", "x4113")
      val x4100 = CounterChain.copy("x4108", "x4100")
      val x4047_x4123 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4107_x4107.readPort).rdPort(x4047_x4123_x4135_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4080(0)), Const(48)), op=FixMul, results=List(b4557))
      WAStage(operands=List(b4557, CU.ctr(x4100(0))), op=FixAdd, results=List(x4047_x4123.writeAddr))
      RAStage(operands=List(CU.ctr(x4116(0)), Const(48)), op=FixMul, results=List(b4561))
      RAStage(operands=List(b4561, CU.ctr(x4113(1))), op=FixAdd, results=List(x4047_x4123.readAddr))
    }
    val x4078 = StreamController(name="x4078",parent=x4148) { implicit CU => 
      val ctr38 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4049 = CounterChain(name = "x4049", ctr38)
    }
    val x4066 = Pipeline(name="x4066",parent=x4078) { implicit CU => 
      val x4054 = CU.temp
      val x4055 = CU.temp
      val x4056 = CU.temp
      val x4057 = CU.temp
      val x3700_x4053 =  ScalarBuffer().wtPort(x3700_argin)
      val x4052 =  ScalarBuffer().wtPort(x4052_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4049 = CounterChain.copy("x4078", "x4049")
      val x4045 = CounterChain.copy("x4148", "x4045")
      val ctr39 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4066_unit = CounterChain(name = "x4066_unit", ctr39)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4049(0))), op=FixAdd, results=List(x4054))
      Stage(operands=List(x4054, CU.load(x3700_x4053)), op=FixMul, results=List(x4055))
      Stage(operands=List(x4055, CU.ctr(x4045(0))), op=FixAdd, results=List(x4056))
      Stage(operands=List(x4056, Const(4)), op=FixMul, results=List(x4057))
      Stage(operands=List(x4057, CU.load(x4052)), op=FixAdd, results=List(CU.scalarOut(x4050_b4547_x4065_b4549_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4050_b4548_x4065_b4550_s)))
    }
    val x4067 = MemoryController(name="x4067",parent=x4078,offchip=x3706_oc, mctpe=TileLoad) { implicit CU => 
      val x4050_b4548_x4067 =  ScalarFIFO(name="size",size=1).wtPort(x4050_b4548_x4065_b4550_s)
      val x4050_b4547_x4067 =  ScalarFIFO(name="offset",size=1).wtPort(x4050_b4547_x4065_b4549_s)
      CU.newVout("data", x4051_x4067_data_v)
    }
    val x4077 = Pipeline(name="x4077",parent=x4078) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4069 = CounterChain(name = "x4069", ctr40)
      var stage: List[Stage] = Nil
    }
    val x4109 = StreamController(name="x4109",parent=x4148) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4080 = CounterChain(name = "x4080", ctr41)
    }
    val x4097 = Pipeline(name="x4097",parent=x4109) { implicit CU => 
      val x4088 = CU.temp
      val x4086 = CU.temp
      val x4087 = CU.temp
      val x4085 = CU.temp
      val x3699_x4084 =  ScalarBuffer().wtPort(x3699_argin)
      val x4083 =  ScalarBuffer().wtPort(x4083_argin)
      val x4045 = CounterChain.copy("x4148", "x4045")
      val x4080 = CounterChain.copy("x4109", "x4080")
      val x3719 = CounterChain.copy("x4457", "x3719")
      val ctr42 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4097_unit = CounterChain(name = "x4097_unit", ctr42)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4045(0)), CU.ctr(x4080(0))), op=FixAdd, results=List(x4085))
      Stage(operands=List(x4085, CU.load(x3699_x4084)), op=FixMul, results=List(x4086))
      Stage(operands=List(x4086, CU.ctr(x3719(1))), op=FixAdd, results=List(x4087))
      Stage(operands=List(x4087, Const(4)), op=FixMul, results=List(x4088))
      Stage(operands=List(x4088, CU.load(x4083)), op=FixAdd, results=List(CU.scalarOut(x4081_b4553_x4096_b4555_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4081_b4554_x4096_b4556_s)))
    }
    val x4098 = MemoryController(name="x4098",parent=x4109,offchip=x3709_oc, mctpe=TileLoad) { implicit CU => 
      val x4081_b4554_x4098 =  ScalarFIFO(name="size",size=1).wtPort(x4081_b4554_x4096_b4556_s)
      val x4081_b4553_x4098 =  ScalarFIFO(name="offset",size=1).wtPort(x4081_b4553_x4096_b4555_s)
      CU.newVout("data", x4082_x4098_data_v)
    }
    val x4108 = Pipeline(name="x4108",parent=x4109) { implicit CU => 
      val ctr43 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4100 = CounterChain(name = "x4100", ctr43)
      var stage: List[Stage] = Nil
    }
    val x4147 = MetaPipeline(name="x4147",parent=x4148) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr45 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4113 = CounterChain(name = "x4113", ctr44, ctr45)
    }
    val x4135 = Pipeline(name="x4135",parent=x4147) { implicit CU => 
      val x4047_x4123 =  VectorFIFO(size=1).wtPort(x4047_x4123_x4135_v)
      val x4046_x4122 =  VectorFIFO(size=1).wtPort(x4046_x4122_x4135_v)
      val ctr46 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4116 = CounterChain(name = "x4116", ctr46)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4046_x4122), CU.load(x4047_x4123)), op=FixMul, results=List(CU.reduce))
      val (_, rr1915) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1915), op=Bypass, results=List(CU.scalarOut(x4114_x4133_s)))
    }
    val x4146 = Pipeline(name="x4146",parent=x4147) { implicit CU => 
      val x4143 = CU.temp
      val x4142 = CU.temp
      val x4114_x4141 =  ScalarBuffer().wtPort(x4114_x4133_s)
      val x3723_x4140 =  VectorFIFO(size=1).wtPort(x3723_x4140_x4146_v)
      val x4045 = CounterChain.copy("x4148", "x4045")
      val ctr47 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4146_unit = CounterChain(name = "x4146_unit", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4045(0)), Const(0)), op=FixEql, results=List(x4142))
      Stage(operands=List(x4142, Const(0), CU.load(x3723_x4140)), op=Mux, results=List(x4143))
      Stage(operands=List(x4143, CU.load(x4114_x4141)), op=FixAdd, results=List(CU.vecOut(x3723_x4145_v)))
    }
    val x4254 = MetaPipeline(name="x4254",parent=x4457) { implicit CU => 
      val x3700_x4149 =  ScalarBuffer().wtPort(x3700_argin)
      val ctr48 = Counter(min=Const(0), max=x3700_x4149.load, step=Const(48), par=1) // Counter
      val x4151 = CounterChain(name = "x4151", ctr48)
    }
    val x4152_dsp0 = MemoryPipeline(name="x4152_dsp0",parent="x4254") { implicit CU => 
      val b4579 = CU.temp
      val b4571 = CU.temp
      val x4182_x4182 =  VectorFIFO(size=1).wtPort(x4157_x4173_data_v)
      val x4175 = CounterChain.copy("x4183", "x4175")
      val x4155 = CounterChain.copy("x4184", "x4155")
      val x4219 = CounterChain.copy("x4253", "x4219")
      val x4222 = CounterChain.copy("x4241", "x4222")
      val x4152_x4228 =  SRAM(size=768,banking = Strided(1)).wtPort(x4182_x4182.readPort).rdPort(x4152_x4228_x4241_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4155(0)), Const(48)), op=FixMul, results=List(b4571))
      WAStage(operands=List(b4571, CU.ctr(x4175(0))), op=FixAdd, results=List(x4152_x4228.writeAddr))
      RAStage(operands=List(CU.ctr(x4219(0)), Const(48)), op=FixMul, results=List(b4579))
      RAStage(operands=List(b4579, CU.ctr(x4222(0))), op=FixAdd, results=List(x4152_x4228.readAddr))
    }
    val x4153_dsp0 = MemoryPipeline(name="x4153_dsp0",parent="x4254") { implicit CU => 
      val b4581 = CU.temp
      val b4577 = CU.temp
      val x4213_x4213 =  VectorFIFO(size=1).wtPort(x4188_x4204_data_v)
      val x4186 = CounterChain.copy("x4215", "x4186")
      val x4206 = CounterChain.copy("x4214", "x4206")
      val x4219 = CounterChain.copy("x4253", "x4219")
      val x4222 = CounterChain.copy("x4241", "x4222")
      val x4153_x4229 =  SRAM(size=2304,banking = Strided(1)).wtPort(x4213_x4213.readPort).rdPort(x4153_x4229_x4241_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4186(0)), Const(48)), op=FixMul, results=List(b4577))
      WAStage(operands=List(b4577, CU.ctr(x4206(0))), op=FixAdd, results=List(x4153_x4229.writeAddr))
      RAStage(operands=List(CU.ctr(x4222(0)), Const(48)), op=FixMul, results=List(b4581))
      RAStage(operands=List(b4581, CU.ctr(x4219(1))), op=FixAdd, results=List(x4153_x4229.readAddr))
    }
    val x4184 = StreamController(name="x4184",parent=x4254) { implicit CU => 
      val ctr49 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4155 = CounterChain(name = "x4155", ctr49)
    }
    val x4172 = Pipeline(name="x4172",parent=x4184) { implicit CU => 
      val x4163 = CU.temp
      val x4161 = CU.temp
      val x4162 = CU.temp
      val x4160 = CU.temp
      val x4158 =  ScalarBuffer().wtPort(x4158_argin)
      val x3700_x4159 =  ScalarBuffer().wtPort(x3700_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4155 = CounterChain.copy("x4184", "x4155")
      val x4151 = CounterChain.copy("x4254", "x4151")
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4172_unit = CounterChain(name = "x4172_unit", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4155(0))), op=FixAdd, results=List(x4160))
      Stage(operands=List(x4160, CU.load(x3700_x4159)), op=FixMul, results=List(x4161))
      Stage(operands=List(x4161, CU.ctr(x4151(0))), op=FixAdd, results=List(x4162))
      Stage(operands=List(x4162, Const(4)), op=FixMul, results=List(x4163))
      Stage(operands=List(x4163, CU.load(x4158)), op=FixAdd, results=List(CU.scalarOut(x4156_b4567_x4171_b4569_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4156_b4568_x4171_b4570_s)))
    }
    val x4173 = MemoryController(name="x4173",parent=x4184,offchip=x3706_oc, mctpe=TileLoad) { implicit CU => 
      val x4156_b4568_x4173 =  ScalarFIFO(name="size",size=1).wtPort(x4156_b4568_x4171_b4570_s)
      val x4156_b4567_x4173 =  ScalarFIFO(name="offset",size=1).wtPort(x4156_b4567_x4171_b4569_s)
      CU.newVout("data", x4157_x4173_data_v)
    }
    val x4183 = Pipeline(name="x4183",parent=x4184) { implicit CU => 
      val ctr51 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4175 = CounterChain(name = "x4175", ctr51)
      var stage: List[Stage] = Nil
    }
    val x4215 = StreamController(name="x4215",parent=x4254) { implicit CU => 
      val ctr52 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4186 = CounterChain(name = "x4186", ctr52)
    }
    val x4203 = Pipeline(name="x4203",parent=x4215) { implicit CU => 
      val x4194 = CU.temp
      val x4192 = CU.temp
      val x4191 = CU.temp
      val x4193 = CU.temp
      val x3699_x4190 =  ScalarBuffer().wtPort(x3699_argin)
      val x4189 =  ScalarBuffer().wtPort(x4189_argin)
      val x4151 = CounterChain.copy("x4254", "x4151")
      val x4186 = CounterChain.copy("x4215", "x4186")
      val x3719 = CounterChain.copy("x4457", "x3719")
      val ctr53 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4203_unit = CounterChain(name = "x4203_unit", ctr53)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4151(0)), CU.ctr(x4186(0))), op=FixAdd, results=List(x4191))
      Stage(operands=List(x4191, CU.load(x3699_x4190)), op=FixMul, results=List(x4192))
      Stage(operands=List(x4192, CU.ctr(x3719(1))), op=FixAdd, results=List(x4193))
      Stage(operands=List(x4193, Const(4)), op=FixMul, results=List(x4194))
      Stage(operands=List(x4194, CU.load(x4189)), op=FixAdd, results=List(CU.scalarOut(x4187_b4573_x4202_b4575_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4187_b4574_x4202_b4576_s)))
    }
    val x4204 = MemoryController(name="x4204",parent=x4215,offchip=x3709_oc, mctpe=TileLoad) { implicit CU => 
      val x4187_b4574_x4204 =  ScalarFIFO(name="size",size=1).wtPort(x4187_b4574_x4202_b4576_s)
      val x4187_b4573_x4204 =  ScalarFIFO(name="offset",size=1).wtPort(x4187_b4573_x4202_b4575_s)
      CU.newVout("data", x4188_x4204_data_v)
    }
    val x4214 = Pipeline(name="x4214",parent=x4215) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4206 = CounterChain(name = "x4206", ctr54)
      var stage: List[Stage] = Nil
    }
    val x4253 = MetaPipeline(name="x4253",parent=x4254) { implicit CU => 
      val ctr55 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val ctr56 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4219 = CounterChain(name = "x4219", ctr55, ctr56)
    }
    val x4241 = Pipeline(name="x4241",parent=x4253) { implicit CU => 
      val x4153_x4229 =  VectorFIFO(size=1).wtPort(x4153_x4229_x4241_v)
      val x4152_x4228 =  VectorFIFO(size=1).wtPort(x4152_x4228_x4241_v)
      val ctr57 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4222 = CounterChain(name = "x4222", ctr57)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4152_x4228), CU.load(x4153_x4229)), op=FixMul, results=List(CU.reduce))
      val (_, rr1966) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr1966), op=Bypass, results=List(CU.scalarOut(x4220_x4239_s)))
    }
    val x4252 = Pipeline(name="x4252",parent=x4253) { implicit CU => 
      val x4249 = CU.temp
      val x4248 = CU.temp
      val x3724_x4246 =  VectorFIFO(size=1).wtPort(x3724_x4246_x4252_v)
      val x4220_x4247 =  ScalarBuffer().wtPort(x4220_x4239_s)
      val x4151 = CounterChain.copy("x4254", "x4151")
      val ctr58 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4252_unit = CounterChain(name = "x4252_unit", ctr58)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4151(0)), Const(0)), op=FixEql, results=List(x4248))
      Stage(operands=List(x4248, Const(0), CU.load(x3724_x4246)), op=Mux, results=List(x4249))
      Stage(operands=List(x4249, CU.load(x4220_x4247)), op=FixAdd, results=List(CU.vecOut(x3724_x4251_v)))
    }
    val x4295 = StreamController(name="x4295",parent=x4457) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4257 = CounterChain(name = "x4257", ctr59)
    }
    val x4285 = Sequential(name="x4285",parent=x4295) { implicit CU => 
      val ctr60 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4285_unit = CounterChain(name = "x4285_unit", ctr60)
    }
    val x4274 = Pipeline(name="x4274",parent=x4285) { implicit CU => 
      val x4266 = CU.temp
      val x4265 = CU.temp
      val x4263 = CU.temp
      val x4264 = CU.temp
      val x3699_x4262 =  ScalarBuffer().wtPort(x3699_argin)
      val x4261 =  ScalarBuffer().wtPort(x4261_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4257 = CounterChain.copy("x4295", "x4257")
      val ctr61 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4274_unit = CounterChain(name = "x4274_unit", ctr61)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4257(0))), op=FixAdd, results=List(x4263))
      Stage(operands=List(x4263, CU.load(x3699_x4262)), op=FixMul, results=List(x4264))
      Stage(operands=List(x4264, CU.ctr(x3719(1))), op=FixAdd, results=List(x4265))
      Stage(operands=List(x4265, Const(4)), op=FixMul, results=List(x4266))
      Stage(operands=List(x4266, CU.load(x4261)), op=FixAdd, results=List(CU.scalarOut(x4258_b4587_x4273_b4589_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4258_b4588_x4273_b4590_s)))
    }
    val x4284 = Pipeline(name="x4284",parent=x4285) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4276 = CounterChain(name = "x4276", ctr62)
      var stage: List[Stage] = Nil
    }
    val x4286 = MemoryController(name="x4286",parent=x4295,offchip=x3712_oc, mctpe=TileStore) { implicit CU => 
      val x4258_b4587_x4286 =  ScalarFIFO(name="offset",size=1).wtPort(x4258_b4587_x4273_b4589_s)
      val x4258_b4588_x4286 =  ScalarFIFO(name="size",size=1).wtPort(x4258_b4588_x4273_b4590_s)
      val x4259_x4286 =  VectorFIFO(name="data",size=1).wtPort(x3720_x4280_x4284_v)
    }
    val x4335 = StreamController(name="x4335",parent=x4457) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4297 = CounterChain(name = "x4297", ctr65)
    }
    val x4325 = Sequential(name="x4325",parent=x4335) { implicit CU => 
      val ctr66 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4325_unit = CounterChain(name = "x4325_unit", ctr66)
    }
    val x4314 = Pipeline(name="x4314",parent=x4325) { implicit CU => 
      val x4305 = CU.temp
      val x4304 = CU.temp
      val x4306 = CU.temp
      val x4303 = CU.temp
      val x3699_x4302 =  ScalarBuffer().wtPort(x3699_argin)
      val x4301 =  ScalarBuffer().wtPort(x4301_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4297 = CounterChain.copy("x4335", "x4297")
      val ctr67 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4314_unit = CounterChain(name = "x4314_unit", ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4297(0))), op=FixAdd, results=List(x4303))
      Stage(operands=List(x4303, CU.load(x3699_x4302)), op=FixMul, results=List(x4304))
      Stage(operands=List(x4304, CU.ctr(x3719(1))), op=FixAdd, results=List(x4305))
      Stage(operands=List(x4305, Const(4)), op=FixMul, results=List(x4306))
      Stage(operands=List(x4306, CU.load(x4301)), op=FixAdd, results=List(CU.scalarOut(x4298_b4593_x4313_b4595_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4298_b4594_x4313_b4596_s)))
    }
    val x4324 = Pipeline(name="x4324",parent=x4325) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4316 = CounterChain(name = "x4316", ctr68)
      var stage: List[Stage] = Nil
    }
    val x4326 = MemoryController(name="x4326",parent=x4335,offchip=x3712_oc, mctpe=TileStore) { implicit CU => 
      val x4298_b4593_x4326 =  ScalarFIFO(name="offset",size=1).wtPort(x4298_b4593_x4313_b4595_s)
      val x4299_x4326 =  VectorFIFO(name="data",size=1).wtPort(x3721_x4320_x4324_v)
      val x4298_b4594_x4326 =  ScalarFIFO(name="size",size=1).wtPort(x4298_b4594_x4313_b4596_s)
    }
    val x4375 = StreamController(name="x4375",parent=x4457) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4337 = CounterChain(name = "x4337", ctr71)
    }
    val x4365 = Sequential(name="x4365",parent=x4375) { implicit CU => 
      val ctr72 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4365_unit = CounterChain(name = "x4365_unit", ctr72)
    }
    val x4354 = Pipeline(name="x4354",parent=x4365) { implicit CU => 
      val x4346 = CU.temp
      val x4345 = CU.temp
      val x4344 = CU.temp
      val x4343 = CU.temp
      val x3699_x4342 =  ScalarBuffer().wtPort(x3699_argin)
      val x4341 =  ScalarBuffer().wtPort(x4341_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4337 = CounterChain.copy("x4375", "x4337")
      val ctr73 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4354_unit = CounterChain(name = "x4354_unit", ctr73)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4337(0))), op=FixAdd, results=List(x4343))
      Stage(operands=List(x4343, CU.load(x3699_x4342)), op=FixMul, results=List(x4344))
      Stage(operands=List(x4344, CU.ctr(x3719(1))), op=FixAdd, results=List(x4345))
      Stage(operands=List(x4345, Const(4)), op=FixMul, results=List(x4346))
      Stage(operands=List(x4346, CU.load(x4341)), op=FixAdd, results=List(CU.scalarOut(x4338_b4599_x4353_b4601_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4338_b4600_x4353_b4602_s)))
    }
    val x4364 = Pipeline(name="x4364",parent=x4365) { implicit CU => 
      val ctr74 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4356 = CounterChain(name = "x4356", ctr74)
      var stage: List[Stage] = Nil
    }
    val x4366 = MemoryController(name="x4366",parent=x4375,offchip=x3712_oc, mctpe=TileStore) { implicit CU => 
      val x4338_b4599_x4366 =  ScalarFIFO(name="offset",size=1).wtPort(x4338_b4599_x4353_b4601_s)
      val x4339_x4366 =  VectorFIFO(name="data",size=1).wtPort(x3722_x4360_x4364_v)
      val x4338_b4600_x4366 =  ScalarFIFO(name="size",size=1).wtPort(x4338_b4600_x4353_b4602_s)
    }
    val x4415 = StreamController(name="x4415",parent=x4457) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4377 = CounterChain(name = "x4377", ctr77)
    }
    val x4405 = Sequential(name="x4405",parent=x4415) { implicit CU => 
      val ctr78 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4405_unit = CounterChain(name = "x4405_unit", ctr78)
    }
    val x4394 = Pipeline(name="x4394",parent=x4405) { implicit CU => 
      val x4384 = CU.temp
      val x4383 = CU.temp
      val x4385 = CU.temp
      val x4386 = CU.temp
      val x3699_x4382 =  ScalarBuffer().wtPort(x3699_argin)
      val x4381 =  ScalarBuffer().wtPort(x4381_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4377 = CounterChain.copy("x4415", "x4377")
      val ctr79 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4394_unit = CounterChain(name = "x4394_unit", ctr79)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4377(0))), op=FixAdd, results=List(x4383))
      Stage(operands=List(x4383, CU.load(x3699_x4382)), op=FixMul, results=List(x4384))
      Stage(operands=List(x4384, CU.ctr(x3719(1))), op=FixAdd, results=List(x4385))
      Stage(operands=List(x4385, Const(4)), op=FixMul, results=List(x4386))
      Stage(operands=List(x4386, CU.load(x4381)), op=FixAdd, results=List(CU.scalarOut(x4378_b4605_x4393_b4607_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4378_b4606_x4393_b4608_s)))
    }
    val x4404 = Pipeline(name="x4404",parent=x4405) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4396 = CounterChain(name = "x4396", ctr80)
      var stage: List[Stage] = Nil
    }
    val x4406 = MemoryController(name="x4406",parent=x4415,offchip=x3712_oc, mctpe=TileStore) { implicit CU => 
      val x4378_b4605_x4406 =  ScalarFIFO(name="offset",size=1).wtPort(x4378_b4605_x4393_b4607_s)
      val x4379_x4406 =  VectorFIFO(name="data",size=1).wtPort(x3723_x4400_x4404_v)
      val x4378_b4606_x4406 =  ScalarFIFO(name="size",size=1).wtPort(x4378_b4606_x4393_b4608_s)
    }
    val x4455 = StreamController(name="x4455",parent=x4457) { implicit CU => 
      val ctr83 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4417 = CounterChain(name = "x4417", ctr83)
    }
    val x4445 = Sequential(name="x4445",parent=x4455) { implicit CU => 
      val ctr84 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4445_unit = CounterChain(name = "x4445_unit", ctr84)
    }
    val x4434 = Pipeline(name="x4434",parent=x4445) { implicit CU => 
      val x4426 = CU.temp
      val x4424 = CU.temp
      val x4423 = CU.temp
      val x4425 = CU.temp
      val x3699_x4422 =  ScalarBuffer().wtPort(x3699_argin)
      val x4421 =  ScalarBuffer().wtPort(x4421_argin)
      val x3719 = CounterChain.copy("x4457", "x3719")
      val x4417 = CounterChain.copy("x4455", "x4417")
      val ctr85 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4434_unit = CounterChain(name = "x4434_unit", ctr85)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3719(0)), CU.ctr(x4417(0))), op=FixAdd, results=List(x4423))
      Stage(operands=List(x4423, CU.load(x3699_x4422)), op=FixMul, results=List(x4424))
      Stage(operands=List(x4424, CU.ctr(x3719(1))), op=FixAdd, results=List(x4425))
      Stage(operands=List(x4425, Const(4)), op=FixMul, results=List(x4426))
      Stage(operands=List(x4426, CU.load(x4421)), op=FixAdd, results=List(CU.scalarOut(x4418_b4611_x4433_b4613_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4418_b4612_x4433_b4614_s)))
    }
    val x4444 = Pipeline(name="x4444",parent=x4445) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4436 = CounterChain(name = "x4436", ctr86)
      var stage: List[Stage] = Nil
    }
    val x4446 = MemoryController(name="x4446",parent=x4455,offchip=x3712_oc, mctpe=TileStore) { implicit CU => 
      val x4419_x4446 =  VectorFIFO(name="data",size=1).wtPort(x3724_x4440_x4444_v)
      val x4418_b4612_x4446 =  ScalarFIFO(name="size",size=1).wtPort(x4418_b4612_x4433_b4614_s)
      val x4418_b4611_x4446 =  ScalarFIFO(name="offset",size=1).wtPort(x4418_b4611_x4433_b4613_s)
    }
    
  }
}
