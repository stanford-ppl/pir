import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SPMV_CRS extends PIRApp {
  def main(top:Top) = {
    val x3878_x3883_s = Scalar("x3878_x3883")
    val bus_415_s = Scalar("bus_415")
    val x3759_x3786_data_v = Vector("x3759_x3786_data")
    val x3960_b4104_x3988_b4106_s = Scalar("x3960_b4104_x3988_b4106")
    val x3757_b4077_x3782_b4085_s = Scalar("x3757_b4077_x3782_b4085")
    val x3742_x3748_s = Scalar("x3742_x3748")
    val bus_369_s = Scalar("bus_369")
    val bus_392_s = Scalar("bus_392")
    val x3757_b4078_x3782_b4086_s = Scalar("x3757_b4078_x3782_b4086")
    val values_dram_oc = OffChip("values_dram")
    val result_dram_oc = OffChip("result_dram")
    val bus_482_s = Scalar("bus_482")
    val x3819_x3846_data_v = Vector("x3819_x3846_data")
    val x3789_x3797_s = Scalar("x3789_x3797")
    val x3665_x3908_x3915_s = Scalar("x3665_x3908_x3915")
    val x3885_x3895_s = Scalar("x3885_x3895")
    val vec_dram_oc = OffChip("vec_dram")
    val x3897_x3914_s = Scalar("x3897_x3914")
    val cols_dram_da = DRAMAddress("cols_dram", "cols_dram")
    val x3962_x4009_v = Vector("x3962_x4009")
    val bus_397_s = Scalar("bus_397")
    val rowid_dram_oc = OffChip("rowid_dram")
    val x3849_x3857_s = Scalar("x3849_x3857")
    val vec_dram_da = DRAMAddress("vec_dram", "vec_dram")
    val x3673_x3680_s = Scalar("x3673_x3680")
    val x3847_x3853_s = Scalar("x3847_x3853")
    val x3818_b4093_x3844_b4101_s = Scalar("x3818_b4093_x3844_b4101")
    val x3961_x3989_s = Scalar("x3961_x3989")
    val bus_418_s = Scalar("bus_418")
    val bus_420_s = Scalar("bus_420")
    val x3667_x3937_x3945_s = Scalar("x3667_x3937_x3945")
    val x3664_x3744_x3749_s = Scalar("x3664_x3744_x3749")
    val bus_391_s = Scalar("bus_391")
    val x3682_b4064_x3706_b4072_s = Scalar("x3682_b4064_x3706_b4072")
    val x3964_x3990_s = Scalar("x3964_x3990")
    val x3683_b4068_x3708_b4076_s = Scalar("x3683_b4068_x3708_b4076")
    val x3966_x3992_s = Scalar("x3966_x3992")
    val x3817_b4091_x3842_b4099_s = Scalar("x3817_b4091_x3842_b4099")
    val x3664_x3746_x3749_s = Scalar("x3664_x3746_x3749")
    val x3758_b4079_x3784_b4087_s = Scalar("x3758_b4079_x3784_b4087")
    val x3713_x3720_s = Scalar("x3713_x3720")
    val x3668_x3948_s = Scalar("x3668_x3948")
    val bus_443_s = Scalar("bus_443")
    val x3666_x3936_x3945_s = Scalar("x3666_x3936_x3945")
    val bus_417_s = Scalar("bus_417")
    val x3711_x3716_s = Scalar("x3711_x3716")
    val x3788_x3795_s = Scalar("x3788_x3795")
    val bus_389_s = Scalar("bus_389")
    val x3817_b4090_x3842_b4098_s = Scalar("x3817_b4090_x3842_b4098")
    val x3672_x3679_s = Scalar("x3672_x3679")
    val x3818_b4094_x3844_b4102_s = Scalar("x3818_b4094_x3844_b4102")
    val x3712_x3718_s = Scalar("x3712_x3718")
    val bus_475_s = Scalar("bus_475")
    val result_dram_da = DRAMAddress("result_dram", "result_dram")
    val x3965_x3991_s = Scalar("x3965_x3991")
    val bus_449_s = Scalar("bus_449")
    val x3951_x3957_s = Scalar("x3951_x3957")
    val x3930_x3944_s = Scalar("x3930_x3944")
    val bus_481_s = Scalar("bus_481")
    val cols_dram_oc = OffChip("cols_dram")
    val bus_363_s = Scalar("bus_363")
    val x3787_x3793_s = Scalar("x3787_x3793")
    val x3898_x3916_data_v = Vector("x3898_x3916_data")
    val bus_480_s = Scalar("bus_480")
    val x3682_b4065_x3706_b4073_s = Scalar("x3682_b4065_x3706_b4073")
    val x3665_x3813_s = Scalar("x3665_x3813")
    val values_dram_da = DRAMAddress("values_dram", "values_dram")
    val x3758_b4081_x3784_b4089_s = Scalar("x3758_b4081_x3784_b4089")
    val rowid_dram_da = DRAMAddress("rowid_dram", "rowid_dram")
    val x3848_x3855_s = Scalar("x3848_x3855")
    val x3667_x3927_s = Scalar("x3667_x3927")
    val x3668_x4005_x4010_s = Scalar("x3668_x4005_x4010")
    val x3664_x3735_s = Scalar("x3664_x3735")
    val bus_423_s = Scalar("bus_423")
    val bus_394_s = Scalar("bus_394")
    val x3741_x3747_s = Scalar("x3741_x3747")
    val x3666_x3873_s = Scalar("x3666_x3873")
    val x3952_x3958_s = Scalar("x3952_x3958")
    val x3671_x3678_s = Scalar("x3671_x3678")
    val x3758_b4080_x3784_b4088_s = Scalar("x3758_b4080_x3784_b4088")
    val bus_364_s = Scalar("bus_364")
    val bus_366_s = Scalar("bus_366")
    val bus_361_s = Scalar("bus_361")
    val x3960_b4103_x3988_b4105_s = Scalar("x3960_b4103_x3988_b4105")
    val x3683_b4067_x3708_b4075_s = Scalar("x3683_b4067_x3708_b4075")
    val x3683_b4066_x3708_b4074_s = Scalar("x3683_b4066_x3708_b4074")
    val x3818_b4092_x3844_b4100_s = Scalar("x3818_b4092_x3844_b4100")
    val x3684_x3710_data_v = Vector("x3684_x3710_data")
    val x3750_x3755_s = Scalar("x3750_x3755")
    val bus_476_s = Scalar("bus_476")
    val x4027 = Sequential(name="x4027",parent=top) { implicit CU => 
      val x4027_unit = CounterChain(name = "x4027_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3664_dsp0 = MemoryPipeline(name="x3664_dsp0",parent="x4027") { implicit CU => 
      val x3735_x3735 = ScalarFIFO(size=1).wtPort(x3664_x3735_s)
      val x3711_x3725 = ScalarBuffer().wtPort(x3711_x3716_s)
      val x3724 = CounterChain.copy("x3736_0", "x3724")
      val x3740 = CounterChain.copy("x3950", "x3740")
      val x3664_x3746 = SRAM(size=495,banking = NoBanking()).wtPort(x3735_x3735.readPort).rdPort(x3664_x3746_x3749_s)
      WAStage(operands=List(CU.ctr(x3724(0)), CU.load(x3711_x3725)), op=FixSub, results=List(x3664_x3746.writeAddr))
      RAStage(operands=List(CU.ctr(x3740(0)), Const(1)), op=FixAdd, results=List(x3664_x3746.readAddr))
    }
    val x3664_dsp1 = MemoryPipeline(name="x3664_dsp1",parent="x4027") { implicit CU => 
      val x3735_x3735 = ScalarFIFO(size=1).wtPort(x3664_x3735_s)
      val x3711_x3725 = ScalarBuffer().wtPort(x3711_x3716_s)
      val x3724 = CounterChain.copy("x3736_0", "x3724")
      val x3740 = CounterChain.copy("x3950", "x3740")
      val x3664_x3744 = SRAM(size=495,banking = NoBanking()).wtPort(x3735_x3735.readPort).rdPort(x3664_x3744_x3749_s).rdAddr(x3740(0))
      WAStage(operands=List(CU.ctr(x3724(0)), CU.load(x3711_x3725)), op=FixSub, results=List(x3664_x3744.writeAddr))
    }
    val x3665_dsp0 = MemoryPipeline(name="x3665_dsp0",parent="x4027") { implicit CU => 
      val x3787_x3802 = ScalarBuffer().wtPort(x3787_x3793_s)
      val x3813_x3813 = ScalarFIFO(size=1).wtPort(x3665_x3813_s)
      val x3801 = CounterChain.copy("x3814_0", "x3801")
      val x3901 = CounterChain.copy("x3915_0", "x3901")
      val x3665_x3908 = SRAM(size=494,banking = NoBanking()).wtPort(x3813_x3813.readPort).rdPort(x3665_x3908_x3915_s).rdAddr(x3901(0))
      WAStage(operands=List(CU.ctr(x3801(0)), CU.load(x3787_x3802)), op=FixSub, results=List(x3665_x3908.writeAddr))
    }
    val x3666_dsp0 = MemoryPipeline(name="x3666_dsp0",parent="x4027") { implicit CU => 
      val x3847_x3862 = ScalarBuffer().wtPort(x3847_x3853_s)
      val x3873_x3873 = ScalarFIFO(size=1).wtPort(x3666_x3873_s)
      val x3861 = CounterChain.copy("x3874_0", "x3861")
      val x3933 = CounterChain.copy("x3945_0", "x3933")
      val x3666_x3936 = SRAM(size=494,banking = NoBanking()).wtPort(x3873_x3873.readPort).rdPort(x3666_x3936_x3945_s).rdAddr(x3933(0))
      WAStage(operands=List(CU.ctr(x3861(0)), CU.load(x3847_x3862)), op=FixSub, results=List(x3666_x3936.writeAddr))
    }
    val x3667_dsp0 = MemoryPipeline(name="x3667_dsp0",parent="x4027") { implicit CU => 
      val x3927_x3927 = ScalarFIFO(size=1).wtPort(x3667_x3927_s)
      val x3919 = CounterChain.copy("x3928_0", "x3919")
      val x3933 = CounterChain.copy("x3945_0", "x3933")
      val x3667_x3937 = SRAM(size=494,banking = NoBanking()).wtPort(x3927_x3927.readPort).rdPort(x3667_x3937_x3945_s).rdAddr(x3933(0)).wtAddr(x3919(0))
    }
    val x3668_dsp0 = MemoryPipeline(name="x3668_dsp0",parent="x4027") { implicit CU => 
      val x3964_x3997 = ScalarBuffer().wtPort(x3964_x3990_s)
      val x3948_x3948 = ScalarFIFO(size=1).wtPort(x3668_x3948_s)
      val x3740 = CounterChain.copy("x3950", "x3740")
      val x3996 = CounterChain.copy("x4010_0", "x3996")
      val x3668_x4005 = SRAM(size=494,banking = NoBanking()).wtPort(x3948_x3948.readPort).rdPort(x3668_x4005_x4010_s).wtAddr(x3740(0))
      RAStage(operands=List(CU.ctr(x3996(0)), CU.load(x3964_x3997)), op=FixSub, results=List(x3668_x4005.readAddr))
    }
    val x4026 = MetaPipeline(name="x4026",parent=x4027) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3670 = CounterChain(name = "x3670", ctr1).iter(1)
    }
    val x3681_0 = Pipeline(name="x3681_0",parent=x4026) { implicit CU => 
      val x3675 = CU.temp()
      val x3674 = CU.temp()
      val x3676 = CU.temp()
      val x3670 = CounterChain.copy("x4026", "x3670")
      val x3681_unit = CounterChain(name = "x3681_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3670(0)), Const(495)), op=FixMul, results=List(x3674, CU.scalarOut(x3671_x3678_s)))
      Stage(operands=List(CU.ctr(x3670(0)), Const(1)), op=FixAdd, results=List(x3675, CU.scalarOut(x3672_x3679_s)))
      Stage(operands=List(x3675, Const(495)), op=FixMul, results=List(x3676))
      Stage(operands=List(x3676, x3674), op=FixSub, results=List(CU.scalarOut(x3673_x3680_s)))
    }
    val x3738 = StreamController(name="x3738",parent=x4026) { implicit CU => 
      val x3738_unit = CounterChain(name = "x3738_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3709 = StreamController(name="x3709",parent=x3738) { implicit CU => 
      val x3709_unit = CounterChain(name = "x3709_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3709_0 = Pipeline(name="x3709_0",parent=x3709) { implicit CU => 
      val x3687 = CU.temp()
      val x3694 = CU.temp()
      val x3686 = CU.temp()
      val x3671_x3685 = ScalarBuffer().wtPort(x3671_x3678_s)
      val x3673_x3688 = ScalarBuffer().wtPort(x3673_x3680_s)
      Stage(operands=List(CU.load(x3671_x3685), Const(4)), op=FixMul, results=List(x3686, CU.scalarOut(bus_361_s)))
      Stage(operands=List(x3686, Const(16)), op=FixMod, results=List(x3687, CU.scalarOut(bus_363_s)))
      Stage(operands=List(x3687, Const(4)), op=FixDiv, results=List(x3694, CU.scalarOut(bus_364_s), CU.scalarOut(x3683_b4067_x3708_b4075_s)))
      Stage(operands=List(x3694, CU.load(x3673_x3688)), op=FixAdd, results=List(CU.scalarOut(x3683_b4068_x3708_b4076_s)))
      Stage(operands=List(CU.load(x3673_x3688), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_366_s)))
    }
    val x3709_1 = Pipeline(name="x3709_1",parent=x3709) { implicit CU => 
      val x3695 = CU.temp()
      val x3692 = CU.temp()
      val x3697 = CU.temp()
      val x3693 = CU.temp()
      val x3691 = CU.temp()
      val x3694 = ScalarFIFO(size=1).wtPort(bus_364_s)
      val x3689 = ScalarFIFO(size=1).wtPort(bus_366_s)
      val x3673_x3688 = ScalarBuffer().wtPort(x3673_x3680_s)
      val x3686 = ScalarFIFO(size=1).wtPort(bus_361_s)
      Stage(operands=List(CU.load(x3686), CU.load(x3689)), op=FixAdd, results=List(x3691))
      Stage(operands=List(x3691, Const(16)), op=FixMod, results=List(x3692))
      Stage(operands=List(Const(16), x3692), op=FixSub, results=List(x3693, CU.scalarOut(bus_369_s)))
      Stage(operands=List(x3693, Const(4)), op=FixDiv, results=List(x3695))
      Stage(operands=List(CU.load(x3673_x3688), CU.load(x3694)), op=FixAdd, results=List(x3697))
      Stage(operands=List(x3697, x3695), op=FixAdd, results=List(CU.scalarOut(x3683_b4066_x3708_b4074_s)))
    }
    val x3709_2 = Pipeline(name="x3709_2",parent=x3709) { implicit CU => 
      val x3690 = CU.temp()
      val x3699 = CU.temp()
      val x3686 = ScalarFIFO(size=1).wtPort(bus_361_s)
      val x3687 = ScalarFIFO(size=1).wtPort(bus_363_s)
      val x3689 = ScalarFIFO(size=1).wtPort(bus_366_s)
      val x3693 = ScalarFIFO(size=1).wtPort(bus_369_s)
      val x3702 = ScalarBuffer().wtPort(rowid_dram_da)
      Stage(operands=List(CU.load(x3689), CU.load(x3687)), op=FixAdd, results=List(x3699))
      Stage(operands=List(x3699, CU.load(x3693)), op=FixAdd, results=List(CU.scalarOut(x3682_b4065_x3706_b4073_s)))
      Stage(operands=List(CU.load(x3686), CU.load(x3687)), op=FixSub, results=List(x3690))
      Stage(operands=List(x3690, CU.load(x3702)), op=FixAdd, results=List(CU.scalarOut(x3682_b4064_x3706_b4072_s)))
    }
    val x3710 = MemoryController(name="x3710",parent=x3738,offchip=rowid_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x3682_b4065_x3710 = ScalarFIFO(name="size",size=1).wtPort(x3682_b4065_x3706_b4073_s)
      val x3682_b4064_x3710 = ScalarFIFO(name="offset",size=1).wtPort(x3682_b4064_x3706_b4072_s)
      CU.newVout("data", x3684_x3710_data_v)
    }
    val x3737 = Sequential(name="x3737",parent=x3738) { implicit CU => 
      val x3737_unit = CounterChain(name = "x3737_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3721_0 = Pipeline(name="x3721_0",parent=x3737) { implicit CU => 
      val x3683_b4067_x3714_b4070 = ScalarFIFO(size=16).wtPort(x3683_b4067_x3708_b4075_s)
      val x3683_b4066_x3714_b4069 = ScalarFIFO(size=16).wtPort(x3683_b4066_x3708_b4074_s)
      val x3683_b4068_x3714_b4071 = ScalarFIFO(size=16).wtPort(x3683_b4068_x3708_b4076_s)
      val x3721_unit = CounterChain(name = "x3721_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3683_b4067_x3714_b4070)), op=Bypass, results=List(CU.scalarOut(x3711_x3716_s)))
      Stage(operands=List(CU.load(x3683_b4068_x3714_b4071)), op=Bypass, results=List(CU.scalarOut(x3712_x3718_s)))
      Stage(operands=List(CU.load(x3683_b4066_x3714_b4069)), op=Bypass, results=List(CU.scalarOut(x3713_x3720_s)))
    }
    val x3736_0 = Pipeline(name="x3736_0",parent=x3737) { implicit CU => 
      val x3684_x3732 = VectorFIFO(size=1).wtPort(x3684_x3710_data_v)
      val x3713_x3722 = ScalarBuffer().wtPort(x3713_x3720_s)
      val x3712_x3727 = ScalarBuffer().wtPort(x3712_x3718_s)
      val x3711_x3725 = ScalarBuffer().wtPort(x3711_x3716_s)
      val ctr2 = Counter(min=Const(0), max=x3713_x3722.load, step=Const(1), par=1) // Counter
      val x3724 = CounterChain(name = "x3724", ctr2).iter(1)
      Stage(operands=List(CU.load(x3711_x3725), CU.ctr(x3724(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x3724(0)), CU.load(x3712_x3727)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x3684_x3732)), op=Bypass, results=List(CU.scalarOut(x3664_x3735_s)))
    }
    val x3950 = MetaPipeline(name="x3950",parent=x4026) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(494), step=Const(1), par=1) // Counter
      val x3740 = CounterChain(name = "x3740", ctr3).iter(494)
    }
    val x3749_0 = Pipeline(name="x3749_0",parent=x3950) { implicit CU => 
      val x3744_x3744 = ScalarFIFO(size=1).wtPort(x3664_x3744_x3749_s)
      val x3746_x3746 = ScalarFIFO(size=1).wtPort(x3664_x3746_x3749_s)
      val x3749_unit = CounterChain(name = "x3749_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3744_x3744)), op=Bypass, results=List(CU.scalarOut(x3741_x3747_s)))
      Stage(operands=List(CU.load(x3746_x3746)), op=Bypass, results=List(CU.scalarOut(x3742_x3748_s)))
    }
    val x3756_0 = Pipeline(name="x3756_0",parent=x3950) { implicit CU => 
      val x3742_x3751 = ScalarBuffer().wtPort(x3742_x3748_s)
      val x3741_x3752 = ScalarBuffer().wtPort(x3741_x3747_s)
      val x3756_unit = CounterChain(name = "x3756_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3742_x3751), CU.load(x3741_x3752)), op=FixSub, results=List(CU.scalarOut(x3750_x3755_s)))
    }
    val x3816 = StreamController(name="x3816",parent=x3950) { implicit CU => 
      val x3816_unit = CounterChain(name = "x3816_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3785 = StreamController(name="x3785",parent=x3816) { implicit CU => 
      val x3785_unit = CounterChain(name = "x3785_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3785_0 = Pipeline(name="x3785_0",parent=x3785) { implicit CU => 
      val x3769 = CU.temp()
      val x3762 = CU.temp()
      val x3761 = CU.temp()
      val x3750_x3763 = ScalarBuffer().wtPort(x3750_x3755_s)
      val x3741_x3760 = ScalarBuffer().wtPort(x3741_x3747_s)
      Stage(operands=List(CU.load(x3741_x3760), Const(4)), op=FixMul, results=List(x3761, CU.scalarOut(bus_389_s)))
      Stage(operands=List(x3761, Const(16)), op=FixMod, results=List(x3762, CU.scalarOut(bus_391_s)))
      Stage(operands=List(x3762, Const(4)), op=FixDiv, results=List(x3769, CU.scalarOut(bus_392_s), CU.scalarOut(x3758_b4080_x3784_b4088_s)))
      Stage(operands=List(x3769, CU.load(x3750_x3763)), op=FixAdd, results=List(CU.scalarOut(x3758_b4081_x3784_b4089_s)))
      Stage(operands=List(CU.load(x3750_x3763), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_394_s)))
    }
    val x3785_1 = Pipeline(name="x3785_1",parent=x3785) { implicit CU => 
      val x3768 = CU.temp()
      val x3766 = CU.temp()
      val x3772 = CU.temp()
      val x3770 = CU.temp()
      val x3767 = CU.temp()
      val x3769 = ScalarFIFO(size=1).wtPort(bus_392_s)
      val x3761 = ScalarFIFO(size=1).wtPort(bus_389_s)
      val x3764 = ScalarFIFO(size=1).wtPort(bus_394_s)
      val x3750_x3763 = ScalarBuffer().wtPort(x3750_x3755_s)
      Stage(operands=List(CU.load(x3761), CU.load(x3764)), op=FixAdd, results=List(x3766))
      Stage(operands=List(x3766, Const(16)), op=FixMod, results=List(x3767))
      Stage(operands=List(Const(16), x3767), op=FixSub, results=List(x3768, CU.scalarOut(bus_397_s)))
      Stage(operands=List(x3768, Const(4)), op=FixDiv, results=List(x3770))
      Stage(operands=List(CU.load(x3750_x3763), CU.load(x3769)), op=FixAdd, results=List(x3772))
      Stage(operands=List(x3772, x3770), op=FixAdd, results=List(CU.scalarOut(x3758_b4079_x3784_b4087_s)))
    }
    val x3785_2 = Pipeline(name="x3785_2",parent=x3785) { implicit CU => 
      val x3765 = CU.temp()
      val x3774 = CU.temp()
      val x3768 = ScalarFIFO(size=1).wtPort(bus_397_s)
      val x3762 = ScalarFIFO(size=1).wtPort(bus_391_s)
      val x3777 = ScalarBuffer().wtPort(cols_dram_da)
      val x3761 = ScalarFIFO(size=1).wtPort(bus_389_s)
      val x3764 = ScalarFIFO(size=1).wtPort(bus_394_s)
      Stage(operands=List(CU.load(x3764), CU.load(x3762)), op=FixAdd, results=List(x3774))
      Stage(operands=List(x3774, CU.load(x3768)), op=FixAdd, results=List(CU.scalarOut(x3757_b4078_x3782_b4086_s)))
      Stage(operands=List(CU.load(x3761), CU.load(x3762)), op=FixSub, results=List(x3765))
      Stage(operands=List(x3765, CU.load(x3777)), op=FixAdd, results=List(CU.scalarOut(x3757_b4077_x3782_b4085_s)))
    }
    val x3786 = MemoryController(name="x3786",parent=x3816,offchip=cols_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x3757_b4078_x3786 = ScalarFIFO(name="size",size=1).wtPort(x3757_b4078_x3782_b4086_s)
      val x3757_b4077_x3786 = ScalarFIFO(name="offset",size=1).wtPort(x3757_b4077_x3782_b4085_s)
      CU.newVout("data", x3759_x3786_data_v)
    }
    val x3815 = Sequential(name="x3815",parent=x3816) { implicit CU => 
      val x3815_unit = CounterChain(name = "x3815_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3798_0 = Pipeline(name="x3798_0",parent=x3815) { implicit CU => 
      val x3758_b4081_x3791_b4084 = ScalarFIFO(size=16).wtPort(x3758_b4081_x3784_b4089_s)
      val x3758_b4080_x3791_b4083 = ScalarFIFO(size=16).wtPort(x3758_b4080_x3784_b4088_s)
      val x3758_b4079_x3791_b4082 = ScalarFIFO(size=16).wtPort(x3758_b4079_x3784_b4087_s)
      val x3798_unit = CounterChain(name = "x3798_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3758_b4080_x3791_b4083)), op=Bypass, results=List(CU.scalarOut(x3787_x3793_s)))
      Stage(operands=List(CU.load(x3758_b4081_x3791_b4084)), op=Bypass, results=List(CU.scalarOut(x3788_x3795_s)))
      Stage(operands=List(CU.load(x3758_b4079_x3791_b4082)), op=Bypass, results=List(CU.scalarOut(x3789_x3797_s)))
    }
    val x3814_0 = Pipeline(name="x3814_0",parent=x3815) { implicit CU => 
      val x3788_x3804 = ScalarBuffer().wtPort(x3788_x3795_s)
      val x3787_x3802 = ScalarBuffer().wtPort(x3787_x3793_s)
      val x3789_x3799 = ScalarBuffer().wtPort(x3789_x3797_s)
      val x3759_x3810 = VectorFIFO(size=1).wtPort(x3759_x3786_data_v)
      val ctr4 = Counter(min=Const(0), max=x3789_x3799.load, step=Const(1), par=1) // Counter
      val x3801 = CounterChain(name = "x3801", ctr4).iter(1)
      Stage(operands=List(CU.load(x3787_x3802), CU.ctr(x3801(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x3801(0)), CU.load(x3788_x3804)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x3759_x3810)), op=Bypass, results=List(CU.scalarOut(x3665_x3813_s)))
    }
    val x3876 = StreamController(name="x3876",parent=x3950) { implicit CU => 
      val x3876_unit = CounterChain(name = "x3876_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3845 = StreamController(name="x3845",parent=x3876) { implicit CU => 
      val x3845_unit = CounterChain(name = "x3845_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3845_0 = Pipeline(name="x3845_0",parent=x3845) { implicit CU => 
      val x3829 = CU.temp()
      val x3822 = CU.temp()
      val x3821 = CU.temp()
      val x3750_x3823 = ScalarBuffer().wtPort(x3750_x3755_s)
      val x3741_x3820 = ScalarBuffer().wtPort(x3741_x3747_s)
      Stage(operands=List(CU.load(x3741_x3820), Const(4)), op=FixMul, results=List(x3821, CU.scalarOut(bus_415_s)))
      Stage(operands=List(x3821, Const(16)), op=FixMod, results=List(x3822, CU.scalarOut(bus_417_s)))
      Stage(operands=List(x3822, Const(4)), op=FixDiv, results=List(x3829, CU.scalarOut(bus_418_s), CU.scalarOut(x3818_b4093_x3844_b4101_s)))
      Stage(operands=List(x3829, CU.load(x3750_x3823)), op=FixAdd, results=List(CU.scalarOut(x3818_b4094_x3844_b4102_s)))
      Stage(operands=List(CU.load(x3750_x3823), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_420_s)))
    }
    val x3845_1 = Pipeline(name="x3845_1",parent=x3845) { implicit CU => 
      val x3832 = CU.temp()
      val x3827 = CU.temp()
      val x3830 = CU.temp()
      val x3826 = CU.temp()
      val x3828 = CU.temp()
      val x3824 = ScalarFIFO(size=1).wtPort(bus_420_s)
      val x3829 = ScalarFIFO(size=1).wtPort(bus_418_s)
      val x3821 = ScalarFIFO(size=1).wtPort(bus_415_s)
      val x3750_x3823 = ScalarBuffer().wtPort(x3750_x3755_s)
      Stage(operands=List(CU.load(x3821), CU.load(x3824)), op=FixAdd, results=List(x3826))
      Stage(operands=List(x3826, Const(16)), op=FixMod, results=List(x3827))
      Stage(operands=List(Const(16), x3827), op=FixSub, results=List(x3828, CU.scalarOut(bus_423_s)))
      Stage(operands=List(x3828, Const(4)), op=FixDiv, results=List(x3830))
      Stage(operands=List(CU.load(x3750_x3823), CU.load(x3829)), op=FixAdd, results=List(x3832))
      Stage(operands=List(x3832, x3830), op=FixAdd, results=List(CU.scalarOut(x3818_b4092_x3844_b4100_s)))
    }
    val x3845_2 = Pipeline(name="x3845_2",parent=x3845) { implicit CU => 
      val x3825 = CU.temp()
      val x3834 = CU.temp()
      val x3824 = ScalarFIFO(size=1).wtPort(bus_420_s)
      val x3822 = ScalarFIFO(size=1).wtPort(bus_417_s)
      val x3821 = ScalarFIFO(size=1).wtPort(bus_415_s)
      val x3837 = ScalarBuffer().wtPort(values_dram_da)
      val x3828 = ScalarFIFO(size=1).wtPort(bus_423_s)
      Stage(operands=List(CU.load(x3824), CU.load(x3822)), op=FixAdd, results=List(x3834))
      Stage(operands=List(x3834, CU.load(x3828)), op=FixAdd, results=List(CU.scalarOut(x3817_b4091_x3842_b4099_s)))
      Stage(operands=List(CU.load(x3821), CU.load(x3822)), op=FixSub, results=List(x3825))
      Stage(operands=List(x3825, CU.load(x3837)), op=FixAdd, results=List(CU.scalarOut(x3817_b4090_x3842_b4098_s)))
    }
    val x3846 = MemoryController(name="x3846",parent=x3876,offchip=values_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x3817_b4090_x3846 = ScalarFIFO(name="offset",size=1).wtPort(x3817_b4090_x3842_b4098_s)
      val x3817_b4091_x3846 = ScalarFIFO(name="size",size=1).wtPort(x3817_b4091_x3842_b4099_s)
      CU.newVout("data", x3819_x3846_data_v)
    }
    val x3875 = Sequential(name="x3875",parent=x3876) { implicit CU => 
      val x3875_unit = CounterChain(name = "x3875_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3858_0 = Pipeline(name="x3858_0",parent=x3875) { implicit CU => 
      val x3818_b4093_x3851_b4096 = ScalarFIFO(size=16).wtPort(x3818_b4093_x3844_b4101_s)
      val x3818_b4092_x3851_b4095 = ScalarFIFO(size=16).wtPort(x3818_b4092_x3844_b4100_s)
      val x3818_b4094_x3851_b4097 = ScalarFIFO(size=16).wtPort(x3818_b4094_x3844_b4102_s)
      val x3858_unit = CounterChain(name = "x3858_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3818_b4093_x3851_b4096)), op=Bypass, results=List(CU.scalarOut(x3847_x3853_s)))
      Stage(operands=List(CU.load(x3818_b4094_x3851_b4097)), op=Bypass, results=List(CU.scalarOut(x3848_x3855_s)))
      Stage(operands=List(CU.load(x3818_b4092_x3851_b4095)), op=Bypass, results=List(CU.scalarOut(x3849_x3857_s)))
    }
    val x3874_0 = Pipeline(name="x3874_0",parent=x3875) { implicit CU => 
      val x3848_x3864 = ScalarBuffer().wtPort(x3848_x3855_s)
      val x3847_x3862 = ScalarBuffer().wtPort(x3847_x3853_s)
      val x3849_x3859 = ScalarBuffer().wtPort(x3849_x3857_s)
      val x3819_x3870 = VectorFIFO(size=1).wtPort(x3819_x3846_data_v)
      val ctr5 = Counter(min=Const(0), max=x3849_x3859.load, step=Const(1), par=1) // Counter
      val x3861 = CounterChain(name = "x3861", ctr5).iter(1)
      Stage(operands=List(CU.load(x3847_x3862), CU.ctr(x3861(0))), op=FixLeq, results=List())
      Stage(operands=List(CU.ctr(x3861(0)), CU.load(x3848_x3864)), op=FixLt, results=List())
      Stage(operands=List(CU.load(x3819_x3870)), op=Bypass, results=List(CU.scalarOut(x3666_x3873_s)))
    }
    val x3884_0 = Pipeline(name="x3884_0",parent=x3950) { implicit CU => 
      val x3742_x3879 = ScalarBuffer().wtPort(x3742_x3748_s)
      val x3741_x3880 = ScalarBuffer().wtPort(x3741_x3747_s)
      val x3884_unit = CounterChain(name = "x3884_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3742_x3879), CU.load(x3741_x3880)), op=FixSub, results=List(CU.scalarOut(x3878_x3883_s)))
    }
    val x3896 = StreamController(name="x3896",parent=x3950) { implicit CU => 
      val x3896_unit = CounterChain(name = "x3896_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3896_0 = Pipeline(name="x3896_0",parent=x3896) { implicit CU => 
      val x3890 = CU.temp()
      val x3889 = CU.temp()
      val x3891 = CU.temp()
      val x3888 = CU.temp()
      val x3878_x3886 = ScalarBuffer().wtPort(x3878_x3883_s)
      Stage(operands=List(CU.load(x3878_x3886), Const(16)), op=FixLt, results=List(CU.scalarOut(bus_443_s)))
      Stage(operands=List(CU.load(x3878_x3886), Const(16)), op=FixMod, results=List(x3888))
      Stage(operands=List(x3888, Const(0)), op=FixEql, results=List(x3889))
      Stage(operands=List(CU.load(x3878_x3886), Const(16)), op=FixAdd, results=List(x3890))
      Stage(operands=List(x3890, x3888), op=FixSub, results=List(x3891))
      Stage(operands=List(x3889, CU.load(x3878_x3886), x3891), op=Mux, results=List(CU.scalarOut(bus_449_s)))
    }
    val x3896_1 = Pipeline(name="x3896_1",parent=x3896) { implicit CU => 
      val x3887 = ScalarFIFO(size=1).wtPort(bus_443_s)
      val x3892 = ScalarFIFO(size=1).wtPort(bus_449_s)
      Stage(operands=List(CU.load(x3887), Const(16), CU.load(x3892)), op=Mux, results=List(CU.scalarOut(x3885_x3895_s)))
    }
    val x3929 = StreamController(name="x3929",parent=x3950) { implicit CU => 
      val x3929_unit = CounterChain(name = "x3929_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3915_0 = Pipeline(name="x3915_0",parent=x3929) { implicit CU => 
      val x3910 = CU.temp()
      val x3903 = CU.temp()
      val x3912 = CU.temp()
      val x3908_x3908 = ScalarFIFO(size=1).wtPort(x3665_x3908_x3915_s)
      val x3878_x3902 = ScalarBuffer().wtPort(x3878_x3883_s)
      val x3904 = ScalarBuffer().wtPort(vec_dram_da)
      val x3885_x3899 = ScalarBuffer().wtPort(x3885_x3895_s)
      val ctr6 = Counter(min=Const(0), max=x3885_x3899.load, step=Const(1), par=1) // Counter
      val x3901 = CounterChain(name = "x3901", ctr6).iter(1)
      Stage(operands=List(CU.load(x3878_x3902), CU.ctr(x3901(0))), op=FixLeq, results=List(x3903))
      Stage(operands=List(CU.load(x3908_x3908), Const(4)), op=FixMul, results=List(x3910))
      Stage(operands=List(x3910, CU.load(x3904)), op=FixAdd, results=List(x3912))
      Stage(operands=List(x3903, CU.load(x3904), x3912), op=Mux, results=List(CU.scalarOut(x3897_x3914_s)))
    }
    val x3916 = MemoryController(name="x3916",parent=x3929,offchip=vec_dram_oc, mctpe=Gather) { implicit CU => 
      val x3897_x3916 = ScalarFIFO(name="addr",size=1).wtPort(x3897_x3914_s)
      CU.newVout("data", x3898_x3916_data_v)
    }
    val x3928_0 = Pipeline(name="x3928_0",parent=x3929) { implicit CU => 
      val x3898_x3922 = VectorFIFO(size=1).wtPort(x3898_x3916_data_v)
      val x3885_x3917 = ScalarBuffer().wtPort(x3885_x3895_s)
      val ctr7 = Counter(min=Const(0), max=x3885_x3917.load, step=Const(1), par=1) // Counter
      val x3919 = CounterChain(name = "x3919", ctr7).iter(1)
      Stage(operands=List(CU.load(x3898_x3922)), op=Bypass, results=List(CU.scalarOut(x3667_x3927_s)))
    }
    val x3945_0 = Pipeline(name="x3945_0",parent=x3950) { implicit CU => 
      val x3878_x3931 = ScalarBuffer().wtPort(x3878_x3883_s)
      val x3937_x3937 = ScalarFIFO(size=1).wtPort(x3667_x3937_x3945_s)
      val x3936_x3936 = ScalarFIFO(size=1).wtPort(x3666_x3936_x3945_s)
      val ctr8 = Counter(min=Const(0), max=x3878_x3931.load, step=Const(1), par=1) // Counter
      val x3933 = CounterChain(name = "x3933", ctr8).iter(1)
      Stage(operands=List(CU.load(x3936_x3936), CU.load(x3937_x3937)), op=FixMul, results=List(CU.reduce))
      val (_, rr467) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3945_0")
      Stage(operands=List(rr467), op=Bypass, results=List(CU.scalarOut(x3930_x3944_s)))
    }
    val x3949_0 = Pipeline(name="x3949_0",parent=x3950) { implicit CU => 
      val x3930_x3946 = ScalarBuffer().wtPort(x3930_x3944_s)
      val x3949_unit = CounterChain(name = "x3949_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x3930_x3946)), op=Bypass, results=List(CU.scalarOut(x3668_x3948_s)))
    }
    val x3959_0 = Pipeline(name="x3959_0",parent=x4026) { implicit CU => 
      val x3955 = CU.temp()
      val x3953 = CU.temp()
      val x3672_x3954 = ScalarBuffer().wtPort(x3672_x3679_s)
      val x3670 = CounterChain.copy("x4026", "x3670")
      val x3959_unit = CounterChain(name = "x3959_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3670(0)), Const(494)), op=FixMul, results=List(x3953, CU.scalarOut(x3951_x3957_s)))
      Stage(operands=List(CU.load(x3672_x3954), Const(494)), op=FixMul, results=List(x3955))
      Stage(operands=List(x3955, x3953), op=FixSub, results=List(CU.scalarOut(x3952_x3958_s)))
    }
    val x4025 = StreamController(name="x4025",parent=x4026) { implicit CU => 
      val x4025_unit = CounterChain(name = "x4025_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x4011 = Sequential(name="x4011",parent=x4025) { implicit CU => 
      val x4011_unit = CounterChain(name = "x4011_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3993 = StreamController(name="x3993",parent=x4011) { implicit CU => 
      val x3993_unit = CounterChain(name = "x3993_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3993_0 = Pipeline(name="x3993_0",parent=x3993) { implicit CU => 
      val x3975 = CU.temp()
      val x3974 = CU.temp()
      val x3971 = CU.temp()
      val x3973 = CU.temp()
      val x3968 = CU.temp()
      val x3952_x3970 = ScalarBuffer().wtPort(x3952_x3958_s)
      val x3951_x3967 = ScalarBuffer().wtPort(x3951_x3957_s)
      Stage(operands=List(CU.load(x3951_x3967), Const(4)), op=FixMul, results=List(x3968, CU.scalarOut(bus_475_s)))
      Stage(operands=List(CU.load(x3952_x3970), Const(4)), op=FixMul, results=List(x3971, CU.scalarOut(bus_476_s)))
      Stage(operands=List(x3968, x3971), op=FixAdd, results=List(x3973))
      Stage(operands=List(x3973, Const(16)), op=FixMod, results=List(x3974))
      Stage(operands=List(Const(16), x3974), op=FixSub, results=List(x3975, CU.scalarOut(bus_480_s)))
      Stage(operands=List(x3975, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_481_s)))
    }
    val x3993_1 = Pipeline(name="x3993_1",parent=x3993) { implicit CU => 
      val x3969 = CU.temp()
      val x3976 = CU.temp()
      val x3979 = CU.temp()
      val x3977 = ScalarFIFO(size=1).wtPort(bus_481_s)
      val x3952_x3970 = ScalarBuffer().wtPort(x3952_x3958_s)
      val x3968 = ScalarFIFO(size=1).wtPort(bus_475_s)
      Stage(operands=List(CU.load(x3968), Const(16)), op=FixMod, results=List(x3969, CU.scalarOut(bus_482_s)))
      Stage(operands=List(x3969, Const(4)), op=FixDiv, results=List(x3976, CU.scalarOut(x3964_x3990_s)))
      Stage(operands=List(CU.load(x3952_x3970), x3976), op=FixAdd, results=List(x3979))
      Stage(operands=List(x3979, CU.load(x3977)), op=FixAdd, results=List(CU.scalarOut(x3961_x3989_s), CU.scalarOut(x3966_x3992_s)))
      Stage(operands=List(x3976, CU.load(x3952_x3970)), op=FixAdd, results=List(CU.scalarOut(x3965_x3991_s)))
    }
    val x3993_2 = Pipeline(name="x3993_2",parent=x3993) { implicit CU => 
      val x3972 = CU.temp()
      val x3981 = CU.temp()
      val x3984 = ScalarBuffer().wtPort(result_dram_da)
      val x3975 = ScalarFIFO(size=1).wtPort(bus_480_s)
      val x3969 = ScalarFIFO(size=1).wtPort(bus_482_s)
      val x3971 = ScalarFIFO(size=1).wtPort(bus_476_s)
      val x3968 = ScalarFIFO(size=1).wtPort(bus_475_s)
      Stage(operands=List(CU.load(x3971), CU.load(x3969)), op=FixAdd, results=List(x3981))
      Stage(operands=List(x3981, CU.load(x3975)), op=FixAdd, results=List(CU.scalarOut(x3960_b4104_x3988_b4106_s)))
      Stage(operands=List(CU.load(x3968), CU.load(x3969)), op=FixSub, results=List(x3972))
      Stage(operands=List(x3972, CU.load(x3984)), op=FixAdd, results=List(CU.scalarOut(x3960_b4103_x3988_b4105_s)))
    }
    val x4010_0 = Pipeline(name="x4010_0",parent=x4011) { implicit CU => 
      val x4001 = CU.temp()
      val x3998 = CU.temp()
      val x4000 = CU.temp()
      val x3964_x3997 = ScalarBuffer().wtPort(x3964_x3990_s)
      val x4005_x4005 = ScalarFIFO(size=1).wtPort(x3668_x4005_x4010_s)
      val x3966_x3994 = ScalarBuffer().wtPort(x3966_x3992_s)
      val x3965_x3999 = ScalarBuffer().wtPort(x3965_x3991_s)
      val ctr9 = Counter(min=Const(0), max=x3966_x3994.load, step=Const(1), par=1) // Counter
      val x3996 = CounterChain(name = "x3996", ctr9).iter(1)
      Stage(operands=List(CU.load(x3964_x3997), CU.ctr(x3996(0))), op=FixLeq, results=List(x3998))
      Stage(operands=List(CU.ctr(x3996(0)), CU.load(x3965_x3999)), op=FixLt, results=List(x4000))
      Stage(operands=List(x3998, x4000), op=BitAnd, results=List(x4001))
      Stage(operands=List(x4001, CU.load(x4005_x4005), Const(0)), op=Mux, results=List(CU.vecOut(x3962_x4009_v)))
    }
    val x4012 = MemoryController(name="x4012",parent=x4025,offchip=result_dram_oc, mctpe=TileStore) { implicit CU => 
      val x3962_x4012 = VectorFIFO(name="data",size=1).wtPort(x3962_x4009_v)
      val x3960_b4104_x4012 = ScalarFIFO(name="size",size=1).wtPort(x3960_b4104_x3988_b4106_s)
      val x3960_b4103_x4012 = ScalarFIFO(name="offset",size=1).wtPort(x3960_b4103_x3988_b4105_s)
    }
    val x4024 = Sequential(name="x4024",parent=x4025) { implicit CU => 
      val x4024_unit = CounterChain(name = "x4024_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    
  }
}
