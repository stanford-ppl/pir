import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object SimpleFoldDesign extends PIRApp {
  override val arch = SN_4x4 
  def main(args: String*)(top:Top) = {
    val x546_oc = OffChip()
    val x586_scalar = Scalar()
    val x543_argout = ArgOut()
    val x661_scalar = Scalar()
    val x542_argin = ArgIn()
    val x645_vector = Vector()
    val x646_mc_mc = MemoryController(TileLoad, x546_oc)
    val x696 = Sequential(name="x696", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x696_unitCC = CounterChain(name = "x696_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x694 = Sequential(name="x694", parent=x696, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x694_unitCC = CounterChain(name = "x694_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x687 = MetaPipeline(name="x687", parent=x694, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x587 = (Const("0i").out, CU.scalarIn(stage0, x542_argin).out, Const("96i").out) // Counter
      val x588 = CounterChain(name = "x588", x587)
    }
    val x658 = MetaPipeline(name="x658", parent=x687, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x658_unitCC = CounterChain(name = "x658_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x646 = TileTransfer(name="x646", parent=x658, memctrl=x646_mc_mc, mctpe=TileLoad, deps=List(), vec=x645_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x646_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x646_cc = CounterChain(name = "x646_cc", x646_ctr)
      val x588 = CounterChain.copy(x687, "x588")
      val x647 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x648 = CounterChain(name = "x648", x647).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x588(0)), CU.ctr(stage(0), x646_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x646_mc_mc.ofs)))
    }
    val x674 = Pipeline(name="x674", parent=x687, deps=List(x658)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x648 = CounterChain.copy(x646, "x648")
      val x658_unitCC = CounterChain.copy(x658, "x658_unitCC")
      val x659 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x660 = CounterChain(name = "x660", x659)
      val x644_x667 = SRAM(size = 96, writeCtr = x648(0), banking = Strided(1), buffering = DoubleBuffer(swapRead = x660(0), swapWrite = x658_unitCC(0))).wtPort(x645_vector).rdAddr(x660(0)).wtAddr(x648(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(x644_x667.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr37) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr37), op=Bypass, results=List(CU.scalarOut(stage(2), x661_scalar)))
    }
    val x685 = UnitPipeline(name ="x685", parent=x687, deps=List(x674)) { implicit CU => 
      val stage0 = CU.emptyStage
      val ar41 = CU.accum(init = Const("0i"))
      val x685_unitCC = CounterChain(name = "x685_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x661_scalar), CU.accum(stage(1), ar41)), op=FixAdd, results=List(CU.scalarOut(stage(1), x586_scalar), CU.accum(stage(1), ar41)))
    }
    val x691 = UnitPipeline(name ="x691", parent=x694, deps=List(x687)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x691_unitCC = CounterChain(name = "x691_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x586_scalar)), op=Bypass, results=List(CU.scalarOut(stage(1), x543_argout)))
    }
    
  }
}
