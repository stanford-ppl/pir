import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProductDesign extends PIRApp {
  override val arch = P2P_4CU_4TT
  def main(args: String*)(top:Top) = {
    val x3810_scalar = Scalar()
    val x3764_vector = Vector()
    val x3893_scalar = Scalar()
    val x3809_scalar = Scalar()
    val x3811_scalar = Scalar()
    val x3766_scalar = Scalar()
    val x3768_scalar = Scalar()
    val x3812_scalar = Scalar()
    val x3762_scalar = Scalar()
    val x3763_scalar = Scalar()
    val x3675_oc = OffChip()
    val x3765_scalar = Scalar()
    val x3677_oc = OffChip()
    val x3808_vector = Vector()
    val x3890_vector = Vector()
    val x3761_vector = Vector()
    val x3668_argin = ArgIn()
    val x3673_oc = OffChip()
    val x3767_scalar = Scalar()
    val x3669_argin = ArgIn()
    val x3787_mc_mc = MemoryController(TileLoad, x3673_oc)
    val x3911_mc_mc = MemoryController(TileStore, x3677_oc)
    val x3831_mc_mc = MemoryController(TileLoad, x3675_oc)
    val x3917 = Sequential(name="x3917", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3917_unitCC = CounterChain(name = "x3917_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3915 = MetaPipeline(name="x3915", parent=x3917, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3756 = (Const("0i").out, CU.scalarIn(stage0, x3668_argin).out, Const("96i").out) // Counter
      val x3757 = (Const("0i").out, CU.scalarIn(stage0, x3669_argin).out, Const("96i").out) // Counter
      val x3758 = CounterChain(name = "x3758", x3756, x3757)
    }
    val x3807 = MetaPipeline(name="x3807", parent=x3915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3807_unitCC = CounterChain(name = "x3807_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3783 = UnitPipeline(name ="x3783", parent=x3807, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr155 = CU.temp
      val tr156 = CU.temp
      val tr152 = CU.temp
      val tr153 = CU.temp
      val tr146 = CU.temp
      val tr150 = CU.temp
      val x3758 = CounterChain.copy(x3915, "x3758")
      val x3783_unitCC = CounterChain(name = "x3783_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3758(0)), Const("96i")), op=FixMod, results=List(CU.scalarOut(stage(1), x3765_scalar), CU.temp(stage(1), tr146)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3758(0)), CU.temp(stage(1), tr146)), op=FixSub, results=List(CU.scalarOut(stage(2), x3767_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr146), Const("96i")), op=FixAdd, results=List(CU.scalarOut(stage(3), x3766_scalar), CU.temp(stage(3), tr150)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr150), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr152)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr150), CU.temp(stage(4), tr152)), op=FixSub, results=List(CU.temp(stage(5), tr153)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr152), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr155)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr155), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr156)))
    }
    val x3787 = TileTransfer(name="x3787", parent=x3807, memctrl=x3787_mc_mc, mctpe=TileLoad, deps=List(x3783), vec=x3764_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3787_ctr = (Const("0l").out, CU.scalarIn(stage0, x3768_scalar).out, Const("1l").out) // Counter
      val x3787_cc = CounterChain(name = "x3787_cc", x3787_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3767_scalar), CU.ctr(stage(0), x3787_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3787_mc_mc.saddr)))
    }
    val x3805 = Pipeline(name="x3805", parent=x3807, deps=List(x3787)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr169 = CU.temp
      val tr168 = CU.temp
      val tr167 = CU.temp
      val x3788 = (Const("0i").out, CU.scalarIn(stage0, x3768_scalar).out, Const("1i").out) // Counter
      val x3789 = CounterChain(name = "x3789", x3788)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3765_scalar), CU.ctr(stage(0), x3789(0))), op=FixLeq, results=List(CU.temp(stage(1), tr167)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3789(0)), CU.scalarIn(stage(1), x3766_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr168)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr167), CU.temp(stage(2), tr168)), op=BitAnd, results=List(CU.temp(stage(3), tr169)))
    }
    val x3851 = MetaPipeline(name="x3851", parent=x3915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3851_unitCC = CounterChain(name = "x3851_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3827 = UnitPipeline(name ="x3827", parent=x3851, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr180 = CU.temp
      val tr173 = CU.temp
      val tr179 = CU.temp
      val tr182 = CU.temp
      val tr183 = CU.temp
      val tr177 = CU.temp
      val x3758 = CounterChain.copy(x3915, "x3758")
      val x3827_unitCC = CounterChain(name = "x3827_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3758(1)), Const("96i")), op=FixMod, results=List(CU.scalarOut(stage(1), x3809_scalar), CU.temp(stage(1), tr173)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3758(1)), CU.temp(stage(1), tr173)), op=FixSub, results=List(CU.scalarOut(stage(2), x3811_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr173), Const("96i")), op=FixAdd, results=List(CU.scalarOut(stage(3), x3810_scalar), CU.temp(stage(3), tr177)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr177), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr179)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr177), CU.temp(stage(4), tr179)), op=FixSub, results=List(CU.temp(stage(5), tr180)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr179), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr182)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr182), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr183)))
    }
    val x3831 = TileTransfer(name="x3831", parent=x3851, memctrl=x3831_mc_mc, mctpe=TileLoad, deps=List(x3827), vec=x3808_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3831_ctr = (Const("0l").out, CU.scalarIn(stage0, x3812_scalar).out, Const("1l").out) // Counter
      val x3831_cc = CounterChain(name = "x3831_cc", x3831_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3811_scalar), CU.ctr(stage(0), x3831_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3831_mc_mc.saddr)))
    }
    val x3849 = Pipeline(name="x3849", parent=x3851, deps=List(x3831)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr195 = CU.temp
      val tr196 = CU.temp
      val tr194 = CU.temp
      val x3832 = (Const("0i").out, CU.scalarIn(stage0, x3812_scalar).out, Const("1i").out) // Counter
      val x3833 = CounterChain(name = "x3833", x3832)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3809_scalar), CU.ctr(stage(0), x3833(0))), op=FixLeq, results=List(CU.temp(stage(1), tr194)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3833(0)), CU.scalarIn(stage(1), x3810_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr195)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr194), CU.temp(stage(2), tr195)), op=BitAnd, results=List(CU.temp(stage(3), tr196)))
    }
    val x3857 = UnitPipeline(name ="x3857", parent=x3915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr200 = CU.temp
      val x3758 = CounterChain.copy(x3915, "x3758")
      val x3857_unitCC = CounterChain(name = "x3857_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3668_argin), CU.ctr(stage(0), x3758(0))), op=FixSub, results=List(CU.temp(stage(1), tr200)))
    }
    val x3863 = UnitPipeline(name ="x3863", parent=x3915, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr205 = CU.temp
      val x3758 = CounterChain.copy(x3915, "x3758")
      val x3863_unitCC = CounterChain(name = "x3863_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3669_argin), CU.ctr(stage(0), x3758(1))), op=FixSub, results=List(CU.temp(stage(1), tr205)))
    }
    val x3889 = Pipeline(name="x3889", parent=x3915, deps=List(x3807, x3851, x3857, x3863)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3833 = CounterChain.copy(x3849, "x3833")
      val x3871 = (Const("0i").out, CU.scalarIn(stage0, x3762_scalar).out, Const("1i").out) // Counter
      val x3872 = (Const("0i").out, CU.scalarIn(stage0, x3763_scalar).out, Const("1i").out) // Counter
      val x3873 = CounterChain(name = "x3873", x3871, x3872)
      val x3851_unitCC = CounterChain.copy(x3851, "x3851_unitCC")
      val x3789 = CounterChain.copy(x3805, "x3789")
      val x3807_unitCC = CounterChain.copy(x3807, "x3807_unitCC")
      val x3759_x3876 = SRAM(size = 96, writeCtr = x3789(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x3873(0), swapWrite = x3807_unitCC(0))).wtPort(x3764_vector).rdAddr(x3873(0)).wtAddr(x3789(0))
      val x3760_x3879 = SRAM(size = 96, writeCtr = x3833(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x3873(0), swapWrite = x3851_unitCC(0))).wtPort(x3808_vector).rdAddr(x3873(1)).wtAddr(x3833(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3759_x3876.load, x3760_x3879.load), op=FixMul, results=List(CU.vecOut(stage(1), x3761_vector)))
    }
    val x3913 = MetaPipeline(name="x3913", parent=x3915, deps=List(x3889)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3891 = (Const("0i").out, CU.scalarIn(stage0, x3762_scalar).out, Const("1i").out) // Counter
      val x3892 = CounterChain(name = "x3892", x3891)
    }
    val x3898 = UnitPipeline(name ="x3898", parent=x3913, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr225 = CU.temp
      val tr224 = CU.temp
      val x3758 = CounterChain.copy(x3915, "x3758")
      val x3892 = CounterChain.copy(x3913, "x3892")
      val x3898_unitCC = CounterChain(name = "x3898_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3758(0)), CU.ctr(stage(0), x3892(0))), op=FixAdd, results=List(CU.temp(stage(1), tr224)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr224), CU.scalarIn(stage(1), x3669_argin)), op=FixMul, results=List(CU.temp(stage(2), tr225)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr225), CU.ctr(stage(2), x3758(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3893_scalar)))
    }
    val x3909 = Pipeline(name="x3909", parent=x3913, deps=List(x3898)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr234 = CU.temp
      val tr229 = CU.temp
      val x3873 = CounterChain.copy(x3889, "x3873")
      val x3899 = (Const("0i").out, CU.scalarIn(stage0, x3763_scalar).out, Const("1i").out) // Counter
      val x3900 = CounterChain(name = "x3900", x3899)
      val x3892 = CounterChain.copy(x3913, "x3892")
      val x3761_x3903 = SRAM(size = 9216, writeCtr = x3873(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x3892(0), swapWrite = x3873(0))).wtPort(x3761_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3761_x3903))
      Stage(stage(1), operands=List(x3873(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr229)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr229), CU.ctr(stage(1), x3873(1))), op=FixAdd, results=List(x3761_x3903.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3892(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr234)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr234), CU.ctr(stage(1), x3900(0))), op=FixAdd, results=List(x3761_x3903.readAddr))
      Stage(stage(3), operands=List(x3761_x3903.load), op=Bypass, results=List(CU.vecOut(stage(3), x3890_vector)))
    }
    val x3911 = TileTransfer(name="x3911", parent=x3913, memctrl=x3911_mc_mc, mctpe=TileStore, deps=List(x3909), vec=x3890_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3911_ctr = (Const("0l").out, CU.scalarIn(stage0, x3763_scalar).out, Const("1l").out) // Counter
      val x3911_cc = CounterChain(name = "x3911_cc", x3911_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3893_scalar), CU.ctr(stage(0), x3911_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3911_mc_mc.saddr)))
    }
    
  }
}
