import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SPMV_CRS extends PIRApp {
  def main(top:Top) = {
    val x3858_x3858_dsp0_bank0_s = Scalar("x3858_x3858_dsp0_bank0")
    val x3796_b4192_x3824_b4200_s = Scalar("x3796_b4192_x3824_b4200")
    val x3878_x3908_data_s = Scalar("x3878_x3908_data")
    val x3782_x3782_dsp0_bank0_s = Scalar("x3782_x3782_dsp0_bank0")
    val bus_308_s = Scalar("bus_308")
    val bus_381_s = Scalar("bus_381")
    val bus_369_s = Scalar("bus_369")
    val x3783_x3783_dsp0_bank0_s = Scalar("x3783_x3783_dsp0_bank0")
    val bus_436_s = Scalar("bus_436")
    val bus_352_s = Scalar("bus_352")
    val values_dram_oc = OffChip("values_dram")
    val result_dram_oc = OffChip("result_dram")
    val x3940_b4219_x3969_b4227_s = Scalar("x3940_b4219_x3969_b4227")
    val bus_307_s = Scalar("bus_307")
    val x4095_b4229_x4125_b4231_s = Scalar("x4095_b4229_x4125_b4231")
    val bus_313_s = Scalar("bus_313")
    val vec_dram_oc = OffChip("vec_dram")
    val x4023_x4041_data_s = Scalar("x4023_x4041_data")
    val x3786_x3793_s = Scalar("x3786_x3793")
    val bus_440_s = Scalar("bus_440")
    val bus_375_s = Scalar("bus_375")
    val rowid_dram_oc = OffChip("rowid_dram")
    val x3939_b4216_x3967_b4224_s = Scalar("x3939_b4216_x3967_b4224")
    val bus_439_s = Scalar("bus_439")
    val x4087_x4093_s = Scalar("x4087_x4093")
    val x3859_x3859_dsp0_bank0_s = Scalar("x3859_x3859_dsp0_bank0")
    val result_dram_da = DRAMAddress("result_dram", "result_dram")
    val bus_335_s = Scalar("bus_335")
    val x3795_b4191_x3822_b4199_s = Scalar("x3795_b4191_x3822_b4199")
    val bus_298_s = Scalar("bus_298")
    val bus_299_s = Scalar("bus_299")
    val x3940_b4218_x3969_b4226_s = Scalar("x3940_b4218_x3969_b4226")
    val x3796_b4194_x3824_b4202_s = Scalar("x3796_b4194_x3824_b4202")
    val values_dram_da = DRAMAddress("values_dram", "values_dram")
    val x4057_x4079_s = Scalar("x4057_x4079")
    val bus_376_s = Scalar("bus_376")
    val x3877_b4207_x3906_b4215_s = Scalar("x3877_b4207_x3906_b4215")
    val rowid_dram_da = DRAMAddress("rowid_dram", "rowid_dram")
    val x3877_b4205_x3906_b4213_s = Scalar("x3877_b4205_x3906_b4213")
    val bus_340_s = Scalar("bus_340")
    val x3876_b4204_x3904_b4212_s = Scalar("x3876_b4204_x3904_b4212")
    val bus_435_s = Scalar("bus_435")
    val bus_337_s = Scalar("bus_337")
    val x3941_x3971_data_s = Scalar("x3941_x3971_data")
    val x4098_x4126_s = Scalar("x4098_x4126")
    val x3796_b4193_x3824_b4201_s = Scalar("x3796_b4193_x3824_b4201")
    val cols_dram_oc = OffChip("cols_dram")
    val x4010_x4020_s = Scalar("x4010_x4020")
    val x4095_b4230_x4125_b4232_s = Scalar("x4095_b4230_x4125_b4232")
    val bus_347_s = Scalar("bus_347")
    val x3876_b4203_x3904_b4211_s = Scalar("x3876_b4203_x3904_b4211")
    val bus_401_s = Scalar("bus_401")
    val bus_367_s = Scalar("bus_367")
    val bus_437_s = Scalar("bus_437")
    val x3785_x3792_s = Scalar("x3785_x3792")
    val x3795_b4190_x3822_b4198_s = Scalar("x3795_b4190_x3822_b4198")
    val x4100_x4128_s = Scalar("x4100_x4128")
    val bus_346_s = Scalar("bus_346")
    val vec_dram_da = DRAMAddress("vec_dram", "vec_dram")
    val bus_338_s = Scalar("bus_338")
    val x3797_x3826_data_s = Scalar("x3797_x3826_data")
    val x4086_x4092_s = Scalar("x4086_x4092")
    val x3877_b4206_x3906_b4214_s = Scalar("x3877_b4206_x3906_b4214")
    val x3857_x3857_dsp0_bank0_s = Scalar("x3857_x3857_dsp0_bank0")
    val x3782_x3782_dsp1_bank0_s = Scalar("x3782_x3782_dsp1_bank0")
    val x4099_x4127_s = Scalar("x4099_x4127")
    val bus_301_s = Scalar("bus_301")
    val bus_364_s = Scalar("bus_364")
    val bus_296_s = Scalar("bus_296")
    val x4096_x4145_s = Scalar("x4096_x4145")
    val bus_366_s = Scalar("bus_366")
    val x3869_x3874_s = Scalar("x3869_x3874")
    val x3940_b4220_x3969_b4228_s = Scalar("x3940_b4220_x3969_b4228")
    val bus_395_s = Scalar("bus_395")
    val x3784_x3791_s = Scalar("x3784_x3791")
    val cols_dram_da = DRAMAddress("cols_dram", "cols_dram")
    val bus_430_s = Scalar("bus_430")
    val x4003_x4008_s = Scalar("x4003_x4008")
    val bus_429_s = Scalar("bus_429")
    val x3939_b4217_x3967_b4225_s = Scalar("x3939_b4217_x3967_b4225")
    val x4022_x4039_s = Scalar("x4022_x4039")
    val x4153 = Sequential(name="x4153",parent=top) { implicit CU => 
      val x4153_unit = CounterChain(name = "x4153_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4152 = MetaPipeline(name="x4152",parent=x4153) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3781 = CounterChain(name = "x3781", ctr1).iter(1)
    }
    val x3782_dsp0_bank0 = MemoryPipeline(name="x3782_dsp0_bank0",parent="x4152") { implicit CU => 
      val x3851 = ScalarFIFO(size=1,name="x3851").wtPort(x3797_x3826_data_s)
      val x3827 = ScalarBuffer(name="x3827").wtPort(x3796_b4193_x3824_b4201_s)
      val x3840 = CounterChain.copy("x3852_0", "x3840")
      val x3856 = CounterChain.copy("x4085", "x3856")
      val x3782 = SRAM(size=495,name="x3782",banking = Strided(1)).wtPort(x3851.readPort).rdPort(x3782_x3782_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x3840(0)), CU.load(x3827)), op=FixSub, results=List(x3782.writeAddr))
      RAStage(operands=List(CU.ctr(x3856(0)), Const(1)), op=FixAdd, results=List(x3782.readAddr))
    }
    val x3782_dsp1_bank0 = MemoryPipeline(name="x3782_dsp1_bank0",parent="x4152") { implicit CU => 
      val x3851 = ScalarFIFO(size=1,name="x3851").wtPort(x3797_x3826_data_s)
      val x3827 = ScalarBuffer(name="x3827").wtPort(x3796_b4193_x3824_b4201_s)
      val x3840 = CounterChain.copy("x3852_0", "x3840")
      val x3856 = CounterChain.copy("x4085", "x3856")
      val x3782 = SRAM(size=495,name="x3782",banking = Strided(1)).wtPort(x3851.readPort).rdPort(x3782_x3782_dsp1_bank0_s).rdAddr(x3856(0))
      WAStage(operands=List(CU.ctr(x3840(0)), CU.load(x3827)), op=FixSub, results=List(x3782.writeAddr))
    }
    val x3783_dsp0_bank0 = MemoryPipeline(name="x3783_dsp0_bank0",parent="x4152") { implicit CU => 
      val x4098 = ScalarBuffer(name="x4098").wtPort(x4098_x4126_s)
      val x4083 = ScalarFIFO(size=1,name="x4083").wtPort(x4057_x4079_s)
      val x3856 = CounterChain.copy("x4085", "x3856")
      val x4132 = CounterChain.copy("x4146_0", "x4132")
      val x3783 = SRAM(size=494,name="x3783",banking = Strided(1)).wtPort(x4083.readPort).rdPort(x3783_x3783_dsp0_bank0_s).wtAddr(x3856(0))
      RAStage(operands=List(CU.ctr(x4132(0)), CU.load(x4098)), op=FixSub, results=List(x3783.readAddr))
    }
    val x3794_0 = Pipeline(name="x3794_0",parent=x4152) { implicit CU => 
      val x3788 = CU.temp(None)
      val x3789 = CU.temp(None)
      val x3787 = CU.temp(None)
      val x3781 = CounterChain.copy("x4152", "x3781")
      val x3794_unit = CounterChain(name = "x3794_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3781(0)), Const(495)), op=FixMul, results=List(x3787, CU.scalarOut(x3784_x3791_s)))
      Stage(operands=List(CU.ctr(x3781(0)), Const(1)), op=FixAdd, results=List(x3788, CU.scalarOut(x3785_x3792_s)))
      Stage(operands=List(x3788, Const(495)), op=FixMul, results=List(x3789))
      Stage(operands=List(x3789, x3787), op=FixSub, results=List(CU.scalarOut(x3786_x3793_s)))
    }
    val x3854 = StreamController(name="x3854",parent=x4152) { implicit CU => 
      val x3854_unit = CounterChain(name = "x3854_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3825 = StreamController(name="x3825",parent=x3854) { implicit CU => 
      val x3825_unit = CounterChain(name = "x3825_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3825_0 = Pipeline(name="x3825_0",parent=x3825) { implicit CU => 
      val x3800 = CU.temp(None)
      val x3801 = CU.temp(None)
      val x3823_x3810 = CU.temp(None)
      val x3784 = ScalarBuffer(name="x3784").wtPort(x3784_x3791_s)
      val x3786 = ScalarBuffer(name="x3786").wtPort(x3786_x3793_s)
      Stage(operands=List(CU.load(x3784), Const(2)), op=FixSla, results=List(x3800, CU.scalarOut(bus_296_s)))
      Stage(operands=List(x3800, Const(64)), op=FixMod, results=List(x3801, CU.scalarOut(bus_298_s)))
      Stage(operands=List(x3801, Const(2)), op=FixSra, results=List(x3823_x3810, CU.scalarOut(bus_299_s), CU.scalarOut(x3796_b4193_x3824_b4201_s)))
      Stage(operands=List(x3823_x3810, CU.load(x3786)), op=FixAdd, results=List(CU.scalarOut(x3796_b4194_x3824_b4202_s)))
      Stage(operands=List(CU.load(x3786), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_301_s)))
    }
    val x3825_1 = Pipeline(name="x3825_1",parent=x3825) { implicit CU => 
      val x3805 = CU.temp(None)
      val x3808 = CU.temp(None)
      val x3809 = CU.temp(None)
      val x3806 = CU.temp(None)
      val x3807 = CU.temp(None)
      val x3803 = ScalarFIFO(size=1,name="x3803").wtPort(bus_301_s)
      val x3800 = ScalarFIFO(size=1,name="x3800").wtPort(bus_296_s)
      Stage(operands=List(CU.load(x3800), CU.load(x3803)), op=FixAdd, results=List(x3805))
      Stage(operands=List(x3805, Const(64)), op=FixMod, results=List(x3806))
      Stage(operands=List(x3806, Const(0)), op=FixEql, results=List(x3807))
      Stage(operands=List(Const(64), x3806), op=FixSub, results=List(x3808))
      Stage(operands=List(x3807, Const(0), x3808), op=Mux, results=List(x3809, CU.scalarOut(bus_307_s)))
      Stage(operands=List(x3809, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_308_s)))
    }
    val x3825_2 = Pipeline(name="x3825_2",parent=x3825) { implicit CU => 
      val x3815 = CU.temp(None)
      val x3813 = CU.temp(None)
      val x3810 = ScalarFIFO(size=1,name="x3810").wtPort(bus_299_s)
      val x3800 = ScalarFIFO(size=1,name="x3800").wtPort(bus_296_s)
      val x3786 = ScalarBuffer(name="x3786").wtPort(x3786_x3793_s)
      val x3803 = ScalarFIFO(size=1,name="x3803").wtPort(bus_301_s)
      val x3811 = ScalarFIFO(size=1,name="x3811").wtPort(bus_308_s)
      val x3801 = ScalarFIFO(size=1,name="x3801").wtPort(bus_298_s)
      val x3809 = ScalarFIFO(size=1,name="x3809").wtPort(bus_307_s)
      Stage(operands=List(CU.load(x3786), CU.load(x3810)), op=FixAdd, results=List(x3813))
      Stage(operands=List(x3813, CU.load(x3811)), op=FixAdd, results=List(CU.scalarOut(x3796_b4192_x3824_b4200_s)))
      Stage(operands=List(CU.load(x3803), CU.load(x3801)), op=FixAdd, results=List(x3815))
      Stage(operands=List(x3815, CU.load(x3809)), op=FixAdd, results=List(CU.scalarOut(x3795_b4191_x3822_b4199_s)))
      Stage(operands=List(CU.load(x3800), CU.load(x3801)), op=FixSub, results=List(CU.scalarOut(bus_313_s)))
    }
    val x3825_3 = Pipeline(name="x3825_3",parent=x3825) { implicit CU => 
      val x3818 = ScalarBuffer(name="x3818").wtPort(rowid_dram_da)
      val x3804 = ScalarFIFO(size=1,name="x3804").wtPort(bus_313_s)
      Stage(operands=List(CU.load(x3804), CU.load(x3818)), op=FixAdd, results=List(CU.scalarOut(x3795_b4190_x3822_b4198_s)))
    }
    val x3826 = MemoryController(name="x3826",parent=x3854,offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x3795_b4191 = ScalarFIFO(size=1,name="size").wtPort(x3795_b4191_x3822_b4199_s)
      val x3795_b4190 = ScalarFIFO(size=1,name="offset").wtPort(x3795_b4190_x3822_b4198_s)
      CU.newSout("data", x3797_x3826_data_s)
    }
    val x3853 = Sequential(name="x3853",parent=x3854) { implicit CU => 
      val x3853_unit = CounterChain(name = "x3853_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3852_0 = Pipeline(name="x3852_0",parent=x3853) { implicit CU => 
      val x3827 = ScalarBuffer(name="x3827").wtPort(x3796_b4193_x3824_b4201_s)
      val x3829 = ScalarBuffer(name="x3829").wtPort(x3796_b4192_x3824_b4200_s)
      val x3828 = ScalarBuffer(name="x3828").wtPort(x3796_b4194_x3824_b4202_s)
      val ctr2 = Counter(min=Const(0), max=x3829.readPort, step=Const(1), par=16) // Counter
      val x3840 = CounterChain(name = "x3840", ctr2).iter(1)
      Stage(operands=List(CU.load(x3827), CU.ctr(x3840(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x3840(0)), CU.load(x3828)), op=FixLt, results=List())
    }
    val x4085 = MetaPipeline(name="x4085",parent=x4152) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(494), step=Const(1), par=1) // Counter
      val x3856 = CounterChain(name = "x3856", ctr3).iter(494)
    }
    val x3857_dsp0_bank0 = MemoryPipeline(name="x3857_dsp0_bank0",parent="x4085") { implicit CU => 
      val x3935 = ScalarFIFO(size=1,name="x3935").wtPort(x3878_x3908_data_s)
      val x3909 = ScalarBuffer(name="x3909").wtPort(x3877_b4206_x3906_b4214_s)
      val x3923 = CounterChain.copy("x3936_0", "x3923")
      val x4026 = CounterChain.copy("x4040_0", "x4026")
      val x3857 = SRAM(size=494,name="x3857",banking = Strided(1)).wtPort(x3935.readPort).rdPort(x3857_x3857_dsp0_bank0_s).rdAddr(x4026(0))
      WAStage(operands=List(CU.ctr(x3923(0)), CU.load(x3909)), op=FixSub, results=List(x3857.writeAddr))
    }
    val x3858_dsp0_bank0 = MemoryPipeline(name="x3858_dsp0_bank0",parent="x4085") { implicit CU => 
      val x3998 = ScalarFIFO(size=1,name="x3998").wtPort(x3941_x3971_data_s)
      val x3972 = ScalarBuffer(name="x3972").wtPort(x3940_b4219_x3969_b4227_s)
      val x3986 = CounterChain.copy("x3999_0", "x3986")
      val x4068 = CounterChain.copy("x4080_0", "x4068")
      val x3858 = SRAM(size=494,name="x3858",banking = Strided(1)).wtPort(x3998.readPort).rdPort(x3858_x3858_dsp0_bank0_s).rdAddr(x4068(0))
      WAStage(operands=List(CU.ctr(x3986(0)), CU.load(x3972)), op=FixSub, results=List(x3858.writeAddr))
    }
    val x3859_dsp0_bank0 = MemoryPipeline(name="x3859_dsp0_bank0",parent="x4085") { implicit CU => 
      val x4052 = ScalarFIFO(size=1,name="x4052").wtPort(x4023_x4041_data_s)
      val x4044 = CounterChain.copy("x4053", "x4044")
      val x4068 = CounterChain.copy("x4080_0", "x4068")
      val x3859 = SRAM(size=494,name="x3859",banking = Strided(1)).wtPort(x4052.readPort).rdPort(x3859_x3859_dsp0_bank0_s).rdAddr(x4068(0)).wtAddr(x4044(0))
    }
    val x3875_0 = Pipeline(name="x3875_0",parent=x4085) { implicit CU => 
      val x3860 = ScalarBuffer(name="x3860").wtPort(x3782_x3782_dsp1_bank0_s)
      val x3861 = ScalarBuffer(name="x3861").wtPort(x3782_x3782_dsp0_bank0_s)
      val x3875_unit = CounterChain(name = "x3875_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3861), CU.load(x3860)), op=FixSub, results=List(CU.scalarOut(x3869_x3874_s)))
    }
    val x3938 = StreamController(name="x3938",parent=x4085) { implicit CU => 
      val x3938_unit = CounterChain(name = "x3938_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3907 = StreamController(name="x3907",parent=x3938) { implicit CU => 
      val x3907_unit = CounterChain(name = "x3907_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3907_0 = Pipeline(name="x3907_0",parent=x3907) { implicit CU => 
      val x3882 = CU.temp(None)
      val x3881 = CU.temp(None)
      val x3905_x3891 = CU.temp(None)
      val x3869 = ScalarBuffer(name="x3869").wtPort(x3869_x3874_s)
      val x3860 = ScalarBuffer(name="x3860").wtPort(x3782_x3782_dsp1_bank0_s)
      Stage(operands=List(CU.load(x3860), Const(2)), op=FixSla, results=List(x3881, CU.scalarOut(bus_335_s)))
      Stage(operands=List(x3881, Const(64)), op=FixMod, results=List(x3882, CU.scalarOut(bus_337_s)))
      Stage(operands=List(x3882, Const(2)), op=FixSra, results=List(x3905_x3891, CU.scalarOut(bus_338_s), CU.scalarOut(x3877_b4206_x3906_b4214_s)))
      Stage(operands=List(x3905_x3891, CU.load(x3869)), op=FixAdd, results=List(CU.scalarOut(x3877_b4207_x3906_b4215_s)))
      Stage(operands=List(CU.load(x3869), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_340_s)))
    }
    val x3907_1 = Pipeline(name="x3907_1",parent=x3907) { implicit CU => 
      val x3888 = CU.temp(None)
      val x3890 = CU.temp(None)
      val x3886 = CU.temp(None)
      val x3887 = CU.temp(None)
      val x3889 = CU.temp(None)
      val x3881 = ScalarFIFO(size=1,name="x3881").wtPort(bus_335_s)
      val x3884 = ScalarFIFO(size=1,name="x3884").wtPort(bus_340_s)
      Stage(operands=List(CU.load(x3881), CU.load(x3884)), op=FixAdd, results=List(x3886))
      Stage(operands=List(x3886, Const(64)), op=FixMod, results=List(x3887))
      Stage(operands=List(x3887, Const(0)), op=FixEql, results=List(x3888))
      Stage(operands=List(Const(64), x3887), op=FixSub, results=List(x3889))
      Stage(operands=List(x3888, Const(0), x3889), op=Mux, results=List(x3890, CU.scalarOut(bus_346_s)))
      Stage(operands=List(x3890, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_347_s)))
    }
    val x3907_2 = Pipeline(name="x3907_2",parent=x3907) { implicit CU => 
      val x3896 = CU.temp(None)
      val x3894 = CU.temp(None)
      val x3891 = ScalarFIFO(size=1,name="x3891").wtPort(bus_338_s)
      val x3882 = ScalarFIFO(size=1,name="x3882").wtPort(bus_337_s)
      val x3892 = ScalarFIFO(size=1,name="x3892").wtPort(bus_347_s)
      val x3890 = ScalarFIFO(size=1,name="x3890").wtPort(bus_346_s)
      val x3884 = ScalarFIFO(size=1,name="x3884").wtPort(bus_340_s)
      val x3869 = ScalarBuffer(name="x3869").wtPort(x3869_x3874_s)
      val x3881 = ScalarFIFO(size=1,name="x3881").wtPort(bus_335_s)
      Stage(operands=List(CU.load(x3869), CU.load(x3891)), op=FixAdd, results=List(x3894))
      Stage(operands=List(x3894, CU.load(x3892)), op=FixAdd, results=List(CU.scalarOut(x3877_b4205_x3906_b4213_s)))
      Stage(operands=List(CU.load(x3884), CU.load(x3882)), op=FixAdd, results=List(x3896))
      Stage(operands=List(x3896, CU.load(x3890)), op=FixAdd, results=List(CU.scalarOut(x3876_b4204_x3904_b4212_s)))
      Stage(operands=List(CU.load(x3881), CU.load(x3882)), op=FixSub, results=List(CU.scalarOut(bus_352_s)))
    }
    val x3907_3 = Pipeline(name="x3907_3",parent=x3907) { implicit CU => 
      val x3885 = ScalarFIFO(size=1,name="x3885").wtPort(bus_352_s)
      val x3899 = ScalarBuffer(name="x3899").wtPort(cols_dram_da)
      Stage(operands=List(CU.load(x3885), CU.load(x3899)), op=FixAdd, results=List(CU.scalarOut(x3876_b4203_x3904_b4211_s)))
    }
    val x3908 = MemoryController(name="x3908",parent=x3938,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x3876_b4204 = ScalarFIFO(size=1,name="size").wtPort(x3876_b4204_x3904_b4212_s)
      val x3876_b4203 = ScalarFIFO(size=1,name="offset").wtPort(x3876_b4203_x3904_b4211_s)
      CU.newSout("data", x3878_x3908_data_s)
    }
    val x3937 = Sequential(name="x3937",parent=x3938) { implicit CU => 
      val x3937_unit = CounterChain(name = "x3937_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3936_0 = Pipeline(name="x3936_0",parent=x3937) { implicit CU => 
      val x3911 = ScalarBuffer(name="x3911").wtPort(x3877_b4205_x3906_b4213_s)
      val x3910 = ScalarBuffer(name="x3910").wtPort(x3877_b4207_x3906_b4215_s)
      val x3909 = ScalarBuffer(name="x3909").wtPort(x3877_b4206_x3906_b4214_s)
      val ctr4 = Counter(min=Const(0), max=x3911.readPort, step=Const(1), par=16) // Counter
      val x3923 = CounterChain(name = "x3923", ctr4).iter(1)
      Stage(operands=List(CU.load(x3909), CU.ctr(x3923(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x3923(0)), CU.load(x3910)), op=FixLt, results=List())
    }
    val x4001 = StreamController(name="x4001",parent=x4085) { implicit CU => 
      val x4001_unit = CounterChain(name = "x4001_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3970 = StreamController(name="x3970",parent=x4001) { implicit CU => 
      val x3970_unit = CounterChain(name = "x3970_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3970_0 = Pipeline(name="x3970_0",parent=x3970) { implicit CU => 
      val x3968_x3954 = CU.temp(None)
      val x3945 = CU.temp(None)
      val x3944 = CU.temp(None)
      val x3869 = ScalarBuffer(name="x3869").wtPort(x3869_x3874_s)
      val x3860 = ScalarBuffer(name="x3860").wtPort(x3782_x3782_dsp1_bank0_s)
      Stage(operands=List(CU.load(x3860), Const(2)), op=FixSla, results=List(x3944, CU.scalarOut(bus_364_s)))
      Stage(operands=List(x3944, Const(64)), op=FixMod, results=List(x3945, CU.scalarOut(bus_366_s)))
      Stage(operands=List(x3945, Const(2)), op=FixSra, results=List(x3968_x3954, CU.scalarOut(bus_367_s), CU.scalarOut(x3940_b4219_x3969_b4227_s)))
      Stage(operands=List(x3968_x3954, CU.load(x3869)), op=FixAdd, results=List(CU.scalarOut(x3940_b4220_x3969_b4228_s)))
      Stage(operands=List(CU.load(x3869), Const(2)), op=FixSla, results=List(CU.scalarOut(bus_369_s)))
    }
    val x3970_1 = Pipeline(name="x3970_1",parent=x3970) { implicit CU => 
      val x3950 = CU.temp(None)
      val x3953 = CU.temp(None)
      val x3952 = CU.temp(None)
      val x3949 = CU.temp(None)
      val x3951 = CU.temp(None)
      val x3947 = ScalarFIFO(size=1,name="x3947").wtPort(bus_369_s)
      val x3944 = ScalarFIFO(size=1,name="x3944").wtPort(bus_364_s)
      Stage(operands=List(CU.load(x3944), CU.load(x3947)), op=FixAdd, results=List(x3949))
      Stage(operands=List(x3949, Const(64)), op=FixMod, results=List(x3950))
      Stage(operands=List(x3950, Const(0)), op=FixEql, results=List(x3951))
      Stage(operands=List(Const(64), x3950), op=FixSub, results=List(x3952))
      Stage(operands=List(x3951, Const(0), x3952), op=Mux, results=List(x3953, CU.scalarOut(bus_375_s)))
      Stage(operands=List(x3953, Const(2)), op=FixSra, results=List(CU.scalarOut(bus_376_s)))
    }
    val x3970_2 = Pipeline(name="x3970_2",parent=x3970) { implicit CU => 
      val x3959 = CU.temp(None)
      val x3957 = CU.temp(None)
      val x3945 = ScalarFIFO(size=1,name="x3945").wtPort(bus_366_s)
      val x3947 = ScalarFIFO(size=1,name="x3947").wtPort(bus_369_s)
      val x3953 = ScalarFIFO(size=1,name="x3953").wtPort(bus_375_s)
      val x3955 = ScalarFIFO(size=1,name="x3955").wtPort(bus_376_s)
      val x3944 = ScalarFIFO(size=1,name="x3944").wtPort(bus_364_s)
      val x3869 = ScalarBuffer(name="x3869").wtPort(x3869_x3874_s)
      val x3954 = ScalarFIFO(size=1,name="x3954").wtPort(bus_367_s)
      Stage(operands=List(CU.load(x3869), CU.load(x3954)), op=FixAdd, results=List(x3957))
      Stage(operands=List(x3957, CU.load(x3955)), op=FixAdd, results=List(CU.scalarOut(x3940_b4218_x3969_b4226_s)))
      Stage(operands=List(CU.load(x3947), CU.load(x3945)), op=FixAdd, results=List(x3959))
      Stage(operands=List(x3959, CU.load(x3953)), op=FixAdd, results=List(CU.scalarOut(x3939_b4217_x3967_b4225_s)))
      Stage(operands=List(CU.load(x3944), CU.load(x3945)), op=FixSub, results=List(CU.scalarOut(bus_381_s)))
    }
    val x3970_3 = Pipeline(name="x3970_3",parent=x3970) { implicit CU => 
      val x3948 = ScalarFIFO(size=1,name="x3948").wtPort(bus_381_s)
      val x3962 = ScalarBuffer(name="x3962").wtPort(values_dram_da)
      Stage(operands=List(CU.load(x3948), CU.load(x3962)), op=FixAdd, results=List(CU.scalarOut(x3939_b4216_x3967_b4224_s)))
    }
    val x3971 = MemoryController(name="x3971",parent=x4001,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x3939_b4216 = ScalarFIFO(size=1,name="offset").wtPort(x3939_b4216_x3967_b4224_s)
      val x3939_b4217 = ScalarFIFO(size=1,name="size").wtPort(x3939_b4217_x3967_b4225_s)
      CU.newSout("data", x3941_x3971_data_s)
    }
    val x4000 = Sequential(name="x4000",parent=x4001) { implicit CU => 
      val x4000_unit = CounterChain(name = "x4000_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3999_0 = Pipeline(name="x3999_0",parent=x4000) { implicit CU => 
      val x3973 = ScalarBuffer(name="x3973").wtPort(x3940_b4220_x3969_b4228_s)
      val x3972 = ScalarBuffer(name="x3972").wtPort(x3940_b4219_x3969_b4227_s)
      val x3974 = ScalarBuffer(name="x3974").wtPort(x3940_b4218_x3969_b4226_s)
      val ctr5 = Counter(min=Const(0), max=x3974.readPort, step=Const(1), par=16) // Counter
      val x3986 = CounterChain(name = "x3986", ctr5).iter(1)
      Stage(operands=List(CU.load(x3972), CU.ctr(x3986(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x3986(0)), CU.load(x3973)), op=FixLt, results=List())
    }
    val x4009_0 = Pipeline(name="x4009_0",parent=x4085) { implicit CU => 
      val x3860 = ScalarBuffer(name="x3860").wtPort(x3782_x3782_dsp1_bank0_s)
      val x3861 = ScalarBuffer(name="x3861").wtPort(x3782_x3782_dsp0_bank0_s)
      val x4009_unit = CounterChain(name = "x4009_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3861), CU.load(x3860)), op=FixSub, results=List(CU.scalarOut(x4003_x4008_s)))
    }
    val x4021 = StreamController(name="x4021",parent=x4085) { implicit CU => 
      val x4021_unit = CounterChain(name = "x4021_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4021_0 = Pipeline(name="x4021_0",parent=x4021) { implicit CU => 
      val x4014 = CU.temp(None)
      val x4013 = CU.temp(None)
      val x4015 = CU.temp(None)
      val x4016 = CU.temp(None)
      val x4003 = ScalarBuffer(name="x4003").wtPort(x4003_x4008_s)
      Stage(operands=List(CU.load(x4003), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_395_s)))
      Stage(operands=List(CU.load(x4003), Const(16)), op=FixMod, results=List(x4013))
      Stage(operands=List(x4013, Const(0)), op=FixEql, results=List(x4014))
      Stage(operands=List(CU.load(x4003), Const(16)), op=FixAdd, results=List(x4015))
      Stage(operands=List(x4015, x4013), op=FixSub, results=List(x4016))
      Stage(operands=List(x4014, CU.load(x4003), x4016), op=Mux, results=List(CU.scalarOut(bus_401_s)))
    }
    val x4021_1 = Pipeline(name="x4021_1",parent=x4021) { implicit CU => 
      val x4012 = ScalarFIFO(size=1,name="x4012").wtPort(bus_395_s)
      val x4017 = ScalarFIFO(size=1,name="x4017").wtPort(bus_401_s)
      Stage(operands=List(CU.load(x4012), Const(16), CU.load(x4017)), op=Mux, results=List(CU.scalarOut(x4010_x4020_s)))
    }
    val x4054 = StreamController(name="x4054",parent=x4085) { implicit CU => 
      val x4054_unit = CounterChain(name = "x4054_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4040_0 = Pipeline(name="x4040_0",parent=x4054) { implicit CU => 
      val x4037 = CU.temp(None)
      val x4028 = CU.temp(None)
      val x4035 = CU.temp(None)
      val x4033 = ScalarFIFO(size=1,name="x4033").wtPort(x3857_x3857_dsp0_bank0_s)
      val x4003 = ScalarBuffer(name="x4003").wtPort(x4003_x4008_s)
      val x4029 = ScalarBuffer(name="x4029").wtPort(vec_dram_da)
      val x4010 = ScalarBuffer(name="x4010").wtPort(x4010_x4020_s)
      val ctr6 = Counter(min=Const(0), max=x4010.readPort, step=Const(1), par=1) // Counter
      val x4026 = CounterChain(name = "x4026", ctr6).iter(1)
      Stage(operands=List(CU.load(x4003), CU.ctr(x4026(0))), op=FixLeq, results=List(x4028))
      Stage(operands=List(CU.load(x4033), Const(2)), op=FixSla, results=List(x4035))
      Stage(operands=List(x4035, CU.load(x4029)), op=FixAdd, results=List(x4037))
      Stage(operands=List(x4028, CU.load(x4029), x4037), op=Mux, results=List(CU.scalarOut(x4022_x4039_s)))
    }
    val x4041 = MemoryController(name="x4041",parent=x4054,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x4022 = ScalarFIFO(size=1,name="addr").wtPort(x4022_x4039_s)
      CU.newSout("data", x4023_x4041_data_s)
    }
    val x4053 = Pipeline(name="x4053",parent=x4054) { implicit CU => 
      val x4003 = ScalarBuffer(name="x4003").wtPort(x4003_x4008_s)
      val x4010 = ScalarBuffer(name="x4010").wtPort(x4010_x4020_s)
      val ctr7 = Counter(min=Const(0), max=x4010.readPort, step=Const(1), par=1) // Counter
      val x4044 = CounterChain(name = "x4044", ctr7).iter(1)
    }
    val x4080_0 = Pipeline(name="x4080_0",parent=x4085) { implicit CU => 
      val x4072 = ScalarFIFO(size=1,name="x4072").wtPort(x3859_x3859_dsp0_bank0_s)
      val x4003 = ScalarBuffer(name="x4003").wtPort(x4003_x4008_s)
      val x4071 = ScalarFIFO(size=1,name="x4071").wtPort(x3858_x3858_dsp0_bank0_s)
      val ctr8 = Counter(min=Const(0), max=x4003.readPort, step=Const(1), par=16) // Counter
      val x4068 = CounterChain(name = "x4068", ctr8).iter(1)
      Stage(operands=List(CU.load(x4071), CU.load(x4072)), op=FixMul, results=List(CU.reduce))
      val (_, rr421) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x4080_0")
      Stage(operands=List(rr421), op=Bypass, results=List(CU.scalarOut(x4057_x4079_s)))
    }
    val x4094_0 = Pipeline(name="x4094_0",parent=x4152) { implicit CU => 
      val x4090 = CU.temp(None)
      val x4088 = CU.temp(None)
      val x3785 = ScalarBuffer(name="x3785").wtPort(x3785_x3792_s)
      val x3781 = CounterChain.copy("x4152", "x3781")
      val x4094_unit = CounterChain(name = "x4094_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3781(0)), Const(494)), op=FixMul, results=List(x4088, CU.scalarOut(x4086_x4092_s)))
      Stage(operands=List(CU.load(x3785), Const(494)), op=FixMul, results=List(x4090))
      Stage(operands=List(x4090, x4088), op=FixSub, results=List(CU.scalarOut(x4087_x4093_s)))
    }
    val x4151 = StreamController(name="x4151",parent=x4152) { implicit CU => 
      val x4151_unit = CounterChain(name = "x4151_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4147 = Sequential(name="x4147",parent=x4151) { implicit CU => 
      val x4147_unit = CounterChain(name = "x4147_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4129 = StreamController(name="x4129",parent=x4147) { implicit CU => 
      val x4129_unit = CounterChain(name = "x4129_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4129_0 = Pipeline(name="x4129_0",parent=x4129) { implicit CU => 
      val x4108 = CU.temp(None)
      val x4109 = CU.temp(None)
      val x4106 = CU.temp(None)
      val x4103 = CU.temp(None)
      val x4087 = ScalarBuffer(name="x4087").wtPort(x4087_x4093_s)
      val x4086 = ScalarBuffer(name="x4086").wtPort(x4086_x4092_s)
      Stage(operands=List(CU.load(x4086), Const(2)), op=FixSla, results=List(x4103, CU.scalarOut(bus_429_s)))
      Stage(operands=List(CU.load(x4087), Const(2)), op=FixSla, results=List(x4106, CU.scalarOut(bus_430_s)))
      Stage(operands=List(x4103, x4106), op=FixAdd, results=List(x4108))
      Stage(operands=List(x4108, Const(64)), op=FixMod, results=List(x4109))
      Stage(operands=List(x4109, Const(0)), op=FixEql, results=List(CU.scalarOut(bus_435_s)))
      Stage(operands=List(Const(64), x4109), op=FixSub, results=List(CU.scalarOut(bus_436_s)))
    }
    val x4129_1 = Pipeline(name="x4129_1",parent=x4129) { implicit CU => 
      val x4104 = CU.temp(None)
      val x4114 = CU.temp(None)
      val x4116 = CU.temp(None)
      val x4113 = CU.temp(None)
      val x4112 = CU.temp(None)
      val x4103 = ScalarFIFO(size=1,name="x4103").wtPort(bus_429_s)
      val x4087 = ScalarBuffer(name="x4087").wtPort(x4087_x4093_s)
      val x4110 = ScalarFIFO(size=1,name="x4110").wtPort(bus_435_s)
      val x4111 = ScalarFIFO(size=1,name="x4111").wtPort(bus_436_s)
      Stage(operands=List(CU.load(x4110), Const(0), CU.load(x4111)), op=Mux, results=List(x4112, CU.scalarOut(bus_437_s)))
      Stage(operands=List(x4112, Const(2)), op=FixSra, results=List(x4114))
      Stage(operands=List(CU.load(x4103), Const(64)), op=FixMod, results=List(x4104, CU.scalarOut(bus_439_s)))
      Stage(operands=List(x4104, Const(2)), op=FixSra, results=List(x4113, CU.scalarOut(bus_440_s), CU.scalarOut(x4098_x4126_s)))
      Stage(operands=List(CU.load(x4087), x4113), op=FixAdd, results=List(x4116))
      Stage(operands=List(x4116, x4114), op=FixAdd, results=List(CU.scalarOut(x4100_x4128_s)))
    }
    val x4129_2 = Pipeline(name="x4129_2",parent=x4129) { implicit CU => 
      val x4118 = CU.temp(None)
      val x4107 = CU.temp(None)
      val x4106 = ScalarFIFO(size=1,name="x4106").wtPort(bus_430_s)
      val x4113 = ScalarFIFO(size=1,name="x4113").wtPort(bus_440_s)
      val x4103 = ScalarFIFO(size=1,name="x4103").wtPort(bus_429_s)
      val x4104 = ScalarFIFO(size=1,name="x4104").wtPort(bus_439_s)
      val x4112 = ScalarFIFO(size=1,name="x4112").wtPort(bus_437_s)
      val x4121 = ScalarBuffer(name="x4121").wtPort(result_dram_da)
      val x4087 = ScalarBuffer(name="x4087").wtPort(x4087_x4093_s)
      Stage(operands=List(CU.load(x4113), CU.load(x4087)), op=FixAdd, results=List(CU.scalarOut(x4099_x4127_s)))
      Stage(operands=List(CU.load(x4106), CU.load(x4104)), op=FixAdd, results=List(x4118))
      Stage(operands=List(x4118, CU.load(x4112)), op=FixAdd, results=List(CU.scalarOut(x4095_b4230_x4125_b4232_s)))
      Stage(operands=List(CU.load(x4103), CU.load(x4104)), op=FixSub, results=List(x4107))
      Stage(operands=List(x4107, CU.load(x4121)), op=FixAdd, results=List(CU.scalarOut(x4095_b4229_x4125_b4231_s)))
    }
    val x4146_0 = Pipeline(name="x4146_0",parent=x4147) { implicit CU => 
      val x4134 = CU.temp(None)
      val x4136 = CU.temp(None)
      val x4144_x4137 = CU.temp(None)
      val x4141 = ScalarFIFO(size=1,name="x4141").wtPort(x3783_x3783_dsp0_bank0_s)
      val x4099 = ScalarBuffer(name="x4099").wtPort(x4099_x4127_s)
      val x4098 = ScalarBuffer(name="x4098").wtPort(x4098_x4126_s)
      val x4100 = ScalarBuffer(name="x4100").wtPort(x4100_x4128_s)
      val ctr9 = Counter(min=Const(0), max=x4100.readPort, step=Const(1), par=16) // Counter
      val x4132 = CounterChain(name = "x4132", ctr9).iter(1)
      Stage(operands=List(CU.load(x4098), CU.ctr(x4132(0))), op=FixLeq, results=List(x4134))
      Stage(operands=List(CU.ctr(x4132(0)), CU.load(x4099)), op=FixLt, results=List(x4136))
      Stage(operands=List(x4134, x4136), op=BitAnd, results=List(x4144_x4137))
      Stage(operands=List(x4144_x4137, CU.load(x4141), Const(0)), op=Mux, results=List(CU.scalarOut(x4096_x4145_s)))
    }
    val x4148 = MemoryController(name="x4148",parent=x4151,offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x4096 = ScalarFIFO(size=1,name="data").wtPort(x4096_x4145_s)
      val x4095_b4230 = ScalarFIFO(size=1,name="size").wtPort(x4095_b4230_x4125_b4232_s)
      val x4095_b4229 = ScalarFIFO(size=1,name="offset").wtPort(x4095_b4229_x4125_b4231_s)
    }
    
  }
}
