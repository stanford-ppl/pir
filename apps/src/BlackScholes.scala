import pir.graph
import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object BlackScholes extends PIRApp {
  def main(top:Top) = {
    val x3860_argin = ArgIn("x3860")
    val bus_581_v = Vector("bus_581")
    val bus_551_v = Vector("bus_551")
    val bus_614_v = Vector("bus_614")
    val x3658_x3999_x4091_v = Vector("x3658_x3999_x4091")
    val bus_706_v = Vector("bus_706")
    val bus_590_v = Vector("bus_590")
    val x3877_b4206_x3885_b4208_s = Scalar("x3877_b4206_x3885_b4208")
    val x3803_argin = ArgIn("x3803")
    val x3653_x3906_x3994_v = Vector("x3653_x3906_x3994")
    val x3744_x3753_data_v = Vector("x3744_x3753_data")
    val x3633_oc = OffChip("x3633")
    val x3656_x3998_x4091_v = Vector("x3656_x3998_x4091")
    val bus_593_v = Vector("bus_593")
    val bus_608_v = Vector("bus_608")
    val x4124_argin = ArgIn("x4124")
    val x3839_b4199_x3847_b4201_s = Scalar("x3839_b4199_x3847_b4201")
    val x3859_x3868_data_v = Vector("x3859_x3868_data")
    val bus_664_v = Vector("bus_664")
    val x3726_argin = ArgIn("x3726")
    val x3655_x3901_x3994_v = Vector("x3655_x3901_x3994")
    val x3841_argin = ArgIn("x3841")
    val bus_592_v = Vector("bus_592")
    val x3725_x3734_data_v = Vector("x3725_x3734_data")
    val bus_583_v = Vector("bus_583")
    val bus_704_v = Vector("bus_704")
    val x4121_b4215_x4130_b4217_s = Scalar("x4121_b4215_x4130_b4217")
    val bus_648_v = Vector("bus_648")
    val bus_626_v = Vector("bus_626")
    val bus_643_v = Vector("bus_643")
    val bus_574_v = Vector("bus_574")
    val x3858_b4202_x3866_b4204_s = Scalar("x3858_b4202_x3866_b4204")
    val x3822_argin = ArgIn("x3822")
    val x3724_b4175_x3732_b4177_s = Scalar("x3724_b4175_x3732_b4177")
    val x3662_x4001_x4091_v = Vector("x3662_x4001_x4091")
    val bus_582_v = Vector("bus_582")
    val x3782_b4186_x3790_b4188_s = Scalar("x3782_b4186_x3790_b4188")
    val bus_544_v = Vector("bus_544")
    val x3688_argin = ArgIn("x3688")
    val x3639_oc = OffChip("x3639")
    val x3877_b4207_x3885_b4209_s = Scalar("x3877_b4207_x3885_b4209")
    val bus_695_v = Vector("bus_695")
    val x3667_b4163_x3675_b4165_s = Scalar("x3667_b4163_x3675_b4165")
    val x3665_x3993_v = Vector("x3665_x3993")
    val bus_603_v = Vector("bus_603")
    val x3763_x3772_data_v = Vector("x3763_x3772_data")
    val x3802_x3811_data_v = Vector("x3802_x3811_data")
    val bus_596_v = Vector("bus_596")
    val bus_634_v = Vector("bus_634")
    val bus_566_v = Vector("bus_566")
    val x3705_b4171_x3713_b4173_s = Scalar("x3705_b4171_x3713_b4173")
    val x3743_b4178_x3751_b4180_s = Scalar("x3743_b4178_x3751_b4180")
    val x3705_b4170_x3713_b4172_s = Scalar("x3705_b4170_x3713_b4172")
    val x3745_argin = ArgIn("x3745")
    val bus_647_v = Vector("bus_647")
    val bus_555_v = Vector("bus_555")
    val x3663_x3905_x3994_v = Vector("x3663_x3905_x3994")
    val x3664_x4002_x4091_v = Vector("x3664_x4002_x4091")
    val bus_672_v = Vector("bus_672")
    val x3821_x3830_data_v = Vector("x3821_x3830_data")
    val x3839_b4198_x3847_b4200_s = Scalar("x3839_b4198_x3847_b4200")
    val x3661_x3904_x3994_v = Vector("x3661_x3904_x3994")
    val bus_675_v = Vector("bus_675")
    val bus_631_v = Vector("bus_631")
    val x3782_b4187_x3790_b4189_s = Scalar("x3782_b4187_x3790_b4189")
    val x3660_x4000_x4091_v = Vector("x3660_x4000_x4091")
    val x4096_argin = ArgIn("x4096")
    val x3801_b4191_x3809_b4193_s = Scalar("x3801_b4191_x3809_b4193")
    val x3686_b4167_x3694_b4169_s = Scalar("x3686_b4167_x3694_b4169")
    val bus_601_v = Vector("bus_601")
    val bus_597_v = Vector("bus_597")
    val bus_541_v = Vector("bus_541")
    val x3643_oc = OffChip("x3643")
    val bus_537_v = Vector("bus_537")
    val x4093_b4211_x4102_b4213_s = Scalar("x4093_b4211_x4102_b4213")
    val x3764_argin = ArgIn("x3764")
    val bus_637_v = Vector("bus_637")
    val bus_652_v = Vector("bus_652")
    val bus_679_v = Vector("bus_679")
    val x3666_x4090_v = Vector("x3666_x4090")
    val bus_612_v = Vector("bus_612")
    val bus_559_v = Vector("bus_559")
    val x3667_b4162_x3675_b4164_s = Scalar("x3667_b4162_x3675_b4164")
    val bus_621_v = Vector("bus_621")
    val x3762_b4183_x3770_b4185_s = Scalar("x3762_b4183_x3770_b4185")
    val x3706_x3715_data_v = Vector("x3706_x3715_data")
    val bus_683_v = Vector("bus_683")
    val x4121_b4214_x4130_b4216_s = Scalar("x4121_b4214_x4130_b4216")
    val bus_534_v = Vector("bus_534")
    val x3657_x3902_x3994_v = Vector("x3657_x3902_x3994")
    val x3878_x3887_data_v = Vector("x3878_x3887_data")
    val bus_682_v = Vector("bus_682")
    val bus_633_v = Vector("bus_633")
    val x3858_b4203_x3866_b4205_s = Scalar("x3858_b4203_x3866_b4205")
    val bus_636_v = Vector("bus_636")
    val x3743_b4179_x3751_b4181_s = Scalar("x3743_b4179_x3751_b4181")
    val bus_709_v = Vector("bus_709")
    val bus_564_v = Vector("bus_564")
    val bus_674_v = Vector("bus_674")
    val bus_580_v = Vector("bus_580")
    val bus_656_v = Vector("bus_656")
    val bus_539_v = Vector("bus_539")
    val bus_713_v = Vector("bus_713")
    val x3659_x3903_x3994_v = Vector("x3659_x3903_x3994")
    val bus_629_v = Vector("bus_629")
    val bus_693_v = Vector("bus_693")
    val x3801_b4190_x3809_b4192_s = Scalar("x3801_b4190_x3809_b4192")
    val x3665_x4107_x4111_v = Vector("x3665_x4107_x4111")
    val bus_666_v = Vector("bus_666")
    val bus_700_v = Vector("bus_700")
    val x3668_x3677_data_v = Vector("x3668_x3677_data")
    val x3641_oc = OffChip("x3641")
    val bus_545_v = Vector("bus_545")
    val bus_556_v = Vector("bus_556")
    val x3707_argin = ArgIn("x3707")
    val bus_684_v = Vector("bus_684")
    val x3724_b4174_x3732_b4176_s = Scalar("x3724_b4174_x3732_b4176")
    val bus_542_v = Vector("bus_542")
    val x3879_argin = ArgIn("x3879")
    val bus_689_v = Vector("bus_689")
    val x3635_oc = OffChip("x3635")
    val bus_658_v = Vector("bus_658")
    val bus_572_v = Vector("bus_572")
    val x3686_b4166_x3694_b4168_s = Scalar("x3686_b4166_x3694_b4168")
    val bus_688_v = Vector("bus_688")
    val bus_558_v = Vector("bus_558")
    val bus_685_v = Vector("bus_685")
    val x3687_x3696_data_v = Vector("x3687_x3696_data")
    val x3637_oc = OffChip("x3637")
    val bus_587_v = Vector("bus_587")
    val x3654_x4003_x4091_v = Vector("x3654_x4003_x4091")
    val x4093_b4210_x4102_b4212_s = Scalar("x4093_b4210_x4102_b4212")
    val x3784_argin = ArgIn("x3784")
    val x3627_argin = ArgIn("x3627")
    val x3631_oc = OffChip("x3631")
    val bus_560_v = Vector("bus_560")
    val x3840_x3849_data_v = Vector("x3840_x3849_data")
    val bus_632_v = Vector("bus_632")
    val bus_617_v = Vector("bus_617")
    val x3666_x4135_x4139_v = Vector("x3666_x4135_x4139")
    val x3783_x3792_data_v = Vector("x3783_x3792_data")
    val x3820_b4194_x3828_b4196_s = Scalar("x3820_b4194_x3828_b4196")
    val bus_591_v = Vector("bus_591")
    val x3820_b4195_x3828_b4197_s = Scalar("x3820_b4195_x3828_b4197")
    val x3762_b4182_x3770_b4184_s = Scalar("x3762_b4182_x3770_b4184")
    val bus_651_v = Vector("bus_651")
    val bus_673_v = Vector("bus_673")
    val bus_540_v = Vector("bus_540")
    val bus_650_v = Vector("bus_650")
    val x3669_argin = ArgIn("x3669")
    val x4151 = Sequential(name="x4151",parent=top) { implicit CU => 
    }
    val x4150 = MetaPipeline(name="x4150",parent=x4151) { implicit CU => 
      val x3627_x3650 =  ScalarBuffer().wtPort(x3627_argin)
      val ctr1 = Counter(min=Const(0), max=x3627_x3650.load, step=Const(2000), par=2) // Counter
      val x3652 = CounterChain(name = "x3652", ctr1).iter(2499)
    }
    val x3653_dsp0 = MemoryPipeline(name="x3653_dsp0",parent="x4150") { implicit CU => 
      val x3683_x3683 =  VectorFIFO(size=1).wtPort(x3668_x3677_data_v)
      val x3679 = CounterChain.copy("x3684", "x3679")
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x3653_x3906 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3683_x3683.readPort).rdPort(x3653_x3906_x3994_v).rdAddr(x3899(0)).wtAddr(x3679(0))
      var stage: List[Stage] = Nil
    }
    val x3654_dsp0 = MemoryPipeline(name="x3654_dsp0",parent="x4150") { implicit CU => 
      val x3798_x3798 =  VectorFIFO(size=1).wtPort(x3783_x3792_data_v)
      val x3794 = CounterChain.copy("x3799", "x3794")
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x3654_x4003 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3798_x3798.readPort).rdPort(x3654_x4003_x4091_v).rdAddr(x3996(0)).wtAddr(x3794(0))
      var stage: List[Stage] = Nil
    }
    val x3655_dsp0 = MemoryPipeline(name="x3655_dsp0",parent="x4150") { implicit CU => 
      val x3702_x3702 =  VectorFIFO(size=1).wtPort(x3687_x3696_data_v)
      val x3698 = CounterChain.copy("x3703", "x3698")
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x3655_x3901 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3702_x3702.readPort).rdPort(x3655_x3901_x3994_v).rdAddr(x3899(0)).wtAddr(x3698(0))
      var stage: List[Stage] = Nil
    }
    val x3656_dsp0 = MemoryPipeline(name="x3656_dsp0",parent="x4150") { implicit CU => 
      val x3817_x3817 =  VectorFIFO(size=1).wtPort(x3802_x3811_data_v)
      val x3813 = CounterChain.copy("x3818", "x3813")
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x3656_x3998 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3817_x3817.readPort).rdPort(x3656_x3998_x4091_v).rdAddr(x3996(0)).wtAddr(x3813(0))
      var stage: List[Stage] = Nil
    }
    val x3657_dsp0 = MemoryPipeline(name="x3657_dsp0",parent="x4150") { implicit CU => 
      val x3721_x3721 =  VectorFIFO(size=1).wtPort(x3706_x3715_data_v)
      val x3717 = CounterChain.copy("x3722", "x3717")
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x3657_x3902 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3721_x3721.readPort).rdPort(x3657_x3902_x3994_v).rdAddr(x3899(0)).wtAddr(x3717(0))
      var stage: List[Stage] = Nil
    }
    val x3658_dsp0 = MemoryPipeline(name="x3658_dsp0",parent="x4150") { implicit CU => 
      val x3836_x3836 =  VectorFIFO(size=1).wtPort(x3821_x3830_data_v)
      val x3832 = CounterChain.copy("x3837", "x3832")
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x3658_x3999 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3836_x3836.readPort).rdPort(x3658_x3999_x4091_v).rdAddr(x3996(0)).wtAddr(x3832(0))
      var stage: List[Stage] = Nil
    }
    val x3659_dsp0 = MemoryPipeline(name="x3659_dsp0",parent="x4150") { implicit CU => 
      val x3740_x3740 =  VectorFIFO(size=1).wtPort(x3725_x3734_data_v)
      val x3736 = CounterChain.copy("x3741", "x3736")
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x3659_x3903 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3740_x3740.readPort).rdPort(x3659_x3903_x3994_v).rdAddr(x3899(0)).wtAddr(x3736(0))
      var stage: List[Stage] = Nil
    }
    val x3660_dsp0 = MemoryPipeline(name="x3660_dsp0",parent="x4150") { implicit CU => 
      val x3855_x3855 =  VectorFIFO(size=1).wtPort(x3840_x3849_data_v)
      val x3851 = CounterChain.copy("x3856", "x3851")
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x3660_x4000 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3855_x3855.readPort).rdPort(x3660_x4000_x4091_v).rdAddr(x3996(0)).wtAddr(x3851(0))
      var stage: List[Stage] = Nil
    }
    val x3661_dsp0 = MemoryPipeline(name="x3661_dsp0",parent="x4150") { implicit CU => 
      val x3759_x3759 =  VectorFIFO(size=1).wtPort(x3744_x3753_data_v)
      val x3755 = CounterChain.copy("x3760", "x3755")
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x3661_x3904 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3759_x3759.readPort).rdPort(x3661_x3904_x3994_v).rdAddr(x3899(0)).wtAddr(x3755(0))
      var stage: List[Stage] = Nil
    }
    val x3662_dsp0 = MemoryPipeline(name="x3662_dsp0",parent="x4150") { implicit CU => 
      val x3874_x3874 =  VectorFIFO(size=1).wtPort(x3859_x3868_data_v)
      val x3870 = CounterChain.copy("x3875", "x3870")
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x3662_x4001 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3874_x3874.readPort).rdPort(x3662_x4001_x4091_v).rdAddr(x3996(0)).wtAddr(x3870(0))
      var stage: List[Stage] = Nil
    }
    val x3663_dsp0 = MemoryPipeline(name="x3663_dsp0",parent="x4150") { implicit CU => 
      val x3778_x3778 =  VectorFIFO(size=1).wtPort(x3763_x3772_data_v)
      val x3774 = CounterChain.copy("x3779", "x3774")
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x3663_x3905 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3778_x3778.readPort).rdPort(x3663_x3905_x3994_v).rdAddr(x3899(0)).wtAddr(x3774(0))
      var stage: List[Stage] = Nil
    }
    val x3664_dsp0 = MemoryPipeline(name="x3664_dsp0",parent="x4150") { implicit CU => 
      val x3893_x3893 =  VectorFIFO(size=1).wtPort(x3878_x3887_data_v)
      val x3889 = CounterChain.copy("x3894", "x3889")
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x3664_x4002 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3893_x3893.readPort).rdPort(x3664_x4002_x4091_v).rdAddr(x3996(0)).wtAddr(x3889(0))
      var stage: List[Stage] = Nil
    }
    val x3665_dsp0 = MemoryPipeline(name="x3665_dsp0",parent="x4150") { implicit CU => 
      val x3993_x3993 =  VectorFIFO(size=1).wtPort(x3665_x3993_v)
      val x3899 = CounterChain.copy("x3994", "x3899")
      val x4105 = CounterChain.copy("x4111", "x4105")
      val x3665_x4107 =  SRAM(size=2000,banking = Strided(1)).wtPort(x3993_x3993.readPort).rdPort(x3665_x4107_x4111_v).rdAddr(x4105(0)).wtAddr(x3899(0))
      var stage: List[Stage] = Nil
    }
    val x3666_dsp0 = MemoryPipeline(name="x3666_dsp0",parent="x4150") { implicit CU => 
      val x4090_x4090 =  VectorFIFO(size=1).wtPort(x3666_x4090_v)
      val x3996 = CounterChain.copy("x4091", "x3996")
      val x4133 = CounterChain.copy("x4139", "x4133")
      val x3666_x4135 =  SRAM(size=2000,banking = Strided(1)).wtPort(x4090_x4090.readPort).rdPort(x3666_x4135_x4139_v).rdAddr(x4133(0)).wtAddr(x3996(0))
      var stage: List[Stage] = Nil
    }
    val x3685 = StreamController(name="x3685",parent=x4150) { implicit CU => 
    }
    val x3676_0 = Pipeline(name="x3676_0",parent=x3685) { implicit CU => 
      val x3670 = CU.temp()
      val x3669 =  ScalarBuffer().wtPort(x3669_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3670))
      Stage(operands=List(x3670, CU.load(x3669)), op=FixAdd, results=List(CU.scalarOut(x3667_b4162_x3675_b4164_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3667_b4163_x3675_b4165_s)))
    }
    val x3677 = MemoryController(name="x3677",parent=x3685,offchip=x3631_oc, mctpe=TileLoad) { implicit CU => 
      val x3667_b4162_x3677 =  ScalarFIFO(name="offset",size=1).wtPort(x3667_b4162_x3675_b4164_s)
      val x3667_b4163_x3677 =  ScalarFIFO(name="size",size=1).wtPort(x3667_b4163_x3675_b4165_s)
      CU.newVout("data", x3668_x3677_data_v)
    }
    val x3684 = Pipeline(name="x3684",parent=x3685) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3679 = CounterChain(name = "x3679", ctr2).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3704 = StreamController(name="x3704",parent=x4150) { implicit CU => 
    }
    val x3695_0 = Pipeline(name="x3695_0",parent=x3704) { implicit CU => 
      val x3689 = CU.temp()
      val x3688 =  ScalarBuffer().wtPort(x3688_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3689))
      Stage(operands=List(x3689, CU.load(x3688)), op=FixAdd, results=List(CU.scalarOut(x3686_b4166_x3694_b4168_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3686_b4167_x3694_b4169_s)))
    }
    val x3696 = MemoryController(name="x3696",parent=x3704,offchip=x3633_oc, mctpe=TileLoad) { implicit CU => 
      val x3686_b4167_x3696 =  ScalarFIFO(name="size",size=1).wtPort(x3686_b4167_x3694_b4169_s)
      val x3686_b4166_x3696 =  ScalarFIFO(name="offset",size=1).wtPort(x3686_b4166_x3694_b4168_s)
      CU.newVout("data", x3687_x3696_data_v)
    }
    val x3703 = Pipeline(name="x3703",parent=x3704) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3698 = CounterChain(name = "x3698", ctr3).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3723 = StreamController(name="x3723",parent=x4150) { implicit CU => 
    }
    val x3714_0 = Pipeline(name="x3714_0",parent=x3723) { implicit CU => 
      val x3708 = CU.temp()
      val x3707 =  ScalarBuffer().wtPort(x3707_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3708))
      Stage(operands=List(x3708, CU.load(x3707)), op=FixAdd, results=List(CU.scalarOut(x3705_b4170_x3713_b4172_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3705_b4171_x3713_b4173_s)))
    }
    val x3715 = MemoryController(name="x3715",parent=x3723,offchip=x3635_oc, mctpe=TileLoad) { implicit CU => 
      val x3705_b4171_x3715 =  ScalarFIFO(name="size",size=1).wtPort(x3705_b4171_x3713_b4173_s)
      val x3705_b4170_x3715 =  ScalarFIFO(name="offset",size=1).wtPort(x3705_b4170_x3713_b4172_s)
      CU.newVout("data", x3706_x3715_data_v)
    }
    val x3722 = Pipeline(name="x3722",parent=x3723) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3717 = CounterChain(name = "x3717", ctr4).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3742 = StreamController(name="x3742",parent=x4150) { implicit CU => 
    }
    val x3733_0 = Pipeline(name="x3733_0",parent=x3742) { implicit CU => 
      val x3727 = CU.temp()
      val x3726 =  ScalarBuffer().wtPort(x3726_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3727))
      Stage(operands=List(x3727, CU.load(x3726)), op=FixAdd, results=List(CU.scalarOut(x3724_b4174_x3732_b4176_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3724_b4175_x3732_b4177_s)))
    }
    val x3734 = MemoryController(name="x3734",parent=x3742,offchip=x3637_oc, mctpe=TileLoad) { implicit CU => 
      val x3724_b4174_x3734 =  ScalarFIFO(name="offset",size=1).wtPort(x3724_b4174_x3732_b4176_s)
      val x3724_b4175_x3734 =  ScalarFIFO(name="size",size=1).wtPort(x3724_b4175_x3732_b4177_s)
      CU.newVout("data", x3725_x3734_data_v)
    }
    val x3741 = Pipeline(name="x3741",parent=x3742) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3736 = CounterChain(name = "x3736", ctr5).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3761 = StreamController(name="x3761",parent=x4150) { implicit CU => 
    }
    val x3752_0 = Pipeline(name="x3752_0",parent=x3761) { implicit CU => 
      val x3746 = CU.temp()
      val x3745 =  ScalarBuffer().wtPort(x3745_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3746))
      Stage(operands=List(x3746, CU.load(x3745)), op=FixAdd, results=List(CU.scalarOut(x3743_b4178_x3751_b4180_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3743_b4179_x3751_b4181_s)))
    }
    val x3753 = MemoryController(name="x3753",parent=x3761,offchip=x3639_oc, mctpe=TileLoad) { implicit CU => 
      val x3743_b4179_x3753 =  ScalarFIFO(name="size",size=1).wtPort(x3743_b4179_x3751_b4181_s)
      val x3743_b4178_x3753 =  ScalarFIFO(name="offset",size=1).wtPort(x3743_b4178_x3751_b4180_s)
      CU.newVout("data", x3744_x3753_data_v)
    }
    val x3760 = Pipeline(name="x3760",parent=x3761) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3755 = CounterChain(name = "x3755", ctr6).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3780 = StreamController(name="x3780",parent=x4150) { implicit CU => 
    }
    val x3771_0 = Pipeline(name="x3771_0",parent=x3780) { implicit CU => 
      val x3765 = CU.temp()
      val x3764 =  ScalarBuffer().wtPort(x3764_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3765))
      Stage(operands=List(x3765, CU.load(x3764)), op=FixAdd, results=List(CU.scalarOut(x3762_b4182_x3770_b4184_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3762_b4183_x3770_b4185_s)))
    }
    val x3772 = MemoryController(name="x3772",parent=x3780,offchip=x3641_oc, mctpe=TileLoad) { implicit CU => 
      val x3762_b4183_x3772 =  ScalarFIFO(name="size",size=1).wtPort(x3762_b4183_x3770_b4185_s)
      val x3762_b4182_x3772 =  ScalarFIFO(name="offset",size=1).wtPort(x3762_b4182_x3770_b4184_s)
      CU.newVout("data", x3763_x3772_data_v)
    }
    val x3779 = Pipeline(name="x3779",parent=x3780) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3774 = CounterChain(name = "x3774", ctr7).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3800 = StreamController(name="x3800",parent=x4150) { implicit CU => 
    }
    val x3791_0 = Pipeline(name="x3791_0",parent=x3800) { implicit CU => 
      val x3785 = CU.temp()
      val x3784 =  ScalarBuffer().wtPort(x3784_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3785))
      Stage(operands=List(x3785, CU.load(x3784)), op=FixAdd, results=List(CU.scalarOut(x3782_b4186_x3790_b4188_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3782_b4187_x3790_b4189_s)))
    }
    val x3792 = MemoryController(name="x3792",parent=x3800,offchip=x3631_oc, mctpe=TileLoad) { implicit CU => 
      val x3782_b4186_x3792 =  ScalarFIFO(name="offset",size=1).wtPort(x3782_b4186_x3790_b4188_s)
      val x3782_b4187_x3792 =  ScalarFIFO(name="size",size=1).wtPort(x3782_b4187_x3790_b4189_s)
      CU.newVout("data", x3783_x3792_data_v)
    }
    val x3799 = Pipeline(name="x3799",parent=x3800) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3794 = CounterChain(name = "x3794", ctr8).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3819 = StreamController(name="x3819",parent=x4150) { implicit CU => 
    }
    val x3810_0 = Pipeline(name="x3810_0",parent=x3819) { implicit CU => 
      val x3804 = CU.temp()
      val x3803 =  ScalarBuffer().wtPort(x3803_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3804))
      Stage(operands=List(x3804, CU.load(x3803)), op=FixAdd, results=List(CU.scalarOut(x3801_b4190_x3809_b4192_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3801_b4191_x3809_b4193_s)))
    }
    val x3811 = MemoryController(name="x3811",parent=x3819,offchip=x3633_oc, mctpe=TileLoad) { implicit CU => 
      val x3801_b4191_x3811 =  ScalarFIFO(name="size",size=1).wtPort(x3801_b4191_x3809_b4193_s)
      val x3801_b4190_x3811 =  ScalarFIFO(name="offset",size=1).wtPort(x3801_b4190_x3809_b4192_s)
      CU.newVout("data", x3802_x3811_data_v)
    }
    val x3818 = Pipeline(name="x3818",parent=x3819) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3813 = CounterChain(name = "x3813", ctr9).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3838 = StreamController(name="x3838",parent=x4150) { implicit CU => 
    }
    val x3829_0 = Pipeline(name="x3829_0",parent=x3838) { implicit CU => 
      val x3823 = CU.temp()
      val x3822 =  ScalarBuffer().wtPort(x3822_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3823))
      Stage(operands=List(x3823, CU.load(x3822)), op=FixAdd, results=List(CU.scalarOut(x3820_b4194_x3828_b4196_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3820_b4195_x3828_b4197_s)))
    }
    val x3830 = MemoryController(name="x3830",parent=x3838,offchip=x3635_oc, mctpe=TileLoad) { implicit CU => 
      val x3820_b4195_x3830 =  ScalarFIFO(name="size",size=1).wtPort(x3820_b4195_x3828_b4197_s)
      val x3820_b4194_x3830 =  ScalarFIFO(name="offset",size=1).wtPort(x3820_b4194_x3828_b4196_s)
      CU.newVout("data", x3821_x3830_data_v)
    }
    val x3837 = Pipeline(name="x3837",parent=x3838) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3832 = CounterChain(name = "x3832", ctr10).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3857 = StreamController(name="x3857",parent=x4150) { implicit CU => 
    }
    val x3848_0 = Pipeline(name="x3848_0",parent=x3857) { implicit CU => 
      val x3842 = CU.temp()
      val x3841 =  ScalarBuffer().wtPort(x3841_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3842))
      Stage(operands=List(x3842, CU.load(x3841)), op=FixAdd, results=List(CU.scalarOut(x3839_b4198_x3847_b4200_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3839_b4199_x3847_b4201_s)))
    }
    val x3849 = MemoryController(name="x3849",parent=x3857,offchip=x3637_oc, mctpe=TileLoad) { implicit CU => 
      val x3839_b4198_x3849 =  ScalarFIFO(name="offset",size=1).wtPort(x3839_b4198_x3847_b4200_s)
      val x3839_b4199_x3849 =  ScalarFIFO(name="size",size=1).wtPort(x3839_b4199_x3847_b4201_s)
      CU.newVout("data", x3840_x3849_data_v)
    }
    val x3856 = Pipeline(name="x3856",parent=x3857) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3851 = CounterChain(name = "x3851", ctr11).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3876 = StreamController(name="x3876",parent=x4150) { implicit CU => 
    }
    val x3867_0 = Pipeline(name="x3867_0",parent=x3876) { implicit CU => 
      val x3861 = CU.temp()
      val x3860 =  ScalarBuffer().wtPort(x3860_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3861))
      Stage(operands=List(x3861, CU.load(x3860)), op=FixAdd, results=List(CU.scalarOut(x3858_b4202_x3866_b4204_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3858_b4203_x3866_b4205_s)))
    }
    val x3868 = MemoryController(name="x3868",parent=x3876,offchip=x3639_oc, mctpe=TileLoad) { implicit CU => 
      val x3858_b4203_x3868 =  ScalarFIFO(name="size",size=1).wtPort(x3858_b4203_x3866_b4205_s)
      val x3858_b4202_x3868 =  ScalarFIFO(name="offset",size=1).wtPort(x3858_b4202_x3866_b4204_s)
      CU.newVout("data", x3859_x3868_data_v)
    }
    val x3875 = Pipeline(name="x3875",parent=x3876) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3870 = CounterChain(name = "x3870", ctr12).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3895 = StreamController(name="x3895",parent=x4150) { implicit CU => 
    }
    val x3886_0 = Pipeline(name="x3886_0",parent=x3895) { implicit CU => 
      val x3880 = CU.temp()
      val x3879 =  ScalarBuffer().wtPort(x3879_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x3880))
      Stage(operands=List(x3880, CU.load(x3879)), op=FixAdd, results=List(CU.scalarOut(x3877_b4206_x3885_b4208_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x3877_b4207_x3885_b4209_s)))
    }
    val x3887 = MemoryController(name="x3887",parent=x3895,offchip=x3641_oc, mctpe=TileLoad) { implicit CU => 
      val x3877_b4207_x3887 =  ScalarFIFO(name="size",size=1).wtPort(x3877_b4207_x3885_b4209_s)
      val x3877_b4206_x3887 =  ScalarFIFO(name="offset",size=1).wtPort(x3877_b4206_x3885_b4208_s)
      CU.newVout("data", x3878_x3887_data_v)
    }
    val x3894 = Pipeline(name="x3894",parent=x3895) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3889 = CounterChain(name = "x3889", ctr13).iter(125)
      var stage: List[Stage] = Nil
    }
    val x3994 = StreamController(name="x3994",parent=x4150) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3899 = CounterChain(name = "x3899", ctr14).iter(125)
    }
    val x3994_0 = Pipeline(name="x3994_0",parent=x3994) { implicit CU => 
      val x3914 = CU.temp()
      val x3909 = CU.temp()
      val x3902_x3902 =  VectorFIFO(size=1).wtPort(x3657_x3902_x3994_v)
      val x3904_x3904 =  VectorFIFO(size=1).wtPort(x3661_x3904_x3994_v)
      val x3901_x3901 =  VectorFIFO(size=1).wtPort(x3655_x3901_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3901_x3901), CU.load(x3902_x3902)), op=FltDiv, results=List(x3909))
      Stage(operands=List(x3909), op=FltLog, results=List(CU.vecOut(bus_534_v)))
      Stage(operands=List(CU.load(x3904_x3904), CU.load(x3904_x3904)), op=FltMul, results=List(x3914))
      Stage(operands=List(x3914, Const(0.5)), op=FltMul, results=List(CU.vecOut(bus_537_v)))
    }
    val x3994_1 = Pipeline(name="x3994_1",parent=x3994) { implicit CU => 
      val x3916 = CU.temp()
      val x3905_x3905 =  VectorFIFO(size=1).wtPort(x3663_x3905_x3994_v)
      val x3915 =  VectorFIFO(size=1).wtPort(bus_537_v)
      val x3903_x3903 =  VectorFIFO(size=1).wtPort(x3659_x3903_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3903_x3903), CU.load(x3915)), op=FltAdd, results=List(x3916))
      Stage(operands=List(x3916, CU.load(x3905_x3905)), op=FltMul, results=List(CU.vecOut(bus_539_v)))
    }
    val x3994_2 = Pipeline(name="x3994_2",parent=x3994) { implicit CU => 
      val x3910 =  VectorFIFO(size=1).wtPort(bus_534_v)
      val x3905_x3905 =  VectorFIFO(size=1).wtPort(x3663_x3905_x3994_v)
      val x3917 =  VectorFIFO(size=1).wtPort(bus_539_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3917), CU.load(x3910)), op=FltAdd, results=List(CU.vecOut(bus_540_v)))
      Stage(operands=List(CU.load(x3905_x3905)), op=FltSqr, results=List(CU.vecOut(bus_541_v)))
    }
    val x3994_3 = Pipeline(name="x3994_3",parent=x3994) { implicit CU => 
      val x3921 = CU.temp()
      val x3922 = CU.temp()
      val x3920 = CU.temp()
      val x3919 =  VectorFIFO(size=1).wtPort(bus_541_v)
      val x3904_x3904 =  VectorFIFO(size=1).wtPort(x3661_x3904_x3994_v)
      val x3918 =  VectorFIFO(size=1).wtPort(bus_540_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3904_x3904), CU.load(x3919)), op=FltMul, results=List(x3920, CU.vecOut(bus_542_v)))
      Stage(operands=List(x3920, x3920), op=FltMul, results=List(x3921))
      Stage(operands=List(CU.load(x3918), x3921), op=FltDiv, results=List(x3922, CU.vecOut(bus_544_v)))
      Stage(operands=List(x3922), op=FltAbs, results=List(CU.vecOut(bus_545_v)))
    }
    val x3994_4 = Pipeline(name="x3994_4",parent=x3994) { implicit CU => 
      val x3928 = CU.temp()
      val x3924 = CU.temp()
      val x3926 = CU.temp()
      val x3925 = CU.temp()
      val x3923 =  VectorFIFO(size=1).wtPort(bus_545_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3923), CU.load(x3923)), op=FltMul, results=List(x3924))
      Stage(operands=List(x3924, Const(-0.05)), op=FltMul, results=List(x3925))
      Stage(operands=List(x3925), op=FltExp, results=List(x3926))
      Stage(operands=List(x3926, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_551_v)))
      Stage(operands=List(CU.load(x3923), Const(0.2316419)), op=FltMul, results=List(x3928))
      Stage(operands=List(x3928, Const(1)), op=FltAdd, results=List(CU.vecOut(bus_555_v)))
    }
    val x3994_5 = Pipeline(name="x3994_5",parent=x3994) { implicit CU => 
      val x3930 = CU.temp()
      val x3929 =  VectorFIFO(size=1).wtPort(bus_555_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(1), CU.load(x3929)), op=FltDiv, results=List(x3930, CU.vecOut(bus_556_v)))
      Stage(operands=List(x3930, Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_558_v)))
      Stage(operands=List(x3930, x3930), op=FltMul, results=List(CU.vecOut(bus_559_v)))
    }
    val x3994_6 = Pipeline(name="x3994_6",parent=x3994) { implicit CU => 
      val x3935 = CU.temp()
      val x3934 = CU.temp()
      val x3933 = CU.temp()
      val x3932 =  VectorFIFO(size=1).wtPort(bus_559_v)
      val x3930 =  VectorFIFO(size=1).wtPort(bus_556_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3932), CU.load(x3930)), op=FltMul, results=List(x3933, CU.vecOut(bus_560_v)))
      Stage(operands=List(x3933, CU.load(x3930)), op=FltMul, results=List(x3934))
      Stage(operands=List(x3934, CU.load(x3930)), op=FltMul, results=List(x3935))
      Stage(operands=List(x3935, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_564_v)))
      Stage(operands=List(x3934, Const(-1.8212559)), op=FltMul, results=List(CU.vecOut(bus_566_v)))
    }
    val x3994_7 = Pipeline(name="x3994_7",parent=x3994) { implicit CU => 
      val x3938 = CU.temp()
      val x3939 = CU.temp()
      val x3940 = CU.temp()
      val x3937 =  VectorFIFO(size=1).wtPort(bus_566_v)
      val x3933 =  VectorFIFO(size=1).wtPort(bus_560_v)
      val x3932 =  VectorFIFO(size=1).wtPort(bus_559_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3932), Const(-0.35656378)), op=FltMul, results=List(x3938))
      Stage(operands=List(CU.load(x3933), Const(1.7814779)), op=FltMul, results=List(x3939))
      Stage(operands=List(x3938, x3939), op=FltAdd, results=List(x3940))
      Stage(operands=List(x3940, CU.load(x3937)), op=FltAdd, results=List(CU.vecOut(bus_572_v)))
    }
    val x3994_8 = Pipeline(name="x3994_8",parent=x3994) { implicit CU => 
      val x3942 = CU.temp()
      val x3936 =  VectorFIFO(size=1).wtPort(bus_564_v)
      val x3941 =  VectorFIFO(size=1).wtPort(bus_572_v)
      val x3931 =  VectorFIFO(size=1).wtPort(bus_558_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3941), CU.load(x3936)), op=FltAdd, results=List(x3942))
      Stage(operands=List(x3942, CU.load(x3931)), op=FltAdd, results=List(CU.vecOut(bus_574_v)))
    }
    val x3994_9 = Pipeline(name="x3994_9",parent=x3994) { implicit CU => 
      val x3944 = CU.temp()
      val x3945 = CU.temp()
      val x3947 = CU.temp()
      val x3946 = CU.temp()
      val x3943 =  VectorFIFO(size=1).wtPort(bus_574_v)
      val x3922 =  VectorFIFO(size=1).wtPort(bus_544_v)
      val x3927 =  VectorFIFO(size=1).wtPort(bus_551_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3943), CU.load(x3927)), op=FltMul, results=List(x3944))
      Stage(operands=List(x3944), op=FltNeg, results=List(x3945))
      Stage(operands=List(x3945, Const(1)), op=FltAdd, results=List(x3946))
      Stage(operands=List(CU.load(x3922), Const(0)), op=FltLt, results=List(x3947))
      Stage(operands=List(x3947, x3944, x3946), op=Mux, results=List(CU.vecOut(bus_580_v)))
    }
    val x3994_10 = Pipeline(name="x3994_10",parent=x3994) { implicit CU => 
      val x3901_x3901 =  VectorFIFO(size=1).wtPort(x3655_x3901_x3994_v)
      val x3948 =  VectorFIFO(size=1).wtPort(bus_580_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3901_x3901), CU.load(x3948)), op=FltMul, results=List(CU.vecOut(bus_581_v)))
    }
    val x3994_11 = Pipeline(name="x3994_11",parent=x3994) { implicit CU => 
      val x3954 = CU.temp()
      val x3953 = CU.temp()
      val x3950 = CU.temp()
      val x3952 = CU.temp()
      val x3951 = CU.temp()
      val x3920 =  VectorFIFO(size=1).wtPort(bus_542_v)
      val x3922 =  VectorFIFO(size=1).wtPort(bus_544_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3922), CU.load(x3920)), op=FltSub, results=List(x3950, CU.vecOut(bus_582_v)))
      Stage(operands=List(x3950), op=FltAbs, results=List(x3951, CU.vecOut(bus_583_v)))
      Stage(operands=List(x3951, x3951), op=FltMul, results=List(x3952))
      Stage(operands=List(x3952, Const(-0.05)), op=FltMul, results=List(x3953))
      Stage(operands=List(x3953), op=FltExp, results=List(x3954))
      Stage(operands=List(x3954, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_587_v)))
    }
    val x3994_12 = Pipeline(name="x3994_12",parent=x3994) { implicit CU => 
      val x3957 = CU.temp()
      val x3956 = CU.temp()
      val x3958 = CU.temp()
      val x3951 =  VectorFIFO(size=1).wtPort(bus_583_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3951), Const(0.2316419)), op=FltMul, results=List(x3956))
      Stage(operands=List(x3956, Const(1)), op=FltAdd, results=List(x3957))
      Stage(operands=List(Const(1), x3957), op=FltDiv, results=List(x3958, CU.vecOut(bus_590_v)))
      Stage(operands=List(x3958, Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_591_v)))
      Stage(operands=List(x3958, x3958), op=FltMul, results=List(CU.vecOut(bus_592_v)))
    }
    val x3994_13 = Pipeline(name="x3994_13",parent=x3994) { implicit CU => 
      val x3963 = CU.temp()
      val x3962 = CU.temp()
      val x3961 = CU.temp()
      val x3958 =  VectorFIFO(size=1).wtPort(bus_590_v)
      val x3960 =  VectorFIFO(size=1).wtPort(bus_592_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3960), CU.load(x3958)), op=FltMul, results=List(x3961, CU.vecOut(bus_593_v)))
      Stage(operands=List(x3961, CU.load(x3958)), op=FltMul, results=List(x3962))
      Stage(operands=List(x3962, CU.load(x3958)), op=FltMul, results=List(x3963))
      Stage(operands=List(x3963, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_596_v)))
      Stage(operands=List(x3962, Const(-1.8212559)), op=FltMul, results=List(CU.vecOut(bus_597_v)))
    }
    val x3994_14 = Pipeline(name="x3994_14",parent=x3994) { implicit CU => 
      val x3966 = CU.temp()
      val x3967 = CU.temp()
      val x3968 = CU.temp()
      val x3965 =  VectorFIFO(size=1).wtPort(bus_597_v)
      val x3961 =  VectorFIFO(size=1).wtPort(bus_593_v)
      val x3960 =  VectorFIFO(size=1).wtPort(bus_592_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3960), Const(-0.35656378)), op=FltMul, results=List(x3966))
      Stage(operands=List(CU.load(x3961), Const(1.7814779)), op=FltMul, results=List(x3967))
      Stage(operands=List(x3966, x3967), op=FltAdd, results=List(x3968))
      Stage(operands=List(x3968, CU.load(x3965)), op=FltAdd, results=List(CU.vecOut(bus_601_v)))
    }
    val x3994_15 = Pipeline(name="x3994_15",parent=x3994) { implicit CU => 
      val x3970 = CU.temp()
      val x3964 =  VectorFIFO(size=1).wtPort(bus_596_v)
      val x3959 =  VectorFIFO(size=1).wtPort(bus_591_v)
      val x3969 =  VectorFIFO(size=1).wtPort(bus_601_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3969), CU.load(x3964)), op=FltAdd, results=List(x3970))
      Stage(operands=List(x3970, CU.load(x3959)), op=FltAdd, results=List(CU.vecOut(bus_603_v)))
    }
    val x3994_16 = Pipeline(name="x3994_16",parent=x3994) { implicit CU => 
      val x3975 = CU.temp()
      val x3974 = CU.temp()
      val x3972 = CU.temp()
      val x3973 = CU.temp()
      val x3955 =  VectorFIFO(size=1).wtPort(bus_587_v)
      val x3971 =  VectorFIFO(size=1).wtPort(bus_603_v)
      val x3950 =  VectorFIFO(size=1).wtPort(bus_582_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3971), CU.load(x3955)), op=FltMul, results=List(x3972))
      Stage(operands=List(x3972), op=FltNeg, results=List(x3973))
      Stage(operands=List(x3973, Const(1)), op=FltAdd, results=List(x3974))
      Stage(operands=List(CU.load(x3950), Const(0)), op=FltLt, results=List(x3975))
      Stage(operands=List(x3975, x3972, x3974), op=Mux, results=List(CU.vecOut(bus_608_v)))
    }
    val x3994_17 = Pipeline(name="x3994_17",parent=x3994) { implicit CU => 
      val x3977 = CU.temp()
      val x3978 = CU.temp()
      val x3979 = CU.temp()
      val x3905_x3905 =  VectorFIFO(size=1).wtPort(x3663_x3905_x3994_v)
      val x3902_x3902 =  VectorFIFO(size=1).wtPort(x3657_x3902_x3994_v)
      val x3903_x3903 =  VectorFIFO(size=1).wtPort(x3659_x3903_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3903_x3903)), op=FltNeg, results=List(x3977))
      Stage(operands=List(x3977, CU.load(x3905_x3905)), op=FltMul, results=List(x3978))
      Stage(operands=List(x3978), op=FltExp, results=List(x3979))
      Stage(operands=List(CU.load(x3902_x3902), x3979), op=FltMul, results=List(CU.vecOut(bus_612_v)))
    }
    val x3994_18 = Pipeline(name="x3994_18",parent=x3994) { implicit CU => 
      val x3984 = CU.temp()
      val x3981 = CU.temp()
      val x3983 = CU.temp()
      val x3980 =  VectorFIFO(size=1).wtPort(bus_612_v)
      val x3976 =  VectorFIFO(size=1).wtPort(bus_608_v)
      val x3949 =  VectorFIFO(size=1).wtPort(bus_581_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3980), CU.load(x3976)), op=FltMul, results=List(x3981))
      Stage(operands=List(CU.load(x3949), x3981), op=FltSub, results=List(CU.vecOut(bus_614_v)))
      Stage(operands=List(CU.load(x3976)), op=FltNeg, results=List(x3983))
      Stage(operands=List(x3983, Const(1)), op=FltAdd, results=List(x3984))
      Stage(operands=List(CU.load(x3980), x3984), op=FltMul, results=List(CU.vecOut(bus_617_v)))
    }
    val x3994_19 = Pipeline(name="x3994_19",parent=x3994) { implicit CU => 
      val x3987 = CU.temp()
      val x3988 = CU.temp()
      val x3986 = CU.temp()
      val x3985 =  VectorFIFO(size=1).wtPort(bus_617_v)
      val x3901_x3901 =  VectorFIFO(size=1).wtPort(x3655_x3901_x3994_v)
      val x3948 =  VectorFIFO(size=1).wtPort(bus_580_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3948)), op=FltNeg, results=List(x3986))
      Stage(operands=List(x3986, Const(1)), op=FltAdd, results=List(x3987))
      Stage(operands=List(CU.load(x3901_x3901), x3987), op=FltMul, results=List(x3988))
      Stage(operands=List(CU.load(x3985), x3988), op=FltSub, results=List(CU.vecOut(bus_621_v)))
    }
    val x3994_20 = Pipeline(name="x3994_20",parent=x3994) { implicit CU => 
      val x3991 = CU.temp()
      val x3982 =  VectorFIFO(size=1).wtPort(bus_614_v)
      val x3989 =  VectorFIFO(size=1).wtPort(bus_621_v)
      val x3906_x3906 =  VectorFIFO(size=1).wtPort(x3653_x3906_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3906_x3906), Const(0)), op=FixEql, results=List(x3991))
      Stage(operands=List(x3991, CU.load(x3989), CU.load(x3982)), op=Mux, results=List(CU.vecOut(x3665_x3993_v)))
    }
    val x4091 = StreamController(name="x4091",parent=x4150) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3996 = CounterChain(name = "x3996", ctr15).iter(125)
    }
    val x4091_0 = Pipeline(name="x4091_0",parent=x4091) { implicit CU => 
      val x4011 = CU.temp()
      val x4006 = CU.temp()
      val x3998_x3998 =  VectorFIFO(size=1).wtPort(x3656_x3998_x4091_v)
      val x3999_x3999 =  VectorFIFO(size=1).wtPort(x3658_x3999_x4091_v)
      val x4001_x4001 =  VectorFIFO(size=1).wtPort(x3662_x4001_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3998_x3998), CU.load(x3999_x3999)), op=FltDiv, results=List(x4006))
      Stage(operands=List(x4006), op=FltLog, results=List(CU.vecOut(bus_626_v)))
      Stage(operands=List(CU.load(x4001_x4001), CU.load(x4001_x4001)), op=FltMul, results=List(x4011))
      Stage(operands=List(x4011, Const(0.5)), op=FltMul, results=List(CU.vecOut(bus_629_v)))
    }
    val x4091_1 = Pipeline(name="x4091_1",parent=x4091) { implicit CU => 
      val x4013 = CU.temp()
      val x4000_x4000 =  VectorFIFO(size=1).wtPort(x3660_x4000_x4091_v)
      val x4012 =  VectorFIFO(size=1).wtPort(bus_629_v)
      val x4002_x4002 =  VectorFIFO(size=1).wtPort(x3664_x4002_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4000_x4000), CU.load(x4012)), op=FltAdd, results=List(x4013))
      Stage(operands=List(x4013, CU.load(x4002_x4002)), op=FltMul, results=List(CU.vecOut(bus_631_v)))
    }
    val x4091_2 = Pipeline(name="x4091_2",parent=x4091) { implicit CU => 
      val x4007 =  VectorFIFO(size=1).wtPort(bus_626_v)
      val x4014 =  VectorFIFO(size=1).wtPort(bus_631_v)
      val x4002_x4002 =  VectorFIFO(size=1).wtPort(x3664_x4002_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4014), CU.load(x4007)), op=FltAdd, results=List(CU.vecOut(bus_632_v)))
      Stage(operands=List(CU.load(x4002_x4002)), op=FltSqr, results=List(CU.vecOut(bus_633_v)))
    }
    val x4091_3 = Pipeline(name="x4091_3",parent=x4091) { implicit CU => 
      val x4018 = CU.temp()
      val x4017 = CU.temp()
      val x4019 = CU.temp()
      val x4016 =  VectorFIFO(size=1).wtPort(bus_633_v)
      val x4015 =  VectorFIFO(size=1).wtPort(bus_632_v)
      val x4001_x4001 =  VectorFIFO(size=1).wtPort(x3662_x4001_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4001_x4001), CU.load(x4016)), op=FltMul, results=List(x4017, CU.vecOut(bus_634_v)))
      Stage(operands=List(x4017, x4017), op=FltMul, results=List(x4018))
      Stage(operands=List(CU.load(x4015), x4018), op=FltDiv, results=List(x4019, CU.vecOut(bus_636_v)))
      Stage(operands=List(x4019), op=FltAbs, results=List(CU.vecOut(bus_637_v)))
    }
    val x4091_4 = Pipeline(name="x4091_4",parent=x4091) { implicit CU => 
      val x4023 = CU.temp()
      val x4022 = CU.temp()
      val x4025 = CU.temp()
      val x4021 = CU.temp()
      val x4020 =  VectorFIFO(size=1).wtPort(bus_637_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4020), CU.load(x4020)), op=FltMul, results=List(x4021))
      Stage(operands=List(x4021, Const(-0.05)), op=FltMul, results=List(x4022))
      Stage(operands=List(x4022), op=FltExp, results=List(x4023))
      Stage(operands=List(x4023, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_643_v)))
      Stage(operands=List(CU.load(x4020), Const(0.2316419)), op=FltMul, results=List(x4025))
      Stage(operands=List(x4025, Const(1)), op=FltAdd, results=List(CU.vecOut(bus_647_v)))
    }
    val x4091_5 = Pipeline(name="x4091_5",parent=x4091) { implicit CU => 
      val x4027 = CU.temp()
      val x4026 =  VectorFIFO(size=1).wtPort(bus_647_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(1), CU.load(x4026)), op=FltDiv, results=List(x4027, CU.vecOut(bus_648_v)))
      Stage(operands=List(x4027, Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_650_v)))
      Stage(operands=List(x4027, x4027), op=FltMul, results=List(CU.vecOut(bus_651_v)))
    }
    val x4091_6 = Pipeline(name="x4091_6",parent=x4091) { implicit CU => 
      val x4031 = CU.temp()
      val x4032 = CU.temp()
      val x4030 = CU.temp()
      val x4027 =  VectorFIFO(size=1).wtPort(bus_648_v)
      val x4029 =  VectorFIFO(size=1).wtPort(bus_651_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4029), CU.load(x4027)), op=FltMul, results=List(x4030, CU.vecOut(bus_652_v)))
      Stage(operands=List(x4030, CU.load(x4027)), op=FltMul, results=List(x4031))
      Stage(operands=List(x4031, CU.load(x4027)), op=FltMul, results=List(x4032))
      Stage(operands=List(x4032, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_656_v)))
      Stage(operands=List(x4031, Const(-1.8212559)), op=FltMul, results=List(CU.vecOut(bus_658_v)))
    }
    val x4091_7 = Pipeline(name="x4091_7",parent=x4091) { implicit CU => 
      val x4035 = CU.temp()
      val x4036 = CU.temp()
      val x4037 = CU.temp()
      val x4034 =  VectorFIFO(size=1).wtPort(bus_658_v)
      val x4030 =  VectorFIFO(size=1).wtPort(bus_652_v)
      val x4029 =  VectorFIFO(size=1).wtPort(bus_651_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4029), Const(-0.35656378)), op=FltMul, results=List(x4035))
      Stage(operands=List(CU.load(x4030), Const(1.7814779)), op=FltMul, results=List(x4036))
      Stage(operands=List(x4035, x4036), op=FltAdd, results=List(x4037))
      Stage(operands=List(x4037, CU.load(x4034)), op=FltAdd, results=List(CU.vecOut(bus_664_v)))
    }
    val x4091_8 = Pipeline(name="x4091_8",parent=x4091) { implicit CU => 
      val x4039 = CU.temp()
      val x4033 =  VectorFIFO(size=1).wtPort(bus_656_v)
      val x4028 =  VectorFIFO(size=1).wtPort(bus_650_v)
      val x4038 =  VectorFIFO(size=1).wtPort(bus_664_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4038), CU.load(x4033)), op=FltAdd, results=List(x4039))
      Stage(operands=List(x4039, CU.load(x4028)), op=FltAdd, results=List(CU.vecOut(bus_666_v)))
    }
    val x4091_9 = Pipeline(name="x4091_9",parent=x4091) { implicit CU => 
      val x4043 = CU.temp()
      val x4044 = CU.temp()
      val x4041 = CU.temp()
      val x4042 = CU.temp()
      val x4040 =  VectorFIFO(size=1).wtPort(bus_666_v)
      val x4019 =  VectorFIFO(size=1).wtPort(bus_636_v)
      val x4024 =  VectorFIFO(size=1).wtPort(bus_643_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4040), CU.load(x4024)), op=FltMul, results=List(x4041))
      Stage(operands=List(x4041), op=FltNeg, results=List(x4042))
      Stage(operands=List(x4042, Const(1)), op=FltAdd, results=List(x4043))
      Stage(operands=List(CU.load(x4019), Const(0)), op=FltLt, results=List(x4044))
      Stage(operands=List(x4044, x4041, x4043), op=Mux, results=List(CU.vecOut(bus_672_v)))
    }
    val x4091_10 = Pipeline(name="x4091_10",parent=x4091) { implicit CU => 
      val x3998_x3998 =  VectorFIFO(size=1).wtPort(x3656_x3998_x4091_v)
      val x4045 =  VectorFIFO(size=1).wtPort(bus_672_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3998_x3998), CU.load(x4045)), op=FltMul, results=List(CU.vecOut(bus_673_v)))
    }
    val x4091_11 = Pipeline(name="x4091_11",parent=x4091) { implicit CU => 
      val x4049 = CU.temp()
      val x4048 = CU.temp()
      val x4047 = CU.temp()
      val x4050 = CU.temp()
      val x4051 = CU.temp()
      val x4017 =  VectorFIFO(size=1).wtPort(bus_634_v)
      val x4019 =  VectorFIFO(size=1).wtPort(bus_636_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4019), CU.load(x4017)), op=FltSub, results=List(x4047, CU.vecOut(bus_674_v)))
      Stage(operands=List(x4047), op=FltAbs, results=List(x4048, CU.vecOut(bus_675_v)))
      Stage(operands=List(x4048, x4048), op=FltMul, results=List(x4049))
      Stage(operands=List(x4049, Const(-0.05)), op=FltMul, results=List(x4050))
      Stage(operands=List(x4050), op=FltExp, results=List(x4051))
      Stage(operands=List(x4051, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_679_v)))
    }
    val x4091_12 = Pipeline(name="x4091_12",parent=x4091) { implicit CU => 
      val x4053 = CU.temp()
      val x4054 = CU.temp()
      val x4055 = CU.temp()
      val x4048 =  VectorFIFO(size=1).wtPort(bus_675_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4048), Const(0.2316419)), op=FltMul, results=List(x4053))
      Stage(operands=List(x4053, Const(1)), op=FltAdd, results=List(x4054))
      Stage(operands=List(Const(1), x4054), op=FltDiv, results=List(x4055, CU.vecOut(bus_682_v)))
      Stage(operands=List(x4055, Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_683_v)))
      Stage(operands=List(x4055, x4055), op=FltMul, results=List(CU.vecOut(bus_684_v)))
    }
    val x4091_13 = Pipeline(name="x4091_13",parent=x4091) { implicit CU => 
      val x4058 = CU.temp()
      val x4059 = CU.temp()
      val x4060 = CU.temp()
      val x4055 =  VectorFIFO(size=1).wtPort(bus_682_v)
      val x4057 =  VectorFIFO(size=1).wtPort(bus_684_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4057), CU.load(x4055)), op=FltMul, results=List(x4058, CU.vecOut(bus_685_v)))
      Stage(operands=List(x4058, CU.load(x4055)), op=FltMul, results=List(x4059))
      Stage(operands=List(x4059, CU.load(x4055)), op=FltMul, results=List(x4060))
      Stage(operands=List(x4060, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_688_v)))
      Stage(operands=List(x4059, Const(-1.8212559)), op=FltMul, results=List(CU.vecOut(bus_689_v)))
    }
    val x4091_14 = Pipeline(name="x4091_14",parent=x4091) { implicit CU => 
      val x4064 = CU.temp()
      val x4063 = CU.temp()
      val x4065 = CU.temp()
      val x4062 =  VectorFIFO(size=1).wtPort(bus_689_v)
      val x4057 =  VectorFIFO(size=1).wtPort(bus_684_v)
      val x4058 =  VectorFIFO(size=1).wtPort(bus_685_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4057), Const(-0.35656378)), op=FltMul, results=List(x4063))
      Stage(operands=List(CU.load(x4058), Const(1.7814779)), op=FltMul, results=List(x4064))
      Stage(operands=List(x4063, x4064), op=FltAdd, results=List(x4065))
      Stage(operands=List(x4065, CU.load(x4062)), op=FltAdd, results=List(CU.vecOut(bus_693_v)))
    }
    val x4091_15 = Pipeline(name="x4091_15",parent=x4091) { implicit CU => 
      val x4067 = CU.temp()
      val x4056 =  VectorFIFO(size=1).wtPort(bus_683_v)
      val x4061 =  VectorFIFO(size=1).wtPort(bus_688_v)
      val x4066 =  VectorFIFO(size=1).wtPort(bus_693_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4066), CU.load(x4061)), op=FltAdd, results=List(x4067))
      Stage(operands=List(x4067, CU.load(x4056)), op=FltAdd, results=List(CU.vecOut(bus_695_v)))
    }
    val x4091_16 = Pipeline(name="x4091_16",parent=x4091) { implicit CU => 
      val x4072 = CU.temp()
      val x4069 = CU.temp()
      val x4071 = CU.temp()
      val x4070 = CU.temp()
      val x4052 =  VectorFIFO(size=1).wtPort(bus_679_v)
      val x4047 =  VectorFIFO(size=1).wtPort(bus_674_v)
      val x4068 =  VectorFIFO(size=1).wtPort(bus_695_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4068), CU.load(x4052)), op=FltMul, results=List(x4069))
      Stage(operands=List(x4069), op=FltNeg, results=List(x4070))
      Stage(operands=List(x4070, Const(1)), op=FltAdd, results=List(x4071))
      Stage(operands=List(CU.load(x4047), Const(0)), op=FltLt, results=List(x4072))
      Stage(operands=List(x4072, x4069, x4071), op=Mux, results=List(CU.vecOut(bus_700_v)))
    }
    val x4091_17 = Pipeline(name="x4091_17",parent=x4091) { implicit CU => 
      val x4074 = CU.temp()
      val x4076 = CU.temp()
      val x4075 = CU.temp()
      val x4000_x4000 =  VectorFIFO(size=1).wtPort(x3660_x4000_x4091_v)
      val x4002_x4002 =  VectorFIFO(size=1).wtPort(x3664_x4002_x4091_v)
      val x3999_x3999 =  VectorFIFO(size=1).wtPort(x3658_x3999_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4000_x4000)), op=FltNeg, results=List(x4074))
      Stage(operands=List(x4074, CU.load(x4002_x4002)), op=FltMul, results=List(x4075))
      Stage(operands=List(x4075), op=FltExp, results=List(x4076))
      Stage(operands=List(CU.load(x3999_x3999), x4076), op=FltMul, results=List(CU.vecOut(bus_704_v)))
    }
    val x4091_18 = Pipeline(name="x4091_18",parent=x4091) { implicit CU => 
      val x4081 = CU.temp()
      val x4080 = CU.temp()
      val x4078 = CU.temp()
      val x4077 =  VectorFIFO(size=1).wtPort(bus_704_v)
      val x4046 =  VectorFIFO(size=1).wtPort(bus_673_v)
      val x4073 =  VectorFIFO(size=1).wtPort(bus_700_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4077), CU.load(x4073)), op=FltMul, results=List(x4078))
      Stage(operands=List(CU.load(x4046), x4078), op=FltSub, results=List(CU.vecOut(bus_706_v)))
      Stage(operands=List(CU.load(x4073)), op=FltNeg, results=List(x4080))
      Stage(operands=List(x4080, Const(1)), op=FltAdd, results=List(x4081))
      Stage(operands=List(CU.load(x4077), x4081), op=FltMul, results=List(CU.vecOut(bus_709_v)))
    }
    val x4091_19 = Pipeline(name="x4091_19",parent=x4091) { implicit CU => 
      val x4084 = CU.temp()
      val x4083 = CU.temp()
      val x4085 = CU.temp()
      val x3998_x3998 =  VectorFIFO(size=1).wtPort(x3656_x3998_x4091_v)
      val x4082 =  VectorFIFO(size=1).wtPort(bus_709_v)
      val x4045 =  VectorFIFO(size=1).wtPort(bus_672_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4045)), op=FltNeg, results=List(x4083))
      Stage(operands=List(x4083, Const(1)), op=FltAdd, results=List(x4084))
      Stage(operands=List(CU.load(x3998_x3998), x4084), op=FltMul, results=List(x4085))
      Stage(operands=List(CU.load(x4082), x4085), op=FltSub, results=List(CU.vecOut(bus_713_v)))
    }
    val x4091_20 = Pipeline(name="x4091_20",parent=x4091) { implicit CU => 
      val x4088 = CU.temp()
      val x4079 =  VectorFIFO(size=1).wtPort(bus_706_v)
      val x4003_x4003 =  VectorFIFO(size=1).wtPort(x3654_x4003_x4091_v)
      val x4086 =  VectorFIFO(size=1).wtPort(bus_713_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4003_x4003), Const(0)), op=FixEql, results=List(x4088))
      Stage(operands=List(x4088, CU.load(x4086), CU.load(x4079)), op=Mux, results=List(CU.vecOut(x3666_x4090_v)))
    }
    val x4120 = StreamController(name="x4120",parent=x4150) { implicit CU => 
    }
    val x4112 = Sequential(name="x4112",parent=x4120) { implicit CU => 
    }
    val x4103_0 = Pipeline(name="x4103_0",parent=x4112) { implicit CU => 
      val x4097 = CU.temp()
      val x4096 =  ScalarBuffer().wtPort(x4096_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x4097))
      Stage(operands=List(x4097, CU.load(x4096)), op=FixAdd, results=List(CU.scalarOut(x4093_b4210_x4102_b4212_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4093_b4211_x4102_b4213_s)))
    }
    val x4111 = Pipeline(name="x4111",parent=x4112) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4105 = CounterChain(name = "x4105", ctr16).iter(125)
      var stage: List[Stage] = Nil
    }
    val x4113 = MemoryController(name="x4113",parent=x4120,offchip=x3643_oc, mctpe=TileStore) { implicit CU => 
      val x4093_b4210_x4113 =  ScalarFIFO(name="offset",size=1).wtPort(x4093_b4210_x4102_b4212_s)
      val x4093_b4211_x4113 =  ScalarFIFO(name="size",size=1).wtPort(x4093_b4211_x4102_b4213_s)
      val x4094_x4113 =  VectorFIFO(name="data",size=1).wtPort(x3665_x4107_x4111_v)
    }
    val x4148 = StreamController(name="x4148",parent=x4150) { implicit CU => 
    }
    val x4140 = Sequential(name="x4140",parent=x4148) { implicit CU => 
    }
    val x4131_0 = Pipeline(name="x4131_0",parent=x4140) { implicit CU => 
      val x4125 = CU.temp()
      val x4124 =  ScalarBuffer().wtPort(x4124_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x4125))
      Stage(operands=List(x4125, CU.load(x4124)), op=FixAdd, results=List(CU.scalarOut(x4121_b4214_x4130_b4216_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4121_b4215_x4130_b4217_s)))
    }
    val x4139 = Pipeline(name="x4139",parent=x4140) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4133 = CounterChain(name = "x4133", ctr18).iter(125)
      var stage: List[Stage] = Nil
    }
    val x4141 = MemoryController(name="x4141",parent=x4148,offchip=x3643_oc, mctpe=TileStore) { implicit CU => 
      val x4122_x4141 =  VectorFIFO(name="data",size=1).wtPort(x3666_x4135_x4139_v)
      val x4121_b4215_x4141 =  ScalarFIFO(name="size",size=1).wtPort(x4121_b4215_x4130_b4217_s)
      val x4121_b4214_x4141 =  ScalarFIFO(name="offset",size=1).wtPort(x4121_b4214_x4130_b4216_s)
    }
    
  }
}
