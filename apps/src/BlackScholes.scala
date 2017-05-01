import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object BlackScholes extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3860_argin = ArgIn("x3860")
    val x3658_x3999_x4091_v = Vector("x3658_x3999_x4091")
    val bus_711_v = Vector("bus_711")
    val x3877_b4206_x3885_b4208_s = Scalar("x3877_b4206_x3885_b4208")
    val x3803_argin = ArgIn("x3803")
    val x3653_x3906_x3994_v = Vector("x3653_x3906_x3994")
    val x3744_x3753_data_v = Vector("x3744_x3753_data")
    val x3633_oc = OffChip("x3633")
    val x3656_x3998_x4091_v = Vector("x3656_x3998_x4091")
    val bus_808_v = Vector("bus_808")
    val bus_736_v = Vector("bus_736")
    val bus_720_v = Vector("bus_720")
    val x4124_argin = ArgIn("x4124")
    val x3839_b4199_x3847_b4201_s = Scalar("x3839_b4199_x3847_b4201")
    val x3859_x3868_data_v = Vector("x3859_x3868_data")
    val bus_663_v = Vector("bus_663")
    val bus_707_v = Vector("bus_707")
    val x3726_argin = ArgIn("x3726")
    val x3655_x3901_x3994_v = Vector("x3655_x3901_x3994")
    val x3841_argin = ArgIn("x3841")
    val bus_639_v = Vector("bus_639")
    val bus_716_v = Vector("bus_716")
    val x3725_x3734_data_v = Vector("x3725_x3734_data")
    val bus_803_v = Vector("bus_803")
    val x4121_b4215_x4130_b4217_s = Scalar("x4121_b4215_x4130_b4217")
    val bus_785_v = Vector("bus_785")
    val bus_781_v = Vector("bus_781")
    val bus_680_v = Vector("bus_680")
    val bus_643_v = Vector("bus_643")
    val bus_799_v = Vector("bus_799")
    val x3858_b4202_x3866_b4204_s = Scalar("x3858_b4202_x3866_b4204")
    val x3822_argin = ArgIn("x3822")
    val x3724_b4175_x3732_b4177_s = Scalar("x3724_b4175_x3732_b4177")
    val bus_812_v = Vector("bus_812")
    val bus_690_v = Vector("bus_690")
    val bus_655_v = Vector("bus_655")
    val x3662_x4001_x4091_v = Vector("x3662_x4001_x4091")
    val bus_750_v = Vector("bus_750")
    val x3782_b4186_x3790_b4188_s = Scalar("x3782_b4186_x3790_b4188")
    val x3688_argin = ArgIn("x3688")
    val x3639_oc = OffChip("x3639")
    val bus_805_v = Vector("bus_805")
    val bus_784_v = Vector("bus_784")
    val x3877_b4207_x3885_b4209_s = Scalar("x3877_b4207_x3885_b4209")
    val bus_695_v = Vector("bus_695")
    val x3667_b4163_x3675_b4165_s = Scalar("x3667_b4163_x3675_b4165")
    val bus_793_v = Vector("bus_793")
    val bus_641_v = Vector("bus_641")
    val x3665_x3993_v = Vector("x3665_x3993")
    val x3763_x3772_data_v = Vector("x3763_x3772_data")
    val x3802_x3811_data_v = Vector("x3802_x3811_data")
    val bus_778_v = Vector("bus_778")
    val x3705_b4171_x3713_b4173_s = Scalar("x3705_b4171_x3713_b4173")
    val x3743_b4178_x3751_b4180_s = Scalar("x3743_b4178_x3751_b4180")
    val x3705_b4170_x3713_b4172_s = Scalar("x3705_b4170_x3713_b4172")
    val x3745_argin = ArgIn("x3745")
    val x3663_x3905_x3994_v = Vector("x3663_x3905_x3994")
    val x3664_x4002_x4091_v = Vector("x3664_x4002_x4091")
    val bus_672_v = Vector("bus_672")
    val x3821_x3830_data_v = Vector("x3821_x3830_data")
    val x3839_b4198_x3847_b4200_s = Scalar("x3839_b4198_x3847_b4200")
    val x3661_x3904_x3994_v = Vector("x3661_x3904_x3994")
    val bus_749_v = Vector("bus_749")
    val x3782_b4187_x3790_b4189_s = Scalar("x3782_b4187_x3790_b4189")
    val x3660_x4000_x4091_v = Vector("x3660_x4000_x4091")
    val bus_787_v = Vector("bus_787")
    val x4096_argin = ArgIn("x4096")
    val bus_747_v = Vector("bus_747")
    val x3801_b4191_x3809_b4193_s = Scalar("x3801_b4191_x3809_b4193")
    val x3686_b4167_x3694_b4169_s = Scalar("x3686_b4167_x3694_b4169")
    val bus_755_v = Vector("bus_755")
    val x3643_oc = OffChip("x3643")
    val x4093_b4211_x4102_b4213_s = Scalar("x4093_b4211_x4102_b4213")
    val bus_764_v = Vector("bus_764")
    val bus_725_v = Vector("bus_725")
    val x3764_argin = ArgIn("x3764")
    val bus_691_v = Vector("bus_691")
    val bus_679_v = Vector("bus_679")
    val x3666_x4090_v = Vector("x3666_x4090")
    val bus_752_v = Vector("bus_752")
    val x3667_b4162_x3675_b4164_s = Scalar("x3667_b4162_x3675_b4164")
    val bus_659_v = Vector("bus_659")
    val x3762_b4183_x3770_b4185_s = Scalar("x3762_b4183_x3770_b4185")
    val bus_774_v = Vector("bus_774")
    val x3706_x3715_data_v = Vector("x3706_x3715_data")
    val bus_660_v = Vector("bus_660")
    val x4121_b4214_x4130_b4216_s = Scalar("x4121_b4214_x4130_b4216")
    val x3657_x3902_x3994_v = Vector("x3657_x3902_x3994")
    val x3878_x3887_data_v = Vector("x3878_x3887_data")
    val bus_682_v = Vector("bus_682")
    val bus_751_v = Vector("bus_751")
    val bus_633_v = Vector("bus_633")
    val x3858_b4203_x3866_b4205_s = Scalar("x3858_b4203_x3866_b4205")
    val x3743_b4179_x3751_b4181_s = Scalar("x3743_b4179_x3751_b4181")
    val bus_731_v = Vector("bus_731")
    val bus_701_v = Vector("bus_701")
    val bus_733_v = Vector("bus_733")
    val bus_681_v = Vector("bus_681")
    val bus_686_v = Vector("bus_686")
    val bus_783_v = Vector("bus_783")
    val bus_713_v = Vector("bus_713")
    val x3659_x3903_x3994_v = Vector("x3659_x3903_x3994")
    val bus_693_v = Vector("bus_693")
    val x3801_b4190_x3809_b4192_s = Scalar("x3801_b4190_x3809_b4192")
    val x3665_x4107_x4111_v = Vector("x3665_x4107_x4111")
    val x3668_x3677_data_v = Vector("x3668_x3677_data")
    val x3641_oc = OffChip("x3641")
    val x3707_argin = ArgIn("x3707")
    val x3724_b4174_x3732_b4176_s = Scalar("x3724_b4174_x3732_b4176")
    val x3879_argin = ArgIn("x3879")
    val bus_689_v = Vector("bus_689")
    val x3635_oc = OffChip("x3635")
    val bus_658_v = Vector("bus_658")
    val x3686_b4166_x3694_b4168_s = Scalar("x3686_b4166_x3694_b4168")
    val bus_772_v = Vector("bus_772")
    val x3687_x3696_data_v = Vector("x3687_x3696_data")
    val x3637_oc = OffChip("x3637")
    val x3654_x4003_x4091_v = Vector("x3654_x4003_x4091")
    val x4093_b4210_x4102_b4212_s = Scalar("x4093_b4210_x4102_b4212")
    val x3784_argin = ArgIn("x3784")
    val x3627_argin = ArgIn("x3627")
    val x3631_oc = OffChip("x3631")
    val x3840_x3849_data_v = Vector("x3840_x3849_data")
    val bus_692_v = Vector("bus_692")
    val x3666_x4135_x4139_v = Vector("x3666_x4135_x4139")
    val bus_644_v = Vector("bus_644")
    val x3783_x3792_data_v = Vector("x3783_x3792_data")
    val bus_782_v = Vector("bus_782")
    val x3820_b4194_x3828_b4196_s = Scalar("x3820_b4194_x3828_b4196")
    val bus_773_v = Vector("bus_773")
    val bus_735_v = Vector("bus_735")
    val x3820_b4195_x3828_b4197_s = Scalar("x3820_b4195_x3828_b4197")
    val x3762_b4182_x3770_b4184_s = Scalar("x3762_b4182_x3770_b4184")
    val bus_771_v = Vector("bus_771")
    val bus_650_v = Vector("bus_650")
    val bus_657_v = Vector("bus_657")
    val bus_742_v = Vector("bus_742")
    val x3669_argin = ArgIn("x3669")
    val x4151 = Sequential(name="x4151",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4151_unit = CounterChain(name = "x4151_unit", ctr1)
    }
    val x4150 = MetaPipeline(name="x4150",parent=x4151) { implicit CU => 
      val x3627_x3650 =  ScalarBuffer().wtPort(x3627_argin)
      val ctr2 = Counter(min=Const(0), max=x3627_x3650.load, step=Const(2000), par=2) // Counter
      val x3652 = CounterChain(name = "x3652", ctr2)
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
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3685_unit = CounterChain(name = "x3685_unit", ctr3)
    }
    val x3676_0 = Pipeline(name="x3676_0",parent=x3685) { implicit CU => 
      val x3670 = CU.temp
      val x3669 =  ScalarBuffer().wtPort(x3669_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3676_unit = CounterChain(name = "x3676_unit", ctr4)
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
      val ctr5 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3679 = CounterChain(name = "x3679", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3704 = StreamController(name="x3704",parent=x4150) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3704_unit = CounterChain(name = "x3704_unit", ctr6)
    }
    val x3695_0 = Pipeline(name="x3695_0",parent=x3704) { implicit CU => 
      val x3689 = CU.temp
      val x3688 =  ScalarBuffer().wtPort(x3688_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3695_unit = CounterChain(name = "x3695_unit", ctr7)
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
      val ctr8 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3698 = CounterChain(name = "x3698", ctr8)
      var stage: List[Stage] = Nil
    }
    val x3723 = StreamController(name="x3723",parent=x4150) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3723_unit = CounterChain(name = "x3723_unit", ctr9)
    }
    val x3714_0 = Pipeline(name="x3714_0",parent=x3723) { implicit CU => 
      val x3708 = CU.temp
      val x3707 =  ScalarBuffer().wtPort(x3707_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3714_unit = CounterChain(name = "x3714_unit", ctr10)
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
      val ctr11 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3717 = CounterChain(name = "x3717", ctr11)
      var stage: List[Stage] = Nil
    }
    val x3742 = StreamController(name="x3742",parent=x4150) { implicit CU => 
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3742_unit = CounterChain(name = "x3742_unit", ctr12)
    }
    val x3733_0 = Pipeline(name="x3733_0",parent=x3742) { implicit CU => 
      val x3727 = CU.temp
      val x3726 =  ScalarBuffer().wtPort(x3726_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3733_unit = CounterChain(name = "x3733_unit", ctr13)
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
      val ctr14 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3736 = CounterChain(name = "x3736", ctr14)
      var stage: List[Stage] = Nil
    }
    val x3761 = StreamController(name="x3761",parent=x4150) { implicit CU => 
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3761_unit = CounterChain(name = "x3761_unit", ctr15)
    }
    val x3752_0 = Pipeline(name="x3752_0",parent=x3761) { implicit CU => 
      val x3746 = CU.temp
      val x3745 =  ScalarBuffer().wtPort(x3745_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3752_unit = CounterChain(name = "x3752_unit", ctr16)
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
      val ctr17 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3755 = CounterChain(name = "x3755", ctr17)
      var stage: List[Stage] = Nil
    }
    val x3780 = StreamController(name="x3780",parent=x4150) { implicit CU => 
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3780_unit = CounterChain(name = "x3780_unit", ctr18)
    }
    val x3771_0 = Pipeline(name="x3771_0",parent=x3780) { implicit CU => 
      val x3765 = CU.temp
      val x3764 =  ScalarBuffer().wtPort(x3764_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3771_unit = CounterChain(name = "x3771_unit", ctr19)
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
      val ctr20 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3774 = CounterChain(name = "x3774", ctr20)
      var stage: List[Stage] = Nil
    }
    val x3800 = StreamController(name="x3800",parent=x4150) { implicit CU => 
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3800_unit = CounterChain(name = "x3800_unit", ctr21)
    }
    val x3791_0 = Pipeline(name="x3791_0",parent=x3800) { implicit CU => 
      val x3785 = CU.temp
      val x3784 =  ScalarBuffer().wtPort(x3784_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3791_unit = CounterChain(name = "x3791_unit", ctr22)
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
      val ctr23 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3794 = CounterChain(name = "x3794", ctr23)
      var stage: List[Stage] = Nil
    }
    val x3819 = StreamController(name="x3819",parent=x4150) { implicit CU => 
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3819_unit = CounterChain(name = "x3819_unit", ctr24)
    }
    val x3810_0 = Pipeline(name="x3810_0",parent=x3819) { implicit CU => 
      val x3804 = CU.temp
      val x3803 =  ScalarBuffer().wtPort(x3803_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3810_unit = CounterChain(name = "x3810_unit", ctr25)
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
      val ctr26 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3813 = CounterChain(name = "x3813", ctr26)
      var stage: List[Stage] = Nil
    }
    val x3838 = StreamController(name="x3838",parent=x4150) { implicit CU => 
      val ctr27 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3838_unit = CounterChain(name = "x3838_unit", ctr27)
    }
    val x3829_0 = Pipeline(name="x3829_0",parent=x3838) { implicit CU => 
      val x3823 = CU.temp
      val x3822 =  ScalarBuffer().wtPort(x3822_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3829_unit = CounterChain(name = "x3829_unit", ctr28)
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
      val ctr29 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3832 = CounterChain(name = "x3832", ctr29)
      var stage: List[Stage] = Nil
    }
    val x3857 = StreamController(name="x3857",parent=x4150) { implicit CU => 
      val ctr30 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3857_unit = CounterChain(name = "x3857_unit", ctr30)
    }
    val x3848_0 = Pipeline(name="x3848_0",parent=x3857) { implicit CU => 
      val x3842 = CU.temp
      val x3841 =  ScalarBuffer().wtPort(x3841_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3848_unit = CounterChain(name = "x3848_unit", ctr31)
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
      val ctr32 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3851 = CounterChain(name = "x3851", ctr32)
      var stage: List[Stage] = Nil
    }
    val x3876 = StreamController(name="x3876",parent=x4150) { implicit CU => 
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3876_unit = CounterChain(name = "x3876_unit", ctr33)
    }
    val x3867_0 = Pipeline(name="x3867_0",parent=x3876) { implicit CU => 
      val x3861 = CU.temp
      val x3860 =  ScalarBuffer().wtPort(x3860_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3867_unit = CounterChain(name = "x3867_unit", ctr34)
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
      val ctr35 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3870 = CounterChain(name = "x3870", ctr35)
      var stage: List[Stage] = Nil
    }
    val x3895 = StreamController(name="x3895",parent=x4150) { implicit CU => 
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3895_unit = CounterChain(name = "x3895_unit", ctr36)
    }
    val x3886_0 = Pipeline(name="x3886_0",parent=x3895) { implicit CU => 
      val x3880 = CU.temp
      val x3879 =  ScalarBuffer().wtPort(x3879_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3886_unit = CounterChain(name = "x3886_unit", ctr37)
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
      val ctr38 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3889 = CounterChain(name = "x3889", ctr38)
      var stage: List[Stage] = Nil
    }
    val x3994 = StreamController(name="x3994",parent=x4150) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3899 = CounterChain(name = "x3899", ctr39)
    }
    val x3994_0 = Pipeline(name="x3994_0",parent=x3994) { implicit CU => 
      val x3909 = CU.temp
      val x3657_x3902 =  VectorFIFO(size=1).wtPort(x3657_x3902_x3994_v)
      val x3655_x3901 =  VectorFIFO(size=1).wtPort(x3655_x3901_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3655_x3901), CU.load(x3657_x3902)), op=FltDiv, results=List(x3909))
      Stage(operands=List(x3909), op=FltLog, results=List(CU.vecOut(bus_633_v)))
    }
    val x3994_1 = Pipeline(name="x3994_1",parent=x3994) { implicit CU => 
      val x3917 = CU.temp
      val x3914 = CU.temp
      val x3916 = CU.temp
      val x3915 = CU.temp
      val x3910 =  VectorFIFO(size=1).wtPort(bus_633_v)
      val x3663_x3905 =  VectorFIFO(size=1).wtPort(x3663_x3905_x3994_v)
      val x3659_x3903 =  VectorFIFO(size=1).wtPort(x3659_x3903_x3994_v)
      val x3661_x3904 =  VectorFIFO(size=1).wtPort(x3661_x3904_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3661_x3904), CU.load(x3661_x3904)), op=FltMul, results=List(x3914))
      Stage(operands=List(x3914, Const(0.5)), op=FltMul, results=List(x3915))
      Stage(operands=List(CU.load(x3659_x3903), x3915), op=FltAdd, results=List(x3916))
      Stage(operands=List(x3916, CU.load(x3663_x3905)), op=FltMul, results=List(x3917))
      Stage(operands=List(x3917, CU.load(x3910)), op=FltAdd, results=List(CU.vecOut(bus_639_v)))
    }
    val x3994_2 = Pipeline(name="x3994_2",parent=x3994) { implicit CU => 
      val x3919 = CU.temp
      val x3663_x3905 =  VectorFIFO(size=1).wtPort(x3663_x3905_x3994_v)
      val x3661_x3904 =  VectorFIFO(size=1).wtPort(x3661_x3904_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3663_x3905)), op=FltSqr, results=List(x3919))
      Stage(operands=List(CU.load(x3661_x3904), x3919), op=FltMul, results=List(CU.vecOut(bus_641_v)))
    }
    val x3994_3 = Pipeline(name="x3994_3",parent=x3994) { implicit CU => 
      val x3921 = CU.temp
      val x3920 =  VectorFIFO(size=1).wtPort(bus_641_v)
      val x3918 =  VectorFIFO(size=1).wtPort(bus_639_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3920), CU.load(x3920)), op=FltMul, results=List(x3921))
      Stage(operands=List(CU.load(x3918), x3921), op=FltDiv, results=List(CU.vecOut(bus_643_v)))
    }
    val x3994_4 = Pipeline(name="x3994_4",parent=x3994) { implicit CU => 
      val x3922 =  VectorFIFO(size=1).wtPort(bus_643_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3922)), op=FltAbs, results=List(CU.vecOut(bus_644_v)))
    }
    val x3994_5 = Pipeline(name="x3994_5",parent=x3994) { implicit CU => 
      val x3924 = CU.temp
      val x3926 = CU.temp
      val x3925 = CU.temp
      val x3923 =  VectorFIFO(size=1).wtPort(bus_644_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3923), CU.load(x3923)), op=FltMul, results=List(x3924))
      Stage(operands=List(x3924, Const(-0.05)), op=FltMul, results=List(x3925))
      Stage(operands=List(x3925), op=FltExp, results=List(x3926))
      Stage(operands=List(x3926, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_650_v)))
    }
    val x3994_6 = Pipeline(name="x3994_6",parent=x3994) { implicit CU => 
      val x3928 = CU.temp
      val x3929 = CU.temp
      val x3923 =  VectorFIFO(size=1).wtPort(bus_644_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3923), Const(0.2316419)), op=FltMul, results=List(x3928))
      Stage(operands=List(x3928, Const(1)), op=FltAdd, results=List(x3929))
      Stage(operands=List(Const(1), x3929), op=FltDiv, results=List(CU.vecOut(bus_655_v)))
    }
    val x3994_7 = Pipeline(name="x3994_7",parent=x3994) { implicit CU => 
      val x3930 =  VectorFIFO(size=1).wtPort(bus_655_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3930), Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_657_v)))
    }
    val x3994_8 = Pipeline(name="x3994_8",parent=x3994) { implicit CU => 
      val x3930 =  VectorFIFO(size=1).wtPort(bus_655_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3930), CU.load(x3930)), op=FltMul, results=List(CU.vecOut(bus_658_v)))
    }
    val x3994_9 = Pipeline(name="x3994_9",parent=x3994) { implicit CU => 
      val x3932 =  VectorFIFO(size=1).wtPort(bus_658_v)
      val x3930 =  VectorFIFO(size=1).wtPort(bus_655_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3932), CU.load(x3930)), op=FltMul, results=List(CU.vecOut(bus_659_v)))
    }
    val x3994_10 = Pipeline(name="x3994_10",parent=x3994) { implicit CU => 
      val x3933 =  VectorFIFO(size=1).wtPort(bus_659_v)
      val x3930 =  VectorFIFO(size=1).wtPort(bus_655_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3933), CU.load(x3930)), op=FltMul, results=List(CU.vecOut(bus_660_v)))
    }
    val x3994_11 = Pipeline(name="x3994_11",parent=x3994) { implicit CU => 
      val x3935 = CU.temp
      val x3934 =  VectorFIFO(size=1).wtPort(bus_660_v)
      val x3930 =  VectorFIFO(size=1).wtPort(bus_655_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3934), CU.load(x3930)), op=FltMul, results=List(x3935))
      Stage(operands=List(x3935, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_663_v)))
    }
    val x3994_12 = Pipeline(name="x3994_12",parent=x3994) { implicit CU => 
      val x3938 = CU.temp
      val x3939 = CU.temp
      val x3941 = CU.temp
      val x3937 = CU.temp
      val x3940 = CU.temp
      val x3936 =  VectorFIFO(size=1).wtPort(bus_663_v)
      val x3933 =  VectorFIFO(size=1).wtPort(bus_659_v)
      val x3932 =  VectorFIFO(size=1).wtPort(bus_658_v)
      val x3934 =  VectorFIFO(size=1).wtPort(bus_660_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3934), Const(-1.8212559)), op=FltMul, results=List(x3937))
      Stage(operands=List(CU.load(x3932), Const(-0.35656378)), op=FltMul, results=List(x3938))
      Stage(operands=List(CU.load(x3933), Const(1.7814779)), op=FltMul, results=List(x3939))
      Stage(operands=List(x3938, x3939), op=FltAdd, results=List(x3940))
      Stage(operands=List(x3940, x3937), op=FltAdd, results=List(x3941))
      Stage(operands=List(x3941, CU.load(x3936)), op=FltAdd, results=List(CU.vecOut(bus_672_v)))
    }
    val x3994_13 = Pipeline(name="x3994_13",parent=x3994) { implicit CU => 
      val x3944 = CU.temp
      val x3945 = CU.temp
      val x3947 = CU.temp
      val x3946 = CU.temp
      val x3943 = CU.temp
      val x3922 =  VectorFIFO(size=1).wtPort(bus_643_v)
      val x3942 =  VectorFIFO(size=1).wtPort(bus_672_v)
      val x3927 =  VectorFIFO(size=1).wtPort(bus_650_v)
      val x3931 =  VectorFIFO(size=1).wtPort(bus_657_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3942), CU.load(x3931)), op=FltAdd, results=List(x3943))
      Stage(operands=List(x3943, CU.load(x3927)), op=FltMul, results=List(x3944))
      Stage(operands=List(x3944), op=FltNeg, results=List(x3945))
      Stage(operands=List(x3945, Const(1)), op=FltAdd, results=List(x3946))
      Stage(operands=List(CU.load(x3922), Const(0)), op=FltLt, results=List(x3947))
      Stage(operands=List(x3947, x3944, x3946), op=Mux, results=List(CU.vecOut(bus_679_v)))
    }
    val x3994_14 = Pipeline(name="x3994_14",parent=x3994) { implicit CU => 
      val x3655_x3901 =  VectorFIFO(size=1).wtPort(x3655_x3901_x3994_v)
      val x3948 =  VectorFIFO(size=1).wtPort(bus_679_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3655_x3901), CU.load(x3948)), op=FltMul, results=List(CU.vecOut(bus_680_v)))
    }
    val x3994_15 = Pipeline(name="x3994_15",parent=x3994) { implicit CU => 
      val x3920 =  VectorFIFO(size=1).wtPort(bus_641_v)
      val x3922 =  VectorFIFO(size=1).wtPort(bus_643_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3922), CU.load(x3920)), op=FltSub, results=List(CU.vecOut(bus_681_v)))
    }
    val x3994_16 = Pipeline(name="x3994_16",parent=x3994) { implicit CU => 
      val x3950 =  VectorFIFO(size=1).wtPort(bus_681_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3950)), op=FltAbs, results=List(CU.vecOut(bus_682_v)))
    }
    val x3994_17 = Pipeline(name="x3994_17",parent=x3994) { implicit CU => 
      val x3954 = CU.temp
      val x3953 = CU.temp
      val x3952 = CU.temp
      val x3951 =  VectorFIFO(size=1).wtPort(bus_682_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3951), CU.load(x3951)), op=FltMul, results=List(x3952))
      Stage(operands=List(x3952, Const(-0.05)), op=FltMul, results=List(x3953))
      Stage(operands=List(x3953), op=FltExp, results=List(x3954))
      Stage(operands=List(x3954, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_686_v)))
    }
    val x3994_18 = Pipeline(name="x3994_18",parent=x3994) { implicit CU => 
      val x3957 = CU.temp
      val x3956 = CU.temp
      val x3951 =  VectorFIFO(size=1).wtPort(bus_682_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3951), Const(0.2316419)), op=FltMul, results=List(x3956))
      Stage(operands=List(x3956, Const(1)), op=FltAdd, results=List(x3957))
      Stage(operands=List(Const(1), x3957), op=FltDiv, results=List(CU.vecOut(bus_689_v)))
    }
    val x3994_19 = Pipeline(name="x3994_19",parent=x3994) { implicit CU => 
      val x3958 =  VectorFIFO(size=1).wtPort(bus_689_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3958), Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_690_v)))
    }
    val x3994_20 = Pipeline(name="x3994_20",parent=x3994) { implicit CU => 
      val x3958 =  VectorFIFO(size=1).wtPort(bus_689_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3958), CU.load(x3958)), op=FltMul, results=List(CU.vecOut(bus_691_v)))
    }
    val x3994_21 = Pipeline(name="x3994_21",parent=x3994) { implicit CU => 
      val x3958 =  VectorFIFO(size=1).wtPort(bus_689_v)
      val x3960 =  VectorFIFO(size=1).wtPort(bus_691_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3960), CU.load(x3958)), op=FltMul, results=List(CU.vecOut(bus_692_v)))
    }
    val x3994_22 = Pipeline(name="x3994_22",parent=x3994) { implicit CU => 
      val x3958 =  VectorFIFO(size=1).wtPort(bus_689_v)
      val x3961 =  VectorFIFO(size=1).wtPort(bus_692_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3961), CU.load(x3958)), op=FltMul, results=List(CU.vecOut(bus_693_v)))
    }
    val x3994_23 = Pipeline(name="x3994_23",parent=x3994) { implicit CU => 
      val x3963 = CU.temp
      val x3958 =  VectorFIFO(size=1).wtPort(bus_689_v)
      val x3962 =  VectorFIFO(size=1).wtPort(bus_693_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3962), CU.load(x3958)), op=FltMul, results=List(x3963))
      Stage(operands=List(x3963, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_695_v)))
    }
    val x3994_24 = Pipeline(name="x3994_24",parent=x3994) { implicit CU => 
      val x3966 = CU.temp
      val x3969 = CU.temp
      val x3965 = CU.temp
      val x3967 = CU.temp
      val x3968 = CU.temp
      val x3964 =  VectorFIFO(size=1).wtPort(bus_695_v)
      val x3961 =  VectorFIFO(size=1).wtPort(bus_692_v)
      val x3962 =  VectorFIFO(size=1).wtPort(bus_693_v)
      val x3960 =  VectorFIFO(size=1).wtPort(bus_691_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3962), Const(-1.8212559)), op=FltMul, results=List(x3965))
      Stage(operands=List(CU.load(x3960), Const(-0.35656378)), op=FltMul, results=List(x3966))
      Stage(operands=List(CU.load(x3961), Const(1.7814779)), op=FltMul, results=List(x3967))
      Stage(operands=List(x3966, x3967), op=FltAdd, results=List(x3968))
      Stage(operands=List(x3968, x3965), op=FltAdd, results=List(x3969))
      Stage(operands=List(x3969, CU.load(x3964)), op=FltAdd, results=List(CU.vecOut(bus_701_v)))
    }
    val x3994_25 = Pipeline(name="x3994_25",parent=x3994) { implicit CU => 
      val x3975 = CU.temp
      val x3974 = CU.temp
      val x3972 = CU.temp
      val x3971 = CU.temp
      val x3973 = CU.temp
      val x3955 =  VectorFIFO(size=1).wtPort(bus_686_v)
      val x3970 =  VectorFIFO(size=1).wtPort(bus_701_v)
      val x3959 =  VectorFIFO(size=1).wtPort(bus_690_v)
      val x3950 =  VectorFIFO(size=1).wtPort(bus_681_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3970), CU.load(x3959)), op=FltAdd, results=List(x3971))
      Stage(operands=List(x3971, CU.load(x3955)), op=FltMul, results=List(x3972))
      Stage(operands=List(x3972), op=FltNeg, results=List(x3973))
      Stage(operands=List(x3973, Const(1)), op=FltAdd, results=List(x3974))
      Stage(operands=List(CU.load(x3950), Const(0)), op=FltLt, results=List(x3975))
      Stage(operands=List(x3975, x3972, x3974), op=Mux, results=List(CU.vecOut(bus_707_v)))
    }
    val x3994_26 = Pipeline(name="x3994_26",parent=x3994) { implicit CU => 
      val x3977 = CU.temp
      val x3978 = CU.temp
      val x3979 = CU.temp
      val x3663_x3905 =  VectorFIFO(size=1).wtPort(x3663_x3905_x3994_v)
      val x3657_x3902 =  VectorFIFO(size=1).wtPort(x3657_x3902_x3994_v)
      val x3659_x3903 =  VectorFIFO(size=1).wtPort(x3659_x3903_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3659_x3903)), op=FltNeg, results=List(x3977))
      Stage(operands=List(x3977, CU.load(x3663_x3905)), op=FltMul, results=List(x3978))
      Stage(operands=List(x3978), op=FltExp, results=List(x3979))
      Stage(operands=List(CU.load(x3657_x3902), x3979), op=FltMul, results=List(CU.vecOut(bus_711_v)))
    }
    val x3994_27 = Pipeline(name="x3994_27",parent=x3994) { implicit CU => 
      val x3981 = CU.temp
      val x3980 =  VectorFIFO(size=1).wtPort(bus_711_v)
      val x3976 =  VectorFIFO(size=1).wtPort(bus_707_v)
      val x3949 =  VectorFIFO(size=1).wtPort(bus_680_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3980), CU.load(x3976)), op=FltMul, results=List(x3981))
      Stage(operands=List(CU.load(x3949), x3981), op=FltSub, results=List(CU.vecOut(bus_713_v)))
    }
    val x3994_28 = Pipeline(name="x3994_28",parent=x3994) { implicit CU => 
      val x3984 = CU.temp
      val x3983 = CU.temp
      val x3980 =  VectorFIFO(size=1).wtPort(bus_711_v)
      val x3976 =  VectorFIFO(size=1).wtPort(bus_707_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3976)), op=FltNeg, results=List(x3983))
      Stage(operands=List(x3983, Const(1)), op=FltAdd, results=List(x3984))
      Stage(operands=List(CU.load(x3980), x3984), op=FltMul, results=List(CU.vecOut(bus_716_v)))
    }
    val x3994_29 = Pipeline(name="x3994_29",parent=x3994) { implicit CU => 
      val x3987 = CU.temp
      val x3988 = CU.temp
      val x3986 = CU.temp
      val x3985 =  VectorFIFO(size=1).wtPort(bus_716_v)
      val x3655_x3901 =  VectorFIFO(size=1).wtPort(x3655_x3901_x3994_v)
      val x3948 =  VectorFIFO(size=1).wtPort(bus_679_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3948)), op=FltNeg, results=List(x3986))
      Stage(operands=List(x3986, Const(1)), op=FltAdd, results=List(x3987))
      Stage(operands=List(CU.load(x3655_x3901), x3987), op=FltMul, results=List(x3988))
      Stage(operands=List(CU.load(x3985), x3988), op=FltSub, results=List(CU.vecOut(bus_720_v)))
    }
    val x3994_30 = Pipeline(name="x3994_30",parent=x3994) { implicit CU => 
      val x3991 = CU.temp
      val x3982 =  VectorFIFO(size=1).wtPort(bus_713_v)
      val x3989 =  VectorFIFO(size=1).wtPort(bus_720_v)
      val x3653_x3906 =  VectorFIFO(size=1).wtPort(x3653_x3906_x3994_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3653_x3906), Const(0)), op=FixEql, results=List(x3991))
      Stage(operands=List(x3991, CU.load(x3989), CU.load(x3982)), op=Mux, results=List(CU.vecOut(x3665_x3993_v)))
    }
    val x4091 = StreamController(name="x4091",parent=x4150) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x3996 = CounterChain(name = "x3996", ctr40)
    }
    val x4091_0 = Pipeline(name="x4091_0",parent=x4091) { implicit CU => 
      val x4006 = CU.temp
      val x3656_x3998 =  VectorFIFO(size=1).wtPort(x3656_x3998_x4091_v)
      val x3658_x3999 =  VectorFIFO(size=1).wtPort(x3658_x3999_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3656_x3998), CU.load(x3658_x3999)), op=FltDiv, results=List(x4006))
      Stage(operands=List(x4006), op=FltLog, results=List(CU.vecOut(bus_725_v)))
    }
    val x4091_1 = Pipeline(name="x4091_1",parent=x4091) { implicit CU => 
      val x4011 = CU.temp
      val x4012 = CU.temp
      val x4013 = CU.temp
      val x4014 = CU.temp
      val x4007 =  VectorFIFO(size=1).wtPort(bus_725_v)
      val x3662_x4001 =  VectorFIFO(size=1).wtPort(x3662_x4001_x4091_v)
      val x3664_x4002 =  VectorFIFO(size=1).wtPort(x3664_x4002_x4091_v)
      val x3660_x4000 =  VectorFIFO(size=1).wtPort(x3660_x4000_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3662_x4001), CU.load(x3662_x4001)), op=FltMul, results=List(x4011))
      Stage(operands=List(x4011, Const(0.5)), op=FltMul, results=List(x4012))
      Stage(operands=List(CU.load(x3660_x4000), x4012), op=FltAdd, results=List(x4013))
      Stage(operands=List(x4013, CU.load(x3664_x4002)), op=FltMul, results=List(x4014))
      Stage(operands=List(x4014, CU.load(x4007)), op=FltAdd, results=List(CU.vecOut(bus_731_v)))
    }
    val x4091_2 = Pipeline(name="x4091_2",parent=x4091) { implicit CU => 
      val x4016 = CU.temp
      val x3662_x4001 =  VectorFIFO(size=1).wtPort(x3662_x4001_x4091_v)
      val x3664_x4002 =  VectorFIFO(size=1).wtPort(x3664_x4002_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3664_x4002)), op=FltSqr, results=List(x4016))
      Stage(operands=List(CU.load(x3662_x4001), x4016), op=FltMul, results=List(CU.vecOut(bus_733_v)))
    }
    val x4091_3 = Pipeline(name="x4091_3",parent=x4091) { implicit CU => 
      val x4018 = CU.temp
      val x4015 =  VectorFIFO(size=1).wtPort(bus_731_v)
      val x4017 =  VectorFIFO(size=1).wtPort(bus_733_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4017), CU.load(x4017)), op=FltMul, results=List(x4018))
      Stage(operands=List(CU.load(x4015), x4018), op=FltDiv, results=List(CU.vecOut(bus_735_v)))
    }
    val x4091_4 = Pipeline(name="x4091_4",parent=x4091) { implicit CU => 
      val x4019 =  VectorFIFO(size=1).wtPort(bus_735_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4019)), op=FltAbs, results=List(CU.vecOut(bus_736_v)))
    }
    val x4091_5 = Pipeline(name="x4091_5",parent=x4091) { implicit CU => 
      val x4023 = CU.temp
      val x4022 = CU.temp
      val x4021 = CU.temp
      val x4020 =  VectorFIFO(size=1).wtPort(bus_736_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4020), CU.load(x4020)), op=FltMul, results=List(x4021))
      Stage(operands=List(x4021, Const(-0.05)), op=FltMul, results=List(x4022))
      Stage(operands=List(x4022), op=FltExp, results=List(x4023))
      Stage(operands=List(x4023, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_742_v)))
    }
    val x4091_6 = Pipeline(name="x4091_6",parent=x4091) { implicit CU => 
      val x4026 = CU.temp
      val x4025 = CU.temp
      val x4020 =  VectorFIFO(size=1).wtPort(bus_736_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4020), Const(0.2316419)), op=FltMul, results=List(x4025))
      Stage(operands=List(x4025, Const(1)), op=FltAdd, results=List(x4026))
      Stage(operands=List(Const(1), x4026), op=FltDiv, results=List(CU.vecOut(bus_747_v)))
    }
    val x4091_7 = Pipeline(name="x4091_7",parent=x4091) { implicit CU => 
      val x4027 =  VectorFIFO(size=1).wtPort(bus_747_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4027), Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_749_v)))
    }
    val x4091_8 = Pipeline(name="x4091_8",parent=x4091) { implicit CU => 
      val x4027 =  VectorFIFO(size=1).wtPort(bus_747_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4027), CU.load(x4027)), op=FltMul, results=List(CU.vecOut(bus_750_v)))
    }
    val x4091_9 = Pipeline(name="x4091_9",parent=x4091) { implicit CU => 
      val x4027 =  VectorFIFO(size=1).wtPort(bus_747_v)
      val x4029 =  VectorFIFO(size=1).wtPort(bus_750_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4029), CU.load(x4027)), op=FltMul, results=List(CU.vecOut(bus_751_v)))
    }
    val x4091_10 = Pipeline(name="x4091_10",parent=x4091) { implicit CU => 
      val x4027 =  VectorFIFO(size=1).wtPort(bus_747_v)
      val x4030 =  VectorFIFO(size=1).wtPort(bus_751_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4030), CU.load(x4027)), op=FltMul, results=List(CU.vecOut(bus_752_v)))
    }
    val x4091_11 = Pipeline(name="x4091_11",parent=x4091) { implicit CU => 
      val x4032 = CU.temp
      val x4027 =  VectorFIFO(size=1).wtPort(bus_747_v)
      val x4031 =  VectorFIFO(size=1).wtPort(bus_752_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4031), CU.load(x4027)), op=FltMul, results=List(x4032))
      Stage(operands=List(x4032, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_755_v)))
    }
    val x4091_12 = Pipeline(name="x4091_12",parent=x4091) { implicit CU => 
      val x4038 = CU.temp
      val x4034 = CU.temp
      val x4035 = CU.temp
      val x4036 = CU.temp
      val x4037 = CU.temp
      val x4033 =  VectorFIFO(size=1).wtPort(bus_755_v)
      val x4030 =  VectorFIFO(size=1).wtPort(bus_751_v)
      val x4029 =  VectorFIFO(size=1).wtPort(bus_750_v)
      val x4031 =  VectorFIFO(size=1).wtPort(bus_752_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4031), Const(-1.8212559)), op=FltMul, results=List(x4034))
      Stage(operands=List(CU.load(x4029), Const(-0.35656378)), op=FltMul, results=List(x4035))
      Stage(operands=List(CU.load(x4030), Const(1.7814779)), op=FltMul, results=List(x4036))
      Stage(operands=List(x4035, x4036), op=FltAdd, results=List(x4037))
      Stage(operands=List(x4037, x4034), op=FltAdd, results=List(x4038))
      Stage(operands=List(x4038, CU.load(x4033)), op=FltAdd, results=List(CU.vecOut(bus_764_v)))
    }
    val x4091_13 = Pipeline(name="x4091_13",parent=x4091) { implicit CU => 
      val x4040 = CU.temp
      val x4043 = CU.temp
      val x4044 = CU.temp
      val x4041 = CU.temp
      val x4042 = CU.temp
      val x4039 =  VectorFIFO(size=1).wtPort(bus_764_v)
      val x4019 =  VectorFIFO(size=1).wtPort(bus_735_v)
      val x4028 =  VectorFIFO(size=1).wtPort(bus_749_v)
      val x4024 =  VectorFIFO(size=1).wtPort(bus_742_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4039), CU.load(x4028)), op=FltAdd, results=List(x4040))
      Stage(operands=List(x4040, CU.load(x4024)), op=FltMul, results=List(x4041))
      Stage(operands=List(x4041), op=FltNeg, results=List(x4042))
      Stage(operands=List(x4042, Const(1)), op=FltAdd, results=List(x4043))
      Stage(operands=List(CU.load(x4019), Const(0)), op=FltLt, results=List(x4044))
      Stage(operands=List(x4044, x4041, x4043), op=Mux, results=List(CU.vecOut(bus_771_v)))
    }
    val x4091_14 = Pipeline(name="x4091_14",parent=x4091) { implicit CU => 
      val x3656_x3998 =  VectorFIFO(size=1).wtPort(x3656_x3998_x4091_v)
      val x4045 =  VectorFIFO(size=1).wtPort(bus_771_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3656_x3998), CU.load(x4045)), op=FltMul, results=List(CU.vecOut(bus_772_v)))
    }
    val x4091_15 = Pipeline(name="x4091_15",parent=x4091) { implicit CU => 
      val x4017 =  VectorFIFO(size=1).wtPort(bus_733_v)
      val x4019 =  VectorFIFO(size=1).wtPort(bus_735_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4019), CU.load(x4017)), op=FltSub, results=List(CU.vecOut(bus_773_v)))
    }
    val x4091_16 = Pipeline(name="x4091_16",parent=x4091) { implicit CU => 
      val x4047 =  VectorFIFO(size=1).wtPort(bus_773_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4047)), op=FltAbs, results=List(CU.vecOut(bus_774_v)))
    }
    val x4091_17 = Pipeline(name="x4091_17",parent=x4091) { implicit CU => 
      val x4049 = CU.temp
      val x4050 = CU.temp
      val x4051 = CU.temp
      val x4048 =  VectorFIFO(size=1).wtPort(bus_774_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4048), CU.load(x4048)), op=FltMul, results=List(x4049))
      Stage(operands=List(x4049, Const(-0.05)), op=FltMul, results=List(x4050))
      Stage(operands=List(x4050), op=FltExp, results=List(x4051))
      Stage(operands=List(x4051, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_778_v)))
    }
    val x4091_18 = Pipeline(name="x4091_18",parent=x4091) { implicit CU => 
      val x4053 = CU.temp
      val x4054 = CU.temp
      val x4048 =  VectorFIFO(size=1).wtPort(bus_774_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4048), Const(0.2316419)), op=FltMul, results=List(x4053))
      Stage(operands=List(x4053, Const(1)), op=FltAdd, results=List(x4054))
      Stage(operands=List(Const(1), x4054), op=FltDiv, results=List(CU.vecOut(bus_781_v)))
    }
    val x4091_19 = Pipeline(name="x4091_19",parent=x4091) { implicit CU => 
      val x4055 =  VectorFIFO(size=1).wtPort(bus_781_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4055), Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_782_v)))
    }
    val x4091_20 = Pipeline(name="x4091_20",parent=x4091) { implicit CU => 
      val x4055 =  VectorFIFO(size=1).wtPort(bus_781_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4055), CU.load(x4055)), op=FltMul, results=List(CU.vecOut(bus_783_v)))
    }
    val x4091_21 = Pipeline(name="x4091_21",parent=x4091) { implicit CU => 
      val x4055 =  VectorFIFO(size=1).wtPort(bus_781_v)
      val x4057 =  VectorFIFO(size=1).wtPort(bus_783_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4057), CU.load(x4055)), op=FltMul, results=List(CU.vecOut(bus_784_v)))
    }
    val x4091_22 = Pipeline(name="x4091_22",parent=x4091) { implicit CU => 
      val x4055 =  VectorFIFO(size=1).wtPort(bus_781_v)
      val x4058 =  VectorFIFO(size=1).wtPort(bus_784_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4058), CU.load(x4055)), op=FltMul, results=List(CU.vecOut(bus_785_v)))
    }
    val x4091_23 = Pipeline(name="x4091_23",parent=x4091) { implicit CU => 
      val x4060 = CU.temp
      val x4055 =  VectorFIFO(size=1).wtPort(bus_781_v)
      val x4059 =  VectorFIFO(size=1).wtPort(bus_785_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4059), CU.load(x4055)), op=FltMul, results=List(x4060))
      Stage(operands=List(x4060, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_787_v)))
    }
    val x4091_24 = Pipeline(name="x4091_24",parent=x4091) { implicit CU => 
      val x4064 = CU.temp
      val x4063 = CU.temp
      val x4065 = CU.temp
      val x4066 = CU.temp
      val x4062 = CU.temp
      val x4057 =  VectorFIFO(size=1).wtPort(bus_783_v)
      val x4061 =  VectorFIFO(size=1).wtPort(bus_787_v)
      val x4059 =  VectorFIFO(size=1).wtPort(bus_785_v)
      val x4058 =  VectorFIFO(size=1).wtPort(bus_784_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4059), Const(-1.8212559)), op=FltMul, results=List(x4062))
      Stage(operands=List(CU.load(x4057), Const(-0.35656378)), op=FltMul, results=List(x4063))
      Stage(operands=List(CU.load(x4058), Const(1.7814779)), op=FltMul, results=List(x4064))
      Stage(operands=List(x4063, x4064), op=FltAdd, results=List(x4065))
      Stage(operands=List(x4065, x4062), op=FltAdd, results=List(x4066))
      Stage(operands=List(x4066, CU.load(x4061)), op=FltAdd, results=List(CU.vecOut(bus_793_v)))
    }
    val x4091_25 = Pipeline(name="x4091_25",parent=x4091) { implicit CU => 
      val x4072 = CU.temp
      val x4069 = CU.temp
      val x4071 = CU.temp
      val x4068 = CU.temp
      val x4070 = CU.temp
      val x4056 =  VectorFIFO(size=1).wtPort(bus_782_v)
      val x4052 =  VectorFIFO(size=1).wtPort(bus_778_v)
      val x4067 =  VectorFIFO(size=1).wtPort(bus_793_v)
      val x4047 =  VectorFIFO(size=1).wtPort(bus_773_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4067), CU.load(x4056)), op=FltAdd, results=List(x4068))
      Stage(operands=List(x4068, CU.load(x4052)), op=FltMul, results=List(x4069))
      Stage(operands=List(x4069), op=FltNeg, results=List(x4070))
      Stage(operands=List(x4070, Const(1)), op=FltAdd, results=List(x4071))
      Stage(operands=List(CU.load(x4047), Const(0)), op=FltLt, results=List(x4072))
      Stage(operands=List(x4072, x4069, x4071), op=Mux, results=List(CU.vecOut(bus_799_v)))
    }
    val x4091_26 = Pipeline(name="x4091_26",parent=x4091) { implicit CU => 
      val x4074 = CU.temp
      val x4076 = CU.temp
      val x4075 = CU.temp
      val x3664_x4002 =  VectorFIFO(size=1).wtPort(x3664_x4002_x4091_v)
      val x3658_x3999 =  VectorFIFO(size=1).wtPort(x3658_x3999_x4091_v)
      val x3660_x4000 =  VectorFIFO(size=1).wtPort(x3660_x4000_x4091_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3660_x4000)), op=FltNeg, results=List(x4074))
      Stage(operands=List(x4074, CU.load(x3664_x4002)), op=FltMul, results=List(x4075))
      Stage(operands=List(x4075), op=FltExp, results=List(x4076))
      Stage(operands=List(CU.load(x3658_x3999), x4076), op=FltMul, results=List(CU.vecOut(bus_803_v)))
    }
    val x4091_27 = Pipeline(name="x4091_27",parent=x4091) { implicit CU => 
      val x4078 = CU.temp
      val x4077 =  VectorFIFO(size=1).wtPort(bus_803_v)
      val x4046 =  VectorFIFO(size=1).wtPort(bus_772_v)
      val x4073 =  VectorFIFO(size=1).wtPort(bus_799_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4077), CU.load(x4073)), op=FltMul, results=List(x4078))
      Stage(operands=List(CU.load(x4046), x4078), op=FltSub, results=List(CU.vecOut(bus_805_v)))
    }
    val x4091_28 = Pipeline(name="x4091_28",parent=x4091) { implicit CU => 
      val x4081 = CU.temp
      val x4080 = CU.temp
      val x4077 =  VectorFIFO(size=1).wtPort(bus_803_v)
      val x4073 =  VectorFIFO(size=1).wtPort(bus_799_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4073)), op=FltNeg, results=List(x4080))
      Stage(operands=List(x4080, Const(1)), op=FltAdd, results=List(x4081))
      Stage(operands=List(CU.load(x4077), x4081), op=FltMul, results=List(CU.vecOut(bus_808_v)))
    }
    val x4091_29 = Pipeline(name="x4091_29",parent=x4091) { implicit CU => 
      val x4084 = CU.temp
      val x4083 = CU.temp
      val x4085 = CU.temp
      val x3656_x3998 =  VectorFIFO(size=1).wtPort(x3656_x3998_x4091_v)
      val x4082 =  VectorFIFO(size=1).wtPort(bus_808_v)
      val x4045 =  VectorFIFO(size=1).wtPort(bus_771_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4045)), op=FltNeg, results=List(x4083))
      Stage(operands=List(x4083, Const(1)), op=FltAdd, results=List(x4084))
      Stage(operands=List(CU.load(x3656_x3998), x4084), op=FltMul, results=List(x4085))
      Stage(operands=List(CU.load(x4082), x4085), op=FltSub, results=List(CU.vecOut(bus_812_v)))
    }
    val x4091_30 = Pipeline(name="x4091_30",parent=x4091) { implicit CU => 
      val x4088 = CU.temp
      val x3654_x4003 =  VectorFIFO(size=1).wtPort(x3654_x4003_x4091_v)
      val x4079 =  VectorFIFO(size=1).wtPort(bus_805_v)
      val x4086 =  VectorFIFO(size=1).wtPort(bus_812_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3654_x4003), Const(0)), op=FixEql, results=List(x4088))
      Stage(operands=List(x4088, CU.load(x4086), CU.load(x4079)), op=Mux, results=List(CU.vecOut(x3666_x4090_v)))
    }
    val x4120 = StreamController(name="x4120",parent=x4150) { implicit CU => 
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4120_unit = CounterChain(name = "x4120_unit", ctr41)
    }
    val x4112 = Sequential(name="x4112",parent=x4120) { implicit CU => 
      val ctr42 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4112_unit = CounterChain(name = "x4112_unit", ctr42)
    }
    val x4103_0 = Pipeline(name="x4103_0",parent=x4112) { implicit CU => 
      val x4097 = CU.temp
      val x4096 =  ScalarBuffer().wtPort(x4096_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4103_unit = CounterChain(name = "x4103_unit", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x4097))
      Stage(operands=List(x4097, CU.load(x4096)), op=FixAdd, results=List(CU.scalarOut(x4093_b4210_x4102_b4212_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4093_b4211_x4102_b4213_s)))
    }
    val x4111 = Pipeline(name="x4111",parent=x4112) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4105 = CounterChain(name = "x4105", ctr44)
      var stage: List[Stage] = Nil
    }
    val x4113 = MemoryController(name="x4113",parent=x4120,offchip=x3643_oc, mctpe=TileStore) { implicit CU => 
      val x4093_b4210_x4113 =  ScalarFIFO(name="offset",size=1).wtPort(x4093_b4210_x4102_b4212_s)
      val x4093_b4211_x4113 =  ScalarFIFO(name="size",size=1).wtPort(x4093_b4211_x4102_b4213_s)
      val x4094_x4113 =  VectorFIFO(name="data",size=1).wtPort(x3665_x4107_x4111_v)
    }
    val x4148 = StreamController(name="x4148",parent=x4150) { implicit CU => 
      val ctr47 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4148_unit = CounterChain(name = "x4148_unit", ctr47)
    }
    val x4140 = Sequential(name="x4140",parent=x4148) { implicit CU => 
      val ctr48 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4140_unit = CounterChain(name = "x4140_unit", ctr48)
    }
    val x4131_0 = Pipeline(name="x4131_0",parent=x4140) { implicit CU => 
      val x4125 = CU.temp
      val x4124 =  ScalarBuffer().wtPort(x4124_argin)
      val x3652 = CounterChain.copy("x4150", "x3652")
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4131_unit = CounterChain(name = "x4131_unit", ctr49)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3652(0)), Const(4)), op=FixMul, results=List(x4125))
      Stage(operands=List(x4125, CU.load(x4124)), op=FixAdd, results=List(CU.scalarOut(x4121_b4214_x4130_b4216_s)))
      Stage(operands=List(Const(8000)), op=Bypass, results=List(CU.scalarOut(x4121_b4215_x4130_b4217_s)))
    }
    val x4139 = Pipeline(name="x4139",parent=x4140) { implicit CU => 
      val ctr50 = Counter(min=Const(0), max=Const(2000), step=Const(1), par=16) // Counter
      val x4133 = CounterChain(name = "x4133", ctr50)
      var stage: List[Stage] = Nil
    }
    val x4141 = MemoryController(name="x4141",parent=x4148,offchip=x3643_oc, mctpe=TileStore) { implicit CU => 
      val x4122_x4141 =  VectorFIFO(name="data",size=1).wtPort(x3666_x4135_x4139_v)
      val x4121_b4215_x4141 =  ScalarFIFO(name="size",size=1).wtPort(x4121_b4215_x4130_b4217_s)
      val x4121_b4214_x4141 =  ScalarFIFO(name="offset",size=1).wtPort(x4121_b4214_x4130_b4216_s)
    }
    
  }
}
