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
    val x4299_x4455_x4467_v = Vector("x4299_x4455_x4467")
    val x4289_x5014_x5020_v = Vector("x4289_x5014_x5020")
    val x4378_x4926_x5006_v = Vector("x4378_x4926_x5006")
    val x4299_x4398_x4410_v = Vector("x4299_x4398_x4410")
    val x4300_x4720_x4728_v = Vector("x4300_x4720_x4728")
    val x4300_x4636_x4644_v = Vector("x4300_x4636_x4644")
    val x4283_oc = OffChip("x4283")
    val x4299_x4588_x4600_v = Vector("x4299_x4588_x4600")
    val x4303_b5111_x4316_b5113_s = Scalar("x4303_b5111_x4316_b5113")
    val x4289_x4456_x4467_v = Vector("x4289_x4456_x4467")
    val x4275_argin = ArgIn("x4275")
    val x4289_x4494_x4505_v = Vector("x4289_x4494_x4505")
    val x4370_x4800_v = Vector("x4370_x4800")
    val x4299_x4820_x4825_v = Vector("x4299_x4820_x4825")
    val x4289_x4608_x4619_v = Vector("x4289_x4608_x4619")
    val x4289_x5019_v = Vector("x4289_x5019")
    val x4299_x4892_x4897_v = Vector("x4299_x4892_x4897")
    val x4295_x5013_x5020_v = Vector("x4295_x5013_x5020")
    val x4364_x4727_s = Scalar("x4364_x4727")
    val x4299_x4607_x4619_v = Vector("x4299_x4607_x4619")
    val x4295_x4928_x5006_v = Vector("x4295_x4928_x5006")
    val x4372_x4824_v = Vector("x4372_x4824")
    val x4280_oc = OffChip("x4280")
    val x4357_x4643_s = Scalar("x4357_x4643")
    val x4374_x4848_v = Vector("x4374_x4848")
    val x4385_x4503_s = Scalar("x4385_x4503")
    val x4358_x4655_s = Scalar("x4358_x4655")
    val x4356_x4631_s = Scalar("x4356_x4631")
    val x4371_x4812_v = Vector("x4371_x4812")
    val x4289_x4513_x4524_v = Vector("x4289_x4513_x4524")
    val x4371_x4919_x5006_v = Vector("x4371_x4919_x5006")
    val x5025_b5170_x5033_b5172_s = Scalar("x5025_b5170_x5033_b5172")
    val x4300_x4696_x4704_v = Vector("x4300_x4696_x4704")
    val x4305_argin = ArgIn("x4305")
    val x4375_x4923_x5006_v = Vector("x4375_x4923_x5006")
    val x4332_argin = ArgIn("x4332")
    val x4361_x4691_s = Scalar("x4361_x4691")
    val x4299_x4531_x4543_v = Vector("x4299_x4531_x4543")
    val x4299_x4796_x4801_v = Vector("x4299_x4796_x4801")
    val x4376_x4872_v = Vector("x4376_x4872")
    val x4372_x4920_x5006_v = Vector("x4372_x4920_x5006")
    val bus_28119_s = Scalar("bus_28119")
    val x4363_x4715_s = Scalar("x4363_x4715")
    val x4377_x4925_x5006_v = Vector("x4377_x4925_x5006")
    val x4299_x4880_x4885_v = Vector("x4299_x4880_x4885")
    val x4289_x4399_x4410_v = Vector("x4289_x4399_x4410")
    val x4276_argin = ArgIn("x4276")
    val x4367_x4763_s = Scalar("x4367_x4763")
    val x4289_x4418_x4429_v = Vector("x4289_x4418_x4429")
    val x4381_x4427_s = Scalar("x4381_x4427")
    val x4299_x4808_x4813_v = Vector("x4299_x4808_x4813")
    val x4368_x4916_x5006_v = Vector("x4368_x4916_x5006")
    val x4299_x4868_x4873_v = Vector("x4299_x4868_x4873")
    val x4376_x4924_x5006_v = Vector("x4376_x4924_x5006")
    val x4374_x4922_x5006_v = Vector("x4374_x4922_x5006")
    val x4299_x4832_x4837_v = Vector("x4299_x4832_x4837")
    val x4375_x4860_v = Vector("x4375_x4860")
    val x4289_x4532_x4543_v = Vector("x4289_x4532_x4543")
    val bus_28096_s = Scalar("bus_28096")
    val x4379_x4927_x5006_v = Vector("x4379_x4927_x5006")
    val x4300_x4684_x4692_v = Vector("x4300_x4684_x4692")
    val x4370_x4918_x5006_v = Vector("x4370_x4918_x5006")
    val x4387_x4541_s = Scalar("x4387_x4541")
    val x4380_x4408_s = Scalar("x4380_x4408")
    val x4289_x4551_x4562_v = Vector("x4289_x4551_x4562")
    val x4379_x4908_v = Vector("x4379_x4908")
    val x4390_x4598_s = Scalar("x4390_x4598")
    val x4389_x4579_s = Scalar("x4389_x4579")
    val bus_28115_s = Scalar("bus_28115")
    val x4300_x4660_x4668_v = Vector("x4300_x4660_x4668")
    val x4299_x4569_x4581_v = Vector("x4299_x4569_x4581")
    val x4299_x4784_x4789_v = Vector("x4299_x4784_x4789")
    val x4300_x4756_x4764_v = Vector("x4300_x4756_x4764")
    val x4300_x4624_x4632_v = Vector("x4300_x4624_x4632")
    val x4330_b5118_x4340_b5120_s = Scalar("x4330_b5118_x4340_b5120")
    val x4299_x4856_x4861_v = Vector("x4299_x4856_x4861")
    val x4295_x5005_v = Vector("x4295_x5005")
    val x4299_x4904_x4909_v = Vector("x4299_x4904_x4909")
    val x4391_x4617_s = Scalar("x4391_x4617")
    val x4377_x4884_v = Vector("x4377_x4884")
    val x4366_x4751_s = Scalar("x4366_x4751")
    val x4304_x4318_data_v = Vector("x4304_x4318_data")
    val x4386_x4522_s = Scalar("x4386_x4522")
    val x5025_b5169_x5033_b5171_s = Scalar("x5025_b5169_x5033_b5171")
    val x4300_x4708_x4716_v = Vector("x4300_x4708_x4716")
    val x4300_x4672_x4680_v = Vector("x4300_x4672_x4680")
    val x4373_x4836_v = Vector("x4373_x4836")
    val x4299_x4436_x4448_v = Vector("x4299_x4436_x4448")
    val x4289_x4437_x4448_v = Vector("x4289_x4437_x4448")
    val x4289_x4475_x4486_v = Vector("x4289_x4475_x4486")
    val x4300_x4732_x4740_v = Vector("x4300_x4732_x4740")
    val x4289_x4570_x4581_v = Vector("x4289_x4570_x4581")
    val x4384_x4484_s = Scalar("x4384_x4484")
    val x4383_x4465_s = Scalar("x4383_x4465")
    val x4331_x4342_data_v = Vector("x4331_x4342_data")
    val x4300_x4648_x4656_v = Vector("x4300_x4648_x4656")
    val x4388_x4560_s = Scalar("x4388_x4560")
    val x4289_x5037_x5041_v = Vector("x4289_x5037_x5041")
    val x4359_x4667_s = Scalar("x4359_x4667")
    val x4362_x4703_s = Scalar("x4362_x4703")
    val x4282_oc = OffChip("x4282")
    val x4382_x4446_s = Scalar("x4382_x4446")
    val x4369_x4917_x5006_v = Vector("x4369_x4917_x5006")
    val x4373_x4921_x5006_v = Vector("x4373_x4921_x5006")
    val x4369_x4788_v = Vector("x4369_x4788")
    val x5028_argin = ArgIn("x5028")
    val x4299_x4844_x4849_v = Vector("x4299_x4844_x4849")
    val x4303_b5112_x4316_b5114_s = Scalar("x4303_b5112_x4316_b5114")
    val x4289_x4589_x4600_v = Vector("x4289_x4589_x4600")
    val x4300_x4744_x4752_v = Vector("x4300_x4744_x4752")
    val x4299_x4474_x4486_v = Vector("x4299_x4474_x4486")
    val x4299_x4550_x4562_v = Vector("x4299_x4550_x4562")
    val x4299_x4512_x4524_v = Vector("x4299_x4512_x4524")
    val x4299_x4417_x4429_v = Vector("x4299_x4417_x4429")
    val x4368_x4776_v = Vector("x4368_x4776")
    val x4299_x4493_x4505_v = Vector("x4299_x4493_x4505")
    val x4365_x4739_s = Scalar("x4365_x4739")
    val x4378_x4896_v = Vector("x4378_x4896")
    val x4330_b5117_x4340_b5119_s = Scalar("x4330_b5117_x4340_b5119")
    val x4360_x4679_s = Scalar("x4360_x4679")
    val x4299_x4772_x4777_v = Vector("x4299_x4772_x4777")
    val x5050 = Sequential(name="x5050",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5050_unit = CounterChain(name = "x5050_unit", ctr1)
    }
    val x4289_dsp0 = MemoryPipeline(name="x4289_dsp0",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x5036 = CounterChain.copy("x5041", "x5036")
      val x4289_x5037 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x5037_x5041_v).rdAddr(x5036(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp1 = MemoryPipeline(name="x4289_dsp1",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4289_x5014 =  SRAM(size=192,banking = NoBanking()).wtPort(x5019_x5019.readPort).rdPort(x4289_x5014_x5020_v).rdAddr(x5011(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp2 = MemoryPipeline(name="x4289_dsp2",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4602 = CounterChain.copy("x4619_0", "x4602")
      val x4289_x4608 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4608_x4619_v).rdAddr(x4602(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp3 = MemoryPipeline(name="x4289_dsp3",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4583 = CounterChain.copy("x4600_0", "x4583")
      val x4289_x4589 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4589_x4600_v).rdAddr(x4583(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp4 = MemoryPipeline(name="x4289_dsp4",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4564 = CounterChain.copy("x4581_0", "x4564")
      val x4289_x4570 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4570_x4581_v).rdAddr(x4564(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp5 = MemoryPipeline(name="x4289_dsp5",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4545 = CounterChain.copy("x4562_0", "x4545")
      val x4289_x4551 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4551_x4562_v).rdAddr(x4545(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp6 = MemoryPipeline(name="x4289_dsp6",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4526 = CounterChain.copy("x4543_0", "x4526")
      val x4289_x4532 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4532_x4543_v).rdAddr(x4526(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp7 = MemoryPipeline(name="x4289_dsp7",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4507 = CounterChain.copy("x4524_0", "x4507")
      val x4289_x4513 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4513_x4524_v).rdAddr(x4507(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp8 = MemoryPipeline(name="x4289_dsp8",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4488 = CounterChain.copy("x4505_0", "x4488")
      val x4289_x4494 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4494_x4505_v).rdAddr(x4488(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp9 = MemoryPipeline(name="x4289_dsp9",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4469 = CounterChain.copy("x4486_0", "x4469")
      val x4289_x4475 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4475_x4486_v).rdAddr(x4469(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp10 = MemoryPipeline(name="x4289_dsp10",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4450 = CounterChain.copy("x4467_0", "x4450")
      val x4289_x4456 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4456_x4467_v).rdAddr(x4450(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp11 = MemoryPipeline(name="x4289_dsp11",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x4431 = CounterChain.copy("x4448_0", "x4431")
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4289_x4437 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4437_x4448_v).rdAddr(x4431(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp12 = MemoryPipeline(name="x4289_dsp12",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4412 = CounterChain.copy("x4429_0", "x4412")
      val x4289_x4418 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4418_x4429_v).rdAddr(x4412(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x4289_dsp13 = MemoryPipeline(name="x4289_dsp13",parent="x5023") { implicit CU => 
      val x5019_x5019 =  VectorFIFO(size=1).wtPort(x4289_x5019_v)
      val x4393 = CounterChain.copy("x4410_0", "x4393")
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4289_x4399 =  SRAM(size=192,banking = Strided(1)).wtPort(x5019_x5019.readPort).rdPort(x4289_x4399_x4410_v).rdAddr(x4393(0)).wtAddr(x5011(0))
      var stage: List[Stage] = Nil
    }
    val x5024 = Sequential(name="x5024",parent=x5050) { implicit CU => 
      val x4275_x4290 =  ScalarBuffer().wtPort(x4275_argin)
      val ctr2 = Counter(min=Const(0), max=x4275_x4290.load, step=Const(1), par=1) // Counter
      val x4292 = CounterChain(name = "x4292", ctr2)
    }
    val x5023 = Sequential(name="x5023",parent=x5024) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x4294 = CounterChain(name = "x4294", ctr3)
    }
    val x4295_dsp0 = MemoryPipeline(name="x4295_dsp0",parent="x5008") { implicit CU => 
      val x5005_x5005 =  VectorFIFO(size=1).wtPort(x4295_x5005_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x5011 = CounterChain.copy("x5020_0", "x5011")
      val x4295_x5013 =  SRAM(size=192,banking = NoBanking()).wtPort(x5005_x5005.readPort).rdPort(x4295_x5013_x5020_v).rdAddr(x5011(0)).wtAddr(x4912(0))
      var stage: List[Stage] = Nil
    }
    val x4295_dsp1 = MemoryPipeline(name="x4295_dsp1",parent="x5008") { implicit CU => 
      val x5005_x5005 =  VectorFIFO(size=1).wtPort(x4295_x5005_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4295_x4928 =  SRAM(size=192,banking = NoBanking()).wtPort(x5005_x5005.readPort).rdPort(x4295_x4928_x5006_v).rdAddr(x4912(0)).wtAddr(x4912(0))
      var stage: List[Stage] = Nil
    }
    val x5009 = MetaPipeline(name="x5009",parent=x5023) { implicit CU => 
      val x4276_x4296 =  ScalarBuffer().wtPort(x4276_argin)
      val ctr4 = Counter(min=Const(0), max=x4276_x4296.load, step=Const(16), par=1) // Counter
      val x4298 = CounterChain(name = "x4298", ctr4)
    }
    val x4299_dsp0 = MemoryPipeline(name="x4299_dsp0",parent="x5009") { implicit CU => 
      val b5167 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4899 = CounterChain.copy("x4909_0", "x4899")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4904 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4904_x4909_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4904.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5167))
      RAStage(operands=List(b5167, CU.ctr(x4899(0))), op=FixAdd, results=List(x4299_x4904.readAddr))
    }
    val x4299_dsp1 = MemoryPipeline(name="x4299_dsp1",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5165 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4887 = CounterChain.copy("x4897_0", "x4887")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4892 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4892_x4897_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4892.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5165))
      RAStage(operands=List(b5165, CU.ctr(x4887(0))), op=FixAdd, results=List(x4299_x4892.readAddr))
    }
    val x4299_dsp2 = MemoryPipeline(name="x4299_dsp2",parent="x5009") { implicit CU => 
      val b5163 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4875 = CounterChain.copy("x4885_0", "x4875")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4880 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4880_x4885_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4880.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5163))
      RAStage(operands=List(b5163, CU.ctr(x4875(0))), op=FixAdd, results=List(x4299_x4880.readAddr))
    }
    val x4299_dsp3 = MemoryPipeline(name="x4299_dsp3",parent="x5009") { implicit CU => 
      val b5161 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4863 = CounterChain.copy("x4873_0", "x4863")
      val x4299_x4868 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4868_x4873_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4868.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5161))
      RAStage(operands=List(b5161, CU.ctr(x4863(0))), op=FixAdd, results=List(x4299_x4868.readAddr))
    }
    val x4299_dsp4 = MemoryPipeline(name="x4299_dsp4",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5159 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4851 = CounterChain.copy("x4861_0", "x4851")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4856 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4856_x4861_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4856.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5159))
      RAStage(operands=List(b5159, CU.ctr(x4851(0))), op=FixAdd, results=List(x4299_x4856.readAddr))
    }
    val x4299_dsp5 = MemoryPipeline(name="x4299_dsp5",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5157 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4839 = CounterChain.copy("x4849_0", "x4839")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4844 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4844_x4849_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4844.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5157))
      RAStage(operands=List(b5157, CU.ctr(x4839(0))), op=FixAdd, results=List(x4299_x4844.readAddr))
    }
    val x4299_dsp6 = MemoryPipeline(name="x4299_dsp6",parent="x5009") { implicit CU => 
      val b5155 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4827 = CounterChain.copy("x4837_0", "x4827")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4832 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4832_x4837_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4832.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5155))
      RAStage(operands=List(b5155, CU.ctr(x4827(0))), op=FixAdd, results=List(x4299_x4832.readAddr))
    }
    val x4299_dsp7 = MemoryPipeline(name="x4299_dsp7",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5153 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4815 = CounterChain.copy("x4825_0", "x4815")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4820 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4820_x4825_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4820.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5153))
      RAStage(operands=List(b5153, CU.ctr(x4815(0))), op=FixAdd, results=List(x4299_x4820.readAddr))
    }
    val x4299_dsp8 = MemoryPipeline(name="x4299_dsp8",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5151 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4803 = CounterChain.copy("x4813_0", "x4803")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4808 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4808_x4813_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4808.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5151))
      RAStage(operands=List(b5151, CU.ctr(x4803(0))), op=FixAdd, results=List(x4299_x4808.readAddr))
    }
    val x4299_dsp9 = MemoryPipeline(name="x4299_dsp9",parent="x5009") { implicit CU => 
      val b5149 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4791 = CounterChain.copy("x4801_0", "x4791")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4796 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4796_x4801_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4796.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5149))
      RAStage(operands=List(b5149, CU.ctr(x4791(0))), op=FixAdd, results=List(x4299_x4796.readAddr))
    }
    val x4299_dsp10 = MemoryPipeline(name="x4299_dsp10",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5147 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4779 = CounterChain.copy("x4789_0", "x4779")
      val x4299_x4784 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4784_x4789_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4784.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5147))
      RAStage(operands=List(b5147, CU.ctr(x4779(0))), op=FixAdd, results=List(x4299_x4784.readAddr))
    }
    val x4299_dsp11 = MemoryPipeline(name="x4299_dsp11",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5145 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4767 = CounterChain.copy("x4777_0", "x4767")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4772 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4772_x4777_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4772.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5145))
      RAStage(operands=List(b5145, CU.ctr(x4767(0))), op=FixAdd, results=List(x4299_x4772.readAddr))
    }
    val x4299_dsp12 = MemoryPipeline(name="x4299_dsp12",parent="x5009") { implicit CU => 
      val b5143 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4602 = CounterChain.copy("x4619_0", "x4602")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4607 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4607_x4619_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4607.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5143))
      RAStage(operands=List(b5143, CU.ctr(x4602(0))), op=FixAdd, results=List(x4299_x4607.readAddr))
    }
    val x4299_dsp13 = MemoryPipeline(name="x4299_dsp13",parent="x5009") { implicit CU => 
      val b5141 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4583 = CounterChain.copy("x4600_0", "x4583")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4588 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4588_x4600_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4588.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5141))
      RAStage(operands=List(b5141, CU.ctr(x4583(0))), op=FixAdd, results=List(x4299_x4588.readAddr))
    }
    val x4299_dsp14 = MemoryPipeline(name="x4299_dsp14",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5139 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4564 = CounterChain.copy("x4581_0", "x4564")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4569 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4569_x4581_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4569.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5139))
      RAStage(operands=List(b5139, CU.ctr(x4564(0))), op=FixAdd, results=List(x4299_x4569.readAddr))
    }
    val x4299_dsp15 = MemoryPipeline(name="x4299_dsp15",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5137 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4545 = CounterChain.copy("x4562_0", "x4545")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4550 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4550_x4562_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4550.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5137))
      RAStage(operands=List(b5137, CU.ctr(x4545(0))), op=FixAdd, results=List(x4299_x4550.readAddr))
    }
    val x4299_dsp16 = MemoryPipeline(name="x4299_dsp16",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5135 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4526 = CounterChain.copy("x4543_0", "x4526")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4531 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4531_x4543_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4531.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5135))
      RAStage(operands=List(b5135, CU.ctr(x4526(0))), op=FixAdd, results=List(x4299_x4531.readAddr))
    }
    val x4299_dsp17 = MemoryPipeline(name="x4299_dsp17",parent="x5009") { implicit CU => 
      val b5133 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4507 = CounterChain.copy("x4524_0", "x4507")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4512 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4512_x4524_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4512.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5133))
      RAStage(operands=List(b5133, CU.ctr(x4507(0))), op=FixAdd, results=List(x4299_x4512.readAddr))
    }
    val x4299_dsp18 = MemoryPipeline(name="x4299_dsp18",parent="x5009") { implicit CU => 
      val b5131 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4488 = CounterChain.copy("x4505_0", "x4488")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4493 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4493_x4505_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4493.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5131))
      RAStage(operands=List(b5131, CU.ctr(x4488(0))), op=FixAdd, results=List(x4299_x4493.readAddr))
    }
    val x4299_dsp19 = MemoryPipeline(name="x4299_dsp19",parent="x5009") { implicit CU => 
      val b5129 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4469 = CounterChain.copy("x4486_0", "x4469")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4474 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4474_x4486_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4474.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5129))
      RAStage(operands=List(b5129, CU.ctr(x4469(0))), op=FixAdd, results=List(x4299_x4474.readAddr))
    }
    val x4299_dsp20 = MemoryPipeline(name="x4299_dsp20",parent="x5009") { implicit CU => 
      val b5127 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4450 = CounterChain.copy("x4467_0", "x4450")
      val x4299_x4455 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4455_x4467_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4455.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5127))
      RAStage(operands=List(b5127, CU.ctr(x4450(0))), op=FixAdd, results=List(x4299_x4455.readAddr))
    }
    val x4299_dsp21 = MemoryPipeline(name="x4299_dsp21",parent="x5009") { implicit CU => 
      val b5125 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4431 = CounterChain.copy("x4448_0", "x4431")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4436 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4436_x4448_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4436.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5125))
      RAStage(operands=List(b5125, CU.ctr(x4431(0))), op=FixAdd, results=List(x4299_x4436.readAddr))
    }
    val x4299_dsp22 = MemoryPipeline(name="x4299_dsp22",parent="x5009") { implicit CU => 
      val b5123 = CU.temp
      val b5115 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4412 = CounterChain.copy("x4429_0", "x4412")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4417 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4417_x4429_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4417.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5123))
      RAStage(operands=List(b5123, CU.ctr(x4412(0))), op=FixAdd, results=List(x4299_x4417.readAddr))
    }
    val x4299_dsp23 = MemoryPipeline(name="x4299_dsp23",parent="x5009") { implicit CU => 
      val b5115 = CU.temp
      val b5121 = CU.temp
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x4304_x4318_data_v)
      val x4393 = CounterChain.copy("x4410_0", "x4393")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val x4320 = CounterChain.copy("x4328", "x4320")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4299_x4398 =  SRAM(size=3072,banking = Strided(1)).wtPort(x4327_x4327.readPort).rdPort(x4299_x4398_x4410_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4302(0)), Const(192)), op=FixMul, results=List(b5115))
      WAStage(operands=List(b5115, CU.ctr(x4320(0))), op=FixAdd, results=List(x4299_x4398.writeAddr))
      RAStage(operands=List(CU.ctr(x4355(0)), Const(192)), op=FixMul, results=List(b5121))
      RAStage(operands=List(b5121, CU.ctr(x4393(0))), op=FixAdd, results=List(x4299_x4398.readAddr))
    }
    val x4300_dsp0 = MemoryPipeline(name="x4300_dsp0",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4756 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4756_x4764_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp1 = MemoryPipeline(name="x4300_dsp1",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4744 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4744_x4752_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp2 = MemoryPipeline(name="x4300_dsp2",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4732 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4732_x4740_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp3 = MemoryPipeline(name="x4300_dsp3",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4720 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4720_x4728_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp4 = MemoryPipeline(name="x4300_dsp4",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4708 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4708_x4716_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp5 = MemoryPipeline(name="x4300_dsp5",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4696 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4696_x4704_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp6 = MemoryPipeline(name="x4300_dsp6",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4684 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4684_x4692_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp7 = MemoryPipeline(name="x4300_dsp7",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4672 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4672_x4680_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp8 = MemoryPipeline(name="x4300_dsp8",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4660 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4660_x4668_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp9 = MemoryPipeline(name="x4300_dsp9",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4648 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4648_x4656_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp10 = MemoryPipeline(name="x4300_dsp10",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4636 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4636_x4644_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4300_dsp11 = MemoryPipeline(name="x4300_dsp11",parent="x5009") { implicit CU => 
      val x4350_x4350 =  VectorFIFO(size=1).wtPort(x4331_x4342_data_v)
      val x4344 = CounterChain.copy("x4351", "x4344")
      val x4355 = CounterChain.copy("x5008", "x4355")
      val x4300_x4624 =  SRAM(size=16,banking = Strided(1)).wtPort(x4350_x4350.readPort).rdPort(x4300_x4624_x4632_v).rdAddr(x4355(0)).wtAddr(x4344(0))
      var stage: List[Stage] = Nil
    }
    val x4329 = StreamController(name="x4329",parent=x5009) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x4302 = CounterChain(name = "x4302", ctr5)
    }
    val x4317_0 = Pipeline(name="x4317_0",parent=x4329) { implicit CU => 
      val x4307 = CU.temp
      val x4308 = CU.temp
      val x4306 = CU.temp
      val x4305 =  ScalarBuffer().wtPort(x4305_argin)
      val x4298 = CounterChain.copy("x5009", "x4298")
      val x4302 = CounterChain.copy("x4329", "x4302")
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4317_unit = CounterChain(name = "x4317_unit", ctr6)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4298(0)), CU.ctr(x4302(0))), op=FixAdd, results=List(x4306))
      Stage(operands=List(x4306, Const(192)), op=FixMul, results=List(x4307))
      Stage(operands=List(x4307, Const(4)), op=FixMul, results=List(x4308))
      Stage(operands=List(x4308, CU.load(x4305)), op=FixAdd, results=List(CU.scalarOut(x4303_b5111_x4316_b5113_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x4303_b5112_x4316_b5114_s)))
    }
    val x4318 = MemoryController(name="x4318",parent=x4329,offchip=x4280_oc, mctpe=TileLoad) { implicit CU => 
      val x4303_b5112_x4318 =  ScalarFIFO(name="size",size=1).wtPort(x4303_b5112_x4316_b5114_s)
      val x4303_b5111_x4318 =  ScalarFIFO(name="offset",size=1).wtPort(x4303_b5111_x4316_b5113_s)
      CU.newVout("data", x4304_x4318_data_v)
    }
    val x4328 = Pipeline(name="x4328",parent=x4329) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4320 = CounterChain(name = "x4320", ctr7)
      var stage: List[Stage] = Nil
    }
    val x4352 = StreamController(name="x4352",parent=x5009) { implicit CU => 
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4352_unit = CounterChain(name = "x4352_unit", ctr8)
    }
    val x4341_0 = Pipeline(name="x4341_0",parent=x4352) { implicit CU => 
      val x4333 = CU.temp
      val x4332 =  ScalarBuffer().wtPort(x4332_argin)
      val x4298 = CounterChain.copy("x5009", "x4298")
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4341_unit = CounterChain(name = "x4341_unit", ctr9)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4298(0)), Const(4)), op=FixMul, results=List(x4333))
      Stage(operands=List(x4333, CU.load(x4332)), op=FixAdd, results=List(CU.scalarOut(x4330_b5117_x4340_b5119_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x4330_b5118_x4340_b5120_s)))
    }
    val x4342 = MemoryController(name="x4342",parent=x4352,offchip=x4282_oc, mctpe=TileLoad) { implicit CU => 
      val x4330_b5118_x4342 =  ScalarFIFO(name="size",size=1).wtPort(x4330_b5118_x4340_b5120_s)
      val x4330_b5117_x4342 =  ScalarFIFO(name="offset",size=1).wtPort(x4330_b5117_x4340_b5119_s)
      CU.newVout("data", x4331_x4342_data_v)
    }
    val x4351 = Pipeline(name="x4351",parent=x4352) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x4344 = CounterChain(name = "x4344", ctr10)
      var stage: List[Stage] = Nil
    }
    val x5008 = MetaPipeline(name="x5008",parent=x5009) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=12) // Counter
      val x4355 = CounterChain(name = "x4355", ctr11)
    }
    val x4368_dsp0 = MemoryPipeline(name="x4368_dsp0",parent="x5008") { implicit CU => 
      val x4776_x4776 =  VectorFIFO(size=1).wtPort(x4368_x4776_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4767 = CounterChain.copy("x4777_0", "x4767")
      val x4368_x4916 =  SRAM(size=192,banking = Strided(1)).wtPort(x4776_x4776.readPort).rdPort(x4368_x4916_x5006_v).rdAddr(x4912(0)).wtAddr(x4767(0))
      var stage: List[Stage] = Nil
    }
    val x4369_dsp0 = MemoryPipeline(name="x4369_dsp0",parent="x5008") { implicit CU => 
      val x4788_x4788 =  VectorFIFO(size=1).wtPort(x4369_x4788_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4779 = CounterChain.copy("x4789_0", "x4779")
      val x4369_x4917 =  SRAM(size=192,banking = Strided(1)).wtPort(x4788_x4788.readPort).rdPort(x4369_x4917_x5006_v).rdAddr(x4912(0)).wtAddr(x4779(0))
      var stage: List[Stage] = Nil
    }
    val x4370_dsp0 = MemoryPipeline(name="x4370_dsp0",parent="x5008") { implicit CU => 
      val x4800_x4800 =  VectorFIFO(size=1).wtPort(x4370_x4800_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4791 = CounterChain.copy("x4801_0", "x4791")
      val x4370_x4918 =  SRAM(size=192,banking = Strided(1)).wtPort(x4800_x4800.readPort).rdPort(x4370_x4918_x5006_v).rdAddr(x4912(0)).wtAddr(x4791(0))
      var stage: List[Stage] = Nil
    }
    val x4371_dsp0 = MemoryPipeline(name="x4371_dsp0",parent="x5008") { implicit CU => 
      val x4812_x4812 =  VectorFIFO(size=1).wtPort(x4371_x4812_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4803 = CounterChain.copy("x4813_0", "x4803")
      val x4371_x4919 =  SRAM(size=192,banking = Strided(1)).wtPort(x4812_x4812.readPort).rdPort(x4371_x4919_x5006_v).rdAddr(x4912(0)).wtAddr(x4803(0))
      var stage: List[Stage] = Nil
    }
    val x4372_dsp0 = MemoryPipeline(name="x4372_dsp0",parent="x5008") { implicit CU => 
      val x4824_x4824 =  VectorFIFO(size=1).wtPort(x4372_x4824_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4815 = CounterChain.copy("x4825_0", "x4815")
      val x4372_x4920 =  SRAM(size=192,banking = Strided(1)).wtPort(x4824_x4824.readPort).rdPort(x4372_x4920_x5006_v).rdAddr(x4912(0)).wtAddr(x4815(0))
      var stage: List[Stage] = Nil
    }
    val x4373_dsp0 = MemoryPipeline(name="x4373_dsp0",parent="x5008") { implicit CU => 
      val x4836_x4836 =  VectorFIFO(size=1).wtPort(x4373_x4836_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4827 = CounterChain.copy("x4837_0", "x4827")
      val x4373_x4921 =  SRAM(size=192,banking = Strided(1)).wtPort(x4836_x4836.readPort).rdPort(x4373_x4921_x5006_v).rdAddr(x4912(0)).wtAddr(x4827(0))
      var stage: List[Stage] = Nil
    }
    val x4374_dsp0 = MemoryPipeline(name="x4374_dsp0",parent="x5008") { implicit CU => 
      val x4848_x4848 =  VectorFIFO(size=1).wtPort(x4374_x4848_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4839 = CounterChain.copy("x4849_0", "x4839")
      val x4374_x4922 =  SRAM(size=192,banking = Strided(1)).wtPort(x4848_x4848.readPort).rdPort(x4374_x4922_x5006_v).rdAddr(x4912(0)).wtAddr(x4839(0))
      var stage: List[Stage] = Nil
    }
    val x4375_dsp0 = MemoryPipeline(name="x4375_dsp0",parent="x5008") { implicit CU => 
      val x4860_x4860 =  VectorFIFO(size=1).wtPort(x4375_x4860_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4851 = CounterChain.copy("x4861_0", "x4851")
      val x4375_x4923 =  SRAM(size=192,banking = Strided(1)).wtPort(x4860_x4860.readPort).rdPort(x4375_x4923_x5006_v).rdAddr(x4912(0)).wtAddr(x4851(0))
      var stage: List[Stage] = Nil
    }
    val x4376_dsp0 = MemoryPipeline(name="x4376_dsp0",parent="x5008") { implicit CU => 
      val x4872_x4872 =  VectorFIFO(size=1).wtPort(x4376_x4872_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4863 = CounterChain.copy("x4873_0", "x4863")
      val x4376_x4924 =  SRAM(size=192,banking = Strided(1)).wtPort(x4872_x4872.readPort).rdPort(x4376_x4924_x5006_v).rdAddr(x4912(0)).wtAddr(x4863(0))
      var stage: List[Stage] = Nil
    }
    val x4377_dsp0 = MemoryPipeline(name="x4377_dsp0",parent="x5008") { implicit CU => 
      val x4884_x4884 =  VectorFIFO(size=1).wtPort(x4377_x4884_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4875 = CounterChain.copy("x4885_0", "x4875")
      val x4377_x4925 =  SRAM(size=192,banking = Strided(1)).wtPort(x4884_x4884.readPort).rdPort(x4377_x4925_x5006_v).rdAddr(x4912(0)).wtAddr(x4875(0))
      var stage: List[Stage] = Nil
    }
    val x4378_dsp0 = MemoryPipeline(name="x4378_dsp0",parent="x5008") { implicit CU => 
      val x4896_x4896 =  VectorFIFO(size=1).wtPort(x4378_x4896_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4887 = CounterChain.copy("x4897_0", "x4887")
      val x4378_x4926 =  SRAM(size=192,banking = Strided(1)).wtPort(x4896_x4896.readPort).rdPort(x4378_x4926_x5006_v).rdAddr(x4912(0)).wtAddr(x4887(0))
      var stage: List[Stage] = Nil
    }
    val x4379_dsp0 = MemoryPipeline(name="x4379_dsp0",parent="x5008") { implicit CU => 
      val x4908_x4908 =  VectorFIFO(size=1).wtPort(x4379_x4908_v)
      val x4912 = CounterChain.copy("x5006", "x4912")
      val x4899 = CounterChain.copy("x4909_0", "x4899")
      val x4379_x4927 =  SRAM(size=192,banking = Strided(1)).wtPort(x4908_x4908.readPort).rdPort(x4379_x4927_x5006_v).rdAddr(x4912(0)).wtAddr(x4899(0))
      var stage: List[Stage] = Nil
    }
    val x4410_0 = Pipeline(name="x4410_0",parent=x5008) { implicit CU => 
      val x4399_x4399 =  VectorFIFO(size=1).wtPort(x4289_x4399_x4410_v)
      val x4398_x4398 =  VectorFIFO(size=1).wtPort(x4299_x4398_x4410_v)
      val ctr12 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4393 = CounterChain(name = "x4393", ctr12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4398_x4398), CU.load(x4399_x4399)), op=FltMul, results=List(CU.reduce))
      val (_, rr27773) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27773), op=Bypass, results=List(CU.scalarOut(x4380_x4408_s)))
    }
    val x4429_0 = Pipeline(name="x4429_0",parent=x5008) { implicit CU => 
      val x4418_x4418 =  VectorFIFO(size=1).wtPort(x4289_x4418_x4429_v)
      val x4417_x4417 =  VectorFIFO(size=1).wtPort(x4299_x4417_x4429_v)
      val ctr13 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4412 = CounterChain(name = "x4412", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4417_x4417), CU.load(x4418_x4418)), op=FltMul, results=List(CU.reduce))
      val (_, rr27784) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27784), op=Bypass, results=List(CU.scalarOut(x4381_x4427_s)))
    }
    val x4448_0 = Pipeline(name="x4448_0",parent=x5008) { implicit CU => 
      val x4437_x4437 =  VectorFIFO(size=1).wtPort(x4289_x4437_x4448_v)
      val x4436_x4436 =  VectorFIFO(size=1).wtPort(x4299_x4436_x4448_v)
      val ctr14 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4431 = CounterChain(name = "x4431", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4436_x4436), CU.load(x4437_x4437)), op=FltMul, results=List(CU.reduce))
      val (_, rr27795) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27795), op=Bypass, results=List(CU.scalarOut(x4382_x4446_s)))
    }
    val x4467_0 = Pipeline(name="x4467_0",parent=x5008) { implicit CU => 
      val x4455_x4455 =  VectorFIFO(size=1).wtPort(x4299_x4455_x4467_v)
      val x4456_x4456 =  VectorFIFO(size=1).wtPort(x4289_x4456_x4467_v)
      val ctr15 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4450 = CounterChain(name = "x4450", ctr15)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4455_x4455), CU.load(x4456_x4456)), op=FltMul, results=List(CU.reduce))
      val (_, rr27806) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27806), op=Bypass, results=List(CU.scalarOut(x4383_x4465_s)))
    }
    val x4486_0 = Pipeline(name="x4486_0",parent=x5008) { implicit CU => 
      val x4475_x4475 =  VectorFIFO(size=1).wtPort(x4289_x4475_x4486_v)
      val x4474_x4474 =  VectorFIFO(size=1).wtPort(x4299_x4474_x4486_v)
      val ctr16 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4469 = CounterChain(name = "x4469", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4474_x4474), CU.load(x4475_x4475)), op=FltMul, results=List(CU.reduce))
      val (_, rr27817) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27817), op=Bypass, results=List(CU.scalarOut(x4384_x4484_s)))
    }
    val x4505_0 = Pipeline(name="x4505_0",parent=x5008) { implicit CU => 
      val x4494_x4494 =  VectorFIFO(size=1).wtPort(x4289_x4494_x4505_v)
      val x4493_x4493 =  VectorFIFO(size=1).wtPort(x4299_x4493_x4505_v)
      val ctr17 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4488 = CounterChain(name = "x4488", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4493_x4493), CU.load(x4494_x4494)), op=FltMul, results=List(CU.reduce))
      val (_, rr27828) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27828), op=Bypass, results=List(CU.scalarOut(x4385_x4503_s)))
    }
    val x4524_0 = Pipeline(name="x4524_0",parent=x5008) { implicit CU => 
      val x4512_x4512 =  VectorFIFO(size=1).wtPort(x4299_x4512_x4524_v)
      val x4513_x4513 =  VectorFIFO(size=1).wtPort(x4289_x4513_x4524_v)
      val ctr18 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4507 = CounterChain(name = "x4507", ctr18)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4512_x4512), CU.load(x4513_x4513)), op=FltMul, results=List(CU.reduce))
      val (_, rr27839) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27839), op=Bypass, results=List(CU.scalarOut(x4386_x4522_s)))
    }
    val x4543_0 = Pipeline(name="x4543_0",parent=x5008) { implicit CU => 
      val x4532_x4532 =  VectorFIFO(size=1).wtPort(x4289_x4532_x4543_v)
      val x4531_x4531 =  VectorFIFO(size=1).wtPort(x4299_x4531_x4543_v)
      val ctr19 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4526 = CounterChain(name = "x4526", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4531_x4531), CU.load(x4532_x4532)), op=FltMul, results=List(CU.reduce))
      val (_, rr27850) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27850), op=Bypass, results=List(CU.scalarOut(x4387_x4541_s)))
    }
    val x4562_0 = Pipeline(name="x4562_0",parent=x5008) { implicit CU => 
      val x4551_x4551 =  VectorFIFO(size=1).wtPort(x4289_x4551_x4562_v)
      val x4550_x4550 =  VectorFIFO(size=1).wtPort(x4299_x4550_x4562_v)
      val ctr20 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4545 = CounterChain(name = "x4545", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4550_x4550), CU.load(x4551_x4551)), op=FltMul, results=List(CU.reduce))
      val (_, rr27861) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27861), op=Bypass, results=List(CU.scalarOut(x4388_x4560_s)))
    }
    val x4581_0 = Pipeline(name="x4581_0",parent=x5008) { implicit CU => 
      val x4569_x4569 =  VectorFIFO(size=1).wtPort(x4299_x4569_x4581_v)
      val x4570_x4570 =  VectorFIFO(size=1).wtPort(x4289_x4570_x4581_v)
      val ctr21 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4564 = CounterChain(name = "x4564", ctr21)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4569_x4569), CU.load(x4570_x4570)), op=FltMul, results=List(CU.reduce))
      val (_, rr27872) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27872), op=Bypass, results=List(CU.scalarOut(x4389_x4579_s)))
    }
    val x4600_0 = Pipeline(name="x4600_0",parent=x5008) { implicit CU => 
      val x4589_x4589 =  VectorFIFO(size=1).wtPort(x4289_x4589_x4600_v)
      val x4588_x4588 =  VectorFIFO(size=1).wtPort(x4299_x4588_x4600_v)
      val ctr22 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4583 = CounterChain(name = "x4583", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4588_x4588), CU.load(x4589_x4589)), op=FltMul, results=List(CU.reduce))
      val (_, rr27883) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27883), op=Bypass, results=List(CU.scalarOut(x4390_x4598_s)))
    }
    val x4619_0 = Pipeline(name="x4619_0",parent=x5008) { implicit CU => 
      val x4608_x4608 =  VectorFIFO(size=1).wtPort(x4289_x4608_x4619_v)
      val x4607_x4607 =  VectorFIFO(size=1).wtPort(x4299_x4607_x4619_v)
      val ctr23 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4602 = CounterChain(name = "x4602", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4607_x4607), CU.load(x4608_x4608)), op=FltMul, results=List(CU.reduce))
      val (_, rr27894) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr27894), op=Bypass, results=List(CU.scalarOut(x4391_x4617_s)))
    }
    val x4632_0 = Pipeline(name="x4632_0",parent=x5008) { implicit CU => 
      val x4626 = CU.temp
      val x4628 = CU.temp
      val x4629 = CU.temp
      val x4627 = CU.temp
      val x4380_x4625 =  ScalarBuffer().wtPort(x4380_x4408_s)
      val x4624_x4624 =  VectorFIFO(size=1).wtPort(x4300_x4624_x4632_v)
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4632_unit = CounterChain(name = "x4632_unit", ctr24)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4380_x4625)), op=FltNeg, results=List(x4626))
      Stage(operands=List(x4626), op=FltExp, results=List(x4627))
      Stage(operands=List(x4627, Const(1)), op=FltAdd, results=List(x4628))
      Stage(operands=List(Const(1), x4628), op=FltDiv, results=List(x4629))
      Stage(operands=List(CU.load(x4624_x4624), x4629), op=FltSub, results=List(CU.scalarOut(x4356_x4631_s)))
    }
    val x4644_0 = Pipeline(name="x4644_0",parent=x5008) { implicit CU => 
      val x4641 = CU.temp
      val x4639 = CU.temp
      val x4638 = CU.temp
      val x4640 = CU.temp
      val x4381_x4637 =  ScalarBuffer().wtPort(x4381_x4427_s)
      val x4636_x4636 =  VectorFIFO(size=1).wtPort(x4300_x4636_x4644_v)
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4644_unit = CounterChain(name = "x4644_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4381_x4637)), op=FltNeg, results=List(x4638))
      Stage(operands=List(x4638), op=FltExp, results=List(x4639))
      Stage(operands=List(x4639, Const(1)), op=FltAdd, results=List(x4640))
      Stage(operands=List(Const(1), x4640), op=FltDiv, results=List(x4641))
      Stage(operands=List(CU.load(x4636_x4636), x4641), op=FltSub, results=List(CU.scalarOut(x4357_x4643_s)))
    }
    val x4656_0 = Pipeline(name="x4656_0",parent=x5008) { implicit CU => 
      val x4650 = CU.temp
      val x4652 = CU.temp
      val x4651 = CU.temp
      val x4653 = CU.temp
      val x4648_x4648 =  VectorFIFO(size=1).wtPort(x4300_x4648_x4656_v)
      val x4382_x4649 =  ScalarBuffer().wtPort(x4382_x4446_s)
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4656_unit = CounterChain(name = "x4656_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4382_x4649)), op=FltNeg, results=List(x4650))
      Stage(operands=List(x4650), op=FltExp, results=List(x4651))
      Stage(operands=List(x4651, Const(1)), op=FltAdd, results=List(x4652))
      Stage(operands=List(Const(1), x4652), op=FltDiv, results=List(x4653))
      Stage(operands=List(CU.load(x4648_x4648), x4653), op=FltSub, results=List(CU.scalarOut(x4358_x4655_s)))
    }
    val x4668_0 = Pipeline(name="x4668_0",parent=x5008) { implicit CU => 
      val x4664 = CU.temp
      val x4665 = CU.temp
      val x4663 = CU.temp
      val x4662 = CU.temp
      val x4383_x4661 =  ScalarBuffer().wtPort(x4383_x4465_s)
      val x4660_x4660 =  VectorFIFO(size=1).wtPort(x4300_x4660_x4668_v)
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4668_unit = CounterChain(name = "x4668_unit", ctr27)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4383_x4661)), op=FltNeg, results=List(x4662))
      Stage(operands=List(x4662), op=FltExp, results=List(x4663))
      Stage(operands=List(x4663, Const(1)), op=FltAdd, results=List(x4664))
      Stage(operands=List(Const(1), x4664), op=FltDiv, results=List(x4665))
      Stage(operands=List(CU.load(x4660_x4660), x4665), op=FltSub, results=List(CU.scalarOut(x4359_x4667_s)))
    }
    val x4680_0 = Pipeline(name="x4680_0",parent=x5008) { implicit CU => 
      val x4676 = CU.temp
      val x4675 = CU.temp
      val x4677 = CU.temp
      val x4674 = CU.temp
      val x4672_x4672 =  VectorFIFO(size=1).wtPort(x4300_x4672_x4680_v)
      val x4384_x4673 =  ScalarBuffer().wtPort(x4384_x4484_s)
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4680_unit = CounterChain(name = "x4680_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4384_x4673)), op=FltNeg, results=List(x4674))
      Stage(operands=List(x4674), op=FltExp, results=List(x4675))
      Stage(operands=List(x4675, Const(1)), op=FltAdd, results=List(x4676))
      Stage(operands=List(Const(1), x4676), op=FltDiv, results=List(x4677))
      Stage(operands=List(CU.load(x4672_x4672), x4677), op=FltSub, results=List(CU.scalarOut(x4360_x4679_s)))
    }
    val x4692_0 = Pipeline(name="x4692_0",parent=x5008) { implicit CU => 
      val x4686 = CU.temp
      val x4689 = CU.temp
      val x4688 = CU.temp
      val x4687 = CU.temp
      val x4385_x4685 =  ScalarBuffer().wtPort(x4385_x4503_s)
      val x4684_x4684 =  VectorFIFO(size=1).wtPort(x4300_x4684_x4692_v)
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4692_unit = CounterChain(name = "x4692_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4385_x4685)), op=FltNeg, results=List(x4686))
      Stage(operands=List(x4686), op=FltExp, results=List(x4687))
      Stage(operands=List(x4687, Const(1)), op=FltAdd, results=List(x4688))
      Stage(operands=List(Const(1), x4688), op=FltDiv, results=List(x4689))
      Stage(operands=List(CU.load(x4684_x4684), x4689), op=FltSub, results=List(CU.scalarOut(x4361_x4691_s)))
    }
    val x4704_0 = Pipeline(name="x4704_0",parent=x5008) { implicit CU => 
      val x4699 = CU.temp
      val x4700 = CU.temp
      val x4698 = CU.temp
      val x4701 = CU.temp
      val x4386_x4697 =  ScalarBuffer().wtPort(x4386_x4522_s)
      val x4696_x4696 =  VectorFIFO(size=1).wtPort(x4300_x4696_x4704_v)
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4704_unit = CounterChain(name = "x4704_unit", ctr30)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4386_x4697)), op=FltNeg, results=List(x4698))
      Stage(operands=List(x4698), op=FltExp, results=List(x4699))
      Stage(operands=List(x4699, Const(1)), op=FltAdd, results=List(x4700))
      Stage(operands=List(Const(1), x4700), op=FltDiv, results=List(x4701))
      Stage(operands=List(CU.load(x4696_x4696), x4701), op=FltSub, results=List(CU.scalarOut(x4362_x4703_s)))
    }
    val x4716_0 = Pipeline(name="x4716_0",parent=x5008) { implicit CU => 
      val x4710 = CU.temp
      val x4712 = CU.temp
      val x4711 = CU.temp
      val x4713 = CU.temp
      val x4708_x4708 =  VectorFIFO(size=1).wtPort(x4300_x4708_x4716_v)
      val x4387_x4709 =  ScalarBuffer().wtPort(x4387_x4541_s)
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4716_unit = CounterChain(name = "x4716_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4387_x4709)), op=FltNeg, results=List(x4710))
      Stage(operands=List(x4710), op=FltExp, results=List(x4711))
      Stage(operands=List(x4711, Const(1)), op=FltAdd, results=List(x4712))
      Stage(operands=List(Const(1), x4712), op=FltDiv, results=List(x4713))
      Stage(operands=List(CU.load(x4708_x4708), x4713), op=FltSub, results=List(CU.scalarOut(x4363_x4715_s)))
    }
    val x4728_0 = Pipeline(name="x4728_0",parent=x5008) { implicit CU => 
      val x4724 = CU.temp
      val x4723 = CU.temp
      val x4722 = CU.temp
      val x4725 = CU.temp
      val x4720_x4720 =  VectorFIFO(size=1).wtPort(x4300_x4720_x4728_v)
      val x4388_x4721 =  ScalarBuffer().wtPort(x4388_x4560_s)
      val ctr32 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4728_unit = CounterChain(name = "x4728_unit", ctr32)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4388_x4721)), op=FltNeg, results=List(x4722))
      Stage(operands=List(x4722), op=FltExp, results=List(x4723))
      Stage(operands=List(x4723, Const(1)), op=FltAdd, results=List(x4724))
      Stage(operands=List(Const(1), x4724), op=FltDiv, results=List(x4725))
      Stage(operands=List(CU.load(x4720_x4720), x4725), op=FltSub, results=List(CU.scalarOut(x4364_x4727_s)))
    }
    val x4740_0 = Pipeline(name="x4740_0",parent=x5008) { implicit CU => 
      val x4737 = CU.temp
      val x4735 = CU.temp
      val x4734 = CU.temp
      val x4736 = CU.temp
      val x4389_x4733 =  ScalarBuffer().wtPort(x4389_x4579_s)
      val x4732_x4732 =  VectorFIFO(size=1).wtPort(x4300_x4732_x4740_v)
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4740_unit = CounterChain(name = "x4740_unit", ctr33)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4389_x4733)), op=FltNeg, results=List(x4734))
      Stage(operands=List(x4734), op=FltExp, results=List(x4735))
      Stage(operands=List(x4735, Const(1)), op=FltAdd, results=List(x4736))
      Stage(operands=List(Const(1), x4736), op=FltDiv, results=List(x4737))
      Stage(operands=List(CU.load(x4732_x4732), x4737), op=FltSub, results=List(CU.scalarOut(x4365_x4739_s)))
    }
    val x4752_0 = Pipeline(name="x4752_0",parent=x5008) { implicit CU => 
      val x4748 = CU.temp
      val x4747 = CU.temp
      val x4749 = CU.temp
      val x4746 = CU.temp
      val x4390_x4745 =  ScalarBuffer().wtPort(x4390_x4598_s)
      val x4744_x4744 =  VectorFIFO(size=1).wtPort(x4300_x4744_x4752_v)
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4752_unit = CounterChain(name = "x4752_unit", ctr34)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4390_x4745)), op=FltNeg, results=List(x4746))
      Stage(operands=List(x4746), op=FltExp, results=List(x4747))
      Stage(operands=List(x4747, Const(1)), op=FltAdd, results=List(x4748))
      Stage(operands=List(Const(1), x4748), op=FltDiv, results=List(x4749))
      Stage(operands=List(CU.load(x4744_x4744), x4749), op=FltSub, results=List(CU.scalarOut(x4366_x4751_s)))
    }
    val x4764_0 = Pipeline(name="x4764_0",parent=x5008) { implicit CU => 
      val x4760 = CU.temp
      val x4759 = CU.temp
      val x4758 = CU.temp
      val x4761 = CU.temp
      val x4756_x4756 =  VectorFIFO(size=1).wtPort(x4300_x4756_x4764_v)
      val x4391_x4757 =  ScalarBuffer().wtPort(x4391_x4617_s)
      val ctr35 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4764_unit = CounterChain(name = "x4764_unit", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4391_x4757)), op=FltNeg, results=List(x4758))
      Stage(operands=List(x4758), op=FltExp, results=List(x4759))
      Stage(operands=List(x4759, Const(1)), op=FltAdd, results=List(x4760))
      Stage(operands=List(Const(1), x4760), op=FltDiv, results=List(x4761))
      Stage(operands=List(CU.load(x4756_x4756), x4761), op=FltSub, results=List(CU.scalarOut(x4367_x4763_s)))
    }
    val x4777_0 = Pipeline(name="x4777_0",parent=x5008) { implicit CU => 
      val x4772_x4772 =  VectorFIFO(size=1).wtPort(x4299_x4772_x4777_v)
      val x4356_x4773 =  ScalarBuffer().wtPort(x4356_x4631_s)
      val ctr36 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4767 = CounterChain(name = "x4767", ctr36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4772_x4772), CU.load(x4356_x4773)), op=FltSub, results=List(CU.vecOut(x4368_x4776_v)))
    }
    val x4789_0 = Pipeline(name="x4789_0",parent=x5008) { implicit CU => 
      val x4784_x4784 =  VectorFIFO(size=1).wtPort(x4299_x4784_x4789_v)
      val x4357_x4785 =  ScalarBuffer().wtPort(x4357_x4643_s)
      val ctr37 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4779 = CounterChain(name = "x4779", ctr37)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4784_x4784), CU.load(x4357_x4785)), op=FltSub, results=List(CU.vecOut(x4369_x4788_v)))
    }
    val x4801_0 = Pipeline(name="x4801_0",parent=x5008) { implicit CU => 
      val x4796_x4796 =  VectorFIFO(size=1).wtPort(x4299_x4796_x4801_v)
      val x4358_x4797 =  ScalarBuffer().wtPort(x4358_x4655_s)
      val ctr38 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4791 = CounterChain(name = "x4791", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4796_x4796), CU.load(x4358_x4797)), op=FltSub, results=List(CU.vecOut(x4370_x4800_v)))
    }
    val x4813_0 = Pipeline(name="x4813_0",parent=x5008) { implicit CU => 
      val x4808_x4808 =  VectorFIFO(size=1).wtPort(x4299_x4808_x4813_v)
      val x4359_x4809 =  ScalarBuffer().wtPort(x4359_x4667_s)
      val ctr39 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4803 = CounterChain(name = "x4803", ctr39)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4808_x4808), CU.load(x4359_x4809)), op=FltSub, results=List(CU.vecOut(x4371_x4812_v)))
    }
    val x4825_0 = Pipeline(name="x4825_0",parent=x5008) { implicit CU => 
      val x4820_x4820 =  VectorFIFO(size=1).wtPort(x4299_x4820_x4825_v)
      val x4360_x4821 =  ScalarBuffer().wtPort(x4360_x4679_s)
      val ctr40 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4815 = CounterChain(name = "x4815", ctr40)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4820_x4820), CU.load(x4360_x4821)), op=FltSub, results=List(CU.vecOut(x4372_x4824_v)))
    }
    val x4837_0 = Pipeline(name="x4837_0",parent=x5008) { implicit CU => 
      val x4832_x4832 =  VectorFIFO(size=1).wtPort(x4299_x4832_x4837_v)
      val x4361_x4833 =  ScalarBuffer().wtPort(x4361_x4691_s)
      val ctr41 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4827 = CounterChain(name = "x4827", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4832_x4832), CU.load(x4361_x4833)), op=FltSub, results=List(CU.vecOut(x4373_x4836_v)))
    }
    val x4849_0 = Pipeline(name="x4849_0",parent=x5008) { implicit CU => 
      val x4844_x4844 =  VectorFIFO(size=1).wtPort(x4299_x4844_x4849_v)
      val x4362_x4845 =  ScalarBuffer().wtPort(x4362_x4703_s)
      val ctr42 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4839 = CounterChain(name = "x4839", ctr42)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4844_x4844), CU.load(x4362_x4845)), op=FltSub, results=List(CU.vecOut(x4374_x4848_v)))
    }
    val x4861_0 = Pipeline(name="x4861_0",parent=x5008) { implicit CU => 
      val x4363_x4857 =  ScalarBuffer().wtPort(x4363_x4715_s)
      val x4856_x4856 =  VectorFIFO(size=1).wtPort(x4299_x4856_x4861_v)
      val ctr43 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4851 = CounterChain(name = "x4851", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4856_x4856), CU.load(x4363_x4857)), op=FltSub, results=List(CU.vecOut(x4375_x4860_v)))
    }
    val x4873_0 = Pipeline(name="x4873_0",parent=x5008) { implicit CU => 
      val x4868_x4868 =  VectorFIFO(size=1).wtPort(x4299_x4868_x4873_v)
      val x4364_x4869 =  ScalarBuffer().wtPort(x4364_x4727_s)
      val ctr44 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4863 = CounterChain(name = "x4863", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4868_x4868), CU.load(x4364_x4869)), op=FltSub, results=List(CU.vecOut(x4376_x4872_v)))
    }
    val x4885_0 = Pipeline(name="x4885_0",parent=x5008) { implicit CU => 
      val x4880_x4880 =  VectorFIFO(size=1).wtPort(x4299_x4880_x4885_v)
      val x4365_x4881 =  ScalarBuffer().wtPort(x4365_x4739_s)
      val ctr45 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4875 = CounterChain(name = "x4875", ctr45)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4880_x4880), CU.load(x4365_x4881)), op=FltSub, results=List(CU.vecOut(x4377_x4884_v)))
    }
    val x4897_0 = Pipeline(name="x4897_0",parent=x5008) { implicit CU => 
      val x4892_x4892 =  VectorFIFO(size=1).wtPort(x4299_x4892_x4897_v)
      val x4366_x4893 =  ScalarBuffer().wtPort(x4366_x4751_s)
      val ctr46 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4887 = CounterChain(name = "x4887", ctr46)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4892_x4892), CU.load(x4366_x4893)), op=FltSub, results=List(CU.vecOut(x4378_x4896_v)))
    }
    val x4909_0 = Pipeline(name="x4909_0",parent=x5008) { implicit CU => 
      val x4904_x4904 =  VectorFIFO(size=1).wtPort(x4299_x4904_x4909_v)
      val x4367_x4905 =  ScalarBuffer().wtPort(x4367_x4763_s)
      val ctr47 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4899 = CounterChain(name = "x4899", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4904_x4904), CU.load(x4367_x4905)), op=FltSub, results=List(CU.vecOut(x4379_x4908_v)))
    }
    val x5006 = StreamController(name="x5006",parent=x5008) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4912 = CounterChain(name = "x4912", ctr48)
    }
    val x5006_0 = Pipeline(name="x5006_0",parent=x5006) { implicit CU => 
      val x4961 = CU.temp
      val x4950 = CU.temp
      val x4919_x4919 =  VectorFIFO(size=1).wtPort(x4371_x4919_x5006_v)
      val x4916_x4916 =  VectorFIFO(size=1).wtPort(x4368_x4916_x5006_v)
      val x4918_x4918 =  VectorFIFO(size=1).wtPort(x4370_x4918_x5006_v)
      val x4917_x4917 =  VectorFIFO(size=1).wtPort(x4369_x4917_x5006_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4916_x4916), CU.load(x4917_x4917)), op=FltAdd, results=List(x4950))
      Stage(operands=List(CU.load(x4918_x4918), CU.load(x4919_x4919)), op=FltAdd, results=List(x4961))
      Stage(operands=List(x4950, x4961), op=FltAdd, results=List(CU.scalarOut(bus_28096_s)))
    }
    val x5006_1 = Pipeline(name="x5006_1",parent=x5006) { implicit CU => 
      val x4988 = CU.temp
      val x4982 = CU.temp
      val x4986 = CU.temp
      val x4920_x4920 =  VectorFIFO(size=1).wtPort(x4372_x4920_x5006_v)
      val x4921_x4921 =  VectorFIFO(size=1).wtPort(x4373_x4921_x5006_v)
      val x4963 =  ScalarFIFO(size=1).wtPort(bus_28096_s)
      val x4922_x4922 =  VectorFIFO(size=1).wtPort(x4374_x4922_x5006_v)
      val x4923_x4923 =  VectorFIFO(size=1).wtPort(x4375_x4923_x5006_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4920_x4920), CU.load(x4921_x4921)), op=FltAdd, results=List(x4982))
      Stage(operands=List(CU.load(x4922_x4922), CU.load(x4923_x4923)), op=FltAdd, results=List(x4986))
      Stage(operands=List(x4982, x4986), op=FltAdd, results=List(x4988))
      Stage(operands=List(CU.load(x4963), x4988), op=FltAdd, results=List(CU.scalarOut(bus_28115_s)))
    }
    val x5006_2 = Pipeline(name="x5006_2",parent=x5006) { implicit CU => 
      val x5000 = CU.temp
      val x4994 = CU.temp
      val x4998 = CU.temp
      val x4924_x4924 =  VectorFIFO(size=1).wtPort(x4376_x4924_x5006_v)
      val x4990 =  ScalarFIFO(size=1).wtPort(bus_28115_s)
      val x4925_x4925 =  VectorFIFO(size=1).wtPort(x4377_x4925_x5006_v)
      val x4926_x4926 =  VectorFIFO(size=1).wtPort(x4378_x4926_x5006_v)
      val x4927_x4927 =  VectorFIFO(size=1).wtPort(x4379_x4927_x5006_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4924_x4924), CU.load(x4925_x4925)), op=FltAdd, results=List(x4994))
      Stage(operands=List(CU.load(x4926_x4926), CU.load(x4927_x4927)), op=FltAdd, results=List(x4998))
      Stage(operands=List(x4994, x4998), op=FltAdd, results=List(x5000))
      Stage(operands=List(CU.load(x4990), x5000), op=FltAdd, results=List(CU.scalarOut(bus_28119_s)))
    }
    val x5006_3 = Pipeline(name="x5006_3",parent=x5006) { implicit CU => 
      val x4928_x4928 =  VectorFIFO(size=1).wtPort(x4295_x4928_x5006_v)
      val x5002 =  ScalarFIFO(size=1).wtPort(bus_28119_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5002), CU.load(x4928_x4928)), op=FltAdd, results=List(CU.vecOut(x4295_x5005_v)))
    }
    val x5020_0 = Pipeline(name="x5020_0",parent=x5023) { implicit CU => 
      val x5017 = CU.temp
      val x5014_x5014 =  VectorFIFO(size=1).wtPort(x4289_x5014_x5020_v)
      val x5013_x5013 =  VectorFIFO(size=1).wtPort(x4295_x5013_x5020_v)
      val ctr49 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x5011 = CounterChain(name = "x5011", ctr49)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5014_x5014), Const(1)), op=FltMul, results=List(x5017))
      Stage(operands=List(CU.load(x5013_x5013), x5017), op=FltAdd, results=List(CU.vecOut(x4289_x5019_v)))
    }
    val x5049 = StreamController(name="x5049",parent=x5050) { implicit CU => 
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5049_unit = CounterChain(name = "x5049_unit", ctr50)
    }
    val x5042 = Sequential(name="x5042",parent=x5049) { implicit CU => 
      val ctr51 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5042_unit = CounterChain(name = "x5042_unit", ctr51)
    }
    val x5034_0 = Pipeline(name="x5034_0",parent=x5042) { implicit CU => 
      val x5028 =  ScalarBuffer().wtPort(x5028_argin)
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x5034_unit = CounterChain(name = "x5034_unit", ctr52)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x5028)), op=FixAdd, results=List(CU.scalarOut(x5025_b5169_x5033_b5171_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x5025_b5170_x5033_b5172_s)))
    }
    val x5041 = Pipeline(name="x5041",parent=x5042) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x5036 = CounterChain(name = "x5036", ctr53)
      var stage: List[Stage] = Nil
    }
    val x5043 = MemoryController(name="x5043",parent=x5049,offchip=x4283_oc, mctpe=TileStore) { implicit CU => 
      val x5025_b5170_x5043 =  ScalarFIFO(name="size",size=1).wtPort(x5025_b5170_x5033_b5172_s)
      val x5026_x5043 =  VectorFIFO(name="data",size=1).wtPort(x4289_x5037_x5041_v)
      val x5025_b5169_x5043 =  ScalarFIFO(name="offset",size=1).wtPort(x5025_b5169_x5033_b5171_s)
    }
    
  }
}
