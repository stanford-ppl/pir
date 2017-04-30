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
    val x3668_x3678_data_v = Vector("x3668_x3678_data")
    val x3581_b4240_x3590_b4242_s = Scalar("x3581_b4240_x3590_b4242")
    val x3417_x3807_x3811_v = Vector("x3417_x3807_x3811")
    val x3421_x3855_x3859_v = Vector("x3421_x3855_x3859")
    val x3918_argin = ArgIn("x3918")
    val x3476_argin = ArgIn("x3476")
    val x3427_x4057_x4061_v = Vector("x3427_x4057_x4061")
    val x3425_x3977_x3981_v = Vector("x3425_x3977_x3981")
    val x3518_x3528_data_v = Vector("x3518_x3528_data")
    val x3408_x3794_x3799_v = Vector("x3408_x3794_x3799")
    val x3517_b4229_x3526_b4231_s = Scalar("x3517_b4229_x3526_b4231")
    val x3648_argin = ArgIn("x3648")
    val x3418_x3819_x3823_v = Vector("x3418_x3819_x3823")
    val x3390_argin = ArgIn("x3390")
    val x3394_oc = OffChip("x3394")
    val x3453_x3463_data_v = Vector("x3453_x3463_data")
    val x3733_x3743_data_v = Vector("x3733_x3743_data")
    val x3625_x3635_data_v = Vector("x3625_x3635_data")
    val x3603_b4244_x3612_b4246_s = Scalar("x3603_b4244_x3612_b4246")
    val x3603_b4245_x3612_b4247_s = Scalar("x3603_b4245_x3612_b4247")
    val x4035_b4317_x4050_b4319_s = Scalar("x4035_b4317_x4050_b4319")
    val x3753_b4272_x3762_b4274_s = Scalar("x3753_b4272_x3762_b4274")
    val x3581_b4241_x3590_b4243_s = Scalar("x3581_b4241_x3590_b4243")
    val x3454_argin = ArgIn("x3454")
    val x3646_b4253_x3655_b4255_s = Scalar("x3646_b4253_x3655_b4255")
    val x3425_x3810_v = Vector("x3425_x3810")
    val x4118_argin = ArgIn("x4118")
    val x3399_oc = OffChip("x3399")
    val x3958_argin = ArgIn("x3958")
    val x3604_x3614_data_v = Vector("x3604_x3614_data")
    val x3647_x3657_data_v = Vector("x3647_x3657_data")
    val x3646_b4252_x3655_b4254_s = Scalar("x3646_b4252_x3655_b4254")
    val x3495_b4224_x3504_b4226_s = Scalar("x3495_b4224_x3504_b4226")
    val x3689_b4261_x3698_b4263_s = Scalar("x3689_b4261_x3698_b4263")
    val x3667_b4256_x3676_b4258_s = Scalar("x3667_b4256_x3676_b4258")
    val x3389_argin = ArgIn("x3389")
    val x3753_b4273_x3762_b4275_s = Scalar("x3753_b4273_x3762_b4275")
    val x3732_b4268_x3741_b4270_s = Scalar("x3732_b4268_x3741_b4270")
    val x3427_x3834_v = Vector("x3427_x3834")
    val x3474_b4220_x3483_b4222_s = Scalar("x3474_b4220_x3483_b4222")
    val x3419_x3831_x3835_v = Vector("x3419_x3831_x3835")
    val x3426_x3822_v = Vector("x3426_x3822")
    val x3582_x3592_data_v = Vector("x3582_x3592_data")
    val x3429_x3858_v = Vector("x3429_x3858")
    val x3875_b4292_x3890_b4294_s = Scalar("x3875_b4292_x3890_b4294")
    val x3626_argin = ArgIn("x3626")
    val x3407_x3782_x3787_v = Vector("x3407_x3782_x3787")
    val x3409_x3806_x3811_v = Vector("x3409_x3806_x3811")
    val x4155_b4334_x4170_b4336_s = Scalar("x4155_b4334_x4170_b4336")
    val x3710_b4264_x3719_b4266_s = Scalar("x3710_b4264_x3719_b4266")
    val x3411_x3830_x3835_v = Vector("x3411_x3830_x3835")
    val x3689_b4260_x3698_b4262_s = Scalar("x3689_b4260_x3698_b4262")
    val x3423_x3786_v = Vector("x3423_x3786")
    val x3667_b4257_x3676_b4259_s = Scalar("x3667_b4257_x3676_b4259")
    val x3430_x4177_x4181_v = Vector("x3430_x4177_x4181")
    val x3496_x3506_data_v = Vector("x3496_x3506_data")
    val x3517_b4228_x3526_b4230_s = Scalar("x3517_b4228_x3526_b4230")
    val x3734_argin = ArgIn("x3734")
    val x3431_b4212_x3440_b4214_s = Scalar("x3431_b4212_x3440_b4214")
    val x4115_b4329_x4130_b4331_s = Scalar("x4115_b4329_x4130_b4331")
    val x3561_x3571_data_v = Vector("x3561_x3571_data")
    val x3429_x4137_x4141_v = Vector("x3429_x4137_x4141")
    val x3415_x3783_x3787_v = Vector("x3415_x3783_x3787")
    val x3755_argin = ArgIn("x3755")
    val x3605_argin = ArgIn("x3605")
    val x3915_b4298_x3930_b4300_s = Scalar("x3915_b4298_x3930_b4300")
    val x4038_argin = ArgIn("x4038")
    val x3712_argin = ArgIn("x3712")
    val x3424_x3937_x3941_v = Vector("x3424_x3937_x3941")
    val x3495_b4225_x3504_b4227_s = Scalar("x3495_b4225_x3504_b4227")
    val x3433_argin = ArgIn("x3433")
    val x3420_x3843_x3847_v = Vector("x3420_x3843_x3847")
    val x3710_b4265_x3719_b4267_s = Scalar("x3710_b4265_x3719_b4267")
    val x3624_b4248_x3633_b4250_s = Scalar("x3624_b4248_x3633_b4250")
    val x3538_b4233_x3547_b4235_s = Scalar("x3538_b4233_x3547_b4235")
    val x3423_x3897_x3901_v = Vector("x3423_x3897_x3901")
    val x3424_x3798_v = Vector("x3424_x3798")
    val x3497_argin = ArgIn("x3497")
    val x3538_b4232_x3547_b4234_s = Scalar("x3538_b4232_x3547_b4234")
    val x3915_b4299_x3930_b4301_s = Scalar("x3915_b4299_x3930_b4301")
    val x3878_argin = ArgIn("x3878")
    val x3560_b4237_x3569_b4239_s = Scalar("x3560_b4237_x3569_b4239")
    val x4075_b4322_x4090_b4324_s = Scalar("x4075_b4322_x4090_b4324")
    val x3416_x3795_x3799_v = Vector("x3416_x3795_x3799")
    val x3690_x3700_data_v = Vector("x3690_x3700_data")
    val x4155_b4335_x4170_b4337_s = Scalar("x4155_b4335_x4170_b4337")
    val x3428_x4097_x4101_v = Vector("x3428_x4097_x4101")
    val x3955_b4305_x3970_b4307_s = Scalar("x3955_b4305_x3970_b4307")
    val x3624_b4249_x3633_b4251_s = Scalar("x3624_b4249_x3633_b4251")
    val x3474_b4221_x3483_b4223_s = Scalar("x3474_b4221_x3483_b4223")
    val x3519_argin = ArgIn("x3519")
    val x3540_argin = ArgIn("x3540")
    val x3995_b4310_x4010_b4312_s = Scalar("x3995_b4310_x4010_b4312")
    val x4158_argin = ArgIn("x4158")
    val x4115_b4328_x4130_b4330_s = Scalar("x4115_b4328_x4130_b4330")
    val x3410_x3818_x3823_v = Vector("x3410_x3818_x3823")
    val x4078_argin = ArgIn("x4078")
    val x3539_x3549_data_v = Vector("x3539_x3549_data")
    val x3475_x3485_data_v = Vector("x3475_x3485_data")
    val x3413_x3854_x3859_v = Vector("x3413_x3854_x3859")
    val x3452_b4217_x3461_b4219_s = Scalar("x3452_b4217_x3461_b4219")
    val x4035_b4316_x4050_b4318_s = Scalar("x4035_b4316_x4050_b4318")
    val x3955_b4304_x3970_b4306_s = Scalar("x3955_b4304_x3970_b4306")
    val x3426_x4017_x4021_v = Vector("x3426_x4017_x4021")
    val x3396_oc = OffChip("x3396")
    val x4075_b4323_x4090_b4325_s = Scalar("x4075_b4323_x4090_b4325")
    val x3412_x3842_x3847_v = Vector("x3412_x3842_x3847")
    val x3583_argin = ArgIn("x3583")
    val x3711_x3721_data_v = Vector("x3711_x3721_data")
    val x3428_x3846_v = Vector("x3428_x3846")
    val x3432_x3442_data_v = Vector("x3432_x3442_data")
    val x3431_b4213_x3440_b4215_s = Scalar("x3431_b4213_x3440_b4215")
    val x3754_x3764_data_v = Vector("x3754_x3764_data")
    val x3998_argin = ArgIn("x3998")
    val x3414_x3866_x3871_v = Vector("x3414_x3866_x3871")
    val x3422_x3867_x3871_v = Vector("x3422_x3867_x3871")
    val x3560_b4236_x3569_b4238_s = Scalar("x3560_b4236_x3569_b4238")
    val x3452_b4216_x3461_b4218_s = Scalar("x3452_b4216_x3461_b4218")
    val x3732_b4269_x3741_b4271_s = Scalar("x3732_b4269_x3741_b4271")
    val x3430_x3870_v = Vector("x3430_x3870")
    val x3875_b4293_x3890_b4295_s = Scalar("x3875_b4293_x3890_b4295")
    val x3691_argin = ArgIn("x3691")
    val x3669_argin = ArgIn("x3669")
    val x3995_b4311_x4010_b4313_s = Scalar("x3995_b4311_x4010_b4313")
    val x3562_argin = ArgIn("x3562")
    val x4195 = Sequential(name="x4195",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4195_unit = CounterChain(name = "x4195_unit", ctr1)
    }
    val x4194 = MetaPipeline(name="x4194",parent=x4195) { implicit CU => 
      val x3390_x3402 =  ScalarBuffer().wtPort(x3390_argin)
      val x3389_x3404 =  ScalarBuffer().wtPort(x3389_argin)
      val ctr2 = Counter(min=Const(0), max=x3389_x3404.load, step=Const(48), par=1) // Counter
      val ctr3 = Counter(min=Const(0), max=x3390_x3402.load, step=Const(48), par=8) // Counter
      val x3406 = CounterChain(name = "x3406", ctr2, ctr3)
    }
    val x3407_dsp0 = MemoryPipeline(name="x3407_dsp0",parent="x4194") { implicit CU => 
      val x3449_x3449 =  VectorFIFO(size=1).wtPort(x3432_x3442_data_v)
      val x3444 = CounterChain.copy("x3450", "x3444")
      val x3778 = CounterChain.copy("x3787_0", "x3778")
      val x3407_x3782 =  SRAM(size=48,banking = Strided(1)).wtPort(x3449_x3449.readPort).rdPort(x3407_x3782_x3787_v).rdAddr(x3778(0)).wtAddr(x3444(0))
      var stage: List[Stage] = Nil
    }
    val x3408_dsp0 = MemoryPipeline(name="x3408_dsp0",parent="x4194") { implicit CU => 
      val x3492_x3492 =  VectorFIFO(size=1).wtPort(x3475_x3485_data_v)
      val x3487 = CounterChain.copy("x3493", "x3487")
      val x3790 = CounterChain.copy("x3799_0", "x3790")
      val x3408_x3794 =  SRAM(size=48,banking = Strided(1)).wtPort(x3492_x3492.readPort).rdPort(x3408_x3794_x3799_v).rdAddr(x3790(0)).wtAddr(x3487(0))
      var stage: List[Stage] = Nil
    }
    val x3409_dsp0 = MemoryPipeline(name="x3409_dsp0",parent="x4194") { implicit CU => 
      val x3535_x3535 =  VectorFIFO(size=1).wtPort(x3518_x3528_data_v)
      val x3530 = CounterChain.copy("x3536", "x3530")
      val x3802 = CounterChain.copy("x3811_0", "x3802")
      val x3409_x3806 =  SRAM(size=48,banking = Strided(1)).wtPort(x3535_x3535.readPort).rdPort(x3409_x3806_x3811_v).rdAddr(x3802(0)).wtAddr(x3530(0))
      var stage: List[Stage] = Nil
    }
    val x3410_dsp0 = MemoryPipeline(name="x3410_dsp0",parent="x4194") { implicit CU => 
      val x3578_x3578 =  VectorFIFO(size=1).wtPort(x3561_x3571_data_v)
      val x3573 = CounterChain.copy("x3579", "x3573")
      val x3814 = CounterChain.copy("x3823_0", "x3814")
      val x3410_x3818 =  SRAM(size=48,banking = Strided(1)).wtPort(x3578_x3578.readPort).rdPort(x3410_x3818_x3823_v).rdAddr(x3814(0)).wtAddr(x3573(0))
      var stage: List[Stage] = Nil
    }
    val x3411_dsp0 = MemoryPipeline(name="x3411_dsp0",parent="x4194") { implicit CU => 
      val x3621_x3621 =  VectorFIFO(size=1).wtPort(x3604_x3614_data_v)
      val x3616 = CounterChain.copy("x3622", "x3616")
      val x3826 = CounterChain.copy("x3835_0", "x3826")
      val x3411_x3830 =  SRAM(size=48,banking = Strided(1)).wtPort(x3621_x3621.readPort).rdPort(x3411_x3830_x3835_v).rdAddr(x3826(0)).wtAddr(x3616(0))
      var stage: List[Stage] = Nil
    }
    val x3412_dsp0 = MemoryPipeline(name="x3412_dsp0",parent="x4194") { implicit CU => 
      val x3664_x3664 =  VectorFIFO(size=1).wtPort(x3647_x3657_data_v)
      val x3659 = CounterChain.copy("x3665", "x3659")
      val x3838 = CounterChain.copy("x3847_0", "x3838")
      val x3412_x3842 =  SRAM(size=48,banking = Strided(1)).wtPort(x3664_x3664.readPort).rdPort(x3412_x3842_x3847_v).rdAddr(x3838(0)).wtAddr(x3659(0))
      var stage: List[Stage] = Nil
    }
    val x3413_dsp0 = MemoryPipeline(name="x3413_dsp0",parent="x4194") { implicit CU => 
      val x3707_x3707 =  VectorFIFO(size=1).wtPort(x3690_x3700_data_v)
      val x3702 = CounterChain.copy("x3708", "x3702")
      val x3850 = CounterChain.copy("x3859_0", "x3850")
      val x3413_x3854 =  SRAM(size=48,banking = Strided(1)).wtPort(x3707_x3707.readPort).rdPort(x3413_x3854_x3859_v).rdAddr(x3850(0)).wtAddr(x3702(0))
      var stage: List[Stage] = Nil
    }
    val x3414_dsp0 = MemoryPipeline(name="x3414_dsp0",parent="x4194") { implicit CU => 
      val x3750_x3750 =  VectorFIFO(size=1).wtPort(x3733_x3743_data_v)
      val x3745 = CounterChain.copy("x3751", "x3745")
      val x3862 = CounterChain.copy("x3871_0", "x3862")
      val x3414_x3866 =  SRAM(size=48,banking = Strided(1)).wtPort(x3750_x3750.readPort).rdPort(x3414_x3866_x3871_v).rdAddr(x3862(0)).wtAddr(x3745(0))
      var stage: List[Stage] = Nil
    }
    val x3415_dsp0 = MemoryPipeline(name="x3415_dsp0",parent="x4194") { implicit CU => 
      val x3470_x3470 =  VectorFIFO(size=1).wtPort(x3453_x3463_data_v)
      val x3465 = CounterChain.copy("x3471", "x3465")
      val x3778 = CounterChain.copy("x3787_0", "x3778")
      val x3415_x3783 =  SRAM(size=48,banking = Strided(1)).wtPort(x3470_x3470.readPort).rdPort(x3415_x3783_x3787_v).rdAddr(x3778(1)).wtAddr(x3465(0))
      var stage: List[Stage] = Nil
    }
    val x3416_dsp0 = MemoryPipeline(name="x3416_dsp0",parent="x4194") { implicit CU => 
      val x3513_x3513 =  VectorFIFO(size=1).wtPort(x3496_x3506_data_v)
      val x3508 = CounterChain.copy("x3514", "x3508")
      val x3790 = CounterChain.copy("x3799_0", "x3790")
      val x3416_x3795 =  SRAM(size=48,banking = Strided(1)).wtPort(x3513_x3513.readPort).rdPort(x3416_x3795_x3799_v).rdAddr(x3790(1)).wtAddr(x3508(0))
      var stage: List[Stage] = Nil
    }
    val x3417_dsp0 = MemoryPipeline(name="x3417_dsp0",parent="x4194") { implicit CU => 
      val x3556_x3556 =  VectorFIFO(size=1).wtPort(x3539_x3549_data_v)
      val x3551 = CounterChain.copy("x3557", "x3551")
      val x3802 = CounterChain.copy("x3811_0", "x3802")
      val x3417_x3807 =  SRAM(size=48,banking = Strided(1)).wtPort(x3556_x3556.readPort).rdPort(x3417_x3807_x3811_v).rdAddr(x3802(1)).wtAddr(x3551(0))
      var stage: List[Stage] = Nil
    }
    val x3418_dsp0 = MemoryPipeline(name="x3418_dsp0",parent="x4194") { implicit CU => 
      val x3599_x3599 =  VectorFIFO(size=1).wtPort(x3582_x3592_data_v)
      val x3594 = CounterChain.copy("x3600", "x3594")
      val x3814 = CounterChain.copy("x3823_0", "x3814")
      val x3418_x3819 =  SRAM(size=48,banking = Strided(1)).wtPort(x3599_x3599.readPort).rdPort(x3418_x3819_x3823_v).rdAddr(x3814(1)).wtAddr(x3594(0))
      var stage: List[Stage] = Nil
    }
    val x3419_dsp0 = MemoryPipeline(name="x3419_dsp0",parent="x4194") { implicit CU => 
      val x3642_x3642 =  VectorFIFO(size=1).wtPort(x3625_x3635_data_v)
      val x3637 = CounterChain.copy("x3643", "x3637")
      val x3826 = CounterChain.copy("x3835_0", "x3826")
      val x3419_x3831 =  SRAM(size=48,banking = Strided(1)).wtPort(x3642_x3642.readPort).rdPort(x3419_x3831_x3835_v).rdAddr(x3826(1)).wtAddr(x3637(0))
      var stage: List[Stage] = Nil
    }
    val x3420_dsp0 = MemoryPipeline(name="x3420_dsp0",parent="x4194") { implicit CU => 
      val x3685_x3685 =  VectorFIFO(size=1).wtPort(x3668_x3678_data_v)
      val x3680 = CounterChain.copy("x3686", "x3680")
      val x3838 = CounterChain.copy("x3847_0", "x3838")
      val x3420_x3843 =  SRAM(size=48,banking = Strided(1)).wtPort(x3685_x3685.readPort).rdPort(x3420_x3843_x3847_v).rdAddr(x3838(1)).wtAddr(x3680(0))
      var stage: List[Stage] = Nil
    }
    val x3421_dsp0 = MemoryPipeline(name="x3421_dsp0",parent="x4194") { implicit CU => 
      val x3728_x3728 =  VectorFIFO(size=1).wtPort(x3711_x3721_data_v)
      val x3723 = CounterChain.copy("x3729", "x3723")
      val x3850 = CounterChain.copy("x3859_0", "x3850")
      val x3421_x3855 =  SRAM(size=48,banking = Strided(1)).wtPort(x3728_x3728.readPort).rdPort(x3421_x3855_x3859_v).rdAddr(x3850(1)).wtAddr(x3723(0))
      var stage: List[Stage] = Nil
    }
    val x3422_dsp0 = MemoryPipeline(name="x3422_dsp0",parent="x4194") { implicit CU => 
      val x3771_x3771 =  VectorFIFO(size=1).wtPort(x3754_x3764_data_v)
      val x3766 = CounterChain.copy("x3772", "x3766")
      val x3862 = CounterChain.copy("x3871_0", "x3862")
      val x3422_x3867 =  SRAM(size=48,banking = Strided(1)).wtPort(x3771_x3771.readPort).rdPort(x3422_x3867_x3871_v).rdAddr(x3862(1)).wtAddr(x3766(0))
      var stage: List[Stage] = Nil
    }
    val x3423_dsp0 = MemoryPipeline(name="x3423_dsp0",parent="x4194") { implicit CU => 
      val b4276 = CU.temp
      val b4296 = CU.temp
      val x3786_x3786 =  VectorFIFO(size=1).wtPort(x3423_x3786_v)
      val x3778 = CounterChain.copy("x3787_0", "x3778")
      val x3874 = CounterChain.copy("x3912", "x3874")
      val x3893 = CounterChain.copy("x3901", "x3893")
      val x3423_x3897 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3786_x3786.readPort).rdPort(x3423_x3897_x3901_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3778(0)), Const(48)), op=FixMul, results=List(b4276))
      WAStage(operands=List(b4276, CU.ctr(x3778(1))), op=FixAdd, results=List(x3423_x3897.writeAddr))
      RAStage(operands=List(CU.ctr(x3874(0)), Const(48)), op=FixMul, results=List(b4296))
      RAStage(operands=List(b4296, CU.ctr(x3893(0))), op=FixAdd, results=List(x3423_x3897.readAddr))
    }
    val x3424_dsp0 = MemoryPipeline(name="x3424_dsp0",parent="x4194") { implicit CU => 
      val b4302 = CU.temp
      val b4278 = CU.temp
      val x3798_x3798 =  VectorFIFO(size=1).wtPort(x3424_x3798_v)
      val x3790 = CounterChain.copy("x3799_0", "x3790")
      val x3914 = CounterChain.copy("x3952", "x3914")
      val x3933 = CounterChain.copy("x3941", "x3933")
      val x3424_x3937 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3798_x3798.readPort).rdPort(x3424_x3937_x3941_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3790(0)), Const(48)), op=FixMul, results=List(b4278))
      WAStage(operands=List(b4278, CU.ctr(x3790(1))), op=FixAdd, results=List(x3424_x3937.writeAddr))
      RAStage(operands=List(CU.ctr(x3914(0)), Const(48)), op=FixMul, results=List(b4302))
      RAStage(operands=List(b4302, CU.ctr(x3933(0))), op=FixAdd, results=List(x3424_x3937.readAddr))
    }
    val x3425_dsp0 = MemoryPipeline(name="x3425_dsp0",parent="x4194") { implicit CU => 
      val b4280 = CU.temp
      val b4308 = CU.temp
      val x3810_x3810 =  VectorFIFO(size=1).wtPort(x3425_x3810_v)
      val x3802 = CounterChain.copy("x3811_0", "x3802")
      val x3954 = CounterChain.copy("x3992", "x3954")
      val x3973 = CounterChain.copy("x3981", "x3973")
      val x3425_x3977 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3810_x3810.readPort).rdPort(x3425_x3977_x3981_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3802(0)), Const(48)), op=FixMul, results=List(b4280))
      WAStage(operands=List(b4280, CU.ctr(x3802(1))), op=FixAdd, results=List(x3425_x3977.writeAddr))
      RAStage(operands=List(CU.ctr(x3954(0)), Const(48)), op=FixMul, results=List(b4308))
      RAStage(operands=List(b4308, CU.ctr(x3973(0))), op=FixAdd, results=List(x3425_x3977.readAddr))
    }
    val x3426_dsp0 = MemoryPipeline(name="x3426_dsp0",parent="x4194") { implicit CU => 
      val b4314 = CU.temp
      val b4282 = CU.temp
      val x3822_x3822 =  VectorFIFO(size=1).wtPort(x3426_x3822_v)
      val x3814 = CounterChain.copy("x3823_0", "x3814")
      val x3994 = CounterChain.copy("x4032", "x3994")
      val x4013 = CounterChain.copy("x4021", "x4013")
      val x3426_x4017 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3822_x3822.readPort).rdPort(x3426_x4017_x4021_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3814(0)), Const(48)), op=FixMul, results=List(b4282))
      WAStage(operands=List(b4282, CU.ctr(x3814(1))), op=FixAdd, results=List(x3426_x4017.writeAddr))
      RAStage(operands=List(CU.ctr(x3994(0)), Const(48)), op=FixMul, results=List(b4314))
      RAStage(operands=List(b4314, CU.ctr(x4013(0))), op=FixAdd, results=List(x3426_x4017.readAddr))
    }
    val x3427_dsp0 = MemoryPipeline(name="x3427_dsp0",parent="x4194") { implicit CU => 
      val b4320 = CU.temp
      val b4284 = CU.temp
      val x3834_x3834 =  VectorFIFO(size=1).wtPort(x3427_x3834_v)
      val x3826 = CounterChain.copy("x3835_0", "x3826")
      val x4034 = CounterChain.copy("x4072", "x4034")
      val x4053 = CounterChain.copy("x4061", "x4053")
      val x3427_x4057 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3834_x3834.readPort).rdPort(x3427_x4057_x4061_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3826(0)), Const(48)), op=FixMul, results=List(b4284))
      WAStage(operands=List(b4284, CU.ctr(x3826(1))), op=FixAdd, results=List(x3427_x4057.writeAddr))
      RAStage(operands=List(CU.ctr(x4034(0)), Const(48)), op=FixMul, results=List(b4320))
      RAStage(operands=List(b4320, CU.ctr(x4053(0))), op=FixAdd, results=List(x3427_x4057.readAddr))
    }
    val x3428_dsp0 = MemoryPipeline(name="x3428_dsp0",parent="x4194") { implicit CU => 
      val b4326 = CU.temp
      val b4286 = CU.temp
      val x3846_x3846 =  VectorFIFO(size=1).wtPort(x3428_x3846_v)
      val x3838 = CounterChain.copy("x3847_0", "x3838")
      val x4074 = CounterChain.copy("x4112", "x4074")
      val x4093 = CounterChain.copy("x4101", "x4093")
      val x3428_x4097 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3846_x3846.readPort).rdPort(x3428_x4097_x4101_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3838(0)), Const(48)), op=FixMul, results=List(b4286))
      WAStage(operands=List(b4286, CU.ctr(x3838(1))), op=FixAdd, results=List(x3428_x4097.writeAddr))
      RAStage(operands=List(CU.ctr(x4074(0)), Const(48)), op=FixMul, results=List(b4326))
      RAStage(operands=List(b4326, CU.ctr(x4093(0))), op=FixAdd, results=List(x3428_x4097.readAddr))
    }
    val x3429_dsp0 = MemoryPipeline(name="x3429_dsp0",parent="x4194") { implicit CU => 
      val b4332 = CU.temp
      val b4288 = CU.temp
      val x3858_x3858 =  VectorFIFO(size=1).wtPort(x3429_x3858_v)
      val x3850 = CounterChain.copy("x3859_0", "x3850")
      val x4114 = CounterChain.copy("x4152", "x4114")
      val x4133 = CounterChain.copy("x4141", "x4133")
      val x3429_x4137 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3858_x3858.readPort).rdPort(x3429_x4137_x4141_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3850(0)), Const(48)), op=FixMul, results=List(b4288))
      WAStage(operands=List(b4288, CU.ctr(x3850(1))), op=FixAdd, results=List(x3429_x4137.writeAddr))
      RAStage(operands=List(CU.ctr(x4114(0)), Const(48)), op=FixMul, results=List(b4332))
      RAStage(operands=List(b4332, CU.ctr(x4133(0))), op=FixAdd, results=List(x3429_x4137.readAddr))
    }
    val x3430_dsp0 = MemoryPipeline(name="x3430_dsp0",parent="x4194") { implicit CU => 
      val b4290 = CU.temp
      val b4338 = CU.temp
      val x3870_x3870 =  VectorFIFO(size=1).wtPort(x3430_x3870_v)
      val x3862 = CounterChain.copy("x3871_0", "x3862")
      val x4154 = CounterChain.copy("x4192", "x4154")
      val x4173 = CounterChain.copy("x4181", "x4173")
      val x3430_x4177 =  SRAM(size=2304,banking = Strided(1)).wtPort(x3870_x3870.readPort).rdPort(x3430_x4177_x4181_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3862(0)), Const(48)), op=FixMul, results=List(b4290))
      WAStage(operands=List(b4290, CU.ctr(x3862(1))), op=FixAdd, results=List(x3430_x4177.writeAddr))
      RAStage(operands=List(CU.ctr(x4154(0)), Const(48)), op=FixMul, results=List(b4338))
      RAStage(operands=List(b4338, CU.ctr(x4173(0))), op=FixAdd, results=List(x3430_x4177.readAddr))
    }
    val x3451 = StreamController(name="x3451",parent=x4194) { implicit CU => 
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3451_unit = CounterChain(name = "x3451_unit", ctr4)
    }
    val x3441_0 = Pipeline(name="x3441_0",parent=x3451) { implicit CU => 
      val x3434 = CU.temp
      val x3433 =  ScalarBuffer().wtPort(x3433_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr5 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3441_unit = CounterChain(name = "x3441_unit", ctr5)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3434))
      Stage(operands=List(x3434, CU.load(x3433)), op=FixAdd, results=List(CU.scalarOut(x3431_b4212_x3440_b4214_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3431_b4213_x3440_b4215_s)))
    }
    val x3442 = MemoryController(name="x3442",parent=x3451,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3431_b4213_x3442 =  ScalarFIFO(name="size",size=1).wtPort(x3431_b4213_x3440_b4215_s)
      val x3431_b4212_x3442 =  ScalarFIFO(name="offset",size=1).wtPort(x3431_b4212_x3440_b4214_s)
      CU.newVout("data", x3432_x3442_data_v)
    }
    val x3450 = Pipeline(name="x3450",parent=x3451) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3444 = CounterChain(name = "x3444", ctr6)
      var stage: List[Stage] = Nil
    }
    val x3472 = StreamController(name="x3472",parent=x4194) { implicit CU => 
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3472_unit = CounterChain(name = "x3472_unit", ctr7)
    }
    val x3462_0 = Pipeline(name="x3462_0",parent=x3472) { implicit CU => 
      val x3455 = CU.temp
      val x3454 =  ScalarBuffer().wtPort(x3454_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr8 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3462_unit = CounterChain(name = "x3462_unit", ctr8)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3455))
      Stage(operands=List(x3455, CU.load(x3454)), op=FixAdd, results=List(CU.scalarOut(x3452_b4216_x3461_b4218_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3452_b4217_x3461_b4219_s)))
    }
    val x3463 = MemoryController(name="x3463",parent=x3472,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3452_b4216_x3463 =  ScalarFIFO(name="offset",size=1).wtPort(x3452_b4216_x3461_b4218_s)
      val x3452_b4217_x3463 =  ScalarFIFO(name="size",size=1).wtPort(x3452_b4217_x3461_b4219_s)
      CU.newVout("data", x3453_x3463_data_v)
    }
    val x3471 = Pipeline(name="x3471",parent=x3472) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3465 = CounterChain(name = "x3465", ctr9)
      var stage: List[Stage] = Nil
    }
    val x3494 = StreamController(name="x3494",parent=x4194) { implicit CU => 
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3494_unit = CounterChain(name = "x3494_unit", ctr10)
    }
    val x3484_0 = Pipeline(name="x3484_0",parent=x3494) { implicit CU => 
      val x3477 = CU.temp
      val x3476 =  ScalarBuffer().wtPort(x3476_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3484_unit = CounterChain(name = "x3484_unit", ctr11)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3477))
      Stage(operands=List(x3477, CU.load(x3476)), op=FixAdd, results=List(CU.scalarOut(x3474_b4220_x3483_b4222_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3474_b4221_x3483_b4223_s)))
    }
    val x3485 = MemoryController(name="x3485",parent=x3494,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3474_b4221_x3485 =  ScalarFIFO(name="size",size=1).wtPort(x3474_b4221_x3483_b4223_s)
      val x3474_b4220_x3485 =  ScalarFIFO(name="offset",size=1).wtPort(x3474_b4220_x3483_b4222_s)
      CU.newVout("data", x3475_x3485_data_v)
    }
    val x3493 = Pipeline(name="x3493",parent=x3494) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3487 = CounterChain(name = "x3487", ctr12)
      var stage: List[Stage] = Nil
    }
    val x3515 = StreamController(name="x3515",parent=x4194) { implicit CU => 
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3515_unit = CounterChain(name = "x3515_unit", ctr13)
    }
    val x3505_0 = Pipeline(name="x3505_0",parent=x3515) { implicit CU => 
      val x3498 = CU.temp
      val x3497 =  ScalarBuffer().wtPort(x3497_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3505_unit = CounterChain(name = "x3505_unit", ctr14)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3498))
      Stage(operands=List(x3498, CU.load(x3497)), op=FixAdd, results=List(CU.scalarOut(x3495_b4224_x3504_b4226_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3495_b4225_x3504_b4227_s)))
    }
    val x3506 = MemoryController(name="x3506",parent=x3515,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3495_b4225_x3506 =  ScalarFIFO(name="size",size=1).wtPort(x3495_b4225_x3504_b4227_s)
      val x3495_b4224_x3506 =  ScalarFIFO(name="offset",size=1).wtPort(x3495_b4224_x3504_b4226_s)
      CU.newVout("data", x3496_x3506_data_v)
    }
    val x3514 = Pipeline(name="x3514",parent=x3515) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3508 = CounterChain(name = "x3508", ctr15)
      var stage: List[Stage] = Nil
    }
    val x3537 = StreamController(name="x3537",parent=x4194) { implicit CU => 
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3537_unit = CounterChain(name = "x3537_unit", ctr16)
    }
    val x3527_0 = Pipeline(name="x3527_0",parent=x3537) { implicit CU => 
      val x3520 = CU.temp
      val x3519 =  ScalarBuffer().wtPort(x3519_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr17 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3527_unit = CounterChain(name = "x3527_unit", ctr17)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3520))
      Stage(operands=List(x3520, CU.load(x3519)), op=FixAdd, results=List(CU.scalarOut(x3517_b4228_x3526_b4230_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3517_b4229_x3526_b4231_s)))
    }
    val x3528 = MemoryController(name="x3528",parent=x3537,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3517_b4228_x3528 =  ScalarFIFO(name="offset",size=1).wtPort(x3517_b4228_x3526_b4230_s)
      val x3517_b4229_x3528 =  ScalarFIFO(name="size",size=1).wtPort(x3517_b4229_x3526_b4231_s)
      CU.newVout("data", x3518_x3528_data_v)
    }
    val x3536 = Pipeline(name="x3536",parent=x3537) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3530 = CounterChain(name = "x3530", ctr18)
      var stage: List[Stage] = Nil
    }
    val x3558 = StreamController(name="x3558",parent=x4194) { implicit CU => 
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3558_unit = CounterChain(name = "x3558_unit", ctr19)
    }
    val x3548_0 = Pipeline(name="x3548_0",parent=x3558) { implicit CU => 
      val x3541 = CU.temp
      val x3540 =  ScalarBuffer().wtPort(x3540_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3548_unit = CounterChain(name = "x3548_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3541))
      Stage(operands=List(x3541, CU.load(x3540)), op=FixAdd, results=List(CU.scalarOut(x3538_b4232_x3547_b4234_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3538_b4233_x3547_b4235_s)))
    }
    val x3549 = MemoryController(name="x3549",parent=x3558,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3538_b4233_x3549 =  ScalarFIFO(name="size",size=1).wtPort(x3538_b4233_x3547_b4235_s)
      val x3538_b4232_x3549 =  ScalarFIFO(name="offset",size=1).wtPort(x3538_b4232_x3547_b4234_s)
      CU.newVout("data", x3539_x3549_data_v)
    }
    val x3557 = Pipeline(name="x3557",parent=x3558) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3551 = CounterChain(name = "x3551", ctr21)
      var stage: List[Stage] = Nil
    }
    val x3580 = StreamController(name="x3580",parent=x4194) { implicit CU => 
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3580_unit = CounterChain(name = "x3580_unit", ctr22)
    }
    val x3570_0 = Pipeline(name="x3570_0",parent=x3580) { implicit CU => 
      val x3563 = CU.temp
      val x3562 =  ScalarBuffer().wtPort(x3562_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr23 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3570_unit = CounterChain(name = "x3570_unit", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3563))
      Stage(operands=List(x3563, CU.load(x3562)), op=FixAdd, results=List(CU.scalarOut(x3560_b4236_x3569_b4238_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3560_b4237_x3569_b4239_s)))
    }
    val x3571 = MemoryController(name="x3571",parent=x3580,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3560_b4237_x3571 =  ScalarFIFO(name="size",size=1).wtPort(x3560_b4237_x3569_b4239_s)
      val x3560_b4236_x3571 =  ScalarFIFO(name="offset",size=1).wtPort(x3560_b4236_x3569_b4238_s)
      CU.newVout("data", x3561_x3571_data_v)
    }
    val x3579 = Pipeline(name="x3579",parent=x3580) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3573 = CounterChain(name = "x3573", ctr24)
      var stage: List[Stage] = Nil
    }
    val x3601 = StreamController(name="x3601",parent=x4194) { implicit CU => 
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3601_unit = CounterChain(name = "x3601_unit", ctr25)
    }
    val x3591_0 = Pipeline(name="x3591_0",parent=x3601) { implicit CU => 
      val x3584 = CU.temp
      val x3583 =  ScalarBuffer().wtPort(x3583_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3591_unit = CounterChain(name = "x3591_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3584))
      Stage(operands=List(x3584, CU.load(x3583)), op=FixAdd, results=List(CU.scalarOut(x3581_b4240_x3590_b4242_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3581_b4241_x3590_b4243_s)))
    }
    val x3592 = MemoryController(name="x3592",parent=x3601,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3581_b4240_x3592 =  ScalarFIFO(name="offset",size=1).wtPort(x3581_b4240_x3590_b4242_s)
      val x3581_b4241_x3592 =  ScalarFIFO(name="size",size=1).wtPort(x3581_b4241_x3590_b4243_s)
      CU.newVout("data", x3582_x3592_data_v)
    }
    val x3600 = Pipeline(name="x3600",parent=x3601) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3594 = CounterChain(name = "x3594", ctr27)
      var stage: List[Stage] = Nil
    }
    val x3623 = StreamController(name="x3623",parent=x4194) { implicit CU => 
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3623_unit = CounterChain(name = "x3623_unit", ctr28)
    }
    val x3613_0 = Pipeline(name="x3613_0",parent=x3623) { implicit CU => 
      val x3606 = CU.temp
      val x3605 =  ScalarBuffer().wtPort(x3605_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3613_unit = CounterChain(name = "x3613_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3606))
      Stage(operands=List(x3606, CU.load(x3605)), op=FixAdd, results=List(CU.scalarOut(x3603_b4244_x3612_b4246_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3603_b4245_x3612_b4247_s)))
    }
    val x3614 = MemoryController(name="x3614",parent=x3623,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3603_b4245_x3614 =  ScalarFIFO(name="size",size=1).wtPort(x3603_b4245_x3612_b4247_s)
      val x3603_b4244_x3614 =  ScalarFIFO(name="offset",size=1).wtPort(x3603_b4244_x3612_b4246_s)
      CU.newVout("data", x3604_x3614_data_v)
    }
    val x3622 = Pipeline(name="x3622",parent=x3623) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3616 = CounterChain(name = "x3616", ctr30)
      var stage: List[Stage] = Nil
    }
    val x3644 = StreamController(name="x3644",parent=x4194) { implicit CU => 
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3644_unit = CounterChain(name = "x3644_unit", ctr31)
    }
    val x3634_0 = Pipeline(name="x3634_0",parent=x3644) { implicit CU => 
      val x3627 = CU.temp
      val x3626 =  ScalarBuffer().wtPort(x3626_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr32 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3634_unit = CounterChain(name = "x3634_unit", ctr32)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3627))
      Stage(operands=List(x3627, CU.load(x3626)), op=FixAdd, results=List(CU.scalarOut(x3624_b4248_x3633_b4250_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3624_b4249_x3633_b4251_s)))
    }
    val x3635 = MemoryController(name="x3635",parent=x3644,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3624_b4249_x3635 =  ScalarFIFO(name="size",size=1).wtPort(x3624_b4249_x3633_b4251_s)
      val x3624_b4248_x3635 =  ScalarFIFO(name="offset",size=1).wtPort(x3624_b4248_x3633_b4250_s)
      CU.newVout("data", x3625_x3635_data_v)
    }
    val x3643 = Pipeline(name="x3643",parent=x3644) { implicit CU => 
      val ctr33 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3637 = CounterChain(name = "x3637", ctr33)
      var stage: List[Stage] = Nil
    }
    val x3666 = StreamController(name="x3666",parent=x4194) { implicit CU => 
      val ctr34 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3666_unit = CounterChain(name = "x3666_unit", ctr34)
    }
    val x3656_0 = Pipeline(name="x3656_0",parent=x3666) { implicit CU => 
      val x3649 = CU.temp
      val x3648 =  ScalarBuffer().wtPort(x3648_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr35 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3656_unit = CounterChain(name = "x3656_unit", ctr35)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3649))
      Stage(operands=List(x3649, CU.load(x3648)), op=FixAdd, results=List(CU.scalarOut(x3646_b4252_x3655_b4254_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3646_b4253_x3655_b4255_s)))
    }
    val x3657 = MemoryController(name="x3657",parent=x3666,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3646_b4252_x3657 =  ScalarFIFO(name="offset",size=1).wtPort(x3646_b4252_x3655_b4254_s)
      val x3646_b4253_x3657 =  ScalarFIFO(name="size",size=1).wtPort(x3646_b4253_x3655_b4255_s)
      CU.newVout("data", x3647_x3657_data_v)
    }
    val x3665 = Pipeline(name="x3665",parent=x3666) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3659 = CounterChain(name = "x3659", ctr36)
      var stage: List[Stage] = Nil
    }
    val x3687 = StreamController(name="x3687",parent=x4194) { implicit CU => 
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3687_unit = CounterChain(name = "x3687_unit", ctr37)
    }
    val x3677_0 = Pipeline(name="x3677_0",parent=x3687) { implicit CU => 
      val x3670 = CU.temp
      val x3669 =  ScalarBuffer().wtPort(x3669_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3677_unit = CounterChain(name = "x3677_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3670))
      Stage(operands=List(x3670, CU.load(x3669)), op=FixAdd, results=List(CU.scalarOut(x3667_b4256_x3676_b4258_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3667_b4257_x3676_b4259_s)))
    }
    val x3678 = MemoryController(name="x3678",parent=x3687,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3667_b4257_x3678 =  ScalarFIFO(name="size",size=1).wtPort(x3667_b4257_x3676_b4259_s)
      val x3667_b4256_x3678 =  ScalarFIFO(name="offset",size=1).wtPort(x3667_b4256_x3676_b4258_s)
      CU.newVout("data", x3668_x3678_data_v)
    }
    val x3686 = Pipeline(name="x3686",parent=x3687) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3680 = CounterChain(name = "x3680", ctr39)
      var stage: List[Stage] = Nil
    }
    val x3709 = StreamController(name="x3709",parent=x4194) { implicit CU => 
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3709_unit = CounterChain(name = "x3709_unit", ctr40)
    }
    val x3699_0 = Pipeline(name="x3699_0",parent=x3709) { implicit CU => 
      val x3692 = CU.temp
      val x3691 =  ScalarBuffer().wtPort(x3691_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr41 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3699_unit = CounterChain(name = "x3699_unit", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3692))
      Stage(operands=List(x3692, CU.load(x3691)), op=FixAdd, results=List(CU.scalarOut(x3689_b4260_x3698_b4262_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3689_b4261_x3698_b4263_s)))
    }
    val x3700 = MemoryController(name="x3700",parent=x3709,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3689_b4261_x3700 =  ScalarFIFO(name="size",size=1).wtPort(x3689_b4261_x3698_b4263_s)
      val x3689_b4260_x3700 =  ScalarFIFO(name="offset",size=1).wtPort(x3689_b4260_x3698_b4262_s)
      CU.newVout("data", x3690_x3700_data_v)
    }
    val x3708 = Pipeline(name="x3708",parent=x3709) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3702 = CounterChain(name = "x3702", ctr42)
      var stage: List[Stage] = Nil
    }
    val x3730 = StreamController(name="x3730",parent=x4194) { implicit CU => 
      val ctr43 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3730_unit = CounterChain(name = "x3730_unit", ctr43)
    }
    val x3720_0 = Pipeline(name="x3720_0",parent=x3730) { implicit CU => 
      val x3713 = CU.temp
      val x3712 =  ScalarBuffer().wtPort(x3712_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3720_unit = CounterChain(name = "x3720_unit", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3713))
      Stage(operands=List(x3713, CU.load(x3712)), op=FixAdd, results=List(CU.scalarOut(x3710_b4264_x3719_b4266_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3710_b4265_x3719_b4267_s)))
    }
    val x3721 = MemoryController(name="x3721",parent=x3730,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3710_b4264_x3721 =  ScalarFIFO(name="offset",size=1).wtPort(x3710_b4264_x3719_b4266_s)
      val x3710_b4265_x3721 =  ScalarFIFO(name="size",size=1).wtPort(x3710_b4265_x3719_b4267_s)
      CU.newVout("data", x3711_x3721_data_v)
    }
    val x3729 = Pipeline(name="x3729",parent=x3730) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3723 = CounterChain(name = "x3723", ctr45)
      var stage: List[Stage] = Nil
    }
    val x3752 = StreamController(name="x3752",parent=x4194) { implicit CU => 
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3752_unit = CounterChain(name = "x3752_unit", ctr46)
    }
    val x3742_0 = Pipeline(name="x3742_0",parent=x3752) { implicit CU => 
      val x3735 = CU.temp
      val x3734 =  ScalarBuffer().wtPort(x3734_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr47 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3742_unit = CounterChain(name = "x3742_unit", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), Const(4)), op=FixMul, results=List(x3735))
      Stage(operands=List(x3735, CU.load(x3734)), op=FixAdd, results=List(CU.scalarOut(x3732_b4268_x3741_b4270_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3732_b4269_x3741_b4271_s)))
    }
    val x3743 = MemoryController(name="x3743",parent=x3752,offchip=x3394_oc, mctpe=TileLoad) { implicit CU => 
      val x3732_b4269_x3743 =  ScalarFIFO(name="size",size=1).wtPort(x3732_b4269_x3741_b4271_s)
      val x3732_b4268_x3743 =  ScalarFIFO(name="offset",size=1).wtPort(x3732_b4268_x3741_b4270_s)
      CU.newVout("data", x3733_x3743_data_v)
    }
    val x3751 = Pipeline(name="x3751",parent=x3752) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3745 = CounterChain(name = "x3745", ctr48)
      var stage: List[Stage] = Nil
    }
    val x3773 = StreamController(name="x3773",parent=x4194) { implicit CU => 
      val ctr49 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3773_unit = CounterChain(name = "x3773_unit", ctr49)
    }
    val x3763_0 = Pipeline(name="x3763_0",parent=x3773) { implicit CU => 
      val x3756 = CU.temp
      val x3755 =  ScalarBuffer().wtPort(x3755_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val ctr50 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3763_unit = CounterChain(name = "x3763_unit", ctr50)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(1)), Const(4)), op=FixMul, results=List(x3756))
      Stage(operands=List(x3756, CU.load(x3755)), op=FixAdd, results=List(CU.scalarOut(x3753_b4272_x3762_b4274_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3753_b4273_x3762_b4275_s)))
    }
    val x3764 = MemoryController(name="x3764",parent=x3773,offchip=x3396_oc, mctpe=TileLoad) { implicit CU => 
      val x3753_b4273_x3764 =  ScalarFIFO(name="size",size=1).wtPort(x3753_b4273_x3762_b4275_s)
      val x3753_b4272_x3764 =  ScalarFIFO(name="offset",size=1).wtPort(x3753_b4272_x3762_b4274_s)
      CU.newVout("data", x3754_x3764_data_v)
    }
    val x3772 = Pipeline(name="x3772",parent=x3773) { implicit CU => 
      val ctr51 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3766 = CounterChain(name = "x3766", ctr51)
      var stage: List[Stage] = Nil
    }
    val x3787_0 = Pipeline(name="x3787_0",parent=x4194) { implicit CU => 
      val x3407_x3782 =  VectorFIFO(size=1).wtPort(x3407_x3782_x3787_v)
      val x3415_x3783 =  VectorFIFO(size=1).wtPort(x3415_x3783_x3787_v)
      val ctr52 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr53 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3778 = CounterChain(name = "x3778", ctr52, ctr53)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3407_x3782), CU.load(x3415_x3783)), op=FixMul, results=List(CU.vecOut(x3423_x3786_v)))
    }
    val x3799_0 = Pipeline(name="x3799_0",parent=x4194) { implicit CU => 
      val x3408_x3794 =  VectorFIFO(size=1).wtPort(x3408_x3794_x3799_v)
      val x3416_x3795 =  VectorFIFO(size=1).wtPort(x3416_x3795_x3799_v)
      val ctr54 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr55 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3790 = CounterChain(name = "x3790", ctr54, ctr55)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3408_x3794), CU.load(x3416_x3795)), op=FixMul, results=List(CU.vecOut(x3424_x3798_v)))
    }
    val x3811_0 = Pipeline(name="x3811_0",parent=x4194) { implicit CU => 
      val x3417_x3807 =  VectorFIFO(size=1).wtPort(x3417_x3807_x3811_v)
      val x3409_x3806 =  VectorFIFO(size=1).wtPort(x3409_x3806_x3811_v)
      val ctr56 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr57 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3802 = CounterChain(name = "x3802", ctr56, ctr57)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3409_x3806), CU.load(x3417_x3807)), op=FixMul, results=List(CU.vecOut(x3425_x3810_v)))
    }
    val x3823_0 = Pipeline(name="x3823_0",parent=x4194) { implicit CU => 
      val x3410_x3818 =  VectorFIFO(size=1).wtPort(x3410_x3818_x3823_v)
      val x3418_x3819 =  VectorFIFO(size=1).wtPort(x3418_x3819_x3823_v)
      val ctr58 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr59 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3814 = CounterChain(name = "x3814", ctr58, ctr59)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3410_x3818), CU.load(x3418_x3819)), op=FixMul, results=List(CU.vecOut(x3426_x3822_v)))
    }
    val x3835_0 = Pipeline(name="x3835_0",parent=x4194) { implicit CU => 
      val x3411_x3830 =  VectorFIFO(size=1).wtPort(x3411_x3830_x3835_v)
      val x3419_x3831 =  VectorFIFO(size=1).wtPort(x3419_x3831_x3835_v)
      val ctr60 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr61 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3826 = CounterChain(name = "x3826", ctr60, ctr61)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3411_x3830), CU.load(x3419_x3831)), op=FixMul, results=List(CU.vecOut(x3427_x3834_v)))
    }
    val x3847_0 = Pipeline(name="x3847_0",parent=x4194) { implicit CU => 
      val x3412_x3842 =  VectorFIFO(size=1).wtPort(x3412_x3842_x3847_v)
      val x3420_x3843 =  VectorFIFO(size=1).wtPort(x3420_x3843_x3847_v)
      val ctr62 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr63 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3838 = CounterChain(name = "x3838", ctr62, ctr63)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3412_x3842), CU.load(x3420_x3843)), op=FixMul, results=List(CU.vecOut(x3428_x3846_v)))
    }
    val x3859_0 = Pipeline(name="x3859_0",parent=x4194) { implicit CU => 
      val x3421_x3855 =  VectorFIFO(size=1).wtPort(x3421_x3855_x3859_v)
      val x3413_x3854 =  VectorFIFO(size=1).wtPort(x3413_x3854_x3859_v)
      val ctr64 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr65 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3850 = CounterChain(name = "x3850", ctr64, ctr65)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3413_x3854), CU.load(x3421_x3855)), op=FixMul, results=List(CU.vecOut(x3429_x3858_v)))
    }
    val x3871_0 = Pipeline(name="x3871_0",parent=x4194) { implicit CU => 
      val x3414_x3866 =  VectorFIFO(size=1).wtPort(x3414_x3866_x3871_v)
      val x3422_x3867 =  VectorFIFO(size=1).wtPort(x3422_x3867_x3871_v)
      val ctr66 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val ctr67 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3862 = CounterChain(name = "x3862", ctr66, ctr67)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3414_x3866), CU.load(x3422_x3867)), op=FixMul, results=List(CU.vecOut(x3430_x3870_v)))
    }
    val x3912 = StreamController(name="x3912",parent=x4194) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3874 = CounterChain(name = "x3874", ctr68)
    }
    val x3902 = Sequential(name="x3902",parent=x3912) { implicit CU => 
      val ctr69 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3902_unit = CounterChain(name = "x3902_unit", ctr69)
    }
    val x3891_0 = Pipeline(name="x3891_0",parent=x3902) { implicit CU => 
      val x3880 = CU.temp
      val x3881 = CU.temp
      val x3883 = CU.temp
      val x3882 = CU.temp
      val x3878 =  ScalarBuffer().wtPort(x3878_argin)
      val x3390_x3879 =  ScalarBuffer().wtPort(x3390_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x3874 = CounterChain.copy("x3912", "x3874")
      val ctr70 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3891_unit = CounterChain(name = "x3891_unit", ctr70)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x3874(0))), op=FixAdd, results=List(x3880))
      Stage(operands=List(x3880, CU.load(x3390_x3879)), op=FixMul, results=List(x3881))
      Stage(operands=List(x3881, CU.ctr(x3406(1))), op=FixAdd, results=List(x3882))
      Stage(operands=List(x3882, Const(4)), op=FixMul, results=List(x3883))
      Stage(operands=List(x3883, CU.load(x3878)), op=FixAdd, results=List(CU.scalarOut(x3875_b4292_x3890_b4294_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3875_b4293_x3890_b4295_s)))
    }
    val x3901 = Pipeline(name="x3901",parent=x3902) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3893 = CounterChain(name = "x3893", ctr71)
      var stage: List[Stage] = Nil
    }
    val x3903 = MemoryController(name="x3903",parent=x3912,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x3875_b4293_x3903 =  ScalarFIFO(name="size",size=1).wtPort(x3875_b4293_x3890_b4295_s)
      val x3875_b4292_x3903 =  ScalarFIFO(name="offset",size=1).wtPort(x3875_b4292_x3890_b4294_s)
      val x3876_x3903 =  VectorFIFO(name="data",size=1).wtPort(x3423_x3897_x3901_v)
    }
    val x3952 = StreamController(name="x3952",parent=x4194) { implicit CU => 
      val ctr74 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3914 = CounterChain(name = "x3914", ctr74)
    }
    val x3942 = Sequential(name="x3942",parent=x3952) { implicit CU => 
      val ctr75 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3942_unit = CounterChain(name = "x3942_unit", ctr75)
    }
    val x3931_0 = Pipeline(name="x3931_0",parent=x3942) { implicit CU => 
      val x3921 = CU.temp
      val x3923 = CU.temp
      val x3922 = CU.temp
      val x3920 = CU.temp
      val x3390_x3919 =  ScalarBuffer().wtPort(x3390_argin)
      val x3918 =  ScalarBuffer().wtPort(x3918_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x3914 = CounterChain.copy("x3952", "x3914")
      val ctr76 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3931_unit = CounterChain(name = "x3931_unit", ctr76)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x3914(0))), op=FixAdd, results=List(x3920))
      Stage(operands=List(x3920, CU.load(x3390_x3919)), op=FixMul, results=List(x3921))
      Stage(operands=List(x3921, CU.ctr(x3406(1))), op=FixAdd, results=List(x3922))
      Stage(operands=List(x3922, Const(4)), op=FixMul, results=List(x3923))
      Stage(operands=List(x3923, CU.load(x3918)), op=FixAdd, results=List(CU.scalarOut(x3915_b4298_x3930_b4300_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3915_b4299_x3930_b4301_s)))
    }
    val x3941 = Pipeline(name="x3941",parent=x3942) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3933 = CounterChain(name = "x3933", ctr77)
      var stage: List[Stage] = Nil
    }
    val x3943 = MemoryController(name="x3943",parent=x3952,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x3915_b4299_x3943 =  ScalarFIFO(name="size",size=1).wtPort(x3915_b4299_x3930_b4301_s)
      val x3916_x3943 =  VectorFIFO(name="data",size=1).wtPort(x3424_x3937_x3941_v)
      val x3915_b4298_x3943 =  ScalarFIFO(name="offset",size=1).wtPort(x3915_b4298_x3930_b4300_s)
    }
    val x3992 = StreamController(name="x3992",parent=x4194) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3954 = CounterChain(name = "x3954", ctr80)
    }
    val x3982 = Sequential(name="x3982",parent=x3992) { implicit CU => 
      val ctr81 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3982_unit = CounterChain(name = "x3982_unit", ctr81)
    }
    val x3971_0 = Pipeline(name="x3971_0",parent=x3982) { implicit CU => 
      val x3963 = CU.temp
      val x3962 = CU.temp
      val x3961 = CU.temp
      val x3960 = CU.temp
      val x3390_x3959 =  ScalarBuffer().wtPort(x3390_argin)
      val x3958 =  ScalarBuffer().wtPort(x3958_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x3954 = CounterChain.copy("x3992", "x3954")
      val ctr82 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3971_unit = CounterChain(name = "x3971_unit", ctr82)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x3954(0))), op=FixAdd, results=List(x3960))
      Stage(operands=List(x3960, CU.load(x3390_x3959)), op=FixMul, results=List(x3961))
      Stage(operands=List(x3961, CU.ctr(x3406(1))), op=FixAdd, results=List(x3962))
      Stage(operands=List(x3962, Const(4)), op=FixMul, results=List(x3963))
      Stage(operands=List(x3963, CU.load(x3958)), op=FixAdd, results=List(CU.scalarOut(x3955_b4304_x3970_b4306_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3955_b4305_x3970_b4307_s)))
    }
    val x3981 = Pipeline(name="x3981",parent=x3982) { implicit CU => 
      val ctr83 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x3973 = CounterChain(name = "x3973", ctr83)
      var stage: List[Stage] = Nil
    }
    val x3983 = MemoryController(name="x3983",parent=x3992,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x3956_x3983 =  VectorFIFO(name="data",size=1).wtPort(x3425_x3977_x3981_v)
      val x3955_b4305_x3983 =  ScalarFIFO(name="size",size=1).wtPort(x3955_b4305_x3970_b4307_s)
      val x3955_b4304_x3983 =  ScalarFIFO(name="offset",size=1).wtPort(x3955_b4304_x3970_b4306_s)
    }
    val x4032 = StreamController(name="x4032",parent=x4194) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x3994 = CounterChain(name = "x3994", ctr86)
    }
    val x4022 = Sequential(name="x4022",parent=x4032) { implicit CU => 
      val ctr87 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4022_unit = CounterChain(name = "x4022_unit", ctr87)
    }
    val x4011_0 = Pipeline(name="x4011_0",parent=x4022) { implicit CU => 
      val x4001 = CU.temp
      val x4002 = CU.temp
      val x4003 = CU.temp
      val x4000 = CU.temp
      val x3998 =  ScalarBuffer().wtPort(x3998_argin)
      val x3390_x3999 =  ScalarBuffer().wtPort(x3390_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x3994 = CounterChain.copy("x4032", "x3994")
      val ctr88 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4011_unit = CounterChain(name = "x4011_unit", ctr88)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x3994(0))), op=FixAdd, results=List(x4000))
      Stage(operands=List(x4000, CU.load(x3390_x3999)), op=FixMul, results=List(x4001))
      Stage(operands=List(x4001, CU.ctr(x3406(1))), op=FixAdd, results=List(x4002))
      Stage(operands=List(x4002, Const(4)), op=FixMul, results=List(x4003))
      Stage(operands=List(x4003, CU.load(x3998)), op=FixAdd, results=List(CU.scalarOut(x3995_b4310_x4010_b4312_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x3995_b4311_x4010_b4313_s)))
    }
    val x4021 = Pipeline(name="x4021",parent=x4022) { implicit CU => 
      val ctr89 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4013 = CounterChain(name = "x4013", ctr89)
      var stage: List[Stage] = Nil
    }
    val x4023 = MemoryController(name="x4023",parent=x4032,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x3995_b4311_x4023 =  ScalarFIFO(name="size",size=1).wtPort(x3995_b4311_x4010_b4313_s)
      val x3995_b4310_x4023 =  ScalarFIFO(name="offset",size=1).wtPort(x3995_b4310_x4010_b4312_s)
      val x3996_x4023 =  VectorFIFO(name="data",size=1).wtPort(x3426_x4017_x4021_v)
    }
    val x4072 = StreamController(name="x4072",parent=x4194) { implicit CU => 
      val ctr92 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4034 = CounterChain(name = "x4034", ctr92)
    }
    val x4062 = Sequential(name="x4062",parent=x4072) { implicit CU => 
      val ctr93 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4062_unit = CounterChain(name = "x4062_unit", ctr93)
    }
    val x4051_0 = Pipeline(name="x4051_0",parent=x4062) { implicit CU => 
      val x4040 = CU.temp
      val x4043 = CU.temp
      val x4041 = CU.temp
      val x4042 = CU.temp
      val x3390_x4039 =  ScalarBuffer().wtPort(x3390_argin)
      val x4038 =  ScalarBuffer().wtPort(x4038_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x4034 = CounterChain.copy("x4072", "x4034")
      val ctr94 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4051_unit = CounterChain(name = "x4051_unit", ctr94)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x4034(0))), op=FixAdd, results=List(x4040))
      Stage(operands=List(x4040, CU.load(x3390_x4039)), op=FixMul, results=List(x4041))
      Stage(operands=List(x4041, CU.ctr(x3406(1))), op=FixAdd, results=List(x4042))
      Stage(operands=List(x4042, Const(4)), op=FixMul, results=List(x4043))
      Stage(operands=List(x4043, CU.load(x4038)), op=FixAdd, results=List(CU.scalarOut(x4035_b4316_x4050_b4318_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4035_b4317_x4050_b4319_s)))
    }
    val x4061 = Pipeline(name="x4061",parent=x4062) { implicit CU => 
      val ctr95 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4053 = CounterChain(name = "x4053", ctr95)
      var stage: List[Stage] = Nil
    }
    val x4063 = MemoryController(name="x4063",parent=x4072,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x4036_x4063 =  VectorFIFO(name="data",size=1).wtPort(x3427_x4057_x4061_v)
      val x4035_b4317_x4063 =  ScalarFIFO(name="size",size=1).wtPort(x4035_b4317_x4050_b4319_s)
      val x4035_b4316_x4063 =  ScalarFIFO(name="offset",size=1).wtPort(x4035_b4316_x4050_b4318_s)
    }
    val x4112 = StreamController(name="x4112",parent=x4194) { implicit CU => 
      val ctr98 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4074 = CounterChain(name = "x4074", ctr98)
    }
    val x4102 = Sequential(name="x4102",parent=x4112) { implicit CU => 
      val ctr99 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4102_unit = CounterChain(name = "x4102_unit", ctr99)
    }
    val x4091_0 = Pipeline(name="x4091_0",parent=x4102) { implicit CU => 
      val x4082 = CU.temp
      val x4081 = CU.temp
      val x4080 = CU.temp
      val x4083 = CU.temp
      val x4078 =  ScalarBuffer().wtPort(x4078_argin)
      val x3390_x4079 =  ScalarBuffer().wtPort(x3390_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x4074 = CounterChain.copy("x4112", "x4074")
      val ctr100 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4091_unit = CounterChain(name = "x4091_unit", ctr100)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x4074(0))), op=FixAdd, results=List(x4080))
      Stage(operands=List(x4080, CU.load(x3390_x4079)), op=FixMul, results=List(x4081))
      Stage(operands=List(x4081, CU.ctr(x3406(1))), op=FixAdd, results=List(x4082))
      Stage(operands=List(x4082, Const(4)), op=FixMul, results=List(x4083))
      Stage(operands=List(x4083, CU.load(x4078)), op=FixAdd, results=List(CU.scalarOut(x4075_b4322_x4090_b4324_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4075_b4323_x4090_b4325_s)))
    }
    val x4101 = Pipeline(name="x4101",parent=x4102) { implicit CU => 
      val ctr101 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4093 = CounterChain(name = "x4093", ctr101)
      var stage: List[Stage] = Nil
    }
    val x4103 = MemoryController(name="x4103",parent=x4112,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x4076_x4103 =  VectorFIFO(name="data",size=1).wtPort(x3428_x4097_x4101_v)
      val x4075_b4323_x4103 =  ScalarFIFO(name="size",size=1).wtPort(x4075_b4323_x4090_b4325_s)
      val x4075_b4322_x4103 =  ScalarFIFO(name="offset",size=1).wtPort(x4075_b4322_x4090_b4324_s)
    }
    val x4152 = StreamController(name="x4152",parent=x4194) { implicit CU => 
      val ctr104 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4114 = CounterChain(name = "x4114", ctr104)
    }
    val x4142 = Sequential(name="x4142",parent=x4152) { implicit CU => 
      val ctr105 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4142_unit = CounterChain(name = "x4142_unit", ctr105)
    }
    val x4131_0 = Pipeline(name="x4131_0",parent=x4142) { implicit CU => 
      val x4120 = CU.temp
      val x4123 = CU.temp
      val x4122 = CU.temp
      val x4121 = CU.temp
      val x3390_x4119 =  ScalarBuffer().wtPort(x3390_argin)
      val x4118 =  ScalarBuffer().wtPort(x4118_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x4114 = CounterChain.copy("x4152", "x4114")
      val ctr106 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4131_unit = CounterChain(name = "x4131_unit", ctr106)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x4114(0))), op=FixAdd, results=List(x4120))
      Stage(operands=List(x4120, CU.load(x3390_x4119)), op=FixMul, results=List(x4121))
      Stage(operands=List(x4121, CU.ctr(x3406(1))), op=FixAdd, results=List(x4122))
      Stage(operands=List(x4122, Const(4)), op=FixMul, results=List(x4123))
      Stage(operands=List(x4123, CU.load(x4118)), op=FixAdd, results=List(CU.scalarOut(x4115_b4328_x4130_b4330_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4115_b4329_x4130_b4331_s)))
    }
    val x4141 = Pipeline(name="x4141",parent=x4142) { implicit CU => 
      val ctr107 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4133 = CounterChain(name = "x4133", ctr107)
      var stage: List[Stage] = Nil
    }
    val x4143 = MemoryController(name="x4143",parent=x4152,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x4115_b4329_x4143 =  ScalarFIFO(name="size",size=1).wtPort(x4115_b4329_x4130_b4331_s)
      val x4116_x4143 =  VectorFIFO(name="data",size=1).wtPort(x3429_x4137_x4141_v)
      val x4115_b4328_x4143 =  ScalarFIFO(name="offset",size=1).wtPort(x4115_b4328_x4130_b4330_s)
    }
    val x4192 = StreamController(name="x4192",parent=x4194) { implicit CU => 
      val ctr110 = Counter(min=Const(0), max=Const(48), step=Const(1), par=1) // Counter
      val x4154 = CounterChain(name = "x4154", ctr110)
    }
    val x4182 = Sequential(name="x4182",parent=x4192) { implicit CU => 
      val ctr111 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4182_unit = CounterChain(name = "x4182_unit", ctr111)
    }
    val x4171_0 = Pipeline(name="x4171_0",parent=x4182) { implicit CU => 
      val x4163 = CU.temp
      val x4161 = CU.temp
      val x4162 = CU.temp
      val x4160 = CU.temp
      val x3390_x4159 =  ScalarBuffer().wtPort(x3390_argin)
      val x4158 =  ScalarBuffer().wtPort(x4158_argin)
      val x3406 = CounterChain.copy("x4194", "x3406")
      val x4154 = CounterChain.copy("x4192", "x4154")
      val ctr112 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x4171_unit = CounterChain(name = "x4171_unit", ctr112)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3406(0)), CU.ctr(x4154(0))), op=FixAdd, results=List(x4160))
      Stage(operands=List(x4160, CU.load(x3390_x4159)), op=FixMul, results=List(x4161))
      Stage(operands=List(x4161, CU.ctr(x3406(1))), op=FixAdd, results=List(x4162))
      Stage(operands=List(x4162, Const(4)), op=FixMul, results=List(x4163))
      Stage(operands=List(x4163, CU.load(x4158)), op=FixAdd, results=List(CU.scalarOut(x4155_b4334_x4170_b4336_s)))
      Stage(operands=List(Const(192)), op=Bypass, results=List(CU.scalarOut(x4155_b4335_x4170_b4337_s)))
    }
    val x4181 = Pipeline(name="x4181",parent=x4182) { implicit CU => 
      val ctr113 = Counter(min=Const(0), max=Const(48), step=Const(1), par=16) // Counter
      val x4173 = CounterChain(name = "x4173", ctr113)
      var stage: List[Stage] = Nil
    }
    val x4183 = MemoryController(name="x4183",parent=x4192,offchip=x3399_oc, mctpe=TileStore) { implicit CU => 
      val x4156_x4183 =  VectorFIFO(name="data",size=1).wtPort(x3430_x4177_x4181_v)
      val x4155_b4335_x4183 =  ScalarFIFO(name="size",size=1).wtPort(x4155_b4335_x4170_b4337_s)
      val x4155_b4334_x4183 =  ScalarFIFO(name="offset",size=1).wtPort(x4155_b4334_x4170_b4336_s)
    }
    
  }
}
