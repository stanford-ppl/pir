import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object OuterProductDesign extends PIRApp {
  override val arch = SN_4x4
  def main(args: String*)(top:Top) = {
    val x3775_vector = Vector("x3775")
    val x3683_argin = ArgIn("x3683")
    val x3778_vector = Vector("x3778")
    val x3825_scalar = Scalar("x3825")
    val x3781_scalar = Scalar("x3781")
    val x3689_oc = OffChip("x3689")
    val x3906_vector = Vector("x3906")
    val x3823_vector = Vector("x3823")
    val x3691_oc = OffChip("x3691")
    val x3777_scalar = Scalar("x3777")
    val x3782_scalar = Scalar("x3782")
    val x3827_scalar = Scalar("x3827")
    val x3909_scalar = Scalar("x3909")
    val x3780_scalar = Scalar("x3780")
    val x3826_scalar = Scalar("x3826")
    val x3779_scalar = Scalar("x3779")
    val x3687_oc = OffChip("x3687")
    val x3824_scalar = Scalar("x3824")
    val x3682_argin = ArgIn("x3682")
    val x3776_scalar = Scalar("x3776")
    val x3846_mc_mc = MemoryController(TileLoad, x3689_oc)
    val x3927_mc_mc = MemoryController(TileStore, x3691_oc)
    val x3801_mc_mc = MemoryController(TileLoad, x3687_oc)
    val x3933 = Sequential(name="x3933", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3933_unitCC = CounterChain(name = "x3933_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3931 = MetaPipeline(name="x3931", parent=x3933, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3770 = (Const("0i").out, CU.scalarIn(stage0, x3682_argin).out, Const("96i").out) // Counter
      val x3771 = (Const("0i").out, CU.scalarIn(stage0, x3683_argin).out, Const("96i").out) // Counter
      val x3772 = CounterChain(name = "x3772", x3770, x3771)
    }
    val x3822 = MetaPipeline(name="x3822", parent=x3931, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3822_unitCC = CounterChain(name = "x3822_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3797 = UnitPipeline(name ="x3797", parent=x3822, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr159 = CU.temp
      val tr158 = CU.temp
      val tr156 = CU.temp
      val tr155 = CU.temp
      val tr153 = CU.temp
      val tr149 = CU.temp
      val x3772 = CounterChain.copy(x3931, "x3772")
      val x3797_unitCC = CounterChain(name = "x3797_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3772(0)), Const("96i")), op=FixMod, results=List(CU.scalarOut(stage(1), x3779_scalar), CU.temp(stage(1), tr149)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3772(0)), CU.temp(stage(1), tr149)), op=FixSub, results=List(CU.scalarOut(stage(2), x3781_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr149), Const("96i")), op=FixAdd, results=List(CU.scalarOut(stage(3), x3780_scalar), CU.temp(stage(3), tr153)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr153), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr155)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr153), CU.temp(stage(4), tr155)), op=FixSub, results=List(CU.temp(stage(5), tr156)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr155), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr158)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr158), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr159)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr156), CU.temp(stage(7), tr159)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3782_scalar)))
    }
    val x3801 = TileTransfer(name="x3801", parent=x3822, memctrl=x3801_mc_mc, mctpe=TileLoad, deps=List(x3797), vec=x3778_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3801_ctr = (Const("0l").out, CU.scalarIn(stage0, x3782_scalar).out, Const("1l").out) // Counter
      val x3801_cc = CounterChain(name = "x3801_cc", x3801_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3781_scalar), CU.ctr(stage(0), x3801_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3801_mc_mc.ofs)))
    }
    val x3820 = Pipeline(name="x3820", parent=x3822, deps=List(x3801)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr173 = CU.temp
      val tr172 = CU.temp
      val tr171 = CU.temp
      val x3802 = (Const("0i").out, CU.scalarIn(stage0, x3782_scalar).out, Const("1i").out) // Counter
      val x3803 = CounterChain(name = "x3803", x3802)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3779_scalar), CU.ctr(stage(0), x3803(0))), op=FixLeq, results=List(CU.temp(stage(1), tr171)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3803(0)), CU.scalarIn(stage(1), x3780_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr172)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr171), CU.temp(stage(2), tr172)), op=BitAnd, results=List(CU.temp(stage(3), tr173)))
    }
    val x3867 = MetaPipeline(name="x3867", parent=x3931, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3867_unitCC = CounterChain(name = "x3867_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x3842 = UnitPipeline(name ="x3842", parent=x3867, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr187 = CU.temp
      val tr186 = CU.temp
      val tr184 = CU.temp
      val tr183 = CU.temp
      val tr181 = CU.temp
      val tr177 = CU.temp
      val x3772 = CounterChain.copy(x3931, "x3772")
      val x3842_unitCC = CounterChain(name = "x3842_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3772(1)), Const("96i")), op=FixMod, results=List(CU.scalarOut(stage(1), x3824_scalar), CU.temp(stage(1), tr177)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3772(1)), CU.temp(stage(1), tr177)), op=FixSub, results=List(CU.scalarOut(stage(2), x3826_scalar)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr177), Const("96i")), op=FixAdd, results=List(CU.scalarOut(stage(3), x3825_scalar), CU.temp(stage(3), tr181)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr181), Const("96i")), op=FixMod, results=List(CU.temp(stage(4), tr183)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr181), CU.temp(stage(4), tr183)), op=FixSub, results=List(CU.temp(stage(5), tr184)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr183), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr186)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr186), Const("96i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr187)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr184), CU.temp(stage(7), tr187)), op=FixAdd, results=List(CU.scalarOut(stage(8), x3827_scalar)))
    }
    val x3846 = TileTransfer(name="x3846", parent=x3867, memctrl=x3846_mc_mc, mctpe=TileLoad, deps=List(x3842), vec=x3823_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3846_ctr = (Const("0l").out, CU.scalarIn(stage0, x3827_scalar).out, Const("1l").out) // Counter
      val x3846_cc = CounterChain(name = "x3846_cc", x3846_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3826_scalar), CU.ctr(stage(0), x3846_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3846_mc_mc.ofs)))
    }
    val x3865 = Pipeline(name="x3865", parent=x3867, deps=List(x3846)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr201 = CU.temp
      val tr200 = CU.temp
      val tr199 = CU.temp
      val x3847 = (Const("0i").out, CU.scalarIn(stage0, x3827_scalar).out, Const("1i").out) // Counter
      val x3848 = CounterChain(name = "x3848", x3847)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3824_scalar), CU.ctr(stage(0), x3848(0))), op=FixLeq, results=List(CU.temp(stage(1), tr199)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x3848(0)), CU.scalarIn(stage(1), x3825_scalar)), op=FixLt, results=List(CU.temp(stage(2), tr200)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr199), CU.temp(stage(2), tr200)), op=BitAnd, results=List(CU.temp(stage(3), tr201)))
    }
    val x3873 = UnitPipeline(name ="x3873", parent=x3931, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr205 = CU.temp
      val x3772 = CounterChain.copy(x3931, "x3772")
      val x3873_unitCC = CounterChain(name = "x3873_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3682_argin), CU.ctr(stage(0), x3772(0))), op=FixSub, results=List(CU.temp(stage(1), tr205)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr205), Const("96i")), op=FltMin, results=List(CU.scalarOut(stage(2), x3776_scalar)))
    }
    val x3879 = UnitPipeline(name ="x3879", parent=x3931, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr210 = CU.temp
      val x3772 = CounterChain.copy(x3931, "x3772")
      val x3879_unitCC = CounterChain(name = "x3879_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3683_argin), CU.ctr(stage(0), x3772(1))), op=FixSub, results=List(CU.temp(stage(1), tr210)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr210), Const("96i")), op=FltMin, results=List(CU.scalarOut(stage(2), x3777_scalar)))
    }
    val x3905 = Pipeline(name="x3905", parent=x3931, deps=List(x3822, x3867, x3873, x3879)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3803 = CounterChain.copy(x3820, "x3803")
      val x3867_unitCC = CounterChain.copy(x3867, "x3867_unitCC")
      val x3887 = (Const("0i").out, CU.scalarIn(stage0, x3776_scalar).out, Const("1i").out) // Counter
      val x3888 = (Const("0i").out, CU.scalarIn(stage0, x3777_scalar).out, Const("1i").out) // Counter
      val x3889 = CounterChain(name = "x3889", x3887, x3888)
      val x3848 = CounterChain.copy(x3865, "x3848")
      val x3822_unitCC = CounterChain.copy(x3822, "x3822_unitCC")
      val x3773_x3892 = SRAM(size = 96, writeCtr = x3803(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x3889(0), swapWrite = x3822_unitCC(0))).wtPort(x3778_vector).rdAddr(x3889(0))
      val x3774_x3895 = SRAM(size = 96, writeCtr = x3848(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x3889(0), swapWrite = x3867_unitCC(0))).wtPort(x3823_vector).rdAddr(x3889(1))
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(1, List(x3773_x3892))
      Stage(stage(1), operands=List(x3803(0), CU.scalarIn(stage(0), x3779_scalar)), op=FixSub, results=List(x3773_x3892.writeAddr))
      stage = stage0 +: WAStages(1, List(x3774_x3895))
      Stage(stage(1), operands=List(x3848(0), CU.scalarIn(stage(0), x3824_scalar)), op=FixSub, results=List(x3774_x3895.writeAddr))
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x3773_x3892.load, x3774_x3895.load), op=FixMul, results=List(CU.vecOut(stage(1), x3775_vector)))
    }
    val x3929 = MetaPipeline(name="x3929", parent=x3931, deps=List(x3905)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3907 = (Const("0i").out, CU.scalarIn(stage0, x3776_scalar).out, Const("1i").out) // Counter
      val x3908 = CounterChain(name = "x3908", x3907)
    }
    val x3914 = UnitPipeline(name ="x3914", parent=x3929, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr234 = CU.temp
      val tr233 = CU.temp
      val x3772 = CounterChain.copy(x3931, "x3772")
      val x3908 = CounterChain.copy(x3929, "x3908")
      val x3914_unitCC = CounterChain(name = "x3914_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3772(0)), CU.ctr(stage(0), x3908(0))), op=FixAdd, results=List(CU.temp(stage(1), tr233)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr233), CU.scalarIn(stage(1), x3683_argin)), op=FixMul, results=List(CU.temp(stage(2), tr234)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr234), CU.ctr(stage(2), x3772(1))), op=FixAdd, results=List(CU.scalarOut(stage(3), x3909_scalar)))
    }
    val x3925 = Pipeline(name="x3925", parent=x3929, deps=List(x3914)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr238 = CU.temp
      val tr243 = CU.temp
      val x3908 = CounterChain.copy(x3929, "x3908")
      val x3915 = (Const("0i").out, CU.scalarIn(stage0, x3777_scalar).out, Const("1i").out) // Counter
      val x3916 = CounterChain(name = "x3916", x3915)
      val x3889 = CounterChain.copy(x3905, "x3889")
      val x3775_x3919 = SRAM(size = 9216, writeCtr = x3889(0), banking = Duplicated(), buffering = MultiBuffer(2, swapRead = x3908(0), swapWrite = x3889(0))).wtPort(x3775_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x3775_x3919))
      Stage(stage(1), operands=List(x3889(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr238)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr238), CU.ctr(stage(1), x3889(1))), op=FixAdd, results=List(x3775_x3919.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3908(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr243)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr243), CU.ctr(stage(1), x3916(0))), op=FixAdd, results=List(x3775_x3919.readAddr))
      Stage(stage(3), operands=List(x3775_x3919.load), op=Bypass, results=List(CU.vecOut(stage(3), x3906_vector)))
    }
    val x3927 = TileTransfer(name="x3927", parent=x3929, memctrl=x3927_mc_mc, mctpe=TileStore, deps=List(x3925), vec=x3906_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3927_ctr = (Const("0l").out, CU.scalarIn(stage0, x3777_scalar).out, Const("1l").out) // Counter
      val x3927_cc = CounterChain(name = "x3927_cc", x3927_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3909_scalar), CU.ctr(stage(0), x3927_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3927_mc_mc.ofs)))
    }
    
  }
}
