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
    val x4330_x4727_v = Vector("x4330_x4727")
    val x4317_x4570_s = Scalar("x4317_x4570")
    val x4316_x4558_s = Scalar("x4316_x4558")
    val x4330_x4788_x4857_v = Vector("x4330_x4788_x4857")
    val x4219_x4522_x4534_v = Vector("x4219_x4522_x4534")
    val x4209_x4485_x4496_v = Vector("x4209_x4485_x4496")
    val bus_17846_s = Scalar("bus_17846")
    val x4315_x4546_s = Scalar("x4315_x4546")
    val x4334_x4792_x4857_v = Vector("x4334_x4792_x4857")
    val x4220_x4599_x4607_v = Vector("x4220_x4599_x4607")
    val x4335_x4361_s = Scalar("x4335_x4361")
    val x4876_b5021_x4884_b5023_s = Scalar("x4876_b5021_x4884_b5023")
    val x4225_argin = ArgIn("x4225")
    val x4332_x4751_v = Vector("x4332_x4751")
    val x4209_x4888_x4892_v = Vector("x4209_x4888_x4892")
    val x4329_x4787_x4857_v = Vector("x4329_x4787_x4857")
    val x4200_oc = OffChip("x4200")
    val x4344_x4532_s = Scalar("x4344_x4532")
    val x4341_x4475_s = Scalar("x4341_x4475")
    val x4220_x4551_x4559_v = Vector("x4220_x4551_x4559")
    val x4219_x4687_x4692_v = Vector("x4219_x4687_x4692")
    val x4219_x4675_x4680_v = Vector("x4219_x4675_x4680")
    val x4209_x4371_x4382_v = Vector("x4209_x4371_x4382")
    val x4220_x4623_x4631_v = Vector("x4220_x4623_x4631")
    val x4252_x4279_data_v = Vector("x4252_x4279_data")
    val x4325_x4783_x4857_v = Vector("x4325_x4783_x4857")
    val x4321_x4618_s = Scalar("x4321_x4618")
    val x4324_x4654_s = Scalar("x4324_x4654")
    val x4219_x4370_x4382_v = Vector("x4219_x4370_x4382")
    val x4215_x4856_v = Vector("x4215_x4856")
    val bus_17542_s = Scalar("bus_17542")
    val x4209_x4447_x4458_v = Vector("x4209_x4447_x4458")
    val x4325_x4667_v = Vector("x4325_x4667")
    val x4340_x4456_s = Scalar("x4340_x4456")
    val x4281_x4289_s = Scalar("x4281_x4289")
    val bus_17543_s = Scalar("bus_17543")
    val x4215_x4793_x4857_v = Vector("x4215_x4793_x4857")
    val x4326_x4679_v = Vector("x4326_x4679")
    val bus_17837_s = Scalar("bus_17837")
    val x4219_x4711_x4716_v = Vector("x4219_x4711_x4716")
    val x4220_x4647_x4655_v = Vector("x4220_x4647_x4655")
    val bus_17536_s = Scalar("bus_17536")
    val x4323_x4642_s = Scalar("x4323_x4642")
    val x4219_x4663_x4668_v = Vector("x4219_x4663_x4668")
    val x4220_x4611_x4619_v = Vector("x4220_x4611_x4619")
    val x4219_x4735_x4740_v = Vector("x4219_x4735_x4740")
    val x4219_x4771_x4776_v = Vector("x4219_x4771_x4776")
    val x4220_x4587_x4595_v = Vector("x4220_x4587_x4595")
    val x4333_x4763_v = Vector("x4333_x4763")
    val x4215_x4864_x4871_v = Vector("x4215_x4864_x4871")
    val x4220_x4539_x4547_v = Vector("x4220_x4539_x4547")
    val x4209_x4409_x4420_v = Vector("x4209_x4409_x4420")
    val x4219_x4465_x4477_v = Vector("x4219_x4465_x4477")
    val x4251_b4971_x4277_b4979_s = Scalar("x4251_b4971_x4277_b4979")
    val x4219_x4427_x4439_v = Vector("x4219_x4427_x4439")
    val x4282_x4291_s = Scalar("x4282_x4291")
    val x4219_x4759_x4764_v = Vector("x4219_x4759_x4764")
    val x4209_x4390_x4401_v = Vector("x4209_x4390_x4401")
    val x4209_x4523_x4534_v = Vector("x4209_x4523_x4534")
    val x4220_x4635_x4643_v = Vector("x4220_x4635_x4643")
    val x4219_x4446_x4458_v = Vector("x4219_x4446_x4458")
    val bus_17533_s = Scalar("bus_17533")
    val x4332_x4790_x4857_v = Vector("x4332_x4790_x4857")
    val x4329_x4715_v = Vector("x4329_x4715")
    val x4334_x4775_v = Vector("x4334_x4775")
    val x4320_x4606_s = Scalar("x4320_x4606")
    val x4219_x4723_x4728_v = Vector("x4219_x4723_x4728")
    val x4209_x4352_x4363_v = Vector("x4209_x4352_x4363")
    val x4209_x4428_x4439_v = Vector("x4209_x4428_x4439")
    val x4328_x4786_x4857_v = Vector("x4328_x4786_x4857")
    val x4219_x4503_x4515_v = Vector("x4219_x4503_x4515")
    val x4224_x4238_data_v = Vector("x4224_x4238_data")
    val x4333_x4791_x4857_v = Vector("x4333_x4791_x4857")
    val x4343_x4513_s = Scalar("x4343_x4513")
    val x4331_x4739_v = Vector("x4331_x4739")
    val x4253_argin = ArgIn("x4253")
    val x4318_x4582_s = Scalar("x4318_x4582")
    val bus_17865_s = Scalar("bus_17865")
    val x4219_x4408_x4420_v = Vector("x4219_x4408_x4420")
    val x4328_x4703_v = Vector("x4328_x4703")
    val x4223_b4962_x4236_b4964_s = Scalar("x4223_b4962_x4236_b4964")
    val bus_17534_s = Scalar("bus_17534")
    val x4196_argin = ArgIn("x4196")
    val x4322_x4630_s = Scalar("x4322_x4630")
    val x4203_oc = OffChip("x4203")
    val x4219_x4351_x4363_v = Vector("x4219_x4351_x4363")
    val x4250_b4968_x4270_b4976_s = Scalar("x4250_b4968_x4270_b4976")
    val x4327_x4785_x4857_v = Vector("x4327_x4785_x4857")
    val x4250_b4969_x4270_b4977_s = Scalar("x4250_b4969_x4270_b4977")
    val x4219_x4699_x4704_v = Vector("x4219_x4699_x4704")
    val x4280_x4287_s = Scalar("x4280_x4287")
    val x4209_x4870_v = Vector("x4209_x4870")
    val x4339_x4437_s = Scalar("x4339_x4437")
    val x4223_b4963_x4236_b4965_s = Scalar("x4223_b4963_x4236_b4965")
    val x4337_x4399_s = Scalar("x4337_x4399")
    val x4879_argin = ArgIn("x4879")
    val bus_17862_s = Scalar("bus_17862")
    val x4195_argin = ArgIn("x4195")
    val x4220_x4575_x4583_v = Vector("x4220_x4575_x4583")
    val x4219_x4484_x4496_v = Vector("x4219_x4484_x4496")
    val x4251_b4972_x4277_b4980_s = Scalar("x4251_b4972_x4277_b4980")
    val x4876_b5022_x4884_b5024_s = Scalar("x4876_b5022_x4884_b5024")
    val x4209_x4865_x4871_v = Vector("x4209_x4865_x4871")
    val bus_17531_s = Scalar("bus_17531")
    val x4342_x4494_s = Scalar("x4342_x4494")
    val x4209_x4466_x4477_v = Vector("x4209_x4466_x4477")
    val x4202_oc = OffChip("x4202")
    val x4209_x4504_x4515_v = Vector("x4209_x4504_x4515")
    val x4219_x4747_x4752_v = Vector("x4219_x4747_x4752")
    val x4251_b4970_x4277_b4978_s = Scalar("x4251_b4970_x4277_b4978")
    val x4319_x4594_s = Scalar("x4319_x4594")
    val x4327_x4691_v = Vector("x4327_x4691")
    val x4326_x4784_x4857_v = Vector("x4326_x4784_x4857")
    val x4331_x4789_x4857_v = Vector("x4331_x4789_x4857")
    val x4220_x4563_x4571_v = Vector("x4220_x4563_x4571")
    val x4338_x4418_s = Scalar("x4338_x4418")
    val x4336_x4380_s = Scalar("x4336_x4380")
    val x4219_x4389_x4401_v = Vector("x4219_x4389_x4401")
    val x4901 = Sequential(name="x4901",parent=top) { implicit CU => 
    }
    val x4209_dsp0 = MemoryPipeline(name="x4209_dsp0",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4887 = CounterChain.copy("x4892", "x4887")
      val x4209_x4888 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4888_x4892_v).rdAddr(x4887(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp1 = MemoryPipeline(name="x4209_dsp1",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4865 =  SRAM(size=192,banking = NoBanking()).wtPort(x4870_x4870.readPort).rdPort(x4209_x4865_x4871_v).rdAddr(x4862(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp2 = MemoryPipeline(name="x4209_dsp2",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4517 = CounterChain.copy("x4534_0", "x4517")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4523 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4523_x4534_v).rdAddr(x4517(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp3 = MemoryPipeline(name="x4209_dsp3",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4498 = CounterChain.copy("x4515_0", "x4498")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4504 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4504_x4515_v).rdAddr(x4498(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp4 = MemoryPipeline(name="x4209_dsp4",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4479 = CounterChain.copy("x4496_0", "x4479")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4485 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4485_x4496_v).rdAddr(x4479(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp5 = MemoryPipeline(name="x4209_dsp5",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4460 = CounterChain.copy("x4477_0", "x4460")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4466 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4466_x4477_v).rdAddr(x4460(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp6 = MemoryPipeline(name="x4209_dsp6",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4441 = CounterChain.copy("x4458_0", "x4441")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4447 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4447_x4458_v).rdAddr(x4441(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp7 = MemoryPipeline(name="x4209_dsp7",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4422 = CounterChain.copy("x4439_0", "x4422")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4428 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4428_x4439_v).rdAddr(x4422(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp8 = MemoryPipeline(name="x4209_dsp8",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4403 = CounterChain.copy("x4420_0", "x4403")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4409 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4409_x4420_v).rdAddr(x4403(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp9 = MemoryPipeline(name="x4209_dsp9",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4384 = CounterChain.copy("x4401_0", "x4384")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4390 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4390_x4401_v).rdAddr(x4384(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp10 = MemoryPipeline(name="x4209_dsp10",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4365 = CounterChain.copy("x4382_0", "x4365")
      val x4209_x4371 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4371_x4382_v).rdAddr(x4365(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4209_dsp11 = MemoryPipeline(name="x4209_dsp11",parent="x4874") { implicit CU => 
      val x4870_x4870 =  VectorFIFO(size=1).wtPort(x4209_x4870_v)
      val x4346 = CounterChain.copy("x4363_0", "x4346")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4209_x4352 =  SRAM(size=192,banking = Strided(1)).wtPort(x4870_x4870.readPort).rdPort(x4209_x4352_x4363_v).rdAddr(x4346(0)).wtAddr(x4862(0))
      var stage: List[Stage] = Nil
    }
    val x4875 = Sequential(name="x4875",parent=x4901) { implicit CU => 
      val x4195_x4210 =  ScalarBuffer().wtPort(x4195_argin)
      val ctr1 = Counter(min=Const(0), max=x4195_x4210.load, step=Const(1), par=1) // Counter
      val x4212 = CounterChain(name = "x4212", ctr1).iter(1)
    }
    val x4874 = Sequential(name="x4874",parent=x4875) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x4214 = CounterChain(name = "x4214", ctr2).iter(1)
    }
    val x4215_dsp0 = MemoryPipeline(name="x4215_dsp0",parent="x4859") { implicit CU => 
      val x4856_x4856 =  VectorFIFO(size=1).wtPort(x4215_x4856_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4862 = CounterChain.copy("x4871_0", "x4862")
      val x4215_x4864 =  SRAM(size=192,banking = NoBanking()).wtPort(x4856_x4856.readPort).rdPort(x4215_x4864_x4871_v).rdAddr(x4862(0)).wtAddr(x4779(0))
      var stage: List[Stage] = Nil
    }
    val x4215_dsp1 = MemoryPipeline(name="x4215_dsp1",parent="x4859") { implicit CU => 
      val x4856_x4856 =  VectorFIFO(size=1).wtPort(x4215_x4856_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4215_x4793 =  SRAM(size=192,banking = NoBanking()).wtPort(x4856_x4856.readPort).rdPort(x4215_x4793_x4857_v).rdAddr(x4779(0)).wtAddr(x4779(0))
      var stage: List[Stage] = Nil
    }
    val x4860 = MetaPipeline(name="x4860",parent=x4874) { implicit CU => 
      val x4196_x4216 =  ScalarBuffer().wtPort(x4196_argin)
      val ctr3 = Counter(min=Const(0), max=x4196_x4216.load, step=Const(10), par=1) // Counter
      val x4218 = CounterChain(name = "x4218", ctr3).iter(1)
    }
    val x4219_dsp0 = MemoryPipeline(name="x4219_dsp0",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5019 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4766 = CounterChain.copy("x4776_0", "x4766")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4771 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4771_x4776_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4771.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5019))
      RAStage(operands=List(b5019, CU.ctr(x4766(0))), op=FixAdd, results=List(x4219_x4771.readAddr))
    }
    val x4219_dsp1 = MemoryPipeline(name="x4219_dsp1",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5017 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4754 = CounterChain.copy("x4764_0", "x4754")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4759 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4759_x4764_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4759.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5017))
      RAStage(operands=List(b5017, CU.ctr(x4754(0))), op=FixAdd, results=List(x4219_x4759.readAddr))
    }
    val x4219_dsp2 = MemoryPipeline(name="x4219_dsp2",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5015 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4742 = CounterChain.copy("x4752_0", "x4742")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4747 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4747_x4752_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4747.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5015))
      RAStage(operands=List(b5015, CU.ctr(x4742(0))), op=FixAdd, results=List(x4219_x4747.readAddr))
    }
    val x4219_dsp3 = MemoryPipeline(name="x4219_dsp3",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5013 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4730 = CounterChain.copy("x4740_0", "x4730")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4735 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4735_x4740_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4735.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5013))
      RAStage(operands=List(b5013, CU.ctr(x4730(0))), op=FixAdd, results=List(x4219_x4735.readAddr))
    }
    val x4219_dsp4 = MemoryPipeline(name="x4219_dsp4",parent="x4860") { implicit CU => 
      val b5011 = CU.temp
      val b4966 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4718 = CounterChain.copy("x4728_0", "x4718")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4723 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4723_x4728_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4723.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5011))
      RAStage(operands=List(b5011, CU.ctr(x4718(0))), op=FixAdd, results=List(x4219_x4723.readAddr))
    }
    val x4219_dsp5 = MemoryPipeline(name="x4219_dsp5",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5009 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4706 = CounterChain.copy("x4716_0", "x4706")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4711 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4711_x4716_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4711.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5009))
      RAStage(operands=List(b5009, CU.ctr(x4706(0))), op=FixAdd, results=List(x4219_x4711.readAddr))
    }
    val x4219_dsp6 = MemoryPipeline(name="x4219_dsp6",parent="x4860") { implicit CU => 
      val b5007 = CU.temp
      val b4966 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4694 = CounterChain.copy("x4704_0", "x4694")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4699 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4699_x4704_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4699.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5007))
      RAStage(operands=List(b5007, CU.ctr(x4694(0))), op=FixAdd, results=List(x4219_x4699.readAddr))
    }
    val x4219_dsp7 = MemoryPipeline(name="x4219_dsp7",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5005 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4682 = CounterChain.copy("x4692_0", "x4682")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4687 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4687_x4692_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4687.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5005))
      RAStage(operands=List(b5005, CU.ctr(x4682(0))), op=FixAdd, results=List(x4219_x4687.readAddr))
    }
    val x4219_dsp8 = MemoryPipeline(name="x4219_dsp8",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b5003 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4670 = CounterChain.copy("x4680_0", "x4670")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4675 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4675_x4680_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4675.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5003))
      RAStage(operands=List(b5003, CU.ctr(x4670(0))), op=FixAdd, results=List(x4219_x4675.readAddr))
    }
    val x4219_dsp9 = MemoryPipeline(name="x4219_dsp9",parent="x4860") { implicit CU => 
      val b5001 = CU.temp
      val b4966 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4658 = CounterChain.copy("x4668_0", "x4658")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4663 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4663_x4668_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4663.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b5001))
      RAStage(operands=List(b5001, CU.ctr(x4658(0))), op=FixAdd, results=List(x4219_x4663.readAddr))
    }
    val x4219_dsp10 = MemoryPipeline(name="x4219_dsp10",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4999 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4517 = CounterChain.copy("x4534_0", "x4517")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4522 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4522_x4534_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4522.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4999))
      RAStage(operands=List(b4999, CU.ctr(x4517(0))), op=FixAdd, results=List(x4219_x4522.readAddr))
    }
    val x4219_dsp11 = MemoryPipeline(name="x4219_dsp11",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4997 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4498 = CounterChain.copy("x4515_0", "x4498")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4503 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4503_x4515_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4503.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4997))
      RAStage(operands=List(b4997, CU.ctr(x4498(0))), op=FixAdd, results=List(x4219_x4503.readAddr))
    }
    val x4219_dsp12 = MemoryPipeline(name="x4219_dsp12",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4995 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4479 = CounterChain.copy("x4496_0", "x4479")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4484 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4484_x4496_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4484.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4995))
      RAStage(operands=List(b4995, CU.ctr(x4479(0))), op=FixAdd, results=List(x4219_x4484.readAddr))
    }
    val x4219_dsp13 = MemoryPipeline(name="x4219_dsp13",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4993 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4460 = CounterChain.copy("x4477_0", "x4460")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4465 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4465_x4477_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4465.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4993))
      RAStage(operands=List(b4993, CU.ctr(x4460(0))), op=FixAdd, results=List(x4219_x4465.readAddr))
    }
    val x4219_dsp14 = MemoryPipeline(name="x4219_dsp14",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4991 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4441 = CounterChain.copy("x4458_0", "x4441")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4446 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4446_x4458_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4446.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4991))
      RAStage(operands=List(b4991, CU.ctr(x4441(0))), op=FixAdd, results=List(x4219_x4446.readAddr))
    }
    val x4219_dsp15 = MemoryPipeline(name="x4219_dsp15",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4989 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4422 = CounterChain.copy("x4439_0", "x4422")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4427 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4427_x4439_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4427.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4989))
      RAStage(operands=List(b4989, CU.ctr(x4422(0))), op=FixAdd, results=List(x4219_x4427.readAddr))
    }
    val x4219_dsp16 = MemoryPipeline(name="x4219_dsp16",parent="x4860") { implicit CU => 
      val b4987 = CU.temp
      val b4966 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4403 = CounterChain.copy("x4420_0", "x4403")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4408 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4408_x4420_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4408.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4987))
      RAStage(operands=List(b4987, CU.ctr(x4403(0))), op=FixAdd, results=List(x4219_x4408.readAddr))
    }
    val x4219_dsp17 = MemoryPipeline(name="x4219_dsp17",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4985 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4384 = CounterChain.copy("x4401_0", "x4384")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4389 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4389_x4401_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4389.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4985))
      RAStage(operands=List(b4985, CU.ctr(x4384(0))), op=FixAdd, results=List(x4219_x4389.readAddr))
    }
    val x4219_dsp18 = MemoryPipeline(name="x4219_dsp18",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4983 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4365 = CounterChain.copy("x4382_0", "x4365")
      val x4219_x4370 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4370_x4382_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4370.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4983))
      RAStage(operands=List(b4983, CU.ctr(x4365(0))), op=FixAdd, results=List(x4219_x4370.readAddr))
    }
    val x4219_dsp19 = MemoryPipeline(name="x4219_dsp19",parent="x4860") { implicit CU => 
      val b4966 = CU.temp
      val b4981 = CU.temp
      val x4247_x4247 =  VectorFIFO(size=1).wtPort(x4224_x4238_data_v)
      val x4346 = CounterChain.copy("x4363_0", "x4346")
      val x4240 = CounterChain.copy("x4248", "x4240")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4222 = CounterChain.copy("x4249", "x4222")
      val x4219_x4351 =  SRAM(size=1920,banking = Strided(1)).wtPort(x4247_x4247.readPort).rdPort(x4219_x4351_x4363_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4222(0)), Const(192)), op=FixMul, results=List(b4966))
      WAStage(operands=List(b4966, CU.ctr(x4240(0))), op=FixAdd, results=List(x4219_x4351.writeAddr))
      RAStage(operands=List(CU.ctr(x4314(0)), Const(192)), op=FixMul, results=List(b4981))
      RAStage(operands=List(b4981, CU.ctr(x4346(0))), op=FixAdd, results=List(x4219_x4351.readAddr))
    }
    val x4220_dsp0 = MemoryPipeline(name="x4220_dsp0",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4647 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4647_x4655_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4647.writeAddr))
    }
    val x4220_dsp1 = MemoryPipeline(name="x4220_dsp1",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4635 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4635_x4643_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4635.writeAddr))
    }
    val x4220_dsp2 = MemoryPipeline(name="x4220_dsp2",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4623 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4623_x4631_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4623.writeAddr))
    }
    val x4220_dsp3 = MemoryPipeline(name="x4220_dsp3",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4611 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4611_x4619_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4611.writeAddr))
    }
    val x4220_dsp4 = MemoryPipeline(name="x4220_dsp4",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4599 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4599_x4607_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4599.writeAddr))
    }
    val x4220_dsp5 = MemoryPipeline(name="x4220_dsp5",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4587 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4587_x4595_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4587.writeAddr))
    }
    val x4220_dsp6 = MemoryPipeline(name="x4220_dsp6",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4575 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4575_x4583_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4575.writeAddr))
    }
    val x4220_dsp7 = MemoryPipeline(name="x4220_dsp7",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4563 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4563_x4571_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4563.writeAddr))
    }
    val x4220_dsp8 = MemoryPipeline(name="x4220_dsp8",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4551 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4551_x4559_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4551.writeAddr))
    }
    val x4220_dsp9 = MemoryPipeline(name="x4220_dsp9",parent="x4860") { implicit CU => 
      val x4308_x4308 =  VectorFIFO(size=1).wtPort(x4252_x4279_data_v)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val x4295 = CounterChain.copy("x4309", "x4295")
      val x4314 = CounterChain.copy("x4859", "x4314")
      val x4220_x4539 =  SRAM(size=10,banking = Strided(1)).wtPort(x4308_x4308.readPort).rdPort(x4220_x4539_x4547_v).rdAddr(x4314(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4295(0)), CU.load(x4280_x4296)), op=FixSub, results=List(x4220_x4539.writeAddr))
    }
    val x4249 = StreamController(name="x4249",parent=x4860) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(10), step=Const(1), par=1) // Counter
      val x4222 = CounterChain(name = "x4222", ctr4).iter(10)
    }
    val x4237_0 = Pipeline(name="x4237_0",parent=x4249) { implicit CU => 
      val x4227 = CU.temp
      val x4228 = CU.temp
      val x4226 = CU.temp
      val x4225 =  ScalarBuffer().wtPort(x4225_argin)
      val x4218 = CounterChain.copy("x4860", "x4218")
      val x4222 = CounterChain.copy("x4249", "x4222")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4218(0)), CU.ctr(x4222(0))), op=FixAdd, results=List(x4226))
      Stage(operands=List(x4226, Const(192)), op=FixMul, results=List(x4227))
      Stage(operands=List(x4227, Const(4)), op=FixMul, results=List(x4228))
      Stage(operands=List(x4228, CU.load(x4225)), op=FixAdd, results=List(CU.scalarOut(x4223_b4962_x4236_b4964_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x4223_b4963_x4236_b4965_s)))
    }
    val x4238 = MemoryController(name="x4238",parent=x4249,offchip=x4200_oc, mctpe=TileLoad) { implicit CU => 
      val x4223_b4963_x4238 =  ScalarFIFO(name="size",size=1).wtPort(x4223_b4963_x4236_b4965_s)
      val x4223_b4962_x4238 =  ScalarFIFO(name="offset",size=1).wtPort(x4223_b4962_x4236_b4964_s)
      CU.newVout("data", x4224_x4238_data_v)
    }
    val x4248 = Pipeline(name="x4248",parent=x4249) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4240 = CounterChain(name = "x4240", ctr5).iter(12)
      var stage: List[Stage] = Nil
    }
    val x4311 = StreamController(name="x4311",parent=x4860) { implicit CU => 
    }
    val x4278 = StreamController(name="x4278",parent=x4311) { implicit CU => 
    }
    val x4278_0 = Pipeline(name="x4278_0",parent=x4278) { implicit CU => 
      val x4260 = CU.temp
      val x4254 = CU.temp
      val x4218 = CounterChain.copy("x4860", "x4218")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x4218(0)), Const(4)), op=FixMul, results=List(x4254, CU.scalarOut(bus_17531_s)))
      Stage(operands=List(x4254, Const(64)), op=FixMod, results=List(x4260, CU.scalarOut(bus_17533_s)))
      Stage(operands=List(x4260, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_17534_s), CU.scalarOut(x4251_b4971_x4277_b4979_s)))
      Stage(operands=List(x4254, Const(40)), op=FixAdd, results=List(CU.scalarOut(bus_17536_s)))
    }
    val x4278_1 = Pipeline(name="x4278_1",parent=x4278) { implicit CU => 
      val x4259 = CU.temp
      val x4256 = CU.temp
      val x4257 = CU.temp
      val x4258 = CU.temp
      val x4255 =  ScalarFIFO(size=1).wtPort(bus_17536_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4255), Const(4)), op=FixDiv, results=List(CU.scalarOut(x4251_b4972_x4277_b4980_s)))
      Stage(operands=List(CU.load(x4255), Const(64)), op=FixMod, results=List(x4256))
      Stage(operands=List(x4256, Const(0)), op=FixEql, results=List(x4257))
      Stage(operands=List(Const(64), x4256), op=FixSub, results=List(x4258))
      Stage(operands=List(x4257, Const(0), x4258), op=Mux, results=List(x4259, CU.scalarOut(bus_17542_s)))
      Stage(operands=List(x4259, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_17543_s)))
    }
    val x4278_2 = Pipeline(name="x4278_2",parent=x4278) { implicit CU => 
      val x4261 = CU.temp
      val x4263 = CU.temp
      val x4274 = CU.temp
      val x4260 =  ScalarFIFO(size=1).wtPort(bus_17533_s)
      val x4271 =  ScalarFIFO(size=1).wtPort(bus_17534_s)
      val x4254 =  ScalarFIFO(size=1).wtPort(bus_17531_s)
      val x4259 =  ScalarFIFO(size=1).wtPort(bus_17542_s)
      val x4273 =  ScalarFIFO(size=1).wtPort(bus_17543_s)
      val x4253 =  ScalarBuffer().wtPort(x4253_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(10), CU.load(x4271)), op=FixAdd, results=List(x4274))
      Stage(operands=List(x4274, CU.load(x4273)), op=FixAdd, results=List(CU.scalarOut(x4251_b4970_x4277_b4978_s)))
      Stage(operands=List(Const(40), CU.load(x4260)), op=FixAdd, results=List(x4261))
      Stage(operands=List(x4261, CU.load(x4259)), op=FixAdd, results=List(CU.scalarOut(x4250_b4969_x4270_b4977_s)))
      Stage(operands=List(CU.load(x4254), CU.load(x4260)), op=FixSub, results=List(x4263))
      Stage(operands=List(x4263, CU.load(x4253)), op=FixAdd, results=List(CU.scalarOut(x4250_b4968_x4270_b4976_s)))
    }
    val x4279 = MemoryController(name="x4279",parent=x4311,offchip=x4202_oc, mctpe=TileLoad) { implicit CU => 
      val x4250_b4969_x4279 =  ScalarFIFO(name="size",size=1).wtPort(x4250_b4969_x4270_b4977_s)
      val x4250_b4968_x4279 =  ScalarFIFO(name="offset",size=1).wtPort(x4250_b4968_x4270_b4976_s)
      CU.newVout("data", x4252_x4279_data_v)
    }
    val x4310 = Sequential(name="x4310",parent=x4311) { implicit CU => 
    }
    val x4292_0 = Pipeline(name="x4292_0",parent=x4310) { implicit CU => 
      val x4251_b4972_x4285_b4975 =  ScalarFIFO(size=16).wtPort(x4251_b4972_x4277_b4980_s)
      val x4251_b4971_x4285_b4974 =  ScalarFIFO(size=16).wtPort(x4251_b4971_x4277_b4979_s)
      val x4251_b4970_x4285_b4973 =  ScalarFIFO(size=16).wtPort(x4251_b4970_x4277_b4978_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4251_b4971_x4285_b4974)), op=Bypass, results=List(CU.scalarOut(x4280_x4287_s)))
      Stage(operands=List(CU.load(x4251_b4972_x4285_b4975)), op=Bypass, results=List(CU.scalarOut(x4281_x4289_s)))
      Stage(operands=List(CU.load(x4251_b4970_x4285_b4973)), op=Bypass, results=List(CU.scalarOut(x4282_x4291_s)))
    }
    val x4309 = Pipeline(name="x4309",parent=x4310) { implicit CU => 
      val x4282_x4293 =  ScalarBuffer().wtPort(x4282_x4291_s)
      val x4281_x4297 =  ScalarBuffer().wtPort(x4281_x4289_s)
      val x4280_x4296 =  ScalarBuffer().wtPort(x4280_x4287_s)
      val ctr6 = Counter(min=Const(0), max=x4282_x4293.load, step=Const(1), par=16) // Counter
      val x4295 = CounterChain(name = "x4295", ctr6).iter(1)
      var stage: List[Stage] = Nil
    }
    val x4859 = MetaPipeline(name="x4859",parent=x4860) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(10), step=Const(1), par=10) // Counter
      val x4314 = CounterChain(name = "x4314", ctr7).iter(1)
    }
    val x4325_dsp0 = MemoryPipeline(name="x4325_dsp0",parent="x4859") { implicit CU => 
      val x4667_x4667 =  VectorFIFO(size=1).wtPort(x4325_x4667_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4658 = CounterChain.copy("x4668_0", "x4658")
      val x4325_x4783 =  SRAM(size=192,banking = Strided(1)).wtPort(x4667_x4667.readPort).rdPort(x4325_x4783_x4857_v).rdAddr(x4779(0)).wtAddr(x4658(0))
      var stage: List[Stage] = Nil
    }
    val x4326_dsp0 = MemoryPipeline(name="x4326_dsp0",parent="x4859") { implicit CU => 
      val x4679_x4679 =  VectorFIFO(size=1).wtPort(x4326_x4679_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4670 = CounterChain.copy("x4680_0", "x4670")
      val x4326_x4784 =  SRAM(size=192,banking = Strided(1)).wtPort(x4679_x4679.readPort).rdPort(x4326_x4784_x4857_v).rdAddr(x4779(0)).wtAddr(x4670(0))
      var stage: List[Stage] = Nil
    }
    val x4327_dsp0 = MemoryPipeline(name="x4327_dsp0",parent="x4859") { implicit CU => 
      val x4691_x4691 =  VectorFIFO(size=1).wtPort(x4327_x4691_v)
      val x4682 = CounterChain.copy("x4692_0", "x4682")
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4327_x4785 =  SRAM(size=192,banking = Strided(1)).wtPort(x4691_x4691.readPort).rdPort(x4327_x4785_x4857_v).rdAddr(x4779(0)).wtAddr(x4682(0))
      var stage: List[Stage] = Nil
    }
    val x4328_dsp0 = MemoryPipeline(name="x4328_dsp0",parent="x4859") { implicit CU => 
      val x4703_x4703 =  VectorFIFO(size=1).wtPort(x4328_x4703_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4694 = CounterChain.copy("x4704_0", "x4694")
      val x4328_x4786 =  SRAM(size=192,banking = Strided(1)).wtPort(x4703_x4703.readPort).rdPort(x4328_x4786_x4857_v).rdAddr(x4779(0)).wtAddr(x4694(0))
      var stage: List[Stage] = Nil
    }
    val x4329_dsp0 = MemoryPipeline(name="x4329_dsp0",parent="x4859") { implicit CU => 
      val x4715_x4715 =  VectorFIFO(size=1).wtPort(x4329_x4715_v)
      val x4706 = CounterChain.copy("x4716_0", "x4706")
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4329_x4787 =  SRAM(size=192,banking = Strided(1)).wtPort(x4715_x4715.readPort).rdPort(x4329_x4787_x4857_v).rdAddr(x4779(0)).wtAddr(x4706(0))
      var stage: List[Stage] = Nil
    }
    val x4330_dsp0 = MemoryPipeline(name="x4330_dsp0",parent="x4859") { implicit CU => 
      val x4727_x4727 =  VectorFIFO(size=1).wtPort(x4330_x4727_v)
      val x4718 = CounterChain.copy("x4728_0", "x4718")
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4330_x4788 =  SRAM(size=192,banking = Strided(1)).wtPort(x4727_x4727.readPort).rdPort(x4330_x4788_x4857_v).rdAddr(x4779(0)).wtAddr(x4718(0))
      var stage: List[Stage] = Nil
    }
    val x4331_dsp0 = MemoryPipeline(name="x4331_dsp0",parent="x4859") { implicit CU => 
      val x4739_x4739 =  VectorFIFO(size=1).wtPort(x4331_x4739_v)
      val x4730 = CounterChain.copy("x4740_0", "x4730")
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4331_x4789 =  SRAM(size=192,banking = Strided(1)).wtPort(x4739_x4739.readPort).rdPort(x4331_x4789_x4857_v).rdAddr(x4779(0)).wtAddr(x4730(0))
      var stage: List[Stage] = Nil
    }
    val x4332_dsp0 = MemoryPipeline(name="x4332_dsp0",parent="x4859") { implicit CU => 
      val x4751_x4751 =  VectorFIFO(size=1).wtPort(x4332_x4751_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4742 = CounterChain.copy("x4752_0", "x4742")
      val x4332_x4790 =  SRAM(size=192,banking = Strided(1)).wtPort(x4751_x4751.readPort).rdPort(x4332_x4790_x4857_v).rdAddr(x4779(0)).wtAddr(x4742(0))
      var stage: List[Stage] = Nil
    }
    val x4333_dsp0 = MemoryPipeline(name="x4333_dsp0",parent="x4859") { implicit CU => 
      val x4763_x4763 =  VectorFIFO(size=1).wtPort(x4333_x4763_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4754 = CounterChain.copy("x4764_0", "x4754")
      val x4333_x4791 =  SRAM(size=192,banking = Strided(1)).wtPort(x4763_x4763.readPort).rdPort(x4333_x4791_x4857_v).rdAddr(x4779(0)).wtAddr(x4754(0))
      var stage: List[Stage] = Nil
    }
    val x4334_dsp0 = MemoryPipeline(name="x4334_dsp0",parent="x4859") { implicit CU => 
      val x4775_x4775 =  VectorFIFO(size=1).wtPort(x4334_x4775_v)
      val x4779 = CounterChain.copy("x4857", "x4779")
      val x4766 = CounterChain.copy("x4776_0", "x4766")
      val x4334_x4792 =  SRAM(size=192,banking = Strided(1)).wtPort(x4775_x4775.readPort).rdPort(x4334_x4792_x4857_v).rdAddr(x4779(0)).wtAddr(x4766(0))
      var stage: List[Stage] = Nil
    }
    val x4363_0 = Pipeline(name="x4363_0",parent=x4859) { implicit CU => 
      val x4352_x4352 =  VectorFIFO(size=1).wtPort(x4209_x4352_x4363_v)
      val x4351_x4351 =  VectorFIFO(size=1).wtPort(x4219_x4351_x4363_v)
      val ctr8 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4346 = CounterChain(name = "x4346", ctr8).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4351_x4351), CU.load(x4352_x4352)), op=FltMul, results=List(CU.reduce))
      val (_, rr17581) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17581), op=Bypass, results=List(CU.scalarOut(x4335_x4361_s)))
    }
    val x4382_0 = Pipeline(name="x4382_0",parent=x4859) { implicit CU => 
      val x4371_x4371 =  VectorFIFO(size=1).wtPort(x4209_x4371_x4382_v)
      val x4370_x4370 =  VectorFIFO(size=1).wtPort(x4219_x4370_x4382_v)
      val ctr9 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4365 = CounterChain(name = "x4365", ctr9).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4370_x4370), CU.load(x4371_x4371)), op=FltMul, results=List(CU.reduce))
      val (_, rr17592) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17592), op=Bypass, results=List(CU.scalarOut(x4336_x4380_s)))
    }
    val x4401_0 = Pipeline(name="x4401_0",parent=x4859) { implicit CU => 
      val x4390_x4390 =  VectorFIFO(size=1).wtPort(x4209_x4390_x4401_v)
      val x4389_x4389 =  VectorFIFO(size=1).wtPort(x4219_x4389_x4401_v)
      val ctr10 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4384 = CounterChain(name = "x4384", ctr10).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4389_x4389), CU.load(x4390_x4390)), op=FltMul, results=List(CU.reduce))
      val (_, rr17603) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17603), op=Bypass, results=List(CU.scalarOut(x4337_x4399_s)))
    }
    val x4420_0 = Pipeline(name="x4420_0",parent=x4859) { implicit CU => 
      val x4409_x4409 =  VectorFIFO(size=1).wtPort(x4209_x4409_x4420_v)
      val x4408_x4408 =  VectorFIFO(size=1).wtPort(x4219_x4408_x4420_v)
      val ctr11 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4403 = CounterChain(name = "x4403", ctr11).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4408_x4408), CU.load(x4409_x4409)), op=FltMul, results=List(CU.reduce))
      val (_, rr17614) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17614), op=Bypass, results=List(CU.scalarOut(x4338_x4418_s)))
    }
    val x4439_0 = Pipeline(name="x4439_0",parent=x4859) { implicit CU => 
      val x4428_x4428 =  VectorFIFO(size=1).wtPort(x4209_x4428_x4439_v)
      val x4427_x4427 =  VectorFIFO(size=1).wtPort(x4219_x4427_x4439_v)
      val ctr12 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4422 = CounterChain(name = "x4422", ctr12).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4427_x4427), CU.load(x4428_x4428)), op=FltMul, results=List(CU.reduce))
      val (_, rr17625) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17625), op=Bypass, results=List(CU.scalarOut(x4339_x4437_s)))
    }
    val x4458_0 = Pipeline(name="x4458_0",parent=x4859) { implicit CU => 
      val x4446_x4446 =  VectorFIFO(size=1).wtPort(x4219_x4446_x4458_v)
      val x4447_x4447 =  VectorFIFO(size=1).wtPort(x4209_x4447_x4458_v)
      val ctr13 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4441 = CounterChain(name = "x4441", ctr13).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4446_x4446), CU.load(x4447_x4447)), op=FltMul, results=List(CU.reduce))
      val (_, rr17636) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17636), op=Bypass, results=List(CU.scalarOut(x4340_x4456_s)))
    }
    val x4477_0 = Pipeline(name="x4477_0",parent=x4859) { implicit CU => 
      val x4466_x4466 =  VectorFIFO(size=1).wtPort(x4209_x4466_x4477_v)
      val x4465_x4465 =  VectorFIFO(size=1).wtPort(x4219_x4465_x4477_v)
      val ctr14 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4460 = CounterChain(name = "x4460", ctr14).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4465_x4465), CU.load(x4466_x4466)), op=FltMul, results=List(CU.reduce))
      val (_, rr17647) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17647), op=Bypass, results=List(CU.scalarOut(x4341_x4475_s)))
    }
    val x4496_0 = Pipeline(name="x4496_0",parent=x4859) { implicit CU => 
      val x4485_x4485 =  VectorFIFO(size=1).wtPort(x4209_x4485_x4496_v)
      val x4484_x4484 =  VectorFIFO(size=1).wtPort(x4219_x4484_x4496_v)
      val ctr15 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4479 = CounterChain(name = "x4479", ctr15).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4484_x4484), CU.load(x4485_x4485)), op=FltMul, results=List(CU.reduce))
      val (_, rr17658) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17658), op=Bypass, results=List(CU.scalarOut(x4342_x4494_s)))
    }
    val x4515_0 = Pipeline(name="x4515_0",parent=x4859) { implicit CU => 
      val x4503_x4503 =  VectorFIFO(size=1).wtPort(x4219_x4503_x4515_v)
      val x4504_x4504 =  VectorFIFO(size=1).wtPort(x4209_x4504_x4515_v)
      val ctr16 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4498 = CounterChain(name = "x4498", ctr16).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4503_x4503), CU.load(x4504_x4504)), op=FltMul, results=List(CU.reduce))
      val (_, rr17669) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17669), op=Bypass, results=List(CU.scalarOut(x4343_x4513_s)))
    }
    val x4534_0 = Pipeline(name="x4534_0",parent=x4859) { implicit CU => 
      val x4523_x4523 =  VectorFIFO(size=1).wtPort(x4209_x4523_x4534_v)
      val x4522_x4522 =  VectorFIFO(size=1).wtPort(x4219_x4522_x4534_v)
      val ctr17 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4517 = CounterChain(name = "x4517", ctr17).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4522_x4522), CU.load(x4523_x4523)), op=FltMul, results=List(CU.reduce))
      val (_, rr17680) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr17680), op=Bypass, results=List(CU.scalarOut(x4344_x4532_s)))
    }
    val x4547_0 = Pipeline(name="x4547_0",parent=x4859) { implicit CU => 
      val x4543 = CU.temp
      val x4542 = CU.temp
      val x4541 = CU.temp
      val x4544 = CU.temp
      val x4539_x4539 =  VectorFIFO(size=1).wtPort(x4220_x4539_x4547_v)
      val x4335_x4540 =  ScalarBuffer().wtPort(x4335_x4361_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4335_x4540)), op=FltNeg, results=List(x4541))
      Stage(operands=List(x4541), op=FltExp, results=List(x4542))
      Stage(operands=List(x4542, Const(1)), op=FltAdd, results=List(x4543))
      Stage(operands=List(Const(1), x4543), op=FltDiv, results=List(x4544))
      Stage(operands=List(CU.load(x4539_x4539), x4544), op=FltSub, results=List(CU.scalarOut(x4315_x4546_s)))
    }
    val x4559_0 = Pipeline(name="x4559_0",parent=x4859) { implicit CU => 
      val x4555 = CU.temp
      val x4554 = CU.temp
      val x4553 = CU.temp
      val x4556 = CU.temp
      val x4551_x4551 =  VectorFIFO(size=1).wtPort(x4220_x4551_x4559_v)
      val x4336_x4552 =  ScalarBuffer().wtPort(x4336_x4380_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4336_x4552)), op=FltNeg, results=List(x4553))
      Stage(operands=List(x4553), op=FltExp, results=List(x4554))
      Stage(operands=List(x4554, Const(1)), op=FltAdd, results=List(x4555))
      Stage(operands=List(Const(1), x4555), op=FltDiv, results=List(x4556))
      Stage(operands=List(CU.load(x4551_x4551), x4556), op=FltSub, results=List(CU.scalarOut(x4316_x4558_s)))
    }
    val x4571_0 = Pipeline(name="x4571_0",parent=x4859) { implicit CU => 
      val x4566 = CU.temp
      val x4567 = CU.temp
      val x4568 = CU.temp
      val x4565 = CU.temp
      val x4563_x4563 =  VectorFIFO(size=1).wtPort(x4220_x4563_x4571_v)
      val x4337_x4564 =  ScalarBuffer().wtPort(x4337_x4399_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4337_x4564)), op=FltNeg, results=List(x4565))
      Stage(operands=List(x4565), op=FltExp, results=List(x4566))
      Stage(operands=List(x4566, Const(1)), op=FltAdd, results=List(x4567))
      Stage(operands=List(Const(1), x4567), op=FltDiv, results=List(x4568))
      Stage(operands=List(CU.load(x4563_x4563), x4568), op=FltSub, results=List(CU.scalarOut(x4317_x4570_s)))
    }
    val x4583_0 = Pipeline(name="x4583_0",parent=x4859) { implicit CU => 
      val x4579 = CU.temp
      val x4577 = CU.temp
      val x4578 = CU.temp
      val x4580 = CU.temp
      val x4575_x4575 =  VectorFIFO(size=1).wtPort(x4220_x4575_x4583_v)
      val x4338_x4576 =  ScalarBuffer().wtPort(x4338_x4418_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4338_x4576)), op=FltNeg, results=List(x4577))
      Stage(operands=List(x4577), op=FltExp, results=List(x4578))
      Stage(operands=List(x4578, Const(1)), op=FltAdd, results=List(x4579))
      Stage(operands=List(Const(1), x4579), op=FltDiv, results=List(x4580))
      Stage(operands=List(CU.load(x4575_x4575), x4580), op=FltSub, results=List(CU.scalarOut(x4318_x4582_s)))
    }
    val x4595_0 = Pipeline(name="x4595_0",parent=x4859) { implicit CU => 
      val x4590 = CU.temp
      val x4589 = CU.temp
      val x4591 = CU.temp
      val x4592 = CU.temp
      val x4587_x4587 =  VectorFIFO(size=1).wtPort(x4220_x4587_x4595_v)
      val x4339_x4588 =  ScalarBuffer().wtPort(x4339_x4437_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4339_x4588)), op=FltNeg, results=List(x4589))
      Stage(operands=List(x4589), op=FltExp, results=List(x4590))
      Stage(operands=List(x4590, Const(1)), op=FltAdd, results=List(x4591))
      Stage(operands=List(Const(1), x4591), op=FltDiv, results=List(x4592))
      Stage(operands=List(CU.load(x4587_x4587), x4592), op=FltSub, results=List(CU.scalarOut(x4319_x4594_s)))
    }
    val x4607_0 = Pipeline(name="x4607_0",parent=x4859) { implicit CU => 
      val x4603 = CU.temp
      val x4602 = CU.temp
      val x4601 = CU.temp
      val x4604 = CU.temp
      val x4599_x4599 =  VectorFIFO(size=1).wtPort(x4220_x4599_x4607_v)
      val x4340_x4600 =  ScalarBuffer().wtPort(x4340_x4456_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4340_x4600)), op=FltNeg, results=List(x4601))
      Stage(operands=List(x4601), op=FltExp, results=List(x4602))
      Stage(operands=List(x4602, Const(1)), op=FltAdd, results=List(x4603))
      Stage(operands=List(Const(1), x4603), op=FltDiv, results=List(x4604))
      Stage(operands=List(CU.load(x4599_x4599), x4604), op=FltSub, results=List(CU.scalarOut(x4320_x4606_s)))
    }
    val x4619_0 = Pipeline(name="x4619_0",parent=x4859) { implicit CU => 
      val x4615 = CU.temp
      val x4614 = CU.temp
      val x4616 = CU.temp
      val x4613 = CU.temp
      val x4341_x4612 =  ScalarBuffer().wtPort(x4341_x4475_s)
      val x4611_x4611 =  VectorFIFO(size=1).wtPort(x4220_x4611_x4619_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4341_x4612)), op=FltNeg, results=List(x4613))
      Stage(operands=List(x4613), op=FltExp, results=List(x4614))
      Stage(operands=List(x4614, Const(1)), op=FltAdd, results=List(x4615))
      Stage(operands=List(Const(1), x4615), op=FltDiv, results=List(x4616))
      Stage(operands=List(CU.load(x4611_x4611), x4616), op=FltSub, results=List(CU.scalarOut(x4321_x4618_s)))
    }
    val x4631_0 = Pipeline(name="x4631_0",parent=x4859) { implicit CU => 
      val x4626 = CU.temp
      val x4628 = CU.temp
      val x4625 = CU.temp
      val x4627 = CU.temp
      val x4623_x4623 =  VectorFIFO(size=1).wtPort(x4220_x4623_x4631_v)
      val x4342_x4624 =  ScalarBuffer().wtPort(x4342_x4494_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4342_x4624)), op=FltNeg, results=List(x4625))
      Stage(operands=List(x4625), op=FltExp, results=List(x4626))
      Stage(operands=List(x4626, Const(1)), op=FltAdd, results=List(x4627))
      Stage(operands=List(Const(1), x4627), op=FltDiv, results=List(x4628))
      Stage(operands=List(CU.load(x4623_x4623), x4628), op=FltSub, results=List(CU.scalarOut(x4322_x4630_s)))
    }
    val x4643_0 = Pipeline(name="x4643_0",parent=x4859) { implicit CU => 
      val x4637 = CU.temp
      val x4639 = CU.temp
      val x4638 = CU.temp
      val x4640 = CU.temp
      val x4635_x4635 =  VectorFIFO(size=1).wtPort(x4220_x4635_x4643_v)
      val x4343_x4636 =  ScalarBuffer().wtPort(x4343_x4513_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4343_x4636)), op=FltNeg, results=List(x4637))
      Stage(operands=List(x4637), op=FltExp, results=List(x4638))
      Stage(operands=List(x4638, Const(1)), op=FltAdd, results=List(x4639))
      Stage(operands=List(Const(1), x4639), op=FltDiv, results=List(x4640))
      Stage(operands=List(CU.load(x4635_x4635), x4640), op=FltSub, results=List(CU.scalarOut(x4323_x4642_s)))
    }
    val x4655_0 = Pipeline(name="x4655_0",parent=x4859) { implicit CU => 
      val x4650 = CU.temp
      val x4652 = CU.temp
      val x4649 = CU.temp
      val x4651 = CU.temp
      val x4344_x4648 =  ScalarBuffer().wtPort(x4344_x4532_s)
      val x4647_x4647 =  VectorFIFO(size=1).wtPort(x4220_x4647_x4655_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4344_x4648)), op=FltNeg, results=List(x4649))
      Stage(operands=List(x4649), op=FltExp, results=List(x4650))
      Stage(operands=List(x4650, Const(1)), op=FltAdd, results=List(x4651))
      Stage(operands=List(Const(1), x4651), op=FltDiv, results=List(x4652))
      Stage(operands=List(CU.load(x4647_x4647), x4652), op=FltSub, results=List(CU.scalarOut(x4324_x4654_s)))
    }
    val x4668_0 = Pipeline(name="x4668_0",parent=x4859) { implicit CU => 
      val x4663_x4663 =  VectorFIFO(size=1).wtPort(x4219_x4663_x4668_v)
      val x4315_x4664 =  ScalarBuffer().wtPort(x4315_x4546_s)
      val ctr18 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4658 = CounterChain(name = "x4658", ctr18).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4663_x4663), CU.load(x4315_x4664)), op=FltSub, results=List(CU.vecOut(x4325_x4667_v)))
    }
    val x4680_0 = Pipeline(name="x4680_0",parent=x4859) { implicit CU => 
      val x4675_x4675 =  VectorFIFO(size=1).wtPort(x4219_x4675_x4680_v)
      val x4316_x4676 =  ScalarBuffer().wtPort(x4316_x4558_s)
      val ctr19 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4670 = CounterChain(name = "x4670", ctr19).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4675_x4675), CU.load(x4316_x4676)), op=FltSub, results=List(CU.vecOut(x4326_x4679_v)))
    }
    val x4692_0 = Pipeline(name="x4692_0",parent=x4859) { implicit CU => 
      val x4317_x4688 =  ScalarBuffer().wtPort(x4317_x4570_s)
      val x4687_x4687 =  VectorFIFO(size=1).wtPort(x4219_x4687_x4692_v)
      val ctr20 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4682 = CounterChain(name = "x4682", ctr20).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4687_x4687), CU.load(x4317_x4688)), op=FltSub, results=List(CU.vecOut(x4327_x4691_v)))
    }
    val x4704_0 = Pipeline(name="x4704_0",parent=x4859) { implicit CU => 
      val x4318_x4700 =  ScalarBuffer().wtPort(x4318_x4582_s)
      val x4699_x4699 =  VectorFIFO(size=1).wtPort(x4219_x4699_x4704_v)
      val ctr21 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4694 = CounterChain(name = "x4694", ctr21).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4699_x4699), CU.load(x4318_x4700)), op=FltSub, results=List(CU.vecOut(x4328_x4703_v)))
    }
    val x4716_0 = Pipeline(name="x4716_0",parent=x4859) { implicit CU => 
      val x4711_x4711 =  VectorFIFO(size=1).wtPort(x4219_x4711_x4716_v)
      val x4319_x4712 =  ScalarBuffer().wtPort(x4319_x4594_s)
      val ctr22 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4706 = CounterChain(name = "x4706", ctr22).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4711_x4711), CU.load(x4319_x4712)), op=FltSub, results=List(CU.vecOut(x4329_x4715_v)))
    }
    val x4728_0 = Pipeline(name="x4728_0",parent=x4859) { implicit CU => 
      val x4320_x4724 =  ScalarBuffer().wtPort(x4320_x4606_s)
      val x4723_x4723 =  VectorFIFO(size=1).wtPort(x4219_x4723_x4728_v)
      val ctr23 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4718 = CounterChain(name = "x4718", ctr23).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4723_x4723), CU.load(x4320_x4724)), op=FltSub, results=List(CU.vecOut(x4330_x4727_v)))
    }
    val x4740_0 = Pipeline(name="x4740_0",parent=x4859) { implicit CU => 
      val x4321_x4736 =  ScalarBuffer().wtPort(x4321_x4618_s)
      val x4735_x4735 =  VectorFIFO(size=1).wtPort(x4219_x4735_x4740_v)
      val ctr24 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4730 = CounterChain(name = "x4730", ctr24).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4735_x4735), CU.load(x4321_x4736)), op=FltSub, results=List(CU.vecOut(x4331_x4739_v)))
    }
    val x4752_0 = Pipeline(name="x4752_0",parent=x4859) { implicit CU => 
      val x4747_x4747 =  VectorFIFO(size=1).wtPort(x4219_x4747_x4752_v)
      val x4322_x4748 =  ScalarBuffer().wtPort(x4322_x4630_s)
      val ctr25 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4742 = CounterChain(name = "x4742", ctr25).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4747_x4747), CU.load(x4322_x4748)), op=FltSub, results=List(CU.vecOut(x4332_x4751_v)))
    }
    val x4764_0 = Pipeline(name="x4764_0",parent=x4859) { implicit CU => 
      val x4323_x4760 =  ScalarBuffer().wtPort(x4323_x4642_s)
      val x4759_x4759 =  VectorFIFO(size=1).wtPort(x4219_x4759_x4764_v)
      val ctr26 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4754 = CounterChain(name = "x4754", ctr26).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4759_x4759), CU.load(x4323_x4760)), op=FltSub, results=List(CU.vecOut(x4333_x4763_v)))
    }
    val x4776_0 = Pipeline(name="x4776_0",parent=x4859) { implicit CU => 
      val x4771_x4771 =  VectorFIFO(size=1).wtPort(x4219_x4771_x4776_v)
      val x4324_x4772 =  ScalarBuffer().wtPort(x4324_x4654_s)
      val ctr27 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4766 = CounterChain(name = "x4766", ctr27).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4771_x4771), CU.load(x4324_x4772)), op=FltSub, results=List(CU.vecOut(x4334_x4775_v)))
    }
    val x4857 = StreamController(name="x4857",parent=x4859) { implicit CU => 
      val ctr28 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4779 = CounterChain(name = "x4779", ctr28).iter(192)
    }
    val x4857_0 = Pipeline(name="x4857_0",parent=x4857) { implicit CU => 
      val x4784_x4784 =  VectorFIFO(size=1).wtPort(x4326_x4784_x4857_v)
      val x4783_x4783 =  VectorFIFO(size=1).wtPort(x4325_x4783_x4857_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4783_x4783), CU.load(x4784_x4784)), op=FltAdd, results=List(CU.scalarOut(bus_17837_s)))
    }
    val x4857_1 = Pipeline(name="x4857_1",parent=x4857) { implicit CU => 
      val x4818 = CU.temp
      val x4786_x4786 =  VectorFIFO(size=1).wtPort(x4328_x4786_x4857_v)
      val x4785_x4785 =  VectorFIFO(size=1).wtPort(x4327_x4785_x4857_v)
      val x4807 =  ScalarFIFO(size=1).wtPort(bus_17837_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4785_x4785), CU.load(x4786_x4786)), op=FltAdd, results=List(x4818))
      Stage(operands=List(CU.load(x4807), x4818), op=FltAdd, results=List(CU.scalarOut(bus_17846_s)))
    }
    val x4857_2 = Pipeline(name="x4857_2",parent=x4857) { implicit CU => 
      val x4787_x4787 =  VectorFIFO(size=1).wtPort(x4329_x4787_x4857_v)
      val x4788_x4788 =  VectorFIFO(size=1).wtPort(x4330_x4788_x4857_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4787_x4787), CU.load(x4788_x4788)), op=FltAdd, results=List(CU.scalarOut(bus_17862_s)))
    }
    val x4857_3 = Pipeline(name="x4857_3",parent=x4857) { implicit CU => 
      val x4845 = CU.temp
      val x4843 = CU.temp
      val x4820 =  ScalarFIFO(size=1).wtPort(bus_17846_s)
      val x4790_x4790 =  VectorFIFO(size=1).wtPort(x4332_x4790_x4857_v)
      val x4839 =  ScalarFIFO(size=1).wtPort(bus_17862_s)
      val x4789_x4789 =  VectorFIFO(size=1).wtPort(x4331_x4789_x4857_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4789_x4789), CU.load(x4790_x4790)), op=FltAdd, results=List(x4843))
      Stage(operands=List(CU.load(x4839), x4843), op=FltAdd, results=List(x4845))
      Stage(operands=List(CU.load(x4820), x4845), op=FltAdd, results=List(CU.scalarOut(bus_17865_s)))
    }
    val x4857_4 = Pipeline(name="x4857_4",parent=x4857) { implicit CU => 
      val x4851 = CU.temp
      val x4853 = CU.temp
      val x4793_x4793 =  VectorFIFO(size=1).wtPort(x4215_x4793_x4857_v)
      val x4792_x4792 =  VectorFIFO(size=1).wtPort(x4334_x4792_x4857_v)
      val x4847 =  ScalarFIFO(size=1).wtPort(bus_17865_s)
      val x4791_x4791 =  VectorFIFO(size=1).wtPort(x4333_x4791_x4857_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4791_x4791), CU.load(x4792_x4792)), op=FltAdd, results=List(x4851))
      Stage(operands=List(CU.load(x4847), x4851), op=FltAdd, results=List(x4853))
      Stage(operands=List(x4853, CU.load(x4793_x4793)), op=FltAdd, results=List(CU.vecOut(x4215_x4856_v)))
    }
    val x4871_0 = Pipeline(name="x4871_0",parent=x4874) { implicit CU => 
      val x4868 = CU.temp
      val x4865_x4865 =  VectorFIFO(size=1).wtPort(x4209_x4865_x4871_v)
      val x4864_x4864 =  VectorFIFO(size=1).wtPort(x4215_x4864_x4871_v)
      val ctr29 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4862 = CounterChain(name = "x4862", ctr29).iter(192)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4865_x4865), Const(1)), op=FltMul, results=List(x4868))
      Stage(operands=List(CU.load(x4864_x4864), x4868), op=FltAdd, results=List(CU.vecOut(x4209_x4870_v)))
    }
    val x4900 = StreamController(name="x4900",parent=x4901) { implicit CU => 
    }
    val x4893 = Sequential(name="x4893",parent=x4900) { implicit CU => 
    }
    val x4885_0 = Pipeline(name="x4885_0",parent=x4893) { implicit CU => 
      val x4879 =  ScalarBuffer().wtPort(x4879_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4879)), op=FixAdd, results=List(CU.scalarOut(x4876_b5021_x4884_b5023_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x4876_b5022_x4884_b5024_s)))
    }
    val x4892 = Pipeline(name="x4892",parent=x4893) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4887 = CounterChain(name = "x4887", ctr30).iter(12)
      var stage: List[Stage] = Nil
    }
    val x4894 = MemoryController(name="x4894",parent=x4900,offchip=x4203_oc, mctpe=TileStore) { implicit CU => 
      val x4877_x4894 =  VectorFIFO(name="data",size=1).wtPort(x4209_x4888_x4892_v)
      val x4876_b5022_x4894 =  ScalarFIFO(name="size",size=1).wtPort(x4876_b5022_x4884_b5024_s)
      val x4876_b5021_x4894 =  ScalarFIFO(name="offset",size=1).wtPort(x4876_b5021_x4884_b5023_s)
    }
    
  }
}
