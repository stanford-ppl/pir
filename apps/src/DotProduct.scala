import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object DotProductDesign extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4374_scalar = Scalar("x4374")
    val x4373_scalar = Scalar("x4373")
    val x4371_scalar = Scalar("x4371")
    val x4372_scalar = Scalar("x4372")
    val x3154_argout = ArgOut("x3154")
    val x4368_scalar = Scalar("x4368")
    val x4378_scalar = Scalar("x4378")
    val x4379_scalar = Scalar("x4379")
    val bus_4226_scalar = Scalar("bus_4226")
    val x4376_scalar = Scalar("x4376")
    val x4369_scalar = Scalar("x4369")
    val x4370_scalar = Scalar("x4370")
    val bus_4220_scalar = Scalar("bus_4220")
    val x3156_oc = OffChip("x3156")
    val x4375_scalar = Scalar("x4375")
    val x3155_oc = OffChip("x3155")
    val x4377_scalar = Scalar("x4377")
    val bus_4223_scalar = Scalar("bus_4223")
    val x3653_mc = MemoryController(TileLoad, x3155_oc)
    val x3698_mc = MemoryController(TileLoad, x3156_oc)
    val x4342_mc = MemoryController(TileLoad, x3156_oc)
    val x4066_mc = MemoryController(TileLoad, x3156_oc)
    val x3285_mc = MemoryController(TileLoad, x3155_oc)
    val x4297_mc = MemoryController(TileLoad, x3155_oc)
    val x3882_mc = MemoryController(TileLoad, x3156_oc)
    val x3377_mc = MemoryController(TileLoad, x3155_oc)
    val x3561_mc = MemoryController(TileLoad, x3155_oc)
    val x4250_mc = MemoryController(TileLoad, x3156_oc)
    val x3469_mc = MemoryController(TileLoad, x3155_oc)
    val x3330_mc = MemoryController(TileLoad, x3156_oc)
    val x3790_mc = MemoryController(TileLoad, x3156_oc)
    val x3514_mc = MemoryController(TileLoad, x3156_oc)
    val x3422_mc = MemoryController(TileLoad, x3156_oc)
    val x3606_mc = MemoryController(TileLoad, x3156_oc)
    val x4113_mc = MemoryController(TileLoad, x3155_oc)
    val x3929_mc = MemoryController(TileLoad, x3155_oc)
    val x3974_mc = MemoryController(TileLoad, x3156_oc)
    val x3837_mc = MemoryController(TileLoad, x3155_oc)
    val x4021_mc = MemoryController(TileLoad, x3155_oc)
    val x4158_mc = MemoryController(TileLoad, x3156_oc)
    val x4205_mc = MemoryController(TileLoad, x3155_oc)
    val x3745_mc = MemoryController(TileLoad, x3155_oc)
    val x4609 = Sequential(name = "x4609", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4609_unitcc = CounterChain(name = "x4609_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4603 = MetaPipeline(name = "x4603", parent=x4609, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const("768000000i").out, Const("8000i").out) // Counter
      val x3235 = CounterChain(name = "x3235", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4603_leaf = UnitPipeline(name = "x4603_leaf", parent=x4603, deps=List(x3766, x3535, x3995, x4179, x4318, x3490, x4601, x3811, x3306, x3443, x4226, x3398, x3351, x4042, x3903, x3950, x4363, x3719, x4271, x3582, x3858, x4087, x3627, x3674, x4134)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4603_unitcc = CounterChain(name = "x4603_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3306 = StreamController(name = "x3306", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3306_unitcc = CounterChain(name = "x3306_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3281_0 = UnitPipeline(name = "x3281_0", parent=x3306, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3508 = CU.temp
      val tr3507 = CU.temp
      val tr3505 = CU.temp
      val tr3504 = CU.temp
      val tr3502 = CU.temp
      val tr3497 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3281_unitcc = CounterChain(name = "x3281_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3497)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3497)), op=FixSub, results=List(CU.scalarOut(stage(2), x3285_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3497), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3502)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3502), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3504)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3502), CU.temp(stage(4), tr3504)), op=FixSub, results=List(CU.temp(stage(5), tr3505)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3504), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3507)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3507), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3508)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3505), CU.temp(stage(7), tr3508)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3285_mc.len)))
    }
    val x3351 = StreamController(name = "x3351", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3351_unitcc = CounterChain(name = "x3351_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3326_0 = UnitPipeline(name = "x3326_0", parent=x3351, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3531 = CU.temp
      val tr3530 = CU.temp
      val tr3528 = CU.temp
      val tr3527 = CU.temp
      val tr3525 = CU.temp
      val tr3520 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3326_unitcc = CounterChain(name = "x3326_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3520)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3520)), op=FixSub, results=List(CU.scalarOut(stage(2), x3330_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3520), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3525)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3525), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3527)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3525), CU.temp(stage(4), tr3527)), op=FixSub, results=List(CU.temp(stage(5), tr3528)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3527), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3530)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3530), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3531)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3528), CU.temp(stage(7), tr3531)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3330_mc.len)))
    }
    val x3398 = StreamController(name = "x3398", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3398_unitcc = CounterChain(name = "x3398_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3373_0 = UnitPipeline(name = "x3373_0", parent=x3398, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3554 = CU.temp
      val tr3553 = CU.temp
      val tr3551 = CU.temp
      val tr3550 = CU.temp
      val tr3548 = CU.temp
      val tr3543 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3373_unitcc = CounterChain(name = "x3373_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3543)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3543)), op=FixSub, results=List(CU.scalarOut(stage(2), x3377_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3543), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3548)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3548), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3550)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3548), CU.temp(stage(4), tr3550)), op=FixSub, results=List(CU.temp(stage(5), tr3551)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3550), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3553)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3553), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3554)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3551), CU.temp(stage(7), tr3554)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3377_mc.len)))
    }
    val x3443 = StreamController(name = "x3443", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3443_unitcc = CounterChain(name = "x3443_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3418_0 = UnitPipeline(name = "x3418_0", parent=x3443, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3577 = CU.temp
      val tr3576 = CU.temp
      val tr3574 = CU.temp
      val tr3573 = CU.temp
      val tr3571 = CU.temp
      val tr3566 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3418_unitcc = CounterChain(name = "x3418_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3566)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3566)), op=FixSub, results=List(CU.scalarOut(stage(2), x3422_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3566), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3571)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3571), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3573)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3571), CU.temp(stage(4), tr3573)), op=FixSub, results=List(CU.temp(stage(5), tr3574)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3573), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3576)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3576), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3577)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3574), CU.temp(stage(7), tr3577)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3422_mc.len)))
    }
    val x3490 = StreamController(name = "x3490", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3490_unitcc = CounterChain(name = "x3490_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3465_0 = UnitPipeline(name = "x3465_0", parent=x3490, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3600 = CU.temp
      val tr3599 = CU.temp
      val tr3597 = CU.temp
      val tr3596 = CU.temp
      val tr3594 = CU.temp
      val tr3589 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3465_unitcc = CounterChain(name = "x3465_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3589)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3589)), op=FixSub, results=List(CU.scalarOut(stage(2), x3469_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3589), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3594)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3594), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3596)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3594), CU.temp(stage(4), tr3596)), op=FixSub, results=List(CU.temp(stage(5), tr3597)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3596), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3599)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3599), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3600)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3597), CU.temp(stage(7), tr3600)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3469_mc.len)))
    }
    val x3535 = StreamController(name = "x3535", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3535_unitcc = CounterChain(name = "x3535_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3510_0 = UnitPipeline(name = "x3510_0", parent=x3535, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3623 = CU.temp
      val tr3622 = CU.temp
      val tr3620 = CU.temp
      val tr3619 = CU.temp
      val tr3617 = CU.temp
      val tr3612 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3510_unitcc = CounterChain(name = "x3510_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3612)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3612)), op=FixSub, results=List(CU.scalarOut(stage(2), x3514_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3612), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3617)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3617), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3619)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3617), CU.temp(stage(4), tr3619)), op=FixSub, results=List(CU.temp(stage(5), tr3620)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3619), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3622)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3622), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3623)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3620), CU.temp(stage(7), tr3623)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3514_mc.len)))
    }
    val x3582 = StreamController(name = "x3582", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3582_unitcc = CounterChain(name = "x3582_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3557_0 = UnitPipeline(name = "x3557_0", parent=x3582, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3646 = CU.temp
      val tr3645 = CU.temp
      val tr3643 = CU.temp
      val tr3642 = CU.temp
      val tr3640 = CU.temp
      val tr3635 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3557_unitcc = CounterChain(name = "x3557_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3635)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3635)), op=FixSub, results=List(CU.scalarOut(stage(2), x3561_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3635), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3640)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3640), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3642)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3640), CU.temp(stage(4), tr3642)), op=FixSub, results=List(CU.temp(stage(5), tr3643)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3642), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3645)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3645), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3646)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3643), CU.temp(stage(7), tr3646)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3561_mc.len)))
    }
    val x3627 = StreamController(name = "x3627", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3627_unitcc = CounterChain(name = "x3627_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3602_0 = UnitPipeline(name = "x3602_0", parent=x3627, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3669 = CU.temp
      val tr3668 = CU.temp
      val tr3666 = CU.temp
      val tr3665 = CU.temp
      val tr3663 = CU.temp
      val tr3658 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3602_unitcc = CounterChain(name = "x3602_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3658)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3658)), op=FixSub, results=List(CU.scalarOut(stage(2), x3606_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3658), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3663)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3663), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3665)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3663), CU.temp(stage(4), tr3665)), op=FixSub, results=List(CU.temp(stage(5), tr3666)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3665), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3668)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3668), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3669)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3666), CU.temp(stage(7), tr3669)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3606_mc.len)))
    }
    val x3674 = StreamController(name = "x3674", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3674_unitcc = CounterChain(name = "x3674_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3649_0 = UnitPipeline(name = "x3649_0", parent=x3674, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3692 = CU.temp
      val tr3691 = CU.temp
      val tr3689 = CU.temp
      val tr3688 = CU.temp
      val tr3686 = CU.temp
      val tr3681 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3649_unitcc = CounterChain(name = "x3649_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3681)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3681)), op=FixSub, results=List(CU.scalarOut(stage(2), x3653_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3681), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3686)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3686), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3688)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3686), CU.temp(stage(4), tr3688)), op=FixSub, results=List(CU.temp(stage(5), tr3689)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3688), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3691)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3691), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3692)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3689), CU.temp(stage(7), tr3692)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3653_mc.len)))
    }
    val x3719 = StreamController(name = "x3719", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3719_unitcc = CounterChain(name = "x3719_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3694_0 = UnitPipeline(name = "x3694_0", parent=x3719, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3715 = CU.temp
      val tr3714 = CU.temp
      val tr3712 = CU.temp
      val tr3711 = CU.temp
      val tr3709 = CU.temp
      val tr3704 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3694_unitcc = CounterChain(name = "x3694_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3704)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3704)), op=FixSub, results=List(CU.scalarOut(stage(2), x3698_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3704), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3709)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3709), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3711)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3709), CU.temp(stage(4), tr3711)), op=FixSub, results=List(CU.temp(stage(5), tr3712)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3711), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3714)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3714), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3715)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3712), CU.temp(stage(7), tr3715)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3698_mc.len)))
    }
    val x3766 = StreamController(name = "x3766", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3766_unitcc = CounterChain(name = "x3766_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3741_0 = UnitPipeline(name = "x3741_0", parent=x3766, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3738 = CU.temp
      val tr3737 = CU.temp
      val tr3735 = CU.temp
      val tr3734 = CU.temp
      val tr3732 = CU.temp
      val tr3727 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3741_unitcc = CounterChain(name = "x3741_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3727)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3727)), op=FixSub, results=List(CU.scalarOut(stage(2), x3745_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3727), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3732)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3732), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3734)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3732), CU.temp(stage(4), tr3734)), op=FixSub, results=List(CU.temp(stage(5), tr3735)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3734), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3737)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3737), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3738)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3735), CU.temp(stage(7), tr3738)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3745_mc.len)))
    }
    val x3811 = StreamController(name = "x3811", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3811_unitcc = CounterChain(name = "x3811_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3786_0 = UnitPipeline(name = "x3786_0", parent=x3811, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3761 = CU.temp
      val tr3760 = CU.temp
      val tr3758 = CU.temp
      val tr3757 = CU.temp
      val tr3755 = CU.temp
      val tr3750 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3786_unitcc = CounterChain(name = "x3786_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3750)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3750)), op=FixSub, results=List(CU.scalarOut(stage(2), x3790_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3750), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3755)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3755), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3757)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3755), CU.temp(stage(4), tr3757)), op=FixSub, results=List(CU.temp(stage(5), tr3758)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3757), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3760)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3760), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3761)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3758), CU.temp(stage(7), tr3761)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3790_mc.len)))
    }
    val x3858 = StreamController(name = "x3858", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3858_unitcc = CounterChain(name = "x3858_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3833_0 = UnitPipeline(name = "x3833_0", parent=x3858, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3784 = CU.temp
      val tr3783 = CU.temp
      val tr3781 = CU.temp
      val tr3780 = CU.temp
      val tr3778 = CU.temp
      val tr3773 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3833_unitcc = CounterChain(name = "x3833_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3773)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3773)), op=FixSub, results=List(CU.scalarOut(stage(2), x3837_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3773), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3778)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3778), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3780)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3778), CU.temp(stage(4), tr3780)), op=FixSub, results=List(CU.temp(stage(5), tr3781)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3780), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3783)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3783), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3784)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3781), CU.temp(stage(7), tr3784)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3837_mc.len)))
    }
    val x3903 = StreamController(name = "x3903", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3903_unitcc = CounterChain(name = "x3903_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3878_0 = UnitPipeline(name = "x3878_0", parent=x3903, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3807 = CU.temp
      val tr3806 = CU.temp
      val tr3804 = CU.temp
      val tr3803 = CU.temp
      val tr3801 = CU.temp
      val tr3796 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3878_unitcc = CounterChain(name = "x3878_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3796)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3796)), op=FixSub, results=List(CU.scalarOut(stage(2), x3882_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3796), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3801)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3801), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3803)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3801), CU.temp(stage(4), tr3803)), op=FixSub, results=List(CU.temp(stage(5), tr3804)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3803), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3806)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3806), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3807)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3804), CU.temp(stage(7), tr3807)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3882_mc.len)))
    }
    val x3950 = StreamController(name = "x3950", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3950_unitcc = CounterChain(name = "x3950_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3925_0 = UnitPipeline(name = "x3925_0", parent=x3950, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3830 = CU.temp
      val tr3829 = CU.temp
      val tr3827 = CU.temp
      val tr3826 = CU.temp
      val tr3824 = CU.temp
      val tr3819 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3925_unitcc = CounterChain(name = "x3925_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3819)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3819)), op=FixSub, results=List(CU.scalarOut(stage(2), x3929_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3819), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3824)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3824), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3826)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3824), CU.temp(stage(4), tr3826)), op=FixSub, results=List(CU.temp(stage(5), tr3827)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3826), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3829)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3829), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3830)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3827), CU.temp(stage(7), tr3830)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3929_mc.len)))
    }
    val x3995 = StreamController(name = "x3995", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3995_unitcc = CounterChain(name = "x3995_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x3970_0 = UnitPipeline(name = "x3970_0", parent=x3995, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3853 = CU.temp
      val tr3852 = CU.temp
      val tr3850 = CU.temp
      val tr3849 = CU.temp
      val tr3847 = CU.temp
      val tr3842 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x3970_unitcc = CounterChain(name = "x3970_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3842)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3842)), op=FixSub, results=List(CU.scalarOut(stage(2), x3974_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3842), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3847)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3847), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3849)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3847), CU.temp(stage(4), tr3849)), op=FixSub, results=List(CU.temp(stage(5), tr3850)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3849), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3852)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3852), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3853)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3850), CU.temp(stage(7), tr3853)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3974_mc.len)))
    }
    val x4042 = StreamController(name = "x4042", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4042_unitcc = CounterChain(name = "x4042_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4017_0 = UnitPipeline(name = "x4017_0", parent=x4042, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3876 = CU.temp
      val tr3875 = CU.temp
      val tr3873 = CU.temp
      val tr3872 = CU.temp
      val tr3870 = CU.temp
      val tr3865 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4017_unitcc = CounterChain(name = "x4017_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3865)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3865)), op=FixSub, results=List(CU.scalarOut(stage(2), x4021_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3865), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3870)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3870), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3872)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3870), CU.temp(stage(4), tr3872)), op=FixSub, results=List(CU.temp(stage(5), tr3873)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3872), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3875)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3875), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3876)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3873), CU.temp(stage(7), tr3876)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4021_mc.len)))
    }
    val x4087 = StreamController(name = "x4087", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4087_unitcc = CounterChain(name = "x4087_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4062_0 = UnitPipeline(name = "x4062_0", parent=x4087, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3899 = CU.temp
      val tr3898 = CU.temp
      val tr3896 = CU.temp
      val tr3895 = CU.temp
      val tr3893 = CU.temp
      val tr3888 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4062_unitcc = CounterChain(name = "x4062_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3888)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3888)), op=FixSub, results=List(CU.scalarOut(stage(2), x4066_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3888), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3893)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3893), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3895)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3893), CU.temp(stage(4), tr3895)), op=FixSub, results=List(CU.temp(stage(5), tr3896)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3895), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3898)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3898), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3899)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3896), CU.temp(stage(7), tr3899)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4066_mc.len)))
    }
    val x4134 = StreamController(name = "x4134", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4134_unitcc = CounterChain(name = "x4134_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4109_0 = UnitPipeline(name = "x4109_0", parent=x4134, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3922 = CU.temp
      val tr3921 = CU.temp
      val tr3919 = CU.temp
      val tr3918 = CU.temp
      val tr3916 = CU.temp
      val tr3911 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4109_unitcc = CounterChain(name = "x4109_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3911)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3911)), op=FixSub, results=List(CU.scalarOut(stage(2), x4113_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3911), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3916)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3916), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3918)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3916), CU.temp(stage(4), tr3918)), op=FixSub, results=List(CU.temp(stage(5), tr3919)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3918), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3921)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3921), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3922)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3919), CU.temp(stage(7), tr3922)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4113_mc.len)))
    }
    val x4179 = StreamController(name = "x4179", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4179_unitcc = CounterChain(name = "x4179_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4154_0 = UnitPipeline(name = "x4154_0", parent=x4179, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3945 = CU.temp
      val tr3944 = CU.temp
      val tr3942 = CU.temp
      val tr3941 = CU.temp
      val tr3939 = CU.temp
      val tr3934 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4154_unitcc = CounterChain(name = "x4154_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3934)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3934)), op=FixSub, results=List(CU.scalarOut(stage(2), x4158_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3934), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3939)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3939), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3941)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3939), CU.temp(stage(4), tr3941)), op=FixSub, results=List(CU.temp(stage(5), tr3942)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3941), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3944)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3944), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3945)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3942), CU.temp(stage(7), tr3945)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4158_mc.len)))
    }
    val x4226 = StreamController(name = "x4226", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4226_unitcc = CounterChain(name = "x4226_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4201_0 = UnitPipeline(name = "x4201_0", parent=x4226, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3968 = CU.temp
      val tr3967 = CU.temp
      val tr3965 = CU.temp
      val tr3964 = CU.temp
      val tr3962 = CU.temp
      val tr3957 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4201_unitcc = CounterChain(name = "x4201_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3957)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3957)), op=FixSub, results=List(CU.scalarOut(stage(2), x4205_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3957), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3962)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3962), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3964)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3962), CU.temp(stage(4), tr3964)), op=FixSub, results=List(CU.temp(stage(5), tr3965)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3964), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3967)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3967), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3968)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3965), CU.temp(stage(7), tr3968)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4205_mc.len)))
    }
    val x4271 = StreamController(name = "x4271", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4271_unitcc = CounterChain(name = "x4271_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4246_0 = UnitPipeline(name = "x4246_0", parent=x4271, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr3991 = CU.temp
      val tr3990 = CU.temp
      val tr3988 = CU.temp
      val tr3987 = CU.temp
      val tr3985 = CU.temp
      val tr3980 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4246_unitcc = CounterChain(name = "x4246_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr3980)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr3980)), op=FixSub, results=List(CU.scalarOut(stage(2), x4250_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr3980), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr3985)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr3985), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr3987)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr3985), CU.temp(stage(4), tr3987)), op=FixSub, results=List(CU.temp(stage(5), tr3988)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr3987), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr3990)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr3990), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr3991)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr3988), CU.temp(stage(7), tr3991)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4250_mc.len)))
    }
    val x4318 = StreamController(name = "x4318", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4318_unitcc = CounterChain(name = "x4318_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4293_0 = UnitPipeline(name = "x4293_0", parent=x4318, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr4014 = CU.temp
      val tr4013 = CU.temp
      val tr4011 = CU.temp
      val tr4010 = CU.temp
      val tr4008 = CU.temp
      val tr4003 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4293_unitcc = CounterChain(name = "x4293_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr4003)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr4003)), op=FixSub, results=List(CU.scalarOut(stage(2), x4297_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4003), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr4008)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr4008), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr4010)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr4008), CU.temp(stage(4), tr4010)), op=FixSub, results=List(CU.temp(stage(5), tr4011)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr4010), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr4013)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr4013), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr4014)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr4011), CU.temp(stage(7), tr4014)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4297_mc.len)))
    }
    val x4363 = StreamController(name = "x4363", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4363_unitcc = CounterChain(name = "x4363_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4338_0 = UnitPipeline(name = "x4338_0", parent=x4363, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr4037 = CU.temp
      val tr4036 = CU.temp
      val tr4034 = CU.temp
      val tr4033 = CU.temp
      val tr4031 = CU.temp
      val tr4026 = CU.temp
      val x3235 = CounterChain.copy(x4603, "x3235")
      val x4338_unitcc = CounterChain(name = "x4338_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3235(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr4026)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3235(0)), CU.temp(stage(1), tr4026)), op=FixSub, results=List(CU.scalarOut(stage(2), x4342_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4026), Const("8000i")), op=FixAdd, results=List(CU.temp(stage(3), tr4031)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr4031), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr4033)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr4031), CU.temp(stage(4), tr4033)), op=FixSub, results=List(CU.temp(stage(5), tr4034)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr4033), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr4036)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr4036), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr4037)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr4034), CU.temp(stage(7), tr4037)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4342_mc.len)))
    }
    val x4417_0 = Pipeline(name = "x4417_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4392 = CounterChain(name = "x4392", ctr5)
      val x3238_x4406 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4392(0))).wtPort(x3285_mc.vdata).rdAddr(x4392(0))
      val x3250_x4409 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4392(0))).wtPort(x3330_mc.vdata).rdAddr(x4392(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3238_x4406.load, x3250_x4409.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4059) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4059), op=Bypass, results=List(CU.scalarOut(stage(2), x4368_scalar)))
    }
    val x4431_0 = Pipeline(name = "x4431_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr10 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4393 = CounterChain(name = "x4393", ctr10)
      val x3239_x4420 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4393(0))).wtPort(x3377_mc.vdata).rdAddr(x4393(0))
      val x3251_x4423 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4393(0))).wtPort(x3422_mc.vdata).rdAddr(x4393(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3239_x4420.load, x3251_x4423.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4072) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4072), op=Bypass, results=List(CU.scalarOut(stage(2), x4369_scalar)))
    }
    val x4445_0 = Pipeline(name = "x4445_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr15 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4394 = CounterChain(name = "x4394", ctr15)
      val x3240_x4434 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4394(0))).wtPort(x3469_mc.vdata).rdAddr(x4394(0))
      val x3252_x4437 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4394(0))).wtPort(x3514_mc.vdata).rdAddr(x4394(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3240_x4434.load, x3252_x4437.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4085) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4085), op=Bypass, results=List(CU.scalarOut(stage(2), x4370_scalar)))
    }
    val x4459_0 = Pipeline(name = "x4459_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr20 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4395 = CounterChain(name = "x4395", ctr20)
      val x3241_x4448 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4395(0))).wtPort(x3561_mc.vdata).rdAddr(x4395(0))
      val x3253_x4451 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4395(0))).wtPort(x3606_mc.vdata).rdAddr(x4395(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3241_x4448.load, x3253_x4451.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4098) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4098), op=Bypass, results=List(CU.scalarOut(stage(2), x4371_scalar)))
    }
    val x4473_0 = Pipeline(name = "x4473_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr25 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4396 = CounterChain(name = "x4396", ctr25)
      val x3242_x4462 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4396(0))).wtPort(x3653_mc.vdata).rdAddr(x4396(0))
      val x3254_x4465 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4396(0))).wtPort(x3698_mc.vdata).rdAddr(x4396(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3242_x4462.load, x3254_x4465.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4111) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4111), op=Bypass, results=List(CU.scalarOut(stage(2), x4372_scalar)))
    }
    val x4487_0 = Pipeline(name = "x4487_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr30 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4397 = CounterChain(name = "x4397", ctr30)
      val x3243_x4476 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4397(0))).wtPort(x3745_mc.vdata).rdAddr(x4397(0))
      val x3255_x4479 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4397(0))).wtPort(x3790_mc.vdata).rdAddr(x4397(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3243_x4476.load, x3255_x4479.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4124) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4124), op=Bypass, results=List(CU.scalarOut(stage(2), x4373_scalar)))
    }
    val x4501_0 = Pipeline(name = "x4501_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr35 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4398 = CounterChain(name = "x4398", ctr35)
      val x3244_x4490 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4398(0))).wtPort(x3837_mc.vdata).rdAddr(x4398(0))
      val x3256_x4493 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4398(0))).wtPort(x3882_mc.vdata).rdAddr(x4398(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3244_x4490.load, x3256_x4493.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4137) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4137), op=Bypass, results=List(CU.scalarOut(stage(2), x4374_scalar)))
    }
    val x4515_0 = Pipeline(name = "x4515_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr40 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4399 = CounterChain(name = "x4399", ctr40)
      val x3245_x4504 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4399(0))).wtPort(x3929_mc.vdata).rdAddr(x4399(0))
      val x3257_x4507 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4399(0))).wtPort(x3974_mc.vdata).rdAddr(x4399(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3245_x4504.load, x3257_x4507.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4150) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4150), op=Bypass, results=List(CU.scalarOut(stage(2), x4375_scalar)))
    }
    val x4529_0 = Pipeline(name = "x4529_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr45 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4400 = CounterChain(name = "x4400", ctr45)
      val x3246_x4518 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4400(0))).wtPort(x4021_mc.vdata).rdAddr(x4400(0))
      val x3258_x4521 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4400(0))).wtPort(x4066_mc.vdata).rdAddr(x4400(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3246_x4518.load, x3258_x4521.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4163) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4163), op=Bypass, results=List(CU.scalarOut(stage(2), x4376_scalar)))
    }
    val x4543_0 = Pipeline(name = "x4543_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr50 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4401 = CounterChain(name = "x4401", ctr50)
      val x3247_x4532 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4401(0))).wtPort(x4113_mc.vdata).rdAddr(x4401(0))
      val x3259_x4535 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4401(0))).wtPort(x4158_mc.vdata).rdAddr(x4401(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3247_x4532.load, x3259_x4535.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4176) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4176), op=Bypass, results=List(CU.scalarOut(stage(2), x4377_scalar)))
    }
    val x4557_0 = Pipeline(name = "x4557_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr55 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4402 = CounterChain(name = "x4402", ctr55)
      val x3248_x4546 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4402(0))).wtPort(x4205_mc.vdata).rdAddr(x4402(0))
      val x3260_x4549 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4402(0))).wtPort(x4250_mc.vdata).rdAddr(x4402(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3248_x4546.load, x3260_x4549.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4189) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4189), op=Bypass, results=List(CU.scalarOut(stage(2), x4378_scalar)))
    }
    val x4571_0 = Pipeline(name = "x4571_0", parent=x4603, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr60 = (Const("0i").out, Const("8000i").out, Const("16i").out) // Counter
      val x4403 = CounterChain(name = "x4403", ctr60)
      val x3249_x4560 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4403(0))).wtPort(x4297_mc.vdata).rdAddr(x4403(0))
      val x3261_x4563 = SemiFIFO(size = 8000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4403(0))).wtPort(x4342_mc.vdata).rdAddr(x4403(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x3249_x4560.load, x3261_x4563.load), op=FixMul, results=List(CU.reduce(stage(1))))
      val (rs1, rr4202) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr4202), op=Bypass, results=List(CU.scalarOut(stage(2), x4379_scalar)))
    }
    val x4601 = StreamController(name = "x4601", parent=x4603, deps=List(x4431_0, x4501_0, x4515_0, x4417_0, x4571_0, x4445_0, x4529_0, x4473_0, x4459_0, x4543_0, x4487_0, x4557_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4601_unitcc = CounterChain(name = "x4601_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4601_0 = StreamPipeline(name = "x4601_0", parent=x4601, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr4219 = CU.temp
      val tr4218 = CU.temp
      val x4601_unitcc = CounterChain.copy(x4601, "x4601_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x4376_scalar), CU.scalarIn(stage(0), x4377_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr4218)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x4378_scalar), CU.scalarIn(stage(1), x4379_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr4219)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4218), CU.temp(stage(2), tr4219)), op=FixAdd, results=List(CU.scalarOut(stage(3), bus_4220_scalar)))
    }
    val x4601_1 = StreamPipeline(name = "x4601_1", parent=x4601, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr4222 = CU.temp
      val tr4221 = CU.temp
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x4368_scalar), CU.scalarIn(stage(0), x4369_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr4221)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x4370_scalar), CU.scalarIn(stage(1), x4371_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr4222)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4221), CU.temp(stage(2), tr4222)), op=FixAdd, results=List(CU.scalarOut(stage(3), bus_4223_scalar)))
    }
    val x4601_2 = StreamPipeline(name = "x4601_2", parent=x4601, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr4225 = CU.temp
      val tr4224 = CU.temp
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x4372_scalar), CU.scalarIn(stage(0), x4373_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr4224)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x4374_scalar), CU.scalarIn(stage(1), x4375_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr4225)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4224), CU.temp(stage(2), tr4225)), op=FixAdd, results=List(CU.scalarOut(stage(3), bus_4226_scalar)))
    }
    val x4601_3 = StreamPipeline(name = "x4601_3", parent=x4601, deps=List(x4601_1, x4601_2, x4601_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr4228 = CU.temp
      val tr4227 = CU.temp
      val ar4217 = CU.accum(init = Const("0i"))
      val x4601_unitcc = CounterChain.copy(x4601, "x4601_unitcc")
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), bus_4223_scalar), CU.scalarIn(stage(0), bus_4226_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr4227)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr4227), CU.scalarIn(stage(1), bus_4220_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr4228)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr4228), CU.accum(stage(3), ar4217)), op=FixAdd, results=List(CU.scalarOut(stage(3), x3154_argout), CU.accum(stage(3), ar4217)))
    }
    
  }
}
