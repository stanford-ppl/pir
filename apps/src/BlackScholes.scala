import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object BlackScholes extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3618_b3951_x3624_b3954_scalar = Scalar("x3618_b3951_x3624_b3954")
    val x3639_b3959_x3645_b3962_scalar = Scalar("x3639_b3959_x3645_b3962")
    val x3797_b3977_x3804_b3982_scalar = Scalar("x3797_b3977_x3804_b3982")
    val x3621_argin = ArgIn("x3621")
    val x3576_b3941_x3582_b3944_scalar = Scalar("x3576_b3941_x3582_b3944")
    val x3570_x3705_x3796_vector = Vector("x3570_x3705_x3796")
    val x3797_b3976_x3804_b3981_scalar = Scalar("x3797_b3976_x3804_b3981")
    val x3575_x3795_vector = Vector("x3575_x3795")
    val x3597_b3946_x3603_b3949_scalar = Scalar("x3597_b3946_x3603_b3949")
    val x3545_oc = OffChip("x3545")
    val x3572_x3656_vector = Vector("x3572_x3656")
    val x3797_b3975_x3804_b3980_scalar = Scalar("x3797_b3975_x3804_b3980")
    val x3681_b3969_x3687_b3972_scalar = Scalar("x3681_b3969_x3687_b3972")
    val x3661_x3667_scalar = Scalar("x3661_x3667")
    val x3801_argin = ArgIn("x3801")
    val x3684_argin = ArgIn("x3684")
    val x3574_x3709_x3796_vector = Vector("x3574_x3709_x3796")
    val x3598_x3604_scalar = Scalar("x3598_x3604")
    val x3660_b3963_x3666_b3966_scalar = Scalar("x3660_b3963_x3666_b3966")
    val x3600_argin = ArgIn("x3600")
    val x3618_b3953_x3624_b3956_scalar = Scalar("x3618_b3953_x3624_b3956")
    val x3639_b3958_x3645_b3961_scalar = Scalar("x3639_b3958_x3645_b3961")
    val x3799_b3978_x3813_b3983_vector = Vector("x3799_b3978_x3813_b3983")
    val x3574_x3698_vector = Vector("x3574_x3698")
    val x3618_b3952_x3624_b3955_scalar = Scalar("x3618_b3952_x3624_b3955")
    val x3576_b3940_x3582_b3943_scalar = Scalar("x3576_b3940_x3582_b3943")
    val x3551_oc = OffChip("x3551")
    val x3597_b3945_x3603_b3948_scalar = Scalar("x3597_b3945_x3603_b3948")
    val x3557_oc = OffChip("x3557")
    val x3571_x3635_vector = Vector("x3571_x3635")
    val x3660_b3965_x3666_b3968_scalar = Scalar("x3660_b3965_x3666_b3968")
    val x3576_b3939_x3582_b3942_scalar = Scalar("x3576_b3939_x3582_b3942")
    val x3798_x3805_scalar = Scalar("x3798_x3805")
    val x3639_b3957_x3645_b3960_scalar = Scalar("x3639_b3957_x3645_b3960")
    val x3642_argin = ArgIn("x3642")
    val x3569_x3593_vector = Vector("x3569_x3593")
    val x3575_x3809_x3814_vector = Vector("x3575_x3809_x3814")
    val x3553_oc = OffChip("x3553")
    val x3541_argin = ArgIn("x3541")
    val x3549_oc = OffChip("x3549")
    val x3555_oc = OffChip("x3555")
    val x3619_x3625_scalar = Scalar("x3619_x3625")
    val x3570_x3614_vector = Vector("x3570_x3614")
    val x3572_x3707_x3796_vector = Vector("x3572_x3707_x3796")
    val x3660_b3964_x3666_b3967_scalar = Scalar("x3660_b3964_x3666_b3967")
    val x3682_x3688_scalar = Scalar("x3682_x3688")
    val x3569_x3710_x3796_vector = Vector("x3569_x3710_x3796")
    val x3681_b3970_x3687_b3973_scalar = Scalar("x3681_b3970_x3687_b3973")
    val x3573_x3677_vector = Vector("x3573_x3677")
    val x3597_b3947_x3603_b3950_scalar = Scalar("x3597_b3947_x3603_b3950")
    val x3681_b3971_x3687_b3974_scalar = Scalar("x3681_b3971_x3687_b3974")
    val x3640_x3646_scalar = Scalar("x3640_x3646")
    val x3663_argin = ArgIn("x3663")
    val x3573_x3708_x3796_vector = Vector("x3573_x3708_x3796")
    val x3577_x3583_scalar = Scalar("x3577_x3583")
    val x3571_x3706_x3796_vector = Vector("x3571_x3706_x3796")
    val x3547_oc = OffChip("x3547")
    val x3579_argin = ArgIn("x3579")
    val x3799_b3979_x3813_b3984_vector = Vector("x3799_b3979_x3813_b3984")
    val x3822 = Sequential(name="x3822",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3821 = MetaPipeline(name="x3821",parent=x3822) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, x3541_x3566.load, Const("640i").out) // Counter
      val x3568 = CounterChain(name = "x3568", ctr1)
      val x3541_x3566 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3541_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3569_dsp0 = MemoryPipeline(name="x3569_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3589 = CounterChain.copy("x3594", "x3589")
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3569_x3710 = SRAM(size = 640, banking = Strided(1)).wtPort(x3569_x3593_vector).rdPort(x3569_x3710_x3796_vector)
      var stage: List[Stage] = Nil
    }
    val x3570_dsp0 = MemoryPipeline(name="x3570_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3610 = CounterChain.copy("x3615", "x3610")
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3570_x3705 = SRAM(size = 640, banking = Strided(1)).wtPort(x3570_x3614_vector).rdPort(x3570_x3705_x3796_vector)
      var stage: List[Stage] = Nil
    }
    val x3571_dsp0 = MemoryPipeline(name="x3571_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3631 = CounterChain.copy("x3636", "x3631")
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3571_x3706 = SRAM(size = 640, banking = Strided(1)).wtPort(x3571_x3635_vector).rdPort(x3571_x3706_x3796_vector)
      var stage: List[Stage] = Nil
    }
    val x3572_dsp0 = MemoryPipeline(name="x3572_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3652 = CounterChain.copy("x3657", "x3652")
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3572_x3707 = SRAM(size = 640, banking = Strided(1)).wtPort(x3572_x3656_vector).rdPort(x3572_x3707_x3796_vector)
      var stage: List[Stage] = Nil
    }
    val x3573_dsp0 = MemoryPipeline(name="x3573_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3673 = CounterChain.copy("x3678", "x3673")
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3573_x3708 = SRAM(size = 640, banking = Strided(1)).wtPort(x3573_x3677_vector).rdPort(x3573_x3708_x3796_vector)
      var stage: List[Stage] = Nil
    }
    val x3574_dsp0 = MemoryPipeline(name="x3574_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3694 = CounterChain.copy("x3699", "x3694")
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3574_x3709 = SRAM(size = 640, banking = Strided(1)).wtPort(x3574_x3698_vector).rdPort(x3574_x3709_x3796_vector)
      var stage: List[Stage] = Nil
    }
    val x3575_dsp0 = MemoryPipeline(name="x3575_dsp0",parent="x3821") { implicit CU => 
      val stage0 = CU.emptyStage
      val x3704 = CounterChain.copy("x3796", "x3704")
      val x3808 = CounterChain.copy("x3814", "x3808")
      val x3575_x3809 = SRAM(size = 640, banking = Strided(1)).wtPort(x3575_x3795_vector).rdPort(x3575_x3809_x3814_vector)
      var stage: List[Stage] = Nil
    }
    val x3596 = Sequential(name="x3596",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3584 = UnitPipeline(name="x3584",parent=x3596) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr160 = CU.temp
      val tr161 = CU.temp
      val tr162 = CU.temp
      val tr163 = CU.temp
      val tr164 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3579 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3579_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr160)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr160), CU.load(stage(1), x3579)), op=FixAdd, results=List(CU.temp(stage(2), tr161)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr162)), op=Bypass, results=List(CU.scalarOut(stage(3), x3576_b3939_x3582_b3942_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr163)), op=Bypass, results=List(CU.scalarOut(stage(4), x3576_b3940_x3582_b3943_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr164)), op=Bypass, results=List(CU.scalarOut(stage(5), x3576_b3941_x3582_b3944_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3577_x3583_scalar)))
    }
    val x3585 = Fringe(name="x3585",parent=x3596,dram=x3545_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3576_b3941_x3585 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3576_b3941_x3582_b3944_scalar).rdPort(None)
      val x3576_b3940_x3585 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3576_b3940_x3582_b3943_scalar).rdPort(None)
      val x3576_b3939_x3585 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3576_b3939_x3582_b3942_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3595 = Sequential(name="x3595",parent=x3596) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3587 = Sequential(name="x3587",parent=x3595) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3594 = Pipeline(name="x3594",parent=x3595) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr2 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3589 = CounterChain(name = "x3589", ctr2)
      val x3578_x3590 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3578_x3590.load), op=Bypass, results=List(CU.vecOut(stage(1), x3569_x3593_vector)))
    }
    val x3617 = Sequential(name="x3617",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3605 = UnitPipeline(name="x3605",parent=x3617) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr167 = CU.temp
      val tr168 = CU.temp
      val tr169 = CU.temp
      val tr170 = CU.temp
      val tr171 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3600 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3600_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr167)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr167), CU.load(stage(1), x3600)), op=FixAdd, results=List(CU.temp(stage(2), tr168)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr169)), op=Bypass, results=List(CU.scalarOut(stage(3), x3597_b3945_x3603_b3948_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr170)), op=Bypass, results=List(CU.scalarOut(stage(4), x3597_b3946_x3603_b3949_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr171)), op=Bypass, results=List(CU.scalarOut(stage(5), x3597_b3947_x3603_b3950_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3598_x3604_scalar)))
    }
    val x3606 = Fringe(name="x3606",parent=x3617,dram=x3547_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3597_b3947_x3606 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3597_b3947_x3603_b3950_scalar).rdPort(None)
      val x3597_b3946_x3606 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3597_b3946_x3603_b3949_scalar).rdPort(None)
      val x3597_b3945_x3606 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3597_b3945_x3603_b3948_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3616 = Sequential(name="x3616",parent=x3617) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3608 = Sequential(name="x3608",parent=x3616) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3615 = Pipeline(name="x3615",parent=x3616) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3610 = CounterChain(name = "x3610", ctr3)
      val x3599_x3611 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3599_x3611.load), op=Bypass, results=List(CU.vecOut(stage(1), x3570_x3614_vector)))
    }
    val x3638 = Sequential(name="x3638",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3626 = UnitPipeline(name="x3626",parent=x3638) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr174 = CU.temp
      val tr175 = CU.temp
      val tr176 = CU.temp
      val tr177 = CU.temp
      val tr178 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3621 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3621_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr174)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr174), CU.load(stage(1), x3621)), op=FixAdd, results=List(CU.temp(stage(2), tr175)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr176)), op=Bypass, results=List(CU.scalarOut(stage(3), x3618_b3951_x3624_b3954_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr177)), op=Bypass, results=List(CU.scalarOut(stage(4), x3618_b3952_x3624_b3955_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr178)), op=Bypass, results=List(CU.scalarOut(stage(5), x3618_b3953_x3624_b3956_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3619_x3625_scalar)))
    }
    val x3627 = Fringe(name="x3627",parent=x3638,dram=x3549_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3618_b3953_x3627 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3618_b3953_x3624_b3956_scalar).rdPort(None)
      val x3618_b3952_x3627 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3618_b3952_x3624_b3955_scalar).rdPort(None)
      val x3618_b3951_x3627 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3618_b3951_x3624_b3954_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3637 = Sequential(name="x3637",parent=x3638) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3629 = Sequential(name="x3629",parent=x3637) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3636 = Pipeline(name="x3636",parent=x3637) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3631 = CounterChain(name = "x3631", ctr4)
      val x3620_x3632 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3620_x3632.load), op=Bypass, results=List(CU.vecOut(stage(1), x3571_x3635_vector)))
    }
    val x3659 = Sequential(name="x3659",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3647 = UnitPipeline(name="x3647",parent=x3659) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr181 = CU.temp
      val tr182 = CU.temp
      val tr183 = CU.temp
      val tr184 = CU.temp
      val tr185 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3642 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3642_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr181)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr181), CU.load(stage(1), x3642)), op=FixAdd, results=List(CU.temp(stage(2), tr182)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr183)), op=Bypass, results=List(CU.scalarOut(stage(3), x3639_b3957_x3645_b3960_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr184)), op=Bypass, results=List(CU.scalarOut(stage(4), x3639_b3958_x3645_b3961_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr185)), op=Bypass, results=List(CU.scalarOut(stage(5), x3639_b3959_x3645_b3962_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3640_x3646_scalar)))
    }
    val x3648 = Fringe(name="x3648",parent=x3659,dram=x3551_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3639_b3959_x3648 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3639_b3959_x3645_b3962_scalar).rdPort(None)
      val x3639_b3958_x3648 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3639_b3958_x3645_b3961_scalar).rdPort(None)
      val x3639_b3957_x3648 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3639_b3957_x3645_b3960_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3658 = Sequential(name="x3658",parent=x3659) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3650 = Sequential(name="x3650",parent=x3658) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3657 = Pipeline(name="x3657",parent=x3658) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3652 = CounterChain(name = "x3652", ctr5)
      val x3641_x3653 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3641_x3653.load), op=Bypass, results=List(CU.vecOut(stage(1), x3572_x3656_vector)))
    }
    val x3680 = Sequential(name="x3680",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3668 = UnitPipeline(name="x3668",parent=x3680) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr188 = CU.temp
      val tr189 = CU.temp
      val tr190 = CU.temp
      val tr191 = CU.temp
      val tr192 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3663 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3663_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr188)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr188), CU.load(stage(1), x3663)), op=FixAdd, results=List(CU.temp(stage(2), tr189)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr190)), op=Bypass, results=List(CU.scalarOut(stage(3), x3660_b3963_x3666_b3966_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr191)), op=Bypass, results=List(CU.scalarOut(stage(4), x3660_b3964_x3666_b3967_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr192)), op=Bypass, results=List(CU.scalarOut(stage(5), x3660_b3965_x3666_b3968_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3661_x3667_scalar)))
    }
    val x3669 = Fringe(name="x3669",parent=x3680,dram=x3553_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3660_b3964_x3669 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3660_b3964_x3666_b3967_scalar).rdPort(None)
      val x3660_b3963_x3669 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3660_b3963_x3666_b3966_scalar).rdPort(None)
      val x3660_b3965_x3669 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3660_b3965_x3666_b3968_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3679 = Sequential(name="x3679",parent=x3680) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3671 = Sequential(name="x3671",parent=x3679) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3678 = Pipeline(name="x3678",parent=x3679) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3673 = CounterChain(name = "x3673", ctr6)
      val x3662_x3674 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3662_x3674.load), op=Bypass, results=List(CU.vecOut(stage(1), x3573_x3677_vector)))
    }
    val x3701 = Sequential(name="x3701",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3689 = UnitPipeline(name="x3689",parent=x3701) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr195 = CU.temp
      val tr196 = CU.temp
      val tr197 = CU.temp
      val tr198 = CU.temp
      val tr199 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3684 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3684_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr195)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr195), CU.load(stage(1), x3684)), op=FixAdd, results=List(CU.temp(stage(2), tr196)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr197)), op=Bypass, results=List(CU.scalarOut(stage(3), x3681_b3969_x3687_b3972_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr198)), op=Bypass, results=List(CU.scalarOut(stage(4), x3681_b3970_x3687_b3973_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr199)), op=Bypass, results=List(CU.scalarOut(stage(5), x3681_b3971_x3687_b3974_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3682_x3688_scalar)))
    }
    val x3690 = Fringe(name="x3690",parent=x3701,dram=x3555_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3681_b3971_x3690 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3681_b3971_x3687_b3974_scalar).rdPort(None)
      val x3681_b3970_x3690 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3681_b3970_x3687_b3973_scalar).rdPort(None)
      val x3681_b3969_x3690 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3681_b3969_x3687_b3972_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3700 = Sequential(name="x3700",parent=x3701) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3692 = Sequential(name="x3692",parent=x3700) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3699 = Pipeline(name="x3699",parent=x3700) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr7 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3694 = CounterChain(name = "x3694", ctr7)
      val x3683_x3695 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3683_x3695.load), op=Bypass, results=List(CU.vecOut(stage(1), x3574_x3698_vector)))
    }
    val x3796 = Pipeline(name="x3796",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr201 = CU.temp
      val tr202 = CU.temp
      val tr204 = CU.temp
      val tr205 = CU.temp
      val tr206 = CU.temp
      val tr207 = CU.temp
      val tr208 = CU.temp
      val tr209 = CU.temp
      val tr210 = CU.temp
      val tr211 = CU.temp
      val tr212 = CU.temp
      val tr213 = CU.temp
      val tr215 = CU.temp
      val tr217 = CU.temp
      val tr219 = CU.temp
      val tr221 = CU.temp
      val tr222 = CU.temp
      val tr224 = CU.temp
      val tr225 = CU.temp
      val tr226 = CU.temp
      val tr227 = CU.temp
      val tr228 = CU.temp
      val tr230 = CU.temp
      val tr232 = CU.temp
      val tr234 = CU.temp
      val tr236 = CU.temp
      val tr237 = CU.temp
      val tr238 = CU.temp
      val tr239 = CU.temp
      val tr240 = CU.temp
      val tr241 = CU.temp
      val tr242 = CU.temp
      val tr243 = CU.temp
      val tr245 = CU.temp
      val tr246 = CU.temp
      val tr247 = CU.temp
      val tr248 = CU.temp
      val tr249 = CU.temp
      val tr250 = CU.temp
      val tr251 = CU.temp
      val tr252 = CU.temp
      val tr253 = CU.temp
      val tr254 = CU.temp
      val tr255 = CU.temp
      val tr256 = CU.temp
      val tr257 = CU.temp
      val tr258 = CU.temp
      val tr259 = CU.temp
      val tr260 = CU.temp
      val tr261 = CU.temp
      val tr262 = CU.temp
      val tr263 = CU.temp
      val tr264 = CU.temp
      val tr265 = CU.temp
      val tr266 = CU.temp
      val tr267 = CU.temp
      val tr268 = CU.temp
      val tr269 = CU.temp
      val tr270 = CU.temp
      val tr271 = CU.temp
      val tr272 = CU.temp
      val tr273 = CU.temp
      val tr274 = CU.temp
      val tr275 = CU.temp
      val tr276 = CU.temp
      val tr277 = CU.temp
      val tr278 = CU.temp
      val tr279 = CU.temp
      val tr280 = CU.temp
      val tr281 = CU.temp
      val tr282 = CU.temp
      val tr283 = CU.temp
      val tr284 = CU.temp
      val tr285 = CU.temp
      val tr286 = CU.temp
      val tr287 = CU.temp
      val ctr8 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3704 = CounterChain(name = "x3704", ctr8)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(76)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x3570_x3705_x3796_vector), CU.vecIn(stage(0), x3571_x3706_x3796_vector)), op=FltDiv, results=List(CU.temp(stage(1), tr201)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x3573_x3708_x3796_vector), CU.vecIn(stage(1), x3573_x3708_x3796_vector)), op=FltMul, results=List(CU.temp(stage(2), tr202)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr202), Const("0.5f")), op=FltMul, results=List(CU.temp(stage(3), tr204)))
      Stage(stage(4), operands=List(CU.vecIn(stage(3), x3572_x3707_x3796_vector), CU.temp(stage(3), tr204)), op=FltAdd, results=List(CU.temp(stage(4), tr205)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr205), CU.vecIn(stage(4), x3574_x3709_x3796_vector)), op=FltMul, results=List(CU.temp(stage(5), tr206)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr206), CU.temp(stage(5), tr201)), op=FltAdd, results=List(CU.temp(stage(6), tr207)))
      Stage(stage(7), operands=List(CU.vecIn(stage(6), x3573_x3708_x3796_vector), CU.vecIn(stage(6), x3574_x3709_x3796_vector)), op=FltMul, results=List(CU.temp(stage(7), tr208)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr208), CU.vecIn(stage(7), x3574_x3709_x3796_vector)), op=FltMul, results=List(CU.temp(stage(8), tr209)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr209), CU.temp(stage(8), tr209)), op=FltMul, results=List(CU.temp(stage(9), tr210)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr207), CU.temp(stage(9), tr210)), op=FltDiv, results=List(CU.temp(stage(10), tr211)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr211)), op=FltAbs, results=List(CU.temp(stage(11), tr212)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr212), CU.temp(stage(11), tr212)), op=FltMul, results=List(CU.temp(stage(12), tr213)))
      Stage(stage(13), operands=List(CU.temp(stage(12), tr213), Const("-0.05f")), op=FltMul, results=List(CU.temp(stage(13), tr215)))
      Stage(stage(14), operands=List(CU.temp(stage(13), tr215), Const("0.3989422804014327f")), op=FltMul, results=List(CU.temp(stage(14), tr217)))
      Stage(stage(15), operands=List(CU.temp(stage(14), tr212), Const("0.2316419f")), op=FltMul, results=List(CU.temp(stage(15), tr219)))
      Stage(stage(16), operands=List(CU.temp(stage(15), tr219), Const("1.0i")), op=FltAdd, results=List(CU.temp(stage(16), tr221)))
      Stage(stage(17), operands=List(Const("1.0i"), CU.temp(stage(16), tr221)), op=FltDiv, results=List(CU.temp(stage(17), tr222)))
      Stage(stage(18), operands=List(CU.temp(stage(17), tr222), Const("0.31938153f")), op=FltMul, results=List(CU.temp(stage(18), tr224)))
      Stage(stage(19), operands=List(CU.temp(stage(18), tr222), CU.temp(stage(18), tr222)), op=FltMul, results=List(CU.temp(stage(19), tr225)))
      Stage(stage(20), operands=List(CU.temp(stage(19), tr225), CU.temp(stage(19), tr222)), op=FltMul, results=List(CU.temp(stage(20), tr226)))
      Stage(stage(21), operands=List(CU.temp(stage(20), tr226), CU.temp(stage(20), tr222)), op=FltMul, results=List(CU.temp(stage(21), tr227)))
      Stage(stage(22), operands=List(CU.temp(stage(21), tr227), CU.temp(stage(21), tr222)), op=FltMul, results=List(CU.temp(stage(22), tr228)))
      Stage(stage(23), operands=List(CU.temp(stage(22), tr228), Const("1.330274429f")), op=FltMul, results=List(CU.temp(stage(23), tr230)))
      Stage(stage(24), operands=List(CU.temp(stage(23), tr227), Const("-1.821255978f")), op=FltMul, results=List(CU.temp(stage(24), tr232)))
      Stage(stage(25), operands=List(CU.temp(stage(24), tr225), Const("-0.356563782f")), op=FltMul, results=List(CU.temp(stage(25), tr234)))
      Stage(stage(26), operands=List(CU.temp(stage(25), tr226), Const("1.781477937f")), op=FltMul, results=List(CU.temp(stage(26), tr236)))
      Stage(stage(27), operands=List(CU.temp(stage(26), tr234), CU.temp(stage(26), tr236)), op=FltAdd, results=List(CU.temp(stage(27), tr237)))
      Stage(stage(28), operands=List(CU.temp(stage(27), tr237), CU.temp(stage(27), tr232)), op=FltAdd, results=List(CU.temp(stage(28), tr238)))
      Stage(stage(29), operands=List(CU.temp(stage(28), tr238), CU.temp(stage(28), tr230)), op=FltAdd, results=List(CU.temp(stage(29), tr239)))
      Stage(stage(30), operands=List(CU.temp(stage(29), tr239), CU.temp(stage(29), tr224)), op=FltAdd, results=List(CU.temp(stage(30), tr240)))
      Stage(stage(31), operands=List(CU.temp(stage(30), tr240), CU.temp(stage(30), tr217)), op=FltMul, results=List(CU.temp(stage(31), tr241)))
      Stage(stage(32), operands=List(CU.temp(stage(31), tr241)), op=FltNeg, results=List(CU.temp(stage(32), tr242)))
      Stage(stage(33), operands=List(CU.temp(stage(32), tr242), Const("1.0i")), op=FltAdd, results=List(CU.temp(stage(33), tr243)))
      Stage(stage(34), operands=List(CU.temp(stage(33), tr211), Const("0.0i")), op=FltLt, results=List(CU.temp(stage(34), tr245)))
      Stage(stage(35), operands=List(CU.temp(stage(34), tr245), CU.temp(stage(34), tr241), CU.temp(stage(34), tr243)), op=Mux, results=List(CU.temp(stage(35), tr246)))
      Stage(stage(36), operands=List(CU.vecIn(stage(35), x3570_x3705_x3796_vector), CU.temp(stage(35), tr246)), op=FltMul, results=List(CU.temp(stage(36), tr247)))
      Stage(stage(37), operands=List(CU.temp(stage(36), tr211), CU.temp(stage(36), tr209)), op=FltSub, results=List(CU.temp(stage(37), tr248)))
      Stage(stage(38), operands=List(CU.temp(stage(37), tr248)), op=FltAbs, results=List(CU.temp(stage(38), tr249)))
      Stage(stage(39), operands=List(CU.temp(stage(38), tr249), CU.temp(stage(38), tr249)), op=FltMul, results=List(CU.temp(stage(39), tr250)))
      Stage(stage(40), operands=List(CU.temp(stage(39), tr250), Const("-0.05f")), op=FltMul, results=List(CU.temp(stage(40), tr251)))
      Stage(stage(41), operands=List(CU.temp(stage(40), tr251), Const("0.3989422804014327f")), op=FltMul, results=List(CU.temp(stage(41), tr252)))
      Stage(stage(42), operands=List(CU.temp(stage(41), tr249), Const("0.2316419f")), op=FltMul, results=List(CU.temp(stage(42), tr253)))
      Stage(stage(43), operands=List(CU.temp(stage(42), tr253), Const("1.0i")), op=FltAdd, results=List(CU.temp(stage(43), tr254)))
      Stage(stage(44), operands=List(Const("1.0i"), CU.temp(stage(43), tr254)), op=FltDiv, results=List(CU.temp(stage(44), tr255)))
      Stage(stage(45), operands=List(CU.temp(stage(44), tr255), Const("0.31938153f")), op=FltMul, results=List(CU.temp(stage(45), tr256)))
      Stage(stage(46), operands=List(CU.temp(stage(45), tr255), CU.temp(stage(45), tr255)), op=FltMul, results=List(CU.temp(stage(46), tr257)))
      Stage(stage(47), operands=List(CU.temp(stage(46), tr257), CU.temp(stage(46), tr255)), op=FltMul, results=List(CU.temp(stage(47), tr258)))
      Stage(stage(48), operands=List(CU.temp(stage(47), tr258), CU.temp(stage(47), tr255)), op=FltMul, results=List(CU.temp(stage(48), tr259)))
      Stage(stage(49), operands=List(CU.temp(stage(48), tr259), CU.temp(stage(48), tr255)), op=FltMul, results=List(CU.temp(stage(49), tr260)))
      Stage(stage(50), operands=List(CU.temp(stage(49), tr260), Const("1.330274429f")), op=FltMul, results=List(CU.temp(stage(50), tr261)))
      Stage(stage(51), operands=List(CU.temp(stage(50), tr259), Const("-1.821255978f")), op=FltMul, results=List(CU.temp(stage(51), tr262)))
      Stage(stage(52), operands=List(CU.temp(stage(51), tr257), Const("-0.356563782f")), op=FltMul, results=List(CU.temp(stage(52), tr263)))
      Stage(stage(53), operands=List(CU.temp(stage(52), tr258), Const("1.781477937f")), op=FltMul, results=List(CU.temp(stage(53), tr264)))
      Stage(stage(54), operands=List(CU.temp(stage(53), tr263), CU.temp(stage(53), tr264)), op=FltAdd, results=List(CU.temp(stage(54), tr265)))
      Stage(stage(55), operands=List(CU.temp(stage(54), tr265), CU.temp(stage(54), tr262)), op=FltAdd, results=List(CU.temp(stage(55), tr266)))
      Stage(stage(56), operands=List(CU.temp(stage(55), tr266), CU.temp(stage(55), tr261)), op=FltAdd, results=List(CU.temp(stage(56), tr267)))
      Stage(stage(57), operands=List(CU.temp(stage(56), tr267), CU.temp(stage(56), tr256)), op=FltAdd, results=List(CU.temp(stage(57), tr268)))
      Stage(stage(58), operands=List(CU.temp(stage(57), tr268), CU.temp(stage(57), tr252)), op=FltMul, results=List(CU.temp(stage(58), tr269)))
      Stage(stage(59), operands=List(CU.temp(stage(58), tr269)), op=FltNeg, results=List(CU.temp(stage(59), tr270)))
      Stage(stage(60), operands=List(CU.temp(stage(59), tr270), Const("1.0i")), op=FltAdd, results=List(CU.temp(stage(60), tr271)))
      Stage(stage(61), operands=List(CU.temp(stage(60), tr248), Const("0.0i")), op=FltLt, results=List(CU.temp(stage(61), tr272)))
      Stage(stage(62), operands=List(CU.temp(stage(61), tr272), CU.temp(stage(61), tr269), CU.temp(stage(61), tr271)), op=Mux, results=List(CU.temp(stage(62), tr273)))
      Stage(stage(63), operands=List(CU.vecIn(stage(62), x3572_x3707_x3796_vector)), op=FltNeg, results=List(CU.temp(stage(63), tr274)))
      Stage(stage(64), operands=List(CU.vecIn(stage(63), x3571_x3706_x3796_vector), CU.temp(stage(63), tr274)), op=FltMul, results=List(CU.temp(stage(64), tr275)))
      Stage(stage(65), operands=List(CU.temp(stage(64), tr275), CU.vecIn(stage(64), x3574_x3709_x3796_vector)), op=FltMul, results=List(CU.temp(stage(65), tr276)))
      Stage(stage(66), operands=List(CU.temp(stage(65), tr276), CU.temp(stage(65), tr273)), op=FltMul, results=List(CU.temp(stage(66), tr277)))
      Stage(stage(67), operands=List(CU.temp(stage(66), tr247), CU.temp(stage(66), tr277)), op=FltSub, results=List(CU.temp(stage(67), tr278)))
      Stage(stage(68), operands=List(CU.temp(stage(67), tr273)), op=FltNeg, results=List(CU.temp(stage(68), tr279)))
      Stage(stage(69), operands=List(CU.temp(stage(68), tr279), Const("1.0i")), op=FltAdd, results=List(CU.temp(stage(69), tr280)))
      Stage(stage(70), operands=List(CU.temp(stage(69), tr276), CU.temp(stage(69), tr280)), op=FltMul, results=List(CU.temp(stage(70), tr281)))
      Stage(stage(71), operands=List(CU.temp(stage(70), tr246)), op=FltNeg, results=List(CU.temp(stage(71), tr282)))
      Stage(stage(72), operands=List(CU.temp(stage(71), tr282), Const("1.0i")), op=FltAdd, results=List(CU.temp(stage(72), tr283)))
      Stage(stage(73), operands=List(CU.vecIn(stage(72), x3570_x3705_x3796_vector), CU.temp(stage(72), tr283)), op=FltMul, results=List(CU.temp(stage(73), tr284)))
      Stage(stage(74), operands=List(CU.temp(stage(73), tr281), CU.temp(stage(73), tr284)), op=FltSub, results=List(CU.temp(stage(74), tr285)))
      Stage(stage(75), operands=List(CU.vecIn(stage(74), x3569_x3710_x3796_vector), Const("0i")), op=FixEql, results=List(CU.temp(stage(75), tr286)))
      Stage(stage(76), operands=List(CU.temp(stage(75), tr286), CU.temp(stage(75), tr285), CU.temp(stage(75), tr278)), op=Mux, results=List(CU.vecOut(stage(76), x3575_x3795_vector), CU.temp(stage(76), tr287)))
    }
    val x3820 = Sequential(name="x3820",parent=x3821) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3815 = Sequential(name="x3815",parent=x3820) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x3806 = UnitPipeline(name="x3806",parent=x3815) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr289 = CU.temp
      val tr290 = CU.temp
      val tr291 = CU.temp
      val tr292 = CU.temp
      val tr293 = CU.temp
      val x3568 = CounterChain.copy("x3821", "x3568")
      val x3801 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x3801_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(6)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3568(0)), Const("4i")), op=FixMul, results=List(CU.temp(stage(1), tr289)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr289), CU.load(stage(1), x3801)), op=FixAdd, results=List(CU.temp(stage(2), tr290)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr291)), op=Bypass, results=List(CU.scalarOut(stage(3), x3797_b3975_x3804_b3980_scalar)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr292)), op=Bypass, results=List(CU.scalarOut(stage(4), x3797_b3976_x3804_b3981_scalar)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr293)), op=Bypass, results=List(CU.scalarOut(stage(5), x3797_b3977_x3804_b3982_scalar)))
      Stage(stage(6), operands=List(Const("640i")), op=Bypass, results=List(CU.scalarOut(stage(6), x3798_x3805_scalar)))
    }
    val x3814 = Pipeline(name="x3814",parent=x3815) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr295 = CU.temp
      val tr296 = CU.temp
      val ctr9 = (Const("0i").out, Const("640i").out, Const("1i").out) // Counter
      val x3808 = CounterChain(name = "x3808", ctr9)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr295)), op=Bypass, results=List(CU.vecOut(stage(1), x3799_b3978_x3813_b3983_vector)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr296)), op=Bypass, results=List(CU.vecOut(stage(2), x3799_b3979_x3813_b3984_vector)))
    }
    val x3816 = Fringe(name="x3816",parent=x3820,dram=x3557_oc, mode=TileStore) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3797_b3975_x3816 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3797_b3975_x3804_b3980_scalar).rdPort(None)
      val x3797_b3977_x3816 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3797_b3977_x3804_b3982_scalar).rdPort(None)
      val x3799_b3978_x3816 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x3799_b3978_x3813_b3983_vector).rdPort(None)
      val x3797_b3976_x3816 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x3797_b3976_x3804_b3981_scalar).rdPort(None)
      val x3799_b3979_x3816 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x3799_b3979_x3813_b3984_vector).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x3819 = Sequential(name="x3819",parent=x3820) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    
  }
}
