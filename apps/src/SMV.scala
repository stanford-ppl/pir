import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object SMV extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3462_x3494_data_v = Vector("x3462_x3494_data")
    val x3463_argin = ArgIn("x3463")
    val x3621_x3635_s = Scalar("x3621_x3635")
    val x3875_argin = ArgIn("x3875")
    val x3424_x3433_data_v = Vector("x3424_x3433_data")
    val x3453_x3456_s = Scalar("x3453_x3456")
    val x3804_argin = ArgIn("x3804")
    val x3661_b3978_x3692_b3986_s = Scalar("x3661_b3978_x3692_b3986")
    val x3847_argin = ArgIn("x3847")
    val x3765_x3773_s = Scalar("x3765_x3773")
    val x3660_b3977_x3685_b3985_s = Scalar("x3660_b3977_x3685_b3985")
    val x3400_x3858_x3862_v = Vector("x3400_x3858_x3862")
    val x3529_b3964_x3554_b3972_s = Scalar("x3529_b3964_x3554_b3972")
    val x3730_b3991_x3761_b3999_s = Scalar("x3730_b3991_x3761_b3999")
    val x3764_x3771_s = Scalar("x3764_x3771")
    val x3388_oc = OffChip("x3388")
    val x3565_x3573_s = Scalar("x3565_x3573")
    val x3390_oc = OffChip("x3390")
    val x3497_x3506_s = Scalar("x3497_x3506")
    val x3532_argin = ArgIn("x3532")
    val x3447_x3628_x3637_v = Vector("x3447_x3628_x3637")
    val x3766_x3775_s = Scalar("x3766_x3775")
    val x3648_x3651_s = Scalar("x3648_x3651")
    val x3604_argin = ArgIn("x3604")
    val x3425_argin = ArgIn("x3425")
    val x3530_b3965_x3561_b3973_s = Scalar("x3530_b3965_x3561_b3973")
    val x3798_x3811_data_v = Vector("x3798_x3811_data")
    val x3647_x3828_x3837_v = Vector("x3647_x3828_x3837")
    val x3423_b3946_x3431_b3948_s = Scalar("x3423_b3946_x3431_b3948")
    val x3404_b3942_x3412_b3944_s = Scalar("x3404_b3942_x3412_b3944")
    val x3731_x3763_data_v = Vector("x3731_x3763_data")
    val x3445_x3603_x3610_v = Vector("x3445_x3603_x3610")
    val x3660_b3976_x3685_b3984_s = Scalar("x3660_b3976_x3685_b3984")
    val x3662_x3694_data_v = Vector("x3662_x3694_data")
    val x3646_x3827_x3837_v = Vector("x3646_x3827_x3837")
    val x3597_x3609_s = Scalar("x3597_x3609")
    val x3404_b3943_x3412_b3945_s = Scalar("x3404_b3943_x3412_b3945")
    val x3872_b4007_x3881_b4009_s = Scalar("x3872_b4007_x3881_b4009")
    val x3531_x3563_data_v = Vector("x3531_x3563_data")
    val x3387_oc = OffChip("x3387")
    val x3844_b4002_x3853_b4004_s = Scalar("x3844_b4002_x3853_b4004")
    val x3653_x3656_s = Scalar("x3653_x3656")
    val x3821_x3835_s = Scalar("x3821_x3835")
    val x3566_x3575_s = Scalar("x3566_x3575")
    val x3844_b4003_x3853_b4005_s = Scalar("x3844_b4003_x3853_b4005")
    val x3461_b3953_x3492_b3961_s = Scalar("x3461_b3953_x3492_b3961")
    val x3400_x3640_v = Vector("x3400_x3640")
    val x3530_b3966_x3561_b3974_s = Scalar("x3530_b3966_x3561_b3974")
    val x3729_b3989_x3754_b3997_s = Scalar("x3729_b3989_x3754_b3997")
    val x3386_oc = OffChip("x3386")
    val x3405_x3414_data_v = Vector("x3405_x3414_data")
    val x3401_x3886_x3890_v = Vector("x3401_x3886_x3890")
    val x3461_b3952_x3492_b3960_s = Scalar("x3461_b3952_x3492_b3960")
    val x3460_b3951_x3485_b3959_s = Scalar("x3460_b3951_x3485_b3959")
    val x3496_x3504_s = Scalar("x3496_x3504")
    val x3383_argin = ArgIn("x3383")
    val x3406_argin = ArgIn("x3406")
    val x3530_b3967_x3561_b3975_s = Scalar("x3530_b3967_x3561_b3975")
    val x3661_b3980_x3692_b3988_s = Scalar("x3661_b3980_x3692_b3988")
    val x3797_x3809_s = Scalar("x3797_x3809")
    val x3696_x3704_s = Scalar("x3696_x3704")
    val x3729_b3990_x3754_b3998_s = Scalar("x3729_b3990_x3754_b3998")
    val x3461_b3954_x3492_b3962_s = Scalar("x3461_b3954_x3492_b3962")
    val x3661_b3979_x3692_b3987_s = Scalar("x3661_b3979_x3692_b3987")
    val x3401_x3840_v = Vector("x3401_x3840")
    val x3695_x3702_s = Scalar("x3695_x3702")
    val x3529_b3963_x3554_b3971_s = Scalar("x3529_b3963_x3554_b3971")
    val x3732_argin = ArgIn("x3732")
    val x3663_argin = ArgIn("x3663")
    val x3423_b3947_x3431_b3949_s = Scalar("x3423_b3947_x3431_b3949")
    val x3402_x3450_x3452_v = Vector("x3402_x3450_x3452")
    val x3730_b3992_x3761_b4000_s = Scalar("x3730_b3992_x3761_b4000")
    val x3460_b3950_x3485_b3958_s = Scalar("x3460_b3950_x3485_b3958")
    val x3645_x3803_x3810_v = Vector("x3645_x3803_x3810")
    val x3495_x3502_s = Scalar("x3495_x3502")
    val x3564_x3571_s = Scalar("x3564_x3571")
    val x3385_oc = OffChip("x3385")
    val x3872_b4006_x3881_b4008_s = Scalar("x3872_b4006_x3881_b4008")
    val x3598_x3611_data_v = Vector("x3598_x3611_data")
    val x3730_b3993_x3761_b4001_s = Scalar("x3730_b3993_x3761_b4001")
    val x3446_x3627_x3637_v = Vector("x3446_x3627_x3637")
    val x3448_x3451_s = Scalar("x3448_x3451")
    val x3697_x3706_s = Scalar("x3697_x3706")
    val x3403_x3650_x3652_v = Vector("x3403_x3650_x3652")
    val x3902 = Sequential(name="x3902",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3902_unit = CounterChain(name = "x3902_unit", ctr1)
    }
    val x3901 = MetaPipeline(name="x3901",parent=x3902) { implicit CU => 
      val x3383_x3397 =  ScalarBuffer().wtPort(x3383_argin)
      val ctr2 = Counter(min=Const(0), max=x3383_x3397.load, step=Const(384), par=2) // Counter
      val x3399 = CounterChain(name = "x3399", ctr2)
    }
    val x3400_dsp0 = MemoryPipeline(name="x3400_dsp0",parent="x3901") { implicit CU => 
      val x3640_x3640 =  VectorFIFO(size=1).wtPort(x3400_x3640_v)
      val x3444 = CounterChain.copy("x3642", "x3444")
      val x3856 = CounterChain.copy("x3862", "x3856")
      val x3400_x3858 =  SRAM(size=384,banking = Strided(1)).wtPort(x3640_x3640.readPort).rdPort(x3400_x3858_x3862_v).rdAddr(x3856(0)).wtAddr(x3444(0))
      var stage: List[Stage] = Nil
    }
    val x3401_dsp0 = MemoryPipeline(name="x3401_dsp0",parent="x3901") { implicit CU => 
      val x3840_x3840 =  VectorFIFO(size=1).wtPort(x3401_x3840_v)
      val x3644 = CounterChain.copy("x3842", "x3644")
      val x3884 = CounterChain.copy("x3890", "x3884")
      val x3401_x3886 =  SRAM(size=384,banking = Strided(1)).wtPort(x3840_x3840.readPort).rdPort(x3401_x3886_x3890_v).rdAddr(x3884(0)).wtAddr(x3644(0))
      var stage: List[Stage] = Nil
    }
    val x3402_dsp0 = MemoryPipeline(name="x3402_dsp0",parent="x3901") { implicit CU => 
      val x3420_x3420 =  VectorFIFO(size=1).wtPort(x3405_x3414_data_v)
      val x3416 = CounterChain.copy("x3421", "x3416")
      val x3444 = CounterChain.copy("x3642", "x3444")
      val x3402_x3450 =  SRAM(size=384,banking = Strided(1)).wtPort(x3420_x3420.readPort).rdPort(x3402_x3450_x3452_v).rdAddr(x3444(0)).wtAddr(x3416(0))
      var stage: List[Stage] = Nil
    }
    val x3403_dsp0 = MemoryPipeline(name="x3403_dsp0",parent="x3901") { implicit CU => 
      val x3439_x3439 =  VectorFIFO(size=1).wtPort(x3424_x3433_data_v)
      val x3435 = CounterChain.copy("x3440", "x3435")
      val x3644 = CounterChain.copy("x3842", "x3644")
      val x3403_x3650 =  SRAM(size=384,banking = Strided(1)).wtPort(x3439_x3439.readPort).rdPort(x3403_x3650_x3652_v).rdAddr(x3644(0)).wtAddr(x3435(0))
      var stage: List[Stage] = Nil
    }
    val x3422 = StreamController(name="x3422",parent=x3901) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3422_unit = CounterChain(name = "x3422_unit", ctr3)
    }
    val x3413 = Pipeline(name="x3413",parent=x3422) { implicit CU => 
      val x3407 = CU.temp
      val x3406 =  ScalarBuffer().wtPort(x3406_argin)
      val x3399 = CounterChain.copy("x3901", "x3399")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3413_unit = CounterChain(name = "x3413_unit", ctr4)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3399(0)), Const(4)), op=FixMul, results=List(x3407))
      Stage(operands=List(x3407, CU.load(x3406)), op=FixAdd, results=List(CU.scalarOut(x3404_b3942_x3412_b3944_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x3404_b3943_x3412_b3945_s)))
    }
    val x3414 = MemoryController(name="x3414",parent=x3422,offchip=x3387_oc, mctpe=TileLoad) { implicit CU => 
      val x3404_b3943_x3414 =  ScalarFIFO(name="size",size=1).wtPort(x3404_b3943_x3412_b3945_s)
      val x3404_b3942_x3414 =  ScalarFIFO(name="offset",size=1).wtPort(x3404_b3942_x3412_b3944_s)
      CU.newVout("data", x3405_x3414_data_v)
    }
    val x3421 = Pipeline(name="x3421",parent=x3422) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3416 = CounterChain(name = "x3416", ctr5)
      var stage: List[Stage] = Nil
    }
    val x3441 = StreamController(name="x3441",parent=x3901) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3441_unit = CounterChain(name = "x3441_unit", ctr6)
    }
    val x3432 = Pipeline(name="x3432",parent=x3441) { implicit CU => 
      val x3426 = CU.temp
      val x3425 =  ScalarBuffer().wtPort(x3425_argin)
      val x3399 = CounterChain.copy("x3901", "x3399")
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3432_unit = CounterChain(name = "x3432_unit", ctr7)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3399(0)), Const(4)), op=FixMul, results=List(x3426))
      Stage(operands=List(x3426, CU.load(x3425)), op=FixAdd, results=List(CU.scalarOut(x3423_b3946_x3431_b3948_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x3423_b3947_x3431_b3949_s)))
    }
    val x3433 = MemoryController(name="x3433",parent=x3441,offchip=x3387_oc, mctpe=TileLoad) { implicit CU => 
      val x3423_b3947_x3433 =  ScalarFIFO(name="size",size=1).wtPort(x3423_b3947_x3431_b3949_s)
      val x3423_b3946_x3433 =  ScalarFIFO(name="offset",size=1).wtPort(x3423_b3946_x3431_b3948_s)
      CU.newVout("data", x3424_x3433_data_v)
    }
    val x3440 = Pipeline(name="x3440",parent=x3441) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3435 = CounterChain(name = "x3435", ctr8)
      var stage: List[Stage] = Nil
    }
    val x3642 = MetaPipeline(name="x3642",parent=x3901) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x3444 = CounterChain(name = "x3444", ctr9)
    }
    val x3445_dsp0 = MemoryPipeline(name="x3445_dsp0",parent="x3642") { implicit CU => 
      val x3495_x3511 =  ScalarBuffer().wtPort(x3495_x3502_s)
      val x3523_x3523 =  VectorFIFO(size=1).wtPort(x3462_x3494_data_v)
      val x3510 = CounterChain.copy("x3524", "x3510")
      val x3600 = CounterChain.copy("x3610", "x3600")
      val x3445_x3603 =  SRAM(size=384,banking = Strided(1)).wtPort(x3523_x3523.readPort).rdPort(x3445_x3603_x3610_v).rdAddr(x3600(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3510(0)), CU.load(x3495_x3511)), op=FixSub, results=List(x3445_x3603.writeAddr))
    }
    val x3446_dsp0 = MemoryPipeline(name="x3446_dsp0",parent="x3642") { implicit CU => 
      val x3564_x3580 =  ScalarBuffer().wtPort(x3564_x3571_s)
      val x3592_x3592 =  VectorFIFO(size=1).wtPort(x3531_x3563_data_v)
      val x3579 = CounterChain.copy("x3593", "x3579")
      val x3624 = CounterChain.copy("x3637", "x3624")
      val x3446_x3627 =  SRAM(size=384,banking = Strided(1)).wtPort(x3592_x3592.readPort).rdPort(x3446_x3627_x3637_v).rdAddr(x3624(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3579(0)), CU.load(x3564_x3580)), op=FixSub, results=List(x3446_x3627.writeAddr))
    }
    val x3447_dsp0 = MemoryPipeline(name="x3447_dsp0",parent="x3642") { implicit CU => 
      val x3618_x3618 =  VectorFIFO(size=1).wtPort(x3598_x3611_data_v)
      val x3613 = CounterChain.copy("x3619", "x3613")
      val x3624 = CounterChain.copy("x3637", "x3624")
      val x3447_x3628 =  SRAM(size=384,banking = Strided(1)).wtPort(x3618_x3618.readPort).rdPort(x3447_x3628_x3637_v).rdAddr(x3624(0)).wtAddr(x3613(0))
      var stage: List[Stage] = Nil
    }
    val x3452 = Pipeline(name="x3452",parent=x3642) { implicit CU => 
      val x3450_x3450 =  VectorFIFO(size=1).wtPort(x3402_x3450_x3452_v)
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3452_unit = CounterChain(name = "x3452_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3450_x3450)), op=Bypass, results=List(CU.scalarOut(x3448_x3451_s)))
    }
    val x3457 = Pipeline(name="x3457",parent=x3642) { implicit CU => 
      val x3399 = CounterChain.copy("x3901", "x3399")
      val x3444 = CounterChain.copy("x3642", "x3444")
      val ctr11 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3457_unit = CounterChain(name = "x3457_unit", ctr11)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3399(0)), CU.ctr(x3444(0))), op=FixAdd, results=List(CU.scalarOut(x3453_x3456_s)))
    }
    val x3526 = StreamController(name="x3526",parent=x3642) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3459 = CounterChain(name = "x3459", ctr12)
    }
    val x3493 = Pipeline(name="x3493",parent=x3526) { implicit CU => 
      val x3489 = CU.temp
      val x3467 = CU.temp
      val x3472 = CU.temp
      val x3478 = CU.temp
      val x3475 = CU.temp
      val x3476 = CU.temp
      val x3471 = CU.temp
      val x3486 = CU.temp
      val x3488 = CU.temp
      val x3468 = CU.temp
      val x3474 = CU.temp
      val x3473 = CU.temp
      val x3469 = CU.temp
      val x3470 = CU.temp
      val x3466 = CU.temp
      val x3453_x3464 =  ScalarBuffer().wtPort(x3453_x3456_s)
      val x3448_x3465 =  ScalarBuffer().wtPort(x3448_x3451_s)
      val x3463 =  ScalarBuffer().wtPort(x3463_argin)
      val x3459 = CounterChain.copy("x3526", "x3459")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3493_unit = CounterChain(name = "x3493_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3453_x3464), CU.ctr(x3459(0))), op=FixAdd, results=List(x3466))
      Stage(operands=List(x3466, Const(60)), op=FixMul, results=List(x3467))
      Stage(operands=List(x3467, Const(4)), op=FixMul, results=List(x3468))
      Stage(operands=List(x3468, Const(64)), op=FixMod, results=List(x3475))
      Stage(operands=List(x3475, Const(4)), op=FixDiv, results=List(x3486, CU.scalarOut(x3461_b3953_x3492_b3961_s)))
      Stage(operands=List(CU.load(x3448_x3465), Const(4)), op=FixMul, results=List(x3469))
      Stage(operands=List(x3468, x3469), op=FixAdd, results=List(x3470))
      Stage(operands=List(x3470, Const(4)), op=FixDiv, results=List(CU.scalarOut(x3461_b3954_x3492_b3962_s)))
      Stage(operands=List(x3470, Const(64)), op=FixMod, results=List(x3471))
      Stage(operands=List(x3471, Const(0)), op=FixEql, results=List(x3472))
      Stage(operands=List(Const(64), x3471), op=FixSub, results=List(x3473))
      Stage(operands=List(x3472, Const(0), x3473), op=Mux, results=List(x3474))
      Stage(operands=List(x3474, Const(4)), op=FixDiv, results=List(x3488))
      Stage(operands=List(CU.load(x3448_x3465), x3486), op=FixAdd, results=List(x3489))
      Stage(operands=List(x3489, x3488), op=FixAdd, results=List(CU.scalarOut(x3461_b3952_x3492_b3960_s)))
      Stage(operands=List(x3469, x3475), op=FixAdd, results=List(x3476))
      Stage(operands=List(x3476, x3474), op=FixAdd, results=List(CU.scalarOut(x3460_b3951_x3485_b3959_s)))
      Stage(operands=List(x3468, x3475), op=FixSub, results=List(x3478))
      Stage(operands=List(x3478, CU.load(x3463)), op=FixAdd, results=List(CU.scalarOut(x3460_b3950_x3485_b3958_s)))
    }
    val x3494 = MemoryController(name="x3494",parent=x3526,offchip=x3385_oc, mctpe=TileLoad) { implicit CU => 
      val x3460_b3950_x3494 =  ScalarFIFO(name="offset",size=1).wtPort(x3460_b3950_x3485_b3958_s)
      val x3460_b3951_x3494 =  ScalarFIFO(name="size",size=1).wtPort(x3460_b3951_x3485_b3959_s)
      CU.newVout("data", x3462_x3494_data_v)
    }
    val x3525 = Sequential(name="x3525",parent=x3526) { implicit CU => 
      val ctr14 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3525_unit = CounterChain(name = "x3525_unit", ctr14)
    }
    val x3507 = Pipeline(name="x3507",parent=x3525) { implicit CU => 
      val x3461_b3953_x3500_b3956 =  ScalarFIFO(size=16).wtPort(x3461_b3953_x3492_b3961_s)
      val x3461_b3952_x3500_b3955 =  ScalarFIFO(size=16).wtPort(x3461_b3952_x3492_b3960_s)
      val x3461_b3954_x3500_b3957 =  ScalarFIFO(size=16).wtPort(x3461_b3954_x3492_b3962_s)
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3507_unit = CounterChain(name = "x3507_unit", ctr15)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3461_b3953_x3500_b3956)), op=Bypass, results=List(CU.scalarOut(x3495_x3502_s)))
      Stage(operands=List(CU.load(x3461_b3954_x3500_b3957)), op=Bypass, results=List(CU.scalarOut(x3496_x3504_s)))
      Stage(operands=List(CU.load(x3461_b3952_x3500_b3955)), op=Bypass, results=List(CU.scalarOut(x3497_x3506_s)))
    }
    val x3524 = Pipeline(name="x3524",parent=x3525) { implicit CU => 
      val x3495_x3511 =  ScalarBuffer().wtPort(x3495_x3502_s)
      val x3497_x3508 =  ScalarBuffer().wtPort(x3497_x3506_s)
      val x3496_x3512 =  ScalarBuffer().wtPort(x3496_x3504_s)
      val ctr16 = Counter(min=Const(0), max=x3497_x3508.load, step=Const(1), par=16) // Counter
      val x3510 = CounterChain(name = "x3510", ctr16)
      var stage: List[Stage] = Nil
    }
    val x3595 = StreamController(name="x3595",parent=x3642) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3528 = CounterChain(name = "x3528", ctr17)
    }
    val x3562 = Pipeline(name="x3562",parent=x3595) { implicit CU => 
      val x3541 = CU.temp
      val x3555 = CU.temp
      val x3537 = CU.temp
      val x3557 = CU.temp
      val x3542 = CU.temp
      val x3539 = CU.temp
      val x3543 = CU.temp
      val x3545 = CU.temp
      val x3540 = CU.temp
      val x3536 = CU.temp
      val x3544 = CU.temp
      val x3538 = CU.temp
      val x3535 = CU.temp
      val x3558 = CU.temp
      val x3547 = CU.temp
      val x3453_x3533 =  ScalarBuffer().wtPort(x3453_x3456_s)
      val x3448_x3534 =  ScalarBuffer().wtPort(x3448_x3451_s)
      val x3532 =  ScalarBuffer().wtPort(x3532_argin)
      val x3528 = CounterChain.copy("x3595", "x3528")
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3562_unit = CounterChain(name = "x3562_unit", ctr18)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3453_x3533), CU.ctr(x3528(0))), op=FixAdd, results=List(x3535))
      Stage(operands=List(x3535, Const(60)), op=FixMul, results=List(x3536))
      Stage(operands=List(x3536, Const(4)), op=FixMul, results=List(x3537))
      Stage(operands=List(x3537, Const(64)), op=FixMod, results=List(x3544))
      Stage(operands=List(x3544, Const(4)), op=FixDiv, results=List(x3555, CU.scalarOut(x3530_b3966_x3561_b3974_s)))
      Stage(operands=List(CU.load(x3448_x3534), Const(4)), op=FixMul, results=List(x3538))
      Stage(operands=List(x3537, x3538), op=FixAdd, results=List(x3539))
      Stage(operands=List(x3539, Const(4)), op=FixDiv, results=List(CU.scalarOut(x3530_b3967_x3561_b3975_s)))
      Stage(operands=List(x3539, Const(64)), op=FixMod, results=List(x3540))
      Stage(operands=List(x3540, Const(0)), op=FixEql, results=List(x3541))
      Stage(operands=List(Const(64), x3540), op=FixSub, results=List(x3542))
      Stage(operands=List(x3541, Const(0), x3542), op=Mux, results=List(x3543))
      Stage(operands=List(x3543, Const(4)), op=FixDiv, results=List(x3557))
      Stage(operands=List(CU.load(x3448_x3534), x3555), op=FixAdd, results=List(x3558))
      Stage(operands=List(x3558, x3557), op=FixAdd, results=List(CU.scalarOut(x3530_b3965_x3561_b3973_s)))
      Stage(operands=List(x3538, x3544), op=FixAdd, results=List(x3545))
      Stage(operands=List(x3545, x3543), op=FixAdd, results=List(CU.scalarOut(x3529_b3964_x3554_b3972_s)))
      Stage(operands=List(x3537, x3544), op=FixSub, results=List(x3547))
      Stage(operands=List(x3547, CU.load(x3532)), op=FixAdd, results=List(CU.scalarOut(x3529_b3963_x3554_b3971_s)))
    }
    val x3563 = MemoryController(name="x3563",parent=x3595,offchip=x3386_oc, mctpe=TileLoad) { implicit CU => 
      val x3529_b3964_x3563 =  ScalarFIFO(name="size",size=1).wtPort(x3529_b3964_x3554_b3972_s)
      val x3529_b3963_x3563 =  ScalarFIFO(name="offset",size=1).wtPort(x3529_b3963_x3554_b3971_s)
      CU.newVout("data", x3531_x3563_data_v)
    }
    val x3594 = Sequential(name="x3594",parent=x3595) { implicit CU => 
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3594_unit = CounterChain(name = "x3594_unit", ctr19)
    }
    val x3576 = Pipeline(name="x3576",parent=x3594) { implicit CU => 
      val x3530_b3967_x3569_b3970 =  ScalarFIFO(size=16).wtPort(x3530_b3967_x3561_b3975_s)
      val x3530_b3966_x3569_b3969 =  ScalarFIFO(size=16).wtPort(x3530_b3966_x3561_b3974_s)
      val x3530_b3965_x3569_b3968 =  ScalarFIFO(size=16).wtPort(x3530_b3965_x3561_b3973_s)
      val ctr20 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3576_unit = CounterChain(name = "x3576_unit", ctr20)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3530_b3966_x3569_b3969)), op=Bypass, results=List(CU.scalarOut(x3564_x3571_s)))
      Stage(operands=List(CU.load(x3530_b3967_x3569_b3970)), op=Bypass, results=List(CU.scalarOut(x3565_x3573_s)))
      Stage(operands=List(CU.load(x3530_b3965_x3569_b3968)), op=Bypass, results=List(CU.scalarOut(x3566_x3575_s)))
    }
    val x3593 = Pipeline(name="x3593",parent=x3594) { implicit CU => 
      val x3564_x3580 =  ScalarBuffer().wtPort(x3564_x3571_s)
      val x3566_x3577 =  ScalarBuffer().wtPort(x3566_x3575_s)
      val x3565_x3581 =  ScalarBuffer().wtPort(x3565_x3573_s)
      val ctr21 = Counter(min=Const(0), max=x3566_x3577.load, step=Const(1), par=16) // Counter
      val x3579 = CounterChain(name = "x3579", ctr21)
      var stage: List[Stage] = Nil
    }
    val x3620 = StreamController(name="x3620",parent=x3642) { implicit CU => 
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3620_unit = CounterChain(name = "x3620_unit", ctr22)
    }
    val x3610 = Pipeline(name="x3610",parent=x3620) { implicit CU => 
      val x3606 = CU.temp
      val x3603_x3603 =  VectorFIFO(size=1).wtPort(x3445_x3603_x3610_v)
      val x3604 =  ScalarBuffer().wtPort(x3604_argin)
      val ctr23 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x3600 = CounterChain(name = "x3600", ctr23)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3603_x3603), Const(4)), op=FixMul, results=List(x3606))
      Stage(operands=List(x3606, CU.load(x3604)), op=FixAdd, results=List(CU.scalarOut(x3597_x3609_s)))
    }
    val x3611 = MemoryController(name="x3611",parent=x3620,offchip=x3388_oc, mctpe=Gather) { implicit CU => 
      val x3597_x3611 =  ScalarFIFO(name="addr",size=1).wtPort(x3597_x3609_s)
      CU.newVout("data", x3598_x3611_data_v)
    }
    val x3619 = Pipeline(name="x3619",parent=x3620) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x3613 = CounterChain(name = "x3613", ctr24)
      var stage: List[Stage] = Nil
    }
    val x3637 = Pipeline(name="x3637",parent=x3642) { implicit CU => 
      val x3627_x3627 =  VectorFIFO(size=1).wtPort(x3446_x3627_x3637_v)
      val x3628_x3628 =  VectorFIFO(size=1).wtPort(x3447_x3628_x3637_v)
      val x3448_x3622 =  ScalarBuffer().wtPort(x3448_x3451_s)
      val ctr25 = Counter(min=Const(0), max=x3448_x3622.load, step=Const(1), par=16) // Counter
      val x3624 = CounterChain(name = "x3624", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3627_x3627), CU.load(x3628_x3628)), op=FixMul, results=List(CU.reduce))
      val (_, rr702) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr702), op=Bypass, results=List(CU.scalarOut(x3621_x3635_s)))
    }
    val x3641 = Pipeline(name="x3641",parent=x3642) { implicit CU => 
      val x3621_x3638 =  ScalarBuffer().wtPort(x3621_x3635_s)
      val ctr26 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3641_unit = CounterChain(name = "x3641_unit", ctr26)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3621_x3638)), op=Bypass, results=List(CU.vecOut(x3400_x3640_v)))
    }
    val x3842 = MetaPipeline(name="x3842",parent=x3901) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x3644 = CounterChain(name = "x3644", ctr27)
    }
    val x3645_dsp0 = MemoryPipeline(name="x3645_dsp0",parent="x3842") { implicit CU => 
      val x3695_x3711 =  ScalarBuffer().wtPort(x3695_x3702_s)
      val x3723_x3723 =  VectorFIFO(size=1).wtPort(x3662_x3694_data_v)
      val x3710 = CounterChain.copy("x3724", "x3710")
      val x3800 = CounterChain.copy("x3810", "x3800")
      val x3645_x3803 =  SRAM(size=384,banking = Strided(1)).wtPort(x3723_x3723.readPort).rdPort(x3645_x3803_x3810_v).rdAddr(x3800(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3710(0)), CU.load(x3695_x3711)), op=FixSub, results=List(x3645_x3803.writeAddr))
    }
    val x3646_dsp0 = MemoryPipeline(name="x3646_dsp0",parent="x3842") { implicit CU => 
      val x3764_x3780 =  ScalarBuffer().wtPort(x3764_x3771_s)
      val x3792_x3792 =  VectorFIFO(size=1).wtPort(x3731_x3763_data_v)
      val x3779 = CounterChain.copy("x3793", "x3779")
      val x3824 = CounterChain.copy("x3837", "x3824")
      val x3646_x3827 =  SRAM(size=384,banking = Strided(1)).wtPort(x3792_x3792.readPort).rdPort(x3646_x3827_x3837_v).rdAddr(x3824(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3779(0)), CU.load(x3764_x3780)), op=FixSub, results=List(x3646_x3827.writeAddr))
    }
    val x3647_dsp0 = MemoryPipeline(name="x3647_dsp0",parent="x3842") { implicit CU => 
      val x3818_x3818 =  VectorFIFO(size=1).wtPort(x3798_x3811_data_v)
      val x3813 = CounterChain.copy("x3819", "x3813")
      val x3824 = CounterChain.copy("x3837", "x3824")
      val x3647_x3828 =  SRAM(size=384,banking = Strided(1)).wtPort(x3818_x3818.readPort).rdPort(x3647_x3828_x3837_v).rdAddr(x3824(0)).wtAddr(x3813(0))
      var stage: List[Stage] = Nil
    }
    val x3652 = Pipeline(name="x3652",parent=x3842) { implicit CU => 
      val x3650_x3650 =  VectorFIFO(size=1).wtPort(x3403_x3650_x3652_v)
      val ctr28 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3652_unit = CounterChain(name = "x3652_unit", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3650_x3650)), op=Bypass, results=List(CU.scalarOut(x3648_x3651_s)))
    }
    val x3657 = Pipeline(name="x3657",parent=x3842) { implicit CU => 
      val x3399 = CounterChain.copy("x3901", "x3399")
      val x3644 = CounterChain.copy("x3842", "x3644")
      val ctr29 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3657_unit = CounterChain(name = "x3657_unit", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3399(0)), CU.ctr(x3644(0))), op=FixAdd, results=List(CU.scalarOut(x3653_x3656_s)))
    }
    val x3726 = StreamController(name="x3726",parent=x3842) { implicit CU => 
      val ctr30 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3659 = CounterChain(name = "x3659", ctr30)
    }
    val x3693 = Pipeline(name="x3693",parent=x3726) { implicit CU => 
      val x3675 = CU.temp
      val x3688 = CU.temp
      val x3667 = CU.temp
      val x3674 = CU.temp
      val x3678 = CU.temp
      val x3673 = CU.temp
      val x3666 = CU.temp
      val x3668 = CU.temp
      val x3671 = CU.temp
      val x3689 = CU.temp
      val x3676 = CU.temp
      val x3672 = CU.temp
      val x3669 = CU.temp
      val x3686 = CU.temp
      val x3670 = CU.temp
      val x3663 =  ScalarBuffer().wtPort(x3663_argin)
      val x3648_x3665 =  ScalarBuffer().wtPort(x3648_x3651_s)
      val x3653_x3664 =  ScalarBuffer().wtPort(x3653_x3656_s)
      val x3659 = CounterChain.copy("x3726", "x3659")
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3693_unit = CounterChain(name = "x3693_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3653_x3664), CU.ctr(x3659(0))), op=FixAdd, results=List(x3666))
      Stage(operands=List(x3666, Const(60)), op=FixMul, results=List(x3667))
      Stage(operands=List(x3667, Const(4)), op=FixMul, results=List(x3668))
      Stage(operands=List(x3668, Const(64)), op=FixMod, results=List(x3675))
      Stage(operands=List(x3675, Const(4)), op=FixDiv, results=List(x3686, CU.scalarOut(x3661_b3979_x3692_b3987_s)))
      Stage(operands=List(CU.load(x3648_x3665), Const(4)), op=FixMul, results=List(x3669))
      Stage(operands=List(x3668, x3669), op=FixAdd, results=List(x3670))
      Stage(operands=List(x3670, Const(4)), op=FixDiv, results=List(CU.scalarOut(x3661_b3980_x3692_b3988_s)))
      Stage(operands=List(x3670, Const(64)), op=FixMod, results=List(x3671))
      Stage(operands=List(x3671, Const(0)), op=FixEql, results=List(x3672))
      Stage(operands=List(Const(64), x3671), op=FixSub, results=List(x3673))
      Stage(operands=List(x3672, Const(0), x3673), op=Mux, results=List(x3674))
      Stage(operands=List(x3674, Const(4)), op=FixDiv, results=List(x3688))
      Stage(operands=List(CU.load(x3648_x3665), x3686), op=FixAdd, results=List(x3689))
      Stage(operands=List(x3689, x3688), op=FixAdd, results=List(CU.scalarOut(x3661_b3978_x3692_b3986_s)))
      Stage(operands=List(x3669, x3675), op=FixAdd, results=List(x3676))
      Stage(operands=List(x3676, x3674), op=FixAdd, results=List(CU.scalarOut(x3660_b3977_x3685_b3985_s)))
      Stage(operands=List(x3668, x3675), op=FixSub, results=List(x3678))
      Stage(operands=List(x3678, CU.load(x3663)), op=FixAdd, results=List(CU.scalarOut(x3660_b3976_x3685_b3984_s)))
    }
    val x3694 = MemoryController(name="x3694",parent=x3726,offchip=x3385_oc, mctpe=TileLoad) { implicit CU => 
      val x3660_b3977_x3694 =  ScalarFIFO(name="size",size=1).wtPort(x3660_b3977_x3685_b3985_s)
      val x3660_b3976_x3694 =  ScalarFIFO(name="offset",size=1).wtPort(x3660_b3976_x3685_b3984_s)
      CU.newVout("data", x3662_x3694_data_v)
    }
    val x3725 = Sequential(name="x3725",parent=x3726) { implicit CU => 
      val ctr32 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3725_unit = CounterChain(name = "x3725_unit", ctr32)
    }
    val x3707 = Pipeline(name="x3707",parent=x3725) { implicit CU => 
      val x3661_b3980_x3700_b3983 =  ScalarFIFO(size=16).wtPort(x3661_b3980_x3692_b3988_s)
      val x3661_b3979_x3700_b3982 =  ScalarFIFO(size=16).wtPort(x3661_b3979_x3692_b3987_s)
      val x3661_b3978_x3700_b3981 =  ScalarFIFO(size=16).wtPort(x3661_b3978_x3692_b3986_s)
      val ctr33 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3707_unit = CounterChain(name = "x3707_unit", ctr33)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3661_b3979_x3700_b3982)), op=Bypass, results=List(CU.scalarOut(x3695_x3702_s)))
      Stage(operands=List(CU.load(x3661_b3980_x3700_b3983)), op=Bypass, results=List(CU.scalarOut(x3696_x3704_s)))
      Stage(operands=List(CU.load(x3661_b3978_x3700_b3981)), op=Bypass, results=List(CU.scalarOut(x3697_x3706_s)))
    }
    val x3724 = Pipeline(name="x3724",parent=x3725) { implicit CU => 
      val x3695_x3711 =  ScalarBuffer().wtPort(x3695_x3702_s)
      val x3697_x3708 =  ScalarBuffer().wtPort(x3697_x3706_s)
      val x3696_x3712 =  ScalarBuffer().wtPort(x3696_x3704_s)
      val ctr34 = Counter(min=Const(0), max=x3697_x3708.load, step=Const(1), par=16) // Counter
      val x3710 = CounterChain(name = "x3710", ctr34)
      var stage: List[Stage] = Nil
    }
    val x3795 = StreamController(name="x3795",parent=x3842) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3728 = CounterChain(name = "x3728", ctr35)
    }
    val x3762 = Pipeline(name="x3762",parent=x3795) { implicit CU => 
      val x3739 = CU.temp
      val x3747 = CU.temp
      val x3744 = CU.temp
      val x3740 = CU.temp
      val x3738 = CU.temp
      val x3741 = CU.temp
      val x3742 = CU.temp
      val x3755 = CU.temp
      val x3758 = CU.temp
      val x3737 = CU.temp
      val x3735 = CU.temp
      val x3745 = CU.temp
      val x3743 = CU.temp
      val x3757 = CU.temp
      val x3736 = CU.temp
      val x3648_x3734 =  ScalarBuffer().wtPort(x3648_x3651_s)
      val x3653_x3733 =  ScalarBuffer().wtPort(x3653_x3656_s)
      val x3732 =  ScalarBuffer().wtPort(x3732_argin)
      val x3728 = CounterChain.copy("x3795", "x3728")
      val ctr36 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3762_unit = CounterChain(name = "x3762_unit", ctr36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3653_x3733), CU.ctr(x3728(0))), op=FixAdd, results=List(x3735))
      Stage(operands=List(x3735, Const(60)), op=FixMul, results=List(x3736))
      Stage(operands=List(x3736, Const(4)), op=FixMul, results=List(x3737))
      Stage(operands=List(x3737, Const(64)), op=FixMod, results=List(x3744))
      Stage(operands=List(x3744, Const(4)), op=FixDiv, results=List(x3755, CU.scalarOut(x3730_b3992_x3761_b4000_s)))
      Stage(operands=List(CU.load(x3648_x3734), Const(4)), op=FixMul, results=List(x3738))
      Stage(operands=List(x3737, x3738), op=FixAdd, results=List(x3739))
      Stage(operands=List(x3739, Const(4)), op=FixDiv, results=List(CU.scalarOut(x3730_b3993_x3761_b4001_s)))
      Stage(operands=List(x3739, Const(64)), op=FixMod, results=List(x3740))
      Stage(operands=List(x3740, Const(0)), op=FixEql, results=List(x3741))
      Stage(operands=List(Const(64), x3740), op=FixSub, results=List(x3742))
      Stage(operands=List(x3741, Const(0), x3742), op=Mux, results=List(x3743))
      Stage(operands=List(x3743, Const(4)), op=FixDiv, results=List(x3757))
      Stage(operands=List(CU.load(x3648_x3734), x3755), op=FixAdd, results=List(x3758))
      Stage(operands=List(x3758, x3757), op=FixAdd, results=List(CU.scalarOut(x3730_b3991_x3761_b3999_s)))
      Stage(operands=List(x3738, x3744), op=FixAdd, results=List(x3745))
      Stage(operands=List(x3745, x3743), op=FixAdd, results=List(CU.scalarOut(x3729_b3990_x3754_b3998_s)))
      Stage(operands=List(x3737, x3744), op=FixSub, results=List(x3747))
      Stage(operands=List(x3747, CU.load(x3732)), op=FixAdd, results=List(CU.scalarOut(x3729_b3989_x3754_b3997_s)))
    }
    val x3763 = MemoryController(name="x3763",parent=x3795,offchip=x3386_oc, mctpe=TileLoad) { implicit CU => 
      val x3729_b3989_x3763 =  ScalarFIFO(name="offset",size=1).wtPort(x3729_b3989_x3754_b3997_s)
      val x3729_b3990_x3763 =  ScalarFIFO(name="size",size=1).wtPort(x3729_b3990_x3754_b3998_s)
      CU.newVout("data", x3731_x3763_data_v)
    }
    val x3794 = Sequential(name="x3794",parent=x3795) { implicit CU => 
      val ctr37 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3794_unit = CounterChain(name = "x3794_unit", ctr37)
    }
    val x3776 = Pipeline(name="x3776",parent=x3794) { implicit CU => 
      val x3730_b3991_x3769_b3994 =  ScalarFIFO(size=16).wtPort(x3730_b3991_x3761_b3999_s)
      val x3730_b3993_x3769_b3996 =  ScalarFIFO(size=16).wtPort(x3730_b3993_x3761_b4001_s)
      val x3730_b3992_x3769_b3995 =  ScalarFIFO(size=16).wtPort(x3730_b3992_x3761_b4000_s)
      val ctr38 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3776_unit = CounterChain(name = "x3776_unit", ctr38)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3730_b3992_x3769_b3995)), op=Bypass, results=List(CU.scalarOut(x3764_x3771_s)))
      Stage(operands=List(CU.load(x3730_b3993_x3769_b3996)), op=Bypass, results=List(CU.scalarOut(x3765_x3773_s)))
      Stage(operands=List(CU.load(x3730_b3991_x3769_b3994)), op=Bypass, results=List(CU.scalarOut(x3766_x3775_s)))
    }
    val x3793 = Pipeline(name="x3793",parent=x3794) { implicit CU => 
      val x3764_x3780 =  ScalarBuffer().wtPort(x3764_x3771_s)
      val x3766_x3777 =  ScalarBuffer().wtPort(x3766_x3775_s)
      val x3765_x3781 =  ScalarBuffer().wtPort(x3765_x3773_s)
      val ctr39 = Counter(min=Const(0), max=x3766_x3777.load, step=Const(1), par=16) // Counter
      val x3779 = CounterChain(name = "x3779", ctr39)
      var stage: List[Stage] = Nil
    }
    val x3820 = StreamController(name="x3820",parent=x3842) { implicit CU => 
      val ctr40 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3820_unit = CounterChain(name = "x3820_unit", ctr40)
    }
    val x3810 = Pipeline(name="x3810",parent=x3820) { implicit CU => 
      val x3806 = CU.temp
      val x3803_x3803 =  VectorFIFO(size=1).wtPort(x3645_x3803_x3810_v)
      val x3804 =  ScalarBuffer().wtPort(x3804_argin)
      val ctr41 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x3800 = CounterChain(name = "x3800", ctr41)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3803_x3803), Const(4)), op=FixMul, results=List(x3806))
      Stage(operands=List(x3806, CU.load(x3804)), op=FixAdd, results=List(CU.scalarOut(x3797_x3809_s)))
    }
    val x3811 = MemoryController(name="x3811",parent=x3820,offchip=x3388_oc, mctpe=Gather) { implicit CU => 
      val x3797_x3811 =  ScalarFIFO(name="addr",size=1).wtPort(x3797_x3809_s)
      CU.newVout("data", x3798_x3811_data_v)
    }
    val x3819 = Pipeline(name="x3819",parent=x3820) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(384), step=Const(1), par=1) // Counter
      val x3813 = CounterChain(name = "x3813", ctr42)
      var stage: List[Stage] = Nil
    }
    val x3837 = Pipeline(name="x3837",parent=x3842) { implicit CU => 
      val x3648_x3822 =  ScalarBuffer().wtPort(x3648_x3651_s)
      val x3827_x3827 =  VectorFIFO(size=1).wtPort(x3646_x3827_x3837_v)
      val x3828_x3828 =  VectorFIFO(size=1).wtPort(x3647_x3828_x3837_v)
      val ctr43 = Counter(min=Const(0), max=x3648_x3822.load, step=Const(1), par=16) // Counter
      val x3824 = CounterChain(name = "x3824", ctr43)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3827_x3827), CU.load(x3828_x3828)), op=FixMul, results=List(CU.reduce))
      val (_, rr777) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr777), op=Bypass, results=List(CU.scalarOut(x3821_x3835_s)))
    }
    val x3841 = Pipeline(name="x3841",parent=x3842) { implicit CU => 
      val x3821_x3838 =  ScalarBuffer().wtPort(x3821_x3835_s)
      val ctr44 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3841_unit = CounterChain(name = "x3841_unit", ctr44)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3821_x3838)), op=Bypass, results=List(CU.vecOut(x3401_x3840_v)))
    }
    val x3871 = StreamController(name="x3871",parent=x3901) { implicit CU => 
      val ctr45 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3871_unit = CounterChain(name = "x3871_unit", ctr45)
    }
    val x3863 = Sequential(name="x3863",parent=x3871) { implicit CU => 
      val ctr46 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3863_unit = CounterChain(name = "x3863_unit", ctr46)
    }
    val x3854 = Pipeline(name="x3854",parent=x3863) { implicit CU => 
      val x3848 = CU.temp
      val x3847 =  ScalarBuffer().wtPort(x3847_argin)
      val x3399 = CounterChain.copy("x3901", "x3399")
      val ctr47 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3854_unit = CounterChain(name = "x3854_unit", ctr47)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3399(0)), Const(4)), op=FixMul, results=List(x3848))
      Stage(operands=List(x3848, CU.load(x3847)), op=FixAdd, results=List(CU.scalarOut(x3844_b4002_x3853_b4004_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x3844_b4003_x3853_b4005_s)))
    }
    val x3862 = Pipeline(name="x3862",parent=x3863) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3856 = CounterChain(name = "x3856", ctr48)
      var stage: List[Stage] = Nil
    }
    val x3864 = MemoryController(name="x3864",parent=x3871,offchip=x3390_oc, mctpe=TileStore) { implicit CU => 
      val x3845_x3864 =  VectorFIFO(name="data",size=1).wtPort(x3400_x3858_x3862_v)
      val x3844_b4003_x3864 =  ScalarFIFO(name="size",size=1).wtPort(x3844_b4003_x3853_b4005_s)
      val x3844_b4002_x3864 =  ScalarFIFO(name="offset",size=1).wtPort(x3844_b4002_x3853_b4004_s)
    }
    val x3899 = StreamController(name="x3899",parent=x3901) { implicit CU => 
      val ctr51 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3899_unit = CounterChain(name = "x3899_unit", ctr51)
    }
    val x3891 = Sequential(name="x3891",parent=x3899) { implicit CU => 
      val ctr52 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3891_unit = CounterChain(name = "x3891_unit", ctr52)
    }
    val x3882 = Pipeline(name="x3882",parent=x3891) { implicit CU => 
      val x3876 = CU.temp
      val x3875 =  ScalarBuffer().wtPort(x3875_argin)
      val x3399 = CounterChain.copy("x3901", "x3399")
      val ctr53 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x3882_unit = CounterChain(name = "x3882_unit", ctr53)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3399(0)), Const(4)), op=FixMul, results=List(x3876))
      Stage(operands=List(x3876, CU.load(x3875)), op=FixAdd, results=List(CU.scalarOut(x3872_b4006_x3881_b4008_s)))
      Stage(operands=List(Const(1536)), op=Bypass, results=List(CU.scalarOut(x3872_b4007_x3881_b4009_s)))
    }
    val x3890 = Pipeline(name="x3890",parent=x3891) { implicit CU => 
      val ctr54 = Counter(min=Const(0), max=Const(384), step=Const(1), par=16) // Counter
      val x3884 = CounterChain(name = "x3884", ctr54)
      var stage: List[Stage] = Nil
    }
    val x3892 = MemoryController(name="x3892",parent=x3899,offchip=x3390_oc, mctpe=TileStore) { implicit CU => 
      val x3872_b4007_x3892 =  ScalarFIFO(name="size",size=1).wtPort(x3872_b4007_x3881_b4009_s)
      val x3872_b4006_x3892 =  ScalarFIFO(name="offset",size=1).wtPort(x3872_b4006_x3881_b4008_s)
      val x3873_x3892 =  VectorFIFO(name="data",size=1).wtPort(x3401_x3886_x3890_v)
    }
    
  }
}
