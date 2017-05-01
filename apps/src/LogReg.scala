import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object LogReg extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4031_x4450_v = Vector("x4031_x4450")
    val bus_17863_s = Scalar("bus_17863")
    val x4025_x4483_x4556_v = Vector("x4025_x4483_x4556")
    val x3947_x4564_x4570_v = Vector("x3947_x4564_x4570")
    val x3957_x4410_x4415_v = Vector("x3957_x4410_x4415")
    val x4021_x4329_s = Scalar("x4021_x4329")
    val x3947_x4222_x4233_v = Vector("x3947_x4222_x4233")
    val x4033_x4474_v = Vector("x4033_x4474")
    val x4015_x4257_s = Scalar("x4015_x4257")
    val x3958_x4274_x4282_v = Vector("x3958_x4274_x4282")
    val x4016_x4269_s = Scalar("x4016_x4269")
    val x3988_b4668_x3998_b4670_s = Scalar("x3988_b4668_x3998_b4670")
    val x3957_x4458_x4463_v = Vector("x3957_x4458_x4463")
    val x4020_x4317_s = Scalar("x4020_x4317")
    val x3953_x4563_x4570_v = Vector("x3953_x4563_x4570")
    val x4017_x4281_s = Scalar("x4017_x4281")
    val x3947_x4089_x4100_v = Vector("x3947_x4089_x4100")
    val x3947_x4146_x4157_v = Vector("x3947_x4146_x4157")
    val x4041_x4193_s = Scalar("x4041_x4193")
    val x3947_x4587_x4591_v = Vector("x3947_x4587_x4591")
    val x3957_x4107_x4119_v = Vector("x3957_x4107_x4119")
    val x4026_x4390_v = Vector("x4026_x4390")
    val x3953_x4555_v = Vector("x3953_x4555")
    val x4575_b4711_x4583_b4713_s = Scalar("x4575_b4711_x4583_b4713")
    val x3958_x4334_x4342_v = Vector("x3958_x4334_x4342")
    val x3940_oc = OffChip("x3940")
    val x3961_b4662_x3974_b4664_s = Scalar("x3961_b4662_x3974_b4664")
    val x3947_x4184_x4195_v = Vector("x3947_x4184_x4195")
    val x4030_x4488_x4556_v = Vector("x4030_x4488_x4556")
    val x3957_x4069_x4081_v = Vector("x3957_x4069_x4081")
    val x4040_x4174_s = Scalar("x4040_x4174")
    val x4030_x4438_v = Vector("x4030_x4438")
    val x4036_x4098_s = Scalar("x4036_x4098")
    val x3958_x4322_x4330_v = Vector("x3958_x4322_x4330")
    val x3957_x4386_x4391_v = Vector("x3957_x4386_x4391")
    val x4038_x4136_s = Scalar("x4038_x4136")
    val x3958_x4262_x4270_v = Vector("x3958_x4262_x4270")
    val x4029_x4426_v = Vector("x4029_x4426")
    val x3957_x4164_x4176_v = Vector("x3957_x4164_x4176")
    val x3957_x4362_x4367_v = Vector("x3957_x4362_x4367")
    val x4028_x4414_v = Vector("x4028_x4414")
    val x4039_x4155_s = Scalar("x4039_x4155")
    val x3957_x4202_x4214_v = Vector("x3957_x4202_x4214")
    val x3957_x4446_x4451_v = Vector("x3957_x4446_x4451")
    val x4029_x4487_x4556_v = Vector("x4029_x4487_x4556")
    val x3988_b4667_x3998_b4669_s = Scalar("x3988_b4667_x3998_b4669")
    val x4032_x4462_v = Vector("x4032_x4462")
    val x4024_x4482_x4556_v = Vector("x4024_x4482_x4556")
    val x3947_x4108_x4119_v = Vector("x3947_x4108_x4119")
    val x4025_x4378_v = Vector("x4025_x4378")
    val bus_17844_s = Scalar("bus_17844")
    val x4031_x4489_x4556_v = Vector("x4031_x4489_x4556")
    val x3957_x4422_x4427_v = Vector("x3957_x4422_x4427")
    val x4035_x4079_s = Scalar("x4035_x4079")
    val x3957_x4374_x4379_v = Vector("x3957_x4374_x4379")
    val x3953_x4492_x4556_v = Vector("x3953_x4492_x4556")
    val x4578_argin = ArgIn("x4578")
    val x3957_x4126_x4138_v = Vector("x3957_x4126_x4138")
    val x3958_x4310_x4318_v = Vector("x3958_x4310_x4318")
    val x3990_argin = ArgIn("x3990")
    val x4022_x4341_s = Scalar("x4022_x4341")
    val x4026_x4484_x4556_v = Vector("x4026_x4484_x4556")
    val x3947_x4203_x4214_v = Vector("x3947_x4203_x4214")
    val x3957_x4221_x4233_v = Vector("x3957_x4221_x4233")
    val x4027_x4485_x4556_v = Vector("x4027_x4485_x4556")
    val x3957_x4145_x4157_v = Vector("x3957_x4145_x4157")
    val x3989_x4000_data_v = Vector("x3989_x4000_data")
    val x4028_x4486_x4556_v = Vector("x4028_x4486_x4556")
    val x4014_x4245_s = Scalar("x4014_x4245")
    val x4018_x4293_s = Scalar("x4018_x4293")
    val x3958_x4238_x4246_v = Vector("x3958_x4238_x4246")
    val x4027_x4402_v = Vector("x4027_x4402")
    val x3962_x3976_data_v = Vector("x3962_x3976_data")
    val x4023_x4353_s = Scalar("x4023_x4353")
    val x3947_x4070_x4081_v = Vector("x3947_x4070_x4081")
    val x4024_x4366_v = Vector("x4024_x4366")
    val x3958_x4286_x4294_v = Vector("x3958_x4286_x4294")
    val x3941_oc = OffChip("x3941")
    val x3933_argin = ArgIn("x3933")
    val x3947_x4127_x4138_v = Vector("x3947_x4127_x4138")
    val x3957_x4088_x4100_v = Vector("x3957_x4088_x4100")
    val x4043_x4231_s = Scalar("x4043_x4231")
    val x4032_x4490_x4556_v = Vector("x4032_x4490_x4556")
    val x3938_oc = OffChip("x3938")
    val x3963_argin = ArgIn("x3963")
    val x3957_x4470_x4475_v = Vector("x3957_x4470_x4475")
    val x3957_x4183_x4195_v = Vector("x3957_x4183_x4195")
    val x4037_x4117_s = Scalar("x4037_x4117")
    val x3958_x4346_x4354_v = Vector("x3958_x4346_x4354")
    val x4575_b4712_x4583_b4714_s = Scalar("x4575_b4712_x4583_b4714")
    val x3957_x4050_x4062_v = Vector("x3957_x4050_x4062")
    val x3947_x4165_x4176_v = Vector("x3947_x4165_x4176")
    val x3947_x4569_v = Vector("x3947_x4569")
    val x3957_x4434_x4439_v = Vector("x3957_x4434_x4439")
    val x3947_x4051_x4062_v = Vector("x3947_x4051_x4062")
    val x3934_argin = ArgIn("x3934")
    val x3958_x4250_x4258_v = Vector("x3958_x4250_x4258")
    val x4034_x4060_s = Scalar("x4034_x4060")
    val x3958_x4298_x4306_v = Vector("x3958_x4298_x4306")
    val x4019_x4305_s = Scalar("x4019_x4305")
    val x4042_x4212_s = Scalar("x4042_x4212")
    val x3961_b4661_x3974_b4663_s = Scalar("x3961_b4661_x3974_b4663")
    val x4033_x4491_x4556_v = Vector("x4033_x4491_x4556")
    val x3957_x4398_x4403_v = Vector("x3957_x4398_x4403")
    val x4600 = Sequential(name="x4600",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4600_unit = CounterChain(name = "x4600_unit", ctr1)
    }
    val x3947_dsp0 = MemoryPipeline(name="x3947_dsp0",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x4586 = CounterChain.copy("x4591", "x4586")
      val x3947_x4587 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4587_x4591_v).rdAddr(x4586(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp1 = MemoryPipeline(name="x3947_dsp1",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4564 =  SRAM(size=192,banking = NoBanking()).wtPort(x4569_x4569.readPort).rdPort(x3947_x4564_x4570_v).rdAddr(x4561(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp2 = MemoryPipeline(name="x3947_dsp2",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4216 = CounterChain.copy("x4233_0", "x4216")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4222 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4222_x4233_v).rdAddr(x4216(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp3 = MemoryPipeline(name="x3947_dsp3",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x4197 = CounterChain.copy("x4214_0", "x4197")
      val x3947_x4203 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4203_x4214_v).rdAddr(x4197(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp4 = MemoryPipeline(name="x3947_dsp4",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4178 = CounterChain.copy("x4195_0", "x4178")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4184 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4184_x4195_v).rdAddr(x4178(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp5 = MemoryPipeline(name="x3947_dsp5",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4159 = CounterChain.copy("x4176_0", "x4159")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4165 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4165_x4176_v).rdAddr(x4159(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp6 = MemoryPipeline(name="x3947_dsp6",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x4140 = CounterChain.copy("x4157_0", "x4140")
      val x3947_x4146 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4146_x4157_v).rdAddr(x4140(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp7 = MemoryPipeline(name="x3947_dsp7",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4121 = CounterChain.copy("x4138_0", "x4121")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4127 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4127_x4138_v).rdAddr(x4121(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp8 = MemoryPipeline(name="x3947_dsp8",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x4102 = CounterChain.copy("x4119_0", "x4102")
      val x3947_x4108 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4108_x4119_v).rdAddr(x4102(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp9 = MemoryPipeline(name="x3947_dsp9",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4083 = CounterChain.copy("x4100_0", "x4083")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4089 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4089_x4100_v).rdAddr(x4083(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp10 = MemoryPipeline(name="x3947_dsp10",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4064 = CounterChain.copy("x4081_0", "x4064")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3947_x4070 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4070_x4081_v).rdAddr(x4064(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x3947_dsp11 = MemoryPipeline(name="x3947_dsp11",parent="x4573") { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x3947_x4569_v)
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x4045 = CounterChain.copy("x4062_0", "x4045")
      val x3947_x4051 =  SRAM(size=192,banking = Strided(1)).wtPort(x4569_x4569.readPort).rdPort(x3947_x4051_x4062_v).rdAddr(x4045(0)).wtAddr(x4561(0))
      var stage: List[Stage] = Nil
    }
    val x4574 = Sequential(name="x4574",parent=x4600) { implicit CU => 
      val x3933_x3948 =  ScalarBuffer().wtPort(x3933_argin)
      val ctr2 = Counter(min=Const(0), max=x3933_x3948.load, step=Const(1), par=1) // Counter
      val x3950 = CounterChain(name = "x3950", ctr2)
    }
    val x4573 = Sequential(name="x4573",parent=x4574) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3952 = CounterChain(name = "x3952", ctr3)
    }
    val x3953_dsp0 = MemoryPipeline(name="x3953_dsp0",parent="x4558") { implicit CU => 
      val x4555_x4555 =  VectorFIFO(size=1).wtPort(x3953_x4555_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4561 = CounterChain.copy("x4570_0", "x4561")
      val x3953_x4563 =  SRAM(size=192,banking = NoBanking()).wtPort(x4555_x4555.readPort).rdPort(x3953_x4563_x4570_v).rdAddr(x4561(0)).wtAddr(x4478(0))
      var stage: List[Stage] = Nil
    }
    val x3953_dsp1 = MemoryPipeline(name="x3953_dsp1",parent="x4558") { implicit CU => 
      val x4555_x4555 =  VectorFIFO(size=1).wtPort(x3953_x4555_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x3953_x4492 =  SRAM(size=192,banking = NoBanking()).wtPort(x4555_x4555.readPort).rdPort(x3953_x4492_x4556_v).rdAddr(x4478(0)).wtAddr(x4478(0))
      var stage: List[Stage] = Nil
    }
    val x4559 = MetaPipeline(name="x4559",parent=x4573) { implicit CU => 
      val x3934_x3954 =  ScalarBuffer().wtPort(x3934_argin)
      val ctr4 = Counter(min=Const(0), max=x3934_x3954.load, step=Const(16), par=1) // Counter
      val x3956 = CounterChain(name = "x3956", ctr4)
    }
    val x3957_dsp0 = MemoryPipeline(name="x3957_dsp0",parent="x4559") { implicit CU => 
      val b4709 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4465 = CounterChain.copy("x4475_0", "x4465")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4470 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4470_x4475_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4470.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4709))
      RAStage(operands=List(b4709, CU.ctr(x4465(0))), op=FixAdd, results=List(x3957_x4470.readAddr))
    }
    val x3957_dsp1 = MemoryPipeline(name="x3957_dsp1",parent="x4559") { implicit CU => 
      val b4707 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4453 = CounterChain.copy("x4463_0", "x4453")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4458 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4458_x4463_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4458.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4707))
      RAStage(operands=List(b4707, CU.ctr(x4453(0))), op=FixAdd, results=List(x3957_x4458.readAddr))
    }
    val x3957_dsp2 = MemoryPipeline(name="x3957_dsp2",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4705 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4441 = CounterChain.copy("x4451_0", "x4441")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4446 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4446_x4451_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4446.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4705))
      RAStage(operands=List(b4705, CU.ctr(x4441(0))), op=FixAdd, results=List(x3957_x4446.readAddr))
    }
    val x3957_dsp3 = MemoryPipeline(name="x3957_dsp3",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4703 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4429 = CounterChain.copy("x4439_0", "x4429")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4434 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4434_x4439_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4434.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4703))
      RAStage(operands=List(b4703, CU.ctr(x4429(0))), op=FixAdd, results=List(x3957_x4434.readAddr))
    }
    val x3957_dsp4 = MemoryPipeline(name="x3957_dsp4",parent="x4559") { implicit CU => 
      val b4701 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4417 = CounterChain.copy("x4427_0", "x4417")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4422 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4422_x4427_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4422.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4701))
      RAStage(operands=List(b4701, CU.ctr(x4417(0))), op=FixAdd, results=List(x3957_x4422.readAddr))
    }
    val x3957_dsp5 = MemoryPipeline(name="x3957_dsp5",parent="x4559") { implicit CU => 
      val b4699 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4405 = CounterChain.copy("x4415_0", "x4405")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4410 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4410_x4415_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4410.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4699))
      RAStage(operands=List(b4699, CU.ctr(x4405(0))), op=FixAdd, results=List(x3957_x4410.readAddr))
    }
    val x3957_dsp6 = MemoryPipeline(name="x3957_dsp6",parent="x4559") { implicit CU => 
      val b4697 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4393 = CounterChain.copy("x4403_0", "x4393")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4398 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4398_x4403_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4398.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4697))
      RAStage(operands=List(b4697, CU.ctr(x4393(0))), op=FixAdd, results=List(x3957_x4398.readAddr))
    }
    val x3957_dsp7 = MemoryPipeline(name="x3957_dsp7",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4695 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4381 = CounterChain.copy("x4391_0", "x4381")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4386 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4386_x4391_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4386.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4695))
      RAStage(operands=List(b4695, CU.ctr(x4381(0))), op=FixAdd, results=List(x3957_x4386.readAddr))
    }
    val x3957_dsp8 = MemoryPipeline(name="x3957_dsp8",parent="x4559") { implicit CU => 
      val b4693 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4369 = CounterChain.copy("x4379_0", "x4369")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4374 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4374_x4379_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4374.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4693))
      RAStage(operands=List(b4693, CU.ctr(x4369(0))), op=FixAdd, results=List(x3957_x4374.readAddr))
    }
    val x3957_dsp9 = MemoryPipeline(name="x3957_dsp9",parent="x4559") { implicit CU => 
      val b4691 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x4357 = CounterChain.copy("x4367_0", "x4357")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4362 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4362_x4367_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4362.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4691))
      RAStage(operands=List(b4691, CU.ctr(x4357(0))), op=FixAdd, results=List(x3957_x4362.readAddr))
    }
    val x3957_dsp10 = MemoryPipeline(name="x3957_dsp10",parent="x4559") { implicit CU => 
      val b4689 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4216 = CounterChain.copy("x4233_0", "x4216")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4221 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4221_x4233_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4221.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4689))
      RAStage(operands=List(b4689, CU.ctr(x4216(0))), op=FixAdd, results=List(x3957_x4221.readAddr))
    }
    val x3957_dsp11 = MemoryPipeline(name="x3957_dsp11",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4687 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4197 = CounterChain.copy("x4214_0", "x4197")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4202 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4202_x4214_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4202.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4687))
      RAStage(operands=List(b4687, CU.ctr(x4197(0))), op=FixAdd, results=List(x3957_x4202.readAddr))
    }
    val x3957_dsp12 = MemoryPipeline(name="x3957_dsp12",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4685 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4178 = CounterChain.copy("x4195_0", "x4178")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4183 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4183_x4195_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4183.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4685))
      RAStage(operands=List(b4685, CU.ctr(x4178(0))), op=FixAdd, results=List(x3957_x4183.readAddr))
    }
    val x3957_dsp13 = MemoryPipeline(name="x3957_dsp13",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4683 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4159 = CounterChain.copy("x4176_0", "x4159")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4164 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4164_x4176_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4164.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4683))
      RAStage(operands=List(b4683, CU.ctr(x4159(0))), op=FixAdd, results=List(x3957_x4164.readAddr))
    }
    val x3957_dsp14 = MemoryPipeline(name="x3957_dsp14",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4681 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x4140 = CounterChain.copy("x4157_0", "x4140")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4145 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4145_x4157_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4145.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4681))
      RAStage(operands=List(b4681, CU.ctr(x4140(0))), op=FixAdd, results=List(x3957_x4145.readAddr))
    }
    val x3957_dsp15 = MemoryPipeline(name="x3957_dsp15",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4679 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4121 = CounterChain.copy("x4138_0", "x4121")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4126 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4126_x4138_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4126.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4679))
      RAStage(operands=List(b4679, CU.ctr(x4121(0))), op=FixAdd, results=List(x3957_x4126.readAddr))
    }
    val x3957_dsp16 = MemoryPipeline(name="x3957_dsp16",parent="x4559") { implicit CU => 
      val b4665 = CU.temp
      val b4677 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x4102 = CounterChain.copy("x4119_0", "x4102")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4107 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4107_x4119_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4107.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4677))
      RAStage(operands=List(b4677, CU.ctr(x4102(0))), op=FixAdd, results=List(x3957_x4107.readAddr))
    }
    val x3957_dsp17 = MemoryPipeline(name="x3957_dsp17",parent="x4559") { implicit CU => 
      val b4675 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4083 = CounterChain.copy("x4100_0", "x4083")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4088 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4088_x4100_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4088.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4675))
      RAStage(operands=List(b4675, CU.ctr(x4083(0))), op=FixAdd, results=List(x3957_x4088.readAddr))
    }
    val x3957_dsp18 = MemoryPipeline(name="x3957_dsp18",parent="x4559") { implicit CU => 
      val b4673 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4064 = CounterChain.copy("x4081_0", "x4064")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4069 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4069_x4081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4069.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4673))
      RAStage(operands=List(b4673, CU.ctr(x4064(0))), op=FixAdd, results=List(x3957_x4069.readAddr))
    }
    val x3957_dsp19 = MemoryPipeline(name="x3957_dsp19",parent="x4559") { implicit CU => 
      val b4671 = CU.temp
      val b4665 = CU.temp
      val x3985_x3985 =  VectorFIFO(size=1).wtPort(x3962_x3976_data_v)
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x4045 = CounterChain.copy("x4062_0", "x4045")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val x3978 = CounterChain.copy("x3986", "x3978")
      val x3957_x4050 =  SRAM(size=3072,banking = Strided(1)).wtPort(x3985_x3985.readPort).rdPort(x3957_x4050_x4062_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3960(0)), Const(192)), op=FixMul, results=List(b4665))
      WAStage(operands=List(b4665, CU.ctr(x3978(0))), op=FixAdd, results=List(x3957_x4050.writeAddr))
      RAStage(operands=List(CU.ctr(x4013(0)), Const(192)), op=FixMul, results=List(b4671))
      RAStage(operands=List(b4671, CU.ctr(x4045(0))), op=FixAdd, results=List(x3957_x4050.readAddr))
    }
    val x3958_dsp0 = MemoryPipeline(name="x3958_dsp0",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4346 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4346_x4354_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp1 = MemoryPipeline(name="x3958_dsp1",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4334 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4334_x4342_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp2 = MemoryPipeline(name="x3958_dsp2",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4322 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4322_x4330_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp3 = MemoryPipeline(name="x3958_dsp3",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4310 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4310_x4318_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp4 = MemoryPipeline(name="x3958_dsp4",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4298 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4298_x4306_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp5 = MemoryPipeline(name="x3958_dsp5",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4286 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4286_x4294_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp6 = MemoryPipeline(name="x3958_dsp6",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4274 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4274_x4282_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp7 = MemoryPipeline(name="x3958_dsp7",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4262 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4262_x4270_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp8 = MemoryPipeline(name="x3958_dsp8",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4250 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4250_x4258_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3958_dsp9 = MemoryPipeline(name="x3958_dsp9",parent="x4559") { implicit CU => 
      val x4008_x4008 =  VectorFIFO(size=1).wtPort(x3989_x4000_data_v)
      val x4002 = CounterChain.copy("x4009", "x4002")
      val x4013 = CounterChain.copy("x4558", "x4013")
      val x3958_x4238 =  SRAM(size=16,banking = Strided(1)).wtPort(x4008_x4008.readPort).rdPort(x3958_x4238_x4246_v).rdAddr(x4013(0)).wtAddr(x4002(0))
      var stage: List[Stage] = Nil
    }
    val x3987 = StreamController(name="x3987",parent=x4559) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x3960 = CounterChain(name = "x3960", ctr5)
    }
    val x3975_0 = Pipeline(name="x3975_0",parent=x3987) { implicit CU => 
      val x3966 = CU.temp
      val x3965 = CU.temp
      val x3964 = CU.temp
      val x3963 =  ScalarBuffer().wtPort(x3963_argin)
      val x3956 = CounterChain.copy("x4559", "x3956")
      val x3960 = CounterChain.copy("x3987", "x3960")
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3975_unit = CounterChain(name = "x3975_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3956(0)), CU.ctr(x3960(0))), op=FixAdd, results=List(x3964))
      Stage(operands=List(x3964, Const(192)), op=FixMul, results=List(x3965))
      Stage(operands=List(x3965, Const(4)), op=FixMul, results=List(x3966))
      Stage(operands=List(x3966, CU.load(x3963)), op=FixAdd, results=List(CU.scalarOut(x3961_b4661_x3974_b4663_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x3961_b4662_x3974_b4664_s)))
    }
    val x3976 = MemoryController(name="x3976",parent=x3987,offchip=x3938_oc, mctpe=TileLoad) { implicit CU => 
      val x3961_b4661_x3976 =  ScalarFIFO(name="offset",size=1).wtPort(x3961_b4661_x3974_b4663_s)
      val x3961_b4662_x3976 =  ScalarFIFO(name="size",size=1).wtPort(x3961_b4662_x3974_b4664_s)
      CU.newVout("data", x3962_x3976_data_v)
    }
    val x3986 = Pipeline(name="x3986",parent=x3987) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x3978 = CounterChain(name = "x3978", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4010 = StreamController(name="x4010",parent=x4559) { implicit CU => 
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4010_unit = CounterChain(name = "x4010_unit", ctr8)
    }
    val x3999_0 = Pipeline(name="x3999_0",parent=x4010) { implicit CU => 
      val x3991 = CU.temp
      val x3990 =  ScalarBuffer().wtPort(x3990_argin)
      val x3956 = CounterChain.copy("x4559", "x3956")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3999_unit = CounterChain(name = "x3999_unit", ctr9)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3956(0)), Const(4)), op=FixMul, results=List(x3991))
      Stage(operands=List(x3991, CU.load(x3990)), op=FixAdd, results=List(CU.scalarOut(x3988_b4667_x3998_b4669_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x3988_b4668_x3998_b4670_s)))
    }
    val x4000 = MemoryController(name="x4000",parent=x4010,offchip=x3940_oc, mctpe=TileLoad) { implicit CU => 
      val x3988_b4668_x4000 =  ScalarFIFO(name="size",size=1).wtPort(x3988_b4668_x3998_b4670_s)
      val x3988_b4667_x4000 =  ScalarFIFO(name="offset",size=1).wtPort(x3988_b4667_x3998_b4669_s)
      CU.newVout("data", x3989_x4000_data_v)
    }
    val x4009 = Pipeline(name="x4009",parent=x4010) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4002 = CounterChain(name = "x4002", ctr10)
      var stage: List[Stage] = Nil
    }
    val x4558 = MetaPipeline(name="x4558",parent=x4559) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=10) // Counter
      val x4013 = CounterChain(name = "x4013", ctr11)
    }
    val x4024_dsp0 = MemoryPipeline(name="x4024_dsp0",parent="x4558") { implicit CU => 
      val x4366_x4366 =  VectorFIFO(size=1).wtPort(x4024_x4366_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4357 = CounterChain.copy("x4367_0", "x4357")
      val x4024_x4482 =  SRAM(size=192,banking = Strided(1)).wtPort(x4366_x4366.readPort).rdPort(x4024_x4482_x4556_v).rdAddr(x4478(0)).wtAddr(x4357(0))
      var stage: List[Stage] = Nil
    }
    val x4025_dsp0 = MemoryPipeline(name="x4025_dsp0",parent="x4558") { implicit CU => 
      val x4378_x4378 =  VectorFIFO(size=1).wtPort(x4025_x4378_v)
      val x4369 = CounterChain.copy("x4379_0", "x4369")
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4025_x4483 =  SRAM(size=192,banking = Strided(1)).wtPort(x4378_x4378.readPort).rdPort(x4025_x4483_x4556_v).rdAddr(x4478(0)).wtAddr(x4369(0))
      var stage: List[Stage] = Nil
    }
    val x4026_dsp0 = MemoryPipeline(name="x4026_dsp0",parent="x4558") { implicit CU => 
      val x4390_x4390 =  VectorFIFO(size=1).wtPort(x4026_x4390_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4381 = CounterChain.copy("x4391_0", "x4381")
      val x4026_x4484 =  SRAM(size=192,banking = Strided(1)).wtPort(x4390_x4390.readPort).rdPort(x4026_x4484_x4556_v).rdAddr(x4478(0)).wtAddr(x4381(0))
      var stage: List[Stage] = Nil
    }
    val x4027_dsp0 = MemoryPipeline(name="x4027_dsp0",parent="x4558") { implicit CU => 
      val x4402_x4402 =  VectorFIFO(size=1).wtPort(x4027_x4402_v)
      val x4393 = CounterChain.copy("x4403_0", "x4393")
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4027_x4485 =  SRAM(size=192,banking = Strided(1)).wtPort(x4402_x4402.readPort).rdPort(x4027_x4485_x4556_v).rdAddr(x4478(0)).wtAddr(x4393(0))
      var stage: List[Stage] = Nil
    }
    val x4028_dsp0 = MemoryPipeline(name="x4028_dsp0",parent="x4558") { implicit CU => 
      val x4414_x4414 =  VectorFIFO(size=1).wtPort(x4028_x4414_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4405 = CounterChain.copy("x4415_0", "x4405")
      val x4028_x4486 =  SRAM(size=192,banking = Strided(1)).wtPort(x4414_x4414.readPort).rdPort(x4028_x4486_x4556_v).rdAddr(x4478(0)).wtAddr(x4405(0))
      var stage: List[Stage] = Nil
    }
    val x4029_dsp0 = MemoryPipeline(name="x4029_dsp0",parent="x4558") { implicit CU => 
      val x4426_x4426 =  VectorFIFO(size=1).wtPort(x4029_x4426_v)
      val x4417 = CounterChain.copy("x4427_0", "x4417")
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4029_x4487 =  SRAM(size=192,banking = Strided(1)).wtPort(x4426_x4426.readPort).rdPort(x4029_x4487_x4556_v).rdAddr(x4478(0)).wtAddr(x4417(0))
      var stage: List[Stage] = Nil
    }
    val x4030_dsp0 = MemoryPipeline(name="x4030_dsp0",parent="x4558") { implicit CU => 
      val x4438_x4438 =  VectorFIFO(size=1).wtPort(x4030_x4438_v)
      val x4429 = CounterChain.copy("x4439_0", "x4429")
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4030_x4488 =  SRAM(size=192,banking = Strided(1)).wtPort(x4438_x4438.readPort).rdPort(x4030_x4488_x4556_v).rdAddr(x4478(0)).wtAddr(x4429(0))
      var stage: List[Stage] = Nil
    }
    val x4031_dsp0 = MemoryPipeline(name="x4031_dsp0",parent="x4558") { implicit CU => 
      val x4450_x4450 =  VectorFIFO(size=1).wtPort(x4031_x4450_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4441 = CounterChain.copy("x4451_0", "x4441")
      val x4031_x4489 =  SRAM(size=192,banking = Strided(1)).wtPort(x4450_x4450.readPort).rdPort(x4031_x4489_x4556_v).rdAddr(x4478(0)).wtAddr(x4441(0))
      var stage: List[Stage] = Nil
    }
    val x4032_dsp0 = MemoryPipeline(name="x4032_dsp0",parent="x4558") { implicit CU => 
      val x4462_x4462 =  VectorFIFO(size=1).wtPort(x4032_x4462_v)
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4453 = CounterChain.copy("x4463_0", "x4453")
      val x4032_x4490 =  SRAM(size=192,banking = Strided(1)).wtPort(x4462_x4462.readPort).rdPort(x4032_x4490_x4556_v).rdAddr(x4478(0)).wtAddr(x4453(0))
      var stage: List[Stage] = Nil
    }
    val x4033_dsp0 = MemoryPipeline(name="x4033_dsp0",parent="x4558") { implicit CU => 
      val x4474_x4474 =  VectorFIFO(size=1).wtPort(x4033_x4474_v)
      val x4465 = CounterChain.copy("x4475_0", "x4465")
      val x4478 = CounterChain.copy("x4556", "x4478")
      val x4033_x4491 =  SRAM(size=192,banking = Strided(1)).wtPort(x4474_x4474.readPort).rdPort(x4033_x4491_x4556_v).rdAddr(x4478(0)).wtAddr(x4465(0))
      var stage: List[Stage] = Nil
    }
    val x4062_0 = Pipeline(name="x4062_0",parent=x4558) { implicit CU => 
      val x4051_x4051 =  VectorFIFO(size=1).wtPort(x3947_x4051_x4062_v)
      val x4050_x4050 =  VectorFIFO(size=1).wtPort(x3957_x4050_x4062_v)
      val ctr12 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4045 = CounterChain(name = "x4045", ctr12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4050_x4050), CU.load(x4051_x4051)), op=FltMul, results=List(CU.reduce))
      val (_, rr17579) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17579), op=Bypass, results=List(CU.scalarOut(x4034_x4060_s)))
    }
    val x4081_0 = Pipeline(name="x4081_0",parent=x4558) { implicit CU => 
      val x4069_x4069 =  VectorFIFO(size=1).wtPort(x3957_x4069_x4081_v)
      val x4070_x4070 =  VectorFIFO(size=1).wtPort(x3947_x4070_x4081_v)
      val ctr13 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4064 = CounterChain(name = "x4064", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4069_x4069), CU.load(x4070_x4070)), op=FltMul, results=List(CU.reduce))
      val (_, rr17590) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17590), op=Bypass, results=List(CU.scalarOut(x4035_x4079_s)))
    }
    val x4100_0 = Pipeline(name="x4100_0",parent=x4558) { implicit CU => 
      val x4089_x4089 =  VectorFIFO(size=1).wtPort(x3947_x4089_x4100_v)
      val x4088_x4088 =  VectorFIFO(size=1).wtPort(x3957_x4088_x4100_v)
      val ctr14 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4083 = CounterChain(name = "x4083", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4088_x4088), CU.load(x4089_x4089)), op=FltMul, results=List(CU.reduce))
      val (_, rr17601) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17601), op=Bypass, results=List(CU.scalarOut(x4036_x4098_s)))
    }
    val x4119_0 = Pipeline(name="x4119_0",parent=x4558) { implicit CU => 
      val x4108_x4108 =  VectorFIFO(size=1).wtPort(x3947_x4108_x4119_v)
      val x4107_x4107 =  VectorFIFO(size=1).wtPort(x3957_x4107_x4119_v)
      val ctr15 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4102 = CounterChain(name = "x4102", ctr15)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4107_x4107), CU.load(x4108_x4108)), op=FltMul, results=List(CU.reduce))
      val (_, rr17612) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17612), op=Bypass, results=List(CU.scalarOut(x4037_x4117_s)))
    }
    val x4138_0 = Pipeline(name="x4138_0",parent=x4558) { implicit CU => 
      val x4126_x4126 =  VectorFIFO(size=1).wtPort(x3957_x4126_x4138_v)
      val x4127_x4127 =  VectorFIFO(size=1).wtPort(x3947_x4127_x4138_v)
      val ctr16 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4121 = CounterChain(name = "x4121", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4126_x4126), CU.load(x4127_x4127)), op=FltMul, results=List(CU.reduce))
      val (_, rr17623) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17623), op=Bypass, results=List(CU.scalarOut(x4038_x4136_s)))
    }
    val x4157_0 = Pipeline(name="x4157_0",parent=x4558) { implicit CU => 
      val x4146_x4146 =  VectorFIFO(size=1).wtPort(x3947_x4146_x4157_v)
      val x4145_x4145 =  VectorFIFO(size=1).wtPort(x3957_x4145_x4157_v)
      val ctr17 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4140 = CounterChain(name = "x4140", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4145_x4145), CU.load(x4146_x4146)), op=FltMul, results=List(CU.reduce))
      val (_, rr17634) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17634), op=Bypass, results=List(CU.scalarOut(x4039_x4155_s)))
    }
    val x4176_0 = Pipeline(name="x4176_0",parent=x4558) { implicit CU => 
      val x4165_x4165 =  VectorFIFO(size=1).wtPort(x3947_x4165_x4176_v)
      val x4164_x4164 =  VectorFIFO(size=1).wtPort(x3957_x4164_x4176_v)
      val ctr18 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4159 = CounterChain(name = "x4159", ctr18)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4164_x4164), CU.load(x4165_x4165)), op=FltMul, results=List(CU.reduce))
      val (_, rr17645) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17645), op=Bypass, results=List(CU.scalarOut(x4040_x4174_s)))
    }
    val x4195_0 = Pipeline(name="x4195_0",parent=x4558) { implicit CU => 
      val x4183_x4183 =  VectorFIFO(size=1).wtPort(x3957_x4183_x4195_v)
      val x4184_x4184 =  VectorFIFO(size=1).wtPort(x3947_x4184_x4195_v)
      val ctr19 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4178 = CounterChain(name = "x4178", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4183_x4183), CU.load(x4184_x4184)), op=FltMul, results=List(CU.reduce))
      val (_, rr17656) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17656), op=Bypass, results=List(CU.scalarOut(x4041_x4193_s)))
    }
    val x4214_0 = Pipeline(name="x4214_0",parent=x4558) { implicit CU => 
      val x4203_x4203 =  VectorFIFO(size=1).wtPort(x3947_x4203_x4214_v)
      val x4202_x4202 =  VectorFIFO(size=1).wtPort(x3957_x4202_x4214_v)
      val ctr20 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4197 = CounterChain(name = "x4197", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4202_x4202), CU.load(x4203_x4203)), op=FltMul, results=List(CU.reduce))
      val (_, rr17667) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17667), op=Bypass, results=List(CU.scalarOut(x4042_x4212_s)))
    }
    val x4233_0 = Pipeline(name="x4233_0",parent=x4558) { implicit CU => 
      val x4222_x4222 =  VectorFIFO(size=1).wtPort(x3947_x4222_x4233_v)
      val x4221_x4221 =  VectorFIFO(size=1).wtPort(x3957_x4221_x4233_v)
      val ctr21 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4216 = CounterChain(name = "x4216", ctr21)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4221_x4221), CU.load(x4222_x4222)), op=FltMul, results=List(CU.reduce))
      val (_, rr17678) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17678), op=Bypass, results=List(CU.scalarOut(x4043_x4231_s)))
    }
    val x4246_0 = Pipeline(name="x4246_0",parent=x4558) { implicit CU => 
      val x4242 = CU.temp
      val x4241 = CU.temp
      val x4240 = CU.temp
      val x4243 = CU.temp
      val x4238_x4238 =  VectorFIFO(size=1).wtPort(x3958_x4238_x4246_v)
      val x4034_x4239 =  ScalarBuffer().wtPort(x4034_x4060_s)
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4246_unit = CounterChain(name = "x4246_unit", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4034_x4239)), op=FltNeg, results=List(x4240))
      Stage(operands=List(x4240), op=FltExp, results=List(x4241))
      Stage(operands=List(x4241, Const(1)), op=FltAdd, results=List(x4242))
      Stage(operands=List(Const(1), x4242), op=FltDiv, results=List(x4243))
      Stage(operands=List(CU.load(x4238_x4238), x4243), op=FltSub, results=List(CU.scalarOut(x4014_x4245_s)))
    }
    val x4258_0 = Pipeline(name="x4258_0",parent=x4558) { implicit CU => 
      val x4252 = CU.temp
      val x4255 = CU.temp
      val x4254 = CU.temp
      val x4253 = CU.temp
      val x4250_x4250 =  VectorFIFO(size=1).wtPort(x3958_x4250_x4258_v)
      val x4035_x4251 =  ScalarBuffer().wtPort(x4035_x4079_s)
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4258_unit = CounterChain(name = "x4258_unit", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4035_x4251)), op=FltNeg, results=List(x4252))
      Stage(operands=List(x4252), op=FltExp, results=List(x4253))
      Stage(operands=List(x4253, Const(1)), op=FltAdd, results=List(x4254))
      Stage(operands=List(Const(1), x4254), op=FltDiv, results=List(x4255))
      Stage(operands=List(CU.load(x4250_x4250), x4255), op=FltSub, results=List(CU.scalarOut(x4015_x4257_s)))
    }
    val x4270_0 = Pipeline(name="x4270_0",parent=x4558) { implicit CU => 
      val x4267 = CU.temp
      val x4266 = CU.temp
      val x4265 = CU.temp
      val x4264 = CU.temp
      val x4036_x4263 =  ScalarBuffer().wtPort(x4036_x4098_s)
      val x4262_x4262 =  VectorFIFO(size=1).wtPort(x3958_x4262_x4270_v)
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4270_unit = CounterChain(name = "x4270_unit", ctr24)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4036_x4263)), op=FltNeg, results=List(x4264))
      Stage(operands=List(x4264), op=FltExp, results=List(x4265))
      Stage(operands=List(x4265, Const(1)), op=FltAdd, results=List(x4266))
      Stage(operands=List(Const(1), x4266), op=FltDiv, results=List(x4267))
      Stage(operands=List(CU.load(x4262_x4262), x4267), op=FltSub, results=List(CU.scalarOut(x4016_x4269_s)))
    }
    val x4282_0 = Pipeline(name="x4282_0",parent=x4558) { implicit CU => 
      val x4277 = CU.temp
      val x4278 = CU.temp
      val x4276 = CU.temp
      val x4279 = CU.temp
      val x4274_x4274 =  VectorFIFO(size=1).wtPort(x3958_x4274_x4282_v)
      val x4037_x4275 =  ScalarBuffer().wtPort(x4037_x4117_s)
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4282_unit = CounterChain(name = "x4282_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4037_x4275)), op=FltNeg, results=List(x4276))
      Stage(operands=List(x4276), op=FltExp, results=List(x4277))
      Stage(operands=List(x4277, Const(1)), op=FltAdd, results=List(x4278))
      Stage(operands=List(Const(1), x4278), op=FltDiv, results=List(x4279))
      Stage(operands=List(CU.load(x4274_x4274), x4279), op=FltSub, results=List(CU.scalarOut(x4017_x4281_s)))
    }
    val x4294_0 = Pipeline(name="x4294_0",parent=x4558) { implicit CU => 
      val x4289 = CU.temp
      val x4291 = CU.temp
      val x4290 = CU.temp
      val x4288 = CU.temp
      val x4038_x4287 =  ScalarBuffer().wtPort(x4038_x4136_s)
      val x4286_x4286 =  VectorFIFO(size=1).wtPort(x3958_x4286_x4294_v)
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4294_unit = CounterChain(name = "x4294_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4038_x4287)), op=FltNeg, results=List(x4288))
      Stage(operands=List(x4288), op=FltExp, results=List(x4289))
      Stage(operands=List(x4289, Const(1)), op=FltAdd, results=List(x4290))
      Stage(operands=List(Const(1), x4290), op=FltDiv, results=List(x4291))
      Stage(operands=List(CU.load(x4286_x4286), x4291), op=FltSub, results=List(CU.scalarOut(x4018_x4293_s)))
    }
    val x4306_0 = Pipeline(name="x4306_0",parent=x4558) { implicit CU => 
      val x4302 = CU.temp
      val x4301 = CU.temp
      val x4300 = CU.temp
      val x4303 = CU.temp
      val x4039_x4299 =  ScalarBuffer().wtPort(x4039_x4155_s)
      val x4298_x4298 =  VectorFIFO(size=1).wtPort(x3958_x4298_x4306_v)
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4306_unit = CounterChain(name = "x4306_unit", ctr27)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4039_x4299)), op=FltNeg, results=List(x4300))
      Stage(operands=List(x4300), op=FltExp, results=List(x4301))
      Stage(operands=List(x4301, Const(1)), op=FltAdd, results=List(x4302))
      Stage(operands=List(Const(1), x4302), op=FltDiv, results=List(x4303))
      Stage(operands=List(CU.load(x4298_x4298), x4303), op=FltSub, results=List(CU.scalarOut(x4019_x4305_s)))
    }
    val x4318_0 = Pipeline(name="x4318_0",parent=x4558) { implicit CU => 
      val x4312 = CU.temp
      val x4314 = CU.temp
      val x4315 = CU.temp
      val x4313 = CU.temp
      val x4040_x4311 =  ScalarBuffer().wtPort(x4040_x4174_s)
      val x4310_x4310 =  VectorFIFO(size=1).wtPort(x3958_x4310_x4318_v)
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4318_unit = CounterChain(name = "x4318_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4040_x4311)), op=FltNeg, results=List(x4312))
      Stage(operands=List(x4312), op=FltExp, results=List(x4313))
      Stage(operands=List(x4313, Const(1)), op=FltAdd, results=List(x4314))
      Stage(operands=List(Const(1), x4314), op=FltDiv, results=List(x4315))
      Stage(operands=List(CU.load(x4310_x4310), x4315), op=FltSub, results=List(CU.scalarOut(x4020_x4317_s)))
    }
    val x4330_0 = Pipeline(name="x4330_0",parent=x4558) { implicit CU => 
      val x4326 = CU.temp
      val x4325 = CU.temp
      val x4327 = CU.temp
      val x4324 = CU.temp
      val x4041_x4323 =  ScalarBuffer().wtPort(x4041_x4193_s)
      val x4322_x4322 =  VectorFIFO(size=1).wtPort(x3958_x4322_x4330_v)
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4330_unit = CounterChain(name = "x4330_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4041_x4323)), op=FltNeg, results=List(x4324))
      Stage(operands=List(x4324), op=FltExp, results=List(x4325))
      Stage(operands=List(x4325, Const(1)), op=FltAdd, results=List(x4326))
      Stage(operands=List(Const(1), x4326), op=FltDiv, results=List(x4327))
      Stage(operands=List(CU.load(x4322_x4322), x4327), op=FltSub, results=List(CU.scalarOut(x4021_x4329_s)))
    }
    val x4342_0 = Pipeline(name="x4342_0",parent=x4558) { implicit CU => 
      val x4337 = CU.temp
      val x4336 = CU.temp
      val x4339 = CU.temp
      val x4338 = CU.temp
      val x4042_x4335 =  ScalarBuffer().wtPort(x4042_x4212_s)
      val x4334_x4334 =  VectorFIFO(size=1).wtPort(x3958_x4334_x4342_v)
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4342_unit = CounterChain(name = "x4342_unit", ctr30)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4042_x4335)), op=FltNeg, results=List(x4336))
      Stage(operands=List(x4336), op=FltExp, results=List(x4337))
      Stage(operands=List(x4337, Const(1)), op=FltAdd, results=List(x4338))
      Stage(operands=List(Const(1), x4338), op=FltDiv, results=List(x4339))
      Stage(operands=List(CU.load(x4334_x4334), x4339), op=FltSub, results=List(CU.scalarOut(x4022_x4341_s)))
    }
    val x4354_0 = Pipeline(name="x4354_0",parent=x4558) { implicit CU => 
      val x4348 = CU.temp
      val x4350 = CU.temp
      val x4349 = CU.temp
      val x4351 = CU.temp
      val x4346_x4346 =  VectorFIFO(size=1).wtPort(x3958_x4346_x4354_v)
      val x4043_x4347 =  ScalarBuffer().wtPort(x4043_x4231_s)
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4354_unit = CounterChain(name = "x4354_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4043_x4347)), op=FltNeg, results=List(x4348))
      Stage(operands=List(x4348), op=FltExp, results=List(x4349))
      Stage(operands=List(x4349, Const(1)), op=FltAdd, results=List(x4350))
      Stage(operands=List(Const(1), x4350), op=FltDiv, results=List(x4351))
      Stage(operands=List(CU.load(x4346_x4346), x4351), op=FltSub, results=List(CU.scalarOut(x4023_x4353_s)))
    }
    val x4367_0 = Pipeline(name="x4367_0",parent=x4558) { implicit CU => 
      val x4362_x4362 =  VectorFIFO(size=1).wtPort(x3957_x4362_x4367_v)
      val x4014_x4363 =  ScalarBuffer().wtPort(x4014_x4245_s)
      val ctr32 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4357 = CounterChain(name = "x4357", ctr32)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4362_x4362), CU.load(x4014_x4363)), op=FltSub, results=List(CU.vecOut(x4024_x4366_v)))
    }
    val x4379_0 = Pipeline(name="x4379_0",parent=x4558) { implicit CU => 
      val x4374_x4374 =  VectorFIFO(size=1).wtPort(x3957_x4374_x4379_v)
      val x4015_x4375 =  ScalarBuffer().wtPort(x4015_x4257_s)
      val ctr33 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4369 = CounterChain(name = "x4369", ctr33)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4374_x4374), CU.load(x4015_x4375)), op=FltSub, results=List(CU.vecOut(x4025_x4378_v)))
    }
    val x4391_0 = Pipeline(name="x4391_0",parent=x4558) { implicit CU => 
      val x4016_x4387 =  ScalarBuffer().wtPort(x4016_x4269_s)
      val x4386_x4386 =  VectorFIFO(size=1).wtPort(x3957_x4386_x4391_v)
      val ctr34 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4381 = CounterChain(name = "x4381", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4386_x4386), CU.load(x4016_x4387)), op=FltSub, results=List(CU.vecOut(x4026_x4390_v)))
    }
    val x4403_0 = Pipeline(name="x4403_0",parent=x4558) { implicit CU => 
      val x4398_x4398 =  VectorFIFO(size=1).wtPort(x3957_x4398_x4403_v)
      val x4017_x4399 =  ScalarBuffer().wtPort(x4017_x4281_s)
      val ctr35 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4393 = CounterChain(name = "x4393", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4398_x4398), CU.load(x4017_x4399)), op=FltSub, results=List(CU.vecOut(x4027_x4402_v)))
    }
    val x4415_0 = Pipeline(name="x4415_0",parent=x4558) { implicit CU => 
      val x4410_x4410 =  VectorFIFO(size=1).wtPort(x3957_x4410_x4415_v)
      val x4018_x4411 =  ScalarBuffer().wtPort(x4018_x4293_s)
      val ctr36 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4405 = CounterChain(name = "x4405", ctr36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4410_x4410), CU.load(x4018_x4411)), op=FltSub, results=List(CU.vecOut(x4028_x4414_v)))
    }
    val x4427_0 = Pipeline(name="x4427_0",parent=x4558) { implicit CU => 
      val x4422_x4422 =  VectorFIFO(size=1).wtPort(x3957_x4422_x4427_v)
      val x4019_x4423 =  ScalarBuffer().wtPort(x4019_x4305_s)
      val ctr37 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4417 = CounterChain(name = "x4417", ctr37)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4422_x4422), CU.load(x4019_x4423)), op=FltSub, results=List(CU.vecOut(x4029_x4426_v)))
    }
    val x4439_0 = Pipeline(name="x4439_0",parent=x4558) { implicit CU => 
      val x4434_x4434 =  VectorFIFO(size=1).wtPort(x3957_x4434_x4439_v)
      val x4020_x4435 =  ScalarBuffer().wtPort(x4020_x4317_s)
      val ctr38 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4429 = CounterChain(name = "x4429", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4434_x4434), CU.load(x4020_x4435)), op=FltSub, results=List(CU.vecOut(x4030_x4438_v)))
    }
    val x4451_0 = Pipeline(name="x4451_0",parent=x4558) { implicit CU => 
      val x4446_x4446 =  VectorFIFO(size=1).wtPort(x3957_x4446_x4451_v)
      val x4021_x4447 =  ScalarBuffer().wtPort(x4021_x4329_s)
      val ctr39 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4441 = CounterChain(name = "x4441", ctr39)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4446_x4446), CU.load(x4021_x4447)), op=FltSub, results=List(CU.vecOut(x4031_x4450_v)))
    }
    val x4463_0 = Pipeline(name="x4463_0",parent=x4558) { implicit CU => 
      val x4022_x4459 =  ScalarBuffer().wtPort(x4022_x4341_s)
      val x4458_x4458 =  VectorFIFO(size=1).wtPort(x3957_x4458_x4463_v)
      val ctr40 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4453 = CounterChain(name = "x4453", ctr40)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4458_x4458), CU.load(x4022_x4459)), op=FltSub, results=List(CU.vecOut(x4032_x4462_v)))
    }
    val x4475_0 = Pipeline(name="x4475_0",parent=x4558) { implicit CU => 
      val x4470_x4470 =  VectorFIFO(size=1).wtPort(x3957_x4470_x4475_v)
      val x4023_x4471 =  ScalarBuffer().wtPort(x4023_x4353_s)
      val ctr41 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4465 = CounterChain(name = "x4465", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4470_x4470), CU.load(x4023_x4471)), op=FltSub, results=List(CU.vecOut(x4033_x4474_v)))
    }
    val x4556 = StreamController(name="x4556",parent=x4558) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4478 = CounterChain(name = "x4478", ctr42)
    }
    val x4556_0 = Pipeline(name="x4556_0",parent=x4556) { implicit CU => 
      val x4517 = CU.temp
      val x4506 = CU.temp
      val x4482_x4482 =  VectorFIFO(size=1).wtPort(x4024_x4482_x4556_v)
      val x4485_x4485 =  VectorFIFO(size=1).wtPort(x4027_x4485_x4556_v)
      val x4484_x4484 =  VectorFIFO(size=1).wtPort(x4026_x4484_x4556_v)
      val x4483_x4483 =  VectorFIFO(size=1).wtPort(x4025_x4483_x4556_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4482_x4482), CU.load(x4483_x4483)), op=FltAdd, results=List(x4506))
      Stage(operands=List(CU.load(x4484_x4484), CU.load(x4485_x4485)), op=FltAdd, results=List(x4517))
      Stage(operands=List(x4506, x4517), op=FltAdd, results=List(CU.scalarOut(bus_17844_s)))
    }
    val x4556_1 = Pipeline(name="x4556_1",parent=x4556) { implicit CU => 
      val x4542 = CU.temp
      val x4538 = CU.temp
      val x4544 = CU.temp
      val x4519 =  ScalarFIFO(size=1).wtPort(bus_17844_s)
      val x4489_x4489 =  VectorFIFO(size=1).wtPort(x4031_x4489_x4556_v)
      val x4487_x4487 =  VectorFIFO(size=1).wtPort(x4029_x4487_x4556_v)
      val x4486_x4486 =  VectorFIFO(size=1).wtPort(x4028_x4486_x4556_v)
      val x4488_x4488 =  VectorFIFO(size=1).wtPort(x4030_x4488_x4556_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4486_x4486), CU.load(x4487_x4487)), op=FltAdd, results=List(x4538))
      Stage(operands=List(CU.load(x4488_x4488), CU.load(x4489_x4489)), op=FltAdd, results=List(x4542))
      Stage(operands=List(x4538, x4542), op=FltAdd, results=List(x4544))
      Stage(operands=List(CU.load(x4519), x4544), op=FltAdd, results=List(CU.scalarOut(bus_17863_s)))
    }
    val x4556_2 = Pipeline(name="x4556_2",parent=x4556) { implicit CU => 
      val x4550 = CU.temp
      val x4552 = CU.temp
      val x4491_x4491 =  VectorFIFO(size=1).wtPort(x4033_x4491_x4556_v)
      val x4546 =  ScalarFIFO(size=1).wtPort(bus_17863_s)
      val x4490_x4490 =  VectorFIFO(size=1).wtPort(x4032_x4490_x4556_v)
      val x4492_x4492 =  VectorFIFO(size=1).wtPort(x3953_x4492_x4556_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4490_x4490), CU.load(x4491_x4491)), op=FltAdd, results=List(x4550))
      Stage(operands=List(CU.load(x4546), x4550), op=FltAdd, results=List(x4552))
      Stage(operands=List(x4552, CU.load(x4492_x4492)), op=FltAdd, results=List(CU.vecOut(x3953_x4555_v)))
    }
    val x4570_0 = Pipeline(name="x4570_0",parent=x4573) { implicit CU => 
      val x4567 = CU.temp
      val x4563_x4563 =  VectorFIFO(size=1).wtPort(x3953_x4563_x4570_v)
      val x4564_x4564 =  VectorFIFO(size=1).wtPort(x3947_x4564_x4570_v)
      val ctr43 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4561 = CounterChain(name = "x4561", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4564_x4564), Const(1)), op=FltMul, results=List(x4567))
      Stage(operands=List(CU.load(x4563_x4563), x4567), op=FltAdd, results=List(CU.vecOut(x3947_x4569_v)))
    }
    val x4599 = StreamController(name="x4599",parent=x4600) { implicit CU => 
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4599_unit = CounterChain(name = "x4599_unit", ctr44)
    }
    val x4592 = Sequential(name="x4592",parent=x4599) { implicit CU => 
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4592_unit = CounterChain(name = "x4592_unit", ctr45)
    }
    val x4584_0 = Pipeline(name="x4584_0",parent=x4592) { implicit CU => 
      val x4578 =  ScalarBuffer().wtPort(x4578_argin)
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4584_unit = CounterChain(name = "x4584_unit", ctr46)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4578)), op=FixAdd, results=List(CU.scalarOut(x4575_b4711_x4583_b4713_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x4575_b4712_x4583_b4714_s)))
    }
    val x4591 = Pipeline(name="x4591",parent=x4592) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4586 = CounterChain(name = "x4586", ctr47)
      var stage: List[Stage] = Nil
    }
    val x4593 = MemoryController(name="x4593",parent=x4599,offchip=x3941_oc, mctpe=TileStore) { implicit CU => 
      val x4575_b4712_x4593 =  ScalarFIFO(name="size",size=1).wtPort(x4575_b4712_x4583_b4714_s)
      val x4575_b4711_x4593 =  ScalarFIFO(name="offset",size=1).wtPort(x4575_b4711_x4583_b4713_s)
      val x4576_x4593 =  VectorFIFO(name="data",size=1).wtPort(x3947_x4587_x4591_v)
    }
    
  }
}
