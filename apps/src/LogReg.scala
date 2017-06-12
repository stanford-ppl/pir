import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object LogReg extends PIRApp {
  def main(top:Top) = {
    val x3879_x4267_x4272_v = Vector("x3879_x4267_x4272")
    val x3879_x4279_x4284_v = Vector("x3879_x4279_x4284")
    val x3860_oc = OffChip("x3860")
    val x3869_x4006_x4017_v = Vector("x3869_x4006_x4017")
    val bus_10228_s = Scalar("bus_10228")
    val x3940_x3947_s = Scalar("x3940_x3947")
    val x3879_x4339_x4344_v = Vector("x3879_x4339_x4344")
    val bus_10477_s = Scalar("bus_10477")
    val x3880_x4167_x4175_v = Vector("x3880_x4167_x4175")
    val x3997_x4129_s = Scalar("x3997_x4129")
    val x3994_x4072_s = Scalar("x3994_x4072")
    val x3984_x4352_x4410_v = Vector("x3984_x4352_x4410")
    val x3880_x4203_x4211_v = Vector("x3880_x4203_x4211")
    val x3913_argin = ArgIn("x3913")
    val x3941_x3949_s = Scalar("x3941_x3949")
    val x3978_x4198_s = Scalar("x3978_x4198")
    val x3942_x3951_s = Scalar("x3942_x3951")
    val x3879_x4005_x4017_v = Vector("x3879_x4005_x4017")
    val x3987_x4355_x4410_v = Vector("x3987_x4355_x4410")
    val x4432_argin = ArgIn("x4432")
    val x3869_x4120_x4131_v = Vector("x3869_x4120_x4131")
    val x3869_x4441_x4445_v = Vector("x3869_x4441_x4445")
    val bus_10226_s = Scalar("bus_10226")
    val x3910_b4522_x3930_b4530_s = Scalar("x3910_b4522_x3930_b4530")
    val x3879_x4100_x4112_v = Vector("x3879_x4100_x4112")
    val x3982_x4246_s = Scalar("x3982_x4246")
    val bus_10225_s = Scalar("bus_10225")
    val x3880_x4239_x4247_v = Vector("x3880_x4239_x4247")
    val x3911_b4523_x3937_b4531_s = Scalar("x3911_b4523_x3937_b4531")
    val x3990_x4358_x4410_v = Vector("x3990_x4358_x4410")
    val bus_10235_s = Scalar("bus_10235")
    val x3993_x4053_s = Scalar("x3993_x4053")
    val x3986_x4295_v = Vector("x3986_x4295")
    val x3883_b4515_x3896_b4517_s = Scalar("x3883_b4515_x3896_b4517")
    val x3995_x4091_s = Scalar("x3995_x4091")
    val x3989_x4357_x4410_v = Vector("x3989_x4357_x4410")
    val x3879_x4081_x4093_v = Vector("x3879_x4081_x4093")
    val x3883_b4516_x3896_b4518_s = Scalar("x3883_b4516_x3896_b4518")
    val x3885_argin = ArgIn("x3885")
    val x3989_x4331_v = Vector("x3989_x4331")
    val x3863_oc = OffChip("x3863")
    val x3879_x4138_x4150_v = Vector("x3879_x4138_x4150")
    val x3884_x3898_data_v = Vector("x3884_x3898_data")
    val x3992_x4034_s = Scalar("x3992_x4034")
    val x3856_argin = ArgIn("x3856")
    val x3980_x4222_s = Scalar("x3980_x4222")
    val x3990_x4343_v = Vector("x3990_x4343")
    val x3869_x4101_x4112_v = Vector("x3869_x4101_x4112")
    val x3855_argin = ArgIn("x3855")
    val x3869_x4063_x4074_v = Vector("x3869_x4063_x4074")
    val x4429_b4566_x4437_b4568_s = Scalar("x4429_b4566_x4437_b4568")
    val x3983_x4259_v = Vector("x3983_x4259")
    val bus_10223_s = Scalar("bus_10223")
    val x3880_x4179_x4187_v = Vector("x3880_x4179_x4187")
    val x3879_x4315_x4320_v = Vector("x3879_x4315_x4320")
    val x3985_x4353_x4410_v = Vector("x3985_x4353_x4410")
    val x3986_x4354_x4410_v = Vector("x3986_x4354_x4410")
    val x3879_x4119_x4131_v = Vector("x3879_x4119_x4131")
    val x3875_x4417_x4424_v = Vector("x3875_x4417_x4424")
    val x3911_b4525_x3937_b4533_s = Scalar("x3911_b4525_x3937_b4533")
    val x3880_x4191_x4199_v = Vector("x3880_x4191_x4199")
    val x3879_x4303_x4308_v = Vector("x3879_x4303_x4308")
    val x3869_x4418_x4424_v = Vector("x3869_x4418_x4424")
    val x3869_x4423_v = Vector("x3869_x4423")
    val x3880_x4155_x4163_v = Vector("x3880_x4155_x4163")
    val x3985_x4283_v = Vector("x3985_x4283")
    val x3983_x4351_x4410_v = Vector("x3983_x4351_x4410")
    val x3879_x4291_x4296_v = Vector("x3879_x4291_x4296")
    val x4429_b4567_x4437_b4569_s = Scalar("x4429_b4567_x4437_b4569")
    val x3977_x4186_s = Scalar("x3977_x4186")
    val x3879_x4062_x4074_v = Vector("x3879_x4062_x4074")
    val x3979_x4210_s = Scalar("x3979_x4210")
    val x3875_x4359_x4410_v = Vector("x3875_x4359_x4410")
    val x3976_x4174_s = Scalar("x3976_x4174")
    val bus_10493_s = Scalar("bus_10493")
    val x3984_x4271_v = Vector("x3984_x4271")
    val x3987_x4307_v = Vector("x3987_x4307")
    val x3869_x4025_x4036_v = Vector("x3869_x4025_x4036")
    val x3991_x4015_s = Scalar("x3991_x4015")
    val x3880_x4215_x4223_v = Vector("x3880_x4215_x4223")
    val x3910_b4521_x3930_b4529_s = Scalar("x3910_b4521_x3930_b4529")
    val x3988_x4319_v = Vector("x3988_x4319")
    val x3911_b4524_x3937_b4532_s = Scalar("x3911_b4524_x3937_b4532")
    val x3988_x4356_x4410_v = Vector("x3988_x4356_x4410")
    val bus_10234_s = Scalar("bus_10234")
    val x3862_oc = OffChip("x3862")
    val x3975_x4162_s = Scalar("x3975_x4162")
    val x3998_x4148_s = Scalar("x3998_x4148")
    val x3869_x4082_x4093_v = Vector("x3869_x4082_x4093")
    val x3879_x4255_x4260_v = Vector("x3879_x4255_x4260")
    val x3879_x4024_x4036_v = Vector("x3879_x4024_x4036")
    val bus_10468_s = Scalar("bus_10468")
    val x3879_x4327_x4332_v = Vector("x3879_x4327_x4332")
    val x3879_x4043_x4055_v = Vector("x3879_x4043_x4055")
    val x3869_x4139_x4150_v = Vector("x3869_x4139_x4150")
    val x3981_x4234_s = Scalar("x3981_x4234")
    val x3912_x3939_data_v = Vector("x3912_x3939_data")
    val x3869_x4044_x4055_v = Vector("x3869_x4044_x4055")
    val x3996_x4110_s = Scalar("x3996_x4110")
    val x3875_x4409_v = Vector("x3875_x4409")
    val x3880_x4227_x4235_v = Vector("x3880_x4227_x4235")
    val x4454 = Sequential(name="x4454",parent=top) { implicit CU => 
    }
    val x3869_dsp0 = MemoryPipeline(name="x3869_dsp0",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4440 = CounterChain.copy("x4445", "x4440")
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4441 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4441_x4445_v).rdAddr(x4440(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp1 = MemoryPipeline(name="x3869_dsp1",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4418 =  SRAM(size=192,banking = NoBanking()).wtPort(x4423_x4423.readPort).rdPort(x3869_x4418_x4424_v).rdAddr(x4415(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp2 = MemoryPipeline(name="x3869_dsp2",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4133 = CounterChain.copy("x4150_0", "x4133")
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4139 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4139_x4150_v).rdAddr(x4133(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp3 = MemoryPipeline(name="x3869_dsp3",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4114 = CounterChain.copy("x4131_0", "x4114")
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4120 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4120_x4131_v).rdAddr(x4114(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp4 = MemoryPipeline(name="x3869_dsp4",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x4095 = CounterChain.copy("x4112_0", "x4095")
      val x3869_x4101 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4101_x4112_v).rdAddr(x4095(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp5 = MemoryPipeline(name="x3869_dsp5",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4076 = CounterChain.copy("x4093_0", "x4076")
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4082 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4082_x4093_v).rdAddr(x4076(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp6 = MemoryPipeline(name="x3869_dsp6",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4057 = CounterChain.copy("x4074_0", "x4057")
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4063 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4063_x4074_v).rdAddr(x4057(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp7 = MemoryPipeline(name="x3869_dsp7",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x4038 = CounterChain.copy("x4055_0", "x4038")
      val x3869_x4044 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4044_x4055_v).rdAddr(x4038(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp8 = MemoryPipeline(name="x3869_dsp8",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x4019 = CounterChain.copy("x4036_0", "x4019")
      val x3869_x4025 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4025_x4036_v).rdAddr(x4019(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x3869_dsp9 = MemoryPipeline(name="x3869_dsp9",parent="x4427") { implicit CU => 
      val x4423_x4423 =  VectorFIFO(size=1).wtPort(x3869_x4423_v)
      val x4000 = CounterChain.copy("x4017_0", "x4000")
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x3869_x4006 =  SRAM(size=192,banking = Strided(1)).wtPort(x4423_x4423.readPort).rdPort(x3869_x4006_x4017_v).rdAddr(x4000(0)).wtAddr(x4415(0))
      var stage: List[Stage] = Nil
    }
    val x4428 = Sequential(name="x4428",parent=x4454) { implicit CU => 
      val x3855_x3870 =  ScalarBuffer().wtPort(x3855_argin)
      val ctr1 = Counter(min=Const(0), max=x3855_x3870.load, step=Const(1), par=1) // Counter
      val x3872 = CounterChain(name = "x3872", ctr1).iter(1)
    }
    val x4427 = Sequential(name="x4427",parent=x4428) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3874 = CounterChain(name = "x3874", ctr2).iter(1)
    }
    val x3875_dsp0 = MemoryPipeline(name="x3875_dsp0",parent="x4412") { implicit CU => 
      val x4409_x4409 =  VectorFIFO(size=1).wtPort(x3875_x4409_v)
      val x4415 = CounterChain.copy("x4424_0", "x4415")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3875_x4417 =  SRAM(size=192,banking = NoBanking()).wtPort(x4409_x4409.readPort).rdPort(x3875_x4417_x4424_v).rdAddr(x4415(0)).wtAddr(x4347(0))
      var stage: List[Stage] = Nil
    }
    val x3875_dsp1 = MemoryPipeline(name="x3875_dsp1",parent="x4412") { implicit CU => 
      val x4409_x4409 =  VectorFIFO(size=1).wtPort(x3875_x4409_v)
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3875_x4359 =  SRAM(size=192,banking = NoBanking()).wtPort(x4409_x4409.readPort).rdPort(x3875_x4359_x4410_v).rdAddr(x4347(0)).wtAddr(x4347(0))
      var stage: List[Stage] = Nil
    }
    val x4413 = MetaPipeline(name="x4413",parent=x4427) { implicit CU => 
      val x3856_x3876 =  ScalarBuffer().wtPort(x3856_argin)
      val ctr3 = Counter(min=Const(0), max=x3856_x3876.load, step=Const(10), par=1) // Counter
      val x3878 = CounterChain(name = "x3878", ctr3).iter(1)
    }
    val x3879_dsp0 = MemoryPipeline(name="x3879_dsp0",parent="x4413") { implicit CU => 
      val b4564 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4334 = CounterChain.copy("x4344_0", "x4334")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4339 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4339_x4344_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4339.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4564))
      RAStage(operands=List(b4564, CU.ctr(x4334(0))), op=FixAdd, results=List(x3879_x4339.readAddr))
    }
    val x3879_dsp1 = MemoryPipeline(name="x3879_dsp1",parent="x4413") { implicit CU => 
      val b4562 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4322 = CounterChain.copy("x4332_0", "x4322")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4327 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4327_x4332_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4327.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4562))
      RAStage(operands=List(b4562, CU.ctr(x4322(0))), op=FixAdd, results=List(x3879_x4327.readAddr))
    }
    val x3879_dsp2 = MemoryPipeline(name="x3879_dsp2",parent="x4413") { implicit CU => 
      val b4560 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4310 = CounterChain.copy("x4320_0", "x4310")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4315 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4315_x4320_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4315.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4560))
      RAStage(operands=List(b4560, CU.ctr(x4310(0))), op=FixAdd, results=List(x3879_x4315.readAddr))
    }
    val x3879_dsp3 = MemoryPipeline(name="x3879_dsp3",parent="x4413") { implicit CU => 
      val b4558 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4298 = CounterChain.copy("x4308_0", "x4298")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4303 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4303_x4308_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4303.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4558))
      RAStage(operands=List(b4558, CU.ctr(x4298(0))), op=FixAdd, results=List(x3879_x4303.readAddr))
    }
    val x3879_dsp4 = MemoryPipeline(name="x3879_dsp4",parent="x4413") { implicit CU => 
      val b4519 = CU.temp
      val b4556 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4286 = CounterChain.copy("x4296_0", "x4286")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4291 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4291_x4296_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4291.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4556))
      RAStage(operands=List(b4556, CU.ctr(x4286(0))), op=FixAdd, results=List(x3879_x4291.readAddr))
    }
    val x3879_dsp5 = MemoryPipeline(name="x3879_dsp5",parent="x4413") { implicit CU => 
      val b4554 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4274 = CounterChain.copy("x4284_0", "x4274")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4279 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4279_x4284_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4279.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4554))
      RAStage(operands=List(b4554, CU.ctr(x4274(0))), op=FixAdd, results=List(x3879_x4279.readAddr))
    }
    val x3879_dsp6 = MemoryPipeline(name="x3879_dsp6",parent="x4413") { implicit CU => 
      val b4552 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x4262 = CounterChain.copy("x4272_0", "x4262")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4267 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4267_x4272_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4267.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4552))
      RAStage(operands=List(b4552, CU.ctr(x4262(0))), op=FixAdd, results=List(x3879_x4267.readAddr))
    }
    val x3879_dsp7 = MemoryPipeline(name="x3879_dsp7",parent="x4413") { implicit CU => 
      val b4550 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4250 = CounterChain.copy("x4260_0", "x4250")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4255 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4255_x4260_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4255.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4550))
      RAStage(operands=List(b4550, CU.ctr(x4250(0))), op=FixAdd, results=List(x3879_x4255.readAddr))
    }
    val x3879_dsp8 = MemoryPipeline(name="x3879_dsp8",parent="x4413") { implicit CU => 
      val b4548 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4133 = CounterChain.copy("x4150_0", "x4133")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4138 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4138_x4150_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4138.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4548))
      RAStage(operands=List(b4548, CU.ctr(x4133(0))), op=FixAdd, results=List(x3879_x4138.readAddr))
    }
    val x3879_dsp9 = MemoryPipeline(name="x3879_dsp9",parent="x4413") { implicit CU => 
      val b4546 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4114 = CounterChain.copy("x4131_0", "x4114")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4119 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4119_x4131_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4119.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4546))
      RAStage(operands=List(b4546, CU.ctr(x4114(0))), op=FixAdd, results=List(x3879_x4119.readAddr))
    }
    val x3879_dsp10 = MemoryPipeline(name="x3879_dsp10",parent="x4413") { implicit CU => 
      val b4519 = CU.temp
      val b4544 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4095 = CounterChain.copy("x4112_0", "x4095")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4100 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4100_x4112_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4100.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4544))
      RAStage(operands=List(b4544, CU.ctr(x4095(0))), op=FixAdd, results=List(x3879_x4100.readAddr))
    }
    val x3879_dsp11 = MemoryPipeline(name="x3879_dsp11",parent="x4413") { implicit CU => 
      val b4542 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4076 = CounterChain.copy("x4093_0", "x4076")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4081 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4081_x4093_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4081.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4542))
      RAStage(operands=List(b4542, CU.ctr(x4076(0))), op=FixAdd, results=List(x3879_x4081.readAddr))
    }
    val x3879_dsp12 = MemoryPipeline(name="x3879_dsp12",parent="x4413") { implicit CU => 
      val b4519 = CU.temp
      val b4540 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x4057 = CounterChain.copy("x4074_0", "x4057")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4062 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4062_x4074_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4062.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4540))
      RAStage(operands=List(b4540, CU.ctr(x4057(0))), op=FixAdd, results=List(x3879_x4062.readAddr))
    }
    val x3879_dsp13 = MemoryPipeline(name="x3879_dsp13",parent="x4413") { implicit CU => 
      val b4538 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4038 = CounterChain.copy("x4055_0", "x4038")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4043 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4043_x4055_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4043.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4538))
      RAStage(operands=List(b4538, CU.ctr(x4038(0))), op=FixAdd, results=List(x3879_x4043.readAddr))
    }
    val x3879_dsp14 = MemoryPipeline(name="x3879_dsp14",parent="x4413") { implicit CU => 
      val b4536 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4019 = CounterChain.copy("x4036_0", "x4019")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4024 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4024_x4036_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4024.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4536))
      RAStage(operands=List(b4536, CU.ctr(x4019(0))), op=FixAdd, results=List(x3879_x4024.readAddr))
    }
    val x3879_dsp15 = MemoryPipeline(name="x3879_dsp15",parent="x4413") { implicit CU => 
      val b4534 = CU.temp
      val b4519 = CU.temp
      val x3907_x3907 =  VectorFIFO(size=1).wtPort(x3884_x3898_data_v)
      val x3882 = CounterChain.copy("x3909", "x3882")
      val x3900 = CounterChain.copy("x3908", "x3900")
      val x4000 = CounterChain.copy("x4017_0", "x4000")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3879_x4005 =  SRAM(size=1920,banking = Strided(1)).wtPort(x3907_x3907.readPort).rdPort(x3879_x4005_x4017_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3882(0)), Const(192)), op=FixMul, results=List(b4519))
      WAStage(operands=List(b4519, CU.ctr(x3900(0))), op=FixAdd, results=List(x3879_x4005.writeAddr))
      RAStage(operands=List(CU.ctr(x3974(0)), Const(192)), op=FixMul, results=List(b4534))
      RAStage(operands=List(b4534, CU.ctr(x4000(0))), op=FixAdd, results=List(x3879_x4005.readAddr))
    }
    val x3880_dsp0 = MemoryPipeline(name="x3880_dsp0",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4239 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4239_x4247_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4239.writeAddr))
    }
    val x3880_dsp1 = MemoryPipeline(name="x3880_dsp1",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4227 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4227_x4235_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4227.writeAddr))
    }
    val x3880_dsp2 = MemoryPipeline(name="x3880_dsp2",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4215 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4215_x4223_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4215.writeAddr))
    }
    val x3880_dsp3 = MemoryPipeline(name="x3880_dsp3",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4203 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4203_x4211_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4203.writeAddr))
    }
    val x3880_dsp4 = MemoryPipeline(name="x3880_dsp4",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4191 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4191_x4199_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4191.writeAddr))
    }
    val x3880_dsp5 = MemoryPipeline(name="x3880_dsp5",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4179 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4179_x4187_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4179.writeAddr))
    }
    val x3880_dsp6 = MemoryPipeline(name="x3880_dsp6",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4167 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4167_x4175_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4167.writeAddr))
    }
    val x3880_dsp7 = MemoryPipeline(name="x3880_dsp7",parent="x4413") { implicit CU => 
      val x3968_x3968 =  VectorFIFO(size=1).wtPort(x3912_x3939_data_v)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3955 = CounterChain.copy("x3969", "x3955")
      val x3974 = CounterChain.copy("x4412", "x3974")
      val x3880_x4155 =  SRAM(size=10,banking = Strided(1)).wtPort(x3968_x3968.readPort).rdPort(x3880_x4155_x4163_v).rdAddr(x3974(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3955(0)), CU.load(x3940_x3956)), op=FixSub, results=List(x3880_x4155.writeAddr))
    }
    val x3909 = StreamController(name="x3909",parent=x4413) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(10), step=Const(1), par=1) // Counter
      val x3882 = CounterChain(name = "x3882", ctr4).iter(10)
    }
    val x3897_0 = Pipeline(name="x3897_0",parent=x3909) { implicit CU => 
      val x3886 = CU.temp
      val x3887 = CU.temp
      val x3888 = CU.temp
      val x3885 =  ScalarBuffer().wtPort(x3885_argin)
      val x3878 = CounterChain.copy("x4413", "x3878")
      val x3882 = CounterChain.copy("x3909", "x3882")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3878(0)), CU.ctr(x3882(0))), op=FixAdd, results=List(x3886))
      Stage(operands=List(x3886, Const(192)), op=FixMul, results=List(x3887))
      Stage(operands=List(x3887, Const(4)), op=FixMul, results=List(x3888))
      Stage(operands=List(x3888, CU.load(x3885)), op=FixAdd, results=List(CU.scalarOut(x3883_b4515_x3896_b4517_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x3883_b4516_x3896_b4518_s)))
    }
    val x3898 = MemoryController(name="x3898",parent=x3909,offchip=x3860_oc, mctpe=TileLoad) { implicit CU => 
      val x3883_b4515_x3898 =  ScalarFIFO(name="offset",size=1).wtPort(x3883_b4515_x3896_b4517_s)
      val x3883_b4516_x3898 =  ScalarFIFO(name="size",size=1).wtPort(x3883_b4516_x3896_b4518_s)
      CU.newVout("data", x3884_x3898_data_v)
    }
    val x3908 = Pipeline(name="x3908",parent=x3909) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x3900 = CounterChain(name = "x3900", ctr5).iter(12)
      var stage: List[Stage] = Nil
    }
    val x3971 = StreamController(name="x3971",parent=x4413) { implicit CU => 
    }
    val x3938 = StreamController(name="x3938",parent=x3971) { implicit CU => 
    }
    val x3938_0 = Pipeline(name="x3938_0",parent=x3938) { implicit CU => 
      val x3914 = CU.temp
      val x3920 = CU.temp
      val x3878 = CounterChain.copy("x4413", "x3878")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3878(0)), Const(4)), op=FixMul, results=List(x3914, CU.scalarOut(bus_10223_s)))
      Stage(operands=List(x3914, Const(64)), op=FixMod, results=List(x3920, CU.scalarOut(bus_10225_s)))
      Stage(operands=List(x3920, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_10226_s), CU.scalarOut(x3911_b4524_x3937_b4532_s)))
      Stage(operands=List(x3914, Const(40)), op=FixAdd, results=List(CU.scalarOut(bus_10228_s)))
    }
    val x3938_1 = Pipeline(name="x3938_1",parent=x3938) { implicit CU => 
      val x3917 = CU.temp
      val x3919 = CU.temp
      val x3918 = CU.temp
      val x3916 = CU.temp
      val x3915 =  ScalarFIFO(size=1).wtPort(bus_10228_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3915), Const(4)), op=FixDiv, results=List(CU.scalarOut(x3911_b4525_x3937_b4533_s)))
      Stage(operands=List(CU.load(x3915), Const(64)), op=FixMod, results=List(x3916))
      Stage(operands=List(x3916, Const(0)), op=FixEql, results=List(x3917))
      Stage(operands=List(Const(64), x3916), op=FixSub, results=List(x3918))
      Stage(operands=List(x3917, Const(0), x3918), op=Mux, results=List(x3919, CU.scalarOut(bus_10234_s)))
      Stage(operands=List(x3919, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_10235_s)))
    }
    val x3938_2 = Pipeline(name="x3938_2",parent=x3938) { implicit CU => 
      val x3921 = CU.temp
      val x3923 = CU.temp
      val x3934 = CU.temp
      val x3933 =  ScalarFIFO(size=1).wtPort(bus_10235_s)
      val x3920 =  ScalarFIFO(size=1).wtPort(bus_10225_s)
      val x3913 =  ScalarBuffer().wtPort(x3913_argin)
      val x3931 =  ScalarFIFO(size=1).wtPort(bus_10226_s)
      val x3919 =  ScalarFIFO(size=1).wtPort(bus_10234_s)
      val x3914 =  ScalarFIFO(size=1).wtPort(bus_10223_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(10), CU.load(x3931)), op=FixAdd, results=List(x3934))
      Stage(operands=List(x3934, CU.load(x3933)), op=FixAdd, results=List(CU.scalarOut(x3911_b4523_x3937_b4531_s)))
      Stage(operands=List(Const(40), CU.load(x3920)), op=FixAdd, results=List(x3921))
      Stage(operands=List(x3921, CU.load(x3919)), op=FixAdd, results=List(CU.scalarOut(x3910_b4522_x3930_b4530_s)))
      Stage(operands=List(CU.load(x3914), CU.load(x3920)), op=FixSub, results=List(x3923))
      Stage(operands=List(x3923, CU.load(x3913)), op=FixAdd, results=List(CU.scalarOut(x3910_b4521_x3930_b4529_s)))
    }
    val x3939 = MemoryController(name="x3939",parent=x3971,offchip=x3862_oc, mctpe=TileLoad) { implicit CU => 
      val x3910_b4521_x3939 =  ScalarFIFO(name="offset",size=1).wtPort(x3910_b4521_x3930_b4529_s)
      val x3910_b4522_x3939 =  ScalarFIFO(name="size",size=1).wtPort(x3910_b4522_x3930_b4530_s)
      CU.newVout("data", x3912_x3939_data_v)
    }
    val x3970 = Sequential(name="x3970",parent=x3971) { implicit CU => 
    }
    val x3952_0 = Pipeline(name="x3952_0",parent=x3970) { implicit CU => 
      val x3911_b4524_x3945_b4527 =  ScalarFIFO(size=16).wtPort(x3911_b4524_x3937_b4532_s)
      val x3911_b4523_x3945_b4526 =  ScalarFIFO(size=16).wtPort(x3911_b4523_x3937_b4531_s)
      val x3911_b4525_x3945_b4528 =  ScalarFIFO(size=16).wtPort(x3911_b4525_x3937_b4533_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3911_b4524_x3945_b4527)), op=Bypass, results=List(CU.scalarOut(x3940_x3947_s)))
      Stage(operands=List(CU.load(x3911_b4525_x3945_b4528)), op=Bypass, results=List(CU.scalarOut(x3941_x3949_s)))
      Stage(operands=List(CU.load(x3911_b4523_x3945_b4526)), op=Bypass, results=List(CU.scalarOut(x3942_x3951_s)))
    }
    val x3969 = Pipeline(name="x3969",parent=x3970) { implicit CU => 
      val x3941_x3957 =  ScalarBuffer().wtPort(x3941_x3949_s)
      val x3940_x3956 =  ScalarBuffer().wtPort(x3940_x3947_s)
      val x3942_x3953 =  ScalarBuffer().wtPort(x3942_x3951_s)
      val ctr6 = Counter(min=Const(0), max=x3942_x3953.load, step=Const(1), par=16) // Counter
      val x3955 = CounterChain(name = "x3955", ctr6).iter(1)
      var stage: List[Stage] = Nil
    }
    val x4412 = MetaPipeline(name="x4412",parent=x4413) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(10), step=Const(1), par=8) // Counter
      val x3974 = CounterChain(name = "x3974", ctr7).iter(2)
    }
    val x3983_dsp0 = MemoryPipeline(name="x3983_dsp0",parent="x4412") { implicit CU => 
      val x4259_x4259 =  VectorFIFO(size=1).wtPort(x3983_x4259_v)
      val x4250 = CounterChain.copy("x4260_0", "x4250")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3983_x4351 =  SRAM(size=192,banking = Strided(1)).wtPort(x4259_x4259.readPort).rdPort(x3983_x4351_x4410_v).rdAddr(x4347(0)).wtAddr(x4250(0))
      var stage: List[Stage] = Nil
    }
    val x3984_dsp0 = MemoryPipeline(name="x3984_dsp0",parent="x4412") { implicit CU => 
      val x4271_x4271 =  VectorFIFO(size=1).wtPort(x3984_x4271_v)
      val x4262 = CounterChain.copy("x4272_0", "x4262")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3984_x4352 =  SRAM(size=192,banking = Strided(1)).wtPort(x4271_x4271.readPort).rdPort(x3984_x4352_x4410_v).rdAddr(x4347(0)).wtAddr(x4262(0))
      var stage: List[Stage] = Nil
    }
    val x3985_dsp0 = MemoryPipeline(name="x3985_dsp0",parent="x4412") { implicit CU => 
      val x4283_x4283 =  VectorFIFO(size=1).wtPort(x3985_x4283_v)
      val x4274 = CounterChain.copy("x4284_0", "x4274")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3985_x4353 =  SRAM(size=192,banking = Strided(1)).wtPort(x4283_x4283.readPort).rdPort(x3985_x4353_x4410_v).rdAddr(x4347(0)).wtAddr(x4274(0))
      var stage: List[Stage] = Nil
    }
    val x3986_dsp0 = MemoryPipeline(name="x3986_dsp0",parent="x4412") { implicit CU => 
      val x4295_x4295 =  VectorFIFO(size=1).wtPort(x3986_x4295_v)
      val x4286 = CounterChain.copy("x4296_0", "x4286")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3986_x4354 =  SRAM(size=192,banking = Strided(1)).wtPort(x4295_x4295.readPort).rdPort(x3986_x4354_x4410_v).rdAddr(x4347(0)).wtAddr(x4286(0))
      var stage: List[Stage] = Nil
    }
    val x3987_dsp0 = MemoryPipeline(name="x3987_dsp0",parent="x4412") { implicit CU => 
      val x4307_x4307 =  VectorFIFO(size=1).wtPort(x3987_x4307_v)
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x4298 = CounterChain.copy("x4308_0", "x4298")
      val x3987_x4355 =  SRAM(size=192,banking = Strided(1)).wtPort(x4307_x4307.readPort).rdPort(x3987_x4355_x4410_v).rdAddr(x4347(0)).wtAddr(x4298(0))
      var stage: List[Stage] = Nil
    }
    val x3988_dsp0 = MemoryPipeline(name="x3988_dsp0",parent="x4412") { implicit CU => 
      val x4319_x4319 =  VectorFIFO(size=1).wtPort(x3988_x4319_v)
      val x4310 = CounterChain.copy("x4320_0", "x4310")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3988_x4356 =  SRAM(size=192,banking = Strided(1)).wtPort(x4319_x4319.readPort).rdPort(x3988_x4356_x4410_v).rdAddr(x4347(0)).wtAddr(x4310(0))
      var stage: List[Stage] = Nil
    }
    val x3989_dsp0 = MemoryPipeline(name="x3989_dsp0",parent="x4412") { implicit CU => 
      val x4331_x4331 =  VectorFIFO(size=1).wtPort(x3989_x4331_v)
      val x4322 = CounterChain.copy("x4332_0", "x4322")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3989_x4357 =  SRAM(size=192,banking = Strided(1)).wtPort(x4331_x4331.readPort).rdPort(x3989_x4357_x4410_v).rdAddr(x4347(0)).wtAddr(x4322(0))
      var stage: List[Stage] = Nil
    }
    val x3990_dsp0 = MemoryPipeline(name="x3990_dsp0",parent="x4412") { implicit CU => 
      val x4343_x4343 =  VectorFIFO(size=1).wtPort(x3990_x4343_v)
      val x4334 = CounterChain.copy("x4344_0", "x4334")
      val x4347 = CounterChain.copy("x4410", "x4347")
      val x3990_x4358 =  SRAM(size=192,banking = Strided(1)).wtPort(x4343_x4343.readPort).rdPort(x3990_x4358_x4410_v).rdAddr(x4347(0)).wtAddr(x4334(0))
      var stage: List[Stage] = Nil
    }
    val x4017_0 = Pipeline(name="x4017_0",parent=x4412) { implicit CU => 
      val x4006_x4006 =  VectorFIFO(size=1).wtPort(x3869_x4006_x4017_v)
      val x4005_x4005 =  VectorFIFO(size=1).wtPort(x3879_x4005_x4017_v)
      val ctr8 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4000 = CounterChain(name = "x4000", ctr8).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4005_x4005), CU.load(x4006_x4006)), op=FltMul, results=List(CU.reduce))
      val (_, rr10269) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10269), op=Bypass, results=List(CU.scalarOut(x3991_x4015_s)))
    }
    val x4036_0 = Pipeline(name="x4036_0",parent=x4412) { implicit CU => 
      val x4024_x4024 =  VectorFIFO(size=1).wtPort(x3879_x4024_x4036_v)
      val x4025_x4025 =  VectorFIFO(size=1).wtPort(x3869_x4025_x4036_v)
      val ctr9 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4019 = CounterChain(name = "x4019", ctr9).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4024_x4024), CU.load(x4025_x4025)), op=FltMul, results=List(CU.reduce))
      val (_, rr10280) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10280), op=Bypass, results=List(CU.scalarOut(x3992_x4034_s)))
    }
    val x4055_0 = Pipeline(name="x4055_0",parent=x4412) { implicit CU => 
      val x4044_x4044 =  VectorFIFO(size=1).wtPort(x3869_x4044_x4055_v)
      val x4043_x4043 =  VectorFIFO(size=1).wtPort(x3879_x4043_x4055_v)
      val ctr10 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4038 = CounterChain(name = "x4038", ctr10).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4043_x4043), CU.load(x4044_x4044)), op=FltMul, results=List(CU.reduce))
      val (_, rr10291) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10291), op=Bypass, results=List(CU.scalarOut(x3993_x4053_s)))
    }
    val x4074_0 = Pipeline(name="x4074_0",parent=x4412) { implicit CU => 
      val x4063_x4063 =  VectorFIFO(size=1).wtPort(x3869_x4063_x4074_v)
      val x4062_x4062 =  VectorFIFO(size=1).wtPort(x3879_x4062_x4074_v)
      val ctr11 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4057 = CounterChain(name = "x4057", ctr11).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4062_x4062), CU.load(x4063_x4063)), op=FltMul, results=List(CU.reduce))
      val (_, rr10302) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10302), op=Bypass, results=List(CU.scalarOut(x3994_x4072_s)))
    }
    val x4093_0 = Pipeline(name="x4093_0",parent=x4412) { implicit CU => 
      val x4081_x4081 =  VectorFIFO(size=1).wtPort(x3879_x4081_x4093_v)
      val x4082_x4082 =  VectorFIFO(size=1).wtPort(x3869_x4082_x4093_v)
      val ctr12 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4076 = CounterChain(name = "x4076", ctr12).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4081_x4081), CU.load(x4082_x4082)), op=FltMul, results=List(CU.reduce))
      val (_, rr10313) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10313), op=Bypass, results=List(CU.scalarOut(x3995_x4091_s)))
    }
    val x4112_0 = Pipeline(name="x4112_0",parent=x4412) { implicit CU => 
      val x4101_x4101 =  VectorFIFO(size=1).wtPort(x3869_x4101_x4112_v)
      val x4100_x4100 =  VectorFIFO(size=1).wtPort(x3879_x4100_x4112_v)
      val ctr13 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4095 = CounterChain(name = "x4095", ctr13).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4100_x4100), CU.load(x4101_x4101)), op=FltMul, results=List(CU.reduce))
      val (_, rr10324) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10324), op=Bypass, results=List(CU.scalarOut(x3996_x4110_s)))
    }
    val x4131_0 = Pipeline(name="x4131_0",parent=x4412) { implicit CU => 
      val x4120_x4120 =  VectorFIFO(size=1).wtPort(x3869_x4120_x4131_v)
      val x4119_x4119 =  VectorFIFO(size=1).wtPort(x3879_x4119_x4131_v)
      val ctr14 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4114 = CounterChain(name = "x4114", ctr14).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4119_x4119), CU.load(x4120_x4120)), op=FltMul, results=List(CU.reduce))
      val (_, rr10335) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10335), op=Bypass, results=List(CU.scalarOut(x3997_x4129_s)))
    }
    val x4150_0 = Pipeline(name="x4150_0",parent=x4412) { implicit CU => 
      val x4138_x4138 =  VectorFIFO(size=1).wtPort(x3879_x4138_x4150_v)
      val x4139_x4139 =  VectorFIFO(size=1).wtPort(x3869_x4139_x4150_v)
      val ctr15 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4133 = CounterChain(name = "x4133", ctr15).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4138_x4138), CU.load(x4139_x4139)), op=FltMul, results=List(CU.reduce))
      val (_, rr10346) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr10346), op=Bypass, results=List(CU.scalarOut(x3998_x4148_s)))
    }
    val x4163_0 = Pipeline(name="x4163_0",parent=x4412) { implicit CU => 
      val x4157 = CU.temp
      val x4159 = CU.temp
      val x4158 = CU.temp
      val x4160 = CU.temp
      val x3991_x4156 =  ScalarBuffer().wtPort(x3991_x4015_s)
      val x4155_x4155 =  VectorFIFO(size=1).wtPort(x3880_x4155_x4163_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3991_x4156)), op=FltNeg, results=List(x4157))
      Stage(operands=List(x4157), op=FltExp, results=List(x4158))
      Stage(operands=List(x4158, Const(1)), op=FltAdd, results=List(x4159))
      Stage(operands=List(Const(1), x4159), op=FltDiv, results=List(x4160))
      Stage(operands=List(CU.load(x4155_x4155), x4160), op=FltSub, results=List(CU.scalarOut(x3975_x4162_s)))
    }
    val x4175_0 = Pipeline(name="x4175_0",parent=x4412) { implicit CU => 
      val x4170 = CU.temp
      val x4171 = CU.temp
      val x4169 = CU.temp
      val x4172 = CU.temp
      val x4167_x4167 =  VectorFIFO(size=1).wtPort(x3880_x4167_x4175_v)
      val x3992_x4168 =  ScalarBuffer().wtPort(x3992_x4034_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3992_x4168)), op=FltNeg, results=List(x4169))
      Stage(operands=List(x4169), op=FltExp, results=List(x4170))
      Stage(operands=List(x4170, Const(1)), op=FltAdd, results=List(x4171))
      Stage(operands=List(Const(1), x4171), op=FltDiv, results=List(x4172))
      Stage(operands=List(CU.load(x4167_x4167), x4172), op=FltSub, results=List(CU.scalarOut(x3976_x4174_s)))
    }
    val x4187_0 = Pipeline(name="x4187_0",parent=x4412) { implicit CU => 
      val x4182 = CU.temp
      val x4184 = CU.temp
      val x4181 = CU.temp
      val x4183 = CU.temp
      val x4179_x4179 =  VectorFIFO(size=1).wtPort(x3880_x4179_x4187_v)
      val x3993_x4180 =  ScalarBuffer().wtPort(x3993_x4053_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3993_x4180)), op=FltNeg, results=List(x4181))
      Stage(operands=List(x4181), op=FltExp, results=List(x4182))
      Stage(operands=List(x4182, Const(1)), op=FltAdd, results=List(x4183))
      Stage(operands=List(Const(1), x4183), op=FltDiv, results=List(x4184))
      Stage(operands=List(CU.load(x4179_x4179), x4184), op=FltSub, results=List(CU.scalarOut(x3977_x4186_s)))
    }
    val x4199_0 = Pipeline(name="x4199_0",parent=x4412) { implicit CU => 
      val x4194 = CU.temp
      val x4195 = CU.temp
      val x4196 = CU.temp
      val x4193 = CU.temp
      val x4191_x4191 =  VectorFIFO(size=1).wtPort(x3880_x4191_x4199_v)
      val x3994_x4192 =  ScalarBuffer().wtPort(x3994_x4072_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3994_x4192)), op=FltNeg, results=List(x4193))
      Stage(operands=List(x4193), op=FltExp, results=List(x4194))
      Stage(operands=List(x4194, Const(1)), op=FltAdd, results=List(x4195))
      Stage(operands=List(Const(1), x4195), op=FltDiv, results=List(x4196))
      Stage(operands=List(CU.load(x4191_x4191), x4196), op=FltSub, results=List(CU.scalarOut(x3978_x4198_s)))
    }
    val x4211_0 = Pipeline(name="x4211_0",parent=x4412) { implicit CU => 
      val x4205 = CU.temp
      val x4206 = CU.temp
      val x4208 = CU.temp
      val x4207 = CU.temp
      val x3995_x4204 =  ScalarBuffer().wtPort(x3995_x4091_s)
      val x4203_x4203 =  VectorFIFO(size=1).wtPort(x3880_x4203_x4211_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3995_x4204)), op=FltNeg, results=List(x4205))
      Stage(operands=List(x4205), op=FltExp, results=List(x4206))
      Stage(operands=List(x4206, Const(1)), op=FltAdd, results=List(x4207))
      Stage(operands=List(Const(1), x4207), op=FltDiv, results=List(x4208))
      Stage(operands=List(CU.load(x4203_x4203), x4208), op=FltSub, results=List(CU.scalarOut(x3979_x4210_s)))
    }
    val x4223_0 = Pipeline(name="x4223_0",parent=x4412) { implicit CU => 
      val x4218 = CU.temp
      val x4219 = CU.temp
      val x4220 = CU.temp
      val x4217 = CU.temp
      val x4215_x4215 =  VectorFIFO(size=1).wtPort(x3880_x4215_x4223_v)
      val x3996_x4216 =  ScalarBuffer().wtPort(x3996_x4110_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3996_x4216)), op=FltNeg, results=List(x4217))
      Stage(operands=List(x4217), op=FltExp, results=List(x4218))
      Stage(operands=List(x4218, Const(1)), op=FltAdd, results=List(x4219))
      Stage(operands=List(Const(1), x4219), op=FltDiv, results=List(x4220))
      Stage(operands=List(CU.load(x4215_x4215), x4220), op=FltSub, results=List(CU.scalarOut(x3980_x4222_s)))
    }
    val x4235_0 = Pipeline(name="x4235_0",parent=x4412) { implicit CU => 
      val x4232 = CU.temp
      val x4230 = CU.temp
      val x4229 = CU.temp
      val x4231 = CU.temp
      val x3997_x4228 =  ScalarBuffer().wtPort(x3997_x4129_s)
      val x4227_x4227 =  VectorFIFO(size=1).wtPort(x3880_x4227_x4235_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3997_x4228)), op=FltNeg, results=List(x4229))
      Stage(operands=List(x4229), op=FltExp, results=List(x4230))
      Stage(operands=List(x4230, Const(1)), op=FltAdd, results=List(x4231))
      Stage(operands=List(Const(1), x4231), op=FltDiv, results=List(x4232))
      Stage(operands=List(CU.load(x4227_x4227), x4232), op=FltSub, results=List(CU.scalarOut(x3981_x4234_s)))
    }
    val x4247_0 = Pipeline(name="x4247_0",parent=x4412) { implicit CU => 
      val x4242 = CU.temp
      val x4241 = CU.temp
      val x4244 = CU.temp
      val x4243 = CU.temp
      val x3998_x4240 =  ScalarBuffer().wtPort(x3998_x4148_s)
      val x4239_x4239 =  VectorFIFO(size=1).wtPort(x3880_x4239_x4247_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3998_x4240)), op=FltNeg, results=List(x4241))
      Stage(operands=List(x4241), op=FltExp, results=List(x4242))
      Stage(operands=List(x4242, Const(1)), op=FltAdd, results=List(x4243))
      Stage(operands=List(Const(1), x4243), op=FltDiv, results=List(x4244))
      Stage(operands=List(CU.load(x4239_x4239), x4244), op=FltSub, results=List(CU.scalarOut(x3982_x4246_s)))
    }
    val x4260_0 = Pipeline(name="x4260_0",parent=x4412) { implicit CU => 
      val x4255_x4255 =  VectorFIFO(size=1).wtPort(x3879_x4255_x4260_v)
      val x3975_x4256 =  ScalarBuffer().wtPort(x3975_x4162_s)
      val ctr16 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4250 = CounterChain(name = "x4250", ctr16).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4255_x4255), CU.load(x3975_x4256)), op=FltSub, results=List(CU.vecOut(x3983_x4259_v)))
    }
    val x4272_0 = Pipeline(name="x4272_0",parent=x4412) { implicit CU => 
      val x4267_x4267 =  VectorFIFO(size=1).wtPort(x3879_x4267_x4272_v)
      val x3976_x4268 =  ScalarBuffer().wtPort(x3976_x4174_s)
      val ctr17 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4262 = CounterChain(name = "x4262", ctr17).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4267_x4267), CU.load(x3976_x4268)), op=FltSub, results=List(CU.vecOut(x3984_x4271_v)))
    }
    val x4284_0 = Pipeline(name="x4284_0",parent=x4412) { implicit CU => 
      val x3977_x4280 =  ScalarBuffer().wtPort(x3977_x4186_s)
      val x4279_x4279 =  VectorFIFO(size=1).wtPort(x3879_x4279_x4284_v)
      val ctr18 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4274 = CounterChain(name = "x4274", ctr18).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4279_x4279), CU.load(x3977_x4280)), op=FltSub, results=List(CU.vecOut(x3985_x4283_v)))
    }
    val x4296_0 = Pipeline(name="x4296_0",parent=x4412) { implicit CU => 
      val x4291_x4291 =  VectorFIFO(size=1).wtPort(x3879_x4291_x4296_v)
      val x3978_x4292 =  ScalarBuffer().wtPort(x3978_x4198_s)
      val ctr19 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4286 = CounterChain(name = "x4286", ctr19).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4291_x4291), CU.load(x3978_x4292)), op=FltSub, results=List(CU.vecOut(x3986_x4295_v)))
    }
    val x4308_0 = Pipeline(name="x4308_0",parent=x4412) { implicit CU => 
      val x4303_x4303 =  VectorFIFO(size=1).wtPort(x3879_x4303_x4308_v)
      val x3979_x4304 =  ScalarBuffer().wtPort(x3979_x4210_s)
      val ctr20 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4298 = CounterChain(name = "x4298", ctr20).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4303_x4303), CU.load(x3979_x4304)), op=FltSub, results=List(CU.vecOut(x3987_x4307_v)))
    }
    val x4320_0 = Pipeline(name="x4320_0",parent=x4412) { implicit CU => 
      val x3980_x4316 =  ScalarBuffer().wtPort(x3980_x4222_s)
      val x4315_x4315 =  VectorFIFO(size=1).wtPort(x3879_x4315_x4320_v)
      val ctr21 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4310 = CounterChain(name = "x4310", ctr21).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4315_x4315), CU.load(x3980_x4316)), op=FltSub, results=List(CU.vecOut(x3988_x4319_v)))
    }
    val x4332_0 = Pipeline(name="x4332_0",parent=x4412) { implicit CU => 
      val x4327_x4327 =  VectorFIFO(size=1).wtPort(x3879_x4327_x4332_v)
      val x3981_x4328 =  ScalarBuffer().wtPort(x3981_x4234_s)
      val ctr22 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4322 = CounterChain(name = "x4322", ctr22).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4327_x4327), CU.load(x3981_x4328)), op=FltSub, results=List(CU.vecOut(x3989_x4331_v)))
    }
    val x4344_0 = Pipeline(name="x4344_0",parent=x4412) { implicit CU => 
      val x4339_x4339 =  VectorFIFO(size=1).wtPort(x3879_x4339_x4344_v)
      val x3982_x4340 =  ScalarBuffer().wtPort(x3982_x4246_s)
      val ctr23 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4334 = CounterChain(name = "x4334", ctr23).iter(12)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4339_x4339), CU.load(x3982_x4340)), op=FltSub, results=List(CU.vecOut(x3990_x4343_v)))
    }
    val x4410 = StreamController(name="x4410",parent=x4412) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4347 = CounterChain(name = "x4347", ctr24).iter(192)
    }
    val x4410_0 = Pipeline(name="x4410_0",parent=x4410) { implicit CU => 
      val x4352_x4352 =  VectorFIFO(size=1).wtPort(x3984_x4352_x4410_v)
      val x4351_x4351 =  VectorFIFO(size=1).wtPort(x3983_x4351_x4410_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4351_x4351), CU.load(x4352_x4352)), op=FltAdd, results=List(CU.scalarOut(bus_10468_s)))
    }
    val x4410_1 = Pipeline(name="x4410_1",parent=x4410) { implicit CU => 
      val x4377 = CU.temp
      val x4354_x4354 =  VectorFIFO(size=1).wtPort(x3986_x4354_x4410_v)
      val x4353_x4353 =  VectorFIFO(size=1).wtPort(x3985_x4353_x4410_v)
      val x4366 =  ScalarFIFO(size=1).wtPort(bus_10468_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4353_x4353), CU.load(x4354_x4354)), op=FltAdd, results=List(x4377))
      Stage(operands=List(CU.load(x4366), x4377), op=FltAdd, results=List(CU.scalarOut(bus_10477_s)))
    }
    val x4410_2 = Pipeline(name="x4410_2",parent=x4410) { implicit CU => 
      val x4356_x4356 =  VectorFIFO(size=1).wtPort(x3988_x4356_x4410_v)
      val x4355_x4355 =  VectorFIFO(size=1).wtPort(x3987_x4355_x4410_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4355_x4355), CU.load(x4356_x4356)), op=FltAdd, results=List(CU.scalarOut(bus_10493_s)))
    }
    val x4410_3 = Pipeline(name="x4410_3",parent=x4410) { implicit CU => 
      val x4406 = CU.temp
      val x4402 = CU.temp
      val x4404 = CU.temp
      val x4357_x4357 =  VectorFIFO(size=1).wtPort(x3989_x4357_x4410_v)
      val x4379 =  ScalarFIFO(size=1).wtPort(bus_10477_s)
      val x4359_x4359 =  VectorFIFO(size=1).wtPort(x3875_x4359_x4410_v)
      val x4398 =  ScalarFIFO(size=1).wtPort(bus_10493_s)
      val x4358_x4358 =  VectorFIFO(size=1).wtPort(x3990_x4358_x4410_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4357_x4357), CU.load(x4358_x4358)), op=FltAdd, results=List(x4402))
      Stage(operands=List(CU.load(x4398), x4402), op=FltAdd, results=List(x4404))
      Stage(operands=List(CU.load(x4379), x4404), op=FltAdd, results=List(x4406))
      Stage(operands=List(x4406, CU.load(x4359_x4359)), op=FltAdd, results=List(CU.vecOut(x3875_x4409_v)))
    }
    val x4424_0 = Pipeline(name="x4424_0",parent=x4427) { implicit CU => 
      val x4421 = CU.temp
      val x4418_x4418 =  VectorFIFO(size=1).wtPort(x3869_x4418_x4424_v)
      val x4417_x4417 =  VectorFIFO(size=1).wtPort(x3875_x4417_x4424_v)
      val ctr25 = Counter(min=Const(0), max=Const(192), step=Const(1), par=1) // Counter
      val x4415 = CounterChain(name = "x4415", ctr25).iter(192)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4418_x4418), Const(1)), op=FltMul, results=List(x4421))
      Stage(operands=List(CU.load(x4417_x4417), x4421), op=FltAdd, results=List(CU.vecOut(x3869_x4423_v)))
    }
    val x4453 = StreamController(name="x4453",parent=x4454) { implicit CU => 
    }
    val x4446 = Sequential(name="x4446",parent=x4453) { implicit CU => 
    }
    val x4438_0 = Pipeline(name="x4438_0",parent=x4446) { implicit CU => 
      val x4432 =  ScalarBuffer().wtPort(x4432_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x4432)), op=FixAdd, results=List(CU.scalarOut(x4429_b4566_x4437_b4568_s)))
      Stage(operands=List(Const(768)), op=Bypass, results=List(CU.scalarOut(x4429_b4567_x4437_b4569_s)))
    }
    val x4445 = Pipeline(name="x4445",parent=x4446) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(192), step=Const(1), par=16) // Counter
      val x4440 = CounterChain(name = "x4440", ctr26).iter(12)
      var stage: List[Stage] = Nil
    }
    val x4447 = MemoryController(name="x4447",parent=x4453,offchip=x3863_oc, mctpe=TileStore) { implicit CU => 
      val x4429_b4566_x4447 =  ScalarFIFO(name="offset",size=1).wtPort(x4429_b4566_x4437_b4568_s)
      val x4430_x4447 =  VectorFIFO(name="data",size=1).wtPort(x3869_x4441_x4445_v)
      val x4429_b4567_x4447 =  ScalarFIFO(name="size",size=1).wtPort(x4429_b4567_x4437_b4569_s)
    }
    
  }
}
